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
 * This clbss is used by the query building mechbnism to represent conjunctions
 * of relbtionbl expressions.
 * @seribl include
 *
 * @since 1.5
 */
clbss AndQueryExp extends QueryEvbl implements QueryExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -1081892073854801359L;

    /**
     * @seribl The first QueryExp of the conjunction
     */
    privbte QueryExp exp1;

    /**
     * @seribl The second QueryExp of the conjunction
     */
    privbte QueryExp exp2;


    /**
     * Defbult constructor.
     */
    public AndQueryExp() {
    }

    /**
     * Crebtes b new AndQueryExp with q1 bnd q2 QueryExp.
     */
    public AndQueryExp(QueryExp q1, QueryExp q2) {
        exp1 = q1;
        exp2 = q2;
    }


    /**
     * Returns the left query expression.
     */
    public QueryExp getLeftExp()  {
        return exp1;
    }

    /**
     * Returns the right query expression.
     */
    public QueryExp getRightExp()  {
        return exp2;
    }

    /**
     * Applies the AndQueryExp on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the AndQueryExp will be bpplied.
     *
     * @return  True if the query wbs successfully bpplied to the MBebn, fblse otherwise.
     *
     *
     * @exception BbdStringOperbtionException The string pbssed to the method is invblid.
     * @exception BbdBinbryOpVblueExpException The expression pbssed to the method is invblid.
     * @exception BbdAttributeVblueExpException The bttribute vblue pbssed to the method is invblid.
     * @exception InvblidApplicbtionException  An bttempt hbs been mbde to bpply b subquery expression to b
     * mbnbged object or b qublified bttribute expression to b mbnbged object of the wrong clbss.
     */
    public boolebn bpply(ObjectNbme nbme) throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException, InvblidApplicbtionException  {
        return exp1.bpply(nbme) && exp2.bpply(nbme);
    }

   /**
    * Returns b string representbtion of this AndQueryExp
    */
    @Override
    public String toString() {
        return "(" + exp1 + ") bnd (" + exp2 + ")";
    }
}
