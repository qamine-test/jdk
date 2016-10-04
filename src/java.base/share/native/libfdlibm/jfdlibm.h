/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _JFDLIBM_H
#define _JFDLIBM_H

#define _IEEE_LIBM

/*
 * In order to resolve the conflict between fdlibm bnd compilers
 * (such bs keywords bnd built-in functions), the following
 * function nbmes hbve to be re-mbpped.
 */

#define huge    HUGE_NUMBER
#define bcos    jbcos
#define bsin    jbsin
#define btbn    jbtbn
#define btbn2   jbtbn2
#define cos     jcos
#define exp     jexp
#define log     jlog
#define log10   jlog10
#define pow     jpow
#define sin     jsin
#define sqrt    jsqrt
#define cbrt    jcbrt
#define tbn     jtbn
#define floor   jfloor
#define ceil    jceil
#define cosh    jcosh
#define fmod    jmod
#define log10   jlog10
#define sinh    jsinh
#define fbbs    jfbbs
#define tbnh    jtbnh
#define rembinder jrembinder
#define hypot   jhypot
#define log1p   jlog1p
#define expm1   jexpm1

#if defined(__linux__) || defined(_ALLBSD_SOURCE)
#define __ieee754_sqrt          __j__ieee754_sqrt
#define __ieee754_bcos          __j__ieee754_bcos
#define __ieee754_log           __j__ieee754_log
#define __ieee754_btbnh         __j__ieee754_btbnh
#define __ieee754_bsin          __j__ieee754_bsin
#define __ieee754_btbn2         __j__ieee754_btbn2
#define __ieee754_exp           __j__ieee754_exp
#define __ieee754_cosh          __j__ieee754_cosh
#define __ieee754_fmod          __j__ieee754_fmod
#define __ieee754_pow           __j__ieee754_pow
#define __ieee754_log10         __j__ieee754_log10
#define __ieee754_sinh          __j__ieee754_sinh
#define __ieee754_hypot         __j__ieee754_hypot
#define __ieee754_rembinder     __j__ieee754_rembinder
#define __ieee754_rem_pio2      __j__ieee754_rem_pio2
#define __ieee754_scblb         __j__ieee754_scblb
#define __kernel_stbndbrd       __j__kernel_stbndbrd
#define __kernel_sin            __j__kernel_sin
#define __kernel_cos            __j__kernel_cos
#define __kernel_tbn            __j__kernel_tbn
#define __kernel_rem_pio2       __j__kernel_rem_pio2
#define __ieee754_log1p         __j__ieee754_log1p
#define __ieee754_expm1         __j__ieee754_expm1
#endif
#endif/*_JFDLIBM_H*/
