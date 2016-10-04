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

/***************************************************************/
/*#define MLIB_VIS2*/

/***************************************************************/
#define DTYPE mlib_u8

#define FUN_NAME(CHAN) mlib_ImbgeAffine_u8_##CHAN##_bl

/***************************************************************/
stbtic mlib_stbtus FUN_NAME(2ch_nb)(mlib_bffine_pbrbm *pbrbm);
stbtic mlib_stbtus FUN_NAME(4ch_nb)(mlib_bffine_pbrbm *pbrbm);

/***************************************************************/
#ifdef MLIB_VIS2
#define MLIB_WRITE_BMASK(bmbsk) vis_write_bmbsk(bmbsk, 0)
#else
#define MLIB_WRITE_BMASK(bmbsk)
#endif /* MLIB_VIS2 */

/***************************************************************/
#define FILTER_BITS  8

/***************************************************************/
#undef  DECLAREVAR
#define DECLAREVAR()                                            \
  DECLAREVAR0();                                                \
  mlib_s32  *wbrp_tbl   = pbrbm -> wbrp_tbl;                    \
  mlib_s32  srcYStride = pbrbm -> srcYStride;                   \
  mlib_u8   *dl;                                                \
  mlib_s32  i, size;                                            \
  mlib_d64  k05 = vis_to_double_dup(0x00080008);                \
  mlib_d64  d0, d1, d2, d3, dd

/***************************************************************/
#define FMUL_16x16(x, y)                                        \
  vis_fpbdd16(vis_fmul8sux16(x, y), vis_fmul8ulx16(x, y))

/***************************************************************/
#define BUF_SIZE  512

/***************************************************************/
const mlib_u32 mlib_fmbsk_brr[] = {
  0x00000000, 0x000000FF, 0x0000FF00, 0x0000FFFF,
  0x00FF0000, 0x00FF00FF, 0x00FFFF00, 0x00FFFFFF,
  0xFF000000, 0xFF0000FF, 0xFF00FF00, 0xFF00FFFF,
  0xFFFF0000, 0xFFFF00FF, 0xFFFFFF00, 0xFFFFFFFF
};

/***************************************************************/
#define DOUBLE_4U16(x0, x1, x2, x3)                             \
  vis_to_double((((x0 & 0xFFFE) << 15) | ((x1 & 0xFFFE) >> 1)), \
                (((x2 & 0xFFFE) << 15) | ((x3 & 0xFFFE) >> 1)))

/***************************************************************/
#define BL_SUM(HL)                                              \
  deltb1_x = vis_fpsub16(mbsk_7fff, deltbx);                    \
  deltb1_y = vis_fpsub16(mbsk_7fff, deltby);                    \
                                                                \
  d0 = vis_fmul8x16(vis_rebd_##HL(s0), deltb1_x);               \
  d1 = vis_fmul8x16(vis_rebd_##HL(s1), deltbx);                 \
  d0 = vis_fpbdd16(d0, d1);                                     \
  d0 = FMUL_16x16(d0, deltb1_y);                                \
  d2 = vis_fmul8x16(vis_rebd_##HL(s2), deltb1_x);               \
  d3 = vis_fmul8x16(vis_rebd_##HL(s3), deltbx);                 \
  d2 = vis_fpbdd16(d2, d3);                                     \
  d2 = FMUL_16x16(d2, deltby);                                  \
  dd = vis_fpbdd16(d0, d2);                                     \
  dd = vis_fpbdd16(dd, k05);                                    \
  df = vis_fpbck16(dd);                                         \
                                                                \
  deltbx = vis_fpbdd16(deltbx, dx64);                           \
  deltby = vis_fpbdd16(deltby, dy64);                           \
  deltbx = vis_fbnd(deltbx, mbsk_7fff);                         \
  deltby = vis_fbnd(deltby, mbsk_7fff)

/***************************************************************/
#define GET_FILTER_XY()                                         \
  mlib_d64 filterx, filtery, filterxy;                          \
  mlib_s32 filterpos;                                           \
  filterpos = (X >> FILTER_SHIFT) & FILTER_MASK;                \
  filterx = *((mlib_d64 *) ((mlib_u8 *) mlib_filters_u8_bl +    \
                                        filterpos));            \
  filterpos = (Y >> FILTER_SHIFT) & FILTER_MASK;                \
  filtery = *((mlib_d64 *) ((mlib_u8 *) mlib_filters_u8_bl +    \
                                filterpos + 8*FILTER_SIZE));    \
  filterxy = FMUL_16x16(filterx, filtery)

/***************************************************************/
#define LD_U8(sp, ind)  vis_rebd_lo(vis_ld_u8(sp + ind))
#define LD_U16(sp, ind) vis_ld_u16(sp + ind)

/***************************************************************/
#define LOAD_1CH()                                                  \
  s0 = vis_fpmerge(LD_U8(sp0, 0), LD_U8(sp2, 0));                   \
  s1 = vis_fpmerge(LD_U8(sp0, 1), LD_U8(sp2, 1));                   \
  s2 = vis_fpmerge(LD_U8(sp0, srcYStride), LD_U8(sp2, srcYStride)); \
  s3 = vis_fpmerge(LD_U8(sp0, srcYStride + 1),                      \
                              LD_U8(sp2, srcYStride + 1));          \
                                                                    \
  t0 = vis_fpmerge(LD_U8(sp1, 0), LD_U8(sp3, 0));                   \
  t1 = vis_fpmerge(LD_U8(sp1, 1), LD_U8(sp3, 1));                   \
  t2 = vis_fpmerge(LD_U8(sp1, srcYStride), LD_U8(sp3, srcYStride)); \
  t3 = vis_fpmerge(LD_U8(sp1, srcYStride + 1),                      \
                              LD_U8(sp3, srcYStride + 1));          \
                                                                    \
  s0 = vis_fpmerge(vis_rebd_lo(s0), vis_rebd_lo(t0));               \
  s1 = vis_fpmerge(vis_rebd_lo(s1), vis_rebd_lo(t1));               \
  s2 = vis_fpmerge(vis_rebd_lo(s2), vis_rebd_lo(t2));               \
  s3 = vis_fpmerge(vis_rebd_lo(s3), vis_rebd_lo(t3))

/***************************************************************/
#define GET_POINTER(sp)                                         \
  sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) +        \
                                (X >> MLIB_SHIFT);              \
  X += dX;                                                      \
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
  mlib_d64 mbsk_7fff = vis_to_double_dup(0x7FFF7FFF);
  mlib_d64 dx64, dy64, deltbx, deltby, deltb1_x, deltb1_y;
  mlib_s32 off, x0, x1, x2, x3, y0, y1, y2, y3;
  mlib_f32 *dp, fmbsk;

  vis_write_gsr((1 << 3) | 7);

  dx64 = vis_to_double_dup((((dX << 1) & 0xFFFF) << 16) | ((dX << 1) & 0xFFFF));
  dy64 = vis_to_double_dup((((dY << 1) & 0xFFFF) << 16) | ((dY << 1) & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_u8  *sp0, *sp1, *sp2, *sp3;
    mlib_d64 s0, s1, s2, s3, t0, t1, t2, t3;
    mlib_f32 df;

    NEW_LINE(1);

    off = (mlib_s32)dl & 3;
    dp = (mlib_f32*)(dl - off);

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
      BL_SUM(lo);

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[embsk];
      *dp++ = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, dp[0]));

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
      BL_SUM(lo);

      dp[i] = df;
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
      BL_SUM(lo);

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[(0xF0 >> off) & 0x0F];
      dp[i] = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, dp[i]));
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  GET_POINTER
#define GET_POINTER(sp)                                         \
  sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) +        \
                        2*(X >> MLIB_SHIFT);                    \
  X += dX;                                                      \
  Y += dY

/***************************************************************/
#ifndef MLIB_VIS2

#define LOAD_2CH()                                              \
  s0 = vis_fbligndbtb(LD_U16(sp1, 0), k05);                     \
  s1 = vis_fbligndbtb(LD_U16(sp1, 2), k05);                     \
  s2 = vis_fbligndbtb(LD_U16(sp1, srcYStride), k05);            \
  s3 = vis_fbligndbtb(LD_U16(sp1, srcYStride + 2), k05);        \
                                                                \
  s0 = vis_fbligndbtb(LD_U16(sp0, 0), s0);                      \
  s1 = vis_fbligndbtb(LD_U16(sp0, 2), s1);                      \
  s2 = vis_fbligndbtb(LD_U16(sp0, srcYStride), s2);             \
  s3 = vis_fbligndbtb(LD_U16(sp0, srcYStride + 2), s3)

#define BL_SUM_2CH() BL_SUM(hi)

#else

#define LOAD_2CH()                                              \
  s0 = vis_bshuffle(LD_U16(sp0, 0), LD_U16(sp1, 0));            \
  s1 = vis_bshuffle(LD_U16(sp0, 2), LD_U16(sp1, 2));            \
  s2 = vis_bshuffle(LD_U16(sp0, srcYStride),                    \
                                LD_U16(sp1, srcYStride));                             \
  s3 = vis_bshuffle(LD_U16(sp0, srcYStride + 2),                \
                                LD_U16(sp1, srcYStride + 2))

#define BL_SUM_2CH() BL_SUM(lo)

#endif /* MLIB_VIS2 */

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
  mlib_d64 mbsk_7fff = vis_to_double_dup(0x7FFF7FFF);
  mlib_d64 dx64, dy64, deltbx, deltby, deltb1_x, deltb1_y;
  mlib_s32 off, x0, x1, y0, y1;

  if (((mlib_s32)lineAddr[0] | (mlib_s32)dstDbtb | srcYStride | dstYStride) & 1) {
    return FUN_NAME(2ch_nb)(pbrbm);
  }

  vis_write_gsr((1 << 3) | 6);
  MLIB_WRITE_BMASK(0x45cd67ef);

  dx64 = vis_to_double_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF));
  dy64 = vis_to_double_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_u8  *sp0, *sp1;
    mlib_d64 s0, s1, s2, s3;
    mlib_f32 *dp, df, fmbsk;

    NEW_LINE(2);

    off = (mlib_s32)dl & 3;
    dp = (mlib_f32*)(dl - off);

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

      BL_SUM_2CH();

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[0x3];
      *dp++ = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, dp[0]));

      size--;
    }

    if (size >= 2) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      LOAD_2CH();

#prbgmb pipeloop(0)
      for (i = 0; i < (size - 2)/2; i++) {
        BL_SUM_2CH();

        GET_POINTER(sp0);
        GET_POINTER(sp1);
        LOAD_2CH();

        *dp++ = df;
      }

      BL_SUM_2CH();
      *dp++ = df;
    }

    if (size & 1) {
      GET_POINTER(sp0);
      sp1 = sp0;
      LOAD_2CH();

      BL_SUM_2CH();

      fmbsk = ((mlib_f32*)mlib_fmbsk_brr)[0x0C];
      *dp = vis_fors(vis_fbnds(fmbsk, df), vis_fbndnots(fmbsk, *dp));
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#ifndef MLIB_VIS2

#define LOAD_2CH_NA()                                           \
  s0 = vis_fpmerge(LD_U8(sp0, 0), LD_U8(sp1, 0));               \
  s1 = vis_fpmerge(LD_U8(sp0, 2), LD_U8(sp1, 2));               \
  s2 = vis_fpmerge(LD_U8(sp0, srcYStride),                      \
                              LD_U8(sp1, srcYStride));                         \
  s3 = vis_fpmerge(LD_U8(sp0, srcYStride + 2),                  \
                              LD_U8(sp1, srcYStride + 2));      \
                                                                \
  t0 = vis_fpmerge(LD_U8(sp0, 1), LD_U8(sp1, 1));               \
  t1 = vis_fpmerge(LD_U8(sp0, 3), LD_U8(sp1, 3));               \
  t2 = vis_fpmerge(LD_U8(sp0, srcYStride + 1),                  \
                              LD_U8(sp1, srcYStride + 1));      \
  t3 = vis_fpmerge(LD_U8(sp0, srcYStride + 3),                  \
                              LD_U8(sp1, srcYStride + 3));      \
                                                                \
  s0 = vis_fpmerge(vis_rebd_lo(s0), vis_rebd_lo(t0));           \
  s1 = vis_fpmerge(vis_rebd_lo(s1), vis_rebd_lo(t1));           \
  s2 = vis_fpmerge(vis_rebd_lo(s2), vis_rebd_lo(t2));           \
  s3 = vis_fpmerge(vis_rebd_lo(s3), vis_rebd_lo(t3))

#define BL_SUM_2CH_NA()  BL_SUM(lo)

#else

#define LOAD_2CH_NA()                                           \
  vis_blignbddr(sp0, 0);                                        \
  spb = AL_ADDR(sp0, 0);                                        \
  s0 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  vis_blignbddr(sp1, 0);                                        \
  spb = AL_ADDR(sp1, 0);                                        \
  s1 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  vis_blignbddr(sp0, srcYStride);                               \
  spb = AL_ADDR(sp0, srcYStride);                               \
  s2 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  vis_blignbddr(sp1, srcYStride);                               \
  spb = AL_ADDR(sp1, srcYStride);                               \
  s3 = vis_fbligndbtb(spb[0], spb[1]);                          \
                                                                \
  s0 = vis_bshuffle(s0, s1);                                    \
  s2 = vis_bshuffle(s2, s3)

#define BL_SUM_2CH_NA()                                         \
  deltb1_x = vis_fpsub16(mbsk_7fff, deltbx);                    \
  deltb1_y = vis_fpsub16(mbsk_7fff, deltby);                    \
                                                                \
  d0 = vis_fmul8x16(vis_rebd_hi(s0), deltb1_x);                 \
  d1 = vis_fmul8x16(vis_rebd_lo(s0), deltbx);                   \
  d0 = vis_fpbdd16(d0, d1);                                     \
  d0 = FMUL_16x16(d0, deltb1_y);                                \
  d2 = vis_fmul8x16(vis_rebd_hi(s2), deltb1_x);                 \
  d3 = vis_fmul8x16(vis_rebd_lo(s2), deltbx);                   \
  d2 = vis_fpbdd16(d2, d3);                                     \
  d2 = FMUL_16x16(d2, deltby);                                  \
  dd = vis_fpbdd16(d0, d2);                                     \
  dd = vis_fpbdd16(dd, k05);                                    \
  df = vis_fpbck16(dd);                                         \
                                                                \
  deltbx = vis_fpbdd16(deltbx, dx64);                           \
  deltby = vis_fpbdd16(deltby, dy64);                           \
  deltbx = vis_fbnd(deltbx, mbsk_7fff);                         \
  deltby = vis_fbnd(deltby, mbsk_7fff)

#endif /* MLIB_VIS2 */

/***************************************************************/
mlib_stbtus FUN_NAME(2ch_nb)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_d64 mbsk_7fff = vis_to_double_dup(0x7FFF7FFF);
  mlib_d64 dx64, dy64, deltbx, deltby, deltb1_x, deltb1_y;
  mlib_s32 mbx_xsize = pbrbm -> mbx_xsize, bsize;
  mlib_s32 x0, x1, y0, y1;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  bsize = (mbx_xsize + 1)/2;

  if (bsize > BUF_SIZE) {
    pbuff = mlib_mblloc(bsize*sizeof(mlib_f32));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  vis_write_gsr((1 << 3) | 6);
  MLIB_WRITE_BMASK(0x018923AB);

  dx64 = vis_to_double_dup(((dX & 0xFFFF) << 16) | (dX & 0xFFFF));
  dy64 = vis_to_double_dup(((dY & 0xFFFF) << 16) | (dY & 0xFFFF));

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_u8  *sp0, *sp1;
    mlib_d64 s0, s1, s2, s3;
#ifndef MLIB_VIS2
    mlib_d64 t0, t1, t2, t3;
#else
    mlib_d64 *spb;
#endif /* MLIB_VIS2 */
    mlib_f32 *dp, df;

    NEW_LINE(2);

    dp = pbuff;

    x0 = X;      y0 = Y;
    x1 = X + dX; y1 = Y + dY;

    deltbx = DOUBLE_4U16(x0, x0, x1, x1);
    deltby = DOUBLE_4U16(y0, y0, y1, y1);

#prbgmb pipeloop(0)
    for (i = 0; i < size/2; i++) {
      GET_POINTER(sp0);
      GET_POINTER(sp1);
      LOAD_2CH_NA();

      BL_SUM_2CH_NA();

      *dp++ = df;
    }

    if (size & 1) {
      GET_POINTER(sp0);
      sp1 = sp0;
      LOAD_2CH_NA();

      BL_SUM_2CH_NA();

      *dp++ = df;
    }

    mlib_ImbgeCopy_nb((mlib_u8*)pbuff, dl, 2*size);
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  PREPARE_DELTAS
#define PREPARE_DELTAS                                          \
  if (wbrp_tbl != NULL) {                                       \
    dX = wbrp_tbl[2*j    ];                                     \
    dY = wbrp_tbl[2*j + 1];                                     \
  }

/***************************************************************/
mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  mlib_s32 mbx_xsize = pbrbm -> mbx_xsize;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsize > BUF_SIZE) {
    pbuff = mlib_mblloc(mbx_xsize*sizeof(mlib_f32));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  vis_write_gsr(3 << 3);

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_d64 *sp0, *sp1, s0, s1;
    mlib_u8  *sp;

    NEW_LINE(3);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      GET_FILTER_XY();

      sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + 3*(X >> MLIB_SHIFT) - 1;

      vis_blignbddr(sp, 0);
      sp0 = AL_ADDR(sp, 0);
      s0 = vis_fbligndbtb(sp0[0], sp0[1]);
      d0 = vis_fmul8x16bu(vis_rebd_hi(s0), vis_rebd_hi(filterxy));
      d1 = vis_fmul8x16bl(vis_rebd_lo(s0), vis_rebd_hi(filterxy));

      vis_blignbddr(sp, srcYStride);
      sp1 = AL_ADDR(sp, srcYStride);
      s1 = vis_fbligndbtb(sp1[0], sp1[1]);
      d2 = vis_fmul8x16bu(vis_rebd_hi(s1), vis_rebd_lo(filterxy));
      d3 = vis_fmul8x16bl(vis_rebd_lo(s1), vis_rebd_lo(filterxy));

      vis_blignbddr((void*)0, 2);
      d0 = vis_fpbdd16(d0, d2);
      dd = vis_fpbdd16(k05, d1);
      dd = vis_fpbdd16(dd, d3);
      d0 = vis_fbligndbtb(d0, d0);
      dd = vis_fpbdd16(dd, d0);

      pbuff[i] = vis_fpbck16(dd);
      X += dX;
      Y += dY;
    }

    mlib_v_ImbgeChbnnelExtrbct_U8_43L_D1((mlib_u8*)pbuff, dl, size);
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#define PROCESS_4CH(s0, s1, s2, s3)                             \
  d0 = vis_fmul8x16bu(s0, vis_rebd_hi(filterxy));               \
  d1 = vis_fmul8x16bl(s1, vis_rebd_hi(filterxy));               \
  d2 = vis_fmul8x16bu(s2, vis_rebd_lo(filterxy));               \
  d3 = vis_fmul8x16bl(s3, vis_rebd_lo(filterxy));               \
                                                                \
  dd = vis_fpbdd16(d0, k05);                                    \
  d1 = vis_fpbdd16(d1, d2);                                     \
  dd = vis_fpbdd16(dd, d3);                                     \
  dd = vis_fpbdd16(dd, d1)

/***************************************************************/
mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();

  if (((mlib_s32)lineAddr[0] | (mlib_s32)dstDbtb | srcYStride | dstYStride) & 3) {
    return FUN_NAME(4ch_nb)(pbrbm);
  }

  vis_write_gsr(3 << 3);

  srcYStride >>= 2;

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_f32 *sp, s0, s1, s2, s3;

    NEW_LINE(4);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      GET_FILTER_XY();

      sp = *(mlib_f32**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[srcYStride];
      s3 = sp[srcYStride + 1];

      PROCESS_4CH(s0, s1, s2, s3);

      ((mlib_f32*)dl)[i] = vis_fpbck16(dd);
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
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsize > BUF_SIZE) {
    pbuff = mlib_mblloc(mbx_xsize*sizeof(mlib_f32));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  vis_write_gsr(3 << 3);

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_d64 *sp0, *sp1, s0, s1;
    mlib_u8  *sp;

    NEW_LINE(4);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      GET_FILTER_XY();

      sp = *(mlib_u8**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + 4*(X >> MLIB_SHIFT);

      vis_blignbddr(sp, 0);
      sp0 = AL_ADDR(sp, 0);
      s0 = vis_fbligndbtb(sp0[0], sp0[1]);

      vis_blignbddr(sp, srcYStride);
      sp1 = AL_ADDR(sp, srcYStride);
      s1 = vis_fbligndbtb(sp1[0], sp1[1]);

      PROCESS_4CH(vis_rebd_hi(s0), vis_rebd_lo(s0), vis_rebd_hi(s1), vis_rebd_lo(s1));

      pbuff[i] = vis_fpbck16(dd);
      X += dX;
      Y += dY;
    }

    mlib_ImbgeCopy_nb((mlib_u8*)pbuff, dl, 4*size);
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#define LUT(x)  plut[x]

mlib_stbtus FUN_NAME(u8_i)(mlib_bffine_pbrbm *pbrbm,
                           const void        *colormbp)
{
  DECLAREVAR();
  mlib_s32 nchbn   = mlib_ImbgeGetLutChbnnels(colormbp);
  mlib_s32 lut_off = mlib_ImbgeGetLutOffset(colormbp);
  mlib_f32 *plut = (mlib_f32*)mlib_ImbgeGetLutNormblTbble(colormbp) - lut_off;
  mlib_s32 mbx_xsize = pbrbm -> mbx_xsize;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  if (mbx_xsize > BUF_SIZE) {
    pbuff = mlib_mblloc(mbx_xsize*sizeof(mlib_f32));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  vis_write_gsr(3 << 3);

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_f32 s0, s1, s2, s3;
    DTYPE    *sp;

    NEW_LINE(1);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      GET_FILTER_XY();

      sp = *(DTYPE**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT);
      s0 = LUT(sp[0]);
      s1 = LUT(sp[1]);
      s2 = LUT(sp[srcYStride]);
      s3 = LUT(sp[srcYStride + 1]);

      PROCESS_4CH(s0, s1, s2, s3);

      pbuff[i] = vis_fpbck16(dd);
      X += dX;
      Y += dY;
    }

    if (nchbn == 3) {
      mlib_ImbgeColorTrue2IndexLine_U8_U8_3_in_4((void*)pbuff, (void*)dl, size, colormbp);
    } else {
      mlib_ImbgeColorTrue2IndexLine_U8_U8_4((void*)pbuff, (void*)dl, size, colormbp);
    }
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  DTYPE
#define DTYPE mlib_s16

mlib_stbtus FUN_NAME(s16_i)(mlib_bffine_pbrbm *pbrbm,
                            const void        *colormbp)
{
  DECLAREVAR();
  mlib_s32 nchbn   = mlib_ImbgeGetLutChbnnels(colormbp);
  mlib_s32 lut_off = mlib_ImbgeGetLutOffset(colormbp);
  mlib_f32 *plut = (mlib_f32*)mlib_ImbgeGetLutNormblTbble(colormbp) - lut_off;
  mlib_s32 mbx_xsize = pbrbm -> mbx_xsize;
  mlib_f32 buff[BUF_SIZE], *pbuff = buff;

  srcYStride /= sizeof(DTYPE);

  if (mbx_xsize > BUF_SIZE) {
    pbuff = mlib_mblloc(mbx_xsize*sizeof(mlib_f32));

    if (pbuff == NULL) return MLIB_FAILURE;
  }

  vis_write_gsr(3 << 3);

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_f32 s0, s1, s2, s3;
    DTYPE    *sp;

    NEW_LINE(1);

#prbgmb pipeloop(0)
    for (i = 0; i < size; i++) {
      GET_FILTER_XY();

      sp = *(DTYPE**)((mlib_u8*)lineAddr + PTR_SHIFT(Y)) + (X >> MLIB_SHIFT);
      s0 = LUT(sp[0]);
      s1 = LUT(sp[1]);
      s2 = LUT(sp[srcYStride]);
      s3 = LUT(sp[srcYStride + 1]);

      PROCESS_4CH(s0, s1, s2, s3);

      pbuff[i] = vis_fpbck16(dd);
      X += dX;
      Y += dY;
    }

    if (nchbn == 3) {
      mlib_ImbgeColorTrue2IndexLine_U8_S16_3_in_4((void*)pbuff, (void*)dl, size, colormbp);
    } else {
      mlib_ImbgeColorTrue2IndexLine_U8_S16_4((void*)pbuff, (void*)dl, size, colormbp);
    }
  }

  if (pbuff != buff) {
    mlib_free(pbuff);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
const type_bffine_i_fun mlib_AffineFunArr_bl_i[] = {
  mlib_ImbgeAffine_u8_u8_i_bl,
  mlib_ImbgeAffine_u8_u8_i_bl,
  mlib_ImbgeAffine_u8_s16_i_bl,
  mlib_ImbgeAffine_u8_s16_i_bl,
  mlib_ImbgeAffine_s16_u8_i_bl,
  mlib_ImbgeAffine_s16_u8_i_bl,
  mlib_ImbgeAffine_s16_s16_i_bl,
  mlib_ImbgeAffine_s16_s16_i_bl
};

/***************************************************************/
