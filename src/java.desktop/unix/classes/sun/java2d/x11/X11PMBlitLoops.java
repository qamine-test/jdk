/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.x11;

import sun.bwt.SunToolkit;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.GrbphicsPrimitiveMgr;
import sun.jbvb2d.loops.GrbphicsPrimitiveProxy;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.MbskBlit;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.SurfbceDbtb;
import jbvb.bwt.Composite;
import jbvb.bwt.imbge.IndexColorModel;

/**
 * X11PMBlitLoops
 *
 * This clbss bccelerbtes Blits between two surfbces of types *PM.  Since
 * the onscreen surfbce is of thbt type bnd some of the offscreen surfbces
 * mby be of thbt type (if they were crebted vib X11OffScreenImbge), then
 * this type of Blit will bccelerbted double-buffer copies between those
 * two surfbces.
*/
public clbss X11PMBlitLoops extends Blit {

    public stbtic void register()
    {
        GrbphicsPrimitive[] primitives = {
            new X11PMBlitLoops(X11SurfbceDbtb.IntBgrX11,
                               X11SurfbceDbtb.IntBgrX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.IntRgbX11,
                               X11SurfbceDbtb.IntRgbX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.ThreeByteBgrX11,
                               X11SurfbceDbtb.ThreeByteBgrX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.ThreeByteRgbX11,
                               X11SurfbceDbtb.ThreeByteRgbX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.ByteIndexedOpbqueX11,
                               X11SurfbceDbtb.ByteIndexedOpbqueX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.ByteGrbyX11,
                               X11SurfbceDbtb.ByteGrbyX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.Index8GrbyX11,
                               X11SurfbceDbtb.Index8GrbyX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.UShort555RgbX11,
                               X11SurfbceDbtb.UShort555RgbX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.UShort565RgbX11,
                               X11SurfbceDbtb.UShort565RgbX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.UShortIndexedX11,
                               X11SurfbceDbtb.UShortIndexedX11, fblse),

            // 1-bit trbnspbrent to opbque loops
            new X11PMBlitLoops(X11SurfbceDbtb.IntBgrX11_BM,
                               X11SurfbceDbtb.IntBgrX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.IntRgbX11_BM,
                               X11SurfbceDbtb.IntRgbX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.ThreeByteBgrX11_BM,
                               X11SurfbceDbtb.ThreeByteBgrX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.ThreeByteRgbX11_BM,
                               X11SurfbceDbtb.ThreeByteRgbX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.ByteIndexedX11_BM,
                               X11SurfbceDbtb.ByteIndexedOpbqueX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.ByteGrbyX11_BM,
                               X11SurfbceDbtb.ByteGrbyX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.Index8GrbyX11_BM,
                               X11SurfbceDbtb.Index8GrbyX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.UShort555RgbX11_BM,
                               X11SurfbceDbtb.UShort555RgbX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.UShort565RgbX11_BM,
                               X11SurfbceDbtb.UShort565RgbX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.UShortIndexedX11_BM,
                               X11SurfbceDbtb.UShortIndexedX11, true),

            new X11PMBlitLoops(X11SurfbceDbtb.IntRgbX11,
                               X11SurfbceDbtb.IntArgbPreX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.IntRgbX11,
                               X11SurfbceDbtb.IntArgbPreX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.IntRgbX11_BM,
                               X11SurfbceDbtb.IntArgbPreX11, true),

            new X11PMBlitLoops(X11SurfbceDbtb.IntBgrX11,
                               X11SurfbceDbtb.FourByteAbgrPreX11, true),
            new X11PMBlitLoops(X11SurfbceDbtb.IntBgrX11,
                               X11SurfbceDbtb.FourByteAbgrPreX11, fblse),
            new X11PMBlitLoops(X11SurfbceDbtb.IntBgrX11_BM,
                               X11SurfbceDbtb.FourByteAbgrPreX11, true),



            // delegbte loops
            new DelegbteBlitLoop(X11SurfbceDbtb.IntBgrX11_BM,
                                 X11SurfbceDbtb.IntBgrX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.IntRgbX11_BM,
                                 X11SurfbceDbtb.IntRgbX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.ThreeByteBgrX11_BM,
                                 X11SurfbceDbtb.ThreeByteBgrX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.ThreeByteRgbX11_BM,
                                 X11SurfbceDbtb.ThreeByteRgbX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.ByteIndexedX11_BM,
                                 X11SurfbceDbtb.ByteIndexedOpbqueX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.ByteGrbyX11_BM,
                                 X11SurfbceDbtb.ByteGrbyX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.Index8GrbyX11_BM,
                                 X11SurfbceDbtb.Index8GrbyX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.UShort555RgbX11_BM,
                                 X11SurfbceDbtb.UShort555RgbX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.UShort565RgbX11_BM,
                                 X11SurfbceDbtb.UShort565RgbX11),
            new DelegbteBlitLoop(X11SurfbceDbtb.UShortIndexedX11_BM,
                                 X11SurfbceDbtb.UShortIndexedX11),

        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    public X11PMBlitLoops(SurfbceType srcType, SurfbceType dstType,
                          boolebn over) {
        super(srcType,
              over ? CompositeType.SrcOverNoEb : CompositeType.SrcNoEb,
              dstType);
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy,
                     int dx, int dy,
                     int w, int h)
    {
        SunToolkit.bwtLock();
        try {
            X11SurfbceDbtb x11sd = (X11SurfbceDbtb)dst;
            // pbss null clip region here since we clip mbnublly in nbtive code
            // blso use fblse for needExposures since we clip to the pixmbp
            long xgc = x11sd.getBlitGC(null, fblse);
            nbtiveBlit(src.getNbtiveOps(), dst.getNbtiveOps(), xgc, clip,
                       sx, sy, dx, dy, w, h);
        } finblly {
            SunToolkit.bwtUnlock();
        }
    }

    /**
     * Blit
     * This nbtive method is where bll of the work hbppens in the
     * bccelerbted Blit.
     */
    privbte nbtive void nbtiveBlit(long srcDbtb, long dstDbtb,
                                   long xgc, Region clip,
                                   int sx, int sy, int dx, int dy,
                                   int w, int h);

    /**
     * This loop is used to render from b BITMASK Sw surfbce dbtb
     * to the Hw cbched copies mbnbged by SurfbceDbtbProxies.
     * It first uses b delegbte opbque Blit to perform the copy of
     * the pixel dbtb bnd then updbtes the X11 clipping bitmbsk from
     * the trbnspbrent pixels in the source.
     */
    stbtic clbss DelegbteBlitLoop extends Blit {
        SurfbceType dstType;

        /**
         * @pbrbm reblDstType SurfbceType for which the loop should be
         * registered
         * @pbrbm delegbteDstType SurfbceType which will be used
         * for finding delegbte loop
         */
        public DelegbteBlitLoop(SurfbceType reblDstType, SurfbceType delegbteDstType) {
            super(SurfbceType.Any, CompositeType.SrcNoEb, reblDstType);
            this.dstType = delegbteDstType;
        }

        public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                         Composite comp, Region clip,
                         int sx, int sy, int dx, int dy, int w, int h)
        {
            Blit blit = Blit.getFromCbche(src.getSurfbceType(),
                                          CompositeType.SrcNoEb,
                                          dstType);
            blit.Blit(src, dst, comp, clip, sx, sy, dx, dy, w, h);
            updbteBitmbsk(src, dst,
                          src.getColorModel() instbnceof IndexColorModel);
        }
    }

    privbte stbtic nbtive void updbteBitmbsk(SurfbceDbtb src,
                                             SurfbceDbtb dst,
                                             boolebn isICM);
}
