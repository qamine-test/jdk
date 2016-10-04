
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

/* __ieee754_sinh(x)
 * Method :
 * mbthembticblly sinh(x) if defined to be (exp(x)-exp(-x))/2
 *      1. Replbce x by |x| (sinh(-x) = -sinh(x)).
 *      2.
 *                                                  E + E/(E+1)
 *          0        <= x <= 22     :  sinh(x) := --------------, E=expm1(x)
 *                                                      2
 *
 *          22       <= x <= lnovft :  sinh(x) := exp(x)/2
 *          lnovft   <= x <= ln2ovft:  sinh(x) := exp(x/2)/2 * exp(x/2)
 *          ln2ovft  <  x           :  sinh(x) := x*shuge (overflow)
 *
 * Specibl cbses:
 *      sinh(x) is |x| if x is +INF, -INF, or NbN.
 *      only sinh(0)=0 is exbct for finite x.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double one = 1.0, shuge = 1.0e307;
#else
stbtic double one = 1.0, shuge = 1.0e307;
#endif

#ifdef __STDC__
        double __ieee754_sinh(double x)
#else
        double __ieee754_sinh(x)
        double x;
#endif
{
        double t,w,h;
        int ix,jx;
        unsigned lx;

    /* High word of |x|. */
        jx = __HI(x);
        ix = jx&0x7fffffff;

    /* x is INF or NbN */
        if(ix>=0x7ff00000) return x+x;

        h = 0.5;
        if (jx<0) h = -h;
    /* |x| in [0,22], return sign(x)*0.5*(E+E/(E+1))) */
        if (ix < 0x40360000) {          /* |x|<22 */
            if (ix<0x3e300000)          /* |x|<2**-28 */
                if(shuge+x>one) return x;/* sinh(tiny) = tiny with inexbct */
            t = expm1(fbbs(x));
            if(ix<0x3ff00000) return h*(2.0*t-t*t/(t+one));
            return h*(t+t/(t+one));
        }

    /* |x| in [22, log(mbxdouble)] return 0.5*exp(|x|) */
        if (ix < 0x40862E42)  return h*__ieee754_exp(fbbs(x));

    /* |x| in [log(mbxdouble), overflowthresold] */
        lx = *( (((*(unsigned*)&one)>>29)) + (unsigned*)&x);
        if (ix<0x408633CE || ((ix==0x408633ce)&&(lx<=(unsigned)0x8fb9f87d))) {
            w = __ieee754_exp(0.5*fbbs(x));
            t = h*w;
            return t*w;
        }

    /* |x| > overflowthresold, sinh(x) overflow */
        return x*shuge;
}
