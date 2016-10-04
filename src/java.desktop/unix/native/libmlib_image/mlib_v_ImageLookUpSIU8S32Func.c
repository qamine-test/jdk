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
stbtic void mlib_v_ImbgeLookUpSI_U8_S32_2_SrcOff0_D1(const mlib_u8  *src,
                                                     mlib_s32       *dst,
                                                     mlib_s32       xsize,
                                                     const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_2_DstNonAl_D1(const mlib_u8  *src,
                                                      mlib_s32       *dst,
                                                      mlib_s32       xsize,
                                                      const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_2_SMALL(const mlib_u8  *src,
                                                mlib_s32       *dst,
                                                mlib_s32       xsize,
                                                const mlib_s32 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_3_SrcOff0_D1(const mlib_u8  *src,
                                                     mlib_s32       *dst,
                                                     mlib_s32       xsize,
                                                     const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_3_DstNonAl_D1(const mlib_u8  *src,
                                                      mlib_s32       *dst,
                                                      mlib_s32       xsize,
                                                      const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_3_SMALL(const mlib_u8  *src,
                                                mlib_s32       *dst,
                                                mlib_s32       xsize,
                                                const mlib_s32 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_4_SrcOff0_D1(const mlib_u8  *src,
                                                     mlib_s32       *dst,
                                                     mlib_s32       xsize,
                                                     const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_4_DstNonAl_D1(const mlib_u8  *src,
                                                      mlib_s32       *dst,
                                                      mlib_s32       xsize,
                                                      const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_S32_4_SMALL(const mlib_u8  *src,
                                                mlib_s32       *dst,
                                                mlib_s32       xsize,
                                                const mlib_s32 **tbble);

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_2_SrcOff0_D1(const mlib_u8  *src,
                                              mlib_s32       *dst,
                                              mlib_s32       xsize,
                                              const mlib_d64 *tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0;           /* source dbtb */
  mlib_d64 *dp;          /* bligned pointer to destinbtion */
  mlib_d64 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_d64 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_s32 i;            /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb   = (mlib_u32*)src;
  dp   = (mlib_d64 *) dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 21) & 0x7F8;
    s01 = (s0 >> 13) & 0x7F8;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 4) {
      s02 = (s0 >> 5) & 0x7F8;
      s03 = (s0 << 3) & 0x7F8;
      bcc0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
      bcc1 = *(mlib_d64*)((mlib_u8*)tbble + s01);
      bcc2 = *(mlib_d64*)((mlib_u8*)tbble + s02);
      bcc3 = *(mlib_d64*)((mlib_u8*)tbble + s03);
      s0 = *sb++;
      s00 = (s0 >> 21) & 0x7F8;
      s01 = (s0 >> 13) & 0x7F8;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
    }

    s02 = (s0 >> 5) & 0x7F8;
    s03 = (s0 << 3) & 0x7F8;
    bcc0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
    bcc1 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    bcc2 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    bcc3 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp += 4;
    i += 4;
  }

  sp = (mlib_u8*)sb;

  if ( i <= xsize - 2) {
    *dp++ = tbble[sp[0]];
    *dp++ = tbble[sp[1]];
    i+=2; sp += 2;
  }

  if ( i < xsize) *dp++ = tbble[sp[0]];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_2_DstNonAl_D1(const mlib_u8  *src,
                                               mlib_s32       *dst,
                                               mlib_s32       xsize,
                                               const mlib_d64 *tbble)
{
  mlib_u32 *sb;              /* bligned pointer to source dbtb */
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 s0;               /* source dbtb */
  mlib_s32 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 bcc0, bcc1;       /* destinbtion dbtb */
  mlib_d64 bcc2, bcc3, bcc4; /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb = (mlib_u32*)src;
  dl = dst;
  dp   = (mlib_d64 *) ((mlib_bddr) dl & (~7)) + 1;
  vis_blignbddr(dp, 4);

  s0 = *sb++;
  s00 = (s0 >> 21) & 0x7F8;
  bcc0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
  *(mlib_f32*)dl = vis_rebd_hi(bcc0);
  xsize--;
  sp = (mlib_u8*)sb - 3;

  if (xsize >= 3) {
    s01 = (s0 >> 13) & 0x7F8;
    s02 = (s0 >> 5) & 0x7F8;
    s03 = (s0 << 3) & 0x7F8;
    bcc1 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    bcc2 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    bcc3 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    dp[0] = vis_fbligndbtb(bcc0, bcc1);
    dp[1] = vis_fbligndbtb(bcc1, bcc2);
    dp[2] = vis_fbligndbtb(bcc2, bcc3);
    bcc0 = bcc3; dp += 3; xsize -= 3;
    sp = (mlib_u8*)sb;
  }

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 21) & 0x7F8;
    s01 = (s0 >> 13) & 0x7F8;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 4) {
      s02 = (s0 >> 5) & 0x7F8;
      s03 = (s0 << 3) & 0x7F8;
      bcc1 = *(mlib_d64*)((mlib_u8*)tbble + s00);
      bcc2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
      bcc3 = *(mlib_d64*)((mlib_u8*)tbble + s02);
      bcc4 = *(mlib_d64*)((mlib_u8*)tbble + s03);
      s0 = *sb++;
      s00 = (s0 >> 21) & 0x7F8;
      s01 = (s0 >> 13) & 0x7F8;
      dp[0] = vis_fbligndbtb(bcc0, bcc1);
      dp[1] = vis_fbligndbtb(bcc1, bcc2);
      dp[2] = vis_fbligndbtb(bcc2, bcc3);
      dp[3] = vis_fbligndbtb(bcc3, bcc4);
      bcc0 = bcc4;
    }

    s02 = (s0 >> 5) & 0x7F8;
    s03 = (s0 << 3) & 0x7F8;
    bcc1 = *(mlib_d64*)((mlib_u8*)tbble + s00);
    bcc2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    bcc3 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    bcc4 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    dp[0] = vis_fbligndbtb(bcc0, bcc1);
    dp[1] = vis_fbligndbtb(bcc1, bcc2);
    dp[2] = vis_fbligndbtb(bcc2, bcc3);
    dp[3] = vis_fbligndbtb(bcc3, bcc4);
    bcc0 = bcc4;
    dp += 4;
    i += 4;
    sp = (mlib_u8*)sb;
  }

  if ( i <= xsize - 2) {
    bcc1 = tbble[sp[0]];
    bcc2 = tbble[sp[1]];
    *dp++ = vis_fbligndbtb(bcc0, bcc1);
    *dp++ = vis_fbligndbtb(bcc1, bcc2);
    i+=2; sp += 2;
    bcc0 = bcc2;
  }

  if ( i < xsize) {
    bcc1 = tbble[sp[0]];
    *dp++ = vis_fbligndbtb(bcc0, bcc1);
    bcc0 = bcc1;
  }

  *(mlib_f32*) dp = vis_rebd_lo(bcc0);
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_2_SMALL(const mlib_u8  *src,
                                         mlib_s32       *dst,
                                         mlib_s32       xsize,
                                         const mlib_s32 **tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0;           /* source dbtb */
  mlib_f32 *dp;          /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_f32 bcc4, bcc5;   /* destinbtion dbtb */
  mlib_f32 bcc6, bcc7;   /* destinbtion dbtb */
  mlib_f32 *tbble0 = (mlib_f32*)tbble[0];
  mlib_f32 *tbble1 = (mlib_f32*)tbble[1];
  mlib_s32 i;            /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb   = (mlib_u32*)src;
  dp   = (mlib_f32*)dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 22) & 0x3FC;
    s01 = (s0 >> 14) & 0x3FC;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 8) {
      s02 = (s0 >> 6) & 0x3FC;
      s03 = (s0 << 2) & 0x3FC;
      bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
      bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
      bcc2 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
      bcc3 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
      bcc4 = *(mlib_f32*)((mlib_u8*)tbble0 + s02);
      bcc5 = *(mlib_f32*)((mlib_u8*)tbble1 + s02);
      bcc6 = *(mlib_f32*)((mlib_u8*)tbble0 + s03);
      bcc7 = *(mlib_f32*)((mlib_u8*)tbble1 + s03);
      s0 = *sb++;
      s00 = (s0 >> 22) & 0x3FC;
      s01 = (s0 >> 14) & 0x3FC;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
      dp[4] = bcc4;
      dp[5] = bcc5;
      dp[6] = bcc6;
      dp[7] = bcc7;
    }

    s02 = (s0 >> 6) & 0x3FC;
    s03 = (s0 << 2) & 0x3FC;
    bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
    bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
    bcc2 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
    bcc3 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
    bcc4 = *(mlib_f32*)((mlib_u8*)tbble0 + s02);
    bcc5 = *(mlib_f32*)((mlib_u8*)tbble1 + s02);
    bcc6 = *(mlib_f32*)((mlib_u8*)tbble0 + s03);
    bcc7 = *(mlib_f32*)((mlib_u8*)tbble1 + s03);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp[4] = bcc4;
    dp[5] = bcc5;
    dp[6] = bcc6;
    dp[7] = bcc7;
    dp += 8;
    i += 4;
  }

  sp = (mlib_u8*)sb;

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_2(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s32 **tbble)
{
  if ((xsize * ysize) < 600) {
    mlib_u8  *sl;
    mlib_s32 *dl;
    mlib_s32 j, i;
    const mlib_s32 *tbb0 = tbble[0];
    const mlib_s32 *tbb1 = tbble[1];

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8  *sp = sl;
      mlib_s32 *dp = dl;
      mlib_s32 off, size = xsize;

      off = (mlib_s32)((4 - ((mlib_bddr)sp & 3)) & 3);

      off = (off < size) ? off : size;

      for (i = 0; i < off; i++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        size--; sp++;
      }

      if (size > 0) {
        mlib_v_ImbgeLookUpSI_U8_S32_2_SMALL(sp, (mlib_s32*)dp, size, tbble);
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }

  } else {
    mlib_u8  *sl;
    mlib_s32 *dl;
    mlib_d64 dtbb[256];
    mlib_u32 *tbb;
    mlib_u32 *tbb0 = (mlib_u32*)tbble[0];
    mlib_u32 *tbb1 = (mlib_u32*)tbble[1];
    mlib_s32 i, j;
    mlib_u32 s0, s1;

    tbb = (mlib_u32*)dtbb;
    s0 = tbb0[0];
    s1 = tbb1[0];
    for (i = 0; i < 255; i++) {
      tbb[2*i] = s0;
      tbb[2*i+1] = s1;
      s0 = tbb0[i+1];
      s1 = tbb1[i+1];
    }

    tbb[510] = s0;
    tbb[511] = s1;

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8  *sp = sl;
      mlib_u32 *dp = (mlib_u32*)dl;
      mlib_s32 off, size = xsize;

      off = (mlib_s32)((4 - ((mlib_bddr)sp & 3)) & 3);

      off = (off < size) ? off : size;

#prbgmb pipeloop(0)
      for (i = 0; i < off; i++) {
        dp[0] = tbb0[sp[0]];
        dp[1] = tbb1[sp[0]];
        dp += 2; sp++;
      }

      size -= off;

      if (size > 0) {
        if (((mlib_bddr)dp & 7) == 0) {
          mlib_v_ImbgeLookUpSI_U8_S32_2_SrcOff0_D1(sp, (mlib_s32*)dp, size, dtbb);
        } else {
          mlib_v_ImbgeLookUpSI_U8_S32_2_DstNonAl_D1(sp, (mlib_s32*)dp, size, dtbb);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_3_SrcOff0_D1(const mlib_u8  *src,
                                              mlib_s32       *dst,
                                              mlib_s32       xsize,
                                              const mlib_d64 *tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 *sb;              /* bligned pointer to source dbtb */
  mlib_u32 s0;               /* source dbtb */
  mlib_s32 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;   /* destinbtion dbtb */
  mlib_d64 t4, t5, t6, t7;   /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  mlib_s32 *ptr;
  mlib_u32 s00, s01, s02, s03;

  dl  = dst;
  sp  = (void *)src;
  dp  = (mlib_d64 *) dl;
  sb  = (mlib_u32*)sp;

  vis_blignbddr((void *) 0, 4);

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 20) & 0xFF0;
    s01 = (s0 >> 12) & 0xFF0;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp+=6) {
      s02 = (s0 >> 4) & 0xFF0;
      s03 = (s0 << 4) & 0xFF0;
      t0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
      t1 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
      t2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
      t3 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
      t4 = *(mlib_d64*)((mlib_u8*)tbble + s02);
      t5 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
      t6 = *(mlib_d64*)((mlib_u8*)tbble + s03);
      t7 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
      t1 = vis_fbligndbtb(t1, t1);
      t1 = vis_fbligndbtb(t1, t2);
      t2 = vis_fbligndbtb(t2, t3);
      t5 = vis_fbligndbtb(t5, t5);
      t5 = vis_fbligndbtb(t5, t6);
      t6 = vis_fbligndbtb(t6, t7);
      s0 = *sb++;
      s00 = (s0 >> 20) & 0xFF0;
      s01 = (s0 >> 12) & 0xFF0;
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t4;
      dp[4] = t5;
      dp[5] = t6;
    }

    s02 = (s0 >> 4) & 0xFF0;
    s03 = (s0 << 4) & 0xFF0;
    t0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
    t1 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
    t2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    t3 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
    t4 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    t5 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
    t6 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    t7 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
    t1 = vis_fbligndbtb(t1, t1);
    t1 = vis_fbligndbtb(t1, t2);
    t2 = vis_fbligndbtb(t2, t3);
    t5 = vis_fbligndbtb(t5, t5);
    t5 = vis_fbligndbtb(t5, t6);
    t6 = vis_fbligndbtb(t6, t7);
    dp[0] = t0;
    dp[1] = t1;
    dp[2] = t2;
    dp[3] = t4;
    dp[4] = t5;
    dp[5] = t6;
    i += 4; dp += 6;
  }

  dl = (mlib_s32*)dp;
  sp = (mlib_u8*)sb;

#prbgmb pipeloop(0)
  for (; i < xsize; i++) {
    ptr = (mlib_s32*)(tbble + (sp[0] << 1));
    dl[0] = ptr[0];
    dl[1] = ptr[1];
    dl[2] = ptr[2];
    dl += 3; sp ++;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_3_DstNonAl_D1(const mlib_u8  *src,
                                               mlib_s32       *dst,
                                               mlib_s32       xsize,
                                               const mlib_d64 *tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 *sb;              /* bligned pointer to source dbtb */
  mlib_u32 s0;               /* source dbtb */
  mlib_s32 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;   /* destinbtion dbtb */
  mlib_d64 t4, t5, t6, t7;   /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  mlib_s32 *ptr;
  mlib_u32 s00, s01, s02, s03;

  dl  = dst;
  sp  = (void *)src;
  dp   = (mlib_d64 *) ((mlib_bddr) dl & (~7));
  sb  = (mlib_u32*)sp;

  vis_blignbddr((void *) 0, 4);

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 20) & 0xFF0;
    s01 = (s0 >> 12) & 0xFF0;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp+=6) {
      s02 = (s0 >> 4) & 0xFF0;
      s03 = (s0 << 4) & 0xFF0;
      t0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
      t1 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
      t2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
      t3 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
      t4 = *(mlib_d64*)((mlib_u8*)tbble + s02);
      t5 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
      t6 = *(mlib_d64*)((mlib_u8*)tbble + s03);
      t7 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
      t1 = vis_fbligndbtb(t0, t1);
      t3 = vis_fbligndbtb(t3, t3);
      t3 = vis_fbligndbtb(t3, t4);
      t4 = vis_fbligndbtb(t4, t5);
      s0 = *sb++;
      s00 = (s0 >> 20) & 0xFF0;
      s01 = (s0 >> 12) & 0xFF0;
      *(mlib_f32*)((mlib_f32*)dp + 1) = vis_rebd_hi(t0);
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp[4] = t4;
      dp[5] = t6;
      *(mlib_f32*)((mlib_f32*)dp + 12) = vis_rebd_hi(t7);
    }

    s02 = (s0 >> 4) & 0xFF0;
    s03 = (s0 << 4) & 0xFF0;
    t0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
    t1 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
    t2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    t3 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
    t4 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    t5 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
    t6 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    t7 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
    t1 = vis_fbligndbtb(t0, t1);
    t3 = vis_fbligndbtb(t3, t3);
    t3 = vis_fbligndbtb(t3, t4);
    t4 = vis_fbligndbtb(t4, t5);
    *(mlib_f32*)((mlib_f32*)dp + 1) = vis_rebd_hi(t0);
    dp[1] = t1;
    dp[2] = t2;
    dp[3] = t3;
    dp[4] = t4;
    dp[5] = t6;
    *(mlib_f32*)((mlib_f32*)dp + 12) = vis_rebd_hi(t7);
    i += 4; dp += 6;
  }

  dl = (mlib_s32*)dp + 1;
  sp = (mlib_u8*)sb;

#prbgmb pipeloop(0)
  for (; i < xsize; i++) {
    ptr = (mlib_s32*)(tbble + (sp[0] << 1));
    dl[0] = ptr[0];
    dl[1] = ptr[1];
    dl[2] = ptr[2];
    dl += 3; sp ++;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_3_SMALL(const mlib_u8  *src,
                                         mlib_s32       *dst,
                                         mlib_s32       xsize,
                                         const mlib_s32 **tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0;           /* source dbtb */
  mlib_f32 *dp;          /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_f32 bcc4, bcc5;   /* destinbtion dbtb */
  mlib_f32 bcc6, bcc7;   /* destinbtion dbtb */
  mlib_f32 bcc8, bcc9;   /* destinbtion dbtb */
  mlib_f32 bcc10, bcc11; /* destinbtion dbtb */
  mlib_f32 *tbble0 = (mlib_f32*)tbble[0];
  mlib_f32 *tbble1 = (mlib_f32*)tbble[1];
  mlib_f32 *tbble2 = (mlib_f32*)tbble[2];
  mlib_s32 i;            /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb   = (mlib_u32*)src;
  dp   = (mlib_f32*)dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 22) & 0x3FC;
    s01 = (s0 >> 14) & 0x3FC;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 12) {
      s02 = (s0 >> 6) & 0x3FC;
      s03 = (s0 << 2) & 0x3FC;
      bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
      bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
      bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
      bcc3 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
      bcc4 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
      bcc5 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
      bcc6 = *(mlib_f32*)((mlib_u8*)tbble0 + s02);
      bcc7 = *(mlib_f32*)((mlib_u8*)tbble1 + s02);
      bcc8 = *(mlib_f32*)((mlib_u8*)tbble2 + s02);
      bcc9 = *(mlib_f32*)((mlib_u8*)tbble0 + s03);
      bcc10 = *(mlib_f32*)((mlib_u8*)tbble1 + s03);
      bcc11 = *(mlib_f32*)((mlib_u8*)tbble2 + s03);
      s0 = *sb++;
      s00 = (s0 >> 22) & 0x3FC;
      s01 = (s0 >> 14) & 0x3FC;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
      dp[4] = bcc4;
      dp[5] = bcc5;
      dp[6] = bcc6;
      dp[7] = bcc7;
      dp[8] = bcc8;
      dp[9] = bcc9;
      dp[10] = bcc10;
      dp[11] = bcc11;
    }

    s02 = (s0 >> 6) & 0x3FC;
    s03 = (s0 << 2) & 0x3FC;
    bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
    bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
    bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
    bcc3 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
    bcc4 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
    bcc5 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
    bcc6 = *(mlib_f32*)((mlib_u8*)tbble0 + s02);
    bcc7 = *(mlib_f32*)((mlib_u8*)tbble1 + s02);
    bcc8 = *(mlib_f32*)((mlib_u8*)tbble2 + s02);
    bcc9 = *(mlib_f32*)((mlib_u8*)tbble0 + s03);
    bcc10 = *(mlib_f32*)((mlib_u8*)tbble1 + s03);
    bcc11 = *(mlib_f32*)((mlib_u8*)tbble2 + s03);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp[4] = bcc4;
    dp[5] = bcc5;
    dp[6] = bcc6;
    dp[7] = bcc7;
    dp[8] = bcc8;
    dp[9] = bcc9;
    dp[10] = bcc10;
    dp[11] = bcc11;
    dp += 12;
    i += 4;
  }

  sp = (mlib_u8*)sb;

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_3(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s32 **tbble)
{
  if ((xsize * ysize) < 600) {
    mlib_u8  *sl;
    mlib_s32 *dl;
    mlib_s32 j, i;
    const mlib_s32 *tbb0 = tbble[0];
    const mlib_s32 *tbb1 = tbble[1];
    const mlib_s32 *tbb2 = tbble[2];

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8  *sp = sl;
      mlib_s32 *dp = dl;
      mlib_s32 off, size = xsize;

      off = (mlib_s32)((4 - ((mlib_bddr)sp & 3)) & 3);

      off = (off < size) ? off : size;

      for (i = 0; i < off; i++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        size--; sp++;
      }

      if (size > 0) {
        mlib_v_ImbgeLookUpSI_U8_S32_3_SMALL(sp, (mlib_s32*)dp, size, tbble);
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }

  } else {
    mlib_u8  *sl;
    mlib_s32 *dl;
    mlib_d64 dtbb[512];
    mlib_u32 *tbb;
    mlib_u32 *tbb0 = (mlib_u32*)tbble[0];
    mlib_u32 *tbb1 = (mlib_u32*)tbble[1];
    mlib_u32 *tbb2 = (mlib_u32*)tbble[2];
    mlib_s32 i, j;
    mlib_u32 s0, s1, s2;

    tbb = (mlib_u32*)dtbb;
    s0 = tbb0[0];
    s1 = tbb1[0];
    s2 = tbb2[0];
    for (i = 0; i < 255; i++) {
      tbb[4*i] = s0;
      tbb[4*i+1] = s1;
      tbb[4*i+2] = s2;
      s0 = tbb0[i+1];
      s1 = tbb1[i+1];
      s2 = tbb2[i+1];
    }

    tbb[1020] = s0;
    tbb[1021] = s1;
    tbb[1022] = s2;

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8  *sp = sl;
      mlib_u32 *dp = (mlib_u32*)dl;
      mlib_s32 off, size = xsize;

      off = (mlib_s32)((4 - ((mlib_bddr)sp & 3)) & 3);

      off = (off < size) ? off : size;

#prbgmb pipeloop(0)
      for (i = 0; i < off; i++) {
        dp[0] = tbb0[sp[0]];
        dp[1] = tbb1[sp[0]];
        dp[2] = tbb2[sp[0]];
        dp += 3; sp++;
      }

      size -= off;

      if (size > 0) {
        if (((mlib_bddr)dp & 7) == 0) {
          mlib_v_ImbgeLookUpSI_U8_S32_3_SrcOff0_D1(sp, (mlib_s32*)dp, size, dtbb);
        } else {
          mlib_v_ImbgeLookUpSI_U8_S32_3_DstNonAl_D1(sp, (mlib_s32*)dp, size, dtbb);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_4_SrcOff0_D1(const mlib_u8  *src,
                                              mlib_s32       *dst,
                                              mlib_s32       xsize,
                                              const mlib_d64 *tbble)
{
  mlib_u32 *sb;            /* bligned pointer to source dbtb */
  mlib_u8  *sp;            /* pointer to source dbtb */
  mlib_u32 s0;             /* source dbtb */
  mlib_d64 *dp;            /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3; /* destinbtion dbtb */
  mlib_d64 t4, t5, t6, t7; /* destinbtion dbtb */
  mlib_s32 i;              /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb   = (mlib_u32*)src;
  dp   = (mlib_d64 *) dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 20) & 0xFF0;
    s01 = (s0 >> 12) & 0xFF0;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp+=8) {
      s02 = (s0 >> 4) & 0xFF0;
      s03 = (s0 << 4) & 0xFF0;
      t0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
      t1 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
      t2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
      t3 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
      t4 = *(mlib_d64*)((mlib_u8*)tbble + s02);
      t5 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
      t6 = *(mlib_d64*)((mlib_u8*)tbble + s03);
      t7 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
      s0 = *sb++;
      s00 = (s0 >> 20) & 0xFF0;
      s01 = (s0 >> 12) & 0xFF0;
      dp[0] = t0;
      dp[1] = t1;
      dp[2] = t2;
      dp[3] = t3;
      dp[4] = t4;
      dp[5] = t5;
      dp[6] = t6;
      dp[7] = t7;
    }

    s02 = (s0 >> 4) & 0xFF0;
    s03 = (s0 << 4) & 0xFF0;
    t0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
    t1 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
    t2 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    t3 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
    t4 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    t5 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
    t6 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    t7 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
    dp[0] = t0;
    dp[1] = t1;
    dp[2] = t2;
    dp[3] = t3;
    dp[4] = t4;
    dp[5] = t5;
    dp[6] = t6;
    dp[7] = t7;
    dp += 8;
    i += 4;
  }

  sp = (mlib_u8*)sb;

  if ( i < xsize ) {
    *dp++ = tbble[2*sp[0]];
    *dp++ = tbble[2*sp[0] + 1];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble[2*sp[0]];
    *dp++ = tbble[2*sp[0] + 1];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble[2*sp[0]];
    *dp++ = tbble[2*sp[0] + 1];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_4_DstNonAl_D1(const mlib_u8  *src,
                                               mlib_s32       *dst,
                                               mlib_s32       xsize,
                                               const mlib_d64 *tbble)
{
  mlib_u32 *sb;                /* bligned pointer to source dbtb */
  mlib_u8  *sp;                /* pointer to source dbtb */
  mlib_u32 s0;                 /* source dbtb */
  mlib_s32 *dl;                /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;                /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;     /* destinbtion dbtb */
  mlib_d64 t4, t5, t6, t7, t8; /* destinbtion dbtb */
  mlib_s32 i;                  /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb = (mlib_u32*)src;
  dl = dst;
  dp   = (mlib_d64 *) ((mlib_bddr) dl & (~7)) + 1;
  vis_blignbddr(dp, 4);
  s0 = *sb++;
  s00 = (s0 >> 20) & 0xFF0;
  t0 = *(mlib_d64*)((mlib_u8*)tbble + s00);
  t1 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
  *(mlib_f32*)dl = vis_rebd_hi(t0);
  dp[0] = vis_fbligndbtb(t0, t1);
  t0 = t1;
  xsize--; dp++;
  sp = (mlib_u8*)sb - 3;

  if (xsize >= 3) {
    s01 = (s0 >> 12) & 0xFF0;
    s02 = (s0 >> 4) & 0xFF0;
    s03 = (s0 << 4) & 0xFF0;
    t1 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    t2 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
    t3 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    t4 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
    t5 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    t6 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
    dp[0] = vis_fbligndbtb(t0, t1);
    dp[1] = vis_fbligndbtb(t1, t2);
    dp[2] = vis_fbligndbtb(t2, t3);
    dp[3] = vis_fbligndbtb(t3, t4);
    dp[4] = vis_fbligndbtb(t4, t5);
    dp[5] = vis_fbligndbtb(t5, t6);
    t0 = t6; dp += 6; xsize -= 3;
    sp = (mlib_u8*)sb;
  }

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 20) & 0xFF0;
    s01 = (s0 >> 12) & 0xFF0;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 8) {
      s02 = (s0 >> 4) & 0xFF0;
      s03 = (s0 << 4) & 0xFF0;
      t1 = *(mlib_d64*)((mlib_u8*)tbble + s00);
      t2 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
      t3 = *(mlib_d64*)((mlib_u8*)tbble + s01);
      t4 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
      t5 = *(mlib_d64*)((mlib_u8*)tbble + s02);
      t6 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
      t7 = *(mlib_d64*)((mlib_u8*)tbble + s03);
      t8 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
      s0 = *sb++;
      s00 = (s0 >> 20) & 0xFF0;
      s01 = (s0 >> 12) & 0xFF0;
      dp[0] = vis_fbligndbtb(t0, t1);
      dp[1] = vis_fbligndbtb(t1, t2);
      dp[2] = vis_fbligndbtb(t2, t3);
      dp[3] = vis_fbligndbtb(t3, t4);
      dp[4] = vis_fbligndbtb(t4, t5);
      dp[5] = vis_fbligndbtb(t5, t6);
      dp[6] = vis_fbligndbtb(t6, t7);
      dp[7] = vis_fbligndbtb(t7, t8);
      t0 = t8;
    }

    s02 = (s0 >> 4) & 0xFF0;
    s03 = (s0 << 4) & 0xFF0;
    t1 = *(mlib_d64*)((mlib_u8*)tbble + s00);
    t2 = *(mlib_d64*)((mlib_u8*)tbble + s00 + 8);
    t3 = *(mlib_d64*)((mlib_u8*)tbble + s01);
    t4 = *(mlib_d64*)((mlib_u8*)tbble + s01 + 8);
    t5 = *(mlib_d64*)((mlib_u8*)tbble + s02);
    t6 = *(mlib_d64*)((mlib_u8*)tbble + s02 + 8);
    t7 = *(mlib_d64*)((mlib_u8*)tbble + s03);
    t8 = *(mlib_d64*)((mlib_u8*)tbble + s03 + 8);
    dp[0] = vis_fbligndbtb(t0, t1);
    dp[1] = vis_fbligndbtb(t1, t2);
    dp[2] = vis_fbligndbtb(t2, t3);
    dp[3] = vis_fbligndbtb(t3, t4);
    dp[4] = vis_fbligndbtb(t4, t5);
    dp[5] = vis_fbligndbtb(t5, t6);
    dp[6] = vis_fbligndbtb(t6, t7);
    dp[7] = vis_fbligndbtb(t7, t8);
    t0 = t8;
    dp += 8;
    i += 4;
    sp = (mlib_u8*)sb;
  }

  if ( i < xsize ) {
    t1 = tbble[2*sp[0]];
    t2 = tbble[2*sp[0] + 1];
    *dp++ = vis_fbligndbtb(t0, t1);
    *dp++ = vis_fbligndbtb(t1, t2);
    i++; sp++;
    t0 = t2;
  }

  if ( i < xsize ) {
    t1 = tbble[2*sp[0]];
    t2 = tbble[2*sp[0] + 1];
    *dp++ = vis_fbligndbtb(t0, t1);
    *dp++ = vis_fbligndbtb(t1, t2);
    i++; sp++;
    t0 = t2;
  }

  if ( i < xsize ) {
    t1 = tbble[2*sp[0]];
    t2 = tbble[2*sp[0] + 1];
    *dp++ = vis_fbligndbtb(t0, t1);
    *dp++ = vis_fbligndbtb(t1, t2);
    t0 = t2;
  }

  *(mlib_f32*) dp = vis_rebd_lo(t0);
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_4_SMALL(const mlib_u8  *src,
                                         mlib_s32       *dst,
                                         mlib_s32       xsize,
                                         const mlib_s32 **tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0;           /* source dbtb */
  mlib_f32 *dp;          /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_f32 bcc4, bcc5;   /* destinbtion dbtb */
  mlib_f32 bcc6, bcc7;   /* destinbtion dbtb */
  mlib_f32 bcc8, bcc9;   /* destinbtion dbtb */
  mlib_f32 bcc10, bcc11; /* destinbtion dbtb */
  mlib_f32 bcc12, bcc13; /* destinbtion dbtb */
  mlib_f32 bcc14, bcc15; /* destinbtion dbtb */
  mlib_f32 *tbble0 = (mlib_f32*)tbble[0];
  mlib_f32 *tbble1 = (mlib_f32*)tbble[1];
  mlib_f32 *tbble2 = (mlib_f32*)tbble[2];
  mlib_f32 *tbble3 = (mlib_f32*)tbble[3];
  mlib_s32 i;            /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb   = (mlib_u32*)src;
  dp   = (mlib_f32*)dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 22) & 0x3FC;
    s01 = (s0 >> 14) & 0x3FC;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 16) {
      s02 = (s0 >> 6) & 0x3FC;
      s03 = (s0 << 2) & 0x3FC;
      bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
      bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
      bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
      bcc3 = *(mlib_f32*)((mlib_u8*)tbble3 + s00);
      bcc4 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
      bcc5 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
      bcc6 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
      bcc7 = *(mlib_f32*)((mlib_u8*)tbble3 + s01);
      bcc8 = *(mlib_f32*)((mlib_u8*)tbble0 + s02);
      bcc9 = *(mlib_f32*)((mlib_u8*)tbble1 + s02);
      bcc10 = *(mlib_f32*)((mlib_u8*)tbble2 + s02);
      bcc11 = *(mlib_f32*)((mlib_u8*)tbble3 + s02);
      bcc12 = *(mlib_f32*)((mlib_u8*)tbble0 + s03);
      bcc13 = *(mlib_f32*)((mlib_u8*)tbble1 + s03);
      bcc14 = *(mlib_f32*)((mlib_u8*)tbble2 + s03);
      bcc15 = *(mlib_f32*)((mlib_u8*)tbble3 + s03);
      s0 = *sb++;
      s00 = (s0 >> 22) & 0x3FC;
      s01 = (s0 >> 14) & 0x3FC;
      dp[0] = bcc0;
      dp[1] = bcc1;
      dp[2] = bcc2;
      dp[3] = bcc3;
      dp[4] = bcc4;
      dp[5] = bcc5;
      dp[6] = bcc6;
      dp[7] = bcc7;
      dp[8] = bcc8;
      dp[9] = bcc9;
      dp[10] = bcc10;
      dp[11] = bcc11;
      dp[12] = bcc12;
      dp[13] = bcc13;
      dp[14] = bcc14;
      dp[15] = bcc15;
    }

    s02 = (s0 >> 6) & 0x3FC;
    s03 = (s0 << 2) & 0x3FC;
    bcc0 = *(mlib_f32*)((mlib_u8*)tbble0 + s00);
    bcc1 = *(mlib_f32*)((mlib_u8*)tbble1 + s00);
    bcc2 = *(mlib_f32*)((mlib_u8*)tbble2 + s00);
    bcc3 = *(mlib_f32*)((mlib_u8*)tbble3 + s00);
    bcc4 = *(mlib_f32*)((mlib_u8*)tbble0 + s01);
    bcc5 = *(mlib_f32*)((mlib_u8*)tbble1 + s01);
    bcc6 = *(mlib_f32*)((mlib_u8*)tbble2 + s01);
    bcc7 = *(mlib_f32*)((mlib_u8*)tbble3 + s01);
    bcc8 = *(mlib_f32*)((mlib_u8*)tbble0 + s02);
    bcc9 = *(mlib_f32*)((mlib_u8*)tbble1 + s02);
    bcc10 = *(mlib_f32*)((mlib_u8*)tbble2 + s02);
    bcc11 = *(mlib_f32*)((mlib_u8*)tbble3 + s02);
    bcc12 = *(mlib_f32*)((mlib_u8*)tbble0 + s03);
    bcc13 = *(mlib_f32*)((mlib_u8*)tbble1 + s03);
    bcc14 = *(mlib_f32*)((mlib_u8*)tbble2 + s03);
    bcc15 = *(mlib_f32*)((mlib_u8*)tbble3 + s03);
    dp[0] = bcc0;
    dp[1] = bcc1;
    dp[2] = bcc2;
    dp[3] = bcc3;
    dp[4] = bcc4;
    dp[5] = bcc5;
    dp[6] = bcc6;
    dp[7] = bcc7;
    dp[8] = bcc8;
    dp[9] = bcc9;
    dp[10] = bcc10;
    dp[11] = bcc11;
    dp[12] = bcc12;
    dp[13] = bcc13;
    dp[14] = bcc14;
    dp[15] = bcc15;
    dp += 16;
    i += 4;
  }

  sp = (mlib_u8*)sb;

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
    *dp++ = tbble3[sp[0]];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
    *dp++ = tbble3[sp[0]];
    i++; sp++;
  }

  if ( i < xsize ) {
    *dp++ = tbble0[sp[0]];
    *dp++ = tbble1[sp[0]];
    *dp++ = tbble2[sp[0]];
    *dp++ = tbble3[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_S32_4(const mlib_u8  *src,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsize,
                                   mlib_s32       ysize,
                                   const mlib_s32 **tbble)
{
  if ((xsize * ysize) < 600) {
    mlib_u8  *sl;
    mlib_s32 *dl;
    mlib_s32 j, i;
    const mlib_s32 *tbb0 = tbble[0];
    const mlib_s32 *tbb1 = tbble[1];
    const mlib_s32 *tbb2 = tbble[2];
    const mlib_s32 *tbb3 = tbble[3];

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8  *sp = sl;
      mlib_s32 *dp = dl;
      mlib_s32 off, size = xsize;

      off = (mlib_s32)((4 - ((mlib_bddr)sp & 3)) & 3);

      off = (off < size) ? off : size;

      for (i = 0; i < off; i++) {
        *dp++ = tbb0[sp[0]];
        *dp++ = tbb1[sp[0]];
        *dp++ = tbb2[sp[0]];
        *dp++ = tbb3[sp[0]];
        size--; sp++;
      }

      if (size > 0) {
        mlib_v_ImbgeLookUpSI_U8_S32_4_SMALL(sp, (mlib_s32*)dp, size, tbble);
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }

  } else {
    mlib_u8  *sl;
    mlib_s32 *dl;
    mlib_d64 dtbb[512];
    mlib_u32 *tbb;
    mlib_u32 *tbb0 = (mlib_u32*)tbble[0];
    mlib_u32 *tbb1 = (mlib_u32*)tbble[1];
    mlib_u32 *tbb2 = (mlib_u32*)tbble[2];
    mlib_u32 *tbb3 = (mlib_u32*)tbble[3];
    mlib_s32 i, j;
    mlib_u32 s0, s1, s2, s3;

    tbb = (mlib_u32*)dtbb;
    s0 = tbb0[0];
    s1 = tbb1[0];
    s2 = tbb2[0];
    s3 = tbb3[0];
    for (i = 0; i < 255; i++) {
      tbb[4*i] = s0;
      tbb[4*i+1] = s1;
      tbb[4*i+2] = s2;
      tbb[4*i+3] = s3;
      s0 = tbb0[i+1];
      s1 = tbb1[i+1];
      s2 = tbb2[i+1];
      s3 = tbb3[i+1];
    }

    tbb[1020] = s0;
    tbb[1021] = s1;
    tbb[1022] = s2;
    tbb[1023] = s3;

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8  *sp = sl;
      mlib_u32 *dp = (mlib_u32*)dl;
      mlib_s32 off, size = xsize;

      off = (mlib_s32)((4 - ((mlib_bddr)sp & 3)) & 3);

      off = (off < size) ? off : size;

#prbgmb pipeloop(0)
      for (i = 0; i < off; i++) {
        dp[0] = tbb0[sp[0]];
        dp[1] = tbb1[sp[0]];
        dp[2] = tbb2[sp[0]];
        dp[3] = tbb3[sp[0]];
        dp += 4; sp++;
      }

      size -= off;

      if (size > 0) {
        if (((mlib_bddr)dp & 7) == 0) {
          mlib_v_ImbgeLookUpSI_U8_S32_4_SrcOff0_D1(sp, (mlib_s32*)dp, size, dtbb);
        } else {
          mlib_v_ImbgeLookUpSI_U8_S32_4_DstNonAl_D1(sp, (mlib_s32*)dp, size, dtbb);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
