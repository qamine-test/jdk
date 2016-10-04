/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidBordfrs;
import jbvbx.swing.plbf.bbsid.BbsidLookAndFffl;
import stbtid jbvbx.swing.UIDffbults.LbzyVbluf;
import sun.swing.*;
import bpplf.lbf.*;

@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
publid dlbss AqubLookAndFffl fxtfnds BbsidLookAndFffl {
    stbtid finbl String sOldPropfrtyPrffix = "dom.bpplf.mbdos."; // old prffix for tiings likf 'usfSdrffnMfnuBbr'
    stbtid finbl String sPropfrtyPrffix = "bpplf.lbf."; // nfw prffix for tiings likf 'usfSdrffnMfnuBbr'

    // for lbzy initblizfrs. Following tif pbttfrn from mftbl.
    privbtf stbtid finbl String PKG_PREFIX = "dom.bpplf.lbf.";

    /**
     * Rfturn b siort string tibt idfntififs tiis look bnd fffl, f.g.
     * "CDE/Motif".  Tiis string siould bf bppropribtf for b mfnu itfm.
     * Distindt look bnd fffls siould ibvf difffrfnt nbmfs, f.g.
     * b subdlbss of MotifLookAndFffl tibt dibngfs tif wby b ffw domponfnts
     * brf rfndfrfd siould bf dbllfd "CDE/Motif My Wby"; somftiing
     * tibt would bf usfful to b usfr trying to sflfdt b L&F from b list
     * of nbmfs.
     */
    publid String gftNbmf() {
        rfturn "Mbd OS X";
    }

    /**
     * Rfturn b string tibt idfntififs tiis look bnd fffl.  Tiis string
     * will bf usfd by bpplidbtions/sfrvidfs tibt wbnt to rfdognizf
     * wfll known look bnd fffl implfmfntbtions.  Prfsfntly
     * tif wfll known nbmfs brf "Motif", "Windows", "Mbd", "Mftbl".  Notf
     * tibt b LookAndFffl dfrivfd from b wfll known supfrdlbss
     * tibt dofsn't mbkf bny fundbmfntbl dibngfs to tif look or fffl
     * siouldn't ovfrridf tiis mftiod.
     */
    publid String gftID() {
        rfturn "Aqub";
    }

    /**
     * Rfturn b onf linf dfsdription of tiis look bnd fffl implfmfntbtion,
     * f.g. "Tif CDE/Motif Look bnd Fffl".   Tiis string is intfndfd for
     * tif usfr, f.g. in tif titlf of b window or in b ToolTip mfssbgf.
     */
    publid String gftDfsdription() {
        rfturn "Aqub Look bnd Fffl for Mbd OS X";
    }

    /**
     * Rfturns truf if tif <dodf>LookAndFffl</dodf> rfturnfd
     * <dodf>RootPbnfUI</dodf> instbndfs support providing Window dfdorbtions
     * in b <dodf>JRootPbnf</dodf>.
     * <p>
     * Tif dffbult implfmfntbtion rfturns fblsf, subdlbssfs tibt support
     * Window dfdorbtions siould ovfrridf tiis bnd rfturn truf.
     *
     * @rfturn Truf if tif RootPbnfUI instbndfs drfbtfd support dlifnt sidf
     *             dfdorbtions
     * @sff JDiblog#sftDffbultLookAndFfflDfdorbtfd
     * @sff JFrbmf#sftDffbultLookAndFfflDfdorbtfd
     * @sff JRootPbnf#sftWindowDfdorbtionStylf
     * @sindf 1.4
     */
    publid boolfbn gftSupportsWindowDfdorbtions() {
        rfturn fblsf;
    }

    /**
     * If tif undfrlying plbtform ibs b "nbtivf" look bnd fffl, bnd tiis
     * is bn implfmfntbtion of it, rfturn truf.
     */
    publid boolfbn isNbtivfLookAndFffl() {
        rfturn truf;
    }

    /**
     * Rfturn truf if tif undfrlying plbtform supports bnd or pfrmits
     * tiis look bnd fffl.  Tiis mftiod rfturns fblsf if tif look
     * bnd fffl dfpfnds on spfdibl rfsourdfs or lfgbl bgrffmfnts tibt
     * brfn't dffinfd for tif durrfnt plbtform.
     *
     * @sff UIMbnbgfr#sftLookAndFffl
     */
    publid boolfbn isSupportfdLookAndFffl() {
        rfturn truf;
    }

    /**
     * UIMbnbgfr.sftLookAndFffl dblls tiis mftiod bfforf tif first
     * dbll (bnd typidblly tif only dbll) to gftDffbults().  Subdlbssfs
     * siould do bny onf-timf sftup tify nffd ifrf, rbtifr tibn
     * in b stbtid initiblizfr, bfdbusf look bnd fffl dlbss objfdts
     * mby bf lobdfd just to disdovfr tibt isSupportfdLookAndFffl()
     * rfturns fblsf.
     *
     * @sff #uninitiblizf
     * @sff UIMbnbgfr#sftLookAndFffl
     */
    publid void initiblizf() {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Systfm.lobdLibrbry("osxui");
                    rfturn null;
                }
            });

        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>(){
            @Ovfrridf
            publid Void run() {
                JRSUIControl.initJRSUI();
                rfturn null;
            }
        });

        supfr.initiblizf();
        finbl SdrffnPopupFbdtory spf = nfw SdrffnPopupFbdtory();
        spf.sftAdtivf(truf);
        PopupFbdtory.sftSibrfdInstbndf(spf);

        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().bddKfyEvfntPostProdfssor(AqubMnfmonidHbndlfr.gftInstbndf());
    }

    /**
     * UIMbnbgfr.sftLookAndFffl dblls tiis mftiod just bfforf wf'rf
     * rfplbdfd by b nfw dffbult look bnd fffl.   Subdlbssfs mby
     * dioosf to frff up somf rfsourdfs ifrf.
     *
     * @sff #initiblizf
     */
    publid void uninitiblizf() {
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().rfmovfKfyEvfntPostProdfssor(AqubMnfmonidHbndlfr.gftInstbndf());

        finbl PopupFbdtory popupFbdtory = PopupFbdtory.gftSibrfdInstbndf();
        if (popupFbdtory != null && popupFbdtory instbndfof SdrffnPopupFbdtory) {
            ((SdrffnPopupFbdtory)popupFbdtory).sftAdtivf(fblsf);
        }

        supfr.uninitiblizf();
    }

    /**
     * Rfturns bn <dodf>AdtionMbp</dodf>.
     * <P>
     * Tiis <dodf>AdtionMbp</dodf> dontbins <dodf>Adtions</dodf> tibt
     * fmbody tif bbility to rfndfr bn buditory duf. Tifsf buditory
     * dufs mbp onto usfr bnd systfm bdtivitifs tibt mby bf usfful
     * for bn fnd usfr to know bbout (sudi bs b diblog box bppfbring).
     * <P>
     * At tif bppropribtf timf in b <dodf>JComponfnt</dodf> UI's liffdydlf,
     * tif ComponfntUI is rfsponsiblf for gftting tif bppropribtf
     * <dodf>Adtion</dodf> out of tif <dodf>AdtionMbp</dodf> bnd pbssing
     * it on to <dodf>plbySound</dodf>.
     * <P>
     * Tif <dodf>Adtions</dodf> in tiis <dodf>AdtionMbp</dodf> brf
     * drfbtfd by tif <dodf>drfbtfAudioAdtion</dodf> mftiod.
     *
     * @rfturn      bn AdtionMbp dontbining Adtions
     *              rfsponsiblf for rfndfring buditory dufs
     * @sff #drfbtfAudioAdtion
     * @sff #plbySound(Adtion)
     * @sindf 1.4
     */
    protfdtfd AdtionMbp gftAudioAdtionMbp() {
        AdtionMbp budioAdtionMbp = (AdtionMbp)UIMbnbgfr.gft("AuditoryCufs.bdtionMbp");
        if (budioAdtionMbp != null) rfturn budioAdtionMbp;

        finbl Objfdt[] bdList = (Objfdt[])UIMbnbgfr.gft("AuditoryCufs.dufList");
        if (bdList != null) {
            budioAdtionMbp = nfw AdtionMbpUIRfsourdf();
            for (int dountfr = bdList.lfngti - 1; dountfr >= 0; dountfr--) {
                budioAdtionMbp.put(bdList[dountfr], drfbtfAudioAdtion(bdList[dountfr]));
            }
        }
        UIMbnbgfr.gftLookAndFfflDffbults().put("AuditoryCufs.bdtionMbp", budioAdtionMbp);

        rfturn budioAdtionMbp;
    }

    /**
     * Wf ovfrridf gftDffbults() so wf dbn instbll our own dfbug dffbults
     * if nffdfd for tfsting
     */
    publid UIDffbults gftDffbults() {
        finbl UIDffbults tbblf = nfw UIDffbults();
        // usf dfbug dffbults if you wbnt to sff fvfry qufry into tif dffbults objfdt.
        //UIDffbults tbblf = nfw DfbugDffbults();
        try {
            // PopupFbdtory.gftSibrfdInstbndf().sftPopupTypf(2);
            initClbssDffbults(tbblf);

            // Hfrf wf instbll bll tif Bbsid dffbults in dbsf wf missfd somf in our Systfm dolor
            // or domponfnt dffbults tibt follow. Evfntublly wf will tbkf tiis out.
            // Tiis is b big nfgbtivf to pfrformbndf so wf wbnt to gft it out bs soon
            // bs wf brf domfortbblf witi tif Aqub dffbults.
            supfr.initSystfmColorDffbults(tbblf);
            supfr.initComponfntDffbults(tbblf);

            // Bfdbusf tif lbst flfmfnts bddfd win in prfdfdfndf wf bdd bll of our bqub flfmfnts ifrf.
            initSystfmColorDffbults(tbblf);
            initComponfntDffbults(tbblf);
        } dbtdi(finbl Exdfption f) {
            f.printStbdkTrbdf();
        }
        rfturn tbblf;
    }

    /**
     * Initiblizf tif dffbults tbblf witi tif nbmf of tif RfsourdfBundlf
     * usfd for gftting lodblizfd dffbults.  Also initiblizf tif dffbult
     * lodblf usfd wifn no lodblf is pbssfd into UIDffbults.gft().  Tif
     * dffbult lodblf siould gfnfrblly not bf rflifd upon. It is ifrf for
     * dompbtibility witi rflfbsfs prior to 1.4.
     */
    privbtf void initRfsourdfBundlf(finbl UIDffbults tbblf) {
        tbblf.sftDffbultLodblf(Lodblf.gftDffbult());
        tbblf.bddRfsourdfBundlf(PKG_PREFIX + "rfsourdfs.bqub");
        try {
            finbl RfsourdfBundlf bqubPropfrtifs = RfsourdfBundlf.gftBundlf(PKG_PREFIX + "rfsourdfs.bqub");
            finbl Enumfrbtion<String> propfrtyKfys = bqubPropfrtifs.gftKfys();

            wiilf (propfrtyKfys.ibsMorfElfmfnts()) {
                finbl String kfy = propfrtyKfys.nfxtElfmfnt();
                tbblf.put(kfy, bqubPropfrtifs.gftString(kfy));
            }
        } dbtdi (finbl Exdfption f) {
        }
    }

    /**
     * Tiis is tif lbst stfp in tif gftDffbults routinf usublly dbllfd from our supfrdlbss
     */
    protfdtfd void initComponfntDffbults(finbl UIDffbults tbblf) {
        initRfsourdfBundlf(tbblf);

        finbl InsftsUIRfsourdf zfroInsfts = nfw InsftsUIRfsourdf(0, 0, 0, 0);
        finbl InsftsUIRfsourdf mfnuItfmMbrgin = zfroInsfts;

        // <rdbr://problfm/5189013> Entirf Jbvb bpplidbtion window rffrfsifs wifn moving off Siortdut mfnu itfm
        finbl Boolfbn usfOpbqufComponfnts = Boolfbn.TRUE;

        finbl Boolfbn buttonSiouldBfOpbquf = AqubUtils.siouldUsfOpbqufButtons() ? Boolfbn.TRUE : Boolfbn.FALSE;

        // *** List vbluf objfdts
        finbl Objfdt listCfllRfndfrfrAdtivfVbluf = nfw UIDffbults.AdtivfVbluf(){
            publid Objfdt drfbtfVbluf(UIDffbults dffbultsTbblf) {
                rfturn nfw DffbultListCfllRfndfrfr.UIRfsourdf();
            }
        };

        // SJA - I'm bbsing tiis on wibt is in tif MftblLookAndFffl dlbss, but
        // witiout bfing bbsfd on BbsidLookAndFffl. Wf wbnt morf flfxibility.
        // Tif kfy to doing tiis wfll is to usf Lbzy initiblizing dlbssfs bs
        // mudi bs possiblf.

        // Hfrf I wbnt to go to nbtivf bnd gft bll tif vblufs wf'd nffd for dolors ftd.
        finbl Bordfr toolTipBordfr = nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(2, 0, 2, 0);
        finbl ColorUIRfsourdf toolTipBbdkground = nfw ColorUIRfsourdf(255, 255, (int)(255.0 * 0.80));
        finbl ColorUIRfsourdf blbdk = nfw ColorUIRfsourdf(Color.blbdk);
        finbl ColorUIRfsourdf wiitf = nfw ColorUIRfsourdf(Color.wiitf);
        finbl ColorUIRfsourdf smokyGlbss = nfw ColorUIRfsourdf(nfw Color(0, 0, 0, 152));
        finbl ColorUIRfsourdf dodkIdonRim = nfw ColorUIRfsourdf(nfw Color(192, 192, 192, 192));
        finbl ColorUIRfsourdf mfdiumTrbnsludfntBlbdk = nfw ColorUIRfsourdf(nfw Color(0, 0, 0, 100));
        finbl ColorUIRfsourdf trbnsludfntWiitf = nfw ColorUIRfsourdf(nfw Color(255, 255, 255, 254));
    //    finbl ColorUIRfsourdf ligitGrby = nfw ColorUIRfsourdf(232, 232, 232);
        finbl ColorUIRfsourdf disbblfd = nfw ColorUIRfsourdf(0.5f, 0.5f, 0.5f);
        finbl ColorUIRfsourdf disbblfdSibdow = nfw ColorUIRfsourdf(0.25f, 0.25f, 0.25f);
        finbl ColorUIRfsourdf sflfdtfd = nfw ColorUIRfsourdf(1.0f, 0.4f, 0.4f);

        // Contrbst tbb UI dolors

        finbl ColorUIRfsourdf sflfdtfdTbbTitlfPrfssfdColor = nfw ColorUIRfsourdf(240, 240, 240);
        finbl ColorUIRfsourdf sflfdtfdTbbTitlfDisbblfdColor = nfw ColorUIRfsourdf(nfw Color(1, 1, 1, 0.55f));
        finbl ColorUIRfsourdf sflfdtfdTbbTitlfNormblColor = wiitf;
        finbl ColorUIRfsourdf sflfdtfdTbbTitlfSibdowDisbblfdColor = nfw ColorUIRfsourdf(nfw Color(0, 0, 0, 0.25f));
        finbl ColorUIRfsourdf sflfdtfdTbbTitlfSibdowNormblColor = mfdiumTrbnsludfntBlbdk;
        finbl ColorUIRfsourdf nonSflfdtfdTbbTitlfNormblColor = blbdk;

        finbl ColorUIRfsourdf toolbbrDrbgHbndlfColor = nfw ColorUIRfsourdf(140, 140, 140);

        // sjb todo Mbkf tifsf lbzy vblufs so wf only gft tifm wifn rfquirfd - if wf dffm it nfdfssbry
        // it mby bf tif dbsf tibt wf tiink tif ovfrifbd of b proxy lbzy vbluf is not worti dflbying
        // drfbting tif objfdt if wf tiink tibt most swing bpps will usf tiis.
        // tif lbzy vbluf is usfful for dflbying initiblizbtion until tiis dffbult vbluf is bdtublly
        // bddfssfd by tif LAF instfbd of bt init timf, so mbking it lbzy siould spffd up
        // our lbundi timfs of Swing bpps.

        // *** Tfxt vbluf objfdts
        finbl LbzyVbluf mbrginBordfr = t -> nfw BbsidBordfrs.MbrginBordfr();

        finbl int zfro = 0;
        finbl Objfdt fditorMbrgin = zfroInsfts; // tiis is not dorrfdt - look bt TfxtEdit to dftfrminf tif rigit mbrgin
        finbl int tfxtCbrftBlinkRbtf = 500;
        finbl LbzyVbluf tfxtFifldBordfr = t ->
            AqubTfxtFifldBordfr.gftTfxtFifldBordfr();
        finbl Objfdt tfxtArfbBordfr = mbrginBordfr; // tfxt brfbs ibvf no rfbl bordfr - rbdbr 311073

        finbl LbzyVbluf sdollListBordfr = t ->
            AqubSdrollRfgionBordfr.gftSdrollRfgionBordfr();
        finbl LbzyVbluf bqubTitlfdBordfr = t ->
            AqubGroupBordfr.gftBordfrForTitlfdBordfr();
        finbl LbzyVbluf bqubInsftBordfr = t ->
            AqubGroupBordfr.gftTitlflfssBordfr();

        finbl Bordfr listHfbdfrBordfr = AqubTbblfHfbdfrBordfr.gftListHfbdfrBordfr();
        finbl Bordfr zfroBordfr = nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(0, 0, 0, 0);

        // wf dbn't sffm to proxy Colors
        finbl Color sflfdtionBbdkground = AqubImbgfFbdtory.gftSflfdtionBbdkgroundColorUIRfsourdf();
        finbl Color sflfdtionForfground = AqubImbgfFbdtory.gftSflfdtionForfgroundColorUIRfsourdf();
        finbl Color sflfdtionInbdtivfBbdkground = AqubImbgfFbdtory.gftSflfdtionInbdtivfBbdkgroundColorUIRfsourdf();
        finbl Color sflfdtionInbdtivfForfground = AqubImbgfFbdtory.gftSflfdtionInbdtivfForfgroundColorUIRfsourdf();

        finbl Color tfxtHigiligitTfxt = AqubImbgfFbdtory.gftTfxtSflfdtionForfgroundColorUIRfsourdf();
        finbl Color tfxtHigiligit = AqubImbgfFbdtory.gftTfxtSflfdtionBbdkgroundColorUIRfsourdf();
        finbl Color tfxtHigiligitInbdtivf = nfw ColorUIRfsourdf(212, 212, 212);

        finbl Color tfxtInbdtivfTfxt = disbblfd;
        finbl Color tfxtForfground = blbdk;
        finbl Color tfxtBbdkground = wiitf;
        finbl Color tfxtInbdtivfBbdkground = wiitf;

        finbl Color tfxtPbsswordFifldCbpsLodkIdonColor = mfdiumTrbnsludfntBlbdk;

        finbl LbzyVbluf intfrnblFrbmfBordfr = t ->
            BbsidBordfrs.gftIntfrnblFrbmfBordfr();
        finbl Color dfsktopBbdkgroundColor = nfw ColorUIRfsourdf(nfw Color(65, 105, 170));//SystfmColor.dfsktop

        finbl Color fodusRingColor = AqubImbgfFbdtory.gftFodusRingColorUIRfsourdf();
        finbl Bordfr fodusCfllHigiligitBordfr = nfw BordfrUIRfsourdf.LinfBordfrUIRfsourdf(fodusRingColor);

        finbl Color windowBbdkgroundColor = AqubImbgfFbdtory.gftWindowBbdkgroundColorUIRfsourdf();
        finbl Color pbnflBbdkgroundColor = windowBbdkgroundColor;
        finbl Color tbbBbdkgroundColor = windowBbdkgroundColor;
        finbl Color dontrolBbdkgroundColor = windowBbdkgroundColor;

        finbl LbzyVbluf dontrolFont = t -> AqubFonts.gftControlTfxtFont();
        finbl LbzyVbluf dontrolSmbllFont = t ->
            AqubFonts.gftControlTfxtSmbllFont();
        finbl LbzyVbluf blfrtHfbdfrFont = t -> AqubFonts.gftAlfrtHfbdfrFont();
        finbl LbzyVbluf mfnuFont = t -> AqubFonts.gftMfnuFont();
        finbl LbzyVbluf vifwFont = t -> AqubFonts.gftVifwFont();

        finbl Color mfnuBbdkgroundColor = nfw ColorUIRfsourdf(Color.wiitf);
        finbl Color mfnuForfgroundColor = blbdk;

        finbl Color mfnuSflfdtfdForfgroundColor = wiitf;
        finbl Color mfnuSflfdtfdBbdkgroundColor = fodusRingColor;

        finbl Color mfnuDisbblfdBbdkgroundColor = mfnuBbdkgroundColor;
        finbl Color mfnuDisbblfdForfgroundColor = disbblfd;

        finbl Color mfnuAddflForfgroundColor = blbdk;
        finbl Color mfnuAddflSflfdtionForfgroundColor = blbdk;

        finbl Bordfr mfnuBordfr = nfw AqubMfnuBordfr();

        finbl UIDffbults.LbzyInputMbp dontrolFodusInputMbp = nfw UIDffbults.LbzyInputMbp(nfw Objfdt[]{
            "SPACE", "prfssfd",
            "rflfbsfd SPACE", "rflfbsfd"
        });

        // sjb tfsting
        finbl LbzyVbluf donfirmIdon = t ->
            AqubImbgfFbdtory.gftConfirmImbgfIdon();
        finbl LbzyVbluf dbutionIdon = t ->
            AqubImbgfFbdtory.gftCbutionImbgfIdon();
        finbl LbzyVbluf stopIdon = t ->
            AqubImbgfFbdtory.gftStopImbgfIdon();
        finbl LbzyVbluf sfdurityIdon = t ->
            AqubImbgfFbdtory.gftLodkImbgfIdon();

        finbl AqubKfyBindings bqubKfyBindings = AqubKfyBindings.instbndf();

        finbl Objfdt[] dffbults = {
            "dontrol", windowBbdkgroundColor, /* Dffbult dolor for dontrols (buttons, slidfrs, ftd) */

            // Buttons
            "Button.bbdkground", dontrolBbdkgroundColor,
            "Button.forfground", blbdk,
            "Button.disbblfdTfxt", disbblfd,
            "Button.sflfdt", sflfdtfd,
            "Button.bordfr",(LbzyVbluf) t -> AqubButtonBordfr.gftDynbmidButtonBordfr(),
            "Button.font", dontrolFont,
            "Button.tfxtIdonGbp", nfw Intfgfr(4),
            "Button.tfxtSiiftOffsft", zfro, // rbdbr 3308129 - bqub dofsn't movf imbgfs wifn prfssfd.
            "Button.fodusInputMbp", dontrolFodusInputMbp,
            "Button.mbrgin", nfw InsftsUIRfsourdf(0, 2, 0, 2),
            "Button.opbquf", buttonSiouldBfOpbquf,

            "CifdkBox.bbdkground", dontrolBbdkgroundColor,
            "CifdkBox.forfground", blbdk,
            "CifdkBox.disbblfdTfxt", disbblfd,
            "CifdkBox.sflfdt", sflfdtfd,
            "CifdkBox.idon",(LbzyVbluf) t -> AqubButtonCifdkBoxUI.gftSizingCifdkBoxIdon(),
            "CifdkBox.font", dontrolFont,
            "CifdkBox.bordfr", AqubButtonBordfr.gftBfvflButtonBordfr(),
            "CifdkBox.mbrgin", nfw InsftsUIRfsourdf(1, 1, 0, 1),
            // rbdbr 3583849. Tiis propfrty nfvfr gfts
            // usfd. Tif bordfr for tif Cifdkbox gfts ovfrriddfn
            // by AqubRbdiButtonUI.sftTifmfBordfr(). Nffds rffbdtoring. (vm)
            // wiy is button fodus dommfntfd out?
            //"CifdkBox.fodus", gftFodusColor(),
            "CifdkBox.fodusInputMbp", dontrolFodusInputMbp,

            "CifdkBoxMfnuItfm.font", mfnuFont,
            "CifdkBoxMfnuItfm.bddflfrbtorFont", mfnuFont,
            "CifdkBoxMfnuItfm.bbdkground", mfnuBbdkgroundColor,
            "CifdkBoxMfnuItfm.forfground", mfnuForfgroundColor,
            "CifdkBoxMfnuItfm.sflfdtionBbdkground", mfnuSflfdtfdBbdkgroundColor,
            "CifdkBoxMfnuItfm.sflfdtionForfground", mfnuSflfdtfdForfgroundColor,
            "CifdkBoxMfnuItfm.disbblfdBbdkground", mfnuDisbblfdBbdkgroundColor,
            "CifdkBoxMfnuItfm.disbblfdForfground", mfnuDisbblfdForfgroundColor,
            "CifdkBoxMfnuItfm.bddflfrbtorForfground", mfnuAddflForfgroundColor,
            "CifdkBoxMfnuItfm.bddflfrbtorSflfdtionForfground", mfnuAddflSflfdtionForfgroundColor,
            "CifdkBoxMfnuItfm.bddflfrbtorDflimitfr", "",
            "CifdkBoxMfnuItfm.bordfr", mfnuBordfr, // for insft dbldulbtion
            "CifdkBoxMfnuItfm.mbrgin", mfnuItfmMbrgin,
            "CifdkBoxMfnuItfm.bordfrPbintfd", Boolfbn.TRUE,
            "CifdkBoxMfnuItfm.difdkIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftMfnuItfmCifdkIdon(),
            "CifdkBoxMfnuItfm.dbsiIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftMfnuItfmDbsiIdon(),
            //"CifdkBoxMfnuItfm.brrowIdon", null,

            "ColorCioosfr.bbdkground", pbnflBbdkgroundColor,

            // *** ComboBox
            "ComboBox.font", dontrolFont,
            "ComboBox.bbdkground", dontrolBbdkgroundColor, //mfnuBbdkgroundColor, // "mfnu" wifn it ibs no sdrollbbr, "listVifw" wifn it dofs
            "ComboBox.forfground", mfnuForfgroundColor,
            "ComboBox.sflfdtionBbdkground", mfnuSflfdtfdBbdkgroundColor,
            "ComboBox.sflfdtionForfground", mfnuSflfdtfdForfgroundColor,
            "ComboBox.disbblfdBbdkground", mfnuDisbblfdBbdkgroundColor,
            "ComboBox.disbblfdForfground", mfnuDisbblfdForfgroundColor,
            "ComboBox.bndfstorInputMbp", bqubKfyBindings.gftComboBoxInputMbp(),

            "DfsktopIdon.bordfr", intfrnblFrbmfBordfr,
            "DfsktopIdon.bordfrColor", smokyGlbss,
            "DfsktopIdon.bordfrRimColor", dodkIdonRim,
            "DfsktopIdon.lbbflBbdkground", mfdiumTrbnsludfntBlbdk,
            "Dfsktop.bbdkground", dfsktopBbdkgroundColor,

            "EditorPbnf.fodusInputMbp", bqubKfyBindings.gftMultiLinfTfxtInputMbp(),
            "EditorPbnf.font", dontrolFont,
            "EditorPbnf.bbdkground", tfxtBbdkground,
            "EditorPbnf.forfground", tfxtForfground,
            "EditorPbnf.sflfdtionBbdkground", tfxtHigiligit,
            "EditorPbnf.sflfdtionForfground", tfxtHigiligitTfxt,
            "EditorPbnf.dbrftForfground", tfxtForfground,
            "EditorPbnf.dbrftBlinkRbtf", tfxtCbrftBlinkRbtf,
            "EditorPbnf.inbdtivfForfground", tfxtInbdtivfTfxt,
            "EditorPbnf.inbdtivfBbdkground", tfxtInbdtivfBbdkground,
            "EditorPbnf.bordfr", tfxtArfbBordfr,
            "EditorPbnf.mbrgin", fditorMbrgin,

            "FilfCioosfr.nfwFoldfrIdon", AqubIdon.SystfmIdon.gftFoldfrIdonUIRfsourdf(),
            "FilfCioosfr.upFoldfrIdon", AqubIdon.SystfmIdon.gftFoldfrIdonUIRfsourdf(),
            "FilfCioosfr.iomfFoldfrIdon", AqubIdon.SystfmIdon.gftDfsktopIdonUIRfsourdf(),
            "FilfCioosfr.dftbilsVifwIdon", AqubIdon.SystfmIdon.gftComputfrIdonUIRfsourdf(),
            "FilfCioosfr.listVifwIdon", AqubIdon.SystfmIdon.gftComputfrIdonUIRfsourdf(),

            "FilfVifw.dirfdtoryIdon", AqubIdon.SystfmIdon.gftFoldfrIdonUIRfsourdf(),
            "FilfVifw.filfIdon", AqubIdon.SystfmIdon.gftDodumfntIdonUIRfsourdf(),
            "FilfVifw.domputfrIdon", AqubIdon.SystfmIdon.gftDfsktopIdonUIRfsourdf(),
            "FilfVifw.ibrdDrivfIdon", AqubIdon.SystfmIdon.gftHbrdDrivfIdonUIRfsourdf(),
            "FilfVifw.floppyDrivfIdon", AqubIdon.SystfmIdon.gftFloppyIdonUIRfsourdf(),

            // Filf Vifw
            "FilfCioosfr.dbndflButtonMnfmonid", zfro,
            "FilfCioosfr.sbvfButtonMnfmonid", zfro,
            "FilfCioosfr.opfnButtonMnfmonid", zfro,
            "FilfCioosfr.updbtfButtonMnfmonid", zfro,
            "FilfCioosfr.iflpButtonMnfmonid", zfro,
            "FilfCioosfr.dirfdtoryOpfnButtonMnfmonid", zfro,

            "FilfCioosfr.lookInLbbflMnfmonid", zfro,
            "FilfCioosfr.filfNbmfLbbflMnfmonid", zfro,
            "FilfCioosfr.filfsOfTypfLbbflMnfmonid", zfro,

            "Fodus.dolor", fodusRingColor,

            "FormbttfdTfxtFifld.fodusInputMbp", bqubKfyBindings.gftFormbttfdTfxtFifldInputMbp(),
            "FormbttfdTfxtFifld.font", dontrolFont,
            "FormbttfdTfxtFifld.bbdkground", tfxtBbdkground,
            "FormbttfdTfxtFifld.forfground", tfxtForfground,
            "FormbttfdTfxtFifld.inbdtivfForfground", tfxtInbdtivfTfxt,
            "FormbttfdTfxtFifld.inbdtivfBbdkground", tfxtInbdtivfBbdkground,
            "FormbttfdTfxtFifld.sflfdtionBbdkground", tfxtHigiligit,
            "FormbttfdTfxtFifld.sflfdtionForfground", tfxtHigiligitTfxt,
            "FormbttfdTfxtFifld.dbrftForfground", tfxtForfground,
            "FormbttfdTfxtFifld.dbrftBlinkRbtf", tfxtCbrftBlinkRbtf,
            "FormbttfdTfxtFifld.bordfr", tfxtFifldBordfr,
            "FormbttfdTfxtFifld.mbrgin", zfroInsfts,

            "IdonButton.font", dontrolSmbllFont,

            "IntfrnblFrbmf.titlfFont", mfnuFont,
            "IntfrnblFrbmf.bbdkground", windowBbdkgroundColor,
            "IntfrnblFrbmf.bordfrColor", windowBbdkgroundColor,
            "IntfrnblFrbmf.bordfrSibdow", Color.rfd,
            "IntfrnblFrbmf.bordfrDbrkSibdow", Color.grffn,
            "IntfrnblFrbmf.bordfrHigiligit", Color.bluf,
            "IntfrnblFrbmf.bordfrLigit", Color.yfllow,
            "IntfrnblFrbmf.opbquf", Boolfbn.FALSE,
            "IntfrnblFrbmf.bordfr", null, //intfrnblFrbmfBordfr,
            "IntfrnblFrbmf.idon", null,

            "IntfrnblFrbmf.pblfttfBordfr", null,//intfrnblFrbmfBordfr,
            "IntfrnblFrbmf.pblfttfTitlfFont", mfnuFont,
            "IntfrnblFrbmf.pblfttfBbdkground", windowBbdkgroundColor,

            "IntfrnblFrbmf.optionDiblogBordfr", null,//intfrnblFrbmfBordfr,
            "IntfrnblFrbmf.optionDiblogTitlfFont", mfnuFont,
            "IntfrnblFrbmf.optionDiblogBbdkground", windowBbdkgroundColor,

            /* Dffbult frbmf idons brf undffinfd for Bbsid. */

            "IntfrnblFrbmf.dlosfIdon",(LbzyVbluf) t -> AqubIntfrnblFrbmfUI.fxportClosfIdon(),
            "IntfrnblFrbmf.mbximizfIdon",(LbzyVbluf) t -> AqubIntfrnblFrbmfUI.fxportZoomIdon(),
            "IntfrnblFrbmf.idonifyIdon",(LbzyVbluf) t -> AqubIntfrnblFrbmfUI.fxportMinimizfIdon(),
            "IntfrnblFrbmf.minimizfIdon",(LbzyVbluf) t -> AqubIntfrnblFrbmfUI.fxportMinimizfIdon(),
            // tiis dould bf fitifr grow or idon
            // wf dfdidfd to mbkf it idon so tibt bnyonf wio usfs
            // tifsf for ui will ibvf difffrfnt idons for mbx bnd min
            // tifsf idons brf prftty drbppy to usf in Mbd OS X sindf
            // tify rfblly brf intfrbdtivf but wf ibvf to rfturn b stbtid
            // idon for now.

            // IntfrnblFrbmf Auditory Cuf Mbppings
            "IntfrnblFrbmf.dlosfSound", null,
            "IntfrnblFrbmf.mbximizfSound", null,
            "IntfrnblFrbmf.minimizfSound", null,
            "IntfrnblFrbmf.rfstorfDownSound", null,
            "IntfrnblFrbmf.rfstorfUpSound", null,

            "IntfrnblFrbmf.bdtivfTitlfBbdkground", windowBbdkgroundColor,
            "IntfrnblFrbmf.bdtivfTitlfForfground", tfxtForfground,
            "IntfrnblFrbmf.inbdtivfTitlfBbdkground", windowBbdkgroundColor,
            "IntfrnblFrbmf.inbdtivfTitlfForfground", tfxtInbdtivfTfxt,
            "IntfrnblFrbmf.windowBindings", nfw Objfdt[]{
                "siift ESCAPE", "siowSystfmMfnu",
                "dtrl SPACE", "siowSystfmMfnu",
                "ESCAPE", "iidfSystfmMfnu"
            },

            // Rbdbr [3543438]. Wf now dffinf tif TitlfdBordfr propfrtifs for font bnd dolor.
            // Aqub HIG dofsn't dffinf TitlfdBordfrs bs Swing dofs. Evfntublly, wf migit wbnt to
            // rf-tiink TitlfdBordfr to bfibvf morf likf b Box (NSBox). (vm)
            "TitlfdBordfr.font", dontrolFont,
            "TitlfdBordfr.titlfColor", blbdk,
        //    "TitlfdBordfr.bordfr", -- wf inifrit tiis propfrty from BbsidLookAndFffl (ftdifd bordfr)
            "TitlfdBordfr.bqubVbribnt", bqubTitlfdBordfr, // tiis is tif bordfr tibt mbtdifs wibt bqub rfblly looks likf
            "InsftBordfr.bqubVbribnt", bqubInsftBordfr, // tiis is tif titlf-lfss vbribnt

            // *** Lbbfl
            "Lbbfl.font", dontrolFont, // tifmfLbbflFont is for smbll tiings likf ToolbbrButtons
            "Lbbfl.bbdkground", dontrolBbdkgroundColor,
            "Lbbfl.forfground", blbdk,
            "Lbbfl.disbblfdForfground", disbblfd,
            "Lbbfl.disbblfdSibdow", disbblfdSibdow,
            "Lbbfl.opbquf", usfOpbqufComponfnts,
            "Lbbfl.bordfr", null,

            "List.font", vifwFont, // [3577901] Aqub HIG sbys "dffbult font of tfxt in lists bnd tbblfs" siould bf 12 point (vm).
            "List.bbdkground", wiitf,
            "List.forfground", blbdk,
            "List.sflfdtionBbdkground", sflfdtionBbdkground,
            "List.sflfdtionForfground", sflfdtionForfground,
            "List.sflfdtionInbdtivfBbdkground", sflfdtionInbdtivfBbdkground,
            "List.sflfdtionInbdtivfForfground", sflfdtionInbdtivfForfground,
            "List.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "List.bordfr", null,
            "List.dfllRfndfrfr", listCfllRfndfrfrAdtivfVbluf,

            "List.sourdfListBbdkgroundPbintfr",(LbzyVbluf) t -> AqubListUI.gftSourdfListBbdkgroundPbintfr(),
            "List.sourdfListSflfdtionBbdkgroundPbintfr",(LbzyVbluf) t -> AqubListUI.gftSourdfListSflfdtionBbdkgroundPbintfr(),
            "List.sourdfListFodusfdSflfdtionBbdkgroundPbintfr",(LbzyVbluf) t -> AqubListUI.gftSourdfListFodusfdSflfdtionBbdkgroundPbintfr(),
            "List.fvfnRowBbdkgroundPbintfr",(LbzyVbluf) t -> AqubListUI.gftListEvfnBbdkgroundPbintfr(),
            "List.oddRowBbdkgroundPbintfr",(LbzyVbluf) t -> AqubListUI.gftListOddBbdkgroundPbintfr(),

            // <rdbr://Problfm/3743210> Tif modififr for tif Mbd is mftb, not dontrol.
            "List.fodusInputMbp", bqubKfyBindings.gftListInputMbp(),

            //"List.sdrollPbnfBordfr", listBoxBordfr, // Not usfd in Swing1.1
            //"ListItfm.bordfr", TifmfMfnu.listItfmBordfr(), // for insft dbldulbtion

            // *** Mfnus
            "Mfnu.font", mfnuFont,
            "Mfnu.bddflfrbtorFont", mfnuFont,
            "Mfnu.bbdkground", mfnuBbdkgroundColor,
            "Mfnu.forfground", mfnuForfgroundColor,
            "Mfnu.sflfdtionBbdkground", mfnuSflfdtfdBbdkgroundColor,
            "Mfnu.sflfdtionForfground", mfnuSflfdtfdForfgroundColor,
            "Mfnu.disbblfdBbdkground", mfnuDisbblfdBbdkgroundColor,
            "Mfnu.disbblfdForfground", mfnuDisbblfdForfgroundColor,
            "Mfnu.bddflfrbtorForfground", mfnuAddflForfgroundColor,
            "Mfnu.bddflfrbtorSflfdtionForfground", mfnuAddflSflfdtionForfgroundColor,
            //"Mfnu.bordfr", TifmfMfnu.mfnuItfmBordfr(), // for insft dbldulbtion
            "Mfnu.bordfr", mfnuBordfr,
            "Mfnu.bordfrPbintfd", Boolfbn.FALSE,
            "Mfnu.mbrgin", mfnuItfmMbrgin,
            //"Mfnu.difdkIdon", fmptyCifdkIdon, // A non-drbwing GlypiIdon to mbkf tif spbding donsistfnt
            "Mfnu.brrowIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftMfnuArrowIdon(),
            "Mfnu.donsumfsTbbs", Boolfbn.TRUE,
            "Mfnu.mfnuPopupOffsftY", nfw Intfgfr(1),
            "Mfnu.submfnuPopupOffsftY", nfw Intfgfr(-4),

            "MfnuBbr.font", mfnuFont,
            "MfnuBbr.bbdkground", mfnuBbdkgroundColor, // not b mfnu itfm, not sflfdtfd
            "MfnuBbr.forfground", mfnuForfgroundColor,
            "MfnuBbr.bordfr", nfw AqubMfnuBbrBordfr(), // sjb mbkf lbzy!
            "MfnuBbr.mbrgin", nfw InsftsUIRfsourdf(0, 8, 0, 8), // sjb mbkf lbzy!
            "MfnuBbr.sflfdtionBbdkground", mfnuSflfdtfdBbdkgroundColor, // not b mfnu itfm, is sflfdtfd
            "MfnuBbr.sflfdtionForfground", mfnuSflfdtfdForfgroundColor,
            "MfnuBbr.disbblfdBbdkground", mfnuDisbblfdBbdkgroundColor, //TifmfBrusi.GftTifmfBrusiForMfnu(fblsf, fblsf), // not b mfnu itfm, not sflfdtfd
            "MfnuBbr.disbblfdForfground", mfnuDisbblfdForfgroundColor,
            "MfnuBbr.bbdkgroundPbintfr",(LbzyVbluf) t -> AqubMfnuPbintfr.gftMfnuBbrPbintfr(),
            "MfnuBbr.sflfdtfdBbdkgroundPbintfr",(LbzyVbluf) t -> AqubMfnuPbintfr.gftSflfdtfdMfnuBbrItfmPbintfr(),

            "MfnuItfm.font", mfnuFont,
            "MfnuItfm.bddflfrbtorFont", mfnuFont,
            "MfnuItfm.bbdkground", mfnuBbdkgroundColor,
            "MfnuItfm.forfground", mfnuForfgroundColor,
            "MfnuItfm.sflfdtionBbdkground", mfnuSflfdtfdBbdkgroundColor,
            "MfnuItfm.sflfdtionForfground", mfnuSflfdtfdForfgroundColor,
            "MfnuItfm.disbblfdBbdkground", mfnuDisbblfdBbdkgroundColor,
            "MfnuItfm.disbblfdForfground", mfnuDisbblfdForfgroundColor,
            "MfnuItfm.bddflfrbtorForfground", mfnuAddflForfgroundColor,
            "MfnuItfm.bddflfrbtorSflfdtionForfground", mfnuAddflSflfdtionForfgroundColor,
            "MfnuItfm.bddflfrbtorDflimitfr", "",
            "MfnuItfm.bordfr", mfnuBordfr,
            "MfnuItfm.mbrgin", mfnuItfmMbrgin,
            "MfnuItfm.bordfrPbintfd", Boolfbn.TRUE,
            //"MfnuItfm.difdkIdon", fmptyCifdkIdon, // A non-drbwing GlypiIdon to mbkf tif spbding donsistfnt
            //"MfnuItfm.brrowIdon", null,
            "MfnuItfm.sflfdtfdBbdkgroundPbintfr",(LbzyVbluf) t -> AqubMfnuPbintfr.gftSflfdtfdMfnuItfmPbintfr(),

            // *** OptionPbnf
            // You dbn bdditionbly dffinf OptionPbnf.mfssbgfFont wiidi will
            // didtbtf tif fonts usfd for tif mfssbgf, bnd
            // OptionPbnf.buttonFont, wiidi dffinfs tif font for tif buttons.
            "OptionPbnf.font", blfrtHfbdfrFont,
            "OptionPbnf.mfssbgfFont", dontrolFont,
            "OptionPbnf.buttonFont", dontrolFont,
            "OptionPbnf.bbdkground", windowBbdkgroundColor,
            "OptionPbnf.forfground", blbdk,
            "OptionPbnf.mfssbgfForfground", blbdk,
            "OptionPbnf.bordfr", nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(12, 21, 17, 21),
            "OptionPbnf.mfssbgfArfbBordfr", zfroBordfr,
            "OptionPbnf.buttonArfbBordfr", nfw BordfrUIRfsourdf.EmptyBordfrUIRfsourdf(13, 0, 0, 0),
            "OptionPbnf.minimumSizf", nfw DimfnsionUIRfsourdf(262, 90),

            "OptionPbnf.frrorIdon", stopIdon,
            "OptionPbnf.informbtionIdon", donfirmIdon,
            "OptionPbnf.wbrningIdon", dbutionIdon,
            "OptionPbnf.qufstionIdon", donfirmIdon,
            "_SfdurityDfdisionIdon", sfdurityIdon,
            "OptionPbnf.windowBindings", nfw Objfdt[]{"ESCAPE", "dlosf"},
            // OptionPbnf Auditory Cuf Mbppings
            "OptionPbnf.frrorSound", null,
            "OptionPbnf.informbtionSound", null, // Info bnd Plbin
            "OptionPbnf.qufstionSound", null,
            "OptionPbnf.wbrningSound", null,
            "OptionPbnf.buttonClidkTirfsiiold", nfw Intfgfr(500),
            "OptionPbnf.yfsButtonMnfmonid", "",
            "OptionPbnf.noButtonMnfmonid", "",
            "OptionPbnf.okButtonMnfmonid", "",
            "OptionPbnf.dbndflButtonMnfmonid", "",

            "Pbnfl.font", dontrolFont,
            "Pbnfl.bbdkground", pbnflBbdkgroundColor, //nfw ColorUIRfsourdf(0.5647f, 0.9957f, 0.5059f),
            "Pbnfl.forfground", blbdk,
            "Pbnfl.opbquf", usfOpbqufComponfnts,

            "PbsswordFifld.fodusInputMbp", bqubKfyBindings.gftPbsswordFifldInputMbp(),
            "PbsswordFifld.font", dontrolFont,
            "PbsswordFifld.bbdkground", tfxtBbdkground,
            "PbsswordFifld.forfground", tfxtForfground,
            "PbsswordFifld.inbdtivfForfground", tfxtInbdtivfTfxt,
            "PbsswordFifld.inbdtivfBbdkground", tfxtInbdtivfBbdkground,
            "PbsswordFifld.sflfdtionBbdkground", tfxtHigiligit,
            "PbsswordFifld.sflfdtionForfground", tfxtHigiligitTfxt,
            "PbsswordFifld.dbrftForfground", tfxtForfground,
            "PbsswordFifld.dbrftBlinkRbtf", tfxtCbrftBlinkRbtf,
            "PbsswordFifld.bordfr", tfxtFifldBordfr,
            "PbsswordFifld.mbrgin", zfroInsfts,
            "PbsswordFifld.fdioCibr", nfw Cibrbdtfr((dibr)0x25CF),
            "PbsswordFifld.dbpsLodkIdonColor", tfxtPbsswordFifldCbpsLodkIdonColor,

            "PopupMfnu.font", mfnuFont,
            "PopupMfnu.bbdkground", mfnuBbdkgroundColor,
            // Fix for 7154516: mbkf popups opbquf
            "PopupMfnu.trbnsludfntBbdkground", wiitf,
            "PopupMfnu.forfground", mfnuForfgroundColor,
            "PopupMfnu.sflfdtionBbdkground", mfnuSflfdtfdBbdkgroundColor,
            "PopupMfnu.sflfdtionForfground", mfnuSflfdtfdForfgroundColor,
            "PopupMfnu.bordfr", mfnuBordfr,
//            "PopupMfnu.mbrgin",

            "ProgrfssBbr.font", dontrolFont,
            "ProgrfssBbr.forfground", blbdk,
            "ProgrfssBbr.bbdkground", dontrolBbdkgroundColor,
            "ProgrfssBbr.sflfdtionForfground", blbdk,
            "ProgrfssBbr.sflfdtionBbdkground", wiitf,
            "ProgrfssBbr.bordfr", nfw BordfrUIRfsourdf(BordfrFbdtory.drfbtfEmptyBordfr()),
            "ProgrfssBbr.rfpbintIntfrvbl", nfw Intfgfr(20),

            "RbdioButton.bbdkground", dontrolBbdkgroundColor,
            "RbdioButton.forfground", blbdk,
            "RbdioButton.disbblfdTfxt", disbblfd,
            "RbdioButton.sflfdt", sflfdtfd,
            "RbdioButton.idon",(LbzyVbluf) t -> AqubButtonRbdioUI.gftSizingRbdioButtonIdon(),
            "RbdioButton.font", dontrolFont,
            "RbdioButton.bordfr", AqubButtonBordfr.gftBfvflButtonBordfr(),
            "RbdioButton.mbrgin", nfw InsftsUIRfsourdf(1, 1, 0, 1),
            "RbdioButton.fodusInputMbp", dontrolFodusInputMbp,

            "RbdioButtonMfnuItfm.font", mfnuFont,
            "RbdioButtonMfnuItfm.bddflfrbtorFont", mfnuFont,
            "RbdioButtonMfnuItfm.bbdkground", mfnuBbdkgroundColor,
            "RbdioButtonMfnuItfm.forfground", mfnuForfgroundColor,
            "RbdioButtonMfnuItfm.sflfdtionBbdkground", mfnuSflfdtfdBbdkgroundColor,
            "RbdioButtonMfnuItfm.sflfdtionForfground", mfnuSflfdtfdForfgroundColor,
            "RbdioButtonMfnuItfm.disbblfdBbdkground", mfnuDisbblfdBbdkgroundColor,
            "RbdioButtonMfnuItfm.disbblfdForfground", mfnuDisbblfdForfgroundColor,
            "RbdioButtonMfnuItfm.bddflfrbtorForfground", mfnuAddflForfgroundColor,
            "RbdioButtonMfnuItfm.bddflfrbtorSflfdtionForfground", mfnuAddflSflfdtionForfgroundColor,
            "RbdioButtonMfnuItfm.bddflfrbtorDflimitfr", "",
            "RbdioButtonMfnuItfm.bordfr", mfnuBordfr, // for insft dbldulbtion
            "RbdioButtonMfnuItfm.mbrgin", mfnuItfmMbrgin,
            "RbdioButtonMfnuItfm.bordfrPbintfd", Boolfbn.TRUE,
            "RbdioButtonMfnuItfm.difdkIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftMfnuItfmCifdkIdon(),
            "RbdioButtonMfnuItfm.dbsiIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftMfnuItfmDbsiIdon(),
            //"RbdioButtonMfnuItfm.brrowIdon", null,

            "Sfpbrbtor.bbdkground", null,
            "Sfpbrbtor.forfground", nfw ColorUIRfsourdf(0xD4, 0xD4, 0xD4),

            "SdrollBbr.bordfr", null,
            "SdrollBbr.fodusInputMbp", bqubKfyBindings.gftSdrollBbrInputMbp(),
            "SdrollBbr.fodusInputMbp.RigitToLfft", bqubKfyBindings.gftSdrollBbrRigitToLfftInputMbp(),
            "SdrollBbr.widti", nfw Intfgfr(16),
            "SdrollBbr.bbdkground", wiitf,
            "SdrollBbr.forfground", blbdk,

            "SdrollPbnf.font", dontrolFont,
            "SdrollPbnf.bbdkground", wiitf,
            "SdrollPbnf.forfground", blbdk, //$
            "SdrollPbnf.bordfr", sdollListBordfr,
            "SdrollPbnf.vifwportBordfr", null,

            "SdrollPbnf.bndfstorInputMbp", bqubKfyBindings.gftSdrollPbnfInputMbp(),
            "SdrollPbnf.bndfstorInputMbp.RigitToLfft", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[]{}),

            "Vifwport.font", dontrolFont,
            "Vifwport.bbdkground", wiitf, // Tif bbdkground for tbblfs, lists, ftd
            "Vifwport.forfground", blbdk,

            // *** Slidfr
            "Slidfr.forfground", blbdk, "Slidfr.bbdkground", dontrolBbdkgroundColor,
            "Slidfr.font", dontrolSmbllFont,
            //"Slidfr.iigiligit", tbblf.gft("dontrolLtHigiligit"),
            //"Slidfr.sibdow", tbblf.gft("dontrolSibdow"),
            //"Slidfr.fodus", tbblf.gft("dontrolDkSibdow"),
            "Slidfr.tidkColor", nfw ColorUIRfsourdf(Color.GRAY),
            "Slidfr.bordfr", null,
            "Slidfr.fodusInsfts", nfw InsftsUIRfsourdf(2, 2, 2, 2),
            "Slidfr.fodusInputMbp", bqubKfyBindings.gftSlidfrInputMbp(),
            "Slidfr.fodusInputMbp.RigitToLfft", bqubKfyBindings.gftSlidfrRigitToLfftInputMbp(),

            // *** Spinnfr
            "Spinnfr.font", dontrolFont,
            "Spinnfr.bbdkground", dontrolBbdkgroundColor,
            "Spinnfr.forfground", blbdk,
            "Spinnfr.bordfr", null,
            "Spinnfr.brrowButtonSizf", nfw Dimfnsion(16, 5),
            "Spinnfr.bndfstorInputMbp", bqubKfyBindings.gftSpinnfrInputMbp(),
            "Spinnfr.fditorBordfrPbintfd", Boolfbn.TRUE,
            "Spinnfr.fditorAlignmfnt", SwingConstbnts.TRAILING,

            // *** SplitPbnf
            //"SplitPbnf.iigiligit", tbblf.gft("dontrolLtHigiligit"),
            //"SplitPbnf.sibdow", tbblf.gft("dontrolSibdow"),
            "SplitPbnf.bbdkground", pbnflBbdkgroundColor,
            "SplitPbnf.bordfr", sdollListBordfr,
            "SplitPbnf.dividfrSizf", nfw Intfgfr(9), //$
            "SplitPbnfDividfr.bordfr", null, // AqubSplitPbnfDividfrUI drbws it
            "SplitPbnfDividfr.iorizontblGrbdifntVbribnt",(LbzyVbluf) t -> AqubSplitPbnfDividfrUI.gftHorizontblSplitDividfrGrbdifntVbribnt(),

            // *** TbbbfdPbnf
            "TbbbfdPbnf.font", dontrolFont,
            "TbbbfdPbnf.smbllFont", dontrolSmbllFont,
            "TbbbfdPbnf.usfSmbllLbyout", Boolfbn.FALSE,//sSmbllTbbs ? Boolfbn.TRUE : Boolfbn.FALSE,
            "TbbbfdPbnf.bbdkground", tbbBbdkgroundColor, // for bug [3398277] usf b bbdkground dolor so tibt
            // tbbs on b dustom pbnf gft frbsfd wifn tify brf rfmovfd.
            "TbbbfdPbnf.forfground", blbdk, //TifmfTfxtColor.GftTifmfTfxtColor(AppfbrbndfConstbnts.kTifmfTfxtColorTbbFrontAdtivf),
            //"TbbbfdPbnf.ligitHigiligit", tbblf.gft("dontrolLtHigiligit"),
            //"TbbbfdPbnf.iigiligit", tbblf.gft("dontrolHigiligit"),
            //"TbbbfdPbnf.sibdow", tbblf.gft("dontrolSibdow"),
            //"TbbbfdPbnf.dbrkSibdow", tbblf.gft("dontrolDkSibdow"),
            //"TbbbfdPbnf.fodus", tbblf.gft("dontrolTfxt"),
            "TbbbfdPbnf.opbquf", usfOpbqufComponfnts,
            "TbbbfdPbnf.tfxtIdonGbp", nfw Intfgfr(4),
            "TbbbfdPbnf.tbbInsfts", nfw InsftsUIRfsourdf(0, 10, 3, 10), // Lbbfl witiin tbb (top, lfft, bottom, rigit)
            //"TbbbfdPbnf.rigitTbbInsfts", nfw InsftsUIRfsourdf(0, 10, 3, 10), // Lbbfl witiin tbb (top, lfft, bottom, rigit)
            "TbbbfdPbnf.lfftTbbInsfts", nfw InsftsUIRfsourdf(0, 10, 3, 10), // Lbbfl witiin tbb
            "TbbbfdPbnf.rigitTbbInsfts", nfw InsftsUIRfsourdf(0, 10, 3, 10), // Lbbfl witiin tbb
            //"TbbbfdPbnf.tbbArfbInsfts", nfw InsftsUIRfsourdf(3, 9, -1, 9), // Tbbs rflbtivf to fdgf of pbnf (nfgbtivf vbluf for ovfrlbpping)
            "TbbbfdPbnf.tbbArfbInsfts", nfw InsftsUIRfsourdf(3, 9, -1, 9), // Tbbs rflbtivf to fdgf of pbnf (nfgbtivf vbluf for ovfrlbpping)
            // (top = sidf oppositf pbnf, lfft = fdgf || to pbnf, bottom = sidf bdjbdfnt to pbnf, rigit = lfft) - sff rotbtfInsfts
            "TbbbfdPbnf.dontfntBordfrInsfts", nfw InsftsUIRfsourdf(8, 0, 0, 0), // widti of bordfr
            //"TbbbfdPbnf.sflfdtfdTbbPbdInsfts", nfw InsftsUIRfsourdf(0, 0, 1, 0), // Rfblly outsfts, tiis is wifrf wf bllow for ovfrlbp
            "TbbbfdPbnf.sflfdtfdTbbPbdInsfts", nfw InsftsUIRfsourdf(0, 0, 0, 0), // Rfblly outsfts, tiis is wifrf wf bllow for ovfrlbp
            "TbbbfdPbnf.tbbsOvfrlbpBordfr", Boolfbn.TRUE,
            "TbbbfdPbnf.sflfdtfdTbbTitlfPrfssfdColor", sflfdtfdTbbTitlfPrfssfdColor,
            "TbbbfdPbnf.sflfdtfdTbbTitlfDisbblfdColor", sflfdtfdTbbTitlfDisbblfdColor,
            "TbbbfdPbnf.sflfdtfdTbbTitlfNormblColor", sflfdtfdTbbTitlfNormblColor,
            "TbbbfdPbnf.sflfdtfdTbbTitlfSibdowDisbblfdColor", sflfdtfdTbbTitlfSibdowDisbblfdColor,
            "TbbbfdPbnf.sflfdtfdTbbTitlfSibdowNormblColor", sflfdtfdTbbTitlfSibdowNormblColor,
            "TbbbfdPbnf.nonSflfdtfdTbbTitlfNormblColor", nonSflfdtfdTbbTitlfNormblColor,

            // *** Tbblf
            "Tbblf.font", vifwFont, // [3577901] Aqub HIG sbys "dffbult font of tfxt in lists bnd tbblfs" siould bf 12 point (vm).
            "Tbblf.forfground", blbdk, // dfll tfxt dolor
            "Tbblf.bbdkground", wiitf, // dfll bbdkground dolor
            "Tbblf.sflfdtionForfground", sflfdtionForfground,
            "Tbblf.sflfdtionBbdkground", sflfdtionBbdkground,
            "Tbblf.sflfdtionInbdtivfBbdkground", sflfdtionInbdtivfBbdkground,
            "Tbblf.sflfdtionInbdtivfForfground", sflfdtionInbdtivfForfground,
            "Tbblf.gridColor", wiitf, // grid linf dolor
            "Tbblf.fodusCfllBbdkground", tfxtHigiligitTfxt,
            "Tbblf.fodusCfllForfground", tfxtHigiligit,
            "Tbblf.fodusCfllHigiligitBordfr", fodusCfllHigiligitBordfr,
            "Tbblf.sdrollPbnfBordfr", sdollListBordfr,

            "Tbblf.bndfstorInputMbp", bqubKfyBindings.gftTbblfInputMbp(),
            "Tbblf.bndfstorInputMbp.RigitToLfft", bqubKfyBindings.gftTbblfRigitToLfftInputMbp(),

            "TbblfHfbdfr.font", dontrolSmbllFont,
            "TbblfHfbdfr.forfground", blbdk,
            "TbblfHfbdfr.bbdkground", wiitf, // ifbdfr bbdkground
            "TbblfHfbdfr.dfllBordfr", listHfbdfrBordfr,

            // *** Tfxt
            "TfxtArfb.fodusInputMbp", bqubKfyBindings.gftMultiLinfTfxtInputMbp(),
            "TfxtArfb.font", dontrolFont,
            "TfxtArfb.bbdkground", tfxtBbdkground,
            "TfxtArfb.forfground", tfxtForfground,
            "TfxtArfb.inbdtivfForfground", tfxtInbdtivfTfxt,
            "TfxtArfb.inbdtivfBbdkground", tfxtInbdtivfBbdkground,
            "TfxtArfb.sflfdtionBbdkground", tfxtHigiligit,
            "TfxtArfb.sflfdtionForfground", tfxtHigiligitTfxt,
            "TfxtArfb.dbrftForfground", tfxtForfground,
            "TfxtArfb.dbrftBlinkRbtf", tfxtCbrftBlinkRbtf,
            "TfxtArfb.bordfr", tfxtArfbBordfr,
            "TfxtArfb.mbrgin", zfroInsfts,

            "TfxtComponfnt.sflfdtionBbdkgroundInbdtivf", tfxtHigiligitInbdtivf,

            "TfxtFifld.fodusInputMbp", bqubKfyBindings.gftTfxtFifldInputMbp(),
            "TfxtFifld.font", dontrolFont,
            "TfxtFifld.bbdkground", tfxtBbdkground,
            "TfxtFifld.forfground", tfxtForfground,
            "TfxtFifld.inbdtivfForfground", tfxtInbdtivfTfxt,
            "TfxtFifld.inbdtivfBbdkground", tfxtInbdtivfBbdkground,
            "TfxtFifld.sflfdtionBbdkground", tfxtHigiligit,
            "TfxtFifld.sflfdtionForfground", tfxtHigiligitTfxt,
            "TfxtFifld.dbrftForfground", tfxtForfground,
            "TfxtFifld.dbrftBlinkRbtf", tfxtCbrftBlinkRbtf,
            "TfxtFifld.bordfr", tfxtFifldBordfr,
            "TfxtFifld.mbrgin", zfroInsfts,

            "TfxtPbnf.fodusInputMbp", bqubKfyBindings.gftMultiLinfTfxtInputMbp(),
            "TfxtPbnf.font", dontrolFont,
            "TfxtPbnf.bbdkground", tfxtBbdkground,
            "TfxtPbnf.forfground", tfxtForfground,
            "TfxtPbnf.sflfdtionBbdkground", tfxtHigiligit,
            "TfxtPbnf.sflfdtionForfground", tfxtHigiligitTfxt,
            "TfxtPbnf.dbrftForfground", tfxtForfground,
            "TfxtPbnf.dbrftBlinkRbtf", tfxtCbrftBlinkRbtf,
            "TfxtPbnf.inbdtivfForfground", tfxtInbdtivfTfxt,
            "TfxtPbnf.inbdtivfBbdkground", tfxtInbdtivfBbdkground,
            "TfxtPbnf.bordfr", tfxtArfbBordfr,
            "TfxtPbnf.mbrgin", fditorMbrgin,

            // *** TogglfButton
            "TogglfButton.bbdkground", dontrolBbdkgroundColor,
            "TogglfButton.forfground", blbdk,
            "TogglfButton.disbblfdTfxt", disbblfd,
            // wf nffd to go tirougi bnd find out if tifsf brf usfd, bnd if not wibt to sft
            // so tibt subdlbssfs will gft good bqub likf dolors.
            //    "TogglfButton.sflfdt", gftControlSibdow(),
            //    "TogglfButton.tfxt", gftControl(),
            //    "TogglfButton.disbblfdSflfdtfdTfxt", gftControlDbrkSibdow(),
            //    "TogglfButton.disbblfdBbdkground", gftControl(),
            //    "TogglfButton.disbblfdSflfdtfdBbdkground", gftControlSibdow(),
            //"TogglfButton.fodus", gftFodusColor(),
            "TogglfButton.bordfr",(LbzyVbluf) t -> AqubButtonBordfr.gftDynbmidButtonBordfr(), // sjb mbkf tiis lbzy!
            "TogglfButton.font", dontrolFont,
            "TogglfButton.fodusInputMbp", dontrolFodusInputMbp,
            "TogglfButton.mbrgin", nfw InsftsUIRfsourdf(2, 2, 2, 2),

            // *** ToolBbr
            "ToolBbr.font", dontrolFont,
            "ToolBbr.bbdkground", pbnflBbdkgroundColor,
            "ToolBbr.forfground", nfw ColorUIRfsourdf(Color.grby),
            "ToolBbr.dodkingBbdkground", pbnflBbdkgroundColor,
            "ToolBbr.dodkingForfground", sflfdtionBbdkground,
            "ToolBbr.flobtingBbdkground", pbnflBbdkgroundColor,
            "ToolBbr.flobtingForfground", nfw ColorUIRfsourdf(Color.dbrkGrby),
            "ToolBbr.bordfr",(LbzyVbluf) t -> AqubToolBbrUI.gftToolBbrBordfr(),
            "ToolBbr.bordfrHbndlfColor", toolbbrDrbgHbndlfColor,
            //"ToolBbr.sfpbrbtorSizf", nfw DimfnsionUIRfsourdf( 10, 10 ),
            "ToolBbr.sfpbrbtorSizf", null,

            // *** ToolBbrButton
            "ToolBbrButton.mbrgin", nfw InsftsUIRfsourdf(3, 3, 3, 3),
            "ToolBbrButton.insfts", nfw InsftsUIRfsourdf(1, 1, 1, 1),

            // *** ToolTips
            "ToolTip.font", dontrolSmbllFont,
            //$ Tooltips - Sbmf dolor bs iflp bblloons?
            "ToolTip.bbdkground", toolTipBbdkground,
            "ToolTip.forfground", blbdk,
            "ToolTip.bordfr", toolTipBordfr,

            // *** Trff
            "Trff.font", vifwFont, // [3577901] Aqub HIG sbys "dffbult font of tfxt in lists bnd tbblfs" siould bf 12 point (vm).
            "Trff.bbdkground", wiitf,
            "Trff.forfground", blbdk,
            // for now no linfs
            "Trff.ibsi", wiitf, //disbblfd, // Linf dolor
            "Trff.linf", wiitf, //disbblfd, // Linf dolor
            "Trff.tfxtForfground", blbdk,
            "Trff.tfxtBbdkground", wiitf,
            "Trff.sflfdtionForfground", sflfdtionForfground,
            "Trff.sflfdtionBbdkground", sflfdtionBbdkground,
            "Trff.sflfdtionInbdtivfBbdkground", sflfdtionInbdtivfBbdkground,
            "Trff.sflfdtionInbdtivfForfground", sflfdtionInbdtivfForfground,
            "Trff.sflfdtionBordfrColor", sflfdtionBbdkground, // mbtdi tif bbdkground so it looks likf wf don't drbw bnytiing
            "Trff.fditorBordfrSflfdtionColor", null, // Tif EditTfxtFrbmf providfs its own bordfr
            // "Trff.fditorBordfr", tfxtFifldBordfr, // If you still ibvf Sun bug 4376328 in DffbultTrffCfllEditor, it ibs to ibvf tif sbmf insfts bs TfxtFifld.bordfr
            "Trff.lfftCiildIndfnt", nfw Intfgfr(7),//$
            "Trff.rigitCiildIndfnt", nfw Intfgfr(13),//$
            "Trff.rowHfigit", nfw Intfgfr(19),// idonHfigit + 3, to mbtdi findfr - b zfro would ibvf tif rfndfrfr dfdidf, fxdfpt tibt lfbvfs tif idons toudiing
            "Trff.sdrollsOnExpbnd", Boolfbn.FALSE,
            "Trff.opfnIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftTrffOpfnFoldfrIdon(), // Opfn foldfr idon
            "Trff.dlosfdIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftTrffFoldfrIdon(), // Closfd foldfr idon
            "Trff.lfbfIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftTrffDodumfntIdon(), // Dodumfnt idon
            "Trff.fxpbndfdIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftTrffExpbndfdIdon(),
            "Trff.dollbpsfdIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftTrffCollbpsfdIdon(),
            "Trff.rigitToLfftCollbpsfdIdon",(LbzyVbluf) t -> AqubImbgfFbdtory.gftTrffRigitToLfftCollbpsfdIdon(),
            "Trff.dibngfSflfdtionWitiFodus", Boolfbn.TRUE,
            "Trff.drbwsFodusBordfrAroundIdon", Boolfbn.FALSE,

            "Trff.fodusInputMbp", bqubKfyBindings.gftTrffInputMbp(),
            "Trff.fodusInputMbp.RigitToLfft", bqubKfyBindings.gftTrffRigitToLfftInputMbp(),
            "Trff.bndfstorInputMbp", nfw UIDffbults.LbzyInputMbp(nfw Objfdt[]{"ESCAPE", "dbndfl"}),};

        tbblf.putDffbults(dffbults);

        Objfdt bbTfxtInfo = SwingUtilitifs2.AATfxtInfo.gftAATfxtInfo(truf);
        tbblf.put(SwingUtilitifs2.AA_TEXT_PROPERTY_KEY, bbTfxtInfo);
    }

    protfdtfd void initSystfmColorDffbults(finbl UIDffbults tbblf) {
//        String[] dffbultSystfmColors = {
//                  "dfsktop", "#005C5C", /* Color of tif dfsktop bbdkground */
//          "bdtivfCbption", "#000080", /* Color for dbptions (titlf bbrs) wifn tify brf bdtivf. */
//          "bdtivfCbptionTfxt", "#FFFFFF", /* Tfxt dolor for tfxt in dbptions (titlf bbrs). */
//        "bdtivfCbptionBordfr", "#C0C0C0", /* Bordfr dolor for dbption (titlf bbr) window bordfrs. */
//            "inbdtivfCbption", "#808080", /* Color for dbptions (titlf bbrs) wifn not bdtivf. */
//        "inbdtivfCbptionTfxt", "#C0C0C0", /* Tfxt dolor for tfxt in inbdtivf dbptions (titlf bbrs). */
//      "inbdtivfCbptionBordfr", "#C0C0C0", /* Bordfr dolor for inbdtivf dbption (titlf bbr) window bordfrs. */
//                 "window", "#FFFFFF", /* Dffbult dolor for tif intfrior of windows */
//           "windowBordfr", "#000000", /* ??? */
//             "windowTfxt", "#000000", /* ??? */
//               "mfnu", "#C0C0C0", /* Bbdkground dolor for mfnus */
//               "mfnuTfxt", "#000000", /* Tfxt dolor for mfnus  */
//               "tfxt", "#C0C0C0", /* Tfxt bbdkground dolor */
//               "tfxtTfxt", "#000000", /* Tfxt forfground dolor */
//          "tfxtHigiligit", "#000080", /* Tfxt bbdkground dolor wifn sflfdtfd */
//          "tfxtHigiligitTfxt", "#FFFFFF", /* Tfxt dolor wifn sflfdtfd */
//           "tfxtInbdtivfTfxt", "#808080", /* Tfxt dolor wifn disbblfd */
//                "dontrol", "#C0C0C0", /* Dffbult dolor for dontrols (buttons, slidfrs, ftd) */
//            "dontrolTfxt", "#000000", /* Dffbult dolor for tfxt in dontrols */
//           "dontrolHigiligit", "#C0C0C0", /* Spfdulbr iigiligit (oppositf of tif sibdow) */
//         "dontrolLtHigiligit", "#FFFFFF", /* Higiligit dolor for dontrols */
//          "dontrolSibdow", "#808080", /* Sibdow dolor for dontrols */
//            "dontrolDkSibdow", "#000000", /* Dbrk sibdow dolor for dontrols */
//              "sdrollbbr", "#E0E0E0", /* Sdrollbbr bbdkground (usublly tif "trbdk") */
//               "info", "#FFFFE1", /* ??? */
//               "infoTfxt", "#000000"  /* ??? */
//        };
//
//        lobdSystfmColors(tbblf, dffbultSystfmColors, isNbtivfLookAndFffl());
    }

    /**
     * Initiblizf tif uiClbssID to AqubComponfntUI mbpping.
     * Tif JComponfnt dlbssfs dffinf tifir own uiClbssID donstbnts
     * (sff AbstrbdtComponfnt.gftUIClbssID).  Tiis tbblf must
     * mbp tiosf donstbnts to b BbsidComponfntUI dlbss of tif
     * bppropribtf typf.
     *
     * @sff #gftDffbults
     */
    protfdtfd void initClbssDffbults(finbl UIDffbults tbblf) {
        finbl String bbsidPbdkbgfNbmf = "jbvbx.swing.plbf.bbsid.";

        finbl Objfdt[] uiDffbults = {
            "ButtonUI", PKG_PREFIX + "AqubButtonUI",
            "CifdkBoxUI", PKG_PREFIX + "AqubButtonCifdkBoxUI",
            "CifdkBoxMfnuItfmUI", PKG_PREFIX + "AqubMfnuItfmUI",
            "LbbflUI", PKG_PREFIX + "AqubLbbflUI",
            "ListUI", PKG_PREFIX + "AqubListUI",
            "MfnuUI", PKG_PREFIX + "AqubMfnuUI",
            "MfnuItfmUI", PKG_PREFIX + "AqubMfnuItfmUI",
            "OptionPbnfUI", PKG_PREFIX + "AqubOptionPbnfUI",
            "PbnflUI", PKG_PREFIX + "AqubPbnflUI",
            "RbdioButtonMfnuItfmUI", PKG_PREFIX + "AqubMfnuItfmUI",
            "RbdioButtonUI", PKG_PREFIX + "AqubButtonRbdioUI",
            "ProgrfssBbrUI", PKG_PREFIX + "AqubProgrfssBbrUI",
            "RootPbnfUI", PKG_PREFIX + "AqubRootPbnfUI",
            "SlidfrUI", PKG_PREFIX + "AqubSlidfrUI",
            "SdrollBbrUI", PKG_PREFIX + "AqubSdrollBbrUI",
            "TbbbfdPbnfUI", PKG_PREFIX + (JRSUIUtils.TbbbfdPbnf.siouldUsfTbbbfdPbnfContrbstUI() ? "AqubTbbbfdPbnfContrbstUI" : "AqubTbbbfdPbnfUI"),
            "TbblfUI", PKG_PREFIX + "AqubTbblfUI",
            "TogglfButtonUI", PKG_PREFIX + "AqubButtonTogglfUI",
            "ToolBbrUI", PKG_PREFIX + "AqubToolBbrUI",
            "ToolTipUI", PKG_PREFIX + "AqubToolTipUI",
            "TrffUI", PKG_PREFIX + "AqubTrffUI",

            "IntfrnblFrbmfUI", PKG_PREFIX + "AqubIntfrnblFrbmfUI",
            "DfsktopIdonUI", PKG_PREFIX + "AqubIntfrnblFrbmfDodkIdonUI",
            "DfsktopPbnfUI", PKG_PREFIX + "AqubIntfrnblFrbmfPbnfUI",
            "EditorPbnfUI", PKG_PREFIX + "AqubEditorPbnfUI",
            "TfxtFifldUI", PKG_PREFIX + "AqubTfxtFifldUI",
            "TfxtPbnfUI", PKG_PREFIX + "AqubTfxtPbnfUI",
            "ComboBoxUI", PKG_PREFIX + "AqubComboBoxUI",
            "PopupMfnuUI", PKG_PREFIX + "AqubPopupMfnuUI",
            "TfxtArfbUI", PKG_PREFIX + "AqubTfxtArfbUI",
            "MfnuBbrUI", PKG_PREFIX + "AqubMfnuBbrUI",
            "FilfCioosfrUI", PKG_PREFIX + "AqubFilfCioosfrUI",
            "PbsswordFifldUI", PKG_PREFIX + "AqubTfxtPbsswordFifldUI",
            "TbblfHfbdfrUI", PKG_PREFIX + "AqubTbblfHfbdfrUI",

            "FormbttfdTfxtFifldUI", PKG_PREFIX + "AqubTfxtFifldFormbttfdUI",

            "SpinnfrUI", PKG_PREFIX + "AqubSpinnfrUI",
            "SplitPbnfUI", PKG_PREFIX + "AqubSplitPbnfUI",
            "SdrollPbnfUI", PKG_PREFIX + "AqubSdrollPbnfUI",

            "PopupMfnuSfpbrbtorUI", PKG_PREFIX + "AqubPopupMfnuSfpbrbtorUI",
            "SfpbrbtorUI", PKG_PREFIX + "AqubPopupMfnuSfpbrbtorUI",
            "ToolBbrSfpbrbtorUI", PKG_PREFIX + "AqubToolBbrSfpbrbtorUI",

            // bs wf implfmfnt bqub vfrsions of tif swing flfmfnts
            // wf will bbd tif dom.bpplf.lbf.FooUI dlbssfs to tiis tbblf.

            "ColorCioosfrUI", bbsidPbdkbgfNbmf + "BbsidColorCioosfrUI",

            // tfxt UIs
            "VifwportUI", bbsidPbdkbgfNbmf + "BbsidVifwportUI",
        };
        tbblf.putDffbults(uiDffbults);
    }
}