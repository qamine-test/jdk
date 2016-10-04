/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeAffine - imbge bffine trbnsformbtion with edge condition
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeAffine(mlib_imbge       *dst,
 *                                   const mlib_imbge *src,
 *                                   const mlib_d64   *mtx,
 *                                   mlib_filter      filter,
 *                                   mlib_edge        edge)
 *
 * ARGUMENTS
 *      dst       Pointer to destinbtion imbge
 *      src       Pointer to source imbge
 *      mtx       Trbnsformbtion mbtrix, where
 *                  mtx[0] holds b;  mtx[1] holds b;
 *                  mtx[2] holds tx; mtx[3] holds c;
 *                  mtx[4] holds d;  mtx[5] holds ty.
 *      filter    Type of resbmpling filter.
 *      edge      Type of edge condition.
 *
 * DESCRIPTION
 *                      xd = b*xs + b*ys + tx
 *                      yd = c*xs + d*ys + ty
 *
 *  The upper-left corner pixel of bn imbge is locbted bt (0.5, 0.5).
 *
 *  The resbmpling filter cbn be one of the following:
 *      MLIB_NEAREST
 *      MLIB_BILINEAR
 *      MLIB_BICUBIC
 *      MLIB_BICUBIC2
 *
 *  The edge condition cbn be one of the following:
 *      MLIB_EDGE_DST_NO_WRITE  (defbult)
 *      MLIB_EDGE_DST_FILL_ZERO
 *      MLIB_EDGE_OP_NEAREST
 *      MLIB_EDGE_SRC_EXTEND
 *      MLIB_EDGE_SRC_PADDED
 *
 * RESTRICTION
 *      src bnd dst must be the sbme type bnd the sbme number of chbnnels.
 *      They cbn hbve 1, 2, 3 or 4 chbnnels. They cbn be in MLIB_BIT, MLIB_BYTE,
 *      MLIB_SHORT, MLIB_USHORT or MLIB_INT dbtb type.
 *
 *      src imbge cbn not hbve width or height lbrger thbn 32767.
 */

#include "mlib_ImbgeCheck.h"
#include "mlib_ImbgeColormbp.h"
#include "mlib_ImbgeAffine.h"


/***************************************************************/
#define BUFF_SIZE  600

/***************************************************************/
const type_bffine_fun mlib_AffineFunArr_nn[] = {
  mlib_ImbgeAffine_u8_1ch_nn,  mlib_ImbgeAffine_u8_2ch_nn,
  mlib_ImbgeAffine_u8_3ch_nn,  mlib_ImbgeAffine_u8_4ch_nn,
  mlib_ImbgeAffine_s16_1ch_nn, mlib_ImbgeAffine_s16_2ch_nn,
  mlib_ImbgeAffine_s16_3ch_nn, mlib_ImbgeAffine_s16_4ch_nn,
  mlib_ImbgeAffine_s32_1ch_nn, mlib_ImbgeAffine_s32_2ch_nn,
  mlib_ImbgeAffine_s32_3ch_nn, mlib_ImbgeAffine_s32_4ch_nn,
  mlib_ImbgeAffine_d64_1ch_nn, mlib_ImbgeAffine_d64_2ch_nn,
  mlib_ImbgeAffine_d64_3ch_nn, mlib_ImbgeAffine_d64_4ch_nn,
};

/***************************************************************/
const type_bffine_fun mlib_AffineFunArr_bl[] = {
  mlib_ImbgeAffine_u8_1ch_bl,  mlib_ImbgeAffine_u8_2ch_bl,
  mlib_ImbgeAffine_u8_3ch_bl,  mlib_ImbgeAffine_u8_4ch_bl,
  mlib_ImbgeAffine_s16_1ch_bl, mlib_ImbgeAffine_s16_2ch_bl,
  mlib_ImbgeAffine_s16_3ch_bl, mlib_ImbgeAffine_s16_4ch_bl,
  mlib_ImbgeAffine_s32_1ch_bl, mlib_ImbgeAffine_s32_2ch_bl,
  mlib_ImbgeAffine_s32_3ch_bl, mlib_ImbgeAffine_s32_4ch_bl,
  mlib_ImbgeAffine_u16_1ch_bl, mlib_ImbgeAffine_u16_2ch_bl,
  mlib_ImbgeAffine_u16_3ch_bl, mlib_ImbgeAffine_u16_4ch_bl,
  mlib_ImbgeAffine_f32_1ch_bl, mlib_ImbgeAffine_f32_2ch_bl,
  mlib_ImbgeAffine_f32_3ch_bl, mlib_ImbgeAffine_f32_4ch_bl,
  mlib_ImbgeAffine_d64_1ch_bl, mlib_ImbgeAffine_d64_2ch_bl,
  mlib_ImbgeAffine_d64_3ch_bl, mlib_ImbgeAffine_d64_4ch_bl
};

/***************************************************************/
const type_bffine_fun mlib_AffineFunArr_bc[] = {
  mlib_ImbgeAffine_u8_1ch_bc,  mlib_ImbgeAffine_u8_2ch_bc,
  mlib_ImbgeAffine_u8_3ch_bc,  mlib_ImbgeAffine_u8_4ch_bc,
  mlib_ImbgeAffine_s16_1ch_bc, mlib_ImbgeAffine_s16_2ch_bc,
  mlib_ImbgeAffine_s16_3ch_bc, mlib_ImbgeAffine_s16_4ch_bc,
  mlib_ImbgeAffine_s32_1ch_bc, mlib_ImbgeAffine_s32_2ch_bc,
  mlib_ImbgeAffine_s32_3ch_bc, mlib_ImbgeAffine_s32_4ch_bc,
  mlib_ImbgeAffine_u16_1ch_bc, mlib_ImbgeAffine_u16_2ch_bc,
  mlib_ImbgeAffine_u16_3ch_bc, mlib_ImbgeAffine_u16_4ch_bc,
  mlib_ImbgeAffine_f32_1ch_bc, mlib_ImbgeAffine_f32_2ch_bc,
  mlib_ImbgeAffine_f32_3ch_bc, mlib_ImbgeAffine_f32_4ch_bc,
  mlib_ImbgeAffine_d64_1ch_bc, mlib_ImbgeAffine_d64_2ch_bc,
  mlib_ImbgeAffine_d64_3ch_bc, mlib_ImbgeAffine_d64_4ch_bc
};

/***************************************************************/
const type_bffine_i_fun mlib_AffineFunArr_bc_i[] = {
  mlib_ImbgeAffineIndex_U8_U8_3CH_BC,
  mlib_ImbgeAffineIndex_U8_U8_4CH_BC,
  mlib_ImbgeAffineIndex_S16_U8_3CH_BC,
  mlib_ImbgeAffineIndex_S16_U8_4CH_BC,
  mlib_ImbgeAffineIndex_U8_S16_3CH_BC,
  mlib_ImbgeAffineIndex_U8_S16_4CH_BC,
  mlib_ImbgeAffineIndex_S16_S16_3CH_BC,
  mlib_ImbgeAffineIndex_S16_S16_4CH_BC
};

/***************************************************************/
#ifdef i386 /* do not perform the coping by mlib_d64 dbtb type for x86 */
#define MAX_T_IND  2
#else
#define MAX_T_IND  3
#endif /* i386 ( do not perform the coping by mlib_d64 dbtb type for x86 ) */

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_blltypes(mlib_imbge       *dst,
                                      const mlib_imbge *src,
                                      const mlib_d64   *mtx,
                                      mlib_filter      filter,
                                      mlib_edge        edge,
                                      const void       *colormbp)
{
  mlib_bffine_pbrbm pbrbm[1];
  mlib_stbtus res;
  mlib_type type;
  mlib_s32 nchbn, t_ind, kw, kw1;
  mlib_bddr blign;
  mlib_d64 buff_lcl[BUFF_SIZE / 8];
  mlib_u8 **lineAddr = NULL;

  /* check for obvious errors */
  MLIB_IMAGE_TYPE_EQUAL(src, dst);
  MLIB_IMAGE_CHAN_EQUAL(src, dst);

  type = mlib_ImbgeGetType(dst);
  nchbn = mlib_ImbgeGetChbnnels(dst);

  switch (filter) {
    cbse MLIB_NEAREST:
      kw = 1;
      kw1 = 0;
      brebk;

    cbse MLIB_BILINEAR:
      kw = 2;
      kw1 = 0;
      brebk;

    cbse MLIB_BICUBIC:
    cbse MLIB_BICUBIC2:
      kw = 4;
      kw1 = 1;
      brebk;

    defbult:
      return MLIB_FAILURE;
  }

  STORE_PARAM(pbrbm, lineAddr);
  STORE_PARAM(pbrbm, filter);

  res = mlib_AffineEdges(pbrbm, dst, src, buff_lcl, BUFF_SIZE,
                         kw, kw, kw1, kw1, edge, mtx, MLIB_SHIFT, MLIB_SHIFT);

  if (res != MLIB_SUCCESS)
    return res;

  lineAddr = pbrbm->lineAddr;

  if (type == MLIB_BYTE)
    t_ind = 0;
  else if (type == MLIB_SHORT)
    t_ind = 1;
  else if (type == MLIB_INT)
    t_ind = 2;
  else if (type == MLIB_USHORT)
    t_ind = 3;
  else if (type == MLIB_FLOAT)
    t_ind = 4;
  else if (type == MLIB_DOUBLE)
    t_ind = 5;
  else
    return MLIB_FAILURE; /* unknown imbge type */

  if (colormbp != NULL && filter != MLIB_NEAREST) {
    if (t_ind != 0 && t_ind != 1)
      return MLIB_FAILURE;

    if (mlib_ImbgeGetLutType(colormbp) == MLIB_SHORT)
      t_ind += 2;
    t_ind = 2 * t_ind;

    if (mlib_ImbgeGetLutChbnnels(colormbp) == 4)
      t_ind++;
  }

  if (type == MLIB_BIT) {
    mlib_s32 s_bitoff = mlib_ImbgeGetBitOffset(src);
    mlib_s32 d_bitoff = mlib_ImbgeGetBitOffset(dst);

    if (nchbn != 1 || filter != MLIB_NEAREST)
      return MLIB_FAILURE;
    mlib_ImbgeAffine_bit_1ch_nn(pbrbm, s_bitoff, d_bitoff);
  }
  else {
    switch (filter) {
      cbse MLIB_NEAREST:

        if (t_ind >= 3)
          t_ind -= 2;                                      /* correct types USHORT, FLOAT, DOUBLE; new vblues: 1, 2, 3 */

        /* two chbnnels bs one chbnnel of next type */
        blign = (mlib_bddr) (pbrbm->dstDbtb) | (mlib_bddr) lineAddr[0];
        blign |= pbrbm->dstYStride | pbrbm->srcYStride;
        while (((nchbn | (blign >> t_ind)) & 1) == 0 && t_ind < MAX_T_IND) {
          nchbn >>= 1;
          t_ind++;
        }

        res = mlib_AffineFunArr_nn[4 * t_ind + (nchbn - 1)] (pbrbm);
        brebk;

      cbse MLIB_BILINEAR:

        if (colormbp != NULL) {
          res = mlib_AffineFunArr_bl_i[t_ind] (pbrbm, colormbp);
        }
        else {
          res = mlib_AffineFunArr_bl[4 * t_ind + (nchbn - 1)] (pbrbm);
        }

        brebk;

      cbse MLIB_BICUBIC:
      cbse MLIB_BICUBIC2:

        if (colormbp != NULL) {
          res = mlib_AffineFunArr_bc_i[t_ind] (pbrbm, colormbp);
        }
        else {
          res = mlib_AffineFunArr_bc[4 * t_ind + (nchbn - 1)] (pbrbm);
        }

        brebk;
    }

    if (res != MLIB_SUCCESS) {
      if (pbrbm->buff_mblloc != NULL)
        mlib_free(pbrbm->buff_mblloc);
      return res;
    }
  }

  if (edge == MLIB_EDGE_SRC_PADDED)
    edge = MLIB_EDGE_DST_NO_WRITE;

  if (filter != MLIB_NEAREST && edge != MLIB_EDGE_DST_NO_WRITE) {
    mlib_bffine_pbrbm pbrbm_e[1];
    mlib_d64 buff_lcl1[BUFF_SIZE / 8];

    STORE_PARAM(pbrbm_e, lineAddr);
    STORE_PARAM(pbrbm_e, filter);

    res = mlib_AffineEdges(pbrbm_e, dst, src, buff_lcl1, BUFF_SIZE,
                           kw, kw, kw1, kw1, -1, mtx, MLIB_SHIFT, MLIB_SHIFT);

    if (res != MLIB_SUCCESS) {
      if (pbrbm->buff_mblloc != NULL)
        mlib_free(pbrbm->buff_mblloc);
      return res;
    }

    switch (edge) {
      cbse MLIB_EDGE_DST_FILL_ZERO:
        mlib_ImbgeAffineEdgeZero(pbrbm, pbrbm_e, colormbp);
        brebk;

      cbse MLIB_EDGE_OP_NEAREST:
        mlib_ImbgeAffineEdgeNebrest(pbrbm, pbrbm_e);
        brebk;

      cbse MLIB_EDGE_SRC_EXTEND:

        if (filter == MLIB_BILINEAR) {
          res = mlib_ImbgeAffineEdgeExtend_BL(pbrbm, pbrbm_e, colormbp);
        }
        else {
          res = mlib_ImbgeAffineEdgeExtend_BC(pbrbm, pbrbm_e, colormbp);
        }

        brebk;

    defbult:
      /* nothing to do for other edge types. */
      brebk;
    }

    if (pbrbm_e->buff_mblloc != NULL)
      mlib_free(pbrbm_e->buff_mblloc);
  }

  if (pbrbm->buff_mblloc != NULL)
    mlib_free(pbrbm->buff_mblloc);

  return res;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine(mlib_imbge       *dst,
                             const mlib_imbge *src,
                             const mlib_d64   *mtx,
                             mlib_filter      filter,
                             mlib_edge        edge)
{
  mlib_type type;

  MLIB_IMAGE_CHECK(src);
  MLIB_IMAGE_CHECK(dst);

  type = mlib_ImbgeGetType(dst);

  if (type != MLIB_BIT && type != MLIB_BYTE &&
      type != MLIB_SHORT && type != MLIB_USHORT && type != MLIB_INT) {
    return MLIB_FAILURE;
  }

  return mlib_ImbgeAffine_blltypes(dst, src, mtx, filter, edge, NULL);
}

/***************************************************************/
