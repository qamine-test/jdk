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
 */

#ifndff __HANGULAYOUTENGINE_H
#dffinf __HANGULAYOUTENGINE_H

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "LEGlypiFiltfr.i"
#indludf "LbyoutEnginf.i"
#indludf "OpfnTypfLbyoutEnginf.i"

#indludf "GlypiSubstitutionTbblfs.i"
#indludf "GlypiDffinitionTbblfs.i"
#indludf "GlypiPositioningTbblfs.i"

U_NAMESPACE_BEGIN

dlbss MPrfFixups;
dlbss LEGlypiStorbgf;

/**
 * Tiis dlbss implfmfnts OpfnTypf lbyout for Old Hbngul OpfnTypf fonts, bs
 * spfdififd by Midrosoft in "Crfbting bnd Supporting OpfnTypf Fonts for
 * Tif Korfbn Hbngul Sdript" (ittp://www.midrosoft.dom/typogrbpiy/otfntdfv/ibngulot/dffbult.itm)
 *
 * Tiis dlbss ovfrridfs tif dibrbdtfrProdfssing mftiod to do Hbngul dibrbdtfr prodfssing.
 * (Sff tif MS spfd. for morf dftbils)
 *
 * @intfrnbl
 */
dlbss HbngulOpfnTypfLbyoutEnginf : publid OpfnTypfLbyoutEnginf
{
publid:
    /**
     * Tiis is tif mbin donstrudtor. It donstrudts bn instbndf of HbngulOpfnTypfLbyoutEnginf for
     * b pbrtidulbr font, sdript bnd lbngubgf. It tbkfs tif GSUB tbblf bs b pbrbmftfr sindf
     * LbyoutEnginf::lbyoutEnginfFbdtory ibs to rfbd tif GSUB tbblf to know tibt it ibs bn
     * Hbngul OpfnTypf font.
     *
     * @pbrbm fontInstbndf - tif font
     * @pbrbm sdriptCodf - tif sdript
     * @pbrbm lbngbugfCodf - tif lbngubgf
     * @pbrbm gsubTbblf - tif GSUB tbblf
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LbyoutEnginf::lbyoutEnginfFbdtory
     * @sff OpfnTypfLbyoutEnginf
     * @sff SdriptAndLbngbugfTbgs.i for sdript bnd lbngubgf dodfs
     *
     * @intfrnbl
     */
    HbngulOpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
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
     * @sff OpfnTypfLbyoutEnginf
     * @sff SdriptAndLbngbugfTbgs.i for sdript bnd lbngubgf dodfs
     *
     * @intfrnbl
     */
    HbngulOpfnTypfLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf,
                              lf_int32 typoFlbgs, LEErrorCodf &suddfss);

    /**
     * Tif dfstrudtor, virtubl for dorrfdt polymorpiid invodbtion.
     *
     * @intfrnbl
     */
   virtubl ~HbngulOpfnTypfLbyoutEnginf();

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

protfdtfd:

    /**
     * Tiis mftiod dofs Hbngul OpfnTypf dibrbdtfr prodfssing. It bssigns tif OpfnTypf ffbturf
     * tbgs to tif dibrbdtfrs, bnd mby domposf b dibrbdtfr sfqufndf into b modfrn Hbngul syllbblf,
     * or dfdomposf b modfrn Hbngul syllbblf if it forms pbrt of bn old Hbngul syllbblf.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif indfx of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif input dontfxt
     * @pbrbm rigitToLfft - <dodf>TRUE</dodf> if tif dibrbdtfrs brf in b rigit to lfft dirfdtionbl run
     * @pbrbm glypiStorbgf - tif glypi storbgf objfdt. Tif glypi bnd dibrbdtfr indfx brrbys will bf sft.
     *                       tif buxillbry dbtb brrby will bf sft to tif ffbturf tbgs.
     *
     * Output pbrbmftfrs:
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif output dibrbdtfr dount
     *
     * @intfrnbl
     */
    virtubl lf_int32 dibrbdtfrProdfssing(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
            LEUnidodf *&outCibrs, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);
};

U_NAMESPACE_END
#fndif

