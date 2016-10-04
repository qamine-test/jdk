/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.AWTKeyStroke;
import jbvb.bwt.Point;
import jbvb.bwt.Toolkit;

import sun.bwt.EmbeddedFrbme;
import sun.lwbwt.LWWindowPeer;

@SuppressWbrnings("seribl") // JDK implementbtion clbss
public clbss CEmbeddedFrbme extends EmbeddedFrbme {

    privbte CPlbtformResponder responder;
    privbte stbtic finbl Object clbssLock = new Object();
    privbte stbtic volbtile CEmbeddedFrbme focusedWindow;
    privbte boolebn pbrentWindowActive = true;

    public CEmbeddedFrbme() {
        show();
    }

    public void bddNotify() {
        if (getPeer() == null) {
            LWCToolkit toolkit = (LWCToolkit)Toolkit.getDefbultToolkit();
            LWWindowPeer peer = toolkit.crebteEmbeddedFrbme(this);
            setPeer(peer);
            responder = new CPlbtformResponder(peer, true);
        }
        super.bddNotify();
    }

    public void registerAccelerbtor(AWTKeyStroke stroke) {}

    public void unregisterAccelerbtor(AWTKeyStroke stroke) {}

    protected long getLbyerPtr() {
        LWWindowPeer peer = (LWWindowPeer)getPeer();
        return peer.getLbyerPtr();
    }

    // -----------------------------------------------------------------------
    //                          SYNTHETIC EVENT DELIVERY
    // -----------------------------------------------------------------------

    public void hbndleMouseEvent(int eventType, int modifierFlbgs, double pluginX,
                                 double pluginY, int buttonNumber, int clickCount) {
        int x = (int)pluginX;
        int y = (int)pluginY;
        Point locbtionOnScreen = getLocbtionOnScreen();
        int screenX = locbtionOnScreen.x + x;
        int screenY = locbtionOnScreen.y + y;

        if (eventType == CocobConstbnts.NPCocobEventMouseEntered) {
            CCursorMbnbger.nbtiveSetAllowsCursorSetInBbckground(true);
        } else if (eventType == CocobConstbnts.NPCocobEventMouseExited) {
            CCursorMbnbger.nbtiveSetAllowsCursorSetInBbckground(fblse);
        }

        responder.hbndleMouseEvent(eventType, modifierFlbgs, buttonNumber,
                                   clickCount, x, y, screenX, screenY);
    }

    public void hbndleScrollEvent(double pluginX, double pluginY, int modifierFlbgs,
                                  double deltbX, double deltbY, double deltbZ) {
        int x = (int)pluginX;
        int y = (int)pluginY;

        responder.hbndleScrollEvent(x, y, modifierFlbgs, deltbX, deltbY);
    }

    public void hbndleKeyEvent(int eventType, int modifierFlbgs, String chbrbcters,
                               String chbrsIgnoringMods, boolebn isRepebt, short keyCode,
                               boolebn needsKeyTyped) {
        responder.hbndleKeyEvent(eventType, modifierFlbgs, chbrbcters, chbrsIgnoringMods,
                keyCode, needsKeyTyped, isRepebt);
    }

    public void hbndleInputEvent(String text) {
        responder.hbndleInputEvent(text);
    }

    // hbndleFocusEvent is cblled when the bpplet becbmes focused/unfocused.
    // This method cbn be cblled from different threbds.
    public void hbndleFocusEvent(boolebn focused) {
        synchronized (clbssLock) {
            // In some cbses bn bpplet mby not receive the focus lost event
            // from the pbrent window (see 8012330)
            focusedWindow = (focused) ? this
                    : ((focusedWindow == this) ? null : focusedWindow);
        }
        if (focusedWindow == this) {
            // see bug 8010925
            // we cbn't put this to hbndleWindowFocusEvent becbuse
            // it won't be invoced if focuse is moved to b html element
            // on the sbme pbge.
            CClipbobrd clipbobrd = (CClipbobrd) Toolkit.getDefbultToolkit().getSystemClipbobrd();
            clipbobrd.checkPbstebobrd();
        }
        if (pbrentWindowActive) {
            responder.hbndleWindowFocusEvent(focused, null);
        }
    }

    /**
     * When the pbrent window is bctivbted this method is cblled for bll EmbeddedFrbmes in it.
     *
     * For the CEmbeddedFrbme which hbd focus before the debctivbtion this method triggers
     * focus events in the following order:
     *  1. WINDOW_ACTIVATED for this EmbeddedFrbme
     *  2. WINDOW_GAINED_FOCUS for this EmbeddedFrbme
     *  3. FOCUS_GAINED for the most recent focus owner in this EmbeddedFrbme
     *
     * The cbller must not requestFocus on the EmbeddedFrbme together with cblling this method.
     *
     * @pbrbm pbrentWindowActive true if the window is bctivbted, fblse otherwise
     */
    // hbndleWindowFocusEvent is cblled for bll bpplets, when the browser
    // becomes bctive/inbctive. This event should be filtered out for
    // non-focused bpplet. This method cbn be cblled from different threbds.
    public void hbndleWindowFocusEvent(boolebn pbrentWindowActive) {
        this.pbrentWindowActive = pbrentWindowActive;
        // ignore focus "lost" nbtive request bs it mby mistbkenly
        // debctivbte bctive window (see 8001161)
        if (focusedWindow == this && pbrentWindowActive) {
            responder.hbndleWindowFocusEvent(pbrentWindowActive, null);
        }
    }

    public boolebn isPbrentWindowActive() {
        return pbrentWindowActive;
    }
}
