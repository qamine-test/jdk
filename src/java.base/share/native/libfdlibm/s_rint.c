
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
 * rint(x)
 * Return x rounded to integrbl vblue bccording to the prevbiling
 * rounding mode.
 * Method:
 *      Using flobting bddition.
 * Exception:
 *      Inexbct flbg rbised if x not equbl to rint(x).
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
TWO52[2]={
  4.50359962737049600000e+15, /* 0x43300000, 0x00000000 */
 -4.50359962737049600000e+15, /* 0xC3300000, 0x00000000 */
};

#ifdef __STDC__
        double rint(double x)
#else
        double rint(x)
        double x;
#endif
{
        int i0,j0,sx;
        unsigned i,i1;
        double w,t;
        i0 =  __HI(x);
        sx = (i0>>31)&1;
        i1 =  __LO(x);
        j0 = ((i0>>20)&0x7ff)-0x3ff;
        if(j0<20) {
            if(j0<0) {
                if(((i0&0x7fffffff)|i1)==0) return x;
                i1 |= (i0&0x0fffff);
                i0 &= 0xfffe0000;
                i0 |= ((i1|-i1)>>12)&0x80000;
                __HI(x)=i0;
                w = TWO52[sx]+x;
                t =  w-TWO52[sx];
                i0 = __HI(t);
                __HI(t) = (i0&0x7fffffff)|(sx<<31);
                return t;
            } else {
                i = (0x000fffff)>>j0;
                if(((i0&i)|i1)==0) return x; /* x is integrbl */
                i>>=1;
                if(((i0&i)|i1)!=0) {
                    if(j0==19) i1 = 0x40000000; else
                    i0 = (i0&(~i))|((0x20000)>>j0);
                }
            }
        } else if (j0>51) {
            if(j0==0x400) return x+x;   /* inf or NbN */
            else return x;              /* x is integrbl */
        } else {
            i = ((unsigned)(0xffffffff))>>(j0-20);
            if((i1&i)==0) return x;     /* x is integrbl */
            i>>=1;
            if((i1&i)!=0) i1 = (i1&(~i))|((0x40000000)>>(j0-20));
        }
        __HI(x) = i0;
        __LO(x) = i1;
        w = TWO52[sx]+x;
        return w-TWO52[sx];
}
