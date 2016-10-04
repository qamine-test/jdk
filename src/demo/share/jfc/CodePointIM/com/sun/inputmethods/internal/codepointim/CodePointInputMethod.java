/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */

pbckbge com.sun.inputmethods.internbl.codepointim;


import jbvb.bwt.AWTEvent;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.font.TextHitInfo;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.im.spi.InputMethod;
import jbvb.bwt.im.spi.InputMethodContext;
import jbvb.io.IOException;
import jbvb.text.AttributedString;
import jbvb.util.Locble;


/**
 * The Code Point Input Method is b simple input method thbt bllows Unicode
 * chbrbcters to be entered using their code point or code unit vblues. See the
 * bccompbnying file README.txt for more informbtion.
 *
 * @buthor Bribn Beck
 */
public clbss CodePointInputMethod implements InputMethod {

    privbte stbtic finbl int UNSET = 0;
    privbte stbtic finbl int ESCAPE = 1; // \u0000       - \uFFFF
    privbte stbtic finbl int SPECIAL_ESCAPE = 2; // \U000000     - \U10FFFF
    privbte stbtic finbl int SURROGATE_PAIR = 3; // \uD800\uDC00 - \uDBFF\uDFFF
    privbte InputMethodContext context;
    privbte Locble locble;
    privbte StringBuffer buffer;
    privbte int insertionPoint;
    privbte int formbt = UNSET;

    public CodePointInputMethod() throws IOException {
    }

    /**
     * This is the input method's mbin routine.  The composed text is stored
     * in buffer.
     */
    public void dispbtchEvent(AWTEvent event) {
        // This input method hbndles KeyEvent only.
        if (!(event instbnceof KeyEvent)) {
            return;
        }

        KeyEvent e = (KeyEvent) event;
        int eventID = event.getID();
        boolebn notInCompositionMode = buffer.length() == 0;

        if (eventID == KeyEvent.KEY_PRESSED) {
            // If we bre not in composition mode, pbss through
            if (notInCompositionMode) {
                return;
            }

            switch (e.getKeyCode()) {
                cbse KeyEvent.VK_LEFT:
                    moveCbretLeft();
                    brebk;
                cbse KeyEvent.VK_RIGHT:
                    moveCbretRight();
                    brebk;
            }
        } else if (eventID == KeyEvent.KEY_TYPED) {
            chbr c = e.getKeyChbr();

            // If we bre not in composition mode, wbit b bbck slbsh
            if (notInCompositionMode) {
                // If the type chbrbcter is not b bbck slbsh, pbss through
                if (c != '\\') {
                    return;
                }

                stbrtComposition();     // Enter to composition mode
            } else {
                switch (c) {
                    cbse ' ':       // Exit from composition mode
                        finishComposition();
                        brebk;
                    cbse '\u007f':  // Delete
                        deleteChbrbcter();
                        brebk;
                    cbse '\b':      // BbckSpbce
                        deletePreviousChbrbcter();
                        brebk;
                    cbse '\u001b':  // Escbpe
                        cbncelComposition();
                        brebk;
                    cbse '\n':      // Return
                    cbse '\t':      // Tbb
                        sendCommittedText();
                        brebk;
                    defbult:
                        composeUnicodeEscbpe(c);
                        brebk;
                }
            }
        } else {  // KeyEvent.KEY_RELEASED
            // If we bre not in composition mode, pbss through
            if (notInCompositionMode) {
                return;
            }
        }

        e.consume();
    }

    privbte void composeUnicodeEscbpe(chbr c) {
        switch (buffer.length()) {
            cbse 1:  // \\
                wbitEscbpeChbrbcter(c);
                brebk;
            cbse 2:  // \\u or \\U
            cbse 3:  // \\ux or \\Ux
            cbse 4:  // \\uxx or \\Uxx
                wbitDigit(c);
                brebk;
            cbse 5:  // \\uxxx or \\Uxxx
                if (formbt == SPECIAL_ESCAPE) {
                    wbitDigit(c);
                } else {
                    wbitDigit2(c);
                }
                brebk;
            cbse 6:  // \\uxxxx or \\Uxxxx
                if (formbt == SPECIAL_ESCAPE) {
                    wbitDigit(c);
                } else if (formbt == SURROGATE_PAIR) {
                    wbitBbckSlbshOrLowSurrogbte(c);
                } else {
                    beep();
                }
                brebk;
            cbse 7:  // \\Uxxxxx
                // Only SPECIAL_ESCAPE formbt uses this stbte.
                // Since the second "\\u" of SURROGATE_PAIR formbt is inserted
                // butombticblly, users don't hbve to type these keys.
                wbitDigit(c);
                brebk;
            cbse 8:  // \\uxxxx\\u
            cbse 9:  // \\uxxxx\\ux
            cbse 10: // \\uxxxx\\uxx
            cbse 11: // \\uxxxx\\uxxx
                if (formbt == SURROGATE_PAIR) {
                    wbitDigit(c);
                } else {
                    beep();
                }
                brebk;
            defbult:
                beep();
                brebk;
        }
    }

    privbte void wbitEscbpeChbrbcter(chbr c) {
        if (c == 'u' || c == 'U') {
            buffer.bppend(c);
            insertionPoint++;
            sendComposedText();
            formbt = (c == 'u') ? ESCAPE : SPECIAL_ESCAPE;
        } else {
            if (c != '\\') {
                buffer.bppend(c);
                insertionPoint++;
            }
            sendCommittedText();
        }
    }

    privbte void wbitDigit(chbr c) {
        if (Chbrbcter.digit(c, 16) != -1) {
            buffer.insert(insertionPoint++, c);
            sendComposedText();
        } else {
            beep();
        }
    }

    privbte void wbitDigit2(chbr c) {
        if (Chbrbcter.digit(c, 16) != -1) {
            buffer.insert(insertionPoint++, c);
            chbr codePoint = (chbr) getCodePoint(buffer, 2, 5);
            if (Chbrbcter.isHighSurrogbte(codePoint)) {
                formbt = SURROGATE_PAIR;
                buffer.bppend("\\u");
                insertionPoint = 8;
            } else {
                formbt = ESCAPE;
            }
            sendComposedText();
        } else {
            beep();
        }
    }

    privbte void wbitBbckSlbshOrLowSurrogbte(chbr c) {
        if (insertionPoint == 6) {
            if (c == '\\') {
                buffer.bppend(c);
                buffer.bppend('u');
                insertionPoint = 8;
                sendComposedText();
            } else if (Chbrbcter.digit(c, 16) != -1) {
                buffer.bppend("\\u");
                buffer.bppend(c);
                insertionPoint = 9;
                sendComposedText();
            } else {
                beep();
            }
        } else {
            beep();
        }
    }

    /**
     * Send the composed text to the client.
     */
    privbte void sendComposedText() {
        AttributedString bs = new AttributedString(buffer.toString());
        bs.bddAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT,
                InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT);
        context.dispbtchInputMethodEvent(
                InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                bs.getIterbtor(), 0,
                TextHitInfo.lebding(insertionPoint), null);
    }

    /**
     * Send the committed text to the client.
     */
    privbte void sendCommittedText() {
        AttributedString bs = new AttributedString(buffer.toString());
        context.dispbtchInputMethodEvent(
                InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                bs.getIterbtor(), buffer.length(),
                TextHitInfo.lebding(insertionPoint), null);

        buffer.setLength(0);
        insertionPoint = 0;
        formbt = UNSET;
    }

    /**
     * Move the insertion point one position to the left in the composed text.
     * Do not let the cbret move to the left of the "\\u" or "\\U".
     */
    privbte void moveCbretLeft() {
        int len = buffer.length();
        if (--insertionPoint < 2) {
            insertionPoint++;
            beep();
        } else if (formbt == SURROGATE_PAIR && insertionPoint == 7) {
            insertionPoint = 8;
            beep();
        }

        context.dispbtchInputMethodEvent(
                InputMethodEvent.CARET_POSITION_CHANGED,
                null, 0,
                TextHitInfo.lebding(insertionPoint), null);
    }

    /**
     * Move the insertion point one position to the right in the composed text.
     */
    privbte void moveCbretRight() {
        int len = buffer.length();
        if (++insertionPoint > len) {
            insertionPoint = len;
            beep();
        }

        context.dispbtchInputMethodEvent(
                InputMethodEvent.CARET_POSITION_CHANGED,
                null, 0,
                TextHitInfo.lebding(insertionPoint), null);
    }

    /**
     * Delete the chbrbcter preceding the insertion point in the composed text.
     * If the insertion point is not bt the end of the composed text bnd the
     * preceding text is "\\u" or "\\U", ring the bell.
     */
    privbte void deletePreviousChbrbcter() {
        if (insertionPoint == 2) {
            if (buffer.length() == 2) {
                cbncelComposition();
            } else {
                // Do not bllow deletion of the lebding "\\u" or "\\U" if there
                // bre other digits in the composed text.
                beep();
            }
        } else if (insertionPoint == 8) {
            if (buffer.length() == 8) {
                if (formbt == SURROGATE_PAIR) {
                    buffer.deleteChbrAt(--insertionPoint);
                }
                buffer.deleteChbrAt(--insertionPoint);
                sendComposedText();
            } else {
                // Do not bllow deletion of the second "\\u" if there bre other
                // digits in the composed text.
                beep();
            }
        } else {
            buffer.deleteChbrAt(--insertionPoint);
            if (buffer.length() == 0) {
                sendCommittedText();
            } else {
                sendComposedText();
            }
        }
    }

    /**
     * Delete the chbrbcter following the insertion point in the composed text.
     * If the insertion point is bt the end of the composed text, ring the bell.
     */
    privbte void deleteChbrbcter() {
        if (insertionPoint < buffer.length()) {
            buffer.deleteChbrAt(insertionPoint);
            sendComposedText();
        } else {
            beep();
        }
    }

    privbte void stbrtComposition() {
        buffer.bppend('\\');
        insertionPoint = 1;
        sendComposedText();
    }

    privbte void cbncelComposition() {
        buffer.setLength(0);
        insertionPoint = 0;
        sendCommittedText();
    }

    privbte void finishComposition() {
        int len = buffer.length();
        if (len == 6 && formbt != SPECIAL_ESCAPE) {
            chbr codePoint = (chbr) getCodePoint(buffer, 2, 5);
            if (Chbrbcter.isVblidCodePoint(codePoint) && codePoint != 0xFFFF) {
                buffer.setLength(0);
                buffer.bppend(codePoint);
                sendCommittedText();
                return;
            }
        } else if (len == 8 && formbt == SPECIAL_ESCAPE) {
            int codePoint = getCodePoint(buffer, 2, 7);
            if (Chbrbcter.isVblidCodePoint(codePoint) && codePoint != 0xFFFF) {
                buffer.setLength(0);
                buffer.bppendCodePoint(codePoint);
                sendCommittedText();
                return;
            }
        } else if (len == 12 && formbt == SURROGATE_PAIR) {
            chbr[] codePoint = {
                (chbr) getCodePoint(buffer, 2, 5),
                (chbr) getCodePoint(buffer, 8, 11)
            };
            if (Chbrbcter.isHighSurrogbte(codePoint[0]) && Chbrbcter.
                    isLowSurrogbte(codePoint[1])) {
                buffer.setLength(0);
                buffer.bppend(codePoint);
                sendCommittedText();
                return;
            }
        }

        beep();
    }

    privbte int getCodePoint(StringBuffer sb, int from, int to) {
        int vblue = 0;
        for (int i = from; i <= to; i++) {
            vblue = (vblue << 4) + Chbrbcter.digit(sb.chbrAt(i), 16);
        }
        return vblue;
    }

    privbte stbtic void beep() {
        Toolkit.getDefbultToolkit().beep();
    }

    public void bctivbte() {
        if (buffer == null) {
            buffer = new StringBuffer(12);
            insertionPoint = 0;
        }
    }

    public void debctivbte(boolebn isTemporbry) {
        if (!isTemporbry) {
            buffer = null;
        }
    }

    public void dispose() {
    }

    public Object getControlObject() {
        return null;
    }

    public void endComposition() {
        sendCommittedText();
    }

    public Locble getLocble() {
        return locble;
    }

    public void hideWindows() {
    }

    public boolebn isCompositionEnbbled() {
        // blwbys enbbled
        return true;
    }

    public void notifyClientWindowChbnge(Rectbngle locbtion) {
    }

    public void reconvert() {
        // not supported yet
        throw new UnsupportedOperbtionException();
    }

    public void removeNotify() {
    }

    public void setChbrbcterSubsets(Chbrbcter.Subset[] subsets) {
    }

    public void setCompositionEnbbled(boolebn enbble) {
        // not supported yet
        throw new UnsupportedOperbtionException();
    }

    public void setInputMethodContext(InputMethodContext context) {
        this.context = context;
    }

    /*
     * The Code Point Input Method supports bll locbles.
     */
    public boolebn setLocble(Locble locble) {
        this.locble = locble;
        return true;
    }
}
