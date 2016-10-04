/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.im.spi.*;
import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.*;
import jbvb.bwt.im.*;
import jbvb.bwt.font.*;
import jbvb.lbng.Chbrbcter.Subset;
import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.*;
import jbvbx.swing.text.JTextComponent;

import sun.bwt.im.InputMethodAdbpter;
import sun.lwbwt.*;

public clbss CInputMethod extends InputMethodAdbpter {
    privbte InputMethodContext fIMContext;
    privbte Component fAwtFocussedComponent;
    privbte LWComponentPeer<?, ?> fAwtFocussedComponentPeer;
    privbte boolebn isActive;

    privbte stbtic Mbp<TextAttribute, Integer>[] sHighlightStyles;

    // Intitblize highlight mbpping tbble bnd its mbpper.
    stbtic {
        @SuppressWbrnings({"rbwtypes", "unchecked"})
        Mbp<TextAttribute, Integer> styles[] = new Mbp[4];
        HbshMbp<TextAttribute, Integer> mbp;

        // UNSELECTED_RAW_TEXT_HIGHLIGHT
        mbp = new HbshMbp<TextAttribute, Integer>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE,
                TextAttribute.UNDERLINE_LOW_GRAY);
        styles[0] = Collections.unmodifibbleMbp(mbp);

        // SELECTED_RAW_TEXT_HIGHLIGHT
        mbp = new HbshMbp<TextAttribute, Integer>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE,
                TextAttribute.UNDERLINE_LOW_GRAY);
        styles[1] = Collections.unmodifibbleMbp(mbp);

        // UNSELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = new HbshMbp<TextAttribute, Integer>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE,
                TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
        styles[2] = Collections.unmodifibbleMbp(mbp);

        // SELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = new HbshMbp<TextAttribute, Integer>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE,
                TextAttribute.UNDERLINE_LOW_TWO_PIXEL);
        styles[3] = Collections.unmodifibbleMbp(mbp);

        sHighlightStyles = styles;

        nbtiveInit();

    }

    public CInputMethod() {
    }


    /**
        * Sets the input method context, which is used to dispbtch input method
     * events to the client component bnd to request informbtion from
     * the client component.
     * <p>
     * This method is cblled once immedibtely bfter instbntibting this input
     * method.
     *
     * @pbrbm context the input method context for this input method
     * @exception NullPointerException if <code>context</code> is null
     */
    public void setInputMethodContext(InputMethodContext context) {
        fIMContext = context;
    }

    /**
        * Attempts to set the input locble. If the input method supports the
     * desired locble, it chbnges its behbvior to support input for the locble
     * bnd returns true.
     * Otherwise, it returns fblse bnd does not chbnge its behbvior.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod},
     * <li>when switching to this input method through the user interfbce if the user
     *     specified b locble or if the previously selected input method's
     *     {@link jbvb.bwt.im.spi.InputMethod#getLocble getLocble} method
     *     returns b non-null vblue.
     * </ul>
     *
     * @pbrbm lbng locble to input
     * @return whether the specified locble is supported
     * @exception NullPointerException if <code>locble</code> is null
     */
    public boolebn setLocble(Locble lbng) {
        return setLocble(lbng, fblse);
    }

    privbte boolebn setLocble(Locble lbng, boolebn onActivbte) {
        Object[] bvbilbble = CInputMethodDescriptor.getAvbilbbleLocblesInternbl();
        for (int i = 0; i < bvbilbble.length; i++) {
            Locble locble = (Locble)bvbilbble[i];
            if (lbng.equbls(locble) ||
                // specibl compbtibility rule for Jbpbnese bnd Korebn
                locble.equbls(Locble.JAPAN) && lbng.equbls(Locble.JAPANESE) ||
                locble.equbls(Locble.KOREA) && lbng.equbls(Locble.KOREAN)) {
                if (isActive) {
                    setNbtiveLocble(locble.toString(), onActivbte);
                }
                return true;
            }
        }
        return fblse;
    }

    /**
        * Returns the current input locble. Might return null in exceptionbl cbses.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#getLocble InputContext.getLocble} bnd
     * <li>when switching from this input method to b different one through the
     *     user interfbce.
     * </ul>
     *
     * @return the current input locble, or null
     */
    public Locble getLocble() {
        // On Mbc OS X we'll bsk the currently bctive input method whbt its locble is.
        Locble returnVblue = getNbtiveLocble();
        if (returnVblue == null) {
            returnVblue = Locble.getDefbult();
        }

        return returnVblue;
    }

    /**
        * Sets the subsets of the Unicode chbrbcter set thbt this input method
     * is bllowed to input. Null mby be pbssed in to indicbte thbt bll
     * chbrbcters bre bllowed.
     * <p>
     * This method is cblled
     * <ul>
     * <li>immedibtely bfter instbntibting this input method,
     * <li>when switching to this input method from b different one, bnd
     * <li>by {@link jbvb.bwt.im.InputContext#setChbrbcterSubsets InputContext.setChbrbcterSubsets}.
     * </ul>
     *
     * @pbrbm subsets the subsets of the Unicode chbrbcter set from which
     * chbrbcters mby be input
     */
    public void setChbrbcterSubsets(Subset[] subsets) {
        // -- SAK: Does mbc OS X support this?
    }

    /**
        * Composition cbnnot be set on Mbc OS X -- the input method remembers this
     */
    public void setCompositionEnbbled(boolebn enbble) {
        throw new UnsupportedOperbtionException("Cbn't bdjust composition mode on Mbc OS X.");
    }

    public boolebn isCompositionEnbbled() {
        throw new UnsupportedOperbtionException("Cbn't bdjust composition mode on Mbc OS X.");
    }

    /**
     * Dispbtches the event to the input method. If input method support is
     * enbbled for the focussed component, incoming events of certbin types
     * bre dispbtched to the current input method for this component before
     * they bre dispbtched to the component's methods or event listeners.
     * The input method decides whether it needs to hbndle the event. If it
     * does, it blso cblls the event's <code>consume</code> method; this
     * cbuses the event to not get dispbtched to the component's event
     * processing methods or event listeners.
     * <p>
     * Events bre dispbtched if they bre instbnces of InputEvent or its
     * subclbsses.
     * This includes instbnces of the AWT clbsses KeyEvent bnd MouseEvent.
     * <p>
     * This method is cblled by {@link jbvb.bwt.im.InputContext#dispbtchEvent InputContext.dispbtchEvent}.
     *
     * @pbrbm event the event being dispbtched to the input method
     * @exception NullPointerException if <code>event</code> is null
     */
    public void dispbtchEvent(finbl AWTEvent event) {
        // No-op for Mbc OS X.
    }


    /**
     * Activbte bnd debctivbte bre no-ops on Mbc OS X.
     * A non-US keybobrd lbyout is bn 'input method' in thbt it generbtes events the sbme wby bs
     * b CJK input method. A component thbt doesn't wbnt input method events still wbnts the debd-key
     * events.
     *
     *
     */
    public void bctivbte() {
        isActive = true;
    }

    public void debctivbte(boolebn isTemporbry) {
        isActive = fblse;
    }

    /**
     * Closes or hides bll windows opened by this input method instbnce or
     * its clbss.  Debctivbte hides windows for us on Mbc OS X.
     */
    public void hideWindows() {
    }

    long getNbtiveViewPtr(LWComponentPeer<?, ?> peer) {
        if (peer.getPlbtformWindow() instbnceof CPlbtformWindow) {
            CPlbtformWindow plbtformWindow = (CPlbtformWindow) peer.getPlbtformWindow();
            CPlbtformView plbtformView = plbtformWindow.getContentView();
            return plbtformView.getAWTView();
        } else {
            return 0;
        }
    }

    /**
        * Notifies the input method thbt b client component hbs been
     * removed from its contbinment hierbrchy, or thbt input method
     * support hbs been disbbled for the component.
     */
    public void removeNotify() {
        if (fAwtFocussedComponentPeer != null) {
            nbtiveEndComposition(getNbtiveViewPtr(fAwtFocussedComponentPeer));
        }

        fAwtFocussedComponentPeer = null;
    }

    /**
     * Informs the input method bdbpter bbout the component thbt hbs the AWT
     * focus if it's using the input context owning this bdbpter instbnce.
     * We blso tbke the opportunity to tell the nbtive side thbt we bre the input method
     * to tblk to when responding to key events.
     */
    protected void setAWTFocussedComponent(Component component) {
        LWComponentPeer<?, ?> peer = null;
        long modelPtr = 0;
        CInputMethod imInstbnce = this;

        // component will be null when we bre told there's no focused component.
        // When thbt hbppens we need to notify the nbtive brchitecture to stop generbting IMEs
        if (component == null) {
            peer = fAwtFocussedComponentPeer;
            imInstbnce = null;
        } else {
            peer = getNebrestNbtivePeer(component);

            // If we hbve b pbssive client, don't pbss input method events to it.
            if (component.getInputMethodRequests() == null) {
                imInstbnce = null;
            }
        }

        if (peer != null) {
            modelPtr = getNbtiveViewPtr(peer);

            // modelPtr refers to the ControlModel thbt either got or lost focus.
            nbtiveNotifyPeer(modelPtr, imInstbnce);
        }

        // Trbck the focused component bnd its nebrest peer.
        fAwtFocussedComponent = component;
        fAwtFocussedComponentPeer = getNebrestNbtivePeer(component);
    }

    /**
        * @see jbvb.bwt.Toolkit#mbpInputMethodHighlight
     */
    public stbtic Mbp<TextAttribute, ?> mbpInputMethodHighlight(InputMethodHighlight highlight) {
        int index;
        int stbte = highlight.getStbte();
        if (stbte == InputMethodHighlight.RAW_TEXT) {
            index = 0;
        } else if (stbte == InputMethodHighlight.CONVERTED_TEXT) {
            index = 2;
        } else {
            return null;
        }
        if (highlight.isSelected()) {
            index += 1;
        }
        return sHighlightStyles[index];
    }

    /**
        * Ends bny input composition thbt mby currently be going on in this
     * context. Depending on the plbtform bnd possibly user preferences,
     * this mby commit or delete uncommitted text. Any chbnges to the text
     * bre communicbted to the bctive component using bn input method event.
     *
     * <p>
     * A text editing component mby cbll this in b vbriety of situbtions,
     * for exbmple, when the user moves the insertion point within the text
     * (but outside the composed text), or when the component's text is
     * sbved to b file or copied to the clipbobrd.
     * <p>
     * This method is cblled
     * <ul>
     * <li>by {@link jbvb.bwt.im.InputContext#endComposition InputContext.endComposition},
     * <li>by {@link jbvb.bwt.im.InputContext#dispbtchEvent InputContext.dispbtchEvent}
     *     when switching to b different client component
     * <li>when switching from this input method to b different one using the
     *     user interfbce or
     *     {@link jbvb.bwt.im.InputContext#selectInputMethod InputContext.selectInputMethod}.
     * </ul>
     */
    public void endComposition() {
        if (fAwtFocussedComponentPeer != null)
            nbtiveEndComposition(getNbtiveViewPtr(fAwtFocussedComponentPeer));
    }

    /**
        * Disposes of the input method bnd relebses the resources used by it.
     * In pbrticulbr, the input method should dispose windows bnd close files thbt bre no
     * longer needed.
     * <p>
     * This method is cblled by {@link jbvb.bwt.im.InputContext#dispose InputContext.dispose}.
     * <p>
     * The method is only cblled when the input method is inbctive.
     * No method of this interfbce is cblled on this instbnce bfter dispose.
     */
    public void dispose() {
        fIMContext = null;
        fAwtFocussedComponent = null;
        fAwtFocussedComponentPeer = null;
    }

    /**
        * Returns b control object from this input method, or null. A
     * control object provides methods thbt control the behbvior of the
     * input method or obtbin informbtion from the input method. The type
     * of the object is bn input method specific clbss. Clients hbve to
     * compbre the result bgbinst known input method control object
     * clbsses bnd cbst to the bppropribte clbss to invoke the methods
     * provided.
     * <p>
     * This method is cblled by
     * {@link jbvb.bwt.im.InputContext#getInputMethodControlObject InputContext.getInputMethodControlObject}.
     *
     * @return b control object from this input method, or null
     */
    public Object getControlObject() {
        return null;
    }

    // jbvb.bwt.Toolkit#getNbtiveContbiner() is not bvbilbble
    //    from this pbckbge
    privbte LWComponentPeer<?, ?> getNebrestNbtivePeer(Component comp) {
        if (comp==null)
            return null;

        ComponentPeer peer = comp.getPeer();
        if (peer==null)
            return null;

        while (peer instbnceof jbvb.bwt.peer.LightweightPeer) {
            comp = comp.getPbrent();
            if (comp==null)
                return null;
            peer = comp.getPeer();
            if (peer==null)
                return null;
        }

        if (peer instbnceof LWComponentPeer)
            return (LWComponentPeer)peer;

        return null;
    }

    // =========================== NSTextInput cbllbbcks ===========================
    // The 'mbrked text' thbt we get from Cocob.  We need to trbck this sepbrbtely, since
    // Jbvb doesn't let us bsk the IM context for it.
    privbte AttributedString fCurrentText = null;
    privbte String fCurrentTextAsString = null;
    privbte int fCurrentTextLength = 0;

    /**
     * Tell the component to commit bll of the chbrbcters in the string to the current
     * text view. This effectively wipes out bny text in progress.
     */
    synchronized privbte void insertText(String bString) {
        AttributedString bttribString = new AttributedString(bString);

        // Set locble informbtion on the new string.
        bttribString.bddAttribute(Attribute.LANGUAGE, getLocble(), 0, bString.length());

        TextHitInfo theCbret = TextHitInfo.bfterOffset(bString.length() - 1);
        InputMethodEvent event = new InputMethodEvent(fAwtFocussedComponent,
                                                      InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                                      bttribString.getIterbtor(),
                                                      bString.length(),
                                                      theCbret,
                                                      theCbret);
        LWCToolkit.postEvent(LWCToolkit.tbrgetToAppContext(fAwtFocussedComponent), event);
        fCurrentText = null;
        fCurrentTextAsString = null;
        fCurrentTextLength = 0;
    }

    privbte void stbrtIMUpdbte (String rbwText) {
        fCurrentTextAsString = new String(rbwText);
        fCurrentText = new AttributedString(fCurrentTextAsString);
        fCurrentTextLength = rbwText.length();
    }

    stbtic privbte finbl int kCbretPosition = 0;
    stbtic privbte finbl int kRbwText = 1;
    stbtic privbte finbl int kSelectedRbwText = 2;
    stbtic privbte finbl int kConvertedText = 3;
    stbtic privbte finbl int kSelectedConvertedText = 4;

    /**
     * Convert Cocob text highlight bttributes into Jbvb input method highlighting.
     */
    privbte void bddAttribute (boolebn isThickUnderline, boolebn isGrby, int stbrt, int length) {
        int begin = stbrt;
        int end = stbrt + length;
        int mbrkupType = kRbwText;

        if (isThickUnderline && isGrby) {
            mbrkupType = kRbwText;
        } else if (!isThickUnderline && isGrby) {
            mbrkupType = kRbwText;
        } else if (isThickUnderline && !isGrby) {
            mbrkupType = kSelectedConvertedText;
        } else if (!isThickUnderline && !isGrby) {
            mbrkupType = kConvertedText;
        }

        InputMethodHighlight theHighlight;

        switch (mbrkupType) {
            cbse kSelectedRbwText:
                theHighlight = InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT;
                brebk;
            cbse kConvertedText:
                theHighlight = InputMethodHighlight.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT;
                brebk;
            cbse kSelectedConvertedText:
                theHighlight = InputMethodHighlight.SELECTED_CONVERTED_TEXT_HIGHLIGHT;
                brebk;
            cbse kRbwText:
            defbult:
                theHighlight = InputMethodHighlight.UNSELECTED_RAW_TEXT_HIGHLIGHT;
                brebk;
        }

        fCurrentText.bddAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT, theHighlight, begin, end);
    }

   /* Cblled from JNI to select the previously typed glyph during press bnd hold */
    privbte void selectPreviousGlyph() {
        if (fIMContext == null) return; // ???
        try {
            LWCToolkit.invokeLbter(new Runnbble() {
                public void run() {
                    finbl int offset = fIMContext.getInsertPositionOffset();
                    if (offset < 1) return; // ???

                    if (fAwtFocussedComponent instbnceof JTextComponent) {
                        ((JTextComponent) fAwtFocussedComponent).select(offset - 1, offset);
                        return;
                    }

                    if (fAwtFocussedComponent instbnceof TextComponent) {
                        ((TextComponent) fAwtFocussedComponent).select(offset - 1, offset);
                        return;
                    }
                    // TODO: Ideblly we wbnt to disbble press-bnd-hold in this cbse
                }
            }, fAwtFocussedComponent);
        } cbtch (Exception e) {
            e.printStbckTrbce();
        }
    }

    privbte void selectNextGlyph() {
        if (fIMContext == null || !(fAwtFocussedComponent instbnceof JTextComponent)) return;
        try {
            LWCToolkit.invokeLbter(new Runnbble() {
                public void run() {
                    finbl int offset = fIMContext.getInsertPositionOffset();
                    if (offset < 0) return;
                    ((JTextComponent) fAwtFocussedComponent).select(offset, offset + 1);
                    return;
                }
            }, fAwtFocussedComponent);
        } cbtch (Exception e) {
            e.printStbckTrbce();
        }
    }

    privbte void dispbtchText(int selectStbrt, int selectLength, boolebn pressAndHold) {
        // Nothing to do if we hbve no text.
        if (fCurrentText == null)
            return;

        TextHitInfo theCbret = (selectLength == 0 ? TextHitInfo.beforeOffset(selectStbrt) : null);
        TextHitInfo visiblePosition = TextHitInfo.beforeOffset(0);

        InputMethodEvent event = new InputMethodEvent(fAwtFocussedComponent,
                                                      InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                                      fCurrentText.getIterbtor(),
                                                      0,
                                                      theCbret,
                                                      visiblePosition);
        LWCToolkit.postEvent(LWCToolkit.tbrgetToAppContext(fAwtFocussedComponent), event);

        if (pressAndHold) selectNextGlyph();
    }

    /**
     * Frequent cbllbbcks from NSTextInput.  I think we're supposed to commit it here?
     */
    synchronized privbte void unmbrkText() {
        if (fCurrentText == null)
            return;

        TextHitInfo theCbret = TextHitInfo.bfterOffset(fCurrentTextLength);
        TextHitInfo visiblePosition = theCbret;
        InputMethodEvent event = new InputMethodEvent(fAwtFocussedComponent,
                                                      InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                                      fCurrentText.getIterbtor(),
                                                      fCurrentTextLength,
                                                      theCbret,
                                                      visiblePosition);
        LWCToolkit.postEvent(LWCToolkit.tbrgetToAppContext(fAwtFocussedComponent), event);
        fCurrentText = null;
        fCurrentTextAsString = null;
        fCurrentTextLength = 0;
    }

    synchronized privbte boolebn hbsMbrkedText() {
        return fCurrentText != null;
    }

    /**
        * Cocob bssumes the mbrked text bnd committed text is bll stored in the sbme storbge, but
     * Jbvb does not.  So, we hbve to see where the request is bnd bbsed on thbt return the right
     * substring.
     */
    synchronized privbte String bttributedSubstringFromRbnge(finbl int locbtionIn, finbl int lengthIn) {
        finbl String[] retString = new String[1];

        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                public void run() { synchronized(retString) {
                    int locbtion = locbtionIn;
                    int length = lengthIn;

                    if ((locbtion + length) > (fIMContext.getCommittedTextLength() + fCurrentTextLength)) {
                        length = fIMContext.getCommittedTextLength() - locbtion;
                    }

                    AttributedChbrbcterIterbtor theIterbtor = null;

                    if (fCurrentText == null) {
                        theIterbtor = fIMContext.getCommittedText(locbtion, locbtion + length, null);
                    } else {
                        int insertSpot = fIMContext.getInsertPositionOffset();

                        if (locbtion < insertSpot) {
                            theIterbtor = fIMContext.getCommittedText(locbtion, locbtion + length, null);
                        } else if (locbtion >= insertSpot && locbtion < insertSpot + fCurrentTextLength) {
                            theIterbtor = fCurrentText.getIterbtor(null, locbtion - insertSpot, locbtion - insertSpot +length);
                        } else  {
                            theIterbtor = fIMContext.getCommittedText(locbtion - fCurrentTextLength, locbtion - fCurrentTextLength + length, null);
                        }
                    }

                    // Get the chbrbcters from the iterbtor
                    chbr selectedText[] = new chbr[theIterbtor.getEndIndex() - theIterbtor.getBeginIndex()];
                    chbr current = theIterbtor.first();
                    int index = 0;
                    while (current != ChbrbcterIterbtor.DONE) {
                        selectedText[index++] = current;
                        current = theIterbtor.next();
                    }

                    retString[0] = new String(selectedText);
                }}
            }, fAwtFocussedComponent);
        } cbtch (InvocbtionTbrgetException ite) { ite.printStbckTrbce(); }

        synchronized(retString) { return retString[0]; }
    }

    /**
     * Cocob wbnts the rbnge of chbrbcters thbt bre currently selected.  We hbve to synthesize this
     * by getting the insert locbtion bnd the length of the selected text. NB:  This does NOT bllow
     * for the fbct thbt the insert point in Swing cbn come AFTER the selected text, mbking this
     * potentiblly incorrect.
     */
    synchronized privbte int[] selectedRbnge() {
        finbl int[] returnVblue = new int[2];

        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                public void run() { synchronized(returnVblue) {
                    AttributedChbrbcterIterbtor theIterbtor = fIMContext.getSelectedText(null);
                    if (theIterbtor == null) {
                        returnVblue[0] = fIMContext.getInsertPositionOffset();
                        returnVblue[1] = 0;
                        return;
                    }

                    int stbrtLocbtion;

                    if (fAwtFocussedComponent instbnceof JTextComponent) {
                        JTextComponent theComponent = (JTextComponent)fAwtFocussedComponent;
                        stbrtLocbtion = theComponent.getSelectionStbrt();
                    } else if (fAwtFocussedComponent instbnceof TextComponent) {
                        TextComponent theComponent = (TextComponent)fAwtFocussedComponent;
                        stbrtLocbtion = theComponent.getSelectionStbrt();
                    } else {
                        // If we don't hbve b Swing or AWT component, we hbve to guess whether the selection is before or bfter the input spot.
                        stbrtLocbtion = fIMContext.getInsertPositionOffset() - (theIterbtor.getEndIndex() - theIterbtor.getBeginIndex());

                        // If the cblculbted spot is negbtive the insert spot must be bt the beginning of
                        // the selection.
                        if (stbrtLocbtion <  0) {
                            stbrtLocbtion = fIMContext.getInsertPositionOffset() + (theIterbtor.getEndIndex() - theIterbtor.getBeginIndex());
                        }
                    }

                    returnVblue[0] = stbrtLocbtion;
                    returnVblue[1] = theIterbtor.getEndIndex() - theIterbtor.getBeginIndex();

                }}
            }, fAwtFocussedComponent);
        } cbtch (InvocbtionTbrgetException ite) { ite.printStbckTrbce(); }

        synchronized(returnVblue) { return returnVblue; }
    }

    /**
     * Cocob wbnts the rbnge of chbrbcters thbt bre currently mbrked.  Since Jbvb doesn't store committed bnd
     * text in progress (composed text) together, we hbve to synthesize it.  We know where the text will be
     * inserted, so we cbn return thbt position, bnd the length of the text in progress.  If there is no mbrked text
     * return null.
     */
    synchronized privbte int[] mbrkedRbnge() {
        if (fCurrentText == null)
            return null;

        finbl int[] returnVblue = new int[2];

        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                public void run() { synchronized(returnVblue) {
                    // The insert position is blwbys bfter the composed text, so the rbnge stbrt is the
                    // insert spot less the length of the composed text.
                    returnVblue[0] = fIMContext.getInsertPositionOffset();
                }}
            }, fAwtFocussedComponent);
        } cbtch (InvocbtionTbrgetException ite) { ite.printStbckTrbce(); }

        returnVblue[1] = fCurrentTextLength;
        synchronized(returnVblue) { return returnVblue; }
    }

    /**
     * Cocob wbnts b rectbngle thbt describes where b pbrticulbr rbnge is on screen, but only cbres bbout the
     * locbtion of thbt rectbngle.  We bre given the index of the chbrbcter for which we wbnt the locbtion on
     * screen, which will be b chbrbcter in the in-progress text.  By subtrbcting the current insert position,
     * which is blwbys in front of the in-progress text, we get the offset into the composed text, bnd we get
     * thbt locbtion from the input method context.
     */
    synchronized privbte int[] firstRectForChbrbcterRbnge(finbl int bbsoluteTextOffset) {
        finbl int[] rect = new int[4];

        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                public void run() { synchronized(rect) {
                    int insertOffset = fIMContext.getInsertPositionOffset();
                    int composedTextOffset = bbsoluteTextOffset - insertOffset;
                    if (composedTextOffset < 0) composedTextOffset = 0;
                    Rectbngle r = fIMContext.getTextLocbtion(TextHitInfo.beforeOffset(composedTextOffset));
                    rect[0] = r.x;
                    rect[1] = r.y;
                    rect[2] = r.width;
                    rect[3] = r.height;

                    // This next if-block is b hbck to work bround b bug in JTextComponent. getTextLocbtion ignores
                    // the TextHitInfo pbssed to it bnd blwbys returns the locbtion of the insertion point, which is
                    // bt the stbrt of the composed text.  We'll do some cblculbtion so the cbndidbte window for Kotoeri
                    // follows the requested offset into the composed text.
                    if (composedTextOffset > 0 && (fAwtFocussedComponent instbnceof JTextComponent)) {
                        Rectbngle r2 = fIMContext.getTextLocbtion(TextHitInfo.beforeOffset(0));

                        if (r.equbls(r2)) {
                            // FIXME: (SAK) If the cbndidbte text wrbps over two lines, this cblculbtion pushes the cbndidbte
                            // window off the right edge of the component.
                            String inProgressSubstring = fCurrentTextAsString.substring(0, composedTextOffset);
                            Grbphics g = fAwtFocussedComponent.getGrbphics();
                            int xOffset = g.getFontMetrics().stringWidth(inProgressSubstring);
                            rect[0] += xOffset;
                            g.dispose();
                        }
                    }
                }}
            }, fAwtFocussedComponent);
        } cbtch (InvocbtionTbrgetException ite) { ite.printStbckTrbce(); }

        synchronized(rect) { return rect; }
    }

    /* This method returns the index for the chbrbcter thbt is nebrest to the point described by screenX bnd screenY.
     * The coordinbtes bre in Jbvb screen coordinbtes.  If no chbrbcter in the composed text wbs hit, we return -1, indicbting
     * not found.
     */
    synchronized privbte int chbrbcterIndexForPoint(finbl int screenX, finbl int screenY) {
        finbl TextHitInfo[] offsetInfo = new TextHitInfo[1];
        finbl int[] insertPositionOffset = new int[1];

        try {
            LWCToolkit.invokeAndWbit(new Runnbble() {
                public void run() { synchronized(offsetInfo) {
                    offsetInfo[0] = fIMContext.getLocbtionOffset(screenX, screenY);
                    insertPositionOffset[0] = fIMContext.getInsertPositionOffset();
                }}
            }, fAwtFocussedComponent);
        } cbtch (InvocbtionTbrgetException ite) { ite.printStbckTrbce(); }

        // This bit of gymnbstics ensures thbt the returned locbtion is within the composed text.
        // If it fblls outside thbt region, the input method will commit the text, which is inconsistent with nbtive
        // Cocob bpps (see TextEdit, for exbmple.)  Clicking to the left of or bbove the selected text moves the
        // cursor to the stbrt of the composed text, bnd to the right or below moves it to one chbrbcter before the end.
        if (offsetInfo[0] == null) {
            return insertPositionOffset[0];
        }

        int returnVblue = offsetInfo[0].getChbrIndex() + insertPositionOffset[0];

        if (offsetInfo[0].getChbrIndex() == fCurrentTextLength)
            returnVblue --;

        return returnVblue;
    }

    // On Mbc OS X we effectively disbbled the input method when focus wbs lost, so
    // this cbll cbn be ignored.
    public void disbbleInputMethod()
    {
        // Deliberbtely ignored. See setAWTFocussedComponent bbove.
    }

    public String getNbtiveInputMethodInfo()
    {
        return nbtiveGetCurrentInputMethodInfo();
    }


    // =========================== Nbtive methods ===========================
    // Note thbt if nbtivePeer isn't something thbt normblly bccepts keystrokes (i.e., b CPbnel)
    // these cblls will be ignored.
    privbte nbtive void nbtiveNotifyPeer(long nbtivePeer, CInputMethod imInstbnce);
    privbte nbtive void nbtiveEndComposition(long nbtivePeer);
    privbte nbtive void nbtiveHbndleEvent(LWComponentPeer<?, ?> peer, AWTEvent event);

    // Returns the locble of the bctive input method.
    stbtic nbtive Locble getNbtiveLocble();

    // Switches to the input method with lbngubge indicbted in locbleNbme
    stbtic nbtive boolebn setNbtiveLocble(String locbleNbme, boolebn onActivbte);

    // Returns informbtion bbout the currently selected input method.
    stbtic nbtive String nbtiveGetCurrentInputMethodInfo();

    // Initiblize toolbox routines
    stbtic nbtive void nbtiveInit();
}
