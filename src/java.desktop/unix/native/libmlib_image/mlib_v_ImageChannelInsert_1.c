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
 * FUNCTIONS
 *      mlib_v_ImbgfCibnnflInsfrt_U8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D1X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D2X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_12_D1
 *      mlib_v_ImbgfCibnnflInsfrt_U8_12
 *      mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D1X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D2X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_13_D1
 *      mlib_v_ImbgfCibnnflInsfrt_U8_13
 *      mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D1X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D2X8
 *      mlib_v_ImbgfCibnnflInsfrt_U8_14_D1
 *      mlib_v_ImbgfCibnnflInsfrt_U8_14
 *      mlib_v_ImbgfCibnnflInsfrt_S16
 *      mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D1X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D2X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_12_D1
 *      mlib_v_ImbgfCibnnflInsfrt_S16_12
 *      mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D1X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D2X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_13_D1
 *      mlib_v_ImbgfCibnnflInsfrt_S16_13
 *      mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D1X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D2X4
 *      mlib_v_ImbgfCibnnflInsfrt_S16_14_D1
 *      mlib_v_ImbgfCibnnflInsfrt_S16_14
 *      mlib_v_ImbgfCibnnflInsfrt_S32
 *      mlib_v_ImbgfCibnnflInsfrt_D64
 *
 * ARGUMENT
 *      srd     pointfr to sourdf imbgf dbtb
 *      dst     pointfr to dfstinbtion imbgf dbtb
 *      slb     sourdf imbgf linf stridf in bytfs
 *      dlb     dfstinbtion imbgf linf stridf in bytfs
 *      dsizf   imbgf dbtb sizf in pixfls
 *      xsizf   imbgf widti in pixfls
 *      ysizf   imbgf ifigit in linfs
 *      dmbsk   dibnnfl mbsk
 *
 * DESCRIPTION
 *      Copy tif 1-dibnnfl sourdf imbgf into tif sflfdtfd dibnnfl
 *      of tif dfstinbtion imbgf -- VIS vfrsion low lfvfl fundtions.
 *
 * NOTE
 *      Tifsf fundtions brf sfpbrbtfd from mlib_v_ImbgfCibnnflInsfrt.d
 *      for loop unrolling bnd strudturf dlbrity.
 */

#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_v_ImbgfCibnnflInsfrt.i"

/***************************************************************/
/* gfnfrbl dibnnfl insfrtion: slowfr duf to tif innfr loop */
void mlib_v_ImbgfCibnnflInsfrt_U8(donst mlib_u8 *srd,
                                  mlib_s32      slb,
                                  mlib_u8       *dst,
                                  mlib_s32      dlb,
                                  mlib_s32      dibnnfls,
                                  mlib_s32      dibnnfld,
                                  mlib_s32      widti,
                                  mlib_s32      ifigit,
                                  mlib_s32      dmbsk)
{
  mlib_u8 *sp;                                        /* pointfr for pixfl in srd */
  mlib_u8 *sl;                                        /* pointfr for linf in srd */
  mlib_u8 *dp;                                        /* pointfr for pixfl in dst */
  mlib_u8 *dl;                                        /* pointfr for linf in dst */
  mlib_s32 i, j, k;                                   /* indidfs for x, y, dibnnfl */
  mlib_s32 dfltbd[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 ind0, ind1, ind2;
  mlib_u8 s0, s1, s2;

  dfltbd[dibnnfls] = 1;
  for (i = (dibnnfld - 1), k = 0; i >= 0; i--) {
    if ((dmbsk & (1 << i)) == 0)
      dfltbd[k]++;
    flsf
      k++;
  }

  dfltbd[dibnnfls] = dibnnfld;
  for (i = 1; i < dibnnfls; i++) {
    dfltbd[dibnnfls] -= dfltbd[i];
  }

  sp = sl = (void *)srd;
  dp = dl = dst + dfltbd[0];

  if (dibnnfls == 2) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[ind0] = s1;
        dp += ind1;
        sp += 2;
      }

      sp = sl += slb;
      dp = dl += dlb;
    }
  }
  flsf if (dibnnfls == 3) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    ind2 = dfltbd[3] + ind1;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[ind0] = s1;
        dp[ind1] = s2;
        dp += ind2;
        sp += 3;
      }

      sp = sl += slb;
      dp = dl += dlb;
    }
  }
}

/***************************************************************/
/* gfnfrbl dibnnfl insfrtion: slowfr duf to tif innfr loop */
void mlib_v_ImbgfCibnnflInsfrt_D64(donst mlib_d64 *srd,
                                   mlib_s32       slb,
                                   mlib_d64       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       dibnnfls,
                                   mlib_s32       dibnnfld,
                                   mlib_s32       widti,
                                   mlib_s32       ifigit,
                                   mlib_s32       dmbsk)
{
  mlib_d64 *sp;                                       /* pointfr for pixfl in srd */
  mlib_d64 *sl;                                       /* pointfr for linf in srd */
  mlib_d64 *dp;                                       /* pointfr for pixfl in dst */
  mlib_d64 *dl;                                       /* pointfr for linf in dst */
  mlib_s32 i, j, k;                                   /* indidfs for x, y, dibnnfl */
  mlib_s32 dfltbd[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 ind0, ind1, ind2;
  mlib_d64 s0, s1, s2;

  dfltbd[dibnnfls] = 1;
  for (i = (dibnnfld - 1), k = 0; i >= 0; i--) {
    if ((dmbsk & (1 << i)) == 0)
      dfltbd[k]++;
    flsf
      k++;
  }

  dfltbd[dibnnfls] = dibnnfld;
  for (i = 1; i < dibnnfls; i++) {
    dfltbd[dibnnfls] -= dfltbd[i];
  }

  sp = sl = (void *)srd;
  dp = dl = dst + dfltbd[0];

  if (dibnnfls == 1) {
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        dp[0] = s0;
        dp += dibnnfld;
        sp++;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  flsf if (dibnnfls == 2) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[ind0] = s1;
        dp += ind1;
        sp += 2;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  flsf if (dibnnfls == 3) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    ind2 = dfltbd[3] + ind1;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[ind0] = s1;
        dp[ind1] = s2;
        dp += ind2;
        sp += 3;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
/* gfnfrbl dibnnfl insfrtion: slowfr duf to tif innfr loop */
void mlib_v_ImbgfCibnnflInsfrt_S16(donst mlib_s16 *srd,
                                   mlib_s32       slb,
                                   mlib_s16       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       dibnnfls,
                                   mlib_s32       dibnnfld,
                                   mlib_s32       widti,
                                   mlib_s32       ifigit,
                                   mlib_s32       dmbsk)
{
  mlib_s16 *sp;                                       /* pointfr for pixfl in srd */
  mlib_s16 *sl;                                       /* pointfr for linf in srd */
  mlib_s16 *dp;                                       /* pointfr for pixfl in dst */
  mlib_s16 *dl;                                       /* pointfr for linf in dst */
  mlib_s32 i, j, k;                                   /* indidfs for x, y, dibnnfl */
  mlib_s32 dfltbd[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 ind0, ind1, ind2;
  mlib_s16 s0, s1, s2;

  dfltbd[dibnnfls] = 1;
  for (i = (dibnnfld - 1), k = 0; i >= 0; i--) {
    if ((dmbsk & (1 << i)) == 0)
      dfltbd[k]++;
    flsf
      k++;
  }

  dfltbd[dibnnfls] = dibnnfld;
  for (i = 1; i < dibnnfls; i++) {
    dfltbd[dibnnfls] -= dfltbd[i];
  }

  sp = sl = (void *)srd;
  dp = dl = dst + dfltbd[0];

  if (dibnnfls == 2) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[ind0] = s1;
        dp += ind1;
        sp += 2;
      }

      sp = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
    }
  }
  flsf if (dibnnfls == 3) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    ind2 = dfltbd[3] + ind1;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[ind0] = s1;
        dp[ind1] = s2;
        dp += ind2;
        sp += 3;
      }

      sp = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
/* gfnfrbl dibnnfl insfrtion: slowfr duf to tif innfr loop */

void mlib_v_ImbgfCibnnflInsfrt_S32(donst mlib_s32 *srd,
                                   mlib_s32       slb,
                                   mlib_s32       *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       dibnnfls,
                                   mlib_s32       dibnnfld,
                                   mlib_s32       widti,
                                   mlib_s32       ifigit,
                                   mlib_s32       dmbsk)
{
  mlib_s32 *sp;                                       /* pointfr for pixfl in srd */
  mlib_s32 *sl;                                       /* pointfr for linf in srd */
  mlib_s32 *dp;                                       /* pointfr for pixfl in dst */
  mlib_s32 *dl;                                       /* pointfr for linf in dst */
  mlib_s32 i, j, k;                                   /* indidfs for x, y, dibnnfl */
  mlib_s32 dfltbd[5] = { 0, 1, 1, 1, 1 };
  mlib_s32 ind0, ind1, ind2;
  mlib_s32 s0, s1, s2;

  dfltbd[dibnnfls] = 1;
  for (i = (dibnnfld - 1), k = 0; i >= 0; i--) {
    if ((dmbsk & (1 << i)) == 0)
      dfltbd[k]++;
    flsf
      k++;
  }

  dfltbd[dibnnfls] = dibnnfld;
  for (i = 1; i < dibnnfls; i++) {
    dfltbd[dibnnfls] -= dfltbd[i];
  }

  sp = sl = (void *)srd;
  dp = dl = dst + dfltbd[0];

  if (dibnnfls == 1) {
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        dp[0] = s0;
        dp += dibnnfld;
        sp++;
      }

      sp = sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
  flsf if (dibnnfls == 2) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        dp[0] = s0;
        dp[ind0] = s1;
        dp += ind1;
        sp += 2;
      }

      sp = sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
  flsf if (dibnnfls == 3) {
    ind0 = dfltbd[1];
    ind1 = dfltbd[2] + ind0;
    ind2 = dfltbd[3] + ind1;
    for (j = 0; j < ifigit; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < widti; i++) {
        s0 = sp[0];
        s1 = sp[1];
        s2 = sp[2];
        dp[0] = s0;
        dp[ind0] = s1;
        dp[ind1] = s2;
        dp += ind2;
        sp += 3;
      }

      sp = sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
    }
  }
}

/***************************************************************/
#dffinf INSERT_U8_12(sd0, dd0, dd1)     /* dibnnfl duplidbtf */ \
  dd0 = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd0));        \
  dd1 = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd0))

/***************************************************************/
/* insfrt onf dibnnfl to b 2-dibnnfl imbgf.
 * boti sourdf bnd dfstinbtion imbgf dbtb brf 8-bytf blignfd.
 * dsizf is multiplf of 8.
 */

void mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D1X8(donst mlib_u8 *srd,
                                            mlib_u8       *dst,
                                            mlib_s32      dsizf,
                                            mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0;
  mlib_d64 dd0, dd1;
  mlib_s32 bmbsk;
  mlib_s32 i;

  bmbsk = dmbsk | (dmbsk << 2) | (dmbsk << 4) | (dmbsk << 6);

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 8; i++) {
    sd0 = *sp++;
    INSERT_U8_12(sd0, dd0, dd1);
    vis_pst_8(dd0, dp++, bmbsk);
    vis_pst_8(dd1, dp++, bmbsk);
  }
}

/***************************************************************/
/* insfrt onf dibnnfl to b 2-dibnnfl imbgf.
 * boti sourdf bnd dfstinbtion imbgf dbtb brf 8-bytf blignfd.
 * xsizf is multiplf of 8.
 */

void mlib_v_ImbgfCibnnflInsfrt_U8_12_A8D2X8(donst mlib_u8 *srd,
                                            mlib_s32      slb,
                                            mlib_u8       *dst,
                                            mlib_s32      dlb,
                                            mlib_s32      xsizf,
                                            mlib_s32      ysizf,
                                            mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0;
  mlib_d64 dd0, dd1;
  mlib_s32 bmbsk;
  mlib_s32 i, j;

  bmbsk = dmbsk | (dmbsk << 2) | (dmbsk << 4) | (dmbsk << 6);

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 8; i++) {
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
/* insfrt onf dibnnfl to b 2-dibnnfl imbgf.
 */

void mlib_v_ImbgfCibnnflInsfrt_U8_12_D1(donst mlib_u8 *srd,
                                        mlib_u8       *dst,
                                        mlib_s32      dsizf,
                                        mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dfnd, *dfnd2;                              /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1;                                  /* 8-bytf sourdf dbtb */
  mlib_d64 dd0, dd1, dd2, dd3;                        /* 8-bytf dfstinbtion dbtb */
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 off;                                       /* offsft of srd ovfr dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 bmbsk;                                     /* dibnnfl mbsk */
  mlib_s32 i, n;

  bmbsk = dmbsk | (dmbsk << 2) | (dmbsk << 4) | (dmbsk << 6);

  sb = (void *)srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf * 2 - 1;
  dfnd2 = dfnd - 15;

  /* dbldulbtf tif srd's offsft ovfr dst */
  off = soff * 2 - doff;

  if (doff % 2 != 0) {
    bmbsk = (~bmbsk) & 0xff;
  }

  if (off == 0) {                           /* srd bnd dst ibvf sbmf blignmfnt */

    /* lobd 8 bytfs */
    sd0 = *sp++;

    /* insfrt, indluding somf gbrbbgf bt tif stbrt point */
    INSERT_U8_12(sd0, dd0, dd1);

    /* storf 16 bytfs rfsult */
    fmbsk = vis_fdgf8(db, dfnd);
    vis_pst_8(dd0, dp++, fmbsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(dd1, dp++, fmbsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
      n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        sd0 = *sp++;
        INSERT_U8_12(sd0, dd0, dd1);
        vis_pst_8(dd0, dp++, bmbsk);
        vis_pst_8(dd1, dp++, bmbsk);
      }
    }

    /* fnd point ibndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      sd0 = *sp++;
      INSERT_U8_12(sd0, dd0, dd1);
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(dd1, dp++, fmbsk & bmbsk);
      }
    }
  }
  flsf if (off < 0) {
    vis_blignbddr((void *)0, off);

    /* gfnfrbtf fdgf mbsk for tif stbrt point */
    fmbsk = vis_fdgf8(db, dfnd);

    /* lobd 8 bytfs */
    sd0 = *sp++;

    /* insfrt bnd storf 16 bytfs */
    INSERT_U8_12(sd0, dd0, dd1);
    vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
      n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        dd2 = dd1;
        sd0 = *sp++;
        INSERT_U8_12(sd0, dd0, dd1);
        vis_pst_8(vis_fbligndbtb(dd2, dd0), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
      }
    }

    /* fnd point ibndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      fmbsk = vis_fdgf8(dp, dfnd);
      dd2 = dd1;
      sd0 = *sp++;
      INSERT_U8_12(sd0, dd0, dd1);
      vis_pst_8(vis_fbligndbtb(dd2, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
      }
    }
  }
  flsf if (off < 8) {
    vis_blignbddr((void *)0, off);

    /* gfnfrbtf fdgf mbsk for tif stbrt point */
    fmbsk = vis_fdgf8(db, dfnd);

    /* lobd 16 bytfs */
    sd0 = *sp++;
    sd1 = *sp++;

    /* insfrt bnd storf 16 bytfs */
    INSERT_U8_12(sd0, dd0, dd1);
    INSERT_U8_12(sd1, dd2, dd3);
    vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
      n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        dd0 = dd2;
        dd1 = dd3;
        sd1 = *sp++;
        INSERT_U8_12(sd1, dd2, dd3);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
      }
    }

    /* fnd point ibndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      fmbsk = vis_fdgf8(dp, dfnd);
      dd0 = dd2;
      dd1 = dd3;
      sd1 = *sp++;
      INSERT_U8_12(sd1, dd2, dd3);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
      }
    }
  }
  flsf {                                    /* (off >= 8) */
    vis_blignbddr((void *)0, off);

    /* gfnfrbtf fdgf mbsk for tif stbrt point */
    fmbsk = vis_fdgf8(db, dfnd);

    /* lobd 16 bytfs */
    sd0 = *sp++;
    sd1 = *sp++;

    /* insfrt bnd storf 16 bytfs */
    INSERT_U8_12(sd0, dd0, dd1);
    INSERT_U8_12(sd1, dd2, dd3);
    vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
      n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 16 + 1;

      /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
      for (i = 0; i < n; i++) {
        dd0 = dd2;
        dd1 = dd3;
        sd1 = *sp++;
        INSERT_U8_12(sd1, dd2, dd3);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, bmbsk);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, bmbsk);
      }
    }

    /* fnd point ibndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      fmbsk = vis_fdgf8(dp, dfnd);
      dd0 = dd2;
      dd1 = dd3;
      sd1 = *sp++;
      INSERT_U8_12(sd1, dd2, dd3);
      vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
      }
    }
  }
}

/***************************************************************/
/* insfrt onf dibnnfl to b 2-dibnnfl imbgf.
 */

void mlib_v_ImbgfCibnnflInsfrt_U8_12(donst mlib_u8 *srd,
                                     mlib_s32      slb,
                                     mlib_u8       *dst,
                                     mlib_s32      dlb,
                                     mlib_s32      xsizf,
                                     mlib_s32      ysizf,
                                     mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_U8_12_D1(sb, db, xsizf, dmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf INSERT_U8_13(sd0, dd0, dd1, dd2)                        \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd0));        \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd0 = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_ii(sdd));        \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdd), vis_rfbd_lo(sdd));        \
  dd1 = vis_frfg_pbir(vis_rfbd_lo(dd0), vis_rfbd_ii(sdf));      \
  dd2 = vis_frfg_pbir(vis_rfbd_lo(sdf), vis_rfbd_lo(sdf))

/***************************************************************/
#dffinf LOAD_INSERT_STORE_U8_A8(dibnnfld)                       \
  sd = *sp++;                                                   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld

/***************************************************************/
#dffinf LOAD_INSERT_STORE_U8(dibnnfld)                          \
  vis_blignbddr((void *)0, off);                                \
  sd0 = sd1;                                                    \
  sd1 = *sp++;                                                  \
  sd  = vis_fbligndbtb(sd0, sd1);                               \
  vis_blignbddr((void *)0, 1);                                  \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;   \
  vis_st_u8(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D1X8(donst mlib_u8 *srd,
                                            mlib_u8       *dst,
                                            mlib_s32      dsizf,
                                            mlib_s32      dmbsk)
{
  mlib_u8 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  vis_blignbddr((void *)0, 1);              /* for 1-bytf lfft siift */

  sp = (mlib_d64 *) srd;
  db = dst + (2 / dmbsk);                   /* 4,2,1 -> 0,1,2 */

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 8; i++) {
    LOAD_INSERT_STORE_U8_A8(3);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_U8_13_A8D2X8(donst mlib_u8 *srd,
                                            mlib_s32      slb,
                                            mlib_u8       *dst,
                                            mlib_s32      dlb,
                                            mlib_s32      xsizf,
                                            mlib_s32      ysizf,
                                            mlib_s32      dmbsk)
{
  mlib_u8 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  vis_blignbddr((void *)0, 1);

  sp = sl = (mlib_d64 *) srd;
  db = dl = dst + (2 / dmbsk);              /* 4,2,1 -> 0,1,2 */

  for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 8; i++) {
      LOAD_INSERT_STORE_U8_A8(3);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_U8_13_D1(donst mlib_u8 *srd,
                                        mlib_u8       *dst,
                                        mlib_s32      dsizf,
                                        mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dfnd;                                      /* fnd point in dfstinbtion */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt points in srd */
  mlib_d64 sd0, sd1, sd;                              /* 8-bytf rfgistfrs for sourdf dbtb */
  mlib_s32 off;                                       /* offsft of bddrfss blignmfnt in srd */
  mlib_s32 i;

  /* prfpbrf tif srd bddrfss */
  sb = (void *)srd;
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  /* prfpbrf tif dst bddrfss */
  db = dst + (2 / dmbsk);                   /* 4,2,1 -> 0,1,2 */
  dfnd = db + dsizf * 3 - 1;

  sd1 = *sp++;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 8; i++) {
    LOAD_INSERT_STORE_U8(3);
  }

  /* rigit fnd ibndling */
  if ((mlib_bddr) db <= (mlib_bddr) dfnd) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 1);
    vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
    db += 3;
    if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
      vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
      db += 3;
      if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
        vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
        db += 3;
        if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
          vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
          db += 3;
          if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
            vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
            db += 3;
            if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
              vis_st_u8(sd = vis_fbligndbtb(sd, sd), db);
              db += 3;
              if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
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
void mlib_v_ImbgfCibnnflInsfrt_U8_13(donst mlib_u8 *srd,
                                     mlib_s32      slb,
                                     mlib_u8       *dst,
                                     mlib_s32      dlb,
                                     mlib_s32      xsizf,
                                     mlib_s32      ysizf,
                                     mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_U8_13_D1(sb, db, xsizf, dmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf INSERT_U8_14(sd0, dd0, dd1, dd2, dd3)                   \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd0));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd0));        \
  dd0 = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  dd1 = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd2 = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  dd3 = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb))

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D1X8(donst mlib_u8 *srd,
                                            mlib_u8       *dst,
                                            mlib_s32      dsizf,
                                            mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0;
  mlib_d64 sdb, sdb;
  mlib_d64 dd0, dd1, dd2, dd3;
  mlib_s32 bmbsk;
  mlib_s32 i;

  bmbsk = dmbsk | (dmbsk << 4);

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 8; i++) {
    sd0 = *sp++;
    INSERT_U8_14(sd0, dd0, dd1, dd2, dd3);
    vis_pst_8(dd0, dp++, bmbsk);
    vis_pst_8(dd1, dp++, bmbsk);
    vis_pst_8(dd2, dp++, bmbsk);
    vis_pst_8(dd3, dp++, bmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_U8_14_A8D2X8(donst mlib_u8 *srd,
                                            mlib_s32      slb,
                                            mlib_u8       *dst,
                                            mlib_s32      dlb,
                                            mlib_s32      xsizf,
                                            mlib_s32      ysizf,
                                            mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0;
  mlib_d64 sdb, sdb;
  mlib_d64 dd0, dd1, dd2, dd3;
  mlib_s32 bmbsk;
  mlib_s32 i, j;

  bmbsk = dmbsk | (dmbsk << 4);

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 8; i++) {
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
void mlib_v_ImbgfCibnnflInsfrt_U8_14_D1(donst mlib_u8 *srd,
                                        mlib_u8       *dst,
                                        mlib_s32      dsizf,
                                        mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dfnd, *dfnd2;                              /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1, sd;                              /* 8-bytf sourdf dbtb */
  mlib_d64 sdb, sdb;
  mlib_d64 dd0, dd1, dd2, dd3, dd4;
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 bmbsk;                                     /* dibnnfl mbsk */
  mlib_s32 i, n;

  sb = (void *)srd;
  db = dst;

  bmbsk = dmbsk | (dmbsk << 4) | (dmbsk << 8);

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf * 4 - 1;
  dfnd2 = dfnd - 31;

  bmbsk = (bmbsk >> (doff % 4)) & 0xff;

  if (doff == 0) {                          /* dst is 8-bytf blignfd */

    vis_blignbddr((void *)0, soff);
    sd0 = *sp++;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);          /* tif intfrmfdibtf is blignfd */

    INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

    fmbsk = vis_fdgf8(db, dfnd);
    vis_pst_8(dd0, dp++, fmbsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) { /* for vfry smbll sizf */
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(dd1, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(dd2, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(dd3, dp++, fmbsk & bmbsk);
        }
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
      n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 32 + 1;

      /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

    /* fnd point ibndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      sd0 = sd1;
      sd1 = *sp++;
      sd = vis_fbligndbtb(sd0, sd1);

      INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(dd0, dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(dd1, dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(dd2, dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(dd3, dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
  flsf {                                    /* dst is not 8-bytf blignfd */
    vis_blignbddr((void *)0, soff);
    sd0 = *sp++;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);          /* tif intfrmfdibtf is blignfd */

    INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

    vis_blignbddr((void *)0, -doff);

    fmbsk = vis_fdgf8(db, dfnd);
    vis_pst_8(vis_fbligndbtb(dd0, dd0), dp++, fmbsk & bmbsk);
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) { /* for vfry smbll sizf */
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
        }
      }
    }

    if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
      n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 32 + 1;

      /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

    /* fnd point ibndling */
    if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
      dd4 = dd3;

      vis_blignbddr((void *)0, soff);
      sd0 = sd1;
      sd1 = *sp++;
      sd = vis_fbligndbtb(sd0, sd1);

      INSERT_U8_14(sd, dd0, dd1, dd2, dd3);

      vis_blignbddr((void *)0, -doff);
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_fbligndbtb(dd4, dd0), dp++, fmbsk & bmbsk);
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk & bmbsk);
        if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
          fmbsk = vis_fdgf8(dp, dfnd);
          vis_pst_8(vis_fbligndbtb(dd1, dd2), dp++, fmbsk & bmbsk);
          if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
            fmbsk = vis_fdgf8(dp, dfnd);
            vis_pst_8(vis_fbligndbtb(dd2, dd3), dp++, fmbsk & bmbsk);
          }
        }
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_U8_14(donst mlib_u8 *srd,
                                     mlib_s32      slb,
                                     mlib_u8       *dst,
                                     mlib_s32      dlb,
                                     mlib_s32      xsizf,
                                     mlib_s32      ysizf,
                                     mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_U8_14_D1(sb, db, xsizf, dmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf LOAD_INSERT_STORE_S16_1X_A8(dibnnfld)                   \
  sd  = *sp++;                                                  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld

/***************************************************************/
#dffinf LOAD_INSERT_STORE_S16_1X(dibnnfld)                      \
  vis_blignbddr((void *)0, off);                                \
  sd0 = sd1;                                                    \
  sd1 = *sp++;                                                  \
  sd  = vis_fbligndbtb(sd0, sd1);                               \
  vis_blignbddr((void *)0, 2);                                  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld;  \
  vis_st_u16(sd = vis_fbligndbtb(sd, sd), db); db += dibnnfld

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D1X4(donst mlib_s16 *srd,
                                             mlib_s16       *dst,
                                             mlib_s32       dsizf,
                                             mlib_s32       dmbsk)
{
  mlib_s16 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  db = dst + (2 - dmbsk);                   /* 2,1 -> 0,1 */

  vis_blignbddr((void *)0, 2);

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_1X_A8(2);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_12_A8D2X4(donst mlib_s16 *srd,
                                             mlib_s32       slb,
                                             mlib_s16       *dst,
                                             mlib_s32       dlb,
                                             mlib_s32       xsizf,
                                             mlib_s32       ysizf,
                                             mlib_s32       dmbsk)
{
  mlib_s16 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  db = dl = dst + (2 - dmbsk);              /* 2,1 -> 0,1 */

  vis_blignbddr((void *)0, 2);

  for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 4; i++) {
      LOAD_INSERT_STORE_S16_1X_A8(2);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_12_D1(donst mlib_s16 *srd,
                                         mlib_s16       *dst,
                                         mlib_s32       dsizf,
                                         mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dfnd;                                     /* fnd point in dfstinbtion */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt points in srd */
  mlib_d64 sd0, sd1, sd;                              /* 8-bytf rfgistfrs for sourdf dbtb */
  mlib_s32 off;                                       /* offsft of bddrfss blignmfnt in srd */
  mlib_s32 i;

  sb = (void *)srd;
  db = dst + (2 - dmbsk);                   /* 2,1 -> 0,1 */

  /* prfpbrf tif srd bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  dfnd = db + dsizf * 2 - 1;

  sd1 = *sp++;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_1X(2);
  }

  /* rigit fnd ibndling */
  if ((mlib_bddr) db <= (mlib_bddr) dfnd) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 2);
    vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
    db += 2;
    if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
      vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      db += 2;
      if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
        vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_12(donst mlib_s16 *srd,
                                      mlib_s32       slb,
                                      mlib_s16       *dst,
                                      mlib_s32       dlb,
                                      mlib_s32       xsizf,
                                      mlib_s32       ysizf,
                                      mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_S16_12_D1(sb, db, xsizf, dmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D1X4(donst mlib_s16 *srd,
                                             mlib_s16       *dst,
                                             mlib_s32       dsizf,
                                             mlib_s32       dmbsk)
{
  mlib_s16 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  db = dst + (2 / dmbsk);                   /* 4,2,1 -> 0,1,2 */

  vis_blignbddr((void *)0, 2);

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_1X_A8(3);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_13_A8D2X4(donst mlib_s16 *srd,
                                             mlib_s32       slb,
                                             mlib_s16       *dst,
                                             mlib_s32       dlb,
                                             mlib_s32       xsizf,
                                             mlib_s32       ysizf,
                                             mlib_s32       dmbsk)
{
  mlib_s16 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  db = dl = dst + (2 / dmbsk);              /* 4,2,1 -> 0,1,2 */

  vis_blignbddr((void *)0, 2);

  for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 4; i++) {
      LOAD_INSERT_STORE_S16_1X_A8(3);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_13_D1(donst mlib_s16 *srd,
                                         mlib_s16       *dst,
                                         mlib_s32       dsizf,
                                         mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dfnd;                                     /* fnd point in dfstinbtion */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt points in srd */
  mlib_d64 sd0, sd1, sd;                              /* 8-bytf rfgistfrs for sourdf dbtb */
  mlib_s32 off;                                       /* offsft of bddrfss blignmfnt in srd */
  mlib_s32 i;

  sb = (void *)srd;
  db = dst + (2 / dmbsk);                   /* 4,2,1 -> 0,1,2 */

  /* prfpbrf tif srd bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  dfnd = db + dsizf * 3 - 1;

  sd1 = *sp++;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_1X(3);
  }

  /* rigit fnd ibndling */
  if ((mlib_bddr) db <= (mlib_bddr) dfnd) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 2);
    vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
    db += 3;
    if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
      vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      db += 3;
      if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
        vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_13(donst mlib_s16 *srd,
                                      mlib_s32       slb,
                                      mlib_s16       *dst,
                                      mlib_s32       dlb,
                                      mlib_s32       xsizf,
                                      mlib_s32       ysizf,
                                      mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_S16_13_D1(sb, db, xsizf, dmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
#dffinf INSERT_S16_14(sp, dp, bmbsk)    /* dibnnfl duplidbtf */ \
  /* obsolftf: it is slowfr tibn tif vis_st_u16() vfrsion*/     \
  sd0 = *sp++;                                                  \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd0));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd0));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd0 = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd));        \
  dd1 = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd));        \
  dd2 = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_lo(sdf));        \
  dd3 = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_lo(sdf));        \
  vis_pst_16(dd0, dp++, bmbsk);                                 \
  vis_pst_16(dd1, dp++, bmbsk);                                 \
  vis_pst_16(dd2, dp++, bmbsk);                                 \
  vis_pst_16(dd3, dp++, bmbsk)

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D1X4(donst mlib_s16 *srd,
                                             mlib_s16       *dst,
                                             mlib_s32       dsizf,
                                             mlib_s32       dmbsk)
{
  mlib_s16 *db;
  mlib_d64 *sp;
  mlib_d64 sd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  db = dst + (6 / dmbsk + 1) / 2;           /* 8,4,2,1 -> 0,1,2,3 */

  vis_blignbddr((void *)0, 2);

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_1X_A8(4);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_14_A8D2X4(donst mlib_s16 *srd,
                                             mlib_s32       slb,
                                             mlib_s16       *dst,
                                             mlib_s32       dlb,
                                             mlib_s32       xsizf,
                                             mlib_s32       ysizf,
                                             mlib_s32       dmbsk)
{
  mlib_s16 *db, *dl;
  mlib_d64 *sp, *sl;
  mlib_d64 sd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  db = dl = dst + (6 / dmbsk + 1) / 2;      /* 8,4,2,1 -> 0,1,2,3 */

  vis_blignbddr((void *)0, 2);

  for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
    for (i = 0; i < xsizf / 4; i++) {
      LOAD_INSERT_STORE_S16_1X_A8(4);
    }

    sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_14_D1(donst mlib_s16 *srd,
                                         mlib_s16       *dst,
                                         mlib_s32       dsizf,
                                         mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dfnd;                                     /* fnd point in dfstinbtion */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt points in srd */
  mlib_d64 sd0, sd1, sd;                              /* 8-bytf rfgistfrs for sourdf dbtb */
  mlib_s32 off;                                       /* offsft of bddrfss blignmfnt in srd */
  mlib_s32 i;

  sb = (void *)srd;
  db = dst + (6 / dmbsk + 1) / 2;           /* 8,4,2,1 -> 0,1,2,3 */

  /* prfpbrf tif srd bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  off = (mlib_bddr) sb & 7;

  dfnd = db + dsizf * 4 - 1;

  sd1 = *sp++;

#prbgmb pipfloop(0)
  for (i = 0; i < dsizf / 4; i++) {
    LOAD_INSERT_STORE_S16_1X(4);
  }

  /* rigit fnd ibndling */
  if ((mlib_bddr) db <= (mlib_bddr) dfnd) {

    vis_blignbddr((void *)0, off);
    sd0 = sd1;
    sd1 = *sp++;
    sd = vis_fbligndbtb(sd0, sd1);

    vis_blignbddr((void *)0, 2);
    vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
    db += 4;
    if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
      vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      db += 4;
      if ((mlib_bddr) db <= (mlib_bddr) dfnd) {
        vis_st_u16(sd = vis_fbligndbtb(sd, sd), db);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflInsfrt_S16_14(donst mlib_s16 *srd,
                                      mlib_s32       slb,
                                      mlib_s16       *dst,
                                      mlib_s32       dlb,
                                      mlib_s32       xsizf,
                                      mlib_s32       ysizf,
                                      mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *sl, *dl;
  mlib_s32 j;

  sb = sl = (void *)srd;
  db = dl = dst;

#prbgmb pipfloop(0)
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflInsfrt_S16_14_D1(sb, db, xsizf, dmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
