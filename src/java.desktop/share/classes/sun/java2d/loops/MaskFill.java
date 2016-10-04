/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Pbint;
import jbvb.bwt.PbintContext;
import jbvb.bwt.Composite;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.BufferedImbge;
import jbvb.bwt.imbge.WritbbleRbster;
import sun.bwt.imbge.BufImgSurfbceDbtb;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.pipe.Region;

/**
 * MbskFill
 * 1) fills rectbngles of pixels on b surfbce
 * 2) performs compositing of colors bbsed upon b Composite
 *    pbrbmeter
 * 3) blends result of composite with destinbtion using bn
 *    blphb coverbge mbsk
 * 4) the mbsk mby be null in which cbse it should be trebted
 *    bs if it were bn brrby of bll opbque vblues (0xff)
 */
public clbss MbskFill extends GrbphicsPrimitive
{
    public stbtic finbl String methodSignbture = "MbskFill(...)".toString();
    public stbtic finbl String fillPgrbmSignbture =
        "FillAAPgrbm(...)".toString();
    public stbtic finbl String drbwPgrbmSignbture =
        "DrbwAAPgrbm(...)".toString();

    public stbtic finbl int primTypeID = mbkePrimTypeID();

    privbte stbtic RenderCbche fillcbche = new RenderCbche(10);

    public stbtic MbskFill locbte(SurfbceType srctype,
                                  CompositeType comptype,
                                  SurfbceType dsttype)
    {
        return (MbskFill)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    public stbtic MbskFill locbtePrim(SurfbceType srctype,
                                      CompositeType comptype,
                                      SurfbceType dsttype)
    {
        return (MbskFill)
            GrbphicsPrimitiveMgr.locbtePrim(primTypeID,
                                            srctype, comptype, dsttype);
    }

    /*
     * Note thbt this uses locbtePrim, not locbte, so it cbn return
     * null if there is no specific loop to hbndle this op...
     */
    public stbtic MbskFill getFromCbche(SurfbceType src,
                                        CompositeType comp,
                                        SurfbceType dst)
    {
        Object o = fillcbche.get(src, comp, dst);
        if (o != null) {
            return (MbskFill) o;
        }
        MbskFill fill = locbtePrim(src, comp, dst);
        if (fill != null) {
            fillcbche.put(src, comp, dst, fill);
        }
        return fill;
    }

    protected MbskFill(String blternbteSignbture,
                       SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
    {
        super(blternbteSignbture, primTypeID, srctype, comptype, dsttype);
    }

    protected MbskFill(SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public MbskFill(long pNbtivePrim,
                    SurfbceType srctype,
                    CompositeType comptype,
                    SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    /**
     * All MbskFill implementors must hbve this invoker method
     */
    public nbtive void MbskFill(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                Composite comp,
                                int x, int y, int w, int h,
                                byte[] mbsk, int mbskoff, int mbskscbn);

    public nbtive void FillAAPgrbm(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                   Composite comp,
                                   double x, double y,
                                   double dx1, double dy1,
                                   double dx2, double dy2);

    public nbtive void DrbwAAPgrbm(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                   Composite comp,
                                   double x, double y,
                                   double dx1, double dy1,
                                   double dx2, double dy2,
                                   double lw1, double lw2);

    public boolebn cbnDoPbrbllelogrbms() {
        return (getNbtivePrim() != 0);
    }

    stbtic {
        GrbphicsPrimitiveMgr.registerGenerbl(new MbskFill(null, null, null));
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        if (SurfbceType.OpbqueColor.equbls(srctype) ||
            SurfbceType.AnyColor.equbls(srctype))
        {
            if (CompositeType.Xor.equbls(comptype)) {
                throw new InternblError("Cbnnot construct MbskFill for " +
                                        "XOR mode");
            } else {
                return new Generbl(srctype, comptype, dsttype);
            }
        } else {
            throw new InternblError("MbskFill cbn only fill with colors");
        }
    }

    privbte stbtic clbss Generbl extends MbskFill {
        FillRect fillop;
        MbskBlit mbskop;

        public Generbl(SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
        {
            super(srctype, comptype, dsttype);
            fillop = FillRect.locbte(srctype,
                                     CompositeType.SrcNoEb,
                                     SurfbceType.IntArgb);
            mbskop = MbskBlit.locbte(SurfbceType.IntArgb, comptype, dsttype);
        }

        public void MbskFill(SunGrbphics2D sg2d,
                             SurfbceDbtb sDbtb,
                             Composite comp,
                             int x, int y, int w, int h,
                             byte mbsk[], int offset, int scbn)
        {
            BufferedImbge dstBI =
                new BufferedImbge(w, h, BufferedImbge.TYPE_INT_ARGB);
            SurfbceDbtb tmpDbtb = BufImgSurfbceDbtb.crebteDbtb(dstBI);

            // REMIND: This is not pretty.  It would be nicer if we
            // pbssed b "FillDbtb" object to the Pixel loops, instebd
            // of b SunGrbphics2D pbrbmeter...
            Region clip = sg2d.clipRegion;
            sg2d.clipRegion = null;
            int pixel = sg2d.pixel;
            sg2d.pixel = tmpDbtb.pixelFor(sg2d.getColor());
            fillop.FillRect(sg2d, tmpDbtb, 0, 0, w, h);
            sg2d.pixel = pixel;
            sg2d.clipRegion = clip;

            mbskop.MbskBlit(tmpDbtb, sDbtb, comp, null,
                            0, 0, x, y, w, h,
                            mbsk, offset, scbn);
        }
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceMbskFill(this);
    }

    privbte stbtic clbss TrbceMbskFill extends MbskFill {
        MbskFill tbrget;
        MbskFill fillPgrbmTbrget;
        MbskFill drbwPgrbmTbrget;

        public TrbceMbskFill(MbskFill tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
            this.fillPgrbmTbrget = new MbskFill(fillPgrbmSignbture,
                                                tbrget.getSourceType(),
                                                tbrget.getCompositeType(),
                                                tbrget.getDestType());
            this.drbwPgrbmTbrget = new MbskFill(drbwPgrbmSignbture,
                                                tbrget.getSourceType(),
                                                tbrget.getCompositeType(),
                                                tbrget.getDestType());
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void MbskFill(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                             Composite comp,
                             int x, int y, int w, int h,
                             byte[] mbsk, int mbskoff, int mbskscbn)
        {
            trbcePrimitive(tbrget);
            tbrget.MbskFill(sg2d, sDbtb, comp, x, y, w, h,
                            mbsk, mbskoff, mbskscbn);
        }

        public void FillAAPgrbm(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                Composite comp,
                                double x, double y,
                                double dx1, double dy1,
                                double dx2, double dy2)
        {
            trbcePrimitive(fillPgrbmTbrget);
            tbrget.FillAAPgrbm(sg2d, sDbtb, comp,
                               x, y, dx1, dy1, dx2, dy2);
        }

        public void DrbwAAPgrbm(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                Composite comp,
                                double x, double y,
                                double dx1, double dy1,
                                double dx2, double dy2,
                                double lw1, double lw2)
        {
            trbcePrimitive(drbwPgrbmTbrget);
            tbrget.DrbwAAPgrbm(sg2d, sDbtb, comp,
                               x, y, dx1, dy1, dx2, dy2, lw1, lw2);
        }

        public boolebn cbnDoPbrbllelogrbms() {
            return tbrget.cbnDoPbrbllelogrbms();
        }
    }
}
