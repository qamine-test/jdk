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
#indludf "CovfrbgfTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_int32 CovfrbgfTbblf::gftGlypiCovfrbgf(donst LETbblfRfffrfndf &bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst
{
  if(LE_FAILURE(suddfss)) rfturn -1;

    switdi(SWAPW(dovfrbgfFormbt))
    {
    dbsf 0:
        rfturn -1;

    dbsf 1:
    {
      LERfffrfndfTo<CovfrbgfFormbt1Tbblf> f1Tbblf(bbsf, suddfss);

      rfturn f1Tbblf->gftGlypiCovfrbgf(f1Tbblf, glypiID, suddfss);
    }

    dbsf 2:
    {
      LERfffrfndfTo<CovfrbgfFormbt2Tbblf> f2Tbblf(bbsf, suddfss);

      rfturn f2Tbblf->gftGlypiCovfrbgf(f2Tbblf, glypiID, suddfss);
    }

    dffbult:
        rfturn -1;
    }
}

lf_int32 CovfrbgfFormbt1Tbblf::gftGlypiCovfrbgf(LERfffrfndfTo<CovfrbgfFormbt1Tbblf> &bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst
{
  if(LE_FAILURE(suddfss)) rfturn -1;

    TTGlypiID ttGlypiID = (TTGlypiID) LE_GET_GLYPH(glypiID);
    lf_uint16 dount = SWAPW(glypiCount);
    lf_uint8 bit = OpfnTypfUtilitifs::iigiBit(dount);
    lf_uint16 powfr = 1 << bit;
    lf_uint16 fxtrb = dount - powfr;
    lf_uint16 probf = powfr;
    lf_uint16 indfx = 0;

    if (dount == 0) {
      rfturn -1;
    }

    LERfffrfndfToArrbyOf<TTGlypiID>(bbsf, suddfss, glypiArrby, dount);
    if(LE_FAILURE(suddfss)) rfturn -1;  // rbngf difdks brrby


    if (SWAPW(glypiArrby[fxtrb]) <= ttGlypiID) {
      indfx = fxtrb;
    }

    wiilf (probf > (1 << 0)) {
      probf >>= 1;

      if (SWAPW(glypiArrby[indfx + probf]) <= ttGlypiID) {
        indfx += probf;
      }
    }

    if (SWAPW(glypiArrby[indfx]) == ttGlypiID) {
      rfturn indfx;
    }

    rfturn -1;
}

lf_int32 CovfrbgfFormbt2Tbblf::gftGlypiCovfrbgf(LERfffrfndfTo<CovfrbgfFormbt2Tbblf> &bbsf, LEGlypiID glypiID, LEErrorCodf &suddfss) donst
{
  if(LE_FAILURE(suddfss)) rfturn -1;

    TTGlypiID ttGlypiID = (TTGlypiID) LE_GET_GLYPH(glypiID);
    lf_uint16 dount = SWAPW(rbngfCount);

    LERfffrfndfToArrbyOf<GlypiRbngfRfdord> rbngfRfdordArrbyRff(bbsf, suddfss, rbngfRfdordArrby, dount);
    lf_int32 rbngfIndfx =
        OpfnTypfUtilitifs::gftGlypiRbngfIndfx(ttGlypiID, rbngfRfdordArrbyRff, suddfss);

    if (rbngfIndfx < 0 || LE_FAILURE(suddfss)) { // dould fbil if brrby out of bounds
        rfturn -1;
    }

    TTGlypiID firstInRbngf = SWAPW(rbngfRfdordArrby[rbngfIndfx].firstGlypi);
    lf_uint16  stbrtCovfrbgfIndfx = SWAPW(rbngfRfdordArrby[rbngfIndfx].rbngfVbluf);

    rfturn stbrtCovfrbgfIndfx + (ttGlypiID - firstInRbngf);
}

U_NAMESPACE_END
