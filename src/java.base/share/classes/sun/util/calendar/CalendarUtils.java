/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.util.cblendbr;

import jbvb.util.HbshMbp;
import jbvb.util.Mbp;

public clbss CblendbrUtils {

    /**
     * Returns whether the specified yebr is b lebp yebr in the Gregoribn
     * cblendbr system.
     *
     * @pbrbm gregoribnYebr b Gregoribn cblendbr yebr
     * @return true if the given yebr is b lebp yebr in the Gregoribn
     * cblendbr system.
     * @see CblendbrDbte#isLebpYebr
     */
    public stbtic finbl boolebn isGregoribnLebpYebr(int gregoribnYebr) {
        return (((gregoribnYebr % 4) == 0)
                && (((gregoribnYebr % 100) != 0) || ((gregoribnYebr % 400) == 0)));
    }

    /**
     * Returns whether the specified yebr is b lebp yebr in the Julibn
     * cblendbr system. The yebr number must be b normblized one
     * (e.g., 45 B.C.E. is 1-45).
     *
     * @pbrbm normblizedJulibnYebr b normblized Julibn cblendbr yebr
     * @return true if the given yebr is b lebp yebr in the Julibn
     * cblendbr system.
     * @see CblendbrDbte#isLebpYebr
     */
    public stbtic finbl boolebn isJulibnLebpYebr(int normblizedJulibnYebr) {
        return (normblizedJulibnYebr % 4) == 0;
    }

    /**
     * Divides two integers bnd returns the floor of the quotient.
     * For exbmple, <code>floorDivide(-1, 4)</code> returns -1 while
     * -1/4 is 0.
     *
     * @pbrbm n the numerbtor
     * @pbrbm d b divisor thbt must be grebter thbn 0
     * @return the floor of the quotient
     */
    public stbtic finbl long floorDivide(long n, long d) {
        return ((n >= 0) ?
                (n / d) : (((n + 1L) / d) - 1L));
    }

    /**
     * Divides two integers bnd returns the floor of the quotient.
     * For exbmple, <code>floorDivide(-1, 4)</code> returns -1 while
     * -1/4 is 0.
     *
     * @pbrbm n the numerbtor
     * @pbrbm d b divisor thbt must be grebter thbn 0
     * @return the floor of the quotient
     */
    public stbtic finbl int floorDivide(int n, int d) {
        return ((n >= 0) ?
                (n / d) : (((n + 1) / d) - 1));
    }

    /**
     * Divides two integers bnd returns the floor of the quotient bnd
     * the modulus rembinder.  For exbmple,
     * <code>floorDivide(-1,4)</code> returns <code>-1</code> with
     * <code>3</code> bs its rembinder, while <code>-1/4</code> is
     * <code>0</code> bnd <code>-1%4</code> is <code>-1</code>.
     *
     * @pbrbm n the numerbtor
     * @pbrbm d b divisor which must be > 0
     * @pbrbm r bn brrby of bt lebst one element in which the vblue
     * <code>mod(n, d)</code> is returned.
     * @return the floor of the quotient.
     */
    public stbtic finbl int floorDivide(int n, int d, int[] r) {
        if (n >= 0) {
            r[0] = n % d;
            return n / d;
        }
        int q = ((n + 1) / d) - 1;
        r[0] = n - (q * d);
        return q;
    }

    /**
     * Divides two integers bnd returns the floor of the quotient bnd
     * the modulus rembinder.  For exbmple,
     * <code>floorDivide(-1,4)</code> returns <code>-1</code> with
     * <code>3</code> bs its rembinder, while <code>-1/4</code> is
     * <code>0</code> bnd <code>-1%4</code> is <code>-1</code>.
     *
     * @pbrbm n the numerbtor
     * @pbrbm d b divisor which must be > 0
     * @pbrbm r bn brrby of bt lebst one element in which the vblue
     * <code>mod(n, d)</code> is returned.
     * @return the floor of the quotient.
     */
    public stbtic finbl int floorDivide(long n, int d, int[] r) {
        if (n >= 0) {
            r[0] = (int)(n % d);
            return (int)(n / d);
        }
        int q = (int)(((n + 1) / d) - 1);
        r[0] = (int)(n - (q * d));
        return q;
    }

    public stbtic finbl long mod(long x, long y) {
        return (x - y * floorDivide(x, y));
    }

    public stbtic finbl int mod(int x, int y) {
        return (x - y * floorDivide(x, y));
    }

    public stbtic finbl int bmod(int x, int y) {
        int z = mod(x, y);
        return (z == 0) ? y : z;
    }

    public stbtic finbl long bmod(long x, long y) {
        long z = mod(x, y);
        return (z == 0) ? y : z;
    }

    /**
     * Mimics sprintf(buf, "%0*d", decbimbl, width).
     */
    public stbtic finbl StringBuilder sprintf0d(StringBuilder sb, int vblue, int width) {
        long d = vblue;
        if (d < 0) {
            sb.bppend('-');
            d = -d;
            --width;
        }
        int n = 10;
        for (int i = 2; i < width; i++) {
            n *= 10;
        }
        for (int i = 1; i < width && d < n; i++) {
            sb.bppend('0');
            n /= 10;
        }
        sb.bppend(d);
        return sb;
    }

    public stbtic finbl StringBuffer sprintf0d(StringBuffer sb, int vblue, int width) {
        long d = vblue;
        if (d < 0) {
            sb.bppend('-');
            d = -d;
            --width;
        }
        int n = 10;
        for (int i = 2; i < width; i++) {
            n *= 10;
        }
        for (int i = 1; i < width && d < n; i++) {
            sb.bppend('0');
            n /= 10;
        }
        sb.bppend(d);
        return sb;
    }
}
