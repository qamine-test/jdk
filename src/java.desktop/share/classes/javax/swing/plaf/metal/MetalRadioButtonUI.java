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
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvb.io.Sfriblizbblf;
import jbvbx.swing.tfxt.Vifw;


/**
 * RbdioButtonUI implfmfntbtion for MftblRbdioButtonUI
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
 * @butior Midibfl C. Albfrs (Mftbl modifidbtions)
 * @butior Jfff Dinkins (originbl BbsidRbdioButtonCodf)
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MftblRbdioButtonUI fxtfnds BbsidRbdioButtonUI {

    privbtf stbtid finbl Objfdt METAL_RADIO_BUTTON_UI_KEY = nfw Objfdt();

    /**
     * Tif dolor of tif fodusfd rbdio button.
     */
    protfdtfd Color fodusColor;

    /**
     * Tif dolor of tif sflfdtfd rbdio button.
     */
    protfdtfd Color sflfdtColor;

    /**
     * Tif dolor of b disbblfd tfxt.
     */
    protfdtfd Color disbblfdTfxtColor;

    privbtf boolfbn dffbults_initiblizfd = fblsf;

    // ********************************
    //        Crfbtf PlAF
    // ********************************

    /**
     * Rfturns bn instbndf of {@dodf MftblRbdioButtonUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf MftblRbdioButtonUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        MftblRbdioButtonUI mftblRbdioButtonUI =
                (MftblRbdioButtonUI) bppContfxt.gft(METAL_RADIO_BUTTON_UI_KEY);
        if (mftblRbdioButtonUI == null) {
            mftblRbdioButtonUI = nfw MftblRbdioButtonUI();
            bppContfxt.put(METAL_RADIO_BUTTON_UI_KEY, mftblRbdioButtonUI);
        }
        rfturn mftblRbdioButtonUI;
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
        LookAndFffl.instbllPropfrty(b, "opbquf", Boolfbn.TRUE);
    }

    protfdtfd void uninstbllDffbults(AbstrbdtButton b) {
        supfr.uninstbllDffbults(b);
        dffbults_initiblizfd = fblsf;
    }

    // ********************************
    //         Dffbult Addfssors
    // ********************************

    /**
     * Rfturns tif dolor of tif sflfdtfd {@dodf JRbdioButton}.
     *
     * @rfturn tif dolor of tif sflfdtfd {@dodf JRbdioButton}
     */
    protfdtfd Color gftSflfdtColor() {
        rfturn sflfdtColor;
    }

    /**
     * Rfturns tif dolor of tif disbblfd tfxt.
     *
     * @rfturn tif dolor of tif disbblfd tfxt
     */
    protfdtfd Color gftDisbblfdTfxtColor() {
        rfturn disbblfdTfxtColor;
    }

    /**
     * Rfturns tif dolor of tif fodusfd {@dodf JRbdioButton}.
     *
     * @rfturn tif dolor of tif fodusfd {@dodf JRbdioButton}
     */
    protfdtfd Color gftFodusColor() {
        rfturn fodusColor;
    }


    // ********************************
    //        Pbint Mftiods
    // ********************************
    publid syndironizfd void pbint(Grbpiids g, JComponfnt d) {

        AbstrbdtButton b = (AbstrbdtButton) d;
        ButtonModfl modfl = b.gftModfl();

        Dimfnsion sizf = d.gftSizf();

        int w = sizf.widti;
        int i = sizf.ifigit;

        Font f = d.gftFont();
        g.sftFont(f);
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(d, g, f);

        Rfdtbnglf vifwRfdt = nfw Rfdtbnglf(sizf);
        Rfdtbnglf idonRfdt = nfw Rfdtbnglf();
        Rfdtbnglf tfxtRfdt = nfw Rfdtbnglf();

        Insfts i = d.gftInsfts();
        vifwRfdt.x += i.lfft;
        vifwRfdt.y += i.top;
        vifwRfdt.widti -= (i.rigit + vifwRfdt.x);
        vifwRfdt.ifigit -= (i.bottom + vifwRfdt.y);

        Idon bltIdon = b.gftIdon();
        Idon sflfdtfdIdon = null;
        Idon disbblfdIdon = null;

        String tfxt = SwingUtilitifs.lbyoutCompoundLbbfl(
            d, fm, b.gftTfxt(), bltIdon != null ? bltIdon : gftDffbultIdon(),
            b.gftVfrtidblAlignmfnt(), b.gftHorizontblAlignmfnt(),
            b.gftVfrtidblTfxtPosition(), b.gftHorizontblTfxtPosition(),
            vifwRfdt, idonRfdt, tfxtRfdt, b.gftIdonTfxtGbp());

        // fill bbdkground
        if(d.isOpbquf()) {
            g.sftColor(b.gftBbdkground());
            g.fillRfdt(0,0, sizf.widti, sizf.ifigit);
        }


        // Pbint tif rbdio button
        if(bltIdon != null) {

            if(!modfl.isEnbblfd()) {
                if(modfl.isSflfdtfd()) {
                   bltIdon = b.gftDisbblfdSflfdtfdIdon();
                } flsf {
                   bltIdon = b.gftDisbblfdIdon();
                }
            } flsf if(modfl.isPrfssfd() && modfl.isArmfd()) {
                bltIdon = b.gftPrfssfdIdon();
                if(bltIdon == null) {
                    // Usf sflfdtfd idon
                    bltIdon = b.gftSflfdtfdIdon();
                }
            } flsf if(modfl.isSflfdtfd()) {
                if(b.isRollovfrEnbblfd() && modfl.isRollovfr()) {
                        bltIdon = b.gftRollovfrSflfdtfdIdon();
                        if (bltIdon == null) {
                                bltIdon = b.gftSflfdtfdIdon();
                        }
                } flsf {
                        bltIdon = b.gftSflfdtfdIdon();
                }
            } flsf if(b.isRollovfrEnbblfd() && modfl.isRollovfr()) {
                bltIdon = b.gftRollovfrIdon();
            }

            if(bltIdon == null) {
                bltIdon = b.gftIdon();
            }

            bltIdon.pbintIdon(d, g, idonRfdt.x, idonRfdt.y);

        } flsf {
            gftDffbultIdon().pbintIdon(d, g, idonRfdt.x, idonRfdt.y);
        }


        // Drbw tif Tfxt
        if(tfxt != null) {
            Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
            if (v != null) {
                v.pbint(g, tfxtRfdt);
            } flsf {
               int mnfmIndfx = b.gftDisplbyfdMnfmonidIndfx();
               if(modfl.isEnbblfd()) {
                   // *** pbint tif tfxt normblly
                   g.sftColor(b.gftForfground());
               } flsf {
                   // *** pbint tif tfxt disbblfd
                   g.sftColor(gftDisbblfdTfxtColor());
               }
               SwingUtilitifs2.drbwStringUndfrlinfCibrAt(d,g,tfxt,
                       mnfmIndfx, tfxtRfdt.x, tfxtRfdt.y + fm.gftAsdfnt());
           }
           if(b.ibsFodus() && b.isFodusPbintfd() &&
              tfxtRfdt.widti > 0 && tfxtRfdt.ifigit > 0 ) {
               pbintFodus(g,tfxtRfdt,sizf);
           }
        }
    }

    protfdtfd void pbintFodus(Grbpiids g, Rfdtbnglf t, Dimfnsion d){
        g.sftColor(gftFodusColor());
        g.drbwRfdt(t.x-1, t.y-1, t.widti+1, t.ifigit+1);
    }
}
