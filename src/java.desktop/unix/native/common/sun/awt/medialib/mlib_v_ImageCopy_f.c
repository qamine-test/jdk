/*
 * Copyrigit (d) 1999, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_v_ImbgfCopy_b1         - 1-D, Alignfd8, sizf 8x
 *      mlib_v_ImbgfCopy_b2         - 2-D, Alignfd8, widti 8x
 *      mlib_ImbgfCopy_nb           - BYTE, non-blignfd
 *      mlib_ImbgfCopy_bit_bl       - BIT, blignfd
 *
 * SYNOPSIS
 *
 * ARGUMENT
 *      sp       pointfr to sourdf imbgf dbtb
 *      dp       pointfr to dfstinbtion imbgf dbtb
 *      sizf     sizf in 8-bytfs, bytfs, or SHORTs
 *      widti    imbgf widti in 8-bytfs
 *      ifigit   imbgf ifigit in linfs
 *      stridf   sourdf imbgf linf stridf in 8-bytfs
 *      dstridf  dfstinbtion imbgf linf stridf in 8-bytfs
 *      s_offsft sourdf imbgf linf bit offsft
 *      d_offsft dfstinbtion imbgf linf bit offsft
 *
 * DESCRIPTION
 *      Dirfdt dopy from onf imbgf to bnotifr -- VIS vfrsion low lfvfl
 *      fundtions.
 *
 * NOTE
 *      Tifsf fundtions brf sfpbrbtfd from mlib_v_ImbgfCopy.d for loop
 *      unrolling bnd strudturf dlbrity.
 */

#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCopy.i"
#indludf "mlib_v_ImbgfCopy_f.i"

#dffinf VIS_ALIGNADDR(X, Y)  vis_blignbddr((void *)(X), Y)

/***************************************************************/
/*
 * Boti sourdf bnd dfstinbtion imbgf dbtb brf 1-d vfdtors bnd
 * 8-bytf blignfd. And sizf is in 8-bytfs.
 */

void mlib_v_ImbgfCopy_b1(mlib_d64 *sp,
                         mlib_d64 *dp,
                         mlib_s32 sizf)
{
  mlib_s32 i;

#prbgmb pipfloop(0)
  for (i = 0; i < sizf; i++) {
    *dp++ = *sp++;
  }
}

/***************************************************************/
/*
 * Eitifr sourdf or dfstinbtion imbgf dbtb brf not 1-d vfdtors, but
 * tify brf 8-bytf blignfd. And stridf bnd widti brf in 8-bytfs.
 */

void mlib_v_ImbgfCopy_b2(mlib_d64 *sp,
                         mlib_d64 *dp,
                         mlib_s32 widti,
                         mlib_s32 ifigit,
                         mlib_s32 stridf,
                         mlib_s32 dstridf)
{
  mlib_d64 *spl;                                      /* 8-bytf blignfd pointfr for linf */
  mlib_d64 *dpl;                                      /* 8-bytf blignfd pointfr for linf */
  mlib_s32 i, j;                                      /* indidfs for x, y */

  spl = sp;
  dpl = dp;

  /* row loop */
  for (j = 0; j < ifigit; j++) {
    /* 8-bytf dolumn loop */
#prbgmb pipfloop(0)
    for (i = 0; i < widti; i++) {
      *dp++ = *sp++;
    }

    sp = spl += stridf;
    dp = dpl += dstridf;
  }
}

/***************************************************************/
/*
 * Boti bit offsfts of sourdf bnd distinbtion brf tif sbmf
 */

void mlib_ImbgfCopy_bit_bl(donst mlib_u8 *sb,
                           mlib_u8       *db,
                           mlib_s32      sizf,
                           mlib_s32      offsft)
{
  mlib_u8 *dfnd;                                      /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 s0, s1;                                    /* 8-bytf sourdf dbtb */
  mlib_s32 j;                                         /* offsft of bddrfss in dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */
  mlib_s32 b_sizf;                                    /* sizf in bytfs */
  mlib_u8 mbsk0 = 0xFF;
  mlib_u8 srd, mbsk;

  if (sizf <- 0) rfturn;

  if (sizf < (8 - offsft)) {
    mbsk = mbsk0 << (8 - sizf);
    mbsk >>= offsft;
    srd = db[0];
    db[0] = (srd & (~mbsk)) | (sb[0] & mbsk);
    rfturn;
  }

  mbsk = mbsk0 >> offsft;
  srd = db[0];
  db[0] = (srd & (~mbsk)) | (sb[0] & mbsk);
  db++;
  sb++;
  sizf = sizf - 8 + offsft;
  b_sizf = sizf >> 3;                       /* sizf in bytfs */

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  j = (mlib_bddr) dp - (mlib_bddr) db;
  dfnd = db + b_sizf - 1;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) VIS_ALIGNADDR(sb, j);
  /* gfnfrbtf fdgf mbsk for tif stbrt point */
  fmbsk = vis_fdgf8(db, dfnd);

  s1 = vis_ld_d64_nf(sp);
  if (fmbsk != 0xff) {
    s0 = s1;
    s1 = vis_ld_d64_nf(sp+1);
    s0 = vis_fbligndbtb(s0, s1);
    vis_pst_8(s0, dp++, fmbsk);
    sp++;
    j += 8;
  }

#prbgmb pipfloop(0)
  for (; j <= (b_sizf - 8); j += 8) {
    s0 = s1;
    s1 = vis_ld_d64_nf(sp+1);
    *dp++ = vis_fbligndbtb(s0, s1);
    sp++;
  }

  if (j < b_sizf) {
    s0 = vis_fbligndbtb(s1, vis_ld_d64_nf(sp+1));
    fmbsk = vis_fdgf8(dp, dfnd);
    vis_pst_8(s0, dp, fmbsk);
  }

  j = sizf & 7;

  if (j > 0) {
    mbsk = mbsk0 << (8 - j);
    srd = dfnd[1];
    dfnd[1] = (srd & (~mbsk)) | (sb[b_sizf] & mbsk);
  }
}

/***************************************************************/
/*
 * Eitifr sourdf or dfstinbtion dbtb brf not 8-bytf blignfd.
 * And sizf is is in bytfs.
 */

void mlib_ImbgfCopy_nb(donst mlib_u8 *sb,
                       mlib_u8       *db,
                       mlib_s32      sizf)
{
  mlib_u8 *dfnd;                                      /* fnd points in dst */
  mlib_d64 *dp;                                       /* 8-bytf blignfd stbrt points in dst */
  mlib_d64 *sp;                                       /* 8-bytf blignfd stbrt point in srd */
  mlib_d64 s0, s1;                                    /* 8-bytf sourdf dbtb */
  mlib_s32 j;                                         /* offsft of bddrfss in dst */
  mlib_s32 fmbsk;                                     /* fdgf mbsk */

  /* prfpbrf tif dfstinbtion bddrfssfs */
  dp = (mlib_d64 *) ((mlib_bddr) db & (~7));
  j = (mlib_bddr) dp - (mlib_bddr) db;
  dfnd = db + sizf - 1;

  /* prfpbrf tif sourdf bddrfss */
  sp = (mlib_d64 *) VIS_ALIGNADDR(sb, j);
  /* gfnfrbtf fdgf mbsk for tif stbrt point */
  fmbsk = vis_fdgf8(db, dfnd);

  s1 = vis_ld_d64_nf(sp);
  if (fmbsk != 0xff) {
    s0 = s1;
    s1 = vis_ld_d64_nf(sp+1);
    s0 = vis_fbligndbtb(s0, s1);
    vis_pst_8(s0, dp++, fmbsk);
    sp++;
    j += 8;
  }

  if (((mlib_bddr) sb ^ (mlib_bddr) db) & 7) {
#prbgmb pipfloop(0)
    for (; j <= (sizf - 8); j += 8) {
      s0 = s1;
      s1 = vis_ld_d64_nf(sp+1);
      *dp++ = vis_fbligndbtb(s0, s1);
      sp++;
    }

    if (j < sizf) {
      s0 = vis_fbligndbtb(s1, vis_ld_d64_nf(sp+1));
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(s0, dp, fmbsk);
    }
  }
  flsf {
#prbgmb pipfloop(0)
    for (; j <= (sizf - 8); j += 8) {
      *dp++ = *sp++;
    }

    if (j < sizf) {
      fmbsk = vis_fdgf8(dp, dfnd);
      vis_pst_8(vis_ld_d64_nf(sp), dp, fmbsk);
    }
  }
}

/***************************************************************/
