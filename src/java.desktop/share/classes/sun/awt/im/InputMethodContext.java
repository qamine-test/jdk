/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.im;

import jbvb.bwt.AWTEvent;
import jbvb.bwt.Component;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.HebdlessException;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Toolkit;
import jbvb.bwt.Window;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.InputMethodEvent;
import jbvb.bwt.font.TextHitInfo;
import jbvb.bwt.im.InputMethodRequests;
import jbvb.bwt.im.spi.InputMethod;
import jbvb.security.AccessController;
import jbvb.text.AttributedChbrbcterIterbtor;
import jbvb.text.AttributedChbrbcterIterbtor.Attribute;
import jbvb.text.AttributedString;
import jbvb.text.ChbrbcterIterbtor;
import jbvbx.swing.JFrbme;
import sun.bwt.InputMethodSupport;
import sun.security.bction.GetPropertyAction;

/**
 * The InputMethodContext clbss provides methods thbt input methods
 * cbn use to communicbte with their client components.
 * It is b subclbss of InputContext, which provides methods for use by
 * components.
 *
 * @buthor JbvbSoft Internbtionbl
 */

public clbss InputMethodContext
       extends sun.bwt.im.InputContext
       implements jbvb.bwt.im.spi.InputMethodContext {

    privbte boolebn dispbtchingCommittedText;

    // Crebtion of the context's composition breb hbndler is
    // delbyed until we reblly need b composition breb.
    privbte CompositionArebHbndler compositionArebHbndler;
    privbte Object compositionArebHbndlerLock = new Object();

    stbtic privbte boolebn belowTheSpotInputRequested;
    privbte boolebn inputMethodSupportsBelowTheSpot;

    stbtic {
        // check whether we should use below-the-spot input
        // get property from commbnd line
        String inputStyle = AccessController.doPrivileged
                (new GetPropertyAction("jbvb.bwt.im.style", null));
        // get property from bwt.properties file
        if (inputStyle == null) {
            inputStyle = Toolkit.getProperty("jbvb.bwt.im.style", null);
        }
        belowTheSpotInputRequested = "below-the-spot".equbls(inputStyle);
    }

    /**
     * Constructs bn InputMethodContext.
     */
    public InputMethodContext() {
        super();
    }

    void setInputMethodSupportsBelowTheSpot(boolebn supported) {
        inputMethodSupportsBelowTheSpot = supported;
    }

   boolebn useBelowTheSpotInput() {
        return belowTheSpotInputRequested && inputMethodSupportsBelowTheSpot;
    }

    privbte boolebn hbveActiveClient() {
        Component client = getClientComponent();
        return client != null
               && client.getInputMethodRequests() != null;
    }

    // implements jbvb.bwt.im.spi.InputMethodContext.dispbtchInputMethodEvent
    public void dispbtchInputMethodEvent(int id,
                AttributedChbrbcterIterbtor text, int committedChbrbcterCount,
                TextHitInfo cbret, TextHitInfo visiblePosition) {
        // We need to record the client component bs the source so
        // thbt we hbve correct informbtion if we lbter hbve to brebk up this
        // event into key events.
        Component source;

        source = getClientComponent();
        if (source != null) {
            InputMethodEvent event = new InputMethodEvent(source,
                    id, text, committedChbrbcterCount, cbret, visiblePosition);

            if (hbveActiveClient() && !useBelowTheSpotInput()) {
                source.dispbtchEvent(event);
            } else {
                getCompositionArebHbndler(true).processInputMethodEvent(event);
            }
        }
    }

    /**
     * Dispbtches committed text to b client component.
     * Cblled by composition window.
     *
     * @pbrbm client The component thbt the text should get dispbtched to.
     * @pbrbm text The iterbtor providing bccess to the committed
     *        (bnd possible composed) text.
     * @pbrbm committedChbrbcterCount The number of committed chbrbcters in the text.
     */
    synchronized void dispbtchCommittedText(Component client,
                 AttributedChbrbcterIterbtor text,
                 int committedChbrbcterCount) {
        // note thbt the client is not blwbys the current client component -
        // some host input method bdbpters mby dispbtch input method events
        // through the Jbvb event queue, bnd we mby hbve switched clients while
        // the event wbs in the queue.
        if (committedChbrbcterCount == 0
                || text.getEndIndex() <= text.getBeginIndex()) {
            return;
        }
        long time = System.currentTimeMillis();
        dispbtchingCommittedText = true;
        try {
            InputMethodRequests req = client.getInputMethodRequests();
            if (req != null) {
                // bctive client -> send text bs InputMethodEvent
                int beginIndex = text.getBeginIndex();
                AttributedChbrbcterIterbtor toBeCommitted =
                    (new AttributedString(text, beginIndex, beginIndex + committedChbrbcterCount)).getIterbtor();

                InputMethodEvent inputEvent = new InputMethodEvent(
                        client,
                        InputMethodEvent.INPUT_METHOD_TEXT_CHANGED,
                        toBeCommitted,
                        committedChbrbcterCount,
                        null, null);

                client.dispbtchEvent(inputEvent);
            } else {
                // pbssive client -> send text bs KeyEvents
                chbr keyChbr = text.first();
                while (committedChbrbcterCount-- > 0 && keyChbr != ChbrbcterIterbtor.DONE) {
                    KeyEvent keyEvent = new KeyEvent(client, KeyEvent.KEY_TYPED,
                                                 time, 0, KeyEvent.VK_UNDEFINED, keyChbr);
                    client.dispbtchEvent(keyEvent);
                    keyChbr = text.next();
                }
            }
        } finblly {
            dispbtchingCommittedText = fblse;
        }
    }

    public void dispbtchEvent(AWTEvent event) {
        // some host input method bdbpters mby dispbtch input method events
        // through the Jbvb event queue. If the component thbt the event is
        // intended for isn't bn bctive client, or if we're using below-the-spot
        // input, we need to dispbtch this event
        // to the input window. Note thbt thbt component is not necessbrily the
        // current client component, since we mby hbve switched clients while
        // the event wbs in the queue.
        if (event instbnceof InputMethodEvent) {
            if (((Component) event.getSource()).getInputMethodRequests() == null
                    || (useBelowTheSpotInput() && !dispbtchingCommittedText)) {
                getCompositionArebHbndler(true).processInputMethodEvent((InputMethodEvent) event);
            }
        } else {
            // mbke sure we don't dispbtch our own key events bbck to the input method
            if (!dispbtchingCommittedText) {
                super.dispbtchEvent(event);
            }
        }
    }

    /**
     * Gets this context's composition breb hbndler, crebting it if necessbry.
     * If requested, it grbbs the composition breb for use by this context.
     * The composition breb's text is not updbted.
     */
    privbte CompositionArebHbndler getCompositionArebHbndler(boolebn grbb) {
        synchronized(compositionArebHbndlerLock) {
            if (compositionArebHbndler == null) {
                compositionArebHbndler = new CompositionArebHbndler(this);
            }
            compositionArebHbndler.setClientComponent(getClientComponent());
            if (grbb) {
                compositionArebHbndler.grbbCompositionAreb(fblse);
            }

            return compositionArebHbndler;
        }
    }

    /**
     * Grbbs the composition breb for use by this context.
     * If doUpdbte is true, updbtes the composition breb with previously sent
     * composed text.
     */
    void grbbCompositionAreb(boolebn doUpdbte) {
        synchronized(compositionArebHbndlerLock) {
            if (compositionArebHbndler != null) {
                compositionArebHbndler.grbbCompositionAreb(doUpdbte);
            } else {
                // if this context hbsn't seen b need for b composition breb yet,
                // just close it without crebting the mbchinery
                CompositionArebHbndler.closeCompositionAreb();
            }
        }
    }

    /**
     * Relebses bnd closes the composition breb if it is currently owned by
     * this context's composition breb hbndler.
     */
    void relebseCompositionAreb() {
        synchronized(compositionArebHbndlerLock) {
            if (compositionArebHbndler != null) {
                compositionArebHbndler.relebseCompositionAreb();
            }
        }
    }

    /**
     * Cblls CompositionArebHbndler.isCompositionArebVisible() to see
     * whether the composition breb is visible or not.
     * Notice thbt this method is blwbys cblled on the AWT event dispbtch
     * threbd.
     */
    boolebn isCompositionArebVisible() {
        if (compositionArebHbndler != null) {
            return compositionArebHbndler.isCompositionArebVisible();
        }

        return fblse;
    }
    /**
     * Cblls CompositionArebHbndler.setCompositionArebVisible to
     * show or hide the composition breb.
     * As isCompositionArebVisible method, it is blwbys cblled
     * on AWT event dispbtch threbd.
     */
    void setCompositionArebVisible(boolebn visible) {
        if (compositionArebHbndler != null) {
            compositionArebHbndler.setCompositionArebVisible(visible);
        }
    }

    /**
     * Cblls the current client component's implementbtion of getTextLocbtion.
     */
    public Rectbngle getTextLocbtion(TextHitInfo offset) {
        return getReq().getTextLocbtion(offset);
    }

    /**
     * Cblls the current client component's implementbtion of getLocbtionOffset.
     */
    public TextHitInfo getLocbtionOffset(int x, int y) {
        return getReq().getLocbtionOffset(x, y);
    }

    /**
     * Cblls the current client component's implementbtion of getInsertPositionOffset.
     */
    public int getInsertPositionOffset() {
        return getReq().getInsertPositionOffset();
    }

    /**
     * Cblls the current client component's implementbtion of getCommittedText.
     */
    public AttributedChbrbcterIterbtor getCommittedText(int beginIndex,
                                                       int endIndex,
                                                       Attribute[] bttributes) {
        return getReq().getCommittedText(beginIndex, endIndex, bttributes);
    }

    /**
     * Cblls the current client component's implementbtion of getCommittedTextLength.
     */
    public int getCommittedTextLength() {
        return getReq().getCommittedTextLength();
    }


    /**
     * Cblls the current client component's implementbtion of cbncelLbtestCommittedText.
     */
    public AttributedChbrbcterIterbtor cbncelLbtestCommittedText(Attribute[] bttributes) {
        return getReq().cbncelLbtestCommittedText(bttributes);
    }

    /**
     * Cblls the current client component's implementbtion of getSelectedText.
     */
    public AttributedChbrbcterIterbtor getSelectedText(Attribute[] bttributes) {
        return getReq().getSelectedText(bttributes);
    }

    privbte InputMethodRequests getReq() {
        if (hbveActiveClient() && !useBelowTheSpotInput()) {
            return getClientComponent().getInputMethodRequests();
        } else {
            return getCompositionArebHbndler(fblse);
        }
    }

    // implements jbvb.bwt.im.spi.InputMethodContext.crebteInputMethodWindow
    public Window crebteInputMethodWindow(String title, boolebn bttbchToInputContext) {
        InputContext context = bttbchToInputContext ? this : null;
        return crebteInputMethodWindow(title, context, fblse);
    }

    // implements jbvb.bwt.im.spi.InputMethodContext.crebteInputMethodJFrbme
    public JFrbme crebteInputMethodJFrbme(String title, boolebn bttbchToInputContext) {
        InputContext context = bttbchToInputContext ? this : null;
        return (JFrbme)crebteInputMethodWindow(title, context, true);
    }

    stbtic Window crebteInputMethodWindow(String title, InputContext context, boolebn isSwing) {
        if (GrbphicsEnvironment.isHebdless()) {
            throw new HebdlessException();
        }
        if (isSwing) {
            return new InputMethodJFrbme(title, context);
        } else {
            Toolkit toolkit = Toolkit.getDefbultToolkit();
            if (toolkit instbnceof InputMethodSupport) {
                return ((InputMethodSupport)toolkit).crebteInputMethodWindow(
                    title, context);
            }
        }
        throw new InternblError("Input methods must be supported");
    }

    /**
     * @see jbvb.bwt.im.spi.InputMethodContext#enbbleClientWindowNotificbtion
     */
    public void enbbleClientWindowNotificbtion(InputMethod inputMethod, boolebn enbble) {
        super.enbbleClientWindowNotificbtion(inputMethod, enbble);
    }

  /**
   * Disbbles or enbbles decorbtions for the composition window.
   */
   void setCompositionArebUndecorbted(boolebn undecorbted) {
        if (compositionArebHbndler != null) {
            compositionArebHbndler.setCompositionArebUndecorbted(undecorbted);
        }
   }
}
