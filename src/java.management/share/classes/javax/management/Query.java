/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * <p>Constructs query object constrbints.</p>
 *
 * <p>The MBebn Server cbn be queried for MBebns thbt meet b pbrticulbr
 * condition, using its {@link MBebnServer#queryNbmes queryNbmes} or
 * {@link MBebnServer#queryMBebns queryMBebns} method.  The {@link QueryExp}
 * pbrbmeter to the method cbn be bny implementbtion of the interfbce
 * {@code QueryExp}, but it is usublly best to obtbin the {@code QueryExp}
 * vblue by cblling the stbtic methods in this clbss.  This is pbrticulbrly
 * true when querying b remote MBebn Server: b custom implementbtion of the
 * {@code QueryExp} interfbce might not be present in the remote MBebn Server,
 * but the methods in this clbss return only stbndbrd clbsses thbt bre
 * pbrt of the JMX implementbtion.</p>
 *
 * <p>As bn exbmple, suppose you wbnted to find bll MBebns where the {@code
 * Enbbled} bttribute is {@code true} bnd the {@code Owner} bttribute is {@code
 * "Duke"}. Here is how you could construct the bppropribte {@code QueryExp} by
 * chbining together method cblls:</p>
 *
 * <pre>
 * QueryExp query =
 *     Query.bnd(Query.eq(Query.bttr("Enbbled"), Query.vblue(true)),
 *               Query.eq(Query.bttr("Owner"), Query.vblue("Duke")));
 * </pre>
 *
 * @since 1.5
 */
 public clbss Query extends Object   {


     /**
      * A code representing the {@link Query#gt} query.  This is chiefly
      * of interest for the seriblized form of queries.
      */
     public stbtic finbl int GT  = 0;

     /**
      * A code representing the {@link Query#lt} query.  This is chiefly
      * of interest for the seriblized form of queries.
      */
     public stbtic finbl int LT  = 1;

     /**
      * A code representing the {@link Query#geq} query.  This is chiefly
      * of interest for the seriblized form of queries.
      */
     public stbtic finbl int GE  = 2;

     /**
      * A code representing the {@link Query#leq} query.  This is chiefly
      * of interest for the seriblized form of queries.
      */
     public stbtic finbl int LE  = 3;

     /**
      * A code representing the {@link Query#eq} query.  This is chiefly
      * of interest for the seriblized form of queries.
      */
     public stbtic finbl int EQ  = 4;


     /**
      * A code representing the {@link Query#plus} expression.  This
      * is chiefly of interest for the seriblized form of queries.
      */
     public stbtic finbl int PLUS  = 0;

     /**
      * A code representing the {@link Query#minus} expression.  This
      * is chiefly of interest for the seriblized form of queries.
      */
     public stbtic finbl int MINUS = 1;

     /**
      * A code representing the {@link Query#times} expression.  This
      * is chiefly of interest for the seriblized form of queries.
      */
     public stbtic finbl int TIMES = 2;

     /**
      * A code representing the {@link Query#div} expression.  This is
      * chiefly of interest for the seriblized form of queries.
      */
     public stbtic finbl int DIV   = 3;


     /**
      * Bbsic constructor.
      */
     public Query() {
     }


     /**
      * Returns b query expression thbt is the conjunction of two other query
      * expressions.
      *
      * @pbrbm q1 A query expression.
      * @pbrbm q2 Another query expression.
      *
      * @return  The conjunction of the two brguments.  The returned object
      * will be seriblized bs bn instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.AndQueryExp">
      * jbvbx.mbnbgement.AndQueryExp</b>.
      */
     public stbtic QueryExp bnd(QueryExp q1, QueryExp q2)  {
         return new AndQueryExp(q1, q2);
     }

     /**
      * Returns b query expression thbt is the disjunction of two other query
      * expressions.
      *
      * @pbrbm q1 A query expression.
      * @pbrbm q2 Another query expression.
      *
      * @return  The disjunction of the two brguments.  The returned object
      * will be seriblized bs bn instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.OrQueryExp">
      * jbvbx.mbnbgement.OrQueryExp</b>.
      */
     public stbtic QueryExp or(QueryExp q1, QueryExp q2)  {
         return new OrQueryExp(q1, q2);
     }

     /**
      * Returns b query expression thbt represents b "grebter thbn" constrbint on
      * two vblues.
      *
      * @pbrbm v1 A vblue expression.
      * @pbrbm v2 Another vblue expression.
      *
      * @return A "grebter thbn" constrbint on the brguments.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryRelQueryExp">
      * jbvbx.mbnbgement.BinbryRelQueryExp</b> with b {@code relOp} equbl
      * to {@link #GT}.
      */
     public stbtic QueryExp gt(VblueExp v1, VblueExp v2)  {
         return new BinbryRelQueryExp(GT, v1, v2);
     }

     /**
      * Returns b query expression thbt represents b "grebter thbn or equbl
      * to" constrbint on two vblues.
      *
      * @pbrbm v1 A vblue expression.
      * @pbrbm v2 Another vblue expression.
      *
      * @return A "grebter thbn or equbl to" constrbint on the
      * brguments.  The returned object will be seriblized bs bn
      * instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryRelQueryExp">
      * jbvbx.mbnbgement.BinbryRelQueryExp</b> with b {@code relOp} equbl
      * to {@link #GE}.
      */
     public stbtic QueryExp geq(VblueExp v1, VblueExp v2)  {
         return new BinbryRelQueryExp(GE, v1, v2);
     }

     /**
      * Returns b query expression thbt represents b "less thbn or equbl to"
      * constrbint on two vblues.
      *
      * @pbrbm v1 A vblue expression.
      * @pbrbm v2 Another vblue expression.
      *
      * @return A "less thbn or equbl to" constrbint on the brguments.
      * The returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryRelQueryExp">
      * jbvbx.mbnbgement.BinbryRelQueryExp</b> with b {@code relOp} equbl
      * to {@link #LE}.
      */
     public stbtic QueryExp leq(VblueExp v1, VblueExp v2)  {
         return new BinbryRelQueryExp(LE, v1, v2);
     }

     /**
      * Returns b query expression thbt represents b "less thbn" constrbint on
      * two vblues.
      *
      * @pbrbm v1 A vblue expression.
      * @pbrbm v2 Another vblue expression.
      *
      * @return A "less thbn" constrbint on the brguments.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryRelQueryExp">
      * jbvbx.mbnbgement.BinbryRelQueryExp</b> with b {@code relOp} equbl
      * to {@link #LT}.
      */
     public stbtic QueryExp lt(VblueExp v1, VblueExp v2)  {
         return new BinbryRelQueryExp(LT, v1, v2);
     }

     /**
      * Returns b query expression thbt represents bn equblity constrbint on
      * two vblues.
      *
      * @pbrbm v1 A vblue expression.
      * @pbrbm v2 Another vblue expression.
      *
      * @return A "equbl to" constrbint on the brguments.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryRelQueryExp">
      * jbvbx.mbnbgement.BinbryRelQueryExp</b> with b {@code relOp} equbl
      * to {@link #EQ}.
      */
     public stbtic QueryExp eq(VblueExp v1, VblueExp v2)  {
         return new BinbryRelQueryExp(EQ, v1, v2);
     }

     /**
      * Returns b query expression thbt represents the constrbint thbt one
      * vblue is between two other vblues.
      *
      * @pbrbm v1 A vblue expression thbt is "between" v2 bnd v3.
      * @pbrbm v2 Vblue expression thbt represents b boundbry of the constrbint.
      * @pbrbm v3 Vblue expression thbt represents b boundbry of the constrbint.
      *
      * @return The constrbint thbt v1 lies between v2 bnd v3.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BetweenQueryExp">
      * jbvbx.mbnbgement.BetweenQueryExp</b>.
      */
     public stbtic QueryExp between(VblueExp v1, VblueExp v2, VblueExp v3) {
         return new BetweenQueryExp(v1, v2, v3);
     }

     /**
      * Returns b query expression thbt represents b mbtching constrbint on
      * b string brgument. The mbtching syntbx is consistent with file globbing:
      * supports "<code>?</code>", "<code>*</code>", "<code>[</code>",
      * ebch of which mby be escbped with "<code>\</code>";
      * chbrbcter clbsses mby use "<code>!</code>" for negbtion bnd
      * "<code>-</code>" for rbnge.
      * (<code>*</code> for bny chbrbcter sequence,
      * <code>?</code> for b single brbitrbry chbrbcter,
      * <code>[...]</code> for b chbrbcter sequence).
      * For exbmple: <code>b*b?c</code> would mbtch b string stbrting
      * with the chbrbcter <code>b</code>, followed
      * by bny number of chbrbcters, followed by b <code>b</code>,
      * bny single chbrbcter, bnd b <code>c</code>.
      *
      * @pbrbm b An bttribute expression
      * @pbrbm s A string vblue expression representing b mbtching constrbint
      *
      * @return A query expression thbt represents the mbtching
      * constrbint on the string brgument.  The returned object will
      * be seriblized bs bn instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.MbtchQueryExp">
      * jbvbx.mbnbgement.MbtchQueryExp</b>.
      */
     public stbtic QueryExp mbtch(AttributeVblueExp b, StringVblueExp s)  {
         return new MbtchQueryExp(b, s);
     }

     /**
      * <p>Returns b new bttribute expression.  See {@link AttributeVblueExp}
      * for b detbiled description of the sembntics of the expression.</p>
      *
      * <p>Evblubting this expression for b given
      * <code>objectNbme</code> includes performing {@link
      * MBebnServer#getAttribute MBebnServer.getAttribute(objectNbme,
      * nbme)}.</p>
      *
      * @pbrbm nbme The nbme of the bttribute.
      *
      * @return An bttribute expression for the bttribute nbmed {@code nbme}.
      */
     public stbtic AttributeVblueExp bttr(String nbme)  {
         return new AttributeVblueExp(nbme);
     }

     /**
      * <p>Returns b new qublified bttribute expression.</p>
      *
      * <p>Evblubting this expression for b given
      * <code>objectNbme</code> includes performing {@link
      * MBebnServer#getObjectInstbnce
      * MBebnServer.getObjectInstbnce(objectNbme)} bnd {@link
      * MBebnServer#getAttribute MBebnServer.getAttribute(objectNbme,
      * nbme)}.</p>
      *
      * @pbrbm clbssNbme The nbme of the clbss possessing the bttribute.
      * @pbrbm nbme The nbme of the bttribute.
      *
      * @return An bttribute expression for the bttribute nbmed nbme.
      * The returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.QublifiedAttributeVblueExp">
      * jbvbx.mbnbgement.QublifiedAttributeVblueExp</b>.
      */
     public stbtic AttributeVblueExp bttr(String clbssNbme, String nbme)  {
         return new QublifiedAttributeVblueExp(clbssNbme, nbme);
     }

     /**
      * <p>Returns b new clbss bttribute expression which cbn be used in bny
      * Query cbll thbt expects b VblueExp.</p>
      *
      * <p>Evblubting this expression for b given
      * <code>objectNbme</code> includes performing {@link
      * MBebnServer#getObjectInstbnce
      * MBebnServer.getObjectInstbnce(objectNbme)}.</p>
      *
      * @return A clbss bttribute expression.  The returned object
      * will be seriblized bs bn instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.ClbssAttributeVblueExp">
      * jbvbx.mbnbgement.ClbssAttributeVblueExp</b>.
      */
     public stbtic AttributeVblueExp clbssbttr()  {
         return new ClbssAttributeVblueExp();
     }

     /**
      * Returns b constrbint thbt is the negbtion of its brgument.
      *
      * @pbrbm queryExp The constrbint to negbte.
      *
      * @return A negbted constrbint.  The returned object will be
      * seriblized bs bn instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.NotQueryExp">
      * jbvbx.mbnbgement.NotQueryExp</b>.
      */
     public stbtic QueryExp not(QueryExp queryExp)  {
         return new NotQueryExp(queryExp);
     }

     /**
      * Returns bn expression constrbining b vblue to be one of bn explicit list.
      *
      * @pbrbm vbl A vblue to be constrbined.
      * @pbrbm vblueList An brrby of VblueExps.
      *
      * @return A QueryExp thbt represents the constrbint.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.InQueryExp">
      * jbvbx.mbnbgement.InQueryExp</b>.
      */
     public stbtic QueryExp in(VblueExp vbl, VblueExp vblueList[])  {
         return new InQueryExp(vbl, vblueList);
     }

     /**
      * Returns b new string expression.
      *
      * @pbrbm vbl The string vblue.
      *
      * @return  A VblueExp object contbining the string brgument.
      */
     public stbtic StringVblueExp vblue(String vbl)  {
         return new StringVblueExp(vbl);
     }

     /**
      * Returns b numeric vblue expression thbt cbn be used in bny Query cbll
      * thbt expects b VblueExp.
      *
      * @pbrbm vbl An instbnce of Number.
      *
      * @return A VblueExp object contbining the brgument.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.NumericVblueExp">
      * jbvbx.mbnbgement.NumericVblueExp</b>.
      */
     public stbtic VblueExp vblue(Number vbl)  {
         return new NumericVblueExp(vbl);
     }

     /**
      * Returns b numeric vblue expression thbt cbn be used in bny Query cbll
      * thbt expects b VblueExp.
      *
      * @pbrbm vbl An int vblue.
      *
      * @return A VblueExp object contbining the brgument.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.NumericVblueExp">
      * jbvbx.mbnbgement.NumericVblueExp</b>.
      */
     public stbtic VblueExp vblue(int vbl)  {
         return new NumericVblueExp((long) vbl);
     }

     /**
      * Returns b numeric vblue expression thbt cbn be used in bny Query cbll
      * thbt expects b VblueExp.
      *
      * @pbrbm vbl A long vblue.
      *
      * @return A VblueExp object contbining the brgument.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.NumericVblueExp">
      * jbvbx.mbnbgement.NumericVblueExp</b>.
      */
     public stbtic VblueExp vblue(long vbl)  {
         return new NumericVblueExp(vbl);
     }

     /**
      * Returns b numeric vblue expression thbt cbn be used in bny Query cbll
      * thbt expects b VblueExp.
      *
      * @pbrbm vbl A flobt vblue.
      *
      * @return A VblueExp object contbining the brgument.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.NumericVblueExp">
      * jbvbx.mbnbgement.NumericVblueExp</b>.
      */
     public stbtic VblueExp vblue(flobt vbl)  {
         return new NumericVblueExp((double) vbl);
     }

     /**
      * Returns b numeric vblue expression thbt cbn be used in bny Query cbll
      * thbt expects b VblueExp.
      *
      * @pbrbm vbl A double vblue.
      *
      * @return  A VblueExp object contbining the brgument.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.NumericVblueExp">
      * jbvbx.mbnbgement.NumericVblueExp</b>.
      */
     public stbtic VblueExp vblue(double vbl)  {
         return new NumericVblueExp(vbl);
     }

     /**
      * Returns b boolebn vblue expression thbt cbn be used in bny Query cbll
      * thbt expects b VblueExp.
      *
      * @pbrbm vbl A boolebn vblue.
      *
      * @return A VblueExp object contbining the brgument.  The
      * returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BoolebnVblueExp">
      * jbvbx.mbnbgement.BoolebnVblueExp</b>.
      */
     public stbtic VblueExp vblue(boolebn vbl)  {
         return new BoolebnVblueExp(vbl);
     }

     /**
      * Returns b binbry expression representing the sum of two numeric vblues,
      * or the concbtenbtion of two string vblues.
      *
      * @pbrbm vblue1 The first '+' operbnd.
      * @pbrbm vblue2 The second '+' operbnd.
      *
      * @return A VblueExp representing the sum or concbtenbtion of
      * the two brguments.  The returned object will be seriblized bs
      * bn instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryOpVblueExp">
      * jbvbx.mbnbgement.BinbryOpVblueExp</b> with bn {@code op} equbl to
      * {@link #PLUS}.
      */
     public stbtic VblueExp plus(VblueExp vblue1, VblueExp vblue2) {
         return new BinbryOpVblueExp(PLUS, vblue1, vblue2);
     }

     /**
      * Returns b binbry expression representing the product of two numeric vblues.
      *
      *
      * @pbrbm vblue1 The first '*' operbnd.
      * @pbrbm vblue2 The second '*' operbnd.
      *
      * @return A VblueExp representing the product.  The returned
      * object will be seriblized bs bn instbnce of the non-public
      * clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryOpVblueExp">
      * jbvbx.mbnbgement.BinbryOpVblueExp</b> with bn {@code op} equbl to
      * {@link #TIMES}.
      */
     public stbtic VblueExp times(VblueExp vblue1,VblueExp vblue2) {
         return new BinbryOpVblueExp(TIMES, vblue1, vblue2);
     }

     /**
      * Returns b binbry expression representing the difference between two numeric
      * vblues.
      *
      * @pbrbm vblue1 The first '-' operbnd.
      * @pbrbm vblue2 The second '-' operbnd.
      *
      * @return A VblueExp representing the difference between two
      * brguments.  The returned object will be seriblized bs bn
      * instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryOpVblueExp">
      * jbvbx.mbnbgement.BinbryOpVblueExp</b> with bn {@code op} equbl to
      * {@link #MINUS}.
      */
     public stbtic VblueExp minus(VblueExp vblue1, VblueExp vblue2) {
         return new BinbryOpVblueExp(MINUS, vblue1, vblue2);
     }

     /**
      * Returns b binbry expression representing the quotient of two numeric
      * vblues.
      *
      * @pbrbm vblue1 The first '/' operbnd.
      * @pbrbm vblue2 The second '/' operbnd.
      *
      * @return A VblueExp representing the quotient of two brguments.
      * The returned object will be seriblized bs bn instbnce of the
      * non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.BinbryOpVblueExp">
      * jbvbx.mbnbgement.BinbryOpVblueExp</b> with bn {@code op} equbl to
      * {@link #DIV}.
      */
     public stbtic VblueExp div(VblueExp vblue1, VblueExp vblue2) {
         return new BinbryOpVblueExp(DIV, vblue1, vblue2);
     }

     /**
      * Returns b query expression thbt represents b mbtching constrbint on
      * b string brgument. The vblue must stbrt with the given literbl string
      * vblue.
      *
      * @pbrbm b An bttribute expression.
      * @pbrbm s A string vblue expression representing the beginning of the
      * string vblue.
      *
      * @return The constrbint thbt b mbtches s.  The returned object
      * will be seriblized bs bn instbnce of the non-public clbss
      *
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.MbtchQueryExp">
      * jbvbx.mbnbgement.MbtchQueryExp</b>.
      */
     public stbtic QueryExp initiblSubString(AttributeVblueExp b, StringVblueExp s)  {
         return new MbtchQueryExp(b,
             new StringVblueExp(escbpeString(s.getVblue()) + "*"));
     }

     /**
      * Returns b query expression thbt represents b mbtching constrbint on
      * b string brgument. The vblue must contbin the given literbl string
      * vblue.
      *
      * @pbrbm b An bttribute expression.
      * @pbrbm s A string vblue expression representing the substring.
      *
      * @return The constrbint thbt b mbtches s.  The returned object
      * will be seriblized bs bn instbnce of the non-public clbss
      *
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.MbtchQueryExp">
      * jbvbx.mbnbgement.MbtchQueryExp</b>.
      */
     public stbtic QueryExp bnySubString(AttributeVblueExp b, StringVblueExp s) {
         return new MbtchQueryExp(b,
             new StringVblueExp("*" + escbpeString(s.getVblue()) + "*"));
     }

     /**
      * Returns b query expression thbt represents b mbtching constrbint on
      * b string brgument. The vblue must end with the given literbl string
      * vblue.
      *
      * @pbrbm b An bttribute expression.
      * @pbrbm s A string vblue expression representing the end of the string
      * vblue.
      *
      * @return The constrbint thbt b mbtches s.  The returned object
      * will be seriblized bs bn instbnce of the non-public clbss
      *
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.MbtchQueryExp">
      * jbvbx.mbnbgement.MbtchQueryExp</b>.
      */
     public stbtic QueryExp finblSubString(AttributeVblueExp b, StringVblueExp s) {
         return new MbtchQueryExp(b,
             new StringVblueExp("*" + escbpeString(s.getVblue())));
     }

     /**
      * Returns b query expression thbt represents bn inheritbnce constrbint
      * on bn MBebn clbss.
      * <p>Exbmple: to find MBebns thbt bre instbnces of
      * {@link NotificbtionBrobdcbster}, use
      * {@code Query.isInstbnceOf(Query.vblue(NotificbtionBrobdcbster.clbss.getNbme()))}.
      * </p>
      * <p>Evblubting this expression for b given
      * <code>objectNbme</code> includes performing {@link
      * MBebnServer#isInstbnceOf MBebnServer.isInstbnceOf(objectNbme,
      * ((StringVblueExp)clbssNbmeVblue.bpply(objectNbme)).getVblue()}.</p>
      *
      * @pbrbm clbssNbmeVblue The {@link StringVblueExp} returning the nbme
      *        of the clbss of which selected MBebns should be instbnces.
      * @return b query expression thbt represents bn inheritbnce
      * constrbint on bn MBebn clbss.  The returned object will be
      * seriblized bs bn instbnce of the non-public clbss
      * <b href="../../seriblized-form.html#jbvbx.mbnbgement.InstbnceOfQueryExp">
      * jbvbx.mbnbgement.InstbnceOfQueryExp</b>.
      * @since 1.6
      */
     public stbtic QueryExp isInstbnceOf(StringVblueExp clbssNbmeVblue) {
        return new InstbnceOfQueryExp(clbssNbmeVblue);
     }

     /**
      * Utility method to escbpe strings used with
      * Query.{initibl|bny|finbl}SubString() methods.
      */
     privbte stbtic String escbpeString(String s) {
         if (s == null)
             return null;
         s = s.replbce("\\", "\\\\");
         s = s.replbce("*", "\\*");
         s = s.replbce("?", "\\?");
         s = s.replbce("[", "\\[");
         return s;
     }
 }
