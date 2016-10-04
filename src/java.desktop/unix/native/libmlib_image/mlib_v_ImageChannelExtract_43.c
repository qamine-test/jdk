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
 * FILENAME: mlib_v_ImbgeChbnnelExtrbct_43.c
 *
 * FUNCTIONS
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43R_A8D1X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43R_A8D2X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43R_D1
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43R
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43R_A8D1X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43R_A8D2X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43R_D1
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43R
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43L_A8D1X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43L_A8D2X8
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43L_D1
 *      mlib_v_ImbgeChbnnelExtrbct_U8_43L
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43L_A8D1X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43L_A8D2X4
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43L_D1
 *      mlib_v_ImbgeChbnnelExtrbct_S16_43L
 *
 * SYNOPSIS
 *
 * ARGUMENT
 *      src    pointer to source imbge dbtb
 *      dst    pointer to destinbtion imbge dbtb
 *      slb    source imbge line stride in bytes
 *      dlb    destinbtion imbge line stride in bytes
 *      dsize imbge dbtb size in pixels
 *      xsize  imbge width in pixels
 *      ysize  imbge height in lines
 *      cmbsk chbnnel mbsk
 *
 * DESCRIPTION
 *      extrbct the right or left 3 chbnnels of b 4-chbnnel imbge to
 *      b 3-chbnnel imbge -- VIS version low level functions.
 *
 *      ABGR => BGR   (43R), or  RGBA => RGB  (43L)
 *
 * NOTE
 *      These functions bre sepbrbted from mlib_v_ImbgeChbnnelExtrbct.c
 *      for loop unrolling bnd structure clbrity.
 */

#include "vis_proto.h"
#include "mlib_imbge.h"
#include "mlib_v_ImbgeChbnnelExtrbct.h"

/***************************************************************/
#define EXTRACT_U8_43R_old          /* shift right */           \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* r7-------------- */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* g7r7------------ */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* b7g7r7---------- */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* r6b7g7r7-------- */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* g6r6b7g7r7------ */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* b6g6r6b7g7r7---- */     \
                                                                \
  dd2 = vis_fbligndbtb(sd2, dd2);    /* r5b6g6r6b7g7r7-- */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd2 = vis_fbligndbtb(sd2, dd2);    /* g5r5b6g6r6b7g7r7 */     \
                                                                \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* b5-------------- */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* r4b5------------ */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* g4r4b5---------- */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* b4g4r4b5-------- */     \
                                                                \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* r3b4g4r4b5------ */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* g3r3b4g4r4b5---- */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* b3g3r3b4g4r4b5-- */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* r2b3g3r3b4g4r4b5 */     \
                                                                \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd0 = vis_fbligndbtb(sd1, dd0);    /* g2-------------- */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd0 = vis_fbligndbtb(sd1, dd0);    /* b2g2------------ */     \
                                                                \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* r1b2g2---------- */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* g1r1b2g2-------- */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* b1g1r1b2g2------ */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* r0b1g1r1b2g2---- */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* g0r0b1g1r1b2g2-- */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(sd0, dd0);           /* b0g0r0b1g1r1b2g2 */

/***************************************************************/
#define EXTRACT_U8_43R              /* shift right */           \
  vis_blignbddr((void *)0, 5);                                  \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* b7g7r7---------- */     \
  sdb = vis_freg_pbir(vis_rebd_hi(sd3), vis_rebd_hi(sd3));      \
  dd2 = vis_fbligndbtb(sdb, dd2);    /* b6g6r6b7g7r7---- */     \
                                                                \
  vis_blignbddr((void *)0, 6);                                  \
  dd2 = vis_fbligndbtb(sd2, dd2);    /* g5r5b6g6r6b7g7r7 */     \
                                                                \
  vis_blignbddr((void *)0, 5);                                  \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* b5g5r5---------- */     \
  sdb = vis_freg_pbir(vis_rebd_hi(sd2), vis_rebd_hi(sd2));      \
  dd1 = vis_fbligndbtb(sdb, dd1);    /* b4g4r4b5g5r5---- */     \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* b3g3r3b4g4r4b5g5 */     \
  sdb = vis_freg_pbir(vis_rebd_hi(sd1), vis_rebd_hi(sd1));      \
  vis_blignbddr((void *)0, 7);                                  \
  dd1 = vis_fbligndbtb(sdb, dd1);    /* r2b3g3r3b4g4r4b5 */     \
                                                                \
  vis_blignbddr((void *)0, 5);                                  \
  dd0 = vis_fbligndbtb(sdb, dd0);    /* b2g2r2---------- */     \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* b1g1r1b2g2r2---- */     \
  sdb = vis_freg_pbir(vis_rebd_hi(sd0), vis_rebd_hi(sd0));      \
  dd0 = vis_fbligndbtb(sdb, dd0);           /* b0g0r0b1g1r1b2g2 */

/***************************************************************/
#define LOAD_EXTRACT_U8_43R_STORE                               \
  sd0 = *sp++;          /* --b0g0r0--b1g1r1 */                  \
  sd1 = *sp++;          /* --b2g2r2--b3g3r3 */                  \
  sd2 = *sp++;          /* --b4g4r4--b5g5r5 */                  \
  sd3 = *sp++;          /* --b6g6r6--b7g7r7 */                  \
  EXTRACT_U8_43R;                                               \
  *dp++ = dd0;          /* b0g0r0b1g1r1b2g2 */                  \
  *dp++ = dd1;          /* r2b3g3r3b4g4r4b5 */                  \
  *dp++ = dd2;                              /* g5r5b6g6r6b7g7r7 */

/***************************************************************/
#define LOAD_EXTRACT_U8_43R                                     \
  vis_blignbddr((void *)soff, 0);                               \
  s0 = s4;                                                      \
  s1 = sp[1];                                                   \
  s2 = sp[2];                                                   \
  s3 = sp[3];                                                   \
  s4 = sp[4];                                                   \
  sd0 = vis_fbligndbtb(s0, s1);                                 \
  sd1 = vis_fbligndbtb(s1, s2);                                 \
  sd2 = vis_fbligndbtb(s2, s3);                                 \
  sd3 = vis_fbligndbtb(s3, s4);                                 \
  sp += 4;                                                      \
  dd2old = dd2;                                                 \
  EXTRACT_U8_43R

/***************************************************************/
/*
 * Both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned. And dsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_43R_A8D1X8(const mlib_u8 *src,
                                              mlib_u8       *dst,
                                              mlib_s32      dsize)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 sdb;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 7); *//* only for _old */

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    LOAD_EXTRACT_U8_43R_STORE;
  }
}

/***************************************************************/
/*
 * Either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned. And slb bnd dlb bre multiple of 8.
 * The xsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_43R_A8D2X8(const mlib_u8 *src,
                                              mlib_s32      slb,
                                              mlib_u8       *dst,
                                              mlib_s32      dlb,
                                              mlib_s32      xsize,
                                              mlib_s32      ysize)
{
  mlib_d64 *sp, *dp;                                  /* 8-byte bligned pointer for pixel */
  mlib_d64 *sl, *dl;                                  /* 8-byte bligned pointer for line */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 sdb;
  mlib_s32 i, j;                                      /* indices for x, y */

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 7); *//* only for _old */

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 8-byte column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 8; i++) {
      LOAD_EXTRACT_U8_43R_STORE;
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
/*
 * Either source or destinbtion dbtb bre not 8-byte bligned.
 * And dsize is is in pixels.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_43R_D1(const mlib_u8 *src,
                                          mlib_u8       *dst,
                                          mlib_s32      dsize)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend, *dend2;                              /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 s0, s1, s2, s3, s4;                        /* 8-byte source row dbtb */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 dd2old;                                    /* the lbst dbtum of the lbst step */
  mlib_d64 sdb;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  dend = db + dsize * 3 - 1;
  dend2 = dend - 23;
  doff = 8 - ((mlib_bddr) db & 7);

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge8(db, dend);

  /* lobd 32 byte, convert, store 24 bytes */
  s4 = sp[0];                               /* initibl vblue */
  LOAD_EXTRACT_U8_43R;

  if (dsize >= 8) {
    if (doff == 8) {
      vis_pst_8(dd0, dp++, embsk);
      *dp++ = dd1;
      *dp++ = dd2;
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      *dp++ = vis_fbligndbtb(dd0, dd1);
      *dp++ = vis_fbligndbtb(dd1, dd2);
    }
  }
  else {                                    /* for very smbll size */
    if (doff == 8) {
      vis_pst_8(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend) {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(vis_fbligndbtb(dd2, dd2), dp++, embsk);
          }
        }
      }
    }
  }

  /* no edge hbndling is needed in the loop */
  if (doff == 8) {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_U8_43R;
        *dp++ = dd0;
        *dp++ = dd1;
        *dp++ = dd2;
      }
    }
  }
  else {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_U8_43R;
        vis_blignbddr((void *)doff, 0);
        *dp++ = vis_fbligndbtb(dd2old, dd0);
        *dp++ = vis_fbligndbtb(dd0, dd1);
        *dp++ = vis_fbligndbtb(dd1, dd2);
      }
    }
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {
    LOAD_EXTRACT_U8_43R;
    embsk = vis_edge8(dp, dend);
    if (doff == 8) {
      vis_pst_8(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_8(vis_fbligndbtb(dd2old, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk);
        }
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_43R(const mlib_u8 *src,
                                       mlib_s32      slb,
                                       mlib_u8       *dst,
                                       mlib_s32      dlb,
                                       mlib_s32      xsize,
                                       mlib_s32      ysize)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_U8_43R_D1(sb, db, xsize);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define EXTRACT_S16_43R_old      /* shift right */              \
                                                                \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* r3------ */             \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* g3r3---- */             \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* b3g3r3-- */             \
                                                                \
  dd2 = vis_fbligndbtb(sd2, dd2);    /* r2b3g3r3 */             \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* g2------ */             \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* b2g2---- */             \
                                                                \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* r1b2g2-- */             \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* g1r1b2g2 */             \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd0 = vis_fbligndbtb(sd1, dd0);    /* b1------ */             \
                                                                \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* r0b1---- */             \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(sd0, dd0);    /* g0r0b1-- */             \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(sd0, dd0);           /* b0g0r0b1 */

/***************************************************************/
#define EXTRACT_S16_43R        /* shift right */                \
                                                                \
  vis_blignbddr((void *)0, 2);                                  \
  dd2 = vis_fbligndbtb(sd3, dd2);    /* b3g3r3-- */             \
                                                                \
  vis_blignbddr((void *)0, 6);                                  \
  dd2 = vis_fbligndbtb(sd2, dd2);    /* r2b3g3r3 */             \
  vis_blignbddr((void *)0, 2);                                  \
  dd1 = vis_fbligndbtb(sd2, dd1);    /* b2g2r2-- */             \
                                                                \
  vis_blignbddr((void *)0, 4);                                  \
  dd1 = vis_fbligndbtb(sd1, dd1);    /* g1r1b2g2 */             \
  vis_blignbddr((void *)0, 2);                                  \
  dd0 = vis_fbligndbtb(sd1, dd0);    /* b1g1r1-- */             \
  dd0 = vis_fbligndbtb(sd0, dd0);           /* b0g0r0b1 */

/***************************************************************/
#define LOAD_EXTRACT_S16_43R_STORE                              \
                                                                \
  sd0 = *sp++;          /* --b0g0r0 */                          \
  sd1 = *sp++;          /* --b1g1r1 */                          \
  sd2 = *sp++;          /* --b2g2r2 */                          \
  sd3 = *sp++;          /* --b3g3r3 */                          \
                                                                \
  EXTRACT_S16_43R;                                              \
                                                                \
  *dp++ = dd0;          /* b0g0r0b1 */                          \
  *dp++ = dd1;          /* g1r1b2g2 */                          \
  *dp++ = dd2;                              /* r2b3g3r3 */

/***************************************************************/
#define LOAD_EXTRACT_S16_43R                                    \
                                                                \
  vis_blignbddr((void *)soff, 0);                               \
  s0 = s4;                                                      \
  s1 = sp[1];                                                   \
  s2 = sp[2];                                                   \
  s3 = sp[3];                                                   \
  s4 = sp[4];                                                   \
  sd0 = vis_fbligndbtb(s0, s1);                                 \
  sd1 = vis_fbligndbtb(s1, s2);                                 \
  sd2 = vis_fbligndbtb(s2, s3);                                 \
  sd3 = vis_fbligndbtb(s3, s4);                                 \
  sp += 4;                                                      \
  dd2old = dd2;                                                 \
  EXTRACT_S16_43R

/***************************************************************/
/*
 * Both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned. And size is in 4-pixels.
 */

void mlib_v_ImbgeChbnnelExtrbct_S16_43R_A8D1X4(const mlib_s16 *src,
                                               mlib_s16       *dst,
                                               mlib_s32       dsize)
{
  mlib_d64 *sp, *dp;                                  /* 8-byte bligned pointer for pixel */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 6); *//* only for _old */

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_EXTRACT_S16_43R_STORE;
  }
}

/***************************************************************/
/*
 * Either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned. The xsize is multiple of 8.
 * slb bnd dlb bre multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_S16_43R_A8D2X4(const mlib_s16 *src,
                                               mlib_s32       slb,
                                               mlib_s16       *dst,
                                               mlib_s32       dlb,
                                               mlib_s32       xsize,
                                               mlib_s32       ysize)
{
  mlib_d64 *sp, *dp;                                  /* 8-byte bligned pointer for pixel */
  mlib_d64 *sl, *dl;                                  /* 8-byte bligned pointer for line */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_s32 i, j;                                      /* indices for x, y */

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 6); *//* only for _old */

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 4-pixel column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 4; i++) {
      LOAD_EXTRACT_S16_43R_STORE;
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
/*
 * Either source or destinbtion dbtb bre not 8-byte bligned.
 * And dsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_S16_43R_D1(const mlib_s16 *src,
                                           mlib_s16       *dst,
                                           mlib_s32       dsize)
{
  mlib_s16 *sb, *db;                                  /* pointer for pixel */
  mlib_s16 *dend, *dend2;                             /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 s0, s1, s2, s3, s4;                        /* 8-byte source row dbtb */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 dd2old;                                    /* the lbst dbtum of the lbst step */
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  dend = db + dsize * 3 - 1;
  dend2 = dend - 11;
  doff = 8 - ((mlib_bddr) db & 7);

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge16(db, dend);

  /* lobd 32 byte, convert, store 24 bytes */
  s4 = sp[0];                               /* initibl vblue */
  LOAD_EXTRACT_S16_43R;

  if (dsize >= 4) {
    if (doff == 8) {
      vis_pst_16(dd0, dp++, embsk);
      *dp++ = dd1;
      *dp++ = dd2;
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      *dp++ = vis_fbligndbtb(dd0, dd1);
      *dp++ = vis_fbligndbtb(dd1, dd2);
    }
  }
  else {                                    /* for very smbll size */
    if (doff == 8) {
      vis_pst_16(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk);
        }
      }
    }
  }

  /* no edge hbndling is needed in the loop */
  if (doff == 8) {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_S16_43R;
        *dp++ = dd0;
        *dp++ = dd1;
        *dp++ = dd2;
      }
    }
  }
  else {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_S16_43R;
        vis_blignbddr((void *)doff, 0);
        *dp++ = vis_fbligndbtb(dd2old, dd0);
        *dp++ = vis_fbligndbtb(dd0, dd1);
        *dp++ = vis_fbligndbtb(dd1, dd2);
      }
    }
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {
    LOAD_EXTRACT_S16_43R;
    embsk = vis_edge16(dp, dend);
    if (doff == 8) {
      vis_pst_16(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_16(vis_fbligndbtb(dd2old, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk);
        }
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_43R(const mlib_s16 *src,
                                        mlib_s32       slb,
                                        mlib_s16       *dst,
                                        mlib_s32       dlb,
                                        mlib_s32       xsize,
                                        mlib_s32       ysize)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_S16_43R_D1(sb, db, xsize);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
#define EXTRACT_U8_43L_old      /* shift left */                \
                                                                \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* --------------r0 */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* ------------r0g0 */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* ----------r0g0b0 */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* --------r0g0b0r1 */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* ------r0g0b0r1g1 */     \
  sd0 = vis_fbligndbtb(sd0, sd0);                               \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* ----r0g0b0r1g1b1 */     \
                                                                \
  dd0 = vis_fbligndbtb(dd0, sd1);    /* --r0g0b0r1g1b1r2 */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd0 = vis_fbligndbtb(dd0, sd1);    /* r0g0b0r1g1b1r2g2 */     \
                                                                \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(dd1, sd1);    /* --------------b2 */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(dd1, sd1);    /* ------------b2r3 */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(dd1, sd1);    /* ----------b2r3g3 */     \
  sd1 = vis_fbligndbtb(sd1, sd1);                               \
  dd1 = vis_fbligndbtb(dd1, sd1);    /* --------b2r3g3b3 */     \
                                                                \
  dd1 = vis_fbligndbtb(dd1, sd2);    /* ------b2r3g3b3r4 */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(dd1, sd2);    /* ----b2r3g3b3r4g4 */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(dd1, sd2);    /* --b2r3g3b3r4g4b4 */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd1 = vis_fbligndbtb(dd1, sd2);    /* b2r3g3b3r4g4b4r5 */     \
                                                                \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd2 = vis_fbligndbtb(dd2, sd2);    /* --------------g5 */     \
  sd2 = vis_fbligndbtb(sd2, sd2);                               \
  dd2 = vis_fbligndbtb(dd2, sd2);    /* ------------g5b5 */     \
                                                                \
  dd2 = vis_fbligndbtb(dd2, sd3);    /* ----------g5b5r6 */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(dd2, sd3);    /* --------g5b5r6g6 */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(dd2, sd3);    /* ------g5b5r6g6b6 */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(dd2, sd3);    /* ----g5b5r6g6b6r7 */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(dd2, sd3);    /* --g5b5r6g6b6r7g7 */     \
  sd3 = vis_fbligndbtb(sd3, sd3);                               \
  dd2 = vis_fbligndbtb(dd2, sd3);           /* g5b5r6g6b6r7g7b7 */

/***************************************************************/
#define EXTRACT_U8_43L        /* shift left */                  \
                                                                \
  vis_blignbddr((void *)0, 3);                                  \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* ----------r0g0b0 */     \
  sdb = vis_freg_pbir(vis_rebd_lo(sd0), vis_rebd_hi(sd0));      \
  dd0 = vis_fbligndbtb(dd0, sdb);    /* ----r0g0b0r1g1b1 */     \
                                                                \
  vis_blignbddr((void *)0, 2);                                  \
  dd0 = vis_fbligndbtb(dd0, sd1);    /* r0g0b0r1g1b1r2g2 */     \
                                                                \
  vis_blignbddr((void *)0, 3);                                  \
  dd1 = vis_fbligndbtb(dd1, sd1);    /* ----------r2g2b2 */     \
  sdb = vis_freg_pbir(vis_rebd_lo(sd1), vis_rebd_hi(sd1));      \
  dd1 = vis_fbligndbtb(dd1, sdb);    /* ----r2g2b2r3g3b3 */     \
  dd1 = vis_fbligndbtb(dd1, sd2);    /* g2b2r3g3b3r4g4b4 */     \
                                                                \
  sdb = vis_freg_pbir(vis_rebd_lo(sd2), vis_rebd_hi(sd2));      \
  vis_blignbddr((void *)0, 1);                                  \
  dd1 = vis_fbligndbtb(dd1, sdb);    /* b2r3g3b3r4g4b4r5 */     \
                                                                \
  vis_blignbddr((void *)0, 3);                                  \
  dd2 = vis_fbligndbtb(dd2, sdb);    /* ----------r5g5b5 */     \
                                                                \
  dd2 = vis_fbligndbtb(dd2, sd3);    /* ----r5g5b5r6g6b6 */     \
  sdb = vis_freg_pbir(vis_rebd_lo(sd3), vis_rebd_hi(sd3));      \
  dd2 = vis_fbligndbtb(dd2, sdb);           /* g5b5r6g6b6r7g7b7 */

/***************************************************************/
#define LOAD_EXTRACT_U8_43L_STORE                               \
                                                                \
  sd0 = *sp++;          /* r0g0b0--r1g1b1-- */                  \
  sd1 = *sp++;          /* r2g2b2--r3g3b3-- */                  \
  sd2 = *sp++;          /* r4g4b4--r5g5b5-- */                  \
  sd3 = *sp++;          /* r6g6b6--r7g7b7-- */                  \
                                                                \
  EXTRACT_U8_43L;                                               \
                                                                \
  *dp++ = dd0;          /* r0g0b0r1g1b1r2g2 */                  \
  *dp++ = dd1;          /* b2r3g3b3r4g4b4r5 */                  \
  *dp++ = dd2;                              /* g5b5r6g6b6r7g7b7 */

/***************************************************************/
#define LOAD_EXTRACT_U8_43L                                             \
                                                                        \
  vis_blignbddr((void *)soff, 0);                                       \
  s0 = s4;                                                              \
  s1 = sp[1];                                                           \
  s2 = sp[2];                                                           \
  s3 = sp[3];                                                           \
  s4 = sp[4];                                                           \
  sd0 = vis_fbligndbtb(s0, s1);  /* the intermedibte is ABGR bligned */ \
  sd1 = vis_fbligndbtb(s1, s2);                                         \
  sd2 = vis_fbligndbtb(s2, s3);                                         \
  sd3 = vis_fbligndbtb(s3, s4);                                         \
  sp += 4;                                                              \
                                                                        \
/*  vis_blignbddr((void *)0, 1); */    /* for _old only */              \
  dd2old = dd2;                                                         \
  EXTRACT_U8_43L

/***************************************************************/
/*
 * Both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned. And dsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_43L_A8D1X8(const mlib_u8 *src,
                                              mlib_u8       *dst,
                                              mlib_s32      dsize)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 sdb;
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 1); *//* for _old only */

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 8; i++) {
    LOAD_EXTRACT_U8_43L_STORE;
  }
}

/***************************************************************/
/*
 * Either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned. And slb bnd dlb bre multiple of 8.
 * The xsize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_43L_A8D2X8(const mlib_u8 *src,
                                              mlib_s32      slb,
                                              mlib_u8       *dst,
                                              mlib_s32      dlb,
                                              mlib_s32      xsize,
                                              mlib_s32      ysize)
{
  mlib_d64 *sp, *dp;                                  /* 8-byte bligned pointer for pixel */
  mlib_d64 *sl, *dl;                                  /* 8-byte bligned pointer for line */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 sdb;
  mlib_s32 i, j;                                      /* indices for x, y */

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 1); *//* for _old only */

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 8-byte column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 8; i++) {
      LOAD_EXTRACT_U8_43L_STORE;
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
/*
 * Either source or destinbtion dbtb bre not 8-byte bligned.
 * And ssize is multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_U8_43L_D1(const mlib_u8 *src,
                                          mlib_u8       *dst,
                                          mlib_s32      dsize)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dend, *dend2;                              /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 s0, s1, s2, s3, s4;                        /* 8-byte source row dbtb */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 dd2old;                                    /* the lbst dbtum of the lbst step */
  mlib_d64 sdb;
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  dend = db + dsize * 3 - 1;
  dend2 = dend - 23;
  doff = 8 - ((mlib_bddr) db & 7);

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge8(db, dend);

  /* lobd 32 byte, convert, store 24 bytes */
  s4 = sp[0];                               /* initibl vblue */
  LOAD_EXTRACT_U8_43L;

  if (dsize >= 8) {
    if (doff == 8) {
      vis_pst_8(dd0, dp++, embsk);
      *dp++ = dd1;
      *dp++ = dd2;
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      *dp++ = vis_fbligndbtb(dd0, dd1);
      *dp++ = vis_fbligndbtb(dd1, dd2);
    }
  }
  else {                                    /* for very smbll size */
    if (doff == 8) {
      vis_pst_8(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dend) {
            embsk = vis_edge8(dp, dend);
            vis_pst_8(vis_fbligndbtb(dd2, dd2), dp++, embsk);
          }
        }
      }
    }
  }

  /* no edge hbndling is needed in the loop */
  if (doff == 8) {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_U8_43L;
        *dp++ = dd0;
        *dp++ = dd1;
        *dp++ = dd2;
      }
    }
  }
  else {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_U8_43L;
        vis_blignbddr((void *)doff, 0);
        *dp++ = vis_fbligndbtb(dd2old, dd0);
        *dp++ = vis_fbligndbtb(dd0, dd1);
        *dp++ = vis_fbligndbtb(dd1, dd2);
      }
    }
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {
    LOAD_EXTRACT_U8_43L;
    embsk = vis_edge8(dp, dend);
    if (doff == 8) {
      vis_pst_8(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_8(vis_fbligndbtb(dd2old, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge8(dp, dend);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge8(dp, dend);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, embsk);
        }
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_U8_43L(const mlib_u8 *src,
                                       mlib_s32      slb,
                                       mlib_u8       *dst,
                                       mlib_s32      dlb,
                                       mlib_s32      xsize,
                                       mlib_s32      ysize)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_U8_43L_D1(sb, db, xsize);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#define EXTRACT_S16_43L              /* shift left */           \
  vis_blignbddr((void *)0, 6);                                  \
  dd0 = vis_fbligndbtb(dd0, sd0);    /* --r0g0b0 */             \
  vis_blignbddr((void *)0, 2);                                  \
  dd0 = vis_fbligndbtb(dd0, sd1);    /* r0g0b0r1 */             \
                                                                \
  vis_blignbddr((void *)0, 6);                                  \
  dd1 = vis_fbligndbtb(dd1, sd1);    /* --r1g1b1 */             \
  vis_blignbddr((void *)0, 4);                                  \
  dd1 = vis_fbligndbtb(dd1, sd2);    /* g1b1r2g2 */             \
                                                                \
  vis_blignbddr((void *)0, 6);                                  \
  dd2 = vis_fbligndbtb(dd2, sd2);    /* --r2g2b2 */             \
  dd2 = vis_fbligndbtb(dd2, sd3);           /* b2r3g3b3 */

/***************************************************************/
#define LOAD_EXTRACT_S16_43L_STORE                              \
                                                                \
  sd0 = *sp++;          /* r0g0b0-- */                          \
  sd1 = *sp++;          /* r1g1b1-- */                          \
  sd2 = *sp++;          /* r2g2b2-- */                          \
  sd3 = *sp++;          /* r3g3b3-- */                          \
                                                                \
  EXTRACT_S16_43L;                                              \
                                                                \
  *dp++ = dd0;          /* r0g0b0r1 */                          \
  *dp++ = dd1;          /* g1b1r2g2 */                          \
  *dp++ = dd2;                              /* b2r3g3b3 */

/***************************************************************/
#define LOAD_EXTRACT_S16_43L                                    \
                                                                \
  vis_blignbddr((void *)soff, 0);                               \
  s0 = s4;                                                      \
  s1 = sp[1];                                                   \
  s2 = sp[2];                                                   \
  s3 = sp[3];                                                   \
  s4 = sp[4];                                                   \
  sd0 = vis_fbligndbtb(s0, s1);                                 \
  sd1 = vis_fbligndbtb(s1, s2);                                 \
  sd2 = vis_fbligndbtb(s2, s3);                                 \
  sd3 = vis_fbligndbtb(s3, s4);                                 \
  sp += 4;                                                      \
  dd2old = dd2;                                                 \
  EXTRACT_S16_43L

/***************************************************************/
/*
 * Both source bnd destinbtion imbge dbtb bre 1-d vectors bnd
 * 8-byte bligned. And dsize is multiple of 4.
 */

void mlib_v_ImbgeChbnnelExtrbct_S16_43L_A8D1X4(const mlib_s16 *src,
                                               mlib_s16       *dst,
                                               mlib_s32       dsize)
{
  mlib_d64 *sp, *dp;                                  /* 8-byte bligned pointer for pixel */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_s32 i;

  sp = (mlib_d64 *) src;
  dp = (mlib_d64 *) dst;

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 2); *//* only for _old */

#prbgmb pipeloop(0)
  for (i = 0; i < dsize / 4; i++) {
    LOAD_EXTRACT_S16_43L_STORE;
  }
}

/***************************************************************/
/*
 * Either source or destinbtion imbge dbtb bre not 1-d vectors, but
 * they bre 8-byte bligned. The xsize is multiple of 4.
 * And slb bnd dlb bre multiple of 8.
 */

void mlib_v_ImbgeChbnnelExtrbct_S16_43L_A8D2X4(const mlib_s16 *src,
                                               mlib_s32       slb,
                                               mlib_s16       *dst,
                                               mlib_s32       dlb,
                                               mlib_s32       xsize,
                                               mlib_s32       ysize)
{
  mlib_d64 *sp, *dp;                                  /* 8-byte bligned pointer for pixel */
  mlib_d64 *sl, *dl;                                  /* 8-byte bligned pointer for line */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_s32 i, j;                                      /* indices for x, y */

  /* set GSR.offset for vis_fbligndbtb()  */
/* vis_blignbddr((void *)0, 2); *//* only for _old */

  sp = sl = (mlib_d64 *) src;
  dp = dl = (mlib_d64 *) dst;

  /* row loop */
  for (j = 0; j < ysize; j++) {
    /* 4-pixel column loop */
#prbgmb pipeloop(0)
    for (i = 0; i < xsize / 4; i++) {
      LOAD_EXTRACT_S16_43L_STORE;
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
/*
 * Either source or destinbtion dbtb bre not 8-byte bligned.
 * And size is is in pixels.
 */

void mlib_v_ImbgeChbnnelExtrbct_S16_43L_D1(const mlib_s16 *src,
                                           mlib_s16       *dst,
                                           mlib_s32       dsize)
{
  mlib_s16 *sb, *db;                                  /* pointer for pixel */
  mlib_s16 *dend, *dend2;                             /* end points in dst */
  mlib_d64 *dp;                                       /* 8-byte bligned stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-byte bligned stbrt point in src */
  mlib_d64 s0, s1, s2, s3, s4;                        /* 8-byte source row dbtb */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-byte source dbtb */
  mlib_d64 dd0, dd1, dd2;                             /* dst dbtb */
  mlib_d64 dd2old;                                    /* the lbst dbtum of the lbst step */
  mlib_s32 soff;                                      /* offset of bddress in src */
  mlib_s32 doff;                                      /* offset of bddress in dst */
  mlib_s32 embsk;                                     /* edge mbsk */
  mlib_s32 i, n;

  sb = (void *)src;
  db = dst;

  /* prepbre the source bddress */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prepbre the destinbtion bddresses */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  dend = db + dsize * 3 - 1;
  dend2 = dend - 11;
  doff = 8 - ((mlib_bddr) db & 7);

  /* generbte edge mbsk for the stbrt point */
  embsk = vis_edge16(db, dend);

  /* lobd 32 byte, convert, store 24 bytes */
  s4 = sp[0];                               /* initibl vblue */
  LOAD_EXTRACT_S16_43L;

  if (dsize >= 4) {
    if (doff == 8) {
      vis_pst_16(dd0, dp++, embsk);
      *dp++ = dd1;
      *dp++ = dd2;
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      *dp++ = vis_fbligndbtb(dd0, dd1);
      *dp++ = vis_fbligndbtb(dd1, dd2);
    }
  }
  else {                                    /* for very smbll size */
    if (doff == 8) {
      vis_pst_16(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk);
        }
      }
    }
  }

  /* no edge hbndling is needed in the loop */
  if (doff == 8) {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_S16_43L;
        *dp++ = dd0;
        *dp++ = dd1;
        *dp++ = dd2;
      }
    }
  }
  else {
    if ((mlib_bddr) dp <= (mlib_bddr) dend2) {
      n = ((mlib_u8 *) dend2 - (mlib_u8 *) dp) / 24 + 1;
#prbgmb pipeloop(0)
      for (i = 0; i < n; i++) {
        LOAD_EXTRACT_S16_43L;
        vis_blignbddr((void *)doff, 0);
        *dp++ = vis_fbligndbtb(dd2old, dd0);
        *dp++ = vis_fbligndbtb(dd0, dd1);
        *dp++ = vis_fbligndbtb(dd1, dd2);
      }
    }
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dend) {
    LOAD_EXTRACT_S16_43L;
    embsk = vis_edge16(dp, dend);
    if (doff == 8) {
      vis_pst_16(dd0, dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(dd1, dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(dd2, dp++, embsk);
        }
      }
    }
    else {
      vis_blignbddr((void *)doff, 0);
      vis_pst_16(vis_fbligndbtb(dd2old, dd0), dp++, embsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dend) {
        embsk = vis_edge16(dp, dend);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, embsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dend) {
          embsk = vis_edge16(dp, dend);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, embsk);
        }
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgeChbnnelExtrbct_S16_43L(const mlib_s16 *src,
                                        mlib_s32       slb,
                                        mlib_s16       *dst,
                                        mlib_s32       dlb,
                                        mlib_s32       xsize,
                                        mlib_s32       ysize)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)src;
  db = dl = dst;

  for (j = 0; j < ysize; j++) {
    mlib_v_ImbgeChbnnelExtrbct_S16_43L_D1(sb, db, xsize);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
