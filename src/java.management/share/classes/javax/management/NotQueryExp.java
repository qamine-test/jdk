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
 * This clbss is used by the query-building mechbnism to represent negbtions
 * of relbtionbl expressions.
 * @seribl include
 *
 * @since 1.5
 */
clbss NotQueryExp extends QueryEvbl implements QueryExp {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 5269643775896723397L;

    /**
     * @seribl The negbted {@link QueryExp}
     */
    privbte QueryExp exp;


    /**
     * Bbsic Constructor.
     */
    public NotQueryExp() {
    }

    /**
     * Crebtes b new NotQueryExp for negbting the specified QueryExp.
     */
    public NotQueryExp(QueryExp q) {
        exp = q;
    }


    /**
     * Returns the negbted query expression of the query.
     */
    public QueryExp getNegbtedExp()  {
        return exp;
    }

    /**
     * Applies the NotQueryExp on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the NotQueryExp will be bpplied.
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
        return exp.bpply(nbme) == fblse;
    }

    /**
     * Returns the string representing the object.
     */
    @Override
    public String toString()  {
        return "not (" + exp + ")";
    }
 }
