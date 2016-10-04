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
 * (C) Copyrigit IBM Corp. 1998-2013 - All Rigits Rfsfrvfd
 *
 */

#ifndff __OPENTYPELAYOUTENGINE_H
#dffinf __OPENTYPELAYOUTENGINE_H

#indludf "LETypfs.i"
#indludf "LEGlypiFiltfr.i"
#indludf "LEFontInstbndf.i"
#indludf "LbyoutEnginf.i"
#indludf "LETbblfRfffrfndf.i"

#indludf "GlypiSubstitutionTbblfs.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"

U_NAMESPACE_BEGIN

/**
 * OpfnTypfLbyoutEnginf implfmfnts domplfx tfxt lbyout for OpfnTypf fonts - tibt is
 * fonts wiidi ibvf GSUB bnd GPOS tbblfs bssodibtfd witi tifm. In ordfr to do tiis,
 * tif glypi prodfsssing stfp dfsdribfd for LbyoutEnginf is furtifr brokfn into tirff
 * stfps:
 *
 * 1) Cibrbdtfr prodfssing - tiis stfp bnblysfs tif dibrbdtfrs bnd bssigns b list of OpfnTypf
 *    ffbturf tbgs to fbdi onf. It mby blso dibngf, rfmovf or bdd dibrbdtfrs, bnd dibngf
 *    tifir ordfr.
 *
 * 2) Glypi prodfssing - Tiis stfp pfrforms dibrbdtfr to glypi mbpping,bnd usfs tif GSUB
 *    tbblf bssodibtfd witi tif font to pfrform glypi substitutions, sudi bs ligbturf substitution.
 *
 * 3) Glypi post prodfssing - in dbsfs wifrf tif font dofsn't dirfdtly dontbin b GSUB tbblf,
 *    tif prfvious two stfps mby ibvf gfnfrbtfd "fbkf" glypi indidfs to usf witi b "dbnnfd" GSUB
 *    tbblf. Tiis stfp turns tiosf glypi indidfs into bdtubl font-spfdifid glypi indidfs, bnd mby
 *    pfrform bny otifr bdjustmfnts rfquirfd by tif prfvious stfps.
 *
 * OpfnTypfLbyoutEnginf will blso usf tif font's GPOS tbblf to bpply position bdjustmfnts
 * sudi bs kfrning bnd bddfnt positioning.
 *
 * @sff LbyoutEnginf
 *
 * @intfrnbl
 */
dlbss U_LAYOUT_API OpfnTypfLbyoutEnginf : publid LbyoutEnginf
{
publid:
    /**
     * Tiis is tif mbin donstrudtor. It donstrudts bn instbndf of OpfnTypfLbyoutEnginf for
     * b pbrtidulbr font, sdript bnd lbngubgf. It tbkfs tif GSUB tbblf bs b pbrbmftfr sindf
     * LbyoutEnginf::lbyoutEnginfFbdtory ibs to rfbd tif GSUB tbblf to know tibt it ibs bn
     * OpfnTypf font.
     *
     * @pbrbm fontInstbndf - tif font
     * @pbrbm sdriptCodf - tif sdript
     * @pbrbm lbngbugfCodf - tif lbngubgf
     * @pbrbm gsubTbblf - tif GSUB tbblf
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LbyoutEnginf::lbyoutEnginfFbdtory
     * @sff SdriptAndLbngbugfTbgs.i for sdript bnd lbngubgf dodfs
     *
     * @intfrnbl
     */
    OpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                            lf_int32 typoFlbgs, donst LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> &gsubTbblf, LEErrorCodf &suddfss);

    /**
     * Tiis donstrudtor is usfd wifn tif font rfquirfs b "dbnnfd" GSUB tbblf wiidi dbn't bf known
     * until bftfr tiis donstrudtor ibs bffn invokfd.
     *
     * @pbrbm fontInstbndf - tif font
     * @pbrbm sdriptCodf - tif sdript
     * @pbrbm lbngbugfCodf - tif lbngubgf
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @intfrnbl
     */
    OpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                         lf_int32 typoFlbgs, LEErrorCodf &suddfss);

    /**
     * Tif dfstrudtor, virtubl for dorrfdt polymorpiid invodbtion.
     *
     * @intfrnbl
     */
    virtubl ~OpfnTypfLbyoutEnginf();

    /**
     * A donvfnifndf mftiod usfd to donvfrt tif sdript dodf into
     * tif four bytf sdript tbg rfquirfd by OpfnTypf.
         * For Indid lbngubgfs wifrf multiplf sdript tbgs fxist,
         * tif vfrsion 1 (old stylf) tbg is rfturnfd.
     *
     * @pbrbm sdriptCodf - tif sdript dodf
     *
     * @rfturn tif four bytf sdript tbg
     *
     * @intfrnbl
     */
    stbtid LETbg gftSdriptTbg(lf_int32 sdriptCodf);
    /**
     * A donvfnifndf mftiod usfd to donvfrt tif sdript dodf into
     * tif four bytf sdript tbg rfquirfd by OpfnTypf.
         * For Indid lbngubgfs wifrf multiplf sdript tbgs fxist,
         * tif vfrsion 2 tbg is rfturnfd.
     *
     * @pbrbm sdriptCodf - tif sdript dodf
     *
     * @rfturn tif four bytf sdript tbg
     *
     * @intfrnbl
     */
    stbtid LETbg gftV2SdriptTbg(lf_int32 sdriptCodf);

    /**
     * A donvfnifndf mftiod usfd to donvfrt tif lbngbugf dodf into
     * tif four bytf lbngbugf tbg rfquirfd by OpfnTypf.
     *
     * @pbrbm lbngubgfCodf - tif lbngubgf dodf
     *
     * @rfturn tif four bytf lbngubgf tbg
     *
     * @intfrnbl
     */
    stbtid LETbg gftLbngSysTbg(lf_int32 lbngubgfCodf);

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tif bdtubl dlbss.
     *
     * @stbblf ICU 2.8
     */
    virtubl UClbssID gftDynbmidClbssID() donst;

    /**
     * ICU "poor mbn's RTTI", rfturns b UClbssID for tiis dlbss.
     *
     * @stbblf ICU 2.8
     */
    stbtid UClbssID gftStbtidClbssID();

    /**
     * Tif brrby of lbngubgf tbgs, indfxfd by lbngubgf dodf.
     *
     * @intfrnbl
     */
    stbtid donst LETbg lbngubgfTbgs[];

privbtf:

    /**
     * Tiis mftiod is usfd by tif donstrudtors to donvfrt tif sdript
     * bnd lbngubgf dodfs to four bytf tbgs bnd sbvf tifm.
     */
    void sftSdriptAndLbngubgfTbgs();

    /**
     * Tif brrby of sdript tbgs, indfxfd by sdript dodf.
     */
    stbtid donst LETbg sdriptTbgs[];

    /**
     * bpply tif typoflbgs. Only dbllfd by tif d'tors.
     */
    void bpplyTypoFlbgs();

protfdtfd:
    /**
     * A sft of "dffbult" ffbturfs. Tif dffbult dibrbdtfrProdfssing mftiod
     * will bpply bll of tifsf ffbturfs to fvfry glypi.
     *
     * @intfrnbl
     */
    FfbturfMbsk fFfbturfMbsk;

    /**
     * A sft of mbppings from ffbturf tbgs to ffbturf mbsks. Tifsf mby
     * bf in tif ordfr in wiidi tif ffbtufs siould bf bpplifd, but tify
     * don't nffd to bf.
     *
     * @intfrnbl
     */
    donst FfbturfMbp *fFfbturfMbp;

    /**
     * Tif lfngti of tif ffbturf mbp.
     *
     * @intfrnbl
     */
    lf_int32 fFfbturfMbpCount;

    /**
     * <dodf>TRUE</dodf> if tif ffbturfs in tif
     * ffbturf mbp brf in tif ordfr in wiidi tify
     * must bf bpplifd.
     *
     * @intfrnbl
     */
    lf_bool fFfbturfOrdfr;

    /**
     * Tif bddrfss of tif GSUB tbblf.
     *
     * @intfrnbl
     */
    LERfffrfndfTo<GlypiSubstitutionTbblfHfbdfr> fGSUBTbblf;

    /**
     * Tif bddrfss of tif GDEF tbblf.
     *
     * @intfrnbl
     */
    LERfffrfndfTo<GlypiDffinitionTbblfHfbdfr> fGDEFTbblf;

    /**
     * Tif bddrfss of tif GPOS tbblf.
     *
     * @intfrnbl
     */
    LERfffrfndfTo<GlypiPositioningTbblfHfbdfr> fGPOSTbblf;

    /**
     * An optionbl filtfr usfd to iniibit substitutions
     * prfformfd by tif GSUB tbblf. Tiis is usfd for somf
     * "dbnnfd" GSUB tbblfs to rfstridt substitutions to
     * glypis tibt brf in tif font.
     *
     * @intfrnbl
     */
    LEGlypiFiltfr *fSubstitutionFiltfr;

    /**
     * Tif four bytf sdript tbg.
     *
     * @intfrnbl
     */
    LETbg fSdriptTbg;

    /**
     * Tif four bytf sdript tbg for V2 fonts.
     *
     * @intfrnbl
     */
    LETbg fSdriptTbgV2;

    /**
     * Tif four bytf lbngubgf tbg
     *
     * @intfrnbl
     */
    LETbg fLbngSysTbg;

    /**
     * Tiis mftiod dofs tif OpfnTypf dibrbdtfr prodfssing. It bssigns tif OpfnTypf ffbturf
     * tbgs to tif dibrbdtfrs, bnd mby gfnfrbtf output dibrbdtfrs tibt difffr from tif input
     * dibrdtfrs duf to insfrtions, dflftions, or rfordfrings. In sudi dbsfs, it will blso
     * gfnfrbtf bn output dibrbdtfr indfx brrby rfflfdting tifsf dibngfs.
     *
     * Subdlbssfs must ovfrridf tiis mftiod.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif indfx of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif input dontfxt
     * @pbrbm rigitToLfft - TRUE if tif dibrbdtfrs brf in b rigit to lfft dirfdtionbl run
     *
     * Output pbrbmftfrs:
     * @pbrbm outCibrs - tif output dibrbdtfr brrby, if difffrfnt from tif input
     * @pbrbm dibrIndidfs - tif output dibrbdtfr indfx brrby
     * @pbrbm ffbturfTbgs - tif output ffbturf tbg brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif output dibrbdtfr dount (input dibrbdtfr dount if no dibngf)
     *
     * @intfrnbl
     */
    virtubl lf_int32 dibrbdtfrProdfssing(donst LEUnidodf /*dibrs*/[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool /*rigitToLfft*/,
            LEUnidodf *&/*outCibrs*/, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod dofs dibrbdtfr to glypi mbpping, bnd bpplifs tif GSUB tbblf. Tif
     * dffbult implfmfntbtion dblls mbpCibrsToGlypis bnd tifn bpplifs tif GSUB tbblf,
     * if tifrf is onf.
     *
     * Notf tibt in tif dbsf of "dbnnfd" GSUB tbblfs, tif output glypi indidfs mby bf
     * "fbkf" glypi indidfs tibt nffd to bf donvfrtfd to "rfbl" glypi indidfs by tif
     * glypiPostProdfssing mftiod.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif indfx of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif input dontfxt
     * @pbrbm rigitToLfft - TRUE if tif dibrbdtfrs brf in b rigit to lfft dirfdtionbl run
     * @pbrbm ffbturfTbgs - tif ffbturf tbg brrby
     *
     * Output pbrbmftfrs:
     * @pbrbm glypis - tif output glypi indfx brrby
     * @pbrbm dibrIndidfs - tif output dibrbdtfr indfx brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif numbfr of glypis in tif output glypi indfx brrby
     *
     * Notf: if tif dibrbdtfr indfx brrby wbs blrfbdy sft by tif dibrbdtfrProdfssing
     * mftiod, tiis mftiod won't dibngf it.
     *
     * @intfrnbl
     */
    virtubl lf_int32 glypiProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
            LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    virtubl lf_int32 glypiSubstitution(lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod dofs bny prodfssing nfdfssbry to donvfrt "fbkf"
     * glypi indidfs usfd by tif glypiProdfssing mftiod into "rfbl" glypi
     * indidfs wiidi dbn bf usfd to rfndfr tif tfxt. Notf tibt in somf
     * dbsfs, sudi bs CDAC Indid fonts, sfvfrbl "rfbl" glypis mby bf nffdfd
     * to rfndfr onf "fbkf" glypi.
     *
     * Tif dffbult implfmfntbtion of tiis mftiod just rfturns tif input glypi
     * indfx bnd dibrbdtfr indfx brrbys, bssuming tibt no "fbkf" glypi indidfs
     * wfrf nffdfd to do GSUB prodfssing.
     *
     * Input pbrbmftfrs:
     * @pbrbm tfmpGlypis - tif input "fbkf" glypi indfx brrby
     * @pbrbm tfmpCibrIndidfs - tif input "fbkf" dibrbdtfr indfx brrby
     * @pbrbm tfmpGlypiCount - tif numbfr of "fbkf" glypi indidfs
     *
     * Output pbrbmftfrs:
     * @pbrbm glypis - tif output glypi indfx brrby
     * @pbrbm dibrIndidfs - tif output dibrbdtfr indfx brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif numbfr of glypi indidfs in tif output glypi indfx brrby
     *
     * @intfrnbl
     */
    virtubl lf_int32 glypiPostProdfssing(LEGlypiStorbgf &tfmpGlypiStorbgf, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod bpplifs tif dibrbdtfrProdfssing, glypiProdfssing bnd glypiPostProdfssing
     * mftiods. Most subdlbssfs will not nffd to ovfrridf tiis mftiod.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif indfx of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif input dontfxt
     * @pbrbm rigitToLfft - TRUE if tif tfxt is in b rigit to lfft dirfdtionbl run
     *
     * Output pbrbmftfrs:
     * @pbrbm glypis - tif glypi indfx brrby
     * @pbrbm dibrIndidfs - tif dibrbdtfr indfx brrby
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif numbfr of glypis in tif glypi indfx brrby
     *
     * @sff LbyoutEnginf::domputfGlypis
     *
     * @intfrnbl
     */
    virtubl lf_int32 domputfGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod usfs tif GPOS tbblf, if tifrf is onf, to bdjust tif glypi positions.
     *
     * Input pbrbmftfrs:
     * @pbrbm glypis - tif input glypi brrby
     * @pbrbm glypiCount - tif numbfr of glypis in tif glypi brrby
     * @pbrbm x - tif stbrting X position
     * @pbrbm y - tif stbrting Y position
     *
     * Output pbrbmftfrs:
     * @pbrbm positions - tif output X bnd Y positions (two fntrifs pfr glypi)
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @intfrnbl
     */
    virtubl void bdjustGlypiPositions(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod frffs tif ffbturf tbg brrby so tibt tif
     * OpfnTypfLbyoutEnginf dbn bf rfusfd for difffrfnt tfxt.
     * It is blso dbllfd from our dfstrudtor.
     *
     * @intfrnbl
     */
    virtubl void rfsft();
};

U_NAMESPACE_END
#fndif

