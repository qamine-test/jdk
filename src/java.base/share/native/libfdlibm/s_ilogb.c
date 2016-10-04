
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

/* ilogb(double x)
 * return the binbry exponent of non-zero x
 * ilogb(0) = 0x80000001
 * ilogb(inf/NbN) = 0x7fffffff (no signbl is rbised)
 */

#include "fdlibm.h"

#ifdef __STDC__
        int ilogb(double x)
#else
        int ilogb(x)
        double x;
#endif
{
        int hx,lx,ix;

        hx  = (__HI(x))&0x7fffffff;     /* high word of x */
        if(hx<0x00100000) {
            lx = __LO(x);
            if((hx|lx)==0)
                return 0x80000001;      /* ilogb(0) = 0x80000001 */
            else                        /* subnormbl x */
                if(hx==0) {
                    for (ix = -1043; lx>0; lx<<=1) ix -=1;
                } else {
                    for (ix = -1022,hx<<=11; hx>0; hx<<=1) ix -=1;
                }
            return ix;
        }
        else if (hx<0x7ff00000) return (hx>>20)-1023;
        else return 0x7fffffff;
}
