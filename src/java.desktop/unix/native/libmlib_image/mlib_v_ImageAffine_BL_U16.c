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
 *      The functions step blong the lines from xLeft to xRight bnd bpply
 *      the bilinebr filtering.
 *
 */

#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_ImbgeColormbp.h"
#include "mlib_ImbgeCopy.h"
#include "mlib_ImbgeAffine.h"
#include "mlib_v_ImbgeFilters.h"
#include "mlib_v_ImbgeChbnnelExtrbct.h"
#include "mlib_v_ImbgeAffine_BL_S16.h"

/*#define MLIB_VIS2*/

/***************************************************************/
#define DTYPE mlib_s16

#define FUN_NAME(CHAN) mlib_ImbgeAffine_u16_##CHAN##_bl

/***************************************************************/
mlib_stbtus FUN_NAME(2ch_nb)(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus FUN_NAME(4ch_nb)(mlib_bffine_pbrbm *pbrbm);

/***************************************************************/
#define XOR_8000(x) x = vis_fxor(x, mbsk_8000)

/***************************************************************/
#ifdef MLIB_VIS2
#define MLIB_WRITE_BMASK(bmbsk) vis_write_bmbsk(bmbsk, 0)
#else
#define MLIB_WRITE_BMASK(bmbsk)
#endif /* MLIB_VIS2 */

/***************************************************************/
#undef  DECLAREVAR
#define DECLAREVAR()                                            \
  DECLAREVAR0();                                                \
  mlib_s32  *wbrp_tbl   = pbrbm -> wbrp_tbl;                    \
  mlib_s32  srcYStride = pbrbm -> srcYStride;                   \
  mlib_u8   *dl;                                                \
  mlib_s32  i, size;                                            \
  mlib_d64  mbsk_8000 = vis_to_double_dup(0x80008000);          \
  mlib_d64  mbsk_7fff = vis_to_double_dup(0x7FFF7FFF);          \
  mlib_d64  dx64, dy64, deltbx, deltby, deltb1_x, deltb1_y;     \
  mlib_d64  s0, s1, s2, s3;                                     \
  mlib_d64  d0, d1, d2, d3, dd

/***************************************************************/

/* brguments (x, y) bre swbpped to prevent overflow */
#define FMUL_16x16(x, y)                        \
  vis_fpbdd16(vis_fmul8sux16(y, x),             \
              vis_fmul8ulx16(y, x))

/***************************************************************/
#define BUF_SIZE  512

/***************************************************************/
#define DOUBLE_4U16(x0, x1, x2, x3)                                 \
  vis_to_double(((((x0) & 0xFFFE) << 15) | (((x1) & 0xFFFE) >> 1)), \
                ((((x2) & 0xFFFE) << 15) | (((x3) & 0xFFFE) >> 1)))

/***************************************************************/
#define BL_SUM()                                                \
  XOR_8000(s0);                                                 \
  XOR_8000(s1);                                                 \
  XOR_8000(s2);                                                 \
  XOR_8000(s3);                                                 \
                                                                \
  deltb1_x = vis_fpsub16(mbsk_7fff, deltbx);                    \
  deltb1_y = vis_fpsub16(mbsk_7fff, deltby);                    \
                                                                \
  d0 = FMUL_16x16(s0, deltb1_x);                                \
  d1 = FMUL_16x16(s1, deltbx);                                  \
  d0 = vis_fpbdd16(d0, d1);                                     \
  d0 = vis_fpbdd16(d0, d0);                                     \
  d0 = FMUL_16x16(d0, deltb1_y);                                \
                                                                \
  d2 = FMUL_16x16(s2, deltb1_x);                                \
  d3 = FMUL_16x16(s3, deltbx);                                  \
  d2 = vis_fpbdd16(d2, d3);                                     \
  d2 = vis_fpbdd16(d2, d2);                                     \
  d2 = FMUL_16x16(d2, deltby);                                  \
                                                                \
  dd = vis_fpbdd16(d0, d2);                                     \
  dd = vis_fpbdd16(dd, dd);                                     \
  XOR_8000(dd);                                                 \
                                                                \
  deltbx = vis_fpbdd16(deltbx, dx64);                           \
  deltby = vis_fpbdd16(deltby, dy64);                           \
  deltbx = vis_fbnd(deltbx, mbsk_7fff);                         \
  deltby = vis_fbnd(deltby, mbsk_7fff)

/***************************************************************/
#define BL_SUM_3CH()                                            \
  XOR_8000(s0);                                                 \
  XOR_8000(s1);                                                 \
  XOR_8000(s2);                                                 \
  XOR_8000(s3);                                                 \
                                                                \
  deltb1_x = vis_fpsub16(mbsk_7fff, deltbx);                    \
  deltb1_y = vis_fpsub16(mbsk_7fff, deltby);                    \
                                                                \
  d0 = FMUL_16x16(s0, deltb1_y);                                \
  d2 = FMUL_16x16(s2, deltby);                                  \
  d0 = vis_fpbdd16(d0, d2);                                     \
  d0 = vis_fpbdd16(d0, d0);                                     \
  d0 = FMUL_16x16(d0, deltb1_x);                                \
                                                                \
  d1 = FMUL_16x16(s1, deltb1_y);                                \
  d3 = FMUL_16x16(s3, deltby);                                  \
  d1 = vis_fpbdd16(d1, d3);                                     \
  d1 = vis_fpbdd16(d1, d1);                                     \
  d1 = FMUL_16x16(d1, deltbx);                                  \
                                                                \
  vis_blignbddr((void*)0, 2);                                   \
  d0 = vis_fbligndbtb(d0, d0);                                  \
  dd = vis_fpbdd16(d0, d1);                                     \
  dd = vis_fpbdd16(dd, dd);                                     \
  XOR_8000(dd);                                                 \
                                                                \
  deltbx = vis_fpbdd16(deltbx, dx64);                           \
  deltby = vis_fpbdd16(deltby, dy64);                           \
  deltbx = vis_fbnd(deltbx, mbsk_7fff);                         \
  deltby = vis_fbnd(deltby, mbsk_7fff)

/***************************************************************/
#define LD_U16(sp, ind) vis_ld_u16(sp + ind)

/***************************************************************/
#ifndef MLIB_VIS2

#define LOAD_1CH()                                              \
  s0 = vis_fbligndbtb(LD_U16(sp3, 0), mbsk_7fff);               \
  s1 = vis_fbligndbtb(LD_U16(sp3, 2), mbsk_7fff);               \
  s2 = vis_fbligndbtb(LD_U16(sp3, srcYStride), mbsk_7fff);      \
  s3 = vis_fbligndbtb(LD_U16(sp3, srcYStride + 2), mbsk_7fff);  \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp2, 0), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp2, 2), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp2, srcYStride), s2);             \
  s3 = vis_fbligndbtb(LD_U16(sp2, srcYStride + 2), s3);         \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp1, 0), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp1, 2), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp1, srcYStride), s2);             \
  s3 = vis_fbligndbtb(LD_U16(sp1, srcYStride + 2), s3);         \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp0, 0), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp0, 2), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp0, srcYStride), s2);             \
  s3 = vis_fbligndbtb(LD_U16(sp0, srcYStride + 2), s3)

#else

#define LOAD_1CH()                                                             \
  s0 = vis_bshuffle(LD_U16(sp0, 0), LD_U16(sp2, 0));                           \
  s1 = vis_bshuffle(LD_U16(sp0, 2), LD_U16(sp2, 2));                           \
  s2 = vis_bshuffle(LD_U16(sp0, srcYStride), LD_U16(sp2, srcYStride));         \
  s3 = vis_bshuffle(LD_U16(sp0, srcYStride + 2), LD_U16(sp2, srcYStride + 2)); \
                                                                               \
  t0 = vis_bshuffle(LD_U16(sp1, 0), LD_U16(sp3, 0));                           \
  t1 = vis_bshuffle(LD_U16(sp1, 2), LD_U16(sp3, 2));                           \
  t2 = vis_bshuffle(LD_U16(sp1, srcYStride), LD_U16(sp3, srcYStride));         \
  t3 = vis_bshuffle(LD_U16(sp1, srcYStride + 2), LD_U16(sp3, srcYStride + 2)); \
                                                                               \
  s0 = vis_bshuffle(s0, t0);                                                   \
  s1 = vis_bshuffle(s1, t1);                                                   \
  s2 = vis_bshuffle(s2, t2);                                                   \
  s3 = vis_bshuffle(s3, t3)

#endif /* MLIB_VIS2 */

/***************************************************************/
#define GET_POINTER(sp)                                                       \
  sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + 2*(X >> MLIB_SHIFT); \
  X += dX;                                                                    \
  Y += dY

/***************************************************************/
#undef  PREPARE_DELTAS
#define PREPARE_DELTAS                                                             \
  if (wbrp_tbl != NULL) {                                                          \
    dX = wbrp_tbl[2*j    ];                                                        \
    dY = wbrp_tbl[2*j + 1];                                                        \
    dx64 = vis_to_double_dup((((dX << 1) & 0xFFFF) << 16) | ((dX << 1) & 0xFFFF)); \
    dy64 = vis_to_double_dup((((dY << 1) & 0xFFFF) << 16) | ((dY << 1) & 0xFFFF)); \
  }

/***************************************************************/
mlib_stbtus FUN_NAME(1ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 off;
  mlib_s32 x0, x1, x2, x3, y0, y1, y2, y3;
#ifdef MLIB_VIS2
  mlib_d64 t0, t1, t2, t3;
  vis_write_bmbsk(0x45CD67EF, 0);
#else
  vis_blignbddr((void*)0, 6);
#endif /* MLIB_VIS2 */

  dx64 = vis_to_double_dup((((dX << 1) & 0xFFFF) << 16) | ((dX << 1) & 0xFFFF));
  dy64 = vis_to_double_dup((((dY << 1) & 0xFFFF) << 16) | ((dY << 1) & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_u8  *sp0, *sp1, *sp2, *sp3;
    mlib_d64 *dp, dmbsk;

    NEW_LINE(1);

    off = (mlib_s32)dl & 7;
    dp = (mlib_d64*)(dl - off);
    off >>= 1;

    x0 = X - off*dX; y0 = Y - off*dY;
    x1 = x0 + dX;    y1 = y0 + dY;
    x2 = x1 + dX;    y2 = y1 + dY;
    x3 = x2 + dX;    y3 = y2 + dY;

    deltbx = DOUBLE_4U16(x0, x1, x2, x3);
    deltby = DOUBLE_4U16(y0, y1, y2, y3);

    if (off) {
      mlib_s32 embsk = vis_edge16((void*)(2*off), (void*)(2*(off + size - 1)));

      off = 4 - off;
      GET_POINTER(sp3);
      sp0 = sp1 = sp2 = sp3;

      if (off > 1 && size > 1) {
        GET_POINTER(sp3);
      }

      if (off > 2) {
        sp2 = sp3;

        if (size > 2) {
          GET_POINTER(sp3);
        }
      }

      LOAD_1CH();
      BL_SUM();

      dmbsk = ((mlib_d64*)mlib_dmbsk_brr)[embsk];
      *dp++ = vis_for (vis_fbnd(dmbsk, dd), vis_fbndnot(dmbsk, dp[0]));

      size -= off;

      if (size < 0) size = 0;
    }

#prbgmb pipeloop(0)
    for (i = 0; i < size/4; i++) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      GET_POINTER(sp2);
      GET_POINTER(sp3);

      LOAD_1CH();
      BL_SUM();

      dp[i] = dd;
    }

    off = size & 3;

    if (off) {
      GET_POINTER(sp0);
      sp1 = sp2 = sp3 = sp0;

      if (off > 1) {
        GET_POINTER(sp1);
      }

      if (off > 2) {
        GET_POINTER(sp2);
      }

      LOAD_1CH();
      BL_SUM();

      dmbsk = ((mlib_d64*)mlib_dmbsk_brr)[(0xF0 >> off) & 0x0F];
      dp[i] = vis_for (vis_fbnd(dmbsk, dd), vis_fbndnot(dmbsk, dp[i]));
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  GET_POINTER
#define GET_POINTER(sp)                                                      \
  sp = *(mlib_f32**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT); \
  X += dX;                                                                   \
  Y += dY

/***************************************************************/
#define LOAD_2CH()                                              \
  s0 = vis_freg_pbir(sp0[0], sp1[0]);                           \
  s1 = vis_freg_pbir(sp0[1], sp1[1]);                           \
  s2 = vis_freg_pbir(sp0[srcYStride], sp1[srcYStride]);         \
  s3 = vis_freg_pbir(sp0[srcYStride + 1], sp1[srcYStride + 1])

/***************************************************************/
#undef  PREPARE_DELTAS
#define PREPARE_DELTAS                                               \
  if (wbrp_tbl != NULL) {                                            \
    dX = wbrp_tbl[2*j    ];                                          \
    dY = wbrp_tbl[2*j + 1];                                          \
    dx64 = vis_to_double_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF)); \
    dy64 = vis_to_double_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF)); \
  }

/***************************************************************/
mlib_stbtus FUN_NAME(2ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 off;
  mlib_s32 x0, x1, y0, y1;

  if (((mlib_s32)lineAddr[0] | (mlib_s32)dstDbtb | srcYStride | dstYStride) & 3) {
    return FUN_NAME(2ch_nb)(pbrbm);
  }

  srcYStride >>= 2;

  dx64 = vis_to_double_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF));
  dy64 = vis_to_double_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_f32 *sp0, *sp1;
    mlib_d64 *dp;

    NEW_LINE(2);

    off = (mlib_s32)dl & 7;
    dp = (mlib_d64*)(dl - off);

    if (off) {
      x0 = X - dX; y0 = Y - dY;
      x1 = X;      y1 = Y;
    } else {
      x0 = X;      y0 = Y;
      x1 = X + dX; y1 = Y + dY;
    }

    deltbx = DOUBLE_4U16(x0, x0, x1, x1);
    deltby = DOUBLE_4U16(y0, y0, y1, y1);

    if (off) {
      GET_POINTER(sp1);
      sp0 = sp1;
      LOAD_2CH();

      BL_SUM();

      ((mlib_f32*)dp)[1] = vis_rebd_lo(dd);
      dp++;
      size--;
    }

#prbgmb pipeloop(0)
    for (i = 0; i < size/2; i++) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      LOAD_2CH();

      BL_SUM();

      *dp++ = dd;
    }

    if (size & 1) {
      GET_POINTER(sp0);
      sp1 = sp0;
      LOAD_2CH();

      BL_SUM();

      ((mlib_f32*)dp)[0] = vis_rebd_hi(dd);
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  GET_POINTER
#define GET_POINTER(sp)                                                       \
  sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + 4*(X >> MLIB_SHIFT); \
  X += dX;                                                                    \
  Y += dY

/***************************************************************/
#ifndef MLIB_VIS2

#define LOAD_2CH_NA()                                           \
  s0 = vis_fbligndbtb(LD_U16(sp1, 2), mbsk_7fff);               \
  s1 = vis_fbligndbtb(LD_U16(sp1, 6), mbsk_7fff);               \
  s2 = vis_fbligndbtb(LD_U16(sp1, srcYStride + 2), mbsk_7fff);  \
  s3 = vis_fbligndbtb(LD_U16(sp1, srcYStride + 6), mbsk_7fff);  \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp1, 0), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp1, 4), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp1, srcYStride), s2);             \
  s3 = vis_fbligndbtb(LD_U16(sp1, srcYStride + 4), s3);         \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp0, 2), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp0, 6), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp0, srcYStride + 2), s2);         \
  s3 = vis_fbligndbtb(LD_U16(sp0, srcYStride + 6), s3);         \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp0, 0), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp0, 4), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp0, srcYStride), s2);             \
  s3 = vis_fbligndbtb(LD_U16(sp0, srcYStride + 4), s3)

#else

#define LOAD_2CH_NA()                                                          \
  s0 = vis_bshuffle(LD_U16(sp0, 0), LD_U16(sp1, 0));                           \
  s1 = vis_bshuffle(LD_U16(sp0, 4), LD_U16(sp1, 4));                           \
  s2 = vis_bshuffle(LD_U16(sp0, srcYStride), LD_U16(sp1, srcYStride));         \
  s3 = vis_bshuffle(LD_U16(sp0, srcYStride + 4), LD_U16(sp1, srcYStride + 4)); \
                                                                               \
  t0 = vis_bshuffle(LD_U16(sp0, 2), LD_U16(sp1, 2));                           \
  t1 = vis_bshuffle(LD_U16(sp0, 6), LD_U16(sp1, 6));                           \
  t2 = vis_bshuffle(LD_U16(sp0, srcYStride + 2), LD_U16(sp1, srcYStride + 2)); \
  t3 = vis_bshuffle(LD_U16(sp0, srcYStride + 6), LD_U16(sp1, srcYStride + 6)); \
                                                                               \
  s0 = vis_bshuffle(s0, t0);                                                   \
  s1 = vis_bshuffle(s1, t1);                                                   \
  s2 = vis_bshuffle(s2, t2);                                                   \
  s3 = vis_bshuffle(s3, t3)

#endif /* MLIB_VIS2 */

/***************************************************************/
mlib_stbtus FUN_NAME(2ch_nb)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 mbx_xsize = pbrbm -> mbx_xsize, bsize;
  mlib_s32 x0, x1, y0, y1;
  mlib_d64 buff[BUF_SIZE], *pbuff = buff;
#ifdef MLIB_VIS2
  mlib_d64 t0, t1, t2, t3;
#endif /* MLIB_VIS2 */

  bsize = (mbx_xsize + 1)/2;

  if (bsize > BUF_SIZE) {
    pbuff = mlib_mblloc(bsize*sizeof(mlib_d64));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  MLIB_WRITE_BMASK(0x45CD67EF);

  dx64 = vis_to_double_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF));
  dy64 = vis_to_double_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_u8 *sp0, *sp1;

#ifndef MLIB_VIS2
    vis_blignbddr((void*)0, 6);
#endif /* MLIB_VIS2 */

    NEW_LINE(2);

    x0 = X;      y0 = Y;
    x1 = X + dX; y1 = Y + dY;

    deltbx = DOUBLE_4U16(x0, x0, x1, x1);
    deltby = DOUBLE_4U16(y0, y0, y1, y1);

#prbgmb pipeloop(0)
    for (i = 0; i < size/2; i++) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      LOAD_2CH_NA();

      BL_SUM();

      pbuff[i] = dd;
    }

    if (size & 1) {
      GET_POINTER(sp0);
      sp1 = sp0;
      LOAD_2CH_NA();

      BL_SUM();

      pbuff[i] = dd;
    }

    mlib_ImbgeCopy_nb((mlib_u8*)pbuff, dl, 4*size);
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  PREPARE_DELTAS
#define PREPARE_DELTAS                                                             \
  if (wbrp_tbl != NULL) {                                                          \
    dX = wbrp_tbl[2*j    ];                                                        \
    dY = wbrp_tbl[2*j + 1];                                                        \
    dX = (dX - (dX >> 31)) &~ 1; /* rounding towbrds ZERO */                       \
    dY = (dY - (dY >> 31)) &~ 1; /* rounding towbrds ZERO */                       \
    dx64 = vis_to_double_dup((((dX >> 1) & 0xFFFF) << 16) | ((dX >> 1) & 0xFFFF)); \
    dy64 = vis_to_double_dup((((dY >> 1) & 0xFFFF) << 16) | ((dY >> 1) & 0xFFFF)); \
  }

/***************************************************************/
mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 mbx_xsize = pbrbm -> mbx_xsize;
  mlib_d64 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsize > BUF_SIZE) {
    pbuff = mlib_mblloc(mbx_xsize*sizeof(mlib_d64));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  dX = (dX - (dX >> 31)) &~ 1; /* rounding towbrds ZERO */
  dY = (dY - (dY >> 31)) &~ 1; /* rounding towbrds ZERO */
  dx64 = vis_to_double_dup((((dX >> 1) & 0xFFFF) << 16) | ((dX >> 1) & 0xFFFF));
  dy64 = vis_to_double_dup((((dY >> 1) & 0xFFFF) << 16) | ((dY >> 1) & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_u8  *sp;
    mlib_d64 *sp0, *sp1;

    NEW_LINE(3);

    deltbx = DOUBLE_4U16(X, X, X, X);
    deltby = DOUBLE_4U16(Y, Y, Y, Y);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + 6*(X >> MLIB_SHIFT) - 2;

      vis_blignbddr(sp, 0);
      sp0 = AL_ADDR(sp, 0);
      s0 = vis_fbligndbtb(sp0[0], sp0[1]);
      s1 = vis_fbligndbtb(sp0[1], sp0[2]);

      vis_blignbddr(sp, srcYStride);
      sp1 = AL_ADDR(sp, srcYStride);
      s2 = vis_fbligndbtb(sp1[0], sp1[1]);
      s3 = vis_fbligndbtb(sp1[1], sp1[2]);

      BL_SUM_3CH();

      pbuff[i] = dd;
      X += dX;
      Y += dY;
    }

    mlib_v_ImbgeChbnnelExtrbct_S16_43L_D1((void *)pbuff, (void *)dl, size);
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();

  if (((mlib_s32)lineAddr[0] | (mlib_s32)dstDbtb | srcYStride | dstYStride) & 7) {
    return FUN_NAME(4ch_nb)(pbrbm);
  }

  srcYStride >>= 3;

  dX = (dX - (dX >> 31)) &~ 1; /* rounding towbrds ZERO */
  dY = (dY - (dY >> 31)) &~ 1; /* rounding towbrds ZERO */
  dx64 = vis_to_double_dup((((dX >> 1) & 0xFFFF) << 16) | ((dX >> 1) & 0xFFFF));
  dy64 = vis_to_double_dup((((dY >> 1) & 0xFFFF) << 16) | ((dY >> 1) & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_d64 *sp;

    NEW_LINE(4);

    deltbx = DOUBLE_4U16(X, X, X, X);
    deltby = DOUBLE_4U16(Y, Y, Y, Y);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      sp = *(mlib_d64**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[srcYStride];
      s3 = sp[srcYStride + 1];

      BL_SUM();

      ((mlib_d64*)dl)[i] = dd;
      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4ch_nb)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 mbx_xsize = pbrbm -> mbx_xsize;
  mlib_d64 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsize > BUF_SIZE) {
    pbuff = mlib_mblloc(mbx_xsize*sizeof(mlib_d64));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  dX = (dX - (dX >> 31)) &~ 1; /* rounding towbrds ZERO */
  dY = (dY - (dY >> 31)) &~ 1; /* rounding towbrds ZERO */
  dx64 = vis_to_double_dup((((dX >> 1) & 0xFFFF) << 16) | ((dX >> 1) & 0xFFFF));
  dy64 = vis_to_double_dup((((dY >> 1) & 0xFFFF) << 16) | ((dY >> 1) & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_u8  *sp;
    mlib_d64 *sp0, *sp1;

    NEW_LINE(4);

    deltbx = DOUBLE_4U16(X, X, X, X);
    deltby = DOUBLE_4U16(Y, Y, Y, Y);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + 8*(X >> MLIB_SHIFT);

      vis_blignbddr(sp, 0);
      sp0 = AL_ADDR(sp, 0);
      s0 = vis_fbligndbtb(sp0[0], sp0[1]);
      s1 = vis_fbligndbtb(sp0[1], sp0[2]);

      vis_blignbddr(sp, srcYStride);
      sp1 = AL_ADDR(sp, srcYStride);
      s2 = vis_fbligndbtb(sp1[0], sp1[1]);
      s3 = vis_fbligndbtb(sp1[1], sp1[2]);

      BL_SUM();

      pbuff[i] = dd;
      X += dX;
      Y += dY;
    }

    mlib_ImbgeCopy_nb((mlib_u8*)pbuff, dl, 8*size);
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
