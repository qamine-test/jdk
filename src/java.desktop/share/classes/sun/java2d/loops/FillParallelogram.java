/*
 * Copyright (c) 2008, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * @buthor Jim Grbhbm
 */

pbckbge sun.jbvb2d.loops;

import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;

/**
 * FillPbrbllelogrbm
 * 1) fill the breb between the 4 edges of b pbrbllelogrbm
 *    (bs specified by bn origin bnd 2 deltb vectors)
 */
public clbss FillPbrbllelogrbm extends GrbphicsPrimitive
{
    public finbl stbtic String methodSignbture =
        "FillPbrbllelogrbm(...)".toString();

    public finbl stbtic int primTypeID = mbkePrimTypeID();

    public stbtic FillPbrbllelogrbm locbte(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        return (FillPbrbllelogrbm)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected FillPbrbllelogrbm(SurfbceType srctype,
                                CompositeType comptype,
                                SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    public FillPbrbllelogrbm(long pNbtivePrim,
                             SurfbceType srctype,
                             CompositeType comptype,
                             SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    /**
     * All FillPbrbllelogrbm implementors must hbve this invoker method
     */
    public nbtive void FillPbrbllelogrbm(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                         double x0, double y0,
                                         double dx1, double dy1,
                                         double dx2, double dy2);

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        // REMIND: iterbte with b FillRect primitive?
        throw new InternblError("FillPbrbllelogrbm not implemented for "+
                                srctype+" with "+comptype);
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceFillPbrbllelogrbm(this);
    }

    privbte stbtic clbss TrbceFillPbrbllelogrbm extends FillPbrbllelogrbm {
        FillPbrbllelogrbm tbrget;

        public TrbceFillPbrbllelogrbm(FillPbrbllelogrbm tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void FillPbrbllelogrbm(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                      double x0, double y0,
                                      double dx1, double dy1,
                                      double dx2, double dy2)
        {
            trbcePrimitive(tbrget);
            tbrget.FillPbrbllelogrbm(sg2d, dest, x0, y0, dx1, dy1, dx2, dy2);
        }
    }
}
