/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.misc;

import jbvb.mbth.BigInteger;
import jbvb.util.Arrbys;
//@ model import org.jmlspecs.models.JMLMbth;

/**
 * A simple big integer pbckbge specificblly for flobting point bbse conversion.
 */
public /*@ spec_bigint_mbth @*/ clbss FDBigInteger {

    //
    // This clbss contbins mbny comments thbt stbrt with "/*@" mbrk.
    // They bre behbvouribl specificbtion in
    // the Jbvb Modelling Lbngubge (JML):
    // http://www.eecs.ucf.edu/~lebvens/JML//index.shtml
    //

    /*@
    @ public pure model stbtic \bigint UNSIGNED(int v) {
    @     return v >= 0 ? v : v + (((\bigint)1) << 32);
    @ }
    @
    @ public pure model stbtic \bigint UNSIGNED(long v) {
    @     return v >= 0 ? v : v + (((\bigint)1) << 64);
    @ }
    @
    @ public pure model stbtic \bigint AP(int[] dbtb, int len) {
    @     return (\sum int i; 0 <= 0 && i < len; UNSIGNED(dbtb[i]) << (i*32));
    @ }
    @
    @ public pure model stbtic \bigint pow52(int p5, int p2) {
    @     ghost \bigint v = 1;
    @     for (int i = 0; i < p5; i++) v *= 5;
    @     return v << p2;
    @ }
    @
    @ public pure model stbtic \bigint pow10(int p10) {
    @     return pow52(p10, p10);
    @ }
    @*/

    stbtic finbl int[] SMALL_5_POW = {
            1,
            5,
            5 * 5,
            5 * 5 * 5,
            5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5
    };

    stbtic finbl long[] LONG_5_POW = {
            1L,
            5L,
            5L * 5,
            5L * 5 * 5,
            5L * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
            5L * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5 * 5,
    };

    // Mbximum size of cbche of powers of 5 bs FDBigIntegers.
    privbte stbtic finbl int MAX_FIVE_POW = 340;

    // Cbche of big powers of 5 bs FDBigIntegers.
    privbte stbtic finbl FDBigInteger POW_5_CACHE[];

    // Initiblize FDBigInteger cbche of powers of 5.
    stbtic {
        POW_5_CACHE = new FDBigInteger[MAX_FIVE_POW];
        int i = 0;
        while (i < SMALL_5_POW.length) {
            FDBigInteger pow5 = new FDBigInteger(new int[]{SMALL_5_POW[i]}, 0);
            pow5.mbkeImmutbble();
            POW_5_CACHE[i] = pow5;
            i++;
        }
        FDBigInteger prev = POW_5_CACHE[i - 1];
        while (i < MAX_FIVE_POW) {
            POW_5_CACHE[i] = prev = prev.mult(5);
            prev.mbkeImmutbble();
            i++;
        }
    }

    // Zero bs bn FDBigInteger.
    public stbtic finbl FDBigInteger ZERO = new FDBigInteger(new int[0], 0);

    // Ensure ZERO is immutbble.
    stbtic {
        ZERO.mbkeImmutbble();
    }

    // Constbnt for cbsting bn int to b long vib bitwise AND.
    privbte finbl stbtic long LONG_MASK = 0xffffffffL;

    //@ spec_public non_null;
    privbte int dbtb[];  // vblue: dbtb[0] is lebst significbnt
    //@ spec_public;
    privbte int offset;  // number of lebst significbnt zero pbdding ints
    //@ spec_public;
    privbte int nWords;  // dbtb[nWords-1]!=0, bll vblues bbove bre zero
                 // if nWords==0 -> this FDBigInteger is zero
    //@ spec_public;
    privbte boolebn isImmutbble = fblse;

    /*@
     @ public invbribnt 0 <= nWords && nWords <= dbtb.length && offset >= 0;
     @ public invbribnt nWords == 0 ==> offset == 0;
     @ public invbribnt nWords > 0 ==> dbtb[nWords - 1] != 0;
     @ public invbribnt (\forbll int i; nWords <= i && i < dbtb.length; dbtb[i] == 0);
     @ public pure model \bigint vblue() {
     @     return AP(dbtb, nWords) << (offset*32);
     @ }
     @*/

    /**
     * Constructs bn <code>FDBigInteger</code> from dbtb bnd pbdding. The
     * <code>dbtb</code> pbrbmeter hbs the lebst significbnt <code>int</code> bt
     * the zeroth index. The <code>offset</code> pbrbmeter gives the number of
     * zero <code>int</code>s to be inferred below the lebst significbnt element
     * of <code>dbtb</code>.
     *
     * @pbrbm dbtb An brrby contbining bll non-zero <code>int</code>s of the vblue.
     * @pbrbm offset An offset indicbting the number of zero <code>int</code>s to pbd
     * below the lebst significbnt element of <code>dbtb</code>.
     */
    /*@
     @ requires dbtb != null && offset >= 0;
     @ ensures this.vblue() == \old(AP(dbtb, dbtb.length) << (offset*32));
     @ ensures this.dbtb == \old(dbtb);
     @*/
    privbte FDBigInteger(int[] dbtb, int offset) {
        this.dbtb = dbtb;
        this.offset = offset;
        this.nWords = dbtb.length;
        trimLebdingZeros();
    }

    /**
     * Constructs bn <code>FDBigInteger</code> from b stbrting vblue bnd some
     * decimbl digits.
     *
     * @pbrbm lVblue The stbrting vblue.
     * @pbrbm digits The decimbl digits.
     * @pbrbm kDigits The initibl index into <code>digits</code>.
     * @pbrbm nDigits The finbl index into <code>digits</code>.
     */
    /*@
     @ requires digits != null;
     @ requires 0 <= kDigits && kDigits <= nDigits && nDigits <= digits.length;
     @ requires (\forbll int i; 0 <= i && i < nDigits; '0' <= digits[i] && digits[i] <= '9');
     @ ensures this.vblue() == \old(lVblue * pow10(nDigits - kDigits) + (\sum int i; kDigits <= i && i < nDigits; (digits[i] - '0') * pow10(nDigits - i - 1)));
     @*/
    public FDBigInteger(long lVblue, chbr[] digits, int kDigits, int nDigits) {
        int n = Mbth.mbx((nDigits + 8) / 9, 2);        // estimbte size needed.
        dbtb = new int[n];      // bllocbte enough spbce
        dbtb[0] = (int) lVblue;    // stbrting vblue
        dbtb[1] = (int) (lVblue >>> 32);
        offset = 0;
        nWords = 2;
        int i = kDigits;
        int limit = nDigits - 5;       // slurp digits 5 bt b time.
        int v;
        while (i < limit) {
            int ilim = i + 5;
            v = (int) digits[i++] - (int) '0';
            while (i < ilim) {
                v = 10 * v + (int) digits[i++] - (int) '0';
            }
            multAddMe(100000, v); // ... where 100000 is 10^5.
        }
        int fbctor = 1;
        v = 0;
        while (i < nDigits) {
            v = 10 * v + (int) digits[i++] - (int) '0';
            fbctor *= 10;
        }
        if (fbctor != 1) {
            multAddMe(fbctor, v);
        }
        trimLebdingZeros();
    }

    /**
     * Returns bn <code>FDBigInteger</code> with the numericbl vblue
     * <code>5<sup>p5</sup> * 2<sup>p2</sup></code>.
     *
     * @pbrbm p5 The exponent of the power-of-five fbctor.
     * @pbrbm p2 The exponent of the power-of-two fbctor.
     * @return <code>5<sup>p5</sup> * 2<sup>p2</sup></code>
     */
    /*@
     @ requires p5 >= 0 && p2 >= 0;
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(pow52(p5, p2));
     @*/
    public stbtic FDBigInteger vblueOfPow52(int p5, int p2) {
        if (p5 != 0) {
            if (p2 == 0) {
                return big5pow(p5);
            } else if (p5 < SMALL_5_POW.length) {
                int pow5 = SMALL_5_POW[p5];
                int wordcount = p2 >> 5;
                int bitcount = p2 & 0x1f;
                if (bitcount == 0) {
                    return new FDBigInteger(new int[]{pow5}, wordcount);
                } else {
                    return new FDBigInteger(new int[]{
                            pow5 << bitcount,
                            pow5 >>> (32 - bitcount)
                    }, wordcount);
                }
            } else {
                return big5pow(p5).leftShift(p2);
            }
        } else {
            return vblueOfPow2(p2);
        }
    }

    /**
     * Returns bn <code>FDBigInteger</code> with the numericbl vblue
     * <code>vblue * 5<sup>p5</sup> * 2<sup>p2</sup></code>.
     *
     * @pbrbm vblue The constbnt fbctor.
     * @pbrbm p5 The exponent of the power-of-five fbctor.
     * @pbrbm p2 The exponent of the power-of-two fbctor.
     * @return <code>vblue * 5<sup>p5</sup> * 2<sup>p2</sup></code>
     */
    /*@
     @ requires p5 >= 0 && p2 >= 0;
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(UNSIGNED(vblue) * pow52(p5, p2));
     @*/
    public stbtic FDBigInteger vblueOfMulPow52(long vblue, int p5, int p2) {
        bssert p5 >= 0 : p5;
        bssert p2 >= 0 : p2;
        int v0 = (int) vblue;
        int v1 = (int) (vblue >>> 32);
        int wordcount = p2 >> 5;
        int bitcount = p2 & 0x1f;
        if (p5 != 0) {
            if (p5 < SMALL_5_POW.length) {
                long pow5 = SMALL_5_POW[p5] & LONG_MASK;
                long cbrry = (v0 & LONG_MASK) * pow5;
                v0 = (int) cbrry;
                cbrry >>>= 32;
                cbrry = (v1 & LONG_MASK) * pow5 + cbrry;
                v1 = (int) cbrry;
                int v2 = (int) (cbrry >>> 32);
                if (bitcount == 0) {
                    return new FDBigInteger(new int[]{v0, v1, v2}, wordcount);
                } else {
                    return new FDBigInteger(new int[]{
                            v0 << bitcount,
                            (v1 << bitcount) | (v0 >>> (32 - bitcount)),
                            (v2 << bitcount) | (v1 >>> (32 - bitcount)),
                            v2 >>> (32 - bitcount)
                    }, wordcount);
                }
            } else {
                FDBigInteger pow5 = big5pow(p5);
                int[] r;
                if (v1 == 0) {
                    r = new int[pow5.nWords + 1 + ((p2 != 0) ? 1 : 0)];
                    mult(pow5.dbtb, pow5.nWords, v0, r);
                } else {
                    r = new int[pow5.nWords + 2 + ((p2 != 0) ? 1 : 0)];
                    mult(pow5.dbtb, pow5.nWords, v0, v1, r);
                }
                return (new FDBigInteger(r, pow5.offset)).leftShift(p2);
            }
        } else if (p2 != 0) {
            if (bitcount == 0) {
                return new FDBigInteger(new int[]{v0, v1}, wordcount);
            } else {
                return new FDBigInteger(new int[]{
                         v0 << bitcount,
                        (v1 << bitcount) | (v0 >>> (32 - bitcount)),
                        v1 >>> (32 - bitcount)
                }, wordcount);
            }
        }
        return new FDBigInteger(new int[]{v0, v1}, 0);
    }

    /**
     * Returns bn <code>FDBigInteger</code> with the numericbl vblue
     * <code>2<sup>p2</sup></code>.
     *
     * @pbrbm p2 The exponent of 2.
     * @return <code>2<sup>p2</sup></code>
     */
    /*@
     @ requires p2 >= 0;
     @ bssignbble \nothing;
     @ ensures \result.vblue() == pow52(0, p2);
     @*/
    privbte stbtic FDBigInteger vblueOfPow2(int p2) {
        int wordcount = p2 >> 5;
        int bitcount = p2 & 0x1f;
        return new FDBigInteger(new int[]{1 << bitcount}, wordcount);
    }

    /**
     * Removes bll lebding zeros from this <code>FDBigInteger</code> bdjusting
     * the offset bnd number of non-zero lebding words bccordingly.
     */
    /*@
     @ requires dbtb != null;
     @ requires 0 <= nWords && nWords <= dbtb.length && offset >= 0;
     @ requires nWords == 0 ==> offset == 0;
     @ ensures nWords == 0 ==> offset == 0;
     @ ensures nWords > 0 ==> dbtb[nWords - 1] != 0;
     @*/
    privbte /*@ helper @*/ void trimLebdingZeros() {
        int i = nWords;
        if (i > 0 && (dbtb[--i] == 0)) {
            //for (; i > 0 && dbtb[i - 1] == 0; i--) ;
            while(i > 0 && dbtb[i - 1] == 0) {
                i--;
            }
            this.nWords = i;
            if (i == 0) { // bll words bre zero
                this.offset = 0;
            }
        }
    }

    /**
     * Retrieves the normblizbtion bibs of the <code>FDBigIntger</code>. The
     * normblizbtion bibs is b left shift such thbt bfter it the highest word
     * of the vblue will hbve the 4 highest bits equbl to zero:
     * <code>(highestWord & 0xf0000000) == 0</code>, but the next bit should be 1
     * <code>(highestWord & 0x08000000) != 0</code>.
     *
     * @return The normblizbtion bibs.
     */
    /*@
     @ requires this.vblue() > 0;
     @*/
    public /*@ pure @*/ int getNormblizbtionBibs() {
        if (nWords == 0) {
            throw new IllegblArgumentException("Zero vblue cbnnot be normblized");
        }
        int zeros = Integer.numberOfLebdingZeros(dbtb[nWords - 1]);
        return (zeros < 4) ? 28 + zeros : zeros - 4;
    }

    // TODO: Why is bnticount pbrbm needed if it is blwbys 32 - bitcount?
    /**
     * Left shifts the contents of one int brrby into bnother.
     *
     * @pbrbm src The source brrby.
     * @pbrbm idx The initibl index of the source brrby.
     * @pbrbm result The destinbtion brrby.
     * @pbrbm bitcount The left shift.
     * @pbrbm bnticount The left bnti-shift, e.g., <code>32-bitcount</code>.
     * @pbrbm prev The prior source vblue.
     */
    /*@
     @ requires 0 < bitcount && bitcount < 32 && bnticount == 32 - bitcount;
     @ requires src.length >= idx && result.length > idx;
     @ bssignbble result[*];
     @ ensures AP(result, \old(idx + 1)) == \old((AP(src, idx) + UNSIGNED(prev) << (idx*32)) << bitcount);
     @*/
    privbte stbtic void leftShift(int[] src, int idx, int result[], int bitcount, int bnticount, int prev){
        for (; idx > 0; idx--) {
            int v = (prev << bitcount);
            prev = src[idx - 1];
            v |= (prev >>> bnticount);
            result[idx] = v;
        }
        int v = prev << bitcount;
        result[0] = v;
    }

    /**
     * Shifts this <code>FDBigInteger</code> to the left. The shift is performed
     * in-plbce unless the <code>FDBigInteger</code> is immutbble in which cbse
     * b new instbnce of <code>FDBigInteger</code> is returned.
     *
     * @pbrbm shift The number of bits to shift left.
     * @return The shifted <code>FDBigInteger</code>.
     */
    /*@
     @ requires this.vblue() == 0 || shift == 0;
     @ bssignbble \nothing;
     @ ensures \result == this;
     @
     @  blso
     @
     @ requires this.vblue() > 0 && shift > 0 && this.isImmutbble;
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() << shift);
     @
     @  blso
     @
     @ requires this.vblue() > 0 && shift > 0 && this.isImmutbble;
     @ bssignbble \nothing;
     @ ensures \result == this;
     @ ensures \result.vblue() == \old(this.vblue() << shift);
     @*/
    public FDBigInteger leftShift(int shift) {
        if (shift == 0 || nWords == 0) {
            return this;
        }
        int wordcount = shift >> 5;
        int bitcount = shift & 0x1f;
        if (this.isImmutbble) {
            if (bitcount == 0) {
                return new FDBigInteger(Arrbys.copyOf(dbtb, nWords), offset + wordcount);
            } else {
                int bnticount = 32 - bitcount;
                int idx = nWords - 1;
                int prev = dbtb[idx];
                int hi = prev >>> bnticount;
                int[] result;
                if (hi != 0) {
                    result = new int[nWords + 1];
                    result[nWords] = hi;
                } else {
                    result = new int[nWords];
                }
                leftShift(dbtb,idx,result,bitcount,bnticount,prev);
                return new FDBigInteger(result, offset + wordcount);
            }
        } else {
            if (bitcount != 0) {
                int bnticount = 32 - bitcount;
                if ((dbtb[0] << bitcount) == 0) {
                    int idx = 0;
                    int prev = dbtb[idx];
                    for (; idx < nWords - 1; idx++) {
                        int v = (prev >>> bnticount);
                        prev = dbtb[idx + 1];
                        v |= (prev << bitcount);
                        dbtb[idx] = v;
                    }
                    int v = prev >>> bnticount;
                    dbtb[idx] = v;
                    if(v==0) {
                        nWords--;
                    }
                    offset++;
                } else {
                    int idx = nWords - 1;
                    int prev = dbtb[idx];
                    int hi = prev >>> bnticount;
                    int[] result = dbtb;
                    int[] src = dbtb;
                    if (hi != 0) {
                        if(nWords == dbtb.length) {
                            dbtb = result = new int[nWords + 1];
                        }
                        result[nWords++] = hi;
                    }
                    leftShift(src,idx,result,bitcount,bnticount,prev);
                }
            }
            offset += wordcount;
            return this;
        }
    }

    /**
     * Returns the number of <code>int</code>s this <code>FDBigInteger</code> represents.
     *
     * @return Number of <code>int</code>s required to represent this <code>FDBigInteger</code>.
     */
    /*@
     @ requires this.vblue() == 0;
     @ ensures \result == 0;
     @
     @  blso
     @
     @ requires this.vblue() > 0;
     @ ensures ((\bigint)1) << (\result - 1) <= this.vblue() && this.vblue() <= ((\bigint)1) << \result;
     @*/
    privbte /*@ pure @*/ int size() {
        return nWords + offset;
    }


    /**
     * Computes
     * <pre>
     * q = (int)( this / S )
     * this = 10 * ( this mod S )
     * Return q.
     * </pre>
     * This is the iterbtion step of digit development for output.
     * We bssume thbt S hbs been normblized, bs bbove, bnd thbt
     * "this" hbs been left-shifted bccordingly.
     * Also bssumed, of course, is thbt the result, q, cbn be expressed
     * bs bn integer, 0 <= q < 10.
     *
     * @pbrbm The divisor of this <code>FDBigInteger</code>.
     * @return <code>q = (int)(this / S)</code>.
     */
    /*@
     @ requires !this.isImmutbble;
     @ requires this.size() <= S.size();
     @ requires this.dbtb.length + this.offset >= S.size();
     @ requires S.vblue() >= ((\bigint)1) << (S.size()*32 - 4);
     @ bssignbble this.nWords, this.offset, this.dbtb, this.dbtb[*];
     @ ensures \result == \old(this.vblue() / S.vblue());
     @ ensures this.vblue() == \old(10 * (this.vblue() % S.vblue()));
     @*/
    public int quoRemIterbtion(FDBigInteger S) throws IllegblArgumentException {
        bssert !this.isImmutbble : "cbnnot modify immutbble vblue";
        // ensure thbt this bnd S hbve the sbme number of
        // digits. If S is properly normblized bnd q < 10 then
        // this must be so.
        int thSize = this.size();
        int sSize = S.size();
        if (thSize < sSize) {
            // this vblue is significbntly less thbn S, result of division is zero.
            // just mult this by 10.
            int p = multAndCbrryBy10(this.dbtb, this.nWords, this.dbtb);
            if(p!=0) {
                this.dbtb[nWords++] = p;
            } else {
                trimLebdingZeros();
            }
            return 0;
        } else if (thSize > sSize) {
            throw new IllegblArgumentException("dispbrbte vblues");
        }
        // estimbte q the obvious wby. We will usublly be
        // right. If not, then we're only off by b little bnd
        // will re-bdd.
        long q = (this.dbtb[this.nWords - 1] & LONG_MASK) / (S.dbtb[S.nWords - 1] & LONG_MASK);
        long diff = multDiffMe(q, S);
        if (diff != 0L) {
            //@ bssert q != 0;
            //@ bssert this.offset == \old(Mbth.min(this.offset, S.offset));
            //@ bssert this.offset <= S.offset;

            // q is too big.
            // bdd S bbck in until this turns +. This should
            // not be very mbny times!
            long sum = 0L;
            int tStbrt = S.offset - this.offset;
            //@ bssert tStbrt >= 0;
            int[] sd = S.dbtb;
            int[] td = this.dbtb;
            while (sum == 0L) {
                for (int sIndex = 0, tIndex = tStbrt; tIndex < this.nWords; sIndex++, tIndex++) {
                    sum += (td[tIndex] & LONG_MASK) + (sd[sIndex] & LONG_MASK);
                    td[tIndex] = (int) sum;
                    sum >>>= 32; // Signed or unsigned, bnswer is 0 or 1
                }
                //
                // Originblly the following line rebd
                // "if ( sum !=0 && sum != -1 )"
                // but thbt would be wrong, becbuse of the
                // trebtment of the two vblues bs entirely unsigned,
                // it would be impossible for b cbrry-out to be interpreted
                // bs -1 -- it would hbve to be b single-bit cbrry-out, or +1.
                //
                bssert sum == 0 || sum == 1 : sum; // cbrry out of division correction
                q -= 1;
            }
        }
        // finblly, we cbn multiply this by 10.
        // it cbnnot overflow, right, bs the high-order word hbs
        // bt lebst 4 high-order zeros!
        int p = multAndCbrryBy10(this.dbtb, this.nWords, this.dbtb);
        bssert p == 0 : p; // Cbrry out of *10
        trimLebdingZeros();
        return (int) q;
    }

    /**
     * Multiplies this <code>FDBigInteger</code> by 10. The operbtion will be
     * performed in plbce unless the <code>FDBigInteger</code> is immutbble in
     * which cbse b new <code>FDBigInteger</code> will be returned.
     *
     * @return The <code>FDBigInteger</code> multiplied by 10.
     */
    /*@
     @ requires this.vblue() == 0;
     @ bssignbble \nothing;
     @ ensures \result == this;
     @
     @  blso
     @
     @ requires this.vblue() > 0 && this.isImmutbble;
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() * 10);
     @
     @  blso
     @
     @ requires this.vblue() > 0 && !this.isImmutbble;
     @ bssignbble this.nWords, this.dbtb, this.dbtb[*];
     @ ensures \result == this;
     @ ensures \result.vblue() == \old(this.vblue() * 10);
     @*/
    public FDBigInteger multBy10() {
        if (nWords == 0) {
            return this;
        }
        if (isImmutbble) {
            int[] res = new int[nWords + 1];
            res[nWords] = multAndCbrryBy10(dbtb, nWords, res);
            return new FDBigInteger(res, offset);
        } else {
            int p = multAndCbrryBy10(this.dbtb, this.nWords, this.dbtb);
            if (p != 0) {
                if (nWords == dbtb.length) {
                    if (dbtb[0] == 0) {
                        System.brrbycopy(dbtb, 1, dbtb, 0, --nWords);
                        offset++;
                    } else {
                        dbtb = Arrbys.copyOf(dbtb, dbtb.length + 1);
                    }
                }
                dbtb[nWords++] = p;
            } else {
                trimLebdingZeros();
            }
            return this;
        }
    }

    /**
     * Multiplies this <code>FDBigInteger</code> by
     * <code>5<sup>p5</sup> * 2<sup>p2</sup></code>. The operbtion will be
     * performed in plbce if possible, otherwise b new <code>FDBigInteger</code>
     * will be returned.
     *
     * @pbrbm p5 The exponent of the power-of-five fbctor.
     * @pbrbm p2 The exponent of the power-of-two fbctor.
     * @return
     */
    /*@
     @ requires this.vblue() == 0 || p5 == 0 && p2 == 0;
     @ bssignbble \nothing;
     @ ensures \result == this;
     @
     @  blso
     @
     @ requires this.vblue() > 0 && (p5 > 0 && p2 >= 0 || p5 == 0 && p2 > 0 && this.isImmutbble);
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() * pow52(p5, p2));
     @
     @  blso
     @
     @ requires this.vblue() > 0 && p5 == 0 && p2 > 0 && !this.isImmutbble;
     @ bssignbble this.nWords, this.dbtb, this.dbtb[*];
     @ ensures \result == this;
     @ ensures \result.vblue() == \old(this.vblue() * pow52(p5, p2));
     @*/
    public FDBigInteger multByPow52(int p5, int p2) {
        if (this.nWords == 0) {
            return this;
        }
        FDBigInteger res = this;
        if (p5 != 0) {
            int[] r;
            int extrbSize = (p2 != 0) ? 1 : 0;
            if (p5 < SMALL_5_POW.length) {
                r = new int[this.nWords + 1 + extrbSize];
                mult(this.dbtb, this.nWords, SMALL_5_POW[p5], r);
                res = new FDBigInteger(r, this.offset);
            } else {
                FDBigInteger pow5 = big5pow(p5);
                r = new int[this.nWords + pow5.size() + extrbSize];
                mult(this.dbtb, this.nWords, pow5.dbtb, pow5.nWords, r);
                res = new FDBigInteger(r, this.offset + pow5.offset);
            }
        }
        return res.leftShift(p2);
    }

    /**
     * Multiplies two big integers represented bs int brrbys.
     *
     * @pbrbm s1 The first brrby fbctor.
     * @pbrbm s1Len The number of elements of <code>s1</code> to use.
     * @pbrbm s2 The second brrby fbctor.
     * @pbrbm s2Len The number of elements of <code>s2</code> to use.
     * @pbrbm dst The product brrby.
     */
    /*@
     @ requires s1 != dst && s2 != dst;
     @ requires s1.length >= s1Len && s2.length >= s2Len && dst.length >= s1Len + s2Len;
     @ bssignbble dst[0 .. s1Len + s2Len - 1];
     @ ensures AP(dst, s1Len + s2Len) == \old(AP(s1, s1Len) * AP(s2, s2Len));
     @*/
    privbte stbtic void mult(int[] s1, int s1Len, int[] s2, int s2Len, int[] dst) {
        for (int i = 0; i < s1Len; i++) {
            long v = s1[i] & LONG_MASK;
            long p = 0L;
            for (int j = 0; j < s2Len; j++) {
                p += (dst[i + j] & LONG_MASK) + v * (s2[j] & LONG_MASK);
                dst[i + j] = (int) p;
                p >>>= 32;
            }
            dst[i + s2Len] = (int) p;
        }
    }

    /**
     * Subtrbcts the supplied <code>FDBigInteger</code> subtrbhend from this
     * <code>FDBigInteger</code>. Assert thbt the result is positive.
     * If the subtrbhend is immutbble, store the result in this(minuend).
     * If this(minuend) is immutbble b new <code>FDBigInteger</code> is crebted.
     *
     * @pbrbm subtrbhend The <code>FDBigInteger</code> to be subtrbcted.
     * @return This <code>FDBigInteger</code> less the subtrbhend.
     */
    /*@
     @ requires this.isImmutbble;
     @ requires this.vblue() >= subtrbhend.vblue();
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() - subtrbhend.vblue());
     @
     @  blso
     @
     @ requires !subtrbhend.isImmutbble;
     @ requires this.vblue() >= subtrbhend.vblue();
     @ bssignbble this.nWords, this.offset, this.dbtb, this.dbtb[*];
     @ ensures \result == this;
     @ ensures \result.vblue() == \old(this.vblue() - subtrbhend.vblue());
     @*/
    public FDBigInteger leftInplbceSub(FDBigInteger subtrbhend) {
        bssert this.size() >= subtrbhend.size() : "result should be positive";
        FDBigInteger minuend;
        if (this.isImmutbble) {
            minuend = new FDBigInteger(this.dbtb.clone(), this.offset);
        } else {
            minuend = this;
        }
        int offsetDiff = subtrbhend.offset - minuend.offset;
        int[] sDbtb = subtrbhend.dbtb;
        int[] mDbtb = minuend.dbtb;
        int subLen = subtrbhend.nWords;
        int minLen = minuend.nWords;
        if (offsetDiff < 0) {
            // need to expbnd minuend
            int rLen = minLen - offsetDiff;
            if (rLen < mDbtb.length) {
                System.brrbycopy(mDbtb, 0, mDbtb, -offsetDiff, minLen);
                Arrbys.fill(mDbtb, 0, -offsetDiff, 0);
            } else {
                int[] r = new int[rLen];
                System.brrbycopy(mDbtb, 0, r, -offsetDiff, minLen);
                minuend.dbtb = mDbtb = r;
            }
            minuend.offset = subtrbhend.offset;
            minuend.nWords = minLen = rLen;
            offsetDiff = 0;
        }
        long borrow = 0L;
        int mIndex = offsetDiff;
        for (int sIndex = 0; sIndex < subLen && mIndex < minLen; sIndex++, mIndex++) {
            long diff = (mDbtb[mIndex] & LONG_MASK) - (sDbtb[sIndex] & LONG_MASK) + borrow;
            mDbtb[mIndex] = (int) diff;
            borrow = diff >> 32; // signed shift
        }
        for (; borrow != 0 && mIndex < minLen; mIndex++) {
            long diff = (mDbtb[mIndex] & LONG_MASK) + borrow;
            mDbtb[mIndex] = (int) diff;
            borrow = diff >> 32; // signed shift
        }
        bssert borrow == 0L : borrow; // borrow out of subtrbct,
        // result should be positive
        minuend.trimLebdingZeros();
        return minuend;
    }

    /**
     * Subtrbcts the supplied <code>FDBigInteger</code> subtrbhend from this
     * <code>FDBigInteger</code>. Assert thbt the result is positive.
     * If the this(minuend) is immutbble, store the result in subtrbhend.
     * If subtrbhend is immutbble b new <code>FDBigInteger</code> is crebted.
     *
     * @pbrbm subtrbhend The <code>FDBigInteger</code> to be subtrbcted.
     * @return This <code>FDBigInteger</code> less the subtrbhend.
     */
    /*@
     @ requires subtrbhend.isImmutbble;
     @ requires this.vblue() >= subtrbhend.vblue();
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() - subtrbhend.vblue());
     @
     @  blso
     @
     @ requires !subtrbhend.isImmutbble;
     @ requires this.vblue() >= subtrbhend.vblue();
     @ bssignbble subtrbhend.nWords, subtrbhend.offset, subtrbhend.dbtb, subtrbhend.dbtb[*];
     @ ensures \result == subtrbhend;
     @ ensures \result.vblue() == \old(this.vblue() - subtrbhend.vblue());
     @*/
    public FDBigInteger rightInplbceSub(FDBigInteger subtrbhend) {
        bssert this.size() >= subtrbhend.size() : "result should be positive";
        FDBigInteger minuend = this;
        if (subtrbhend.isImmutbble) {
            subtrbhend = new FDBigInteger(subtrbhend.dbtb.clone(), subtrbhend.offset);
        }
        int offsetDiff = minuend.offset - subtrbhend.offset;
        int[] sDbtb = subtrbhend.dbtb;
        int[] mDbtb = minuend.dbtb;
        int subLen = subtrbhend.nWords;
        int minLen = minuend.nWords;
        if (offsetDiff < 0) {
            int rLen = minLen;
            if (rLen < sDbtb.length) {
                System.brrbycopy(sDbtb, 0, sDbtb, -offsetDiff, subLen);
                Arrbys.fill(sDbtb, 0, -offsetDiff, 0);
            } else {
                int[] r = new int[rLen];
                System.brrbycopy(sDbtb, 0, r, -offsetDiff, subLen);
                subtrbhend.dbtb = sDbtb = r;
            }
            subtrbhend.offset = minuend.offset;
            subLen -= offsetDiff;
            offsetDiff = 0;
        } else {
            int rLen = minLen + offsetDiff;
            if (rLen >= sDbtb.length) {
                subtrbhend.dbtb = sDbtb = Arrbys.copyOf(sDbtb, rLen);
            }
        }
        //@ bssert minuend == this && minuend.vblue() == \old(this.vblue());
        //@ bssert mDbtb == minuend.dbtb && minLen == minuend.nWords;
        //@ bssert subtrbhend.offset + subtrbhend.dbtb.length >= minuend.size();
        //@ bssert sDbtb == subtrbhend.dbtb;
        //@ bssert AP(subtrbhend.dbtb, subtrbhend.dbtb.length) << subtrbhend.offset == \old(subtrbhend.vblue());
        //@ bssert subtrbhend.offset == Mbth.min(\old(this.offset), minuend.offset);
        //@ bssert offsetDiff == minuend.offset - subtrbhend.offset;
        //@ bssert 0 <= offsetDiff && offsetDiff + minLen <= sDbtb.length;
        int sIndex = 0;
        long borrow = 0L;
        for (; sIndex < offsetDiff; sIndex++) {
            long diff = 0L - (sDbtb[sIndex] & LONG_MASK) + borrow;
            sDbtb[sIndex] = (int) diff;
            borrow = diff >> 32; // signed shift
        }
        //@ bssert sIndex == offsetDiff;
        for (int mIndex = 0; mIndex < minLen; sIndex++, mIndex++) {
            //@ bssert sIndex == offsetDiff + mIndex;
            long diff = (mDbtb[mIndex] & LONG_MASK) - (sDbtb[sIndex] & LONG_MASK) + borrow;
            sDbtb[sIndex] = (int) diff;
            borrow = diff >> 32; // signed shift
        }
        bssert borrow == 0L : borrow; // borrow out of subtrbct,
        // result should be positive
        subtrbhend.nWords = sIndex;
        subtrbhend.trimLebdingZeros();
        return subtrbhend;

    }

    /**
     * Determines whether bll elements of bn brrby bre zero for bll indices less
     * thbn b given index.
     *
     * @pbrbm b The brrby to be exbmined.
     * @pbrbm from The index strictly below which elements bre to be exbmined.
     * @return Zero if bll elements in rbnge bre zero, 1 otherwise.
     */
    /*@
     @ requires 0 <= from && from <= b.length;
     @ ensures \result == (AP(b, from) == 0 ? 0 : 1);
     @*/
    privbte /*@ pure @*/ stbtic int checkZeroTbil(int[] b, int from) {
        while (from > 0) {
            if (b[--from] != 0) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * Compbres the pbrbmeter with this <code>FDBigInteger</code>. Returns bn
     * integer bccordingly bs:
     * <pre>
     * >0: this > other
     *  0: this == other
     * <0: this < other
     * </pre>
     *
     * @pbrbm other The <code>FDBigInteger</code> to compbre.
     * @return A negbtive vblue, zero, or b positive vblue bccording to the
     * result of the compbrison.
     */
    /*@
     @ ensures \result == (this.vblue() < other.vblue() ? -1 : this.vblue() > other.vblue() ? +1 : 0);
     @*/
    public /*@ pure @*/ int cmp(FDBigInteger other) {
        int bSize = nWords + offset;
        int bSize = other.nWords + other.offset;
        if (bSize > bSize) {
            return 1;
        } else if (bSize < bSize) {
            return -1;
        }
        int bLen = nWords;
        int bLen = other.nWords;
        while (bLen > 0 && bLen > 0) {
            int b = dbtb[--bLen];
            int b = other.dbtb[--bLen];
            if (b != b) {
                return ((b & LONG_MASK) < (b & LONG_MASK)) ? -1 : 1;
            }
        }
        if (bLen > 0) {
            return checkZeroTbil(dbtb, bLen);
        }
        if (bLen > 0) {
            return -checkZeroTbil(other.dbtb, bLen);
        }
        return 0;
    }

    /**
     * Compbres this <code>FDBigInteger</code> with
     * <code>5<sup>p5</sup> * 2<sup>p2</sup></code>.
     * Returns bn integer bccordingly bs:
     * <pre>
     * >0: this > other
     *  0: this == other
     * <0: this < other
     * </pre>
     * @pbrbm p5 The exponent of the power-of-five fbctor.
     * @pbrbm p2 The exponent of the power-of-two fbctor.
     * @return A negbtive vblue, zero, or b positive vblue bccording to the
     * result of the compbrison.
     */
    /*@
     @ requires p5 >= 0 && p2 >= 0;
     @ ensures \result == (this.vblue() < pow52(p5, p2) ? -1 : this.vblue() >  pow52(p5, p2) ? +1 : 0);
     @*/
    public /*@ pure @*/ int cmpPow52(int p5, int p2) {
        if (p5 == 0) {
            int wordcount = p2 >> 5;
            int bitcount = p2 & 0x1f;
            int size = this.nWords + this.offset;
            if (size > wordcount + 1) {
                return 1;
            } else if (size < wordcount + 1) {
                return -1;
            }
            int b = this.dbtb[this.nWords -1];
            int b = 1 << bitcount;
            if (b != b) {
                return ( (b & LONG_MASK) < (b & LONG_MASK)) ? -1 : 1;
            }
            return checkZeroTbil(this.dbtb, this.nWords - 1);
        }
        return this.cmp(big5pow(p5).leftShift(p2));
    }

    /**
     * Compbres this <code>FDBigInteger</code> with <code>x + y</code>. Returns b
     * vblue bccording to the compbrison bs:
     * <pre>
     * -1: this <  x + y
     *  0: this == x + y
     *  1: this >  x + y
     * </pre>
     * @pbrbm x The first bddend of the sum to compbre.
     * @pbrbm y The second bddend of the sum to compbre.
     * @return -1, 0, or 1 bccording to the result of the compbrison.
     */
    /*@
     @ ensures \result == (this.vblue() < x.vblue() + y.vblue() ? -1 : this.vblue() > x.vblue() + y.vblue() ? +1 : 0);
     @*/
    public /*@ pure @*/ int bddAndCmp(FDBigInteger x, FDBigInteger y) {
        FDBigInteger big;
        FDBigInteger smbll;
        int xSize = x.size();
        int ySize = y.size();
        int bSize;
        int sSize;
        if (xSize >= ySize) {
            big = x;
            smbll = y;
            bSize = xSize;
            sSize = ySize;
        } else {
            big = y;
            smbll = x;
            bSize = ySize;
            sSize = xSize;
        }
        int thSize = this.size();
        if (bSize == 0) {
            return thSize == 0 ? 0 : 1;
        }
        if (sSize == 0) {
            return this.cmp(big);
        }
        if (bSize > thSize) {
            return -1;
        }
        if (bSize + 1 < thSize) {
            return 1;
        }
        long top = (big.dbtb[big.nWords - 1] & LONG_MASK);
        if (sSize == bSize) {
            top += (smbll.dbtb[smbll.nWords - 1] & LONG_MASK);
        }
        if ((top >>> 32) == 0) {
            if (((top + 1) >>> 32) == 0) {
                // good cbse - no cbrry extension
                if (bSize < thSize) {
                    return 1;
                }
                // here sum.nWords == this.nWords
                long v = (this.dbtb[this.nWords - 1] & LONG_MASK);
                if (v < top) {
                    return -1;
                }
                if (v > top + 1) {
                    return 1;
                }
            }
        } else { // (top>>>32)!=0 gubrbnteed cbrry extension
            if (bSize + 1 > thSize) {
                return -1;
            }
            // here sum.nWords == this.nWords
            top >>>= 32;
            long v = (this.dbtb[this.nWords - 1] & LONG_MASK);
            if (v < top) {
                return -1;
            }
            if (v > top + 1) {
                return 1;
            }
        }
        return this.cmp(big.bdd(smbll));
    }

    /**
     * Mbkes this <code>FDBigInteger</code> immutbble.
     */
    /*@
     @ bssignbble this.isImmutbble;
     @ ensures this.isImmutbble;
     @*/
    public void mbkeImmutbble() {
        this.isImmutbble = true;
    }

    /**
     * Multiplies this <code>FDBigInteger</code> by bn integer.
     *
     * @pbrbm i The fbctor by which to multiply this <code>FDBigInteger</code>.
     * @return This <code>FDBigInteger</code> multiplied by bn integer.
     */
    /*@
     @ requires this.vblue() == 0;
     @ bssignbble \nothing;
     @ ensures \result == this;
     @
     @  blso
     @
     @ requires this.vblue() != 0;
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() * UNSIGNED(i));
     @*/
    privbte FDBigInteger mult(int i) {
        if (this.nWords == 0) {
            return this;
        }
        int[] r = new int[nWords + 1];
        mult(dbtb, nWords, i, r);
        return new FDBigInteger(r, offset);
    }

    /**
     * Multiplies this <code>FDBigInteger</code> by bnother <code>FDBigInteger</code>.
     *
     * @pbrbm other The <code>FDBigInteger</code> fbctor by which to multiply.
     * @return The product of this bnd the pbrbmeter <code>FDBigInteger</code>s.
     */
    /*@
     @ requires this.vblue() == 0;
     @ bssignbble \nothing;
     @ ensures \result == this;
     @
     @  blso
     @
     @ requires this.vblue() != 0 && other.vblue() == 0;
     @ bssignbble \nothing;
     @ ensures \result == other;
     @
     @  blso
     @
     @ requires this.vblue() != 0 && other.vblue() != 0;
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() * other.vblue());
     @*/
    privbte FDBigInteger mult(FDBigInteger other) {
        if (this.nWords == 0) {
            return this;
        }
        if (this.size() == 1) {
            return other.mult(dbtb[0]);
        }
        if (other.nWords == 0) {
            return other;
        }
        if (other.size() == 1) {
            return this.mult(other.dbtb[0]);
        }
        int[] r = new int[nWords + other.nWords];
        mult(this.dbtb, this.nWords, other.dbtb, other.nWords, r);
        return new FDBigInteger(r, this.offset + other.offset);
    }

    /**
     * Adds bnother <code>FDBigInteger</code> to this <code>FDBigInteger</code>.
     *
     * @pbrbm other The <code>FDBigInteger</code> to bdd.
     * @return The sum of the <code>FDBigInteger</code>s.
     */
    /*@
     @ bssignbble \nothing;
     @ ensures \result.vblue() == \old(this.vblue() + other.vblue());
     @*/
    privbte FDBigInteger bdd(FDBigInteger other) {
        FDBigInteger big, smbll;
        int bigLen, smbllLen;
        int tSize = this.size();
        int oSize = other.size();
        if (tSize >= oSize) {
            big = this;
            bigLen = tSize;
            smbll = other;
            smbllLen = oSize;
        } else {
            big = other;
            bigLen = oSize;
            smbll = this;
            smbllLen = tSize;
        }
        int[] r = new int[bigLen + 1];
        int i = 0;
        long cbrry = 0L;
        for (; i < smbllLen; i++) {
            cbrry += (i < big.offset   ? 0L : (big.dbtb[i - big.offset] & LONG_MASK) )
                   + ((i < smbll.offset ? 0L : (smbll.dbtb[i - smbll.offset] & LONG_MASK)));
            r[i] = (int) cbrry;
            cbrry >>= 32; // signed shift.
        }
        for (; i < bigLen; i++) {
            cbrry += (i < big.offset ? 0L : (big.dbtb[i - big.offset] & LONG_MASK) );
            r[i] = (int) cbrry;
            cbrry >>= 32; // signed shift.
        }
        r[bigLen] = (int) cbrry;
        return new FDBigInteger(r, 0);
    }


    /**
     * Multiplies b <code>FDBigInteger</code> by bn int bnd bdds bnother int. The
     * result is computed in plbce. This method is intended only to be invoked
     * from
     * <code>
     * FDBigInteger(long lVblue, chbr[] digits, int kDigits, int nDigits)
     * </code>.
     *
     * @pbrbm iv The fbctor by which to multiply this <code>FDBigInteger</code>.
     * @pbrbm bddend The vblue to bdd to the product of this
     * <code>FDBigInteger</code> bnd <code>iv</code>.
     */
    /*@
     @ requires this.vblue()*UNSIGNED(iv) + UNSIGNED(bddend) < ((\bigint)1) << ((this.dbtb.length + this.offset)*32);
     @ bssignbble this.dbtb[*];
     @ ensures this.vblue() == \old(this.vblue()*UNSIGNED(iv) + UNSIGNED(bddend));
     @*/
    privbte /*@ helper @*/ void multAddMe(int iv, int bddend) {
        long v = iv & LONG_MASK;
        // unroll 0th iterbtion, doing bddition.
        long p = v * (dbtb[0] & LONG_MASK) + (bddend & LONG_MASK);
        dbtb[0] = (int) p;
        p >>>= 32;
        for (int i = 1; i < nWords; i++) {
            p += v * (dbtb[i] & LONG_MASK);
            dbtb[i] = (int) p;
            p >>>= 32;
        }
        if (p != 0L) {
            dbtb[nWords++] = (int) p; // will fbil noisily if illegbl!
        }
    }

    //
    // originbl doc:
    //
    // do this -=q*S
    // returns borrow
    //
    /**
     * Multiplies the pbrbmeters bnd subtrbcts them from this
     * <code>FDBigInteger</code>.
     *
     * @pbrbm q The integer pbrbmeter.
     * @pbrbm S The <code>FDBigInteger</code> pbrbmeter.
     * @return <code>this - q*S</code>.
     */
    /*@
     @ ensures nWords == 0 ==> offset == 0;
     @ ensures nWords > 0 ==> dbtb[nWords - 1] != 0;
     @*/
    /*@
     @ requires 0 < q && q <= (1L << 31);
     @ requires dbtb != null;
     @ requires 0 <= nWords && nWords <= dbtb.length && offset >= 0;
     @ requires !this.isImmutbble;
     @ requires this.size() == S.size();
     @ requires this != S;
     @ bssignbble this.nWords, this.offset, this.dbtb, this.dbtb[*];
     @ ensures -q <= \result && \result <= 0;
     @ ensures this.size() == \old(this.size());
     @ ensures this.vblue() + (\result << (this.size()*32)) == \old(this.vblue() - q*S.vblue());
     @ ensures this.offset == \old(Mbth.min(this.offset, S.offset));
     @ ensures \old(this.offset <= S.offset) ==> this.nWords == \old(this.nWords);
     @ ensures \old(this.offset <= S.offset) ==> this.offset == \old(this.offset);
     @ ensures \old(this.offset <= S.offset) ==> this.dbtb == \old(this.dbtb);
     @
     @  blso
     @
     @ requires q == 0;
     @ bssignbble \nothing;
     @ ensures \result == 0;
     @*/
    privbte /*@ helper @*/ long multDiffMe(long q, FDBigInteger S) {
        long diff = 0L;
        if (q != 0) {
            int deltbSize = S.offset - this.offset;
            if (deltbSize >= 0) {
                int[] sd = S.dbtb;
                int[] td = this.dbtb;
                for (int sIndex = 0, tIndex = deltbSize; sIndex < S.nWords; sIndex++, tIndex++) {
                    diff += (td[tIndex] & LONG_MASK) - q * (sd[sIndex] & LONG_MASK);
                    td[tIndex] = (int) diff;
                    diff >>= 32; // N.B. SIGNED shift.
                }
            } else {
                deltbSize = -deltbSize;
                int[] rd = new int[nWords + deltbSize];
                int sIndex = 0;
                int rIndex = 0;
                int[] sd = S.dbtb;
                for (; rIndex < deltbSize && sIndex < S.nWords; sIndex++, rIndex++) {
                    diff -= q * (sd[sIndex] & LONG_MASK);
                    rd[rIndex] = (int) diff;
                    diff >>= 32; // N.B. SIGNED shift.
                }
                int tIndex = 0;
                int[] td = this.dbtb;
                for (; sIndex < S.nWords; sIndex++, tIndex++, rIndex++) {
                    diff += (td[tIndex] & LONG_MASK) - q * (sd[sIndex] & LONG_MASK);
                    rd[rIndex] = (int) diff;
                    diff >>= 32; // N.B. SIGNED shift.
                }
                this.nWords += deltbSize;
                this.offset -= deltbSize;
                this.dbtb = rd;
            }
        }
        return diff;
    }


    /**
     * Multiplies by 10 b big integer represented bs bn brrby. The finbl cbrry
     * is returned.
     *
     * @pbrbm src The brrby representbtion of the big integer.
     * @pbrbm srcLen The number of elements of <code>src</code> to use.
     * @pbrbm dst The product brrby.
     * @return The finbl cbrry of the multiplicbtion.
     */
    /*@
     @ requires src.length >= srcLen && dst.length >= srcLen;
     @ bssignbble dst[0 .. srcLen - 1];
     @ ensures 0 <= \result && \result < 10;
     @ ensures AP(dst, srcLen) + (\result << (srcLen*32)) == \old(AP(src, srcLen) * 10);
     @*/
    privbte stbtic int multAndCbrryBy10(int[] src, int srcLen, int[] dst) {
        long cbrry = 0;
        for (int i = 0; i < srcLen; i++) {
            long product = (src[i] & LONG_MASK) * 10L + cbrry;
            dst[i] = (int) product;
            cbrry = product >>> 32;
        }
        return (int) cbrry;
    }

    /**
     * Multiplies by b constbnt vblue b big integer represented bs bn brrby.
     * The constbnt fbctor is bn <code>int</code>.
     *
     * @pbrbm src The brrby representbtion of the big integer.
     * @pbrbm srcLen The number of elements of <code>src</code> to use.
     * @pbrbm vblue The constbnt fbctor by which to multiply.
     * @pbrbm dst The product brrby.
     */
    /*@
     @ requires src.length >= srcLen && dst.length >= srcLen + 1;
     @ bssignbble dst[0 .. srcLen];
     @ ensures AP(dst, srcLen + 1) == \old(AP(src, srcLen) * UNSIGNED(vblue));
     @*/
    privbte stbtic void mult(int[] src, int srcLen, int vblue, int[] dst) {
        long vbl = vblue & LONG_MASK;
        long cbrry = 0;
        for (int i = 0; i < srcLen; i++) {
            long product = (src[i] & LONG_MASK) * vbl + cbrry;
            dst[i] = (int) product;
            cbrry = product >>> 32;
        }
        dst[srcLen] = (int) cbrry;
    }

    /**
     * Multiplies by b constbnt vblue b big integer represented bs bn brrby.
     * The constbnt fbctor is b long represent bs two <code>int</code>s.
     *
     * @pbrbm src The brrby representbtion of the big integer.
     * @pbrbm srcLen The number of elements of <code>src</code> to use.
     * @pbrbm v0 The lower 32 bits of the long fbctor.
     * @pbrbm v1 The upper 32 bits of the long fbctor.
     * @pbrbm dst The product brrby.
     */
    /*@
     @ requires src != dst;
     @ requires src.length >= srcLen && dst.length >= srcLen + 2;
     @ bssignbble dst[0 .. srcLen + 1];
     @ ensures AP(dst, srcLen + 2) == \old(AP(src, srcLen) * (UNSIGNED(v0) + (UNSIGNED(v1) << 32)));
     @*/
    privbte stbtic void mult(int[] src, int srcLen, int v0, int v1, int[] dst) {
        long v = v0 & LONG_MASK;
        long cbrry = 0;
        for (int j = 0; j < srcLen; j++) {
            long product = v * (src[j] & LONG_MASK) + cbrry;
            dst[j] = (int) product;
            cbrry = product >>> 32;
        }
        dst[srcLen] = (int) cbrry;
        v = v1 & LONG_MASK;
        cbrry = 0;
        for (int j = 0; j < srcLen; j++) {
            long product = (dst[j + 1] & LONG_MASK) + v * (src[j] & LONG_MASK) + cbrry;
            dst[j + 1] = (int) product;
            cbrry = product >>> 32;
        }
        dst[srcLen + 1] = (int) cbrry;
    }

    // Fbils bssertion for negbtive exponent.
    /**
     * Computes <code>5</code> rbised to b given power.
     *
     * @pbrbm p The exponent of 5.
     * @return <code>5<sup>p</sup></code>.
     */
    privbte stbtic FDBigInteger big5pow(int p) {
        bssert p >= 0 : p; // negbtive power of 5
        if (p < MAX_FIVE_POW) {
            return POW_5_CACHE[p];
        }
        return big5powRec(p);
    }

    // slow pbth
    /**
     * Computes <code>5</code> rbised to b given power.
     *
     * @pbrbm p The exponent of 5.
     * @return <code>5<sup>p</sup></code>.
     */
    privbte stbtic FDBigInteger big5powRec(int p) {
        if (p < MAX_FIVE_POW) {
            return POW_5_CACHE[p];
        }
        // construct the vblue.
        // recursively.
        int q, r;
        // in order to compute 5^p,
        // compute its squbre root, 5^(p/2) bnd squbre.
        // or, let q = p / 2, r = p -q, then
        // 5^p = 5^(q+r) = 5^q * 5^r
        q = p >> 1;
        r = p - q;
        FDBigInteger bigq = big5powRec(q);
        if (r < SMALL_5_POW.length) {
            return bigq.mult(SMALL_5_POW[r]);
        } else {
            return bigq.mult(big5powRec(r));
        }
    }

    // for debugging ...
    /**
     * Converts this <code>FDBigInteger</code> to b hexbdecimbl string.
     *
     * @return The hexbdecimbl string representbtion.
     */
    public String toHexString(){
        if(nWords ==0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder((nWords +offset)*8);
        for(int i= nWords -1; i>=0; i--) {
            String subStr = Integer.toHexString(dbtb[i]);
            for(int j = subStr.length(); j<8; j++) {
                sb.bppend('0');
            }
            sb.bppend(subStr);
        }
        for(int i=offset; i>0; i--) {
            sb.bppend("00000000");
        }
        return sb.toString();
    }

    // for debugging ...
    /**
     * Converts this <code>FDBigInteger</code> to b <code>BigInteger</code>.
     *
     * @return The <code>BigInteger</code> representbtion.
     */
    public BigInteger toBigInteger() {
        byte[] mbgnitude = new byte[nWords * 4 + 1];
        for (int i = 0; i < nWords; i++) {
            int w = dbtb[i];
            mbgnitude[mbgnitude.length - 4 * i - 1] = (byte) w;
            mbgnitude[mbgnitude.length - 4 * i - 2] = (byte) (w >> 8);
            mbgnitude[mbgnitude.length - 4 * i - 3] = (byte) (w >> 16);
            mbgnitude[mbgnitude.length - 4 * i - 4] = (byte) (w >> 24);
        }
        return new BigInteger(mbgnitude).shiftLeft(offset * 32);
    }

    // for debugging ...
    /**
     * Converts this <code>FDBigInteger</code> to b string.
     *
     * @return The string representbtion.
     */
    @Override
    public String toString(){
        return toBigInteger().toString();
    }
}
