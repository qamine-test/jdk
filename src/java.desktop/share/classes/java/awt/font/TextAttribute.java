/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 */

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996 - 1998, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;

import jbvb.io.InvblidObjfdtExdfption;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import sun.misd.SibrfdSfdrfts;

/**
 * Tif <dodf>TfxtAttributf</dodf> dlbss dffinfs bttributf kfys bnd
 * bttributf vblufs usfd for tfxt rfndfring.
 * <p>
 * <dodf>TfxtAttributf</dodf> instbndfs brf usfd bs bttributf kfys to
 * idfntify bttributfs in
 * {@link jbvb.bwt.Font Font},
 * {@link jbvb.bwt.font.TfxtLbyout TfxtLbyout},
 * {@link jbvb.tfxt.AttributfdCibrbdtfrItfrbtor AttributfdCibrbdtfrItfrbtor},
 * bnd otifr dlbssfs ibndling tfxt bttributfs. Otifr donstbnts dffinfd
 * in tiis dlbss dbn bf usfd bs bttributf vblufs.
 * <p>
 * For fbdi tfxt bttributf, tif dodumfntbtion providfs:
 * <UL>
 *   <LI>tif typf of its vbluf,
 *   <LI>tif rflfvbnt prfdffinfd donstbnts, if bny
 *   <LI>tif dffbult ffffdt if tif bttributf is bbsfnt
 *   <LI>tif vblid vblufs if tifrf brf limitbtions
 *   <LI>b dfsdription of tif ffffdt.
 * </UL>
 *
 * <H3>Vblufs</H3>
 * <UL>
 *   <LI>Tif vblufs of bttributfs must blwbys bf immutbblf.
 *   <LI>Wifrf vbluf limitbtions brf givfn, bny vbluf outsidf of tibt
 *   sft is rfsfrvfd for futurf usf; tif vbluf will bf trfbtfd bs
 *   tif dffbult.
 *   <LI>Tif vbluf <dodf>null</dodf> is trfbtfd tif sbmf bs tif
 *   dffbult vbluf bnd rfsults in tif dffbult bfibvior.
 *   <li>If tif vbluf is not of tif propfr typf, tif bttributf
 *   will bf ignorfd.
 *   <li>Tif idfntity of tif vbluf dofs not mbttfr, only tif bdtubl
 *   vbluf.  For fxbmplf, <dodf>TfxtAttributf.WEIGHT_BOLD</dodf> bnd
 *   <dodf>nfw Flobt(2.0)</dodf>
 *   indidbtf tif sbmf <dodf>WEIGHT</dodf>.
 *   <li>Attributf vblufs of typf <dodf>Numbfr</dodf> (usfd for
 *   <dodf>WEIGHT</dodf>, <dodf>WIDTH</dodf>, <dodf>POSTURE</dodf>,
 *   <dodf>SIZE</dodf>, <dodf>JUSTIFICATION</dodf>, bnd
 *   <dodf>TRACKING</dodf>) dbn vbry blong tifir nbturbl rbngf bnd brf
 *   not rfstridtfd to tif prfdffinfd donstbnts.
 *   <dodf>Numbfr.flobtVbluf()</dodf> is usfd to gft tif bdtubl vbluf
 *   from tif <dodf>Numbfr</dodf>.
 *   <li>Tif vblufs for <dodf>WEIGHT</dodf>, <dodf>WIDTH</dodf>, bnd
 *   <dodf>POSTURE</dodf> brf intfrpolbtfd by tif systfm, wiidi
 *   dbn sflfdt tif 'nfbrfst bvbilbblf' font or usf otifr tfdiniqufs to
 *   bpproximbtf tif usfr's rfqufst.
 *
 * </UL>
 *
 * <i4>Summbry of bttributfs</i4>
 * <tbblf stylf="flobt:dfntfr" bordfr="0" dfllspbding="0" dfllpbdding="2" widti="95%"
 *     summbry="Kfy, vbluf typf, prindipbl donstbnts, bnd dffbult vbluf
 *     bfibvior of bll TfxtAttributfs">
 * <tr stylf="bbdkground-dolor:#ddddff">
 * <ti vblign="TOP" blign="CENTER">Kfy</ti>
 * <ti vblign="TOP" blign="CENTER">Vbluf Typf</ti>
 * <ti vblign="TOP" blign="CENTER">Prindipbl Constbnts</ti>
 * <ti vblign="TOP" blign="CENTER">Dffbult Vbluf</ti>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #FAMILY}</td>
 * <td vblign="TOP">String</td>
 * <td vblign="TOP">Sff Font {@link jbvb.bwt.Font#DIALOG DIALOG},
 * {@link jbvb.bwt.Font#DIALOG_INPUT DIALOG_INPUT},<br> {@link jbvb.bwt.Font#SERIF SERIF},
 * {@link jbvb.bwt.Font#SANS_SERIF SANS_SERIF}, bnd {@link jbvb.bwt.Font#MONOSPACED MONOSPACED}.
 * </td>
 * <td vblign="TOP">"Dffbult" (usf plbtform dffbult)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #WEIGHT}</td>
 * <td vblign="TOP">Numbfr</td>
 * <td vblign="TOP">WEIGHT_REGULAR, WEIGHT_BOLD</td>
 * <td vblign="TOP">WEIGHT_REGULAR</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #WIDTH}</td>
 * <td vblign="TOP">Numbfr</td>
 * <td vblign="TOP">WIDTH_CONDENSED, WIDTH_REGULAR,<br>WIDTH_EXTENDED</td>
 * <td vblign="TOP">WIDTH_REGULAR</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #POSTURE}</td>
 * <td vblign="TOP">Numbfr</td>
 * <td vblign="TOP">POSTURE_REGULAR, POSTURE_OBLIQUE</td>
 * <td vblign="TOP">POSTURE_REGULAR</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #SIZE}</td>
 * <td vblign="TOP">Numbfr</td>
 * <td vblign="TOP">nonf</td>
 * <td vblign="TOP">12.0</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #TRANSFORM}</td>
 * <td vblign="TOP">{@link TrbnsformAttributf}</td>
 * <td vblign="TOP">Sff TrbnsformAttributf {@link TrbnsformAttributf#IDENTITY IDENTITY}</td>
 * <td vblign="TOP">TrbnsformAttributf.IDENTITY</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #SUPERSCRIPT}</td>
 * <td vblign="TOP">Intfgfr</td>
 * <td vblign="TOP">SUPERSCRIPT_SUPER, SUPERSCRIPT_SUB</td>
 * <td vblign="TOP">0 (usf tif stbndbrd glypis bnd mftrids)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #FONT}</td>
 * <td vblign="TOP">{@link jbvb.bwt.Font}</td>
 * <td vblign="TOP">nonf</td>
 * <td vblign="TOP">null (do not ovfrridf font rfsolution)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #CHAR_REPLACEMENT}</td>
 * <td vblign="TOP">{@link GrbpiidAttributf}</td>
 * <td vblign="TOP">nonf</td>
 * <td vblign="TOP">null (drbw tfxt using font glypis)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #FOREGROUND}</td>
 * <td vblign="TOP">{@link jbvb.bwt.Pbint}</td>
 * <td vblign="TOP">nonf</td>
 * <td vblign="TOP">null (usf durrfnt grbpiids pbint)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #BACKGROUND}</td>
 * <td vblign="TOP">{@link jbvb.bwt.Pbint}</td>
 * <td vblign="TOP">nonf</td>
 * <td vblign="TOP">null (do not rfndfr bbdkground)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #UNDERLINE}</td>
 * <td vblign="TOP">Intfgfr</td>
 * <td vblign="TOP">UNDERLINE_ON</td>
 * <td vblign="TOP">-1 (do not rfndfr undfrlinf)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #STRIKETHROUGH}</td>
 * <td vblign="TOP">Boolfbn</td>
 * <td vblign="TOP">STRIKETHROUGH_ON</td>
 * <td vblign="TOP">fblsf (do not rfndfr strikftirougi)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #RUN_DIRECTION}</td>
 * <td vblign="TOP">Boolfbn</td>
 * <td vblign="TOP">RUN_DIRECTION_LTR<br>RUN_DIRECTION_RTL</td>
 * <td vblign="TOP">null (usf {@link jbvb.tfxt.Bidi} stbndbrd dffbult)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #BIDI_EMBEDDING}</td>
 * <td vblign="TOP">Intfgfr</td>
 * <td vblign="TOP">nonf</td>
 * <td vblign="TOP">0 (usf bbsf linf dirfdtion)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #JUSTIFICATION}</td>
 * <td vblign="TOP">Numbfr</td>
 * <td vblign="TOP">JUSTIFICATION_FULL</td>
 * <td vblign="TOP">JUSTIFICATION_FULL</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #INPUT_METHOD_HIGHLIGHT}</td>
 * <td vblign="TOP">{@link jbvb.bwt.im.InputMftiodHigiligit},<br>{@link jbvb.tfxt.Annotbtion}</td>
 * <td vblign="TOP">(sff dlbss)</td>
 * <td vblign="TOP">null (do not bpply input iigiligiting)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #INPUT_METHOD_UNDERLINE}</td>
 * <td vblign="TOP">Intfgfr</td>
 * <td vblign="TOP">UNDERLINE_LOW_ONE_PIXEL,<br>UNDERLINE_LOW_TWO_PIXEL</td>
 * <td vblign="TOP">-1 (do not rfndfr undfrlinf)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #SWAP_COLORS}</td>
 * <td vblign="TOP">Boolfbn</td>
 * <td vblign="TOP">SWAP_COLORS_ON</td>
 * <td vblign="TOP">fblsf (do not swbp dolors)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #NUMERIC_SHAPING}</td>
 * <td vblign="TOP">{@link jbvb.bwt.font.NumfridSibpfr}</td>
 * <td vblign="TOP">nonf</td>
 * <td vblign="TOP">null (do not sibpf digits)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #KERNING}</td>
 * <td vblign="TOP">Intfgfr</td>
 * <td vblign="TOP">KERNING_ON</td>
 * <td vblign="TOP">0 (do not rfqufst kfrning)</td>
 * </tr>
 * <tr stylf="bbdkground-dolor:#ffffff">
 * <td vblign="TOP">{@link #LIGATURES}</td>
 * <td vblign="TOP">Intfgfr</td>
 * <td vblign="TOP">LIGATURES_ON</td>
 * <td vblign="TOP">0 (do not form optionbl ligbturfs)</td>
 * </tr>
 * <tr>
 * <td vblign="TOP">{@link #TRACKING}</td>
 * <td vblign="TOP">Numbfr</td>
 * <td vblign="TOP">TRACKING_LOOSE, TRACKING_TIGHT</td>
 * <td vblign="TOP">0 (do not bdd trbdking)</td>
 * </tr>
 * </tbblf>
 *
 * @sff jbvb.bwt.Font
 * @sff jbvb.bwt.font.TfxtLbyout
 * @sff jbvb.tfxt.AttributfdCibrbdtfrItfrbtor
 */
publid finbl dlbss TfxtAttributf fxtfnds Attributf {

    // tbblf of bll instbndfs in tiis dlbss, usfd by rfbdRfsolvf
    privbtf stbtid finbl Mbp<String, TfxtAttributf>
            instbndfMbp = nfw HbsiMbp<String, TfxtAttributf>(29);

    // For bddfss from jbvb.tfxt.Bidi
    stbtid {
        if (SibrfdSfdrfts.gftJbvbAWTFontAddfss() == null) {
            SibrfdSfdrfts.sftJbvbAWTFontAddfss(nfw JbvbAWTFontAddfssImpl());
        }
    }

    /**
     * Construdts b <dodf>TfxtAttributf</dodf> witi tif spfdififd nbmf.
     * @pbrbm nbmf tif bttributf nbmf to bssign to tiis
     * <dodf>TfxtAttributf</dodf>
     */
    protfdtfd TfxtAttributf(String nbmf) {
        supfr(nbmf);
        if (tiis.gftClbss() == TfxtAttributf.dlbss) {
            instbndfMbp.put(nbmf, tiis);
        }
    }

    /**
     * Rfsolvfs instbndfs bfing dfsfriblizfd to tif prfdffinfd donstbnts.
     */
    protfdtfd Objfdt rfbdRfsolvf() tirows InvblidObjfdtExdfption {
        if (tiis.gftClbss() != TfxtAttributf.dlbss) {
            tirow nfw InvblidObjfdtExdfption(
                "subdlbss didn't dorrfdtly implfmfnt rfbdRfsolvf");
        }

        TfxtAttributf instbndf = instbndfMbp.gft(gftNbmf());
        if (instbndf != null) {
            rfturn instbndf;
        } flsf {
            tirow nfw InvblidObjfdtExdfption("unknown bttributf nbmf");
        }
    }

    // Sfriblizbtion dompbtibility witi Jbvb 2 plbtform v1.2.
    // 1.2 will tirow bn InvblidObjfdtExdfption if fvfr bskfd to
    // dfsfriblizf INPUT_METHOD_UNDERLINE.
    // Tiis siouldn't ibppfn in rfbl liff.
    stbtid finbl long sfriblVfrsionUID = 7744112784117861702L;

    //
    // For usf witi Font.
    //

    /**
     * Attributf kfy for tif font nbmf.  Vblufs brf instbndfs of
     * <b><dodf>String</dodf></b>.  Tif dffbult vbluf is
     * <dodf>"Dffbult"</dodf>, wiidi dbusfs tif plbtform dffbult font
     * fbmily to bf usfd.
     *
     * <p> Tif <dodf>Font</dodf> dlbss dffinfs donstbnts for tif logidbl
     * font nbmfs
     * {@link jbvb.bwt.Font#DIALOG DIALOG},
     * {@link jbvb.bwt.Font#DIALOG_INPUT DIALOG_INPUT},
     * {@link jbvb.bwt.Font#SANS_SERIF SANS_SERIF},
     * {@link jbvb.bwt.Font#SERIF SERIF}, bnd
     * {@link jbvb.bwt.Font#MONOSPACED MONOSPACED}.
     *
     * <p>Tiis dffinfs tif vbluf pbssfd bs <dodf>nbmf</dodf> to tif
     * <dodf>Font</dodf> donstrudtor.  Boti logidbl bnd piysidbl
     * font nbmfs brf bllowfd. If b font witi tif rfqufstfd nbmf
     * is not found, tif dffbult font is usfd.
     *
     * <p><fm>Notf:</fm> Tiis bttributf is unfortunbtfly misnbmfd, bs
     * it spfdififs tif fbdf nbmf bnd not just tif fbmily.  Tius
     * vblufs sudi bs "Ludidb Sbns Bold" will sflfdt tibt fbdf if it
     * fxists.  Notf, tiougi, tibt if tif rfqufstfd fbdf dofs not
     * fxist, tif dffbult will bf usfd witi <fm>rfgulbr</fm> wfigit.
     * Tif "Bold" in tif nbmf is pbrt of tif fbdf nbmf, not b sfpbrbtf
     * rfqufst tibt tif font's wfigit bf bold.</p>
     */
    publid stbtid finbl TfxtAttributf FAMILY =
        nfw TfxtAttributf("fbmily");

    /**
     * Attributf kfy for tif wfigit of b font.  Vblufs brf instbndfs
     * of <b><dodf>Numbfr</dodf></b>.  Tif dffbult vbluf is
     * <dodf>WEIGHT_REGULAR</dodf>.
     *
     * <p>Sfvfrbl donstbnt vblufs brf providfd, sff {@link
     * #WEIGHT_EXTRA_LIGHT}, {@link #WEIGHT_LIGHT}, {@link
     * #WEIGHT_DEMILIGHT}, {@link #WEIGHT_REGULAR}, {@link
     * #WEIGHT_SEMIBOLD}, {@link #WEIGHT_MEDIUM}, {@link
     * #WEIGHT_DEMIBOLD}, {@link #WEIGHT_BOLD}, {@link #WEIGHT_HEAVY},
     * {@link #WEIGHT_EXTRABOLD}, bnd {@link #WEIGHT_ULTRABOLD}.  Tif
     * vbluf <dodf>WEIGHT_BOLD</dodf> dorrfsponds to tif
     * stylf vbluf <dodf>Font.BOLD</dodf> bs pbssfd to tif
     * <dodf>Font</dodf> donstrudtor.
     *
     * <p>Tif vbluf is rougily tif rbtio of tif stfm widti to tibt of
     * tif rfgulbr wfigit.
     *
     * <p>Tif systfm dbn intfrpolbtf tif providfd vbluf.
     */
    publid stbtid finbl TfxtAttributf WEIGHT =
        nfw TfxtAttributf("wfigit");

    /**
     * Tif ligitfst prfdffinfd wfigit.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_EXTRA_LIGHT =
        Flobt.vblufOf(0.5f);

    /**
     * Tif stbndbrd ligit wfigit.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_LIGHT =
        Flobt.vblufOf(0.75f);

    /**
     * An intfrmfdibtf wfigit bftwffn <dodf>WEIGHT_LIGHT</dodf> bnd
     * <dodf>WEIGHT_STANDARD</dodf>.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_DEMILIGHT =
        Flobt.vblufOf(0.875f);

    /**
     * Tif stbndbrd wfigit. Tiis is tif dffbult vbluf for <dodf>WEIGHT</dodf>.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_REGULAR =
        Flobt.vblufOf(1.0f);

    /**
     * A modfrbtfly ifbvifr wfigit tibn <dodf>WEIGHT_REGULAR</dodf>.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_SEMIBOLD =
        Flobt.vblufOf(1.25f);

    /**
     * An intfrmfdibtf wfigit bftwffn <dodf>WEIGHT_REGULAR</dodf> bnd
     * <dodf>WEIGHT_BOLD</dodf>.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_MEDIUM =
        Flobt.vblufOf(1.5f);

    /**
     * A modfrbtfly ligitfr wfigit tibn <dodf>WEIGHT_BOLD</dodf>.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_DEMIBOLD =
        Flobt.vblufOf(1.75f);

    /**
     * Tif stbndbrd bold wfigit.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_BOLD =
        Flobt.vblufOf(2.0f);

    /**
     * A modfrbtfly ifbvifr wfigit tibn <dodf>WEIGHT_BOLD</dodf>.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_HEAVY =
        Flobt.vblufOf(2.25f);

    /**
     * An fxtrb ifbvy wfigit.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_EXTRABOLD =
        Flobt.vblufOf(2.5f);

    /**
     * Tif ifbvifst prfdffinfd wfigit.
     * @sff #WEIGHT
     */
    publid stbtid finbl Flobt WEIGHT_ULTRABOLD =
        Flobt.vblufOf(2.75f);

    /**
     * Attributf kfy for tif widti of b font.  Vblufs brf instbndfs of
     * <b><dodf>Numbfr</dodf></b>.  Tif dffbult vbluf is
     * <dodf>WIDTH_REGULAR</dodf>.
     *
     * <p>Sfvfrbl donstbnt vblufs brf providfd, sff {@link
     * #WIDTH_CONDENSED}, {@link #WIDTH_SEMI_CONDENSED}, {@link
     * #WIDTH_REGULAR}, {@link #WIDTH_SEMI_EXTENDED}, {@link
     * #WIDTH_EXTENDED}.
     *
     * <p>Tif vbluf is rougily tif rbtio of tif bdvbndf widti to tibt
     * of tif rfgulbr widti.
     *
     * <p>Tif systfm dbn intfrpolbtf tif providfd vbluf.
     */
    publid stbtid finbl TfxtAttributf WIDTH =
        nfw TfxtAttributf("widti");

    /**
     * Tif most dondfnsfd prfdffinfd widti.
     * @sff #WIDTH
     */
    publid stbtid finbl Flobt WIDTH_CONDENSED =
        Flobt.vblufOf(0.75f);

    /**
     * A modfrbtfly dondfnsfd widti.
     * @sff #WIDTH
     */
    publid stbtid finbl Flobt WIDTH_SEMI_CONDENSED =
        Flobt.vblufOf(0.875f);

    /**
     * Tif stbndbrd widti. Tiis is tif dffbult vbluf for
     * <dodf>WIDTH</dodf>.
     * @sff #WIDTH
     */
    publid stbtid finbl Flobt WIDTH_REGULAR =
        Flobt.vblufOf(1.0f);

    /**
     * A modfrbtfly fxtfndfd widti.
     * @sff #WIDTH
     */
    publid stbtid finbl Flobt WIDTH_SEMI_EXTENDED =
        Flobt.vblufOf(1.25f);

    /**
     * Tif most fxtfndfd prfdffinfd widti.
     * @sff #WIDTH
     */
    publid stbtid finbl Flobt WIDTH_EXTENDED =
        Flobt.vblufOf(1.5f);

    /**
     * Attributf kfy for tif posturf of b font.  Vblufs brf instbndfs
     * of <b><dodf>Numbfr</dodf></b>. Tif dffbult vbluf is
     * <dodf>POSTURE_REGULAR</dodf>.
     *
     * <p>Two donstbnt vblufs brf providfd, {@link #POSTURE_REGULAR}
     * bnd {@link #POSTURE_OBLIQUE}. Tif vbluf
     * <dodf>POSTURE_OBLIQUE</dodf> dorrfsponds to tif stylf vbluf
     * <dodf>Font.ITALIC</dodf> bs pbssfd to tif <dodf>Font</dodf>
     * donstrudtor.
     *
     * <p>Tif vbluf is rougily tif slopf of tif stfms of tif font,
     * fxprfssfd bs tif run ovfr tif risf.  Positivf vblufs lfbn rigit.
     *
     * <p>Tif systfm dbn intfrpolbtf tif providfd vbluf.
     *
     * <p>Tiis will bfffdt tif font's itblid bnglf bs rfturnfd by
     * <dodf>Font.gftItblidAnglf</dodf>.
     *
     * @sff jbvb.bwt.Font#gftItblidAnglf()
     */
    publid stbtid finbl TfxtAttributf POSTURE =
        nfw TfxtAttributf("posturf");

    /**
     * Tif stbndbrd posturf, uprigit.  Tiis is tif dffbult vbluf for
     * <dodf>POSTURE</dodf>.
     * @sff #POSTURE
     */
    publid stbtid finbl Flobt POSTURE_REGULAR =
        Flobt.vblufOf(0.0f);

    /**
     * Tif stbndbrd itblid posturf.
     * @sff #POSTURE
     */
    publid stbtid finbl Flobt POSTURE_OBLIQUE =
        Flobt.vblufOf(0.20f);

    /**
     * Attributf kfy for tif font sizf.  Vblufs brf instbndfs of
     * <b><dodf>Numbfr</dodf></b>.  Tif dffbult vbluf is 12pt.
     *
     * <p>Tiis dorrfsponds to tif <dodf>sizf</dodf> pbrbmftfr to tif
     * <dodf>Font</dodf> donstrudtor.
     *
     * <p>Vfry lbrgf or smbll sizfs will impbdt rfndfring pfrformbndf,
     * bnd tif rfndfring systfm migit not rfndfr tfxt bt tifsf sizfs.
     * Nfgbtivf sizfs brf illfgbl bnd rfsult in tif dffbult sizf.
     *
     * <p>Notf tibt tif bppfbrbndf bnd mftrids of b 12pt font witi b
     * 2x trbnsform migit bf difffrfnt tibn tibt of b 24 point font
     * witi no trbnsform.
     */
    publid stbtid finbl TfxtAttributf SIZE =
        nfw TfxtAttributf("sizf");

    /**
     * Attributf kfy for tif trbnsform of b font.  Vblufs brf
     * instbndfs of <b><dodf>TrbnsformAttributf</dodf></b>.  Tif
     * dffbult vbluf is <dodf>TrbnsformAttributf.IDENTITY</dodf>.
     *
     * <p>Tif <dodf>TrbnsformAttributf</dodf> dlbss dffinfs tif
     * donstbnt {@link TrbnsformAttributf#IDENTITY IDENTITY}.
     *
     * <p>Tiis dorrfsponds to tif trbnsform pbssfd to
     * <dodf>Font.dfrivfFont(AffinfTrbnsform)</dodf>.  Sindf tibt
     * trbnsform is mutbblf bnd <dodf>TfxtAttributf</dodf> vblufs must
     * not bf, tif <dodf>TrbnsformAttributf</dodf> wrbppfr dlbss is
     * usfd.
     *
     * <p>Tif primbry intfnt is to support sdbling bnd skfwing, tiougi
     * otifr ffffdts brf possiblf.</p>
     *
     * <p>Somf trbnsforms will dbusf tif bbsflinf to bf rotbtfd bnd/or
     * siiftfd.  Tif tfxt bnd tif bbsflinf brf trbnsformfd togftifr so
     * tibt tif tfxt follows tif nfw bbsflinf.  For fxbmplf, witi tfxt
     * on b iorizontbl bbsflinf, tif nfw bbsflinf follows tif
     * dirfdtion of tif unit x vfdtor pbssfd tirougi tif
     * trbnsform. Tfxt mftrids brf mfbsurfd bgbinst tiis nfw bbsflinf.
     * So, for fxbmplf, witi otifr tiings bfing fqubl, tfxt rfndfrfd
     * witi b rotbtfd TRANSFORM bnd bn unrotbtfd TRANSFORM will mfbsurf bs
     * ibving tif sbmf bsdfnt, dfsdfnt, bnd bdvbndf.</p>
     *
     * <p>In stylfd tfxt, tif bbsflinfs for fbdi sudi run brf blignfd
     * onf bftfr tif otifr to potfntiblly drfbtf b non-linfbr bbsflinf
     * for tif fntirf run of tfxt. For morf informbtion, sff {@link
     * TfxtLbyout#gftLbyoutPbti}.</p>
     *
     * @sff TrbnsformAttributf
     * @sff jbvb.bwt.gfom.AffinfTrbnsform
     */
     publid stbtid finbl TfxtAttributf TRANSFORM =
        nfw TfxtAttributf("trbnsform");

    /**
     * Attributf kfy for supfrsdripting bnd subsdripting.  Vblufs brf
     * instbndfs of <b><dodf>Intfgfr</dodf></b>.  Tif dffbult vbluf is
     * 0, wiidi mfbns tibt no supfrsdript or subsdript is usfd.
     *
     * <p>Two donstbnt vblufs brf providfd, sff {@link
     * #SUPERSCRIPT_SUPER} bnd {@link #SUPERSCRIPT_SUB}.  Tifsf ibvf
     * tif vblufs 1 bnd -1 rfspfdtivfly.  Vblufs of
     * grfbtfr mbgnitudf dffinf grfbtfr lfvfls of supfrsdript or
     * subsdripting, for fxbmplf, 2 dorrfsponds to supfr-supfrsdript,
     * 3 to supfr-supfr-supfrsdript, bnd similbrly for nfgbtivf vblufs
     * bnd subsdript, up to b lfvfl of 7 (or -7).  Vblufs bfyond tiis
     * rbngf brf rfsfrvfd; bfibvior is plbtform-dfpfndfnt.
     *
     * <p><dodf>SUPERSCRIPT</dodf> dbn
     * impbdt tif bsdfnt bnd dfsdfnt of b font.  Tif bsdfnt
     * bnd dfsdfnt dbn nfvfr bfdomf nfgbtivf, iowfvfr.
     */
    publid stbtid finbl TfxtAttributf SUPERSCRIPT =
        nfw TfxtAttributf("supfrsdript");

    /**
     * Stbndbrd supfrsdript.
     * @sff #SUPERSCRIPT
     */
    publid stbtid finbl Intfgfr SUPERSCRIPT_SUPER =
        Intfgfr.vblufOf(1);

    /**
     * Stbndbrd subsdript.
     * @sff #SUPERSCRIPT
     */
    publid stbtid finbl Intfgfr SUPERSCRIPT_SUB =
        Intfgfr.vblufOf(-1);

    /**
     * Attributf kfy usfd to providf tif font to usf to rfndfr tfxt.
     * Vblufs brf instbndfs of {@link jbvb.bwt.Font}.  Tif dffbult
     * vbluf is null, indidbting tibt normbl rfsolution of b
     * <dodf>Font</dodf> from bttributfs siould bf pfrformfd.
     *
     * <p><dodf>TfxtLbyout</dodf> bnd
     * <dodf>AttributfdCibrbdtfrItfrbtor</dodf> work in tfrms of
     * <dodf>Mbps</dodf> of <dodf>TfxtAttributfs</dodf>.  Normblly,
     * bll tif bttributfs brf fxbminfd bnd usfd to sflfdt bnd
     * donfigurf b <dodf>Font</dodf> instbndf.  If b <dodf>FONT</dodf>
     * bttributf is prfsfnt, tiougi, its bssodibtfd <dodf>Font</dodf>
     * will bf usfd.  Tiis providfs b wby for usfrs to ovfrridf tif
     * rfsolution of font bttributfs into b <dodf>Font</dodf>, or
     * fordf usf of b pbrtidulbr <dodf>Font</dodf> instbndf.  Tiis
     * blso bllows usfrs to spfdify subdlbssfs of <dodf>Font</dodf> in
     * dbsfs wifrf b <dodf>Font</dodf> dbn bf subdlbssfd.
     *
     * <p><dodf>FONT</dodf> is usfd for spfdibl situbtions wifrf
     * dlifnts blrfbdy ibvf b <dodf>Font</dodf> instbndf but still
     * nffd to usf <dodf>Mbp</dodf>-bbsfd APIs.  Typidblly, tifrf will
     * bf no otifr bttributfs in tif <dodf>Mbp</dodf> fxdfpt tif
     * <dodf>FONT</dodf> bttributf.  Witi <dodf>Mbp</dodf>-bbsfd APIs
     * tif dommon dbsf is to spfdify bll bttributfs individublly, so
     * <dodf>FONT</dodf> is not nffdfd or dfsirfbblf.
     *
     * <p>Howfvfr, if boti <dodf>FONT</dodf> bnd otifr bttributfs brf
     * prfsfnt in tif <dodf>Mbp</dodf>, tif rfndfring systfm will
     * mfrgf tif bttributfs dffinfd in tif <dodf>Font</dodf> witi tif
     * bdditionbl bttributfs.  Tiis mfrging prodfss dlbssififs
     * <dodf>TfxtAttributfs</dodf> into two groups.  Onf group, tif
     * 'primbry' group, is donsidfrfd fundbmfntbl to tif sflfdtion bnd
     * mftrid bfibvior of b font.  Tifsf bttributfs brf
     * <dodf>FAMILY</dodf>, <dodf>WEIGHT</dodf>, <dodf>WIDTH</dodf>,
     * <dodf>POSTURE</dodf>, <dodf>SIZE</dodf>,
     * <dodf>TRANSFORM</dodf>, <dodf>SUPERSCRIPT</dodf>, bnd
     * <dodf>TRACKING</dodf>. Tif otifr group, tif 'sfdondbry' group,
     * donsists of bll otifr dffinfd bttributfs, witi tif fxdfption of
     * <dodf>FONT</dodf> itsflf.
     *
     * <p>To gfnfrbtf tif nfw <dodf>Mbp</dodf>, first tif
     * <dodf>Font</dodf> is obtbinfd from tif <dodf>FONT</dodf>
     * bttributf, bnd <fm>bll</fm> of its bttributfs fxtrbdtfd into b
     * nfw <dodf>Mbp</dodf>.  Tifn only tif <fm>sfdondbry</fm>
     * bttributfs from tif originbl <dodf>Mbp</dodf> brf bddfd to
     * tiosf in tif nfw <dodf>Mbp</dodf>.  Tius tif vblufs of primbry
     * bttributfs domf solfly from tif <dodf>Font</dodf>, bnd tif
     * vblufs of sfdondbry bttributfs originbtf witi tif
     * <dodf>Font</dodf> but dbn bf ovfrriddfn by otifr vblufs in tif
     * <dodf>Mbp</dodf>.
     *
     * <p><fm>Notf:</fm><dodf>Font's</dodf> <dodf>Mbp</dodf>-bbsfd
     * donstrudtor bnd <dodf>dfrivfFont</dodf> mftiods do not prodfss
     * tif <dodf>FONT</dodf> bttributf, bs tifsf brf usfd to drfbtf
     * nfw <dodf>Font</dodf> objfdts.  Instfbd, {@link
     * jbvb.bwt.Font#gftFont(Mbp) Font.gftFont(Mbp)} siould bf usfd to
     * ibndlf tif <dodf>FONT</dodf> bttributf.
     *
     * @sff jbvb.bwt.Font
     */
    publid stbtid finbl TfxtAttributf FONT =
        nfw TfxtAttributf("font");

    /**
     * Attributf kfy for b usfr-dffinfd glypi to displby in lifu
     * of tif font's stbndbrd glypi for b dibrbdtfr.  Vblufs brf
     * intbndfs of GrbpiidAttributf.  Tif dffbult vbluf is null,
     * indidbting tibt tif stbndbrd glypis providfd by tif font
     * siould bf usfd.
     *
     * <p>Tiis bttributf is usfd to rfsfrvf spbdf for b grbpiid or
     * otifr domponfnt fmbfddfd in b linf of tfxt.  It is rfquirfd for
     * dorrfdt positioning of 'inlinf' domponfnts witiin b linf wifn
     * bidirfdtionbl rfordfring (sff {@link jbvb.tfxt.Bidi}) is
     * pfrformfd.  Ebdi dibrbdtfr (Unidodf dodf point) will bf
     * rfndfrfd using tif providfd GrbpiidAttributf. Typidblly, tif
     * dibrbdtfrs to wiidi tiis bttributf is bpplifd siould bf
     * <dodf>&#92;uFFFC</dodf>.
     *
     * <p>Tif GrbpiidAttributf dftfrminfs tif logidbl bnd visubl
     * bounds of tif tfxt; tif bdtubl Font vblufs brf ignorfd.
     *
     * @sff GrbpiidAttributf
     */
    publid stbtid finbl TfxtAttributf CHAR_REPLACEMENT =
        nfw TfxtAttributf("dibr_rfplbdfmfnt");

    //
    // Adornmfnts bddfd to tfxt.
    //

    /**
     * Attributf kfy for tif pbint usfd to rfndfr tif tfxt.  Vblufs brf
     * instbndfs of <b><dodf>Pbint</dodf></b>.  Tif dffbult vbluf is
     * null, indidbting tibt tif <dodf>Pbint</dodf> sft on tif
     * <dodf>Grbpiids2D</dodf> bt tif timf of rfndfring is usfd.
     *
     * <p>Glypis will bf rfndfrfd using tiis
     * <dodf>Pbint</dodf> rfgbrdlfss of tif <dodf>Pbint</dodf> vbluf
     * sft on tif <dodf>Grbpiids</dodf> (but sff {@link #SWAP_COLORS}).
     *
     * @sff jbvb.bwt.Pbint
     * @sff #SWAP_COLORS
     */
    publid stbtid finbl TfxtAttributf FOREGROUND =
        nfw TfxtAttributf("forfground");

    /**
     * Attributf kfy for tif pbint usfd to rfndfr tif bbdkground of
     * tif tfxt.  Vblufs brf instbndfs of <b><dodf>Pbint</dodf></b>.
     * Tif dffbult vbluf is null, indidbting tibt tif bbdkground
     * siould not bf rfndfrfd.
     *
     * <p>Tif logidbl bounds of tif tfxt will bf fillfd using tiis
     * <dodf>Pbint</dodf>, bnd tifn tif tfxt will bf rfndfrfd on top
     * of it (but sff {@link #SWAP_COLORS}).
     *
     * <p>Tif visubl bounds of tif tfxt is fxtfndfd to indludf tif
     * logidbl bounds, if nfdfssbry.  Tif outlinf is not bfffdtfd.
     *
     * @sff jbvb.bwt.Pbint
     * @sff #SWAP_COLORS
     */
    publid stbtid finbl TfxtAttributf BACKGROUND =
        nfw TfxtAttributf("bbdkground");

    /**
     * Attributf kfy for undfrlinf.  Vblufs brf instbndfs of
     * <b><dodf>Intfgfr</dodf></b>.  Tif dffbult vbluf is -1, wiidi
     * mfbns no undfrlinf.
     *
     * <p>Tif donstbnt vbluf {@link #UNDERLINE_ON} is providfd.
     *
     * <p>Tif undfrlinf bfffdts boti tif visubl bounds bnd tif outlinf
     * of tif tfxt.
     */
    publid stbtid finbl TfxtAttributf UNDERLINE =
        nfw TfxtAttributf("undfrlinf");

    /**
     * Stbndbrd undfrlinf.
     *
     * @sff #UNDERLINE
     */
    publid stbtid finbl Intfgfr UNDERLINE_ON =
        Intfgfr.vblufOf(0);

    /**
     * Attributf kfy for strikftirougi.  Vblufs brf instbndfs of
     * <b><dodf>Boolfbn</dodf></b>.  Tif dffbult vbluf is
     * <dodf>fblsf</dodf>, wiidi mfbns no strikftirougi.
     *
     * <p>Tif donstbnt vbluf {@link #STRIKETHROUGH_ON} is providfd.
     *
     * <p>Tif strikftirougi bfffdts boti tif visubl bounds bnd tif
     * outlinf of tif tfxt.
     */
    publid stbtid finbl TfxtAttributf STRIKETHROUGH =
        nfw TfxtAttributf("strikftirougi");

    /**
     * A singlf strikftirougi.
     *
     * @sff #STRIKETHROUGH
     */
    publid stbtid finbl Boolfbn STRIKETHROUGH_ON =
        Boolfbn.TRUE;

    //
    // Attributfs usf to dontrol lbyout of tfxt on b linf.
    //

    /**
     * Attributf kfy for tif run dirfdtion of tif linf.  Vblufs brf
     * instbndfs of <b><dodf>Boolfbn</dodf></b>.  Tif dffbult vbluf is
     * null, wiidi indidbtfs tibt tif stbndbrd Bidi blgoritim for
     * dftfrmining run dirfdtion siould bf usfd witi tif vbluf {@link
     * jbvb.tfxt.Bidi#DIRECTION_DEFAULT_LEFT_TO_RIGHT}.
     *
     * <p>Tif donstbnts {@link #RUN_DIRECTION_RTL} bnd {@link
     * #RUN_DIRECTION_LTR} brf providfd.
     *
     * <p>Tiis dftfrminfs tif vbluf pbssfd to tif {@link
     * jbvb.tfxt.Bidi} donstrudtor to sflfdt tif primbry dirfdtion of
     * tif tfxt in tif pbrbgrbpi.
     *
     * <p><fm>Notf:</fm> Tiis bttributf siould ibvf tif sbmf vbluf for
     * bll tif tfxt in b pbrbgrbpi, otifrwisf tif bfibvior is
     * undftfrminfd.
     *
     * @sff jbvb.tfxt.Bidi
     */
    publid stbtid finbl TfxtAttributf RUN_DIRECTION =
        nfw TfxtAttributf("run_dirfdtion");

    /**
     * Lfft-to-rigit run dirfdtion.
     * @sff #RUN_DIRECTION
     */
    publid stbtid finbl Boolfbn RUN_DIRECTION_LTR =
        Boolfbn.FALSE;

    /**
     * Rigit-to-lfft run dirfdtion.
     * @sff #RUN_DIRECTION
     */
    publid stbtid finbl Boolfbn RUN_DIRECTION_RTL =
        Boolfbn.TRUE;

    /**
     * Attributf kfy for tif fmbfdding lfvfl of tif tfxt.  Vblufs brf
     * instbndfs of <b><dodf>Intfgfr</dodf></b>.  Tif dffbult vbluf is
     * <dodf>null</dodf>, indidbting tibt tif tif Bidirfdtionbl
     * blgoritim siould run witiout fxplidit fmbfddings.
     *
     * <p>Positivf vblufs 1 tirougi 61 brf <fm>fmbfdding</fm> lfvfls,
     * nfgbtivf vblufs -1 tirougi -61 brf <fm>ovfrridf</fm> lfvfls.
     * Tif vbluf 0 mfbns tibt tif bbsf linf dirfdtion is usfd.  Tifsf
     * lfvfls brf pbssfd in tif fmbfdding lfvfls brrby to tif {@link
     * jbvb.tfxt.Bidi} donstrudtor.
     *
     * <p><fm>Notf:</fm> Wifn tiis bttributf is prfsfnt bnywifrf in
     * b pbrbgrbpi, tifn bny Unidodf bidi dontrol dibrbdtfrs (RLO,
     * LRO, RLE, LRE, bnd PDF) in tif pbrbgrbpi brf
     * disrfgbrdfd, bnd runs of tfxt wifrf tiis bttributf is not
     * prfsfnt brf trfbtfd bs tiougi it wfrf prfsfnt bnd ibd tif vbluf
     * 0.
     *
     * @sff jbvb.tfxt.Bidi
     */
    publid stbtid finbl TfxtAttributf BIDI_EMBEDDING =
        nfw TfxtAttributf("bidi_fmbfdding");

    /**
     * Attributf kfy for tif justifidbtion of b pbrbgrbpi.  Vblufs brf
     * instbndfs of <b><dodf>Numbfr</dodf></b>.  Tif dffbult vbluf is
     * 1, indidbting tibt justifidbtion siould usf tif full widti
     * providfd.  Vblufs brf pinnfd to tif rbngf [0..1].
     *
     * <p>Tif donstbnts {@link #JUSTIFICATION_FULL} bnd {@link
     * #JUSTIFICATION_NONE} brf providfd.
     *
     * <p>Spfdififs tif frbdtion of tif fxtrb spbdf to usf wifn
     * justifidbtion is rfqufstfd on b <dodf>TfxtLbyout</dodf>. For
     * fxbmplf, if tif linf is 50 points widf bnd it is rfqufstfd to
     * justify to 70 points, b vbluf of 0.75 will pbd to usf
     * tirff-qubrtfrs of tif rfmbining spbdf, or 15 points, so tibt
     * tif rfsulting linf will bf 65 points in lfngti.
     *
     * <p><fm>Notf:</fm> Tiis siould ibvf tif sbmf vbluf for bll tif
     * tfxt in b pbrbgrbpi, otifrwisf tif bfibvior is undftfrminfd.
     *
     * @sff TfxtLbyout#gftJustififdLbyout
     */
    publid stbtid finbl TfxtAttributf JUSTIFICATION =
        nfw TfxtAttributf("justifidbtion");

    /**
     * Justify tif linf to tif full rfqufstfd widti.  Tiis is tif
     * dffbult vbluf for <dodf>JUSTIFICATION</dodf>.
     * @sff #JUSTIFICATION
     */
    publid stbtid finbl Flobt JUSTIFICATION_FULL =
        Flobt.vblufOf(1.0f);

    /**
     * Do not bllow tif linf to bf justififd.
     * @sff #JUSTIFICATION
     */
    publid stbtid finbl Flobt JUSTIFICATION_NONE =
        Flobt.vblufOf(0.0f);

    //
    // For usf by input mftiod.
    //

    /**
     * Attributf kfy for input mftiod iigiligit stylfs.
     *
     * <p>Vblufs brf instbndfs of {@link
     * jbvb.bwt.im.InputMftiodHigiligit} or {@link
     * jbvb.tfxt.Annotbtion}.  Tif dffbult vbluf is <dodf>null</dodf>,
     * wiidi mfbns tibt input mftiod stylfs siould not bf bpplifd
     * bfforf rfndfring.
     *
     * <p>If bdjbdfnt runs of tfxt witi tif sbmf
     * <dodf>InputMftiodHigiligit</dodf> nffd to bf rfndfrfd
     * sfpbrbtfly, tif <dodf>InputMftiodHigiligits</dodf> siould bf
     * wrbppfd in <dodf>Annotbtion</dodf> instbndfs.
     *
     * <p>Input mftiod iigiligits brf usfd wiilf tfxt is bfing
     * domposfd by bn input mftiod. Tfxt fditing domponfnts siould
     * rftbin tifm fvfn if tify gfnfrblly only dfbl witi unstylfd
     * tfxt, bnd mbkf tifm bvbilbblf to tif drbwing routinfs.
     *
     * @sff jbvb.bwt.Font
     * @sff jbvb.bwt.im.InputMftiodHigiligit
     * @sff jbvb.tfxt.Annotbtion
     */
    publid stbtid finbl TfxtAttributf INPUT_METHOD_HIGHLIGHT =
        nfw TfxtAttributf("input mftiod iigiligit");

    /**
     * Attributf kfy for input mftiod undfrlinfs.  Vblufs
     * brf instbndfs of <b><dodf>Intfgfr</dodf></b>.  Tif dffbult
     * vbluf is <dodf>-1</dodf>, wiidi mfbns no undfrlinf.
     *
     * <p>Sfvfrbl donstbnt vblufs brf providfd, sff {@link
     * #UNDERLINE_LOW_ONE_PIXEL}, {@link #UNDERLINE_LOW_TWO_PIXEL},
     * {@link #UNDERLINE_LOW_DOTTED}, {@link #UNDERLINE_LOW_GRAY}, bnd
     * {@link #UNDERLINE_LOW_DASHED}.
     *
     * <p>Tiis mby bf usfd in donjundtion witi {@link #UNDERLINE} if
     * dfsirfd.  Tif primbry purposf is for usf by input mftiods.
     * Otifr usf of tifsf undfrlinfs for simplf ornbmfntbtion migit
     * donfusf usfrs.
     *
     * <p>Tif input mftiod undfrlinf bfffdts boti tif visubl bounds bnd
     * tif outlinf of tif tfxt.
     *
     * @sindf 1.3
     */
    publid stbtid finbl TfxtAttributf INPUT_METHOD_UNDERLINE =
        nfw TfxtAttributf("input mftiod undfrlinf");

    /**
     * Singlf pixfl solid low undfrlinf.
     * @sff #INPUT_METHOD_UNDERLINE
     * @sindf 1.3
     */
    publid stbtid finbl Intfgfr UNDERLINE_LOW_ONE_PIXEL =
        Intfgfr.vblufOf(1);

    /**
     * Doublf pixfl solid low undfrlinf.
     * @sff #INPUT_METHOD_UNDERLINE
     * @sindf 1.3
     */
    publid stbtid finbl Intfgfr UNDERLINE_LOW_TWO_PIXEL =
        Intfgfr.vblufOf(2);

    /**
     * Singlf pixfl dottfd low undfrlinf.
     * @sff #INPUT_METHOD_UNDERLINE
     * @sindf 1.3
     */
    publid stbtid finbl Intfgfr UNDERLINE_LOW_DOTTED =
        Intfgfr.vblufOf(3);

    /**
     * Doublf pixfl grby low undfrlinf.
     * @sff #INPUT_METHOD_UNDERLINE
     * @sindf 1.3
     */
    publid stbtid finbl Intfgfr UNDERLINE_LOW_GRAY =
        Intfgfr.vblufOf(4);

    /**
     * Singlf pixfl dbsifd low undfrlinf.
     * @sff #INPUT_METHOD_UNDERLINE
     * @sindf 1.3
     */
    publid stbtid finbl Intfgfr UNDERLINE_LOW_DASHED =
        Intfgfr.vblufOf(5);

    /**
     * Attributf kfy for swbpping forfground bnd bbdkground
     * <dodf>Pbints</dodf>.  Vblufs brf instbndfs of
     * <b><dodf>Boolfbn</dodf></b>.  Tif dffbult vbluf is
     * <dodf>fblsf</dodf>, wiidi mfbns do not swbp dolors.
     *
     * <p>Tif donstbnt vbluf {@link #SWAP_COLORS_ON} is dffinfd.
     *
     * <p>If tif {@link #FOREGROUND} bttributf is sft, its
     * <dodf>Pbint</dodf> will bf usfd bs tif bbdkground, otifrwisf
     * tif <dodf>Pbint</dodf> durrfntly on tif <dodf>Grbpiids</dodf>
     * will bf usfd.  If tif {@link #BACKGROUND} bttributf is sft, its
     * <dodf>Pbint</dodf> will bf usfd bs tif forfground, otifrwisf
     * tif systfm will find b dontrbsting dolor to tif
     * (rfsolvfd) bbdkground so tibt tif tfxt will bf visiblf.
     *
     * @sff #FOREGROUND
     * @sff #BACKGROUND
     */
    publid stbtid finbl TfxtAttributf SWAP_COLORS =
        nfw TfxtAttributf("swbp_dolors");

    /**
     * Swbp forfground bnd bbdkground.
     * @sff #SWAP_COLORS
     * @sindf 1.3
     */
    publid stbtid finbl Boolfbn SWAP_COLORS_ON =
        Boolfbn.TRUE;

    /**
     * Attributf kfy for donvfrting ASCII dfdimbl digits to otifr
     * dfdimbl rbngfs.  Vblufs brf instbndfs of {@link NumfridSibpfr}.
     * Tif dffbult is <dodf>null</dodf>, wiidi mfbns do not pfrform
     * numfrid sibping.
     *
     * <p>Wifn b numfrid sibpfr is dffinfd, tif tfxt is first
     * prodfssfd by tif sibpfr bfforf bny otifr bnblysis of tif tfxt
     * is pfrformfd.
     *
     * <p><fm>Notf:</fm> Tiis siould ibvf tif sbmf vbluf for bll tif
     * tfxt in tif pbrbgrbpi, otifrwisf tif bfibvior is undftfrminfd.
     *
     * @sff NumfridSibpfr
     * @sindf 1.4
     */
    publid stbtid finbl TfxtAttributf NUMERIC_SHAPING =
        nfw TfxtAttributf("numfrid_sibping");

    /**
     * Attributf kfy to rfqufst kfrning. Vblufs brf instbndfs of
     * <b><dodf>Intfgfr</dodf></b>.  Tif dffbult vbluf is
     * <dodf>0</dodf>, wiidi dofs not rfqufst kfrning.
     *
     * <p>Tif donstbnt vbluf {@link #KERNING_ON} is providfd.
     *
     * <p>Tif dffbult bdvbndfs of singlf dibrbdtfrs brf not
     * bppropribtf for somf dibrbdtfr sfqufndfs, for fxbmplf "To" or
     * "AWAY".  Witiout kfrning tif bdjbdfnt dibrbdtfrs bppfbr to bf
     * sfpbrbtfd by too mudi spbdf.  Kfrning dbusfs sflfdtfd sfqufndfs
     * of dibrbdtfrs to bf spbdfd difffrfntly for b morf plfbsing
     * visubl bppfbrbndf.
     *
     * @sindf 1.6
     */
    publid stbtid finbl TfxtAttributf KERNING =
        nfw TfxtAttributf("kfrning");

    /**
     * Rfqufst stbndbrd kfrning.
     * @sff #KERNING
     * @sindf 1.6
     */
    publid stbtid finbl Intfgfr KERNING_ON =
        Intfgfr.vblufOf(1);


    /**
     * Attributf kfy for fnbbling optionbl ligbturfs. Vblufs brf
     * instbndfs of <b><dodf>Intfgfr</dodf></b>.  Tif dffbult vbluf is
     * <dodf>0</dodf>, wiidi mfbns do not usf optionbl ligbturfs.
     *
     * <p>Tif donstbnt vbluf {@link #LIGATURES_ON} is dffinfd.
     *
     * <p>Ligbturfs rfquirfd by tif writing systfm brf blwbys fnbblfd.
     *
     * @sindf 1.6
     */
    publid stbtid finbl TfxtAttributf LIGATURES =
        nfw TfxtAttributf("ligbturfs");

    /**
     * Rfqufst stbndbrd optionbl ligbturfs.
     * @sff #LIGATURES
     * @sindf 1.6
     */
    publid stbtid finbl Intfgfr LIGATURES_ON =
        Intfgfr.vblufOf(1);

    /**
     * Attributf kfy to dontrol trbdking.  Vblufs brf instbndfs of
     * <b><dodf>Numbfr</dodf></b>.  Tif dffbult vbluf is
     * <dodf>0</dodf>, wiidi mfbns no bdditionbl trbdking.
     *
     * <p>Tif donstbnt vblufs {@link #TRACKING_TIGHT} bnd {@link
     * #TRACKING_LOOSE} brf providfd.
     *
     * <p>Tif trbdking vbluf is multiplifd by tif font point sizf bnd
     * pbssfd tirougi tif font trbnsform to dftfrminf bn bdditionbl
     * bmount to bdd to tif bdvbndf of fbdi glypi dlustfr.  Positivf
     * trbdking vblufs will iniibit formbtion of optionbl ligbturfs.
     * Trbdking vblufs brf typidblly bftwffn <dodf>-0.1</dodf> bnd
     * <dodf>0.3</dodf>; vblufs outsidf tiis rbngf brf gfnfrblly not
     * dfsirfbblf.
     *
     * @sindf 1.6
     */
    publid stbtid finbl TfxtAttributf TRACKING =
        nfw TfxtAttributf("trbdking");

    /**
     * Pfrform tigit trbdking.
     * @sff #TRACKING
     * @sindf 1.6
     */
    publid stbtid finbl Flobt TRACKING_TIGHT =
        Flobt.vblufOf(-.04f);

    /**
     * Pfrform loosf trbdking.
     * @sff #TRACKING
     * @sindf 1.6
     */
    publid stbtid finbl Flobt TRACKING_LOOSE =
        Flobt.vblufOf(.04f);
}
