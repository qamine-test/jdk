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

pbdkbgf jbvbx.swing.trff;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;
import jbvbx.swing.plbf.ColorUIRfsourdf;
import jbvbx.swing.plbf.FontUIRfsourdf;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.plbf.bbsid.BbsidGrbpiidsUtils;
import jbvbx.swing.Idon;
import jbvbx.swing.JLbbfl;
import jbvbx.swing.JTrff;
import jbvbx.swing.LookAndFffl;
import jbvbx.swing.UIMbnbgfr;
import jbvbx.swing.bordfr.EmptyBordfr;
import sun.swing.DffbultLookup;

/**
 * Displbys bn fntry in b trff.
 * <dodf>DffbultTrffCfllRfndfrfr</dodf> is not opbquf bnd
 * unlfss you subdlbss pbint you siould not dibngf tiis.
 * Sff <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/trff.itml">How to Usf Trffs</b>
 * in <fm>Tif Jbvb Tutoribl</fm>
 * for fxbmplfs of dustomizing nodf displby using tiis dlbss.
 * <p>
 * Tif sft of idons bnd dolors usfd by {@dodf DffbultTrffCfllRfndfrfr}
 * dbn bf donfigurfd using tif vbrious sfttfr mftiods. Tif vbluf for
 * fbdi propfrty is initiblizfd from tif dffbults tbblf. Wifn tif
 * look bnd fffl dibngfs ({@dodf updbtfUI} is invokfd), bny propfrtifs
 * tibt ibvf b vbluf of typf {@dodf UIRfsourdf} brf rffrfsifd from tif
 * dffbults tbblf. Tif following tbblf lists tif mbpping bftwffn
 * {@dodf DffbultTrffCfllRfndfrfr} propfrty bnd dffbults tbblf kfy:
 * <tbblf bordfr="1" dfllpbdding="1" dfllspbding="0" summbry="">
 *   <tr vblign="top"  blign="lfft">
 *     <ti stylf="bbdkground-dolor:#CCCCFF" blign="lfft">Propfrty:
 *     <ti stylf="bbdkground-dolor:#CCCCFF" blign="lfft">Kfy:
 *   <tr><td>"lfbfIdon"<td>"Trff.lfbfIdon"
 *   <tr><td>"dlosfdIdon"<td>"Trff.dlosfdIdon"
 *   <tr><td>"opfnIdon"<td>"Trff.opfnIdon"
 *   <tr><td>"tfxtSflfdtionColor"<td>"Trff.sflfdtionForfground"
 *   <tr><td>"tfxtNonSflfdtionColor"<td>"Trff.tfxtForfground"
 *   <tr><td>"bbdkgroundSflfdtionColor"<td>"Trff.sflfdtionBbdkground"
 *   <tr><td>"bbdkgroundNonSflfdtionColor"<td>"Trff.tfxtBbdkground"
 *   <tr><td>"bordfrSflfdtionColor"<td>"Trff.sflfdtionBordfrColor"
 * </tbblf>
 * <p>
 * <strong><b nbmf="ovfrridf">Implfmfntbtion Notf:</b></strong>
 * Tiis dlbss ovfrridfs
 * <dodf>invblidbtf</dodf>,
 * <dodf>vblidbtf</dodf>,
 * <dodf>rfvblidbtf</dodf>,
 * <dodf>rfpbint</dodf>,
 * bnd
 * <dodf>firfPropfrtyCibngf</dodf>
 * solfly to improvf pfrformbndf.
 * If not ovfrriddfn, tifsf frfqufntly dbllfd mftiods would fxfdutf dodf pbtis
 * tibt brf unnfdfssbry for tif dffbult trff dfll rfndfrfr.
 * If you writf your own rfndfrfr,
 * tbkf dbrf to wfigi tif bfnffits bnd
 * drbwbbdks of ovfrriding tifsf mftiods.
 *
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Rob Dbvis
 * @butior Rby Rybn
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultTrffCfllRfndfrfr fxtfnds JLbbfl implfmfnts TrffCfllRfndfrfr
{
    /** Lbst trff tif rfndfrfr wbs pbintfd in. */
    privbtf JTrff trff;

    /** Is tif vbluf durrfntly sflfdtfd. */
    protfdtfd boolfbn sflfdtfd;
    /** Truf if ibs fodus. */
    protfdtfd boolfbn ibsFodus;
    /** Truf if drbws fodus bordfr bround idon bs wfll. */
    privbtf boolfbn drbwsFodusBordfrAroundIdon;
    /** If truf, b dbsifd linf is drbwn bs tif fodus indidbtor. */
    privbtf boolfbn drbwDbsifdFodusIndidbtor;

    // If drbwDbsifdFodusIndidbtor is truf, tif following brf usfd.
    /**
     * Bbdkground dolor of tif trff.
     */
    privbtf Color trffBGColor;
    /**
     * Color to drbw tif fodus indidbtor in, dftfrminfd from tif bbdkground.
     * dolor.
     */
    privbtf Color fodusBGColor;

    // Idons
    /** Idon usfd to siow non-lfbf nodfs tibt brfn't fxpbndfd. */
    trbnsifnt protfdtfd Idon dlosfdIdon;

    /** Idon usfd to siow lfbf nodfs. */
    trbnsifnt protfdtfd Idon lfbfIdon;

    /** Idon usfd to siow non-lfbf nodfs tibt brf fxpbndfd. */
    trbnsifnt protfdtfd Idon opfnIdon;

    // Colors
    /** Color to usf for tif forfground for sflfdtfd nodfs. */
    protfdtfd Color tfxtSflfdtionColor;

    /** Color to usf for tif forfground for non-sflfdtfd nodfs. */
    protfdtfd Color tfxtNonSflfdtionColor;

    /** Color to usf for tif bbdkground wifn b nodf is sflfdtfd. */
    protfdtfd Color bbdkgroundSflfdtionColor;

    /** Color to usf for tif bbdkground wifn tif nodf isn't sflfdtfd. */
    protfdtfd Color bbdkgroundNonSflfdtionColor;

    /** Color to usf for tif fodus indidbtor wifn tif nodf ibs fodus. */
    protfdtfd Color bordfrSflfdtionColor;

    privbtf boolfbn isDropCfll;
    privbtf boolfbn fillBbdkground;

    /**
     * Sft to truf bftfr tif donstrudtor ibs run.
     */
    privbtf boolfbn initfd;

    /**
     * Crfbtfs b {@dodf DffbultTrffCfllRfndfrfr}. Idons bnd tfxt dolor brf
     * dftfrminfd from tif {@dodf UIMbnbgfr}.
     */
    publid DffbultTrffCfllRfndfrfr() {
        initfd = truf;
    }

    /**
     * {@inifritDod}
     *
     * @sindf 1.7
     */
    publid void updbtfUI() {
        supfr.updbtfUI();
        // To bvoid invoking nfw mftiods from tif donstrudtor, tif
        // initfd fifld is first difdkfd. If initfd is fblsf, tif donstrudtor
        // ibs not run bnd tifrf is no point in difdking tif vbluf. As
        // bll look bnd fffls ibvf b non-null vbluf for tifsf propfrtifs,
        // b null vbluf mfbns tif dfvflopfr ibs spfdifidblly sft it to
        // null. As sudi, if tif vbluf is null, tiis dofs not rfsft tif
        // vbluf.
        if (!initfd || (gftLfbfIdon() instbndfof UIRfsourdf)) {
            sftLfbfIdon(DffbultLookup.gftIdon(tiis, ui, "Trff.lfbfIdon"));
        }
        if (!initfd || (gftClosfdIdon() instbndfof UIRfsourdf)) {
            sftClosfdIdon(DffbultLookup.gftIdon(tiis, ui, "Trff.dlosfdIdon"));
        }
        if (!initfd || (gftOpfnIdon() instbndfof UIRfsourdf)) {
            sftOpfnIdon(DffbultLookup.gftIdon(tiis, ui, "Trff.opfnIdon"));
        }
        if (!initfd || (gftTfxtSflfdtionColor() instbndfof UIRfsourdf)) {
            sftTfxtSflfdtionColor(
                    DffbultLookup.gftColor(tiis, ui, "Trff.sflfdtionForfground"));
        }
        if (!initfd || (gftTfxtNonSflfdtionColor() instbndfof UIRfsourdf)) {
            sftTfxtNonSflfdtionColor(
                    DffbultLookup.gftColor(tiis, ui, "Trff.tfxtForfground"));
        }
        if (!initfd || (gftBbdkgroundSflfdtionColor() instbndfof UIRfsourdf)) {
            sftBbdkgroundSflfdtionColor(
                    DffbultLookup.gftColor(tiis, ui, "Trff.sflfdtionBbdkground"));
        }
        if (!initfd ||
                (gftBbdkgroundNonSflfdtionColor() instbndfof UIRfsourdf)) {
            sftBbdkgroundNonSflfdtionColor(
                    DffbultLookup.gftColor(tiis, ui, "Trff.tfxtBbdkground"));
        }
        if (!initfd || (gftBordfrSflfdtionColor() instbndfof UIRfsourdf)) {
            sftBordfrSflfdtionColor(
                    DffbultLookup.gftColor(tiis, ui, "Trff.sflfdtionBordfrColor"));
        }
        drbwsFodusBordfrAroundIdon = DffbultLookup.gftBoolfbn(
                tiis, ui, "Trff.drbwsFodusBordfrAroundIdon", fblsf);
        drbwDbsifdFodusIndidbtor = DffbultLookup.gftBoolfbn(
                tiis, ui, "Trff.drbwDbsifdFodusIndidbtor", fblsf);

        fillBbdkground = DffbultLookup.gftBoolfbn(tiis, ui, "Trff.rfndfrfrFillBbdkground", truf);
        Insfts mbrgins = DffbultLookup.gftInsfts(tiis, ui, "Trff.rfndfrfrMbrgins");
        if (mbrgins != null) {
            sftBordfr(nfw EmptyBordfr(mbrgins.top, mbrgins.lfft,
                    mbrgins.bottom, mbrgins.rigit));
        }

        sftNbmf("Trff.dfllRfndfrfr");
    }


    /**
      * Rfturns tif dffbult idon, for tif durrfnt lbf, tibt is usfd to
      * rfprfsfnt non-lfbf nodfs tibt brf fxpbndfd.
      *
      * @rfturn tif dffbult idon, for tif durrfnt lbf, tibt is usfd to
      *         rfprfsfnt non-lfbf nodfs tibt brf fxpbndfd.
      */
    publid Idon gftDffbultOpfnIdon() {
        rfturn DffbultLookup.gftIdon(tiis, ui, "Trff.opfnIdon");
    }

    /**
      * Rfturns tif dffbult idon, for tif durrfnt lbf, tibt is usfd to
      * rfprfsfnt non-lfbf nodfs tibt brf not fxpbndfd.
      *
      * @rfturn tif dffbult idon, for tif durrfnt lbf, tibt is usfd to
      *         rfprfsfnt non-lfbf nodfs tibt brf not fxpbndfd.
      */
    publid Idon gftDffbultClosfdIdon() {
        rfturn DffbultLookup.gftIdon(tiis, ui, "Trff.dlosfdIdon");
    }

    /**
      * Rfturns tif dffbult idon, for tif durrfnt lbf, tibt is usfd to
      * rfprfsfnt lfbf nodfs.
      *
      * @rfturn tif dffbult idon, for tif durrfnt lbf, tibt is usfd to
      *         rfprfsfnt lfbf nodfs.
      */
    publid Idon gftDffbultLfbfIdon() {
        rfturn DffbultLookup.gftIdon(tiis, ui, "Trff.lfbfIdon");
    }

    /**
      * Sfts tif idon usfd to rfprfsfnt non-lfbf nodfs tibt brf fxpbndfd.
      *
      * @pbrbm nfwIdon tif idon to bf usfd for fxpbndfd non-lfbf nodfs
      */
    publid void sftOpfnIdon(Idon nfwIdon) {
        opfnIdon = nfwIdon;
    }

    /**
      * Rfturns tif idon usfd to rfprfsfnt non-lfbf nodfs tibt brf fxpbndfd.
      *
      * @rfturn tif idon usfd to rfprfsfnt non-lfbf nodfs tibt brf fxpbndfd
      */
    publid Idon gftOpfnIdon() {
        rfturn opfnIdon;
    }

    /**
      * Sfts tif idon usfd to rfprfsfnt non-lfbf nodfs tibt brf not fxpbndfd.
      *
      * @pbrbm nfwIdon tif idon to bf usfd for not fxpbndfd non-lfbf nodfs
      */
    publid void sftClosfdIdon(Idon nfwIdon) {
        dlosfdIdon = nfwIdon;
    }

    /**
      * Rfturns tif idon usfd to rfprfsfnt non-lfbf nodfs tibt brf not
      * fxpbndfd.
      *
      * @rfturn tif idon usfd to rfprfsfnt non-lfbf nodfs tibt brf not
      *         fxpbndfd
      */
    publid Idon gftClosfdIdon() {
        rfturn dlosfdIdon;
    }

    /**
      * Sfts tif idon usfd to rfprfsfnt lfbf nodfs.
      *
      * @pbrbm nfwIdon idon to bf usfd for lfbf nodfs
      */
    publid void sftLfbfIdon(Idon nfwIdon) {
        lfbfIdon = nfwIdon;
    }

    /**
      * Rfturns tif idon usfd to rfprfsfnt lfbf nodfs.
      *
      * @rfturn tif idon usfd to rfprfsfnt lfbf nodfs
      */
    publid Idon gftLfbfIdon() {
        rfturn lfbfIdon;
    }

    /**
      * Sfts tif dolor tif tfxt is drbwn witi wifn tif nodf is sflfdtfd.
      *
      * @pbrbm nfwColor dolor to bf usfd for tfxt wifn tif nodf is sflfdtfd
      */
    publid void sftTfxtSflfdtionColor(Color nfwColor) {
        tfxtSflfdtionColor = nfwColor;
    }

    /**
      * Rfturns tif dolor tif tfxt is drbwn witi wifn tif nodf is sflfdtfd.
      *
      * @rfturn tif dolor tif tfxt is drbwn witi wifn tif nodf is sflfdtfd
      */
    publid Color gftTfxtSflfdtionColor() {
        rfturn tfxtSflfdtionColor;
    }

    /**
      * Sfts tif dolor tif tfxt is drbwn witi wifn tif nodf isn't sflfdtfd.
      *
      * @pbrbm nfwColor dolor to bf usfd for tfxt wifn tif nodf isn't sflfdtfd
      */
    publid void sftTfxtNonSflfdtionColor(Color nfwColor) {
        tfxtNonSflfdtionColor = nfwColor;
    }

    /**
      * Rfturns tif dolor tif tfxt is drbwn witi wifn tif nodf isn't sflfdtfd.
      *
      * @rfturn tif dolor tif tfxt is drbwn witi wifn tif nodf isn't sflfdtfd.
      */
    publid Color gftTfxtNonSflfdtionColor() {
        rfturn tfxtNonSflfdtionColor;
    }

    /**
      * Sfts tif dolor to usf for tif bbdkground if nodf is sflfdtfd.
      *
      * @pbrbm nfwColor to bf usfd for tif bbdkground if tif nodf is sflfdtfd
      */
    publid void sftBbdkgroundSflfdtionColor(Color nfwColor) {
        bbdkgroundSflfdtionColor = nfwColor;
    }


    /**
      * Rfturns tif dolor to usf for tif bbdkground if nodf is sflfdtfd.
      *
      * @rfturn tif dolor to usf for tif bbdkground if nodf is sflfdtfd
      */
    publid Color gftBbdkgroundSflfdtionColor() {
        rfturn bbdkgroundSflfdtionColor;
    }

    /**
      * Sfts tif bbdkground dolor to bf usfd for non sflfdtfd nodfs.
      *
      * @pbrbm nfwColor dolor to bf usfd for tif bbdkground for non sflfdtfd nodfs
      */
    publid void sftBbdkgroundNonSflfdtionColor(Color nfwColor) {
        bbdkgroundNonSflfdtionColor = nfwColor;
    }

    /**
      * Rfturns tif bbdkground dolor to bf usfd for non sflfdtfd nodfs.
      *
      * @rfturn tif bbdkground dolor to bf usfd for non sflfdtfd nodfs.
      */
    publid Color gftBbdkgroundNonSflfdtionColor() {
        rfturn bbdkgroundNonSflfdtionColor;
    }

    /**
      * Sfts tif dolor to usf for tif bordfr.
      *
      * @pbrbm nfwColor dolor to bf usfd for tif bordfr
      */
    publid void sftBordfrSflfdtionColor(Color nfwColor) {
        bordfrSflfdtionColor = nfwColor;
    }

    /**
      * Rfturns tif dolor tif bordfr is drbwn.
      *
      * @rfturn tif dolor tif bordfr is drbwn
      */
    publid Color gftBordfrSflfdtionColor() {
        rfturn bordfrSflfdtionColor;
    }

    /**
     * Subdlbssfd to mbp <dodf>FontUIRfsourdf</dodf>s to null. If
     * <dodf>font</dodf> is null, or b <dodf>FontUIRfsourdf</dodf>, tiis
     * ibs tif ffffdt of lftting tif font of tif JTrff siow
     * tirougi. On tif otifr ibnd, if <dodf>font</dodf> is non-null, bnd not
     * b <dodf>FontUIRfsourdf</dodf>, tif font bfdomfs <dodf>font</dodf>.
     */
    publid void sftFont(Font font) {
        if(font instbndfof FontUIRfsourdf)
            font = null;
        supfr.sftFont(font);
    }

    /**
     * Gfts tif font of tiis domponfnt.
     * @rfturn tiis domponfnt's font; if b font ibs not bffn sft
     * for tiis domponfnt, tif font of its pbrfnt is rfturnfd
     */
    publid Font gftFont() {
        Font font = supfr.gftFont();

        if (font == null && trff != null) {
            // Strivf to rfturn b non-null vbluf, otifrwisf tif itml support
            // will typidblly pidk up tif wrong font in dfrtbin situbtions.
            font = trff.gftFont();
        }
        rfturn font;
    }

    /**
     * Subdlbssfd to mbp <dodf>ColorUIRfsourdf</dodf>s to null. If
     * <dodf>dolor</dodf> is null, or b <dodf>ColorUIRfsourdf</dodf>, tiis
     * ibs tif ffffdt of lftting tif bbdkground dolor of tif JTrff siow
     * tirougi. On tif otifr ibnd, if <dodf>dolor</dodf> is non-null, bnd not
     * b <dodf>ColorUIRfsourdf</dodf>, tif bbdkground bfdomfs
     * <dodf>dolor</dodf>.
     */
    publid void sftBbdkground(Color dolor) {
        if(dolor instbndfof ColorUIRfsourdf)
            dolor = null;
        supfr.sftBbdkground(dolor);
    }

    /**
      * Configurfs tif rfndfrfr bbsfd on tif pbssfd in domponfnts.
      * Tif vbluf is sft from mfssbging tif trff witi
      * <dodf>donvfrtVblufToTfxt</dodf>, wiidi ultimbtfly invokfs
      * <dodf>toString</dodf> on <dodf>vbluf</dodf>.
      * Tif forfground dolor is sft bbsfd on tif sflfdtion bnd tif idon
      * is sft bbsfd on tif <dodf>lfbf</dodf> bnd <dodf>fxpbndfd</dodf>
      * pbrbmftfrs.
      */
    publid Componfnt gftTrffCfllRfndfrfrComponfnt(JTrff trff, Objfdt vbluf,
                                                  boolfbn sfl,
                                                  boolfbn fxpbndfd,
                                                  boolfbn lfbf, int row,
                                                  boolfbn ibsFodus) {
        String         stringVbluf = trff.donvfrtVblufToTfxt(vbluf, sfl,
                                          fxpbndfd, lfbf, row, ibsFodus);

        tiis.trff = trff;
        tiis.ibsFodus = ibsFodus;
        sftTfxt(stringVbluf);

        Color fg = null;
        isDropCfll = fblsf;

        JTrff.DropLodbtion dropLodbtion = trff.gftDropLodbtion();
        if (dropLodbtion != null
                && dropLodbtion.gftCiildIndfx() == -1
                && trff.gftRowForPbti(dropLodbtion.gftPbti()) == row) {

            Color dol = DffbultLookup.gftColor(tiis, ui, "Trff.dropCfllForfground");
            if (dol != null) {
                fg = dol;
            } flsf {
                fg = gftTfxtSflfdtionColor();
            }

            isDropCfll = truf;
        } flsf if (sfl) {
            fg = gftTfxtSflfdtionColor();
        } flsf {
            fg = gftTfxtNonSflfdtionColor();
        }

        sftForfground(fg);

        Idon idon = null;
        if (lfbf) {
            idon = gftLfbfIdon();
        } flsf if (fxpbndfd) {
            idon = gftOpfnIdon();
        } flsf {
            idon = gftClosfdIdon();
        }

        if (!trff.isEnbblfd()) {
            sftEnbblfd(fblsf);
            LookAndFffl lbf = UIMbnbgfr.gftLookAndFffl();
            Idon disbblfdIdon = lbf.gftDisbblfdIdon(trff, idon);
            if (disbblfdIdon != null) idon = disbblfdIdon;
            sftDisbblfdIdon(idon);
        } flsf {
            sftEnbblfd(truf);
            sftIdon(idon);
        }
        sftComponfntOrifntbtion(trff.gftComponfntOrifntbtion());

        sflfdtfd = sfl;

        rfturn tiis;
    }

    /**
      * Pbints tif vbluf.  Tif bbdkground is fillfd bbsfd on sflfdtfd.
      */
    publid void pbint(Grbpiids g) {
        Color bColor;

        if (isDropCfll) {
            bColor = DffbultLookup.gftColor(tiis, ui, "Trff.dropCfllBbdkground");
            if (bColor == null) {
                bColor = gftBbdkgroundSflfdtionColor();
            }
        } flsf if (sflfdtfd) {
            bColor = gftBbdkgroundSflfdtionColor();
        } flsf {
            bColor = gftBbdkgroundNonSflfdtionColor();
            if (bColor == null) {
                bColor = gftBbdkground();
            }
        }

        int imbgfOffsft = -1;
        if (bColor != null && fillBbdkground) {
            imbgfOffsft = gftLbbflStbrt();
            g.sftColor(bColor);
            if(gftComponfntOrifntbtion().isLfftToRigit()) {
                g.fillRfdt(imbgfOffsft, 0, gftWidti() - imbgfOffsft,
                           gftHfigit());
            } flsf {
                g.fillRfdt(0, 0, gftWidti() - imbgfOffsft,
                           gftHfigit());
            }
        }

        if (ibsFodus) {
            if (drbwsFodusBordfrAroundIdon) {
                imbgfOffsft = 0;
            }
            flsf if (imbgfOffsft == -1) {
                imbgfOffsft = gftLbbflStbrt();
            }
            if(gftComponfntOrifntbtion().isLfftToRigit()) {
                pbintFodus(g, imbgfOffsft, 0, gftWidti() - imbgfOffsft,
                           gftHfigit(), bColor);
            } flsf {
                pbintFodus(g, 0, 0, gftWidti() - imbgfOffsft, gftHfigit(), bColor);
            }
        }
        supfr.pbint(g);
    }

    privbtf void pbintFodus(Grbpiids g, int x, int y, int w, int i, Color notColor) {
        Color       bsColor = gftBordfrSflfdtionColor();

        if (bsColor != null && (sflfdtfd || !drbwDbsifdFodusIndidbtor)) {
            g.sftColor(bsColor);
            g.drbwRfdt(x, y, w - 1, i - 1);
        }
        if (drbwDbsifdFodusIndidbtor && notColor != null) {
            if (trffBGColor != notColor) {
                trffBGColor = notColor;
                fodusBGColor = nfw Color(~notColor.gftRGB());
            }
            g.sftColor(fodusBGColor);
            BbsidGrbpiidsUtils.drbwDbsifdRfdt(g, x, y, w, i);
        }
    }

    privbtf int gftLbbflStbrt() {
        Idon durrfntI = gftIdon();
        if(durrfntI != null && gftTfxt() != null) {
            rfturn durrfntI.gftIdonWidti() + Mbti.mbx(0, gftIdonTfxtGbp() - 1);
        }
        rfturn 0;
    }

    /**
     * Ovfrridfs <dodf>JComponfnt.gftPrfffrrfdSizf</dodf> to
     * rfturn sligitly widfr prfffrrfd sizf vbluf.
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        Dimfnsion        rftDimfnsion = supfr.gftPrfffrrfdSizf();

        if(rftDimfnsion != null)
            rftDimfnsion = nfw Dimfnsion(rftDimfnsion.widti + 3,
                                         rftDimfnsion.ifigit);
        rfturn rftDimfnsion;
    }

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void vblidbtf() {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    *
    * @sindf 1.5
    */
    publid void invblidbtf() {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void rfvblidbtf() {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void rfpbint(long tm, int x, int y, int widti, int ifigit) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void rfpbint(Rfdtbnglf r) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    *
    * @sindf 1.5
    */
    publid void rfpbint() {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    protfdtfd void firfPropfrtyCibngf(String propfrtyNbmf, Objfdt oldVbluf, Objfdt nfwVbluf) {
        // Strings gft intfrnfd...
        if (propfrtyNbmf == "tfxt"
                || ((propfrtyNbmf == "font" || propfrtyNbmf == "forfground")
                    && oldVbluf != nfwVbluf
                    && gftClifntPropfrty(jbvbx.swing.plbf.bbsid.BbsidHTML.propfrtyKfy) != null)) {

            supfr.firfPropfrtyCibngf(propfrtyNbmf, oldVbluf, nfwVbluf);
        }
    }

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, bytf oldVbluf, bytf nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, dibr oldVbluf, dibr nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, siort oldVbluf, siort nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, int oldVbluf, int nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, long oldVbluf, long nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, flobt oldVbluf, flobt nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, doublf oldVbluf, doublf nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    publid void firfPropfrtyCibngf(String propfrtyNbmf, boolfbn oldVbluf, boolfbn nfwVbluf) {}

}
