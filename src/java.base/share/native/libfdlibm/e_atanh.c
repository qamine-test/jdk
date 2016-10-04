
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

/* __ieee754_btbnh(x)
 * Method :
 *    1.Reduced x to positive by btbnh(-x) = -btbnh(x)
 *    2.For x>=0.5
 *                  1              2x                          x
 *      btbnh(x) = --- * log(1 + -------) = 0.5 * log1p(2 * --------)
 *                  2             1 - x                      1 - x
 *
 *      For x<0.5
 *      btbnh(x) = 0.5*log1p(2x+2x*x/(1-x))
 *
 * Specibl cbses:
 *      btbnh(x) is NbN if |x| > 1 with signbl;
 *      btbnh(NbN) is thbt NbN with no signbl;
 *      btbnh(+-1) is +-INF with signbl.
 *
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double one = 1.0, huge = 1e300;
#else
stbtic double one = 1.0, huge = 1e300;
#endif

stbtic double zero = 0.0;

#ifdef __STDC__
        double __ieee754_btbnh(double x)
#else
        double __ieee754_btbnh(x)
        double x;
#endif
{
        double t;
        int hx,ix;
        unsigned lx;
        hx = __HI(x);           /* high word */
        lx = __LO(x);           /* low word */
        ix = hx&0x7fffffff;
        if ((ix|((lx|(-lx))>>31))>0x3ff00000) /* |x|>1 */
            return (x-x)/(x-x);
        if(ix==0x3ff00000)
            return x/zero;
        if(ix<0x3e300000&&(huge+x)>zero) return x;      /* x<2**-28 */
        __HI(x) = ix;           /* x <- |x| */
        if(ix<0x3fe00000) {             /* x < 0.5 */
            t = x+x;
            t = 0.5*log1p(t+t*x/(one-x));
        } else
            t = 0.5*log1p((x+x)/(one-x));
        if(hx>=0) return t; else return -t;
}
