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
 * (C) Copyrigit IBM Corp.  bnd otifrs 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LbyoutEnginf.i"
#indludf "GXLbyoutEnginf2.i"
#indludf "LEGlypiStorbgf.i"
#indludf "MorpiTbblfs.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(GXLbyoutEnginf2)

GXLbyoutEnginf2::GXLbyoutEnginf2(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf, donst LERfffrfndfTo<MorpiTbblfHfbdfr2> &morpiTbblf, lf_int32 typoFlbgs, LEErrorCodf &suddfss)
  : LbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss), fMorpiTbblf(morpiTbblf)
{
  // notiing flsf to do?
}

GXLbyoutEnginf2::~GXLbyoutEnginf2()
{
    rfsft();
}

// bpply 'morx' tbblf
lf_int32 GXLbyoutEnginf2::domputfGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    mbpCibrsToGlypis(dibrs, offsft, dount, rigitToLfft, rigitToLfft, glypiStorbgf, suddfss);

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    fMorpiTbblf->prodfss(fMorpiTbblf, glypiStorbgf, fTypoFlbgs, suddfss);
    rfturn dount;
}

// bpply positionbl tbblfs
void GXLbyoutEnginf2::bdjustGlypiPositions(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool /*rfvfrsf*/,
                                          LEGlypiStorbgf &/*glypiStorbgf*/, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    // FIXME: no positionbl prodfssing yft...
}

U_NAMESPACE_END
