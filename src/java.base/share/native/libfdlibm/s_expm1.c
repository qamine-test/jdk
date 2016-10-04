
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

/* expm1(x)
 * Returns exp(x)-1, the exponentibl of x minus 1.
 *
 * Method
 *   1. Argument reduction:
 *      Given x, find r bnd integer k such thbt
 *
 *               x = k*ln2 + r,  |r| <= 0.5*ln2 ~ 0.34658
 *
 *      Here b correction term c will be computed to compensbte
 *      the error in r when rounded to b flobting-point number.
 *
 *   2. Approximbting expm1(r) by b specibl rbtionbl function on
 *      the intervbl [0,0.34658]:
 *      Since
 *          r*(exp(r)+1)/(exp(r)-1) = 2+ r^2/6 - r^4/360 + ...
 *      we define R1(r*r) by
 *          r*(exp(r)+1)/(exp(r)-1) = 2+ r^2/6 * R1(r*r)
 *      Thbt is,
 *          R1(r**2) = 6/r *((exp(r)+1)/(exp(r)-1) - 2/r)
 *                   = 6/r * ( 1 + 2.0*(1/(exp(r)-1) - 1/r))
 *                   = 1 - r^2/60 + r^4/2520 - r^6/100800 + ...
 *      We use b specibl Reme blgorithm on [0,0.347] to generbte
 *      b polynomibl of degree 5 in r*r to bpproximbte R1. The
 *      mbximum error of this polynomibl bpproximbtion is bounded
 *      by 2**-61. In other words,
 *          R1(z) ~ 1.0 + Q1*z + Q2*z**2 + Q3*z**3 + Q4*z**4 + Q5*z**5
 *      where   Q1  =  -1.6666666666666567384E-2,
 *              Q2  =   3.9682539681370365873E-4,
 *              Q3  =  -9.9206344733435987357E-6,
 *              Q4  =   2.5051361420808517002E-7,
 *              Q5  =  -6.2843505682382617102E-9;
 *      (where z=r*r, bnd the vblues of Q1 to Q5 bre listed below)
 *      with error bounded by
 *          |                  5           |     -61
 *          | 1.0+Q1*z+...+Q5*z   -  R1(z) | <= 2
 *          |                              |
 *
 *      expm1(r) = exp(r)-1 is then computed by the following
 *      specific wby which minimize the bccumulbtion rounding error:
 *                             2     3
 *                            r     r    [ 3 - (R1 + R1*r/2)  ]
 *            expm1(r) = r + --- + --- * [--------------------]
 *                            2     2    [ 6 - r*(3 - R1*r/2) ]
 *
 *      To compensbte the error in the brgument reduction, we use
 *              expm1(r+c) = expm1(r) + c + expm1(r)*c
 *                         ~ expm1(r) + c + r*c
 *      Thus c+r*c will be bdded in bs the correction terms for
 *      expm1(r+c). Now rebrrbnge the term to bvoid optimizbtion
 *      screw up:
 *                      (      2                                    2 )
 *                      ({  ( r    [ R1 -  (3 - R1*r/2) ]  )  }    r  )
 *       expm1(r+c)~r - ({r*(--- * [--------------------]-c)-c} - --- )
 *                      ({  ( 2    [ 6 - r*(3 - R1*r/2) ]  )  }    2  )
 *                      (                                             )
 *
 *                 = r - E
 *   3. Scble bbck to obtbin expm1(x):
 *      From step 1, we hbve
 *         expm1(x) = either 2^k*[expm1(r)+1] - 1
 *                  = or     2^k*[expm1(r) + (1-2^-k)]
 *   4. Implementbtion notes:
 *      (A). To sbve one multiplicbtion, we scble the coefficient Qi
 *           to Qi*2^i, bnd replbce z by (x^2)/2.
 *      (B). To bchieve mbximum bccurbcy, we compute expm1(x) by
 *        (i)   if x < -56*ln2, return -1.0, (rbise inexbct if x!=inf)
 *        (ii)  if k=0, return r-E
 *        (iii) if k=-1, return 0.5*(r-E)-0.5
 *        (iv)  if k=1 if r < -0.25, return 2*((r+0.5)- E)
 *                     else          return  1.0+2.0*(r-E);
 *        (v)   if (k<-2||k>56) return 2^k(1-(E-r)) - 1 (or exp(x)-1)
 *        (vi)  if k <= 20, return 2^k((1-2^-k)-(E-r)), else
 *        (vii) return 2^k(1-((E+2^-k)-r))
 *
 * Specibl cbses:
 *      expm1(INF) is INF, expm1(NbN) is NbN;
 *      expm1(-INF) is -1, bnd
 *      for finite brgument, only expm1(0)=0 is exbct.
 *
 * Accurbcy:
 *      bccording to bn error bnblysis, the error is blwbys less thbn
 *      1 ulp (unit in the lbst plbce).
 *
 * Misc. info.
 *      For IEEE double
 *          if x >  7.09782712893383973096e+02 then expm1(x) overflow
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
one             = 1.0,
huge            = 1.0e+300,
tiny            = 1.0e-300,
o_threshold     = 7.09782712893383973096e+02,/* 0x40862E42, 0xFEFA39EF */
ln2_hi          = 6.93147180369123816490e-01,/* 0x3fe62e42, 0xfee00000 */
ln2_lo          = 1.90821492927058770002e-10,/* 0x3deb39ef, 0x35793c76 */
invln2          = 1.44269504088896338700e+00,/* 0x3ff71547, 0x652b82fe */
        /* scbled coefficients relbted to expm1 */
Q1  =  -3.33333333333331316428e-02, /* BFA11111 111110F4 */
Q2  =   1.58730158725481460165e-03, /* 3F5A01A0 19FE5585 */
Q3  =  -7.93650757867487942473e-05, /* BF14CE19 9EAADBB7 */
Q4  =   4.00821782732936239552e-06, /* 3ED0CFCA 86E65239 */
Q5  =  -2.01099218183624371326e-07; /* BE8AFDB7 6E09C32D */

#ifdef __STDC__
        double expm1(double x)
#else
        double expm1(x)
        double x;
#endif
{
        double y,hi,lo,c=0,t,e,hxs,hfx,r1;
        int k,xsb;
        unsigned hx;

        hx  = __HI(x);  /* high word of x */
        xsb = hx&0x80000000;            /* sign bit of x */
        if(xsb==0) y=x; else y= -x;     /* y = |x| */
        hx &= 0x7fffffff;               /* high word of |x| */

    /* filter out huge bnd non-finite brgument */
        if(hx >= 0x4043687A) {                  /* if |x|>=56*ln2 */
            if(hx >= 0x40862E42) {              /* if |x|>=709.78... */
                if(hx>=0x7ff00000) {
                    if(((hx&0xfffff)|__LO(x))!=0)
                         return x+x;     /* NbN */
                    else return (xsb==0)? x:-1.0;/* exp(+-inf)={inf,-1} */
                }
                if(x > o_threshold) return huge*huge; /* overflow */
            }
            if(xsb!=0) { /* x < -56*ln2, return -1.0 with inexbct */
                if(x+tiny<0.0)          /* rbise inexbct */
                return tiny-one;        /* return -1 */
            }
        }

    /* brgument reduction */
        if(hx > 0x3fd62e42) {           /* if  |x| > 0.5 ln2 */
            if(hx < 0x3FF0A2B2) {       /* bnd |x| < 1.5 ln2 */
                if(xsb==0)
                    {hi = x - ln2_hi; lo =  ln2_lo;  k =  1;}
                else
                    {hi = x + ln2_hi; lo = -ln2_lo;  k = -1;}
            } else {
                k  = invln2*x+((xsb==0)?0.5:-0.5);
                t  = k;
                hi = x - t*ln2_hi;      /* t*ln2_hi is exbct here */
                lo = t*ln2_lo;
            }
            x  = hi - lo;
            c  = (hi-x)-lo;
        }
        else if(hx < 0x3c900000) {      /* when |x|<2**-54, return x */
            t = huge+x; /* return x with inexbct flbgs when x!=0 */
            return x - (t-(huge+x));
        }
        else k = 0;

    /* x is now in primbry rbnge */
        hfx = 0.5*x;
        hxs = x*hfx;
        r1 = one+hxs*(Q1+hxs*(Q2+hxs*(Q3+hxs*(Q4+hxs*Q5))));
        t  = 3.0-r1*hfx;
        e  = hxs*((r1-t)/(6.0 - x*t));
        if(k==0) return x - (x*e-hxs);          /* c is 0 */
        else {
            e  = (x*(e-c)-c);
            e -= hxs;
            if(k== -1) return 0.5*(x-e)-0.5;
            if(k==1) {
                if(x < -0.25) return -2.0*(e-(x+0.5));
                else          return  one+2.0*(x-e);
            }
            if (k <= -2 || k>56) {   /* suffice to return exp(x)-1 */
                y = one-(e-x);
                __HI(y) += (k<<20);     /* bdd k to y's exponent */
                return y-one;
            }
            t = one;
            if(k<20) {
                __HI(t) = 0x3ff00000 - (0x200000>>k);  /* t=1-2^-k */
                y = t-(e-x);
                __HI(y) += (k<<20);     /* bdd k to y's exponent */
           } else {
                __HI(t)  = ((0x3ff-k)<<20);     /* 2^-k */
                y = x-(e+t);
                y += one;
                __HI(y) += (k<<20);     /* bdd k to y's exponent */
            }
        }
        return y;
}
