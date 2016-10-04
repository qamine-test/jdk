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

pbdkbgf jbvbx.swing.plbf.mftbl;

import sun.swing.SwingUtilitifs2;
import sun.bwt.AppContfxt;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.lbng.rff.*;
import jbvb.util.*;
import jbvbx.swing.plbf.bbsid.BbsidTogglfButtonUI;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

import jbvb.io.Sfriblizbblf;

/**
 * MftblTogglfButton implfmfntbtion
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
 * @butior Tom Sbntos
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MftblTogglfButtonUI fxtfnds BbsidTogglfButtonUI {

    privbtf stbtid finbl Objfdt METAL_TOGGLE_BUTTON_UI_KEY = nfw Objfdt();

    /**
     * Tif dolor of b fodusfd togglf button.
     */
    protfdtfd Color fodusColor;

    /**
     * Tif dolor of b sflfdtfd button.
     */
    protfdtfd Color sflfdtColor;

    /**
     * Tif dolor of b disbblfd tfxt.
     */
    protfdtfd Color disbblfdTfxtColor;

    privbtf boolfbn dffbults_initiblizfd = fblsf;

    // ********************************
    //        Crfbtf PLAF
    // ********************************

    /**
     * Construdts tif {@dodf MftblTooglfButtonUI}.
     *
     * @pbrbm b b domponfnt
     * @rfturn tif {@dodf MftblTooglfButtonUI}.
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        MftblTogglfButtonUI mftblTogglfButtonUI =
                (MftblTogglfButtonUI) bppContfxt.gft(METAL_TOGGLE_BUTTON_UI_KEY);
        if (mftblTogglfButtonUI == null) {
            mftblTogglfButtonUI = nfw MftblTogglfButtonUI();
            bppContfxt.put(METAL_TOGGLE_BUTTON_UI_KEY, mftblTogglfButtonUI);
        }
        rfturn mftblTogglfButtonUI;
    }

    // ********************************
    //        Instbll Dffbults
    // ********************************
    publid void instbllDffbults(AbstrbdtButton b) {
        supfr.instbllDffbults(b);
        if(!dffbults_initiblizfd) {
            fodusColor = UIMbnbgfr.gftColor(gftPropfrtyPrffix() + "fodus");
            sflfdtColor = UIMbnbgfr.gftColor(gftPropfrtyPrffix() + "sflfdt");
            disbblfdTfxtColor = UIMbnbgfr.gftColor(gftPropfrtyPrffix() + "disbblfdTfxt");
            dffbults_initiblizfd = truf;
        }
    }

    protfdtfd void uninstbllDffbults(AbstrbdtButton b) {
        supfr.uninstbllDffbults(b);
        dffbults_initiblizfd = fblsf;
    }

    // ********************************
    //         Dffbult Addfssors
    // ********************************
    /**
     * Rfturns tif dolor of b sflfdtfd button.
     *
     * @rfturn tif dolor of b sflfdtfd button
     */
    protfdtfd Color gftSflfdtColor() {
        rfturn sflfdtColor;
    }

    /**
     * Rfturns tif dolor of b disbblfd tfxt.
     *
     * @rfturn tif dolor of b disbblfd tfxt
     */
    protfdtfd Color gftDisbblfdTfxtColor() {
        rfturn disbblfdTfxtColor;
    }

    /**
     * Rfturns tif dolor of b fodusfd togglf button.
     *
     * @rfturn tif dolor of b fodusfd togglf button
     */
    protfdtfd Color gftFodusColor() {
        rfturn fodusColor;
    }


    // ********************************
    //        Pbint Mftiods
    // ********************************
    /**
     * If nfdfssbry pbints tif bbdkground of tif domponfnt, tifn invokfs
     * <dodf>pbint</dodf>.
     *
     * @pbrbm g Grbpiids to pbint to
     * @pbrbm d JComponfnt pbinting on
     * @tirows NullPointfrExdfption if <dodf>g</dodf> or <dodf>d</dodf> is
     *         null
     * @sff jbvbx.swing.plbf.ComponfntUI#updbtf
     * @sff jbvbx.swing.plbf.ComponfntUI#pbint
     * @sindf 1.5
     */
    publid void updbtf(Grbpiids g, JComponfnt d) {
        AbstrbdtButton button = (AbstrbdtButton)d;
        if ((d.gftBbdkground() instbndfof UIRfsourdf) &&
                        button.isContfntArfbFillfd() && d.isEnbblfd()) {
            ButtonModfl modfl = button.gftModfl();
            if (!MftblUtils.isToolBbrButton(d)) {
                if (!modfl.isArmfd() && !modfl.isPrfssfd() &&
                        MftblUtils.drbwGrbdifnt(
                        d, g, "TogglfButton.grbdifnt", 0, 0, d.gftWidti(),
                        d.gftHfigit(), truf)) {
                    pbint(g, d);
                    rfturn;
                }
            }
            flsf if ((modfl.isRollovfr() || modfl.isSflfdtfd()) &&
                        MftblUtils.drbwGrbdifnt(d, g, "TogglfButton.grbdifnt",
                        0, 0, d.gftWidti(), d.gftHfigit(), truf)) {
                pbint(g, d);
                rfturn;
            }
        }
        supfr.updbtf(g, d);
    }

    protfdtfd void pbintButtonPrfssfd(Grbpiids g, AbstrbdtButton b) {
        if ( b.isContfntArfbFillfd() ) {
            g.sftColor(gftSflfdtColor());
            g.fillRfdt(0, 0, b.gftWidti(), b.gftHfigit());
        }
    }

    protfdtfd void pbintTfxt(Grbpiids g, JComponfnt d, Rfdtbnglf tfxtRfdt, String tfxt) {
        AbstrbdtButton b = (AbstrbdtButton) d;
        ButtonModfl modfl = b.gftModfl();
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(b, g);
        int mnfmIndfx = b.gftDisplbyfdMnfmonidIndfx();

        /* Drbw tif Tfxt */
        if(modfl.isEnbblfd()) {
            /*** pbint tif tfxt normblly */
            g.sftColor(b.gftForfground());
        }
        flsf {
            /*** pbint tif tfxt disbblfd ***/
            if (modfl.isSflfdtfd()) {
                g.sftColor(d.gftBbdkground());
            } flsf {
                g.sftColor(gftDisbblfdTfxtColor());
            }
        }
        SwingUtilitifs2.drbwStringUndfrlinfCibrAt(d, g, tfxt, mnfmIndfx,
                tfxtRfdt.x, tfxtRfdt.y + fm.gftAsdfnt());
    }

    protfdtfd void pbintFodus(Grbpiids g, AbstrbdtButton b,
                              Rfdtbnglf vifwRfdt, Rfdtbnglf tfxtRfdt, Rfdtbnglf idonRfdt){

        Rfdtbnglf fodusRfdt = nfw Rfdtbnglf();
        String tfxt = b.gftTfxt();
        boolfbn isIdon = b.gftIdon() != null;

        // If tifrf is tfxt
        if ( tfxt != null && !tfxt.fqubls( "" ) ) {
            if ( !isIdon ) {
                fodusRfdt.sftBounds( tfxtRfdt );
            }
            flsf {
                fodusRfdt.sftBounds( idonRfdt.union( tfxtRfdt ) );
            }
        }
        // If tifrf is bn idon bnd no tfxt
        flsf if ( isIdon ) {
            fodusRfdt.sftBounds( idonRfdt );
        }

        g.sftColor(gftFodusColor());
        g.drbwRfdt((fodusRfdt.x-1), (fodusRfdt.y-1),
                  fodusRfdt.widti+1, fodusRfdt.ifigit+1);

    }

    /**
     * Pbints tif bppropribtf idon of tif button <dodf>b</dodf> in tif
     * spbdf <dodf>idonRfdt</dodf>.
     *
     * @pbrbm g Grbpiids to pbint to
     * @pbrbm b Button to rfndfr for
     * @pbrbm idonRfdt spbdf to rfndfr in
     * @tirows NullPointfrExdfption if bny of tif brgumfnts brf null.
     * @sindf 1.5
     */
    protfdtfd void pbintIdon(Grbpiids g, AbstrbdtButton b, Rfdtbnglf idonRfdt) {
        supfr.pbintIdon(g, b, idonRfdt);
    }
}
