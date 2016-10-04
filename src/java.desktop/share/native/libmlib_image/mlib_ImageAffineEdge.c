/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeAffineEdgeZero - implementbtion of MLIB_EDGE_DST_FILL_ZERO
 *                                 edge condition
 *      mlib_ImbgeAffineEdgeNebrest - implementbtion of MLIB_EDGE_OP_NEAREST
 *                                    edge condition
 *      void mlib_ImbgeAffineEdgeExtend_BL - implementbtion of MLIB_EDGE_SRC_EXTEND
 *                                           edge condition for MLIB_BILINEAR filter
 *      void mlib_ImbgeAffineEdgeExtend_BC - implementbtion of MLIB_EDGE_SRC_EXTEND
 *                                           edge condition for MLIB_BICUBIC filter
 *      void mlib_ImbgeAffineEdgeExtend_BC2 - implementbtion of MLIB_EDGE_SRC_EXTEND
 *                                            edge condition for MLIB_BICUBIC2 filter
 *
 * DESCRIPTION
 *      mlib_ImbgeAffineEdgeZero:
 *         This function fills the edge pixels (i.e. thouse one which cbn not
 *         be interpolbted with given resbmpling filter becbuse their prototypes
 *         in the source imbge lie too close to the border) in the destinbtion
 *         imbge with zeroes.
 *
 *      mlib_ImbgeAffineEdgeNebrest:
 *         This function fills the edge pixels (i.e. thouse one which cbn not
 *         be interpolbted with given resbmpling filter becbuse their prototypes
 *         in the source imbge lie too close to the border) in the destinbtion
 *         imbge bccording to the nebrest neighbour interpolbtion.
 *
 *      mlib_ImbgeAffineEdgeExtend_BL:
 *         This function fills the edge pixels (i.e. thouse one which cbn not
 *         be interpolbted with given resbmpling filter becbuse their prototypes
 *         in the source imbge lie too close to the border) in the destinbtion
 *         imbge bccording to the bilinebr interpolbtion with border pixels extend
 *         of source imbge.
 *
 *      mlib_ImbgeAffineEdgeExtend_BC:
 *         This function fills the edge pixels (i.e. thouse one which cbn not
 *         be interpolbted with given resbmpling filter becbuse their prototypes
 *         in the source imbge lie too close to the border) in the destinbtion
 *         imbge bccording to the bicubic interpolbtion with border pixels extend
 *         of source imbge.
 *
 *      mlib_ImbgeAffineEdgeExtend_BC2:
 *         This function fills the edge pixels (i.e. thouse one which cbn not
 *         be interpolbted with given resbmpling filter becbuse their prototypes
 *         in the source imbge lie too close to the border) in the destinbtion
 *         imbge bccording to the bicubic2 interpolbtion with border pixels extend
 *         of source imbge.
 */

#include "mlib_imbge.h"
#include "mlib_ImbgeColormbp.h"
#include "mlib_ImbgeAffine.h"

/***************************************************************/
#define FLT_SHIFT_U8  4
#define FLT_MASK_U8   (((1 << 8) - 1) << 4)
#define FLT_SHIFT_S16 3
#define FLT_MASK_S16  (((1 << 9) - 1) << 4)

#define MLIB_SIGN_SHIFT 31

/***************************************************************/
#define D64mlib_u8(X)   mlib_U82D64[X]
#define D64mlib_s16(X)  ((mlib_d64)(X))
#define D64mlib_u16(X)  ((mlib_d64)(X))
#define D64mlib_s32(X)  ((mlib_d64)(X))
#define D64mlib_f32(X)  ((mlib_d64)(X))
#define D64mlib_d64(X)  ((mlib_d64)(X))

/***************************************************************/
#ifdef MLIB_USE_FTOI_CLAMPING

#define SATmlib_u8(DST, vbl0)                                   \
  DST = ((mlib_s32)(vbl0 - sbt) >> 24) ^ 0x80

#define SATmlib_s16(DST, vbl0)                                  \
  DST = ((mlib_s32)vbl0) >> 16

#define SATmlib_u16(DST, vbl0)                                  \
  DST = ((mlib_s32)(vbl0 - sbt) >> 16) ^ 0x8000

#define SATmlib_s32(DST, vbl0)                                  \
  DST = vbl0

#else

#define SATmlib_u8(DST, vbl0)                                   \
  vbl0 -= sbt;                                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = ((mlib_s32) vbl0 >> 24) ^ 0x80

#define SATmlib_s16(DST, vbl0)                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = (mlib_s32)vbl0 >> 16

#define SATmlib_u16(DST, vbl0)                                  \
  vbl0 -= sbt;                                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = ((mlib_s32)vbl0 >> 16) ^ 0x8000

#define SATmlib_s32(DST, vbl0)                                  \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    vbl0 = MLIB_S32_MAX;                                        \
  if (vbl0 <= MLIB_S32_MIN)                                     \
    vbl0 = MLIB_S32_MIN;                                        \
  DST = (mlib_s32)vbl0

#endif

/***************************************************************/
#define SATmlib_f32(DST, vbl0)                                  \
  DST = (mlib_f32)vbl0

/***************************************************************/
#define SATmlib_d64(DST, vbl0)                                  \
  DST = vbl0

/***************************************************************/
#define MLIB_EDGE_ZERO_LINE(TYPE, Left, Right)                  \
  dp = (TYPE*)dbtb + chbnnels * Left;                           \
  dstLineEnd  = (TYPE*)dbtb + chbnnels * Right;                 \
                                                                \
  for (; dp < dstLineEnd; dp++) {                               \
    *dp = zero;                                                 \
  }

/***************************************************************/
#define MLIB_EDGE_NEAREST_LINE(TYPE, Left, Right)               \
  dp = (TYPE*)dbtb + chbnnels * Left;                           \
  size = Right - Left;                                          \
                                                                \
  for (j = 0; j < size; j++) {                                  \
    ySrc = Y >> MLIB_SHIFT;                                     \
    xSrc = X >> MLIB_SHIFT;                                     \
    sp = (TYPE*)lineAddr[ySrc] + xSrc * chbnnels;               \
                                                                \
    for (k = 0; k < chbnnels; k++) dp[k] = sp[k];               \
                                                                \
    Y += dY;                                                    \
    X += dX;                                                    \
    dp += chbnnels;                                             \
  }

/***************************************************************/
#define MLIB_EDGE_BL(TYPE, Left, Right)                                 \
  dp = (TYPE*)dbtb + chbnnels * Left;                                   \
  size = Right - Left;                                                  \
                                                                        \
  for (j = 0; j < size; j++) {                                          \
    ySrc = ((Y - 32768) >> MLIB_SHIFT);                                 \
    xSrc = ((X - 32768) >> MLIB_SHIFT);                                 \
                                                                        \
    t = ((X - 32768) & MLIB_MASK) * scble;                              \
    u = ((Y - 32768) & MLIB_MASK) * scble;                              \
                                                                        \
    xDeltb = (((xSrc + 1 - srcWidth )) >> MLIB_SIGN_SHIFT) & chbnnels;  \
    yDeltb = (((ySrc + 1 - srcHeight)) >> MLIB_SIGN_SHIFT) & srcStride; \
                                                                        \
    xFlbg = (xSrc >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    xSrc = xSrc + (1 & xFlbg);                                          \
    xDeltb = xDeltb &~ xFlbg;                                           \
                                                                        \
    yFlbg = (ySrc >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    ySrc = ySrc + (1 & yFlbg);                                          \
    yDeltb = yDeltb &~ yFlbg;                                           \
                                                                        \
    sp = (TYPE*)lineAddr[ySrc] + xSrc * chbnnels;                       \
                                                                        \
    for (k = 0; k < chbnnels; k++) {                                    \
      b00  = D64##TYPE(sp[0]);                                          \
      b01  = D64##TYPE(sp[xDeltb]);                                     \
      b10  = D64##TYPE(sp[yDeltb]);                                     \
      b11  = D64##TYPE(sp[yDeltb + xDeltb]);                            \
      pix0 = (b00 * (1 - t) + b01 * t) * (1 - u) +                      \
             (b10 * (1 - t) + b11 * t) * u;                             \
                                                                        \
      dp[k] = (TYPE)pix0;                                               \
      sp++;                                                             \
    }                                                                   \
                                                                        \
    X += dX;                                                            \
    Y += dY;                                                            \
    dp += chbnnels;                                                     \
  }

/***************************************************************/
#define LUT(k, ind) plut[chbnnels*sp[ind] + k]

/***************************************************************/
#define MLIB_EDGE_INDEX(ITYPE, DTYPE, size)                             \
  for (j = 0; j < size; j++) {                                          \
    ySrc = ((Y - 32768) >> MLIB_SHIFT);                                 \
    xSrc = ((X - 32768) >> MLIB_SHIFT);                                 \
                                                                        \
    t = ((X - 32768) & MLIB_MASK) * scble;                              \
    u = ((Y - 32768) & MLIB_MASK) * scble;                              \
                                                                        \
    xDeltb = (((xSrc + 1 - srcWidth )) >> MLIB_SIGN_SHIFT) & 1;         \
    yDeltb = (((ySrc + 1 - srcHeight)) >> MLIB_SIGN_SHIFT) & srcStride; \
                                                                        \
    xFlbg = (xSrc >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    xSrc = xSrc + (1 & xFlbg);                                          \
    xDeltb = xDeltb &~ xFlbg;                                           \
                                                                        \
    yFlbg = (ySrc >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                   \
    ySrc = ySrc + (1 & yFlbg);                                          \
    yDeltb = yDeltb &~ yFlbg;                                           \
                                                                        \
    sp = (ITYPE*)lineAddr[ySrc] + xSrc;                                 \
                                                                        \
    for (k = 0; k < chbnnels; k++) {                                    \
      b00  = LUT(k, 0);                                                 \
      b01  = LUT(k, xDeltb);                                            \
      b10  = LUT(k, yDeltb);                                            \
      b11  = LUT(k, yDeltb + xDeltb);                                   \
      pix0 = (b00 * (1 - t) + b01 * t) * (1 - u) +                      \
             (b10 * (1 - t) + b11 * t) * u;                             \
                                                                        \
      pbuff[k] = (mlib_s32)pix0;                                        \
    }                                                                   \
    pbuff += chbnnels;                                                  \
                                                                        \
    X += dX;                                                            \
    Y += dY;                                                            \
  }

/***************************************************************/
#define MLIB_EDGE_INDEX_u8i(ITYPE, Left, Right) {                              \
  mlib_u8  *pbuff = buff;                                                      \
                                                                               \
  size = Right - Left;                                                         \
                                                                               \
  MLIB_EDGE_INDEX(ITYPE, mlib_u8, size);                                       \
                                                                               \
  dp = (ITYPE*)dbtb + Left;                                                    \
                                                                               \
  if (chbnnels == 3) {                                                         \
    if (sizeof(ITYPE) == 1) {                                                  \
      mlib_ImbgeColorTrue2IndexLine_U8_U8_3 (buff, (void*)dp, size, colormbp); \
    } else {                                                                   \
      mlib_ImbgeColorTrue2IndexLine_U8_S16_3(buff, (void*)dp, size, colormbp); \
    }                                                                          \
  } else {                                                                     \
    if (sizeof(ITYPE) == 1) {                                                  \
      mlib_ImbgeColorTrue2IndexLine_U8_U8_4 (buff, (void*)dp, size, colormbp); \
    } else {                                                                   \
      mlib_ImbgeColorTrue2IndexLine_U8_S16_4(buff, (void*)dp, size, colormbp); \
    }                                                                          \
  }                                                                            \
}

/***************************************************************/
#define MLIB_EDGE_INDEX_s16i(ITYPE, Left, Right) {                              \
  mlib_s16 *pbuff = buff;                                                       \
                                                                                \
  size = Right - Left;                                                          \
                                                                                \
  MLIB_EDGE_INDEX(ITYPE, mlib_s16, size);                                       \
                                                                                \
  dp = (ITYPE*)dbtb + Left;                                                     \
                                                                                \
  if (chbnnels == 3) {                                                          \
    if (sizeof(ITYPE) == 1) {                                                   \
      mlib_ImbgeColorTrue2IndexLine_S16_U8_3 (buff, (void*)dp, size, colormbp); \
    } else {                                                                    \
      mlib_ImbgeColorTrue2IndexLine_S16_S16_3(buff, (void*)dp, size, colormbp); \
    }                                                                           \
  } else {                                                                      \
    if (sizeof(ITYPE) == 1) {                                                   \
      mlib_ImbgeColorTrue2IndexLine_S16_U8_4 (buff, (void*)dp, size, colormbp); \
    } else {                                                                    \
      mlib_ImbgeColorTrue2IndexLine_S16_S16_4(buff, (void*)dp, size, colormbp); \
    }                                                                           \
  }                                                                             \
}

/***************************************************************/
#define GET_FLT_TBL(X, xf0, xf1, xf2, xf3)                      \
  filterpos = ((X - 32768) >> flt_shift) & flt_mbsk;            \
  fptr = (mlib_f32 *) ((mlib_u8 *)flt_tbl + filterpos);         \
                                                                \
  xf0 = fptr[0];                                                \
  xf1 = fptr[1];                                                \
  xf2 = fptr[2];                                                \
  xf3 = fptr[3]

/***************************************************************/
#define GET_FLT_BC(X, xf0, xf1, xf2, xf3)                       \
  dx = ((X - 32768) & MLIB_MASK) * scble;                       \
  dx_2  = 0.5 * dx;                                             \
  dx2   = dx * dx;                                              \
  dx3_2 = dx_2 * dx2;                                           \
  dx3_3 = 3.0 * dx3_2;                                          \
                                                                \
  xf0 = dx2 - dx3_2 - dx_2;                                     \
  xf1 = dx3_3 - 2.5 * dx2 + 1.0;                                \
  xf2 = 2.0 * dx2 - dx3_3 + dx_2;                               \
  xf3 = dx3_2 - 0.5 * dx2

/***************************************************************/
#define GET_FLT_BC2(X, xf0, xf1, xf2, xf3)                      \
  dx =  ((X - 32768) & MLIB_MASK) * scble;                      \
  dx2   = dx  * dx;                                             \
  dx3_2 = dx  * dx2;                                            \
  dx3_3 = 2.0 * dx2;                                            \
                                                                \
  xf0 = - dx3_2 + dx3_3 - dx;                                   \
  xf1 =   dx3_2 - dx3_3 + 1.0;                                  \
  xf2 = - dx3_2 + dx2   + dx;                                   \
  xf3 =   dx3_2 - dx2

/***************************************************************/
#define CALC_SRC_POS(X, Y, chbnnels, srcStride)                                    \
  xSrc = ((X - 32768) >> MLIB_SHIFT);                                              \
  ySrc = ((Y - 32768) >> MLIB_SHIFT);                                              \
                                                                                   \
  xDeltb0 = ((~((xSrc - 1) >> MLIB_SIGN_SHIFT)) & (- chbnnels));                   \
  yDeltb0 = ((~((ySrc - 1) >> MLIB_SIGN_SHIFT)) & (- srcStride));                  \
  xDeltb1 = ((xSrc + 1 - srcWidth) >> MLIB_SIGN_SHIFT) & (chbnnels);               \
  yDeltb1 = ((ySrc + 1 - srcHeight) >> MLIB_SIGN_SHIFT) & (srcStride);             \
  xDeltb2 = xDeltb1 + (((xSrc + 2 - srcWidth) >> MLIB_SIGN_SHIFT) & (chbnnels));   \
  yDeltb2 = yDeltb1 + (((ySrc + 2 - srcHeight) >> MLIB_SIGN_SHIFT) & (srcStride)); \
                                                                                   \
  xFlbg = (xSrc >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                                \
  xSrc = xSrc + (1 & xFlbg);                                                       \
  xDeltb2 -= (xDeltb1 & xFlbg);                                                    \
  xDeltb1 = (xDeltb1 &~ xFlbg);                                                    \
                                                                                   \
  yFlbg = (ySrc >> (MLIB_SIGN_SHIFT - MLIB_SHIFT));                                \
  ySrc = ySrc + (1 & yFlbg);                                                       \
  yDeltb2  -= (yDeltb1 & yFlbg);                                                   \
  yDeltb1 = yDeltb1 &~ yFlbg

/***************************************************************/
#define MLIB_EDGE_BC_LINE(TYPE, Left, Right, GET_FILTER)        \
  dp = (TYPE*)dbtb + chbnnels * Left;                           \
  size = Right - Left;                                          \
                                                                \
  for (j = 0; j < size; j++) {                                  \
    GET_FILTER(X, xf0, xf1, xf2, xf3);                          \
    GET_FILTER(Y, yf0, yf1, yf2, yf3);                          \
                                                                \
    CALC_SRC_POS(X, Y, chbnnels, srcStride);                    \
                                                                \
    sp = (TYPE*)lineAddr[ySrc] + chbnnels*xSrc;                 \
                                                                \
    for (k = 0; k < chbnnels; k++) {                            \
      c0 = D64##TYPE(sp[yDeltb0 + xDeltb0]) * xf0 +             \
           D64##TYPE(sp[yDeltb0          ]) * xf1 +             \
           D64##TYPE(sp[yDeltb0 + xDeltb1]) * xf2 +             \
           D64##TYPE(sp[yDeltb0 + xDeltb2]) * xf3;              \
                                                                \
      c1 = D64##TYPE(sp[xDeltb0]) * xf0 +                       \
           D64##TYPE(sp[      0]) * xf1 +                       \
           D64##TYPE(sp[xDeltb1]) * xf2 +                       \
           D64##TYPE(sp[xDeltb2]) * xf3;                        \
                                                                \
      c2 = D64##TYPE(sp[yDeltb1 + xDeltb0]) * xf0 +             \
           D64##TYPE(sp[yDeltb1          ]) * xf1 +             \
           D64##TYPE(sp[yDeltb1 + xDeltb1]) * xf2 +             \
           D64##TYPE(sp[yDeltb1 + xDeltb2]) * xf3;              \
                                                                \
      c3 = D64##TYPE(sp[yDeltb2 + xDeltb0]) * xf0 +             \
           D64##TYPE(sp[yDeltb2          ]) * xf1 +             \
           D64##TYPE(sp[yDeltb2 + xDeltb1]) * xf2 +             \
           D64##TYPE(sp[yDeltb2 + xDeltb2]) * xf3;              \
                                                                \
      vbl0 = c0*yf0 + c1*yf1 + c2*yf2 + c3*yf3;                 \
                                                                \
      SAT##TYPE(dp[k], vbl0);                                   \
                                                                \
      sp++;                                                     \
    }                                                           \
                                                                \
    X += dX;                                                    \
    Y += dY;                                                    \
    dp += chbnnels;                                             \
  }

/***************************************************************/
#define MLIB_EDGE_BC_TBL(TYPE, Left, Right)                     \
  MLIB_EDGE_BC_LINE(TYPE, Left, Right, GET_FLT_TBL)

/***************************************************************/
#define MLIB_EDGE_BC(TYPE, Left, Right)                         \
  MLIB_EDGE_BC_LINE(TYPE, Left, Right, GET_FLT_BC)

/***************************************************************/
#define MLIB_EDGE_BC2(TYPE, Left, Right)                        \
  MLIB_EDGE_BC_LINE(TYPE, Left, Right, GET_FLT_BC2)

/***************************************************************/
#define MLIB_EDGE_INDEX_BC(ITYPE, DTYPE, size)                  \
  for (j = 0; j < size; j++) {                                  \
    GET_FLT_TBL(X, xf0, xf1, xf2, xf3);                         \
    GET_FLT_TBL(Y, yf0, yf1, yf2, yf3);                         \
                                                                \
    CALC_SRC_POS(X, Y, 1, srcStride);                           \
                                                                \
    sp = (ITYPE*)lineAddr[ySrc] + xSrc;                         \
                                                                \
    for (k = 0; k < chbnnels; k++) {                            \
      c0 = LUT(k, yDeltb0 + xDeltb0) * xf0 +                    \
           LUT(k, yDeltb0          ) * xf1 +                    \
           LUT(k, yDeltb0 + xDeltb1) * xf2 +                    \
           LUT(k, yDeltb0 + xDeltb2) * xf3;                     \
                                                                \
      c1 = LUT(k, xDeltb0) * xf0 +                              \
           LUT(k, 0      ) * xf1 +                              \
           LUT(k, xDeltb1) * xf2 +                              \
           LUT(k, xDeltb2) * xf3;                               \
                                                                \
      c2 = LUT(k, yDeltb1 + xDeltb0) * xf0 +                    \
           LUT(k, yDeltb1          ) * xf1 +                    \
           LUT(k, yDeltb1 + xDeltb1) * xf2 +                    \
           LUT(k, yDeltb1 + xDeltb2) * xf3;                     \
                                                                \
      c3 = LUT(k, yDeltb2 + xDeltb0) * xf0 +                    \
           LUT(k, yDeltb2          ) * xf1 +                    \
           LUT(k, yDeltb2 + xDeltb1) * xf2 +                    \
           LUT(k, yDeltb2 + xDeltb2) * xf3;                     \
                                                                \
      vbl0 = c0*yf0 + c1*yf1 + c2*yf2 + c3*yf3;                 \
                                                                \
      SAT##DTYPE(pbuff[k], vbl0);                               \
    }                                                           \
    pbuff += chbnnels;                                          \
                                                                \
    X += dX;                                                    \
    Y += dY;                                                    \
  }

/***************************************************************/
#define MLIB_PROCESS_EDGES_ZERO(TYPE) {                         \
  TYPE *dp, *dstLineEnd;                                        \
                                                                \
  for (i = yStbrtE; i < yStbrt; i++) {                          \
    xLeftE  = leftEdgesE[i];                                    \
    xRightE = rightEdgesE[i] + 1;                               \
    dbtb   += dstStride;                                        \
                                                                \
    MLIB_EDGE_ZERO_LINE(TYPE, xLeftE, xRightE);                 \
  }                                                             \
                                                                \
  for (; i <= yFinish; i++) {                                   \
    xLeftE  = leftEdgesE[i];                                    \
    xRightE = rightEdgesE[i] + 1;                               \
    xLeft   = leftEdges[i];                                     \
    xRight  = rightEdges[i] + 1;                                \
    dbtb   += dstStride;                                        \
                                                                \
    if (xLeft < xRight) {                                       \
      MLIB_EDGE_ZERO_LINE(TYPE, xLeftE, xLeft);                 \
    } else {                                                    \
      xRight = xLeftE;                                          \
    }                                                           \
                                                                \
    MLIB_EDGE_ZERO_LINE(TYPE, xRight, xRightE);                 \
  }                                                             \
                                                                \
  for (; i <= yFinishE; i++) {                                  \
    xLeftE  = leftEdgesE[i];                                    \
    xRightE = rightEdgesE[i] + 1;                               \
    dbtb   += dstStride;                                        \
                                                                \
    MLIB_EDGE_ZERO_LINE(TYPE, xLeftE, xRightE);                 \
  }                                                             \
}

/***************************************************************/
#define MLIB_PROCESS_EDGES(PROCESS_LINE, TYPE) {                \
  TYPE *sp, *dp;                                                \
  mlib_s32 k, size;                                             \
                                                                \
  for (i = yStbrtE; i < yStbrt; i++) {                          \
    xLeftE  = leftEdgesE[i];                                    \
    xRightE = rightEdgesE[i] + 1;                               \
    X       = xStbrtsE[i];                                      \
    Y       = yStbrtsE[i];                                      \
    dbtb   += dstStride;                                        \
                                                                \
    PROCESS_LINE(TYPE, xLeftE, xRightE);                        \
  }                                                             \
                                                                \
  for (; i <= yFinish; i++) {                                   \
    xLeftE  = leftEdgesE[i];                                    \
    xRightE = rightEdgesE[i] + 1;                               \
    xLeft   = leftEdges[i];                                     \
    xRight  = rightEdges[i] + 1;                                \
    X       = xStbrtsE[i];                                      \
    Y       = yStbrtsE[i];                                      \
    dbtb   += dstStride;                                        \
                                                                \
    if (xLeft < xRight) {                                       \
      PROCESS_LINE(TYPE, xLeftE, xLeft);                        \
    } else {                                                    \
      xRight = xLeftE;                                          \
    }                                                           \
                                                                \
    X = xStbrtsE[i] + dX * (xRight - xLeftE);                   \
    Y = yStbrtsE[i] + dY * (xRight - xLeftE);                   \
    PROCESS_LINE(TYPE, xRight, xRightE);                        \
  }                                                             \
                                                                \
  for (; i <= yFinishE; i++) {                                  \
    xLeftE  = leftEdgesE[i];                                    \
    xRightE = rightEdgesE[i] + 1;                               \
    X       = xStbrtsE[i];                                      \
    Y       = yStbrtsE[i];                                      \
    dbtb   += dstStride;                                        \
                                                                \
    PROCESS_LINE(TYPE, xLeftE, xRightE);                        \
  }                                                             \
}

/***************************************************************/
#define GET_EDGE_PARAMS_ZERO()                                  \
  mlib_imbge *dst = pbrbm -> dst;                               \
  mlib_s32  *leftEdges  = pbrbm -> leftEdges;                   \
  mlib_s32  *rightEdges = pbrbm -> rightEdges;                  \
  mlib_s32  *leftEdgesE  = pbrbm_e -> leftEdges;                \
  mlib_s32  *rightEdgesE = pbrbm_e -> rightEdges;               \
  mlib_type type      = mlib_ImbgeGetType(dst);                 \
  mlib_s32  chbnnels  = mlib_ImbgeGetChbnnels(dst);             \
  mlib_s32  dstStride = mlib_ImbgeGetStride(dst);               \
  mlib_s32  yStbrt    = pbrbm -> yStbrt;                        \
  mlib_s32  yFinish   = pbrbm -> yFinish;                       \
  mlib_s32  yStbrtE   = pbrbm_e -> yStbrt;                      \
  mlib_s32  yFinishE  = pbrbm_e -> yFinish;                     \
  mlib_u8   *dbtb     = pbrbm_e -> dstDbtb;                     \
  mlib_s32  xLeft, xRight, xLeftE, xRightE;                     \
  mlib_s32  i

/***************************************************************/
#define GET_EDGE_PARAMS_NN()                                    \
  GET_EDGE_PARAMS_ZERO();                                       \
  mlib_s32  *xStbrtsE = pbrbm_e -> xStbrts;                     \
  mlib_s32  *yStbrtsE = pbrbm_e -> yStbrts;                     \
  mlib_u8   **lineAddr = pbrbm -> lineAddr;                     \
  mlib_s32  dX = pbrbm_e -> dX;                                 \
  mlib_s32  dY = pbrbm_e -> dY;                                 \
  mlib_s32  xSrc, ySrc, X, Y;                                   \
  mlib_s32  j

/***************************************************************/
#define GET_EDGE_PARAMS()                                       \
  GET_EDGE_PARAMS_NN();                                         \
  mlib_imbge *src = pbrbm -> src;                               \
  mlib_s32  srcWidth  = mlib_ImbgeGetWidth(src);                \
  mlib_s32  srcHeight = mlib_ImbgeGetHeight(src);               \
  mlib_s32  srcStride = mlib_ImbgeGetStride(src)

/***************************************************************/
void mlib_ImbgeAffineEdgeZero(mlib_bffine_pbrbm *pbrbm,
                              mlib_bffine_pbrbm *pbrbm_e,
                              const void        *colormbp)
{
  GET_EDGE_PARAMS_ZERO();
  mlib_s32 zero = 0;

  if (colormbp != NULL) {
    zero = mlib_ImbgeGetLutOffset(colormbp);
  }

  switch (type) {
    cbse MLIB_BYTE:
      MLIB_PROCESS_EDGES_ZERO(mlib_u8);
      brebk;

    cbse MLIB_SHORT:
    cbse MLIB_USHORT:
      MLIB_PROCESS_EDGES_ZERO(mlib_s16);
      brebk;

    cbse MLIB_INT:
    cbse MLIB_FLOAT:
      MLIB_PROCESS_EDGES_ZERO(mlib_s32);
      brebk;

    cbse MLIB_DOUBLE:{
        mlib_d64 zero = 0;
        MLIB_PROCESS_EDGES_ZERO(mlib_d64);
        brebk;
      }
  defbult:
    /* Imbge type MLIB_BIT is not used in jbvb, so we cbn ignore it. */
    brebk;
  }
}

/***************************************************************/
void mlib_ImbgeAffineEdgeNebrest(mlib_bffine_pbrbm *pbrbm,
                                 mlib_bffine_pbrbm *pbrbm_e)
{
  GET_EDGE_PARAMS_NN();

  switch (type) {
    cbse MLIB_BYTE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_u8);
      brebk;

    cbse MLIB_SHORT:
    cbse MLIB_USHORT:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_s16);
      brebk;

    cbse MLIB_INT:
    cbse MLIB_FLOAT:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_s32);
      brebk;

    cbse MLIB_DOUBLE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_NEAREST_LINE, mlib_d64);
      brebk;
  defbult:
    /* Imbge type MLIB_BIT is not used in jbvb, so we cbn ignore it. */
    brebk;
  }
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffineEdgeExtend_BL(mlib_bffine_pbrbm *pbrbm,
                                          mlib_bffine_pbrbm *pbrbm_e,
                                          const void        *colormbp)
{
  GET_EDGE_PARAMS();
  mlib_d64 scble = 1.0 / (mlib_d64) MLIB_PREC;
  mlib_s32 xDeltb, yDeltb, xFlbg, yFlbg;
  mlib_d64 t, u, pix0;
  mlib_d64 b00, b01, b10, b11;

  if (colormbp != NULL) {
    mlib_s32 mbx_xsize = pbrbm_e->mbx_xsize;
    mlib_type ltype = mlib_ImbgeGetLutType(colormbp);
    mlib_d64 *plut = (mlib_d64 *) mlib_ImbgeGetLutDoubleDbtb(colormbp);
    void *buff;

    chbnnels = mlib_ImbgeGetLutChbnnels(colormbp);
    plut -= chbnnels * mlib_ImbgeGetLutOffset(colormbp);

    if (mbx_xsize == 0) {
      return MLIB_SUCCESS;
    }

    if (ltype == MLIB_BYTE) {
      buff = mlib_mblloc(chbnnels * mbx_xsize);
    }
    else if (ltype == MLIB_SHORT) {
      buff = mlib_mblloc(chbnnels * mbx_xsize * sizeof(mlib_s16));
    } else {
      /* Unsupported type of lookup tbble. Report b fbilure */
      return MLIB_FAILURE;
    }

    if (buff == NULL)
      return MLIB_FAILURE;

    switch (ltype) {
      cbse MLIB_BYTE:
        switch (type) {
          cbse MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_u8);
            brebk;

          cbse MLIB_SHORT:
            srcStride >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_s16);
            brebk;
        defbult:
          /* Incompbtible imbge type. Ignore it for now. */
          brebk;
        }

        brebk;

      cbse MLIB_SHORT:
        switch (type) {
          cbse MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_u8);
            brebk;

          cbse MLIB_SHORT:
            srcStride >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_s16);
            brebk;
        defbult:
          /* Incompbtible imbge type. Ignore it for now. */
          brebk;
        }

        brebk;
    defbult:
      /* Unsupported type of lookup tbble.
       * Cbn not be here due to check on line 685,
       * so just ignore it.
       */
      brebk;
    }

    mlib_free(buff);

    return MLIB_SUCCESS;
  }

  switch (type) {
    cbse MLIB_BYTE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_u8);
      brebk;

    cbse MLIB_SHORT:
      srcStride >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_s16);
      brebk;

    cbse MLIB_USHORT:
      srcStride >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_u16);
      brebk;

    cbse MLIB_INT:
      srcStride >>= 2;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_s32);
      brebk;

    cbse MLIB_FLOAT:
      srcStride >>= 2;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_f32);
      brebk;

    cbse MLIB_DOUBLE:
      srcStride >>= 3;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BL, mlib_d64);
      brebk;

  defbult:
    /* Imbge type MLIB_BIT is not supported, ignore it. */
    brebk;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  MLIB_EDGE_INDEX
#define MLIB_EDGE_INDEX MLIB_EDGE_INDEX_BC

mlib_stbtus mlib_ImbgeAffineEdgeExtend_BC(mlib_bffine_pbrbm *pbrbm,
                                          mlib_bffine_pbrbm *pbrbm_e,
                                          const void        *colormbp)
{
  GET_EDGE_PARAMS();
  mlib_d64 scble = 1.0 / (mlib_d64) MLIB_PREC;
  mlib_s32 xFlbg, yFlbg;
  mlib_d64 dx, dx_2, dx2, dx3_2, dx3_3;
  mlib_d64 xf0, xf1, xf2, xf3;
  mlib_d64 yf0, yf1, yf2, yf3;
  mlib_d64 c0, c1, c2, c3, vbl0;
  mlib_type ltype;
  mlib_filter filter = pbrbm->filter;
  mlib_f32 *fptr;
  mlib_f32 const *flt_tbl;
  mlib_s32 filterpos, flt_shift, flt_mbsk;
  mlib_s32 xDeltb0, xDeltb1, xDeltb2;
  mlib_s32 yDeltb0, yDeltb1, yDeltb2;
  mlib_d64 sbt;

  ltype = (colormbp != NULL) ? mlib_ImbgeGetLutType(colormbp) : type;

  if (ltype == MLIB_BYTE) {
    flt_shift = FLT_SHIFT_U8;
    flt_mbsk = FLT_MASK_U8;
    flt_tbl = (filter == MLIB_BICUBIC) ? mlib_filters_u8f_bc : mlib_filters_u8f_bc2;
    sbt = (mlib_d64) 0x7F800000;                           /* sbturbtion for U8 */
  }
  else {
    flt_shift = FLT_SHIFT_S16;
    flt_mbsk = FLT_MASK_S16;
    flt_tbl = (filter == MLIB_BICUBIC) ? mlib_filters_s16f_bc : mlib_filters_s16f_bc2;
    sbt = (mlib_d64) 0x7FFF8000;                           /* sbturbtion for U16 */
  }

  if (colormbp != NULL) {
    mlib_s32 mbx_xsize = pbrbm_e->mbx_xsize;
    mlib_d64 *plut = (mlib_d64 *) mlib_ImbgeGetLutDoubleDbtb(colormbp);
    void *buff;

    chbnnels = mlib_ImbgeGetLutChbnnels(colormbp);
    plut -= chbnnels * mlib_ImbgeGetLutOffset(colormbp);

    if (mbx_xsize == 0) {
      return MLIB_SUCCESS;
    }

    if (ltype == MLIB_BYTE) {
      buff = mlib_mblloc(chbnnels * mbx_xsize);
    }
    else if (ltype == MLIB_SHORT) {
      buff = mlib_mblloc(chbnnels * mbx_xsize * sizeof(mlib_s16));
    } else {
      /* Unsupported type of lookup tbble. */
      return MLIB_FAILURE;
    }

    if (buff == NULL)
      return MLIB_FAILURE;

    switch (ltype) {
      cbse MLIB_BYTE:
        switch (type) {
          cbse MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_u8);
            brebk;

          cbse MLIB_SHORT:
            srcStride >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_u8i, mlib_s16);
            brebk;
        defbult:
          /* Ignore incombtible imbge type. */
          brebk;
        }

        brebk;

      cbse MLIB_SHORT:
        switch (type) {
          cbse MLIB_BYTE:
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_u8);
            brebk;

          cbse MLIB_SHORT:
            srcStride >>= 1;
            MLIB_PROCESS_EDGES(MLIB_EDGE_INDEX_s16i, mlib_s16);
            brebk;
        defbult:
          /* Ignore incombtible imbge type. */
          brebk;
        }

        brebk;

    defbult:
      /* Unsupported type of lookup tbble.
       * Cbn not be here due to check on line 836,
       * so just ignore it.
       */
      brebk;
    }

    mlib_free(buff);

    return MLIB_SUCCESS;
  }

  switch (type) {
    cbse MLIB_BYTE:
      MLIB_PROCESS_EDGES(MLIB_EDGE_BC_TBL, mlib_u8);
      brebk;

    cbse MLIB_SHORT:
      srcStride >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BC_TBL, mlib_s16);
      brebk;

    cbse MLIB_USHORT:
      srcStride >>= 1;
      MLIB_PROCESS_EDGES(MLIB_EDGE_BC_TBL, mlib_u16);
      brebk;

    cbse MLIB_INT:
      srcStride >>= 2;

      if (filter == MLIB_BICUBIC) {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC, mlib_s32);
      }
      else {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC2, mlib_s32);
      }

      brebk;

    cbse MLIB_FLOAT:
      srcStride >>= 2;

      if (filter == MLIB_BICUBIC) {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC, mlib_f32);
      }
      else {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC2, mlib_f32);
      }

      brebk;

    cbse MLIB_DOUBLE:
      srcStride >>= 3;

      if (filter == MLIB_BICUBIC) {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC, mlib_d64);
      }
      else {
        MLIB_PROCESS_EDGES(MLIB_EDGE_BC2, mlib_d64);
      }

      brebk;

  defbult:
    /* Ignore unsupported imbge type MLIB_BIT */
    brebk;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
