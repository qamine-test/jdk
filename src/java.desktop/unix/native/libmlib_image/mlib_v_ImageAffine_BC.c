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
 *      the bicubic filtering.
 *
 */

#include "vis_proto.h"
#include "mlib_ImbgeAffine.h"
#include "mlib_v_ImbgeFilters.h"

/*#define MLIB_VIS2*/

/***************************************************************/
#define DTYPE  mlib_u8

#define FILTER_BITS  8

/***************************************************************/
#ifdef MLIB_VIS2
#define MLIB_WRITE_BMASK(bmbsk) vis_write_bmbsk(bmbsk, 0)
#else
#define MLIB_WRITE_BMASK(bmbsk)
#endif /* MLIB_VIS2 */

/***************************************************************/
#define sPtr srcPixelPtr

/***************************************************************/
#define NEXT_PIXEL_1BC_U8()                                     \
  xSrc = (X>>MLIB_SHIFT)-1;                                     \
  ySrc = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + xSrc

/***************************************************************/
#ifndef MLIB_VIS2

#define ALIGN_ADDR(db, dp)                                      \
  db = vis_blignbddr(dp, 0)

#else

#define ALIGN_ADDR(db, dp)                                      \
  vis_blignbddr(dp, 0);                                         \
  db = (mlib_d64*)(((mlib_bddr)(dp)) &~ 7)

#endif /* MLIB_VIS2 */

/***************************************************************/
#define LOAD_BC_U8_1CH_1PIXEL(mlib_filters_u8)                         \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  filterposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFilter = *((mlib_d64 *) ((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  filterposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xFilter = *((mlib_d64 *)((mlib_u8 *)mlib_filters_u8 + filterposx));  \
  X += dX;                                                             \
  Y += dY

/***************************************************************/
#ifndef MLIB_VIS2

#define SUM_4x16(v1, v3)                                        \
  vis_blignbddr((void*)2, 0);                                   \
  v0 = vis_fbligndbtb(v3, v3);                                  \
  v2 = vis_fpbdd16(v3, v0);                                     \
  v1 = vis_write_lo(v1, vis_fpbdd16s(vis_rebd_hi(v2), vis_rebd_lo(v2)))

#else

#define SUM_4x16(v1, v3)                                              \
  v2 = vis_freg_pbir(vis_fpbdd16s(vis_rebd_hi(v3), vis_rebd_lo(v3)),  \
                     vis_fpbdd16s(vis_rebd_hi(v3), vis_rebd_lo(v3))); \
  v3 = vis_bshuffle(v2, v2);                                          \
  v1 = vis_write_lo(v1, vis_fpbdd16s(vis_rebd_hi(v3), vis_rebd_lo(v3)))

#endif /* MLIB_VIS2 */

/***************************************************************/
#define RESULT_1BC_U8_1PIXEL(ind)                                    \
  v0 = vis_fmul8x16bu(vis_rebd_hi(row0##ind), vis_rebd_hi(yFilter)); \
  v1 = vis_fmul8x16bl(vis_rebd_hi(row1##ind), vis_rebd_hi(yFilter)); \
  sum = vis_fpbdd16(v0, v1);                                         \
  v2 = vis_fmul8x16bu(vis_rebd_hi(row2##ind), vis_rebd_lo(yFilter)); \
  sum = vis_fpbdd16(sum, v2);                                        \
  v3 = vis_fmul8x16bl(vis_rebd_hi(row3##ind), vis_rebd_lo(yFilter)); \
  sum = vis_fpbdd16(sum, v3);                                        \
  v0 = vis_fmul8sux16(sum, xFilter);                                 \
  v1 = vis_fmul8ulx16(sum, xFilter);                                 \
  v3 = vis_fpbdd16(v1, v0);                                          \
  SUM_4x16(v1, v3);                                                  \
  res = vis_write_lo(res, vis_fpbck16(v1))

/***************************************************************/
#define BC_U8_1CH(index, ind1, ind2, mlib_filters_u8)                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  v0 = vis_fmul8x16bu(vis_rebd_hi(row0##ind1), vis_rebd_hi(yFilter));  \
  filterposy = (Y >> FILTER_SHIFT);                                    \
  dbtb1 = dpSrc[1];                                                    \
  v1 = vis_fmul8x16bl(vis_rebd_hi(row1##ind1), vis_rebd_hi(yFilter));  \
  row0##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  filterposx = (X >> FILTER_SHIFT);                                    \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  sum = vis_fpbdd16(v0, v1);                                           \
  dbtb0 = dpSrc[0];                                                    \
  v2 = vis_fmul8x16bu(vis_rebd_hi(row2##ind1), vis_rebd_lo(yFilter));  \
  X += dX;                                                             \
  dbtb1 = dpSrc[1];                                                    \
  row1##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  Y += dY;                                                             \
  sum = vis_fpbdd16(sum, v2);                                          \
  xSrc = (X>>MLIB_SHIFT)-1;                                            \
  v3 = vis_fmul8x16bl(vis_rebd_hi(row3##ind1), vis_rebd_lo(yFilter));  \
  dbtb0 = dpSrc[0];                                                    \
  ySrc = (Y>>MLIB_SHIFT)-1;                                            \
  sum = vis_fpbdd16(sum, v3);                                          \
  dbtb1 = dpSrc[1];                                                    \
  filterposy &= FILTER_MASK;                                           \
  v0 = vis_fmul8sux16(sum, xFilter);                                   \
  row2##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  sPtr += srcYStride;                                                  \
  v1 = vis_fmul8ulx16(sum, xFilter);                                   \
  filterposx &= FILTER_MASK;                                           \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  d##index = vis_fpbdd16(v0, v1);                                      \
  dbtb1 = dpSrc[1];                                                    \
  row3##ind2 = vis_fbligndbtb(dbtb0, dbtb1);                           \
  yFilter = *((mlib_d64 *) ((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  xFilter = *((mlib_d64 *)((mlib_u8 *)mlib_filters_u8 + filterposx));  \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + xSrc

/***************************************************************/
#ifndef MLIB_VIS2

#define FADD_1BC_U8()                                           \
  p0 = vis_fpbdd16s(vis_rebd_hi(d0), vis_rebd_lo(d0));          \
  p1 = vis_fpbdd16s(vis_rebd_hi(d1), vis_rebd_lo(d1));          \
  p2 = vis_fpbdd16s(vis_rebd_hi(d2), vis_rebd_lo(d2));          \
  p3 = vis_fpbdd16s(vis_rebd_hi(d3), vis_rebd_lo(d3));          \
  m02 = vis_fpmerge(p0, p2);                                    \
  m13 = vis_fpmerge(p1, p3);                                    \
  m0213 = vis_fpmerge(vis_rebd_hi(m02), vis_rebd_hi(m13));      \
  e0 = vis_fpmerge(vis_rebd_hi(m0213), vis_rebd_lo(m0213));     \
  m0213 = vis_fpmerge(vis_rebd_lo(m02), vis_rebd_lo(m13));      \
  e1 = vis_fpmerge(vis_rebd_hi(m0213), vis_rebd_lo(m0213));     \
  res = vis_fpbdd16(e0, e1)

#else

#define FADD_1BC_U8()                                                 \
  v0 = vis_freg_pbir(vis_fpbdd16s(vis_rebd_hi(d0), vis_rebd_lo(d0)),  \
                     vis_fpbdd16s(vis_rebd_hi(d1), vis_rebd_lo(d1))); \
  v1 = vis_freg_pbir(vis_fpbdd16s(vis_rebd_hi(d2), vis_rebd_lo(d2)),  \
                     vis_fpbdd16s(vis_rebd_hi(d3), vis_rebd_lo(d3))); \
  v2 = vis_bshuffle(v0, v0);                                          \
  v3 = vis_bshuffle(v1, v1);                                          \
  res = vis_freg_pbir(vis_fpbdd16s(vis_rebd_hi(v2), vis_rebd_lo(v2)), \
                      vis_fpbdd16s(vis_rebd_hi(v3), vis_rebd_lo(v3)))

#endif /* MLIB_VIS2 */

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_u8_1ch_bc (mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  mlib_s32  filterposx, filterposy;
  mlib_d64  dbtb0, dbtb1;
  mlib_d64  sum;
  mlib_d64  row00, row10, row20, row30;
  mlib_d64  row01, row11, row21, row31;
  mlib_d64  xFilter, yFilter;
  mlib_d64  v0, v1, v2, v3;
  mlib_d64  d0, d1, d2, d3;
#ifndef MLIB_VIS2
  mlib_f32  p0, p1, p2, p3;
  mlib_d64  e0, e1;
  mlib_d64  m02, m13, m0213;
#endif /* MLIB_VIS2 */
  mlib_d64  *dpSrc;
  mlib_s32  blign, cols, i;
  mlib_d64  res;
  const mlib_s16 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = mlib_filters_u8_bc;
  } else {
    mlib_filters_tbble = mlib_filters_u8_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {

    vis_write_gsr(3 << 3);
    MLIB_WRITE_BMASK(0x0145ABEF);

    CLIP(1);

    cols = xRight - xLeft + 1;
    blign = (4 - ((mlib_bddr)dstPixelPtr) & 3) & 3;
    blign = (cols < blign)? cols : blign;

    for (i = 0; i < blign; i++) {
      NEXT_PIXEL_1BC_U8();
      LOAD_BC_U8_1CH_1PIXEL(mlib_filters_tbble);
      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(res, dstPixelPtr++);
    }

    if (i <= cols - 10) {

      NEXT_PIXEL_1BC_U8();
      LOAD_BC_U8_1CH_1PIXEL(mlib_filters_tbble);

      NEXT_PIXEL_1BC_U8();

      BC_U8_1CH(0, 0, 1, mlib_filters_tbble);
      BC_U8_1CH(1, 1, 0, mlib_filters_tbble);
      BC_U8_1CH(2, 0, 1, mlib_filters_tbble);
      BC_U8_1CH(3, 1, 0, mlib_filters_tbble);

      FADD_1BC_U8();

      BC_U8_1CH(0, 0, 1, mlib_filters_tbble);
      BC_U8_1CH(1, 1, 0, mlib_filters_tbble);
      BC_U8_1CH(2, 0, 1, mlib_filters_tbble);
      BC_U8_1CH(3, 1, 0, mlib_filters_tbble);

#prbgmb pipeloop(0)
      for (; i <= cols - 14; i+=4) {
        *(mlib_f32*)dstPixelPtr = vis_fpbck16(res);
        FADD_1BC_U8();
        BC_U8_1CH(0, 0, 1, mlib_filters_tbble);
        BC_U8_1CH(1, 1, 0, mlib_filters_tbble);
        BC_U8_1CH(2, 0, 1, mlib_filters_tbble);
        BC_U8_1CH(3, 1, 0, mlib_filters_tbble);
        dstPixelPtr += 4;
      }

      *(mlib_f32*)dstPixelPtr = vis_fpbck16(res);
      dstPixelPtr += 4;
      FADD_1BC_U8();
      *(mlib_f32*)dstPixelPtr = vis_fpbck16(res);
      dstPixelPtr += 4;

      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(res, dstPixelPtr++);

      LOAD_BC_U8_1CH_1PIXEL(mlib_filters_tbble);
      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(res, dstPixelPtr++);
      i += 10;
    }

    for (; i < cols; i++) {
      NEXT_PIXEL_1BC_U8();
      LOAD_BC_U8_1CH_1PIXEL(mlib_filters_tbble);
      RESULT_1BC_U8_1PIXEL(0);
      vis_st_u8(res, dstPixelPtr++);
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#define FADD_2BC_U8()                                           \
  d0 = vis_fpbdd16(d00, d10);                                   \
  d1 = vis_fpbdd16(d01, d11);                                   \
  d2 = vis_fpbdd16(d02, d12);                                   \
  d3 = vis_fpbdd16(d03, d13);                                   \
  p0 = vis_fpbdd16s(vis_rebd_hi(d0), vis_rebd_lo(d0));          \
  p1 = vis_fpbdd16s(vis_rebd_hi(d1), vis_rebd_lo(d1));          \
  p2 = vis_fpbdd16s(vis_rebd_hi(d2), vis_rebd_lo(d2));          \
  p3 = vis_fpbdd16s(vis_rebd_hi(d3), vis_rebd_lo(d3));          \
  e0 = vis_freg_pbir(p0, p1);                                   \
  e1 = vis_freg_pbir(p2, p3);                                   \
  res = vis_fpbck16_pbir(e0, e1)

/***************************************************************/
#define LOAD_BC_U8_2CH_1PIXEL(mlib_filters_u8)                         \
  filterposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFilter = *((mlib_d64 *) ((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  filterposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xFilter = *((mlib_d64 *)((mlib_u8 *)mlib_filters_u8 + filterposx));  \
  X += dX;                                                             \
  Y += dY;                                                             \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row0 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row1 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row2 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  row3 = vis_fbligndbtb(dbtb0, dbtb1)

/***************************************************************/
#define NEXT_PIXEL_2BC_U8()                                     \
  xSrc = (X>>MLIB_SHIFT)-1;                                     \
  ySrc = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + (xSrc<<1)

/***************************************************************/
#define RESULT_2BC_U8_1PIXEL()                                   \
  v00 = vis_fmul8x16bu(vis_rebd_hi(row0), vis_rebd_hi(yFilter)); \
  dr = vis_fpmerge(vis_rebd_hi(xFilter), vis_rebd_lo(xFilter));  \
  v01 = vis_fmul8x16bu(vis_rebd_lo(row0), vis_rebd_hi(yFilter)); \
  dr = vis_fpmerge(vis_rebd_hi(dr), vis_rebd_lo(dr));            \
  v10 = vis_fmul8x16bl(vis_rebd_hi(row1), vis_rebd_hi(yFilter)); \
  dr1 = vis_fpmerge(vis_rebd_lo(dr), vis_rebd_lo(dr));           \
  v11 = vis_fmul8x16bl(vis_rebd_lo(row1), vis_rebd_hi(yFilter)); \
  dr = vis_fpmerge(vis_rebd_hi(dr), vis_rebd_hi(dr));            \
  v20 = vis_fmul8x16bu(vis_rebd_hi(row2), vis_rebd_lo(yFilter)); \
  xFilter0 = vis_fpmerge(vis_rebd_hi(dr), vis_rebd_hi(dr1));     \
  v21 = vis_fmul8x16bu(vis_rebd_lo(row2), vis_rebd_lo(yFilter)); \
  xFilter1 = vis_fpmerge(vis_rebd_lo(dr), vis_rebd_lo(dr1));     \
  v30 = vis_fmul8x16bl(vis_rebd_hi(row3), vis_rebd_lo(yFilter)); \
  sum0 = vis_fpbdd16(v00, v10);                                  \
  v31 = vis_fmul8x16bl(vis_rebd_lo(row3), vis_rebd_lo(yFilter)); \
  sum1 = vis_fpbdd16(v01, v11);                                  \
  sum0 = vis_fpbdd16(sum0, v20);                                 \
  sum1 = vis_fpbdd16(sum1, v21);                                 \
  sum0 = vis_fpbdd16(sum0, v30);                                 \
  sum1 = vis_fpbdd16(sum1, v31);                                 \
  v00 = vis_fmul8sux16(sum0, xFilter0);                          \
  v01 = vis_fmul8sux16(sum1, xFilter1);                          \
  v10 = vis_fmul8ulx16(sum0, xFilter0);                          \
  sum0 = vis_fpbdd16(v00, v10);                                  \
  v11 = vis_fmul8ulx16(sum1, xFilter1);                          \
  sum1 = vis_fpbdd16(v01, v11);                                  \
  d0 = vis_fpbdd16(sum0, sum1);                                  \
  v00 = vis_write_lo(v00, vis_fpbdd16s(vis_rebd_hi(d0),          \
                                       vis_rebd_lo(d0)));        \
  res = vis_write_lo(res, vis_fpbck16(v00))

/***************************************************************/
#define BC_U8_2CH(index, mlib_filters_u8)                              \
  v00 = vis_fmul8x16bu(vis_rebd_hi(row0), vis_rebd_hi(yFilter));       \
  dr = vis_fpmerge(vis_rebd_hi(xFilter), vis_rebd_lo(xFilter));        \
  v01 = vis_fmul8x16bu(vis_rebd_lo(row0), vis_rebd_hi(yFilter));       \
  dr = vis_fpmerge(vis_rebd_hi(dr), vis_rebd_lo(dr));                  \
  v10 = vis_fmul8x16bl(vis_rebd_hi(row1), vis_rebd_hi(yFilter));       \
  dr1 = vis_fpmerge(vis_rebd_lo(dr), vis_rebd_lo(dr));                 \
  v11 = vis_fmul8x16bl(vis_rebd_lo(row1), vis_rebd_hi(yFilter));       \
  dr = vis_fpmerge(vis_rebd_hi(dr), vis_rebd_hi(dr));                  \
  v20 = vis_fmul8x16bu(vis_rebd_hi(row2), vis_rebd_lo(yFilter));       \
  xFilter0 = vis_fpmerge(vis_rebd_hi(dr), vis_rebd_hi(dr1));           \
  v21 = vis_fmul8x16bu(vis_rebd_lo(row2), vis_rebd_lo(yFilter));       \
  xFilter1 = vis_fpmerge(vis_rebd_lo(dr), vis_rebd_lo(dr1));           \
  v30 = vis_fmul8x16bl(vis_rebd_hi(row3), vis_rebd_lo(yFilter));       \
  v31 = vis_fmul8x16bl(vis_rebd_lo(row3), vis_rebd_lo(yFilter));       \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  sum0 = vis_fpbdd16(v00, v10);                                        \
  filterposy = (Y >> FILTER_SHIFT);                                    \
  dbtb1 = dpSrc[1];                                                    \
  row0 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  filterposx = (X >> FILTER_SHIFT);                                    \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  sum1 = vis_fpbdd16(v01, v11);                                        \
  X += dX;                                                             \
  dbtb1 = dpSrc[1];                                                    \
  sum0 = vis_fpbdd16(sum0, v20);                                       \
  row1 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  Y += dY;                                                             \
  sum1 = vis_fpbdd16(sum1, v21);                                       \
  xSrc = (X>>MLIB_SHIFT)-1;                                            \
  dbtb0 = dpSrc[0];                                                    \
  ySrc = (Y>>MLIB_SHIFT)-1;                                            \
  sum0 = vis_fpbdd16(sum0, v30);                                       \
  dbtb1 = dpSrc[1];                                                    \
  filterposy &= FILTER_MASK;                                           \
  sum1 = vis_fpbdd16(sum1, v31);                                       \
  v00 = vis_fmul8sux16(sum0, xFilter0);                                \
  row2 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  v01 = vis_fmul8sux16(sum1, xFilter1);                                \
  sPtr += srcYStride;                                                  \
  v10 = vis_fmul8ulx16(sum0, xFilter0);                                \
  filterposx &= FILTER_MASK;                                           \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  v11= vis_fmul8ulx16(sum1, xFilter1);                                 \
  dbtb0 = dpSrc[0];                                                    \
  d0##index = vis_fpbdd16(v00, v10);                                   \
  dbtb1 = dpSrc[1];                                                    \
  row3 = vis_fbligndbtb(dbtb0, dbtb1);                                 \
  yFilter = *((mlib_d64 *) ((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  d1##index = vis_fpbdd16(v01, v11);                                   \
  xFilter = *((mlib_d64 *)((mlib_u8 *)mlib_filters_u8 + filterposx));  \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + (xSrc<<1)

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_u8_2ch_bc (mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE  *dstLineEnd;
  mlib_s32  filterposx, filterposy;
  mlib_d64  dbtb0, dbtb1;
  mlib_d64  sum0, sum1;
  mlib_d64  row0, row1, row2, row3;
  mlib_f32  p0, p1, p2, p3;
  mlib_d64  xFilter;
  mlib_d64  xFilter0, xFilter1, yFilter;
  mlib_d64  v00, v10, v20, v30;
  mlib_d64  v01, v11, v21, v31;
  mlib_d64  d0, d1, d2, d3;
  mlib_d64  d00, d01, d02, d03;
  mlib_d64  d10, d11, d12, d13;
  mlib_d64  e0, e1;
  mlib_d64  *dpSrc;
  mlib_s32  cols, i, mbsk, off;
  mlib_d64  dr, dr1;
  mlib_d64  res, *dp;
  const mlib_s16 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = mlib_filters_u8_bc;
  } else {
    mlib_filters_tbble = mlib_filters_u8_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {

    vis_write_gsr(3 << 3);

    CLIP(2);
    dstLineEnd  = (DTYPE*)dstDbtb + 2 * xRight;

    cols = xRight - xLeft + 1;
    dp = vis_blignbddr(dstPixelPtr, 0);
    off = dstPixelPtr - (mlib_u8*)dp;
    dstLineEnd += 1;
    mbsk = vis_edge8(dstPixelPtr, dstLineEnd);
    i = 0;

    if (i <= cols - 10) {

      NEXT_PIXEL_2BC_U8();
      LOAD_BC_U8_2CH_1PIXEL(mlib_filters_tbble);

      NEXT_PIXEL_2BC_U8();

      BC_U8_2CH(0, mlib_filters_tbble);
      BC_U8_2CH(1, mlib_filters_tbble);
      BC_U8_2CH(2, mlib_filters_tbble);
      BC_U8_2CH(3, mlib_filters_tbble);

      FADD_2BC_U8();

      BC_U8_2CH(0, mlib_filters_tbble);
      BC_U8_2CH(1, mlib_filters_tbble);
      BC_U8_2CH(2, mlib_filters_tbble);
      BC_U8_2CH(3, mlib_filters_tbble);

#prbgmb pipeloop(0)
      for (; i <= cols-14; i+=4) {
        vis_blignbddr((void *)(8 - (mlib_bddr)dstPixelPtr), 0);
        res = vis_fbligndbtb(res, res);
        vis_pst_8(res, dp++, mbsk);
        vis_pst_8(res, dp, ~mbsk);
        FADD_2BC_U8();
        BC_U8_2CH(0, mlib_filters_tbble);
        BC_U8_2CH(1, mlib_filters_tbble);
        BC_U8_2CH(2, mlib_filters_tbble);
        BC_U8_2CH(3, mlib_filters_tbble);
      }

      vis_blignbddr((void *)(8 - (mlib_bddr)dstPixelPtr), 0);
      res = vis_fbligndbtb(res, res);
      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);

      FADD_2BC_U8();
      vis_blignbddr((void *)(8 - (mlib_bddr)dstPixelPtr), 0);
      res = vis_fbligndbtb(res, res);
      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);

      dstPixelPtr = (mlib_u8*)dp + off;

      RESULT_2BC_U8_1PIXEL();
      vis_blignbddr((void *)7, 0);
      vis_st_u8(res, dstPixelPtr+1);
      res = vis_fbligndbtb(res, res);
      vis_st_u8(res, dstPixelPtr);
      dstPixelPtr += 2;

      LOAD_BC_U8_2CH_1PIXEL(mlib_filters_tbble);
      RESULT_2BC_U8_1PIXEL();
      vis_blignbddr((void *)7, 0);
      vis_st_u8(res, dstPixelPtr+1);
      res = vis_fbligndbtb(res, res);
      vis_st_u8(res, dstPixelPtr);
      dstPixelPtr += 2;
      i += 10;
    }

    for (; i < cols; i++) {
      NEXT_PIXEL_2BC_U8();
      LOAD_BC_U8_2CH_1PIXEL(mlib_filters_tbble);
      RESULT_2BC_U8_1PIXEL();
      vis_blignbddr((void *)7, 0);
      vis_st_u8(res, dstPixelPtr+1);
      res = vis_fbligndbtb(res, res);
      vis_st_u8(res, dstPixelPtr);
      dstPixelPtr += 2;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#ifndef MLIB_VIS2

#define FADD_3BC_U8()                                           \
  vis_blignbddr((void*)6, 0);                                   \
  d3 = vis_fbligndbtb(d0, d1);                                  \
  vis_blignbddr((void*)2, 0);                                   \
  d4 = vis_fbligndbtb(d1, d2);                                  \
  d0 = vis_fpbdd16(d0, d3);                                     \
  d2 = vis_fpbdd16(d2, d4);                                     \
  d1 = vis_fbligndbtb(d2, d2);                                  \
  d0 = vis_fpbdd16(d0, d1);                                     \
  f0.f = vis_fpbck16(d0)

#else

#define FADD_3BC_U8()                                           \
  vis_blignbddr((void*)4, 0);                                   \
  d3 = vis_bshuffle(d0, d1);                                    \
  d1 = vis_fbligndbtb(d1, d2);                                  \
  d2 = vis_fbligndbtb(d2, d2);                                  \
  d4 = vis_bshuffle(d1, d2);                                    \
  d0 = vis_fpbdd16(d0, d3);                                     \
  d1 = vis_fpbdd16(d1, d4);                                     \
  d0 = vis_fpbdd16(d0, d1);                                     \
  f0.f = vis_fpbck16(d0)

#endif /* MLIB_VIS2 */

/***************************************************************/
#define LOAD_BC_U8_3CH_1PIXEL(mlib_filters_u8, mlib_filters_u8_3)      \
  filterposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFilter = *((mlib_d64 *) ((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  filterposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filters_u8_3+3*filterposx));      \
  xFilter0 = xPtr[0];                                                  \
  xFilter1 = xPtr[1];                                                  \
  xFilter2 = xPtr[2];                                                  \
  X += dX;                                                             \
  Y += dY;                                                             \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row31 = vis_fbligndbtb(dbtb1, dbtb2)

/***************************************************************/
#define STORE_BC_U8_3CH_1PIXEL()                                \
 dstPixelPtr[0] = f0.t[0];                                      \
 dstPixelPtr[1] = f0.t[1];                                      \
 dstPixelPtr[2] = f0.t[2];                                      \
 dstPixelPtr += 3

/***************************************************************/
#define NEXT_PIXEL_3BC_U8()                                     \
  xSrc = (X>>MLIB_SHIFT)-1;                                     \
  ySrc = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + (3*xSrc)

/***************************************************************/
#define RESULT_3BC_U8_1PIXEL()                                    \
  v00 = vis_fmul8x16bu(vis_rebd_hi(row00), vis_rebd_hi(yFilter)); \
  v01 = vis_fmul8x16bu(vis_rebd_lo(row00), vis_rebd_hi(yFilter)); \
  v02 = vis_fmul8x16bu(vis_rebd_hi(row01), vis_rebd_hi(yFilter)); \
  v10 = vis_fmul8x16bl(vis_rebd_hi(row10), vis_rebd_hi(yFilter)); \
  v11 = vis_fmul8x16bl(vis_rebd_lo(row10), vis_rebd_hi(yFilter)); \
  v12 = vis_fmul8x16bl(vis_rebd_hi(row11), vis_rebd_hi(yFilter)); \
  v20 = vis_fmul8x16bu(vis_rebd_hi(row20), vis_rebd_lo(yFilter)); \
  sum0 = vis_fpbdd16(v00, v10);                                   \
  v21 = vis_fmul8x16bu(vis_rebd_lo(row20), vis_rebd_lo(yFilter)); \
  sum1 = vis_fpbdd16(v01, v11);                                   \
  v22 = vis_fmul8x16bu(vis_rebd_hi(row21), vis_rebd_lo(yFilter)); \
  sum2 = vis_fpbdd16(v02, v12);                                   \
  v30 = vis_fmul8x16bl(vis_rebd_hi(row30), vis_rebd_lo(yFilter)); \
  sum0 = vis_fpbdd16(sum0, v20);                                  \
  v31 = vis_fmul8x16bl(vis_rebd_lo(row30), vis_rebd_lo(yFilter)); \
  sum1 = vis_fpbdd16(sum1, v21);                                  \
  v32 = vis_fmul8x16bl(vis_rebd_hi(row31), vis_rebd_lo(yFilter)); \
  sum2 = vis_fpbdd16(sum2, v22);                                  \
  sum0 = vis_fpbdd16(sum0, v30);                                  \
  sum1 = vis_fpbdd16(sum1, v31);                                  \
  v00 = vis_fmul8sux16(sum0, xFilter0);                           \
  sum2 = vis_fpbdd16(sum2, v32);                                  \
  v01 = vis_fmul8ulx16(sum0, xFilter0);                           \
  v10 = vis_fmul8sux16(sum1, xFilter1);                           \
  d0 = vis_fpbdd16(v00, v01);                                     \
  v11 = vis_fmul8ulx16(sum1, xFilter1);                           \
  v20 = vis_fmul8sux16(sum2, xFilter2);                           \
  d1 = vis_fpbdd16(v10, v11);                                     \
  v21 = vis_fmul8ulx16(sum2, xFilter2);                           \
  d2 = vis_fpbdd16(v20, v21);                                     \
  FADD_3BC_U8();

/***************************************************************/
#define BC_U8_3CH(mlib_filters_u8, mlib_filters_u8_3)                 \
  v00 = vis_fmul8x16bu(vis_rebd_hi(row00), vis_rebd_hi(yFilter));     \
  v01 = vis_fmul8x16bu(vis_rebd_lo(row00), vis_rebd_hi(yFilter));     \
  v02 = vis_fmul8x16bu(vis_rebd_hi(row01), vis_rebd_hi(yFilter));     \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  dbtb0 = dpSrc[0];                                                   \
  filterposy = (Y >> FILTER_SHIFT);                                   \
  v10 = vis_fmul8x16bl(vis_rebd_hi(row10), vis_rebd_hi(yFilter));     \
  dbtb1 = dpSrc[1];                                                   \
  v11 = vis_fmul8x16bl(vis_rebd_lo(row10), vis_rebd_hi(yFilter));     \
  sum0 = vis_fpbdd16(v00, v10);                                       \
  dbtb2 = dpSrc[2];                                                   \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v12 = vis_fmul8x16bl(vis_rebd_hi(row11), vis_rebd_hi(yFilter));     \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  filterposx = (X >> FILTER_SHIFT);                                   \
  sPtr += srcYStride;                                                 \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  v20 = vis_fmul8x16bu(vis_rebd_hi(row20), vis_rebd_lo(yFilter));     \
  sum1 = vis_fpbdd16(v01, v11);                                       \
  dbtb0 = dpSrc[0];                                                   \
  X += dX;                                                            \
  dbtb1 = dpSrc[1];                                                   \
  v21 = vis_fmul8x16bu(vis_rebd_lo(row20), vis_rebd_lo(yFilter));     \
  sum2 = vis_fpbdd16(v02, v12);                                       \
  dbtb2 = dpSrc[2];                                                   \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v22 = vis_fmul8x16bu(vis_rebd_hi(row21), vis_rebd_lo(yFilter));     \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srcYStride;                                                 \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  Y += dY;                                                            \
  xSrc = (X>>MLIB_SHIFT)-1;                                           \
  v30 = vis_fmul8x16bl(vis_rebd_hi(row30), vis_rebd_lo(yFilter));     \
  sum0 = vis_fpbdd16(sum0, v20);                                      \
  dbtb0 = dpSrc[0];                                                   \
  ySrc = (Y>>MLIB_SHIFT)-1;                                           \
  dbtb1 = dpSrc[1];                                                   \
  v31 = vis_fmul8x16bl(vis_rebd_lo(row30), vis_rebd_lo(yFilter));     \
  sum1 = vis_fpbdd16(sum1, v21);                                      \
  dbtb2 = dpSrc[2];                                                   \
  filterposy &= FILTER_MASK;                                          \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v32 = vis_fmul8x16bl(vis_rebd_hi(row31), vis_rebd_lo(yFilter));     \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srcYStride;                                                 \
  filterposx &= FILTER_MASK;                                          \
  sum2 = vis_fpbdd16(sum2, v22);                                      \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  sum0 = vis_fpbdd16(sum0, v30);                                      \
  dbtb0 = dpSrc[0];                                                   \
  sum1 = vis_fpbdd16(sum1, v31);                                      \
  v00 = vis_fmul8sux16(sum0, xFilter0);                               \
  dbtb1 = dpSrc[1];                                                   \
  sum2 = vis_fpbdd16(sum2, v32);                                      \
  v01 = vis_fmul8ulx16(sum0, xFilter0);                               \
  dbtb2 = dpSrc[2];                                                   \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v10 = vis_fmul8sux16(sum1, xFilter1);                               \
  d0 = vis_fpbdd16(v00, v01);                                         \
  row31 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  yFilter = *((mlib_d64 *)((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  v11 = vis_fmul8ulx16(sum1, xFilter1);                               \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filters_u8_3+3*filterposx));     \
  xFilter0 = xPtr[0];                                                 \
  v20 = vis_fmul8sux16(sum2, xFilter2);                               \
  d1 = vis_fpbdd16(v10, v11);                                         \
  xFilter1 = xPtr[1];                                                 \
  v21 = vis_fmul8ulx16(sum2, xFilter2);                               \
  xFilter2 = xPtr[2];                                                 \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + (3*xSrc);                        \
  d2 = vis_fpbdd16(v20, v21)

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_u8_3ch_bc (mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  mlib_s32  filterposx, filterposy;
  mlib_d64  dbtb0, dbtb1, dbtb2;
  mlib_d64  sum0, sum1, sum2;
  mlib_d64  row00, row10, row20, row30;
  mlib_d64  row01, row11, row21, row31;
  mlib_d64  xFilter0, xFilter1, xFilter2, yFilter;
  mlib_d64  v00, v10, v20, v30;
  mlib_d64  v01, v11, v21, v31;
  mlib_d64  v02, v12, v22, v32;
  mlib_d64  d0, d1, d2, d3, d4;
  mlib_d64  *dpSrc;
  mlib_s32  cols, i;
  mlib_d64  *xPtr;
  union {
    mlib_u8 t[4];
    mlib_f32 f;
  } f0;
  const mlib_s16 *mlib_filters_tbble  ;
  const mlib_s16 *mlib_filters_tbble_3;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble   = mlib_filters_u8_bc;
    mlib_filters_tbble_3 = mlib_filters_u8_bc_3;
  } else {
    mlib_filters_tbble   = mlib_filters_u8_bc2;
    mlib_filters_tbble_3 = mlib_filters_u8_bc2_3;
  }

  vis_write_gsr(3 << 3);
  MLIB_WRITE_BMASK(0x6789ABCD);

  for (j = yStbrt; j <= yFinish; j ++) {

    CLIP(3);

    cols = xRight - xLeft + 1;
    i = 0;

    if (i <= cols - 4) {

      NEXT_PIXEL_3BC_U8();
      LOAD_BC_U8_3CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_3);

      NEXT_PIXEL_3BC_U8();

      BC_U8_3CH(mlib_filters_tbble, mlib_filters_tbble_3);
      FADD_3BC_U8();

      BC_U8_3CH(mlib_filters_tbble, mlib_filters_tbble_3);

#prbgmb pipeloop(0)
      for (; i < cols-4; i++) {
        STORE_BC_U8_3CH_1PIXEL();

        FADD_3BC_U8();
        BC_U8_3CH(mlib_filters_tbble, mlib_filters_tbble_3);
      }

      STORE_BC_U8_3CH_1PIXEL();

      FADD_3BC_U8();
      STORE_BC_U8_3CH_1PIXEL();

      RESULT_3BC_U8_1PIXEL();
      STORE_BC_U8_3CH_1PIXEL();

      LOAD_BC_U8_3CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_3);
      RESULT_3BC_U8_1PIXEL();
      STORE_BC_U8_3CH_1PIXEL();
      i += 4;
    }

    for (; i < cols; i++) {
      NEXT_PIXEL_3BC_U8();
      LOAD_BC_U8_3CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_3);
      RESULT_3BC_U8_1PIXEL();
      STORE_BC_U8_3CH_1PIXEL();
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#define FADD_4BC_U8()                                           \
  d0 = vis_fpbdd16(d00, d10);                                   \
  d1 = vis_fpbdd16(d20, d30);                                   \
  d0 = vis_fpbdd16(d0, d1);                                     \
  d2 = vis_fpbdd16(d01, d11);                                   \
  d3 = vis_fpbdd16(d21, d31);                                   \
  d2 = vis_fpbdd16(d2, d3);                                     \
  res = vis_fpbck16_pbir(d0, d2)

/***************************************************************/
#define LOAD_BC_U8_4CH_1PIXEL(mlib_filters_u8, mlib_filters_u8_4)      \
  filterposy = (Y >> FILTER_SHIFT) & FILTER_MASK;                      \
  yFilter = *((mlib_d64 *) ((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  filterposx = (X >> FILTER_SHIFT) & FILTER_MASK;                      \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filters_u8_4+4*filterposx));      \
  xFilter0 = xPtr[0];                                                  \
  xFilter1 = xPtr[1];                                                  \
  xFilter2 = xPtr[2];                                                  \
  xFilter3 = xPtr[3];                                                  \
  X += dX;                                                             \
  Y += dY;                                                             \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                                \
  sPtr += srcYStride;                                                  \
  ALIGN_ADDR(dpSrc, sPtr);                                             \
  dbtb0 = dpSrc[0];                                                    \
  dbtb1 = dpSrc[1];                                                    \
  dbtb2 = dpSrc[2];                                                    \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                                \
  row31 = vis_fbligndbtb(dbtb1, dbtb2)

/***************************************************************/
#define NEXT_PIXEL_4BC_U8()                                     \
  xSrc = (X>>MLIB_SHIFT)-1;                                     \
  ySrc = (Y>>MLIB_SHIFT)-1;                                     \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + (4*xSrc)

/***************************************************************/
#define RESULT_4BC_U8_1PIXEL(ind)                                 \
  v00 = vis_fmul8x16bu(vis_rebd_hi(row00), vis_rebd_hi(yFilter)); \
  v01 = vis_fmul8x16bu(vis_rebd_lo(row00), vis_rebd_hi(yFilter)); \
  v02 = vis_fmul8x16bu(vis_rebd_hi(row01), vis_rebd_hi(yFilter)); \
  v03 = vis_fmul8x16bu(vis_rebd_lo(row01), vis_rebd_hi(yFilter)); \
  v10 = vis_fmul8x16bl(vis_rebd_hi(row10), vis_rebd_hi(yFilter)); \
  v11 = vis_fmul8x16bl(vis_rebd_lo(row10), vis_rebd_hi(yFilter)); \
  sum0 = vis_fpbdd16(v00, v10);                                   \
  v12 = vis_fmul8x16bl(vis_rebd_hi(row11), vis_rebd_hi(yFilter)); \
  sum1 = vis_fpbdd16(v01, v11);                                   \
  v13 = vis_fmul8x16bl(vis_rebd_lo(row11), vis_rebd_hi(yFilter)); \
  sum2 = vis_fpbdd16(v02, v12);                                   \
  v20 = vis_fmul8x16bu(vis_rebd_hi(row20), vis_rebd_lo(yFilter)); \
  sum3 = vis_fpbdd16(v03, v13);                                   \
  v21 = vis_fmul8x16bu(vis_rebd_lo(row20), vis_rebd_lo(yFilter)); \
  sum0 = vis_fpbdd16(sum0, v20);                                  \
  v22 = vis_fmul8x16bu(vis_rebd_hi(row21), vis_rebd_lo(yFilter)); \
  sum1 = vis_fpbdd16(sum1, v21);                                  \
  v23 = vis_fmul8x16bu(vis_rebd_lo(row21), vis_rebd_lo(yFilter)); \
  sum2 = vis_fpbdd16(sum2, v22);                                  \
  v30 = vis_fmul8x16bl(vis_rebd_hi(row30), vis_rebd_lo(yFilter)); \
  sum3 = vis_fpbdd16(sum3, v23);                                  \
  v31 = vis_fmul8x16bl(vis_rebd_lo(row30), vis_rebd_lo(yFilter)); \
  sum0 = vis_fpbdd16(sum0, v30);                                  \
  v32 = vis_fmul8x16bl(vis_rebd_hi(row31), vis_rebd_lo(yFilter)); \
  sum1 = vis_fpbdd16(sum1, v31);                                  \
  v33 = vis_fmul8x16bl(vis_rebd_lo(row31), vis_rebd_lo(yFilter)); \
  sum2 = vis_fpbdd16(sum2, v32);                                  \
  v00 = vis_fmul8sux16(sum0, xFilter0);                           \
  sum3 = vis_fpbdd16(sum3, v33);                                  \
  v01 = vis_fmul8ulx16(sum0, xFilter0);                           \
  v10 = vis_fmul8sux16(sum1, xFilter1);                           \
  d0##ind = vis_fpbdd16(v00, v01);                                \
  v11 = vis_fmul8ulx16(sum1, xFilter1);                           \
  v20 = vis_fmul8sux16(sum2, xFilter2);                           \
  d1##ind = vis_fpbdd16(v10, v11);                                \
  v21 = vis_fmul8ulx16(sum2, xFilter2);                           \
  v30 = vis_fmul8sux16(sum3, xFilter3);                           \
  d2##ind = vis_fpbdd16(v20, v21);                                \
  v31 = vis_fmul8ulx16(sum3, xFilter3);                           \
  d3##ind = vis_fpbdd16(v30, v31)

/***************************************************************/
#define BC_U8_4CH(ind, mlib_filters_u8, mlib_filters_u8_4)            \
  v00 = vis_fmul8x16bu(vis_rebd_hi(row00), vis_rebd_hi(yFilter));     \
  v01 = vis_fmul8x16bu(vis_rebd_lo(row00), vis_rebd_hi(yFilter));     \
  v02 = vis_fmul8x16bu(vis_rebd_hi(row01), vis_rebd_hi(yFilter));     \
  v03 = vis_fmul8x16bu(vis_rebd_lo(row01), vis_rebd_hi(yFilter));     \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  dbtb0 = dpSrc[0];                                                   \
  filterposy = (Y >> FILTER_SHIFT);                                   \
  v10 = vis_fmul8x16bl(vis_rebd_hi(row10), vis_rebd_hi(yFilter));     \
  dbtb1 = dpSrc[1];                                                   \
  v11 = vis_fmul8x16bl(vis_rebd_lo(row10), vis_rebd_hi(yFilter));     \
  sum0 = vis_fpbdd16(v00, v10);                                       \
  dbtb2 = dpSrc[2];                                                   \
  row00 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v12 = vis_fmul8x16bl(vis_rebd_hi(row11), vis_rebd_hi(yFilter));     \
  row01 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  filterposx = (X >> FILTER_SHIFT);                                   \
  v13 = vis_fmul8x16bl(vis_rebd_lo(row11), vis_rebd_hi(yFilter));     \
  sPtr += srcYStride;                                                 \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  v20 = vis_fmul8x16bu(vis_rebd_hi(row20), vis_rebd_lo(yFilter));     \
  sum1 = vis_fpbdd16(v01, v11);                                       \
  dbtb0 = dpSrc[0];                                                   \
  X += dX;                                                            \
  dbtb1 = dpSrc[1];                                                   \
  v21 = vis_fmul8x16bu(vis_rebd_lo(row20), vis_rebd_lo(yFilter));     \
  sum2 = vis_fpbdd16(v02, v12);                                       \
  dbtb2 = dpSrc[2];                                                   \
  row10 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v22 = vis_fmul8x16bu(vis_rebd_hi(row21), vis_rebd_lo(yFilter));     \
  row11 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srcYStride;                                                 \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  v23 = vis_fmul8x16bu(vis_rebd_lo(row21), vis_rebd_lo(yFilter));     \
  sum3 = vis_fpbdd16(v03, v13);                                       \
  Y += dY;                                                            \
  xSrc = (X>>MLIB_SHIFT)-1;                                           \
  v30 = vis_fmul8x16bl(vis_rebd_hi(row30), vis_rebd_lo(yFilter));     \
  sum0 = vis_fpbdd16(sum0, v20);                                      \
  dbtb0 = dpSrc[0];                                                   \
  ySrc = (Y>>MLIB_SHIFT)-1;                                           \
  dbtb1 = dpSrc[1];                                                   \
  v31 = vis_fmul8x16bl(vis_rebd_lo(row30), vis_rebd_lo(yFilter));     \
  sum1 = vis_fpbdd16(sum1, v21);                                      \
  dbtb2 = dpSrc[2];                                                   \
  filterposy &= FILTER_MASK;                                          \
  row20 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v32 = vis_fmul8x16bl(vis_rebd_hi(row31), vis_rebd_lo(yFilter));     \
  row21 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  sPtr += srcYStride;                                                 \
  filterposx &= FILTER_MASK;                                          \
  v33 = vis_fmul8x16bl(vis_rebd_lo(row31), vis_rebd_lo(yFilter));     \
  sum2 = vis_fpbdd16(sum2, v22);                                      \
  ALIGN_ADDR(dpSrc, sPtr);                                            \
  sum3 = vis_fpbdd16(sum3, v23);                                      \
  sum0 = vis_fpbdd16(sum0, v30);                                      \
  dbtb0 = dpSrc[0];                                                   \
  sum1 = vis_fpbdd16(sum1, v31);                                      \
  v00 = vis_fmul8sux16(sum0, xFilter0);                               \
  dbtb1 = dpSrc[1];                                                   \
  sum2 = vis_fpbdd16(sum2, v32);                                      \
  v01 = vis_fmul8ulx16(sum0, xFilter0);                               \
  sum3 = vis_fpbdd16(sum3, v33);                                      \
  dbtb2 = dpSrc[2];                                                   \
  row30 = vis_fbligndbtb(dbtb0, dbtb1);                               \
  v10 = vis_fmul8sux16(sum1, xFilter1);                               \
  d0##ind = vis_fpbdd16(v00, v01);                                    \
  row31 = vis_fbligndbtb(dbtb1, dbtb2);                               \
  yFilter = *((mlib_d64 *)((mlib_u8 *)mlib_filters_u8 + filterposy)); \
  v11 = vis_fmul8ulx16(sum1, xFilter1);                               \
  xPtr=((mlib_d64 *)((mlib_u8 *)mlib_filters_u8_4+4*filterposx));     \
  xFilter0 = xPtr[0];                                                 \
  v20 = vis_fmul8sux16(sum2, xFilter2);                               \
  d1##ind = vis_fpbdd16(v10, v11);                                    \
  xFilter1 = xPtr[1];                                                 \
  v21 = vis_fmul8ulx16(sum2, xFilter2);                               \
  xFilter2 = xPtr[2];                                                 \
  v30 = vis_fmul8sux16(sum3, xFilter3);                               \
  d2##ind = vis_fpbdd16(v20, v21);                                    \
  v31 = vis_fmul8ulx16(sum3, xFilter3);                               \
  xFilter3 = xPtr[3];                                                 \
  sPtr = (mlib_u8 *)lineAddr[ySrc] + (4*xSrc);                        \
  d3##ind = vis_fpbdd16(v30, v31)

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_u8_4ch_bc (mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE  *dstLineEnd;
  mlib_s32  filterposx, filterposy;
  mlib_d64  dbtb0, dbtb1, dbtb2;
  mlib_d64  sum0, sum1, sum2, sum3;
  mlib_d64  row00, row10, row20, row30;
  mlib_d64  row01, row11, row21, row31;
  mlib_d64  xFilter0, xFilter1, xFilter2, xFilter3, yFilter;
  mlib_d64  v00, v10, v20, v30;
  mlib_d64  v01, v11, v21, v31;
  mlib_d64  v02, v12, v22, v32;
  mlib_d64  v03, v13, v23, v33;
  mlib_d64  d0, d1, d2, d3;
  mlib_d64  d00, d10, d20, d30;
  mlib_d64  d01, d11, d21, d31;
  mlib_d64  *dpSrc;
  mlib_s32  cols, i;
  mlib_d64  res, *dp, *xPtr;
  mlib_s32  mbsk, embsk, gsrd;
  const mlib_s16 *mlib_filters_tbble  ;
  const mlib_s16 *mlib_filters_tbble_4;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble   = mlib_filters_u8_bc;
    mlib_filters_tbble_4 = mlib_filters_u8_bc_4;
  } else {
    mlib_filters_tbble   = mlib_filters_u8_bc2;
    mlib_filters_tbble_4 = mlib_filters_u8_bc2_4;
  }

  for (j = yStbrt; j <= yFinish; j++) {

    vis_write_gsr(3 << 3);

    CLIP(4);
    dstLineEnd  = (DTYPE*)dstDbtb + 4 * xRight;
    dstLineEnd += 3;
    dp = (mlib_d64*)vis_blignbddr(dstPixelPtr, 0);
    mbsk = vis_edge8(dstPixelPtr, dstLineEnd);
    gsrd = ((8 - (mlib_bddr)dstPixelPtr) & 7);

    cols = xRight - xLeft + 1;
    i = 0;

    if (i <= cols - 6) {

      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_4);

      NEXT_PIXEL_4BC_U8();

      BC_U8_4CH(0, mlib_filters_tbble, mlib_filters_tbble_4);
      BC_U8_4CH(1, mlib_filters_tbble, mlib_filters_tbble_4);
      FADD_4BC_U8();

      BC_U8_4CH(0, mlib_filters_tbble, mlib_filters_tbble_4);
      BC_U8_4CH(1, mlib_filters_tbble, mlib_filters_tbble_4);

#prbgmb pipeloop(0)
      for (; i <= cols-8; i+=2) {
        vis_blignbddr((void *)gsrd, 0);
        res = vis_fbligndbtb(res, res);

        vis_pst_8(res, dp++, mbsk);
        vis_pst_8(res, dp, ~mbsk);

        FADD_4BC_U8();
        BC_U8_4CH(0, mlib_filters_tbble, mlib_filters_tbble_4);
        BC_U8_4CH(1, mlib_filters_tbble, mlib_filters_tbble_4);
      }

      vis_blignbddr((void *)gsrd, 0);
      res = vis_fbligndbtb(res, res);

      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);

      FADD_4BC_U8();
      vis_blignbddr((void *)gsrd, 0);
      res = vis_fbligndbtb(res, res);

      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);

      RESULT_4BC_U8_1PIXEL(0);
      LOAD_BC_U8_4CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_4);
      RESULT_4BC_U8_1PIXEL(1);
      FADD_4BC_U8();

      vis_blignbddr((void *)gsrd, 0);
      res = vis_fbligndbtb(res, res);

      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);
      i += 6;
    }

    if (i <= cols-4) {
      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_4);

      NEXT_PIXEL_4BC_U8();

      BC_U8_4CH(0, mlib_filters_tbble, mlib_filters_tbble_4);
      BC_U8_4CH(1, mlib_filters_tbble, mlib_filters_tbble_4);
      FADD_4BC_U8();
      vis_blignbddr((void *)gsrd, 0);
      res = vis_fbligndbtb(res, res);

      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);

      RESULT_4BC_U8_1PIXEL(0);
      LOAD_BC_U8_4CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_4);
      RESULT_4BC_U8_1PIXEL(1);
      FADD_4BC_U8();

      vis_blignbddr((void *)gsrd, 0);
      res = vis_fbligndbtb(res, res);

      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);
      i += 4;
    }

    if (i <= cols-2) {
      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_4);
      RESULT_4BC_U8_1PIXEL(0);

      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_4);
      RESULT_4BC_U8_1PIXEL(1);
      FADD_4BC_U8();

      vis_blignbddr((void *)gsrd, 0);
      res = vis_fbligndbtb(res, res);

      vis_pst_8(res, dp++, mbsk);
      vis_pst_8(res, dp, ~mbsk);
      i += 2;
    }

    if (i < cols) {
      NEXT_PIXEL_4BC_U8();
      LOAD_BC_U8_4CH_1PIXEL(mlib_filters_tbble, mlib_filters_tbble_4);
      RESULT_4BC_U8_1PIXEL(0);

      d0 = vis_fpbdd16(d00, d10);
      d1 = vis_fpbdd16(d20, d30);
      d0 = vis_fpbdd16(d0, d1);
      res = vis_fpbck16_pbir(d0, d0);
      vis_blignbddr((void *)gsrd, 0);
      res = vis_fbligndbtb(res, res);

      embsk = vis_edge8(dp, dstLineEnd);
      vis_pst_8(res, dp++, embsk & mbsk);

      if ((mlib_u8*)dp <= (mlib_u8*)dstLineEnd) {
        mbsk = vis_edge8(dp, dstLineEnd);
        vis_pst_8(res, dp, mbsk);
      }
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
