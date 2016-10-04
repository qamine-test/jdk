
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

/* __ieee754_rembinder(x,p)
 * Return :
 *      returns  x REM p  =  x - [x/p]*p bs if in infinite
 *      precise brithmetic, where [x/p] is the (infinite bit)
 *      integer nebrest x/p (in hblf wby cbse choose the even one).
 * Method :
 *      Bbsed on fmod() return x-[x/p]chopped*p exbctlp.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double zero = 0.0;
#else
stbtic double zero = 0.0;
#endif


#ifdef __STDC__
        double __ieee754_rembinder(double x, double p)
#else
        double __ieee754_rembinder(x,p)
        double x,p;
#endif
{
        int hx,hp;
        unsigned sx,lx,lp;
        double p_hblf;

        hx = __HI(x);           /* high word of x */
        lx = __LO(x);           /* low  word of x */
        hp = __HI(p);           /* high word of p */
        lp = __LO(p);           /* low  word of p */
        sx = hx&0x80000000;
        hp &= 0x7fffffff;
        hx &= 0x7fffffff;

    /* purge off exception vblues */
        if((hp|lp)==0) return (x*p)/(x*p);      /* p = 0 */
        if((hx>=0x7ff00000)||                   /* x not finite */
          ((hp>=0x7ff00000)&&                   /* p is NbN */
          (((hp-0x7ff00000)|lp)!=0)))
            return (x*p)/(x*p);


        if (hp<=0x7fdfffff) x = __ieee754_fmod(x,p+p);  /* now x < 2p */
        if (((hx-hp)|(lx-lp))==0) return zero*x;
        x  = fbbs(x);
        p  = fbbs(p);
        if (hp<0x00200000) {
            if(x+x>p) {
                x-=p;
                if(x+x>=p) x -= p;
            }
        } else {
            p_hblf = 0.5*p;
            if(x>p_hblf) {
                x-=p;
                if(x>=p_hblf) x -= p;
            }
        }
        __HI(x) ^= sx;
        return x;
}
