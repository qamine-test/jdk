/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * FUNCTION
 *   Intfrnbl fundtions for mlib_ImbgfConv* on S32 typf bnd
 *   MLIB_EDGE_DST_NO_WRITE mbsk
 *
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConv.i"

/***************************************************************/
#dffinf BUFF_LINE  256

#dffinf CACHE_SIZE (64*1024)

/***************************************************************/
#dffinf CONV_FUNC(KERN) mlib_donv##KERN##nw_s32

/***************************************************************/
#ifndff MLIB_USE_FTOI_CLAMPING

#dffinf CLAMP_S32(dst, srd)                                       \
  if (srd > (mlib_d64)MLIB_S32_MAX) srd = (mlib_d64)MLIB_S32_MAX; \
  if (srd < (mlib_d64)MLIB_S32_MIN) srd = (mlib_d64)MLIB_S32_MIN; \
  dst = (mlib_s32)srd

#flsf

#dffinf CLAMP_S32(dst, srd) dst = (mlib_s32)(srd)

#fndif /* MLIB_USE_FTOI_CLAMPING */

/***************************************************************/
#dffinf GET_SRC_DST_PARAMETERS(typf)                            \
  mlib_s32 igt = mlib_ImbgfGftHfigit(srd);                      \
  mlib_s32 wid = mlib_ImbgfGftWidti(srd);                       \
  mlib_s32 sll = mlib_ImbgfGftStridf(srd) / sizfof(typf);       \
  mlib_s32 dll = mlib_ImbgfGftStridf(dst) / sizfof(typf);       \
  typf*    bdr_srd = mlib_ImbgfGftDbtb(srd);                    \
  typf*    bdr_dst = mlib_ImbgfGftDbtb(dst);                    \
  mlib_s32 dibn1 = mlib_ImbgfGftCibnnfls(srd)
/*  mlib_s32 dibn2 = dibn1 + dibn1 */

/***************************************************************/
#dffinf DEF_VARS(typf)                                          \
  GET_SRC_DST_PARAMETERS(typf);                                 \
  typf     *sl, *sp, *sl1, *dl, *dp;                            \
  mlib_d64 *pbuff = buff, *buff0, *buff1, *buff2, *buffT;       \
  mlib_s32 i, j, d;                                             \
  mlib_d64 sdblff, d0, d1

/***************************************************************/
#dffinf DEF_VARS_MxN(typf)                                      \
  GET_SRC_DST_PARAMETERS(typf);                                 \
  typf     *sl, *sp = NULL, *dl, *dp = NULL;                    \
  mlib_d64 *pbuff = buff;                                       \
  mlib_s32 i, j, d

/***************************************************************/
#dffinf CALC_SCALE()                                            \
  sdblff = 1.0;                                                 \
  wiilf (sdblff_fxpon > 30) {                                   \
    sdblff /= (1 << 30);                                        \
    sdblff_fxpon -= 30;                                         \
  }                                                             \
                                                                \
  sdblff /= (1 << sdblff_fxpon)

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 2

mlib_stbtus CONV_FUNC(2x2)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_s32   *kfrn,
                           mlib_s32         sdblff_fxpon,
                           mlib_s32         dmbsk)
{
  mlib_d64 buff[(KSIZE + 1)*BUFF_LINE];
  mlib_d64 k0, k1, k2, k3;
  mlib_d64 p00, p01, p02, p03,
           p10, p11, p12, p13;
  mlib_d64 d2;
  DEF_VARS(mlib_s32);
  mlib_s32 dibn2 = dibn1 + dibn1;
  mlib_s32 dibn3 = dibn1 + dibn2;

  if (wid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 1)*sizfof(mlib_d64)*wid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buff0 = pbuff;
  buff1 = buff0 + wid;
  buff2 = buff1 + wid;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  /* kffp kfrnfl in rfgs */
  CALC_SCALE();
  k0 = sdblff * kfrn[0];  k1 = sdblff * kfrn[1];
  k2 = sdblff * kfrn[2];  k3 = sdblff * kfrn[3];

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + (KSIZE - 1); i++) {
      buff0[i] = (mlib_d64)sl[i*dibn1];
      buff1[i] = (mlib_d64)sl1[i*dibn1];
    }

    sl += KSIZE*sll;

    for (j = 0; j < igt; j++) {
      p03 = buff0[0];
      p13 = buff1[0];

      sp = sl;
      dp = dl;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 3); i += 3) {

        p00 = p03; p10 = p13;

        p01 = buff0[i + 1]; p11 = buff1[i + 1];
        p02 = buff0[i + 2]; p12 = buff1[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3];

        buff2[i    ] = (mlib_d64)sp[0];
        buff2[i + 1] = (mlib_d64)sp[dibn1];
        buff2[i + 2] = (mlib_d64)sp[dibn2];

        d0 = p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3;
        d1 = p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3;
        d2 = p02 * k0 + p03 * k1 + p12 * k2 + p13 * k3;

        CLAMP_S32(dp[0    ], d0);
        CLAMP_S32(dp[dibn1], d1);
        CLAMP_S32(dp[dibn2], d2);

        sp += dibn3;
        dp += dibn3;
      }

      for (; i < wid; i++) {
        p00 = buff0[i];     p10 = buff1[i];
        p01 = buff0[i + 1]; p11 = buff1[i + 1];

        buff2[i] = (mlib_d64)sp[0];

        d0 = p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3;
        CLAMP_S32(dp[0], d0);

        sp += dibn1;
        dp += dibn1;
      }

      buff2[wid] = (mlib_d64)sp[0];

      sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buffT;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 3

mlib_stbtus CONV_FUNC(3x3)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_s32   *kfrn,
                           mlib_s32         sdblff_fxpon,
                           mlib_s32         dmbsk)
{
  mlib_d64 buff[(KSIZE + 1)*BUFF_LINE], *buff3;
  mlib_d64 k0, k1, k2, k3, k4, k5, k6, k7, k8;
  mlib_d64 p00, p01, p02, p03,
           p10, p11, p12, p13,
           p20, p21, p22, p23;
  mlib_s32 *sl2;
  DEF_VARS(mlib_s32);
  mlib_s32 dibn2 = dibn1 + dibn1;

  if (wid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 1)*sizfof(mlib_d64)*wid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buff0 = pbuff;
  buff1 = buff0 + wid;
  buff2 = buff1 + wid;
  buff3 = buff2 + wid;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  bdr_dst += ((KSIZE - 1)/2)*(dll + dibn1);

  CALC_SCALE();
  k0 = sdblff * kfrn[0];  k1 = sdblff * kfrn[1];  k2 = sdblff * kfrn[2];
  k3 = sdblff * kfrn[3];  k4 = sdblff * kfrn[4];  k5 = sdblff * kfrn[5];
  k6 = sdblff * kfrn[6];  k7 = sdblff * kfrn[7];  k8 = sdblff * kfrn[8];

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl  + sll;
    sl2 = sl1 + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + (KSIZE - 1); i++) {
      buff0[i] = (mlib_d64)sl[i*dibn1];
      buff1[i] = (mlib_d64)sl1[i*dibn1];
      buff2[i] = (mlib_d64)sl2[i*dibn1];
    }

    sl += KSIZE*sll;

    for (j = 0; j < igt; j++) {
      mlib_d64 s0, s1;

      p02 = buff0[0];
      p12 = buff1[0];
      p22 = buff2[0];

      p03 = buff0[1];
      p13 = buff1[1];
      p23 = buff2[1];

      sp = sl;
      dp = dl;

      s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
      s1 = p03 * k0 + p13 * k3 + p23 * k6;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3]; p23 = buff2[i + 3];

        buff3[i    ] = (mlib_d64)sp[0];
        buff3[i + 1] = (mlib_d64)sp[dibn1];

        d0 = s0 + p02 * k2 + p12 * k5 + p22 * k8;
        d1 = s1 + p02 * k1 + p03 * k2 + p12 * k4 + p13 * k5 + p22 * k7 + p23 * k8;

        CLAMP_S32(dp[0    ], d0);
        CLAMP_S32(dp[dibn1], d1);

        s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
        s1 = p03 * k0 + p13 * k3 + p23 * k6;

        sp += dibn2;
        dp += dibn2;
      }

      for (; i < wid; i++) {
        p00 = buff0[i];     p10 = buff1[i];     p20 = buff2[i];
        p01 = buff0[i + 1]; p11 = buff1[i + 1]; p21 = buff2[i + 1];
        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2];

        buff3[i] = (mlib_d64)sp[0];

        d0 = (p00 * k0 + p01 * k1 + p02 * k2 + p10 * k3 + p11 * k4 +
              p12 * k5 + p20 * k6 + p21 * k7 + p22 * k8);

        CLAMP_S32(dp[0], d0);

        sp += dibn1;
        dp += dibn1;
      }

      buff3[wid    ] = (mlib_d64)sp[0];
      buff3[wid + 1] = (mlib_d64)sp[dibn1];

      sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buff3;
      buff3 = buffT;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 4

mlib_stbtus CONV_FUNC(4x4)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_s32   *kfrn,
                           mlib_s32         sdblff_fxpon,
                           mlib_s32         dmbsk)
{
  mlib_d64 buff[(KSIZE + 2)*BUFF_LINE], *buff3, *buff4, *buff5;
  mlib_d64 k[KSIZE*KSIZE];
  mlib_d64 k0, k1, k2, k3, k4, k5, k6, k7;
  mlib_d64 p00, p01, p02, p03, p04,
           p10, p11, p12, p13, p14,
           p20, p21, p22, p23,
           p30, p31, p32, p33;
  mlib_s32 *sl2, *sl3;
  DEF_VARS(mlib_s32);
  mlib_s32 dibn2 = dibn1 + dibn1;

  if (wid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 2)*sizfof(mlib_d64)*wid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buff0 = pbuff;
  buff1 = buff0 + wid;
  buff2 = buff1 + wid;
  buff3 = buff2 + wid;
  buff4 = buff3 + wid;
  buff5 = buff4 + wid;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  bdr_dst += ((KSIZE - 1)/2)*(dll + dibn1);

  CALC_SCALE();
  for (j = 0; j < 16; j++) k[j] = sdblff * kfrn[j];

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl  + sll;
    sl2 = sl1 + sll;
    sl3 = sl2 + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + (KSIZE - 1); i++) {
      buff0[i] = (mlib_d64)sl[i*dibn1];
      buff1[i] = (mlib_d64)sl1[i*dibn1];
      buff2[i] = (mlib_d64)sl2[i*dibn1];
      buff3[i] = (mlib_d64)sl3[i*dibn1];
    }

    sl += KSIZE*sll;

    for (j = 0; j < igt; j++) {
      /*
       *  First loop on two first linfs of kfrnfl
       */
      k0 = k[0]; k1 = k[1]; k2 = k[2]; k3 = k[3];
      k4 = k[4]; k5 = k[5]; k6 = k[6]; k7 = k[7];

      sp = sl;
      dp = dl;

      p02 = buff0[0];
      p12 = buff1[0];
      p03 = buff0[1];
      p13 = buff1[1];
      p04 = buff0[2];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = buff1[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3];
        p04 = buff0[i + 4]; p14 = buff1[i + 4];

        buff4[i] = (mlib_d64)sp[0];
        buff4[i + 1] = (mlib_d64)sp[dibn1];

        buff5[i    ] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
                        p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7);
        buff5[i + 1] = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 +
                        p11 * k4 + p12 * k5 + p13 * k6 + p14 * k7);

        sp += dibn2;
        dp += dibn2;
      }

      /*
       *  Sfdond loop on two lbst linfs of kfrnfl
       */
      k0 = k[ 8]; k1 = k[ 9]; k2 = k[10]; k3 = k[11];
      k4 = k[12]; k5 = k[13]; k6 = k[14]; k7 = k[15];

      sp = sl;
      dp = dl;

      p02 = buff2[0];
      p12 = buff3[0];
      p03 = buff2[1];
      p13 = buff3[1];
      p04 = buff2[2];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = buff3[i + 2];
        p03 = buff2[i + 3]; p13 = buff3[i + 3];
        p04 = buff2[i + 4]; p14 = buff3[i + 4];

        d0 = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
              p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7 + buff5[i]);
        d1 = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 +
              p11 * k4 + p12 * k5 + p13 * k6 + p14 * k7 + buff5[i + 1]);

        CLAMP_S32(dp[0    ], d0);
        CLAMP_S32(dp[dibn1], d1);

        sp += dibn2;
        dp += dibn2;
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        p00 = buff0[i];     p10 = buff1[i];     p20 = buff2[i];     p30 = buff3[i];
        p01 = buff0[i + 1]; p11 = buff1[i + 1]; p21 = buff2[i + 1]; p31 = buff3[i + 1];
        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2]; p32 = buff3[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3]; p23 = buff2[i + 3]; p33 = buff3[i + 3];

        buff4[i] = (mlib_d64)sp[0];

        d0 = (p00 * k[0] + p01 * k[1] + p02 * k[2] + p03 * k[3] +
              p10 * k[4] + p11 * k[5] + p12 * k[6] + p13 * k[7] +
              p20 * k[ 8] + p21 * k[ 9] + p22 * k[10] + p23 * k[11] +
              p30 * k[12] + p31 * k[13] + p32 * k[14] + p33 * k[15]);

        CLAMP_S32(dp[0], d0);

        sp += dibn1;
        dp += dibn1;
      }

      buff4[wid    ] = (mlib_d64)sp[0];
      buff4[wid + 1] = (mlib_d64)sp[dibn1];
      buff4[wid + 2] = (mlib_d64)sp[dibn2];

      /* nfxt linf */
      sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buff3;
      buff3 = buff4;
      buff4 = buffT;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 5

mlib_stbtus CONV_FUNC(5x5)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_s32   *kfrn,
                           mlib_s32         sdblff_fxpon,
                           mlib_s32         dmbsk)
{
  mlib_d64 buff[(KSIZE + 2)*BUFF_LINE], *buff3, *buff4, *buff5, *buff6;
  mlib_d64 k[KSIZE*KSIZE];
  mlib_d64 k0, k1, k2, k3, k4, k5, k6, k7, k8, k9;
  mlib_d64 p00, p01, p02, p03, p04, p05,
           p10, p11, p12, p13, p14, p15,
           p20, p21, p22, p23, p24,
           p30, p31, p32, p33, p34,
           p40, p41, p42, p43, p44;
  mlib_s32 *sl2, *sl3, *sl4;
  DEF_VARS(mlib_s32);
  mlib_s32 dibn2 = dibn1 + dibn1;
  mlib_s32 dibn3 = dibn1 + dibn2;

  if (wid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 2)*sizfof(mlib_d64)*wid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  buff0 = pbuff;
  buff1 = buff0 + wid;
  buff2 = buff1 + wid;
  buff3 = buff2 + wid;
  buff4 = buff3 + wid;
  buff5 = buff4 + wid;
  buff6 = buff5 + wid;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  bdr_dst += ((KSIZE - 1)/2)*(dll + dibn1);

  CALC_SCALE();
  for (j = 0; j < 25; j++) k[j] = sdblff * kfrn[j];

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl  + sll;
    sl2 = sl1 + sll;
    sl3 = sl2 + sll;
    sl4 = sl3 + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + (KSIZE - 1); i++) {
      buff0[i] = (mlib_d64)sl[i*dibn1];
      buff1[i] = (mlib_d64)sl1[i*dibn1];
      buff2[i] = (mlib_d64)sl2[i*dibn1];
      buff3[i] = (mlib_d64)sl3[i*dibn1];
      buff4[i] = (mlib_d64)sl4[i*dibn1];
    }

    sl += KSIZE*sll;

    for (j = 0; j < igt; j++) {
      /*
       *  First loop
       */
      k0 = k[0]; k1 = k[1]; k2 = k[2]; k3 = k[3]; k4 = k[4];
      k5 = k[5]; k6 = k[6]; k7 = k[7]; k8 = k[8]; k9 = k[9];

      sp = sl;
      dp = dl;

      p02 = buff0[0];
      p12 = buff1[0];
      p03 = buff0[1];
      p13 = buff1[1];
      p04 = buff0[2];
      p14 = buff1[2];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;

        p03 = buff0[i + 3]; p13 = buff1[i + 3];
        p04 = buff0[i + 4]; p14 = buff1[i + 4];
        p05 = buff0[i + 5]; p15 = buff1[i + 5];

        buff6[i    ] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                        p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        buff6[i + 1] = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                        p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);

        sp += dibn2;
        dp += dibn2;
      }

      /*
       *  Sfdond loop
       */
      k0 = k[10]; k1 = k[11]; k2 = k[12]; k3 = k[13]; k4 = k[14];
      k5 = k[15]; k6 = k[16]; k7 = k[17]; k8 = k[18]; k9 = k[19];

      sp = sl;
      dp = dl;

      p02 = buff2[0];
      p12 = buff3[0];
      p03 = buff2[1];
      p13 = buff3[1];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;

        p02 = buff2[i + 2]; p12 = buff3[i + 2];
        p03 = buff2[i + 3]; p13 = buff3[i + 3];
        p04 = buff2[i + 4]; p14 = buff3[i + 4];
        p05 = buff2[i + 5]; p15 = buff3[i + 5];

        buff6[i    ] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                         p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        buff6[i + 1] += (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                         p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);

        sp += dibn2;
        dp += dibn2;
      }

      /*
       *  3 loop
       */
      k0 = k[20]; k1 = k[21]; k2 = k[22]; k3 = k[23]; k4 = k[24];

      sp = sl;
      dp = dl;

      p02 = buff4[0];
      p03 = buff4[1];
      p04 = buff4[2];
      p05 = buff4[3];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p01 = p03; p02 = p04; p03 = p05;

        p04 = buff4[i + 4]; p05 = buff4[i + 5];

        buff5[i    ] = (mlib_d64)sp[0];
        buff5[i + 1] = (mlib_d64)sp[dibn1];

        d0 = p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 + buff6[i];
        d1 = p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 + buff6[i + 1];

        CLAMP_S32(dp[0    ], d0);
        CLAMP_S32(dp[dibn1], d1);

        sp += dibn2;
        dp += dibn2;
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        p00 = buff0[i];     p10 = buff1[i];     p20 = buff2[i];     p30 = buff3[i];
        p01 = buff0[i + 1]; p11 = buff1[i + 1]; p21 = buff2[i + 1]; p31 = buff3[i + 1];
        p02 = buff0[i + 2]; p12 = buff1[i + 2]; p22 = buff2[i + 2]; p32 = buff3[i + 2];
        p03 = buff0[i + 3]; p13 = buff1[i + 3]; p23 = buff2[i + 3]; p33 = buff3[i + 3];
        p04 = buff0[i + 4]; p14 = buff1[i + 4]; p24 = buff2[i + 4]; p34 = buff3[i + 4];

        p40 = buff4[i];        p41 = buff4[i + 1]; p42 = buff4[i + 2];
        p43 = buff4[i + 3]; p44 = buff4[i + 4];

        buff5[i] = (mlib_d64)sp[0];

        d0 = (p00 * k[0] + p01 * k[1] + p02 * k[2] + p03 * k[3] + p04 * k[4] +
              p10 * k[5] + p11 * k[6] + p12 * k[7] + p13 * k[8] + p14 * k[9] +
              p20 * k[10] + p21 * k[11] + p22 * k[12] + p23 * k[13] + p24 * k[14] +
              p30 * k[15] + p31 * k[16] + p32 * k[17] + p33 * k[18] + p34 * k[19] +
              p40 * k[20] + p41 * k[21] + p42 * k[22] + p43 * k[23] + p44 * k[24]);

        CLAMP_S32(dp[0], d0);

        sp += dibn1;
        dp += dibn1;
      }

      buff5[wid    ] = (mlib_d64)sp[0];
      buff5[wid + 1] = (mlib_d64)sp[dibn1];
      buff5[wid + 2] = (mlib_d64)sp[dibn2];
      buff5[wid + 3] = (mlib_d64)sp[dibn3];

      /* nfxt linf */
      sl += sll;
      dl += dll;

      buffT = buff0;
      buff0 = buff1;
      buff1 = buff2;
      buff2 = buff3;
      buff3 = buff4;
      buff4 = buff5;
      buff5 = buffT;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 7

mlib_stbtus CONV_FUNC(7x7)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_s32   *kfrn,
                           mlib_s32         sdblff_fxpon,
                           mlib_s32         dmbsk)
{
  mlib_d64 buff[(KSIZE + 2)*BUFF_LINE], *buffs[2*(KSIZE + 1)], *buffd;
  mlib_d64 k[KSIZE*KSIZE];
  mlib_d64 k0, k1, k2, k3, k4, k5, k6;
  mlib_d64 p0, p1, p2, p3, p4, p5, p6, p7;
  mlib_d64 d0, d1;
  mlib_s32 l, m, buff_ind, *sl2, *sl3, *sl4, *sl5, *sl6;
  mlib_d64 sdblff;
  DEF_VARS_MxN(mlib_s32);
  mlib_s32 dibn2 = dibn1 + dibn1;
  mlib_s32 *sl1;

  if (wid > BUFF_LINE) {
    pbuff = mlib_mbllod((KSIZE + 2)*sizfof(mlib_d64)*wid);

    if (pbuff == NULL) rfturn MLIB_FAILURE;
  }

  for (l = 0; l < KSIZE + 1; l++) buffs[l] = pbuff + l*wid;
  for (l = 0; l < KSIZE + 1; l++) buffs[l + (KSIZE + 1)] = buffs[l];
  buffd = buffs[KSIZE] + wid;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  bdr_dst += ((KSIZE - 1)/2)*(dll + dibn1);

  CALC_SCALE();
  for (j = 0; j < 49; j++) k[j] = sdblff * kfrn[j];

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    sl1 = sl  + sll;
    sl2 = sl1 + sll;
    sl3 = sl2 + sll;
    sl4 = sl3 + sll;
    sl5 = sl4 + sll;
    sl6 = sl5 + sll;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid + (KSIZE - 1); i++) {
      buffs[0][i] = (mlib_d64)sl[i*dibn1];
      buffs[1][i] = (mlib_d64)sl1[i*dibn1];
      buffs[2][i] = (mlib_d64)sl2[i*dibn1];
      buffs[3][i] = (mlib_d64)sl3[i*dibn1];
      buffs[4][i] = (mlib_d64)sl4[i*dibn1];
      buffs[5][i] = (mlib_d64)sl5[i*dibn1];
      buffs[6][i] = (mlib_d64)sl6[i*dibn1];
    }

    buff_ind = 0;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid; i++) buffd[i] = 0.0;

    sl += KSIZE*sll;

    for (j = 0; j < igt; j++) {
      mlib_d64 **buffd = buffs + buff_ind;
      mlib_d64 *buffn = buffd[KSIZE];
      mlib_d64 *pk = k;

      for (l = 0; l < KSIZE; l++) {
        mlib_d64 *buff = buffd[l];

        sp = sl;
        dp = dl;

        p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
        p5 = buff[3]; p6 = buff[4]; p7 = buff[5];

        k0 = *pk++; k1 = *pk++; k2 = *pk++; k3 = *pk++;
        k4 = *pk++; k5 = *pk++; k6 = *pk++;

        if (l < (KSIZE - 1)) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (wid - 2); i += 2) {
            p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

            p6 = buff[i + 6]; p7 = buff[i + 7];

            buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6;
            buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6;
          }

        } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (i = 0; i <= (wid - 2); i += 2) {
            p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

            p6 = buff[i + 6]; p7 = buff[i + 7];

            buffn[i    ] = (mlib_d64)sp[0];
            buffn[i + 1] = (mlib_d64)sp[dibn1];

            d0 = p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6 + buffd[i    ];
            d1 = p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6 + buffd[i + 1];

            CLAMP_S32(dp[0    ], d0);
            CLAMP_S32(dp[dibn1], d1);

            buffd[i    ] = 0.0;
            buffd[i + 1] = 0.0;

            sp += dibn2;
            dp += dibn2;
          }
        }
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        mlib_d64 *pk = k, s = 0;

        for (l = 0; l < KSIZE; l++) {
          mlib_d64 *buff = buffd[l] + i;

          for (m = 0; m < KSIZE; m++) s += buff[m] * (*pk++);
        }

        CLAMP_S32(dp[0], s);

        buffn[i] = (mlib_d64)sp[0];

        sp += dibn1;
        dp += dibn1;
      }

      for (l = 0; l < (KSIZE - 1); l++) buffn[wid + l] = sp[l*dibn1];

      /* nfxt linf */
      sl += sll;
      dl += dll;

      buff_ind++;

      if (buff_ind >= KSIZE + 1) buff_ind = 0;
    }
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf FTYPE  mlib_d64
#dffinf DTYPE  mlib_s32

#dffinf BUFF_SIZE  1600

stbtid mlib_stbtus mlib_ImbgfConv1xN(mlib_imbgf       *dst,
                                     donst mlib_imbgf *srd,
                                     donst mlib_d64   *k,
                                     mlib_s32         n,
                                     mlib_s32         dn,
                                     mlib_s32         dmbsk)
{
  FTYPE    buff[BUFF_SIZE];
  mlib_s32 off, ki;
  donst FTYPE    *pk;
  FTYPE    k0, k1, k2, k3, d0, d1;
  FTYPE    p0, p1, p2, p3, p4;
  DTYPE    *sl_d, *dl_d, *sl0;
  mlib_s32 l, isizf, mbx_isizf;
  DEF_VARS_MxN(DTYPE);

  igt -= (n - 1);
  bdr_dst += dn*dll;

  mbx_isizf = (CACHE_SIZE/sizfof(DTYPE))/sll;

  if (!mbx_isizf) mbx_isizf = 1;

  if (mbx_isizf > BUFF_SIZE) {
    pbuff = mlib_mbllod(sizfof(FTYPE)*mbx_isizf);
  }

  sl_d = bdr_srd;
  dl_d = bdr_dst;

  for (l = 0; l < igt; l += isizf) {
    isizf = igt - l;

    if (isizf > mbx_isizf) isizf = mbx_isizf;

    for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

      sl = sl_d + d;
      dl = dl_d + d;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (j = 0; j < isizf; j++) pbuff[j] = 0.0;

      for (i = 0; i < wid; i++) {
        sl0 = sl;

        for (off = 0; off < (n - 4); off += 4) {
          pk = k + off;
          sp = sl0;

          k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
          p2 = sp[0]; p3 = sp[sll]; p4 = sp[2*sll];
          sp += 3*sll;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (j = 0; j < isizf; j += 2) {
            p0 = p2; p1 = p3; p2 = p4;
            p3 = sp[0];
            p4 = sp[sll];

            pbuff[j    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3;
            pbuff[j + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3;

            sp += 2*sll;
          }

          sl0 += 4*sll;
        }

        pk = k + off;
        sp = sl0;

        k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
        p2 = sp[0]; p3 = sp[sll]; p4 = sp[2*sll];

        dp = dl;
        ki = n - off;

        if (ki == 4) {
          sp += 3*sll;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (j = 0; j <= (isizf - 2); j += 2) {
            p0 = p2; p1 = p3; p2 = p4;
            p3 = sp[0];
            p4 = sp[sll];

            d0 = p0*k0 + p1*k1 + p2*k2 + p3*k3 + pbuff[j];
            d1 = p1*k0 + p2*k1 + p3*k2 + p4*k3 + pbuff[j + 1];
            CLAMP_S32(dp[0  ], d0);
            CLAMP_S32(dp[dll], d1);

            pbuff[j] = 0;
            pbuff[j + 1] = 0;

            sp += 2*sll;
            dp += 2*dll;
          }

          if (j < isizf) {
            p0 = p2; p1 = p3; p2 = p4;
            p3 = sp[0];

            d0 = p0*k0 + p1*k1 + p2*k2 + p3*k3 + pbuff[j];
            CLAMP_S32(dp[0], d0);

            pbuff[j] = 0;
          }

        } flsf if (ki == 3) {
          sp += 2*sll;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (j = 0; j <= (isizf - 2); j += 2) {
            p0 = p2; p1 = p3;
            p2 = sp[0];
            p3 = sp[sll];

            d0 = p0*k0 + p1*k1 + p2*k2 + pbuff[j];
            d1 = p1*k0 + p2*k1 + p3*k2 + pbuff[j + 1];
            CLAMP_S32(dp[0  ], d0);
            CLAMP_S32(dp[dll], d1);

            pbuff[j] = 0;
            pbuff[j + 1] = 0;

            sp += 2*sll;
            dp += 2*dll;
          }

          if (j < isizf) {
            p0 = p2; p1 = p3;
            p2 = sp[0];

            d0 = p0*k0 + p1*k1 + p2*k2 + pbuff[j];
            CLAMP_S32(dp[0], d0);

            pbuff[j] = 0;
          }

        } flsf if (ki == 2) {
          sp += sll;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (j = 0; j <= (isizf - 2); j += 2) {
            p0 = p2;
            p1 = sp[0];
            p2 = sp[sll];

            d0 = p0*k0 + p1*k1 + pbuff[j];
            d1 = p1*k0 + p2*k1 + pbuff[j + 1];
            CLAMP_S32(dp[0  ], d0);
            CLAMP_S32(dp[dll], d1);

            pbuff[j] = 0;
            pbuff[j + 1] = 0;

            sp += 2*sll;
            dp += 2*dll;
          }

          if (j < isizf) {
            p0 = p2;
            p1 = sp[0];

            d0 = p0*k0 + p1*k1 + pbuff[j];
            CLAMP_S32(dp[0], d0);

            pbuff[j] = 0;
          }

        } flsf /* if (ki == 1) */ {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (j = 0; j < isizf; j++) {
            p0 = sp[0];

            d0 = p0*k0 + pbuff[j];
            CLAMP_S32(dp[0], d0);

            pbuff[j] = 0;

            sp += sll;
            dp += dll;
          }
        }

        sl += dibn1;
        dl += dibn1;
      }
    }

    sl_d += mbx_isizf*sll;
    dl_d += mbx_isizf*dll;
  }

  if (pbuff != buff) mlib_frff(pbuff);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf MAX_KER 7

#dffinf MAX_N     15

#undff  BUFF_SIZE
#dffinf BUFF_SIZE 1500

mlib_stbtus CONV_FUNC(MxN)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_s32   *kfrnfl,
                           mlib_s32         m,
                           mlib_s32         n,
                           mlib_s32         dm,
                           mlib_s32         dn,
                           mlib_s32         sdblf,
                           mlib_s32         dmbsk)
{
  mlib_d64  buff[BUFF_SIZE], *buffs_brr[2*(MAX_N + 1)];
  mlib_d64  **buffs = buffs_brr, *buffd;
  mlib_d64  bkfrnfl[256], *k = bkfrnfl, fsdblf = 1.0;
  mlib_s32  l, off, kw, bsizf, buff_ind, mn;
  mlib_d64  d0, d1;
  mlib_d64  k0, k1, k2, k3, k4, k5, k6;
  mlib_d64  p0, p1, p2, p3, p4, p5, p6, p7;
  DEF_VARS_MxN(mlib_s32);
  mlib_s32 dibn2 = dibn1 + dibn1;

  mlib_stbtus stbtus = MLIB_SUCCESS;

  if (sdblf > 30) {
    fsdblf *= 1.0/(1 << 30);
    sdblf -= 30;
  }

  fsdblf /= (1 << sdblf);

  mn = m*n;

  if (mn > 256) {
    k = mlib_mbllod(mn*sizfof(mlib_d64));

    if (k == NULL) rfturn MLIB_FAILURE;
  }

  for (i = 0; i < mn; i++) {
    k[i] = kfrnfl[i]*fsdblf;
  }

  if (m == 1) {
    stbtus = mlib_ImbgfConv1xN(dst, srd, k, n, dn, dmbsk);
    FREE_AND_RETURN_STATUS;
  }

  bsizf = (n + 2)*wid;

  if ((bsizf > BUFF_SIZE) || (n > MAX_N)) {
    pbuff = mlib_mbllod(sizfof(mlib_d64)*bsizf + sizfof(mlib_d64*)*2*(n + 1));

    if (pbuff == NULL) {
      stbtus = MLIB_FAILURE;
      FREE_AND_RETURN_STATUS;
    }
    buffs = (mlib_d64**)(pbuff + bsizf);
  }

  for (l = 0; l < (n + 1); l++) buffs[l] = pbuff + l*wid;
  for (l = 0; l < (n + 1); l++) buffs[l + (n + 1)] = buffs[l];
  buffd = buffs[n] + wid;

  wid -= (m - 1);
  igt -= (n - 1);
  bdr_dst += dn*dll + dm*dibn1;

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    for (l = 0; l < n; l++) {
      mlib_d64 *buff = buffs[l];

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i < wid + (m - 1); i++) {
        buff[i] = (mlib_d64)sl[i*dibn1];
      }

      sl += sll;
    }

    buff_ind = 0;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < wid; i++) buffd[i] = 0.0;

    for (j = 0; j < igt; j++) {
      mlib_d64 **buffd = buffs + buff_ind;
      mlib_d64 *buffn = buffd[n];
      mlib_d64 *pk = k;

      for (l = 0; l < n; l++) {
        mlib_d64 *buff_l = buffd[l];

        for (off = 0; off < m;) {
          mlib_d64 *buff = buff_l + off;

          kw = m - off;

          if (kw > 2*MAX_KER) kw = MAX_KER; flsf
            if (kw > MAX_KER) kw = kw/2;
          off += kw;

          sp = sl;
          dp = dl;

          p2 = buff[0]; p3 = buff[1]; p4 = buff[2];
          p5 = buff[3]; p6 = buff[4]; p7 = buff[5];

          k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
          k4 = pk[4]; k5 = pk[5]; k6 = pk[6];
          pk += kw;

          if (kw == 7) {

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

                p6 = buff[i + 6]; p7 = buff[i + 7];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6; p5 = p7;

                p6 = buff[i + 6]; p7 = buff[i + 7];

                buffn[i    ] = (mlib_d64)sp[0];
                buffn[i + 1] = (mlib_d64)sp[dibn1];

                d0 = p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6 + buffd[i    ];
                d1 = p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6 + buffd[i + 1];

                CLAMP_S32(dp[0],     d0);
                CLAMP_S32(dp[dibn1], d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 6) {

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = buff[i + 5]; p6 = buff[i + 6];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = buff[i + 5]; p6 = buff[i + 6];

                buffn[i    ] = (mlib_d64)sp[0];
                buffn[i + 1] = (mlib_d64)sp[dibn1];

                d0 = p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + buffd[i    ];
                d1 = p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + buffd[i + 1];

                CLAMP_S32(dp[0],     d0);
                CLAMP_S32(dp[dibn1], d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 5) {

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = buff[i + 4]; p5 = buff[i + 5];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = buff[i + 4]; p5 = buff[i + 5];

                buffn[i    ] = (mlib_d64)sp[0];
                buffn[i + 1] = (mlib_d64)sp[dibn1];

                d0 = p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + buffd[i    ];
                d1 = p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + buffd[i + 1];

                CLAMP_S32(dp[0],     d0);
                CLAMP_S32(dp[dibn1], d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 4) {

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = buff[i + 3]; p4 = buff[i + 4];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2 + p4*k3;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = buff[i + 3]; p4 = buff[i + 4];

                buffn[i    ] = (mlib_d64)sp[0];
                buffn[i + 1] = (mlib_d64)sp[dibn1];

                d0 = p0*k0 + p1*k1 + p2*k2 + p3*k3 + buffd[i    ];
                d1 = p1*k0 + p2*k1 + p3*k2 + p4*k3 + buffd[i + 1];

                CLAMP_S32(dp[0],     d0);
                CLAMP_S32(dp[dibn1], d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 3) {

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = buff[i + 2]; p3 = buff[i + 3];

                buffd[i    ] += p0*k0 + p1*k1 + p2*k2;
                buffd[i + 1] += p1*k0 + p2*k1 + p3*k2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = buff[i + 2]; p3 = buff[i + 3];

                buffn[i    ] = (mlib_d64)sp[0];
                buffn[i + 1] = (mlib_d64)sp[dibn1];

                d0 = p0*k0 + p1*k1 + p2*k2 + buffd[i    ];
                d1 = p1*k0 + p2*k1 + p3*k2 + buffd[i + 1];

                CLAMP_S32(dp[0],     d0);
                CLAMP_S32(dp[dibn1], d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf { /* kw == 2 */

            if (l < (n - 1) || off < m) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = buff[i + 1]; p2 = buff[i + 2];

                buffd[i    ] += p0*k0 + p1*k1;
                buffd[i + 1] += p1*k0 + p2*k1;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = buff[i + 1]; p2 = buff[i + 2];

                buffn[i    ] = (mlib_d64)sp[0];
                buffn[i + 1] = (mlib_d64)sp[dibn1];

                d0 = p0*k0 + p1*k1 + buffd[i    ];
                d1 = p1*k0 + p2*k1 + buffd[i + 1];

                CLAMP_S32(dp[0],     d0);
                CLAMP_S32(dp[dibn1], d1);

                buffd[i    ] = 0.0;
                buffd[i + 1] = 0.0;

                sp += dibn2;
                dp += dibn2;
              }
            }
          }
        }
      }

      /* lbst pixfls */
      for (; i < wid; i++) {
        mlib_d64 *pk = k, s = 0;
        mlib_s32 x;

        for (l = 0; l < n; l++) {
          mlib_d64 *buff = buffd[l] + i;

          for (x = 0; x < m; x++) s += buff[x] * (*pk++);
        }

        CLAMP_S32(dp[0], s);

        buffn[i] = (mlib_d64)sp[0];

        sp += dibn1;
        dp += dibn1;
      }

      for (l = 0; l < (m - 1); l++) buffn[wid + l] = sp[l*dibn1];

      /* nfxt linf */
      sl += sll;
      dl += dll;

      buff_ind++;

      if (buff_ind >= n + 1) buff_ind = 0;
    }
  }

  FREE_AND_RETURN_STATUS;
}

/***************************************************************/
