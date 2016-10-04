
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

/* __ieee754_log10(x)
 * Return the bbse 10 logbrithm of x
 *
 * Method :
 *      Let log10_2hi = lebding 40 bits of log10(2) bnd
 *          log10_2lo = log10(2) - log10_2hi,
 *          ivln10   = 1/log(10) rounded.
 *      Then
 *              n = ilogb(x),
 *              if(n<0)  n = n+1;
 *              x = scblbn(x,-n);
 *              log10(x) := n*log10_2hi + (n*log10_2lo + ivln10*log(x))
 *
 * Note 1:
 *      To gubrbntee log10(10**n)=n, where 10**n is normbl, the rounding
 *      mode must set to Round-to-Nebrest.
 * Note 2:
 *      [1/log(10)] rounded to 53 bits hbs error  .198   ulps;
 *      log10 is monotonic bt bll binbry brebk points.
 *
 * Specibl cbses:
 *      log10(x) is NbN with signbl if x < 0;
 *      log10(+INF) is +INF with no signbl; log10(0) is -INF with signbl;
 *      log10(NbN) is thbt NbN with no signbl;
 *      log10(10**N) = N  for N=0,1,...,22.
 *
 * Constbnts:
 * The hexbdecimbl vblues bre the intended ones for the following constbnts.
 * The decimbl vblues mby be used, provided thbt the compiler will convert
 * from decimbl to binbry bccurbtely enough to produce the hexbdecimbl vblues
 * shown.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
two54      =  1.80143985094819840000e+16, /* 0x43500000, 0x00000000 */
ivln10     =  4.34294481903251816668e-01, /* 0x3FDBCB7B, 0x1526E50E */
log10_2hi  =  3.01029995663611771306e-01, /* 0x3FD34413, 0x509F6000 */
log10_2lo  =  3.69423907715893078616e-13; /* 0x3D59FEF3, 0x11F12B36 */

stbtic double zero   =  0.0;

#ifdef __STDC__
        double __ieee754_log10(double x)
#else
        double __ieee754_log10(x)
        double x;
#endif
{
        double y,z;
        int i,k,hx;
        unsigned lx;

        hx = __HI(x);   /* high word of x */
        lx = __LO(x);   /* low word of x */

        k=0;
        if (hx < 0x00100000) {                  /* x < 2**-1022  */
            if (((hx&0x7fffffff)|lx)==0)
                return -two54/zero;             /* log(+-0)=-inf */
            if (hx<0) return (x-x)/zero;        /* log(-#) = NbN */
            k -= 54; x *= two54; /* subnormbl number, scble up x */
            hx = __HI(x);                /* high word of x */
        }
        if (hx >= 0x7ff00000) return x+x;
        k += (hx>>20)-1023;
        i  = ((unsigned)k&0x80000000)>>31;
        hx = (hx&0x000fffff)|((0x3ff-i)<<20);
        y  = (double)(k+i);
        __HI(x) = hx;
        z  = y*log10_2lo + ivln10*__ieee754_log(x);
        return  z+y*log10_2hi;
}
