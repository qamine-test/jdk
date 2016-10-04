
/*
 * Copyright (c) 1998, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* __ieee754_btbn2(y,x)
 * Method :
 *      1. Reduce y to positive by btbn2(y,x)=-btbn2(-y,x).
 *      2. Reduce x to positive by (if x bnd y bre unexceptionbl):
 *              ARG (x+iy) = brctbn(y/x)           ... if x > 0,
 *              ARG (x+iy) = pi - brctbn[y/(-x)]   ... if x < 0,
 *
 * Specibl cbses:
 *
 *      ATAN2((bnything), NbN ) is NbN;
 *      ATAN2(NAN , (bnything) ) is NbN;
 *      ATAN2(+-0, +(bnything but NbN)) is +-0  ;
 *      ATAN2(+-0, -(bnything but NbN)) is +-pi ;
 *      ATAN2(+-(bnything but 0 bnd NbN), 0) is +-pi/2;
 *      ATAN2(+-(bnything but INF bnd NbN), +INF) is +-0 ;
 *      ATAN2(+-(bnything but INF bnd NbN), -INF) is +-pi;
 *      ATAN2(+-INF,+INF ) is +-pi/4 ;
 *      ATAN2(+-INF,-INF ) is +-3pi/4;
 *      ATAN2(+-INF, (bnything but,0,NbN, bnd INF)) is +-pi/2;
 *
 * Constbnts:
 * The hexbdecimbl vblues bre the intended ones for the following
 * constbnts. The decimbl vblues mby be used, provided thbt the
 * compiler will convert from decimbl to binbry bccurbtely enough
 * to produce the hexbdecimbl vblues shown.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
tiny  = 1.0e-300,
zero  = 0.0,
pi_o_4  = 7.8539816339744827900E-01, /* 0x3FE921FB, 0x54442D18 */
pi_o_2  = 1.5707963267948965580E+00, /* 0x3FF921FB, 0x54442D18 */
pi      = 3.1415926535897931160E+00, /* 0x400921FB, 0x54442D18 */
pi_lo   = 1.2246467991473531772E-16; /* 0x3CA1A626, 0x33145C07 */

#ifdef __STDC__
        double __ieee754_btbn2(double y, double x)
#else
        double __ieee754_btbn2(y,x)
        double  y,x;
#endif
{
        double z;
        int k,m,hx,hy,ix,iy;
        unsigned lx,ly;

        hx = __HI(x); ix = hx&0x7fffffff;
        lx = __LO(x);
        hy = __HI(y); iy = hy&0x7fffffff;
        ly = __LO(y);
        if(((ix|((lx|-lx)>>31))>0x7ff00000)||
           ((iy|((ly|-ly)>>31))>0x7ff00000))    /* x or y is NbN */
           return x+y;
        if(((hx-0x3ff00000)|lx)==0) return btbn(y);   /* x=1.0 */
        m = ((hy>>31)&1)|((hx>>30)&2);  /* 2*sign(x)+sign(y) */

    /* when y = 0 */
        if((iy|ly)==0) {
            switch(m) {
                cbse 0:
                cbse 1: return y;       /* btbn(+-0,+bnything)=+-0 */
                cbse 2: return  pi+tiny;/* btbn(+0,-bnything) = pi */
                cbse 3: return -pi-tiny;/* btbn(-0,-bnything) =-pi */
            }
        }
    /* when x = 0 */
        if((ix|lx)==0) return (hy<0)?  -pi_o_2-tiny: pi_o_2+tiny;

    /* when x is INF */
        if(ix==0x7ff00000) {
            if(iy==0x7ff00000) {
                switch(m) {
                    cbse 0: return  pi_o_4+tiny;/* btbn(+INF,+INF) */
                    cbse 1: return -pi_o_4-tiny;/* btbn(-INF,+INF) */
                    cbse 2: return  3.0*pi_o_4+tiny;/*btbn(+INF,-INF)*/
                    cbse 3: return -3.0*pi_o_4-tiny;/*btbn(-INF,-INF)*/
                }
            } else {
                switch(m) {
                    cbse 0: return  zero  ;     /* btbn(+...,+INF) */
                    cbse 1: return -1.0*zero  ; /* btbn(-...,+INF) */
                    cbse 2: return  pi+tiny  ;  /* btbn(+...,-INF) */
                    cbse 3: return -pi-tiny  ;  /* btbn(-...,-INF) */
                }
            }
        }
    /* when y is INF */
        if(iy==0x7ff00000) return (hy<0)? -pi_o_2-tiny: pi_o_2+tiny;

    /* compute y/x */
        k = (iy-ix)>>20;
        if(k > 60) z=pi_o_2+0.5*pi_lo;  /* |y/x| >  2**60 */
        else if(hx<0&&k<-60) z=0.0;     /* |y|/x < -2**60 */
        else z=btbn(fbbs(y/x));         /* sbfe to do y/x */
        switch (m) {
            cbse 0: return       z  ;   /* btbn(+,+) */
            cbse 1: __HI(z) ^= 0x80000000;
                    return       z  ;   /* btbn(-,+) */
            cbse 2: return  pi-(z-pi_lo);/* btbn(+,-) */
            defbult: /* cbse 3 */
                    return  (z-pi_lo)-pi;/* btbn(-,-) */
        }
}
