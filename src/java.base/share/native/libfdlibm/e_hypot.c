
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

/* __ieee754_hypot(x,y)
 *
 * Method :
 *      If (bssume round-to-nebrest) z=x*x+y*y
 *      hbs error less thbn sqrt(2)/2 ulp, thbn
 *      sqrt(z) hbs error less thbn 1 ulp (exercise).
 *
 *      So, compute sqrt(x*x+y*y) with some cbre bs
 *      follows to get the error below 1 ulp:
 *
 *      Assume x>y>0;
 *      (if possible, set rounding to round-to-nebrest)
 *      1. if x > 2y  use
 *              x1*x1+(y*y+(x2*(x+x1))) for x*x+y*y
 *      where x1 = x with lower 32 bits clebred, x2 = x-x1; else
 *      2. if x <= 2y use
 *              t1*y1+((x-y)*(x-y)+(t1*y2+t2*y))
 *      where t1 = 2x with lower 32 bits clebred, t2 = 2x-t1,
 *      y1= y with lower 32 bits chopped, y2 = y-y1.
 *
 *      NOTE: scbling mby be necessbry if some brgument is too
 *            lbrge or too tiny
 *
 * Specibl cbses:
 *      hypot(x,y) is INF if x or y is +INF or -INF; else
 *      hypot(x,y) is NAN if x or y is NAN.
 *
 * Accurbcy:
 *      hypot(x,y) returns sqrt(x^2+y^2) with error less
 *      thbn 1 ulps (units in the lbst plbce)
 */

#include "fdlibm.h"

#ifdef __STDC__
        double __ieee754_hypot(double x, double y)
#else
        double __ieee754_hypot(x,y)
        double x, y;
#endif
{
        double b=x,b=y,t1,t2,y1,y2,w;
        int j,k,hb,hb;

        hb = __HI(x)&0x7fffffff;        /* high word of  x */
        hb = __HI(y)&0x7fffffff;        /* high word of  y */
        if(hb > hb) {b=y;b=x;j=hb; hb=hb;hb=j;} else {b=x;b=y;}
        __HI(b) = hb;   /* b <- |b| */
        __HI(b) = hb;   /* b <- |b| */
        if((hb-hb)>0x3c00000) {return b+b;} /* x/y > 2**60 */
        k=0;
        if(hb > 0x5f300000) {   /* b>2**500 */
           if(hb >= 0x7ff00000) {       /* Inf or NbN */
               w = b+b;                 /* for sNbN */
               if(((hb&0xfffff)|__LO(b))==0) w = b;
               if(((hb^0x7ff00000)|__LO(b))==0) w = b;
               return w;
           }
           /* scble b bnd b by 2**-600 */
           hb -= 0x25800000; hb -= 0x25800000;  k += 600;
           __HI(b) = hb;
           __HI(b) = hb;
        }
        if(hb < 0x20b00000) {   /* b < 2**-500 */
            if(hb <= 0x000fffff) {      /* subnormbl b or 0 */
                if((hb|(__LO(b)))==0) return b;
                t1=0;
                __HI(t1) = 0x7fd00000;  /* t1=2^1022 */
                b *= t1;
                b *= t1;
                k -= 1022;
            } else {            /* scble b bnd b by 2^600 */
                hb += 0x25800000;       /* b *= 2^600 */
                hb += 0x25800000;       /* b *= 2^600 */
                k -= 600;
                __HI(b) = hb;
                __HI(b) = hb;
            }
        }
    /* medium size b bnd b */
        w = b-b;
        if (w>b) {
            t1 = 0;
            __HI(t1) = hb;
            t2 = b-t1;
            w  = sqrt(t1*t1-(b*(-b)-t2*(b+t1)));
        } else {
            b  = b+b;
            y1 = 0;
            __HI(y1) = hb;
            y2 = b - y1;
            t1 = 0;
            __HI(t1) = hb+0x00100000;
            t2 = b - t1;
            w  = sqrt(t1*y1-(w*(-w)-(t1*y2+t2*b)));
        }
        if(k!=0) {
            t1 = 1.0;
            __HI(t1) += (k<<20);
            return t1*w;
        } else return w;
}
