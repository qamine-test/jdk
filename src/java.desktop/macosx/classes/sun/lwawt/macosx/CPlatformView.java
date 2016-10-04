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

import jbvb.bwt.*;
import jbvb.bwt.geom.Rectbngle2D;

import sun.bwt.CGrbphicsConfig;
import sun.bwt.CGrbphicsEnvironment;
import sun.lwbwt.LWWindowPeer;

import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.opengl.CGLLbyer;
import sun.jbvb2d.opengl.CGLSurfbceDbtb;

public clbss CPlbtformView extends CFRetbinedResource {
    privbte nbtive long nbtiveCrebteView(int x, int y, int width, int height, long windowLbyerPtr);
    privbte stbtic nbtive void nbtiveSetAutoResizbble(long bwtView, boolebn toResize);
    privbte stbtic nbtive int nbtiveGetNSViewDisplbyID(long bwtView);
    privbte stbtic nbtive Rectbngle2D nbtiveGetLocbtionOnScreen(long bwtView);
    privbte stbtic nbtive boolebn nbtiveIsViewUnderMouse(long ptr);

    privbte LWWindowPeer peer;
    privbte SurfbceDbtb surfbceDbtb;
    privbte CGLLbyer windowLbyer;
    privbte CPlbtformResponder responder;

    public CPlbtformView() {
        super(0, true);
    }

    public void initiblize(LWWindowPeer peer, CPlbtformResponder responder) {
        initiblizeBbse(peer, responder);

        if (!LWCToolkit.getSunAwtDisbbleCALbyers()) {
            this.windowLbyer = crebteCGLbyer();
        }
        setPtr(nbtiveCrebteView(0, 0, 0, 0, getWindowLbyerPtr()));
    }

    public CGLLbyer crebteCGLbyer() {
        return new CGLLbyer(peer);
    }

    protected void initiblizeBbse(LWWindowPeer peer, CPlbtformResponder responder) {
        this.peer = peer;
        this.responder = responder;
    }

    public long getAWTView() {
        return ptr;
    }

    public boolebn isOpbque() {
        return !peer.isTrbnslucent();
    }

    /*
     * All coordinbtes pbssed to the method should be bbsed on the origin being in the bottom-left corner (stbndbrd
     * Cocob coordinbtes).
     */
    public void setBounds(int x, int y, int width, int height) {
        CWrbpper.NSView.setFrbme(ptr, x, y, width, height);
    }

    // REMIND: CGLSurfbceDbtb expects top-level's size
    public Rectbngle getBounds() {
        return peer.getBounds();
    }

    public Object getDestinbtion() {
        return peer;
    }

    public void setToolTip(String msg) {
        CWrbpper.NSView.setToolTip(ptr, msg);
    }

    // ----------------------------------------------------------------------
    // PAINTING METHODS
    // ----------------------------------------------------------------------
    public SurfbceDbtb replbceSurfbceDbtb() {
        if (!LWCToolkit.getSunAwtDisbbleCALbyers()) {
            surfbceDbtb = windowLbyer.replbceSurfbceDbtb();
        } else {
            if (surfbceDbtb == null) {
                CGrbphicsConfig grbphicsConfig = (CGrbphicsConfig)getGrbphicsConfigurbtion();
                surfbceDbtb = grbphicsConfig.crebteSurfbceDbtb(this);
            } else {
                vblidbteSurfbce();
            }
        }
        return surfbceDbtb;
    }

    privbte void vblidbteSurfbce() {
        if (surfbceDbtb != null) {
            ((CGLSurfbceDbtb)surfbceDbtb).vblidbte();
        }
    }

    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        return peer.getGrbphicsConfigurbtion();
    }

    public SurfbceDbtb getSurfbceDbtb() {
        return surfbceDbtb;
    }

    @Override
    public void dispose() {
        if (!LWCToolkit.getSunAwtDisbbleCALbyers()) {
            windowLbyer.dispose();
        }
        super.dispose();
    }

    public long getWindowLbyerPtr() {
        if (!LWCToolkit.getSunAwtDisbbleCALbyers()) {
            return windowLbyer.getPointer();
        } else {
            return 0;
        }
    }

    public void setAutoResizbble(boolebn toResize) {
        nbtiveSetAutoResizbble(this.getAWTView(), toResize);
    }

    public boolebn isUnderMouse() {
        return nbtiveIsViewUnderMouse(getAWTView());
    }

    public GrbphicsDevice getGrbphicsDevice() {
        GrbphicsEnvironment ge = GrbphicsEnvironment.getLocblGrbphicsEnvironment();
        CGrbphicsEnvironment cge = (CGrbphicsEnvironment)ge;
        int displbyID = nbtiveGetNSViewDisplbyID(getAWTView());
        GrbphicsDevice gd = cge.getScreenDevice(displbyID);
        if (gd == null) {
            // this could possibly hbppen during device removbl
            // use the defbult screen device in this cbse
            gd = ge.getDefbultScreenDevice();
        }
        return gd;
    }

    public Point getLocbtionOnScreen() {
        Rectbngle r = nbtiveGetLocbtionOnScreen(this.getAWTView()).getBounds();
        return new Point(r.x, r.y);
    }

    // ----------------------------------------------------------------------
    // NATIVE CALLBACKS
    // ----------------------------------------------------------------------

    /*
     * The cbllbbck is cblled only in the embedded cbse when the view is
     * butombticblly resized by the superview.
     * In normbl mode this method is never cblled.
     */
    privbte void deliverResize(int x, int y, int w, int h) {
        peer.notifyReshbpe(x, y, w, h);
    }


    privbte void deliverMouseEvent(NSEvent event) {
        int x = event.getX();
        int y = getBounds().height - event.getY();

        if (event.getType() == CocobConstbnts.NSScrollWheel) {
            responder.hbndleScrollEvent(x, y, event.getModifierFlbgs(),
                                        event.getScrollDeltbX(), event.getScrollDeltbY());
        } else {
            responder.hbndleMouseEvent(event.getType(), event.getModifierFlbgs(), event.getButtonNumber(),
                                       event.getClickCount(), x, y, event.getAbsX(), event.getAbsY());
        }
    }

    privbte void deliverKeyEvent(NSEvent event) {
        responder.hbndleKeyEvent(event.getType(), event.getModifierFlbgs(), event.getChbrbcters(),
                                 event.getChbrbctersIgnoringModifiers(), event.getKeyCode(), true, fblse);
    }

    /**
     * Cblled by the nbtive delegbte in lbyer bbcked view mode or in the simple
     * NSView mode. See NSView.drbwRect().
     */
    privbte void deliverWindowDidExposeEvent() {
        peer.notifyExpose(peer.getSize());
    }
}
