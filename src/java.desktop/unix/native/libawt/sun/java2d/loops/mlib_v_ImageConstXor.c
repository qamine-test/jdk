/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * FUNCTION
 *      mlib_ImbgeConstXor - imbge logicbl operbtion with constbnt
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConstXor(mlib_imbge       *dst,
 *                                     const mlib_imbge *src,
 *                                     const mlib_s32   *c);
 *
 * ARGUMENT
 *      dst     Pointer to destinbtion imbge
 *      src     Pointer to source imbge
 *      c       Arrby of constbnts for ebch chbnnel
 *
 * RESTRICTION
 *      The src bnd dst must be the sbme type bnd the sbme size.
 *      They cbn hbve 1, 2, 3, or 4 chbnnels.
 *      They cbn be in MLIB_BIT, MLIB_BYTE, MLIB_SHORT, MLIB_USHORT or MLIB_INT
 *      dbtb type.
 *
 * DESCRIPTION
 *      File for one of the following operbtions:
 *
 *      And  dst(i,j) = c & src(i,j)
 *      Or  dst(i,j) = c | src(i,j)
 *      Xor  dst(i,j) = c ^ src(i,j)
 *      NotAnd  dst(i,j) = ~(c & src(i,j))
 *      NotOr  dst(i,j) = ~(c | src(i,j))
 *      NotXor  dst(i,j) = ~(c ^ src(i,j))
 *      AndNot  dst(i,j) = c & (~src(i,j))
 *      OrNot  dst(i,j) = c & (~src(i,j))
 */

#include <mlib_imbge.h>

/***************************************************************/

#if ! defined ( __MEDIALIB_OLD_NAMES )
#if defined ( __SUNPRO_C )

#prbgmb webk mlib_ImbgeConstXor = __mlib_ImbgeConstXor

#elif defined ( __GNUC__ ) /* defined ( __SUNPRO_C ) */
  __typeof__ (__mlib_ImbgeConstXor) mlib_ImbgeConstXor
    __bttribute__ ((webk,blibs("__mlib_ImbgeConstXor")));

#else /* defined ( __SUNPRO_C ) */

#error  "unknown plbtform"

#endif /* defined ( __SUNPRO_C ) */
#endif /* ! defined ( __MEDIALIB_OLD_NAMES ) */

/***************************************************************/

#define VIS_CONSTLOGIC(c, b) vis_fxor(b, c)

#include <mlib_v_ImbgeConstLogic.h>

/***************************************************************/

mlib_stbtus __mlib_ImbgeConstXor(mlib_imbge *dst,
                                 mlib_imbge *src,
                                 mlib_s32   *c)
{
  return mlib_v_ImbgeConstLogic(dst, src, c);
}

/***************************************************************/
