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
stbtid void mlib_v_ImbgfLookUpSI_S16_S16_2_DstA8D1(donst mlib_s16 *srd,
                                                   mlib_s16       *dst,
                                                   mlib_s32       xsizf,
                                                   donst mlib_s16 **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S16_S16_2_D1(donst mlib_s16 *srd,
                                              mlib_s16       *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_s16 **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S16_S16_3_D1(donst mlib_s16 *srd,
                                              mlib_s16       *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_s16 **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff0_D1(donst mlib_s16 *srd,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsizf,
                                                      donst mlib_s16 **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff1_D1(donst mlib_s16 *srd,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsizf,
                                                      donst mlib_s16 **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff2_D1(donst mlib_s16 *srd,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsizf,
                                                      donst mlib_s16 **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff3_D1(donst mlib_s16 *srd,
                                                      mlib_s16       *dst,
                                                      mlib_s32       xsizf,
                                                      donst mlib_s16 **tbblf);

/***************************************************************/
#dffinf VIS_LD_U16_I(X, Y)      vis_ld_u16_i((void *)(X), (Y))

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_2_DstA8D1(donst mlib_s16 *srd,
                                            mlib_s16       *dst,
                                            mlib_s32       xsizf,
                                            donst mlib_s16 **tbblf)
{
  mlib_s16 *sp;              /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1;           /* sourdf dbtb */
  mlib_s16 *dl;              /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;              /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;       /* dfstinbtion dbtb */
  mlib_d64 t3, bdd;          /* dfstinbtion dbtb */
  mlib_s32 i;                /* loop vbribblf */
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];

  sp   = (void *)srd;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  if (xsizf >= 2) {

    s0 = (sp[0] << 1);
    s1 = (sp[1] << 1);
    sp += 2;

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 4; i+=2, sp+=2) {
      t3 = VIS_LD_U16_I(tbb1, s1);
      t2 = VIS_LD_U16_I(tbb0, s1);
      t1 = VIS_LD_U16_I(tbb1, s0);
      t0 = VIS_LD_U16_I(tbb0, s0);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = (sp[0] << 1);
      s1 = (sp[1] << 1);
      *dp++ = bdd;
    }

    t3 = VIS_LD_U16_I(tbb1, s1);
    t2 = VIS_LD_U16_I(tbb0, s1);
    t1 = VIS_LD_U16_I(tbb1, s0);
    t0 = VIS_LD_U16_I(tbb0, s0);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }

  if ((xsizf & 1) != 0) {
    s0 = (sp[0] << 1);
    t1 = VIS_LD_U16_I(tbb1, s0);
    t0 = VIS_LD_U16_I(tbb0, s0);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *(mlib_f32*)dp = vis_rfbd_ii(bdd);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_2_D1(donst mlib_s16 *srd,
                                       mlib_s16       *dst,
                                       mlib_s32       xsizf,
                                       donst mlib_s16 **tbblf)
{
  mlib_s16 *sp;                /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2;         /* sourdf dbtb */
  mlib_s16 *dl;                /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;                /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;         /* dfstinbtion dbtb */
  mlib_d64 t3, bdd;            /* dfstinbtion dbtb */
  mlib_s32 i;                  /* loop vbribblf */
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];

  sp   = (void *)srd;
  dl   = dst;

  vis_blignbddr((void *) 0, 6);

  s0 = *sp++;
  *dl++ = tbb0[s0];
  dp   = (mlib_d64 *) dl;
  xsizf--; s0 <<= 1;

  if (xsizf >= 2) {

    s1 = (sp[0] << 1);
    s2 = (sp[1] << 1);
    sp += 2;

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 4; i+=2, sp+=2) {
      t3 = VIS_LD_U16_I(tbb0, s2);
      t2 = VIS_LD_U16_I(tbb1, s1);
      t1 = VIS_LD_U16_I(tbb0, s1);
      t0 = VIS_LD_U16_I(tbb1, s0);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s2;
      s1 = (sp[0] << 1);
      s2 = (sp[1] << 1);
      *dp++ = bdd;
    }

    t3 = VIS_LD_U16_I(tbb0, s2);
    t2 = VIS_LD_U16_I(tbb1, s1);
    t1 = VIS_LD_U16_I(tbb0, s1);
    t0 = VIS_LD_U16_I(tbb1, s0);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s2;
    *dp++ = bdd;
  }

  dl = (mlib_s16*)dp;

  if ((xsizf & 1) != 0) {
    s1 = (sp[0] << 1);
    t1 = VIS_LD_U16_I(tbb0, s1);
    t0 = VIS_LD_U16_I(tbb1, s0);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *(mlib_f32*)dp = vis_rfbd_ii(bdd);
    s0 = s1; dl += 2;
  }

  s0 >>= 1;
  *dl = tbb1[s0];
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_2(donst mlib_s16 *srd,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsizf,
                                    mlib_s32       ysizf,
                                    donst mlib_s16 **tbblf)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  mlib_s32 j;
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j ++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    mlib_s32 off, s0, sizf = xsizf;

    off = ((8 - ((mlib_bddr)dp & 7)) & 7);

    if ((off >= 4) && (sizf > 0)) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      sizf--;
    }

    if (sizf > 0) {

      if (((mlib_bddr)dp & 7) == 0) {
        mlib_v_ImbgfLookUpSI_S16_S16_2_DstA8D1(sp, dp, sizf, tbblf);
      } flsf {
        mlib_v_ImbgfLookUpSI_S16_S16_2_D1(sp, dp, sizf, tbblf);
      }
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_3_D1(donst mlib_s16 *srd,
                                       mlib_s16       *dst,
                                       mlib_s32       xsizf,
                                       donst mlib_s16 **tbblf)
{
  mlib_s16 *sp;              /* pointfr to sourdf dbtb */
  mlib_s16 *dl;              /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;              /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2, t3;   /* dfstinbtion dbtb */
  mlib_d64 bdd0, bdd1, bdd2; /* dfstinbtion dbtb */
  mlib_s32 i;                /* loop vbribblf */
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];
  donst mlib_s16 *tbb2 = &tbblf[2][32768];
  mlib_s32 s00, s01, s02, s03;

  sp   = (void *)srd;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  i = 0;

  if (xsizf >= 4) {

    s00 = (sp[0] << 1);
    s01 = (sp[1] << 1);
    s02 = (sp[2] << 1);
    s03 = (sp[3] << 1);
    sp += 4;

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 8; i+=4, sp+=4) {
      t3 = VIS_LD_U16_I(tbb0, s01);
      t2 = VIS_LD_U16_I(tbb2, s00);
      t1 = VIS_LD_U16_I(tbb1, s00);
      t0 = VIS_LD_U16_I(tbb0, s00);
      bdd0 = vis_fbligndbtb(t3, bdd0);
      bdd0 = vis_fbligndbtb(t2, bdd0);
      bdd0 = vis_fbligndbtb(t1, bdd0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
      t3 = VIS_LD_U16_I(tbb1, s02);
      t2 = VIS_LD_U16_I(tbb0, s02);
      t1 = VIS_LD_U16_I(tbb2, s01);
      t0 = VIS_LD_U16_I(tbb1, s01);
      bdd1 = vis_fbligndbtb(t3, bdd1);
      bdd1 = vis_fbligndbtb(t2, bdd1);
      bdd1 = vis_fbligndbtb(t1, bdd1);
      bdd1 = vis_fbligndbtb(t0, bdd1);
      t3 = VIS_LD_U16_I(tbb2, s03);
      t2 = VIS_LD_U16_I(tbb1, s03);
      t1 = VIS_LD_U16_I(tbb0, s03);
      t0 = VIS_LD_U16_I(tbb2, s02);
      bdd2 = vis_fbligndbtb(t3, bdd2);
      bdd2 = vis_fbligndbtb(t2, bdd2);
      bdd2 = vis_fbligndbtb(t1, bdd2);
      bdd2 = vis_fbligndbtb(t0, bdd2);
      s00 = (sp[0] << 1);
      s01 = (sp[1] << 1);
      s02 = (sp[2] << 1);
      s03 = (sp[3] << 1);
      *dp++ = bdd0;
      *dp++ = bdd1;
      *dp++ = bdd2;
    }

    t3 = VIS_LD_U16_I(tbb0, s01);
    t2 = VIS_LD_U16_I(tbb2, s00);
    t1 = VIS_LD_U16_I(tbb1, s00);
    t0 = VIS_LD_U16_I(tbb0, s00);
    bdd0 = vis_fbligndbtb(t3, bdd0);
    bdd0 = vis_fbligndbtb(t2, bdd0);
    bdd0 = vis_fbligndbtb(t1, bdd0);
    bdd0 = vis_fbligndbtb(t0, bdd0);
    t3 = VIS_LD_U16_I(tbb1, s02);
    t2 = VIS_LD_U16_I(tbb0, s02);
    t1 = VIS_LD_U16_I(tbb2, s01);
    t0 = VIS_LD_U16_I(tbb1, s01);
    bdd1 = vis_fbligndbtb(t3, bdd1);
    bdd1 = vis_fbligndbtb(t2, bdd1);
    bdd1 = vis_fbligndbtb(t1, bdd1);
    bdd1 = vis_fbligndbtb(t0, bdd1);
    t3 = VIS_LD_U16_I(tbb2, s03);
    t2 = VIS_LD_U16_I(tbb1, s03);
    t1 = VIS_LD_U16_I(tbb0, s03);
    t0 = VIS_LD_U16_I(tbb2, s02);
    bdd2 = vis_fbligndbtb(t3, bdd2);
    bdd2 = vis_fbligndbtb(t2, bdd2);
    bdd2 = vis_fbligndbtb(t1, bdd2);
    bdd2 = vis_fbligndbtb(t0, bdd2);
    *dp++ = bdd0;
    *dp++ = bdd1;
    *dp++ = bdd2;
    i += 4;
  }

  dl = (mlib_s16*)dp;

#prbgmb pipfloop(0)
  for (; i < xsizf; i++) {
    s00 = sp[0];
    dl[0] = tbb0[s00];
    dl[1] = tbb1[s00];
    dl[2] = tbb2[s00];
    dl += 3; sp ++;
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_3(donst mlib_s16 *srd,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsizf,
                                    mlib_s32       ysizf,
                                    donst mlib_s16 **tbblf)
{
  mlib_s16  *sl;
  mlib_s16 *dl;
  mlib_s32 i, j;
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];
  donst mlib_s16 *tbb2 = &tbblf[2][32768];

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j ++) {
    mlib_s16 *sp = sl;
    mlib_s16*dp = dl;
    mlib_s32 off, s0, sizf = xsizf;

    off = ((mlib_bddr)dp & 7) >> 1;
    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off; i++) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      *dp++ = tbb2[s0];
      sizf--;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUpSI_S16_S16_3_D1(sp, dp, sizf, tbblf);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff0_D1(donst mlib_s16 *srd,
                                               mlib_s16       *dst,
                                               mlib_s32       xsizf,
                                               donst mlib_s16 **tbblf)
{
  mlib_s16 *sp;              /* pointfr to sourdf dbtb */
  mlib_s32 s0;               /* sourdf dbtb */
  mlib_s16 *dl;              /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;              /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2, t3;   /* dfstinbtion dbtb */
  mlib_d64 bdd;              /* dfstinbtion dbtb */
  mlib_s32 i;                /* loop vbribblf */
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];
  donst mlib_s16 *tbb2 = &tbblf[2][32768];
  donst mlib_s16 *tbb3 = &tbblf[3][32768];

  sp   = (void *)srd;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  if (xsizf >= 1) {

    s0 = (*sp++) << 1;

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 2; i++) {
      t3 = VIS_LD_U16_I(tbb3, s0);
      t2 = VIS_LD_U16_I(tbb2, s0);
      t1 = VIS_LD_U16_I(tbb1, s0);
      t0 = VIS_LD_U16_I(tbb0, s0);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = (*sp++) << 1;
      *dp++ = bdd;
    }

    t3 = VIS_LD_U16_I(tbb3, s0);
    t2 = VIS_LD_U16_I(tbb2, s0);
    t1 = VIS_LD_U16_I(tbb1, s0);
    t0 = VIS_LD_U16_I(tbb0, s0);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff1_D1(donst mlib_s16 *srd,
                                               mlib_s16       *dst,
                                               mlib_s32       xsizf,
                                               donst mlib_s16 **tbblf)
{
  mlib_s16 *sp;              /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1;           /* sourdf dbtb */
  mlib_s16 *dl;              /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;              /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2, t3;   /* dfstinbtion dbtb */
  mlib_d64 bdd;              /* dfstinbtion dbtb */
  mlib_s32 i;                /* loop vbribblf */
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];
  donst mlib_s16 *tbb2 = &tbblf[2][32768];
  donst mlib_s16 *tbb3 = &tbblf[3][32768];

  sp   = (void *)srd;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  s0 = (*sp++) << 1;

  if (xsizf >= 1) {

    s1 = (*sp++) << 1;

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 2; i++) {
      t3 = VIS_LD_U16_I(tbb0, s1);
      t2 = VIS_LD_U16_I(tbb3, s0);
      t1 = VIS_LD_U16_I(tbb2, s0);
      t0 = VIS_LD_U16_I(tbb1, s0);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s1;
      s1 = (*sp++) << 1;
      *dp++ = bdd;
    }

    t3 = VIS_LD_U16_I(tbb0, s1);
    t2 = VIS_LD_U16_I(tbb3, s0);
    t1 = VIS_LD_U16_I(tbb2, s0);
    t0 = VIS_LD_U16_I(tbb1, s0);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s1;
    *dp++ = bdd;
  }

  dl = (mlib_s16*)dp;
  s0 >>= 1;

  dl[0] = tbb1[s0];
  dl[1] = tbb2[s0];
  dl[2] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff2_D1(donst mlib_s16 *srd,
                                               mlib_s16       *dst,
                                               mlib_s32       xsizf,
                                               donst mlib_s16 **tbblf)
{
  mlib_s16 *sp;              /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1;           /* sourdf dbtb */
  mlib_s16 *dl;              /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;              /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2, t3;   /* dfstinbtion dbtb */
  mlib_d64 bdd;              /* dfstinbtion dbtb */
  mlib_s32 i;                /* loop vbribblf */
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];
  donst mlib_s16 *tbb2 = &tbblf[2][32768];
  donst mlib_s16 *tbb3 = &tbblf[3][32768];

  sp   = (void *)srd;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  s0 = (*sp++) << 1;

  if (xsizf >= 1) {

    s1 = (*sp++) << 1;

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 2; i++) {
      t3 = VIS_LD_U16_I(tbb1, s1);
      t2 = VIS_LD_U16_I(tbb0, s1);
      t1 = VIS_LD_U16_I(tbb3, s0);
      t0 = VIS_LD_U16_I(tbb2, s0);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s1;
      s1 = (*sp++) << 1;
      *dp++ = bdd;
    }

    t3 = VIS_LD_U16_I(tbb1, s1);
    t2 = VIS_LD_U16_I(tbb0, s1);
    t1 = VIS_LD_U16_I(tbb3, s0);
    t0 = VIS_LD_U16_I(tbb2, s0);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s1;
    *dp++ = bdd;
  }

  dl = (mlib_s16*)dp;
  s0 >>= 1;

  dl[0] = tbb2[s0];
  dl[1] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff3_D1(donst mlib_s16 *srd,
                                               mlib_s16       *dst,
                                               mlib_s32       xsizf,
                                               donst mlib_s16 **tbblf)
{
  mlib_s16 *sp;              /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1;           /* sourdf dbtb */
  mlib_s16 *dl;              /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;              /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2, t3;   /* dfstinbtion dbtb */
  mlib_d64 bdd;              /* dfstinbtion dbtb */
  mlib_s32 i;                /* loop vbribblf */
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];
  donst mlib_s16 *tbb2 = &tbblf[2][32768];
  donst mlib_s16 *tbb3 = &tbblf[3][32768];

  sp   = (void *)srd;
  dl   = dst;
  dp   = (mlib_d64 *) dl;

  vis_blignbddr((void *) 0, 6);

  s0 = (*sp++) << 1;

  if (xsizf >= 1) {

    s1 = (*sp++) << 1;

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 2; i++) {
      t3 = VIS_LD_U16_I(tbb2, s1);
      t2 = VIS_LD_U16_I(tbb1, s1);
      t1 = VIS_LD_U16_I(tbb0, s1);
      t0 = VIS_LD_U16_I(tbb3, s0);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s1;
      s1 = (*sp++) << 1;
      *dp++ = bdd;
    }

    t3 = VIS_LD_U16_I(tbb2, s1);
    t2 = VIS_LD_U16_I(tbb1, s1);
    t1 = VIS_LD_U16_I(tbb0, s1);
    t0 = VIS_LD_U16_I(tbb3, s0);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s1;
    *dp++ = bdd;
  }

  dl = (mlib_s16*)dp;
  s0 >>= 1;

  dl[0] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S16_S16_4(donst mlib_s16 *srd,
                                    mlib_s32       slb,
                                    mlib_s16       *dst,
                                    mlib_s32       dlb,
                                    mlib_s32       xsizf,
                                    mlib_s32       ysizf,
                                    donst mlib_s16 **tbblf)
{
  mlib_s16 *sl;
  mlib_s16 *dl;
  mlib_s32 j;
  donst mlib_s16 *tbb0 = &tbblf[0][32768];
  donst mlib_s16 *tbb1 = &tbblf[1][32768];
  donst mlib_s16 *tbb2 = &tbblf[2][32768];

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j ++) {
    mlib_s16 *sp = sl;
    mlib_s16 *dp = dl;
    mlib_s32 off, s0, sizf = xsizf;

    if (sizf > 0) {
      off =  ((8 - ((mlib_bddr)dp & 7)) & 7) >> 1;

      if (off == 0) {
        mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff0_D1(sp, dp, sizf, tbblf);
      } flsf if (off == 1) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        sizf--;
        mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff1_D1(sp, dp, sizf, tbblf);
      } flsf if (off == 2) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        sizf--;
        mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff2_D1(sp, dp, sizf, tbblf);
      } flsf if (off == 3) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        *dp++ = tbb2[s0];
        sizf--;
        mlib_v_ImbgfLookUpSI_S16_S16_4_DstOff3_D1(sp, dp, sizf, tbblf);
      }
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s16 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
