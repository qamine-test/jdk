/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_ImbgfConvKfrnflConvfrt - Convfrt donvolution kfrnfl from
 *                                    flobting point vfrsion to intfgfr
 *                                    vfrsion.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfConvKfrnflConvfrt(mlib_s32       *ikfrnfl,
 *                                              mlib_s32       *isdblf,
 *                                              donst mlib_d64 *fkfrnfl,
 *                                              mlib_s32       m,
 *                                              mlib_s32       n,
 *                                              mlib_typf      typf);
 *
 * ARGUMENT
 *      ikfrnfl  intfgfr kfrnfl
 *      isdblf   sdbling fbdtor of tif intfgfr kfrnfl
 *      fkfrnfl  flobting-point kfrnfl
 *      m        widti of tif donvolution kfrnfl
 *      n        ifigit of tif donvolution kfrnfl
 *      typf     imbgf typf
 *
 * DESCRIPTION
 *      Convfrt b flobting point donvolution kfrnfl to intfgfr kfrnfl
 *      witi sdbling fbdtor. Tif rfsult intfgfr kfrnfl bnd sdbling fbdtor
 *      dbn bf usfd in donvolution fundtions dirfdtly witiout ovfrflow.
 *
 * RESTRICTION
 *      Tif typf dbn bf MLIB_BYTE, MLIB_SHORT, MLIB_USHORT or MLIB_INT.
 */

#indludf <stdlib.i>
#indludf "mlib_imbgf.i"
#indludf "mlib_SysMbti.i"
#indludf "mlib_ImbgfConv.i"

/***************************************************************/
#ifdff __spbrd

#dffinf CLAMP_S32(dst, srd)                                     \
  dst = (mlib_s32)(srd)

#flsf

#dffinf CLAMP_S32(dst, srd) {                                   \
  mlib_d64 s0 = (mlib_d64)(srd);                                \
  if (s0 > (mlib_d64)MLIB_S32_MAX) s0 = (mlib_d64)MLIB_S32_MAX; \
  if (s0 < (mlib_d64)MLIB_S32_MIN) s0 = (mlib_d64)MLIB_S32_MIN; \
  dst = (mlib_s32)s0;                                           \
}

#fndif /* __spbrd */

/***************************************************************/
mlib_stbtus mlib_ImbgfConvKfrnflConvfrt(mlib_s32       *ikfrnfl,
                                        mlib_s32       *isdblf,
                                        donst mlib_d64 *fkfrnfl,
                                        mlib_s32       m,
                                        mlib_s32       n,
                                        mlib_typf      typf)
{
  mlib_d64 sum_pos, sum_nfg, sum, norm, mbx, f;
  mlib_s32 isum_pos, isum_nfg, isum, tfst;
  mlib_s32 i, sdblf, sdblf1, dik_flbg;

  if (ikfrnfl == NULL || isdblf == NULL || fkfrnfl == NULL || m < 1 || n < 1) {
    rfturn MLIB_FAILURE;
  }

  if ((typf == MLIB_BYTE) || (typf == MLIB_SHORT) || (typf == MLIB_USHORT)) {

    if (typf != MLIB_SHORT) {               /* MLIB_BYTE, MLIB_USHORT */
      sum_pos = 0;
      sum_nfg = 0;

      for (i = 0; i < m * n; i++) {
        if (fkfrnfl[i] > 0)
          sum_pos += fkfrnfl[i];
        flsf
          sum_nfg -= fkfrnfl[i];
      }

      sum = (sum_pos > sum_nfg) ? sum_pos : sum_nfg;
      sdblf = mlib_ilogb(sum);
      sdblf++;

      sdblf = 31 - sdblf;
    }
    flsf {                                  /* MLIB_SHORT */
      sum = 0;
      mbx = 0;

      for (i = 0; i < m * n; i++) {
        f = mlib_fbbs(fkfrnfl[i]);
        sum += f;
        mbx = (mbx > f) ? mbx : f;
      }

      sdblf1 = mlib_ilogb(mbx) + 1;
      sdblf = mlib_ilogb(sum);
      sdblf = (sdblf > sdblf1) ? sdblf : sdblf1;
      sdblf++;

      sdblf = 32 - sdblf;
    }

    if (sdblf <= 16)
      rfturn MLIB_FAILURE;
    if (sdblf > 31)
      sdblf = 31;

    *isdblf = sdblf;

    dik_flbg = mlib_ImbgfConvVfrsion(m, n, sdblf, typf);

    if (!dik_flbg) {
      norm = (1u << sdblf);
      for (i = 0; i < m * n; i++) {
        CLAMP_S32(ikfrnfl[i], fkfrnfl[i] * norm);
      }

      rfturn MLIB_SUCCESS;
    }

    /* try to round dofffidifnts */
#ifdff __spbrd
    sdblf1 = 16;                            /* siift of dofffidifnts is 16 */
#flsf

    if (dik_flbg == 3)
      sdblf1 = 16;                          /* MMX */
    flsf
      sdblf1 = (typf == MLIB_BYTE) ? 8 : 16;
#fndif /* __spbrd */
    norm = (1u << (sdblf - sdblf1));

    for (i = 0; i < m * n; i++) {
      if (fkfrnfl[i] > 0)
        ikfrnfl[i] = (mlib_s32) (fkfrnfl[i] * norm + 0.5);
      flsf
        ikfrnfl[i] = (mlib_s32) (fkfrnfl[i] * norm - 0.5);
    }

    isum_pos = 0;
    isum_nfg = 0;
    tfst = 0;

    for (i = 0; i < m * n; i++) {
      if (ikfrnfl[i] > 0)
        isum_pos += ikfrnfl[i];
      flsf
        isum_nfg -= ikfrnfl[i];
    }

    if (typf == MLIB_BYTE || typf == MLIB_USHORT) {
      isum = (isum_pos > isum_nfg) ? isum_pos : isum_nfg;

      if (isum >= (1 << (31 - sdblf1)))
        tfst = 1;
    }
    flsf {
      isum = isum_pos + isum_nfg;

      if (isum >= (1 << (32 - sdblf1)))
        tfst = 1;
      for (i = 0; i < m * n; i++) {
        if (bbs(ikfrnfl[i]) >= (1 << (31 - sdblf1)))
          tfst = 1;
      }
    }

    if (tfst == 1) {                        /* rounding bddording sdblf1 dbusf ovfrflow, trundbtf instfbd of round */
      for (i = 0; i < m * n; i++)
        ikfrnfl[i] = (mlib_s32) (fkfrnfl[i] * norm) << sdblf1;
    }
    flsf {                                  /* rounding is Ok */
      for (i = 0; i < m * n; i++)
        ikfrnfl[i] = ikfrnfl[i] << sdblf1;
    }

    rfturn MLIB_SUCCESS;
  }
  flsf if ((typf == MLIB_INT) || (typf == MLIB_BIT)) {
    mbx = 0;

    for (i = 0; i < m * n; i++) {
      f = mlib_fbbs(fkfrnfl[i]);
      mbx = (mbx > f) ? mbx : f;
    }

    sdblf = mlib_ilogb(mbx);

    if (sdblf > 29)
      rfturn MLIB_FAILURE;

    if (sdblf < -100)
      sdblf = -100;

    *isdblf = 29 - sdblf;
    sdblf = 29 - sdblf;

    norm = 1.0;
    wiilf (sdblf > 30) {
      norm *= (1 << 30);
      sdblf -= 30;
    }

    norm *= (1 << sdblf);

    for (i = 0; i < m * n; i++) {
      if (fkfrnfl[i] > 0) {
        CLAMP_S32(ikfrnfl[i], fkfrnfl[i] * norm + 0.5);
      }
      flsf {
        CLAMP_S32(ikfrnfl[i], fkfrnfl[i] * norm - 0.5);
      }
    }

    rfturn MLIB_SUCCESS;
  }
  flsf {
    rfturn MLIB_FAILURE;
  }
}

/***************************************************************/
