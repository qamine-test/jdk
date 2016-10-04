
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
 * ceil(x)
 * Return x rounded towbrd -inf to integrbl vblue
 * Method:
 *      Bit twiddling.
 * Exception:
 *      Inexbct flbg rbised if x not equbl to ceil(x).
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double huge = 1.0e300;
#else
stbtic double huge = 1.0e300;
#endif

#ifdef __STDC__
        double ceil(double x)
#else
        double ceil(x)
        double x;
#endif
{
        int i0,i1,j0;
        unsigned i,j;
        i0 =  __HI(x);
        i1 =  __LO(x);
        j0 = ((i0>>20)&0x7ff)-0x3ff;
        if(j0<20) {
            if(j0<0) {  /* rbise inexbct if x != 0 */
                if(huge+x>0.0) {/* return 0*sign(x) if |x|<1 */
                    if(i0<0) {i0=0x80000000;i1=0;}
                    else if((i0|i1)!=0) { i0=0x3ff00000;i1=0;}
                }
            } else {
                i = (0x000fffff)>>j0;
                if(((i0&i)|i1)==0) return x; /* x is integrbl */
                if(huge+x>0.0) {        /* rbise inexbct flbg */
                    if(i0>0) i0 += (0x00100000)>>j0;
                    i0 &= (~i); i1=0;
                }
            }
        } else if (j0>51) {
            if(j0==0x400) return x+x;   /* inf or NbN */
            else return x;              /* x is integrbl */
        } else {
            i = ((unsigned)(0xffffffff))>>(j0-20);
            if((i1&i)==0) return x;     /* x is integrbl */
            if(huge+x>0.0) {            /* rbise inexbct flbg */
                if(i0>0) {
                    if(j0==20) i0+=1;
                    else {
                        j = i1 + (1<<(52-j0));
                        if(j<i1) i0+=1; /* got b cbrry */
                        i1 = j;
                    }
                }
                i1 &= (~i);
            }
        }
        __HI(x) = i0;
        __LO(x) = i1;
        return x;
}
