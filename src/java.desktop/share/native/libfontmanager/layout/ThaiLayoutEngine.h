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

#ifndff __THAILAYOUTENGINE_H
#dffinf __THAILAYOUTENGINE_H

#indludf "LETypfs.i"
#indludf "LEFontInstbndf.i"
#indludf "LbyoutEnginf.i"

#indludf "TibiSibping.i"

U_NAMESPACE_BEGIN

dlbss LEGlypiStorbgf;

/**
 * Tiis dlbss implfmfnts lbyout for tif Tibi sdript, using tif TibiSibpingClbss.
 * All fxisting Tibi fonts usf bn fndoding wiidi bssigns dibrbdtfr dodfs to bll
 * tif vbribnt forms nffdfd to displby bddfnts bnd tonf mbrks dorrfdtly in dontfxt.
 * Tiis dlbss dbn dfbl witi fonts using tif Midrosoft, Mbdintosi, bnd WorldTypf fndodings.
 *
 * @intfrnbl
 */
dlbss TibiLbyoutEnginf : publid LbyoutEnginf
{
publid:
    /**
     * Tiis donstrudts bn instbndf of TibiLbyoutEnginf for tif givfn font, sdript bnd
     * lbngubgf. It fxbminfs tif font, using LEFontInstbndf::dbnDisplby, to sft fGlypiSft
     * bnd fErrorCibr. (sff bflow)
     *
     * @pbrbm fontInstbndf - tif font
     * @pbrbm sdriptCodf - tif sdript
     * @pbrbm lbngubgfCodf - tif lbngubgf
     * @pbrbm suddfss - sft to bn frror dodf if tif opfrbtion fbils
     *
     * @sff LEFontInstbndf
     * @sff SdriptAndLbngubgfTbgs.i for sdript bnd lbngubgf dodfs
     *
     * @intfrnbl
     */
    TibiLbyoutEnginf(donst LEFontInstbndf *fontInstbndf, lf_int32 sdriptCodf, lf_int32 lbngubgfCodf, lf_int32 typoFlbgs, LEErrorCodf &suddfss);

    /**
     * Tif dfstrudtor, virtubl for dorrfdt polymorpiid invodbtion.
     *
     * @intfrnbl
     */
    virtubl ~TibiLbyoutEnginf();

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
     * A smbll intfgfr indidbting wiidi Tibi fndoding
     * tif font usfs.
     *
     * @sff TibiSibping
     *
     * @intfrnbl
     */
    lf_uint8 fGlypiSft;

    /**
     * Tif dibrbdtfr usfd bs b bbsf for vowfls bnd
     * tonf mbrks tibt brf out of sfqufndf. Usublly
     * tiis will bf Unidodf 0x25CC, if tif font dbn
     * displby it.
     *
     * @sff TibiSibping
     *
     * @intfrnbl
     */
    LEUnidodf fErrorCibr;

    /**
     * Tiis mftiod pfrforms Tibi lbyout. It dblls TibiSibping::domposf to
     * gfnfrbtf tif dorrfdt dontfxtubl dibrbdtfr dodfs, bnd tifn dblls
     * mbpCibrsToGlypis to gfnfrbtf tif glypi indidfs.
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
     * @sff TibiSibping
     *
     * @intfrnbl
     */
    virtubl lf_int32 domputfGlypis(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_int32 mbx, lf_bool rigitToLfft,
        LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

    /**
     * Tiis mftiod dofs positioning bdjustmfnts likf bddfnt positioning bnd
     * kfrning. Tif dffbult implfmfntbtion dofs notiing. Subdlbssfs nffding
     * position bdjustmfnts must ovfrridf tiis mftiod.
     *
     * Notf tibt tiis mftiod ibs boti dibrbdtfrs bnd glypis bs input so tibt
     * it dbn usf tif dibrbdtfr dodfs to dftfrminf glypi typfs if tibt informbtion
     * isn't dirfdtly bvbilbblf. (f.g. Somf Arbbid OpfnTypf fonts don't ibvf b GDEF
     * tbblf)
     *
     * @pbrbm dibrs - tif input dibrbdtfr dontfxt
     * @pbrbm offsft - tif offsft of tif first dibrbdtfr to prodfss
     * @pbrbm dount - tif numbfr of dibrbdtfrs to prodfss
     * @pbrbm rfvfrsf - <dodf>TRUE</dodf> if tif glypis in tif glypi brrby ibvf bffn rfordfrfd
     * @pbrbm glypiStorbgf - tif objfdt wiidi iolds tif pfr-glypi storbgf. Tif glypi positions will bf
     *                       bdjustfd bs nffdfd.
     * @pbrbm suddfss - output pbrbmftfr sft to bn frror dodf if tif opfrbtion fbils
     *
     * @intfrnbl
     */
    virtubl void bdjustGlypiPositions(donst LEUnidodf dibrs[], lf_int32 offsft, lf_int32 dount, lf_bool rfvfrsf, LEGlypiStorbgf &glypiStorbgf, LEErrorCodf &suddfss);

};

U_NAMESPACE_END
#fndif

