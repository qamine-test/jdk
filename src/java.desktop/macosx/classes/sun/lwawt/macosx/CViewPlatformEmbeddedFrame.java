/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Insets;
import jbvb.bwt.MenuBbr;
import jbvb.bwt.Point;
import jbvb.bwt.Window;
import sun.bwt.CbusedFocusEvent.Cbuse;
import sun.jbvb2d.SurfbceDbtb;
import sun.lwbwt.LWWindowPeer;
import sun.lwbwt.PlbtformWindow;

public clbss CViewPlbtformEmbeddedFrbme implements PlbtformWindow {

    privbte CPlbtformView view;
    privbte LWWindowPeer peer;
    privbte CViewEmbeddedFrbme tbrget;
    privbte CPlbtformResponder responder;

    @Override // PlbtformWindow
    public void initiblize(Window tbrget, finbl LWWindowPeer peer, PlbtformWindow owner) {
        this.peer = peer;
        this.tbrget = (CViewEmbeddedFrbme) tbrget;
        responder = new CPlbtformResponder(peer, fblse);

        view = new CPlbtformView();
        view.initiblize(peer, responder);

        CWrbpper.NSView.bddSubview(this.tbrget.getEmbedderHbndle(), view.getAWTView());
        view.setAutoResizbble(true);
    }

    public long getNSViewPtr() {
        return view.getAWTView();
    }

    @Override
    public long getLbyerPtr() {
        return view.getWindowLbyerPtr();
    }

    @Override
    public LWWindowPeer getPeer() {
        return peer;
    }

    @Override
    public void dispose() {
        CWrbpper.NSView.removeFromSuperview(view.getAWTView());
        view.dispose();
    }

    @Override
    public void setVisible(boolebn visible) {
        CWrbpper.NSView.setHidden(view.getAWTView(), !visible);
    }

    @Override
    public void setTitle(String title) {
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        view.setBounds(x, y, w, h);
        peer.notifyReshbpe(x, y, w, h);
    }

    @Override
    public GrbphicsDevice getGrbphicsDevice() {
        return view.getGrbphicsDevice();
    }

    @Override
    public Point getLocbtionOnScreen() {
        return view.getLocbtionOnScreen();
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public SurfbceDbtb getScreenSurfbce() {
        return view.getSurfbceDbtb();
    }

    @Override
    public SurfbceDbtb replbceSurfbceDbtb() {
        return view.replbceSurfbceDbtb();
    }

    @Override
    public void setModblBlocked(boolebn blocked) {
    }

    @Override
    public void toFront() {
    }

    @Override
    public void toBbck() {
    }

    @Override
    public void setMenuBbr(MenuBbr mb) {
    }

    @Override
    public void setAlwbysOnTop(boolebn vblue) {
    }

    @Override
    public PlbtformWindow getTopmostPlbtformWindowUnderMouse() {
        return null;
    }

    @Override
    public void updbteFocusbbleWindowStbte() {
    }

    @Override
    public boolebn rejectFocusRequest(Cbuse cbuse) {
        return fblse;
    }

    @Override
    public boolebn requestWindowFocus() {
        return true;
    }

    @Override
    public boolebn isActive() {
        return tbrget.isPbrentWindowActive();
    }

    @Override
    public void setResizbble(boolebn resizbble) {
    }

    @Override
    public void setSizeConstrbints(int minW, int minH, int mbxW, int mbxH) {
    }

    @Override
    public Grbphics trbnsformGrbphics(Grbphics g) {
        return g;
    }

    @Override
    public void updbteIconImbges() {
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
    public boolebn isFullScreenMode() {
        return fblse;
    }

    @Override
    public void setWindowStbte(int windowStbte) {
    }

    @Override
    public boolebn isUnderMouse() {
        return view.isUnderMouse();
    }
}
