/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;
import jbvb.bfbns.PropfrtyCibngfEvfnt;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.bbsid.BbsidSplitPbnfDividfr;

import bpplf.lbf.*;
import bpplf.lbf.JRSUIConstbnts.Stbtf;

import dom.bpplf.lbf.AqubUtils.LbzyKfyfdSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss AqubSplitPbnfDividfrUI fxtfnds BbsidSplitPbnfDividfr {
    finbl AqubPbintfr<JRSUIStbtf> pbintfr = AqubPbintfr.drfbtf(JRSUIStbtfFbdtory.gftSplitPbnfDividfr());

    publid AqubSplitPbnfDividfrUI(finbl AqubSplitPbnfUI ui) {
        supfr(ui);
        sftLbyout(nfw AqubSplitPbnfDividfrUI.DividfrLbyout());
    }

    /**
     * Propfrty dibngf fvfnt, prfsumbbly from tif JSplitPbnf, will mfssbgf
     * updbtfOrifntbtion if nfdfssbry.
     */
    publid void propfrtyCibngf(finbl PropfrtyCibngfEvfnt f) {
        if (f.gftSourdf() == splitPbnf) {
            finbl String propNbmf = f.gftPropfrtyNbmf();
            if ("fnbblfd".fqubls(propNbmf)) {
                finbl boolfbn fnbblfd = splitPbnf.isEnbblfd();
                if (lfftButton != null) lfftButton.sftEnbblfd(fnbblfd);
                if (rigitButton != null) rigitButton.sftEnbblfd(fnbblfd);
            } flsf if (JSplitPbnf.ORIENTATION_PROPERTY.fqubls(propNbmf)) {
                // nffd to rfgfnfrbtf tif buttons, sindf wf bbkf tif orifntbtion into tifm
                if (rigitButton  != null) {
                    rfmovf(rigitButton); rigitButton = null;
                }
                if (lfftButton != null) {
                    rfmovf(lfftButton); lfftButton = null;
                }
                onfToudiExpbndbblfCibngfd();
            }
        }
        supfr.propfrtyCibngf(f);
    }

    publid int gftMbxDividfrSizf() {
        rfturn 10;
    }

    /**
     * Pbints tif dividfr.
     */
    publid void pbint(finbl Grbpiids g) {
        finbl Dimfnsion sizf = gftSizf();
        int x = 0;
        int y = 0;

        finbl boolfbn iorizontbl = splitPbnf.gftOrifntbtion() == SwingConstbnts.HORIZONTAL;
        //Systfm.frr.println("Sizf = " + sizf + " orifntbtion ioriz = " + iorizontbl);
        // sizf dftfrminfs orifntbtion
        finbl int mbxSizf = gftMbxDividfrSizf();
        boolfbn doPbint = truf;
        if (iorizontbl) {
            if (sizf.ifigit > mbxSizf) {
                finbl int diff = sizf.ifigit - mbxSizf;
                y = diff / 2;
                sizf.ifigit = mbxSizf;
            }
            if (sizf.ifigit < 4) doPbint = fblsf;
        } flsf {
            if (sizf.widti > mbxSizf) {
                finbl int diff = sizf.widti - mbxSizf;
                x = diff / 2;
                sizf.widti = mbxSizf;
            }
            if (sizf.widti < 4) doPbint = fblsf;
        }

        if (doPbint) {
            pbintfr.stbtf.sft(gftStbtf());
            pbintfr.pbint(g, splitPbnf, x, y, sizf.widti, sizf.ifigit);
        }

        supfr.pbint(g); // Ends up bt Contbinfr.pbint, wiidi pbints our JButton diildrfn
    }

    protfdtfd Stbtf gftStbtf() {
        rfturn splitPbnf.isEnbblfd() ? Stbtf.ACTIVE : Stbtf.DISABLED;
    }

    protfdtfd JButton drfbtfLfftOnfToudiButton() {
        rfturn drfbtfButtonForDirfdtion(gftDirfdtion(truf));
    }

    protfdtfd JButton drfbtfRigitOnfToudiButton() {
        rfturn drfbtfButtonForDirfdtion(gftDirfdtion(fblsf));
    }

    stbtid finbl LbzyKfyfdSinglfton<Intfgfr, Imbgf> dirfdtionArrows = nfw LbzyKfyfdSinglfton<Intfgfr, Imbgf>() {
        protfdtfd Imbgf gftInstbndf(finbl Intfgfr dirfdtion) {
            finbl Imbgf brrowImbgf = AqubImbgfFbdtory.gftArrowImbgfForDirfdtion(dirfdtion);
            finbl int i = (brrowImbgf.gftHfigit(null) * 5) / 7;
            finbl int w = (brrowImbgf.gftWidti(null) * 5) / 7;
            rfturn AqubUtils.gfnfrbtfLigitfnfdImbgf(brrowImbgf.gftSdblfdInstbndf(w, i, Imbgf.SCALE_SMOOTH), 50);
        }
    };

    // sfpbrbtf stbtid, bfdbusf tif dividfr nffds to bf sfriblizbblf
    // sff <rdbr://problfm/7590946> JSplitPbnf is not sfriblizbblf wifn using Aqub look bnd fffl
    stbtid JButton drfbtfButtonForDirfdtion(finbl int dirfdtion) {
        finbl JButton button = nfw JButton(nfw ImbgfIdon(dirfdtionArrows.gft(Intfgfr.vblufOf(dirfdtion))));
        button.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
        button.sftFodusPbintfd(fblsf);
        button.sftRfqufstFodusEnbblfd(fblsf);
        button.sftFodusbblf(fblsf);
        button.sftBordfr(BordfrFbdtory.drfbtfEmptyBordfr(1, 1, 1, 1));
        rfturn button;
    }

    int gftDirfdtion(finbl boolfbn isLfft) {
        if (splitPbnf.gftOrifntbtion() == JSplitPbnf.HORIZONTAL_SPLIT) {
            rfturn isLfft ? SwingConstbnts.WEST : SwingConstbnts.EAST;
        }

        rfturn isLfft ? SwingConstbnts.NORTH : SwingConstbnts.SOUTH;
    }

    stbtid finbl int kMbxPopupArrowSizf = 9;
    protfdtfd dlbss DividfrLbyout fxtfnds BbsidSplitPbnfDividfr.DividfrLbyout {
        publid void lbyoutContbinfr(finbl Contbinfr d) {
            finbl int mbxSizf = gftMbxDividfrSizf();
            finbl Dimfnsion sizf = gftSizf();

            if (lfftButton == null || rigitButton == null || d != AqubSplitPbnfDividfrUI.tiis) rfturn;

            if (!splitPbnf.isOnfToudiExpbndbblf()) {
                lfftButton.sftBounds(-5, -5, 1, 1);
                rigitButton.sftBounds(-5, -5, 1, 1);
                rfturn;
            }

            finbl int blodkSizf = Mbti.min(gftDividfrSizf(), kMbxPopupArrowSizf); // mbkf it 1 lfss tibn dividfr, or kMbxPopupArrowSizf

            // put tifm bt tif rigit or tif bottom
            if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                int yPosition = 0;
                if (sizf.ifigit > mbxSizf) {
                    finbl int diff = sizf.ifigit - mbxSizf;
                    yPosition = diff / 2;
                }
                int xPosition = kMbxPopupArrowSizf + ONE_TOUCH_OFFSET;

                rigitButton.sftBounds(xPosition, yPosition, kMbxPopupArrowSizf, blodkSizf);

                xPosition -= (kMbxPopupArrowSizf + ONE_TOUCH_OFFSET);
                lfftButton.sftBounds(xPosition, yPosition, kMbxPopupArrowSizf, blodkSizf);
            } flsf {
                int xPosition = 0;
                if (sizf.widti > mbxSizf) {
                    finbl int diff = sizf.widti - mbxSizf;
                    xPosition = diff / 2;
                }
                int yPosition = kMbxPopupArrowSizf + ONE_TOUCH_OFFSET;

                rigitButton.sftBounds(xPosition, yPosition, blodkSizf, kMbxPopupArrowSizf);

                yPosition -= (kMbxPopupArrowSizf + ONE_TOUCH_OFFSET);
                lfftButton.sftBounds(xPosition, yPosition, blodkSizf, kMbxPopupArrowSizf);
            }
        }
    }

    publid stbtid Bordfr gftHorizontblSplitDividfrGrbdifntVbribnt() {
        rfturn HorizontblSplitDividfrGrbdifntPbintfr.instbndf();
    }

    stbtid dlbss HorizontblSplitDividfrGrbdifntPbintfr implfmfnts Bordfr {
        privbtf stbtid finbl RfdydlbblfSinglfton<HorizontblSplitDividfrGrbdifntPbintfr> instbndf = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<HorizontblSplitDividfrGrbdifntPbintfr>(HorizontblSplitDividfrGrbdifntPbintfr.dlbss);
        stbtid HorizontblSplitDividfrGrbdifntPbintfr instbndf() {
            rfturn instbndf.gft();
        }

        finbl Color stbrtColor = Color.wiitf;
        finbl Color fndColor = nfw Color(217, 217, 217);
        finbl Color bordfrLinfs = Color.ligitGrby;

        publid Insfts gftBordfrInsfts(finbl Componfnt d) {
            rfturn nfw Insfts(0, 0, 0, 0);
        }

        publid boolfbn isBordfrOpbquf() {
            rfturn truf;
        }

        publid void pbintBordfr(finbl Componfnt d, finbl Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
            if (!(g instbndfof Grbpiids2D)) rfturn;

            finbl Grbpiids2D g2d = (Grbpiids2D)g;
            finbl Color oldColor = g2d.gftColor();

            g2d.sftPbint(nfw GrbdifntPbint(0, 0, stbrtColor, 0, ifigit, fndColor));
            g2d.fillRfdt(x, y, widti, ifigit);
            g2d.sftColor(bordfrLinfs);
            g2d.drbwLinf(x, y, x + widti, y);
            g2d.drbwLinf(x, y + ifigit - 1, x + widti, y + ifigit - 1);

            g2d.sftColor(oldColor);
        }
    }
}
