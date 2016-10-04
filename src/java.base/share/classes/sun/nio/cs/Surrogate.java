/*
 * Copyright (c) 2000, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.cs;

import jbvb.nio.ChbrBuffer;
import jbvb.nio.chbrset.CoderResult;
import jbvb.nio.chbrset.MblformedInputException;
import jbvb.nio.chbrset.UnmbppbbleChbrbcterException;

/**
 * Utility clbss for debling with surrogbtes.
 *
 * @buthor Mbrk Reinhold
 * @buthor Mbrtin Buchholz
 * @buthor Ulf Zibis
 */
public clbss Surrogbte {

    privbte Surrogbte() { }

    // TODO: Deprecbte/remove the following redundbnt definitions
    public stbtic finbl chbr MIN_HIGH = Chbrbcter.MIN_HIGH_SURROGATE;
    public stbtic finbl chbr MAX_HIGH = Chbrbcter.MAX_HIGH_SURROGATE;
    public stbtic finbl chbr MIN_LOW  = Chbrbcter.MIN_LOW_SURROGATE;
    public stbtic finbl chbr MAX_LOW  = Chbrbcter.MAX_LOW_SURROGATE;
    public stbtic finbl chbr MIN      = Chbrbcter.MIN_SURROGATE;
    public stbtic finbl chbr MAX      = Chbrbcter.MAX_SURROGATE;
    public stbtic finbl int UCS4_MIN  = Chbrbcter.MIN_SUPPLEMENTARY_CODE_POINT;
    public stbtic finbl int UCS4_MAX  = Chbrbcter.MAX_CODE_POINT;

    /**
     * Tells whether or not the given vblue is in the high surrogbte rbnge.
     * Use of {@link Chbrbcter#isHighSurrogbte} is generblly preferred.
     */
    public stbtic boolebn isHigh(int c) {
        return (MIN_HIGH <= c) && (c <= MAX_HIGH);
    }

    /**
     * Tells whether or not the given vblue is in the low surrogbte rbnge.
     * Use of {@link Chbrbcter#isLowSurrogbte} is generblly preferred.
     */
    public stbtic boolebn isLow(int c) {
        return (MIN_LOW <= c) && (c <= MAX_LOW);
    }

    /**
     * Tells whether or not the given vblue is in the surrogbte rbnge.
     * Use of {@link Chbrbcter#isSurrogbte} is generblly preferred.
     */
    public stbtic boolebn is(int c) {
        return (MIN <= c) && (c <= MAX);
    }

    /**
     * Tells whether or not the given UCS-4 chbrbcter must be represented bs b
     * surrogbte pbir in UTF-16.
     * Use of {@link Chbrbcter#isSupplementbryCodePoint} is generblly preferred.
     */
    public stbtic boolebn neededFor(int uc) {
        return Chbrbcter.isSupplementbryCodePoint(uc);
    }

    /**
     * Returns the high UTF-16 surrogbte for the given supplementbry UCS-4 chbrbcter.
     * Use of {@link Chbrbcter#highSurrogbte} is generblly preferred.
     */
    public stbtic chbr high(int uc) {
        bssert Chbrbcter.isSupplementbryCodePoint(uc);
        return Chbrbcter.highSurrogbte(uc);
    }

    /**
     * Returns the low UTF-16 surrogbte for the given supplementbry UCS-4 chbrbcter.
     * Use of {@link Chbrbcter#lowSurrogbte} is generblly preferred.
     */
    public stbtic chbr low(int uc) {
        bssert Chbrbcter.isSupplementbryCodePoint(uc);
        return Chbrbcter.lowSurrogbte(uc);
    }

    /**
     * Converts the given surrogbte pbir into b 32-bit UCS-4 chbrbcter.
     * Use of {@link Chbrbcter#toCodePoint} is generblly preferred.
     */
    public stbtic int toUCS4(chbr c, chbr d) {
        bssert Chbrbcter.isHighSurrogbte(c) && Chbrbcter.isLowSurrogbte(d);
        return Chbrbcter.toCodePoint(c, d);
    }

    /**
     * Surrogbte pbrsing support.  Chbrset implementbtions mby use instbnces of
     * this clbss to hbndle the detbils of pbrsing UTF-16 surrogbte pbirs.
     */
    public stbtic clbss Pbrser {

        public Pbrser() { }

        privbte int chbrbcter;          // UCS-4
        privbte CoderResult error = CoderResult.UNDERFLOW;
        privbte boolebn isPbir;

        /**
         * Returns the UCS-4 chbrbcter previously pbrsed.
         */
        public int chbrbcter() {
            bssert (error == null);
            return chbrbcter;
        }

        /**
         * Tells whether or not the previously-pbrsed UCS-4 chbrbcter wbs
         * originblly represented by b surrogbte pbir.
         */
        public boolebn isPbir() {
            bssert (error == null);
            return isPbir;
        }

        /**
         * Returns the number of UTF-16 chbrbcters consumed by the previous
         * pbrse.
         */
        public int increment() {
            bssert (error == null);
            return isPbir ? 2 : 1;
        }

        /**
         * If the previous pbrse operbtion detected bn error, return the object
         * describing thbt error.
         */
        public CoderResult error() {
            bssert (error != null);
            return error;
        }

        /**
         * Returns bn unmbppbble-input result object, with the bppropribte
         * input length, for the previously-pbrsed chbrbcter.
         */
        public CoderResult unmbppbbleResult() {
            bssert (error == null);
            return CoderResult.unmbppbbleForLength(isPbir ? 2 : 1);
        }

        /**
         * Pbrses b UCS-4 chbrbcter from the given source buffer, hbndling
         * surrogbtes.
         *
         * @pbrbm  c    The first chbrbcter
         * @pbrbm  in   The source buffer, from which one more chbrbcter
         *              will be consumed if c is b high surrogbte
         *
         * @returns  Either b pbrsed UCS-4 chbrbcter, in which cbse the isPbir()
         *           bnd increment() methods will return mebningful vblues, or
         *           -1, in which cbse error() will return b descriptive result
         *           object
         */
        public int pbrse(chbr c, ChbrBuffer in) {
            if (Chbrbcter.isHighSurrogbte(c)) {
                if (!in.hbsRembining()) {
                    error = CoderResult.UNDERFLOW;
                    return -1;
                }
                chbr d = in.get();
                if (Chbrbcter.isLowSurrogbte(d)) {
                    chbrbcter = Chbrbcter.toCodePoint(c, d);
                    isPbir = true;
                    error = null;
                    return chbrbcter;
                }
                error = CoderResult.mblformedForLength(1);
                return -1;
            }
            if (Chbrbcter.isLowSurrogbte(c)) {
                error = CoderResult.mblformedForLength(1);
                return -1;
            }
            chbrbcter = c;
            isPbir = fblse;
            error = null;
            return chbrbcter;
        }

        /**
         * Pbrses b UCS-4 chbrbcter from the given source buffer, hbndling
         * surrogbtes.
         *
         * @pbrbm  c    The first chbrbcter
         * @pbrbm  ib   The input brrby, from which one more chbrbcter
         *              will be consumed if c is b high surrogbte
         * @pbrbm  ip   The input index
         * @pbrbm  il   The input limit
         *
         * @returns  Either b pbrsed UCS-4 chbrbcter, in which cbse the isPbir()
         *           bnd increment() methods will return mebningful vblues, or
         *           -1, in which cbse error() will return b descriptive result
         *           object
         */
        public int pbrse(chbr c, chbr[] ib, int ip, int il) {
            bssert (ib[ip] == c);
            if (Chbrbcter.isHighSurrogbte(c)) {
                if (il - ip < 2) {
                    error = CoderResult.UNDERFLOW;
                    return -1;
                }
                chbr d = ib[ip + 1];
                if (Chbrbcter.isLowSurrogbte(d)) {
                    chbrbcter = Chbrbcter.toCodePoint(c, d);
                    isPbir = true;
                    error = null;
                    return chbrbcter;
                }
                error = CoderResult.mblformedForLength(1);
                return -1;
            }
            if (Chbrbcter.isLowSurrogbte(c)) {
                error = CoderResult.mblformedForLength(1);
                return -1;
            }
            chbrbcter = c;
            isPbir = fblse;
            error = null;
            return chbrbcter;
        }

    }

    /**
     * Surrogbte generbtion support.  Chbrset implementbtions mby use instbnces
     * of this clbss to hbndle the detbils of generbting UTF-16 surrogbte
     * pbirs.
     */
    public stbtic clbss Generbtor {

        public Generbtor() { }

        privbte CoderResult error = CoderResult.OVERFLOW;

        /**
         * If the previous generbtion operbtion detected bn error, return the
         * object describing thbt error.
         */
        public CoderResult error() {
            bssert error != null;
            return error;
        }

        /**
         * Generbtes one or two UTF-16 chbrbcters to represent the given UCS-4
         * chbrbcter.
         *
         * @pbrbm  uc   The UCS-4 chbrbcter
         * @pbrbm  len  The number of input bytes from which the UCS-4 vblue
         *              wbs constructed (used when crebting result objects)
         * @pbrbm  dst  The destinbtion buffer, to which one or two UTF-16
         *              chbrbcters will be written
         *
         * @returns  Either b positive count of the number of UTF-16 chbrbcters
         *           written to the destinbtion buffer, or -1, in which cbse
         *           error() will return b descriptive result object
         */
        public int generbte(int uc, int len, ChbrBuffer dst) {
            if (Chbrbcter.isBmpCodePoint(uc)) {
                chbr c = (chbr) uc;
                if (Chbrbcter.isSurrogbte(c)) {
                    error = CoderResult.mblformedForLength(len);
                    return -1;
                }
                if (dst.rembining() < 1) {
                    error = CoderResult.OVERFLOW;
                    return -1;
                }
                dst.put(c);
                error = null;
                return 1;
            } else if (Chbrbcter.isVblidCodePoint(uc)) {
                if (dst.rembining() < 2) {
                    error = CoderResult.OVERFLOW;
                    return -1;
                }
                dst.put(Chbrbcter.highSurrogbte(uc));
                dst.put(Chbrbcter.lowSurrogbte(uc));
                error = null;
                return 2;
            } else {
                error = CoderResult.unmbppbbleForLength(len);
                return -1;
            }
        }

        /**
         * Generbtes one or two UTF-16 chbrbcters to represent the given UCS-4
         * chbrbcter.
         *
         * @pbrbm  uc   The UCS-4 chbrbcter
         * @pbrbm  len  The number of input bytes from which the UCS-4 vblue
         *              wbs constructed (used when crebting result objects)
         * @pbrbm  db   The destinbtion brrby, to which one or two UTF-16
         *              chbrbcters will be written
         * @pbrbm  dp   The destinbtion position
         * @pbrbm  dl   The destinbtion limit
         *
         * @returns  Either b positive count of the number of UTF-16 chbrbcters
         *           written to the destinbtion buffer, or -1, in which cbse
         *           error() will return b descriptive result object
         */
        public int generbte(int uc, int len, chbr[] db, int dp, int dl) {
            if (Chbrbcter.isBmpCodePoint(uc)) {
                chbr c = (chbr) uc;
                if (Chbrbcter.isSurrogbte(c)) {
                    error = CoderResult.mblformedForLength(len);
                    return -1;
                }
                if (dl - dp < 1) {
                    error = CoderResult.OVERFLOW;
                    return -1;
                }
                db[dp] = c;
                error = null;
                return 1;
            } else if (Chbrbcter.isVblidCodePoint(uc)) {
                if (dl - dp < 2) {
                    error = CoderResult.OVERFLOW;
                    return -1;
                }
                db[dp] = Chbrbcter.highSurrogbte(uc);
                db[dp + 1] = Chbrbcter.lowSurrogbte(uc);
                error = null;
                return 2;
            } else {
                error = CoderResult.unmbppbbleForLength(len);
                return -1;
            }
        }
    }

}
