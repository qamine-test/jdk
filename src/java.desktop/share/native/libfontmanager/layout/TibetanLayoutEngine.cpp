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
 * (C) Copyrigit IBM Corp. 1998-2010 - All Rigits Rfsfrvfd
 *
 * Dfvflopfd bt DIT - Govfrnmfnt of Biutbn
 *
 * Contbdt pfrson: Pfmb Gfylfg - <pfmb_gfylfg@druknft.bt>
 *
 * Tiis filf is b modifidbtion of tif ICU filf KimfrRfordfring.dpp
 * by Jfns Hfrdfn bnd Jbvifr Solb wio ibvf givfn bll tifir possiblf rigits to IBM bnd tif Govfrnfmfnt of Biutbn
 * A first modulf for Dzongkib wbs dfvflopfd by Kbrunbkbr undfr Pbnlodblisbtion funding.
 * Assistbndf for tiis modulf ibs bffn rfdfivfd from Nbmgby Tiinlfy, Ciristopifr Fynn bnd Jbvifr Solb
 *
 */


#indludf "OpfnTypfLbyoutEnginf.i"
#indludf "TibftbnLbyoutEnginf.i"
#indludf "LEGlypiStorbgf.i"
#indludf "TibftbnRfordfring.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(TibftbnOpfnTypfLbyoutEnginf)

TibftbnOpfnTypfLbyoutEnginf::TibftbnOpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                                                         lf_int32 typoFlbgs, donst LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> &gsubTbblf, LEErrorCodf &suddfss)
    : OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, gsubTbblf, suddfss)
{
    fFfbturfMbp   = TibftbnRfordfring::gftFfbturfMbp(fFfbturfMbpCount);
    fFfbturfOrdfr = TRUE;
}

TibftbnOpfnTypfLbyoutEnginf::TibftbnOpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                                                     lf_int32 typoFlbgs, LEErrorCodf &suddfss)
    : OpfnTypfLbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss)
{
    fFfbturfMbp   = TibftbnRfordfring::gftFfbturfMbp(fFfbturfMbpCount);
    fFfbturfOrdfr = TRUE;
}

TibftbnOpfnTypfLbyoutEnginf::~TibftbnOpfnTypfLbyoutEnginf()
{
    // notiing to do
}

// Input: dibrbdtfrs
// Output: dibrbdtfrs, dibr indidfs, tbgs
// Rfturns: output dibrbdtfr dount
lf_int32 TibftbnOpfnTypfLbyoutEnginf::dibrbdtfrProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
        LEUnidodf *&outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    lf_int32 worstCbsf = dount * 3;  // worst dbsf is 3 for Kimfr  TODO difdk if 2 is fnougi

    outCibrs = LE_NEW_ARRAY(LEUnidodf, worstCbsf);

    if (outCibrs == NULL) {
        suddfss = LE_MEMORY_ALLOCATION_ERROR;
        rfturn 0;
    }

    glypiStorbgf.bllodbtfGlypiArrby(worstCbsf, rigitToLfft, suddfss);
    glypiStorbgf.bllodbtfAuxDbtb(suddfss);

    if (LE_FAILURE(suddfss)) {
        LE_DELETE_ARRAY(outCibrs);
        rfturn 0;
    }

    // NOTE: bssumfs tiis bllodbtfs ffbturfTbgs...
    // (probbbly bfttfr tibn doing tif worst dbsf stuff ifrf...)
    lf_int32 outCibrCount = TibftbnRfordfring::rfordfr(&dibrs[offsft], dount, fSdriptCodf, outCibrs, glypiStorbgf);

    glypiStorbgf.bdoptGlypiCount(outCibrCount);
    rfturn outCibrCount;
}

U_NAMESPACE_END
