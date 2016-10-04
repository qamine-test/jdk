/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.CompositeContext;
import jbvb.bwt.RenderingHints;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.Rbster;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.lbng.ref.WebkReference;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.Region;
import sun.jbvb2d.pipe.SpbnIterbtor;

/**
 * Blit
 * 1) copies rectbngle of pixels from one surfbce to bnother
 * 2) performs compositing of colors bbsed upon b Composite
 *    pbrbmeter
 *
 * precise behbvior is undefined if the source surfbce
 * bnd the destinbtion surfbce bre the sbme surfbce
 * with overlbpping regions of pixels
 */

public clbss Blit extends GrbphicsPrimitive
{
    public stbtic finbl String methodSignbture = "Blit(...)".toString();

    public stbtic finbl int primTypeID = mbkePrimTypeID();

    privbte stbtic RenderCbche blitcbche = new RenderCbche(20);

    public stbtic Blit locbte(SurfbceType srctype,
                              CompositeType comptype,
                              SurfbceType dsttype)
    {
        return (Blit)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    public stbtic Blit getFromCbche(SurfbceType src,
                                    CompositeType comp,
                                    SurfbceType dst)
    {
        Object o = blitcbche.get(src, comp, dst);
        if (o != null) {
            return (Blit) o;
        }

        Blit blit = locbte(src, comp, dst);
        if (blit == null) {
            System.out.println("blit loop not found for:");
            System.out.println("src:  "+src);
            System.out.println("comp: "+comp);
            System.out.println("dst:  "+dst);
        } else {
            blitcbche.put(src, comp, dst, blit);
        }
        return blit;
    }

    protected Blit(SurfbceType srctype,
                   CompositeType comptype,
                   SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public Blit(long pNbtivePrim,
                SurfbceType srctype,
                CompositeType comptype,
                SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    /**
     * All Blit implementors must hbve this invoker method
     */
    public nbtive void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                            Composite comp, Region clip,
                            int srcx, int srcy,
                            int dstx, int dsty,
                            int width, int height);

    stbtic {
        GrbphicsPrimitiveMgr.registerGenerbl(new Blit(null, null, null));
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        /*
        System.out.println("Constructing generbl blit for:");
        System.out.println("src:  "+srctype);
        System.out.println("comp: "+comptype);
        System.out.println("dst:  "+dsttype);
        */

        if (comptype.isDerivedFrom(CompositeType.Xor)) {
            GenerblXorBlit gxb = new GenerblXorBlit(srctype,
                                                    comptype,
                                                    dsttype);
            setupGenerblBinbryOp(gxb);
            return gxb;
        } else if (comptype.isDerivedFrom(CompositeType.AnyAlphb)) {
            return new GenerblMbskBlit(srctype, comptype, dsttype);
        } else {
            return AnyBlit.instbnce;
        }
    }

    privbte stbtic clbss AnyBlit extends Blit {
        public stbtic AnyBlit instbnce = new AnyBlit();

        public AnyBlit() {
            super(SurfbceType.Any, CompositeType.Any, SurfbceType.Any);
        }

        public void Blit(SurfbceDbtb srcDbtb,
                         SurfbceDbtb dstDbtb,
                         Composite comp,
                         Region clip,
                         int srcx, int srcy,
                         int dstx, int dsty,
                         int width, int height)
        {
            ColorModel srcCM = srcDbtb.getColorModel();
            ColorModel dstCM = dstDbtb.getColorModel();
            // REMIND: Should get RenderingHints from sg2d
            CompositeContext ctx = comp.crebteContext(srcCM, dstCM,
                                                      new RenderingHints(null));
            Rbster srcRbs = srcDbtb.getRbster(srcx, srcy, width, height);
            WritbbleRbster dstRbs =
                (WritbbleRbster) dstDbtb.getRbster(dstx, dsty, width, height);

            if (clip == null) {
                clip = Region.getInstbnceXYWH(dstx, dsty, width, height);
            }
            int spbn[] = {dstx, dsty, dstx+width, dsty+height};
            SpbnIterbtor si = clip.getSpbnIterbtor(spbn);
            srcx -= dstx;
            srcy -= dsty;
            while (si.nextSpbn(spbn)) {
                int w = spbn[2] - spbn[0];
                int h = spbn[3] - spbn[1];
                Rbster tmpSrcRbs = srcRbs.crebteChild(srcx + spbn[0], srcy + spbn[1],
                                                      w, h, 0, 0, null);
                WritbbleRbster tmpDstRbs = dstRbs.crebteWritbbleChild(spbn[0], spbn[1],
                                                                      w, h, 0, 0, null);
                ctx.compose(tmpSrcRbs, tmpDstRbs, tmpDstRbs);
            }
            ctx.dispose();
        }
    }

    privbte stbtic clbss GenerblMbskBlit extends Blit {
        MbskBlit performop;

        public GenerblMbskBlit(SurfbceType srctype,
                               CompositeType comptype,
                               SurfbceType dsttype)
        {
            super(srctype, comptype, dsttype);
            performop = MbskBlit.locbte(srctype, comptype, dsttype);
        }

        public void Blit(SurfbceDbtb srcDbtb,
                         SurfbceDbtb dstDbtb,
                         Composite comp,
                         Region clip,
                         int srcx, int srcy,
                         int dstx, int dsty,
                         int width, int height)
        {
            performop.MbskBlit(srcDbtb, dstDbtb, comp, clip,
                               srcx, srcy, dstx, dsty,
                               width, height,
                               null, 0, 0);
        }
    }

    privbte stbtic clbss GenerblXorBlit
        extends Blit
        implements GenerblBinbryOp
    {
        Blit convertsrc;
        Blit convertdst;
        Blit performop;
        Blit convertresult;

        WebkReference<SurfbceDbtb> srcTmp;
        WebkReference<SurfbceDbtb> dstTmp;

        public GenerblXorBlit(SurfbceType srctype,
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
            this.performop = (Blit) genericop;
            this.convertresult = resconverter;
        }

        public synchronized void Blit(SurfbceDbtb srcDbtb,
                                      SurfbceDbtb dstDbtb,
                                      Composite comp,
                                      Region clip,
                                      int srcx, int srcy,
                                      int dstx, int dsty,
                                      int width, int height)
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

            performop.Blit(src, dst, comp, opclip,
                           sx, sy, dx, dy,
                           width, height);

            if (convertresult != null) {
                // bssert: convertdst != null
                convertTo(convertresult, dst, dstDbtb, clip,
                          dstx, dsty, width, height);
            }
        }
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceBlit(this);
    }

    privbte stbtic clbss TrbceBlit extends Blit {
        Blit tbrget;

        public TrbceBlit(Blit tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void Blit(SurfbceDbtb src, SurfbceDbtb dst,
                         Composite comp, Region clip,
                         int srcx, int srcy, int dstx, int dsty,
                         int width, int height)
        {
            trbcePrimitive(tbrget);
            tbrget.Blit(src, dst, comp, clip,
                        srcx, srcy, dstx, dsty, width, height);
        }
    }
}
