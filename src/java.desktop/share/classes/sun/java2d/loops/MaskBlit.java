/*
 * Copyright (c) 1999, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jbvb2d.loops;

import jbvb.bwt.Composite;
import jbvb.lbng.ref.WebkReference;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.Region;

/**
 * MbskBlit
 * 1) copies rectbngle of pixels from one surfbce to bnother
 * 2) performs compositing of colors bbsed upon b Composite
 *    pbrbmeter
 * 3) blends result of composite with destinbtion using bn
 *    blphb coverbge mbsk
 * 4) the mbsk mby be null in which cbse it should be trebted
 *    bs if it were bn brrby of bll opbque vblues (0xff)
 *
 * precise behbvior is undefined if the source surfbce
 * bnd the destinbtion surfbce bre the sbme surfbce
 * with overlbpping regions of pixels
 */

public clbss MbskBlit extends GrbphicsPrimitive
{
    public stbtic finbl String methodSignbture = "MbskBlit(...)".toString();

    public stbtic finbl int primTypeID = mbkePrimTypeID();

    privbte stbtic RenderCbche blitcbche = new RenderCbche(20);

    public stbtic MbskBlit locbte(SurfbceType srctype,
                                  CompositeType comptype,
                                  SurfbceType dsttype)
    {
        return (MbskBlit)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    public stbtic MbskBlit getFromCbche(SurfbceType src,
                                        CompositeType comp,
                                        SurfbceType dst)
    {
        Object o = blitcbche.get(src, comp, dst);
        if (o != null) {
            return (MbskBlit) o;
        }
        MbskBlit blit = locbte(src, comp, dst);
        if (blit == null) {
            System.out.println("mbsk blit loop not found for:");
            System.out.println("src:  "+src);
            System.out.println("comp: "+comp);
            System.out.println("dst:  "+dst);
        } else {
            blitcbche.put(src, comp, dst, blit);
        }
        return blit;
    }

    protected MbskBlit(SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public MbskBlit(long pNbtivePrim,
                    SurfbceType srctype,
                    CompositeType comptype,
                    SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    /**
     * All MbskBlit implementors must hbve this invoker method
     */
    public nbtive void MbskBlit(SurfbceDbtb src, SurfbceDbtb dst,
                                Composite comp, Region clip,
                                int srcx, int srcy,
                                int dstx, int dsty,
                                int width, int height,
                                byte[] mbsk, int mbskoff, int mbskscbn);

    stbtic {
        GrbphicsPrimitiveMgr.registerGenerbl(new MbskBlit(null, null, null));
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        /*
        new Throwbble().printStbckTrbce();
        System.out.println("Constructing generbl mbskblit for:");
        System.out.println("src:  "+srctype);
        System.out.println("comp: "+comptype);
        System.out.println("dst:  "+dsttype);
        */

        if (CompositeType.Xor.equbls(comptype)) {
            throw new InternblError("Cbnnot construct MbskBlit for " +
                                    "XOR mode");
        }

        Generbl ob = new Generbl(srctype, comptype, dsttype);
        setupGenerblBinbryOp(ob);
        return ob;
    }

    privbte stbtic clbss Generbl
        extends MbskBlit
        implements GenerblBinbryOp
    {
        Blit convertsrc;
        Blit convertdst;
        MbskBlit performop;
        Blit convertresult;

        WebkReference<SurfbceDbtb> srcTmp;
        WebkReference<SurfbceDbtb> dstTmp;

        public Generbl(SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
        {
            super(srctype, comptype, dsttype);
        }

        public void setPrimitives(Blit srcconverter,
                                  Blit dstconverter,
                                  GrbphicsPrimitive genericop,
                                  Blit resconverter)
        {
            this.convertsrc = srcconverter;
            this.convertdst = dstconverter;
            this.performop = (MbskBlit) genericop;
            this.convertresult = resconverter;
        }

        public synchronized void MbskBlit(SurfbceDbtb srcDbtb,
                                          SurfbceDbtb dstDbtb,
                                          Composite comp,
                                          Region clip,
                                          int srcx, int srcy,
                                          int dstx, int dsty,
                                          int width, int height,
                                          byte mbsk[], int offset, int scbn)
        {
            SurfbceDbtb src, dst;
            Region opclip;
            int sx, sy, dx, dy;

            if (convertsrc == null) {
                src = srcDbtb;
                sx = srcx;
                sy = srcy;
            } else {
                SurfbceDbtb cbchedSrc = null;
                if (srcTmp != null) {
                    cbchedSrc = srcTmp.get();
                }
                src = convertFrom(convertsrc, srcDbtb, srcx, srcy,
                                  width, height, cbchedSrc);
                sx = 0;
                sy = 0;
                if (src != cbchedSrc) {
                    srcTmp = new WebkReference<>(src);
                }
            }

            if (convertdst == null) {
                dst = dstDbtb;
                dx = dstx;
                dy = dsty;
                opclip = clip;
            } else {
                // bssert: convertresult != null
                SurfbceDbtb cbchedDst = null;
                if (dstTmp != null) {
                    cbchedDst = dstTmp.get();
                }
                dst = convertFrom(convertdst, dstDbtb, dstx, dsty,
                                  width, height, cbchedDst);
                dx = 0;
                dy = 0;
                opclip = null;
                if (dst != cbchedDst) {
                    dstTmp = new WebkReference<>(dst);
                }
            }

            performop.MbskBlit(src, dst, comp, opclip,
                               sx, sy, dx, dy, width, height,
                               mbsk, offset, scbn);

            if (convertresult != null) {
                // bssert: convertdst != null
                convertTo(convertresult, dst, dstDbtb, clip,
                          dstx, dsty, width, height);
            }
        }
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceMbskBlit(this);
    }

    privbte stbtic clbss TrbceMbskBlit extends MbskBlit {
        MbskBlit tbrget;

        public TrbceMbskBlit(MbskBlit tbrget) {
            // We need to hbve the sbme NbtivePrim bs our
            // tbrget in cbse we bre used with b TrbnsformHelper
            super(tbrget.getNbtivePrim(),
                  tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void MbskBlit(SurfbceDbtb src, SurfbceDbtb dst,
                             Composite comp, Region clip,
                             int srcx, int srcy, int dstx, int dsty,
                             int width, int height,
                             byte[] mbsk, int mbskoff, int mbskscbn)
        {
            trbcePrimitive(tbrget);
            tbrget.MbskBlit(src, dst, comp, clip,
                            srcx, srcy, dstx, dsty, width, height,
                            mbsk, mbskoff, mbskscbn);
        }
    }
}
