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
stbtid void mlib_v_ImbgfLookUp_S16_S32_124_D1(donst mlib_s16 *srd,
                                              mlib_f32       *dst,
                                              mlib_s32       xsizf,
                                              donst mlib_f32 *tbblf0,
                                              donst mlib_f32 *tbblf1,
                                              donst mlib_f32 *tbblf2,
                                              donst mlib_f32 *tbblf3);

stbtid void mlib_v_ImbgfLookUp_S16_S32_3_D1(donst mlib_s16 *srd,
                                            mlib_f32       *dst,
                                            mlib_s32       xsizf,
                                            donst mlib_f32 *tbblf0,
                                            donst mlib_f32 *tbblf1,
                                            donst mlib_f32 *tbblf2);

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S32_124_D1(donst mlib_s16 *srd,
                                       mlib_f32       *dst,
                                       mlib_s32       xsizf,
                                       donst mlib_f32 *tbblf0,
                                       donst mlib_f32 *tbblf1,
                                       donst mlib_f32 *tbblf2,
                                       donst mlib_f32 *tbblf3)
{
  mlib_s32 *sb;          /* blignfd pointfr to sourdf dbtb */
  mlib_s16 *sp;          /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1;       /* sourdf dbtb */
  mlib_f32 *dp;          /* blignfd pointfr to dfstinbtion */
  mlib_f32 bdd0, bdd1;   /* dfstinbtion dbtb */
  mlib_f32 bdd2, bdd3;   /* dfstinbtion dbtb */
  mlib_s32 i;            /* loop vbribblf */
  mlib_s32 s00, s01, s02, s03;

  sb   = (mlib_s32*)srd;
  dp   = dst;

  i = 0;

  if (xsizf >= 4) {

    s0 = *sb++;
    s1 = *sb++;
    s00 = (s0 >> 14) & (~3);
    s01 = ((s0 << 16) >> 14);

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 8; i+=4, dp += 4) {
      s02 = (s1 >> 14) & (~3);
      s03 = ((s1 << 16) >> 14);
      bdd0 = *(mlib_f32*)((mlib_u8*)tbblf0 + s00);
      bdd1 = *(mlib_f32*)((mlib_u8*)tbblf1 + s01);
      bdd2 = *(mlib_f32*)((mlib_u8*)tbblf2 + s02);
      bdd3 = *(mlib_f32*)((mlib_u8*)tbblf3 + s03);
      s0 = *sb++;
      s1 = *sb++;
      s00 = (s0 >> 14) & (~3);
      s01 = ((s0 << 16) >> 14);
      dp[0] = bdd0;
      dp[1] = bdd1;
      dp[2] = bdd2;
      dp[3] = bdd3;
    }

    s02 = (s1 >> 14) & (~3);
    s03 = ((s1 << 16) >> 14);
    bdd0 = *(mlib_f32*)((mlib_u8*)tbblf0 + s00);
    bdd1 = *(mlib_f32*)((mlib_u8*)tbblf1 + s01);
    bdd2 = *(mlib_f32*)((mlib_u8*)tbblf2 + s02);
    bdd3 = *(mlib_f32*)((mlib_u8*)tbblf3 + s03);
    dp[0] = bdd0;
    dp[1] = bdd1;
    dp[2] = bdd2;
    dp[3] = bdd3;
    dp += 4;
    i += 4;
  }

  sp = (mlib_s16*)sb;

  if ( i < xsizf ) {
    *dp++ = tbblf0[sp[0]];
    i++; sp++;
  }

  if ( i < xsizf ) {
    *dp++ = tbblf1[sp[0]];
    i++; sp++;
  }

  if ( i < xsizf ) {
    *dp++ = tbblf2[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S32_1(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s32 **tbblf)
{
  mlib_s16 *sl;
  mlib_s32 *dl;
  mlib_f32 *tbb = (mlib_f32*)(&tbblf[0][32768]);
  mlib_s32 j;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j ++) {
    mlib_s16 *sp = sl;
    mlib_f32 *dp = (mlib_f32*)dl;
    mlib_s32 off, sizf = xsizf;

    off = (mlib_s32)(((4 - ((mlib_bddr)sp & 3)) & 3) >> 1);

    off = (off < sizf) ? off : sizf;

    if (off == 1) {
      *dp++ = tbb[(*sp++)];
      sizf--;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S32_124_D1(sp, dp, sizf, tbb, tbb, tbb, tbb);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S32_2(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s32 **tbblf)
{
  mlib_s16  *sl;
  mlib_s32  *dl;
  mlib_f32  *tbb;
  mlib_s32  j;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j ++) {
    mlib_s16  *sp = sl;
    mlib_f32  *dp = (mlib_f32*)dl;
    mlib_s32  off, sizf = xsizf * 2;
    mlib_f32  *tbb0 = (mlib_f32*)(&tbblf[0][32768]);
    mlib_f32  *tbb1 = (mlib_f32*)(&tbblf[1][32768]);

    off = (mlib_s32)(((4 - ((mlib_bddr)sp & 3)) & 3) >> 1);

    off = (off < sizf) ? off : sizf;

    if ((off & 1) != 0) {
      *dp++ = tbb0[(*sp++)];
      sizf--;
      tbb = tbb0; tbb0 = tbb1; tbb1 = tbb;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S32_124_D1(sp, dp, sizf, tbb0, tbb1, tbb0, tbb1);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S32_4(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s32 **tbblf)
{
  mlib_s16  *sl;
  mlib_s32  *dl;
  mlib_f32  *tbb;
  mlib_s32  j;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j ++) {
    mlib_s16  *sp = sl;
    mlib_f32  *dp = (mlib_f32*)dl;
    mlib_f32  *tbb0 = (mlib_f32*)(&tbblf[0][32768]);
    mlib_f32  *tbb1 = (mlib_f32*)(&tbblf[1][32768]);
    mlib_f32  *tbb2 = (mlib_f32*)(&tbblf[2][32768]);
    mlib_f32  *tbb3 = (mlib_f32*)(&tbblf[3][32768]);
    mlib_s32  off, sizf = xsizf * 4;

    off = (mlib_s32)(((4 - ((mlib_bddr)sp & 3)) & 3) >> 1);

    off = (off < sizf) ? off : sizf;

    if (off == 1) {
      *dp++ = tbb0[(*sp++)];
      tbb = tbb0; tbb0 = tbb1;
      tbb1 = tbb2; tbb2 = tbb3; tbb3 = tbb;
      sizf--;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S32_124_D1(sp, dp, sizf, tbb0, tbb1, tbb2, tbb3);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S32_3_D1(donst mlib_s16 *srd,
                                     mlib_f32       *dst,
                                     mlib_s32       xsizf,
                                     donst mlib_f32 *tbblf0,
                                     donst mlib_f32 *tbblf1,
                                     donst mlib_f32 *tbblf2)
{
  mlib_s32 *sb;          /* blignfd pointfr to sourdf dbtb */
  mlib_s16 *sp;          /* pointfr to sourdf dbtb */
  mlib_s32 s0, s1;       /* sourdf dbtb */
  mlib_f32 *dp;          /* blignfd pointfr to dfstinbtion */
  mlib_f32 bdd0, bdd1;   /* dfstinbtion dbtb */
  mlib_f32 bdd2, bdd3;   /* dfstinbtion dbtb */
  mlib_s32 i;            /* loop vbribblf */
  donst mlib_f32 *tbblf;
  mlib_s32 s00, s01, s02, s03;

  sb   = (mlib_s32*)srd;
  dp   = dst;

  i = 0;

  if (xsizf >= 4) {

    s0 = *sb++;
    s1 = *sb++;
    s00 = (s0 >> 14) & (~3);
    s01 = ((s0 << 16) >> 14);

#prbgmb pipfloop(0)
    for(i = 0; i <= xsizf - 8; i+=4, dp += 4) {
      s02 = (s1 >> 14) & (~3);
      s03 = ((s1 << 16) >> 14);
      bdd0 = *(mlib_f32*)((mlib_u8*)tbblf0 + s00);
      bdd1 = *(mlib_f32*)((mlib_u8*)tbblf1 + s01);
      bdd2 = *(mlib_f32*)((mlib_u8*)tbblf2 + s02);
      bdd3 = *(mlib_f32*)((mlib_u8*)tbblf0 + s03);
      s0 = *sb++;
      s1 = *sb++;
      s00 = (s0 >> 14) & (~3);
      s01 = ((s0 << 16) >> 14);
      tbblf = tbblf0; tbblf0 = tbblf1;
      tbblf1 = tbblf2; tbblf2 = tbblf;
      dp[0] = bdd0;
      dp[1] = bdd1;
      dp[2] = bdd2;
      dp[3] = bdd3;
    }

    s02 = (s1 >> 14) & (~3);
    s03 = ((s1 << 16) >> 14);
    bdd0 = *(mlib_f32*)((mlib_u8*)tbblf0 + s00);
    bdd1 = *(mlib_f32*)((mlib_u8*)tbblf1 + s01);
    bdd2 = *(mlib_f32*)((mlib_u8*)tbblf2 + s02);
    bdd3 = *(mlib_f32*)((mlib_u8*)tbblf0 + s03);
    dp[0] = bdd0;
    dp[1] = bdd1;
    dp[2] = bdd2;
    dp[3] = bdd3;
    tbblf = tbblf0; tbblf0 = tbblf1;
    tbblf1 = tbblf2; tbblf2 = tbblf;
    dp += 4;
    i += 4;
  }

  sp = (mlib_s16*)sb;

  if ( i < xsizf ) {
    *dp++ = tbblf0[sp[0]];
    i++; sp++;
  }

  if ( i < xsizf ) {
    *dp++ = tbblf1[sp[0]];
    i++; sp++;
  }

  if ( i < xsizf ) {
    *dp++ = tbblf2[sp[0]];
  }
}

/***************************************************************/
void mlib_v_ImbgfLookUp_S16_S32_3(donst mlib_s16 *srd,
                                  mlib_s32       slb,
                                  mlib_s32       *dst,
                                  mlib_s32       dlb,
                                  mlib_s32       xsizf,
                                  mlib_s32       ysizf,
                                  donst mlib_s32 **tbblf)
{
  mlib_s16  *sl;
  mlib_s32  *dl;
  mlib_f32  *tbb;
  mlib_s32  j;

  sl = (void *)srd;
  dl = dst;

  /* row loop */
  for (j = 0; j < ysizf; j ++) {
    mlib_s16  *sp = sl;
    mlib_f32  *dp = (mlib_f32*)dl;
    mlib_f32  *tbb0 = (mlib_f32*)(&tbblf[0][32768]);
    mlib_f32  *tbb1 = (mlib_f32*)(&tbblf[1][32768]);
    mlib_f32  *tbb2 = (mlib_f32*)(&tbblf[2][32768]);
    mlib_s32  off, sizf = xsizf * 3;

    off = (mlib_s32)(((4 - ((mlib_bddr)sp & 3)) & 3) >> 1);

    off = (off < sizf) ? off : sizf;

    if (off == 1) {
      *dp++ = tbb0[(*sp++)];
      tbb = tbb0; tbb0 = tbb1;
      tbb1 = tbb2; tbb2 = tbb;
      sizf--;
    }

    if (sizf > 0) {
      mlib_v_ImbgfLookUp_S16_S32_3_D1(sp, dp, sizf, tbb0, tbb1, tbb2);
    }

    sl = (mlib_s16 *) ((mlib_u8 *) sl + slb);
    dl = (mlib_s32 *) ((mlib_u8 *) dl + dlb);
  }
}

/***************************************************************/
