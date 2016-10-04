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



/*
 * FUNCTIONS
 *      mlib_v_ImbgeChbnnelInsert_U8
 *      mlib_v_ImbgeChbnnelInsert_U8_12_A8D1X8
 *      mlib_v_ImbgeChbnnelInsert_U8_12_A8D2X8
 *      mlib_v_ImbgeChbnnelInsert_U8_12_D1
 *      mlib_v_ImbgeChbnnelInsert_U8_12
 *      mlib_v_ImbgeChbnnelInsert_U8_13_A8D1X8
 *      mlib_v_ImbgeChbnnelInsert_U8_13_A8D2X8
 *      mlib_v_ImbgeChbnnelInsert_U8_13_D1
 *      mlib_v_ImbgeChbnnelInsert_U8_13
 *      mlib_v_ImbgeChbnnelInsert_U8_14_A8D1X8
 *      mlib_v_ImbgeChbnnelInsert_U8_14_A8D2X8
 *      mlib_v_ImbgeChbnnelInsert_U8_14_D1
 *      mlib_v_ImbgeChbnnelInsert_U8_14
 *      mlib_v_ImbgeChbnnelInsert_S16
 *      mlib_v_ImbgeChbnnelInsert_S16_12_A8D1X4
 *      mlib_v_ImbgeChbnnelInsert_S16_12_A8D2X4
 *      mlib_v_ImbgeChbnnelInsert_S16_12_D1
 *      mlib_v_ImbgeChbnnelInsert_S16_12
 *      mlib_v_ImbgeChbnnelInsert_S16_13_A8D1X4
 *      mlib_v_ImbgeChbnnelInsert_S16_13_A8D2X4
 *      mlib_v_ImbgeChbnnelInsert_S16_13_D1
 *      mlib_v_ImbgeChbnnelInsert_S16_13
 *      mlib_v_ImbgeChbnnelInsert_S16_14_A8D1X4
 *      mlib_v_ImbgeChbnnelInsert_S16_14_A8D2X4
 *      mlib_v_ImbgeChbnnelInsert_S16_14_D1
 *      mlib_v_ImbgeChbnnelInsert_S16_14
 *      mlib_v_ImbgeChbnnelInsert_S32
 *      mlib_v_ImbgeChbnnelInsert_D64
 *
 * ARGUMENT
 *      src     pointer to source imbge dbtb
 *      dst     pointer to destinbtion imbge dbtb
 *      slb     source imbge line stride in bytes
 *      dlb     destinbtion imbge line stride in bytes
 *      dsize   imbge dbtb size in pixels
 *      xsize   imbge width in pixels
 *      ysize   imbge height in lines
 *      cmbsk   chbnnel mbsk
 *
 * DESCRIPTION
 *      Copy the 1-chbnnel source imbge into the selected chbnnel
 *      of the destinbtion imbge -- VIS version low level functions.
 *
 * NOTE
 *      These functions bre sepbrbted from mlib_v_ImbgeChbnnelInsert.c
 *      for loop unrolling bnd structure clbrity.
 */

#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_v_ImbgeChbnnelInsert.h"

/***************************************************************/
/* generbl chbnnel insertion: slower due to the inner loop */
void mlib_v_ImbgeChbnnelInsert_U8(const mlib_u8 *src,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      chbnnels,
                                  mlib_s32      chbnneld,
                                  mlib_s32      width,
                                  mlib_s32      height,
                                  mlib_s32      cmbsk)
{
  mlib_u8 *sp;                                        /* pointer for pixel in src */
  mlib_u8 *sl;                                        /* pointer for line in src */
  mlib_u8 *dp;                                        /* pointer for pixel in dst */
  mlib_u8 *dl;                                        /* pointer for line in dst */
  mlib_s32 i, j, k;                                   /* indices for x, y, chbnnel */
  mlib_s32 deltbc[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 inc0, inc1, inc2;
  mlib_u8 s0, s1, s2;

  deltbc[chbnnels] = 1;
  for (i = (chbnneld - 1), k = 0; i >= 0; i--) {
    if ((cmbsk & (1 << i)) == 0)
      deltbc[k]++;
    else
      k++;
  }

  deltbc[chbnnels] = chbnneld;
  for (i = 1; i < chbnnels; i++) {
    deltbc[chbnnels] -= deltbc[i];
  }

  sp = sl = (void *)src;
  dp = dl = dst + deltbc[0];

  if (chbnnels == 2) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[inc0] = s1;
        dp += inc1;
        sp += 2;
      }

      sp = sl += slb;
      dp = dl += dlb;
    }
  }
  else if (chbnnels == 3) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    inc2 = deltbc[3] + inc1;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[inc0] = s1;
        dp[inc1] = s2;
        dp += inc2;
        sp += 3;
      }

      sp = sl += slb;
      dp = dl += dlb;
    }
  }
}

/***************************************************************/
/* generbl chbnnel insertion: slower due to the inner loop */
void mlib_v_ImbgeChbnnelInsert_D64(const mlib_d64 *src,
                                   mlib_s32       slb,
                                   mlib_d64       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       chbnnels,
                                   mlib_s32       chbnneld,
                                   mlib_s32       width,
                                   mlib_s32       height,
                                   mlib_s32       cmbsk)
{
  mlib_d64 *sp;                                       /* pointer for pixel in src */
  mlib_d64 *sl;                                       /* pointer for line in src */
  mlib_d64 *dp;                                       /* pointer for pixel in dst */
  mlib_d64 *dl;                                       /* pointer for line in dst */
  mlib_s32 i, j, k;                                   /* indices for x, y, chbnnel */
  mlib_s32 deltbc[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 inc0, inc1, inc2;
  mlib_d64 s0, s1, s2;

  deltbc[chbnnels] = 1;
  for (i = (chbnneld - 1), k = 0; i >= 0; i--) {
    if ((cmbsk & (1 << i)) == 0)
      deltbc[k]++;
    else
      k++;
  }

  deltbc[chbnnels] = chbnneld;
  for (i = 1; i < chbnnels; i++) {
    deltbc[chbnnels] -= deltbc[i];
  }

  sp = sl = (void *)src;
  dp = dl = dst + deltbc[0];

  if (chbnnels == 1) {
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        dp[0] = s0;
        dp += chbnneld;
        sp++;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (chbnnels == 2) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[inc0] = s1;
        dp += inc1;
        sp += 2;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (chbnnels == 3) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    inc2 = deltbc[3] + inc1;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[inc0] = s1;
        dp[inc1] = s2;
        dp += inc2;
        sp += 3;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
/* generbl chbnnel insertion: slower due to the inner loop */
void mlib_v_ImbgeChbnnelInsert_S16(const mlib_s16 *src,
                                   mlib_s32       slb,
                                   mlib_s16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       chbnnels,
                                   mlib_s32       chbnneld,
                                   mlib_s32       width,
                                   mlib_s32       height,
                                   mlib_s32       cmbsk)
{
  mlib_s16 *sp;                                       /* pointer for pixel in src */
  mlib_s16 *sl;                                       /* pointer for line in src */
  mlib_s16 *dp;                                       /* pointer for pixel in dst */
  mlib_s16 *dl;                                       /* pointer for line in dst */
  mlib_s32 i, j, k;                                   /* indices for x, y, chbnnel */
  mlib_s32 deltbc[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 inc0, inc1, inc2;
  mlib_s16 s0, s1, s2;

  deltbc[chbnnels] = 1;
  for (i = (chbnneld - 1), k = 0; i >= 0; i--) {
    if ((cmbsk & (1 << i)) == 0)
      deltbc[k]++;
    else
      k++;
  }

  deltbc[chbnnels] = chbnneld;
  for (i = 1; i < chbnnels; i++) {
    deltbc[chbnnels] -= deltbc[i];
  }

  sp = sl = (void *)src;
  dp = dl = dst + deltbc[0];

  if (chbnnels == 2) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[inc0] = s1;
        dp += inc1;
        sp += 2;
      }

      sp = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (chbnnels == 3) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    inc2 = deltbc[3] + inc1;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[inc0] = s1;
        dp[inc1] = s2;
        dp += inc2;
        sp += 3;
      }

      sp = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
/* generbl chbnnel insertion: slower due to the inner loop */

void mlib_v_ImbgeChbnnelInsert_S32(const mlib_s32 *src,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       chbnnels,
                                   mlib_s32       chbnneld,
                                   mlib_s32       width,
                                   mlib_s32       height,
                                   mlib_s32       cmbsk)
{
  mlib_s32 *sp;                                       /* pointer for pixel in src */
  mlib_s32 *sl;                                       /* pointer for line in src */
  mlib_s32 *dp;                                       /* pointer for pixel in dst */
  mlib_s32 *dl;                                       /* pointer for line in dst */
  mlib_s32 i, j, k;                                   /* indices for x, y, chbnnel */
  mlib_s32 deltbc[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 inc0, inc1, inc2;
  mlib_s32 s0, s1, s2;

  deltbc[chbnnels] = 1;
  for (i = (chbnneld - 1), k = 0; i >= 0; i--) {
    if ((cmbsk & (1 << i)) == 0)
      deltbc[k]++;
    else
      k++;
  }

  deltbc[chbnnels] = chbnneld;
  for (i = 1; i < chbnnels; i++) {
    deltbc[chbnnels] -= deltbc[i];
  }

  sp = sl = (void *)src;
  dp = dl = dst + deltbc[0];

  if (chbnnels == 1) {
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        dp[0] = s0;
        dp += chbnneld;
        sp++;
      }

      sp = sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (chbnnels == 2) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[inc0] = s1;
        dp += inc1;
        sp += 2;
      }

      sp = sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (chbnnels == 3) {
    inc0 = deltbc[1];
    inc1 = deltbc[2] + inc0;
    inc2 = deltbc[3] + inc1;
    for (j = 0; j < height; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < width; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[inc0] = s1;
        dp[inc1] = s2;
        dp += inc2;
        sp += 3;
      }

      sp = sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
#define INSERT_U8_12(sd0, dd0, dd1)     /* chbnnel duplicbte */ \
  dd0 = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd0));        \
  dd1 = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd0))

/***************************************************************/
/* insert one chbnnel to b 2-chbnnel imbge.
 * both source bnd destinbtion imbge dbtb bre 8-byte bligned.
 * dsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelInsert_U8_12_A8D1X8(const mlib_u8 *src,
                                            mlib_u8       *dst,
                                            mlib_s32      dsize,
                                            mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0;
  mlib_d64 dd0, dd1;
  mlib_s32 bmbsk;
  mlib_s32 i;

  bmbsk = cmbsk | (cmbsk << 2) | (cmbsk << 4) | (cmbsk << 6);

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    sd0 = *sp++;
    INSERT_U8_12(sd0, dd0, dd1);
    vis_pst_8(dd0, dp++, bmbsk);
    vis_pst_8(dd1, dp++, bmbsk);
  }
}

/***************************************************************/
/* insert one chbnnel to b 2-chbnnel imbge.
 * both source bnd destinbtion imbge dbtb bre 8-byte bligned.
 * xsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelInsert_U8_12_A8D2X8(const mlib_u8 *src,
                                            mlib_s32      slb,
                                            mlib_u8       *dst,
                                            mlib_s32      dlb,
                                            mlib_s32      xsize,
                                            mlib_s32      ysize,
                                            mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0;
  mlib_d64 dd0, dd1;
  mlib_s32 bmbsk;
  mlib_s32 i, j;

  bmbsk = cmbsk | (cmbsk << 2) | (cmbsk << 4) | (cmbsk << 6);

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 8; i++) {
      sd0 = *sp++;
      INSERT_U8_12(sd0, dd0, dd1);
      vis_pst_8(dd0, dp++, bmbsk);
      vis_pst_8(dd1, dp++, bmbsk);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
/* insert one chbnnel to b 2-chbnnel imbge.
 */

void mlib_v_ImbgeChbnnelInsert_U8_12_D1(const mlib_u8 *src,
                                        mlib_u8       *dst,
                                        mlib_s32      dsize,
                                        mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend, *dend2;                              /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1;                                  /* 8-byte source dbtb */
  mlib_d64 dd0, dd1, dd2, dd3;                        /* 8-byte destinbtion dbtb */
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 off;                                       /* offset of src over dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 bmbsk;                                     /* chbnnel mbsk */
  mlib_s32 i, n;

  bmbsk = cmbsk | (cmbsk << 2) | (cmbsk << 4) | (cmbsk << 6);

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize * 2 - 1;
  dend2 = dend - 15;

  /* cblculbte the src's offset over dst */
  off = soff * 2 - doff;

  if (doff % 2 != 0) {
    bmbsk = (~bmbsk) & 0xff;
  }

  if (off == 0) {                           /* src bnd dst hbve sbme blignment */

    /* lobd 8 bytes */
    sd0 = *sp++;

    /* insert, including some gbrbbge bt the stbrt point */
    INSERT_U8_12(sd0, dd0, dd1);

    /* store 16 bytes result */
    embsk = vis_edge8(db, dend);
    vis_pst_8(dd0, dp++, embsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      embsk = vis_edge8(dp, dend);
      vis_pst_8(dd1, dp++, embsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        sd0 = *sp++;
        INSERT_U8_12(sd0, dd0, dd1);
        vis_pst_8(dd0, dp++, bmbsk);
        vis_pst_8(dd1, dp++, bmbsk);
      }
    }

    /* end point hbndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      sd0 = *sp++;
      INSERT_U8_12(sd0, dd0, dd1);
      embsk = vis_edge8(dp, dend);
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk & bmbsk);
      }
    }
  }
  else if (off < 0) {
    vis_blignbddr((void *)0, off);

    /* generbte edge mbsk for the stbrt point */
    embsk = vis_edge8(db, dend);

    /* lobd 8 bytes */
    sd0 = *sp++;

    /* insert bnd store 16 bytes */
    INSERT_U8_12(sd0, dd0, dd1);
    vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        dd2 = dd1;
        sd0 = *sp++;
        INSERT_U8_12(sd0, dd0, dd1);
        vis_pst_8(vis_fbligndbtb(dd2, dd0), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      }
    }

    /* end point hbndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      embsk = vis_edge8(dp, dend);
      dd2 = dd1;
      sd0 = *sp++;
      INSERT_U8_12(sd0, dd0, dd1);
      vis_pst_8(vis_fbligndbtb(dd2, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
      }
    }
  }
  else if (off < 8) {
    vis_blignbddr((void *)0, off);

    /* generbte edge mbsk for the stbrt point */
    embsk = vis_edge8(db, dend);

    /* lobd 16 bytes */
    sd0 = *sp++;
    sd1 = *sp++;

    /* insert bnd store 16 bytes */
    INSERT_U8_12(sd0, dd0, dd1);
    INSERT_U8_12(sd1, dd2, dd3);
    vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        dd0 = dd2;
        dd1 = dd3;
        sd1 = *sp++;
        INSERT_U8_12(sd1, dd2, dd3);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      }
    }

    /* end point hbndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      embsk = vis_edge8(dp, dend);
      dd0 = dd2;
      dd1 = dd3;
      sd1 = *sp++;
      INSERT_U8_12(sd1, dd2, dd3);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
      }
    }
  }
  else {                                    /* (off >= 8) */
    vis_blignbddr((void *)0, off);

    /* generbte edge mbsk for the stbrt point */
    embsk = vis_edge8(db, dend);

    /* lobd 16 bytes */
    sd0 = *sp++;
    sd1 = *sp++;

    /* insert bnd store 16 bytes */
    INSERT_U8_12(sd0, dd0, dd1);
    INSERT_U8_12(sd1, dd2, dd3);
    vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        dd0 = dd2;
        dd1 = dd3;
        sd1 = *sp++;
        INSERT_U8_12(sd1, dd2, dd3);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    /* end point hbndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      embsk = vis_edge8(dp, dend);
      dd0 = dd2;
      dd1 = dd3;
      sd1 = *sp++;
      INSERT_U8_12(sd1, dd2, dd3);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
      }
    }
  }
}

/***************************************************************/
/* insert one chbnnel to b 2-chbnnel imbge.
 */

void mlib_v_ImbgeChbnnelInsert_U8_12(const mlib_u8 *src,
                                     mlib_s32      slb,
                                     mlib_u8       *dst,
                                     mlib_s32      dlb,
                                     mlib_s32      xsize,
                                     mlib_s32      ysize,
                                     mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_U8_12_D1(sb, db, xsize, cmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define INSERT_U8_13(sd0, dd0, dd1, dd2)                        \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd0));        \
  sdb = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  sdd = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd0 = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_hi(sdd));        \
  sde = vis_fpmerge(vis_rebd_lo(sdc), vis_rebd_lo(sdd));        \
  dd1 = vis_freg_pbir(vis_rebd_lo(dd0), vis_rebd_hi(sde));      \
  dd2 = vis_freg_pbir(vis_rebd_lo(sde), vis_rebd_lo(sde))

/***************************************************************/
#define LOAD_INSERT_STORE_U8_A8(chbnneld)                       \
  sd = *sp++;                                                   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld

/***************************************************************/
#define LOAD_INSERT_STORE_U8(chbnneld)                          \
  vis_blignbddr((void *)0, off);                                \
  sd0 = sd1;                                                    \
  sd1 = *sp++;                                                  \
  sd  = vis_fbligndbtb(sd0, sd1);                               \
  vis_blignbddr((void *)0, 1);                                  \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_13_A8D1X8(const mlib_u8 *src,
                                            mlib_u8       *dst,
                                            mlib_s32      dsize,
                                            mlib_s32      cmbsk)
{
  mlib_u8 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  vis_blignbddr((void *)0, 1);              /* for 1-byte left shift */

  sp = (mlib_d64 *) src;
  db = dst + (2 / cmbsk);                   /* 4,2,1 -> 0,1,2 */

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    LOAD_INSERT_STORE_U8_A8(3);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_13_A8D2X8(const mlib_u8 *src,
                                            mlib_s32      slb,
                                            mlib_u8       *dst,
                                            mlib_s32      dlb,
                                            mlib_s32      xsize,
                                            mlib_s32      ysize,
                                            mlib_s32      cmbsk)
{
  mlib_u8 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  vis_blignbddr((void *)0, 1);

  sp = sl = (mlib_d64 *) src;
  db = dl = dst + (2 / cmbsk);              /* 4,2,1 -> 0,1,2 */

  for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 8; i++) {
      LOAD_INSERT_STORE_U8_A8(3);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_13_D1(const mlib_u8 *src,
                                        mlib_u8       *dst,
                                        mlib_s32      dsize,
                                        mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend;                                      /* end point in destinbtion */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt points in src */
  mlib_d64 sd0, sd1, sd;                              /* 8-byte registers for source dbtb */
  mlib_s32 off;                                       /* offset of bddress blignment in src */
  mlib_s32 i;

  /* prepbre the src bddress */
  sb = (void *)src;
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  /* prepbre the dst bddress */
  db = dst + (2 / cmbsk);                   /* 4,2,1 -> 0,1,2 */
  dend = db + dsize * 3 - 1;

  sd1 = *sp++;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    LOAD_INSERT_STORE_U8(3);
  }

  /* right end hbndling */
  if ((mlib_bddr) db <= (mlib_bddr) dend) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 1);
    vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
    db += 3;
    if ((mlib_bddr) db <= (mlib_bddr) dend) {
      vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
      db += 3;
      if ((mlib_bddr) db <= (mlib_bddr) dend) {
        vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
        db += 3;
        if ((mlib_bddr) db <= (mlib_bddr) dend) {
          vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
          db += 3;
          if ((mlib_bddr) db <= (mlib_bddr) dend) {
            vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
            db += 3;
            if ((mlib_bddr) db <= (mlib_bddr) dend) {
              vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
              db += 3;
              if ((mlib_bddr) db <= (mlib_bddr) dend) {
                vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
              }
            }
          }
        }
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_13(const mlib_u8 *src,
                                     mlib_s32      slb,
                                     mlib_u8       *dst,
                                     mlib_s32      dlb,
                                     mlib_s32      xsize,
                                     mlib_s32      ysize,
                                     mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_U8_13_D1(sb, db, xsize, cmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define INSERT_U8_14(sd0, dd0, dd1, dd2, dd3)                   \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd0));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd0));        \
  dd0 = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  dd1 = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd2 = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  dd3 = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb))

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_14_A8D1X8(const mlib_u8 *src,
                                            mlib_u8       *dst,
                                            mlib_s32      dsize,
                                            mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0;
  mlib_d64 sdb, sdb;
  mlib_d64 dd0, dd1, dd2, dd3;
  mlib_s32 bmbsk;
  mlib_s32 i;

  bmbsk = cmbsk | (cmbsk << 4);

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    sd0 = *sp++;
    INSERT_U8_14(sd0, dd0, dd1, dd2, dd3);
    vis_pst_8(dd0, dp++, bmbsk);
    vis_pst_8(dd1, dp++, bmbsk);
    vis_pst_8(dd2, dp++, bmbsk);
    vis_pst_8(dd3, dp++, bmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_14_A8D2X8(const mlib_u8 *src,
                                            mlib_s32      slb,
                                            mlib_u8       *dst,
                                            mlib_s32      dlb,
                                            mlib_s32      xsize,
                                            mlib_s32      ysize,
                                            mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0;
  mlib_d64 sdb, sdb;
  mlib_d64 dd0, dd1, dd2, dd3;
  mlib_s32 bmbsk;
  mlib_s32 i, j;

  bmbsk = cmbsk | (cmbsk << 4);

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 8; i++) {
      sd0 = *sp++;
      INSERT_U8_14(sd0, dd0, dd1, dd2, dd3);
      vis_pst_8(dd0, dp++, bmbsk);
      vis_pst_8(dd1, dp++, bmbsk);
      vis_pst_8(dd2, dp++, bmbsk);
      vis_pst_8(dd3, dp++, bmbsk);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_14_D1(const mlib_u8 *src,
                                        mlib_u8       *dst,
                                        mlib_s32      dsize,
                                        mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend, *dend2;                              /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1, sd;                              /* 8-byte source dbtb */
  mlib_d64 sdb, sdb;
  mlib_d64 dd0, dd1, dd2, dd3, dd4;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 bmbsk;                                     /* chbnnel mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  bmbsk = cmbsk | (cmbsk << 4) | (cmbsk << 8);

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize * 4 - 1;
  dend2 = dend - 31;

  bmbsk = (bmbsk >> (doff % 4)) & 0xff;

  if (doff == 0) {                          /* dst is 8-byte bligned */

    vis_blignbddr((void *)0, soff);
    sd0 = *sp++;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);          /* the intermedibte is bligned */

    INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

    embsk = vis_edge8(db, dend);
    vis_pst_8(dd0, dp++, embsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dend) { /* for very smbll size */
      embsk = vis_edge8(dp, dend);
      vis_pst_8(dd1, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd2, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd3, dp++, embsk & bmbsk);
        }
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 32 + 1;

      /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        sd0 = sd1;
        sd1 = *sp++;
        sd = vis_fbligndbtb(sd0, sd1);

        INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

        vis_pst_8(dd0, dp++, bmbsk);
        vis_pst_8(dd1, dp++, bmbsk);
        vis_pst_8(dd2, dp++, bmbsk);
        vis_pst_8(dd3, dp++, bmbsk);
      }
    }

    /* end point hbndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      sd0 = sd1;
      sd1 = *sp++;
      sd = vis_fbligndbtb(sd0, sd1);

      INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

      embsk = vis_edge8(dp, dend);
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend) {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(dd3, dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
  else {                                    /* dst is not 8-byte bligned */
    vis_blignbddr((void *)0, soff);
    sd0 = *sp++;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);          /* the intermedibte is bligned */

    INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

    vis_blignbddr((void *)0, -doff);

    embsk = vis_edge8(db, dend);
    vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dend) { /* for very smbll size */
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
        }
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 32 + 1;

      /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        dd4 = dd3;

        vis_blignbddr((void *)0, soff);
        sd0 = sd1;
        sd1 = *sp++;
        sd = vis_fbligndbtb(sd0, sd1);

        INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

        vis_blignbddr((void *)0, -doff);
        vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    /* end point hbndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dend) {
      dd4 = dd3;

      vis_blignbddr((void *)0, soff);
      sd0 = sd1;
      sd1 = *sp++;
      sd = vis_fbligndbtb(sd0, sd1);

      INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

      vis_blignbddr((void *)0, -doff);
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend) {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_U8_14(const mlib_u8 *src,
                                     mlib_s32      slb,
                                     mlib_u8       *dst,
                                     mlib_s32      dlb,
                                     mlib_s32      xsize,
                                     mlib_s32      ysize,
                                     mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_U8_14_D1(sb, db, xsize, cmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define LOAD_INSERT_STORE_S16_1X_A8(chbnneld)                   \
  sd  = *sp++;                                                  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld

/***************************************************************/
#define LOAD_INSERT_STORE_S16_1X(chbnneld)                      \
  vis_blignbddr((void *)0, off);                                \
  sd0 = sd1;                                                    \
  sd1 = *sp++;                                                  \
  sd  = vis_fbligndbtb(sd0, sd1);                               \
  vis_blignbddr((void *)0, 2);                                  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += chbnneld

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_12_A8D1X4(const mlib_s16 *src,
                                             mlib_s16       *dst,
                                             mlib_s32       dsize,
                                             mlib_s32       cmbsk)
{
  mlib_s16 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  db = dst + (2 - cmbsk);                   /* 2,1 -> 0,1 */

  vis_blignbddr((void *)0, 2);

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_1X_A8(2);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_12_A8D2X4(const mlib_s16 *src,
                                             mlib_s32       slb,
                                             mlib_s16       *dst,
                                             mlib_s32       dlb,
                                             mlib_s32       xsize,
                                             mlib_s32       ysize,
                                             mlib_s32       cmbsk)
{
  mlib_s16 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  db = dl = dst + (2 - cmbsk);              /* 2,1 -> 0,1 */

  vis_blignbddr((void *)0, 2);

  for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 4; i++) {
      LOAD_INSERT_STORE_S16_1X_A8(2);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_12_D1(const mlib_s16 *src,
                                         mlib_s16       *dst,
                                         mlib_s32       dsize,
                                         mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dend;                                     /* end point in destinbtion */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt points in src */
  mlib_d64 sd0, sd1, sd;                              /* 8-byte registers for source dbtb */
  mlib_s32 off;                                       /* offset of bddress blignment in src */
  mlib_s32 i;

  sb = (void *)src;
  db = dst + (2 - cmbsk);                   /* 2,1 -> 0,1 */

  /* prepbre the src bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  dend = db + dsize * 2 - 1;

  sd1 = *sp++;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_1X(2);
  }

  /* right end hbndling */
  if ((mlib_bddr) db <= (mlib_bddr) dend) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 2);
    vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
    db += 2;
    if ((mlib_bddr) db <= (mlib_bddr) dend) {
      vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      db += 2;
      if ((mlib_bddr) db <= (mlib_bddr) dend) {
        vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_12(const mlib_s16 *src,
                                      mlib_s32       slb,
                                      mlib_s16       *dst,
                                      mlib_s32       dlb,
                                      mlib_s32       xsize,
                                      mlib_s32       ysize,
                                      mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_S16_12_D1(sb, db, xsize, cmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_13_A8D1X4(const mlib_s16 *src,
                                             mlib_s16       *dst,
                                             mlib_s32       dsize,
                                             mlib_s32       cmbsk)
{
  mlib_s16 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  db = dst + (2 / cmbsk);                   /* 4,2,1 -> 0,1,2 */

  vis_blignbddr((void *)0, 2);

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_1X_A8(3);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_13_A8D2X4(const mlib_s16 *src,
                                             mlib_s32       slb,
                                             mlib_s16       *dst,
                                             mlib_s32       dlb,
                                             mlib_s32       xsize,
                                             mlib_s32       ysize,
                                             mlib_s32       cmbsk)
{
  mlib_s16 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  db = dl = dst + (2 / cmbsk);              /* 4,2,1 -> 0,1,2 */

  vis_blignbddr((void *)0, 2);

  for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 4; i++) {
      LOAD_INSERT_STORE_S16_1X_A8(3);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_13_D1(const mlib_s16 *src,
                                         mlib_s16       *dst,
                                         mlib_s32       dsize,
                                         mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dend;                                     /* end point in destinbtion */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt points in src */
  mlib_d64 sd0, sd1, sd;                              /* 8-byte registers for source dbtb */
  mlib_s32 off;                                       /* offset of bddress blignment in src */
  mlib_s32 i;

  sb = (void *)src;
  db = dst + (2 / cmbsk);                   /* 4,2,1 -> 0,1,2 */

  /* prepbre the src bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  dend = db + dsize * 3 - 1;

  sd1 = *sp++;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_1X(3);
  }

  /* right end hbndling */
  if ((mlib_bddr) db <= (mlib_bddr) dend) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 2);
    vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
    db += 3;
    if ((mlib_bddr) db <= (mlib_bddr) dend) {
      vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      db += 3;
      if ((mlib_bddr) db <= (mlib_bddr) dend) {
        vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_13(const mlib_s16 *src,
                                      mlib_s32       slb,
                                      mlib_s16       *dst,
                                      mlib_s32       dlb,
                                      mlib_s32       xsize,
                                      mlib_s32       ysize,
                                      mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_S16_13_D1(sb, db, xsize, cmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
#define INSERT_S16_14(sp, dp, bmbsk)    /* chbnnel duplicbte */ \
  /* obsolete: it is slower thbn the vis_st_u16() version*/     \
  sd0 = *sp++;                                                  \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd0));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd0));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  sdd = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  sde = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  sdf = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd0 = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc));        \
  dd1 = vis_fpmerge(vis_rebd_hi(sdd), vis_rebd_lo(sdd));        \
  dd2 = vis_fpmerge(vis_rebd_hi(sde), vis_rebd_lo(sde));        \
  dd3 = vis_fpmerge(vis_rebd_hi(sdf), vis_rebd_lo(sdf));        \
  vis_pst_16(dd0, dp++, bmbsk);                                 \
  vis_pst_16(dd1, dp++, bmbsk);                                 \
  vis_pst_16(dd2, dp++, bmbsk);                                 \
  vis_pst_16(dd3, dp++, bmbsk)

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_14_A8D1X4(const mlib_s16 *src,
                                             mlib_s16       *dst,
                                             mlib_s32       dsize,
                                             mlib_s32       cmbsk)
{
  mlib_s16 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  db = dst + (6 / cmbsk + 1) / 2;           /* 8,4,2,1 -> 0,1,2,3 */

  vis_blignbddr((void *)0, 2);

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_1X_A8(4);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_14_A8D2X4(const mlib_s16 *src,
                                             mlib_s32       slb,
                                             mlib_s16       *dst,
                                             mlib_s32       dlb,
                                             mlib_s32       xsize,
                                             mlib_s32       ysize,
                                             mlib_s32       cmbsk)
{
  mlib_s16 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  db = dl = dst + (6 / cmbsk + 1) / 2;      /* 8,4,2,1 -> 0,1,2,3 */

  vis_blignbddr((void *)0, 2);

  for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 4; i++) {
      LOAD_INSERT_STORE_S16_1X_A8(4);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_14_D1(const mlib_s16 *src,
                                         mlib_s16       *dst,
                                         mlib_s32       dsize,
                                         mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dend;                                     /* end point in destinbtion */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt points in src */
  mlib_d64 sd0, sd1, sd;                              /* 8-byte registers for source dbtb */
  mlib_s32 off;                                       /* offset of bddress blignment in src */
  mlib_s32 i;

  sb = (void *)src;
  db = dst + (6 / cmbsk + 1) / 2;           /* 8,4,2,1 -> 0,1,2,3 */

  /* prepbre the src bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  dend = db + dsize * 4 - 1;

  sd1 = *sp++;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_1X(4);
  }

  /* right end hbndling */
  if ((mlib_bddr) db <= (mlib_bddr) dend) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 2);
    vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
    db += 4;
    if ((mlib_bddr) db <= (mlib_bddr) dend) {
      vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      db += 4;
      if ((mlib_bddr) db <= (mlib_bddr) dend) {
        vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelInsert_S16_14(const mlib_s16 *src,
                                      mlib_s32       slb,
                                      mlib_s16       *dst,
                                      mlib_s32       dlb,
                                      mlib_s32       xsize,
                                      mlib_s32       ysize,
                                      mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_S16_14_D1(sb, db, xsize, cmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
