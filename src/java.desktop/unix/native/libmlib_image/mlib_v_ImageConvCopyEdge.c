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
 *      mlib_ImbgeConvCopyEdge  - Copy src edges  to dst edges
 *
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeConvCopyEdge(mlib_imbge       *dst,
 *                                         const mlib_imbge *src,
 *                                         mlib_s32         dx_l,
 *                                         mlib_s32         dx_r,
 *                                         mlib_s32         dy_t,
 *                                         mlib_s32         dy_b,
 *                                         mlib_s32         cmbsk)
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

 *      The unselected chbnnels bre not overwritten.
 *      If src bnd dst hbve just one chbnnel,
 *      cmbsk is ignored.
 */

#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_ImbgeConvEdge.h"

/***************************************************************/
stbtic void mlib_ImbgeConvCopyEdge_U8(mlib_imbge       *dst,
                                      const mlib_imbge *src,
                                      mlib_s32         dx_l,
                                      mlib_s32         dx_r,
                                      mlib_s32         dy_t,
                                      mlib_s32         dy_b,
                                      mlib_s32         cmbsk,
                                      mlib_s32         nchbn);

stbtic void mlib_ImbgeConvCopyEdge_U8_3(mlib_imbge       *dst,
                                        const mlib_imbge *src,
                                        mlib_s32         dx_l,
                                        mlib_s32         dx_r,
                                        mlib_s32         dy_t,
                                        mlib_s32         dy_b,
                                        mlib_s32         cmbsk);

stbtic void mlib_ImbgeConvCopyEdge_S16(mlib_imbge       *dst,
                                       const mlib_imbge *src,
                                       mlib_s32         dx_l,
                                       mlib_s32         dx_r,
                                       mlib_s32         dy_t,
                                       mlib_s32         dy_b,
                                       mlib_s32         cmbsk,
                                       mlib_s32         nchbn);

stbtic void mlib_ImbgeConvCopyEdge_S16_3(mlib_imbge       *dst,
                                         const mlib_imbge *src,
                                         mlib_s32         dx_l,
                                         mlib_s32         dx_r,
                                         mlib_s32         dy_t,
                                         mlib_s32         dy_b,
                                         mlib_s32         cmbsk);

stbtic void mlib_ImbgeConvCopyEdge_S32(mlib_imbge       *dst,
                                       const mlib_imbge *src,
                                       mlib_s32         dx_l,
                                       mlib_s32         dx_r,
                                       mlib_s32         dy_t,
                                       mlib_s32         dy_b,
                                       mlib_s32         cmbsk,
                                       mlib_s32         nchbn);

stbtic void mlib_ImbgeConvCopyEdge_S32_3(mlib_imbge       *dst,
                                         const mlib_imbge *src,
                                         mlib_s32         dx_l,
                                         mlib_s32         dx_r,
                                         mlib_s32         dy_t,
                                         mlib_s32         dy_b,
                                         mlib_s32         cmbsk);

stbtic void mlib_ImbgeConvCopyEdge_S32_4(mlib_imbge       *dst,
                                         const mlib_imbge *src,
                                         mlib_s32         dx_l,
                                         mlib_s32         dx_r,
                                         mlib_s32         dy_t,
                                         mlib_s32         dy_b,
                                         mlib_s32         cmbsk);

/***************************************************************/
#define VERT_EDGES(chbn, type, mbsk)                             \
  type *pdst = (type *) mlib_ImbgeGetDbtb(dst);                  \
  type *psrc = (type *) mlib_ImbgeGetDbtb(src);                  \
  type *pdst_row, *psrc_row, *pdst_row_end;                      \
  mlib_s32 img_height = mlib_ImbgeGetHeight(dst);                \
  mlib_s32 img_width  = mlib_ImbgeGetWidth(dst);                 \
  mlib_s32 dst_stride = mlib_ImbgeGetStride(dst) / sizeof(type); \
  mlib_s32 src_stride = mlib_ImbgeGetStride(src) / sizeof(type); \
  mlib_s32 i, j, l;                                              \
  mlib_s32 embsk, testchbn;                                      \
  mlib_s32 img_width_t, img_width_b;                             \
  mlib_d64 *dpdst, *dpsrc, dbtb0, dbtb1;                         \
                                                                 \
  testchbn = 1;                                                  \
  for (l = chbn - 1; l >= 0; l--) {                              \
    if ((mbsk & testchbn) == 0) {                                \
      testchbn <<= 1;                                            \
      continue;                                                  \
    }                                                            \
    testchbn <<= 1;                                              \
    for (j = 0; j < dx_l; j++) {                                 \
      for (i = dy_t; i < (img_height - dy_b); i++) {             \
        pdst[i*dst_stride + l + j*chbn] =                        \
          psrc[i*src_stride + l + j*chbn];                       \
      }                                                          \
    }                                                            \
    for (j = 0; j < dx_r; j++) {                                 \
      for (i = dy_t; i < (img_height - dy_b); i++) {             \
        pdst[i*dst_stride + l+(img_width-1 - j)*chbn] =          \
        psrc[i*src_stride + l+(img_width-1 - j)*chbn];           \
      }                                                          \
    }                                                            \
  }                                                              \
  img_width_t = img_width;                                       \
  img_width_b = img_width;                                       \
  if (((img_width * chbn) == dst_stride) &&                      \
      ((img_width * chbn) == src_stride)) {                      \
    img_width_t *= dy_t;                                         \
    img_width_b *= dy_b;                                         \
    dst_stride *= (img_height - dy_b);                           \
    src_stride *= (img_height - dy_b);                           \
    img_height = 2;                                              \
    dy_t = ((dy_t == 0) ? 0 : 1);                                \
    dy_b = ((dy_b == 0) ? 0 : 1);                                \
  }

/***************************************************************/
#define HORIZ_EDGES(chbn, type, mbsk) {                         \
    testchbn = 1;                                               \
    for (l = chbn - 1; l >= 0; l--) {                           \
      if ((mbsk & testchbn) == 0) {                             \
        testchbn <<= 1;                                         \
        continue;                                               \
      }                                                         \
      testchbn <<= 1;                                           \
      for (i = 0; i < dy_t; i++) {                              \
        for (j = 0; j < img_width_t; j++) {                     \
          pdst[i*dst_stride + l + j*chbn] =                     \
            psrc[i*src_stride + l + j*chbn];                    \
        }                                                       \
      }                                                         \
      for (i = 0; i < dy_b; i++) {                              \
        for (j = 0; j < img_width_b; j++) {                     \
          pdst[(img_height-1 - i)*dst_stride + l + j*chbn] =    \
          psrc[(img_height-1 - i)*src_stride + l + j*chbn];     \
        }                                                       \
      }                                                         \
    }                                                           \
    return;                                                     \
  }

/***************************************************************/
mlib_stbtus mlib_ImbgeConvCopyEdge(mlib_imbge       *dst,
                                   const mlib_imbge *src,
                                   mlib_s32         dx_l,
                                   mlib_s32         dx_r,
                                   mlib_s32         dy_t,
                                   mlib_s32         dy_b,
                                   mlib_s32         cmbsk)
{
  mlib_s32 img_width = mlib_ImbgeGetWidth(dst);
  mlib_s32 img_height = mlib_ImbgeGetHeight(dst);

  if (dx_l + dx_r > img_width) {
    dx_l = img_width;
    dx_r = 0;
  }

  if (dy_t + dy_b > img_height) {
    dy_t = img_height;
    dy_b = 0;
  }

  switch (mlib_ImbgeGetType(dst)) {
    cbse MLIB_BIT:
      return mlib_ImbgeConvCopyEdge_Bit(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk);

    cbse MLIB_BYTE:
      switch (mlib_ImbgeGetChbnnels(dst)) {

        cbse 1:
          mlib_ImbgeConvCopyEdge_U8(dst, src, dx_l, dx_r, dy_t, dy_b, 1, 1);
          brebk;

        cbse 2:
          mlib_ImbgeConvCopyEdge_U8(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk, 2);
          brebk;

        cbse 3:
          mlib_ImbgeConvCopyEdge_U8_3(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk);
          brebk;

        cbse 4:
          mlib_ImbgeConvCopyEdge_U8(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk, 4);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_SHORT:
    cbse MLIB_USHORT:
      switch (mlib_ImbgeGetChbnnels(dst)) {

        cbse 1:
          mlib_ImbgeConvCopyEdge_S16(dst, src, dx_l, dx_r, dy_t, dy_b, 1, 1);
          brebk;

        cbse 2:
          mlib_ImbgeConvCopyEdge_S16(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk, 2);
          brebk;

        cbse 3:
          mlib_ImbgeConvCopyEdge_S16_3(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk);
          brebk;

        cbse 4:
          mlib_ImbgeConvCopyEdge_S16(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk, 4);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_INT:
    cbse MLIB_FLOAT:
      switch (mlib_ImbgeGetChbnnels(dst)) {

        cbse 1:
          mlib_ImbgeConvCopyEdge_S32(dst, src, dx_l, dx_r, dy_t, dy_b, 1, 1);
          brebk;

        cbse 2:
          mlib_ImbgeConvCopyEdge_S32(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk, 2);
          brebk;

        cbse 3:
          mlib_ImbgeConvCopyEdge_S32_3(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk);
          brebk;

        cbse 4:
          mlib_ImbgeConvCopyEdge_S32_4(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk);
          brebk;

        defbult:
          return MLIB_FAILURE;
      }

      brebk;

    cbse MLIB_DOUBLE:
      return mlib_ImbgeConvCopyEdge_Fp(dst, src, dx_l, dx_r, dy_t, dy_b, cmbsk);

    defbult:
      return MLIB_FAILURE;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
void mlib_ImbgeConvCopyEdge_U8(mlib_imbge       *dst,
                               const mlib_imbge *src,
                               mlib_s32         dx_l,
                               mlib_s32         dx_r,
                               mlib_s32         dy_t,
                               mlib_s32         dy_b,
                               mlib_s32         cmbsk,
                               mlib_s32         nchbn)
{
  mlib_s32 tmbsk = cmbsk & ((1 << nchbn) - 1), mbsk1, offset;
  VERT_EDGES(nchbn, mlib_u8, cmbsk);

  if (img_width < 16 / nchbn)
    HORIZ_EDGES(nchbn, mlib_u8, cmbsk);

  if (nchbn == 1)
    tmbsk = 0xFFFF;
  else if (nchbn == 2) {
    tmbsk |= (tmbsk << 2);
    tmbsk |= (tmbsk << 4);
    tmbsk |= (tmbsk << 8);
  }
  else if (nchbn == 4) {
    tmbsk |= (tmbsk << 4);
    tmbsk |= (tmbsk << 8);
  }

  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride,
      psrc_row = psrc + i * src_stride, pdst_row_end = pdst_row + img_width_t * nchbn - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -offset);
    mbsk1 = (tmbsk >> offset);
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    dbtb0 = dbtb1;
    for (; j < (img_width_t * nchbn - 8); j += 8) {
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
      dbtb0 = dbtb1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk1;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (img_height - 1 - i) * dst_stride;
    psrc_row = psrc + (img_height - 1 - i) * src_stride;
    pdst_row_end = pdst_row + img_width_b * nchbn - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -offset);
    mbsk1 = (tmbsk >> offset);
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    dbtb0 = dbtb1;
    for (; j < (img_width_b * nchbn - 8); j += 8) {
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
      dbtb0 = dbtb1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk1;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvCopyEdge_U8_3(mlib_imbge       *dst,
                                 const mlib_imbge *src,
                                 mlib_s32         dx_l,
                                 mlib_s32         dx_r,
                                 mlib_s32         dy_t,
                                 mlib_s32         dy_b,
                                 mlib_s32         cmbsk)
{
  mlib_s32 tmbsk = cmbsk & 7, mbsk0, mbsk1, mbsk2, offset;

  VERT_EDGES(3, mlib_u8, cmbsk);

  if (img_width < 16)
    HORIZ_EDGES(3, mlib_u8, cmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  tmbsk |= (tmbsk << 12);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride,
      psrc_row = psrc + i * src_stride, pdst_row_end = pdst_row + img_width_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -offset);
    mbsk2 = (tmbsk >> (offset + 1));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk2;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (img_width_t * 3 - 24); j += 24) {
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 1, mbsk1);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 2, mbsk2);
      dbtb0 = dbtb1;
      dpdst += 3;
    }

    if (j < (img_width_t * 3 - 8)) {
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;

      if (j < (img_width_t * 3 - 16)) {
        dbtb1 = *dpsrc++;
        vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
        dbtb0 = dbtb1;
        mbsk0 = mbsk2;
      }
      else {
        mbsk0 = mbsk1;
      }
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk0;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (img_height - 1 - i) * dst_stride;
    psrc_row = psrc + (img_height - 1 - i) * src_stride;
    pdst_row_end = pdst_row + img_width_b * 3 - 1;

    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_u8 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -offset);
    mbsk2 = (tmbsk >> (offset + 1));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge8(pdst_row, pdst_row_end) & mbsk2;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_u8 *) dpdst - pdst_row);
    for (; j < (img_width_b * 3 - 24); j += 24) {
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 1, mbsk1);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 2, mbsk2);
      dbtb0 = dbtb1;
      dpdst += 3;
    }

    if (j < (img_width_b * 3 - 8)) {
      dbtb1 = *dpsrc++;
      vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;

      if (j < (img_width_b * 3 - 16)) {
        dbtb1 = *dpsrc++;
        vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
        dbtb0 = dbtb1;
        mbsk0 = mbsk2;
      }
      else {
        mbsk0 = mbsk1;
      }
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge8(dpdst, pdst_row_end) & mbsk0;
    vis_pst_8(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvCopyEdge_S16(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         cmbsk,
                                mlib_s32         nchbn)
{
  mlib_s32 tmbsk = cmbsk & ((1 << nchbn) - 1), mbsk1, offset;
  VERT_EDGES(nchbn, mlib_s16, cmbsk);

  if (img_width < 16 / nchbn)
    HORIZ_EDGES(nchbn, mlib_s16, cmbsk);

  if (nchbn == 1)
    tmbsk = 0xFFFF;
  else if (nchbn == 2) {
    tmbsk |= (tmbsk << 2);
    tmbsk |= (tmbsk << 4);
  }
  else if (nchbn == 4)
    tmbsk |= (tmbsk << 4);

  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride,
      psrc_row = psrc + i * src_stride, pdst_row_end = pdst_row + img_width_t * nchbn - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 1));
    mbsk1 = (tmbsk >> offset);
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    dbtb0 = dbtb1;
    for (; j < (img_width_t * nchbn - 4); j += 4) {
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
      dbtb0 = dbtb1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk1;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (img_height - 1 - i) * dst_stride;
    psrc_row = psrc + (img_height - 1 - i) * src_stride;
    pdst_row_end = pdst_row + img_width_b * nchbn - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 1));
    mbsk1 = (tmbsk >> offset);
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    dbtb0 = dbtb1;
    for (; j < (img_width_b * nchbn - 4); j += 4) {
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
      dbtb0 = dbtb1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk1;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvCopyEdge_S16_3(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  mlib_s32         cmbsk)
{
  mlib_s32 tmbsk = cmbsk & 7, mbsk0, mbsk1, mbsk2, offset;

  VERT_EDGES(3, mlib_s16, cmbsk);

  if (img_width < 16)
    HORIZ_EDGES(3, mlib_s16, cmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  tmbsk |= (tmbsk << 12);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride,
      psrc_row = psrc + i * src_stride, pdst_row_end = pdst_row + img_width_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 1));
    mbsk2 = (tmbsk >> (offset + 2));
    mbsk0 = mbsk2 >> 2;
    mbsk1 = mbsk0 >> 2;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk2;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (img_width_t * 3 - 12); j += 12) {
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 1, mbsk1);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 2, mbsk2);
      dbtb0 = dbtb1;
      dpdst += 3;
    }

    if (j < (img_width_t * 3 - 4)) {
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;

      if (j < (img_width_t * 3 - 8)) {
        dbtb1 = *dpsrc++;
        vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
        dbtb0 = dbtb1;
        mbsk0 = mbsk2;
      }
      else {
        mbsk0 = mbsk1;
      }
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk0;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (img_height - 1 - i) * dst_stride;
    psrc_row = psrc + (img_height - 1 - i) * src_stride;
    pdst_row_end = pdst_row + img_width_b * 3 - 1;

    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s16 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 1));
    mbsk2 = (tmbsk >> (offset + 2));
    mbsk0 = mbsk2 >> 2;
    mbsk1 = mbsk0 >> 2;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge16(pdst_row, pdst_row_end) & mbsk2;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_s16 *) dpdst - pdst_row);
    for (; j < (img_width_b * 3 - 12); j += 12) {
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 1, mbsk1);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 2, mbsk2);
      dbtb0 = dbtb1;
      dpdst += 3;
    }

    if (j < (img_width_b * 3 - 4)) {
      dbtb1 = *dpsrc++;
      vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;

      if (j < (img_width_b * 3 - 8)) {
        dbtb1 = *dpsrc++;
        vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
        dbtb0 = dbtb1;
        mbsk0 = mbsk2;
      }
      else {
        mbsk0 = mbsk1;
      }
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge16(dpdst, pdst_row_end) & mbsk0;
    vis_pst_16(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvCopyEdge_S32(mlib_imbge       *dst,
                                const mlib_imbge *src,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         cmbsk,
                                mlib_s32         nchbn)
{
  mlib_s32 tmbsk = cmbsk & ((1 << nchbn) - 1), mbsk1, offset;
  VERT_EDGES(nchbn, mlib_s32, cmbsk);

  if (img_width < 16 / nchbn)
    HORIZ_EDGES(nchbn, mlib_s32, cmbsk);

  if (nchbn == 1)
    tmbsk = 0xFFFF;
  else if (nchbn == 2) {
    tmbsk |= (tmbsk << 2);
    tmbsk |= (tmbsk << 4);
  }

  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride,
      psrc_row = psrc + i * src_stride, pdst_row_end = pdst_row + img_width_t * nchbn - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 2));
    mbsk1 = (tmbsk >> offset);
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    dbtb0 = dbtb1;
    for (; j < (img_width_t * nchbn - 2); j += 2) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
      dbtb0 = dbtb1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk1;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (img_height - 1 - i) * dst_stride;
    psrc_row = psrc + (img_height - 1 - i) * src_stride;
    pdst_row_end = pdst_row + img_width_b * nchbn - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 2));
    mbsk1 = (tmbsk >> offset);
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    dbtb0 = dbtb1;
    for (; j < (img_width_b * nchbn - 2); j += 2) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
      dbtb0 = dbtb1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk1;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvCopyEdge_S32_3(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  mlib_s32         cmbsk)
{
  mlib_s32 tmbsk = cmbsk & 7, mbsk0, mbsk1, mbsk2, offset;

  VERT_EDGES(3, mlib_s32, cmbsk);

  if (img_width < 16)
    HORIZ_EDGES(3, mlib_s32, cmbsk);

  tmbsk |= (tmbsk << 3);
  tmbsk |= (tmbsk << 6);
  tmbsk |= (tmbsk << 12);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride,
      psrc_row = psrc + i * src_stride, pdst_row_end = pdst_row + img_width_t * 3 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 2));
    mbsk2 = (tmbsk >> (offset + 1));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk2;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (img_width_t * 3 - 6); j += 6) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 1, mbsk1);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 2, mbsk2);
      dbtb0 = dbtb1;
      dpdst += 3;
    }

    if (j < (img_width_t * 3 - 2)) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;

      if (j < (img_width_t * 3 - 4)) {
        dbtb1 = *dpsrc++;
        vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
        dbtb0 = dbtb1;
        mbsk0 = mbsk2;
      }
      else {
        mbsk0 = mbsk1;
      }
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (img_height - 1 - i) * dst_stride;
    psrc_row = psrc + (img_height - 1 - i) * src_stride;
    pdst_row_end = pdst_row + img_width_b * 3 - 1;

    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 2));
    mbsk2 = (tmbsk >> (offset + 1));
    mbsk0 = mbsk2 >> 1;
    mbsk1 = mbsk0 >> 1;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk2;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (img_width_b * 3 - 6); j += 6) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 1, mbsk1);
      dbtb0 = dbtb1;
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst + 2, mbsk2);
      dbtb0 = dbtb1;
      dpdst += 3;
    }

    if (j < (img_width_b * 3 - 2)) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;

      if (j < (img_width_b * 3 - 4)) {
        dbtb1 = *dpsrc++;
        vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk1);
        dbtb0 = dbtb1;
        mbsk0 = mbsk2;
      }
      else {
        mbsk0 = mbsk1;
      }
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }
}

/***************************************************************/
void mlib_ImbgeConvCopyEdge_S32_4(mlib_imbge       *dst,
                                  const mlib_imbge *src,
                                  mlib_s32         dx_l,
                                  mlib_s32         dx_r,
                                  mlib_s32         dy_t,
                                  mlib_s32         dy_b,
                                  mlib_s32         cmbsk)
{
  mlib_s32 tmbsk = cmbsk & 15, mbsk0, mbsk1, offset;

  VERT_EDGES(4, mlib_s32, cmbsk);

  if (img_width < 16)
    HORIZ_EDGES(4, mlib_s32, cmbsk);

  tmbsk |= (tmbsk << 4);
  tmbsk |= (tmbsk << 8);
  for (i = 0; i < dy_t; i++) {
    pdst_row = pdst + i * dst_stride,
      psrc_row = psrc + i * src_stride, pdst_row_end = pdst_row + img_width_t * 4 - 1;
    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 2));
    mbsk1 = (tmbsk >> (offset + 2));
    mbsk0 = mbsk1 >> 2;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (img_width_t * 4 - 4); j += 4) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb1, dbtb0), dpdst + 1, mbsk1);
      dpdst += 2;
    }

    if (j < (img_width_t * 4 - 2)) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;
      mbsk0 = mbsk1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }

  for (i = 0; i < dy_b; i++) {
    pdst_row = pdst + (img_height - 1 - i) * dst_stride;
    psrc_row = psrc + (img_height - 1 - i) * src_stride;
    pdst_row_end = pdst_row + img_width_b * 4 - 1;

    dpdst = (mlib_d64 *) ((mlib_bddr) pdst_row & ~7);
    offset = pdst_row - (mlib_s32 *) dpdst;
    dpsrc = (mlib_d64 *) vis_blignbddr(psrc_row, -(offset << 2));
    mbsk1 = (tmbsk >> (offset + 2));
    mbsk0 = mbsk1 >> 2;
    dbtb0 = *dpsrc++;
    dbtb1 = *dpsrc++;
    embsk = vis_edge32(pdst_row, pdst_row_end) & mbsk1;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, embsk);
    dbtb0 = dbtb1;
    j = (mlib_s32) ((mlib_s32 *) dpdst - pdst_row);
    for (; j < (img_width_b * 4 - 4); j += 4) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, mbsk0);
      dbtb0 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb1, dbtb0), dpdst + 1, mbsk1);
      dpdst += 2;
    }

    if (j < (img_width_b * 4 - 2)) {
      dbtb1 = *dpsrc++;
      vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst++, mbsk0);
      dbtb0 = dbtb1;
      mbsk0 = mbsk1;
    }

    dbtb1 = *dpsrc++;
    embsk = vis_edge32(dpdst, pdst_row_end) & mbsk0;
    vis_pst_32(vis_fbligndbtb(dbtb0, dbtb1), dpdst, embsk);
  }
}

/***************************************************************/
