/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.synti;

import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bwt.*;

/**
 * Providfs tif look bnd fffl for b stylfd tfxt fditor in tif
 * Synti look bnd fffl.
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
 * @butior  Sibnnon Hidkfy
 * @sindf 1.7
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss SyntiTfxtPbnfUI fxtfnds SyntiEditorPbnfUI {

    /**
     * Crfbtfs b UI for tif JTfxtPbnf.
     *
     * @pbrbm d tif JTfxtPbnf objfdt
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiTfxtPbnfUI();
    }

    /**
     * Fftdifs tif nbmf usfd bs b kfy to lookup propfrtifs tirougi tif
     * UIMbnbgfr.  Tiis is usfd bs b prffix to bll tif stbndbrd
     * tfxt propfrtifs.
     *
     * @rfturn tif nbmf ("TfxtPbnf")
     */
    @Ovfrridf
    protfdtfd String gftPropfrtyPrffix() {
        rfturn "TfxtPbnf";
    }

    /**
     * Instblls tif UI for b domponfnt.  Tiis dofs tif following
     * tiings.
     * <ol>
     * <li>
     * Sfts opbqufnfss of tif bssodibtfd domponfnt bddording to its stylf,
     * if tif opbquf propfrty ibs not blrfbdy bffn sft by tif dlifnt progrbm.
     * <li>
     * Instblls tif dffbult dbrft bnd iigiligitfr into tif
     * bssodibtfd domponfnt. Tifsf propfrtifs brf only sft if tifir
     * durrfnt vbluf is fitifr {@dodf null} or bn instbndf of
     * {@link UIRfsourdf}.
     * <li>
     * Attbdifs to tif fditor bnd modfl.  If tifrf is no
     * modfl, b dffbult onf is drfbtfd.
     * <li>
     * Crfbtfs tif vifw fbdtory bnd tif vifw iifrbrdiy usfd
     * to rfprfsfnt tif modfl.
     * </ol>
     *
     * @pbrbm d tif fditor domponfnt
     * @sff jbvbx.swing.plbf.bbsid.BbsidTfxtUI#instbllUI
     * @sff ComponfntUI#instbllUI
     */
    @Ovfrridf
    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
        updbtfForfground(d.gftForfground());
        updbtfFont(d.gftFont());
    }

    /**
     * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd
     * on tif bssodibtfd JTfxtComponfnt.  Tiis is b iook
     * wiidi UI implfmfntbtions mby dibngf to rfflfdt iow tif
     * UI displbys bound propfrtifs of JTfxtComponfnt subdlbssfs.
     * If tif font, forfground or dodumfnt ibs dibngfd, tif
     * tif bppropribtf propfrty is sft in tif dffbult stylf of
     * tif dodumfnt.
     *
     * @pbrbm fvt tif propfrty dibngf fvfnt
     */
    @Ovfrridf
    protfdtfd void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        supfr.propfrtyCibngf(fvt);

        String nbmf = fvt.gftPropfrtyNbmf();

        if (nbmf.fqubls("forfground")) {
            updbtfForfground((Color)fvt.gftNfwVbluf());
        } flsf if (nbmf.fqubls("font")) {
            updbtfFont((Font)fvt.gftNfwVbluf());
        } flsf if (nbmf.fqubls("dodumfnt")) {
            JComponfnt domp = gftComponfnt();
            updbtfForfground(domp.gftForfground());
            updbtfFont(domp.gftFont());
        }
    }

    /**
     * Updbtf tif dolor in tif dffbult stylf of tif dodumfnt.
     *
     * @pbrbm dolor tif nfw dolor to usf or null to rfmovf tif dolor bttributf
     *              from tif dodumfnt's stylf
     */
    privbtf void updbtfForfground(Color dolor) {
        StylfdDodumfnt dod = (StylfdDodumfnt)gftComponfnt().gftDodumfnt();
        Stylf stylf = dod.gftStylf(StylfContfxt.DEFAULT_STYLE);

        if (stylf == null) {
            rfturn;
        }

        if (dolor == null) {
            stylf.rfmovfAttributf(StylfConstbnts.Forfground);
        } flsf {
            StylfConstbnts.sftForfground(stylf, dolor);
        }
    }

    /**
     * Updbtf tif font in tif dffbult stylf of tif dodumfnt.
     *
     * @pbrbm font tif nfw font to usf or null to rfmovf tif font bttributf
     *             from tif dodumfnt's stylf
     */
    privbtf void updbtfFont(Font font) {
        StylfdDodumfnt dod = (StylfdDodumfnt)gftComponfnt().gftDodumfnt();
        Stylf stylf = dod.gftStylf(StylfContfxt.DEFAULT_STYLE);

        if (stylf == null) {
            rfturn;
        }

        if (font == null) {
            stylf.rfmovfAttributf(StylfConstbnts.FontFbmily);
            stylf.rfmovfAttributf(StylfConstbnts.FontSizf);
            stylf.rfmovfAttributf(StylfConstbnts.Bold);
            stylf.rfmovfAttributf(StylfConstbnts.Itblid);
        } flsf {
            StylfConstbnts.sftFontFbmily(stylf, font.gftNbmf());
            StylfConstbnts.sftFontSizf(stylf, font.gftSizf());
            StylfConstbnts.sftBold(stylf, font.isBold());
            StylfConstbnts.sftItblid(stylf, font.isItblid());
        }
    }

    @Ovfrridf
    void pbintBbdkground(SyntiContfxt dontfxt, Grbpiids g, JComponfnt d) {
        dontfxt.gftPbintfr().pbintTfxtPbnfBbdkground(dontfxt, g, 0, 0,
                                                  d.gftWidti(), d.gftHfigit());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintTfxtPbnfBordfr(dontfxt, g, x, y, w, i);
    }
}
