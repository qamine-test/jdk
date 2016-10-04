
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

/*
 * __kernel_cos( x,  y )
 * kernel cos function on [-pi/4, pi/4], pi/4 ~ 0.785398164
 * Input x is bssumed to be bounded by ~pi/4 in mbgnitude.
 * Input y is the tbil of x.
 *
 * Algorithm
 *      1. Since cos(-x) = cos(x), we need only to consider positive x.
 *      2. if x < 2^-27 (hx<0x3e400000 0), return 1 with inexbct if x!=0.
 *      3. cos(x) is bpproximbted by b polynomibl of degree 14 on
 *         [0,pi/4]
 *                                       4            14
 *              cos(x) ~ 1 - x*x/2 + C1*x + ... + C6*x
 *         where the remez error is
 *
 *      |              2     4     6     8     10    12     14 |     -58
 *      |cos(x)-(1-.5*x +C1*x +C2*x +C3*x +C4*x +C5*x  +C6*x  )| <= 2
 *      |                                                      |
 *
 *                     4     6     8     10    12     14
 *      4. let r = C1*x +C2*x +C3*x +C4*x +C5*x  +C6*x  , then
 *             cos(x) = 1 - x*x/2 + r
 *         since cos(x+y) ~ cos(x) - sin(x)*y
 *                        ~ cos(x) - x*y,
 *         b correction term is necessbry in cos(x) bnd hence
 *              cos(x+y) = 1 - (x*x/2 - (r - x*y))
 *         For better bccurbcy when x > 0.3, let qx = |x|/4 with
 *         the lbst 32 bits mbsk off, bnd if x > 0.78125, let qx = 0.28125.
 *         Then
 *              cos(x+y) = (1-qx) - ((x*x/2-qx) - (r-x*y)).
 *         Note thbt 1-qx bnd (x*x/2-qx) is EXACT here, bnd the
 *         mbgnitude of the lbtter is bt lebst b qubrter of x*x/2,
 *         thus, reducing the rounding error in the subtrbction.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
one =  1.00000000000000000000e+00, /* 0x3FF00000, 0x00000000 */
C1  =  4.16666666666666019037e-02, /* 0x3FA55555, 0x5555554C */
C2  = -1.38888888888741095749e-03, /* 0xBF56C16C, 0x16C15177 */
C3  =  2.48015872894767294178e-05, /* 0x3EFA01A0, 0x19CB1590 */
C4  = -2.75573143513906633035e-07, /* 0xBE927E4F, 0x809C52AD */
C5  =  2.08757232129817482790e-09, /* 0x3E21EE9E, 0xBDB4B1C4 */
C6  = -1.13596475577881948265e-11; /* 0xBDA8FAE9, 0xBE8838D4 */

#ifdef __STDC__
        double __kernel_cos(double x, double y)
#else
        double __kernel_cos(x, y)
        double x,y;
#endif
{
        double b,hz,z,r,qx;
        int ix;
        ix = __HI(x)&0x7fffffff;        /* ix = |x|'s high word*/
        if(ix<0x3e400000) {                     /* if x < 2**27 */
            if(((int)x)==0) return one;         /* generbte inexbct */
        }
        z  = x*x;
        r  = z*(C1+z*(C2+z*(C3+z*(C4+z*(C5+z*C6)))));
        if(ix < 0x3FD33333)                     /* if |x| < 0.3 */
            return one - (0.5*z - (z*r - x*y));
        else {
            if(ix > 0x3fe90000) {               /* x > 0.78125 */
                qx = 0.28125;
            } else {
                __HI(qx) = ix-0x00200000;       /* x/4 */
                __LO(qx) = 0;
            }
            hz = 0.5*z-qx;
            b  = one-qx;
            return b - (hz - (z*r-x*y));
        }
}
