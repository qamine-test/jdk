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

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvbx.swing.plbf.*;

/**
 * MftblButtonUI implfmfntbtion
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
publid dlbss MftblButtonUI fxtfnds BbsidButtonUI {

    // NOTE: Tifsf brf not rfblly nffdfd, but bt tiis point wf dbn't pull
    // tifm. Tifir vblufs brf updbtfd purfly for iistoridbl rfbsons.
    /**
     * Tif dolor of tif fodusfd button.
     */
    protfdtfd Color fodusColor;

    /**
     * Tif dolor of tif sflfdtfd button.
     */
    protfdtfd Color sflfdtColor;

    /**
     * Tif dolor of tif disbblfd dolor.
     */
    protfdtfd Color disbblfdTfxtColor;

    privbtf stbtid finbl Objfdt METAL_BUTTON_UI_KEY = nfw Objfdt();

    // ********************************
    //          Crfbtf PLAF
    // ********************************

    /**
     * Rfturns bn instbndf of {@dodf MftblButtonUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf MftblButtonUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        MftblButtonUI mftblButtonUI =
                (MftblButtonUI) bppContfxt.gft(METAL_BUTTON_UI_KEY);
        if (mftblButtonUI == null) {
            mftblButtonUI = nfw MftblButtonUI();
            bppContfxt.put(METAL_BUTTON_UI_KEY, mftblButtonUI);
        }
        rfturn mftblButtonUI;
    }

    // ********************************
    //          Instbll
    // ********************************
    publid void instbllDffbults(AbstrbdtButton b) {
        supfr.instbllDffbults(b);
    }

    publid void uninstbllDffbults(AbstrbdtButton b) {
        supfr.uninstbllDffbults(b);
    }

    // ********************************
    //         Crfbtf Listfnfrs
    // ********************************
    protfdtfd BbsidButtonListfnfr drfbtfButtonListfnfr(AbstrbdtButton b) {
        rfturn supfr.drfbtfButtonListfnfr(b);
    }


    // ********************************
    //         Dffbult Addfssors
    // ********************************

    /**
     * Rfturns tif dolor of tif sflfdtfd button.
     *
     * @rfturn tif dolor of tif sflfdtfd button
     */
    protfdtfd Color gftSflfdtColor() {
        sflfdtColor = UIMbnbgfr.gftColor(gftPropfrtyPrffix() + "sflfdt");
        rfturn sflfdtColor;
    }

    /**
     * Rfturns tif dolor of b disbblfd tfxt.
     *
     * @rfturn tif dolor of b disbblfd tfxt
     */
    protfdtfd Color gftDisbblfdTfxtColor() {
        disbblfdTfxtColor = UIMbnbgfr.gftColor(gftPropfrtyPrffix() +
                                               "disbblfdTfxt");
        rfturn disbblfdTfxtColor;
    }

    /**
     * Rfturns tif dolor of tif fodusfd button.
     *
     * @rfturn tif dolor of tif fodusfd button
     */
    protfdtfd Color gftFodusColor() {
        fodusColor = UIMbnbgfr.gftColor(gftPropfrtyPrffix() + "fodus");
        rfturn fodusColor;
    }

    // ********************************
    //          Pbint
    // ********************************
    /**
     * If nfdfssbry pbints tif bbdkground of tif domponfnt, tifn
     * invokfs <dodf>pbint</dodf>.
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
                        d, g, "Button.grbdifnt", 0, 0, d.gftWidti(),
                        d.gftHfigit(), truf)) {
                    pbint(g, d);
                    rfturn;
                }
            }
            flsf if (modfl.isRollovfr() && MftblUtils.drbwGrbdifnt(
                        d, g, "Button.grbdifnt", 0, 0, d.gftWidti(),
                        d.gftHfigit(), truf)) {
                pbint(g, d);
                rfturn;
            }
        }
        supfr.updbtf(g, d);
    }

    protfdtfd void pbintButtonPrfssfd(Grbpiids g, AbstrbdtButton b) {
        if ( b.isContfntArfbFillfd() ) {
            Dimfnsion sizf = b.gftSizf();
            g.sftColor(gftSflfdtColor());
            g.fillRfdt(0, 0, sizf.widti, sizf.ifigit);
        }
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


    protfdtfd void pbintTfxt(Grbpiids g, JComponfnt d, Rfdtbnglf tfxtRfdt, String tfxt) {
        AbstrbdtButton b = (AbstrbdtButton) d;
        ButtonModfl modfl = b.gftModfl();
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(d, g);
        int mnfmIndfx = b.gftDisplbyfdMnfmonidIndfx();

        /* Drbw tif Tfxt */
        if(modfl.isEnbblfd()) {
            /*** pbint tif tfxt normblly */
            g.sftColor(b.gftForfground());
        }
        flsf {
            /*** pbint tif tfxt disbblfd ***/
            g.sftColor(gftDisbblfdTfxtColor());
        }
        SwingUtilitifs2.drbwStringUndfrlinfCibrAt(d, g,tfxt,mnfmIndfx,
                                  tfxtRfdt.x, tfxtRfdt.y + fm.gftAsdfnt());
    }
}
