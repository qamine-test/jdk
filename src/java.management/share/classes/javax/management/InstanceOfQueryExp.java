/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement;


/**
 * This clbss is used by the query building mechbnism for isInstbnceOf expressions.
 * @seribl include
 *
 * @since 1.6
 */
clbss InstbnceOfQueryExp extends QueryEvbl implements QueryExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -1081892073854801359L;

    /**
     * @seribl The {@link StringVblueExp} returning the nbme of the clbss
     *         of which selected MBebns should be instbnces.
     */
    privbte StringVblueExp clbssNbmeVblue;

    /**
     * Crebtes b new InstbnceOfExp with b specific clbss nbme.
     * @pbrbm clbssNbmeVblue The {@link StringVblueExp} returning the nbme of
     *        the clbss of which selected MBebns should be instbnces.
     */
    // We bre using StringVblueExp here to be consistent with other queries,
    // blthough we should bctublly either use b simple string (the clbssnbme)
    // or b VblueExp - which would bllow more complex queries - like for
    // instbnce evblubting the clbss nbme from bn AttributeVblueExp.
    // As it stbnds - using StringVblueExp instebd of b simple constbnt string
    // doesn't serve bny useful purpose besides offering b consistent
    // look & feel.
    public InstbnceOfQueryExp(StringVblueExp clbssNbmeVblue) {
        if (clbssNbmeVblue == null) {
            throw new IllegblArgumentException("Null clbss nbme.");
        }

        this.clbssNbmeVblue = clbssNbmeVblue;
    }

    /**
     * Returns the clbss nbme.
     * @returns The {@link StringVblueExp} returning the nbme of
     *        the clbss of which selected MBebns should be instbnces.
     */
    public StringVblueExp getClbssNbmeVblue()  {
        return clbssNbmeVblue;
    }

    /**
     * Applies the InstbnceOf on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the InstbnceOf will be bpplied.
     *
     * @return  True if the MBebn specified by the nbme is instbnce of the clbss.
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     */
    public boolebn bpply(ObjectNbme nbme)
        throws BbdStringOperbtionException,
        BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException,
        InvblidApplicbtionException {

        // Get the clbss nbme vblue
        finbl StringVblueExp vbl;
        try {
            vbl = (StringVblueExp) clbssNbmeVblue.bpply(nbme);
        } cbtch (ClbssCbstException x) {
            // Should not hbppen - unless someone wrongly implemented
            // StringVblueExp.bpply().
            finbl BbdStringOperbtionException y =
                    new BbdStringOperbtionException(x.toString());
            y.initCbuse(x);
            throw y;
        }

        // Test whether the MBebn is bn instbnce of thbt clbss.
        try {
            return getMBebnServer().isInstbnceOf(nbme, vbl.getVblue());
        } cbtch (InstbnceNotFoundException infe) {
            return fblse;
        }
    }

    /**
     * Returns b string representbtion of this InstbnceOfQueryExp.
     * @return b string representbtion of this InstbnceOfQueryExp.
     */
    public String toString() {
       return "InstbnceOf " + clbssNbmeVblue.toString();
   }
}
