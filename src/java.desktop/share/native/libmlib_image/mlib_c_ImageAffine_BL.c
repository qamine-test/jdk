/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      mlib_ImbgeAffine_u8_1ch_bl
 *      mlib_ImbgeAffine_u8_2ch_bl
 *      mlib_ImbgeAffine_u8_3ch_bl
 *      mlib_ImbgeAffine_u8_4ch_bl
 *        - imbge bffine trbnsformbtion with Bilinebr filtering
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeAffine_u8_?ch_bl(mlib_s32 *leftEdges,
 *                                             mlib_s32 *rightEdges,
 *                                             mlib_s32 *xStbrts,
 *                                             mlib_s32 *yStbrts,
 *                                             mlib_s32 *sides,
 *                                             mlib_u8  *dstDbtb,
 *                                             mlib_u8  **lineAddr,
 *                                             mlib_s32 dstYStride,
 *                                             mlib_s32 is_bffine,
 *                                             mlib_s32 srcYStride)
 *
 * ARGUMENTS
 *      leftEdges  brrby[dstHeight] of xLeft coordinbtes
 *      RightEdges brrby[dstHeight] of xRight coordinbtes
 *      xStbrts    brrby[dstHeight] of xStbrt * 65536 coordinbtes
 *      yStbrts    brrby[dstHeight] of yStbrt * 65536 coordinbtes
 *      sides      output brrby[4]. sides[0] is yStbrt, sides[1] is yFinish,
 *                 sides[2] is dx * 65536, sides[3] is dy * 65536
 *      dstDbtb    pointer to the first pixel on (yStbrt - 1) line
 *      lineAddr   brrby[srcHeight] of pointers to the first pixel on
 *                 the corresponding lines
 *      dstYStride stride of destinbtion imbge
 *      is_bffine  indicbtor (Affine - GridWbrp)
 *      srcYStride stride of source imbge
 *
 * DESCRIPTION
 *      The functions step blong the lines from xLeft to xRight bnd bpply
 *      the bilinebr filtering.
 *
 */

#include "mlib_ImbgeAffine.h"

/***************************************************************/
#define DTYPE  mlib_u8
#define FTYPE  mlib_f32

/***************************************************************/
#define TTYPE    mlib_f32
#define I2F(x)   mlib_U82F32[x]
#define ROUND(x) ((x) + 0.5f)

#define FUN_NAME(CHAN) mlib_ImbgeAffine_u8_##CHAN##_bl

/***************************************************************/
#ifdef __spbrc /* for SPARC, using flobting-point multiplies is fbster */

/***************************************************************/
#define GET_POINTERS(ind)                                       \
  fdx = (FTYPE)(X & MLIB_MASK) * scble;                         \
  fdy = (FTYPE)(Y & MLIB_MASK) * scble;                         \
  ySrc = MLIB_POINTER_SHIFT(Y);  Y += dY;                       \
  xSrc = X >> MLIB_SHIFT;  X += dX;                             \
  srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + ind * xSrc;  \
  srcPixelPtr2 = (DTYPE *)((mlib_u8 *)srcPixelPtr + srcYStride)

/***************************************************************/
#define COUNT(ind)                                              \
  pix0_##ind = b00_##ind + fdy * (b10_##ind - b00_##ind);       \
  pix1_##ind = b01_##ind + fdy * (b11_##ind - b01_##ind);       \
  res##ind = ROUND(pix0_##ind + fdx * (pix1_##ind - pix0_##ind))

/***************************************************************/
#define LOAD(ind, ind1, ind2)                                   \
  b00_##ind = I2F(srcPixelPtr[ind1]);                           \
  b01_##ind = I2F(srcPixelPtr[ind2]);                           \
  b10_##ind = I2F(srcPixelPtr2[ind1]);                          \
  b11_##ind = I2F(srcPixelPtr2[ind2])

/***************************************************************/
mlib_stbtus FUN_NAME(1ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;
  FTYPE scble = (FTYPE) 1.0 / MLIB_PREC;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE fdx, fdy;
    TTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE pix0_0, pix1_0, res0;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

    GET_POINTERS(1);
    LOAD(0, 0, 1);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr++) {
      COUNT(0);
      GET_POINTERS(1);
      LOAD(0, 0, 1);
      dstPixelPtr[0] = (DTYPE) res0;
    }

    COUNT(0);
    dstPixelPtr[0] = (DTYPE) res0;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(2ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;
  FTYPE scble = (FTYPE) 1.0 / MLIB_PREC;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE fdx, fdy;
    TTYPE b00_0, b01_0, b10_0, b11_0;
    TTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE pix0_0, pix1_0, res0;
    FTYPE pix0_1, pix1_1, res1;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

    GET_POINTERS(2);
    LOAD(0, 0, 2);
    LOAD(1, 1, 3);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 2) {
      COUNT(0);
      COUNT(1);
      GET_POINTERS(2);
      LOAD(0, 0, 2);
      LOAD(1, 1, 3);
      dstPixelPtr[0] = (DTYPE) res0;
      dstPixelPtr[1] = (DTYPE) res1;
    }

    COUNT(0);
    COUNT(1);
    dstPixelPtr[0] = (DTYPE) res0;
    dstPixelPtr[1] = (DTYPE) res1;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;
  FTYPE scble = (FTYPE) 1.0 / MLIB_PREC;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE fdx, fdy;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE b00_2, b01_2, b10_2, b11_2;
    FTYPE pix0_0, pix1_0, res0;
    FTYPE pix0_1, pix1_1, res1;
    FTYPE pix0_2, pix1_2, res2;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

    GET_POINTERS(3);
    LOAD(0, 0, 3);
    LOAD(1, 1, 4);
    LOAD(2, 2, 5);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 3) {
      COUNT(0);
      COUNT(1);
      COUNT(2);
      GET_POINTERS(3);
      LOAD(0, 0, 3);
      LOAD(1, 1, 4);
      LOAD(2, 2, 5);
      dstPixelPtr[0] = (DTYPE) res0;
      dstPixelPtr[1] = (DTYPE) res1;
      dstPixelPtr[2] = (DTYPE) res2;
    }

    COUNT(0);
    COUNT(1);
    COUNT(2);
    dstPixelPtr[0] = (DTYPE) res0;
    dstPixelPtr[1] = (DTYPE) res1;
    dstPixelPtr[2] = (DTYPE) res2;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;
  FTYPE scble = (FTYPE) 1.0 / MLIB_PREC;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE fdx, fdy;
    TTYPE b00_0, b01_0, b10_0, b11_0;
    TTYPE b00_1, b01_1, b10_1, b11_1;
    TTYPE b00_2, b01_2, b10_2, b11_2;
    TTYPE b00_3, b01_3, b10_3, b11_3;
    FTYPE pix0_0, pix1_0, res0;
    FTYPE pix0_1, pix1_1, res1;
    FTYPE pix0_2, pix1_2, res2;
    FTYPE pix0_3, pix1_3, res3;

    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

    GET_POINTERS(4);
    LOAD(0, 0, 4);
    LOAD(1, 1, 5);
    LOAD(2, 2, 6);
    LOAD(3, 3, 7);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 4) {
      COUNT(0);
      COUNT(1);
      COUNT(2);
      COUNT(3);
      GET_POINTERS(4);
      LOAD(0, 0, 4);
      LOAD(1, 1, 5);
      LOAD(2, 2, 6);
      LOAD(3, 3, 7);
      dstPixelPtr[0] = (DTYPE) res0;
      dstPixelPtr[1] = (DTYPE) res1;
      dstPixelPtr[2] = (DTYPE) res2;
      dstPixelPtr[3] = (DTYPE) res3;
    }

    COUNT(0);
    COUNT(1);
    COUNT(2);
    COUNT(3);
    dstPixelPtr[0] = (DTYPE) res0;
    dstPixelPtr[1] = (DTYPE) res1;
    dstPixelPtr[2] = (DTYPE) res2;
    dstPixelPtr[3] = (DTYPE) res3;
  }

  return MLIB_SUCCESS;
}

#else       /* for x86, using integer multiplies is fbster */

/* for SHORT/USHORT decrebse MLIB_SHIFT due to
 * overflow in multiplies like fdy * (b10 - b00)
 */
/*
#undef  MLIB_SHIFT
#define MLIB_SHIFT  15
*/

#define MLIB_ROUND   (1 << (MLIB_SHIFT - 1))

/***************************************************************/
#define GET_POINTERS(ind)                                        \
  fdx = X & MLIB_MASK;                                           \
  fdy = Y & MLIB_MASK;                                           \
  ySrc = MLIB_POINTER_SHIFT(Y);                                  \
  xSrc = X >> MLIB_SHIFT;                                        \
  srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + ind * xSrc;   \
  srcPixelPtr2 = (DTYPE *)((mlib_u8 *)srcPixelPtr + srcYStride); \
  X += dX;                                                       \
  Y += dY

/***************************************************************/
#define COUNT(ind)                                                                       \
  pix0_##ind = b00_##ind + ((fdy * (b10_##ind - b00_##ind) + MLIB_ROUND) >> MLIB_SHIFT); \
  pix1_##ind = b01_##ind + ((fdy * (b11_##ind - b01_##ind) + MLIB_ROUND) >> MLIB_SHIFT); \
  res##ind = pix0_##ind + ((fdx * (pix1_##ind - pix0_##ind) + MLIB_ROUND) >> MLIB_SHIFT)

/***************************************************************/
#define LOAD(ind, ind1, ind2)                                   \
  b00_##ind = srcPixelPtr[ind1];                                \
  b01_##ind = srcPixelPtr[ind2];                                \
  b10_##ind = srcPixelPtr2[ind1];                               \
  b11_##ind = srcPixelPtr2[ind2]

/***************************************************************/
mlib_stbtus FUN_NAME(1ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;

#if MLIB_SHIFT == 15
  dX = (dX + 1) >> 1;
  dY = (dY + 1) >> 1;
#endif /* MLIB_SHIFT == 15 */

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 fdx, fdy;
    mlib_s32 b00_0, b01_0, b10_0, b11_0;
    mlib_s32 pix0_0, pix1_0, res0;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;
#if MLIB_SHIFT == 15
    X = X >> 1;
    Y = Y >> 1;
#endif /* MLIB_SHIFT == 15 */

    GET_POINTERS(1);
    LOAD(0, 0, 1);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr++) {
      COUNT(0);
      GET_POINTERS(1);
      LOAD(0, 0, 1);
      dstPixelPtr[0] = (DTYPE) res0;
    }

    COUNT(0);
    dstPixelPtr[0] = (DTYPE) res0;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(2ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;

#if MLIB_SHIFT == 15
  dX = (dX + 1) >> 1;
  dY = (dY + 1) >> 1;
#endif /* MLIB_SHIFT == 15 */

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 fdx, fdy;
    mlib_s32 b00_0, b01_0, b10_0, b11_0;
    mlib_s32 b00_1, b01_1, b10_1, b11_1;
    mlib_s32 pix0_0, pix1_0, res0;
    mlib_s32 pix0_1, pix1_1, res1;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;
#if MLIB_SHIFT == 15
    X = X >> 1;
    Y = Y >> 1;
#endif /* MLIB_SHIFT == 15 */

    GET_POINTERS(2);
    LOAD(0, 0, 2);
    LOAD(1, 1, 3);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 2) {
      COUNT(0);
      COUNT(1);
      GET_POINTERS(2);
      LOAD(0, 0, 2);
      LOAD(1, 1, 3);
      dstPixelPtr[0] = (DTYPE) res0;
      dstPixelPtr[1] = (DTYPE) res1;
    }

    COUNT(0);
    COUNT(1);
    dstPixelPtr[0] = (DTYPE) res0;
    dstPixelPtr[1] = (DTYPE) res1;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;

#if MLIB_SHIFT == 15
  dX = (dX + 1) >> 1;
  dY = (dY + 1) >> 1;
#endif /* MLIB_SHIFT == 15 */

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 fdx, fdy;
    mlib_s32 b00_0, b01_0, b10_0, b11_0;
    mlib_s32 b00_1, b01_1, b10_1, b11_1;
    mlib_s32 b00_2, b01_2, b10_2, b11_2;
    mlib_s32 pix0_0, pix1_0, res0;
    mlib_s32 pix0_1, pix1_1, res1;
    mlib_s32 pix0_2, pix1_2, res2;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;
#if MLIB_SHIFT == 15
    X = X >> 1;
    Y = Y >> 1;
#endif /* MLIB_SHIFT == 15 */

    GET_POINTERS(3);
    LOAD(0, 0, 3);
    LOAD(1, 1, 4);
    LOAD(2, 2, 5);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 3) {
      COUNT(0);
      COUNT(1);
      COUNT(2);
      GET_POINTERS(3);
      LOAD(0, 0, 3);
      LOAD(1, 1, 4);
      LOAD(2, 2, 5);
      dstPixelPtr[0] = (DTYPE) res0;
      dstPixelPtr[1] = (DTYPE) res1;
      dstPixelPtr[2] = (DTYPE) res2;
    }

    COUNT(0);
    COUNT(1);
    COUNT(2);
    dstPixelPtr[0] = (DTYPE) res0;
    dstPixelPtr[1] = (DTYPE) res1;
    dstPixelPtr[2] = (DTYPE) res2;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  DTYPE *srcPixelPtr2;

#if MLIB_SHIFT == 15
  dX = (dX + 1) >> 1;
  dY = (dY + 1) >> 1;
#endif /* MLIB_SHIFT == 15 */

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 fdx, fdy;
    mlib_s32 b00_0, b01_0, b10_0, b11_0;
    mlib_s32 b00_1, b01_1, b10_1, b11_1;
    mlib_s32 b00_2, b01_2, b10_2, b11_2;
    mlib_s32 b00_3, b01_3, b10_3, b11_3;
    mlib_s32 pix0_0, pix1_0, res0;
    mlib_s32 pix0_1, pix1_1, res1;
    mlib_s32 pix0_2, pix1_2, res2;
    mlib_s32 pix0_3, pix1_3, res3;

    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;
#if MLIB_SHIFT == 15
    X = X >> 1;
    Y = Y >> 1;
#endif /* MLIB_SHIFT == 15 */

    GET_POINTERS(4);
    LOAD(0, 0, 4);
    LOAD(1, 1, 5);
    LOAD(2, 2, 6);
    LOAD(3, 3, 7);

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 4) {
      COUNT(0);
      COUNT(1);
      COUNT(2);
      COUNT(3);
      GET_POINTERS(4);
      LOAD(0, 0, 4);
      LOAD(1, 1, 5);
      LOAD(2, 2, 6);
      LOAD(3, 3, 7);
      dstPixelPtr[0] = (DTYPE) res0;
      dstPixelPtr[1] = (DTYPE) res1;
      dstPixelPtr[2] = (DTYPE) res2;
      dstPixelPtr[3] = (DTYPE) res3;
    }

    COUNT(0);
    COUNT(1);
    COUNT(2);
    COUNT(3);
    dstPixelPtr[0] = (DTYPE) res0;
    dstPixelPtr[1] = (DTYPE) res1;
    dstPixelPtr[2] = (DTYPE) res2;
    dstPixelPtr[3] = (DTYPE) res3;
  }

  return MLIB_SUCCESS;
}

#endif /* __spbrc ( for SPARC, using flobting-point multiplies is fbster ) */

/***************************************************************/
