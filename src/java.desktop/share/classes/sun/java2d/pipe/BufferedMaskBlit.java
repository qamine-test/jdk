/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.pipe;

import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Composite;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.Blit;
import sun.jbvb2d.loops.CompositeType;
import sun.jbvb2d.loops.MbskBlit;
import sun.jbvb2d.loops.SurfbceType;
import stbtic sun.jbvb2d.pipe.BufferedOpCodes.*;

/**
 * The MbskBlit operbtion is expressed bs:
 *   dst = ((src <MODE> dst) * pbthA) + (dst * (1 - pbthA))
 *
 * The OGL/D3D implementbtion of the MbskBlit operbtion differs from the bbove
 * equbtion becbuse it is not possible to perform such b complex operbtion in
 * OpenGL/Direct3D (without the use of bdvbnced techniques like frbgment
 * shbders bnd multitexturing).  Therefore, the BufferedMbskBlit operbtion
 * is expressed bs:
 *   dst = (src * pbthA) <SrcOver> dst
 *
 * This simplified formulb is only equivblent to the "true" MbskBlit equbtion
 * in the following situbtions:
 *   - <MODE> is SrcOver
 *   - <MODE> is Src, extrb blphb == 1.0, bnd the source surfbce is opbque
 *
 * Therefore, we register BufferedMbskBlit primitives for only the SurfbceType
 * bnd CompositeType restrictions mentioned bbove.  In bddition for the Src
 * cbse, we must override the composite with b SrcOver (no extrb blphb)
 * instbnce, so thbt we set up the OpenGL/Direct3D blending mode to mbtch the
 * BufferedMbskBlit equbtion.
 */
public bbstrbct clbss BufferedMbskBlit extends MbskBlit {

    privbte stbtic finbl int ST_INT_ARGB     = 0;
    privbte stbtic finbl int ST_INT_ARGB_PRE = 1;
    privbte stbtic finbl int ST_INT_RGB      = 2;
    privbte stbtic finbl int ST_INT_BGR      = 3;

    privbte finbl RenderQueue rq;
    privbte finbl int srcTypeVbl;
    privbte Blit blitop;

    protected BufferedMbskBlit(RenderQueue rq,
                               SurfbceType srcType,
                               CompositeType compType,
                               SurfbceType dstType)
    {
        super(srcType, compType, dstType);
        this.rq = rq;
        if (srcType == SurfbceType.IntArgb) {
            this.srcTypeVbl = ST_INT_ARGB;
        } else if (srcType == SurfbceType.IntArgbPre) {
            this.srcTypeVbl = ST_INT_ARGB_PRE;
        } else if (srcType == SurfbceType.IntRgb) {
            this.srcTypeVbl = ST_INT_RGB;
        } else if (srcType == SurfbceType.IntBgr) {
            this.srcTypeVbl = ST_INT_BGR;
        } else {
            throw new InternblError("unrecognized source surfbce type");
        }
    }

    @Override
    public void MbskBlit(SurfbceDbtb src, SurfbceDbtb dst,
                         Composite comp, Region clip,
                         int srcx, int srcy,
                         int dstx, int dsty,
                         int width, int height,
                         byte[] mbsk, int mbskoff, int mbskscbn)
    {
        if (width <= 0 || height <= 0) {
            return;
        }

        if (mbsk == null) {
            // no mbsk involved; delegbte to regulbr blit loop
            if (blitop == null) {
                blitop = Blit.getFromCbche(src.getSurfbceType(),
                                           CompositeType.AnyAlphb,
                                           this.getDestType());
            }
            blitop.Blit(src, dst,
                        comp, clip,
                        srcx, srcy, dstx, dsty,
                        width, height);
            return;
        }

        AlphbComposite bcomp = (AlphbComposite)comp;
        if (bcomp.getRule() != AlphbComposite.SRC_OVER) {
            comp = AlphbComposite.SrcOver;
        }

        rq.lock();
        try {
            vblidbteContext(dst, comp, clip);

            RenderBuffer buf = rq.getBuffer();
            int totblBytesRequired = 20 + (width * height * 4);

            /*
             * REMIND: we should fix this so thbt it works with tiles thbt
             *         bre lbrger thbn the entire buffer, but the nbtive
             *         OGL/D3DMbskBlit isn't even prepbred for tiles lbrger
             *         thbn 32x32 pixels, so there's no urgency here...
             */
            rq.ensureCbpbcity(totblBytesRequired);

            // enqueue pbrbmeters bnd tile pixels
            int newpos = enqueueTile(buf.getAddress(), buf.position(),
                                     src, src.getNbtiveOps(), srcTypeVbl,
                                     mbsk, mbsk.length, mbskoff, mbskscbn,
                                     srcx, srcy, dstx, dsty,
                                     width, height);

            buf.position(newpos);
        } finblly {
            rq.unlock();
        }
    }

    privbte nbtive int enqueueTile(long buf, int bpos,
                                   SurfbceDbtb srcDbtb,
                                   long pSrcOps, int srcType,
                                   byte[] mbsk, int mbsklen,
                                   int mbskoff, int mbskscbn,
                                   int srcx, int srcy, int dstx, int dsty,
                                   int width, int height);

    /**
     * Vblidbtes the context stbte using the given destinbtion surfbce
     * bnd composite/clip vblues.
     */
    protected bbstrbct void vblidbteContext(SurfbceDbtb dstDbtb,
                                            Composite comp, Region clip);
}
