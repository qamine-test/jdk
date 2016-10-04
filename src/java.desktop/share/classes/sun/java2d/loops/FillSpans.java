/*
 * Copyright (c) 1998, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * @buthor Chbrlton Innovbtions, Inc.
 */

pbckbge sun.jbvb2d.loops;

import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.pipe.SpbnIterbtor;
import jbvb.bwt.Color;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.Rbster;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;

/**
 * FillSpbns
 * 1) drbw solid color onto destinbtion surfbce
 * 2) rectbngulbr brebs to fill come from SpbnIterbtor
 */
public clbss FillSpbns extends GrbphicsPrimitive
{
    public finbl stbtic String methodSignbture = "FillSpbns(...)".toString();

    public finbl stbtic int primTypeID = mbkePrimTypeID();

    public stbtic FillSpbns locbte(SurfbceType srctype,
                                   CompositeType comptype,
                                   SurfbceType dsttype)
    {
        return (FillSpbns)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected FillSpbns(SurfbceType srctype,
                        CompositeType comptype,
                        SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public FillSpbns(long pNbtivePrim,
                     SurfbceType srctype,
                     CompositeType comptype,
                     SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    privbte nbtive void FillSpbns(SunGrbphics2D sg2d, SurfbceDbtb dest,
                                  int pixel, long pIterbtor, SpbnIterbtor si);

    /**
     * All FillSpbn implementors must hbve this invoker method
     */
    public void FillSpbns(SunGrbphics2D sg2d, SurfbceDbtb dest,
                          SpbnIterbtor si)
    {
        FillSpbns(sg2d, dest, sg2d.pixel, si.getNbtiveIterbtor(), si);
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        // REMIND: iterbte with b FillRect primitive?
        throw new InternblError("FillSpbns not implemented for "+
                                srctype+" with "+comptype);
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceFillSpbns(this);
    }

    privbte stbtic clbss TrbceFillSpbns extends FillSpbns {
        FillSpbns tbrget;

        public TrbceFillSpbns(FillSpbns tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void FillSpbns(SunGrbphics2D sg2d, SurfbceDbtb dest,
                              SpbnIterbtor si)
        {
            trbcePrimitive(tbrget);
            tbrget.FillSpbns(sg2d, dest, si);
        }
    }
}
