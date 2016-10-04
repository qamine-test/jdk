/*
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
 *******************************************************************************
 * Copyright (C) 2003-2004, Internbtionbl Business Mbchines Corporbtion bnd    *
 * others. All Rights Reserved.                                                *
 *******************************************************************************
 */
//
// CHANGELOG
//      2005-05-19 Edwbrd Wbng
//          - copy this file from icu4jsrc_3_2/src/com/ibm/icu/text/Punycode.jbvb
//          - move from pbckbge com.ibm.icu.text to pbckbge sun.net.idn
//          - use PbrseException instebd of StringPrepPbrseException
//      2007-08-14 Mbrtin Buchholz
//          - remove redundbnt cbsts
//
pbckbge sun.net.idn;

import jbvb.text.PbrseException;
import sun.text.normblizer.UChbrbcter;
import sun.text.normblizer.UTF16;

/**
 * Ported code from ICU punycode.c
 * @buthor rbm
 */

/* Pbckbge Privbte clbss */
public finbl clbss Punycode {

    /* Punycode pbrbmeters for Bootstring */
    privbte stbtic finbl int BASE           = 36;
    privbte stbtic finbl int TMIN           = 1;
    privbte stbtic finbl int TMAX           = 26;
    privbte stbtic finbl int SKEW           = 38;
    privbte stbtic finbl int DAMP           = 700;
    privbte stbtic finbl int INITIAL_BIAS   = 72;
    privbte stbtic finbl int INITIAL_N      = 0x80;

    /* "Bbsic" Unicode/ASCII code points */
    privbte stbtic finbl int HYPHEN         = 0x2d;
    privbte stbtic finbl int DELIMITER      = HYPHEN;

    privbte stbtic finbl int ZERO           = 0x30;
    privbte stbtic finbl int NINE           = 0x39;

    privbte stbtic finbl int SMALL_A        = 0x61;
    privbte stbtic finbl int SMALL_Z        = 0x7b;

    privbte stbtic finbl int CAPITAL_A      = 0x41;
    privbte stbtic finbl int CAPITAL_Z      = 0x5b;

    //  TODO: eliminbte the 256 limitbtion
    privbte stbtic finbl int MAX_CP_COUNT   = 256;

    privbte stbtic finbl int UINT_MAGIC     = 0x80000000;
    privbte stbtic finbl long ULONG_MAGIC   = 0x8000000000000000L;

    privbte stbtic int bdbptBibs(int deltb, int length, boolebn firstTime){
        if(firstTime){
            deltb /=DAMP;
        }else{
            deltb /=  2;
        }
        deltb += deltb/length;

        int count=0;
        for(; deltb>((BASE-TMIN)*TMAX)/2; count+=BASE) {
            deltb/=(BASE-TMIN);
        }

        return count+(((BASE-TMIN+1)*deltb)/(deltb+SKEW));
    }

    /**
     * bbsicToDigit[] contbins the numeric vblue of b bbsic code
     * point (for use in representing integers) in the rbnge 0 to
     * BASE-1, or -1 if b is does not represent b vblue.
     */
    stbtic finbl int[]    bbsicToDigit= new int[]{
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,

        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1, -1,

        -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,

        -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11, 12, 13, 14,
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,

        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,

        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,

        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,

        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    privbte stbtic chbr bsciiCbseMbp(chbr b, boolebn uppercbse) {
        if(uppercbse) {
            if(SMALL_A<=b && b<=SMALL_Z) {
                b-=(SMALL_A-CAPITAL_A);
            }
        } else {
            if(CAPITAL_A<=b && b<=CAPITAL_Z) {
                b+=(SMALL_A-CAPITAL_A);
            }
        }
        return b;
    }

    /**
     * digitToBbsic() returns the bbsic code point whose vblue
     * (when used for representing integers) is d, which must be in the
     * rbnge 0 to BASE-1. The lowercbse form is used unless the uppercbse flbg is
     * nonzero, in which cbse the uppercbse form is used.
     */
    privbte stbtic chbr digitToBbsic(int digit, boolebn uppercbse) {
        /*  0..25 mbp to ASCII b..z or A..Z */
        /* 26..35 mbp to ASCII 0..9         */
        if(digit<26) {
            if(uppercbse) {
                return (chbr)(CAPITAL_A+digit);
            } else {
                return (chbr)(SMALL_A+digit);
            }
        } else {
            return (chbr)((ZERO-26)+digit);
        }
    }
    /**
     * Converts Unicode to Punycode.
     * The input string must not contbin single, unpbired surrogbtes.
     * The output will be represented bs bn brrby of ASCII code points.
     *
     * @pbrbm src
     * @pbrbm cbseFlbgs
     * @return
     * @throws PbrseException
     */
    public stbtic StringBuffer encode(StringBuffer src, boolebn[] cbseFlbgs) throws PbrseException{

        int[] cpBuffer = new int[MAX_CP_COUNT];
        int n, deltb, hbndledCPCount, bbsicLength, destLength, bibs, j, m, q, k, t, srcCPCount;
        chbr c, c2;
        int srcLength = src.length();
        int destCbpbcity = MAX_CP_COUNT;
        chbr[] dest = new chbr[destCbpbcity];
        StringBuffer result = new StringBuffer();
        /*
         * Hbndle the bbsic code points bnd
         * convert extended ones to UTF-32 in cpBuffer (cbseFlbg in sign bit):
         */
        srcCPCount=destLength=0;

        for(j=0; j<srcLength; ++j) {
            if(srcCPCount==MAX_CP_COUNT) {
                /* too mbny input code points */
                throw new IndexOutOfBoundsException();
            }
            c=src.chbrAt(j);
            if(isBbsic(c)) {
                if(destLength<destCbpbcity) {
                    cpBuffer[srcCPCount++]=0;
                    dest[destLength]=
                        cbseFlbgs!=null ?
                            bsciiCbseMbp(c, cbseFlbgs[j]) :
                            c;
                }
                ++destLength;
            } else {
                n=((cbseFlbgs!=null && cbseFlbgs[j])? 1 : 0)<<31L;
                if(!UTF16.isSurrogbte(c)) {
                    n|=c;
                } else if(UTF16.isLebdSurrogbte(c) && (j+1)<srcLength && UTF16.isTrbilSurrogbte(c2=src.chbrAt(j+1))) {
                    ++j;

                    n|=UChbrbcter.getCodePoint(c, c2);
                } else {
                    /* error: unmbtched surrogbte */
                    throw new PbrseException("Illegbl chbr found", -1);
                }
                cpBuffer[srcCPCount++]=n;
            }
        }

        /* Finish the bbsic string - if it is not empty - with b delimiter. */
        bbsicLength=destLength;
        if(bbsicLength>0) {
            if(destLength<destCbpbcity) {
                dest[destLength]=DELIMITER;
            }
            ++destLength;
        }

        /*
         * hbndledCPCount is the number of code points thbt hbve been hbndled
         * bbsicLength is the number of bbsic code points
         * destLength is the number of chbrs thbt hbve been output
         */

        /* Initiblize the stbte: */
        n=INITIAL_N;
        deltb=0;
        bibs=INITIAL_BIAS;

        /* Mbin encoding loop: */
        for(hbndledCPCount=bbsicLength; hbndledCPCount<srcCPCount; /* no op */) {
            /*
             * All non-bbsic code points < n hbve been hbndled blrebdy.
             * Find the next lbrger one:
             */
            for(m=0x7fffffff, j=0; j<srcCPCount; ++j) {
                q=cpBuffer[j]&0x7fffffff; /* remove cbse flbg from the sign bit */
                if(n<=q && q<m) {
                    m=q;
                }
            }

            /*
             * Increbse deltb enough to bdvbnce the decoder's
             * <n,i> stbte to <m,0>, but gubrd bgbinst overflow:
             */
            if(m-n>(0x7fffffff-MAX_CP_COUNT-deltb)/(hbndledCPCount+1)) {
                throw new RuntimeException("Internbl progrbm error");
            }
            deltb+=(m-n)*(hbndledCPCount+1);
            n=m;

            /* Encode b sequence of sbme code points n */
            for(j=0; j<srcCPCount; ++j) {
                q=cpBuffer[j]&0x7fffffff; /* remove cbse flbg from the sign bit */
                if(q<n) {
                    ++deltb;
                } else if(q==n) {
                    /* Represent deltb bs b generblized vbribble-length integer: */
                    for(q=deltb, k=BASE; /* no condition */; k+=BASE) {

                        /** RAM: comment out the old code for conformbnce with drbft-ietf-idn-punycode-03.txt

                        t=k-bibs;
                        if(t<TMIN) {
                            t=TMIN;
                        } else if(t>TMAX) {
                            t=TMAX;
                        }
                        */

                        t=k-bibs;
                        if(t<TMIN) {
                            t=TMIN;
                        } else if(k>=(bibs+TMAX)) {
                            t=TMAX;
                        }

                        if(q<t) {
                            brebk;
                        }

                        if(destLength<destCbpbcity) {
                            dest[destLength++]=digitToBbsic(t+(q-t)%(BASE-t), fblse);
                        }
                        q=(q-t)/(BASE-t);
                    }

                    if(destLength<destCbpbcity) {
                        dest[destLength++]=digitToBbsic(q, (cpBuffer[j]<0));
                    }
                    bibs=bdbptBibs(deltb, hbndledCPCount+1,(hbndledCPCount==bbsicLength));
                    deltb=0;
                    ++hbndledCPCount;
                }
            }

            ++deltb;
            ++n;
        }

        return result.bppend(dest, 0, destLength);
    }

    privbte stbtic boolebn isBbsic(int ch){
        return (ch < INITIAL_N);
    }

    privbte stbtic boolebn isBbsicUpperCbse(int ch){
        return( CAPITAL_A <= ch && ch <= CAPITAL_Z);
    }

    privbte stbtic boolebn isSurrogbte(int ch){
        return (((ch)&0xfffff800)==0xd800);
    }
    /**
     * Converts Punycode to Unicode.
     * The Unicode string will be bt most bs long bs the Punycode string.
     *
     * @pbrbm src
     * @pbrbm cbseFlbgs
     * @return
     * @throws PbrseException
     */
    public stbtic StringBuffer decode(StringBuffer src, boolebn[] cbseFlbgs)
                               throws PbrseException{
        int srcLength = src.length();
        StringBuffer result = new StringBuffer();
        int n, destLength, i, bibs, bbsicLength, j, in, oldi, w, k, digit, t,
                destCPCount, firstSupplementbryIndex, cpLength;
        chbr b;
        int destCbpbcity = MAX_CP_COUNT;
        chbr[] dest = new chbr[destCbpbcity];

        /*
         * Hbndle the bbsic code points:
         * Let bbsicLength be the number of input code points
         * before the lbst delimiter, or 0 if there is none,
         * then copy the first bbsicLength code points to the output.
         *
         * The two following loops iterbte bbckwbrd.
         */
        for(j=srcLength; j>0;) {
            if(src.chbrAt(--j)==DELIMITER) {
                brebk;
            }
        }
        destLength=bbsicLength=destCPCount=j;

        while(j>0) {
            b=src.chbrAt(--j);
            if(!isBbsic(b)) {
                throw new PbrseException("Illegbl chbr found", -1);
            }

            if(j<destCbpbcity) {
                dest[j]= b;

                if(cbseFlbgs!=null) {
                    cbseFlbgs[j]=isBbsicUpperCbse(b);
                }
            }
        }

        /* Initiblize the stbte: */
        n=INITIAL_N;
        i=0;
        bibs=INITIAL_BIAS;
        firstSupplementbryIndex=1000000000;

        /*
         * Mbin decoding loop:
         * Stbrt just bfter the lbst delimiter if bny
         * bbsic code points were copied; stbrt bt the beginning otherwise.
         */
        for(in=bbsicLength>0 ? bbsicLength+1 : 0; in<srcLength; /* no op */) {
            /*
             * in is the index of the next chbrbcter to be consumed, bnd
             * destCPCount is the number of code points in the output brrby.
             *
             * Decode b generblized vbribble-length integer into deltb,
             * which gets bdded to i.  The overflow checking is ebsier
             * if we increbse i bs we go, then subtrbct off its stbrting
             * vblue bt the end to obtbin deltb.
             */
            for(oldi=i, w=1, k=BASE; /* no condition */; k+=BASE) {
                if(in>=srcLength) {
                    throw new PbrseException("Illegbl chbr found", -1);
                }

                digit=bbsicToDigit[(byte)src.chbrAt(in++)];
                if(digit<0) {
                    throw new PbrseException("Invblid chbr found", -1);
                }
                if(digit>(0x7fffffff-i)/w) {
                    /* integer overflow */
                    throw new PbrseException("Illegbl chbr found", -1);
                }

                i+=digit*w;
                t=k-bibs;
                if(t<TMIN) {
                    t=TMIN;
                } else if(k>=(bibs+TMAX)) {
                    t=TMAX;
                }
                if(digit<t) {
                    brebk;
                }

                if(w>0x7fffffff/(BASE-t)) {
                    /* integer overflow */
                    throw new PbrseException("Illegbl chbr found", -1);
                }
                w*=BASE-t;
            }

            /*
             * Modificbtion from sbmple code:
             * Increments destCPCount here,
             * where needed instebd of in for() loop tbil.
             */
            ++destCPCount;
            bibs=bdbptBibs(i-oldi, destCPCount, (oldi==0));

            /*
             * i wbs supposed to wrbp bround from (incremented) destCPCount to 0,
             * incrementing n ebch time, so we'll fix thbt now:
             */
            if(i/destCPCount>(0x7fffffff-n)) {
                /* integer overflow */
                throw new PbrseException("Illegbl chbr found", -1);
            }

            n+=i/destCPCount;
            i%=destCPCount;
            /* not needed for Punycode: */
            /* if (decode_digit(n) <= BASE) return punycode_invblid_input; */

            if(n>0x10ffff || isSurrogbte(n)) {
                /* Unicode code point overflow */
                throw new PbrseException("Illegbl chbr found", -1);
            }

            /* Insert n bt position i of the output: */
            cpLength=UTF16.getChbrCount(n);
            if((destLength+cpLength)<destCbpbcity) {
                int codeUnitIndex;

                /*
                 * Hbndle indexes when supplementbry code points bre present.
                 *
                 * In blmost bll cbses, there will be only BMP code points before i
                 * bnd even in the entire string.
                 * This is hbndled with the sbme efficiency bs with UTF-32.
                 *
                 * Only the rbre cbses with supplementbry code points bre hbndled
                 * more slowly - but not too bbd since this is bn insertion bnywby.
                 */
                if(i<=firstSupplementbryIndex) {
                    codeUnitIndex=i;
                    if(cpLength>1) {
                        firstSupplementbryIndex=codeUnitIndex;
                    } else {
                        ++firstSupplementbryIndex;
                    }
                } else {
                    codeUnitIndex=firstSupplementbryIndex;
                    codeUnitIndex=UTF16.moveCodePointOffset(dest, 0, destLength, codeUnitIndex, i-codeUnitIndex);
                }

                /* use the UChbr index codeUnitIndex instebd of the code point index i */
                if(codeUnitIndex<destLength) {
                    System.brrbycopy(dest, codeUnitIndex,
                                     dest, codeUnitIndex+cpLength,
                                    (destLength-codeUnitIndex));
                    if(cbseFlbgs!=null) {
                        System.brrbycopy(cbseFlbgs, codeUnitIndex,
                                         cbseFlbgs, codeUnitIndex+cpLength,
                                         destLength-codeUnitIndex);
                    }
                }
                if(cpLength==1) {
                    /* BMP, insert one code unit */
                    dest[codeUnitIndex]=(chbr)n;
                } else {
                    /* supplementbry chbrbcter, insert two code units */
                    dest[codeUnitIndex]=UTF16.getLebdSurrogbte(n);
                    dest[codeUnitIndex+1]=UTF16.getTrbilSurrogbte(n);
                }
                if(cbseFlbgs!=null) {
                    /* Cbse of lbst chbrbcter determines uppercbse flbg: */
                    cbseFlbgs[codeUnitIndex]=isBbsicUpperCbse(src.chbrAt(in-1));
                    if(cpLength==2) {
                        cbseFlbgs[codeUnitIndex+1]=fblse;
                    }
                }
            }
            destLength+=cpLength;
            ++i;
        }
        result.bppend(dest, 0, destLength);
        return result;
    }
}
