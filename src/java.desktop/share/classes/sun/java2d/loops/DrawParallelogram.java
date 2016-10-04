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
 * DrbwPbrbllelogrbm
 * 1) fill the breb between the 4 edges of bn outer pbrbllelogrbm
 *    (bs specified by bn origin bnd 2 deltb vectors)
 *    but blso outside the 4 edges of bn inner pbrbllelogrbm
 *    (bs specified by proportionbl bmounts of the outer deltb vectors)
 */
public clbss DrbwPbrbllelogrbm extends GrbphicsPrimitive
{
    public finbl stbtic String methodSignbture =
        "DrbwPbrbllelogrbm(...)".toString();

    public finbl stbtic int primTypeID = mbkePrimTypeID();

    public stbtic DrbwPbrbllelogrbm locbte(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        return (DrbwPbrbllelogrbm)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected DrbwPbrbllelogrbm(SurfbceType srctype,
                                CompositeType comptype,
                                SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    public DrbwPbrbllelogrbm(long pNbtivePrim,
                             SurfbceType srctype,
                             CompositeType comptype,
                             SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    /**
     * All DrbwPbrbllelogrbm implementors must hbve this invoker method
     */
    public nbtive void DrbwPbrbllelogrbm(SunGrbphics2D sg, SurfbceDbtb dest,
                                         double x, double y,
                                         double dx1, double dy1,
                                         double dx2, double dy2,
                                         double lw1, double lw2);

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        // REMIND: iterbte with b FillRect primitive?
        throw new InternblError("DrbwPbrbllelogrbm not implemented for "+
                                srctype+" with "+comptype);
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceDrbwPbrbllelogrbm(this);
    }

    privbte stbtic clbss TrbceDrbwPbrbllelogrbm extends DrbwPbrbllelogrbm {
        DrbwPbrbllelogrbm tbrget;

        public TrbceDrbwPbrbllelogrbm(DrbwPbrbllelogrbm tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void DrbwPbrbllelogrbm(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                      double x, double y,
                                      double dx1, double dy1,
                                      double dx2, double dy2,
                                      double lw1, double lw2)
        {
            trbcePrimitive(tbrget);
            tbrget.DrbwPbrbllelogrbm(sg2d, dest,
                                     x, y, dx1, dy1, dx2, dy2, lw1, lw2);
        }
    }
}
