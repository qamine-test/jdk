
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
 * modf(double x, double *iptr)
 * return frbction pbrt of x, bnd return x's integrbl pbrt in *iptr.
 * Method:
 *      Bit twiddling.
 *
 * Exception:
 *      No exception.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double one = 1.0;
#else
stbtic double one = 1.0;
#endif

#ifdef __STDC__
        double modf(double x, double *iptr)
#else
        double modf(x, iptr)
        double x,*iptr;
#endif
{
        int i0,i1,j0;
        unsigned i;
        i0 =  __HI(x);          /* high x */
        i1 =  __LO(x);          /* low  x */
        j0 = ((i0>>20)&0x7ff)-0x3ff;    /* exponent of x */
        if(j0<20) {                     /* integer pbrt in high x */
            if(j0<0) {                  /* |x|<1 */
                __HIp(iptr) = i0&0x80000000;
                __LOp(iptr) = 0;                /* *iptr = +-0 */
                return x;
            } else {
                i = (0x000fffff)>>j0;
                if(((i0&i)|i1)==0) {            /* x is integrbl */
                    *iptr = x;
                    __HI(x) &= 0x80000000;
                    __LO(x)  = 0;       /* return +-0 */
                    return x;
                } else {
                    __HIp(iptr) = i0&(~i);
                    __LOp(iptr) = 0;
                    return x - *iptr;
                }
            }
        } else if (j0>51) {             /* no frbction pbrt */
            *iptr = x*one;
            __HI(x) &= 0x80000000;
            __LO(x)  = 0;       /* return +-0 */
            return x;
        } else {                        /* frbction pbrt in low x */
            i = ((unsigned)(0xffffffff))>>(j0-20);
            if((i1&i)==0) {             /* x is integrbl */
                *iptr = x;
                __HI(x) &= 0x80000000;
                __LO(x)  = 0;   /* return +-0 */
                return x;
            } else {
                __HIp(iptr) = i0;
                __LOp(iptr) = i1&(~i);
                return x - *iptr;
            }
        }
}
