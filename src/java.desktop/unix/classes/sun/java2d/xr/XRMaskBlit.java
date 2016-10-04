/*
 * Copyright (c) 2010, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.xr;

import stbtic sun.jbvb2d.loops.CompositeType.SrcNoEb;
import stbtic sun.jbvb2d.loops.CompositeType.SrcOver;

import jbvb.bwt.Composite;

import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.Region;

/**
 * For XRender there is no "blit", everything is just b fill with Repebt or Not.
 * So bbsicblly this just quite the sbme bs MbskFill.
 *
 * @buthor Clemens Eisserer
 */
public clbss XRMbskBlit extends MbskBlit {
    stbtic void register() {
        GrbphicsPrimitive[] primitives = {
                new XRMbskBlit(XRSurfbceDbtb.IntArgbPreX11, SrcOver,
                               XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskBlit(XRSurfbceDbtb.IntRgbX11, SrcOver,
                               XRSurfbceDbtb.IntRgbX11),
                new XRMbskBlit(XRSurfbceDbtb.IntArgbPreX11, SrcNoEb,
                               XRSurfbceDbtb.IntRgbX11),
                new XRMbskBlit(XRSurfbceDbtb.IntRgbX11, SrcNoEb,
                               XRSurfbceDbtb.IntArgbPreX11)
                };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    public XRMbskBlit(SurfbceType srcType, CompositeType compType,
            SurfbceType dstType) {
        super(srcType, CompositeType.AnyAlphb, dstType);
    }

    protected nbtive void mbskBlit(long srcXsdo, long dstxsdo, int srcx,
            int srcy, int dstx, int dsty, int w, int h, int mbskoff,
            int mbskscbn, int mbsklen, byte[] mbsk);

    public void MbskBlit(SurfbceDbtb src, SurfbceDbtb dst, Composite comp,
            Region clip, int srcx, int srcy, int dstx, int dsty, int width,
            int height, byte[] mbsk, int mbskoff, int mbskscbn) {
        if (width <= 0 || height <= 0) {
            return;
        }

        try {
            SunToolkit.bwtLock();

            XRSurfbceDbtb x11sd = (XRSurfbceDbtb) src;
            x11sd.vblidbteAsSource(null, XRUtils.RepebtNone, XRUtils.FAST);

            XRCompositeMbnbger mbskBuffer = x11sd.mbskBuffer;
            XRSurfbceDbtb x11dst = (XRSurfbceDbtb) dst;
            x11dst.vblidbteAsDestinbtion(null, clip);

            int mbskPict = mbskBuffer.getMbskBuffer().
                         uplobdMbsk(width, height, mbskscbn, mbskoff, mbsk);
            mbskBuffer.XRComposite(x11sd.getPicture(), mbskPict, x11dst.getPicture(),
                                  srcx, srcy, 0, 0, dstx, dsty, width, height);
            mbskBuffer.getMbskBuffer().clebrUplobdMbsk(mbskPict, width, height);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }
}
