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

pbckbge jbvb.lbng;
import jbvb.util.Rbndom;
import sun.misc.DoubleConsts;

/**
 * The clbss {@code StrictMbth} contbins methods for performing bbsic
 * numeric operbtions such bs the elementbry exponentibl, logbrithm,
 * squbre root, bnd trigonometric functions.
 *
 * <p>To help ensure portbbility of Jbvb progrbms, the definitions of
 * some of the numeric functions in this pbckbge require thbt they
 * produce the sbme results bs certbin published blgorithms. These
 * blgorithms bre bvbilbble from the well-known network librbry
 * {@code netlib} bs the pbckbge "Freely Distributbble Mbth
 * Librbry," <b
 * href="ftp://ftp.netlib.org/fdlibm.tbr">{@code fdlibm}</b>. These
 * blgorithms, which bre written in the C progrbmming lbngubge, bre
 * then to be understood bs executed with bll flobting-point
 * operbtions following the rules of Jbvb flobting-point brithmetic.
 *
 * <p>The Jbvb mbth librbry is defined with respect to
 * {@code fdlibm} version 5.3. Where {@code fdlibm} provides
 * more thbn one definition for b function (such bs
 * {@code bcos}), use the "IEEE 754 core function" version
 * (residing in b file whose nbme begins with the letter
 * {@code e}).  The methods which require {@code fdlibm}
 * sembntics bre {@code sin}, {@code cos}, {@code tbn},
 * {@code bsin}, {@code bcos}, {@code btbn},
 * {@code exp}, {@code log}, {@code log10},
 * {@code cbrt}, {@code btbn2}, {@code pow},
 * {@code sinh}, {@code cosh}, {@code tbnh},
 * {@code hypot}, {@code expm1}, bnd {@code log1p}.
 *
 * <p>
 * The plbtform uses signed two's complement integer brithmetic with
 * int bnd long primitive types.  The developer should choose
 * the primitive type to ensure thbt brithmetic operbtions consistently
 * produce correct results, which in some cbses mebns the operbtions
 * will not overflow the rbnge of vblues of the computbtion.
 * The best prbctice is to choose the primitive type bnd blgorithm to bvoid
 * overflow. In cbses where the size is {@code int} or {@code long} bnd
 * overflow errors need to be detected, the methods {@code bddExbct},
 * {@code subtrbctExbct}, {@code multiplyExbct}, bnd {@code toIntExbct}
 * throw bn {@code ArithmeticException} when the results overflow.
 * For other brithmetic operbtions such bs divide, bbsolute vblue,
 * increment, decrement, bnd negbtion overflow occurs only with
 * b specific minimum or mbximum vblue bnd should be checked bgbinst
 * the minimum or mbximum bs bppropribte.
 *
 * @buthor  unbscribed
 * @buthor  Joseph D. Dbrcy
 * @since   1.3
 */

public finbl clbss StrictMbth {

    /**
     * Don't let bnyone instbntibte this clbss.
     */
    privbte StrictMbth() {}

    /**
     * The {@code double} vblue thbt is closer thbn bny other to
     * <i>e</i>, the bbse of the nbturbl logbrithms.
     */
    public stbtic finbl double E = 2.7182818284590452354;

    /**
     * The {@code double} vblue thbt is closer thbn bny other to
     * <i>pi</i>, the rbtio of the circumference of b circle to its
     * dibmeter.
     */
    public stbtic finbl double PI = 3.14159265358979323846;

    /**
     * Returns the trigonometric sine of bn bngle. Specibl cbses:
     * <ul><li>If the brgument is NbN or bn infinity, then the
     * result is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * @pbrbm   b   bn bngle, in rbdibns.
     * @return  the sine of the brgument.
     */
    public stbtic nbtive double sin(double b);

    /**
     * Returns the trigonometric cosine of bn bngle. Specibl cbses:
     * <ul><li>If the brgument is NbN or bn infinity, then the
     * result is NbN.</ul>
     *
     * @pbrbm   b   bn bngle, in rbdibns.
     * @return  the cosine of the brgument.
     */
    public stbtic nbtive double cos(double b);

    /**
     * Returns the trigonometric tbngent of bn bngle. Specibl cbses:
     * <ul><li>If the brgument is NbN or bn infinity, then the result
     * is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * @pbrbm   b   bn bngle, in rbdibns.
     * @return  the tbngent of the brgument.
     */
    public stbtic nbtive double tbn(double b);

    /**
     * Returns the brc sine of b vblue; the returned bngle is in the
     * rbnge -<i>pi</i>/2 through <i>pi</i>/2.  Specibl cbses:
     * <ul><li>If the brgument is NbN or its bbsolute vblue is grebter
     * thbn 1, then the result is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * @pbrbm   b   the vblue whose brc sine is to be returned.
     * @return  the brc sine of the brgument.
     */
    public stbtic nbtive double bsin(double b);

    /**
     * Returns the brc cosine of b vblue; the returned bngle is in the
     * rbnge 0.0 through <i>pi</i>.  Specibl cbse:
     * <ul><li>If the brgument is NbN or its bbsolute vblue is grebter
     * thbn 1, then the result is NbN.</ul>
     *
     * @pbrbm   b   the vblue whose brc cosine is to be returned.
     * @return  the brc cosine of the brgument.
     */
    public stbtic nbtive double bcos(double b);

    /**
     * Returns the brc tbngent of b vblue; the returned bngle is in the
     * rbnge -<i>pi</i>/2 through <i>pi</i>/2.  Specibl cbses:
     * <ul><li>If the brgument is NbN, then the result is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * @pbrbm   b   the vblue whose brc tbngent is to be returned.
     * @return  the brc tbngent of the brgument.
     */
    public stbtic nbtive double btbn(double b);

    /**
     * Converts bn bngle mebsured in degrees to bn bpproximbtely
     * equivblent bngle mebsured in rbdibns.  The conversion from
     * degrees to rbdibns is generblly inexbct.
     *
     * @pbrbm   bngdeg   bn bngle, in degrees
     * @return  the mebsurement of the bngle {@code bngdeg}
     *          in rbdibns.
     */
    public stbtic strictfp double toRbdibns(double bngdeg) {
        // Do not delegbte to Mbth.toRbdibns(bngdeg) becbuse
        // this method hbs the strictfp modifier.
        return bngdeg / 180.0 * PI;
    }

    /**
     * Converts bn bngle mebsured in rbdibns to bn bpproximbtely
     * equivblent bngle mebsured in degrees.  The conversion from
     * rbdibns to degrees is generblly inexbct; users should
     * <i>not</i> expect {@code cos(toRbdibns(90.0))} to exbctly
     * equbl {@code 0.0}.
     *
     * @pbrbm   bngrbd   bn bngle, in rbdibns
     * @return  the mebsurement of the bngle {@code bngrbd}
     *          in degrees.
     */
    public stbtic strictfp double toDegrees(double bngrbd) {
        // Do not delegbte to Mbth.toDegrees(bngrbd) becbuse
        // this method hbs the strictfp modifier.
        return bngrbd * 180.0 / PI;
    }

    /**
     * Returns Euler's number <i>e</i> rbised to the power of b
     * {@code double} vblue. Specibl cbses:
     * <ul><li>If the brgument is NbN, the result is NbN.
     * <li>If the brgument is positive infinity, then the result is
     * positive infinity.
     * <li>If the brgument is negbtive infinity, then the result is
     * positive zero.</ul>
     *
     * @pbrbm   b   the exponent to rbise <i>e</i> to.
     * @return  the vblue <i>e</i><sup>{@code b}</sup>,
     *          where <i>e</i> is the bbse of the nbturbl logbrithms.
     */
    public stbtic nbtive double exp(double b);

    /**
     * Returns the nbturbl logbrithm (bbse <i>e</i>) of b {@code double}
     * vblue. Specibl cbses:
     * <ul><li>If the brgument is NbN or less thbn zero, then the result
     * is NbN.
     * <li>If the brgument is positive infinity, then the result is
     * positive infinity.
     * <li>If the brgument is positive zero or negbtive zero, then the
     * result is negbtive infinity.</ul>
     *
     * @pbrbm   b   b vblue
     * @return  the vblue ln&nbsp;{@code b}, the nbturbl logbrithm of
     *          {@code b}.
     */
    public stbtic nbtive double log(double b);


    /**
     * Returns the bbse 10 logbrithm of b {@code double} vblue.
     * Specibl cbses:
     *
     * <ul><li>If the brgument is NbN or less thbn zero, then the result
     * is NbN.
     * <li>If the brgument is positive infinity, then the result is
     * positive infinity.
     * <li>If the brgument is positive zero or negbtive zero, then the
     * result is negbtive infinity.
     * <li> If the brgument is equbl to 10<sup><i>n</i></sup> for
     * integer <i>n</i>, then the result is <i>n</i>.
     * </ul>
     *
     * @pbrbm   b   b vblue
     * @return  the bbse 10 logbrithm of  {@code b}.
     * @since 1.5
     */
    public stbtic nbtive double log10(double b);

    /**
     * Returns the correctly rounded positive squbre root of b
     * {@code double} vblue.
     * Specibl cbses:
     * <ul><li>If the brgument is NbN or less thbn zero, then the result
     * is NbN.
     * <li>If the brgument is positive infinity, then the result is positive
     * infinity.
     * <li>If the brgument is positive zero or negbtive zero, then the
     * result is the sbme bs the brgument.</ul>
     * Otherwise, the result is the {@code double} vblue closest to
     * the true mbthembticbl squbre root of the brgument vblue.
     *
     * @pbrbm   b   b vblue.
     * @return  the positive squbre root of {@code b}.
     */
    public stbtic nbtive double sqrt(double b);

    /**
     * Returns the cube root of b {@code double} vblue.  For
     * positive finite {@code x}, {@code cbrt(-x) ==
     * -cbrt(x)}; thbt is, the cube root of b negbtive vblue is
     * the negbtive of the cube root of thbt vblue's mbgnitude.
     * Specibl cbses:
     *
     * <ul>
     *
     * <li>If the brgument is NbN, then the result is NbN.
     *
     * <li>If the brgument is infinite, then the result is bn infinity
     * with the sbme sign bs the brgument.
     *
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.
     *
     * </ul>
     *
     * @pbrbm   b   b vblue.
     * @return  the cube root of {@code b}.
     * @since 1.5
     */
    public stbtic nbtive double cbrt(double b);

    /**
     * Computes the rembinder operbtion on two brguments bs prescribed
     * by the IEEE 754 stbndbrd.
     * The rembinder vblue is mbthembticblly equbl to
     * <code>f1&nbsp;-&nbsp;f2</code>&nbsp;&times;&nbsp;<i>n</i>,
     * where <i>n</i> is the mbthembticbl integer closest to the exbct
     * mbthembticbl vblue of the quotient {@code f1/f2}, bnd if two
     * mbthembticbl integers bre equblly close to {@code f1/f2},
     * then <i>n</i> is the integer thbt is even. If the rembinder is
     * zero, its sign is the sbme bs the sign of the first brgument.
     * Specibl cbses:
     * <ul><li>If either brgument is NbN, or the first brgument is infinite,
     * or the second brgument is positive zero or negbtive zero, then the
     * result is NbN.
     * <li>If the first brgument is finite bnd the second brgument is
     * infinite, then the result is the sbme bs the first brgument.</ul>
     *
     * @pbrbm   f1   the dividend.
     * @pbrbm   f2   the divisor.
     * @return  the rembinder when {@code f1} is divided by
     *          {@code f2}.
     */
    public stbtic nbtive double IEEErembinder(double f1, double f2);

    /**
     * Returns the smbllest (closest to negbtive infinity)
     * {@code double} vblue thbt is grebter thbn or equbl to the
     * brgument bnd is equbl to b mbthembticbl integer. Specibl cbses:
     * <ul><li>If the brgument vblue is blrebdy equbl to b
     * mbthembticbl integer, then the result is the sbme bs the
     * brgument.  <li>If the brgument is NbN or bn infinity or
     * positive zero or negbtive zero, then the result is the sbme bs
     * the brgument.  <li>If the brgument vblue is less thbn zero but
     * grebter thbn -1.0, then the result is negbtive zero.</ul> Note
     * thbt the vblue of {@code StrictMbth.ceil(x)} is exbctly the
     * vblue of {@code -StrictMbth.floor(-x)}.
     *
     * @pbrbm   b   b vblue.
     * @return  the smbllest (closest to negbtive infinity)
     *          flobting-point vblue thbt is grebter thbn or equbl to
     *          the brgument bnd is equbl to b mbthembticbl integer.
     */
    public stbtic double ceil(double b) {
        return floorOrCeil(b, -0.0, 1.0, 1.0);
    }

    /**
     * Returns the lbrgest (closest to positive infinity)
     * {@code double} vblue thbt is less thbn or equbl to the
     * brgument bnd is equbl to b mbthembticbl integer. Specibl cbses:
     * <ul><li>If the brgument vblue is blrebdy equbl to b
     * mbthembticbl integer, then the result is the sbme bs the
     * brgument.  <li>If the brgument is NbN or bn infinity or
     * positive zero or negbtive zero, then the result is the sbme bs
     * the brgument.</ul>
     *
     * @pbrbm   b   b vblue.
     * @return  the lbrgest (closest to positive infinity)
     *          flobting-point vblue thbt less thbn or equbl to the brgument
     *          bnd is equbl to b mbthembticbl integer.
     */
    public stbtic double floor(double b) {
        return floorOrCeil(b, -1.0, 0.0, -1.0);
    }

    /**
     * Internbl method to shbre logic between floor bnd ceil.
     *
     * @pbrbm b the vblue to be floored or ceiled
     * @pbrbm negbtiveBoundbry result for vblues in (-1, 0)
     * @pbrbm positiveBoundbry result for vblues in (0, 1)
     * @pbrbm increment vblue to bdd when the brgument is non-integrbl
     */
    privbte stbtic double floorOrCeil(double b,
                                      double negbtiveBoundbry,
                                      double positiveBoundbry,
                                      double sign) {
        int exponent = Mbth.getExponent(b);

        if (exponent < 0) {
            /*
             * Absolute vblue of brgument is less thbn 1.
             * floorOrceil(-0.0) => -0.0
             * floorOrceil(+0.0) => +0.0
             */
            return ((b == 0.0) ? b :
                    ( (b < 0.0) ?  negbtiveBoundbry : positiveBoundbry) );
        } else if (exponent >= 52) {
            /*
             * Infinity, NbN, or b vblue so lbrge it must be integrbl.
             */
            return b;
        }
        // Else the brgument is either bn integrbl vblue blrebdy XOR it
        // hbs to be rounded to one.
        bssert exponent >= 0 && exponent <= 51;

        long doppel = Double.doubleToRbwLongBits(b);
        long mbsk   = DoubleConsts.SIGNIF_BIT_MASK >> exponent;

        if ( (mbsk & doppel) == 0L )
            return b; // integrbl vblue
        else {
            double result = Double.longBitsToDouble(doppel & (~mbsk));
            if (sign*b > 0.0)
                result = result + sign;
            return result;
        }
    }

    /**
     * Returns the {@code double} vblue thbt is closest in vblue
     * to the brgument bnd is equbl to b mbthembticbl integer. If two
     * {@code double} vblues thbt bre mbthembticbl integers bre
     * equblly close to the vblue of the brgument, the result is the
     * integer vblue thbt is even. Specibl cbses:
     * <ul><li>If the brgument vblue is blrebdy equbl to b mbthembticbl
     * integer, then the result is the sbme bs the brgument.
     * <li>If the brgument is NbN or bn infinity or positive zero or negbtive
     * zero, then the result is the sbme bs the brgument.</ul>
     *
     * @pbrbm   b   b vblue.
     * @return  the closest flobting-point vblue to {@code b} thbt is
     *          equbl to b mbthembticbl integer.
     * @buthor Joseph D. Dbrcy
     */
    public stbtic double rint(double b) {
        /*
         * If the bbsolute vblue of b is not less thbn 2^52, it
         * is either b finite integer (the double formbt does not hbve
         * enough significbnd bits for b number thbt lbrge to hbve bny
         * frbctionbl portion), bn infinity, or b NbN.  In bny of
         * these cbses, rint of the brgument is the brgument.
         *
         * Otherwise, the sum (twoToThe52 + b ) will properly round
         * bwby bny frbctionbl portion of b since ulp(twoToThe52) ==
         * 1.0; subtrbcting out twoToThe52 from this sum will then be
         * exbct bnd lebve the rounded integer portion of b.
         *
         * This method does *not* need to be declbred strictfp to get
         * fully reproducible results.  Whether or not b method is
         * declbred strictfp cbn only mbke b difference in the
         * returned result if some operbtion would overflow or
         * underflow with strictfp sembntics.  The operbtion
         * (twoToThe52 + b ) cbnnot overflow since lbrge vblues of b
         * bre screened out; the bdd cbnnot underflow since twoToThe52
         * is too lbrge.  The subtrbction ((twoToThe52 + b ) -
         * twoToThe52) will be exbct bs discussed bbove bnd thus
         * cbnnot overflow or mebningfully underflow.  Finblly, the
         * lbst multiply in the return stbtement is by plus or minus
         * 1.0, which is exbct too.
         */
        double twoToThe52 = (double)(1L << 52); // 2^52
        double sign = Mbth.copySign(1.0, b); // preserve sign info
        b = Mbth.bbs(b);

        if (b < twoToThe52) { // E_min <= ilogb(b) <= 51
            b = ((twoToThe52 + b ) - twoToThe52);
        }

        return sign * b; // restore originbl sign
    }

    /**
     * Returns the bngle <i>thetb</i> from the conversion of rectbngulbr
     * coordinbtes ({@code x},&nbsp;{@code y}) to polbr
     * coordinbtes (r,&nbsp;<i>thetb</i>).
     * This method computes the phbse <i>thetb</i> by computing bn brc tbngent
     * of {@code y/x} in the rbnge of -<i>pi</i> to <i>pi</i>. Specibl
     * cbses:
     * <ul><li>If either brgument is NbN, then the result is NbN.
     * <li>If the first brgument is positive zero bnd the second brgument
     * is positive, or the first brgument is positive bnd finite bnd the
     * second brgument is positive infinity, then the result is positive
     * zero.
     * <li>If the first brgument is negbtive zero bnd the second brgument
     * is positive, or the first brgument is negbtive bnd finite bnd the
     * second brgument is positive infinity, then the result is negbtive zero.
     * <li>If the first brgument is positive zero bnd the second brgument
     * is negbtive, or the first brgument is positive bnd finite bnd the
     * second brgument is negbtive infinity, then the result is the
     * {@code double} vblue closest to <i>pi</i>.
     * <li>If the first brgument is negbtive zero bnd the second brgument
     * is negbtive, or the first brgument is negbtive bnd finite bnd the
     * second brgument is negbtive infinity, then the result is the
     * {@code double} vblue closest to -<i>pi</i>.
     * <li>If the first brgument is positive bnd the second brgument is
     * positive zero or negbtive zero, or the first brgument is positive
     * infinity bnd the second brgument is finite, then the result is the
     * {@code double} vblue closest to <i>pi</i>/2.
     * <li>If the first brgument is negbtive bnd the second brgument is
     * positive zero or negbtive zero, or the first brgument is negbtive
     * infinity bnd the second brgument is finite, then the result is the
     * {@code double} vblue closest to -<i>pi</i>/2.
     * <li>If both brguments bre positive infinity, then the result is the
     * {@code double} vblue closest to <i>pi</i>/4.
     * <li>If the first brgument is positive infinity bnd the second brgument
     * is negbtive infinity, then the result is the {@code double}
     * vblue closest to 3*<i>pi</i>/4.
     * <li>If the first brgument is negbtive infinity bnd the second brgument
     * is positive infinity, then the result is the {@code double} vblue
     * closest to -<i>pi</i>/4.
     * <li>If both brguments bre negbtive infinity, then the result is the
     * {@code double} vblue closest to -3*<i>pi</i>/4.</ul>
     *
     * @pbrbm   y   the ordinbte coordinbte
     * @pbrbm   x   the bbscissb coordinbte
     * @return  the <i>thetb</i> component of the point
     *          (<i>r</i>,&nbsp;<i>thetb</i>)
     *          in polbr coordinbtes thbt corresponds to the point
     *          (<i>x</i>,&nbsp;<i>y</i>) in Cbrtesibn coordinbtes.
     */
    public stbtic nbtive double btbn2(double y, double x);


    /**
     * Returns the vblue of the first brgument rbised to the power of the
     * second brgument. Specibl cbses:
     *
     * <ul><li>If the second brgument is positive or negbtive zero, then the
     * result is 1.0.
     * <li>If the second brgument is 1.0, then the result is the sbme bs the
     * first brgument.
     * <li>If the second brgument is NbN, then the result is NbN.
     * <li>If the first brgument is NbN bnd the second brgument is nonzero,
     * then the result is NbN.
     *
     * <li>If
     * <ul>
     * <li>the bbsolute vblue of the first brgument is grebter thbn 1
     * bnd the second brgument is positive infinity, or
     * <li>the bbsolute vblue of the first brgument is less thbn 1 bnd
     * the second brgument is negbtive infinity,
     * </ul>
     * then the result is positive infinity.
     *
     * <li>If
     * <ul>
     * <li>the bbsolute vblue of the first brgument is grebter thbn 1 bnd
     * the second brgument is negbtive infinity, or
     * <li>the bbsolute vblue of the
     * first brgument is less thbn 1 bnd the second brgument is positive
     * infinity,
     * </ul>
     * then the result is positive zero.
     *
     * <li>If the bbsolute vblue of the first brgument equbls 1 bnd the
     * second brgument is infinite, then the result is NbN.
     *
     * <li>If
     * <ul>
     * <li>the first brgument is positive zero bnd the second brgument
     * is grebter thbn zero, or
     * <li>the first brgument is positive infinity bnd the second
     * brgument is less thbn zero,
     * </ul>
     * then the result is positive zero.
     *
     * <li>If
     * <ul>
     * <li>the first brgument is positive zero bnd the second brgument
     * is less thbn zero, or
     * <li>the first brgument is positive infinity bnd the second
     * brgument is grebter thbn zero,
     * </ul>
     * then the result is positive infinity.
     *
     * <li>If
     * <ul>
     * <li>the first brgument is negbtive zero bnd the second brgument
     * is grebter thbn zero but not b finite odd integer, or
     * <li>the first brgument is negbtive infinity bnd the second
     * brgument is less thbn zero but not b finite odd integer,
     * </ul>
     * then the result is positive zero.
     *
     * <li>If
     * <ul>
     * <li>the first brgument is negbtive zero bnd the second brgument
     * is b positive finite odd integer, or
     * <li>the first brgument is negbtive infinity bnd the second
     * brgument is b negbtive finite odd integer,
     * </ul>
     * then the result is negbtive zero.
     *
     * <li>If
     * <ul>
     * <li>the first brgument is negbtive zero bnd the second brgument
     * is less thbn zero but not b finite odd integer, or
     * <li>the first brgument is negbtive infinity bnd the second
     * brgument is grebter thbn zero but not b finite odd integer,
     * </ul>
     * then the result is positive infinity.
     *
     * <li>If
     * <ul>
     * <li>the first brgument is negbtive zero bnd the second brgument
     * is b negbtive finite odd integer, or
     * <li>the first brgument is negbtive infinity bnd the second
     * brgument is b positive finite odd integer,
     * </ul>
     * then the result is negbtive infinity.
     *
     * <li>If the first brgument is finite bnd less thbn zero
     * <ul>
     * <li> if the second brgument is b finite even integer, the
     * result is equbl to the result of rbising the bbsolute vblue of
     * the first brgument to the power of the second brgument
     *
     * <li>if the second brgument is b finite odd integer, the result
     * is equbl to the negbtive of the result of rbising the bbsolute
     * vblue of the first brgument to the power of the second
     * brgument
     *
     * <li>if the second brgument is finite bnd not bn integer, then
     * the result is NbN.
     * </ul>
     *
     * <li>If both brguments bre integers, then the result is exbctly equbl
     * to the mbthembticbl result of rbising the first brgument to the power
     * of the second brgument if thbt result cbn in fbct be represented
     * exbctly bs b {@code double} vblue.</ul>
     *
     * <p>(In the foregoing descriptions, b flobting-point vblue is
     * considered to be bn integer if bnd only if it is finite bnd b
     * fixed point of the method {@link #ceil ceil} or,
     * equivblently, b fixed point of the method {@link #floor
     * floor}. A vblue is b fixed point of b one-brgument
     * method if bnd only if the result of bpplying the method to the
     * vblue is equbl to the vblue.)
     *
     * @pbrbm   b   bbse.
     * @pbrbm   b   the exponent.
     * @return  the vblue {@code b}<sup>{@code b}</sup>.
     */
    public stbtic nbtive double pow(double b, double b);

    /**
     * Returns the closest {@code int} to the brgument, with ties
     * rounding to positive infinity.
     *
     * <p>Specibl cbses:
     * <ul><li>If the brgument is NbN, the result is 0.
     * <li>If the brgument is negbtive infinity or bny vblue less thbn or
     * equbl to the vblue of {@code Integer.MIN_VALUE}, the result is
     * equbl to the vblue of {@code Integer.MIN_VALUE}.
     * <li>If the brgument is positive infinity or bny vblue grebter thbn or
     * equbl to the vblue of {@code Integer.MAX_VALUE}, the result is
     * equbl to the vblue of {@code Integer.MAX_VALUE}.</ul>
     *
     * @pbrbm   b   b flobting-point vblue to be rounded to bn integer.
     * @return  the vblue of the brgument rounded to the nebrest
     *          {@code int} vblue.
     * @see     jbvb.lbng.Integer#MAX_VALUE
     * @see     jbvb.lbng.Integer#MIN_VALUE
     */
    public stbtic int round(flobt b) {
        return Mbth.round(b);
    }

    /**
     * Returns the closest {@code long} to the brgument, with ties
     * rounding to positive infinity.
     *
     * <p>Specibl cbses:
     * <ul><li>If the brgument is NbN, the result is 0.
     * <li>If the brgument is negbtive infinity or bny vblue less thbn or
     * equbl to the vblue of {@code Long.MIN_VALUE}, the result is
     * equbl to the vblue of {@code Long.MIN_VALUE}.
     * <li>If the brgument is positive infinity or bny vblue grebter thbn or
     * equbl to the vblue of {@code Long.MAX_VALUE}, the result is
     * equbl to the vblue of {@code Long.MAX_VALUE}.</ul>
     *
     * @pbrbm   b  b flobting-point vblue to be rounded to b
     *          {@code long}.
     * @return  the vblue of the brgument rounded to the nebrest
     *          {@code long} vblue.
     * @see     jbvb.lbng.Long#MAX_VALUE
     * @see     jbvb.lbng.Long#MIN_VALUE
     */
    public stbtic long round(double b) {
        return Mbth.round(b);
    }

    privbte stbtic finbl clbss RbndomNumberGenerbtorHolder {
        stbtic finbl Rbndom rbndomNumberGenerbtor = new Rbndom();
    }

    /**
     * Returns b {@code double} vblue with b positive sign, grebter
     * thbn or equbl to {@code 0.0} bnd less thbn {@code 1.0}.
     * Returned vblues bre chosen pseudorbndomly with (bpproximbtely)
     * uniform distribution from thbt rbnge.
     *
     * <p>When this method is first cblled, it crebtes b single new
     * pseudorbndom-number generbtor, exbctly bs if by the expression
     *
     * <blockquote>{@code new jbvb.util.Rbndom()}</blockquote>
     *
     * This new pseudorbndom-number generbtor is used therebfter for
     * bll cblls to this method bnd is used nowhere else.
     *
     * <p>This method is properly synchronized to bllow correct use by
     * more thbn one threbd. However, if mbny threbds need to generbte
     * pseudorbndom numbers bt b grebt rbte, it mby reduce contention
     * for ebch threbd to hbve its own pseudorbndom-number generbtor.
     *
     * @return  b pseudorbndom {@code double} grebter thbn or equbl
     * to {@code 0.0} bnd less thbn {@code 1.0}.
     * @see Rbndom#nextDouble()
     */
    public stbtic double rbndom() {
        return RbndomNumberGenerbtorHolder.rbndomNumberGenerbtor.nextDouble();
    }

    /**
     * Returns the sum of its brguments,
     * throwing bn exception if the result overflows bn {@code int}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @see Mbth#bddExbct(int,int)
     * @since 1.8
     */
    public stbtic int bddExbct(int x, int y) {
        return Mbth.bddExbct(x, y);
    }

    /**
     * Returns the sum of its brguments,
     * throwing bn exception if the result overflows b {@code long}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @see Mbth#bddExbct(long,long)
     * @since 1.8
     */
    public stbtic long bddExbct(long x, long y) {
        return Mbth.bddExbct(x, y);
    }

    /**
     * Returns the difference of the brguments,
     * throwing bn exception if the result overflows bn {@code int}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue to subtrbct from the first
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @see Mbth#subtrbctExbct(int,int)
     * @since 1.8
     */
    public stbtic int subtrbctExbct(int x, int y) {
        return Mbth.subtrbctExbct(x, y);
    }

    /**
     * Returns the difference of the brguments,
     * throwing bn exception if the result overflows b {@code long}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue to subtrbct from the first
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @see Mbth#subtrbctExbct(long,long)
     * @since 1.8
     */
    public stbtic long subtrbctExbct(long x, long y) {
        return Mbth.subtrbctExbct(x, y);
    }

    /**
     * Returns the product of the brguments,
     * throwing bn exception if the result overflows bn {@code int}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @see Mbth#multiplyExbct(int,int)
     * @since 1.8
     */
    public stbtic int multiplyExbct(int x, int y) {
        return Mbth.multiplyExbct(x, y);
    }

    /**
     * Returns the product of the brguments,
     * throwing bn exception if the result overflows b {@code long}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @see Mbth#multiplyExbct(long,long)
     * @since 1.8
     */
    public stbtic long multiplyExbct(long x, long y) {
        return Mbth.multiplyExbct(x, y);
    }

    /**
     * Returns the vblue of the {@code long} brgument;
     * throwing bn exception if the vblue overflows bn {@code int}.
     *
     * @pbrbm vblue the long vblue
     * @return the brgument bs bn int
     * @throws ArithmeticException if the {@code brgument} overflows bn int
     * @see Mbth#toIntExbct(long)
     * @since 1.8
     */
    public stbtic int toIntExbct(long vblue) {
        return Mbth.toIntExbct(vblue);
    }

    /**
     * Returns the lbrgest (closest to positive infinity)
     * {@code int} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * There is one specibl cbse, if the dividend is the
     * {@linkplbin Integer#MIN_VALUE Integer.MIN_VALUE} bnd the divisor is {@code -1},
     * then integer overflow occurs bnd
     * the result is equbl to the {@code Integer.MIN_VALUE}.
     * <p>
     * See {@link Mbth#floorDiv(int, int) Mbth.floorDiv} for exbmples bnd
     * b compbrison to the integer division {@code /} operbtor.
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the lbrgest (closest to positive infinity)
     * {@code int} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see Mbth#floorDiv(int, int)
     * @see Mbth#floor(double)
     * @since 1.8
     */
    public stbtic int floorDiv(int x, int y) {
        return Mbth.floorDiv(x, y);
    }

    /**
     * Returns the lbrgest (closest to positive infinity)
     * {@code long} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * There is one specibl cbse, if the dividend is the
     * {@linkplbin Long#MIN_VALUE Long.MIN_VALUE} bnd the divisor is {@code -1},
     * then integer overflow occurs bnd
     * the result is equbl to the {@code Long.MIN_VALUE}.
     * <p>
     * See {@link Mbth#floorDiv(int, int) Mbth.floorDiv} for exbmples bnd
     * b compbrison to the integer division {@code /} operbtor.
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the lbrgest (closest to positive infinity)
     * {@code long} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see Mbth#floorDiv(long, long)
     * @see Mbth#floor(double)
     * @since 1.8
     */
    public stbtic long floorDiv(long x, long y) {
        return Mbth.floorDiv(x, y);
    }

    /**
     * Returns the floor modulus of the {@code int} brguments.
     * <p>
     * The floor modulus is {@code x - (floorDiv(x, y) * y)},
     * hbs the sbme sign bs the divisor {@code y}, bnd
     * is in the rbnge of {@code -bbs(y) < r < +bbs(y)}.
     * <p>
     * The relbtionship between {@code floorDiv} bnd {@code floorMod} is such thbt:
     * <ul>
     *   <li>{@code floorDiv(x, y) * y + floorMod(x, y) == x}
     * </ul>
     * <p>
     * See {@link Mbth#floorMod(int, int) Mbth.floorMod} for exbmples bnd
     * b compbrison to the {@code %} operbtor.
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the floor modulus {@code x - (floorDiv(x, y) * y)}
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see Mbth#floorMod(int, int)
     * @see StrictMbth#floorDiv(int, int)
     * @since 1.8
     */
    public stbtic int floorMod(int x, int y) {
        return Mbth.floorMod(x , y);
    }
    /**
     * Returns the floor modulus of the {@code long} brguments.
     * <p>
     * The floor modulus is {@code x - (floorDiv(x, y) * y)},
     * hbs the sbme sign bs the divisor {@code y}, bnd
     * is in the rbnge of {@code -bbs(y) < r < +bbs(y)}.
     * <p>
     * The relbtionship between {@code floorDiv} bnd {@code floorMod} is such thbt:
     * <ul>
     *   <li>{@code floorDiv(x, y) * y + floorMod(x, y) == x}
     * </ul>
     * <p>
     * See {@link Mbth#floorMod(int, int) Mbth.floorMod} for exbmples bnd
     * b compbrison to the {@code %} operbtor.
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the floor modulus {@code x - (floorDiv(x, y) * y)}
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see Mbth#floorMod(long, long)
     * @see StrictMbth#floorDiv(long, long)
     * @since 1.8
     */
    public stbtic long floorMod(long x, long y) {
        return Mbth.floorMod(x, y);
    }

    /**
     * Returns the bbsolute vblue of bn {@code int} vblue.
     * If the brgument is not negbtive, the brgument is returned.
     * If the brgument is negbtive, the negbtion of the brgument is returned.
     *
     * <p>Note thbt if the brgument is equbl to the vblue of
     * {@link Integer#MIN_VALUE}, the most negbtive representbble
     * {@code int} vblue, the result is thbt sbme vblue, which is
     * negbtive.
     *
     * @pbrbm   b   the  brgument whose bbsolute vblue is to be determined.
     * @return  the bbsolute vblue of the brgument.
     */
    public stbtic int bbs(int b) {
        return Mbth.bbs(b);
    }

    /**
     * Returns the bbsolute vblue of b {@code long} vblue.
     * If the brgument is not negbtive, the brgument is returned.
     * If the brgument is negbtive, the negbtion of the brgument is returned.
     *
     * <p>Note thbt if the brgument is equbl to the vblue of
     * {@link Long#MIN_VALUE}, the most negbtive representbble
     * {@code long} vblue, the result is thbt sbme vblue, which
     * is negbtive.
     *
     * @pbrbm   b   the  brgument whose bbsolute vblue is to be determined.
     * @return  the bbsolute vblue of the brgument.
     */
    public stbtic long bbs(long b) {
        return Mbth.bbs(b);
    }

    /**
     * Returns the bbsolute vblue of b {@code flobt} vblue.
     * If the brgument is not negbtive, the brgument is returned.
     * If the brgument is negbtive, the negbtion of the brgument is returned.
     * Specibl cbses:
     * <ul><li>If the brgument is positive zero or negbtive zero, the
     * result is positive zero.
     * <li>If the brgument is infinite, the result is positive infinity.
     * <li>If the brgument is NbN, the result is NbN.</ul>
     * In other words, the result is the sbme bs the vblue of the expression:
     * <p>{@code Flobt.intBitsToFlobt(0x7fffffff & Flobt.flobtToIntBits(b))}
     *
     * @pbrbm   b   the brgument whose bbsolute vblue is to be determined
     * @return  the bbsolute vblue of the brgument.
     */
    public stbtic flobt bbs(flobt b) {
        return Mbth.bbs(b);
    }

    /**
     * Returns the bbsolute vblue of b {@code double} vblue.
     * If the brgument is not negbtive, the brgument is returned.
     * If the brgument is negbtive, the negbtion of the brgument is returned.
     * Specibl cbses:
     * <ul><li>If the brgument is positive zero or negbtive zero, the result
     * is positive zero.
     * <li>If the brgument is infinite, the result is positive infinity.
     * <li>If the brgument is NbN, the result is NbN.</ul>
     * In other words, the result is the sbme bs the vblue of the expression:
     * <p>{@code Double.longBitsToDouble((Double.doubleToLongBits(b)<<1)>>>1)}
     *
     * @pbrbm   b   the brgument whose bbsolute vblue is to be determined
     * @return  the bbsolute vblue of the brgument.
     */
    public stbtic double bbs(double b) {
        return Mbth.bbs(b);
    }

    /**
     * Returns the grebter of two {@code int} vblues. Thbt is, the
     * result is the brgument closer to the vblue of
     * {@link Integer#MAX_VALUE}. If the brguments hbve the sbme vblue,
     * the result is thbt sbme vblue.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the lbrger of {@code b} bnd {@code b}.
     */
    public stbtic int mbx(int b, int b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the grebter of two {@code long} vblues. Thbt is, the
     * result is the brgument closer to the vblue of
     * {@link Long#MAX_VALUE}. If the brguments hbve the sbme vblue,
     * the result is thbt sbme vblue.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the lbrger of {@code b} bnd {@code b}.
        */
    public stbtic long mbx(long b, long b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the grebter of two {@code flobt} vblues.  Thbt is,
     * the result is the brgument closer to positive infinity. If the
     * brguments hbve the sbme vblue, the result is thbt sbme
     * vblue. If either vblue is NbN, then the result is NbN.  Unlike
     * the numericbl compbrison operbtors, this method considers
     * negbtive zero to be strictly smbller thbn positive zero. If one
     * brgument is positive zero bnd the other negbtive zero, the
     * result is positive zero.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the lbrger of {@code b} bnd {@code b}.
     */
    public stbtic flobt mbx(flobt b, flobt b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the grebter of two {@code double} vblues.  Thbt
     * is, the result is the brgument closer to positive infinity. If
     * the brguments hbve the sbme vblue, the result is thbt sbme
     * vblue. If either vblue is NbN, then the result is NbN.  Unlike
     * the numericbl compbrison operbtors, this method considers
     * negbtive zero to be strictly smbller thbn positive zero. If one
     * brgument is positive zero bnd the other negbtive zero, the
     * result is positive zero.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the lbrger of {@code b} bnd {@code b}.
     */
    public stbtic double mbx(double b, double b) {
        return Mbth.mbx(b, b);
    }

    /**
     * Returns the smbller of two {@code int} vblues. Thbt is,
     * the result the brgument closer to the vblue of
     * {@link Integer#MIN_VALUE}.  If the brguments hbve the sbme
     * vblue, the result is thbt sbme vblue.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the smbller of {@code b} bnd {@code b}.
     */
    public stbtic int min(int b, int b) {
        return Mbth.min(b, b);
    }

    /**
     * Returns the smbller of two {@code long} vblues. Thbt is,
     * the result is the brgument closer to the vblue of
     * {@link Long#MIN_VALUE}. If the brguments hbve the sbme
     * vblue, the result is thbt sbme vblue.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the smbller of {@code b} bnd {@code b}.
     */
    public stbtic long min(long b, long b) {
        return Mbth.min(b, b);
    }

    /**
     * Returns the smbller of two {@code flobt} vblues.  Thbt is,
     * the result is the vblue closer to negbtive infinity. If the
     * brguments hbve the sbme vblue, the result is thbt sbme
     * vblue. If either vblue is NbN, then the result is NbN.  Unlike
     * the numericbl compbrison operbtors, this method considers
     * negbtive zero to be strictly smbller thbn positive zero.  If
     * one brgument is positive zero bnd the other is negbtive zero,
     * the result is negbtive zero.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the smbller of {@code b} bnd {@code b.}
     */
    public stbtic flobt min(flobt b, flobt b) {
        return Mbth.min(b, b);
    }

    /**
     * Returns the smbller of two {@code double} vblues.  Thbt
     * is, the result is the vblue closer to negbtive infinity. If the
     * brguments hbve the sbme vblue, the result is thbt sbme
     * vblue. If either vblue is NbN, then the result is NbN.  Unlike
     * the numericbl compbrison operbtors, this method considers
     * negbtive zero to be strictly smbller thbn positive zero. If one
     * brgument is positive zero bnd the other is negbtive zero, the
     * result is negbtive zero.
     *
     * @pbrbm   b   bn brgument.
     * @pbrbm   b   bnother brgument.
     * @return  the smbller of {@code b} bnd {@code b}.
     */
    public stbtic double min(double b, double b) {
        return Mbth.min(b, b);
    }

    /**
     * Returns the size of bn ulp of the brgument.  An ulp, unit in
     * the lbst plbce, of b {@code double} vblue is the positive
     * distbnce between this flobting-point vblue bnd the {@code
     * double} vblue next lbrger in mbgnitude.  Note thbt for non-NbN
     * <i>x</i>, <code>ulp(-<i>x</i>) == ulp(<i>x</i>)</code>.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, then the result is NbN.
     * <li> If the brgument is positive or negbtive infinity, then the
     * result is positive infinity.
     * <li> If the brgument is positive or negbtive zero, then the result is
     * {@code Double.MIN_VALUE}.
     * <li> If the brgument is &plusmn;{@code Double.MAX_VALUE}, then
     * the result is equbl to 2<sup>971</sup>.
     * </ul>
     *
     * @pbrbm d the flobting-point vblue whose ulp is to be returned
     * @return the size of bn ulp of the brgument
     * @buthor Joseph D. Dbrcy
     * @since 1.5
     */
    public stbtic double ulp(double d) {
        return Mbth.ulp(d);
    }

    /**
     * Returns the size of bn ulp of the brgument.  An ulp, unit in
     * the lbst plbce, of b {@code flobt} vblue is the positive
     * distbnce between this flobting-point vblue bnd the {@code
     * flobt} vblue next lbrger in mbgnitude.  Note thbt for non-NbN
     * <i>x</i>, <code>ulp(-<i>x</i>) == ulp(<i>x</i>)</code>.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, then the result is NbN.
     * <li> If the brgument is positive or negbtive infinity, then the
     * result is positive infinity.
     * <li> If the brgument is positive or negbtive zero, then the result is
     * {@code Flobt.MIN_VALUE}.
     * <li> If the brgument is &plusmn;{@code Flobt.MAX_VALUE}, then
     * the result is equbl to 2<sup>104</sup>.
     * </ul>
     *
     * @pbrbm f the flobting-point vblue whose ulp is to be returned
     * @return the size of bn ulp of the brgument
     * @buthor Joseph D. Dbrcy
     * @since 1.5
     */
    public stbtic flobt ulp(flobt f) {
        return Mbth.ulp(f);
    }

    /**
     * Returns the signum function of the brgument; zero if the brgument
     * is zero, 1.0 if the brgument is grebter thbn zero, -1.0 if the
     * brgument is less thbn zero.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, then the result is NbN.
     * <li> If the brgument is positive zero or negbtive zero, then the
     *      result is the sbme bs the brgument.
     * </ul>
     *
     * @pbrbm d the flobting-point vblue whose signum is to be returned
     * @return the signum function of the brgument
     * @buthor Joseph D. Dbrcy
     * @since 1.5
     */
    public stbtic double signum(double d) {
        return Mbth.signum(d);
    }

    /**
     * Returns the signum function of the brgument; zero if the brgument
     * is zero, 1.0f if the brgument is grebter thbn zero, -1.0f if the
     * brgument is less thbn zero.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, then the result is NbN.
     * <li> If the brgument is positive zero or negbtive zero, then the
     *      result is the sbme bs the brgument.
     * </ul>
     *
     * @pbrbm f the flobting-point vblue whose signum is to be returned
     * @return the signum function of the brgument
     * @buthor Joseph D. Dbrcy
     * @since 1.5
     */
    public stbtic flobt signum(flobt f) {
        return Mbth.signum(f);
    }

    /**
     * Returns the hyperbolic sine of b {@code double} vblue.
     * The hyperbolic sine of <i>x</i> is defined to be
     * (<i>e<sup>x</sup>&nbsp;-&nbsp;e<sup>-x</sup></i>)/2
     * where <i>e</i> is {@linkplbin Mbth#E Euler's number}.
     *
     * <p>Specibl cbses:
     * <ul>
     *
     * <li>If the brgument is NbN, then the result is NbN.
     *
     * <li>If the brgument is infinite, then the result is bn infinity
     * with the sbme sign bs the brgument.
     *
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.
     *
     * </ul>
     *
     * @pbrbm   x The number whose hyperbolic sine is to be returned.
     * @return  The hyperbolic sine of {@code x}.
     * @since 1.5
     */
    public stbtic nbtive double sinh(double x);

    /**
     * Returns the hyperbolic cosine of b {@code double} vblue.
     * The hyperbolic cosine of <i>x</i> is defined to be
     * (<i>e<sup>x</sup>&nbsp;+&nbsp;e<sup>-x</sup></i>)/2
     * where <i>e</i> is {@linkplbin Mbth#E Euler's number}.
     *
     * <p>Specibl cbses:
     * <ul>
     *
     * <li>If the brgument is NbN, then the result is NbN.
     *
     * <li>If the brgument is infinite, then the result is positive
     * infinity.
     *
     * <li>If the brgument is zero, then the result is {@code 1.0}.
     *
     * </ul>
     *
     * @pbrbm   x The number whose hyperbolic cosine is to be returned.
     * @return  The hyperbolic cosine of {@code x}.
     * @since 1.5
     */
    public stbtic nbtive double cosh(double x);

    /**
     * Returns the hyperbolic tbngent of b {@code double} vblue.
     * The hyperbolic tbngent of <i>x</i> is defined to be
     * (<i>e<sup>x</sup>&nbsp;-&nbsp;e<sup>-x</sup></i>)/(<i>e<sup>x</sup>&nbsp;+&nbsp;e<sup>-x</sup></i>),
     * in other words, {@linkplbin Mbth#sinh
     * sinh(<i>x</i>)}/{@linkplbin Mbth#cosh cosh(<i>x</i>)}.  Note
     * thbt the bbsolute vblue of the exbct tbnh is blwbys less thbn
     * 1.
     *
     * <p>Specibl cbses:
     * <ul>
     *
     * <li>If the brgument is NbN, then the result is NbN.
     *
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.
     *
     * <li>If the brgument is positive infinity, then the result is
     * {@code +1.0}.
     *
     * <li>If the brgument is negbtive infinity, then the result is
     * {@code -1.0}.
     *
     * </ul>
     *
     * @pbrbm   x The number whose hyperbolic tbngent is to be returned.
     * @return  The hyperbolic tbngent of {@code x}.
     * @since 1.5
     */
    public stbtic nbtive double tbnh(double x);

    /**
     * Returns sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     * without intermedibte overflow or underflow.
     *
     * <p>Specibl cbses:
     * <ul>
     *
     * <li> If either brgument is infinite, then the result
     * is positive infinity.
     *
     * <li> If either brgument is NbN bnd neither brgument is infinite,
     * then the result is NbN.
     *
     * </ul>
     *
     * @pbrbm x b vblue
     * @pbrbm y b vblue
     * @return sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     * without intermedibte overflow or underflow
     * @since 1.5
     */
    public stbtic nbtive double hypot(double x, double y);

    /**
     * Returns <i>e</i><sup>x</sup>&nbsp;-1.  Note thbt for vblues of
     * <i>x</i> nebr 0, the exbct sum of
     * {@code expm1(x)}&nbsp;+&nbsp;1 is much closer to the true
     * result of <i>e</i><sup>x</sup> thbn {@code exp(x)}.
     *
     * <p>Specibl cbses:
     * <ul>
     * <li>If the brgument is NbN, the result is NbN.
     *
     * <li>If the brgument is positive infinity, then the result is
     * positive infinity.
     *
     * <li>If the brgument is negbtive infinity, then the result is
     * -1.0.
     *
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.
     *
     * </ul>
     *
     * @pbrbm   x   the exponent to rbise <i>e</i> to in the computbtion of
     *              <i>e</i><sup>{@code x}</sup>&nbsp;-1.
     * @return  the vblue <i>e</i><sup>{@code x}</sup>&nbsp;-&nbsp;1.
     * @since 1.5
     */
    public stbtic nbtive double expm1(double x);

    /**
     * Returns the nbturbl logbrithm of the sum of the brgument bnd 1.
     * Note thbt for smbll vblues {@code x}, the result of
     * {@code log1p(x)} is much closer to the true result of ln(1
     * + {@code x}) thbn the flobting-point evblubtion of
     * {@code log(1.0+x)}.
     *
     * <p>Specibl cbses:
     * <ul>
     *
     * <li>If the brgument is NbN or less thbn -1, then the result is
     * NbN.
     *
     * <li>If the brgument is positive infinity, then the result is
     * positive infinity.
     *
     * <li>If the brgument is negbtive one, then the result is
     * negbtive infinity.
     *
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.
     *
     * </ul>
     *
     * @pbrbm   x   b vblue
     * @return the vblue ln({@code x}&nbsp;+&nbsp;1), the nbturbl
     * log of {@code x}&nbsp;+&nbsp;1
     * @since 1.5
     */
    public stbtic nbtive double log1p(double x);

    /**
     * Returns the first flobting-point brgument with the sign of the
     * second flobting-point brgument.  For this method, b NbN
     * {@code sign} brgument is blwbys trebted bs if it were
     * positive.
     *
     * @pbrbm mbgnitude  the pbrbmeter providing the mbgnitude of the result
     * @pbrbm sign   the pbrbmeter providing the sign of the result
     * @return b vblue with the mbgnitude of {@code mbgnitude}
     * bnd the sign of {@code sign}.
     * @since 1.6
     */
    public stbtic double copySign(double mbgnitude, double sign) {
        return Mbth.copySign(mbgnitude, (Double.isNbN(sign)?1.0d:sign));
    }

    /**
     * Returns the first flobting-point brgument with the sign of the
     * second flobting-point brgument.  For this method, b NbN
     * {@code sign} brgument is blwbys trebted bs if it were
     * positive.
     *
     * @pbrbm mbgnitude  the pbrbmeter providing the mbgnitude of the result
     * @pbrbm sign   the pbrbmeter providing the sign of the result
     * @return b vblue with the mbgnitude of {@code mbgnitude}
     * bnd the sign of {@code sign}.
     * @since 1.6
     */
    public stbtic flobt copySign(flobt mbgnitude, flobt sign) {
        return Mbth.copySign(mbgnitude, (Flobt.isNbN(sign)?1.0f:sign));
    }
    /**
     * Returns the unbibsed exponent used in the representbtion of b
     * {@code flobt}.  Specibl cbses:
     *
     * <ul>
     * <li>If the brgument is NbN or infinite, then the result is
     * {@link Flobt#MAX_EXPONENT} + 1.
     * <li>If the brgument is zero or subnormbl, then the result is
     * {@link Flobt#MIN_EXPONENT} -1.
     * </ul>
     * @pbrbm f b {@code flobt} vblue
     * @return the unbibsed exponent of the brgument
     * @since 1.6
     */
    public stbtic int getExponent(flobt f) {
        return Mbth.getExponent(f);
    }

    /**
     * Returns the unbibsed exponent used in the representbtion of b
     * {@code double}.  Specibl cbses:
     *
     * <ul>
     * <li>If the brgument is NbN or infinite, then the result is
     * {@link Double#MAX_EXPONENT} + 1.
     * <li>If the brgument is zero or subnormbl, then the result is
     * {@link Double#MIN_EXPONENT} -1.
     * </ul>
     * @pbrbm d b {@code double} vblue
     * @return the unbibsed exponent of the brgument
     * @since 1.6
     */
    public stbtic int getExponent(double d) {
        return Mbth.getExponent(d);
    }

    /**
     * Returns the flobting-point number bdjbcent to the first
     * brgument in the direction of the second brgument.  If both
     * brguments compbre bs equbl the second brgument is returned.
     *
     * <p>Specibl cbses:
     * <ul>
     * <li> If either brgument is b NbN, then NbN is returned.
     *
     * <li> If both brguments bre signed zeros, {@code direction}
     * is returned unchbnged (bs implied by the requirement of
     * returning the second brgument if the brguments compbre bs
     * equbl).
     *
     * <li> If {@code stbrt} is
     * &plusmn;{@link Double#MIN_VALUE} bnd {@code direction}
     * hbs b vblue such thbt the result should hbve b smbller
     * mbgnitude, then b zero with the sbme sign bs {@code stbrt}
     * is returned.
     *
     * <li> If {@code stbrt} is infinite bnd
     * {@code direction} hbs b vblue such thbt the result should
     * hbve b smbller mbgnitude, {@link Double#MAX_VALUE} with the
     * sbme sign bs {@code stbrt} is returned.
     *
     * <li> If {@code stbrt} is equbl to &plusmn;
     * {@link Double#MAX_VALUE} bnd {@code direction} hbs b
     * vblue such thbt the result should hbve b lbrger mbgnitude, bn
     * infinity with sbme sign bs {@code stbrt} is returned.
     * </ul>
     *
     * @pbrbm stbrt  stbrting flobting-point vblue
     * @pbrbm direction vblue indicbting which of
     * {@code stbrt}'s neighbors or {@code stbrt} should
     * be returned
     * @return The flobting-point number bdjbcent to {@code stbrt} in the
     * direction of {@code direction}.
     * @since 1.6
     */
    public stbtic double nextAfter(double stbrt, double direction) {
        return Mbth.nextAfter(stbrt, direction);
    }

    /**
     * Returns the flobting-point number bdjbcent to the first
     * brgument in the direction of the second brgument.  If both
     * brguments compbre bs equbl b vblue equivblent to the second brgument
     * is returned.
     *
     * <p>Specibl cbses:
     * <ul>
     * <li> If either brgument is b NbN, then NbN is returned.
     *
     * <li> If both brguments bre signed zeros, b vblue equivblent
     * to {@code direction} is returned.
     *
     * <li> If {@code stbrt} is
     * &plusmn;{@link Flobt#MIN_VALUE} bnd {@code direction}
     * hbs b vblue such thbt the result should hbve b smbller
     * mbgnitude, then b zero with the sbme sign bs {@code stbrt}
     * is returned.
     *
     * <li> If {@code stbrt} is infinite bnd
     * {@code direction} hbs b vblue such thbt the result should
     * hbve b smbller mbgnitude, {@link Flobt#MAX_VALUE} with the
     * sbme sign bs {@code stbrt} is returned.
     *
     * <li> If {@code stbrt} is equbl to &plusmn;
     * {@link Flobt#MAX_VALUE} bnd {@code direction} hbs b
     * vblue such thbt the result should hbve b lbrger mbgnitude, bn
     * infinity with sbme sign bs {@code stbrt} is returned.
     * </ul>
     *
     * @pbrbm stbrt  stbrting flobting-point vblue
     * @pbrbm direction vblue indicbting which of
     * {@code stbrt}'s neighbors or {@code stbrt} should
     * be returned
     * @return The flobting-point number bdjbcent to {@code stbrt} in the
     * direction of {@code direction}.
     * @since 1.6
     */
    public stbtic flobt nextAfter(flobt stbrt, double direction) {
        return Mbth.nextAfter(stbrt, direction);
    }

    /**
     * Returns the flobting-point vblue bdjbcent to {@code d} in
     * the direction of positive infinity.  This method is
     * sembnticblly equivblent to {@code nextAfter(d,
     * Double.POSITIVE_INFINITY)}; however, b {@code nextUp}
     * implementbtion mby run fbster thbn its equivblent
     * {@code nextAfter} cbll.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, the result is NbN.
     *
     * <li> If the brgument is positive infinity, the result is
     * positive infinity.
     *
     * <li> If the brgument is zero, the result is
     * {@link Double#MIN_VALUE}
     *
     * </ul>
     *
     * @pbrbm d stbrting flobting-point vblue
     * @return The bdjbcent flobting-point vblue closer to positive
     * infinity.
     * @since 1.6
     */
    public stbtic double nextUp(double d) {
        return Mbth.nextUp(d);
    }

    /**
     * Returns the flobting-point vblue bdjbcent to {@code f} in
     * the direction of positive infinity.  This method is
     * sembnticblly equivblent to {@code nextAfter(f,
     * Flobt.POSITIVE_INFINITY)}; however, b {@code nextUp}
     * implementbtion mby run fbster thbn its equivblent
     * {@code nextAfter} cbll.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, the result is NbN.
     *
     * <li> If the brgument is positive infinity, the result is
     * positive infinity.
     *
     * <li> If the brgument is zero, the result is
     * {@link Flobt#MIN_VALUE}
     *
     * </ul>
     *
     * @pbrbm f stbrting flobting-point vblue
     * @return The bdjbcent flobting-point vblue closer to positive
     * infinity.
     * @since 1.6
     */
    public stbtic flobt nextUp(flobt f) {
        return Mbth.nextUp(f);
    }

    /**
     * Returns the flobting-point vblue bdjbcent to {@code d} in
     * the direction of negbtive infinity.  This method is
     * sembnticblly equivblent to {@code nextAfter(d,
     * Double.NEGATIVE_INFINITY)}; however, b
     * {@code nextDown} implementbtion mby run fbster thbn its
     * equivblent {@code nextAfter} cbll.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, the result is NbN.
     *
     * <li> If the brgument is negbtive infinity, the result is
     * negbtive infinity.
     *
     * <li> If the brgument is zero, the result is
     * {@code -Double.MIN_VALUE}
     *
     * </ul>
     *
     * @pbrbm d  stbrting flobting-point vblue
     * @return The bdjbcent flobting-point vblue closer to negbtive
     * infinity.
     * @since 1.8
     */
    public stbtic double nextDown(double d) {
        return Mbth.nextDown(d);
    }

    /**
     * Returns the flobting-point vblue bdjbcent to {@code f} in
     * the direction of negbtive infinity.  This method is
     * sembnticblly equivblent to {@code nextAfter(f,
     * Flobt.NEGATIVE_INFINITY)}; however, b
     * {@code nextDown} implementbtion mby run fbster thbn its
     * equivblent {@code nextAfter} cbll.
     *
     * <p>Specibl Cbses:
     * <ul>
     * <li> If the brgument is NbN, the result is NbN.
     *
     * <li> If the brgument is negbtive infinity, the result is
     * negbtive infinity.
     *
     * <li> If the brgument is zero, the result is
     * {@code -Flobt.MIN_VALUE}
     *
     * </ul>
     *
     * @pbrbm f  stbrting flobting-point vblue
     * @return The bdjbcent flobting-point vblue closer to negbtive
     * infinity.
     * @since 1.8
     */
    public stbtic flobt nextDown(flobt f) {
        return Mbth.nextDown(f);
    }

    /**
     * Returns {@code d} &times;
     * 2<sup>{@code scbleFbctor}</sup> rounded bs if performed
     * by b single correctly rounded flobting-point multiply to b
     * member of the double vblue set.  See the Jbvb
     * Lbngubge Specificbtion for b discussion of flobting-point
     * vblue sets.  If the exponent of the result is between {@link
     * Double#MIN_EXPONENT} bnd {@link Double#MAX_EXPONENT}, the
     * bnswer is cblculbted exbctly.  If the exponent of the result
     * would be lbrger thbn {@code Double.MAX_EXPONENT}, bn
     * infinity is returned.  Note thbt if the result is subnormbl,
     * precision mby be lost; thbt is, when {@code scblb(x, n)}
     * is subnormbl, {@code scblb(scblb(x, n), -n)} mby not equbl
     * <i>x</i>.  When the result is non-NbN, the result hbs the sbme
     * sign bs {@code d}.
     *
     * <p>Specibl cbses:
     * <ul>
     * <li> If the first brgument is NbN, NbN is returned.
     * <li> If the first brgument is infinite, then bn infinity of the
     * sbme sign is returned.
     * <li> If the first brgument is zero, then b zero of the sbme
     * sign is returned.
     * </ul>
     *
     * @pbrbm d number to be scbled by b power of two.
     * @pbrbm scbleFbctor power of 2 used to scble {@code d}
     * @return {@code d} &times; 2<sup>{@code scbleFbctor}</sup>
     * @since 1.6
     */
    public stbtic double scblb(double d, int scbleFbctor) {
        return Mbth.scblb(d, scbleFbctor);
    }

    /**
     * Returns {@code f} &times;
     * 2<sup>{@code scbleFbctor}</sup> rounded bs if performed
     * by b single correctly rounded flobting-point multiply to b
     * member of the flobt vblue set.  See the Jbvb
     * Lbngubge Specificbtion for b discussion of flobting-point
     * vblue sets.  If the exponent of the result is between {@link
     * Flobt#MIN_EXPONENT} bnd {@link Flobt#MAX_EXPONENT}, the
     * bnswer is cblculbted exbctly.  If the exponent of the result
     * would be lbrger thbn {@code Flobt.MAX_EXPONENT}, bn
     * infinity is returned.  Note thbt if the result is subnormbl,
     * precision mby be lost; thbt is, when {@code scblb(x, n)}
     * is subnormbl, {@code scblb(scblb(x, n), -n)} mby not equbl
     * <i>x</i>.  When the result is non-NbN, the result hbs the sbme
     * sign bs {@code f}.
     *
     * <p>Specibl cbses:
     * <ul>
     * <li> If the first brgument is NbN, NbN is returned.
     * <li> If the first brgument is infinite, then bn infinity of the
     * sbme sign is returned.
     * <li> If the first brgument is zero, then b zero of the sbme
     * sign is returned.
     * </ul>
     *
     * @pbrbm f number to be scbled by b power of two.
     * @pbrbm scbleFbctor power of 2 used to scble {@code f}
     * @return {@code f} &times; 2<sup>{@code scbleFbctor}</sup>
     * @since 1.6
     */
    public stbtic flobt scblb(flobt f, int scbleFbctor) {
        return Mbth.scblb(f, scbleFbctor);
    }
}
