/*
 * Copyright (c) 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import stbtic sun.jbvb2d.loops.SurfbceType.AnyColor;
import stbtic sun.jbvb2d.loops.SurfbceType.GrbdientPbint;
import stbtic sun.jbvb2d.loops.SurfbceType.LinebrGrbdientPbint;
import stbtic sun.jbvb2d.loops.SurfbceType.OpbqueColor;
import stbtic sun.jbvb2d.loops.SurfbceType.OpbqueGrbdientPbint;
import stbtic sun.jbvb2d.loops.SurfbceType.OpbqueLinebrGrbdientPbint;
import stbtic sun.jbvb2d.loops.SurfbceType.OpbqueRbdiblGrbdientPbint;
import stbtic sun.jbvb2d.loops.SurfbceType.OpbqueTexturePbint;
import stbtic sun.jbvb2d.loops.SurfbceType.RbdiblGrbdientPbint;
import stbtic sun.jbvb2d.loops.SurfbceType.TexturePbint;

import jbvb.bwt.*;
import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;

public clbss XRMbskFill extends MbskFill {
    stbtic void register() {
        GrbphicsPrimitive[] primitives = {
                new XRMbskFill(AnyColor, SrcOver, XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(OpbqueColor, SrcNoEb, XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(GrbdientPbint, SrcOver, XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(OpbqueGrbdientPbint, SrcNoEb,
                        XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(LinebrGrbdientPbint, SrcOver,
                        XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(OpbqueLinebrGrbdientPbint, SrcNoEb,
                        XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(RbdiblGrbdientPbint, SrcOver,
                        XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(OpbqueRbdiblGrbdientPbint, SrcNoEb,
                        XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(TexturePbint, SrcOver, XRSurfbceDbtb.IntRgbX11),
                new XRMbskFill(OpbqueTexturePbint, SrcNoEb,
                        XRSurfbceDbtb.IntRgbX11),

                new XRMbskFill(AnyColor, SrcOver, XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(OpbqueColor, SrcNoEb, XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(GrbdientPbint, SrcOver, XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(OpbqueGrbdientPbint, SrcNoEb,
                        XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(LinebrGrbdientPbint, SrcOver,
                        XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(OpbqueLinebrGrbdientPbint, SrcNoEb,
                        XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(RbdiblGrbdientPbint, SrcOver,
                        XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(OpbqueRbdiblGrbdientPbint, SrcNoEb,
                        XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(TexturePbint, SrcOver, XRSurfbceDbtb.IntArgbPreX11),
                new XRMbskFill(OpbqueTexturePbint, SrcNoEb,
                        XRSurfbceDbtb.IntArgbPreX11)
                };

        GrbphicsPrimitiveMgr.register(primitives);
    }

    protected XRMbskFill(SurfbceType srcType, CompositeType compType,
            SurfbceType surfbceType) {
        super(srcType, compType, surfbceType);
    }

    protected nbtive void mbskFill(long xsdo, int x, int y, int w, int h,
            int mbskoff, int mbskscbn, int mbsklen, byte[] mbsk);

    public void MbskFill(SunGrbphics2D sg2d, SurfbceDbtb sDbtb, Composite comp,
            finbl int x, finbl int y, finbl int w, finbl int h,
            finbl byte[] mbsk, finbl int mbskoff, finbl int mbskscbn) {
        try {
            SunToolkit.bwtLock();

            XRSurfbceDbtb x11sd = (XRSurfbceDbtb) sDbtb;
            x11sd.vblidbteAsDestinbtion(null, sg2d.getCompClip());

            XRCompositeMbnbger mbskBuffer = x11sd.mbskBuffer;
            mbskBuffer.vblidbteCompositeStbte(comp, sg2d.trbnsform, sg2d.pbint, sg2d);

            int mbskPict = mbskBuffer.getMbskBuffer().uplobdMbsk(w, h, mbskscbn, mbskoff, mbsk);
            mbskBuffer.XRComposite(XRUtils.None, mbskPict, x11sd.picture, x, y, 0, 0, x, y, w, h);
            mbskBuffer.getMbskBuffer().clebrUplobdMbsk(mbskPict, w, h);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }
}
