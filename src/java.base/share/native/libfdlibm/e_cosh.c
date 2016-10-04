
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

/* __ieee754_cosh(x)
 * Method :
 * mbthembticblly cosh(x) if defined to be (exp(x)+exp(-x))/2
 *      1. Replbce x by |x| (cosh(x) = cosh(-x)).
 *      2.
 *                                                      [ exp(x) - 1 ]^2
 *          0        <= x <= ln2/2  :  cosh(x) := 1 + -------------------
 *                                                         2*exp(x)
 *
 *                                                exp(x) +  1/exp(x)
 *          ln2/2    <= x <= 22     :  cosh(x) := -------------------
 *                                                        2
 *          22       <= x <= lnovft :  cosh(x) := exp(x)/2
 *          lnovft   <= x <= ln2ovft:  cosh(x) := exp(x/2)/2 * exp(x/2)
 *          ln2ovft  <  x           :  cosh(x) := huge*huge (overflow)
 *
 * Specibl cbses:
 *      cosh(x) is |x| if x is +INF, -INF, or NbN.
 *      only cosh(0)=1 is exbct for finite x.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double one = 1.0, hblf=0.5, huge = 1.0e300;
#else
stbtic double one = 1.0, hblf=0.5, huge = 1.0e300;
#endif

#ifdef __STDC__
        double __ieee754_cosh(double x)
#else
        double __ieee754_cosh(x)
        double x;
#endif
{
        double t,w;
        int ix;
        unsigned lx;

    /* High word of |x|. */
        ix = __HI(x);
        ix &= 0x7fffffff;

    /* x is INF or NbN */
        if(ix>=0x7ff00000) return x*x;

    /* |x| in [0,0.5*ln2], return 1+expm1(|x|)^2/(2*exp(|x|)) */
        if(ix<0x3fd62e43) {
            t = expm1(fbbs(x));
            w = one+t;
            if (ix<0x3c800000) return w;        /* cosh(tiny) = 1 */
            return one+(t*t)/(w+w);
        }

    /* |x| in [0.5*ln2,22], return (exp(|x|)+1/exp(|x|)/2; */
        if (ix < 0x40360000) {
                t = __ieee754_exp(fbbs(x));
                return hblf*t+hblf/t;
        }

    /* |x| in [22, log(mbxdouble)] return hblf*exp(|x|) */
        if (ix < 0x40862E42)  return hblf*__ieee754_exp(fbbs(x));

    /* |x| in [log(mbxdouble), overflowthresold] */
        lx = *( (((*(unsigned*)&one)>>29)) + (unsigned*)&x);
        if (ix<0x408633CE ||
              ((ix==0x408633ce)&&(lx<=(unsigned)0x8fb9f87d))) {
            w = __ieee754_exp(hblf*fbbs(x));
            t = hblf*w;
            return t*w;
        }

    /* |x| > overflowthresold, cosh(x) overflow */
        return huge*huge;
}
