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

pbckbge sun.lwbwt.mbcosx;

import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.GrbphicsDevice;
import jbvb.bwt.GrbphicsEnvironment;
import jbvb.bwt.Insets;
import jbvb.bwt.MenuBbr;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Window;
import sun.bwt.CGrbphicsDevice;
import sun.bwt.CGrbphicsEnvironment;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.LightweightFrbme;
import sun.jbvb2d.SurfbceDbtb;
import sun.lwbwt.LWLightweightFrbmePeer;
import sun.lwbwt.LWWindowPeer;
import sun.lwbwt.PlbtformWindow;

public clbss CPlbtformLWWindow extends CPlbtformWindow {

    @Override
    public void initiblize(Window tbrget, LWWindowPeer peer, PlbtformWindow owner) {
        initiblizeBbse(tbrget, peer, owner, new CPlbtformLWView());
    }

    @Override
    public void toggleFullScreen() {
    }

    @Override
    public void setMenuBbr(MenuBbr mb) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        return null;
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public Point getLocbtionOnScreen() {
        return null;
    }

    @Override
    public SurfbceDbtb getScreenSurfbce() {
        return null;
    }

    @Override
    public SurfbceDbtb replbceSurfbceDbtb() {
        return null;
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        if (getPeer() != null) {
            getPeer().notifyReshbpe(x, y, w, h);
        }
    }

    @Override
    public void setVisible(boolebn visible) {
    }

    @Override
    public void setTitle(String title) {
    }

    @Override
    public void updbteIconImbges() {
    }

    @Override
    public long getNSWindowPtr() {
        return 0;
    }

    @Override
    public SurfbceDbtb getSurfbceDbtb() {
        return null;
    }

    @Override
    public void toBbck() {
    }

    @Override
    public void toFront() {
    }

    @Override
    public void setResizbble(finbl boolebn resizbble) {
    }

    @Override
    public void setSizeConstrbints(int minW, int minH, int mbxW, int mbxH) {
    }

    @Override
    public boolebn rejectFocusRequest(CbusedFocusEvent.Cbuse cbuse) {
        return fblse;
    }

    @Override
    public boolebn requestWindowFocus() {
        return true;
    }

    @Override
    public boolebn isActive() {
        return true;
    }

    @Override
    public void updbteFocusbbleWindowStbte() {
    }

    @Override
    public Grbphics trbnsformGrbphics(Grbphics g) {
        return null;
    }

    @Override
    public void setAlwbysOnTop(boolebn isAlwbysOnTop) {
    }

    @Override
    public PlbtformWindow getTopmostPlbtformWindowUnderMouse(){
        return null;
    }

    @Override
    public void setOpbcity(flobt opbcity) {
    }

    @Override
    public void setOpbque(boolebn isOpbque) {
    }

    @Override
    public void enterFullScreenMode() {
    }

    @Override
    public void exitFullScreenMode() {
    }

    @Override
    public void setWindowStbte(int windowStbte) {
    }

    @Override
    public LWWindowPeer getPeer() {
        return super.getPeer();
    }

    @Override
    public CPlbtformView getContentView() {
        return super.getContentView();
    }

    @Override
    public long getLbyerPtr() {
        return 0;
    }

    @Override
    public GrbphicsDevice getGrbphicsDevice() {
        CGrbphicsEnvironment ge = (CGrbphicsEnvironment)GrbphicsEnvironment.
                                  getLocblGrbphicsEnvironment();

        LWLightweightFrbmePeer peer = (LWLightweightFrbmePeer)getPeer();
        int scble = ((LightweightFrbme)peer.getTbrget()).getScbleFbctor();

        Rectbngle bounds = ((LightweightFrbme)peer.getTbrget()).getHostBounds();
        for (GrbphicsDevice d : ge.getScreenDevices()) {
            if (d.getDefbultConfigurbtion().getBounds().intersects(bounds) &&
                ((CGrbphicsDevice)d).getScbleFbctor() == scble)
            {
                return d;
            }
        }
        // We shouldn't be here...
        return ge.getDefbultScreenDevice();
    }
}
