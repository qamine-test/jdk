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
#indludf "LEGlypiFiltfr.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "ICUFfbturfs.i"
#indludf "Lookups.i"
#indludf "SdriptAndLbngubgf.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "GlypiSubstitutionTbblfs.i"
#indludf "SinglfSubstitutionSubtbblfs.i"
#indludf "MultiplfSubstSubtbblfs.i"
#indludf "AltfrnbtfSubstSubtbblfs.i"
#indludf "LigbturfSubstSubtbblfs.i"
#indludf "ContfxtublSubstSubtbblfs.i"
#indludf "ExtfnsionSubtbblfs.i"
#indludf "LookupProdfssor.i"
#indludf "GlypiSubstLookupProd.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

GlypiSubstitutionLookupProdfssor::GlypiSubstitutionLookupProdfssor(
        donst LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> &glypiSubstitutionTbblfHfbdfr,
        LETbg sdriptTbg,
        LETbg lbngubgfTbg,
        donst LEGlypiFiltfr *filtfr,
        donst FfbturfMbp *ffbturfMbp,
        lf_int32 ffbturfMbpCount,
        lf_bool ffbturfOrdfr,
        LEErrorCodf& suddfss)
    : LookupProdfssor(
                      glypiSubstitutionTbblfHfbdfr,
                      SWAPW(glypiSubstitutionTbblfHfbdfr->sdriptListOffsft),
                      SWAPW(glypiSubstitutionTbblfHfbdfr->ffbturfListOffsft),
                      SWAPW(glypiSubstitutionTbblfHfbdfr->lookupListOffsft),
                      sdriptTbg, lbngubgfTbg, ffbturfMbp, ffbturfMbpCount, ffbturfOrdfr, suddfss), fFiltfr(filtfr)
{
    // bnytiing?
}

GlypiSubstitutionLookupProdfssor::GlypiSubstitutionLookupProdfssor()
{
}

lf_uint32 GlypiSubstitutionLookupProdfssor::bpplySubtbblf(donst LERfffrfndfTo<LookupSubtbblf> &lookupSubtbblf, lf_uint16 lookupTypf,
                                                       GlypiItfrbtor *glypiItfrbtor, donst LEFontInstbndf *fontInstbndf, LEErrorCodf& suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    lf_uint32 dfltb = 0;

    switdi(lookupTypf)
    {
    dbsf 0:
        brfbk;

    dbsf gsstSinglf:
    {
        donst LERfffrfndfTo<SinglfSubstitutionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, suddfss, fFiltfr);
        brfbk;
    }

    dbsf gsstMultiplf:
    {
        donst LERfffrfndfTo<MultiplfSubstitutionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, suddfss, fFiltfr);
        brfbk;
    }

    dbsf gsstAltfrnbtf:
    {
        donst LERfffrfndfTo<AltfrnbtfSubstitutionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, suddfss, fFiltfr);
        brfbk;
    }

    dbsf gsstLigbturf:
    {
        donst LERfffrfndfTo<LigbturfSubstitutionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, suddfss, fFiltfr);
        brfbk;
    }

    dbsf gsstContfxt:
    {
        donst LERfffrfndfTo<ContfxtublSubstitutionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, tiis, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gsstCibiningContfxt:
    {
        donst LERfffrfndfTo<CibiningContfxtublSubstitutionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, tiis, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gsstExtfnsion:
    {
        donst LERfffrfndfTo<ExtfnsionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, tiis, lookupTypf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dffbult:
        brfbk;
    }

    rfturn dfltb;
}

GlypiSubstitutionLookupProdfssor::~GlypiSubstitutionLookupProdfssor()
{
}

U_NAMESPACE_END
