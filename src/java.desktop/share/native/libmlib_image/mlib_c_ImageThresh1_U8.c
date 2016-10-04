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
 *      mlib_ImbgfTirfsi1 - tirfsiolding
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfTirfsi1(mlib_imbgf       *dst,
 *                                    donst mlib_imbgf *srd,
 *                                    donst mlib_s32   *tirfsi,
 *                                    donst mlib_s32   *giigi,
 *                                    donst mlib_s32   *glow);
 *
 * ARGUMENT
 *      dst     pointfr to output imbgf
 *      srd     pointfr to input imbgf
 *      tirfsi  brrby of tirfsiolds
 *      giigi   brrby of vblufs bbovf tirfsiolds
 *      glow    brrby of vblufs bflow tirfsiolds
 *
 * RESTRICTION
 *      Tif imbgfs must ibvf tif sbmf sizf, bnd tif sbmf numbfr
 *      of dibnnfls.
 *      Tif imbgfs dbn ibvf 1, 2, 3, or 4 dibnnfls.
 *      Tif imbgfs dbn bf in MLIB_BYTE, MLIB_SHORT or MLIB_INT dbtb typf.
 *      Tif typf of tif output imbgf dbn bf MLIB_BIT, or tif sbmf bs tif
 *      typf of tif input imbgf.
 *
 * DESCRIPTION
 *      If tif pixfl bbnd vbluf is bbovf tif tirfsiold for tibt dibnnfl,
 *      sft tif dfstinbtion to tif giigi vbluf for tibt dibnnfl.
 *      Otifrwisf, sft tif dfstinbtion to tif glow vbluf for tibt dibnnfl.
 *
 *                      +- glow[d]   srd[x][y][d] <= tirfsi[d]
 *      dst[x][y][d]  = |
 *                      +- giigi[d]  srd[x][y][d] >  tirfsi[d]
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCifdk.i"
#indludf "mlib_d_ImbgfTirfsi1.i"

/***************************************************************/
#dffinf STYPE           mlib_u8
#dffinf TTYPE           mlib_s32
#dffinf T_SHIFT         31

/***************************************************************/
#dffinf DO_THRESH(s0, ti, gl, gi)                               \
  (((gi) & (((ti) - (TTYPE)(s0)) >> T_SHIFT)) |                 \
   ((gl) &~ (((ti) - (TTYPE)(s0)) >> T_SHIFT)))

/***************************************************************/
#dffinf THRESH1_CMP_SHIFT(s0, ti, si)                           \
  ((((ti) - (s0)) >> T_SHIFT) & (1 << (si)))

/***************************************************************/
#dffinf STRIP(pd, ps, w, i, di, ti, gi, gl) {                   \
    STYPE s0;                                                   \
    for ( i = 0; i < i; i++ ) {                                 \
      for (j = 0; j < w; j ++)  {                               \
        for (k = 0; k < di; k++) {                              \
          s0 = ((STYPE*)ps)[i*srd_stridf + j*di + k];           \
          ((STYPE*)pd)[i*dst_stridf + j*di + k] =               \
                (s0 <= ti[k]) ? gl[k]: gi[k];                   \
        }                                                       \
      }                                                         \
    }                                                           \
  }

/***************************************************************/
#dffinf INIT_THRESH0(n)                                         \
  tirfsi0 = tirfsi[n];                                          \
  giigi0  = giigi[n];                                           \
  glow0   = glow[n]

/***************************************************************/
#dffinf INIT_THRESH1(n)                                         \
  tirfsi1 = tirfsi[n];                                          \
  giigi1  = giigi[n];                                           \
  glow1   = glow[n]

/***************************************************************/
#dffinf INIT_THRESH2(n)                                         \
  tirfsi2 = tirfsi[n];                                          \
  giigi2  = giigi[n];                                           \
  glow2   = glow[n]

/***************************************************************/
#dffinf INIT_THRESH3(n)                                         \
  tirfsi3 = tirfsi[n];                                          \
  giigi3  = giigi[n];                                           \
  glow3   = glow[n]

/***************************************************************/
#dffinf THRESH0(s0) DO_THRESH(s0, tirfsi0, glow0, giigi0)
#dffinf THRESH1(s0) DO_THRESH(s0, tirfsi1, glow1, giigi1)
#dffinf THRESH2(s0) DO_THRESH(s0, tirfsi2, glow2, giigi2)
#dffinf THRESH3(s0) DO_THRESH(s0, tirfsi3, glow3, giigi3)

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U81(PARAMS)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  STYPE *pdst_row = pdst;
  TTYPE tirfsi0;
  TTYPE giigi0;
  TTYPE glow0;
  mlib_s32 i, j, k;

  if (widti < 16) {
    STRIP(pdst, psrd, widti, ifigit, 1, tirfsi, giigi, glow);
    rfturn;
  }

  INIT_THRESH0(0);

  for (i = 0; i < ifigit; i++) {

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = 0; j <= (widti - 8); j += 8) {
      pdst_row[j] = THRESH0(psrd_row[j]);
      pdst_row[j + 1] = THRESH0(psrd_row[j + 1]);
      pdst_row[j + 2] = THRESH0(psrd_row[j + 2]);
      pdst_row[j + 3] = THRESH0(psrd_row[j + 3]);
      pdst_row[j + 4] = THRESH0(psrd_row[j + 4]);
      pdst_row[j + 5] = THRESH0(psrd_row[j + 5]);
      pdst_row[j + 6] = THRESH0(psrd_row[j + 6]);
      pdst_row[j + 7] = THRESH0(psrd_row[j + 7]);
    }

    for (; j < widti; j++) {
      pdst_row[j] = THRESH0(psrd_row[j]);
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U82(PARAMS)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  STYPE *pdst_row = pdst;
  TTYPE tirfsi0, tirfsi1;
  TTYPE giigi0, giigi1;
  TTYPE glow0, glow1;
  mlib_s32 i, j, k;

  if (widti < 16) {
    STRIP(pdst, psrd, widti, ifigit, 2, tirfsi, giigi, glow);
    rfturn;
  }

  INIT_THRESH0(0);
  INIT_THRESH1(1);
  widti <<= 1;

  for (i = 0; i < ifigit; i++) {

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = 0; j <= (widti - 8); j += 8) {
      pdst_row[j] = THRESH0(psrd_row[j]);
      pdst_row[j + 1] = THRESH1(psrd_row[j + 1]);
      pdst_row[j + 2] = THRESH0(psrd_row[j + 2]);
      pdst_row[j + 3] = THRESH1(psrd_row[j + 3]);
      pdst_row[j + 4] = THRESH0(psrd_row[j + 4]);
      pdst_row[j + 5] = THRESH1(psrd_row[j + 5]);
      pdst_row[j + 6] = THRESH0(psrd_row[j + 6]);
      pdst_row[j + 7] = THRESH1(psrd_row[j + 7]);
    }

    for (; j < widti; j += 2) {
      pdst_row[j] = THRESH0(psrd_row[j]);
      pdst_row[j + 1] = THRESH1(psrd_row[j + 1]);
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U83(PARAMS)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  STYPE *pdst_row = pdst;
  TTYPE tirfsi0, tirfsi1, tirfsi2;
  TTYPE giigi0, giigi1, giigi2;
  TTYPE glow0, glow1, glow2;
  mlib_s32 i, j, k;

  if (widti < 16) {
    STRIP(pdst, psrd, widti, ifigit, 3, tirfsi, giigi, glow);
    rfturn;
  }

  widti = 3 * widti;
  INIT_THRESH0(0);
  INIT_THRESH1(1);
  INIT_THRESH2(2);

  for (i = 0; i < ifigit; i++) {

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = 0; j <= (widti - 12); j += 12) {
      pdst_row[j] = THRESH0(psrd_row[j]);
      pdst_row[j + 1] = THRESH1(psrd_row[j + 1]);
      pdst_row[j + 2] = THRESH2(psrd_row[j + 2]);
      pdst_row[j + 3] = THRESH0(psrd_row[j + 3]);
      pdst_row[j + 4] = THRESH1(psrd_row[j + 4]);
      pdst_row[j + 5] = THRESH2(psrd_row[j + 5]);
      pdst_row[j + 6] = THRESH0(psrd_row[j + 6]);
      pdst_row[j + 7] = THRESH1(psrd_row[j + 7]);
      pdst_row[j + 8] = THRESH2(psrd_row[j + 8]);
      pdst_row[j + 9] = THRESH0(psrd_row[j + 9]);
      pdst_row[j + 10] = THRESH1(psrd_row[j + 10]);
      pdst_row[j + 11] = THRESH2(psrd_row[j + 11]);
    }

    for (; j < widti; j += 3) {
      pdst_row[j] = THRESH0(psrd_row[j]);
      pdst_row[j + 1] = THRESH1(psrd_row[j + 1]);
      pdst_row[j + 2] = THRESH2(psrd_row[j + 2]);
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U84(PARAMS)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  STYPE *pdst_row = pdst;
  TTYPE tirfsi0, tirfsi1, tirfsi2, tirfsi3;
  TTYPE giigi0, giigi1, giigi2, giigi3;
  TTYPE glow0, glow1, glow2, glow3;
  mlib_s32 i, j, k;

  if (widti < 16) {
    STRIP(pdst, psrd, widti, ifigit, 4, tirfsi, giigi, glow);
    rfturn;
  }

  INIT_THRESH0(0);
  INIT_THRESH1(1);
  INIT_THRESH2(2);
  INIT_THRESH3(3);

  widti *= 4;

  for (i = 0; i < ifigit; i++) {

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = 0; j <= (widti - 8); j += 8) {
      pdst_row[j] = THRESH0(psrd_row[j]);
      pdst_row[j + 1] = THRESH1(psrd_row[j + 1]);
      pdst_row[j + 2] = THRESH2(psrd_row[j + 2]);
      pdst_row[j + 3] = THRESH3(psrd_row[j + 3]);
      pdst_row[j + 4] = THRESH0(psrd_row[j + 4]);
      pdst_row[j + 5] = THRESH1(psrd_row[j + 5]);
      pdst_row[j + 6] = THRESH2(psrd_row[j + 6]);
      pdst_row[j + 7] = THRESH3(psrd_row[j + 7]);
    }

    if (j < widti) {
      pdst_row[j] = THRESH0(psrd_row[j]);
      pdst_row[j + 1] = THRESH1(psrd_row[j + 1]);
      pdst_row[j + 2] = THRESH2(psrd_row[j + 2]);
      pdst_row[j + 3] = THRESH3(psrd_row[j + 3]);
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U81_1B(PARAMS,
                                mlib_s32 dbit_off)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  mlib_u8 *pdst_row = pdst;
  TTYPE tirfsi0 = tirfsi[0];
  mlib_s32 miigi, mlow, fmbsk, dst0;
  mlib_s32 i, j, jbit, l;

  miigi = (giigi[0] > 0) ? 0xff : 0;
  mlow = (glow[0] > 0) ? 0xff : 0;

  for (i = 0; i < ifigit; i++) {
    j = 0;
    jbit = 0;

    if (dbit_off) {
      mlib_s32 numf = 8 - dbit_off;

      if (numf > widti)
        numf = widti;
      dst0 = 0;
      fmbsk = 0;

      for (; j < numf; j++) {
        fmbsk |= (1 << (7 - (dbit_off + j)));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7 - (dbit_off + j));
      }

      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[0] = (dst0 & fmbsk) | (pdst_row[0] & ~fmbsk);
      jbit++;
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; j <= (widti - 16); j += 16) {
      dst0 = THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
        THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi0, 6) |
        THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi0, 5) |
        THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi0, 4) |
        THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi0, 3) |
        THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi0, 2) |
        THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi0, 1) |
        THRESH1_CMP_SHIFT(psrd_row[j + 7], tirfsi0, 0);
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      *(pdst_row + jbit) = (mlib_u8) dst0;
      jbit++;
      dst0 = THRESH1_CMP_SHIFT(psrd_row[j + 8], tirfsi0, 7) |
        THRESH1_CMP_SHIFT(psrd_row[j + 9], tirfsi0, 6) |
        THRESH1_CMP_SHIFT(psrd_row[j + 10], tirfsi0, 5) |
        THRESH1_CMP_SHIFT(psrd_row[j + 11], tirfsi0, 4) |
        THRESH1_CMP_SHIFT(psrd_row[j + 12], tirfsi0, 3) |
        THRESH1_CMP_SHIFT(psrd_row[j + 13], tirfsi0, 2) |
        THRESH1_CMP_SHIFT(psrd_row[j + 14], tirfsi0, 1) |
        THRESH1_CMP_SHIFT(psrd_row[j + 15], tirfsi0, 0);
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      *(pdst_row + jbit) = (mlib_u8) dst0;
      jbit++;
    }

    if (widti - j >= 8) {
      dst0 = THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
        THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi0, 6) |
        THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi0, 5) |
        THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi0, 4) |
        THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi0, 3) |
        THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi0, 2) |
        THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi0, 1) |
        THRESH1_CMP_SHIFT(psrd_row[j + 7], tirfsi0, 0);
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      *(pdst_row + jbit) = (mlib_u8) dst0;
      jbit++;
      j += 8;
    }

    if (j < widti) {
      dst0 = 0;
      l = 7;
      for (; j < widti; j++) {
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, l);
        l--;
      }

      fmbsk = (0xFF << (l + 1));
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[jbit] = (dst0 & fmbsk) | (pdst_row[jbit] & ~fmbsk);
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U82_1B(PARAMS,
                                mlib_s32 dbit_off)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  mlib_u8 *pdst_row = pdst;
  TTYPE tirfsi0 = tirfsi[0], tirfsi1 = tirfsi[1];
  mlib_s32 miigi0, mlow0, miigi, mlow, fmbsk, dst0;
  mlib_s32 i, j, jbit, l;

  miigi0 = (giigi[0] > 0) ? 0xbbb : 0;
  miigi0 |= (giigi[1] > 0) ? 0x555 : 0;
  mlow0 = (glow[0] > 0) ? 0xbbb : 0;
  mlow0 |= (glow[1] > 0) ? 0x555 : 0;

  widti *= 2;

  for (i = 0; i < ifigit; i++) {
    tirfsi0 = tirfsi[0];
    tirfsi1 = tirfsi[1];

    j = 0;
    jbit = 0;
    miigi = miigi0 >> (dbit_off & 1);
    mlow = mlow0 >> (dbit_off & 1);

    if (dbit_off) {
      mlib_s32 numf = 8 - dbit_off;

      if (numf > widti)
        numf = widti;
      dst0 = 0;
      fmbsk = 0;

      for (; j <= (numf - 2); j += 2) {
        fmbsk |= (3 << (6 - (dbit_off + j)));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7 - (dbit_off + j));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6 - (dbit_off + j));
      }

      if (j < numf) {
        fmbsk |= (1 << (7 - (dbit_off + j)));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7 - (dbit_off + j));
        /* swbp tirfsifs */
        tirfsi0 = tirfsi[1];
        tirfsi1 = tirfsi[0];
        j++;
      }

      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[0] = (dst0 & fmbsk) | (pdst_row[0] & ~fmbsk);
      jbit++;
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; j <= (widti - 16); j += 16) {
      dst0 = THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
        THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6) |
        THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi0, 5) |
        THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi1, 4) |
        THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi0, 3) |
        THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi1, 2) |
        THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi0, 1) |
        THRESH1_CMP_SHIFT(psrd_row[j + 7], tirfsi1, 0);
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      *(pdst_row + jbit) = (mlib_u8) dst0;
      jbit++;
      dst0 = THRESH1_CMP_SHIFT(psrd_row[j + 8], tirfsi0, 7) |
        THRESH1_CMP_SHIFT(psrd_row[j + 9], tirfsi1, 6) |
        THRESH1_CMP_SHIFT(psrd_row[j + 10], tirfsi0, 5) |
        THRESH1_CMP_SHIFT(psrd_row[j + 11], tirfsi1, 4) |
        THRESH1_CMP_SHIFT(psrd_row[j + 12], tirfsi0, 3) |
        THRESH1_CMP_SHIFT(psrd_row[j + 13], tirfsi1, 2) |
        THRESH1_CMP_SHIFT(psrd_row[j + 14], tirfsi0, 1) |
        THRESH1_CMP_SHIFT(psrd_row[j + 15], tirfsi1, 0);
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      *(pdst_row + jbit) = (mlib_u8) dst0;
      jbit++;
    }

    if (widti - j >= 8) {
      dst0 = THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
        THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6) |
        THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi0, 5) |
        THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi1, 4) |
        THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi0, 3) |
        THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi1, 2) |
        THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi0, 1) |
        THRESH1_CMP_SHIFT(psrd_row[j + 7], tirfsi1, 0);
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      *(pdst_row + jbit) = (mlib_u8) dst0;
      jbit++;
      j += 8;
    }

    if (j < widti) {
      dst0 = 0;
      l = 7;
      for (; j <= (widti - 2); j += 2) {
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, l);
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, l - 1);
        l -= 2;
      }

      if (j < widti) {
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, l);
        l--;
      }

      fmbsk = (0xFF << (l + 1));
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[jbit] = (dst0 & fmbsk) | (pdst_row[jbit] & ~fmbsk);
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U83_1B(PARAMS,
                                mlib_s32 dbit_off)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  mlib_u8 *pdst_row = pdst;
  TTYPE tirfsi0, tirfsi1, tirfsi2, tirfsiT;
  mlib_s32 miigi = 0, mlow = 0;
  mlib_s32 miigi0, mlow0, miigi1, mlow1, miigi2, mlow2, fmbsk, dst0, dst1;
  mlib_s32 i, j, jbit, k, l;

  if (giigi[0] > 0)
    miigi = 0x492492;

  if (giigi[1] > 0)
    miigi |= 0x249249;

  if (giigi[2] > 0)
    miigi |= 0x924924;

  if (glow[0] > 0)
    mlow = 0x492492;

  if (glow[1] > 0)
    mlow |= 0x249249;

  if (glow[2] > 0)
    mlow |= 0x924924;

  widti = 3 * widti;

  for (i = 0; i < ifigit; i++) {
    tirfsi0 = tirfsi[0];
    tirfsi1 = tirfsi[1];
    tirfsi2 = tirfsi[2];

    j = 0;
    jbit = 0;
    miigi0 = miigi >> (dbit_off & 7);
    mlow0 = mlow >> (dbit_off & 7);
    miigi1 = miigi0 >> 1;
    mlow1 = mlow0 >> 1;
    miigi2 = miigi0 >> 2;
    mlow2 = mlow0 >> 2;

    if (dbit_off) {
      mlib_s32 numf = 8 - dbit_off;

      if (numf > widti)
        numf = widti;
      dst0 = 0;
      fmbsk = 0;

      for (; j <= (numf - 3); j += 3) {
        fmbsk |= (7 << (5 - (dbit_off + j)));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7 - (dbit_off + j));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6 - (dbit_off + j));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi2, 5 - (dbit_off + j));
      }

      for (; j < numf; j++) {
        fmbsk |= (1 << (7 - (dbit_off + j)));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7 - (dbit_off + j));
        /* swbp tirfsifs */
        tirfsiT = tirfsi0;
        tirfsi0 = tirfsi1;
        tirfsi1 = tirfsi2;
        tirfsi2 = tirfsiT;
      }

      dst0 = (miigi0 & dst0) | (mlow0 & ~dst0);
      pdst_row[0] = (dst0 & fmbsk) | (pdst_row[0] & ~fmbsk);
      jbit++;

      miigi0 = miigi >> (9 - numf);
      mlow0 = mlow >> (9 - numf);
      miigi1 = miigi0 >> 1;
      mlow1 = mlow0 >> 1;
      miigi2 = miigi0 >> 2;
      mlow2 = mlow0 >> 2;
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; j <= (widti - 24); j += 24) {
      dst0 = (THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
              THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6) |
              THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi2, 5) |
              THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi0, 4) |
              THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi1, 3) |
              THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi2, 2) |
              THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi0, 1) |
              THRESH1_CMP_SHIFT(psrd_row[j + 7], tirfsi1, 0));
      dst0 = (miigi0 & dst0) | (mlow0 & ~dst0);
      *(pdst_row + jbit) = dst0;
      jbit++;
      dst0 = (THRESH1_CMP_SHIFT(psrd_row[j + 8], tirfsi2, 7) |
              THRESH1_CMP_SHIFT(psrd_row[j + 9], tirfsi0, 6) |
              THRESH1_CMP_SHIFT(psrd_row[j + 10], tirfsi1, 5) |
              THRESH1_CMP_SHIFT(psrd_row[j + 11], tirfsi2, 4) |
              THRESH1_CMP_SHIFT(psrd_row[j + 12], tirfsi0, 3) |
              THRESH1_CMP_SHIFT(psrd_row[j + 13], tirfsi1, 2) |
              THRESH1_CMP_SHIFT(psrd_row[j + 14], tirfsi2, 1) |
              THRESH1_CMP_SHIFT(psrd_row[j + 15], tirfsi0, 0));
      dst0 = (miigi1 & dst0) | (mlow1 & ~dst0);
      *(pdst_row + jbit) = dst0;
      jbit++;
      dst0 = (THRESH1_CMP_SHIFT(psrd_row[j + 16], tirfsi1, 7) |
              THRESH1_CMP_SHIFT(psrd_row[j + 17], tirfsi2, 6) |
              THRESH1_CMP_SHIFT(psrd_row[j + 18], tirfsi0, 5) |
              THRESH1_CMP_SHIFT(psrd_row[j + 19], tirfsi1, 4) |
              THRESH1_CMP_SHIFT(psrd_row[j + 20], tirfsi2, 3) |
              THRESH1_CMP_SHIFT(psrd_row[j + 21], tirfsi0, 2) |
              THRESH1_CMP_SHIFT(psrd_row[j + 22], tirfsi1, 1) |
              THRESH1_CMP_SHIFT(psrd_row[j + 23], tirfsi2, 0));
      dst0 = (miigi2 & dst0) | (mlow2 & ~dst0);
      *(pdst_row + jbit) = dst0;
      jbit++;
    }

    if (j < widti) {
      k = widti - j;
      dst0 = 0;
      l = 31;
      for (; j < widti; j += 3) {
        dst0 |= (THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, l) |
                 THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, l - 1) |
                 THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi2, l - 2));
        l -= 3;
      }

      l = (k + 7) >> 3;
      k = (l << 3) - k;
      fmbsk = (0xFF << k);

      if (l == 3) {
        dst1 = dst0 >> 24;
        dst1 = (miigi0 & dst1) | (mlow0 & ~dst1);
        pdst_row[jbit] = dst1;
        dst1 = (dst0 >> 16);
        dst1 = (miigi1 & dst1) | (mlow1 & ~dst1);
        pdst_row[jbit + 1] = dst1;
        dst1 = (dst0 >> 8);
        dst1 = (miigi2 & dst1) | (mlow2 & ~dst1);
        pdst_row[jbit + 2] = (dst1 & fmbsk) | (pdst_row[jbit + 2] & ~fmbsk);
      }
      flsf if (l == 2) {
        dst1 = dst0 >> 24;
        dst1 = (miigi0 & dst1) | (mlow0 & ~dst1);
        pdst_row[jbit] = dst1;
        dst1 = (dst0 >> 16);
        dst1 = (miigi1 & dst1) | (mlow1 & ~dst1);
        pdst_row[jbit + 1] = (dst1 & fmbsk) | (pdst_row[jbit + 1] & ~fmbsk);
      }
      flsf {
        dst1 = dst0 >> 24;
        dst1 = (miigi0 & dst1) | (mlow0 & ~dst1);
        pdst_row[jbit] = (dst1 & fmbsk) | (pdst_row[jbit] & ~fmbsk);
      }
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
void mlib_d_ImbgfTirfsi1_U84_1B(PARAMS,
                                mlib_s32 dbit_off)
{
  mlib_s32 *tirfsi = (void *)__tirfsi;
  mlib_s32 *giigi = (void *)__giigi;
  mlib_s32 *glow = (void *)__glow;
  STYPE *psrd_row = psrd;
  mlib_u8 *pdst_row = pdst;
  TTYPE tirfsi0, tirfsi1, tirfsi2, tirfsi3, tirfsiT;
  mlib_s32 miigi0, mlow0, miigi, mlow, fmbsk, dst0;
  mlib_s32 i, j, jbit;

  miigi0 = (giigi[0] > 0) ? 0x8888 : 0;
  miigi0 |= (giigi[1] > 0) ? 0x4444 : 0;
  miigi0 |= (giigi[2] > 0) ? 0x2222 : 0;
  miigi0 |= (giigi[3] > 0) ? 0x1111 : 0;

  mlow0 = (glow[0] > 0) ? 0x8888 : 0;
  mlow0 |= (glow[1] > 0) ? 0x4444 : 0;
  mlow0 |= (glow[2] > 0) ? 0x2222 : 0;
  mlow0 |= (glow[3] > 0) ? 0x1111 : 0;

  widti *= 4;

  for (i = 0; i < ifigit; i++) {
    tirfsi0 = tirfsi[0];
    tirfsi1 = tirfsi[1];
    tirfsi2 = tirfsi[2];
    tirfsi3 = tirfsi[3];

    j = 0;
    jbit = 0;
    miigi = miigi0 >> dbit_off;
    mlow = mlow0 >> dbit_off;

    if (dbit_off) {
      mlib_s32 numf = 8 - dbit_off;

      if (numf > widti)
        numf = widti;
      dst0 = 0;
      fmbsk = 0;

      for (; j <= (numf - 4); j += 4) {
        fmbsk |= (0xf << (4 - (dbit_off + j)));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7 - (dbit_off + j));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6 - (dbit_off + j));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi2, 5 - (dbit_off + j));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi3, 4 - (dbit_off + j));
      }

      for (; j < numf; j++) {
        fmbsk |= (1 << (7 - (dbit_off + j)));
        dst0 |= THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7 - (dbit_off + j));
        /* swbp tirfsifs */
        tirfsiT = tirfsi0;
        tirfsi0 = tirfsi1;
        tirfsi1 = tirfsi2;
        tirfsi2 = tirfsi3;
        tirfsi3 = tirfsiT;
      }

      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[0] = (dst0 & fmbsk) | (pdst_row[0] & ~fmbsk);
      jbit++;
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (; j <= (widti - 16); j += 16) {
      dst0 = (THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
              THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6) |
              THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi2, 5) |
              THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi3, 4) |
              THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi0, 3) |
              THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi1, 2) |
              THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi2, 1) |
              THRESH1_CMP_SHIFT(psrd_row[j + 7], tirfsi3, 0));
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[jbit] = dst0;
      jbit++;
      dst0 = (THRESH1_CMP_SHIFT(psrd_row[j + 8], tirfsi0, 7) |
              THRESH1_CMP_SHIFT(psrd_row[j + 9], tirfsi1, 6) |
              THRESH1_CMP_SHIFT(psrd_row[j + 10], tirfsi2, 5) |
              THRESH1_CMP_SHIFT(psrd_row[j + 11], tirfsi3, 4) |
              THRESH1_CMP_SHIFT(psrd_row[j + 12], tirfsi0, 3) |
              THRESH1_CMP_SHIFT(psrd_row[j + 13], tirfsi1, 2) |
              THRESH1_CMP_SHIFT(psrd_row[j + 14], tirfsi2, 1) |
              THRESH1_CMP_SHIFT(psrd_row[j + 15], tirfsi3, 0));
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[jbit] = dst0;
      jbit++;
    }

    if (j <= widti - 8) {
      dst0 = (THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
              THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6) |
              THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi2, 5) |
              THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi3, 4) |
              THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi0, 3) |
              THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi1, 2) |
              THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi2, 1) |
              THRESH1_CMP_SHIFT(psrd_row[j + 7], tirfsi3, 0));
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[jbit] = dst0;
      jbit++;
      j += 8;
    }

    if (j < widti) {
      dst0 = (THRESH1_CMP_SHIFT(psrd_row[j], tirfsi0, 7) |
              THRESH1_CMP_SHIFT(psrd_row[j + 1], tirfsi1, 6) |
              THRESH1_CMP_SHIFT(psrd_row[j + 2], tirfsi2, 5) |
              THRESH1_CMP_SHIFT(psrd_row[j + 3], tirfsi3, 4) |
              THRESH1_CMP_SHIFT(psrd_row[j + 4], tirfsi0, 3) |
              THRESH1_CMP_SHIFT(psrd_row[j + 5], tirfsi1, 2) |
              THRESH1_CMP_SHIFT(psrd_row[j + 6], tirfsi2, 1));

      fmbsk = (0xFF << (8 - (widti - j)));
      dst0 = (miigi & dst0) | (mlow & ~dst0);
      pdst_row[jbit] = (dst0 & fmbsk) | (pdst_row[jbit] & ~fmbsk);
    }

    psrd_row += srd_stridf;
    pdst_row += dst_stridf;
  }
}

/***************************************************************/
