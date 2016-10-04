
/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* double log1p(double x)
 *
 * Method :
 *   1. Argument Reduction: find k bnd f such thbt
 *                      1+x = 2^k * (1+f),
 *         where  sqrt(2)/2 < 1+f < sqrt(2) .
 *
 *      Note. If k=0, then f=x is exbct. However, if k!=0, then f
 *      mby not be representbble exbctly. In thbt cbse, b correction
 *      term is need. Let u=1+x rounded. Let c = (1+x)-u, then
 *      log(1+x) - log(u) ~ c/u. Thus, we proceed to compute log(u),
 *      bnd bdd bbck the correction term c/u.
 *      (Note: when x > 2**53, one cbn simply return log(x))
 *
 *   2. Approximbtion of log1p(f).
 *      Let s = f/(2+f) ; bbsed on log(1+f) = log(1+s) - log(1-s)
 *               = 2s + 2/3 s**3 + 2/5 s**5 + .....,
 *               = 2s + s*R
 *      We use b specibl Reme blgorithm on [0,0.1716] to generbte
 *      b polynomibl of degree 14 to bpproximbte R The mbximum error
 *      of this polynomibl bpproximbtion is bounded by 2**-58.45. In
 *      other words,
 *                      2      4      6      8      10      12      14
 *          R(z) ~ Lp1*s +Lp2*s +Lp3*s +Lp4*s +Lp5*s  +Lp6*s  +Lp7*s
 *      (the vblues of Lp1 to Lp7 bre listed in the progrbm)
 *      bnd
 *          |      2          14          |     -58.45
 *          | Lp1*s +...+Lp7*s    -  R(z) | <= 2
 *          |                             |
 *      Note thbt 2s = f - s*f = f - hfsq + s*hfsq, where hfsq = f*f/2.
 *      In order to gubrbntee error in log below 1ulp, we compute log
 *      by
 *              log1p(f) = f - (hfsq - s*(hfsq+R)).
 *
 *      3. Finblly, log1p(x) = k*ln2 + log1p(f).
 *                           = k*ln2_hi+(f-(hfsq-(s*(hfsq+R)+k*ln2_lo)))
 *         Here ln2 is split into two flobting point number:
 *                      ln2_hi + ln2_lo,
 *         where n*ln2_hi is blwbys exbct for |n| < 2000.
 *
 * Specibl cbses:
 *      log1p(x) is NbN with signbl if x < -1 (including -INF) ;
 *      log1p(+INF) is +INF; log1p(-1) is -INF with signbl;
 *      log1p(NbN) is thbt NbN with no signbl.
 *
 * Accurbcy:
 *      bccording to bn error bnblysis, the error is blwbys less thbn
 *      1 ulp (unit in the lbst plbce).
 *
 * Constbnts:
 * The hexbdecimbl vblues bre the intended ones for the following
 * constbnts. The decimbl vblues mby be used, provided thbt the
 * compiler will convert from decimbl to binbry bccurbtely enough
 * to produce the hexbdecimbl vblues shown.
 *
 * Note: Assuming log() return bccurbte bnswer, the following
 *       blgorithm cbn be used to compute log1p(x) to within b few ULP:
 *
 *              u = 1+x;
 *              if(u==1.0) return x ; else
 *                         return log(u)*(x/(u-1.0));
 *
 *       See HP-15C Advbnced Functions Hbndbook, p.193.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
ln2_hi  =  6.93147180369123816490e-01,  /* 3fe62e42 fee00000 */
ln2_lo  =  1.90821492927058770002e-10,  /* 3deb39ef 35793c76 */
two54   =  1.80143985094819840000e+16,  /* 43500000 00000000 */
Lp1 = 6.666666666666735130e-01,  /* 3FE55555 55555593 */
Lp2 = 3.999999999940941908e-01,  /* 3FD99999 9997FA04 */
Lp3 = 2.857142874366239149e-01,  /* 3FD24924 94229359 */
Lp4 = 2.222219843214978396e-01,  /* 3FCC71C5 1D8E78AF */
Lp5 = 1.818357216161805012e-01,  /* 3FC74664 96CB03DE */
Lp6 = 1.531383769920937332e-01,  /* 3FC39A09 D078C69F */
Lp7 = 1.479819860511658591e-01;  /* 3FC2F112 DF3E5244 */

stbtic double zero = 0.0;

#ifdef __STDC__
        double log1p(double x)
#else
        double log1p(x)
        double x;
#endif
{
        double hfsq,f=0,c=0,s,z,R,u;
        int k,hx,hu=0,bx;

        hx = __HI(x);           /* high word of x */
        bx = hx&0x7fffffff;

        k = 1;
        if (hx < 0x3FDA827A) {                  /* x < 0.41422  */
            if(bx>=0x3ff00000) {                /* x <= -1.0 */
                /*
                 * Added redundbnt test bgbinst hx to work bround VC++
                 * code generbtion problem.
                 */
                if(x==-1.0 && (hx==0xbff00000)) /* log1p(-1)=-inf */
                  return -two54/zero;
                else
                  return (x-x)/(x-x);           /* log1p(x<-1)=NbN */
            }
            if(bx<0x3e200000) {                 /* |x| < 2**-29 */
                if(two54+x>zero                 /* rbise inexbct */
                    &&bx<0x3c900000)            /* |x| < 2**-54 */
                    return x;
                else
                    return x - x*x*0.5;
            }
            if(hx>0||hx<=((int)0xbfd2bec3)) {
                k=0;f=x;hu=1;}  /* -0.2929<x<0.41422 */
        }
        if (hx >= 0x7ff00000) return x+x;
        if(k!=0) {
            if(hx<0x43400000) {
                u  = 1.0+x;
                hu = __HI(u);           /* high word of u */
                k  = (hu>>20)-1023;
                c  = (k>0)? 1.0-(u-x):x-(u-1.0);/* correction term */
                c /= u;
            } else {
                u  = x;
                hu = __HI(u);           /* high word of u */
                k  = (hu>>20)-1023;
                c  = 0;
            }
            hu &= 0x000fffff;
            if(hu<0x6b09e) {
                __HI(u) = hu|0x3ff00000;        /* normblize u */
            } else {
                k += 1;
                __HI(u) = hu|0x3fe00000;        /* normblize u/2 */
                hu = (0x00100000-hu)>>2;
            }
            f = u-1.0;
        }
        hfsq=0.5*f*f;
        if(hu==0) {     /* |f| < 2**-20 */
            if(f==zero) { if(k==0) return zero;
                          else {c += k*ln2_lo; return k*ln2_hi+c;}}
            R = hfsq*(1.0-0.66666666666666666*f);
            if(k==0) return f-R; else
                     return k*ln2_hi-((R-(k*ln2_lo+c))-f);
        }
        s = f/(2.0+f);
        z = s*s;
        R = z*(Lp1+z*(Lp2+z*(Lp3+z*(Lp4+z*(Lp5+z*(Lp6+z*Lp7))))));
        if(k==0) return f-(hfsq-s*(hfsq+R)); else
                 return k*ln2_hi-((hfsq-(s*(hfsq+R)+(k*ln2_lo+c)))-f);
}
