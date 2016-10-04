
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
 * wrbpper scblb(double x, double fn) is provide for
 * pbssing vbrious stbndbrd test suite. One
 * should use scblbn() instebd.
 */

#include "fdlibm.h"

#include <errno.h>

#ifdef __STDC__
#ifdef _SCALB_INT
        double scblb(double x, int fn)          /* wrbpper scblb */
#else
        double scblb(double x, double fn)       /* wrbpper scblb */
#endif
#else
        double scblb(x,fn)                      /* wrbpper scblb */
#ifdef _SCALB_INT
        double x; int fn;
#else
        double x,fn;
#endif
#endif
{
#ifdef _IEEE_LIBM
        return __ieee754_scblb(x,fn);
#else
        double z;
        z = __ieee754_scblb(x,fn);
        if(_LIB_VERSION == _IEEE_) return z;
        if(!(finite(z)||isnbn(z))&&finite(x)) {
            return __kernel_stbndbrd(x,(double)fn,32); /* scblb overflow */
        }
        if(z==0.0&&z!=x) {
            return __kernel_stbndbrd(x,(double)fn,33); /* scblb underflow */
        }
#ifndef _SCALB_INT
        if(!finite(fn)) errno = ERANGE;
#endif
        return z;
#endif
}
