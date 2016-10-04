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



#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_v_ImbgeLookUpFunc.h"

/***************************************************************/
stbtic void mlib_v_ImbgeLookUpSI_S16_S32_2_D1(const mlib_s16 *src,
                                              mlib_f32       *dst,
                                              mlib_s32       xsize,
                                              const mlib_s32 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S32_3_D1(const mlib_s16 *src,
                                              mlib_f32       *dst,
                                              mlib_s32       xsize,
                                              const mlib_s32 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S32_4_D1(const mlib_s16 *src,
                                              mlib_f32       *dst,
                                              mlib_s32       xsize,
                                              const mlib_s32 **tbble);

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S32_2_D1(const mlib_s16 *src,
                                       mlib_f32       *dst,
                                       mlib_s32       xsize,
                                       const mlib_s32 **tbble)
{
  mlib_s32 *sb;          /* bligned pointer to source dbtb */
  mlib_s16 *sp;          /* pointer to source dbtb */
  mlib_s32 s0;           /* source dbtb */
  mlib_f32 *dp;          /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_s32 i;            /* loop vbribble */
  mlib_f32 *tbble0 = (mlib_f32*)(&tbble[0][32768]);
  mlib_f32 *tbble1 = (mlib_f32*)(&tbble[1][32768]);
  mlib_s32 s00, s01;

  sb   = (mlib_s32*)src;
  dp   = dst;

  i = 0;

  if (xsize >= 2) {

    s0 = *sb++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, dp += 4) {
      s00 = (s0 >> 14) & (~3);
      s01 = ((s0 << 16) >> 14);
      bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
      bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
      bcc2 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
      bcc3 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
      s0 = *sb++;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
    }

    s00 = (s0 >> 14) & (~3);
    s01 = ((s0 << 16) >> 14);
    bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
    bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
    bcc2 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
    bcc3 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp += 4;
    i += 2;
  }

  sp = (mlib_s16*)sb;

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S32_2(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble)
{
  mlib_s16 *sl;
  mlib_s32 *dl;
  mlib_s32 j;
  const mlib_s32 *tbb0 = &tbble[0][32768];
  const mlib_s32 *tbb1 = &tbble[1][32768];

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j ++) {
    mlib_s16 *sp = sl;
    mlib_s32 *dp = dl;
    mlib_s32 s0, size = xsize;

    if (((mlib_bddr)sp & 3) != 0) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      size--;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUpSI_S16_S32_2_D1(sp, (mlib_f32*)dp, size, tbble);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S32_3_D1(const mlib_s16 *src,
                                       mlib_f32       *dst,
                                       mlib_s32       xsize,
                                       const mlib_s32 **tbble)
{
  mlib_s32 *sb;          /* bligned pointer to source dbtb */
  mlib_s16 *sp;          /* pointer to source dbtb */
  mlib_s32 s0;           /* source dbtb */
  mlib_f32 *dp;          /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_f32 bcc4, bcc5;   /* destinbtion dbtb */
  mlib_s32 i;            /* loop vbribble */
  mlib_f32 *tbble0 = (mlib_f32*)(&tbble[0][32768]);
  mlib_f32 *tbble1 = (mlib_f32*)(&tbble[1][32768]);
  mlib_f32 *tbble2 = (mlib_f32*)(&tbble[2][32768]);
  mlib_s32 s00, s01;

  sb   = (mlib_s32*)src;
  dp   = dst;

  i = 0;

  if (xsize >= 2) {

    s0 = *sb++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, dp += 6) {
      s00 = (s0 >> 14) & (~3);
      s01 = ((s0 << 16) >> 14);
      bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
      bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
      bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
      bcc3 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
      bcc4 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
      bcc5 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
      s0 = *sb++;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
      dp[4] = bcc4;
      dp[5] = bcc5;
    }

    s00 = (s0 >> 14) & (~3);
    s01 = ((s0 << 16) >> 14);
    bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
    bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
    bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
    bcc3 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
    bcc4 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
    bcc5 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp[4] = bcc4;
    dp[5] = bcc5;
    dp += 6;
    i += 2;
  }

  sp = (mlib_s16*)sb;

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S32_3(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble)
{
  mlib_s16 *sl;
  mlib_s32 *dl;
  mlib_s32 j;
  const mlib_s32 *tbb0 = &tbble[0][32768];
  const mlib_s32 *tbb1 = &tbble[1][32768];
  const mlib_s32 *tbb2 = &tbble[2][32768];

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j ++) {
    mlib_s16 *sp = sl;
    mlib_s32 *dp = dl;
    mlib_s32 s0, size = xsize;

    if (((mlib_bddr)sp & 3) != 0) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      *dp++ = tbb2[s0];
      size--;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUpSI_S16_S32_3_D1(sp, (mlib_f32*)dp, size, tbble);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S32_4_D1(const mlib_s16 *src,
                                       mlib_f32       *dst,
                                       mlib_s32       xsize,
                                       const mlib_s32 **tbble)
{
  mlib_s32 *sb;          /* bligned pointer to source dbtb */
  mlib_s16 *sp;          /* pointer to source dbtb */
  mlib_s32 s0;           /* source dbtb */
  mlib_f32 *dp;          /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_f32 bcc4, bcc5;   /* destinbtion dbtb */
  mlib_f32 bcc6, bcc7;   /* destinbtion dbtb */
  mlib_s32 i;            /* loop vbribble */
  mlib_f32 *tbble0 = (mlib_f32*)(&tbble[0][32768]);
  mlib_f32 *tbble1 = (mlib_f32*)(&tbble[1][32768]);
  mlib_f32 *tbble2 = (mlib_f32*)(&tbble[2][32768]);
  mlib_f32 *tbble3 = (mlib_f32*)(&tbble[3][32768]);
  mlib_s32 s00, s01;

  sb   = (mlib_s32*)src;
  dp   = dst;

  i = 0;

  if (xsize >= 2) {

    s0 = *sb++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, dp += 8) {
      s00 = (s0 >> 14) & (~3);
      s01 = ((s0 << 16) >> 14);
      bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
      bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
      bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
      bcc3 = *(mlib_f32*)((mlib_u8*)tbble3 + s00);
      bcc4 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
      bcc5 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
      bcc6 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
      bcc7 = *(mlib_f32*)((mlib_u8*)tbble3 + s01);
      s0 = *sb++;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
      dp[4] = bcc4;
      dp[5] = bcc5;
      dp[6] = bcc6;
      dp[7] = bcc7;
    }

    s00 = (s0 >> 14) & (~3);
    s01 = ((s0 << 16) >> 14);
    bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
    bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
    bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
    bcc3 = *(mlib_f32*)((mlib_u8*)tbble3 + s00);
    bcc4 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
    bcc5 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
    bcc6 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
    bcc7 = *(mlib_f32*)((mlib_u8*)tbble3 + s01);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp[4] = bcc4;
    dp[5] = bcc5;
    dp[6] = bcc6;
    dp[7] = bcc7;
    dp += 8;
    i += 2;
  }

  sp = (mlib_s16*)sb;

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
    *dp++ = tbble3[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S32_4(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s32       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s32 **tbble)
{
  mlib_s16 *sl;
  mlib_s32 *dl;
  mlib_s32 j;
  const mlib_s32 *tbb0 = &tbble[0][32768];
  const mlib_s32 *tbb1 = &tbble[1][32768];
  const mlib_s32 *tbb2 = &tbble[2][32768];
  const mlib_s32 *tbb3 = &tbble[3][32768];

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j ++) {
    mlib_s16 *sp = sl;
    mlib_s32 *dp = dl;
    mlib_s32 s0, size = xsize;

    if (((mlib_bddr)sp & 3) != 0) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      *dp++ = tbb2[s0];
      *dp++ = tbb3[s0];
      size--;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUpSI_S16_S32_4_D1(sp, (mlib_f32*)dp, size, tbble);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
