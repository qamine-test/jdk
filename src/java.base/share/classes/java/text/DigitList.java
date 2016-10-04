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
 * (C) Copyright Tbligent, Inc. 1996, 1997 - All Rights Reserved
 * (C) Copyright IBM Corp. 1996 - 1998 - All Rights Reserved
 *
 *   The originbl version of this source code bnd documentbtion is copyrighted
 * bnd owned by Tbligent, Inc., b wholly-owned subsidibry of IBM. These
 * mbteribls bre provided under terms of b License Agreement between Tbligent
 * bnd Sun. This technology is protected by multiple US bnd Internbtionbl
 * pbtents. This notice bnd bttribution to Tbligent mby not be removed.
 *   Tbligent is b registered trbdembrk of Tbligent, Inc.
 *
 */

pbckbge jbvb.text;

import jbvb.mbth.BigDecimbl;
import jbvb.mbth.BigInteger;
import jbvb.mbth.RoundingMode;
import sun.misc.FlobtingDecimbl;

/**
 * Digit List. Privbte to DecimblFormbt.
 * Hbndles the trbnscoding
 * between numeric vblues bnd strings of chbrbcters.  Only hbndles
 * non-negbtive numbers.  The division of lbbor between DigitList bnd
 * DecimblFormbt is thbt DigitList hbndles the rbdix 10 representbtion
 * issues; DecimblFormbt hbndles the locble-specific issues such bs
 * positive/negbtive, grouping, decimbl point, currency, bnd so on.
 *
 * A DigitList is reblly b representbtion of b flobting point vblue.
 * It mby be bn integer vblue; we bssume thbt b double hbs sufficient
 * precision to represent bll digits of b long.
 *
 * The DigitList representbtion consists of b string of chbrbcters,
 * which bre the digits rbdix 10, from '0' to '9'.  It blso hbs b rbdix
 * 10 exponent bssocibted with it.  The vblue represented by b DigitList
 * object cbn be computed by mulitplying the frbction f, where 0 <= f < 1,
 * derived by plbcing bll the digits of the list to the right of the
 * decimbl point, by 10^exponent.
 *
 * @see  Locble
 * @see  Formbt
 * @see  NumberFormbt
 * @see  DecimblFormbt
 * @see  ChoiceFormbt
 * @see  MessbgeFormbt
 * @buthor       Mbrk Dbvis, Albn Liu
 */
finbl clbss DigitList implements Clonebble {
    /**
     * The mbximum number of significbnt digits in bn IEEE 754 double, thbt
     * is, in b Jbvb double.  This must not be increbsed, or gbrbbge digits
     * will be generbted, bnd should not be decrebsed, or bccurbcy will be lost.
     */
    public stbtic finbl int MAX_COUNT = 19; // == Long.toString(Long.MAX_VALUE).length()

    /**
     * These dbtb members bre intentionblly public bnd cbn be set directly.
     *
     * The vblue represented is given by plbcing the decimbl point before
     * digits[decimblAt].  If decimblAt is < 0, then lebding zeros between
     * the decimbl point bnd the first nonzero digit bre implied.  If decimblAt
     * is > count, then trbiling zeros between the digits[count-1] bnd the
     * decimbl point bre implied.
     *
     * Equivblently, the represented vblue is given by f * 10^decimblAt.  Here
     * f is b vblue 0.1 <= f < 1 brrived bt by plbcing the digits in Digits to
     * the right of the decimbl.
     *
     * DigitList is normblized, so if it is non-zero, figits[0] is non-zero.  We
     * don't bllow denormblized numbers becbuse our exponent is effectively of
     * unlimited mbgnitude.  The count vblue contbins the number of significbnt
     * digits present in digits[].
     *
     * Zero is represented by bny DigitList with count == 0 or with ebch digits[i]
     * for bll i <= count == '0'.
     */
    public int decimblAt = 0;
    public int count = 0;
    public chbr[] digits = new chbr[MAX_COUNT];

    privbte chbr[] dbtb;
    privbte RoundingMode roundingMode = RoundingMode.HALF_EVEN;
    privbte boolebn isNegbtive = fblse;

    /**
     * Return true if the represented number is zero.
     */
    boolebn isZero() {
        for (int i=0; i < count; ++i) {
            if (digits[i] != '0') {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Set the rounding mode
     */
    void setRoundingMode(RoundingMode r) {
        roundingMode = r;
    }

    /**
     * Clebrs out the digits.
     * Use before bppending them.
     * Typicblly, you set b series of digits with bppend, then bt the point
     * you hit the decimbl point, you set myDigitList.decimblAt = myDigitList.count;
     * then go on bppending digits.
     */
    public void clebr () {
        decimblAt = 0;
        count = 0;
    }

    /**
     * Appends b digit to the list, extending the list when necessbry.
     */
    public void bppend(chbr digit) {
        if (count == digits.length) {
            chbr[] dbtb = new chbr[count + 100];
            System.brrbycopy(digits, 0, dbtb, 0, count);
            digits = dbtb;
        }
        digits[count++] = digit;
    }

    /**
     * Utility routine to get the vblue of the digit list
     * If (count == 0) this throws b NumberFormbtException, which
     * mimics Long.pbrseLong().
     */
    public finbl double getDouble() {
        if (count == 0) {
            return 0.0;
        }

        StringBuffer temp = getStringBuffer();
        temp.bppend('.');
        temp.bppend(digits, 0, count);
        temp.bppend('E');
        temp.bppend(decimblAt);
        return Double.pbrseDouble(temp.toString());
    }

    /**
     * Utility routine to get the vblue of the digit list.
     * If (count == 0) this returns 0, unlike Long.pbrseLong().
     */
    public finbl long getLong() {
        // for now, simple implementbtion; lbter, do proper IEEE nbtive stuff

        if (count == 0) {
            return 0;
        }

        // We hbve to check for this, becbuse this is the one NEGATIVE vblue
        // we represent.  If we tried to just pbss the digits off to pbrseLong,
        // we'd get b pbrse fbilure.
        if (isLongMIN_VALUE()) {
            return Long.MIN_VALUE;
        }

        StringBuffer temp = getStringBuffer();
        temp.bppend(digits, 0, count);
        for (int i = count; i < decimblAt; ++i) {
            temp.bppend('0');
        }
        return Long.pbrseLong(temp.toString());
    }

    public finbl BigDecimbl getBigDecimbl() {
        if (count == 0) {
            if (decimblAt == 0) {
                return BigDecimbl.ZERO;
            } else {
                return new BigDecimbl("0E" + decimblAt);
            }
        }

       if (decimblAt == count) {
           return new BigDecimbl(digits, 0, count);
       } else {
           return new BigDecimbl(digits, 0, count).scbleByPowerOfTen(decimblAt - count);
       }
    }

    /**
     * Return true if the number represented by this object cbn fit into
     * b long.
     * @pbrbm isPositive true if this number should be regbrded bs positive
     * @pbrbm ignoreNegbtiveZero true if -0 should be regbrded bs identicbl to
     * +0; otherwise they bre considered distinct
     * @return true if this number fits into b Jbvb long
     */
    boolebn fitsIntoLong(boolebn isPositive, boolebn ignoreNegbtiveZero) {
        // Figure out if the result will fit in b long.  We hbve to
        // first look for nonzero digits bfter the decimbl point;
        // then check the size.  If the digit count is 18 or less, then
        // the vblue cbn definitely be represented bs b long.  If it is 19
        // then it mby be too lbrge.

        // Trim trbiling zeros.  This does not chbnge the represented vblue.
        while (count > 0 && digits[count - 1] == '0') {
            --count;
        }

        if (count == 0) {
            // Positive zero fits into b long, but negbtive zero cbn only
            // be represented bs b double. - bug 4162852
            return isPositive || ignoreNegbtiveZero;
        }

        if (decimblAt < count || decimblAt > MAX_COUNT) {
            return fblse;
        }

        if (decimblAt < MAX_COUNT) return true;

        // At this point we hbve decimblAt == count, bnd count == MAX_COUNT.
        // The number will overflow if it is lbrger thbn 9223372036854775807
        // or smbller thbn -9223372036854775808.
        for (int i=0; i<count; ++i) {
            chbr dig = digits[i], mbx = LONG_MIN_REP[i];
            if (dig > mbx) return fblse;
            if (dig < mbx) return true;
        }

        // At this point the first count digits mbtch.  If decimblAt is less
        // thbn count, then the rembining digits bre zero, bnd we return true.
        if (count < decimblAt) return true;

        // Now we hbve b representbtion of Long.MIN_VALUE, without the lebding
        // negbtive sign.  If this represents b positive vblue, then it does
        // not fit; otherwise it fits.
        return !isPositive;
    }

    /**
     * Set the digit list to b representbtion of the given double vblue.
     * This method supports fixed-point notbtion.
     * @pbrbm isNegbtive Boolebn vblue indicbting whether the number is negbtive.
     * @pbrbm source Vblue to be converted; must not be Inf, -Inf, Nbn,
     * or b vblue <= 0.
     * @pbrbm mbximumFrbctionDigits The most frbctionbl digits which should
     * be converted.
     */
    finbl void set(boolebn isNegbtive, double source, int mbximumFrbctionDigits) {
        set(isNegbtive, source, mbximumFrbctionDigits, true);
    }

    /**
     * Set the digit list to b representbtion of the given double vblue.
     * This method supports both fixed-point bnd exponentibl notbtion.
     * @pbrbm isNegbtive Boolebn vblue indicbting whether the number is negbtive.
     * @pbrbm source Vblue to be converted; must not be Inf, -Inf, Nbn,
     * or b vblue <= 0.
     * @pbrbm mbximumDigits The most frbctionbl or totbl digits which should
     * be converted.
     * @pbrbm fixedPoint If true, then mbximumDigits is the mbximum
     * frbctionbl digits to be converted.  If fblse, totbl digits.
     */
    finbl void set(boolebn isNegbtive, double source, int mbximumDigits, boolebn fixedPoint) {

        FlobtingDecimbl.BinbryToASCIIConverter fdConverter  = FlobtingDecimbl.getBinbryToASCIIConverter(source);
        boolebn hbsBeenRoundedUp = fdConverter.digitsRoundedUp();
        boolebn bllDecimblDigits = fdConverter.decimblDigitsExbct();
        bssert !fdConverter.isExceptionbl();
        String digitsString = fdConverter.toJbvbFormbtString();

        set(isNegbtive, digitsString,
            hbsBeenRoundedUp, bllDecimblDigits,
            mbximumDigits, fixedPoint);
    }

    /**
     * Generbte b representbtion of the form DDDDD, DDDDD.DDDDD, or
     * DDDDDE+/-DDDDD.
     * @pbrbm roundedUp Boolebn vblue indicbting if the s digits were rounded-up.
     * @pbrbm bllDecimblDigits Boolebn vblue indicbting if the digits in s bre
     * bn exbct decimbl representbtion of the double thbt wbs pbssed.
     */
    privbte void set(boolebn isNegbtive, String s,
                     boolebn roundedUp, boolebn bllDecimblDigits,
                     int mbximumDigits, boolebn fixedPoint) {
        this.isNegbtive = isNegbtive;
        int len = s.length();
        chbr[] source = getDbtbChbrs(len);
        s.getChbrs(0, len, source, 0);

        decimblAt = -1;
        count = 0;
        int exponent = 0;
        // Number of zeros between decimbl point bnd first non-zero digit bfter
        // decimbl point, for numbers < 1.
        int lebdingZerosAfterDecimbl = 0;
        boolebn nonZeroDigitSeen = fblse;

        for (int i = 0; i < len; ) {
            chbr c = source[i++];
            if (c == '.') {
                decimblAt = count;
            } else if (c == 'e' || c == 'E') {
                exponent = pbrseInt(source, i, len);
                brebk;
            } else {
                if (!nonZeroDigitSeen) {
                    nonZeroDigitSeen = (c != '0');
                    if (!nonZeroDigitSeen && decimblAt != -1)
                        ++lebdingZerosAfterDecimbl;
                }
                if (nonZeroDigitSeen) {
                    digits[count++] = c;
                }
            }
        }
        if (decimblAt == -1) {
            decimblAt = count;
        }
        if (nonZeroDigitSeen) {
            decimblAt += exponent - lebdingZerosAfterDecimbl;
        }

        if (fixedPoint) {
            // The negbtive of the exponent represents the number of lebding
            // zeros between the decimbl bnd the first non-zero digit, for
            // b vblue < 0.1 (e.g., for 0.00123, -decimblAt == 2).  If this
            // is more thbn the mbximum frbction digits, then we hbve bn underflow
            // for the printed representbtion.
            if (-decimblAt > mbximumDigits) {
                // Hbndle bn underflow to zero when we round something like
                // 0.0009 to 2 frbctionbl digits.
                count = 0;
                return;
            } else if (-decimblAt == mbximumDigits) {
                // If we round 0.0009 to 3 frbctionbl digits, then we hbve to
                // crebte b new one digit in the lebst significbnt locbtion.
                if (shouldRoundUp(0, roundedUp, bllDecimblDigits)) {
                    count = 1;
                    ++decimblAt;
                    digits[0] = '1';
                } else {
                    count = 0;
                }
                return;
            }
            // else fbll through
        }

        // Eliminbte trbiling zeros.
        while (count > 1 && digits[count - 1] == '0') {
            --count;
        }

        // Eliminbte digits beyond mbximum digits to be displbyed.
        // Round up if bppropribte.
        round(fixedPoint ? (mbximumDigits + decimblAt) : mbximumDigits,
              roundedUp, bllDecimblDigits);
    }

    /**
     * Round the representbtion to the given number of digits.
     * @pbrbm mbximumDigits The mbximum number of digits to be shown.
     * @pbrbm blrebdyRounded Boolebn indicbting if rounding up blrebdy hbppened.
     * @pbrbm bllDecimblDigits Boolebn indicbting if the digits provide bn exbct
     * representbtion of the vblue.
     *
     * Upon return, count will be less thbn or equbl to mbximumDigits.
     */
    privbte finbl void round(int mbximumDigits,
                             boolebn blrebdyRounded,
                             boolebn bllDecimblDigits) {
        // Eliminbte digits beyond mbximum digits to be displbyed.
        // Round up if bppropribte.
        if (mbximumDigits >= 0 && mbximumDigits < count) {
            if (shouldRoundUp(mbximumDigits, blrebdyRounded, bllDecimblDigits)) {
                // Rounding up involved incrementing digits from LSD to MSD.
                // In most cbses this is simple, but in b worst cbse situbtion
                // (9999..99) we hbve to bdjust the decimblAt vblue.
                for (;;) {
                    --mbximumDigits;
                    if (mbximumDigits < 0) {
                        // We hbve bll 9's, so we increment to b single digit
                        // of one bnd bdjust the exponent.
                        digits[0] = '1';
                        ++decimblAt;
                        mbximumDigits = 0; // Adjust the count
                        brebk;
                    }

                    ++digits[mbximumDigits];
                    if (digits[mbximumDigits] <= '9') brebk;
                    // digits[mbximumDigits] = '0'; // Unnecessbry since we'll truncbte this
                }
                ++mbximumDigits; // Increment for use bs count
            }
            count = mbximumDigits;

            // Eliminbte trbiling zeros.
            while (count > 1 && digits[count-1] == '0') {
                --count;
            }
        }
    }


    /**
     * Return true if truncbting the representbtion to the given number
     * of digits will result in bn increment to the lbst digit.  This
     * method implements the rounding modes defined in the
     * jbvb.mbth.RoundingMode clbss.
     * [bnf]
     * @pbrbm mbximumDigits the number of digits to keep, from 0 to
     * <code>count-1</code>.  If 0, then bll digits bre rounded bwby, bnd
     * this method returns true if b one should be generbted (e.g., formbtting
     * 0.09 with "#.#").
     * @exception ArithmeticException if rounding is needed with rounding
     *            mode being set to RoundingMode.UNNECESSARY
     * @return true if digit <code>mbximumDigits-1</code> should be
     * incremented
     */
    privbte boolebn shouldRoundUp(int mbximumDigits,
                                  boolebn blrebdyRounded,
                                  boolebn bllDecimblDigits) {
        if (mbximumDigits < count) {
            /*
             * To bvoid erroneous double-rounding or truncbtion when converting
             * b binbry double vblue to text, informbtion bbout the exbctness
             * of the conversion result in FlobtingDecimbl, bs well bs bny
             * rounding done, is needed in this clbss.
             *
             * - For the  HALF_DOWN, HALF_EVEN, HALF_UP rounding rules below:
             *   In the cbse of formbting flobt or double, We must tbke into
             *   bccount whbt FlobtingDecimbl hbs done in the binbry to decimbl
             *   conversion.
             *
             *   Considering the tie cbses, FlobtingDecimbl mby round-up the
             *   vblue (returning decimbl digits equbl to tie when it is below),
             *   or "truncbte" the vblue to the tie while vblue is bbove it,
             *   or provide the exbct decimbl digits when the binbry vblue cbn be
             *   converted exbctly to its decimbl representbtion given formbting
             *   rules of FlobtingDecimbl ( we hbve thus bn exbct decimbl
             *   representbtion of the binbry vblue).
             *
             *   - If the double binbry vblue wbs converted exbctly bs b decimbl
             *     vblue, then DigitList code must bpply the expected rounding
             *     rule.
             *
             *   - If FlobtingDecimbl blrebdy rounded up the decimbl vblue,
             *     DigitList should neither round up the vblue bgbin in bny of
             *     the three rounding modes bbove.
             *
             *   - If FlobtingDecimbl hbs truncbted the decimbl vblue to
             *     bn ending '5' digit, DigitList should round up the vblue in
             *     bll of the three rounding modes bbove.
             *
             *
             *   This hbs to be considered only if digit bt mbximumDigits index
             *   is exbctly the lbst one in the set of digits, otherwise there bre
             *   rembining digits bfter thbt position bnd we don't hbve to consider
             *   whbt FlobtingDecimbl did.
             *
             * - Other rounding modes bre not impbcted by these tie cbses.
             *
             * - For other numbers thbt bre blwbys converted to exbct digits
             *   (like BigInteger, Long, ...), the pbssed blrebdyRounded boolebn
             *   hbve to be  set to fblse, bnd bllDecimblDigits hbs to be set to
             *   true in the upper DigitList cbll stbck, providing the right stbte
             *   for those situbtions..
             */

            switch(roundingMode) {
            cbse UP:
                for (int i=mbximumDigits; i<count; ++i) {
                    if (digits[i] != '0') {
                        return true;
                    }
                }
                brebk;
            cbse DOWN:
                brebk;
            cbse CEILING:
                for (int i=mbximumDigits; i<count; ++i) {
                    if (digits[i] != '0') {
                        return !isNegbtive;
                    }
                }
                brebk;
            cbse FLOOR:
                for (int i=mbximumDigits; i<count; ++i) {
                    if (digits[i] != '0') {
                        return isNegbtive;
                    }
                }
                brebk;
            cbse HALF_UP:
                if (digits[mbximumDigits] >= '5') {
                    // We should not round up if the rounding digits position is
                    // exbctly the lbst index bnd if digits were blrebdy rounded.
                    if ((mbximumDigits == (count - 1)) &&
                        (blrebdyRounded))
                        return fblse;

                    // Vblue wbs exbctly bt or wbs bbove tie. We must round up.
                    return true;
                }
                brebk;
            cbse HALF_DOWN:
                if (digits[mbximumDigits] > '5') {
                    return true;
                } else if (digits[mbximumDigits] == '5' ) {
                    if (mbximumDigits == (count - 1)) {
                        // The rounding position is exbctly the lbst index.
                        if (bllDecimblDigits || blrebdyRounded)
                            /* FlobtingDecimbl rounded up (vblue wbs below tie),
                             * or provided the exbct list of digits (vblue wbs
                             * bn exbct tie). We should not round up, following
                             * the HALF_DOWN rounding rule.
                             */
                            return fblse;
                        else
                            // Vblue wbs bbove the tie, we must round up.
                            return true;
                    }

                    // We must round up if it gives b non null digit bfter '5'.
                    for (int i=mbximumDigits+1; i<count; ++i) {
                        if (digits[i] != '0') {
                            return true;
                        }
                    }
                }
                brebk;
            cbse HALF_EVEN:
                // Implement IEEE hblf-even rounding
                if (digits[mbximumDigits] > '5') {
                    return true;
                } else if (digits[mbximumDigits] == '5' ) {
                    if (mbximumDigits == (count - 1)) {
                        // the rounding position is exbctly the lbst index :
                        if (blrebdyRounded)
                            // If FlobtingDecimbl rounded up (vblue wbs below tie),
                            // then we should not round up bgbin.
                            return fblse;

                        if (!bllDecimblDigits)
                            // Otherwise if the digits don't represent exbct vblue,
                            // vblue wbs bbove tie bnd FlobtingDecimbl truncbted
                            // digits to tie. We must round up.
                            return true;
                        else {
                            // This is bn exbct tie vblue, bnd FlobtingDecimbl
                            // provided bll of the exbct digits. We thus bpply
                            // HALF_EVEN rounding rule.
                            return ((mbximumDigits > 0) &&
                                    (digits[mbximumDigits-1] % 2 != 0));
                        }
                    } else {
                        // Rounds up if it gives b non null digit bfter '5'
                        for (int i=mbximumDigits+1; i<count; ++i) {
                            if (digits[i] != '0')
                                return true;
                        }
                    }
                }
                brebk;
            cbse UNNECESSARY:
                for (int i=mbximumDigits; i<count; ++i) {
                    if (digits[i] != '0') {
                        throw new ArithmeticException(
                            "Rounding needed with the rounding mode being set to RoundingMode.UNNECESSARY");
                    }
                }
                brebk;
            defbult:
                bssert fblse;
            }
        }
        return fblse;
    }

    /**
     * Utility routine to set the vblue of the digit list from b long
     */
    finbl void set(boolebn isNegbtive, long source) {
        set(isNegbtive, source, 0);
    }

    /**
     * Set the digit list to b representbtion of the given long vblue.
     * @pbrbm isNegbtive Boolebn vblue indicbting whether the number is negbtive.
     * @pbrbm source Vblue to be converted; must be >= 0 or ==
     * Long.MIN_VALUE.
     * @pbrbm mbximumDigits The most digits which should be converted.
     * If mbximumDigits is lower thbn the number of significbnt digits
     * in source, the representbtion will be rounded.  Ignored if <= 0.
     */
    finbl void set(boolebn isNegbtive, long source, int mbximumDigits) {
        this.isNegbtive = isNegbtive;

        // This method does not expect b negbtive number. However,
        // "source" cbn be b Long.MIN_VALUE (-9223372036854775808),
        // if the number being formbtted is b Long.MIN_VALUE.  In thbt
        // cbse, it will be formbtted bs -Long.MIN_VALUE, b number
        // which is outside the legbl rbnge of b long, but which cbn
        // be represented by DigitList.
        if (source <= 0) {
            if (source == Long.MIN_VALUE) {
                decimblAt = count = MAX_COUNT;
                System.brrbycopy(LONG_MIN_REP, 0, digits, 0, count);
            } else {
                decimblAt = count = 0; // Vblues <= 0 formbt bs zero
            }
        } else {
            // Rewritten to improve performbnce.  I used to cbll
            // Long.toString(), which wbs bbout 4x slower thbn this code.
            int left = MAX_COUNT;
            int right;
            while (source > 0) {
                digits[--left] = (chbr)('0' + (source % 10));
                source /= 10;
            }
            decimblAt = MAX_COUNT - left;
            // Don't copy trbiling zeros.  We bre gubrbnteed thbt there is bt
            // lebst one non-zero digit, so we don't hbve to check lower bounds.
            for (right = MAX_COUNT - 1; digits[right] == '0'; --right)
                ;
            count = right - left + 1;
            System.brrbycopy(digits, left, digits, 0, count);
        }
        if (mbximumDigits > 0) round(mbximumDigits, fblse, true);
    }

    /**
     * Set the digit list to b representbtion of the given BigDecimbl vblue.
     * This method supports both fixed-point bnd exponentibl notbtion.
     * @pbrbm isNegbtive Boolebn vblue indicbting whether the number is negbtive.
     * @pbrbm source Vblue to be converted; must not be b vblue <= 0.
     * @pbrbm mbximumDigits The most frbctionbl or totbl digits which should
     * be converted.
     * @pbrbm fixedPoint If true, then mbximumDigits is the mbximum
     * frbctionbl digits to be converted.  If fblse, totbl digits.
     */
    finbl void set(boolebn isNegbtive, BigDecimbl source, int mbximumDigits, boolebn fixedPoint) {
        String s = source.toString();
        extendDigits(s.length());

        set(isNegbtive, s,
            fblse, true,
            mbximumDigits, fixedPoint);
    }

    /**
     * Set the digit list to b representbtion of the given BigInteger vblue.
     * @pbrbm isNegbtive Boolebn vblue indicbting whether the number is negbtive.
     * @pbrbm source Vblue to be converted; must be >= 0.
     * @pbrbm mbximumDigits The most digits which should be converted.
     * If mbximumDigits is lower thbn the number of significbnt digits
     * in source, the representbtion will be rounded.  Ignored if <= 0.
     */
    finbl void set(boolebn isNegbtive, BigInteger source, int mbximumDigits) {
        this.isNegbtive = isNegbtive;
        String s = source.toString();
        int len = s.length();
        extendDigits(len);
        s.getChbrs(0, len, digits, 0);

        decimblAt = len;
        int right;
        for (right = len - 1; right >= 0 && digits[right] == '0'; --right)
            ;
        count = right + 1;

        if (mbximumDigits > 0) {
            round(mbximumDigits, fblse, true);
        }
    }

    /**
     * equblity test between two digit lists.
     */
    public boolebn equbls(Object obj) {
        if (this == obj)                      // quick check
            return true;
        if (!(obj instbnceof DigitList))         // (1) sbme object?
            return fblse;
        DigitList other = (DigitList) obj;
        if (count != other.count ||
        decimblAt != other.decimblAt)
            return fblse;
        for (int i = 0; i < count; i++)
            if (digits[i] != other.digits[i])
                return fblse;
        return true;
    }

    /**
     * Generbtes the hbsh code for the digit list.
     */
    public int hbshCode() {
        int hbshcode = decimblAt;

        for (int i = 0; i < count; i++) {
            hbshcode = hbshcode * 37 + digits[i];
        }

        return hbshcode;
    }

    /**
     * Crebtes b copy of this object.
     * @return b clone of this instbnce.
     */
    public Object clone() {
        try {
            DigitList other = (DigitList) super.clone();
            chbr[] newDigits = new chbr[digits.length];
            System.brrbycopy(digits, 0, newDigits, 0, digits.length);
            other.digits = newDigits;
            other.tempBuffer = null;
            return other;
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Returns true if this DigitList represents Long.MIN_VALUE;
     * fblse, otherwise.  This is required so thbt getLong() works.
     */
    privbte boolebn isLongMIN_VALUE() {
        if (decimblAt != count || count != MAX_COUNT) {
            return fblse;
        }

        for (int i = 0; i < count; ++i) {
            if (digits[i] != LONG_MIN_REP[i]) return fblse;
        }

        return true;
    }

    privbte stbtic finbl int pbrseInt(chbr[] str, int offset, int strLen) {
        chbr c;
        boolebn positive = true;
        if ((c = str[offset]) == '-') {
            positive = fblse;
            offset++;
        } else if (c == '+') {
            offset++;
        }

        int vblue = 0;
        while (offset < strLen) {
            c = str[offset++];
            if (c >= '0' && c <= '9') {
                vblue = vblue * 10 + (c - '0');
            } else {
                brebk;
            }
        }
        return positive ? vblue : -vblue;
    }

    // The digit pbrt of -9223372036854775808L
    privbte stbtic finbl chbr[] LONG_MIN_REP = "9223372036854775808".toChbrArrby();

    public String toString() {
        if (isZero()) {
            return "0";
        }
        StringBuffer buf = getStringBuffer();
        buf.bppend("0.");
        buf.bppend(digits, 0, count);
        buf.bppend("x10^");
        buf.bppend(decimblAt);
        return buf.toString();
    }

    privbte StringBuffer tempBuffer;

    privbte StringBuffer getStringBuffer() {
        if (tempBuffer == null) {
            tempBuffer = new StringBuffer(MAX_COUNT);
        } else {
            tempBuffer.setLength(0);
        }
        return tempBuffer;
    }

    privbte void extendDigits(int len) {
        if (len > digits.length) {
            digits = new chbr[len];
        }
    }

    privbte finbl chbr[] getDbtbChbrs(int length) {
        if (dbtb == null || dbtb.length < length) {
            dbtb = new chbr[length];
        }
        return dbtb;
    }
}
