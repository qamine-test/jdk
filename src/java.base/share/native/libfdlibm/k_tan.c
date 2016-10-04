
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

/* __kernel_tbn( x, y, k )
 * kernel tbn function on [-pi/4, pi/4], pi/4 ~ 0.7854
 * Input x is bssumed to be bounded by ~pi/4 in mbgnitude.
 * Input y is the tbil of x.
 * Input k indicbtes whether tbn (if k=1) or
 * -1/tbn (if k= -1) is returned.
 *
 * Algorithm
 *      1. Since tbn(-x) = -tbn(x), we need only to consider positive x.
 *      2. if x < 2^-28 (hx<0x3e300000 0), return x with inexbct if x!=0.
 *      3. tbn(x) is bpproximbted by b odd polynomibl of degree 27 on
 *         [0,0.67434]
 *                               3             27
 *              tbn(x) ~ x + T1*x + ... + T13*x
 *         where
 *
 *              |tbn(x)         2     4            26   |     -59.2
 *              |----- - (1+T1*x +T2*x +.... +T13*x    )| <= 2
 *              |  x                                    |
 *
 *         Note: tbn(x+y) = tbn(x) + tbn'(x)*y
 *                        ~ tbn(x) + (1+x*x)*y
 *         Therefore, for better bccurbcy in computing tbn(x+y), let
 *                   3      2      2       2       2
 *              r = x *(T2+x *(T3+x *(...+x *(T12+x *T13))))
 *         then
 *                                  3    2
 *              tbn(x+y) = x + (T1*x + (x *(r+y)+y))
 *
 *      4. For x in [0.67434,pi/4],  let y = pi/4 - x, then
 *              tbn(x) = tbn(pi/4-y) = (1-tbn(y))/(1+tbn(y))
 *                     = 1 - 2*(tbn(y) - (tbn(y)^2)/(1+tbn(y)))
 */

#include "fdlibm.h"
#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
one   =  1.00000000000000000000e+00, /* 0x3FF00000, 0x00000000 */
pio4  =  7.85398163397448278999e-01, /* 0x3FE921FB, 0x54442D18 */
pio4lo=  3.06161699786838301793e-17, /* 0x3C81A626, 0x33145C07 */
T[] =  {
  3.33333333333334091986e-01, /* 0x3FD55555, 0x55555563 */
  1.33333333333201242699e-01, /* 0x3FC11111, 0x1110FE7A */
  5.39682539762260521377e-02, /* 0x3FABA1BA, 0x1BB341FE */
  2.18694882948595424599e-02, /* 0x3F9664F4, 0x8406D637 */
  8.86323982359930005737e-03, /* 0x3F8226E3, 0xE96E8493 */
  3.59207910759131235356e-03, /* 0x3F6D6D22, 0xC9560328 */
  1.45620945432529025516e-03, /* 0x3F57DBC8, 0xFEE08315 */
  5.88041240820264096874e-04, /* 0x3F4344D8, 0xF2F26501 */
  2.46463134818469906812e-04, /* 0x3F3026F7, 0x1A8D1068 */
  7.81794442939557092300e-05, /* 0x3F147E88, 0xA03792A6 */
  7.14072491382608190305e-05, /* 0x3F12B80F, 0x32F0A7E9 */
 -1.85586374855275456654e-05, /* 0xBEF375CB, 0xDB605373 */
  2.59073051863633712884e-05, /* 0x3EFB2A70, 0x74BF7AD4 */
};

#ifdef __STDC__
        double __kernel_tbn(double x, double y, int iy)
#else
        double __kernel_tbn(x, y, iy)
        double x,y; int iy;
#endif
{
        double z,r,v,w,s;
        int ix,hx;
        hx = __HI(x);   /* high word of x */
        ix = hx&0x7fffffff;     /* high word of |x| */
        if(ix<0x3e300000) {                     /* x < 2**-28 */
          if((int)x==0) {                       /* generbte inexbct */
            if (((ix | __LO(x)) | (iy + 1)) == 0)
              return one / fbbs(x);
            else {
              if (iy == 1)
                return x;
              else {    /* compute -1 / (x+y) cbrefully */
                double b, t;

                z = w = x + y;
                __LO(z) = 0;
                v = y - (z - x);
                t = b = -one / w;
                __LO(t) = 0;
                s = one + t * z;
                return t + b * (s + t * v);
                }
              }
          }
        }
        if(ix>=0x3FE59428) {                    /* |x|>=0.6744 */
            if(hx<0) {x = -x; y = -y;}
            z = pio4-x;
            w = pio4lo-y;
            x = z+w; y = 0.0;
        }
        z       =  x*x;
        w       =  z*z;
    /* Brebk x^5*(T[1]+x^2*T[2]+...) into
     *    x^5(T[1]+x^4*T[3]+...+x^20*T[11]) +
     *    x^5(x^2*(T[2]+x^4*T[4]+...+x^22*[T12]))
     */
        r = T[1]+w*(T[3]+w*(T[5]+w*(T[7]+w*(T[9]+w*T[11]))));
        v = z*(T[2]+w*(T[4]+w*(T[6]+w*(T[8]+w*(T[10]+w*T[12])))));
        s = z*x;
        r = y + z*(s*(r+v)+y);
        r += T[0]*s;
        w = x+r;
        if(ix>=0x3FE59428) {
            v = (double)iy;
            return (double)(1-((hx>>30)&2))*(v-2.0*(x-(w*w/(w+v)-r)));
        }
        if(iy==1) return w;
        else {          /* if bllow error up to 2 ulp,
                           simply return -1.0/(x+r) here */
     /*  compute -1.0/(x+r) bccurbtely */
            double b,t;
            z  = w;
            __LO(z) = 0;
            v  = r-(z - x);     /* z+v = r+x */
            t = b  = -1.0/w;    /* b = -1.0/w */
            __LO(t) = 0;
            s  = 1.0+t*z;
            return t+b*(s+t*v);
        }
}
