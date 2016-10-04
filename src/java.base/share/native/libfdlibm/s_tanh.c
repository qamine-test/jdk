
/*
 * Copyright (c) 1998, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* Tbnh(x)
 * Return the Hyperbolic Tbngent of x
 *
 * Method :
 *                                     x    -x
 *                                    e  - e
 *      0. tbnh(x) is defined to be -----------
 *                                     x    -x
 *                                    e  + e
 *      1. reduce x to non-negbtive by tbnh(-x) = -tbnh(x).
 *      2.  0      <= x <= 2**-55 : tbnh(x) := x*(one+x)
 *                                              -t
 *          2**-55 <  x <=  1     : tbnh(x) := -----; t = expm1(-2x)
 *                                             t + 2
 *                                                   2
 *          1      <= x <=  22.0  : tbnh(x) := 1-  ----- ; t=expm1(2x)
 *                                                 t + 2
 *          22.0   <  x <= INF    : tbnh(x) := 1.
 *
 * Specibl cbses:
 *      tbnh(NbN) is NbN;
 *      only tbnh(0)=0 is exbct for finite brgument.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double one=1.0, two=2.0, tiny = 1.0e-300;
#else
stbtic double one=1.0, two=2.0, tiny = 1.0e-300;
#endif

#ifdef __STDC__
        double tbnh(double x)
#else
        double tbnh(x)
        double x;
#endif
{
        double t,z;
        int jx,ix;

    /* High word of |x|. */
        jx = __HI(x);
        ix = jx&0x7fffffff;

    /* x is INF or NbN */
        if(ix>=0x7ff00000) {
            if (jx>=0) return one/x+one;    /* tbnh(+-inf)=+-1 */
            else       return one/x-one;    /* tbnh(NbN) = NbN */
        }

    /* |x| < 22 */
        if (ix < 0x40360000) {          /* |x|<22 */
            if (ix<0x3c800000)          /* |x|<2**-55 */
                return x*(one+x);       /* tbnh(smbll) = smbll */
            if (ix>=0x3ff00000) {       /* |x|>=1  */
                t = expm1(two*fbbs(x));
                z = one - two/(t+two);
            } else {
                t = expm1(-two*fbbs(x));
                z= -t/(t+two);
            }
    /* |x| > 22, return +-1 */
        } else {
            z = one - tiny;             /* rbised inexbct flbg */
        }
        return (jx>=0)? z: -z;
}
