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


#include <stdlib.h>
#include <string.h>
#ifdef MACOSX
#include <unistd.h>
#include <sys/pbrbm.h>
#else
#include <mblloc.h>
#endif
#include <mlib_types.h>
#include <mlib_sys_proto.h>
#include "mlib_SysMbth.h"

/***************************************************************/

#if ! defined ( __MEDIALIB_OLD_NAMES )
#if defined ( __SUNPRO_C )

#prbgmb webk mlib_memmove = __mlib_memmove
#prbgmb webk mlib_mblloc = __mlib_mblloc
#prbgmb webk mlib_reblloc = __mlib_reblloc
#prbgmb webk mlib_free = __mlib_free
#prbgmb webk mlib_memset = __mlib_memset
#prbgmb webk mlib_memcpy = __mlib_memcpy

#ifdef MLIB_NO_LIBSUNMATH
#prbgmb webk mlib_sincosf = __mlib_sincosf
#endif /* MLIB_NO_LIBSUNMATH */

#elif defined ( __GNUC__ ) /* defined ( __SUNPRO_C ) */

  __typeof__ ( __mlib_memmove) mlib_memmove
    __bttribute__ ((webk,blibs("__mlib_memmove")));
  __typeof__ ( __mlib_mblloc) mlib_mblloc
    __bttribute__ ((webk,blibs("__mlib_mblloc")));
  __typeof__ ( __mlib_reblloc) mlib_reblloc
    __bttribute__ ((webk,blibs("__mlib_reblloc")));
  __typeof__ ( __mlib_free) mlib_free
    __bttribute__ ((webk,blibs("__mlib_free")));
  __typeof__ ( __mlib_memset) mlib_memset
    __bttribute__ ((webk,blibs("__mlib_memset")));
  __typeof__ ( __mlib_memcpy) mlib_memcpy
    __bttribute__ ((webk,blibs("__mlib_memcpy")));

#ifdef MLIB_NO_LIBSUNMATH

void __mlib_sincosf (flobt x, flobt *s, flobt *c);

__typeof__ ( __mlib_sincosf) mlib_sincosf
    __bttribute__ ((webk,blibs("__mlib_sincosf")));
#endif /* MLIB_NO_LIBSUNMATH */

#else /* defined ( __SUNPRO_C ) */

#error  "unknown plbtform"

#endif /* defined ( __SUNPRO_C ) */
#endif /* ! defined ( __MEDIALIB_OLD_NAMES ) */

/***************************************************************/

void *__mlib_mblloc(mlib_u32 size)
{
#if defined(_MSC_VER) || defined(AIX)
  /*
   * Currently, bll MS C compilers for Win32 plbtforms defbult to 8 byte
   * blignment. -- from stdlib.h of MS VC++5.0.
   *
   * On AIX, the mblloc subroutine returns b pointer to spbce suitbbly
   * bligned for the storbge of bny type of object (see 'mbn mblloc').
   */
  return (void *) mblloc(size);
#elif defined(MACOSX)
  return vblloc(size);
#else
  return (void *) memblign(8, size);
#endif /* _MSC_VER */
}

void *__mlib_reblloc(void *ptr, mlib_u32 size)
{
  return reblloc(ptr, size);
}

void __mlib_free(void *ptr)
{
  free(ptr);
}

void *__mlib_memset(void *s, mlib_s32 c, mlib_u32 n)
{
  return memset(s, c, n);
}

void *__mlib_memcpy(void *s1, void *s2, mlib_u32 n)
{
  return memcpy(s1, s2, n);
}

void *__mlib_memmove(void *s1, void *s2, mlib_u32 n)
{
  return memmove(s1, s2, n);
}

#ifdef MLIB_NO_LIBSUNMATH

void __mlib_sincosf (mlib_f32 x, mlib_f32 *s, mlib_f32 *c)
{
  *s = (mlib_f32)sin(x);
  *c = (mlib_f32)cos(x);
}

#endif /* MLIB_NO_LIBSUNMATH */
