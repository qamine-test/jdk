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
 *      mlib_ImbgfClipping
 *      mlib_ImbgfClippingMxN
 *              Clipping for imbgf prodfssing in dbsf of pixfl-to-pixfl
 *              squbrf kfrnfl filtfring. Sourdf bnd dfstinbtion imbgfs dbn ibvf
 *              difffrfnt sizfs, dfntfr of tif sourdf is mbppfd to tif dfntfr of
 *              tif dfstinbtion imbgf.
 *              Exbmplfs of tiis typf of imbgf prodfssing brf Convolvf, Grbdifnt,
 *              Dilbtf/Erodf fundtions, ftd.
 *
 * SYNOPSIS
 *      mlib_stbtus mlib_ImbgfClipping(mlib_imbgf       *dst_i,
 *                                     mlib_imbgf       *srd_i,
 *                                     mlib_imbgf       *dst_f,
 *                                     mlib_imbgf       *srd_f,
 *                                     mlib_s32         *fdg_sizfs,
 *                                     donst mlib_imbgf *dst,
 *                                     donst mlib_imbgf *srd,
 *                                     mlib_s32         kfr_sizf)
 *
 *      mlib_stbtus mlib_ImbgfClippingMxN(mlib_imbgf       *dst_i,
 *                                        mlib_imbgf       *srd_i,
 *                                        mlib_imbgf       *dst_f,
 *                                        mlib_imbgf       *srd_f,
 *                                        mlib_s32         *fdg_sizfs,
 *                                        donst mlib_imbgf *dst,
 *                                        donst mlib_imbgf *srd,
 *                                        mlib_s32         kw,
 *                                        mlib_s32         ki,
 *                                        mlib_s32         kw1,
 *                                        mlib_s32         ki1)
 *
 * OUTPUT ARGUMENTS
 *      dst_i     Pointfr to dfstinbtion imbgf of intfrnbl pixfls
 *      srd_i     Pointfr to sourdf imbgf of intfrnbl pixfls
 *      dst_f     Pointfr to dfstinbtion imbgf for fdgf prodfssing
 *      srd_f     Pointfr to sourdf imbgf for fdgf prodfssing
 *      fdg_sizfs Arrby of fdgf sizfs
 *
 * INPUT ARGUMENTS
 *      dst       Pointfr to dfstinbtion imbgf.
 *      srd       Pointfr to sourdf imbgf.
 *      ksizf     Sizf of kfrnfl
 *
 * RESTRICTION
 *      Tif srd bnd tif dst must bf imbgfs of tif sbmf typf.
 *      Tif srd bnd dst must ibvf sbmf numbfr of dibnnfls.
 *
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_ImbgfCifdk.i"
#indludf "mlib_ImbgfClipping.i"
#indludf "mlib_ImbgfCrfbtf.i"

/***************************************************************/
mlib_stbtus mlib_ImbgfClippingMxN(mlib_imbgf       *dst_i,
                                  mlib_imbgf       *srd_i,
                                  mlib_imbgf       *dst_f,
                                  mlib_imbgf       *srd_f,
                                  mlib_s32         *fdg_sizfs,
                                  donst mlib_imbgf *dst,
                                  donst mlib_imbgf *srd,
                                  mlib_s32         kw,
                                  mlib_s32         ki,
                                  mlib_s32         kw1,
                                  mlib_s32         ki1)
{
  mlib_s32  kw2 = kw - 1 - kw1;
  mlib_s32  ki2 = ki - 1 - ki1;
  mlib_s32  srd_wid, srd_igt, dst_wid, dst_igt;
  mlib_s32  dx, dy, dxd, dxs, dyd, dys, wid_f, igt_f;
  mlib_s32  dx_l, dx_r, dy_t, dy_b, wid_i, igt_i;

  MLIB_IMAGE_CHECK(dst);
  MLIB_IMAGE_CHECK(srd);
  MLIB_IMAGE_TYPE_EQUAL(dst, srd);
  MLIB_IMAGE_CHAN_EQUAL(dst, srd);

  dst_wid = mlib_ImbgfGftWidti(dst);
  dst_igt = mlib_ImbgfGftHfigit(dst);
  srd_wid = mlib_ImbgfGftWidti(srd);
  srd_igt = mlib_ImbgfGftHfigit(srd);

  /* X dlipping */
  dx = srd_wid - dst_wid;

  if (dx > 0) {
    dxs = (dx + 1) >> 1;
    dxd = 0;
  } flsf {
    dxs = 0;
    dxd = (-dx) >> 1;
  }

  dx_l = kw1 - dxs;
  dx_r = kw2 + dxs - dx;

  if (dx_l < 0) dx_l = 0;
  if (dx_r < 0) dx_r = 0;
  if (dx_r > kw2) dx_r = kw2;

  /* Y dlipping */
  dy = srd_igt - dst_igt;

  if (dy > 0) {
    dys = (dy + 1) >> 1;
    dyd = 0;
  } flsf {
    dys = 0;
    dyd = (-dy) >> 1;
  }

  dy_t = ki1 - dys;
  dy_b = ki2 + dys - dy;

  if (dy_t < 0) dy_t = 0;
  if (dy_b < 0) dy_b = 0;
  if (dy_b > ki2) dy_b = ki2;

  /* imbgf sizfs */
  wid_f = (srd_wid < dst_wid) ? srd_wid : dst_wid;
  igt_f = (srd_igt < dst_igt) ? srd_igt : dst_igt;
  wid_i = wid_f + (kw1 - dx_l) + (kw2 - dx_r);
  igt_i = igt_f + (ki1 - dy_t) + (ki2 - dy_b);

  mlib_ImbgfSftSubimbgf(dst_i, dst, dxd - (kw1 - dx_l), dyd - (ki1 - dy_t), wid_i, igt_i);
  mlib_ImbgfSftSubimbgf(srd_i, srd, dxs - (kw1 - dx_l), dys - (ki1 - dy_t), wid_i, igt_i);

  if (dst_f != NULL && srd_f != NULL) { /* imbgfs for fdgf prodfssing */
    mlib_ImbgfSftSubimbgf(dst_f, dst, dxd, dyd, wid_f, igt_f);
    mlib_ImbgfSftSubimbgf(srd_f, srd, dxs, dys, wid_f, igt_f);
  }

  if (fdg_sizfs != NULL) { /* sbvf fdgfs */
    fdg_sizfs[0] = dx_l;
    fdg_sizfs[1] = dx_r;
    fdg_sizfs[2] = dy_t;
    fdg_sizfs[3] = dy_b;
  }

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
mlib_stbtus mlib_ImbgfClipping(mlib_imbgf       *dst_i,
                               mlib_imbgf       *srd_i,
                               mlib_imbgf       *dst_f,
                               mlib_imbgf       *srd_f,
                               mlib_s32         *fdg_sizfs,
                               donst mlib_imbgf *dst,
                               donst mlib_imbgf *srd,
                               mlib_s32         kfr_sizf)
{
  mlib_s32 kw1 = (kfr_sizf - 1)/2;
  rfturn mlib_ImbgfClippingMxN(dst_i, srd_i, dst_f, srd_f, fdg_sizfs,
                               dst, srd, kfr_sizf, kfr_sizf, kw1, kw1);
}

/***************************************************************/
