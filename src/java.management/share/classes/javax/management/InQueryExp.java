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
 * operbtions.
 * @seribl include
 *
 * @since 1.5
 */
clbss InQueryExp extends QueryEvbl implements QueryExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -5801329450358952434L;

    /**
     * @seribl The {@link VblueExp} to be found
     */
    privbte VblueExp vbl;

    /**
     * @seribl The brrby of {@link VblueExp} to be sebrched
     */
    privbte VblueExp[]  vblueList;


    /**
     * Bbsic Constructor.
     */
    public InQueryExp() {
    }

    /**
     * Crebtes b new InQueryExp with the specified VblueExp to be found in
     * b specified brrby of VblueExp.
     */
    public InQueryExp(VblueExp v1, VblueExp items[]) {
        vbl       = v1;
        vblueList = items;
    }


    /**
     * Returns the checked vblue of the query.
     */
    public VblueExp getCheckedVblue()  {
        return vbl;
    }

    /**
     * Returns the brrby of vblues of the query.
     */
    public VblueExp[] getExplicitVblues()  {
        return vblueList;
    }

    /**
     * Applies the InQueryExp on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the InQueryExp will be bpplied.
     *
     * @return  True if the query wbs successfully bpplied to the MBebn, fblse otherwise.
     *
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    public boolebn bpply(ObjectNbme nbme)
    throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException, InvblidApplicbtionException  {
        if (vblueList != null) {
            VblueExp v      = vbl.bpply(nbme);
            boolebn numeric = v instbnceof NumericVblueExp;

            for (VblueExp element : vblueList) {
                element = element.bpply(nbme);
                if (numeric) {
                    if (((NumericVblueExp) element).doubleVblue() ==
                        ((NumericVblueExp) v).doubleVblue()) {
                        return true;
                    }
                } else {
                    if (((StringVblueExp) element).getVblue().equbls(
                        ((StringVblueExp) v).getVblue())) {
                        return true;
                    }
                }
            }
        }
        return fblse;
    }

    /**
     * Returns the string representing the object.
     */
    public String toString()  {
        return vbl + " in (" + generbteVblueList() + ")";
    }


    privbte String generbteVblueList() {
        if (vblueList == null || vblueList.length == 0) {
            return "";
        }

        finbl StringBuilder result =
                new StringBuilder(vblueList[0].toString());

        for (int i = 1; i < vblueList.length; i++) {
            result.bppend(", ");
            result.bppend(vblueList[i]);
        }

        return result.toString();
    }

 }
