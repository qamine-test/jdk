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
stbtid void mlib_v_ImbgfLookUpSI_S32_U8_2_DstA8D1(donst mlib_s32 *srd,
                                                  mlib_u8        *dst,
                                                  mlib_s32       xsizf,
                                                  donst mlib_u8  **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S32_U8_2_D1(donst mlib_s32 *srd,
                                             mlib_u8        *dst,
                                             mlib_s32       xsizf,
                                             donst mlib_u8  **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S32_U8_3_D1(donst mlib_s32 *srd,
                                             mlib_u8        *dst,
                                             mlib_s32       xsizf,
                                             donst mlib_u8  **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff0_D1(donst mlib_s32 *srd,
                                                     mlib_u8        *dst,
                                                     mlib_s32       xsizf,
                                                     donst mlib_u8  **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff1_D1(donst mlib_s32 *srd,
                                                     mlib_u8        *dst,
                                                     mlib_s32       xsizf,
                                                     donst mlib_u8  **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff2_D1(donst mlib_s32 *srd,
                                                     mlib_u8        *dst,
                                                     mlib_s32       xsizf,
                                                     donst mlib_u8  **tbblf);

stbtid void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff3_D1(donst mlib_s32 *srd,
                                                     mlib_u8        *dst,
                                                     mlib_s32       xsizf,
                                                     donst mlib_u8  **tbblf);

/***************************************************************/
#dffinf VIS_LD_U8_I(X, Y)       vis_ld_u8_i((void *)(X), (Y))

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_2_DstA8D1(donst mlib_s32 *srd,
                                           mlib_u8        *dst,
                                           mlib_s32       xsizf,
                                           donst mlib_u8  **tbblf)
{
  mlib_s32 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2, s3;             /* sourdf dbtb */
  mlib_u16 *dl;                        /* pointfr to stbrt of dfstinbtion */
  mlib_u16 *dfnd;                      /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];

  sp = (void *)srd;
  dl = (mlib_u16 *) dst;
  dp = (mlib_d64 *) dl;
  dfnd = dl + xsizf - 1;

  vis_blignbddr((void *)0, 7);

  if (xsizf >= 4) {

    s0 = sp[0];
    s1 = sp[1];
    s2 = sp[2];
    s3 = sp[3];
    sp += 4;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 8; i += 4, sp += 4) {
      t7 = VIS_LD_U8_I(tbb1, s3);
      t6 = VIS_LD_U8_I(tbb0, s3);
      t5 = VIS_LD_U8_I(tbb1, s2);
      t4 = VIS_LD_U8_I(tbb0, s2);
      t3 = VIS_LD_U8_I(tbb1, s1);
      t2 = VIS_LD_U8_I(tbb0, s1);
      t1 = VIS_LD_U8_I(tbb1, s0);
      t0 = VIS_LD_U8_I(tbb0, s0);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = sp[0];
      s1 = sp[1];
      s2 = sp[2];
      s3 = sp[3];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbb1, s3);
    t6 = VIS_LD_U8_I(tbb0, s3);
    t5 = VIS_LD_U8_I(tbb1, s2);
    t4 = VIS_LD_U8_I(tbb0, s2);
    t3 = VIS_LD_U8_I(tbb1, s1);
    t2 = VIS_LD_U8_I(tbb0, s1);
    t1 = VIS_LD_U8_I(tbb1, s0);
    t0 = VIS_LD_U8_I(tbb0, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }

  if ((mlib_bddr) dp <= (mlib_bddr) dfnd) {

    num = (mlib_s32) ((mlib_u16 *) dfnd - (mlib_u16 *) dp);
    sp += num;
    num++;
#prbgmb pipfloop(0)
    for (i = 0; i < num; i++) {
      s0 = *sp;
      sp--;

      t0 = VIS_LD_U8_I(tbb1, s0);
      bdd = vis_fbligndbtb(t0, bdd);

      t0 = VIS_LD_U8_I(tbb0, s0);
      bdd = vis_fbligndbtb(t0, bdd);
    }

    fmbsk = vis_fdgf16(dp, dfnd);
    vis_pst_16(bdd, dp, fmbsk);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_2_D1(donst mlib_s32 *srd,
                                      mlib_u8        *dst,
                                      mlib_s32       xsizf,
                                      donst mlib_u8  **tbblf)
{
  mlib_s32 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2, s3, s4;         /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_u8 *dfnd;                       /* pointfr to fnd of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 fmbsk;                      /* fdgf mbsk */
  mlib_s32 i, num;                     /* loop vbribblf */
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];

  sp = (void *)srd;
  dl = dst;

  dfnd = dl + 2 * xsizf - 1;

  vis_blignbddr((void *)0, 7);

  s0 = *sp++;
  *dl++ = tbb0[s0];
  dp = (mlib_d64 *) dl;
  xsizf--;

  if (xsizf >= 4) {

    s1 = sp[0];
    s2 = sp[1];
    s3 = sp[2];
    s4 = sp[3];
    sp += 4;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 8; i += 4, sp += 4) {
      t7 = VIS_LD_U8_I(tbb0, s4);
      t6 = VIS_LD_U8_I(tbb1, s3);
      t5 = VIS_LD_U8_I(tbb0, s3);
      t4 = VIS_LD_U8_I(tbb1, s2);
      t3 = VIS_LD_U8_I(tbb0, s2);
      t2 = VIS_LD_U8_I(tbb1, s1);
      t1 = VIS_LD_U8_I(tbb0, s1);
      t0 = VIS_LD_U8_I(tbb1, s0);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s4;
      s1 = sp[0];
      s2 = sp[1];
      s3 = sp[2];
      s4 = sp[3];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbb0, s4);
    t6 = VIS_LD_U8_I(tbb1, s3);
    t5 = VIS_LD_U8_I(tbb0, s3);
    t4 = VIS_LD_U8_I(tbb1, s2);
    t3 = VIS_LD_U8_I(tbb0, s2);
    t2 = VIS_LD_U8_I(tbb1, s1);
    t1 = VIS_LD_U8_I(tbb0, s1);
    t0 = VIS_LD_U8_I(tbb1, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s4;
    *dp++ = bdd;
  }

  num = (mlib_s32) (((mlib_u8 *) dfnd - (mlib_u8 *) dp) >> 1);
  sp += num - 1;

#prbgmb pipfloop(0)
  for (i = 0; i < num; i++) {
    s1 = *sp;
    sp--;

    t0 = VIS_LD_U8_I(tbb1, s1);
    bdd = vis_fbligndbtb(t0, bdd);

    t0 = VIS_LD_U8_I(tbb0, s1);
    bdd = vis_fbligndbtb(t0, bdd);
  }

  t0 = VIS_LD_U8_I(tbb1, s0);
  bdd = vis_fbligndbtb(t0, bdd);
  fmbsk = vis_fdgf8(dp, dfnd);
  vis_pst_8(bdd, dp, fmbsk);
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_2(donst mlib_s32 *srd,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsizf,
                                   mlib_s32       ysizf,
                                   donst mlib_u8  **tbblf)
{
  mlib_s32 *sl;
  mlib_u8 *dl;
  mlib_s32 i, j;
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_s32 *sp = sl;
    mlib_u8 *dp = dl;
    mlib_s32 off, s0, sizf = xsizf;

    off = (mlib_s32) (((8 - ((mlib_bddr) dp & 7)) & 7) >> 1);
    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off; i++) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      sizf--;
    }

    if (sizf > 0) {

      if (((mlib_bddr) dp & 1) == 0) {
        mlib_v_ImbgfLookUpSI_S32_U8_2_DstA8D1(sp, dp, sizf, tbblf);
      }
      flsf {
        mlib_v_ImbgfLookUpSI_S32_U8_2_D1(sp, dp, sizf, tbblf);
      }
    }

    sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_3_D1(donst mlib_s32 *srd,
                                      mlib_u8        *dst,
                                      mlib_s32       xsizf,
                                      donst mlib_u8  **tbblf)
{
  mlib_s32 *sp;                        /* pointfr to sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7;                     /* dfstinbtion dbtb */
  mlib_d64 bdd0, bdd1, bdd2;           /* dfstinbtion dbtb */
  mlib_s32 i;                          /* loop vbribblf */
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb2 = &tbblf[2][(mlib_u32) 2147483648u];
  mlib_s32 s00, s01, s02, s03;
  mlib_s32 s10, s11, s12, s13;

  sp = (void *)srd;
  dl = dst;
  dp = (mlib_d64 *) dl;

  vis_blignbddr((void *)0, 7);

  i = 0;

  if (xsizf >= 8) {

    s00 = sp[0];
    s01 = sp[1];
    s02 = sp[2];
    s03 = sp[3];
    s10 = sp[4];
    s11 = sp[5];
    s12 = sp[6];
    s13 = sp[7];
    sp += 8;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 16; i += 8, sp += 8) {
      t7 = VIS_LD_U8_I(tbb1, s02);
      t6 = VIS_LD_U8_I(tbb0, s02);
      t5 = VIS_LD_U8_I(tbb2, s01);
      t4 = VIS_LD_U8_I(tbb1, s01);
      t3 = VIS_LD_U8_I(tbb0, s01);
      t2 = VIS_LD_U8_I(tbb2, s00);
      t1 = VIS_LD_U8_I(tbb1, s00);
      t0 = VIS_LD_U8_I(tbb0, s00);
      bdd0 = vis_fbligndbtb(t7, bdd0);
      bdd0 = vis_fbligndbtb(t6, bdd0);
      bdd0 = vis_fbligndbtb(t5, bdd0);
      bdd0 = vis_fbligndbtb(t4, bdd0);
      bdd0 = vis_fbligndbtb(t3, bdd0);
      bdd0 = vis_fbligndbtb(t2, bdd0);
      bdd0 = vis_fbligndbtb(t1, bdd0);
      bdd0 = vis_fbligndbtb(t0, bdd0);
      t7 = VIS_LD_U8_I(tbb0, s11);
      t6 = VIS_LD_U8_I(tbb2, s10);
      t5 = VIS_LD_U8_I(tbb1, s10);
      t4 = VIS_LD_U8_I(tbb0, s10);
      t3 = VIS_LD_U8_I(tbb2, s03);
      t2 = VIS_LD_U8_I(tbb1, s03);
      t1 = VIS_LD_U8_I(tbb0, s03);
      t0 = VIS_LD_U8_I(tbb2, s02);
      bdd1 = vis_fbligndbtb(t7, bdd1);
      bdd1 = vis_fbligndbtb(t6, bdd1);
      bdd1 = vis_fbligndbtb(t5, bdd1);
      bdd1 = vis_fbligndbtb(t4, bdd1);
      bdd1 = vis_fbligndbtb(t3, bdd1);
      bdd1 = vis_fbligndbtb(t2, bdd1);
      bdd1 = vis_fbligndbtb(t1, bdd1);
      bdd1 = vis_fbligndbtb(t0, bdd1);
      t7 = VIS_LD_U8_I(tbb2, s13);
      t6 = VIS_LD_U8_I(tbb1, s13);
      t5 = VIS_LD_U8_I(tbb0, s13);
      t4 = VIS_LD_U8_I(tbb2, s12);
      t3 = VIS_LD_U8_I(tbb1, s12);
      t2 = VIS_LD_U8_I(tbb0, s12);
      t1 = VIS_LD_U8_I(tbb2, s11);
      t0 = VIS_LD_U8_I(tbb1, s11);
      bdd2 = vis_fbligndbtb(t7, bdd2);
      bdd2 = vis_fbligndbtb(t6, bdd2);
      bdd2 = vis_fbligndbtb(t5, bdd2);
      bdd2 = vis_fbligndbtb(t4, bdd2);
      bdd2 = vis_fbligndbtb(t3, bdd2);
      bdd2 = vis_fbligndbtb(t2, bdd2);
      bdd2 = vis_fbligndbtb(t1, bdd2);
      bdd2 = vis_fbligndbtb(t0, bdd2);
      s00 = sp[0];
      s01 = sp[1];
      s02 = sp[2];
      s03 = sp[3];
      s10 = sp[4];
      s11 = sp[5];
      s12 = sp[6];
      s13 = sp[7];
      *dp++ = bdd0;
      *dp++ = bdd1;
      *dp++ = bdd2;
    }

    t7 = VIS_LD_U8_I(tbb1, s02);
    t6 = VIS_LD_U8_I(tbb0, s02);
    t5 = VIS_LD_U8_I(tbb2, s01);
    t4 = VIS_LD_U8_I(tbb1, s01);
    t3 = VIS_LD_U8_I(tbb0, s01);
    t2 = VIS_LD_U8_I(tbb2, s00);
    t1 = VIS_LD_U8_I(tbb1, s00);
    t0 = VIS_LD_U8_I(tbb0, s00);
    bdd0 = vis_fbligndbtb(t7, bdd0);
    bdd0 = vis_fbligndbtb(t6, bdd0);
    bdd0 = vis_fbligndbtb(t5, bdd0);
    bdd0 = vis_fbligndbtb(t4, bdd0);
    bdd0 = vis_fbligndbtb(t3, bdd0);
    bdd0 = vis_fbligndbtb(t2, bdd0);
    bdd0 = vis_fbligndbtb(t1, bdd0);
    bdd0 = vis_fbligndbtb(t0, bdd0);
    t7 = VIS_LD_U8_I(tbb0, s11);
    t6 = VIS_LD_U8_I(tbb2, s10);
    t5 = VIS_LD_U8_I(tbb1, s10);
    t4 = VIS_LD_U8_I(tbb0, s10);
    t3 = VIS_LD_U8_I(tbb2, s03);
    t2 = VIS_LD_U8_I(tbb1, s03);
    t1 = VIS_LD_U8_I(tbb0, s03);
    t0 = VIS_LD_U8_I(tbb2, s02);
    bdd1 = vis_fbligndbtb(t7, bdd1);
    bdd1 = vis_fbligndbtb(t6, bdd1);
    bdd1 = vis_fbligndbtb(t5, bdd1);
    bdd1 = vis_fbligndbtb(t4, bdd1);
    bdd1 = vis_fbligndbtb(t3, bdd1);
    bdd1 = vis_fbligndbtb(t2, bdd1);
    bdd1 = vis_fbligndbtb(t1, bdd1);
    bdd1 = vis_fbligndbtb(t0, bdd1);
    t7 = VIS_LD_U8_I(tbb2, s13);
    t6 = VIS_LD_U8_I(tbb1, s13);
    t5 = VIS_LD_U8_I(tbb0, s13);
    t4 = VIS_LD_U8_I(tbb2, s12);
    t3 = VIS_LD_U8_I(tbb1, s12);
    t2 = VIS_LD_U8_I(tbb0, s12);
    t1 = VIS_LD_U8_I(tbb2, s11);
    t0 = VIS_LD_U8_I(tbb1, s11);
    bdd2 = vis_fbligndbtb(t7, bdd2);
    bdd2 = vis_fbligndbtb(t6, bdd2);
    bdd2 = vis_fbligndbtb(t5, bdd2);
    bdd2 = vis_fbligndbtb(t4, bdd2);
    bdd2 = vis_fbligndbtb(t3, bdd2);
    bdd2 = vis_fbligndbtb(t2, bdd2);
    bdd2 = vis_fbligndbtb(t1, bdd2);
    bdd2 = vis_fbligndbtb(t0, bdd2);
    *dp++ = bdd0;
    *dp++ = bdd1;
    *dp++ = bdd2;
    i += 8;
  }

  dl = (mlib_u8 *) dp;

#prbgmb pipfloop(0)
  for (; i < xsizf; i++) {
    s00 = sp[0];
    dl[0] = tbb0[s00];
    dl[1] = tbb1[s00];
    dl[2] = tbb2[s00];
    dl += 3;
    sp++;
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_3(donst mlib_s32 *srd,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsizf,
                                   mlib_s32       ysizf,
                                   donst mlib_u8  **tbblf)
{
  mlib_s32 *sl;
  mlib_u8 *dl;
  mlib_s32 i, j;
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb2 = &tbblf[2][(mlib_u32) 2147483648u];

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_s32 *sp = sl;
    mlib_u8 *dp = dl;
    mlib_s32 off, s0, sizf = xsizf;

    off = (mlib_s32) ((mlib_bddr) dp & 7);
    off = (off * 5) & 7;
    off = (off < sizf) ? off : sizf;

    for (i = 0; i < off; i++) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      *dp++ = tbb2[s0];
      sizf--;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUpSI_S32_U8_3_D1(sp, dp, sizf, tbblf);
    }

    sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff0_D1(donst mlib_s32 *srd,
                                              mlib_u8        *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_u8  **tbblf)
{
  mlib_s32 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1;                     /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 i;                          /* loop vbribblf */
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb2 = &tbblf[2][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb3 = &tbblf[3][(mlib_u32) 2147483648u];

  sp = (void *)srd;
  dl = dst;
  dp = (mlib_d64 *) dl;

  vis_blignbddr((void *)0, 7);

  if (xsizf >= 2) {

    s0 = sp[0];
    s1 = sp[1];
    sp += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 4; i += 2, sp += 2) {
      t7 = VIS_LD_U8_I(tbb3, s1);
      t6 = VIS_LD_U8_I(tbb2, s1);
      t5 = VIS_LD_U8_I(tbb1, s1);
      t4 = VIS_LD_U8_I(tbb0, s1);
      t3 = VIS_LD_U8_I(tbb3, s0);
      t2 = VIS_LD_U8_I(tbb2, s0);
      t1 = VIS_LD_U8_I(tbb1, s0);
      t0 = VIS_LD_U8_I(tbb0, s0);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = sp[0];
      s1 = sp[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbb3, s1);
    t6 = VIS_LD_U8_I(tbb2, s1);
    t5 = VIS_LD_U8_I(tbb1, s1);
    t4 = VIS_LD_U8_I(tbb0, s1);
    t3 = VIS_LD_U8_I(tbb3, s0);
    t2 = VIS_LD_U8_I(tbb2, s0);
    t1 = VIS_LD_U8_I(tbb1, s0);
    t0 = VIS_LD_U8_I(tbb0, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    *dp++ = bdd;
  }

  if ((xsizf & 1) != 0) {
    s0 = sp[0];
    t7 = VIS_LD_U8_I(tbb3, s0);
    t6 = VIS_LD_U8_I(tbb2, s0);
    t5 = VIS_LD_U8_I(tbb1, s0);
    t4 = VIS_LD_U8_I(tbb0, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    *(mlib_f32 *) dp = vis_rfbd_ii(bdd);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff1_D1(donst mlib_s32 *srd,
                                              mlib_u8        *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_u8  **tbblf)
{
  mlib_s32 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 i;                          /* loop vbribblf */
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb2 = &tbblf[2][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb3 = &tbblf[3][(mlib_u32) 2147483648u];

  sp = (void *)srd;
  dl = dst;
  dp = (mlib_d64 *) dl;

  vis_blignbddr((void *)0, 7);

  s0 = *sp++;

  if (xsizf >= 2) {

    s1 = sp[0];
    s2 = sp[1];
    sp += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 4; i += 2, sp += 2) {
      t7 = VIS_LD_U8_I(tbb0, s2);
      t6 = VIS_LD_U8_I(tbb3, s1);
      t5 = VIS_LD_U8_I(tbb2, s1);
      t4 = VIS_LD_U8_I(tbb1, s1);
      t3 = VIS_LD_U8_I(tbb0, s1);
      t2 = VIS_LD_U8_I(tbb3, s0);
      t1 = VIS_LD_U8_I(tbb2, s0);
      t0 = VIS_LD_U8_I(tbb1, s0);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s2;
      s1 = sp[0];
      s2 = sp[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbb0, s2);
    t6 = VIS_LD_U8_I(tbb3, s1);
    t5 = VIS_LD_U8_I(tbb2, s1);
    t4 = VIS_LD_U8_I(tbb1, s1);
    t3 = VIS_LD_U8_I(tbb0, s1);
    t2 = VIS_LD_U8_I(tbb3, s0);
    t1 = VIS_LD_U8_I(tbb2, s0);
    t0 = VIS_LD_U8_I(tbb1, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s2;
    *dp++ = bdd;
  }

  dl = (mlib_u8 *) dp;

  if ((xsizf & 1) != 0) {
    s1 = sp[0];
    t7 = VIS_LD_U8_I(tbb0, s1);
    t6 = VIS_LD_U8_I(tbb3, s0);
    t5 = VIS_LD_U8_I(tbb2, s0);
    t4 = VIS_LD_U8_I(tbb1, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    *(mlib_f32 *) dl = vis_rfbd_ii(bdd);
    dl += 4;
    s0 = s1;
  }

  dl[0] = tbb1[s0];
  dl[1] = tbb2[s0];
  dl[2] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff2_D1(donst mlib_s32 *srd,
                                              mlib_u8        *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_u8  **tbblf)
{
  mlib_s32 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 i;                          /* loop vbribblf */
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb2 = &tbblf[2][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb3 = &tbblf[3][(mlib_u32) 2147483648u];

  sp = (void *)srd;
  dl = dst;
  dp = (mlib_d64 *) dl;

  vis_blignbddr((void *)0, 7);

  s0 = *sp++;

  if (xsizf >= 2) {

    s1 = sp[0];
    s2 = sp[1];
    sp += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 4; i += 2, sp += 2) {
      t7 = VIS_LD_U8_I(tbb1, s2);
      t6 = VIS_LD_U8_I(tbb0, s2);
      t5 = VIS_LD_U8_I(tbb3, s1);
      t4 = VIS_LD_U8_I(tbb2, s1);
      t3 = VIS_LD_U8_I(tbb1, s1);
      t2 = VIS_LD_U8_I(tbb0, s1);
      t1 = VIS_LD_U8_I(tbb3, s0);
      t0 = VIS_LD_U8_I(tbb2, s0);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s2;
      s1 = sp[0];
      s2 = sp[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbb1, s2);
    t6 = VIS_LD_U8_I(tbb0, s2);
    t5 = VIS_LD_U8_I(tbb3, s1);
    t4 = VIS_LD_U8_I(tbb2, s1);
    t3 = VIS_LD_U8_I(tbb1, s1);
    t2 = VIS_LD_U8_I(tbb0, s1);
    t1 = VIS_LD_U8_I(tbb3, s0);
    t0 = VIS_LD_U8_I(tbb2, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s2;
    *dp++ = bdd;
  }

  dl = (mlib_u8 *) dp;

  if ((xsizf & 1) != 0) {
    s1 = sp[0];
    t7 = VIS_LD_U8_I(tbb1, s1);
    t6 = VIS_LD_U8_I(tbb0, s1);
    t5 = VIS_LD_U8_I(tbb3, s0);
    t4 = VIS_LD_U8_I(tbb2, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    *(mlib_f32 *) dl = vis_rfbd_ii(bdd);
    dl += 4;
    s0 = s1;
  }

  dl[0] = tbb2[s0];
  dl[1] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff3_D1(donst mlib_s32 *srd,
                                              mlib_u8        *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_u8  **tbblf)
{
  mlib_s32 *sp;                        /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1, s2;                 /* sourdf dbtb */
  mlib_u8 *dl;                         /* pointfr to stbrt of dfstinbtion */
  mlib_d64 *dp;                        /* blignfd pointfr to dfstinbtion */
  mlib_d64 t0, t1, t2;                 /* dfstinbtion dbtb */
  mlib_d64 t3, t4, t5;                 /* dfstinbtion dbtb */
  mlib_d64 t6, t7, bdd;                /* dfstinbtion dbtb */
  mlib_s32 i;                          /* loop vbribblf */
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb2 = &tbblf[2][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb3 = &tbblf[3][(mlib_u32) 2147483648u];

  sp = (void *)srd;
  dl = dst;
  dp = (mlib_d64 *) dl;

  vis_blignbddr((void *)0, 7);

  s0 = *sp++;

  if (xsizf >= 2) {

    s1 = sp[0];
    s2 = sp[1];
    sp += 2;

#prbgmb pipfloop(0)
    for (i = 0; i <= xsizf - 4; i += 2, sp += 2) {
      t7 = VIS_LD_U8_I(tbb2, s2);
      t6 = VIS_LD_U8_I(tbb1, s2);
      t5 = VIS_LD_U8_I(tbb0, s2);
      t4 = VIS_LD_U8_I(tbb3, s1);
      t3 = VIS_LD_U8_I(tbb2, s1);
      t2 = VIS_LD_U8_I(tbb1, s1);
      t1 = VIS_LD_U8_I(tbb0, s1);
      t0 = VIS_LD_U8_I(tbb3, s0);
      bdd = vis_fbligndbtb(t7, bdd);
      bdd = vis_fbligndbtb(t6, bdd);
      bdd = vis_fbligndbtb(t5, bdd);
      bdd = vis_fbligndbtb(t4, bdd);
      bdd = vis_fbligndbtb(t3, bdd);
      bdd = vis_fbligndbtb(t2, bdd);
      bdd = vis_fbligndbtb(t1, bdd);
      bdd = vis_fbligndbtb(t0, bdd);
      s0 = s2;
      s1 = sp[0];
      s2 = sp[1];
      *dp++ = bdd;
    }

    t7 = VIS_LD_U8_I(tbb2, s2);
    t6 = VIS_LD_U8_I(tbb1, s2);
    t5 = VIS_LD_U8_I(tbb0, s2);
    t4 = VIS_LD_U8_I(tbb3, s1);
    t3 = VIS_LD_U8_I(tbb2, s1);
    t2 = VIS_LD_U8_I(tbb1, s1);
    t1 = VIS_LD_U8_I(tbb0, s1);
    t0 = VIS_LD_U8_I(tbb3, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    bdd = vis_fbligndbtb(t3, bdd);
    bdd = vis_fbligndbtb(t2, bdd);
    bdd = vis_fbligndbtb(t1, bdd);
    bdd = vis_fbligndbtb(t0, bdd);
    s0 = s2;
    *dp++ = bdd;
  }

  dl = (mlib_u8 *) dp;

  if ((xsizf & 1) != 0) {
    s1 = sp[0];
    t7 = VIS_LD_U8_I(tbb2, s1);
    t6 = VIS_LD_U8_I(tbb1, s1);
    t5 = VIS_LD_U8_I(tbb0, s1);
    t4 = VIS_LD_U8_I(tbb3, s0);
    bdd = vis_fbligndbtb(t7, bdd);
    bdd = vis_fbligndbtb(t6, bdd);
    bdd = vis_fbligndbtb(t5, bdd);
    bdd = vis_fbligndbtb(t4, bdd);
    *(mlib_f32 *) dl = vis_rfbd_ii(bdd);
    dl += 4;
    s0 = s1;
  }

  dl[0] = tbb3[s0];
}

/***************************************************************/
void mlib_v_ImbgfLookUpSI_S32_U8_4(donst mlib_s32 *srd,
                                   mlib_s32       slb,
                                   mlib_u8        *dst,
                                   mlib_s32       dlb,
                                   mlib_s32       xsizf,
                                   mlib_s32       ysizf,
                                   donst mlib_u8  **tbblf)
{
  mlib_s32 *sl;
  mlib_u8 *dl;
  mlib_s32 j;
  donst mlib_u8 *tbb0 = &tbblf[0][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb1 = &tbblf[1][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb2 = &tbblf[2][(mlib_u32) 2147483648u];
  donst mlib_u8 *tbb3 = &tbblf[3][(mlib_u32) 2147483648u];

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j++) {
    mlib_s32 *sp = sl;
    mlib_u8 *dp = dl;
    mlib_s32 off, s0, sizf = xsizf;

    off = (mlib_s32) ((8 - ((mlib_bddr) dp & 7)) & 7);

    if ((off >= 4) && (sizf > 0)) {
      s0 = *sp++;
      *dp++ = tbb0[s0];
      *dp++ = tbb1[s0];
      *dp++ = tbb2[s0];
      *dp++ = tbb3[s0];
      sizf--;
    }

    if (sizf > 0) {
      off = (mlib_s32) ((4 - ((mlib_bddr) dp & 3)) & 3);

      if (off == 0) {
        mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff0_D1(sp, dp, sizf, tbblf);
      }
      flsf if (off == 1) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        sizf--;
        mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff1_D1(sp, dp, sizf, tbblf);
      }
      flsf if (off == 2) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        sizf--;
        mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff2_D1(sp, dp, sizf, tbblf);
      }
      flsf if (off == 3) {
        s0 = *sp;
        *dp++ = tbb0[s0];
        *dp++ = tbb1[s0];
        *dp++ = tbb2[s0];
        sizf--;
        mlib_v_ImbgfLookUpSI_S32_U8_4_DstOff3_D1(sp, dp, sizf, tbblf);
      }
    }

    sl = (mlib_s32 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_u8 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
