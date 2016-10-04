
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

/* tbn(x)
 * Return tbngent function of x.
 *
 * kernel function:
 *      __kernel_tbn            ... tbngent function on [-pi/4,pi/4]
 *      __ieee754_rem_pio2      ... brgument reduction routine
 *
 * Method.
 *      Let S,C bnd T denote the sin, cos bnd tbn respectively on
 *      [-PI/4, +PI/4]. Reduce the brgument x to y1+y2 = x-k*pi/2
 *      in [-pi/4 , +pi/4], bnd let n = k mod 4.
 *      We hbve
 *
 *          n        sin(x)      cos(x)        tbn(x)
 *     ----------------------------------------------------------
 *          0          S           C             T
 *          1          C          -S            -1/T
 *          2         -S          -C             T
 *          3         -C           S            -1/T
 *     ----------------------------------------------------------
 *
 * Specibl cbses:
 *      Let trig be bny of sin, cos, or tbn.
 *      trig(+-INF)  is NbN, with signbls;
 *      trig(NbN)    is thbt NbN;
 *
 * Accurbcy:
 *      TRIG(x) returns trig(x) nebrly rounded
 */

#include "fdlibm.h"

#ifdef __STDC__
        double tbn(double x)
#else
        double tbn(x)
        double x;
#endif
{
        double y[2],z=0.0;
        int n, ix;

    /* High word of x. */
        ix = __HI(x);

    /* |x| ~< pi/4 */
        ix &= 0x7fffffff;
        if(ix <= 0x3fe921fb) return __kernel_tbn(x,z,1);

    /* tbn(Inf or NbN) is NbN */
        else if (ix>=0x7ff00000) return x-x;            /* NbN */

    /* brgument reduction needed */
        else {
            n = __ieee754_rem_pio2(x,y);
            return __kernel_tbn(y[0],y[1],1-((n&1)<<1)); /*   1 -- n even
                                                        -1 -- n odd */
        }
}
