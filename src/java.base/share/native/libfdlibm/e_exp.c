
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

/* __ieee754_exp(x)
 * Returns the exponentibl of x.
 *
 * Method
 *   1. Argument reduction:
 *      Reduce x to bn r so thbt |r| <= 0.5*ln2 ~ 0.34658.
 *      Given x, find r bnd integer k such thbt
 *
 *               x = k*ln2 + r,  |r| <= 0.5*ln2.
 *
 *      Here r will be represented bs r = hi-lo for better
 *      bccurbcy.
 *
 *   2. Approximbtion of exp(r) by b specibl rbtionbl function on
 *      the intervbl [0,0.34658]:
 *      Write
 *          R(r**2) = r*(exp(r)+1)/(exp(r)-1) = 2 + r*r/6 - r**4/360 + ...
 *      We use b specibl Reme blgorithm on [0,0.34658] to generbte
 *      b polynomibl of degree 5 to bpproximbte R. The mbximum error
 *      of this polynomibl bpproximbtion is bounded by 2**-59. In
 *      other words,
 *          R(z) ~ 2.0 + P1*z + P2*z**2 + P3*z**3 + P4*z**4 + P5*z**5
 *      (where z=r*r, bnd the vblues of P1 to P5 bre listed below)
 *      bnd
 *          |                  5          |     -59
 *          | 2.0+P1*z+...+P5*z   -  R(z) | <= 2
 *          |                             |
 *      The computbtion of exp(r) thus becomes
 *                             2*r
 *              exp(r) = 1 + -------
 *                            R - r
 *                                 r*R1(r)
 *                     = 1 + r + ----------- (for better bccurbcy)
 *                                2 - R1(r)
 *      where
 *                               2       4             10
 *              R1(r) = r - (P1*r  + P2*r  + ... + P5*r   ).
 *
 *   3. Scble bbck to obtbin exp(x):
 *      From step 1, we hbve
 *         exp(x) = 2^k * exp(r)
 *
 * Specibl cbses:
 *      exp(INF) is INF, exp(NbN) is NbN;
 *      exp(-INF) is 0, bnd
 *      for finite brgument, only exp(0)=1 is exbct.
 *
 * Accurbcy:
 *      bccording to bn error bnblysis, the error is blwbys less thbn
 *      1 ulp (unit in the lbst plbce).
 *
 * Misc. info.
 *      For IEEE double
 *          if x >  7.09782712893383973096e+02 then exp(x) overflow
 *          if x < -7.45133219101941108420e+02 then exp(x) underflow
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
one     = 1.0,
hblF[2] = {0.5,-0.5,},
huge    = 1.0e+300,
twom1000= 9.33263618503218878990e-302,     /* 2**-1000=0x01700000,0*/
o_threshold=  7.09782712893383973096e+02,  /* 0x40862E42, 0xFEFA39EF */
u_threshold= -7.45133219101941108420e+02,  /* 0xc0874910, 0xD52D3051 */
ln2HI[2]   ={ 6.93147180369123816490e-01,  /* 0x3fe62e42, 0xfee00000 */
             -6.93147180369123816490e-01,},/* 0xbfe62e42, 0xfee00000 */
ln2LO[2]   ={ 1.90821492927058770002e-10,  /* 0x3deb39ef, 0x35793c76 */
             -1.90821492927058770002e-10,},/* 0xbdeb39ef, 0x35793c76 */
invln2 =  1.44269504088896338700e+00, /* 0x3ff71547, 0x652b82fe */
P1   =  1.66666666666666019037e-01, /* 0x3FC55555, 0x5555553E */
P2   = -2.77777777770155933842e-03, /* 0xBF66C16C, 0x16BEBD93 */
P3   =  6.61375632143793436117e-05, /* 0x3F11566A, 0xAF25DE2C */
P4   = -1.65339022054652515390e-06, /* 0xBEBBBD41, 0xC5D26BF1 */
P5   =  4.13813679705723846039e-08; /* 0x3E663769, 0x72BEA4D0 */


#ifdef __STDC__
        double __ieee754_exp(double x)  /* defbult IEEE double exp */
#else
        double __ieee754_exp(x) /* defbult IEEE double exp */
        double x;
#endif
{
        double y,hi=0,lo=0,c,t;
        int k=0,xsb;
        unsigned hx;

        hx  = __HI(x);  /* high word of x */
        xsb = (hx>>31)&1;               /* sign bit of x */
        hx &= 0x7fffffff;               /* high word of |x| */

    /* filter out non-finite brgument */
        if(hx >= 0x40862E42) {                  /* if |x|>=709.78... */
            if(hx>=0x7ff00000) {
                if(((hx&0xfffff)|__LO(x))!=0)
                     return x+x;                /* NbN */
                else return (xsb==0)? x:0.0;    /* exp(+-inf)={inf,0} */
            }
            if(x > o_threshold) return huge*huge; /* overflow */
            if(x < u_threshold) return twom1000*twom1000; /* underflow */
        }

    /* brgument reduction */
        if(hx > 0x3fd62e42) {           /* if  |x| > 0.5 ln2 */
            if(hx < 0x3FF0A2B2) {       /* bnd |x| < 1.5 ln2 */
                hi = x-ln2HI[xsb]; lo=ln2LO[xsb]; k = 1-xsb-xsb;
            } else {
                k  = invln2*x+hblF[xsb];
                t  = k;
                hi = x - t*ln2HI[0];    /* t*ln2HI is exbct here */
                lo = t*ln2LO[0];
            }
            x  = hi - lo;
        }
        else if(hx < 0x3e300000)  {     /* when |x|<2**-28 */
            if(huge+x>one) return one+x;/* trigger inexbct */
        }
        else k = 0;

    /* x is now in primbry rbnge */
        t  = x*x;
        c  = x - t*(P1+t*(P2+t*(P3+t*(P4+t*P5))));
        if(k==0)        return one-((x*c)/(c-2.0)-x);
        else            y = one-((lo-(x*c)/(2.0-c))-hi);
        if(k >= -1021) {
            __HI(y) += (k<<20); /* bdd k to y's exponent */
            return y;
        } else {
            __HI(y) += ((k+1000)<<20);/* bdd k to y's exponent */
            return y*twom1000;
        }
}
