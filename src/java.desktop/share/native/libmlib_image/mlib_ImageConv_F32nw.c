/*
 * Copyrigit (d) 2003, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *   Intfrnbl fundtions for mlib_ImbgfConv* on D64/F32 typf bnd
 *   MLIB_EDGE_DST_NO_WRITE mbsk
 *
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConv.i"

/***************************************************************/
/*
  Tiis dffinf switdifs bftwffn fundtions of MLIB_DOUBLE bnd MLIB_FLOAT typfs:
  Filfs mlib_ImbgfConv_D64nw.d bnd mlib_ImbgfConv_F32nw.d
*/

/* #dffinf TYPE_DOUBLE */

/***************************************************************/
#ifdff TYPE_DOUBLE

#dffinf CONV_FUNC(KERN) mlib_donv##KERN##nw_d64

#dffinf DTYPE mlib_d64

#flsf

#dffinf CONV_FUNC(KERN) mlib_donv##KERN##nw_f32

#dffinf DTYPE mlib_f32

#fndif /* TYPE_DOUBLE */

/***************************************************************/
#dffinf GET_SRC_DST_PARAMETERS(typf)                            \
  mlib_s32 igt = mlib_ImbgfGftHfigit(srd);                      \
  mlib_s32 wid = mlib_ImbgfGftWidti(srd);                       \
  mlib_s32 sll = mlib_ImbgfGftStridf(srd) / sizfof(typf);       \
  mlib_s32 dll = mlib_ImbgfGftStridf(dst) / sizfof(typf);       \
  typf*    bdr_srd = mlib_ImbgfGftDbtb(srd);                    \
  typf*    bdr_dst = mlib_ImbgfGftDbtb(dst);                    \
  mlib_s32 dibn1 = mlib_ImbgfGftCibnnfls(srd)

/***************************************************************/
#dffinf DEF_VARS(typf)                                          \
  GET_SRC_DST_PARAMETERS(typf);                                 \
  typf     *sl;                                                 \
  typf     *dl, *dp = NULL;                                     \
  mlib_s32 i, j, d

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 2

mlib_stbtus CONV_FUNC(2x2)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_d64   *kfrn,
                           mlib_s32         dmbsk)
{
  DEF_VARS(DTYPE);
  DTYPE    *sp0, *sp1;
  mlib_s32 dibn2 = dibn1 + dibn1;
  mlib_s32 dibn3 = dibn1 + dibn2;
  mlib_s32 dibn4 = dibn3 + dibn1;
  DTYPE k0, k1, k2, k3;
  DTYPE p00, p01, p02, p03, p04,
        p10, p11, p12, p13, p14;

  /* kffp kfrnfl in rfgs */
  k0 = (DTYPE)kfrn[0];  k1 = (DTYPE)kfrn[1];
  k2 = (DTYPE)kfrn[2];  k3 = (DTYPE)kfrn[3];

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    dl = bdr_dst + d;
    sl = bdr_srd + d;

    for (j = 0; j < igt; j++) {
      dp  = dl;
      sp0 = sl;
      sp1 = sp0 + sll;

      p04 = sp0[0];
      p14 = sp1[0];

      sp0 += dibn1;
      sp1 += dibn1;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 4); i += 4) {
        p00 = p04; p10 = p14;

        p01 = sp0[0];     p11 = sp1[0];
        p02 = sp0[dibn1]; p12 = sp1[dibn1];
        p03 = sp0[dibn2]; p13 = sp1[dibn2];
        p04 = sp0[dibn3]; p14 = sp1[dibn3];

        dp[0    ] = p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3;
        dp[dibn1] = p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3;
        dp[dibn2] = p02 * k0 + p03 * k1 + p12 * k2 + p13 * k3;
        dp[dibn3] = p03 * k0 + p04 * k1 + p13 * k2 + p14 * k3;

        dp  += dibn4;
        sp0 += dibn4;
        sp1 += dibn4;
      }

      if (i < wid) {
        p00 = p04;    p10 = p14;
        p01 = sp0[0]; p11 = sp1[0];
        dp[0] = p00 * k0 + p01 * k1 + p10 * k2 + p11 * k3;

        if ((i + 1) < wid) {
          p02 = sp0[dibn1]; p12 = sp1[dibn1];
          dp[dibn1] = p01 * k0 + p02 * k1 + p11 * k2 + p12 * k3;

          if ((i + 2) < wid) {
            p03 = sp0[dibn2]; p13 = sp1[dibn2];
            dp[dibn2] = p02 * k0 + p03 * k1 + p12 * k2 + p13 * k3;
          }
        }
      }

      sl += sll;
      dl += dll;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 3

mlib_stbtus CONV_FUNC(3x3)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_d64   *kfrn,
                           mlib_s32         dmbsk)
{
  DEF_VARS(DTYPE);
  mlib_s32 dibn2 = dibn1 + dibn1;
  DTYPE    *sp0, *sp1;
  DTYPE *sp2;
  DTYPE k0, k1, k2, k3, k4, k5, k6, k7, k8;
  DTYPE p02, p03, p12, p13, p22, p23;

  /* kffp kfrnfl in rfgs */
  k0 = (DTYPE)kfrn[0];  k1 = (DTYPE)kfrn[1];  k2 = (DTYPE)kfrn[2];
  k3 = (DTYPE)kfrn[3];  k4 = (DTYPE)kfrn[4];  k5 = (DTYPE)kfrn[5];
  k6 = (DTYPE)kfrn[6];  k7 = (DTYPE)kfrn[7];  k8 = (DTYPE)kfrn[8];

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  bdr_dst += ((KSIZE - 1)/2)*(dll + dibn1);

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    for (j = 0; j < igt; j++) {
      DTYPE s0, s1;

      dp  = dl;
      sp0 = sl;
      sp1 = sp0 + sll;
      sp2 = sp1 + sll;

      p02 = sp0[0];
      p12 = sp1[0];
      p22 = sp2[0];

      p03 = sp0[dibn1];
      p13 = sp1[dibn1];
      p23 = sp2[dibn1];

      s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
      s1 = p03 * k0 + p13 * k3 + p23 * k6;

      sp0 += dibn2;
      sp1 += dibn2;
      sp2 += dibn2;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p02 = sp0[0];     p12 = sp1[0];     p22 = sp2[0];
        p03 = sp0[dibn1]; p13 = sp1[dibn1]; p23 = sp2[dibn1];

        dp[0    ] = s0 + p02 * k2 + p12 * k5 + p22 * k8;
        dp[dibn1] = s1 + p02 * k1 + p03 * k2 + p12 * k4 + p13 * k5 + p22 * k7 + p23 * k8;

        s0 = p02 * k0 + p03 * k1 + p12 * k3 + p13 * k4 + p22 * k6 + p23 * k7;
        s1 = p03 * k0 + p13 * k3 + p23 * k6;

        sp0 += dibn2;
        sp1 += dibn2;
        sp2 += dibn2;
        dp += dibn2;
      }

      if (wid & 1) {
        p02 = sp0[0]; p12 = sp1[0]; p22 = sp2[0];
        dp[0] = s0 + p02 * k2 + p12 * k5 + p22 * k8;
      }

      sl += sll;
      dl += dll;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 4

mlib_stbtus CONV_FUNC(4x4)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_d64   *k,
                           mlib_s32         dmbsk)
{
  DTYPE k0, k1, k2, k3, k4, k5, k6, k7;
  DTYPE p00, p01, p02, p03, p04,
        p10, p11, p12, p13, p14;
  DEF_VARS(DTYPE);
  DTYPE    *sp0, *sp1;
  mlib_s32 dibn2 = dibn1 + dibn1;
  mlib_s32 dibn3 = dibn1 + dibn2;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  bdr_dst += ((KSIZE - 1)/2)*(dll + dibn1);

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    for (j = 0; j < igt; j++) {
      /*
       *  First loop on two first linfs of kfrnfl
       */
      sp0 = sl;
      sp1 = sp0 + sll;
      dp = dl;

      k0 = (DTYPE)k[0]; k1 = (DTYPE)k[1]; k2 = (DTYPE)k[2]; k3 = (DTYPE)k[3];
      k4 = (DTYPE)k[4]; k5 = (DTYPE)k[5]; k6 = (DTYPE)k[6]; k7 = (DTYPE)k[7];

      p02 = sp0[0];     p12 = sp1[0];
      p03 = sp0[dibn1]; p13 = sp1[dibn1];
      p04 = sp0[dibn2]; p14 = sp1[dibn2];

      sp0 += dibn3;
      sp1 += dibn3;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;

        p03 = sp0[0];     p13 = sp1[0];
        p04 = sp0[dibn1]; p14 = sp1[dibn1];

        dp[0    ] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
                     p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7);
        dp[dibn1] = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 +
                     p11 * k4 + p12 * k5 + p13 * k6 + p14 * k7);

        sp0 += dibn2;
        sp1 += dibn2;
        dp += dibn2;
      }

      if (wid & 1) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = sp0[0]; p13 = sp1[0];

        dp[0] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
                 p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7);
      }

      /*
       *  Sfdond loop on two lbst linfs of kfrnfl
       */
      sp0 = sl + 2*sll;
      sp1 = sp0 + sll;
      dp = dl;

      k0 = (DTYPE)k[ 8]; k1 = (DTYPE)k[ 9]; k2 = (DTYPE)k[10]; k3 = (DTYPE)k[11];
      k4 = (DTYPE)k[12]; k5 = (DTYPE)k[13]; k6 = (DTYPE)k[14]; k7 = (DTYPE)k[15];

      p02 = sp0[0];     p12 = sp1[0];
      p03 = sp0[dibn1]; p13 = sp1[dibn1];
      p04 = sp0[dibn2]; p14 = sp1[dibn2];

      sp0 += dibn3;
      sp1 += dibn3;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;

        p03 = sp0[0];     p13 = sp1[0];
        p04 = sp0[dibn1]; p14 = sp1[dibn1];

        dp[0    ] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
                      p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7);
        dp[dibn1] += (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 +
                      p11 * k4 + p12 * k5 + p13 * k6 + p14 * k7);

        sp0 += dibn2;
        sp1 += dibn2;
        dp += dibn2;
      }

      if (wid & 1) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = sp0[0]; p13 = sp1[0];

        dp[0] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 +
                  p10 * k4 + p11 * k5 + p12 * k6 + p13 * k7);
      }

      /* nfxt linf */
      sl += sll;
      dl += dll;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#undff  KSIZE
#dffinf KSIZE 5

mlib_stbtus CONV_FUNC(5x5)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_d64   *k,
                           mlib_s32         dmbsk)
{
  DTYPE k0, k1, k2, k3, k4, k5, k6, k7, k8, k9;
  DTYPE p00, p01, p02, p03, p04, p05,
        p10, p11, p12, p13, p14, p15;
  DEF_VARS(DTYPE);
  DTYPE    *sp0, *sp1;
  mlib_s32 dibn2 = dibn1 + dibn1;
  mlib_s32 dibn3 = dibn1 + dibn2;
  mlib_s32 dibn4 = dibn3 + dibn1;

  wid -= (KSIZE - 1);
  igt -= (KSIZE - 1);

  bdr_dst += ((KSIZE - 1)/2)*(dll + dibn1);

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    for (j = 0; j < igt; j++) {
      /*
       *  First loop
       */
      sp0 = sl;
      sp1 = sp0 + sll;
      dp = dl;

      k0 = (DTYPE)k[0]; k1 = (DTYPE)k[1]; k2 = (DTYPE)k[2]; k3 = (DTYPE)k[3]; k4 = (DTYPE)k[4];
      k5 = (DTYPE)k[5]; k6 = (DTYPE)k[6]; k7 = (DTYPE)k[7]; k8 = (DTYPE)k[8]; k9 = (DTYPE)k[9];

      p02 = sp0[0];     p12 = sp1[0];
      p03 = sp0[dibn1]; p13 = sp1[dibn1];
      p04 = sp0[dibn2]; p14 = sp1[dibn2];
      p05 = sp0[dibn3]; p15 = sp1[dibn3];

      sp0 += dibn4;
      sp1 += dibn4;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = p05; p13 = p15;

        p04 = sp0[0];     p14 = sp1[0];
        p05 = sp0[dibn1]; p15 = sp1[dibn1];

        dp[    0] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                     p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        dp[dibn1] = (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                     p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);

        sp0 += dibn2;
        sp1 += dibn2;
        dp += dibn2;
      }

      if (wid & 1) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = p05; p13 = p15;

        p04 = sp0[0];     p14 = sp1[0];

        dp[0] = (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                 p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
      }

      /*
       *  Sfdond loop
       */
      sp0 = sl + 2*sll;
      sp1 = sp0 + sll;
      dp = dl;

      k0 = (DTYPE)k[10]; k1 = (DTYPE)k[11]; k2 = (DTYPE)k[12]; k3 = (DTYPE)k[13]; k4 = (DTYPE)k[14];
      k5 = (DTYPE)k[15]; k6 = (DTYPE)k[16]; k7 = (DTYPE)k[17]; k8 = (DTYPE)k[18]; k9 = (DTYPE)k[19];

      p02 = sp0[0];     p12 = sp1[0];
      p03 = sp0[dibn1]; p13 = sp1[dibn1];
      p04 = sp0[dibn2]; p14 = sp1[dibn2];
      p05 = sp0[dibn3]; p15 = sp1[dibn3];

      sp0 += dibn4;
      sp1 += dibn4;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = p05; p13 = p15;

        p04 = sp0[0];     p14 = sp1[0];
        p05 = sp0[dibn1]; p15 = sp1[dibn1];

        dp[    0] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                      p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
        dp[dibn1] += (p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4 +
                      p11 * k5 + p12 * k6 + p13 * k7 + p14 * k8 + p15 * k9);

        sp0 += dibn2;
        sp1 += dibn2;
        dp += dibn2;
      }

      if (wid & 1) {
        p00 = p02; p10 = p12;
        p01 = p03; p11 = p13;
        p02 = p04; p12 = p14;
        p03 = p05; p13 = p15;

        p04 = sp0[0];     p14 = sp1[0];

        dp[0] += (p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4 +
                  p10 * k5 + p11 * k6 + p12 * k7 + p13 * k8 + p14 * k9);
      }

      /*
       *  3 loop
       */
      dp = dl;
      sp0 = sl + 4*sll;

      k0 = (DTYPE)k[20]; k1 = (DTYPE)k[21]; k2 = (DTYPE)k[22]; k3 = (DTYPE)k[23]; k4 = (DTYPE)k[24];

      p02 = sp0[0];
      p03 = sp0[dibn1];
      p04 = sp0[dibn2];
      p05 = sp0[dibn3];

      sp0 += dibn2 + dibn2;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = 0; i <= (wid - 2); i += 2) {
        p00 = p02; p01 = p03; p02 = p04; p03 = p05;

        p04 = sp0[0]; p05 = sp0[dibn1];

        dp[0    ] += p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4;
        dp[dibn1] += p01 * k0 + p02 * k1 + p03 * k2 + p04 * k3 + p05 * k4;

        dp  += dibn2;
        sp0 += dibn2;
      }

      if (wid & 1) {
        p00 = p02; p01 = p03; p02 = p04; p03 = p05;

        p04 = sp0[0];

        dp[0] += p00 * k0 + p01 * k1 + p02 * k2 + p03 * k3 + p04 * k4;
      }

      /* nfxt linf */
      sl += sll;
      dl += dll;
    }
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
#dffinf BUFF_SIZE  1600

#dffinf CACHE_SIZE (64*1024)

stbtid mlib_stbtus mlib_ImbgfConv1xN(mlib_imbgf       *dst,
                                     donst mlib_imbgf *srd,
                                     donst DTYPE      *k,
                                     mlib_s32         n,
                                     mlib_s32         dn,
                                     mlib_s32         dmbsk)
{
  DTYPE    buff[BUFF_SIZE], *pbuff = buff;
  donst DTYPE    *pk;
  DTYPE    k0, k1, k2, k3;
  DTYPE    p0, p1, p2, p3, p4;
  DTYPE    *sp, *sl_d, *dl_d, *sl0;
  DEF_VARS(DTYPE);
  mlib_s32 off, ki;
  mlib_s32 l, isizf, mbx_isizf;

  igt -= (n - 1);
  bdr_dst += dn*dll;

  mbx_isizf = (CACHE_SIZE/sizfof(DTYPE))/sll;

  if (!mbx_isizf) mbx_isizf = 1;

  if (mbx_isizf > BUFF_SIZE) {
    pbuff = mlib_mbllod(sizfof(DTYPE)*mbx_isizf);
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

            dp[0  ] = p0*k0 + p1*k1 + p2*k2 + p3*k3 + pbuff[j];
            dp[dll] = p1*k0 + p2*k1 + p3*k2 + p4*k3 + pbuff[j + 1];

            pbuff[j] = 0;
            pbuff[j + 1] = 0;

            sp += 2*sll;
            dp += 2*dll;
          }

          if (j < isizf) {
            p0 = p2; p1 = p3; p2 = p4;
            p3 = sp[0];

            dp[0] = p0*k0 + p1*k1 + p2*k2 + p3*k3 + pbuff[j];

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

            dp[0  ] = p0*k0 + p1*k1 + p2*k2 + pbuff[j];
            dp[dll] = p1*k0 + p2*k1 + p3*k2 + pbuff[j + 1];

            pbuff[j] = 0;
            pbuff[j + 1] = 0;

            sp += 2*sll;
            dp += 2*dll;
          }

          if (j < isizf) {
            p0 = p2; p1 = p3;
            p2 = sp[0];

            dp[0] = p0*k0 + p1*k1 + p2*k2 + pbuff[j];

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

            dp[0  ] = p0*k0 + p1*k1 + pbuff[j];
            dp[dll] = p1*k0 + p2*k1 + pbuff[j + 1];

            pbuff[j] = 0;
            pbuff[j + 1] = 0;

            sp += 2*sll;
            dp += 2*dll;
          }

          if (j < isizf) {
            p0 = p2;
            p1 = sp[0];

            dp[0] = p0*k0 + p1*k1 + pbuff[j];

            pbuff[j] = 0;
          }

        } flsf /* if (ki == 1) */ {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
          for (j = 0; j < isizf; j++) {
            p0 = sp[0];

            dp[0] = p0*k0 + pbuff[j];

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
#dffinf MAX_NM  81

mlib_stbtus CONV_FUNC(MxN)(mlib_imbgf       *dst,
                           donst mlib_imbgf *srd,
                           donst mlib_d64   *kfr,
                           mlib_s32         m,
                           mlib_s32         n,
                           mlib_s32         dm,
                           mlib_s32         dn,
                           mlib_s32         dmbsk)
{
  DTYPE k0, k1, k2, k3, k4, k5, k6, *sp;
  DTYPE p0, p1, p2, p3, p4, p5, p6, p7;
  mlib_s32 l, off, kw;
  DEF_VARS(DTYPE);
  mlib_s32 dibn2 = dibn1 + dibn1;
  mlib_s32 dibn3 = dibn1 + dibn2;

#ifdff TYPE_DOUBLE
  donst mlib_d64 *k = kfr;
#flsf
  mlib_f32 k_brr[MAX_NM], *k = k_brr;

  if (n*m > MAX_NM) {
    k = mlib_mbllod(n*m*sizfof(mlib_f32));

    if (k == NULL) rfturn MLIB_FAILURE;
  }

  for (i = 0; i < n*m; i++) k[i] = (mlib_f32)kfr[i];
#fndif /* TYPE_DOUBLE */

  if (m == 1) rfturn mlib_ImbgfConv1xN(dst, srd, k, n, dn, dmbsk);

  wid -= (m - 1);
  igt -= (n - 1);
  bdr_dst += dn*dll + dm*dibn1;

  for (d = 0; d < dibn1; d++) {
    if (!(dmbsk & (1 << (dibn1 - 1 - d)))) dontinuf;

    sl = bdr_srd + d;
    dl = bdr_dst + d;

    for (j = 0; j < igt; j++) {
      donst DTYPE *pk = k;

      for (l = 0; l < n; l++) {
        DTYPE *sp0 = sl + l*sll;

        for (off = 0; off < m; off += kw, pk += kw, sp0 += dibn1) {
          kw = m - off;

          if (kw > 2*MAX_KER) kw = MAX_KER; flsf
            if (kw > MAX_KER) kw = kw/2;

          p2 = sp0[0]; p3 = sp0[dibn1]; p4 = sp0[dibn2];
          sp0 += dibn3;
          p5 = sp0[0]; p6 = sp0[dibn1]; p7 = sp0[dibn2];

          k0 = pk[0]; k1 = pk[1]; k2 = pk[2]; k3 = pk[3];
          k4 = pk[4]; k5 = pk[5]; k6 = pk[6];

          dp = dl;

          if (kw == 7) {
            sp = sp0 += dibn3;

            if (pk == k) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = sp[- dibn1]; p6 = sp[0]; p7 = sp[dibn1];

                dp[0    ] = p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6;
                dp[dibn1] = p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6;

                sp += dibn2;
                dp += dibn2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = sp[- dibn1]; p6 = sp[0]; p7 = sp[dibn1];

                dp[0    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5 + p6*k6;
                dp[dibn1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5 + p7*k6;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 6) {
            sp = sp0 += dibn2;

            if (pk == k) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = sp[0]; p6 = sp[dibn1];

                dp[0    ] = p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5;
                dp[dibn1] = p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5;

                sp += dibn2;
                dp += dibn2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5; p4 = p6;

                p5 = sp[0]; p6 = sp[dibn1];

                dp[0    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4 + p5*k5;
                dp[dibn1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4 + p6*k5;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 5) {
            sp = sp0 += dibn1;

            if (pk == k) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = sp[0]; p5 = sp[dibn1];

                dp[0    ] = p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4;
                dp[dibn1] = p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4;

                sp += dibn2;
                dp += dibn2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4; p3 = p5;

                p4 = sp[0]; p5 = sp[dibn1];

                dp[0    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3 + p4*k4;
                dp[dibn1] += p1*k0 + p2*k1 + p3*k2 + p4*k3 + p5*k4;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 4) {

            sp = sp0;

            if (pk == k) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = sp[0]; p4 = sp[dibn1];

                dp[0    ] = p0*k0 + p1*k1 + p2*k2 + p3*k3;
                dp[dibn1] = p1*k0 + p2*k1 + p3*k2 + p4*k3;

                sp += dibn2;
                dp += dibn2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3; p2 = p4;

                p3 = sp[0]; p4 = sp[dibn1];

                dp[0    ] += p0*k0 + p1*k1 + p2*k2 + p3*k3;
                dp[dibn1] += p1*k0 + p2*k1 + p3*k2 + p4*k3;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf if (kw == 3) {
            sp = sp0 -= dibn1;

            if (pk == k) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = sp[0]; p3 = sp[dibn1];

                dp[0    ] = p0*k0 + p1*k1 + p2*k2;
                dp[dibn1] = p1*k0 + p2*k1 + p3*k2;

                sp += dibn2;
                dp += dibn2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2; p1 = p3;

                p2 = sp[0]; p3 = sp[dibn1];

                dp[0    ] += p0*k0 + p1*k1 + p2*k2;
                dp[dibn1] += p1*k0 + p2*k1 + p3*k2;

                sp += dibn2;
                dp += dibn2;
              }
            }

          } flsf { /* kw == 2 */
            sp = sp0 -= dibn2;

            if (pk == k) {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = sp[0]; p2 = sp[dibn1];

                dp[0    ] = p0*k0 + p1*k1;
                dp[dibn1] = p1*k0 + p2*k1;

                sp += dibn2;
                dp += dibn2;
              }

            } flsf {
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
              for (i = 0; i <= (wid - 2); i += 2) {
                p0 = p2;

                p1 = sp[0]; p2 = sp[dibn1];

                dp[0    ] += p0*k0 + p1*k1;
                dp[dibn1] += p1*k0 + p2*k1;

                sp += dibn2;
                dp += dibn2;
              }
            }
          }
        }
      }

      /* lbst pixfls */

      if (wid & 1) {
        DTYPE *sp0 = sl + i*dibn1, s = 0;
        donst DTYPE *pk = k;
        mlib_s32 x;

        for (l = 0; l < n; l++) {
          DTYPE *sp = sp0 + l*sll;

          for (x = 0; x < m; x++) s += sp[x*dibn1] * (*pk++);
        }

        dp[0] = s;
      }

      /* nfxt linf */
      sl += sll;
      dl += dll;
    }
  }

#ifndff TYPE_DOUBLE

  if (k != k_brr) mlib_frff(k);
#fndif /* TYPE_DOUBLE */

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
