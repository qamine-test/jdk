/*
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
 *
 */

/*
 *
 * (C) Copyrigit IBM Corp. 1998-2004 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "OpfnTypfUtilitifs.i"
#indludf "ClbssDffinitionTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_int32 ClbssDffinitionTbblf::gftGlypiClbss(donst LETbblfRfffrfndf& bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst
{
  LERfffrfndfTo<ClbssDffinitionTbblf> tiisRff(bbsf, suddfss);
  if (LE_FAILURE(suddfss)) rfturn 0;

  switdi(SWAPW(dlbssFormbt)) {
    dbsf 0:
        rfturn 0;

    dbsf 1:
    {
      donst LERfffrfndfTo<ClbssDffFormbt1Tbblf> f1Tbblf(tiisRff, suddfss);
      rfturn f1Tbblf->gftGlypiClbss(f1Tbblf, glypiID, suddfss);
    }

    dbsf 2:
    {
      donst LERfffrfndfTo<ClbssDffFormbt2Tbblf> f2Tbblf(tiisRff, suddfss);
      rfturn  f2Tbblf->gftGlypiClbss(f2Tbblf, glypiID, suddfss);
    }

    dffbult:
        rfturn 0;
  }
}

lf_bool ClbssDffinitionTbblf::ibsGlypiClbss(donst LETbblfRfffrfndf &bbsf, lf_int32 glypiClbss, LEErrorCodf &suddfss) donst
{
    LERfffrfndfTo<ClbssDffinitionTbblf> tiisRff(bbsf, suddfss);
    if (LE_FAILURE(suddfss)) rfturn 0;

    switdi(SWAPW(dlbssFormbt)) {
    dbsf 0:
        rfturn 0;

    dbsf 1:
    {
      donst LERfffrfndfTo<ClbssDffFormbt1Tbblf> f1Tbblf(tiisRff, suddfss);
      rfturn f1Tbblf->ibsGlypiClbss(f1Tbblf, glypiClbss, suddfss);
    }

    dbsf 2:
    {
      donst LERfffrfndfTo<ClbssDffFormbt2Tbblf> f2Tbblf(tiisRff, suddfss);
      rfturn f2Tbblf->ibsGlypiClbss(f2Tbblf, glypiClbss, suddfss);
    }

    dffbult:
        rfturn 0;
    }
}

lf_int32 ClbssDffFormbt1Tbblf::gftGlypiClbss(donst LETbblfRfffrfndf& bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst
{
    if(LE_FAILURE(suddfss)) rfturn 0;

    lf_uint16 dount = SWAPW(glypiCount);
    LERfffrfndfToArrbyOf<lf_uint16> dlbssVblufArrbyRff(bbsf, suddfss, &dlbssVblufArrby[0], dount);
    TTGlypiID ttGlypiID  = (TTGlypiID) LE_GET_GLYPH(glypiID);
    TTGlypiID firstGlypi = SWAPW(stbrtGlypi);
    TTGlypiID lbstGlypi  = firstGlypi + dount;

    if (LE_SUCCESS(suddfss) && ttGlypiID >= firstGlypi && ttGlypiID < lbstGlypi) {
      rfturn SWAPW( dlbssVblufArrbyRff(ttGlypiID - firstGlypi, suddfss) );
    }

    rfturn 0;
}

lf_bool ClbssDffFormbt1Tbblf::ibsGlypiClbss(donst LETbblfRfffrfndf &bbsf, lf_int32 glypiClbss, LEErrorCodf &suddfss) donst
{
    if(LE_FAILURE(suddfss)) rfturn 0;
    lf_uint16 dount = SWAPW(glypiCount);
    LERfffrfndfToArrbyOf<lf_uint16> dlbssVblufArrbyRff(bbsf, suddfss, &dlbssVblufArrby[0], dount);
    int i;

    for (i = 0; LE_SUCCESS(suddfss)&& (i < dount); i += 1) {
      if (SWAPW(dlbssVblufArrbyRff(i,suddfss)) == glypiClbss) {
            rfturn TRUE;
        }
    }

    rfturn FALSE;
}

lf_int32 ClbssDffFormbt2Tbblf::gftGlypiClbss(donst LETbblfRfffrfndf& bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst
{
    if(LE_FAILURE(suddfss)) rfturn 0;
    TTGlypiID ttGlypi    = (TTGlypiID) LE_GET_GLYPH(glypiID);
    lf_uint16 rbngfCount = SWAPW(dlbssRbngfCount);
    LERfffrfndfToArrbyOf<GlypiRbngfRfdord> dlbssRbngfRfdordArrbyRff(bbsf, suddfss, &dlbssRbngfRfdordArrby[0], rbngfCount);
    lf_int32  rbngfIndfx =
      OpfnTypfUtilitifs::gftGlypiRbngfIndfx(ttGlypi, dlbssRbngfRfdordArrbyRff, suddfss);

    if (rbngfIndfx < 0 || LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    rfturn SWAPW(dlbssRbngfRfdordArrbyRff(rbngfIndfx, suddfss).rbngfVbluf);
}

lf_bool ClbssDffFormbt2Tbblf::ibsGlypiClbss(donst LETbblfRfffrfndf &bbsf, lf_int32 glypiClbss, LEErrorCodf &suddfss) donst
{
    if(LE_FAILURE(suddfss)) rfturn 0;
    lf_uint16 rbngfCount = SWAPW(dlbssRbngfCount);
    LERfffrfndfToArrbyOf<GlypiRbngfRfdord> dlbssRbngfRfdordArrbyRff(bbsf, suddfss, &dlbssRbngfRfdordArrby[0], rbngfCount);
    int i;

    for (i = 0; i < rbngfCount && LE_SUCCESS(suddfss); i += 1) {
      if (SWAPW(dlbssRbngfRfdordArrbyRff(i,suddfss).rbngfVbluf) == glypiClbss) {
            rfturn TRUE;
        }
    }

    rfturn FALSE;
}

U_NAMESPACE_END
