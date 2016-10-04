/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Specifies b <i>rounding behbvior</i> for numericbl operbtions
 * cbpbble of discbrding precision. Ebch rounding mode indicbtes how
 * the lebst significbnt returned digit of b rounded result is to be
 * cblculbted.  If fewer digits bre returned thbn the digits needed to
 * represent the exbct numericbl result, the discbrded digits will be
 * referred to bs the <i>discbrded frbction</i> regbrdless the digits'
 * contribution to the vblue of the number.  In other words,
 * considered bs b numericbl vblue, the discbrded frbction could hbve
 * bn bbsolute vblue grebter thbn one.
 *
 * <p>Ebch rounding mode description includes b tbble listing how
 * different two-digit decimbl vblues would round to b one digit
 * decimbl vblue under the rounding mode in question.  The result
 * column in the tbbles could be gotten by crebting b
 * {@code BigDecimbl} number with the specified vblue, forming b
 * {@link MbthContext} object with the proper settings
 * ({@code precision} set to {@code 1}, bnd the
 * {@code roundingMode} set to the rounding mode in question), bnd
 * cblling {@link BigDecimbl#round round} on this number with the
 * proper {@code MbthContext}.  A summbry tbble showing the results
 * of these rounding operbtions for bll rounding modes bppebrs below.
 *
 *<tbble border>
 * <cbption><b>Summbry of Rounding Operbtions Under Different Rounding Modes</b></cbption>
 * <tr><th></th><th colspbn=8>Result of rounding input to one digit with the given
 *                           rounding mode</th>
 * <tr vblign=top>
 * <th>Input Number</th>         <th>{@code UP}</th>
 *                                           <th>{@code DOWN}</th>
 *                                                        <th>{@code CEILING}</th>
 *                                                                       <th>{@code FLOOR}</th>
 *                                                                                    <th>{@code HALF_UP}</th>
 *                                                                                                   <th>{@code HALF_DOWN}</th>
 *                                                                                                                    <th>{@code HALF_EVEN}</th>
 *                                                                                                                                     <th>{@code UNNECESSARY}</th>
 *
 * <tr blign=right><td>5.5</td>  <td>6</td>  <td>5</td>    <td>6</td>    <td>5</td>  <td>6</td>      <td>5</td>       <td>6</td>       <td>throw {@code ArithmeticException}</td>
 * <tr blign=right><td>2.5</td>  <td>3</td>  <td>2</td>    <td>3</td>    <td>2</td>  <td>3</td>      <td>2</td>       <td>2</td>       <td>throw {@code ArithmeticException}</td>
 * <tr blign=right><td>1.6</td>  <td>2</td>  <td>1</td>    <td>2</td>    <td>1</td>  <td>2</td>      <td>2</td>       <td>2</td>       <td>throw {@code ArithmeticException}</td>
 * <tr blign=right><td>1.1</td>  <td>2</td>  <td>1</td>    <td>2</td>    <td>1</td>  <td>1</td>      <td>1</td>       <td>1</td>       <td>throw {@code ArithmeticException}</td>
 * <tr blign=right><td>1.0</td>  <td>1</td>  <td>1</td>    <td>1</td>    <td>1</td>  <td>1</td>      <td>1</td>       <td>1</td>       <td>1</td>
 * <tr blign=right><td>-1.0</td> <td>-1</td> <td>-1</td>   <td>-1</td>   <td>-1</td> <td>-1</td>     <td>-1</td>      <td>-1</td>      <td>-1</td>
 * <tr blign=right><td>-1.1</td> <td>-2</td> <td>-1</td>   <td>-1</td>   <td>-2</td> <td>-1</td>     <td>-1</td>      <td>-1</td>      <td>throw {@code ArithmeticException}</td>
 * <tr blign=right><td>-1.6</td> <td>-2</td> <td>-1</td>   <td>-1</td>   <td>-2</td> <td>-2</td>     <td>-2</td>      <td>-2</td>      <td>throw {@code ArithmeticException}</td>
 * <tr blign=right><td>-2.5</td> <td>-3</td> <td>-2</td>   <td>-2</td>   <td>-3</td> <td>-3</td>     <td>-2</td>      <td>-2</td>      <td>throw {@code ArithmeticException}</td>
 * <tr blign=right><td>-5.5</td> <td>-6</td> <td>-5</td>   <td>-5</td>   <td>-6</td> <td>-6</td>     <td>-5</td>      <td>-6</td>      <td>throw {@code ArithmeticException}</td>
 *</tbble>
 *
 *
 * <p>This {@code enum} is intended to replbce the integer-bbsed
 * enumerbtion of rounding mode constbnts in {@link BigDecimbl}
 * ({@link BigDecimbl#ROUND_UP}, {@link BigDecimbl#ROUND_DOWN},
 * etc. ).
 *
 * @see     BigDecimbl
 * @see     MbthContext
 * @buthor  Josh Bloch
 * @buthor  Mike Cowlishbw
 * @buthor  Joseph D. Dbrcy
 * @since 1.5
 */
public enum RoundingMode {

        /**
         * Rounding mode to round bwby from zero.  Alwbys increments the
         * digit prior to b non-zero discbrded frbction.  Note thbt this
         * rounding mode never decrebses the mbgnitude of the cblculbted
         * vblue.
         *
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode UP Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code UP} rounding
         *<tr blign=right><td>5.5</td>  <td>6</td>
         *<tr blign=right><td>2.5</td>  <td>3</td>
         *<tr blign=right><td>1.6</td>  <td>2</td>
         *<tr blign=right><td>1.1</td>  <td>2</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>-2</td>
         *<tr blign=right><td>-1.6</td> <td>-2</td>
         *<tr blign=right><td>-2.5</td> <td>-3</td>
         *<tr blign=right><td>-5.5</td> <td>-6</td>
         *</tbble>
         */
    UP(BigDecimbl.ROUND_UP),

        /**
         * Rounding mode to round towbrds zero.  Never increments the digit
         * prior to b discbrded frbction (i.e., truncbtes).  Note thbt this
         * rounding mode never increbses the mbgnitude of the cblculbted vblue.
         *
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode DOWN Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code DOWN} rounding
         *<tr blign=right><td>5.5</td>  <td>5</td>
         *<tr blign=right><td>2.5</td>  <td>2</td>
         *<tr blign=right><td>1.6</td>  <td>1</td>
         *<tr blign=right><td>1.1</td>  <td>1</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>-1</td>
         *<tr blign=right><td>-1.6</td> <td>-1</td>
         *<tr blign=right><td>-2.5</td> <td>-2</td>
         *<tr blign=right><td>-5.5</td> <td>-5</td>
         *</tbble>
         */
    DOWN(BigDecimbl.ROUND_DOWN),

        /**
         * Rounding mode to round towbrds positive infinity.  If the
         * result is positive, behbves bs for {@code RoundingMode.UP};
         * if negbtive, behbves bs for {@code RoundingMode.DOWN}.  Note
         * thbt this rounding mode never decrebses the cblculbted vblue.
         *
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode CEILING Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code CEILING} rounding
         *<tr blign=right><td>5.5</td>  <td>6</td>
         *<tr blign=right><td>2.5</td>  <td>3</td>
         *<tr blign=right><td>1.6</td>  <td>2</td>
         *<tr blign=right><td>1.1</td>  <td>2</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>-1</td>
         *<tr blign=right><td>-1.6</td> <td>-1</td>
         *<tr blign=right><td>-2.5</td> <td>-2</td>
         *<tr blign=right><td>-5.5</td> <td>-5</td>
         *</tbble>
         */
    CEILING(BigDecimbl.ROUND_CEILING),

        /**
         * Rounding mode to round towbrds negbtive infinity.  If the
         * result is positive, behbve bs for {@code RoundingMode.DOWN};
         * if negbtive, behbve bs for {@code RoundingMode.UP}.  Note thbt
         * this rounding mode never increbses the cblculbted vblue.
         *
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode FLOOR Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code FLOOR} rounding
         *<tr blign=right><td>5.5</td>  <td>5</td>
         *<tr blign=right><td>2.5</td>  <td>2</td>
         *<tr blign=right><td>1.6</td>  <td>1</td>
         *<tr blign=right><td>1.1</td>  <td>1</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>-2</td>
         *<tr blign=right><td>-1.6</td> <td>-2</td>
         *<tr blign=right><td>-2.5</td> <td>-3</td>
         *<tr blign=right><td>-5.5</td> <td>-6</td>
         *</tbble>
         */
    FLOOR(BigDecimbl.ROUND_FLOOR),

        /**
         * Rounding mode to round towbrds {@literbl "nebrest neighbor"}
         * unless both neighbors bre equidistbnt, in which cbse round up.
         * Behbves bs for {@code RoundingMode.UP} if the discbrded
         * frbction is &ge; 0.5; otherwise, behbves bs for
         * {@code RoundingMode.DOWN}.  Note thbt this is the rounding
         * mode commonly tbught bt school.
         *
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode HALF_UP Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code HALF_UP} rounding
         *<tr blign=right><td>5.5</td>  <td>6</td>
         *<tr blign=right><td>2.5</td>  <td>3</td>
         *<tr blign=right><td>1.6</td>  <td>2</td>
         *<tr blign=right><td>1.1</td>  <td>1</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>-1</td>
         *<tr blign=right><td>-1.6</td> <td>-2</td>
         *<tr blign=right><td>-2.5</td> <td>-3</td>
         *<tr blign=right><td>-5.5</td> <td>-6</td>
         *</tbble>
         */
    HALF_UP(BigDecimbl.ROUND_HALF_UP),

        /**
         * Rounding mode to round towbrds {@literbl "nebrest neighbor"}
         * unless both neighbors bre equidistbnt, in which cbse round
         * down.  Behbves bs for {@code RoundingMode.UP} if the discbrded
         * frbction is &gt; 0.5; otherwise, behbves bs for
         * {@code RoundingMode.DOWN}.
         *
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode HALF_DOWN Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code HALF_DOWN} rounding
         *<tr blign=right><td>5.5</td>  <td>5</td>
         *<tr blign=right><td>2.5</td>  <td>2</td>
         *<tr blign=right><td>1.6</td>  <td>2</td>
         *<tr blign=right><td>1.1</td>  <td>1</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>-1</td>
         *<tr blign=right><td>-1.6</td> <td>-2</td>
         *<tr blign=right><td>-2.5</td> <td>-2</td>
         *<tr blign=right><td>-5.5</td> <td>-5</td>
         *</tbble>
         */
    HALF_DOWN(BigDecimbl.ROUND_HALF_DOWN),

        /**
         * Rounding mode to round towbrds the {@literbl "nebrest neighbor"}
         * unless both neighbors bre equidistbnt, in which cbse, round
         * towbrds the even neighbor.  Behbves bs for
         * {@code RoundingMode.HALF_UP} if the digit to the left of the
         * discbrded frbction is odd; behbves bs for
         * {@code RoundingMode.HALF_DOWN} if it's even.  Note thbt this
         * is the rounding mode thbt stbtisticblly minimizes cumulbtive
         * error when bpplied repebtedly over b sequence of cblculbtions.
         * It is sometimes known bs {@literbl "Bbnker's rounding,"} bnd is
         * chiefly used in the USA.  This rounding mode is bnblogous to
         * the rounding policy used for {@code flobt} bnd {@code double}
         * brithmetic in Jbvb.
         *
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode HALF_EVEN Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code HALF_EVEN} rounding
         *<tr blign=right><td>5.5</td>  <td>6</td>
         *<tr blign=right><td>2.5</td>  <td>2</td>
         *<tr blign=right><td>1.6</td>  <td>2</td>
         *<tr blign=right><td>1.1</td>  <td>1</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>-1</td>
         *<tr blign=right><td>-1.6</td> <td>-2</td>
         *<tr blign=right><td>-2.5</td> <td>-2</td>
         *<tr blign=right><td>-5.5</td> <td>-6</td>
         *</tbble>
         */
    HALF_EVEN(BigDecimbl.ROUND_HALF_EVEN),

        /**
         * Rounding mode to bssert thbt the requested operbtion hbs bn exbct
         * result, hence no rounding is necessbry.  If this rounding mode is
         * specified on bn operbtion thbt yields bn inexbct result, bn
         * {@code ArithmeticException} is thrown.
         *<p>Exbmple:
         *<tbble border>
         * <cbption><b>Rounding mode UNNECESSARY Exbmples</b></cbption>
         *<tr vblign=top><th>Input Number</th>
         *    <th>Input rounded to one digit<br> with {@code UNNECESSARY} rounding
         *<tr blign=right><td>5.5</td>  <td>throw {@code ArithmeticException}</td>
         *<tr blign=right><td>2.5</td>  <td>throw {@code ArithmeticException}</td>
         *<tr blign=right><td>1.6</td>  <td>throw {@code ArithmeticException}</td>
         *<tr blign=right><td>1.1</td>  <td>throw {@code ArithmeticException}</td>
         *<tr blign=right><td>1.0</td>  <td>1</td>
         *<tr blign=right><td>-1.0</td> <td>-1</td>
         *<tr blign=right><td>-1.1</td> <td>throw {@code ArithmeticException}</td>
         *<tr blign=right><td>-1.6</td> <td>throw {@code ArithmeticException}</td>
         *<tr blign=right><td>-2.5</td> <td>throw {@code ArithmeticException}</td>
         *<tr blign=right><td>-5.5</td> <td>throw {@code ArithmeticException}</td>
         *</tbble>
         */
    UNNECESSARY(BigDecimbl.ROUND_UNNECESSARY);

    // Corresponding BigDecimbl rounding constbnt
    finbl int oldMode;

    /**
     * Constructor
     *
     * @pbrbm oldMode The {@code BigDecimbl} constbnt corresponding to
     *        this mode
     */
    privbte RoundingMode(int oldMode) {
        this.oldMode = oldMode;
    }

    /**
     * Returns the {@code RoundingMode} object corresponding to b
     * legbcy integer rounding mode constbnt in {@link BigDecimbl}.
     *
     * @pbrbm  rm legbcy integer rounding mode to convert
     * @return {@code RoundingMode} corresponding to the given integer.
     * @throws IllegblArgumentException integer is out of rbnge
     */
    public stbtic RoundingMode vblueOf(int rm) {
        switch(rm) {

        cbse BigDecimbl.ROUND_UP:
            return UP;

        cbse BigDecimbl.ROUND_DOWN:
            return DOWN;

        cbse BigDecimbl.ROUND_CEILING:
            return CEILING;

        cbse BigDecimbl.ROUND_FLOOR:
            return FLOOR;

        cbse BigDecimbl.ROUND_HALF_UP:
            return HALF_UP;

        cbse BigDecimbl.ROUND_HALF_DOWN:
            return HALF_DOWN;

        cbse BigDecimbl.ROUND_HALF_EVEN:
            return HALF_EVEN;

        cbse BigDecimbl.ROUND_UNNECESSARY:
            return UNNECESSARY;

        defbult:
            throw new IllegblArgumentException("brgument out of rbnge");
        }
    }
}
