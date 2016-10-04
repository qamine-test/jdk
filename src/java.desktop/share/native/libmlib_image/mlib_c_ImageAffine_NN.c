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
 *      mlib_ImbgeAffine_u8_1ch_nn
 *      mlib_ImbgeAffine_u8_2ch_nn
 *      mlib_ImbgeAffine_u8_3ch_nn
 *      mlib_ImbgeAffine_u8_4ch_nn
 *      mlib_ImbgeAffine_s16_1ch_nn
 *      mlib_ImbgeAffine_s16_2ch_nn
 *      mlib_ImbgeAffine_s16_3ch_nn
 *      mlib_ImbgeAffine_s16_4ch_nn
 *        - imbge bffine trbnsformbtion with Nebrest Neighbor filtering
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeAffine_[u8|s16]_?ch_nn(mlib_s32 *leftEdges,
 *                                                   mlib_s32 *rightEdges,
 *                                                   mlib_s32 *xStbrts,
 *                                                   mlib_s32 *yStbrts,
 *                                                   mlib_s32 *sides,
 *                                                   mlib_u8  *dstDbtb,
 *                                                   mlib_u8  **lineAddr,
 *                                                   mlib_s32 dstYStride,
 *                                                   mlib_s32 is_bffine)
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
 *
 * DESCRIPTION
 *      The functions step blong the lines from xLeft to xRight bnd get the
 *      nebrest pixel vblues bs being with the following coordinbtes
 *      ((xStbrt - (i - xLeft) * dx) >> 16, (yStbrt - (i - xLeft) * dy) >> 16)
 *
 */

#include "mlib_ImbgeAffine.h"

/***************************************************************/
#undef  DTYPE
#define DTYPE mlib_u8

mlib_stbtus mlib_ImbgeAffine_u8_1ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    DTYPE pix0;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr <= dstLineEnd; dstPixelPtr++) {
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc);
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      pix0 = srcPixelPtr[xSrc];
      dstPixelPtr[0] = pix0;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_u8_2ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    DTYPE pix0, pix1;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 2 * xSrc;
    pix0 = srcPixelPtr[0];
    pix1 = srcPixelPtr[1];
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 2) {
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 2 * xSrc;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      dstPixelPtr[0] = pix0;
      dstPixelPtr[1] = pix1;
      pix0 = srcPixelPtr[0];
      pix1 = srcPixelPtr[1];
    }

    dstPixelPtr[0] = pix0;
    dstPixelPtr[1] = pix1;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_u8_3ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    DTYPE pix0, pix1, pix2;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 3 * xSrc;
    pix0 = srcPixelPtr[0];
    pix1 = srcPixelPtr[1];
    pix2 = srcPixelPtr[2];
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 3) {
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 3 * xSrc;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      dstPixelPtr[0] = pix0;
      dstPixelPtr[1] = pix1;
      dstPixelPtr[2] = pix2;
      pix0 = srcPixelPtr[0];
      pix1 = srcPixelPtr[1];
      pix2 = srcPixelPtr[2];
    }

    dstPixelPtr[0] = pix0;
    dstPixelPtr[1] = pix1;
    dstPixelPtr[2] = pix2;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_u8_4ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    DTYPE pix0, pix1, pix2, pix3;
    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 4 * xSrc;
    pix0 = srcPixelPtr[0];
    pix1 = srcPixelPtr[1];
    pix2 = srcPixelPtr[2];
    pix3 = srcPixelPtr[3];
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 4) {
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 4 * xSrc;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      dstPixelPtr[0] = pix0;
      dstPixelPtr[1] = pix1;
      dstPixelPtr[2] = pix2;
      dstPixelPtr[3] = pix3;
      pix0 = srcPixelPtr[0];
      pix1 = srcPixelPtr[1];
      pix2 = srcPixelPtr[2];
      pix3 = srcPixelPtr[3];
    }

    dstPixelPtr[0] = pix0;
    dstPixelPtr[1] = pix1;
    dstPixelPtr[2] = pix2;
    dstPixelPtr[3] = pix3;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  DTYPE
#define DTYPE mlib_u16

mlib_stbtus mlib_ImbgeAffine_s16_1ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 pix0;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc);
    pix0 = srcPixelPtr[xSrc];
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr++) {
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc);
      dstPixelPtr[0] = pix0;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      pix0 = srcPixelPtr[xSrc];
    }

    dstPixelPtr[0] = pix0;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_s16_2ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 pix0, pix1;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 2 * xSrc;
    pix0 = srcPixelPtr[0];
    pix1 = srcPixelPtr[1];
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 2) {
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 2 * xSrc;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      dstPixelPtr[0] = pix0;
      dstPixelPtr[1] = pix1;
      pix0 = srcPixelPtr[0];
      pix1 = srcPixelPtr[1];
    }

    dstPixelPtr[0] = pix0;
    dstPixelPtr[1] = pix1;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_s16_3ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 pix0, pix1, pix2;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 3 * xSrc;
    pix0 = srcPixelPtr[0];
    pix1 = srcPixelPtr[1];
    pix2 = srcPixelPtr[2];
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 3) {
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 3 * xSrc;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      dstPixelPtr[0] = pix0;
      dstPixelPtr[1] = pix1;
      dstPixelPtr[2] = pix2;
      pix0 = srcPixelPtr[0];
      pix1 = srcPixelPtr[1];
      pix2 = srcPixelPtr[2];
    }

    dstPixelPtr[0] = pix0;
    dstPixelPtr[1] = pix1;
    dstPixelPtr[2] = pix2;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_s16_4ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_NN();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 pix0, pix1, pix2, pix3;
    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 4 * xSrc;
    pix0 = srcPixelPtr[0];
    pix1 = srcPixelPtr[1];
    pix2 = srcPixelPtr[2];
    pix3 = srcPixelPtr[3];
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 4) {
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 4 * xSrc;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      dstPixelPtr[0] = pix0;
      dstPixelPtr[1] = pix1;
      dstPixelPtr[2] = pix2;
      dstPixelPtr[3] = pix3;
      pix0 = srcPixelPtr[0];
      pix1 = srcPixelPtr[1];
      pix2 = srcPixelPtr[2];
      pix3 = srcPixelPtr[3];
    }

    dstPixelPtr[0] = pix0;
    dstPixelPtr[1] = pix1;
    dstPixelPtr[2] = pix2;
    dstPixelPtr[3] = pix3;
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
