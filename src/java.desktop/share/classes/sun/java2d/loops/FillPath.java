/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.SunGrbphics2D;
import sun.jbvb2d.SurfbceDbtb;
import jbvb.bwt.geom.Pbth2D;

/**
 *   FillPbth
 *   1. fill pbth onto destinbtion surfbce
 *   2. must bccept output breb [x, y, dx, dy]
 *      from within the surfbce description dbtb for clip rect
 */
public clbss FillPbth extends GrbphicsPrimitive {

    public finbl stbtic String methodSignbture =
        "FillPbth(...)".toString();

    public finbl stbtic int primTypeID = mbkePrimTypeID();

    public stbtic FillPbth locbte(SurfbceType srctype,
                                  CompositeType comptype,
                                  SurfbceType dsttype)
    {
        return (FillPbth)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    protected FillPbth(SurfbceType srctype,
                       CompositeType comptype,
                       SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    public FillPbth(long pNbtivePrim,
                    SurfbceType srctype,
                    CompositeType comptype,
                    SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }


    /**
     *   All FillPbth implementors must hbve this invoker method
     */
    public nbtive void FillPbth(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                                int trbnsX, int trbnsY,
                                Pbth2D.Flobt p2df);

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        throw new InternblError("FillPbth not implemented for "+
                                srctype+" with "+comptype);
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceFillPbth(this);
    }

    privbte stbtic clbss TrbceFillPbth extends FillPbth {
        FillPbth tbrget;

        public TrbceFillPbth(FillPbth tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void FillPbth(SunGrbphics2D sg2d, SurfbceDbtb sDbtb,
                             int trbnsX, int trbnsY,
                             Pbth2D.Flobt p2df)
        {
            trbcePrimitive(tbrget);
            tbrget.FillPbth(sg2d, sDbtb, trbnsX, trbnsY, p2df);
        }
    }
}
