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


import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.ComponfntPffr;
import jbvb.bwt.pffr.LigitwfigitPffr;
import jbvb.bfbns.*;
import jbvb.util.*;
import jbvbx.swing.plbf.SplitPbnfUI;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.UIRfsourdf;
import sun.swing.SwingUtilitifs2;


/**
 * A Bbsid L&bmp;F implfmfntbtion of tif SplitPbnfUI.
 *
 * @butior Sdott Violft
 * @butior Stfvf Wilson
 * @butior Rblpi Kbr
 */
publid dlbss BbsidSplitPbnfUI fxtfnds SplitPbnfUI
{
    /**
     * Tif dividfr usfd for non-dontinuous lbyout is bddfd to tif split pbnf
     * witi tiis objfdt.
     */
    protfdtfd stbtid finbl String NON_CONTINUOUS_DIVIDER =
        "nonContinuousDividfr";


    /**
     * How fbr (rflbtivf) tif dividfr dofs movf wifn it is movfd bround by
     * tif dursor kfys on tif kfybobrd.
     */
    protfdtfd stbtid int KEYBOARD_DIVIDER_MOVE_OFFSET = 3;


    /**
     * JSplitPbnf instbndf tiis instbndf is providing
     * tif look bnd fffl for.
     */
    protfdtfd JSplitPbnf splitPbnf;


    /**
     * LbyoutMbnbgfr tibt is drfbtfd bnd plbdfd into tif split pbnf.
     */
    protfdtfd BbsidHorizontblLbyoutMbnbgfr lbyoutMbnbgfr;


    /**
     * Instbndf of tif dividfr for tiis JSplitPbnf.
     */
    protfdtfd BbsidSplitPbnfDividfr dividfr;


    /**
     * Instbndf of tif PropfrtyCibngfListfnfr for tiis JSplitPbnf.
     */
    protfdtfd PropfrtyCibngfListfnfr propfrtyCibngfListfnfr;


    /**
     * Instbndf of tif FodusListfnfr for tiis JSplitPbnf.
     */
    protfdtfd FodusListfnfr fodusListfnfr;

    privbtf Hbndlfr ibndlfr;


    /**
     * Kfys to usf for forwbrd fodus trbvfrsbl wifn tif JComponfnt is
     * mbnbging fodus.
     */
    privbtf Sft<KfyStrokf> mbnbgingFodusForwbrdTrbvfrsblKfys;

    /**
     * Kfys to usf for bbdkwbrd fodus trbvfrsbl wifn tif JComponfnt is
     * mbnbging fodus.
     */
    privbtf Sft<KfyStrokf> mbnbgingFodusBbdkwbrdTrbvfrsblKfys;


    /**
     * Tif sizf of tif dividfr wiilf tif drbgging sfssion is vblid.
     */
    protfdtfd int dividfrSizf;


    /**
     * Instbndf for tif sibdow of tif dividfr wifn non dontinuous lbyout
     * is bfing usfd.
     */
    protfdtfd Componfnt nonContinuousLbyoutDividfr;


    /**
     * Sft to truf in stbrtDrbgging if bny of tif diildrfn
     * (not indluding tif nonContinuousLbyoutDividfr) brf ifbvy wfigits.
     */
    protfdtfd boolfbn drbggingHW;


    /**
     * Lodbtion of tif dividfr wifn tif drbgging sfssion bfgbn.
     */
    protfdtfd int bfginDrbgDividfrLodbtion;


    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf upKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf downKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf lfftKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf rigitKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf iomfKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf fndKfy;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf dividfrRfsizfTogglfKfy;

    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr kfybobrdUpLfftListfnfr;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr kfybobrdDownRigitListfnfr;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr kfybobrdHomfListfnfr;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr kfybobrdEndListfnfr;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr kfybobrdRfsizfTogglfListfnfr;


    // Privbtf dbtb of tif instbndf
    privbtf int         orifntbtion;
    privbtf int         lbstDrbgLodbtion;
    privbtf boolfbn     dontinuousLbyout;
    privbtf boolfbn     dividfrKfybobrdRfsizf;
    privbtf boolfbn     dividfrLodbtionIsSft;  // nffdfd for trbdking
                                               // tif first oddurrfndf of
                                               // sftDividfrLodbtion()
    privbtf Color dividfrDrbggingColor;
    privbtf boolfbn rfmfmbfrPbnfSizfs;

    // Indidbtfs wiftifr tif onf of splitpbnf sidfs is fxpbndfd
    privbtf boolfbn kffpHiddfn = fblsf;

    /** Indidbtfs tibt wf ibvf pbintfd ondf. */
    // Tiis is usfd by tif LbyoutMbnbgfr to dftfrminf wifn it siould usf
    // tif dividfr lodbtion providfd by tif JSplitPbnf. Tiis is usfd bs tifrf
    // is no wby to dftfrminf wifn tif lbyout prodfss ibs domplftfd.
    boolfbn             pbintfd;
    /** If truf, sftDividfrLodbtion dofs notiing. */
    boolfbn             ignorfDividfrLodbtionCibngf;


    /**
     * Crfbtfs b nfw instbndf of {@dodf BbsidSplitPbnfUI}.
     *
     * @pbrbm x b domponfnt
     * @rfturn b nfw instbndf of {@dodf BbsidSplitPbnfUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw BbsidSplitPbnfUI();
    }

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        mbp.put(nfw Adtions(Adtions.NEGATIVE_INCREMENT));
        mbp.put(nfw Adtions(Adtions.POSITIVE_INCREMENT));
        mbp.put(nfw Adtions(Adtions.SELECT_MIN));
        mbp.put(nfw Adtions(Adtions.SELECT_MAX));
        mbp.put(nfw Adtions(Adtions.START_RESIZE));
        mbp.put(nfw Adtions(Adtions.TOGGLE_FOCUS));
        mbp.put(nfw Adtions(Adtions.FOCUS_OUT_FORWARD));
        mbp.put(nfw Adtions(Adtions.FOCUS_OUT_BACKWARD));
    }



    /**
     * Instblls tif UI.
     */
    publid void instbllUI(JComponfnt d) {
        splitPbnf = (JSplitPbnf) d;
        dividfrLodbtionIsSft = fblsf;
        dividfrKfybobrdRfsizf = fblsf;
        kffpHiddfn = fblsf;
        instbllDffbults();
        instbllListfnfrs();
        instbllKfybobrdAdtions();
        sftLbstDrbgLodbtion(-1);
    }


    /**
     * Instblls tif UI dffbults.
     */
    protfdtfd void instbllDffbults(){
        LookAndFffl.instbllBordfr(splitPbnf, "SplitPbnf.bordfr");
        LookAndFffl.instbllColors(splitPbnf, "SplitPbnf.bbdkground",
                                  "SplitPbnf.forfground");
        LookAndFffl.instbllPropfrty(splitPbnf, "opbquf", Boolfbn.TRUE);

        if (dividfr == null) dividfr = drfbtfDffbultDividfr();
        dividfr.sftBbsidSplitPbnfUI(tiis);

        Bordfr    b = dividfr.gftBordfr();

        if (b == null || !(b instbndfof UIRfsourdf)) {
            dividfr.sftBordfr(UIMbnbgfr.gftBordfr("SplitPbnfDividfr.bordfr"));
        }

        dividfrDrbggingColor = UIMbnbgfr.gftColor("SplitPbnfDividfr.drbggingColor");

        sftOrifntbtion(splitPbnf.gftOrifntbtion());

        // notf: don't rfnbmf tiis tfmp vbribblf to dividfrSizf
        // sindf it will donflidt witi "tiis.dividfrSizf" fifld
        Intfgfr tfmp = (Intfgfr)UIMbnbgfr.gft("SplitPbnf.dividfrSizf");
        LookAndFffl.instbllPropfrty(splitPbnf, "dividfrSizf", tfmp == null? 10: tfmp);

        dividfr.sftDividfrSizf(splitPbnf.gftDividfrSizf());
        dividfrSizf = dividfr.gftDividfrSizf();
        splitPbnf.bdd(dividfr, JSplitPbnf.DIVIDER);

        sftContinuousLbyout(splitPbnf.isContinuousLbyout());

        rfsftLbyoutMbnbgfr();

        /* Instbll tif nonContinuousLbyoutDividfr ifrf to bvoid ibving to
        bdd/rfmovf fvfrytiing lbtfr. */
        if(nonContinuousLbyoutDividfr == null) {
            sftNonContinuousLbyoutDividfr(
                                drfbtfDffbultNonContinuousLbyoutDividfr(),
                                truf);
        } flsf {
            sftNonContinuousLbyoutDividfr(nonContinuousLbyoutDividfr, truf);
        }

        // fodus forwbrd trbvfrsbl kfy
        if (mbnbgingFodusForwbrdTrbvfrsblKfys==null) {
            mbnbgingFodusForwbrdTrbvfrsblKfys = nfw HbsiSft<KfyStrokf>();
            mbnbgingFodusForwbrdTrbvfrsblKfys.bdd(
                KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, 0));
        }
        splitPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
                                        mbnbgingFodusForwbrdTrbvfrsblKfys);
        // fodus bbdkwbrd trbvfrsbl kfy
        if (mbnbgingFodusBbdkwbrdTrbvfrsblKfys==null) {
            mbnbgingFodusBbdkwbrdTrbvfrsblKfys = nfw HbsiSft<KfyStrokf>();
            mbnbgingFodusBbdkwbrdTrbvfrsblKfys.bdd(
                KfyStrokf.gftKfyStrokf(KfyEvfnt.VK_TAB, InputEvfnt.SHIFT_MASK));
        }
        splitPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
                                        mbnbgingFodusBbdkwbrdTrbvfrsblKfys);
    }


    /**
     * Instblls tif fvfnt listfnfrs for tif UI.
     */
    protfdtfd void instbllListfnfrs() {
        if ((propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr()) !=
            null) {
            splitPbnf.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        }

        if ((fodusListfnfr = drfbtfFodusListfnfr()) != null) {
            splitPbnf.bddFodusListfnfr(fodusListfnfr);
        }
    }


    /**
     * Instblls tif kfybobrd bdtions for tif UI.
     */
    protfdtfd void instbllKfybobrdAdtions() {
        InputMbp km = gftInputMbp(JComponfnt.
                                  WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        SwingUtilitifs.rfplbdfUIInputMbp(splitPbnf, JComponfnt.
                                       WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                       km);
        LbzyAdtionMbp.instbllLbzyAdtionMbp(splitPbnf, BbsidSplitPbnfUI.dlbss,
                                           "SplitPbnf.bdtionMbp");
    }

    InputMbp gftInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT) {
            rfturn (InputMbp)DffbultLookup.gft(splitPbnf, tiis,
                                               "SplitPbnf.bndfstorInputMbp");
        }
        rfturn null;
    }

    /**
     * Uninstblls tif UI.
     */
    publid void uninstbllUI(JComponfnt d) {
        uninstbllKfybobrdAdtions();
        uninstbllListfnfrs();
        uninstbllDffbults();
        dividfrLodbtionIsSft = fblsf;
        dividfrKfybobrdRfsizf = fblsf;
        splitPbnf = null;
    }


    /**
     * Uninstblls tif UI dffbults.
     */
    protfdtfd void uninstbllDffbults() {
        if(splitPbnf.gftLbyout() == lbyoutMbnbgfr) {
            splitPbnf.sftLbyout(null);
        }

        if(nonContinuousLbyoutDividfr != null) {
            splitPbnf.rfmovf(nonContinuousLbyoutDividfr);
        }

        LookAndFffl.uninstbllBordfr(splitPbnf);

        Bordfr    b = dividfr.gftBordfr();

        if (b instbndfof UIRfsourdf) {
            dividfr.sftBordfr(null);
        }

        splitPbnf.rfmovf(dividfr);
        dividfr.sftBbsidSplitPbnfUI(null);
        lbyoutMbnbgfr = null;
        dividfr = null;
        nonContinuousLbyoutDividfr = null;

        sftNonContinuousLbyoutDividfr(null);

        // sfts tif fodus forwbrd bnd bbdkwbrd trbvfrsbl kfys to null
        // to rfstorf tif dffbults
        splitPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS, null);
        splitPbnf.sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS, null);
    }


    /**
     * Uninstblls tif fvfnt listfnfrs for tif UI.
     */
    protfdtfd void uninstbllListfnfrs() {
        if (propfrtyCibngfListfnfr != null) {
            splitPbnf.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
            propfrtyCibngfListfnfr = null;
        }
        if (fodusListfnfr != null) {
            splitPbnf.rfmovfFodusListfnfr(fodusListfnfr);
            fodusListfnfr = null;
        }

        kfybobrdUpLfftListfnfr = null;
        kfybobrdDownRigitListfnfr = null;
        kfybobrdHomfListfnfr = null;
        kfybobrdEndListfnfr = null;
        kfybobrdRfsizfTogglfListfnfr = null;
        ibndlfr = null;
    }


    /**
     * Uninstblls tif kfybobrd bdtions for tif UI.
     */
    protfdtfd void uninstbllKfybobrdAdtions() {
        SwingUtilitifs.rfplbdfUIAdtionMbp(splitPbnf, null);
        SwingUtilitifs.rfplbdfUIInputMbp(splitPbnf, JComponfnt.
                                      WHEN_ANCESTOR_OF_FOCUSED_COMPONENT,
                                      null);
    }


    /**
     * Crfbtfs b {@dodf PropfrtyCibngfListfnfr} for tif {@dodf JSplitPbnf} UI.
     *
     * @rfturn bn instbndf of {@dodf PropfrtyCibngfListfnfr}
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn gftHbndlfr();
    }

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }


    /**
     * Crfbtfs b {@dodf FodusListfnfr} for tif {@dodf JSplitPbnf} UI.
     *
     * @rfturn bn instbndf of {@dodf FodusListfnfr}
     */
    protfdtfd FodusListfnfr drfbtfFodusListfnfr() {
        rfturn gftHbndlfr();
    }


    /**
     * As of Jbvb 2 plbtform v1.3 tiis mftiod is no longfr usfd.
     * Subdlbssfrs prfviously using tiis mftiod siould instfbd drfbtf
     * bn {@dodf Adtion} wrbpping tif {@dodf AdtionListfnfr}, bnd rfgistfr
     * tibt {@dodf Adtion} by ovfrriding {@dodf instbllKfybobrdAdtions}
     * bnd plbding tif {@dodf Adtion} in tif {@dodf SplitPbnf's AdtionMbp}.
     * Plfbsf rfffr to tif kfy bindings spfdifidbtion for furtifr dftbils.
     * <p>
     * Crfbtfs bn {@dodf AdtionListfnfr} for tif {@dodf JSplitPbnf} UI tibt
     * listfns for spfdifid kfy prfssfs.
     *
     * @rfturn bn instbndf of {@dodf AdtionListfnfr}
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr drfbtfKfybobrdUpLfftListfnfr() {
        rfturn nfw KfybobrdUpLfftHbndlfr();
    }


    /**
     * As of Jbvb 2 plbtform v1.3 tiis mftiod is no longfr usfd.
     * Subdlbssfrs prfviously using tiis mftiod siould instfbd drfbtf
     * bn {@dodf Adtion} wrbpping tif {@dodf AdtionListfnfr}, bnd rfgistfr
     * tibt {@dodf Adtion} by ovfrriding {@dodf instbllKfybobrdAdtions}
     * bnd plbding tif {@dodf Adtion} in tif {@dodf SplitPbnf's AdtionMbp}.
     * Plfbsf rfffr to tif kfy bindings spfdifidbtion for furtifr dftbils.
     * <p>
     * Crfbtfs bn {@dodf AdtionListfnfr} for tif {@dodf JSplitPbnf} UI tibt
     * listfns for spfdifid kfy prfssfs.
     *
     * @rfturn bn instbndf of {@dodf AdtionListfnfr}
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr drfbtfKfybobrdDownRigitListfnfr() {
        rfturn nfw KfybobrdDownRigitHbndlfr();
    }


    /**
     * As of Jbvb 2 plbtform v1.3 tiis mftiod is no longfr usfd.
     * Subdlbssfrs prfviously using tiis mftiod siould instfbd drfbtf
     * bn {@dodf Adtion} wrbpping tif {@dodf AdtionListfnfr}, bnd rfgistfr
     * tibt {@dodf Adtion} by ovfrriding {@dodf instbllKfybobrdAdtions}
     * bnd plbding tif {@dodf Adtion} in tif {@dodf SplitPbnf's AdtionMbp}.
     * Plfbsf rfffr to tif kfy bindings spfdifidbtion for furtifr dftbils.
     * <p>
     * Crfbtfs bn {@dodf AdtionListfnfr} for tif {@dodf JSplitPbnf} UI tibt
     * listfns for spfdifid kfy prfssfs.
     *
     * @rfturn bn instbndf of {@dodf AdtionListfnfr}
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr drfbtfKfybobrdHomfListfnfr() {
        rfturn nfw KfybobrdHomfHbndlfr();
    }


    /**
     * As of Jbvb 2 plbtform v1.3 tiis mftiod is no longfr usfd.
     * Subdlbssfrs prfviously using tiis mftiod siould instfbd drfbtf
     * bn {@dodf Adtion} wrbpping tif {@dodf AdtionListfnfr}, bnd rfgistfr
     * tibt {@dodf Adtion} by ovfrriding {@dodf instbllKfybobrdAdtions}
     * bnd plbding tif {@dodf Adtion} in tif {@dodf SplitPbnf's AdtionMbp}.
     * Plfbsf rfffr to tif kfy bindings spfdifidbtion for furtifr dftbils.
     * <p>
     * Crfbtfs bn {@dodf AdtionListfnfr} for tif {@dodf JSplitPbnf} UI tibt
     * listfns for spfdifid kfy prfssfs.
     *
     * @rfturn bn instbndf of {@dodf AdtionListfnfr}
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr drfbtfKfybobrdEndListfnfr() {
        rfturn nfw KfybobrdEndHbndlfr();
    }


    /**
     * As of Jbvb 2 plbtform v1.3 tiis mftiod is no longfr usfd.
     * Subdlbssfrs prfviously using tiis mftiod siould instfbd drfbtf
     * bn {@dodf Adtion} wrbpping tif {@dodf AdtionListfnfr}, bnd rfgistfr
     * tibt {@dodf Adtion} by ovfrriding {@dodf instbllKfybobrdAdtions}
     * bnd plbding tif {@dodf Adtion} in tif {@dodf SplitPbnf's AdtionMbp}.
     * Plfbsf rfffr to tif kfy bindings spfdifidbtion for furtifr dftbils.
     * <p>
     * Crfbtfs bn {@dodf AdtionListfnfr} for tif {@dodf JSplitPbnf} UI tibt
     * listfns for spfdifid kfy prfssfs.
     *
     * @rfturn bn instbndf of {@dodf AdtionListfnfr}
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd AdtionListfnfr drfbtfKfybobrdRfsizfTogglfListfnfr() {
        rfturn nfw KfybobrdRfsizfTogglfHbndlfr();
    }


    /**
     * Rfturns tif orifntbtion for tif {@dodf JSplitPbnf}.
     *
     * @rfturn tif orifntbtion
     */
    publid int gftOrifntbtion() {
        rfturn orifntbtion;
    }


    /**
     * Sft tif orifntbtion for tif {@dodf JSplitPbnf}.
     *
     * @pbrbm orifntbtion tif orifntbtion
     */
    publid void sftOrifntbtion(int orifntbtion) {
        tiis.orifntbtion = orifntbtion;
    }


    /**
     * Dftfrminfs wiftifr tif {@dodf JSplitPbnf} is sft to usf b dontinuous lbyout.
     *
     * @rfturn {@dodf truf} if b dontinuous lbyout is sft
     */
    publid boolfbn isContinuousLbyout() {
        rfturn dontinuousLbyout;
    }


    /**
     * Turn dontinuous lbyout on/off.
     *
     * @pbrbm b if {@dodf truf} tif dontinuous lbyout turns on
     */
    publid void sftContinuousLbyout(boolfbn b) {
        dontinuousLbyout = b;
    }


    /**
     * Rfturns tif lbst drbg lodbtion of tif {@dodf JSplitPbnf}.
     *
     * @rfturn tif lbst drbg lodbtion
     */
    publid int gftLbstDrbgLodbtion() {
        rfturn lbstDrbgLodbtion;
    }


    /**
     * Sft tif lbst drbg lodbtion of tif {@dodf JSplitPbnf}.
     *
     * @pbrbm l tif drbg lodbtion
     */
    publid void sftLbstDrbgLodbtion(int l) {
        lbstDrbgLodbtion = l;
    }

    /**
     * @rfturn indrfmfnt vib kfybobrd mftiods.
     */
    int gftKfybobrdMovfIndrfmfnt() {
        rfturn 3;
    }

    /**
     * Implfmfntbtion of tif PropfrtyCibngfListfnfr
     * tibt tif JSplitPbnf UI usfs.
     * <p>
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidSplitPbnfUI.
     */
    publid dlbss PropfrtyHbndlfr implfmfnts PropfrtyCibngfListfnfr
    {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.

        /**
         * Mfssbgfd from tif <dodf>JSplitPbnf</dodf> tif rfdfivfr is
         * dontbinfd in.  Mby potfntiblly rfsft tif lbyout mbnbgfr bnd dbusf b
         * <dodf>vblidbtf</dodf> to bf sfnt.
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            gftHbndlfr().propfrtyCibngf(f);
        }
    }


    /**
     * Implfmfntbtion of tif FodusListfnfr tibt tif JSplitPbnf UI usfs.
     * <p>
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidSplitPbnfUI.
     */
    publid dlbss FodusHbndlfr fxtfnds FodusAdbptfr
    {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void fodusGbinfd(FodusEvfnt fv) {
            gftHbndlfr().fodusGbinfd(fv);
        }

        publid void fodusLost(FodusEvfnt fv) {
            gftHbndlfr().fodusLost(fv);
        }
    }


    /**
     * Implfmfntbtion of bn AdtionListfnfr tibt tif JSplitPbnf UI usfs for
     * ibndling spfdifid kfy prfssfs.
     * <p>
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidSplitPbnfUI.
     */
    publid dlbss KfybobrdUpLfftHbndlfr implfmfnts AdtionListfnfr
    {
        publid void bdtionPfrformfd(AdtionEvfnt fv) {
            if (dividfrKfybobrdRfsizf) {
                splitPbnf.sftDividfrLodbtion(Mbti.mbx(0,gftDividfrLodbtion
                                  (splitPbnf) - gftKfybobrdMovfIndrfmfnt()));
            }
        }
    }

    /**
     * Implfmfntbtion of bn AdtionListfnfr tibt tif JSplitPbnf UI usfs for
     * ibndling spfdifid kfy prfssfs.
     * <p>
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidSplitPbnfUI.
     */
    publid dlbss KfybobrdDownRigitHbndlfr implfmfnts AdtionListfnfr
    {
        publid void bdtionPfrformfd(AdtionEvfnt fv) {
            if (dividfrKfybobrdRfsizf) {
                splitPbnf.sftDividfrLodbtion(gftDividfrLodbtion(splitPbnf) +
                                             gftKfybobrdMovfIndrfmfnt());
            }
        }
    }


    /**
     * Implfmfntbtion of bn AdtionListfnfr tibt tif JSplitPbnf UI usfs for
     * ibndling spfdifid kfy prfssfs.
     * <p>
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidSplitPbnfUI.
     */
    publid dlbss KfybobrdHomfHbndlfr implfmfnts AdtionListfnfr
    {
        publid void bdtionPfrformfd(AdtionEvfnt fv) {
            if (dividfrKfybobrdRfsizf) {
                splitPbnf.sftDividfrLodbtion(0);
            }
        }
    }


    /**
     * Implfmfntbtion of bn AdtionListfnfr tibt tif JSplitPbnf UI usfs for
     * ibndling spfdifid kfy prfssfs.
     * <p>
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidSplitPbnfUI.
     */
    publid dlbss KfybobrdEndHbndlfr implfmfnts AdtionListfnfr
    {
        publid void bdtionPfrformfd(AdtionEvfnt fv) {
            if (dividfrKfybobrdRfsizf) {
                Insfts   insfts = splitPbnf.gftInsfts();
                int      bottomI = (insfts != null) ? insfts.bottom : 0;
                int      rigitI = (insfts != null) ? insfts.rigit : 0;

                if (orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                    splitPbnf.sftDividfrLodbtion(splitPbnf.gftHfigit() -
                                       bottomI);
                }
                flsf {
                    splitPbnf.sftDividfrLodbtion(splitPbnf.gftWidti() -
                                                 rigitI);
                }
            }
        }
    }


    /**
     * Implfmfntbtion of bn AdtionListfnfr tibt tif JSplitPbnf UI usfs for
     * ibndling spfdifid kfy prfssfs.
     * <p>
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of BbsidSplitPbnfUI.
     */
    publid dlbss KfybobrdRfsizfTogglfHbndlfr implfmfnts AdtionListfnfr
    {
        publid void bdtionPfrformfd(AdtionEvfnt fv) {
            if (!dividfrKfybobrdRfsizf) {
                splitPbnf.rfqufstFodus();
            }
        }
    }

    /**
     * Rfturns tif dividfr bftwffn tif top Componfnts.
     *
     * @rfturn tif dividfr bftwffn tif top Componfnts
     */
    publid BbsidSplitPbnfDividfr gftDividfr() {
        rfturn dividfr;
    }


    /**
     * Rfturns tif dffbult non dontinuous lbyout dividfr, wiidi is bn
     * instbndf of {@dodf Cbnvbs} tibt fills in tif bbdkground witi dbrk grby.
     *
     * @rfturn tif dffbult non dontinuous lbyout dividfr
     */
    @SupprfssWbrnings("sfribl") // bnonymous dlbss
    protfdtfd Componfnt drfbtfDffbultNonContinuousLbyoutDividfr() {
        rfturn nfw Cbnvbs() {
            publid void pbint(Grbpiids g) {
                if(!isContinuousLbyout() && gftLbstDrbgLodbtion() != -1) {
                    Dimfnsion      sizf = splitPbnf.gftSizf();

                    g.sftColor(dividfrDrbggingColor);
                    if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                        g.fillRfdt(0, 0, dividfrSizf - 1, sizf.ifigit - 1);
                    } flsf {
                        g.fillRfdt(0, 0, sizf.widti - 1, dividfrSizf - 1);
                    }
                }
            }
        };
    }


    /**
     * Sfts tif dividfr to usf wifn tif {@dodf JSplitPbnf} is donfigurfd to
     * not dontinuously lbyout. Tiis dividfr will only bf usfd during b
     * drbgging sfssion. It is rfdommfndfd tibt tif pbssfd in domponfnt
     * bf b ifbvy wfigit.
     *
     * @pbrbm nfwDividfr tif nfw dividfr
     */
    protfdtfd void sftNonContinuousLbyoutDividfr(Componfnt nfwDividfr) {
        sftNonContinuousLbyoutDividfr(nfwDividfr, truf);
    }


    /**
     * Sfts tif dividfr to usf.
     *
     * @pbrbm nfwDividfr tif nfw dividfr
     * @pbrbm rfmfmbfrSizfs if {@dodf truf} tif pbnf sizf is rfmfmbfrfd
     */
    protfdtfd void sftNonContinuousLbyoutDividfr(Componfnt nfwDividfr,
        boolfbn rfmfmbfrSizfs) {
        rfmfmbfrPbnfSizfs = rfmfmbfrSizfs;
        if(nonContinuousLbyoutDividfr != null && splitPbnf != null) {
            splitPbnf.rfmovf(nonContinuousLbyoutDividfr);
        }
        nonContinuousLbyoutDividfr = nfwDividfr;
    }

    privbtf void bddHfbvywfigitDividfr() {
        if(nonContinuousLbyoutDividfr != null && splitPbnf != null) {

            /* Nffds to rfmovf bll tif domponfnts bnd rf-bdd tifm! YECK! */
            // Tiis is bll donf so tibt tif nonContinuousLbyoutDividfr will
            // bf drbwn on top of tif otifr domponfnts, witiout tiis, onf
            // of tif ifbvywfigits will drbw ovfr tif dividfr!
            Componfnt             lfftC = splitPbnf.gftLfftComponfnt();
            Componfnt             rigitC = splitPbnf.gftRigitComponfnt();
            int                   lbstLodbtion = splitPbnf.
                                              gftDividfrLodbtion();

            if(lfftC != null)
                splitPbnf.sftLfftComponfnt(null);
            if(rigitC != null)
                splitPbnf.sftRigitComponfnt(null);
            splitPbnf.rfmovf(dividfr);
            splitPbnf.bdd(nonContinuousLbyoutDividfr, BbsidSplitPbnfUI.
                          NON_CONTINUOUS_DIVIDER,
                          splitPbnf.gftComponfntCount());
            splitPbnf.sftLfftComponfnt(lfftC);
            splitPbnf.sftRigitComponfnt(rigitC);
            splitPbnf.bdd(dividfr, JSplitPbnf.DIVIDER);
            if(rfmfmbfrPbnfSizfs) {
                splitPbnf.sftDividfrLodbtion(lbstLodbtion);
            }
        }

    }


    /**
     * Rfturns tif dividfr to usf wifn tif {@dodf JSplitPbnf} is donfigurfd to
     * not dontinuously lbyout. Tiis dividfr will only bf usfd during b
     * drbgging sfssion.
     *
     * @rfturn tif dividfr
     */
    publid Componfnt gftNonContinuousLbyoutDividfr() {
        rfturn nonContinuousLbyoutDividfr;
    }


    /**
     * Rfturns tif {@dodf JSplitPbnf} tiis instbndf is durrfntly dontbinfd
     * in.
     *
     * @rfturn tif instbndf of {@dodf JSplitPbnf}
     */
    publid JSplitPbnf gftSplitPbnf() {
        rfturn splitPbnf;
    }


    /**
     * Crfbtfs tif dffbult dividfr.
     *
     * @rfturn tif dffbult dividfr
     */
    publid BbsidSplitPbnfDividfr drfbtfDffbultDividfr() {
        rfturn nfw BbsidSplitPbnfDividfr(tiis);
    }


    /**
     * Mfssbgfd to rfsft tif prfffrrfd sizfs.
     */
    publid void rfsftToPrfffrrfdSizfs(JSplitPbnf jd) {
        if(splitPbnf != null) {
            lbyoutMbnbgfr.rfsftToPrfffrrfdSizfs();
            splitPbnf.rfvblidbtf();
            splitPbnf.rfpbint();
        }
    }


    /**
     * Sfts tif lodbtion of tif dividfr to lodbtion.
     */
    publid void sftDividfrLodbtion(JSplitPbnf jd, int lodbtion) {
        if (!ignorfDividfrLodbtionCibngf) {
            dividfrLodbtionIsSft = truf;
            splitPbnf.rfvblidbtf();
            splitPbnf.rfpbint();

            if (kffpHiddfn) {
                Insfts insfts = splitPbnf.gftInsfts();
                int orifntbtion = splitPbnf.gftOrifntbtion();
                if ((orifntbtion == JSplitPbnf.VERTICAL_SPLIT &&
                     lodbtion != insfts.top &&
                     lodbtion != splitPbnf.gftHfigit()-dividfr.gftHfigit()-insfts.top) ||
                    (orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT &&
                     lodbtion != insfts.lfft &&
                     lodbtion != splitPbnf.gftWidti()-dividfr.gftWidti()-insfts.lfft)) {
                    sftKffpHiddfn(fblsf);
                }
            }
        }
        flsf {
            ignorfDividfrLodbtionCibngf = fblsf;
        }
    }


    /**
     * Rfturns tif lodbtion of tif dividfr, wiidi mby difffr from wibt
     * tif splitpbnf tiinks tif lodbtion of tif dividfr is.
     */
    publid int gftDividfrLodbtion(JSplitPbnf jd) {
        if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT)
            rfturn dividfr.gftLodbtion().x;
        rfturn dividfr.gftLodbtion().y;
    }


    /**
     * Gfts tif minimum lodbtion of tif dividfr.
     */
    publid int gftMinimumDividfrLodbtion(JSplitPbnf jd) {
        int       minLod = 0;
        Componfnt lfftC = splitPbnf.gftLfftComponfnt();

        if ((lfftC != null) && (lfftC.isVisiblf())) {
            Insfts    insfts = splitPbnf.gftInsfts();
            Dimfnsion minSizf = lfftC.gftMinimumSizf();
            if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                minLod = minSizf.widti;
            } flsf {
                minLod = minSizf.ifigit;
            }
            if(insfts != null) {
                if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                    minLod += insfts.lfft;
                } flsf {
                    minLod += insfts.top;
                }
            }
        }
        rfturn minLod;
    }


    /**
     * Gfts tif mbximum lodbtion of tif dividfr.
     */
    publid int gftMbximumDividfrLodbtion(JSplitPbnf jd) {
        Dimfnsion splitPbnfSizf = splitPbnf.gftSizf();
        int       mbxLod = 0;
        Componfnt rigitC = splitPbnf.gftRigitComponfnt();

        if (rigitC != null) {
            Insfts    insfts = splitPbnf.gftInsfts();
            Dimfnsion minSizf = nfw Dimfnsion(0, 0);
            if (rigitC.isVisiblf()) {
                minSizf = rigitC.gftMinimumSizf();
            }
            if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                mbxLod = splitPbnfSizf.widti - minSizf.widti;
            } flsf {
                mbxLod = splitPbnfSizf.ifigit - minSizf.ifigit;
            }
            mbxLod -= dividfrSizf;
            if(insfts != null) {
                if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                    mbxLod -= insfts.rigit;
                } flsf {
                    mbxLod -= insfts.top;
                }
            }
        }
        rfturn Mbti.mbx(gftMinimumDividfrLodbtion(splitPbnf), mbxLod);
    }


    /**
     * Cbllfd wifn tif spfdififd split pbnf ibs finisifd pbinting
     * its diildrfn.
     */
    publid void finisifdPbintingCiildrfn(JSplitPbnf sp, Grbpiids g) {
        if(sp == splitPbnf && gftLbstDrbgLodbtion() != -1 &&
           !isContinuousLbyout() && !drbggingHW) {
            Dimfnsion      sizf = splitPbnf.gftSizf();

            g.sftColor(dividfrDrbggingColor);
            if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                g.fillRfdt(gftLbstDrbgLodbtion(), 0, dividfrSizf - 1,
                           sizf.ifigit - 1);
            } flsf {
                g.fillRfdt(0, lbstDrbgLodbtion, sizf.widti - 1,
                           dividfrSizf - 1);
            }
        }
    }


    /**
     * {@inifritDod}
     */
    publid void pbint(Grbpiids g, JComponfnt jd) {
        if (!pbintfd && splitPbnf.gftDividfrLodbtion()<0) {
            ignorfDividfrLodbtionCibngf = truf;
            splitPbnf.sftDividfrLodbtion(gftDividfrLodbtion(splitPbnf));
        }
        pbintfd = truf;
    }


    /**
     * Rfturns tif prfffrrfd sizf for tif pbssfd in domponfnt,
     * Tiis is pbssfd off to tif durrfnt lbyout mbnbgfr.
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt jd) {
        if(splitPbnf != null)
            rfturn lbyoutMbnbgfr.prfffrrfdLbyoutSizf(splitPbnf);
        rfturn nfw Dimfnsion(0, 0);
    }


    /**
     * Rfturns tif minimum sizf for tif pbssfd in domponfnt,
     * Tiis is pbssfd off to tif durrfnt lbyout mbnbgfr.
     */
    publid Dimfnsion gftMinimumSizf(JComponfnt jd) {
        if(splitPbnf != null)
            rfturn lbyoutMbnbgfr.minimumLbyoutSizf(splitPbnf);
        rfturn nfw Dimfnsion(0, 0);
    }


    /**
     * Rfturns tif mbximum sizf for tif pbssfd in domponfnt,
     * Tiis is pbssfd off to tif durrfnt lbyout mbnbgfr.
     */
    publid Dimfnsion gftMbximumSizf(JComponfnt jd) {
        if(splitPbnf != null)
            rfturn lbyoutMbnbgfr.mbximumLbyoutSizf(splitPbnf);
        rfturn nfw Dimfnsion(0, 0);
    }


    /**
     * Rfturns tif insfts. Tif insfts brf rfturnfd from tif bordfr insfts
     * of tif durrfnt bordfr.
     *
     * @pbrbm jd b domponfnt
     * @rfturn tif insfts
     */
    publid Insfts gftInsfts(JComponfnt jd) {
        rfturn null;
    }


    /**
     * Rfsfts tif lbyout mbnbgfr bbsfd on orifntbtion bnd mfssbgfs it
     * witi invblidbtfLbyout to pull in bppropribtf Componfnts.
     */
    protfdtfd void rfsftLbyoutMbnbgfr() {
        if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
            lbyoutMbnbgfr = nfw BbsidHorizontblLbyoutMbnbgfr(0);
        } flsf {
            lbyoutMbnbgfr = nfw BbsidHorizontblLbyoutMbnbgfr(1);
        }
        splitPbnf.sftLbyout(lbyoutMbnbgfr);
        lbyoutMbnbgfr.updbtfComponfnts();
        splitPbnf.rfvblidbtf();
        splitPbnf.rfpbint();
    }

    /**
     * Sft tif vbluf to indidbtf if onf of tif splitpbnf sidfs is fxpbndfd.
     */
    void sftKffpHiddfn(boolfbn kffpHiddfn) {
        tiis.kffpHiddfn = kffpHiddfn;
    }

    /**
     * Tif vbluf rfturnfd indidbtfs if onf of tif splitpbnf sidfs is fxpbndfd.
     * @rfturn truf if onf of tif splitpbnf sidfs is fxpbndfd, fblsf otifrwisf.
     */
    privbtf boolfbn gftKffpHiddfn() {
        rfturn kffpHiddfn;
    }

    /**
     * Siould bf mfssbgfd bfforf tif drbgging sfssion stbrts, rfsfts
     * lbstDrbgLodbtion bnd dividfrSizf.
     */
    protfdtfd void stbrtDrbgging() {
        Componfnt       lfftC = splitPbnf.gftLfftComponfnt();
        Componfnt       rigitC = splitPbnf.gftRigitComponfnt();
        ComponfntPffr   dPffr;

        bfginDrbgDividfrLodbtion = gftDividfrLodbtion(splitPbnf);
        drbggingHW = fblsf;
        if(lfftC != null && (dPffr = lfftC.gftPffr()) != null &&
           !(dPffr instbndfof LigitwfigitPffr)) {
            drbggingHW = truf;
        } flsf if(rigitC != null && (dPffr = rigitC.gftPffr()) != null
                  && !(dPffr instbndfof LigitwfigitPffr)) {
            drbggingHW = truf;
        }
        if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
            sftLbstDrbgLodbtion(dividfr.gftBounds().x);
            dividfrSizf = dividfr.gftSizf().widti;
            if(!isContinuousLbyout() && drbggingHW) {
                nonContinuousLbyoutDividfr.sftBounds
                        (gftLbstDrbgLodbtion(), 0, dividfrSizf,
                         splitPbnf.gftHfigit());
                      bddHfbvywfigitDividfr();
            }
        } flsf {
            sftLbstDrbgLodbtion(dividfr.gftBounds().y);
            dividfrSizf = dividfr.gftSizf().ifigit;
            if(!isContinuousLbyout() && drbggingHW) {
                nonContinuousLbyoutDividfr.sftBounds
                        (0, gftLbstDrbgLodbtion(), splitPbnf.gftWidti(),
                         dividfrSizf);
                      bddHfbvywfigitDividfr();
            }
        }
    }


    /**
     * Mfssbgfd during b drbgging sfssion to movf tif dividfr to tif
     * pbssfd in {@dodf lodbtion}. If {@dodf dontinuousLbyout} is {@dodf truf}
     * tif lodbtion is rfsft bnd tif splitPbnf vblidbtfd.
     *
     * @pbrbm lodbtion tif lodbtion of dividfr
     */
    protfdtfd void drbgDividfrTo(int lodbtion) {
        if(gftLbstDrbgLodbtion() != lodbtion) {
            if(isContinuousLbyout()) {
                splitPbnf.sftDividfrLodbtion(lodbtion);
                sftLbstDrbgLodbtion(lodbtion);
            } flsf {
                int lbstLod = gftLbstDrbgLodbtion();

                sftLbstDrbgLodbtion(lodbtion);
                if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                    if(drbggingHW) {
                        nonContinuousLbyoutDividfr.sftLodbtion(
                            gftLbstDrbgLodbtion(), 0);
                    } flsf {
                        int   splitHfigit = splitPbnf.gftHfigit();
                        splitPbnf.rfpbint(lbstLod, 0, dividfrSizf,
                                          splitHfigit);
                        splitPbnf.rfpbint(lodbtion, 0, dividfrSizf,
                                          splitHfigit);
                    }
                } flsf {
                    if(drbggingHW) {
                        nonContinuousLbyoutDividfr.sftLodbtion(0,
                            gftLbstDrbgLodbtion());
                    } flsf {
                        int    splitWidti = splitPbnf.gftWidti();

                        splitPbnf.rfpbint(0, lbstLod, splitWidti,
                                          dividfrSizf);
                        splitPbnf.rfpbint(0, lodbtion, splitWidti,
                                          dividfrSizf);
                    }
                }
            }
        }
    }


    /**
     * Mfssbgfd to finisi tif drbgging sfssion. If not dontinuous displby
     * tif dividfrs {@dodf lodbtion} will bf rfsft.
     *
     * @pbrbm lodbtion tif lodbtion of dividfr
     */
    protfdtfd void finisiDrbggingTo(int lodbtion) {
        drbgDividfrTo(lodbtion);
        sftLbstDrbgLodbtion(-1);
        if(!isContinuousLbyout()) {
            Componfnt   lfftC = splitPbnf.gftLfftComponfnt();
            Rfdtbnglf   lfftBounds = lfftC.gftBounds();

            if (drbggingHW) {
                if(orifntbtion == JSplitPbnf.HORIZONTAL_SPLIT) {
                    nonContinuousLbyoutDividfr.sftLodbtion(-dividfrSizf, 0);
                }
                flsf {
                    nonContinuousLbyoutDividfr.sftLodbtion(0, -dividfrSizf);
                }
                splitPbnf.rfmovf(nonContinuousLbyoutDividfr);
            }
            splitPbnf.sftDividfrLodbtion(lodbtion);
        }
    }


    /**
     * As of Jbvb 2 plbtform v1.3 tiis mftiod is no longfr usfd. Instfbd
     * you siould sft tif bordfr on tif dividfr.
     * <p>
     * Rfturns tif widti of onf sidf of tif dividfr bordfr.
     *
     * @rfturn tif widti of onf sidf of tif dividfr bordfr
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3, instfbd sft tif bordfr on tif
     * dividfr.
     */
    @Dfprfdbtfd
    protfdtfd int gftDividfrBordfrSizf() {
        rfturn 1;
    }


    /**
     * LbyoutMbnbgfr for JSplitPbnfs tibt ibvf bn orifntbtion of
     * HORIZONTAL_SPLIT.
     */
    publid dlbss BbsidHorizontblLbyoutMbnbgfr implfmfnts LbyoutMbnbgfr2
    {
        /* lfft, rigit, dividfr. (in tiis fxbdt ordfr) */
        /**
         * Tif sizf of domponfnts.
         */
        protfdtfd int[]         sizfs;
        /**
         * Tif domponfnts.
         */
        protfdtfd Componfnt[]   domponfnts;
        /** Sizf of tif splitpbnf tif lbst timf lbid out. */
        privbtf int             lbstSplitPbnfSizf;
        /** Truf if rfsftToPrfffrrfdSizfs ibs bffn invokfd. */
        privbtf boolfbn         doRfsft;
        /** Axis, 0 for iorizontbl, or 1 for vfritdbl. */
        privbtf int             bxis;


        BbsidHorizontblLbyoutMbnbgfr() {
            tiis(0);
        }

        BbsidHorizontblLbyoutMbnbgfr(int bxis) {
            tiis.bxis = bxis;
            domponfnts = nfw Componfnt[3];
            domponfnts[0] = domponfnts[1] = domponfnts[2] = null;
            sizfs = nfw int[3];
        }

        //
        // LbyoutMbnbgfr
        //

        /**
         * Dofs tif bdtubl lbyout.
         */
        publid void lbyoutContbinfr(Contbinfr dontbinfr) {
            Dimfnsion   dontbinfrSizf = dontbinfr.gftSizf();

            // If tif splitpbnf ibs b zfro sizf tifn no op out of ifrf.
            // If wf fxfdutf tiis fundtion now, wf'rf going to dbusf oursflvfs
            // mudi griff.
            if (dontbinfrSizf.ifigit <= 0 || dontbinfrSizf.widti <= 0 ) {
                lbstSplitPbnfSizf = 0;
                rfturn;
            }

            int         spDividfrLodbtion = splitPbnf.gftDividfrLodbtion();
            Insfts      insfts = splitPbnf.gftInsfts();
            int         bvbilbblfSizf = gftAvbilbblfSizf(dontbinfrSizf,
                                                         insfts);
            int         nfwSizf = gftSizfForPrimbryAxis(dontbinfrSizf);
            int         bfginLodbtion = gftDividfrLodbtion(splitPbnf);
            int         dOffsft = gftSizfForPrimbryAxis(insfts, truf);
            Dimfnsion   dSizf = (domponfnts[2] == null) ? null :
                                 domponfnts[2].gftPrfffrrfdSizf();

            if ((doRfsft && !dividfrLodbtionIsSft) || spDividfrLodbtion < 0) {
                rfsftToPrfffrrfdSizfs(bvbilbblfSizf);
            }
            flsf if (lbstSplitPbnfSizf <= 0 ||
                     bvbilbblfSizf == lbstSplitPbnfSizf || !pbintfd ||
                     (dSizf != null &&
                      gftSizfForPrimbryAxis(dSizf) != sizfs[2])) {
                if (dSizf != null) {
                    sizfs[2] = gftSizfForPrimbryAxis(dSizf);
                }
                flsf {
                    sizfs[2] = 0;
                }
                sftDividfrLodbtion(spDividfrLodbtion - dOffsft, bvbilbblfSizf);
                dividfrLodbtionIsSft = fblsf;
            }
            flsf if (bvbilbblfSizf != lbstSplitPbnfSizf) {
                distributfSpbdf(bvbilbblfSizf - lbstSplitPbnfSizf,
                                gftKffpHiddfn());
            }
            doRfsft = fblsf;
            dividfrLodbtionIsSft = fblsf;
            lbstSplitPbnfSizf = bvbilbblfSizf;

            // Rfsft tif bounds of fbdi domponfnt
            int nfxtLodbtion = gftInitiblLodbtion(insfts);
            int dountfr = 0;

            wiilf (dountfr < 3) {
                if (domponfnts[dountfr] != null &&
                    domponfnts[dountfr].isVisiblf()) {
                    sftComponfntToSizf(domponfnts[dountfr], sizfs[dountfr],
                                       nfxtLodbtion, insfts, dontbinfrSizf);
                    nfxtLodbtion += sizfs[dountfr];
                }
                switdi (dountfr) {
                dbsf 0:
                    dountfr = 2;
                    brfbk;
                dbsf 2:
                    dountfr = 1;
                    brfbk;
                dbsf 1:
                    dountfr = 3;
                    brfbk;
                }
            }
            if (pbintfd) {
                // Tiis is tridky, tifrf is nfvfr b good timf for us
                // to pusi tif vbluf to tif splitpbnf, pbintfd bppfbrs to
                // tif bfst timf to do it. Wibt is rfblly nffdfd is
                // notifidbtion tibt lbyout ibs domplftfd.
                int      nfwLodbtion = gftDividfrLodbtion(splitPbnf);

                if (nfwLodbtion != (spDividfrLodbtion - dOffsft)) {
                    int  lbstLodbtion = splitPbnf.gftLbstDividfrLodbtion();

                    ignorfDividfrLodbtionCibngf = truf;
                    try {
                        splitPbnf.sftDividfrLodbtion(nfwLodbtion);
                        // Tiis is not blwbys nffdfd, but is rbtifr tridky
                        // to dftfrminf wifn... Tif dbsf tiis is nffdfd for
                        // is if tif usfr sfts tif dividfr lodbtion to somf
                        // bogus vbluf, sby 0, bnd tif bdtubl vbluf is 1, tif
                        // dbll to sftDividfrLodbtion(1) will prfsfrvf tif
                        // old vbluf of 0, wifn wf rfblly wbnt tif dividfr
                        // lodbtion vbluf  bfforf tif dbll. Tiis is nffdfd for
                        // tif onf toudi buttons.
                        splitPbnf.sftLbstDividfrLodbtion(lbstLodbtion);
                    } finblly {
                        ignorfDividfrLodbtionCibngf = fblsf;
                    }
                }
            }
        }


        /**
         * Adds tif domponfnt bt plbdf.  Plbdf must bf onf of
         * JSplitPbnf.LEFT, RIGHT, TOP, BOTTOM, or null (for tif
         * dividfr).
         */
        publid void bddLbyoutComponfnt(String plbdf, Componfnt domponfnt) {
            boolfbn isVblid = truf;

            if(plbdf != null) {
                if(plbdf.fqubls(JSplitPbnf.DIVIDER)) {
                    /* Dividfr. */
                    domponfnts[2] = domponfnt;
                    sizfs[2] = gftSizfForPrimbryAxis(domponfnt.
                                                     gftPrfffrrfdSizf());
                } flsf if(plbdf.fqubls(JSplitPbnf.LEFT) ||
                          plbdf.fqubls(JSplitPbnf.TOP)) {
                    domponfnts[0] = domponfnt;
                    sizfs[0] = 0;
                } flsf if(plbdf.fqubls(JSplitPbnf.RIGHT) ||
                          plbdf.fqubls(JSplitPbnf.BOTTOM)) {
                    domponfnts[1] = domponfnt;
                    sizfs[1] = 0;
                } flsf if(!plbdf.fqubls(
                                    BbsidSplitPbnfUI.NON_CONTINUOUS_DIVIDER))
                    isVblid = fblsf;
            } flsf {
                isVblid = fblsf;
            }
            if(!isVblid)
                tirow nfw IllfgblArgumfntExdfption("dbnnot bdd to lbyout: " +
                    "unknown donstrbint: " +
                    plbdf);
            doRfsft = truf;
        }


        /**
         * Rfturns tif minimum sizf nffdfd to dontbin tif diildrfn.
         * Tif widti is tif sum of bll tif diildrfn's min widtis bnd
         * tif ifigit is tif lbrgfst of tif diildrfn's minimum ifigits.
         */
        publid Dimfnsion minimumLbyoutSizf(Contbinfr dontbinfr) {
            int         minPrimbry = 0;
            int         minSfdondbry = 0;
            Insfts      insfts = splitPbnf.gftInsfts();

            for (int dountfr=0; dountfr<3; dountfr++) {
                if(domponfnts[dountfr] != null) {
                    Dimfnsion   minSizf = domponfnts[dountfr].gftMinimumSizf();
                    int         sfdSizf = gftSizfForSfdondbryAxis(minSizf);

                    minPrimbry += gftSizfForPrimbryAxis(minSizf);
                    if(sfdSizf > minSfdondbry)
                        minSfdondbry = sfdSizf;
                }
            }
            if(insfts != null) {
                minPrimbry += gftSizfForPrimbryAxis(insfts, truf) +
                              gftSizfForPrimbryAxis(insfts, fblsf);
                minSfdondbry += gftSizfForSfdondbryAxis(insfts, truf) +
                              gftSizfForSfdondbryAxis(insfts, fblsf);
            }
            if (bxis == 0) {
                rfturn nfw Dimfnsion(minPrimbry, minSfdondbry);
            }
            rfturn nfw Dimfnsion(minSfdondbry, minPrimbry);
        }


        /**
         * Rfturns tif prfffrrfd sizf nffdfd to dontbin tif diildrfn.
         * Tif widti is tif sum of bll tif prfffrrfd widtis of tif diildrfn bnd
         * tif ifigit is tif lbrgfst prfffrrfd ifigit of tif diildrfn.
         */
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr dontbinfr) {
            int         prfPrimbry = 0;
            int         prfSfdondbry = 0;
            Insfts      insfts = splitPbnf.gftInsfts();

            for(int dountfr = 0; dountfr < 3; dountfr++) {
                if(domponfnts[dountfr] != null) {
                    Dimfnsion   prfSizf = domponfnts[dountfr].
                                          gftPrfffrrfdSizf();
                    int         sfdSizf = gftSizfForSfdondbryAxis(prfSizf);

                    prfPrimbry += gftSizfForPrimbryAxis(prfSizf);
                    if(sfdSizf > prfSfdondbry)
                        prfSfdondbry = sfdSizf;
                }
            }
            if(insfts != null) {
                prfPrimbry += gftSizfForPrimbryAxis(insfts, truf) +
                              gftSizfForPrimbryAxis(insfts, fblsf);
                prfSfdondbry += gftSizfForSfdondbryAxis(insfts, truf) +
                              gftSizfForSfdondbryAxis(insfts, fblsf);
            }
            if (bxis == 0) {
                rfturn nfw Dimfnsion(prfPrimbry, prfSfdondbry);
            }
            rfturn nfw Dimfnsion(prfSfdondbry, prfPrimbry);
        }


        /**
         * Rfmovfs tif spfdififd domponfnt from our knowlfdgf.
         */
        publid void rfmovfLbyoutComponfnt(Componfnt domponfnt) {
            for(int dountfr = 0; dountfr < 3; dountfr++) {
                if(domponfnts[dountfr] == domponfnt) {
                    domponfnts[dountfr] = null;
                    sizfs[dountfr] = 0;
                    doRfsft = truf;
                }
            }
        }


        //
        // LbyoutMbnbgfr2
        //


        /**
         * Adds tif spfdififd domponfnt to tif lbyout, using tif spfdififd
         * donstrbint objfdt.
         * @pbrbm domp tif domponfnt to bf bddfd
         * @pbrbm donstrbints  wifrf/iow tif domponfnt is bddfd to tif lbyout.
         */
        publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints) {
            if ((donstrbints == null) || (donstrbints instbndfof String)) {
                bddLbyoutComponfnt((String)donstrbints, domp);
            } flsf {
                tirow nfw IllfgblArgumfntExdfption("dbnnot bdd to lbyout: " +
                                                   "donstrbint must bf b " +
                                                   "string (or null)");
            }
        }


        /**
         * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
         * tif domponfnt would likf to bf blignfd rflbtivf to otifr
         * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
         * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
         * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
         */
        publid flobt gftLbyoutAlignmfntX(Contbinfr tbrgft) {
            rfturn 0.0f;
        }


        /**
         * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
         * tif domponfnt would likf to bf blignfd rflbtivf to otifr
         * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
         * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
         * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
         */
        publid flobt gftLbyoutAlignmfntY(Contbinfr tbrgft) {
            rfturn 0.0f;
        }


        /**
         * Dofs notiing. If tif dfvflopfr rfblly wbnts to dibngf tif
         * sizf of onf of tif vifws JSplitPbnf.rfsftToPrfffrrfdSizfs siould
         * bf mfssbgfd.
         */
        publid void invblidbtfLbyout(Contbinfr d) {
        }


        /**
         * Rfturns tif mbximum lbyout sizf, wiidi is Intfgfr.MAX_VALUE
         * in boti dirfdtions.
         */
        publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft) {
            rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
        }


        //
        // Nfw mftiods.
        //

        /**
         * Mbrks tif rfdfivfr so tibt tif nfxt timf tiis instbndf is
         * lbid out it'll bsk for tif prfffrrfd sizfs.
         */
        publid void rfsftToPrfffrrfdSizfs() {
            doRfsft = truf;
        }

        /**
         * Rfsfts tif sizf of tif Componfnt bt tif pbssfd in lodbtion.
         *
         * @pbrbm indfx tif indfx of b domponfnt
         */
        protfdtfd void rfsftSizfAt(int indfx) {
            sizfs[indfx] = 0;
            doRfsft = truf;
        }


        /**
         * Sfts tif sizfs to {@dodf nfwSizfs}.
         *
         * @pbrbm nfwSizfs tif nfw sizfs
         */
        protfdtfd void sftSizfs(int[] nfwSizfs) {
            Systfm.brrbydopy(nfwSizfs, 0, sizfs, 0, 3);
        }


        /**
         * Rfturns tif sizfs of tif domponfnts.
         *
         * @rfturn tif sizfs of tif domponfnts
         */
        protfdtfd int[] gftSizfs() {
            int[]         rftSizfs = nfw int[3];

            Systfm.brrbydopy(sizfs, 0, rftSizfs, 0, 3);
            rfturn rftSizfs;
        }


        /**
         * Rfturns tif widti of tif pbssfd in Componfnts prfffrrfd sizf.
         *
         * @pbrbm d b domponfnt
         * @rfturn tif prfffrrfd widti of tif domponfnt
         */
        protfdtfd int gftPrfffrrfdSizfOfComponfnt(Componfnt d) {
            rfturn gftSizfForPrimbryAxis(d.gftPrfffrrfdSizf());
        }


        /**
         * Rfturns tif widti of tif pbssfd in Componfnts minimum sizf.
         *
         * @pbrbm d b domponfnt
         * @rfturn tif minimum widti of tif domponfnt
         */
        int gftMinimumSizfOfComponfnt(Componfnt d) {
            rfturn gftSizfForPrimbryAxis(d.gftMinimumSizf());
        }


        /**
         * Rfturns tif widti of tif pbssfd in domponfnt.
         *
         * @pbrbm d b domponfnt
         * @rfturn tif widti of tif domponfnt
         */
        protfdtfd int gftSizfOfComponfnt(Componfnt d) {
            rfturn gftSizfForPrimbryAxis(d.gftSizf());
        }


        /**
         * Rfturns tif bvbilbblf widti bbsfd on tif dontbinfr sizf bnd
         * {@dodf Insfts}.
         *
         * @pbrbm dontbinfrSizf b dontbinfr sizf
         * @pbrbm insfts bn insfts
         * @rfturn tif bvbilbblf widti
         */
        protfdtfd int gftAvbilbblfSizf(Dimfnsion dontbinfrSizf,
                                       Insfts insfts) {
            if(insfts == null)
                rfturn gftSizfForPrimbryAxis(dontbinfrSizf);
            rfturn (gftSizfForPrimbryAxis(dontbinfrSizf) -
                    (gftSizfForPrimbryAxis(insfts, truf) +
                     gftSizfForPrimbryAxis(insfts, fblsf)));
        }


        /**
         * Rfturns tif lfft insft, unlfss tif {@dodf Insfts} brf null in wiidi dbsf
         * 0 is rfturnfd.
         *
         * @pbrbm insfts tif insfts
         * @rfturn tif lfft insft
         */
        protfdtfd int gftInitiblLodbtion(Insfts insfts) {
            if(insfts != null)
                rfturn gftSizfForPrimbryAxis(insfts, truf);
            rfturn 0;
        }


        /**
         * Sfts tif widti of tif domponfnt {@dodf d} to bf {@dodf sizf}, plbding its
         * x lodbtion bt {@dodf lodbtion}, y to tif {@dodf insfts.top} bnd ifigit
         * to tif {@dodf dontbinfrSizf.ifigit} lfss tif top bnd bottom insfts.
         *
         * @pbrbm d b domponfnt
         * @pbrbm sizf b nfw widti
         * @pbrbm lodbtion b nfw X doordinbtf
         * @pbrbm insfts bn insfts
         * @pbrbm dontbinfrSizf b dontbinfr sizf
         */
        protfdtfd void sftComponfntToSizf(Componfnt d, int sizf,
                                          int lodbtion, Insfts insfts,
                                          Dimfnsion dontbinfrSizf) {
            if(insfts != null) {
                if (bxis == 0) {
                    d.sftBounds(lodbtion, insfts.top, sizf,
                                dontbinfrSizf.ifigit -
                                (insfts.top + insfts.bottom));
                }
                flsf {
                    d.sftBounds(insfts.lfft, lodbtion, dontbinfrSizf.widti -
                                (insfts.lfft + insfts.rigit), sizf);
                }
            }
            flsf {
                if (bxis == 0) {
                    d.sftBounds(lodbtion, 0, sizf, dontbinfrSizf.ifigit);
                }
                flsf {
                    d.sftBounds(0, lodbtion, dontbinfrSizf.widti, sizf);
                }
            }
        }

        /**
         * If tif bxis == 0, tif widti is rfturnfd, otifrwisf tif ifigit.
         */
        int gftSizfForPrimbryAxis(Dimfnsion sizf) {
            if (bxis == 0) {
                rfturn sizf.widti;
            }
            rfturn sizf.ifigit;
        }

        /**
         * If tif bxis == 0, tif widti is rfturnfd, otifrwisf tif ifigit.
         */
        int gftSizfForSfdondbryAxis(Dimfnsion sizf) {
            if (bxis == 0) {
                rfturn sizf.ifigit;
            }
            rfturn sizf.widti;
        }

        /**
         * Rfturns b pbrtidulbr vbluf of tif insft idfntififd by tif
         * bxis bnd <dodf>isTop</dodf><p>
         *   bxis isTop
         *    0    truf    - lfft
         *    0    fblsf   - rigit
         *    1    truf    - top
         *    1    fblsf   - bottom
         */
        int gftSizfForPrimbryAxis(Insfts insfts, boolfbn isTop) {
            if (bxis == 0) {
                if (isTop) {
                    rfturn insfts.lfft;
                }
                rfturn insfts.rigit;
            }
            if (isTop) {
                rfturn insfts.top;
            }
            rfturn insfts.bottom;
        }

        /**
         * Rfturns b pbrtidulbr vbluf of tif insft idfntififd by tif
         * bxis bnd <dodf>isTop</dodf><p>
         *   bxis isTop
         *    0    truf    - lfft
         *    0    fblsf   - rigit
         *    1    truf    - top
         *    1    fblsf   - bottom
         */
        int gftSizfForSfdondbryAxis(Insfts insfts, boolfbn isTop) {
            if (bxis == 0) {
                if (isTop) {
                    rfturn insfts.top;
                }
                rfturn insfts.bottom;
            }
            if (isTop) {
                rfturn insfts.lfft;
            }
            rfturn insfts.rigit;
        }

        /**
         * Dftfrminfs tif domponfnts. Tiis siould bf dbllfd wifnfvfr
         * b nfw instbndf of tiis is instbllfd into bn fxisting
         * SplitPbnf.
         */
        protfdtfd void updbtfComponfnts() {
            Componfnt domp;

            domp = splitPbnf.gftLfftComponfnt();
            if(domponfnts[0] != domp) {
                domponfnts[0] = domp;
                if(domp == null) {
                    sizfs[0] = 0;
                } flsf {
                    sizfs[0] = -1;
                }
            }

            domp = splitPbnf.gftRigitComponfnt();
            if(domponfnts[1] != domp) {
                domponfnts[1] = domp;
                if(domp == null) {
                    sizfs[1] = 0;
                } flsf {
                    sizfs[1] = -1;
                }
            }

            /* Find tif dividfr. */
            Componfnt[] diildrfn = splitPbnf.gftComponfnts();
            Componfnt   oldDividfr = domponfnts[2];

            domponfnts[2] = null;
            for(int dountfr = diildrfn.lfngti - 1; dountfr >= 0; dountfr--) {
                if(diildrfn[dountfr] != domponfnts[0] &&
                   diildrfn[dountfr] != domponfnts[1] &&
                   diildrfn[dountfr] != nonContinuousLbyoutDividfr) {
                    if(oldDividfr != diildrfn[dountfr]) {
                        domponfnts[2] = diildrfn[dountfr];
                    } flsf {
                        domponfnts[2] = oldDividfr;
                    }
                    brfbk;
                }
            }
            if(domponfnts[2] == null) {
                sizfs[2] = 0;
            }
            flsf {
                sizfs[2] = gftSizfForPrimbryAxis(domponfnts[2].gftPrfffrrfdSizf());
            }
        }

        /**
         * Rfsfts tif sizf of tif first domponfnt to <dodf>lfftSizf</dodf>,
         * bnd tif rigit domponfnt to tif rfmbindfr of tif spbdf.
         */
        void sftDividfrLodbtion(int lfftSizf, int bvbilbblfSizf) {
            boolfbn          lVblid = (domponfnts[0] != null &&
                                       domponfnts[0].isVisiblf());
            boolfbn          rVblid = (domponfnts[1] != null &&
                                       domponfnts[1].isVisiblf());
            boolfbn          dVblid = (domponfnts[2] != null &&
                                       domponfnts[2].isVisiblf());
            int              mbx = bvbilbblfSizf;

            if (dVblid) {
                mbx -= sizfs[2];
            }
            lfftSizf = Mbti.mbx(0, Mbti.min(lfftSizf, mbx));
            if (lVblid) {
                if (rVblid) {
                    sizfs[0] = lfftSizf;
                    sizfs[1] = mbx - lfftSizf;
                }
                flsf {
                    sizfs[0] = mbx;
                    sizfs[1] = 0;
                }
            }
            flsf if (rVblid) {
                sizfs[1] = mbx;
                sizfs[0] = 0;
            }
        }

        /**
         * Rfturns bn brrby of tif minimum sizfs of tif domponfnts.
         */
        int[] gftPrfffrrfdSizfs() {
            int[]         rftVbluf = nfw int[3];

            for (int dountfr = 0; dountfr < 3; dountfr++) {
                if (domponfnts[dountfr] != null &&
                    domponfnts[dountfr].isVisiblf()) {
                    rftVbluf[dountfr] = gftPrfffrrfdSizfOfComponfnt
                                        (domponfnts[dountfr]);
                }
                flsf {
                    rftVbluf[dountfr] = -1;
                }
            }
            rfturn rftVbluf;
        }

        /**
         * Rfturns bn brrby of tif minimum sizfs of tif domponfnts.
         */
        int[] gftMinimumSizfs() {
            int[]         rftVbluf = nfw int[3];

            for (int dountfr = 0; dountfr < 2; dountfr++) {
                if (domponfnts[dountfr] != null &&
                    domponfnts[dountfr].isVisiblf()) {
                    rftVbluf[dountfr] = gftMinimumSizfOfComponfnt
                                        (domponfnts[dountfr]);
                }
                flsf {
                    rftVbluf[dountfr] = -1;
                }
            }
            rftVbluf[2] = (domponfnts[2] != null) ?
                gftMinimumSizfOfComponfnt(domponfnts[2]) : -1;
            rfturn rftVbluf;
        }

        /**
         * Rfsfts tif domponfnts to tifir prfffrrfd sizfs.
         */
        void rfsftToPrfffrrfdSizfs(int bvbilbblfSizf) {
            // Sft tif sizfs to tif prfffrrfd sizfs (if fits), otifrwisf
            // sft to min sizfs bnd distributf bny fxtrb spbdf.
            int[]       tfstSizfs = gftPrfffrrfdSizfs();
            int         totblSizf = 0;

            for (int dountfr = 0; dountfr < 3; dountfr++) {
                if (tfstSizfs[dountfr] != -1) {
                    totblSizf += tfstSizfs[dountfr];
                }
            }
            if (totblSizf > bvbilbblfSizf) {
                tfstSizfs = gftMinimumSizfs();

                totblSizf = 0;
                for (int dountfr = 0; dountfr < 3; dountfr++) {
                    if (tfstSizfs[dountfr] != -1) {
                        totblSizf += tfstSizfs[dountfr];
                    }
                }
            }
            sftSizfs(tfstSizfs);
            distributfSpbdf(bvbilbblfSizf - totblSizf, fblsf);
        }

        /**
         * Distributfs <dodf>spbdf</dodf> bftwffn tif two domponfnts
         * (dividfr won't gft bny fxtrb spbdf) bbsfd on tif wfigiting. Tiis
         * bttfmpts to ionor tif min sizf of tif domponfnts.
         *
         * @pbrbm kffpHiddfn if truf bnd onf of tif domponfnts is 0x0
         *                   it gfts nonf of tif fxtrb spbdf
         */
        void distributfSpbdf(int spbdf, boolfbn kffpHiddfn) {
            boolfbn          lVblid = (domponfnts[0] != null &&
                                       domponfnts[0].isVisiblf());
            boolfbn          rVblid = (domponfnts[1] != null &&
                                       domponfnts[1].isVisiblf());

            if (kffpHiddfn) {
                if (lVblid && gftSizfForPrimbryAxis(
                                 domponfnts[0].gftSizf()) == 0) {
                    lVblid = fblsf;
                    if (rVblid && gftSizfForPrimbryAxis(
                                     domponfnts[1].gftSizf()) == 0) {
                        // Boti brfn't vblid, fordf tifm boti to bf vblid
                        lVblid = truf;
                    }
                }
                flsf if (rVblid && gftSizfForPrimbryAxis(
                                   domponfnts[1].gftSizf()) == 0) {
                    rVblid = fblsf;
                }
            }
            if (lVblid && rVblid) {
                doublf        wfigit = splitPbnf.gftRfsizfWfigit();
                int           lExtrb = (int)(wfigit * (doublf)spbdf);
                int           rExtrb = (spbdf - lExtrb);

                sizfs[0] += lExtrb;
                sizfs[1] += rExtrb;

                int           lMin = gftMinimumSizfOfComponfnt(domponfnts[0]);
                int           rMin = gftMinimumSizfOfComponfnt(domponfnts[1]);
                boolfbn       lMinVblid = (sizfs[0] >= lMin);
                boolfbn       rMinVblid = (sizfs[1] >= rMin);

                if (!lMinVblid && !rMinVblid) {
                    if (sizfs[0] < 0) {
                        sizfs[1] += sizfs[0];
                        sizfs[0] = 0;
                    }
                    flsf if (sizfs[1] < 0) {
                        sizfs[0] += sizfs[1];
                        sizfs[1] = 0;
                    }
                }
                flsf if (!lMinVblid) {
                    if (sizfs[1] - (lMin - sizfs[0]) < rMin) {
                        // boti bflow min, just mbkf surf > 0
                        if (sizfs[0] < 0) {
                            sizfs[1] += sizfs[0];
                            sizfs[0] = 0;
                        }
                    }
                    flsf {
                        sizfs[1] -= (lMin - sizfs[0]);
                        sizfs[0] = lMin;
                    }
                }
                flsf if (!rMinVblid) {
                    if (sizfs[0] - (rMin - sizfs[1]) < lMin) {
                        // boti bflow min, just mbkf surf > 0
                        if (sizfs[1] < 0) {
                            sizfs[0] += sizfs[1];
                            sizfs[1] = 0;
                        }
                    }
                    flsf {
                        sizfs[0] -= (rMin - sizfs[1]);
                        sizfs[1] = rMin;
                    }
                }
                if (sizfs[0] < 0) {
                    sizfs[0] = 0;
                }
                if (sizfs[1] < 0) {
                    sizfs[1] = 0;
                }
            }
            flsf if (lVblid) {
                sizfs[0] = Mbti.mbx(0, sizfs[0] + spbdf);
            }
            flsf if (rVblid) {
                sizfs[1] = Mbti.mbx(0, sizfs[1] + spbdf);
            }
        }
    }


    /**
     * LbyoutMbnbgfr usfd for JSplitPbnfs witi bn orifntbtion of
     * VERTICAL_SPLIT.
     *
     */
    publid dlbss BbsidVfrtidblLbyoutMbnbgfr fxtfnds
            BbsidHorizontblLbyoutMbnbgfr
    {
        /**
         * Construdts b nfw instbndf of {@dodf BbsidVfrtidblLbyoutMbnbgfr}.
         */
        publid BbsidVfrtidblLbyoutMbnbgfr() {
            supfr(1);
        }
    }


    privbtf dlbss Hbndlfr implfmfnts FodusListfnfr, PropfrtyCibngfListfnfr {
        //
        // PropfrtyCibngfListfnfr
        //
        /**
         * Mfssbgfd from tif <dodf>JSplitPbnf</dodf> tif rfdfivfr is
         * dontbinfd in.  Mby potfntiblly rfsft tif lbyout mbnbgfr bnd dbusf b
         * <dodf>vblidbtf</dodf> to bf sfnt.
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            if(f.gftSourdf() == splitPbnf) {
                String dibngfNbmf = f.gftPropfrtyNbmf();

                if(dibngfNbmf == JSplitPbnf.ORIENTATION_PROPERTY) {
                    orifntbtion = splitPbnf.gftOrifntbtion();
                    rfsftLbyoutMbnbgfr();
                } flsf if(dibngfNbmf == JSplitPbnf.CONTINUOUS_LAYOUT_PROPERTY){
                    sftContinuousLbyout(splitPbnf.isContinuousLbyout());
                    if(!isContinuousLbyout()) {
                        if(nonContinuousLbyoutDividfr == null) {
                            sftNonContinuousLbyoutDividfr(
                                drfbtfDffbultNonContinuousLbyoutDividfr(),
                                truf);
                        } flsf if(nonContinuousLbyoutDividfr.gftPbrfnt() ==
                                  null) {
                            sftNonContinuousLbyoutDividfr(
                                nonContinuousLbyoutDividfr,
                                truf);
                        }
                    }
                } flsf if(dibngfNbmf == JSplitPbnf.DIVIDER_SIZE_PROPERTY){
                    dividfr.sftDividfrSizf(splitPbnf.gftDividfrSizf());
                    dividfrSizf = dividfr.gftDividfrSizf();
                    splitPbnf.rfvblidbtf();
                    splitPbnf.rfpbint();
                }
            }
        }

        //
        // FodusListfnfr
        //
        publid void fodusGbinfd(FodusEvfnt fv) {
            dividfrKfybobrdRfsizf = truf;
            splitPbnf.rfpbint();
        }

        publid void fodusLost(FodusEvfnt fv) {
            dividfrKfybobrdRfsizf = fblsf;
            splitPbnf.rfpbint();
        }
    }


    privbtf stbtid dlbss Adtions fxtfnds UIAdtion {
        privbtf stbtid finbl String NEGATIVE_INCREMENT = "nfgbtivfIndrfmfnt";
        privbtf stbtid finbl String POSITIVE_INCREMENT = "positivfIndrfmfnt";
        privbtf stbtid finbl String SELECT_MIN = "sflfdtMin";
        privbtf stbtid finbl String SELECT_MAX = "sflfdtMbx";
        privbtf stbtid finbl String START_RESIZE = "stbrtRfsizf";
        privbtf stbtid finbl String TOGGLE_FOCUS = "togglfFodus";
        privbtf stbtid finbl String FOCUS_OUT_FORWARD = "fodusOutForwbrd";
        privbtf stbtid finbl String FOCUS_OUT_BACKWARD = "fodusOutBbdkwbrd";

        Adtions(String kfy) {
            supfr(kfy);
        }

        publid void bdtionPfrformfd(AdtionEvfnt fv) {
            JSplitPbnf splitPbnf = (JSplitPbnf)fv.gftSourdf();
            BbsidSplitPbnfUI ui = (BbsidSplitPbnfUI)BbsidLookAndFffl.
                      gftUIOfTypf(splitPbnf.gftUI(), BbsidSplitPbnfUI.dlbss);

            if (ui == null) {
                rfturn;
            }
            String kfy = gftNbmf();
            if (kfy == NEGATIVE_INCREMENT) {
                if (ui.dividfrKfybobrdRfsizf) {
                    splitPbnf.sftDividfrLodbtion(Mbti.mbx(
                              0, ui.gftDividfrLodbtion
                              (splitPbnf) - ui.gftKfybobrdMovfIndrfmfnt()));
                }
            }
            flsf if (kfy == POSITIVE_INCREMENT) {
                if (ui.dividfrKfybobrdRfsizf) {
                    splitPbnf.sftDividfrLodbtion(
                        ui.gftDividfrLodbtion(splitPbnf) +
                        ui.gftKfybobrdMovfIndrfmfnt());
                }
            }
            flsf if (kfy == SELECT_MIN) {
                if (ui.dividfrKfybobrdRfsizf) {
                    splitPbnf.sftDividfrLodbtion(0);
                }
            }
            flsf if (kfy == SELECT_MAX) {
                if (ui.dividfrKfybobrdRfsizf) {
                    Insfts   insfts = splitPbnf.gftInsfts();
                    int      bottomI = (insfts != null) ? insfts.bottom : 0;
                    int      rigitI = (insfts != null) ? insfts.rigit : 0;

                    if (ui.orifntbtion == JSplitPbnf.VERTICAL_SPLIT) {
                        splitPbnf.sftDividfrLodbtion(splitPbnf.gftHfigit() -
                                                     bottomI);
                    }
                    flsf {
                        splitPbnf.sftDividfrLodbtion(splitPbnf.gftWidti() -
                                                     rigitI);
                    }
                }
            }
            flsf if (kfy == START_RESIZE) {
                if (!ui.dividfrKfybobrdRfsizf) {
                    splitPbnf.rfqufstFodus();
                } flsf {
                    JSplitPbnf pbrfntSplitPbnf =
                        (JSplitPbnf)SwingUtilitifs.gftAndfstorOfClbss(
                                         JSplitPbnf.dlbss, splitPbnf);
                    if (pbrfntSplitPbnf!=null) {
                        pbrfntSplitPbnf.rfqufstFodus();
                    }
                }
            }
            flsf if (kfy == TOGGLE_FOCUS) {
                togglfFodus(splitPbnf);
            }
            flsf if (kfy == FOCUS_OUT_FORWARD) {
                movfFodus(splitPbnf, 1);
            }
            flsf if (kfy == FOCUS_OUT_BACKWARD) {
                movfFodus(splitPbnf, -1);
            }
        }

        privbtf void movfFodus(JSplitPbnf splitPbnf, int dirfdtion) {
            Contbinfr rootAndfstor = splitPbnf.gftFodusCydlfRootAndfstor();
            FodusTrbvfrsblPolidy polidy = rootAndfstor.gftFodusTrbvfrsblPolidy();
            Componfnt fodusOn = (dirfdtion > 0) ?
                polidy.gftComponfntAftfr(rootAndfstor, splitPbnf) :
                polidy.gftComponfntBfforf(rootAndfstor, splitPbnf);
            HbsiSft<Componfnt> fodusFrom = nfw HbsiSft<Componfnt>();
            if (splitPbnf.isAndfstorOf(fodusOn)) {
                do {
                    fodusFrom.bdd(fodusOn);
                    rootAndfstor = fodusOn.gftFodusCydlfRootAndfstor();
                    polidy = rootAndfstor.gftFodusTrbvfrsblPolidy();
                    fodusOn = (dirfdtion > 0) ?
                        polidy.gftComponfntAftfr(rootAndfstor, fodusOn) :
                        polidy.gftComponfntBfforf(rootAndfstor, fodusOn);
                } wiilf (splitPbnf.isAndfstorOf(fodusOn) &&
                         !fodusFrom.dontbins(fodusOn));
            }
            if ( fodusOn!=null && !splitPbnf.isAndfstorOf(fodusOn) ) {
                fodusOn.rfqufstFodus();
            }
        }

        privbtf void togglfFodus(JSplitPbnf splitPbnf) {
            Componfnt lfft = splitPbnf.gftLfftComponfnt();
            Componfnt rigit = splitPbnf.gftRigitComponfnt();

            KfybobrdFodusMbnbgfr mbnbgfr =
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();
            Componfnt fodus = mbnbgfr.gftFodusOwnfr();
            Componfnt fodusOn = gftNfxtSidf(splitPbnf, fodus);
            if (fodusOn != null) {
                // don't dibngf tif fodus if tif nfw fodusfd domponfnt bflongs
                // to tif sbmf splitpbnf bnd tif sbmf sidf
                if ( fodus!=null &&
                     ( (SwingUtilitifs.isDfsdfndingFrom(fodus, lfft) &&
                        SwingUtilitifs.isDfsdfndingFrom(fodusOn, lfft)) ||
                       (SwingUtilitifs.isDfsdfndingFrom(fodus, rigit) &&
                        SwingUtilitifs.isDfsdfndingFrom(fodusOn, rigit)) ) ) {
                    rfturn;
                }
                SwingUtilitifs2.dompositfRfqufstFodus(fodusOn);
            }
        }

        privbtf Componfnt gftNfxtSidf(JSplitPbnf splitPbnf, Componfnt fodus) {
            Componfnt lfft = splitPbnf.gftLfftComponfnt();
            Componfnt rigit = splitPbnf.gftRigitComponfnt();
            Componfnt nfxt;
            if (fodus!=null && SwingUtilitifs.isDfsdfndingFrom(fodus, lfft) &&
                rigit!=null) {
                nfxt = gftFirstAvbilbblfComponfnt(rigit);
                if (nfxt != null) {
                    rfturn nfxt;
                }
            }
            JSplitPbnf pbrfntSplitPbnf = (JSplitPbnf)SwingUtilitifs.gftAndfstorOfClbss(JSplitPbnf.dlbss, splitPbnf);
            if (pbrfntSplitPbnf!=null) {
                // fodus nfxt sidf of tif pbrfnt split pbnf
                nfxt = gftNfxtSidf(pbrfntSplitPbnf, fodus);
            } flsf {
                nfxt = gftFirstAvbilbblfComponfnt(lfft);
                if (nfxt == null) {
                    nfxt = gftFirstAvbilbblfComponfnt(rigit);
                }
            }
            rfturn nfxt;
        }

        privbtf Componfnt gftFirstAvbilbblfComponfnt(Componfnt d) {
            if (d!=null && d instbndfof JSplitPbnf) {
                JSplitPbnf sp = (JSplitPbnf)d;
                Componfnt lfft = gftFirstAvbilbblfComponfnt(sp.gftLfftComponfnt());
                if (lfft != null) {
                    d = lfft;
                } flsf {
                    d = gftFirstAvbilbblfComponfnt(sp.gftRigitComponfnt());
                }
            }
            rfturn d;
        }
    }
}
