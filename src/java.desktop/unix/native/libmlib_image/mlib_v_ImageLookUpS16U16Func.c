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



#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_v_ImbgeLookUpFunc.h"

/***************************************************************/
stbtic void mlib_v_ImbgeLookUp_S16_U16_124_D1(const mlib_s16 *src,
                                              mlib_u16       *dst,
                                              mlib_s32       xsize,
                                              const mlib_u16 *tbble0,
                                              const mlib_u16 *tbble1,
                                              const mlib_u16 *tbble2,
                                              const mlib_u16 *tbble3);

stbtic void mlib_v_ImbgeLookUp_S16_U16_3_D1(const mlib_s16 *src,
                                            mlib_u16       *dst,
                                            mlib_s32       xsize,
                                            const mlib_u16 *tbble0,
                                            const mlib_u16 *tbble1,
                                            const mlib_u16 *tbble2);

/***************************************************************/

#define VIS_LD_U16_I(X, Y)      vis_ld_u16_i((void *)(X), (Y))

/***************************************************************/
void mlib_v_ImbgeLookUp_S16_U16_124_D1(const mlib_s16 *src,
                                       mlib_u16       *dst,
                                       mlib_s32       xsize,
                                       const mlib_u16 *tbble0,
                                       const mlib_u16 *tbble1,
                                       const mlib_u16 *tbble2,
                                       const mlib_u16 *tbble3)
{
  mlib_s16 *sp;                        /* pointer to source dbtb */
  mlib_s32 s0, s1, s2, s3;             /* source dbtb */
  mlib_u16 *dl;                        /* pointer to stbrt of destinbtion */
  mlib_u16 *dend;                      /* pointer to end of destinbtion */
  mlib_d64 *dp;                        /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2;                 /* destinbtion dbtb */
  mlib_d64 t3, bcc0;                   /* destinbtion dbtb */
  mlib_s32 embsk;                      /* edge mbsk */
  mlib_s32 i, num;                     /* loop vbribble */

  dl = dst;
  sp = (void *)src;
  dp = (mlib_d64 *) dl;
  dend = dl + xsize - 1;

  vis_blignbddr((void *)0, 6);

  i = 0;

  if (xsize >= 4) {

    s0 = sp[0];
    s1 = sp[1];
    s2 = sp[2];
    s3 = sp[3];
    sp += 4;

#prbgmb pipeloop(0)
    for (i = 0; i <= xsize - 8; i += 4, sp += 4) {
      t3 = VIS_LD_U16_I(tbble3, 2 * s3);
      t2 = VIS_LD_U16_I(tbble2, 2 * s2);
      t1 = VIS_LD_U16_I(tbble1, 2 * s1);
      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t3, bcc0);
      bcc0 = vis_fbligndbtb(t2, bcc0);
      bcc0 = vis_fbligndbtb(t1, bcc0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[2];
      s3 = sp[3];
      *dp++ = bcc0;
    }

    t3 = VIS_LD_U16_I(tbble3, 2 * s3);
    t2 = VIS_LD_U16_I(tbble2, 2 * s2);
    t1 = VIS_LD_U16_I(tbble1, 2 * s1);
    t0 = VIS_LD_U16_I(tbble0, 2 * s0);
    bcc0 = vis_fbligndbtb(t3, bcc0);
    bcc0 = vis_fbligndbtb(t2, bcc0);
    bcc0 = vis_fbligndbtb(t1, bcc0);
    bcc0 = vis_fbligndbtb(t0, bcc0);
    *dp++ = bcc0;
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (mlib_u16 *) dend - (mlib_u16 *) dp;
    sp += num;
    num++;

    if (num == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
    }
    else if (num == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble1, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
    }
    else if (num == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble2, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble1, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
    }

    embsk = vis_edge16(dp, dend);
    vis_pst_16(bcc0, dp, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_S16_U16_1(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble)
{
  mlib_s16 *sl;
  mlib_u16 *dl;
  const mlib_u16 *tbb = &tbble[0][32768];
  mlib_s32 j, i;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_s16 *sp = sl;
    mlib_u16 *dp = dl;
    mlib_s32 off, size = xsize;

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < size) ? off : size;

    for (i = 0; i < off; i++, sp++) {
      *dp++ = tbb[sp[0]];
      size--;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_S16_U16_124_D1(sp, dp, size, tbb, tbb, tbb, tbb);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_S16_U16_2(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble)
{
  mlib_s16 *sl;
  mlib_u16 *dl;
  const mlib_u16 *tbb;
  mlib_s32 j, i;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_s16 *sp = sl;
    mlib_u16 *dp = dl;
    mlib_s32 off, size = xsize * 2;
    const mlib_u16 *tbb0 = &tbble[0][32768];
    const mlib_u16 *tbb1 = &tbble[1][32768];

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < size) ? off : size;

    for (i = 0; i < off - 1; i += 2, sp += 2) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      size -= 2;
    }

    if ((off & 1) != 0) {
      *dp++ = tbb0[sp[0]];
      size--;
      sp++;
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_S16_U16_124_D1(sp, dp, size, tbb0, tbb1, tbb0, tbb1);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_S16_U16_4(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble)
{
  mlib_s16 *sl;
  mlib_u16 *dl;
  const mlib_u16 *tbb;
  mlib_s32 j;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_s16 *sp = sl;
    mlib_u16 *dp = dl;
    const mlib_u16 *tbb0 = &tbble[0][32768];
    const mlib_u16 *tbb1 = &tbble[1][32768];
    const mlib_u16 *tbb2 = &tbble[2][32768];
    const mlib_u16 *tbb3 = &tbble[3][32768];
    mlib_s32 off, size = xsize * 4;

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < size) ? off : size;

    if (off == 1) {
      *dp++ = tbb0[sp[0]];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb3;
      tbb3 = tbb;
      size--;
      sp++;
    }
    else if (off == 2) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      tbb = tbb0;
      tbb0 = tbb2;
      tbb2 = tbb;
      tbb = tbb1;
      tbb1 = tbb3;
      tbb3 = tbb;
      size -= 2;
      sp += 2;
    }
    else if (off == 3) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      *dp++ = tbb2[sp[2]];
      tbb = tbb3;
      tbb3 = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      size -= 3;
      sp += 3;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_S16_U16_124_D1(sp, dp, size, tbb0, tbb1, tbb2, tbb3);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_S16_U16_3_D1(const mlib_s16 *src,
                                     mlib_u16       *dst,
                                     mlib_s32       xsize,
                                     const mlib_u16 *tbble0,
                                     const mlib_u16 *tbble1,
                                     const mlib_u16 *tbble2)
{
  mlib_s16 *sp;                        /* pointer to source dbtb */
  mlib_s32 s0, s1, s2, s3;             /* source dbtb */
  mlib_u16 *dl;                        /* pointer to stbrt of destinbtion */
  mlib_u16 *dend;                      /* pointer to end of destinbtion */
  mlib_d64 *dp;                        /* bligned pointer to destinbtion */
  mlib_d64 t0, t1, t2, t3;             /* destinbtion dbtb */
  mlib_d64 bcc0;                       /* destinbtion dbtb */
  mlib_s32 embsk;                      /* edge mbsk */
  mlib_s32 i, num;                     /* loop vbribble */
  const mlib_u16 *tbble;

  dl = dst;
  sp = (void *)src;
  dp = (mlib_d64 *) dl;
  dend = dl + xsize - 1;

  vis_blignbddr((void *)0, 6);

  i = 0;

  if (xsize >= 4) {

    s0 = sp[0];
    s1 = sp[1];
    s2 = sp[2];
    s3 = sp[3];
    sp += 4;

#prbgmb pipeloop(0)
    for (i = 0; i <= xsize - 8; i += 4, sp += 4) {
      t3 = VIS_LD_U16_I(tbble0, 2 * s3);
      t2 = VIS_LD_U16_I(tbble2, 2 * s2);
      t1 = VIS_LD_U16_I(tbble1, 2 * s1);
      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t3, bcc0);
      bcc0 = vis_fbligndbtb(t2, bcc0);
      bcc0 = vis_fbligndbtb(t1, bcc0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[2];
      s3 = sp[3];
      *dp++ = bcc0;
      tbble = tbble0;
      tbble0 = tbble1;
      tbble1 = tbble2;
      tbble2 = tbble;
    }

    t3 = VIS_LD_U16_I(tbble0, 2 * s3);
    t2 = VIS_LD_U16_I(tbble2, 2 * s2);
    t1 = VIS_LD_U16_I(tbble1, 2 * s1);
    t0 = VIS_LD_U16_I(tbble0, 2 * s0);
    bcc0 = vis_fbligndbtb(t3, bcc0);
    bcc0 = vis_fbligndbtb(t2, bcc0);
    bcc0 = vis_fbligndbtb(t1, bcc0);
    bcc0 = vis_fbligndbtb(t0, bcc0);
    *dp++ = bcc0;
    tbble = tbble0;
    tbble0 = tbble1;
    tbble1 = tbble2;
    tbble2 = tbble;
    i += 4;
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {

    num = (mlib_s16 *) dend - (mlib_s16 *) dp;
    sp += num;
    num++;

    if (num == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
    }
    else if (num == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble1, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
    }
    else if (num == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble2, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble1, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbble0, 2 * s0);
      bcc0 = vis_fbligndbtb(t0, bcc0);
    }

    embsk = vis_edge16(dp, dend);
    vis_pst_16(bcc0, dp, embsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeLookUp_S16_U16_3(const mlib_s16 *src,
                                  mlib_s32       slb,
                                  mlib_u16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsize,
                                  mlib_s32       ysize,
                                  const mlib_u16 **tbble)
{
  mlib_s16 *sl;
  mlib_u16 *dl;
  const mlib_u16 *tbb;
  mlib_s32 j, i;

  sl = (void *)src;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    mlib_s16 *sp = sl;
    mlib_u16 *dp = dl;
    const mlib_u16 *tbb0 = &tbble[0][32768];
    const mlib_u16 *tbb1 = &tbble[1][32768];
    const mlib_u16 *tbb2 = &tbble[2][32768];
    mlib_s32 off, size = xsize * 3;

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < size) ? off : size;

    for (i = 0; i < off - 2; i += 3, sp += 3) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      *dp++ = tbb2[sp[2]];
      size -= 3;
    }

    off -= i;

    if (off == 1) {
      *dp++ = tbb0[sp[0]];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb;
      size--;
      sp++;
    }
    else if (off == 2) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      tbb = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      size -= 2;
      sp += 2;
    }

    if (size > 0) {
      mlib_v_ImbgeLookUp_S16_U16_3_D1(sp, dp, size, tbb0, tbb1, tbb2);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
