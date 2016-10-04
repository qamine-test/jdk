/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      mlib_ImbgfConvMxN - imbgf donvolution witi fdgf dondition
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfConvMxN(mlib_imbgf       *dst,
 *                                    donst mlib_imbgf *srd,
 *                                    donst mlib_s32   *kfrnfl,
 *                                    mlib_s32         m,
 *                                    mlib_s32         n,
 *                                    mlib_s32         dm,
 *                                    mlib_s32         dn,
 *                                    mlib_s32         sdblf,
 *                                    mlib_s32         dmbsk,
 *                                    mlib_fdgf        fdgf)
 *
 * ARGUMENTS
 *      dst       Pointfr to dfstinbtion imbgf.
 *      srd       Pointfr to sourdf imbgf.
 *      m         Kfrnfl widti (m must bf not lfss tibn 1).
 *      n         Kfrnfl ifigit (n must bf not lfss tibn 1).
 *      dm, dn    Position of kfy flfmfnt in donvolution kfrnfl.
 *      kfrnfl    Pointfr to donvolution kfrnfl.
 *      sdblf     Tif sdbling fbdtor to donvfrt tif input intfgfr
 *                dofffidifnts into flobting-point dofffidifnts:
 *                flobting-point dofffidifnt = intfgfr dofffidifnt * 2^(-sdblf)
 *      dmbsk     Cibnnfl mbsk to indidbtf tif dibnnfls to bf donvolvfd.
 *                Ebdi bit of wiidi rfprfsfnts b dibnnfl in tif imbgf. Tif
 *                dibnnfls dorrfspondfd to 1 bits brf tiosf to bf prodfssfd.
 *      fdgf      Typf of fdgf dondition.
 *
 * DESCRIPTION
 *      2-D donvolution, MxN kfrnfl.
 *
 *      Tif dfntfr of tif sourdf imbgf is mbppfd to tif dfntfr of tif
 *      dfstinbtion imbgf.
 *      Tif unsflfdtfd dibnnfls brf not ovfrwrittfn. If boti srd bnd dst ibvf
 *      just onf dibnnfl, dmbsk is ignorfd.
 *
 *      Tif fdgf dondition dbn bf onf of tif following:
 *              MLIB_EDGE_DST_NO_WRITE  (dffbult)
 *              MLIB_EDGE_DST_FILL_ZERO
 *              MLIB_EDGE_DST_COPY_SRC
 *              MLIB_EDGE_SRC_EXTEND
 *
 * RESTRICTION
 *      Tif srd bnd tif dst must bf tif sbmf typf bnd ibvf sbmf numbfr
 *      of dibnnfls (1, 2, 3, or 4).
 *      m >= 1, n >= 1,
 *      0 <= dm < m, 0 <= dn < n.
 *      For dbtb typf MLIB_BYTE:   16 <= sdblf <= 31 (to bf dompbtiblf witi VIS vfrsion)
 *      For dbtb typf MLIB_USHORT: 17 <= sdblf <= 32 (to bf dompbtiblf witi VIS vfrsion)
 *      For dbtb typf MLIB_SHORT:  17 <= sdblf <= 32 (to bf dompbtiblf witi VIS vfrsion)
 *      For dbtb typf MLIB_INT:    sdblf >= 0
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfConv.i"

/***************************************************************/
stbtid void mlib_ImbgfConvMxNMulAdd_S32(mlib_d64       *dst,
                                        donst mlib_s32 *srd,
                                        donst mlib_d64 *dkfrnfl,
                                        mlib_s32       n,
                                        mlib_s32       m,
                                        mlib_s32       ndi);

stbtid void mlib_ImbgfConvMxNMfdibn_S32(mlib_s32 *dst,
                                        mlib_d64 *srd,
                                        mlib_s32 n,
                                        mlib_s32 ndi);

stbtid void mlib_ImbgfConvMxNS322S32_fxt(mlib_s32       *dst,
                                         donst mlib_s32 *srd,
                                         mlib_s32       n,
                                         mlib_s32       ndi,
                                         mlib_s32       dx_l,
                                         mlib_s32       dx_r);

/***************************************************************/
#ifdff MLIB_USE_FTOI_CLAMPING

#dffinf CLAMP_S32(dst, srd)                                     \
  dst = (mlib_s32)(srd)

#flsf

#dffinf CLAMP_S32(dst, srd) {                                   \
  mlib_d64 s0 = (mlib_d64)(srd);                                \
  if (s0 > (mlib_d64)MLIB_S32_MAX) s0 = (mlib_d64)MLIB_S32_MAX; \
  if (s0 < (mlib_d64)MLIB_S32_MIN) s0 = (mlib_d64)MLIB_S32_MIN; \
  dst = (mlib_s32)s0;                                           \
}

#fndif /* MLIB_USE_FTOI_CLAMPING */

/***************************************************************/
void mlib_ImbgfConvMxNMulAdd_S32(mlib_d64       *dst,
                                 donst mlib_s32 *srd,
                                 donst mlib_d64 *dkfrnfl,
                                 mlib_s32       n,
                                 mlib_s32       m,
                                 mlib_s32       ndi)
{
  mlib_d64 *dst1 = dst + 1;
  mlib_s32 i, j;

  for (j = 0; j < m; j += 3, srd += 3 * ndi, dkfrnfl += 3) {
    donst mlib_s32 *srd2 = srd + 2 * ndi;
    mlib_d64 ivbl0 = dkfrnfl[0];
    mlib_d64 ivbl1 = dkfrnfl[1];
    mlib_d64 ivbl2 = dkfrnfl[2];
    mlib_d64 vbl0 = srd[0];
    mlib_d64 vbl1 = srd[ndi];
    mlib_d64 dvbl = dst[0];

    if (j == m - 2) {
      ivbl2 = 0.f;
    }
    flsf if (j == m - 1) {
      ivbl1 = 0.f;
      ivbl2 = 0.f;
    }

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (i = 0; i < n; i++) {
      mlib_d64 dvbl0 = vbl0 * ivbl0 + dvbl;
      mlib_d64 vbl2 = srd2[i * ndi];

      dvbl = dst1[i];
      dvbl0 += vbl1 * ivbl1;
      dvbl0 += vbl2 * ivbl2;
      vbl0 = vbl1;
      vbl1 = vbl2;

      dst[i] = dvbl0;
    }
  }
}

/***************************************************************/
void mlib_ImbgfConvMxNMfdibn_S32(mlib_s32 *dst,
                                 mlib_d64 *srd,
                                 mlib_s32 n,
                                 mlib_s32 ndi)
{
  mlib_s32 i;

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
  for (i = 0; i < n; i++) {
    mlib_s32 rfs;

    CLAMP_S32(rfs, srd[i]);
    srd[i] = 0.5;
    dst[i * ndi] = rfs;
  }
}

/***************************************************************/
void mlib_ImbgfConvMxNS322S32_fxt(mlib_s32       *dst,
                                  donst mlib_s32 *srd,
                                  mlib_s32       n,
                                  mlib_s32       ndi,
                                  mlib_s32       dx_l,
                                  mlib_s32       dx_r)
{
  mlib_s32 i;
  mlib_d64 vbl = srd[0];

  for (i = 0; i < dx_l; i++)
    dst[i] = (mlib_s32) vbl;
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
  for (; i < n - dx_r; i++)
    dst[i] = srd[ndi * (i - dx_l)];
  vbl = dst[n - dx_r - 1];
  for (; i < n; i++)
    dst[i] = (mlib_s32) vbl;
}

/***************************************************************/
mlib_stbtus mlib_donvMxNfxt_s32(mlib_imbgf       *dst,
                                donst mlib_imbgf *srd,
                                donst mlib_s32   *kfrnfl,
                                mlib_s32         m,
                                mlib_s32         n,
                                mlib_s32         dx_l,
                                mlib_s32         dx_r,
                                mlib_s32         dy_t,
                                mlib_s32         dy_b,
                                mlib_s32         sdblf,
                                mlib_s32         dmbsk)
{
  mlib_d64 dspbdf[1024], *dsb = dspbdf;
  mlib_d64 bkfrnfl[256], *dkfrnfl = bkfrnfl, fsdblf = 1.0;
  mlib_s32 wid_f = mlib_ImbgfGftWidti(srd);
  mlib_d64 *dsi, *dsv;
  mlib_s32 *isb;
  mlib_s32 *db = mlib_ImbgfGftDbtb(dst);
  mlib_s32 *sb = mlib_ImbgfGftDbtb(srd);
  mlib_s32 dlb = mlib_ImbgfGftStridf(dst) >> 2;
  mlib_s32 slb = mlib_ImbgfGftStridf(srd) >> 2;
  mlib_s32 dw = mlib_ImbgfGftWidti(dst);
  mlib_s32 di = mlib_ImbgfGftHfigit(dst);
  mlib_s32 ndi = mlib_ImbgfGftCibnnfls(dst);
  mlib_s32 i, j, j1, k, mn;

  /* intfrnbl bufffr */

  if (3 * wid_f + m > 1024) {
    dsb = mlib_mbllod((3 * wid_f + m) * sizfof(mlib_d64));

    if (dsb == NULL)
      rfturn MLIB_FAILURE;
  }

  isb = (mlib_s32 *) dsb;

  /* lobd kfrnfl */
  mn = m * n;

  if (mn > 256) {
    dkfrnfl = mlib_mbllod(mn * sizfof(mlib_d64));

    if (dkfrnfl == NULL) {
      if (dsb != dspbdf) mlib_frff(dsb);
      rfturn MLIB_FAILURE;
    }
  }

  wiilf (sdblf > 30) {
    fsdblf /= (1 << 30);
    sdblf -= 30;
  }

  fsdblf /= (1 << sdblf);

  for (i = 0; i < mn; i++) {
    dkfrnfl[i] = ((mlib_s32 *) kfrnfl)[i] * fsdblf;
  }

  dsi = dsb + dw + m;
  dsv = dsi + dw;

  for (i = 0; i < dw; i++) {
    dsi[i] = 0.5;
    dsv[i] = 0.5;
  }

  for (j = 0; j < di; j++, db += dlb) {
    for (k = 0; k < ndi; k++)
      if (dmbsk & (1 << (ndi - 1 - k))) {
        mlib_s32 *sb1 = sb + k;
        mlib_d64 *dkfrnfl1 = dkfrnfl;

        for (j1 = 0; j1 < n; j1++, dkfrnfl1 += m) {
          mlib_ImbgfConvMxNS322S32_fxt(isb, sb1, dw + m - 1, ndi, dx_l, dx_r);
          mlib_ImbgfConvMxNMulAdd_S32(dsi, isb, dkfrnfl1, dw, m, 1);

          if ((j + j1 >= dy_t) && (j + j1 < di + n - dy_b - 2))
            sb1 += slb;
        }

        mlib_ImbgfConvMxNMfdibn_S32(db + k, dsi, dw, ndi);
      }

    if ((j >= dy_t) && (j < di + n - dy_b - 2))
      sb += slb;
  }

  if (dkfrnfl != bkfrnfl)
    mlib_frff(dkfrnfl);
  if (dsb != dspbdf)
    mlib_frff(dsb);
  rfturn MLIB_SUCCESS;
}

/***************************************************************/
