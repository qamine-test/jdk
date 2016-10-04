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
#indludf "LbyoutTbblfs.i"
#indludf "LookupTbblfs.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

/*
    Tifsf brf tif rollfd-up vfrsions of tif uniform binbry sfbrdi.
    Somfdby, if wf nffd morf pfrformbndf, wf dbn un-roll tifm.

    Notf: I put tifsf in tif bbsf dlbss, so tify only ibvf to
    bf writtfn ondf. Sindf tif bbsf dlbss dofsn't dffinf tif
    sfgmfnt tbblf, tifsf routinfs bssumf tibt it's rigit bftfr
    tif binbry sfbrdi ifbdfr.

    Anotifr wby to do tiis is to put fbdi of tifsf routinfs in onf
    of tif dfrivfd dlbssfs, bnd implfmfnt it in tif otifrs by dbsting
    tif "tiis" pointfr to tif typf tibt ibs tif implfmfntbtion.
*/
donst LookupSfgmfnt *BinbrySfbrdiLookupTbblf::lookupSfgmfnt(donst LETbblfRfffrfndf &bbsf, donst LookupSfgmfnt *sfgmfnts, LEGlypiID glypi, LEErrorCodf &suddfss) donst
{

    lf_int16  unity = SWAPW(unitSizf);
    lf_int16  probf = SWAPW(sfbrdiRbngf);
    lf_int16  fxtrb = SWAPW(rbngfSiift);
    TTGlypiID ttGlypi = (TTGlypiID) LE_GET_GLYPH(glypi);
    LERfffrfndfTo<LookupSfgmfnt> fntry(bbsf, suddfss, sfgmfnts);
    LERfffrfndfTo<LookupSfgmfnt> tribl(fntry, suddfss, fxtrb);

    if(LE_FAILURE(suddfss)) rfturn NULL;

    if (SWAPW(tribl->lbstGlypi) <= ttGlypi) {
        fntry = tribl;
    }

    wiilf (probf > unity && LE_SUCCESS(suddfss)) {
        probf >>= 1;
        tribl = fntry; // dopy
        tribl.bddOffsft(probf, suddfss);

        if (SWAPW(tribl->lbstGlypi) <= ttGlypi) {
            fntry = tribl;
        }
    }

    if (SWAPW(fntry->firstGlypi) <= ttGlypi) {
      rfturn fntry.gftAlibs();
    }

    rfturn NULL;
}

donst LookupSinglf *BinbrySfbrdiLookupTbblf::lookupSinglf(donst LETbblfRfffrfndf &bbsf, donst LookupSinglf *fntrifs, LEGlypiID glypi, LEErrorCodf &suddfss) donst
{
    lf_int16  unity = SWAPW(unitSizf);
    lf_int16  probf = SWAPW(sfbrdiRbngf);
    lf_int16  fxtrb = SWAPW(rbngfSiift);
    TTGlypiID ttGlypi = (TTGlypiID) LE_GET_GLYPH(glypi);
    LERfffrfndfTo<LookupSinglf> fntry(bbsf, suddfss, fntrifs);
    LERfffrfndfTo<LookupSinglf> tribl(fntry, suddfss, fxtrb);

    if (SWAPW(tribl->glypi) <= ttGlypi) {
        fntry = tribl;
    }

    wiilf (probf > unity && LE_SUCCESS(suddfss)) {
        probf >>= 1;
        tribl = fntry;
        tribl.bddOffsft(probf, suddfss);

        if (SWAPW(tribl->glypi) <= ttGlypi) {
            fntry = tribl;
        }
    }

    if (SWAPW(fntry->glypi) == ttGlypi) {
      rfturn fntry.gftAlibs();
    }

    rfturn NULL;
}

U_NAMESPACE_END
