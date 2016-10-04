/*
 * Copyright (c) 2007, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.d3d;

import jbvb.bwt.Composite;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.bwt.imbge.AffineTrbnsformOp;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.BufferedImbgeOp;
import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.bnnotbtion.Nbtive;
import sun.jbvb2d.ScreenUpdbteMbnbger;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.loops.GrbphicsPrimitiveMgr;
import sun.jbvb2d.loops.ScbledBlit;
import sun.jbvb2d.loops.SurfbceType;
import sun.jbvb2d.loops.TrbnsformBlit;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.RenderBuffer;
import sun.jbvb2d.pipe.RenderQueue;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;
import sun.jbvb2d.windows.GDIWindowSurfbceDbtb;

clbss D3DBlitLoops {

    stbtic void register() {
        Blit blitIntArgbPreToSurfbce =
            new D3DSwToSurfbceBlit(SurfbceType.IntArgbPre,
                                   D3DSurfbceDbtb.ST_INT_ARGB_PRE);
        Blit blitIntArgbPreToTexture =
            new D3DSwToTextureBlit(SurfbceType.IntArgbPre,
                                   D3DSurfbceDbtb.ST_INT_ARGB_PRE);

        GrbphicsPrimitive[] primitives = {
            // prevent D3DSurfbce -> Screen blits
            new D3DSurfbceToGDIWindowSurfbceBlit(),
            new D3DSurfbceToGDIWindowSurfbceScble(),
            new D3DSurfbceToGDIWindowSurfbceTrbnsform(),

            // surfbce->surfbce ops
            new D3DSurfbceToSurfbceBlit(),
            new D3DSurfbceToSurfbceScble(),
            new D3DSurfbceToSurfbceTrbnsform(),

            // render-to-texture surfbce->surfbce ops
            new D3DRTTSurfbceToSurfbceBlit(),
            new D3DRTTSurfbceToSurfbceScble(),
            new D3DRTTSurfbceToSurfbceTrbnsform(),

            // surfbce->sw ops
            new D3DSurfbceToSwBlit(SurfbceType.IntArgb,
                                   D3DSurfbceDbtb.ST_INT_ARGB),

            // sw->surfbce ops
            blitIntArgbPreToSurfbce,
            new D3DSwToSurfbceBlit(SurfbceType.IntArgb,
                                   D3DSurfbceDbtb.ST_INT_ARGB),
            new D3DSwToSurfbceBlit(SurfbceType.IntRgb,
                                   D3DSurfbceDbtb.ST_INT_RGB),
            new D3DSwToSurfbceBlit(SurfbceType.IntBgr,
                                   D3DSurfbceDbtb.ST_INT_BGR),
            new D3DSwToSurfbceBlit(SurfbceType.ThreeByteBgr,
                                   D3DSurfbceDbtb.ST_3BYTE_BGR),
            new D3DSwToSurfbceBlit(SurfbceType.Ushort565Rgb,
                                   D3DSurfbceDbtb.ST_USHORT_565_RGB),
            new D3DSwToSurfbceBlit(SurfbceType.Ushort555Rgb,
                                   D3DSurfbceDbtb.ST_USHORT_555_RGB),
            new D3DSwToSurfbceBlit(SurfbceType.ByteIndexed,
                                   D3DSurfbceDbtb.ST_BYTE_INDEXED),
            // REMIND: we don't hbve b nbtive sw loop to bbck this loop up
//            new D3DSwToSurfbceBlit(SurfbceType.ByteIndexedBm,
//                                   D3DSurfbceDbtb.ST_BYTE_INDEXED_BM),
            new D3DGenerblBlit(D3DSurfbceDbtb.D3DSurfbce,
                               CompositeType.AnyAlphb,
                               blitIntArgbPreToSurfbce),

            new D3DSwToSurfbceScble(SurfbceType.IntArgb,
                                    D3DSurfbceDbtb.ST_INT_ARGB),
            new D3DSwToSurfbceScble(SurfbceType.IntArgbPre,
                                    D3DSurfbceDbtb.ST_INT_ARGB_PRE),
            new D3DSwToSurfbceScble(SurfbceType.IntRgb,
                                    D3DSurfbceDbtb.ST_INT_RGB),
            new D3DSwToSurfbceScble(SurfbceType.IntBgr,
                                    D3DSurfbceDbtb.ST_INT_BGR),
            new D3DSwToSurfbceScble(SurfbceType.ThreeByteBgr,
                                    D3DSurfbceDbtb.ST_3BYTE_BGR),
            new D3DSwToSurfbceScble(SurfbceType.Ushort565Rgb,
                                    D3DSurfbceDbtb.ST_USHORT_565_RGB),
            new D3DSwToSurfbceScble(SurfbceType.Ushort555Rgb,
                                    D3DSurfbceDbtb.ST_USHORT_555_RGB),
            new D3DSwToSurfbceScble(SurfbceType.ByteIndexed,
                                    D3DSurfbceDbtb.ST_BYTE_INDEXED),
            // REMIND: we don't hbve b nbtive sw loop to bbck this loop up
//            new D3DSwToSurfbceScble(SurfbceType.ByteIndexedBm,
//                                    D3DSurfbceDbtb.ST_BYTE_INDEXED_BM),

            new D3DSwToSurfbceTrbnsform(SurfbceType.IntArgb,
                                        D3DSurfbceDbtb.ST_INT_ARGB),
            new D3DSwToSurfbceTrbnsform(SurfbceType.IntArgbPre,
                                        D3DSurfbceDbtb.ST_INT_ARGB_PRE),
            new D3DSwToSurfbceTrbnsform(SurfbceType.IntRgb,
                                        D3DSurfbceDbtb.ST_INT_RGB),
            new D3DSwToSurfbceTrbnsform(SurfbceType.IntBgr,
                                        D3DSurfbceDbtb.ST_INT_BGR),
            new D3DSwToSurfbceTrbnsform(SurfbceType.ThreeByteBgr,
                                        D3DSurfbceDbtb.ST_3BYTE_BGR),
            new D3DSwToSurfbceTrbnsform(SurfbceType.Ushort565Rgb,
                                        D3DSurfbceDbtb.ST_USHORT_565_RGB),
            new D3DSwToSurfbceTrbnsform(SurfbceType.Ushort555Rgb,
                                        D3DSurfbceDbtb.ST_USHORT_555_RGB),
            new D3DSwToSurfbceTrbnsform(SurfbceType.ByteIndexed,
                                        D3DSurfbceDbtb.ST_BYTE_INDEXED),
            // REMIND: we don't hbve b nbtive sw loop to bbck this loop up
//            new D3DSwToSurfbceTrbnsform(SurfbceType.ByteIndexedBm,
//                                        D3DSurfbceDbtb.ST_BYTE_INDEXED_BM),

            // texture->surfbce ops
            new D3DTextureToSurfbceBlit(),
            new D3DTextureToSurfbceScble(),
            new D3DTextureToSurfbceTrbnsform(),

            // sw->texture ops
            blitIntArgbPreToTexture,
            new D3DSwToTextureBlit(SurfbceType.IntRgb,
                                   D3DSurfbceDbtb.ST_INT_RGB),
            new D3DSwToTextureBlit(SurfbceType.IntArgb,
                                   D3DSurfbceDbtb.ST_INT_ARGB),
            new D3DSwToTextureBlit(SurfbceType.IntBgr,
                                   D3DSurfbceDbtb.ST_INT_BGR),
            new D3DSwToTextureBlit(SurfbceType.ThreeByteBgr,
                                   D3DSurfbceDbtb.ST_3BYTE_BGR),
            new D3DSwToTextureBlit(SurfbceType.Ushort565Rgb,
                                   D3DSurfbceDbtb.ST_USHORT_565_RGB),
            new D3DSwToTextureBlit(SurfbceType.Ushort555Rgb,
                                   D3DSurfbceDbtb.ST_USHORT_555_RGB),
            new D3DSwToTextureBlit(SurfbceType.ByteIndexed,
                                   D3DSurfbceDbtb.ST_BYTE_INDEXED),
            // REMIND: we don't hbve b nbtive sw loop to bbck this loop up
//            new D3DSwToTextureBlit(SurfbceType.ByteIndexedBm,
//                                   D3DSurfbceDbtb.ST_BYTE_INDEXED_BM),
            new D3DGenerblBlit(D3DSurfbceDbtb.D3DTexture,
                               CompositeType.SrcNoEb,
                               blitIntArgbPreToTexture),
        };
        GrbphicsPrimitiveMgr.register(primitives);
    }

    /**
     * The following offsets bre used to pbck the pbrbmeters in
     * crebtePbckedPbrbms().  (They bre blso used bt the nbtive level when
     * unpbcking the pbrbms.)
     */
    @Nbtive privbte stbtic finbl int OFFSET_SRCTYPE = 16;
    @Nbtive privbte stbtic finbl int OFFSET_HINT    =  8;
    @Nbtive privbte stbtic finbl int OFFSET_TEXTURE =  3;
    @Nbtive privbte stbtic finbl int OFFSET_RTT     =  2;
    @Nbtive privbte stbtic finbl int OFFSET_XFORM   =  1;
    @Nbtive privbte stbtic finbl int OFFSET_ISOBLIT =  0;

    /**
     * Pbcks the given pbrbmeters into b single int vblue in order to sbve
     * spbce on the rendering queue.
     */
    privbte stbtic int crebtePbckedPbrbms(boolebn isoblit, boolebn texture,
                                          boolebn rtt, boolebn xform,
                                          int hint, int srctype)
    {
        return
            ((srctype           << OFFSET_SRCTYPE) |
             (hint              << OFFSET_HINT   ) |
             ((texture ? 1 : 0) << OFFSET_TEXTURE) |
             ((rtt     ? 1 : 0) << OFFSET_RTT    ) |
             ((xform   ? 1 : 0) << OFFSET_XFORM  ) |
             ((isoblit ? 1 : 0) << OFFSET_ISOBLIT));
    }

    /**
     * Enqueues b BLIT operbtion with the given pbrbmeters.  Note thbt the
     * RenderQueue lock must be held before cblling this method.
     */
    privbte stbtic void enqueueBlit(RenderQueue rq,
                                    SurfbceDbtb src, SurfbceDbtb dst,
                                    int pbckedPbrbms,
                                    int sx1, int sy1,
                                    int sx2, int sy2,
                                    double dx1, double dy1,
                                    double dx2, double dy2)
    {
        // bssert rq.lock.isHeldByCurrentThrebd();
        RenderBuffer buf = rq.getBuffer();
        rq.ensureCbpbcityAndAlignment(72, 24);
        buf.putInt(BLIT);
        buf.putInt(pbckedPbrbms);
        buf.putInt(sx1).putInt(sy1);
        buf.putInt(sx2).putInt(sy2);
        buf.putDouble(dx1).putDouble(dy1);
        buf.putDouble(dx2).putDouble(dy2);
        buf.putLong(src.getNbtiveOps());
        buf.putLong(dst.getNbtiveOps());
    }

    stbtic void Blit(SurfbceDbtb srcDbtb, SurfbceDbtb dstDbtb,
                     Composite comp, Region clip,
                     AffineTrbnsform xform, int hint,
                     int sx1, int sy1,
                     int sx2, int sy2,
                     double dx1, double dy1,
                     double dx2, double dy2,
                     int srctype, boolebn texture)
    {
        int ctxflbgs = 0;
        if (srcDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE) {
            ctxflbgs |= D3DContext.SRC_IS_OPAQUE;
        }

        D3DSurfbceDbtb d3dDst = (D3DSurfbceDbtb)dstDbtb;
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            // mbke sure the RenderQueue keeps b hbrd reference to the
            // source (sysmem) SurfbceDbtb to prevent it from being
            // disposed while the operbtion is processed on the QFT
            rq.bddReference(srcDbtb);

            if (texture) {
                // mbke sure we hbve b current context before uplobding
                // the sysmem dbtb to the texture object
                D3DContext.setScrbtchSurfbce(d3dDst.getContext());
            } else {
                D3DContext.vblidbteContext(d3dDst, d3dDst,
                                           clip, comp, xform, null, null,
                                           ctxflbgs);
            }

            int pbckedPbrbms = crebtePbckedPbrbms(fblse, texture,
                                                  fblse, xform != null,
                                                  hint, srctype);
            enqueueBlit(rq, srcDbtb, dstDbtb,
                        pbckedPbrbms,
                        sx1, sy1, sx2, sy2,
                        dx1, dy1, dx2, dy2);

            // blwbys flush immedibtely, since we (currently) hbve no mebns
            // of trbcking chbnges to the system memory surfbce
            rq.flushNow();
        } finblly {
            rq.unlock();
        }

        if (d3dDst.getType() == D3DSurfbceDbtb.WINDOW) {
            // flush immedibtely when copying to the screen to improve
            // responsiveness of bpplicbtions using VI or BI bbckbuffers
            D3DScreenUpdbteMbnbger mgr =
                (D3DScreenUpdbteMbnbger)ScreenUpdbteMbnbger.getInstbnce();
            mgr.runUpdbteNow();
        }
    }

    /**
     * Note: The srcImg bnd biop pbrbmeters bre only used when invoked
     * from the D3DBufImgOps.renderImbgeWithOp() method; in bll other cbses,
     * this method cbn be cblled with null vblues for those two pbrbmeters,
     * bnd they will be effectively ignored.
     */
    stbtic void IsoBlit(SurfbceDbtb srcDbtb, SurfbceDbtb dstDbtb,
                        BufferedImbge srcImg, BufferedImbgeOp biop,
                        Composite comp, Region clip,
                        AffineTrbnsform xform, int hint,
                        int sx1, int sy1,
                        int sx2, int sy2,
                        double dx1, double dy1,
                        double dx2, double dy2,
                        boolebn texture)
    {
        int ctxflbgs = 0;
        if (srcDbtb.getTrbnspbrency() == Trbnspbrency.OPAQUE) {
            ctxflbgs |= D3DContext.SRC_IS_OPAQUE;
        }

        D3DSurfbceDbtb d3dDst = (D3DSurfbceDbtb)dstDbtb;
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        boolebn rtt = fblse;
        rq.lock();
        try {
            D3DSurfbceDbtb d3dSrc = (D3DSurfbceDbtb)srcDbtb;
            int srctype = d3dSrc.getType();
            D3DSurfbceDbtb srcCtxDbtb = d3dSrc;
            if (srctype == D3DSurfbceDbtb.TEXTURE) {
                rtt = fblse;
            } else {
                // the source is b bbckbuffer, or render-to-texture
                // surfbce; we set rtt to true to differentibte this kind
                // of surfbce from b regulbr texture object
                rtt = true;
            }

            D3DContext.vblidbteContext(srcCtxDbtb, d3dDst,
                                       clip, comp, xform, null, null,
                                       ctxflbgs);

            if (biop != null) {
                D3DBufImgOps.enbbleBufImgOp(rq, d3dSrc, srcImg, biop);
            }

            int pbckedPbrbms = crebtePbckedPbrbms(true, texture,
                                                  rtt, xform != null,
                                                  hint, 0 /*unused*/);
            enqueueBlit(rq, srcDbtb, dstDbtb,
                        pbckedPbrbms,
                        sx1, sy1, sx2, sy2,
                        dx1, dy1, dx2, dy2);

            if (biop != null) {
                D3DBufImgOps.disbbleBufImgOp(rq, biop);
            }
        } finblly {
            rq.unlock();
        }

        if (rtt && (d3dDst.getType() == D3DSurfbceDbtb.WINDOW)) {
            // we only hbve to flush immedibtely when copying from b
            // (non-texture) surfbce to the screen; otherwise Swing bpps
            // might bppebr unresponsive until the buto-flush completes
            D3DScreenUpdbteMbnbger mgr =
                (D3DScreenUpdbteMbnbger)ScreenUpdbteMbnbger.getInstbnce();
            mgr.runUpdbteNow();
        }
    }
}

clbss D3DSurfbceToSurfbceBlit extends Blit {

    D3DSurfbceToSurfbceBlit() {
        super(D3DSurfbceDbtb.D3DSurfbce,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy, int w, int h)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, null,
                             AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+h,
                             dx, dy, dx+w, dy+h,
                             fblse);
    }
}

clbss D3DSurfbceToSurfbceScble extends ScbledBlit {

    D3DSurfbceToSurfbceScble() {
        super(D3DSurfbceDbtb.D3DSurfbce,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Scble(SurfbceDbtb src, SurfbceDbtb dst,
                      Composite comp, Region clip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      double dx1, double dy1,
                      double dx2, double dy2)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, null,
                             AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             fblse);
    }
}

clbss D3DSurfbceToSurfbceTrbnsform extends TrbnsformBlit {

    D3DSurfbceToSurfbceTrbnsform() {
        super(D3DSurfbceDbtb.D3DSurfbce,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Trbnsform(SurfbceDbtb src, SurfbceDbtb dst,
                          Composite comp, Region clip,
                          AffineTrbnsform bt, int hint,
                          int sx, int sy, int dx, int dy,
                          int w, int h)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, bt, hint,
                             sx, sy, sx+w, sy+h,
                             dx, dy, dx+w, dy+h,
                             fblse);
    }
}

clbss D3DRTTSurfbceToSurfbceBlit extends Blit {

    D3DRTTSurfbceToSurfbceBlit() {
        super(D3DSurfbceDbtb.D3DSurfbceRTT,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy, int w, int h)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, null,
                             AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+h,
                             dx, dy, dx+w, dy+h,
                             true);
    }
}

clbss D3DRTTSurfbceToSurfbceScble extends ScbledBlit {

    D3DRTTSurfbceToSurfbceScble() {
        super(D3DSurfbceDbtb.D3DSurfbceRTT,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Scble(SurfbceDbtb src, SurfbceDbtb dst,
                      Composite comp, Region clip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      double dx1, double dy1,
                      double dx2, double dy2)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, null,
                             AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             true);
    }
}

clbss D3DRTTSurfbceToSurfbceTrbnsform extends TrbnsformBlit {

    D3DRTTSurfbceToSurfbceTrbnsform() {
        super(D3DSurfbceDbtb.D3DSurfbceRTT,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Trbnsform(SurfbceDbtb src, SurfbceDbtb dst,
                          Composite comp, Region clip,
                          AffineTrbnsform bt, int hint,
                          int sx, int sy, int dx, int dy, int w, int h)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, bt, hint,
                             sx, sy, sx+w, sy+h,
                             dx, dy, dx+w, dy+h,
                             true);
    }
}

clbss D3DSurfbceToSwBlit extends Blit {

    privbte int typevbl;

    // REMIND: destinbtion will bctublly be opbque/premultiplied...
    D3DSurfbceToSwBlit(SurfbceType dstType, int typevbl) {
        super(D3DSurfbceDbtb.D3DSurfbce,
              CompositeType.SrcNoEb,
              dstType);
        this.typevbl = typevbl;
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy,
                     int w, int h)
    {
        D3DRenderQueue rq = D3DRenderQueue.getInstbnce();
        rq.lock();
        try {
            // mbke sure the RenderQueue keeps b hbrd reference to the
            // destinbtion (sysmem) SurfbceDbtb to prevent it from being
            // disposed while the operbtion is processed on the QFT
            rq.bddReference(dst);

            RenderBuffer buf = rq.getBuffer();
            D3DContext.setScrbtchSurfbce(((D3DSurfbceDbtb)src).getContext());

            rq.ensureCbpbcityAndAlignment(48, 32);
            buf.putInt(SURFACE_TO_SW_BLIT);
            buf.putInt(sx).putInt(sy);
            buf.putInt(dx).putInt(dy);
            buf.putInt(w).putInt(h);
            buf.putInt(typevbl);
            buf.putLong(src.getNbtiveOps());
            buf.putLong(dst.getNbtiveOps());

            // blwbys flush immedibtely
            rq.flushNow();
        } finblly {
            rq.unlock();
        }
    }
}

clbss D3DSwToSurfbceBlit extends Blit {

    privbte int typevbl;

    D3DSwToSurfbceBlit(SurfbceType srcType, int typevbl) {
        super(srcType,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
        this.typevbl = typevbl;
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy, int w, int h)
    {
        D3DBlitLoops.Blit(src, dst,
                          comp, clip, null,
                          AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx, sy, sx+w, sy+h,
                          dx, dy, dx+w, dy+h,
                          typevbl, fblse);
    }
}

clbss D3DSwToSurfbceScble extends ScbledBlit {

    privbte int typevbl;

    D3DSwToSurfbceScble(SurfbceType srcType, int typevbl) {
        super(srcType,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
        this.typevbl = typevbl;
    }

    public void Scble(SurfbceDbtb src, SurfbceDbtb dst,
                      Composite comp, Region clip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      double dx1, double dy1,
                      double dx2, double dy2)
    {
        D3DBlitLoops.Blit(src, dst,
                          comp, clip, null,
                          AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx1, sy1, sx2, sy2,
                          dx1, dy1, dx2, dy2,
                          typevbl, fblse);
    }
}

clbss D3DSwToSurfbceTrbnsform extends TrbnsformBlit {

    privbte int typevbl;

    D3DSwToSurfbceTrbnsform(SurfbceType srcType, int typevbl) {
        super(srcType,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
        this.typevbl = typevbl;
    }

    public void Trbnsform(SurfbceDbtb src, SurfbceDbtb dst,
                          Composite comp, Region clip,
                          AffineTrbnsform bt, int hint,
                          int sx, int sy, int dx, int dy, int w, int h)
    {
        D3DBlitLoops.Blit(src, dst,
                          comp, clip, bt, hint,
                          sx, sy, sx+w, sy+h,
                          dx, dy, dx+w, dy+h,
                          typevbl, fblse);
    }
}

clbss D3DSwToTextureBlit extends Blit {

    privbte int typevbl;

    D3DSwToTextureBlit(SurfbceType srcType, int typevbl) {
        super(srcType,
              CompositeType.SrcNoEb,
              D3DSurfbceDbtb.D3DTexture);
        this.typevbl = typevbl;
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy, int w, int h)
    {
        D3DBlitLoops.Blit(src, dst,
                          comp, clip, null,
                          AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                          sx, sy, sx+w, sy+h,
                          dx, dy, dx+w, dy+h,
                          typevbl, true);
    }
}

clbss D3DTextureToSurfbceBlit extends Blit {

    D3DTextureToSurfbceBlit() {
        super(D3DSurfbceDbtb.D3DTexture,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy, int w, int h)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, null,
                             AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx, sy, sx+w, sy+h,
                             dx, dy, dx+w, dy+h,
                             true);
    }
}

clbss D3DTextureToSurfbceScble extends ScbledBlit {

    D3DTextureToSurfbceScble() {
        super(D3DSurfbceDbtb.D3DTexture,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Scble(SurfbceDbtb src, SurfbceDbtb dst,
                      Composite comp, Region clip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      double dx1, double dy1,
                      double dx2, double dy2)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, null,
                             AffineTrbnsformOp.TYPE_NEAREST_NEIGHBOR,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             true);
    }
}

clbss D3DTextureToSurfbceTrbnsform extends TrbnsformBlit {

    D3DTextureToSurfbceTrbnsform() {
        super(D3DSurfbceDbtb.D3DTexture,
              CompositeType.AnyAlphb,
              D3DSurfbceDbtb.D3DSurfbce);
    }

    public void Trbnsform(SurfbceDbtb src, SurfbceDbtb dst,
                          Composite comp, Region clip,
                          AffineTrbnsform bt, int hint,
                          int sx, int sy, int dx, int dy,
                          int w, int h)
    {
        D3DBlitLoops.IsoBlit(src, dst,
                             null, null,
                             comp, clip, bt, hint,
                             sx, sy, sx+w, sy+h,
                             dx, dy, dx+w, dy+h,
                             true);
    }
}

/**
 * This generbl Blit implementbtion converts bny source surfbce to bn
 * intermedibte IntArgbPre surfbce, bnd then uses the more specific
 * IntArgbPre->D3DSurfbce/Texture loop to get the intermedibte
 * (premultiplied) surfbce down to D3D.
 */
clbss D3DGenerblBlit extends Blit {

    privbte Blit performop;
    privbte WebkReference<SurfbceDbtb> srcTmp;

    D3DGenerblBlit(SurfbceType dstType,
                   CompositeType compType,
                   Blit performop)
    {
        super(SurfbceType.Any, compType, dstType);
        this.performop = performop;
    }

    public synchronized void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                                  Composite comp, Region clip,
                                  int sx, int sy, int dx, int dy,
                                  int w, int h)
    {
        Blit convertsrc = Blit.getFromCbche(src.getSurfbceType(),
                                            CompositeType.SrcNoEb,
                                            SurfbceType.IntArgbPre);

        SurfbceDbtb cbchedSrc = null;
        if (srcTmp != null) {
            // use cbched intermedibte surfbce, if bvbilbble
            cbchedSrc = srcTmp.get();
        }

        // convert source to IntArgbPre
        src = convertFrom(convertsrc, src, sx, sy, w, h,
                          cbchedSrc, BufferedImbge.TYPE_INT_ARGB_PRE);

        // copy IntArgbPre intermedibte surfbce to D3D surfbce
        performop.Blit(src, dst, comp, clip,
                       0, 0, dx, dy, w, h);

        if (src != cbchedSrc) {
            // cbche the intermedibte surfbce
            srcTmp = new WebkReference<>(src);
        }
    }
}

/*
 * The following clbsses prohibit copying D3DSurfbces to the screen
 * (the D3D->sysmem->GDI pbth is known to be very very slow).
 *
 * Note: we used to disbble hw bccelerbtion for the surbfce mbnbger bssocibted
 * with the source surfbce in these loops but it proved to be too cbutious.
 *
 * In most cbses d3d->screen copy hbppens only during some trbnsitionbl
 * period where the bccelerbted destinbtion surfbce is being recrebted or
 * restored (for exbmple, when Swing's bbckbuffer VI is copied to the screen
 * but the D3DScreenSurfbceMbnbger couldn't restore its surfbce).
 *
 * An exception is if for some rebson we could not enbble bccelerbted on-screen
 * rendering for this window for some permbnent rebson (like window being too
 * smbll, or b present BufferStrbtegy).
 *
 * This mebnt thbt we'd disbble hw bccelerbtion bfter the first fbilure
 * completely (bt lebst until the src imbge is recrebted which in cbse of
 * Swing bbck-buffer hbppens only bfter resize).
 *
 * Now we delegbte to the VISM to figure out if the bccelerbtion needs to
 * be disbbled or if we cbn wbit for b while until the onscreen bccelerbted
 * cbn resume (by mbrking the source surfbce lost bnd mbking sure the
 * VISM hbs b chbnce to use the bbckup surfbce).
 *
 */

clbss D3DSurfbceToGDIWindowSurfbceBlit extends Blit {

    D3DSurfbceToGDIWindowSurfbceBlit() {
        super(D3DSurfbceDbtb.D3DSurfbce,
              CompositeType.AnyAlphb,
              GDIWindowSurfbceDbtb.AnyGdi);
    }
    @Override
    public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                     Composite comp, Region clip,
                     int sx, int sy, int dx, int dy, int w, int h)
    {
        // see comment bbove
        D3DVolbtileSurfbceMbnbger.hbndleVItoScreenOp(src, dst);
    }

}

clbss D3DSurfbceToGDIWindowSurfbceScble extends ScbledBlit {

    D3DSurfbceToGDIWindowSurfbceScble() {
        super(D3DSurfbceDbtb.D3DSurfbce,
              CompositeType.AnyAlphb,
              GDIWindowSurfbceDbtb.AnyGdi);
    }
    @Override
    public void Scble(SurfbceDbtb src, SurfbceDbtb dst,
                      Composite comp, Region clip,
                      int sx1, int sy1,
                      int sx2, int sy2,
                      double dx1, double dy1,
                      double dx2, double dy2)
    {
        // see comment bbove
        D3DVolbtileSurfbceMbnbger.hbndleVItoScreenOp(src, dst);
    }
}

clbss D3DSurfbceToGDIWindowSurfbceTrbnsform extends TrbnsformBlit {

    D3DSurfbceToGDIWindowSurfbceTrbnsform() {
        super(D3DSurfbceDbtb.D3DSurfbce,
              CompositeType.AnyAlphb,
              GDIWindowSurfbceDbtb.AnyGdi);
    }
    @Override
    public void Trbnsform(SurfbceDbtb src, SurfbceDbtb dst,
                          Composite comp, Region clip,
                          AffineTrbnsform bt, int hint,
                          int sx, int sy, int dx, int dy,
                          int w, int h)
    {
        // see comment bbove
        D3DVolbtileSurfbceMbnbger.hbndleVItoScreenOp(src, dst);
    }
}
