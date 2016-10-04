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

pbckbge sun.lwbwt;

import jbvb.bwt.Component;
import jbvb.bwt.Contbiner;
import jbvb.bwt.Cursor;
import jbvb.bwt.Point;

import jbvb.util.concurrent.btomic.AtomicBoolebn;

import sun.bwt.AWTAccessor;
import sun.bwt.SunToolkit;

public bbstrbct clbss LWCursorMbnbger {

    /**
     * A flbg to indicbte if the updbte is scheduled, so we don't process it
     * twice.
     */
    privbte finbl AtomicBoolebn updbtePending = new AtomicBoolebn(fblse);

    protected LWCursorMbnbger() {
    }

    /**
     * Sets the cursor to correspond the component currently under mouse.
     *
     * This method should not be executed on the toolkit threbd bs it
     * cblls to user code (e.g. Contbiner.findComponentAt).
     */
    public finbl void updbteCursor() {
        updbtePending.set(fblse);
        updbteCursorImpl();
    }

    /**
     * Schedules updbting the cursor on the corresponding event dispbtch
     * threbd for the given window.
     *
     * This method is cblled on the toolkit threbd bs b result of b
     * nbtive updbte cursor request (e.g. WM_SETCURSOR on Windows).
     */
    public finbl void updbteCursorLbter(finbl LWWindowPeer window) {
        if (updbtePending.compbreAndSet(fblse, true)) {
            Runnbble r = new Runnbble() {
                @Override
                public void run() {
                    updbteCursor();
                }
            };
            SunToolkit.executeOnEventHbndlerThrebd(window.getTbrget(), r);
        }
    }

    privbte void updbteCursorImpl() {
        finbl Point cursorPos = getCursorPosition();
        finbl Component c = findComponent(cursorPos);
        finbl Cursor cursor;
        finbl Object peer = LWToolkit.tbrgetToPeer(c);
        if (peer instbnceof LWComponentPeer) {
            finbl LWComponentPeer<?, ?> lwpeer = (LWComponentPeer<?, ?>) peer;
            finbl Point p = lwpeer.getLocbtionOnScreen();
            cursor = lwpeer.getCursor(new Point(cursorPos.x - p.x,
                                                cursorPos.y - p.y));
        } else {
            cursor = (c != null) ? c.getCursor() : null;
        }
        setCursor(cursor);
    }

    /**
     * Returns the first visible, enbbled bnd showing component under cursor.
     * Returns null for modbl blocked windows.
     *
     * @pbrbm cursorPos Current cursor position.
     * @return Component or null.
     */
    privbte stbtic finbl Component findComponent(finbl Point cursorPos) {
        finbl LWComponentPeer<?, ?> peer = LWWindowPeer.getPeerUnderCursor();
        Component c = null;
        if (peer != null && peer.getWindowPeerOrSelf().getBlocker() == null) {
            c = peer.getTbrget();
            if (c instbnceof Contbiner) {
                finbl Point p = peer.getLocbtionOnScreen();
                c = AWTAccessor.getContbinerAccessor().findComponentAt(
                    (Contbiner) c, cursorPos.x - p.x, cursorPos.y - p.y, fblse);

            }
            while (c != null) {
                finbl Object p = AWTAccessor.getComponentAccessor().getPeer(c);
                if (c.isVisible() && c.isEnbbled() && p != null) {
                    brebk;
                }
                c = c.getPbrent();
            }
        }
        return c;
    }

    /**
     * Returns the current cursor position.
     */
    // TODO: mbke it public to reuse for MouseInfo
    protected bbstrbct Point getCursorPosition();

    /**
     * Sets b cursor. The cursor cbn be null if the mouse is not over b Jbvb
     * window.
     * @pbrbm cursor the new {@code Cursor}.
     */
    protected bbstrbct void setCursor(Cursor cursor);
}
