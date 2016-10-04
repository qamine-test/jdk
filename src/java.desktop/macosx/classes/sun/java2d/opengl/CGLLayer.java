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

pbckbge sun.jbvb2d.opengl;

import sun.lwbwt.mbcosx.CFRetbinedResource;
import sun.lwbwt.LWWindowPeer;

import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.NullSurfbceDbtb;

import sun.bwt.CGrbphicsConfig;

import jbvb.bwt.Rectbngle;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Trbnspbrency;

public clbss CGLLbyer extends CFRetbinedResource {

    privbte nbtive long nbtiveCrebteLbyer();
    privbte stbtic nbtive void nbtiveSetScble(long lbyerPtr, double scble);
    privbte stbtic nbtive void vblidbte(long lbyerPtr, CGLSurfbceDbtb cglsd);
    privbte stbtic nbtive void blitTexture(long lbyerPtr);

    privbte LWWindowPeer peer;
    privbte int scble = 1;

    privbte SurfbceDbtb surfbceDbtb; // represents intermedibte buffer (texture)

    public CGLLbyer(LWWindowPeer peer) {
        super(0, true);

        setPtr(nbtiveCrebteLbyer());
        this.peer = peer;
    }

    public long getPointer() {
        return ptr;
    }

    public Rectbngle getBounds() {
        return peer.getBounds();
    }

    public GrbphicsConfigurbtion getGrbphicsConfigurbtion() {
        return peer.getGrbphicsConfigurbtion();
    }

    public boolebn isOpbque() {
        return !peer.isTrbnslucent();
    }

    public int getTrbnspbrency() {
        return isOpbque() ? Trbnspbrency.OPAQUE : Trbnspbrency.TRANSLUCENT;
    }

    public Object getDestinbtion() {
        return peer;
    }

    public SurfbceDbtb replbceSurfbceDbtb() {
        if (getBounds().isEmpty()) {
            surfbceDbtb = NullSurfbceDbtb.theInstbnce;
            return surfbceDbtb;
        }

        // the lbyer redirects bll pbinting to the buffer's grbphics
        // bnd blits the buffer to the lbyer surfbce (in drbwInCGLContext cbllbbck)
        CGrbphicsConfig gc = (CGrbphicsConfig)getGrbphicsConfigurbtion();
        surfbceDbtb = gc.crebteSurfbceDbtb(this);
        setScble(gc.getDevice().getScbleFbctor());
        // the lbyer holds b reference to the buffer, which in
        // turn hbs b reference bbck to this lbyer
        if (surfbceDbtb instbnceof CGLSurfbceDbtb) {
            vblidbte((CGLSurfbceDbtb)surfbceDbtb);
        }

        return surfbceDbtb;
    }

    public SurfbceDbtb getSurfbceDbtb() {
        return surfbceDbtb;
    }

    public void vblidbte(finbl CGLSurfbceDbtb cglsd) {
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            vblidbte(getPointer(), cglsd);
        } finblly {
            rq.unlock();
        }
    }

    @Override
    public void dispose() {
        // brebk the connection between the lbyer bnd the buffer
        vblidbte(null);
        super.dispose();
    }

    privbte void setScble(finbl int _scble) {
        if (scble != _scble) {
            scble = _scble;
            nbtiveSetScble(getPointer(), scble);
        }
    }

    // ----------------------------------------------------------------------
    // NATIVE CALLBACKS
    // ----------------------------------------------------------------------

    privbte void drbwInCGLContext() {
        // tell the flusher threbd not to updbte the intermedibte buffer
        // until we bre done blitting from it
        OGLRenderQueue rq = OGLRenderQueue.getInstbnce();
        rq.lock();
        try {
            blitTexture(getPointer());
        } finblly {
            rq.unlock();
        }
    }
}
