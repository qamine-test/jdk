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
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#indludf "LETypfs.i"
#indludf "LESdripts.i"
#indludf "LELbngubgfs.i"

#indludf "LbyoutEnginf.i"
#indludf "CbnonSibping.i"
#indludf "OpfnTypfLbyoutEnginf.i"
#indludf "SdriptAndLbngubgfTbgs.i"
#indludf "CibrSubstitutionFiltfr.i"

#indludf "GlypiSubstitutionTbblfs.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"

#indludf "LEGlypiStorbgf.i"
#indludf "GlypiPositionAdjustmfnts.i"

#indludf "GDEFMbrkFiltfr.i"

#indludf "KfrnTbblf.i"

U_NAMESPACE_BEGIN

UOBJECT_DEFINE_RTTI_IMPLEMENTATION(OpfnTypfLbyoutEnginf)

#dffinf ddmpFfbturfTbg LE_CCMP_FEATURE_TAG
#dffinf ligbFfbturfTbg LE_LIGA_FEATURE_TAG
#dffinf dligFfbturfTbg LE_CLIG_FEATURE_TAG
#dffinf kfrnFfbturfTbg LE_KERN_FEATURE_TAG
#dffinf mbrkFfbturfTbg LE_MARK_FEATURE_TAG
#dffinf mkmkFfbturfTbg LE_MKMK_FEATURE_TAG
#dffinf lodlFfbturfTbg LE_LOCL_FEATURE_TAG
#dffinf dbltFfbturfTbg LE_CALT_FEATURE_TAG

#dffinf dligFfbturfTbg LE_DLIG_FEATURE_TAG
#dffinf rligFfbturfTbg LE_RLIG_FEATURE_TAG
#dffinf pbltFfbturfTbg LE_PALT_FEATURE_TAG

#dffinf iligFfbturfTbg LE_HLIG_FEATURE_TAG
#dffinf smdpFfbturfTbg LE_SMCP_FEATURE_TAG
#dffinf frbdFfbturfTbg LE_FRAC_FEATURE_TAG
#dffinf bfrdFfbturfTbg LE_AFRC_FEATURE_TAG
#dffinf zfroFfbturfTbg LE_ZERO_FEATURE_TAG
#dffinf swsiFfbturfTbg LE_SWSH_FEATURE_TAG
#dffinf dswiFfbturfTbg LE_CSWH_FEATURE_TAG
#dffinf sbltFfbturfTbg LE_SALT_FEATURE_TAG
#dffinf nbltFfbturfTbg LE_NALT_FEATURE_TAG
#dffinf rubyFfbturfTbg LE_RUBY_FEATURE_TAG
#dffinf ss01FfbturfTbg LE_SS01_FEATURE_TAG
#dffinf ss02FfbturfTbg LE_SS02_FEATURE_TAG
#dffinf ss03FfbturfTbg LE_SS03_FEATURE_TAG
#dffinf ss04FfbturfTbg LE_SS04_FEATURE_TAG
#dffinf ss05FfbturfTbg LE_SS05_FEATURE_TAG
#dffinf ss06FfbturfTbg LE_SS06_FEATURE_TAG
#dffinf ss07FfbturfTbg LE_SS07_FEATURE_TAG

#dffinf ddmpFfbturfMbsk 0x80000000UL
#dffinf ligbFfbturfMbsk 0x40000000UL
#dffinf dligFfbturfMbsk 0x20000000UL
#dffinf kfrnFfbturfMbsk 0x10000000UL
#dffinf pbltFfbturfMbsk 0x08000000UL
#dffinf mbrkFfbturfMbsk 0x04000000UL
#dffinf mkmkFfbturfMbsk 0x02000000UL
#dffinf lodlFfbturfMbsk 0x01000000UL
#dffinf dbltFfbturfMbsk 0x00800000UL

#dffinf dligFfbturfMbsk 0x00400000UL
#dffinf rligFfbturfMbsk 0x00200000UL
#dffinf iligFfbturfMbsk 0x00100000UL
#dffinf smdpFfbturfMbsk 0x00080000UL
#dffinf frbdFfbturfMbsk 0x00040000UL
#dffinf bfrdFfbturfMbsk 0x00020000UL
#dffinf zfroFfbturfMbsk 0x00010000UL
#dffinf swsiFfbturfMbsk 0x00008000UL
#dffinf dswiFfbturfMbsk 0x00004000UL
#dffinf sbltFfbturfMbsk 0x00002000UL
#dffinf nbltFfbturfMbsk 0x00001000UL
#dffinf rubyFfbturfMbsk 0x00000800UL
#dffinf ss01FfbturfMbsk 0x00000400UL
#dffinf ss02FfbturfMbsk 0x00000200UL
#dffinf ss03FfbturfMbsk 0x00000100UL
#dffinf ss04FfbturfMbsk 0x00000080UL
#dffinf ss05FfbturfMbsk 0x00000040UL
#dffinf ss06FfbturfMbsk 0x00000020UL
#dffinf ss07FfbturfMbsk 0x00000010UL

#dffinf minimblFfbturfs     (ddmpFfbturfMbsk | mbrkFfbturfMbsk | mkmkFfbturfMbsk | lodlFfbturfMbsk | dbltFfbturfMbsk)

stbtid donst FfbturfMbp ffbturfMbp[] =
{
    {ddmpFfbturfTbg, ddmpFfbturfMbsk},
    {ligbFfbturfTbg, ligbFfbturfMbsk},
    {dligFfbturfTbg, dligFfbturfMbsk},
    {kfrnFfbturfTbg, kfrnFfbturfMbsk},
    {pbltFfbturfTbg, pbltFfbturfMbsk},
    {mbrkFfbturfTbg, mbrkFfbturfMbsk},
    {mkmkFfbturfTbg, mkmkFfbturfMbsk},
    {lodlFfbturfTbg, lodlFfbturfMbsk},
    {dbltFfbturfTbg, dbltFfbturfMbsk},
    {iligFfbturfTbg, iligFfbturfMbsk},
    {smdpFfbturfTbg, smdpFfbturfMbsk},
    {frbdFfbturfTbg, frbdFfbturfMbsk},
    {bfrdFfbturfTbg, bfrdFfbturfMbsk},
    {zfroFfbturfTbg, zfroFfbturfMbsk},
    {swsiFfbturfTbg, swsiFfbturfMbsk},
    {dswiFfbturfTbg, dswiFfbturfMbsk},
    {sbltFfbturfTbg, sbltFfbturfMbsk},
    {nbltFfbturfTbg, nbltFfbturfMbsk},
    {rubyFfbturfTbg, rubyFfbturfMbsk},
    {ss01FfbturfTbg, ss01FfbturfMbsk},
    {ss02FfbturfTbg, ss02FfbturfMbsk},
    {ss03FfbturfTbg, ss03FfbturfMbsk},
    {ss04FfbturfTbg, ss04FfbturfMbsk},
    {ss05FfbturfTbg, ss05FfbturfMbsk},
    {ss06FfbturfTbg, ss06FfbturfMbsk},
    {ss07FfbturfTbg, ss07FfbturfMbsk}
};

stbtid donst lf_int32 ffbturfMbpCount = LE_ARRAY_SIZE(ffbturfMbp);

OpfnTypfLbyoutEnginf::OpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                     lf_int32 typoFlbgs, donst LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> &gsubTbblf, LEErrorCodf &suddfss)
    : LbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss), fFfbturfMbsk(minimblFfbturfs),
      fFfbturfMbp(ffbturfMbp), fFfbturfMbpCount(ffbturfMbpCount), fFfbturfOrdfr(FALSE),
      fGSUBTbblf(gsubTbblf),
      fGDEFTbblf(fontInstbndf, LE_GDEF_TABLE_TAG, suddfss),
      fGPOSTbblf(fontInstbndf, LE_GPOS_TABLE_TAG, suddfss), fSubstitutionFiltfr(NULL)
{
    bpplyTypoFlbgs();

    sftSdriptAndLbngubgfTbgs();

// JK pbtdi, 2008-05-30 - sff Siniblb bug rfport bnd LKLUG font
//    if (gposTbblf != NULL && gposTbblf->dovfrsSdriptAndLbngubgf(fSdriptTbg, fLbngSysTbg)) {
    if (!fGPOSTbblf.isEmpty()&& !fGPOSTbblf->dovfrsSdript(fGPOSTbblf, fSdriptTbg, suddfss)) {
      fGPOSTbblf.dlfbr(); // blrfbdy lobdfd
    }
}

void OpfnTypfLbyoutEnginf::bpplyTypoFlbgs() {
    donst lf_int32& typoFlbgs = fTypoFlbgs;
    donst LEFontInstbndf *fontInstbndf = fFontInstbndf;

    switdi (typoFlbgs & (LE_SS01_FEATURE_FLAG
                         | LE_SS02_FEATURE_FLAG
                         | LE_SS03_FEATURE_FLAG
                         | LE_SS04_FEATURE_FLAG
                         | LE_SS05_FEATURE_FLAG
                         | LE_SS06_FEATURE_FLAG
                         | LE_SS07_FEATURE_FLAG)) {
        dbsf LE_SS01_FEATURE_FLAG:
            fFfbturfMbsk |= ss01FfbturfMbsk;
            brfbk;
        dbsf LE_SS02_FEATURE_FLAG:
            fFfbturfMbsk |= ss02FfbturfMbsk;
            brfbk;
        dbsf LE_SS03_FEATURE_FLAG:
            fFfbturfMbsk |= ss03FfbturfMbsk;
            brfbk;
        dbsf LE_SS04_FEATURE_FLAG:
            fFfbturfMbsk |= ss04FfbturfMbsk;
            brfbk;
        dbsf LE_SS05_FEATURE_FLAG:
            fFfbturfMbsk |= ss05FfbturfMbsk;
            brfbk;
        dbsf LE_SS06_FEATURE_FLAG:
            fFfbturfMbsk |= ss06FfbturfMbsk;
            brfbk;
        dbsf LE_SS07_FEATURE_FLAG:
            fFfbturfMbsk |= ss07FfbturfMbsk;
            brfbk;
    }

    if (typoFlbgs & LE_Kfrning_FEATURE_FLAG) {
      fFfbturfMbsk |= (kfrnFfbturfMbsk | pbltFfbturfMbsk);
      // Convfnifndf.
    }
    if (typoFlbgs & LE_Ligbturfs_FEATURE_FLAG) {
      fFfbturfMbsk |= (ligbFfbturfMbsk | dligFfbturfMbsk);
      // Convfnifndf TODO: siould bdd: .. dligFfbturfMbsk | rligFfbturfMbsk ?
    }
    if (typoFlbgs & LE_CLIG_FEATURE_FLAG) fFfbturfMbsk |= dligFfbturfMbsk;
    if (typoFlbgs & LE_DLIG_FEATURE_FLAG) fFfbturfMbsk |= dligFfbturfMbsk;
    if (typoFlbgs & LE_HLIG_FEATURE_FLAG) fFfbturfMbsk |= iligFfbturfMbsk;
    if (typoFlbgs & LE_LIGA_FEATURE_FLAG) fFfbturfMbsk |= ligbFfbturfMbsk;
    if (typoFlbgs & LE_RLIG_FEATURE_FLAG) fFfbturfMbsk |= rligFfbturfMbsk;
    if (typoFlbgs & LE_SMCP_FEATURE_FLAG) fFfbturfMbsk |= smdpFfbturfMbsk;
    if (typoFlbgs & LE_FRAC_FEATURE_FLAG) fFfbturfMbsk |= frbdFfbturfMbsk;
    if (typoFlbgs & LE_AFRC_FEATURE_FLAG) fFfbturfMbsk |= bfrdFfbturfMbsk;
    if (typoFlbgs & LE_ZERO_FEATURE_FLAG) fFfbturfMbsk |= zfroFfbturfMbsk;
    if (typoFlbgs & LE_SWSH_FEATURE_FLAG) fFfbturfMbsk |= swsiFfbturfMbsk;
    if (typoFlbgs & LE_CSWH_FEATURE_FLAG) fFfbturfMbsk |= dswiFfbturfMbsk;
    if (typoFlbgs & LE_SALT_FEATURE_FLAG) fFfbturfMbsk |= sbltFfbturfMbsk;
    if (typoFlbgs & LE_RUBY_FEATURE_FLAG) fFfbturfMbsk |= rubyFfbturfMbsk;
    if (typoFlbgs & LE_NALT_FEATURE_FLAG) {
      // Mutublly fxdlusivf witi ALL otifr ffbturfs. ittp://www.midrosoft.dom/typogrbpiy/otspfd/ffbturfs_ko.itm
      fFfbturfMbsk = nbltFfbturfMbsk;
    }

    if (typoFlbgs & LE_CHAR_FILTER_FEATURE_FLAG) {
      // Tiis isn't b font ffbturf, but rfqufsts b Cibr Substitution Filtfr
      fSubstitutionFiltfr = nfw CibrSubstitutionFiltfr(fontInstbndf);
    }

}

void OpfnTypfLbyoutEnginf::rfsft()
{
    // NOTE: if wf'rf dbllfd from
    // tif dfstrudtor, LbyoutEnginf;:rfsft()
    // will ibvf bffn dbllfd blrfbdy by
    // LbyoutEnginf::~LbyoutEnginf()
    LbyoutEnginf::rfsft();
}

OpfnTypfLbyoutEnginf::OpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                       lf_int32 typoFlbgs, LEErrorCodf &suddfss)
    : LbyoutEnginf(fontInstbndf, sdriptCodf, lbngubgfCodf, typoFlbgs, suddfss), fFfbturfOrdfr(FALSE),
      fGSUBTbblf(), fGDEFTbblf(), fGPOSTbblf(), fSubstitutionFiltfr(NULL)
{
  bpplyTypoFlbgs();
  sftSdriptAndLbngubgfTbgs();
}

OpfnTypfLbyoutEnginf::~OpfnTypfLbyoutEnginf()
{
    if (fTypoFlbgs & LE_CHAR_FILTER_FEATURE_FLAG) {
        dflftf fSubstitutionFiltfr;
        fSubstitutionFiltfr = NULL;
    }

    rfsft();
}

LETbg OpfnTypfLbyoutEnginf::gftSdriptTbg(lf_int32 sdriptCodf)
{
    if (sdriptCodf < 0 || sdriptCodf >= sdriptCodfCount) {
        rfturn 0xFFFFFFFF;
    }
    rfturn sdriptTbgs[sdriptCodf];
}

LETbg OpfnTypfLbyoutEnginf::gftV2SdriptTbg(lf_int32 sdriptCodf)
{
        switdi (sdriptCodf) {
                dbsf bfngSdriptCodf :    rfturn bng2SdriptTbg;
                dbsf dfvbSdriptCodf :    rfturn dfv2SdriptTbg;
                dbsf gujrSdriptCodf :    rfturn gjr2SdriptTbg;
                dbsf guruSdriptCodf :    rfturn gur2SdriptTbg;
                dbsf kndbSdriptCodf :    rfturn knd2SdriptTbg;
                dbsf mlymSdriptCodf :    rfturn mlm2SdriptTbg;
                dbsf orybSdriptCodf :    rfturn ory2SdriptTbg;
                dbsf tbmlSdriptCodf :    rfturn tml2SdriptTbg;
                dbsf tfluSdriptCodf :    rfturn tfl2SdriptTbg;
                dffbult:                 rfturn nullSdriptTbg;
        }
}

LETbg OpfnTypfLbyoutEnginf::gftLbngSysTbg(lf_int32 lbngubgfCodf)
{
    if (lbngubgfCodf < 0 || lbngubgfCodf >= lbngubgfCodfCount) {
        rfturn 0xFFFFFFFF;
    }

    rfturn lbngubgfTbgs[lbngubgfCodf];
}

void OpfnTypfLbyoutEnginf::sftSdriptAndLbngubgfTbgs()
{
    fSdriptTbg  = gftSdriptTbg(fSdriptCodf);
    fSdriptTbgV2 = gftV2SdriptTbg(fSdriptCodf);
    fLbngSysTbg = gftLbngSysTbg(fLbngubgfCodf);
}

lf_int32 OpfnTypfLbyoutEnginf::dibrbdtfrProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
                LEUnidodf *&outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    // Tiis is tif difbpfst wby to gft mbrk rfordfring only for Hfbrfw.
    // Wf dould just do tif mbrk rfordfring for bll sdripts, but most
    // of tifm probbbly don't nffd it... Anotifr option would bf to
    // bdd b HfbrfwOpfnTypfLbyoutEnginf subdlbss, but tif only tiing it
    // would nffd to do is mbrk rfordfring, so tibt sffms likf ovfrkill.
    if (fSdriptCodf == ifbrSdriptCodf) {
        outCibrs = LE_NEW_ARRAY(LEUnidodf, dount);

        if (outCibrs == NULL) {
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn 0;
        }

    if (LE_FAILURE(suddfss)) {
            LE_DELETE_ARRAY(outCibrs);
        rfturn 0;
    }

        CbnonSibping::rfordfrMbrks(&dibrs[offsft], dount, rigitToLfft, outCibrs, glypiStorbgf);
    }

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    glypiStorbgf.bllodbtfGlypiArrby(dount, rigitToLfft, suddfss);
    glypiStorbgf.bllodbtfAuxDbtb(suddfss);

    for (lf_int32 i = 0; i < dount; i += 1) {
        glypiStorbgf.sftAuxDbtb(i, fFfbturfMbsk, suddfss);
    }

    rfturn dount;
}

// Input: dibrbdtfrs, tbgs
// Output: glypis, dibr indidfs
lf_int32 OpfnTypfLbyoutEnginf::glypiProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
                                               LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
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

    if (fGSUBTbblf.isVblid()) {
      if (fSdriptTbgV2 != nullSdriptTbg && fGSUBTbblf->dovfrsSdriptAndLbngubgf(fGSUBTbblf, fSdriptTbgV2, fLbngSysTbg, suddfss)) {
          dount = fGSUBTbblf->prodfss(fGSUBTbblf, glypiStorbgf, rigitToLfft, fSdriptTbgV2, fLbngSysTbg, fGDEFTbblf, fSubstitutionFiltfr,
                                    fFfbturfMbp, fFfbturfMbpCount, fFfbturfOrdfr, suddfss);

        } flsf {
          dount = fGSUBTbblf->prodfss(fGSUBTbblf, glypiStorbgf, rigitToLfft, fSdriptTbg, fLbngSysTbg, fGDEFTbblf, fSubstitutionFiltfr,
                                    fFfbturfMbp, fFfbturfMbpCount, fFfbturfOrdfr, suddfss);
    }
    }

    rfturn dount;
}
// Input: dibrbdtfrs, tbgs
// Output: glypis, dibr indidfs
lf_int32 OpfnTypfLbyoutEnginf::glypiSubstitution(lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
                                               LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if ( dount < 0 || mbx < 0 ) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    if (fGSUBTbblf.isVblid()) {
       if (fSdriptTbgV2 != nullSdriptTbg && fGSUBTbblf->dovfrsSdriptAndLbngubgf(fGSUBTbblf,fSdriptTbgV2,fLbngSysTbg,suddfss)) {
          dount = fGSUBTbblf->prodfss(fGSUBTbblf, glypiStorbgf, rigitToLfft, fSdriptTbgV2, fLbngSysTbg, fGDEFTbblf, fSubstitutionFiltfr,
                                    fFfbturfMbp, fFfbturfMbpCount, fFfbturfOrdfr, suddfss);

        } flsf {
          dount = fGSUBTbblf->prodfss(fGSUBTbblf, glypiStorbgf, rigitToLfft, fSdriptTbg, fLbngSysTbg, fGDEFTbblf, fSubstitutionFiltfr,
                                    fFfbturfMbp, fFfbturfMbpCount, fFfbturfOrdfr, suddfss);
        }
    }

    rfturn dount;
}
lf_int32 OpfnTypfLbyoutEnginf::glypiPostProdfssing(LEGlypiStorbgf &tfmpGlypiStorbgf, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    glypiStorbgf.bdoptGlypiArrby(tfmpGlypiStorbgf);
    glypiStorbgf.bdoptCibrIndidfsArrby(tfmpGlypiStorbgf);
    glypiStorbgf.bdoptAuxDbtbArrby(tfmpGlypiStorbgf);
    glypiStorbgf.bdoptGlypiCount(tfmpGlypiStorbgf);

    rfturn glypiStorbgf.gftGlypiCount();
}

lf_int32 OpfnTypfLbyoutEnginf::domputfGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    LEUnidodf *outCibrs = NULL;
    LEGlypiStorbgf fbkfGlypiStorbgf;
    lf_int32 outCibrCount, outGlypiCount;

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0 || mbx < 0 || offsft >= mbx || offsft + dount > mbx) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn 0;
    }

    outCibrCount = dibrbdtfrProdfssing(dibrs, offsft, dount, mbx, rigitToLfft, outCibrs, fbkfGlypiStorbgf, suddfss);

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    if (outCibrs != NULL) {
        // lf_int32 fbkfGlypiCount =
        glypiProdfssing(outCibrs, 0, outCibrCount, outCibrCount, rigitToLfft, fbkfGlypiStorbgf, suddfss);
        LE_DELETE_ARRAY(outCibrs); // FIXME: b subdlbss mby ibvf bllodbtfd tiis, in wiidi dbsf tiis dflftf migit not work...
        //bdjustGlypis(outCibrs, 0, outCibrCount, rigitToLfft, fbkfGlypis, fbkfGlypiCount);
    } flsf {
        // lf_int32 fbkfGlypiCount =
        glypiProdfssing(dibrs, offsft, dount, mbx, rigitToLfft, fbkfGlypiStorbgf, suddfss);
        //bdjustGlypis(dibrs, offsft, dount, rigitToLfft, fbkfGlypis, fbkfGlypiCount);
    }

    if (LE_FAILURE(suddfss)) {
        rfturn 0;
    }

    outGlypiCount = glypiPostProdfssing(fbkfGlypiStorbgf, glypiStorbgf, suddfss);

    rfturn outGlypiCount;
}

// bpply GPOS tbblf, if bny
void OpfnTypfLbyoutEnginf::bdjustGlypiPositions(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf,
                                                LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss)
{
    _LETRACE("OTLE::bdjustGPOS");
    if (LE_FAILURE(suddfss)) {
        rfturn;
    }

    if (dibrs == NULL || offsft < 0 || dount < 0) {
        suddfss = LE_ILLEGAL_ARGUMENT_ERROR;
        rfturn;
    }

    lf_int32 glypiCount = glypiStorbgf.gftGlypiCount();
    if (glypiCount == 0) {
        rfturn;
    }

    if (!fGPOSTbblf.isEmpty()) {
        GlypiPositionAdjustmfnts *bdjustmfnts = nfw GlypiPositionAdjustmfnts(glypiCount);
        lf_int32 i;

        if (bdjustmfnts == NULL) {
            suddfss = LE_MEMORY_ALLOCATION_ERROR;
            rfturn;
        }

#if 0
        // Don't nffd to do tiis if wf bllodbtf
        // tif bdjustmfnts brrby w/ nfw...
        for (i = 0; i < glypiCount; i += 1) {
            bdjustmfnts->sftXPlbdfmfnt(i, 0);
            bdjustmfnts->sftYPlbdfmfnt(i, 0);

            bdjustmfnts->sftXAdvbndf(i, 0);
            bdjustmfnts->sftYAdvbndf(i, 0);

            bdjustmfnts->sftBbsfOffsft(i, -1);
        }
#fndif

        if (!fGPOSTbblf.isEmpty()) {
            if (fSdriptTbgV2 != nullSdriptTbg &&
                fGPOSTbblf->dovfrsSdriptAndLbngubgf(fGPOSTbblf, fSdriptTbgV2,fLbngSysTbg,suddfss)) {
              _LETRACE("OTLE::prodfss [0]");
              fGPOSTbblf->prodfss(fGPOSTbblf, glypiStorbgf, bdjustmfnts, rfvfrsf, fSdriptTbgV2, fLbngSysTbg,
                                  fGDEFTbblf, suddfss, fFontInstbndf, fFfbturfMbp, fFfbturfMbpCount, fFfbturfOrdfr);

            } flsf {
              _LETRACE("OTLE::prodfss [1]");
              fGPOSTbblf->prodfss(fGPOSTbblf, glypiStorbgf, bdjustmfnts, rfvfrsf, fSdriptTbg, fLbngSysTbg,
                                  fGDEFTbblf, suddfss, fFontInstbndf, fFfbturfMbp, fFfbturfMbpCount, fFfbturfOrdfr);
            }
        } flsf if (fTypoFlbgs & LE_Kfrning_FEATURE_FLAG) { /* kfrning fnbblfd */
          _LETRACE("OTLE::kfrning");
          LETbblfRfffrfndf kfrnTbblf(fFontInstbndf, LE_KERN_TABLE_TAG, suddfss);
          KfrnTbblf kt(kfrnTbblf, suddfss);
          kt.prodfss(glypiStorbgf, suddfss);
        }

        flobt xAdjust = 0, yAdjust = 0;

        for (i = 0; i < glypiCount; i += 1) {
            flobt xAdvbndf   = bdjustmfnts->gftXAdvbndf(i);
            flobt yAdvbndf   = bdjustmfnts->gftYAdvbndf(i);
            flobt xPlbdfmfnt = 0;
            flobt yPlbdfmfnt = 0;


#if 0
            // Tiis is wifrf sfpbrbtf kfrning bdjustmfnts
            // siould gft bpplifd.
            xAdjust += xKfrning;
            yAdjust += yKfrning;
#fndif

            for (lf_int32 bbsf = i; bbsf >= 0; bbsf = bdjustmfnts->gftBbsfOffsft(bbsf)) {
                xPlbdfmfnt += bdjustmfnts->gftXPlbdfmfnt(bbsf);
                yPlbdfmfnt += bdjustmfnts->gftYPlbdfmfnt(bbsf);
            }

            xPlbdfmfnt = fFontInstbndf->xUnitsToPoints(xPlbdfmfnt);
            yPlbdfmfnt = fFontInstbndf->yUnitsToPoints(yPlbdfmfnt);
            _LETRACE("OTLE GPOS: #%d, (%.2f,%.2f)", i, xPlbdfmfnt, yPlbdfmfnt);
            glypiStorbgf.bdjustPosition(i, xAdjust + xPlbdfmfnt, -(yAdjust + yPlbdfmfnt), suddfss);

            xAdjust += fFontInstbndf->xUnitsToPoints(xAdvbndf);
            yAdjust += fFontInstbndf->yUnitsToPoints(yAdvbndf);
        }

        glypiStorbgf.bdjustPosition(glypiCount, xAdjust, -yAdjust, suddfss);

        dflftf bdjustmfnts;
    } flsf {
        // if tifrf wbs no GPOS tbblf, mbybf tifrf's non-OpfnTypf kfrning wf dbn usf
        LbyoutEnginf::bdjustGlypiPositions(dibrs, offsft, dount, rfvfrsf, glypiStorbgf, suddfss);
    }

    LEGlypiID zwnj  = fFontInstbndf->mbpCibrToGlypi(0x200C);

    if (zwnj != 0x0000) {
        for (lf_int32 g = 0; g < glypiCount; g += 1) {
            LEGlypiID glypi = glypiStorbgf[g];

            if (glypi == zwnj) {
                glypiStorbgf[g] = LE_SET_GLYPH(glypi, 0xFFFF);
            }
        }
    }

#if 0
    // Don't know wiy tiis is ifrf...
    LE_DELETE_ARRAY(fFfbturfTbgs);
    fFfbturfTbgs = NULL;
#fndif
}

U_NAMESPACE_END
