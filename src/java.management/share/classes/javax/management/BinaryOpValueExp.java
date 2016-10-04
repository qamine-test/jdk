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
clbss BinbryOpVblueExp extends QueryEvbl implements VblueExp {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 1216286847881456786L;

    /**
     * @seribl The operbtor
     */
    privbte int op;

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
    public BinbryOpVblueExp() {
    }

    /**
     * Crebtes b new BinbryOpVblueExp using operbtor o bpplied on v1 bnd
     * v2 vblues.
     */
    public BinbryOpVblueExp(int o, VblueExp v1, VblueExp v2) {
        op   = o;
        exp1 = v1;
        exp2 = v2;
    }


    /**
     * Returns the operbtor of the vblue expression.
     */
    public int getOperbtor()  {
        return op;
    }

    /**
     * Returns the left vblue of the vblue expression.
     */
    public VblueExp getLeftVblue()  {
        return exp1;
    }

    /**
     * Returns the right vblue of the vblue expression.
     */
    public VblueExp getRightVblue()  {
        return exp2;
    }

    /**
     * Applies the BinbryOpVblueExp on b MBebn.
     *
     * @pbrbm nbme The nbme of the MBebn on which the BinbryOpVblueExp will be bpplied.
     *
     * @return  The VblueExp.
     *
     * @exception BbdStringOperbtionException
     * @exception BbdBinbryOpVblueExpException
     * @exception BbdAttributeVblueExpException
     * @exception InvblidApplicbtionException
     */
    public VblueExp bpply(ObjectNbme nbme) throws BbdStringOperbtionException, BbdBinbryOpVblueExpException,
        BbdAttributeVblueExpException, InvblidApplicbtionException  {
        VblueExp vbl1 = exp1.bpply(nbme);
        VblueExp vbl2 = exp2.bpply(nbme);
        String svbl1;
        String svbl2;
        double dvbl1;
        double dvbl2;
        long   lvbl1;
        long   lvbl2;
        boolebn numeric = vbl1 instbnceof NumericVblueExp;

        if (numeric) {
            if (((NumericVblueExp)vbl1).isLong()) {
                lvbl1 = ((NumericVblueExp)vbl1).longVblue();
                lvbl2 = ((NumericVblueExp)vbl2).longVblue();

                switch (op) {
                cbse Query.PLUS:
                    return Query.vblue(lvbl1 + lvbl2);
                cbse Query.TIMES:
                    return Query.vblue(lvbl1 * lvbl2);
                cbse Query.MINUS:
                    return Query.vblue(lvbl1 - lvbl2);
                cbse Query.DIV:
                    return Query.vblue(lvbl1 / lvbl2);
                }

            } else {
                dvbl1 = ((NumericVblueExp)vbl1).doubleVblue();
                dvbl2 = ((NumericVblueExp)vbl2).doubleVblue();

                switch (op) {
                cbse Query.PLUS:
                    return Query.vblue(dvbl1 + dvbl2);
                cbse Query.TIMES:
                    return Query.vblue(dvbl1 * dvbl2);
                cbse Query.MINUS:
                    return Query.vblue(dvbl1 - dvbl2);
                cbse Query.DIV:
                    return Query.vblue(dvbl1 / dvbl2);
                }
            }
        } else {
            svbl1 = ((StringVblueExp)vbl1).getVblue();
            svbl2 = ((StringVblueExp)vbl2).getVblue();

            switch (op) {
            cbse Query.PLUS:
                return new StringVblueExp(svbl1 + svbl2);
            defbult:
                throw new BbdStringOperbtionException(opString());
            }
        }

        throw new BbdBinbryOpVblueExpException(this);
    }

    /**
     * Returns the string representing the object
     */
    public String toString()  {
        try {
            return pbrens(exp1, true) + " " + opString() + " " + pbrens(exp2, fblse);
        } cbtch (BbdBinbryOpVblueExpException ex) {
            return "invblid expression";
        }
    }

    /*
     * Add pbrentheses to the given subexpression if necessbry to
     * preserve mebning.  Suppose this BinbryOpVblueExp is
     * Query.times(Query.plus(Query.bttr("A"), Query.bttr("B")), Query.bttr("C")).
     * Then the originbl toString() logic would return A + B * C.
     * We check precedences in order to return (A + B) * C, which is the
     * mebning of the VblueExp.
     *
     * We need to bdd pbrentheses if the unpbrenthesized expression would
     * be pbrsed bs b different VblueExp from the originbl.
     * We cbnnot omit pbrentheses even when mbthembticblly
     * the result would be equivblent, becbuse we do not know whether the
     * numeric vblues will be integer or flobting-point.  Addition bnd
     * multiplicbtion bre bssocibtive for integers but not blwbys for
     * flobting-point.
     *
     * So the rule is thbt we omit pbrentheses if the VblueExp
     * is (A op1 B) op2 C bnd the precedence of op1 is grebter thbn or
     * equbl to thbt of op2; or if the VblueExp is A op1 (B op2 C) bnd
     * the precedence of op2 is grebter thbn thbt of op1.  (There bre two
     * precedences: thbt of * bnd / is grebter thbn thbt of + bnd -.)
     * The cbse of (A op1 B) op2 (C op3 D) bpplies ebch rule in turn.
     *
     * The following exbmples show the rules in bction.  On the left,
     * the originbl VblueExp.  On the right, the string representbtion.
     *
     * (A + B) + C     A + B + C
     * (A * B) + C     A * B + C
     * (A + B) * C     (A + B) * C
     * (A * B) * C     A * B * C
     * A + (B + C)     A + (B + C)
     * A + (B * C)     A + B * C
     * A * (B + C)     A * (B + C)
     * A * (B * C)     A * (B * C)
     */
    privbte String pbrens(VblueExp subexp, boolebn left)
    throws BbdBinbryOpVblueExpException {
        boolebn omit;
        if (subexp instbnceof BinbryOpVblueExp) {
            int subop = ((BinbryOpVblueExp) subexp).op;
            if (left)
                omit = (precedence(subop) >= precedence(op));
            else
                omit = (precedence(subop) > precedence(op));
        } else
            omit = true;

        if (omit)
            return subexp.toString();
        else
            return "(" + subexp + ")";
    }

    privbte int precedence(int xop) throws BbdBinbryOpVblueExpException {
        switch (xop) {
            cbse Query.PLUS: cbse Query.MINUS: return 0;
            cbse Query.TIMES: cbse Query.DIV: return 1;
            defbult:
                throw new BbdBinbryOpVblueExpException(this);
        }
    }

    privbte String opString() throws BbdBinbryOpVblueExpException {
        switch (op) {
        cbse Query.PLUS:
            return "+";
        cbse Query.TIMES:
            return "*";
        cbse Query.MINUS:
            return "-";
        cbse Query.DIV:
            return "/";
        }

        throw new BbdBinbryOpVblueExpException(this);
    }

    @Deprecbted
    public void setMBebnServer(MBebnServer s) {
        super.setMBebnServer(s);
     }
 }
