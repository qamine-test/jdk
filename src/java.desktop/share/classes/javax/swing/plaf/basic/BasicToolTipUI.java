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

pbdkbgf jbvbx.swing.plbf.bbsid;

import sun.swing.SwingUtilitifs2;
import jbvb.bwt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

import jbvbx.swing.*;
import jbvbx.swing.BordfrFbdtory;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.ToolTipUI;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.UIRfsourdf;
import jbvbx.swing.tfxt.Vifw;


/**
 * Stbndbrd tool tip L&bmp;F.
 *
 * @butior Dbvf Moorf
 */
publid dlbss BbsidToolTipUI fxtfnds ToolTipUI
{
    stbtid BbsidToolTipUI sibrfdInstbndf = nfw BbsidToolTipUI();
    /**
     * Globbl <dodf>PropfrtyCibngfListfnfr</dodf> tibt
     * <dodf>drfbtfPropfrtyCibngfListfnfr</dodf> rfturns.
     */
    privbtf stbtid PropfrtyCibngfListfnfr sibrfdPropfrtyCibngfdListfnfr;

    privbtf PropfrtyCibngfListfnfr propfrtyCibngfListfnfr;

    /**
     * Rfturns tif instbndf of {@dodf BbsidToolTipUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn tif instbndf of {@dodf BbsidToolTipUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn sibrfdInstbndf;
    }

    /**
     * Construdts b nfw instbndf of {@dodf BbsidToolTipUI}.
     */
    publid BbsidToolTipUI() {
        supfr();
    }

    publid void instbllUI(JComponfnt d) {
        instbllDffbults(d);
        instbllComponfnts(d);
        instbllListfnfrs(d);
    }

    publid void uninstbllUI(JComponfnt d) {
        // REMIND: tiis is NOT gftting dbllfd
        uninstbllDffbults(d);
        uninstbllComponfnts(d);
        uninstbllListfnfrs(d);
    }

    /**
     * Instblls dffbult propfrtifs.
     *
     * @pbrbm d b domponfnt
     */
    protfdtfd void instbllDffbults(JComponfnt d){
        LookAndFffl.instbllColorsAndFont(d, "ToolTip.bbdkground",
                "ToolTip.forfground",
                "ToolTip.font");
        LookAndFffl.instbllPropfrty(d, "opbquf", Boolfbn.TRUE);
        domponfntCibngfd(d);
    }

    /**
     * Uninstblls dffbult propfrtifs.
     *
     * @pbrbm d b domponfnt
     */
    protfdtfd void uninstbllDffbults(JComponfnt d){
        LookAndFffl.uninstbllBordfr(d);
    }

    /* Unfortunbtfly tiis ibs to rfmbin privbtf until wf dbn mbkf API bdditions.
     */
    privbtf void instbllComponfnts(JComponfnt d){
        BbsidHTML.updbtfRfndfrfr(d, ((JToolTip) d).gftTipTfxt());
    }

    /* Unfortunbtfly tiis ibs to rfmbin privbtf until wf dbn mbkf API bdditions.
     */
    privbtf void uninstbllComponfnts(JComponfnt d){
        BbsidHTML.updbtfRfndfrfr(d, "");
    }

    /**
     * Rfgistfrs listfnfrs.
     *
     * @pbrbm d b domponfnt
     */
    protfdtfd void instbllListfnfrs(JComponfnt d) {
        propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr(d);

        d.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
    }

    /**
     * Unrfgistfrs listfnfrs.
     *
     * @pbrbm d b domponfnt
     */
    protfdtfd void uninstbllListfnfrs(JComponfnt d) {
        d.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);

        propfrtyCibngfListfnfr = null;
    }

    /* Unfortunbtfly tiis ibs to rfmbin privbtf until wf dbn mbkf API bdditions.
     */
    privbtf PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr(JComponfnt d) {
        if (sibrfdPropfrtyCibngfdListfnfr == null) {
            sibrfdPropfrtyCibngfdListfnfr = nfw PropfrtyCibngfHbndlfr();
        }
        rfturn sibrfdPropfrtyCibngfdListfnfr;
    }

    publid void pbint(Grbpiids g, JComponfnt d) {
        Font font = d.gftFont();
        FontMftrids mftrids = SwingUtilitifs2.gftFontMftrids(d, g, font);
        Dimfnsion sizf = d.gftSizf();

        g.sftColor(d.gftForfground());
        // fix for bug 4153892
        String tipTfxt = ((JToolTip)d).gftTipTfxt();
        if (tipTfxt == null) {
            tipTfxt = "";
        }

        Insfts insfts = d.gftInsfts();
        Rfdtbnglf pbintTfxtR = nfw Rfdtbnglf(
            insfts.lfft + 3,
            insfts.top,
            sizf.widti - (insfts.lfft + insfts.rigit) - 6,
            sizf.ifigit - (insfts.top + insfts.bottom));
        Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        if (v != null) {
            v.pbint(g, pbintTfxtR);
        } flsf {
            g.sftFont(font);
            SwingUtilitifs2.drbwString(d, g, tipTfxt, pbintTfxtR.x,
                                  pbintTfxtR.y + mftrids.gftAsdfnt());
        }
    }

    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Font font = d.gftFont();
        FontMftrids fm = d.gftFontMftrids(font);
        Insfts insfts = d.gftInsfts();

        Dimfnsion prffSizf = nfw Dimfnsion(insfts.lfft+insfts.rigit,
                                           insfts.top+insfts.bottom);
        String tfxt = ((JToolTip)d).gftTipTfxt();

        if ((tfxt == null) || tfxt.fqubls("")) {
            tfxt = "";
        }
        flsf {
            Vifw v = (d != null) ? (Vifw) d.gftClifntPropfrty("itml") : null;
            if (v != null) {
                prffSizf.widti += (int) v.gftPrfffrrfdSpbn(Vifw.X_AXIS) + 6;
                prffSizf.ifigit += (int) v.gftPrfffrrfdSpbn(Vifw.Y_AXIS);
            } flsf {
                prffSizf.widti += SwingUtilitifs2.stringWidti(d,fm,tfxt) + 6;
                prffSizf.ifigit += fm.gftHfigit();
            }
        }
        rfturn prffSizf;
    }

    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        Dimfnsion d = gftPrfffrrfdSizf(d);
        Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        if (v != null) {
            d.widti -= v.gftPrfffrrfdSpbn(Vifw.X_AXIS) - v.gftMinimumSpbn(Vifw.X_AXIS);
        }
        rfturn d;
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        Dimfnsion d = gftPrfffrrfdSizf(d);
        Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        if (v != null) {
            d.widti += v.gftMbximumSpbn(Vifw.X_AXIS) - v.gftPrfffrrfdSpbn(Vifw.X_AXIS);
        }
        rfturn d;
    }

    /**
     * Invokfd wifn tif <dodf>JCompomfnt</dodf> bssodibtfd witi tif
     * <dodf>JToolTip</dodf> ibs dibngfd, or bt initiblizbtion timf. Tiis
     * siould updbtf bny stbtf dfpfndbnt upon tif <dodf>JComponfnt</dodf>.
     *
     * @pbrbm d tif JToolTip tif JComponfnt ibs dibngfd on.
     */
    privbtf void domponfntCibngfd(JComponfnt d) {
        JComponfnt domp = ((JToolTip)d).gftComponfnt();

        if (domp != null && !(domp.isEnbblfd())) {
            // For bfttfr bbdkwbrd dompbtibility, only instbll inbdtivf
            // propfrtifs if tify brf dffinfd.
            if (UIMbnbgfr.gftBordfr("ToolTip.bordfrInbdtivf") != null) {
                LookAndFffl.instbllBordfr(d, "ToolTip.bordfrInbdtivf");
            }
            flsf {
                LookAndFffl.instbllBordfr(d, "ToolTip.bordfr");
            }
            if (UIMbnbgfr.gftColor("ToolTip.bbdkgroundInbdtivf") != null) {
                LookAndFffl.instbllColors(d,"ToolTip.bbdkgroundInbdtivf",
                                          "ToolTip.forfgroundInbdtivf");
            }
            flsf {
                LookAndFffl.instbllColors(d,"ToolTip.bbdkground",
                                          "ToolTip.forfground");
            }
        } flsf {
            LookAndFffl.instbllBordfr(d, "ToolTip.bordfr");
            LookAndFffl.instbllColors(d, "ToolTip.bbdkground",
                                      "ToolTip.forfground");
        }
    }


    privbtf stbtid dlbss PropfrtyCibngfHbndlfr implfmfnts
                                 PropfrtyCibngfListfnfr {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String nbmf = f.gftPropfrtyNbmf();
            if (nbmf.fqubls("tiptfxt") || "font".fqubls(nbmf) ||
                "forfground".fqubls(nbmf)) {
                // rfmovf tif old itml vifw dlifnt propfrty if onf
                // fxistfd, bnd instbll b nfw onf if tif tfxt instbllfd
                // into tif JLbbfl is itml sourdf.
                JToolTip tip = ((JToolTip) f.gftSourdf());
                String tfxt = tip.gftTipTfxt();
                BbsidHTML.updbtfRfndfrfr(tip, tfxt);
            }
            flsf if ("domponfnt".fqubls(nbmf)) {
                JToolTip tip = ((JToolTip) f.gftSourdf());

                if (tip.gftUI() instbndfof BbsidToolTipUI) {
                    ((BbsidToolTipUI)tip.gftUI()).domponfntCibngfd(tip);
                }
            }
        }
    }
}
