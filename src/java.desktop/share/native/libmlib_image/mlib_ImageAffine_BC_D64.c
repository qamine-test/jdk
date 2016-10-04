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
 *      Imbge bffine trbnsformbtion with Bicubic filtering
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgeAffine_[s32|f32|d64]_?ch_bc(mlib_s32 *leftEdges,
 *                                                        mlib_s32 *rightEdges,
 *                                                        mlib_s32 *xStbrts,
 *                                                        mlib_s32 *yStbrts,
 *                                                        mlib_s32 *sides,
 *                                                        mlib_u8  *dstDbtb,
 *                                                        mlib_u8  **lineAddr,
 *                                                        mlib_s32 dstYStride,
 *                                                        mlib_s32 is_bffine,
 *                                                        mlib_s32 srcYStride,
 *                                                        mlib_filter filter)
 *
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
 *      filter     type of resbmpling filter
 *
 * DESCRIPTION
 *      The functions step blong the lines from xLeft to xRight bnd bpply
 *      the Bicubic bnd Bicubic2 filtering.
 *
 */

#include "mlib_ImbgeAffine.h"

#define IMG_TYPE  5

/***************************************************************/
#if IMG_TYPE == 3

#define DTYPE  mlib_s32
#define FTYPE  mlib_d64

#define FUN_NAME(CHAN) mlib_ImbgeAffine_s32_##CHAN##_bc

#define STORE(res, x) SAT32(res)

#elif IMG_TYPE == 4

#define DTYPE  mlib_f32
#define FTYPE  DTYPE

#define FUN_NAME(CHAN) mlib_ImbgeAffine_f32_##CHAN##_bc

#define STORE(res, x) res = (x)

#elif IMG_TYPE == 5

#define DTYPE  mlib_d64
#define FTYPE  DTYPE

#define FUN_NAME(CHAN) mlib_ImbgeAffine_d64_##CHAN##_bc

#define STORE(res, x) res = (x)

#endif /* IMG_TYPE == 3 */

/***************************************************************/
#define CREATE_COEF_BICUBIC( X, Y, OPERATOR )                   \
  dx = (X & MLIB_MASK) * scble;                                 \
  dy = (Y & MLIB_MASK) * scble;                                 \
  dx_2  = ((FTYPE)0.5)  * dx;                                   \
  dy_2  = ((FTYPE)0.5)  * dy;                                   \
  dx2   = dx   * dx;    dy2   = dy   * dy;                      \
  dx3_2 = dx_2 * dx2;   dy3_2 = dy_2 * dy2;                     \
  dx3_3 = ((FTYPE)3.0)  * dx3_2;                                \
  dy3_3 = ((FTYPE)3.0)  * dy3_2;                                \
                                                                \
  xf0 = dx2 - dx3_2 - dx_2;                                     \
  xf1 = dx3_3 - ((FTYPE)2.5) * dx2 + ((FTYPE)1.0);              \
  xf2 = ((FTYPE)2.0) * dx2 - dx3_3 + dx_2;                      \
  xf3 = dx3_2 - ((FTYPE)0.5) * dx2;                             \
                                                                \
  OPERATOR;                                                     \
                                                                \
  yf0 = dy2 - dy3_2 - dy_2;                                     \
  yf1 = dy3_3 - ((FTYPE)2.5) * dy2 + ((FTYPE)1.0);              \
  yf2 = ((FTYPE)2.0) * dy2 - dy3_3 + dy_2;                      \
  yf3 = dy3_2 - ((FTYPE)0.5) * dy2

/***************************************************************/
#define CREATE_COEF_BICUBIC_2( X, Y, OPERATOR )                 \
  dx = (X & MLIB_MASK) * scble;                                 \
  dy = (Y & MLIB_MASK) * scble;                                 \
  dx2   = dx  * dx;    dy2   = dy  * dy;                        \
  dx3_2 = dx  * dx2;   dy3_2 = dy  * dy2;                       \
  dx3_3 = ((FTYPE)2.0) * dx2;                                   \
  dy3_3 = ((FTYPE)2.0) * dy2;                                   \
                                                                \
  xf0 = dx3_3 - dx3_2 - dx;                                     \
  xf1 = dx3_2 - dx3_3 + ((FTYPE)1.0);                           \
  xf2 = dx2   - dx3_2   + dx;                                   \
  xf3 = dx3_2 - dx2;                                            \
                                                                \
  OPERATOR;                                                     \
                                                                \
  yf0 = dy3_3 - dy3_2 - dy;                                     \
  yf1 = dy3_2 - dy3_3 + ((FTYPE)1.0);                           \
  yf2 = dy2   - dy3_2   + dy;                                   \
  yf3 = dy3_2 - dy2

/***************************************************************/
mlib_stbtus FUN_NAME(1ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE xf0, xf1, xf2, xf3;
    FTYPE yf0, yf1, yf2, yf3;
    FTYPE dx, dx_2, dx2, dx3_2, dx3_3;
    FTYPE dy, dy_2, dy2, dy3_2, dy3_3;
    FTYPE c0, c1, c2, c3, vbl0;
    FTYPE scble = 1 / 65536.f;
    FTYPE s0, s1, s2, s3;
    FTYPE s4, s5, s6, s7;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

    if (filter == MLIB_BICUBIC) {
      CREATE_COEF_BICUBIC(X, Y,;);
    }
    else {
      CREATE_COEF_BICUBIC_2(X, Y,;);
    }

    xSrc = (X >> MLIB_SHIFT) - 1;
    ySrc = (Y >> MLIB_SHIFT) - 1;

    srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + xSrc;
    s0 = srcPixelPtr[0];
    s1 = srcPixelPtr[1];
    s2 = srcPixelPtr[2];
    s3 = srcPixelPtr[3];

    srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
    s4 = srcPixelPtr[0];
    s5 = srcPixelPtr[1];
    s6 = srcPixelPtr[2];
    s7 = srcPixelPtr[3];

    if (filter == MLIB_BICUBIC) {
      for (; dstPixelPtr <= (dstLineEnd - 1); dstPixelPtr++) {
        X += dX;
        Y += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
              srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
              srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);

        CREATE_COEF_BICUBIC(X, Y, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

        STORE(dstPixelPtr[0], vbl0);

        xSrc = (X >> MLIB_SHIFT) - 1;
        ySrc = (Y >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + xSrc;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[1];
        s2 = srcPixelPtr[2];
        s3 = srcPixelPtr[3];

        srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[1];
        s6 = srcPixelPtr[2];
        s7 = srcPixelPtr[3];
      }

    }
    else {
      for (; dstPixelPtr <= (dstLineEnd - 1); dstPixelPtr++) {
        X += dX;
        Y += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
              srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
              srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);

        CREATE_COEF_BICUBIC_2(X, Y, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

        STORE(dstPixelPtr[0], vbl0);

        xSrc = (X >> MLIB_SHIFT) - 1;
        ySrc = (Y >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + xSrc;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[1];
        s2 = srcPixelPtr[2];
        s3 = srcPixelPtr[3];

        srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[1];
        s6 = srcPixelPtr[2];
        s7 = srcPixelPtr[3];
      }
    }

    c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
    c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
    srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
    c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
          srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);
    srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
    c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
          srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);

    vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
    STORE(dstPixelPtr[0], vbl0);
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(2ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE xf0, xf1, xf2, xf3;
    FTYPE yf0, yf1, yf2, yf3;
    FTYPE dx, dx_2, dx2, dx3_2, dx3_3;
    FTYPE dy, dy_2, dy2, dy3_2, dy3_3;
    FTYPE c0, c1, c2, c3, vbl0;
    FTYPE scble = 1 / 65536.f;
    FTYPE s0, s1, s2, s3;
    FTYPE s4, s5, s6, s7;
    mlib_s32 k;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

    for (k = 0; k < 2; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      if (filter == MLIB_BICUBIC) {
        CREATE_COEF_BICUBIC(X1, Y1,;);
      }
      else {
        CREATE_COEF_BICUBIC_2(X1, Y1,;);
      }

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 2 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[2];
      s2 = srcPixelPtr[4];
      s3 = srcPixelPtr[6];

      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[2];
      s6 = srcPixelPtr[4];
      s7 = srcPixelPtr[6];

      if (filter == MLIB_BICUBIC) {
        for (; dPtr <= (dstLineEnd - 1); dPtr += 2) {
          X1 += dX;
          Y1 += dY;

          c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
          c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
                srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
                srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);

          CREATE_COEF_BICUBIC(X1, Y1, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

          STORE(dPtr[0], vbl0);

          xSrc = (X1 >> MLIB_SHIFT) - 1;
          ySrc = (Y1 >> MLIB_SHIFT) - 1;

          srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 2 * xSrc + k;
          s0 = srcPixelPtr[0];
          s1 = srcPixelPtr[2];
          s2 = srcPixelPtr[4];
          s3 = srcPixelPtr[6];

          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          s4 = srcPixelPtr[0];
          s5 = srcPixelPtr[2];
          s6 = srcPixelPtr[4];
          s7 = srcPixelPtr[6];
        }

      }
      else {
        for (; dPtr <= (dstLineEnd - 1); dPtr += 2) {
          X1 += dX;
          Y1 += dY;

          c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
          c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
                srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
                srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);

          CREATE_COEF_BICUBIC_2(X1, Y1, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

            STORE(dPtr[0], vbl0);

          xSrc = (X1 >> MLIB_SHIFT) - 1;
          ySrc = (Y1 >> MLIB_SHIFT) - 1;

          srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 2 * xSrc + k;
          s0 = srcPixelPtr[0];
          s1 = srcPixelPtr[2];
          s2 = srcPixelPtr[4];
          s3 = srcPixelPtr[6];

          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          s4 = srcPixelPtr[0];
          s5 = srcPixelPtr[2];
          s6 = srcPixelPtr[4];
          s7 = srcPixelPtr[6];
        }
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
            srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
            srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
      STORE(dPtr[0], vbl0);
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE xf0, xf1, xf2, xf3;
    FTYPE yf0, yf1, yf2, yf3;
    FTYPE dx, dx_2, dx2, dx3_2, dx3_3;
    FTYPE dy, dy_2, dy2, dy3_2, dy3_3;
    FTYPE c0, c1, c2, c3, vbl0;
    FTYPE scble = 1 / 65536.f;
    FTYPE s0, s1, s2, s3;
    FTYPE s4, s5, s6, s7;
    mlib_s32 k;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

    for (k = 0; k < 3; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      if (filter == MLIB_BICUBIC) {
        CREATE_COEF_BICUBIC(X1, Y1,;);
      }
      else {
        CREATE_COEF_BICUBIC_2(X1, Y1,;);
      }

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 3 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[3];
      s2 = srcPixelPtr[6];
      s3 = srcPixelPtr[9];

      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[3];
      s6 = srcPixelPtr[6];
      s7 = srcPixelPtr[9];

      if (filter == MLIB_BICUBIC) {
        for (; dPtr <= (dstLineEnd - 1); dPtr += 3) {
          X1 += dX;
          Y1 += dY;

          c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
          c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
                srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
                srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);

          CREATE_COEF_BICUBIC(X1, Y1, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

          STORE(dPtr[0], vbl0);

          xSrc = (X1 >> MLIB_SHIFT) - 1;
          ySrc = (Y1 >> MLIB_SHIFT) - 1;

          srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 3 * xSrc + k;
          s0 = srcPixelPtr[0];
          s1 = srcPixelPtr[3];
          s2 = srcPixelPtr[6];
          s3 = srcPixelPtr[9];

          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          s4 = srcPixelPtr[0];
          s5 = srcPixelPtr[3];
          s6 = srcPixelPtr[6];
          s7 = srcPixelPtr[9];
        }

      }
      else {
        for (; dPtr <= (dstLineEnd - 1); dPtr += 3) {
          X1 += dX;
          Y1 += dY;

          c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
          c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
                srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
                srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);

          CREATE_COEF_BICUBIC_2(X1, Y1, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

            STORE(dPtr[0], vbl0);

          xSrc = (X1 >> MLIB_SHIFT) - 1;
          ySrc = (Y1 >> MLIB_SHIFT) - 1;

          srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 3 * xSrc + k;
          s0 = srcPixelPtr[0];
          s1 = srcPixelPtr[3];
          s2 = srcPixelPtr[6];
          s3 = srcPixelPtr[9];

          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          s4 = srcPixelPtr[0];
          s5 = srcPixelPtr[3];
          s6 = srcPixelPtr[6];
          s7 = srcPixelPtr[9];
        }
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
            srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
            srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
      STORE(dPtr[0], vbl0);
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    FTYPE xf0, xf1, xf2, xf3;
    FTYPE yf0, yf1, yf2, yf3;
    FTYPE dx, dx_2, dx2, dx3_2, dx3_3;
    FTYPE dy, dy_2, dy2, dy3_2, dy3_3;
    FTYPE c0, c1, c2, c3, vbl0;
    FTYPE scble = 1 / 65536.f;
    FTYPE s0, s1, s2, s3;
    FTYPE s4, s5, s6, s7;
    mlib_s32 k;

    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

    for (k = 0; k < 4; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      if (filter == MLIB_BICUBIC) {
        CREATE_COEF_BICUBIC(X1, Y1,;);
      }
      else {
        CREATE_COEF_BICUBIC_2(X1, Y1,;);
      }

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 4 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[4];
      s2 = srcPixelPtr[8];
      s3 = srcPixelPtr[12];

      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[4];
      s6 = srcPixelPtr[8];
      s7 = srcPixelPtr[12];

      if (filter == MLIB_BICUBIC) {
        for (; dPtr <= (dstLineEnd - 1); dPtr += 4) {

          X1 += dX;
          Y1 += dY;

          c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
          c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
                srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
                srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);

          CREATE_COEF_BICUBIC(X1, Y1, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

          STORE(dPtr[0], vbl0);

          xSrc = (X1 >> MLIB_SHIFT) - 1;
          ySrc = (Y1 >> MLIB_SHIFT) - 1;

          srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 4 * xSrc + k;
          s0 = srcPixelPtr[0];
          s1 = srcPixelPtr[4];
          s2 = srcPixelPtr[8];
          s3 = srcPixelPtr[12];

          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          s4 = srcPixelPtr[0];
          s5 = srcPixelPtr[4];
          s6 = srcPixelPtr[8];
          s7 = srcPixelPtr[12];
        }

      }
      else {
        for (; dPtr <= (dstLineEnd - 1); dPtr += 4) {

          X1 += dX;
          Y1 += dY;

          c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
          c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
                srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);
          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
                srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);

          CREATE_COEF_BICUBIC_2(X1, Y1, vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3));

            STORE(dPtr[0], vbl0);

          xSrc = (X1 >> MLIB_SHIFT) - 1;
          ySrc = (Y1 >> MLIB_SHIFT) - 1;

          srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 4 * xSrc + k;
          s0 = srcPixelPtr[0];
          s1 = srcPixelPtr[4];
          s2 = srcPixelPtr[8];
          s3 = srcPixelPtr[12];

          srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
          s4 = srcPixelPtr[0];
          s5 = srcPixelPtr[4];
          s6 = srcPixelPtr[8];
          s7 = srcPixelPtr[12];
        }
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
            srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_u8 *) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
            srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
      STORE(dPtr[0], vbl0);
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
