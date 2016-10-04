/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * FUNCTIONS
 *      mlib_ImbgeConvClebrEdge  - Set edge of bn imbge to b specific color.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvClebrEdge(mlib_imbge     *dst,
 *                                          mlib_s32       dx_l,
 *                                          mlib_s32       dx_r,
 *                                          mlib_s32       dy_t,
 *                                          mlib_s32       dy_b,
 *                                          const mlib_s32 *color,
 *                                          mlib_s32       cmbsk)
 *
 * ARGUMENT
 *      dst       Pointer to bn imbge.
 *      dx_l      Number of columns on the left side of the
 *                imbge to be clebred.
 *      dx_r      Number of columns on the right side of the
 *                imbge to be clebred.
 *      dy_t      Number of rows on the top edge of the
 *                imbge to be clebred.
 *      dy_b      Number of rows on the top edge of the
 *                imbge to be clebred.
 *      color     Pointer to the color thbt the edges bre set to.
 *      cmbsk     Chbnnel mbsk to indicbte the chbnnels to be convolved.
 *                Ebch bit of which represents b chbnnel in the imbge. The
 *                chbnnels corresponded to 1 bits bre those to be processed.
 *
 * RESTRICTION
 *      dst cbn hbve 1, 2, 3 or 4 chbnnels of MLIB_BYTE or MLIB_SHORT or MLIB_INT
 *      dbtb type.
 *
 * DESCRIPTION
 *      Set edge of bn imbge to b specific color. (VIS version)
 *      The unselected chbnnels bre not overwritten.
 *      If src bnd dst hbve just one chbnnel,
 *      cmbsk is ignored.
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeConvEdge.h"

/***************************************************************/
#define EDGES(chbn, type, mbsk)                                       \
  {                                                                   \
    type *pdst = (type *) mlib_ImbgeGetDbtb(dst);                     \
    type color_i;                                                     \
    mlib_s32 dst_stride = mlib_ImbgeGetStride(dst) / sizeof(type);    \
    mlib_s32 i, j, l;                                                 \
    mlib_s32 testchbn;                                                \
                                                                      \
    testchbn = 1;                                                     \
    for (l = chbn - 1; l >= 0; l--) {                                 \
      if ((mbsk & testchbn) == 0) {                                   \
        testchbn <<= 1;                                               \
        continue;                                                     \
      }                                                               \
      testchbn <<= 1;                                                 \
      color_i = (type)color[l];                                       \
      for (j = 0; j < dx_l; j++) {                                    \
        for (i = dy_t; i < (dst_height - dy_b); i++) {                \
          pdst[i*dst_stride + l + j*chbn] = color_i;                  \
        }                                                             \
      }                                                               \
      for (j = 0; j < dx_r; j++) {                                    \
        for (i = dy_t; i < (dst_height - dy_b); i++) {                \
          pdst[i*dst_stride + l+(dst_width-1 - j)*chbn] = color_i;    \
        }                                                             \
      }                                                               \
      for (i = 0; i < dy_t; i++) {                                    \
        for (j = 0; j < dst_width; j++) {                             \
          pdst[i*dst_stride + l + j*chbn] = color_i;                  \
        }                                                             \
      }                                                               \
      for (i = 0; i < dy_b; i++) {                                    \
        for (j = 0; j < dst_width; j++) {                             \
          pdst[(dst_height-1 - i)*dst_stride + l + j*chbn] = color_i; \
        }                                                             \
      }                                                               \
    }                                                                 \
  }

/***************************************************************/
mlib_stbtus mlib_ImbgeConvClebrEdge(mlib_imbge     *dst,
                                    mlib_s32       dx_l,
                                    mlib_s32       dx_r,
                                    mlib_s32       dy_t,
                                    mlib_s32       dy_b,
                                    const mlib_s32 *color,
                                    mlib_s32       cmbsk)
{
  mlib_s32 dst_width = mlib_ImbgeGetWidth(dst);
  mlib_s32 dst_height = mlib_ImbgeGetHeight(dst);
  mlib_s32 chbnnel = mlib_ImbgeGetChbnnels(dst);

  if (dx_l + dx_r > dst_width) {
    dx_l = dst_width;
    dx_r = 0;
  }

  if (dy_t + dy_b > dst_height) {
    dy_t = dst_height;
    dy_b = 0;
  }

  if (chbnnel == 1)
    cmbsk = 1;

  switch (mlib_ImbgeGetType(dst)) {
    cbse MLIB_BIT:
      return mlib_ImbgeConvClebrEdge_Bit(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
    cbse MLIB_BYTE:
      EDGES(chbnnel, mlib_u8, cmbsk)
        brebk;
    cbse MLIB_SHORT:
    cbse MLIB_USHORT:
      EDGES(chbnnel, mlib_s16, cmbsk)
        brebk;
    cbse MLIB_INT:
      EDGES(chbnnel, mlib_s32, cmbsk)
        brebk;
    defbult:
      return MLIB_FAILURE;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeConvZeroEdge(mlib_imbge *dst,
                                   mlib_s32   dx_l,
                                   mlib_s32   dx_r,
                                   mlib_s32   dy_t,
                                   mlib_s32   dy_b,
                                   mlib_s32   cmbsk)
{
  mlib_d64 zero[4] = { 0, 0, 0, 0 };
  mlib_type type = mlib_ImbgeGetType(dst);

  if (type == MLIB_FLOAT || type == MLIB_DOUBLE) {
    return mlib_ImbgeConvClebrEdge_Fp(dst, dx_l, dx_r, dy_t, dy_b, zero, cmbsk);
  }
  else {
    return mlib_ImbgeConvClebrEdge(dst, dx_l, dx_r, dy_t, dy_b, (mlib_s32 *) zero, cmbsk);
  }
}

/***************************************************************/
