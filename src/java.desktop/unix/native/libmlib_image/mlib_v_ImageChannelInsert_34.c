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
 * FILENAME: mlib_v_ImbgeChbnnelInsert_34.c
 *
 * FUNCTIONS
 *      mlib_v_ImbgeChbnnelInsert_U8_34R_A8D1X8
 *      mlib_v_ImbgeChbnnelInsert_U8_34R_A8D2X8
 *      mlib_v_ImbgeChbnnelInsert_U8_34R_D1
 *      mlib_v_ImbgeChbnnelInsert_U8_34R
 *      mlib_v_ImbgeChbnnelInsert_S16_34R_A8D1X4
 *      mlib_v_ImbgeChbnnelInsert_S16_34R_A8D2X4
 *      mlib_v_ImbgeChbnnelInsert_S16_34R_D1
 *      mlib_v_ImbgeChbnnelInsert_S16_34R
 *      mlib_v_ImbgeChbnnelInsert_U8_34L_A8D1X8
 *      mlib_v_ImbgeChbnnelInsert_U8_34L_A8D2X8
 *      mlib_v_ImbgeChbnnelInsert_U8_34L_D1
 *      mlib_v_ImbgeChbnnelInsert_U8_34L
 *      mlib_v_ImbgeChbnnelInsert_S16_34L_A8D1X4
 *      mlib_v_ImbgeChbnnelInsert_S16_34L_A8D2X4
 *      mlib_v_ImbgeChbnnelInsert_S16_34L_D1
 *      mlib_v_ImbgeChbnnelInsert_S16_34L
 *
 * SYNOPSIS
 *
 * ARGUMENT
 *      src       pointer to source imbge dbtb
 *      dst       pointer to destinbtion imbge dbtb
 *          slb   source imbge line stride in bytes
 *          dlb   destinbtion imbge line stride in bytes
 *          dsize       imbge dbtb size in pixels
 *          xsize       imbge width in pixels
 *          ysize       imbge height in lines
 *          cmbsk chbnnel mbsk
 *
 * DESCRIPTION
 *          Insert b 3-chbnnel imbge into the right or left 3 chbnnels of
 *          b 4-chbnnel imbge low level functions.
 *
 *                BGR => ABGR   (34R), or       RGB => RGBA     (34L)
 *
 * NOTE
 *          These functions bre sepbrbted from mlib_v_ImbgeChbnnelInsert.c
 *          for loop unrolling bnd structure clbrity.
 */

#include <stdlib.h>
#include "vis_proto.h"
#include "mlib_imbge.h"

/***************************************************************/
#define INSERT_U8_34R                                                                         \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));                    \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));                    \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));                    \
  sdd = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));                    \
  sde = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_hi(sdc));                    \
  sdf = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdc));                    \
  sdg = vis_fpmerge(vis_rebd_hi(sdd), vis_rebd_lo(sde));                    \
  sdh = vis_fpmerge(vis_rebd_lo(sdd), vis_rebd_hi(sdf));                    \
  sdi = vis_fpmerge(vis_rebd_hi(sde), vis_rebd_lo(sdf));                    \
  sdj = vis_fpmerge(vis_rebd_hi(sdg), vis_rebd_hi(sdi));                    \
  sdk = vis_fpmerge(vis_rebd_lo(sdg), vis_rebd_lo(sdi));                    \
  sdl = vis_fpmerge(vis_rebd_hi(sdh), vis_rebd_hi(sdh));                    \
  sdm = vis_fpmerge(vis_rebd_lo(sdh), vis_rebd_lo(sdh));                    \
  dd0 = vis_fpmerge(vis_rebd_hi(sdl), vis_rebd_hi(sdj));                    \
  dd1 = vis_fpmerge(vis_rebd_lo(sdl), vis_rebd_lo(sdj));                    \
  dd2 = vis_fpmerge(vis_rebd_hi(sdm), vis_rebd_hi(sdk));                    \
  dd3 = vis_fpmerge(vis_rebd_lo(sdm), vis_rebd_lo(sdk));

/***************************************************************/
#define LOAD_INSERT_STORE_U8_34R_A8                                                         \
  sd0 = *sp++;                                  /* b0g0r0b1g1r1b2g2 */                  \
  sd1 = *sp++;                                  /* r2b3g3r3b4g4r4b5 */                  \
  sd2 = *sp++;                                  /* g5r5b6g6r6b7g7r7 */                  \
  INSERT_U8_34R                                                                                           \
  vis_pst_8(dd0, dp++, bmbsk);                                                                \
  vis_pst_8(dd1, dp++, bmbsk);                                                                \
  vis_pst_8(dd2, dp++, bmbsk);                                                                \
  vis_pst_8(dd3, dp++, bmbsk);

/***************************************************************/
#define LOAD_INSERT_U8_34R                                                                      \
  vis_blignbddr((void *)soff, 0);                                                             \
  s0 = s3;                                                                                                    \
  s1 = sp[1];                                                                                               \
  s2 = sp[2];                                                                                               \
  s3 = sp[3];                                                                                               \
  sd0 = vis_fbligndbtb(s0, s1);                                 \
  sd1 = vis_fbligndbtb(s1, s2);                                                               \
  sd2 = vis_fbligndbtb(s2, s3);                                                               \
  sp += 3;                                                                                                    \
  dd4 = dd3;                                                                  \
  INSERT_U8_34R

/***************************************************************/
/*
 * Both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned. And dsize is multiple of 8.
 */

void
mlib_v_ImbgeChbnnelInsert_U8_34R_A8D1X8(mlib_u8  *src,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsize)
{
  mlib_d64  *sp, *dp;
  mlib_d64  sd0, sd1, sd2;          /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdc, sdd; /* intermedibte vbribbles */
  mlib_d64  sde, sdf, sdg, sdh;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int       bmbsk = 0x77;
  int       i;

  sp = (mlib_d64 *)src;
  dp = (mlib_d64 *)dst;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    LOAD_INSERT_STORE_U8_34R_A8;
  }
}

/***************************************************************/
/*
 * Either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned. And slb bnd dlb bre multiple of 8.
 * The xsize is multiple of 8.
 */

void
mlib_v_ImbgeChbnnelInsert_U8_34R_A8D2X8(mlib_u8  *src,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                                mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_d64  *sp, *dp;             /* 8-byte bligned pointer for pixel */
  mlib_d64  *sl, *dl;             /* 8-byte bligned pointer for line */
  mlib_d64  sd0, sd1, sd2;      /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdc, sdd; /* intermedibte vbribbles */
  mlib_d64  sde, sdf, sdg, sdh;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int         bmbsk = 0x77;
  int       i, j;               /* indices for x, y */

  sp = sl = (mlib_d64 *)src;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 8-byte column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 8; i++) {
      LOAD_INSERT_STORE_U8_34R_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * either source or destinbtion dbtb bre not 8-byte bligned.
 */

void
mlib_v_ImbgeChbnnelInsert_U8_34R_D1(mlib_u8  *src,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsize)
{
  mlib_u8   *sb, *db;
  mlib_u8   *dend, *dend2;      /* end points in dst */
  mlib_d64  *dp;                  /* 8-byte bligned stbrt points in dst */
  mlib_d64  *sp;                  /* 8-byte bligned stbrt point in src */
  mlib_d64  s0, s1, s2, s3;     /* 8-byte source rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-byte source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                  /* the lbst dbtum of the lbst step */
  mlib_d64  sdb, sdb, sdc, sdd; /* intermedibte vbribbles */
  mlib_d64  sde, sdf, sdg, sdh;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int       soff;                 /* offset of bddress in src */
  int       doff;                 /* offset of bddress in dst */
  int       embsk;              /* edge mbsk */
  int         bmbsk;            /* chbnnel mbsk */
  int         i, n;

  sb = src;
  db = dst;

  /* prepbre the source bddress */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dend  = db + dsize * 4 - 1;
  dend2 = dend - 31;
  doff  = ((mlib_bddr) db & 7);

  /* set bbnd mbsk for vis_pst_8 to store the bytes needed */
  bmbsk = 0xff & (0x7777 >> doff) ;

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge8(db, dend);

  /* lobd 24 bytes, convert to 32 bytes */
  s3 = sp[0];                                   /* initibl vblue */
  LOAD_INSERT_U8_34R;

  if (doff == 0) {                              /* dst is 8-byte bligned */

    if (dsize >= 8 ) {
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      vis_pst_8(dd1, dp++, bmbsk);
      vis_pst_8(dd2, dp++, bmbsk);
      vis_pst_8(dd3, dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(dd3, dp++, embsk & bmbsk);
          }
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34R;
        vis_pst_8(dd0, dp++, bmbsk);
        vis_pst_8(dd1, dp++, bmbsk);
        vis_pst_8(dd2, dp++, bmbsk);
        vis_pst_8(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_U8_34R;
      embsk = vis_edge8(dp, dend);
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(dd3, dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
  else {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsize >= 8 ) {
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
            if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
              embsk = vis_edge8(dp, dend);
              vis_pst_8(vis_fbligndbtb(dd3, dd3), dp++, embsk & bmbsk);
            }
          }
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34R;
        vis_blignbddr((void *)0, -doff);
        vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_U8_34R;
      vis_blignbddr((void *)0, -doff);
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/

void
mlib_v_ImbgeChbnnelInsert_U8_34R(mlib_u8  *src,  mlib_s32 slb,
                                                 mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_u8   *sb, *db;
  mlib_u8   *sl, *dl;
  int         j;

  sb = sl = src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_U8_34R_D1(sb, db, xsize);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define INSERT_S16_34R                                                                              \
  vis_blignbddr((void *)0, 6);                                                                \
  dd0 = vis_fbligndbtb(sd0, sd0);                 /* b1b0g0r0 */                \
  vis_blignbddr((void *)0, 4);                                                                \
  dd1 = vis_fbligndbtb(sd0, sd1);                 /* r0b1gbr1 */                \
  vis_blignbddr((void *)0, 2);                                                                \
  dd2 = vis_fbligndbtb(sd1, sd2);                       /* r1b2g2r2 */          \
  dd3 = sd2;                                                          /* r2b3g3r3 */

/***************************************************************/
#define LOAD_INSERT_STORE_S16_34R_A8                                                      \
  sd0 = *sp++;                                          /* b0g0r0b1 */                      \
  sd1 = *sp++;                                          /* g1r1b2g2 */                      \
  sd2 = *sp++;                                          /* r2b3g3r3 */                      \
  INSERT_S16_34R                                                                                          \
  vis_pst_16(dd0, dp++, bmbsk);                                                               \
  vis_pst_16(dd1, dp++, bmbsk);                                                               \
  vis_pst_16(dd2, dp++, bmbsk);                                                               \
  vis_pst_16(dd3, dp++, bmbsk);

/***************************************************************/
#define LOAD_INSERT_S16_34R                                                                       \
  vis_blignbddr((void *)soff, 0);                                                             \
  s0 = s3;                                                                                                    \
  s1 = sp[1];                                                                                               \
  s2 = sp[2];                                                                                               \
  s3 = sp[3];                                                                                               \
  sd0 = vis_fbligndbtb(s0, s1);                                                               \
  sd1 = vis_fbligndbtb(s1, s2);                                                               \
  sd2 = vis_fbligndbtb(s2, s3);                                                               \
  sp += 3;                                                                                                    \
  dd4 = dd3;                                                                                                \
  INSERT_S16_34R

/***************************************************************/
/*
 * both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned.  dsize is multiple of 4.
 */

void
mlib_v_ImbgeChbnnelInsert_S16_34R_A8D1X4(mlib_s16 *src,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsize)
{
  mlib_d64  *sp, *dp;           /* 8-byte bligned pointer for pixel */
  mlib_d64  sd0, sd1, sd2;      /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x07;       /* chbnnel mbsk */
  int       i;

  sp = (mlib_d64 *)src;
  dp = (mlib_d64 *)dst;

  /* set GSR.offset for vis_fbligndbtb()  */
  /* vis_blignbddr((void *)0, 2); */            /* only for _old */

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_34R_A8;
  }
}

/***************************************************************/
/*
 * either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned.  xsize is multiple of 4.
 */

void
mlib_v_ImbgeChbnnelInsert_S16_34R_A8D2X4(mlib_s16 *src,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_d64  *sp, *dp;           /* 8-byte bligned pointer for pixel */
  mlib_d64  *sl, *dl;           /* 8-byte bligned pointer for line */
  mlib_d64  sd0, sd1, sd2;      /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x07;       /* chbnnel mbsk */
  int       i, j;               /* indices for x, y */

  sp = sl = (mlib_d64 *)src;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 4-pixel column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 4; i++) {
      LOAD_INSERT_STORE_S16_34R_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * either source or destinbtion dbtb bre not 8-byte bligned.
 */

void
mlib_v_ImbgeChbnnelInsert_S16_34R_D1(mlib_s16 *src,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsize)
{
  mlib_s16  *sb, *db;           /* pointer for pixel */
  mlib_s16  *dend, *dend2;      /* end points in dst */
  mlib_d64  *dp;                /* 8-byte bligned stbrt points in dst */
  mlib_d64  *sp;                /* 8-byte bligned stbrt point in src */
  mlib_d64  s0, s1, s2, s3;     /* 8-byte source rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-byte source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                /* the lbst dbtum of the lbst step */
  int soff;             /* offset of bddress in src */
  int doff;             /* offset of bddress in dst */
  int       embsk;              /* edge mbsk */
  int       bmbsk;              /* chbnnel mbsk */
  int       i, n;

  sb = src;
  db = dst;

  /* prepbre the source bddress */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dend  = db + dsize * 4 - 1;
  dend2 = dend - 15;
  doff  = ((mlib_bddr) db & 7);

  /* set chbnnel mbsk for vis_pst_16 to store the words needed */
  bmbsk = 0xff & (0x77 >> (doff / 2));

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge16(db, dend);

  /* lobd 24 byte, convert, store 32 bytes */
  s3 = sp[0];                                   /* initibl vblue */
  LOAD_INSERT_S16_34R;

  if (doff == 0) {                              /* dst is 8-byte bligned */

    if (dsize >= 4 ) {
      vis_pst_16(dd0, dp++, embsk & bmbsk);
      vis_pst_16(dd1, dp++, bmbsk);
      vis_pst_16(dd2, dp++, bmbsk);
      vis_pst_16(dd3, dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_16(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk & bmbsk);
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34R;
        vis_pst_16(dd0, dp++, bmbsk);
        vis_pst_16(dd1, dp++, bmbsk);
        vis_pst_16(dd2, dp++, bmbsk);
        vis_pst_16(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_S16_34R;
      embsk = vis_edge16(dp, dend);
      vis_pst_16(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk & bmbsk);
        }
      }
    }
  }
  else {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsize >= 4 ) {
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge16(dp, dend);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
          }
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34R;
        vis_blignbddr((void *)0, -doff);
        vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_S16_34R;
      vis_blignbddr((void *)0, -doff);
      embsk = vis_edge16(dp, dend);
      vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge16(dp, dend);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/

void
mlib_v_ImbgeChbnnelInsert_S16_34R(mlib_s16 *src,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_s16  *sb, *db;
  mlib_s16  *sl, *dl;
  int       j;

  sb = sl = src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_S16_34R_D1(sb, db, xsize);
    sb = sl = (mlib_s16 *)((mlib_u8 *)sl + slb);
    db = dl = (mlib_s16 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
#define INSERT_U8_34L                                                                                 \
  sdb = vis_fpmerge(vis_rebd_hi(sd0), vis_rebd_lo(sd1));                    \
  sdb = vis_fpmerge(vis_rebd_lo(sd0), vis_rebd_hi(sd2));                    \
  sdc = vis_fpmerge(vis_rebd_hi(sd1), vis_rebd_lo(sd2));                    \
  sdd = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdb));                    \
  sde = vis_fpmerge(vis_rebd_lo(sdb), vis_rebd_hi(sdc));                    \
  sdf = vis_fpmerge(vis_rebd_hi(sdb), vis_rebd_lo(sdc));                    \
  sdg = vis_fpmerge(vis_rebd_hi(sdd), vis_rebd_lo(sde));                    \
  sdh = vis_fpmerge(vis_rebd_lo(sdd), vis_rebd_hi(sdf));                    \
  sdi = vis_fpmerge(vis_rebd_hi(sde), vis_rebd_lo(sdf));                    \
  sdj = vis_fpmerge(vis_rebd_hi(sdg), vis_rebd_hi(sdi));                    \
  sdk = vis_fpmerge(vis_rebd_lo(sdg), vis_rebd_lo(sdi));                    \
  sdl = vis_fpmerge(vis_rebd_hi(sdh), vis_rebd_hi(sdh));                    \
  sdm = vis_fpmerge(vis_rebd_lo(sdh), vis_rebd_lo(sdh));                    \
  dd0 = vis_fpmerge(vis_rebd_hi(sdj), vis_rebd_hi(sdl));                    \
  dd1 = vis_fpmerge(vis_rebd_lo(sdj), vis_rebd_lo(sdl));                    \
  dd2 = vis_fpmerge(vis_rebd_hi(sdk), vis_rebd_hi(sdm));                    \
  dd3 = vis_fpmerge(vis_rebd_lo(sdk), vis_rebd_lo(sdm));

/***************************************************************/
#define LOAD_INSERT_STORE_U8_34L_A8                                                         \
  sd0 = *sp++;                                  /* b0g0r0b1g1r1b2g2 */                  \
  sd1 = *sp++;                                  /* r2b3g3r3b4g4r4b5 */                  \
  sd2 = *sp++;                                  /* g5r5b6g6r6b7g7r7 */                  \
  INSERT_U8_34L                                                                                                       \
  vis_pst_8(dd0, dp++, bmbsk);                                                                \
  vis_pst_8(dd1, dp++, bmbsk);                                                                \
  vis_pst_8(dd2, dp++, bmbsk);                                                                \
  vis_pst_8(dd3, dp++, bmbsk);

/***************************************************************/
#define LOAD_INSERT_U8_34L                                                                        \
  vis_blignbddr((void *)soff, 0);                                                             \
  s0 = s3;                                                                                                    \
  s1 = sp[1];                                                                                               \
  s2 = sp[2];                                                                                               \
  s3 = sp[3];                                                                                               \
  sd0 = vis_fbligndbtb(s0, s1);                                 \
  sd1 = vis_fbligndbtb(s1, s2);                                                               \
  sd2 = vis_fbligndbtb(s2, s3);                                                               \
  sp += 3;                                                                                                    \
  dd4 = dd3;                                                    \
  INSERT_U8_34L

/***************************************************************/
/*
 * Both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned. And dsize is multiple of 8.
 */
void
mlib_v_ImbgeChbnnelInsert_U8_34L_A8D1X8(mlib_u8  *src,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsize)
{
  mlib_d64  *sp, *dp;
  mlib_d64  sd0, sd1, sd2;          /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdc, sdd; /* intermedibte vbribbles */
  mlib_d64  sde, sdf, sdg, sdh;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int         bmbsk = 0xee;
  int         i;

  sp = (mlib_d64 *)src;
  dp = (mlib_d64 *)dst;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    LOAD_INSERT_STORE_U8_34L_A8;
  }
}

/***************************************************************/
/*
 * Either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned. And slb bnd dlb bre multiple of 8.
 * The xsize is multiple of 8.
 */
void
mlib_v_ImbgeChbnnelInsert_U8_34L_A8D2X8(mlib_u8  *src,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_d64  *sp, *dp;           /* 8-byte bligned pointer for pixel */
  mlib_d64  *sl, *dl;           /* 8-byte bligned pointer for line */
  mlib_d64  sd0, sd1, sd2;      /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdc, sdd; /* intermedibte vbribbles */
  mlib_d64  sde, sdf, sdg, sdh;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int         bmbsk = 0xee;
  int       i, j;               /* indices for x, y */

  sp = sl = (mlib_d64 *)src;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 8-byte column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 8; i++) {
      LOAD_INSERT_STORE_U8_34L_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * either source or destinbtion dbtb bre not 8-byte bligned.
 */
void
mlib_v_ImbgeChbnnelInsert_U8_34L_D1(mlib_u8  *src,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsize)
{
  mlib_u8   *sb, *db;
  mlib_u8   *dend, *dend2;      /* end points in dst */
  mlib_d64  *dp;                /* 8-byte bligned stbrt points in dst */
  mlib_d64  *sp;                /* 8-byte bligned stbrt point in src */
  mlib_d64  s0, s1, s2, s3;     /* 8-byte source rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-byte source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                /* the lbst dbtum of the lbst step */
  mlib_d64  sdb, sdb, sdc, sdd; /* intermedibte vbribbles */
  mlib_d64  sde, sdf, sdg, sdh;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int       soff;               /* offset of bddress in src */
  int       doff;               /* offset of bddress in dst */
  int       embsk;              /* edge mbsk */
  int         bmbsk;            /* chbnnel mbsk */
  int         i, n;

  sb = src;
  db = dst;

  /* prepbre the source bddress */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dend  = db + dsize * 4 - 1;
  dend2 = dend - 31;
  doff  = ((mlib_bddr) db & 7);

  /* set bbnd mbsk for vis_pst_8 to store the bytes needed */
  bmbsk = 0xff & (0xeeee >> doff) ;

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge8(db, dend);

  /* lobd 24 bytes, convert to 32 bytes */
  s3 = sp[0];                                   /* initibl vblue */
  LOAD_INSERT_U8_34L;

  if (doff == 0) {                              /* dst is 8-byte bligned */

    if (dsize >= 8 ) {
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      vis_pst_8(dd1, dp++, bmbsk);
      vis_pst_8(dd2, dp++, bmbsk);
      vis_pst_8(dd3, dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(dd3, dp++, embsk & bmbsk);
          }
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34L;
        vis_pst_8(dd0, dp++, bmbsk);
        vis_pst_8(dd1, dp++, bmbsk);
        vis_pst_8(dd2, dp++, bmbsk);
        vis_pst_8(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_U8_34L;
      embsk = vis_edge8(dp, dend);
      vis_pst_8(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(dd3, dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
  else {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsize >= 8 ) {
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
            if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
              embsk = vis_edge8(dp, dend);
              vis_pst_8(vis_fbligndbtb(dd3, dd3), dp++, embsk & bmbsk);
            }
          }
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34L;
        vis_blignbddr((void *)0, -doff);
        vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_U8_34L;
      vis_blignbddr((void *)0, -doff);
      embsk = vis_edge8(dp, dend);
      vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/
void
mlib_v_ImbgeChbnnelInsert_U8_34L(mlib_u8  *src,  mlib_s32 slb,
                                                         mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_u8   *sb, *db;
  mlib_u8   *sl, *dl;
  int         j;

  sb = sl = src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_U8_34L_D1(sb, db, xsize);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define INSERT_S16_34L                                                                              \
  dd0 = sd0;                                                            /* b0g0r0b1 */        \
  vis_blignbddr((void *)0, 6);                                                                \
  dd1 = vis_fbligndbtb(sd0, sd1);                       /* b1gbr1b2 */        \
  vis_blignbddr((void *)0, 4);                                                                \
  dd2 = vis_fbligndbtb(sd1, sd2);                         /* b2g2r2b3 */              \
  vis_blignbddr((void *)0, 2);                                                                \
  dd3 = vis_fbligndbtb(sd2, sd2);                         /* b3g3r3r2 */

/***************************************************************/
#define LOAD_INSERT_STORE_S16_34L_A8                                                      \
  sd0 = *sp++;                                          /* b0g0r0b1 */                          \
  sd1 = *sp++;                                          /* g1r1b2g2 */                      \
  sd2 = *sp++;                                          /* r2b3g3r3 */                      \
  INSERT_S16_34L                                                                                          \
  vis_pst_16(dd0, dp++, bmbsk);                                                               \
  vis_pst_16(dd1, dp++, bmbsk);                                                               \
  vis_pst_16(dd2, dp++, bmbsk);                                                               \
  vis_pst_16(dd3, dp++, bmbsk);

/***************************************************************/
#define LOAD_INSERT_S16_34L                                                                       \
  vis_blignbddr((void *)soff, 0);                                                             \
  s0 = s3;                                                                                                    \
  s1 = sp[1];                                                                                               \
  s2 = sp[2];                                                                                               \
  s3 = sp[3];                                                                                               \
  sd0 = vis_fbligndbtb(s0, s1);                                                               \
  sd1 = vis_fbligndbtb(s1, s2);                                                               \
  sd2 = vis_fbligndbtb(s2, s3);                                                               \
  sp += 3;                                                                                                    \
  dd4 = dd3;                                                                                                \
  INSERT_S16_34L

/***************************************************************/
/*
 * both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned.  dsize is multiple of 4.
 */

void
mlib_v_ImbgeChbnnelInsert_S16_34L_A8D1X4(mlib_s16 *src,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsize)
{
  mlib_d64  *sp, *dp;           /* 8-byte bligned pointer for pixel */
  mlib_d64  sd0, sd1, sd2;      /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x0e;       /* chbnnel mbsk */
  int       i;

  sp = (mlib_d64 *)src;
  dp = (mlib_d64 *)dst;

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_INSERT_STORE_S16_34L_A8;
  }
}

/***************************************************************/
/*
 * either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned.  xsize is multiple of 4.
 */

void
mlib_v_ImbgeChbnnelInsert_S16_34L_A8D2X4(mlib_s16 *src,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_d64  *sp, *dp;           /* 8-byte bligned pointer for pixel */
  mlib_d64  *sl, *dl;           /* 8-byte bligned pointer for line */
  mlib_d64  sd0, sd1, sd2;      /* source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x0e;       /* chbnnel mbsk */
  int       i, j;               /* indices for x, y */

  sp = sl = (mlib_d64 *)src;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 4-pixel column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 4; i++) {
      LOAD_INSERT_STORE_S16_34L_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * either source or destinbtion dbtb bre not 8-byte bligned.
 */

void
mlib_v_ImbgeChbnnelInsert_S16_34L_D1(mlib_s16 *src,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsize)
{
  mlib_s16  *sb, *db;           /* pointer for pixel */
  mlib_s16  *dend, *dend2;      /* end points in dst */
  mlib_d64  *dp;                /* 8-byte bligned stbrt points in dst */
  mlib_d64  *sp;                /* 8-byte bligned stbrt point in src */
  mlib_d64  s0, s1, s2, s3;     /* 8-byte source rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-byte source dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                /* the lbst dbtum of the lbst step */
  int soff;             /* offset of bddress in src */
  int doff;             /* offset of bddress in dst */
  int       embsk;              /* edge mbsk */
  int       bmbsk;              /* chbnnel mbsk */
  int       i, n;

  sb = src;
  db = dst;

  /* prepbre the source bddress */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dend  = db + dsize * 4 - 1;
  dend2 = dend - 15;
  doff  = ((mlib_bddr) db & 7);

  /* set chbnnel mbsk for vis_pst_16 to store the words needed */
  bmbsk = 0xff & (0xee >> (doff / 2));

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge16(db, dend);

  /* lobd 24 byte, convert, store 32 bytes */
  s3 = sp[0];                                   /* initibl vblue */
  LOAD_INSERT_S16_34L;

  if (doff == 0) {                              /* dst is 8-byte bligned */

    if (dsize >= 4 ) {
      vis_pst_16(dd0, dp++, embsk & bmbsk);
      vis_pst_16(dd1, dp++, bmbsk);
      vis_pst_16(dd2, dp++, bmbsk);
      vis_pst_16(dd3, dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_16(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk & bmbsk);
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34L;
        vis_pst_16(dd0, dp++, bmbsk);
        vis_pst_16(dd1, dp++, bmbsk);
        vis_pst_16(dd2, dp++, bmbsk);
        vis_pst_16(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_S16_34L;
      embsk = vis_edge16(dp, dend);
      vis_pst_16(dd0, dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk & bmbsk);
        }
      }
    }
  }
  else {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsize >= 4 ) {
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    else {                                      /* for very smbll size */
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge16(dp, dend);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
          }
        }
      }
    }

    /* no edge hbndling is needed in the loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dend2)  {
      n = ((mlib_u8 *)dend2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34L;
        vis_blignbddr((void *)0, -doff);
        vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
      LOAD_INSERT_S16_34L;
      vis_blignbddr((void *)0, -doff);
      embsk = vis_edge16(dp, dend);
      vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, embsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend)  {
            embsk = vis_edge16(dp, dend);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, embsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/

void
mlib_v_ImbgeChbnnelInsert_S16_34L(mlib_s16 *src,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsize, mlib_s32 ysize)
{
  mlib_s16  *sb, *db;
  mlib_s16  *sl, *dl;
  int       j;

  sb = sl = src;
  db = dl = dst;

#prbgmb pipeloop(0)
  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelInsert_S16_34L_D1(sb, db, xsize);
    sb = sl = (mlib_s16 *)((mlib_u8 *)sl + slb);
    db = dl = (mlib_s16 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
