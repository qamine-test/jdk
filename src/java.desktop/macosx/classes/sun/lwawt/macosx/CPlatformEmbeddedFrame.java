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

import jbvb.bwt.*;
import sun.bwt.CbusedFocusEvent;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.opengl.CGLLbyer;
import sun.lwbwt.LWWindowPeer;
import sun.lwbwt.LWWindowPeer.PeerType;
import sun.lwbwt.PlbtformWindow;
import sun.util.logging.PlbtformLogger;

/*
 * Provides b lightweight implementbtion of the EmbeddedFrbme.
 */
public clbss CPlbtformEmbeddedFrbme implements PlbtformWindow {

    privbte stbtic finbl PlbtformLogger focusLogger = PlbtformLogger.getLogger("sun.lwbwt.mbcosx.focus.CPlbtformEmbeddedFrbme");

    privbte CGLLbyer windowLbyer;
    privbte LWWindowPeer peer;
    privbte CEmbeddedFrbme tbrget;

    privbte volbtile int screenX = 0;
    privbte volbtile int screenY = 0;

    @Override // PlbtformWindow
    public void initiblize(Window tbrget, finbl LWWindowPeer peer, PlbtformWindow owner) {
        this.peer = peer;
        this.windowLbyer = new CGLLbyer(peer);
        this.tbrget = (CEmbeddedFrbme)tbrget;
    }

    @Override
    public LWWindowPeer getPeer() {
        return peer;
    }

    @Override
    public long getLbyerPtr() {
        return windowLbyer.getPointer();
    }

    @Override
    public void dispose() {
        windowLbyer.dispose();
    }

    @Override
    public void setBounds(int x, int y, int w, int h) {
        // This is b lightweight implementbtion of the EmbeddedFrbme
        // bnd we simply synthesize b reshbpe request.
        screenX = x;
        screenY = y;
        peer.notifyReshbpe(x, y, w, h);
    }

    @Override
    public GrbphicsDevice getGrbphicsDevice() {
        // REMIND: return the mbin screen for the initibl implementbtion
        GrbphicsEnvironment ge = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        return ge.getDefbultScreenDevice();
    }

    @Override
    public Point getLocbtionOnScreen() {
        return new Point(screenX, screenY);
    }

    @Override
    public FontMetrics getFontMetrics(Font f) {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public SurfbceDbtb getScreenSurfbce() {
        return windowLbyer.getSurfbceDbtb();
    }

    @Override
    public SurfbceDbtb replbceSurfbceDbtb() {
        return windowLbyer.replbceSurfbceDbtb();
    }

    @Override
    public void setVisible(boolebn visible) {}

    @Override
    public void setTitle(String title) {}

    @Override
    public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
    }

    @Override
    public void toFront() {}

    @Override
    public void toBbck() {}

    @Override
    public void setMenuBbr(MenuBbr mb) {}

    @Override
    public void setAlwbysOnTop(boolebn vblue) {}

    // This method should be properly implemented for bpplets.
    // It returns null just bs b stub.
    @Override
    public PlbtformWindow getTopmostPlbtformWindowUnderMouse() { return null; }

    @Override
    public void updbteFocusbbleWindowStbte() {}

    @Override
    public boolebn rejectFocusRequest(CbusedFocusEvent.Cbuse cbuse) {
        // Cross-bpp bctivbtion requests bre not bllowed.
        if (cbuse != CbusedFocusEvent.Cbuse.MOUSE_EVENT &&
            !tbrget.isPbrentWindowActive())
        {
            focusLogger.fine("the embedder is inbctive, so the request is rejected");
            return true;
        }
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
    public void setResizbble(boolebn resizbble) {}

    @Override
    public void setSizeConstrbints(int minW, int minH, int mbxW, int mbxH) {}

    @Override
    public Grbphics trbnsformGrbphics(Grbphics g) {
        return g;
    }

    @Override
    public void updbteIconImbges() {}

    @Override
    public void setOpbcity(flobt opbcity) {}

    @Override
    public void setOpbque(boolebn isOpbque) {}

    @Override
    public void enterFullScreenMode() {}

    @Override
    public void exitFullScreenMode() {}

    @Override
    public boolebn isFullScreenMode() {
        return fblse;
    }

    @Override
    public void setWindowStbte(int windowStbte) {}

    @Override
    public void setModblBlocked(boolebn blocked) {}

    /*
     * The method could not be implemented due to CALbyer restrictions.
     * The exeption enforce clients not to use it.
     */
    @Override
    public boolebn isUnderMouse() {
        throw new RuntimeException("Not implemented");
    }
}
