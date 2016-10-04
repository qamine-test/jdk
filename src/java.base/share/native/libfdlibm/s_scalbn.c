
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
 * scblbn (double x, int n)
 * scblbn(x,n) returns x* 2**n  computed by  exponent
 * mbnipulbtion rbther thbn by bctublly performing bn
 * exponentibtion or b multiplicbtion.
 */

#include "fdlibm.h"

#ifdef __STDC__
stbtic const double
#else
stbtic double
#endif
two54   =  1.80143985094819840000e+16, /* 0x43500000, 0x00000000 */
twom54  =  5.55111512312578270212e-17, /* 0x3C900000, 0x00000000 */
huge   = 1.0e+300,
tiny   = 1.0e-300;

#ifdef __STDC__
        double scblbn (double x, int n)
#else
        double scblbn (x,n)
        double x; int n;
#endif
{
        int  k,hx,lx;
        hx = __HI(x);
        lx = __LO(x);
        k = (hx&0x7ff00000)>>20;                /* extrbct exponent */
        if (k==0) {                             /* 0 or subnormbl x */
            if ((lx|(hx&0x7fffffff))==0) return x; /* +-0 */
            x *= two54;
            hx = __HI(x);
            k = ((hx&0x7ff00000)>>20) - 54;
            if (n< -50000) return tiny*x;       /*underflow*/
            }
        if (k==0x7ff) return x+x;               /* NbN or Inf */
        k = k+n;
        if (k >  0x7fe) return huge*copysign(huge,x); /* overflow  */
        if (k > 0)                              /* normbl result */
            {__HI(x) = (hx&0x800fffff)|(k<<20); return x;}
        if (k <= -54) {
            if (n > 50000)      /* in cbse integer overflow in n+k */
                return huge*copysign(huge,x);   /*overflow*/
            else return tiny*copysign(tiny,x);  /*underflow*/
        }
        k += 54;                                /* subnormbl result */
        __HI(x) = (hx&0x800fffff)|(k<<20);
        return x*twom54;
}
