/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */



/*
 * FILENAME: mlib_v_ImbgfCibnnflInsfrt_34.d
 *
 * FUNCTIONS
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D1X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D2X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34R_D1
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34R
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D1X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D2X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34R_D1
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34R
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D1X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D2X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34L_D1
 *      mlib_v_ImbgfCibnnflInsfrt_U8_34L
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D1X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D2X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34L_D1
 *      mlib_v_ImbgfCibnnflInsfrt_S16_34L
 *
 * SYNOPSIS
 *
 * ARGUMENT
 *      srd       pointfr to sourdf imbgf dbtb
 *      dst       pointfr to dfstinbtion imbgf dbtb
 *          slb   sourdf imbgf linf stridf in bytfs
 *          dlb   dfstinbtion imbgf linf stridf in bytfs
 *          dsizf       imbgf dbtb sizf in pixfls
 *          xsizf       imbgf widti in pixfls
 *          ysizf       imbgf ifigit in linfs
 *          dmbsk dibnnfl mbsk
 *
 * DESCRIPTION
 *          Insfrt b 3-dibnnfl imbgf into tif rigit or lfft 3 dibnnfls of
 *          b 4-dibnnfl imbgf low lfvfl fundtions.
 *
 *                BGR => ABGR   (34R), or       RGB => RGBA     (34L)
 *
 * NOTE
 *          Tifsf fundtions brf sfpbrbtfd from mlib_v_ImbgfCibnnflInsfrt.d
 *          for loop unrolling bnd strudturf dlbrity.
 */

#indludf <stdlib.i>
#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"

/***************************************************************/
#dffinf INSERT_U8_34R                                                                         \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));                    \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));                    \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));                    \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));                    \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_ii(sdd));                    \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdd));                    \
  sdg = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdf));                    \
  sdi = vis_fpmfrgf(vis_rfbd_lo(sdd), vis_rfbd_ii(sdf));                    \
  sdi = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_lo(sdf));                    \
  sdj = vis_fpmfrgf(vis_rfbd_ii(sdg), vis_rfbd_ii(sdi));                    \
  sdk = vis_fpmfrgf(vis_rfbd_lo(sdg), vis_rfbd_lo(sdi));                    \
  sdl = vis_fpmfrgf(vis_rfbd_ii(sdi), vis_rfbd_ii(sdi));                    \
  sdm = vis_fpmfrgf(vis_rfbd_lo(sdi), vis_rfbd_lo(sdi));                    \
  dd0 = vis_fpmfrgf(vis_rfbd_ii(sdl), vis_rfbd_ii(sdj));                    \
  dd1 = vis_fpmfrgf(vis_rfbd_lo(sdl), vis_rfbd_lo(sdj));                    \
  dd2 = vis_fpmfrgf(vis_rfbd_ii(sdm), vis_rfbd_ii(sdk));                    \
  dd3 = vis_fpmfrgf(vis_rfbd_lo(sdm), vis_rfbd_lo(sdk));

/***************************************************************/
#dffinf LOAD_INSERT_STORE_U8_34R_A8                                                         \
  sd0 = *sp++;                                  /* b0g0r0b1g1r1b2g2 */                  \
  sd1 = *sp++;                                  /* r2b3g3r3b4g4r4b5 */                  \
  sd2 = *sp++;                                  /* g5r5b6g6r6b7g7r7 */                  \
  INSERT_U8_34R                                                                                           \
  vis_pst_8(dd0, dp++, bmbsk);                                                                \
  vis_pst_8(dd1, dp++, bmbsk);                                                                \
  vis_pst_8(dd2, dp++, bmbsk);                                                                \
  vis_pst_8(dd3, dp++, bmbsk);

/***************************************************************/
#dffinf LOAD_INSERT_U8_34R                                                                      \
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
 * Boti sourdf bnd dfstinbtion imbgf dbtb brf 1-d vfdtors bnd
 * 8-bytf blignfd. And dsizf is multiplf of 8.
 */

void
mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D1X8(mlib_u8  *srd,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsizf)
{
  mlib_d64  *sp, *dp;
  mlib_d64  sd0, sd1, sd2;          /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdd, sdd; /* intfrmfdibtf vbribblfs */
  mlib_d64  sdf, sdf, sdg, sdi;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int       bmbsk = 0x77;
  int       i;

  sp = (mlib_d64 *)srd;
  dp = (mlib_d64 *)dst;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 8; i++) {
    LOAD_INSERT_STORE_U8_34R_A8;
  }
}

/***************************************************************/
/*
 * Eitifr sourdf or dfstinbtion imbgf dbtb brf not 1-d vfdtors, but
 * tify brf 8-bytf blignfd. And slb bnd dlb brf multiplf of 8.
 * Tif xsizf is multiplf of 8.
 */

void
mlib_v_ImbgfCibnnflInsfrt_U8_34R_A8D2X8(mlib_u8  *srd,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                                mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_d64  *sp, *dp;             /* 8-bytf blignfd pointfr for pixfl */
  mlib_d64  *sl, *dl;             /* 8-bytf blignfd pointfr for linf */
  mlib_d64  sd0, sd1, sd2;      /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdd, sdd; /* intfrmfdibtf vbribblfs */
  mlib_d64  sdf, sdf, sdg, sdi;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int         bmbsk = 0x77;
  int       i, j;               /* indidfs for x, y */

  sp = sl = (mlib_d64 *)srd;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    /* 8-bytf dolumn loop */
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 8; i++) {
      LOAD_INSERT_STORE_U8_34R_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * fitifr sourdf or dfstinbtion dbtb brf not 8-bytf blignfd.
 */

void
mlib_v_ImbgfCibnnflInsfrt_U8_34R_D1(mlib_u8  *srd,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsizf)
{
  mlib_u8   *sb, *db;
  mlib_u8   *dfnd, *dfnd2;      /* fnd points in dst */
  mlib_d64  *dp;                  /* 8-bytf blignfd stbrt points in dst */
  mlib_d64  *sp;                  /* 8-bytf blignfd stbrt point in srd */
  mlib_d64  s0, s1, s2, s3;     /* 8-bytf sourdf rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-bytf sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                  /* tif lbst dbtum of tif lbst stfp */
  mlib_d64  sdb, sdb, sdd, sdd; /* intfrmfdibtf vbribblfs */
  mlib_d64  sdf, sdf, sdg, sdi;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int       soff;                 /* offsft of bddrfss in srd */
  int       doff;                 /* offsft of bddrfss in dst */
  int       fmbsk;              /* fdgf mbsk */
  int         bmbsk;            /* dibnnfl mbsk */
  int         i, n;

  sb = srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dfnd  = db + dsizf * 4 - 1;
  dfnd2 = dfnd - 31;
  doff  = ((mlib_bddr) db & 7);

  /* sft bbnd mbsk for vis_pst_8 to storf tif bytfs nffdfd */
  bmbsk = 0xff & (0x7777 >> doff) ;

  /* gfnfrbtf fdgf mbsk for tif stbrt point */
  fmbsk = vis_fdgf8(db, dfnd);

  /* lobd 24 bytfs, donvfrt to 32 bytfs */
  s3 = sp[0];                                   /* initibl vbluf */
  LOAD_INSERT_U8_34R;

  if (doff == 0) {                              /* dst is 8-bytf blignfd */

    if (dsizf >= 8 ) {
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      vis_pst_8(dd1, dp++, bmbsk);
      vis_pst_8(dd2, dp++, bmbsk);
      vis_pst_8(dd3, dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(dd2, dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(dd3, dp++, fmbsk & bmbsk);
          }
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34R;
        vis_pst_8(dd0, dp++, bmbsk);
        vis_pst_8(dd1, dp++, bmbsk);
        vis_pst_8(dd2, dp++, bmbsk);
        vis_pst_8(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_U8_34R;
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(dd2, dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(dd3, dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
  flsf {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsizf >= 8 ) {
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
            if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
              fmbsk = vis_fdgf8(dp, dfnd);
              vis_pst_8(vis_fbligndbtb(dd3, dd3), dp++, fmbsk & bmbsk);
            }
          }
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34R;
        vis_blignbddr((void *)0, -doff);
        vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_U8_34R;
      vis_blignbddr((void *)0, -doff);
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/

void
mlib_v_ImbgfCibnnflInsfrt_U8_34R(mlib_u8  *srd,  mlib_s32 slb,
                                                 mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_u8   *sb, *db;
  mlib_u8   *sl, *dl;
  int         j;

  sb = sl = srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_U8_34R_D1(sb, db, xsizf);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf INSERT_S16_34R                                                                              \
  vis_blignbddr((void *)0, 6);                                                                \
  dd0 = vis_fbligndbtb(sd0, sd0);                 /* b1b0g0r0 */                \
  vis_blignbddr((void *)0, 4);                                                                \
  dd1 = vis_fbligndbtb(sd0, sd1);                 /* r0b1gbr1 */                \
  vis_blignbddr((void *)0, 2);                                                                \
  dd2 = vis_fbligndbtb(sd1, sd2);                       /* r1b2g2r2 */          \
  dd3 = sd2;                                                          /* r2b3g3r3 */

/***************************************************************/
#dffinf LOAD_INSERT_STORE_S16_34R_A8                                                      \
  sd0 = *sp++;                                          /* b0g0r0b1 */                      \
  sd1 = *sp++;                                          /* g1r1b2g2 */                      \
  sd2 = *sp++;                                          /* r2b3g3r3 */                      \
  INSERT_S16_34R                                                                                          \
  vis_pst_16(dd0, dp++, bmbsk);                                                               \
  vis_pst_16(dd1, dp++, bmbsk);                                                               \
  vis_pst_16(dd2, dp++, bmbsk);                                                               \
  vis_pst_16(dd3, dp++, bmbsk);

/***************************************************************/
#dffinf LOAD_INSERT_S16_34R                                                                       \
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
 * boti sourdf bnd dfstinbtion imbgf dbtb brf 1-d vfdtors bnd
 * 8-bytf blignfd.  dsizf is multiplf of 4.
 */

void
mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D1X4(mlib_s16 *srd,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsizf)
{
  mlib_d64  *sp, *dp;           /* 8-bytf blignfd pointfr for pixfl */
  mlib_d64  sd0, sd1, sd2;      /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x07;       /* dibnnfl mbsk */
  int       i;

  sp = (mlib_d64 *)srd;
  dp = (mlib_d64 *)dst;

  /* sft GSR.offsft for vis_fbligndbtb()  */
  /* vis_blignbddr((void *)0, 2); */            /* only for _old */

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_34R_A8;
  }
}

/***************************************************************/
/*
 * fitifr sourdf or dfstinbtion imbgf dbtb brf not 1-d vfdtors, but
 * tify brf 8-bytf blignfd.  xsizf is multiplf of 4.
 */

void
mlib_v_ImbgfCibnnflInsfrt_S16_34R_A8D2X4(mlib_s16 *srd,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_d64  *sp, *dp;           /* 8-bytf blignfd pointfr for pixfl */
  mlib_d64  *sl, *dl;           /* 8-bytf blignfd pointfr for linf */
  mlib_d64  sd0, sd1, sd2;      /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x07;       /* dibnnfl mbsk */
  int       i, j;               /* indidfs for x, y */

  sp = sl = (mlib_d64 *)srd;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    /* 4-pixfl dolumn loop */
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 4; i++) {
      LOAD_INSERT_STORE_S16_34R_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * fitifr sourdf or dfstinbtion dbtb brf not 8-bytf blignfd.
 */

void
mlib_v_ImbgfCibnnflInsfrt_S16_34R_D1(mlib_s16 *srd,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsizf)
{
  mlib_s16  *sb, *db;           /* pointfr for pixfl */
  mlib_s16  *dfnd, *dfnd2;      /* fnd points in dst */
  mlib_d64  *dp;                /* 8-bytf blignfd stbrt points in dst */
  mlib_d64  *sp;                /* 8-bytf blignfd stbrt point in srd */
  mlib_d64  s0, s1, s2, s3;     /* 8-bytf sourdf rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-bytf sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                /* tif lbst dbtum of tif lbst stfp */
  int soff;             /* offsft of bddrfss in srd */
  int doff;             /* offsft of bddrfss in dst */
  int       fmbsk;              /* fdgf mbsk */
  int       bmbsk;              /* dibnnfl mbsk */
  int       i, n;

  sb = srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dfnd  = db + dsizf * 4 - 1;
  dfnd2 = dfnd - 15;
  doff  = ((mlib_bddr) db & 7);

  /* sft dibnnfl mbsk for vis_pst_16 to storf tif words nffdfd */
  bmbsk = 0xff & (0x77 >> (doff / 2));

  /* gfnfrbtf fdgf mbsk for tif stbrt point */
  fmbsk = vis_fdgf16(db, dfnd);

  /* lobd 24 bytf, donvfrt, storf 32 bytfs */
  s3 = sp[0];                                   /* initibl vbluf */
  LOAD_INSERT_S16_34R;

  if (doff == 0) {                              /* dst is 8-bytf blignfd */

    if (dsizf >= 4 ) {
      vis_pst_16(dd0, dp++, fmbsk & bmbsk);
      vis_pst_16(dd1, dp++, bmbsk);
      vis_pst_16(dd2, dp++, bmbsk);
      vis_pst_16(dd3, dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_16(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(dd2, dp++, fmbsk & bmbsk);
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34R;
        vis_pst_16(dd0, dp++, bmbsk);
        vis_pst_16(dd1, dp++, bmbsk);
        vis_pst_16(dd2, dp++, bmbsk);
        vis_pst_16(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_S16_34R;
      fmbsk = vis_fdgf16(dp, dfnd);
      vis_pst_16(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(dd2, dp++, fmbsk & bmbsk);
        }
      }
    }
  }
  flsf {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsizf >= 4 ) {
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf16(dp, dfnd);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
          }
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34R;
        vis_blignbddr((void *)0, -doff);
        vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_S16_34R;
      vis_blignbddr((void *)0, -doff);
      fmbsk = vis_fdgf16(dp, dfnd);
      vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf16(dp, dfnd);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/

void
mlib_v_ImbgfCibnnflInsfrt_S16_34R(mlib_s16 *srd,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_s16  *sb, *db;
  mlib_s16  *sl, *dl;
  int       j;

  sb = sl = srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_S16_34R_D1(sb, db, xsizf);
    sb = sl = (mlib_s16 *)((mlib_u8 *)sl + slb);
    db = dl = (mlib_s16 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
#dffinf INSERT_U8_34L                                                                                 \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));                    \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));                    \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));                    \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));                    \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_ii(sdd));                    \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdd));                    \
  sdg = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdf));                    \
  sdi = vis_fpmfrgf(vis_rfbd_lo(sdd), vis_rfbd_ii(sdf));                    \
  sdi = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_lo(sdf));                    \
  sdj = vis_fpmfrgf(vis_rfbd_ii(sdg), vis_rfbd_ii(sdi));                    \
  sdk = vis_fpmfrgf(vis_rfbd_lo(sdg), vis_rfbd_lo(sdi));                    \
  sdl = vis_fpmfrgf(vis_rfbd_ii(sdi), vis_rfbd_ii(sdi));                    \
  sdm = vis_fpmfrgf(vis_rfbd_lo(sdi), vis_rfbd_lo(sdi));                    \
  dd0 = vis_fpmfrgf(vis_rfbd_ii(sdj), vis_rfbd_ii(sdl));                    \
  dd1 = vis_fpmfrgf(vis_rfbd_lo(sdj), vis_rfbd_lo(sdl));                    \
  dd2 = vis_fpmfrgf(vis_rfbd_ii(sdk), vis_rfbd_ii(sdm));                    \
  dd3 = vis_fpmfrgf(vis_rfbd_lo(sdk), vis_rfbd_lo(sdm));

/***************************************************************/
#dffinf LOAD_INSERT_STORE_U8_34L_A8                                                         \
  sd0 = *sp++;                                  /* b0g0r0b1g1r1b2g2 */                  \
  sd1 = *sp++;                                  /* r2b3g3r3b4g4r4b5 */                  \
  sd2 = *sp++;                                  /* g5r5b6g6r6b7g7r7 */                  \
  INSERT_U8_34L                                                                                                       \
  vis_pst_8(dd0, dp++, bmbsk);                                                                \
  vis_pst_8(dd1, dp++, bmbsk);                                                                \
  vis_pst_8(dd2, dp++, bmbsk);                                                                \
  vis_pst_8(dd3, dp++, bmbsk);

/***************************************************************/
#dffinf LOAD_INSERT_U8_34L                                                                        \
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
 * Boti sourdf bnd dfstinbtion imbgf dbtb brf 1-d vfdtors bnd
 * 8-bytf blignfd. And dsizf is multiplf of 8.
 */
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D1X8(mlib_u8  *srd,
                                                                mlib_u8  *dst,
                                                                mlib_s32 dsizf)
{
  mlib_d64  *sp, *dp;
  mlib_d64  sd0, sd1, sd2;          /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdd, sdd; /* intfrmfdibtf vbribblfs */
  mlib_d64  sdf, sdf, sdg, sdi;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int         bmbsk = 0xff;
  int         i;

  sp = (mlib_d64 *)srd;
  dp = (mlib_d64 *)dst;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 8; i++) {
    LOAD_INSERT_STORE_U8_34L_A8;
  }
}

/***************************************************************/
/*
 * Eitifr sourdf or dfstinbtion imbgf dbtb brf not 1-d vfdtors, but
 * tify brf 8-bytf blignfd. And slb bnd dlb brf multiplf of 8.
 * Tif xsizf is multiplf of 8.
 */
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L_A8D2X8(mlib_u8  *srd,  mlib_s32 slb,
                                                                mlib_u8  *dst,  mlib_s32 dlb,
                                                        mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_d64  *sp, *dp;           /* 8-bytf blignfd pointfr for pixfl */
  mlib_d64  *sl, *dl;           /* 8-bytf blignfd pointfr for linf */
  mlib_d64  sd0, sd1, sd2;      /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  sdb, sdb, sdd, sdd; /* intfrmfdibtf vbribblfs */
  mlib_d64  sdf, sdf, sdg, sdi;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int         bmbsk = 0xff;
  int       i, j;               /* indidfs for x, y */

  sp = sl = (mlib_d64 *)srd;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    /* 8-bytf dolumn loop */
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 8; i++) {
      LOAD_INSERT_STORE_U8_34L_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * fitifr sourdf or dfstinbtion dbtb brf not 8-bytf blignfd.
 */
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L_D1(mlib_u8  *srd,
                                                            mlib_u8  *dst,
                                                            mlib_s32 dsizf)
{
  mlib_u8   *sb, *db;
  mlib_u8   *dfnd, *dfnd2;      /* fnd points in dst */
  mlib_d64  *dp;                /* 8-bytf blignfd stbrt points in dst */
  mlib_d64  *sp;                /* 8-bytf blignfd stbrt point in srd */
  mlib_d64  s0, s1, s2, s3;     /* 8-bytf sourdf rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-bytf sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                /* tif lbst dbtum of tif lbst stfp */
  mlib_d64  sdb, sdb, sdd, sdd; /* intfrmfdibtf vbribblfs */
  mlib_d64  sdf, sdf, sdg, sdi;
  mlib_d64  sdi, sdj, sdk, sdl;
  mlib_d64  sdm;
  int       soff;               /* offsft of bddrfss in srd */
  int       doff;               /* offsft of bddrfss in dst */
  int       fmbsk;              /* fdgf mbsk */
  int         bmbsk;            /* dibnnfl mbsk */
  int         i, n;

  sb = srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dfnd  = db + dsizf * 4 - 1;
  dfnd2 = dfnd - 31;
  doff  = ((mlib_bddr) db & 7);

  /* sft bbnd mbsk for vis_pst_8 to storf tif bytfs nffdfd */
  bmbsk = 0xff & (0xffff >> doff) ;

  /* gfnfrbtf fdgf mbsk for tif stbrt point */
  fmbsk = vis_fdgf8(db, dfnd);

  /* lobd 24 bytfs, donvfrt to 32 bytfs */
  s3 = sp[0];                                   /* initibl vbluf */
  LOAD_INSERT_U8_34L;

  if (doff == 0) {                              /* dst is 8-bytf blignfd */

    if (dsizf >= 8 ) {
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      vis_pst_8(dd1, dp++, bmbsk);
      vis_pst_8(dd2, dp++, bmbsk);
      vis_pst_8(dd3, dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(dd2, dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(dd3, dp++, fmbsk & bmbsk);
          }
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34L;
        vis_pst_8(dd0, dp++, bmbsk);
        vis_pst_8(dd1, dp++, bmbsk);
        vis_pst_8(dd2, dp++, bmbsk);
        vis_pst_8(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_U8_34L;
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(dd2, dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(dd3, dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
  flsf {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsizf >= 8 ) {
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
            if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
              fmbsk = vis_fdgf8(dp, dfnd);
              vis_pst_8(vis_fbligndbtb(dd3, dd3), dp++, fmbsk & bmbsk);
            }
          }
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_U8_34L;
        vis_blignbddr((void *)0, -doff);
        vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_U8_34L;
      vis_blignbddr((void *)0, -doff);
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/
void
mlib_v_ImbgfCibnnflInsfrt_U8_34L(mlib_u8  *srd,  mlib_s32 slb,
                                                         mlib_u8  *dst,  mlib_s32 dlb,
                                                         mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_u8   *sb, *db;
  mlib_u8   *sl, *dl;
  int         j;

  sb = sl = srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_U8_34L_D1(sb, db, xsizf);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf INSERT_S16_34L                                                                              \
  dd0 = sd0;                                                            /* b0g0r0b1 */        \
  vis_blignbddr((void *)0, 6);                                                                \
  dd1 = vis_fbligndbtb(sd0, sd1);                       /* b1gbr1b2 */        \
  vis_blignbddr((void *)0, 4);                                                                \
  dd2 = vis_fbligndbtb(sd1, sd2);                         /* b2g2r2b3 */              \
  vis_blignbddr((void *)0, 2);                                                                \
  dd3 = vis_fbligndbtb(sd2, sd2);                         /* b3g3r3r2 */

/***************************************************************/
#dffinf LOAD_INSERT_STORE_S16_34L_A8                                                      \
  sd0 = *sp++;                                          /* b0g0r0b1 */                          \
  sd1 = *sp++;                                          /* g1r1b2g2 */                      \
  sd2 = *sp++;                                          /* r2b3g3r3 */                      \
  INSERT_S16_34L                                                                                          \
  vis_pst_16(dd0, dp++, bmbsk);                                                               \
  vis_pst_16(dd1, dp++, bmbsk);                                                               \
  vis_pst_16(dd2, dp++, bmbsk);                                                               \
  vis_pst_16(dd3, dp++, bmbsk);

/***************************************************************/
#dffinf LOAD_INSERT_S16_34L                                                                       \
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
 * boti sourdf bnd dfstinbtion imbgf dbtb brf 1-d vfdtors bnd
 * 8-bytf blignfd.  dsizf is multiplf of 4.
 */

void
mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D1X4(mlib_s16 *srd,
                                                                 mlib_s16 *dst,
                                                                 mlib_s32 dsizf)
{
  mlib_d64  *sp, *dp;           /* 8-bytf blignfd pointfr for pixfl */
  mlib_d64  sd0, sd1, sd2;      /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x0f;       /* dibnnfl mbsk */
  int       i;

  sp = (mlib_d64 *)srd;
  dp = (mlib_d64 *)dst;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_34L_A8;
  }
}

/***************************************************************/
/*
 * fitifr sourdf or dfstinbtion imbgf dbtb brf not 1-d vfdtors, but
 * tify brf 8-bytf blignfd.  xsizf is multiplf of 4.
 */

void
mlib_v_ImbgfCibnnflInsfrt_S16_34L_A8D2X4(mlib_s16 *srd,  mlib_s32 slb,
                                                                 mlib_s16 *dst,  mlib_s32 dlb,
                                                                 mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_d64  *sp, *dp;           /* 8-bytf blignfd pointfr for pixfl */
  mlib_d64  *sl, *dl;           /* 8-bytf blignfd pointfr for linf */
  mlib_d64  sd0, sd1, sd2;      /* sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  int       bmbsk = 0x0f;       /* dibnnfl mbsk */
  int       i, j;               /* indidfs for x, y */

  sp = sl = (mlib_d64 *)srd;
  dp = dl = (mlib_d64 *)dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    /* 4-pixfl dolumn loop */
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 4; i++) {
      LOAD_INSERT_STORE_S16_34L_A8;
    }
    sp = sl = (mlib_d64 *)((mlib_u8 *)sl + slb);
    dp = dl = (mlib_d64 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
/*
 * fitifr sourdf or dfstinbtion dbtb brf not 8-bytf blignfd.
 */

void
mlib_v_ImbgfCibnnflInsfrt_S16_34L_D1(mlib_s16 *srd,
                                                             mlib_s16 *dst,
                                                             mlib_s32 dsizf)
{
  mlib_s16  *sb, *db;           /* pointfr for pixfl */
  mlib_s16  *dfnd, *dfnd2;      /* fnd points in dst */
  mlib_d64  *dp;                /* 8-bytf blignfd stbrt points in dst */
  mlib_d64  *sp;                /* 8-bytf blignfd stbrt point in srd */
  mlib_d64  s0, s1, s2, s3;     /* 8-bytf sourdf rbw dbtb */
  mlib_d64  sd0, sd1, sd2;      /* 8-bytf sourdf dbtb */
  mlib_d64  dd0, dd1, dd2, dd3; /* dst dbtb */
  mlib_d64  dd4;                /* tif lbst dbtum of tif lbst stfp */
  int soff;             /* offsft of bddrfss in srd */
  int doff;             /* offsft of bddrfss in dst */
  int       fmbsk;              /* fdgf mbsk */
  int       bmbsk;              /* dibnnfl mbsk */
  int       i, n;

  sb = srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp    = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff  = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp    = (mlib_d64 *)((mlib_bddr) db & (~7));
  dfnd  = db + dsizf * 4 - 1;
  dfnd2 = dfnd - 15;
  doff  = ((mlib_bddr) db & 7);

  /* sft dibnnfl mbsk for vis_pst_16 to storf tif words nffdfd */
  bmbsk = 0xff & (0xff >> (doff / 2));

  /* gfnfrbtf fdgf mbsk for tif stbrt point */
  fmbsk = vis_fdgf16(db, dfnd);

  /* lobd 24 bytf, donvfrt, storf 32 bytfs */
  s3 = sp[0];                                   /* initibl vbluf */
  LOAD_INSERT_S16_34L;

  if (doff == 0) {                              /* dst is 8-bytf blignfd */

    if (dsizf >= 4 ) {
      vis_pst_16(dd0, dp++, fmbsk & bmbsk);
      vis_pst_16(dd1, dp++, bmbsk);
      vis_pst_16(dd2, dp++, bmbsk);
      vis_pst_16(dd3, dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_16(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(dd2, dp++, fmbsk & bmbsk);
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34L;
        vis_pst_16(dd0, dp++, bmbsk);
        vis_pst_16(dd1, dp++, bmbsk);
        vis_pst_16(dd2, dp++, bmbsk);
        vis_pst_16(dd3, dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_S16_34L;
      fmbsk = vis_fdgf16(dp, dfnd);
      vis_pst_16(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(dd2, dp++, fmbsk & bmbsk);
        }
      }
    }
  }
  flsf {                                        /* (doff != 0) */
    vis_blignbddr((void *)0, -doff);

    if (dsizf >= 4 ) {
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
    }
    flsf {                                      /* for vfry smbll sizf */
      vis_pst_16(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf16(dp, dfnd);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
          }
        }
      }
    }

    /* no fdgf ibndling is nffdfd in tif loop */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2)  {
      n = ((mlib_u8 *)dfnd2 - (mlib_u8 *)dp) / 32 + 1;
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        LOAD_INSERT_S16_34L;
        vis_blignbddr((void *)0, -doff);
        vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
      LOAD_INSERT_S16_34L;
      vis_blignbddr((void *)0, -doff);
      fmbsk = vis_fdgf16(dp, dfnd);
      vis_pst_16(vis_fbligndbtb(dd4, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
        fmbsk = vis_fdgf16(dp, dfnd);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
          fmbsk = vis_fdgf16(dp, dfnd);
          vis_pst_16(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd)  {
            fmbsk = vis_fdgf16(dp, dfnd);
            vis_pst_16(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/

void
mlib_v_ImbgfCibnnflInsfrt_S16_34L(mlib_s16 *srd,  mlib_s32 slb,
                                                          mlib_s16 *dst,  mlib_s32 dlb,
                                                          mlib_s32 xsizf, mlib_s32 ysizf)
{
  mlib_s16  *sb, *db;
  mlib_s16  *sl, *dl;
  int       j;

  sb = sl = srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_S16_34L_D1(sb, db, xsizf);
    sb = sl = (mlib_s16 *)((mlib_u8 *)sl + slb);
    db = dl = (mlib_s16 *)((mlib_u8 *)dl + dlb);
  }
}

/***************************************************************/
