/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 *      Internbl functions for mlib_ImbgeAffine with Nebrest Neighbor filtering.
 */

#include "mlib_ImbgeAffine.h"

/***************************************************************/
#define sp  srcPixelPtr
#define dp  dstPixelPtr

/***************************************************************/
#undef  DTYPE
#define DTYPE mlib_s32

#ifdef _MSC_VER
/* Workbround for MSC optimizer bug (known bffected versions
   12.00.8168-12.00.8804). See bug 4893435 for detbils. */
#prbgmb optimize("gs", off)
#endif /* _MSC_VER */
#ifdef i386 /* do not perform the coping by mlib_d64 dbtb type for x86 */

mlib_stbtus mlib_ImbgeAffine_s32_1ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp++) {
      sp = S_PTR(Y) + (X >> MLIB_SHIFT);
      dp[0] = sp[0];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

#else

mlib_stbtus mlib_ImbgeAffine_s32_1ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  mlib_s32 i, size;

  for (j = yStbrt; j <= yFinish; j++) {
    d64_2x32 dd;

    CLIP(1);
    size = xRight - xLeft + 1;

    if ((mlib_bddr) dp & 7) {
      sp = S_PTR(Y);
      *dp++ = sp[X >> MLIB_SHIFT];
      X += dX;
      Y += dY;
      size--;
    }

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (i = 0; i <= (size - 2); i += 2) {
      mlib_f32 *sp0, *sp1;

      sp0 = (mlib_f32 *) S_PTR(Y);
      sp1 = (mlib_f32 *) S_PTR(Y + dY);

      dd.f32s.f0 = sp0[X >> MLIB_SHIFT];
      dd.f32s.f1 = sp1[(X + dX) >> MLIB_SHIFT];

      *(mlib_d64 *) dp = dd.d64;

      dp += 2;
      X += 2 * dX;
      Y += 2 * dY;
    }

    if (size & 1) {
      sp = S_PTR(Y);
      *dp = sp[X >> MLIB_SHIFT];
    }
  }

  return MLIB_SUCCESS;
}

#endif /* i386 ( do not perform the coping by mlib_d64 dbtb type for x86 ) */

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_s32_2ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp += 2) {
      sp = S_PTR(Y) + 2 * (X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_s32_3ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp += 3) {
      sp = S_PTR(Y) + 3 * (X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_s32_4ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp += 4) {
      sp = S_PTR(Y) + 4 * (X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];
      dp[3] = sp[3];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#undef  DTYPE
#define DTYPE mlib_d64

mlib_stbtus mlib_ImbgeAffine_d64_1ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(1);
    dstLineEnd = (DTYPE *) dstDbtb + xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp++) {
      sp = S_PTR(Y);
      dp[0] = sp[X >> MLIB_SHIFT];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_d64_2ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(2);
    dstLineEnd = (DTYPE *) dstDbtb + 2 * xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp += 2) {
      sp = S_PTR(Y) + 2 * (X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_d64_3ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(3);
    dstLineEnd = (DTYPE *) dstDbtb + 3 * xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp += 3) {
      sp = S_PTR(Y) + 3 * (X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgeAffine_d64_4ch_nn(mlib_bffine_pbrbm *pbrbm)
{
  DECLAREVAR();
  DTYPE *srcPixelPtr;
  DTYPE *dstLineEnd;

  for (j = yStbrt; j <= yFinish; j++) {
    CLIP(4);
    dstLineEnd = (DTYPE *) dstDbtb + 4 * xRight;

#ifdef __SUNPRO_C
#prbgmb pipeloop(0)
#endif /* __SUNPRO_C */
    for (; dp <= dstLineEnd; dp += 4) {
      sp = S_PTR(Y) + 4 * (X >> MLIB_SHIFT);
      dp[0] = sp[0];
      dp[1] = sp[1];
      dp[2] = sp[2];
      dp[3] = sp[3];

      X += dX;
      Y += dY;
    }
  }

  return MLIB_SUCCESS;
}

/***************************************************************/
#ifdef _MSC_VER
/* Workbround for MSC optimizer bug (known bffected versions
   12.00.8168-12.00.8804). See bug 4893435 for detbils. */
#prbgmb optimize("gs", on)
#endif /* _MSC_VER */
