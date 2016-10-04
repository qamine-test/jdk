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
 * FUNCTION
 *   Internbl functions for mlib_ImbgeAffine with bilinebr filtering.
 */

#include "mlib_ImbgeAffine.h"

/***************************************************************/
#define DTYPE  mlib_s32
#define FTYPE  mlib_d64
/* hbndle FTYPE to DTYPE conversion like in mlib_ImbgeAffine_BC_S32.c */
#define STORE(res, x)  SAT_32(res, x)

#define FUN_NAME(CHAN) mlib_ImbgeAffine_s32_##CHAN##_bl

/***************************************************************/
mlib_stbtus FUN_NAME(1ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  FTYPE scble = ONE / MLIB_PREC;
  mlib_s32 srcYStride1;

  srcYStride /= sizeof(DTYPE);
  srcYStride1 = srcYStride + 1;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE pix0;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

    t = (X & MLIB_MASK) * scble;
    u = (Y & MLIB_MASK) * scble;
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + xSrc;
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srcPixelPtr[0];
    b01_0 = srcPixelPtr[1];
    b10_0 = srcPixelPtr[srcYStride];
    b11_0 = srcPixelPtr[srcYStride1];

    for (; dstPixelPtr < dstLineEnd; dstPixelPtr++) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      t = (X & MLIB_MASK) * scble;
      u = (Y & MLIB_MASK) * scble;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      srcPixelPtr = *(DTYPE **) ((mlib_u8 *) lineAddr + ySrc) + xSrc;
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b00_0 = srcPixelPtr[0];
      b01_0 = srcPixelPtr[1];
      b10_0 = srcPixelPtr[srcYStride];
      b11_0 = srcPixelPtr[srcYStride1];
      STORE(dstPixelPtr[0], pix0);
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    STORE(dstPixelPtr[0], pix0);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(2ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  FTYPE scble = ONE / MLIB_PREC;

  for (j = yStbrt; j <= yFinish; j++) {
    DTYPE *srcPixelPtr2;
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE pix0, pix1;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

    t = (X & MLIB_MASK) * scble;
    u = (Y & MLIB_MASK) * scble;
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 2 * xSrc;
    srcPixelPtr2 = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srcPixelPtr[0];
    b00_1 = srcPixelPtr[1];
    b01_0 = srcPixelPtr[2];
    b01_1 = srcPixelPtr[3];
    b10_0 = srcPixelPtr2[0];
    b10_1 = srcPixelPtr2[1];
    b11_0 = srcPixelPtr2[2];
    b11_1 = srcPixelPtr2[3];

    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 2) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
      t = (X & MLIB_MASK) * scble;
      u = (Y & MLIB_MASK) * scble;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 2 * xSrc;
      srcPixelPtr2 = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b01_0 = srcPixelPtr[2];
      b01_1 = srcPixelPtr[3];
      b00_0 = srcPixelPtr[0];
      b00_1 = srcPixelPtr[1];
      b10_0 = srcPixelPtr2[0];
      b10_1 = srcPixelPtr2[1];
      b11_0 = srcPixelPtr2[2];
      b11_1 = srcPixelPtr2[3];
      STORE(dstPixelPtr[0], pix0);
      STORE(dstPixelPtr[1], pix1);
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
    STORE(dstPixelPtr[0], pix0);
    STORE(dstPixelPtr[1], pix1);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  FTYPE scble = ONE / MLIB_PREC;

  for (j = yStbrt; j <= yFinish; j++) {
    DTYPE *srcPixelPtr2;
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE b00_2, b01_2, b10_2, b11_2;
    FTYPE pix0, pix1, pix2;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

    t = (X & MLIB_MASK) * scble;
    u = (Y & MLIB_MASK) * scble;
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 3 * xSrc;
    srcPixelPtr2 = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srcPixelPtr[0];
    b00_1 = srcPixelPtr[1];
    b00_2 = srcPixelPtr[2];
    b01_0 = srcPixelPtr[3];
    b01_1 = srcPixelPtr[4];
    b01_2 = srcPixelPtr[5];
    b10_0 = srcPixelPtr2[0];
    b10_1 = srcPixelPtr2[1];
    b10_2 = srcPixelPtr2[2];
    b11_0 = srcPixelPtr2[3];
    b11_1 = srcPixelPtr2[4];
    b11_2 = srcPixelPtr2[5];

    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 3) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
      pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
      t = (X & MLIB_MASK) * scble;
      u = (Y & MLIB_MASK) * scble;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> MLIB_SHIFT;
      X += dX;
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 3 * xSrc;
      srcPixelPtr2 = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b01_0 = srcPixelPtr[3];
      b01_1 = srcPixelPtr[4];
      b01_2 = srcPixelPtr[5];
      b00_0 = srcPixelPtr[0];
      b00_1 = srcPixelPtr[1];
      b00_2 = srcPixelPtr[2];
      b10_0 = srcPixelPtr2[0];
      b10_1 = srcPixelPtr2[1];
      b10_2 = srcPixelPtr2[2];
      b11_0 = srcPixelPtr2[3];
      b11_1 = srcPixelPtr2[4];
      b11_2 = srcPixelPtr2[5];
      STORE(dstPixelPtr[0], pix0);
      STORE(dstPixelPtr[1], pix1);
      STORE(dstPixelPtr[2], pix2);
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
    pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
    STORE(dstPixelPtr[0], pix0);
    STORE(dstPixelPtr[1], pix1);
    STORE(dstPixelPtr[2], pix2);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BL();
  DTYPE *dstLineEnd;
  FTYPE scble = ONE / MLIB_PREC;

  for (j = yStbrt; j <= yFinish; j++) {
    DTYPE *srcPixelPtr2;
    FTYPE t, u, k0, k1, k2, k3;
    FTYPE b00_0, b01_0, b10_0, b11_0;
    FTYPE b00_1, b01_1, b10_1, b11_1;
    FTYPE b00_2, b01_2, b10_2, b11_2;
    FTYPE b00_3, b01_3, b10_3, b11_3;
    FTYPE pix0, pix1, pix2, pix3;

    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

    t = (X & MLIB_MASK) * scble;
    u = (Y & MLIB_MASK) * scble;
    ySrc = MLIB_POINTER_SHIFT(Y);
    Y += dY;
    xSrc = X >> MLIB_SHIFT;
    X += dX;
    srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + 4 * xSrc;
    srcPixelPtr2 = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
    k3 = t * u;
    k2 = (ONE - t) * u;
    k1 = t * (ONE - u);
    k0 = (ONE - t) * (ONE - u);
    b00_0 = srcPixelPtr[0];
    b00_1 = srcPixelPtr[1];
    b00_2 = srcPixelPtr[2];
    b00_3 = srcPixelPtr[3];
    b01_0 = srcPixelPtr[4];
    b01_1 = srcPixelPtr[5];
    b01_2 = srcPixelPtr[6];
    b01_3 = srcPixelPtr[7];
    b10_0 = srcPixelPtr2[0];
    b10_1 = srcPixelPtr2[1];
    b10_2 = srcPixelPtr2[2];
    b10_3 = srcPixelPtr2[3];
    b11_0 = srcPixelPtr2[4];
    b11_1 = srcPixelPtr2[5];
    b11_2 = srcPixelPtr2[6];
    b11_3 = srcPixelPtr2[7];

    for (; dstPixelPtr < dstLineEnd; dstPixelPtr += 4) {
      pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
      pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
      pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
      pix3 = k0 * b00_3 + k1 * b01_3 + k2 * b10_3 + k3 * b11_3;
      t = (X & MLIB_MASK) * scble;
      u = (Y & MLIB_MASK) * scble;
      ySrc = MLIB_POINTER_SHIFT(Y);
      Y += dY;
      xSrc = X >> (MLIB_SHIFT - 2);
      X += dX;
      srcPixelPtr = MLIB_POINTER_GET(lineAddr, ySrc) + (xSrc & ~3);
      srcPixelPtr2 = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      k3 = t * u;
      k2 = (ONE - t) * u;
      k1 = t * (ONE - u);
      k0 = (ONE - t) * (ONE - u);
      b00_3 = srcPixelPtr[3];
      b01_3 = srcPixelPtr[7];
      b10_3 = srcPixelPtr2[3];
      b11_3 = srcPixelPtr2[7];
      b00_0 = srcPixelPtr[0];
      b00_1 = srcPixelPtr[1];
      b00_2 = srcPixelPtr[2];
      b01_0 = srcPixelPtr[4];
      b01_1 = srcPixelPtr[5];
      b01_2 = srcPixelPtr[6];
      b10_0 = srcPixelPtr2[0];
      b10_1 = srcPixelPtr2[1];
      b10_2 = srcPixelPtr2[2];
      b11_0 = srcPixelPtr2[4];
      b11_1 = srcPixelPtr2[5];
      b11_2 = srcPixelPtr2[6];
      STORE(dstPixelPtr[0], pix0);
      STORE(dstPixelPtr[1], pix1);
      STORE(dstPixelPtr[2], pix2);
      STORE(dstPixelPtr[3], pix3);
    }

    pix0 = k0 * b00_0 + k1 * b01_0 + k2 * b10_0 + k3 * b11_0;
    pix1 = k0 * b00_1 + k1 * b01_1 + k2 * b10_1 + k3 * b11_1;
    pix2 = k0 * b00_2 + k1 * b01_2 + k2 * b10_2 + k3 * b11_2;
    pix3 = k0 * b00_3 + k1 * b01_3 + k2 * b10_3 + k3 * b11_3;
    STORE(dstPixelPtr[0], pix0);
    STORE(dstPixelPtr[1], pix1);
    STORE(dstPixelPtr[2], pix2);
    STORE(dstPixelPtr[3], pix3);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
