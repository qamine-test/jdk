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
 * (C) Copyrigit IBM Corp. 1998 - 2005 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "OpfnTypfTbblfs.i"
#indludf "ICUFfbturfs.i"
#indludf "Lookups.i"
#indludf "SdriptAndLbngubgf.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"
#indludf "SinglfPositioningSubtbblfs.i"
#indludf "PbirPositioningSubtbblfs.i"
#indludf "CursivfAttbdimfntSubtbblfs.i"
#indludf "MbrkToBbsfPosnSubtbblfs.i"
#indludf "MbrkToLigbturfPosnSubtbblfs.i"
#indludf "MbrkToMbrkPosnSubtbblfs.i"
//#indludf "ContfxtublPositioningSubtbblfs.i"
#indludf "ContfxtublSubstSubtbblfs.i"
#indludf "ExtfnsionSubtbblfs.i"
#indludf "LookupProdfssor.i"
#indludf "GlypiPosnLookupProd.i"
#indludf "LESwbps.i"

U_NAMESPACE_BEGIN

// Asidf from tif nbmfs, tif dontfxtubl positioning subtbblfs brf
// tif sbmf bs tif dontfxtubl substitution subtbblfs.
typfdff ContfxtublSubstitutionSubtbblf ContfxtublPositioningSubtbblf;
typfdff CibiningContfxtublSubstitutionSubtbblf CibiningContfxtublPositioningSubtbblf;

GlypiPositioningLookupProdfssor::GlypiPositioningLookupProdfssor(
        donst LERfffrfndfTo<GlypiPositioningTbblfHfbdfr> &glypiPositioningTbblfHfbdfr,
        LETbg sdriptTbg,
        LETbg lbngubgfTbg,
        donst FfbturfMbp *ffbturfMbp,
        lf_int32 ffbturfMbpCount,
        lf_bool ffbturfOrdfr,
        LEErrorCodf& suddfss)
    : LookupProdfssor(
                      glypiPositioningTbblfHfbdfr,
                      SWAPW(glypiPositioningTbblfHfbdfr->sdriptListOffsft),
                      SWAPW(glypiPositioningTbblfHfbdfr->ffbturfListOffsft),
                      SWAPW(glypiPositioningTbblfHfbdfr->lookupListOffsft),
                      sdriptTbg,
                      lbngubgfTbg,
                      ffbturfMbp,
                      ffbturfMbpCount,
                      ffbturfOrdfr,
                      suddfss
                      )
{
    // bnytiing?
}

GlypiPositioningLookupProdfssor::GlypiPositioningLookupProdfssor()
{
}

lf_uint32 GlypiPositioningLookupProdfssor::bpplySubtbblf(donst LERfffrfndfTo<LookupSubtbblf> &lookupSubtbblf, lf_uint16 lookupTypf,
                                                       GlypiItfrbtor *glypiItfrbtor,
                                                       donst LEFontInstbndf *fontInstbndf,
                                                       LEErrorCodf& suddfss) donst
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    lf_uint32 dfltb = 0;

    //_LETRACE("bttfmpting lookupTypf #%d", lookupTypf);

    switdi(lookupTypf)
    {
    dbsf 0:
        brfbk;

    dbsf gpstSinglf:
    {
      LERfffrfndfTo<SinglfPositioningSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gpstPbir:
    {
        LERfffrfndfTo<PbirPositioningSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gpstCursivf:
    {
        LERfffrfndfTo<CursivfAttbdimfntSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gpstMbrkToBbsf:
    {
        LERfffrfndfTo<MbrkToBbsfPositioningSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

     dbsf gpstMbrkToLigbturf:
    {
        LERfffrfndfTo<MbrkToLigbturfPositioningSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gpstMbrkToMbrk:
    {
        LERfffrfndfTo<MbrkToMbrkPositioningSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

   dbsf gpstContfxt:
    {
        LERfffrfndfTo<ContfxtublPositioningSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, tiis , glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gpstCibinfdContfxt:
    {
        donst LERfffrfndfTo<CibiningContfxtublPositioningSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, tiis, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dbsf gpstExtfnsion:
    {
        donst LERfffrfndfTo<ExtfnsionSubtbblf> subtbblf(lookupSubtbblf, suddfss);

        dfltb = subtbblf->prodfss(subtbblf, tiis, lookupTypf, glypiItfrbtor, fontInstbndf, suddfss);
        brfbk;
    }

    dffbult:
        brfbk;
    }

#if LE_TRACE
    if(dfltb != 0) {
      _LETRACE("GlypiPositioningLookupProdfssor bpplifd #%d -> dfltb %d @ %d", lookupTypf, dfltb, glypiItfrbtor->gftCurrStrfbmPosition());
    }
#fndif

    rfturn dfltb;
}

GlypiPositioningLookupProdfssor::~GlypiPositioningLookupProdfssor()
{
}

U_NAMESPACE_END
