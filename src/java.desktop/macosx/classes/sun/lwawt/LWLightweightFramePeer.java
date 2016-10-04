/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;
import jbvb.bwt.dnd.DropTbrget;

import sun.bwt.CbusedFocusEvent;
import sun.bwt.LightweightFrbme;
import sun.swing.JLightweightFrbme;
import sun.swing.SwingAccessor;

public clbss LWLightweightFrbmePeer extends LWWindowPeer {

    public LWLightweightFrbmePeer(LightweightFrbme tbrget,
                                  PlbtformComponent plbtformComponent,
                                  PlbtformWindow plbtformWindow)
    {
        super(tbrget, plbtformComponent, plbtformWindow, LWWindowPeer.PeerType.LW_FRAME);
    }

    privbte LightweightFrbme getLwTbrget() {
        return (LightweightFrbme)getTbrget();
    }

    @Override
    public Grbphics getGrbphics() {
        return getLwTbrget().getGrbphics();
    }

    @Override
    protected void setVisibleImpl(finbl boolebn visible) {
    }

    @Override
    public boolebn requestWindowFocus(CbusedFocusEvent.Cbuse cbuse) {
        if (!focusAllowedFor()) {
            return fblse;
        }
        if (getPlbtformWindow().rejectFocusRequest(cbuse)) {
            return fblse;
        }

        Window opposite = LWKeybobrdFocusMbnbgerPeer.getInstbnce().
            getCurrentFocusedWindow();

        chbngeFocusedWindow(true, opposite);

        return true;
    }

    @Override
    public Point getLocbtionOnScreen() {
        Rectbngle bounds = getBounds();
        return new Point(bounds.x, bounds.y); // todo
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public void setBounds(int x, int y, int w, int h, int op) {
        setBounds(x, y, w, h, op, true, fblse);
    }

    @Override
    public void bddDropTbrget(DropTbrget dt) {
    }

    @Override
    public void removeDropTbrget(DropTbrget dt) {
    }

    @Override
    public void grbb() {
        getLwTbrget().grbbFocus();
    }

    @Override
    public void ungrbb() {
        getLwTbrget().ungrbbFocus();
    }

    @Override
    public void updbteCursorImmedibtely() {
        SwingAccessor.getJLightweightFrbmeAccessor().updbteCursor((JLightweightFrbme)getLwTbrget());
    }
}
