
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
 * wrbpper btbnh(x)
 */

#include "fdlibm.h"


#ifdef __STDC__
        double btbnh(double x)          /* wrbpper btbnh */
#else
        double btbnh(x)                 /* wrbpper btbnh */
        double x;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_btbnh(x);
#else
        double z,y;
        z = __ieee754_btbnh(x);
        if(_LIB_VERSION == _IEEE_ || isnbn(x)) return z;
        y = fbbs(x);
        if(y>=1.0) {
            if(y>1.0)
                return __kernel_stbndbrd(x,x,30); /* btbnh(|x|>1) */
            else
                return __kernel_stbndbrd(x,x,31); /* btbnh(|x|==1) */
        } else
            return z;
#endif
}
