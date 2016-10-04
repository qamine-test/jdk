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
stbtic void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff0_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_u16 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff1_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_u16 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff2_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_u16 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff3_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_u16 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_2_DstNonAl_D1(const mlib_u8  *src,
                                                     mlib_u8        *dst,
                                                     mlib_s32       xsize,
                                                     const mlib_u16 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_2_DstA8D1_SMALL(const mlib_u8 *src,
                                                       mlib_u8       *dst,
                                                       mlib_s32      xsize,
                                                       const mlib_u8 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_2_D1_SMALL(const mlib_u8 *src,
                                                  mlib_u8       *dst,
                                                  mlib_s32      xsize,
                                                  const mlib_u8 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff0_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff1_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff2_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff3_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_d64 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_3_D1_SMALL(const mlib_u8 *src,
                                                  mlib_u8       *dst,
                                                  mlib_s32      xsize,
                                                  const mlib_u8 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_4_SrcOff0_D1(const mlib_u8  *src,
                                                    mlib_u8        *dst,
                                                    mlib_s32       xsize,
                                                    const mlib_f32 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_4_DstNonAl_D1(const mlib_u8  *src,
                                                     mlib_u8        *dst,
                                                     mlib_s32       xsize,
                                                     const mlib_f32 *tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff0_D1_SMALL(const mlib_u8 *src,
                                                          mlib_u8       *dst,
                                                          mlib_s32      xsize,
                                                          const mlib_u8 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff1_D1_SMALL(const mlib_u8 *src,
                                                          mlib_u8       *dst,
                                                          mlib_s32      xsize,
                                                          const mlib_u8 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff2_D1_SMALL(const mlib_u8 *src,
                                                          mlib_u8       *dst,
                                                          mlib_s32      xsize,
                                                          const mlib_u8 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff3_D1_SMALL(const mlib_u8 *src,
                                                          mlib_u8       *dst,
                                                          mlib_s32      xsize,
                                                          const mlib_u8 **tbble);

/***************************************************************/
#define VIS_LD_U8_I(X, Y)       vis_ld_u8_i((void *)(X), (Y))
#define VIS_LD_U16_I(X, Y)      vis_ld_u16_i((void *)(X), (Y))

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff0_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_u16 *tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0;           /* source dbtb */
  mlib_u16 *dl;          /* pointer to stbrt of destinbtion */
  mlib_u16 *dend;        /* pointer to end of destinbtion */
  mlib_d64 *dp;          /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;   /* destinbtion dbtb */
  mlib_d64 t3, bcc;      /* destinbtion dbtb */
  mlib_s32 embsk;        /* edge mbsk */
  mlib_s32 i, num;       /* loop vbribble */

  sb   = (mlib_u32*)src;
  dl   = (mlib_u16*)dst;
  dp   = (mlib_d64 *) dl;
  dend = dl + xsize - 1;

  vis_blignbddr((void *) 0, 6);

  if (xsize >= 4) {

    s0 = sb[0];
    sb ++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, sb++) {
      t3 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
      t2 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
      t1 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
      t0 = VIS_LD_U16_I(tbble, (s0 >> 23) & 0x1FE);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = sb[0];
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s0 >> 23) & 0x1FE);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }

  sp = (mlib_u8*)sb;

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (mlib_u16*) dend - (mlib_u16*) dp;
    sp  += num;
    num ++;
#prbgmb pipeloop(0)
    for (i = 0; i < num; i ++) {
      s0 = (mlib_s32) *sp;
      sp --;

      t0  = VIS_LD_U16_I(tbble, 2*s0);
      bcc = vis_fbligndbtb(t0, bcc);
    }

    embsk = vis_edge16(dp, dend);
    vis_pst_16(bcc, dp, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff1_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_u16 *tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0, s1;       /* source dbtb */
  mlib_u16 *dl;          /* pointer to stbrt of destinbtion */
  mlib_u16 *dend;        /* pointer to end of destinbtion */
  mlib_d64 *dp;          /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;   /* destinbtion dbtb */
  mlib_d64 t3, bcc;      /* destinbtion dbtb */
  mlib_s32 embsk;        /* edge mbsk */
  mlib_s32 i, num;       /* loop vbribble */

  sb   = (mlib_u32*)(src-1);
  dl   = (mlib_u16*)dst;
  dp   = (mlib_d64 *) dl;
  dend = dl + xsize - 1;

  vis_blignbddr((void *) 0, 6);

  s0 = *sb++;

  if (xsize >= 4) {

    s1 = sb[0];
    sb ++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, sb++) {
      t3 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
      t2 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
      t1 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
      t0 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s1;
      s1 = sb[0];
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }

  sp = (mlib_u8*)sb;
  sp -= 3;

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (mlib_u16*) dend - (mlib_u16*) dp;
    sp  += num;
    num ++;
#prbgmb pipeloop(0)
    for (i = 0; i < num; i ++) {
      s0 = (mlib_s32) *sp;
      sp --;

      t0  = VIS_LD_U16_I(tbble, 2*s0);
      bcc = vis_fbligndbtb(t0, bcc);
    }

    embsk = vis_edge16(dp, dend);
    vis_pst_16(bcc, dp, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff2_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_u16 *tbble)
{
  mlib_u32 *sb;          /* pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0, s1;       /* source dbtb */
  mlib_u16 *dl;          /* pointer to stbrt of destinbtion */
  mlib_u16 *dend;        /* pointer to end of destinbtion */
  mlib_d64 *dp;          /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;   /* destinbtion dbtb */
  mlib_d64 t3, bcc;      /* destinbtion dbtb */
  mlib_s32 embsk;        /* edge mbsk */
  mlib_s32 i, num;       /* loop vbribble */

  sb   = (mlib_u32*)(src-2);
  dl   = (mlib_u16*)dst;
  dp   = (mlib_d64 *) dl;
  dend = dl + xsize - 1;

  vis_blignbddr((void *) 0, 6);

  s0 = *sb++;

  if (xsize >= 4) {

    s1 = sb[0];
    sb ++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, sb++) {
      t3 = VIS_LD_U16_I(tbble, (s1 >> 15) & 0x1FE);
      t2 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
      t1 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
      t0 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s1;
      s1 = sb[0];
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbble, (s1 >> 15) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }

  sp = (mlib_u8*)sb;
  sp -= 2;

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (mlib_u16*) dend - (mlib_u16*) dp;
    sp  += num;
    num ++;
#prbgmb pipeloop(0)
    for (i = 0; i < num; i ++) {
      s0 = (mlib_s32) *sp;
      sp --;

      t0  = VIS_LD_U16_I(tbble, 2*s0);
      bcc = vis_fbligndbtb(t0, bcc);
    }

    embsk = vis_edge16(dp, dend);
    vis_pst_16(bcc, dp, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff3_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_u16 *tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0, s1;       /* source dbtb */
  mlib_u16 *dl;          /* pointer to stbrt of destinbtion */
  mlib_u16 *dend;        /* pointer to end of destinbtion */
  mlib_d64 *dp;          /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;   /* destinbtion dbtb */
  mlib_d64 t3, bcc;      /* destinbtion dbtb */
  mlib_s32 embsk;        /* edge mbsk */
  mlib_s32 i, num;       /* loop vbribble */

  sb   = (mlib_u32*)(src-3);
  dl   = (mlib_u16*)dst;
  dp   = (mlib_d64 *) dl;
  dend = dl + xsize - 1;

  vis_blignbddr((void *) 0, 6);

  s0 = *sb++;

  if (xsize >= 4) {

    s1 = sb[0];
    sb ++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, sb++) {
      t3 = VIS_LD_U16_I(tbble, (s1 >> 7) & 0x1FE);
      t2 = VIS_LD_U16_I(tbble, (s1 >> 15) & 0x1FE);
      t1 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
      t0 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s1;
      s1 = sb[0];
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbble, (s1 >> 7) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s1 >> 15) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }

  sp = (mlib_u8*)sb;
  sp -= 1;

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (mlib_u16*) dend - (mlib_u16*) dp;
    sp  += num;
    num ++;
#prbgmb pipeloop(0)
    for (i = 0; i < num; i ++) {
      s0 = (mlib_s32) *sp;
      sp --;

      t0  = VIS_LD_U16_I(tbble, 2*s0);
      bcc = vis_fbligndbtb(t0, bcc);
    }

    embsk = vis_edge16(dp, dend);
    vis_pst_16(bcc, dp, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2_DstNonAl_D1(const mlib_u8  *src,
                                              mlib_u8        *dst,
                                              mlib_s32       xsize,
                                              const mlib_u16 *tbble)
{
  mlib_u32 *sb;             /* bligned pointer to source dbtb */
  mlib_u8  *sp;             /* pointer to source dbtb */
  mlib_u32 s0, s1, s2, s3;  /* source dbtb */
  mlib_u8  *dl;             /* pointer to stbrt of destinbtion */
  mlib_u8  *dend;           /* pointer to end of destinbtion */
  mlib_d64 *dp;             /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;      /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;      /* destinbtion dbtb */
  mlib_d64 t6, t7, bcc0;    /* destinbtion dbtb */
  mlib_d64 bcc1, bcc2;      /* destinbtion dbtb */
  mlib_d64 bcc3, bcc4;      /* destinbtion dbtb */
  mlib_s32 embsk;           /* edge mbsk */
  mlib_s32 i, num;          /* loop vbribble */
  mlib_s32 off;             /* offset */

  sb   = (mlib_u32*)src;
  dl   = dst;
  sp   = (void *)src;
  dend = dl + 2*xsize - 1;
  dp   = (mlib_d64 *) ((mlib_bddr) dl & (~7));
  off  = (mlib_bddr) dp - (mlib_bddr) dl;

  embsk = vis_edge8(dl, dend);
  num = (xsize < 4) ? xsize : 4;

  sp += (num-1);

  vis_blignbddr(dp, 6);

  for (i = 0; i < num; i ++) {
    s0 = (mlib_s32) *sp;
    sp --;

    t0  = VIS_LD_U16_I(tbble, 2*s0);
    bcc0 = vis_fbligndbtb(t0, bcc0);
  }

  vis_blignbddr(dp, off);
  vis_pst_8(vis_fbligndbtb(bcc0, bcc0), dp++, embsk);

  sb++;

  xsize -= 4;

  i = 0;

  if (xsize >= 16) {

    s0 = sb[0];
    s1 = sb[1];
    s2 = sb[2];
    s3 = sb[3];
    sb += 4;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 32; i+=16, sb+=4) {
      vis_blignbddr(dp, 6);
      t3 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
      t2 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
      t1 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
      t0 = VIS_LD_U16_I(tbble, (s0 >> 23) & 0x1FE);
      bcc1 = vis_fbligndbtb(t3, bcc1);
      bcc1 = vis_fbligndbtb(t2, bcc1);
      bcc1 = vis_fbligndbtb(t1, bcc1);
      bcc1 = vis_fbligndbtb(t0, bcc1);
      t7 = VIS_LD_U16_I(tbble, (s1 << 1) & 0x1FE);
      t6 = VIS_LD_U16_I(tbble, (s1 >> 7) & 0x1FE);
      t5 = VIS_LD_U16_I(tbble, (s1 >> 15) & 0x1FE);
      t4 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
      bcc2 = vis_fbligndbtb(t7, bcc2);
      bcc2 = vis_fbligndbtb(t6, bcc2);
      bcc2 = vis_fbligndbtb(t5, bcc2);
      bcc2 = vis_fbligndbtb(t4, bcc2);
      t3 = VIS_LD_U16_I(tbble, (s2 << 1) & 0x1FE);
      t2 = VIS_LD_U16_I(tbble, (s2 >> 7) & 0x1FE);
      t1 = VIS_LD_U16_I(tbble, (s2 >> 15) & 0x1FE);
      t0 = VIS_LD_U16_I(tbble, (s2 >> 23) & 0x1FE);
      bcc3 = vis_fbligndbtb(t3, bcc3);
      bcc3 = vis_fbligndbtb(t2, bcc3);
      bcc3 = vis_fbligndbtb(t1, bcc3);
      bcc3 = vis_fbligndbtb(t0, bcc3);
      t7 = VIS_LD_U16_I(tbble, (s3 << 1) & 0x1FE);
      t6 = VIS_LD_U16_I(tbble, (s3 >> 7) & 0x1FE);
      t5 = VIS_LD_U16_I(tbble, (s3 >> 15) & 0x1FE);
      t4 = VIS_LD_U16_I(tbble, (s3 >> 23) & 0x1FE);
      bcc4 = vis_fbligndbtb(t7, bcc4);
      bcc4 = vis_fbligndbtb(t6, bcc4);
      bcc4 = vis_fbligndbtb(t5, bcc4);
      bcc4 = vis_fbligndbtb(t4, bcc4);
      vis_blignbddr(dp, off);
      s0 = sb[0];
      s1 = sb[1];
      s2 = sb[2];
      s3 = sb[3];
      *dp++ = vis_fbligndbtb(bcc0, bcc1);
      *dp++ = vis_fbligndbtb(bcc1, bcc2);
      *dp++ = vis_fbligndbtb(bcc2, bcc3);
      *dp++ = vis_fbligndbtb(bcc3, bcc4);
      bcc0 = bcc4;
    }

    vis_blignbddr(dp, 6);
    t3 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s0 >> 23) & 0x1FE);
    bcc1 = vis_fbligndbtb(t3, bcc1);
    bcc1 = vis_fbligndbtb(t2, bcc1);
    bcc1 = vis_fbligndbtb(t1, bcc1);
    bcc1 = vis_fbligndbtb(t0, bcc1);
    t7 = VIS_LD_U16_I(tbble, (s1 << 1) & 0x1FE);
    t6 = VIS_LD_U16_I(tbble, (s1 >> 7) & 0x1FE);
    t5 = VIS_LD_U16_I(tbble, (s1 >> 15) & 0x1FE);
    t4 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
    bcc2 = vis_fbligndbtb(t7, bcc2);
    bcc2 = vis_fbligndbtb(t6, bcc2);
    bcc2 = vis_fbligndbtb(t5, bcc2);
    bcc2 = vis_fbligndbtb(t4, bcc2);
    t3 = VIS_LD_U16_I(tbble, (s2 << 1) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s2 >> 7) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s2 >> 15) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s2 >> 23) & 0x1FE);
    bcc3 = vis_fbligndbtb(t3, bcc3);
    bcc3 = vis_fbligndbtb(t2, bcc3);
    bcc3 = vis_fbligndbtb(t1, bcc3);
    bcc3 = vis_fbligndbtb(t0, bcc3);
    t7 = VIS_LD_U16_I(tbble, (s3 << 1) & 0x1FE);
    t6 = VIS_LD_U16_I(tbble, (s3 >> 7) & 0x1FE);
    t5 = VIS_LD_U16_I(tbble, (s3 >> 15) & 0x1FE);
    t4 = VIS_LD_U16_I(tbble, (s3 >> 23) & 0x1FE);
    bcc4 = vis_fbligndbtb(t7, bcc4);
    bcc4 = vis_fbligndbtb(t6, bcc4);
    bcc4 = vis_fbligndbtb(t5, bcc4);
    bcc4 = vis_fbligndbtb(t4, bcc4);
    vis_blignbddr(dp, off);
    *dp++ = vis_fbligndbtb(bcc0, bcc1);
    *dp++ = vis_fbligndbtb(bcc1, bcc2);
    *dp++ = vis_fbligndbtb(bcc2, bcc3);
    *dp++ = vis_fbligndbtb(bcc3, bcc4);
    bcc0 = bcc4; i+=16;
  }

  if (i <= xsize - 8) {
    s0 = sb[0];
    s1 = sb[1];
    vis_blignbddr(dp, 6);
    t3 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s0 >> 23) & 0x1FE);
    bcc1 = vis_fbligndbtb(t3, bcc1);
    bcc1 = vis_fbligndbtb(t2, bcc1);
    bcc1 = vis_fbligndbtb(t1, bcc1);
    bcc1 = vis_fbligndbtb(t0, bcc1);
    t7 = VIS_LD_U16_I(tbble, (s1 << 1) & 0x1FE);
    t6 = VIS_LD_U16_I(tbble, (s1 >> 7) & 0x1FE);
    t5 = VIS_LD_U16_I(tbble, (s1 >> 15) & 0x1FE);
    t4 = VIS_LD_U16_I(tbble, (s1 >> 23) & 0x1FE);
    bcc2 = vis_fbligndbtb(t7, bcc2);
    bcc2 = vis_fbligndbtb(t6, bcc2);
    bcc2 = vis_fbligndbtb(t5, bcc2);
    bcc2 = vis_fbligndbtb(t4, bcc2);
    vis_blignbddr(dp, off);
    *dp++ = vis_fbligndbtb(bcc0, bcc1);
    *dp++ = vis_fbligndbtb(bcc1, bcc2);
    bcc0 = bcc2; i += 8; sb += 2;
  }

  if (i <= xsize - 4) {
    s0 = *sb++;
    vis_blignbddr(dp, 6);
    t3 = VIS_LD_U16_I(tbble, (s0 << 1) & 0x1FE);
    t2 = VIS_LD_U16_I(tbble, (s0 >> 7) & 0x1FE);
    t1 = VIS_LD_U16_I(tbble, (s0 >> 15) & 0x1FE);
    t0 = VIS_LD_U16_I(tbble, (s0 >> 23) & 0x1FE);
    bcc1 = vis_fbligndbtb(t3, bcc1);
    bcc1 = vis_fbligndbtb(t2, bcc1);
    bcc1 = vis_fbligndbtb(t1, bcc1);
    bcc1 = vis_fbligndbtb(t0, bcc1);
    vis_blignbddr(dp, off);
    *dp++ = vis_fbligndbtb(bcc0, bcc1);
    bcc0 = bcc1;
  }

  sp = (mlib_u8*)sb;

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (((mlib_u8*) dend - (mlib_u8*) dp) + off + 1) >> 1;
    sp  += (num - 1);
    vis_blignbddr(dp, 6);
#prbgmb pipeloop(0)
    for (i = 0; i < num; i ++) {
      s0 = (mlib_s32) *sp;
      sp --;

      t0  = VIS_LD_U16_I(tbble, 2*s0);
      bcc1 = vis_fbligndbtb(t0, bcc1);
    }

    vis_blignbddr(dp, off);
    embsk = vis_edge8(dp, dend);
    vis_pst_8(vis_fbligndbtb(bcc0, bcc1), dp++, embsk);
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {
    embsk = vis_edge8(dp, dend);
    vis_pst_8(vis_fbligndbtb(bcc1, bcc1), dp++, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2_DstA8D1_SMALL(const mlib_u8 *src,
                                                mlib_u8       *dst,
                                                mlib_s32      xsize,
                                                const mlib_u8 **tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 s0, s1, s2, s3;   /* source dbtb */
  mlib_u16 *dl;              /* pointer to stbrt of destinbtion */
  mlib_u16 *dend;            /* pointer to end of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;       /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;       /* destinbtion dbtb */
  mlib_d64 t6, t7, bcc;      /* destinbtion dbtb */
  mlib_s32 embsk;            /* edge mbsk */
  mlib_s32 i, num;           /* loop vbribble */
  const mlib_u8  *tbb0 = tbble[0];
  const mlib_u8  *tbb1 = tbble[1];

  sp   = (void *)src;
  dl   = (mlib_u16*)dst;
  dp   = (mlib_d64 *) dl;
  dend = dl + xsize - 1;

  vis_blignbddr((void *) 0, 7);

  if (xsize >= 4) {

    s0 = sp[0];
    s1 = sp[1];
    s2 = sp[2];
    s3 = sp[3];
    sp += 4;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, sp+=4) {
      t7 = VIS_LD_U8_I(tbb1, s3);
      t6 = VIS_LD_U8_I(tbb0, s3);
      t5 = VIS_LD_U8_I(tbb1, s2);
      t4 = VIS_LD_U8_I(tbb0, s2);
      t3 = VIS_LD_U8_I(tbb1, s1);
      t2 = VIS_LD_U8_I(tbb0, s1);
      t1 = VIS_LD_U8_I(tbb1, s0);
      t0 = VIS_LD_U8_I(tbb0, s0);
      bcc = vis_fbligndbtb(t7, bcc);
      bcc = vis_fbligndbtb(t6, bcc);
      bcc = vis_fbligndbtb(t5, bcc);
      bcc = vis_fbligndbtb(t4, bcc);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[2];
      s3 = sp[3];
      *dp++ = bcc;
    }

    t7 = VIS_LD_U8_I(tbb1, s3);
    t6 = VIS_LD_U8_I(tbb0, s3);
    t5 = VIS_LD_U8_I(tbb1, s2);
    t4 = VIS_LD_U8_I(tbb0, s2);
    t3 = VIS_LD_U8_I(tbb1, s1);
    t2 = VIS_LD_U8_I(tbb0, s1);
    t1 = VIS_LD_U8_I(tbb1, s0);
    t0 = VIS_LD_U8_I(tbb0, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (mlib_u16*) dend - (mlib_u16*) dp;
    sp  += num;
    num ++;
#prbgmb pipeloop(0)
    for (i = 0; i < num; i ++) {
      s0 = (mlib_s32) *sp;
      sp --;

      t0  = VIS_LD_U8_I(tbb1, s0);
      bcc = vis_fbligndbtb(t0, bcc);

      t0  = VIS_LD_U8_I(tbb0, s0);
      bcc = vis_fbligndbtb(t0, bcc);
    }

    embsk = vis_edge16(dp, dend);
    vis_pst_16(bcc, dp, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2_D1_SMALL(const mlib_u8 *src,
                                           mlib_u8       *dst,
                                           mlib_s32      xsize,
                                           const mlib_u8 **tbble)
{
  mlib_u8  *sp;                /* pointer to source dbtb */
  mlib_u32 s0, s1, s2, s3, s4; /* source dbtb */
  mlib_u8  *dl;                /* pointer to stbrt of destinbtion */
  mlib_u8  *dend;              /* pointer to end of destinbtion */
  mlib_d64 *dp;                /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;         /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;         /* destinbtion dbtb */
  mlib_d64 t6, t7, bcc;        /* destinbtion dbtb */
  mlib_s32 embsk;              /* edge mbsk */
  mlib_s32 i, num;             /* loop vbribble */
  const mlib_u8  *tbb0 = tbble[0];
  const mlib_u8  *tbb1 = tbble[1];

  sp   = (void *)src;
  dl   = dst;

  dend = dl + 2 * xsize - 1;

  vis_blignbddr((void *) 0, 7);

  s0 = *sp++;
  *dl++ = tbb0[s0];
  dp   = (mlib_d64 *) dl;
  xsize--;

  if (xsize >= 4) {

    s1 = sp[0];
    s2 = sp[1];
    s3 = sp[2];
    s4 = sp[3];
    sp += 4;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, sp+=4) {
      t7 = VIS_LD_U8_I(tbb0, s4);
      t6 = VIS_LD_U8_I(tbb1, s3);
      t5 = VIS_LD_U8_I(tbb0, s3);
      t4 = VIS_LD_U8_I(tbb1, s2);
      t3 = VIS_LD_U8_I(tbb0, s2);
      t2 = VIS_LD_U8_I(tbb1, s1);
      t1 = VIS_LD_U8_I(tbb0, s1);
      t0 = VIS_LD_U8_I(tbb1, s0);
      bcc = vis_fbligndbtb(t7, bcc);
      bcc = vis_fbligndbtb(t6, bcc);
      bcc = vis_fbligndbtb(t5, bcc);
      bcc = vis_fbligndbtb(t4, bcc);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s4;
      s1 = sp[0];
      s2 = sp[1];
      s3 = sp[2];
      s4 = sp[3];
      *dp++ = bcc;
    }

    t7 = VIS_LD_U8_I(tbb0, s4);
    t6 = VIS_LD_U8_I(tbb1, s3);
    t5 = VIS_LD_U8_I(tbb0, s3);
    t4 = VIS_LD_U8_I(tbb1, s2);
    t3 = VIS_LD_U8_I(tbb0, s2);
    t2 = VIS_LD_U8_I(tbb1, s1);
    t1 = VIS_LD_U8_I(tbb0, s1);
    t0 = VIS_LD_U8_I(tbb1, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s4;
    *dp++ = bcc;
  }

  num = ((mlib_u8*) dend - (mlib_u8*) dp) >> 1;
  sp  += num;
  num ++;

#prbgmb pipeloop(0)
  for (i = 0; i < num; i ++) {
    s1 = (mlib_s32) *sp;
    sp --;

    t0  = VIS_LD_U8_I(tbb1, s1);
    bcc = vis_fbligndbtb(t0, bcc);

    t0  = VIS_LD_U8_I(tbb0, s1);
    bcc = vis_fbligndbtb(t0, bcc);
  }

  t0  = VIS_LD_U8_I(tbb1, s0);
  bcc = vis_fbligndbtb(t0, bcc);
  embsk = vis_edge8(dp, dend);
  vis_pst_8(bcc, dp, embsk);
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_2(const mlib_u8 *src,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      xsize,
                                  mlib_s32      ysize,
                                  const mlib_u8 **tbble)
{
  if ((xsize * ysize) < 650) {
    mlib_u8  *sl;
    mlib_u8  *dl;
    mlib_s32 i, j;

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8 *sp = sl;
      mlib_u8 *dp = dl;
      mlib_s32 off, s0, size = xsize;

      off = ((8 - ((mlib_bddr)dp & 7)) & 7) >> 1;
      off = (off < size) ? off : size;

      for (i = 0; i < off; i++) {
        s0 = *sp++;
        *dp++ = tbble[0][s0];
        *dp++ = tbble[1][s0];
        size--;
      }

      if (size > 0) {

        if (((mlib_bddr)dp & 1) == 0) {
          mlib_v_ImbgeLookUpSI_U8_U8_2_DstA8D1_SMALL(sp, dp, size, tbble);
        } else {
          mlib_v_ImbgeLookUpSI_U8_U8_2_D1_SMALL(sp, dp, size, tbble);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
    }

  } else {
    mlib_u8  *sl;
    mlib_u8  *dl;
    mlib_u16 tbb[256];
    const mlib_u8  *tbb0 = tbble[0];
    const mlib_u8  *tbb1 = tbble[1];
    mlib_s32 i, j, s0, s1, s2;

    s0 = tbb0[0];
    s1 = tbb1[0];
    for (i = 1; i < 256; i++) {
      s2 = (s0 << 8) + s1;
      s0 = tbb0[i];
      s1 = tbb1[i];
      tbb[i-1] = (mlib_u16)s2;
    }

    s2 = (s0 << 8) + s1;
    tbb[255] = (mlib_u16)s2;

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8 *sp = sl;
      mlib_u8 *dp = dl;
      mlib_s32 off, s0, size = xsize;

      if (((mlib_bddr)dp & 1) == 0) {

        off = ((8 - ((mlib_bddr)dp & 7)) & 7) >> 1;
        off = (off < size) ? off : size;

        for (i = 0; i < off; i++) {
          *(mlib_u16*)dp = tbb[(*sp)];
          dp += 2;
          size--; sp++;
        }

        if (size > 0) {

          off = (mlib_bddr)sp & 3;

          if (off == 0) {
            mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff0_D1(sp, dp, size, tbb);
          } else if (off == 1) {
            mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff1_D1(sp, dp, size, tbb);
          } else if (off == 2) {
            mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff2_D1(sp, dp, size, tbb);
          } else {
            mlib_v_ImbgeLookUpSI_U8_U8_2_SrcOff3_D1(sp, dp, size, tbb);
          }
        }

      } else {

        off = ((4 - ((mlib_bddr)sp & 3)) & 3);
        off = (off < size) ? off : size;

        for (i = 0; i < off; i++) {
          s0 = tbb[(*sp)];
          *dp++ = (s0 >> 8);
          *dp++ = (s0 & 0xFF);
          size--; sp++;
        }

        if (size > 0) {
          mlib_v_ImbgeLookUpSI_U8_U8_2_DstNonAl_D1(sp, dp, size, tbb);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff0_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_d64 *tbble)
{
  mlib_u8  *sp;            /* pointer to source dbtb */
  mlib_u32 *sb;            /* bligned pointer to source dbtb */
  mlib_u32 s0;             /* source dbtb */
  mlib_u8  *dl;            /* pointer to stbrt of destinbtion */
  mlib_f32 *dp;            /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3; /* destinbtion dbtb */
  mlib_d64 bcc0, bcc1;     /* destinbtion dbtb */
  mlib_s32 i;              /* loop vbribble */
  mlib_u8  *ptr;

  dl   =  dst;
  dp   = (mlib_f32 *) dl;
  sp = (void *)src;
  sb = (mlib_u32*)sp;

  vis_blignbddr((void *) 0, 3);

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp+=3) {
      t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 21) & 0x7F8 ));
      t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 13) & 0x7F8 ));
      t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 5) & 0x7F8 ));
      t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
      bcc0 = vis_fbligndbtb(t0, t0);
      bcc0 = vis_fbligndbtb(bcc0, t1);
      bcc1 = vis_fbligndbtb(bcc0, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t2);
      bcc1 = vis_fbligndbtb(bcc1, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t3);
      s0 = *sb++;
      dp[0] = vis_rebd_lo(bcc1);
      dp[1] = vis_rebd_hi(bcc0);
      dp[2] = vis_rebd_lo(bcc0);
    }

    t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 21) & 0x7F8 ));
    t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 13) & 0x7F8 ));
    t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 5) & 0x7F8 ));
    t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
    bcc0 = vis_fbligndbtb(t0, t0);
    bcc0 = vis_fbligndbtb(bcc0, t1);
    bcc1 = vis_fbligndbtb(bcc0, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t2);
    bcc1 = vis_fbligndbtb(bcc1, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t3);
    dp[0] = vis_rebd_lo(bcc1);
    dp[1] = vis_rebd_hi(bcc0);
    dp[2] = vis_rebd_lo(bcc0);
    dp += 3;
    i += 4;
  }

  dl = (mlib_u8*)dp;

#prbgmb pipeloop(0)
  for (; i < xsize; i++) {
    ptr = (mlib_u8*)(tbble + src[i]);
    dl[0] = ptr[0];
    dl[1] = ptr[1];
    dl[2] = ptr[2];
    dl += 3;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff1_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_d64 *tbble)
{
  mlib_u8  *sp;            /* pointer to source dbtb */
  mlib_u32 *sb;            /* bligned pointer to source dbtb */
  mlib_u32 s0, s1;         /* source dbtb */
  mlib_u8  *dl;            /* pointer to stbrt of destinbtion */
  mlib_f32 *dp;            /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3; /* destinbtion dbtb */
  mlib_d64 bcc0, bcc1;     /* destinbtion dbtb */
  mlib_s32 i;              /* loop vbribble */
  mlib_u8  *ptr;

  dl   =  dst;
  dp   = (mlib_f32 *) dl;
  sp = (void *)src;
  sb = (mlib_u32*)(sp - 1);

  vis_blignbddr((void *) 0, 3);

  i = 0;
  s0 = *sb++;

  if (xsize >= 4) {

    s1 = *sb++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp+=3) {
      t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 13) & 0x7F8 ));
      t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 5) & 0x7F8 ));
      t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
      t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 21) & 0x7F8 ));
      bcc0 = vis_fbligndbtb(t0, t0);
      bcc0 = vis_fbligndbtb(bcc0, t1);
      bcc1 = vis_fbligndbtb(bcc0, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t2);
      bcc1 = vis_fbligndbtb(bcc1, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t3);
      s0 = s1;
      s1 = *sb++;
      dp[0] = vis_rebd_lo(bcc1);
      dp[1] = vis_rebd_hi(bcc0);
      dp[2] = vis_rebd_lo(bcc0);
    }

    t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 13) & 0x7F8 ));
    t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 5) & 0x7F8 ));
    t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
    t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 21) & 0x7F8 ));
    bcc0 = vis_fbligndbtb(t0, t0);
    bcc0 = vis_fbligndbtb(bcc0, t1);
    bcc1 = vis_fbligndbtb(bcc0, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t2);
    bcc1 = vis_fbligndbtb(bcc1, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t3);
    dp[0] = vis_rebd_lo(bcc1);
    dp[1] = vis_rebd_hi(bcc0);
    dp[2] = vis_rebd_lo(bcc0);
    dp += 3;
    i += 4;
  }

  dl = (mlib_u8*)dp;

#prbgmb pipeloop(0)
  for (; i < xsize; i++) {
    ptr = (mlib_u8*)(tbble + src[i]);
    dl[0] = ptr[0];
    dl[1] = ptr[1];
    dl[2] = ptr[2];
    dl += 3;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff2_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_d64 *tbble)
{
  mlib_u8  *sp;            /* pointer to source dbtb */
  mlib_u32 *sb;            /* bligned pointer to source dbtb */
  mlib_u32 s0, s1;         /* source dbtb */
  mlib_u8  *dl;            /* pointer to stbrt of destinbtion */
  mlib_f32 *dp;            /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3; /* destinbtion dbtb */
  mlib_d64 bcc0, bcc1;     /* destinbtion dbtb */
  mlib_s32 i;              /* loop vbribble */
  mlib_u8  *ptr;

  dl   =  dst;
  dp   = (mlib_f32 *) dl;
  sp = (void *)src;
  sb = (mlib_u32*)(sp - 2);

  vis_blignbddr((void *) 0, 3);

  i = 0;
  s0 = *sb++;

  if (xsize >= 4) {

    s1 = *sb++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp+=3) {
      t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 5) & 0x7F8 ));
      t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
      t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 21) & 0x7F8 ));
      t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 13) & 0x7F8 ));
      bcc0 = vis_fbligndbtb(t0, t0);
      bcc0 = vis_fbligndbtb(bcc0, t1);
      bcc1 = vis_fbligndbtb(bcc0, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t2);
      bcc1 = vis_fbligndbtb(bcc1, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t3);
      s0 = s1;
      s1 = *sb++;
      dp[0] = vis_rebd_lo(bcc1);
      dp[1] = vis_rebd_hi(bcc0);
      dp[2] = vis_rebd_lo(bcc0);
    }

    t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 >> 5) & 0x7F8 ));
    t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
    t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 21) & 0x7F8 ));
    t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 13) & 0x7F8 ));
    bcc0 = vis_fbligndbtb(t0, t0);
    bcc0 = vis_fbligndbtb(bcc0, t1);
    bcc1 = vis_fbligndbtb(bcc0, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t2);
    bcc1 = vis_fbligndbtb(bcc1, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t3);
    dp[0] = vis_rebd_lo(bcc1);
    dp[1] = vis_rebd_hi(bcc0);
    dp[2] = vis_rebd_lo(bcc0);
    dp += 3;
    i += 4;
  }

  dl = (mlib_u8*)dp;

#prbgmb pipeloop(0)
  for (; i < xsize; i++) {
    ptr = (mlib_u8*)(tbble + src[i]);
    dl[0] = ptr[0];
    dl[1] = ptr[1];
    dl[2] = ptr[2];
    dl += 3;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff3_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_d64 *tbble)
{
  mlib_u8  *sp;            /* pointer to source dbtb */
  mlib_u32 *sb;            /* bligned pointer to source dbtb */
  mlib_u32 s0, s1;         /* source dbtb */
  mlib_u8  *dl;            /* pointer to stbrt of destinbtion */
  mlib_f32 *dp;            /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3; /* destinbtion dbtb */
  mlib_d64 bcc0, bcc1;     /* destinbtion dbtb */
  mlib_s32 i;              /* loop vbribble */
  mlib_u8  *ptr;

  dl   =  dst;
  dp   = (mlib_f32 *) dl;
  sp = (void *)src;
  sb = (mlib_u32*)(sp - 3);

  vis_blignbddr((void *) 0, 3);

  i = 0;
  s0 = *sb++;

  if (xsize >= 4) {

    s1 = *sb++;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp+=3) {
      t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
      t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 21) & 0x7F8 ));
      t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 13) & 0x7F8 ));
      t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 5) & 0x7F8 ));
      bcc0 = vis_fbligndbtb(t0, t0);
      bcc0 = vis_fbligndbtb(bcc0, t1);
      bcc1 = vis_fbligndbtb(bcc0, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t2);
      bcc1 = vis_fbligndbtb(bcc1, bcc0);
      bcc0 = vis_fbligndbtb(bcc0, t3);
      s0 = s1;
      s1 = *sb++;
      dp[0] = vis_rebd_lo(bcc1);
      dp[1] = vis_rebd_hi(bcc0);
      dp[2] = vis_rebd_lo(bcc0);
    }

    t0 = *(mlib_d64*)((mlib_u8*)tbble + ((s0 << 3) & 0x7F8 ));
    t1 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 21) & 0x7F8 ));
    t2 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 13) & 0x7F8 ));
    t3 = *(mlib_d64*)((mlib_u8*)tbble + ((s1 >> 5) & 0x7F8 ));
    bcc0 = vis_fbligndbtb(t0, t0);
    bcc0 = vis_fbligndbtb(bcc0, t1);
    bcc1 = vis_fbligndbtb(bcc0, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t2);
    bcc1 = vis_fbligndbtb(bcc1, bcc0);
    bcc0 = vis_fbligndbtb(bcc0, t3);
    dp[0] = vis_rebd_lo(bcc1);
    dp[1] = vis_rebd_hi(bcc0);
    dp[2] = vis_rebd_lo(bcc0);
    dp += 3;
    i += 4;
  }

  dl = (mlib_u8*)dp;

#prbgmb pipeloop(0)
  for (; i < xsize; i++) {
    ptr = (mlib_u8*)(tbble + src[i]);
    dl[0] = ptr[0];
    dl[1] = ptr[1];
    dl[2] = ptr[2];
    dl += 3;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_3_D1_SMALL(const mlib_u8 *src,
                                           mlib_u8       *dst,
                                           mlib_s32      xsize,
                                           const mlib_u8 **tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u8  *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;       /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;       /* destinbtion dbtb */
  mlib_d64 t6, t7;           /* destinbtion dbtb */
  mlib_d64 bcc0, bcc1, bcc2; /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_u8  *tbb0 = tbble[0];
  const mlib_u8  *tbb1 = tbble[1];
  const mlib_u8  *tbb2 = tbble[2];
  mlib_u32 s00, s01, s02, s03;
  mlib_u32 s10, s11, s12, s13;

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 7);

  i = 0;

  if (xsize >= 8) {

    s00 = sp[0];
    s01 = sp[1];
    s02 = sp[2];
    s03 = sp[3];
    s10 = sp[4];
    s11 = sp[5];
    s12 = sp[6];
    s13 = sp[7];
    sp += 8;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 16; i+=8, sp+=8) {
      t7 = VIS_LD_U8_I(tbb1, s02);
      t6 = VIS_LD_U8_I(tbb0, s02);
      t5 = VIS_LD_U8_I(tbb2, s01);
      t4 = VIS_LD_U8_I(tbb1, s01);
      t3 = VIS_LD_U8_I(tbb0, s01);
      t2 = VIS_LD_U8_I(tbb2, s00);
      t1 = VIS_LD_U8_I(tbb1, s00);
      t0 = VIS_LD_U8_I(tbb0, s00);
      bcc0 = vis_fbligndbtb(t7, bcc0);
      bcc0 = vis_fbligndbtb(t6, bcc0);
      bcc0 = vis_fbligndbtb(t5, bcc0);
      bcc0 = vis_fbligndbtb(t4, bcc0);
      bcc0 = vis_fbligndbtb(t3, bcc0);
      bcc0 = vis_fbligndbtb(t2, bcc0);
      bcc0 = vis_fbligndbtb(t1, bcc0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
      t7 = VIS_LD_U8_I(tbb0, s11);
      t6 = VIS_LD_U8_I(tbb2, s10);
      t5 = VIS_LD_U8_I(tbb1, s10);
      t4 = VIS_LD_U8_I(tbb0, s10);
      t3 = VIS_LD_U8_I(tbb2, s03);
      t2 = VIS_LD_U8_I(tbb1, s03);
      t1 = VIS_LD_U8_I(tbb0, s03);
      t0 = VIS_LD_U8_I(tbb2, s02);
      bcc1 = vis_fbligndbtb(t7, bcc1);
      bcc1 = vis_fbligndbtb(t6, bcc1);
      bcc1 = vis_fbligndbtb(t5, bcc1);
      bcc1 = vis_fbligndbtb(t4, bcc1);
      bcc1 = vis_fbligndbtb(t3, bcc1);
      bcc1 = vis_fbligndbtb(t2, bcc1);
      bcc1 = vis_fbligndbtb(t1, bcc1);
      bcc1 = vis_fbligndbtb(t0, bcc1);
      t7 = VIS_LD_U8_I(tbb2, s13);
      t6 = VIS_LD_U8_I(tbb1, s13);
      t5 = VIS_LD_U8_I(tbb0, s13);
      t4 = VIS_LD_U8_I(tbb2, s12);
      t3 = VIS_LD_U8_I(tbb1, s12);
      t2 = VIS_LD_U8_I(tbb0, s12);
      t1 = VIS_LD_U8_I(tbb2, s11);
      t0 = VIS_LD_U8_I(tbb1, s11);
      bcc2 = vis_fbligndbtb(t7, bcc2);
      bcc2 = vis_fbligndbtb(t6, bcc2);
      bcc2 = vis_fbligndbtb(t5, bcc2);
      bcc2 = vis_fbligndbtb(t4, bcc2);
      bcc2 = vis_fbligndbtb(t3, bcc2);
      bcc2 = vis_fbligndbtb(t2, bcc2);
      bcc2 = vis_fbligndbtb(t1, bcc2);
      bcc2 = vis_fbligndbtb(t0, bcc2);
      s00 = sp[0];
      s01 = sp[1];
      s02 = sp[2];
      s03 = sp[3];
      s10 = sp[4];
      s11 = sp[5];
      s12 = sp[6];
      s13 = sp[7];
      *dp++ = bcc0;
      *dp++ = bcc1;
      *dp++ = bcc2;
    }

    t7 = VIS_LD_U8_I(tbb1, s02);
    t6 = VIS_LD_U8_I(tbb0, s02);
    t5 = VIS_LD_U8_I(tbb2, s01);
    t4 = VIS_LD_U8_I(tbb1, s01);
    t3 = VIS_LD_U8_I(tbb0, s01);
    t2 = VIS_LD_U8_I(tbb2, s00);
    t1 = VIS_LD_U8_I(tbb1, s00);
    t0 = VIS_LD_U8_I(tbb0, s00);
    bcc0 = vis_fbligndbtb(t7, bcc0);
    bcc0 = vis_fbligndbtb(t6, bcc0);
    bcc0 = vis_fbligndbtb(t5, bcc0);
    bcc0 = vis_fbligndbtb(t4, bcc0);
    bcc0 = vis_fbligndbtb(t3, bcc0);
    bcc0 = vis_fbligndbtb(t2, bcc0);
    bcc0 = vis_fbligndbtb(t1, bcc0);
    bcc0 = vis_fbligndbtb(t0, bcc0);
    t7 = VIS_LD_U8_I(tbb0, s11);
    t6 = VIS_LD_U8_I(tbb2, s10);
    t5 = VIS_LD_U8_I(tbb1, s10);
    t4 = VIS_LD_U8_I(tbb0, s10);
    t3 = VIS_LD_U8_I(tbb2, s03);
    t2 = VIS_LD_U8_I(tbb1, s03);
    t1 = VIS_LD_U8_I(tbb0, s03);
    t0 = VIS_LD_U8_I(tbb2, s02);
    bcc1 = vis_fbligndbtb(t7, bcc1);
    bcc1 = vis_fbligndbtb(t6, bcc1);
    bcc1 = vis_fbligndbtb(t5, bcc1);
    bcc1 = vis_fbligndbtb(t4, bcc1);
    bcc1 = vis_fbligndbtb(t3, bcc1);
    bcc1 = vis_fbligndbtb(t2, bcc1);
    bcc1 = vis_fbligndbtb(t1, bcc1);
    bcc1 = vis_fbligndbtb(t0, bcc1);
    t7 = VIS_LD_U8_I(tbb2, s13);
    t6 = VIS_LD_U8_I(tbb1, s13);
    t5 = VIS_LD_U8_I(tbb0, s13);
    t4 = VIS_LD_U8_I(tbb2, s12);
    t3 = VIS_LD_U8_I(tbb1, s12);
    t2 = VIS_LD_U8_I(tbb0, s12);
    t1 = VIS_LD_U8_I(tbb2, s11);
    t0 = VIS_LD_U8_I(tbb1, s11);
    bcc2 = vis_fbligndbtb(t7, bcc2);
    bcc2 = vis_fbligndbtb(t6, bcc2);
    bcc2 = vis_fbligndbtb(t5, bcc2);
    bcc2 = vis_fbligndbtb(t4, bcc2);
    bcc2 = vis_fbligndbtb(t3, bcc2);
    bcc2 = vis_fbligndbtb(t2, bcc2);
    bcc2 = vis_fbligndbtb(t1, bcc2);
    bcc2 = vis_fbligndbtb(t0, bcc2);
    *dp++ = bcc0;
    *dp++ = bcc1;
    *dp++ = bcc2;
    i += 8;
  }

  dl = (mlib_u8*)dp;

#prbgmb pipeloop(0)
  for (; i < xsize; i++) {
    s00 = sp[0];
    dl[0] = tbb0[s00];
    dl[1] = tbb1[s00];
    dl[2] = tbb2[s00];
    dl += 3; sp ++;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_3(const mlib_u8 *src,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      xsize,
                                  mlib_s32      ysize,
                                  const mlib_u8 **tbble)
{
  if ((xsize * ysize) < 650) {
    mlib_u8  *sl;
    mlib_u8  *dl;
    mlib_s32 i, j;
    const mlib_u8  *tbb0 = tbble[0];
    const mlib_u8  *tbb1 = tbble[1];
    const mlib_u8  *tbb2 = tbble[2];

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8 *sp = sl;
      mlib_u8 *dp = dl;
      mlib_s32 off, s0, size = xsize;

      off = (mlib_bddr)dp & 7;
      off = (off * 5) & 7;
      off = (off < size) ? off : size;

      for (i = 0; i < off; i++) {
        s0 = *sp++;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        *dp++ = tbb2[s0];
        size--;
      }

      if (size > 0) {
        mlib_v_ImbgeLookUpSI_U8_U8_3_D1_SMALL(sp, dp, size, tbble);
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
    }

  } else {
    mlib_u8  *sl;
    mlib_u8  *dl;
    mlib_u32 tbb[512];
    const mlib_u8  *tbb0 = tbble[0];
    const mlib_u8  *tbb1 = tbble[1];
    const mlib_u8  *tbb2 = tbble[2];
    mlib_s32 i, j;
    mlib_u32 s0, s1, s2, s3;

    s0 = tbb0[0];
    s1 = tbb1[0];
    s2 = tbb2[0];
    for (i = 1; i < 256; i++) {
      s3 = (s0 << 24) + (s1 << 16) + (s2 << 8);
      s0 = tbb0[i];
      s1 = tbb1[i];
      s2 = tbb2[i];
      tbb[2*i-2] = s3;
    }

    s3 = (s0 << 24) + (s1 << 16) + (s2 << 8);
    tbb[510] = s3;

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8 *sp = sl;
      mlib_u8 *dp = dl;
      mlib_s32 off, size = xsize;
      mlib_u8  *ptr;

      off = ((mlib_bddr)dp & 3);
      off = (off < size) ? off : size;

#prbgmb pipeloop(0)
      for (i = 0; i < off; i++) {
        ptr = (mlib_u8*)(tbb + 2*sp[i]);
        dp[0] = ptr[0];
        dp[1] = ptr[1];
        dp[2] = ptr[2];
        dp += 3;
      }

      size -= off;
      sp += off;

      if (size > 0) {
        off = (mlib_bddr)sp & 3;

        if (off == 0) {
          mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff0_D1(sp, dp, size, (mlib_d64*)tbb);
        } else if (off == 1) {
          mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff1_D1(sp, dp, size, (mlib_d64*)tbb);
        } else if (off == 2) {
          mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff2_D1(sp, dp, size, (mlib_d64*)tbb);
        } else if (off == 3) {
          mlib_v_ImbgeLookUpSI_U8_U8_3_SrcOff3_D1(sp, dp, size, (mlib_d64*)tbb);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_4_SrcOff0_D1(const mlib_u8  *src,
                                             mlib_u8        *dst,
                                             mlib_s32       xsize,
                                             const mlib_f32 *tbble)
{
  mlib_u32 *sb;          /* bligned pointer to source dbtb */
  mlib_u8  *sp;          /* pointer to source dbtb */
  mlib_u32 s0;           /* source dbtb */
  mlib_f32 *dp;          /* bligned pointer to destinbtion */
  mlib_f32 bcc0, bcc1;   /* destinbtion dbtb */
  mlib_f32 bcc2, bcc3;   /* destinbtion dbtb */
  mlib_s32 i;            /* loop vbribble */
  mlib_u32 s00, s01, s02, s03;

  sb   = (mlib_u32*)src;
  dp   = (mlib_f32 *) dst;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 22) & 0x3FC;
    s01 = (s0 >> 14) & 0x3FC;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 4) {
      s02 = (s0 >> 6) & 0x3FC;
      s03 = (s0 << 2) & 0x3FC;
      bcc0 = *(mlib_f32*)((mlib_u8*)tbble + s00);
      bcc1 = *(mlib_f32*)((mlib_u8*)tbble + s01);
      bcc2 = *(mlib_f32*)((mlib_u8*)tbble + s02);
      bcc3 = *(mlib_f32*)((mlib_u8*)tbble + s03);
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
    bcc0 = *(mlib_f32*)((mlib_u8*)tbble + s00);
    bcc1 = *(mlib_f32*)((mlib_u8*)tbble + s01);
    bcc2 = *(mlib_f32*)((mlib_u8*)tbble + s02);
    bcc3 = *(mlib_f32*)((mlib_u8*)tbble + s03);
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

  if ( i < xsize) *dp = tbble[sp[0]];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_4_DstNonAl_D1(const mlib_u8  *src,
                                              mlib_u8        *dst,
                                              mlib_s32       xsize,
                                              const mlib_f32 *tbble)
{
  mlib_u32 *sb;              /* bligned pointer to source dbtb */
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 s0;               /* source dbtb */
  mlib_u8  *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 bcc0, bcc1, bcc2; /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  mlib_u8  *dend;            /* pointer to end of destinbtion */
  mlib_s32 embsk;            /* edge mbsk */
  mlib_s32 off;
  mlib_u32 s00, s01, s02, s03;

  sb   = (mlib_u32*)src;
  sp = (void *)src;
  dl = dst;
  dend = dl + (xsize << 2) - 1;
  dp   = (mlib_d64 *) ((mlib_bddr) dl & (~7));
  off  = (mlib_bddr) dp - (mlib_bddr) dl;
  vis_blignbddr(dp, off);

  embsk = vis_edge8(dl, dend);
  bcc0 = vis_freg_pbir(tbble[sp[0]], tbble[sp[1]]);
  vis_pst_8(vis_fbligndbtb(bcc0, bcc0), dp++, embsk);
  sp += 2;

  xsize -= 2;

  if (xsize >= 2) {
    bcc1 = vis_freg_pbir(tbble[sp[0]], tbble[sp[1]]);
    *dp++ = vis_fbligndbtb(bcc0, bcc1);
    bcc0 = bcc1;
    sp += 2; xsize -= 2;
  }

  sb++;

  i = 0;

  if (xsize >= 4) {

    s0 = *sb++;
    s00 = (s0 >> 22) & 0x3FC;
    s01 = (s0 >> 14) & 0x3FC;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, dp += 2) {
      s02 = (s0 >> 6) & 0x3FC;
      s03 = (s0 << 2) & 0x3FC;
      bcc1 = vis_freg_pbir(*(mlib_f32*)((mlib_u8*)tbble + s00),
                           *(mlib_f32*)((mlib_u8*)tbble + s01));
      bcc2 = vis_freg_pbir(*(mlib_f32*)((mlib_u8*)tbble + s02),
                           *(mlib_f32*)((mlib_u8*)tbble + s03));
      s0 = *sb++;
      s00 = (s0 >> 22) & 0x3FC;
      s01 = (s0 >> 14) & 0x3FC;
      dp[0] = vis_fbligndbtb(bcc0, bcc1);
      dp[1] = vis_fbligndbtb(bcc1, bcc2);
      bcc0 = bcc2;
    }

    s02 = (s0 >> 6) & 0x3FC;
    s03 = (s0 << 2) & 0x3FC;
    bcc1 = vis_freg_pbir(*(mlib_f32*)((mlib_u8*)tbble + s00),
                         *(mlib_f32*)((mlib_u8*)tbble + s01));
    bcc2 = vis_freg_pbir(*(mlib_f32*)((mlib_u8*)tbble + s02),
                         *(mlib_f32*)((mlib_u8*)tbble + s03));
    dp[0] = vis_fbligndbtb(bcc0, bcc1);
    dp[1] = vis_fbligndbtb(bcc1, bcc2);
    bcc0 = bcc2;
    sp = (mlib_u8*)sb;
    dp += 2;
    i += 4;
  }

  if ( i <= xsize - 2) {
    bcc1 = vis_freg_pbir(tbble[sp[0]], tbble[sp[1]]);
    *dp++ = vis_fbligndbtb(bcc0, bcc1);
    bcc0 = bcc1;
    i+=2; sp += 2;
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {
    embsk = vis_edge8(dp, dend);
    bcc1 = vis_freg_pbir(tbble[sp[0]], tbble[sp[1]]);
    vis_pst_8(vis_fbligndbtb(bcc0, bcc1), dp++, embsk);
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {
    embsk = vis_edge8(dp, dend);
    vis_pst_8(vis_fbligndbtb(bcc1, bcc1), dp++, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff0_D1_SMALL(const mlib_u8 *src,
                                                   mlib_u8       *dst,
                                                   mlib_s32      xsize,
                                                   const mlib_u8 **tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 s0, s1;           /* source dbtb */
  mlib_u8 *dl;               /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;       /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;       /* destinbtion dbtb */
  mlib_d64 t6, t7, bcc;      /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_u8  *tbb0 = tbble[0];
  const mlib_u8  *tbb1 = tbble[1];
  const mlib_u8  *tbb2 = tbble[2];
  const mlib_u8  *tbb3 = tbble[3];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 7);

  if (xsize >= 2) {

    s0 = sp[0];
    s1 = sp[1];
    sp += 2;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, sp+=2) {
      t7 = VIS_LD_U8_I(tbb3, s1);
      t6 = VIS_LD_U8_I(tbb2, s1);
      t5 = VIS_LD_U8_I(tbb1, s1);
      t4 = VIS_LD_U8_I(tbb0, s1);
      t3 = VIS_LD_U8_I(tbb3, s0);
      t2 = VIS_LD_U8_I(tbb2, s0);
      t1 = VIS_LD_U8_I(tbb1, s0);
      t0 = VIS_LD_U8_I(tbb0, s0);
      bcc = vis_fbligndbtb(t7, bcc);
      bcc = vis_fbligndbtb(t6, bcc);
      bcc = vis_fbligndbtb(t5, bcc);
      bcc = vis_fbligndbtb(t4, bcc);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = sp[0];
      s1 = sp[1];
      *dp++ = bcc;
    }

    t7 = VIS_LD_U8_I(tbb3, s1);
    t6 = VIS_LD_U8_I(tbb2, s1);
    t5 = VIS_LD_U8_I(tbb1, s1);
    t4 = VIS_LD_U8_I(tbb0, s1);
    t3 = VIS_LD_U8_I(tbb3, s0);
    t2 = VIS_LD_U8_I(tbb2, s0);
    t1 = VIS_LD_U8_I(tbb1, s0);
    t0 = VIS_LD_U8_I(tbb0, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }

  if ((xsize & 1) != 0) {
    s0 = sp[0];
    t7 = VIS_LD_U8_I(tbb3, s0);
    t6 = VIS_LD_U8_I(tbb2, s0);
    t5 = VIS_LD_U8_I(tbb1, s0);
    t4 = VIS_LD_U8_I(tbb0, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    *(mlib_f32*)dp = vis_rebd_hi(bcc);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff1_D1_SMALL(const mlib_u8 *src,
                                                   mlib_u8       *dst,
                                                   mlib_s32      xsize,
                                                   const mlib_u8 **tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 s0, s1, s2;       /* source dbtb */
  mlib_u8  *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;       /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;       /* destinbtion dbtb */
  mlib_d64 t6, t7, bcc;      /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_u8  *tbb0 = tbble[0];
  const mlib_u8  *tbb1 = tbble[1];
  const mlib_u8  *tbb2 = tbble[2];
  const mlib_u8  *tbb3 = tbble[3];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 7);

  s0 = *sp++;

  if (xsize >= 2) {

    s1 = sp[0];
    s2 = sp[1];
    sp += 2;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, sp+=2) {
      t7 = VIS_LD_U8_I(tbb0, s2);
      t6 = VIS_LD_U8_I(tbb3, s1);
      t5 = VIS_LD_U8_I(tbb2, s1);
      t4 = VIS_LD_U8_I(tbb1, s1);
      t3 = VIS_LD_U8_I(tbb0, s1);
      t2 = VIS_LD_U8_I(tbb3, s0);
      t1 = VIS_LD_U8_I(tbb2, s0);
      t0 = VIS_LD_U8_I(tbb1, s0);
      bcc = vis_fbligndbtb(t7, bcc);
      bcc = vis_fbligndbtb(t6, bcc);
      bcc = vis_fbligndbtb(t5, bcc);
      bcc = vis_fbligndbtb(t4, bcc);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s2;
      s1 = sp[0];
      s2 = sp[1];
      *dp++ = bcc;
    }

    t7 = VIS_LD_U8_I(tbb0, s2);
    t6 = VIS_LD_U8_I(tbb3, s1);
    t5 = VIS_LD_U8_I(tbb2, s1);
    t4 = VIS_LD_U8_I(tbb1, s1);
    t3 = VIS_LD_U8_I(tbb0, s1);
    t2 = VIS_LD_U8_I(tbb3, s0);
    t1 = VIS_LD_U8_I(tbb2, s0);
    t0 = VIS_LD_U8_I(tbb1, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s2;
    *dp++ = bcc;
  }

  dl = (mlib_u8*)dp;

  if ((xsize & 1) != 0) {
    s1 = sp[0];
    t7 = VIS_LD_U8_I(tbb0, s1);
    t6 = VIS_LD_U8_I(tbb3, s0);
    t5 = VIS_LD_U8_I(tbb2, s0);
    t4 = VIS_LD_U8_I(tbb1, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    *(mlib_f32*)dl = vis_rebd_hi(bcc);
    dl += 4;
    s0 = s1;
  }

  dl[0] = tbb1[s0];
  dl[1] = tbb2[s0];
  dl[2] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff2_D1_SMALL(const mlib_u8 *src,
                                                   mlib_u8       *dst,
                                                   mlib_s32      xsize,
                                                   const mlib_u8 **tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 s0, s1, s2;       /* source dbtb */
  mlib_u8  *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;       /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;       /* destinbtion dbtb */
  mlib_d64 t6, t7, bcc;      /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_u8  *tbb0 = tbble[0];
  const mlib_u8  *tbb1 = tbble[1];
  const mlib_u8  *tbb2 = tbble[2];
  const mlib_u8  *tbb3 = tbble[3];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 7);

  s0 = *sp++;

  if (xsize >= 2) {

    s1 = sp[0];
    s2 = sp[1];
    sp += 2;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, sp+=2) {
      t7 = VIS_LD_U8_I(tbb1, s2);
      t6 = VIS_LD_U8_I(tbb0, s2);
      t5 = VIS_LD_U8_I(tbb3, s1);
      t4 = VIS_LD_U8_I(tbb2, s1);
      t3 = VIS_LD_U8_I(tbb1, s1);
      t2 = VIS_LD_U8_I(tbb0, s1);
      t1 = VIS_LD_U8_I(tbb3, s0);
      t0 = VIS_LD_U8_I(tbb2, s0);
      bcc = vis_fbligndbtb(t7, bcc);
      bcc = vis_fbligndbtb(t6, bcc);
      bcc = vis_fbligndbtb(t5, bcc);
      bcc = vis_fbligndbtb(t4, bcc);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s2;
      s1 = sp[0];
      s2 = sp[1];
      *dp++ = bcc;
    }

    t7 = VIS_LD_U8_I(tbb1, s2);
    t6 = VIS_LD_U8_I(tbb0, s2);
    t5 = VIS_LD_U8_I(tbb3, s1);
    t4 = VIS_LD_U8_I(tbb2, s1);
    t3 = VIS_LD_U8_I(tbb1, s1);
    t2 = VIS_LD_U8_I(tbb0, s1);
    t1 = VIS_LD_U8_I(tbb3, s0);
    t0 = VIS_LD_U8_I(tbb2, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s2;
    *dp++ = bcc;
  }

  dl = (mlib_u8*)dp;

  if ((xsize & 1) != 0) {
    s1 = sp[0];
    t7 = VIS_LD_U8_I(tbb1, s1);
    t6 = VIS_LD_U8_I(tbb0, s1);
    t5 = VIS_LD_U8_I(tbb3, s0);
    t4 = VIS_LD_U8_I(tbb2, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    *(mlib_f32*)dl = vis_rebd_hi(bcc);
    dl += 4;
    s0 = s1;
  }

  dl[0] = tbb2[s0];
  dl[1] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff3_D1_SMALL(const mlib_u8 *src,
                                                   mlib_u8       *dst,
                                                   mlib_s32      xsize,
                                                   const mlib_u8 **tbble)
{
  mlib_u8  *sp;              /* pointer to source dbtb */
  mlib_u32 s0, s1, s2;       /* source dbtb */
  mlib_u8 *dl;               /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;       /* destinbtion dbtb */
  mlib_d64 t3, t4, t5;       /* destinbtion dbtb */
  mlib_d64 t6, t7, bcc;      /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_u8  *tbb0 = tbble[0];
  const mlib_u8  *tbb1 = tbble[1];
  const mlib_u8  *tbb2 = tbble[2];
  const mlib_u8  *tbb3 = tbble[3];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 7);

  s0 = *sp++;

  if (xsize >= 2) {

    s1 = sp[0];
    s2 = sp[1];
    sp += 2;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, sp+=2) {
      t7 = VIS_LD_U8_I(tbb2, s2);
      t6 = VIS_LD_U8_I(tbb1, s2);
      t5 = VIS_LD_U8_I(tbb0, s2);
      t4 = VIS_LD_U8_I(tbb3, s1);
      t3 = VIS_LD_U8_I(tbb2, s1);
      t2 = VIS_LD_U8_I(tbb1, s1);
      t1 = VIS_LD_U8_I(tbb0, s1);
      t0 = VIS_LD_U8_I(tbb3, s0);
      bcc = vis_fbligndbtb(t7, bcc);
      bcc = vis_fbligndbtb(t6, bcc);
      bcc = vis_fbligndbtb(t5, bcc);
      bcc = vis_fbligndbtb(t4, bcc);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s2;
      s1 = sp[0];
      s2 = sp[1];
      *dp++ = bcc;
    }

    t7 = VIS_LD_U8_I(tbb2, s2);
    t6 = VIS_LD_U8_I(tbb1, s2);
    t5 = VIS_LD_U8_I(tbb0, s2);
    t4 = VIS_LD_U8_I(tbb3, s1);
    t3 = VIS_LD_U8_I(tbb2, s1);
    t2 = VIS_LD_U8_I(tbb1, s1);
    t1 = VIS_LD_U8_I(tbb0, s1);
    t0 = VIS_LD_U8_I(tbb3, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s2;
    *dp++ = bcc;
  }

  dl = (mlib_u8*)dp;

  if ((xsize & 1) != 0) {
    s1 = sp[0];
    t7 = VIS_LD_U8_I(tbb2, s1);
    t6 = VIS_LD_U8_I(tbb1, s1);
    t5 = VIS_LD_U8_I(tbb0, s1);
    t4 = VIS_LD_U8_I(tbb3, s0);
    bcc = vis_fbligndbtb(t7, bcc);
    bcc = vis_fbligndbtb(t6, bcc);
    bcc = vis_fbligndbtb(t5, bcc);
    bcc = vis_fbligndbtb(t4, bcc);
    *(mlib_f32*)dl = vis_rebd_hi(bcc);
    dl += 4;
    s0 = s1;
  }

  dl[0] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_U8_U8_4(const mlib_u8 *src,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      xsize,
                                  mlib_s32      ysize,
                                  const mlib_u8 **tbble)
{
  if ((xsize * ysize) < 500) {
    mlib_u8  *sl;
    mlib_u8  *dl;
    mlib_s32 j;
    const mlib_u8  *tbb0 = tbble[0];
    const mlib_u8  *tbb1 = tbble[1];
    const mlib_u8  *tbb2 = tbble[2];
    const mlib_u8  *tbb3 = tbble[3];

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8 *sp = sl;
      mlib_u8 *dp = dl;
      mlib_s32 off, s0, size = xsize;

      off =  (8 - ((mlib_bddr)dp & 7)) & 7;

      if ((off >= 4) && (size > 0)) {
        s0 = *sp++;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        *dp++ = tbb2[s0];
        *dp++ = tbb3[s0];
        size--;
      }

      if (size > 0) {
        off =  (4 - ((mlib_bddr)dp & 3)) & 3;

        if (off == 0) {
          mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff0_D1_SMALL(sp, dp, size, tbble);
        } else if (off == 1) {
          s0 = *sp;
          *dp++ = tbb0[s0];
          size--;
          mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff1_D1_SMALL(sp, dp, size, tbble);
        } else if (off == 2) {
          s0 = *sp;
          *dp++ = tbb0[s0];
          *dp++ = tbb1[s0];
          size--;
          mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff2_D1_SMALL(sp, dp, size, tbble);
        } else if (off == 3) {
          s0 = *sp;
          *dp++ = tbb0[s0];
          *dp++ = tbb1[s0];
          *dp++ = tbb2[s0];
          size--;
          mlib_v_ImbgeLookUpSI_U8_U8_4_DstOff3_D1_SMALL(sp, dp, size, tbble);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
    }

  } else {
    mlib_u8  *sl;
    mlib_u8  *dl;
    mlib_u32 tbb[256];
    const mlib_u8  *tbb0 = tbble[0];
    const mlib_u8  *tbb1 = tbble[1];
    const mlib_u8  *tbb2 = tbble[2];
    const mlib_u8  *tbb3 = tbble[3];
    mlib_s32 i, j;
    mlib_u32 s0, s1, s2, s3, s4;

    s0 = tbb0[0];
    s1 = tbb1[0];
    s2 = tbb2[0];
    s3 = tbb3[0];
    for (i = 1; i < 256; i++) {
      s4 = (s0 << 24) + (s1 << 16) + (s2 << 8) + s3;
      s0 = tbb0[i];
      s1 = tbb1[i];
      s2 = tbb2[i];
      s3 = tbb3[i];
      tbb[i-1] = s4;
    }

    s4 = (s0 << 24) + (s1 << 16) + (s2 << 8) + s3;
    tbb[255] = s4;

    sl = (void *)src;
    dl = dst;

    /* row loop */
    for (j = 0; j < ysize; j ++) {
      mlib_u8 *sp = sl;
      mlib_u8 *dp = dl;
      mlib_s32 off, size = xsize;

      if (((mlib_bddr)dp & 3) == 0) {
        off = (4 - (mlib_bddr)sp & 3) & 3;

        off = (off < size) ? off : size;

#prbgmb pipeloop(0)
        for (i = 0; i < off; i++) {
          *(mlib_u32*)dp = tbb[(*sp)];
          dp += 4; sp++;
        }

        size -= off;

        if (size > 0) {
          mlib_v_ImbgeLookUpSI_U8_U8_4_SrcOff0_D1(sp, dp, size, (mlib_f32*)tbb);
        }

      } else {

        off = ((4 - ((mlib_bddr)sp & 3)) & 3);
        off = (off < size) ? off : size;

        for (i = 0; i < off; i++) {
          s0 = tbb[(*sp)];
          *dp++ = (s0 >> 24);
          *dp++ = (s0 >> 16);
          *dp++ = (s0 >> 8);
          *dp++ = s0;
          size--; sp++;
        }

        if (size > 0) {
          mlib_v_ImbgeLookUpSI_U8_U8_4_DstNonAl_D1(sp, dp, size, (mlib_f32*)tbb);
        }
      }

      sl = (mlib_u8 *) ((mlib_u8 *) sl + slb);
      dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
