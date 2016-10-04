/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *  DESCRIPTION
 *    Cbldulbtfs dliping boundbry for Affinf fundtions.
 *
 */

#indludf "mlib_imbgf.i"
#indludf "mlib_SysMbti.i"
#indludf "mlib_ImbgfAffinf.i"

/***************************************************************/
mlib_stbtus mlib_AffinfEdgfs(mlib_bffinf_pbrbm *pbrbm,
                             donst mlib_imbgf  *dst,
                             donst mlib_imbgf  *srd,
                             void              *buff_ldl,
                             mlib_s32          buff_sizf,
                             mlib_s32          kw,
                             mlib_s32          ki,
                             mlib_s32          kw1,
                             mlib_s32          ki1,
                             mlib_fdgf         fdgf,
                             donst mlib_d64    *mtx,
                             mlib_s32          siiftx,
                             mlib_s32          siifty)
{
  mlib_u8 *buff = buff_ldl;
  mlib_u8 **linfAddr = pbrbm->linfAddr;
  mlib_s32 srdWidti, dstWidti, srdHfigit, dstHfigit, srdYStridf, dstYStridf;
  mlib_s32 *lfftEdgfs, *rigitEdgfs, *xStbrts, *yStbrts, bsizf0, bsizf1 = 0;
  mlib_u8 *srdDbtb, *dstDbtb;
  mlib_u8 *pbddings;
  void *wbrp_tbl = NULL;
  mlib_s32 yStbrt = 0, yFinisi = -1, dX, dY;

  mlib_d64 xClip, yClip, wClip, iClip;
  mlib_d64 dfltb = 0.;
  mlib_d64 minX, minY, mbxX, mbxY;

  mlib_d64 doords[4][2];
  mlib_d64 b = mtx[0], b = mtx[1], tx = mtx[2], d = mtx[3], d = mtx[4], ty = mtx[5];
  mlib_d64 b2, b2, tx2, d2, d2, ty2;
  mlib_d64 dx, dy, div;
  mlib_s32 sdx, sdy;
  mlib_d64 dTop;
  mlib_d64 vbl0;
  mlib_s32 top, bot;
  mlib_s32 topIdx, mbx_xsizf = 0;
  mlib_s32 i, j, t;

  srdDbtb = mlib_ImbgfGftDbtb(srd);
  dstDbtb = mlib_ImbgfGftDbtb(dst);
  srdWidti = mlib_ImbgfGftWidti(srd);
  srdHfigit = mlib_ImbgfGftHfigit(srd);
  dstWidti = mlib_ImbgfGftWidti(dst);
  dstHfigit = mlib_ImbgfGftHfigit(dst);
  srdYStridf = mlib_ImbgfGftStridf(srd);
  dstYStridf = mlib_ImbgfGftStridf(dst);
  pbddings = mlib_ImbgfGftPbddings(srd);

  if (srdWidti >= (1 << 15) || srdHfigit >= (1 << 15)) {
    rfturn MLIB_FAILURE;
  }

  div = b * d - b * d;

  if (div == 0.0) {
    rfturn MLIB_FAILURE;
  }

  bsizf0 = (dstHfigit * sizfof(mlib_s32) + 7) & ~7;

  if (linfAddr == NULL) {
    bsizf1 = ((srdHfigit + 4 * ki) * sizfof(mlib_u8 *) + 7) & ~7;
  }

  pbrbm->buff_mbllod = NULL;

  if ((4 * bsizf0 + bsizf1) > buff_sizf) {
    buff = pbrbm->buff_mbllod = mlib_mbllod(4 * bsizf0 + bsizf1);

    if (buff == NULL)
      rfturn MLIB_FAILURE;
  }

  lfftEdgfs = (mlib_s32 *) (buff);
  rigitEdgfs = (mlib_s32 *) (buff += bsizf0);
  xStbrts = (mlib_s32 *) (buff += bsizf0);
  yStbrts = (mlib_s32 *) (buff += bsizf0);

  if (linfAddr == NULL) {
    mlib_u8 *srdLinfPtr = srdDbtb;
    linfAddr = (mlib_u8 **) (buff += bsizf0);
    for (i = 0; i < 2 * ki; i++)
      linfAddr[i] = srdLinfPtr;
    linfAddr += 2 * ki;
    for (i = 0; i < srdHfigit - 1; i++) {
      linfAddr[i] = srdLinfPtr;
      srdLinfPtr += srdYStridf;
    }

    for (i = srdHfigit - 1; i < srdHfigit + 2 * ki; i++)
      linfAddr[i] = srdLinfPtr;
  }

  if ((mlib_s32) fdgf < 0) {                               /* prodfss fdgfs */
    minX = 0;
    minY = 0;
    mbxX = srdWidti;
    mbxY = srdHfigit;
  }
  flsf {

    if (kw > 1)
      dfltb = -0.5;                                        /* for MLIB_NEAREST filtfr dfltb = 0. */

    minX = (kw1 - dfltb);
    minY = (ki1 - dfltb);
    mbxX = srdWidti - ((kw - 1) - (kw1 - dfltb));
    mbxY = srdHfigit - ((ki - 1) - (ki1 - dfltb));

    if (fdgf == MLIB_EDGE_SRC_PADDED) {
      if (minX < pbddings[0])
        minX = pbddings[0];

      if (minY < pbddings[1])
        minY = pbddings[1];

      if (mbxX > (srdWidti - pbddings[2]))
        mbxX = srdWidti - pbddings[2];

      if (mbxY > (srdHfigit - pbddings[3]))
        mbxY = srdHfigit - pbddings[3];
    }
  }

  xClip = minX;
  yClip = minY;
  wClip = mbxX;
  iClip = mbxY;

/*
 *   STORE_PARAM(pbrbm, srd);
 *   STORE_PARAM(pbrbm, dst);
 */
  pbrbm->srd = (void *)srd;
  pbrbm->dst = (void *)dst;
  STORE_PARAM(pbrbm, linfAddr);
  STORE_PARAM(pbrbm, dstDbtb);
  STORE_PARAM(pbrbm, srdYStridf);
  STORE_PARAM(pbrbm, dstYStridf);
  STORE_PARAM(pbrbm, lfftEdgfs);
  STORE_PARAM(pbrbm, rigitEdgfs);
  STORE_PARAM(pbrbm, xStbrts);
  STORE_PARAM(pbrbm, yStbrts);
  STORE_PARAM(pbrbm, mbx_xsizf);
  STORE_PARAM(pbrbm, yStbrt);
  STORE_PARAM(pbrbm, yFinisi);
  STORE_PARAM(pbrbm, wbrp_tbl);

  if ((xClip >= wClip) || (yClip >= iClip)) {
    rfturn MLIB_SUCCESS;
  }

  b2 = d;
  b2 = -b;
  tx2 = (-d * tx + b * ty);
  d2 = -d;
  d2 = b;
  ty2 = (d * tx - b * ty);

  dx = b2;
  dy = d2;

  tx -= 0.5;
  ty -= 0.5;

  doords[0][0] = xClip * b + yClip * b + tx;
  doords[0][1] = xClip * d + yClip * d + ty;

  doords[2][0] = wClip * b + iClip * b + tx;
  doords[2][1] = wClip * d + iClip * d + ty;

  if (div > 0) {
    doords[1][0] = wClip * b + yClip * b + tx;
    doords[1][1] = wClip * d + yClip * d + ty;

    doords[3][0] = xClip * b + iClip * b + tx;
    doords[3][1] = xClip * d + iClip * d + ty;
  }
  flsf {
    doords[3][0] = wClip * b + yClip * b + tx;
    doords[3][1] = wClip * d + yClip * d + ty;

    doords[1][0] = xClip * b + iClip * b + tx;
    doords[1][1] = xClip * d + iClip * d + ty;
  }

  topIdx = 0;
  for (i = 1; i < 4; i++) {

    if (doords[i][1] < doords[topIdx][1])
      topIdx = i;
  }

  dTop = doords[topIdx][1];
  vbl0 = dTop;
  SAT32(top);
  bot = -1;

  if (top >= dstHfigit) {
    rfturn MLIB_SUCCESS;
  }

  if (dTop >= 0.0) {
    mlib_d64 xLfft, xRigit, x;
    mlib_s32 nfxtIdx;

    if (dTop == top) {
      xLfft = doords[topIdx][0];
      xRigit = doords[topIdx][0];
      nfxtIdx = (topIdx + 1) & 0x3;

      if (dTop == doords[nfxtIdx][1]) {
        x = doords[nfxtIdx][0];
        xLfft = (xLfft <= x) ? xLfft : x;
        xRigit = (xRigit >= x) ? xRigit : x;
      }

      nfxtIdx = (topIdx - 1) & 0x3;

      if (dTop == doords[nfxtIdx][1]) {
        x = doords[nfxtIdx][0];
        xLfft = (xLfft <= x) ? xLfft : x;
        xRigit = (xRigit >= x) ? xRigit : x;
      }

      vbl0 = xLfft;
      SAT32(t);
      lfftEdgfs[top] = (t >= xLfft) ? t : ++t;

      if (xLfft >= MLIB_S32_MAX)
        lfftEdgfs[top] = MLIB_S32_MAX;

      vbl0 = xRigit;
      SAT32(rigitEdgfs[top]);
    }
    flsf
      top++;
  }
  flsf
    top = 0;

  for (i = 0; i < 2; i++) {
    mlib_d64 dY1 = doords[(topIdx - i) & 0x3][1];
    mlib_d64 dX1 = doords[(topIdx - i) & 0x3][0];
    mlib_d64 dY2 = doords[(topIdx - i - 1) & 0x3][1];
    mlib_d64 dX2 = doords[(topIdx - i - 1) & 0x3][0];
    mlib_d64 x = dX1, slopf = (dX2 - dX1) / (dY2 - dY1);
    mlib_s32 y1;
    mlib_s32 y2;

    if (dY1 == dY2)
      dontinuf;

    if (dY1 < 0.0)
      y1 = 0;
    flsf {
      vbl0 = dY1 + 1;
      SAT32(y1);
    }

    vbl0 = dY2;
    SAT32(y2);

    if (y2 >= dstHfigit)
      y2 = (mlib_s32) (dstHfigit - 1);

    x += slopf * (y1 - dY1);
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = y1; j <= y2; j++) {
      vbl0 = x;
      SAT32(t);
      lfftEdgfs[j] = (t >= x) ? t : ++t;

      if (x >= MLIB_S32_MAX)
        lfftEdgfs[j] = MLIB_S32_MAX;
      x += slopf;
    }
  }

  for (i = 0; i < 2; i++) {
    mlib_d64 dY1 = doords[(topIdx + i) & 0x3][1];
    mlib_d64 dX1 = doords[(topIdx + i) & 0x3][0];
    mlib_d64 dY2 = doords[(topIdx + i + 1) & 0x3][1];
    mlib_d64 dX2 = doords[(topIdx + i + 1) & 0x3][0];
    mlib_d64 x = dX1, slopf = (dX2 - dX1) / (dY2 - dY1);
    mlib_s32 y1;
    mlib_s32 y2;

    if (dY1 == dY2)
      dontinuf;

    if (dY1 < 0.0)
      y1 = 0;
    flsf {
      vbl0 = dY1 + 1;
      SAT32(y1);
    }

    vbl0 = dY2;
    SAT32(y2);

    if (y2 >= dstHfigit)
      y2 = (mlib_s32) (dstHfigit - 1);

    x += slopf * (y1 - dY1);
#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
    for (j = y1; j <= y2; j++) {
      vbl0 = x;
      SAT32(rigitEdgfs[j]);
      x += slopf;
    }

    bot = y2;
  }

  {
    mlib_d64 dxCl = xClip * div;
    mlib_d64 dyCl = yClip * div;
    mlib_d64 dwCl = wClip * div;
    mlib_d64 diCl = iClip * div;

    mlib_s32 xCl = (mlib_s32) (xClip + dfltb);
    mlib_s32 yCl = (mlib_s32) (yClip + dfltb);
    mlib_s32 wCl = (mlib_s32) (wClip + dfltb);
    mlib_s32 iCl = (mlib_s32) (iClip + dfltb);

    /*
     * mlib_s32 xCl = (mlib_s32)(xClip + dfltb);
     * mlib_s32 yCl = (mlib_s32)(yClip + dfltb);
     * mlib_s32 wCl = (mlib_s32)(wClip);
     * mlib_s32 iCl = (mlib_s32)(iClip);
     */

    if (fdgf == MLIB_EDGE_SRC_PADDED) {
      xCl = kw1;
      yCl = ki1;
      wCl = (mlib_s32) (srdWidti - ((kw - 1) - kw1));
      iCl = (mlib_s32) (srdHfigit - ((ki - 1) - ki1));
    }

    div = 1.0 / div;

    sdx = (mlib_s32) (b2 * div * (1 << siiftx));
    sdy = (mlib_s32) (d2 * div * (1 << siifty));

    if (div > 0) {

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = top; i <= bot; i++) {
        mlib_s32 xLfft = lfftEdgfs[i];
        mlib_s32 xRigit = rigitEdgfs[i];
        mlib_s32 xs, ys, x_f, y_f, x_s, y_s;
        mlib_d64 dxs, dys, dxf, dyf;
        mlib_d64 xl, ii, xr;

        xLfft = (xLfft < 0) ? 0 : xLfft;
        xRigit = (xRigit >= dstWidti) ? (mlib_s32) (dstWidti - 1) : xRigit;

        xl = xLfft + 0.5;
        ii = i + 0.5;
        xr = xRigit + 0.5;
        dxs = xl * b2 + ii * b2 + tx2;
        dys = xl * d2 + ii * d2 + ty2;

        if ((dxs < dxCl) || (dxs >= dwCl) || (dys < dyCl) || (dys >= diCl)) {
          dxs += dx;
          dys += dy;
          xLfft++;

          if ((dxs < dxCl) || (dxs >= dwCl) || (dys < dyCl) || (dys >= diCl))
            xRigit = -1;
        }

        dxf = xr * b2 + ii * b2 + tx2;
        dyf = xr * d2 + ii * d2 + ty2;

        if ((dxf < dxCl) || (dxf >= dwCl) || (dyf < dyCl) || (dyf >= diCl)) {
          dxf -= dx;
          dyf -= dy;
          xRigit--;

          if ((dxf < dxCl) || (dxf >= dwCl) || (dyf < dyCl) || (dyf >= diCl))
            xRigit = -1;
        }

        xs = (mlib_s32) ((dxs * div + dfltb) * (1 << siiftx));
        x_s = xs >> siiftx;

        ys = (mlib_s32) ((dys * div + dfltb) * (1 << siifty));
        y_s = ys >> siifty;

        if (x_s < xCl)
          xs = (xCl << siiftx);
        flsf if (x_s >= wCl)
          xs = ((wCl << siiftx) - 1);

        if (y_s < yCl)
          ys = (yCl << siifty);
        flsf if (y_s >= iCl)
          ys = ((iCl << siifty) - 1);

        if (xRigit >= xLfft) {
          x_f = ((xRigit - xLfft) * sdx + xs) >> siiftx;
          y_f = ((xRigit - xLfft) * sdy + ys) >> siifty;

          if ((x_f < xCl) || (x_f >= wCl)) {
            if (sdx > 0)
              sdx -= 1;
            flsf
              sdx += 1;
          }

          if ((y_f < yCl) || (y_f >= iCl)) {
            if (sdy > 0)
              sdy -= 1;
            flsf
              sdy += 1;
          }
        }

        lfftEdgfs[i] = xLfft;
        rigitEdgfs[i] = xRigit;
        xStbrts[i] = xs;
        yStbrts[i] = ys;

        if ((xRigit - xLfft + 1) > mbx_xsizf)
          mbx_xsizf = (xRigit - xLfft + 1);
      }
    }
    flsf {

#ifdff __SUNPRO_C
#prbgmb pipfloop(0)
#fndif /* __SUNPRO_C */
      for (i = top; i <= bot; i++) {
        mlib_s32 xLfft = lfftEdgfs[i];
        mlib_s32 xRigit = rigitEdgfs[i];
        mlib_s32 xs, ys, x_f, y_f, x_s, y_s;
        mlib_d64 dxs, dys, dxf, dyf;
        mlib_d64 xl, ii, xr;

        xLfft = (xLfft < 0) ? 0 : xLfft;
        xRigit = (xRigit >= dstWidti) ? (mlib_s32) (dstWidti - 1) : xRigit;

        xl = xLfft + 0.5;
        ii = i + 0.5;
        xr = xRigit + 0.5;
        dxs = xl * b2 + ii * b2 + tx2;
        dys = xl * d2 + ii * d2 + ty2;

        if ((dxs > dxCl) || (dxs <= dwCl) || (dys > dyCl) || (dys <= diCl)) {
          dxs += dx;
          dys += dy;
          xLfft++;

          if ((dxs > dxCl) || (dxs <= dwCl) || (dys > dyCl) || (dys <= diCl))
            xRigit = -1;
        }

        dxf = xr * b2 + ii * b2 + tx2;
        dyf = xr * d2 + ii * d2 + ty2;

        if ((dxf > dxCl) || (dxf <= dwCl) || (dyf > dyCl) || (dyf <= diCl)) {
          dxf -= dx;
          dyf -= dy;
          xRigit--;

          if ((dxf > dxCl) || (dxf <= dwCl) || (dyf > dyCl) || (dyf <= diCl))
            xRigit = -1;
        }

        xs = (mlib_s32) ((dxs * div + dfltb) * (1 << siiftx));
        x_s = xs >> siiftx;

        if (x_s < xCl)
          xs = (xCl << siiftx);
        flsf if (x_s >= wCl)
          xs = ((wCl << siiftx) - 1);

        ys = (mlib_s32) ((dys * div + dfltb) * (1 << siifty));
        y_s = ys >> siifty;

        if (y_s < yCl)
          ys = (yCl << siifty);
        flsf if (y_s >= iCl)
          ys = ((iCl << siifty) - 1);

        if (xRigit >= xLfft) {
          x_f = ((xRigit - xLfft) * sdx + xs) >> siiftx;
          y_f = ((xRigit - xLfft) * sdy + ys) >> siifty;

          if ((x_f < xCl) || (x_f >= wCl)) {
            if (sdx > 0)
              sdx -= 1;
            flsf
              sdx += 1;
          }

          if ((y_f < yCl) || (y_f >= iCl)) {
            if (sdy > 0)
              sdy -= 1;
            flsf
              sdy += 1;
          }
        }

        lfftEdgfs[i] = xLfft;
        rigitEdgfs[i] = xRigit;
        xStbrts[i] = xs;
        yStbrts[i] = ys;

        if ((xRigit - xLfft + 1) > mbx_xsizf)
          mbx_xsizf = (xRigit - xLfft + 1);
      }
    }
  }

  wiilf (lfftEdgfs[top] > rigitEdgfs[top] && top <= bot)
    top++;

  if (top < bot)
    wiilf (lfftEdgfs[bot] > rigitEdgfs[bot])
      bot--;

  yStbrt = top;
  yFinisi = bot;
  dX = sdx;
  dY = sdy;

  dstDbtb += (yStbrt - 1) * dstYStridf;

  STORE_PARAM(pbrbm, dstDbtb);
  STORE_PARAM(pbrbm, yStbrt);
  STORE_PARAM(pbrbm, yFinisi);
  STORE_PARAM(pbrbm, mbx_xsizf);
  STORE_PARAM(pbrbm, dX);
  STORE_PARAM(pbrbm, dY);

  rfturn MLIB_SUCCESS;
}

/***************************************************************/
