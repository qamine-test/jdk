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
 * FILENAME: mlib_ImbgfCibnnflExtrbdt_1.d
 *
 * FUNCTIONS
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_21_A8D1X8
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_21_A8D2X8
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_21_D1
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_21
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_31_A8D1X8
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_31_A8D2X8
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_31_D1
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_31
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_41_A8D1X8
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_41_A8D2X8
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_41_D1
 *      mlib_v_ImbgfCibnnflExtrbdt_U8_41
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_21_A8D1X4
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_21_A8D2X4
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_21_D1
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_21
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_31_A8D1X4
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_31_A8D2X4
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_31_D1
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_31
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_41_A8D1X4
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_41_A8D2X4
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_41_D1
 *      mlib_v_ImbgfCibnnflExtrbdt_S16_41
 *
 * ARGUMENT
 *      srd    pointfr to sourdf imbgf dbtb
 *      dst    pointfr to dfstinbtion imbgf dbtb
 *      slb    sourdf imbgf linf stridf in bytfs
 *      dlb   dfstinbtion imbgf linf stridf in bytfs
 *      dsizf  imbgf dbtb sizf in pixfls
 *      xsizf  imbgf widti in pixfls
 *      ysizf  imbgf ifigit in linfs
 *      dmbsk dibnnfl mbsk
 *
 * DESCRIPTION
 *      Extrbdt tif onf sflfdtfd dibnnfl of tif sourdf imbgf into tif
 *      1-dibnnfl dfstinbtion imbgf.
 *
 * NOTE
 *      Tifsf fundtions brf sfpbrbtfd from mlib_ImbgfCibnnflExtrbdt.d
 *      for loop unrolling bnd strudturf dlbrity.
 */

#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_v_ImbgfCibnnflExtrbdt.i"

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_21L(sd0, sd1, dd)                     \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd1));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_ii(sdd))

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_21R(sd0, sd1, dd)                     \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd1));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_lo(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
/* fxtrbdt onf dibnnfl from b 2-dibnnfl imbgf.
 * boti sourdf bnd dfstinbtion imbgf dbtb brf 8-bytf blignfd.
 * xsizf is multiplf of 8.
 */

void mlib_v_ImbgfCibnnflExtrbdt_U8_21_A8D1X8(donst mlib_u8 *srd,
                                             mlib_u8       *dst,
                                             mlib_s32      dsizf,
                                             mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdd, sdd;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

  if (dmbsk == 2) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_U8_21L(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
  flsf {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_U8_21R(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
/* fxtrbdt onf dibnnfl from b 2-dibnnfl imbgf.
 * boti sourdf bnd dfstinbtion imbgf dbtb brf 8-bytf blignfd.
 * xsizf is multiplf of 8.
 */

void mlib_v_ImbgfCibnnflExtrbdt_U8_21_A8D2X8(donst mlib_u8 *srd,
                                             mlib_s32      slb,
                                             mlib_u8       *dst,
                                             mlib_s32      dlb,
                                             mlib_s32      xsizf,
                                             mlib_s32      ysizf,
                                             mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdd, sdd;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  if (dmbsk == 2) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_U8_21L(sd0, sd1, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  flsf {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
/* fxtrbdt onf dibnnfl from b 2-dibnnfl imbgf.
 */

void mlib_v_ImbgfCibnnflExtrbdt_U8_21_D1(donst mlib_u8 *srd,
                                         mlib_u8       *dst,
                                         mlib_s32      dsizf,
                                         mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dfnd, *dfnd2;                              /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-bytf sourdf dbtb */
  mlib_d64 sdb, sdb, sdd, sdd;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 off;                                       /* offsft of srd ovfr dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 i, n;

  sb = (void *)srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf - 1;
  dfnd2 = dfnd - 7;

  /* dbldulbtf tif srd's offsft ovfr dst */
  if (dmbsk == 2) {
    off = soff / 2 - doff;
  }
  flsf {
    off = (soff + 1) / 2 - doff;
  }

  if (((dmbsk == 2) && (soff % 2 == 0)) || ((dmbsk == 1) && (soff % 2 != 0))) { /* fxtrbdt fvfn bytfs */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 16 bytfs */
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 32 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_21L(sd0, sd1, dd0);
        CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_21L(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf {                                    /* fxtrbdt odd bytfs */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs, don't dbrf tif gbrbbgf bt tif stbrt point */
      sd0 = *sp++;
      sd1 = *sp++;

      /* fxtrbdt bnd storf 8 bytfs */
      CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 16 bytfs */
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 32 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_21R(sd0, sd1, dd0);
        CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_21R(sd2, sd3, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
}

/***************************************************************/
/* fxtrbdt onf dibnnfl from b 2-dibnnfl imbgf.
 */

void mlib_v_ImbgfCibnnflExtrbdt_U8_21(donst mlib_u8 *srd,
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

  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflExtrbdt_U8_21_D1(sb, db, xsizf, dmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd)                \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));        \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_ii(sdd));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdf))

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd)                \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));        \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdd));        \
  dd  = vis_fpmfrgf(vis_rfbd_lo(sdd), vis_rfbd_ii(sdf))

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd)                \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_ii(sdd));        \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdd));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdf))

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_U8_31_A8D1X8(donst mlib_u8 *srd,
                                             mlib_u8       *dst,
                                             mlib_s32      dsizf,
                                             mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdd, sdd, sdf;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

  if (dmbsk == 4) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  flsf if (dmbsk == 2) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  flsf {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_U8_31_A8D2X8(donst mlib_u8 *srd,
                                             mlib_s32      slb,
                                             mlib_u8       *dst,
                                             mlib_s32      dlb,
                                             mlib_s32      xsizf,
                                             mlib_s32      ysizf,
                                             mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdd, sdd, sdf;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  if (dmbsk == 4) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
  flsf if (dmbsk == 2) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
  flsf {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
void mlib_v_ImbgfCibnnflExtrbdt_U8_31_D1(donst mlib_u8 *srd,
                                         mlib_u8       *dst,
                                         mlib_s32      dsizf,
                                         mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dfnd, *dfnd2;                              /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1, sd2;                             /* 8-bytf sourdf dbtb */
  mlib_d64 sd3, sd4, sd5;
  mlib_d64 sdb, sdb, sdd, sdd, sdf;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 off;                                       /* offsft of srd ovfr dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 i, n;

  sb = (void *)srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf - 1;
  dfnd2 = dfnd - 7;

  /* dbldulbtf tif srd's offsft ovfr dst */
  if (dmbsk == 4) {
    off = soff / 3 - doff;
  }
  flsf if (dmbsk == 2) {
    off = (soff + 1) / 3 - doff;
  }
  flsf {
    off = (soff + 2) / 3 - doff;
  }

  if (((dmbsk == 4) && (soff % 3 == 0)) ||
      ((dmbsk == 2) && (soff % 3 == 2)) ||
      ((dmbsk == 1) && (soff % 3 == 1))) { /* fxtrbdt lfft dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_31L(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_U8_31L(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf if (((dmbsk == 4) && (soff % 3 == 1)) ||
           ((dmbsk == 2) && (soff % 3 == 0)) ||
           ((dmbsk == 1) && (soff % 3 == 2))) {
    /* fxtrbdt middlf dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_31M(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_U8_31M(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf {                                    /* fxtrbdt rigit dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_31R(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_U8_31R(sd3, sd4, sd5, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_U8_31(donst mlib_u8 *srd,
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

  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflExtrbdt_U8_31_D1(sb, db, xsizf, dmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd)           \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_ii(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sd1), vis_rfbd_lo(sd3));        \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdd));        \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdd));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_ii(sdf))

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd)          \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_ii(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sd1), vis_rfbd_lo(sd3));        \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdd));        \
  sdf = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdd));        \
  dd  = vis_fpmfrgf(vis_rfbd_lo(sdf), vis_rfbd_lo(sdf))

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd)          \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_ii(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sd1), vis_rfbd_lo(sd3));        \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdd));        \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdd));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdf), vis_rfbd_ii(sdf))

/***************************************************************/
#dffinf CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd)           \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_ii(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sd1), vis_rfbd_lo(sd3));        \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdd));        \
  sdf = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdd));        \
  dd  = vis_fpmfrgf(vis_rfbd_lo(sdf), vis_rfbd_lo(sdf))

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_U8_41_A8D1X8(donst mlib_u8 *srd,
                                             mlib_u8       *dst,
                                             mlib_s32      dsizf,
                                             mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdd, sdd, sdf, sdf;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

  if (dmbsk == 8) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  flsf if (dmbsk == 4) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  flsf if (dmbsk == 2) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  flsf {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 8; i++) {
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
void mlib_v_ImbgfCibnnflExtrbdt_U8_41_A8D2X8(donst mlib_u8 *srd,
                                             mlib_s32      slb,
                                             mlib_u8       *dst,
                                             mlib_s32      dlb,
                                             mlib_s32      xsizf,
                                             mlib_s32      ysizf,
                                             mlib_s32      dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdd, sdd, sdf, sdf;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  if (dmbsk == 8) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
  flsf if (dmbsk == 4) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
  flsf if (dmbsk == 2) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
  flsf {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 8; i++) {
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
void mlib_v_ImbgfCibnnflExtrbdt_U8_41_D1(donst mlib_u8 *srd,
                                         mlib_u8       *dst,
                                         mlib_s32      dsizf,
                                         mlib_s32      dmbsk)
{
  mlib_u8 *sb, *db;
  mlib_u8 *dfnd, *dfnd2;                              /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-bytf sourdf dbtb */
  mlib_d64 sd4, sd5, sd6, sd7;
  mlib_d64 sdb, sdb, sdd, sdd;
  mlib_d64 sdf, sdf;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 off;                                       /* offsft of srd ovfr dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 i, n;

  sb = (void *)srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf - 1;
  dfnd2 = dfnd - 7;

  /* dbldulbtf tif srd's offsft ovfr dst */
  if (dmbsk == 8) {
    off = soff / 4 - doff;
  }
  flsf if (dmbsk == 4) {
    off = (soff + 1) / 4 - doff;
  }
  flsf if (dmbsk == 2) {
    off = (soff + 2) / 4 - doff;
  }
  flsf {
    off = (soff + 3) / 4 - doff;
  }

  if (((dmbsk == 8) && (soff % 4 == 0)) ||
      ((dmbsk == 4) && (soff % 4 == 3)) ||
      ((dmbsk == 2) && (soff % 4 == 2)) ||
      ((dmbsk == 1) && (soff % 4 == 1))) { /* fxtrbdt lfft dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41L(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf if (((dmbsk == 8) && (soff % 4 == 1)) ||
           ((dmbsk == 4) && (soff % 4 == 0)) ||
           ((dmbsk == 2) && (soff % 4 == 3)) ||
           ((dmbsk == 1) && (soff % 4 == 2))) {
    /* fxtrbdt middlf lfft dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41ML(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf if (((dmbsk == 8) && (soff % 4 == 2)) ||
           ((dmbsk == 4) && (soff % 4 == 1)) ||
           ((dmbsk == 2) && (soff % 4 == 0)) ||
           ((dmbsk == 1) && (soff % 4 == 3))) { /* fxtrbdt middlf rigit dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41MR(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf {                                    /* fxtrbdt rigit dibnnfl */
    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_8(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);
        vis_pst_8(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf8(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_U8_41R(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_U8_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf8(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_U8_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_8(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_U8_41(donst mlib_u8 *srd,
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

  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflExtrbdt_U8_41_D1(sb, db, xsizf, dmbsk);
    sb = sl += slb;
    db = dl += dlb;
  }
}

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_21L(sd0, sd1, dd)                    \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd1));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_21R(sd0, sd1, dd)                    \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd1));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
/* fxtrbdt onf dibnnfl from b 2-dibnnfl imbgf.
 * boti sourdf bnd dfstinbtion imbgf dbtb brf 8-bytf blignfd.
 * dsizf is multiplf of 4.
 */

void mlib_v_ImbgfCibnnflExtrbdt_S16_21_A8D1X4(donst mlib_s16 *srd,
                                              mlib_s16       *dst,
                                              mlib_s32       dsizf,
                                              mlib_s32       dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

  if (dmbsk == 2) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_S16_21L(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
  flsf {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      CHANNELEXTRACT_S16_21R(sd0, sd1, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_S16_21_A8D2X4(donst mlib_s16 *srd,
                                              mlib_s32       slb,
                                              mlib_s16       *dst,
                                              mlib_s32       dlb,
                                              mlib_s32       xsizf,
                                              mlib_s32       ysizf,
                                              mlib_s32       dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  if (dmbsk == 2) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_S16_21L(sd0, sd1, dd);
        *dp++ = dd;
      }

      sp = sl = (mlib_d64 *) ((mlib_u8 *) sl + slb);
      dp = dl = (mlib_d64 *) ((mlib_u8 *) dl + dlb);
    }
  }
  flsf {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
void mlib_v_ImbgfCibnnflExtrbdt_S16_21_D1(donst mlib_s16 *srd,
                                          mlib_s16       *dst,
                                          mlib_s32       dsizf,
                                          mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dfnd, *dfnd2;                             /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-bytf sourdf dbtb */
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 off;                                       /* offsft of dst ovfr srd */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 i, n;

  sb = (void *)srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf - 1;
  dfnd2 = dfnd - 3;

  /* dbldulbtf tif srd's offsft ovfr dst */
  if (dmbsk == 2) {
    off = (soff / 4) * 2 - doff;
  }
  flsf {
    off = ((soff + 3) / 4) * 2 - doff;
  }

  if (((dmbsk == 2) && (soff % 4 == 0)) || ((dmbsk == 1) && (soff % 4 != 0))) { /* fxtrbdt fvfn words */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 16 bytfs */
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 32 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_21L(sd0, sd1, dd0);
        CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_21L(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf {                                    /* fxtrbdt odd words */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs, don't dbrf tif gbrbbgf bt tif stbrt point */
      sd0 = *sp++;
      sd1 = *sp++;

      /* fxtrbdt bnd storf 8 bytfs */
      CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 16 bytfs */
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 32 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_21R(sd0, sd1, dd0);
        CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_21R(sd2, sd3, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_S16_21(donst mlib_s16 *srd,
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

  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflExtrbdt_S16_21_D1(sb, db, xsizf, dmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd)               \
  /* fxtrbdt tif lfft dibnnfl */                                \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd)               \
  /* fxtrbdt tif middlf dibnnfl */                              \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_lo(sd1));        \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_ii(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd)               \
  /* fxtrbdt tif rigit dibnnfl */                               \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_ii(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_lo(sd2));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_lo(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_S16_31_A8D1X4(donst mlib_s16 *srd,
                                              mlib_s16       *dst,
                                              mlib_s32       dsizf,
                                              mlib_s32       dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

  if (dmbsk == 4) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  flsf if (dmbsk == 2) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
  flsf {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd);
      *dp++ = dd;
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_S16_31_A8D2X4(donst mlib_s16 *srd,
                                              mlib_s32       slb,
                                              mlib_s16       *dst,
                                              mlib_s32       dlb,
                                              mlib_s32       xsizf,
                                              mlib_s32       ysizf,
                                              mlib_s32       dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  if (dmbsk == 4) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
  flsf if (dmbsk == 2) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
  flsf {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
void mlib_v_ImbgfCibnnflExtrbdt_S16_31_D1(donst mlib_s16 *srd,
                                          mlib_s16       *dst,
                                          mlib_s32       dsizf,
                                          mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dfnd, *dfnd2;                             /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1, sd2;                             /* 8-bytf sourdf dbtb */
  mlib_d64 sd3, sd4, sd5;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 off;                                       /* offsft of srd ovfr dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 i, n;

  sb = (void *)srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf - 1;
  dfnd2 = dfnd - 3;

  /* dbldulbtf tif srd's offsft ovfr dst */
  if (dmbsk == 4) {
    off = (soff / 6) * 2 - doff;
  }
  flsf if (dmbsk == 2) {
    off = ((soff + 2) / 6) * 2 - doff;
  }
  flsf {
    off = ((soff + 4) / 6) * 2 - doff;
  }

  if (((dmbsk == 4) && (soff % 6 == 0)) ||
      ((dmbsk == 2) && (soff % 6 == 4)) ||
      ((dmbsk == 1) && (soff % 6 == 2))) { /* fxtrbdt lfft dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_31L(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_S16_31L(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf if (((dmbsk == 4) && (soff % 6 == 2)) ||
           ((dmbsk == 2) && (soff % 6 == 0)) ||
           ((dmbsk == 1) && (soff % 6 == 4))) {
    /* fxtrbdt middlf dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_31M(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_S16_31M(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf {                                    /* fxtrbdt rigit dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_31R(sd0, sd1, sd2, dd0);
        CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          dd0 = dd1;
          sd3 = *sp++;
          sd4 = *sp++;
          sd5 = *sp++;
          CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
          *dp++ = vis_fbligndbtb(dd0, dd1);
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        CHANNELEXTRACT_S16_31R(sd3, sd4, sd5, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_S16_31(donst mlib_s16 *srd,
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

  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflExtrbdt_S16_31_D1(sb, db, xsizf, dmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_41L(sd0, sd1,  sd2, sd3, dd)         \
  /* fxtrbdt tif lfft dibnnfl */                                \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_ii(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd)         \
  /* fxtrbdt tif middlf lfft dibnnfl */                         \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd0), vis_rfbd_ii(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_ii(sd1), vis_rfbd_ii(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd)         \
  /* fxtrbdt tif middlf rigit dibnnfl */                        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd1), vis_rfbd_lo(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_ii(sdb), vis_rfbd_ii(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
#dffinf CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd)          \
  /* fxtrbdt tif rigit dibnnfl */                               \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd0), vis_rfbd_lo(sd2));        \
  sdb = vis_fpmfrgf(vis_rfbd_lo(sd1), vis_rfbd_lo(sd3));        \
  sdd = vis_fpmfrgf(vis_rfbd_lo(sdb), vis_rfbd_lo(sdb));        \
  dd  = vis_fpmfrgf(vis_rfbd_ii(sdd), vis_rfbd_lo(sdd))

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_S16_41_A8D1X4(donst mlib_s16 *srd,
                                              mlib_s16       *dst,
                                              mlib_s32       dsizf,
                                              mlib_s32       dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd;
  mlib_s32 i;

  sp = (mlib_d64 *) srd;
  dp = (mlib_d64 *) dst;

  if (dmbsk == 8) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  flsf if (dmbsk == 4) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  flsf if (dmbsk == 2) {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;
      CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd);
      *dp++ = dd;
    }
  }
  flsf {
#prbgmb pipfloop(0)
    for (i = 0; i < dsizf / 4; i++) {
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
void mlib_v_ImbgfCibnnflExtrbdt_S16_41_A8D2X4(donst mlib_s16 *srd,
                                              mlib_s32       slb,
                                              mlib_s16       *dst,
                                              mlib_s32       dlb,
                                              mlib_s32       xsizf,
                                              mlib_s32       ysizf,
                                              mlib_s32       dmbsk)
{
  mlib_d64 *sp, *dp;
  mlib_d64 *sl, *dl;
  mlib_d64 sd0, sd1, sd2, sd3;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd;
  mlib_s32 i, j;

  sp = sl = (mlib_d64 *) srd;
  dp = dl = (mlib_d64 *) dst;

  if (dmbsk == 8) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
  flsf if (dmbsk == 4) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
  flsf if (dmbsk == 2) {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
  flsf {
    for (j = 0; j < ysizf; j++) {
#prbgmb pipfloop(0)
      for (i = 0; i < xsizf / 4; i++) {
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
void mlib_v_ImbgfCibnnflExtrbdt_S16_41_D1(donst mlib_s16 *srd,
                                          mlib_s16       *dst,
                                          mlib_s32       dsizf,
                                          mlib_s32       dmbsk)
{
  mlib_s16 *sb, *db;
  mlib_s16 *dfnd, *dfnd2;                             /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 sd0, sd1, sd2, sd3;                        /* 8-bytf sourdf dbtb */
  mlib_d64 sd4, sd5, sd6, sd7;
  mlib_d64 sdb, sdb, sdd;
  mlib_d64 dd0, dd1;
  mlib_s32 soff;                                      /* offsft of bddrfss in srd */
  mlib_s32 doff;                                      /* offsft of bddrfss in dst */
  mlib_s32 off;                                       /* offsft of srd ovfr dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 i, n;

  sb = (void *)srd;
  db = dst;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) ((mlib_bddr) sb & (~7));
  soff = ((mlib_bddr) sb & 7);

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  doff = ((mlib_bddr) db & 7);
  dfnd = db + dsizf - 1;
  dfnd2 = dfnd - 3;

  /* dbldulbtf tif srd's offsft ovfr dst */
  if (dmbsk == 8) {
    off = (soff / 8) * 2 - doff;
  }
  flsf if (dmbsk == 4) {
    off = ((soff + 2) / 8) * 2 - doff;
  }
  flsf if (dmbsk == 2) {
    off = ((soff + 4) / 8) * 2 - doff;
  }
  flsf {
    off = ((soff + 6) / 8) * 2 - doff;
  }

  if (((dmbsk == 8) && (soff == 0)) ||
      ((dmbsk == 4) && (soff == 6)) ||
      ((dmbsk == 2) && (soff == 4)) ||
      ((dmbsk == 1) && (soff == 2))) { /* fxtrbdt lfft dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_41L(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_S16_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41L(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf if (((dmbsk == 8) && (soff == 2)) ||
           ((dmbsk == 4) && (soff == 0)) ||
           ((dmbsk == 2) && (soff == 6)) ||
           ((dmbsk == 1) && (soff == 4))) { /* fxtrbdt middlf lfft dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_41ML(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_S16_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41ML(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf if (((dmbsk == 8) && (soff == 4)) ||
           ((dmbsk == 4) && (soff == 2)) ||
           ((dmbsk == 2) && (soff == 0)) ||
           ((dmbsk == 1) && (soff == 6))) { /* fxtrbdt middlf rigit dibnnfl */

    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }
      flsf {
        /* lobd 48 bytfs */
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_41MR(sd0, sd1, sd2, sd3, dd0);
        CHANNELEXTRACT_S16_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41MR(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
  flsf {                                    /* fxtrbdt rigit dibnnfl */
    if (off == 0) {                         /* srd bnd dst ibvf sbmf blignmfnt */

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      /* lobd 16 bytfs */
      sd0 = *sp++;
      sd1 = *sp++;
      sd2 = *sp++;
      sd3 = *sp++;

      /* fxtrbdt, indluding somf gbrbbgf bt tif stbrt point */
      CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd0);

      /* storf 8 bytfs rfsult */
      vis_pst_16(dd0, dp++, fmbsk);

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
        for (i = 0; i < n; i++) {
          sd0 = *sp++;
          sd1 = *sp++;
          sd2 = *sp++;
          sd3 = *sp++;
          CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd0);
          *dp++ = dd0;
        }
      }

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        sd0 = *sp++;
        sd1 = *sp++;
        sd2 = *sp++;
        sd3 = *sp++;
        CHANNELEXTRACT_S16_41R(sd0, sd1, sd2, sd3, dd0);
        vis_pst_16(dd0, dp++, fmbsk);
      }
    }
    flsf {
      vis_blignbddr((void *)0, off);

      /* gfnfrbtf fdgf mbsk for tif stbrt point */
      fmbsk = vis_fdgf16(db, dfnd);

      if (off < 0) {
        /* lobd 24 bytfs */
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;

        /* fxtrbdt bnd storf 8 bytfs */
        CHANNELEXTRACT_S16_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd1, dd1), dp++, fmbsk);
      }

      if ((mlib_bddr) dp <= (mlib_bddr) dfnd2) {
        n = ((mlib_u8 *) dfnd2 - (mlib_u8 *) dp) / 8 + 1;

        /* 8-pixfl dolumn loop, fmbsk not nffdfd */
#prbgmb pipfloop(0)
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

      /* fnd point ibndling */
      if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {
        fmbsk = vis_fdgf16(dp, dfnd);
        dd0 = dd1;
        sd4 = *sp++;
        sd5 = *sp++;
        sd6 = *sp++;
        sd7 = *sp++;
        CHANNELEXTRACT_S16_41R(sd4, sd5, sd6, sd7, dd1);
        vis_pst_16(vis_fbligndbtb(dd0, dd1), dp++, fmbsk);
      }
    }
  }
}

/***************************************************************/
void mlib_v_ImbgfCibnnflExtrbdt_S16_41(donst mlib_s16 *srd,
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
  for (j = 0; j < ysizf; j++) {
    mlib_v_ImbgfCibnnflExtrbdt_S16_41_D1(sb, db, xsizf, dmbsk);
    sb = sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    db = dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
