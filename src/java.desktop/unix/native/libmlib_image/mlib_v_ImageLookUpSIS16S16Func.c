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
stbtic void mlib_v_ImbgeLookUpSI_S16_S16_2_DstA8D1(const mlib_s16 *src,
                                                   mlib_s16       *dst,
                                                   mlib_s32       xsize,
                                                   const mlib_s16 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S16_2_D1(const mlib_s16 *src,
                                              mlib_s16       *dst,
                                              mlib_s32       xsize,
                                              const mlib_s16 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S16_3_D1(const mlib_s16 *src,
                                              mlib_s16       *dst,
                                              mlib_s32       xsize,
                                              const mlib_s16 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff0_D1(const mlib_s16 *src,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsize,
                                                      const mlib_s16 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff1_D1(const mlib_s16 *src,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsize,
                                                      const mlib_s16 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff2_D1(const mlib_s16 *src,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsize,
                                                      const mlib_s16 **tbble);

stbtic void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff3_D1(const mlib_s16 *src,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsize,
                                                      const mlib_s16 **tbble);

/***************************************************************/
#define VIS_LD_U16_I(X, Y)      vis_ld_u16_i((void *)(X), (Y))

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_2_DstA8D1(const mlib_s16 *src,
                                            mlib_s16       *dst,
                                            mlib_s32       xsize,
                                            const mlib_s16 **tbble)
{
  mlib_s16 *sp;              /* pointer to source dbtb */
  mlib_s32 s0, s1;           /* source dbtb */
  mlib_s16 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;       /* destinbtion dbtb */
  mlib_d64 t3, bcc;          /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  if (xsize >= 2) {

    s0 = (sp[0] << 1);
    s1 = (sp[1] << 1);
    sp += 2;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, sp+=2) {
      t3 = VIS_LD_U16_I(tbb1, s1);
      t2 = VIS_LD_U16_I(tbb0, s1);
      t1 = VIS_LD_U16_I(tbb1, s0);
      t0 = VIS_LD_U16_I(tbb0, s0);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = (sp[0] << 1);
      s1 = (sp[1] << 1);
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbb1, s1);
    t2 = VIS_LD_U16_I(tbb0, s1);
    t1 = VIS_LD_U16_I(tbb1, s0);
    t0 = VIS_LD_U16_I(tbb0, s0);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }

  if ((xsize & 1) != 0) {
    s0 = (sp[0] << 1);
    t1 = VIS_LD_U16_I(tbb1, s0);
    t0 = VIS_LD_U16_I(tbb0, s0);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *(mlib_f32*)dp = vis_rebd_hi(bcc);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_2_D1(const mlib_s16 *src,
                                       mlib_s16       *dst,
                                       mlib_s32       xsize,
                                       const mlib_s16 **tbble)
{
  mlib_s16 *sp;                /* pointer to source dbtb */
  mlib_s32 s0, s1, s2;         /* source dbtb */
  mlib_s16 *dl;                /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;                /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;         /* destinbtion dbtb */
  mlib_d64 t3, bcc;            /* destinbtion dbtb */
  mlib_s32 i;                  /* loop vbribble */
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];

  sp   = (void *)src;
  dl   = dst;

  vis_blignbddr((void *) 0, 6);

  s0 = *sp++;
  *dl++ = tbb0[s0];
  dp   = (mlib_d64 *) dl;
  xsize--; s0 <<= 1;

  if (xsize >= 2) {

    s1 = (sp[0] << 1);
    s2 = (sp[1] << 1);
    sp += 2;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 4; i+=2, sp+=2) {
      t3 = VIS_LD_U16_I(tbb0, s2);
      t2 = VIS_LD_U16_I(tbb1, s1);
      t1 = VIS_LD_U16_I(tbb0, s1);
      t0 = VIS_LD_U16_I(tbb1, s0);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s2;
      s1 = (sp[0] << 1);
      s2 = (sp[1] << 1);
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbb0, s2);
    t2 = VIS_LD_U16_I(tbb1, s1);
    t1 = VIS_LD_U16_I(tbb0, s1);
    t0 = VIS_LD_U16_I(tbb1, s0);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s2;
    *dp++ = bcc;
  }

  dl = (mlib_s16*)dp;

  if ((xsize & 1) != 0) {
    s1 = (sp[0] << 1);
    t1 = VIS_LD_U16_I(tbb0, s1);
    t0 = VIS_LD_U16_I(tbb1, s0);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *(mlib_f32*)dp = vis_rebd_hi(bcc);
    s0 = s1; dl += 2;
  }

  s0 >>= 1;
  *dl = tbb1[s0];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_2(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  mlib_s32 j;
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j ++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    mlib_s32 off, s0, size = xsize;

    off = ((8 - ((mlib_bddr)dp & 7)) & 7);

    if ((off >= 4) && (size > 0)) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      size--;
    }

    if (size > 0) {

      if (((mlib_bddr)dp & 7) == 0) {
        mlib_v_ImbgeLookUpSI_S16_S16_2_DstA8D1(sp, dp, size, tbble);
      } else {
        mlib_v_ImbgeLookUpSI_S16_S16_2_D1(sp, dp, size, tbble);
      }
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_3_D1(const mlib_s16 *src,
                                       mlib_s16       *dst,
                                       mlib_s32       xsize,
                                       const mlib_s16 **tbble)
{
  mlib_s16 *sp;              /* pointer to source dbtb */
  mlib_s16 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;   /* destinbtion dbtb */
  mlib_d64 bcc0, bcc1, bcc2; /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];
  const mlib_s16 *tbb2 = &tbble[2][32768];
  mlib_s32 s00, s01, s02, s03;

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  i = 0;

  if (xsize >= 4) {

    s00 = (sp[0] << 1);
    s01 = (sp[1] << 1);
    s02 = (sp[2] << 1);
    s03 = (sp[3] << 1);
    sp += 4;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 8; i+=4, sp+=4) {
      t3 = VIS_LD_U16_I(tbb0, s01);
      t2 = VIS_LD_U16_I(tbb2, s00);
      t1 = VIS_LD_U16_I(tbb1, s00);
      t0 = VIS_LD_U16_I(tbb0, s00);
      bcc0 = vis_fbligndbtb(t3, bcc0);
      bcc0 = vis_fbligndbtb(t2, bcc0);
      bcc0 = vis_fbligndbtb(t1, bcc0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
      t3 = VIS_LD_U16_I(tbb1, s02);
      t2 = VIS_LD_U16_I(tbb0, s02);
      t1 = VIS_LD_U16_I(tbb2, s01);
      t0 = VIS_LD_U16_I(tbb1, s01);
      bcc1 = vis_fbligndbtb(t3, bcc1);
      bcc1 = vis_fbligndbtb(t2, bcc1);
      bcc1 = vis_fbligndbtb(t1, bcc1);
      bcc1 = vis_fbligndbtb(t0, bcc1);
      t3 = VIS_LD_U16_I(tbb2, s03);
      t2 = VIS_LD_U16_I(tbb1, s03);
      t1 = VIS_LD_U16_I(tbb0, s03);
      t0 = VIS_LD_U16_I(tbb2, s02);
      bcc2 = vis_fbligndbtb(t3, bcc2);
      bcc2 = vis_fbligndbtb(t2, bcc2);
      bcc2 = vis_fbligndbtb(t1, bcc2);
      bcc2 = vis_fbligndbtb(t0, bcc2);
      s00 = (sp[0] << 1);
      s01 = (sp[1] << 1);
      s02 = (sp[2] << 1);
      s03 = (sp[3] << 1);
      *dp++ = bcc0;
      *dp++ = bcc1;
      *dp++ = bcc2;
    }

    t3 = VIS_LD_U16_I(tbb0, s01);
    t2 = VIS_LD_U16_I(tbb2, s00);
    t1 = VIS_LD_U16_I(tbb1, s00);
    t0 = VIS_LD_U16_I(tbb0, s00);
    bcc0 = vis_fbligndbtb(t3, bcc0);
    bcc0 = vis_fbligndbtb(t2, bcc0);
    bcc0 = vis_fbligndbtb(t1, bcc0);
    bcc0 = vis_fbligndbtb(t0, bcc0);
    t3 = VIS_LD_U16_I(tbb1, s02);
    t2 = VIS_LD_U16_I(tbb0, s02);
    t1 = VIS_LD_U16_I(tbb2, s01);
    t0 = VIS_LD_U16_I(tbb1, s01);
    bcc1 = vis_fbligndbtb(t3, bcc1);
    bcc1 = vis_fbligndbtb(t2, bcc1);
    bcc1 = vis_fbligndbtb(t1, bcc1);
    bcc1 = vis_fbligndbtb(t0, bcc1);
    t3 = VIS_LD_U16_I(tbb2, s03);
    t2 = VIS_LD_U16_I(tbb1, s03);
    t1 = VIS_LD_U16_I(tbb0, s03);
    t0 = VIS_LD_U16_I(tbb2, s02);
    bcc2 = vis_fbligndbtb(t3, bcc2);
    bcc2 = vis_fbligndbtb(t2, bcc2);
    bcc2 = vis_fbligndbtb(t1, bcc2);
    bcc2 = vis_fbligndbtb(t0, bcc2);
    *dp++ = bcc0;
    *dp++ = bcc1;
    *dp++ = bcc2;
    i += 4;
  }

  dl = (mlib_s16*)dp;

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
void mlib_v_ImbgeLookUpSI_S16_S16_3(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble)
{
  mlib_s16  *sl;
  mlib_s16 *dl;
  mlib_s32 i, j;
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];
  const mlib_s16 *tbb2 = &tbble[2][32768];

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j ++) {
    mlib_s16 *sp = sl;
    mlib_s16*dp = dl;
    mlib_s32 off, s0, size = xsize;

    off = ((mlib_bddr)dp & 7) >> 1;
    off = (off < size) ? off : size;

    for (i = 0; i < off; i++) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      *dp++ = tbb2[s0];
      size--;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUpSI_S16_S16_3_D1(sp, dp, size, tbble);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff0_D1(const mlib_s16 *src,
                                               mlib_s16       *dst,
                                               mlib_s32       xsize,
                                               const mlib_s16 **tbble)
{
  mlib_s16 *sp;              /* pointer to source dbtb */
  mlib_s32 s0;               /* source dbtb */
  mlib_s16 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;   /* destinbtion dbtb */
  mlib_d64 bcc;              /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];
  const mlib_s16 *tbb2 = &tbble[2][32768];
  const mlib_s16 *tbb3 = &tbble[3][32768];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  if (xsize >= 1) {

    s0 = (*sp++) << 1;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 2; i++) {
      t3 = VIS_LD_U16_I(tbb3, s0);
      t2 = VIS_LD_U16_I(tbb2, s0);
      t1 = VIS_LD_U16_I(tbb1, s0);
      t0 = VIS_LD_U16_I(tbb0, s0);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = (*sp++) << 1;
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbb3, s0);
    t2 = VIS_LD_U16_I(tbb2, s0);
    t1 = VIS_LD_U16_I(tbb1, s0);
    t0 = VIS_LD_U16_I(tbb0, s0);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    *dp++ = bcc;
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff1_D1(const mlib_s16 *src,
                                               mlib_s16       *dst,
                                               mlib_s32       xsize,
                                               const mlib_s16 **tbble)
{
  mlib_s16 *sp;              /* pointer to source dbtb */
  mlib_s32 s0, s1;           /* source dbtb */
  mlib_s16 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;   /* destinbtion dbtb */
  mlib_d64 bcc;              /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];
  const mlib_s16 *tbb2 = &tbble[2][32768];
  const mlib_s16 *tbb3 = &tbble[3][32768];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  s0 = (*sp++) << 1;

  if (xsize >= 1) {

    s1 = (*sp++) << 1;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 2; i++) {
      t3 = VIS_LD_U16_I(tbb0, s1);
      t2 = VIS_LD_U16_I(tbb3, s0);
      t1 = VIS_LD_U16_I(tbb2, s0);
      t0 = VIS_LD_U16_I(tbb1, s0);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s1;
      s1 = (*sp++) << 1;
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbb0, s1);
    t2 = VIS_LD_U16_I(tbb3, s0);
    t1 = VIS_LD_U16_I(tbb2, s0);
    t0 = VIS_LD_U16_I(tbb1, s0);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s1;
    *dp++ = bcc;
  }

  dl = (mlib_s16*)dp;
  s0 >>= 1;

  dl[0] = tbb1[s0];
  dl[1] = tbb2[s0];
  dl[2] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff2_D1(const mlib_s16 *src,
                                               mlib_s16       *dst,
                                               mlib_s32       xsize,
                                               const mlib_s16 **tbble)
{
  mlib_s16 *sp;              /* pointer to source dbtb */
  mlib_s32 s0, s1;           /* source dbtb */
  mlib_s16 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;   /* destinbtion dbtb */
  mlib_d64 bcc;              /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];
  const mlib_s16 *tbb2 = &tbble[2][32768];
  const mlib_s16 *tbb3 = &tbble[3][32768];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  s0 = (*sp++) << 1;

  if (xsize >= 1) {

    s1 = (*sp++) << 1;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 2; i++) {
      t3 = VIS_LD_U16_I(tbb1, s1);
      t2 = VIS_LD_U16_I(tbb0, s1);
      t1 = VIS_LD_U16_I(tbb3, s0);
      t0 = VIS_LD_U16_I(tbb2, s0);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s1;
      s1 = (*sp++) << 1;
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbb1, s1);
    t2 = VIS_LD_U16_I(tbb0, s1);
    t1 = VIS_LD_U16_I(tbb3, s0);
    t0 = VIS_LD_U16_I(tbb2, s0);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s1;
    *dp++ = bcc;
  }

  dl = (mlib_s16*)dp;
  s0 >>= 1;

  dl[0] = tbb2[s0];
  dl[1] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff3_D1(const mlib_s16 *src,
                                               mlib_s16       *dst,
                                               mlib_s32       xsize,
                                               const mlib_s16 **tbble)
{
  mlib_s16 *sp;              /* pointer to source dbtb */
  mlib_s32 s0, s1;           /* source dbtb */
  mlib_s16 *dl;              /* pointer to stbrt of destinbtion */
  mlib_d64 *dp;              /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;   /* destinbtion dbtb */
  mlib_d64 bcc;              /* destinbtion dbtb */
  mlib_s32 i;                /* loop vbribble */
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];
  const mlib_s16 *tbb2 = &tbble[2][32768];
  const mlib_s16 *tbb3 = &tbble[3][32768];

  sp   = (void *)src;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  s0 = (*sp++) << 1;

  if (xsize >= 1) {

    s1 = (*sp++) << 1;

#prbgmb pipeloop(0)
    for(i = 0; i <= xsize - 2; i++) {
      t3 = VIS_LD_U16_I(tbb2, s1);
      t2 = VIS_LD_U16_I(tbb1, s1);
      t1 = VIS_LD_U16_I(tbb0, s1);
      t0 = VIS_LD_U16_I(tbb3, s0);
      bcc = vis_fbligndbtb(t3, bcc);
      bcc = vis_fbligndbtb(t2, bcc);
      bcc = vis_fbligndbtb(t1, bcc);
      bcc = vis_fbligndbtb(t0, bcc);
      s0 = s1;
      s1 = (*sp++) << 1;
      *dp++ = bcc;
    }

    t3 = VIS_LD_U16_I(tbb2, s1);
    t2 = VIS_LD_U16_I(tbb1, s1);
    t1 = VIS_LD_U16_I(tbb0, s1);
    t0 = VIS_LD_U16_I(tbb3, s0);
    bcc = vis_fbligndbtb(t3, bcc);
    bcc = vis_fbligndbtb(t2, bcc);
    bcc = vis_fbligndbtb(t1, bcc);
    bcc = vis_fbligndbtb(t0, bcc);
    s0 = s1;
    *dp++ = bcc;
  }

  dl = (mlib_s16*)dp;
  s0 >>= 1;

  dl[0] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgeLookUpSI_S16_S16_4(const mlib_s16 *src,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsize,
                                    mlib_s32       ysize,
                                    const mlib_s16 **tbble)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  mlib_s32 j;
  const mlib_s16 *tbb0 = &tbble[0][32768];
  const mlib_s16 *tbb1 = &tbble[1][32768];
  const mlib_s16 *tbb2 = &tbble[2][32768];

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j ++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    mlib_s32 off, s0, size = xsize;

    if (size > 0) {
      off =  ((8 - ((mlib_bddr)dp & 7)) & 7) >> 1;

      if (off == 0) {
        mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff0_D1(sp, dp, size, tbble);
      } else if (off == 1) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        size--;
        mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff1_D1(sp, dp, size, tbble);
      } else if (off == 2) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        size--;
        mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff2_D1(sp, dp, size, tbble);
      } else if (off == 3) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        *dp++ = tbb2[s0];
        size--;
        mlib_v_ImbgeLookUpSI_S16_S16_4_DstOff3_D1(sp, dp, size, tbble);
      }
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
