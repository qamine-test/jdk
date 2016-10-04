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
 * FILENAME: mlib_ImbgeChbnnelExtrbct_1.c
 *
 * FUNCTIONS
 *      mlib_v_ImbgeChbnnelExtrbct_U8_21_A8D1X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_21_A8D2X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_21_D1
 *      mlib_v_ImbgeChbnnelExtrbct_U8_21
 *      mlib_v_ImbgeChbnnelExtrbct_U8_31_A8D1X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_31_A8D2X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_31_D1
 *      mlib_v_ImbgeChbnnelExtrbct_U8_31
 *      mlib_v_ImbgeChbnnelExtrbct_U8_41_A8D1X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_41_A8D2X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_41_D1
 *      mlib_v_ImbgeChbnnelExtrbct_U8_41
 *      mlib_v_ImbgeChbnnelExtrbct_S16_21_A8D1X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_21_A8D2X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_21_D1
 *      mlib_v_ImbgeChbnnelExtrbct_S16_21
 *      mlib_v_ImbgeChbnnelExtrbct_S16_31_A8D1X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_31_A8D2X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_31_D1
 *      mlib_v_ImbgeChbnnelExtrbct_S16_31
 *      mlib_v_ImbgeChbnnelExtrbct_S16_41_A8D1X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_41_A8D2X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_41_D1
 *      mlib_v_ImbgeChbnnelExtrbct_S16_41
 *
 * ARGUMENT
 *      src    pointer to source imbge dbtb
 *      dst    pointer to destinbtion imbge dbtb
 *      slb    source imbge line stride in bytes
 *      dlb   destinbtion imbge line stride in bytes
 *      dsize  imbge dbtb size in pixels
 *      xsize  imbge width in pixels
 *      ysize  imbge height in lines
 *      cmbsk chbnnel mbsk
 *
 * DESCRIPTION
 *      Extrbct the one selected chbnnel of the source imbge into the
 *      1-chbnnel destinbtion imbge.
 *
 * NOTE
 *      These functions bre sepbrbted from mlib_ImbgeChbnnelExtrbct.c
 *      for loop unrolling bnd structure clbrity.
 */

#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_v_ImbgeChbnnelExtrbct.h"

/***************************************************************/
#define CHANNELEXTRACT_U8_21L(sd0, sd1, dd)                     \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd1));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  sdd = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_hi(sdd))

/***************************************************************/
#define CHANNELEXTRACT_U8_21R(sd0, sd1, dd)                     \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd1));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  sdd = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd  = vis_fpmerge(vis_rebd_lo(sdc), vis_rebd_lo(sdd))

/***************************************************************/
/* extrbct one chbnnel from b 2-chbnnel imbge.
 * both source bnd destinbtion imbge dbtb bre 8-byte bligned.
 * xsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_21_A8D1X8(const mlib_u8 *src,
                                             mlib_u8       *dst,
                                             mlib_s32      dsize,
                                             mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdc, sdd;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  if (cmbsk == 2) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_U8_21L(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
  else {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_U8_21R(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
/* extrbct one chbnnel from b 2-chbnnel imbge.
 * both source bnd destinbtion imbge dbtb bre 8-byte bligned.
 * xsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_21_A8D2X8(const mlib_u8 *src,
                                             mlib_s32      slb,
                                             mlib_u8       *dst,
                                             mlib_s32      dlb,
                                             mlib_s32      xsize,
                                             mlib_s32      ysize,
                                             mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdc, sdd;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  if (cmbsk == 2) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_U8_21L(sd0, sd1, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_U8_21R(sd0, sd1, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
/* extrbct one chbnnel from b 2-chbnnel imbge.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_21_D1(const mlib_u8 *src,
                                         mlib_u8       *dst,
                                         mlib_s32      dsize,
                                         mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend, *dend2;                              /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 sdb, sdb, sdc, sdd;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 off;                                       /* offset of src over dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize - 1;
  dend2 = dend - 7;

  /* cblculbte the src's offset over dst */
  if (cmbsk == 2) {
    off = soff / 2 - doff;
  }
  else {
    off = (soff + 1) / 2 - doff;
  }

  if (((cmbsk == 2) && (soff % 2 == 0)) || ((cmbsk == 1) && (soff % 2 != 0))) { /* extrbct even bytes */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 16 bytes */
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 32 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);
        CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else {                                    /* extrbct odd bytes */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes, don't cbre the gbrbbge bt the stbrt point */
      sd0 = *sp++;
      sd1 = *sp++;

      /* extrbct bnd store 8 bytes */
      CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 16 bytes */
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 32 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
        CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
}

/***************************************************************/
/* extrbct one chbnnel from b 2-chbnnel imbge.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_21(const mlib_u8 *src,
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

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_U8_21_D1(sb, db, xsize, cmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd)                \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));        \
  sdd = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));        \
  sde = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_hi(sdc));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdd), vis_rebd_lo(sde))

/***************************************************************/
#define CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd)                \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));        \
  sdd = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));        \
  sde = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdc));        \
  dd  = vis_fpmerge(vis_rebd_lo(sdd), vis_rebd_hi(sde))

/***************************************************************/
#define CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd)                \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));        \
  sdd = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_hi(sdc));        \
  sde = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdc));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdd), vis_rebd_lo(sde))

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_31_A8D1X8(const mlib_u8 *src,
                                             mlib_u8       *dst,
                                             mlib_s32      dsize,
                                             mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdc, sdd, sde;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  if (cmbsk == 4) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  else if (cmbsk == 2) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  else {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_31_A8D2X8(const mlib_u8 *src,
                                             mlib_s32      slb,
                                             mlib_u8       *dst,
                                             mlib_s32      dlb,
                                             mlib_s32      xsize,
                                             mlib_s32      ysize,
                                             mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdc, sdd, sde;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  if (cmbsk == 4) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (cmbsk == 2) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_31_D1(const mlib_u8 *src,
                                         mlib_u8       *dst,
                                         mlib_s32      dsize,
                                         mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend, *dend2;                              /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1, sd2;                             /* 8-byte source dbtb */
  mlib_d64 sd3, sd4, sd5;
  mlib_d64 sdb, sdb, sdc, sdd, sde;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 off;                                       /* offset of src over dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize - 1;
  dend2 = dend - 7;

  /* cblculbte the src's offset over dst */
  if (cmbsk == 4) {
    off = soff / 3 - doff;
  }
  else if (cmbsk == 2) {
    off = (soff + 1) / 3 - doff;
  }
  else {
    off = (soff + 2) / 3 - doff;
  }

  if (((cmbsk == 4) && (soff % 3 == 0)) ||
      ((cmbsk == 2) && (soff % 3 == 2)) ||
      ((cmbsk == 1) && (soff % 3 == 1))) { /* extrbct left chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else if (((cmbsk == 4) && (soff % 3 == 1)) ||
           ((cmbsk == 2) && (soff % 3 == 0)) ||
           ((cmbsk == 1) && (soff % 3 == 2))) {
    /* extrbct middle chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else {                                    /* extrbct right chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_31(const mlib_u8 *src,
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

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_U8_31_D1(sb, db, xsize, cmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd)           \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd2));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_hi(sd3));        \
  sdd = vis_fpmerge(vis_rebd_lo(sd1), vis_rebd_lo(sd3));        \
  sde = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdc));        \
  sdf = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdd));        \
  dd  = vis_fpmerge(vis_rebd_hi(sde), vis_rebd_hi(sdf))

/***************************************************************/
#define CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd)          \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd2));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_hi(sd3));        \
  sdd = vis_fpmerge(vis_rebd_lo(sd1), vis_rebd_lo(sd3));        \
  sde = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdc));        \
  sdf = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdd));        \
  dd  = vis_fpmerge(vis_rebd_lo(sde), vis_rebd_lo(sdf))

/***************************************************************/
#define CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd)          \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd2));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_hi(sd3));        \
  sdd = vis_fpmerge(vis_rebd_lo(sd1), vis_rebd_lo(sd3));        \
  sde = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdc));        \
  sdf = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdd));        \
  dd  = vis_fpmerge(vis_rebd_hi(sde), vis_rebd_hi(sdf))

/***************************************************************/
#define CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd)           \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd2));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_hi(sd3));        \
  sdd = vis_fpmerge(vis_rebd_lo(sd1), vis_rebd_lo(sd3));        \
  sde = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdc));        \
  sdf = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdd));        \
  dd  = vis_fpmerge(vis_rebd_lo(sde), vis_rebd_lo(sdf))

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_41_A8D1X8(const mlib_u8 *src,
                                             mlib_u8       *dst,
                                             mlib_s32      dsize,
                                             mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdc, sdd, sde, sdf;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  if (cmbsk == 8) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  else if (cmbsk == 4) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  else if (cmbsk == 2) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  else {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_41_A8D2X8(const mlib_u8 *src,
                                             mlib_s32      slb,
                                             mlib_u8       *dst,
                                             mlib_s32      dlb,
                                             mlib_s32      xsize,
                                             mlib_s32      ysize,
                                             mlib_s32      cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdc, sdd, sde, sdf;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  if (cmbsk == 8) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (cmbsk == 4) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (cmbsk == 2) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_41_D1(const mlib_u8 *src,
                                         mlib_u8       *dst,
                                         mlib_s32      dsize,
                                         mlib_s32      cmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend, *dend2;                              /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 sd4, sd5, sd6, sd7;
  mlib_d64 sdb, sdb, sdc, sdd;
  mlib_d64 sde, sdf;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 off;                                       /* offset of src over dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize - 1;
  dend2 = dend - 7;

  /* cblculbte the src's offset over dst */
  if (cmbsk == 8) {
    off = soff / 4 - doff;
  }
  else if (cmbsk == 4) {
    off = (soff + 1) / 4 - doff;
  }
  else if (cmbsk == 2) {
    off = (soff + 2) / 4 - doff;
  }
  else {
    off = (soff + 3) / 4 - doff;
  }

  if (((cmbsk == 8) && (soff % 4 == 0)) ||
      ((cmbsk == 4) && (soff % 4 == 3)) ||
      ((cmbsk == 2) && (soff % 4 == 2)) ||
      ((cmbsk == 1) && (soff % 4 == 1))) { /* extrbct left chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_U8_41L(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else if (((cmbsk == 8) && (soff % 4 == 1)) ||
           ((cmbsk == 4) && (soff % 4 == 0)) ||
           ((cmbsk == 2) && (soff % 4 == 3)) ||
           ((cmbsk == 1) && (soff % 4 == 2))) {
    /* extrbct middle left chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_U8_41ML(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else if (((cmbsk == 8) && (soff % 4 == 2)) ||
           ((cmbsk == 4) && (soff % 4 == 1)) ||
           ((cmbsk == 2) && (soff % 4 == 0)) ||
           ((cmbsk == 1) && (soff % 4 == 3))) { /* extrbct middle right chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_U8_41MR(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else {                                    /* extrbct right chbnnel */
    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_8(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge8(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_U8_41R(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_41(const mlib_u8 *src,
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

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_U8_41_D1(sb, db, xsize, cmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define CHANNELEXTRACT_S16_21L(sd0, sd1, dd)                    \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd1));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
#define CHANNELEXTRACT_S16_21R(sd0, sd1, dd)                    \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd1));        \
  sdc = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
/* extrbct one chbnnel from b 2-chbnnel imbge.
 * both source bnd destinbtion imbge dbtb bre 8-byte bligned.
 * dsize is multiple of 4.
 */

void mlib_v_ImbgeChbnnelExtrbct_S16_21_A8D1X4(const mlib_s16 *src,
                                              mlib_s16       *dst,
                                              mlib_s32       dsize,
                                              mlib_s32       cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  if (cmbsk == 2) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_S16_21L(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
  else {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_S16_21R(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_21_A8D2X4(const mlib_s16 *src,
                                              mlib_s32       slb,
                                              mlib_s16       *dst,
                                              mlib_s32       dlb,
                                              mlib_s32       xsize,
                                              mlib_s32       ysize,
                                              mlib_s32       cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  if (cmbsk == 2) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_S16_21L(sd0, sd1, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_S16_21R(sd0, sd1, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_21_D1(const mlib_s16 *src,
                                          mlib_s16       *dst,
                                          mlib_s32       dsize,
                                          mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dend, *dend2;                             /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 off;                                       /* offset of dst over src */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize - 1;
  dend2 = dend - 3;

  /* cblculbte the src's offset over dst */
  if (cmbsk == 2) {
    off = (soff / 4) * 2 - doff;
  }
  else {
    off = ((soff + 3) / 4) * 2 - doff;
  }

  if (((cmbsk == 2) && (soff % 4 == 0)) || ((cmbsk == 1) && (soff % 4 != 0))) { /* extrbct even words */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 16 bytes */
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 32 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);
        CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else {                                    /* extrbct odd words */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes, don't cbre the gbrbbge bt the stbrt point */
      sd0 = *sp++;
      sd1 = *sp++;

      /* extrbct bnd store 8 bytes */
      CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 16 bytes */
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 32 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
        CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_21(const mlib_s16 *src,
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

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_S16_21_D1(sb, db, xsize, cmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
#define CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd)               \
  /* extrbct the left chbnnel */                                \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
#define CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd)               \
  /* extrbct the middle chbnnel */                              \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));        \
  sdb = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));        \
  sdc = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_hi(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
#define CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd)               \
  /* extrbct the right chbnnel */                               \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));        \
  sdb = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_31_A8D1X4(const mlib_s16 *src,
                                              mlib_s16       *dst,
                                              mlib_s32       dsize,
                                              mlib_s32       cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  if (cmbsk == 4) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  else if (cmbsk == 2) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  else {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_31_A8D2X4(const mlib_s16 *src,
                                              mlib_s32       slb,
                                              mlib_s16       *dst,
                                              mlib_s32       dlb,
                                              mlib_s32       xsize,
                                              mlib_s32       ysize,
                                              mlib_s32       cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  if (cmbsk == 4) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (cmbsk == 2) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_31_D1(const mlib_s16 *src,
                                          mlib_s16       *dst,
                                          mlib_s32       dsize,
                                          mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dend, *dend2;                             /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1, sd2;                             /* 8-byte source dbtb */
  mlib_d64 sd3, sd4, sd5;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 off;                                       /* offset of src over dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize - 1;
  dend2 = dend - 3;

  /* cblculbte the src's offset over dst */
  if (cmbsk == 4) {
    off = (soff / 6) * 2 - doff;
  }
  else if (cmbsk == 2) {
    off = ((soff + 2) / 6) * 2 - doff;
  }
  else {
    off = ((soff + 4) / 6) * 2 - doff;
  }

  if (((cmbsk == 4) && (soff % 6 == 0)) ||
      ((cmbsk == 2) && (soff % 6 == 4)) ||
      ((cmbsk == 1) && (soff % 6 == 2))) { /* extrbct left chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else if (((cmbsk == 4) && (soff % 6 == 2)) ||
           ((cmbsk == 2) && (soff % 6 == 0)) ||
           ((cmbsk == 1) && (soff % 6 == 4))) {
    /* extrbct middle chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else {                                    /* extrbct right chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_31(const mlib_s16 *src,
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

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_S16_31_D1(sb, db, xsize, cmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
#define CHANNELEXTRACT_S16_41L(sd0, sd1,  sd2, sd3, dd)         \
  /* extrbct the left chbnnel */                                \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd2));        \
  sdb = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_hi(sd3));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
#define CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd)         \
  /* extrbct the middle left chbnnel */                         \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_hi(sd2));        \
  sdb = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_hi(sd3));        \
  sdc = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
#define CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd)         \
  /* extrbct the middle right chbnnel */                        \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd2));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd1), vis_rebd_lo(sd3));        \
  sdc = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_hi(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
#define CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd)          \
  /* extrbct the right chbnnel */                               \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_lo(sd2));        \
  sdb = vis_fpmerge(vis_rebd_lo(sd1), vis_rebd_lo(sd3));        \
  sdc = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_lo(sdb));        \
  dd  = vis_fpmerge(vis_rebd_hi(sdc), vis_rebd_lo(sdc))

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_41_A8D1X4(const mlib_s16 *src,
                                              mlib_s16       *dst,
                                              mlib_s32       dsize,
                                              mlib_s32       cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  if (cmbsk == 8) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  else if (cmbsk == 4) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  else if (cmbsk == 2) {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  else {
#prbgmb pipeloop(0)
    for (i = 0; i < dsize / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_41_A8D2X4(const mlib_s16 *src,
                                              mlib_s32       slb,
                                              mlib_s16       *dst,
                                              mlib_s32       dlb,
                                              mlib_s32       xsize,
                                              mlib_s32       ysize,
                                              mlib_s32       cmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  if (cmbsk == 8) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (cmbsk == 4) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else if (cmbsk == 2) {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  else {
    for (j = 0; j < ysize; j++) {
#prbgmb pipeloop(0)
      for (i = 0; i < xsize / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_41_D1(const mlib_s16 *src,
                                          mlib_s16       *dst,
                                          mlib_s32       dsize,
                                          mlib_s32       cmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dend, *dend2;                             /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 sd4, sd5, sd6, sd7;
  mlib_d64 sdb, sdb, sdc;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 off;                                       /* offset of src over dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dend = db + dsize - 1;
  dend2 = dend - 3;

  /* cblculbte the src's offset over dst */
  if (cmbsk == 8) {
    off = (soff / 8) * 2 - doff;
  }
  else if (cmbsk == 4) {
    off = ((soff + 2) / 8) * 2 - doff;
  }
  else if (cmbsk == 2) {
    off = ((soff + 4) / 8) * 2 - doff;
  }
  else {
    off = ((soff + 6) / 8) * 2 - doff;
  }

  if (((cmbsk == 8) && (soff == 0)) ||
      ((cmbsk == 4) && (soff == 6)) ||
      ((cmbsk == 2) && (soff == 4)) ||
      ((cmbsk == 1) && (soff == 2))) { /* extrbct left chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_S16_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_S16_41L(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else if (((cmbsk == 8) && (soff == 2)) ||
           ((cmbsk == 4) && (soff == 0)) ||
           ((cmbsk == 2) && (soff == 6)) ||
           ((cmbsk == 1) && (soff == 4))) { /* extrbct middle left chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_S16_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_S16_41ML(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else if (((cmbsk == 8) && (soff == 4)) ||
           ((cmbsk == 4) && (soff == 2)) ||
           ((cmbsk == 2) && (soff == 0)) ||
           ((cmbsk == 1) && (soff == 6))) { /* extrbct middle right chbnnel */

    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }
      else {
        /* lobd 48 bytes */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_S16_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_S16_41MR(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
  else {                                    /* extrbct right chbnnel */
    if (off == 0) {                         /* src bnd dst hbve sbme blignment */

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      /* lobd 16 bytes */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* extrbct, including some gbrbbge bt the stbrt point */
      CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd0);

      /* store 8 bytes result */
      vis_pst_16(dd0, dp++, embsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, embsk);
      }
    }
    else {
      vis_blignbddr((void *)0, off);

      /* generbte edge mbsk for the stbrt point */
      embsk = vis_edge16(db, dend);

      if (off < 0) {
        /* lobd 24 bytes */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* extrbct bnd store 8 bytes */
        CHANNELEXTRACT_S16_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, embsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
        n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixel column loop, embsk not needed */
#prbgmb pipeloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd4 = *sp++;
          sd5 = *sp++;
          sd6 = *sp++;
          sd7 = *sp++;
          CHANNELEXTRACT_S16_41R(sd4, sd5, sd6, sd7, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* end point hbndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_41(const mlib_s16 *src,
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
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_S16_41_D1(sb, db, xsize, cmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
