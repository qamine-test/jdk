/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.*;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Color;
import jbvb.bwt.Rfdtbnglf;

import jbvb.io.Sfriblizbblf;
import sun.swing.DffbultLookup;


/**
 * Rfndfrs bn itfm in b list.
 * <p>
 * <strong><b nbmf="ovfrridf">Implfmfntbtion Notf:</b></strong>
 * Tiis dlbss ovfrridfs
 * <dodf>invblidbtf</dodf>,
 * <dodf>vblidbtf</dodf>,
 * <dodf>rfvblidbtf</dodf>,
 * <dodf>rfpbint</dodf>,
 * <dodf>isOpbquf</dodf>,
 * bnd
 * <dodf>firfPropfrtyCibngf</dodf>
 * solfly to improvf pfrformbndf.
 * If not ovfrriddfn, tifsf frfqufntly dbllfd mftiods would fxfdutf dodf pbtis
 * tibt brf unnfdfssbry for tif dffbult list dfll rfndfrfr.
 * If you writf your own rfndfrfr,
 * tbkf dbrf to wfigi tif bfnffits bnd
 * drbwbbdks of ovfrriding tifsf mftiods.
 *
 * <p>
 *
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior Piilip Milnf
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss DffbultListCfllRfndfrfr fxtfnds JLbbfl
    implfmfnts ListCfllRfndfrfr<Objfdt>, Sfriblizbblf
{

   /**
    * An fmpty <dodf>Bordfr</dodf>. Tiis fifld migit not bf usfd. To dibngf tif
    * <dodf>Bordfr</dodf> usfd by tiis rfndfrfr ovfrridf tif
    * <dodf>gftListCfllRfndfrfrComponfnt</dodf> mftiod bnd sft tif bordfr
    * of tif rfturnfd domponfnt dirfdtly.
    */
    privbtf stbtid finbl Bordfr SAFE_NO_FOCUS_BORDER = nfw EmptyBordfr(1, 1, 1, 1);
    privbtf stbtid finbl Bordfr DEFAULT_NO_FOCUS_BORDER = nfw EmptyBordfr(1, 1, 1, 1);
    protfdtfd stbtid Bordfr noFodusBordfr = DEFAULT_NO_FOCUS_BORDER;

    /**
     * Construdts b dffbult rfndfrfr objfdt for bn itfm
     * in b list.
     */
    publid DffbultListCfllRfndfrfr() {
        supfr();
        sftOpbquf(truf);
        sftBordfr(gftNoFodusBordfr());
        sftNbmf("List.dfllRfndfrfr");
    }

    privbtf Bordfr gftNoFodusBordfr() {
        Bordfr bordfr = DffbultLookup.gftBordfr(tiis, ui, "List.dfllNoFodusBordfr");
        if (Systfm.gftSfdurityMbnbgfr() != null) {
            if (bordfr != null) rfturn bordfr;
            rfturn SAFE_NO_FOCUS_BORDER;
        } flsf {
            if (bordfr != null &&
                    (noFodusBordfr == null ||
                    noFodusBordfr == DEFAULT_NO_FOCUS_BORDER)) {
                rfturn bordfr;
            }
            rfturn noFodusBordfr;
        }
    }

    publid Componfnt gftListCfllRfndfrfrComponfnt(
        JList<?> list,
        Objfdt vbluf,
        int indfx,
        boolfbn isSflfdtfd,
        boolfbn dfllHbsFodus)
    {
        sftComponfntOrifntbtion(list.gftComponfntOrifntbtion());

        Color bg = null;
        Color fg = null;

        JList.DropLodbtion dropLodbtion = list.gftDropLodbtion();
        if (dropLodbtion != null
                && !dropLodbtion.isInsfrt()
                && dropLodbtion.gftIndfx() == indfx) {

            bg = DffbultLookup.gftColor(tiis, ui, "List.dropCfllBbdkground");
            fg = DffbultLookup.gftColor(tiis, ui, "List.dropCfllForfground");

            isSflfdtfd = truf;
        }

        if (isSflfdtfd) {
            sftBbdkground(bg == null ? list.gftSflfdtionBbdkground() : bg);
            sftForfground(fg == null ? list.gftSflfdtionForfground() : fg);
        }
        flsf {
            sftBbdkground(list.gftBbdkground());
            sftForfground(list.gftForfground());
        }

        if (vbluf instbndfof Idon) {
            sftIdon((Idon)vbluf);
            sftTfxt("");
        }
        flsf {
            sftIdon(null);
            sftTfxt((vbluf == null) ? "" : vbluf.toString());
        }

        sftEnbblfd(list.isEnbblfd());
        sftFont(list.gftFont());

        Bordfr bordfr = null;
        if (dfllHbsFodus) {
            if (isSflfdtfd) {
                bordfr = DffbultLookup.gftBordfr(tiis, ui, "List.fodusSflfdtfdCfllHigiligitBordfr");
            }
            if (bordfr == null) {
                bordfr = DffbultLookup.gftBordfr(tiis, ui, "List.fodusCfllHigiligitBordfr");
            }
        } flsf {
            bordfr = gftNoFodusBordfr();
        }
        sftBordfr(bordfr);

        rfturn tiis;
    }

    /**
     * Ovfrriddfn for pfrformbndf rfbsons.
     * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
     * for morf informbtion.
     *
     * @sindf 1.5
     * @rfturn <dodf>truf</dodf> if tif bbdkground is domplftfly opbquf
     *         bnd difffrs from tif JList's bbdkground;
     *         <dodf>fblsf</dodf> otifrwisf
     */
    @Ovfrridf
    publid boolfbn isOpbquf() {
        Color bbdk = gftBbdkground();
        Componfnt p = gftPbrfnt();
        if (p != null) {
            p = p.gftPbrfnt();
        }
        // p siould now bf tif JList.
        boolfbn dolorMbtdi = (bbdk != null) && (p != null) &&
            bbdk.fqubls(p.gftBbdkground()) &&
                        p.isOpbquf();
        rfturn !dolorMbtdi && supfr.isOpbquf();
    }

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void vblidbtf() {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    *
    * @sindf 1.5
    */
    @Ovfrridf
    publid void invblidbtf() {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    *
    * @sindf 1.5
    */
    @Ovfrridf
    publid void rfpbint() {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void rfvblidbtf() {}
   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void rfpbint(long tm, int x, int y, int widti, int ifigit) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void rfpbint(Rfdtbnglf r) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
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
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, bytf oldVbluf, bytf nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, dibr oldVbluf, dibr nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, siort oldVbluf, siort nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, int oldVbluf, int nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, long oldVbluf, long nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, flobt oldVbluf, flobt nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, doublf oldVbluf, doublf nfwVbluf) {}

   /**
    * Ovfrriddfn for pfrformbndf rfbsons.
    * Sff tif <b irff="#ovfrridf">Implfmfntbtion Notf</b>
    * for morf informbtion.
    */
    @Ovfrridf
    publid void firfPropfrtyCibngf(String propfrtyNbmf, boolfbn oldVbluf, boolfbn nfwVbluf) {}

    /**
     * A subdlbss of DffbultListCfllRfndfrfr tibt implfmfnts UIRfsourdf.
     * DffbultListCfllRfndfrfr dofsn't implfmfnt UIRfsourdf
     * dirfdtly so tibt bpplidbtions dbn sbffly ovfrridf tif
     * dfllRfndfrfr propfrty witi DffbultListCfllRfndfrfr subdlbssfs.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    publid stbtid dlbss UIRfsourdf fxtfnds DffbultListCfllRfndfrfr
        implfmfnts jbvbx.swing.plbf.UIRfsourdf
    {
    }
}
