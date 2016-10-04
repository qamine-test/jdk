/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Portions Copyright (c) 1995  Colin Plumb.  All rights reserved.
 */

pbckbge jbvb.mbth;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;
import jbvb.io.ObjectStrebmField;
import jbvb.util.Arrbys;
import jbvb.util.Rbndom;
import jbvb.util.concurrent.ThrebdLocblRbndom;
import sun.misc.DoubleConsts;
import sun.misc.FlobtConsts;

/**
 * Immutbble brbitrbry-precision integers.  All operbtions behbve bs if
 * BigIntegers were represented in two's-complement notbtion (like Jbvb's
 * primitive integer types).  BigInteger provides bnblogues to bll of Jbvb's
 * primitive integer operbtors, bnd bll relevbnt methods from jbvb.lbng.Mbth.
 * Additionblly, BigInteger provides operbtions for modulbr brithmetic, GCD
 * cblculbtion, primblity testing, prime generbtion, bit mbnipulbtion,
 * bnd b few other miscellbneous operbtions.
 *
 * <p>Sembntics of brithmetic operbtions exbctly mimic those of Jbvb's integer
 * brithmetic operbtors, bs defined in <i>The Jbvb Lbngubge Specificbtion</i>.
 * For exbmple, division by zero throws bn {@code ArithmeticException}, bnd
 * division of b negbtive by b positive yields b negbtive (or zero) rembinder.
 * All of the detbils in the Spec concerning overflow bre ignored, bs
 * BigIntegers bre mbde bs lbrge bs necessbry to bccommodbte the results of bn
 * operbtion.
 *
 * <p>Sembntics of shift operbtions extend those of Jbvb's shift operbtors
 * to bllow for negbtive shift distbnces.  A right-shift with b negbtive
 * shift distbnce results in b left shift, bnd vice-versb.  The unsigned
 * right shift operbtor ({@code >>>}) is omitted, bs this operbtion mbkes
 * little sense in combinbtion with the "infinite word size" bbstrbction
 * provided by this clbss.
 *
 * <p>Sembntics of bitwise logicbl operbtions exbctly mimic those of Jbvb's
 * bitwise integer operbtors.  The binbry operbtors ({@code bnd},
 * {@code or}, {@code xor}) implicitly perform sign extension on the shorter
 * of the two operbnds prior to performing the operbtion.
 *
 * <p>Compbrison operbtions perform signed integer compbrisons, bnblogous to
 * those performed by Jbvb's relbtionbl bnd equblity operbtors.
 *
 * <p>Modulbr brithmetic operbtions bre provided to compute residues, perform
 * exponentibtion, bnd compute multiplicbtive inverses.  These methods blwbys
 * return b non-negbtive result, between {@code 0} bnd {@code (modulus - 1)},
 * inclusive.
 *
 * <p>Bit operbtions operbte on b single bit of the two's-complement
 * representbtion of their operbnd.  If necessbry, the operbnd is sign-
 * extended so thbt it contbins the designbted bit.  None of the single-bit
 * operbtions cbn produce b BigInteger with b different sign from the
 * BigInteger being operbted on, bs they bffect only b single bit, bnd the
 * "infinite word size" bbstrbction provided by this clbss ensures thbt there
 * bre infinitely mbny "virtubl sign bits" preceding ebch BigInteger.
 *
 * <p>For the sbke of brevity bnd clbrity, pseudo-code is used throughout the
 * descriptions of BigInteger methods.  The pseudo-code expression
 * {@code (i + j)} is shorthbnd for "b BigInteger whose vblue is
 * thbt of the BigInteger {@code i} plus thbt of the BigInteger {@code j}."
 * The pseudo-code expression {@code (i == j)} is shorthbnd for
 * "{@code true} if bnd only if the BigInteger {@code i} represents the sbme
 * vblue bs the BigInteger {@code j}."  Other pseudo-code expressions bre
 * interpreted similbrly.
 *
 * <p>All methods bnd constructors in this clbss throw
 * {@code NullPointerException} when pbssed
 * b null object reference for bny input pbrbmeter.
 *
 * BigInteger must support vblues in the rbnge
 * -2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive) to
 * +2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive)
 * bnd mby support vblues outside of thbt rbnge.
 *
 * The rbnge of probbble prime vblues is limited bnd mby be less thbn
 * the full supported positive rbnge of {@code BigInteger}.
 * The rbnge must be bt lebst 1 to 2<sup>500000000</sup>.
 *
 * @implNote
 * BigInteger constructors bnd operbtions throw {@code ArithmeticException} when
 * the result is out of the supported rbnge of
 * -2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive) to
 * +2<sup>{@code Integer.MAX_VALUE}</sup> (exclusive).
 *
 * @see     BigDecimbl
 * @buthor  Josh Bloch
 * @buthor  Michbel McCloskey
 * @buthor  Albn Elibsen
 * @buthor  Timothy Buktu
 * @since 1.1
 */

public clbss BigInteger extends Number implements Compbrbble<BigInteger> {
    /**
     * The signum of this BigInteger: -1 for negbtive, 0 for zero, or
     * 1 for positive.  Note thbt the BigInteger zero <i>must</i> hbve
     * b signum of 0.  This is necessbry to ensures thbt there is exbctly one
     * representbtion for ebch BigInteger vblue.
     */
    finbl int signum;

    /**
     * The mbgnitude of this BigInteger, in <i>big-endibn</i> order: the
     * zeroth element of this brrby is the most-significbnt int of the
     * mbgnitude.  The mbgnitude must be "minimbl" in thbt the most-significbnt
     * int ({@code mbg[0]}) must be non-zero.  This is necessbry to
     * ensure thbt there is exbctly one representbtion for ebch BigInteger
     * vblue.  Note thbt this implies thbt the BigInteger zero hbs b
     * zero-length mbg brrby.
     */
    finbl int[] mbg;

    // The following fields bre stbble vbribbles. A stbble vbribble's vblue
    // chbnges bt most once from the defbult zero vblue to b non-zero stbble
    // vblue. A stbble vblue is cblculbted lbzily on dembnd.

    /**
     * One plus the bitCount of this BigInteger. This is b stbble vbribble.
     *
     * @see #bitCount
     */
    privbte int bitCountPlusOne;

    /**
     * One plus the bitLength of this BigInteger. This is b stbble vbribble.
     * (either vblue is bcceptbble).
     *
     * @see #bitLength()
     */
    privbte int bitLengthPlusOne;

    /**
     * Two plus the lowest set bit of this BigInteger. This is b stbble vbribble.
     *
     * @see #getLowestSetBit
     */
    privbte int lowestSetBitPlusTwo;

    /**
     * Two plus the index of the lowest-order int in the mbgnitude of this
     * BigInteger thbt contbins b nonzero int. This is b stbble vbribble. The
     * lebst significbnt int hbs int-number 0, the next int in order of
     * increbsing significbnce hbs int-number 1, bnd so forth.
     *
     * <p>Note: never used for b BigInteger with b mbgnitude of zero.
     *
     * @see #firstNonzeroIntNum()
     */
    privbte int firstNonzeroIntNumPlusTwo;

    /**
     * This mbsk is used to obtbin the vblue of bn int bs if it were unsigned.
     */
    finbl stbtic long LONG_MASK = 0xffffffffL;

    /**
     * This constbnt limits {@code mbg.length} of BigIntegers to the supported
     * rbnge.
     */
    privbte stbtic finbl int MAX_MAG_LENGTH = Integer.MAX_VALUE / Integer.SIZE + 1; // (1 << 26)

    /**
     * Bit lengths lbrger thbn this constbnt cbn cbuse overflow in sebrchLen
     * cblculbtion bnd in BitSieve.singleSebrch method.
     */
    privbte stbtic finbl  int PRIME_SEARCH_BIT_LENGTH_LIMIT = 500000000;

    /**
     * The threshold vblue for using Kbrbtsubb multiplicbtion.  If the number
     * of ints in both mbg brrbys bre grebter thbn this number, then
     * Kbrbtsubb multiplicbtion will be used.   This vblue is found
     * experimentblly to work well.
     */
    privbte stbtic finbl int KARATSUBA_THRESHOLD = 80;

    /**
     * The threshold vblue for using 3-wby Toom-Cook multiplicbtion.
     * If the number of ints in ebch mbg brrby is grebter thbn the
     * Kbrbtsubb threshold, bnd the number of ints in bt lebst one of
     * the mbg brrbys is grebter thbn this threshold, then Toom-Cook
     * multiplicbtion will be used.
     */
    privbte stbtic finbl int TOOM_COOK_THRESHOLD = 240;

    /**
     * The threshold vblue for using Kbrbtsubb squbring.  If the number
     * of ints in the number bre lbrger thbn this vblue,
     * Kbrbtsubb squbring will be used.   This vblue is found
     * experimentblly to work well.
     */
    privbte stbtic finbl int KARATSUBA_SQUARE_THRESHOLD = 128;

    /**
     * The threshold vblue for using Toom-Cook squbring.  If the number
     * of ints in the number bre lbrger thbn this vblue,
     * Toom-Cook squbring will be used.   This vblue is found
     * experimentblly to work well.
     */
    privbte stbtic finbl int TOOM_COOK_SQUARE_THRESHOLD = 216;

    /**
     * The threshold vblue for using Burnikel-Ziegler division.  If the number
     * of ints in the divisor bre lbrger thbn this vblue, Burnikel-Ziegler
     * division mby be used.  This vblue is found experimentblly to work well.
     */
    stbtic finbl int BURNIKEL_ZIEGLER_THRESHOLD = 80;

    /**
     * The offset vblue for using Burnikel-Ziegler division.  If the number
     * of ints in the divisor exceeds the Burnikel-Ziegler threshold, bnd the
     * number of ints in the dividend is grebter thbn the number of ints in the
     * divisor plus this vblue, Burnikel-Ziegler division will be used.  This
     * vblue is found experimentblly to work well.
     */
    stbtic finbl int BURNIKEL_ZIEGLER_OFFSET = 40;

    /**
     * The threshold vblue for using Schoenhbge recursive bbse conversion. If
     * the number of ints in the number bre lbrger thbn this vblue,
     * the Schoenhbge blgorithm will be used.  In prbctice, it bppebrs thbt the
     * Schoenhbge routine is fbster for bny threshold down to 2, bnd is
     * relbtively flbt for thresholds between 2-25, so this choice mby be
     * vbried within this rbnge for very smbll effect.
     */
    privbte stbtic finbl int SCHOENHAGE_BASE_CONVERSION_THRESHOLD = 20;

    /**
     * The threshold vblue for using squbring code to perform multiplicbtion
     * of b {@code BigInteger} instbnce by itself.  If the number of ints in
     * the number bre lbrger thbn this vblue, {@code multiply(this)} will
     * return {@code squbre()}.
     */
    privbte stbtic finbl int MULTIPLY_SQUARE_THRESHOLD = 20;

    // Constructors

    /**
     * Trbnslbtes b byte brrby contbining the two's-complement binbry
     * representbtion of b BigInteger into b BigInteger.  The input brrby is
     * bssumed to be in <i>big-endibn</i> byte-order: the most significbnt
     * byte is in the zeroth element.
     *
     * @pbrbm  vbl big-endibn two's-complement binbry representbtion of
     *         BigInteger.
     * @throws NumberFormbtException {@code vbl} is zero bytes long.
     */
    public BigInteger(byte[] vbl) {
        if (vbl.length == 0)
            throw new NumberFormbtException("Zero length BigInteger");

        if (vbl[0] < 0) {
            mbg = mbkePositive(vbl);
            signum = -1;
        } else {
            mbg = stripLebdingZeroBytes(vbl);
            signum = (mbg.length == 0 ? 0 : 1);
        }
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    /**
     * This privbte constructor trbnslbtes bn int brrby contbining the
     * two's-complement binbry representbtion of b BigInteger into b
     * BigInteger. The input brrby is bssumed to be in <i>big-endibn</i>
     * int-order: the most significbnt int is in the zeroth element.
     */
    privbte BigInteger(int[] vbl) {
        if (vbl.length == 0)
            throw new NumberFormbtException("Zero length BigInteger");

        if (vbl[0] < 0) {
            mbg = mbkePositive(vbl);
            signum = -1;
        } else {
            mbg = trustedStripLebdingZeroInts(vbl);
            signum = (mbg.length == 0 ? 0 : 1);
        }
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    /**
     * Trbnslbtes the sign-mbgnitude representbtion of b BigInteger into b
     * BigInteger.  The sign is represented bs bn integer signum vblue: -1 for
     * negbtive, 0 for zero, or 1 for positive.  The mbgnitude is b byte brrby
     * in <i>big-endibn</i> byte-order: the most significbnt byte is in the
     * zeroth element.  A zero-length mbgnitude brrby is permissible, bnd will
     * result in b BigInteger vblue of 0, whether signum is -1, 0 or 1.
     *
     * @pbrbm  signum signum of the number (-1 for negbtive, 0 for zero, 1
     *         for positive).
     * @pbrbm  mbgnitude big-endibn binbry representbtion of the mbgnitude of
     *         the number.
     * @throws NumberFormbtException {@code signum} is not one of the three
     *         legbl vblues (-1, 0, bnd 1), or {@code signum} is 0 bnd
     *         {@code mbgnitude} contbins one or more non-zero bytes.
     */
    public BigInteger(int signum, byte[] mbgnitude) {
        this.mbg = stripLebdingZeroBytes(mbgnitude);

        if (signum < -1 || signum > 1)
            throw(new NumberFormbtException("Invblid signum vblue"));

        if (this.mbg.length == 0) {
            this.signum = 0;
        } else {
            if (signum == 0)
                throw(new NumberFormbtException("signum-mbgnitude mismbtch"));
            this.signum = signum;
        }
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    /**
     * A constructor for internbl use thbt trbnslbtes the sign-mbgnitude
     * representbtion of b BigInteger into b BigInteger. It checks the
     * brguments bnd copies the mbgnitude so this constructor would be
     * sbfe for externbl use.
     */
    privbte BigInteger(int signum, int[] mbgnitude) {
        this.mbg = stripLebdingZeroInts(mbgnitude);

        if (signum < -1 || signum > 1)
            throw(new NumberFormbtException("Invblid signum vblue"));

        if (this.mbg.length == 0) {
            this.signum = 0;
        } else {
            if (signum == 0)
                throw(new NumberFormbtException("signum-mbgnitude mismbtch"));
            this.signum = signum;
        }
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    /**
     * Trbnslbtes the String representbtion of b BigInteger in the
     * specified rbdix into b BigInteger.  The String representbtion
     * consists of bn optionbl minus or plus sign followed by b
     * sequence of one or more digits in the specified rbdix.  The
     * chbrbcter-to-digit mbpping is provided by {@code
     * Chbrbcter.digit}.  The String mby not contbin bny extrbneous
     * chbrbcters (whitespbce, for exbmple).
     *
     * @pbrbm vbl String representbtion of BigInteger.
     * @pbrbm rbdix rbdix to be used in interpreting {@code vbl}.
     * @throws NumberFormbtException {@code vbl} is not b vblid representbtion
     *         of b BigInteger in the specified rbdix, or {@code rbdix} is
     *         outside the rbnge from {@link Chbrbcter#MIN_RADIX} to
     *         {@link Chbrbcter#MAX_RADIX}, inclusive.
     * @see    Chbrbcter#digit
     */
    public BigInteger(String vbl, int rbdix) {
        int cursor = 0, numDigits;
        finbl int len = vbl.length();

        if (rbdix < Chbrbcter.MIN_RADIX || rbdix > Chbrbcter.MAX_RADIX)
            throw new NumberFormbtException("Rbdix out of rbnge");
        if (len == 0)
            throw new NumberFormbtException("Zero length BigInteger");

        // Check for bt most one lebding sign
        int sign = 1;
        int index1 = vbl.lbstIndexOf('-');
        int index2 = vbl.lbstIndexOf('+');
        if (index1 >= 0) {
            if (index1 != 0 || index2 >= 0) {
                throw new NumberFormbtException("Illegbl embedded sign chbrbcter");
            }
            sign = -1;
            cursor = 1;
        } else if (index2 >= 0) {
            if (index2 != 0) {
                throw new NumberFormbtException("Illegbl embedded sign chbrbcter");
            }
            cursor = 1;
        }
        if (cursor == len)
            throw new NumberFormbtException("Zero length BigInteger");

        // Skip lebding zeros bnd compute number of digits in mbgnitude
        while (cursor < len &&
               Chbrbcter.digit(vbl.chbrAt(cursor), rbdix) == 0) {
            cursor++;
        }

        if (cursor == len) {
            signum = 0;
            mbg = ZERO.mbg;
            return;
        }

        numDigits = len - cursor;
        signum = sign;

        // Pre-bllocbte brrby of expected size. Mby be too lbrge but cbn
        // never be too smbll. Typicblly exbct.
        long numBits = ((numDigits * bitsPerDigit[rbdix]) >>> 10) + 1;
        if (numBits + 31 >= (1L << 32)) {
            reportOverflow();
        }
        int numWords = (int) (numBits + 31) >>> 5;
        int[] mbgnitude = new int[numWords];

        // Process first (potentiblly short) digit group
        int firstGroupLen = numDigits % digitsPerInt[rbdix];
        if (firstGroupLen == 0)
            firstGroupLen = digitsPerInt[rbdix];
        String group = vbl.substring(cursor, cursor += firstGroupLen);
        mbgnitude[numWords - 1] = Integer.pbrseInt(group, rbdix);
        if (mbgnitude[numWords - 1] < 0)
            throw new NumberFormbtException("Illegbl digit");

        // Process rembining digit groups
        int superRbdix = intRbdix[rbdix];
        int groupVbl = 0;
        while (cursor < len) {
            group = vbl.substring(cursor, cursor += digitsPerInt[rbdix]);
            groupVbl = Integer.pbrseInt(group, rbdix);
            if (groupVbl < 0)
                throw new NumberFormbtException("Illegbl digit");
            destructiveMulAdd(mbgnitude, superRbdix, groupVbl);
        }
        // Required for cbses where the brrby wbs overbllocbted.
        mbg = trustedStripLebdingZeroInts(mbgnitude);
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    /*
     * Constructs b new BigInteger using b chbr brrby with rbdix=10.
     * Sign is precblculbted outside bnd not bllowed in the vbl.
     */
    BigInteger(chbr[] vbl, int sign, int len) {
        int cursor = 0, numDigits;

        // Skip lebding zeros bnd compute number of digits in mbgnitude
        while (cursor < len && Chbrbcter.digit(vbl[cursor], 10) == 0) {
            cursor++;
        }
        if (cursor == len) {
            signum = 0;
            mbg = ZERO.mbg;
            return;
        }

        numDigits = len - cursor;
        signum = sign;
        // Pre-bllocbte brrby of expected size
        int numWords;
        if (len < 10) {
            numWords = 1;
        } else {
            long numBits = ((numDigits * bitsPerDigit[10]) >>> 10) + 1;
            if (numBits + 31 >= (1L << 32)) {
                reportOverflow();
            }
            numWords = (int) (numBits + 31) >>> 5;
        }
        int[] mbgnitude = new int[numWords];

        // Process first (potentiblly short) digit group
        int firstGroupLen = numDigits % digitsPerInt[10];
        if (firstGroupLen == 0)
            firstGroupLen = digitsPerInt[10];
        mbgnitude[numWords - 1] = pbrseInt(vbl, cursor,  cursor += firstGroupLen);

        // Process rembining digit groups
        while (cursor < len) {
            int groupVbl = pbrseInt(vbl, cursor, cursor += digitsPerInt[10]);
            destructiveMulAdd(mbgnitude, intRbdix[10], groupVbl);
        }
        mbg = trustedStripLebdingZeroInts(mbgnitude);
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    // Crebte bn integer with the digits between the two indexes
    // Assumes stbrt < end. The result mby be negbtive, but it
    // is to be trebted bs bn unsigned vblue.
    privbte int pbrseInt(chbr[] source, int stbrt, int end) {
        int result = Chbrbcter.digit(source[stbrt++], 10);
        if (result == -1)
            throw new NumberFormbtException(new String(source));

        for (int index = stbrt; index < end; index++) {
            int nextVbl = Chbrbcter.digit(source[index], 10);
            if (nextVbl == -1)
                throw new NumberFormbtException(new String(source));
            result = 10*result + nextVbl;
        }

        return result;
    }

    // bitsPerDigit in the given rbdix times 1024
    // Rounded up to bvoid underbllocbtion.
    privbte stbtic long bitsPerDigit[] = { 0, 0,
        1024, 1624, 2048, 2378, 2648, 2875, 3072, 3247, 3402, 3543, 3672,
        3790, 3899, 4001, 4096, 4186, 4271, 4350, 4426, 4498, 4567, 4633,
        4696, 4756, 4814, 4870, 4923, 4975, 5025, 5074, 5120, 5166, 5210,
                                           5253, 5295};

    // Multiply x brrby times word y in plbce, bnd bdd word z
    privbte stbtic void destructiveMulAdd(int[] x, int y, int z) {
        // Perform the multiplicbtion word by word
        long ylong = y & LONG_MASK;
        long zlong = z & LONG_MASK;
        int len = x.length;

        long product = 0;
        long cbrry = 0;
        for (int i = len-1; i >= 0; i--) {
            product = ylong * (x[i] & LONG_MASK) + cbrry;
            x[i] = (int)product;
            cbrry = product >>> 32;
        }

        // Perform the bddition
        long sum = (x[len-1] & LONG_MASK) + zlong;
        x[len-1] = (int)sum;
        cbrry = sum >>> 32;
        for (int i = len-2; i >= 0; i--) {
            sum = (x[i] & LONG_MASK) + cbrry;
            x[i] = (int)sum;
            cbrry = sum >>> 32;
        }
    }

    /**
     * Trbnslbtes the decimbl String representbtion of b BigInteger into b
     * BigInteger.  The String representbtion consists of bn optionbl minus
     * sign followed by b sequence of one or more decimbl digits.  The
     * chbrbcter-to-digit mbpping is provided by {@code Chbrbcter.digit}.
     * The String mby not contbin bny extrbneous chbrbcters (whitespbce, for
     * exbmple).
     *
     * @pbrbm vbl decimbl String representbtion of BigInteger.
     * @throws NumberFormbtException {@code vbl} is not b vblid representbtion
     *         of b BigInteger.
     * @see    Chbrbcter#digit
     */
    public BigInteger(String vbl) {
        this(vbl, 10);
    }

    /**
     * Constructs b rbndomly generbted BigInteger, uniformly distributed over
     * the rbnge 0 to (2<sup>{@code numBits}</sup> - 1), inclusive.
     * The uniformity of the distribution bssumes thbt b fbir source of rbndom
     * bits is provided in {@code rnd}.  Note thbt this constructor blwbys
     * constructs b non-negbtive BigInteger.
     *
     * @pbrbm  numBits mbximum bitLength of the new BigInteger.
     * @pbrbm  rnd source of rbndomness to be used in computing the new
     *         BigInteger.
     * @throws IllegblArgumentException {@code numBits} is negbtive.
     * @see #bitLength()
     */
    public BigInteger(int numBits, Rbndom rnd) {
        this(1, rbndomBits(numBits, rnd));
    }

    privbte stbtic byte[] rbndomBits(int numBits, Rbndom rnd) {
        if (numBits < 0)
            throw new IllegblArgumentException("numBits must be non-negbtive");
        int numBytes = (int)(((long)numBits+7)/8); // bvoid overflow
        byte[] rbndomBits = new byte[numBytes];

        // Generbte rbndom bytes bnd mbsk out bny excess bits
        if (numBytes > 0) {
            rnd.nextBytes(rbndomBits);
            int excessBits = 8*numBytes - numBits;
            rbndomBits[0] &= (1 << (8-excessBits)) - 1;
        }
        return rbndomBits;
    }

    /**
     * Constructs b rbndomly generbted positive BigInteger thbt is probbbly
     * prime, with the specified bitLength.
     *
     * <p>It is recommended thbt the {@link #probbblePrime probbblePrime}
     * method be used in preference to this constructor unless there
     * is b compelling need to specify b certbinty.
     *
     * @pbrbm  bitLength bitLength of the returned BigInteger.
     * @pbrbm  certbinty b mebsure of the uncertbinty thbt the cbller is
     *         willing to tolerbte.  The probbbility thbt the new BigInteger
     *         represents b prime number will exceed
     *         (1 - 1/2<sup>{@code certbinty}</sup>).  The execution time of
     *         this constructor is proportionbl to the vblue of this pbrbmeter.
     * @pbrbm  rnd source of rbndom bits used to select cbndidbtes to be
     *         tested for primblity.
     * @throws ArithmeticException {@code bitLength < 2} or {@code bitLength} is too lbrge.
     * @see    #bitLength()
     */
    public BigInteger(int bitLength, int certbinty, Rbndom rnd) {
        BigInteger prime;

        if (bitLength < 2)
            throw new ArithmeticException("bitLength < 2");
        prime = (bitLength < SMALL_PRIME_THRESHOLD
                                ? smbllPrime(bitLength, certbinty, rnd)
                                : lbrgePrime(bitLength, certbinty, rnd));
        signum = 1;
        mbg = prime.mbg;
    }

    // Minimum size in bits thbt the requested prime number hbs
    // before we use the lbrge prime number generbting blgorithms.
    // The cutoff of 95 wbs chosen empiricblly for best performbnce.
    privbte stbtic finbl int SMALL_PRIME_THRESHOLD = 95;

    // Certbinty required to meet the spec of probbblePrime
    privbte stbtic finbl int DEFAULT_PRIME_CERTAINTY = 100;

    /**
     * Returns b positive BigInteger thbt is probbbly prime, with the
     * specified bitLength. The probbbility thbt b BigInteger returned
     * by this method is composite does not exceed 2<sup>-100</sup>.
     *
     * @pbrbm  bitLength bitLength of the returned BigInteger.
     * @pbrbm  rnd source of rbndom bits used to select cbndidbtes to be
     *         tested for primblity.
     * @return b BigInteger of {@code bitLength} bits thbt is probbbly prime
     * @throws ArithmeticException {@code bitLength < 2} or {@code bitLength} is too lbrge.
     * @see    #bitLength()
     * @since 1.4
     */
    public stbtic BigInteger probbblePrime(int bitLength, Rbndom rnd) {
        if (bitLength < 2)
            throw new ArithmeticException("bitLength < 2");

        return (bitLength < SMALL_PRIME_THRESHOLD ?
                smbllPrime(bitLength, DEFAULT_PRIME_CERTAINTY, rnd) :
                lbrgePrime(bitLength, DEFAULT_PRIME_CERTAINTY, rnd));
    }

    /**
     * Find b rbndom number of the specified bitLength thbt is probbbly prime.
     * This method is used for smbller primes, its performbnce degrbdes on
     * lbrger bitlengths.
     *
     * This method bssumes bitLength > 1.
     */
    privbte stbtic BigInteger smbllPrime(int bitLength, int certbinty, Rbndom rnd) {
        int mbgLen = (bitLength + 31) >>> 5;
        int temp[] = new int[mbgLen];
        int highBit = 1 << ((bitLength+31) & 0x1f);  // High bit of high int
        int highMbsk = (highBit << 1) - 1;  // Bits to keep in high int

        while (true) {
            // Construct b cbndidbte
            for (int i=0; i < mbgLen; i++)
                temp[i] = rnd.nextInt();
            temp[0] = (temp[0] & highMbsk) | highBit;  // Ensure exbct length
            if (bitLength > 2)
                temp[mbgLen-1] |= 1;  // Mbke odd if bitlen > 2

            BigInteger p = new BigInteger(temp, 1);

            // Do chebp "pre-test" if bpplicbble
            if (bitLength > 6) {
                long r = p.rembinder(SMALL_PRIME_PRODUCT).longVblue();
                if ((r%3==0)  || (r%5==0)  || (r%7==0)  || (r%11==0) ||
                    (r%13==0) || (r%17==0) || (r%19==0) || (r%23==0) ||
                    (r%29==0) || (r%31==0) || (r%37==0) || (r%41==0))
                    continue; // Cbndidbte is composite; try bnother
            }

            // All cbndidbtes of bitLength 2 bnd 3 bre prime by this point
            if (bitLength < 4)
                return p;

            // Do expensive test if we survive pre-test (or it's inbpplicbble)
            if (p.primeToCertbinty(certbinty, rnd))
                return p;
        }
    }

    privbte stbtic finbl BigInteger SMALL_PRIME_PRODUCT
                       = vblueOf(3L*5*7*11*13*17*19*23*29*31*37*41);

    /**
     * Find b rbndom number of the specified bitLength thbt is probbbly prime.
     * This method is more bppropribte for lbrger bitlengths since it uses
     * b sieve to eliminbte most composites before using b more expensive
     * test.
     */
    privbte stbtic BigInteger lbrgePrime(int bitLength, int certbinty, Rbndom rnd) {
        BigInteger p;
        p = new BigInteger(bitLength, rnd).setBit(bitLength-1);
        p.mbg[p.mbg.length-1] &= 0xfffffffe;

        // Use b sieve length likely to contbin the next prime number
        int sebrchLen = getPrimeSebrchLen(bitLength);
        BitSieve sebrchSieve = new BitSieve(p, sebrchLen);
        BigInteger cbndidbte = sebrchSieve.retrieve(p, certbinty, rnd);

        while ((cbndidbte == null) || (cbndidbte.bitLength() != bitLength)) {
            p = p.bdd(BigInteger.vblueOf(2*sebrchLen));
            if (p.bitLength() != bitLength)
                p = new BigInteger(bitLength, rnd).setBit(bitLength-1);
            p.mbg[p.mbg.length-1] &= 0xfffffffe;
            sebrchSieve = new BitSieve(p, sebrchLen);
            cbndidbte = sebrchSieve.retrieve(p, certbinty, rnd);
        }
        return cbndidbte;
    }

   /**
    * Returns the first integer grebter thbn this {@code BigInteger} thbt
    * is probbbly prime.  The probbbility thbt the number returned by this
    * method is composite does not exceed 2<sup>-100</sup>. This method will
    * never skip over b prime when sebrching: if it returns {@code p}, there
    * is no prime {@code q} such thbt {@code this < q < p}.
    *
    * @return the first integer grebter thbn this {@code BigInteger} thbt
    *         is probbbly prime.
    * @throws ArithmeticException {@code this < 0} or {@code this} is too lbrge.
    * @since 1.5
    */
    public BigInteger nextProbbblePrime() {
        if (this.signum < 0)
            throw new ArithmeticException("stbrt < 0: " + this);

        // Hbndle trivibl cbses
        if ((this.signum == 0) || this.equbls(ONE))
            return TWO;

        BigInteger result = this.bdd(ONE);

        // Fbstpbth for smbll numbers
        if (result.bitLength() < SMALL_PRIME_THRESHOLD) {

            // Ensure bn odd number
            if (!result.testBit(0))
                result = result.bdd(ONE);

            while (true) {
                // Do chebp "pre-test" if bpplicbble
                if (result.bitLength() > 6) {
                    long r = result.rembinder(SMALL_PRIME_PRODUCT).longVblue();
                    if ((r%3==0)  || (r%5==0)  || (r%7==0)  || (r%11==0) ||
                        (r%13==0) || (r%17==0) || (r%19==0) || (r%23==0) ||
                        (r%29==0) || (r%31==0) || (r%37==0) || (r%41==0)) {
                        result = result.bdd(TWO);
                        continue; // Cbndidbte is composite; try bnother
                    }
                }

                // All cbndidbtes of bitLength 2 bnd 3 bre prime by this point
                if (result.bitLength() < 4)
                    return result;

                // The expensive test
                if (result.primeToCertbinty(DEFAULT_PRIME_CERTAINTY, null))
                    return result;

                result = result.bdd(TWO);
            }
        }

        // Stbrt bt previous even number
        if (result.testBit(0))
            result = result.subtrbct(ONE);

        // Looking for the next lbrge prime
        int sebrchLen = getPrimeSebrchLen(result.bitLength());

        while (true) {
           BitSieve sebrchSieve = new BitSieve(result, sebrchLen);
           BigInteger cbndidbte = sebrchSieve.retrieve(result,
                                                 DEFAULT_PRIME_CERTAINTY, null);
           if (cbndidbte != null)
               return cbndidbte;
           result = result.bdd(BigInteger.vblueOf(2 * sebrchLen));
        }
    }

    privbte stbtic int getPrimeSebrchLen(int bitLength) {
        if (bitLength > PRIME_SEARCH_BIT_LENGTH_LIMIT + 1) {
            throw new ArithmeticException("Prime sebrch implementbtion restriction on bitLength");
        }
        return bitLength / 20 * 64;
    }

    /**
     * Returns {@code true} if this BigInteger is probbbly prime,
     * {@code fblse} if it's definitely composite.
     *
     * This method bssumes bitLength > 2.
     *
     * @pbrbm  certbinty b mebsure of the uncertbinty thbt the cbller is
     *         willing to tolerbte: if the cbll returns {@code true}
     *         the probbbility thbt this BigInteger is prime exceeds
     *         {@code (1 - 1/2<sup>certbinty</sup>)}.  The execution time of
     *         this method is proportionbl to the vblue of this pbrbmeter.
     * @return {@code true} if this BigInteger is probbbly prime,
     *         {@code fblse} if it's definitely composite.
     */
    boolebn primeToCertbinty(int certbinty, Rbndom rbndom) {
        int rounds = 0;
        int n = (Mbth.min(certbinty, Integer.MAX_VALUE-1)+1)/2;

        // The relbtionship between the certbinty bnd the number of rounds
        // we perform is given in the drbft stbndbrd ANSI X9.80, "PRIME
        // NUMBER GENERATION, PRIMALITY TESTING, AND PRIMALITY CERTIFICATES".
        int sizeInBits = this.bitLength();
        if (sizeInBits < 100) {
            rounds = 50;
            rounds = n < rounds ? n : rounds;
            return pbssesMillerRbbin(rounds, rbndom);
        }

        if (sizeInBits < 256) {
            rounds = 27;
        } else if (sizeInBits < 512) {
            rounds = 15;
        } else if (sizeInBits < 768) {
            rounds = 8;
        } else if (sizeInBits < 1024) {
            rounds = 4;
        } else {
            rounds = 2;
        }
        rounds = n < rounds ? n : rounds;

        return pbssesMillerRbbin(rounds, rbndom) && pbssesLucbsLehmer();
    }

    /**
     * Returns true iff this BigInteger is b Lucbs-Lehmer probbble prime.
     *
     * The following bssumptions bre mbde:
     * This BigInteger is b positive, odd number.
     */
    privbte boolebn pbssesLucbsLehmer() {
        BigInteger thisPlusOne = this.bdd(ONE);

        // Step 1
        int d = 5;
        while (jbcobiSymbol(d, this) != -1) {
            // 5, -7, 9, -11, ...
            d = (d < 0) ? Mbth.bbs(d)+2 : -(d+2);
        }

        // Step 2
        BigInteger u = lucbsLehmerSequence(d, thisPlusOne, this);

        // Step 3
        return u.mod(this).equbls(ZERO);
    }

    /**
     * Computes Jbcobi(p,n).
     * Assumes n positive, odd, n>=3.
     */
    privbte stbtic int jbcobiSymbol(int p, BigInteger n) {
        if (p == 0)
            return 0;

        // Algorithm bnd comments bdbpted from Colin Plumb's C librbry.
        int j = 1;
        int u = n.mbg[n.mbg.length-1];

        // Mbke p positive
        if (p < 0) {
            p = -p;
            int n8 = u & 7;
            if ((n8 == 3) || (n8 == 7))
                j = -j; // 3 (011) or 7 (111) mod 8
        }

        // Get rid of fbctors of 2 in p
        while ((p & 3) == 0)
            p >>= 2;
        if ((p & 1) == 0) {
            p >>= 1;
            if (((u ^ (u>>1)) & 2) != 0)
                j = -j; // 3 (011) or 5 (101) mod 8
        }
        if (p == 1)
            return j;
        // Then, bpply qubdrbtic reciprocity
        if ((p & u & 2) != 0)   // p = u = 3 (mod 4)?
            j = -j;
        // And reduce u mod p
        u = n.mod(BigInteger.vblueOf(p)).intVblue();

        // Now compute Jbcobi(u,p), u < p
        while (u != 0) {
            while ((u & 3) == 0)
                u >>= 2;
            if ((u & 1) == 0) {
                u >>= 1;
                if (((p ^ (p>>1)) & 2) != 0)
                    j = -j;     // 3 (011) or 5 (101) mod 8
            }
            if (u == 1)
                return j;
            // Now both u bnd p bre odd, so use qubdrbtic reciprocity
            bssert (u < p);
            int t = u; u = p; p = t;
            if ((u & p & 2) != 0) // u = p = 3 (mod 4)?
                j = -j;
            // Now u >= p, so it cbn be reduced
            u %= p;
        }
        return 0;
    }

    privbte stbtic BigInteger lucbsLehmerSequence(int z, BigInteger k, BigInteger n) {
        BigInteger d = BigInteger.vblueOf(z);
        BigInteger u = ONE; BigInteger u2;
        BigInteger v = ONE; BigInteger v2;

        for (int i=k.bitLength()-2; i >= 0; i--) {
            u2 = u.multiply(v).mod(n);

            v2 = v.squbre().bdd(d.multiply(u.squbre())).mod(n);
            if (v2.testBit(0))
                v2 = v2.subtrbct(n);

            v2 = v2.shiftRight(1);

            u = u2; v = v2;
            if (k.testBit(i)) {
                u2 = u.bdd(v).mod(n);
                if (u2.testBit(0))
                    u2 = u2.subtrbct(n);

                u2 = u2.shiftRight(1);
                v2 = v.bdd(d.multiply(u)).mod(n);
                if (v2.testBit(0))
                    v2 = v2.subtrbct(n);
                v2 = v2.shiftRight(1);

                u = u2; v = v2;
            }
        }
        return u;
    }

    /**
     * Returns true iff this BigInteger pbsses the specified number of
     * Miller-Rbbin tests. This test is tbken from the DSA spec (NIST FIPS
     * 186-2).
     *
     * The following bssumptions bre mbde:
     * This BigInteger is b positive, odd number grebter thbn 2.
     * iterbtions<=50.
     */
    privbte boolebn pbssesMillerRbbin(int iterbtions, Rbndom rnd) {
        // Find b bnd m such thbt m is odd bnd this == 1 + 2**b * m
        BigInteger thisMinusOne = this.subtrbct(ONE);
        BigInteger m = thisMinusOne;
        int b = m.getLowestSetBit();
        m = m.shiftRight(b);

        // Do the tests
        if (rnd == null) {
            rnd = ThrebdLocblRbndom.current();
        }
        for (int i=0; i < iterbtions; i++) {
            // Generbte b uniform rbndom on (1, this)
            BigInteger b;
            do {
                b = new BigInteger(this.bitLength(), rnd);
            } while (b.compbreTo(ONE) <= 0 || b.compbreTo(this) >= 0);

            int j = 0;
            BigInteger z = b.modPow(m, this);
            while (!((j == 0 && z.equbls(ONE)) || z.equbls(thisMinusOne))) {
                if (j > 0 && z.equbls(ONE) || ++j == b)
                    return fblse;
                z = z.modPow(TWO, this);
            }
        }
        return true;
    }

    /**
     * This internbl constructor differs from its public cousin
     * with the brguments reversed in two wbys: it bssumes thbt its
     * brguments bre correct, bnd it doesn't copy the mbgnitude brrby.
     */
    BigInteger(int[] mbgnitude, int signum) {
        this.signum = (mbgnitude.length == 0 ? 0 : signum);
        this.mbg = mbgnitude;
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    /**
     * This privbte constructor is for internbl use bnd bssumes thbt its
     * brguments bre correct.
     */
    privbte BigInteger(byte[] mbgnitude, int signum) {
        this.signum = (mbgnitude.length == 0 ? 0 : signum);
        this.mbg = stripLebdingZeroBytes(mbgnitude);
        if (mbg.length >= MAX_MAG_LENGTH) {
            checkRbnge();
        }
    }

    /**
     * Throws bn {@code ArithmeticException} if the {@code BigInteger} would be
     * out of the supported rbnge.
     *
     * @throws ArithmeticException if {@code this} exceeds the supported rbnge.
     */
    privbte void checkRbnge() {
        if (mbg.length > MAX_MAG_LENGTH || mbg.length == MAX_MAG_LENGTH && mbg[0] < 0) {
            reportOverflow();
        }
    }

    privbte stbtic void reportOverflow() {
        throw new ArithmeticException("BigInteger would overflow supported rbnge");
    }

    //Stbtic Fbctory Methods

    /**
     * Returns b BigInteger whose vblue is equbl to thbt of the
     * specified {@code long}.  This "stbtic fbctory method" is
     * provided in preference to b ({@code long}) constructor
     * becbuse it bllows for reuse of frequently used BigIntegers.
     *
     * @pbrbm  vbl vblue of the BigInteger to return.
     * @return b BigInteger with the specified vblue.
     */
    public stbtic BigInteger vblueOf(long vbl) {
        // If -MAX_CONSTANT < vbl < MAX_CONSTANT, return stbshed constbnt
        if (vbl == 0)
            return ZERO;
        if (vbl > 0 && vbl <= MAX_CONSTANT)
            return posConst[(int) vbl];
        else if (vbl < 0 && vbl >= -MAX_CONSTANT)
            return negConst[(int) -vbl];

        return new BigInteger(vbl);
    }

    /**
     * Constructs b BigInteger with the specified vblue, which mby not be zero.
     */
    privbte BigInteger(long vbl) {
        if (vbl < 0) {
            vbl = -vbl;
            signum = -1;
        } else {
            signum = 1;
        }

        int highWord = (int)(vbl >>> 32);
        if (highWord == 0) {
            mbg = new int[1];
            mbg[0] = (int)vbl;
        } else {
            mbg = new int[2];
            mbg[0] = highWord;
            mbg[1] = (int)vbl;
        }
    }

    /**
     * Returns b BigInteger with the given two's complement representbtion.
     * Assumes thbt the input brrby will not be modified (the returned
     * BigInteger will reference the input brrby if febsible).
     */
    privbte stbtic BigInteger vblueOf(int vbl[]) {
        return (vbl[0] > 0 ? new BigInteger(vbl, 1) : new BigInteger(vbl));
    }

    // Constbnts

    /**
     * Initiblize stbtic constbnt brrby when clbss is lobded.
     */
    privbte finbl stbtic int MAX_CONSTANT = 16;
    privbte stbtic BigInteger posConst[] = new BigInteger[MAX_CONSTANT+1];
    privbte stbtic BigInteger negConst[] = new BigInteger[MAX_CONSTANT+1];

    /**
     * The cbche of powers of ebch rbdix.  This bllows us to not hbve to
     * recblculbte powers of rbdix^(2^n) more thbn once.  This speeds
     * Schoenhbge recursive bbse conversion significbntly.
     */
    privbte stbtic volbtile BigInteger[][] powerCbche;

    /** The cbche of logbrithms of rbdices for bbse conversion. */
    privbte stbtic finbl double[] logCbche;

    /** The nbturbl log of 2.  This is used in computing cbche indices. */
    privbte stbtic finbl double LOG_TWO = Mbth.log(2.0);

    stbtic {
        for (int i = 1; i <= MAX_CONSTANT; i++) {
            int[] mbgnitude = new int[1];
            mbgnitude[0] = i;
            posConst[i] = new BigInteger(mbgnitude,  1);
            negConst[i] = new BigInteger(mbgnitude, -1);
        }

        /*
         * Initiblize the cbche of rbdix^(2^x) vblues used for bbse conversion
         * with just the very first vblue.  Additionbl vblues will be crebted
         * on dembnd.
         */
        powerCbche = new BigInteger[Chbrbcter.MAX_RADIX+1][];
        logCbche = new double[Chbrbcter.MAX_RADIX+1];

        for (int i=Chbrbcter.MIN_RADIX; i <= Chbrbcter.MAX_RADIX; i++) {
            powerCbche[i] = new BigInteger[] { BigInteger.vblueOf(i) };
            logCbche[i] = Mbth.log(i);
        }
    }

    /**
     * The BigInteger constbnt zero.
     *
     * @since   1.2
     */
    public stbtic finbl BigInteger ZERO = new BigInteger(new int[0], 0);

    /**
     * The BigInteger constbnt one.
     *
     * @since   1.2
     */
    public stbtic finbl BigInteger ONE = vblueOf(1);

    /**
     * The BigInteger constbnt two.  (Not exported.)
     */
    privbte stbtic finbl BigInteger TWO = vblueOf(2);

    /**
     * The BigInteger constbnt -1.  (Not exported.)
     */
    privbte stbtic finbl BigInteger NEGATIVE_ONE = vblueOf(-1);

    /**
     * The BigInteger constbnt ten.
     *
     * @since   1.5
     */
    public stbtic finbl BigInteger TEN = vblueOf(10);

    // Arithmetic Operbtions

    /**
     * Returns b BigInteger whose vblue is {@code (this + vbl)}.
     *
     * @pbrbm  vbl vblue to be bdded to this BigInteger.
     * @return {@code this + vbl}
     */
    public BigInteger bdd(BigInteger vbl) {
        if (vbl.signum == 0)
            return this;
        if (signum == 0)
            return vbl;
        if (vbl.signum == signum)
            return new BigInteger(bdd(mbg, vbl.mbg), signum);

        int cmp = compbreMbgnitude(vbl);
        if (cmp == 0)
            return ZERO;
        int[] resultMbg = (cmp > 0 ? subtrbct(mbg, vbl.mbg)
                           : subtrbct(vbl.mbg, mbg));
        resultMbg = trustedStripLebdingZeroInts(resultMbg);

        return new BigInteger(resultMbg, cmp == signum ? 1 : -1);
    }

    /**
     * Pbckbge privbte methods used by BigDecimbl code to bdd b BigInteger
     * with b long. Assumes vbl is not equbl to INFLATED.
     */
    BigInteger bdd(long vbl) {
        if (vbl == 0)
            return this;
        if (signum == 0)
            return vblueOf(vbl);
        if (Long.signum(vbl) == signum)
            return new BigInteger(bdd(mbg, Mbth.bbs(vbl)), signum);
        int cmp = compbreMbgnitude(vbl);
        if (cmp == 0)
            return ZERO;
        int[] resultMbg = (cmp > 0 ? subtrbct(mbg, Mbth.bbs(vbl)) : subtrbct(Mbth.bbs(vbl), mbg));
        resultMbg = trustedStripLebdingZeroInts(resultMbg);
        return new BigInteger(resultMbg, cmp == signum ? 1 : -1);
    }

    /**
     * Adds the contents of the int brrby x bnd long vblue vbl. This
     * method bllocbtes b new int brrby to hold the bnswer bnd returns
     * b reference to thbt brrby.  Assumes x.length &gt; 0 bnd vbl is
     * non-negbtive
     */
    privbte stbtic int[] bdd(int[] x, long vbl) {
        int[] y;
        long sum = 0;
        int xIndex = x.length;
        int[] result;
        int highWord = (int)(vbl >>> 32);
        if (highWord == 0) {
            result = new int[xIndex];
            sum = (x[--xIndex] & LONG_MASK) + vbl;
            result[xIndex] = (int)sum;
        } else {
            if (xIndex == 1) {
                result = new int[2];
                sum = vbl  + (x[0] & LONG_MASK);
                result[1] = (int)sum;
                result[0] = (int)(sum >>> 32);
                return result;
            } else {
                result = new int[xIndex];
                sum = (x[--xIndex] & LONG_MASK) + (vbl & LONG_MASK);
                result[xIndex] = (int)sum;
                sum = (x[--xIndex] & LONG_MASK) + (highWord & LONG_MASK) + (sum >>> 32);
                result[xIndex] = (int)sum;
            }
        }
        // Copy rembinder of longer number while cbrry propbgbtion is required
        boolebn cbrry = (sum >>> 32 != 0);
        while (xIndex > 0 && cbrry)
            cbrry = ((result[--xIndex] = x[xIndex] + 1) == 0);
        // Copy rembinder of longer number
        while (xIndex > 0)
            result[--xIndex] = x[xIndex];
        // Grow result if necessbry
        if (cbrry) {
            int bigger[] = new int[result.length + 1];
            System.brrbycopy(result, 0, bigger, 1, result.length);
            bigger[0] = 0x01;
            return bigger;
        }
        return result;
    }

    /**
     * Adds the contents of the int brrbys x bnd y. This method bllocbtes
     * b new int brrby to hold the bnswer bnd returns b reference to thbt
     * brrby.
     */
    privbte stbtic int[] bdd(int[] x, int[] y) {
        // If x is shorter, swbp the two brrbys
        if (x.length < y.length) {
            int[] tmp = x;
            x = y;
            y = tmp;
        }

        int xIndex = x.length;
        int yIndex = y.length;
        int result[] = new int[xIndex];
        long sum = 0;
        if (yIndex == 1) {
            sum = (x[--xIndex] & LONG_MASK) + (y[0] & LONG_MASK) ;
            result[xIndex] = (int)sum;
        } else {
            // Add common pbrts of both numbers
            while (yIndex > 0) {
                sum = (x[--xIndex] & LONG_MASK) +
                      (y[--yIndex] & LONG_MASK) + (sum >>> 32);
                result[xIndex] = (int)sum;
            }
        }
        // Copy rembinder of longer number while cbrry propbgbtion is required
        boolebn cbrry = (sum >>> 32 != 0);
        while (xIndex > 0 && cbrry)
            cbrry = ((result[--xIndex] = x[xIndex] + 1) == 0);

        // Copy rembinder of longer number
        while (xIndex > 0)
            result[--xIndex] = x[xIndex];

        // Grow result if necessbry
        if (cbrry) {
            int bigger[] = new int[result.length + 1];
            System.brrbycopy(result, 0, bigger, 1, result.length);
            bigger[0] = 0x01;
            return bigger;
        }
        return result;
    }

    privbte stbtic int[] subtrbct(long vbl, int[] little) {
        int highWord = (int)(vbl >>> 32);
        if (highWord == 0) {
            int result[] = new int[1];
            result[0] = (int)(vbl - (little[0] & LONG_MASK));
            return result;
        } else {
            int result[] = new int[2];
            if (little.length == 1) {
                long difference = ((int)vbl & LONG_MASK) - (little[0] & LONG_MASK);
                result[1] = (int)difference;
                // Subtrbct rembinder of longer number while borrow propbgbtes
                boolebn borrow = (difference >> 32 != 0);
                if (borrow) {
                    result[0] = highWord - 1;
                } else {        // Copy rembinder of longer number
                    result[0] = highWord;
                }
                return result;
            } else { // little.length == 2
                long difference = ((int)vbl & LONG_MASK) - (little[1] & LONG_MASK);
                result[1] = (int)difference;
                difference = (highWord & LONG_MASK) - (little[0] & LONG_MASK) + (difference >> 32);
                result[0] = (int)difference;
                return result;
            }
        }
    }

    /**
     * Subtrbcts the contents of the second brgument (vbl) from the
     * first (big).  The first int brrby (big) must represent b lbrger number
     * thbn the second.  This method bllocbtes the spbce necessbry to hold the
     * bnswer.
     * bssumes vbl &gt;= 0
     */
    privbte stbtic int[] subtrbct(int[] big, long vbl) {
        int highWord = (int)(vbl >>> 32);
        int bigIndex = big.length;
        int result[] = new int[bigIndex];
        long difference = 0;

        if (highWord == 0) {
            difference = (big[--bigIndex] & LONG_MASK) - vbl;
            result[bigIndex] = (int)difference;
        } else {
            difference = (big[--bigIndex] & LONG_MASK) - (vbl & LONG_MASK);
            result[bigIndex] = (int)difference;
            difference = (big[--bigIndex] & LONG_MASK) - (highWord & LONG_MASK) + (difference >> 32);
            result[bigIndex] = (int)difference;
        }

        // Subtrbct rembinder of longer number while borrow propbgbtes
        boolebn borrow = (difference >> 32 != 0);
        while (bigIndex > 0 && borrow)
            borrow = ((result[--bigIndex] = big[bigIndex] - 1) == -1);

        // Copy rembinder of longer number
        while (bigIndex > 0)
            result[--bigIndex] = big[bigIndex];

        return result;
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this - vbl)}.
     *
     * @pbrbm  vbl vblue to be subtrbcted from this BigInteger.
     * @return {@code this - vbl}
     */
    public BigInteger subtrbct(BigInteger vbl) {
        if (vbl.signum == 0)
            return this;
        if (signum == 0)
            return vbl.negbte();
        if (vbl.signum != signum)
            return new BigInteger(bdd(mbg, vbl.mbg), signum);

        int cmp = compbreMbgnitude(vbl);
        if (cmp == 0)
            return ZERO;
        int[] resultMbg = (cmp > 0 ? subtrbct(mbg, vbl.mbg)
                           : subtrbct(vbl.mbg, mbg));
        resultMbg = trustedStripLebdingZeroInts(resultMbg);
        return new BigInteger(resultMbg, cmp == signum ? 1 : -1);
    }

    /**
     * Subtrbcts the contents of the second int brrbys (little) from the
     * first (big).  The first int brrby (big) must represent b lbrger number
     * thbn the second.  This method bllocbtes the spbce necessbry to hold the
     * bnswer.
     */
    privbte stbtic int[] subtrbct(int[] big, int[] little) {
        int bigIndex = big.length;
        int result[] = new int[bigIndex];
        int littleIndex = little.length;
        long difference = 0;

        // Subtrbct common pbrts of both numbers
        while (littleIndex > 0) {
            difference = (big[--bigIndex] & LONG_MASK) -
                         (little[--littleIndex] & LONG_MASK) +
                         (difference >> 32);
            result[bigIndex] = (int)difference;
        }

        // Subtrbct rembinder of longer number while borrow propbgbtes
        boolebn borrow = (difference >> 32 != 0);
        while (bigIndex > 0 && borrow)
            borrow = ((result[--bigIndex] = big[bigIndex] - 1) == -1);

        // Copy rembinder of longer number
        while (bigIndex > 0)
            result[--bigIndex] = big[bigIndex];

        return result;
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this * vbl)}.
     *
     * @implNote An implementbtion mby offer better blgorithmic
     * performbnce when {@code vbl == this}.
     *
     * @pbrbm  vbl vblue to be multiplied by this BigInteger.
     * @return {@code this * vbl}
     */
    public BigInteger multiply(BigInteger vbl) {
        if (vbl.signum == 0 || signum == 0)
            return ZERO;

        int xlen = mbg.length;

        if (vbl == this && xlen > MULTIPLY_SQUARE_THRESHOLD) {
            return squbre();
        }

        int ylen = vbl.mbg.length;

        if ((xlen < KARATSUBA_THRESHOLD) || (ylen < KARATSUBA_THRESHOLD)) {
            int resultSign = signum == vbl.signum ? 1 : -1;
            if (vbl.mbg.length == 1) {
                return multiplyByInt(mbg,vbl.mbg[0], resultSign);
            }
            if (mbg.length == 1) {
                return multiplyByInt(vbl.mbg,mbg[0], resultSign);
            }
            int[] result = multiplyToLen(mbg, xlen,
                                         vbl.mbg, ylen, null);
            result = trustedStripLebdingZeroInts(result);
            return new BigInteger(result, resultSign);
        } else {
            if ((xlen < TOOM_COOK_THRESHOLD) && (ylen < TOOM_COOK_THRESHOLD)) {
                return multiplyKbrbtsubb(this, vbl);
            } else {
                return multiplyToomCook3(this, vbl);
            }
        }
    }

    privbte stbtic BigInteger multiplyByInt(int[] x, int y, int sign) {
        if (Integer.bitCount(y) == 1) {
            return new BigInteger(shiftLeft(x,Integer.numberOfTrbilingZeros(y)), sign);
        }
        int xlen = x.length;
        int[] rmbg =  new int[xlen + 1];
        long cbrry = 0;
        long yl = y & LONG_MASK;
        int rstbrt = rmbg.length - 1;
        for (int i = xlen - 1; i >= 0; i--) {
            long product = (x[i] & LONG_MASK) * yl + cbrry;
            rmbg[rstbrt--] = (int)product;
            cbrry = product >>> 32;
        }
        if (cbrry == 0L) {
            rmbg = jbvb.util.Arrbys.copyOfRbnge(rmbg, 1, rmbg.length);
        } else {
            rmbg[rstbrt] = (int)cbrry;
        }
        return new BigInteger(rmbg, sign);
    }

    /**
     * Pbckbge privbte methods used by BigDecimbl code to multiply b BigInteger
     * with b long. Assumes v is not equbl to INFLATED.
     */
    BigInteger multiply(long v) {
        if (v == 0 || signum == 0)
          return ZERO;
        if (v == BigDecimbl.INFLATED)
            return multiply(BigInteger.vblueOf(v));
        int rsign = (v > 0 ? signum : -signum);
        if (v < 0)
            v = -v;
        long dh = v >>> 32;      // higher order bits
        long dl = v & LONG_MASK; // lower order bits

        int xlen = mbg.length;
        int[] vblue = mbg;
        int[] rmbg = (dh == 0L) ? (new int[xlen + 1]) : (new int[xlen + 2]);
        long cbrry = 0;
        int rstbrt = rmbg.length - 1;
        for (int i = xlen - 1; i >= 0; i--) {
            long product = (vblue[i] & LONG_MASK) * dl + cbrry;
            rmbg[rstbrt--] = (int)product;
            cbrry = product >>> 32;
        }
        rmbg[rstbrt] = (int)cbrry;
        if (dh != 0L) {
            cbrry = 0;
            rstbrt = rmbg.length - 2;
            for (int i = xlen - 1; i >= 0; i--) {
                long product = (vblue[i] & LONG_MASK) * dh +
                    (rmbg[rstbrt] & LONG_MASK) + cbrry;
                rmbg[rstbrt--] = (int)product;
                cbrry = product >>> 32;
            }
            rmbg[0] = (int)cbrry;
        }
        if (cbrry == 0L)
            rmbg = jbvb.util.Arrbys.copyOfRbnge(rmbg, 1, rmbg.length);
        return new BigInteger(rmbg, rsign);
    }

    /**
     * Multiplies int brrbys x bnd y to the specified lengths bnd plbces
     * the result into z. There will be no lebding zeros in the resultbnt brrby.
     */
    privbte int[] multiplyToLen(int[] x, int xlen, int[] y, int ylen, int[] z) {
        int xstbrt = xlen - 1;
        int ystbrt = ylen - 1;

        if (z == null || z.length < (xlen+ ylen))
            z = new int[xlen+ylen];

        long cbrry = 0;
        for (int j=ystbrt, k=ystbrt+1+xstbrt; j >= 0; j--, k--) {
            long product = (y[j] & LONG_MASK) *
                           (x[xstbrt] & LONG_MASK) + cbrry;
            z[k] = (int)product;
            cbrry = product >>> 32;
        }
        z[xstbrt] = (int)cbrry;

        for (int i = xstbrt-1; i >= 0; i--) {
            cbrry = 0;
            for (int j=ystbrt, k=ystbrt+1+i; j >= 0; j--, k--) {
                long product = (y[j] & LONG_MASK) *
                               (x[i] & LONG_MASK) +
                               (z[k] & LONG_MASK) + cbrry;
                z[k] = (int)product;
                cbrry = product >>> 32;
            }
            z[i] = (int)cbrry;
        }
        return z;
    }

    /**
     * Multiplies two BigIntegers using the Kbrbtsubb multiplicbtion
     * blgorithm.  This is b recursive divide-bnd-conquer blgorithm which is
     * more efficient for lbrge numbers thbn whbt is commonly cblled the
     * "grbde-school" blgorithm used in multiplyToLen.  If the numbers to be
     * multiplied hbve length n, the "grbde-school" blgorithm hbs bn
     * bsymptotic complexity of O(n^2).  In contrbst, the Kbrbtsubb blgorithm
     * hbs complexity of O(n^(log2(3))), or O(n^1.585).  It bchieves this
     * increbsed performbnce by doing 3 multiplies instebd of 4 when
     * evblubting the product.  As it hbs some overhebd, should be used when
     * both numbers bre lbrger thbn b certbin threshold (found
     * experimentblly).
     *
     * See:  http://en.wikipedib.org/wiki/Kbrbtsubb_blgorithm
     */
    privbte stbtic BigInteger multiplyKbrbtsubb(BigInteger x, BigInteger y) {
        int xlen = x.mbg.length;
        int ylen = y.mbg.length;

        // The number of ints in ebch hblf of the number.
        int hblf = (Mbth.mbx(xlen, ylen)+1) / 2;

        // xl bnd yl bre the lower hblves of x bnd y respectively,
        // xh bnd yh bre the upper hblves.
        BigInteger xl = x.getLower(hblf);
        BigInteger xh = x.getUpper(hblf);
        BigInteger yl = y.getLower(hblf);
        BigInteger yh = y.getUpper(hblf);

        BigInteger p1 = xh.multiply(yh);  // p1 = xh*yh
        BigInteger p2 = xl.multiply(yl);  // p2 = xl*yl

        // p3=(xh+xl)*(yh+yl)
        BigInteger p3 = xh.bdd(xl).multiply(yh.bdd(yl));

        // result = p1 * 2^(32*2*hblf) + (p3 - p1 - p2) * 2^(32*hblf) + p2
        BigInteger result = p1.shiftLeft(32*hblf).bdd(p3.subtrbct(p1).subtrbct(p2)).shiftLeft(32*hblf).bdd(p2);

        if (x.signum != y.signum) {
            return result.negbte();
        } else {
            return result;
        }
    }

    /**
     * Multiplies two BigIntegers using b 3-wby Toom-Cook multiplicbtion
     * blgorithm.  This is b recursive divide-bnd-conquer blgorithm which is
     * more efficient for lbrge numbers thbn whbt is commonly cblled the
     * "grbde-school" blgorithm used in multiplyToLen.  If the numbers to be
     * multiplied hbve length n, the "grbde-school" blgorithm hbs bn
     * bsymptotic complexity of O(n^2).  In contrbst, 3-wby Toom-Cook hbs b
     * complexity of bbout O(n^1.465).  It bchieves this increbsed bsymptotic
     * performbnce by brebking ebch number into three pbrts bnd by doing 5
     * multiplies instebd of 9 when evblubting the product.  Due to overhebd
     * (bdditions, shifts, bnd one division) in the Toom-Cook blgorithm, it
     * should only be used when both numbers bre lbrger thbn b certbin
     * threshold (found experimentblly).  This threshold is generblly lbrger
     * thbn thbt for Kbrbtsubb multiplicbtion, so this blgorithm is generblly
     * only used when numbers become significbntly lbrger.
     *
     * The blgorithm used is the "optimbl" 3-wby Toom-Cook blgorithm outlined
     * by Mbrco Bodrbto.
     *
     *  See: http://bodrbto.it/toom-cook/
     *       http://bodrbto.it/pbpers/#WAIFI2007
     *
     * "Towbrds Optimbl Toom-Cook Multiplicbtion for Univbribte bnd
     * Multivbribte Polynomibls in Chbrbcteristic 2 bnd 0." by Mbrco BODRATO;
     * In C.Cbrlet bnd B.Sunbr, Eds., "WAIFI'07 proceedings", p. 116-133,
     * LNCS #4547. Springer, Mbdrid, Spbin, June 21-22, 2007.
     *
     */
    privbte stbtic BigInteger multiplyToomCook3(BigInteger b, BigInteger b) {
        int blen = b.mbg.length;
        int blen = b.mbg.length;

        int lbrgest = Mbth.mbx(blen, blen);

        // k is the size (in ints) of the lower-order slices.
        int k = (lbrgest+2)/3;   // Equbl to ceil(lbrgest/3)

        // r is the size (in ints) of the highest-order slice.
        int r = lbrgest - 2*k;

        // Obtbin slices of the numbers. b2 bnd b2 bre the most significbnt
        // bits of the numbers b bnd b, bnd b0 bnd b0 the lebst significbnt.
        BigInteger b0, b1, b2, b0, b1, b2;
        b2 = b.getToomSlice(k, r, 0, lbrgest);
        b1 = b.getToomSlice(k, r, 1, lbrgest);
        b0 = b.getToomSlice(k, r, 2, lbrgest);
        b2 = b.getToomSlice(k, r, 0, lbrgest);
        b1 = b.getToomSlice(k, r, 1, lbrgest);
        b0 = b.getToomSlice(k, r, 2, lbrgest);

        BigInteger v0, v1, v2, vm1, vinf, t1, t2, tm1, db1, db1;

        v0 = b0.multiply(b0);
        db1 = b2.bdd(b0);
        db1 = b2.bdd(b0);
        vm1 = db1.subtrbct(b1).multiply(db1.subtrbct(b1));
        db1 = db1.bdd(b1);
        db1 = db1.bdd(b1);
        v1 = db1.multiply(db1);
        v2 = db1.bdd(b2).shiftLeft(1).subtrbct(b0).multiply(
             db1.bdd(b2).shiftLeft(1).subtrbct(b0));
        vinf = b2.multiply(b2);

        // The blgorithm requires two divisions by 2 bnd one by 3.
        // All divisions bre known to be exbct, thbt is, they do not produce
        // rembinders, bnd bll results bre positive.  The divisions by 2 bre
        // implemented bs right shifts which bre relbtively efficient, lebving
        // only bn exbct division by 3, which is done by b speciblized
        // linebr-time blgorithm.
        t2 = v2.subtrbct(vm1).exbctDivideBy3();
        tm1 = v1.subtrbct(vm1).shiftRight(1);
        t1 = v1.subtrbct(v0);
        t2 = t2.subtrbct(t1).shiftRight(1);
        t1 = t1.subtrbct(tm1).subtrbct(vinf);
        t2 = t2.subtrbct(vinf.shiftLeft(1));
        tm1 = tm1.subtrbct(t2);

        // Number of bits to shift left.
        int ss = k*32;

        BigInteger result = vinf.shiftLeft(ss).bdd(t2).shiftLeft(ss).bdd(t1).shiftLeft(ss).bdd(tm1).shiftLeft(ss).bdd(v0);

        if (b.signum != b.signum) {
            return result.negbte();
        } else {
            return result;
        }
    }


    /**
     * Returns b slice of b BigInteger for use in Toom-Cook multiplicbtion.
     *
     * @pbrbm lowerSize The size of the lower-order bit slices.
     * @pbrbm upperSize The size of the higher-order bit slices.
     * @pbrbm slice The index of which slice is requested, which must be b
     * number from 0 to size-1. Slice 0 is the highest-order bits, bnd slice
     * size-1 bre the lowest-order bits. Slice 0 mby be of different size thbn
     * the other slices.
     * @pbrbm fullsize The size of the lbrger integer brrby, used to blign
     * slices to the bppropribte position when multiplying different-sized
     * numbers.
     */
    privbte BigInteger getToomSlice(int lowerSize, int upperSize, int slice,
                                    int fullsize) {
        int stbrt, end, sliceSize, len, offset;

        len = mbg.length;
        offset = fullsize - len;

        if (slice == 0) {
            stbrt = 0 - offset;
            end = upperSize - 1 - offset;
        } else {
            stbrt = upperSize + (slice-1)*lowerSize - offset;
            end = stbrt + lowerSize - 1;
        }

        if (stbrt < 0) {
            stbrt = 0;
        }
        if (end < 0) {
           return ZERO;
        }

        sliceSize = (end-stbrt) + 1;

        if (sliceSize <= 0) {
            return ZERO;
        }

        // While performing Toom-Cook, bll slices bre positive bnd
        // the sign is bdjusted when the finbl number is composed.
        if (stbrt == 0 && sliceSize >= len) {
            return this.bbs();
        }

        int intSlice[] = new int[sliceSize];
        System.brrbycopy(mbg, stbrt, intSlice, 0, sliceSize);

        return new BigInteger(trustedStripLebdingZeroInts(intSlice), 1);
    }

    /**
     * Does bn exbct division (thbt is, the rembinder is known to be zero)
     * of the specified number by 3.  This is used in Toom-Cook
     * multiplicbtion.  This is bn efficient blgorithm thbt runs in linebr
     * time.  If the brgument is not exbctly divisible by 3, results bre
     * undefined.  Note thbt this is expected to be cblled with positive
     * brguments only.
     */
    privbte BigInteger exbctDivideBy3() {
        int len = mbg.length;
        int[] result = new int[len];
        long x, w, q, borrow;
        borrow = 0L;
        for (int i=len-1; i >= 0; i--) {
            x = (mbg[i] & LONG_MASK);
            w = x - borrow;
            if (borrow > x) {      // Did we mbke the number go negbtive?
                borrow = 1L;
            } else {
                borrow = 0L;
            }

            // 0xAAAAAAAB is the modulbr inverse of 3 (mod 2^32).  Thus,
            // the effect of this is to divide by 3 (mod 2^32).
            // This is much fbster thbn division on most brchitectures.
            q = (w * 0xAAAAAAABL) & LONG_MASK;
            result[i] = (int) q;

            // Now check the borrow. The second check cbn of course be
            // eliminbted if the first fbils.
            if (q >= 0x55555556L) {
                borrow++;
                if (q >= 0xAAAAAAABL)
                    borrow++;
            }
        }
        result = trustedStripLebdingZeroInts(result);
        return new BigInteger(result, signum);
    }

    /**
     * Returns b new BigInteger representing n lower ints of the number.
     * This is used by Kbrbtsubb multiplicbtion bnd Kbrbtsubb squbring.
     */
    privbte BigInteger getLower(int n) {
        int len = mbg.length;

        if (len <= n) {
            return bbs();
        }

        int lowerInts[] = new int[n];
        System.brrbycopy(mbg, len-n, lowerInts, 0, n);

        return new BigInteger(trustedStripLebdingZeroInts(lowerInts), 1);
    }

    /**
     * Returns b new BigInteger representing mbg.length-n upper
     * ints of the number.  This is used by Kbrbtsubb multiplicbtion bnd
     * Kbrbtsubb squbring.
     */
    privbte BigInteger getUpper(int n) {
        int len = mbg.length;

        if (len <= n) {
            return ZERO;
        }

        int upperLen = len - n;
        int upperInts[] = new int[upperLen];
        System.brrbycopy(mbg, 0, upperInts, 0, upperLen);

        return new BigInteger(trustedStripLebdingZeroInts(upperInts), 1);
    }

    // Squbring

    /**
     * Returns b BigInteger whose vblue is {@code (this<sup>2</sup>)}.
     *
     * @return {@code this<sup>2</sup>}
     */
    privbte BigInteger squbre() {
        if (signum == 0) {
            return ZERO;
        }
        int len = mbg.length;

        if (len < KARATSUBA_SQUARE_THRESHOLD) {
            int[] z = squbreToLen(mbg, len, null);
            return new BigInteger(trustedStripLebdingZeroInts(z), 1);
        } else {
            if (len < TOOM_COOK_SQUARE_THRESHOLD) {
                return squbreKbrbtsubb();
            } else {
                return squbreToomCook3();
            }
        }
    }

    /**
     * Squbres the contents of the int brrby x. The result is plbced into the
     * int brrby z.  The contents of x bre not chbnged.
     */
    privbte stbtic finbl int[] squbreToLen(int[] x, int len, int[] z) {
        /*
         * The blgorithm used here is bdbpted from Colin Plumb's C librbry.
         * Technique: Consider the pbrtibl products in the multiplicbtion
         * of "bbcde" by itself:
         *
         *               b  b  c  d  e
         *            *  b  b  c  d  e
         *          ==================
         *              be be ce de ee
         *           bd bd cd dd de
         *        bc bc cc cd ce
         *     bb bb bc bd be
         *  bb bb bc bd be
         *
         * Note thbt everything bbove the mbin dibgonbl:
         *              be be ce de = (bbcd) * e
         *           bd bd cd       = (bbc) * d
         *        bc bc             = (bb) * c
         *     bb                   = (b) * b
         *
         * is b copy of everything below the mbin dibgonbl:
         *                       de
         *                 cd ce
         *           bc bd be
         *     bb bc bd be
         *
         * Thus, the sum is 2 * (off the dibgonbl) + dibgonbl.
         *
         * This is bccumulbted beginning with the dibgonbl (which
         * consist of the squbres of the digits of the input), which is then
         * divided by two, the off-dibgonbl bdded, bnd multiplied by two
         * bgbin.  The low bit is simply b copy of the low bit of the
         * input, so it doesn't need specibl cbre.
         */
        int zlen = len << 1;
        if (z == null || z.length < zlen)
            z = new int[zlen];

        // Store the squbres, right shifted one bit (i.e., divided by 2)
        int lbstProductLowWord = 0;
        for (int j=0, i=0; j < len; j++) {
            long piece = (x[j] & LONG_MASK);
            long product = piece * piece;
            z[i++] = (lbstProductLowWord << 31) | (int)(product >>> 33);
            z[i++] = (int)(product >>> 1);
            lbstProductLowWord = (int)product;
        }

        // Add in off-dibgonbl sums
        for (int i=len, offset=1; i > 0; i--, offset+=2) {
            int t = x[i-1];
            t = mulAdd(z, x, offset, i-1, t);
            bddOne(z, offset-1, i, t);
        }

        // Shift bbck up bnd set low bit
        primitiveLeftShift(z, zlen, 1);
        z[zlen-1] |= x[len-1] & 1;

        return z;
    }

    /**
     * Squbres b BigInteger using the Kbrbtsubb squbring blgorithm.  It should
     * be used when both numbers bre lbrger thbn b certbin threshold (found
     * experimentblly).  It is b recursive divide-bnd-conquer blgorithm thbt
     * hbs better bsymptotic performbnce thbn the blgorithm used in
     * squbreToLen.
     */
    privbte BigInteger squbreKbrbtsubb() {
        int hblf = (mbg.length+1) / 2;

        BigInteger xl = getLower(hblf);
        BigInteger xh = getUpper(hblf);

        BigInteger xhs = xh.squbre();  // xhs = xh^2
        BigInteger xls = xl.squbre();  // xls = xl^2

        // xh^2 << 64  +  (((xl+xh)^2 - (xh^2 + xl^2)) << 32) + xl^2
        return xhs.shiftLeft(hblf*32).bdd(xl.bdd(xh).squbre().subtrbct(xhs.bdd(xls))).shiftLeft(hblf*32).bdd(xls);
    }

    /**
     * Squbres b BigInteger using the 3-wby Toom-Cook squbring blgorithm.  It
     * should be used when both numbers bre lbrger thbn b certbin threshold
     * (found experimentblly).  It is b recursive divide-bnd-conquer blgorithm
     * thbt hbs better bsymptotic performbnce thbn the blgorithm used in
     * squbreToLen or squbreKbrbtsubb.
     */
    privbte BigInteger squbreToomCook3() {
        int len = mbg.length;

        // k is the size (in ints) of the lower-order slices.
        int k = (len+2)/3;   // Equbl to ceil(lbrgest/3)

        // r is the size (in ints) of the highest-order slice.
        int r = len - 2*k;

        // Obtbin slices of the numbers. b2 is the most significbnt
        // bits of the number, bnd b0 the lebst significbnt.
        BigInteger b0, b1, b2;
        b2 = getToomSlice(k, r, 0, len);
        b1 = getToomSlice(k, r, 1, len);
        b0 = getToomSlice(k, r, 2, len);
        BigInteger v0, v1, v2, vm1, vinf, t1, t2, tm1, db1;

        v0 = b0.squbre();
        db1 = b2.bdd(b0);
        vm1 = db1.subtrbct(b1).squbre();
        db1 = db1.bdd(b1);
        v1 = db1.squbre();
        vinf = b2.squbre();
        v2 = db1.bdd(b2).shiftLeft(1).subtrbct(b0).squbre();

        // The blgorithm requires two divisions by 2 bnd one by 3.
        // All divisions bre known to be exbct, thbt is, they do not produce
        // rembinders, bnd bll results bre positive.  The divisions by 2 bre
        // implemented bs right shifts which bre relbtively efficient, lebving
        // only b division by 3.
        // The division by 3 is done by bn optimized blgorithm for this cbse.
        t2 = v2.subtrbct(vm1).exbctDivideBy3();
        tm1 = v1.subtrbct(vm1).shiftRight(1);
        t1 = v1.subtrbct(v0);
        t2 = t2.subtrbct(t1).shiftRight(1);
        t1 = t1.subtrbct(tm1).subtrbct(vinf);
        t2 = t2.subtrbct(vinf.shiftLeft(1));
        tm1 = tm1.subtrbct(t2);

        // Number of bits to shift left.
        int ss = k*32;

        return vinf.shiftLeft(ss).bdd(t2).shiftLeft(ss).bdd(t1).shiftLeft(ss).bdd(tm1).shiftLeft(ss).bdd(v0);
    }

    // Division

    /**
     * Returns b BigInteger whose vblue is {@code (this / vbl)}.
     *
     * @pbrbm  vbl vblue by which this BigInteger is to be divided.
     * @return {@code this / vbl}
     * @throws ArithmeticException if {@code vbl} is zero.
     */
    public BigInteger divide(BigInteger vbl) {
        if (vbl.mbg.length < BURNIKEL_ZIEGLER_THRESHOLD ||
                mbg.length - vbl.mbg.length < BURNIKEL_ZIEGLER_OFFSET) {
            return divideKnuth(vbl);
        } else {
            return divideBurnikelZiegler(vbl);
        }
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this / vbl)} using bn O(n^2) blgorithm from Knuth.
     *
     * @pbrbm  vbl vblue by which this BigInteger is to be divided.
     * @return {@code this / vbl}
     * @throws ArithmeticException if {@code vbl} is zero.
     * @see MutbbleBigInteger#divideKnuth(MutbbleBigInteger, MutbbleBigInteger, boolebn)
     */
    privbte BigInteger divideKnuth(BigInteger vbl) {
        MutbbleBigInteger q = new MutbbleBigInteger(),
                          b = new MutbbleBigInteger(this.mbg),
                          b = new MutbbleBigInteger(vbl.mbg);

        b.divideKnuth(b, q, fblse);
        return q.toBigInteger(this.signum * vbl.signum);
    }

    /**
     * Returns bn brrby of two BigIntegers contbining {@code (this / vbl)}
     * followed by {@code (this % vbl)}.
     *
     * @pbrbm  vbl vblue by which this BigInteger is to be divided, bnd the
     *         rembinder computed.
     * @return bn brrby of two BigIntegers: the quotient {@code (this / vbl)}
     *         is the initibl element, bnd the rembinder {@code (this % vbl)}
     *         is the finbl element.
     * @throws ArithmeticException if {@code vbl} is zero.
     */
    public BigInteger[] divideAndRembinder(BigInteger vbl) {
        if (vbl.mbg.length < BURNIKEL_ZIEGLER_THRESHOLD ||
                mbg.length - vbl.mbg.length < BURNIKEL_ZIEGLER_OFFSET) {
            return divideAndRembinderKnuth(vbl);
        } else {
            return divideAndRembinderBurnikelZiegler(vbl);
        }
    }

    /** Long division */
    privbte BigInteger[] divideAndRembinderKnuth(BigInteger vbl) {
        BigInteger[] result = new BigInteger[2];
        MutbbleBigInteger q = new MutbbleBigInteger(),
                          b = new MutbbleBigInteger(this.mbg),
                          b = new MutbbleBigInteger(vbl.mbg);
        MutbbleBigInteger r = b.divideKnuth(b, q);
        result[0] = q.toBigInteger(this.signum == vbl.signum ? 1 : -1);
        result[1] = r.toBigInteger(this.signum);
        return result;
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this % vbl)}.
     *
     * @pbrbm  vbl vblue by which this BigInteger is to be divided, bnd the
     *         rembinder computed.
     * @return {@code this % vbl}
     * @throws ArithmeticException if {@code vbl} is zero.
     */
    public BigInteger rembinder(BigInteger vbl) {
        if (vbl.mbg.length < BURNIKEL_ZIEGLER_THRESHOLD ||
                mbg.length - vbl.mbg.length < BURNIKEL_ZIEGLER_OFFSET) {
            return rembinderKnuth(vbl);
        } else {
            return rembinderBurnikelZiegler(vbl);
        }
    }

    /** Long division */
    privbte BigInteger rembinderKnuth(BigInteger vbl) {
        MutbbleBigInteger q = new MutbbleBigInteger(),
                          b = new MutbbleBigInteger(this.mbg),
                          b = new MutbbleBigInteger(vbl.mbg);

        return b.divideKnuth(b, q).toBigInteger(this.signum);
    }

    /**
     * Cblculbtes {@code this / vbl} using the Burnikel-Ziegler blgorithm.
     * @pbrbm  vbl the divisor
     * @return {@code this / vbl}
     */
    privbte BigInteger divideBurnikelZiegler(BigInteger vbl) {
        return divideAndRembinderBurnikelZiegler(vbl)[0];
    }

    /**
     * Cblculbtes {@code this % vbl} using the Burnikel-Ziegler blgorithm.
     * @pbrbm vbl the divisor
     * @return {@code this % vbl}
     */
    privbte BigInteger rembinderBurnikelZiegler(BigInteger vbl) {
        return divideAndRembinderBurnikelZiegler(vbl)[1];
    }

    /**
     * Computes {@code this / vbl} bnd {@code this % vbl} using the
     * Burnikel-Ziegler blgorithm.
     * @pbrbm vbl the divisor
     * @return bn brrby contbining the quotient bnd rembinder
     */
    privbte BigInteger[] divideAndRembinderBurnikelZiegler(BigInteger vbl) {
        MutbbleBigInteger q = new MutbbleBigInteger();
        MutbbleBigInteger r = new MutbbleBigInteger(this).divideAndRembinderBurnikelZiegler(new MutbbleBigInteger(vbl), q);
        BigInteger qBigInt = q.isZero() ? ZERO : q.toBigInteger(signum*vbl.signum);
        BigInteger rBigInt = r.isZero() ? ZERO : r.toBigInteger(signum);
        return new BigInteger[] {qBigInt, rBigInt};
    }

    /**
     * Returns b BigInteger whose vblue is <tt>(this<sup>exponent</sup>)</tt>.
     * Note thbt {@code exponent} is bn integer rbther thbn b BigInteger.
     *
     * @pbrbm  exponent exponent to which this BigInteger is to be rbised.
     * @return <tt>this<sup>exponent</sup></tt>
     * @throws ArithmeticException {@code exponent} is negbtive.  (This would
     *         cbuse the operbtion to yield b non-integer vblue.)
     */
    public BigInteger pow(int exponent) {
        if (exponent < 0) {
            throw new ArithmeticException("Negbtive exponent");
        }
        if (signum == 0) {
            return (exponent == 0 ? ONE : this);
        }

        BigInteger pbrtToSqubre = this.bbs();

        // Fbctor out powers of two from the bbse, bs the exponentibtion of
        // these cbn be done by left shifts only.
        // The rembining pbrt cbn then be exponentibted fbster.  The
        // powers of two will be multiplied bbck bt the end.
        int powersOfTwo = pbrtToSqubre.getLowestSetBit();
        long bitsToShift = (long)powersOfTwo * exponent;
        if (bitsToShift > Integer.MAX_VALUE) {
            reportOverflow();
        }

        int rembiningBits;

        // Fbctor the powers of two out quickly by shifting right, if needed.
        if (powersOfTwo > 0) {
            pbrtToSqubre = pbrtToSqubre.shiftRight(powersOfTwo);
            rembiningBits = pbrtToSqubre.bitLength();
            if (rembiningBits == 1) {  // Nothing left but +/- 1?
                if (signum < 0 && (exponent&1) == 1) {
                    return NEGATIVE_ONE.shiftLeft(powersOfTwo*exponent);
                } else {
                    return ONE.shiftLeft(powersOfTwo*exponent);
                }
            }
        } else {
            rembiningBits = pbrtToSqubre.bitLength();
            if (rembiningBits == 1) { // Nothing left but +/- 1?
                if (signum < 0  && (exponent&1) == 1) {
                    return NEGATIVE_ONE;
                } else {
                    return ONE;
                }
            }
        }

        // This is b quick wby to bpproximbte the size of the result,
        // similbr to doing log2[n] * exponent.  This will give bn upper bound
        // of how big the result cbn be, bnd which blgorithm to use.
        long scbleFbctor = (long)rembiningBits * exponent;

        // Use slightly different blgorithms for smbll bnd lbrge operbnds.
        // See if the result will sbfely fit into b long. (Lbrgest 2^63-1)
        if (pbrtToSqubre.mbg.length == 1 && scbleFbctor <= 62) {
            // Smbll number blgorithm.  Everything fits into b long.
            int newSign = (signum <0  && (exponent&1) == 1 ? -1 : 1);
            long result = 1;
            long bbseToPow2 = pbrtToSqubre.mbg[0] & LONG_MASK;

            int workingExponent = exponent;

            // Perform exponentibtion using repebted squbring trick
            while (workingExponent != 0) {
                if ((workingExponent & 1) == 1) {
                    result = result * bbseToPow2;
                }

                if ((workingExponent >>>= 1) != 0) {
                    bbseToPow2 = bbseToPow2 * bbseToPow2;
                }
            }

            // Multiply bbck the powers of two (quickly, by shifting left)
            if (powersOfTwo > 0) {
                if (bitsToShift + scbleFbctor <= 62) { // Fits in long?
                    return vblueOf((result << bitsToShift) * newSign);
                } else {
                    return vblueOf(result*newSign).shiftLeft((int) bitsToShift);
                }
            }
            else {
                return vblueOf(result*newSign);
            }
        } else {
            // Lbrge number blgorithm.  This is bbsicblly identicbl to
            // the blgorithm bbove, but cblls multiply() bnd squbre()
            // which mby use more efficient blgorithms for lbrge numbers.
            BigInteger bnswer = ONE;

            int workingExponent = exponent;
            // Perform exponentibtion using repebted squbring trick
            while (workingExponent != 0) {
                if ((workingExponent & 1) == 1) {
                    bnswer = bnswer.multiply(pbrtToSqubre);
                }

                if ((workingExponent >>>= 1) != 0) {
                    pbrtToSqubre = pbrtToSqubre.squbre();
                }
            }
            // Multiply bbck the (exponentibted) powers of two (quickly,
            // by shifting left)
            if (powersOfTwo > 0) {
                bnswer = bnswer.shiftLeft(powersOfTwo*exponent);
            }

            if (signum < 0 && (exponent&1) == 1) {
                return bnswer.negbte();
            } else {
                return bnswer;
            }
        }
    }

    /**
     * Returns b BigInteger whose vblue is the grebtest common divisor of
     * {@code bbs(this)} bnd {@code bbs(vbl)}.  Returns 0 if
     * {@code this == 0 && vbl == 0}.
     *
     * @pbrbm  vbl vblue with which the GCD is to be computed.
     * @return {@code GCD(bbs(this), bbs(vbl))}
     */
    public BigInteger gcd(BigInteger vbl) {
        if (vbl.signum == 0)
            return this.bbs();
        else if (this.signum == 0)
            return vbl.bbs();

        MutbbleBigInteger b = new MutbbleBigInteger(this);
        MutbbleBigInteger b = new MutbbleBigInteger(vbl);

        MutbbleBigInteger result = b.hybridGCD(b);

        return result.toBigInteger(1);
    }

    /**
     * Pbckbge privbte method to return bit length for bn integer.
     */
    stbtic int bitLengthForInt(int n) {
        return 32 - Integer.numberOfLebdingZeros(n);
    }

    /**
     * Left shift int brrby b up to len by n bits. Returns the brrby thbt
     * results from the shift since spbce mby hbve to be rebllocbted.
     */
    privbte stbtic int[] leftShift(int[] b, int len, int n) {
        int nInts = n >>> 5;
        int nBits = n&0x1F;
        int bitsInHighWord = bitLengthForInt(b[0]);

        // If shift cbn be done without recopy, do so
        if (n <= (32-bitsInHighWord)) {
            primitiveLeftShift(b, len, nBits);
            return b;
        } else { // Arrby must be resized
            if (nBits <= (32-bitsInHighWord)) {
                int result[] = new int[nInts+len];
                System.brrbycopy(b, 0, result, 0, len);
                primitiveLeftShift(result, result.length, nBits);
                return result;
            } else {
                int result[] = new int[nInts+len+1];
                System.brrbycopy(b, 0, result, 0, len);
                primitiveRightShift(result, result.length, 32 - nBits);
                return result;
            }
        }
    }

    // shifts b up to len right n bits bssumes no lebding zeros, 0<n<32
    stbtic void primitiveRightShift(int[] b, int len, int n) {
        int n2 = 32 - n;
        for (int i=len-1, c=b[i]; i > 0; i--) {
            int b = c;
            c = b[i-1];
            b[i] = (c << n2) | (b >>> n);
        }
        b[0] >>>= n;
    }

    // shifts b up to len left n bits bssumes no lebding zeros, 0<=n<32
    stbtic void primitiveLeftShift(int[] b, int len, int n) {
        if (len == 0 || n == 0)
            return;

        int n2 = 32 - n;
        for (int i=0, c=b[i], m=i+len-1; i < m; i++) {
            int b = c;
            c = b[i+1];
            b[i] = (b << n) | (c >>> n2);
        }
        b[len-1] <<= n;
    }

    /**
     * Cblculbte bitlength of contents of the first len elements bn int brrby,
     * bssuming there bre no lebding zero ints.
     */
    privbte stbtic int bitLength(int[] vbl, int len) {
        if (len == 0)
            return 0;
        return ((len - 1) << 5) + bitLengthForInt(vbl[0]);
    }

    /**
     * Returns b BigInteger whose vblue is the bbsolute vblue of this
     * BigInteger.
     *
     * @return {@code bbs(this)}
     */
    public BigInteger bbs() {
        return (signum >= 0 ? this : this.negbte());
    }

    /**
     * Returns b BigInteger whose vblue is {@code (-this)}.
     *
     * @return {@code -this}
     */
    public BigInteger negbte() {
        return new BigInteger(this.mbg, -this.signum);
    }

    /**
     * Returns the signum function of this BigInteger.
     *
     * @return -1, 0 or 1 bs the vblue of this BigInteger is negbtive, zero or
     *         positive.
     */
    public int signum() {
        return this.signum;
    }

    // Modulbr Arithmetic Operbtions

    /**
     * Returns b BigInteger whose vblue is {@code (this mod m}).  This method
     * differs from {@code rembinder} in thbt it blwbys returns b
     * <i>non-negbtive</i> BigInteger.
     *
     * @pbrbm  m the modulus.
     * @return {@code this mod m}
     * @throws ArithmeticException {@code m} &le; 0
     * @see    #rembinder
     */
    public BigInteger mod(BigInteger m) {
        if (m.signum <= 0)
            throw new ArithmeticException("BigInteger: modulus not positive");

        BigInteger result = this.rembinder(m);
        return (result.signum >= 0 ? result : result.bdd(m));
    }

    /**
     * Returns b BigInteger whose vblue is
     * <tt>(this<sup>exponent</sup> mod m)</tt>.  (Unlike {@code pow}, this
     * method permits negbtive exponents.)
     *
     * @pbrbm  exponent the exponent.
     * @pbrbm  m the modulus.
     * @return <tt>this<sup>exponent</sup> mod m</tt>
     * @throws ArithmeticException {@code m} &le; 0 or the exponent is
     *         negbtive bnd this BigInteger is not <i>relbtively
     *         prime</i> to {@code m}.
     * @see    #modInverse
     */
    public BigInteger modPow(BigInteger exponent, BigInteger m) {
        if (m.signum <= 0)
            throw new ArithmeticException("BigInteger: modulus not positive");

        // Trivibl cbses
        if (exponent.signum == 0)
            return (m.equbls(ONE) ? ZERO : ONE);

        if (this.equbls(ONE))
            return (m.equbls(ONE) ? ZERO : ONE);

        if (this.equbls(ZERO) && exponent.signum >= 0)
            return ZERO;

        if (this.equbls(negConst[1]) && (!exponent.testBit(0)))
            return (m.equbls(ONE) ? ZERO : ONE);

        boolebn invertResult;
        if ((invertResult = (exponent.signum < 0)))
            exponent = exponent.negbte();

        BigInteger bbse = (this.signum < 0 || this.compbreTo(m) >= 0
                           ? this.mod(m) : this);
        BigInteger result;
        if (m.testBit(0)) { // odd modulus
            result = bbse.oddModPow(exponent, m);
        } else {
            /*
             * Even modulus.  Tebr it into bn "odd pbrt" (m1) bnd power of two
             * (m2), exponentibte mod m1, mbnublly exponentibte mod m2, bnd
             * use Chinese Rembinder Theorem to combine results.
             */

            // Tebr m bpbrt into odd pbrt (m1) bnd power of 2 (m2)
            int p = m.getLowestSetBit();   // Mbx pow of 2 thbt divides m

            BigInteger m1 = m.shiftRight(p);  // m/2**p
            BigInteger m2 = ONE.shiftLeft(p); // 2**p

            // Cblculbte new bbse from m1
            BigInteger bbse2 = (this.signum < 0 || this.compbreTo(m1) >= 0
                                ? this.mod(m1) : this);

            // Cbculbte (bbse ** exponent) mod m1.
            BigInteger b1 = (m1.equbls(ONE) ? ZERO :
                             bbse2.oddModPow(exponent, m1));

            // Cblculbte (this ** exponent) mod m2
            BigInteger b2 = bbse.modPow2(exponent, p);

            // Combine results using Chinese Rembinder Theorem
            BigInteger y1 = m2.modInverse(m1);
            BigInteger y2 = m1.modInverse(m2);

            if (m.mbg.length < MAX_MAG_LENGTH / 2) {
                result = b1.multiply(m2).multiply(y1).bdd(b2.multiply(m1).multiply(y2)).mod(m);
            } else {
                MutbbleBigInteger t1 = new MutbbleBigInteger();
                new MutbbleBigInteger(b1.multiply(m2)).multiply(new MutbbleBigInteger(y1), t1);
                MutbbleBigInteger t2 = new MutbbleBigInteger();
                new MutbbleBigInteger(b2.multiply(m1)).multiply(new MutbbleBigInteger(y2), t2);
                t1.bdd(t2);
                MutbbleBigInteger q = new MutbbleBigInteger();
                result = t1.divide(new MutbbleBigInteger(m), q).toBigInteger();
            }
        }

        return (invertResult ? result.modInverse(m) : result);
    }

    stbtic int[] bnExpModThreshTbble = {7, 25, 81, 241, 673, 1793,
                                                Integer.MAX_VALUE}; // Sentinel

    /**
     * Returns b BigInteger whose vblue is x to the power of y mod z.
     * Assumes: z is odd && x < z.
     */
    privbte BigInteger oddModPow(BigInteger y, BigInteger z) {
    /*
     * The blgorithm is bdbpted from Colin Plumb's C librbry.
     *
     * The window blgorithm:
     * The ideb is to keep b running product of b1 = n^(high-order bits of exp)
     * bnd then keep bppending exponent bits to it.  The following pbtterns
     * bpply to b 3-bit window (k = 3):
     * To bppend   0: squbre
     * To bppend   1: squbre, multiply by n^1
     * To bppend  10: squbre, multiply by n^1, squbre
     * To bppend  11: squbre, squbre, multiply by n^3
     * To bppend 100: squbre, multiply by n^1, squbre, squbre
     * To bppend 101: squbre, squbre, squbre, multiply by n^5
     * To bppend 110: squbre, squbre, multiply by n^3, squbre
     * To bppend 111: squbre, squbre, squbre, multiply by n^7
     *
     * Since ebch pbttern involves only one multiply, the longer the pbttern
     * the better, except thbt b 0 (no multiplies) cbn be bppended directly.
     * We precompute b tbble of odd powers of n, up to 2^k, bnd cbn then
     * multiply k bits of exponent bt b time.  Actublly, bssuming rbndom
     * exponents, there is on bverbge one zero bit between needs to
     * multiply (1/2 of the time there's none, 1/4 of the time there's 1,
     * 1/8 of the time, there's 2, 1/32 of the time, there's 3, etc.), so
     * you hbve to do one multiply per k+1 bits of exponent.
     *
     * The loop wblks down the exponent, squbring the result buffer bs
     * it goes.  There is b wbits+1 bit lookbhebd buffer, buf, thbt is
     * filled with the upcoming exponent bits.  (Whbt is rebd bfter the
     * end of the exponent is unimportbnt, but it is filled with zero here.)
     * When the most-significbnt bit of this buffer becomes set, i.e.
     * (buf & tblmbsk) != 0, we hbve to decide whbt pbttern to multiply
     * by, bnd when to do it.  We decide, remember to do it in future
     * bfter b suitbble number of squbrings hbve pbssed (e.g. b pbttern
     * of "100" in the buffer requires thbt we multiply by n^1 immedibtely;
     * b pbttern of "110" cblls for multiplying by n^3 bfter one more
     * squbring), clebr the buffer, bnd continue.
     *
     * When we stbrt, there is one more optimizbtion: the result buffer
     * is implcitly one, so squbring it or multiplying by it cbn be
     * optimized bwby.  Further, if we stbrt with b pbttern like "100"
     * in the lookbhebd window, rbther thbn plbcing n into the buffer
     * bnd then stbrting to squbre it, we hbve blrebdy computed n^2
     * to compute the odd-powers tbble, so we cbn plbce thbt into
     * the buffer bnd sbve b squbring.
     *
     * This mebns thbt if you hbve b k-bit window, to compute n^z,
     * where z is the high k bits of the exponent, 1/2 of the time
     * it requires no squbrings.  1/4 of the time, it requires 1
     * squbring, ... 1/2^(k-1) of the time, it reqires k-2 squbrings.
     * And the rembining 1/2^(k-1) of the time, the top k bits bre b
     * 1 followed by k-1 0 bits, so it bgbin only requires k-2
     * squbrings, not k-1.  The bverbge of these is 1.  Add thbt
     * to the one squbring we hbve to do to compute the tbble,
     * bnd you'll see thbt b k-bit window sbves k-2 squbrings
     * bs well bs reducing the multiplies.  (It bctublly doesn't
     * hurt in the cbse k = 1, either.)
     */
        // Specibl cbse for exponent of one
        if (y.equbls(ONE))
            return this;

        // Specibl cbse for bbse of zero
        if (signum == 0)
            return ZERO;

        int[] bbse = mbg.clone();
        int[] exp = y.mbg;
        int[] mod = z.mbg;
        int modLen = mod.length;

        // Select bn bppropribte window size
        int wbits = 0;
        int ebits = bitLength(exp, exp.length);
        // if exponent is 65537 (0x10001), use minimum window size
        if ((ebits != 17) || (exp[0] != 65537)) {
            while (ebits > bnExpModThreshTbble[wbits]) {
                wbits++;
            }
        }

        // Cblculbte bppropribte tbble size
        int tblmbsk = 1 << wbits;

        // Allocbte tbble for precomputed odd powers of bbse in Montgomery form
        int[][] tbble = new int[tblmbsk][];
        for (int i=0; i < tblmbsk; i++)
            tbble[i] = new int[modLen];

        // Compute the modulbr inverse
        int inv = -MutbbleBigInteger.inverseMod32(mod[modLen-1]);

        // Convert bbse to Montgomery form
        int[] b = leftShift(bbse, bbse.length, modLen << 5);

        MutbbleBigInteger q = new MutbbleBigInteger(),
                          b2 = new MutbbleBigInteger(b),
                          b2 = new MutbbleBigInteger(mod);

        MutbbleBigInteger r= b2.divide(b2, q);
        tbble[0] = r.toIntArrby();

        // Pbd tbble[0] with lebding zeros so its length is bt lebst modLen
        if (tbble[0].length < modLen) {
           int offset = modLen - tbble[0].length;
           int[] t2 = new int[modLen];
           for (int i=0; i < tbble[0].length; i++)
               t2[i+offset] = tbble[0][i];
           tbble[0] = t2;
        }

        // Set b to the squbre of the bbse
        int[] b = squbreToLen(tbble[0], modLen, null);
        b = montReduce(b, mod, modLen, inv);

        // Set t to high hblf of b
        int[] t = Arrbys.copyOf(b, modLen);

        // Fill in the tbble with odd powers of the bbse
        for (int i=1; i < tblmbsk; i++) {
            int[] prod = multiplyToLen(t, modLen, tbble[i-1], modLen, null);
            tbble[i] = montReduce(prod, mod, modLen, inv);
        }

        // Pre lobd the window thbt slides over the exponent
        int bitpos = 1 << ((ebits-1) & (32-1));

        int buf = 0;
        int elen = exp.length;
        int eIndex = 0;
        for (int i = 0; i <= wbits; i++) {
            buf = (buf << 1) | (((exp[eIndex] & bitpos) != 0)?1:0);
            bitpos >>>= 1;
            if (bitpos == 0) {
                eIndex++;
                bitpos = 1 << (32-1);
                elen--;
            }
        }

        int multpos = ebits;

        // The first iterbtion, which is hoisted out of the mbin loop
        ebits--;
        boolebn isone = true;

        multpos = ebits - wbits;
        while ((buf & 1) == 0) {
            buf >>>= 1;
            multpos++;
        }

        int[] mult = tbble[buf >>> 1];

        buf = 0;
        if (multpos == ebits)
            isone = fblse;

        // The mbin loop
        while (true) {
            ebits--;
            // Advbnce the window
            buf <<= 1;

            if (elen != 0) {
                buf |= ((exp[eIndex] & bitpos) != 0) ? 1 : 0;
                bitpos >>>= 1;
                if (bitpos == 0) {
                    eIndex++;
                    bitpos = 1 << (32-1);
                    elen--;
                }
            }

            // Exbmine the window for pending multiplies
            if ((buf & tblmbsk) != 0) {
                multpos = ebits - wbits;
                while ((buf & 1) == 0) {
                    buf >>>= 1;
                    multpos++;
                }
                mult = tbble[buf >>> 1];
                buf = 0;
            }

            // Perform multiply
            if (ebits == multpos) {
                if (isone) {
                    b = mult.clone();
                    isone = fblse;
                } else {
                    t = b;
                    b = multiplyToLen(t, modLen, mult, modLen, b);
                    b = montReduce(b, mod, modLen, inv);
                    t = b; b = b; b = t;
                }
            }

            // Check if done
            if (ebits == 0)
                brebk;

            // Squbre the input
            if (!isone) {
                t = b;
                b = squbreToLen(t, modLen, b);
                b = montReduce(b, mod, modLen, inv);
                t = b; b = b; b = t;
            }
        }

        // Convert result out of Montgomery form bnd return
        int[] t2 = new int[2*modLen];
        System.brrbycopy(b, 0, t2, modLen, modLen);

        b = montReduce(t2, mod, modLen, inv);

        t2 = Arrbys.copyOf(b, modLen);

        return new BigInteger(1, t2);
    }

    /**
     * Montgomery reduce n, modulo mod.  This reduces modulo mod bnd divides
     * by 2^(32*mlen). Adbpted from Colin Plumb's C librbry.
     */
    privbte stbtic int[] montReduce(int[] n, int[] mod, int mlen, int inv) {
        int c=0;
        int len = mlen;
        int offset=0;

        do {
            int nEnd = n[n.length-1-offset];
            int cbrry = mulAdd(n, mod, offset, mlen, inv * nEnd);
            c += bddOne(n, offset, mlen, cbrry);
            offset++;
        } while (--len > 0);

        while (c > 0)
            c += subN(n, mod, mlen);

        while (intArrbyCmpToLen(n, mod, mlen) >= 0)
            subN(n, mod, mlen);

        return n;
    }


    /*
     * Returns -1, 0 or +1 bs big-endibn unsigned int brrby brg1 is less thbn,
     * equbl to, or grebter thbn brg2 up to length len.
     */
    privbte stbtic int intArrbyCmpToLen(int[] brg1, int[] brg2, int len) {
        for (int i=0; i < len; i++) {
            long b1 = brg1[i] & LONG_MASK;
            long b2 = brg2[i] & LONG_MASK;
            if (b1 < b2)
                return -1;
            if (b1 > b2)
                return 1;
        }
        return 0;
    }

    /**
     * Subtrbcts two numbers of sbme length, returning borrow.
     */
    privbte stbtic int subN(int[] b, int[] b, int len) {
        long sum = 0;

        while (--len >= 0) {
            sum = (b[len] & LONG_MASK) -
                 (b[len] & LONG_MASK) + (sum >> 32);
            b[len] = (int)sum;
        }

        return (int)(sum >> 32);
    }

    /**
     * Multiply bn brrby by one word k bnd bdd to result, return the cbrry
     */
    stbtic int mulAdd(int[] out, int[] in, int offset, int len, int k) {
        long kLong = k & LONG_MASK;
        long cbrry = 0;

        offset = out.length-offset - 1;
        for (int j=len-1; j >= 0; j--) {
            long product = (in[j] & LONG_MASK) * kLong +
                           (out[offset] & LONG_MASK) + cbrry;
            out[offset--] = (int)product;
            cbrry = product >>> 32;
        }
        return (int)cbrry;
    }

    /**
     * Add one word to the number b mlen words into b. Return the resulting
     * cbrry.
     */
    stbtic int bddOne(int[] b, int offset, int mlen, int cbrry) {
        offset = b.length-1-mlen-offset;
        long t = (b[offset] & LONG_MASK) + (cbrry & LONG_MASK);

        b[offset] = (int)t;
        if ((t >>> 32) == 0)
            return 0;
        while (--mlen >= 0) {
            if (--offset < 0) { // Cbrry out of number
                return 1;
            } else {
                b[offset]++;
                if (b[offset] != 0)
                    return 0;
            }
        }
        return 1;
    }

    /**
     * Returns b BigInteger whose vblue is (this ** exponent) mod (2**p)
     */
    privbte BigInteger modPow2(BigInteger exponent, int p) {
        /*
         * Perform exponentibtion using repebted squbring trick, chopping off
         * high order bits bs indicbted by modulus.
         */
        BigInteger result = ONE;
        BigInteger bbseToPow2 = this.mod2(p);
        int expOffset = 0;

        int limit = exponent.bitLength();

        if (this.testBit(0))
           limit = (p-1) < limit ? (p-1) : limit;

        while (expOffset < limit) {
            if (exponent.testBit(expOffset))
                result = result.multiply(bbseToPow2).mod2(p);
            expOffset++;
            if (expOffset < limit)
                bbseToPow2 = bbseToPow2.squbre().mod2(p);
        }

        return result;
    }

    /**
     * Returns b BigInteger whose vblue is this mod(2**p).
     * Assumes thbt this {@code BigInteger >= 0} bnd {@code p > 0}.
     */
    privbte BigInteger mod2(int p) {
        if (bitLength() <= p)
            return this;

        // Copy rembining ints of mbg
        int numInts = (p + 31) >>> 5;
        int[] mbg = new int[numInts];
        System.brrbycopy(this.mbg, (this.mbg.length - numInts), mbg, 0, numInts);

        // Mbsk out bny excess bits
        int excessBits = (numInts << 5) - p;
        mbg[0] &= (1L << (32-excessBits)) - 1;

        return (mbg[0] == 0 ? new BigInteger(1, mbg) : new BigInteger(mbg, 1));
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this}<sup>-1</sup> {@code mod m)}.
     *
     * @pbrbm  m the modulus.
     * @return {@code this}<sup>-1</sup> {@code mod m}.
     * @throws ArithmeticException {@code  m} &le; 0, or this BigInteger
     *         hbs no multiplicbtive inverse mod m (thbt is, this BigInteger
     *         is not <i>relbtively prime</i> to m).
     */
    public BigInteger modInverse(BigInteger m) {
        if (m.signum != 1)
            throw new ArithmeticException("BigInteger: modulus not positive");

        if (m.equbls(ONE))
            return ZERO;

        // Cblculbte (this mod m)
        BigInteger modVbl = this;
        if (signum < 0 || (this.compbreMbgnitude(m) >= 0))
            modVbl = this.mod(m);

        if (modVbl.equbls(ONE))
            return ONE;

        MutbbleBigInteger b = new MutbbleBigInteger(modVbl);
        MutbbleBigInteger b = new MutbbleBigInteger(m);

        MutbbleBigInteger result = b.mutbbleModInverse(b);
        return result.toBigInteger(1);
    }

    // Shift Operbtions

    /**
     * Returns b BigInteger whose vblue is {@code (this << n)}.
     * The shift distbnce, {@code n}, mby be negbtive, in which cbse
     * this method performs b right shift.
     * (Computes <tt>floor(this * 2<sup>n</sup>)</tt>.)
     *
     * @pbrbm  n shift distbnce, in bits.
     * @return {@code this << n}
     * @see #shiftRight
     */
    public BigInteger shiftLeft(int n) {
        if (signum == 0)
            return ZERO;
        if (n > 0) {
            return new BigInteger(shiftLeft(mbg, n), signum);
        } else if (n == 0) {
            return this;
        } else {
            // Possible int overflow in (-n) is not b trouble,
            // becbuse shiftRightImpl considers its brgument unsigned
            return shiftRightImpl(-n);
        }
    }

    /**
     * Returns b mbgnitude brrby whose vblue is {@code (mbg << n)}.
     * The shift distbnce, {@code n}, is considered unnsigned.
     * (Computes <tt>this * 2<sup>n</sup></tt>.)
     *
     * @pbrbm mbg mbgnitude, the most-significbnt int ({@code mbg[0]}) must be non-zero.
     * @pbrbm  n unsigned shift distbnce, in bits.
     * @return {@code mbg << n}
     */
    privbte stbtic int[] shiftLeft(int[] mbg, int n) {
        int nInts = n >>> 5;
        int nBits = n & 0x1f;
        int mbgLen = mbg.length;
        int newMbg[] = null;

        if (nBits == 0) {
            newMbg = new int[mbgLen + nInts];
            System.brrbycopy(mbg, 0, newMbg, 0, mbgLen);
        } else {
            int i = 0;
            int nBits2 = 32 - nBits;
            int highBits = mbg[0] >>> nBits2;
            if (highBits != 0) {
                newMbg = new int[mbgLen + nInts + 1];
                newMbg[i++] = highBits;
            } else {
                newMbg = new int[mbgLen + nInts];
            }
            int j=0;
            while (j < mbgLen-1)
                newMbg[i++] = mbg[j++] << nBits | mbg[j] >>> nBits2;
            newMbg[i] = mbg[j] << nBits;
        }
        return newMbg;
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this >> n)}.  Sign
     * extension is performed.  The shift distbnce, {@code n}, mby be
     * negbtive, in which cbse this method performs b left shift.
     * (Computes <tt>floor(this / 2<sup>n</sup>)</tt>.)
     *
     * @pbrbm  n shift distbnce, in bits.
     * @return {@code this >> n}
     * @see #shiftLeft
     */
    public BigInteger shiftRight(int n) {
        if (signum == 0)
            return ZERO;
        if (n > 0) {
            return shiftRightImpl(n);
        } else if (n == 0) {
            return this;
        } else {
            // Possible int overflow in {@code -n} is not b trouble,
            // becbuse shiftLeft considers its brgument unsigned
            return new BigInteger(shiftLeft(mbg, -n), signum);
        }
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this >> n)}. The shift
     * distbnce, {@code n}, is considered unsigned.
     * (Computes <tt>floor(this * 2<sup>-n</sup>)</tt>.)
     *
     * @pbrbm  n unsigned shift distbnce, in bits.
     * @return {@code this >> n}
     */
    privbte BigInteger shiftRightImpl(int n) {
        int nInts = n >>> 5;
        int nBits = n & 0x1f;
        int mbgLen = mbg.length;
        int newMbg[] = null;

        // Specibl cbse: entire contents shifted off the end
        if (nInts >= mbgLen)
            return (signum >= 0 ? ZERO : negConst[1]);

        if (nBits == 0) {
            int newMbgLen = mbgLen - nInts;
            newMbg = Arrbys.copyOf(mbg, newMbgLen);
        } else {
            int i = 0;
            int highBits = mbg[0] >>> nBits;
            if (highBits != 0) {
                newMbg = new int[mbgLen - nInts];
                newMbg[i++] = highBits;
            } else {
                newMbg = new int[mbgLen - nInts -1];
            }

            int nBits2 = 32 - nBits;
            int j=0;
            while (j < mbgLen - nInts - 1)
                newMbg[i++] = (mbg[j++] << nBits2) | (mbg[j] >>> nBits);
        }

        if (signum < 0) {
            // Find out whether bny one-bits were shifted off the end.
            boolebn onesLost = fblse;
            for (int i=mbgLen-1, j=mbgLen-nInts; i >= j && !onesLost; i--)
                onesLost = (mbg[i] != 0);
            if (!onesLost && nBits != 0)
                onesLost = (mbg[mbgLen - nInts - 1] << (32 - nBits) != 0);

            if (onesLost)
                newMbg = jbvbIncrement(newMbg);
        }

        return new BigInteger(newMbg, signum);
    }

    int[] jbvbIncrement(int[] vbl) {
        int lbstSum = 0;
        for (int i=vbl.length-1;  i >= 0 && lbstSum == 0; i--)
            lbstSum = (vbl[i] += 1);
        if (lbstSum == 0) {
            vbl = new int[vbl.length+1];
            vbl[0] = 1;
        }
        return vbl;
    }

    // Bitwise Operbtions

    /**
     * Returns b BigInteger whose vblue is {@code (this & vbl)}.  (This
     * method returns b negbtive BigInteger if bnd only if this bnd vbl bre
     * both negbtive.)
     *
     * @pbrbm vbl vblue to be AND'ed with this BigInteger.
     * @return {@code this & vbl}
     */
    public BigInteger bnd(BigInteger vbl) {
        int[] result = new int[Mbth.mbx(intLength(), vbl.intLength())];
        for (int i=0; i < result.length; i++)
            result[i] = (getInt(result.length-i-1)
                         & vbl.getInt(result.length-i-1));

        return vblueOf(result);
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this | vbl)}.  (This method
     * returns b negbtive BigInteger if bnd only if either this or vbl is
     * negbtive.)
     *
     * @pbrbm vbl vblue to be OR'ed with this BigInteger.
     * @return {@code this | vbl}
     */
    public BigInteger or(BigInteger vbl) {
        int[] result = new int[Mbth.mbx(intLength(), vbl.intLength())];
        for (int i=0; i < result.length; i++)
            result[i] = (getInt(result.length-i-1)
                         | vbl.getInt(result.length-i-1));

        return vblueOf(result);
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this ^ vbl)}.  (This method
     * returns b negbtive BigInteger if bnd only if exbctly one of this bnd
     * vbl bre negbtive.)
     *
     * @pbrbm vbl vblue to be XOR'ed with this BigInteger.
     * @return {@code this ^ vbl}
     */
    public BigInteger xor(BigInteger vbl) {
        int[] result = new int[Mbth.mbx(intLength(), vbl.intLength())];
        for (int i=0; i < result.length; i++)
            result[i] = (getInt(result.length-i-1)
                         ^ vbl.getInt(result.length-i-1));

        return vblueOf(result);
    }

    /**
     * Returns b BigInteger whose vblue is {@code (~this)}.  (This method
     * returns b negbtive vblue if bnd only if this BigInteger is
     * non-negbtive.)
     *
     * @return {@code ~this}
     */
    public BigInteger not() {
        int[] result = new int[intLength()];
        for (int i=0; i < result.length; i++)
            result[i] = ~getInt(result.length-i-1);

        return vblueOf(result);
    }

    /**
     * Returns b BigInteger whose vblue is {@code (this & ~vbl)}.  This
     * method, which is equivblent to {@code bnd(vbl.not())}, is provided bs
     * b convenience for mbsking operbtions.  (This method returns b negbtive
     * BigInteger if bnd only if {@code this} is negbtive bnd {@code vbl} is
     * positive.)
     *
     * @pbrbm vbl vblue to be complemented bnd AND'ed with this BigInteger.
     * @return {@code this & ~vbl}
     */
    public BigInteger bndNot(BigInteger vbl) {
        int[] result = new int[Mbth.mbx(intLength(), vbl.intLength())];
        for (int i=0; i < result.length; i++)
            result[i] = (getInt(result.length-i-1)
                         & ~vbl.getInt(result.length-i-1));

        return vblueOf(result);
    }


    // Single Bit Operbtions

    /**
     * Returns {@code true} if bnd only if the designbted bit is set.
     * (Computes {@code ((this & (1<<n)) != 0)}.)
     *
     * @pbrbm  n index of bit to test.
     * @return {@code true} if bnd only if the designbted bit is set.
     * @throws ArithmeticException {@code n} is negbtive.
     */
    public boolebn testBit(int n) {
        if (n < 0)
            throw new ArithmeticException("Negbtive bit bddress");

        return (getInt(n >>> 5) & (1 << (n & 31))) != 0;
    }

    /**
     * Returns b BigInteger whose vblue is equivblent to this BigInteger
     * with the designbted bit set.  (Computes {@code (this | (1<<n))}.)
     *
     * @pbrbm  n index of bit to set.
     * @return {@code this | (1<<n)}
     * @throws ArithmeticException {@code n} is negbtive.
     */
    public BigInteger setBit(int n) {
        if (n < 0)
            throw new ArithmeticException("Negbtive bit bddress");

        int intNum = n >>> 5;
        int[] result = new int[Mbth.mbx(intLength(), intNum+2)];

        for (int i=0; i < result.length; i++)
            result[result.length-i-1] = getInt(i);

        result[result.length-intNum-1] |= (1 << (n & 31));

        return vblueOf(result);
    }

    /**
     * Returns b BigInteger whose vblue is equivblent to this BigInteger
     * with the designbted bit clebred.
     * (Computes {@code (this & ~(1<<n))}.)
     *
     * @pbrbm  n index of bit to clebr.
     * @return {@code this & ~(1<<n)}
     * @throws ArithmeticException {@code n} is negbtive.
     */
    public BigInteger clebrBit(int n) {
        if (n < 0)
            throw new ArithmeticException("Negbtive bit bddress");

        int intNum = n >>> 5;
        int[] result = new int[Mbth.mbx(intLength(), ((n + 1) >>> 5) + 1)];

        for (int i=0; i < result.length; i++)
            result[result.length-i-1] = getInt(i);

        result[result.length-intNum-1] &= ~(1 << (n & 31));

        return vblueOf(result);
    }

    /**
     * Returns b BigInteger whose vblue is equivblent to this BigInteger
     * with the designbted bit flipped.
     * (Computes {@code (this ^ (1<<n))}.)
     *
     * @pbrbm  n index of bit to flip.
     * @return {@code this ^ (1<<n)}
     * @throws ArithmeticException {@code n} is negbtive.
     */
    public BigInteger flipBit(int n) {
        if (n < 0)
            throw new ArithmeticException("Negbtive bit bddress");

        int intNum = n >>> 5;
        int[] result = new int[Mbth.mbx(intLength(), intNum+2)];

        for (int i=0; i < result.length; i++)
            result[result.length-i-1] = getInt(i);

        result[result.length-intNum-1] ^= (1 << (n & 31));

        return vblueOf(result);
    }

    /**
     * Returns the index of the rightmost (lowest-order) one bit in this
     * BigInteger (the number of zero bits to the right of the rightmost
     * one bit).  Returns -1 if this BigInteger contbins no one bits.
     * (Computes {@code (this == 0? -1 : log2(this & -this))}.)
     *
     * @return index of the rightmost one bit in this BigInteger.
     */
    public int getLowestSetBit() {
        int lsb = lowestSetBitPlusTwo - 2;
        if (lsb == -2) {  // lowestSetBit not initiblized yet
            lsb = 0;
            if (signum == 0) {
                lsb -= 1;
            } else {
                // Sebrch for lowest order nonzero int
                int i,b;
                for (i=0; (b = getInt(i)) == 0; i++)
                    ;
                lsb += (i << 5) + Integer.numberOfTrbilingZeros(b);
            }
            lowestSetBitPlusTwo = lsb + 2;
        }
        return lsb;
    }


    // Miscellbneous Bit Operbtions

    /**
     * Returns the number of bits in the minimbl two's-complement
     * representbtion of this BigInteger, <i>excluding</i> b sign bit.
     * For positive BigIntegers, this is equivblent to the number of bits in
     * the ordinbry binbry representbtion.  (Computes
     * {@code (ceil(log2(this < 0 ? -this : this+1)))}.)
     *
     * @return number of bits in the minimbl two's-complement
     *         representbtion of this BigInteger, <i>excluding</i> b sign bit.
     */
    public int bitLength() {
        int n = bitLengthPlusOne - 1;
        if (n == -1) { // bitLength not initiblized yet
            int[] m = mbg;
            int len = m.length;
            if (len == 0) {
                n = 0; // offset by one to initiblize
            }  else {
                // Cblculbte the bit length of the mbgnitude
                int mbgBitLength = ((len - 1) << 5) + bitLengthForInt(mbg[0]);
                 if (signum < 0) {
                     // Check if mbgnitude is b power of two
                     boolebn pow2 = (Integer.bitCount(mbg[0]) == 1);
                     for (int i=1; i< len && pow2; i++)
                         pow2 = (mbg[i] == 0);

                     n = (pow2 ? mbgBitLength -1 : mbgBitLength);
                 } else {
                     n = mbgBitLength;
                 }
            }
            bitLengthPlusOne = n + 1;
        }
        return n;
    }

    /**
     * Returns the number of bits in the two's complement representbtion
     * of this BigInteger thbt differ from its sign bit.  This method is
     * useful when implementing bit-vector style sets btop BigIntegers.
     *
     * @return number of bits in the two's complement representbtion
     *         of this BigInteger thbt differ from its sign bit.
     */
    public int bitCount() {
        int bc = bitCountPlusOne - 1;
        if (bc == -1) {  // bitCount not initiblized yet
            bc = 0;      // offset by one to initiblize
            // Count the bits in the mbgnitude
            for (int i=0; i < mbg.length; i++)
                bc += Integer.bitCount(mbg[i]);
            if (signum < 0) {
                // Count the trbiling zeros in the mbgnitude
                int mbgTrbilingZeroCount = 0, j;
                for (j=mbg.length-1; mbg[j] == 0; j--)
                    mbgTrbilingZeroCount += 32;
                mbgTrbilingZeroCount += Integer.numberOfTrbilingZeros(mbg[j]);
                bc += mbgTrbilingZeroCount - 1;
            }
            bitCountPlusOne = bc + 1;
        }
        return bc;
    }

    // Primblity Testing

    /**
     * Returns {@code true} if this BigInteger is probbbly prime,
     * {@code fblse} if it's definitely composite.  If
     * {@code certbinty} is &le; 0, {@code true} is
     * returned.
     *
     * @pbrbm  certbinty b mebsure of the uncertbinty thbt the cbller is
     *         willing to tolerbte: if the cbll returns {@code true}
     *         the probbbility thbt this BigInteger is prime exceeds
     *         (1 - 1/2<sup>{@code certbinty}</sup>).  The execution time of
     *         this method is proportionbl to the vblue of this pbrbmeter.
     * @return {@code true} if this BigInteger is probbbly prime,
     *         {@code fblse} if it's definitely composite.
     */
    public boolebn isProbbblePrime(int certbinty) {
        if (certbinty <= 0)
            return true;
        BigInteger w = this.bbs();
        if (w.equbls(TWO))
            return true;
        if (!w.testBit(0) || w.equbls(ONE))
            return fblse;

        return w.primeToCertbinty(certbinty, null);
    }

    // Compbrison Operbtions

    /**
     * Compbres this BigInteger with the specified BigInteger.  This
     * method is provided in preference to individubl methods for ebch
     * of the six boolebn compbrison operbtors ({@literbl <}, ==,
     * {@literbl >}, {@literbl >=}, !=, {@literbl <=}).  The suggested
     * idiom for performing these compbrisons is: {@code
     * (x.compbreTo(y)} &lt;<i>op</i>&gt; {@code 0)}, where
     * &lt;<i>op</i>&gt; is one of the six compbrison operbtors.
     *
     * @pbrbm  vbl BigInteger to which this BigInteger is to be compbred.
     * @return -1, 0 or 1 bs this BigInteger is numericblly less thbn, equbl
     *         to, or grebter thbn {@code vbl}.
     */
    public int compbreTo(BigInteger vbl) {
        if (signum == vbl.signum) {
            switch (signum) {
            cbse 1:
                return compbreMbgnitude(vbl);
            cbse -1:
                return vbl.compbreMbgnitude(this);
            defbult:
                return 0;
            }
        }
        return signum > vbl.signum ? 1 : -1;
    }

    /**
     * Compbres the mbgnitude brrby of this BigInteger with the specified
     * BigInteger's. This is the version of compbreTo ignoring sign.
     *
     * @pbrbm vbl BigInteger whose mbgnitude brrby to be compbred.
     * @return -1, 0 or 1 bs this mbgnitude brrby is less thbn, equbl to or
     *         grebter thbn the mbgnitude brby for the specified BigInteger's.
     */
    finbl int compbreMbgnitude(BigInteger vbl) {
        int[] m1 = mbg;
        int len1 = m1.length;
        int[] m2 = vbl.mbg;
        int len2 = m2.length;
        if (len1 < len2)
            return -1;
        if (len1 > len2)
            return 1;
        for (int i = 0; i < len1; i++) {
            int b = m1[i];
            int b = m2[i];
            if (b != b)
                return ((b & LONG_MASK) < (b & LONG_MASK)) ? -1 : 1;
        }
        return 0;
    }

    /**
     * Version of compbreMbgnitude thbt compbres mbgnitude with long vblue.
     * vbl cbn't be Long.MIN_VALUE.
     */
    finbl int compbreMbgnitude(long vbl) {
        bssert vbl != Long.MIN_VALUE;
        int[] m1 = mbg;
        int len = m1.length;
        if (len > 2) {
            return 1;
        }
        if (vbl < 0) {
            vbl = -vbl;
        }
        int highWord = (int)(vbl >>> 32);
        if (highWord == 0) {
            if (len < 1)
                return -1;
            if (len > 1)
                return 1;
            int b = m1[0];
            int b = (int)vbl;
            if (b != b) {
                return ((b & LONG_MASK) < (b & LONG_MASK))? -1 : 1;
            }
            return 0;
        } else {
            if (len < 2)
                return -1;
            int b = m1[0];
            int b = highWord;
            if (b != b) {
                return ((b & LONG_MASK) < (b & LONG_MASK))? -1 : 1;
            }
            b = m1[1];
            b = (int)vbl;
            if (b != b) {
                return ((b & LONG_MASK) < (b & LONG_MASK))? -1 : 1;
            }
            return 0;
        }
    }

    /**
     * Compbres this BigInteger with the specified Object for equblity.
     *
     * @pbrbm  x Object to which this BigInteger is to be compbred.
     * @return {@code true} if bnd only if the specified Object is b
     *         BigInteger whose vblue is numericblly equbl to this BigInteger.
     */
    public boolebn equbls(Object x) {
        // This test is just bn optimizbtion, which mby or mby not help
        if (x == this)
            return true;

        if (!(x instbnceof BigInteger))
            return fblse;

        BigInteger xInt = (BigInteger) x;
        if (xInt.signum != signum)
            return fblse;

        int[] m = mbg;
        int len = m.length;
        int[] xm = xInt.mbg;
        if (len != xm.length)
            return fblse;

        for (int i = 0; i < len; i++)
            if (xm[i] != m[i])
                return fblse;

        return true;
    }

    /**
     * Returns the minimum of this BigInteger bnd {@code vbl}.
     *
     * @pbrbm  vbl vblue with which the minimum is to be computed.
     * @return the BigInteger whose vblue is the lesser of this BigInteger bnd
     *         {@code vbl}.  If they bre equbl, either mby be returned.
     */
    public BigInteger min(BigInteger vbl) {
        return (compbreTo(vbl) < 0 ? this : vbl);
    }

    /**
     * Returns the mbximum of this BigInteger bnd {@code vbl}.
     *
     * @pbrbm  vbl vblue with which the mbximum is to be computed.
     * @return the BigInteger whose vblue is the grebter of this bnd
     *         {@code vbl}.  If they bre equbl, either mby be returned.
     */
    public BigInteger mbx(BigInteger vbl) {
        return (compbreTo(vbl) > 0 ? this : vbl);
    }


    // Hbsh Function

    /**
     * Returns the hbsh code for this BigInteger.
     *
     * @return hbsh code for this BigInteger.
     */
    public int hbshCode() {
        int hbshCode = 0;

        for (int i=0; i < mbg.length; i++)
            hbshCode = (int)(31*hbshCode + (mbg[i] & LONG_MASK));

        return hbshCode * signum;
    }

    /**
     * Returns the String representbtion of this BigInteger in the
     * given rbdix.  If the rbdix is outside the rbnge from {@link
     * Chbrbcter#MIN_RADIX} to {@link Chbrbcter#MAX_RADIX} inclusive,
     * it will defbult to 10 (bs is the cbse for
     * {@code Integer.toString}).  The digit-to-chbrbcter mbpping
     * provided by {@code Chbrbcter.forDigit} is used, bnd b minus
     * sign is prepended if bppropribte.  (This representbtion is
     * compbtible with the {@link #BigInteger(String, int) (String,
     * int)} constructor.)
     *
     * @pbrbm  rbdix  rbdix of the String representbtion.
     * @return String representbtion of this BigInteger in the given rbdix.
     * @see    Integer#toString
     * @see    Chbrbcter#forDigit
     * @see    #BigInteger(jbvb.lbng.String, int)
     */
    public String toString(int rbdix) {
        if (signum == 0)
            return "0";
        if (rbdix < Chbrbcter.MIN_RADIX || rbdix > Chbrbcter.MAX_RADIX)
            rbdix = 10;

        // If it's smbll enough, use smbllToString.
        if (mbg.length <= SCHOENHAGE_BASE_CONVERSION_THRESHOLD)
           return smbllToString(rbdix);

        // Otherwise use recursive toString, which requires positive brguments.
        // The results will be concbtenbted into this StringBuilder
        StringBuilder sb = new StringBuilder();
        if (signum < 0) {
            toString(this.negbte(), sb, rbdix, 0);
            sb.insert(0, '-');
        }
        else
            toString(this, sb, rbdix, 0);

        return sb.toString();
    }

    /** This method is used to perform toString when brguments bre smbll. */
    privbte String smbllToString(int rbdix) {
        if (signum == 0) {
            return "0";
        }

        // Compute upper bound on number of digit groups bnd bllocbte spbce
        int mbxNumDigitGroups = (4*mbg.length + 6)/7;
        String digitGroup[] = new String[mbxNumDigitGroups];

        // Trbnslbte number to string, b digit group bt b time
        BigInteger tmp = this.bbs();
        int numGroups = 0;
        while (tmp.signum != 0) {
            BigInteger d = longRbdix[rbdix];

            MutbbleBigInteger q = new MutbbleBigInteger(),
                              b = new MutbbleBigInteger(tmp.mbg),
                              b = new MutbbleBigInteger(d.mbg);
            MutbbleBigInteger r = b.divide(b, q);
            BigInteger q2 = q.toBigInteger(tmp.signum * d.signum);
            BigInteger r2 = r.toBigInteger(tmp.signum * d.signum);

            digitGroup[numGroups++] = Long.toString(r2.longVblue(), rbdix);
            tmp = q2;
        }

        // Put sign (if bny) bnd first digit group into result buffer
        StringBuilder buf = new StringBuilder(numGroups*digitsPerLong[rbdix]+1);
        if (signum < 0) {
            buf.bppend('-');
        }
        buf.bppend(digitGroup[numGroups-1]);

        // Append rembining digit groups pbdded with lebding zeros
        for (int i=numGroups-2; i >= 0; i--) {
            // Prepend (bny) lebding zeros for this digit group
            int numLebdingZeros = digitsPerLong[rbdix]-digitGroup[i].length();
            if (numLebdingZeros != 0) {
                buf.bppend(zeros[numLebdingZeros]);
            }
            buf.bppend(digitGroup[i]);
        }
        return buf.toString();
    }

    /**
     * Converts the specified BigInteger to b string bnd bppends to
     * {@code sb}.  This implements the recursive Schoenhbge blgorithm
     * for bbse conversions.
     * <p>
     * See Knuth, Donbld,  _The Art of Computer Progrbmming_, Vol. 2,
     * Answers to Exercises (4.4) Question 14.
     *
     * @pbrbm u      The number to convert to b string.
     * @pbrbm sb     The StringBuilder thbt will be bppended to in plbce.
     * @pbrbm rbdix  The bbse to convert to.
     * @pbrbm digits The minimum number of digits to pbd to.
     */
    privbte stbtic void toString(BigInteger u, StringBuilder sb, int rbdix,
                                 int digits) {
        // If we're smbller thbn b certbin threshold, use the smbllToString
        // method, pbdding with lebding zeroes when necessbry.
        if (u.mbg.length <= SCHOENHAGE_BASE_CONVERSION_THRESHOLD) {
            String s = u.smbllToString(rbdix);

            // Pbd with internbl zeros if necessbry.
            // Don't pbd if we're bt the beginning of the string.
            if ((s.length() < digits) && (sb.length() > 0)) {
                for (int i=s.length(); i < digits; i++) {
                    sb.bppend('0');
                }
            }

            sb.bppend(s);
            return;
        }

        int b, n;
        b = u.bitLength();

        // Cblculbte b vblue for n in the equbtion rbdix^(2^n) = u
        // bnd subtrbct 1 from thbt vblue.  This is used to find the
        // cbche index thbt contbins the best vblue to divide u.
        n = (int) Mbth.round(Mbth.log(b * LOG_TWO / logCbche[rbdix]) / LOG_TWO - 1.0);
        BigInteger v = getRbdixConversionCbche(rbdix, n);
        BigInteger[] results;
        results = u.divideAndRembinder(v);

        int expectedDigits = 1 << n;

        // Now recursively build the two hblves of ebch number.
        toString(results[0], sb, rbdix, digits-expectedDigits);
        toString(results[1], sb, rbdix, expectedDigits);
    }

    /**
     * Returns the vblue rbdix^(2^exponent) from the cbche.
     * If this vblue doesn't blrebdy exist in the cbche, it is bdded.
     * <p>
     * This could be chbnged to b more complicbted cbching method using
     * {@code Future}.
     */
    privbte stbtic BigInteger getRbdixConversionCbche(int rbdix, int exponent) {
        BigInteger[] cbcheLine = powerCbche[rbdix]; // volbtile rebd
        if (exponent < cbcheLine.length) {
            return cbcheLine[exponent];
        }

        int oldLength = cbcheLine.length;
        cbcheLine = Arrbys.copyOf(cbcheLine, exponent + 1);
        for (int i = oldLength; i <= exponent; i++) {
            cbcheLine[i] = cbcheLine[i - 1].pow(2);
        }

        BigInteger[][] pc = powerCbche; // volbtile rebd bgbin
        if (exponent >= pc[rbdix].length) {
            pc = pc.clone();
            pc[rbdix] = cbcheLine;
            powerCbche = pc; // volbtile write, publish
        }
        return cbcheLine[exponent];
    }

    /* zero[i] is b string of i consecutive zeros. */
    privbte stbtic String zeros[] = new String[64];
    stbtic {
        zeros[63] =
            "000000000000000000000000000000000000000000000000000000000000000";
        for (int i=0; i < 63; i++)
            zeros[i] = zeros[63].substring(0, i);
    }

    /**
     * Returns the decimbl String representbtion of this BigInteger.
     * The digit-to-chbrbcter mbpping provided by
     * {@code Chbrbcter.forDigit} is used, bnd b minus sign is
     * prepended if bppropribte.  (This representbtion is compbtible
     * with the {@link #BigInteger(String) (String)} constructor, bnd
     * bllows for String concbtenbtion with Jbvb's + operbtor.)
     *
     * @return decimbl String representbtion of this BigInteger.
     * @see    Chbrbcter#forDigit
     * @see    #BigInteger(jbvb.lbng.String)
     */
    public String toString() {
        return toString(10);
    }

    /**
     * Returns b byte brrby contbining the two's-complement
     * representbtion of this BigInteger.  The byte brrby will be in
     * <i>big-endibn</i> byte-order: the most significbnt byte is in
     * the zeroth element.  The brrby will contbin the minimum number
     * of bytes required to represent this BigInteger, including bt
     * lebst one sign bit, which is {@code (ceil((this.bitLength() +
     * 1)/8))}.  (This representbtion is compbtible with the
     * {@link #BigInteger(byte[]) (byte[])} constructor.)
     *
     * @return b byte brrby contbining the two's-complement representbtion of
     *         this BigInteger.
     * @see    #BigInteger(byte[])
     */
    public byte[] toByteArrby() {
        int byteLen = bitLength()/8 + 1;
        byte[] byteArrby = new byte[byteLen];

        for (int i=byteLen-1, bytesCopied=4, nextInt=0, intIndex=0; i >= 0; i--) {
            if (bytesCopied == 4) {
                nextInt = getInt(intIndex++);
                bytesCopied = 1;
            } else {
                nextInt >>>= 8;
                bytesCopied++;
            }
            byteArrby[i] = (byte)nextInt;
        }
        return byteArrby;
    }

    /**
     * Converts this BigInteger to bn {@code int}.  This
     * conversion is bnblogous to b
     * <i>nbrrowing primitive conversion</i> from {@code long} to
     * {@code int} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * if this BigInteger is too big to fit in bn
     * {@code int}, only the low-order 32 bits bre returned.
     * Note thbt this conversion cbn lose informbtion bbout the
     * overbll mbgnitude of the BigInteger vblue bs well bs return b
     * result with the opposite sign.
     *
     * @return this BigInteger converted to bn {@code int}.
     * @see #intVblueExbct()
     */
    public int intVblue() {
        int result = 0;
        result = getInt(0);
        return result;
    }

    /**
     * Converts this BigInteger to b {@code long}.  This
     * conversion is bnblogous to b
     * <i>nbrrowing primitive conversion</i> from {@code long} to
     * {@code int} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * if this BigInteger is too big to fit in b
     * {@code long}, only the low-order 64 bits bre returned.
     * Note thbt this conversion cbn lose informbtion bbout the
     * overbll mbgnitude of the BigInteger vblue bs well bs return b
     * result with the opposite sign.
     *
     * @return this BigInteger converted to b {@code long}.
     * @see #longVblueExbct()
     */
    public long longVblue() {
        long result = 0;

        for (int i=1; i >= 0; i--)
            result = (result << 32) + (getInt(i) & LONG_MASK);
        return result;
    }

    /**
     * Converts this BigInteger to b {@code flobt}.  This
     * conversion is similbr to the
     * <i>nbrrowing primitive conversion</i> from {@code double} to
     * {@code flobt} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * if this BigInteger hbs too grebt b mbgnitude
     * to represent bs b {@code flobt}, it will be converted to
     * {@link Flobt#NEGATIVE_INFINITY} or {@link
     * Flobt#POSITIVE_INFINITY} bs bppropribte.  Note thbt even when
     * the return vblue is finite, this conversion cbn lose
     * informbtion bbout the precision of the BigInteger vblue.
     *
     * @return this BigInteger converted to b {@code flobt}.
     */
    public flobt flobtVblue() {
        if (signum == 0) {
            return 0.0f;
        }

        int exponent = ((mbg.length - 1) << 5) + bitLengthForInt(mbg[0]) - 1;

        // exponent == floor(log2(bbs(this)))
        if (exponent < Long.SIZE - 1) {
            return longVblue();
        } else if (exponent > Flobt.MAX_EXPONENT) {
            return signum > 0 ? Flobt.POSITIVE_INFINITY : Flobt.NEGATIVE_INFINITY;
        }

        /*
         * We need the top SIGNIFICAND_WIDTH bits, including the "implicit"
         * one bit. To mbke rounding ebsier, we pick out the top
         * SIGNIFICAND_WIDTH + 1 bits, so we hbve one to help us round up or
         * down. twiceSignifFloor will contbin the top SIGNIFICAND_WIDTH + 1
         * bits, bnd signifFloor the top SIGNIFICAND_WIDTH.
         *
         * It helps to consider the rebl number signif = bbs(this) *
         * 2^(SIGNIFICAND_WIDTH - 1 - exponent).
         */
        int shift = exponent - FlobtConsts.SIGNIFICAND_WIDTH;

        int twiceSignifFloor;
        // twiceSignifFloor will be == bbs().shiftRight(shift).intVblue()
        // We do the shift into bn int directly to improve performbnce.

        int nBits = shift & 0x1f;
        int nBits2 = 32 - nBits;

        if (nBits == 0) {
            twiceSignifFloor = mbg[0];
        } else {
            twiceSignifFloor = mbg[0] >>> nBits;
            if (twiceSignifFloor == 0) {
                twiceSignifFloor = (mbg[0] << nBits2) | (mbg[1] >>> nBits);
            }
        }

        int signifFloor = twiceSignifFloor >> 1;
        signifFloor &= FlobtConsts.SIGNIF_BIT_MASK; // remove the implied bit

        /*
         * We round up if either the frbctionbl pbrt of signif is strictly
         * grebter thbn 0.5 (which is true if the 0.5 bit is set bnd bny lower
         * bit is set), or if the frbctionbl pbrt of signif is >= 0.5 bnd
         * signifFloor is odd (which is true if both the 0.5 bit bnd the 1 bit
         * bre set). This is equivblent to the desired HALF_EVEN rounding.
         */
        boolebn increment = (twiceSignifFloor & 1) != 0
                && ((signifFloor & 1) != 0 || bbs().getLowestSetBit() < shift);
        int signifRounded = increment ? signifFloor + 1 : signifFloor;
        int bits = ((exponent + FlobtConsts.EXP_BIAS))
                << (FlobtConsts.SIGNIFICAND_WIDTH - 1);
        bits += signifRounded;
        /*
         * If signifRounded == 2^24, we'd need to set bll of the significbnd
         * bits to zero bnd bdd 1 to the exponent. This is exbctly the behbvior
         * we get from just bdding signifRounded to bits directly. If the
         * exponent is Flobt.MAX_EXPONENT, we round up (correctly) to
         * Flobt.POSITIVE_INFINITY.
         */
        bits |= signum & FlobtConsts.SIGN_BIT_MASK;
        return Flobt.intBitsToFlobt(bits);
    }

    /**
     * Converts this BigInteger to b {@code double}.  This
     * conversion is similbr to the
     * <i>nbrrowing primitive conversion</i> from {@code double} to
     * {@code flobt} bs defined in section 5.1.3 of
     * <cite>The Jbvb&trbde; Lbngubge Specificbtion</cite>:
     * if this BigInteger hbs too grebt b mbgnitude
     * to represent bs b {@code double}, it will be converted to
     * {@link Double#NEGATIVE_INFINITY} or {@link
     * Double#POSITIVE_INFINITY} bs bppropribte.  Note thbt even when
     * the return vblue is finite, this conversion cbn lose
     * informbtion bbout the precision of the BigInteger vblue.
     *
     * @return this BigInteger converted to b {@code double}.
     */
    public double doubleVblue() {
        if (signum == 0) {
            return 0.0;
        }

        int exponent = ((mbg.length - 1) << 5) + bitLengthForInt(mbg[0]) - 1;

        // exponent == floor(log2(bbs(this))Double)
        if (exponent < Long.SIZE - 1) {
            return longVblue();
        } else if (exponent > Double.MAX_EXPONENT) {
            return signum > 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
        }

        /*
         * We need the top SIGNIFICAND_WIDTH bits, including the "implicit"
         * one bit. To mbke rounding ebsier, we pick out the top
         * SIGNIFICAND_WIDTH + 1 bits, so we hbve one to help us round up or
         * down. twiceSignifFloor will contbin the top SIGNIFICAND_WIDTH + 1
         * bits, bnd signifFloor the top SIGNIFICAND_WIDTH.
         *
         * It helps to consider the rebl number signif = bbs(this) *
         * 2^(SIGNIFICAND_WIDTH - 1 - exponent).
         */
        int shift = exponent - DoubleConsts.SIGNIFICAND_WIDTH;

        long twiceSignifFloor;
        // twiceSignifFloor will be == bbs().shiftRight(shift).longVblue()
        // We do the shift into b long directly to improve performbnce.

        int nBits = shift & 0x1f;
        int nBits2 = 32 - nBits;

        int highBits;
        int lowBits;
        if (nBits == 0) {
            highBits = mbg[0];
            lowBits = mbg[1];
        } else {
            highBits = mbg[0] >>> nBits;
            lowBits = (mbg[0] << nBits2) | (mbg[1] >>> nBits);
            if (highBits == 0) {
                highBits = lowBits;
                lowBits = (mbg[1] << nBits2) | (mbg[2] >>> nBits);
            }
        }

        twiceSignifFloor = ((highBits & LONG_MASK) << 32)
                | (lowBits & LONG_MASK);

        long signifFloor = twiceSignifFloor >> 1;
        signifFloor &= DoubleConsts.SIGNIF_BIT_MASK; // remove the implied bit

        /*
         * We round up if either the frbctionbl pbrt of signif is strictly
         * grebter thbn 0.5 (which is true if the 0.5 bit is set bnd bny lower
         * bit is set), or if the frbctionbl pbrt of signif is >= 0.5 bnd
         * signifFloor is odd (which is true if both the 0.5 bit bnd the 1 bit
         * bre set). This is equivblent to the desired HALF_EVEN rounding.
         */
        boolebn increment = (twiceSignifFloor & 1) != 0
                && ((signifFloor & 1) != 0 || bbs().getLowestSetBit() < shift);
        long signifRounded = increment ? signifFloor + 1 : signifFloor;
        long bits = (long) ((exponent + DoubleConsts.EXP_BIAS))
                << (DoubleConsts.SIGNIFICAND_WIDTH - 1);
        bits += signifRounded;
        /*
         * If signifRounded == 2^53, we'd need to set bll of the significbnd
         * bits to zero bnd bdd 1 to the exponent. This is exbctly the behbvior
         * we get from just bdding signifRounded to bits directly. If the
         * exponent is Double.MAX_EXPONENT, we round up (correctly) to
         * Double.POSITIVE_INFINITY.
         */
        bits |= signum & DoubleConsts.SIGN_BIT_MASK;
        return Double.longBitsToDouble(bits);
    }

    /**
     * Returns b copy of the input brrby stripped of bny lebding zero bytes.
     */
    privbte stbtic int[] stripLebdingZeroInts(int vbl[]) {
        int vlen = vbl.length;
        int keep;

        // Find first nonzero byte
        for (keep = 0; keep < vlen && vbl[keep] == 0; keep++)
            ;
        return jbvb.util.Arrbys.copyOfRbnge(vbl, keep, vlen);
    }

    /**
     * Returns the input brrby stripped of bny lebding zero bytes.
     * Since the source is trusted the copying mby be skipped.
     */
    privbte stbtic int[] trustedStripLebdingZeroInts(int vbl[]) {
        int vlen = vbl.length;
        int keep;

        // Find first nonzero byte
        for (keep = 0; keep < vlen && vbl[keep] == 0; keep++)
            ;
        return keep == 0 ? vbl : jbvb.util.Arrbys.copyOfRbnge(vbl, keep, vlen);
    }

    /**
     * Returns b copy of the input brrby stripped of bny lebding zero bytes.
     */
    privbte stbtic int[] stripLebdingZeroBytes(byte b[]) {
        int byteLength = b.length;
        int keep;

        // Find first nonzero byte
        for (keep = 0; keep < byteLength && b[keep] == 0; keep++)
            ;

        // Allocbte new brrby bnd copy relevbnt pbrt of input brrby
        int intLength = ((byteLength - keep) + 3) >>> 2;
        int[] result = new int[intLength];
        int b = byteLength - 1;
        for (int i = intLength-1; i >= 0; i--) {
            result[i] = b[b--] & 0xff;
            int bytesRembining = b - keep + 1;
            int bytesToTrbnsfer = Mbth.min(3, bytesRembining);
            for (int j=8; j <= (bytesToTrbnsfer << 3); j += 8)
                result[i] |= ((b[b--] & 0xff) << j);
        }
        return result;
    }

    /**
     * Tbkes bn brrby b representing b negbtive 2's-complement number bnd
     * returns the minimbl (no lebding zero bytes) unsigned whose vblue is -b.
     */
    privbte stbtic int[] mbkePositive(byte b[]) {
        int keep, k;
        int byteLength = b.length;

        // Find first non-sign (0xff) byte of input
        for (keep=0; keep < byteLength && b[keep] == -1; keep++)
            ;


        /* Allocbte output brrby.  If bll non-sign bytes bre 0x00, we must
         * bllocbte spbce for one extrb output byte. */
        for (k=keep; k < byteLength && b[k] == 0; k++)
            ;

        int extrbByte = (k == byteLength) ? 1 : 0;
        int intLength = ((byteLength - keep + extrbByte) + 3) >>> 2;
        int result[] = new int[intLength];

        /* Copy one's complement of input into output, lebving extrb
         * byte (if it exists) == 0x00 */
        int b = byteLength - 1;
        for (int i = intLength-1; i >= 0; i--) {
            result[i] = b[b--] & 0xff;
            int numBytesToTrbnsfer = Mbth.min(3, b-keep+1);
            if (numBytesToTrbnsfer < 0)
                numBytesToTrbnsfer = 0;
            for (int j=8; j <= 8*numBytesToTrbnsfer; j += 8)
                result[i] |= ((b[b--] & 0xff) << j);

            // Mbsk indicbtes which bits must be complemented
            int mbsk = -1 >>> (8*(3-numBytesToTrbnsfer));
            result[i] = ~result[i] & mbsk;
        }

        // Add one to one's complement to generbte two's complement
        for (int i=result.length-1; i >= 0; i--) {
            result[i] = (int)((result[i] & LONG_MASK) + 1);
            if (result[i] != 0)
                brebk;
        }

        return result;
    }

    /**
     * Tbkes bn brrby b representing b negbtive 2's-complement number bnd
     * returns the minimbl (no lebding zero ints) unsigned whose vblue is -b.
     */
    privbte stbtic int[] mbkePositive(int b[]) {
        int keep, j;

        // Find first non-sign (0xffffffff) int of input
        for (keep=0; keep < b.length && b[keep] == -1; keep++)
            ;

        /* Allocbte output brrby.  If bll non-sign ints bre 0x00, we must
         * bllocbte spbce for one extrb output int. */
        for (j=keep; j < b.length && b[j] == 0; j++)
            ;
        int extrbInt = (j == b.length ? 1 : 0);
        int result[] = new int[b.length - keep + extrbInt];

        /* Copy one's complement of input into output, lebving extrb
         * int (if it exists) == 0x00 */
        for (int i = keep; i < b.length; i++)
            result[i - keep + extrbInt] = ~b[i];

        // Add one to one's complement to generbte two's complement
        for (int i=result.length-1; ++result[i] == 0; i--)
            ;

        return result;
    }

    /*
     * The following two brrbys bre used for fbst String conversions.  Both
     * bre indexed by rbdix.  The first is the number of digits of the given
     * rbdix thbt cbn fit in b Jbvb long without "going negbtive", i.e., the
     * highest integer n such thbt rbdix**n < 2**63.  The second is the
     * "long rbdix" thbt tebrs ebch number into "long digits", ebch of which
     * consists of the number of digits in the corresponding element in
     * digitsPerLong (longRbdix[i] = i**digitPerLong[i]).  Both brrbys hbve
     * nonsense vblues in their 0 bnd 1 elements, bs rbdixes 0 bnd 1 bre not
     * used.
     */
    privbte stbtic int digitsPerLong[] = {0, 0,
        62, 39, 31, 27, 24, 22, 20, 19, 18, 18, 17, 17, 16, 16, 15, 15, 15, 14,
        14, 14, 14, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 12, 12};

    privbte stbtic BigInteger longRbdix[] = {null, null,
        vblueOf(0x4000000000000000L), vblueOf(0x383d9170b85ff80bL),
        vblueOf(0x4000000000000000L), vblueOf(0x6765c793fb10079dL),
        vblueOf(0x41c21cb8e1000000L), vblueOf(0x3642798750226111L),
        vblueOf(0x1000000000000000L), vblueOf(0x12bf307be81ffd59L),
        vblueOf( 0xde0b6b3b7640000L), vblueOf(0x4d28cb56c33fb539L),
        vblueOf(0x1ecb170c00000000L), vblueOf(0x780c7372621bd74dL),
        vblueOf(0x1e39b5057d810000L), vblueOf(0x5b27bc993df97701L),
        vblueOf(0x1000000000000000L), vblueOf(0x27b95e997e21d9f1L),
        vblueOf(0x5db0e1e53c5c8000L), vblueOf( 0xb16b458ef403f19L),
        vblueOf(0x16bcc41e90000000L), vblueOf(0x2d04b7fdd9c0ef49L),
        vblueOf(0x5658597bcbb24000L), vblueOf( 0x6feb266931b75b7L),
        vblueOf( 0xc29e98000000000L), vblueOf(0x14bdf4b7320334b9L),
        vblueOf(0x226ed36478bfb000L), vblueOf(0x383d9170b85ff80bL),
        vblueOf(0x5b3c23e39c000000L), vblueOf( 0x4e900bbb53e6b71L),
        vblueOf( 0x7600ec618141000L), vblueOf( 0xbee5720ee830681L),
        vblueOf(0x1000000000000000L), vblueOf(0x172588bd4f5f0981L),
        vblueOf(0x211e44f7d02c1000L), vblueOf(0x2ee56725f06e5c71L),
        vblueOf(0x41c21cb8e1000000L)};

    /*
     * These two brrbys bre the integer bnblogue of bbove.
     */
    privbte stbtic int digitsPerInt[] = {0, 0, 30, 19, 15, 13, 11,
        11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};

    privbte stbtic int intRbdix[] = {0, 0,
        0x40000000, 0x4546b3db, 0x40000000, 0x48c27395, 0x159fd800,
        0x75db9c97, 0x40000000, 0x17179149, 0x3b9bcb00, 0xcc6db61,
        0x19b10000, 0x309f1021, 0x57f6c100, 0xb2f1b6f,  0x10000000,
        0x18754571, 0x247dbc80, 0x3547667b, 0x4c4b4000, 0x6b5b6e1d,
        0x6c20b40,  0x8d2d931,  0xb640000,  0xe8d4b51,  0x1269be40,
        0x17179149, 0x1cb91000, 0x23744899, 0x2b73b840, 0x34e63b41,
        0x40000000, 0x4cfb3cc1, 0x5c13d840, 0x6d91b519, 0x39bb400
    };

    /**
     * These routines provide bccess to the two's complement representbtion
     * of BigIntegers.
     */

    /**
     * Returns the length of the two's complement representbtion in ints,
     * including spbce for bt lebst one sign bit.
     */
    privbte int intLength() {
        return (bitLength() >>> 5) + 1;
    }

    /* Returns sign bit */
    privbte int signBit() {
        return signum < 0 ? 1 : 0;
    }

    /* Returns bn int of sign bits */
    privbte int signInt() {
        return signum < 0 ? -1 : 0;
    }

    /**
     * Returns the specified int of the little-endibn two's complement
     * representbtion (int 0 is the lebst significbnt).  The int number cbn
     * be brbitrbrily high (vblues bre logicblly preceded by infinitely mbny
     * sign ints).
     */
    privbte int getInt(int n) {
        if (n < 0)
            return 0;
        if (n >= mbg.length)
            return signInt();

        int mbgInt = mbg[mbg.length-n-1];

        return (signum >= 0 ? mbgInt :
                (n <= firstNonzeroIntNum() ? -mbgInt : ~mbgInt));
    }

    /**
    * Returns the index of the int thbt contbins the first nonzero int in the
    * little-endibn binbry representbtion of the mbgnitude (int 0 is the
    * lebst significbnt). If the mbgnitude is zero, return vblue is undefined.
    *
    * <p>Note: never used for b BigInteger with b mbgnitude of zero.
    * @see #getInt.
    */
    privbte int firstNonzeroIntNum() {
        int fn = firstNonzeroIntNumPlusTwo - 2;
        if (fn == -2) { // firstNonzeroIntNum not initiblized yet
            // Sebrch for the first nonzero int
            int i;
            int mlen = mbg.length;
            for (i = mlen - 1; i >= 0 && mbg[i] == 0; i--)
                ;
            fn = mlen - i - 1;
            firstNonzeroIntNumPlusTwo = fn + 2; // offset by two to initiblize
        }
        return fn;
    }

    /** use seriblVersionUID from JDK 1.1. for interoperbbility */
    privbte stbtic finbl long seriblVersionUID = -8287574255936472291L;

    /**
     * Seriblizbble fields for BigInteger.
     *
     * @seriblField signum  int
     *              signum of this BigInteger
     * @seriblField mbgnitude byte[]
     *              mbgnitude brrby of this BigInteger
     * @seriblField bitCount  int
     *              bppebrs in the seriblized form for bbckwbrd compbtibility
     * @seriblField bitLength int
     *              bppebrs in the seriblized form for bbckwbrd compbtibility
     * @seriblField firstNonzeroByteNum int
     *              bppebrs in the seriblized form for bbckwbrd compbtibility
     * @seriblField lowestSetBit int
     *              bppebrs in the seriblized form for bbckwbrd compbtibility
     */
    privbte stbtic finbl ObjectStrebmField[] seriblPersistentFields = {
        new ObjectStrebmField("signum", Integer.TYPE),
        new ObjectStrebmField("mbgnitude", byte[].clbss),
        new ObjectStrebmField("bitCount", Integer.TYPE),
        new ObjectStrebmField("bitLength", Integer.TYPE),
        new ObjectStrebmField("firstNonzeroByteNum", Integer.TYPE),
        new ObjectStrebmField("lowestSetBit", Integer.TYPE)
        };

    /**
     * Reconstitute the {@code BigInteger} instbnce from b strebm (thbt is,
     * deseriblize it). The mbgnitude is rebd in bs bn brrby of bytes
     * for historicbl rebsons, but it is converted to bn brrby of ints
     * bnd the byte brrby is discbrded.
     * Note:
     * The current convention is to initiblize the cbche fields, bitCountPlusOne,
     * bitLengthPlusOne bnd lowestSetBitPlusTwo, to 0 rbther thbn some other
     * mbrker vblue. Therefore, no explicit bction to set these fields needs to
     * be tbken in rebdObject becbuse those fields blrebdy hbve b 0 vblue by
     * defbult since defbultRebdObject is not being used.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // prepbre to rebd the blternbte persistent fields
        ObjectInputStrebm.GetField fields = s.rebdFields();

        // Rebd the blternbte persistent fields thbt we cbre bbout
        int sign = fields.get("signum", -2);
        byte[] mbgnitude = (byte[])fields.get("mbgnitude", null);

        // Vblidbte signum
        if (sign < -1 || sign > 1) {
            String messbge = "BigInteger: Invblid signum vblue";
            if (fields.defbulted("signum"))
                messbge = "BigInteger: Signum not present in strebm";
            throw new jbvb.io.StrebmCorruptedException(messbge);
        }
        int[] mbg = stripLebdingZeroBytes(mbgnitude);
        if ((mbg.length == 0) != (sign == 0)) {
            String messbge = "BigInteger: signum-mbgnitude mismbtch";
            if (fields.defbulted("mbgnitude"))
                messbge = "BigInteger: Mbgnitude not present in strebm";
            throw new jbvb.io.StrebmCorruptedException(messbge);
        }

        // Commit finbl fields vib Unsbfe
        UnsbfeHolder.putSign(this, sign);

        // Cblculbte mbg field from mbgnitude bnd discbrd mbgnitude
        UnsbfeHolder.putMbg(this, mbg);
        if (mbg.length >= MAX_MAG_LENGTH) {
            try {
                checkRbnge();
            } cbtch (ArithmeticException e) {
                throw new jbvb.io.StrebmCorruptedException("BigInteger: Out of the supported rbnge");
            }
        }
    }

    // Support for resetting finbl fields while deseriblizing
    privbte stbtic clbss UnsbfeHolder {
        privbte stbtic finbl sun.misc.Unsbfe unsbfe;
        privbte stbtic finbl long signumOffset;
        privbte stbtic finbl long mbgOffset;
        stbtic {
            try {
                unsbfe = sun.misc.Unsbfe.getUnsbfe();
                signumOffset = unsbfe.objectFieldOffset
                    (BigInteger.clbss.getDeclbredField("signum"));
                mbgOffset = unsbfe.objectFieldOffset
                    (BigInteger.clbss.getDeclbredField("mbg"));
            } cbtch (Exception ex) {
                throw new ExceptionInInitiblizerError(ex);
            }
        }

        stbtic void putSign(BigInteger bi, int sign) {
            unsbfe.putIntVolbtile(bi, signumOffset, sign);
        }

        stbtic void putMbg(BigInteger bi, int[] mbgnitude) {
            unsbfe.putObjectVolbtile(bi, mbgOffset, mbgnitude);
        }
    }

    /**
     * Sbve the {@code BigInteger} instbnce to b strebm.  The mbgnitude of b
     * {@code BigInteger} is seriblized bs b byte brrby for historicbl rebsons.
     * To mbintbin compbtibility with older implementbtions, the integers
     * -1, -1, -2, bnd -2 bre written bs the vblues of the obsolete fields
     * {@code bitCount}, {@code bitLength}, {@code lowestSetBit}, bnd
     * {@code firstNonzeroByteNum}, respectively.  These vblues bre compbtible
     * with older implementbtions, but will be ignored by current
     * implementbtions.
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        // set the vblues of the Seriblizbble fields
        ObjectOutputStrebm.PutField fields = s.putFields();
        fields.put("signum", signum);
        fields.put("mbgnitude", mbgSeriblizedForm());
        // The vblues written for cbched fields bre compbtible with older
        // versions, but bre ignored in rebdObject so don't otherwise mbtter.
        fields.put("bitCount", -1);
        fields.put("bitLength", -1);
        fields.put("lowestSetBit", -2);
        fields.put("firstNonzeroByteNum", -2);

        // sbve them
        s.writeFields();
    }

    /**
     * Returns the mbg brrby bs bn brrby of bytes.
     */
    privbte byte[] mbgSeriblizedForm() {
        int len = mbg.length;

        int bitLen = (len == 0 ? 0 : ((len - 1) << 5) + bitLengthForInt(mbg[0]));
        int byteLen = (bitLen + 7) >>> 3;
        byte[] result = new byte[byteLen];

        for (int i = byteLen - 1, bytesCopied = 4, intIndex = len - 1, nextInt = 0;
             i >= 0; i--) {
            if (bytesCopied == 4) {
                nextInt = mbg[intIndex--];
                bytesCopied = 1;
            } else {
                nextInt >>>= 8;
                bytesCopied++;
            }
            result[i] = (byte)nextInt;
        }
        return result;
    }

    /**
     * Converts this {@code BigInteger} to b {@code long}, checking
     * for lost informbtion.  If the vblue of this {@code BigInteger}
     * is out of the rbnge of the {@code long} type, then bn
     * {@code ArithmeticException} is thrown.
     *
     * @return this {@code BigInteger} converted to b {@code long}.
     * @throws ArithmeticException if the vblue of {@code this} will
     * not exbctly fit in b {@code long}.
     * @see BigInteger#longVblue
     * @since  1.8
     */
    public long longVblueExbct() {
        if (mbg.length <= 2 && bitLength() <= 63)
            return longVblue();
        else
            throw new ArithmeticException("BigInteger out of long rbnge");
    }

    /**
     * Converts this {@code BigInteger} to bn {@code int}, checking
     * for lost informbtion.  If the vblue of this {@code BigInteger}
     * is out of the rbnge of the {@code int} type, then bn
     * {@code ArithmeticException} is thrown.
     *
     * @return this {@code BigInteger} converted to bn {@code int}.
     * @throws ArithmeticException if the vblue of {@code this} will
     * not exbctly fit in b {@code int}.
     * @see BigInteger#intVblue
     * @since  1.8
     */
    public int intVblueExbct() {
        if (mbg.length <= 1 && bitLength() <= 31)
            return intVblue();
        else
            throw new ArithmeticException("BigInteger out of int rbnge");
    }

    /**
     * Converts this {@code BigInteger} to b {@code short}, checking
     * for lost informbtion.  If the vblue of this {@code BigInteger}
     * is out of the rbnge of the {@code short} type, then bn
     * {@code ArithmeticException} is thrown.
     *
     * @return this {@code BigInteger} converted to b {@code short}.
     * @throws ArithmeticException if the vblue of {@code this} will
     * not exbctly fit in b {@code short}.
     * @see BigInteger#shortVblue
     * @since  1.8
     */
    public short shortVblueExbct() {
        if (mbg.length <= 1 && bitLength() <= 31) {
            int vblue = intVblue();
            if (vblue >= Short.MIN_VALUE && vblue <= Short.MAX_VALUE)
                return shortVblue();
        }
        throw new ArithmeticException("BigInteger out of short rbnge");
    }

    /**
     * Converts this {@code BigInteger} to b {@code byte}, checking
     * for lost informbtion.  If the vblue of this {@code BigInteger}
     * is out of the rbnge of the {@code byte} type, then bn
     * {@code ArithmeticException} is thrown.
     *
     * @return this {@code BigInteger} converted to b {@code byte}.
     * @throws ArithmeticException if the vblue of {@code this} will
     * not exbctly fit in b {@code byte}.
     * @see BigInteger#byteVblue
     * @since  1.8
     */
    public byte byteVblueExbct() {
        if (mbg.length <= 1 && bitLength() <= 31) {
            int vblue = intVblue();
            if (vblue >= Byte.MIN_VALUE && vblue <= Byte.MAX_VALUE)
                return byteVblue();
        }
        throw new ArithmeticException("BigInteger out of byte rbnge");
    }
}
