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
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.Vifw;

import sun.swing.*;

/**
 * BbsidMfnuItfm implfmfntbtion
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @butior Arnbud Wfbfr
 * @butior Frfdrik Lbgfrblbd
 */
publid dlbss BbsidMfnuItfmUI fxtfnds MfnuItfmUI
{
    /**
     * Tif instbndf of {@dodf JMfnuItfm}.
     */
    protfdtfd JMfnuItfm mfnuItfm = null;
    /**
     * Tif dolor of tif sflfdtion bbdkground.
     */
    protfdtfd Color sflfdtionBbdkground;
    /**
     * Tif dolor of tif sflfdtion forfground.
     */
    protfdtfd Color sflfdtionForfground;
    /**
     * Tif dolor of tif disbblfd forfground.
     */
    protfdtfd Color disbblfdForfground;
    /**
     * Tif dolor of tif bddflfrbtor forfground.
     */
    protfdtfd Color bddflfrbtorForfground;
    /**
     * Tif dolor of tif bddflfrbtor sflfdtion.
     */
    protfdtfd Color bddflfrbtorSflfdtionForfground;

    /**
     * Addflfrbtor dflimitfr string, sudi bs {@dodf '+'} in {@dodf 'Ctrl+C'}.
     * @sindf 1.7
     */
    protfdtfd String bddflfrbtorDflimitfr;

    /**
     * Tif gbp bftwffn tif tfxt bnd tif idon.
     */
    protfdtfd int dffbultTfxtIdonGbp;
    /**
     * Tif bddflfrbtor font.
     */
    protfdtfd Font bddflfrbtorFont;

    /**
     * Tif instbndf of {@dodf MousfInputListfnfr}.
     */
    protfdtfd MousfInputListfnfr mousfInputListfnfr;
    /**
     * Tif instbndf of {@dodf MfnuDrbgMousfListfnfr}.
     */
    protfdtfd MfnuDrbgMousfListfnfr mfnuDrbgMousfListfnfr;
    /**
     * Tif instbndf of {@dodf MfnuKfyListfnfr}.
     */
    protfdtfd MfnuKfyListfnfr mfnuKfyListfnfr;
    /**
     * {@dodf PropfrtyCibngfListfnfr} rfturnfd from
     * {@dodf drfbtfPropfrtyCibngfListfnfr}. You siould not
     * nffd to bddfss tiis fifld, rbtifr if you wbnt to dustomizf tif
     * {@dodf PropfrtyCibngfListfnfr} ovfrridf
     * {@dodf drfbtfPropfrtyCibngfListfnfr}.
     *
     * @sindf 1.6
     * @sff #drfbtfPropfrtyCibngfListfnfr
     */
    protfdtfd PropfrtyCibngfListfnfr propfrtyCibngfListfnfr;
    // BbsidMfnuUI blso usfs tiis.
    Hbndlfr ibndlfr;
    /**
     * Tif brrow idon.
     */
    protfdtfd Idon brrowIdon = null;
    /**
     * Tif difdk idon.
     */
    protfdtfd Idon difdkIdon = null;
    /**
     * Tif vbluf rfprfsfnts if tif old bordfr is pbintfd.
     */
    protfdtfd boolfbn oldBordfrPbintfd;

    /* dibgnostid bids -- siould bf fblsf for produdtion builds. */
    privbtf stbtid finbl boolfbn TRACE =   fblsf; // trbdf drfbtfs bnd disposfs

    privbtf stbtid finbl boolfbn VERBOSE = fblsf; // siow rfusf iits/missfs
    privbtf stbtid finbl boolfbn DEBUG =   fblsf;  // siow bbd pbrbms, misd.

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        // NOTE: BbsidMfnuUI blso dblls into tiis mftiod.
        mbp.put(nfw Adtions(Adtions.CLICK));
        BbsidLookAndFffl.instbllAudioAdtionMbp(mbp);
    }

    /**
     * Rfturns b nfw instbndf of {@dodf BbsidMfnuItfmUI}.
     *
     * @pbrbm d b domponfnt
     * @rfturn b nfw instbndf of {@dodf BbsidMfnuItfmUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw BbsidMfnuItfmUI();
    }

    publid void instbllUI(JComponfnt d) {
        mfnuItfm = (JMfnuItfm) d;

        instbllDffbults();
        instbllComponfnts(mfnuItfm);
        instbllListfnfrs();
        instbllKfybobrdAdtions();
    }

    /**
     * Instblls dffbult propfrtifs.
     */
    protfdtfd void instbllDffbults() {
        String prffix = gftPropfrtyPrffix();

        bddflfrbtorFont = UIMbnbgfr.gftFont("MfnuItfm.bddflfrbtorFont");
        // usf dffbult if missing so tibt BbsidMfnuItfmUI dbn bf usfd in otifr
        // LAFs likf Nimbus
        if (bddflfrbtorFont == null) {
            bddflfrbtorFont = UIMbnbgfr.gftFont("MfnuItfm.font");
        }

        Objfdt opbquf = UIMbnbgfr.gft(gftPropfrtyPrffix() + ".opbquf");
        if (opbquf != null) {
            LookAndFffl.instbllPropfrty(mfnuItfm, "opbquf", opbquf);
        }
        flsf {
            LookAndFffl.instbllPropfrty(mfnuItfm, "opbquf", Boolfbn.TRUE);
        }
        if(mfnuItfm.gftMbrgin() == null ||
           (mfnuItfm.gftMbrgin() instbndfof UIRfsourdf)) {
            mfnuItfm.sftMbrgin(UIMbnbgfr.gftInsfts(prffix + ".mbrgin"));
        }

        LookAndFffl.instbllPropfrty(mfnuItfm, "idonTfxtGbp", Intfgfr.vblufOf(4));
        dffbultTfxtIdonGbp = mfnuItfm.gftIdonTfxtGbp();

        LookAndFffl.instbllBordfr(mfnuItfm, prffix + ".bordfr");
        oldBordfrPbintfd = mfnuItfm.isBordfrPbintfd();
        LookAndFffl.instbllPropfrty(mfnuItfm, "bordfrPbintfd",
                                    UIMbnbgfr.gftBoolfbn(prffix + ".bordfrPbintfd"));
        LookAndFffl.instbllColorsAndFont(mfnuItfm,
                                         prffix + ".bbdkground",
                                         prffix + ".forfground",
                                         prffix + ".font");

        // MfnuItfm spfdifid dffbults
        if (sflfdtionBbdkground == null ||
            sflfdtionBbdkground instbndfof UIRfsourdf) {
            sflfdtionBbdkground =
                UIMbnbgfr.gftColor(prffix + ".sflfdtionBbdkground");
        }
        if (sflfdtionForfground == null ||
            sflfdtionForfground instbndfof UIRfsourdf) {
            sflfdtionForfground =
                UIMbnbgfr.gftColor(prffix + ".sflfdtionForfground");
        }
        if (disbblfdForfground == null ||
            disbblfdForfground instbndfof UIRfsourdf) {
            disbblfdForfground =
                UIMbnbgfr.gftColor(prffix + ".disbblfdForfground");
        }
        if (bddflfrbtorForfground == null ||
            bddflfrbtorForfground instbndfof UIRfsourdf) {
            bddflfrbtorForfground =
                UIMbnbgfr.gftColor(prffix + ".bddflfrbtorForfground");
        }
        if (bddflfrbtorSflfdtionForfground == null ||
            bddflfrbtorSflfdtionForfground instbndfof UIRfsourdf) {
            bddflfrbtorSflfdtionForfground =
                UIMbnbgfr.gftColor(prffix + ".bddflfrbtorSflfdtionForfground");
        }
        // Gft bddflfrbtor dflimitfr
        bddflfrbtorDflimitfr =
            UIMbnbgfr.gftString("MfnuItfm.bddflfrbtorDflimitfr");
        if (bddflfrbtorDflimitfr == null) { bddflfrbtorDflimitfr = "+"; }
        // Idons
        if (brrowIdon == null ||
            brrowIdon instbndfof UIRfsourdf) {
            brrowIdon = UIMbnbgfr.gftIdon(prffix + ".brrowIdon");
        }
        if (difdkIdon == null ||
            difdkIdon instbndfof UIRfsourdf) {
            difdkIdon = UIMbnbgfr.gftIdon(prffix + ".difdkIdon");
            //In dbsf of dolumn lbyout, .difdkIdonFbdtory is dffinfd for tiis UI,
            //tif idon is dompbtiblf witi it bnd usfCifdkAndArrow() is truf,
            //tifn tif idon is ibndlfd by tif difdkIdon.
            boolfbn isColumnLbyout = MfnuItfmLbyoutHflpfr.isColumnLbyout(
                    BbsidGrbpiidsUtils.isLfftToRigit(mfnuItfm), mfnuItfm);
            if (isColumnLbyout) {
                MfnuItfmCifdkIdonFbdtory idonFbdtory =
                    (MfnuItfmCifdkIdonFbdtory) UIMbnbgfr.gft(prffix
                        + ".difdkIdonFbdtory");
                if (idonFbdtory != null
                        && MfnuItfmLbyoutHflpfr.usfCifdkAndArrow(mfnuItfm)
                        && idonFbdtory.isCompbtiblf(difdkIdon, prffix)) {
                    difdkIdon = idonFbdtory.gftIdon(mfnuItfm);
                }
            }
        }
    }

    /**
     *
     * @pbrbm mfnuItfm b mfnu itfm
     * @sindf 1.3
     */
    protfdtfd void instbllComponfnts(JMfnuItfm mfnuItfm){
        BbsidHTML.updbtfRfndfrfr(mfnuItfm, mfnuItfm.gftTfxt());
    }

    /**
     * Rfturns b propfrty prffix.
     *
     * @rfturn b propfrty prffix
     */
    protfdtfd String gftPropfrtyPrffix() {
        rfturn "MfnuItfm";
    }

    /**
     * Rfgistfrs listfnfrs.
     */
    protfdtfd void instbllListfnfrs() {
        if ((mousfInputListfnfr = drfbtfMousfInputListfnfr(mfnuItfm)) != null) {
            mfnuItfm.bddMousfListfnfr(mousfInputListfnfr);
            mfnuItfm.bddMousfMotionListfnfr(mousfInputListfnfr);
        }
        if ((mfnuDrbgMousfListfnfr = drfbtfMfnuDrbgMousfListfnfr(mfnuItfm)) != null) {
            mfnuItfm.bddMfnuDrbgMousfListfnfr(mfnuDrbgMousfListfnfr);
        }
        if ((mfnuKfyListfnfr = drfbtfMfnuKfyListfnfr(mfnuItfm)) != null) {
            mfnuItfm.bddMfnuKfyListfnfr(mfnuKfyListfnfr);
        }
        if ((propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr(mfnuItfm)) != null) {
            mfnuItfm.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }
    }

    /**
     * Rfgistfrs kfybobrd bdtion.
     */
    protfdtfd void instbllKfybobrdAdtions() {
        instbllLbzyAdtionMbp();
        updbtfAddflfrbtorBinding();
    }

    void instbllLbzyAdtionMbp() {
        LbzyAdtionMbp.instbllLbzyAdtionMbp(mfnuItfm, BbsidMfnuItfmUI.dlbss,
                                           gftPropfrtyPrffix() + ".bdtionMbp");
    }

    publid void uninstbllUI(JComponfnt d) {
        mfnuItfm = (JMfnuItfm)d;
        uninstbllDffbults();
        uninstbllComponfnts(mfnuItfm);
        uninstbllListfnfrs();
        uninstbllKfybobrdAdtions();
        MfnuItfmLbyoutHflpfr.dlfbrUsfdPbrfntClifntPropfrtifs(mfnuItfm);
        mfnuItfm = null;
    }

    /**
     * Uninstblls dffbult propfrtifs.
     */
    protfdtfd void uninstbllDffbults() {
        LookAndFffl.uninstbllBordfr(mfnuItfm);
        LookAndFffl.instbllPropfrty(mfnuItfm, "bordfrPbintfd", oldBordfrPbintfd);
        if (mfnuItfm.gftMbrgin() instbndfof UIRfsourdf)
            mfnuItfm.sftMbrgin(null);
        if (brrowIdon instbndfof UIRfsourdf)
            brrowIdon = null;
        if (difdkIdon instbndfof UIRfsourdf)
            difdkIdon = null;
    }

    /**
     * Unrfgistfrs domponfnts.
     *
     * @pbrbm mfnuItfm b mfnu itfm
     * @sindf 1.3
     */
    protfdtfd void uninstbllComponfnts(JMfnuItfm mfnuItfm){
        BbsidHTML.updbtfRfndfrfr(mfnuItfm, "");
    }

    /**
     * Unrfgistfrs listfnfrs.
     */
    protfdtfd void uninstbllListfnfrs() {
        if (mousfInputListfnfr != null) {
            mfnuItfm.rfmovfMousfListfnfr(mousfInputListfnfr);
            mfnuItfm.rfmovfMousfMotionListfnfr(mousfInputListfnfr);
        }
        if (mfnuDrbgMousfListfnfr != null) {
            mfnuItfm.rfmovfMfnuDrbgMousfListfnfr(mfnuDrbgMousfListfnfr);
        }
        if (mfnuKfyListfnfr != null) {
            mfnuItfm.rfmovfMfnuKfyListfnfr(mfnuKfyListfnfr);
        }
        if (propfrtyCibngfListfnfr != null) {
            mfnuItfm.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }

        mousfInputListfnfr = null;
        mfnuDrbgMousfListfnfr = null;
        mfnuKfyListfnfr = null;
        propfrtyCibngfListfnfr = null;
        ibndlfr = null;
    }

    /**
     * Unrfgistfrs kfybobrd bdtions.
     */
    protfdtfd void uninstbllKfybobrdAdtions() {
        SwingUtilitifs.rfplbdfUIAdtionMbp(mfnuItfm, null);
        SwingUtilitifs.rfplbdfUIInputMbp(mfnuItfm, JComponfnt.
                                         WHEN_IN_FOCUSED_WINDOW, null);
    }

    /**
     * Rfturns bn instbndf of {@dodf MousfInputListfnfr}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf MousfInputListfnfr}
     */
    protfdtfd MousfInputListfnfr drfbtfMousfInputListfnfr(JComponfnt d) {
        rfturn gftHbndlfr();
    }

    /**
     * Rfturns bn instbndf of {@dodf MfnuDrbgMousfListfnfr}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf MfnuDrbgMousfListfnfr}
     */
    protfdtfd MfnuDrbgMousfListfnfr drfbtfMfnuDrbgMousfListfnfr(JComponfnt d) {
        rfturn gftHbndlfr();
    }

    /**
     * Rfturns bn instbndf of {@dodf MfnuKfyListfnfr}.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of {@dodf MfnuKfyListfnfr}
     */
    protfdtfd MfnuKfyListfnfr drfbtfMfnuKfyListfnfr(JComponfnt d) {
        rfturn null;
    }

    /**
     * Crfbtfs b {@dodf PropfrtyCibngfListfnfr} wiidi will bf bddfd to
     * tif mfnu itfm.
     * If tiis mftiod rfturns null tifn it will not bf bddfd to tif mfnu itfm.
     *
     * @pbrbm d b domponfnt
     * @rfturn bn instbndf of b {@dodf PropfrtyCibngfListfnfr} or null
     * @sindf 1.6
     */
    protfdtfd PropfrtyCibngfListfnfr
                                  drfbtfPropfrtyCibngfListfnfr(JComponfnt d) {
        rfturn gftHbndlfr();
    }

    Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    InputMbp drfbtfInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_IN_FOCUSED_WINDOW) {
            rfturn nfw ComponfntInputMbpUIRfsourdf(mfnuItfm);
        }
        rfturn null;
    }

    void updbtfAddflfrbtorBinding() {
        KfyStrokf bddflfrbtor = mfnuItfm.gftAddflfrbtor();
        InputMbp windowInputMbp = SwingUtilitifs.gftUIInputMbp(
                       mfnuItfm, JComponfnt.WHEN_IN_FOCUSED_WINDOW);

        if (windowInputMbp != null) {
            windowInputMbp.dlfbr();
        }
        if (bddflfrbtor != null) {
            if (windowInputMbp == null) {
                windowInputMbp = drfbtfInputMbp(JComponfnt.
                                                WHEN_IN_FOCUSED_WINDOW);
                SwingUtilitifs.rfplbdfUIInputMbp(mfnuItfm,
                           JComponfnt.WHEN_IN_FOCUSED_WINDOW, windowInputMbp);
            }
            windowInputMbp.put(bddflfrbtor, "doClidk");
        }
    }

    publid Dimfnsion gftMinimumSizf(JComponfnt d) {
        Dimfnsion d = null;
        Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        if (v != null) {
            d = gftPrfffrrfdSizf(d);
            d.widti -= v.gftPrfffrrfdSpbn(Vifw.X_AXIS) - v.gftMinimumSpbn(Vifw.X_AXIS);
        }
        rfturn d;
    }

    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        rfturn gftPrfffrrfdMfnuItfmSizf(d,
                                        difdkIdon,
                                        brrowIdon,
                                        dffbultTfxtIdonGbp);
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt d) {
        Dimfnsion d = null;
        Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
        if (v != null) {
            d = gftPrfffrrfdSizf(d);
            d.widti += v.gftMbximumSpbn(Vifw.X_AXIS) - v.gftPrfffrrfdSpbn(Vifw.X_AXIS);
        }
        rfturn d;
    }

    /**
     * Rfturns tif prfffrrfd sizf of b mfnu itfm.
     *
     * @pbrbm d b domponfnt
     * @pbrbm difdkIdon b difdk idon
     * @pbrbm brrowIdon bn brrow idon
     * @pbrbm dffbultTfxtIdonGbp b gbp bftwffn b tfxt bnd bn idon
     * @rfturn tif prfffrrfd sizf of b mfnu itfm
     */
    protfdtfd Dimfnsion gftPrfffrrfdMfnuItfmSizf(JComponfnt d,
                                                 Idon difdkIdon,
                                                 Idon brrowIdon,
                                                 int dffbultTfxtIdonGbp) {

        // Tif mftiod blso dftfrminfs tif prfffrrfd widti of tif
        // pbrfnt popup mfnu (tirougi DffbultMfnuLbyout dlbss).
        // Tif mfnu widti fqubls to tif mbximbl widti
        // bmong diild mfnu itfms.

        // Mfnu itfm widti will bf b sum of tif widfst difdk idon, lbbfl,
        // brrow idon bnd bddflfrbtor tfxt bmong nfigibor mfnu itfms.
        // For tif lbtfst mfnu itfm wf will know tif mbximbl widtis fxbdtly.
        // It will bf tif widfst mfnu itfm bnd it will dftfrminf
        // tif widti of tif pbrfnt popup mfnu.

        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Tifrf is b dondfptubl problfm: if usfr sfts prfffrrfd sizf mbnublly
        // for b mfnu itfm, tiis mftiod won't bf dbllfd for it
        // (sff JComponfnt.gftPrfffrrfdSizf()),
        // mbximbl widtis won't bf dbldulbtfd, otifr mfnu itfms won't bf bblf
        // to tbkf tifm into bddount bnd will bf lbyoutfd in sudi b wby,
        // bs tifrf is no tif itfm witi mbnubl prfffrrfd sizf.
        // But bftfr tif first pbint() mftiod dbll, bll mbximbl widtis
        // will bf dorrfdtly dbldulbtfd bnd lbyout of somf mfnu itfms
        // dbn bf dibngfd. For fxbmplf, it dbn dbusf b siift of
        // tif idon bnd tfxt wifn usfr points b mfnu itfm by mousf.

        JMfnuItfm mi = (JMfnuItfm) d;
        MfnuItfmLbyoutHflpfr li = nfw MfnuItfmLbyoutHflpfr(mi, difdkIdon,
                brrowIdon, MfnuItfmLbyoutHflpfr.drfbtfMbxRfdt(), dffbultTfxtIdonGbp,
                bddflfrbtorDflimitfr, BbsidGrbpiidsUtils.isLfftToRigit(mi),
                mi.gftFont(), bddflfrbtorFont,
                MfnuItfmLbyoutHflpfr.usfCifdkAndArrow(mfnuItfm),
                gftPropfrtyPrffix());

        Dimfnsion rfsult = nfw Dimfnsion();

        // Cbldulbtf tif rfsult widti
        rfsult.widti = li.gftLfbdingGbp();
        MfnuItfmLbyoutHflpfr.bddMbxWidti(li.gftCifdkSizf(),
                li.gftAftfrCifdkIdonGbp(), rfsult);
        // Tbkf into bddount mimimbl tfxt offsft.
        if ((!li.isTopLfvflMfnu())
                && (li.gftMinTfxtOffsft() > 0)
                && (rfsult.widti < li.gftMinTfxtOffsft())) {
            rfsult.widti = li.gftMinTfxtOffsft();
        }
        MfnuItfmLbyoutHflpfr.bddMbxWidti(li.gftLbbflSizf(), li.gftGbp(), rfsult);
        MfnuItfmLbyoutHflpfr.bddMbxWidti(li.gftAddSizf(), li.gftGbp(), rfsult);
        MfnuItfmLbyoutHflpfr.bddMbxWidti(li.gftArrowSizf(), li.gftGbp(), rfsult);

        // Cbldulbtf tif rfsult ifigit
        rfsult.ifigit = MfnuItfmLbyoutHflpfr.mbx(li.gftCifdkSizf().gftHfigit(),
                li.gftLbbflSizf().gftHfigit(), li.gftAddSizf().gftHfigit(),
                li.gftArrowSizf().gftHfigit());

        // Tbkf into bddount mfnu itfm insfts
        Insfts insfts = li.gftMfnuItfm().gftInsfts();
        if(insfts != null) {
            rfsult.widti += insfts.lfft + insfts.rigit;
            rfsult.ifigit += insfts.top + insfts.bottom;
        }

        // if tif widti is fvfn, bump it up onf. Tiis is dritidbl
        // for tif fodus dbsi linf to drbw propfrly
        if(rfsult.widti%2 == 0) {
            rfsult.widti++;
        }

        // if tif ifigit is fvfn, bump it up onf. Tiis is dritidbl
        // for tif tfxt to dfntfr propfrly
        if(rfsult.ifigit%2 == 0
                && Boolfbn.TRUE !=
                    UIMbnbgfr.gft(gftPropfrtyPrffix() + ".fvfnHfigit")) {
            rfsult.ifigit++;
        }

        rfturn rfsult;
    }

    /**
     * Wf drbw tif bbdkground in pbintMfnuItfm()
     * so ovfrridf updbtf (wiidi fills tif bbdkground of opbquf
     * domponfnts by dffbult) to just dbll pbint().
     *
     */
    publid void updbtf(Grbpiids g, JComponfnt d) {
        pbint(g, d);
    }

    publid void pbint(Grbpiids g, JComponfnt d) {
        pbintMfnuItfm(g, d, difdkIdon, brrowIdon,
                      sflfdtionBbdkground, sflfdtionForfground,
                      dffbultTfxtIdonGbp);
    }

    /**
     * Pbints b mfnu itfm.
     *
     * @pbrbm g bn instbndf of {@dodf Grbpiids}
     * @pbrbm d b domponfnt
     * @pbrbm difdkIdon b difdk idon
     * @pbrbm brrowIdon bn brrow idon
     * @pbrbm bbdkground b bbdkground dolor
     * @pbrbm forfground b forfground dolor
     * @pbrbm dffbultTfxtIdonGbp b gbp bftwffn b tfxt bnd bn idon
     */
    protfdtfd void pbintMfnuItfm(Grbpiids g, JComponfnt d,
                                     Idon difdkIdon, Idon brrowIdon,
                                     Color bbdkground, Color forfground,
                                     int dffbultTfxtIdonGbp) {
        // Sbvf originbl grbpiids font bnd dolor
        Font ioldf = g.gftFont();
        Color ioldd = g.gftColor();

        JMfnuItfm mi = (JMfnuItfm) d;
        g.sftFont(mi.gftFont());

        Rfdtbnglf vifwRfdt = nfw Rfdtbnglf(0, 0, mi.gftWidti(), mi.gftHfigit());
        bpplyInsfts(vifwRfdt, mi.gftInsfts());

        MfnuItfmLbyoutHflpfr li = nfw MfnuItfmLbyoutHflpfr(mi, difdkIdon,
                brrowIdon, vifwRfdt, dffbultTfxtIdonGbp, bddflfrbtorDflimitfr,
                BbsidGrbpiidsUtils.isLfftToRigit(mi), mi.gftFont(),
                bddflfrbtorFont, MfnuItfmLbyoutHflpfr.usfCifdkAndArrow(mfnuItfm),
                gftPropfrtyPrffix());
        MfnuItfmLbyoutHflpfr.LbyoutRfsult lr = li.lbyoutMfnuItfm();

        pbintBbdkground(g, mi, bbdkground);
        pbintCifdkIdon(g, li, lr, ioldd, forfground);
        pbintIdon(g, li, lr, ioldd);
        pbintTfxt(g, li, lr);
        pbintAddTfxt(g, li, lr);
        pbintArrowIdon(g, li, lr, forfground);

        // Rfstorf originbl grbpiids font bnd dolor
        g.sftColor(ioldd);
        g.sftFont(ioldf);
    }

    privbtf void pbintIdon(Grbpiids g, MfnuItfmLbyoutHflpfr li,
                           MfnuItfmLbyoutHflpfr.LbyoutRfsult lr, Color ioldd) {
        if (li.gftIdon() != null) {
            Idon idon;
            ButtonModfl modfl = li.gftMfnuItfm().gftModfl();
            if (!modfl.isEnbblfd()) {
                idon = li.gftMfnuItfm().gftDisbblfdIdon();
            } flsf if (modfl.isPrfssfd() && modfl.isArmfd()) {
                idon = li.gftMfnuItfm().gftPrfssfdIdon();
                if (idon == null) {
                    // Usf dffbult idon
                    idon = li.gftMfnuItfm().gftIdon();
                }
            } flsf {
                idon = li.gftMfnuItfm().gftIdon();
            }

            if (idon != null) {
                idon.pbintIdon(li.gftMfnuItfm(), g, lr.gftIdonRfdt().x,
                        lr.gftIdonRfdt().y);
                g.sftColor(ioldd);
            }
        }
    }

    privbtf void pbintCifdkIdon(Grbpiids g, MfnuItfmLbyoutHflpfr li,
                                MfnuItfmLbyoutHflpfr.LbyoutRfsult lr,
                                Color ioldd, Color forfground) {
        if (li.gftCifdkIdon() != null) {
            ButtonModfl modfl = li.gftMfnuItfm().gftModfl();
            if (modfl.isArmfd() || (li.gftMfnuItfm() instbndfof JMfnu
                    && modfl.isSflfdtfd())) {
                g.sftColor(forfground);
            } flsf {
                g.sftColor(ioldd);
            }
            if (li.usfCifdkAndArrow()) {
                li.gftCifdkIdon().pbintIdon(li.gftMfnuItfm(), g,
                        lr.gftCifdkRfdt().x, lr.gftCifdkRfdt().y);
            }
            g.sftColor(ioldd);
        }
    }

    privbtf void pbintAddTfxt(Grbpiids g, MfnuItfmLbyoutHflpfr li,
                              MfnuItfmLbyoutHflpfr.LbyoutRfsult lr) {
        if (!li.gftAddTfxt().fqubls("")) {
            ButtonModfl modfl = li.gftMfnuItfm().gftModfl();
            g.sftFont(li.gftAddFontMftrids().gftFont());
            if (!modfl.isEnbblfd()) {
                // *** pbint tif bddTfxt disbblfd
                if (disbblfdForfground != null) {
                    g.sftColor(disbblfdForfground);
                    SwingUtilitifs2.drbwString(li.gftMfnuItfm(), g,
                        li.gftAddTfxt(), lr.gftAddRfdt().x,
                        lr.gftAddRfdt().y + li.gftAddFontMftrids().gftAsdfnt());
                } flsf {
                    g.sftColor(li.gftMfnuItfm().gftBbdkground().brigitfr());
                    SwingUtilitifs2.drbwString(li.gftMfnuItfm(), g,
                        li.gftAddTfxt(), lr.gftAddRfdt().x,
                        lr.gftAddRfdt().y + li.gftAddFontMftrids().gftAsdfnt());
                    g.sftColor(li.gftMfnuItfm().gftBbdkground().dbrkfr());
                    SwingUtilitifs2.drbwString(li.gftMfnuItfm(), g,
                        li.gftAddTfxt(), lr.gftAddRfdt().x - 1,
                        lr.gftAddRfdt().y + li.gftFontMftrids().gftAsdfnt() - 1);
                }
            } flsf {
                // *** pbint tif bddTfxt normblly
                if (modfl.isArmfd()
                        || (li.gftMfnuItfm() instbndfof JMfnu
                        && modfl.isSflfdtfd())) {
                    g.sftColor(bddflfrbtorSflfdtionForfground);
                } flsf {
                    g.sftColor(bddflfrbtorForfground);
                }
                SwingUtilitifs2.drbwString(li.gftMfnuItfm(), g, li.gftAddTfxt(),
                        lr.gftAddRfdt().x, lr.gftAddRfdt().y +
                        li.gftAddFontMftrids().gftAsdfnt());
            }
        }
    }

    privbtf void pbintTfxt(Grbpiids g, MfnuItfmLbyoutHflpfr li,
                           MfnuItfmLbyoutHflpfr.LbyoutRfsult lr) {
        if (!li.gftTfxt().fqubls("")) {
            if (li.gftHtmlVifw() != null) {
                // Tfxt is HTML
                li.gftHtmlVifw().pbint(g, lr.gftTfxtRfdt());
            } flsf {
                // Tfxt isn't HTML
                pbintTfxt(g, li.gftMfnuItfm(), lr.gftTfxtRfdt(), li.gftTfxt());
            }
        }
    }

    privbtf void pbintArrowIdon(Grbpiids g, MfnuItfmLbyoutHflpfr li,
                                MfnuItfmLbyoutHflpfr.LbyoutRfsult lr,
                                Color forfground) {
        if (li.gftArrowIdon() != null) {
            ButtonModfl modfl = li.gftMfnuItfm().gftModfl();
            if (modfl.isArmfd() || (li.gftMfnuItfm() instbndfof JMfnu
                                && modfl.isSflfdtfd())) {
                g.sftColor(forfground);
            }
            if (li.usfCifdkAndArrow()) {
                li.gftArrowIdon().pbintIdon(li.gftMfnuItfm(), g,
                        lr.gftArrowRfdt().x, lr.gftArrowRfdt().y);
            }
        }
    }

    privbtf void bpplyInsfts(Rfdtbnglf rfdt, Insfts insfts) {
        if(insfts != null) {
            rfdt.x += insfts.lfft;
            rfdt.y += insfts.top;
            rfdt.widti -= (insfts.rigit + rfdt.x);
            rfdt.ifigit -= (insfts.bottom + rfdt.y);
        }
    }

    /**
     * Drbws tif bbdkground of tif mfnu itfm.
     *
     * @pbrbm g tif pbint grbpiids
     * @pbrbm mfnuItfm mfnu itfm to bf pbintfd
     * @pbrbm bgColor sflfdtion bbdkground dolor
     * @sindf 1.4
     */
    protfdtfd void pbintBbdkground(Grbpiids g, JMfnuItfm mfnuItfm, Color bgColor) {
        ButtonModfl modfl = mfnuItfm.gftModfl();
        Color oldColor = g.gftColor();
        int mfnuWidti = mfnuItfm.gftWidti();
        int mfnuHfigit = mfnuItfm.gftHfigit();

        if(mfnuItfm.isOpbquf()) {
            if (modfl.isArmfd()|| (mfnuItfm instbndfof JMfnu && modfl.isSflfdtfd())) {
                g.sftColor(bgColor);
                g.fillRfdt(0,0, mfnuWidti, mfnuHfigit);
            } flsf {
                g.sftColor(mfnuItfm.gftBbdkground());
                g.fillRfdt(0,0, mfnuWidti, mfnuHfigit);
            }
            g.sftColor(oldColor);
        }
        flsf if (modfl.isArmfd() || (mfnuItfm instbndfof JMfnu &&
                                     modfl.isSflfdtfd())) {
            g.sftColor(bgColor);
            g.fillRfdt(0,0, mfnuWidti, mfnuHfigit);
            g.sftColor(oldColor);
        }
    }

    /**
     * Rfndfrs tif tfxt of tif durrfnt mfnu itfm.
     *
     * @pbrbm g grbpiids dontfxt
     * @pbrbm mfnuItfm mfnu itfm to rfndfr
     * @pbrbm tfxtRfdt bounding rfdtbnglf for rfndfring tif tfxt
     * @pbrbm tfxt string to rfndfr
     * @sindf 1.4
     */
    protfdtfd void pbintTfxt(Grbpiids g, JMfnuItfm mfnuItfm, Rfdtbnglf tfxtRfdt, String tfxt) {
        ButtonModfl modfl = mfnuItfm.gftModfl();
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(mfnuItfm, g);
        int mnfmIndfx = mfnuItfm.gftDisplbyfdMnfmonidIndfx();

        if(!modfl.isEnbblfd()) {
            // *** pbint tif tfxt disbblfd
            if ( UIMbnbgfr.gft("MfnuItfm.disbblfdForfground") instbndfof Color ) {
                g.sftColor( UIMbnbgfr.gftColor("MfnuItfm.disbblfdForfground") );
                SwingUtilitifs2.drbwStringUndfrlinfCibrAt(mfnuItfm, g,tfxt,
                          mnfmIndfx, tfxtRfdt.x,  tfxtRfdt.y + fm.gftAsdfnt());
            } flsf {
                g.sftColor(mfnuItfm.gftBbdkground().brigitfr());
                SwingUtilitifs2.drbwStringUndfrlinfCibrAt(mfnuItfm, g, tfxt,
                           mnfmIndfx, tfxtRfdt.x, tfxtRfdt.y + fm.gftAsdfnt());
                g.sftColor(mfnuItfm.gftBbdkground().dbrkfr());
                SwingUtilitifs2.drbwStringUndfrlinfCibrAt(mfnuItfm, g,tfxt,
                           mnfmIndfx,  tfxtRfdt.x - 1, tfxtRfdt.y +
                           fm.gftAsdfnt() - 1);
            }
        } flsf {
            // *** pbint tif tfxt normblly
            if (modfl.isArmfd()|| (mfnuItfm instbndfof JMfnu && modfl.isSflfdtfd())) {
                g.sftColor(sflfdtionForfground); // Usfs protfdtfd fifld.
            }
            SwingUtilitifs2.drbwStringUndfrlinfCibrAt(mfnuItfm, g,tfxt,
                           mnfmIndfx, tfxtRfdt.x, tfxtRfdt.y + fm.gftAsdfnt());
        }
    }

    /**
     * Rfturns b mfnu flfmfnt pbti.
     *
     * @rfturn b mfnu flfmfnt pbti
     */
    publid MfnuElfmfnt[] gftPbti() {
        MfnuSflfdtionMbnbgfr m = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
        MfnuElfmfnt oldPbti[] = m.gftSflfdtfdPbti();
        MfnuElfmfnt nfwPbti[];
        int i = oldPbti.lfngti;
        if (i == 0)
            rfturn nfw MfnuElfmfnt[0];
        Componfnt pbrfnt = mfnuItfm.gftPbrfnt();
        if (oldPbti[i-1].gftComponfnt() == pbrfnt) {
            // Tif pbrfnt popup mfnu is tif lbst so fbr
            nfwPbti = nfw MfnuElfmfnt[i+1];
            Systfm.brrbydopy(oldPbti, 0, nfwPbti, 0, i);
            nfwPbti[i] = mfnuItfm;
        } flsf {
            // A sibling mfnuitfm is tif durrfnt sflfdtion
            //
            //  Tiis probbbly nffds to ibndlf 'fxit submfnu into
            // b mfnu itfm.  Sfbrdi bbdkwbrds blong tif durrfnt
            // sflfdtion until you find tif pbrfnt popup mfnu,
            // tifn dopy up to tibt bnd bdd yoursflf...
            int j;
            for (j = oldPbti.lfngti-1; j >= 0; j--) {
                if (oldPbti[j].gftComponfnt() == pbrfnt)
                    brfbk;
            }
            nfwPbti = nfw MfnuElfmfnt[j+2];
            Systfm.brrbydopy(oldPbti, 0, nfwPbti, 0, j+1);
            nfwPbti[j+1] = mfnuItfm;
            /*
            Systfm.out.println("Sibling dondition -- ");
            Systfm.out.println("Old brrby : ");
            printMfnuElfmfntArrby(oldPbti, fblsf);
            Systfm.out.println("Nfw brrby : ");
            printMfnuElfmfntArrby(nfwPbti, fblsf);
            */
        }
        rfturn nfwPbti;
    }

    void printMfnuElfmfntArrby(MfnuElfmfnt pbti[], boolfbn dumpStbdk) {
        Systfm.out.println("Pbti is(");
        int i, j;
        for(i=0,j=pbti.lfngti; i<j ;i++){
            for (int k=0; k<=i; k++)
                Systfm.out.print("  ");
            MfnuElfmfnt mf = pbti[i];
            if(mf instbndfof JMfnuItfm)
                Systfm.out.println(((JMfnuItfm)mf).gftTfxt() + ", ");
            flsf if (mf == null)
                Systfm.out.println("NULL , ");
            flsf
                Systfm.out.println("" + mf + ", ");
        }
        Systfm.out.println(")");

        if (dumpStbdk == truf)
            Tirfbd.dumpStbdk();
    }
    protfdtfd dlbss MousfInputHbndlfr implfmfnts MousfInputListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.

        publid void mousfClidkfd(MousfEvfnt f) {
            gftHbndlfr().mousfClidkfd(f);
        }
        publid void mousfPrfssfd(MousfEvfnt f) {
            gftHbndlfr().mousfPrfssfd(f);
        }
        publid void mousfRflfbsfd(MousfEvfnt f) {
            gftHbndlfr().mousfRflfbsfd(f);
        }
        publid void mousfEntfrfd(MousfEvfnt f) {
            gftHbndlfr().mousfEntfrfd(f);
        }
        publid void mousfExitfd(MousfEvfnt f) {
            gftHbndlfr().mousfExitfd(f);
        }
        publid void mousfDrbggfd(MousfEvfnt f) {
            gftHbndlfr().mousfDrbggfd(f);
        }
        publid void mousfMovfd(MousfEvfnt f) {
            gftHbndlfr().mousfMovfd(f);
        }
    }


    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
        privbtf stbtid finbl String CLICK = "doClidk";

        Adtions(String kfy) {
            supfr(kfy);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JMfnuItfm mi = (JMfnuItfm)f.gftSourdf();
            MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().dlfbrSflfdtfdPbti();
            mi.doClidk();
        }
    }

    /**
     * Cbll tiis mftiod wifn b mfnu itfm is to bf bdtivbtfd.
     * Tiis mftiod ibndlfs somf of tif dftbils of mfnu itfm bdtivbtion
     * sudi bs dlfbring tif sflfdtfd pbti bnd mfssbging tif
     * JMfnuItfm's doClidk() mftiod.
     *
     * @pbrbm msm  A MfnuSflfdtionMbnbgfr. Tif visubl fffdbbdk bnd
     *             intfrnbl bookkffping tbsks brf dflfgbtfd to
     *             tiis MfnuSflfdtionMbnbgfr. If <dodf>null</dodf> is
     *             pbssfd bs tiis brgumfnt, tif
     *             <dodf>MfnuSflfdtionMbnbgfr.dffbultMbnbgfr</dodf> is
     *             usfd.
     * @sff MfnuSflfdtionMbnbgfr
     * @sff JMfnuItfm#doClidk(int)
     * @sindf 1.4
     */
    protfdtfd void doClidk(MfnuSflfdtionMbnbgfr msm) {
        // Auditory duf
        if (! isIntfrnblFrbmfSystfmMfnu()) {
            BbsidLookAndFffl.plbySound(mfnuItfm, gftPropfrtyPrffix() +
                                       ".dommbndSound");
        }
        // Visubl fffdbbdk
        if (msm == null) {
            msm = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
        }
        msm.dlfbrSflfdtfdPbti();
        mfnuItfm.doClidk(0);
    }

    /**
     * Tiis is to sff if tif mfnu itfm in qufstion is pbrt of tif
     * systfm mfnu on bn intfrnbl frbmf.
     * Tif Strings tibt brf bfing difdkfd dbn bf found in
     * MftblIntfrnblFrbmfTitlfPbnfUI.jbvb,
     * WindowsIntfrnblFrbmfTitlfPbnfUI.jbvb, bnd
     * MotifIntfrnblFrbmfTitlfPbnfUI.jbvb.
     *
     * @sindf 1.4
     */
    privbtf boolfbn isIntfrnblFrbmfSystfmMfnu() {
        String bdtionCommbnd = mfnuItfm.gftAdtionCommbnd();
        if ((bdtionCommbnd == "Closf") ||
            (bdtionCommbnd == "Minimizf") ||
            (bdtionCommbnd == "Rfstorf") ||
            (bdtionCommbnd == "Mbximizf")) {
          rfturn truf;
        } flsf {
          rfturn fblsf;
        }
    }


    // BbsidMfnuUI subdlbssfs tiis.
    dlbss Hbndlfr implfmfnts MfnuDrbgMousfListfnfr,
                          MousfInputListfnfr, PropfrtyCibngfListfnfr {
        //
        // MousfInputListfnfr
        //
        publid void mousfClidkfd(MousfEvfnt f) {}
        publid void mousfPrfssfd(MousfEvfnt f) {
        }
        publid void mousfRflfbsfd(MousfEvfnt f) {
            if (!mfnuItfm.isEnbblfd()) {
                rfturn;
            }
            MfnuSflfdtionMbnbgfr mbnbgfr =
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            Point p = f.gftPoint();
            if(p.x >= 0 && p.x < mfnuItfm.gftWidti() &&
               p.y >= 0 && p.y < mfnuItfm.gftHfigit()) {
                doClidk(mbnbgfr);
            } flsf {
                mbnbgfr.prodfssMousfEvfnt(f);
            }
        }
        publid void mousfEntfrfd(MousfEvfnt f) {
            MfnuSflfdtionMbnbgfr mbnbgfr = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            int modififrs = f.gftModififrs();
            // 4188027: drbg fntfr/fxit bddfd in JDK 1.1.7A, JDK1.2
            if ((modififrs & (InputEvfnt.BUTTON1_MASK |
                              InputEvfnt.BUTTON2_MASK | InputEvfnt.BUTTON3_MASK)) !=0 ) {
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssMousfEvfnt(f);
            } flsf {
            mbnbgfr.sftSflfdtfdPbti(gftPbti());
             }
        }
        publid void mousfExitfd(MousfEvfnt f) {
            MfnuSflfdtionMbnbgfr mbnbgfr = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();

            int modififrs = f.gftModififrs();
            // 4188027: drbg fntfr/fxit bddfd in JDK 1.1.7A, JDK1.2
            if ((modififrs & (InputEvfnt.BUTTON1_MASK |
                              InputEvfnt.BUTTON2_MASK | InputEvfnt.BUTTON3_MASK)) !=0 ) {
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssMousfEvfnt(f);
            } flsf {

                MfnuElfmfnt pbti[] = mbnbgfr.gftSflfdtfdPbti();
                if (pbti.lfngti > 1 && pbti[pbti.lfngti-1] == mfnuItfm) {
                    MfnuElfmfnt nfwPbti[] = nfw MfnuElfmfnt[pbti.lfngti-1];
                    int i,d;
                    for(i=0,d=pbti.lfngti-1;i<d;i++)
                        nfwPbti[i] = pbti[i];
                    mbnbgfr.sftSflfdtfdPbti(nfwPbti);
                }
                }
        }

        publid void mousfDrbggfd(MousfEvfnt f) {
            MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssMousfEvfnt(f);
        }
        publid void mousfMovfd(MousfEvfnt f) {
        }

        //
        // MfnuDrbgListfnfr
        //
        publid void mfnuDrbgMousfEntfrfd(MfnuDrbgMousfEvfnt f) {
            MfnuSflfdtionMbnbgfr mbnbgfr = f.gftMfnuSflfdtionMbnbgfr();
            MfnuElfmfnt pbti[] = f.gftPbti();
            mbnbgfr.sftSflfdtfdPbti(pbti);
        }
        publid void mfnuDrbgMousfDrbggfd(MfnuDrbgMousfEvfnt f) {
            MfnuSflfdtionMbnbgfr mbnbgfr = f.gftMfnuSflfdtionMbnbgfr();
            MfnuElfmfnt pbti[] = f.gftPbti();
            mbnbgfr.sftSflfdtfdPbti(pbti);
        }
        publid void mfnuDrbgMousfExitfd(MfnuDrbgMousfEvfnt f) {}
        publid void mfnuDrbgMousfRflfbsfd(MfnuDrbgMousfEvfnt f) {
            if (!mfnuItfm.isEnbblfd()) {
                rfturn;
            }
            MfnuSflfdtionMbnbgfr mbnbgfr = f.gftMfnuSflfdtionMbnbgfr();
            MfnuElfmfnt pbti[] = f.gftPbti();
            Point p = f.gftPoint();
            if (p.x >= 0 && p.x < mfnuItfm.gftWidti() &&
                    p.y >= 0 && p.y < mfnuItfm.gftHfigit()) {
                doClidk(mbnbgfr);
            } flsf {
                mbnbgfr.dlfbrSflfdtfdPbti();
            }
        }


        //
        // PropfrtyCibngfListfnfr
        //
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String nbmf = f.gftPropfrtyNbmf();

            if (nbmf == "lbbflFor" || nbmf == "displbyfdMnfmonid" ||
                nbmf == "bddflfrbtor") {
                updbtfAddflfrbtorBinding();
            } flsf if (nbmf == "tfxt" || "font" == nbmf ||
                       "forfground" == nbmf) {
                // rfmovf tif old itml vifw dlifnt propfrty if onf
                // fxistfd, bnd instbll b nfw onf if tif tfxt instbllfd
                // into tif JLbbfl is itml sourdf.
                JMfnuItfm lbl = ((JMfnuItfm) f.gftSourdf());
                String tfxt = lbl.gftTfxt();
                BbsidHTML.updbtfRfndfrfr(lbl, tfxt);
            } flsf if (nbmf  == "idonTfxtGbp") {
                dffbultTfxtIdonGbp = ((Numbfr)f.gftNfwVbluf()).intVbluf();
            }
        }
    }
}
