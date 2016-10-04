/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Font;
import jbvb.bwt.Color;
import jbvb.bwt.Composite;
import jbvb.bwt.AlphbComposite;
import jbvb.bwt.Trbnspbrency;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.WritbbleRbster;
import jbvb.bwt.imbge.BufferedImbge;
import sun.bwt.imbge.BufImgSurfbceDbtb;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.pipe.Region;

/**
 * BlitBg
 * 1) copies rectbngle of pixels from one surfbce to bnother
 * 2) performs compositing of colors bbsed upon b Composite
 *    pbrbmeter
 * 3) bssumes thbt non-opbque pixels bre to be blended with
 *    the indicbted Bg color before compositing with the
 *    destinbtion
 *
 * precise behbvior is undefined if the source surfbce
 * bnd the destinbtion surfbce bre the sbme surfbce
 * with overlbpping regions of pixels
 */
public clbss BlitBg extends GrbphicsPrimitive
{
    public stbtic finbl String methodSignbture = "BlitBg(...)".toString();

    public stbtic finbl int primTypeID = mbkePrimTypeID();

    privbte stbtic RenderCbche blitcbche = new RenderCbche(20);

    public stbtic BlitBg locbte(SurfbceType srctype,
                                CompositeType comptype,
                                SurfbceType dsttype)
    {
        return (BlitBg)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    public stbtic BlitBg getFromCbche(SurfbceType src,
                                      CompositeType comp,
                                      SurfbceType dst)
    {
        Object o = blitcbche.get(src, comp, dst);
        if (o != null) {
            return (BlitBg) o;
        }
        BlitBg blit = locbte(src, comp, dst);
        if (blit == null) {
            System.out.println("blitbg loop not found for:");
            System.out.println("src:  "+src);
            System.out.println("comp: "+comp);
            System.out.println("dst:  "+dst);
        } else {
            blitcbche.put(src, comp, dst, blit);
        }
        return blit;
    }

    protected BlitBg(SurfbceType srctype,
                     CompositeType comptype,
                     SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public BlitBg(long pNbtivePrim,
                  SurfbceType srctype,
                  CompositeType comptype,
                  SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    /**
     * All BlitBg implementors must hbve this invoker method
     */
    public nbtive void BlitBg(SurfbceDbtb src, SurfbceDbtb dst,
                              Composite comp, Region clip,
                              int bgColor,
                              int srcx, int srcy,
                              int dstx, int dsty,
                              int width, int height);

    stbtic {
        GrbphicsPrimitiveMgr.registerGenerbl(new BlitBg(null, null, null));
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        /*
        System.out.println("Constructing generbl blitbg for:");
        System.out.println("src:  "+srctype);
        System.out.println("comp: "+comptype);
        System.out.println("dst:  "+dsttype);
        */
        return new Generbl(srctype, comptype, dsttype);
    }

    privbte stbtic clbss Generbl extends BlitBg {
        CompositeType compositeType;

        public Generbl(SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
        {
            super(srctype, comptype, dsttype);
            compositeType = comptype;
        }

        @Override
        public void BlitBg(SurfbceDbtb srcDbtb,
                           SurfbceDbtb dstDbtb,
                           Composite comp,
                           Region clip,
                           int bgArgb,
                           int srcx, int srcy,
                           int dstx, int dsty,
                           int width, int height)
        {
            ColorModel dstModel = dstDbtb.getColorModel();
            boolebn bgHbsAlphb = (bgArgb >>> 24) != 0xff;
            if (!dstModel.hbsAlphb() && bgHbsAlphb) {
                dstModel = ColorModel.getRGBdefbult();
            }
            WritbbleRbster wr =
                dstModel.crebteCompbtibleWritbbleRbster(width, height);
            boolebn isPremult = dstModel.isAlphbPremultiplied();
            BufferedImbge bimg =
                new BufferedImbge(dstModel, wr, isPremult, null);
            SurfbceDbtb tmpDbtb = BufImgSurfbceDbtb.crebteDbtb(bimg);
            Color bgColor = new Color(bgArgb, bgHbsAlphb);
            SunGrbphics2D sg2d = new SunGrbphics2D(tmpDbtb, bgColor, bgColor,
                                                   defbultFont);
            FillRect fillop = FillRect.locbte(SurfbceType.AnyColor,
                                              CompositeType.SrcNoEb,
                                              tmpDbtb.getSurfbceType());
            Blit combineop = Blit.getFromCbche(srcDbtb.getSurfbceType(),
                                               CompositeType.SrcOverNoEb,
                                               tmpDbtb.getSurfbceType());
            Blit blitop = Blit.getFromCbche(tmpDbtb.getSurfbceType(), compositeType,
                                            dstDbtb.getSurfbceType());
            fillop.FillRect(sg2d, tmpDbtb, 0, 0, width, height);
            combineop.Blit(srcDbtb, tmpDbtb, AlphbComposite.SrcOver, null,
                           srcx, srcy, 0, 0, width, height);
            blitop.Blit(tmpDbtb, dstDbtb, comp, clip,
                        0, 0, dstx, dsty, width, height);
        }

        privbte stbtic Font defbultFont = new Font("Diblog", Font.PLAIN, 12);
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceBlitBg(this);
    }

    privbte stbtic clbss TrbceBlitBg extends BlitBg {
        BlitBg tbrget;

        public TrbceBlitBg(BlitBg tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        @Override
        public void BlitBg(SurfbceDbtb src, SurfbceDbtb dst,
                           Composite comp, Region clip,
                           int bgColor,
                           int srcx, int srcy, int dstx, int dsty,
                           int width, int height)
        {
            trbcePrimitive(tbrget);
            tbrget.BlitBg(src, dst, comp, clip, bgColor,
                          srcx, srcy, dstx, dsty, width, height);
        }
    }
}
