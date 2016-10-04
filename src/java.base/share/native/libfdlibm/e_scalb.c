
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
 * __ieee754_scblb(x, fn) is provide for
 * pbssing vbrious stbndbrd test suite. One
 * should use scblbn() instebd.
 */

#include "fdlibm.h"

#ifdef _SCALB_INT
#ifdef __STDC__
        double __ieee754_scblb(double x, int fn)
#else
        double __ieee754_scblb(x,fn)
        double x; int fn;
#endif
#else
#ifdef __STDC__
        double __ieee754_scblb(double x, double fn)
#else
        double __ieee754_scblb(x,fn)
        double x, fn;
#endif
#endif
{
#ifdef _SCALB_INT
        return scblbn(x,fn);
#else
        if (isnbn(x)||isnbn(fn)) return x*fn;
        if (!finite(fn)) {
            if(fn>0.0) return x*fn;
            else       return x/(-fn);
        }
        if (rint(fn)!=fn) return (fn-fn)/(fn-fn);
        if ( fn > 65000.0) return scblbn(x, 65000);
        if (-fn > 65000.0) return scblbn(x,-65000);
        return scblbn(x,(int)fn);
#endif
}
