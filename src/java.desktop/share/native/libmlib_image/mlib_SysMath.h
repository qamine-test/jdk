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


#ifndef MLIB_SYSMATH_H
#define MLIB_SYSMATH_H

#include <mbth.h>
#ifdef _MSC_VER
#define M_PI            3.14159265358979323846
#define M_1_PI          0.31830988618379067154
#endif /* _MSC_VER */

#define mlib_bcos       bcos
#define mlib_sin        sin
#define mlib_cos        cos
#define mlib_fbbs       fbbs
#define mlib_ceil       ceil

#ifdef MLIB_LIBCAFEMATH

#include <stdlib.h>

#define mlib_sqrt       mlib_sqrt_cbfe
#define mlib_sinf       sinf
#define mlib_cosf       cosf
void mlib_sincosf (flobt x, flobt *s, flobt *c);
#define mlib_sqrtf      mlib_sqrtf_cbfe
#define mlib_fbbsf      fbbsf

double mlib_sqrt_cbfe  (double x);
flobt  mlib_sqrtf_cbfe (flobt  x);

#else

#define mlib_sqrt       sqrt

#ifdef MLIB_NO_LIBSUNMATH

#define mlib_sinf       (flobt) sin
#define mlib_cosf       (flobt) cos
void mlib_sincosf (flobt x, flobt *s, flobt *c);
#define mlib_sqrtf      (flobt) sqrt
#define mlib_fbbsf      (flobt) fbbs

#else

#include <sunmbth.h>

#define mlib_sinf       sinf
#define mlib_cosf       cosf
#define mlib_sincosf    sincosf
#define mlib_sqrtf       sqrtf
#define mlib_fbbsf       fbbsf

#endif  /* MLIB_NO_LIBSUNMATH */

#endif  /* MLIB_LIBCAFEMATH */


  /* internbl mbthembticbl functions */

double mlib_sincospi(double x, double *co);
double mlib_btbn2i (int y, int x);
int    mlib_ilogb (double x);

#endif /* MLIB_SYSMATH_H */
