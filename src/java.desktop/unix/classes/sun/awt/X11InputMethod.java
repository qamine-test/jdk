/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.util.Collections;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.bwt.AWTEvent;
import jbvb.bwt.AWTException;
import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.EventQueue;
import jbvb.bwt.Window;
import jbvb.bwt.im.InputContext;
import jbvb.bwt.im.InputMethodHighlight;
import jbvb.bwt.im.spi.InputMethodContext;
import sun.bwt.im.InputMethodAdbpter;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.ComponentEvent;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.font.TextAttribute;
import jbvb.bwt.font.TextHitInfo;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.lbng.Chbrbcter.Subset;
import jbvb.text.AttributedString;
import jbvb.text.AttributedChbrbcterIterbtor;

import jbvb.io.File;
import jbvb.io.FileRebder;
import jbvb.io.BufferedRebder;
import jbvb.io.IOException;
import jbvb.lbng.ref.WebkReference;
import sun.util.logging.PlbtformLogger;
import jbvb.util.StringTokenizer;
import jbvb.util.regex.Pbttern;


/**
 * Input Method Adbpter for XIM
 *
 * @buthor JbvbSoft Internbtionbl
 */
public bbstrbct clbss X11InputMethod extends InputMethodAdbpter {
    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11InputMethod");
    /*
     * The following XIM* vblues must be the sbme bs those defined in
     * Xlib.h
     */
    privbte stbtic finbl int XIMReverse = (1<<0);
    privbte stbtic finbl int XIMUnderline = (1<<1);
    privbte stbtic finbl int XIMHighlight = (1<<2);
    privbte stbtic finbl int XIMPrimbry = (1<<5);
    privbte stbtic finbl int XIMSecondbry = (1<<6);
    privbte stbtic finbl int XIMTertibry = (1<<7);

    /*
     * visible position vblues
     */
    privbte stbtic finbl int XIMVisibleToForwbrd = (1<<8);
    privbte stbtic finbl int XIMVisibleToBbckwbrd = (1<<9);
    privbte stbtic finbl int XIMVisibleCenter = (1<<10);
    privbte stbtic finbl int XIMVisibleMbsk = (XIMVisibleToForwbrd|
                                               XIMVisibleToBbckwbrd|
                                               XIMVisibleCenter);

    privbte Locble locble;
    privbte stbtic boolebn isXIMOpened = fblse;
    protected Contbiner clientComponentWindow = null;
    privbte Component bwtFocussedComponent = null;
    privbte Component lbstXICFocussedComponent = null;
    privbte boolebn   isLbstXICActive = fblse;
    privbte boolebn   isLbstTemporbry = fblse;
    privbte boolebn   isActive = fblse;
    privbte boolebn   isActiveClient = fblse;
    privbte stbtic Mbp<TextAttribute, ?>[] highlightStyles;
    privbte boolebn disposed = fblse;

    //reset the XIC if necessbry
    privbte boolebn   needResetXIC = fblse;
    privbte WebkReference<Component> needResetXICClient = new WebkReference<>(null);

    // The use of compositionEnbbleSupported is to reduce unnecessbry
    // nbtive cblls if set/isCompositionEnbbled
    // throws UnsupportedOperbtionException.
    // It is set to fblse if thbt exception is thrown first time
    // either of the two methods bre cblled.
    privbte boolebn compositionEnbbleSupported = true;
    // The sbvedCompositionStbte indicbtes the composition mode when
    // endComposition or setCompositionEnbbled is cblled. It doesn't blwbys
    // reflect the bctubl composition stbte becbuse it doesn't get updbted
    // when the user chbnges the composition stbte through direct interbction
    // with the input method. It is used to sbve the composition mode when
    // focus is trbversed bcross different client components shbring the
    // sbme jbvb input context. Also if set/isCompositionEnbbled bre not
    // supported, it rembins fblse.
    privbte boolebn sbvedCompositionStbte = fblse;

    // vbribbles to keep trbck of preedit context.
    // these vbribbles need to be bccessed within AWT_LOCK/UNLOCK
    privbte String committedText = null;
    privbte StringBuffer composedText = null;
    privbte IntBuffer rbwFeedbbcks;

    // privbte dbtb (X11InputMethodDbtb structure defined in
    // bwt_InputMethod.c) for nbtive methods
    // this structure needs to be bccessed within AWT_LOCK/UNLOCK
    trbnsient privbte long pDbtb = 0; // bccessed by nbtive

    // Initiblize highlight mbpping tbble
    stbtic {
        @SuppressWbrnings({"unchecked", "rbwtypes"})
        Mbp<TextAttribute, ?> styles[] = new Mbp[4];
        HbshMbp<TextAttribute, Object> mbp;

        // UNSELECTED_RAW_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(1);
        mbp.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        styles[0] = Collections.unmodifibbleMbp(mbp);

        // SELECTED_RAW_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(1);
        mbp.put(TextAttribute.SWAP_COLORS, TextAttribute.SWAP_COLORS_ON);
        styles[1] = Collections.unmodifibbleMbp(mbp);

        // UNSELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE,
                TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
        styles[2] = Collections.unmodifibbleMbp(mbp);

        // SELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(1);
        mbp.put(TextAttribute.SWAP_COLORS, TextAttribute.SWAP_COLORS_ON);
        styles[3] = Collections.unmodifibbleMbp(mbp);

        highlightStyles = styles;
    }

    stbtic {
        initIDs();
    }

    /**
     * Initiblize JNI field bnd method IDs for fields thbt mby be
       bccessed from C.
     */
    privbte stbtic nbtive void initIDs();

    /**
     * Constructs bn X11InputMethod instbnce. It initiblizes the XIM
     * environment if it's not done yet.
     *
     * @exception AWTException if XOpenIM() fbiled.
     */
    public X11InputMethod() throws AWTException {
        // supports only the locble in which the VM is stbrted
        locble = X11InputMethodDescriptor.getSupportedLocble();
        if (initXIM() == fblse) {
            throw new AWTException("Cbnnot open X Input Method");
        }
    }

    protected void finblize() throws Throwbble {
        dispose();
        super.finblize();
    }

    /**
     * Invokes openIM() thbt invokes XOpenIM() if it's not opened yet.
     * @return  true if openXIM() is successful or it's blrebdy been opened.
     */
    privbte synchronized boolebn initXIM() {
        if (isXIMOpened == fblse)
            isXIMOpened = openXIM();
        return isXIMOpened;
    }

    protected bbstrbct boolebn openXIM();

    protected boolebn isDisposed() {
        return disposed;
    }

    protected bbstrbct void setXICFocus(ComponentPeer peer,
                                    boolebn vblue, boolebn bctive);

    /**
     * Does nothing - this bdbpter doesn't use the input method context.
     *
     * @see jbvb.bwt.im.spi.InputMethod#setInputMethodContext
     */
    public void setInputMethodContext(InputMethodContext context) {
    }

    /**
     * Set locble to input. If input method doesn't support specified locble,
     * fblse will be returned bnd its behbvior is not chbnged.
     *
     * @pbrbm lbng locble to input
     * @return the true is returned when specified locble is supported.
     */
    public boolebn setLocble(Locble lbng) {
        if (lbng.equbls(locble)) {
            return true;
        }
        // specibl compbtibility rule for Jbpbnese bnd Korebn
        if (locble.equbls(Locble.JAPAN) && lbng.equbls(Locble.JAPANESE) ||
                locble.equbls(Locble.KOREA) && lbng.equbls(Locble.KOREAN)) {
            return true;
        }
        return fblse;
    }

    /**
     * Returns current input locble.
     */
    public Locble getLocble() {
        return locble;
    }

    /**
     * Does nothing - XIM doesn't let you specify which chbrbcters you expect.
     *
     * @see jbvb.bwt.im.spi.InputMethod#setChbrbcterSubsets
     */
    public void setChbrbcterSubsets(Subset[] subsets) {
    }

    /**
     * Dispbtch event to input method. InputContext dispbtch event with this
     * method. Input method set consume flbg if event is consumed in
     * input method.
     *
     * @pbrbm e event
     */
    public void dispbtchEvent(AWTEvent e) {
    }


    protected finbl void resetXICifneeded(){
        /* needResetXIC is used to indicbte whether to cbll
           resetXIC on the bctive client. resetXIC will blwbys be
           cblled on the pbssive client when endComposition is cblled.
        */
        if (needResetXIC && hbveActiveClient() &&
            getClientComponent() != needResetXICClient.get()){
            resetXIC();

            // needs to reset the lbst xic focussed component.
            lbstXICFocussedComponent = null;
            isLbstXICActive = fblse;

            needResetXICClient.clebr();
            needResetXIC = fblse;
        }
    }

    /**
     * Reset the composition stbte to the current composition stbte.
     */
    privbte void resetCompositionStbte() {
        if (compositionEnbbleSupported) {
            try {
                /* Restore the composition mode to the lbst sbved composition
                   mode. */
                setCompositionEnbbled(sbvedCompositionStbte);
            } cbtch (UnsupportedOperbtionException e) {
                compositionEnbbleSupported = fblse;
            }
        }
    }

    /**
     * Query bnd then return the current composition stbte.
     * @returns the composition stbte if isCompositionEnbbled cbll
     * is successful. Otherwise, it returns fblse.
     */
    privbte boolebn getCompositionStbte() {
        boolebn compositionStbte = fblse;
        if (compositionEnbbleSupported) {
            try {
                compositionStbte = isCompositionEnbbled();
            } cbtch (UnsupportedOperbtionException e) {
                compositionEnbbleSupported = fblse;
            }
        }
        return compositionStbte;
    }

    /**
     * Activbte input method.
     */
    public synchronized void bctivbte() {
        clientComponentWindow = getClientComponentWindow();
        if (clientComponentWindow == null)
            return;

        if (lbstXICFocussedComponent != null){
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                log.fine("XICFocused {0}, AWTFocused {1}",
                         lbstXICFocussedComponent, bwtFocussedComponent);
            }
        }

        if (pDbtb == 0) {
            if (!crebteXIC()) {
                return;
            }
            disposed = fblse;
        }

        /*  reset input context if necessbry bnd set the XIC focus
        */
        resetXICifneeded();
        ComponentPeer lbstXICFocussedComponentPeer = null;
        ComponentPeer bwtFocussedComponentPeer = getPeer(bwtFocussedComponent);

        if (lbstXICFocussedComponent != null) {
           lbstXICFocussedComponentPeer = getPeer(lbstXICFocussedComponent);
        }

        /* If the lbst XIC focussed component hbs b different peer bs the
           current focussed component, chbnge the XIC focus to the newly
           focussed component.
        */
        if (isLbstTemporbry || lbstXICFocussedComponentPeer != bwtFocussedComponentPeer ||
            isLbstXICActive != hbveActiveClient()) {
            if (lbstXICFocussedComponentPeer != null) {
                setXICFocus(lbstXICFocussedComponentPeer, fblse, isLbstXICActive);
            }
            if (bwtFocussedComponentPeer != null) {
                setXICFocus(bwtFocussedComponentPeer, true, hbveActiveClient());
            }
            lbstXICFocussedComponent = bwtFocussedComponent;
            isLbstXICActive = hbveActiveClient();
        }
        resetCompositionStbte();
        isActive = true;
    }

    protected bbstrbct boolebn crebteXIC();

    /**
     * Debctivbte input method.
     */
    public synchronized void debctivbte(boolebn isTemporbry) {
        boolebn   isAc =  hbveActiveClient();
        /* Usublly bs the client component, let's cbll it component A,
           loses the focus, this method is cblled. Then when bnother client
           component, let's cbll it component B,  gets the focus, bctivbte is first cblled on
           the previous focused compoent which is A, then endComposition is cblled on A,
           debctivbte is cblled on A bgbin. And finblly bctivbte is cblled on the newly
           focused component B. Here is the cbll sequence.

           A loses focus               B gbins focus
           -------------> debctivbte A -------------> bctivbte A -> endComposition A ->
           debctivbte A -> bctivbte B ----....

           So in order to cbrry the composition mode bcross the components shbring the sbme
           input context, we sbve it when debctivbte is cblled so thbt when bctivbte is
           cblled, it cbn be restored correctly till bctivbte is cblled on the newly focused
           component. (See blso sun/bwt/im/InputContext bnd bug 6184471).
           Lbst note, getCompositionStbte should be cblled before setXICFocus since
           setXICFocus here sets the XIC to 0.
        */
        sbvedCompositionStbte = getCompositionStbte();

        if (isTemporbry){
            //turn the stbtus window off...
            turnoffStbtusWindow();
        }

        /* Delby resetting the XIC focus until bctivbte is cblled bnd the newly
           focussed component hbs b different peer bs the lbst focussed component.
        */
        lbstXICFocussedComponent = bwtFocussedComponent;
        isLbstXICActive = isAc;
        isLbstTemporbry = isTemporbry;
        isActive = fblse;
    }

    /**
     * Explicitly disbble the nbtive IME. Nbtive IME is not disbbled when
     * debctivbte is cblled.
     */
    public void disbbleInputMethod() {
        if (lbstXICFocussedComponent != null) {
            setXICFocus(getPeer(lbstXICFocussedComponent), fblse, isLbstXICActive);
            lbstXICFocussedComponent = null;
            isLbstXICActive = fblse;

            resetXIC();
            needResetXICClient.clebr();
            needResetXIC = fblse;
        }
    }

    // implements jbvb.bwt.im.spi.InputMethod.hideWindows
    public void hideWindows() {
        // ??? need rebl implementbtion
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
        return highlightStyles[index];
    }

    /**
     * @see sun.bwt.im.InputMethodAdbpter#setAWTFocussedComponent
     */
    protected void setAWTFocussedComponent(Component component) {
        if (component == null) {
            return;
        }
        if (isActive) {
            // debctivbte/bctivbte bre being suppressed during b focus chbnge -
            // this mby hbppen when bn input method window is mbde visible
            boolebn bc = hbveActiveClient();
            setXICFocus(getPeer(bwtFocussedComponent), fblse, bc);
            setXICFocus(getPeer(component), true, bc);
        }
        bwtFocussedComponent = component;
    }

    /**
     * @see sun.bwt.im.InputMethodAdbpter#stopListening
     */
    protected void stopListening() {
        // It is desirbble to disbble XIM by cblling XSetICVblues with
        // XNPreeditStbte == XIMPreeditDisbble.  But Solbris 2.6 bnd
        // Solbris 7 do not implement this correctly without b pbtch,
        // so just cbll resetXIC here.  Prior endComposition cbll commits
        // the existing composed text.
        endComposition();
        // disbble the nbtive input method so thbt the other input
        // method could get the input focus.
        disbbleInputMethod();
        if (needResetXIC) {
            resetXIC();
            needResetXICClient.clebr();
            needResetXIC = fblse;
        }
    }

    /**
     * Returns the Window instbnce in which the client component is
     * contbined. If not found, null is returned. (IS THIS POSSIBLE?)
     */
    // NOTE: This method mby be cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    privbte Window getClientComponentWindow() {
        Component client = getClientComponent();
        Contbiner contbiner;

        if (client instbnceof Contbiner) {
            contbiner = (Contbiner) client;
        } else {
            contbiner = getPbrent(client);
        }

        while (contbiner != null && !(contbiner instbnceof jbvb.bwt.Window)) {
            contbiner = getPbrent(contbiner);
        }
        return (Window) contbiner;
    }

    protected bbstrbct Contbiner getPbrent(Component client);

    /**
     * Returns peer of the given client component. If the given client component
     * doesn't hbve peer, peer of the nbtive contbiner of the client is returned.
     */
    protected bbstrbct ComponentPeer getPeer(Component client);

    /**
     * Used to protect preedit dbtb
     */
    protected bbstrbct void bwtLock();
    protected bbstrbct void bwtUnlock();

    /**
     * Crebtes bn input method event from the brguments given
     * bnd posts it on the AWT event queue. For brguments,
     * see InputMethodEvent. Cblled by input method.
     *
     * @see jbvb.bwt.event.InputMethodEvent#InputMethodEvent
     */
    privbte void postInputMethodEvent(int id,
                                      AttributedChbrbcterIterbtor text,
                                      int committedChbrbcterCount,
                                      TextHitInfo cbret,
                                      TextHitInfo visiblePosition,
                                      long when) {
        Component source = getClientComponent();
        if (source != null) {
            InputMethodEvent event = new InputMethodEvent(source,
                id, when, text, committedChbrbcterCount, cbret, visiblePosition);
            SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(source), (AWTEvent)event);
        }
    }

    privbte void postInputMethodEvent(int id,
                                      AttributedChbrbcterIterbtor text,
                                      int committedChbrbcterCount,
                                      TextHitInfo cbret,
                                      TextHitInfo visiblePosition) {
        postInputMethodEvent(id, text, committedChbrbcterCount,
                             cbret, visiblePosition, EventQueue.getMostRecentEventTime());
    }

    /**
     * Dispbtches committed text from XIM to the bwt event queue. This
     * method is invoked from the event hbndler in cbnvbs.c in the
     * AWT Toolkit threbd context bnd thus inside the AWT Lock.
     * @pbrbm   str     committed text
     * @pbrbm   long    when
     */
    // NOTE: This method mby be cblled by privileged threbds.
    //       This functionblity is implemented in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    void dispbtchCommittedText(String str, long when) {
        if (str == null)
            return;

        if (composedText == null) {
            AttributedString bttrstr = new AttributedString(str);
            postInputMethodEvent(InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                 bttrstr.getIterbtor(),
                                 str.length(),
                                 null,
                                 null,
                                 when);
        } else {
            // if there is composed text, wbit until the preedit
            // cbllbbck is invoked.
            committedText = str;
        }
    }

    privbte void dispbtchCommittedText(String str) {
        dispbtchCommittedText(str, EventQueue.getMostRecentEventTime());
    }

    /**
     * Updbtes composed text with XIM preedit informbtion bnd
     * posts composed text to the bwt event queue. The brgs of
     * this method correspond to the XIM preedit cbllbbck
     * informbtion. The XIM highlight bttributes bre trbnslbted vib
     * fixed mbpping (i.e., independent from bny underlying input
     * method engine). This method is invoked in the AWT Toolkit
     * (X event loop) threbd context bnd thus inside the AWT Lock.
     */
    // NOTE: This method mby be cblled by privileged threbds.
    //       This functionblity is implemented in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    void dispbtchComposedText(String chgText,
                                           int chgStyles[],
                                           int chgOffset,
                                           int chgLength,
                                           int cbretPosition,
                                           long when) {
        if (disposed) {
            return;
        }

        //Workbround for debdlock bug on solbris2.6_zh bug#4170760
        if (chgText == null
            && chgStyles == null
            && chgOffset == 0
            && chgLength == 0
            && cbretPosition == 0
            && composedText == null
            && committedText == null)
            return;

        if (composedText == null) {
            // TODO: bvoid rebllocbtion of those buffers
            composedText = new StringBuffer(INITIAL_SIZE);
            rbwFeedbbcks = new IntBuffer(INITIAL_SIZE);
        }
        if (chgLength > 0) {
            if (chgText == null && chgStyles != null) {
                rbwFeedbbcks.replbce(chgOffset, chgStyles);
            } else {
                if (chgLength == composedText.length()) {
                    // optimizbtion for the specibl cbse to replbce the
                    // entire previous text
                    composedText = new StringBuffer(INITIAL_SIZE);
                    rbwFeedbbcks = new IntBuffer(INITIAL_SIZE);
                } else {
                    if (composedText.length() > 0) {
                        if (chgOffset+chgLength < composedText.length()) {
                            String text;
                            text = composedText.toString().substring(chgOffset+chgLength,
                                                                     composedText.length());
                            composedText.setLength(chgOffset);
                            composedText.bppend(text);
                        } else {
                            // in cbse to remove substring from chgOffset
                            // to the end
                            composedText.setLength(chgOffset);
                        }
                        rbwFeedbbcks.remove(chgOffset, chgLength);
                    }
                }
            }
        }
        if (chgText != null) {
            composedText.insert(chgOffset, chgText);
            if (chgStyles != null)
                rbwFeedbbcks.insert(chgOffset, chgStyles);
        }

        if (composedText.length() == 0) {
            composedText = null;
            rbwFeedbbcks = null;

            // if there is bny outstbnding committed text stored by
            // dispbtchCommittedText(), it hbs to be sent to the
            // client component.
            if (committedText != null) {
                dispbtchCommittedText(committedText, when);
                committedText = null;
                return;
            }

            // otherwise, send null text to delete client's composed
            // text.
            postInputMethodEvent(InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                 null,
                                 0,
                                 null,
                                 null,
                                 when);

            return;
        }

        // Now sending the composed text to the client
        int composedOffset;
        AttributedString inputText;

        // if there is bny pbrtiblly committed text, concbtenbte it to
        // the composed text.
        if (committedText != null) {
            composedOffset = committedText.length();
            inputText = new AttributedString(committedText + composedText);
            committedText = null;
        } else {
            composedOffset = 0;
            inputText = new AttributedString(composedText.toString());
        }

        int currentFeedbbck;
        int nextFeedbbck;
        int stbrtOffset = 0;
        int currentOffset;
        int visiblePosition = 0;
        TextHitInfo visiblePositionInfo = null;

        rbwFeedbbcks.rewind();
        currentFeedbbck = rbwFeedbbcks.getNext();
        rbwFeedbbcks.unget();
        while ((nextFeedbbck = rbwFeedbbcks.getNext()) != -1) {
            if (visiblePosition == 0) {
                visiblePosition = nextFeedbbck & XIMVisibleMbsk;
                if (visiblePosition != 0) {
                    int index = rbwFeedbbcks.getOffset() - 1;

                    if (visiblePosition == XIMVisibleToBbckwbrd)
                        visiblePositionInfo = TextHitInfo.lebding(index);
                    else
                        visiblePositionInfo = TextHitInfo.trbiling(index);
                }
            }
            nextFeedbbck &= ~XIMVisibleMbsk;
            if (currentFeedbbck != nextFeedbbck) {
                rbwFeedbbcks.unget();
                currentOffset = rbwFeedbbcks.getOffset();
                inputText.bddAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT,
                                       convertVisublFeedbbckToHighlight(currentFeedbbck),
                                       composedOffset + stbrtOffset,
                                       composedOffset + currentOffset);
                stbrtOffset = currentOffset;
                currentFeedbbck = nextFeedbbck;
            }
        }
        currentOffset = rbwFeedbbcks.getOffset();
        if (currentOffset >= 0) {
            inputText.bddAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT,
                                   convertVisublFeedbbckToHighlight(currentFeedbbck),
                                   composedOffset + stbrtOffset,
                                   composedOffset + currentOffset);
        }

        postInputMethodEvent(InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                             inputText.getIterbtor(),
                             composedOffset,
                             TextHitInfo.lebding(cbretPosition),
                             visiblePositionInfo,
                             when);
    }

    /**
     * Flushes composed bnd committed text held in this context.
     * This method is invoked in the AWT Toolkit (X event loop) threbd context
     * bnd thus inside the AWT Lock.
     */
    // NOTE: This method mby be cblled by privileged threbds.
    //       This functionblity is implemented in b pbckbge-privbte method
    //       to insure thbt it cbnnot be overridden by client subclbsses.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!
    void flushText() {
        String flush = (committedText != null ? committedText : "");
        if (composedText != null) {
            flush += composedText.toString();
        }

        if (!flush.equbls("")) {
            AttributedString bttrstr = new AttributedString(flush);
            postInputMethodEvent(InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                                 bttrstr.getIterbtor(),
                                 flush.length(),
                                 null,
                                 null,
                                 EventQueue.getMostRecentEventTime());
            composedText = null;
            committedText = null;
        }
    }

    /*
     * Subclbsses should override disposeImpl() instebd of dispose(). Client
     * code should blwbys invoke dispose(), never disposeImpl().
     */
    protected synchronized void disposeImpl() {
        disposeXIC();
        bwtLock();
        composedText = null;
        committedText = null;
        rbwFeedbbcks = null;
        bwtUnlock();
        bwtFocussedComponent = null;
        lbstXICFocussedComponent = null;
    }

    /**
     * Frees bll X Window resources bssocibted with this object.
     *
     * @see jbvb.bwt.im.spi.InputMethod#dispose
     */
    public finbl void dispose() {
        boolebn cbll_disposeImpl = fblse;

        if (!disposed) {
            synchronized (this) {
                if (!disposed) {
                    disposed = cbll_disposeImpl = true;
                }
            }
        }

        if (cbll_disposeImpl) {
            disposeImpl();
        }
    }

    /**
     * Returns null.
     *
     * @see jbvb.bwt.im.spi.InputMethod#getControlObject
     */
    public Object getControlObject() {
        return null;
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethod#removeNotify
     */
    public synchronized void removeNotify() {
        dispose();
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethod#setCompositionEnbbled(boolebn)
     */
    public void setCompositionEnbbled(boolebn enbble) {
        /* If the composition stbte is successfully chbnged, set
           the sbvedCompositionStbte to 'enbble'. Otherwise, simply
           return.
           setCompositionEnbbledNbtive mby throw UnsupportedOperbtionException.
           Don't try to cbtch it since the method mby be cblled by clients.
           Use pbckbge privbte mthod 'resetCompositionStbte' if you wbnt the
           exception to be cbught.
        */
        if (setCompositionEnbbledNbtive(enbble)) {
            sbvedCompositionStbte = enbble;
        }
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethod#isCompositionEnbbled
     */
    public boolebn isCompositionEnbbled() {
        /* isCompositionEnbbledNbtive mby throw UnsupportedOperbtionException.
           Don't try to cbtch it since this method mby be cblled by clients.
           Use pbckbge privbte method 'getCompositionStbte' if you wbnt the
           exception to be cbught.
        */
        return isCompositionEnbbledNbtive();
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
     *
     */
    public void endComposition() {
        if (disposed) {
            return;
        }

        /* Before cblling resetXIC, record the current composition mode
           so thbt it cbn be restored lbter. */
        sbvedCompositionStbte = getCompositionStbte();
        boolebn bctive = hbveActiveClient();
        if (bctive && composedText == null && committedText == null){
            needResetXIC = true;
            needResetXICClient = new WebkReference<>(getClientComponent());
            return;
        }

        String text = resetXIC();
        /* needResetXIC is only set to true for bctive client. So pbssive
           client should not reset the flbg to fblse. */
        if (bctive) {
            needResetXIC = fblse;
        }

        // Remove bny existing composed text by posting bn InputMethodEvent
        // with null composed text.  It would be desirbble to wbit for b
        // dispbtchComposedText cbll from X input method engine, but some
        // input method does not conform to the XIM specificbtion bnd does
        // not cbll the preedit cbllbbck to erbse preedit text on cblling
        // XmbResetIC.  To work bround this problem, do it here by ourselves.
        bwtLock();
        composedText = null;
        postInputMethodEvent(InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                             null,
                             0,
                             null,
                             null);

        if (text != null && text.length() > 0) {
            dispbtchCommittedText(text);
        }
        bwtUnlock();

        // Restore the preedit stbte if it wbs enbbled
        if (sbvedCompositionStbte) {
            resetCompositionStbte();
        }
    }

    /**
     * Returns b string with informbtion bbout the current input method server, or null.
     * On both Linux & SunOS, the vblue of environment vbribble XMODIFIERS is
     * returned if set. Otherwise, on SunOS, $HOME/.dtprofile will be pbrsed
     * to find out the lbngubge service engine (btok or wnn) since there is
     * no API in Xlib which returns the informbtion of nbtive
     * IM server or lbngubge service bnd we wbnt to try our best to return bs much
     * informbtion bs possible.
     *
     * Note: This method could return null on Linux if XMODIFIERS is not set properly or
     * if bny IOException is thrown.
     * See mbn pbge of XSetLocbleModifiers(3X11) for the usgbe of XMODIFIERS,
     * btok12setup(1) bnd wnn6setup(1) for the informbtion written to
     * $HOME/.dtprofile when you run these two commbnds.
     *
     */
    public String getNbtiveInputMethodInfo() {
        String xmodifiers = System.getenv("XMODIFIERS");
        String imInfo = null;

        // If XMODIFIERS is set, return the vblue
        if (xmodifiers != null) {
            int imIndex = xmodifiers.indexOf("@im=");
            if (imIndex != -1) {
                imInfo = xmodifiers.substring(imIndex + 4);
            }
        } else if (System.getProperty("os.nbme").stbrtsWith("SunOS")) {
            File dtprofile = new File(System.getProperty("user.home") +
                                      "/.dtprofile");
            String lbngubgeEngineInfo = null;
            try {
                BufferedRebder br = new BufferedRebder(new FileRebder(dtprofile));
                String line = null;

                while ( lbngubgeEngineInfo == null && (line = br.rebdLine()) != null) {
                    if (line.contbins("btok") || line.contbins("wnn")) {
                        StringTokenizer tokens =  new StringTokenizer(line);
                        while (tokens.hbsMoreTokens()) {
                            String token = tokens.nextToken();
                            if (Pbttern.mbtches("btok.*setup", token) ||
                                Pbttern.mbtches("wnn.*setup", token)){
                                lbngubgeEngineInfo = token.substring(0, token.indexOf("setup"));
                                brebk;
                            }
                        }
                    }
                }

                br.close();
            } cbtch(IOException ioex) {
                // Since this method is provided for internbl testing only,
                // we dump the stbck trbce for the ebse of debugging.
                ioex.printStbckTrbce();
            }

            imInfo = "htt " + lbngubgeEngineInfo;
        }

        return imInfo;
    }


    /**
     * Performs mbpping from bn XIM visible feedbbck vblue to Jbvb IM highlight.
     * @return Jbvb input method highlight
     */
    privbte InputMethodHighlight convertVisublFeedbbckToHighlight(int feedbbck) {
        InputMethodHighlight highlight;

        switch (feedbbck) {
        cbse XIMUnderline:
            highlight = InputMethodHighlight.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT;
            brebk;
        cbse XIMReverse:
            highlight = InputMethodHighlight.SELECTED_CONVERTED_TEXT_HIGHLIGHT;
            brebk;
        cbse XIMHighlight:
            highlight = InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT;
            brebk;
        cbse XIMPrimbry:
            highlight = InputMethodHighlight.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT;
            brebk;
        cbse XIMSecondbry:
            highlight = InputMethodHighlight.SELECTED_CONVERTED_TEXT_HIGHLIGHT;
            brebk;
        cbse XIMTertibry:
            highlight = InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT;
            brebk;
        defbult:
            highlight = InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT;
            brebk;
        }
        return highlight;
    }

    // initibl cbpbcity size for string buffer, etc.
    privbte stbtic finbl int INITIAL_SIZE = 64;

    /**
     * IntBuffer is bn inner clbss thbt mbnipulbtes bn int brrby bnd
     * provides UNIX file io strebm-like progrbmming interfbces to
     * bccess it. (An blternbtive would be to use ArrbyList which mby
     * be too expensive for the work.)
     */
    privbte finbl clbss IntBuffer {
        privbte int[] intArrby;
        privbte int size;
        privbte int index;

        IntBuffer(int initiblCbpbcity) {
            intArrby = new int[initiblCbpbcity];
            size = 0;
            index = 0;
        }

        void insert(int offset, int[] vblues) {
            int newSize = size + vblues.length;
            if (intArrby.length < newSize) {
                int[] newIntArrby = new int[newSize * 2];
                System.brrbycopy(intArrby, 0, newIntArrby, 0, size);
                intArrby = newIntArrby;
            }
            System.brrbycopy(intArrby, offset, intArrby, offset+vblues.length,
                             size - offset);
            System.brrbycopy(vblues, 0, intArrby, offset, vblues.length);
            size += vblues.length;
            if (index > offset)
                index = offset;
        }

        void remove(int offset, int length) {
            if (offset + length != size)
                System.brrbycopy(intArrby, offset+length, intArrby, offset,
                                 size - offset - length);
            size -= length;
            if (index > offset)
                index = offset;
        }

        void replbce(int offset, int[] vblues) {
            System.brrbycopy(vblues, 0, intArrby, offset, vblues.length);
        }

        void removeAll() {
            size = 0;
            index = 0;
        }

        void rewind() {
            index = 0;
        }

        int getNext() {
            if (index == size)
                return -1;
            return intArrby[index++];
        }

        void unget() {
            if (index != 0)
                index--;
        }

        int getOffset() {
            return index;
        }

        public String toString() {
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < size;) {
                s.bppend(intArrby[i++]);
                if (i < size)
                    s.bppend(",");
            }
            return s.toString();
        }
    }

    /*
     * Nbtive methods
     */
    protected nbtive String resetXIC();
    privbte nbtive void disposeXIC();
    privbte nbtive boolebn setCompositionEnbbledNbtive(boolebn enbble);
    privbte nbtive boolebn isCompositionEnbbledNbtive();
    privbte nbtive void turnoffStbtusWindow();
}
