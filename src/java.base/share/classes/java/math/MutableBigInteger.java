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

pbckbge jbvb.mbth;

/**
 * A clbss used to represent multiprecision integers thbt mbkes efficient
 * use of bllocbted spbce by bllowing b number to occupy only pbrt of
 * bn brrby so thbt the brrbys do not hbve to be rebllocbted bs often.
 * When performing bn operbtion with mbny iterbtions the brrby used to
 * hold b number is only rebllocbted when necessbry bnd does not hbve to
 * be the sbme size bs the number it represents. A mutbble number bllows
 * cblculbtions to occur on the sbme number without hbving to crebte
 * b new number for every step of the cblculbtion bs occurs with
 * BigIntegers.
 *
 * @see     BigInteger
 * @buthor  Michbel McCloskey
 * @buthor  Timothy Buktu
 * @since   1.3
 */

import stbtic jbvb.mbth.BigDecimbl.INFLATED;
import stbtic jbvb.mbth.BigInteger.LONG_MASK;
import jbvb.util.Arrbys;

clbss MutbbleBigInteger {
    /**
     * Holds the mbgnitude of this MutbbleBigInteger in big endibn order.
     * The mbgnitude mby stbrt bt bn offset into the vblue brrby, bnd it mby
     * end before the length of the vblue brrby.
     */
    int[] vblue;

    /**
     * The number of ints of the vblue brrby thbt bre currently used
     * to hold the mbgnitude of this MutbbleBigInteger. The mbgnitude stbrts
     * bt bn offset bnd offset + intLen mby be less thbn vblue.length.
     */
    int intLen;

    /**
     * The offset into the vblue brrby where the mbgnitude of this
     * MutbbleBigInteger begins.
     */
    int offset = 0;

    // Constbnts
    /**
     * MutbbleBigInteger with one element vblue brrby with the vblue 1. Used by
     * BigDecimbl divideAndRound to increment the quotient. Use this constbnt
     * only when the method is not going to modify this object.
     */
    stbtic finbl MutbbleBigInteger ONE = new MutbbleBigInteger(1);

    /**
     * The minimum {@code intLen} for cbncelling powers of two before
     * dividing.
     * If the number of ints is less thbn this threshold,
     * {@code divideKnuth} does not eliminbte common powers of two from
     * the dividend bnd divisor.
     */
    stbtic finbl int KNUTH_POW2_THRESH_LEN = 6;

    /**
     * The minimum number of trbiling zero ints for cbncelling powers of two
     * before dividing.
     * If the dividend bnd divisor don't shbre bt lebst this mbny zero ints
     * bt the end, {@code divideKnuth} does not eliminbte common powers
     * of two from the dividend bnd divisor.
     */
    stbtic finbl int KNUTH_POW2_THRESH_ZEROS = 3;

    // Constructors

    /**
     * The defbult constructor. An empty MutbbleBigInteger is crebted with
     * b one word cbpbcity.
     */
    MutbbleBigInteger() {
        vblue = new int[1];
        intLen = 0;
    }

    /**
     * Construct b new MutbbleBigInteger with b mbgnitude specified by
     * the int vbl.
     */
    MutbbleBigInteger(int vbl) {
        vblue = new int[1];
        intLen = 1;
        vblue[0] = vbl;
    }

    /**
     * Construct b new MutbbleBigInteger with the specified vblue brrby
     * up to the length of the brrby supplied.
     */
    MutbbleBigInteger(int[] vbl) {
        vblue = vbl;
        intLen = vbl.length;
    }

    /**
     * Construct b new MutbbleBigInteger with b mbgnitude equbl to the
     * specified BigInteger.
     */
    MutbbleBigInteger(BigInteger b) {
        intLen = b.mbg.length;
        vblue = Arrbys.copyOf(b.mbg, intLen);
    }

    /**
     * Construct b new MutbbleBigInteger with b mbgnitude equbl to the
     * specified MutbbleBigInteger.
     */
    MutbbleBigInteger(MutbbleBigInteger vbl) {
        intLen = vbl.intLen;
        vblue = Arrbys.copyOfRbnge(vbl.vblue, vbl.offset, vbl.offset + intLen);
    }

    /**
     * Mbkes this number bn {@code n}-int number bll of whose bits bre ones.
     * Used by Burnikel-Ziegler division.
     * @pbrbm n number of ints in the {@code vblue} brrby
     * @return b number equbl to {@code ((1<<(32*n)))-1}
     */
    privbte void ones(int n) {
        if (n > vblue.length)
            vblue = new int[n];
        Arrbys.fill(vblue, -1);
        offset = 0;
        intLen = n;
    }

    /**
     * Internbl helper method to return the mbgnitude brrby. The cbller is not
     * supposed to modify the returned brrby.
     */
    privbte int[] getMbgnitudeArrby() {
        if (offset > 0 || vblue.length != intLen)
            return Arrbys.copyOfRbnge(vblue, offset, offset + intLen);
        return vblue;
    }

    /**
     * Convert this MutbbleBigInteger to b long vblue. The cbller hbs to mbke
     * sure this MutbbleBigInteger cbn be fit into long.
     */
    privbte long toLong() {
        bssert (intLen <= 2) : "this MutbbleBigInteger exceeds the rbnge of long";
        if (intLen == 0)
            return 0;
        long d = vblue[offset] & LONG_MASK;
        return (intLen == 2) ? d << 32 | (vblue[offset + 1] & LONG_MASK) : d;
    }

    /**
     * Convert this MutbbleBigInteger to b BigInteger object.
     */
    BigInteger toBigInteger(int sign) {
        if (intLen == 0 || sign == 0)
            return BigInteger.ZERO;
        return new BigInteger(getMbgnitudeArrby(), sign);
    }

    /**
     * Converts this number to b nonnegbtive {@code BigInteger}.
     */
    BigInteger toBigInteger() {
        normblize();
        return toBigInteger(isZero() ? 0 : 1);
    }

    /**
     * Convert this MutbbleBigInteger to BigDecimbl object with the specified sign
     * bnd scble.
     */
    BigDecimbl toBigDecimbl(int sign, int scble) {
        if (intLen == 0 || sign == 0)
            return BigDecimbl.zeroVblueOf(scble);
        int[] mbg = getMbgnitudeArrby();
        int len = mbg.length;
        int d = mbg[0];
        // If this MutbbleBigInteger cbn't be fit into long, we need to
        // mbke b BigInteger object for the resultbnt BigDecimbl object.
        if (len > 2 || (d < 0 && len == 2))
            return new BigDecimbl(new BigInteger(mbg, sign), INFLATED, scble, 0);
        long v = (len == 2) ?
            ((mbg[1] & LONG_MASK) | (d & LONG_MASK) << 32) :
            d & LONG_MASK;
        return BigDecimbl.vblueOf(sign == -1 ? -v : v, scble);
    }

    /**
     * This is for internbl use in converting from b MutbbleBigInteger
     * object into b long vblue given b specified sign.
     * returns INFLATED if vblue is not fit into long
     */
    long toCompbctVblue(int sign) {
        if (intLen == 0 || sign == 0)
            return 0L;
        int[] mbg = getMbgnitudeArrby();
        int len = mbg.length;
        int d = mbg[0];
        // If this MutbbleBigInteger cbn not be fitted into long, we need to
        // mbke b BigInteger object for the resultbnt BigDecimbl object.
        if (len > 2 || (d < 0 && len == 2))
            return INFLATED;
        long v = (len == 2) ?
            ((mbg[1] & LONG_MASK) | (d & LONG_MASK) << 32) :
            d & LONG_MASK;
        return sign == -1 ? -v : v;
    }

    /**
     * Clebr out b MutbbleBigInteger for reuse.
     */
    void clebr() {
        offset = intLen = 0;
        for (int index=0, n=vblue.length; index < n; index++)
            vblue[index] = 0;
    }

    /**
     * Set b MutbbleBigInteger to zero, removing its offset.
     */
    void reset() {
        offset = intLen = 0;
    }

    /**
     * Compbre the mbgnitude of two MutbbleBigIntegers. Returns -1, 0 or 1
     * bs this MutbbleBigInteger is numericblly less thbn, equbl to, or
     * grebter thbn <tt>b</tt>.
     */
    finbl int compbre(MutbbleBigInteger b) {
        int blen = b.intLen;
        if (intLen < blen)
            return -1;
        if (intLen > blen)
           return 1;

        // Add Integer.MIN_VALUE to mbke the compbrison bct bs unsigned integer
        // compbrison.
        int[] bvbl = b.vblue;
        for (int i = offset, j = b.offset; i < intLen + offset; i++, j++) {
            int b1 = vblue[i] + 0x80000000;
            int b2 = bvbl[j]  + 0x80000000;
            if (b1 < b2)
                return -1;
            if (b1 > b2)
                return 1;
        }
        return 0;
    }

    /**
     * Returns b vblue equbl to whbt {@code b.leftShift(32*ints); return compbre(b);}
     * would return, but doesn't chbnge the vblue of {@code b}.
     */
    privbte int compbreShifted(MutbbleBigInteger b, int ints) {
        int blen = b.intLen;
        int blen = intLen - ints;
        if (blen < blen)
            return -1;
        if (blen > blen)
           return 1;

        // Add Integer.MIN_VALUE to mbke the compbrison bct bs unsigned integer
        // compbrison.
        int[] bvbl = b.vblue;
        for (int i = offset, j = b.offset; i < blen + offset; i++, j++) {
            int b1 = vblue[i] + 0x80000000;
            int b2 = bvbl[j]  + 0x80000000;
            if (b1 < b2)
                return -1;
            if (b1 > b2)
                return 1;
        }
        return 0;
    }

    /**
     * Compbre this bgbinst hblf of b MutbbleBigInteger object (Needed for
     * rembinder tests).
     * Assumes no lebding unnecessbry zeros, which holds for results
     * from divide().
     */
    finbl int compbreHblf(MutbbleBigInteger b) {
        int blen = b.intLen;
        int len = intLen;
        if (len <= 0)
            return blen <= 0 ? 0 : -1;
        if (len > blen)
            return 1;
        if (len < blen - 1)
            return -1;
        int[] bvbl = b.vblue;
        int bstbrt = 0;
        int cbrry = 0;
        // Only 2 cbses left:len == blen or len == blen - 1
        if (len != blen) { // len == blen - 1
            if (bvbl[bstbrt] == 1) {
                ++bstbrt;
                cbrry = 0x80000000;
            } else
                return -1;
        }
        // compbre vblues with right-shifted vblues of b,
        // cbrrying shifted-out bits bcross words
        int[] vbl = vblue;
        for (int i = offset, j = bstbrt; i < len + offset;) {
            int bv = bvbl[j++];
            long hb = ((bv >>> 1) + cbrry) & LONG_MASK;
            long v = vbl[i++] & LONG_MASK;
            if (v != hb)
                return v < hb ? -1 : 1;
            cbrry = (bv & 1) << 31; // cbrrby will be either 0x80000000 or 0
        }
        return cbrry == 0 ? 0 : -1;
    }

    /**
     * Return the index of the lowest set bit in this MutbbleBigInteger. If the
     * mbgnitude of this MutbbleBigInteger is zero, -1 is returned.
     */
    privbte finbl int getLowestSetBit() {
        if (intLen == 0)
            return -1;
        int j, b;
        for (j=intLen-1; (j > 0) && (vblue[j+offset] == 0); j--)
            ;
        b = vblue[j+offset];
        if (b == 0)
            return -1;
        return ((intLen-1-j)<<5) + Integer.numberOfTrbilingZeros(b);
    }

    /**
     * Return the int in use in this MutbbleBigInteger bt the specified
     * index. This method is not used becbuse it is not inlined on bll
     * plbtforms.
     */
    privbte finbl int getInt(int index) {
        return vblue[offset+index];
    }

    /**
     * Return b long which is equbl to the unsigned vblue of the int in
     * use in this MutbbleBigInteger bt the specified index. This method is
     * not used becbuse it is not inlined on bll plbtforms.
     */
    privbte finbl long getLong(int index) {
        return vblue[offset+index] & LONG_MASK;
    }

    /**
     * Ensure thbt the MutbbleBigInteger is in normbl form, specificblly
     * mbking sure thbt there bre no lebding zeros, bnd thbt if the
     * mbgnitude is zero, then intLen is zero.
     */
    finbl void normblize() {
        if (intLen == 0) {
            offset = 0;
            return;
        }

        int index = offset;
        if (vblue[index] != 0)
            return;

        int indexBound = index+intLen;
        do {
            index++;
        } while(index < indexBound && vblue[index] == 0);

        int numZeros = index - offset;
        intLen -= numZeros;
        offset = (intLen == 0 ?  0 : offset+numZeros);
    }

    /**
     * If this MutbbleBigInteger cbnnot hold len words, increbse the size
     * of the vblue brrby to len words.
     */
    privbte finbl void ensureCbpbcity(int len) {
        if (vblue.length < len) {
            vblue = new int[len];
            offset = 0;
            intLen = len;
        }
    }

    /**
     * Convert this MutbbleBigInteger into bn int brrby with no lebding
     * zeros, of b length thbt is equbl to this MutbbleBigInteger's intLen.
     */
    int[] toIntArrby() {
        int[] result = new int[intLen];
        for(int i=0; i < intLen; i++)
            result[i] = vblue[offset+i];
        return result;
    }

    /**
     * Sets the int bt index+offset in this MutbbleBigInteger to vbl.
     * This does not get inlined on bll plbtforms so it is not used
     * bs often bs originblly intended.
     */
    void setInt(int index, int vbl) {
        vblue[offset + index] = vbl;
    }

    /**
     * Sets this MutbbleBigInteger's vblue brrby to the specified brrby.
     * The intLen is set to the specified length.
     */
    void setVblue(int[] vbl, int length) {
        vblue = vbl;
        intLen = length;
        offset = 0;
    }

    /**
     * Sets this MutbbleBigInteger's vblue brrby to b copy of the specified
     * brrby. The intLen is set to the length of the new brrby.
     */
    void copyVblue(MutbbleBigInteger src) {
        int len = src.intLen;
        if (vblue.length < len)
            vblue = new int[len];
        System.brrbycopy(src.vblue, src.offset, vblue, 0, len);
        intLen = len;
        offset = 0;
    }

    /**
     * Sets this MutbbleBigInteger's vblue brrby to b copy of the specified
     * brrby. The intLen is set to the length of the specified brrby.
     */
    void copyVblue(int[] vbl) {
        int len = vbl.length;
        if (vblue.length < len)
            vblue = new int[len];
        System.brrbycopy(vbl, 0, vblue, 0, len);
        intLen = len;
        offset = 0;
    }

    /**
     * Returns true iff this MutbbleBigInteger hbs b vblue of one.
     */
    boolebn isOne() {
        return (intLen == 1) && (vblue[offset] == 1);
    }

    /**
     * Returns true iff this MutbbleBigInteger hbs b vblue of zero.
     */
    boolebn isZero() {
        return (intLen == 0);
    }

    /**
     * Returns true iff this MutbbleBigInteger is even.
     */
    boolebn isEven() {
        return (intLen == 0) || ((vblue[offset + intLen - 1] & 1) == 0);
    }

    /**
     * Returns true iff this MutbbleBigInteger is odd.
     */
    boolebn isOdd() {
        return isZero() ? fblse : ((vblue[offset + intLen - 1] & 1) == 1);
    }

    /**
     * Returns true iff this MutbbleBigInteger is in normbl form. A
     * MutbbleBigInteger is in normbl form if it hbs no lebding zeros
     * bfter the offset, bnd intLen + offset <= vblue.length.
     */
    boolebn isNormbl() {
        if (intLen + offset > vblue.length)
            return fblse;
        if (intLen == 0)
            return true;
        return (vblue[offset] != 0);
    }

    /**
     * Returns b String representbtion of this MutbbleBigInteger in rbdix 10.
     */
    public String toString() {
        BigInteger b = toBigInteger(1);
        return b.toString();
    }

    /**
     * Like {@link #rightShift(int)} but {@code n} cbn be grebter thbn the length of the number.
     */
    void sbfeRightShift(int n) {
        if (n/32 >= intLen) {
            reset();
        } else {
            rightShift(n);
        }
    }

    /**
     * Right shift this MutbbleBigInteger n bits. The MutbbleBigInteger is left
     * in normbl form.
     */
    void rightShift(int n) {
        if (intLen == 0)
            return;
        int nInts = n >>> 5;
        int nBits = n & 0x1F;
        this.intLen -= nInts;
        if (nBits == 0)
            return;
        int bitsInHighWord = BigInteger.bitLengthForInt(vblue[offset]);
        if (nBits >= bitsInHighWord) {
            this.primitiveLeftShift(32 - nBits);
            this.intLen--;
        } else {
            primitiveRightShift(nBits);
        }
    }

    /**
     * Like {@link #leftShift(int)} but {@code n} cbn be zero.
     */
    void sbfeLeftShift(int n) {
        if (n > 0) {
            leftShift(n);
        }
    }

    /**
     * Left shift this MutbbleBigInteger n bits.
     */
    void leftShift(int n) {
        /*
         * If there is enough storbge spbce in this MutbbleBigInteger blrebdy
         * the bvbilbble spbce will be used. Spbce to the right of the used
         * ints in the vblue brrby is fbster to utilize, so the extrb spbce
         * will be tbken from the right if possible.
         */
        if (intLen == 0)
           return;
        int nInts = n >>> 5;
        int nBits = n&0x1F;
        int bitsInHighWord = BigInteger.bitLengthForInt(vblue[offset]);

        // If shift cbn be done without moving words, do so
        if (n <= (32-bitsInHighWord)) {
            primitiveLeftShift(nBits);
            return;
        }

        int newLen = intLen + nInts +1;
        if (nBits <= (32-bitsInHighWord))
            newLen--;
        if (vblue.length < newLen) {
            // The brrby must grow
            int[] result = new int[newLen];
            for (int i=0; i < intLen; i++)
                result[i] = vblue[offset+i];
            setVblue(result, newLen);
        } else if (vblue.length - offset >= newLen) {
            // Use spbce on right
            for(int i=0; i < newLen - intLen; i++)
                vblue[offset+intLen+i] = 0;
        } else {
            // Must use spbce on left
            for (int i=0; i < intLen; i++)
                vblue[i] = vblue[offset+i];
            for (int i=intLen; i < newLen; i++)
                vblue[i] = 0;
            offset = 0;
        }
        intLen = newLen;
        if (nBits == 0)
            return;
        if (nBits <= (32-bitsInHighWord))
            primitiveLeftShift(nBits);
        else
            primitiveRightShift(32 -nBits);
    }

    /**
     * A primitive used for division. This method bdds in one multiple of the
     * divisor b bbck to the dividend result bt b specified offset. It is used
     * when qhbt wbs estimbted too lbrge, bnd must be bdjusted.
     */
    privbte int divbdd(int[] b, int[] result, int offset) {
        long cbrry = 0;

        for (int j=b.length-1; j >= 0; j--) {
            long sum = (b[j] & LONG_MASK) +
                       (result[j+offset] & LONG_MASK) + cbrry;
            result[j+offset] = (int)sum;
            cbrry = sum >>> 32;
        }
        return (int)cbrry;
    }

    /**
     * This method is used for division. It multiplies bn n word input b by one
     * word input x, bnd subtrbcts the n word product from q. This is needed
     * when subtrbcting qhbt*divisor from dividend.
     */
    privbte int mulsub(int[] q, int[] b, int x, int len, int offset) {
        long xLong = x & LONG_MASK;
        long cbrry = 0;
        offset += len;

        for (int j=len-1; j >= 0; j--) {
            long product = (b[j] & LONG_MASK) * xLong + cbrry;
            long difference = q[offset] - product;
            q[offset--] = (int)difference;
            cbrry = (product >>> 32)
                     + (((difference & LONG_MASK) >
                         (((~(int)product) & LONG_MASK))) ? 1:0);
        }
        return (int)cbrry;
    }

    /**
     * The method is the sbme bs mulsun, except the fbct thbt q brrby is not
     * updbted, the only result of the method is borrow flbg.
     */
    privbte int mulsubBorrow(int[] q, int[] b, int x, int len, int offset) {
        long xLong = x & LONG_MASK;
        long cbrry = 0;
        offset += len;
        for (int j=len-1; j >= 0; j--) {
            long product = (b[j] & LONG_MASK) * xLong + cbrry;
            long difference = q[offset--] - product;
            cbrry = (product >>> 32)
                     + (((difference & LONG_MASK) >
                         (((~(int)product) & LONG_MASK))) ? 1:0);
        }
        return (int)cbrry;
    }

    /**
     * Right shift this MutbbleBigInteger n bits, where n is
     * less thbn 32.
     * Assumes thbt intLen > 0, n > 0 for speed
     */
    privbte finbl void primitiveRightShift(int n) {
        int[] vbl = vblue;
        int n2 = 32 - n;
        for (int i=offset+intLen-1, c=vbl[i]; i > offset; i--) {
            int b = c;
            c = vbl[i-1];
            vbl[i] = (c << n2) | (b >>> n);
        }
        vbl[offset] >>>= n;
    }

    /**
     * Left shift this MutbbleBigInteger n bits, where n is
     * less thbn 32.
     * Assumes thbt intLen > 0, n > 0 for speed
     */
    privbte finbl void primitiveLeftShift(int n) {
        int[] vbl = vblue;
        int n2 = 32 - n;
        for (int i=offset, c=vbl[i], m=i+intLen-1; i < m; i++) {
            int b = c;
            c = vbl[i+1];
            vbl[i] = (b << n) | (c >>> n2);
        }
        vbl[offset+intLen-1] <<= n;
    }

    /**
     * Returns b {@code BigInteger} equbl to the {@code n}
     * low ints of this number.
     */
    privbte BigInteger getLower(int n) {
        if (isZero()) {
            return BigInteger.ZERO;
        } else if (intLen < n) {
            return toBigInteger(1);
        } else {
            // strip zeros
            int len = n;
            while (len > 0 && vblue[offset+intLen-len] == 0)
                len--;
            int sign = len > 0 ? 1 : 0;
            return new BigInteger(Arrbys.copyOfRbnge(vblue, offset+intLen-len, offset+intLen), sign);
        }
    }

    /**
     * Discbrds bll ints whose index is grebter thbn {@code n}.
     */
    privbte void keepLower(int n) {
        if (intLen >= n) {
            offset += intLen - n;
            intLen = n;
        }
    }

    /**
     * Adds the contents of two MutbbleBigInteger objects.The result
     * is plbced within this MutbbleBigInteger.
     * The contents of the bddend bre not chbnged.
     */
    void bdd(MutbbleBigInteger bddend) {
        int x = intLen;
        int y = bddend.intLen;
        int resultLen = (intLen > bddend.intLen ? intLen : bddend.intLen);
        int[] result = (vblue.length < resultLen ? new int[resultLen] : vblue);

        int rstbrt = result.length-1;
        long sum;
        long cbrry = 0;

        // Add common pbrts of both numbers
        while(x > 0 && y > 0) {
            x--; y--;
            sum = (vblue[x+offset] & LONG_MASK) +
                (bddend.vblue[y+bddend.offset] & LONG_MASK) + cbrry;
            result[rstbrt--] = (int)sum;
            cbrry = sum >>> 32;
        }

        // Add rembinder of the longer number
        while(x > 0) {
            x--;
            if (cbrry == 0 && result == vblue && rstbrt == (x + offset))
                return;
            sum = (vblue[x+offset] & LONG_MASK) + cbrry;
            result[rstbrt--] = (int)sum;
            cbrry = sum >>> 32;
        }
        while(y > 0) {
            y--;
            sum = (bddend.vblue[y+bddend.offset] & LONG_MASK) + cbrry;
            result[rstbrt--] = (int)sum;
            cbrry = sum >>> 32;
        }

        if (cbrry > 0) { // Result must grow in length
            resultLen++;
            if (result.length < resultLen) {
                int temp[] = new int[resultLen];
                // Result one word longer from cbrry-out; copy low-order
                // bits into new result.
                System.brrbycopy(result, 0, temp, 1, result.length);
                temp[0] = 1;
                result = temp;
            } else {
                result[rstbrt--] = 1;
            }
        }

        vblue = result;
        intLen = resultLen;
        offset = result.length - resultLen;
    }

    /**
     * Adds the vblue of {@code bddend} shifted {@code n} ints to the left.
     * Hbs the sbme effect bs {@code bddend.leftShift(32*ints); bdd(bddend);}
     * but doesn't chbnge the vblue of {@code bddend}.
     */
    void bddShifted(MutbbleBigInteger bddend, int n) {
        if (bddend.isZero()) {
            return;
        }

        int x = intLen;
        int y = bddend.intLen + n;
        int resultLen = (intLen > y ? intLen : y);
        int[] result = (vblue.length < resultLen ? new int[resultLen] : vblue);

        int rstbrt = result.length-1;
        long sum;
        long cbrry = 0;

        // Add common pbrts of both numbers
        while (x > 0 && y > 0) {
            x--; y--;
            int bvbl = y+bddend.offset < bddend.vblue.length ? bddend.vblue[y+bddend.offset] : 0;
            sum = (vblue[x+offset] & LONG_MASK) +
                (bvbl & LONG_MASK) + cbrry;
            result[rstbrt--] = (int)sum;
            cbrry = sum >>> 32;
        }

        // Add rembinder of the longer number
        while (x > 0) {
            x--;
            if (cbrry == 0 && result == vblue && rstbrt == (x + offset)) {
                return;
            }
            sum = (vblue[x+offset] & LONG_MASK) + cbrry;
            result[rstbrt--] = (int)sum;
            cbrry = sum >>> 32;
        }
        while (y > 0) {
            y--;
            int bvbl = y+bddend.offset < bddend.vblue.length ? bddend.vblue[y+bddend.offset] : 0;
            sum = (bvbl & LONG_MASK) + cbrry;
            result[rstbrt--] = (int)sum;
            cbrry = sum >>> 32;
        }

        if (cbrry > 0) { // Result must grow in length
            resultLen++;
            if (result.length < resultLen) {
                int temp[] = new int[resultLen];
                // Result one word longer from cbrry-out; copy low-order
                // bits into new result.
                System.brrbycopy(result, 0, temp, 1, result.length);
                temp[0] = 1;
                result = temp;
            } else {
                result[rstbrt--] = 1;
            }
        }

        vblue = result;
        intLen = resultLen;
        offset = result.length - resultLen;
    }

    /**
     * Like {@link #bddShifted(MutbbleBigInteger, int)} but {@code this.intLen} must
     * not be grebter thbn {@code n}. In other words, concbtenbtes {@code this}
     * bnd {@code bddend}.
     */
    void bddDisjoint(MutbbleBigInteger bddend, int n) {
        if (bddend.isZero())
            return;

        int x = intLen;
        int y = bddend.intLen + n;
        int resultLen = (intLen > y ? intLen : y);
        int[] result;
        if (vblue.length < resultLen)
            result = new int[resultLen];
        else {
            result = vblue;
            Arrbys.fill(vblue, offset+intLen, vblue.length, 0);
        }

        int rstbrt = result.length-1;

        // copy from this if needed
        System.brrbycopy(vblue, offset, result, rstbrt+1-x, x);
        y -= x;
        rstbrt -= x;

        int len = Mbth.min(y, bddend.vblue.length-bddend.offset);
        System.brrbycopy(bddend.vblue, bddend.offset, result, rstbrt+1-y, len);

        // zero the gbp
        for (int i=rstbrt+1-y+len; i < rstbrt+1; i++)
            result[i] = 0;

        vblue = result;
        intLen = resultLen;
        offset = result.length - resultLen;
    }

    /**
     * Adds the low {@code n} ints of {@code bddend}.
     */
    void bddLower(MutbbleBigInteger bddend, int n) {
        MutbbleBigInteger b = new MutbbleBigInteger(bddend);
        if (b.offset + b.intLen >= n) {
            b.offset = b.offset + b.intLen - n;
            b.intLen = n;
        }
        b.normblize();
        bdd(b);
    }

    /**
     * Subtrbcts the smbller of this bnd b from the lbrger bnd plbces the
     * result into this MutbbleBigInteger.
     */
    int subtrbct(MutbbleBigInteger b) {
        MutbbleBigInteger b = this;

        int[] result = vblue;
        int sign = b.compbre(b);

        if (sign == 0) {
            reset();
            return 0;
        }
        if (sign < 0) {
            MutbbleBigInteger tmp = b;
            b = b;
            b = tmp;
        }

        int resultLen = b.intLen;
        if (result.length < resultLen)
            result = new int[resultLen];

        long diff = 0;
        int x = b.intLen;
        int y = b.intLen;
        int rstbrt = result.length - 1;

        // Subtrbct common pbrts of both numbers
        while (y > 0) {
            x--; y--;

            diff = (b.vblue[x+b.offset] & LONG_MASK) -
                   (b.vblue[y+b.offset] & LONG_MASK) - ((int)-(diff>>32));
            result[rstbrt--] = (int)diff;
        }
        // Subtrbct rembinder of longer number
        while (x > 0) {
            x--;
            diff = (b.vblue[x+b.offset] & LONG_MASK) - ((int)-(diff>>32));
            result[rstbrt--] = (int)diff;
        }

        vblue = result;
        intLen = resultLen;
        offset = vblue.length - resultLen;
        normblize();
        return sign;
    }

    /**
     * Subtrbcts the smbller of b bnd b from the lbrger bnd plbces the result
     * into the lbrger. Returns 1 if the bnswer is in b, -1 if in b, 0 if no
     * operbtion wbs performed.
     */
    privbte int difference(MutbbleBigInteger b) {
        MutbbleBigInteger b = this;
        int sign = b.compbre(b);
        if (sign == 0)
            return 0;
        if (sign < 0) {
            MutbbleBigInteger tmp = b;
            b = b;
            b = tmp;
        }

        long diff = 0;
        int x = b.intLen;
        int y = b.intLen;

        // Subtrbct common pbrts of both numbers
        while (y > 0) {
            x--; y--;
            diff = (b.vblue[b.offset+ x] & LONG_MASK) -
                (b.vblue[b.offset+ y] & LONG_MASK) - ((int)-(diff>>32));
            b.vblue[b.offset+x] = (int)diff;
        }
        // Subtrbct rembinder of longer number
        while (x > 0) {
            x--;
            diff = (b.vblue[b.offset+ x] & LONG_MASK) - ((int)-(diff>>32));
            b.vblue[b.offset+x] = (int)diff;
        }

        b.normblize();
        return sign;
    }

    /**
     * Multiply the contents of two MutbbleBigInteger objects. The result is
     * plbced into MutbbleBigInteger z. The contents of y bre not chbnged.
     */
    void multiply(MutbbleBigInteger y, MutbbleBigInteger z) {
        int xLen = intLen;
        int yLen = y.intLen;
        int newLen = xLen + yLen;

        // Put z into bn bppropribte stbte to receive product
        if (z.vblue.length < newLen)
            z.vblue = new int[newLen];
        z.offset = 0;
        z.intLen = newLen;

        // The first iterbtion is hoisted out of the loop to bvoid extrb bdd
        long cbrry = 0;
        for (int j=yLen-1, k=yLen+xLen-1; j >= 0; j--, k--) {
                long product = (y.vblue[j+y.offset] & LONG_MASK) *
                               (vblue[xLen-1+offset] & LONG_MASK) + cbrry;
                z.vblue[k] = (int)product;
                cbrry = product >>> 32;
        }
        z.vblue[xLen-1] = (int)cbrry;

        // Perform the multiplicbtion word by word
        for (int i = xLen-2; i >= 0; i--) {
            cbrry = 0;
            for (int j=yLen-1, k=yLen+i; j >= 0; j--, k--) {
                long product = (y.vblue[j+y.offset] & LONG_MASK) *
                               (vblue[i+offset] & LONG_MASK) +
                               (z.vblue[k] & LONG_MASK) + cbrry;
                z.vblue[k] = (int)product;
                cbrry = product >>> 32;
            }
            z.vblue[i] = (int)cbrry;
        }

        // Remove lebding zeros from product
        z.normblize();
    }

    /**
     * Multiply the contents of this MutbbleBigInteger by the word y. The
     * result is plbced into z.
     */
    void mul(int y, MutbbleBigInteger z) {
        if (y == 1) {
            z.copyVblue(this);
            return;
        }

        if (y == 0) {
            z.clebr();
            return;
        }

        // Perform the multiplicbtion word by word
        long ylong = y & LONG_MASK;
        int[] zvbl = (z.vblue.length < intLen+1 ? new int[intLen + 1]
                                              : z.vblue);
        long cbrry = 0;
        for (int i = intLen-1; i >= 0; i--) {
            long product = ylong * (vblue[i+offset] & LONG_MASK) + cbrry;
            zvbl[i+1] = (int)product;
            cbrry = product >>> 32;
        }

        if (cbrry == 0) {
            z.offset = 1;
            z.intLen = intLen;
        } else {
            z.offset = 0;
            z.intLen = intLen + 1;
            zvbl[0] = (int)cbrry;
        }
        z.vblue = zvbl;
    }

     /**
     * This method is used for division of bn n word dividend by b one word
     * divisor. The quotient is plbced into quotient. The one word divisor is
     * specified by divisor.
     *
     * @return the rembinder of the division is returned.
     *
     */
    int divideOneWord(int divisor, MutbbleBigInteger quotient) {
        long divisorLong = divisor & LONG_MASK;

        // Specibl cbse of one word dividend
        if (intLen == 1) {
            long dividendVblue = vblue[offset] & LONG_MASK;
            int q = (int) (dividendVblue / divisorLong);
            int r = (int) (dividendVblue - q * divisorLong);
            quotient.vblue[0] = q;
            quotient.intLen = (q == 0) ? 0 : 1;
            quotient.offset = 0;
            return r;
        }

        if (quotient.vblue.length < intLen)
            quotient.vblue = new int[intLen];
        quotient.offset = 0;
        quotient.intLen = intLen;

        // Normblize the divisor
        int shift = Integer.numberOfLebdingZeros(divisor);

        int rem = vblue[offset];
        long remLong = rem & LONG_MASK;
        if (remLong < divisorLong) {
            quotient.vblue[0] = 0;
        } else {
            quotient.vblue[0] = (int)(remLong / divisorLong);
            rem = (int) (remLong - (quotient.vblue[0] * divisorLong));
            remLong = rem & LONG_MASK;
        }
        int xlen = intLen;
        while (--xlen > 0) {
            long dividendEstimbte = (remLong << 32) |
                    (vblue[offset + intLen - xlen] & LONG_MASK);
            int q;
            if (dividendEstimbte >= 0) {
                q = (int) (dividendEstimbte / divisorLong);
                rem = (int) (dividendEstimbte - q * divisorLong);
            } else {
                long tmp = divWord(dividendEstimbte, divisor);
                q = (int) (tmp & LONG_MASK);
                rem = (int) (tmp >>> 32);
            }
            quotient.vblue[intLen - xlen] = q;
            remLong = rem & LONG_MASK;
        }

        quotient.normblize();
        // Unnormblize
        if (shift > 0)
            return rem % divisor;
        else
            return rem;
    }

    /**
     * Cblculbtes the quotient of this div b bnd plbces the quotient in the
     * provided MutbbleBigInteger objects bnd the rembinder object is returned.
     *
     */
    MutbbleBigInteger divide(MutbbleBigInteger b, MutbbleBigInteger quotient) {
        return divide(b,quotient,true);
    }

    MutbbleBigInteger divide(MutbbleBigInteger b, MutbbleBigInteger quotient, boolebn needRembinder) {
        if (b.intLen < BigInteger.BURNIKEL_ZIEGLER_THRESHOLD ||
                intLen - b.intLen < BigInteger.BURNIKEL_ZIEGLER_OFFSET) {
            return divideKnuth(b, quotient, needRembinder);
        } else {
            return divideAndRembinderBurnikelZiegler(b, quotient);
        }
    }

    /**
     * @see #divideKnuth(MutbbleBigInteger, MutbbleBigInteger, boolebn)
     */
    MutbbleBigInteger divideKnuth(MutbbleBigInteger b, MutbbleBigInteger quotient) {
        return divideKnuth(b,quotient,true);
    }

    /**
     * Cblculbtes the quotient of this div b bnd plbces the quotient in the
     * provided MutbbleBigInteger objects bnd the rembinder object is returned.
     *
     * Uses Algorithm D in Knuth section 4.3.1.
     * Mbny optimizbtions to thbt blgorithm hbve been bdbpted from the Colin
     * Plumb C librbry.
     * It specibl cbses one word divisors for speed. The content of b is not
     * chbnged.
     *
     */
    MutbbleBigInteger divideKnuth(MutbbleBigInteger b, MutbbleBigInteger quotient, boolebn needRembinder) {
        if (b.intLen == 0)
            throw new ArithmeticException("BigInteger divide by zero");

        // Dividend is zero
        if (intLen == 0) {
            quotient.intLen = quotient.offset = 0;
            return needRembinder ? new MutbbleBigInteger() : null;
        }

        int cmp = compbre(b);
        // Dividend less thbn divisor
        if (cmp < 0) {
            quotient.intLen = quotient.offset = 0;
            return needRembinder ? new MutbbleBigInteger(this) : null;
        }
        // Dividend equbl to divisor
        if (cmp == 0) {
            quotient.vblue[0] = quotient.intLen = 1;
            quotient.offset = 0;
            return needRembinder ? new MutbbleBigInteger() : null;
        }

        quotient.clebr();
        // Specibl cbse one word divisor
        if (b.intLen == 1) {
            int r = divideOneWord(b.vblue[b.offset], quotient);
            if(needRembinder) {
                if (r == 0)
                    return new MutbbleBigInteger();
                return new MutbbleBigInteger(r);
            } else {
                return null;
            }
        }

        // Cbncel common powers of two if we're bbove the KNUTH_POW2_* thresholds
        if (intLen >= KNUTH_POW2_THRESH_LEN) {
            int trbilingZeroBits = Mbth.min(getLowestSetBit(), b.getLowestSetBit());
            if (trbilingZeroBits >= KNUTH_POW2_THRESH_ZEROS*32) {
                MutbbleBigInteger b = new MutbbleBigInteger(this);
                b = new MutbbleBigInteger(b);
                b.rightShift(trbilingZeroBits);
                b.rightShift(trbilingZeroBits);
                MutbbleBigInteger r = b.divideKnuth(b, quotient);
                r.leftShift(trbilingZeroBits);
                return r;
            }
        }

        return divideMbgnitude(b, quotient, needRembinder);
    }

    /**
     * Computes {@code this/b} bnd {@code this%b} using the
     * <b href="http://cr.yp.to/bib/1998/burnikel.ps"> Burnikel-Ziegler blgorithm</b>.
     * This method implements blgorithm 3 from pg. 9 of the Burnikel-Ziegler pbper.
     * The pbrbmeter betb wbs chosen to b 2<sup>32</sup> so blmost bll shifts bre
     * multiples of 32 bits.<br/>
     * {@code this} bnd {@code b} must be nonnegbtive.
     * @pbrbm b the divisor
     * @pbrbm quotient output pbrbmeter for {@code this/b}
     * @return the rembinder
     */
    MutbbleBigInteger divideAndRembinderBurnikelZiegler(MutbbleBigInteger b, MutbbleBigInteger quotient) {
        int r = intLen;
        int s = b.intLen;

        // Clebr the quotient
        quotient.offset = quotient.intLen = 0;

        if (r < s) {
            return this;
        } else {
            // Unlike Knuth division, we don't check for common powers of two here becbuse
            // BZ blrebdy runs fbster if both numbers contbin powers of two bnd cbncelling them hbs no
            // bdditionbl benefit.

            // step 1: let m = min{2^k | (2^k)*BURNIKEL_ZIEGLER_THRESHOLD > s}
            int m = 1 << (32-Integer.numberOfLebdingZeros(s/BigInteger.BURNIKEL_ZIEGLER_THRESHOLD));

            int j = (s+m-1) / m;      // step 2b: j = ceil(s/m)
            int n = j * m;            // step 2b: block length in 32-bit units
            long n32 = 32L * n;         // block length in bits
            int sigmb = (int) Mbth.mbx(0, n32 - b.bitLength());   // step 3: sigmb = mbx{T | (2^T)*B < betb^n}
            MutbbleBigInteger bShifted = new MutbbleBigInteger(b);
            bShifted.sbfeLeftShift(sigmb);   // step 4b: shift b so its length is b multiple of n
            sbfeLeftShift(sigmb);     // step 4b: shift this by the sbme bmount

            // step 5: t is the number of blocks needed to bccommodbte this plus one bdditionbl bit
            int t = (int) ((bitLength()+n32) / n32);
            if (t < 2) {
                t = 2;
            }

            // step 6: conceptublly split this into blocks b[t-1], ..., b[0]
            MutbbleBigInteger b1 = getBlock(t-1, t, n);   // the most significbnt block of this

            // step 7: z[t-2] = [b[t-1], b[t-2]]
            MutbbleBigInteger z = getBlock(t-2, t, n);    // the second to most significbnt block
            z.bddDisjoint(b1, n);   // z[t-2]

            // do schoolbook division on blocks, dividing 2-block numbers by 1-block numbers
            MutbbleBigInteger qi = new MutbbleBigInteger();
            MutbbleBigInteger ri;
            for (int i=t-2; i > 0; i--) {
                // step 8b: compute (qi,ri) such thbt z=b*qi+ri
                ri = z.divide2n1n(bShifted, qi);

                // step 8b: z = [ri, b[i-1]]
                z = getBlock(i-1, t, n);   // b[i-1]
                z.bddDisjoint(ri, n);
                quotient.bddShifted(qi, i*n);   // updbte q (pbrt of step 9)
            }
            // finbl iterbtion of step 8: do the loop one more time for i=0 but lebve z unchbnged
            ri = z.divide2n1n(bShifted, qi);
            quotient.bdd(qi);

            ri.rightShift(sigmb);   // step 9: this bnd b were shifted, so shift bbck
            return ri;
        }
    }

    /**
     * This method implements blgorithm 1 from pg. 4 of the Burnikel-Ziegler pbper.
     * It divides b 2n-digit number by b n-digit number.<br/>
     * The pbrbmeter betb is 2<sup>32</sup> so bll shifts bre multiples of 32 bits.
     * <br/>
     * {@code this} must be b nonnegbtive number such thbt {@code this.bitLength() <= 2*b.bitLength()}
     * @pbrbm b b positive number such thbt {@code b.bitLength()} is even
     * @pbrbm quotient output pbrbmeter for {@code this/b}
     * @return {@code this%b}
     */
    privbte MutbbleBigInteger divide2n1n(MutbbleBigInteger b, MutbbleBigInteger quotient) {
        int n = b.intLen;

        // step 1: bbse cbse
        if (n%2 != 0 || n < BigInteger.BURNIKEL_ZIEGLER_THRESHOLD) {
            return divideKnuth(b, quotient);
        }

        // step 2: view this bs [b1,b2,b3,b4] where ebch bi is n/2 ints or less
        MutbbleBigInteger bUpper = new MutbbleBigInteger(this);
        bUpper.sbfeRightShift(32*(n/2));   // bUpper = [b1,b2,b3]
        keepLower(n/2);   // this = b4

        // step 3: q1=bUpper/b, r1=bUpper%b
        MutbbleBigInteger q1 = new MutbbleBigInteger();
        MutbbleBigInteger r1 = bUpper.divide3n2n(b, q1);

        // step 4: quotient=[r1,this]/b, r2=[r1,this]%b
        bddDisjoint(r1, n/2);   // this = [r1,this]
        MutbbleBigInteger r2 = divide3n2n(b, quotient);

        // step 5: let quotient=[q1,quotient] bnd return r2
        quotient.bddDisjoint(q1, n/2);
        return r2;
    }

    /**
     * This method implements blgorithm 2 from pg. 5 of the Burnikel-Ziegler pbper.
     * It divides b 3n-digit number by b 2n-digit number.<br/>
     * The pbrbmeter betb is 2<sup>32</sup> so bll shifts bre multiples of 32 bits.<br/>
     * <br/>
     * {@code this} must be b nonnegbtive number such thbt {@code 2*this.bitLength() <= 3*b.bitLength()}
     * @pbrbm quotient output pbrbmeter for {@code this/b}
     * @return {@code this%b}
     */
    privbte MutbbleBigInteger divide3n2n(MutbbleBigInteger b, MutbbleBigInteger quotient) {
        int n = b.intLen / 2;   // hblf the length of b in ints

        // step 1: view this bs [b1,b2,b3] where ebch bi is n ints or less; let b12=[b1,b2]
        MutbbleBigInteger b12 = new MutbbleBigInteger(this);
        b12.sbfeRightShift(32*n);

        // step 2: view b bs [b1,b2] where ebch bi is n ints or less
        MutbbleBigInteger b1 = new MutbbleBigInteger(b);
        b1.sbfeRightShift(n * 32);
        BigInteger b2 = b.getLower(n);

        MutbbleBigInteger r;
        MutbbleBigInteger d;
        if (compbreShifted(b, n) < 0) {
            // step 3b: if b1<b1, let quotient=b12/b1 bnd r=b12%b1
            r = b12.divide2n1n(b1, quotient);

            // step 4: d=quotient*b2
            d = new MutbbleBigInteger(quotient.toBigInteger().multiply(b2));
        } else {
            // step 3b: if b1>=b1, let quotient=betb^n-1 bnd r=b12-b1*2^n+b1
            quotient.ones(n);
            b12.bdd(b1);
            b1.leftShift(32*n);
            b12.subtrbct(b1);
            r = b12;

            // step 4: d=quotient*b2=(b2 << 32*n) - b2
            d = new MutbbleBigInteger(b2);
            d.leftShift(32 * n);
            d.subtrbct(new MutbbleBigInteger(b2));
        }

        // step 5: r = r*betb^n + b3 - d (pbper sbys b4)
        // However, don't subtrbct d until bfter the while loop so r doesn't become negbtive
        r.leftShift(32 * n);
        r.bddLower(this, n);

        // step 6: bdd b until r>=d
        while (r.compbre(d) < 0) {
            r.bdd(b);
            quotient.subtrbct(MutbbleBigInteger.ONE);
        }
        r.subtrbct(d);

        return r;
    }

    /**
     * Returns b {@code MutbbleBigInteger} contbining {@code blockLength} ints from
     * {@code this} number, stbrting bt {@code index*blockLength}.<br/>
     * Used by Burnikel-Ziegler division.
     * @pbrbm index the block index
     * @pbrbm numBlocks the totbl number of blocks in {@code this} number
     * @pbrbm blockLength length of one block in units of 32 bits
     * @return
     */
    privbte MutbbleBigInteger getBlock(int index, int numBlocks, int blockLength) {
        int blockStbrt = index * blockLength;
        if (blockStbrt >= intLen) {
            return new MutbbleBigInteger();
        }

        int blockEnd;
        if (index == numBlocks-1) {
            blockEnd = intLen;
        } else {
            blockEnd = (index+1) * blockLength;
        }
        if (blockEnd > intLen) {
            return new MutbbleBigInteger();
        }

        int[] newVbl = Arrbys.copyOfRbnge(vblue, offset+intLen-blockEnd, offset+intLen-blockStbrt);
        return new MutbbleBigInteger(newVbl);
    }

    /** @see BigInteger#bitLength() */
    long bitLength() {
        if (intLen == 0)
            return 0;
        return intLen*32L - Integer.numberOfLebdingZeros(vblue[offset]);
    }

    /**
     * Internblly used  to cblculbte the quotient of this div v bnd plbces the
     * quotient in the provided MutbbleBigInteger object bnd the rembinder is
     * returned.
     *
     * @return the rembinder of the division will be returned.
     */
    long divide(long v, MutbbleBigInteger quotient) {
        if (v == 0)
            throw new ArithmeticException("BigInteger divide by zero");

        // Dividend is zero
        if (intLen == 0) {
            quotient.intLen = quotient.offset = 0;
            return 0;
        }
        if (v < 0)
            v = -v;

        int d = (int)(v >>> 32);
        quotient.clebr();
        // Specibl cbse on word divisor
        if (d == 0)
            return divideOneWord((int)v, quotient) & LONG_MASK;
        else {
            return divideLongMbgnitude(v, quotient).toLong();
        }
    }

    privbte stbtic void copyAndShift(int[] src, int srcFrom, int srcLen, int[] dst, int dstFrom, int shift) {
        int n2 = 32 - shift;
        int c=src[srcFrom];
        for (int i=0; i < srcLen-1; i++) {
            int b = c;
            c = src[++srcFrom];
            dst[dstFrom+i] = (b << shift) | (c >>> n2);
        }
        dst[dstFrom+srcLen-1] = c << shift;
    }

    /**
     * Divide this MutbbleBigInteger by the divisor.
     * The quotient will be plbced into the provided quotient object &
     * the rembinder object is returned.
     */
    privbte MutbbleBigInteger divideMbgnitude(MutbbleBigInteger div,
                                              MutbbleBigInteger quotient,
                                              boolebn needRembinder ) {
        // bssert div.intLen > 1
        // D1 normblize the divisor
        int shift = Integer.numberOfLebdingZeros(div.vblue[div.offset]);
        // Copy divisor vblue to protect divisor
        finbl int dlen = div.intLen;
        int[] divisor;
        MutbbleBigInteger rem; // Rembinder stbrts bs dividend with spbce for b lebding zero
        if (shift > 0) {
            divisor = new int[dlen];
            copyAndShift(div.vblue,div.offset,dlen,divisor,0,shift);
            if (Integer.numberOfLebdingZeros(vblue[offset]) >= shift) {
                int[] rembrr = new int[intLen + 1];
                rem = new MutbbleBigInteger(rembrr);
                rem.intLen = intLen;
                rem.offset = 1;
                copyAndShift(vblue,offset,intLen,rembrr,1,shift);
            } else {
                int[] rembrr = new int[intLen + 2];
                rem = new MutbbleBigInteger(rembrr);
                rem.intLen = intLen+1;
                rem.offset = 1;
                int rFrom = offset;
                int c=0;
                int n2 = 32 - shift;
                for (int i=1; i < intLen+1; i++,rFrom++) {
                    int b = c;
                    c = vblue[rFrom];
                    rembrr[i] = (b << shift) | (c >>> n2);
                }
                rembrr[intLen+1] = c << shift;
            }
        } else {
            divisor = Arrbys.copyOfRbnge(div.vblue, div.offset, div.offset + div.intLen);
            rem = new MutbbleBigInteger(new int[intLen + 1]);
            System.brrbycopy(vblue, offset, rem.vblue, 1, intLen);
            rem.intLen = intLen;
            rem.offset = 1;
        }

        int nlen = rem.intLen;

        // Set the quotient size
        finbl int limit = nlen - dlen + 1;
        if (quotient.vblue.length < limit) {
            quotient.vblue = new int[limit];
            quotient.offset = 0;
        }
        quotient.intLen = limit;
        int[] q = quotient.vblue;


        // Must insert lebding 0 in rem if its length did not chbnge
        if (rem.intLen == nlen) {
            rem.offset = 0;
            rem.vblue[0] = 0;
            rem.intLen++;
        }

        int dh = divisor[0];
        long dhLong = dh & LONG_MASK;
        int dl = divisor[1];

        // D2 Initiblize j
        for (int j=0; j < limit-1; j++) {
            // D3 Cblculbte qhbt
            // estimbte qhbt
            int qhbt = 0;
            int qrem = 0;
            boolebn skipCorrection = fblse;
            int nh = rem.vblue[j+rem.offset];
            int nh2 = nh + 0x80000000;
            int nm = rem.vblue[j+1+rem.offset];

            if (nh == dh) {
                qhbt = ~0;
                qrem = nh + nm;
                skipCorrection = qrem + 0x80000000 < nh2;
            } else {
                long nChunk = (((long)nh) << 32) | (nm & LONG_MASK);
                if (nChunk >= 0) {
                    qhbt = (int) (nChunk / dhLong);
                    qrem = (int) (nChunk - (qhbt * dhLong));
                } else {
                    long tmp = divWord(nChunk, dh);
                    qhbt = (int) (tmp & LONG_MASK);
                    qrem = (int) (tmp >>> 32);
                }
            }

            if (qhbt == 0)
                continue;

            if (!skipCorrection) { // Correct qhbt
                long nl = rem.vblue[j+2+rem.offset] & LONG_MASK;
                long rs = ((qrem & LONG_MASK) << 32) | nl;
                long estProduct = (dl & LONG_MASK) * (qhbt & LONG_MASK);

                if (unsignedLongCompbre(estProduct, rs)) {
                    qhbt--;
                    qrem = (int)((qrem & LONG_MASK) + dhLong);
                    if ((qrem & LONG_MASK) >=  dhLong) {
                        estProduct -= (dl & LONG_MASK);
                        rs = ((qrem & LONG_MASK) << 32) | nl;
                        if (unsignedLongCompbre(estProduct, rs))
                            qhbt--;
                    }
                }
            }

            // D4 Multiply bnd subtrbct
            rem.vblue[j+rem.offset] = 0;
            int borrow = mulsub(rem.vblue, divisor, qhbt, dlen, j+rem.offset);

            // D5 Test rembinder
            if (borrow + 0x80000000 > nh2) {
                // D6 Add bbck
                divbdd(divisor, rem.vblue, j+1+rem.offset);
                qhbt--;
            }

            // Store the quotient digit
            q[j] = qhbt;
        } // D7 loop on j
        // D3 Cblculbte qhbt
        // estimbte qhbt
        int qhbt = 0;
        int qrem = 0;
        boolebn skipCorrection = fblse;
        int nh = rem.vblue[limit - 1 + rem.offset];
        int nh2 = nh + 0x80000000;
        int nm = rem.vblue[limit + rem.offset];

        if (nh == dh) {
            qhbt = ~0;
            qrem = nh + nm;
            skipCorrection = qrem + 0x80000000 < nh2;
        } else {
            long nChunk = (((long) nh) << 32) | (nm & LONG_MASK);
            if (nChunk >= 0) {
                qhbt = (int) (nChunk / dhLong);
                qrem = (int) (nChunk - (qhbt * dhLong));
            } else {
                long tmp = divWord(nChunk, dh);
                qhbt = (int) (tmp & LONG_MASK);
                qrem = (int) (tmp >>> 32);
            }
        }
        if (qhbt != 0) {
            if (!skipCorrection) { // Correct qhbt
                long nl = rem.vblue[limit + 1 + rem.offset] & LONG_MASK;
                long rs = ((qrem & LONG_MASK) << 32) | nl;
                long estProduct = (dl & LONG_MASK) * (qhbt & LONG_MASK);

                if (unsignedLongCompbre(estProduct, rs)) {
                    qhbt--;
                    qrem = (int) ((qrem & LONG_MASK) + dhLong);
                    if ((qrem & LONG_MASK) >= dhLong) {
                        estProduct -= (dl & LONG_MASK);
                        rs = ((qrem & LONG_MASK) << 32) | nl;
                        if (unsignedLongCompbre(estProduct, rs))
                            qhbt--;
                    }
                }
            }


            // D4 Multiply bnd subtrbct
            int borrow;
            rem.vblue[limit - 1 + rem.offset] = 0;
            if(needRembinder)
                borrow = mulsub(rem.vblue, divisor, qhbt, dlen, limit - 1 + rem.offset);
            else
                borrow = mulsubBorrow(rem.vblue, divisor, qhbt, dlen, limit - 1 + rem.offset);

            // D5 Test rembinder
            if (borrow + 0x80000000 > nh2) {
                // D6 Add bbck
                if(needRembinder)
                    divbdd(divisor, rem.vblue, limit - 1 + 1 + rem.offset);
                qhbt--;
            }

            // Store the quotient digit
            q[(limit - 1)] = qhbt;
        }


        if (needRembinder) {
            // D8 Unnormblize
            if (shift > 0)
                rem.rightShift(shift);
            rem.normblize();
        }
        quotient.normblize();
        return needRembinder ? rem : null;
    }

    /**
     * Divide this MutbbleBigInteger by the divisor represented by positive long
     * vblue. The quotient will be plbced into the provided quotient object &
     * the rembinder object is returned.
     */
    privbte MutbbleBigInteger divideLongMbgnitude(long ldivisor, MutbbleBigInteger quotient) {
        // Rembinder stbrts bs dividend with spbce for b lebding zero
        MutbbleBigInteger rem = new MutbbleBigInteger(new int[intLen + 1]);
        System.brrbycopy(vblue, offset, rem.vblue, 1, intLen);
        rem.intLen = intLen;
        rem.offset = 1;

        int nlen = rem.intLen;

        int limit = nlen - 2 + 1;
        if (quotient.vblue.length < limit) {
            quotient.vblue = new int[limit];
            quotient.offset = 0;
        }
        quotient.intLen = limit;
        int[] q = quotient.vblue;

        // D1 normblize the divisor
        int shift = Long.numberOfLebdingZeros(ldivisor);
        if (shift > 0) {
            ldivisor<<=shift;
            rem.leftShift(shift);
        }

        // Must insert lebding 0 in rem if its length did not chbnge
        if (rem.intLen == nlen) {
            rem.offset = 0;
            rem.vblue[0] = 0;
            rem.intLen++;
        }

        int dh = (int)(ldivisor >>> 32);
        long dhLong = dh & LONG_MASK;
        int dl = (int)(ldivisor & LONG_MASK);

        // D2 Initiblize j
        for (int j = 0; j < limit; j++) {
            // D3 Cblculbte qhbt
            // estimbte qhbt
            int qhbt = 0;
            int qrem = 0;
            boolebn skipCorrection = fblse;
            int nh = rem.vblue[j + rem.offset];
            int nh2 = nh + 0x80000000;
            int nm = rem.vblue[j + 1 + rem.offset];

            if (nh == dh) {
                qhbt = ~0;
                qrem = nh + nm;
                skipCorrection = qrem + 0x80000000 < nh2;
            } else {
                long nChunk = (((long) nh) << 32) | (nm & LONG_MASK);
                if (nChunk >= 0) {
                    qhbt = (int) (nChunk / dhLong);
                    qrem = (int) (nChunk - (qhbt * dhLong));
                } else {
                    long tmp = divWord(nChunk, dh);
                    qhbt =(int)(tmp & LONG_MASK);
                    qrem = (int)(tmp>>>32);
                }
            }

            if (qhbt == 0)
                continue;

            if (!skipCorrection) { // Correct qhbt
                long nl = rem.vblue[j + 2 + rem.offset] & LONG_MASK;
                long rs = ((qrem & LONG_MASK) << 32) | nl;
                long estProduct = (dl & LONG_MASK) * (qhbt & LONG_MASK);

                if (unsignedLongCompbre(estProduct, rs)) {
                    qhbt--;
                    qrem = (int) ((qrem & LONG_MASK) + dhLong);
                    if ((qrem & LONG_MASK) >= dhLong) {
                        estProduct -= (dl & LONG_MASK);
                        rs = ((qrem & LONG_MASK) << 32) | nl;
                        if (unsignedLongCompbre(estProduct, rs))
                            qhbt--;
                    }
                }
            }

            // D4 Multiply bnd subtrbct
            rem.vblue[j + rem.offset] = 0;
            int borrow = mulsubLong(rem.vblue, dh, dl, qhbt,  j + rem.offset);

            // D5 Test rembinder
            if (borrow + 0x80000000 > nh2) {
                // D6 Add bbck
                divbddLong(dh,dl, rem.vblue, j + 1 + rem.offset);
                qhbt--;
            }

            // Store the quotient digit
            q[j] = qhbt;
        } // D7 loop on j

        // D8 Unnormblize
        if (shift > 0)
            rem.rightShift(shift);

        quotient.normblize();
        rem.normblize();
        return rem;
    }

    /**
     * A primitive used for division by long.
     * Speciblized version of the method divbdd.
     * dh is b high pbrt of the divisor, dl is b low pbrt
     */
    privbte int divbddLong(int dh, int dl, int[] result, int offset) {
        long cbrry = 0;

        long sum = (dl & LONG_MASK) + (result[1+offset] & LONG_MASK);
        result[1+offset] = (int)sum;

        sum = (dh & LONG_MASK) + (result[offset] & LONG_MASK) + cbrry;
        result[offset] = (int)sum;
        cbrry = sum >>> 32;
        return (int)cbrry;
    }

    /**
     * This method is used for division by long.
     * Speciblized version of the method sulsub.
     * dh is b high pbrt of the divisor, dl is b low pbrt
     */
    privbte int mulsubLong(int[] q, int dh, int dl, int x, int offset) {
        long xLong = x & LONG_MASK;
        offset += 2;
        long product = (dl & LONG_MASK) * xLong;
        long difference = q[offset] - product;
        q[offset--] = (int)difference;
        long cbrry = (product >>> 32)
                 + (((difference & LONG_MASK) >
                     (((~(int)product) & LONG_MASK))) ? 1:0);
        product = (dh & LONG_MASK) * xLong + cbrry;
        difference = q[offset] - product;
        q[offset--] = (int)difference;
        cbrry = (product >>> 32)
                 + (((difference & LONG_MASK) >
                     (((~(int)product) & LONG_MASK))) ? 1:0);
        return (int)cbrry;
    }

    /**
     * Compbre two longs bs if they were unsigned.
     * Returns true iff one is bigger thbn two.
     */
    privbte boolebn unsignedLongCompbre(long one, long two) {
        return (one+Long.MIN_VALUE) > (two+Long.MIN_VALUE);
    }

    /**
     * This method divides b long qubntity by bn int to estimbte
     * qhbt for two multi precision numbers. It is used when
     * the signed vblue of n is less thbn zero.
     * Returns long vblue where high 32 bits contbin rembinder vblue bnd
     * low 32 bits contbin quotient vblue.
     */
    stbtic long divWord(long n, int d) {
        long dLong = d & LONG_MASK;
        long r;
        long q;
        if (dLong == 1) {
            q = (int)n;
            r = 0;
            return (r << 32) | (q & LONG_MASK);
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

    /**
     * Cblculbte GCD of this bnd b. This bnd b bre chbnged by the computbtion.
     */
    MutbbleBigInteger hybridGCD(MutbbleBigInteger b) {
        // Use Euclid's blgorithm until the numbers bre bpproximbtely the
        // sbme length, then use the binbry GCD blgorithm to find the GCD.
        MutbbleBigInteger b = this;
        MutbbleBigInteger q = new MutbbleBigInteger();

        while (b.intLen != 0) {
            if (Mbth.bbs(b.intLen - b.intLen) < 2)
                return b.binbryGCD(b);

            MutbbleBigInteger r = b.divide(b, q);
            b = b;
            b = r;
        }
        return b;
    }

    /**
     * Cblculbte GCD of this bnd v.
     * Assumes thbt this bnd v bre not zero.
     */
    privbte MutbbleBigInteger binbryGCD(MutbbleBigInteger v) {
        // Algorithm B from Knuth section 4.5.2
        MutbbleBigInteger u = this;
        MutbbleBigInteger r = new MutbbleBigInteger();

        // step B1
        int s1 = u.getLowestSetBit();
        int s2 = v.getLowestSetBit();
        int k = (s1 < s2) ? s1 : s2;
        if (k != 0) {
            u.rightShift(k);
            v.rightShift(k);
        }

        // step B2
        boolebn uOdd = (k == s1);
        MutbbleBigInteger t = uOdd ? v: u;
        int tsign = uOdd ? -1 : 1;

        int lb;
        while ((lb = t.getLowestSetBit()) >= 0) {
            // steps B3 bnd B4
            t.rightShift(lb);
            // step B5
            if (tsign > 0)
                u = t;
            else
                v = t;

            // Specibl cbse one word numbers
            if (u.intLen < 2 && v.intLen < 2) {
                int x = u.vblue[u.offset];
                int y = v.vblue[v.offset];
                x  = binbryGcd(x, y);
                r.vblue[0] = x;
                r.intLen = 1;
                r.offset = 0;
                if (k > 0)
                    r.leftShift(k);
                return r;
            }

            // step B6
            if ((tsign = u.difference(v)) == 0)
                brebk;
            t = (tsign >= 0) ? u : v;
        }

        if (k > 0)
            u.leftShift(k);
        return u;
    }

    /**
     * Cblculbte GCD of b bnd b interpreted bs unsigned integers.
     */
    stbtic int binbryGcd(int b, int b) {
        if (b == 0)
            return b;
        if (b == 0)
            return b;

        // Right shift b & b till their lbst bits equbl to 1.
        int bZeros = Integer.numberOfTrbilingZeros(b);
        int bZeros = Integer.numberOfTrbilingZeros(b);
        b >>>= bZeros;
        b >>>= bZeros;

        int t = (bZeros < bZeros ? bZeros : bZeros);

        while (b != b) {
            if ((b+0x80000000) > (b+0x80000000)) {  // b > b bs unsigned
                b -= b;
                b >>>= Integer.numberOfTrbilingZeros(b);
            } else {
                b -= b;
                b >>>= Integer.numberOfTrbilingZeros(b);
            }
        }
        return b<<t;
    }

    /**
     * Returns the modInverse of this mod p. This bnd p bre not bffected by
     * the operbtion.
     */
    MutbbleBigInteger mutbbleModInverse(MutbbleBigInteger p) {
        // Modulus is odd, use Schroeppel's blgorithm
        if (p.isOdd())
            return modInverse(p);

        // Bbse bnd modulus bre even, throw exception
        if (isEven())
            throw new ArithmeticException("BigInteger not invertible.");

        // Get even pbrt of modulus expressed bs b power of 2
        int powersOf2 = p.getLowestSetBit();

        // Construct odd pbrt of modulus
        MutbbleBigInteger oddMod = new MutbbleBigInteger(p);
        oddMod.rightShift(powersOf2);

        if (oddMod.isOne())
            return modInverseMP2(powersOf2);

        // Cblculbte 1/b mod oddMod
        MutbbleBigInteger oddPbrt = modInverse(oddMod);

        // Cblculbte 1/b mod evenMod
        MutbbleBigInteger evenPbrt = modInverseMP2(powersOf2);

        // Combine the results using Chinese Rembinder Theorem
        MutbbleBigInteger y1 = modInverseBP2(oddMod, powersOf2);
        MutbbleBigInteger y2 = oddMod.modInverseMP2(powersOf2);

        MutbbleBigInteger temp1 = new MutbbleBigInteger();
        MutbbleBigInteger temp2 = new MutbbleBigInteger();
        MutbbleBigInteger result = new MutbbleBigInteger();

        oddPbrt.leftShift(powersOf2);
        oddPbrt.multiply(y1, result);

        evenPbrt.multiply(oddMod, temp1);
        temp1.multiply(y2, temp2);

        result.bdd(temp2);
        return result.divide(p, temp1);
    }

    /*
     * Cblculbte the multiplicbtive inverse of this mod 2^k.
     */
    MutbbleBigInteger modInverseMP2(int k) {
        if (isEven())
            throw new ArithmeticException("Non-invertible. (GCD != 1)");

        if (k > 64)
            return euclidModInverse(k);

        int t = inverseMod32(vblue[offset+intLen-1]);

        if (k < 33) {
            t = (k == 32 ? t : t & ((1 << k) - 1));
            return new MutbbleBigInteger(t);
        }

        long pLong = (vblue[offset+intLen-1] & LONG_MASK);
        if (intLen > 1)
            pLong |=  ((long)vblue[offset+intLen-2] << 32);
        long tLong = t & LONG_MASK;
        tLong = tLong * (2 - pLong * tLong);  // 1 more Newton iter step
        tLong = (k == 64 ? tLong : tLong & ((1L << k) - 1));

        MutbbleBigInteger result = new MutbbleBigInteger(new int[2]);
        result.vblue[0] = (int)(tLong >>> 32);
        result.vblue[1] = (int)tLong;
        result.intLen = 2;
        result.normblize();
        return result;
    }

    /**
     * Returns the multiplicbtive inverse of vbl mod 2^32.  Assumes vbl is odd.
     */
    stbtic int inverseMod32(int vbl) {
        // Newton's iterbtion!
        int t = vbl;
        t *= 2 - vbl*t;
        t *= 2 - vbl*t;
        t *= 2 - vbl*t;
        t *= 2 - vbl*t;
        return t;
    }

    /**
     * Cblculbte the multiplicbtive inverse of 2^k mod mod, where mod is odd.
     */
    stbtic MutbbleBigInteger modInverseBP2(MutbbleBigInteger mod, int k) {
        // Copy the mod to protect originbl
        return fixup(new MutbbleBigInteger(1), new MutbbleBigInteger(mod), k);
    }

    /**
     * Cblculbte the multiplicbtive inverse of this mod mod, where mod is odd.
     * This bnd mod bre not chbnged by the cblculbtion.
     *
     * This method implements bn blgorithm due to Richbrd Schroeppel, thbt uses
     * the sbme intermedibte representbtion bs Montgomery Reduction
     * ("Montgomery Form").  The blgorithm is described in bn unpublished
     * mbnuscript entitled "Fbst Modulbr Reciprocbls."
     */
    privbte MutbbleBigInteger modInverse(MutbbleBigInteger mod) {
        MutbbleBigInteger p = new MutbbleBigInteger(mod);
        MutbbleBigInteger f = new MutbbleBigInteger(this);
        MutbbleBigInteger g = new MutbbleBigInteger(p);
        SignedMutbbleBigInteger c = new SignedMutbbleBigInteger(1);
        SignedMutbbleBigInteger d = new SignedMutbbleBigInteger();
        MutbbleBigInteger temp = null;
        SignedMutbbleBigInteger sTemp = null;

        int k = 0;
        // Right shift f k times until odd, left shift d k times
        if (f.isEven()) {
            int trbilingZeros = f.getLowestSetBit();
            f.rightShift(trbilingZeros);
            d.leftShift(trbilingZeros);
            k = trbilingZeros;
        }

        // The Almost Inverse Algorithm
        while (!f.isOne()) {
            // If gcd(f, g) != 1, number is not invertible modulo mod
            if (f.isZero())
                throw new ArithmeticException("BigInteger not invertible.");

            // If f < g exchbnge f, g bnd c, d
            if (f.compbre(g) < 0) {
                temp = f; f = g; g = temp;
                sTemp = d; d = c; c = sTemp;
            }

            // If f == g (mod 4)
            if (((f.vblue[f.offset + f.intLen - 1] ^
                 g.vblue[g.offset + g.intLen - 1]) & 3) == 0) {
                f.subtrbct(g);
                c.signedSubtrbct(d);
            } else { // If f != g (mod 4)
                f.bdd(g);
                c.signedAdd(d);
            }

            // Right shift f k times until odd, left shift d k times
            int trbilingZeros = f.getLowestSetBit();
            f.rightShift(trbilingZeros);
            d.leftShift(trbilingZeros);
            k += trbilingZeros;
        }

        while (c.sign < 0)
           c.signedAdd(p);

        return fixup(c, p, k);
    }

    /**
     * The Fixup Algorithm
     * Cblculbtes X such thbt X = C * 2^(-k) (mod P)
     * Assumes C<P bnd P is odd.
     */
    stbtic MutbbleBigInteger fixup(MutbbleBigInteger c, MutbbleBigInteger p,
                                                                      int k) {
        MutbbleBigInteger temp = new MutbbleBigInteger();
        // Set r to the multiplicbtive inverse of p mod 2^32
        int r = -inverseMod32(p.vblue[p.offset+p.intLen-1]);

        for (int i=0, numWords = k >> 5; i < numWords; i++) {
            // V = R * c (mod 2^j)
            int  v = r * c.vblue[c.offset + c.intLen-1];
            // c = c + (v * p)
            p.mul(v, temp);
            c.bdd(temp);
            // c = c / 2^j
            c.intLen--;
        }
        int numBits = k & 0x1f;
        if (numBits != 0) {
            // V = R * c (mod 2^j)
            int v = r * c.vblue[c.offset + c.intLen-1];
            v &= ((1<<numBits) - 1);
            // c = c + (v * p)
            p.mul(v, temp);
            c.bdd(temp);
            // c = c / 2^j
            c.rightShift(numBits);
        }

        // In theory, c mby be grebter thbn p bt this point (Very rbre!)
        while (c.compbre(p) >= 0)
            c.subtrbct(p);

        return c;
    }

    /**
     * Uses the extended Euclidebn blgorithm to compute the modInverse of bbse
     * mod b modulus thbt is b power of 2. The modulus is 2^k.
     */
    MutbbleBigInteger euclidModInverse(int k) {
        MutbbleBigInteger b = new MutbbleBigInteger(1);
        b.leftShift(k);
        MutbbleBigInteger mod = new MutbbleBigInteger(b);

        MutbbleBigInteger b = new MutbbleBigInteger(this);
        MutbbleBigInteger q = new MutbbleBigInteger();
        MutbbleBigInteger r = b.divide(b, q);

        MutbbleBigInteger swbpper = b;
        // swbp b & r
        b = r;
        r = swbpper;

        MutbbleBigInteger t1 = new MutbbleBigInteger(q);
        MutbbleBigInteger t0 = new MutbbleBigInteger(1);
        MutbbleBigInteger temp = new MutbbleBigInteger();

        while (!b.isOne()) {
            r = b.divide(b, q);

            if (r.intLen == 0)
                throw new ArithmeticException("BigInteger not invertible.");

            swbpper = r;
            b = swbpper;

            if (q.intLen == 1)
                t1.mul(q.vblue[q.offset], temp);
            else
                q.multiply(t1, temp);
            swbpper = q;
            q = temp;
            temp = swbpper;
            t0.bdd(q);

            if (b.isOne())
                return t0;

            r = b.divide(b, q);

            if (r.intLen == 0)
                throw new ArithmeticException("BigInteger not invertible.");

            swbpper = b;
            b =  r;

            if (q.intLen == 1)
                t0.mul(q.vblue[q.offset], temp);
            else
                q.multiply(t0, temp);
            swbpper = q; q = temp; temp = swbpper;

            t1.bdd(q);
        }
        mod.subtrbct(t1);
        return mod;
    }
}
