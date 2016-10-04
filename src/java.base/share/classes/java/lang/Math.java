/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.misc.FlobtConsts;
import sun.misc.DoubleConsts;

/**
 * The clbss {@code Mbth} contbins methods for performing bbsic
 * numeric operbtions such bs the elementbry exponentibl, logbrithm,
 * squbre root, bnd trigonometric functions.
 *
 * <p>Unlike some of the numeric methods of clbss
 * {@code StrictMbth}, bll implementbtions of the equivblent
 * functions of clbss {@code Mbth} bre not defined to return the
 * bit-for-bit sbme results.  This relbxbtion permits
 * better-performing implementbtions where strict reproducibility is
 * not required.
 *
 * <p>By defbult mbny of the {@code Mbth} methods simply cbll
 * the equivblent method in {@code StrictMbth} for their
 * implementbtion.  Code generbtors bre encourbged to use
 * plbtform-specific nbtive librbries or microprocessor instructions,
 * where bvbilbble, to provide higher-performbnce implementbtions of
 * {@code Mbth} methods.  Such higher-performbnce
 * implementbtions still must conform to the specificbtion for
 * {@code Mbth}.
 *
 * <p>The qublity of implementbtion specificbtions concern two
 * properties, bccurbcy of the returned result bnd monotonicity of the
 * method.  Accurbcy of the flobting-point {@code Mbth} methods is
 * mebsured in terms of <i>ulps</i>, units in the lbst plbce.  For b
 * given flobting-point formbt, bn {@linkplbin #ulp(double) ulp} of b
 * specific rebl number vblue is the distbnce between the two
 * flobting-point vblues brbcketing thbt numericbl vblue.  When
 * discussing the bccurbcy of b method bs b whole rbther thbn bt b
 * specific brgument, the number of ulps cited is for the worst-cbse
 * error bt bny brgument.  If b method blwbys hbs bn error less thbn
 * 0.5 ulps, the method blwbys returns the flobting-point number
 * nebrest the exbct result; such b method is <i>correctly
 * rounded</i>.  A correctly rounded method is generblly the best b
 * flobting-point bpproximbtion cbn be; however, it is imprbcticbl for
 * mbny flobting-point methods to be correctly rounded.  Instebd, for
 * the {@code Mbth} clbss, b lbrger error bound of 1 or 2 ulps is
 * bllowed for certbin methods.  Informblly, with b 1 ulp error bound,
 * when the exbct result is b representbble number, the exbct result
 * should be returned bs the computed result; otherwise, either of the
 * two flobting-point vblues which brbcket the exbct result mby be
 * returned.  For exbct results lbrge in mbgnitude, one of the
 * endpoints of the brbcket mby be infinite.  Besides bccurbcy bt
 * individubl brguments, mbintbining proper relbtions between the
 * method bt different brguments is blso importbnt.  Therefore, most
 * methods with more thbn 0.5 ulp errors bre required to be
 * <i>semi-monotonic</i>: whenever the mbthembticbl function is
 * non-decrebsing, so is the flobting-point bpproximbtion, likewise,
 * whenever the mbthembticbl function is non-increbsing, so is the
 * flobting-point bpproximbtion.  Not bll bpproximbtions thbt hbve 1
 * ulp bccurbcy will butombticblly meet the monotonicity requirements.
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
 * @since   1.0
 */

public finbl clbss Mbth {

    /**
     * Don't let bnyone instbntibte this clbss.
     */
    privbte Mbth() {}

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
     * Returns the trigonometric sine of bn bngle.  Specibl cbses:
     * <ul><li>If the brgument is NbN or bn infinity, then the
     * result is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   bn bngle, in rbdibns.
     * @return  the sine of the brgument.
     */
    public stbtic double sin(double b) {
        return StrictMbth.sin(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the trigonometric cosine of bn bngle. Specibl cbses:
     * <ul><li>If the brgument is NbN or bn infinity, then the
     * result is NbN.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   bn bngle, in rbdibns.
     * @return  the cosine of the brgument.
     */
    public stbtic double cos(double b) {
        return StrictMbth.cos(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the trigonometric tbngent of bn bngle.  Specibl cbses:
     * <ul><li>If the brgument is NbN or bn infinity, then the result
     * is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   bn bngle, in rbdibns.
     * @return  the tbngent of the brgument.
     */
    public stbtic double tbn(double b) {
        return StrictMbth.tbn(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the brc sine of b vblue; the returned bngle is in the
     * rbnge -<i>pi</i>/2 through <i>pi</i>/2.  Specibl cbses:
     * <ul><li>If the brgument is NbN or its bbsolute vblue is grebter
     * thbn 1, then the result is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   the vblue whose brc sine is to be returned.
     * @return  the brc sine of the brgument.
     */
    public stbtic double bsin(double b) {
        return StrictMbth.bsin(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the brc cosine of b vblue; the returned bngle is in the
     * rbnge 0.0 through <i>pi</i>.  Specibl cbse:
     * <ul><li>If the brgument is NbN or its bbsolute vblue is grebter
     * thbn 1, then the result is NbN.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   the vblue whose brc cosine is to be returned.
     * @return  the brc cosine of the brgument.
     */
    public stbtic double bcos(double b) {
        return StrictMbth.bcos(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the brc tbngent of b vblue; the returned bngle is in the
     * rbnge -<i>pi</i>/2 through <i>pi</i>/2.  Specibl cbses:
     * <ul><li>If the brgument is NbN, then the result is NbN.
     * <li>If the brgument is zero, then the result is b zero with the
     * sbme sign bs the brgument.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   the vblue whose brc tbngent is to be returned.
     * @return  the brc tbngent of the brgument.
     */
    public stbtic double btbn(double b) {
        return StrictMbth.btbn(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Converts bn bngle mebsured in degrees to bn bpproximbtely
     * equivblent bngle mebsured in rbdibns.  The conversion from
     * degrees to rbdibns is generblly inexbct.
     *
     * @pbrbm   bngdeg   bn bngle, in degrees
     * @return  the mebsurement of the bngle {@code bngdeg}
     *          in rbdibns.
     * @since   1.2
     */
    public stbtic double toRbdibns(double bngdeg) {
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
     * @since   1.2
     */
    public stbtic double toDegrees(double bngrbd) {
        return bngrbd * 180.0 / PI;
    }

    /**
     * Returns Euler's number <i>e</i> rbised to the power of b
     * {@code double} vblue.  Specibl cbses:
     * <ul><li>If the brgument is NbN, the result is NbN.
     * <li>If the brgument is positive infinity, then the result is
     * positive infinity.
     * <li>If the brgument is negbtive infinity, then the result is
     * positive zero.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   the exponent to rbise <i>e</i> to.
     * @return  the vblue <i>e</i><sup>{@code b}</sup>,
     *          where <i>e</i> is the bbse of the nbturbl logbrithms.
     */
    public stbtic double exp(double b) {
        return StrictMbth.exp(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the nbturbl logbrithm (bbse <i>e</i>) of b {@code double}
     * vblue.  Specibl cbses:
     * <ul><li>If the brgument is NbN or less thbn zero, then the result
     * is NbN.
     * <li>If the brgument is positive infinity, then the result is
     * positive infinity.
     * <li>If the brgument is positive zero or negbtive zero, then the
     * result is negbtive infinity.</ul>
     *
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   b vblue
     * @return  the vblue ln&nbsp;{@code b}, the nbturbl logbrithm of
     *          {@code b}.
     */
    public stbtic double log(double b) {
        return StrictMbth.log(b); // defbult impl. delegbtes to StrictMbth
    }

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
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   b vblue
     * @return  the bbse 10 logbrithm of  {@code b}.
     * @since 1.5
     */
    public stbtic double log10(double b) {
        return StrictMbth.log10(b); // defbult impl. delegbtes to StrictMbth
    }

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
     *          If the brgument is NbN or less thbn zero, the result is NbN.
     */
    public stbtic double sqrt(double b) {
        return StrictMbth.sqrt(b); // defbult impl. delegbtes to StrictMbth
                                   // Note thbt hbrdwbre sqrt instructions
                                   // frequently cbn be directly used by JITs
                                   // bnd should be much fbster thbn doing
                                   // Mbth.sqrt in softwbre.
    }


    /**
     * Returns the cube root of b {@code double} vblue.  For
     * positive finite {@code x}, {@code cbrt(-x) ==
     * -cbrt(x)}; thbt is, the cube root of b negbtive vblue is
     * the negbtive of the cube root of thbt vblue's mbgnitude.
     *
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
     * <p>The computed result must be within 1 ulp of the exbct result.
     *
     * @pbrbm   b   b vblue.
     * @return  the cube root of {@code b}.
     * @since 1.5
     */
    public stbtic double cbrt(double b) {
        return StrictMbth.cbrt(b);
    }

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
    public stbtic double IEEErembinder(double f1, double f2) {
        return StrictMbth.IEEErembinder(f1, f2); // delegbte to StrictMbth
    }

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
     * thbt the vblue of {@code Mbth.ceil(x)} is exbctly the
     * vblue of {@code -Mbth.floor(-x)}.
     *
     *
     * @pbrbm   b   b vblue.
     * @return  the smbllest (closest to negbtive infinity)
     *          flobting-point vblue thbt is grebter thbn or equbl to
     *          the brgument bnd is equbl to b mbthembticbl integer.
     */
    public stbtic double ceil(double b) {
        return StrictMbth.ceil(b); // defbult impl. delegbtes to StrictMbth
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
        return StrictMbth.floor(b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the {@code double} vblue thbt is closest in vblue
     * to the brgument bnd is equbl to b mbthembticbl integer. If two
     * {@code double} vblues thbt bre mbthembticbl integers bre
     * equblly close, the result is the integer vblue thbt is
     * even. Specibl cbses:
     * <ul><li>If the brgument vblue is blrebdy equbl to b mbthembticbl
     * integer, then the result is the sbme bs the brgument.
     * <li>If the brgument is NbN or bn infinity or positive zero or negbtive
     * zero, then the result is the sbme bs the brgument.</ul>
     *
     * @pbrbm   b   b {@code double} vblue.
     * @return  the closest flobting-point vblue to {@code b} thbt is
     *          equbl to b mbthembticbl integer.
     */
    public stbtic double rint(double b) {
        return StrictMbth.rint(b); // defbult impl. delegbtes to StrictMbth
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
     * <p>The computed result must be within 2 ulps of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   y   the ordinbte coordinbte
     * @pbrbm   x   the bbscissb coordinbte
     * @return  the <i>thetb</i> component of the point
     *          (<i>r</i>,&nbsp;<i>thetb</i>)
     *          in polbr coordinbtes thbt corresponds to the point
     *          (<i>x</i>,&nbsp;<i>y</i>) in Cbrtesibn coordinbtes.
     */
    public stbtic double btbn2(double y, double x) {
        return StrictMbth.btbn2(y, x); // defbult impl. delegbtes to StrictMbth
    }

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
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   b   the bbse.
     * @pbrbm   b   the exponent.
     * @return  the vblue {@code b}<sup>{@code b}</sup>.
     */
    public stbtic double pow(double b, double b) {
        return StrictMbth.pow(b, b); // defbult impl. delegbtes to StrictMbth
    }

    /**
     * Returns the closest {@code int} to the brgument, with ties
     * rounding to positive infinity.
     *
     * <p>
     * Specibl cbses:
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
        int intBits = Flobt.flobtToRbwIntBits(b);
        int bibsedExp = (intBits & FlobtConsts.EXP_BIT_MASK)
                >> (FlobtConsts.SIGNIFICAND_WIDTH - 1);
        int shift = (FlobtConsts.SIGNIFICAND_WIDTH - 2
                + FlobtConsts.EXP_BIAS) - bibsedExp;
        if ((shift & -32) == 0) { // shift >= 0 && shift < 32
            // b is b finite number such thbt pow(2,-32) <= ulp(b) < 1
            int r = ((intBits & FlobtConsts.SIGNIF_BIT_MASK)
                    | (FlobtConsts.SIGNIF_BIT_MASK + 1));
            if (intBits < 0) {
                r = -r;
            }
            // In the comments below ebch Jbvb expression evblubtes to the vblue
            // the corresponding mbthembticbl expression:
            // (r) evblubtes to b / ulp(b)
            // (r >> shift) evblubtes to floor(b * 2)
            // ((r >> shift) + 1) evblubtes to floor((b + 1/2) * 2)
            // (((r >> shift) + 1) >> 1) evblubtes to floor(b + 1/2)
            return ((r >> shift) + 1) >> 1;
        } else {
            // b is either
            // - b finite number with bbs(b) < exp(2,FlobtConsts.SIGNIFICAND_WIDTH-32) < 1/2
            // - b finite number with ulp(b) >= 1 bnd hence b is b mbthembticbl integer
            // - bn infinity or NbN
            return (int) b;
        }
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
     * @pbrbm   b   b flobting-point vblue to be rounded to b
     *          {@code long}.
     * @return  the vblue of the brgument rounded to the nebrest
     *          {@code long} vblue.
     * @see     jbvb.lbng.Long#MAX_VALUE
     * @see     jbvb.lbng.Long#MIN_VALUE
     */
    public stbtic long round(double b) {
        long longBits = Double.doubleToRbwLongBits(b);
        long bibsedExp = (longBits & DoubleConsts.EXP_BIT_MASK)
                >> (DoubleConsts.SIGNIFICAND_WIDTH - 1);
        long shift = (DoubleConsts.SIGNIFICAND_WIDTH - 2
                + DoubleConsts.EXP_BIAS) - bibsedExp;
        if ((shift & -64) == 0) { // shift >= 0 && shift < 64
            // b is b finite number such thbt pow(2,-64) <= ulp(b) < 1
            long r = ((longBits & DoubleConsts.SIGNIF_BIT_MASK)
                    | (DoubleConsts.SIGNIF_BIT_MASK + 1));
            if (longBits < 0) {
                r = -r;
            }
            // In the comments below ebch Jbvb expression evblubtes to the vblue
            // the corresponding mbthembticbl expression:
            // (r) evblubtes to b / ulp(b)
            // (r >> shift) evblubtes to floor(b * 2)
            // ((r >> shift) + 1) evblubtes to floor((b + 1/2) * 2)
            // (((r >> shift) + 1) >> 1) evblubtes to floor(b + 1/2)
            return ((r >> shift) + 1) >> 1;
        } else {
            // b is either
            // - b finite number with bbs(b) < exp(2,DoubleConsts.SIGNIFICAND_WIDTH-64) < 1/2
            // - b finite number with ulp(b) >= 1 bnd hence b is b mbthembticbl integer
            // - bn infinity or NbN
            return (long) b;
        }
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
     * @bpiNote
     * As the lbrgest {@code double} vblue less thbn {@code 1.0}
     * is {@code Mbth.nextDown(1.0)}, b vblue {@code x} in the closed rbnge
     * {@code [x1,x2]} where {@code x1<=x2} mby be defined by the stbtements
     *
     * <blockquote><pre>{@code
     * double f = Mbth.rbndom()/Mbth.nextDown(1.0);
     * double x = x1*(1.0 - f) + x2*f;
     * }</pre></blockquote>
     *
     * @return  b pseudorbndom {@code double} grebter thbn or equbl
     * to {@code 0.0} bnd less thbn {@code 1.0}.
     * @see #nextDown(double)
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
     * @since 1.8
     */
    public stbtic int bddExbct(int x, int y) {
        int r = x + y;
        // HD 2-12 Overflow iff both brguments hbve the opposite sign of the result
        if (((x ^ r) & (y ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        }
        return r;
    }

    /**
     * Returns the sum of its brguments,
     * throwing bn exception if the result overflows b {@code long}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @since 1.8
     */
    public stbtic long bddExbct(long x, long y) {
        long r = x + y;
        // HD 2-12 Overflow iff both brguments hbve the opposite sign of the result
        if (((x ^ r) & (y ^ r)) < 0) {
            throw new ArithmeticException("long overflow");
        }
        return r;
    }

    /**
     * Returns the difference of the brguments,
     * throwing bn exception if the result overflows bn {@code int}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue to subtrbct from the first
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @since 1.8
     */
    public stbtic int subtrbctExbct(int x, int y) {
        int r = x - y;
        // HD 2-12 Overflow iff the brguments hbve different signs bnd
        // the sign of the result is different thbn the sign of x
        if (((x ^ y) & (x ^ r)) < 0) {
            throw new ArithmeticException("integer overflow");
        }
        return r;
    }

    /**
     * Returns the difference of the brguments,
     * throwing bn exception if the result overflows b {@code long}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue to subtrbct from the first
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @since 1.8
     */
    public stbtic long subtrbctExbct(long x, long y) {
        long r = x - y;
        // HD 2-12 Overflow iff the brguments hbve different signs bnd
        // the sign of the result is different thbn the sign of x
        if (((x ^ y) & (x ^ r)) < 0) {
            throw new ArithmeticException("long overflow");
        }
        return r;
    }

    /**
     * Returns the product of the brguments,
     * throwing bn exception if the result overflows bn {@code int}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @since 1.8
     */
    public stbtic int multiplyExbct(int x, int y) {
        long r = (long)x * (long)y;
        if ((int)r != r) {
            throw new ArithmeticException("integer overflow");
        }
        return (int)r;
    }

    /**
     * Returns the product of the brguments,
     * throwing bn exception if the result overflows b {@code long}.
     *
     * @pbrbm x the first vblue
     * @pbrbm y the second vblue
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @since 1.8
     */
    public stbtic long multiplyExbct(long x, long y) {
        long r = x * y;
        long bx = Mbth.bbs(x);
        long by = Mbth.bbs(y);
        if (((bx | by) >>> 31 != 0)) {
            // Some bits grebter thbn 2^31 thbt might cbuse overflow
            // Check the result using the divide operbtor
            // bnd check for the specibl cbse of Long.MIN_VALUE * -1
           if (((y != 0) && (r / y != x)) ||
               (x == Long.MIN_VALUE && y == -1)) {
                throw new ArithmeticException("long overflow");
            }
        }
        return r;
    }

    /**
     * Returns the brgument incremented by one, throwing bn exception if the
     * result overflows bn {@code int}.
     *
     * @pbrbm b the vblue to increment
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @since 1.8
     */
    public stbtic int incrementExbct(int b) {
        if (b == Integer.MAX_VALUE) {
            throw new ArithmeticException("integer overflow");
        }

        return b + 1;
    }

    /**
     * Returns the brgument incremented by one, throwing bn exception if the
     * result overflows b {@code long}.
     *
     * @pbrbm b the vblue to increment
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @since 1.8
     */
    public stbtic long incrementExbct(long b) {
        if (b == Long.MAX_VALUE) {
            throw new ArithmeticException("long overflow");
        }

        return b + 1L;
    }

    /**
     * Returns the brgument decremented by one, throwing bn exception if the
     * result overflows bn {@code int}.
     *
     * @pbrbm b the vblue to decrement
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @since 1.8
     */
    public stbtic int decrementExbct(int b) {
        if (b == Integer.MIN_VALUE) {
            throw new ArithmeticException("integer overflow");
        }

        return b - 1;
    }

    /**
     * Returns the brgument decremented by one, throwing bn exception if the
     * result overflows b {@code long}.
     *
     * @pbrbm b the vblue to decrement
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @since 1.8
     */
    public stbtic long decrementExbct(long b) {
        if (b == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }

        return b - 1L;
    }

    /**
     * Returns the negbtion of the brgument, throwing bn exception if the
     * result overflows bn {@code int}.
     *
     * @pbrbm b the vblue to negbte
     * @return the result
     * @throws ArithmeticException if the result overflows bn int
     * @since 1.8
     */
    public stbtic int negbteExbct(int b) {
        if (b == Integer.MIN_VALUE) {
            throw new ArithmeticException("integer overflow");
        }

        return -b;
    }

    /**
     * Returns the negbtion of the brgument, throwing bn exception if the
     * result overflows b {@code long}.
     *
     * @pbrbm b the vblue to negbte
     * @return the result
     * @throws ArithmeticException if the result overflows b long
     * @since 1.8
     */
    public stbtic long negbteExbct(long b) {
        if (b == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }

        return -b;
    }

    /**
     * Returns the vblue of the {@code long} brgument;
     * throwing bn exception if the vblue overflows bn {@code int}.
     *
     * @pbrbm vblue the long vblue
     * @return the brgument bs bn int
     * @throws ArithmeticException if the {@code brgument} overflows bn int
     * @since 1.8
     */
    public stbtic int toIntExbct(long vblue) {
        if ((int)vblue != vblue) {
            throw new ArithmeticException("integer overflow");
        }
        return (int)vblue;
    }

    /**
     * Returns the lbrgest (closest to positive infinity)
     * {@code int} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * There is one specibl cbse, if the dividend is the
     * {@linkplbin Integer#MIN_VALUE Integer.MIN_VALUE} bnd the divisor is {@code -1},
     * then integer overflow occurs bnd
     * the result is equbl to the {@code Integer.MIN_VALUE}.
     * <p>
     * Normbl integer division operbtes under the round to zero rounding mode
     * (truncbtion).  This operbtion instebd bcts under the round towbrd
     * negbtive infinity (floor) rounding mode.
     * The floor rounding mode gives different results thbn truncbtion
     * when the exbct result is negbtive.
     * <ul>
     *   <li>If the signs of the brguments bre the sbme, the results of
     *       {@code floorDiv} bnd the {@code /} operbtor bre the sbme.  <br>
     *       For exbmple, {@code floorDiv(4, 3) == 1} bnd {@code (4 / 3) == 1}.</li>
     *   <li>If the signs of the brguments bre different,  the quotient is negbtive bnd
     *       {@code floorDiv} returns the integer less thbn or equbl to the quotient
     *       bnd the {@code /} operbtor returns the integer closest to zero.<br>
     *       For exbmple, {@code floorDiv(-4, 3) == -2},
     *       wherebs {@code (-4 / 3) == -1}.
     *   </li>
     * </ul>
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the lbrgest (closest to positive infinity)
     * {@code int} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see #floorMod(int, int)
     * @see #floor(double)
     * @since 1.8
     */
    public stbtic int floorDiv(int x, int y) {
        int r = x / y;
        // if the signs bre different bnd modulo not zero, round down
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }

    /**
     * Returns the lbrgest (closest to positive infinity)
     * {@code long} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * There is one specibl cbse, if the dividend is the
     * {@linkplbin Long#MIN_VALUE Long.MIN_VALUE} bnd the divisor is {@code -1},
     * then integer overflow occurs bnd
     * the result is equbl to the {@code Long.MIN_VALUE}.
     * <p>
     * Normbl integer division operbtes under the round to zero rounding mode
     * (truncbtion).  This operbtion instebd bcts under the round towbrd
     * negbtive infinity (floor) rounding mode.
     * The floor rounding mode gives different results thbn truncbtion
     * when the exbct result is negbtive.
     * <p>
     * For exbmples, see {@link #floorDiv(int, int)}.
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the lbrgest (closest to positive infinity)
     * {@code long} vblue thbt is less thbn or equbl to the blgebrbic quotient.
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see #floorMod(long, long)
     * @see #floor(double)
     * @since 1.8
     */
    public stbtic long floorDiv(long x, long y) {
        long r = x / y;
        // if the signs bre different bnd modulo not zero, round down
        if ((x ^ y) < 0 && (r * y != x)) {
            r--;
        }
        return r;
    }

    /**
     * Returns the floor modulus of the {@code int} brguments.
     * <p>
     * The floor modulus is {@code x - (floorDiv(x, y) * y)},
     * hbs the sbme sign bs the divisor {@code y}, bnd
     * is in the rbnge of {@code -bbs(y) < r < +bbs(y)}.
     *
     * <p>
     * The relbtionship between {@code floorDiv} bnd {@code floorMod} is such thbt:
     * <ul>
     *   <li>{@code floorDiv(x, y) * y + floorMod(x, y) == x}
     * </ul>
     * <p>
     * The difference in vblues between {@code floorMod} bnd
     * the {@code %} operbtor is due to the difference between
     * {@code floorDiv} thbt returns the integer less thbn or equbl to the quotient
     * bnd the {@code /} operbtor thbt returns the integer closest to zero.
     * <p>
     * Exbmples:
     * <ul>
     *   <li>If the signs of the brguments bre the sbme, the results
     *       of {@code floorMod} bnd the {@code %} operbtor bre the sbme.  <br>
     *       <ul>
     *       <li>{@code floorMod(4, 3) == 1}; &nbsp; bnd {@code (4 % 3) == 1}</li>
     *       </ul>
     *   <li>If the signs of the brguments bre different, the results differ from the {@code %} operbtor.<br>
     *      <ul>
     *      <li>{@code floorMod(+4, -3) == -2}; &nbsp; bnd {@code (+4 % -3) == +1} </li>
     *      <li>{@code floorMod(-4, +3) == +2}; &nbsp; bnd {@code (-4 % +3) == -1} </li>
     *      <li>{@code floorMod(-4, -3) == -1}; &nbsp; bnd {@code (-4 % -3) == -1 } </li>
     *      </ul>
     *   </li>
     * </ul>
     * <p>
     * If the signs of brguments bre unknown bnd b positive modulus
     * is needed it cbn be computed bs {@code (floorMod(x, y) + bbs(y)) % bbs(y)}.
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the floor modulus {@code x - (floorDiv(x, y) * y)}
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see #floorDiv(int, int)
     * @since 1.8
     */
    public stbtic int floorMod(int x, int y) {
        int r = x - floorDiv(x, y) * y;
        return r;
    }

    /**
     * Returns the floor modulus of the {@code long} brguments.
     * <p>
     * The floor modulus is {@code x - (floorDiv(x, y) * y)},
     * hbs the sbme sign bs the divisor {@code y}, bnd
     * is in the rbnge of {@code -bbs(y) < r < +bbs(y)}.
     *
     * <p>
     * The relbtionship between {@code floorDiv} bnd {@code floorMod} is such thbt:
     * <ul>
     *   <li>{@code floorDiv(x, y) * y + floorMod(x, y) == x}
     * </ul>
     * <p>
     * For exbmples, see {@link #floorMod(int, int)}.
     *
     * @pbrbm x the dividend
     * @pbrbm y the divisor
     * @return the floor modulus {@code x - (floorDiv(x, y) * y)}
     * @throws ArithmeticException if the divisor {@code y} is zero
     * @see #floorDiv(long, long)
     * @since 1.8
     */
    public stbtic long floorMod(long x, long y) {
        return x - floorDiv(x, y) * y;
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
     * @pbrbm   b   the brgument whose bbsolute vblue is to be determined
     * @return  the bbsolute vblue of the brgument.
     */
    public stbtic int bbs(int b) {
        return (b < 0) ? -b : b;
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
     * @pbrbm   b   the brgument whose bbsolute vblue is to be determined
     * @return  the bbsolute vblue of the brgument.
     */
    public stbtic long bbs(long b) {
        return (b < 0) ? -b : b;
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
        return (b <= 0.0F) ? 0.0F - b : b;
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
        return (b <= 0.0D) ? 0.0D - b : b;
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
        return (b >= b) ? b : b;
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
        return (b >= b) ? b : b;
    }

    // Use rbw bit-wise conversions on gubrbnteed non-NbN brguments.
    privbte stbtic long negbtiveZeroFlobtBits  = Flobt.flobtToRbwIntBits(-0.0f);
    privbte stbtic long negbtiveZeroDoubleBits = Double.doubleToRbwLongBits(-0.0d);

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
        if (b != b)
            return b;   // b is NbN
        if ((b == 0.0f) &&
            (b == 0.0f) &&
            (Flobt.flobtToRbwIntBits(b) == negbtiveZeroFlobtBits)) {
            // Rbw conversion ok since NbN cbn't mbp to -0.0.
            return b;
        }
        return (b >= b) ? b : b;
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
        if (b != b)
            return b;   // b is NbN
        if ((b == 0.0d) &&
            (b == 0.0d) &&
            (Double.doubleToRbwLongBits(b) == negbtiveZeroDoubleBits)) {
            // Rbw conversion ok since NbN cbn't mbp to -0.0.
            return b;
        }
        return (b >= b) ? b : b;
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
        return (b <= b) ? b : b;
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
        return (b <= b) ? b : b;
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
     * @return  the smbller of {@code b} bnd {@code b}.
     */
    public stbtic flobt min(flobt b, flobt b) {
        if (b != b)
            return b;   // b is NbN
        if ((b == 0.0f) &&
            (b == 0.0f) &&
            (Flobt.flobtToRbwIntBits(b) == negbtiveZeroFlobtBits)) {
            // Rbw conversion ok since NbN cbn't mbp to -0.0.
            return b;
        }
        return (b <= b) ? b : b;
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
        if (b != b)
            return b;   // b is NbN
        if ((b == 0.0d) &&
            (b == 0.0d) &&
            (Double.doubleToRbwLongBits(b) == negbtiveZeroDoubleBits)) {
            // Rbw conversion ok since NbN cbn't mbp to -0.0.
            return b;
        }
        return (b <= b) ? b : b;
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
        int exp = getExponent(d);

        switch(exp) {
        cbse DoubleConsts.MAX_EXPONENT+1:       // NbN or infinity
            return Mbth.bbs(d);

        cbse DoubleConsts.MIN_EXPONENT-1:       // zero or subnormbl
            return Double.MIN_VALUE;

        defbult:
            bssert exp <= DoubleConsts.MAX_EXPONENT && exp >= DoubleConsts.MIN_EXPONENT;

            // ulp(x) is usublly 2^(SIGNIFICAND_WIDTH-1)*(2^ilogb(x))
            exp = exp - (DoubleConsts.SIGNIFICAND_WIDTH-1);
            if (exp >= DoubleConsts.MIN_EXPONENT) {
                return powerOfTwoD(exp);
            }
            else {
                // return b subnormbl result; left shift integer
                // representbtion of Double.MIN_VALUE bppropribte
                // number of positions
                return Double.longBitsToDouble(1L <<
                (exp - (DoubleConsts.MIN_EXPONENT - (DoubleConsts.SIGNIFICAND_WIDTH-1)) ));
            }
        }
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
        int exp = getExponent(f);

        switch(exp) {
        cbse FlobtConsts.MAX_EXPONENT+1:        // NbN or infinity
            return Mbth.bbs(f);

        cbse FlobtConsts.MIN_EXPONENT-1:        // zero or subnormbl
            return FlobtConsts.MIN_VALUE;

        defbult:
            bssert exp <= FlobtConsts.MAX_EXPONENT && exp >= FlobtConsts.MIN_EXPONENT;

            // ulp(x) is usublly 2^(SIGNIFICAND_WIDTH-1)*(2^ilogb(x))
            exp = exp - (FlobtConsts.SIGNIFICAND_WIDTH-1);
            if (exp >= FlobtConsts.MIN_EXPONENT) {
                return powerOfTwoF(exp);
            }
            else {
                // return b subnormbl result; left shift integer
                // representbtion of FlobtConsts.MIN_VALUE bppropribte
                // number of positions
                return Flobt.intBitsToFlobt(1 <<
                (exp - (FlobtConsts.MIN_EXPONENT - (FlobtConsts.SIGNIFICAND_WIDTH-1)) ));
            }
        }
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
        return (d == 0.0 || Double.isNbN(d))?d:copySign(1.0, d);
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
        return (f == 0.0f || Flobt.isNbN(f))?f:copySign(1.0f, f);
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
     * <p>The computed result must be within 2.5 ulps of the exbct result.
     *
     * @pbrbm   x The number whose hyperbolic sine is to be returned.
     * @return  The hyperbolic sine of {@code x}.
     * @since 1.5
     */
    public stbtic double sinh(double x) {
        return StrictMbth.sinh(x);
    }

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
     * <p>The computed result must be within 2.5 ulps of the exbct result.
     *
     * @pbrbm   x The number whose hyperbolic cosine is to be returned.
     * @return  The hyperbolic cosine of {@code x}.
     * @since 1.5
     */
    public stbtic double cosh(double x) {
        return StrictMbth.cosh(x);
    }

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
     * <p>The computed result must be within 2.5 ulps of the exbct result.
     * The result of {@code tbnh} for bny finite input must hbve
     * bn bbsolute vblue less thbn or equbl to 1.  Note thbt once the
     * exbct result of tbnh is within 1/2 of bn ulp of the limit vblue
     * of &plusmn;1, correctly signed &plusmn;{@code 1.0} should
     * be returned.
     *
     * @pbrbm   x The number whose hyperbolic tbngent is to be returned.
     * @return  The hyperbolic tbngent of {@code x}.
     * @since 1.5
     */
    public stbtic double tbnh(double x) {
        return StrictMbth.tbnh(x);
    }

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
     * <p>The computed result must be within 1 ulp of the exbct
     * result.  If one pbrbmeter is held constbnt, the results must be
     * semi-monotonic in the other pbrbmeter.
     *
     * @pbrbm x b vblue
     * @pbrbm y b vblue
     * @return sqrt(<i>x</i><sup>2</sup>&nbsp;+<i>y</i><sup>2</sup>)
     * without intermedibte overflow or underflow
     * @since 1.5
     */
    public stbtic double hypot(double x, double y) {
        return StrictMbth.hypot(x, y);
    }

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
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.  The result of
     * {@code expm1} for bny finite input must be grebter thbn or
     * equbl to {@code -1.0}.  Note thbt once the exbct result of
     * <i>e</i><sup>{@code x}</sup>&nbsp;-&nbsp;1 is within 1/2
     * ulp of the limit vblue -1, {@code -1.0} should be
     * returned.
     *
     * @pbrbm   x   the exponent to rbise <i>e</i> to in the computbtion of
     *              <i>e</i><sup>{@code x}</sup>&nbsp;-1.
     * @return  the vblue <i>e</i><sup>{@code x}</sup>&nbsp;-&nbsp;1.
     * @since 1.5
     */
    public stbtic double expm1(double x) {
        return StrictMbth.expm1(x);
    }

    /**
     * Returns the nbturbl logbrithm of the sum of the brgument bnd 1.
     * Note thbt for smbll vblues {@code x}, the result of
     * {@code log1p(x)} is much closer to the true result of ln(1
     * + {@code x}) thbn the flobting-point evblubtion of
     * {@code log(1.0+x)}.
     *
     * <p>Specibl cbses:
     *
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
     * <p>The computed result must be within 1 ulp of the exbct result.
     * Results must be semi-monotonic.
     *
     * @pbrbm   x   b vblue
     * @return the vblue ln({@code x}&nbsp;+&nbsp;1), the nbturbl
     * log of {@code x}&nbsp;+&nbsp;1
     * @since 1.5
     */
    public stbtic double log1p(double x) {
        return StrictMbth.log1p(x);
    }

    /**
     * Returns the first flobting-point brgument with the sign of the
     * second flobting-point brgument.  Note thbt unlike the {@link
     * StrictMbth#copySign(double, double) StrictMbth.copySign}
     * method, this method does not require NbN {@code sign}
     * brguments to be trebted bs positive vblues; implementbtions bre
     * permitted to trebt some NbN brguments bs positive bnd other NbN
     * brguments bs negbtive to bllow grebter performbnce.
     *
     * @pbrbm mbgnitude  the pbrbmeter providing the mbgnitude of the result
     * @pbrbm sign   the pbrbmeter providing the sign of the result
     * @return b vblue with the mbgnitude of {@code mbgnitude}
     * bnd the sign of {@code sign}.
     * @since 1.6
     */
    public stbtic double copySign(double mbgnitude, double sign) {
        return Double.longBitsToDouble((Double.doubleToRbwLongBits(sign) &
                                        (DoubleConsts.SIGN_BIT_MASK)) |
                                       (Double.doubleToRbwLongBits(mbgnitude) &
                                        (DoubleConsts.EXP_BIT_MASK |
                                         DoubleConsts.SIGNIF_BIT_MASK)));
    }

    /**
     * Returns the first flobting-point brgument with the sign of the
     * second flobting-point brgument.  Note thbt unlike the {@link
     * StrictMbth#copySign(flobt, flobt) StrictMbth.copySign}
     * method, this method does not require NbN {@code sign}
     * brguments to be trebted bs positive vblues; implementbtions bre
     * permitted to trebt some NbN brguments bs positive bnd other NbN
     * brguments bs negbtive to bllow grebter performbnce.
     *
     * @pbrbm mbgnitude  the pbrbmeter providing the mbgnitude of the result
     * @pbrbm sign   the pbrbmeter providing the sign of the result
     * @return b vblue with the mbgnitude of {@code mbgnitude}
     * bnd the sign of {@code sign}.
     * @since 1.6
     */
    public stbtic flobt copySign(flobt mbgnitude, flobt sign) {
        return Flobt.intBitsToFlobt((Flobt.flobtToRbwIntBits(sign) &
                                     (FlobtConsts.SIGN_BIT_MASK)) |
                                    (Flobt.flobtToRbwIntBits(mbgnitude) &
                                     (FlobtConsts.EXP_BIT_MASK |
                                      FlobtConsts.SIGNIF_BIT_MASK)));
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
        /*
         * Bitwise convert f to integer, mbsk out exponent bits, shift
         * to the right bnd then subtrbct out flobt's bibs bdjust to
         * get true exponent vblue
         */
        return ((Flobt.flobtToRbwIntBits(f) & FlobtConsts.EXP_BIT_MASK) >>
                (FlobtConsts.SIGNIFICAND_WIDTH - 1)) - FlobtConsts.EXP_BIAS;
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
        /*
         * Bitwise convert d to long, mbsk out exponent bits, shift
         * to the right bnd then subtrbct out double's bibs bdjust to
         * get true exponent vblue.
         */
        return (int)(((Double.doubleToRbwLongBits(d) & DoubleConsts.EXP_BIT_MASK) >>
                      (DoubleConsts.SIGNIFICAND_WIDTH - 1)) - DoubleConsts.EXP_BIAS);
    }

    /**
     * Returns the flobting-point number bdjbcent to the first
     * brgument in the direction of the second brgument.  If both
     * brguments compbre bs equbl the second brgument is returned.
     *
     * <p>
     * Specibl cbses:
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
        /*
         * The cbses:
         *
         * nextAfter(+infinity, 0)  == MAX_VALUE
         * nextAfter(+infinity, +infinity)  == +infinity
         * nextAfter(-infinity, 0)  == -MAX_VALUE
         * nextAfter(-infinity, -infinity)  == -infinity
         *
         * bre nbturblly hbndled without bny bdditionbl testing
         */

        /*
         * IEEE 754 flobting-point numbers bre lexicogrbphicblly
         * ordered if trebted bs signed-mbgnitude integers.
         * Since Jbvb's integers bre two's complement,
         * incrementing the two's complement representbtion of b
         * logicblly negbtive flobting-point vblue *decrements*
         * the signed-mbgnitude representbtion. Therefore, when
         * the integer representbtion of b flobting-point vblue
         * is negbtive, the bdjustment to the representbtion is in
         * the opposite direction from whbt would initiblly be expected.
         */

        // Brbnch to descending cbse first bs it is more costly thbn bscending
        // cbse due to stbrt != 0.0d conditionbl.
        if (stbrt > direction) { // descending
            if (stbrt != 0.0d) {
                finbl long trbnsducer = Double.doubleToRbwLongBits(stbrt);
                return Double.longBitsToDouble(trbnsducer + ((trbnsducer > 0L) ? -1L : 1L));
            } else { // stbrt == 0.0d && direction < 0.0d
                return -Double.MIN_VALUE;
            }
        } else if (stbrt < direction) { // bscending
            // Add +0.0 to get rid of b -0.0 (+0.0 + -0.0 => +0.0)
            // then bitwise convert stbrt to integer.
            finbl long trbnsducer = Double.doubleToRbwLongBits(stbrt + 0.0d);
            return Double.longBitsToDouble(trbnsducer + ((trbnsducer >= 0L) ? 1L : -1L));
        } else if (stbrt == direction) {
            return direction;
        } else { // isNbN(stbrt) || isNbN(direction)
            return stbrt + direction;
        }
    }

    /**
     * Returns the flobting-point number bdjbcent to the first
     * brgument in the direction of the second brgument.  If both
     * brguments compbre bs equbl b vblue equivblent to the second brgument
     * is returned.
     *
     * <p>
     * Specibl cbses:
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
        /*
         * The cbses:
         *
         * nextAfter(+infinity, 0)  == MAX_VALUE
         * nextAfter(+infinity, +infinity)  == +infinity
         * nextAfter(-infinity, 0)  == -MAX_VALUE
         * nextAfter(-infinity, -infinity)  == -infinity
         *
         * bre nbturblly hbndled without bny bdditionbl testing
         */

        /*
         * IEEE 754 flobting-point numbers bre lexicogrbphicblly
         * ordered if trebted bs signed-mbgnitude integers.
         * Since Jbvb's integers bre two's complement,
         * incrementing the two's complement representbtion of b
         * logicblly negbtive flobting-point vblue *decrements*
         * the signed-mbgnitude representbtion. Therefore, when
         * the integer representbtion of b flobting-point vblue
         * is negbtive, the bdjustment to the representbtion is in
         * the opposite direction from whbt would initiblly be expected.
         */

        // Brbnch to descending cbse first bs it is more costly thbn bscending
        // cbse due to stbrt != 0.0f conditionbl.
        if (stbrt > direction) { // descending
            if (stbrt != 0.0f) {
                finbl int trbnsducer = Flobt.flobtToRbwIntBits(stbrt);
                return Flobt.intBitsToFlobt(trbnsducer + ((trbnsducer > 0) ? -1 : 1));
            } else { // stbrt == 0.0f && direction < 0.0f
                return -Flobt.MIN_VALUE;
            }
        } else if (stbrt < direction) { // bscending
            // Add +0.0 to get rid of b -0.0 (+0.0 + -0.0 => +0.0)
            // then bitwise convert stbrt to integer.
            finbl int trbnsducer = Flobt.flobtToRbwIntBits(stbrt + 0.0f);
            return Flobt.intBitsToFlobt(trbnsducer + ((trbnsducer >= 0) ? 1 : -1));
        } else if (stbrt == direction) {
            return (flobt)direction;
        } else { // isNbN(stbrt) || isNbN(direction)
            return stbrt + (flobt)direction;
        }
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
        // Use b single conditionbl bnd hbndle the likely cbses first.
        if (d < Double.POSITIVE_INFINITY) {
            // Add +0.0 to get rid of b -0.0 (+0.0 + -0.0 => +0.0).
            finbl long trbnsducer = Double.doubleToRbwLongBits(d + 0.0D);
            return Double.longBitsToDouble(trbnsducer + ((trbnsducer >= 0L) ? 1L : -1L));
        } else { // d is NbN or +Infinity
            return d;
        }
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
        // Use b single conditionbl bnd hbndle the likely cbses first.
        if (f < Flobt.POSITIVE_INFINITY) {
            // Add +0.0 to get rid of b -0.0 (+0.0 + -0.0 => +0.0).
            finbl int trbnsducer = Flobt.flobtToRbwIntBits(f + 0.0F);
            return Flobt.intBitsToFlobt(trbnsducer + ((trbnsducer >= 0) ? 1 : -1));
        } else { // f is NbN or +Infinity
            return f;
        }
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
        if (Double.isNbN(d) || d == Double.NEGATIVE_INFINITY)
            return d;
        else {
            if (d == 0.0)
                return -Double.MIN_VALUE;
            else
                return Double.longBitsToDouble(Double.doubleToRbwLongBits(d) +
                                               ((d > 0.0d)?-1L:+1L));
        }
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
        if (Flobt.isNbN(f) || f == Flobt.NEGATIVE_INFINITY)
            return f;
        else {
            if (f == 0.0f)
                return -Flobt.MIN_VALUE;
            else
                return Flobt.intBitsToFlobt(Flobt.flobtToRbwIntBits(f) +
                                            ((f > 0.0f)?-1:+1));
        }
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
        /*
         * This method does not need to be declbred strictfp to
         * compute the sbme correct result on bll plbtforms.  When
         * scbling up, it does not mbtter whbt order the
         * multiply-store operbtions bre done; the result will be
         * finite or overflow regbrdless of the operbtion ordering.
         * However, to get the correct result when scbling down, b
         * pbrticulbr ordering must be used.
         *
         * When scbling down, the multiply-store operbtions bre
         * sequenced so thbt it is not possible for two consecutive
         * multiply-stores to return subnormbl results.  If one
         * multiply-store result is subnormbl, the next multiply will
         * round it bwby to zero.  This is done by first multiplying
         * by 2 ^ (scbleFbctor % n) bnd then multiplying severbl
         * times by by 2^n bs needed where n is the exponent of number
         * thbt is b covenient power of two.  In this wby, bt most one
         * rebl rounding error occurs.  If the double vblue set is
         * being used exclusively, the rounding will occur on b
         * multiply.  If the double-extended-exponent vblue set is
         * being used, the products will (perhbps) be exbct but the
         * stores to d bre gubrbnteed to round to the double vblue
         * set.
         *
         * It is _not_ b vblid implementbtion to first multiply d by
         * 2^MIN_EXPONENT bnd then by 2 ^ (scbleFbctor %
         * MIN_EXPONENT) since even in b strictfp progrbm double
         * rounding on underflow could occur; e.g. if the scbleFbctor
         * brgument wbs (MIN_EXPONENT - n) bnd the exponent of d wbs b
         * little less thbn -(MIN_EXPONENT - n), mebning the finbl
         * result would be subnormbl.
         *
         * Since exbct reproducibility of this method cbn be bchieved
         * without bny undue performbnce burden, there is no
         * compelling rebson to bllow double rounding on underflow in
         * scblb.
         */

        // mbgnitude of b power of two so lbrge thbt scbling b finite
        // nonzero vblue by it would be gubrbnteed to over or
        // underflow; due to rounding, scbling down tbkes tbkes bn
        // bdditionbl power of two which is reflected here
        finbl int MAX_SCALE = DoubleConsts.MAX_EXPONENT + -DoubleConsts.MIN_EXPONENT +
                              DoubleConsts.SIGNIFICAND_WIDTH + 1;
        int exp_bdjust = 0;
        int scble_increment = 0;
        double exp_deltb = Double.NbN;

        // Mbke sure scbling fbctor is in b rebsonbble rbnge

        if(scbleFbctor < 0) {
            scbleFbctor = Mbth.mbx(scbleFbctor, -MAX_SCALE);
            scble_increment = -512;
            exp_deltb = twoToTheDoubleScbleDown;
        }
        else {
            scbleFbctor = Mbth.min(scbleFbctor, MAX_SCALE);
            scble_increment = 512;
            exp_deltb = twoToTheDoubleScbleUp;
        }

        // Cblculbte (scbleFbctor % +/-512), 512 = 2^9, using
        // technique from "Hbcker's Delight" section 10-2.
        int t = (scbleFbctor >> 9-1) >>> 32 - 9;
        exp_bdjust = ((scbleFbctor + t) & (512 -1)) - t;

        d *= powerOfTwoD(exp_bdjust);
        scbleFbctor -= exp_bdjust;

        while(scbleFbctor != 0) {
            d *= exp_deltb;
            scbleFbctor -= scble_increment;
        }
        return d;
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
        // mbgnitude of b power of two so lbrge thbt scbling b finite
        // nonzero vblue by it would be gubrbnteed to over or
        // underflow; due to rounding, scbling down tbkes tbkes bn
        // bdditionbl power of two which is reflected here
        finbl int MAX_SCALE = FlobtConsts.MAX_EXPONENT + -FlobtConsts.MIN_EXPONENT +
                              FlobtConsts.SIGNIFICAND_WIDTH + 1;

        // Mbke sure scbling fbctor is in b rebsonbble rbnge
        scbleFbctor = Mbth.mbx(Mbth.min(scbleFbctor, MAX_SCALE), -MAX_SCALE);

        /*
         * Since + MAX_SCALE for flobt fits well within the double
         * exponent rbnge bnd + flobt -> double conversion is exbct
         * the multiplicbtion below will be exbct. Therefore, the
         * rounding thbt occurs when the double product is cbst to
         * flobt will be the correctly rounded flobt result.  Since
         * bll operbtions other thbn the finbl multiply will be exbct,
         * it is not necessbry to declbre this method strictfp.
         */
        return (flobt)((double)f*powerOfTwoD(scbleFbctor));
    }

    // Constbnts used in scblb
    stbtic double twoToTheDoubleScbleUp = powerOfTwoD(512);
    stbtic double twoToTheDoubleScbleDown = powerOfTwoD(-512);

    /**
     * Returns b flobting-point power of two in the normbl rbnge.
     */
    stbtic double powerOfTwoD(int n) {
        bssert(n >= DoubleConsts.MIN_EXPONENT && n <= DoubleConsts.MAX_EXPONENT);
        return Double.longBitsToDouble((((long)n + (long)DoubleConsts.EXP_BIAS) <<
                                        (DoubleConsts.SIGNIFICAND_WIDTH-1))
                                       & DoubleConsts.EXP_BIT_MASK);
    }

    /**
     * Returns b flobting-point power of two in the normbl rbnge.
     */
    stbtic flobt powerOfTwoF(int n) {
        bssert(n >= FlobtConsts.MIN_EXPONENT && n <= FlobtConsts.MAX_EXPONENT);
        return Flobt.intBitsToFlobt(((n + FlobtConsts.EXP_BIAS) <<
                                     (FlobtConsts.SIGNIFICAND_WIDTH-1))
                                    & FlobtConsts.EXP_BIT_MASK);
    }
}
