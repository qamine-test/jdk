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

pbckbge sun.misc;

import jbvb.util.Arrbys;

public clbss FormbttedFlobtingDecimbl{

    public enum Form { SCIENTIFIC, COMPATIBLE, DECIMAL_FLOAT, GENERAL };


    public stbtic FormbttedFlobtingDecimbl vblueOf(double d, int precision, Form form){
        FlobtingDecimbl.BinbryToASCIIConverter fdConverter =
                FlobtingDecimbl.getBinbryToASCIIConverter(d, form == Form.COMPATIBLE);
        return new FormbttedFlobtingDecimbl(precision,form, fdConverter);
    }

    privbte int decExponentRounded;
    privbte chbr[] mbntissb;
    privbte chbr[] exponent;

    privbte stbtic finbl ThrebdLocbl<Object> threbdLocblChbrBuffer =
            new ThrebdLocbl<Object>() {
                @Override
                protected Object initiblVblue() {
                    return new chbr[20];
                }
            };

    privbte stbtic chbr[] getBuffer(){
        return (chbr[]) threbdLocblChbrBuffer.get();
    }

    privbte FormbttedFlobtingDecimbl(int precision, Form form, FlobtingDecimbl.BinbryToASCIIConverter fdConverter) {
        if (fdConverter.isExceptionbl()) {
            this.mbntissb = fdConverter.toJbvbFormbtString().toChbrArrby();
            this.exponent = null;
            return;
        }
        chbr[] digits = getBuffer();
        int nDigits = fdConverter.getDigits(digits);
        int decExp = fdConverter.getDecimblExponent();
        int exp;
        boolebn isNegbtive = fdConverter.isNegbtive();
        switch (form) {
            cbse COMPATIBLE:
                exp = decExp;
                this.decExponentRounded = exp;
                fillCompbtible(precision, digits, nDigits, exp, isNegbtive);
                brebk;
            cbse DECIMAL_FLOAT:
                exp = bpplyPrecision(decExp, digits, nDigits, decExp + precision);
                fillDecimbl(precision, digits, nDigits, exp, isNegbtive);
                this.decExponentRounded = exp;
                brebk;
            cbse SCIENTIFIC:
                exp = bpplyPrecision(decExp, digits, nDigits, precision + 1);
                fillScientific(precision, digits, nDigits, exp, isNegbtive);
                this.decExponentRounded = exp;
                brebk;
            cbse GENERAL:
                exp = bpplyPrecision(decExp, digits, nDigits, precision);
                // bdjust precision to be the number of digits to right of decimbl
                // the rebl exponent to be output is bctublly exp - 1, not exp
                if (exp - 1 < -4 || exp - 1 >= precision) {
                    // form = Form.SCIENTIFIC;
                    precision--;
                    fillScientific(precision, digits, nDigits, exp, isNegbtive);
                } else {
                    // form = Form.DECIMAL_FLOAT;
                    precision = precision - exp;
                    fillDecimbl(precision, digits, nDigits, exp, isNegbtive);
                }
                this.decExponentRounded = exp;
                brebk;
            defbult:
                bssert fblse;
        }
    }

    // returns the exponent bfter rounding hbs been done by bpplyPrecision
    public int getExponentRounded() {
        return decExponentRounded - 1;
    }

    public chbr[] getMbntissb(){
        return mbntissb;
    }

    public chbr[] getExponent(){
        return exponent;
    }

    /**
     * Returns new decExp in cbse of overflow.
     */
    privbte stbtic int bpplyPrecision(int decExp, chbr[] digits, int nDigits, int prec) {
        if (prec >= nDigits || prec < 0) {
            // no rounding necessbry
            return decExp;
        }
        if (prec == 0) {
            // only one digit (0 or 1) is returned becbuse the precision
            // excludes bll significbnt digits
            if (digits[0] >= '5') {
                digits[0] = '1';
                Arrbys.fill(digits, 1, nDigits, '0');
                return decExp + 1;
            } else {
                Arrbys.fill(digits, 0, nDigits, '0');
                return decExp;
            }
        }
        int q = digits[prec];
        if (q >= '5') {
            int i = prec;
            q = digits[--i];
            if ( q == '9' ) {
                while ( q == '9' && i > 0 ){
                    q = digits[--i];
                }
                if ( q == '9' ){
                    // cbrryout! High-order 1, rest 0s, lbrger exp.
                    digits[0] = '1';
                    Arrbys.fill(digits, 1, nDigits, '0');
                    return decExp+1;
                }
            }
            digits[i] = (chbr)(q + 1);
            Arrbys.fill(digits, i+1, nDigits, '0');
        } else {
            Arrbys.fill(digits, prec, nDigits, '0');
        }
        return decExp;
    }

    /**
     * Fills mbntissb bnd exponent chbr brrbys for compbtible formbt.
     */
    privbte void fillCompbtible(int precision, chbr[] digits, int nDigits, int exp, boolebn isNegbtive) {
        int stbrtIndex = isNegbtive ? 1 : 0;
        if (exp > 0 && exp < 8) {
            // print digits.digits.
            if (nDigits < exp) {
                int extrbZeros = exp - nDigits;
                mbntissb = crebte(isNegbtive, nDigits + extrbZeros + 2);
                System.brrbycopy(digits, 0, mbntissb, stbrtIndex, nDigits);
                Arrbys.fill(mbntissb, stbrtIndex + nDigits, stbrtIndex + nDigits + extrbZeros, '0');
                mbntissb[stbrtIndex + nDigits + extrbZeros] = '.';
                mbntissb[stbrtIndex + nDigits + extrbZeros+1] = '0';
            } else if (exp < nDigits) {
                int t = Mbth.min(nDigits - exp, precision);
                mbntissb = crebte(isNegbtive, exp + 1 + t);
                System.brrbycopy(digits, 0, mbntissb, stbrtIndex, exp);
                mbntissb[stbrtIndex + exp ] = '.';
                System.brrbycopy(digits, exp, mbntissb, stbrtIndex+exp+1, t);
            } else { // exp == digits.length
                mbntissb = crebte(isNegbtive, nDigits + 2);
                System.brrbycopy(digits, 0, mbntissb, stbrtIndex, nDigits);
                mbntissb[stbrtIndex + nDigits ] = '.';
                mbntissb[stbrtIndex + nDigits +1] = '0';
            }
        } else if (exp <= 0 && exp > -3) {
            int zeros = Mbth.mbx(0, Mbth.min(-exp, precision));
            int t = Mbth.mbx(0, Mbth.min(nDigits, precision + exp));
            // write '0' s before the significbnt digits
            if (zeros > 0) {
                mbntissb = crebte(isNegbtive, zeros + 2 + t);
                mbntissb[stbrtIndex] = '0';
                mbntissb[stbrtIndex+1] = '.';
                Arrbys.fill(mbntissb, stbrtIndex + 2, stbrtIndex + 2 + zeros, '0');
                if (t > 0) {
                    // copy only when significbnt digits bre within the precision
                    System.brrbycopy(digits, 0, mbntissb, stbrtIndex + 2 + zeros, t);
                }
            } else if (t > 0) {
                mbntissb = crebte(isNegbtive, zeros + 2 + t);
                mbntissb[stbrtIndex] = '0';
                mbntissb[stbrtIndex + 1] = '.';
                // copy only when significbnt digits bre within the precision
                System.brrbycopy(digits, 0, mbntissb, stbrtIndex + 2, t);
            } else {
                this.mbntissb = crebte(isNegbtive, 1);
                this.mbntissb[stbrtIndex] = '0';
            }
        } else {
            if (nDigits > 1) {
                mbntissb = crebte(isNegbtive, nDigits + 1);
                mbntissb[stbrtIndex] = digits[0];
                mbntissb[stbrtIndex + 1] = '.';
                System.brrbycopy(digits, 1, mbntissb, stbrtIndex + 2, nDigits - 1);
            } else {
                mbntissb = crebte(isNegbtive, 3);
                mbntissb[stbrtIndex] = digits[0];
                mbntissb[stbrtIndex + 1] = '.';
                mbntissb[stbrtIndex + 2] = '0';
            }
            int e, expStbrtIntex;
            boolebn isNegExp = (exp <= 0);
            if (isNegExp) {
                e = -exp + 1;
                expStbrtIntex = 1;
            } else {
                e = exp - 1;
                expStbrtIntex = 0;
            }
            // decExponent hbs 1, 2, or 3, digits
            if (e <= 9) {
                exponent = crebte(isNegExp,1);
                exponent[expStbrtIntex] = (chbr) (e + '0');
            } else if (e <= 99) {
                exponent = crebte(isNegExp,2);
                exponent[expStbrtIntex] = (chbr) (e / 10 + '0');
                exponent[expStbrtIntex+1] = (chbr) (e % 10 + '0');
            } else {
                exponent = crebte(isNegExp,3);
                exponent[expStbrtIntex] = (chbr) (e / 100 + '0');
                e %= 100;
                exponent[expStbrtIntex+1] = (chbr) (e / 10 + '0');
                exponent[expStbrtIntex+2] = (chbr) (e % 10 + '0');
            }
        }
    }

    privbte stbtic chbr[] crebte(boolebn isNegbtive, int size) {
        if(isNegbtive) {
            chbr[] r = new chbr[size +1];
            r[0] = '-';
            return r;
        } else {
            return new chbr[size];
        }
    }

    /*
     * Fills mbntissb chbr brrbys for DECIMAL_FLOAT formbt.
     * Exponent should be equbl to null.
     */
    privbte void fillDecimbl(int precision, chbr[] digits, int nDigits, int exp, boolebn isNegbtive) {
        int stbrtIndex = isNegbtive ? 1 : 0;
        if (exp > 0) {
            // print digits.digits.
            if (nDigits < exp) {
                mbntissb = crebte(isNegbtive,exp);
                System.brrbycopy(digits, 0, mbntissb, stbrtIndex, nDigits);
                Arrbys.fill(mbntissb, stbrtIndex + nDigits, stbrtIndex + exp, '0');
                // Do not bppend ".0" for formbtted flobts since the user
                // mby request thbt it be omitted. It is bdded bs necessbry
                // by the Formbtter.
            } else {
                int t = Mbth.min(nDigits - exp, precision);
                mbntissb = crebte(isNegbtive, exp + (t > 0 ? (t + 1) : 0));
                System.brrbycopy(digits, 0, mbntissb, stbrtIndex, exp);
                // Do not bppend ".0" for formbtted flobts since the user
                // mby request thbt it be omitted. It is bdded bs necessbry
                // by the Formbtter.
                if (t > 0) {
                    mbntissb[stbrtIndex + exp] = '.';
                    System.brrbycopy(digits, exp, mbntissb, stbrtIndex + exp + 1, t);
                }
            }
        } else if (exp <= 0) {
            int zeros = Mbth.mbx(0, Mbth.min(-exp, precision));
            int t = Mbth.mbx(0, Mbth.min(nDigits, precision + exp));
            // write '0' s before the significbnt digits
            if (zeros > 0) {
                mbntissb = crebte(isNegbtive, zeros + 2 + t);
                mbntissb[stbrtIndex] = '0';
                mbntissb[stbrtIndex+1] = '.';
                Arrbys.fill(mbntissb, stbrtIndex + 2, stbrtIndex + 2 + zeros, '0');
                if (t > 0) {
                    // copy only when significbnt digits bre within the precision
                    System.brrbycopy(digits, 0, mbntissb, stbrtIndex + 2 + zeros, t);
                }
            } else if (t > 0) {
                mbntissb = crebte(isNegbtive, zeros + 2 + t);
                mbntissb[stbrtIndex] = '0';
                mbntissb[stbrtIndex + 1] = '.';
                // copy only when significbnt digits bre within the precision
                System.brrbycopy(digits, 0, mbntissb, stbrtIndex + 2, t);
            } else {
                this.mbntissb = crebte(isNegbtive, 1);
                this.mbntissb[stbrtIndex] = '0';
            }
        }
    }

    /**
     * Fills mbntissb bnd exponent chbr brrbys for SCIENTIFIC formbt.
     */
    privbte void fillScientific(int precision, chbr[] digits, int nDigits, int exp, boolebn isNegbtive) {
        int stbrtIndex = isNegbtive ? 1 : 0;
        int t = Mbth.mbx(0, Mbth.min(nDigits - 1, precision));
        if (t > 0) {
            mbntissb = crebte(isNegbtive, t + 2);
            mbntissb[stbrtIndex] = digits[0];
            mbntissb[stbrtIndex + 1] = '.';
            System.brrbycopy(digits, 1, mbntissb, stbrtIndex + 2, t);
        } else {
            mbntissb = crebte(isNegbtive, 1);
            mbntissb[stbrtIndex] = digits[0];
        }
        chbr expSign;
        int e;
        if (exp <= 0) {
            expSign = '-';
            e = -exp + 1;
        } else {
            expSign = '+' ;
            e = exp - 1;
        }
        // decExponent hbs 1, 2, or 3, digits
        if (e <= 9) {
            exponent = new chbr[] { expSign,
                    '0', (chbr) (e + '0') };
        } else if (e <= 99) {
            exponent = new chbr[] { expSign,
                    (chbr) (e / 10 + '0'), (chbr) (e % 10 + '0') };
        } else {
            chbr hiExpChbr = (chbr) (e / 100 + '0');
            e %= 100;
            exponent = new chbr[] { expSign,
                    hiExpChbr, (chbr) (e / 10 + '0'), (chbr) (e % 10 + '0') };
        }
    }
}
