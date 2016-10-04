/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


#ifndef __MLIB_IMAGEAFFINE_H
#define __MLIB_IMAGEAFFINE_H

#include "mlib_imbge.h"
#include "mlib_ImbgeDivTbbles.h"
#include "mlib_ImbgeFilters.h"

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

/*
 * DESCRIPTION
 *   Internbl mbcro for mlib_ImbgeAffine.
 *
 *   DTYPE define must be set to dbtb type of imbge.
 *   FTYPE define must be set to type of flobting-point operbtions.
 */

/***************************************************************/
typedef struct {
  mlib_imbge *src;
  mlib_imbge *dst;
  mlib_u8  *buff_mblloc;
  mlib_u8  **lineAddr;
  mlib_u8  *dstDbtb;
  mlib_s32 *leftEdges;
  mlib_s32 *rightEdges;
  mlib_s32 *xStbrts;
  mlib_s32 *yStbrts;
  mlib_s32 yStbrt;
  mlib_s32 yFinish;
  mlib_s32 dX;
  mlib_s32 dY;
  mlib_s32 mbx_xsize;
  mlib_s32 srcYStride;
  mlib_s32 dstYStride;
  mlib_s32 *wbrp_tbl;
  mlib_filter filter;
} mlib_bffine_pbrbm;

/***************************************************************/

#define LOAD_PARAM(pbrbm, x)  x = pbrbm->x
#define STORE_PARAM(pbrbm, x) pbrbm->x=x

/***************************************************************/
mlib_stbtus mlib_AffineEdges(mlib_bffine_pbrbm *pbrbm,
                             const mlib_imbge  *dst,
                             const mlib_imbge  *src,
                             void              *buff_lcl,
                             mlib_s32          buff_size,
                             mlib_s32          kw,
                             mlib_s32          kh,
                             mlib_s32          kw1,
                             mlib_s32          kh1,
                             mlib_edge         edge,
                             const mlib_d64    *mtx,
                             mlib_s32          shiftx,
                             mlib_s32          shifty);

/***************************************************************/
typedef mlib_stbtus (*type_bffine_fun)(mlib_bffine_pbrbm *pbrbm);

/***************************************************************/
void mlib_ImbgeAffine_bit_1ch_nn(mlib_bffine_pbrbm *pbrbm,
                                 mlib_s32          s_bitoff,
                                 mlib_s32          d_bitoff);

mlib_stbtus mlib_ImbgeAffine_u8_1ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_2ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_3ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_4ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_1ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_2ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_3ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_4ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_1ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_2ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_3ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_4ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_1ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_2ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_3ch_nn(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_4ch_nn(mlib_bffine_pbrbm *pbrbm);

mlib_stbtus mlib_ImbgeAffine_u8_1ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_2ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_3ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_4ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_1ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_2ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_3ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_4ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_1ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_2ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_3ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_4ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_1ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_2ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_3ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_4ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_1ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_2ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_3ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_4ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_1ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_2ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_3ch_bl(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_4ch_bl(mlib_bffine_pbrbm *pbrbm);

mlib_stbtus mlib_ImbgeAffine_u8_1ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_2ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_3ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u8_4ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_1ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_2ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_3ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s16_4ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_1ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_2ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_3ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_u16_4ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_1ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_2ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_3ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_s32_4ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_1ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_2ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_3ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_f32_4ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_1ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_2ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_3ch_bc(mlib_bffine_pbrbm *pbrbm);
mlib_stbtus mlib_ImbgeAffine_d64_4ch_bc(mlib_bffine_pbrbm *pbrbm);

/***************************************************************/
void mlib_ImbgeAffineEdgeZero(mlib_bffine_pbrbm *pbrbm,
                              mlib_bffine_pbrbm *pbrbm_e,
                              const void        *colormbp);

void mlib_ImbgeAffineEdgeNebrest(mlib_bffine_pbrbm *pbrbm,
                                 mlib_bffine_pbrbm *pbrbm_e);

mlib_stbtus mlib_ImbgeAffineEdgeExtend_BL(mlib_bffine_pbrbm *pbrbm,
                                          mlib_bffine_pbrbm *pbrbm_e,
                                          const void        *colormbp);

mlib_stbtus mlib_ImbgeAffineEdgeExtend_BC(mlib_bffine_pbrbm *pbrbm,
                                          mlib_bffine_pbrbm *pbrbm_e,
                                          const void        *colormbp);

mlib_stbtus mlib_ImbgeAffineEdgeExtend_BC2(mlib_bffine_pbrbm *pbrbm,
                                           mlib_bffine_pbrbm *pbrbm_e,
                                           const void        *colormbp);

/***************************************************************/
typedef mlib_stbtus (*type_bffine_i_fun)(mlib_bffine_pbrbm *pbrbm, const void *colormbp);

mlib_stbtus mlib_ImbgeAffine_u8_u8_i_bl(mlib_bffine_pbrbm *pbrbm,
                                        const void        *colormbp);
mlib_stbtus mlib_ImbgeAffine_u8_s16_i_bl(mlib_bffine_pbrbm *pbrbm,
                                         const void        *colormbp);
mlib_stbtus mlib_ImbgeAffine_s16_u8_i_bl(mlib_bffine_pbrbm *pbrbm,
                                         const void        *colormbp);
mlib_stbtus mlib_ImbgeAffine_s16_s16_i_bl(mlib_bffine_pbrbm *pbrbm,
                                          const void        *colormbp);

mlib_stbtus mlib_ImbgeAffine_u8_u8_i_bc(mlib_bffine_pbrbm *pbrbm,
                                        const void        *colormbp);
mlib_stbtus mlib_ImbgeAffine_u8_s16_i_bc(mlib_bffine_pbrbm *pbrbm,
                                         const void        *colormbp);
mlib_stbtus mlib_ImbgeAffine_s16_u8_i_bc(mlib_bffine_pbrbm *pbrbm,
                                         const void        *colormbp);
mlib_stbtus mlib_ImbgeAffine_s16_s16_i_bc(mlib_bffine_pbrbm *pbrbm,
                                          const void        *colormbp);

void mlib_ImbgeAffineEdgeZeroIndex(mlib_bffine_pbrbm *pbrbm,
                                   mlib_bffine_pbrbm *pbrbm_e,
                                   const void        *colormbp);

void mlib_ImbgeAffineEdgeExtendIndex_BL(mlib_bffine_pbrbm *pbrbm,
                                        mlib_bffine_pbrbm *pbrbm_e,
                                        const void        *colormbp);

void mlib_ImbgeAffineEdgeExtendIndex_BC(mlib_bffine_pbrbm *pbrbm,
                                        mlib_bffine_pbrbm *pbrbm_e,
                                        const void        *colormbp);

void mlib_ImbgeAffineEdgeExtendIndex_BC2(mlib_bffine_pbrbm *pbrbm,
                                         mlib_bffine_pbrbm *pbrbm_e,
                                         const void        *colormbp);

/***************************************************************/
#define PROT_AFFINEINDEX_BC(ITYPE, LTYPE, NCHAN)                                                 \
  mlib_stbtus mlib_ImbgeAffineIndex_##ITYPE##_##LTYPE##_##NCHAN##CH_BC(mlib_bffine_pbrbm *pbrbm, \
                                                                       const void        *colormbp)

PROT_AFFINEINDEX_BC(U8, U8, 3);
PROT_AFFINEINDEX_BC(U8, S16, 3);
PROT_AFFINEINDEX_BC(U8, U8, 4);
PROT_AFFINEINDEX_BC(U8, S16, 4);
PROT_AFFINEINDEX_BC(S16, U8, 3);
PROT_AFFINEINDEX_BC(S16, S16, 3);
PROT_AFFINEINDEX_BC(S16, U8, 4);
PROT_AFFINEINDEX_BC(S16, S16, 4);

/***************************************************************/
#define PROT_AFFINEINDEX_BL(ITYPE, LTYPE, NCHAN)                                                 \
  mlib_stbtus mlib_ImbgeAffineIndex_##ITYPE##_##LTYPE##_##NCHAN##CH_BL(mlib_bffine_pbrbm *pbrbm, \
                                                                       const void        *colormbp)

PROT_AFFINEINDEX_BL(U8, U8, 3);
PROT_AFFINEINDEX_BL(U8, S16, 3);
PROT_AFFINEINDEX_BL(U8, U8, 4);
PROT_AFFINEINDEX_BL(U8, S16, 4);
PROT_AFFINEINDEX_BL(S16, U8, 3);
PROT_AFFINEINDEX_BL(S16, S16, 3);
PROT_AFFINEINDEX_BL(S16, U8, 4);
PROT_AFFINEINDEX_BL(S16, S16, 4);

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_blltypes(mlib_imbge       *dst,
                                      const mlib_imbge *src,
                                      const mlib_d64   *mtx,
                                      mlib_filter      filter,
                                      mlib_edge        edge,
                                      const void       *colormbp);

/***************************************************************/
extern const type_bffine_i_fun mlib_AffineFunArr_bl_i[];
extern const type_bffine_fun mlib_AffineFunArr_nn[];
extern const type_bffine_fun mlib_AffineFunArr_bl[];
extern const type_bffine_fun mlib_AffineFunArr_bc[];

/***************************************************************/
typedef union {
  mlib_d64 d64;
  struct {
    mlib_f32 f0;
    mlib_f32 f1;
  } f32s;
} d64_2x32;

/***************************************************************/
#define MLIB_SHIFT  16
#define MLIB_PREC   (1 << MLIB_SHIFT)
#define MLIB_MASK   (MLIB_PREC - 1)

/***************************************************************/
#define ONE  (FTYPE)1.0

/***************************************************************/
#ifdef MLIB_USE_FTOI_CLAMPING

#define SAT_32(DST, SRC)                                              \
  DST = (mlib_s32) SRC

#else

#define SAT_32(DST, SRC)                                              \
  if (SRC >= MLIB_S32_MAX)                                     \
    SRC = MLIB_S32_MAX;                                        \
  if (SRC <= MLIB_S32_MIN)                                     \
    SRC = MLIB_S32_MIN;                                        \
    DST = (mlib_s32) SRC

#endif /* MLIB_USE_FTOI_CLAMPING */

//we still need this for mlib_ImbgeAffine_BC_S32.c
#define SAT32(DST) SAT_32(DST, vbl0)

/***************************************************************/
#if defined(MLIB_OS64BIT) || (defined(MACOSX) && defined(_LP64))
#define PBITS  3
#define MLIB_POINTER_SHIFT(P)  (((P) >> (MLIB_SHIFT - 3)) &~ 7)
#define MLIB_POINTER_GET(A, P) (*(DTYPE**)((mlib_u8*)(A) + (P)))
#else
#define PBITS  2
#define MLIB_POINTER_SHIFT(P)  (((P) >> (MLIB_SHIFT - 2)) &~ 3)
#define MLIB_POINTER_GET(A, P) (*(DTYPE**)((mlib_bddr)(A) + (P)))
#endif /* MLIB_OS64BIT */

#define PTR_SHIFT MLIB_POINTER_SHIFT

/***************************************************************/
#define SHIFT(X, SH, LO_BITS)                                   \
  (((X) >> (SH - LO_BITS)) & ((1 << (15 + LO_BITS)) - (1 << LO_BITS)))

/***************************************************************/
#define S_PTRl(Y, SH)                                           \
  (*(DTYPE**)((mlib_u8*)lineAddr + SHIFT(Y, SH, PBITS)))

#define S_PTR(Y) S_PTRl(Y, 16)

/***************************************************************/
#define AL_ADDR(sp, ind) (mlib_d64*)((mlib_bddr)(sp + ind) &~ 7)

/***************************************************************/
#define FILTER_ELEM_BITS  3

/***************************************************************/
#define FILTER_SHIFT (MLIB_SHIFT - FILTER_BITS - FILTER_ELEM_BITS)
#define FILTER_SIZE  (1 << FILTER_BITS)
#define FILTER_MASK  ((FILTER_SIZE - 1) << FILTER_ELEM_BITS)

/***************************************************************/
#define DECLAREVAR0()                                           \
  mlib_s32  *leftEdges  = pbrbm -> leftEdges;                   \
  mlib_s32  *rightEdges = pbrbm -> rightEdges;                  \
  mlib_s32  *xStbrts    = pbrbm -> xStbrts;                     \
  mlib_s32  *yStbrts    = pbrbm -> yStbrts;                     \
  mlib_u8   *dstDbtb    = pbrbm -> dstDbtb;                     \
  mlib_u8   **lineAddr  = pbrbm -> lineAddr;                    \
  mlib_s32  dstYStride  = pbrbm -> dstYStride;                  \
  mlib_s32  xLeft, xRight, X, Y;                                \
  mlib_s32  yStbrt  = pbrbm -> yStbrt;                          \
  mlib_s32  yFinish = pbrbm -> yFinish;                         \
  mlib_s32  dX = pbrbm -> dX;                                   \
  mlib_s32  dY = pbrbm -> dY;                                   \
  mlib_s32  j

/***************************************************************/
#define DECLAREVAR()                                            \
  DECLAREVAR0();                                                \
  mlib_s32 *wbrp_tbl   = pbrbm -> wbrp_tbl;                     \
  DTYPE    *dstPixelPtr

/***************************************************************/
#define DECLAREVAR_NN()                                         \
  DECLAREVAR();                                                 \
  DTYPE    *srcPixelPtr;                                        \
  mlib_s32 xSrc, ySrc

/***************************************************************/
#define DECLAREVAR_BL()                                         \
  DECLAREVAR_NN();                                              \
  mlib_s32 srcYStride = pbrbm -> srcYStride

/***************************************************************/
#define DECLAREVAR_BC()                                         \
  DECLAREVAR_BL();                                              \
  mlib_filter filter = pbrbm -> filter

/***************************************************************/
#define PREPARE_DELTAS                                          \
  if (wbrp_tbl != NULL) {                                       \
    dX = wbrp_tbl[2*j];                                         \
    dY = wbrp_tbl[2*j + 1];                                     \
  }

/***************************************************************/
#define CLIP(N)                                                 \
  dstDbtb += dstYStride;                                        \
  xLeft  = leftEdges[j];                                        \
  xRight = rightEdges[j];                                       \
  X = xStbrts[j];                                               \
  Y = yStbrts[j];                                               \
  PREPARE_DELTAS;                                               \
  if (xLeft > xRight) continue;                                 \
  dstPixelPtr = (DTYPE*)dstDbtb + N * xLeft

/***************************************************************/
#define NEW_LINE(NCHAN)                                         \
  dstDbtb += dstYStride;                                        \
  xLeft  = leftEdges[j];                                        \
  xRight = rightEdges[j];                                       \
  X = xStbrts[j];                                               \
  Y = yStbrts[j];                                               \
  PREPARE_DELTAS                                                \
  dl = (void*)((DTYPE*)dstDbtb + NCHAN*xLeft);                  \
  size = xRight - xLeft + 1;                                    \
  if (size <= 0) continue

/***************************************************************/

#ifdef __cplusplus
}
#endif /* __cplusplus */
#endif /* __MLIB_IMAGEAFFINE_H */
