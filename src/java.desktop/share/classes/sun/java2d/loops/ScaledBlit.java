/*
 * Copyright (c) 2001, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * ScbledBlit
 * 1) copies rectbngle of pixels from one surfbce to bnother
 *    while scbling the pixels to meet the sizes specified
 * 2) performs compositing of colors bbsed upon b Composite
 *    pbrbmeter
 *
 * precise behbvior is undefined if the source surfbce
 * bnd the destinbtion surfbce bre the sbme surfbce
 * with overlbpping regions of pixels
 */

public clbss ScbledBlit extends GrbphicsPrimitive
{
    public stbtic finbl String methodSignbture = "ScbledBlit(...)".toString();

    public stbtic finbl int primTypeID = mbkePrimTypeID();

    privbte stbtic RenderCbche blitcbche = new RenderCbche(20);

    public stbtic ScbledBlit locbte(SurfbceType srctype,
                              CompositeType comptype,
                              SurfbceType dsttype)
    {
        return (ScbledBlit)
            GrbphicsPrimitiveMgr.locbte(primTypeID,
                                        srctype, comptype, dsttype);
    }

    public stbtic ScbledBlit getFromCbche(SurfbceType src,
                                          CompositeType comp,
                                          SurfbceType dst)
    {
        Object o = blitcbche.get(src, comp, dst);
        if (o != null) {
            return (ScbledBlit) o;
        }
        ScbledBlit blit = locbte(src, comp, dst);
        if (blit == null) {
            /*
            System.out.println("blit loop not found for:");
            System.out.println("src:  "+src);
            System.out.println("comp: "+comp);
            System.out.println("dst:  "+dst);
            */
        } else {
            blitcbche.put(src, comp, dst, blit);
        }
        return blit;
    }

    protected ScbledBlit(SurfbceType srctype,
                   CompositeType comptype,
                   SurfbceType dsttype)
    {
        super(methodSignbture, primTypeID, srctype, comptype, dsttype);
    }

    public ScbledBlit(long pNbtivePrim,
                      SurfbceType srctype,
                      CompositeType comptype,
                      SurfbceType dsttype)
    {
        super(pNbtivePrim, methodSignbture, primTypeID,
              srctype, comptype, dsttype);
    }

    public nbtive void Scble(SurfbceDbtb src, SurfbceDbtb dst,
                             Composite comp, Region clip,
                             int sx1, int sy1,
                             int sx2, int sy2,
                             double dx1, double dy1,
                             double dx2, double dy2);

    stbtic {
        GrbphicsPrimitiveMgr.registerGenerbl(new ScbledBlit(null, null, null));
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
        return null;
    }

    public GrbphicsPrimitive trbceWrbp() {
        return new TrbceScbledBlit(this);
    }

    privbte stbtic clbss TrbceScbledBlit extends ScbledBlit {
        ScbledBlit tbrget;

        public TrbceScbledBlit(ScbledBlit tbrget) {
            super(tbrget.getSourceType(),
                  tbrget.getCompositeType(),
                  tbrget.getDestType());
            this.tbrget = tbrget;
        }

        public GrbphicsPrimitive trbceWrbp() {
            return this;
        }

        public void Scble(SurfbceDbtb src, SurfbceDbtb dst,
                          Composite comp, Region clip,
                          int sx1, int sy1,
                          int sx2, int sy2,
                          double dx1, double dy1,
                          double dx2, double dy2)
        {
            trbcePrimitive(tbrget);
            tbrget.Scble(src, dst, comp, clip,
                         sx1, sy1, sx2, sy2,
                         dx1, dy1, dx2, dy2);
        }
    }
}
