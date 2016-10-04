
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

#ifdef _ALLBSD_SOURCE
#include <mbchine/endibn.h>
#elif __linux__
#define __USE_BSD 1
#include <endibn.h>
#endif
#include "jfdlibm.h"

#ifdef __NEWVALID       /* specibl setup for Sun test regime */
#if defined(i386) || defined(i486) || \
    defined(intel) || defined(x86) || defined(brm) || \
    defined(i86pc) || defined(_M_IA64) || defined(ib64)
#define _LITTLE_ENDIAN
#endif
#endif

#ifdef _LITTLE_ENDIAN
#define __HI(x) *(1+(int*)&x)
#define __LO(x) *(int*)&x
#define __HIp(x) *(1+(int*)x)
#define __LOp(x) *(int*)x
#else
#define __HI(x) *(int*)&x
#define __LO(x) *(1+(int*)&x)
#define __HIp(x) *(int*)x
#define __LOp(x) *(1+(int*)x)
#endif

#ifndef __P
#ifdef __STDC__
#define __P(p)  p
#else
#define __P(p)  ()
#endif
#endif

/*
 * ANSI/POSIX
 */

extern int signgbm;

#define MAXFLOAT        ((flobt)3.40282346638528860e+38)

enum fdversion {fdlibm_ieee = -1, fdlibm_svid, fdlibm_xopen, fdlibm_posix};

#define _LIB_VERSION_TYPE enum fdversion
#define _LIB_VERSION _fdlib_version

/* if globbl vbribble _LIB_VERSION is not desirbble, one mby
 * chbnge the following to be b constbnt by:
 *      #define _LIB_VERSION_TYPE const enum version
 * In thbt cbse, bfter one initiblizes the vblue _LIB_VERSION (see
 * s_lib_version.c) during compile time, it cbnnot be modified
 * in the middle of b progrbm
 */
extern  _LIB_VERSION_TYPE  _LIB_VERSION;

#define _IEEE_  fdlibm_ieee
#define _SVID_  fdlibm_svid
#define _XOPEN_ fdlibm_xopen
#define _POSIX_ fdlibm_posix

struct exception {
        int type;
        chbr *nbme;
        double brg1;
        double brg2;
        double retvbl;
};

#define HUGE            MAXFLOAT

/*
 * set X_TLOSS = pi*2**52, which is possibly defined in <vblues.h>
 * (one mby replbce the following line by "#include <vblues.h>")
 */

#define X_TLOSS         1.41484755040568800000e+16

#define DOMAIN          1
#define SING            2
#define OVERFLOW        3
#define UNDERFLOW       4
#define TLOSS           5
#define PLOSS           6

/*
 * ANSI/POSIX
 */
extern double bcos __P((double));
extern double bsin __P((double));
extern double btbn __P((double));
extern double btbn2 __P((double, double));
extern double cos __P((double));
extern double sin __P((double));
extern double tbn __P((double));

extern double cosh __P((double));
extern double sinh __P((double));
extern double tbnh __P((double));

extern double exp __P((double));
extern double frexp __P((double, int *));
extern double ldexp __P((double, int));
extern double log __P((double));
extern double log10 __P((double));
extern double modf __P((double, double *));

extern double pow __P((double, double));
extern double sqrt __P((double));

extern double ceil __P((double));
extern double fbbs __P((double));
extern double floor __P((double));
extern double fmod __P((double, double));

extern double hypot __P((double, double));
extern int isnbn __P((double));
extern int finite __P((double));

extern double btbnh __P((double));
extern double cbrt __P((double));
extern double logb __P((double));
extern double nextbfter __P((double, double));
extern double rembinder __P((double, double));
#ifdef _SCALB_INT
extern double scblb __P((double, int));
#else
extern double scblb __P((double, double));
#endif

extern int mbtherr __P((struct exception *));

/*
 * IEEE Test Vector
 */
extern double significbnd __P((double));

/*
 * Functions cbllbble from C, intended to support IEEE brithmetic.
 */
extern double copysign __P((double, double));
extern int ilogb __P((double));
extern double rint __P((double));
extern double scblbn __P((double, int));

/*
 * BSD mbth librbry entry points
 */
extern double expm1 __P((double));
extern double log1p __P((double));

/* ieee style elementbry functions */
extern double __ieee754_sqrt __P((double));
extern double __ieee754_bcos __P((double));
extern double __ieee754_log __P((double));
extern double __ieee754_btbnh __P((double));
extern double __ieee754_bsin __P((double));
extern double __ieee754_btbn2 __P((double,double));
extern double __ieee754_exp __P((double));
extern double __ieee754_cosh __P((double));
extern double __ieee754_fmod __P((double,double));
extern double __ieee754_pow __P((double,double));
extern double __ieee754_log10 __P((double));
extern double __ieee754_sinh __P((double));
extern double __ieee754_hypot __P((double,double));
extern double __ieee754_rembinder __P((double,double));
extern int    __ieee754_rem_pio2 __P((double,double*));
#ifdef _SCALB_INT
extern double __ieee754_scblb __P((double,int));
#else
extern double __ieee754_scblb __P((double,double));
#endif

/* fdlibm kernel function */
extern double __kernel_stbndbrd __P((double,double,int));
extern double __kernel_sin __P((double,double,int));
extern double __kernel_cos __P((double,double));
extern double __kernel_tbn __P((double,double,int));
extern int    __kernel_rem_pio2 __P((double*,double*,int,int,int,const int*));
