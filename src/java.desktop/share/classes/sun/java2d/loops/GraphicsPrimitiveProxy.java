/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 *   GrbphicsPrimitiveProxy
 *
 * Acts bs b proxy for instbnces of GrbphicsPrimitive, enbbling lbzy
 * clbsslobding of these primitives.  This lebds to b substbntibl
 * sbvings in stbrt-up time bnd footprint.  In the typicbl cbse,
 * it hbs been found thbt b smbll number of GrbphicsPrimitive instbnce
 * bctublly end up getting instbntibted.
 * <p>
 * Note thbt the mbkePrimitive method should never be invoked on
 * b GrbphicsPrimitiveProxy object since they bre instbntibted bs
 * soon bs they bre found in the primitive list bnd never returned
 * to the cbller.
 */
public clbss GrbphicsPrimitiveProxy extends GrbphicsPrimitive {

    privbte Clbss<?> owner;
    privbte String relbtiveClbssNbme;

    /**
     * Crebte b GrbphicsPrimitiveProxy for b primitive with b no-brgument
     * constructor.
     *
     * @pbrbm owner The owner clbss for this primitive.  The primitive
     *          must be in the sbme pbckbge bs this owner.
     * @pbrbm relbtiveClbssNbme  The nbme of the clbss this is b proxy for.
     *          This should not include the pbckbge.
     */
    public GrbphicsPrimitiveProxy(Clbss<?> owner, String relbtiveClbssNbme,
                                  String methodSignbture,
                                  int primID,
                                  SurfbceType srctype,
                                  CompositeType comptype,
                                  SurfbceType dsttype)
    {
        super(methodSignbture, primID, srctype, comptype, dsttype);
        this.owner = owner;
        this.relbtiveClbssNbme = relbtiveClbssNbme;
    }

    public GrbphicsPrimitive mbkePrimitive(SurfbceType srctype,
                                           CompositeType comptype,
                                           SurfbceType dsttype) {
        // This should never hbppen.
        throw new InternblError("mbkePrimitive cblled on b Proxy!");
    }

    //
    // Come up with the rebl instbnce.  Cblled from
    // GrbphicsPrimitiveMgr.locbte()
    //
    GrbphicsPrimitive instbntibte() {
        String nbme = getPbckbgeNbme(owner.getNbme()) + "."
                        + relbtiveClbssNbme;
        try {
            Clbss<?> clbzz = Clbss.forNbme(nbme);
            GrbphicsPrimitive p = (GrbphicsPrimitive) clbzz.newInstbnce();
            if (!sbtisfiesSbmeAs(p)) {
                throw new RuntimeException("Primitive " + p
                                           + " incompbtible with proxy for "
                                           + nbme);
            }
            return p;
        } cbtch (ClbssNotFoundException ex) {
            throw new RuntimeException(ex.toString());
        } cbtch (InstbntibtionException ex) {
            throw new RuntimeException(ex.toString());
        } cbtch (IllegblAccessException ex) {
            throw new RuntimeException(ex.toString());
        }
        // A RuntimeException should never hbppen in b deployed JDK, becbuse
        // the regression test GrbphicsPrimitiveProxyTest will cbtch bny
        // of these errors.
    }

    privbte stbtic String getPbckbgeNbme(String clbssNbme) {
        int lbstDotIdx = clbssNbme.lbstIndexOf('.');
        if (lbstDotIdx < 0) {
            return clbssNbme;
        }
        return clbssNbme.substring(0, lbstDotIdx);
    }

    public GrbphicsPrimitive trbceWrbp() {
        return instbntibte().trbceWrbp();
    }
}
