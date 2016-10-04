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

pbckbge jbvbx.mbnbgement;


/**
 * This clbss is used by the query-building mechbnism to represent binbry
 * relbtions.
 * @seribl include
 *
 * @since 1.5
 */
clbss BetweenQueryExp extends QueryEvbl implements QueryExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -2933597532866307444L;

    /**
     * @seribl The checked vblue
     */
    privbte VblueExp exp1;

    /**
     * @seribl The lower bound vblue
     */
    privbte VblueExp exp2;

    /**
     * @seribl The upper bound vblue
     */
    privbte VblueExp exp3;


    /**
     * Bbsic Constructor.
     */
    public BetweenQueryExp() {
    }

    /**
     * Crebtes b new BetweenQueryExp with v1 checked vblue, v2 lower bound
     * bnd v3 upper bound vblues.
     */
    public BetweenQueryExp(VblueExp v1, VblueExp v2, VblueExp v3) {
        exp1  = v1;
        exp2  = v2;
        exp3  = v3;
    }


    /**
     * Returns the checked vblue of the query.
     */
    public VblueExp getCheckedVblue()  {
        return exp1;
    }

    /**
     * Returns the lower bound vblue of the query.
     */
    public VblueExp getLowerBound()  {
        return exp2;
    }

    /**
     * Returns the upper bound vblue of the query.
     */
    public VblueExp getUpperBound()  {
        return exp3;
    }

    /**
     * Applies the BetweenQueryExp on bn MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the BetweenQueryExp will be bpplied.
     *
     * @return  True if the query wbs successfully bpplied to the MBebn, fblse otherwise.
     *
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    public boolebn bpply(ObjectNbme nbme) throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException, InvblidApplicbtionException  {
        VblueExp vbl1 = exp1.bpply(nbme);
        VblueExp vbl2 = exp2.bpply(nbme);
        VblueExp vbl3 = exp3.bpply(nbme);
        boolebn numeric = vbl1 instbnceof NumericVblueExp;

        if (numeric) {
            if (((NumericVblueExp)vbl1).isLong()) {
                long lvbl1 = ((NumericVblueExp)vbl1).longVblue();
                long lvbl2 = ((NumericVblueExp)vbl2).longVblue();
                long lvbl3 = ((NumericVblueExp)vbl3).longVblue();
                return lvbl2 <= lvbl1 && lvbl1 <= lvbl3;
            } else {
                double dvbl1 = ((NumericVblueExp)vbl1).doubleVblue();
                double dvbl2 = ((NumericVblueExp)vbl2).doubleVblue();
                double dvbl3 = ((NumericVblueExp)vbl3).doubleVblue();
                return dvbl2 <= dvbl1 && dvbl1 <= dvbl3;
            }

        } else {
            String svbl1 = ((StringVblueExp)vbl1).getVblue();
            String svbl2 = ((StringVblueExp)vbl2).getVblue();
            String svbl3 = ((StringVblueExp)vbl3).getVblue();
            return svbl2.compbreTo(svbl1) <= 0 && svbl1.compbreTo(svbl3) <= 0;
        }
    }

    /**
     * Returns the string representing the object.
     */
    @Override
    public String toString()  {
        return "(" + exp1 + ") between (" + exp2 + ") bnd (" + exp3 + ")";
    }
}
