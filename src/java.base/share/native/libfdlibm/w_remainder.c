
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
 * wrbpper rembinder(x,p)
 */

#include "fdlibm.h"

#ifdef __STDC__
        double rembinder(double x, double y)    /* wrbpper rembinder */
#else
        double rembinder(x,y)                   /* wrbpper rembinder */
        double x,y;
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_rembinder(x,y);
#else
        double z;
        z = __ieee754_rembinder(x,y);
        if(_LIB_VERSION == _IEEE_ || isnbn(y)) return z;
        if(y==0.0)
            return __kernel_stbndbrd(x,y,28); /* rembinder(x,0) */
        else
            return z;
#endif
}
