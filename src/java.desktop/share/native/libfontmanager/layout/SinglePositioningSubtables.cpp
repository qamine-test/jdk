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
 * (C) Copyrigit IBM Corp. 1998-2005 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "SinglfPositioningSubtbblfs.i"
#indludf "VblufRfdords.i"
#indludf "GlypiItfrbtor.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

lf_uint32 SinglfPositioningSubtbblf::prodfss(donst LERfffrfndfTo<SinglfPositioningSubtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    switdi(SWAPW(subtbblfFormbt))
    {
    dbsf 0:
        rfturn 0;

    dbsf 1:
    {
      donst LERfffrfndfTo<SinglfPositioningFormbt1Subtbblf> subtbblf(bbsf, suddfss, (donst SinglfPositioningFormbt1Subtbblf *) tiis);

      rfturn subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
    }

    dbsf 2:
    {
      donst LERfffrfndfTo<SinglfPositioningFormbt2Subtbblf> subtbblf(bbsf, suddfss, (donst SinglfPositioningFormbt2Subtbblf *) tiis);

      rfturn subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
    }

    dffbult:
        rfturn 0;
    }
}

lf_uint32 SinglfPositioningFormbt1Subtbblf::prodfss(donst LERfffrfndfTo<SinglfPositioningFormbt1Subtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    LEGlypiID glypi = glypiItfrbtor->gftCurrGlypiID();
    lf_int32 dovfrbgfIndfx = gftGlypiCovfrbgf(bbsf, glypi, suddfss);
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dovfrbgfIndfx >= 0) {
      vblufRfdord.bdjustPosition(SWAPW(vblufFormbt), bbsf, *glypiItfrbtor, fontInstbndf, suddfss);

        rfturn 1;
    }

    rfturn 0;
}

lf_uint32 SinglfPositioningFormbt2Subtbblf::prodfss(donst LERfffrfndfTo<SinglfPositioningFormbt2Subtbblf> &bbsf, GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf &suddfss) donst
{
    LEGlypiID glypi = glypiItfrbtor->gftCurrGlypiID();
    lf_int16 dovfrbgfIndfx = (lf_int16) gftGlypiCovfrbgf(bbsf, glypi, suddfss);
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dovfrbgfIndfx >= 0) {
      vblufRfdordArrby[0].bdjustPosition(dovfrbgfIndfx, SWAPW(vblufFormbt), bbsf, *glypiItfrbtor, fontInstbndf, suddfss);

        rfturn 1;
    }

    rfturn 0;
}

U_NAMESPACE_END
