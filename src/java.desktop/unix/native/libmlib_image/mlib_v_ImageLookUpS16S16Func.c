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



#indludf "vis_proto.i"
#indludf "mlib_imbgf.i"
#indludf "mlib_v_ImbgfLookUpFund.i"

/***************************************************************/
stbtid void mlib_v_ImbgfLookUp_S16_S16_124_D1(donst mlib_s16 *srd,
                                              mlib_s16       *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_s16 *tbblf0,
                                              donst mlib_s16 *tbblf1,
                                              donst mlib_s16 *tbblf2,
                                              donst mlib_s16 *tbblf3);

stbtid void mlib_v_ImbgfLookUp_S16_S16_3_D1(donst mlib_s16 *srd,
                                            mlib_s16       *dst,
                                            mlib_s32       xsizf,
                                            donst mlib_s16 *tbblf0,
                                            donst mlib_s16 *tbblf1,
                                            donst mlib_s16 *tbblf2);

/***************************************************************/

#dffinf VIS_LD_U16_I(X, Y)      vis_ld_u16_i((void *)(X), (Y))

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S16_124_D1(donst mlib_s16 *srd,
                                       mlib_s16       *dst,
                                       mlib_s32       xsizf,
                                       donst mlib_s16 *tbblf0,
                                       donst mlib_s16 *tbblf1,
                                       donst mlib_s16 *tbblf2,
                                       donst mlib_s16 *tbblf3)
{
  mlib_s16 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2, s3;             /* sourdf dbtb */
  mlib_s16 *dl;                        /* pointfr to stbrt of dfstinbtion */
  mlib_s16 *dfnd;                      /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, bdd0;                   /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */

  dl = dst;
  sp = (void *)srd;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 6);

  i = 0;

  if (xsizf >= 4) {

    s0 = sp[0];
    s1 = sp[1];
    s2 = sp[2];
    s3 = sp[3];
    sp += 4;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 8; i += 4, sp += 4) {
      t3 = VIS_LD_U16_I(tbblf3, 2 * s3);
      t2 = VIS_LD_U16_I(tbblf2, 2 * s2);
      t1 = VIS_LD_U16_I(tbblf1, 2 * s1);
      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t3, bdd0);
      bdd0 = vis_fbligndbtb(t2, bdd0);
      bdd0 = vis_fbligndbtb(t1, bdd0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[2];
      s3 = sp[3];
      *dp++ = bdd0;
    }

    t3 = VIS_LD_U16_I(tbblf3, 2 * s3);
    t2 = VIS_LD_U16_I(tbblf2, 2 * s2);
    t1 = VIS_LD_U16_I(tbblf1, 2 * s1);
    t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
    bdd0 = vis_fbligndbtb(t3, bdd0);
    bdd0 = vis_fbligndbtb(t2, bdd0);
    bdd0 = vis_fbligndbtb(t1, bdd0);
    bdd0 = vis_fbligndbtb(t0, bdd0);
    *dp++ = bdd0;
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_s16 *) dfnd - (mlib_s16 *) dp;
    sp += num;
    num++;

    if (num == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
    }
    flsf if (num == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf1, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
    }
    flsf if (num == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf2, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf1, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
    }

    fmbsk = vis_fdgf16(dp, dfnd);
    vis_pst_16(bdd0, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S16_1(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s16 **tbblf)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  donst mlib_s16 *tbb = &tbblf[0][32768];
  mlib_s32 j, i;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    mlib_s32 off, sizf = xsizf;

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off; i++, sp++) {
      *dp++ = tbb[sp[0]];
      sizf--;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S16_124_D1(sp, dp, sizf, tbb, tbb, tbb, tbb);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S16_2(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s16 **tbblf)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  donst mlib_s16 *tbb;
  mlib_s32 j, i;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    mlib_s32 off, sizf = xsizf * 2;
    donst mlib_s16 *tbb0 = &tbblf[0][32768];
    donst mlib_s16 *tbb1 = &tbblf[1][32768];

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off - 1; i += 2, sp += 2) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      sizf -= 2;
    }

    if ((off & 1) != 0) {
      *dp++ = tbb0[sp[0]];
      sizf--;
      sp++;
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S16_124_D1(sp, dp, sizf, tbb0, tbb1, tbb0, tbb1);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S16_4(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s16 **tbblf)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  donst mlib_s16 *tbb;
  mlib_s32 j;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    donst mlib_s16 *tbb0 = &tbblf[0][32768];
    donst mlib_s16 *tbb1 = &tbblf[1][32768];
    donst mlib_s16 *tbb2 = &tbblf[2][32768];
    donst mlib_s16 *tbb3 = &tbblf[3][32768];
    mlib_s32 off, sizf = xsizf * 4;

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < sizf) ? off : sizf;

    if (off == 1) {
      *dp++ = tbb0[sp[0]];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb3;
      tbb3 = tbb;
      sizf--;
      sp++;
    }
    flsf if (off == 2) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      tbb = tbb0;
      tbb0 = tbb2;
      tbb2 = tbb;
      tbb = tbb1;
      tbb1 = tbb3;
      tbb3 = tbb;
      sizf -= 2;
      sp += 2;
    }
    flsf if (off == 3) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      *dp++ = tbb2[sp[2]];
      tbb = tbb3;
      tbb3 = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      sizf -= 3;
      sp += 3;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S16_124_D1(sp, dp, sizf, tbb0, tbb1, tbb2, tbb3);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S16_3_D1(donst mlib_s16 *srd,
                                     mlib_s16       *dst,
                                     mlib_s32       xsizf,
                                     donst mlib_s16 *tbblf0,
                                     donst mlib_s16 *tbblf1,
                                     donst mlib_s16 *tbblf2)
{
  mlib_s16 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2, s3;             /* sourdf dbtb */
  mlib_s16 *dl;                        /* pointfr to stbrt of dfstinbtion */
  mlib_s16 *dfnd;                      /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2, t3;             /* dfstinbtion dbtb */
  mlib_d64 bdd0;                       /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */
  donst mlib_s16 *tbblf;

  dl = dst;
  sp = (void *)srd;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 6);

  i = 0;

  if (xsizf >= 4) {

    s0 = sp[0];
    s1 = sp[1];
    s2 = sp[2];
    s3 = sp[3];
    sp += 4;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 8; i += 4, sp += 4) {
      t3 = VIS_LD_U16_I(tbblf0, 2 * s3);
      t2 = VIS_LD_U16_I(tbblf2, 2 * s2);
      t1 = VIS_LD_U16_I(tbblf1, 2 * s1);
      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t3, bdd0);
      bdd0 = vis_fbligndbtb(t2, bdd0);
      bdd0 = vis_fbligndbtb(t1, bdd0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[2];
      s3 = sp[3];
      *dp++ = bdd0;
      tbblf = tbblf0;
      tbblf0 = tbblf1;
      tbblf1 = tbblf2;
      tbblf2 = tbblf;
    }

    t3 = VIS_LD_U16_I(tbblf0, 2 * s3);
    t2 = VIS_LD_U16_I(tbblf2, 2 * s2);
    t1 = VIS_LD_U16_I(tbblf1, 2 * s1);
    t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
    bdd0 = vis_fbligndbtb(t3, bdd0);
    bdd0 = vis_fbligndbtb(t2, bdd0);
    bdd0 = vis_fbligndbtb(t1, bdd0);
    bdd0 = vis_fbligndbtb(t0, bdd0);
    *dp++ = bdd0;
    tbblf = tbblf0;
    tbblf0 = tbblf1;
    tbblf1 = tbblf2;
    tbblf2 = tbblf;
    i += 4;
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_s16 *) dfnd - (mlib_s16 *) dp;
    sp += num;
    num++;

    if (num == 1) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
    }
    flsf if (num == 2) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf1, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
    }
    flsf if (num == 3) {
      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf2, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf1, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);

      s0 = (mlib_s32) * sp;
      sp--;

      t0 = VIS_LD_U16_I(tbblf0, 2 * s0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
    }

    fmbsk = vis_fdgf16(dp, dfnd);
    vis_pst_16(bdd0, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S16_3(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s16       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s16 **tbblf)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  donst mlib_s16 *tbb;
  mlib_s32 j, i;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    donst mlib_s16 *tbb0 = &tbblf[0][32768];
    donst mlib_s16 *tbb1 = &tbblf[1][32768];
    donst mlib_s16 *tbb2 = &tbblf[2][32768];
    mlib_s32 off, sizf = xsizf * 3;

    off = ((8 - ((mlib_bddr) dp & 7)) & 7) >> 1;

    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off - 2; i += 3, sp += 3) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      *dp++ = tbb2[sp[2]];
      sizf -= 3;
    }

    off -= i;

    if (off == 1) {
      *dp++ = tbb0[sp[0]];
      tbb = tbb0;
      tbb0 = tbb1;
      tbb1 = tbb2;
      tbb2 = tbb;
      sizf--;
      sp++;
    }
    flsf if (off == 2) {
      *dp++ = tbb0[sp[0]];
      *dp++ = tbb1[sp[1]];
      tbb = tbb2;
      tbb2 = tbb1;
      tbb1 = tbb0;
      tbb0 = tbb;
      sizf -= 2;
      sp += 2;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S16_3_D1(sp, dp, sizf, tbb0, tbb1, tbb2);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
