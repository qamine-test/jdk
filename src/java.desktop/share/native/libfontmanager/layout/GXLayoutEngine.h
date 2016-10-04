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

#ifndff __GXLAYOUTENGINE_H
#dffinf __GXLAYOUTENGINE_H

#indludf "LETypfs.i"
#indludf "LbyoutEnginf.i"

#indludf "MorpiTbblfs.i"

U_NAMESPACE_BEGIN

dlbss LEFontInstbndf;
dlbss LEGlypiStorbgf;

/**
 * Tiis dlbss implfmfnts lbyout for QuidkDrbw GX or Applf Advbndfd Typogrbypi (AAT)
 * fonts. A font is b GX or AAT font if it dontbins b 'mort' tbblf. Sff Applf's
 * TrufTypf Rfffrfndf Mbnubl (ittp://fonts.bpplf.dom/TTRffMbn/indfx.itml) for dftbils.
 * Informbtion bbout 'mort' tbblfs is in tif dibptfr titlfd "Font Filfs."
 *
 * @intfrnbl
 */
dlbss GXLbyoutEnginf : publid LbyoutEnginf
{
publid:
    /**
     * Tiis is tif mbin donstrudtor. It donstrudts bn instbndf of GXLbyoutEnginf for
     * b pbrtidulbr font, sdript bnd lbngubgf. It tbkfs tif 'mort' tbblf bs b pbrbmftfr sindf
     * LbyoutEnginf::lbyoutEnginfFbdtory ibs to rfbd tif 'mort' tbblf to know tibt it ibs b
     * GX font.
     *
     * Notf: GX bnd AAT fonts don't dontbin bny sdript bnd lbngubgf spfdifid tbblfs, so
     * tif sdript bnd lbngubgf brf ignorfd.
     *
     * @pbrbm fontInstbndf - tif font
     * @pbrbm sdriptCodf - tif sdript
     * @pbrbm lbngbugfCodf - tif lbngubgf
     * @pbrbm morpiTbblf - tif 'mort' tbblf
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LbyoutEnginf::lbyoutEnginfFbdtory
     * @sff SdriptAndLbngbugfTbgs.i for sdript bnd lbngubgf dodfs
     *
     * @intfrnbl
     */
    GXLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf, donst LERfffrfndfTo<MorpiTbblfHfbdfr> &morpiTbblf, LEErrorCodf &suddfss);

    /**
     * Tif dfstrudtor, virtubl for dorrfdt polymorpiid invodbtion.
     *
     * @intfrnbl
     */
    virtubl ~GXLbyoutEnginf();

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
     * Tif bddrfss of tif 'mort' tbblf
     *
     * @intfrnbl
     */
    LERfffrfndfTo<MorpiTbblfHfbdfr> fMorpiTbblf;

    /**
     * Tiis mftiod dofs GX lbyout using tif font's 'mort' tbblf. It donvfrts tif
     * input dibrbdtfr dodfs to glypi indidfs using mbpCibrsToGlypis, bnd tifn
     * bpplifs tif 'mort' tbblf.
     *
     * Input pbrbmftfrs:
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif indfx of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm mbx - tif numbfr of dibrbdtfrs in tif input dontfxt
     * @pbrbm rigitToLfft - <dodf>TRUE</dodf> if tif tfxt is in b rigit to lfft dirfdtionbl run
     * @pbrbm glypiStorbgf - tif glypi storbgf objfdt. Tif glypi bnd dibr indfx brrbys will bf sft.
     *
     * Output pbrbmftfrs:
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @rfturn tif numbfr of glypis in tif glypi indfx brrby
     *
     * @intfrnbl
     */
    virtubl lf_int32 domputfGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
        LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod bdjusts tif glypi positions using tif font's
     * 'kfrn', 'trbk', 'bsln', 'opbd' bnd 'just' tbblfs.
     *
     * Input pbrbmftfrs:
     * @pbrbm glypiStorbgf - tif objfdt iolding tif glypi storbgf. Tif positions will bf updbtfd bs nffdfd.
     *
     * Output pbrbmftfrs:
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @intfrnbl
     */
    virtubl void bdjustGlypiPositions(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf,
                                      LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

};

U_NAMESPACE_END
#fndif

