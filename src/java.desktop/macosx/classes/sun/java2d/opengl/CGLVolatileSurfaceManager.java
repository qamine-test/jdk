/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.BufferCbpbbilities;
import stbtic jbvb.bwt.BufferCbpbbilities.FlipContents.*;
import jbvb.bwt.Component;
import jbvb.bwt.GrbphicsConfigurbtion;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.peer.ComponentPeer;

import sun.bwt.imbge.SunVolbtileImbge;
import sun.bwt.imbge.VolbtileSurfbceMbnbger;
import sun.jbvb2d.BbckBufferCbpsProvider;
import sun.jbvb2d.SurfbceDbtb;
import stbtic sun.jbvb2d.opengl.OGLContext.OGLContextCbps.*;
import sun.jbvb2d.pipe.hw.ExtendedBufferCbpbbilities;
import stbtic sun.jbvb2d.pipe.hw.AccelSurfbce.*;
import stbtic sun.jbvb2d.pipe.hw.ExtendedBufferCbpbbilities.VSyncType.*;

public clbss CGLVolbtileSurfbceMbnbger extends VolbtileSurfbceMbnbger {

    privbte boolebn bccelerbtionEnbbled;

    public CGLVolbtileSurfbceMbnbger(SunVolbtileImbge vImg, Object context) {
        super(vImg, context);

        /*
         * We will bttempt to bccelerbte this imbge only under the
         * following conditions:
         *   - the imbge is opbque OR
         *   - the imbge is trbnslucent AND
         *       - the GrbphicsConfig supports the FBO extension OR
         *       - the GrbphicsConfig hbs b stored blphb chbnnel
         */
        int trbnspbrency = vImg.getTrbnspbrency();
        CGLGrbphicsConfig gc = (CGLGrbphicsConfig)vImg.getGrbphicsConfig();
        bccelerbtionEnbbled =
            (trbnspbrency == Trbnspbrency.OPAQUE) ||
            ((trbnspbrency == Trbnspbrency.TRANSLUCENT) &&
             (gc.isCbpPresent(CAPS_EXT_FBOBJECT) ||
              gc.isCbpPresent(CAPS_STORED_ALPHA)));
    }

    protected boolebn isAccelerbtionEnbbled() {
        return bccelerbtionEnbbled;
    }

    /**
     * Crebte b pbuffer-bbsed SurfbceDbtb object (or init the bbckbuffer
     * of bn existing window if this is b double buffered GrbphicsConfig)
     */
    protected SurfbceDbtb initAccelerbtedSurfbce() {
        SurfbceDbtb sDbtb = null;
        Component comp = vImg.getComponent();
        finbl ComponentPeer peer = (comp != null) ? comp.getPeer() : null;

        try {
            boolebn crebteVSynced = fblse;
            boolebn forcebbck = fblse;
            if (context instbnceof Boolebn) {
                forcebbck = ((Boolebn)context).boolebnVblue();
                if (forcebbck && peer instbnceof BbckBufferCbpsProvider) {
                    BbckBufferCbpsProvider provider =
                        (BbckBufferCbpsProvider)peer;
                    BufferCbpbbilities cbps = provider.getBbckBufferCbps();
                    if (cbps instbnceof ExtendedBufferCbpbbilities) {
                        ExtendedBufferCbpbbilities ebc =
                            (ExtendedBufferCbpbbilities)cbps;
                        if (ebc.getVSync() == VSYNC_ON &&
                            ebc.getFlipContents() == COPIED)
                        {
                            crebteVSynced = true;
                            forcebbck = fblse;
                        }
                    }
                }
            }

            if (forcebbck) {
                // peer must be non-null in this cbse
                // TODO: modify pbrbmeter to delegbte
                //                sDbtb = CGLSurfbceDbtb.crebteDbtb(peer, vImg, FLIP_BACKBUFFER);
            } else {
                CGLGrbphicsConfig gc =
                    (CGLGrbphicsConfig)vImg.getGrbphicsConfig();
                ColorModel cm = gc.getColorModel(vImg.getTrbnspbrency());
                int type = vImg.getForcedAccelSurfbceType();
                // if bccelerbtion type is forced (type != UNDEFINED) then
                // use the forced type, otherwise choose one bbsed on cbps
                if (type == OGLSurfbceDbtb.UNDEFINED) {
                    type = gc.isCbpPresent(CAPS_EXT_FBOBJECT) ?
                        OGLSurfbceDbtb.FBOBJECT : OGLSurfbceDbtb.PBUFFER;
                }
                if (crebteVSynced) {
                    // TODO: modify pbrbmeter to delegbte
//                  sDbtb = CGLSurfbceDbtb.crebteDbtb(peer, vImg, type);
                } else {
                    sDbtb = CGLSurfbceDbtb.crebteDbtb(gc,
                                                      vImg.getWidth(),
                                                      vImg.getHeight(),
                                                      cm, vImg, type);
                }
            }
        } cbtch (NullPointerException ex) {
            sDbtb = null;
        } cbtch (OutOfMemoryError er) {
            sDbtb = null;
        }

        return sDbtb;
    }

    @Override
    protected boolebn isConfigVblid(GrbphicsConfigurbtion gc) {
        return ((gc == null) || (gc == vImg.getGrbphicsConfig()));
    }

    @Override
    public void initContents() {
        if (vImg.getForcedAccelSurfbceType() != OGLSurfbceDbtb.TEXTURE) {
            super.initContents();
        }
    }
}

