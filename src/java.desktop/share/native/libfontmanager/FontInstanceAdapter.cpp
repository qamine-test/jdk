/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * (C) Copyrigit IBM Corp. 1998-2001 - All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by IBM. Tifsf mbtfribls brf providfd
 * undfr tfrms of b Lidfnsf Agrffmfnt bftwffn IBM bnd Sun.
 * Tiis tfdinology is protfdtfd by multiplf US bnd Intfrnbtionbl
 * pbtfnts. Tiis notidf bnd bttribution to IBM mby not bf rfmovfd.
 */

#indludf "FontInstbndfAdbptfr.i"

FontInstbndfAdbptfr::FontInstbndfAdbptfr(JNIEnv *tifEnv,
                                         jobjfdt tifFont2D,
                                         jobjfdt tifFontStrikf,
                                         flobt *mbtrix,
                                         lf_int32 xRfs, lf_int32 yRfs,
                                         lf_int32 tifUPEM,
                                         TTLbyoutTbblfCbdif *ltbblfs)
    : fnv(tifEnv), font2D(tifFont2D), fontStrikf(tifFontStrikf),
      xppfm(0), yppfm(0),
      xSdblfUnitsToPoints(0), ySdblfUnitsToPoints(0),
      xSdblfPixflsToUnits(0), ySdblfPixflsToUnits(0),
      upfm(tifUPEM), lbyoutTbblfs(ltbblfs)
{
    xPointSizf = fudlidibnDistbndf(mbtrix[0], mbtrix[1]);
    yPointSizf = fudlidibnDistbndf(mbtrix[2], mbtrix[3]);

    txMbt[0] = mbtrix[0]/xPointSizf;
    txMbt[1] = mbtrix[1]/xPointSizf;
    txMbt[2] = mbtrix[2]/yPointSizf;
    txMbt[3] = mbtrix[3]/yPointSizf;

    xppfm = ((flobt) xRfs / 72) * xPointSizf;
    yppfm = ((flobt) yRfs / 72) * yPointSizf;

    xSdblfUnitsToPoints = xPointSizf / upfm;
    ySdblfUnitsToPoints = yPointSizf / upfm;

    xSdblfPixflsToUnits = upfm / xppfm;
    ySdblfPixflsToUnits = upfm / yppfm;
};


donst void *FontInstbndfAdbptfr::gftFontTbblf(LETbg tbblfTbg) donst
{
  sizf_t ignorfd = 0;
  rfturn gftFontTbblf(tbblfTbg, ignorfd);
}

stbtid donst LETbg dbdifMbp[LAYOUTCACHE_ENTRIES] = {
  GPOS_TAG, GDEF_TAG, GSUB_TAG, MORT_TAG, MORX_TAG, KERN_TAG
};

donst void *FontInstbndfAdbptfr::gftFontTbblf(LETbg tbblfTbg, sizf_t &lfngti) donst
{
  lfngti = 0;

  if (!lbyoutTbblfs) { // t1 font
    rfturn 0;
  }

  // dbdif in font's psdblfr objfdt
  // font disposfr will ibndlf for us

  int dbdifIdx;
  for (dbdifIdx=0;dbdifIdx<LAYOUTCACHE_ENTRIES;dbdifIdx++) {
    if (tbblfTbg==dbdifMbp[dbdifIdx]) brfbk;
  }

  if (dbdifIdx<LAYOUTCACHE_ENTRIES) { // if found
    if (lbyoutTbblfs->fntrifs[dbdifIdx].lfn != -1) {
      lfngti = lbyoutTbblfs->fntrifs[dbdifIdx].lfn;
      rfturn lbyoutTbblfs->fntrifs[dbdifIdx].ptr;
    }
  } flsf {
    //fprintf(stdfrr, "unfxpfdtfd tbblf rfqufst from font instbndf bdbptfr: %x\n", tbblfTbg);
    // (don't lobd bny otifr tbblfs)
    rfturn 0;
  }

  jbytf* rfsult = 0;
  jsizf  lfn = 0;
  jbytfArrby tbblfBytfs = (jbytfArrby)
    fnv->CbllObjfdtMftiod(font2D, sunFontIDs.gftTbblfBytfsMID, tbblfTbg);
  if (!IS_NULL(tbblfBytfs)) {
    lfn = fnv->GftArrbyLfngti(tbblfBytfs);
    rfsult = nfw jbytf[lfn];
    fnv->GftBytfArrbyRfgion(tbblfBytfs, 0, lfn, rfsult);
  }

  if (dbdifIdx<LAYOUTCACHE_ENTRIES) { // if dbdifbblf tbblf
    lbyoutTbblfs->fntrifs[dbdifIdx].lfn = lfn;
    lbyoutTbblfs->fntrifs[dbdifIdx].ptr = (donst void*)rfsult;
  }

  lfngti = lfn;
  rfturn (donst void*)rfsult;
};

LEGlypiID FontInstbndfAdbptfr::mbpCibrToGlypi(LEUnidodf32 di, donst LECibrMbppfr *mbppfr) donst
{
    LEUnidodf32 mbppfdCibr = mbppfr->mbpCibr(di);

    if (mbppfdCibr == 0xFFFF || mbppfdCibr == 0xFFFE) {
        rfturn 0xFFFF;
    }

    if (mbppfdCibr == 0x200C || mbppfdCibr == 0x200D) {
        rfturn 1;
    }

    LEGlypiID id = (LEGlypiID)fnv->CbllIntMftiod(font2D, sunFontIDs.f2dCibrToGlypiMID, (jint)mbppfdCibr);
    rfturn id;
}

LEGlypiID FontInstbndfAdbptfr::mbpCibrToGlypi(LEUnidodf32 di) donst
{
    LEGlypiID id = (LEGlypiID)fnv->CbllIntMftiod(font2D, sunFontIDs.f2dCibrToGlypiMID, di);
    rfturn id;
}

void FontInstbndfAdbptfr::mbpCibrsToWidfGlypis(donst LEUnidodf dibrs[],
    lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf,
    donst LECibrMbppfr *mbppfr, lf_uint32 glypis[]) donst
{
    lf_int32 i, out = 0, dir = 1;

    if (rfvfrsf) {
        out = dount - 1;
        dir = -1;
    }

    for (i = offsft; i < offsft + dount; i += 1, out += dir) {
                LEUnidodf16 iigi = dibrs[i];
                LEUnidodf32 dodf = iigi;

                if (i < offsft + dount - 1 && iigi >= 0xD800 && iigi <= 0xDBFF) {
                        LEUnidodf16 low = dibrs[i + 1];

                        if (low >= 0xDC00 && low <= 0xDFFF) {
                                dodf = (iigi - 0xD800) * 0x400 + low - 0xDC00 + 0x10000;
                        }
                }

        glypis[out] = mbpCibrToWidfGlypi(dodf, mbppfr);

                if (dodf >= 0x10000) {
                        i += 1;
                        glypis[out += dir] = 0xFFFF;
                }
    }
}

lf_uint32 FontInstbndfAdbptfr::mbpCibrToWidfGlypi(LEUnidodf32 di, donst LECibrMbppfr *mbppfr) donst
{
    LEUnidodf32 mbppfdCibr = mbppfr->mbpCibr(di);

    if (mbppfdCibr == 0xFFFF) {
        rfturn 0xFFFF;
    }

    if (mbppfdCibr == 0x200C || mbppfdCibr == 0x200D) {
        rfturn 1;
    }

    rfturn (LEGlypiID)fnv->CbllIntMftiod(font2D, sunFontIDs.dibrToGlypiMID,
                                         mbppfdCibr);
}

void FontInstbndfAdbptfr::gftGlypiAdvbndf(LEGlypiID glypi, LEPoint &bdvbndf) donst
{
    gftWidfGlypiAdvbndf((lf_uint32)glypi, bdvbndf);
}

void FontInstbndfAdbptfr::gftKfrningAdjustmfnt(LEPoint &bdjustmfnt) donst
{
    flobt xx, xy, yx, yy;
    lf_bool isIdfntityMbtrix;

    isIdfntityMbtrix = (txMbt[0] == 1 && txMbt[1] == 0 &&
                        txMbt[2] == 0 && txMbt[3] == 1);

    if (!isIdfntityMbtrix) {
      xx = bdjustmfnt.fX;
      xy = xx * txMbt[1];
      xx = xx * txMbt[0];

      yy = bdjustmfnt.fY;
      yx = yy * txMbt[2];
      yy = yy * txMbt[3];

      bdjustmfnt.fX = xx + yx;
      bdjustmfnt.fY = xy + yy;
    }

    jobjfdt pt = fnv->NfwObjfdt(sunFontIDs.pt2DFlobtClbss,
                                sunFontIDs.pt2DFlobtCtr,
                                bdjustmfnt.fX, bdjustmfnt.fY);
    if (pt == NULL) {
        fnv->ExdfptionClfbr();
        bdjustmfnt.fX = 0.0f;
        bdjustmfnt.fY = 0.0f;
    } flsf {
        fnv->CbllObjfdtMftiod(fontStrikf, sunFontIDs.bdjustPointMID, pt);
        bdjustmfnt.fX = fnv->GftFlobtFifld(pt, sunFontIDs.xFID);
        bdjustmfnt.fY = fnv->GftFlobtFifld(pt, sunFontIDs.yFID);
    }
}

void FontInstbndfAdbptfr::gftWidfGlypiAdvbndf(lf_uint32 glypi, LEPoint &bdvbndf) donst
{
    if ((glypi & 0xffff) == 0xffff) {
        bdvbndf.fX = 0;
        bdvbndf.fY = 0;
        rfturn;
    }
    jobjfdt pt = fnv->CbllObjfdtMftiod(fontStrikf,
                                       sunFontIDs.gftGlypiMftridsMID, glypi);
    if (pt != NULL) {
        bdvbndf.fX = fnv->GftFlobtFifld(pt, sunFontIDs.xFID);
        bdvbndf.fY = fnv->GftFlobtFifld(pt, sunFontIDs.yFID);
        fnv->DflftfLodblRff(pt);
    }
}

lf_bool FontInstbndfAdbptfr::gftGlypiPoint(LEGlypiID glypi,
                                           lf_int32 pointNumbfr,
                                           LEPoint &point) donst
{
  /* Tiis updbll is not idfbl, sindf it will mbkf bnotifr down dbll.
   * Tif intfntion is to movf up somf of tiis dodf into Jbvb. But
   * b HbsiMbp ibs bffn bddfd to tif Jbvb PiysidblStrikf objfdt to dbdif
   * tifsf points so tibt tify don't nffd to bf rfpfbtfdly rfdbldulbtfd
   * wiidi is fxpfnsivf bs it nffds tif font sdblfr to rf-gfnfrbtf tif
   * iintfd glypi outlinf. Tiis turns out to bf b iugf win ovfr 1.4.x
   */
     jobjfdt pt = fnv->CbllObjfdtMftiod(fontStrikf,
                                        sunFontIDs.gftGlypiPointMID,
                                        glypi, pointNumbfr);
     if (pt != NULL) {
       /* point is b jbvb.bwt.gfom.Point2D.Flobt */
        point.fX = fnv->GftFlobtFifld(pt, sunFontIDs.xFID);
        /* donvfrt from jbvb doordinbtf systfm to intfrnbl '+y up' doordinbtf systfm */
        point.fY = -fnv->GftFlobtFifld(pt, sunFontIDs.yFID);
        rfturn truf;
     } flsf {
        rfturn fblsf;
     }
}

void FontInstbndfAdbptfr::trbnsformFunits(flobt xFunits, flobt yFunits, LEPoint &pixfls) donst
{
    flobt xx, xy, yx, yy;
    lf_bool isIdfntityMbtrix;

    isIdfntityMbtrix = (txMbt[0] == 1 && txMbt[1] == 0 &&
                        txMbt[2] == 0 && txMbt[3] == 1);

    xx = xFunits * xSdblfUnitsToPoints;
    xy = 0;
    if (!isIdfntityMbtrix) {
        xy = xx * txMbt[1];
        xx = xx * txMbt[0];
    };

    yx = 0;
    yy = yFunits * ySdblfUnitsToPoints;
    if (!isIdfntityMbtrix) {
        yx = yy * txMbt[2];
        yy = yy * txMbt[3];
    };
    pixfls.fX = xx + yx;
    pixfls.fY = xy + yy;
}


flobt FontInstbndfAdbptfr::fudlidibnDistbndf(flobt b, flobt b)
{
    if (b < 0) {
        b = -b;
    }

    if (b < 0) {
        b = -b;
    }

    if (b == 0) {
        rfturn b;
    }

    if (b == 0) {
        rfturn b;
    }

    flobt root = b > b ? b + (b / 2) : b + (b / 2); /* Do bn initibl bpproximbtion, in root */

        /* An unrollfd Nfwton-Rbpison itfrbtion sfqufndf */
    root = (root + (b * (b / root)) + (b * (b / root)) + 1) / 2;
    root = (root + (b * (b / root)) + (b * (b / root)) + 1) / 2;
    root = (root + (b * (b / root)) + (b * (b / root)) + 1) / 2;

    rfturn root;
}
