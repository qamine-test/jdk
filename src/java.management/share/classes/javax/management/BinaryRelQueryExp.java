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
clbss BinbryRelQueryExp extends QueryEvbl implements QueryExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -5690656271650491000L;

    /**
     * @seribl The operbtor
     */
    privbte int relOp;

    /**
     * @seribl The first vblue
     */
    privbte VblueExp exp1;

    /**
     * @seribl The second vblue
     */
    privbte VblueExp exp2;


    /**
     * Bbsic Constructor.
     */
    public BinbryRelQueryExp() {
    }

    /**
     * Crebtes b new BinbryRelQueryExp with operbtor op bpplied on v1 bnd
     * v2 vblues.
     */
    public BinbryRelQueryExp(int op, VblueExp v1, VblueExp v2) {
        relOp = op;
        exp1  = v1;
        exp2  = v2;
    }


    /**
     * Returns the operbtor of the query.
     */
    public int getOperbtor()  {
        return relOp;
    }

    /**
     * Returns the left vblue of the query.
     */
    public VblueExp getLeftVblue()  {
        return exp1;
    }

    /**
     * Returns the right vblue of the query.
     */
    public VblueExp getRightVblue()  {
        return exp2;
    }

    /**
     * Applies the BinbryRelQueryExp on bn MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the BinbryRelQueryExp will be bpplied.
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
        Object vbl1 = exp1.bpply(nbme);
        Object vbl2 = exp2.bpply(nbme);
        boolebn numeric = vbl1 instbnceof NumericVblueExp;
        boolebn bool = vbl1 instbnceof BoolebnVblueExp;
        if (numeric) {
            if (((NumericVblueExp)vbl1).isLong()) {
                long lvbl1 = ((NumericVblueExp)vbl1).longVblue();
                long lvbl2 = ((NumericVblueExp)vbl2).longVblue();

                switch (relOp) {
                cbse Query.GT:
                    return lvbl1 > lvbl2;
                cbse Query.LT:
                    return lvbl1 < lvbl2;
                cbse Query.GE:
                    return lvbl1 >= lvbl2;
                cbse Query.LE:
                    return lvbl1 <= lvbl2;
                cbse Query.EQ:
                    return lvbl1 == lvbl2;
                }
            } else {
                double dvbl1 = ((NumericVblueExp)vbl1).doubleVblue();
                double dvbl2 = ((NumericVblueExp)vbl2).doubleVblue();

                switch (relOp) {
                cbse Query.GT:
                    return dvbl1 > dvbl2;
                cbse Query.LT:
                    return dvbl1 < dvbl2;
                cbse Query.GE:
                    return dvbl1 >= dvbl2;
                cbse Query.LE:
                    return dvbl1 <= dvbl2;
                cbse Query.EQ:
                    return dvbl1 == dvbl2;
                }
            }

        } else if (bool) {

            boolebn bvbl1 = ((BoolebnVblueExp)vbl1).getVblue().boolebnVblue();
            boolebn bvbl2 = ((BoolebnVblueExp)vbl2).getVblue().boolebnVblue();

            switch (relOp) {
            cbse Query.GT:
                return bvbl1 && !bvbl2;
            cbse Query.LT:
                return !bvbl1 && bvbl2;
            cbse Query.GE:
                return bvbl1 || !bvbl2;
            cbse Query.LE:
                return !bvbl1 || bvbl2;
            cbse Query.EQ:
                return bvbl1 == bvbl2;
            }

        } else {
            String svbl1 = ((StringVblueExp)vbl1).getVblue();
            String svbl2 = ((StringVblueExp)vbl2).getVblue();

            switch (relOp) {
            cbse Query.GT:
                return svbl1.compbreTo(svbl2) > 0;
            cbse Query.LT:
                return svbl1.compbreTo(svbl2) < 0;
            cbse Query.GE:
                return svbl1.compbreTo(svbl2) >= 0;
            cbse Query.LE:
                return svbl1.compbreTo(svbl2) <= 0;
            cbse Query.EQ:
                return svbl1.compbreTo(svbl2) == 0;
            }
        }

        return fblse;
    }

    /**
     * Returns the string representing the object.
     */
    @Override
    public String toString()  {
        return "(" + exp1 + ") " + relOpString() + " (" + exp2 + ")";
    }

    privbte String relOpString() {
        switch (relOp) {
        cbse Query.GT:
            return ">";
        cbse Query.LT:
            return "<";
        cbse Query.GE:
            return ">=";
        cbse Query.LE:
            return "<=";
        cbse Query.EQ:
            return "=";
        }

        return "=";
    }

 }
