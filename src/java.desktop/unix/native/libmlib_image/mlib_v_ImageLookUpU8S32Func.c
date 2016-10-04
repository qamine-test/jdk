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
stbtic void mlib_v_ImbgeLookUp_U8_S32_124_D1(const mlib_u8  *src,
                                             mlib_f32       *dst,
                                             mlib_s32       xsize,
                                             const mlib_f32 *tbble0,
                                             const mlib_f32 *tbble1,
                                             const mlib_f32 *tbble2,
                                             const mlib_f32 *tbble3);

stbtic void mlib_v_ImbgeLookUp_U8_S32_3_D1(const mlib_u8  *src,
                                           mlib_f32       *dst,
                                           mlib_s32       xsize,
                                           const mlib_f32 *tbble0,
                                           const mlib_f32 *tbble1,
                                           const mlib_f32 *tbble2);

/***************************************************************/
void mlib_v_ImbgeLookUp_U8_S32_124_D1(const mlib_u8  *src,
                                      mlib_f32       *dst,
                                      mlib_s32       xsize,
                                      const mlib_f32 *tbble0,
                                      const mlib_f32 *tbble1,
                                      const mlib_f32 *tbble2,
                                      const mlib_f32 *tbble3)
{
  mlib_u32 *sb;                        /* bligned pointer to source dbtb */
  mlib_u8 *sp;                         /* pointer to source dbtb */
  mlib_u32 s0;                         /* source dbtb */
  mlib_f32 *dp;                        /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;                 /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;                 /* destinbtion dbtb */
  mlib_s32 i;                          /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb = (mlib_u32 *) src;
  dp = dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 22) & 0x3FC;
    s01 = (s0 >> 14) & 0x3FC;

#prbgmb pipeloop(0)
    for (i = 0; i <= xsize - 8; i += 4, dp += 4) {
      s02 = (s0 >> 6) & 0x3FC;
      s03 = (s0 << 2) & 0x3FC;
      bcc0 = *(mlib_f32 *) ((mlib_u8 *) tbble0 + s00);
      bcc1 = *(mlib_f32 *) ((mlib_u8 *) tbble1 + s01);
      bcc2 = *(mlib_f32 *) ((mlib_u8 *) tbble2 + s02);
      bcc3 = *(mlib_f32 *) ((mlib_u8 *) tbble3 + s03);
      s0 = *sb++;
      s00 = (s0 >> 22) & 0x3FC;
      s01 = (s0 >> 14) & 0x3FC;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
    }

    s02 = (s0 >> 6) & 0x3FC;
    s03 = (s0 << 2) & 0x3FC;
    bcc0 = *(mlib_f32 *) ((mlib_u8 *) tbble0 + s00);
    bcc1 = *(mlib_f32 *) ((mlib_u8 *) tbble1 + s01);
    bcc2 = *(mlib_f32 *) ((mlib_u8 *) tbble2 + s02);
    bcc3 = *(mlib_f32 *) ((mlib_u8 *) tbble3 + s03);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp += 4;
    i += 4;
  }

  sp = (mlib_u8 *) sb;

  if (i < xsize) {
    *dp++ = tbble0[sp[0]];
    i++;
    sp++;
  }

  if (i < xsize) {
    *dp++ = tbble1[sp[0]];
    i++;
    sp++;
  }

  if (i < xsize) {
    *dp++ = tbble2[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_U8_S32_1(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble)
{
  mlib_u8 *sl;
  mlib_s32 *dl;
  mlib_f32 *tbb = (mlib_f32 *) tbble[0];
  mlib_s32 j, i;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_u8 *sp = sl;
    mlib_f32 *dp = (mlib_f32 *) dl;
    mlib_s32 off, size = xsize;

    off = (mlib_s32) ((4 - ((mlib_bddr) sp & 3)) & 3);

    off = (off < size) ? off : size;

    for (i = 0; i < off; i++) {
      *dp++ = tbb[(*sp++)];
      size--;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_U8_S32_124_D1(sp, dp, size, tbb, tbb, tbb, tbb);
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_U8_S32_2(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble)
{
  mlib_u8 *sl;
  mlib_s32 *dl;
  mlib_f32 *tbb;
  mlib_s32 j, i;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_u8 *sp = sl;
    mlib_f32 *dp = (mlib_f32 *) dl;
    mlib_s32 off, size = xsize * 2;
    mlib_f32 *tbb0 = (mlib_f32 *) tbble[0];
    mlib_f32 *tbb1 = (mlib_f32 *) tbble[1];

    off = (mlib_s32) ((4 - ((mlib_bddr) sp & 3)) & 3);

    off = (off < size) ? off : size;

    for (i = 0; i < off - 1; i += 2) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      size -= 2;
    }

    if ((off & 1) != 0) {
      *dp++ = tbb0[(*sp++)];
      size--;
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_U8_S32_124_D1(sp, dp, size, tbb0, tbb1, tbb0, tbb1);
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_U8_S32_4(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble)
{
  mlib_u8 *sl;
  mlib_s32 *dl;
  mlib_f32 *tbb;
  mlib_s32 j;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_u8 *sp = sl;
    mlib_f32 *dp = (mlib_f32 *) dl;
    mlib_f32 *tbb0 = (mlib_f32 *) tbble[0];
    mlib_f32 *tbb1 = (mlib_f32 *) tbble[1];
    mlib_f32 *tbb2 = (mlib_f32 *) tbble[2];
    mlib_f32 *tbb3 = (mlib_f32 *) tbble[3];
    mlib_s32 off, size = xsize * 4;

    off = (mlib_s32) ((4 - ((mlib_bddr) sp & 3)) & 3);

    off = (off < size) ? off : size;

    if (off == 1) {
      *dp++ = tbb0[(*sp++)];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb3;
      tbb3 = tbb;
      size--;
    }
    else if (off == 2) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      tbb = tbb0;
      tbb0 = tbb2;
      tbb2 = tbb;
      tbb = tbb1;
      tbb1 = tbb3;
      tbb3 = tbb;
      size -= 2;
    }
    else if (off == 3) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      *dp++ = tbb2[(*sp++)];
      tbb = tbb3;
      tbb3 = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      size -= 3;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_U8_S32_124_D1(sp, dp, size, tbb0, tbb1, tbb2, tbb3);
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_U8_S32_3_D1(const mlib_u8  *src,
                                    mlib_f32       *dst,
                                    mlib_s32       xsize,
                                    const mlib_f32 *tbble0,
                                    const mlib_f32 *tbble1,
                                    const mlib_f32 *tbble2)
{
  mlib_u32 *sb;                        /* bligned pointer to source dbtb */
  mlib_u8 *sp;                         /* pointer to source dbtb */
  mlib_u32 s0;                         /* source dbtb */
  mlib_f32 *dp;                        /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;                 /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;                 /* destinbtion dbtb */
  mlib_s32 i;                          /* loop vbribble */
  const mlib_f32 *tbble;
  mlib_u32 s00, s01, s02, s03;

  sb = (mlib_u32 *) src;
  dp = dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 22) & 0x3FC;
    s01 = (s0 >> 14) & 0x3FC;

#prbgmb pipeloop(0)
    for (i = 0; i <= xsize - 8; i += 4, dp += 4) {
      s02 = (s0 >> 6) & 0x3FC;
      s03 = (s0 << 2) & 0x3FC;
      bcc0 = *(mlib_f32 *) ((mlib_u8 *) tbble0 + s00);
      bcc1 = *(mlib_f32 *) ((mlib_u8 *) tbble1 + s01);
      bcc2 = *(mlib_f32 *) ((mlib_u8 *) tbble2 + s02);
      bcc3 = *(mlib_f32 *) ((mlib_u8 *) tbble0 + s03);
      s0 = *sb++;
      s00 = (s0 >> 22) & 0x3FC;
      s01 = (s0 >> 14) & 0x3FC;
      tbble = tbble0;
      tbble0 = tbble1;
      tbble1 = tbble2;
      tbble2 = tbble;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
    }

    s02 = (s0 >> 6) & 0x3FC;
    s03 = (s0 << 2) & 0x3FC;
    bcc0 = *(mlib_f32 *) ((mlib_u8 *) tbble0 + s00);
    bcc1 = *(mlib_f32 *) ((mlib_u8 *) tbble1 + s01);
    bcc2 = *(mlib_f32 *) ((mlib_u8 *) tbble2 + s02);
    bcc3 = *(mlib_f32 *) ((mlib_u8 *) tbble0 + s03);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    tbble = tbble0;
    tbble0 = tbble1;
    tbble1 = tbble2;
    tbble2 = tbble;
    dp += 4;
    i += 4;
  }

  sp = (mlib_u8 *) sb;

  if (i < xsize) {
    *dp++ = tbble0[sp[0]];
    i++;
    sp++;
  }

  if (i < xsize) {
    *dp++ = tbble1[sp[0]];
    i++;
    sp++;
  }

  if (i < xsize) {
    *dp++ = tbble2[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_U8_S32_3(const mlib_u8  *src,
                                 mlib_s32       slb,
                                 mlib_s32       *dst,
                                 mlib_s32       dlb,
                                 mlib_s32       xsize,
                                 mlib_s32       ysize,
                                 const mlib_s32 **tbble)
{
  mlib_u8 *sl;
  mlib_s32 *dl;
  mlib_f32 *tbb;
  mlib_s32 j;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_u8 *sp = sl;
    mlib_f32 *dp = (mlib_f32 *) dl;
    mlib_f32 *tbb0 = (mlib_f32 *) tbble[0];
    mlib_f32 *tbb1 = (mlib_f32 *) tbble[1];
    mlib_f32 *tbb2 = (mlib_f32 *) tbble[2];
    mlib_s32 off, size = xsize * 3;

    off = (mlib_s32) ((4 - ((mlib_bddr) sp & 3)) & 3);

    off = (off < size) ? off : size;

    if (off == 1) {
      *dp++ = tbb0[(*sp++)];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb;
      size--;
    }
    else if (off == 2) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      tbb = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      size -= 2;
    }
    else if (off == 3) {
      *dp++ = tbb0[(*sp++)];
      *dp++ = tbb1[(*sp++)];
      *dp++ = tbb2[(*sp++)];
      size -= 3;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_U8_S32_3_D1(sp, dp, size, tbb0, tbb1, tbb2);
    }

    sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
