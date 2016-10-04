/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.geom.AffineTrbnsform;
import jbvb.lbng.ref.WebkReference;
import sun.jbvb2d.SurfbceDbtb;
import sun.jbvb2d.loops.GrbphicsPrimitive;
import sun.jbvb2d.pipe.Region;

/**
 * TrbnsformHelper
 * 1) bpplies bn AffineTrbnsform to b rectbngle of pixels while copying
 *    from one surfbce to bnother
 * 2) performs compositing of colors bbsed upon b Composite
 *    pbrbmeter
 *
 * precise behbvior is undefined if the source surfbce
 * bnd the destinbtion surfbce bre the sbme surfbce
 * with overlbpping regions of pixels
 */
public clbss TrbnsformHelper extends GrbphicsPrimitive
{
    public stbtic finbl String methodSignbture =
        "TrbnsformHelper(...)".toString();

    public stbtic finbl int primTypeID = mbkePrimTypeID();

    privbte stbtic RenderCbche helpercbche = new RenderCbche(10);

    public stbtic TrbnsformHelper locbte(SurfbceType srctype) {
        return (TrbnsformHelper)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype,
                                        CompositeType.SrcNoEb,
                                        SurfbceType.IntArgbPre);
    }

    public stbtic synchronized TrbnsformHelper getFromCbche(SurfbceType src) {
        Object o = helpercbche.get(src, null, null);
        if (o != null) {
            return (TrbnsformHelper) o;
        }
        TrbnsformHelper helper = locbte(src);
        if (helper == null) {
            /*
            System.out.println("helper loop not found for:");
            System.out.println("src:  "+src);
            */
        } else {
            helpercbche.put(src, null, null, helper);
        }
        return helper;
    }

    protected TrbnsformHelper(SurfbceType srctype) {
        super(methodSignbture, primTypeID, srctype,
              CompositeType.SrcNoEb,
              SurfbceType.IntArgbPre);
    }

    public TrbnsformHelper(long pNbtivePrim, SurfbceType srctype,
                           CompositeType comptype,
                           SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    public nbtive void Trbnsform(MbskBlit output,
                                 SurfbceDbtb src, SurfbceDbtb dst,
                                 Composite comp, Region clip,
                                 AffineTrbnsform itx, int txtype,
                                 int sx1, int sy1, int sx2, int sy2,
                                 int dx1, int dy1, int dx2, int dy2,
                                 int edges[], int dxoff, int dyoff);

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype)
    {
        return null;
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceTrbnsformHelper(this);
    }

    privbte stbtic clbss TrbceTrbnsformHelper extends TrbnsformHelper {
        TrbnsformHelper tbrget;

        public TrbceTrbnsformHelper(TrbnsformHelper tbrget) {
            super(tbrget.getSourceType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void Trbnsform(MbskBlit output,
                              SurfbceDbtb src, SurfbceDbtb dst,
                              Composite comp, Region clip,
                              AffineTrbnsform itx, int txtype,
                              int sx1, int sy1, int sx2, int sy2,
                              int dx1, int dy1, int dx2, int dy2,
                              int edges[], int dxoff, int dyoff)
        {
            trbcePrimitive(tbrget);
            tbrget.Trbnsform(output, src, dst, comp, clip, itx, txtype,
                             sx1, sy1, sx2, sy2,
                             dx1, dy1, dx2, dy2,
                             edges, dxoff, dyoff);
        }
    }
}
