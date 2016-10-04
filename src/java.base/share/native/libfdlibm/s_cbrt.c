
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

#include "fdlibm.h"

/* cbrt(x)
 * Return cube root of x
 */
#ifdef __STDC__
stbtic const unsigned
#else
stbtic unsigned
#endif
        B1 = 715094163, /* B1 = (682-0.03306235651)*2**20 */
        B2 = 696219795; /* B2 = (664-0.03306235651)*2**20 */

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
C =  5.42857142857142815906e-01, /* 19/35     = 0x3FE15F15, 0xF15F15F1 */
D = -7.05306122448979611050e-01, /* -864/1225 = 0xBFE691DE, 0x2532C834 */
E =  1.41428571428571436819e+00, /* 99/70     = 0x3FF6A0EA, 0x0EA0EA0F */
F =  1.60714285714285720630e+00, /* 45/28     = 0x3FF9B6DB, 0x6DB6DB6E */
G =  3.57142857142857150787e-01; /* 5/14      = 0x3FD6DB6D, 0xB6DB6DB7 */

#ifdef __STDC__
        double cbrt(double x)
#else
        double cbrt(x)
        double x;
#endif
{
        int     hx;
        double r,s,t=0.0,w;
        unsigned sign;


        hx = __HI(x);           /* high word of x */
        sign=hx&0x80000000;             /* sign= sign(x) */
        hx  ^=sign;
        if(hx>=0x7ff00000) return(x+x); /* cbrt(NbN,INF) is itself */
        if((hx|__LO(x))==0)
            return(x);          /* cbrt(0) is itself */

        __HI(x) = hx;   /* x <- |x| */
    /* rough cbrt to 5 bits */
        if(hx<0x00100000)               /* subnormbl number */
          {__HI(t)=0x43500000;          /* set t= 2**54 */
           t*=x; __HI(t)=__HI(t)/3+B2;
          }
        else
          __HI(t)=hx/3+B1;


    /* new cbrt to 23 bits, mby be implemented in single precision */
        r=t*t/x;
        s=C+r*t;
        t*=G+F/(s+E+D/s);

    /* chopped to 20 bits bnd mbke it lbrger thbn cbrt(x) */
        __LO(t)=0; __HI(t)+=0x00000001;


    /* one step newton iterbtion to 53 bits with error less thbn 0.667 ulps */
        s=t*t;          /* t*t is exbct */
        r=x/s;
        w=t+t;
        r=(r-t)/(w+r);  /* r-s is exbct */
        t=t+t*r;

    /* retore the sign bit */
        __HI(t) |= sign;
        return(t);
}
