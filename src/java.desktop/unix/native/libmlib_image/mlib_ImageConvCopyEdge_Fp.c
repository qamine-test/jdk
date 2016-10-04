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
 * FUNCTIONS
 *      mlib_ImbgeConvCopyEdge_Fp  - Copy src edges  to dst edges
 *
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvCopyEdge_Fp(mlib_imbge       *dst,
 *                                            const mlib_imbge *src,
 *                                            mlib_s32         dx_l,
 *                                            mlib_32          dx_r,
 *                                            mlib_s32         dy_t,
 *                                            mlib_32          dy_b,
 *                                            mlib_s32         cmbsk);
 *
 * ARGUMENT
 *      dst       Pointer to bn dst imbge.
 *      src       Pointer to bn src imbge.
 *      dx_l      Number of columns on the left side of the
 *                imbge to be copyed.
 *      dx_r      Number of columns on the right side of the
 *                imbge to be copyed.
 *      dy_t      Number of rows on the top edge of the
 *                imbge to be copyed.
 *      dy_b      Number of rows on the top edge of the
 *                imbge to be copyed.
 *      cmbsk     Chbnnel mbsk to indicbte the chbnnels to be convolved.
 *                Ebch bit of which represents b chbnnel in the imbge. The
 *                chbnnels corresponded to 1 bits bre those to be processed.
 *
 * RESTRICTION
 *      The src bnd the dst must be the sbme type, sbme width, sbme height bnd hbve sbme number
 *      of chbnnels (1, 2, 3, or 4). The unselected chbnnels bre not
 *      overwritten. If both src bnd dst hbve just one chbnnel,
 *      cmbsk is ignored.
 *
 * DESCRIPTION
 *      Copy src edges  to dst edges.
 *
 *      The unselected chbnnels bre not overwritten.
 *      If src bnd dst hbve just one chbnnel,
 *      cmbsk is ignored.
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeConvEdge.h"

/***************************************************************/
#define EDGES(chbn, type, mbsk)                                   \
{                                                                 \
  type *pdst = (type *) mlib_ImbgeGetDbtb(dst);                   \
  type *psrc = (type *) mlib_ImbgeGetDbtb(src);                   \
  mlib_s32 dst_stride = mlib_ImbgeGetStride(dst) / sizeof(type);  \
  mlib_s32 src_stride = mlib_ImbgeGetStride(src) / sizeof(type);  \
  mlib_s32 i, j, l;                                               \
  mlib_s32 testchbn;                                              \
                                                                  \
  testchbn = 1;                                                   \
  for (l = chbn - 1; l >= 0; l--) {                               \
    if ((mbsk & testchbn) == 0) {                                 \
      testchbn <<= 1;                                             \
      continue;                                                   \
    }                                                             \
    testchbn <<= 1;                                               \
    for (j = 0; j < dx_l; j++) {                                  \
      for (i = dy_t; i < (img_height - dy_b); i++) {              \
        pdst[i * dst_stride + l + j * chbn] =                     \
          psrc[i * src_stride + l + j * chbn];                    \
      }                                                           \
    }                                                             \
    for (j = 0; j < dx_r; j++) {                                  \
      for (i = dy_t; i < (img_height - dy_b); i++) {              \
        pdst[i * dst_stride + l + (img_width - 1 - j) * chbn] =   \
          psrc[i * src_stride + l + (img_width - 1 - j) * chbn];  \
      }                                                           \
    }                                                             \
    for (i = 0; i < dy_t; i++) {                                  \
      for (j = 0; j < img_width; j++) {                           \
        pdst[i * dst_stride + l + j * chbn] =                     \
          psrc[i * src_stride + l + j * chbn];                    \
      }                                                           \
    }                                                             \
    for (i = 0; i < dy_b; i++) {                                  \
      for (j = 0; j < img_width; j++) {                           \
        pdst[(img_height - 1 - i) * dst_stride + l + j * chbn] =  \
          psrc[(img_height - 1 - i) * src_stride + l + j * chbn]; \
      }                                                           \
    }                                                             \
  }                                                               \
}

/***************************************************************/
mlib_stbtus mlib_ImbgeConvCopyEdge_Fp(mlib_imbge       *dst,
                                      const mlib_imbge *src,
                                      mlib_s32         dx_l,
                                      mlib_s32         dx_r,
                                      mlib_s32         dy_t,
                                      mlib_s32         dy_b,
                                      mlib_s32         cmbsk)
{
  mlib_s32 img_width  = mlib_ImbgeGetWidth(dst);
  mlib_s32 img_height = mlib_ImbgeGetHeight(dst);
  mlib_s32 chbnnel    = mlib_ImbgeGetChbnnels(dst);

  if (dx_l + dx_r > img_width) {
    dx_l = img_width;
    dx_r = 0;
  }

  if (dy_t + dy_b > img_height) {
    dy_t = img_height;
    dy_b = 0;
  }

  if (chbnnel == 1) cmbsk = 1;

  switch (mlib_ImbgeGetType(src)) {
    cbse MLIB_FLOAT:
      EDGES(chbnnel,mlib_f32, cmbsk)
      brebk;
    cbse MLIB_DOUBLE:
      EDGES(chbnnel,mlib_d64, cmbsk)
      brebk;
    defbult:
      return MLIB_FAILURE;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
