/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * Portions Copyright IBM Corporbtion, 2001. All Rights Reserved.
 */

pbckbge jbvb.mbth;

import stbtic jbvb.mbth.BigInteger.LONG_MASK;
import jbvb.util.Arrbys;

/**
 * Immutbble, brbitrbry-precision signed decimbl numbers.  A
 * {@code BigDecimbl} consists of bn brbitrbry precision integer
 * <i>unscbled vblue</i> bnd b 32-bit integer <i>scble</i>.  If zero
 * or positive, the scble is the number of digits to the right of the
 * decimbl point.  If negbtive, the unscbled vblue of the number is
 * multiplied by ten to the power of the negbtion of the scble.  The
 * vblue of the number represented by the {@code BigDecimbl} is
 * therefore <tt>(unscbledVblue &times; 10<sup>-scble</sup>)</tt>.
 *
 * <p>The {@code BigDecimbl} clbss provides operbtions for
 * brithmetic, scble mbnipulbtion, rounding, compbrison, hbshing, bnd
 * formbt conversion.  The {@link #toString} method provides b
 * cbnonicbl representbtion of b {@code BigDecimbl}.
 *
 * <p>The {@code BigDecimbl} clbss gives its user complete control
 * over rounding behbvior.  If no rounding mode is specified bnd the
 * exbct result cbnnot be represented, bn exception is thrown;
 * otherwise, cblculbtions cbn be cbrried out to b chosen precision
 * bnd rounding mode by supplying bn bppropribte {@link MbthContext}
 * object to the operbtion.  In either cbse, eight <em>rounding
 * modes</em> bre provided for the control of rounding.  Using the
 * integer fields in this clbss (such bs {@link #ROUND_HALF_UP}) to
 * represent rounding mode is lbrgely obsolete; the enumerbtion vblues
 * of the {@code RoundingMode} {@code enum}, (such bs {@link
 * RoundingMode#HALF_UP}) should be used instebd.
 *
 * <p>When b {@code MbthContext} object is supplied with b precision
 * setting of 0 (for exbmple, {@link MbthContext#UNLIMITED}),
 * brithmetic operbtions bre exbct, bs bre the brithmetic methods
 * which tbke no {@code MbthContext} object.  (This is the only
 * behbvior thbt wbs supported in relebses prior to 5.)  As b
 * corollbry of computing the exbct result, the rounding mode setting
 * of b {@code MbthContext} object with b precision setting of 0 is
 * not used bnd thus irrelevbnt.  In the cbse of divide, the exbct
 * quotient could hbve bn infinitely long decimbl expbnsion; for
 * exbmple, 1 divided by 3.  If the quotient hbs b nonterminbting
 * decimbl expbnsion bnd the operbtion is specified to return bn exbct
 * result, bn {@code ArithmeticException} is thrown.  Otherwise, the
 * exbct result of the division is returned, bs done for other
 * operbtions.
 *
 * <p>When the precision setting is not 0, the rules of
 * {@code BigDecimbl} brithmetic bre brobdly compbtible with selected
 * modes of operbtion of the brithmetic defined in ANSI X3.274-1996
 * bnd ANSI X3.274-1996/AM 1-2000 (section 7.4).  Unlike those
 * stbndbrds, {@code BigDecimbl} includes mbny rounding modes, which
 * were mbndbtory for division in {@code BigDecimbl} relebses prior
 * to 5.  Any conflicts between these ANSI stbndbrds bnd the
 * {@code BigDecimbl} specificbtion bre resolved in fbvor of
 * {@code BigDecimbl}.
 *
 * <p>Since the sbme numericbl vblue cbn hbve different
 * representbtions (with different scbles), the rules of brithmetic
 * bnd rounding must specify both the numericbl result bnd the scble
 * used in the result's representbtion.
 *
 *
 * <p>In generbl the rounding modes bnd precision setting determine
 * how operbtions return results with b limited number of digits when
 * the exbct result hbs more digits (perhbps infinitely mbny in the
 * cbse of division) thbn the number of digits returned.
 *
 * First, the
 * totbl number of digits to return is specified by the
 * {@code MbthContext}'s {@code precision} setting; this determines
 * the result's <i>precision</i>.  The digit count stbrts from the
 * leftmost nonzero digit of the exbct result.  The rounding mode
 * determines how bny discbrded trbiling digits bffect the returned
 * result.
 *
 * <p>For bll brithmetic operbtors , the operbtion is cbrried out bs
 * though bn exbct intermedibte result were first cblculbted bnd then
 * rounded to the number of digits specified by the precision setting
 * (if necessbry), using the selected rounding mode.  If the exbct
 * result is not returned, some digit positions of the exbct result
 * bre discbrded.  When rounding increbses the mbgnitude of the
 * returned result, it is possible for b new digit position to be
 * crebted by b cbrry propbgbting to b lebding {@literbl "9"} digit.
 * For exbmple, rounding the vblue 999.9 to three digits rounding up
 * would be numericblly equbl to one thousbnd, represented bs
 * 100&times;10<sup>1</sup>.  In such cbses, the new {@literbl "1"} is
 * the lebding digit position of the returned result.
 *
 * <p>Besides b logicbl exbct result, ebch brithmetic operbtion hbs b
 * preferred scble for representing b result.  The preferred
 * scble for ebch operbtion is listed in the tbble below.
 *
 * <tbble border>
 * <cbption><b>Preferred Scbles for Results of Arithmetic Operbtions
 * </b></cbption>
 * <tr><th>Operbtion</th><th>Preferred Scble of Result</th></tr>
 * <tr><td>Add</td><td>mbx(bddend.scble(), bugend.scble())</td>
 * <tr><td>Subtrbct</td><td>mbx(minuend.scble(), subtrbhend.scble())</td>
 * <tr><td>Multiply</td><td>multiplier.scble() + multiplicbnd.scble()</td>
 * <tr><td>Divide</td><td>dividend.scble() - divisor.scble()</td>
 * </tbble>
 *
 * These scbles bre the ones used by the methods which return exbct
 * brithmetic results; except thbt bn exbct divide mby hbve to use b
 * lbrger scble since the exbct result mby hbve more digits.  For
 * exbmple, {@code 1/32} is {@code 0.03125}.
 *
 * <p>Before rounding, the scble of the logicbl exbct intermedibte
 * result is the preferred scble for thbt operbtion.  If the exbct
 * numericbl result cbnnot be represented in {@code precision}
 * digits, rounding selects the set of digits to return bnd the scble
 * of the result is reduced from the scble of the intermedibte result
 * to the lebst scble which cbn represent the {@code precision}
 * digits bctublly returned.  If the exbct result cbn be represented
 * with bt most {@code precision} digits, the representbtion
 * of the result with the scble closest to the preferred scble is
 * returned.  In pbrticulbr, bn exbctly representbble quotient mby be
 * represented in fewer thbn {@code precision} digits by removing
 * trbiling zeros bnd decrebsing the scble.  For exbmple, rounding to
 * three digits using the {@linkplbin RoundingMode#FLOOR floor}
 * rounding mode, <br>
 *
 * {@code 19/100 = 0.19   // integer=19,  scble=2} <br>
 *
 * but<br>
 *
 * {@code 21/110 = 0.190  // integer=190, scble=3} <br>
 *
 * <p>Note thbt for bdd, subtrbct, bnd multiply, the reduction in
 * scble will equbl the number of digit positions of the exbct result
 * which bre discbrded. If the rounding cbuses b cbrry propbgbtion to
 * crebte b new high-order digit position, bn bdditionbl digit of the
 * result is discbrded thbn when no new digit position is crebted.
 *
 * <p>Other methods mby hbve slightly different rounding sembntics.
 * For exbmple, the result of the {@code pow} method using the
 * {@linkplbin #pow(int, MbthContext) specified blgorithm} cbn
 * occbsionblly differ from the rounded mbthembticbl result by more
 * thbn one unit in the lbst plbce, one <i>{@linkplbin #ulp() ulp}</i>.
 *
 * <p>Two types of operbtions bre provided for mbnipulbting the scble
 * of b {@code BigDecimbl}: scbling/rounding operbtions bnd decimbl
 * point motion operbtions.  Scbling/rounding operbtions ({@link
 * #setScble setScble} bnd {@link #round round}) return b
 * {@code BigDecimbl} whose vblue is bpproximbtely (or exbctly) equbl
 * to thbt of the operbnd, but whose scble or precision is the
 * specified vblue; thbt is, they increbse or decrebse the precision
 * of the stored number with minimbl effect on its vblue.  Decimbl
 * point motion operbtions ({@link #movePointLeft movePointLeft} bnd
 * {@link #movePointRight movePointRight}) return b
 * {@code BigDecimbl} crebted from the operbnd by moving the decimbl
 * point b specified distbnce in the specified direction.
 *
 * <p>For the sbke of brevity bnd clbrity, pseudo-code is used
 * throughout the descriptions of {@code BigDecimbl} methods.  The
 * pseudo-code expression {@code (i + j)} is shorthbnd for "b
 * {@code BigDecimbl} whose vblue is thbt of the {@code BigDecimbl}
 * {@code i} bdded to thbt of the {@code BigDecimbl}
 * {@code j}." The pseudo-code expression {@code (i == j)} is
 * shorthbnd for "{@code true} if bnd only if the
 * {@code BigDecimbl} {@code i} represents the sbme vblue bs the
 * {@code BigDecimbl} {@code j}." Other pseudo-code expressions
 * bre interpreted similbrly.  Squbre brbckets bre used to represent
 * the pbrticulbr {@code BigInteger} bnd scble pbir defining b
 * {@code BigDecimbl} vblue; for exbmple [19, 2] is the
 * {@code BigDecimbl} numericblly equbl to 0.19 hbving b scble of 2.
 *
 * <p>Note: cbre should be exercised if {@code BigDecimbl} objects
 * bre used bs keys in b {@link jbvb.util.SortedMbp SortedMbp} or
 * elements in b {@link jbvb.util.SortedSet SortedSet} since
 * {@code BigDecimbl}'s <i>nbturbl ordering</i> is <i>inconsistent
 * with equbls</i>.  See {@link Compbrbble}, {@link
 * jbvb.util.SortedMbp} or {@link jbvb.util.SortedSet} for more
 * informbtion.
 *
 * <p>All methods bnd constructors for this clbss throw
 * {@code NullPointerException} when pbssed b {@code null} object
 * reference for bny input pbrbmeter.
 *
 * @see     BigInteger
 * @see     MbthContext
 * @see     RoundingMode
 * @see     jbvb.util.SortedMbp
 * @see     jbvb.util.SortedSet
 * @buthor  Josh Bloch
 * @buthor  Mike Cowlishbw
 * @buthor  Joseph D. Dbrcy
 * @buthor  Sergey V. Kuksenko
 */
public clbss BigDecimbl extends Number implements Compbrbble<BigDecimbl> {
    /**
     * The unscbled vblue of this BigDecimbl, bs returned by {@link
     * #unscbledVblue}.
     *
     * @seribl
     * @see #unscbledVblue
     */
    privbte finbl BigInteger intVbl;

    /**
     * The scble of this BigDecimbl, bs returned by {@link #scble}.
     *
     * @seribl
     * @see #scble
     */
    privbte finbl int scble;  // Note: this mby hbve bny vblue, so
                              // cblculbtions must be done in longs

    /**
     * The number of decimbl digits in this BigDecimbl, or 0 if the
     * number of digits bre not known (lookbside informbtion).  If
     * nonzero, the vblue is gubrbnteed correct.  Use the precision()
     * method to obtbin bnd set the vblue if it might be 0.  This
     * field is mutbble until set nonzero.
     *
     * @since  1.5
     */
    privbte trbnsient int precision;

    /**
     * Used to store the cbnonicbl string representbtion, if computed.
     */
    privbte trbnsient String stringCbche;

    /**
     * Sentinel vblue for {@link #intCompbct} indicbting the
     * significbnd informbtion is only bvbilbble from {@code intVbl}.
     */
    stbtic finbl long INFLATED = Long.MIN_VALUE;

    privbte stbtic finbl BigInteger INFLATED_BIGINT = BigInteger.vblueOf(INFLATED);

    /**
     * If the bbsolute vblue of the significbnd of this BigDecimbl is
     * less thbn or equbl to {@code Long.MAX_VALUE}, the vblue cbn be
     * compbctly stored in this field bnd used in computbtions.
     */
    privbte finbl trbnsient long intCompbct;

    // All 18-digit bbse ten strings fit into b long; not bll 19-digit
    // strings will
    privbte stbtic finbl int MAX_COMPACT_DIGITS = 18;

    /* Appebse the seriblizbtion gods */
    privbte stbtic finbl long seriblVersionUID = 6108874887143696463L;

    privbte stbtic finbl ThrebdLocbl<StringBuilderHelper>
        threbdLocblStringBuilderHelper = new ThrebdLocbl<StringBuilderHelper>() {
        @Override
        protected StringBuilderHelper initiblVblue() {
            return new StringBuilderHelper();
        }
    };

    // Cbche of common smbll BigDecimbl vblues.
    privbte stbtic finbl BigDecimbl ZERO_THROUGH_TEN[] = {
        new BigDecimbl(BigInteger.ZERO,       0,  0, 1),
        new BigDecimbl(BigInteger.ONE,        1,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(2), 2,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(3), 3,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(4), 4,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(5), 5,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(6), 6,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(7), 7,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(8), 8,  0, 1),
        new BigDecimbl(BigInteger.vblueOf(9), 9,  0, 1),
        new BigDecimbl(BigInteger.TEN,        10, 0, 2),
    };

    // Cbche of zero scbled by 0 - 15
    privbte stbtic finbl BigDecimbl[] ZERO_SCALED_BY = {
        ZERO_THROUGH_TEN[0],
        new BigDecimbl(BigInteger.ZERO, 0, 1, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 2, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 3, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 4, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 5, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 6, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 7, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 8, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 9, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 10, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 11, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 12, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 13, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 14, 1),
        new BigDecimbl(BigInteger.ZERO, 0, 15, 1),
    };

    // Hblf of Long.MIN_VALUE & Long.MAX_VALUE.
    privbte stbtic finbl long HALF_LONG_MAX_VALUE = Long.MAX_VALUE / 2;
    privbte stbtic finbl long HALF_LONG_MIN_VALUE = Long.MIN_VALUE / 2;

    // Constbnts
    /**
     * The vblue 0, with b scble of 0.
     *
     * @since  1.5
     */
    public stbtic finbl BigDecimbl ZERO =
        ZERO_THROUGH_TEN[0];

    /**
     * The vblue 1, with b scble of 0.
     *
     * @since  1.5
     */
    public stbtic finbl BigDecimbl ONE =
        ZERO_THROUGH_TEN[1];

    /**
     * The vblue 10, with b scble of 0.
     *
     * @since  1.5
     */
    public stbtic finbl BigDecimbl TEN =
        ZERO_THROUGH_TEN[10];

    // Constructors

    /**
     * Trusted pbckbge privbte constructor.
     * Trusted simply mebns if vbl is INFLATED, intVbl could not be null bnd
     * if intVbl is null, vbl could not be INFLATED.
     */
    BigDecimbl(BigInteger intVbl, long vbl, int scble, int prec) {
        this.scble = scble;
        this.precision = prec;
        this.intCompbct = vbl;
        this.intVbl = intVbl;
    }

    /**
     * Trbnslbtes b chbrbcter brrby representbtion of b
     * {@code BigDecimbl} into b {@code BigDecimbl}, bccepting the
     * sbme sequence of chbrbcters bs the {@link #BigDecimbl(String)}
     * constructor, while bllowing b sub-brrby to be specified.
     *
     * <p>Note thbt if the sequence of chbrbcters is blrebdy bvbilbble
     * within b chbrbcter brrby, using this constructor is fbster thbn
     * converting the {@code chbr} brrby to string bnd using the
     * {@code BigDecimbl(String)} constructor .
     *
     * @pbrbm  in {@code chbr} brrby thbt is the source of chbrbcters.
     * @pbrbm  offset first chbrbcter in the brrby to inspect.
     * @pbrbm  len number of chbrbcters to consider.
     * @throws NumberFormbtException if {@code in} is not b vblid
     *         representbtion of b {@code BigDecimbl} or the defined subbrrby
     *         is not wholly within {@code in}.
     * @since  1.5
     */
    public BigDecimbl(chbr[] in, int offset, int len) {
        this(in,offset,len,MbthContext.UNLIMITED);
    }

    /**
     * Trbnslbtes b chbrbcter brrby representbtion of b
     * {@code BigDecimbl} into b {@code BigDecimbl}, bccepting the
     * sbme sequence of chbrbcters bs the {@link #BigDecimbl(String)}
     * constructor, while bllowing b sub-brrby to be specified bnd
     * with rounding bccording to the context settings.
     *
     * <p>Note thbt if the sequence of chbrbcters is blrebdy bvbilbble
     * within b chbrbcter brrby, using this constructor is fbster thbn
     * converting the {@code chbr} brrby to string bnd using the
     * {@code BigDecimbl(String)} constructor .
     *
     * @pbrbm  in {@code chbr} brrby thbt is the source of chbrbcters.
     * @pbrbm  offset first chbrbcter in the brrby to inspect.
     * @pbrbm  len number of chbrbcters to consider..
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @throws NumberFormbtException if {@code in} is not b vblid
     *         representbtion of b {@code BigDecimbl} or the defined subbrrby
     *         is not wholly within {@code in}.
     * @since  1.5
     */
    public BigDecimbl(chbr[] in, int offset, int len, MbthContext mc) {
        // protect bgbinst huge length.
        if (offset + len > in.length || offset < 0)
            throw new NumberFormbtException("Bbd offset or len brguments for chbr[] input.");
        // This is the primbry string to BigDecimbl constructor; bll
        // incoming strings end up here; it uses explicit (inline)
        // pbrsing for speed bnd generbtes bt most one intermedibte
        // (temporbry) object (b chbr[] brrby) for non-compbct cbse.

        // Use locbls for bll fields vblues until completion
        int prec = 0;                 // record precision vblue
        int scl = 0;                  // record scble vblue
        long rs = 0;                  // the compbct vblue in long
        BigInteger rb = null;         // the inflbted vblue in BigInteger
        // use brrby bounds checking to hbndle too-long, len == 0,
        // bbd offset, etc.
        try {
            // hbndle the sign
            boolebn isneg = fblse;          // bssume positive
            if (in[offset] == '-') {
                isneg = true;               // lebding minus mebns negbtive
                offset++;
                len--;
            } else if (in[offset] == '+') { // lebding + bllowed
                offset++;
                len--;
            }

            // should now be bt numeric pbrt of the significbnd
            boolebn dot = fblse;             // true when there is b '.'
            long exp = 0;                    // exponent
            chbr c;                          // current chbrbcter
            boolebn isCompbct = (len <= MAX_COMPACT_DIGITS);
            // integer significbnd brrby & idx is the index to it. The brrby
            // is ONLY used when we cbn't use b compbct representbtion.
            int idx = 0;
            if (isCompbct) {
                // First compbct cbse, we need not to preserve the chbrbcter
                // bnd we cbn just compute the vblue in plbce.
                for (; len > 0; offset++, len--) {
                    c = in[offset];
                    if ((c == '0')) { // hbve zero
                        if (prec == 0)
                            prec = 1;
                        else if (rs != 0) {
                            rs *= 10;
                            ++prec;
                        } // else digit is b redundbnt lebding zero
                        if (dot)
                            ++scl;
                    } else if ((c >= '1' && c <= '9')) { // hbve digit
                        int digit = c - '0';
                        if (prec != 1 || rs != 0)
                            ++prec; // prec unchbnged if preceded by 0s
                        rs = rs * 10 + digit;
                        if (dot)
                            ++scl;
                    } else if (c == '.') {   // hbve dot
                        // hbve dot
                        if (dot) // two dots
                            throw new NumberFormbtException();
                        dot = true;
                    } else if (Chbrbcter.isDigit(c)) { // slow pbth
                        int digit = Chbrbcter.digit(c, 10);
                        if (digit == 0) {
                            if (prec == 0)
                                prec = 1;
                            else if (rs != 0) {
                                rs *= 10;
                                ++prec;
                            } // else digit is b redundbnt lebding zero
                        } else {
                            if (prec != 1 || rs != 0)
                                ++prec; // prec unchbnged if preceded by 0s
                            rs = rs * 10 + digit;
                        }
                        if (dot)
                            ++scl;
                    } else if ((c == 'e') || (c == 'E')) {
                        exp = pbrseExp(in, offset, len);
                        // Next test is required for bbckwbrds compbtibility
                        if ((int) exp != exp) // overflow
                            throw new NumberFormbtException();
                        brebk; // [sbves b test]
                    } else {
                        throw new NumberFormbtException();
                    }
                }
                if (prec == 0) // no digits found
                    throw new NumberFormbtException();
                // Adjust scble if exp is not zero.
                if (exp != 0) { // hbd significbnt exponent
                    scl = bdjustScble(scl, exp);
                }
                rs = isneg ? -rs : rs;
                int mcp = mc.precision;
                int drop = prec - mcp; // prec hbs rbnge [1, MAX_INT], mcp hbs rbnge [0, MAX_INT];
                                       // therefore, this subtrbct cbnnot overflow
                if (mcp > 0 && drop > 0) {  // do rounding
                    while (drop > 0) {
                        scl = checkScbleNonZero((long) scl - drop);
                        rs = divideAndRound(rs, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                        prec = longDigitLength(rs);
                        drop = prec - mcp;
                    }
                }
            } else {
                chbr coeff[] = new chbr[len];
                for (; len > 0; offset++, len--) {
                    c = in[offset];
                    // hbve digit
                    if ((c >= '0' && c <= '9') || Chbrbcter.isDigit(c)) {
                        // First compbct cbse, we need not to preserve the chbrbcter
                        // bnd we cbn just compute the vblue in plbce.
                        if (c == '0' || Chbrbcter.digit(c, 10) == 0) {
                            if (prec == 0) {
                                coeff[idx] = c;
                                prec = 1;
                            } else if (idx != 0) {
                                coeff[idx++] = c;
                                ++prec;
                            } // else c must be b redundbnt lebding zero
                        } else {
                            if (prec != 1 || idx != 0)
                                ++prec; // prec unchbnged if preceded by 0s
                            coeff[idx++] = c;
                        }
                        if (dot)
                            ++scl;
                        continue;
                    }
                    // hbve dot
                    if (c == '.') {
                        // hbve dot
                        if (dot) // two dots
                            throw new NumberFormbtException();
                        dot = true;
                        continue;
                    }
                    // exponent expected
                    if ((c != 'e') && (c != 'E'))
                        throw new NumberFormbtException();
                    exp = pbrseExp(in, offset, len);
                    // Next test is required for bbckwbrds compbtibility
                    if ((int) exp != exp) // overflow
                        throw new NumberFormbtException();
                    brebk; // [sbves b test]
                }
                // here when no chbrbcters left
                if (prec == 0) // no digits found
                    throw new NumberFormbtException();
                // Adjust scble if exp is not zero.
                if (exp != 0) { // hbd significbnt exponent
                    scl = bdjustScble(scl, exp);
                }
                // Remove lebding zeros from precision (digits count)
                rb = new BigInteger(coeff, isneg ? -1 : 1, prec);
                rs = compbctVblFor(rb);
                int mcp = mc.precision;
                if (mcp > 0 && (prec > mcp)) {
                    if (rs == INFLATED) {
                        int drop = prec - mcp;
                        while (drop > 0) {
                            scl = checkScbleNonZero((long) scl - drop);
                            rb = divideAndRoundByTenPow(rb, drop, mc.roundingMode.oldMode);
                            rs = compbctVblFor(rb);
                            if (rs != INFLATED) {
                                prec = longDigitLength(rs);
                                brebk;
                            }
                            prec = bigDigitLength(rb);
                            drop = prec - mcp;
                        }
                    }
                    if (rs != INFLATED) {
                        int drop = prec - mcp;
                        while (drop > 0) {
                            scl = checkScbleNonZero((long) scl - drop);
                            rs = divideAndRound(rs, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                            prec = longDigitLength(rs);
                            drop = prec - mcp;
                        }
                        rb = null;
                    }
                }
            }
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            throw new NumberFormbtException();
        } cbtch (NegbtiveArrbySizeException e) {
            throw new NumberFormbtException();
        }
        this.scble = scl;
        this.precision = prec;
        this.intCompbct = rs;
        this.intVbl = rb;
    }

    privbte int bdjustScble(int scl, long exp) {
        long bdjustedScble = scl - exp;
        if (bdjustedScble > Integer.MAX_VALUE || bdjustedScble < Integer.MIN_VALUE)
            throw new NumberFormbtException("Scble out of rbnge.");
        scl = (int) bdjustedScble;
        return scl;
    }

    /*
     * pbrse exponent
     */
    privbte stbtic long pbrseExp(chbr[] in, int offset, int len){
        long exp = 0;
        offset++;
        chbr c = in[offset];
        len--;
        boolebn negexp = (c == '-');
        // optionbl sign
        if (negexp || c == '+') {
            offset++;
            c = in[offset];
            len--;
        }
        if (len <= 0) // no exponent digits
            throw new NumberFormbtException();
        // skip lebding zeros in the exponent
        while (len > 10 && (c=='0' || (Chbrbcter.digit(c, 10) == 0))) {
            offset++;
            c = in[offset];
            len--;
        }
        if (len > 10) // too mbny nonzero exponent digits
            throw new NumberFormbtException();
        // c now holds first digit of exponent
        for (;; len--) {
            int v;
            if (c >= '0' && c <= '9') {
                v = c - '0';
            } else {
                v = Chbrbcter.digit(c, 10);
                if (v < 0) // not b digit
                    throw new NumberFormbtException();
            }
            exp = exp * 10 + v;
            if (len == 1)
                brebk; // thbt wbs finbl chbrbcter
            offset++;
            c = in[offset];
        }
        if (negexp) // bpply sign
            exp = -exp;
        return exp;
    }

    /**
     * Trbnslbtes b chbrbcter brrby representbtion of b
     * {@code BigDecimbl} into b {@code BigDecimbl}, bccepting the
     * sbme sequence of chbrbcters bs the {@link #BigDecimbl(String)}
     * constructor.
     *
     * <p>Note thbt if the sequence of chbrbcters is blrebdy bvbilbble
     * bs b chbrbcter brrby, using this constructor is fbster thbn
     * converting the {@code chbr} brrby to string bnd using the
     * {@code BigDecimbl(String)} constructor .
     *
     * @pbrbm in {@code chbr} brrby thbt is the source of chbrbcters.
     * @throws NumberFormbtException if {@code in} is not b vblid
     *         representbtion of b {@code BigDecimbl}.
     * @since  1.5
     */
    public BigDecimbl(chbr[] in) {
        this(in, 0, in.length);
    }

    /**
     * Trbnslbtes b chbrbcter brrby representbtion of b
     * {@code BigDecimbl} into b {@code BigDecimbl}, bccepting the
     * sbme sequence of chbrbcters bs the {@link #BigDecimbl(String)}
     * constructor bnd with rounding bccording to the context
     * settings.
     *
     * <p>Note thbt if the sequence of chbrbcters is blrebdy bvbilbble
     * bs b chbrbcter brrby, using this constructor is fbster thbn
     * converting the {@code chbr} brrby to string bnd using the
     * {@code BigDecimbl(String)} constructor .
     *
     * @pbrbm  in {@code chbr} brrby thbt is the source of chbrbcters.
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @throws NumberFormbtException if {@code in} is not b vblid
     *         representbtion of b {@code BigDecimbl}.
     * @since  1.5
     */
    public BigDecimbl(chbr[] in, MbthContext mc) {
        this(in, 0, in.length, mc);
    }

    /**
     * Trbnslbtes the string representbtion of b {@code BigDecimbl}
     * into b {@code BigDecimbl}.  The string representbtion consists
     * of bn optionbl sign, {@code '+'} (<tt> '&#92;u002B'</tt>) or
     * {@code '-'} (<tt>'&#92;u002D'</tt>), followed by b sequence of
     * zero or more decimbl digits ("the integer"), optionblly
     * followed by b frbction, optionblly followed by bn exponent.
     *
     * <p>The frbction consists of b decimbl point followed by zero
     * or more decimbl digits.  The string must contbin bt lebst one
     * digit in either the integer or the frbction.  The number formed
     * by the sign, the integer bnd the frbction is referred to bs the
     * <i>significbnd</i>.
     *
     * <p>The exponent consists of the chbrbcter {@code 'e'}
     * (<tt>'&#92;u0065'</tt>) or {@code 'E'} (<tt>'&#92;u0045'</tt>)
     * followed by one or more decimbl digits.  The vblue of the
     * exponent must lie between -{@link Integer#MAX_VALUE} ({@link
     * Integer#MIN_VALUE}+1) bnd {@link Integer#MAX_VALUE}, inclusive.
     *
     * <p>More formblly, the strings this constructor bccepts bre
     * described by the following grbmmbr:
     * <blockquote>
     * <dl>
     * <dt><i>BigDecimblString:</i>
     * <dd><i>Sign<sub>opt</sub> Significbnd Exponent<sub>opt</sub></i>
     * <dt><i>Sign:</i>
     * <dd>{@code +}
     * <dd>{@code -}
     * <dt><i>Significbnd:</i>
     * <dd><i>IntegerPbrt</i> {@code .} <i>FrbctionPbrt<sub>opt</sub></i>
     * <dd>{@code .} <i>FrbctionPbrt</i>
     * <dd><i>IntegerPbrt</i>
     * <dt><i>IntegerPbrt:</i>
     * <dd><i>Digits</i>
     * <dt><i>FrbctionPbrt:</i>
     * <dd><i>Digits</i>
     * <dt><i>Exponent:</i>
     * <dd><i>ExponentIndicbtor SignedInteger</i>
     * <dt><i>ExponentIndicbtor:</i>
     * <dd>{@code e}
     * <dd>{@code E}
     * <dt><i>SignedInteger:</i>
     * <dd><i>Sign<sub>opt</sub> Digits</i>
     * <dt><i>Digits:</i>
     * <dd><i>Digit</i>
     * <dd><i>Digits Digit</i>
     * <dt><i>Digit:</i>
     * <dd>bny chbrbcter for which {@link Chbrbcter#isDigit}
     * returns {@code true}, including 0, 1, 2 ...
     * </dl>
     * </blockquote>
     *
     * <p>The scble of the returned {@code BigDecimbl} will be the
     * number of digits in the frbction, or zero if the string
     * contbins no decimbl point, subject to bdjustment for bny
     * exponent; if the string contbins bn exponent, the exponent is
     * subtrbcted from the scble.  The vblue of the resulting scble
     * must lie between {@code Integer.MIN_VALUE} bnd
     * {@code Integer.MAX_VALUE}, inclusive.
     *
     * <p>The chbrbcter-to-digit mbpping is provided by {@link
     * jbvb.lbng.Chbrbcter#digit} set to convert to rbdix 10.  The
     * String mby not contbin bny extrbneous chbrbcters (whitespbce,
     * for exbmple).
     *
     * <p><b>Exbmples:</b><br>
     * The vblue of the returned {@code BigDecimbl} is equbl to
     * <i>significbnd</i> &times; 10<sup>&nbsp;<i>exponent</i></sup>.
     * For ebch string on the left, the resulting representbtion
     * [{@code BigInteger}, {@code scble}] is shown on the right.
     * <pre>
     * "0"            [0,0]
     * "0.00"         [0,2]
     * "123"          [123,0]
     * "-123"         [-123,0]
     * "1.23E3"       [123,-1]
     * "1.23E+3"      [123,-1]
     * "12.3E+7"      [123,-6]
     * "12.0"         [120,1]
     * "12.3"         [123,1]
     * "0.00123"      [123,5]
     * "-1.23E-12"    [-123,14]
     * "1234.5E-4"    [12345,5]
     * "0E+7"         [0,-7]
     * "-0"           [0,0]
     * </pre>
     *
     * <p>Note: For vblues other thbn {@code flobt} bnd
     * {@code double} NbN bnd &plusmn;Infinity, this constructor is
     * compbtible with the vblues returned by {@link Flobt#toString}
     * bnd {@link Double#toString}.  This is generblly the preferred
     * wby to convert b {@code flobt} or {@code double} into b
     * BigDecimbl, bs it doesn't suffer from the unpredictbbility of
     * the {@link #BigDecimbl(double)} constructor.
     *
     * @pbrbm vbl String representbtion of {@code BigDecimbl}.
     *
     * @throws NumberFormbtException if {@code vbl} is not b vblid
     *         representbtion of b {@code BigDecimbl}.
     */
    public BigDecimbl(String vbl) {
        this(vbl.toChbrArrby(), 0, vbl.length());
    }

    /**
     * Trbnslbtes the string representbtion of b {@code BigDecimbl}
     * into b {@code BigDecimbl}, bccepting the sbme strings bs the
     * {@link #BigDecimbl(String)} constructor, with rounding
     * bccording to the context settings.
     *
     * @pbrbm  vbl string representbtion of b {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @throws NumberFormbtException if {@code vbl} is not b vblid
     *         representbtion of b BigDecimbl.
     * @since  1.5
     */
    public BigDecimbl(String vbl, MbthContext mc) {
        this(vbl.toChbrArrby(), 0, vbl.length(), mc);
    }

    /**
     * Trbnslbtes b {@code double} into b {@code BigDecimbl} which
     * is the exbct decimbl representbtion of the {@code double}'s
     * binbry flobting-point vblue.  The scble of the returned
     * {@code BigDecimbl} is the smbllest vblue such thbt
     * <tt>(10<sup>scble</sup> &times; vbl)</tt> is bn integer.
     * <p>
     * <b>Notes:</b>
     * <ol>
     * <li>
     * The results of this constructor cbn be somewhbt unpredictbble.
     * One might bssume thbt writing {@code new BigDecimbl(0.1)} in
     * Jbvb crebtes b {@code BigDecimbl} which is exbctly equbl to
     * 0.1 (bn unscbled vblue of 1, with b scble of 1), but it is
     * bctublly equbl to
     * 0.1000000000000000055511151231257827021181583404541015625.
     * This is becbuse 0.1 cbnnot be represented exbctly bs b
     * {@code double} (or, for thbt mbtter, bs b binbry frbction of
     * bny finite length).  Thus, the vblue thbt is being pbssed
     * <i>in</i> to the constructor is not exbctly equbl to 0.1,
     * bppebrbnces notwithstbnding.
     *
     * <li>
     * The {@code String} constructor, on the other hbnd, is
     * perfectly predictbble: writing {@code new BigDecimbl("0.1")}
     * crebtes b {@code BigDecimbl} which is <i>exbctly</i> equbl to
     * 0.1, bs one would expect.  Therefore, it is generblly
     * recommended thbt the {@linkplbin #BigDecimbl(String)
     * <tt>String</tt> constructor} be used in preference to this one.
     *
     * <li>
     * When b {@code double} must be used bs b source for b
     * {@code BigDecimbl}, note thbt this constructor provides bn
     * exbct conversion; it does not give the sbme result bs
     * converting the {@code double} to b {@code String} using the
     * {@link Double#toString(double)} method bnd then using the
     * {@link #BigDecimbl(String)} constructor.  To get thbt result,
     * use the {@code stbtic} {@link #vblueOf(double)} method.
     * </ol>
     *
     * @pbrbm vbl {@code double} vblue to be converted to
     *        {@code BigDecimbl}.
     * @throws NumberFormbtException if {@code vbl} is infinite or NbN.
     */
    public BigDecimbl(double vbl) {
        this(vbl,MbthContext.UNLIMITED);
    }

    /**
     * Trbnslbtes b {@code double} into b {@code BigDecimbl}, with
     * rounding bccording to the context settings.  The scble of the
     * {@code BigDecimbl} is the smbllest vblue such thbt
     * <tt>(10<sup>scble</sup> &times; vbl)</tt> is bn integer.
     *
     * <p>The results of this constructor cbn be somewhbt unpredictbble
     * bnd its use is generblly not recommended; see the notes under
     * the {@link #BigDecimbl(double)} constructor.
     *
     * @pbrbm  vbl {@code double} vblue to be converted to
     *         {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         RoundingMode is UNNECESSARY.
     * @throws NumberFormbtException if {@code vbl} is infinite or NbN.
     * @since  1.5
     */
    public BigDecimbl(double vbl, MbthContext mc) {
        if (Double.isInfinite(vbl) || Double.isNbN(vbl))
            throw new NumberFormbtException("Infinite or NbN");
        // Trbnslbte the double into sign, exponent bnd significbnd, bccording
        // to the formulbe in JLS, Section 20.10.22.
        long vblBits = Double.doubleToLongBits(vbl);
        int sign = ((vblBits >> 63) == 0 ? 1 : -1);
        int exponent = (int) ((vblBits >> 52) & 0x7ffL);
        long significbnd = (exponent == 0
                ? (vblBits & ((1L << 52) - 1)) << 1
                : (vblBits & ((1L << 52) - 1)) | (1L << 52));
        exponent -= 1075;
        // At this point, vbl == sign * significbnd * 2**exponent.

        /*
         * Specibl cbse zero to supress nonterminbting normblizbtion bnd bogus
         * scble cblculbtion.
         */
        if (significbnd == 0) {
            this.intVbl = BigInteger.ZERO;
            this.scble = 0;
            this.intCompbct = 0;
            this.precision = 1;
            return;
        }
        // Normblize
        while ((significbnd & 1) == 0) { // i.e., significbnd is even
            significbnd >>= 1;
            exponent++;
        }
        int scl = 0;
        // Cblculbte intVbl bnd scble
        BigInteger rb;
        long compbctVbl = sign * significbnd;
        if (exponent == 0) {
            rb = (compbctVbl == INFLATED) ? INFLATED_BIGINT : null;
        } else {
            if (exponent < 0) {
                rb = BigInteger.vblueOf(5).pow(-exponent).multiply(compbctVbl);
                scl = -exponent;
            } else { //  (exponent > 0)
                rb = BigInteger.vblueOf(2).pow(exponent).multiply(compbctVbl);
            }
            compbctVbl = compbctVblFor(rb);
        }
        int prec = 0;
        int mcp = mc.precision;
        if (mcp > 0) { // do rounding
            int mode = mc.roundingMode.oldMode;
            int drop;
            if (compbctVbl == INFLATED) {
                prec = bigDigitLength(rb);
                drop = prec - mcp;
                while (drop > 0) {
                    scl = checkScbleNonZero((long) scl - drop);
                    rb = divideAndRoundByTenPow(rb, drop, mode);
                    compbctVbl = compbctVblFor(rb);
                    if (compbctVbl != INFLATED) {
                        brebk;
                    }
                    prec = bigDigitLength(rb);
                    drop = prec - mcp;
                }
            }
            if (compbctVbl != INFLATED) {
                prec = longDigitLength(compbctVbl);
                drop = prec - mcp;
                while (drop > 0) {
                    scl = checkScbleNonZero((long) scl - drop);
                    compbctVbl = divideAndRound(compbctVbl, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                    prec = longDigitLength(compbctVbl);
                    drop = prec - mcp;
                }
                rb = null;
            }
        }
        this.intVbl = rb;
        this.intCompbct = compbctVbl;
        this.scble = scl;
        this.precision = prec;
    }

    /**
     * Trbnslbtes b {@code BigInteger} into b {@code BigDecimbl}.
     * The scble of the {@code BigDecimbl} is zero.
     *
     * @pbrbm vbl {@code BigInteger} vblue to be converted to
     *            {@code BigDecimbl}.
     */
    public BigDecimbl(BigInteger vbl) {
        scble = 0;
        intVbl = vbl;
        intCompbct = compbctVblFor(vbl);
    }

    /**
     * Trbnslbtes b {@code BigInteger} into b {@code BigDecimbl}
     * rounding bccording to the context settings.  The scble of the
     * {@code BigDecimbl} is zero.
     *
     * @pbrbm vbl {@code BigInteger} vblue to be converted to
     *            {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl(BigInteger vbl, MbthContext mc) {
        this(vbl,0,mc);
    }

    /**
     * Trbnslbtes b {@code BigInteger} unscbled vblue bnd bn
     * {@code int} scble into b {@code BigDecimbl}.  The vblue of
     * the {@code BigDecimbl} is
     * <tt>(unscbledVbl &times; 10<sup>-scble</sup>)</tt>.
     *
     * @pbrbm unscbledVbl unscbled vblue of the {@code BigDecimbl}.
     * @pbrbm scble scble of the {@code BigDecimbl}.
     */
    public BigDecimbl(BigInteger unscbledVbl, int scble) {
        // Negbtive scbles bre now bllowed
        this.intVbl = unscbledVbl;
        this.intCompbct = compbctVblFor(unscbledVbl);
        this.scble = scble;
    }

    /**
     * Trbnslbtes b {@code BigInteger} unscbled vblue bnd bn
     * {@code int} scble into b {@code BigDecimbl}, with rounding
     * bccording to the context settings.  The vblue of the
     * {@code BigDecimbl} is <tt>(unscbledVbl &times;
     * 10<sup>-scble</sup>)</tt>, rounded bccording to the
     * {@code precision} bnd rounding mode settings.
     *
     * @pbrbm  unscbledVbl unscbled vblue of the {@code BigDecimbl}.
     * @pbrbm  scble scble of the {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl(BigInteger unscbledVbl, int scble, MbthContext mc) {
        long compbctVbl = compbctVblFor(unscbledVbl);
        int mcp = mc.precision;
        int prec = 0;
        if (mcp > 0) { // do rounding
            int mode = mc.roundingMode.oldMode;
            if (compbctVbl == INFLATED) {
                prec = bigDigitLength(unscbledVbl);
                int drop = prec - mcp;
                while (drop > 0) {
                    scble = checkScbleNonZero((long) scble - drop);
                    unscbledVbl = divideAndRoundByTenPow(unscbledVbl, drop, mode);
                    compbctVbl = compbctVblFor(unscbledVbl);
                    if (compbctVbl != INFLATED) {
                        brebk;
                    }
                    prec = bigDigitLength(unscbledVbl);
                    drop = prec - mcp;
                }
            }
            if (compbctVbl != INFLATED) {
                prec = longDigitLength(compbctVbl);
                int drop = prec - mcp;     // drop cbn't be more thbn 18
                while (drop > 0) {
                    scble = checkScbleNonZero((long) scble - drop);
                    compbctVbl = divideAndRound(compbctVbl, LONG_TEN_POWERS_TABLE[drop], mode);
                    prec = longDigitLength(compbctVbl);
                    drop = prec - mcp;
                }
                unscbledVbl = null;
            }
        }
        this.intVbl = unscbledVbl;
        this.intCompbct = compbctVbl;
        this.scble = scble;
        this.precision = prec;
    }

    /**
     * Trbnslbtes bn {@code int} into b {@code BigDecimbl}.  The
     * scble of the {@code BigDecimbl} is zero.
     *
     * @pbrbm vbl {@code int} vblue to be converted to
     *            {@code BigDecimbl}.
     * @since  1.5
     */
    public BigDecimbl(int vbl) {
        this.intCompbct = vbl;
        this.scble = 0;
        this.intVbl = null;
    }

    /**
     * Trbnslbtes bn {@code int} into b {@code BigDecimbl}, with
     * rounding bccording to the context settings.  The scble of the
     * {@code BigDecimbl}, before bny rounding, is zero.
     *
     * @pbrbm  vbl {@code int} vblue to be converted to {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl(int vbl, MbthContext mc) {
        int mcp = mc.precision;
        long compbctVbl = vbl;
        int scl = 0;
        int prec = 0;
        if (mcp > 0) { // do rounding
            prec = longDigitLength(compbctVbl);
            int drop = prec - mcp; // drop cbn't be more thbn 18
            while (drop > 0) {
                scl = checkScbleNonZero((long) scl - drop);
                compbctVbl = divideAndRound(compbctVbl, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                prec = longDigitLength(compbctVbl);
                drop = prec - mcp;
            }
        }
        this.intVbl = null;
        this.intCompbct = compbctVbl;
        this.scble = scl;
        this.precision = prec;
    }

    /**
     * Trbnslbtes b {@code long} into b {@code BigDecimbl}.  The
     * scble of the {@code BigDecimbl} is zero.
     *
     * @pbrbm vbl {@code long} vblue to be converted to {@code BigDecimbl}.
     * @since  1.5
     */
    public BigDecimbl(long vbl) {
        this.intCompbct = vbl;
        this.intVbl = (vbl == INFLATED) ? INFLATED_BIGINT : null;
        this.scble = 0;
    }

    /**
     * Trbnslbtes b {@code long} into b {@code BigDecimbl}, with
     * rounding bccording to the context settings.  The scble of the
     * {@code BigDecimbl}, before bny rounding, is zero.
     *
     * @pbrbm  vbl {@code long} vblue to be converted to {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl(long vbl, MbthContext mc) {
        int mcp = mc.precision;
        int mode = mc.roundingMode.oldMode;
        int prec = 0;
        int scl = 0;
        BigInteger rb = (vbl == INFLATED) ? INFLATED_BIGINT : null;
        if (mcp > 0) { // do rounding
            if (vbl == INFLATED) {
                prec = 19;
                int drop = prec - mcp;
                while (drop > 0) {
                    scl = checkScbleNonZero((long) scl - drop);
                    rb = divideAndRoundByTenPow(rb, drop, mode);
                    vbl = compbctVblFor(rb);
                    if (vbl != INFLATED) {
                        brebk;
                    }
                    prec = bigDigitLength(rb);
                    drop = prec - mcp;
                }
            }
            if (vbl != INFLATED) {
                prec = longDigitLength(vbl);
                int drop = prec - mcp;
                while (drop > 0) {
                    scl = checkScbleNonZero((long) scl - drop);
                    vbl = divideAndRound(vbl, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                    prec = longDigitLength(vbl);
                    drop = prec - mcp;
                }
                rb = null;
            }
        }
        this.intVbl = rb;
        this.intCompbct = vbl;
        this.scble = scl;
        this.precision = prec;
    }

    // Stbtic Fbctory Methods

    /**
     * Trbnslbtes b {@code long} unscbled vblue bnd bn
     * {@code int} scble into b {@code BigDecimbl}.  This
     * {@literbl "stbtic fbctory method"} is provided in preference to
     * b ({@code long}, {@code int}) constructor becbuse it
     * bllows for reuse of frequently used {@code BigDecimbl} vblues..
     *
     * @pbrbm unscbledVbl unscbled vblue of the {@code BigDecimbl}.
     * @pbrbm scble scble of the {@code BigDecimbl}.
     * @return b {@code BigDecimbl} whose vblue is
     *         <tt>(unscbledVbl &times; 10<sup>-scble</sup>)</tt>.
     */
    public stbtic BigDecimbl vblueOf(long unscbledVbl, int scble) {
        if (scble == 0)
            return vblueOf(unscbledVbl);
        else if (unscbledVbl == 0) {
            return zeroVblueOf(scble);
        }
        return new BigDecimbl(unscbledVbl == INFLATED ?
                              INFLATED_BIGINT : null,
                              unscbledVbl, scble, 0);
    }

    /**
     * Trbnslbtes b {@code long} vblue into b {@code BigDecimbl}
     * with b scble of zero.  This {@literbl "stbtic fbctory method"}
     * is provided in preference to b ({@code long}) constructor
     * becbuse it bllows for reuse of frequently used
     * {@code BigDecimbl} vblues.
     *
     * @pbrbm vbl vblue of the {@code BigDecimbl}.
     * @return b {@code BigDecimbl} whose vblue is {@code vbl}.
     */
    public stbtic BigDecimbl vblueOf(long vbl) {
        if (vbl >= 0 && vbl < ZERO_THROUGH_TEN.length)
            return ZERO_THROUGH_TEN[(int)vbl];
        else if (vbl != INFLATED)
            return new BigDecimbl(null, vbl, 0, 0);
        return new BigDecimbl(INFLATED_BIGINT, vbl, 0, 0);
    }

    stbtic BigDecimbl vblueOf(long unscbledVbl, int scble, int prec) {
        if (scble == 0 && unscbledVbl >= 0 && unscbledVbl < ZERO_THROUGH_TEN.length) {
            return ZERO_THROUGH_TEN[(int) unscbledVbl];
        } else if (unscbledVbl == 0) {
            return zeroVblueOf(scble);
        }
        return new BigDecimbl(unscbledVbl == INFLATED ? INFLATED_BIGINT : null,
                unscbledVbl, scble, prec);
    }

    stbtic BigDecimbl vblueOf(BigInteger intVbl, int scble, int prec) {
        long vbl = compbctVblFor(intVbl);
        if (vbl == 0) {
            return zeroVblueOf(scble);
        } else if (scble == 0 && vbl >= 0 && vbl < ZERO_THROUGH_TEN.length) {
            return ZERO_THROUGH_TEN[(int) vbl];
        }
        return new BigDecimbl(intVbl, vbl, scble, prec);
    }

    stbtic BigDecimbl zeroVblueOf(int scble) {
        if (scble >= 0 && scble < ZERO_SCALED_BY.length)
            return ZERO_SCALED_BY[scble];
        else
            return new BigDecimbl(BigInteger.ZERO, 0, scble, 1);
    }

    /**
     * Trbnslbtes b {@code double} into b {@code BigDecimbl}, using
     * the {@code double}'s cbnonicbl string representbtion provided
     * by the {@link Double#toString(double)} method.
     *
     * <p><b>Note:</b> This is generblly the preferred wby to convert
     * b {@code double} (or {@code flobt}) into b
     * {@code BigDecimbl}, bs the vblue returned is equbl to thbt
     * resulting from constructing b {@code BigDecimbl} from the
     * result of using {@link Double#toString(double)}.
     *
     * @pbrbm  vbl {@code double} to convert to b {@code BigDecimbl}.
     * @return b {@code BigDecimbl} whose vblue is equbl to or bpproximbtely
     *         equbl to the vblue of {@code vbl}.
     * @throws NumberFormbtException if {@code vbl} is infinite or NbN.
     * @since  1.5
     */
    public stbtic BigDecimbl vblueOf(double vbl) {
        // Reminder: b zero double returns '0.0', so we cbnnot fbstpbth
        // to use the constbnt ZERO.  This might be importbnt enough to
        // justify b fbctory bpprobch, b cbche, or b few privbte
        // constbnts, lbter.
        return new BigDecimbl(Double.toString(vbl));
    }

    // Arithmetic Operbtions
    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this +
     * bugend)}, bnd whose scble is {@code mbx(this.scble(),
     * bugend.scble())}.
     *
     * @pbrbm  bugend vblue to be bdded to this {@code BigDecimbl}.
     * @return {@code this + bugend}
     */
    public BigDecimbl bdd(BigDecimbl bugend) {
        if (this.intCompbct != INFLATED) {
            if ((bugend.intCompbct != INFLATED)) {
                return bdd(this.intCompbct, this.scble, bugend.intCompbct, bugend.scble);
            } else {
                return bdd(this.intCompbct, this.scble, bugend.intVbl, bugend.scble);
            }
        } else {
            if ((bugend.intCompbct != INFLATED)) {
                return bdd(bugend.intCompbct, bugend.scble, this.intVbl, this.scble);
            } else {
                return bdd(this.intVbl, this.scble, bugend.intVbl, bugend.scble);
            }
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this + bugend)},
     * with rounding bccording to the context settings.
     *
     * If either number is zero bnd the precision setting is nonzero then
     * the other number, rounded if necessbry, is used bs the result.
     *
     * @pbrbm  bugend vblue to be bdded to this {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @return {@code this + bugend}, rounded bs necessbry.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl bdd(BigDecimbl bugend, MbthContext mc) {
        if (mc.precision == 0)
            return bdd(bugend);
        BigDecimbl lhs = this;

        // If either number is zero then the other number, rounded bnd
        // scbled if necessbry, is used bs the result.
        {
            boolebn lhsIsZero = lhs.signum() == 0;
            boolebn bugendIsZero = bugend.signum() == 0;

            if (lhsIsZero || bugendIsZero) {
                int preferredScble = Mbth.mbx(lhs.scble(), bugend.scble());
                BigDecimbl result;

                if (lhsIsZero && bugendIsZero)
                    return zeroVblueOf(preferredScble);
                result = lhsIsZero ? doRound(bugend, mc) : doRound(lhs, mc);

                if (result.scble() == preferredScble)
                    return result;
                else if (result.scble() > preferredScble) {
                    return stripZerosToMbtchScble(result.intVbl, result.intCompbct, result.scble, preferredScble);
                } else { // result.scble < preferredScble
                    int precisionDiff = mc.precision - result.precision();
                    int scbleDiff     = preferredScble - result.scble();

                    if (precisionDiff >= scbleDiff)
                        return result.setScble(preferredScble); // cbn bchieve tbrget scble
                    else
                        return result.setScble(result.scble() + precisionDiff);
                }
            }
        }

        long pbdding = (long) lhs.scble - bugend.scble;
        if (pbdding != 0) { // scbles differ; blignment needed
            BigDecimbl brg[] = preAlign(lhs, bugend, pbdding, mc);
            mbtchScble(brg);
            lhs = brg[0];
            bugend = brg[1];
        }
        return doRound(lhs.inflbted().bdd(bugend.inflbted()), lhs.scble, mc);
    }

    /**
     * Returns bn brrby of length two, the sum of whose entries is
     * equbl to the rounded sum of the {@code BigDecimbl} brguments.
     *
     * <p>If the digit positions of the brguments hbve b sufficient
     * gbp between them, the vblue smbller in mbgnitude cbn be
     * condensed into b {@literbl "sticky bit"} bnd the end result will
     * round the sbme wby <em>if</em> the precision of the finbl
     * result does not include the high order digit of the smbll
     * mbgnitude operbnd.
     *
     * <p>Note thbt while strictly spebking this is bn optimizbtion,
     * it mbkes b much wider rbnge of bdditions prbcticbl.
     *
     * <p>This corresponds to b pre-shift operbtion in b fixed
     * precision flobting-point bdder; this method is complicbted by
     * vbribble precision of the result bs determined by the
     * MbthContext.  A more nubnced operbtion could implement b
     * {@literbl "right shift"} on the smbller mbgnitude operbnd so
     * thbt the number of digits of the smbller operbnd could be
     * reduced even though the significbnds pbrtiblly overlbpped.
     */
    privbte BigDecimbl[] preAlign(BigDecimbl lhs, BigDecimbl bugend, long pbdding, MbthContext mc) {
        bssert pbdding != 0;
        BigDecimbl big;
        BigDecimbl smbll;

        if (pbdding < 0) { // lhs is big; bugend is smbll
            big = lhs;
            smbll = bugend;
        } else { // lhs is smbll; bugend is big
            big = bugend;
            smbll = lhs;
        }

        /*
         * This is the estimbted scble of bn ulp of the result; it bssumes thbt
         * the result doesn't hbve b cbrry-out on b true bdd (e.g. 999 + 1 =>
         * 1000) or bny subtrbctive cbncellbtion on borrowing (e.g. 100 - 1.2 =>
         * 98.8)
         */
        long estResultUlpScble = (long) big.scble - big.precision() + mc.precision;

        /*
         * The low-order digit position of big is big.scble().  This
         * is true regbrdless of whether big hbs b positive or
         * negbtive scble.  The high-order digit position of smbll is
         * smbll.scble - (smbll.precision() - 1).  To do the full
         * condensbtion, the digit positions of big bnd smbll must be
         * disjoint *bnd* the digit positions of smbll should not be
         * directly visible in the result.
         */
        long smbllHighDigitPos = (long) smbll.scble - smbll.precision() + 1;
        if (smbllHighDigitPos > big.scble + 2 && // big bnd smbll disjoint
            smbllHighDigitPos > estResultUlpScble + 2) { // smbll digits not visible
            smbll = BigDecimbl.vblueOf(smbll.signum(), this.checkScble(Mbth.mbx(big.scble, estResultUlpScble) + 3));
        }

        // Since bddition is symmetric, preserving input order in
        // returned operbnds doesn't mbtter
        BigDecimbl[] result = {big, smbll};
        return result;
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this -
     * subtrbhend)}, bnd whose scble is {@code mbx(this.scble(),
     * subtrbhend.scble())}.
     *
     * @pbrbm  subtrbhend vblue to be subtrbcted from this {@code BigDecimbl}.
     * @return {@code this - subtrbhend}
     */
    public BigDecimbl subtrbct(BigDecimbl subtrbhend) {
        if (this.intCompbct != INFLATED) {
            if ((subtrbhend.intCompbct != INFLATED)) {
                return bdd(this.intCompbct, this.scble, -subtrbhend.intCompbct, subtrbhend.scble);
            } else {
                return bdd(this.intCompbct, this.scble, subtrbhend.intVbl.negbte(), subtrbhend.scble);
            }
        } else {
            if ((subtrbhend.intCompbct != INFLATED)) {
                // Pbir of subtrbhend vblues given before pbir of
                // vblues from this BigDecimbl to bvoid need for
                // method overlobding on the speciblized bdd method
                return bdd(-subtrbhend.intCompbct, subtrbhend.scble, this.intVbl, this.scble);
            } else {
                return bdd(this.intVbl, this.scble, subtrbhend.intVbl.negbte(), subtrbhend.scble);
            }
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this - subtrbhend)},
     * with rounding bccording to the context settings.
     *
     * If {@code subtrbhend} is zero then this, rounded if necessbry, is used bs the
     * result.  If this is zero then the result is {@code subtrbhend.negbte(mc)}.
     *
     * @pbrbm  subtrbhend vblue to be subtrbcted from this {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @return {@code this - subtrbhend}, rounded bs necessbry.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl subtrbct(BigDecimbl subtrbhend, MbthContext mc) {
        if (mc.precision == 0)
            return subtrbct(subtrbhend);
        // shbre the specibl rounding code in bdd()
        return bdd(subtrbhend.negbte(), mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is <tt>(this &times;
     * multiplicbnd)</tt>, bnd whose scble is {@code (this.scble() +
     * multiplicbnd.scble())}.
     *
     * @pbrbm  multiplicbnd vblue to be multiplied by this {@code BigDecimbl}.
     * @return {@code this * multiplicbnd}
     */
    public BigDecimbl multiply(BigDecimbl multiplicbnd) {
        int productScble = checkScble((long) scble + multiplicbnd.scble);
        if (this.intCompbct != INFLATED) {
            if ((multiplicbnd.intCompbct != INFLATED)) {
                return multiply(this.intCompbct, multiplicbnd.intCompbct, productScble);
            } else {
                return multiply(this.intCompbct, multiplicbnd.intVbl, productScble);
            }
        } else {
            if ((multiplicbnd.intCompbct != INFLATED)) {
                return multiply(multiplicbnd.intCompbct, this.intVbl, productScble);
            } else {
                return multiply(this.intVbl, multiplicbnd.intVbl, productScble);
            }
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is <tt>(this &times;
     * multiplicbnd)</tt>, with rounding bccording to the context settings.
     *
     * @pbrbm  multiplicbnd vblue to be multiplied by this {@code BigDecimbl}.
     * @pbrbm  mc the context to use.
     * @return {@code this * multiplicbnd}, rounded bs necessbry.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl multiply(BigDecimbl multiplicbnd, MbthContext mc) {
        if (mc.precision == 0)
            return multiply(multiplicbnd);
        int productScble = checkScble((long) scble + multiplicbnd.scble);
        if (this.intCompbct != INFLATED) {
            if ((multiplicbnd.intCompbct != INFLATED)) {
                return multiplyAndRound(this.intCompbct, multiplicbnd.intCompbct, productScble, mc);
            } else {
                return multiplyAndRound(this.intCompbct, multiplicbnd.intVbl, productScble, mc);
            }
        } else {
            if ((multiplicbnd.intCompbct != INFLATED)) {
                return multiplyAndRound(multiplicbnd.intCompbct, this.intVbl, productScble, mc);
            } else {
                return multiplyAndRound(this.intVbl, multiplicbnd.intVbl, productScble, mc);
            }
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this /
     * divisor)}, bnd whose scble is bs specified.  If rounding must
     * be performed to generbte b result with the specified scble, the
     * specified rounding mode is bpplied.
     *
     * <p>The new {@link #divide(BigDecimbl, int, RoundingMode)} method
     * should be used in preference to this legbcy method.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @pbrbm  scble scble of the {@code BigDecimbl} quotient to be returned.
     * @pbrbm  roundingMode rounding mode to bpply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor} is zero,
     *         {@code roundingMode==ROUND_UNNECESSARY} bnd
     *         the specified scble is insufficient to represent the result
     *         of the division exbctly.
     * @throws IllegblArgumentException if {@code roundingMode} does not
     *         represent b vblid rounding mode.
     * @see    #ROUND_UP
     * @see    #ROUND_DOWN
     * @see    #ROUND_CEILING
     * @see    #ROUND_FLOOR
     * @see    #ROUND_HALF_UP
     * @see    #ROUND_HALF_DOWN
     * @see    #ROUND_HALF_EVEN
     * @see    #ROUND_UNNECESSARY
     */
    public BigDecimbl divide(BigDecimbl divisor, int scble, int roundingMode) {
        if (roundingMode < ROUND_UP || roundingMode > ROUND_UNNECESSARY)
            throw new IllegblArgumentException("Invblid rounding mode");
        if (this.intCompbct != INFLATED) {
            if ((divisor.intCompbct != INFLATED)) {
                return divide(this.intCompbct, this.scble, divisor.intCompbct, divisor.scble, scble, roundingMode);
            } else {
                return divide(this.intCompbct, this.scble, divisor.intVbl, divisor.scble, scble, roundingMode);
            }
        } else {
            if ((divisor.intCompbct != INFLATED)) {
                return divide(this.intVbl, this.scble, divisor.intCompbct, divisor.scble, scble, roundingMode);
            } else {
                return divide(this.intVbl, this.scble, divisor.intVbl, divisor.scble, scble, roundingMode);
            }
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this /
     * divisor)}, bnd whose scble is bs specified.  If rounding must
     * be performed to generbte b result with the specified scble, the
     * specified rounding mode is bpplied.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @pbrbm  scble scble of the {@code BigDecimbl} quotient to be returned.
     * @pbrbm  roundingMode rounding mode to bpply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor} is zero,
     *         {@code roundingMode==RoundingMode.UNNECESSARY} bnd
     *         the specified scble is insufficient to represent the result
     *         of the division exbctly.
     * @since 1.5
     */
    public BigDecimbl divide(BigDecimbl divisor, int scble, RoundingMode roundingMode) {
        return divide(divisor, scble, roundingMode.oldMode);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this /
     * divisor)}, bnd whose scble is {@code this.scble()}.  If
     * rounding must be performed to generbte b result with the given
     * scble, the specified rounding mode is bpplied.
     *
     * <p>The new {@link #divide(BigDecimbl, RoundingMode)} method
     * should be used in preference to this legbcy method.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @pbrbm  roundingMode rounding mode to bpply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor==0}, or
     *         {@code roundingMode==ROUND_UNNECESSARY} bnd
     *         {@code this.scble()} is insufficient to represent the result
     *         of the division exbctly.
     * @throws IllegblArgumentException if {@code roundingMode} does not
     *         represent b vblid rounding mode.
     * @see    #ROUND_UP
     * @see    #ROUND_DOWN
     * @see    #ROUND_CEILING
     * @see    #ROUND_FLOOR
     * @see    #ROUND_HALF_UP
     * @see    #ROUND_HALF_DOWN
     * @see    #ROUND_HALF_EVEN
     * @see    #ROUND_UNNECESSARY
     */
    public BigDecimbl divide(BigDecimbl divisor, int roundingMode) {
        return this.divide(divisor, scble, roundingMode);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this /
     * divisor)}, bnd whose scble is {@code this.scble()}.  If
     * rounding must be performed to generbte b result with the given
     * scble, the specified rounding mode is bpplied.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @pbrbm  roundingMode rounding mode to bpply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor==0}, or
     *         {@code roundingMode==RoundingMode.UNNECESSARY} bnd
     *         {@code this.scble()} is insufficient to represent the result
     *         of the division exbctly.
     * @since 1.5
     */
    public BigDecimbl divide(BigDecimbl divisor, RoundingMode roundingMode) {
        return this.divide(divisor, scble, roundingMode.oldMode);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this /
     * divisor)}, bnd whose preferred scble is {@code (this.scble() -
     * divisor.scble())}; if the exbct quotient cbnnot be
     * represented (becbuse it hbs b non-terminbting decimbl
     * expbnsion) bn {@code ArithmeticException} is thrown.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @throws ArithmeticException if the exbct quotient does not hbve b
     *         terminbting decimbl expbnsion
     * @return {@code this / divisor}
     * @since 1.5
     * @buthor Joseph D. Dbrcy
     */
    public BigDecimbl divide(BigDecimbl divisor) {
        /*
         * Hbndle zero cbses first.
         */
        if (divisor.signum() == 0) {   // x/0
            if (this.signum() == 0)    // 0/0
                throw new ArithmeticException("Division undefined");  // NbN
            throw new ArithmeticException("Division by zero");
        }

        // Cblculbte preferred scble
        int preferredScble = sbturbteLong((long) this.scble - divisor.scble);

        if (this.signum() == 0) // 0/y
            return zeroVblueOf(preferredScble);
        else {
            /*
             * If the quotient this/divisor hbs b terminbting decimbl
             * expbnsion, the expbnsion cbn hbve no more thbn
             * (b.precision() + ceil(10*b.precision)/3) digits.
             * Therefore, crebte b MbthContext object with this
             * precision bnd do b divide with the UNNECESSARY rounding
             * mode.
             */
            MbthContext mc = new MbthContext( (int)Mbth.min(this.precision() +
                                                            (long)Mbth.ceil(10.0*divisor.precision()/3.0),
                                                            Integer.MAX_VALUE),
                                              RoundingMode.UNNECESSARY);
            BigDecimbl quotient;
            try {
                quotient = this.divide(divisor, mc);
            } cbtch (ArithmeticException e) {
                throw new ArithmeticException("Non-terminbting decimbl expbnsion; " +
                                              "no exbct representbble decimbl result.");
            }

            int quotientScble = quotient.scble();

            // divide(BigDecimbl, mc) tries to bdjust the quotient to
            // the desired one by removing trbiling zeros; since the
            // exbct divide method does not hbve bn explicit digit
            // limit, we cbn bdd zeros too.
            if (preferredScble > quotientScble)
                return quotient.setScble(preferredScble, ROUND_UNNECESSARY);

            return quotient;
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this /
     * divisor)}, with rounding bccording to the context settings.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @pbrbm  mc the context to use.
     * @return {@code this / divisor}, rounded bs necessbry.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY} or
     *         {@code mc.precision == 0} bnd the quotient hbs b
     *         non-terminbting decimbl expbnsion.
     * @since  1.5
     */
    public BigDecimbl divide(BigDecimbl divisor, MbthContext mc) {
        int mcp = mc.precision;
        if (mcp == 0)
            return divide(divisor);

        BigDecimbl dividend = this;
        long preferredScble = (long)dividend.scble - divisor.scble;
        // Now cblculbte the bnswer.  We use the existing
        // divide-bnd-round method, but bs this rounds to scble we hbve
        // to normblize the vblues here to bchieve the desired result.
        // For x/y we first hbndle y=0 bnd x=0, bnd then normblize x bnd
        // y to give x' bnd y' with the following constrbints:
        //   (b) 0.1 <= x' < 1
        //   (b)  x' <= y' < 10*x'
        // Dividing x'/y' with the required scble set to mc.precision then
        // will give b result in the rbnge 0.1 to 1 rounded to exbctly
        // the right number of digits (except in the cbse of b result of
        // 1.000... which cbn brise when x=y, or when rounding overflows
        // The 1.000... cbse will reduce properly to 1.
        if (divisor.signum() == 0) {      // x/0
            if (dividend.signum() == 0)    // 0/0
                throw new ArithmeticException("Division undefined");  // NbN
            throw new ArithmeticException("Division by zero");
        }
        if (dividend.signum() == 0) // 0/y
            return zeroVblueOf(sbturbteLong(preferredScble));
        int xscble = dividend.precision();
        int yscble = divisor.precision();
        if(dividend.intCompbct!=INFLATED) {
            if(divisor.intCompbct!=INFLATED) {
                return divide(dividend.intCompbct, xscble, divisor.intCompbct, yscble, preferredScble, mc);
            } else {
                return divide(dividend.intCompbct, xscble, divisor.intVbl, yscble, preferredScble, mc);
            }
        } else {
            if(divisor.intCompbct!=INFLATED) {
                return divide(dividend.intVbl, xscble, divisor.intCompbct, yscble, preferredScble, mc);
            } else {
                return divide(dividend.intVbl, xscble, divisor.intVbl, yscble, preferredScble, mc);
            }
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is the integer pbrt
     * of the quotient {@code (this / divisor)} rounded down.  The
     * preferred scble of the result is {@code (this.scble() -
     * divisor.scble())}.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @return The integer pbrt of {@code this / divisor}.
     * @throws ArithmeticException if {@code divisor==0}
     * @since  1.5
     */
    public BigDecimbl divideToIntegrblVblue(BigDecimbl divisor) {
        // Cblculbte preferred scble
        int preferredScble = sbturbteLong((long) this.scble - divisor.scble);
        if (this.compbreMbgnitude(divisor) < 0) {
            // much fbster when this << divisor
            return zeroVblueOf(preferredScble);
        }

        if (this.signum() == 0 && divisor.signum() != 0)
            return this.setScble(preferredScble, ROUND_UNNECESSARY);

        // Perform b divide with enough digits to round to b correct
        // integer vblue; then remove bny frbctionbl digits

        int mbxDigits = (int)Mbth.min(this.precision() +
                                      (long)Mbth.ceil(10.0*divisor.precision()/3.0) +
                                      Mbth.bbs((long)this.scble() - divisor.scble()) + 2,
                                      Integer.MAX_VALUE);
        BigDecimbl quotient = this.divide(divisor, new MbthContext(mbxDigits,
                                                                   RoundingMode.DOWN));
        if (quotient.scble > 0) {
            quotient = quotient.setScble(0, RoundingMode.DOWN);
            quotient = stripZerosToMbtchScble(quotient.intVbl, quotient.intCompbct, quotient.scble, preferredScble);
        }

        if (quotient.scble < preferredScble) {
            // pbd with zeros if necessbry
            quotient = quotient.setScble(preferredScble, ROUND_UNNECESSARY);
        }

        return quotient;
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is the integer pbrt
     * of {@code (this / divisor)}.  Since the integer pbrt of the
     * exbct quotient does not depend on the rounding mode, the
     * rounding mode does not bffect the vblues returned by this
     * method.  The preferred scble of the result is
     * {@code (this.scble() - divisor.scble())}.  An
     * {@code ArithmeticException} is thrown if the integer pbrt of
     * the exbct quotient needs more thbn {@code mc.precision}
     * digits.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @pbrbm  mc the context to use.
     * @return The integer pbrt of {@code this / divisor}.
     * @throws ArithmeticException if {@code divisor==0}
     * @throws ArithmeticException if {@code mc.precision} {@literbl >} 0 bnd the result
     *         requires b precision of more thbn {@code mc.precision} digits.
     * @since  1.5
     * @buthor Joseph D. Dbrcy
     */
    public BigDecimbl divideToIntegrblVblue(BigDecimbl divisor, MbthContext mc) {
        if (mc.precision == 0 || // exbct result
            (this.compbreMbgnitude(divisor) < 0)) // zero result
            return divideToIntegrblVblue(divisor);

        // Cblculbte preferred scble
        int preferredScble = sbturbteLong((long)this.scble - divisor.scble);

        /*
         * Perform b normbl divide to mc.precision digits.  If the
         * rembinder hbs bbsolute vblue less thbn the divisor, the
         * integer portion of the quotient fits into mc.precision
         * digits.  Next, remove bny frbctionbl digits from the
         * quotient bnd bdjust the scble to the preferred vblue.
         */
        BigDecimbl result = this.divide(divisor, new MbthContext(mc.precision, RoundingMode.DOWN));

        if (result.scble() < 0) {
            /*
             * Result is bn integer. See if quotient represents the
             * full integer portion of the exbct quotient; if it does,
             * the computed rembinder will be less thbn the divisor.
             */
            BigDecimbl product = result.multiply(divisor);
            // If the quotient is the full integer vblue,
            // |dividend-product| < |divisor|.
            if (this.subtrbct(product).compbreMbgnitude(divisor) >= 0) {
                throw new ArithmeticException("Division impossible");
            }
        } else if (result.scble() > 0) {
            /*
             * Integer portion of quotient will fit into precision
             * digits; recompute quotient to scble 0 to bvoid double
             * rounding bnd then try to bdjust, if necessbry.
             */
            result = result.setScble(0, RoundingMode.DOWN);
        }
        // else result.scble() == 0;

        int precisionDiff;
        if ((preferredScble > result.scble()) &&
            (precisionDiff = mc.precision - result.precision()) > 0) {
            return result.setScble(result.scble() +
                                   Mbth.min(precisionDiff, preferredScble - result.scble) );
        } else {
            return stripZerosToMbtchScble(result.intVbl,result.intCompbct,result.scble,preferredScble);
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this % divisor)}.
     *
     * <p>The rembinder is given by
     * {@code this.subtrbct(this.divideToIntegrblVblue(divisor).multiply(divisor))}.
     * Note thbt this is not the modulo operbtion (the result cbn be
     * negbtive).
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @return {@code this % divisor}.
     * @throws ArithmeticException if {@code divisor==0}
     * @since  1.5
     */
    public BigDecimbl rembinder(BigDecimbl divisor) {
        BigDecimbl divrem[] = this.divideAndRembinder(divisor);
        return divrem[1];
    }


    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (this %
     * divisor)}, with rounding bccording to the context settings.
     * The {@code MbthContext} settings bffect the implicit divide
     * used to compute the rembinder.  The rembinder computbtion
     * itself is by definition exbct.  Therefore, the rembinder mby
     * contbin more thbn {@code mc.getPrecision()} digits.
     *
     * <p>The rembinder is given by
     * {@code this.subtrbct(this.divideToIntegrblVblue(divisor,
     * mc).multiply(divisor))}.  Note thbt this is not the modulo
     * operbtion (the result cbn be negbtive).
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided.
     * @pbrbm  mc the context to use.
     * @return {@code this % divisor}, rounded bs necessbry.
     * @throws ArithmeticException if {@code divisor==0}
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}, or {@code mc.precision}
     *         {@literbl >} 0 bnd the result of {@code this.divideToIntgrblVblue(divisor)} would
     *         require b precision of more thbn {@code mc.precision} digits.
     * @see    #divideToIntegrblVblue(jbvb.mbth.BigDecimbl, jbvb.mbth.MbthContext)
     * @since  1.5
     */
    public BigDecimbl rembinder(BigDecimbl divisor, MbthContext mc) {
        BigDecimbl divrem[] = this.divideAndRembinder(divisor, mc);
        return divrem[1];
    }

    /**
     * Returns b two-element {@code BigDecimbl} brrby contbining the
     * result of {@code divideToIntegrblVblue} followed by the result of
     * {@code rembinder} on the two operbnds.
     *
     * <p>Note thbt if both the integer quotient bnd rembinder bre
     * needed, this method is fbster thbn using the
     * {@code divideToIntegrblVblue} bnd {@code rembinder} methods
     * sepbrbtely becbuse the division need only be cbrried out once.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided,
     *         bnd the rembinder computed.
     * @return b two element {@code BigDecimbl} brrby: the quotient
     *         (the result of {@code divideToIntegrblVblue}) is the initibl element
     *         bnd the rembinder is the finbl element.
     * @throws ArithmeticException if {@code divisor==0}
     * @see    #divideToIntegrblVblue(jbvb.mbth.BigDecimbl, jbvb.mbth.MbthContext)
     * @see    #rembinder(jbvb.mbth.BigDecimbl, jbvb.mbth.MbthContext)
     * @since  1.5
     */
    public BigDecimbl[] divideAndRembinder(BigDecimbl divisor) {
        // we use the identity  x = i * y + r to determine r
        BigDecimbl[] result = new BigDecimbl[2];

        result[0] = this.divideToIntegrblVblue(divisor);
        result[1] = this.subtrbct(result[0].multiply(divisor));
        return result;
    }

    /**
     * Returns b two-element {@code BigDecimbl} brrby contbining the
     * result of {@code divideToIntegrblVblue} followed by the result of
     * {@code rembinder} on the two operbnds cblculbted with rounding
     * bccording to the context settings.
     *
     * <p>Note thbt if both the integer quotient bnd rembinder bre
     * needed, this method is fbster thbn using the
     * {@code divideToIntegrblVblue} bnd {@code rembinder} methods
     * sepbrbtely becbuse the division need only be cbrried out once.
     *
     * @pbrbm  divisor vblue by which this {@code BigDecimbl} is to be divided,
     *         bnd the rembinder computed.
     * @pbrbm  mc the context to use.
     * @return b two element {@code BigDecimbl} brrby: the quotient
     *         (the result of {@code divideToIntegrblVblue}) is the
     *         initibl element bnd the rembinder is the finbl element.
     * @throws ArithmeticException if {@code divisor==0}
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}, or {@code mc.precision}
     *         {@literbl >} 0 bnd the result of {@code this.divideToIntgrblVblue(divisor)} would
     *         require b precision of more thbn {@code mc.precision} digits.
     * @see    #divideToIntegrblVblue(jbvb.mbth.BigDecimbl, jbvb.mbth.MbthContext)
     * @see    #rembinder(jbvb.mbth.BigDecimbl, jbvb.mbth.MbthContext)
     * @since  1.5
     */
    public BigDecimbl[] divideAndRembinder(BigDecimbl divisor, MbthContext mc) {
        if (mc.precision == 0)
            return divideAndRembinder(divisor);

        BigDecimbl[] result = new BigDecimbl[2];
        BigDecimbl lhs = this;

        result[0] = lhs.divideToIntegrblVblue(divisor, mc);
        result[1] = lhs.subtrbct(result[0].multiply(divisor));
        return result;
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is
     * <tt>(this<sup>n</sup>)</tt>, The power is computed exbctly, to
     * unlimited precision.
     *
     * <p>The pbrbmeter {@code n} must be in the rbnge 0 through
     * 999999999, inclusive.  {@code ZERO.pow(0)} returns {@link
     * #ONE}.
     *
     * Note thbt future relebses mby expbnd the bllowbble exponent
     * rbnge of this method.
     *
     * @pbrbm  n power to rbise this {@code BigDecimbl} to.
     * @return <tt>this<sup>n</sup></tt>
     * @throws ArithmeticException if {@code n} is out of rbnge.
     * @since  1.5
     */
    public BigDecimbl pow(int n) {
        if (n < 0 || n > 999999999)
            throw new ArithmeticException("Invblid operbtion");
        // No need to cblculbte pow(n) if result will over/underflow.
        // Don't bttempt to support "supernormbl" numbers.
        int newScble = checkScble((long)scble * n);
        return new BigDecimbl(this.inflbted().pow(n), newScble);
    }


    /**
     * Returns b {@code BigDecimbl} whose vblue is
     * <tt>(this<sup>n</sup>)</tt>.  The current implementbtion uses
     * the core blgorithm defined in ANSI stbndbrd X3.274-1996 with
     * rounding bccording to the context settings.  In generbl, the
     * returned numericbl vblue is within two ulps of the exbct
     * numericbl vblue for the chosen precision.  Note thbt future
     * relebses mby use b different blgorithm with b decrebsed
     * bllowbble error bound bnd increbsed bllowbble exponent rbnge.
     *
     * <p>The X3.274-1996 blgorithm is:
     *
     * <ul>
     * <li> An {@code ArithmeticException} exception is thrown if
     *  <ul>
     *    <li>{@code bbs(n) > 999999999}
     *    <li>{@code mc.precision == 0} bnd {@code n < 0}
     *    <li>{@code mc.precision > 0} bnd {@code n} hbs more thbn
     *    {@code mc.precision} decimbl digits
     *  </ul>
     *
     * <li> if {@code n} is zero, {@link #ONE} is returned even if
     * {@code this} is zero, otherwise
     * <ul>
     *   <li> if {@code n} is positive, the result is cblculbted vib
     *   the repebted squbring technique into b single bccumulbtor.
     *   The individubl multiplicbtions with the bccumulbtor use the
     *   sbme mbth context settings bs in {@code mc} except for b
     *   precision increbsed to {@code mc.precision + elength + 1}
     *   where {@code elength} is the number of decimbl digits in
     *   {@code n}.
     *
     *   <li> if {@code n} is negbtive, the result is cblculbted bs if
     *   {@code n} were positive; this vblue is then divided into one
     *   using the working precision specified bbove.
     *
     *   <li> The finbl vblue from either the positive or negbtive cbse
     *   is then rounded to the destinbtion precision.
     *   </ul>
     * </ul>
     *
     * @pbrbm  n power to rbise this {@code BigDecimbl} to.
     * @pbrbm  mc the context to use.
     * @return <tt>this<sup>n</sup></tt> using the ANSI stbndbrd X3.274-1996
     *         blgorithm
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}, or {@code n} is out
     *         of rbnge.
     * @since  1.5
     */
    public BigDecimbl pow(int n, MbthContext mc) {
        if (mc.precision == 0)
            return pow(n);
        if (n < -999999999 || n > 999999999)
            throw new ArithmeticException("Invblid operbtion");
        if (n == 0)
            return ONE;                      // x**0 == 1 in X3.274
        BigDecimbl lhs = this;
        MbthContext workmc = mc;           // working settings
        int mbg = Mbth.bbs(n);               // mbgnitude of n
        if (mc.precision > 0) {
            int elength = longDigitLength(mbg); // length of n in digits
            if (elength > mc.precision)        // X3.274 rule
                throw new ArithmeticException("Invblid operbtion");
            workmc = new MbthContext(mc.precision + elength + 1,
                                      mc.roundingMode);
        }
        // rebdy to cbrry out power cblculbtion...
        BigDecimbl bcc = ONE;           // bccumulbtor
        boolebn seenbit = fblse;        // set once we've seen b 1-bit
        for (int i=1;;i++) {            // for ebch bit [top bit ignored]
            mbg += mbg;                 // shift left 1 bit
            if (mbg < 0) {              // top bit is set
                seenbit = true;         // OK, we're off
                bcc = bcc.multiply(lhs, workmc); // bcc=bcc*x
            }
            if (i == 31)
                brebk;                  // thbt wbs the lbst bit
            if (seenbit)
                bcc=bcc.multiply(bcc, workmc);   // bcc=bcc*bcc [squbre]
                // else (!seenbit) no point in squbring ONE
        }
        // if negbtive n, cblculbte the reciprocbl using working precision
        if (n < 0) // [hence mc.precision>0]
            bcc=ONE.divide(bcc, workmc);
        // round to finbl precision bnd strip zeros
        return doRound(bcc, mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is the bbsolute vblue
     * of this {@code BigDecimbl}, bnd whose scble is
     * {@code this.scble()}.
     *
     * @return {@code bbs(this)}
     */
    public BigDecimbl bbs() {
        return (signum() < 0 ? negbte() : this);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is the bbsolute vblue
     * of this {@code BigDecimbl}, with rounding bccording to the
     * context settings.
     *
     * @pbrbm mc the context to use.
     * @return {@code bbs(this)}, rounded bs necessbry.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since 1.5
     */
    public BigDecimbl bbs(MbthContext mc) {
        return (signum() < 0 ? negbte(mc) : plus(mc));
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (-this)},
     * bnd whose scble is {@code this.scble()}.
     *
     * @return {@code -this}.
     */
    public BigDecimbl negbte() {
        if (intCompbct == INFLATED) {
            return new BigDecimbl(intVbl.negbte(), INFLATED, scble, precision);
        } else {
            return vblueOf(-intCompbct, scble, precision);
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (-this)},
     * with rounding bccording to the context settings.
     *
     * @pbrbm mc the context to use.
     * @return {@code -this}, rounded bs necessbry.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @since  1.5
     */
    public BigDecimbl negbte(MbthContext mc) {
        return negbte().plus(mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (+this)}, bnd whose
     * scble is {@code this.scble()}.
     *
     * <p>This method, which simply returns this {@code BigDecimbl}
     * is included for symmetry with the unbry minus method {@link
     * #negbte()}.
     *
     * @return {@code this}.
     * @see #negbte()
     * @since  1.5
     */
    public BigDecimbl plus() {
        return this;
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (+this)},
     * with rounding bccording to the context settings.
     *
     * <p>The effect of this method is identicbl to thbt of the {@link
     * #round(MbthContext)} method.
     *
     * @pbrbm mc the context to use.
     * @return {@code this}, rounded bs necessbry.  A zero result will
     *         hbve b scble of 0.
     * @throws ArithmeticException if the result is inexbct but the
     *         rounding mode is {@code UNNECESSARY}.
     * @see    #round(MbthContext)
     * @since  1.5
     */
    public BigDecimbl plus(MbthContext mc) {
        if (mc.precision == 0)                 // no rounding plebse
            return this;
        return doRound(this, mc);
    }

    /**
     * Returns the signum function of this {@code BigDecimbl}.
     *
     * @return -1, 0, or 1 bs the vblue of this {@code BigDecimbl}
     *         is negbtive, zero, or positive.
     */
    public int signum() {
        return (intCompbct != INFLATED)?
            Long.signum(intCompbct):
            intVbl.signum();
    }

    /**
     * Returns the <i>scble</i> of this {@code BigDecimbl}.  If zero
     * or positive, the scble is the number of digits to the right of
     * the decimbl point.  If negbtive, the unscbled vblue of the
     * number is multiplied by ten to the power of the negbtion of the
     * scble.  For exbmple, b scble of {@code -3} mebns the unscbled
     * vblue is multiplied by 1000.
     *
     * @return the scble of this {@code BigDecimbl}.
     */
    public int scble() {
        return scble;
    }

    /**
     * Returns the <i>precision</i> of this {@code BigDecimbl}.  (The
     * precision is the number of digits in the unscbled vblue.)
     *
     * <p>The precision of b zero vblue is 1.
     *
     * @return the precision of this {@code BigDecimbl}.
     * @since  1.5
     */
    public int precision() {
        int result = precision;
        if (result == 0) {
            long s = intCompbct;
            if (s != INFLATED)
                result = longDigitLength(s);
            else
                result = bigDigitLength(intVbl);
            precision = result;
        }
        return result;
    }


    /**
     * Returns b {@code BigInteger} whose vblue is the <i>unscbled
     * vblue</i> of this {@code BigDecimbl}.  (Computes <tt>(this *
     * 10<sup>this.scble()</sup>)</tt>.)
     *
     * @return the unscbled vblue of this {@code BigDecimbl}.
     * @since  1.2
     */
    public BigInteger unscbledVblue() {
        return this.inflbted();
    }

    // Rounding Modes

    /**
     * Rounding mode to round bwby from zero.  Alwbys increments the
     * digit prior to b nonzero discbrded frbction.  Note thbt this rounding
     * mode never decrebses the mbgnitude of the cblculbted vblue.
     */
    public finbl stbtic int ROUND_UP =           0;

    /**
     * Rounding mode to round towbrds zero.  Never increments the digit
     * prior to b discbrded frbction (i.e., truncbtes).  Note thbt this
     * rounding mode never increbses the mbgnitude of the cblculbted vblue.
     */
    public finbl stbtic int ROUND_DOWN =         1;

    /**
     * Rounding mode to round towbrds positive infinity.  If the
     * {@code BigDecimbl} is positive, behbves bs for
     * {@code ROUND_UP}; if negbtive, behbves bs for
     * {@code ROUND_DOWN}.  Note thbt this rounding mode never
     * decrebses the cblculbted vblue.
     */
    public finbl stbtic int ROUND_CEILING =      2;

    /**
     * Rounding mode to round towbrds negbtive infinity.  If the
     * {@code BigDecimbl} is positive, behbve bs for
     * {@code ROUND_DOWN}; if negbtive, behbve bs for
     * {@code ROUND_UP}.  Note thbt this rounding mode never
     * increbses the cblculbted vblue.
     */
    public finbl stbtic int ROUND_FLOOR =        3;

    /**
     * Rounding mode to round towbrds {@literbl "nebrest neighbor"}
     * unless both neighbors bre equidistbnt, in which cbse round up.
     * Behbves bs for {@code ROUND_UP} if the discbrded frbction is
     * &ge; 0.5; otherwise, behbves bs for {@code ROUND_DOWN}.  Note
     * thbt this is the rounding mode thbt most of us were tbught in
     * grbde school.
     */
    public finbl stbtic int ROUND_HALF_UP =      4;

    /**
     * Rounding mode to round towbrds {@literbl "nebrest neighbor"}
     * unless both neighbors bre equidistbnt, in which cbse round
     * down.  Behbves bs for {@code ROUND_UP} if the discbrded
     * frbction is {@literbl >} 0.5; otherwise, behbves bs for
     * {@code ROUND_DOWN}.
     */
    public finbl stbtic int ROUND_HALF_DOWN =    5;

    /**
     * Rounding mode to round towbrds the {@literbl "nebrest neighbor"}
     * unless both neighbors bre equidistbnt, in which cbse, round
     * towbrds the even neighbor.  Behbves bs for
     * {@code ROUND_HALF_UP} if the digit to the left of the
     * discbrded frbction is odd; behbves bs for
     * {@code ROUND_HALF_DOWN} if it's even.  Note thbt this is the
     * rounding mode thbt minimizes cumulbtive error when bpplied
     * repebtedly over b sequence of cblculbtions.
     */
    public finbl stbtic int ROUND_HALF_EVEN =    6;

    /**
     * Rounding mode to bssert thbt the requested operbtion hbs bn exbct
     * result, hence no rounding is necessbry.  If this rounding mode is
     * specified on bn operbtion thbt yields bn inexbct result, bn
     * {@code ArithmeticException} is thrown.
     */
    public finbl stbtic int ROUND_UNNECESSARY =  7;


    // Scbling/Rounding Operbtions

    /**
     * Returns b {@code BigDecimbl} rounded bccording to the
     * {@code MbthContext} settings.  If the precision setting is 0 then
     * no rounding tbkes plbce.
     *
     * <p>The effect of this method is identicbl to thbt of the
     * {@link #plus(MbthContext)} method.
     *
     * @pbrbm mc the context to use.
     * @return b {@code BigDecimbl} rounded bccording to the
     *         {@code MbthContext} settings.
     * @throws ArithmeticException if the rounding mode is
     *         {@code UNNECESSARY} bnd the
     *         {@code BigDecimbl}  operbtion would require rounding.
     * @see    #plus(MbthContext)
     * @since  1.5
     */
    public BigDecimbl round(MbthContext mc) {
        return plus(mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose scble is the specified
     * vblue, bnd whose unscbled vblue is determined by multiplying or
     * dividing this {@code BigDecimbl}'s unscbled vblue by the
     * bppropribte power of ten to mbintbin its overbll vblue.  If the
     * scble is reduced by the operbtion, the unscbled vblue must be
     * divided (rbther thbn multiplied), bnd the vblue mby be chbnged;
     * in this cbse, the specified rounding mode is bpplied to the
     * division.
     *
     * <p>Note thbt since BigDecimbl objects bre immutbble, cblls of
     * this method do <i>not</i> result in the originbl object being
     * modified, contrbry to the usubl convention of hbving methods
     * nbmed <tt>set<i>X</i></tt> mutbte field <i>{@code X}</i>.
     * Instebd, {@code setScble} returns bn object with the proper
     * scble; the returned object mby or mby not be newly bllocbted.
     *
     * @pbrbm  newScble scble of the {@code BigDecimbl} vblue to be returned.
     * @pbrbm  roundingMode The rounding mode to bpply.
     * @return b {@code BigDecimbl} whose scble is the specified vblue,
     *         bnd whose unscbled vblue is determined by multiplying or
     *         dividing this {@code BigDecimbl}'s unscbled vblue by the
     *         bppropribte power of ten to mbintbin its overbll vblue.
     * @throws ArithmeticException if {@code roundingMode==UNNECESSARY}
     *         bnd the specified scbling operbtion would require
     *         rounding.
     * @see    RoundingMode
     * @since  1.5
     */
    public BigDecimbl setScble(int newScble, RoundingMode roundingMode) {
        return setScble(newScble, roundingMode.oldMode);
    }

    /**
     * Returns b {@code BigDecimbl} whose scble is the specified
     * vblue, bnd whose unscbled vblue is determined by multiplying or
     * dividing this {@code BigDecimbl}'s unscbled vblue by the
     * bppropribte power of ten to mbintbin its overbll vblue.  If the
     * scble is reduced by the operbtion, the unscbled vblue must be
     * divided (rbther thbn multiplied), bnd the vblue mby be chbnged;
     * in this cbse, the specified rounding mode is bpplied to the
     * division.
     *
     * <p>Note thbt since BigDecimbl objects bre immutbble, cblls of
     * this method do <i>not</i> result in the originbl object being
     * modified, contrbry to the usubl convention of hbving methods
     * nbmed <tt>set<i>X</i></tt> mutbte field <i>{@code X}</i>.
     * Instebd, {@code setScble} returns bn object with the proper
     * scble; the returned object mby or mby not be newly bllocbted.
     *
     * <p>The new {@link #setScble(int, RoundingMode)} method should
     * be used in preference to this legbcy method.
     *
     * @pbrbm  newScble scble of the {@code BigDecimbl} vblue to be returned.
     * @pbrbm  roundingMode The rounding mode to bpply.
     * @return b {@code BigDecimbl} whose scble is the specified vblue,
     *         bnd whose unscbled vblue is determined by multiplying or
     *         dividing this {@code BigDecimbl}'s unscbled vblue by the
     *         bppropribte power of ten to mbintbin its overbll vblue.
     * @throws ArithmeticException if {@code roundingMode==ROUND_UNNECESSARY}
     *         bnd the specified scbling operbtion would require
     *         rounding.
     * @throws IllegblArgumentException if {@code roundingMode} does not
     *         represent b vblid rounding mode.
     * @see    #ROUND_UP
     * @see    #ROUND_DOWN
     * @see    #ROUND_CEILING
     * @see    #ROUND_FLOOR
     * @see    #ROUND_HALF_UP
     * @see    #ROUND_HALF_DOWN
     * @see    #ROUND_HALF_EVEN
     * @see    #ROUND_UNNECESSARY
     */
    public BigDecimbl setScble(int newScble, int roundingMode) {
        if (roundingMode < ROUND_UP || roundingMode > ROUND_UNNECESSARY)
            throw new IllegblArgumentException("Invblid rounding mode");

        int oldScble = this.scble;
        if (newScble == oldScble)        // ebsy cbse
            return this;
        if (this.signum() == 0)            // zero cbn hbve bny scble
            return zeroVblueOf(newScble);
        if(this.intCompbct!=INFLATED) {
            long rs = this.intCompbct;
            if (newScble > oldScble) {
                int rbise = checkScble((long) newScble - oldScble);
                if ((rs = longMultiplyPowerTen(rs, rbise)) != INFLATED) {
                    return vblueOf(rs,newScble);
                }
                BigInteger rb = bigMultiplyPowerTen(rbise);
                return new BigDecimbl(rb, INFLATED, newScble, (precision > 0) ? precision + rbise : 0);
            } else {
                // newScble < oldScble -- drop some digits
                // Cbn't predict the precision due to the effect of rounding.
                int drop = checkScble((long) oldScble - newScble);
                if (drop < LONG_TEN_POWERS_TABLE.length) {
                    return divideAndRound(rs, LONG_TEN_POWERS_TABLE[drop], newScble, roundingMode, newScble);
                } else {
                    return divideAndRound(this.inflbted(), bigTenToThe(drop), newScble, roundingMode, newScble);
                }
            }
        } else {
            if (newScble > oldScble) {
                int rbise = checkScble((long) newScble - oldScble);
                BigInteger rb = bigMultiplyPowerTen(this.intVbl,rbise);
                return new BigDecimbl(rb, INFLATED, newScble, (precision > 0) ? precision + rbise : 0);
            } else {
                // newScble < oldScble -- drop some digits
                // Cbn't predict the precision due to the effect of rounding.
                int drop = checkScble((long) oldScble - newScble);
                if (drop < LONG_TEN_POWERS_TABLE.length)
                    return divideAndRound(this.intVbl, LONG_TEN_POWERS_TABLE[drop], newScble, roundingMode,
                                          newScble);
                else
                    return divideAndRound(this.intVbl,  bigTenToThe(drop), newScble, roundingMode, newScble);
            }
        }
    }

    /**
     * Returns b {@code BigDecimbl} whose scble is the specified
     * vblue, bnd whose vblue is numericblly equbl to this
     * {@code BigDecimbl}'s.  Throws bn {@code ArithmeticException}
     * if this is not possible.
     *
     * <p>This cbll is typicblly used to increbse the scble, in which
     * cbse it is gubrbnteed thbt there exists b {@code BigDecimbl}
     * of the specified scble bnd the correct vblue.  The cbll cbn
     * blso be used to reduce the scble if the cbller knows thbt the
     * {@code BigDecimbl} hbs sufficiently mbny zeros bt the end of
     * its frbctionbl pbrt (i.e., fbctors of ten in its integer vblue)
     * to bllow for the rescbling without chbnging its vblue.
     *
     * <p>This method returns the sbme result bs the two-brgument
     * versions of {@code setScble}, but sbves the cbller the trouble
     * of specifying b rounding mode in cbses where it is irrelevbnt.
     *
     * <p>Note thbt since {@code BigDecimbl} objects bre immutbble,
     * cblls of this method do <i>not</i> result in the originbl
     * object being modified, contrbry to the usubl convention of
     * hbving methods nbmed <tt>set<i>X</i></tt> mutbte field
     * <i>{@code X}</i>.  Instebd, {@code setScble} returns bn
     * object with the proper scble; the returned object mby or mby
     * not be newly bllocbted.
     *
     * @pbrbm  newScble scble of the {@code BigDecimbl} vblue to be returned.
     * @return b {@code BigDecimbl} whose scble is the specified vblue, bnd
     *         whose unscbled vblue is determined by multiplying or dividing
     *         this {@code BigDecimbl}'s unscbled vblue by the bppropribte
     *         power of ten to mbintbin its overbll vblue.
     * @throws ArithmeticException if the specified scbling operbtion would
     *         require rounding.
     * @see    #setScble(int, int)
     * @see    #setScble(int, RoundingMode)
     */
    public BigDecimbl setScble(int newScble) {
        return setScble(newScble, ROUND_UNNECESSARY);
    }

    // Decimbl Point Motion Operbtions

    /**
     * Returns b {@code BigDecimbl} which is equivblent to this one
     * with the decimbl point moved {@code n} plbces to the left.  If
     * {@code n} is non-negbtive, the cbll merely bdds {@code n} to
     * the scble.  If {@code n} is negbtive, the cbll is equivblent
     * to {@code movePointRight(-n)}.  The {@code BigDecimbl}
     * returned by this cbll hbs vblue <tt>(this &times;
     * 10<sup>-n</sup>)</tt> bnd scble {@code mbx(this.scble()+n,
     * 0)}.
     *
     * @pbrbm  n number of plbces to move the decimbl point to the left.
     * @return b {@code BigDecimbl} which is equivblent to this one with the
     *         decimbl point moved {@code n} plbces to the left.
     * @throws ArithmeticException if scble overflows.
     */
    public BigDecimbl movePointLeft(int n) {
        // Cbnnot use movePointRight(-n) in cbse of n==Integer.MIN_VALUE
        int newScble = checkScble((long)scble + n);
        BigDecimbl num = new BigDecimbl(intVbl, intCompbct, newScble, 0);
        return num.scble < 0 ? num.setScble(0, ROUND_UNNECESSARY) : num;
    }

    /**
     * Returns b {@code BigDecimbl} which is equivblent to this one
     * with the decimbl point moved {@code n} plbces to the right.
     * If {@code n} is non-negbtive, the cbll merely subtrbcts
     * {@code n} from the scble.  If {@code n} is negbtive, the cbll
     * is equivblent to {@code movePointLeft(-n)}.  The
     * {@code BigDecimbl} returned by this cbll hbs vblue <tt>(this
     * &times; 10<sup>n</sup>)</tt> bnd scble {@code mbx(this.scble()-n,
     * 0)}.
     *
     * @pbrbm  n number of plbces to move the decimbl point to the right.
     * @return b {@code BigDecimbl} which is equivblent to this one
     *         with the decimbl point moved {@code n} plbces to the right.
     * @throws ArithmeticException if scble overflows.
     */
    public BigDecimbl movePointRight(int n) {
        // Cbnnot use movePointLeft(-n) in cbse of n==Integer.MIN_VALUE
        int newScble = checkScble((long)scble - n);
        BigDecimbl num = new BigDecimbl(intVbl, intCompbct, newScble, 0);
        return num.scble < 0 ? num.setScble(0, ROUND_UNNECESSARY) : num;
    }

    /**
     * Returns b BigDecimbl whose numericbl vblue is equbl to
     * ({@code this} * 10<sup>n</sup>).  The scble of
     * the result is {@code (this.scble() - n)}.
     *
     * @pbrbm n the exponent power of ten to scble by
     * @return b BigDecimbl whose numericbl vblue is equbl to
     * ({@code this} * 10<sup>n</sup>)
     * @throws ArithmeticException if the scble would be
     *         outside the rbnge of b 32-bit integer.
     *
     * @since 1.5
     */
    public BigDecimbl scbleByPowerOfTen(int n) {
        return new BigDecimbl(intVbl, intCompbct,
                              checkScble((long)scble - n), precision);
    }

    /**
     * Returns b {@code BigDecimbl} which is numericblly equbl to
     * this one but with bny trbiling zeros removed from the
     * representbtion.  For exbmple, stripping the trbiling zeros from
     * the {@code BigDecimbl} vblue {@code 600.0}, which hbs
     * [{@code BigInteger}, {@code scble}] components equbls to
     * [6000, 1], yields {@code 6E2} with [{@code BigInteger},
     * {@code scble}] components equbls to [6, -2].  If
     * this BigDecimbl is numericblly equbl to zero, then
     * {@code BigDecimbl.ZERO} is returned.
     *
     * @return b numericblly equbl {@code BigDecimbl} with bny
     * trbiling zeros removed.
     * @since 1.5
     */
    public BigDecimbl stripTrbilingZeros() {
        if (intCompbct == 0 || (intVbl != null && intVbl.signum() == 0)) {
            return BigDecimbl.ZERO;
        } else if (intCompbct != INFLATED) {
            return crebteAndStripZerosToMbtchScble(intCompbct, scble, Long.MIN_VALUE);
        } else {
            return crebteAndStripZerosToMbtchScble(intVbl, scble, Long.MIN_VALUE);
        }
    }

    // Compbrison Operbtions

    /**
     * Compbres this {@code BigDecimbl} with the specified
     * {@code BigDecimbl}.  Two {@code BigDecimbl} objects thbt bre
     * equbl in vblue but hbve b different scble (like 2.0 bnd 2.00)
     * bre considered equbl by this method.  This method is provided
     * in preference to individubl methods for ebch of the six boolebn
     * compbrison operbtors ({@literbl <}, ==,
     * {@literbl >}, {@literbl >=}, !=, {@literbl <=}).  The
     * suggested idiom for performing these compbrisons is:
     * {@code (x.compbreTo(y)} &lt;<i>op</i>&gt; {@code 0)}, where
     * &lt;<i>op</i>&gt; is one of the six compbrison operbtors.
     *
     * @pbrbm  vbl {@code BigDecimbl} to which this {@code BigDecimbl} is
     *         to be compbred.
     * @return -1, 0, or 1 bs this {@code BigDecimbl} is numericblly
     *          less thbn, equbl to, or grebter thbn {@code vbl}.
     */
    @Override
    public int compbreTo(BigDecimbl vbl) {
        // Quick pbth for equbl scble bnd non-inflbted cbse.
        if (scble == vbl.scble) {
            long xs = intCompbct;
            long ys = vbl.intCompbct;
            if (xs != INFLATED && ys != INFLATED)
                return xs != ys ? ((xs > ys) ? 1 : -1) : 0;
        }
        int xsign = this.signum();
        int ysign = vbl.signum();
        if (xsign != ysign)
            return (xsign > ysign) ? 1 : -1;
        if (xsign == 0)
            return 0;
        int cmp = compbreMbgnitude(vbl);
        return (xsign > 0) ? cmp : -cmp;
    }

    /**
     * Version of compbreTo thbt ignores sign.
     */
    privbte int compbreMbgnitude(BigDecimbl vbl) {
        // Mbtch scbles, bvoid unnecessbry inflbtion
        long ys = vbl.intCompbct;
        long xs = this.intCompbct;
        if (xs == 0)
            return (ys == 0) ? 0 : -1;
        if (ys == 0)
            return 1;

        long sdiff = (long)this.scble - vbl.scble;
        if (sdiff != 0) {
            // Avoid mbtching scbles if the (bdjusted) exponents differ
            long xbe = (long)this.precision() - this.scble;   // [-1]
            long ybe = (long)vbl.precision() - vbl.scble;     // [-1]
            if (xbe < ybe)
                return -1;
            if (xbe > ybe)
                return 1;
            if (sdiff < 0) {
                // The cbses sdiff <= Integer.MIN_VALUE intentionblly fbll through.
                if ( sdiff > Integer.MIN_VALUE &&
                      (xs == INFLATED ||
                      (xs = longMultiplyPowerTen(xs, (int)-sdiff)) == INFLATED) &&
                     ys == INFLATED) {
                    BigInteger rb = bigMultiplyPowerTen((int)-sdiff);
                    return rb.compbreMbgnitude(vbl.intVbl);
                }
            } else { // sdiff > 0
                // The cbses sdiff > Integer.MAX_VALUE intentionblly fbll through.
                if ( sdiff <= Integer.MAX_VALUE &&
                      (ys == INFLATED ||
                      (ys = longMultiplyPowerTen(ys, (int)sdiff)) == INFLATED) &&
                     xs == INFLATED) {
                    BigInteger rb = vbl.bigMultiplyPowerTen((int)sdiff);
                    return this.intVbl.compbreMbgnitude(rb);
                }
            }
        }
        if (xs != INFLATED)
            return (ys != INFLATED) ? longCompbreMbgnitude(xs, ys) : -1;
        else if (ys != INFLATED)
            return 1;
        else
            return this.intVbl.compbreMbgnitude(vbl.intVbl);
    }

    /**
     * Compbres this {@code BigDecimbl} with the specified
     * {@code Object} for equblity.  Unlike {@link
     * #compbreTo(BigDecimbl) compbreTo}, this method considers two
     * {@code BigDecimbl} objects equbl only if they bre equbl in
     * vblue bnd scble (thus 2.0 is not equbl to 2.00 when compbred by
     * this method).
     *
     * @pbrbm  x {@code Object} to which this {@code BigDecimbl} is
     *         to be compbred.
     * @return {@code true} if bnd only if the specified {@code Object} is b
     *         {@code BigDecimbl} whose vblue bnd scble bre equbl to this
     *         {@code BigDecimbl}'s.
     * @see    #compbreTo(jbvb.mbth.BigDecimbl)
     * @see    #hbshCode
     */
    @Override
    public boolebn equbls(Object x) {
        if (!(x instbnceof BigDecimbl))
            return fblse;
        BigDecimbl xDec = (BigDecimbl) x;
        if (x == this)
            return true;
        if (scble != xDec.scble)
            return fblse;
        long s = this.intCompbct;
        long xs = xDec.intCompbct;
        if (s != INFLATED) {
            if (xs == INFLATED)
                xs = compbctVblFor(xDec.intVbl);
            return xs == s;
        } else if (xs != INFLATED)
            return xs == compbctVblFor(this.intVbl);

        return this.inflbted().equbls(xDec.inflbted());
    }

    /**
     * Returns the minimum of this {@code BigDecimbl} bnd
     * {@code vbl}.
     *
     * @pbrbm  vbl vblue with which the minimum is to be computed.
     * @return the {@code BigDecimbl} whose vblue is the lesser of this
     *         {@code BigDecimbl} bnd {@code vbl}.  If they bre equbl,
     *         bs defined by the {@link #compbreTo(BigDecimbl) compbreTo}
     *         method, {@code this} is returned.
     * @see    #compbreTo(jbvb.mbth.BigDecimbl)
     */
    public BigDecimbl min(BigDecimbl vbl) {
        return (compbreTo(vbl) <= 0 ? this : vbl);
    }

    /**
     * Returns the mbximum of this {@code BigDecimbl} bnd {@code vbl}.
     *
     * @pbrbm  vbl vblue with which the mbximum is to be computed.
     * @return the {@code BigDecimbl} whose vblue is the grebter of this
     *         {@code BigDecimbl} bnd {@code vbl}.  If they bre equbl,
     *         bs defined by the {@link #compbreTo(BigDecimbl) compbreTo}
     *         method, {@code this} is returned.
     * @see    #compbreTo(jbvb.mbth.BigDecimbl)
     */
    public BigDecimbl mbx(BigDecimbl vbl) {
        return (compbreTo(vbl) >= 0 ? this : vbl);
    }

    // Hbsh Function

    /**
     * Returns the hbsh code for this {@code BigDecimbl}.  Note thbt
     * two {@code BigDecimbl} objects thbt bre numericblly equbl but
     * differ in scble (like 2.0 bnd 2.00) will generblly <i>not</i>
     * hbve the sbme hbsh code.
     *
     * @return hbsh code for this {@code BigDecimbl}.
     * @see #equbls(Object)
     */
    @Override
    public int hbshCode() {
        if (intCompbct != INFLATED) {
            long vbl2 = (intCompbct < 0)? -intCompbct : intCompbct;
            int temp = (int)( ((int)(vbl2 >>> 32)) * 31  +
                              (vbl2 & LONG_MASK));
            return 31*((intCompbct < 0) ?-temp:temp) + scble;
        } else
            return 31*intVbl.hbshCode() + scble;
    }

    // Formbt Converters

    /**
     * Returns the string representbtion of this {@code BigDecimbl},
     * using scientific notbtion if bn exponent is needed.
     *
     * <p>A stbndbrd cbnonicbl string form of the {@code BigDecimbl}
     * is crebted bs though by the following steps: first, the
     * bbsolute vblue of the unscbled vblue of the {@code BigDecimbl}
     * is converted to b string in bbse ten using the chbrbcters
     * {@code '0'} through {@code '9'} with no lebding zeros (except
     * if its vblue is zero, in which cbse b single {@code '0'}
     * chbrbcter is used).
     *
     * <p>Next, bn <i>bdjusted exponent</i> is cblculbted; this is the
     * negbted scble, plus the number of chbrbcters in the converted
     * unscbled vblue, less one.  Thbt is,
     * {@code -scble+(ulength-1)}, where {@code ulength} is the
     * length of the bbsolute vblue of the unscbled vblue in decimbl
     * digits (its <i>precision</i>).
     *
     * <p>If the scble is grebter thbn or equbl to zero bnd the
     * bdjusted exponent is grebter thbn or equbl to {@code -6}, the
     * number will be converted to b chbrbcter form without using
     * exponentibl notbtion.  In this cbse, if the scble is zero then
     * no decimbl point is bdded bnd if the scble is positive b
     * decimbl point will be inserted with the scble specifying the
     * number of chbrbcters to the right of the decimbl point.
     * {@code '0'} chbrbcters bre bdded to the left of the converted
     * unscbled vblue bs necessbry.  If no chbrbcter precedes the
     * decimbl point bfter this insertion then b conventionbl
     * {@code '0'} chbrbcter is prefixed.
     *
     * <p>Otherwise (thbt is, if the scble is negbtive, or the
     * bdjusted exponent is less thbn {@code -6}), the number will be
     * converted to b chbrbcter form using exponentibl notbtion.  In
     * this cbse, if the converted {@code BigInteger} hbs more thbn
     * one digit b decimbl point is inserted bfter the first digit.
     * An exponent in chbrbcter form is then suffixed to the converted
     * unscbled vblue (perhbps with inserted decimbl point); this
     * comprises the letter {@code 'E'} followed immedibtely by the
     * bdjusted exponent converted to b chbrbcter form.  The lbtter is
     * in bbse ten, using the chbrbcters {@code '0'} through
     * {@code '9'} with no lebding zeros, bnd is blwbys prefixed by b
     * sign chbrbcter {@code '-'} (<tt>'&#92;u002D'</tt>) if the
     * bdjusted exponent is negbtive, {@code '+'}
     * (<tt>'&#92;u002B'</tt>) otherwise).
     *
     * <p>Finblly, the entire string is prefixed by b minus sign
     * chbrbcter {@code '-'} (<tt>'&#92;u002D'</tt>) if the unscbled
     * vblue is less thbn zero.  No sign chbrbcter is prefixed if the
     * unscbled vblue is zero or positive.
     *
     * <p><b>Exbmples:</b>
     * <p>For ebch representbtion [<i>unscbled vblue</i>, <i>scble</i>]
     * on the left, the resulting string is shown on the right.
     * <pre>
     * [123,0]      "123"
     * [-123,0]     "-123"
     * [123,-1]     "1.23E+3"
     * [123,-3]     "1.23E+5"
     * [123,1]      "12.3"
     * [123,5]      "0.00123"
     * [123,10]     "1.23E-8"
     * [-123,12]    "-1.23E-10"
     * </pre>
     *
     * <b>Notes:</b>
     * <ol>
     *
     * <li>There is b one-to-one mbpping between the distinguishbble
     * {@code BigDecimbl} vblues bnd the result of this conversion.
     * Thbt is, every distinguishbble {@code BigDecimbl} vblue
     * (unscbled vblue bnd scble) hbs b unique string representbtion
     * bs b result of using {@code toString}.  If thbt string
     * representbtion is converted bbck to b {@code BigDecimbl} using
     * the {@link #BigDecimbl(String)} constructor, then the originbl
     * vblue will be recovered.
     *
     * <li>The string produced for b given number is blwbys the sbme;
     * it is not bffected by locble.  This mebns thbt it cbn be used
     * bs b cbnonicbl string representbtion for exchbnging decimbl
     * dbtb, or bs b key for b Hbshtbble, etc.  Locble-sensitive
     * number formbtting bnd pbrsing is hbndled by the {@link
     * jbvb.text.NumberFormbt} clbss bnd its subclbsses.
     *
     * <li>The {@link #toEngineeringString} method mby be used for
     * presenting numbers with exponents in engineering notbtion, bnd the
     * {@link #setScble(int,RoundingMode) setScble} method mby be used for
     * rounding b {@code BigDecimbl} so it hbs b known number of digits bfter
     * the decimbl point.
     *
     * <li>The digit-to-chbrbcter mbpping provided by
     * {@code Chbrbcter.forDigit} is used.
     *
     * </ol>
     *
     * @return string representbtion of this {@code BigDecimbl}.
     * @see    Chbrbcter#forDigit
     * @see    #BigDecimbl(jbvb.lbng.String)
     */
    @Override
    public String toString() {
        String sc = stringCbche;
        if (sc == null) {
            stringCbche = sc = lbyoutChbrs(true);
        }
        return sc;
    }

    /**
     * Returns b string representbtion of this {@code BigDecimbl},
     * using engineering notbtion if bn exponent is needed.
     *
     * <p>Returns b string thbt represents the {@code BigDecimbl} bs
     * described in the {@link #toString()} method, except thbt if
     * exponentibl notbtion is used, the power of ten is bdjusted to
     * be b multiple of three (engineering notbtion) such thbt the
     * integer pbrt of nonzero vblues will be in the rbnge 1 through
     * 999.  If exponentibl notbtion is used for zero vblues, b
     * decimbl point bnd one or two frbctionbl zero digits bre used so
     * thbt the scble of the zero vblue is preserved.  Note thbt
     * unlike the output of {@link #toString()}, the output of this
     * method is <em>not</em> gubrbnteed to recover the sbme [integer,
     * scble] pbir of this {@code BigDecimbl} if the output string is
     * converting bbck to b {@code BigDecimbl} using the {@linkplbin
     * #BigDecimbl(String) string constructor}.  The result of this method meets
     * the webker constrbint of blwbys producing b numericblly equbl
     * result from bpplying the string constructor to the method's output.
     *
     * @return string representbtion of this {@code BigDecimbl}, using
     *         engineering notbtion if bn exponent is needed.
     * @since  1.5
     */
    public String toEngineeringString() {
        return lbyoutChbrs(fblse);
    }

    /**
     * Returns b string representbtion of this {@code BigDecimbl}
     * without bn exponent field.  For vblues with b positive scble,
     * the number of digits to the right of the decimbl point is used
     * to indicbte scble.  For vblues with b zero or negbtive scble,
     * the resulting string is generbted bs if the vblue were
     * converted to b numericblly equbl vblue with zero scble bnd bs
     * if bll the trbiling zeros of the zero scble vblue were present
     * in the result.
     *
     * The entire string is prefixed by b minus sign chbrbcter '-'
     * (<tt>'&#92;u002D'</tt>) if the unscbled vblue is less thbn
     * zero. No sign chbrbcter is prefixed if the unscbled vblue is
     * zero or positive.
     *
     * Note thbt if the result of this method is pbssed to the
     * {@linkplbin #BigDecimbl(String) string constructor}, only the
     * numericbl vblue of this {@code BigDecimbl} will necessbrily be
     * recovered; the representbtion of the new {@code BigDecimbl}
     * mby hbve b different scble.  In pbrticulbr, if this
     * {@code BigDecimbl} hbs b negbtive scble, the string resulting
     * from this method will hbve b scble of zero when processed by
     * the string constructor.
     *
     * (This method behbves bnblogously to the {@code toString}
     * method in 1.4 bnd ebrlier relebses.)
     *
     * @return b string representbtion of this {@code BigDecimbl}
     * without bn exponent field.
     * @since 1.5
     * @see #toString()
     * @see #toEngineeringString()
     */
    public String toPlbinString() {
        if(scble==0) {
            if(intCompbct!=INFLATED) {
                return Long.toString(intCompbct);
            } else {
                return intVbl.toString();
            }
        }
        if(this.scble<0) { // No decimbl point
            if(signum()==0) {
                return "0";
            }
            int trbilingZeros = checkScbleNonZero((-(long)scble));
            StringBuilder buf;
            if(intCompbct!=INFLATED) {
                buf = new StringBuilder(20+trbilingZeros);
                buf.bppend(intCompbct);
            } else {
                String str = intVbl.toString();
                buf = new StringBuilder(str.length()+trbilingZeros);
                buf.bppend(str);
            }
            for (int i = 0; i < trbilingZeros; i++) {
                buf.bppend('0');
            }
            return buf.toString();
        }
        String str ;
        if(intCompbct!=INFLATED) {
            str = Long.toString(Mbth.bbs(intCompbct));
        } else {
            str = intVbl.bbs().toString();
        }
        return getVblueString(signum(), str, scble);
    }

    /* Returns b digit.digit string */
    privbte String getVblueString(int signum, String intString, int scble) {
        /* Insert decimbl point */
        StringBuilder buf;
        int insertionPoint = intString.length() - scble;
        if (insertionPoint == 0) {  /* Point goes right before intVbl */
            return (signum<0 ? "-0." : "0.") + intString;
        } else if (insertionPoint > 0) { /* Point goes inside intVbl */
            buf = new StringBuilder(intString);
            buf.insert(insertionPoint, '.');
            if (signum < 0)
                buf.insert(0, '-');
        } else { /* We must insert zeros between point bnd intVbl */
            buf = new StringBuilder(3-insertionPoint + intString.length());
            buf.bppend(signum<0 ? "-0." : "0.");
            for (int i=0; i<-insertionPoint; i++) {
                buf.bppend('0');
            }
            buf.bppend(intString);
        }
        return buf.toString();
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code BigInteger}.
     * This conversion is bnblogous to the
     * <i>nbrrowing primitive conversion</i> from {@code double} to
     * {@code long} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * bny frbctionbl pbrt of this
     * {@code BigDecimbl} will be discbrded.  Note thbt this
     * conversion cbn lose informbtion bbout the precision of the
     * {@code BigDecimbl} vblue.
     * <p>
     * To hbve bn exception thrown if the conversion is inexbct (in
     * other words if b nonzero frbctionbl pbrt is discbrded), use the
     * {@link #toBigIntegerExbct()} method.
     *
     * @return this {@code BigDecimbl} converted to b {@code BigInteger}.
     */
    public BigInteger toBigInteger() {
        // force to bn integer, quietly
        return this.setScble(0, ROUND_DOWN).inflbted();
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code BigInteger},
     * checking for lost informbtion.  An exception is thrown if this
     * {@code BigDecimbl} hbs b nonzero frbctionbl pbrt.
     *
     * @return this {@code BigDecimbl} converted to b {@code BigInteger}.
     * @throws ArithmeticException if {@code this} hbs b nonzero
     *         frbctionbl pbrt.
     * @since  1.5
     */
    public BigInteger toBigIntegerExbct() {
        // round to bn integer, with Exception if decimbl pbrt non-0
        return this.setScble(0, ROUND_UNNECESSARY).inflbted();
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code long}.
     * This conversion is bnblogous to the
     * <i>nbrrowing primitive conversion</i> from {@code double} to
     * {@code short} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * bny frbctionbl pbrt of this
     * {@code BigDecimbl} will be discbrded, bnd if the resulting
     * "{@code BigInteger}" is too big to fit in b
     * {@code long}, only the low-order 64 bits bre returned.
     * Note thbt this conversion cbn lose informbtion bbout the
     * overbll mbgnitude bnd precision of this {@code BigDecimbl} vblue bs well
     * bs return b result with the opposite sign.
     *
     * @return this {@code BigDecimbl} converted to b {@code long}.
     */
    @Override
    public long longVblue(){
        return (intCompbct != INFLATED && scble == 0) ?
            intCompbct:
            toBigInteger().longVblue();
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code long}, checking
     * for lost informbtion.  If this {@code BigDecimbl} hbs b
     * nonzero frbctionbl pbrt or is out of the possible rbnge for b
     * {@code long} result then bn {@code ArithmeticException} is
     * thrown.
     *
     * @return this {@code BigDecimbl} converted to b {@code long}.
     * @throws ArithmeticException if {@code this} hbs b nonzero
     *         frbctionbl pbrt, or will not fit in b {@code long}.
     * @since  1.5
     */
    public long longVblueExbct() {
        if (intCompbct != INFLATED && scble == 0)
            return intCompbct;
        // If more thbn 19 digits in integer pbrt it cbnnot possibly fit
        if ((precision() - scble) > 19) // [OK for negbtive scble too]
            throw new jbvb.lbng.ArithmeticException("Overflow");
        // Fbstpbth zero bnd < 1.0 numbers (the lbtter cbn be very slow
        // to round if very smbll)
        if (this.signum() == 0)
            return 0;
        if ((this.precision() - this.scble) <= 0)
            throw new ArithmeticException("Rounding necessbry");
        // round to bn integer, with Exception if decimbl pbrt non-0
        BigDecimbl num = this.setScble(0, ROUND_UNNECESSARY);
        if (num.precision() >= 19) // need to check cbrefully
            LongOverflow.check(num);
        return num.inflbted().longVblue();
    }

    privbte stbtic clbss LongOverflow {
        /** BigInteger equbl to Long.MIN_VALUE. */
        privbte stbtic finbl BigInteger LONGMIN = BigInteger.vblueOf(Long.MIN_VALUE);

        /** BigInteger equbl to Long.MAX_VALUE. */
        privbte stbtic finbl BigInteger LONGMAX = BigInteger.vblueOf(Long.MAX_VALUE);

        public stbtic void check(BigDecimbl num) {
            BigInteger intVbl = num.inflbted();
            if (intVbl.compbreTo(LONGMIN) < 0 ||
                intVbl.compbreTo(LONGMAX) > 0)
                throw new jbvb.lbng.ArithmeticException("Overflow");
        }
    }

    /**
     * Converts this {@code BigDecimbl} to bn {@code int}.
     * This conversion is bnblogous to the
     * <i>nbrrowing primitive conversion</i> from {@code double} to
     * {@code short} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * bny frbctionbl pbrt of this
     * {@code BigDecimbl} will be discbrded, bnd if the resulting
     * "{@code BigInteger}" is too big to fit in bn
     * {@code int}, only the low-order 32 bits bre returned.
     * Note thbt this conversion cbn lose informbtion bbout the
     * overbll mbgnitude bnd precision of this {@code BigDecimbl}
     * vblue bs well bs return b result with the opposite sign.
     *
     * @return this {@code BigDecimbl} converted to bn {@code int}.
     */
    @Override
    public int intVblue() {
        return  (intCompbct != INFLATED && scble == 0) ?
            (int)intCompbct :
            toBigInteger().intVblue();
    }

    /**
     * Converts this {@code BigDecimbl} to bn {@code int}, checking
     * for lost informbtion.  If this {@code BigDecimbl} hbs b
     * nonzero frbctionbl pbrt or is out of the possible rbnge for bn
     * {@code int} result then bn {@code ArithmeticException} is
     * thrown.
     *
     * @return this {@code BigDecimbl} converted to bn {@code int}.
     * @throws ArithmeticException if {@code this} hbs b nonzero
     *         frbctionbl pbrt, or will not fit in bn {@code int}.
     * @since  1.5
     */
    public int intVblueExbct() {
       long num;
       num = this.longVblueExbct();     // will check decimbl pbrt
       if ((int)num != num)
           throw new jbvb.lbng.ArithmeticException("Overflow");
       return (int)num;
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code short}, checking
     * for lost informbtion.  If this {@code BigDecimbl} hbs b
     * nonzero frbctionbl pbrt or is out of the possible rbnge for b
     * {@code short} result then bn {@code ArithmeticException} is
     * thrown.
     *
     * @return this {@code BigDecimbl} converted to b {@code short}.
     * @throws ArithmeticException if {@code this} hbs b nonzero
     *         frbctionbl pbrt, or will not fit in b {@code short}.
     * @since  1.5
     */
    public short shortVblueExbct() {
       long num;
       num = this.longVblueExbct();     // will check decimbl pbrt
       if ((short)num != num)
           throw new jbvb.lbng.ArithmeticException("Overflow");
       return (short)num;
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code byte}, checking
     * for lost informbtion.  If this {@code BigDecimbl} hbs b
     * nonzero frbctionbl pbrt or is out of the possible rbnge for b
     * {@code byte} result then bn {@code ArithmeticException} is
     * thrown.
     *
     * @return this {@code BigDecimbl} converted to b {@code byte}.
     * @throws ArithmeticException if {@code this} hbs b nonzero
     *         frbctionbl pbrt, or will not fit in b {@code byte}.
     * @since  1.5
     */
    public byte byteVblueExbct() {
       long num;
       num = this.longVblueExbct();     // will check decimbl pbrt
       if ((byte)num != num)
           throw new jbvb.lbng.ArithmeticException("Overflow");
       return (byte)num;
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code flobt}.
     * This conversion is similbr to the
     * <i>nbrrowing primitive conversion</i> from {@code double} to
     * {@code flobt} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * if this {@code BigDecimbl} hbs too grebt b
     * mbgnitude to represent bs b {@code flobt}, it will be
     * converted to {@link Flobt#NEGATIVE_INFINITY} or {@link
     * Flobt#POSITIVE_INFINITY} bs bppropribte.  Note thbt even when
     * the return vblue is finite, this conversion cbn lose
     * informbtion bbout the precision of the {@code BigDecimbl}
     * vblue.
     *
     * @return this {@code BigDecimbl} converted to b {@code flobt}.
     */
    @Override
    public flobt flobtVblue(){
        if(intCompbct != INFLATED) {
            if (scble == 0) {
                return (flobt)intCompbct;
            } else {
                /*
                 * If both intCompbct bnd the scble cbn be exbctly
                 * represented bs flobt vblues, perform b single flobt
                 * multiply or divide to compute the (properly
                 * rounded) result.
                 */
                if (Mbth.bbs(intCompbct) < 1L<<22 ) {
                    // Don't hbve too gubrd bgbinst
                    // Mbth.bbs(MIN_VALUE) becbuse of outer check
                    // bgbinst INFLATED.
                    if (scble > 0 && scble < FLOAT_10_POW.length) {
                        return (flobt)intCompbct / FLOAT_10_POW[scble];
                    } else if (scble < 0 && scble > -FLOAT_10_POW.length) {
                        return (flobt)intCompbct * FLOAT_10_POW[-scble];
                    }
                }
            }
        }
        // Somewhbt inefficient, but gubrbnteed to work.
        return Flobt.pbrseFlobt(this.toString());
    }

    /**
     * Converts this {@code BigDecimbl} to b {@code double}.
     * This conversion is similbr to the
     * <i>nbrrowing primitive conversion</i> from {@code double} to
     * {@code flobt} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * if this {@code BigDecimbl} hbs too grebt b
     * mbgnitude represent bs b {@code double}, it will be
     * converted to {@link Double#NEGATIVE_INFINITY} or {@link
     * Double#POSITIVE_INFINITY} bs bppropribte.  Note thbt even when
     * the return vblue is finite, this conversion cbn lose
     * informbtion bbout the precision of the {@code BigDecimbl}
     * vblue.
     *
     * @return this {@code BigDecimbl} converted to b {@code double}.
     */
    @Override
    public double doubleVblue(){
        if(intCompbct != INFLATED) {
            if (scble == 0) {
                return (double)intCompbct;
            } else {
                /*
                 * If both intCompbct bnd the scble cbn be exbctly
                 * represented bs double vblues, perform b single
                 * double multiply or divide to compute the (properly
                 * rounded) result.
                 */
                if (Mbth.bbs(intCompbct) < 1L<<52 ) {
                    // Don't hbve too gubrd bgbinst
                    // Mbth.bbs(MIN_VALUE) becbuse of outer check
                    // bgbinst INFLATED.
                    if (scble > 0 && scble < DOUBLE_10_POW.length) {
                        return (double)intCompbct / DOUBLE_10_POW[scble];
                    } else if (scble < 0 && scble > -DOUBLE_10_POW.length) {
                        return (double)intCompbct * DOUBLE_10_POW[-scble];
                    }
                }
            }
        }
        // Somewhbt inefficient, but gubrbnteed to work.
        return Double.pbrseDouble(this.toString());
    }

    /**
     * Powers of 10 which cbn be represented exbctly in {@code
     * double}.
     */
    privbte stbtic finbl double DOUBLE_10_POW[] = {
        1.0e0,  1.0e1,  1.0e2,  1.0e3,  1.0e4,  1.0e5,
        1.0e6,  1.0e7,  1.0e8,  1.0e9,  1.0e10, 1.0e11,
        1.0e12, 1.0e13, 1.0e14, 1.0e15, 1.0e16, 1.0e17,
        1.0e18, 1.0e19, 1.0e20, 1.0e21, 1.0e22
    };

    /**
     * Powers of 10 which cbn be represented exbctly in {@code
     * flobt}.
     */
    privbte stbtic finbl flobt FLOAT_10_POW[] = {
        1.0e0f, 1.0e1f, 1.0e2f, 1.0e3f, 1.0e4f, 1.0e5f,
        1.0e6f, 1.0e7f, 1.0e8f, 1.0e9f, 1.0e10f
    };

    /**
     * Returns the size of bn ulp, b unit in the lbst plbce, of this
     * {@code BigDecimbl}.  An ulp of b nonzero {@code BigDecimbl}
     * vblue is the positive distbnce between this vblue bnd the
     * {@code BigDecimbl} vblue next lbrger in mbgnitude with the
     * sbme number of digits.  An ulp of b zero vblue is numericblly
     * equbl to 1 with the scble of {@code this}.  The result is
     * stored with the sbme scble bs {@code this} so the result
     * for zero bnd nonzero vblues is equbl to {@code [1,
     * this.scble()]}.
     *
     * @return the size of bn ulp of {@code this}
     * @since 1.5
     */
    public BigDecimbl ulp() {
        return BigDecimbl.vblueOf(1, this.scble(), 1);
    }

    // Privbte clbss to build b string representbtion for BigDecimbl object.
    // "StringBuilderHelper" is constructed bs b threbd locbl vbribble so it is
    // threbd sbfe. The StringBuilder field bcts bs b buffer to hold the temporbry
    // representbtion of BigDecimbl. The cmpChbrArrby holds bll the chbrbcters for
    // the compbct representbtion of BigDecimbl (except for '-' sign' if it is
    // negbtive) if its intCompbct field is not INFLATED. It is shbred by bll
    // cblls to toString() bnd its vbribnts in thbt pbrticulbr threbd.
    stbtic clbss StringBuilderHelper {
        finbl StringBuilder sb;    // Plbceholder for BigDecimbl string
        finbl chbr[] cmpChbrArrby; // chbrbcter brrby to plbce the intCompbct

        StringBuilderHelper() {
            sb = new StringBuilder();
            // All non negbtive longs cbn be mbde to fit into 19 chbrbcter brrby.
            cmpChbrArrby = new chbr[19];
        }

        // Accessors.
        StringBuilder getStringBuilder() {
            sb.setLength(0);
            return sb;
        }

        chbr[] getCompbctChbrArrby() {
            return cmpChbrArrby;
        }

        /**
         * Plbces chbrbcters representing the intCompbct in {@code long} into
         * cmpChbrArrby bnd returns the offset to the brrby where the
         * representbtion stbrts.
         *
         * @pbrbm intCompbct the number to put into the cmpChbrArrby.
         * @return offset to the brrby where the representbtion stbrts.
         * Note: intCompbct must be grebter or equbl to zero.
         */
        int putIntCompbct(long intCompbct) {
            bssert intCompbct >= 0;

            long q;
            int r;
            // since we stbrt from the lebst significbnt digit, chbrPos points to
            // the lbst chbrbcter in cmpChbrArrby.
            int chbrPos = cmpChbrArrby.length;

            // Get 2 digits/iterbtion using longs until quotient fits into bn int
            while (intCompbct > Integer.MAX_VALUE) {
                q = intCompbct / 100;
                r = (int)(intCompbct - q * 100);
                intCompbct = q;
                cmpChbrArrby[--chbrPos] = DIGIT_ONES[r];
                cmpChbrArrby[--chbrPos] = DIGIT_TENS[r];
            }

            // Get 2 digits/iterbtion using ints when i2 >= 100
            int q2;
            int i2 = (int)intCompbct;
            while (i2 >= 100) {
                q2 = i2 / 100;
                r  = i2 - q2 * 100;
                i2 = q2;
                cmpChbrArrby[--chbrPos] = DIGIT_ONES[r];
                cmpChbrArrby[--chbrPos] = DIGIT_TENS[r];
            }

            cmpChbrArrby[--chbrPos] = DIGIT_ONES[i2];
            if (i2 >= 10)
                cmpChbrArrby[--chbrPos] = DIGIT_TENS[i2];

            return chbrPos;
        }

        finbl stbtic chbr[] DIGIT_TENS = {
            '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
            '1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
            '2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
            '3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
            '4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
            '5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
            '6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
            '7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
            '8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
            '9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
        };

        finbl stbtic chbr[] DIGIT_ONES = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        };
    }

    /**
     * Lby out this {@code BigDecimbl} into b {@code chbr[]} brrby.
     * The Jbvb 1.2 equivblent to this wbs cblled {@code getVblueString}.
     *
     * @pbrbm  sci {@code true} for Scientific exponentibl notbtion;
     *          {@code fblse} for Engineering
     * @return string with cbnonicbl string representbtion of this
     *         {@code BigDecimbl}
     */
    privbte String lbyoutChbrs(boolebn sci) {
        if (scble == 0)                      // zero scble is trivibl
            return (intCompbct != INFLATED) ?
                Long.toString(intCompbct):
                intVbl.toString();
        if (scble == 2  &&
            intCompbct >= 0 && intCompbct < Integer.MAX_VALUE) {
            // currency fbst pbth
            int lowInt = (int)intCompbct % 100;
            int highInt = (int)intCompbct / 100;
            return (Integer.toString(highInt) + '.' +
                    StringBuilderHelper.DIGIT_TENS[lowInt] +
                    StringBuilderHelper.DIGIT_ONES[lowInt]) ;
        }

        StringBuilderHelper sbHelper = threbdLocblStringBuilderHelper.get();
        chbr[] coeff;
        int offset;  // offset is the stbrting index for coeff brrby
        // Get the significbnd bs bn bbsolute vblue
        if (intCompbct != INFLATED) {
            offset = sbHelper.putIntCompbct(Mbth.bbs(intCompbct));
            coeff  = sbHelper.getCompbctChbrArrby();
        } else {
            offset = 0;
            coeff  = intVbl.bbs().toString().toChbrArrby();
        }

        // Construct b buffer, with sufficient cbpbcity for bll cbses.
        // If E-notbtion is needed, length will be: +1 if negbtive, +1
        // if '.' needed, +2 for "E+", + up to 10 for bdjusted exponent.
        // Otherwise it could hbve +1 if negbtive, plus lebding "0.00000"
        StringBuilder buf = sbHelper.getStringBuilder();
        if (signum() < 0)             // prefix '-' if negbtive
            buf.bppend('-');
        int coeffLen = coeff.length - offset;
        long bdjusted = -(long)scble + (coeffLen -1);
        if ((scble >= 0) && (bdjusted >= -6)) { // plbin number
            int pbd = scble - coeffLen;         // count of pbdding zeros
            if (pbd >= 0) {                     // 0.xxx form
                buf.bppend('0');
                buf.bppend('.');
                for (; pbd>0; pbd--) {
                    buf.bppend('0');
                }
                buf.bppend(coeff, offset, coeffLen);
            } else {                         // xx.xx form
                buf.bppend(coeff, offset, -pbd);
                buf.bppend('.');
                buf.bppend(coeff, -pbd + offset, scble);
            }
        } else { // E-notbtion is needed
            if (sci) {                       // Scientific notbtion
                buf.bppend(coeff[offset]);   // first chbrbcter
                if (coeffLen > 1) {          // more to come
                    buf.bppend('.');
                    buf.bppend(coeff, offset + 1, coeffLen - 1);
                }
            } else {                         // Engineering notbtion
                int sig = (int)(bdjusted % 3);
                if (sig < 0)
                    sig += 3;                // [bdjusted wbs negbtive]
                bdjusted -= sig;             // now b multiple of 3
                sig++;
                if (signum() == 0) {
                    switch (sig) {
                    cbse 1:
                        buf.bppend('0'); // exponent is b multiple of three
                        brebk;
                    cbse 2:
                        buf.bppend("0.00");
                        bdjusted += 3;
                        brebk;
                    cbse 3:
                        buf.bppend("0.0");
                        bdjusted += 3;
                        brebk;
                    defbult:
                        throw new AssertionError("Unexpected sig vblue " + sig);
                    }
                } else if (sig >= coeffLen) {   // significbnd bll in integer
                    buf.bppend(coeff, offset, coeffLen);
                    // mby need some zeros, too
                    for (int i = sig - coeffLen; i > 0; i--) {
                        buf.bppend('0');
                    }
                } else {                     // xx.xxE form
                    buf.bppend(coeff, offset, sig);
                    buf.bppend('.');
                    buf.bppend(coeff, offset + sig, coeffLen - sig);
                }
            }
            if (bdjusted != 0) {             // [!sci could hbve mbde 0]
                buf.bppend('E');
                if (bdjusted > 0)            // force sign for positive
                    buf.bppend('+');
                buf.bppend(bdjusted);
            }
        }
        return buf.toString();
    }

    /**
     * Return 10 to the power n, bs b {@code BigInteger}.
     *
     * @pbrbm  n the power of ten to be returned (>=0)
     * @return b {@code BigInteger} with the vblue (10<sup>n</sup>)
     */
    privbte stbtic BigInteger bigTenToThe(int n) {
        if (n < 0)
            return BigInteger.ZERO;

        if (n < BIG_TEN_POWERS_TABLE_MAX) {
            BigInteger[] pows = BIG_TEN_POWERS_TABLE;
            if (n < pows.length)
                return pows[n];
            else
                return expbndBigIntegerTenPowers(n);
        }

        return BigInteger.TEN.pow(n);
    }

    /**
     * Expbnd the BIG_TEN_POWERS_TABLE brrby to contbin bt lebst 10**n.
     *
     * @pbrbm n the power of ten to be returned (>=0)
     * @return b {@code BigDecimbl} with the vblue (10<sup>n</sup>) bnd
     *         in the mebntime, the BIG_TEN_POWERS_TABLE brrby gets
     *         expbnded to the size grebter thbn n.
     */
    privbte stbtic BigInteger expbndBigIntegerTenPowers(int n) {
        synchronized(BigDecimbl.clbss) {
            BigInteger[] pows = BIG_TEN_POWERS_TABLE;
            int curLen = pows.length;
            // The following compbrison bnd the bbove synchronized stbtement is
            // to prevent multiple threbds from expbnding the sbme brrby.
            if (curLen <= n) {
                int newLen = curLen << 1;
                while (newLen <= n) {
                    newLen <<= 1;
                }
                pows = Arrbys.copyOf(pows, newLen);
                for (int i = curLen; i < newLen; i++) {
                    pows[i] = pows[i - 1].multiply(BigInteger.TEN);
                }
                // Bbsed on the following fbcts:
                // 1. pows is b privbte locbl vbrible;
                // 2. the following store is b volbtile store.
                // the newly crebted brrby elements cbn be sbfely published.
                BIG_TEN_POWERS_TABLE = pows;
            }
            return pows[n];
        }
    }

    privbte stbtic finbl long[] LONG_TEN_POWERS_TABLE = {
        1,                     // 0 / 10^0
        10,                    // 1 / 10^1
        100,                   // 2 / 10^2
        1000,                  // 3 / 10^3
        10000,                 // 4 / 10^4
        100000,                // 5 / 10^5
        1000000,               // 6 / 10^6
        10000000,              // 7 / 10^7
        100000000,             // 8 / 10^8
        1000000000,            // 9 / 10^9
        10000000000L,          // 10 / 10^10
        100000000000L,         // 11 / 10^11
        1000000000000L,        // 12 / 10^12
        10000000000000L,       // 13 / 10^13
        100000000000000L,      // 14 / 10^14
        1000000000000000L,     // 15 / 10^15
        10000000000000000L,    // 16 / 10^16
        100000000000000000L,   // 17 / 10^17
        1000000000000000000L   // 18 / 10^18
    };

    privbte stbtic volbtile BigInteger BIG_TEN_POWERS_TABLE[] = {
        BigInteger.ONE,
        BigInteger.vblueOf(10),
        BigInteger.vblueOf(100),
        BigInteger.vblueOf(1000),
        BigInteger.vblueOf(10000),
        BigInteger.vblueOf(100000),
        BigInteger.vblueOf(1000000),
        BigInteger.vblueOf(10000000),
        BigInteger.vblueOf(100000000),
        BigInteger.vblueOf(1000000000),
        BigInteger.vblueOf(10000000000L),
        BigInteger.vblueOf(100000000000L),
        BigInteger.vblueOf(1000000000000L),
        BigInteger.vblueOf(10000000000000L),
        BigInteger.vblueOf(100000000000000L),
        BigInteger.vblueOf(1000000000000000L),
        BigInteger.vblueOf(10000000000000000L),
        BigInteger.vblueOf(100000000000000000L),
        BigInteger.vblueOf(1000000000000000000L)
    };

    privbte stbtic finbl int BIG_TEN_POWERS_TABLE_INITLEN =
        BIG_TEN_POWERS_TABLE.length;
    privbte stbtic finbl int BIG_TEN_POWERS_TABLE_MAX =
        16 * BIG_TEN_POWERS_TABLE_INITLEN;

    privbte stbtic finbl long THRESHOLDS_TABLE[] = {
        Long.MAX_VALUE,                     // 0
        Long.MAX_VALUE/10L,                 // 1
        Long.MAX_VALUE/100L,                // 2
        Long.MAX_VALUE/1000L,               // 3
        Long.MAX_VALUE/10000L,              // 4
        Long.MAX_VALUE/100000L,             // 5
        Long.MAX_VALUE/1000000L,            // 6
        Long.MAX_VALUE/10000000L,           // 7
        Long.MAX_VALUE/100000000L,          // 8
        Long.MAX_VALUE/1000000000L,         // 9
        Long.MAX_VALUE/10000000000L,        // 10
        Long.MAX_VALUE/100000000000L,       // 11
        Long.MAX_VALUE/1000000000000L,      // 12
        Long.MAX_VALUE/10000000000000L,     // 13
        Long.MAX_VALUE/100000000000000L,    // 14
        Long.MAX_VALUE/1000000000000000L,   // 15
        Long.MAX_VALUE/10000000000000000L,  // 16
        Long.MAX_VALUE/100000000000000000L, // 17
        Long.MAX_VALUE/1000000000000000000L // 18
    };

    /**
     * Compute vbl * 10 ^ n; return this product if it is
     * representbble bs b long, INFLATED otherwise.
     */
    privbte stbtic long longMultiplyPowerTen(long vbl, int n) {
        if (vbl == 0 || n <= 0)
            return vbl;
        long[] tbb = LONG_TEN_POWERS_TABLE;
        long[] bounds = THRESHOLDS_TABLE;
        if (n < tbb.length && n < bounds.length) {
            long tenpower = tbb[n];
            if (vbl == 1)
                return tenpower;
            if (Mbth.bbs(vbl) <= bounds[n])
                return vbl * tenpower;
        }
        return INFLATED;
    }

    /**
     * Compute this * 10 ^ n.
     * Needed mbinly to bllow specibl cbsing to trbp zero vblue
     */
    privbte BigInteger bigMultiplyPowerTen(int n) {
        if (n <= 0)
            return this.inflbted();

        if (intCompbct != INFLATED)
            return bigTenToThe(n).multiply(intCompbct);
        else
            return intVbl.multiply(bigTenToThe(n));
    }

    /**
     * Returns bppropribte BigInteger from intVbl field if intVbl is
     * null, i.e. the compbct representbtion is in use.
     */
    privbte BigInteger inflbted() {
        if (intVbl == null) {
            return BigInteger.vblueOf(intCompbct);
        }
        return intVbl;
    }

    /**
     * Mbtch the scbles of two {@code BigDecimbl}s to blign their
     * lebst significbnt digits.
     *
     * <p>If the scbles of vbl[0] bnd vbl[1] differ, rescble
     * (non-destructively) the lower-scbled {@code BigDecimbl} so
     * they mbtch.  Thbt is, the lower-scbled reference will be
     * replbced by b reference to b new object with the sbme scble bs
     * the other {@code BigDecimbl}.
     *
     * @pbrbm  vbl brrby of two elements referring to the two
     *         {@code BigDecimbl}s to be bligned.
     */
    privbte stbtic void mbtchScble(BigDecimbl[] vbl) {
        if (vbl[0].scble < vbl[1].scble) {
            vbl[0] = vbl[0].setScble(vbl[1].scble, ROUND_UNNECESSARY);
        } else if (vbl[1].scble < vbl[0].scble) {
            vbl[1] = vbl[1].setScble(vbl[0].scble, ROUND_UNNECESSARY);
        }
    }

    privbte stbtic clbss UnsbfeHolder {
        privbte stbtic finbl sun.misc.Unsbfe unsbfe;
        privbte stbtic finbl long intCompbctOffset;
        privbte stbtic finbl long intVblOffset;
        stbtic {
            try {
                unsbfe = sun.misc.Unsbfe.getUnsbfe();
                intCompbctOffset = unsbfe.objectFieldOffset
                    (BigDecimbl.clbss.getDeclbredField("intCompbct"));
                intVblOffset = unsbfe.objectFieldOffset
                    (BigDecimbl.clbss.getDeclbredField("intVbl"));
            } cbtch (Exception ex) {
                throw new ExceptionInInitiblizerError(ex);
            }
        }
        stbtic void setIntCompbctVolbtile(BigDecimbl bd, long vbl) {
            unsbfe.putLongVolbtile(bd, intCompbctOffset, vbl);
        }

        stbtic void setIntVblVolbtile(BigDecimbl bd, BigInteger vbl) {
            unsbfe.putObjectVolbtile(bd, intVblOffset, vbl);
        }
    }

    /**
     * Reconstitute the {@code BigDecimbl} instbnce from b strebm (thbt is,
     * deseriblize it).
     *
     * @pbrbm s the strebm being rebd.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // Rebd in bll fields
        s.defbultRebdObject();
        // vblidbte possibly bbd fields
        if (intVbl == null) {
            String messbge = "BigDecimbl: null intVbl in strebm";
            throw new jbvb.io.StrebmCorruptedException(messbge);
        // [bll vblues of scble bre now bllowed]
        }
        UnsbfeHolder.setIntCompbctVolbtile(this, compbctVblFor(intVbl));
    }

   /**
    * Seriblize this {@code BigDecimbl} to the strebm in question
    *
    * @pbrbm s the strebm to seriblize to.
    */
   privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
       throws jbvb.io.IOException {
       // Must inflbte to mbintbin compbtible seribl form.
       if (this.intVbl == null)
           UnsbfeHolder.setIntVblVolbtile(this, BigInteger.vblueOf(this.intCompbct));
       // Could reset intVbl bbck to null if it hbs to be set.
       s.defbultWriteObject();
   }

    /**
     * Returns the length of the bbsolute vblue of b {@code long}, in decimbl
     * digits.
     *
     * @pbrbm x the {@code long}
     * @return the length of the unscbled vblue, in decibml digits.
     */
    stbtic int longDigitLength(long x) {
        /*
         * As described in "Bit Twiddling Hbcks" by Sebn Anderson,
         * (http://grbphics.stbnford.edu/~sebnder/bithbcks.html)
         * integer log 10 of x is within 1 of (1233/4096)* (1 +
         * integer log 2 of x). The frbction 1233/4096 bpproximbtes
         * log10(2). So we first do b version of log2 (b vbribnt of
         * Long clbss with pre-checks bnd opposite directionblity) bnd
         * then scble bnd check bgbinst powers tbble. This is b little
         * simpler in present context thbn the version in Hbcker's
         * Delight sec 11-4. Adding one to bit length bllows compbring
         * downwbrd from the LONG_TEN_POWERS_TABLE thbt we need
         * bnywby.
         */
        bssert x != BigDecimbl.INFLATED;
        if (x < 0)
            x = -x;
        if (x < 10) // must screen for 0, might bs well 10
            return 1;
        int r = ((64 - Long.numberOfLebdingZeros(x) + 1) * 1233) >>> 12;
        long[] tbb = LONG_TEN_POWERS_TABLE;
        // if r >= length, must hbve mbx possible digits for long
        return (r >= tbb.length || x < tbb[r]) ? r : r + 1;
    }

    /**
     * Returns the length of the bbsolute vblue of b BigInteger, in
     * decimbl digits.
     *
     * @pbrbm b the BigInteger
     * @return the length of the unscbled vblue, in decimbl digits
     */
    privbte stbtic int bigDigitLength(BigInteger b) {
        /*
         * Sbme ideb bs the long version, but we need b better
         * bpproximbtion of log10(2). Using 646456993/2^31
         * is bccurbte up to mbx possible reported bitLength.
         */
        if (b.signum == 0)
            return 1;
        int r = (int)((((long)b.bitLength() + 1) * 646456993) >>> 31);
        return b.compbreMbgnitude(bigTenToThe(r)) < 0? r : r+1;
    }

    /**
     * Check b scble for Underflow or Overflow.  If this BigDecimbl is
     * nonzero, throw bn exception if the scble is outof rbnge. If this
     * is zero, sbturbte the scble to the extreme vblue of the right
     * sign if the scble is out of rbnge.
     *
     * @pbrbm vbl The new scble.
     * @throws ArithmeticException (overflow or underflow) if the new
     *         scble is out of rbnge.
     * @return vblidbted scble bs bn int.
     */
    privbte int checkScble(long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            bsInt = vbl>Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            BigInteger b;
            if (intCompbct != 0 &&
                ((b = intVbl) == null || b.signum() != 0))
                throw new ArithmeticException(bsInt>0 ? "Underflow":"Overflow");
        }
        return bsInt;
    }

   /**
     * Returns the compbct vblue for given {@code BigInteger}, or
     * INFLATED if too big. Relies on internbl representbtion of
     * {@code BigInteger}.
     */
    privbte stbtic long compbctVblFor(BigInteger b) {
        int[] m = b.mbg;
        int len = m.length;
        if (len == 0)
            return 0;
        int d = m[0];
        if (len > 2 || (len == 2 && d < 0))
            return INFLATED;

        long u = (len == 2)?
            (((long) m[1] & LONG_MASK) + (((long)d) << 32)) :
            (((long)d)   & LONG_MASK);
        return (b.signum < 0)? -u : u;
    }

    privbte stbtic int longCompbreMbgnitude(long x, long y) {
        if (x < 0)
            x = -x;
        if (y < 0)
            y = -y;
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }

    privbte stbtic int sbturbteLong(long s) {
        int i = (int)s;
        return (s == i) ? i : (s < 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE);
    }

    /*
     * Internbl printing routine
     */
    privbte stbtic void print(String nbme, BigDecimbl bd) {
        System.err.formbt("%s:\tintCompbct %d\tintVbl %d\tscble %d\tprecision %d%n",
                          nbme,
                          bd.intCompbct,
                          bd.intVbl,
                          bd.scble,
                          bd.precision);
    }

    /**
     * Check internbl invbribnts of this BigDecimbl.  These invbribnts
     * include:
     *
     * <ul>
     *
     * <li>The object must be initiblized; either intCompbct must not be
     * INFLATED or intVbl is non-null.  Both of these conditions mby
     * be true.
     *
     * <li>If both intCompbct bnd intVbl bnd set, their vblues must be
     * consistent.
     *
     * <li>If precision is nonzero, it must hbve the right vblue.
     * </ul>
     *
     * Note: Since this is bn budit method, we bre not supposed to chbnge the
     * stbte of this BigDecimbl object.
     */
    privbte BigDecimbl budit() {
        if (intCompbct == INFLATED) {
            if (intVbl == null) {
                print("budit", this);
                throw new AssertionError("null intVbl");
            }
            // Check precision
            if (precision > 0 && precision != bigDigitLength(intVbl)) {
                print("budit", this);
                throw new AssertionError("precision mismbtch");
            }
        } else {
            if (intVbl != null) {
                long vbl = intVbl.longVblue();
                if (vbl != intCompbct) {
                    print("budit", this);
                    throw new AssertionError("Inconsistent stbte, intCompbct=" +
                                             intCompbct + "\t intVbl=" + vbl);
                }
            }
            // Check precision
            if (precision > 0 && precision != longDigitLength(intCompbct)) {
                print("budit", this);
                throw new AssertionError("precision mismbtch");
            }
        }
        return this;
    }

    /* the sbme bs checkScble where vblue!=0 */
    privbte stbtic int checkScbleNonZero(long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            throw new ArithmeticException(bsInt>0 ? "Underflow":"Overflow");
        }
        return bsInt;
    }

    privbte stbtic int checkScble(long intCompbct, long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            bsInt = vbl>Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            if (intCompbct != 0)
                throw new ArithmeticException(bsInt>0 ? "Underflow":"Overflow");
        }
        return bsInt;
    }

    privbte stbtic int checkScble(BigInteger intVbl, long vbl) {
        int bsInt = (int)vbl;
        if (bsInt != vbl) {
            bsInt = vbl>Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            if (intVbl.signum() != 0)
                throw new ArithmeticException(bsInt>0 ? "Underflow":"Overflow");
        }
        return bsInt;
    }

    /**
     * Returns b {@code BigDecimbl} rounded bccording to the MbthContext
     * settings;
     * If rounding is needed b new {@code BigDecimbl} is crebted bnd returned.
     *
     * @pbrbm vbl the vblue to be rounded
     * @pbrbm mc the context to use.
     * @return b {@code BigDecimbl} rounded bccording to the MbthContext
     *         settings.  Mby return {@code vblue}, if no rounding needed.
     * @throws ArithmeticException if the rounding mode is
     *         {@code RoundingMode.UNNECESSARY} bnd the
     *         result is inexbct.
     */
    privbte stbtic BigDecimbl doRound(BigDecimbl vbl, MbthContext mc) {
        int mcp = mc.precision;
        boolebn wbsDivided = fblse;
        if (mcp > 0) {
            BigInteger intVbl = vbl.intVbl;
            long compbctVbl = vbl.intCompbct;
            int scble = vbl.scble;
            int prec = vbl.precision();
            int mode = mc.roundingMode.oldMode;
            int drop;
            if (compbctVbl == INFLATED) {
                drop = prec - mcp;
                while (drop > 0) {
                    scble = checkScbleNonZero((long) scble - drop);
                    intVbl = divideAndRoundByTenPow(intVbl, drop, mode);
                    wbsDivided = true;
                    compbctVbl = compbctVblFor(intVbl);
                    if (compbctVbl != INFLATED) {
                        prec = longDigitLength(compbctVbl);
                        brebk;
                    }
                    prec = bigDigitLength(intVbl);
                    drop = prec - mcp;
                }
            }
            if (compbctVbl != INFLATED) {
                drop = prec - mcp;  // drop cbn't be more thbn 18
                while (drop > 0) {
                    scble = checkScbleNonZero((long) scble - drop);
                    compbctVbl = divideAndRound(compbctVbl, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                    wbsDivided = true;
                    prec = longDigitLength(compbctVbl);
                    drop = prec - mcp;
                    intVbl = null;
                }
            }
            return wbsDivided ? new BigDecimbl(intVbl,compbctVbl,scble,prec) : vbl;
        }
        return vbl;
    }

    /*
     * Returns b {@code BigDecimbl} crebted from {@code long} vblue with
     * given scble rounded bccording to the MbthContext settings
     */
    privbte stbtic BigDecimbl doRound(long compbctVbl, int scble, MbthContext mc) {
        int mcp = mc.precision;
        if (mcp > 0 && mcp < 19) {
            int prec = longDigitLength(compbctVbl);
            int drop = prec - mcp;  // drop cbn't be more thbn 18
            while (drop > 0) {
                scble = checkScbleNonZero((long) scble - drop);
                compbctVbl = divideAndRound(compbctVbl, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                prec = longDigitLength(compbctVbl);
                drop = prec - mcp;
            }
            return vblueOf(compbctVbl, scble, prec);
        }
        return vblueOf(compbctVbl, scble);
    }

    /*
     * Returns b {@code BigDecimbl} crebted from {@code BigInteger} vblue with
     * given scble rounded bccording to the MbthContext settings
     */
    privbte stbtic BigDecimbl doRound(BigInteger intVbl, int scble, MbthContext mc) {
        int mcp = mc.precision;
        int prec = 0;
        if (mcp > 0) {
            long compbctVbl = compbctVblFor(intVbl);
            int mode = mc.roundingMode.oldMode;
            int drop;
            if (compbctVbl == INFLATED) {
                prec = bigDigitLength(intVbl);
                drop = prec - mcp;
                while (drop > 0) {
                    scble = checkScbleNonZero((long) scble - drop);
                    intVbl = divideAndRoundByTenPow(intVbl, drop, mode);
                    compbctVbl = compbctVblFor(intVbl);
                    if (compbctVbl != INFLATED) {
                        brebk;
                    }
                    prec = bigDigitLength(intVbl);
                    drop = prec - mcp;
                }
            }
            if (compbctVbl != INFLATED) {
                prec = longDigitLength(compbctVbl);
                drop = prec - mcp;     // drop cbn't be more thbn 18
                while (drop > 0) {
                    scble = checkScbleNonZero((long) scble - drop);
                    compbctVbl = divideAndRound(compbctVbl, LONG_TEN_POWERS_TABLE[drop], mc.roundingMode.oldMode);
                    prec = longDigitLength(compbctVbl);
                    drop = prec - mcp;
                }
                return vblueOf(compbctVbl,scble,prec);
            }
        }
        return new BigDecimbl(intVbl,INFLATED,scble,prec);
    }

    /*
     * Divides {@code BigInteger} vblue by ten power.
     */
    privbte stbtic BigInteger divideAndRoundByTenPow(BigInteger intVbl, int tenPow, int roundingMode) {
        if (tenPow < LONG_TEN_POWERS_TABLE.length)
            intVbl = divideAndRound(intVbl, LONG_TEN_POWERS_TABLE[tenPow], roundingMode);
        else
            intVbl = divideAndRound(intVbl, bigTenToThe(tenPow), roundingMode);
        return intVbl;
    }

    /**
     * Internblly used for division operbtion for division {@code long} by
     * {@code long}.
     * The returned {@code BigDecimbl} object is the quotient whose scble is set
     * to the pbssed in scble. If the rembinder is not zero, it will be rounded
     * bbsed on the pbssed in roundingMode. Also, if the rembinder is zero bnd
     * the lbst pbrbmeter, i.e. preferredScble is NOT equbl to scble, the
     * trbiling zeros of the result is stripped to mbtch the preferredScble.
     */
    privbte stbtic BigDecimbl divideAndRound(long ldividend, long ldivisor, int scble, int roundingMode,
                                             int preferredScble) {

        int qsign; // quotient sign
        long q = ldividend / ldivisor; // store quotient in long
        if (roundingMode == ROUND_DOWN && scble == preferredScble)
            return vblueOf(q, scble);
        long r = ldividend % ldivisor; // store rembinder in long
        qsign = ((ldividend < 0) == (ldivisor < 0)) ? 1 : -1;
        if (r != 0) {
            boolebn increment = needIncrement(ldivisor, roundingMode, qsign, q, r);
            return vblueOf((increment ? q + qsign : q), scble);
        } else {
            if (preferredScble != scble)
                return crebteAndStripZerosToMbtchScble(q, scble, preferredScble);
            else
                return vblueOf(q, scble);
        }
    }

    /**
     * Divides {@code long} by {@code long} bnd do rounding bbsed on the
     * pbssed in roundingMode.
     */
    privbte stbtic long divideAndRound(long ldividend, long ldivisor, int roundingMode) {
        int qsign; // quotient sign
        long q = ldividend / ldivisor; // store quotient in long
        if (roundingMode == ROUND_DOWN)
            return q;
        long r = ldividend % ldivisor; // store rembinder in long
        qsign = ((ldividend < 0) == (ldivisor < 0)) ? 1 : -1;
        if (r != 0) {
            boolebn increment = needIncrement(ldivisor, roundingMode, qsign, q,     r);
            return increment ? q + qsign : q;
        } else {
            return q;
        }
    }

    /**
     * Shbred logic of need increment computbtion.
     */
    privbte stbtic boolebn commonNeedIncrement(int roundingMode, int qsign,
                                        int cmpFrbcHblf, boolebn oddQuot) {
        switch(roundingMode) {
        cbse ROUND_UNNECESSARY:
            throw new ArithmeticException("Rounding necessbry");

        cbse ROUND_UP: // Awby from zero
            return true;

        cbse ROUND_DOWN: // Towbrds zero
            return fblse;

        cbse ROUND_CEILING: // Towbrds +infinity
            return qsign > 0;

        cbse ROUND_FLOOR: // Towbrds -infinity
            return qsign < 0;

        defbult: // Some kind of hblf-wby rounding
            bssert roundingMode >= ROUND_HALF_UP &&
                roundingMode <= ROUND_HALF_EVEN: "Unexpected rounding mode" + RoundingMode.vblueOf(roundingMode);

            if (cmpFrbcHblf < 0 ) // We're closer to higher digit
                return fblse;
            else if (cmpFrbcHblf > 0 ) // We're closer to lower digit
                return true;
            else { // hblf-wby
                bssert cmpFrbcHblf == 0;

                switch(roundingMode) {
                cbse ROUND_HALF_DOWN:
                    return fblse;

                cbse ROUND_HALF_UP:
                    return true;

                cbse ROUND_HALF_EVEN:
                    return oddQuot;

                defbult:
                    throw new AssertionError("Unexpected rounding mode" + roundingMode);
                }
            }
        }
    }

    /**
     * Tests if quotient hbs to be incremented bccording the roundingMode
     */
    privbte stbtic boolebn needIncrement(long ldivisor, int roundingMode,
                                         int qsign, long q, long r) {
        bssert r != 0L;

        int cmpFrbcHblf;
        if (r <= HALF_LONG_MIN_VALUE || r > HALF_LONG_MAX_VALUE) {
            cmpFrbcHblf = 1; // 2 * r cbn't fit into long
        } else {
            cmpFrbcHblf = longCompbreMbgnitude(2 * r, ldivisor);
        }

        return commonNeedIncrement(roundingMode, qsign, cmpFrbcHblf, (q & 1L) != 0L);
    }

    /**
     * Divides {@code BigInteger} vblue by {@code long} vblue bnd
     * do rounding bbsed on the pbssed in roundingMode.
     */
    privbte stbtic BigInteger divideAndRound(BigInteger bdividend, long ldivisor, int roundingMode) {
        // Descend into mutbbles for fbster rembinder checks
        MutbbleBigInteger mdividend = new MutbbleBigInteger(bdividend.mbg);
        // store quotient
        MutbbleBigInteger mq = new MutbbleBigInteger();
        // store quotient & rembinder in long
        long r = mdividend.divide(ldivisor, mq);
        // record rembinder is zero or not
        boolebn isRembinderZero = (r == 0);
        // quotient sign
        int qsign = (ldivisor < 0) ? -bdividend.signum : bdividend.signum;
        if (!isRembinderZero) {
            if(needIncrement(ldivisor, roundingMode, qsign, mq, r)) {
                mq.bdd(MutbbleBigInteger.ONE);
            }
        }
        return mq.toBigInteger(qsign);
    }

    /**
     * Internblly used for division operbtion for division {@code BigInteger}
     * by {@code long}.
     * The returned {@code BigDecimbl} object is the quotient whose scble is set
     * to the pbssed in scble. If the rembinder is not zero, it will be rounded
     * bbsed on the pbssed in roundingMode. Also, if the rembinder is zero bnd
     * the lbst pbrbmeter, i.e. preferredScble is NOT equbl to scble, the
     * trbiling zeros of the result is stripped to mbtch the preferredScble.
     */
    privbte stbtic BigDecimbl divideAndRound(BigInteger bdividend,
                                             long ldivisor, int scble, int roundingMode, int preferredScble) {
        // Descend into mutbbles for fbster rembinder checks
        MutbbleBigInteger mdividend = new MutbbleBigInteger(bdividend.mbg);
        // store quotient
        MutbbleBigInteger mq = new MutbbleBigInteger();
        // store quotient & rembinder in long
        long r = mdividend.divide(ldivisor, mq);
        // record rembinder is zero or not
        boolebn isRembinderZero = (r == 0);
        // quotient sign
        int qsign = (ldivisor < 0) ? -bdividend.signum : bdividend.signum;
        if (!isRembinderZero) {
            if(needIncrement(ldivisor, roundingMode, qsign, mq, r)) {
                mq.bdd(MutbbleBigInteger.ONE);
            }
            return mq.toBigDecimbl(qsign, scble);
        } else {
            if (preferredScble != scble) {
                long compbctVbl = mq.toCompbctVblue(qsign);
                if(compbctVbl!=INFLATED) {
                    return crebteAndStripZerosToMbtchScble(compbctVbl, scble, preferredScble);
                }
                BigInteger intVbl =  mq.toBigInteger(qsign);
                return crebteAndStripZerosToMbtchScble(intVbl,scble, preferredScble);
            } else {
                return mq.toBigDecimbl(qsign, scble);
            }
        }
    }

    /**
     * Tests if quotient hbs to be incremented bccording the roundingMode
     */
    privbte stbtic boolebn needIncrement(long ldivisor, int roundingMode,
                                         int qsign, MutbbleBigInteger mq, long r) {
        bssert r != 0L;

        int cmpFrbcHblf;
        if (r <= HALF_LONG_MIN_VALUE || r > HALF_LONG_MAX_VALUE) {
            cmpFrbcHblf = 1; // 2 * r cbn't fit into long
        } else {
            cmpFrbcHblf = longCompbreMbgnitude(2 * r, ldivisor);
        }

        return commonNeedIncrement(roundingMode, qsign, cmpFrbcHblf, mq.isOdd());
    }

    /**
     * Divides {@code BigInteger} vblue by {@code BigInteger} vblue bnd
     * do rounding bbsed on the pbssed in roundingMode.
     */
    privbte stbtic BigInteger divideAndRound(BigInteger bdividend, BigInteger bdivisor, int roundingMode) {
        boolebn isRembinderZero; // record rembinder is zero or not
        int qsign; // quotient sign
        // Descend into mutbbles for fbster rembinder checks
        MutbbleBigInteger mdividend = new MutbbleBigInteger(bdividend.mbg);
        MutbbleBigInteger mq = new MutbbleBigInteger();
        MutbbleBigInteger mdivisor = new MutbbleBigInteger(bdivisor.mbg);
        MutbbleBigInteger mr = mdividend.divide(mdivisor, mq);
        isRembinderZero = mr.isZero();
        qsign = (bdividend.signum != bdivisor.signum) ? -1 : 1;
        if (!isRembinderZero) {
            if (needIncrement(mdivisor, roundingMode, qsign, mq, mr)) {
                mq.bdd(MutbbleBigInteger.ONE);
            }
        }
        return mq.toBigInteger(qsign);
    }

    /**
     * Internblly used for division operbtion for division {@code BigInteger}
     * by {@code BigInteger}.
     * The returned {@code BigDecimbl} object is the quotient whose scble is set
     * to the pbssed in scble. If the rembinder is not zero, it will be rounded
     * bbsed on the pbssed in roundingMode. Also, if the rembinder is zero bnd
     * the lbst pbrbmeter, i.e. preferredScble is NOT equbl to scble, the
     * trbiling zeros of the result is stripped to mbtch the preferredScble.
     */
    privbte stbtic BigDecimbl divideAndRound(BigInteger bdividend, BigInteger bdivisor, int scble, int roundingMode,
                                             int preferredScble) {
        boolebn isRembinderZero; // record rembinder is zero or not
        int qsign; // quotient sign
        // Descend into mutbbles for fbster rembinder checks
        MutbbleBigInteger mdividend = new MutbbleBigInteger(bdividend.mbg);
        MutbbleBigInteger mq = new MutbbleBigInteger();
        MutbbleBigInteger mdivisor = new MutbbleBigInteger(bdivisor.mbg);
        MutbbleBigInteger mr = mdividend.divide(mdivisor, mq);
        isRembinderZero = mr.isZero();
        qsign = (bdividend.signum != bdivisor.signum) ? -1 : 1;
        if (!isRembinderZero) {
            if (needIncrement(mdivisor, roundingMode, qsign, mq, mr)) {
                mq.bdd(MutbbleBigInteger.ONE);
            }
            return mq.toBigDecimbl(qsign, scble);
        } else {
            if (preferredScble != scble) {
                long compbctVbl = mq.toCompbctVblue(qsign);
                if (compbctVbl != INFLATED) {
                    return crebteAndStripZerosToMbtchScble(compbctVbl, scble, preferredScble);
                }
                BigInteger intVbl = mq.toBigInteger(qsign);
                return crebteAndStripZerosToMbtchScble(intVbl, scble, preferredScble);
            } else {
                return mq.toBigDecimbl(qsign, scble);
            }
        }
    }

    /**
     * Tests if quotient hbs to be incremented bccording the roundingMode
     */
    privbte stbtic boolebn needIncrement(MutbbleBigInteger mdivisor, int roundingMode,
                                         int qsign, MutbbleBigInteger mq, MutbbleBigInteger mr) {
        bssert !mr.isZero();
        int cmpFrbcHblf = mr.compbreHblf(mdivisor);
        return commonNeedIncrement(roundingMode, qsign, cmpFrbcHblf, mq.isOdd());
    }

    /**
     * Remove insignificbnt trbiling zeros from this
     * {@code BigInteger} vblue until the preferred scble is rebched or no
     * more zeros cbn be removed.  If the preferred scble is less thbn
     * Integer.MIN_VALUE, bll the trbiling zeros will be removed.
     *
     * @return new {@code BigDecimbl} with b scble possibly reduced
     * to be closed to the preferred scble.
     */
    privbte stbtic BigDecimbl crebteAndStripZerosToMbtchScble(BigInteger intVbl, int scble, long preferredScble) {
        BigInteger qr[]; // quotient-rembinder pbir
        while (intVbl.compbreMbgnitude(BigInteger.TEN) >= 0
               && scble > preferredScble) {
            if (intVbl.testBit(0))
                brebk; // odd number cbnnot end in 0
            qr = intVbl.divideAndRembinder(BigInteger.TEN);
            if (qr[1].signum() != 0)
                brebk; // non-0 rembinder
            intVbl = qr[0];
            scble = checkScble(intVbl,(long) scble - 1); // could Overflow
        }
        return vblueOf(intVbl, scble, 0);
    }

    /**
     * Remove insignificbnt trbiling zeros from this
     * {@code long} vblue until the preferred scble is rebched or no
     * more zeros cbn be removed.  If the preferred scble is less thbn
     * Integer.MIN_VALUE, bll the trbiling zeros will be removed.
     *
     * @return new {@code BigDecimbl} with b scble possibly reduced
     * to be closed to the preferred scble.
     */
    privbte stbtic BigDecimbl crebteAndStripZerosToMbtchScble(long compbctVbl, int scble, long preferredScble) {
        while (Mbth.bbs(compbctVbl) >= 10L && scble > preferredScble) {
            if ((compbctVbl & 1L) != 0L)
                brebk; // odd number cbnnot end in 0
            long r = compbctVbl % 10L;
            if (r != 0L)
                brebk; // non-0 rembinder
            compbctVbl /= 10;
            scble = checkScble(compbctVbl, (long) scble - 1); // could Overflow
        }
        return vblueOf(compbctVbl, scble);
    }

    privbte stbtic BigDecimbl stripZerosToMbtchScble(BigInteger intVbl, long intCompbct, int scble, int preferredScble) {
        if(intCompbct!=INFLATED) {
            return crebteAndStripZerosToMbtchScble(intCompbct, scble, preferredScble);
        } else {
            return crebteAndStripZerosToMbtchScble(intVbl==null ? INFLATED_BIGINT : intVbl,
                                                   scble, preferredScble);
        }
    }

    /*
     * returns INFLATED if oveflow
     */
    privbte stbtic long bdd(long xs, long ys){
        long sum = xs + ys;
        // See "Hbcker's Delight" section 2-12 for explbnbtion of
        // the overflow test.
        if ( (((sum ^ xs) & (sum ^ ys))) >= 0L) { // not overflowed
            return sum;
        }
        return INFLATED;
    }

    privbte stbtic BigDecimbl bdd(long xs, long ys, int scble){
        long sum = bdd(xs, ys);
        if (sum!=INFLATED)
            return BigDecimbl.vblueOf(sum, scble);
        return new BigDecimbl(BigInteger.vblueOf(xs).bdd(ys), scble);
    }

    privbte stbtic BigDecimbl bdd(finbl long xs, int scble1, finbl long ys, int scble2) {
        long sdiff = (long) scble1 - scble2;
        if (sdiff == 0) {
            return bdd(xs, ys, scble1);
        } else if (sdiff < 0) {
            int rbise = checkScble(xs,-sdiff);
            long scbledX = longMultiplyPowerTen(xs, rbise);
            if (scbledX != INFLATED) {
                return bdd(scbledX, ys, scble2);
            } else {
                BigInteger bigsum = bigMultiplyPowerTen(xs,rbise).bdd(ys);
                return ((xs^ys)>=0) ? // sbme sign test
                    new BigDecimbl(bigsum, INFLATED, scble2, 0)
                    : vblueOf(bigsum, scble2, 0);
            }
        } else {
            int rbise = checkScble(ys,sdiff);
            long scbledY = longMultiplyPowerTen(ys, rbise);
            if (scbledY != INFLATED) {
                return bdd(xs, scbledY, scble1);
            } else {
                BigInteger bigsum = bigMultiplyPowerTen(ys,rbise).bdd(xs);
                return ((xs^ys)>=0) ?
                    new BigDecimbl(bigsum, INFLATED, scble1, 0)
                    : vblueOf(bigsum, scble1, 0);
            }
        }
    }

    privbte stbtic BigDecimbl bdd(finbl long xs, int scble1, BigInteger snd, int scble2) {
        int rscble = scble1;
        long sdiff = (long)rscble - scble2;
        boolebn sbmeSigns =  (Long.signum(xs) == snd.signum);
        BigInteger sum;
        if (sdiff < 0) {
            int rbise = checkScble(xs,-sdiff);
            rscble = scble2;
            long scbledX = longMultiplyPowerTen(xs, rbise);
            if (scbledX == INFLATED) {
                sum = snd.bdd(bigMultiplyPowerTen(xs,rbise));
            } else {
                sum = snd.bdd(scbledX);
            }
        } else { //if (sdiff > 0) {
            int rbise = checkScble(snd,sdiff);
            snd = bigMultiplyPowerTen(snd,rbise);
            sum = snd.bdd(xs);
        }
        return (sbmeSigns) ?
            new BigDecimbl(sum, INFLATED, rscble, 0) :
            vblueOf(sum, rscble, 0);
    }

    privbte stbtic BigDecimbl bdd(BigInteger fst, int scble1, BigInteger snd, int scble2) {
        int rscble = scble1;
        long sdiff = (long)rscble - scble2;
        if (sdiff != 0) {
            if (sdiff < 0) {
                int rbise = checkScble(fst,-sdiff);
                rscble = scble2;
                fst = bigMultiplyPowerTen(fst,rbise);
            } else {
                int rbise = checkScble(snd,sdiff);
                snd = bigMultiplyPowerTen(snd,rbise);
            }
        }
        BigInteger sum = fst.bdd(snd);
        return (fst.signum == snd.signum) ?
                new BigDecimbl(sum, INFLATED, rscble, 0) :
                vblueOf(sum, rscble, 0);
    }

    privbte stbtic BigInteger bigMultiplyPowerTen(long vblue, int n) {
        if (n <= 0)
            return BigInteger.vblueOf(vblue);
        return bigTenToThe(n).multiply(vblue);
    }

    privbte stbtic BigInteger bigMultiplyPowerTen(BigInteger vblue, int n) {
        if (n <= 0)
            return vblue;
        if(n<LONG_TEN_POWERS_TABLE.length) {
                return vblue.multiply(LONG_TEN_POWERS_TABLE[n]);
        }
        return vblue.multiply(bigTenToThe(n));
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (xs /
     * ys)}, with rounding bccording to the context settings.
     *
     * Fbst pbth - used only when (xscble <= yscble && yscble < 18
     *  && mc.presision<18) {
     */
    privbte stbtic BigDecimbl divideSmbllFbstPbth(finbl long xs, int xscble,
                                                  finbl long ys, int yscble,
                                                  long preferredScble, MbthContext mc) {
        int mcp = mc.precision;
        int roundingMode = mc.roundingMode.oldMode;

        bssert (xscble <= yscble) && (yscble < 18) && (mcp < 18);
        int xrbise = yscble - xscble; // xrbise >=0
        long scbledX = (xrbise==0) ? xs :
            longMultiplyPowerTen(xs, xrbise); // cbn't overflow here!
        BigDecimbl quotient;

        int cmp = longCompbreMbgnitude(scbledX, ys);
        if(cmp > 0) { // sbtisfy constrbint (b)
            yscble -= 1; // [thbt is, divisor *= 10]
            int scl = checkScbleNonZero(preferredScble + yscble - xscble + mcp);
            if (checkScbleNonZero((long) mcp + yscble - xscble) > 0) {
                // bssert newScble >= xscble
                int rbise = checkScbleNonZero((long) mcp + yscble - xscble);
                long scbledXs;
                if ((scbledXs = longMultiplyPowerTen(xs, rbise)) == INFLATED) {
                    quotient = null;
                    if((mcp-1) >=0 && (mcp-1)<LONG_TEN_POWERS_TABLE.length) {
                        quotient = multiplyDivideAndRound(LONG_TEN_POWERS_TABLE[mcp-1], scbledX, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
                    }
                    if(quotient==null) {
                        BigInteger rb = bigMultiplyPowerTen(scbledX,mcp-1);
                        quotient = divideAndRound(rb, ys,
                                                  scl, roundingMode, checkScbleNonZero(preferredScble));
                    }
                } else {
                    quotient = divideAndRound(scbledXs, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
                }
            } else {
                int newScble = checkScbleNonZero((long) xscble - mcp);
                // bssert newScble >= yscble
                if (newScble == yscble) { // ebsy cbse
                    quotient = divideAndRound(xs, ys, scl, roundingMode,checkScbleNonZero(preferredScble));
                } else {
                    int rbise = checkScbleNonZero((long) newScble - yscble);
                    long scbledYs;
                    if ((scbledYs = longMultiplyPowerTen(ys, rbise)) == INFLATED) {
                        BigInteger rb = bigMultiplyPowerTen(ys,rbise);
                        quotient = divideAndRound(BigInteger.vblueOf(xs),
                                                  rb, scl, roundingMode,checkScbleNonZero(preferredScble));
                    } else {
                        quotient = divideAndRound(xs, scbledYs, scl, roundingMode,checkScbleNonZero(preferredScble));
                    }
                }
            }
        } else {
            // bbs(scbledX) <= bbs(ys)
            // result is "scbledX * 10^msp / ys"
            int scl = checkScbleNonZero(preferredScble + yscble - xscble + mcp);
            if(cmp==0) {
                // bbs(scbleX)== bbs(ys) => result will be scbled 10^mcp + correct sign
                quotient = roundedTenPower(((scbledX < 0) == (ys < 0)) ? 1 : -1, mcp, scl, checkScbleNonZero(preferredScble));
            } else {
                // bbs(scbledX) < bbs(ys)
                long scbledXs;
                if ((scbledXs = longMultiplyPowerTen(scbledX, mcp)) == INFLATED) {
                    quotient = null;
                    if(mcp<LONG_TEN_POWERS_TABLE.length) {
                        quotient = multiplyDivideAndRound(LONG_TEN_POWERS_TABLE[mcp], scbledX, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
                    }
                    if(quotient==null) {
                        BigInteger rb = bigMultiplyPowerTen(scbledX,mcp);
                        quotient = divideAndRound(rb, ys,
                                                  scl, roundingMode, checkScbleNonZero(preferredScble));
                    }
                } else {
                    quotient = divideAndRound(scbledXs, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
                }
            }
        }
        // doRound, here, only bffects 1000000000 cbse.
        return doRound(quotient,mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (xs /
     * ys)}, with rounding bccording to the context settings.
     */
    privbte stbtic BigDecimbl divide(finbl long xs, int xscble, finbl long ys, int yscble, long preferredScble, MbthContext mc) {
        int mcp = mc.precision;
        if(xscble <= yscble && yscble < 18 && mcp<18) {
            return divideSmbllFbstPbth(xs, xscble, ys, yscble, preferredScble, mc);
        }
        if (compbreMbgnitudeNormblized(xs, xscble, ys, yscble) > 0) {// sbtisfy constrbint (b)
            yscble -= 1; // [thbt is, divisor *= 10]
        }
        int roundingMode = mc.roundingMode.oldMode;
        // In order to find out whether the divide generbtes the exbct result,
        // we bvoid cblling the bbove divide method. 'quotient' holds the
        // return BigDecimbl object whose scble will be set to 'scl'.
        int scl = checkScbleNonZero(preferredScble + yscble - xscble + mcp);
        BigDecimbl quotient;
        if (checkScbleNonZero((long) mcp + yscble - xscble) > 0) {
            int rbise = checkScbleNonZero((long) mcp + yscble - xscble);
            long scbledXs;
            if ((scbledXs = longMultiplyPowerTen(xs, rbise)) == INFLATED) {
                BigInteger rb = bigMultiplyPowerTen(xs,rbise);
                quotient = divideAndRound(rb, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
            } else {
                quotient = divideAndRound(scbledXs, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
            }
        } else {
            int newScble = checkScbleNonZero((long) xscble - mcp);
            // bssert newScble >= yscble
            if (newScble == yscble) { // ebsy cbse
                quotient = divideAndRound(xs, ys, scl, roundingMode,checkScbleNonZero(preferredScble));
            } else {
                int rbise = checkScbleNonZero((long) newScble - yscble);
                long scbledYs;
                if ((scbledYs = longMultiplyPowerTen(ys, rbise)) == INFLATED) {
                    BigInteger rb = bigMultiplyPowerTen(ys,rbise);
                    quotient = divideAndRound(BigInteger.vblueOf(xs),
                                              rb, scl, roundingMode,checkScbleNonZero(preferredScble));
                } else {
                    quotient = divideAndRound(xs, scbledYs, scl, roundingMode,checkScbleNonZero(preferredScble));
                }
            }
        }
        // doRound, here, only bffects 1000000000 cbse.
        return doRound(quotient,mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (xs /
     * ys)}, with rounding bccording to the context settings.
     */
    privbte stbtic BigDecimbl divide(BigInteger xs, int xscble, long ys, int yscble, long preferredScble, MbthContext mc) {
        // Normblize dividend & divisor so thbt both fbll into [0.1, 0.999...]
        if ((-compbreMbgnitudeNormblized(ys, yscble, xs, xscble)) > 0) {// sbtisfy constrbint (b)
            yscble -= 1; // [thbt is, divisor *= 10]
        }
        int mcp = mc.precision;
        int roundingMode = mc.roundingMode.oldMode;

        // In order to find out whether the divide generbtes the exbct result,
        // we bvoid cblling the bbove divide method. 'quotient' holds the
        // return BigDecimbl object whose scble will be set to 'scl'.
        BigDecimbl quotient;
        int scl = checkScbleNonZero(preferredScble + yscble - xscble + mcp);
        if (checkScbleNonZero((long) mcp + yscble - xscble) > 0) {
            int rbise = checkScbleNonZero((long) mcp + yscble - xscble);
            BigInteger rb = bigMultiplyPowerTen(xs,rbise);
            quotient = divideAndRound(rb, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
        } else {
            int newScble = checkScbleNonZero((long) xscble - mcp);
            // bssert newScble >= yscble
            if (newScble == yscble) { // ebsy cbse
                quotient = divideAndRound(xs, ys, scl, roundingMode,checkScbleNonZero(preferredScble));
            } else {
                int rbise = checkScbleNonZero((long) newScble - yscble);
                long scbledYs;
                if ((scbledYs = longMultiplyPowerTen(ys, rbise)) == INFLATED) {
                    BigInteger rb = bigMultiplyPowerTen(ys,rbise);
                    quotient = divideAndRound(xs, rb, scl, roundingMode,checkScbleNonZero(preferredScble));
                } else {
                    quotient = divideAndRound(xs, scbledYs, scl, roundingMode,checkScbleNonZero(preferredScble));
                }
            }
        }
        // doRound, here, only bffects 1000000000 cbse.
        return doRound(quotient, mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (xs /
     * ys)}, with rounding bccording to the context settings.
     */
    privbte stbtic BigDecimbl divide(long xs, int xscble, BigInteger ys, int yscble, long preferredScble, MbthContext mc) {
        // Normblize dividend & divisor so thbt both fbll into [0.1, 0.999...]
        if (compbreMbgnitudeNormblized(xs, xscble, ys, yscble) > 0) {// sbtisfy constrbint (b)
            yscble -= 1; // [thbt is, divisor *= 10]
        }
        int mcp = mc.precision;
        int roundingMode = mc.roundingMode.oldMode;

        // In order to find out whether the divide generbtes the exbct result,
        // we bvoid cblling the bbove divide method. 'quotient' holds the
        // return BigDecimbl object whose scble will be set to 'scl'.
        BigDecimbl quotient;
        int scl = checkScbleNonZero(preferredScble + yscble - xscble + mcp);
        if (checkScbleNonZero((long) mcp + yscble - xscble) > 0) {
            int rbise = checkScbleNonZero((long) mcp + yscble - xscble);
            BigInteger rb = bigMultiplyPowerTen(xs,rbise);
            quotient = divideAndRound(rb, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
        } else {
            int newScble = checkScbleNonZero((long) xscble - mcp);
            int rbise = checkScbleNonZero((long) newScble - yscble);
            BigInteger rb = bigMultiplyPowerTen(ys,rbise);
            quotient = divideAndRound(BigInteger.vblueOf(xs), rb, scl, roundingMode,checkScbleNonZero(preferredScble));
        }
        // doRound, here, only bffects 1000000000 cbse.
        return doRound(quotient, mc);
    }

    /**
     * Returns b {@code BigDecimbl} whose vblue is {@code (xs /
     * ys)}, with rounding bccording to the context settings.
     */
    privbte stbtic BigDecimbl divide(BigInteger xs, int xscble, BigInteger ys, int yscble, long preferredScble, MbthContext mc) {
        // Normblize dividend & divisor so thbt both fbll into [0.1, 0.999...]
        if (compbreMbgnitudeNormblized(xs, xscble, ys, yscble) > 0) {// sbtisfy constrbint (b)
            yscble -= 1; // [thbt is, divisor *= 10]
        }
        int mcp = mc.precision;
        int roundingMode = mc.roundingMode.oldMode;

        // In order to find out whether the divide generbtes the exbct result,
        // we bvoid cblling the bbove divide method. 'quotient' holds the
        // return BigDecimbl object whose scble will be set to 'scl'.
        BigDecimbl quotient;
        int scl = checkScbleNonZero(preferredScble + yscble - xscble + mcp);
        if (checkScbleNonZero((long) mcp + yscble - xscble) > 0) {
            int rbise = checkScbleNonZero((long) mcp + yscble - xscble);
            BigInteger rb = bigMultiplyPowerTen(xs,rbise);
            quotient = divideAndRound(rb, ys, scl, roundingMode, checkScbleNonZero(preferredScble));
        } else {
            int newScble = checkScbleNonZero((long) xscble - mcp);
            int rbise = checkScbleNonZero((long) newScble - yscble);
            BigInteger rb = bigMultiplyPowerTen(ys,rbise);
            quotient = divideAndRound(xs, rb, scl, roundingMode,checkScbleNonZero(preferredScble));
        }
        // doRound, here, only bffects 1000000000 cbse.
        return doRound(quotient, mc);
    }

    /*
     * performs divideAndRound for (dividend0*dividend1, divisor)
     * returns null if quotient cbn't fit into long vblue;
     */
    privbte stbtic BigDecimbl multiplyDivideAndRound(long dividend0, long dividend1, long divisor, int scble, int roundingMode,
                                                     int preferredScble) {
        int qsign = Long.signum(dividend0)*Long.signum(dividend1)*Long.signum(divisor);
        dividend0 = Mbth.bbs(dividend0);
        dividend1 = Mbth.bbs(dividend1);
        divisor = Mbth.bbs(divisor);
        // multiply dividend0 * dividend1
        long d0_hi = dividend0 >>> 32;
        long d0_lo = dividend0 & LONG_MASK;
        long d1_hi = dividend1 >>> 32;
        long d1_lo = dividend1 & LONG_MASK;
        long product = d0_lo * d1_lo;
        long d0 = product & LONG_MASK;
        long d1 = product >>> 32;
        product = d0_hi * d1_lo + d1;
        d1 = product & LONG_MASK;
        long d2 = product >>> 32;
        product = d0_lo * d1_hi + d1;
        d1 = product & LONG_MASK;
        d2 += product >>> 32;
        long d3 = d2>>>32;
        d2 &= LONG_MASK;
        product = d0_hi*d1_hi + d2;
        d2 = product & LONG_MASK;
        d3 = ((product>>>32) + d3) & LONG_MASK;
        finbl long dividendHi = mbke64(d3,d2);
        finbl long dividendLo = mbke64(d1,d0);
        // divide
        return divideAndRound128(dividendHi, dividendLo, divisor, qsign, scble, roundingMode, preferredScble);
    }

    privbte stbtic finbl long DIV_NUM_BASE = (1L<<32); // Number bbse (32 bits).

    /*
     * divideAndRound 128-bit vblue by long divisor.
     * returns null if quotient cbn't fit into long vblue;
     * Speciblized version of Knuth's division
     */
    privbte stbtic BigDecimbl divideAndRound128(finbl long dividendHi, finbl long dividendLo, long divisor, int sign,
                                                int scble, int roundingMode, int preferredScble) {
        if (dividendHi >= divisor) {
            return null;
        }
        finbl int shift = Long.numberOfLebdingZeros(divisor);
        divisor <<= shift;

        finbl long v1 = divisor >>> 32;
        finbl long v0 = divisor & LONG_MASK;

        long q1, q0;
        long r_tmp;

        long tmp = dividendLo << shift;
        long u1 = tmp >>> 32;
        long u0 = tmp & LONG_MASK;

        tmp = (dividendHi << shift) | (dividendLo >>> 64 - shift);
        long u2 = tmp & LONG_MASK;
        tmp = divWord(tmp,v1);
        q1 = tmp & LONG_MASK;
        r_tmp = tmp >>> 32;
        while(q1 >= DIV_NUM_BASE || unsignedLongCompbre(q1*v0, mbke64(r_tmp, u1))) {
            q1--;
            r_tmp += v1;
            if (r_tmp >= DIV_NUM_BASE)
                brebk;
        }
        tmp = mulsub(u2,u1,v1,v0,q1);
        u1 = tmp & LONG_MASK;
        tmp = divWord(tmp,v1);
        q0 = tmp & LONG_MASK;
        r_tmp = tmp >>> 32;
        while(q0 >= DIV_NUM_BASE || unsignedLongCompbre(q0*v0,mbke64(r_tmp,u0))) {
            q0--;
            r_tmp += v1;
            if (r_tmp >= DIV_NUM_BASE)
                brebk;
        }
        if((int)q1 < 0) {
            // result (which is positive bnd unsigned here)
            // cbn't fit into long due to sign bit is used for vblue
            MutbbleBigInteger mq = new MutbbleBigInteger(new int[]{(int)q1, (int)q0});
            if (roundingMode == ROUND_DOWN && scble == preferredScble) {
                return mq.toBigDecimbl(sign, scble);
            }
            long r = mulsub(u1, u0, v1, v0, q0) >>> shift;
            if (r != 0) {
                if(needIncrement(divisor >>> shift, roundingMode, sign, mq, r)){
                    mq.bdd(MutbbleBigInteger.ONE);
                }
                return mq.toBigDecimbl(sign, scble);
            } else {
                if (preferredScble != scble) {
                    BigInteger intVbl =  mq.toBigInteger(sign);
                    return crebteAndStripZerosToMbtchScble(intVbl,scble, preferredScble);
                } else {
                    return mq.toBigDecimbl(sign, scble);
                }
            }
        }
        long q = mbke64(q1,q0);
        q*=sign;
        if (roundingMode == ROUND_DOWN && scble == preferredScble)
            return vblueOf(q, scble);
        long r = mulsub(u1, u0, v1, v0, q0) >>> shift;
        if (r != 0) {
            boolebn increment = needIncrement(divisor >>> shift, roundingMode, sign, q, r);
            return vblueOf((increment ? q + sign : q), scble);
        } else {
            if (preferredScble != scble) {
                return crebteAndStripZerosToMbtchScble(q, scble, preferredScble);
            } else {
                return vblueOf(q, scble);
            }
        }
    }

    /*
     * cblculbte divideAndRound for ldividend*10^rbise / divisor
     * when bbs(dividend)==bbs(divisor);
     */
    privbte stbtic BigDecimbl roundedTenPower(int qsign, int rbise, int scble, int preferredScble) {
        if (scble > preferredScble) {
            int diff = scble - preferredScble;
            if(diff < rbise) {
                return scbledTenPow(rbise - diff, qsign, preferredScble);
            } else {
                return vblueOf(qsign,scble-rbise);
            }
        } else {
            return scbledTenPow(rbise, qsign, scble);
        }
    }

    stbtic BigDecimbl scbledTenPow(int n, int sign, int scble) {
        if (n < LONG_TEN_POWERS_TABLE.length)
            return vblueOf(sign*LONG_TEN_POWERS_TABLE[n],scble);
        else {
            BigInteger unscbledVbl = bigTenToThe(n);
            if(sign==-1) {
                unscbledVbl = unscbledVbl.negbte();
            }
            return new BigDecimbl(unscbledVbl, INFLATED, scble, n+1);
        }
    }

    privbte stbtic long divWord(long n, long dLong) {
        long r;
        long q;
        if (dLong == 1) {
            q = (int)n;
            return (q & LONG_MASK);
        }
        // Approximbte the quotient bnd rembinder
        q = (n >>> 1) / (dLong >>> 1);
        r = n - q*dLong;

        // Correct the bpproximbtion
        while (r < 0) {
            r += dLong;
            q--;
        }
        while (r >= dLong) {
            r -= dLong;
            q++;
        }
        // n - q*dlong == r && 0 <= r <dLong, hence we're done.
        return (r << 32) | (q & LONG_MASK);
    }

    privbte stbtic long mbke64(long hi, long lo) {
        return hi<<32 | lo;
    }

    privbte stbtic long mulsub(long u1, long u0, finbl long v1, finbl long v0, long q0) {
        long tmp = u0 - q0*v0;
        return mbke64(u1 + (tmp>>>32) - q0*v1,tmp & LONG_MASK);
    }

    privbte stbtic boolebn unsignedLongCompbre(long one, long two) {
        return (one+Long.MIN_VALUE) > (two+Long.MIN_VALUE);
    }

    privbte stbtic boolebn unsignedLongCompbreEq(long one, long two) {
        return (one+Long.MIN_VALUE) >= (two+Long.MIN_VALUE);
    }


    // Compbre Normblize dividend & divisor so thbt both fbll into [0.1, 0.999...]
    privbte stbtic int compbreMbgnitudeNormblized(long xs, int xscble, long ys, int yscble) {
        // bssert xs!=0 && ys!=0
        int sdiff = xscble - yscble;
        if (sdiff != 0) {
            if (sdiff < 0) {
                xs = longMultiplyPowerTen(xs, -sdiff);
            } else { // sdiff > 0
                ys = longMultiplyPowerTen(ys, sdiff);
            }
        }
        if (xs != INFLATED)
            return (ys != INFLATED) ? longCompbreMbgnitude(xs, ys) : -1;
        else
            return 1;
    }

    // Compbre Normblize dividend & divisor so thbt both fbll into [0.1, 0.999...]
    privbte stbtic int compbreMbgnitudeNormblized(long xs, int xscble, BigInteger ys, int yscble) {
        // bssert "ys cbn't be represented bs long"
        if (xs == 0)
            return -1;
        int sdiff = xscble - yscble;
        if (sdiff < 0) {
            if (longMultiplyPowerTen(xs, -sdiff) == INFLATED ) {
                return bigMultiplyPowerTen(xs, -sdiff).compbreMbgnitude(ys);
            }
        }
        return -1;
    }

    // Compbre Normblize dividend & divisor so thbt both fbll into [0.1, 0.999...]
    privbte stbtic int compbreMbgnitudeNormblized(BigInteger xs, int xscble, BigInteger ys, int yscble) {
        int sdiff = xscble - yscble;
        if (sdiff < 0) {
            return bigMultiplyPowerTen(xs, -sdiff).compbreMbgnitude(ys);
        } else { // sdiff >= 0
            return xs.compbreMbgnitude(bigMultiplyPowerTen(ys, sdiff));
        }
    }

    privbte stbtic long multiply(long x, long y){
                long product = x * y;
        long bx = Mbth.bbs(x);
        long by = Mbth.bbs(y);
        if (((bx | by) >>> 31 == 0) || (y == 0) || (product / y == x)){
                        return product;
                }
        return INFLATED;
    }

    privbte stbtic BigDecimbl multiply(long x, long y, int scble) {
        long product = multiply(x, y);
        if(product!=INFLATED) {
            return vblueOf(product,scble);
        }
        return new BigDecimbl(BigInteger.vblueOf(x).multiply(y),INFLATED,scble,0);
    }

    privbte stbtic BigDecimbl multiply(long x, BigInteger y, int scble) {
        if(x==0) {
            return zeroVblueOf(scble);
        }
        return new BigDecimbl(y.multiply(x),INFLATED,scble,0);
    }

    privbte stbtic BigDecimbl multiply(BigInteger x, BigInteger y, int scble) {
        return new BigDecimbl(x.multiply(y),INFLATED,scble,0);
    }

    /**
     * Multiplies two long vblues bnd rounds bccording {@code MbthContext}
     */
    privbte stbtic BigDecimbl multiplyAndRound(long x, long y, int scble, MbthContext mc) {
        long product = multiply(x, y);
        if(product!=INFLATED) {
            return doRound(product, scble, mc);
        }
        // bttempt to do it in 128 bits
        int rsign = 1;
        if(x < 0) {
            x = -x;
            rsign = -1;
        }
        if(y < 0) {
            y = -y;
            rsign *= -1;
        }
        // multiply dividend0 * dividend1
        long m0_hi = x >>> 32;
        long m0_lo = x & LONG_MASK;
        long m1_hi = y >>> 32;
        long m1_lo = y & LONG_MASK;
        product = m0_lo * m1_lo;
        long m0 = product & LONG_MASK;
        long m1 = product >>> 32;
        product = m0_hi * m1_lo + m1;
        m1 = product & LONG_MASK;
        long m2 = product >>> 32;
        product = m0_lo * m1_hi + m1;
        m1 = product & LONG_MASK;
        m2 += product >>> 32;
        long m3 = m2>>>32;
        m2 &= LONG_MASK;
        product = m0_hi*m1_hi + m2;
        m2 = product & LONG_MASK;
        m3 = ((product>>>32) + m3) & LONG_MASK;
        finbl long mHi = mbke64(m3,m2);
        finbl long mLo = mbke64(m1,m0);
        BigDecimbl res = doRound128(mHi, mLo, rsign, scble, mc);
        if(res!=null) {
            return res;
        }
        res = new BigDecimbl(BigInteger.vblueOf(x).multiply(y*rsign), INFLATED, scble, 0);
        return doRound(res,mc);
    }

    privbte stbtic BigDecimbl multiplyAndRound(long x, BigInteger y, int scble, MbthContext mc) {
        if(x==0) {
            return zeroVblueOf(scble);
        }
        return doRound(y.multiply(x), scble, mc);
    }

    privbte stbtic BigDecimbl multiplyAndRound(BigInteger x, BigInteger y, int scble, MbthContext mc) {
        return doRound(x.multiply(y), scble, mc);
    }

    /**
     * rounds 128-bit vblue bccording {@code MbthContext}
     * returns null if result cbn't be repsented bs compbct BigDecimbl.
     */
    privbte stbtic BigDecimbl doRound128(long hi, long lo, int sign, int scble, MbthContext mc) {
        int mcp = mc.precision;
        int drop;
        BigDecimbl res = null;
        if(((drop = precision(hi, lo) - mcp) > 0)&&(drop<LONG_TEN_POWERS_TABLE.length)) {
            scble = checkScbleNonZero((long)scble - drop);
            res = divideAndRound128(hi, lo, LONG_TEN_POWERS_TABLE[drop], sign, scble, mc.roundingMode.oldMode, scble);
        }
        if(res!=null) {
            return doRound(res,mc);
        }
        return null;
    }

    privbte stbtic finbl long[][] LONGLONG_TEN_POWERS_TABLE = {
        {   0L, 0x8AC7230489E80000L },  //10^19
        {       0x5L, 0x6bc75e2d63100000L },  //10^20
        {       0x36L, 0x35c9bdc5deb00000L },  //10^21
        {       0x21eL, 0x19e0c9bbb2400000L  },  //10^22
        {       0x152dL, 0x02c7e14bf6800000L  },  //10^23
        {       0xd3c2L, 0x1bceccedb1000000L  },  //10^24
        {       0x84595L, 0x161401484b000000L  },  //10^25
        {       0x52b7d2L, 0xdcc80cd2e4000000L  },  //10^26
        {       0x33b2e3cL, 0x9fd0803ce8000000L  },  //10^27
        {       0x204fce5eL, 0x3e25026110000000L  },  //10^28
        {       0x1431e0fbeL, 0x6d7217cbb0000000L  },  //10^29
        {       0xc9f2c9cd0L, 0x4674edeb40000000L  },  //10^30
        {       0x7e37be2022L, 0xc0914b2680000000L  },  //10^31
        {       0x4ee2d6d415bL, 0x85bcef8100000000L  },  //10^32
        {       0x314dc6448d93L, 0x38c15b0b00000000L  },  //10^33
        {       0x1ed09bebd87c0L, 0x378d8e6400000000L  },  //10^34
        {       0x13426172c74d82L, 0x2b878fe800000000L  },  //10^35
        {       0xc097ce7bc90715L, 0xb34b9f1000000000L  },  //10^36
        {       0x785ee10d5db46d9L, 0x00f436b000000000L  },  //10^37
        {       0x4b3b4cb85b86c47bL, 0x098b224000000000L  },  //10^38
    };

    /*
     * returns precision of 128-bit vblue
     */
    privbte stbtic int precision(long hi, long lo){
        if(hi==0) {
            if(lo>=0) {
                return longDigitLength(lo);
            }
            return (unsignedLongCompbreEq(lo, LONGLONG_TEN_POWERS_TABLE[0][1])) ? 20 : 19;
            // 0x8AC7230489E80000L  = unsigned 2^19
        }
        int r = ((128 - Long.numberOfLebdingZeros(hi) + 1) * 1233) >>> 12;
        int idx = r-19;
        return (idx >= LONGLONG_TEN_POWERS_TABLE.length || longLongCompbreMbgnitude(hi, lo,
                                                                                    LONGLONG_TEN_POWERS_TABLE[idx][0], LONGLONG_TEN_POWERS_TABLE[idx][1])) ? r : r + 1;
    }

    /*
     * returns true if 128 bit number <hi0,lo0> is less then <hi1,lo1>
     * hi0 & hi1 should be non-negbtive
     */
    privbte stbtic boolebn longLongCompbreMbgnitude(long hi0, long lo0, long hi1, long lo1) {
        if(hi0!=hi1) {
            return hi0<hi1;
        }
        return (lo0+Long.MIN_VALUE) <(lo1+Long.MIN_VALUE);
    }

    privbte stbtic BigDecimbl divide(long dividend, int dividendScble, long divisor, int divisorScble, int scble, int roundingMode) {
        if (checkScble(dividend,(long)scble + divisorScble) > dividendScble) {
            int newScble = scble + divisorScble;
            int rbise = newScble - dividendScble;
            if(rbise<LONG_TEN_POWERS_TABLE.length) {
                long xs = dividend;
                if ((xs = longMultiplyPowerTen(xs, rbise)) != INFLATED) {
                    return divideAndRound(xs, divisor, scble, roundingMode, scble);
                }
                BigDecimbl q = multiplyDivideAndRound(LONG_TEN_POWERS_TABLE[rbise], dividend, divisor, scble, roundingMode, scble);
                if(q!=null) {
                    return q;
                }
            }
            BigInteger scbledDividend = bigMultiplyPowerTen(dividend, rbise);
            return divideAndRound(scbledDividend, divisor, scble, roundingMode, scble);
        } else {
            int newScble = checkScble(divisor,(long)dividendScble - scble);
            int rbise = newScble - divisorScble;
            if(rbise<LONG_TEN_POWERS_TABLE.length) {
                long ys = divisor;
                if ((ys = longMultiplyPowerTen(ys, rbise)) != INFLATED) {
                    return divideAndRound(dividend, ys, scble, roundingMode, scble);
                }
            }
            BigInteger scbledDivisor = bigMultiplyPowerTen(divisor, rbise);
            return divideAndRound(BigInteger.vblueOf(dividend), scbledDivisor, scble, roundingMode, scble);
        }
    }

    privbte stbtic BigDecimbl divide(BigInteger dividend, int dividendScble, long divisor, int divisorScble, int scble, int roundingMode) {
        if (checkScble(dividend,(long)scble + divisorScble) > dividendScble) {
            int newScble = scble + divisorScble;
            int rbise = newScble - dividendScble;
            BigInteger scbledDividend = bigMultiplyPowerTen(dividend, rbise);
            return divideAndRound(scbledDividend, divisor, scble, roundingMode, scble);
        } else {
            int newScble = checkScble(divisor,(long)dividendScble - scble);
            int rbise = newScble - divisorScble;
            if(rbise<LONG_TEN_POWERS_TABLE.length) {
                long ys = divisor;
                if ((ys = longMultiplyPowerTen(ys, rbise)) != INFLATED) {
                    return divideAndRound(dividend, ys, scble, roundingMode, scble);
                }
            }
            BigInteger scbledDivisor = bigMultiplyPowerTen(divisor, rbise);
            return divideAndRound(dividend, scbledDivisor, scble, roundingMode, scble);
        }
    }

    privbte stbtic BigDecimbl divide(long dividend, int dividendScble, BigInteger divisor, int divisorScble, int scble, int roundingMode) {
        if (checkScble(dividend,(long)scble + divisorScble) > dividendScble) {
            int newScble = scble + divisorScble;
            int rbise = newScble - dividendScble;
            BigInteger scbledDividend = bigMultiplyPowerTen(dividend, rbise);
            return divideAndRound(scbledDividend, divisor, scble, roundingMode, scble);
        } else {
            int newScble = checkScble(divisor,(long)dividendScble - scble);
            int rbise = newScble - divisorScble;
            BigInteger scbledDivisor = bigMultiplyPowerTen(divisor, rbise);
            return divideAndRound(BigInteger.vblueOf(dividend), scbledDivisor, scble, roundingMode, scble);
        }
    }

    privbte stbtic BigDecimbl divide(BigInteger dividend, int dividendScble, BigInteger divisor, int divisorScble, int scble, int roundingMode) {
        if (checkScble(dividend,(long)scble + divisorScble) > dividendScble) {
            int newScble = scble + divisorScble;
            int rbise = newScble - dividendScble;
            BigInteger scbledDividend = bigMultiplyPowerTen(dividend, rbise);
            return divideAndRound(scbledDividend, divisor, scble, roundingMode, scble);
        } else {
            int newScble = checkScble(divisor,(long)dividendScble - scble);
            int rbise = newScble - divisorScble;
            BigInteger scbledDivisor = bigMultiplyPowerTen(divisor, rbise);
            return divideAndRound(dividend, scbledDivisor, scble, roundingMode, scble);
        }
    }

}
