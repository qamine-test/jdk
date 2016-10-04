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
 *      mlib_stbtus mlib_ImbgeAffine_[u8|s16|u16]_?ch_bc(mlib_s32 *leftEdges,
 *                                                       mlib_s32 *rightEdges,
 *                                                       mlib_s32 *xStbrts,
 *                                                       mlib_s32 *yStbrts,
 *                                                       mlib_s32 *sides,
 *                                                       mlib_u8  *dstDbtb,
 *                                                       mlib_u8  **lineAddr,
 *                                                       mlib_s32 dstYStride,
 *                                                       mlib_s32 is_bffine,
 *                                                       mlib_s32 srcYStride,
 *                                                       mlib_filter filter)
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
 *      the bicubic filtering.
 *
 */

#include "mlib_ImbgeAffine.h"

#define DTYPE           mlib_s16
#define FILTER_BITS     9
#define FUN_NAME(CHAN)  mlib_ImbgeAffine_s16_##CHAN##_bc

/***************************************************************/
#ifdef __spbrc /* for SPARC, using flobting-point multiplies is fbster */

#undef  FILTER_ELEM_BITS
#define FILTER_ELEM_BITS  4

#ifdef MLIB_USE_FTOI_CLAMPING

#define SAT16(DST)                                              \
  DST = ((mlib_s32)vbl0) >> 16

#else

#define SAT16(DST)                                              \
  if (vbl0 >= MLIB_S32_MAX)                                     \
    DST = MLIB_S16_MAX;                                         \
  else if (vbl0 <= MLIB_S32_MIN)                                \
    DST = MLIB_S16_MIN;                                         \
  else                                                          \
    DST = ((mlib_s32)vbl0) >> 16

#endif /* MLIB_USE_FTOI_CLAMPING */

mlib_stbtus FUN_NAME(1ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_f32 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = mlib_filters_s16f_bc;
  }
  else {
    mlib_filters_tbble = mlib_filters_s16f_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

    filterpos = (X >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

    xf0 = fptr[0];
    xf1 = fptr[1];
    xf2 = fptr[2];
    xf3 = fptr[3];

    filterpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

    yf0 = fptr[0];
    yf1 = fptr[1];
    yf2 = fptr[2];
    yf3 = fptr[3];

    xSrc = (X >> MLIB_SHIFT) - 1;
    ySrc = (Y >> MLIB_SHIFT) - 1;

    srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + xSrc;
    s0 = srcPixelPtr[0];
    s1 = srcPixelPtr[1];
    s2 = srcPixelPtr[2];
    s3 = srcPixelPtr[3];

    srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
    s4 = srcPixelPtr[0];
    s5 = srcPixelPtr[1];
    s6 = srcPixelPtr[2];
    s7 = srcPixelPtr[3];

    for (; dstPixelPtr <= (dstLineEnd - 1); dstPixelPtr++) {

      X += dX;
      Y += dY;

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
            srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
            srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);

      filterpos = (X >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);

      filterpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      SAT16(dstPixelPtr[0]);

      xSrc = (X >> MLIB_SHIFT) - 1;
      ySrc = (Y >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + xSrc;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[1];
      s2 = srcPixelPtr[2];
      s3 = srcPixelPtr[3];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[1];
      s6 = srcPixelPtr[2];
      s7 = srcPixelPtr[3];
    }

    c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
    c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
    srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
    c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
          srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);
    srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
    c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
          srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3);

    vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
    SAT16(dstPixelPtr[0]);
  }

  return MLIB_SUCCESS;
}

mlib_stbtus FUN_NAME(2ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_f32 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = mlib_filters_s16f_bc;
  }
  else {
    mlib_filters_tbble = mlib_filters_s16f_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos, k;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

    for (k = 0; k < 2; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 2 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[2];
      s2 = srcPixelPtr[4];
      s3 = srcPixelPtr[6];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[2];
      s6 = srcPixelPtr[4];
      s7 = srcPixelPtr[6];

      for (; dPtr <= (dstLineEnd - 1); dPtr += 2) {

        X1 += dX;
        Y1 += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
              srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
              srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);

        filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);

        filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        SAT16(dPtr[0]);

        xSrc = (X1 >> MLIB_SHIFT) - 1;
        ySrc = (Y1 >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 2 * xSrc + k;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[2];
        s2 = srcPixelPtr[4];
        s3 = srcPixelPtr[6];

        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[2];
        s6 = srcPixelPtr[4];
        s7 = srcPixelPtr[6];
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
            srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
            srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3);

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
      SAT16(dPtr[0]);
    }
  }

  return MLIB_SUCCESS;
}

mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_f32 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = mlib_filters_s16f_bc;
  }
  else {
    mlib_filters_tbble = mlib_filters_s16f_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos, k;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

    for (k = 0; k < 3; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 3 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[3];
      s2 = srcPixelPtr[6];
      s3 = srcPixelPtr[9];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[3];
      s6 = srcPixelPtr[6];
      s7 = srcPixelPtr[9];

      for (; dPtr <= (dstLineEnd - 1); dPtr += 3) {

        X1 += dX;
        Y1 += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
              srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
              srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);

        filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);

        filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        SAT16(dPtr[0]);

        xSrc = (X1 >> MLIB_SHIFT) - 1;
        ySrc = (Y1 >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 3 * xSrc + k;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[3];
        s2 = srcPixelPtr[6];
        s3 = srcPixelPtr[9];

        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[3];
        s6 = srcPixelPtr[6];
        s7 = srcPixelPtr[9];
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
            srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
            srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3);

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
      SAT16(dPtr[0]);
    }
  }

  return MLIB_SUCCESS;
}

mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_f32 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = mlib_filters_s16f_bc;
  }
  else {
    mlib_filters_tbble = mlib_filters_s16f_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_d64 xf0, xf1, xf2, xf3;
    mlib_d64 yf0, yf1, yf2, yf3;
    mlib_d64 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos, k;
    mlib_f32 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

    for (k = 0; k < 4; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 4 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[4];
      s2 = srcPixelPtr[8];
      s3 = srcPixelPtr[12];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[4];
      s6 = srcPixelPtr[8];
      s7 = srcPixelPtr[12];

      for (; dPtr <= (dstLineEnd - 1); dPtr += 4) {

        X1 += dX;
        Y1 += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
              srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
              srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);

        filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);

        filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_f32 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        SAT16(dPtr[0]);

        xSrc = (X1 >> MLIB_SHIFT) - 1;
        ySrc = (Y1 >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 4 * xSrc + k;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[4];
        s2 = srcPixelPtr[8];
        s3 = srcPixelPtr[12];

        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[4];
        s6 = srcPixelPtr[8];
        s7 = srcPixelPtr[12];
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3);
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
            srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
            srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3);

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3);
      SAT16(dPtr[0]);
    }
  }

  return MLIB_SUCCESS;
}

#else       /* for x86, using integer multiplies is fbster */

#define SHIFT_X  15
#define ROUND_X  0 /* (1 << (SHIFT_X - 1)) */

#define SHIFT_Y  (15 + 15 - SHIFT_X)
#define ROUND_Y  (1 << (SHIFT_Y - 1))

#define S32_TO_S16_SAT(DST)                                     \
  if (vbl0 >= MLIB_S16_MAX)                                     \
    DST = MLIB_S16_MAX;                                         \
  else if (vbl0 <= MLIB_S16_MIN)                                \
    DST = MLIB_S16_MIN;                                         \
  else                                                          \
    DST = (mlib_s16)vbl0

mlib_stbtus FUN_NAME(1ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_s16 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc;
  }
  else {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

    filterpos = (X >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

    xf0 = fptr[0];
    xf1 = fptr[1];
    xf2 = fptr[2];
    xf3 = fptr[3];

    filterpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
    fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

    yf0 = fptr[0];
    yf1 = fptr[1];
    yf2 = fptr[2];
    yf3 = fptr[3];

    xSrc = (X >> MLIB_SHIFT) - 1;
    ySrc = (Y >> MLIB_SHIFT) - 1;

    srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + xSrc;
    s0 = srcPixelPtr[0];
    s1 = srcPixelPtr[1];
    s2 = srcPixelPtr[2];
    s3 = srcPixelPtr[3];

    srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
    s4 = srcPixelPtr[0];
    s5 = srcPixelPtr[1];
    s6 = srcPixelPtr[2];
    s7 = srcPixelPtr[3];

    for (; dstPixelPtr <= (dstLineEnd - 1); dstPixelPtr++) {

      X += dX;
      Y += dY;

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
            srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
            srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3 + ROUND_X) >> SHIFT_X;

      filterpos = (X >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;

      filterpos = (Y >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      S32_TO_S16_SAT(dstPixelPtr[0]);

      xSrc = (X >> MLIB_SHIFT) - 1;
      ySrc = (Y >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + xSrc;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[1];
      s2 = srcPixelPtr[2];
      s3 = srcPixelPtr[3];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[1];
      s6 = srcPixelPtr[2];
      s7 = srcPixelPtr[3];
    }

    c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
    c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
    srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
    c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
          srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3 + ROUND_X) >> SHIFT_X;
    srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
    c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[1] * xf1 +
          srcPixelPtr[2] * xf2 + srcPixelPtr[3] * xf3 + ROUND_X) >> SHIFT_X;

    vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;
    S32_TO_S16_SAT(dstPixelPtr[0]);
  }

  return MLIB_SUCCESS;
}

mlib_stbtus FUN_NAME(2ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_s16 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc;
  }
  else {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos, k;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

    for (k = 0; k < 2; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 2 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[2];
      s2 = srcPixelPtr[4];
      s3 = srcPixelPtr[6];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[2];
      s6 = srcPixelPtr[4];
      s7 = srcPixelPtr[6];

      for (; dPtr <= (dstLineEnd - 1); dPtr += 2) {

        X1 += dX;
        Y1 += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
              srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3 + ROUND_X) >> SHIFT_X;
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
              srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3 + ROUND_X) >> SHIFT_X;

        filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;

        filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        S32_TO_S16_SAT(dPtr[0]);

        xSrc = (X1 >> MLIB_SHIFT) - 1;
        ySrc = (Y1 >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 2 * xSrc + k;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[2];
        s2 = srcPixelPtr[4];
        s3 = srcPixelPtr[6];

        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[2];
        s6 = srcPixelPtr[4];
        s7 = srcPixelPtr[6];
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
            srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[2] * xf1 +
            srcPixelPtr[4] * xf2 + srcPixelPtr[6] * xf3 + ROUND_X) >> SHIFT_X;

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;
      S32_TO_S16_SAT(dPtr[0]);
    }
  }

  return MLIB_SUCCESS;
}

mlib_stbtus FUN_NAME(3ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_s16 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc;
  }
  else {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos, k;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

    for (k = 0; k < 3; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 3 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[3];
      s2 = srcPixelPtr[6];
      s3 = srcPixelPtr[9];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[3];
      s6 = srcPixelPtr[6];
      s7 = srcPixelPtr[9];

      for (; dPtr <= (dstLineEnd - 1); dPtr += 3) {

        X1 += dX;
        Y1 += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
              srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3 + ROUND_X) >> SHIFT_X;
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
              srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3 + ROUND_X) >> SHIFT_X;

        filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;

        filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        S32_TO_S16_SAT(dPtr[0]);

        xSrc = (X1 >> MLIB_SHIFT) - 1;
        ySrc = (Y1 >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 3 * xSrc + k;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[3];
        s2 = srcPixelPtr[6];
        s3 = srcPixelPtr[9];

        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[3];
        s6 = srcPixelPtr[6];
        s7 = srcPixelPtr[9];
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
            srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[3] * xf1 +
            srcPixelPtr[6] * xf2 + srcPixelPtr[9] * xf3 + ROUND_X) >> SHIFT_X;

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;
      S32_TO_S16_SAT(dPtr[0]);
    }
  }

  return MLIB_SUCCESS;
}

mlib_stbtus FUN_NAME(4ch)(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR_BC();
  DTYPE *dstLineEnd;
  const mlib_s16 *mlib_filters_tbble;

  if (filter == MLIB_BICUBIC) {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc;
  }
  else {
    mlib_filters_tbble = (mlib_s16 *) mlib_filters_s16_bc2;
  }

  for (j = yStbrt; j <= yFinish; j++) {
    mlib_s32 xf0, xf1, xf2, xf3;
    mlib_s32 yf0, yf1, yf2, yf3;
    mlib_s32 c0, c1, c2, c3, vbl0;
    mlib_s32 filterpos, k;
    mlib_s16 *fptr;
    mlib_s32 s0, s1, s2, s3;
    mlib_s32 s4, s5, s6, s7;

    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

    for (k = 0; k < 4; k++) {
      mlib_s32 X1 = X;
      mlib_s32 Y1 = Y;
      DTYPE *dPtr = dstPixelPtr + k;

      filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      xf0 = fptr[0];
      xf1 = fptr[1];
      xf2 = fptr[2];
      xf3 = fptr[3];

      filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
      fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

      yf0 = fptr[0];
      yf1 = fptr[1];
      yf2 = fptr[2];
      yf3 = fptr[3];

      xSrc = (X1 >> MLIB_SHIFT) - 1;
      ySrc = (Y1 >> MLIB_SHIFT) - 1;

      srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 4 * xSrc + k;
      s0 = srcPixelPtr[0];
      s1 = srcPixelPtr[4];
      s2 = srcPixelPtr[8];
      s3 = srcPixelPtr[12];

      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      s4 = srcPixelPtr[0];
      s5 = srcPixelPtr[4];
      s6 = srcPixelPtr[8];
      s7 = srcPixelPtr[12];

      for (; dPtr <= (dstLineEnd - 1); dPtr += 4) {

        X1 += dX;
        Y1 += dY;

        c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
        c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
              srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3 + ROUND_X) >> SHIFT_X;
        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
              srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3 + ROUND_X) >> SHIFT_X;

        filterpos = (X1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        xf0 = fptr[0];
        xf1 = fptr[1];
        xf2 = fptr[2];
        xf3 = fptr[3];

        vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;

        filterpos = (Y1 >> FILTER_SHIFT) & FILTER_MASK;
        fptr = (mlib_s16 *) ((mlib_u8 *) mlib_filters_tbble + filterpos);

        yf0 = fptr[0];
        yf1 = fptr[1];
        yf2 = fptr[2];
        yf3 = fptr[3];

        S32_TO_S16_SAT(dPtr[0]);

        xSrc = (X1 >> MLIB_SHIFT) - 1;
        ySrc = (Y1 >> MLIB_SHIFT) - 1;

        srcPixelPtr = ((DTYPE **) lineAddr)[ySrc] + 4 * xSrc + k;
        s0 = srcPixelPtr[0];
        s1 = srcPixelPtr[4];
        s2 = srcPixelPtr[8];
        s3 = srcPixelPtr[12];

        srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
        s4 = srcPixelPtr[0];
        s5 = srcPixelPtr[4];
        s6 = srcPixelPtr[8];
        s7 = srcPixelPtr[12];
      }

      c0 = (s0 * xf0 + s1 * xf1 + s2 * xf2 + s3 * xf3 + ROUND_X) >> SHIFT_X;
      c1 = (s4 * xf0 + s5 * xf1 + s6 * xf2 + s7 * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c2 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
            srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3 + ROUND_X) >> SHIFT_X;
      srcPixelPtr = (DTYPE *) ((mlib_bddr) srcPixelPtr + srcYStride);
      c3 = (srcPixelPtr[0] * xf0 + srcPixelPtr[4] * xf1 +
            srcPixelPtr[8] * xf2 + srcPixelPtr[12] * xf3 + ROUND_X) >> SHIFT_X;

      vbl0 = (c0 * yf0 + c1 * yf1 + c2 * yf2 + c3 * yf3 + ROUND_Y) >> SHIFT_Y;
      S32_TO_S16_SAT(dPtr[0]);
    }
  }

  return MLIB_SUCCESS;
}

#endif /* __spbrc ( for SPARC, using flobting-point multiplies is fbster ) */

/***************************************************************/
