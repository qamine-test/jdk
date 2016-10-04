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



/*
 * FUNCTIONS
 *      mlib_ImbgeConvClebrEdge  - Set edge of bn imbge to b specific
 *                                        color. (VIS version)
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
#include "vis_proto.h"
#include "mlib_ImbgeConvEdge.h"

/***************************************************************/
stbtic void mlib_ImbgeConvClebrEdge_U8_1(mlib_imbge     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         const mlib_s32 *color);

stbtic void mlib_ImbgeConvClebrEdge_U8_2(mlib_imbge     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         const mlib_s32 *color,
                                         mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_U8_3(mlib_imbge     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         const mlib_s32 *color,
                                         mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_U8_4(mlib_imbge     *dst,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r,
                                         mlib_s32       dy_t,
                                         mlib_s32       dy_b,
                                         const mlib_s32 *color,
                                         mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_S16_1(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color);

stbtic void mlib_ImbgeConvClebrEdge_S16_2(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color,
                                          mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_S16_3(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color,
                                          mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_S16_4(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color,
                                          mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_S32_1(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color);

stbtic void mlib_ImbgeConvClebrEdge_S32_2(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color,
                                          mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_S32_3(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color,
                                          mlib_s32       cmbsk);

stbtic void mlib_ImbgeConvClebrEdge_S32_4(mlib_imbge     *dst,
                                          mlib_s32       dx_l,
                                          mlib_s32       dx_r,
                                          mlib_s32       dy_t,
                                          mlib_s32       dy_b,
                                          const mlib_s32 *color,
                                          mlib_s32       cmbsk);

/***************************************************************/
#define VERT_EDGES(chbn, type, mbsk)                             \
  type *pdst = (type *) mlib_ImbgeGetDbtb(dst);                  \
  type *pdst_row, *pdst_row_end;                                 \
  type color_i;                                                  \
  mlib_s32 dst_height = mlib_ImbgeGetHeight(dst);                \
  mlib_s32 dst_width  = mlib_ImbgeGetWidth(dst);                 \
  mlib_s32 dst_stride = mlib_ImbgeGetStride(dst) / sizeof(type); \
  mlib_s32 i, j, l;                                              \
  mlib_s32 embsk, testchbn;                                      \
  mlib_s32 dst_width_t, dst_width_b;                             \
  mlib_d64 *dpdst;                                               \
                                                                 \
  testchbn = 1;                                                  \
  for (l = chbn - 1; l >= 0; l--) {                              \
    if ((mbsk & testchbn) == 0) {                                \
      testchbn <<= 1;                                            \
      continue;                                                  \
    }                                                            \
    testchbn <<= 1;                                              \
    color_i = (type)color[l];                                    \
    for (j = 0; j < dx_l; j++) {                                 \
      for (i = dy_t; i < (dst_height - dy_b); i++) {             \
        pdst[i*dst_stride + l + j*chbn] = color_i;               \
      }                                                          \
    }                                                            \
    for (j = 0; j < dx_r; j++) {                                 \
      for (i = dy_t; i < (dst_height - dy_b); i++) {             \
        pdst[i*dst_stride + l+(dst_width-1 - j)*chbn] = color_i; \
      }                                                          \
    }                                                            \
  }                                                              \
                                                                 \
  dst_width_t = dst_width;                                       \
  dst_width_b = dst_width;                                       \
  if ((dst_width * chbn) == dst_stride) {                        \
    dst_width_t *= dy_t;                                         \
    dst_width_b *= dy_b;                                         \
    dst_stride *= (dst_height - dy_b);                           \
    dst_height = 2;                                              \
    dy_t = ((dy_t == 0) ? 0 : 1);                                \
    dy_b = ((dy_b == 0) ? 0 : 1);                                \
  }

/***************************************************************/
#define HORIZ_EDGES(chbn, type, mbsk)                            \
{                                                                \
  testchbn = 1;                                                  \
  for (l = chbn - 1; l >= 0; l--) {                              \
    if ((mbsk & testchbn) == 0) {                                \
      testchbn <<= 1;                                            \
      continue;                                                  \
    }                                                            \
    testchbn <<= 1;                                              \
    color_i = (type) color[l];                                   \
    for (i = 0; i < dy_t; i++) {                                 \
      for (j = 0; j < dst_width_t; j++) {                        \
        pdst[i * dst_stride + l + j * chbn] = color_i;           \
      }                                                          \
    }                                                            \
    for (i = 0; i < dy_b; i++) {                                 \
      for (j = 0; j < dst_width_b; j++) {                        \
        pdst[(dst_height - 1 - i) * dst_stride + l + j * chbn] = \
          color_i;                                               \
      }                                                          \
    }                                                            \
  }                                                              \
  return;                                                        \
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

  if (dx_l + dx_r > dst_width) {
    dx_l = dst_width;
    dx_r = 0;
  }

  if (dy_t + dy_b > dst_height) {
    dy_t = dst_height;
    dy_b = 0;
  }

  switch (mlib_ImbgeGetType(dst)) {
    cbse MLIB_BIT:
      return mlib_ImbgeConvClebrEdge_Bit(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);

    cbse MLIB_BYTE:
      switch (mlib_ImbgeGetChbnnels(dst)) {

        cbse 1:
          mlib_ImbgeConvClebrEdge_U8_1(dst, dx_l, dx_r, dy_t, dy_b, color);
          brebk;

        cbse 2:
          mlib_ImbgeConvClebrEdge_U8_2(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        cbse 3:
          mlib_ImbgeConvClebrEdge_U8_3(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        cbse 4:
          mlib_ImbgeConvClebrEdge_U8_4(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_SHORT:
    cbse MLIB_USHORT:
      switch (mlib_ImbgeGetChbnnels(dst)) {

        cbse 1:
          mlib_ImbgeConvClebrEdge_S16_1(dst, dx_l, dx_r, dy_t, dy_b, color);
          brebk;

        cbse 2:
          mlib_ImbgeConvClebrEdge_S16_2(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        cbse 3:
          mlib_ImbgeConvClebrEdge_S16_3(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        cbse 4:
          mlib_ImbgeConvClebrEdge_S16_4(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_INT:
      switch (mlib_ImbgeGetChbnnels(dst)) {

        cbse 1:
          mlib_ImbgeConvClebrEdge_S32_1(dst, dx_l, dx_r, dy_t, dy_b, color);
          brebk;

        cbse 2:
          mlib_ImbgeConvClebrEdge_S32_2(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        cbse 3:
          mlib_ImbgeConvClebrEdge_S32_3(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        cbse 4:
          mlib_ImbgeConvClebrEdge_S32_4(dst, dx_l, dx_r, dy_t, dy_b, color, cmbsk);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

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
void mlib_ImbgeConvClebrEdge_U8_1(mlib_imbge     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  const mlib_s32 *color)
{
  mlib_u32 color0 = color[0] & 0xFF;
  mlib_d64 dcolor;

  VERT_EDGES(1, mlib_u8, 1);

  if (dst_width < 16)
    HORIZ_EDGES(1, mlib_u8, 1);

  color0 |= (color0 << 8);
  color0 |= (color0 << 16);
  dcolor = vis_to_double_dup(color0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    embsk = vis_edge8(pdst_row, pdst_row_end);
    vis_pst_8(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_t - 8); j += 8)
      *dpdst++ = dcolor;
    embsk = vis_edge8(dpdst, pdst_row_end);
    vis_pst_8(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    embsk = vis_edge8(pdst_row, pdst_row_end);
    vis_pst_8(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_b - 8); j += 8)
      *dpdst++ = dcolor;
    embsk = vis_edge8(dpdst, pdst_row_end);
    vis_pst_8(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_U8_2(mlib_imbge     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  const mlib_s32 *color,
                                  mlib_s32       cmbsk)
{
  mlib_u32 color0 = color[0] & 0xFF, color1 = color[1] & 0xFF;
  mlib_d64 dcolor0;
  mlib_s32 tmbsk = cmbsk & 3, mbsk1, offset;
  mlib_d64 dcolor;

  VERT_EDGES(2, mlib_u8, cmbsk);

  if (dst_width < 8)
    HORIZ_EDGES(2, mlib_u8, cmbsk);

  tmbsk |= (tmbsk << 2);
  tmbsk |= (tmbsk << 4);
  tmbsk |= (tmbsk << 8);
  color0 = (color0 << 8) | color1;
  color0 |= (color0 << 16);
  dcolor0 = vis_to_double_dup(color0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offset = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_8(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 2 - 8); j += 8)
      vis_pst_8(dcolor, dpdst++, mbsk1);
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk1;
    vis_pst_8(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offset = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_8(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 2 - 8); j += 8)
      vis_pst_8(dcolor, dpdst++, mbsk1);
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk1;
    vis_pst_8(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_U8_3(mlib_imbge     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  const mlib_s32 *color,
                                  mlib_s32       cmbsk)
{
  mlib_u32 color0 = color[0] & 0xFF,
    color1 = color[1] & 0xFF, color2 = color[2] & 0xFF, col;
  mlib_d64 dcolor1, dcolor2, dcolor00, dcolor11, dcolor22;
  mlib_s32 tmbsk = cmbsk & 7, mbsk0, mbsk1, mbsk2, offset;
  mlib_d64 dcolor;

  VERT_EDGES(3, mlib_u8, cmbsk);

  if (dst_width < 16)
    HORIZ_EDGES(3, mlib_u8, cmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  tmbsk |= (tmbsk << 12);
  col = (color0 << 16) | (color1 << 8) | color2;
  color0 = (col << 8) | color0;
  color1 = (color0 << 8) | color1;
  color2 = (color1 << 8) | color2;
  dcolor = vis_to_double(color0, color1);
  dcolor1 = vis_to_double(color2, color0);
  dcolor2 = vis_to_double(color1, color2);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    mbsk2 = (tmbsk >> (9 - ((8 - offset) & 7)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    dcolor22 = vis_fbligndbtb(dcolor2, dcolor);
    dcolor00 = vis_fbligndbtb(dcolor, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor2);
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_8(dcolor22, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 3 - 24); j += 24) {
      vis_pst_8(dcolor00, dpdst, mbsk0);
      vis_pst_8(dcolor11, dpdst + 1, mbsk1);
      vis_pst_8(dcolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_width_t * 3 - 8)) {
      vis_pst_8(dcolor00, dpdst++, mbsk0);

      if (j < (dst_width_t * 3 - 16)) {
        vis_pst_8(dcolor11, dpdst++, mbsk1);
        dcolor00 = dcolor22;
        mbsk0 = mbsk2;
      }
      else {
        dcolor00 = dcolor11;
        mbsk0 = mbsk1;
      }
    }

    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk0;
    vis_pst_8(dcolor00, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    mbsk2 = (tmbsk >> (9 - ((8 - offset) & 7)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    dcolor22 = vis_fbligndbtb(dcolor2, dcolor);
    dcolor00 = vis_fbligndbtb(dcolor, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor2);
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_8(dcolor22, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 3 - 24); j += 24) {
      vis_pst_8(dcolor00, dpdst, mbsk0);
      vis_pst_8(dcolor11, dpdst + 1, mbsk1);
      vis_pst_8(dcolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_width_b * 3 - 8)) {
      vis_pst_8(dcolor00, dpdst++, mbsk0);

      if (j < (dst_width_b * 3 - 16)) {
        vis_pst_8(dcolor11, dpdst++, mbsk1);
        dcolor00 = dcolor22;
        mbsk0 = mbsk2;
      }
      else {
        dcolor00 = dcolor11;
        mbsk0 = mbsk1;
      }
    }

    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk0;
    vis_pst_8(dcolor00, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_U8_4(mlib_imbge     *dst,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r,
                                  mlib_s32       dy_t,
                                  mlib_s32       dy_b,
                                  const mlib_s32 *color,
                                  mlib_s32       cmbsk)
{
  mlib_u32 color0 = color[0] & 0xFF,
    color1 = color[1] & 0xFF, color2 = color[2] & 0xFF, color3 = color[3] & 0xFF;
  mlib_d64 dcolor0;
  mlib_s32 tmbsk = cmbsk & 0xF, mbsk1, offset;
  mlib_d64 dcolor;

  VERT_EDGES(4, mlib_u8, cmbsk);

  if (dst_width < 4)
    HORIZ_EDGES(4, mlib_u8, cmbsk);

  tmbsk |= (tmbsk << 4);
  tmbsk |= (tmbsk << 8);
  color0 = (color0 << 24) | (color1 << 16) | (color2 << 8) | color3;
  dcolor0 = vis_to_double_dup(color0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_8(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 4 - 8); j += 8)
      vis_pst_8(dcolor, dpdst++, mbsk1);
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk1;
    vis_pst_8(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_8(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 4 - 8); j += 8)
      vis_pst_8(dcolor, dpdst++, mbsk1);
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk1;
    vis_pst_8(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S16_1(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color)
{
  mlib_u32 color0 = color[0] & 0xFFFF;
  mlib_d64 dcolor;

  VERT_EDGES(1, mlib_s16, 1);

  if (dst_width < 8)
    HORIZ_EDGES(1, mlib_s16, 1);

  color0 |= (color0 << 16);
  dcolor = vis_to_double_dup(color0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    embsk = vis_edge16(pdst_row, pdst_row_end);
    vis_pst_16(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_t - 4); j += 4)
      *dpdst++ = dcolor;
    embsk = vis_edge16(dpdst, pdst_row_end);
    vis_pst_16(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    embsk = vis_edge16(pdst_row, pdst_row_end);
    vis_pst_16(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_b - 4); j += 4)
      *dpdst++ = dcolor;
    embsk = vis_edge16(dpdst, pdst_row_end);
    vis_pst_16(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S16_2(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color,
                                   mlib_s32       cmbsk)
{
  mlib_u32 color0 = color[0] & 0xFFFF, color1 = color[1] & 0xFFFF;
  mlib_d64 dcolor0;
  mlib_s32 tmbsk = cmbsk & 3, mbsk1, offset;
  mlib_d64 dcolor;

  VERT_EDGES(2, mlib_s16, cmbsk);

  if (dst_width < 4)
    HORIZ_EDGES(2, mlib_s16, cmbsk);

  tmbsk |= (tmbsk << 2);
  tmbsk |= (tmbsk << 4);
  color0 = (color0 << 16) | color1;
  dcolor0 = vis_to_double_dup(color0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offset = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_16(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 2 - 4); j += 4)
      vis_pst_16(dcolor, dpdst++, mbsk1);
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk1;
    vis_pst_16(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offset = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_16(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 2 - 4); j += 4)
      vis_pst_16(dcolor, dpdst++, mbsk1);
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk1;
    vis_pst_16(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S16_3(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color,
                                   mlib_s32       cmbsk)
{
  mlib_u32 color0 = color[0] & 0xFFFF,
    color1 = color[1] & 0xFFFF, color2 = color[2] & 0xFFFF, col0, col1, col2;
  mlib_d64 dcolor1, dcolor2, dcolor00, dcolor11, dcolor22;
  mlib_s32 tmbsk = cmbsk & 7, mbsk0, mbsk1, mbsk2, offset;
  mlib_d64 dcolor;

  VERT_EDGES(3, mlib_s16, cmbsk);

  if (dst_width < 8)
    HORIZ_EDGES(3, mlib_s16, cmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  tmbsk |= (tmbsk << 12);
  col0 = (color0 << 16) | color1;
  col1 = (color2 << 16) | color0;
  col2 = (color1 << 16) | color2;
  dcolor = vis_to_double(col0, col1);
  dcolor1 = vis_to_double(col2, col0);
  dcolor2 = vis_to_double(col1, col2);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    mbsk2 = (tmbsk >> (6 - ((4 - offset) & 3)));
    mbsk0 = mbsk2 >> 2;
    mbsk1 = mbsk0 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    dcolor22 = vis_fbligndbtb(dcolor2, dcolor);
    dcolor00 = vis_fbligndbtb(dcolor, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor2);
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_16(dcolor22, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 3 - 12); j += 12) {
      vis_pst_16(dcolor00, dpdst, mbsk0);
      vis_pst_16(dcolor11, dpdst + 1, mbsk1);
      vis_pst_16(dcolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_width_t * 3 - 4)) {
      vis_pst_16(dcolor00, dpdst++, mbsk0);

      if (j < (dst_width_t * 3 - 8)) {
        vis_pst_16(dcolor11, dpdst++, mbsk1);
        dcolor00 = dcolor22;
        mbsk0 = mbsk2;
      }
      else {
        dcolor00 = dcolor11;
        mbsk0 = mbsk1;
      }
    }

    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk0;
    vis_pst_16(dcolor00, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    mbsk2 = (tmbsk >> (6 - ((4 - offset) & 3)));
    mbsk0 = mbsk2 >> 2;
    mbsk1 = mbsk0 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    dcolor22 = vis_fbligndbtb(dcolor2, dcolor);
    dcolor00 = vis_fbligndbtb(dcolor, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor2);
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_16(dcolor22, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 3 - 12); j += 12) {
      vis_pst_16(dcolor00, dpdst, mbsk0);
      vis_pst_16(dcolor11, dpdst + 1, mbsk1);
      vis_pst_16(dcolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_width_b * 3 - 4)) {
      vis_pst_16(dcolor00, dpdst++, mbsk0);

      if (j < (dst_width_b * 3 - 8)) {
        vis_pst_16(dcolor11, dpdst++, mbsk1);
        dcolor00 = dcolor22;
        mbsk0 = mbsk2;
      }
      else {
        dcolor00 = dcolor11;
        mbsk0 = mbsk1;
      }
    }

    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk0;
    vis_pst_16(dcolor00, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S16_4(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color,
                                   mlib_s32       cmbsk)
{
  mlib_u32 color0 = color[0] & 0xFFFF,
    color1 = color[1] & 0xFFFF, color2 = color[2] & 0xFFFF, color3 = color[3] & 0xFFFF;
  mlib_d64 dcolor0;
  mlib_s32 tmbsk = cmbsk & 0xF, mbsk1, offset;
  mlib_d64 dcolor;

  VERT_EDGES(4, mlib_s16, cmbsk);

  if (dst_width < 4)
    HORIZ_EDGES(4, mlib_s16, cmbsk);

  tmbsk |= (tmbsk << 4);
  color0 = (color0 << 16) | color1;
  color1 = (color2 << 16) | color3;
  dcolor0 = vis_to_double(color0, color1);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_16(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 4 - 4); j += 4)
      vis_pst_16(dcolor, dpdst++, mbsk1);
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk1;
    vis_pst_16(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_16(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 4 - 4); j += 4)
      vis_pst_16(dcolor, dpdst++, mbsk1);
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk1;
    vis_pst_16(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S32_1(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color)
{
  mlib_s32 color0 = color[0];
  mlib_d64 dcolor;

  VERT_EDGES(1, mlib_s32, 1);

  if (dst_width < 8)
    HORIZ_EDGES(1, mlib_s32, 1);

  dcolor = vis_to_double_dup(color0);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    embsk = vis_edge32(pdst_row, pdst_row_end);
    vis_pst_32(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_t - 2); j += 2)
      *dpdst++ = dcolor;
    embsk = vis_edge32(dpdst, pdst_row_end);
    vis_pst_32(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    embsk = vis_edge32(pdst_row, pdst_row_end);
    vis_pst_32(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_b - 2); j += 2)
      *dpdst++ = dcolor;
    embsk = vis_edge32(dpdst, pdst_row_end);
    vis_pst_32(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S32_2(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color,
                                   mlib_s32       cmbsk)
{
  mlib_s32 color0 = color[0], color1 = color[1];
  mlib_d64 dcolor0;
  mlib_s32 tmbsk = cmbsk & 3, mbsk1, offset;
  mlib_d64 dcolor;

  VERT_EDGES(2, mlib_s32, cmbsk);

  if (dst_width < 4)
    HORIZ_EDGES(2, mlib_s32, cmbsk);

  tmbsk |= (tmbsk << 2);
  dcolor0 = vis_to_double(color0, color1);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offset = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_32(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 2 - 2); j += 2)
      vis_pst_32(dcolor, dpdst++, mbsk1);
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk1;
    vis_pst_32(dcolor, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 2 - 1;
    dpdst = (mlib_d64 *) vis_blignbddr(pdst_row, 0);
    offset = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> offset);
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    dcolor = vis_fbligndbtb(dcolor0, dcolor0);
    vis_pst_32(dcolor, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 2 - 2); j += 2)
      vis_pst_32(dcolor, dpdst++, mbsk1);
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk1;
    vis_pst_32(dcolor, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S32_3(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color,
                                   mlib_s32       cmbsk)
{
  mlib_s32 color0 = color[0], color1 = color[1], color2 = color[2];
  mlib_d64 dcolor1, dcolor2, dcolor00, dcolor11, dcolor22;
  mlib_s32 tmbsk = cmbsk & 7, mbsk0, mbsk1, mbsk2, offset;
  mlib_d64 dcolor;

  VERT_EDGES(3, mlib_s32, cmbsk);

  if (dst_width < 8)
    HORIZ_EDGES(3, mlib_s32, cmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  dcolor = vis_to_double(color0, color1);
  dcolor1 = vis_to_double(color2, color0);
  dcolor2 = vis_to_double(color1, color2);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    mbsk2 = (tmbsk >> (3 - ((2 - offset) & 1)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    dcolor22 = vis_fbligndbtb(dcolor2, dcolor);
    dcolor00 = vis_fbligndbtb(dcolor, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor2);
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(dcolor22, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 3 - 6); j += 6) {
      vis_pst_32(dcolor00, dpdst, mbsk0);
      vis_pst_32(dcolor11, dpdst + 1, mbsk1);
      vis_pst_32(dcolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_width_t * 3 - 2)) {
      vis_pst_32(dcolor00, dpdst++, mbsk0);

      if (j < (dst_width_t * 3 - 4)) {
        vis_pst_32(dcolor11, dpdst++, mbsk1);
        dcolor00 = dcolor22;
        mbsk0 = mbsk2;
      }
      else {
        dcolor00 = dcolor11;
        mbsk0 = mbsk1;
      }
    }

    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(dcolor00, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    mbsk2 = (tmbsk >> (3 - ((2 - offset) & 1)));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    dcolor22 = vis_fbligndbtb(dcolor2, dcolor);
    dcolor00 = vis_fbligndbtb(dcolor, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor2);
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk2;

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(dcolor22, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 3 - 6); j += 6) {
      vis_pst_32(dcolor00, dpdst, mbsk0);
      vis_pst_32(dcolor11, dpdst + 1, mbsk1);
      vis_pst_32(dcolor22, dpdst + 2, mbsk2);
      dpdst += 3;
    }

    if (j < (dst_width_b * 3 - 2)) {
      vis_pst_32(dcolor00, dpdst++, mbsk0);

      if (j < (dst_width_b * 3 - 4)) {
        vis_pst_32(dcolor11, dpdst++, mbsk1);
        dcolor00 = dcolor22;
        mbsk0 = mbsk2;
      }
      else {
        dcolor00 = dcolor11;
        mbsk0 = mbsk1;
      }
    }

    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(dcolor00, dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvClebrEdge_S32_4(mlib_imbge     *dst,
                                   mlib_s32       dx_l,
                                   mlib_s32       dx_r,
                                   mlib_s32       dy_t,
                                   mlib_s32       dy_b,
                                   const mlib_s32 *color,
                                   mlib_s32       cmbsk)
{
  mlib_u32 color0 = color[0], color1 = color[1], color2 = color[2], color3 = color[3];
  mlib_d64 dcolor0, dcolor1, dcolor00, dcolor11;
  mlib_s32 tmbsk = cmbsk & 0xF, mbsk0, mbsk1, offset;

  VERT_EDGES(4, mlib_s32, cmbsk);

  if (dst_width < 4)
    HORIZ_EDGES(4, mlib_s32, cmbsk);

  tmbsk |= (tmbsk << 4);
  dcolor0 = vis_to_double(color0, color1);
  dcolor1 = vis_to_double(color2, color3);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride;
    pdst_row_end = pdst_row + dst_width_t * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> (4 - ((2 - offset) & 1)));
    mbsk0 = mbsk1 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    dcolor00 = vis_fbligndbtb(dcolor0, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor0);

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(dcolor11, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_t * 4 - 4); j += 4) {
      vis_pst_32(dcolor00, dpdst, mbsk0);
      vis_pst_32(dcolor11, dpdst + 1, mbsk1);
      dpdst += 2;
    }

    if (j < (dst_width_t * 4 - 2)) {
      vis_pst_32(dcolor00, dpdst++, mbsk0);
      dcolor00 = dcolor11;
      mbsk0 = mbsk1;
    }

    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(dcolor00, dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (dst_height - 1 - i) * dst_stride;
    pdst_row_end = pdst_row + dst_width_b * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    mbsk1 = (tmbsk >> (4 - ((2 - offset) & 1)));
    mbsk0 = mbsk1 >> 2;
    vis_blignbddr((void *)(-(mlib_bddr) pdst_row), 8);
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    dcolor00 = vis_fbligndbtb(dcolor0, dcolor1);
    dcolor11 = vis_fbligndbtb(dcolor1, dcolor0);

    if ((mlib_bddr) pdst_row & 7)
      vis_pst_32(dcolor11, dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (dst_width_b * 4 - 4); j += 4) {
      vis_pst_32(dcolor00, dpdst, mbsk0);
      vis_pst_32(dcolor11, dpdst + 1, mbsk1);
      dpdst += 2;
    }

    if (j < (dst_width_b * 4 - 2)) {
      vis_pst_32(dcolor00, dpdst++, mbsk0);
      dcolor00 = dcolor11;
      mbsk0 = mbsk1;
    }

    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(dcolor00, dpdst, embsk);
  }
}

/***************************************************************/
