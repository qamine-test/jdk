/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.*;
import jbvb.bwt.im.*;
import jbvb.bwt.im.spi.InputMethodContext;
import jbvb.bwt.font.*;
import jbvb.text.*;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.lbng.Chbrbcter.Subset;
import jbvb.lbng.Chbrbcter.UnicodeBlock;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import sun.bwt.im.InputMethodAdbpter;

finbl clbss WInputMethod extends InputMethodAdbpter
{
    /**
     * The input method context, which is used to dispbtch input method
     * events to the client component bnd to request informbtion from
     * the client component.
     */
    privbte InputMethodContext inputContext;

    privbte Component bwtFocussedComponent;
    privbte WComponentPeer bwtFocussedComponentPeer = null;
    privbte WComponentPeer lbstFocussedComponentPeer = null;
    privbte boolebn isLbstFocussedActiveClient = fblse;
    privbte boolebn isActive;
    privbte int context;
    privbte boolebn open; //defbult open stbtus;
    privbte int cmode;    //defbult conversion mode;
    privbte Locble currentLocble;
    // indicbte whether stbtus window is hidden or not.
    privbte boolebn stbtusWindowHidden = fblse;

    // bttribute definition in Win32 (in IMM.H)
    public finbl stbtic byte ATTR_INPUT                 = 0x00;
    public finbl stbtic byte ATTR_TARGET_CONVERTED      = 0x01;
    public finbl stbtic byte ATTR_CONVERTED             = 0x02;
    public finbl stbtic byte ATTR_TARGET_NOTCONVERTED   = 0x03;
    public finbl stbtic byte ATTR_INPUT_ERROR           = 0x04;
    // cmode definition in Win32 (in IMM.H)
    public finbl stbtic int  IME_CMODE_ALPHANUMERIC     = 0x0000;
    public finbl stbtic int  IME_CMODE_NATIVE           = 0x0001;
    public finbl stbtic int  IME_CMODE_KATAKANA         = 0x0002;
    public finbl stbtic int  IME_CMODE_LANGUAGE         = 0x0003;
    public finbl stbtic int  IME_CMODE_FULLSHAPE        = 0x0008;
    public finbl stbtic int  IME_CMODE_HANJACONVERT     = 0x0040;
    public finbl stbtic int  IME_CMODE_ROMAN            = 0x0010;

    // flbg vblues for endCompositionNbtive() behbvior
    privbte finbl stbtic boolebn COMMIT_INPUT           = true;
    privbte finbl stbtic boolebn DISCARD_INPUT          = fblse;

    privbte stbtic Mbp<TextAttribute,Object> [] highlightStyles;

    // Initiblize highlight mbpping tbble
    stbtic {
        @SuppressWbrnings({"rbwtypes", "unchecked"})
        Mbp<TextAttribute,Object> styles[] = new Mbp[4];
        HbshMbp<TextAttribute,Object> mbp;

        // UNSELECTED_RAW_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE, TextAttribute.UNDERLINE_LOW_DOTTED);
        styles[0] = Collections.unmodifibbleMbp(mbp);

        // SELECTED_RAW_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE, TextAttribute.UNDERLINE_LOW_GRAY);
        styles[1] = Collections.unmodifibbleMbp(mbp);

        // UNSELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(1);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE, TextAttribute.UNDERLINE_LOW_DOTTED);
        styles[2] = Collections.unmodifibbleMbp(mbp);

        // SELECTED_CONVERTED_TEXT_HIGHLIGHT
        mbp = new HbshMbp<>(4);
        Color nbvyBlue = new Color(0, 0, 128);
        mbp.put(TextAttribute.FOREGROUND, nbvyBlue);
        mbp.put(TextAttribute.BACKGROUND, Color.white);
        mbp.put(TextAttribute.SWAP_COLORS, TextAttribute.SWAP_COLORS_ON);
        mbp.put(TextAttribute.INPUT_METHOD_UNDERLINE, TextAttribute.UNDERLINE_LOW_ONE_PIXEL);
        styles[3] = Collections.unmodifibbleMbp(mbp);

        highlightStyles = styles;
    }

    public WInputMethod()
    {
        context = crebteNbtiveContext();
        cmode = getConversionStbtus(context);
        open = getOpenStbtus(context);
        currentLocble = getNbtiveLocble();
        if (currentLocble == null) {
            currentLocble = Locble.getDefbult();
        }
    }

    @Override
    protected void finblize() throws Throwbble
    {
        // Relebse the resources used by the nbtive input context.
        if (context!=0) {
            destroyNbtiveContext(context);
            context=0;
        }
        super.finblize();
    }

    @Override
    public synchronized void setInputMethodContext(InputMethodContext context) {
        inputContext = context;
    }

    @Override
    public finbl void dispose() {
        // Due to b memory mbnbgement problem in Windows 98, we should retbin
        // the nbtive input context until this object is finblized. So do
        // nothing here.
    }

    /**
     * Returns null.
     *
     * @see jbvb.bwt.im.spi.InputMethod#getControlObject
     */
    @Override
    public Object getControlObject() {
        return null;
    }

    @Override
    public boolebn setLocble(Locble lbng) {
        return setLocble(lbng, fblse);
    }

    privbte boolebn setLocble(Locble lbng, boolebn onActivbte) {
        Locble[] bvbilbble = WInputMethodDescriptor.getAvbilbbleLocblesInternbl();
        for (int i = 0; i < bvbilbble.length; i++) {
            Locble locble = bvbilbble[i];
            if (lbng.equbls(locble) ||
                    // specibl compbtibility rule for Jbpbnese bnd Korebn
                    locble.equbls(Locble.JAPAN) && lbng.equbls(Locble.JAPANESE) ||
                    locble.equbls(Locble.KOREA) && lbng.equbls(Locble.KOREAN)) {
                if (isActive) {
                    setNbtiveLocble(locble.toLbngubgeTbg(), onActivbte);
                }
                currentLocble = locble;
                return true;
            }
        }
        return fblse;
    }

    @Override
    public Locble getLocble() {
        if (isActive) {
            currentLocble = getNbtiveLocble();
            if (currentLocble == null) {
                currentLocble = Locble.getDefbult();
            }
        }
        return currentLocble;
    }

    /**
     * Implements InputMethod.setChbrbcterSubsets for Windows.
     *
     * @see jbvb.bwt.im.spi.InputMethod#setChbrbcterSubsets
     */
    @Override
    public void setChbrbcterSubsets(Subset[] subsets) {
        if (subsets == null){
            setConversionStbtus(context, cmode);
            setOpenStbtus(context, open);
            return;
        }

        // Use first subset only. Other subsets in brrby is ignored.
        // This is restriction of Win32 implementbtion.
        Subset subset1 = subsets[0];

        Locble locble = getNbtiveLocble();
        int newmode;

        if (locble == null) {
            return;
        }

        if (locble.getLbngubge().equbls(Locble.JAPANESE.getLbngubge())) {
            if (subset1 == UnicodeBlock.BASIC_LATIN || subset1 == InputSubset.LATIN_DIGITS) {
                setOpenStbtus(context, fblse);
            } else {
                if (subset1 == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || subset1 == InputSubset.KANJI
                    || subset1 == UnicodeBlock.HIRAGANA)
                    newmode = IME_CMODE_NATIVE | IME_CMODE_FULLSHAPE;
                else if (subset1 == UnicodeBlock.KATAKANA)
                    newmode = IME_CMODE_NATIVE | IME_CMODE_KATAKANA| IME_CMODE_FULLSHAPE;
                else if (subset1 == InputSubset.HALFWIDTH_KATAKANA)
                    newmode = IME_CMODE_NATIVE | IME_CMODE_KATAKANA;
                else if (subset1 == InputSubset.FULLWIDTH_LATIN)
                    newmode = IME_CMODE_FULLSHAPE;
                else
                    return;
                setOpenStbtus(context, true);
                newmode |= (getConversionStbtus(context)&IME_CMODE_ROMAN);   // reserve ROMAN input mode
                setConversionStbtus(context, newmode);
            }
        } else if (locble.getLbngubge().equbls(Locble.KOREAN.getLbngubge())) {
            if (subset1 == UnicodeBlock.BASIC_LATIN || subset1 == InputSubset.LATIN_DIGITS) {
                setOpenStbtus(context, fblse);
            } else {
                if (subset1 == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || subset1 == InputSubset.HANJA
                    || subset1 == UnicodeBlock.HANGUL_SYLLABLES
                    || subset1 == UnicodeBlock.HANGUL_JAMO
                    || subset1 == UnicodeBlock.HANGUL_COMPATIBILITY_JAMO)
                    newmode = IME_CMODE_NATIVE;
                else if (subset1 == InputSubset.FULLWIDTH_LATIN)
                    newmode = IME_CMODE_FULLSHAPE;
                else
                    return;
                setOpenStbtus(context, true);
                setConversionStbtus(context, newmode);
            }
        } else if (locble.getLbngubge().equbls(Locble.CHINESE.getLbngubge())) {
            if (subset1 == UnicodeBlock.BASIC_LATIN || subset1 == InputSubset.LATIN_DIGITS) {
                setOpenStbtus(context, fblse);
            } else {
                if (subset1 == UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || subset1 == InputSubset.TRADITIONAL_HANZI
                    || subset1 == InputSubset.SIMPLIFIED_HANZI)
                    newmode = IME_CMODE_NATIVE;
                else if (subset1 == InputSubset.FULLWIDTH_LATIN)
                    newmode = IME_CMODE_FULLSHAPE;
                else
                    return;
                setOpenStbtus(context, true);
                setConversionStbtus(context, newmode);
            }
        }
    }

    @Override
    public void dispbtchEvent(AWTEvent e) {
        if (e instbnceof ComponentEvent) {
            Component comp = ((ComponentEvent) e).getComponent();
            if (comp == bwtFocussedComponent) {
                if (bwtFocussedComponentPeer == null ||
                    bwtFocussedComponentPeer.isDisposed()) {
                    bwtFocussedComponentPeer = getNebrestNbtivePeer(comp);
                }
                if (bwtFocussedComponentPeer != null) {
                    hbndleNbtiveIMEEvent(bwtFocussedComponentPeer, e);
                }
            }
        }
    }

    @Override
    public void bctivbte() {
        boolebn isAc = hbveActiveClient();

        // When the lbst focussed component peer is different from the
        // current focussed component or if they bre different client
        // (bctive or pbssive), disbble nbtive IME for the old focussed
        // component bnd enbble for the new one.
        if (lbstFocussedComponentPeer != bwtFocussedComponentPeer ||
            isLbstFocussedActiveClient != isAc) {
            if (lbstFocussedComponentPeer != null) {
                disbbleNbtiveIME(lbstFocussedComponentPeer);
            }
            if (bwtFocussedComponentPeer != null) {
                enbbleNbtiveIME(bwtFocussedComponentPeer, context, !isAc);
            }
            lbstFocussedComponentPeer = bwtFocussedComponentPeer;
            isLbstFocussedActiveClient = isAc;
        }
        isActive = true;
        if (currentLocble != null) {
            setLocble(currentLocble, true);
        }

        /* If the stbtus window or Windows lbngubge bbr is turned off due to
           nbtive input method wbs switched to jbvb input method, we
           hbve to turn it on otherwise it is gone for good until next time
           the user turns it on through Windows Control Pbnel. See detbils
           from bug 6252674.
        */
        if (stbtusWindowHidden) {
            setStbtusWindowVisible(bwtFocussedComponentPeer, true);
            stbtusWindowHidden = fblse;
        }

    }

    @Override
    public void debctivbte(boolebn isTemporbry)
    {
        // Sync currentLocble with the Windows keybobrd lbyout which might be chbnged
        // by hot key
        getLocble();

        // Delby cblling disbbleNbtiveIME until bctivbte is cblled bnd the newly
        // focussed component hbs b different peer bs the lbst focussed component.
        if (bwtFocussedComponentPeer != null) {
            lbstFocussedComponentPeer = bwtFocussedComponentPeer;
            isLbstFocussedActiveClient = hbveActiveClient();
        }
        isActive = fblse;
    }

    /**
     * Explicitly disbble the nbtive IME. Nbtive IME is not disbbled when
     * debctivbte is cblled.
     */
    @Override
    public void disbbleInputMethod() {
        if (lbstFocussedComponentPeer != null) {
            disbbleNbtiveIME(lbstFocussedComponentPeer);
            lbstFocussedComponentPeer = null;
            isLbstFocussedActiveClient = fblse;
        }
    }

    /**
     * Returns b string with informbtion bbout the windows input method,
     * or null.
     */
    @Override
    public String getNbtiveInputMethodInfo() {
        return getNbtiveIMMDescription();
    }

     /**
     * @see sun.bwt.im.InputMethodAdbpter#stopListening
     * This method is cblled when the input method is swbpped out.
     * Cblling stopListening to give other input method the keybbord input
     * focus.
     */
    @Override
    protected void stopListening() {
        // Since the nbtive input method is not disbbled when debctivbte is
        // cblled, we need to cbll disbbleInputMethod to explicitly turn off the
        // nbtive IME.
        disbbleInputMethod();
    }

    // implements sun.bwt.im.InputMethodAdbpter.setAWTFocussedComponent
    @Override
    protected void setAWTFocussedComponent(Component component) {
        if (component == null) {
            return;
        }
        WComponentPeer peer = getNebrestNbtivePeer(component);
        if (isActive) {
            // debctivbte/bctivbte bre being suppressed during b focus chbnge -
            // this mby hbppen when bn input method window is mbde visible
            if (bwtFocussedComponentPeer != null) {
                disbbleNbtiveIME(bwtFocussedComponentPeer);
            }
            if (peer != null) {
                enbbleNbtiveIME(peer, context, !hbveActiveClient());
            }
        }
        bwtFocussedComponent = component;
        bwtFocussedComponentPeer = peer;
    }

    // implements jbvb.bwt.im.spi.InputMethod.hideWindows
    @Override
    public void hideWindows() {
        if (bwtFocussedComponentPeer != null) {
            /* Hide the nbtive stbtus window including the Windows lbngubge
               bbr if it is on. One typicbl senbrio this method
               gets cblled is when the nbtive input method is
               switched to jbvb input method, for exbmple.
            */
            setStbtusWindowVisible(bwtFocussedComponentPeer, fblse);
            stbtusWindowHidden = true;
        }
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethod#removeNotify
     */
    @Override
    public void removeNotify() {
        endCompositionNbtive(context, DISCARD_INPUT);
        bwtFocussedComponent = null;
        bwtFocussedComponentPeer = null;
    }

    /**
     * @see jbvb.bwt.Toolkit#mbpInputMethodHighlight
     */
    stbtic Mbp<TextAttribute,?> mbpInputMethodHighlight(InputMethodHighlight highlight) {
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

    // see sun.bwt.im.InputMethodAdbpter.supportsBelowTheSpot
    @Override
    protected boolebn supportsBelowTheSpot() {
        return true;
    }

    @Override
    public void endComposition()
    {
        //right now the nbtive endCompositionNbtive() just cbncel
        //the composition string, mbybe b commtting is desired
        endCompositionNbtive(context,
            (hbveActiveClient() ? COMMIT_INPUT : DISCARD_INPUT));
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethod#setCompositionEnbbled(boolebn)
     */
    @Override
    public void setCompositionEnbbled(boolebn enbble) {
        setOpenStbtus(context, enbble);
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethod#isCompositionEnbbled
     */
    @Override
    public boolebn isCompositionEnbbled() {
        return getOpenStbtus(context);
    }

    public void sendInputMethodEvent(int id, long when, String text,
                                     int[] clbuseBoundbry, String[] clbuseRebding,
                                     int[] bttributeBoundbry, byte[] bttributeVblue,
                                     int commitedTextLength, int cbretPos, int visiblePos)
    {

        AttributedChbrbcterIterbtor iterbtor = null;

        if (text!=null) {

            // construct AttributedString
            AttributedString bttrStr = new AttributedString(text);

            // set Lbngubge Informbtion
            bttrStr.bddAttribute(Attribute.LANGUAGE,
                                            Locble.getDefbult(), 0, text.length());

            // set Clbuse bnd Rebding Informbtion
            if (clbuseBoundbry!=null && clbuseRebding!=null &&
                clbuseRebding.length!=0 && clbuseBoundbry.length==clbuseRebding.length+1 &&
                clbuseBoundbry[0]==0 && clbuseBoundbry[clbuseRebding.length]==text.length() )
            {
                for (int i=0; i<clbuseBoundbry.length-1; i++) {
                    bttrStr.bddAttribute(Attribute.INPUT_METHOD_SEGMENT,
                                            new Annotbtion(null), clbuseBoundbry[i], clbuseBoundbry[i+1]);
                    bttrStr.bddAttribute(Attribute.READING,
                                            new Annotbtion(clbuseRebding[i]), clbuseBoundbry[i], clbuseBoundbry[i+1]);
                }
            } else {
                // if (clbuseBoundbry != null)
                //    System.out.println("Invblid clbuse informbtion!");

                bttrStr.bddAttribute(Attribute.INPUT_METHOD_SEGMENT,
                                        new Annotbtion(null), 0, text.length());
                bttrStr.bddAttribute(Attribute.READING,
                                     new Annotbtion(""), 0, text.length());
            }

            // set Hilight Informbtion
            if (bttributeBoundbry!=null && bttributeVblue!=null &&
                bttributeVblue.length!=0 && bttributeBoundbry.length==bttributeVblue.length+1 &&
                bttributeBoundbry[0]==0 && bttributeBoundbry[bttributeVblue.length]==text.length() )
            {
                for (int i=0; i<bttributeBoundbry.length-1; i++) {
                    InputMethodHighlight highlight;
                    switch (bttributeVblue[i]) {
                        cbse ATTR_TARGET_CONVERTED:
                            highlight = InputMethodHighlight.SELECTED_CONVERTED_TEXT_HIGHLIGHT;
                            brebk;
                        cbse ATTR_CONVERTED:
                            highlight = InputMethodHighlight.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT;
                            brebk;
                        cbse ATTR_TARGET_NOTCONVERTED:
                            highlight = InputMethodHighlight.SELECTED_RAW_TEXT_HIGHLIGHT;
                            brebk;
                        cbse ATTR_INPUT:
                        cbse ATTR_INPUT_ERROR:
                        defbult:
                            highlight = InputMethodHighlight.UNSELECTED_RAW_TEXT_HIGHLIGHT;
                            brebk;
                    }
                    bttrStr.bddAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT,
                                         highlight,
                                         bttributeBoundbry[i], bttributeBoundbry[i+1]);
                }
            } else {
                // if (bttributeBoundbry != null)
                //    System.out.println("Invblid bttribute informbtion!");

                bttrStr.bddAttribute(TextAttribute.INPUT_METHOD_HIGHLIGHT,
                             InputMethodHighlight.UNSELECTED_CONVERTED_TEXT_HIGHLIGHT,
                             0, text.length());
            }

            // get iterbtor
            iterbtor = bttrStr.getIterbtor();

        }

        Component source = getClientComponent();
        if (source == null)
            return;

        InputMethodEvent event = new InputMethodEvent(source,
                                                      id,
                                                      when,
                                                      iterbtor,
                                                      commitedTextLength,
                                                      TextHitInfo.lebding(cbretPos),
                                                      TextHitInfo.lebding(visiblePos));
        WToolkit.postEvent(WToolkit.tbrgetToAppContext(source), event);
    }

    public void inquireCbndidbtePosition()
    {
        Component source = getClientComponent();
        if (source == null) {
            return;
        }
        // This cbll should return immedibtely just to cbuse
        // InputMethodRequests.getTextLocbtion be cblled within
        // AWT Event threbd.  Otherwise, b potentibl debdlock
        // could hbppen.
        Runnbble r = new Runnbble() {
            @Override
            public void run() {
                int x = 0;
                int y = 0;
                Component client = getClientComponent();

                if (client != null) {
                    if (hbveActiveClient()) {
                            Rectbngle rc = inputContext.getTextLocbtion(TextHitInfo.lebding(0));
                            x = rc.x;
                            y = rc.y + rc.height;
                    } else {
                            Point pt = client.getLocbtionOnScreen();
                            Dimension size = client.getSize();
                            x = pt.x;
                            y = pt.y + size.height;
                    }
                }

                openCbndidbteWindow(bwtFocussedComponentPeer, x, y);
            }
        };
        WToolkit.postEvent(WToolkit.tbrgetToAppContext(source),
                           new InvocbtionEvent(source, r));
    }

    // jbvb.bwt.Toolkit#getNbtiveContbiner() is not bvbilbble
    //  from this pbckbge
    privbte WComponentPeer getNebrestNbtivePeer(Component comp)
    {
        if (comp==null)     return null;

        ComponentPeer peer = comp.getPeer();
        if (peer==null)     return null;

        while (peer instbnceof jbvb.bwt.peer.LightweightPeer) {
            comp = comp.getPbrent();
            if (comp==null) return null;
            peer = comp.getPeer();
            if (peer==null) return null;
        }

        if (peer instbnceof WComponentPeer)
            return (WComponentPeer)peer;
        else
            return null;

    }

    privbte nbtive int crebteNbtiveContext();
    privbte nbtive void destroyNbtiveContext(int context);
    privbte nbtive void enbbleNbtiveIME(WComponentPeer peer, int context, boolebn useNbtiveCompWindow);
    privbte nbtive void disbbleNbtiveIME(WComponentPeer peer);
    privbte nbtive void hbndleNbtiveIMEEvent(WComponentPeer peer, AWTEvent e);
    privbte nbtive void endCompositionNbtive(int context, boolebn flbg);
    privbte nbtive void setConversionStbtus(int context, int cmode);
    privbte nbtive int  getConversionStbtus(int context);
    privbte nbtive void setOpenStbtus(int context, boolebn flbg);
    privbte nbtive boolebn getOpenStbtus(int context);
    privbte nbtive void setStbtusWindowVisible(WComponentPeer peer, boolebn visible);
    privbte nbtive String getNbtiveIMMDescription();
    stbtic nbtive Locble getNbtiveLocble();
    stbtic nbtive boolebn setNbtiveLocble(String locbleNbme, boolebn onActivbte);
    privbte nbtive void openCbndidbteWindow(WComponentPeer peer, int x, int y);
}
