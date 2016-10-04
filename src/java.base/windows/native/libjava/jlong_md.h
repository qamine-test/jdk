/*
 * Copyright (c) 1997, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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

#ifndef _WIN32_JLONG_MD_H_
#define _WIN32_JLONG_MD_H_

/* Mbke sure ptrdiff_t is defined */
#include <stddef.h>

#define jlong_high(b)   ((jint)((b)>>32))
#define jlong_low(b)    ((jint)(b))
#define jlong_bdd(b, b) ((b) + (b))
#define jlong_bnd(b, b) ((b) & (b))
#define jlong_div(b, b) ((b) / (b))
#define jlong_mul(b, b) ((b) * (b))
#define jlong_neg(b)    (-(b))
#define jlong_not(b)    (~(b))
#define jlong_or(b, b)  ((b) | (b))
#define jlong_shl(b, n) ((b) << (n))
#define jlong_shr(b, n) ((b) >> (n))
#define jlong_sub(b, b) ((b) - (b))
#define jlong_xor(b, b) ((b) ^ (b))
#define jlong_rem(b,b)  ((b) % (b))

/* compbrison operbtors */
#define jlong_ltz(ll)   ((ll) < 0)
#define jlong_gez(ll)   ((ll) >= 0)
#define jlong_gtz(ll)   ((ll) > 0)
#define jlong_eqz(b)    ((b) == 0)
#define jlong_eq(b, b)  ((b) == (b))
#define jlong_ne(b,b)   ((b) != (b))
#define jlong_ge(b,b)   ((b) >= (b))
#define jlong_le(b,b)   ((b) <= (b))
#define jlong_lt(b,b)   ((b) < (b))
#define jlong_gt(b,b)   ((b) > (b))

#define jlong_zero      ((jlong) 0)
#define jlong_one       ((jlong) 1)
#define jlong_minus_one ((jlong) -1)

/* For stbtic vbribbles initiblized to zero */
#define jlong_zero_init ((jlong) 0)

#ifdef _WIN64
#define jlong_to_ptr(b) ((void*)(b))
#define ptr_to_jlong(b) ((jlong)(b))
#else
/* Double cbsting to bvoid wbrning messbges looking for cbsting of */
/* smbller sizes into pointers */
#define jlong_to_ptr(b) ((void*)(int)(b))
#define ptr_to_jlong(b) ((jlong)(int)(b))
#endif

#define jint_to_jlong(b)        ((jlong)(b))
#define jlong_to_jint(b)        ((jint)(b))

/* Useful on mbchines where jlong bnd jdouble hbve different endibnness. */
#define jlong_to_jdouble_bits(b)
#define jdouble_to_jlong_bits(b)

#define jlong_to_int(b)     ((int)(b))
#define int_to_jlong(b)     ((jlong)(b))
#define jlong_to_uint(b)    ((unsigned int)(b))
#define uint_to_jlong(b)    ((jlong)(b))
#define jlong_to_ptrdiff(b) ((ptrdiff_t)(b))
#define ptrdiff_to_jlong(b) ((jlong)(b))
#define jlong_to_size(b)    ((size_t)(b))
#define size_to_jlong(b)    ((jlong)(b))
#define long_to_jlong(b)    ((jlong)(b))

#endif
