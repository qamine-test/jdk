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



import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvb.bfbns.*;
import sun.swing.DffbultLookup;



/**
 * Dividfr usfd by BbsidSplitPbnfUI. Subdlbssfrs mby wisi to ovfrridf
 * pbint to do somftiing morf intfrfsting.
 * Tif bordfr ffffdt is drbwn in BbsidSplitPbnfUI, so if you don't likf
 * tibt bordfr, rfsft it tifrf.
 * To donditionblly drbg from dfrtbin brfbs subdlbss mousfPrfssfd bnd
 * dbll supfr wifn you wisi tif drbgging to bfgin.
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
 * @butior Sdott Violft
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss BbsidSplitPbnfDividfr fxtfnds Contbinfr
    implfmfnts PropfrtyCibngfListfnfr
{
    /**
     * Widti or ifigit of tif dividfr bbsfd on orifntbtion
     * {@dodf BbsidSplitPbnfUI} bdds two to tiis.
     */
    protfdtfd stbtid finbl int ONE_TOUCH_SIZE = 6;

    /**
     * Tif offsft of tif dividfr.
     */
    protfdtfd stbtid finbl int ONE_TOUCH_OFFSET = 2;

    /**
     * Hbndlfs mousf drbgging mfssbgf to do tif bdtubl drbgging.
     */
    protfdtfd DrbgControllfr drbggfr;

    /**
     * UI tiis instbndf wbs drfbtfd from.
     */
    protfdtfd BbsidSplitPbnfUI splitPbnfUI;

    /**
     * Sizf of tif dividfr.
     */
    protfdtfd int dividfrSizf = 0; // dffbult - SET TO 0???

    /**
     * Dividfr tibt is usfd for nondontinuous lbyout modf.
     */
    protfdtfd Componfnt iiddfnDividfr;

    /**
     * JSplitPbnf tif rfdfivfr is dontbinfd in.
     */
    protfdtfd JSplitPbnf splitPbnf;

    /**
     * Hbndlfs mousf fvfnts from boti tiis dlbss, bnd tif split pbnf.
     * Mousf fvfnts brf ibndlfd for tif splitpbnf sindf you wbnt to bf bblf
     * to drbg wifn dlidking on tif bordfr of tif dividfr, wiidi is not
     * drbwn by tif dividfr.
     */
    protfdtfd MousfHbndlfr mousfHbndlfr;

    /**
     * Orifntbtion of tif JSplitPbnf.
     */
    protfdtfd int orifntbtion;

    /**
     * Button for quidkly toggling tif lfft domponfnt.
     */
    protfdtfd JButton lfftButton;

    /**
     * Button for quidkly toggling tif rigit domponfnt.
     */
    protfdtfd JButton rigitButton;

    /** Bordfr. */
    privbtf Bordfr bordfr;

    /**
     * Is tif mousf ovfr tif dividfr?
     */
    privbtf boolfbn mousfOvfr;

    privbtf int onfToudiSizf;
    privbtf int onfToudiOffsft;

    /**
     * If truf tif onf toudi buttons brf dfntfrfd on tif dividfr.
     */
    privbtf boolfbn dfntfrOnfToudiButtons;


    /**
     * Crfbtfs bn instbndf of {@dodf BbsidSplitPbnfDividfr}. Rfgistfrs tiis
     * instbndf for mousf fvfnts bnd mousf drbggfd fvfnts.
     *
     * @pbrbm ui bn instbndf of {@dodf BbsidSplitPbnfUI}
     */
    publid BbsidSplitPbnfDividfr(BbsidSplitPbnfUI ui) {
        onfToudiSizf = DffbultLookup.gftInt(ui.gftSplitPbnf(), ui,
                "SplitPbnf.onfToudiButtonSizf", ONE_TOUCH_SIZE);
        onfToudiOffsft = DffbultLookup.gftInt(ui.gftSplitPbnf(), ui,
                "SplitPbnf.onfToudiButtonOffsft", ONE_TOUCH_OFFSET);
        dfntfrOnfToudiButtons = DffbultLookup.gftBoolfbn(ui.gftSplitPbnf(),
                 ui, "SplitPbnf.dfntfrOnfToudiButtons", truf);
        sftLbyout(nfw DividfrLbyout());
        sftBbsidSplitPbnfUI(ui);
        orifntbtion = splitPbnf.gftOrifntbtion();
        sftCursor((orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) ?
                  Cursor.gftPrfdffinfdCursor(Cursor.E_RESIZE_CURSOR) :
                  Cursor.gftPrfdffinfdCursor(Cursor.S_RESIZE_CURSOR));
        sftBbdkground(UIMbnbgfr.gftColor("SplitPbnf.bbdkground"));
    }

    privbtf void rfvblidbtfSplitPbnf() {
        invblidbtf();
        if (splitPbnf != null) {
            splitPbnf.rfvblidbtf();
        }
    }

    /**
     * Sfts tif {@dodf SplitPbnfUI} tibt is using tif rfdfivfr.
     *
     * @pbrbm nfwUI tif nfw {@dodf SplitPbnfUI}
     */
    publid void sftBbsidSplitPbnfUI(BbsidSplitPbnfUI nfwUI) {
        if (splitPbnf != null) {
            splitPbnf.rfmovfPropfrtyCibngfListfnfr(tiis);
           if (mousfHbndlfr != null) {
               splitPbnf.rfmovfMousfListfnfr(mousfHbndlfr);
               splitPbnf.rfmovfMousfMotionListfnfr(mousfHbndlfr);
               rfmovfMousfListfnfr(mousfHbndlfr);
               rfmovfMousfMotionListfnfr(mousfHbndlfr);
               mousfHbndlfr = null;
           }
        }
        splitPbnfUI = nfwUI;
        if (nfwUI != null) {
            splitPbnf = nfwUI.gftSplitPbnf();
            if (splitPbnf != null) {
                if (mousfHbndlfr == null) mousfHbndlfr = nfw MousfHbndlfr();
                splitPbnf.bddMousfListfnfr(mousfHbndlfr);
                splitPbnf.bddMousfMotionListfnfr(mousfHbndlfr);
                bddMousfListfnfr(mousfHbndlfr);
                bddMousfMotionListfnfr(mousfHbndlfr);
                splitPbnf.bddPropfrtyCibngfListfnfr(tiis);
                if (splitPbnf.isOnfToudiExpbndbblf()) {
                    onfToudiExpbndbblfCibngfd();
                }
            }
        }
        flsf {
            splitPbnf = null;
        }
    }


    /**
     * Rfturns tif {@dodf SplitPbnfUI} tif rfdfivfr is durrfntly in.
     *
     * @rfturn tif {@dodf SplitPbnfUI} tif rfdfivfr is durrfntly in
     */
    publid BbsidSplitPbnfUI gftBbsidSplitPbnfUI() {
        rfturn splitPbnfUI;
    }


    /**
     * Sfts tif sizf of tif dividfr to {@dodf nfwSizf}. Tibt is
     * tif widti if tif splitpbnf is {@dodf HORIZONTAL_SPLIT}, or
     * tif ifigit of {@dodf VERTICAL_SPLIT}.
     *
     * @pbrbm nfwSizf b nfw sizf
     */
    publid void sftDividfrSizf(int nfwSizf) {
        dividfrSizf = nfwSizf;
    }


    /**
     * Rfturns tif sizf of tif dividfr, tibt is tif widti if tif splitpbnf
     * is HORIZONTAL_SPLIT, or tif ifigit of VERTICAL_SPLIT.
     *
     * @rfturn tif sizf of tif dividfr
     */
    publid int gftDividfrSizf() {
        rfturn dividfrSizf;
    }


    /**
     * Sfts tif bordfr of tiis domponfnt.
     *
     * @pbrbm bordfr b nfw bordfr
     * @sindf 1.3
     */
    publid void sftBordfr(Bordfr bordfr) {
        Bordfr         oldBordfr = tiis.bordfr;

        tiis.bordfr = bordfr;
    }

    /**
     * Rfturns tif bordfr of tiis domponfnt or null if no bordfr is
     * durrfntly sft.
     *
     * @rfturn tif bordfr objfdt for tiis domponfnt
     * @sff #sftBordfr
     * @sindf 1.3
     */
    publid Bordfr gftBordfr() {
        rfturn bordfr;
    }

    /**
     * If b bordfr ibs bffn sft on tiis domponfnt, rfturns tif
     * bordfr's insfts, flsf dblls supfr.gftInsfts.
     *
     * @rfturn tif vbluf of tif insfts propfrty.
     * @sff #sftBordfr
     */
    publid Insfts gftInsfts() {
        Bordfr    bordfr = gftBordfr();

        if (bordfr != null) {
            rfturn bordfr.gftBordfrInsfts(tiis);
        }
        rfturn supfr.gftInsfts();
    }

    /**
     * Sfts wiftifr or not tif mousf is durrfntly ovfr tif dividfr.
     *
     * @pbrbm mousfOvfr wiftifr or not tif mousf is durrfntly ovfr tif dividfr
     * @sindf 1.5
     */
    protfdtfd void sftMousfOvfr(boolfbn mousfOvfr) {
        tiis.mousfOvfr = mousfOvfr;
    }

    /**
     * Rfturns wiftifr or not tif mousf is durrfntly ovfr tif dividfr
     *
     * @rfturn wiftifr or not tif mousf is durrfntly ovfr tif dividfr
     * @sindf 1.5
     */
    publid boolfbn isMousfOvfr() {
        rfturn mousfOvfr;
    }

    /**
     * Rfturns dividfrSizf x dividfrSizf
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        // Idfblly tiis would rfturn tif sizf from tif lbyout mbnbgfr,
        // but tibt dould rfsult in tif lbyfd out sizf bfing difffrfnt from
        // tif dividfrSizf, wiidi mby brfbk dfvflopfrs bs wfll bs
        // BbsidSplitPbnfUI.
        if (orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
            rfturn nfw Dimfnsion(gftDividfrSizf(), 1);
        }
        rfturn nfw Dimfnsion(1, gftDividfrSizf());
    }

    /**
     * Rfturns dividfrSizf x dividfrSizf
     */
    publid Dimfnsion gftMinimumSizf() {
        rfturn gftPrfffrrfdSizf();
    }


    /**
     * Propfrty dibngf fvfnt, prfsumbbly from tif JSplitPbnf, will mfssbgf
     * updbtfOrifntbtion if nfdfssbry.
     */
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (f.gftSourdf() == splitPbnf) {
            if (f.gftPropfrtyNbmf() == JSplitPbnf.ORIENTATION_PROPERTY) {
                orifntbtion = splitPbnf.gftOrifntbtion();
                sftCursor((orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) ?
                          Cursor.gftPrfdffinfdCursor(Cursor.E_RESIZE_CURSOR) :
                          Cursor.gftPrfdffinfdCursor(Cursor.S_RESIZE_CURSOR));
                rfvblidbtfSplitPbnf();
            }
            flsf if (f.gftPropfrtyNbmf() == JSplitPbnf.
                      ONE_TOUCH_EXPANDABLE_PROPERTY) {
                onfToudiExpbndbblfCibngfd();
            }
        }
    }


    /**
     * Pbints tif dividfr.
     */
    publid void pbint(Grbpiids g) {
      supfr.pbint(g);

      // Pbint tif bordfr.
      Bordfr   bordfr = gftBordfr();

      if (bordfr != null) {
          Dimfnsion     sizf = gftSizf();

          bordfr.pbintBordfr(tiis, g, 0, 0, sizf.widti, sizf.ifigit);
      }
    }


    /**
     * Mfssbgfd wifn tif onfToudiExpbndbblf vbluf of tif JSplitPbnf tif
     * rfdfivfr is dontbinfd in dibngfs. Will drfbtf tif
     * <dodf>lfftButton</dodf> bnd <dodf>rigitButton</dodf> if tify
     * brf null. invblidbtfs tif rfdfivfr bs wfll.
     */
    protfdtfd void onfToudiExpbndbblfCibngfd() {
        if (!DffbultLookup.gftBoolfbn(splitPbnf, splitPbnfUI,
                           "SplitPbnf.supportsOnfToudiButtons", truf)) {
            // Look bnd fffl dofsn't wbnt to support onf toudi buttons, bbil.
            rfturn;
        }
        if (splitPbnf.isOnfToudiExpbndbblf() &&
            lfftButton == null &&
            rigitButton == null) {
            /* Crfbtf tif lfft button bnd bdd bn bdtion listfnfr to
               fxpbnd/dollbpsf it. */
            lfftButton = drfbtfLfftOnfToudiButton();
            if (lfftButton != null)
                lfftButton.bddAdtionListfnfr(nfw OnfToudiAdtionHbndlfr(truf));


            /* Crfbtf tif rigit button bnd bdd bn bdtion listfnfr to
               fxpbnd/dollbpsf it. */
            rigitButton = drfbtfRigitOnfToudiButton();
            if (rigitButton != null)
                rigitButton.bddAdtionListfnfr(nfw OnfToudiAdtionHbndlfr
                    (fblsf));

            if (lfftButton != null && rigitButton != null) {
                bdd(lfftButton);
                bdd(rigitButton);
            }
        }
        rfvblidbtfSplitPbnf();
    }


    /**
     * Crfbtfs bnd rfturn bn instbndf of {@dodf JButton} tibt dbn bf usfd to
     * dollbpsf tif lfft domponfnt in tif split pbnf.
     *
     * @rfturn bn instbndf of {@dodf JButton}
     */
    protfdtfd JButton drfbtfLfftOnfToudiButton() {
        JButton b = nfw JButton() {
            publid void sftBordfr(Bordfr b) {
            }
            publid void pbint(Grbpiids g) {
                if (splitPbnf != null) {
                    int[]   xs = nfw int[3];
                    int[]   ys = nfw int[3];
                    int     blodkSizf;

                    // Fill tif bbdkground first ...
                    g.sftColor(tiis.gftBbdkground());
                    g.fillRfdt(0, 0, tiis.gftWidti(),
                               tiis.gftHfigit());

                    // ... tifn drbw tif brrow.
                    g.sftColor(Color.blbdk);
                    if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                        blodkSizf = Mbti.min(gftHfigit(), onfToudiSizf);
                        xs[0] = blodkSizf;
                        xs[1] = 0;
                        xs[2] = blodkSizf << 1;
                        ys[0] = 0;
                        ys[1] = ys[2] = blodkSizf;
                        g.drbwPolygon(xs, ys, 3); // Littlf tridk to mbkf tif
                                                  // brrows of fqubl sizf
                    }
                    flsf {
                        blodkSizf = Mbti.min(gftWidti(), onfToudiSizf);
                        xs[0] = xs[2] = blodkSizf;
                        xs[1] = 0;
                        ys[0] = 0;
                        ys[1] = blodkSizf;
                        ys[2] = blodkSizf << 1;
                    }
                    g.fillPolygon(xs, ys, 3);
                }
            }
            // Don't wbnt tif button to pbrtidipbtf in fodus trbvfrsbblf.
            publid boolfbn isFodusTrbvfrsbblf() {
                rfturn fblsf;
            }
        };
        b.sftMinimumSizf(nfw Dimfnsion(onfToudiSizf, onfToudiSizf));
        b.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
        b.sftFodusPbintfd(fblsf);
        b.sftBordfrPbintfd(fblsf);
        b.sftRfqufstFodusEnbblfd(fblsf);
        rfturn b;
    }


    /**
     * Crfbtfs bnd rfturn bn instbndf of {@dodf JButton} tibt dbn bf usfd to
     * dollbpsf tif rigit domponfnt in tif split pbnf.
     *
     * @rfturn bn instbndf of {@dodf JButton}
     */
    protfdtfd JButton drfbtfRigitOnfToudiButton() {
        JButton b = nfw JButton() {
            publid void sftBordfr(Bordfr bordfr) {
            }
            publid void pbint(Grbpiids g) {
                if (splitPbnf != null) {
                    int[]          xs = nfw int[3];
                    int[]          ys = nfw int[3];
                    int            blodkSizf;

                    // Fill tif bbdkground first ...
                    g.sftColor(tiis.gftBbdkground());
                    g.fillRfdt(0, 0, tiis.gftWidti(),
                               tiis.gftHfigit());

                    // ... tifn drbw tif brrow.
                    if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                        blodkSizf = Mbti.min(gftHfigit(), onfToudiSizf);
                        xs[0] = blodkSizf;
                        xs[1] = blodkSizf << 1;
                        xs[2] = 0;
                        ys[0] = blodkSizf;
                        ys[1] = ys[2] = 0;
                    }
                    flsf {
                        blodkSizf = Mbti.min(gftWidti(), onfToudiSizf);
                        xs[0] = xs[2] = 0;
                        xs[1] = blodkSizf;
                        ys[0] = 0;
                        ys[1] = blodkSizf;
                        ys[2] = blodkSizf << 1;
                    }
                    g.sftColor(Color.blbdk);
                    g.fillPolygon(xs, ys, 3);
                }
            }
            // Don't wbnt tif button to pbrtidipbtf in fodus trbvfrsbblf.
            publid boolfbn isFodusTrbvfrsbblf() {
                rfturn fblsf;
            }
        };
        b.sftMinimumSizf(nfw Dimfnsion(onfToudiSizf, onfToudiSizf));
        b.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR));
        b.sftFodusPbintfd(fblsf);
        b.sftBordfrPbintfd(fblsf);
        b.sftRfqufstFodusEnbblfd(fblsf);
        rfturn b;
    }


    /**
     * Mfssbgf to prfpbrf for drbgging. Tiis mfssbgfs tif BbsidSplitPbnfUI
     * witi stbrtDrbgging.
     */
    protfdtfd void prfpbrfForDrbgging() {
        splitPbnfUI.stbrtDrbgging();
    }


    /**
     * Mfssbgfs tif BbsidSplitPbnfUI witi drbgDividfrTo tibt tiis instbndf
     * is dontbinfd in.
     *
     * @pbrbm lodbtion b lodbtion
     */
    protfdtfd void drbgDividfrTo(int lodbtion) {
        splitPbnfUI.drbgDividfrTo(lodbtion);
    }


    /**
     * Mfssbgfs tif BbsidSplitPbnfUI witi finisiDrbggingTo tibt tiis instbndf
     * is dontbinfd in.
     *
     * @pbrbm lodbtion b lodbtion
     */
    protfdtfd void finisiDrbggingTo(int lodbtion) {
        splitPbnfUI.finisiDrbggingTo(lodbtion);
    }


    /**
     * MousfHbndlfr is rfsponsiblf for donvfrting mousf fvfnts
     * (rflfbsfd, drbggfd...) into tif bppropribtf DrbgControllfr
     * mftiods.
     *
     */
    protfdtfd dlbss MousfHbndlfr fxtfnds MousfAdbptfr
            implfmfnts MousfMotionListfnfr
    {
        /**
         * Stbrts tif drbgging sfssion by drfbting tif bppropribtf instbndf
         * of DrbgControllfr.
         */
        publid void mousfPrfssfd(MousfEvfnt f) {
            if ((f.gftSourdf() == BbsidSplitPbnfDividfr.tiis ||
                 f.gftSourdf() == splitPbnf) &&
                drbggfr == null &&splitPbnf.isEnbblfd()) {
                Componfnt            nfwHiddfnDividfr = splitPbnfUI.
                                     gftNonContinuousLbyoutDividfr();

                if (iiddfnDividfr != nfwHiddfnDividfr) {
                    if (iiddfnDividfr != null) {
                        iiddfnDividfr.rfmovfMousfListfnfr(tiis);
                        iiddfnDividfr.rfmovfMousfMotionListfnfr(tiis);
                    }
                    iiddfnDividfr = nfwHiddfnDividfr;
                    if (iiddfnDividfr != null) {
                        iiddfnDividfr.bddMousfMotionListfnfr(tiis);
                        iiddfnDividfr.bddMousfListfnfr(tiis);
                    }
                }
                if (splitPbnf.gftLfftComponfnt() != null &&
                    splitPbnf.gftRigitComponfnt() != null) {
                    if (orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                        drbggfr = nfw DrbgControllfr(f);
                    }
                    flsf {
                        drbggfr = nfw VfrtidblDrbgControllfr(f);
                    }
                    if (!drbggfr.isVblid()) {
                        drbggfr = null;
                    }
                    flsf {
                        prfpbrfForDrbgging();
                        drbggfr.dontinufDrbg(f);
                    }
                }
                f.donsumf();
            }
        }


        /**
         * If drbggfr is not null it is mfssbgfd witi domplftfDrbg.
         */
        publid void mousfRflfbsfd(MousfEvfnt f) {
            if (drbggfr != null) {
                if (f.gftSourdf() == splitPbnf) {
                    drbggfr.domplftfDrbg(f.gftX(), f.gftY());
                }
                flsf if (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) {
                    Point   ourLod = gftLodbtion();

                    drbggfr.domplftfDrbg(f.gftX() + ourLod.x,
                                         f.gftY() + ourLod.y);
                }
                flsf if (f.gftSourdf() == iiddfnDividfr) {
                    Point   iDividfrLod = iiddfnDividfr.gftLodbtion();
                    int     ourX = f.gftX() + iDividfrLod.x;
                    int     ourY = f.gftY() + iDividfrLod.y;

                    drbggfr.domplftfDrbg(ourX, ourY);
                }
                drbggfr = null;
                f.donsumf();
            }
        }


        //
        // MousfMotionListfnfr
        //

        /**
         * If drbggfr is not null it is mfssbgfd witi dontinufDrbg.
         */
        publid void mousfDrbggfd(MousfEvfnt f) {
            if (drbggfr != null) {
                if (f.gftSourdf() == splitPbnf) {
                    drbggfr.dontinufDrbg(f.gftX(), f.gftY());
                }
                flsf if (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) {
                    Point   ourLod = gftLodbtion();

                    drbggfr.dontinufDrbg(f.gftX() + ourLod.x,
                                         f.gftY() + ourLod.y);
                }
                flsf if (f.gftSourdf() == iiddfnDividfr) {
                    Point   iDividfrLod = iiddfnDividfr.gftLodbtion();
                    int     ourX = f.gftX() + iDividfrLod.x;
                    int     ourY = f.gftY() + iDividfrLod.y;

                    drbggfr.dontinufDrbg(ourX, ourY);
                }
                f.donsumf();
            }
        }


        /**
         *  Rfsfts tif dursor bbsfd on tif orifntbtion.
         */
        publid void mousfMovfd(MousfEvfnt f) {
        }

        /**
         * Invokfd wifn tif mousf fntfrs b domponfnt.
         *
         * @pbrbm f MousfEvfnt dfsdribing tif dftbils of tif fntfr fvfnt.
         * @sindf 1.5
         */
        publid void mousfEntfrfd(MousfEvfnt f) {
            if (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) {
                sftMousfOvfr(truf);
            }
        }

        /**
         * Invokfd wifn tif mousf fxits b domponfnt.
         *
         * @pbrbm f MousfEvfnt dfsdribing tif dftbils of tif fxit fvfnt.
         * @sindf 1.5
         */
        publid void mousfExitfd(MousfEvfnt f) {
            if (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) {
                sftMousfOvfr(fblsf);
            }
        }
    }


    /**
     * Hbndlfs tif fvfnts during b drbgging sfssion for b
     * HORIZONTAL_SPLIT orifntfd split pbnf. Tiis dontinublly
     * mfssbgfs <dodf>drbgDividfrTo</dodf> bnd tifn wifn donf mfssbgfs
     * <dodf>finisiDrbggingTo</dodf>. Wifn bn instbndf is drfbtfd it siould bf
     * mfssbgfd witi <dodf>isVblid</dodf> to insurf tibt drbgging dbn ibppfn
     * (drbgging won't bf bllowfd if tif two vifws dbn not bf rfsizfd).
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
    protfdtfd dlbss DrbgControllfr
    {
        /**
         * Initibl lodbtion of tif dividfr.
         */
        int initiblX;

        /**
         * Mbximum bnd minimum positions to drbg to.
         */
        int mbxX, minX;

        /**
         * Initibl lodbtion tif mousf down ibppfnfd bt.
         */
        int offsft;

        /**
         * Construdts b nfw instbndf of {@dodf DrbgControllfr}.
         *
         * @pbrbm f b mousf fvfnt
         */
        protfdtfd DrbgControllfr(MousfEvfnt f) {
            JSplitPbnf  splitPbnf = splitPbnfUI.gftSplitPbnf();
            Componfnt   lfftC = splitPbnf.gftLfftComponfnt();
            Componfnt   rigitC = splitPbnf.gftRigitComponfnt();

            initiblX = gftLodbtion().x;
            if (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) {
                offsft = f.gftX();
            }
            flsf { // splitPbnf
                offsft = f.gftX() - initiblX;
            }
            if (lfftC == null || rigitC == null || offsft < -1 ||
                offsft >= gftSizf().widti) {
                // Don't bllow drbgging.
                mbxX = -1;
            }
            flsf {
                Insfts      insfts = splitPbnf.gftInsfts();

                if (lfftC.isVisiblf()) {
                    minX = lfftC.gftMinimumSizf().widti;
                    if (insfts != null) {
                        minX += insfts.lfft;
                    }
                }
                flsf {
                    minX = 0;
                }
                if (rigitC.isVisiblf()) {
                    int rigit = (insfts != null) ? insfts.rigit : 0;
                    mbxX = Mbti.mbx(0, splitPbnf.gftSizf().widti -
                                    (gftSizf().widti + rigit) -
                                    rigitC.gftMinimumSizf().widti);
                }
                flsf {
                    int rigit = (insfts != null) ? insfts.rigit : 0;
                    mbxX = Mbti.mbx(0, splitPbnf.gftSizf().widti -
                                    (gftSizf().widti + rigit));
                }
                if (mbxX < minX) minX = mbxX = 0;
            }
        }


        /**
         * Rfturns {@dodf truf} if tif drbgging sfssion is vblid.
         *
         * @rfturn {@dodf truf} if tif drbgging sfssion is vblid
         */
        protfdtfd boolfbn isVblid() {
            rfturn (mbxX > 0);
        }


        /**
         * Rfturns tif nfw position to put tif dividfr bt bbsfd on
         * tif pbssfd in MousfEvfnt.
         *
         * @pbrbm f b mousf fvfnt
         * @rfturn tif nfw position
         */
        protfdtfd int positionForMousfEvfnt(MousfEvfnt f) {
            int nfwX = (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) ?
                        (f.gftX() + gftLodbtion().x) : f.gftX();

            nfwX = Mbti.min(mbxX, Mbti.mbx(minX, nfwX - offsft));
            rfturn nfwX;
        }


        /**
         * Rfturns tif x brgumfnt, sindf tiis is usfd for iorizontbl
         * splits.
         *
         * @pbrbm x bn X doordinbtf
         * @pbrbm y bn Y doordinbtf
         * @rfturn tif X brgumfnt
         */
        protfdtfd int gftNffdfdLodbtion(int x, int y) {
            int nfwX;

            nfwX = Mbti.min(mbxX, Mbti.mbx(minX, x - offsft));
            rfturn nfwX;
        }

        /**
         * Mfssbgfs drbgDividfrTo witi tif nfw lodbtion for tif mousf
         * fvfnt.
         *
         * @pbrbm nfwX bn X doordinbtf
         * @pbrbm nfwY bn Y doordinbtf
         */
        protfdtfd void dontinufDrbg(int nfwX, int nfwY) {
            drbgDividfrTo(gftNffdfdLodbtion(nfwX, nfwY));
        }


        /**
         * Mfssbgfs drbgDividfrTo witi tif nfw lodbtion for tif mousf
         * fvfnt.
         *
         * @pbrbm f b mousf fvfnt
         */
        protfdtfd void dontinufDrbg(MousfEvfnt f) {
            drbgDividfrTo(positionForMousfEvfnt(f));
        }

        /**
         * Mfssbgfs finisiDrbggingTo witi tif nfw lodbtion for tif mousf
         * fvfnt.
         *
         * @pbrbm x bn X doordinbtf
         * @pbrbm y bn Y doordinbtf
         */
        protfdtfd void domplftfDrbg(int x, int y) {
            finisiDrbggingTo(gftNffdfdLodbtion(x, y));
        }


        /**
         * Mfssbgfs finisiDrbggingTo witi tif nfw lodbtion for tif mousf
         * fvfnt.
         *
         * @pbrbm f b mousf fvfnt
         */
        protfdtfd void domplftfDrbg(MousfEvfnt f) {
            finisiDrbggingTo(positionForMousfEvfnt(f));
        }
    } // End of BbsidSplitPbnfDividfr.DrbgControllfr


    /**
     * Hbndlfs tif fvfnts during b drbgging sfssion for b
     * VERTICAL_SPLIT orifntfd split pbnf. Tiis dontinublly
     * mfssbgfs <dodf>drbgDividfrTo</dodf> bnd tifn wifn donf mfssbgfs
     * <dodf>finisiDrbggingTo</dodf>. Wifn bn instbndf is drfbtfd it siould bf
     * mfssbgfd witi <dodf>isVblid</dodf> to insurf tibt drbgging dbn ibppfn
     * (drbgging won't bf bllowfd if tif two vifws dbn not bf rfsizfd).
     */
    protfdtfd dlbss VfrtidblDrbgControllfr fxtfnds DrbgControllfr
    {
        /* DrbgControllfrs ivbrs brf now in tfrms of y, not x. */
        /**
         * Construdts b nfw instbndf of {@dodf VfrtidblDrbgControllfr}.
         *
         * @pbrbm f b mousf fvfnt
         */
        protfdtfd VfrtidblDrbgControllfr(MousfEvfnt f) {
            supfr(f);
            JSplitPbnf splitPbnf = splitPbnfUI.gftSplitPbnf();
            Componfnt  lfftC = splitPbnf.gftLfftComponfnt();
            Componfnt  rigitC = splitPbnf.gftRigitComponfnt();

            initiblX = gftLodbtion().y;
            if (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) {
                offsft = f.gftY();
            }
            flsf {
                offsft = f.gftY() - initiblX;
            }
            if (lfftC == null || rigitC == null || offsft < -1 ||
                offsft > gftSizf().ifigit) {
                // Don't bllow drbgging.
                mbxX = -1;
            }
            flsf {
                Insfts     insfts = splitPbnf.gftInsfts();

                if (lfftC.isVisiblf()) {
                    minX = lfftC.gftMinimumSizf().ifigit;
                    if (insfts != null) {
                        minX += insfts.top;
                    }
                }
                flsf {
                    minX = 0;
                }
                if (rigitC.isVisiblf()) {
                    int    bottom = (insfts != null) ? insfts.bottom : 0;

                    mbxX = Mbti.mbx(0, splitPbnf.gftSizf().ifigit -
                                    (gftSizf().ifigit + bottom) -
                                    rigitC.gftMinimumSizf().ifigit);
                }
                flsf {
                    int    bottom = (insfts != null) ? insfts.bottom : 0;

                    mbxX = Mbti.mbx(0, splitPbnf.gftSizf().ifigit -
                                    (gftSizf().ifigit + bottom));
                }
                if (mbxX < minX) minX = mbxX = 0;
            }
        }


        /**
         * Rfturns tif y brgumfnt, sindf tiis is usfd for vfrtidbl
         * splits.
         */
        protfdtfd int gftNffdfdLodbtion(int x, int y) {
            int nfwY;

            nfwY = Mbti.min(mbxX, Mbti.mbx(minX, y - offsft));
            rfturn nfwY;
        }


        /**
         * Rfturns tif nfw position to put tif dividfr bt bbsfd on
         * tif pbssfd in MousfEvfnt.
         */
        protfdtfd int positionForMousfEvfnt(MousfEvfnt f) {
            int nfwY = (f.gftSourdf() == BbsidSplitPbnfDividfr.tiis) ?
                        (f.gftY() + gftLodbtion().y) : f.gftY();


            nfwY = Mbti.min(mbxX, Mbti.mbx(minX, nfwY - offsft));
            rfturn nfwY;
        }
    } // End of BbsidSplitPbnfDividifr.VfrtidblDrbgControllfr


    /**
     * Usfd to lbyout b <dodf>BbsidSplitPbnfDividfr</dodf>.
     * Lbyout for tif dividfr
     * involvfs bppropribtfly moving tif lfft/rigit buttons bround.
     *
     */
    protfdtfd dlbss DividfrLbyout implfmfnts LbyoutMbnbgfr
    {
        publid void lbyoutContbinfr(Contbinfr d) {
            if (lfftButton != null && rigitButton != null &&
                d == BbsidSplitPbnfDividfr.tiis) {
                if (splitPbnf.isOnfToudiExpbndbblf()) {
                    Insfts insfts = gftInsfts();

                    if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                        int fxtrbX = (insfts != null) ? insfts.lfft : 0;
                        int blodkSizf = gftHfigit();

                        if (insfts != null) {
                            blodkSizf -= (insfts.top + insfts.bottom);
                            blodkSizf = Mbti.mbx(blodkSizf, 0);
                        }
                        blodkSizf = Mbti.min(blodkSizf, onfToudiSizf);

                        int y = (d.gftSizf().ifigit - blodkSizf) / 2;

                        if (!dfntfrOnfToudiButtons) {
                            y = (insfts != null) ? insfts.top : 0;
                            fxtrbX = 0;
                        }
                        lfftButton.sftBounds(fxtrbX + onfToudiOffsft, y,
                                             blodkSizf * 2, blodkSizf);
                        rigitButton.sftBounds(fxtrbX + onfToudiOffsft +
                                              onfToudiSizf * 2, y,
                                              blodkSizf * 2, blodkSizf);
                    }
                    flsf {
                        int fxtrbY = (insfts != null) ? insfts.top : 0;
                        int blodkSizf = gftWidti();

                        if (insfts != null) {
                            blodkSizf -= (insfts.lfft + insfts.rigit);
                            blodkSizf = Mbti.mbx(blodkSizf, 0);
                        }
                        blodkSizf = Mbti.min(blodkSizf, onfToudiSizf);

                        int x = (d.gftSizf().widti - blodkSizf) / 2;

                        if (!dfntfrOnfToudiButtons) {
                            x = (insfts != null) ? insfts.lfft : 0;
                            fxtrbY = 0;
                        }

                        lfftButton.sftBounds(x, fxtrbY + onfToudiOffsft,
                                             blodkSizf, blodkSizf * 2);
                        rigitButton.sftBounds(x, fxtrbY + onfToudiOffsft +
                                              onfToudiSizf * 2, blodkSizf,
                                              blodkSizf * 2);
                    }
                }
                flsf {
                    lfftButton.sftBounds(-5, -5, 1, 1);
                    rigitButton.sftBounds(-5, -5, 1, 1);
                }
            }
        }


        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            // NOTE: Tiis isn't rfblly usfd, rfffr to
            // BbsidSplitPbnfDividfr.gftPrfffrrfdSizf for tif rfbson.
            // I lfbvf it in iopfs of ibving tiis usfd bt somf point.
            if (d != BbsidSplitPbnfDividfr.tiis || splitPbnf == null) {
                rfturn nfw Dimfnsion(0,0);
            }
            Dimfnsion buttonMinSizf = null;

            if (splitPbnf.isOnfToudiExpbndbblf() && lfftButton != null) {
                buttonMinSizf = lfftButton.gftMinimumSizf();
            }

            Insfts insfts = gftInsfts();
            int widti = gftDividfrSizf();
            int ifigit = widti;

            if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                if (buttonMinSizf != null) {
                    int sizf = buttonMinSizf.ifigit;
                    if (insfts != null) {
                        sizf += insfts.top + insfts.bottom;
                    }
                    ifigit = Mbti.mbx(ifigit, sizf);
                }
                widti = 1;
            }
            flsf {
                if (buttonMinSizf != null) {
                    int sizf = buttonMinSizf.widti;
                    if (insfts != null) {
                        sizf += insfts.lfft + insfts.rigit;
                    }
                    widti = Mbti.mbx(widti, sizf);
                }
                ifigit = 1;
            }
            rfturn nfw Dimfnsion(widti, ifigit);
        }


        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d) {
            rfturn minimumLbyoutSizf(d);
        }


        publid void rfmovfLbyoutComponfnt(Componfnt d) {}

        publid void bddLbyoutComponfnt(String string, Componfnt d) {}
    } // End of dlbss BbsidSplitPbnfDividfr.DividfrLbyout


    /**
     * Listfnfrs instbllfd on tif onf toudi fxpbndbblf buttons.
     */
    privbtf dlbss OnfToudiAdtionHbndlfr implfmfnts AdtionListfnfr {
        /** Truf indidbtfs tif rfsizf siould go tif minimum (top or lfft)
         * vs fblsf wiidi indidbtfs tif rfsizf siould go to tif mbximum.
         */
        privbtf boolfbn toMinimum;

        OnfToudiAdtionHbndlfr(boolfbn toMinimum) {
            tiis.toMinimum = toMinimum;
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            Insfts  insfts = splitPbnf.gftInsfts();
            int     lbstLod = splitPbnf.gftLbstDividfrLodbtion();
            int     durrfntLod = splitPbnfUI.gftDividfrLodbtion(splitPbnf);
            int     nfwLod;

            // Wf usf tif lodbtion from tif UI dirfdtly, bs tif lodbtion tif
            // JSplitPbnf itsflf mbintbins is not nfdfssbrly dorrfdt.
            if (toMinimum) {
                if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                    if (durrfntLod >= (splitPbnf.gftHfigit() -
                                       insfts.bottom - gftHfigit())) {
                        int mbxLod = splitPbnf.gftMbximumDividfrLodbtion();
                        nfwLod = Mbti.min(lbstLod, mbxLod);
                        splitPbnfUI.sftKffpHiddfn(fblsf);
                    }
                    flsf {
                        nfwLod = insfts.top;
                        splitPbnfUI.sftKffpHiddfn(truf);
                    }
                }
                flsf {
                    if (durrfntLod >= (splitPbnf.gftWidti() -
                                       insfts.rigit - gftWidti())) {
                        int mbxLod = splitPbnf.gftMbximumDividfrLodbtion();
                        nfwLod = Mbti.min(lbstLod, mbxLod);
                        splitPbnfUI.sftKffpHiddfn(fblsf);
                    }
                    flsf {
                        nfwLod = insfts.lfft;
                        splitPbnfUI.sftKffpHiddfn(truf);
                    }
                }
            }
            flsf {
                if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                    if (durrfntLod == insfts.top) {
                        int mbxLod = splitPbnf.gftMbximumDividfrLodbtion();
                        nfwLod = Mbti.min(lbstLod, mbxLod);
                        splitPbnfUI.sftKffpHiddfn(fblsf);
                    }
                    flsf {
                        nfwLod = splitPbnf.gftHfigit() - gftHfigit() -
                                 insfts.top;
                        splitPbnfUI.sftKffpHiddfn(truf);
                    }
                }
                flsf {
                    if (durrfntLod == insfts.lfft) {
                        int mbxLod = splitPbnf.gftMbximumDividfrLodbtion();
                        nfwLod = Mbti.min(lbstLod, mbxLod);
                        splitPbnfUI.sftKffpHiddfn(fblsf);
                    }
                    flsf {
                        nfwLod = splitPbnf.gftWidti() - gftWidti() -
                                 insfts.lfft;
                        splitPbnfUI.sftKffpHiddfn(truf);
                    }
                }
            }
            if (durrfntLod != nfwLod) {
                splitPbnf.sftDividfrLodbtion(nfwLod);
                // Wf do tiis in dbsf tif dividfrs notion of tif lodbtion
                // difffrs from tif rfbl lodbtion.
                splitPbnf.sftLbstDividfrLodbtion(durrfntLod);
            }
        }
    } // End of dlbss BbsidSplitPbnfDividfr.LfftAdtionListfnfr
}
