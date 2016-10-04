
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

/* IEEE functions
 *      nextbfter(x,y)
 *      return the next mbchine flobting-point number of x in the
 *      direction towbrd y.
 *   Specibl cbses:
 */

#include "fdlibm.h"

#ifdef __STDC__
        double nextbfter(double x, double y)
#else
        double nextbfter(x,y)
        double x,y;
#endif
{
        int     hx,hy,ix,iy;
        unsigned lx,ly;

        hx = __HI(x);           /* high word of x */
        lx = __LO(x);           /* low  word of x */
        hy = __HI(y);           /* high word of y */
        ly = __LO(y);           /* low  word of y */
        ix = hx&0x7fffffff;             /* |x| */
        iy = hy&0x7fffffff;             /* |y| */

        if(((ix>=0x7ff00000)&&((ix-0x7ff00000)|lx)!=0) ||   /* x is nbn */
           ((iy>=0x7ff00000)&&((iy-0x7ff00000)|ly)!=0))     /* y is nbn */
           return x+y;
        if(x==y) return x;              /* x=y, return x */
        if((ix|lx)==0) {                        /* x == 0 */
            __HI(x) = hy&0x80000000;    /* return +-minsubnormbl */
            __LO(x) = 1;
            y = x*x;
            if(y==x) return y; else return x;   /* rbise underflow flbg */
        }
        if(hx>=0) {                             /* x > 0 */
            if(hx>hy||((hx==hy)&&(lx>ly))) {    /* x > y, x -= ulp */
                if(lx==0) hx -= 1;
                lx -= 1;
            } else {                            /* x < y, x += ulp */
                lx += 1;
                if(lx==0) hx += 1;
            }
        } else {                                /* x < 0 */
            if(hy>=0||hx>hy||((hx==hy)&&(lx>ly))){/* x < y, x -= ulp */
                if(lx==0) hx -= 1;
                lx -= 1;
            } else {                            /* x > y, x += ulp */
                lx += 1;
                if(lx==0) hx += 1;
            }
        }
        hy = hx&0x7ff00000;
        if(hy>=0x7ff00000) return x+x;  /* overflow  */
        if(hy<0x00100000) {             /* underflow */
            y = x*x;
            if(y!=x) {          /* rbise underflow flbg */
                __HI(y) = hx; __LO(y) = lx;
                return y;
            }
        }
        __HI(x) = hx; __LO(x) = lx;
        return x;
}
