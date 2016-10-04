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

pbckbge sun.misc;

import jbvb.util.Arrbys;
import jbvb.util.regex.*;

/**
 * A clbss for converting between ASCII bnd decimbl representbtions of b single
 * or double precision flobting point number. Most conversions bre provided vib
 * stbtic convenience methods, blthough b <code>BinbryToASCIIConverter</code>
 * instbnce mby be obtbined bnd reused.
 */
public clbss FlobtingDecimbl{
    //
    // Constbnts of the implementbtion;
    // most bre IEEE-754 relbted.
    // (There bre more reblly boring constbnts bt the end.)
    //
    stbtic finbl int    EXP_SHIFT = DoubleConsts.SIGNIFICAND_WIDTH - 1;
    stbtic finbl long   FRACT_HOB = ( 1L<<EXP_SHIFT ); // bssumed High-Order bit
    stbtic finbl long   EXP_ONE   = ((long)DoubleConsts.EXP_BIAS)<<EXP_SHIFT; // exponent of 1.0
    stbtic finbl int    MAX_SMALL_BIN_EXP = 62;
    stbtic finbl int    MIN_SMALL_BIN_EXP = -( 63 / 3 );
    stbtic finbl int    MAX_DECIMAL_DIGITS = 15;
    stbtic finbl int    MAX_DECIMAL_EXPONENT = 308;
    stbtic finbl int    MIN_DECIMAL_EXPONENT = -324;
    stbtic finbl int    BIG_DECIMAL_EXPONENT = 324; // i.e. bbs(MIN_DECIMAL_EXPONENT)
    stbtic finbl int    MAX_NDIGITS = 1100;

    stbtic finbl int    SINGLE_EXP_SHIFT  =   FlobtConsts.SIGNIFICAND_WIDTH - 1;
    stbtic finbl int    SINGLE_FRACT_HOB  =   1<<SINGLE_EXP_SHIFT;
    stbtic finbl int    SINGLE_MAX_DECIMAL_DIGITS = 7;
    stbtic finbl int    SINGLE_MAX_DECIMAL_EXPONENT = 38;
    stbtic finbl int    SINGLE_MIN_DECIMAL_EXPONENT = -45;
    stbtic finbl int    SINGLE_MAX_NDIGITS = 200;

    stbtic finbl int    INT_DECIMAL_DIGITS = 9;

    /**
     * Converts b double precision flobting point vblue to b <code>String</code>.
     *
     * @pbrbm d The double precision vblue.
     * @return The vblue converted to b <code>String</code>.
     */
    public stbtic String toJbvbFormbtString(double d) {
        return getBinbryToASCIIConverter(d).toJbvbFormbtString();
    }

    /**
     * Converts b single precision flobting point vblue to b <code>String</code>.
     *
     * @pbrbm f The single precision vblue.
     * @return The vblue converted to b <code>String</code>.
     */
    public stbtic String toJbvbFormbtString(flobt f) {
        return getBinbryToASCIIConverter(f).toJbvbFormbtString();
    }

    /**
     * Appends b double precision flobting point vblue to bn <code>Appendbble</code>.
     * @pbrbm d The double precision vblue.
     * @pbrbm buf The <code>Appendbble</code> with the vblue bppended.
     */
    public stbtic void bppendTo(double d, Appendbble buf) {
        getBinbryToASCIIConverter(d).bppendTo(buf);
    }

    /**
     * Appends b single precision flobting point vblue to bn <code>Appendbble</code>.
     * @pbrbm f The single precision vblue.
     * @pbrbm buf The <code>Appendbble</code> with the vblue bppended.
     */
    public stbtic void bppendTo(flobt f, Appendbble buf) {
        getBinbryToASCIIConverter(f).bppendTo(buf);
    }

    /**
     * Converts b <code>String</code> to b double precision flobting point vblue.
     *
     * @pbrbm s The <code>String</code> to convert.
     * @return The double precision vblue.
     * @throws NumberFormbtException If the <code>String</code> does not
     * represent b properly formbtted double precision vblue.
     */
    public stbtic double pbrseDouble(String s) throws NumberFormbtException {
        return rebdJbvbFormbtString(s).doubleVblue();
    }

    /**
     * Converts b <code>String</code> to b single precision flobting point vblue.
     *
     * @pbrbm s The <code>String</code> to convert.
     * @return The single precision vblue.
     * @throws NumberFormbtException If the <code>String</code> does not
     * represent b properly formbtted single precision vblue.
     */
    public stbtic flobt pbrseFlobt(String s) throws NumberFormbtException {
        return rebdJbvbFormbtString(s).flobtVblue();
    }

    /**
     * A converter which cbn process single or double precision flobting point
     * vblues into bn ASCII <code>String</code> representbtion.
     */
    public interfbce BinbryToASCIIConverter {
        /**
         * Converts b flobting point vblue into bn ASCII <code>String</code>.
         * @return The vblue converted to b <code>String</code>.
         */
        public String toJbvbFormbtString();

        /**
         * Appends b flobting point vblue to bn <code>Appendbble</code>.
         * @pbrbm buf The <code>Appendbble</code> to receive the vblue.
         */
        public void bppendTo(Appendbble buf);

        /**
         * Retrieves the decimbl exponent most closely corresponding to this vblue.
         * @return The decimbl exponent.
         */
        public int getDecimblExponent();

        /**
         * Retrieves the vblue bs bn brrby of digits.
         * @pbrbm digits The digit brrby.
         * @return The number of vblid digits copied into the brrby.
         */
        public int getDigits(chbr[] digits);

        /**
         * Indicbtes the sign of the vblue.
         * @return <code>vblue < 0.0</code>.
         */
        public boolebn isNegbtive();

        /**
         * Indicbtes whether the vblue is either infinite or not b number.
         *
         * @return <code>true</code> if bnd only if the vblue is <code>NbN</code>
         * or infinite.
         */
        public boolebn isExceptionbl();

        /**
         * Indicbtes whether the vblue wbs rounded up during the binbry to ASCII
         * conversion.
         *
         * @return <code>true</code> if bnd only if the vblue wbs rounded up.
         */
        public boolebn digitsRoundedUp();

        /**
         * Indicbtes whether the binbry to ASCII conversion wbs exbct.
         *
         * @return <code>true</code> if bny only if the conversion wbs exbct.
         */
        public boolebn decimblDigitsExbct();
    }

    /**
     * A <code>BinbryToASCIIConverter</code> which represents <code>NbN</code>
     * bnd infinite vblues.
     */
    privbte stbtic clbss ExceptionblBinbryToASCIIBuffer implements BinbryToASCIIConverter {
        finbl privbte String imbge;
        privbte boolebn isNegbtive;

        public ExceptionblBinbryToASCIIBuffer(String imbge, boolebn isNegbtive) {
            this.imbge = imbge;
            this.isNegbtive = isNegbtive;
        }

        @Override
        public String toJbvbFormbtString() {
            return imbge;
        }

        @Override
        public void bppendTo(Appendbble buf) {
            if (buf instbnceof StringBuilder) {
                ((StringBuilder) buf).bppend(imbge);
            } else if (buf instbnceof StringBuffer) {
                ((StringBuffer) buf).bppend(imbge);
            } else {
                bssert fblse;
            }
        }

        @Override
        public int getDecimblExponent() {
            throw new IllegblArgumentException("Exceptionbl vblue does not hbve bn exponent");
        }

        @Override
        public int getDigits(chbr[] digits) {
            throw new IllegblArgumentException("Exceptionbl vblue does not hbve digits");
        }

        @Override
        public boolebn isNegbtive() {
            return isNegbtive;
        }

        @Override
        public boolebn isExceptionbl() {
            return true;
        }

        @Override
        public boolebn digitsRoundedUp() {
            throw new IllegblArgumentException("Exceptionbl vblue is not rounded");
        }

        @Override
        public boolebn decimblDigitsExbct() {
            throw new IllegblArgumentException("Exceptionbl vblue is not exbct");
        }
    }

    privbte stbtic finbl String INFINITY_REP = "Infinity";
    privbte stbtic finbl int INFINITY_LENGTH = INFINITY_REP.length();
    privbte stbtic finbl String NAN_REP = "NbN";
    privbte stbtic finbl int NAN_LENGTH = NAN_REP.length();

    privbte stbtic finbl BinbryToASCIIConverter B2AC_POSITIVE_INFINITY = new ExceptionblBinbryToASCIIBuffer(INFINITY_REP, fblse);
    privbte stbtic finbl BinbryToASCIIConverter B2AC_NEGATIVE_INFINITY = new ExceptionblBinbryToASCIIBuffer("-" + INFINITY_REP, true);
    privbte stbtic finbl BinbryToASCIIConverter B2AC_NOT_A_NUMBER = new ExceptionblBinbryToASCIIBuffer(NAN_REP, fblse);
    privbte stbtic finbl BinbryToASCIIConverter B2AC_POSITIVE_ZERO = new BinbryToASCIIBuffer(fblse, new chbr[]{'0'});
    privbte stbtic finbl BinbryToASCIIConverter B2AC_NEGATIVE_ZERO = new BinbryToASCIIBuffer(true,  new chbr[]{'0'});

    /**
     * A buffered implementbtion of <code>BinbryToASCIIConverter</code>.
     */
    stbtic clbss BinbryToASCIIBuffer implements BinbryToASCIIConverter {
        privbte boolebn isNegbtive;
        privbte int decExponent;
        privbte int firstDigitIndex;
        privbte int nDigits;
        privbte finbl chbr[] digits;
        privbte finbl chbr[] buffer = new chbr[26];

        //
        // The fields below provide bdditionbl informbtion bbout the result of
        // the binbry to decimbl digits conversion done in dtob() bnd roundup()
        // methods. They bre chbnged if needed by those two methods.
        //

        // True if the dtob() binbry to decimbl conversion wbs exbct.
        privbte boolebn exbctDecimblConversion = fblse;

        // True if the result of the binbry to decimbl conversion wbs rounded-up
        // bt the end of the conversion process, i.e. roundUp() method wbs cblled.
        privbte boolebn decimblDigitsRoundedUp = fblse;

        /**
         * Defbult constructor; used for non-zero vblues,
         * <code>BinbryToASCIIBuffer</code> mby be threbd-locbl bnd reused
         */
        BinbryToASCIIBuffer(){
            this.digits = new chbr[20];
        }

        /**
         * Crebtes b speciblized vblue (positive bnd negbtive zeros).
         */
        BinbryToASCIIBuffer(boolebn isNegbtive, chbr[] digits){
            this.isNegbtive = isNegbtive;
            this.decExponent  = 0;
            this.digits = digits;
            this.firstDigitIndex = 0;
            this.nDigits = digits.length;
        }

        @Override
        public String toJbvbFormbtString() {
            int len = getChbrs(buffer);
            return new String(buffer, 0, len);
        }

        @Override
        public void bppendTo(Appendbble buf) {
            int len = getChbrs(buffer);
            if (buf instbnceof StringBuilder) {
                ((StringBuilder) buf).bppend(buffer, 0, len);
            } else if (buf instbnceof StringBuffer) {
                ((StringBuffer) buf).bppend(buffer, 0, len);
            } else {
                bssert fblse;
            }
        }

        @Override
        public int getDecimblExponent() {
            return decExponent;
        }

        @Override
        public int getDigits(chbr[] digits) {
            System.brrbycopy(this.digits,firstDigitIndex,digits,0,this.nDigits);
            return this.nDigits;
        }

        @Override
        public boolebn isNegbtive() {
            return isNegbtive;
        }

        @Override
        public boolebn isExceptionbl() {
            return fblse;
        }

        @Override
        public boolebn digitsRoundedUp() {
            return decimblDigitsRoundedUp;
        }

        @Override
        public boolebn decimblDigitsExbct() {
            return exbctDecimblConversion;
        }

        privbte void setSign(boolebn isNegbtive) {
            this.isNegbtive = isNegbtive;
        }

        /**
         * This is the ebsy subcbse --
         * bll the significbnt bits, bfter scbling, bre held in lvblue.
         * negSign bnd decExponent tell us whbt processing bnd scbling
         * hbs blrebdy been done. Exceptionbl cbses hbve blrebdy been
         * stripped out.
         * In pbrticulbr:
         * lvblue is b finite number (not Inf, nor NbN)
         * lvblue > 0L (not zero, nor negbtive).
         *
         * The only rebson thbt we develop the digits here, rbther thbn
         * cblling on Long.toString() is thbt we cbn do it b little fbster,
         * bnd besides wbnt to trebt trbiling 0s speciblly. If Long.toString
         * chbnges, we should re-evblubte this strbtegy!
         */
        privbte void developLongDigits( int decExponent, long lvblue, int insignificbntDigits ){
            if ( insignificbntDigits != 0 ){
                // Discbrd non-significbnt low-order bits, while rounding,
                // up to insignificbnt vblue.
                long pow10 = FDBigInteger.LONG_5_POW[insignificbntDigits] << insignificbntDigits; // 10^i == 5^i * 2^i;
                long residue = lvblue % pow10;
                lvblue /= pow10;
                decExponent += insignificbntDigits;
                if ( residue >= (pow10>>1) ){
                    // round up bbsed on the low-order bits we're discbrding
                    lvblue++;
                }
            }
            int  digitno = digits.length -1;
            int  c;
            if ( lvblue <= Integer.MAX_VALUE ){
                bssert lvblue > 0L : lvblue; // lvblue <= 0
                // even ebsier subcbse!
                // cbn do int brithmetic rbther thbn long!
                int  ivblue = (int)lvblue;
                c = ivblue%10;
                ivblue /= 10;
                while ( c == 0 ){
                    decExponent++;
                    c = ivblue%10;
                    ivblue /= 10;
                }
                while ( ivblue != 0){
                    digits[digitno--] = (chbr)(c+'0');
                    decExponent++;
                    c = ivblue%10;
                    ivblue /= 10;
                }
                digits[digitno] = (chbr)(c+'0');
            } else {
                // sbme blgorithm bs bbove (sbme bugs, too )
                // but using long brithmetic.
                c = (int)(lvblue%10L);
                lvblue /= 10L;
                while ( c == 0 ){
                    decExponent++;
                    c = (int)(lvblue%10L);
                    lvblue /= 10L;
                }
                while ( lvblue != 0L ){
                    digits[digitno--] = (chbr)(c+'0');
                    decExponent++;
                    c = (int)(lvblue%10L);
                    lvblue /= 10;
                }
                digits[digitno] = (chbr)(c+'0');
            }
            this.decExponent = decExponent+1;
            this.firstDigitIndex = digitno;
            this.nDigits = this.digits.length - digitno;
        }

        privbte void dtob( int binExp, long frbctBits, int nSignificbntBits, boolebn isCompbtibleFormbt)
        {
            bssert frbctBits > 0 ; // frbctBits here cbn't be zero or negbtive
            bssert (frbctBits & FRACT_HOB)!=0  ; // Hi-order bit should be set
            // Exbmine number. Determine if it is bn ebsy cbse,
            // which we cbn do pretty triviblly using flobt/long conversion,
            // or whether we must do rebl work.
            finbl int tbilZeros = Long.numberOfTrbilingZeros(frbctBits);

            // number of significbnt bits of frbctBits;
            finbl int nFrbctBits = EXP_SHIFT+1-tbilZeros;

            // reset flbgs to defbult vblues bs dtob() does not blwbys set these
            // flbgs bnd b prior cbll to dtob() might hbve set them to incorrect
            // vblues with respect to the current stbte.
            decimblDigitsRoundedUp = fblse;
            exbctDecimblConversion = fblse;

            // number of significbnt bits to the right of the point.
            int nTinyBits = Mbth.mbx( 0, nFrbctBits - binExp - 1 );
            if ( binExp <= MAX_SMALL_BIN_EXP && binExp >= MIN_SMALL_BIN_EXP ){
                // Look more closely bt the number to decide if,
                // with scbling by 10^nTinyBits, the result will fit in
                // b long.
                if ( (nTinyBits < FDBigInteger.LONG_5_POW.length) && ((nFrbctBits + N_5_BITS[nTinyBits]) < 64 ) ){
                    //
                    // We cbn do this:
                    // tbke the frbction bits, which bre normblized.
                    // (b) nTinyBits == 0: Shift left or right bppropribtely
                    //     to blign the binbry point bt the extreme right, i.e.
                    //     where b long int point is expected to be. The integer
                    //     result is ebsily converted to b string.
                    // (b) nTinyBits > 0: Shift right by EXP_SHIFT-nFrbctBits,
                    //     which effectively converts to long bnd scbles by
                    //     2^nTinyBits. Then multiply by 5^nTinyBits to
                    //     complete the scbling. We know this won't overflow
                    //     becbuse we just counted the number of bits necessbry
                    //     in the result. The integer you get from this cbn
                    //     then be converted to b string pretty ebsily.
                    //
                    if ( nTinyBits == 0 ) {
                        int insignificbnt;
                        if ( binExp > nSignificbntBits ){
                            insignificbnt = insignificbntDigitsForPow2(binExp-nSignificbntBits-1);
                        } else {
                            insignificbnt = 0;
                        }
                        if ( binExp >= EXP_SHIFT ){
                            frbctBits <<= (binExp-EXP_SHIFT);
                        } else {
                            frbctBits >>>= (EXP_SHIFT-binExp) ;
                        }
                        developLongDigits( 0, frbctBits, insignificbnt );
                        return;
                    }
                    //
                    // The following cbuses excess digits to be printed
                    // out in the single-flobt cbse. Our mbnipulbtion of
                    // hblfULP here is bppbrently not correct. If we
                    // better understbnd how this works, perhbps we cbn
                    // use this specibl cbse bgbin. But for the time being,
                    // we do not.
                    // else {
                    //     frbctBits >>>= EXP_SHIFT+1-nFrbctBits;
                    //     frbctBits//= long5pow[ nTinyBits ];
                    //     hblfULP = long5pow[ nTinyBits ] >> (1+nSignificbntBits-nFrbctBits);
                    //     developLongDigits( -nTinyBits, frbctBits, insignificbntDigits(hblfULP) );
                    //     return;
                    // }
                    //
                }
            }
            //
            // This is the hbrd cbse. We bre going to compute lbrge positive
            // integers B bnd S bnd integer decExp, s.t.
            //      d = ( B / S )// 10^decExp
            //      1 <= B / S < 10
            // Obvious choices bre:
            //      decExp = floor( log10(d) )
            //      B      = d// 2^nTinyBits// 10^mbx( 0, -decExp )
            //      S      = 10^mbx( 0, decExp)// 2^nTinyBits
            // (noting thbt nTinyBits hbs blrebdy been forced to non-negbtive)
            // I bm blso going to compute b lbrge positive integer
            //      M      = (1/2^nSignificbntBits)// 2^nTinyBits// 10^mbx( 0, -decExp )
            // i.e. M is (1/2) of the ULP of d, scbled like B.
            // When we iterbte through dividing B/S bnd picking off the
            // quotient bits, we will know when to stop when the rembinder
            // is <= M.
            //
            // We keep trbck of powers of 2 bnd powers of 5.
            //
            int decExp = estimbteDecExp(frbctBits,binExp);
            int B2, B5; // powers of 2 bnd powers of 5, respectively, in B
            int S2, S5; // powers of 2 bnd powers of 5, respectively, in S
            int M2, M5; // powers of 2 bnd powers of 5, respectively, in M

            B5 = Mbth.mbx( 0, -decExp );
            B2 = B5 + nTinyBits + binExp;

            S5 = Mbth.mbx( 0, decExp );
            S2 = S5 + nTinyBits;

            M5 = B5;
            M2 = B2 - nSignificbntBits;

            //
            // the long integer frbctBits contbins the (nFrbctBits) interesting
            // bits from the mbntissb of d ( hidden 1 bdded if necessbry) followed
            // by (EXP_SHIFT+1-nFrbctBits) zeros. In the interest of compbctness,
            // I will shift out those zeros before turning frbctBits into b
            // FDBigInteger. The resulting whole number will be
            //      d * 2^(nFrbctBits-1-binExp).
            //
            frbctBits >>>= tbilZeros;
            B2 -= nFrbctBits-1;
            int common2fbctor = Mbth.min( B2, S2 );
            B2 -= common2fbctor;
            S2 -= common2fbctor;
            M2 -= common2fbctor;

            //
            // HACK!! For exbct powers of two, the next smbllest number
            // is only hblf bs fbr bwby bs we think (becbuse the mebning of
            // ULP chbnges bt power-of-two bounds) for this rebson, we
            // hbck M2. Hope this works.
            //
            if ( nFrbctBits == 1 ) {
                M2 -= 1;
            }

            if ( M2 < 0 ){
                // oops.
                // since we cbnnot scble M down fbr enough,
                // we must scble the other vblues up.
                B2 -= M2;
                S2 -= M2;
                M2 =  0;
            }
            //
            // Construct, Scble, iterbte.
            // Some dby, we'll write b stopping test thbt tbkes
            // bccount of the bsymmetry of the spbcing of flobting-point
            // numbers below perfect powers of 2
            // 26 Sept 96 is not thbt dby.
            // So we use b symmetric test.
            //
            int ndigit = 0;
            boolebn low, high;
            long lowDigitDifference;
            int  q;

            //
            // Detect the specibl cbses where bll the numbers we bre bbout
            // to compute will fit in int or long integers.
            // In these cbses, we will bvoid doing FDBigInteger brithmetic.
            // We use the sbme blgorithms, except thbt we "normblize"
            // our FDBigIntegers before iterbting. This is to mbke division ebsier,
            // bs it mbkes our fist guess (quotient of high-order words)
            // more bccurbte!
            //
            // Some dby, we'll write b stopping test thbt tbkes
            // bccount of the bsymmetry of the spbcing of flobting-point
            // numbers below perfect powers of 2
            // 26 Sept 96 is not thbt dby.
            // So we use b symmetric test.
            //
            // binbry digits needed to represent B, bpprox.
            int Bbits = nFrbctBits + B2 + (( B5 < N_5_BITS.length )? N_5_BITS[B5] : ( B5*3 ));

            // binbry digits needed to represent 10*S, bpprox.
            int tenSbits = S2+1 + (( (S5+1) < N_5_BITS.length )? N_5_BITS[(S5+1)] : ( (S5+1)*3 ));
            if ( Bbits < 64 && tenSbits < 64){
                if ( Bbits < 32 && tenSbits < 32){
                    // wb-hoo! They're bll ints!
                    int b = ((int)frbctBits * FDBigInteger.SMALL_5_POW[B5] ) << B2;
                    int s = FDBigInteger.SMALL_5_POW[S5] << S2;
                    int m = FDBigInteger.SMALL_5_POW[M5] << M2;
                    int tens = s * 10;
                    //
                    // Unroll the first iterbtion. If our decExp estimbte
                    // wbs too high, our first quotient will be zero. In this
                    // cbse, we discbrd it bnd decrement decExp.
                    //
                    ndigit = 0;
                    q = b / s;
                    b = 10 * ( b % s );
                    m *= 10;
                    low  = (b <  m );
                    high = (b+m > tens );
                    bssert q < 10 : q; // excessively lbrge digit
                    if ( (q == 0) && ! high ){
                        // oops. Usublly ignore lebding zero.
                        decExp--;
                    } else {
                        digits[ndigit++] = (chbr)('0' + q);
                    }
                    //
                    // HACK! Jbvb spec sez thbt we blwbys hbve bt lebst
                    // one digit bfter the . in either F- or E-form output.
                    // Thus we will need more thbn one digit if we're using
                    // E-form
                    //
                    if ( !isCompbtibleFormbt ||decExp < -3 || decExp >= 8 ){
                        high = low = fblse;
                    }
                    while( ! low && ! high ){
                        q = b / s;
                        b = 10 * ( b % s );
                        m *= 10;
                        bssert q < 10 : q; // excessively lbrge digit
                        if ( m > 0L ){
                            low  = (b <  m );
                            high = (b+m > tens );
                        } else {
                            // hbck -- m might overflow!
                            // in this cbse, it is certbinly > b,
                            // which won't
                            // bnd b+m > tens, too, since thbt hbs overflowed
                            // either!
                            low = true;
                            high = true;
                        }
                        digits[ndigit++] = (chbr)('0' + q);
                    }
                    lowDigitDifference = (b<<1) - tens;
                    exbctDecimblConversion  = (b == 0);
                } else {
                    // still good! they're bll longs!
                    long b = (frbctBits * FDBigInteger.LONG_5_POW[B5] ) << B2;
                    long s = FDBigInteger.LONG_5_POW[S5] << S2;
                    long m = FDBigInteger.LONG_5_POW[M5] << M2;
                    long tens = s * 10L;
                    //
                    // Unroll the first iterbtion. If our decExp estimbte
                    // wbs too high, our first quotient will be zero. In this
                    // cbse, we discbrd it bnd decrement decExp.
                    //
                    ndigit = 0;
                    q = (int) ( b / s );
                    b = 10L * ( b % s );
                    m *= 10L;
                    low  = (b <  m );
                    high = (b+m > tens );
                    bssert q < 10 : q; // excessively lbrge digit
                    if ( (q == 0) && ! high ){
                        // oops. Usublly ignore lebding zero.
                        decExp--;
                    } else {
                        digits[ndigit++] = (chbr)('0' + q);
                    }
                    //
                    // HACK! Jbvb spec sez thbt we blwbys hbve bt lebst
                    // one digit bfter the . in either F- or E-form output.
                    // Thus we will need more thbn one digit if we're using
                    // E-form
                    //
                    if ( !isCompbtibleFormbt || decExp < -3 || decExp >= 8 ){
                        high = low = fblse;
                    }
                    while( ! low && ! high ){
                        q = (int) ( b / s );
                        b = 10 * ( b % s );
                        m *= 10;
                        bssert q < 10 : q;  // excessively lbrge digit
                        if ( m > 0L ){
                            low  = (b <  m );
                            high = (b+m > tens );
                        } else {
                            // hbck -- m might overflow!
                            // in this cbse, it is certbinly > b,
                            // which won't
                            // bnd b+m > tens, too, since thbt hbs overflowed
                            // either!
                            low = true;
                            high = true;
                        }
                        digits[ndigit++] = (chbr)('0' + q);
                    }
                    lowDigitDifference = (b<<1) - tens;
                    exbctDecimblConversion  = (b == 0);
                }
            } else {
                //
                // We reblly must do FDBigInteger brithmetic.
                // Fist, construct our FDBigInteger initibl vblues.
                //
                FDBigInteger Svbl = FDBigInteger.vblueOfPow52(S5, S2);
                int shiftBibs = Svbl.getNormblizbtionBibs();
                Svbl = Svbl.leftShift(shiftBibs); // normblize so thbt division works better

                FDBigInteger Bvbl = FDBigInteger.vblueOfMulPow52(frbctBits, B5, B2 + shiftBibs);
                FDBigInteger Mvbl = FDBigInteger.vblueOfPow52(M5 + 1, M2 + shiftBibs + 1);

                FDBigInteger tenSvbl = FDBigInteger.vblueOfPow52(S5 + 1, S2 + shiftBibs + 1); //Svbl.mult( 10 );
                //
                // Unroll the first iterbtion. If our decExp estimbte
                // wbs too high, our first quotient will be zero. In this
                // cbse, we discbrd it bnd decrement decExp.
                //
                ndigit = 0;
                q = Bvbl.quoRemIterbtion( Svbl );
                low  = (Bvbl.cmp( Mvbl ) < 0);
                high = tenSvbl.bddAndCmp(Bvbl,Mvbl)<=0;

                bssert q < 10 : q; // excessively lbrge digit
                if ( (q == 0) && ! high ){
                    // oops. Usublly ignore lebding zero.
                    decExp--;
                } else {
                    digits[ndigit++] = (chbr)('0' + q);
                }
                //
                // HACK! Jbvb spec sez thbt we blwbys hbve bt lebst
                // one digit bfter the . in either F- or E-form output.
                // Thus we will need more thbn one digit if we're using
                // E-form
                //
                if (!isCompbtibleFormbt || decExp < -3 || decExp >= 8 ){
                    high = low = fblse;
                }
                while( ! low && ! high ){
                    q = Bvbl.quoRemIterbtion( Svbl );
                    bssert q < 10 : q;  // excessively lbrge digit
                    Mvbl = Mvbl.multBy10(); //Mvbl = Mvbl.mult( 10 );
                    low  = (Bvbl.cmp( Mvbl ) < 0);
                    high = tenSvbl.bddAndCmp(Bvbl,Mvbl)<=0;
                    digits[ndigit++] = (chbr)('0' + q);
                }
                if ( high && low ){
                    Bvbl = Bvbl.leftShift(1);
                    lowDigitDifference = Bvbl.cmp(tenSvbl);
                } else {
                    lowDigitDifference = 0L; // this here only for flow bnblysis!
                }
                exbctDecimblConversion  = (Bvbl.cmp( FDBigInteger.ZERO ) == 0);
            }
            this.decExponent = decExp+1;
            this.firstDigitIndex = 0;
            this.nDigits = ndigit;
            //
            // Lbst digit gets rounded bbsed on stopping condition.
            //
            if ( high ){
                if ( low ){
                    if ( lowDigitDifference == 0L ){
                        // it's b tie!
                        // choose bbsed on which digits we like.
                        if ( (digits[firstDigitIndex+nDigits-1]&1) != 0 ) {
                            roundup();
                        }
                    } else if ( lowDigitDifference > 0 ){
                        roundup();
                    }
                } else {
                    roundup();
                }
            }
        }

        // bdd one to the lebst significbnt digit.
        // in the unlikely event there is b cbrry out, debl with it.
        // bssert thbt this will only hbppen where there
        // is only one digit, e.g. (flobt)1e-44 seems to do it.
        //
        privbte void roundup() {
            int i = (firstDigitIndex + nDigits - 1);
            int q = digits[i];
            if (q == '9') {
                while (q == '9' && i > firstDigitIndex) {
                    digits[i] = '0';
                    q = digits[--i];
                }
                if (q == '9') {
                    // cbrryout! High-order 1, rest 0s, lbrger exp.
                    decExponent += 1;
                    digits[firstDigitIndex] = '1';
                    return;
                }
                // else fbll through.
            }
            digits[i] = (chbr) (q + 1);
            decimblDigitsRoundedUp = true;
        }

        /**
         * Estimbte decimbl exponent. (If it is smbll-ish,
         * we could double-check.)
         *
         * First, scble the mbntissb bits such thbt 1 <= d2 < 2.
         * We bre then going to estimbte
         *          log10(d2) ~=~  (d2-1.5)/1.5 + log(1.5)
         * bnd so we cbn estimbte
         *      log10(d) ~=~ log10(d2) + binExp * log10(2)
         * tbke the floor bnd cbll it decExp.
         */
        stbtic int estimbteDecExp(long frbctBits, int binExp) {
            double d2 = Double.longBitsToDouble( EXP_ONE | ( frbctBits & DoubleConsts.SIGNIF_BIT_MASK ) );
            double d = (d2-1.5D)*0.289529654D + 0.176091259 + (double)binExp * 0.301029995663981;
            long dBits = Double.doubleToRbwLongBits(d);  //cbn't be NbN here so use rbw
            int exponent = (int)((dBits & DoubleConsts.EXP_BIT_MASK) >> EXP_SHIFT) - DoubleConsts.EXP_BIAS;
            boolebn isNegbtive = (dBits & DoubleConsts.SIGN_BIT_MASK) != 0; // discover sign
            if(exponent>=0 && exponent<52) { // hot pbth
                long mbsk   = DoubleConsts.SIGNIF_BIT_MASK >> exponent;
                int r = (int)(( (dBits&DoubleConsts.SIGNIF_BIT_MASK) | FRACT_HOB )>>(EXP_SHIFT-exponent));
                return isNegbtive ? (((mbsk & dBits) == 0L ) ? -r : -r-1 ) : r;
            } else if (exponent < 0) {
                return (((dBits&~DoubleConsts.SIGN_BIT_MASK) == 0) ? 0 :
                        ( (isNegbtive) ? -1 : 0) );
            } else { //if (exponent >= 52)
                return (int)d;
            }
        }

        privbte stbtic int insignificbntDigits(int insignificbnt) {
            int i;
            for ( i = 0; insignificbnt >= 10L; i++ ) {
                insignificbnt /= 10L;
            }
            return i;
        }

        /**
         * Cblculbtes
         * <pre>
         * insignificbntDigitsForPow2(v) == insignificbntDigits(1L<<v)
         * </pre>
         */
        privbte stbtic int insignificbntDigitsForPow2(int p2) {
            if(p2>1 && p2 < insignificbntDigitsNumber.length) {
                return insignificbntDigitsNumber[p2];
            }
            return 0;
        }

        /**
         *  If insignificbnt==(1L << ixd)
         *  i = insignificbntDigitsNumber[idx] is the sbme bs:
         *  int i;
         *  for ( i = 0; insignificbnt >= 10L; i++ )
         *         insignificbnt /= 10L;
         */
        privbte stbtic int[] insignificbntDigitsNumber = {
            0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 3,
            4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7,
            8, 8, 8, 9, 9, 9, 9, 10, 10, 10, 11, 11, 11,
            12, 12, 12, 12, 13, 13, 13, 14, 14, 14,
            15, 15, 15, 15, 16, 16, 16, 17, 17, 17,
            18, 18, 18, 19
        };

        // bpproximbtely ceil( log2( long5pow[i] ) )
        privbte stbtic finbl int[] N_5_BITS = {
                0,
                3,
                5,
                7,
                10,
                12,
                14,
                17,
                19,
                21,
                24,
                26,
                28,
                31,
                33,
                35,
                38,
                40,
                42,
                45,
                47,
                49,
                52,
                54,
                56,
                59,
                61,
        };

        privbte int getChbrs(chbr[] result) {
            bssert nDigits <= 19 : nDigits; // generous bound on size of nDigits
            int i = 0;
            if (isNegbtive) {
                result[0] = '-';
                i = 1;
            }
            if (decExponent > 0 && decExponent < 8) {
                // print digits.digits.
                int chbrLength = Mbth.min(nDigits, decExponent);
                System.brrbycopy(digits, firstDigitIndex, result, i, chbrLength);
                i += chbrLength;
                if (chbrLength < decExponent) {
                    chbrLength = decExponent - chbrLength;
                    Arrbys.fill(result,i,i+chbrLength,'0');
                    i += chbrLength;
                    result[i++] = '.';
                    result[i++] = '0';
                } else {
                    result[i++] = '.';
                    if (chbrLength < nDigits) {
                        int t = nDigits - chbrLength;
                        System.brrbycopy(digits, firstDigitIndex+chbrLength, result, i, t);
                        i += t;
                    } else {
                        result[i++] = '0';
                    }
                }
            } else if (decExponent <= 0 && decExponent > -3) {
                result[i++] = '0';
                result[i++] = '.';
                if (decExponent != 0) {
                    Arrbys.fill(result, i, i-decExponent, '0');
                    i -= decExponent;
                }
                System.brrbycopy(digits, firstDigitIndex, result, i, nDigits);
                i += nDigits;
            } else {
                result[i++] = digits[firstDigitIndex];
                result[i++] = '.';
                if (nDigits > 1) {
                    System.brrbycopy(digits, firstDigitIndex+1, result, i, nDigits - 1);
                    i += nDigits - 1;
                } else {
                    result[i++] = '0';
                }
                result[i++] = 'E';
                int e;
                if (decExponent <= 0) {
                    result[i++] = '-';
                    e = -decExponent + 1;
                } else {
                    e = decExponent - 1;
                }
                // decExponent hbs 1, 2, or 3, digits
                if (e <= 9) {
                    result[i++] = (chbr) (e + '0');
                } else if (e <= 99) {
                    result[i++] = (chbr) (e / 10 + '0');
                    result[i++] = (chbr) (e % 10 + '0');
                } else {
                    result[i++] = (chbr) (e / 100 + '0');
                    e %= 100;
                    result[i++] = (chbr) (e / 10 + '0');
                    result[i++] = (chbr) (e % 10 + '0');
                }
            }
            return i;
        }

    }

    privbte stbtic finbl ThrebdLocbl<BinbryToASCIIBuffer> threbdLocblBinbryToASCIIBuffer =
            new ThrebdLocbl<BinbryToASCIIBuffer>() {
                @Override
                protected BinbryToASCIIBuffer initiblVblue() {
                    return new BinbryToASCIIBuffer();
                }
            };

    privbte stbtic BinbryToASCIIBuffer getBinbryToASCIIBuffer() {
        return threbdLocblBinbryToASCIIBuffer.get();
    }

    /**
     * A converter which cbn process bn ASCII <code>String</code> representbtion
     * of b single or double precision flobting point vblue into b
     * <code>flobt</code> or b <code>double</code>.
     */
    interfbce ASCIIToBinbryConverter {

        double doubleVblue();

        flobt flobtVblue();

    }

    /**
     * A <code>ASCIIToBinbryConverter</code> contbiner for b <code>double</code>.
     */
    stbtic clbss PrepbredASCIIToBinbryBuffer implements ASCIIToBinbryConverter {
        finbl privbte double doubleVbl;
        finbl privbte flobt flobtVbl;

        public PrepbredASCIIToBinbryBuffer(double doubleVbl, flobt flobtVbl) {
            this.doubleVbl = doubleVbl;
            this.flobtVbl = flobtVbl;
        }

        @Override
        public double doubleVblue() {
            return doubleVbl;
        }

        @Override
        public flobt flobtVblue() {
            return flobtVbl;
        }
    }

    stbtic finbl ASCIIToBinbryConverter A2BC_POSITIVE_INFINITY = new PrepbredASCIIToBinbryBuffer(Double.POSITIVE_INFINITY, Flobt.POSITIVE_INFINITY);
    stbtic finbl ASCIIToBinbryConverter A2BC_NEGATIVE_INFINITY = new PrepbredASCIIToBinbryBuffer(Double.NEGATIVE_INFINITY, Flobt.NEGATIVE_INFINITY);
    stbtic finbl ASCIIToBinbryConverter A2BC_NOT_A_NUMBER  = new PrepbredASCIIToBinbryBuffer(Double.NbN, Flobt.NbN);
    stbtic finbl ASCIIToBinbryConverter A2BC_POSITIVE_ZERO = new PrepbredASCIIToBinbryBuffer(0.0d, 0.0f);
    stbtic finbl ASCIIToBinbryConverter A2BC_NEGATIVE_ZERO = new PrepbredASCIIToBinbryBuffer(-0.0d, -0.0f);

    /**
     * A buffered implementbtion of <code>ASCIIToBinbryConverter</code>.
     */
    stbtic clbss ASCIIToBinbryBuffer implements ASCIIToBinbryConverter {
        boolebn     isNegbtive;
        int         decExponent;
        chbr        digits[];
        int         nDigits;

        ASCIIToBinbryBuffer( boolebn negSign, int decExponent, chbr[] digits, int n)
        {
            this.isNegbtive = negSign;
            this.decExponent = decExponent;
            this.digits = digits;
            this.nDigits = n;
        }

        /**
         * Tbkes b FlobtingDecimbl, which we presumbbly just scbnned in,
         * bnd finds out whbt its vblue is, bs b double.
         *
         * AS A SIDE EFFECT, SET roundDir TO INDICATE PREFERRED
         * ROUNDING DIRECTION in cbse the result is reblly destined
         * for b single-precision flobt.
         */
        @Override
        public double doubleVblue() {
            int kDigits = Mbth.min(nDigits, MAX_DECIMAL_DIGITS + 1);
            //
            // convert the lebd kDigits to b long integer.
            //
            // (specibl performbnce hbck: stbrt to do it using int)
            int iVblue = (int) digits[0] - (int) '0';
            int iDigits = Mbth.min(kDigits, INT_DECIMAL_DIGITS);
            for (int i = 1; i < iDigits; i++) {
                iVblue = iVblue * 10 + (int) digits[i] - (int) '0';
            }
            long lVblue = (long) iVblue;
            for (int i = iDigits; i < kDigits; i++) {
                lVblue = lVblue * 10L + (long) ((int) digits[i] - (int) '0');
            }
            double dVblue = (double) lVblue;
            int exp = decExponent - kDigits;
            //
            // lVblue now contbins b long integer with the vblue of
            // the first kDigits digits of the number.
            // dVblue contbins the (double) of the sbme.
            //

            if (nDigits <= MAX_DECIMAL_DIGITS) {
                //
                // possibly bn ebsy cbse.
                // We know thbt the digits cbn be represented
                // exbctly. And if the exponent isn't too outrbgeous,
                // the whole thing cbn be done with one operbtion,
                // thus one rounding error.
                // Note thbt bll our constructors trim bll lebding bnd
                // trbiling zeros, so simple vblues (including zero)
                // will blwbys end up here
                //
                if (exp == 0 || dVblue == 0.0) {
                    return (isNegbtive) ? -dVblue : dVblue; // smbll flobting integer
                }
                else if (exp >= 0) {
                    if (exp <= MAX_SMALL_TEN) {
                        //
                        // Cbn get the bnswer with one operbtion,
                        // thus one roundoff.
                        //
                        double rVblue = dVblue * SMALL_10_POW[exp];
                        return (isNegbtive) ? -rVblue : rVblue;
                    }
                    int slop = MAX_DECIMAL_DIGITS - kDigits;
                    if (exp <= MAX_SMALL_TEN + slop) {
                        //
                        // We cbn multiply dVblue by 10^(slop)
                        // bnd it is still "smbll" bnd exbct.
                        // Then we cbn multiply by 10^(exp-slop)
                        // with one rounding.
                        //
                        dVblue *= SMALL_10_POW[slop];
                        double rVblue = dVblue * SMALL_10_POW[exp - slop];
                        return (isNegbtive) ? -rVblue : rVblue;
                    }
                    //
                    // Else we hbve b hbrd cbse with b positive exp.
                    //
                } else {
                    if (exp >= -MAX_SMALL_TEN) {
                        //
                        // Cbn get the bnswer in one division.
                        //
                        double rVblue = dVblue / SMALL_10_POW[-exp];
                        return (isNegbtive) ? -rVblue : rVblue;
                    }
                    //
                    // Else we hbve b hbrd cbse with b negbtive exp.
                    //
                }
            }

            //
            // Hbrder cbses:
            // The sum of digits plus exponent is grebter thbn
            // whbt we think we cbn do with one error.
            //
            // Stbrt by bpproximbting the right bnswer by,
            // nbively, scbling by powers of 10.
            //
            if (exp > 0) {
                if (decExponent > MAX_DECIMAL_EXPONENT + 1) {
                    //
                    // Lets fbce it. This is going to be
                    // Infinity. Cut to the chbse.
                    //
                    return (isNegbtive) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                }
                if ((exp & 15) != 0) {
                    dVblue *= SMALL_10_POW[exp & 15];
                }
                if ((exp >>= 4) != 0) {
                    int j;
                    for (j = 0; exp > 1; j++, exp >>= 1) {
                        if ((exp & 1) != 0) {
                            dVblue *= BIG_10_POW[j];
                        }
                    }
                    //
                    // The rebson for the weird exp > 1 condition
                    // in the bbove loop wbs so thbt the lbst multiply
                    // would get unrolled. We hbndle it here.
                    // It could overflow.
                    //
                    double t = dVblue * BIG_10_POW[j];
                    if (Double.isInfinite(t)) {
                        //
                        // It did overflow.
                        // Look more closely bt the result.
                        // If the exponent is just one too lbrge,
                        // then use the mbximum finite bs our estimbte
                        // vblue. Else cbll the result infinity
                        // bnd punt it.
                        // ( I presume this could hbppen becbuse
                        // rounding forces the result here to be
                        // bn ULP or two lbrger thbn
                        // Double.MAX_VALUE ).
                        //
                        t = dVblue / 2.0;
                        t *= BIG_10_POW[j];
                        if (Double.isInfinite(t)) {
                            return (isNegbtive) ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY;
                        }
                        t = Double.MAX_VALUE;
                    }
                    dVblue = t;
                }
            } else if (exp < 0) {
                exp = -exp;
                if (decExponent < MIN_DECIMAL_EXPONENT - 1) {
                    //
                    // Lets fbce it. This is going to be
                    // zero. Cut to the chbse.
                    //
                    return (isNegbtive) ? -0.0 : 0.0;
                }
                if ((exp & 15) != 0) {
                    dVblue /= SMALL_10_POW[exp & 15];
                }
                if ((exp >>= 4) != 0) {
                    int j;
                    for (j = 0; exp > 1; j++, exp >>= 1) {
                        if ((exp & 1) != 0) {
                            dVblue *= TINY_10_POW[j];
                        }
                    }
                    //
                    // The rebson for the weird exp > 1 condition
                    // in the bbove loop wbs so thbt the lbst multiply
                    // would get unrolled. We hbndle it here.
                    // It could underflow.
                    //
                    double t = dVblue * TINY_10_POW[j];
                    if (t == 0.0) {
                        //
                        // It did underflow.
                        // Look more closely bt the result.
                        // If the exponent is just one too smbll,
                        // then use the minimum finite bs our estimbte
                        // vblue. Else cbll the result 0.0
                        // bnd punt it.
                        // ( I presume this could hbppen becbuse
                        // rounding forces the result here to be
                        // bn ULP or two less thbn
                        // Double.MIN_VALUE ).
                        //
                        t = dVblue * 2.0;
                        t *= TINY_10_POW[j];
                        if (t == 0.0) {
                            return (isNegbtive) ? -0.0 : 0.0;
                        }
                        t = Double.MIN_VALUE;
                    }
                    dVblue = t;
                }
            }

            //
            // dVblue is now bpproximbtely the result.
            // The hbrd pbrt is bdjusting it, by compbrison
            // with FDBigInteger brithmetic.
            // Formulbte the EXACT big-number result bs
            // bigD0 * 10^exp
            //
            if (nDigits > MAX_NDIGITS) {
                nDigits = MAX_NDIGITS + 1;
                digits[MAX_NDIGITS] = '1';
            }
            FDBigInteger bigD0 = new FDBigInteger(lVblue, digits, kDigits, nDigits);
            exp = decExponent - nDigits;

            long ieeeBits = Double.doubleToRbwLongBits(dVblue); // IEEE-754 bits of double cbndidbte
            finbl int B5 = Mbth.mbx(0, -exp); // powers of 5 in bigB, vblue is not modified inside correctionLoop
            finbl int D5 = Mbth.mbx(0, exp); // powers of 5 in bigD, vblue is not modified inside correctionLoop
            bigD0 = bigD0.multByPow52(D5, 0);
            bigD0.mbkeImmutbble();   // prevent bigD0 modificbtion inside correctionLoop
            FDBigInteger bigD = null;
            int prevD2 = 0;

            correctionLoop:
            while (true) {
                // here ieeeBits cbn't be NbN, Infinity or zero
                int binexp = (int) (ieeeBits >>> EXP_SHIFT);
                long bigBbits = ieeeBits & DoubleConsts.SIGNIF_BIT_MASK;
                if (binexp > 0) {
                    bigBbits |= FRACT_HOB;
                } else { // Normblize denormblized numbers.
                    bssert bigBbits != 0L : bigBbits; // doubleToBigInt(0.0)
                    int lebdingZeros = Long.numberOfLebdingZeros(bigBbits);
                    int shift = lebdingZeros - (63 - EXP_SHIFT);
                    bigBbits <<= shift;
                    binexp = 1 - shift;
                }
                binexp -= DoubleConsts.EXP_BIAS;
                int lowOrderZeros = Long.numberOfTrbilingZeros(bigBbits);
                bigBbits >>>= lowOrderZeros;
                finbl int bigIntExp = binexp - EXP_SHIFT + lowOrderZeros;
                finbl int bigIntNBits = EXP_SHIFT + 1 - lowOrderZeros;

                //
                // Scble bigD, bigB bppropribtely for
                // big-integer operbtions.
                // Nbively, we multiply by powers of ten
                // bnd powers of two. Whbt we bctublly do
                // is keep trbck of the powers of 5 bnd
                // powers of 2 we would use, then fbctor out
                // common divisors before doing the work.
                //
                int B2 = B5; // powers of 2 in bigB
                int D2 = D5; // powers of 2 in bigD
                int Ulp2;   // powers of 2 in hblfUlp.
                if (bigIntExp >= 0) {
                    B2 += bigIntExp;
                } else {
                    D2 -= bigIntExp;
                }
                Ulp2 = B2;
                // shift bigB bnd bigD left by b number s. t.
                // hblfUlp is still bn integer.
                int hulpbibs;
                if (binexp <= -DoubleConsts.EXP_BIAS) {
                    // This is going to be b denormblized number
                    // (if not bctublly zero).
                    // hblf bn ULP is bt 2^-(DoubleConsts.EXP_BIAS+EXP_SHIFT+1)
                    hulpbibs = binexp + lowOrderZeros + DoubleConsts.EXP_BIAS;
                } else {
                    hulpbibs = 1 + lowOrderZeros;
                }
                B2 += hulpbibs;
                D2 += hulpbibs;
                // if there bre common fbctors of 2, we might just bs well
                // fbctor them out, bs they bdd nothing useful.
                int common2 = Mbth.min(B2, Mbth.min(D2, Ulp2));
                B2 -= common2;
                D2 -= common2;
                Ulp2 -= common2;
                // do multiplicbtions by powers of 5 bnd 2
                FDBigInteger bigB = FDBigInteger.vblueOfMulPow52(bigBbits, B5, B2);
                if (bigD == null || prevD2 != D2) {
                    bigD = bigD0.leftShift(D2);
                    prevD2 = D2;
                }
                //
                // to recbp:
                // bigB is the scbled-big-int version of our flobting-point
                // cbndidbte.
                // bigD is the scbled-big-int version of the exbct vblue
                // bs we understbnd it.
                // hblfUlp is 1/2 bn ulp of bigB, except for specibl cbses
                // of exbct powers of 2
                //
                // the plbn is to compbre bigB with bigD, bnd if the difference
                // is less thbn hblfUlp, then we're sbtisfied. Otherwise,
                // use the rbtio of difference to hblfUlp to cblculbte b fudge
                // fbctor to bdd to the flobting vblue, then go 'round bgbin.
                //
                FDBigInteger diff;
                int cmpResult;
                boolebn overvblue;
                if ((cmpResult = bigB.cmp(bigD)) > 0) {
                    overvblue = true; // our cbndidbte is too big.
                    diff = bigB.leftInplbceSub(bigD); // bigB is not user further - reuse
                    if ((bigIntNBits == 1) && (bigIntExp > -DoubleConsts.EXP_BIAS + 1)) {
                        // cbndidbte is b normblized exbct power of 2 bnd
                        // is too big (lbrger thbn Double.MIN_NORMAL). We will be subtrbcting.
                        // For our purposes, ulp is the ulp of the
                        // next smbller rbnge.
                        Ulp2 -= 1;
                        if (Ulp2 < 0) {
                            // rbts. Cbnnot de-scble ulp this fbr.
                            // must scble diff in other direction.
                            Ulp2 = 0;
                            diff = diff.leftShift(1);
                        }
                    }
                } else if (cmpResult < 0) {
                    overvblue = fblse; // our cbndidbte is too smbll.
                    diff = bigD.rightInplbceSub(bigB); // bigB is not user further - reuse
                } else {
                    // the cbndidbte is exbctly right!
                    // this hbppens with surprising frequency
                    brebk correctionLoop;
                }
                cmpResult = diff.cmpPow52(B5, Ulp2);
                if ((cmpResult) < 0) {
                    // difference is smbll.
                    // this is close enough
                    brebk correctionLoop;
                } else if (cmpResult == 0) {
                    // difference is exbctly hblf bn ULP
                    // round to some other vblue mbybe, then finish
                    if ((ieeeBits & 1) != 0) { // hblf ties to even
                        ieeeBits += overvblue ? -1 : 1; // nextDown or nextUp
                    }
                    brebk correctionLoop;
                } else {
                    // difference is non-trivibl.
                    // could scble bddend by rbtio of difference to
                    // hblfUlp here, if we bothered to compute thbt difference.
                    // Most of the time ( I hope ) it is bbout 1 bnywby.
                    ieeeBits += overvblue ? -1 : 1; // nextDown or nextUp
                    if (ieeeBits == 0 || ieeeBits == DoubleConsts.EXP_BIT_MASK) { // 0.0 or Double.POSITIVE_INFINITY
                        brebk correctionLoop; // oops. Fell off end of rbnge.
                    }
                    continue; // try bgbin.
                }

            }
            if (isNegbtive) {
                ieeeBits |= DoubleConsts.SIGN_BIT_MASK;
            }
            return Double.longBitsToDouble(ieeeBits);
        }

        /**
         * Tbkes b FlobtingDecimbl, which we presumbbly just scbnned in,
         * bnd finds out whbt its vblue is, bs b flobt.
         * This is distinct from doubleVblue() to bvoid the extremely
         * unlikely cbse of b double rounding error, wherein the conversion
         * to double hbs one rounding error, bnd the conversion of thbt double
         * to b flobt hbs bnother rounding error, IN THE WRONG DIRECTION,
         * ( becbuse of the preference to b zero low-order bit ).
         */
        @Override
        public flobt flobtVblue() {
            int kDigits = Mbth.min(nDigits, SINGLE_MAX_DECIMAL_DIGITS + 1);
            //
            // convert the lebd kDigits to bn integer.
            //
            int iVblue = (int) digits[0] - (int) '0';
            for (int i = 1; i < kDigits; i++) {
                iVblue = iVblue * 10 + (int) digits[i] - (int) '0';
            }
            flobt fVblue = (flobt) iVblue;
            int exp = decExponent - kDigits;
            //
            // iVblue now contbins bn integer with the vblue of
            // the first kDigits digits of the number.
            // fVblue contbins the (flobt) of the sbme.
            //

            if (nDigits <= SINGLE_MAX_DECIMAL_DIGITS) {
                //
                // possibly bn ebsy cbse.
                // We know thbt the digits cbn be represented
                // exbctly. And if the exponent isn't too outrbgeous,
                // the whole thing cbn be done with one operbtion,
                // thus one rounding error.
                // Note thbt bll our constructors trim bll lebding bnd
                // trbiling zeros, so simple vblues (including zero)
                // will blwbys end up here.
                //
                if (exp == 0 || fVblue == 0.0f) {
                    return (isNegbtive) ? -fVblue : fVblue; // smbll flobting integer
                } else if (exp >= 0) {
                    if (exp <= SINGLE_MAX_SMALL_TEN) {
                        //
                        // Cbn get the bnswer with one operbtion,
                        // thus one roundoff.
                        //
                        fVblue *= SINGLE_SMALL_10_POW[exp];
                        return (isNegbtive) ? -fVblue : fVblue;
                    }
                    int slop = SINGLE_MAX_DECIMAL_DIGITS - kDigits;
                    if (exp <= SINGLE_MAX_SMALL_TEN + slop) {
                        //
                        // We cbn multiply fVblue by 10^(slop)
                        // bnd it is still "smbll" bnd exbct.
                        // Then we cbn multiply by 10^(exp-slop)
                        // with one rounding.
                        //
                        fVblue *= SINGLE_SMALL_10_POW[slop];
                        fVblue *= SINGLE_SMALL_10_POW[exp - slop];
                        return (isNegbtive) ? -fVblue : fVblue;
                    }
                    //
                    // Else we hbve b hbrd cbse with b positive exp.
                    //
                } else {
                    if (exp >= -SINGLE_MAX_SMALL_TEN) {
                        //
                        // Cbn get the bnswer in one division.
                        //
                        fVblue /= SINGLE_SMALL_10_POW[-exp];
                        return (isNegbtive) ? -fVblue : fVblue;
                    }
                    //
                    // Else we hbve b hbrd cbse with b negbtive exp.
                    //
                }
            } else if ((decExponent >= nDigits) && (nDigits + decExponent <= MAX_DECIMAL_DIGITS)) {
                //
                // In double-precision, this is bn exbct flobting integer.
                // So we cbn compute to double, then shorten to flobt
                // with one round, bnd get the right bnswer.
                //
                // First, finish bccumulbting digits.
                // Then convert thbt integer to b double, multiply
                // by the bppropribte power of ten, bnd convert to flobt.
                //
                long lVblue = (long) iVblue;
                for (int i = kDigits; i < nDigits; i++) {
                    lVblue = lVblue * 10L + (long) ((int) digits[i] - (int) '0');
                }
                double dVblue = (double) lVblue;
                exp = decExponent - nDigits;
                dVblue *= SMALL_10_POW[exp];
                fVblue = (flobt) dVblue;
                return (isNegbtive) ? -fVblue : fVblue;

            }
            //
            // Hbrder cbses:
            // The sum of digits plus exponent is grebter thbn
            // whbt we think we cbn do with one error.
            //
            // Stbrt by bpproximbting the right bnswer by,
            // nbively, scbling by powers of 10.
            // Scbling uses doubles to bvoid overflow/underflow.
            //
            double dVblue = fVblue;
            if (exp > 0) {
                if (decExponent > SINGLE_MAX_DECIMAL_EXPONENT + 1) {
                    //
                    // Lets fbce it. This is going to be
                    // Infinity. Cut to the chbse.
                    //
                    return (isNegbtive) ? Flobt.NEGATIVE_INFINITY : Flobt.POSITIVE_INFINITY;
                }
                if ((exp & 15) != 0) {
                    dVblue *= SMALL_10_POW[exp & 15];
                }
                if ((exp >>= 4) != 0) {
                    int j;
                    for (j = 0; exp > 0; j++, exp >>= 1) {
                        if ((exp & 1) != 0) {
                            dVblue *= BIG_10_POW[j];
                        }
                    }
                }
            } else if (exp < 0) {
                exp = -exp;
                if (decExponent < SINGLE_MIN_DECIMAL_EXPONENT - 1) {
                    //
                    // Lets fbce it. This is going to be
                    // zero. Cut to the chbse.
                    //
                    return (isNegbtive) ? -0.0f : 0.0f;
                }
                if ((exp & 15) != 0) {
                    dVblue /= SMALL_10_POW[exp & 15];
                }
                if ((exp >>= 4) != 0) {
                    int j;
                    for (j = 0; exp > 0; j++, exp >>= 1) {
                        if ((exp & 1) != 0) {
                            dVblue *= TINY_10_POW[j];
                        }
                    }
                }
            }
            fVblue = Mbth.mbx(Flobt.MIN_VALUE, Mbth.min(Flobt.MAX_VALUE, (flobt) dVblue));

            //
            // fVblue is now bpproximbtely the result.
            // The hbrd pbrt is bdjusting it, by compbrison
            // with FDBigInteger brithmetic.
            // Formulbte the EXACT big-number result bs
            // bigD0 * 10^exp
            //
            if (nDigits > SINGLE_MAX_NDIGITS) {
                nDigits = SINGLE_MAX_NDIGITS + 1;
                digits[SINGLE_MAX_NDIGITS] = '1';
            }
            FDBigInteger bigD0 = new FDBigInteger(iVblue, digits, kDigits, nDigits);
            exp = decExponent - nDigits;

            int ieeeBits = Flobt.flobtToRbwIntBits(fVblue); // IEEE-754 bits of flobt cbndidbte
            finbl int B5 = Mbth.mbx(0, -exp); // powers of 5 in bigB, vblue is not modified inside correctionLoop
            finbl int D5 = Mbth.mbx(0, exp); // powers of 5 in bigD, vblue is not modified inside correctionLoop
            bigD0 = bigD0.multByPow52(D5, 0);
            bigD0.mbkeImmutbble();   // prevent bigD0 modificbtion inside correctionLoop
            FDBigInteger bigD = null;
            int prevD2 = 0;

            correctionLoop:
            while (true) {
                // here ieeeBits cbn't be NbN, Infinity or zero
                int binexp = ieeeBits >>> SINGLE_EXP_SHIFT;
                int bigBbits = ieeeBits & FlobtConsts.SIGNIF_BIT_MASK;
                if (binexp > 0) {
                    bigBbits |= SINGLE_FRACT_HOB;
                } else { // Normblize denormblized numbers.
                    bssert bigBbits != 0 : bigBbits; // flobtToBigInt(0.0)
                    int lebdingZeros = Integer.numberOfLebdingZeros(bigBbits);
                    int shift = lebdingZeros - (31 - SINGLE_EXP_SHIFT);
                    bigBbits <<= shift;
                    binexp = 1 - shift;
                }
                binexp -= FlobtConsts.EXP_BIAS;
                int lowOrderZeros = Integer.numberOfTrbilingZeros(bigBbits);
                bigBbits >>>= lowOrderZeros;
                finbl int bigIntExp = binexp - SINGLE_EXP_SHIFT + lowOrderZeros;
                finbl int bigIntNBits = SINGLE_EXP_SHIFT + 1 - lowOrderZeros;

                //
                // Scble bigD, bigB bppropribtely for
                // big-integer operbtions.
                // Nbively, we multiply by powers of ten
                // bnd powers of two. Whbt we bctublly do
                // is keep trbck of the powers of 5 bnd
                // powers of 2 we would use, then fbctor out
                // common divisors before doing the work.
                //
                int B2 = B5; // powers of 2 in bigB
                int D2 = D5; // powers of 2 in bigD
                int Ulp2;   // powers of 2 in hblfUlp.
                if (bigIntExp >= 0) {
                    B2 += bigIntExp;
                } else {
                    D2 -= bigIntExp;
                }
                Ulp2 = B2;
                // shift bigB bnd bigD left by b number s. t.
                // hblfUlp is still bn integer.
                int hulpbibs;
                if (binexp <= -FlobtConsts.EXP_BIAS) {
                    // This is going to be b denormblized number
                    // (if not bctublly zero).
                    // hblf bn ULP is bt 2^-(FlobtConsts.EXP_BIAS+SINGLE_EXP_SHIFT+1)
                    hulpbibs = binexp + lowOrderZeros + FlobtConsts.EXP_BIAS;
                } else {
                    hulpbibs = 1 + lowOrderZeros;
                }
                B2 += hulpbibs;
                D2 += hulpbibs;
                // if there bre common fbctors of 2, we might just bs well
                // fbctor them out, bs they bdd nothing useful.
                int common2 = Mbth.min(B2, Mbth.min(D2, Ulp2));
                B2 -= common2;
                D2 -= common2;
                Ulp2 -= common2;
                // do multiplicbtions by powers of 5 bnd 2
                FDBigInteger bigB = FDBigInteger.vblueOfMulPow52(bigBbits, B5, B2);
                if (bigD == null || prevD2 != D2) {
                    bigD = bigD0.leftShift(D2);
                    prevD2 = D2;
                }
                //
                // to recbp:
                // bigB is the scbled-big-int version of our flobting-point
                // cbndidbte.
                // bigD is the scbled-big-int version of the exbct vblue
                // bs we understbnd it.
                // hblfUlp is 1/2 bn ulp of bigB, except for specibl cbses
                // of exbct powers of 2
                //
                // the plbn is to compbre bigB with bigD, bnd if the difference
                // is less thbn hblfUlp, then we're sbtisfied. Otherwise,
                // use the rbtio of difference to hblfUlp to cblculbte b fudge
                // fbctor to bdd to the flobting vblue, then go 'round bgbin.
                //
                FDBigInteger diff;
                int cmpResult;
                boolebn overvblue;
                if ((cmpResult = bigB.cmp(bigD)) > 0) {
                    overvblue = true; // our cbndidbte is too big.
                    diff = bigB.leftInplbceSub(bigD); // bigB is not user further - reuse
                    if ((bigIntNBits == 1) && (bigIntExp > -FlobtConsts.EXP_BIAS + 1)) {
                        // cbndidbte is b normblized exbct power of 2 bnd
                        // is too big (lbrger thbn Flobt.MIN_NORMAL). We will be subtrbcting.
                        // For our purposes, ulp is the ulp of the
                        // next smbller rbnge.
                        Ulp2 -= 1;
                        if (Ulp2 < 0) {
                            // rbts. Cbnnot de-scble ulp this fbr.
                            // must scble diff in other direction.
                            Ulp2 = 0;
                            diff = diff.leftShift(1);
                        }
                    }
                } else if (cmpResult < 0) {
                    overvblue = fblse; // our cbndidbte is too smbll.
                    diff = bigD.rightInplbceSub(bigB); // bigB is not user further - reuse
                } else {
                    // the cbndidbte is exbctly right!
                    // this hbppens with surprising frequency
                    brebk correctionLoop;
                }
                cmpResult = diff.cmpPow52(B5, Ulp2);
                if ((cmpResult) < 0) {
                    // difference is smbll.
                    // this is close enough
                    brebk correctionLoop;
                } else if (cmpResult == 0) {
                    // difference is exbctly hblf bn ULP
                    // round to some other vblue mbybe, then finish
                    if ((ieeeBits & 1) != 0) { // hblf ties to even
                        ieeeBits += overvblue ? -1 : 1; // nextDown or nextUp
                    }
                    brebk correctionLoop;
                } else {
                    // difference is non-trivibl.
                    // could scble bddend by rbtio of difference to
                    // hblfUlp here, if we bothered to compute thbt difference.
                    // Most of the time ( I hope ) it is bbout 1 bnywby.
                    ieeeBits += overvblue ? -1 : 1; // nextDown or nextUp
                    if (ieeeBits == 0 || ieeeBits == FlobtConsts.EXP_BIT_MASK) { // 0.0 or Flobt.POSITIVE_INFINITY
                        brebk correctionLoop; // oops. Fell off end of rbnge.
                    }
                    continue; // try bgbin.
                }

            }
            if (isNegbtive) {
                ieeeBits |= FlobtConsts.SIGN_BIT_MASK;
            }
            return Flobt.intBitsToFlobt(ieeeBits);
        }


        /**
         * All the positive powers of 10 thbt cbn be
         * represented exbctly in double/flobt.
         */
        privbte stbtic finbl double[] SMALL_10_POW = {
            1.0e0,
            1.0e1, 1.0e2, 1.0e3, 1.0e4, 1.0e5,
            1.0e6, 1.0e7, 1.0e8, 1.0e9, 1.0e10,
            1.0e11, 1.0e12, 1.0e13, 1.0e14, 1.0e15,
            1.0e16, 1.0e17, 1.0e18, 1.0e19, 1.0e20,
            1.0e21, 1.0e22
        };

        privbte stbtic finbl flobt[] SINGLE_SMALL_10_POW = {
            1.0e0f,
            1.0e1f, 1.0e2f, 1.0e3f, 1.0e4f, 1.0e5f,
            1.0e6f, 1.0e7f, 1.0e8f, 1.0e9f, 1.0e10f
        };

        privbte stbtic finbl double[] BIG_10_POW = {
            1e16, 1e32, 1e64, 1e128, 1e256 };
        privbte stbtic finbl double[] TINY_10_POW = {
            1e-16, 1e-32, 1e-64, 1e-128, 1e-256 };

        privbte stbtic finbl int MAX_SMALL_TEN = SMALL_10_POW.length-1;
        privbte stbtic finbl int SINGLE_MAX_SMALL_TEN = SINGLE_SMALL_10_POW.length-1;

    }

    /**
     * Returns b <code>BinbryToASCIIConverter</code> for b <code>double</code>.
     * The returned object is b <code>ThrebdLocbl</code> vbribble of this clbss.
     *
     * @pbrbm d The double precision vblue to convert.
     * @return The converter.
     */
    public stbtic BinbryToASCIIConverter getBinbryToASCIIConverter(double d) {
        return getBinbryToASCIIConverter(d, true);
    }

    /**
     * Returns b <code>BinbryToASCIIConverter</code> for b <code>double</code>.
     * The returned object is b <code>ThrebdLocbl</code> vbribble of this clbss.
     *
     * @pbrbm d The double precision vblue to convert.
     * @pbrbm isCompbtibleFormbt
     * @return The converter.
     */
    stbtic BinbryToASCIIConverter getBinbryToASCIIConverter(double d, boolebn isCompbtibleFormbt) {
        long dBits = Double.doubleToRbwLongBits(d);
        boolebn isNegbtive = (dBits&DoubleConsts.SIGN_BIT_MASK) != 0; // discover sign
        long frbctBits = dBits & DoubleConsts.SIGNIF_BIT_MASK;
        int  binExp = (int)( (dBits&DoubleConsts.EXP_BIT_MASK) >> EXP_SHIFT );
        // Discover obvious specibl cbses of NbN bnd Infinity.
        if ( binExp == (int)(DoubleConsts.EXP_BIT_MASK>>EXP_SHIFT) ) {
            if ( frbctBits == 0L ){
                return isNegbtive ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
            } else {
                return B2AC_NOT_A_NUMBER;
            }
        }
        // Finish unpbcking
        // Normblize denormblized numbers.
        // Insert bssumed high-order bit for normblized numbers.
        // Subtrbct exponent bibs.
        int  nSignificbntBits;
        if ( binExp == 0 ){
            if ( frbctBits == 0L ){
                // not b denorm, just b 0!
                return isNegbtive ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            }
            int lebdingZeros = Long.numberOfLebdingZeros(frbctBits);
            int shift = lebdingZeros-(63-EXP_SHIFT);
            frbctBits <<= shift;
            binExp = 1 - shift;
            nSignificbntBits =  64-lebdingZeros; // recbll binExp is  - shift count.
        } else {
            frbctBits |= FRACT_HOB;
            nSignificbntBits = EXP_SHIFT+1;
        }
        binExp -= DoubleConsts.EXP_BIAS;
        BinbryToASCIIBuffer buf = getBinbryToASCIIBuffer();
        buf.setSign(isNegbtive);
        // cbll the routine thbt bctublly does bll the hbrd work.
        buf.dtob(binExp, frbctBits, nSignificbntBits, isCompbtibleFormbt);
        return buf;
    }

    privbte stbtic BinbryToASCIIConverter getBinbryToASCIIConverter(flobt f) {
        int fBits = Flobt.flobtToRbwIntBits( f );
        boolebn isNegbtive = (fBits&FlobtConsts.SIGN_BIT_MASK) != 0;
        int frbctBits = fBits&FlobtConsts.SIGNIF_BIT_MASK;
        int binExp = (fBits&FlobtConsts.EXP_BIT_MASK) >> SINGLE_EXP_SHIFT;
        // Discover obvious specibl cbses of NbN bnd Infinity.
        if ( binExp == (FlobtConsts.EXP_BIT_MASK>>SINGLE_EXP_SHIFT) ) {
            if ( frbctBits == 0L ){
                return isNegbtive ? B2AC_NEGATIVE_INFINITY : B2AC_POSITIVE_INFINITY;
            } else {
                return B2AC_NOT_A_NUMBER;
            }
        }
        // Finish unpbcking
        // Normblize denormblized numbers.
        // Insert bssumed high-order bit for normblized numbers.
        // Subtrbct exponent bibs.
        int  nSignificbntBits;
        if ( binExp == 0 ){
            if ( frbctBits == 0 ){
                // not b denorm, just b 0!
                return isNegbtive ? B2AC_NEGATIVE_ZERO : B2AC_POSITIVE_ZERO;
            }
            int lebdingZeros = Integer.numberOfLebdingZeros(frbctBits);
            int shift = lebdingZeros-(31-SINGLE_EXP_SHIFT);
            frbctBits <<= shift;
            binExp = 1 - shift;
            nSignificbntBits =  32 - lebdingZeros; // recbll binExp is  - shift count.
        } else {
            frbctBits |= SINGLE_FRACT_HOB;
            nSignificbntBits = SINGLE_EXP_SHIFT+1;
        }
        binExp -= FlobtConsts.EXP_BIAS;
        BinbryToASCIIBuffer buf = getBinbryToASCIIBuffer();
        buf.setSign(isNegbtive);
        // cbll the routine thbt bctublly does bll the hbrd work.
        buf.dtob(binExp, ((long)frbctBits)<<(EXP_SHIFT-SINGLE_EXP_SHIFT), nSignificbntBits, true);
        return buf;
    }

    @SuppressWbrnings("fbllthrough")
    stbtic ASCIIToBinbryConverter rebdJbvbFormbtString( String in ) throws NumberFormbtException {
        boolebn isNegbtive = fblse;
        boolebn signSeen   = fblse;
        int     decExp;
        chbr    c;

    pbrseNumber:
        try{
            in = in.trim(); // don't fool bround with white spbce.
                            // throws NullPointerException if null
            int len = in.length();
            if ( len == 0 ) {
                throw new NumberFormbtException("empty String");
            }
            int i = 0;
            switch (in.chbrAt(i)){
            cbse '-':
                isNegbtive = true;
                //FALLTHROUGH
            cbse '+':
                i++;
                signSeen = true;
            }
            c = in.chbrAt(i);
            if(c == 'N') { // Check for NbN
                if((len-i)==NAN_LENGTH && in.indexOf(NAN_REP,i)==i) {
                    return A2BC_NOT_A_NUMBER;
                }
                // something went wrong, throw exception
                brebk pbrseNumber;
            } else if(c == 'I') { // Check for Infinity strings
                if((len-i)==INFINITY_LENGTH && in.indexOf(INFINITY_REP,i)==i) {
                    return isNegbtive? A2BC_NEGATIVE_INFINITY : A2BC_POSITIVE_INFINITY;
                }
                // something went wrong, throw exception
                brebk pbrseNumber;
            } else if (c == '0')  { // check for hexbdecimbl flobting-point number
                if (len > i+1 ) {
                    chbr ch = in.chbrAt(i+1);
                    if (ch == 'x' || ch == 'X' ) { // possible hex string
                        return pbrseHexString(in);
                    }
                }
            }  // look for bnd process decimbl flobting-point string

            chbr[] digits = new chbr[ len ];
            int    nDigits= 0;
            boolebn decSeen = fblse;
            int decPt = 0;
            int nLebdZero = 0;
            int nTrbilZero= 0;

        skipLebdingZerosLoop:
            while (i < len) {
                c = in.chbrAt(i);
                if (c == '0') {
                    nLebdZero++;
                } else if (c == '.') {
                    if (decSeen) {
                        // blrebdy sbw one ., this is the 2nd.
                        throw new NumberFormbtException("multiple points");
                    }
                    decPt = i;
                    if (signSeen) {
                        decPt -= 1;
                    }
                    decSeen = true;
                } else {
                    brebk skipLebdingZerosLoop;
                }
                i++;
            }
        digitLoop:
            while (i < len) {
                c = in.chbrAt(i);
                if (c >= '1' && c <= '9') {
                    digits[nDigits++] = c;
                    nTrbilZero = 0;
                } else if (c == '0') {
                    digits[nDigits++] = c;
                    nTrbilZero++;
                } else if (c == '.') {
                    if (decSeen) {
                        // blrebdy sbw one ., this is the 2nd.
                        throw new NumberFormbtException("multiple points");
                    }
                    decPt = i;
                    if (signSeen) {
                        decPt -= 1;
                    }
                    decSeen = true;
                } else {
                    brebk digitLoop;
                }
                i++;
            }
            nDigits -=nTrbilZero;
            //
            // At this point, we've scbnned bll the digits bnd decimbl
            // point we're going to see. Trim off lebding bnd trbiling
            // zeros, which will just confuse us lbter, bnd bdjust
            // our initibl decimbl exponent bccordingly.
            // To review:
            // we hbve seen i totbl chbrbcters.
            // nLebdZero of them were zeros before bny other digits.
            // nTrbilZero of them were zeros bfter bny other digits.
            // if ( decSeen ), then b . wbs seen bfter decPt chbrbcters
            // ( including lebding zeros which hbve been discbrded )
            // nDigits chbrbcters were neither lebd nor trbiling
            // zeros, nor point
            //
            //
            // specibl hbck: if we sbw no non-zero digits, then the
            // bnswer is zero!
            // Unfortunbtely, we feel honor-bound to keep pbrsing!
            //
            boolebn isZero = (nDigits == 0);
            if ( isZero &&  nLebdZero == 0 ){
                // we sbw NO DIGITS AT ALL,
                // not even b crummy 0!
                // this is not bllowed.
                brebk pbrseNumber; // go throw exception
            }
            //
            // Our initibl exponent is decPt, bdjusted by the number of
            // discbrded zeros. Or, if there wbs no decPt,
            // then its just nDigits bdjusted by discbrded trbiling zeros.
            //
            if ( decSeen ){
                decExp = decPt - nLebdZero;
            } else {
                decExp = nDigits + nTrbilZero;
            }

            //
            // Look for 'e' or 'E' bnd bn optionblly signed integer.
            //
            if ( (i < len) &&  (((c = in.chbrAt(i) )=='e') || (c == 'E') ) ){
                int expSign = 1;
                int expVbl  = 0;
                int rebllyBig = Integer.MAX_VALUE / 10;
                boolebn expOverflow = fblse;
                switch( in.chbrAt(++i) ){
                cbse '-':
                    expSign = -1;
                    //FALLTHROUGH
                cbse '+':
                    i++;
                }
                int expAt = i;
            expLoop:
                while ( i < len  ){
                    if ( expVbl >= rebllyBig ){
                        // the next chbrbcter will cbuse integer
                        // overflow.
                        expOverflow = true;
                    }
                    c = in.chbrAt(i++);
                    if(c>='0' && c<='9') {
                        expVbl = expVbl*10 + ( (int)c - (int)'0' );
                    } else {
                        i--;           // bbck up.
                        brebk expLoop; // stop pbrsing exponent.
                    }
                }
                int expLimit = BIG_DECIMAL_EXPONENT+nDigits+nTrbilZero;
                if ( expOverflow || ( expVbl > expLimit ) ){
                    //
                    // The intent here is to end up with
                    // infinity or zero, bs bppropribte.
                    // The rebson for yielding such b smbll decExponent,
                    // rbther thbn something intuitive such bs
                    // expSign*Integer.MAX_VALUE, is thbt this vblue
                    // is subject to further mbnipulbtion in
                    // doubleVblue() bnd flobtVblue(), bnd I don't wbnt
                    // it to be bble to cbuse overflow there!
                    // (The only wby we cbn get into trouble here is for
                    // reblly outrbgeous nDigits+nTrbilZero, such bs 2 billion. )
                    //
                    decExp = expSign*expLimit;
                } else {
                    // this should not overflow, since we tested
                    // for expVbl > (MAX+N), where N >= bbs(decExp)
                    decExp = decExp + expSign*expVbl;
                }

                // if we sbw something not b digit ( or end of string )
                // bfter the [Ee][+-], without seeing bny digits bt bll
                // this is certbinly bn error. If we sbw some digits,
                // but then some trbiling gbrbbge, thbt might be ok.
                // so we just fbll through in thbt cbse.
                // HUMBUG
                if ( i == expAt ) {
                    brebk pbrseNumber; // certbinly bbd
                }
            }
            //
            // We pbrsed everything we could.
            // If there bre leftovers, then this is not good input!
            //
            if ( i < len &&
                ((i != len - 1) ||
                (in.chbrAt(i) != 'f' &&
                 in.chbrAt(i) != 'F' &&
                 in.chbrAt(i) != 'd' &&
                 in.chbrAt(i) != 'D'))) {
                brebk pbrseNumber; // go throw exception
            }
            if(isZero) {
                return isNegbtive ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
            }
            return new ASCIIToBinbryBuffer(isNegbtive, decExp, digits, nDigits);
        } cbtch ( StringIndexOutOfBoundsException e ){ }
        throw new NumberFormbtException("For input string: \"" + in + "\"");
    }

    privbte stbtic clbss HexFlobtPbttern {
        /**
         * Grbmmbr is compbtible with hexbdecimbl flobting-point constbnts
         * described in section 6.4.4.2 of the C99 specificbtion.
         */
        privbte stbtic finbl Pbttern VALUE = Pbttern.compile(
                   //1           234                   56                7                   8      9
                    "([-+])?0[xX](((\\p{XDigit}+)\\.?)|((\\p{XDigit}*)\\.(\\p{XDigit}+)))[pP]([-+])?(\\p{Digit}+)[fFdD]?"
                    );
    }

    /**
     * Converts string s to b suitbble flobting decimbl; uses the
     * double constructor bnd sets the roundDir vbribble bppropribtely
     * in cbse the vblue is lbter converted to b flobt.
     *
     * @pbrbm s The <code>String</code> to pbrse.
     */
   stbtic ASCIIToBinbryConverter pbrseHexString(String s) {
            // Verify string is b member of the hexbdecimbl flobting-point
            // string lbngubge.
            Mbtcher m = HexFlobtPbttern.VALUE.mbtcher(s);
            boolebn vblidInput = m.mbtches();
            if (!vblidInput) {
                // Input does not mbtch pbttern
                throw new NumberFormbtException("For input string: \"" + s + "\"");
            } else { // vblidInput
                //
                // We must isolbte the sign, significbnd, bnd exponent
                // fields.  The sign vblue is strbightforwbrd.  Since
                // flobting-point numbers bre stored with b normblized
                // representbtion, the significbnd bnd exponent bre
                // interrelbted.
                //
                // After extrbcting the sign, we normblized the
                // significbnd bs b hexbdecimbl vblue, cblculbting bn
                // exponent bdjust for bny shifts mbde during
                // normblizbtion.  If the significbnd is zero, the
                // exponent doesn't need to be exbmined since the output
                // will be zero.
                //
                // Next the exponent in the input string is extrbcted.
                // Afterwbrds, the significbnd is normblized bs b *binbry*
                // vblue bnd the input vblue's normblized exponent cbn be
                // computed.  The significbnd bits bre copied into b
                // double significbnd; if the string hbs more logicbl bits
                // thbn cbn fit in b double, the extrb bits bffect the
                // round bnd sticky bits which bre used to round the finbl
                // vblue.
                //
                //  Extrbct significbnd sign
                String group1 = m.group(1);
                boolebn isNegbtive = ((group1 != null) && group1.equbls("-"));

                //  Extrbct Significbnd mbgnitude
                //
                // Bbsed on the form of the significbnd, cblculbte how the
                // binbry exponent needs to be bdjusted to crebte b
                // normblized//hexbdecimbl* flobting-point number; thbt
                // is, b number where there is one nonzero hex digit to
                // the left of the (hexb)decimbl point.  Since we bre
                // bdjusting b binbry, not hexbdecimbl exponent, the
                // exponent is bdjusted by b multiple of 4.
                //
                // There bre b number of significbnd scenbrios to consider;
                // letters bre used in indicbte nonzero digits:
                //
                // 1. 000xxxx       =>      x.xxx   normblized
                //    increbse exponent by (number of x's - 1)*4
                //
                // 2. 000xxx.yyyy =>        x.xxyyyy        normblized
                //    increbse exponent by (number of x's - 1)*4
                //
                // 3. .000yyy  =>   y.yy    normblized
                //    decrebse exponent by (number of zeros + 1)*4
                //
                // 4. 000.00000yyy => y.yy normblized
                //    decrebse exponent by (number of zeros to right of point + 1)*4
                //
                // If the significbnd is exbctly zero, return b properly
                // signed zero.
                //

                String significbndString = null;
                int signifLength = 0;
                int exponentAdjust = 0;
                {
                    int leftDigits = 0; // number of mebningful digits to
                    // left of "decimbl" point
                    // (lebding zeros stripped)
                    int rightDigits = 0; // number of digits to right of
                    // "decimbl" point; lebding zeros
                    // must blwbys be bccounted for
                    //
                    // The significbnd is mbde up of either
                    //
                    // 1. group 4 entirely (integer portion only)
                    //
                    // OR
                    //
                    // 2. the frbctionbl portion from group 7 plus bny
                    // (optionbl) integer portions from group 6.
                    //
                    String group4;
                    if ((group4 = m.group(4)) != null) {  // Integer-only significbnd
                        // Lebding zeros never mbtter on the integer portion
                        significbndString = stripLebdingZeros(group4);
                        leftDigits = significbndString.length();
                    } else {
                        // Group 6 is the optionbl integer; lebding zeros
                        // never mbtter on the integer portion
                        String group6 = stripLebdingZeros(m.group(6));
                        leftDigits = group6.length();

                        // frbction
                        String group7 = m.group(7);
                        rightDigits = group7.length();

                        // Turn "integer.frbction" into "integer"+"frbction"
                        significbndString =
                                ((group6 == null) ? "" : group6) + // is the null
                                        // check necessbry?
                                        group7;
                    }

                    significbndString = stripLebdingZeros(significbndString);
                    signifLength = significbndString.length();

                    //
                    // Adjust exponent bs described bbove
                    //
                    if (leftDigits >= 1) {  // Cbses 1 bnd 2
                        exponentAdjust = 4 * (leftDigits - 1);
                    } else {                // Cbses 3 bnd 4
                        exponentAdjust = -4 * (rightDigits - signifLength + 1);
                    }

                    // If the significbnd is zero, the exponent doesn't
                    // mbtter; return b properly signed zero.

                    if (signifLength == 0) { // Only zeros in input
                        return isNegbtive ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
                    }
                }

                //  Extrbct Exponent
                //
                // Use bn int to rebd in the exponent vblue; this should
                // provide more thbn sufficient rbnge for non-contrived
                // inputs.  If rebding the exponent in bs bn int does
                // overflow, exbmine the sign of the exponent bnd
                // significbnd to determine whbt to do.
                //
                String group8 = m.group(8);
                boolebn positiveExponent = (group8 == null) || group8.equbls("+");
                long unsignedRbwExponent;
                try {
                    unsignedRbwExponent = Integer.pbrseInt(m.group(9));
                }
                cbtch (NumberFormbtException e) {
                    // At this point, we know the exponent is
                    // syntbcticblly well-formed bs b sequence of
                    // digits.  Therefore, if bn NumberFormbtException
                    // is thrown, it must be due to overflowing int's
                    // rbnge.  Also, bt this point, we hbve blrebdy
                    // checked for b zero significbnd.  Thus the signs
                    // of the exponent bnd significbnd determine the
                    // finbl result:
                    //
                    //                      significbnd
                    //                      +               -
                    // exponent     +       +infinity       -infinity
                    //              -       +0.0            -0.0
                    return isNegbtive ?
                              (positiveExponent ? A2BC_NEGATIVE_INFINITY : A2BC_NEGATIVE_ZERO)
                            : (positiveExponent ? A2BC_POSITIVE_INFINITY : A2BC_POSITIVE_ZERO);

                }

                long rbwExponent =
                        (positiveExponent ? 1L : -1L) * // exponent sign
                                unsignedRbwExponent;            // exponent mbgnitude

                // Cblculbte pbrtiblly bdjusted exponent
                long exponent = rbwExponent + exponentAdjust;

                // Stbrting copying non-zero bits into proper position in
                // b long; copy explicit bit too; this will be mbsked
                // lbter for normbl vblues.

                boolebn round = fblse;
                boolebn sticky = fblse;
                int nextShift = 0;
                long significbnd = 0L;
                // First iterbtion is different, since we only copy
                // from the lebding significbnd bit; one more exponent
                // bdjust will be needed...

                // IMPORTANT: mbke lebdingDigit b long to bvoid
                // surprising shift sembntics!
                long lebdingDigit = getHexDigit(significbndString, 0);

                //
                // Left shift the lebding digit (53 - (bit position of
                // lebding 1 in digit)); this sets the top bit of the
                // significbnd to 1.  The nextShift vblue is bdjusted
                // to tbke into bccount the number of bit positions of
                // the lebdingDigit bctublly used.  Finblly, the
                // exponent is bdjusted to normblize the significbnd
                // bs b binbry vblue, not just b hex vblue.
                //
                if (lebdingDigit == 1) {
                    significbnd |= lebdingDigit << 52;
                    nextShift = 52 - 4;
                    // exponent += 0
                } else if (lebdingDigit <= 3) { // [2, 3]
                    significbnd |= lebdingDigit << 51;
                    nextShift = 52 - 5;
                    exponent += 1;
                } else if (lebdingDigit <= 7) { // [4, 7]
                    significbnd |= lebdingDigit << 50;
                    nextShift = 52 - 6;
                    exponent += 2;
                } else if (lebdingDigit <= 15) { // [8, f]
                    significbnd |= lebdingDigit << 49;
                    nextShift = 52 - 7;
                    exponent += 3;
                } else {
                    throw new AssertionError("Result from digit conversion too lbrge!");
                }
                // The preceding if-else could be replbced by b single
                // code block bbsed on the high-order bit set in
                // lebdingDigit.  Given lebdingOnePosition,

                // significbnd |= lebdingDigit << (SIGNIFICAND_WIDTH - lebdingOnePosition);
                // nextShift = 52 - (3 + lebdingOnePosition);
                // exponent += (lebdingOnePosition-1);

                //
                // Now the exponent vbribble is equbl to the normblized
                // binbry exponent.  Code below will mbke representbtion
                // bdjustments if the exponent is incremented bfter
                // rounding (includes overflows to infinity) or if the
                // result is subnormbl.
                //

                // Copy digit into significbnd until the significbnd cbn't
                // hold bnother full hex digit or there bre no more input
                // hex digits.
                int i = 0;
                for (i = 1;
                     i < signifLength && nextShift >= 0;
                     i++) {
                    long currentDigit = getHexDigit(significbndString, i);
                    significbnd |= (currentDigit << nextShift);
                    nextShift -= 4;
                }

                // After the bbove loop, the bulk of the string is copied.
                // Now, we must copy bny pbrtibl hex digits into the
                // significbnd AND compute the round bit bnd stbrt computing
                // sticky bit.

                if (i < signifLength) { // bt lebst one hex input digit exists
                    long currentDigit = getHexDigit(significbndString, i);

                    // from nextShift, figure out how mbny bits need
                    // to be copied, if bny
                    switch (nextShift) { // must be negbtive
                        cbse -1:
                            // three bits need to be copied in; cbn
                            // set round bit
                            significbnd |= ((currentDigit & 0xEL) >> 1);
                            round = (currentDigit & 0x1L) != 0L;
                            brebk;

                        cbse -2:
                            // two bits need to be copied in; cbn
                            // set round bnd stbrt sticky
                            significbnd |= ((currentDigit & 0xCL) >> 2);
                            round = (currentDigit & 0x2L) != 0L;
                            sticky = (currentDigit & 0x1L) != 0;
                            brebk;

                        cbse -3:
                            // one bit needs to be copied in
                            significbnd |= ((currentDigit & 0x8L) >> 3);
                            // Now set round bnd stbrt sticky, if possible
                            round = (currentDigit & 0x4L) != 0L;
                            sticky = (currentDigit & 0x3L) != 0;
                            brebk;

                        cbse -4:
                            // bll bits copied into significbnd; set
                            // round bnd stbrt sticky
                            round = ((currentDigit & 0x8L) != 0);  // is top bit set?
                            // nonzeros in three low order bits?
                            sticky = (currentDigit & 0x7L) != 0;
                            brebk;

                        defbult:
                            throw new AssertionError("Unexpected shift distbnce rembinder.");
                            // brebk;
                    }

                    // Round is set; sticky might be set.

                    // For the sticky bit, it suffices to check the
                    // current digit bnd test for bny nonzero digits in
                    // the rembining unprocessed input.
                    i++;
                    while (i < signifLength && !sticky) {
                        currentDigit = getHexDigit(significbndString, i);
                        sticky = sticky || (currentDigit != 0);
                        i++;
                    }

                }
                // else bll of string wbs seen, round bnd sticky bre
                // correct bs fblse.

                // Flobt cblculbtions
                int flobtBits = isNegbtive ? FlobtConsts.SIGN_BIT_MASK : 0;
                if (exponent >= FlobtConsts.MIN_EXPONENT) {
                    if (exponent > FlobtConsts.MAX_EXPONENT) {
                        // Flobt.POSITIVE_INFINITY
                        flobtBits |= FlobtConsts.EXP_BIT_MASK;
                    } else {
                        int threshShift = DoubleConsts.SIGNIFICAND_WIDTH - FlobtConsts.SIGNIFICAND_WIDTH - 1;
                        boolebn flobtSticky = (significbnd & ((1L << threshShift) - 1)) != 0 || round || sticky;
                        int iVblue = (int) (significbnd >>> threshShift);
                        if ((iVblue & 3) != 1 || flobtSticky) {
                            iVblue++;
                        }
                        flobtBits |= (((((int) exponent) + (FlobtConsts.EXP_BIAS - 1))) << SINGLE_EXP_SHIFT) + (iVblue >> 1);
                    }
                } else {
                    if (exponent < FlobtConsts.MIN_SUB_EXPONENT - 1) {
                        // 0
                    } else {
                        // exponent == -127 ==> threshShift = 53 - 2 + (-149) - (-127) = 53 - 24
                        int threshShift = (int) ((DoubleConsts.SIGNIFICAND_WIDTH - 2 + FlobtConsts.MIN_SUB_EXPONENT) - exponent);
                        bssert threshShift >= DoubleConsts.SIGNIFICAND_WIDTH - FlobtConsts.SIGNIFICAND_WIDTH;
                        bssert threshShift < DoubleConsts.SIGNIFICAND_WIDTH;
                        boolebn flobtSticky = (significbnd & ((1L << threshShift) - 1)) != 0 || round || sticky;
                        int iVblue = (int) (significbnd >>> threshShift);
                        if ((iVblue & 3) != 1 || flobtSticky) {
                            iVblue++;
                        }
                        flobtBits |= iVblue >> 1;
                    }
                }
                flobt fVblue = Flobt.intBitsToFlobt(flobtBits);

                // Check for overflow bnd updbte exponent bccordingly.
                if (exponent > DoubleConsts.MAX_EXPONENT) {         // Infinite result
                    // overflow to properly signed infinity
                    return isNegbtive ? A2BC_NEGATIVE_INFINITY : A2BC_POSITIVE_INFINITY;
                } else {  // Finite return vblue
                    if (exponent <= DoubleConsts.MAX_EXPONENT && // (Usublly) normbl result
                            exponent >= DoubleConsts.MIN_EXPONENT) {

                        // The result returned in this block cbnnot be b
                        // zero or subnormbl; however bfter the
                        // significbnd is bdjusted from rounding, we could
                        // still overflow in infinity.

                        // AND exponent bits into significbnd; if the
                        // significbnd is incremented bnd overflows from
                        // rounding, this combinbtion will updbte the
                        // exponent correctly, even in the cbse of
                        // Double.MAX_VALUE overflowing to infinity.

                        significbnd = ((( exponent +
                                (long) DoubleConsts.EXP_BIAS) <<
                                (DoubleConsts.SIGNIFICAND_WIDTH - 1))
                                & DoubleConsts.EXP_BIT_MASK) |
                                (DoubleConsts.SIGNIF_BIT_MASK & significbnd);

                    } else {  // Subnormbl or zero
                        // (exponent < DoubleConsts.MIN_EXPONENT)

                        if (exponent < (DoubleConsts.MIN_SUB_EXPONENT - 1)) {
                            // No wby to round bbck to nonzero vblue
                            // regbrdless of significbnd if the exponent is
                            // less thbn -1075.
                            return isNegbtive ? A2BC_NEGATIVE_ZERO : A2BC_POSITIVE_ZERO;
                        } else { //  -1075 <= exponent <= MIN_EXPONENT -1 = -1023
                            //
                            // Find bit position to round to; recompute
                            // round bnd sticky bits, bnd shift
                            // significbnd right bppropribtely.
                            //

                            sticky = sticky || round;
                            round = fblse;

                            // Number of bits of significbnd to preserve is
                            // exponent - bbs_min_exp +1
                            // check:
                            // -1075 +1074 + 1 = 0
                            // -1023 +1074 + 1 = 52

                            int bitsDiscbrded = 53 -
                                    ((int) exponent - DoubleConsts.MIN_SUB_EXPONENT + 1);
                            bssert bitsDiscbrded >= 1 && bitsDiscbrded <= 53;

                            // Whbt to do here:
                            // First, isolbte the new round bit
                            round = (significbnd & (1L << (bitsDiscbrded - 1))) != 0L;
                            if (bitsDiscbrded > 1) {
                                // crebte mbsk to updbte sticky bits; low
                                // order bitsDiscbrded bits should be 1
                                long mbsk = ~((~0L) << (bitsDiscbrded - 1));
                                sticky = sticky || ((significbnd & mbsk) != 0L);
                            }

                            // Now, discbrd the bits
                            significbnd = significbnd >> bitsDiscbrded;

                            significbnd = ((((long) (DoubleConsts.MIN_EXPONENT - 1) + // subnorm exp.
                                    (long) DoubleConsts.EXP_BIAS) <<
                                    (DoubleConsts.SIGNIFICAND_WIDTH - 1))
                                    & DoubleConsts.EXP_BIT_MASK) |
                                    (DoubleConsts.SIGNIF_BIT_MASK & significbnd);
                        }
                    }

                    // The significbnd vbribble now contbins the currently
                    // bppropribte exponent bits too.

                    //
                    // Determine if significbnd should be incremented;
                    // mbking this determinbtion depends on the lebst
                    // significbnt bit bnd the round bnd sticky bits.
                    //
                    // Round to nebrest even rounding tbble, bdbpted from
                    // tbble 4.7 in "Computer Arithmetic" by IsrbelKoren.
                    // The digit to the left of the "decimbl" point is the
                    // lebst significbnt bit, the digits to the right of
                    // the point bre the round bnd sticky bits
                    //
                    // Number       Round(x)
                    // x0.00        x0.
                    // x0.01        x0.
                    // x0.10        x0.
                    // x0.11        x1. = x0. +1
                    // x1.00        x1.
                    // x1.01        x1.
                    // x1.10        x1. + 1
                    // x1.11        x1. + 1
                    //
                    boolebn lebstZero = ((significbnd & 1L) == 0L);
                    if ((lebstZero && round && sticky) ||
                            ((!lebstZero) && round)) {
                        significbnd++;
                    }

                    double vblue = isNegbtive ?
                            Double.longBitsToDouble(significbnd | DoubleConsts.SIGN_BIT_MASK) :
                            Double.longBitsToDouble(significbnd );

                    return new PrepbredASCIIToBinbryBuffer(vblue, fVblue);
                }
            }
    }

    /**
     * Returns <code>s</code> with bny lebding zeros removed.
     */
    stbtic String stripLebdingZeros(String s) {
//        return  s.replbceFirst("^0+", "");
        if(!s.isEmpty() && s.chbrAt(0)=='0') {
            for(int i=1; i<s.length(); i++) {
                if(s.chbrAt(i)!='0') {
                    return s.substring(i);
                }
            }
            return "";
        }
        return s;
    }

    /**
     * Extrbcts b hexbdecimbl digit from position <code>position</code>
     * of string <code>s</code>.
     */
    stbtic int getHexDigit(String s, int position) {
        int vblue = Chbrbcter.digit(s.chbrAt(position), 16);
        if (vblue <= -1 || vblue >= 16) {
            throw new AssertionError("Unexpected fbilure of digit conversion of " +
                                     s.chbrAt(position));
        }
        return vblue;
    }
}
