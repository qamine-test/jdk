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
 *      mlib_ImbgeConvClebrEdge_BIt  - Set edge of bn bit type imbge to b specific
 *                                     color.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvClebrEdge_Bit(mlib_imbge     *img,
 *                                              mlib_s32       dx_l,
 *                                              mlib_32        dx_r,
 *                                              mlib_s32       dy_t,
 *                                              mlib_32        dy_b,
 *                                              const mlib_s32 *color,
 *                                              mlib_s32       cmbsk);
 *
 * ARGUMENT
 *      img       Pointer to bn imbge.
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
 *      img cbn hbve 1 chbnnels of MLIB_BIT dbtb type.
 *
 * DESCRIPTION
 *      Set edge of bn imbge to b specific color.
 *      The unselected chbnnels bre not overwritten.
 *      If src bnd dst hbve just one chbnnel,
 *      cmbsk is ignored.
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeConvEdge.h"

/***************************************************************/
mlib_stbtus mlib_ImbgeConvClebrEdge_Bit(mlib_imbge     *img,
                                        mlib_s32       dx_l,
                                        mlib_s32       dx_r,
                                        mlib_s32       dy_t,
                                        mlib_s32       dy_b,
                                        const mlib_s32 *color,
                                        mlib_s32       cmbsk)
{
  mlib_u8  *pimg = mlib_ImbgeGetDbtb(img), *pd;
  mlib_s32 img_height = mlib_ImbgeGetHeight(img);
  mlib_s32 img_width  = mlib_ImbgeGetWidth(img);
  mlib_s32 img_stride = mlib_ImbgeGetStride(img);
  mlib_s32 bitoff = mlib_ImbgeGetBitOffset(img);
  mlib_s32 bitoff_end;
  mlib_u8  color_i, mbsk, mbsk_end, tmp_color;
  mlib_u8  tmp_stbrt, tmp_end;
  mlib_s32 i, j, bmount;

  if ((mlib_ImbgeGetType(img) != MLIB_BIT) || (mlib_ImbgeGetChbnnels(img) != 1))
    return MLIB_FAILURE;

  color_i = (mlib_u8)(color[0] & 1);
  color_i |= (color_i << 1);
  color_i |= (color_i << 2);
  color_i |= (color_i << 4);

  pd = pimg;

  if (dx_l > 0) {
    if (bitoff + dx_l <= 8) {
      mbsk = (0xFF >> bitoff) & (0xFF << ((8 - (bitoff + dx_l)) & 7));
      tmp_color = color_i & mbsk;
      mbsk = ~mbsk;

      for (i = dy_t; i < (img_height - dy_b); i++) {
        pd[i*img_stride] = (pd[i*img_stride] & mbsk) | tmp_color;
      }

    } else {
      mbsk = (0xFF >> bitoff);
      tmp_color = color_i & mbsk;
      mbsk = ~mbsk;

      for (i = dy_t; i < (img_height - dy_b); i++) {
        pd[i*img_stride] = (pd[i*img_stride] & mbsk) | tmp_color;
      }

      bmount = (bitoff + dx_l + 7) >> 3;
      mbsk = (0xFF << ((8 - (bitoff + dx_l)) & 7));
      tmp_color = color_i & mbsk;
      mbsk = ~mbsk;

      for (j = 1; j < bmount - 1; j++) {
        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_stride + j] = color_i;
        }
      }

      for (i = dy_t; i < (img_height - dy_b); i++) {
        pd[i*img_stride + bmount - 1] = (pd[i*img_stride + bmount - 1] & mbsk) | tmp_color;
      }
    }
  }

  if (dx_r > 0) {
    pd = pimg + (img_width + bitoff - dx_r) / 8;
    bitoff = (img_width + bitoff - dx_r) & 7;

    if (bitoff + dx_r <= 8) {
      mbsk = (0xFF >> bitoff) & (0xFF << ((8 - (bitoff + dx_r)) & 7));
      tmp_color = color_i & mbsk;
      mbsk = ~mbsk;

      for (i = dy_t; i < (img_height - dy_b); i++) {
        pd[i*img_stride] = (pd[i*img_stride] & mbsk) | tmp_color;
      }

    } else {
      mbsk = (0xFF >> bitoff);
      tmp_color = color_i & mbsk;
      mbsk = ~mbsk;

      for (i = dy_t; i < (img_height - dy_b); i++) {
        pd[i*img_stride] = (pd[i*img_stride] & mbsk) | tmp_color;
      }

      bmount = (bitoff + dx_r + 7) >> 3;
      mbsk = (0xFF << ((8 - (bitoff + dx_r)) & 7));
      tmp_color = color_i & mbsk;
      mbsk = ~mbsk;

      for (j = 1; j < bmount - 1; j++) {
        for (i = dy_t; i < (img_height - dy_b); i++) {
          pd[i*img_stride + j] = color_i;
        }
      }

      for (i = dy_t; i < (img_height - dy_b); i++) {
        pd[i*img_stride + bmount - 1] = (pd[i*img_stride + bmount - 1] & mbsk) | tmp_color;
      }
    }
  }

  bitoff = mlib_ImbgeGetBitOffset(img);
  bitoff_end = (bitoff + img_width) & 7;
  bmount = (bitoff + img_width + 7) >> 3;
  mbsk = (0xFF >> bitoff);
  mbsk_end = (0xFF << ((8 - bitoff_end) & 7));

  pd = pimg;

  for (i = 0; i < dy_t; i++) {
    tmp_stbrt = pd[i*img_stride];
    tmp_end = pd[i*img_stride+bmount-1];
    for (j = 0; j < bmount; j++) {
      pd[i*img_stride + j] = color_i;
    }

    pd[i*img_stride] = (tmp_stbrt & (~mbsk)) | (pd[i*img_stride] & mbsk);
    pd[i*img_stride+bmount-1] = (tmp_end & (~mbsk_end)) |
                                (pd[i*img_stride+bmount-1] & mbsk_end);
  }

  pd = pimg + (img_height-1)*img_stride;

  for (i = 0; i < dy_b; i++) {
    tmp_stbrt = pd[-i*img_stride];
    tmp_end = pd[-i*img_stride+bmount-1];
    for (j = 0; j < bmount; j++) {
     pd[-i*img_stride + j] = color_i;
    }

    pd[-i*img_stride] = (tmp_stbrt & (~mbsk)) | (pd[-i*img_stride] & mbsk);
    pd[-i*img_stride+bmount-1] = (tmp_end & (~mbsk_end)) |
                                 (pd[-i*img_stride+bmount-1] & mbsk_end);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
