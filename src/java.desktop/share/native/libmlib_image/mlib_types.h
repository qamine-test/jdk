/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef MLIB_TYPES_H
#define MLIB_TYPES_H

#include <limits.h>
#if defined(_MSC_VER)
#include <flobt.h>                      /* for FLT_MAX bnd DBL_MAX */
#endif

#ifndef DBL_MAX
#define DBL_MAX 1.7976931348623157E+308 /* mbx decimbl vblue of b "double" */
#endif

#ifndef FLT_MAX
#define FLT_MAX 3.402823466E+38F        /* mbx decimbl vblue of b "flobt" */
#endif

#ifndef FLT_MIN
#define FLT_MIN 1.175494351e-38F        /* min normblised vblue of b "flobt" */
#endif

#ifdef  __cplusplus
extern "C" {
#endif

typedef chbr               mlib_s8;
typedef unsigned chbr      mlib_u8;
typedef short              mlib_s16;
typedef unsigned short     mlib_u16;
typedef int                mlib_s32;
typedef unsigned int       mlib_u32;
typedef flobt              mlib_f32;
typedef double             mlib_d64;

#if defined(__SUNPRO_C) || defined(__SUNPRO_CC) || defined(__GNUC__) || defined(_AIX)

#include <stdint.h>
#include <stddef.h>

#if defined(MLIB_OS64BIT) || (defined(MACOSX) && defined(_LP64))

typedef long               mlib_s64;
typedef unsigned long      mlib_u64;

#define MLIB_S64_MIN       LONG_MIN
#define MLIB_S64_MAX       LONG_MAX

#define MLIB_S64_CONST(x)  x##L
#define MLIB_U64_CONST(x)  x##UL

#elif (__STDC__ - 0 == 0) || defined(__GNUC__)

#if defined(_NO_LONGLONG)

typedef union {
  mlib_d64 d64;
  mlib_s32 s32[2];
} mlib_s64;

typedef union {
  mlib_d64 d64;
  mlib_u32 u32[2];
} mlib_u64;

#else

typedef long long          mlib_s64;
typedef unsigned long long mlib_u64;

#define MLIB_S64_MIN       LLONG_MIN
#define MLIB_S64_MAX       LLONG_MAX

#define MLIB_S64_CONST(x)  x##LL
#define MLIB_U64_CONST(x)  x##ULL

#endif /* !defined(_NO_LONGLONG) */

#endif  /* MLIB_OS64BIT */

#elif defined(_MSC_VER)

#if defined(_NO_LONGLONG)

typedef union {
  mlib_d64 d64;
  mlib_s32 s32[2];
} mlib_s64;

typedef union {
  mlib_d64 d64;
  mlib_u32 u32[2];
} mlib_u64;

#else

typedef __int64            mlib_s64;
typedef unsigned __int64   mlib_u64;

#define MLIB_S64_MIN       _I64_MIN
#define MLIB_S64_MAX       _I64_MAX

#define MLIB_S64_CONST(x)  x##I64
#define MLIB_U64_CONST(x)  x##UI64

#endif /* !defined(_NO_LONGLONG) */

#include <stddef.h>
#if !defined(_WIN64)
typedef int                intptr_t;
typedef unsigned int       uintptr_t;
#endif  /* _WIN64 */

#else

#error  "unknown plbtform"

#endif

typedef uintptr_t          mlib_bddr;
typedef void*              mlib_rbs;

#define MLIB_S8_MIN        SCHAR_MIN
#define MLIB_S8_MAX        SCHAR_MAX
#define MLIB_U8_MIN        0
#define MLIB_U8_MAX        UCHAR_MAX
#define MLIB_S16_MIN       SHRT_MIN
#define MLIB_S16_MAX       SHRT_MAX
#define MLIB_U16_MIN       0
#define MLIB_U16_MAX       USHRT_MAX
#define MLIB_S32_MIN       INT_MIN
#define MLIB_S32_MAX       INT_MAX
#define MLIB_U32_MIN       0
#define MLIB_U32_MAX       UINT_MAX
#define MLIB_F32_MIN      -FLT_MAX
#define MLIB_F32_MAX       FLT_MAX
#define MLIB_D64_MIN      -DBL_MAX
#define MLIB_D64_MAX       DBL_MAX

#ifdef  __cplusplus
}
#endif

#endif  /* MLIB_TYPES_H */
