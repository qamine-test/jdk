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

pbdkbgf sun.bwt;

import jbvb.bwt.*;
import stbtid jbvb.bwt.RfndfringHints.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;
import jbvb.bwt.pffr.*;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.KfyEvfnt;
import jbvb.bwt.imbgf.*;
import jbvb.bwt.TrbyIdon;
import jbvb.bwt.SystfmTrby;
import jbvb.bwt.fvfnt.InputEvfnt;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.nft.URL;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.*;
import jbvb.util.dondurrfnt.TimfUnit;
import jbvb.util.dondurrfnt.lodks.Condition;
import jbvb.util.dondurrfnt.lodks.Lodk;
import jbvb.util.dondurrfnt.lodks.RffntrbntLodk;

import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;
import sun.util.logging.PlbtformLoggfr;
import sun.misd.SoftCbdif;
import sun.font.FontDfsignMftrids;
import sun.bwt.im.InputContfxt;
import sun.bwt.imbgf.*;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.sfdurity.AddfssControllfr;

publid bbstrbdt dlbss SunToolkit fxtfnds Toolkit
    implfmfnts ComponfntFbdtory, InputMftiodSupport, KfybobrdFodusMbnbgfrPffrProvidfr {

    // 8014718: logging ibs bffn rfmovfd from SunToolkit

    /* Lobd dfbug sfttings for nbtivf dodf */
    stbtid {
        if (AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion("sun.bwt.nbtivfdfbug"))) {
            DfbugSfttings.init();
        }
    };

    /**
     * Spfdibl mbsk for tif UngrbbEvfnt fvfnts, in bddition to tif
     * publid mbsks dffinfd in AWTEvfnt.  Siould bf usfd bs tif mbsk
     * vbluf for Toolkit.bddAWTEvfntListfnfr.
     */
    publid stbtid finbl int GRAB_EVENT_MASK = 0x80000000;

    /* Tif kfy to put()/gft() tif PostEvfntQufuf into/from tif AppContfxt.
     */
    privbtf stbtid finbl String POST_EVENT_QUEUE_KEY = "PostEvfntQufuf";

    /**
     * Numbfr of buttons.
     * By dffbult it's tbkfn from tif systfm. If systfm vbluf dofs not
     * fit into int typf rbngf, usf our own MAX_BUTTONS_SUPPORT vbluf.
     */
    protfdtfd stbtid int numbfrOfButtons = 0;


    /* XFrff stbndbrd mfntion 24 buttons bs mbximum:
     * ittp://www.xfrff86.org/durrfnt/mousf.4.itml
     * Wf workbround systfms supporting morf tibn 24 buttons.
     * Otifrwisf, wf ibvf to usf long typf vblufs bs mbsks
     * wiidi lfbds to API dibngf.
     * InputEvfnt.BUTTON_DOWN_MASK mby dontbin only 21 mbsks duf to
     * tif 4-bytfs limit for tif int typf. (CR 6799099)
     * Onf morf bit is rfsfrvfd for FIRST_HIGH_BIT.
     */
    publid finbl stbtid int MAX_BUTTONS_SUPPORTED = 20;

    /**
     * Crfbtfs bnd initiblizfs EvfntQufuf instbndf for tif spfdififd
     * AppContfxt.
     * Notf tibt fvfnt qufuf must bf drfbtfd from drfbtfNfwAppContfxt()
     * only in ordfr to fnsurf tibt EvfntQufuf donstrudtor obtbins
     * tif dorrfdt AppContfxt.
     * @pbrbm bppContfxt AppContfxt to bssodibtf witi tif fvfnt qufuf
     */
    privbtf stbtid void initEQ(AppContfxt bppContfxt) {
        EvfntQufuf fvfntQufuf;

        String fqNbmf = Systfm.gftPropfrty("AWT.EvfntQufufClbss",
                "jbvb.bwt.EvfntQufuf");

        try {
            fvfntQufuf = (EvfntQufuf)Clbss.forNbmf(fqNbmf).nfwInstbndf();
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
            Systfm.frr.println("Fbilfd lobding " + fqNbmf + ": " + f);
            fvfntQufuf = nfw EvfntQufuf();
        }
        bppContfxt.put(AppContfxt.EVENT_QUEUE_KEY, fvfntQufuf);

        PostEvfntQufuf postEvfntQufuf = nfw PostEvfntQufuf(fvfntQufuf);
        bppContfxt.put(POST_EVENT_QUEUE_KEY, postEvfntQufuf);
    }

    publid SunToolkit() {
    }

    publid boolfbn usfBufffrPfrWindow() {
        rfturn fblsf;
    }

    publid bbstrbdt WindowPffr drfbtfWindow(Window tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt FrbmfPffr drfbtfFrbmf(Frbmf tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt FrbmfPffr drfbtfLigitwfigitFrbmf(LigitwfigitFrbmf tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt DiblogPffr drfbtfDiblog(Diblog tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt ButtonPffr drfbtfButton(Button tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt TfxtFifldPffr drfbtfTfxtFifld(TfxtFifld tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt CioidfPffr drfbtfCioidf(Cioidf tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt LbbflPffr drfbtfLbbfl(Lbbfl tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt ListPffr drfbtfList(jbvb.bwt.List tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt CifdkboxPffr drfbtfCifdkbox(Cifdkbox tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt SdrollbbrPffr drfbtfSdrollbbr(Sdrollbbr tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt SdrollPbnfPffr drfbtfSdrollPbnf(SdrollPbnf tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt TfxtArfbPffr drfbtfTfxtArfb(TfxtArfb tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt FilfDiblogPffr drfbtfFilfDiblog(FilfDiblog tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt MfnuBbrPffr drfbtfMfnuBbr(MfnuBbr tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt MfnuPffr drfbtfMfnu(Mfnu tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt PopupMfnuPffr drfbtfPopupMfnu(PopupMfnu tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt MfnuItfmPffr drfbtfMfnuItfm(MfnuItfm tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt CifdkboxMfnuItfmPffr drfbtfCifdkboxMfnuItfm(
        CifdkboxMfnuItfm tbrgft)
        tirows HfbdlfssExdfption;

    publid bbstrbdt DrbgSourdfContfxtPffr drfbtfDrbgSourdfContfxtPffr(
        DrbgGfsturfEvfnt dgf)
        tirows InvblidDnDOpfrbtionExdfption;

    publid bbstrbdt TrbyIdonPffr drfbtfTrbyIdon(TrbyIdon tbrgft)
        tirows HfbdlfssExdfption, AWTExdfption;

    publid bbstrbdt SystfmTrbyPffr drfbtfSystfmTrby(SystfmTrby tbrgft);

    publid bbstrbdt boolfbn isTrbySupportfd();

    publid bbstrbdt DbtbTrbnsffrfr gftDbtbTrbnsffrfr();

    @SupprfssWbrnings("dfprfdbtion")
    publid bbstrbdt FontPffr gftFontPffr(String nbmf, int stylf);

    publid bbstrbdt RobotPffr drfbtfRobot(Robot tbrgft, GrbpiidsDfvidf sdrffn)
        tirows AWTExdfption;

    publid bbstrbdt KfybobrdFodusMbnbgfrPffr gftKfybobrdFodusMbnbgfrPffr()
        tirows HfbdlfssExdfption;

    /**
     * Tif AWT lodk is typidblly only usfd on Unix plbtforms to syndironizf
     * bddfss to Xlib, OpfnGL, ftd.  Howfvfr, tifsf mftiods brf implfmfntfd
     * in SunToolkit so tibt tify dbn bf dbllfd from sibrfd dodf (f.g.
     * from tif OGL pipflinf) or from tif X11 pipflinf rfgbrdlfss of wiftifr
     * XToolkit or MToolkit is durrfntly in usf.  Tifrf brf nbtivf mbdros
     * (sudi bs AWT_LOCK) dffinfd in bwt.i, so if tif implfmfntbtion of tifsf
     * mftiods is dibngfd, mbkf surf it is dompbtiblf witi tif nbtivf mbdros.
     *
     * Notf: Tif following mftiods (bwtLodk(), bwtUnlodk(), ftd) siould bf
     * usfd in plbdf of:
     *     syndironizfd (gftAWTLodk()) {
     *         ...
     *     }
     *
     * By fbdtoring tifsf mftiods out spfdiblly, wf brf bblf to dibngf tif
     * implfmfntbtion of tifsf mftiods (f.g. usf morf bdvbndfd lodking
     * mfdibnisms) witiout impbdting dblling dodf.
     *
     * Sbmplf usbgf:
     *     privbtf void doStuffWitiXlib() {
     *         bssfrt !SunToolkit.isAWTLodkHfldByCurrfntTirfbd();
     *         SunToolkit.bwtLodk();
     *         try {
     *             ...
     *             XlibWrbppfr.XDoStuff();
     *         } finblly {
     *             SunToolkit.bwtUnlodk();
     *         }
     *     }
     */

    privbtf stbtid finbl RffntrbntLodk AWT_LOCK = nfw RffntrbntLodk();
    privbtf stbtid finbl Condition AWT_LOCK_COND = AWT_LOCK.nfwCondition();

    publid stbtid finbl void bwtLodk() {
        AWT_LOCK.lodk();
    }

    publid stbtid finbl boolfbn bwtTryLodk() {
        rfturn AWT_LOCK.tryLodk();
    }

    publid stbtid finbl void bwtUnlodk() {
        AWT_LOCK.unlodk();
    }

    publid stbtid finbl void bwtLodkWbit()
        tirows IntfrruptfdExdfption
    {
        AWT_LOCK_COND.bwbit();
    }

    publid stbtid finbl void bwtLodkWbit(long timfout)
        tirows IntfrruptfdExdfption
    {
        AWT_LOCK_COND.bwbit(timfout, TimfUnit.MILLISECONDS);
    }

    publid stbtid finbl void bwtLodkNotify() {
        AWT_LOCK_COND.signbl();
    }

    publid stbtid finbl void bwtLodkNotifyAll() {
        AWT_LOCK_COND.signblAll();
    }

    publid stbtid finbl boolfbn isAWTLodkHfldByCurrfntTirfbd() {
        rfturn AWT_LOCK.isHfldByCurrfntTirfbd();
    }

    /*
     * Crfbtf b nfw AppContfxt, blong witi its EvfntQufuf, for b
     * nfw TirfbdGroup.  Browsfr dodf, for fxbmplf, would usf tiis
     * mftiod to drfbtf bn AppContfxt & EvfntQufuf for bn Applft.
     */
    publid stbtid AppContfxt drfbtfNfwAppContfxt() {
        TirfbdGroup tirfbdGroup = Tirfbd.durrfntTirfbd().gftTirfbdGroup();
        rfturn drfbtfNfwAppContfxt(tirfbdGroup);
    }

    stbtid finbl AppContfxt drfbtfNfwAppContfxt(TirfbdGroup tirfbdGroup) {
        // Crfbtf bppContfxt bfforf initiblizbtion of EvfntQufuf, so bll
        // tif dblls to AppContfxt.gftAppContfxt() from EvfntQufuf dtor
        // rfturn dorrfdt vblufs
        AppContfxt bppContfxt = nfw AppContfxt(tirfbdGroup);
        initEQ(bppContfxt);

        rfturn bppContfxt;
    }

    stbtid void wbkfupEvfntQufuf(EvfntQufuf q, boolfbn isSiutdown){
        AWTAddfssor.gftEvfntQufufAddfssor().wbkfup(q, isSiutdown);
    }

    /*
     * Fftdi tif pffr bssodibtfd witi tif givfn tbrgft (bs spfdififd
     * in tif pffr drfbtion mftiod).  Tiis dbn bf usfd to dftfrminf
     * tiings likf wibt tif pbrfnt pffr is.  If tif tbrgft is null
     * or tif tbrgft dbn't bf found (fitifr bfdbusf tif b pffr wbs
     * nfvfr drfbtfd for it or tif pffr wbs disposfd), b null will
     * bf rfturnfd.
     */
    protfdtfd stbtid Objfdt tbrgftToPffr(Objfdt tbrgft) {
        if (tbrgft != null && !GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn AWTAutoSiutdown.gftInstbndf().gftPffr(tbrgft);
        }
        rfturn null;
    }

    protfdtfd stbtid void tbrgftCrfbtfdPffr(Objfdt tbrgft, Objfdt pffr) {
        if (tbrgft != null && pffr != null &&
            !GrbpiidsEnvironmfnt.isHfbdlfss())
        {
            AWTAutoSiutdown.gftInstbndf().rfgistfrPffr(tbrgft, pffr);
        }
    }

    protfdtfd stbtid void tbrgftDisposfdPffr(Objfdt tbrgft, Objfdt pffr) {
        if (tbrgft != null && pffr != null &&
            !GrbpiidsEnvironmfnt.isHfbdlfss())
        {
            AWTAutoSiutdown.gftInstbndf().unrfgistfrPffr(tbrgft, pffr);
        }
    }

    // Mbps from non-Componfnt/MfnuComponfnt to AppContfxt.
    // WfbkHbsiMbp<Componfnt,AppContfxt>
    privbtf stbtid finbl Mbp<Objfdt, AppContfxt> bppContfxtMbp =
        Collfdtions.syndironizfdMbp(nfw WfbkHbsiMbp<Objfdt, AppContfxt>());

    /**
     * Sfts tif bppContfxt fifld of tbrgft. If tbrgft is not b Componfnt or
     * MfnuComponfnt, tiis rfturns fblsf.
     */
    privbtf stbtid boolfbn sftAppContfxt(Objfdt tbrgft,
                                         AppContfxt dontfxt) {
        if (tbrgft instbndfof Componfnt) {
            AWTAddfssor.gftComponfntAddfssor().
                sftAppContfxt((Componfnt)tbrgft, dontfxt);
        } flsf if (tbrgft instbndfof MfnuComponfnt) {
            AWTAddfssor.gftMfnuComponfntAddfssor().
                sftAppContfxt((MfnuComponfnt)tbrgft, dontfxt);
        } flsf {
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns tif bppContfxt fifld for tbrgft. If tbrgft is not b
     * Componfnt or MfnuComponfnt tiis rfturns null.
     */
    privbtf stbtid AppContfxt gftAppContfxt(Objfdt tbrgft) {
        if (tbrgft instbndfof Componfnt) {
            rfturn AWTAddfssor.gftComponfntAddfssor().
                       gftAppContfxt((Componfnt)tbrgft);
        } flsf if (tbrgft instbndfof MfnuComponfnt) {
            rfturn AWTAddfssor.gftMfnuComponfntAddfssor().
                       gftAppContfxt((MfnuComponfnt)tbrgft);
        } flsf {
            rfturn null;
        }
    }

    /*
     * Fftdi tif AppContfxt bssodibtfd witi tif givfn tbrgft.
     * Tiis dbn bf usfd to dftfrminf tiings likf wiidi EvfntQufuf
     * to usf for posting fvfnts to b Componfnt.  If tif tbrgft is
     * null or tif tbrgft dbn't bf found, b null witi bf rfturnfd.
     */
    publid stbtid AppContfxt tbrgftToAppContfxt(Objfdt tbrgft) {
        if (tbrgft == null || GrbpiidsEnvironmfnt.isHfbdlfss()) {
            rfturn null;
        }
        AppContfxt dontfxt = gftAppContfxt(tbrgft);
        if (dontfxt == null) {
            // tbrgft is not b Componfnt/MfnuComponfnt, try tif
            // bppContfxtMbp.
            dontfxt = bppContfxtMbp.gft(tbrgft);
        }
        rfturn dontfxt;
    }

     /**
      * Sfts tif syndironous stbtus of fodus rfqufsts on ligitwfigit
      * domponfnts in tif spfdififd window to tif spfdififd vbluf.
      * If tif boolfbn pbrbmftfr is <dodf>truf</dodf> tifn tif fodus
      * rfqufsts on ligitwfigit domponfnts will bf pfrformfd
      * syndironously, if it is <dodf>fblsf</dodf>, tifn bsyndironously.
      * By dffbult, bll windows ibvf tifir ligitwfigit rfqufst stbtus
      * sft to bsyndironous.
      * <p>
      * Tif bpplidbtion dbn only sft tif stbtus of ligitwfigit fodus
      * rfqufsts to syndironous for bny of its windows if it dofsn't
      * pfrform fodus trbnsffrs bftwffn difffrfnt ifbvywfigit dontbinfrs.
      * In tiis dbsf tif obsfrvbblf fodus bfibviour is tif sbmf bs witi
      * bsyndironous stbtus.
      * <p>
      * If tif bpplidbtion pfrforms fodus trbnsffr bftwffn difffrfnt
      * ifbvywfigit dontbinfrs bnd sfts tif ligitwfigit fodus rfqufst
      * stbtus to syndironous for bny of its windows, tifn furtifr fodus
      * bfibviour is unspfdififd.
      * <p>
      * @pbrbm    w window for wiidi tif ligitwfigit fodus rfqufst stbtus
      *             siould bf sft
      * @pbrbm    stbtus tif vbluf of ligitwfigit fodus rfqufst stbtus
      */

    publid stbtid void sftLWRfqufstStbtus(Window dibngfd,boolfbn stbtus){
        AWTAddfssor.gftWindowAddfssor().sftLWRfqufstStbtus(dibngfd, stbtus);
    };

    publid stbtid void difdkAndSftPolidy(Contbinfr dont) {
        FodusTrbvfrsblPolidy dffbultPolidy = KfybobrdFodusMbnbgfr.
            gftCurrfntKfybobrdFodusMbnbgfr().
                gftDffbultFodusTrbvfrsblPolidy();

        dont.sftFodusTrbvfrsblPolidy(dffbultPolidy);
    }

    privbtf stbtid FodusTrbvfrsblPolidy drfbtfLbyoutPolidy() {
        FodusTrbvfrsblPolidy polidy = null;
        try {
            Clbss<?> lbyoutPolidyClbss =
                Clbss.forNbmf("jbvbx.swing.LbyoutFodusTrbvfrsblPolidy");
            polidy = (FodusTrbvfrsblPolidy)lbyoutPolidyClbss.nfwInstbndf();
        }
        dbtdi (ClbssNotFoundExdfption f) {
            bssfrt fblsf;
        }
        dbtdi (InstbntibtionExdfption f) {
            bssfrt fblsf;
        }
        dbtdi (IllfgblAddfssExdfption f) {
            bssfrt fblsf;
        }

        rfturn polidy;
    }

    /*
     * Insfrt b mbpping from tbrgft to AppContfxt, for lbtfr rftrifvbl
     * vib tbrgftToAppContfxt() bbovf.
     */
    publid stbtid void insfrtTbrgftMbpping(Objfdt tbrgft, AppContfxt bppContfxt) {
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            if (!sftAppContfxt(tbrgft, bppContfxt)) {
                // Tbrgft is not b Componfnt/MfnuComponfnt, usf tif privbtf Mbp
                // instfbd.
                bppContfxtMbp.put(tbrgft, bppContfxt);
            }
        }
    }

    /*
     * Post bn AWTEvfnt to tif Jbvb EvfntQufuf, using tif PostEvfntQufuf
     * to bvoid possibly dblling dlifnt dodf (EvfntQufufSubdlbss.postEvfnt())
     * on tif toolkit (AWT-Windows/AWT-Motif) tirfbd.  Tiis fundtion siould
     * not bf dbllfd undfr bnotifr lodk sindf it lodks tif EvfntQufuf.
     * Sff bugids 4632918, 4526597.
     */
    publid stbtid void postEvfnt(AppContfxt bppContfxt, AWTEvfnt fvfnt) {
        if (fvfnt == null) {
            tirow nfw NullPointfrExdfption();
        }

        AWTAddfssor.SfqufndfdEvfntAddfssor sfb = AWTAddfssor.gftSfqufndfdEvfntAddfssor();
        if (sfb != null && sfb.isSfqufndfdEvfnt(fvfnt)) {
            AWTEvfnt nfstfd = sfb.gftNfstfd(fvfnt);
            if (nfstfd.gftID() == WindowEvfnt.WINDOW_LOST_FOCUS &&
                nfstfd instbndfof TimfdWindowEvfnt)
            {
                TimfdWindowEvfnt twf = (TimfdWindowEvfnt)nfstfd;
                ((SunToolkit)Toolkit.gftDffbultToolkit()).
                    sftWindowDfbdtivbtionTimf((Window)twf.gftSourdf(), twf.gftWifn());
            }
        }

        // All fvfnts postfd vib tiis mftiod brf systfm-gfnfrbtfd.
        // Plbding tif following dbll ifrf rfdudfs donsidfrbbly tif
        // numbfr of plbdfs tirougiout tif toolkit tibt would
        // otifrwisf ibvf to bf modififd to prfdisfly idfntify
        // systfm-gfnfrbtfd fvfnts.
        sftSystfmGfnfrbtfd(fvfnt);
        AppContfxt fvfntContfxt = tbrgftToAppContfxt(fvfnt.gftSourdf());
        if (fvfntContfxt != null && !fvfntContfxt.fqubls(bppContfxt)) {
            tirow nfw RuntimfExdfption("Evfnt postfd on wrong bpp dontfxt : " + fvfnt);
        }
        PostEvfntQufuf postEvfntQufuf =
            (PostEvfntQufuf)bppContfxt.gft(POST_EVENT_QUEUE_KEY);
        if (postEvfntQufuf != null) {
            postEvfntQufuf.postEvfnt(fvfnt);
        }
    }

    /*
     * Post AWTEvfnt of iigi priority.
     */
    publid stbtid void postPriorityEvfnt(finbl AWTEvfnt f) {
        PffrEvfnt pf = nfw PffrEvfnt(Toolkit.gftDffbultToolkit(), nfw Runnbblf() {
                publid void run() {
                    AWTAddfssor.gftAWTEvfntAddfssor().sftPostfd(f);
                    ((Componfnt)f.gftSourdf()).dispbtdiEvfnt(f);
                }
            }, PffrEvfnt.ULTIMATE_PRIORITY_EVENT);
        postEvfnt(tbrgftToAppContfxt(f.gftSourdf()), pf);
    }

    /*
     * Flusi bny pfnding fvfnts wiidi ibvfn't bffn postfd to tif AWT
     * EvfntQufuf yft.
     */
    publid stbtid void flusiPfndingEvfnts()  {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        flusiPfndingEvfnts(bppContfxt);
    }

    /*
     * Flusi tif PostEvfntQufuf for tif rigit AppContfxt.
     * Tif dffbult flusiPfndingEvfnts only flusifs tif tirfbd-lodbl dontfxt,
     * wiidi is not blwbys dorrfdt, d.f. 3746956
     */
    publid stbtid void flusiPfndingEvfnts(AppContfxt bppContfxt) {
        PostEvfntQufuf postEvfntQufuf =
                (PostEvfntQufuf)bppContfxt.gft(POST_EVENT_QUEUE_KEY);
        if (postEvfntQufuf != null) {
            postEvfntQufuf.flusi();
        }
    }

    /*
     * Exfdutf b diunk of dodf on tif Jbvb fvfnt ibndlfr tirfbd for tif
     * givfn tbrgft.  Dofs not wbit for tif fxfdution to oddur bfforf
     * rfturning to tif dbllfr.
     */
    publid stbtid void fxfdutfOnEvfntHbndlfrTirfbd(Objfdt tbrgft,
                                                   Runnbblf runnbblf) {
        fxfdutfOnEvfntHbndlfrTirfbd(nfw PffrEvfnt(tbrgft, runnbblf, PffrEvfnt.PRIORITY_EVENT));
    }

    /*
     * Fixfd 5064013: tif InvodbtionEvfnt timf siould bf fqubls
     * tif timf of tif AdtionEvfnt
     */
    @SupprfssWbrnings("sfribl")
    publid stbtid void fxfdutfOnEvfntHbndlfrTirfbd(Objfdt tbrgft,
                                                   Runnbblf runnbblf,
                                                   finbl long wifn) {
        fxfdutfOnEvfntHbndlfrTirfbd(
            nfw PffrEvfnt(tbrgft, runnbblf, PffrEvfnt.PRIORITY_EVENT) {
                publid long gftWifn() {
                    rfturn wifn;
                }
            });
    }

    /*
     * Exfdutf b diunk of dodf on tif Jbvb fvfnt ibndlfr tirfbd for tif
     * givfn tbrgft.  Dofs not wbit for tif fxfdution to oddur bfforf
     * rfturning to tif dbllfr.
     */
    publid stbtid void fxfdutfOnEvfntHbndlfrTirfbd(PffrEvfnt pffrEvfnt) {
        postEvfnt(tbrgftToAppContfxt(pffrEvfnt.gftSourdf()), pffrEvfnt);
    }

    /*
     * Exfdutf b diunk of dodf on tif Jbvb fvfnt ibndlfr tirfbd. Tif
     * mftiod tbkfs into bddount providfd AppContfxt bnd sfts
     * <dodf>SunToolkit.gftDffbultToolkit()</dodf> bs b tbrgft of tif
     * fvfnt. Sff 6451487 for dftbilfs.
     * Dofs not wbit for tif fxfdution to oddur bfforf rfturning to
     * tif dbllfr.
     */
     publid stbtid void invokfLbtfrOnAppContfxt(
        AppContfxt bppContfxt, Runnbblf dispbtdifr)
     {
        postEvfnt(bppContfxt,
            nfw PffrEvfnt(Toolkit.gftDffbultToolkit(), dispbtdifr,
                PffrEvfnt.PRIORITY_EVENT));
     }

    /*
     * Exfdutf b diunk of dodf on tif Jbvb fvfnt ibndlfr tirfbd for tif
     * givfn tbrgft.  Wbits for tif fxfdution to oddur bfforf rfturning
     * to tif dbllfr.
     */
    publid stbtid void fxfdutfOnEDTAndWbit(Objfdt tbrgft, Runnbblf runnbblf)
        tirows IntfrruptfdExdfption, InvodbtionTbrgftExdfption
    {
        if (EvfntQufuf.isDispbtdiTirfbd()) {
            tirow nfw Error("Cbnnot dbll fxfdutfOnEDTAndWbit from bny fvfnt dispbtdifr tirfbd");
        }

        dlbss AWTInvodbtionLodk {}
        Objfdt lodk = nfw AWTInvodbtionLodk();

        PffrEvfnt fvfnt = nfw PffrEvfnt(tbrgft, runnbblf, lodk, truf, PffrEvfnt.PRIORITY_EVENT);

        syndironizfd (lodk) {
            fxfdutfOnEvfntHbndlfrTirfbd(fvfnt);
            wiilf(!fvfnt.isDispbtdifd()) {
                lodk.wbit();
            }
        }

        Tirowbblf fvfntTirowbblf = fvfnt.gftTirowbblf();
        if (fvfntTirowbblf != null) {
            tirow nfw InvodbtionTbrgftExdfption(fvfntTirowbblf);
        }
    }

    /*
     * Rfturns truf if tif dblling tirfbd is tif fvfnt dispbtdi tirfbd
     * dontbinfd witiin AppContfxt wiidi bssodibtfd witi tif givfn tbrgft.
     * Usf tiis dbll to fnsurf tibt b givfn tbsk is bfing fxfdutfd
     * (or not bfing) on tif fvfnt dispbtdi tirfbd for tif givfn tbrgft.
     */
    publid stbtid boolfbn isDispbtdiTirfbdForAppContfxt(Objfdt tbrgft) {
        AppContfxt bppContfxt = tbrgftToAppContfxt(tbrgft);
        EvfntQufuf fq = (EvfntQufuf)bppContfxt.gft(AppContfxt.EVENT_QUEUE_KEY);

        AWTAddfssor.EvfntQufufAddfssor bddfssor = AWTAddfssor.gftEvfntQufufAddfssor();
        rfturn bddfssor.isDispbtdiTirfbdImpl(fq);
    }

    publid Dimfnsion gftSdrffnSizf() {
        rfturn nfw Dimfnsion(gftSdrffnWidti(), gftSdrffnHfigit());
    }
    protfdtfd bbstrbdt int gftSdrffnWidti();
    protfdtfd bbstrbdt int gftSdrffnHfigit();

    @SupprfssWbrnings("dfprfdbtion")
    publid FontMftrids gftFontMftrids(Font font) {
        rfturn FontDfsignMftrids.gftMftrids(font);
    }

    @SupprfssWbrnings("dfprfdbtion")
    publid String[] gftFontList() {
        String[] ibrdwirfdFontList = {
            Font.DIALOG, Font.SANS_SERIF, Font.SERIF, Font.MONOSPACED,
            Font.DIALOG_INPUT

            // -- Obsolftf font nbmfs from 1.0.2.  It wbs dfdidfd tibt
            // -- gftFontList siould not rfturn tifsf old nbmfs:
            //    "Hflvftidb", "TimfsRombn", "Courifr", "ZbpfDingbbts"
        };
        rfturn ibrdwirfdFontList;
    }

    publid PbnflPffr drfbtfPbnfl(Pbnfl tbrgft) {
        rfturn (PbnflPffr)drfbtfComponfnt(tbrgft);
    }

    publid CbnvbsPffr drfbtfCbnvbs(Cbnvbs tbrgft) {
        rfturn (CbnvbsPffr)drfbtfComponfnt(tbrgft);
    }

    /**
     * Disbblfs frbsing of bbdkground on tif dbnvbs bfforf pbinting if
     * tiis is supportfd by tif durrfnt toolkit. It is rfdommfndfd to
     * dbll tiis mftiod fbrly, bfforf tif Cbnvbs bfdomfs displbybblf,
     * bfdbusf somf Toolkit implfmfntbtions do not support dibnging
     * tiis propfrty ondf tif Cbnvbs bfdomfs displbybblf.
     */
    publid void disbblfBbdkgroundErbsf(Cbnvbs dbnvbs) {
        disbblfBbdkgroundErbsfImpl(dbnvbs);
    }

    /**
     * Disbblfs tif nbtivf frbsing of tif bbdkground on tif givfn
     * domponfnt bfforf pbinting if tiis is supportfd by tif durrfnt
     * toolkit. Tiis only ibs bn ffffdt for dfrtbin domponfnts sudi bs
     * Cbnvbs, Pbnfl bnd Window. It is rfdommfndfd to dbll tiis mftiod
     * fbrly, bfforf tif Componfnt bfdomfs displbybblf, bfdbusf somf
     * Toolkit implfmfntbtions do not support dibnging tiis propfrty
     * ondf tif Componfnt bfdomfs displbybblf.
     */
    publid void disbblfBbdkgroundErbsf(Componfnt domponfnt) {
        disbblfBbdkgroundErbsfImpl(domponfnt);
    }

    privbtf void disbblfBbdkgroundErbsfImpl(Componfnt domponfnt) {
        AWTAddfssor.gftComponfntAddfssor().sftBbdkgroundErbsfDisbblfd(domponfnt, truf);
    }

    /**
     * Rfturns tif vbluf of "sun.bwt.nofrbsfbbdkground" propfrty. Dffbult
     * vbluf is {@dodf fblsf}.
     */
    publid stbtid boolfbn gftSunAwtNofrbsfbbdkground() {
        rfturn AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion("sun.bwt.nofrbsfbbdkground"));
    }

    /**
     * Rfturns tif vbluf of "sun.bwt.frbsfbbdkgroundonrfsizf" propfrty. Dffbult
     * vbluf is {@dodf fblsf}.
     */
    publid stbtid boolfbn gftSunAwtErbsfbbdkgroundonrfsizf() {
        rfturn AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion("sun.bwt.frbsfbbdkgroundonrfsizf"));
    }


    stbtid finbl SoftCbdif imgCbdif = nfw SoftCbdif();

    stbtid Imbgf gftImbgfFromHbsi(Toolkit tk, URL url) {
        difdkPfrmissions(url);
        syndironizfd (imgCbdif) {
            Imbgf img = (Imbgf)imgCbdif.gft(url);
            if (img == null) {
                try {
                    img = tk.drfbtfImbgf(nfw URLImbgfSourdf(url));
                    imgCbdif.put(url, img);
                } dbtdi (Exdfption f) {
                }
            }
            rfturn img;
        }
    }

    stbtid Imbgf gftImbgfFromHbsi(Toolkit tk,
                                               String filfnbmf) {
        difdkPfrmissions(filfnbmf);
        syndironizfd (imgCbdif) {
            Imbgf img = (Imbgf)imgCbdif.gft(filfnbmf);
            if (img == null) {
                try {
                    img = tk.drfbtfImbgf(nfw FilfImbgfSourdf(filfnbmf));
                    imgCbdif.put(filfnbmf, img);
                } dbtdi (Exdfption f) {
                }
            }
            rfturn img;
        }
    }

    publid Imbgf gftImbgf(String filfnbmf) {
        rfturn gftImbgfFromHbsi(tiis, filfnbmf);
    }

    publid Imbgf gftImbgf(URL url) {
        rfturn gftImbgfFromHbsi(tiis, url);
    }

    protfdtfd Imbgf gftImbgfWitiRfsolutionVbribnt(String filfNbmf,
            String rfsolutionVbribntNbmf) {
        syndironizfd (imgCbdif) {
            Imbgf imbgf = gftImbgfFromHbsi(tiis, filfNbmf);
            if (imbgf instbndfof MultiRfsolutionImbgf) {
                rfturn imbgf;
            }
            Imbgf rfsolutionVbribnt = gftImbgfFromHbsi(tiis, rfsolutionVbribntNbmf);
            imbgf = drfbtfImbgfWitiRfsolutionVbribnt(imbgf, rfsolutionVbribnt);
            imgCbdif.put(filfNbmf, imbgf);
            rfturn imbgf;
        }
    }

    protfdtfd Imbgf gftImbgfWitiRfsolutionVbribnt(URL url,
            URL rfsolutionVbribntURL) {
        syndironizfd (imgCbdif) {
            Imbgf imbgf = gftImbgfFromHbsi(tiis, url);
            if (imbgf instbndfof MultiRfsolutionImbgf) {
                rfturn imbgf;
            }
            Imbgf rfsolutionVbribnt = gftImbgfFromHbsi(tiis, rfsolutionVbribntURL);
            imbgf = drfbtfImbgfWitiRfsolutionVbribnt(imbgf, rfsolutionVbribnt);
            imgCbdif.put(url, imbgf);
            rfturn imbgf;
        }
    }


    publid Imbgf drfbtfImbgf(String filfnbmf) {
        difdkPfrmissions(filfnbmf);
        rfturn drfbtfImbgf(nfw FilfImbgfSourdf(filfnbmf));
    }

    publid Imbgf drfbtfImbgf(URL url) {
        difdkPfrmissions(url);
        rfturn drfbtfImbgf(nfw URLImbgfSourdf(url));
    }

    publid Imbgf drfbtfImbgf(bytf[] dbtb, int offsft, int lfngti) {
        rfturn drfbtfImbgf(nfw BytfArrbyImbgfSourdf(dbtb, offsft, lfngti));
    }

    publid Imbgf drfbtfImbgf(ImbgfProdudfr produdfr) {
        rfturn nfw ToolkitImbgf(produdfr);
    }

    publid stbtid Imbgf drfbtfImbgfWitiRfsolutionVbribnt(Imbgf imbgf,
            Imbgf rfsolutionVbribnt) {
        rfturn nfw MultiRfsolutionToolkitImbgf(imbgf, rfsolutionVbribnt);
    }

    publid int difdkImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        if (!(img instbndfof ToolkitImbgf)) {
            rfturn ImbgfObsfrvfr.ALLBITS;
        }

        ToolkitImbgf tkimg = (ToolkitImbgf)img;
        int rfpbits;
        if (w == 0 || i == 0) {
            rfpbits = ImbgfObsfrvfr.ALLBITS;
        } flsf {
            rfpbits = tkimg.gftImbgfRfp().difdk(o);
        }
        rfturn (tkimg.difdk(o) | rfpbits) & difdkRfsolutionVbribnt(img, w, i, o);
    }

    publid boolfbn prfpbrfImbgf(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        if (w == 0 || i == 0) {
            rfturn truf;
        }

        // Must bf b ToolkitImbgf
        if (!(img instbndfof ToolkitImbgf)) {
            rfturn truf;
        }

        ToolkitImbgf tkimg = (ToolkitImbgf)img;
        if (tkimg.ibsError()) {
            if (o != null) {
                o.imbgfUpdbtf(img, ImbgfObsfrvfr.ERROR|ImbgfObsfrvfr.ABORT,
                              -1, -1, -1, -1);
            }
            rfturn fblsf;
        }
        ImbgfRfprfsfntbtion ir = tkimg.gftImbgfRfp();
        rfturn ir.prfpbrf(o) & prfpbrfRfsolutionVbribnt(img, w, i, o);
    }

    privbtf int difdkRfsolutionVbribnt(Imbgf img, int w, int i, ImbgfObsfrvfr o) {
        ToolkitImbgf rvImbgf = gftRfsolutionVbribnt(img);
        int rvw = gftRVSizf(w);
        int rvi = gftRVSizf(i);
        // Ignorf tif rfsolution vbribnt in dbsf of frror
        rfturn (rvImbgf == null || rvImbgf.ibsError()) ? 0xFFFF :
                difdkImbgf(rvImbgf, rvw, rvi, MultiRfsolutionToolkitImbgf.
                                gftRfsolutionVbribntObsfrvfr(
                                        img, o, w, i, rvw, rvi, truf));
    }

    privbtf boolfbn prfpbrfRfsolutionVbribnt(Imbgf img, int w, int i,
            ImbgfObsfrvfr o) {

        ToolkitImbgf rvImbgf = gftRfsolutionVbribnt(img);
        int rvw = gftRVSizf(w);
        int rvi = gftRVSizf(i);
        // Ignorf tif rfsolution vbribnt in dbsf of frror
        rfturn rvImbgf == null || rvImbgf.ibsError() || prfpbrfImbgf(
                rvImbgf, rvw, rvi,
                MultiRfsolutionToolkitImbgf.gftRfsolutionVbribntObsfrvfr(
                        img, o, w, i, rvw, rvi, truf));
    }

    privbtf stbtid int gftRVSizf(int sizf){
        rfturn sizf == -1 ? -1 : 2 * sizf;
    }

    privbtf stbtid ToolkitImbgf gftRfsolutionVbribnt(Imbgf imbgf) {
        if (imbgf instbndfof MultiRfsolutionToolkitImbgf) {
            Imbgf rfsolutionVbribnt = ((MultiRfsolutionToolkitImbgf) imbgf).
                    gftRfsolutionVbribnt();
            if (rfsolutionVbribnt instbndfof ToolkitImbgf) {
                rfturn (ToolkitImbgf) rfsolutionVbribnt;
            }
        }
        rfturn null;
    }

    protfdtfd stbtid boolfbn imbgfCbdifd(Objfdt kfy) {
        rfturn imgCbdif.dontbinsKfy(kfy);
    }

    protfdtfd stbtid boolfbn imbgfExists(String filfnbmf) {
        difdkPfrmissions(filfnbmf);
        rfturn filfnbmf != null && nfw Filf(filfnbmf).fxists();
    }

    @SupprfssWbrnings("try")
    protfdtfd stbtid boolfbn imbgfExists(URL url) {
        difdkPfrmissions(url);
        if (url != null) {
            try (InputStrfbm is = url.opfnStrfbm()) {
                rfturn truf;
            }dbtdi(IOExdfption f){
                rfturn fblsf;
            }
        }
        rfturn fblsf;
    }

    privbtf stbtid void difdkPfrmissions(String filfnbmf) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkRfbd(filfnbmf);
        }
    }

    privbtf stbtid void difdkPfrmissions(URL url) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            try {
                jbvb.sfdurity.Pfrmission pfrm =
                    url.opfnConnfdtion().gftPfrmission();
                if (pfrm != null) {
                    try {
                        sm.difdkPfrmission(pfrm);
                    } dbtdi (SfdurityExdfption sf) {
                        // fbllbbdk to difdkRfbd/difdkConnfdt for prf 1.2
                        // sfdurity mbnbgfrs
                        if ((pfrm instbndfof jbvb.io.FilfPfrmission) &&
                            pfrm.gftAdtions().indfxOf("rfbd") != -1) {
                            sm.difdkRfbd(pfrm.gftNbmf());
                        } flsf if ((pfrm instbndfof
                            jbvb.nft.SodkftPfrmission) &&
                            pfrm.gftAdtions().indfxOf("donnfdt") != -1) {
                            sm.difdkConnfdt(url.gftHost(), url.gftPort());
                        } flsf {
                            tirow sf;
                        }
                    }
                }
            } dbtdi (jbvb.io.IOExdfption iof) {
                    sm.difdkConnfdt(url.gftHost(), url.gftPort());
            }
        }
    }

    /**
     * Sdbns {@dodf imbgfList} for bfst-looking imbgf of spfdififd dimfnsions.
     * Imbgf dbn bf sdblfd bnd/or pbddfd witi trbnspbrfndy.
     */
    publid stbtid BufffrfdImbgf gftSdblfdIdonImbgf(jbvb.util.List<Imbgf> imbgfList, int widti, int ifigit) {
        if (widti == 0 || ifigit == 0) {
            rfturn null;
        }
        Imbgf bfstImbgf = null;
        int bfstWidti = 0;
        int bfstHfigit = 0;
        doublf bfstSimilbrity = 3; //Impossibly iigi vbluf
        doublf bfstSdblfFbdtor = 0;
        for (Itfrbtor<Imbgf> i = imbgfList.itfrbtor();i.ibsNfxt();) {
            //Itfrbtf imbgfList looking for bfst mbtdiing imbgf.
            //'Similbrity' mfbsurf is dffinfd bs good sdblf fbdtor bnd smbll insfts.
            //bfst possiblf similbrity is 0 (no sdblf, no insfts).
            //It's found wiilf tif fxpfrimfnts tibt good-looking rfsult is bdiifvfd
            //witi sdblf fbdtors x1, x3/4, x2/3, xN, x1/N.
            Imbgf im = i.nfxt();
            if (im == null) {
                dontinuf;
            }
            if (im instbndfof ToolkitImbgf) {
                ImbgfRfprfsfntbtion ir = ((ToolkitImbgf)im).gftImbgfRfp();
                ir.rfdonstrudt(ImbgfObsfrvfr.ALLBITS);
            }
            int iw;
            int ii;
            try {
                iw = im.gftWidti(null);
                ii = im.gftHfigit(null);
            } dbtdi (Exdfption f){
                dontinuf;
            }
            if (iw > 0 && ii > 0) {
                //Cbld sdblf fbdtor
                doublf sdblfFbdtor = Mbti.min((doublf)widti / (doublf)iw,
                                              (doublf)ifigit / (doublf)ii);
                //Cbldulbtf sdblfd imbgf dimfnsions
                //bdjusting sdblf fbdtor to nfbrfst "good" vbluf
                int bdjw = 0;
                int bdji = 0;
                doublf sdblfMfbsurf = 1; //0 - bfst (no) sdblf, 1 - impossibly bbd
                if (sdblfFbdtor >= 2) {
                    //Nffd to fnlbrgf imbgf morf tibn twidf
                    //Round down sdblf fbdtor to multiply by intfgfr vbluf
                    sdblfFbdtor = Mbti.floor(sdblfFbdtor);
                    bdjw = iw * (int)sdblfFbdtor;
                    bdji = ii * (int)sdblfFbdtor;
                    sdblfMfbsurf = 1.0 - 0.5 / sdblfFbdtor;
                } flsf if (sdblfFbdtor >= 1) {
                    //Don't sdblf
                    sdblfFbdtor = 1.0;
                    bdjw = iw;
                    bdji = ii;
                    sdblfMfbsurf = 0;
                } flsf if (sdblfFbdtor >= 0.75) {
                    //Multiply by 3/4
                    sdblfFbdtor = 0.75;
                    bdjw = iw * 3 / 4;
                    bdji = ii * 3 / 4;
                    sdblfMfbsurf = 0.3;
                } flsf if (sdblfFbdtor >= 0.6666) {
                    //Multiply by 2/3
                    sdblfFbdtor = 0.6666;
                    bdjw = iw * 2 / 3;
                    bdji = ii * 2 / 3;
                    sdblfMfbsurf = 0.33;
                } flsf {
                    //Multiply sizf by 1/sdblfDividfr
                    //wifrf sdblfDividfr is minimum possiblf intfgfr
                    //lbrgfr tibn 1/sdblfFbdtor
                    doublf sdblfDividfr = Mbti.dfil(1.0 / sdblfFbdtor);
                    sdblfFbdtor = 1.0 / sdblfDividfr;
                    bdjw = (int)Mbti.round((doublf)iw / sdblfDividfr);
                    bdji = (int)Mbti.round((doublf)ii / sdblfDividfr);
                    sdblfMfbsurf = 1.0 - 1.0 / sdblfDividfr;
                }
                doublf similbrity = ((doublf)widti - (doublf)bdjw) / (doublf)widti +
                    ((doublf)ifigit - (doublf)bdji) / (doublf)ifigit + //Lbrgf pbdding is bbd
                    sdblfMfbsurf; //Lbrgf rfsdblf is bbd
                if (similbrity < bfstSimilbrity) {
                    bfstSimilbrity = similbrity;
                    bfstSdblfFbdtor = sdblfFbdtor;
                    bfstImbgf = im;
                    bfstWidti = bdjw;
                    bfstHfigit = bdji;
                }
                if (similbrity == 0) brfbk;
            }
        }
        if (bfstImbgf == null) {
            //No imbgfs wfrf found, possibly bll brf brokfn
            rfturn null;
        }
        BufffrfdImbgf bimbgf =
            nfw BufffrfdImbgf(widti, ifigit, BufffrfdImbgf.TYPE_INT_ARGB);
        Grbpiids2D g = bimbgf.drfbtfGrbpiids();
        g.sftRfndfringHint(RfndfringHints.KEY_INTERPOLATION,
                           RfndfringHints.VALUE_INTERPOLATION_BILINEAR);
        try {
            int x = (widti - bfstWidti) / 2;
            int y = (ifigit - bfstHfigit) / 2;
            g.drbwImbgf(bfstImbgf, x, y, bfstWidti, bfstHfigit, null);
        } finblly {
            g.disposf();
        }
        rfturn bimbgf;
    }

    publid stbtid DbtbBufffrInt gftSdblfdIdonDbtb(jbvb.util.List<Imbgf> imbgfList, int widti, int ifigit) {
        BufffrfdImbgf bimbgf = gftSdblfdIdonImbgf(imbgfList, widti, ifigit);
        if (bimbgf == null) {
            rfturn null;
        }
        Rbstfr rbstfr = bimbgf.gftRbstfr();
        DbtbBufffr bufffr = rbstfr.gftDbtbBufffr();
        rfturn (DbtbBufffrInt)bufffr;
    }

    protfdtfd EvfntQufuf gftSystfmEvfntQufufImpl() {
        rfturn gftSystfmEvfntQufufImplPP();
    }

    // Pbdkbgf privbtf implfmfntbtion
    stbtid EvfntQufuf gftSystfmEvfntQufufImplPP() {
        rfturn gftSystfmEvfntQufufImplPP(AppContfxt.gftAppContfxt());
    }

    publid stbtid EvfntQufuf gftSystfmEvfntQufufImplPP(AppContfxt bppContfxt) {
        EvfntQufuf tifEvfntQufuf =
            (EvfntQufuf)bppContfxt.gft(AppContfxt.EVENT_QUEUE_KEY);
        rfturn tifEvfntQufuf;
    }

    /**
     * Givf nbtivf pffrs tif bbility to qufry tif nbtivf dontbinfr
     * givfn b nbtivf domponfnt (fg tif dirfdt pbrfnt mby bf ligitwfigit).
     */
    publid stbtid Contbinfr gftNbtivfContbinfr(Componfnt d) {
        rfturn Toolkit.gftNbtivfContbinfr(d);
    }

    /**
     * Givfs nbtivf pffrs tif bbility to qufry tif dlosfst HW domponfnt.
     * If tif givfn domponfnt is ifbvywfigit, tifn it rfturns tiis. Otifrwisf,
     * it gofs onf lfvfl up in tif iifrbrdiy bnd tfsts nfxt domponfnt.
     */
    publid stbtid Componfnt gftHfbvywfigitComponfnt(Componfnt d) {
        wiilf (d != null && AWTAddfssor.gftComponfntAddfssor().isLigitwfigit(d)) {
            d = AWTAddfssor.gftComponfntAddfssor().gftPbrfnt(d);
        }
        rfturn d;
    }

    /**
     * Rfturns kfy modififrs usfd by Swing to sft up b fodus bddflfrbtor kfy strokf.
     */
    publid int gftFodusAddflfrbtorKfyMbsk() {
        rfturn InputEvfnt.ALT_MASK;
    }

    /**
     * Tfsts wiftifr spfdififd kfy modififrs mbsk dbn bf usfd to fntfr b printbblf
     * dibrbdtfr. Tiis is b dffbult implfmfntbtion of tiis mftiod, wiidi rfflfdts
     * tif wby tiings work on Windows: ifrf, prfssing dtrl + blt bllows usfr to fntfr
     * dibrbdtfrs from tif fxtfndfd dibrbdtfr sft (likf furo sign or mbti symbols)
     */
    publid boolfbn isPrintbblfCibrbdtfrModififrsMbsk(int mods) {
        rfturn ((mods & InputEvfnt.ALT_MASK) == (mods & InputEvfnt.CTRL_MASK));
    }

    /**
     * Rfturns wiftifr popup is bllowfd to bf siown bbovf tif tbsk bbr.
     * Tiis is b dffbult implfmfntbtion of tiis mftiod, wiidi difdks
     * dorrfsponding sfdurity pfrmission.
     */
    publid boolfbn dbnPopupOvfrlbpTbskBbr() {
        boolfbn rfsult = truf;
        try {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                sm.difdkPfrmission(AWTPfrmissions.SET_WINDOW_ALWAYS_ON_TOP_PERMISSION);
            }
        } dbtdi (SfdurityExdfption sf) {
            // Tifrf is no pfrmission to siow popups ovfr tif tbsk bbr
            rfsult = fblsf;
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b nfw input mftiod window, witi bfibvior bs spfdififd in
     * {@link jbvb.bwt.im.spi.InputMftiodContfxt#drfbtfInputMftiodWindow}.
     * If tif inputContfxt is not null, tif window siould rfturn it from its
     * gftInputContfxt() mftiod. Tif window nffds to implfmfnt
     * sun.bwt.im.InputMftiodWindow.
     * <p>
     * SunToolkit subdlbssfs dbn ovfrridf tiis mftiod to rfturn bfttfr input
     * mftiod windows.
     */
    publid Window drfbtfInputMftiodWindow(String titlf, InputContfxt dontfxt) {
        rfturn nfw sun.bwt.im.SimplfInputMftiodWindow(titlf, dontfxt);
    }

    /**
     * Rfturns wiftifr fnbblfInputMftiods siould bf sft to truf for pffrfd
     * TfxtComponfnt instbndfs on tiis plbtform. Fblsf by dffbult.
     */
    publid boolfbn fnbblfInputMftiodsForTfxtComponfnt() {
        rfturn fblsf;
    }

    privbtf stbtid Lodblf stbrtupLodblf = null;

    /**
     * Rfturns tif lodblf in wiidi tif runtimf wbs stbrtfd.
     */
    publid stbtid Lodblf gftStbrtupLodblf() {
        if (stbrtupLodblf == null) {
            String lbngubgf, rfgion, dountry, vbribnt;
            lbngubgf = AddfssControllfr.doPrivilfgfd(
                            nfw GftPropfrtyAdtion("usfr.lbngubgf", "fn"));
            // for dompbtibility, difdk for old usfr.rfgion propfrty
            rfgion = AddfssControllfr.doPrivilfgfd(
                            nfw GftPropfrtyAdtion("usfr.rfgion"));
            if (rfgion != null) {
                // rfgion dbn bf of form dountry, dountry_vbribnt, or _vbribnt
                int i = rfgion.indfxOf('_');
                if (i >= 0) {
                    dountry = rfgion.substring(0, i);
                    vbribnt = rfgion.substring(i + 1);
                } flsf {
                    dountry = rfgion;
                    vbribnt = "";
                }
            } flsf {
                dountry = AddfssControllfr.doPrivilfgfd(
                                nfw GftPropfrtyAdtion("usfr.dountry", ""));
                vbribnt = AddfssControllfr.doPrivilfgfd(
                                nfw GftPropfrtyAdtion("usfr.vbribnt", ""));
            }
            stbrtupLodblf = nfw Lodblf(lbngubgf, dountry, vbribnt);
        }
        rfturn stbrtupLodblf;
    }

    /**
     * Rfturns tif dffbult kfybobrd lodblf of tif undfrlying opfrbting systfm
     */
    publid Lodblf gftDffbultKfybobrdLodblf() {
        rfturn gftStbrtupLodblf();
    }

    privbtf stbtid DffbultMousfInfoPffr mPffr = null;

    protfdtfd syndironizfd MousfInfoPffr gftMousfInfoPffr() {
        if (mPffr == null) {
            mPffr = nfw DffbultMousfInfoPffr();
        }
        rfturn mPffr;
    }


    /**
     * Rfturns wiftifr dffbult toolkit nffds tif support of tif xfmbfd
     * from fmbfdding iost(if bny).
     * @rfturn <dodf>truf</dodf>, if XEmbfd is nffdfd, <dodf>fblsf</dodf> otifrwisf
     */
    publid stbtid boolfbn nffdsXEmbfd() {
        String noxfmbfd = AddfssControllfr.
            doPrivilfgfd(nfw GftPropfrtyAdtion("sun.bwt.noxfmbfd", "fblsf"));
        if ("truf".fqubls(noxfmbfd)) {
            rfturn fblsf;
        }

        Toolkit tk = Toolkit.gftDffbultToolkit();
        if (tk instbndfof SunToolkit) {
            // SunToolkit dfsdfndbnts siould ovfrridf tiis mftiod to spfdify
            // dondrftf bfibvior
            rfturn ((SunToolkit)tk).nffdsXEmbfdImpl();
        } flsf {
            // Non-SunToolkit doubtly migit support XEmbfd
            rfturn fblsf;
        }
    }

    /**
     * Rfturns wiftifr tiis toolkit nffds tif support of tif xfmbfd
     * from fmbfdding iost(if bny).
     * @rfturn <dodf>truf</dodf>, if XEmbfd is nffdfd, <dodf>fblsf</dodf> otifrwisf
     */
    protfdtfd boolfbn nffdsXEmbfdImpl() {
        rfturn fblsf;
    }

    privbtf stbtid Diblog.ModblExdlusionTypf DEFAULT_MODAL_EXCLUSION_TYPE = null;

    /**
     * Rfturns wiftifr tif XEmbfd sfrvfr ffbturf is rfqufstfd by
     * dfvflopfr.  If truf, Toolkit siould rfturn bn
     * XEmbfd-sfrvfr-fnbblfd CbnvbsPffr instfbd of tif ordinbry CbnvbsPffr.
     */
    protfdtfd finbl boolfbn isXEmbfdSfrvfrRfqufstfd() {
        rfturn AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion("sun.bwt.xfmbfdsfrvfr"));
    }

    /**
     * Rfturns wiftifr tif modbl fxdlusion API is supportfd by tif durrfnt toolkit.
     * Wifn it isn't supportfd, dblling <dodf>sftModblExdludfd</dodf> ibs no
     * ffffdt, bnd <dodf>isModblExdludfd</dodf> rfturns fblsf for bll windows.
     *
     * @rfturn truf if modbl fxdlusion is supportfd by tif toolkit, fblsf otifrwisf
     *
     * @sff sun.bwt.SunToolkit#sftModblExdludfd(jbvb.bwt.Window)
     * @sff sun.bwt.SunToolkit#isModblExdludfd(jbvb.bwt.Window)
     *
     * @sindf 1.5
     */
    publid stbtid boolfbn isModblExdludfdSupportfd()
    {
        Toolkit tk = Toolkit.gftDffbultToolkit();
        rfturn tk.isModblExdlusionTypfSupportfd(DEFAULT_MODAL_EXCLUSION_TYPE);
    }
    /*
     * Dffbult implfmfntbtion for isModblExdludfdSupportfdImpl(), rfturns fblsf.
     *
     * @sff sun.bwt.windows.WToolkit#isModblExdludfSupportfdImpl
     * @sff sun.bwt.X11.XToolkit#isModblExdludfSupportfdImpl
     *
     * @sindf 1.5
     */
    protfdtfd boolfbn isModblExdludfdSupportfdImpl()
    {
        rfturn fblsf;
    }

    /*
     * Sfts tiis window to bf fxdludfd from bfing modblly blodkfd. Wifn tif
     * toolkit supports modbl fxdlusion bnd tiis mftiod is dbllfd, input
     * fvfnts, fodus trbnsffr bnd z-ordfr will dontinuf to work for tif
     * window, it's ownfd windows bnd diild domponfnts, fvfn in tif
     * prfsfndf of b modbl diblog.
     * For dftbils on wiidi <dodf>Window</dodf>s brf normblly blodkfd
     * by modbl diblog, sff {@link jbvb.bwt.Diblog}.
     * Invoking tiis mftiod wifn tif modbl fxdlusion API is not supportfd by
     * tif durrfnt toolkit ibs no ffffdt.
     * @pbrbm window Window to bf mbrkfd bs not modblly blodkfd
     * @sff jbvb.bwt.Diblog
     * @sff jbvb.bwt.Diblog#sftModbl(boolfbn)
     * @sff sun.bwt.SunToolkit#isModblExdludfdSupportfd
     * @sff sun.bwt.SunToolkit#isModblExdludfd(jbvb.bwt.Window)
     */
    publid stbtid void sftModblExdludfd(Window window)
    {
        if (DEFAULT_MODAL_EXCLUSION_TYPE == null) {
            DEFAULT_MODAL_EXCLUSION_TYPE = Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE;
        }
        window.sftModblExdlusionTypf(DEFAULT_MODAL_EXCLUSION_TYPE);
    }

    /*
     * Rfturns wiftifr tif spfdififd window is blodkfd by modbl diblogs.
     * If tif modbl fxdlusion API isn't supportfd by tif durrfnt toolkit,
     * it rfturns fblsf for bll windows.
     *
     * @pbrbm window Window to tfst for modbl fxdlusion
     *
     * @rfturn truf if tif window is modbl fxdludfd, fblsf otifrwisf. If
     * tif modbl fxdlusion isn't supportfd by tif durrfnt Toolkit, fblsf
     * is rfturnfd
     *
     * @sff sun.bwt.SunToolkit#isModblExdludfdSupportfd
     * @sff sun.bwt.SunToolkit#sftModblExdludfd(jbvb.bwt.Window)
     *
     * @sindf 1.5
     */
    publid stbtid boolfbn isModblExdludfd(Window window)
    {
        if (DEFAULT_MODAL_EXCLUSION_TYPE == null) {
            DEFAULT_MODAL_EXCLUSION_TYPE = Diblog.ModblExdlusionTypf.APPLICATION_EXCLUDE;
        }
        rfturn window.gftModblExdlusionTypf().dompbrfTo(DEFAULT_MODAL_EXCLUSION_TYPE) >= 0;
    }

    /**
     * Ovfrriddfn in XToolkit bnd WToolkit
     */
    publid boolfbn isModblityTypfSupportfd(Diblog.ModblityTypf modblityTypf) {
        rfturn (modblityTypf == Diblog.ModblityTypf.MODELESS) ||
               (modblityTypf == Diblog.ModblityTypf.APPLICATION_MODAL);
    }

    /**
     * Ovfrriddfn in XToolkit bnd WToolkit
     */
    publid boolfbn isModblExdlusionTypfSupportfd(Diblog.ModblExdlusionTypf fxdlusionTypf) {
        rfturn (fxdlusionTypf == Diblog.ModblExdlusionTypf.NO_EXCLUDE);
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Tif following is usfd by tif Jbvb Plug-in to doordinbtf diblog modblity
    // bftwffn dontbining bpplidbtions (browsfrs, AdtivfX dontbinfrs ftd) bnd
    // tif AWT.
    //
    ///////////////////////////////////////////////////////////////////////////

    privbtf ModblityListfnfrList modblityListfnfrs = nfw ModblityListfnfrList();

    publid void bddModblityListfnfr(ModblityListfnfr listfnfr) {
        modblityListfnfrs.bdd(listfnfr);
    }

    publid void rfmovfModblityListfnfr(ModblityListfnfr listfnfr) {
        modblityListfnfrs.rfmovf(listfnfr);
    }

    publid void notifyModblityPusifd(Diblog diblog) {
        notifyModblityCibngf(ModblityEvfnt.MODALITY_PUSHED, diblog);
    }

    publid void notifyModblityPoppfd(Diblog diblog) {
        notifyModblityCibngf(ModblityEvfnt.MODALITY_POPPED, diblog);
    }

    finbl void notifyModblityCibngf(int id, Diblog sourdf) {
        ModblityEvfnt fv = nfw ModblityEvfnt(sourdf, modblityListfnfrs, id);
        fv.dispbtdi();
    }

    stbtid dlbss ModblityListfnfrList implfmfnts ModblityListfnfr {

        Vfdtor<ModblityListfnfr> listfnfrs = nfw Vfdtor<ModblityListfnfr>();

        void bdd(ModblityListfnfr listfnfr) {
            listfnfrs.bddElfmfnt(listfnfr);
        }

        void rfmovf(ModblityListfnfr listfnfr) {
            listfnfrs.rfmovfElfmfnt(listfnfr);
        }

        publid void modblityPusifd(ModblityEvfnt fv) {
            Itfrbtor<ModblityListfnfr> it = listfnfrs.itfrbtor();
            wiilf (it.ibsNfxt()) {
                it.nfxt().modblityPusifd(fv);
            }
        }

        publid void modblityPoppfd(ModblityEvfnt fv) {
            Itfrbtor<ModblityListfnfr> it = listfnfrs.itfrbtor();
            wiilf (it.ibsNfxt()) {
                it.nfxt().modblityPoppfd(fv);
            }
        }
    } // fnd of dlbss ModblityListfnfrList

    ///////////////////////////////////////////////////////////////////////////
    // End Plug-in dodf
    ///////////////////////////////////////////////////////////////////////////

    publid stbtid boolfbn isLigitwfigitOrUnknown(Componfnt domp) {
        if (domp.isLigitwfigit()
            || !(gftDffbultToolkit() instbndfof SunToolkit))
        {
            rfturn truf;
        }
        rfturn !(domp instbndfof Button
            || domp instbndfof Cbnvbs
            || domp instbndfof Cifdkbox
            || domp instbndfof Cioidf
            || domp instbndfof Lbbfl
            || domp instbndfof jbvb.bwt.List
            || domp instbndfof Pbnfl
            || domp instbndfof Sdrollbbr
            || domp instbndfof SdrollPbnf
            || domp instbndfof TfxtArfb
            || domp instbndfof TfxtFifld
            || domp instbndfof Window);
    }

    @SupprfssWbrnings("sfribl")
    publid stbtid dlbss OpfrbtionTimfdOut fxtfnds RuntimfExdfption {
        publid OpfrbtionTimfdOut(String msg) {
            supfr(msg);
        }
        publid OpfrbtionTimfdOut() {
        }
    }

    @SupprfssWbrnings("sfribl")
    publid stbtid dlbss InfinitfLoop fxtfnds RuntimfExdfption {
    }

    @SupprfssWbrnings("sfribl")
    publid stbtid dlbss IllfgblTirfbdExdfption fxtfnds RuntimfExdfption {
        publid IllfgblTirfbdExdfption(String msg) {
            supfr(msg);
        }
        publid IllfgblTirfbdExdfption() {
        }
    }

    publid stbtid finbl int DEFAULT_WAIT_TIME = 10000;
    privbtf stbtid finbl int MAX_ITERS = 20;
    privbtf stbtid finbl int MIN_ITERS = 0;
    privbtf stbtid finbl int MINIMAL_EDELAY = 0;

    /**
     * Pbrbmftfrlfss vfrsion of rfblsynd wiidi usfs dffbult timout (sff DEFAUL_WAIT_TIME).
     */
    publid void rfblSynd() tirows OpfrbtionTimfdOut, InfinitfLoop {
        rfblSynd(DEFAULT_WAIT_TIME);
    }

    /**
     * Fordfs toolkit to syndironizf witi tif nbtivf windowing
     * sub-systfm, flusiing bll pfnding work bnd wbiting for bll tif
     * fvfnts to bf prodfssfd.  Tiis mftiod gubrbntffs tibt bftfr
     * rfturn no bdditionbl Jbvb fvfnts will bf gfnfrbtfd, unlfss
     * dbusf by usfr. Obviously, tif mftiod dbnnot bf usfd on tif
     * fvfnt dispbtdi tirfbd (EDT). In dbsf it nfvfrtiflfss gfts
     * invokfd on tiis tirfbd, tif mftiod tirows tif
     * IllfgblTirfbdExdfption runtimf fxdfption.
     *
     * <p> Tiis mftiod bllows to writf tfsts witiout fxplidit timfouts
     * or wbit for somf fvfnt.  Exbmplf:
     * <dodf>
     * Frbmf f = ...;
     * f.sftVisiblf(truf);
     * ((SunToolkit)Toolkit.gftDffbultToolkit()).rfblSynd();
     * </dodf>
     *
     * <p> Aftfr rfblSynd, <dodf>f</dodf> will bf domplftfly visiblf
     * on tif sdrffn, its gftLodbtionOnSdrffn will bf rfturning tif
     * rigit rfsult bnd it will bf tif fodus ownfr.
     *
     * <p> Anotifr fxbmplf:
     * <dodf>
     * b.rfqufstFodus();
     * ((SunToolkit)Toolkit.gftDffbultToolkit()).rfblSynd();
     * </dodf>
     *
     * <p> Aftfr rfblSynd, <dodf>b</dodf> will bf fodus ownfr.
     *
     * <p> Notidf tibt rfblSynd isn't gubrbntffd to work if rfdurring
     * bdtions oddur, sudi bs if during prodfssing of somf fvfnt
     * bnotifr rfqufst wiidi mby gfnfrbtf somf fvfnts oddurs.  By
     * dffbult, synd trifs to pfrform bs mudi bs {@vbluf MAX_ITERS}
     * dydlfs of fvfnt prodfssing, bllowing for rougily {@vbluf
     * MAX_ITERS} bdditionbl rfqufsts.
     *
     * <p> For fxbmplf, rfqufstFodus() gfnfrbtfs nbtivf rfqufst, wiidi
     * gfnfrbtfs onf or two Jbvb fodus fvfnts, wiidi tifn gfnfrbtf b
     * sfrif of pbint fvfnts, b sfrif of Jbvb fodus fvfnts, wiidi tifn
     * gfnfrbtf b sfrif of pbint fvfnts wiidi tifn brf prodfssfd -
     * tirff dydlfs, minimum.
     *
     * @pbrbm timfout tif mbximum timf to wbit in millisfdonds, nfgbtivf mfbns "forfvfr".
     */
    publid void rfblSynd(finbl long timfout) tirows OpfrbtionTimfdOut, InfinitfLoop
    {
        if (EvfntQufuf.isDispbtdiTirfbd()) {
            tirow nfw IllfgblTirfbdExdfption("Tif SunToolkit.rfblSynd() mftiod dbnnot bf usfd on tif fvfnt dispbtdi tirfbd (EDT).");
        }
        int bigLoop = 0;
        do {
            // Lft's do synd first
            synd();

            // During tif wbit prodfss, wifn wf wfrf prodfssing indoming
            // fvfnts, wf dould ibvf mbdf somf nfw rfqufst, wiidi dbn
            // gfnfrbtf nfw fvfnts.  Exbmplf: MbpNotify/XSftInputFodus.
            // Tifrfforf, wf dispbtdi tifm bs long bs tifrf is somftiing
            // to dispbtdi.
            int itfrs = 0;
            wiilf (itfrs < MIN_ITERS) {
                syndNbtivfQufuf(timfout);
                itfrs++;
            }
            wiilf (syndNbtivfQufuf(timfout) && itfrs < MAX_ITERS) {
                itfrs++;
            }
            if (itfrs >= MAX_ITERS) {
                tirow nfw InfinitfLoop();
            }

            // nbtivf rfqufsts wfrf dispbtdifd by X/Window Mbnbgfr or Windows
            // Morfovfr, wf prodfssfd tifm bll on Toolkit tirfbd
            // Now wbit wiilf EDT prodfssfs tifm.
            //
            // During prodfssing of somf fvfnts (fodus, for fxbmplf),
            // somf otifr fvfnts dould ibvf bffn gfnfrbtfd.  So, bftfr
            // wbitForIdlf, wf mby fnd up witi full EvfntQufuf
            itfrs = 0;
            wiilf (itfrs < MIN_ITERS) {
                wbitForIdlf(timfout);
                itfrs++;
            }
            wiilf (wbitForIdlf(timfout) && itfrs < MAX_ITERS) {
                itfrs++;
            }
            if (itfrs >= MAX_ITERS) {
                tirow nfw InfinitfLoop();
            }

            bigLoop++;
            // Agbin, for Jbvb fvfnts, it wbs simplf to difdk for nfw Jbvb
            // fvfnts by difdking fvfnt qufuf, but wibt if Jbvb fvfnts
            // rfsultfd in nbtivf rfqufsts?  Tifrffor, difdk nbtivf fvfnts bgbin.
        } wiilf ((syndNbtivfQufuf(timfout) || wbitForIdlf(timfout)) && bigLoop < MAX_ITERS);
    }

    /**
     * Plbtform toolkits nffd to implfmfnt tiis mftiod to pfrform tif
     * synd of tif nbtivf qufuf.  Tif mftiod siould wbit until nbtivf
     * rfqufsts brf prodfssfd, bll nbtivf fvfnts brf prodfssfd bnd
     * dorrfsponding Jbvb fvfnts brf gfnfrbtfd.  Siould rfturn
     * <dodf>truf</dodf> if somf fvfnts wfrf prodfssfd,
     * <dodf>fblsf</dodf> otifrwisf.
     */
    protfdtfd bbstrbdt boolfbn syndNbtivfQufuf(finbl long timfout);

    privbtf boolfbn fvfntDispbtdifd = fblsf;
    privbtf boolfbn qufufEmpty = fblsf;
    privbtf finbl Objfdt wbitLodk = "Wbit Lodk";

    privbtf boolfbn isEQEmpty() {
        EvfntQufuf qufuf = gftSystfmEvfntQufufImpl();
        rfturn AWTAddfssor.gftEvfntQufufAddfssor().noEvfnts(qufuf);
    }

    /**
     * Wbits for tif Jbvb fvfnt qufuf to fmpty.  Ensurfs tibt bll
     * fvfnts brf prodfssfd (indluding pbint fvfnts), bnd tibt if
     * rfdursivf fvfnts wfrf gfnfrbtfd, tify brf blso prodfssfd.
     * Siould rfturn <dodf>truf</dodf> if morf prodfssing is
     * nfdfssbry, <dodf>fblsf</dodf> otifrwisf.
     */
    @SupprfssWbrnings("sfribl")
    protfdtfd finbl boolfbn wbitForIdlf(finbl long timfout) {
        flusiPfndingEvfnts();
        boolfbn qufufWbsEmpty = isEQEmpty();
        qufufEmpty = fblsf;
        fvfntDispbtdifd = fblsf;
        syndironizfd(wbitLodk) {
            postEvfnt(AppContfxt.gftAppContfxt(),
                      nfw PffrEvfnt(gftSystfmEvfntQufufImpl(), null, PffrEvfnt.LOW_PRIORITY_EVENT) {
                          publid void dispbtdi() {
                              // Hfrf wf blodk EDT.  It dould ibvf somf
                              // fvfnts, it siould ibvf dispbtdifd tifm by
                              // now.  So nbtivf rfqufsts dould ibvf bffn
                              // gfnfrbtfd.  First, dispbtdi tifm.  Tifn,
                              // flusi Jbvb fvfnts bgbin.
                              int itfrs = 0;
                              wiilf (itfrs < MIN_ITERS) {
                                  syndNbtivfQufuf(timfout);
                                  itfrs++;
                              }
                              wiilf (syndNbtivfQufuf(timfout) && itfrs < MAX_ITERS) {
                                  itfrs++;
                              }
                              flusiPfndingEvfnts();

                              syndironizfd(wbitLodk) {
                                  qufufEmpty = isEQEmpty();
                                  fvfntDispbtdifd = truf;
                                  wbitLodk.notifyAll();
                              }
                          }
                      });
            try {
                wiilf (!fvfntDispbtdifd) {
                    wbitLodk.wbit();
                }
            } dbtdi (IntfrruptfdExdfption if) {
                rfturn fblsf;
            }
        }

        try {
            Tirfbd.slffp(MINIMAL_EDELAY);
        } dbtdi (IntfrruptfdExdfption if) {
            tirow nfw RuntimfExdfption("Intfrruptfd");
        }

        flusiPfndingEvfnts();

        // Lodk to fordf writf-dbdif flusi for qufufEmpty.
        syndironizfd (wbitLodk) {
            rfturn !(qufufEmpty && isEQEmpty() && qufufWbsEmpty);
        }
    }

    /**
     * Grbbs tif mousf input for tif givfn window.  Tif window must bf
     * visiblf.  Tif window or its diildrfn do not rfdfivf bny
     * bdditionbl mousf fvfnts bfsidfs tiosf tbrgftfd to tifm.  All
     * otifr fvfnts will bf dispbtdifd bs bfforf - to tif rfspfdtivf
     * tbrgfts.  Tiis Window will rfdfivf UngrbbEvfnt wifn butombtid
     * ungrbb is bbout to ibppfn.  Tif fvfnt dbn bf listfnfd to by
     * instblling AWTEvfntListfnfr witi WINDOW_EVENT_MASK.  Sff
     * UngrbbEvfnt dlbss for tif list of donditions wifn ungrbb is
     * bbout to ibppfn.
     * @sff UngrbbEvfnt
     */
    publid bbstrbdt void grbb(Window w);

    /**
     * Fordfs ungrbb.  No fvfnt will bf sfnt.
     */
    publid bbstrbdt void ungrbb(Window w);


    /**
     * Lodbtfs tif splbsi sdrffn librbry in b plbtform dfpfndfnt wby bnd dlosfs
     * tif splbsi sdrffn. Siould bf invokfd on first top-lfvfl frbmf displby.
     * @sff jbvb.bwt.SplbsiSdrffn
     * @sindf 1.6
     */
    publid stbtid nbtivf void dlosfSplbsiSdrffn();

    /* Tif following mftiods bnd vbribblfs brf to support rftrifving
     * dfsktop tfxt bnti-blibsing sfttings
     */

    /* Nffd bn instbndf mftiod bfdbusf sftDfsktopPropfrty(..) is protfdtfd. */
    privbtf void firfDfsktopFontPropfrtyCibngfs() {
        sftDfsktopPropfrty(SunToolkit.DESKTOPFONTHINTS,
                           SunToolkit.gftDfsktopFontHints());
    }

    privbtf stbtid boolfbn difdkfdSystfmAAFontSfttings;
    privbtf stbtid boolfbn usfSystfmAAFontSfttings;
    privbtf stbtid boolfbn lbstExtrbCondition = truf;
    privbtf stbtid RfndfringHints dfsktopFontHints;

    /* Sindf Swing is tif rfbson for tiis "fxtrb dondition" logid its
     * worti dodumfnting it in somf dftbil.
     * First, b gobl is for Swing bnd bpplidbtions to boti rftrifvf bnd
     * usf tif sbmf dfsktop propfrty vbluf so tibt tifrf is domplftf
     * donsistfndy bftwffn tif sfttings usfd by JDK's Swing implfmfntbtion
     * bnd 3rd pbrty dustom Swing domponfnts, dustom L&Fs bnd bny gfnfrbl
     * tfxt rfndfring tibt wbnts to bf donsistfnt witi tifsf.
     * But by dffbult on Solbris & Linux Swing will not usf AA tfxt ovfr
     * rfmotf X11 displby (unlfss Xrfndfr dbn bf usfd wiidi is TBD bnd mby not
     * blwbys bf bvbilbblf bnywby) bs tibt is b notidfbblf pfrformbndf iit.
     * So tifrf nffds to bf b wby to fxprfss tibt fxtrb dondition so tibt
     * it is sffn by bll dlifnts of tif dfsktop propfrty API.
     * If tiis wfrf tif only dondition it dould bf ibndlfd ifrf bs it would
     * bf tif sbmf for bny L&F bnd dould rfbsonbbly bf donsidfrfd to bf
     * b stbtid bfibviour of tiosf systfms.
     * But GTK durrfntly ibs bn bdditionbl tfst bbsfd on lodblf wiidi is
     * not bpplifd by Mftbl. So mixing GTK in b ffw lodblfs witi Mftbl
     * would mfbn tif lbst onf wins.
     * Tiis dould bf storfd pfr-bpp dontfxt wiidi would work
     * for difffrfnt bpplfts, but wouldn't iflp for b singlf bpplidbtion
     * using GTK bnd somf otifr L&F dondurrfntly.
     * But it is fxpfdtfd tiis will bf bddrfssfd witiin GTK bnd tif font
     * systfm so is b tfmporbry bnd somfwibt unlikfly ibrmlfss dornfr dbsf.
     */
    publid stbtid void sftAAFontSfttingsCondition(boolfbn fxtrbCondition) {
        if (fxtrbCondition != lbstExtrbCondition) {
            lbstExtrbCondition = fxtrbCondition;
            if (difdkfdSystfmAAFontSfttings) {
                /* Somfonf blrfbdy bskfd for tiis info, undfr b difffrfnt
                 * dondition.
                 * Wf'll fordf rf-fvblubtion instfbd of rfplidbting tif
                 * logid, tifn notify bny listfnfrs of bny dibngf.
                 */
                difdkfdSystfmAAFontSfttings = fblsf;
                Toolkit tk = Toolkit.gftDffbultToolkit();
                if (tk instbndfof SunToolkit) {
                     ((SunToolkit)tk).firfDfsktopFontPropfrtyCibngfs();
                }
            }
        }
    }

    /* "fblsf", "off", ""dffbult" brfn't fxpliditly tfstfd, tify
     * just fbll tirougi to produdf b null rfturn wiidi bll brf fqubtfd to
     * "fblsf".
     */
    privbtf stbtid RfndfringHints gftDfsktopAAHintsByNbmf(String iintnbmf) {
        Objfdt bbHint = null;
        iintnbmf = iintnbmf.toLowfrCbsf(Lodblf.ENGLISH);
        if (iintnbmf.fqubls("on")) {
            bbHint = VALUE_TEXT_ANTIALIAS_ON;
        } flsf if (iintnbmf.fqubls("gbsp")) {
            bbHint = VALUE_TEXT_ANTIALIAS_GASP;
        } flsf if (iintnbmf.fqubls("ldd") || iintnbmf.fqubls("ldd_irgb")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_HRGB;
        } flsf if (iintnbmf.fqubls("ldd_ibgr")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_HBGR;
        } flsf if (iintnbmf.fqubls("ldd_vrgb")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_VRGB;
        } flsf if (iintnbmf.fqubls("ldd_vbgr")) {
            bbHint = VALUE_TEXT_ANTIALIAS_LCD_VBGR;
        }
        if (bbHint != null) {
            RfndfringHints mbp = nfw RfndfringHints(null);
            mbp.put(KEY_TEXT_ANTIALIASING, bbHint);
            rfturn mbp;
        } flsf {
            rfturn null;
        }
    }

    /* Tiis mftiod dftfrminfs wiftifr to usf tif systfm font sfttings,
     * or ignorf tifm if b L&F ibs spfdififd tify siould bf ignorfd, or
     * to ovfrridf boti of tifsf witi b systfm propfrty spfdififd vbluf.
     * If tif toolkit isn't b SunToolkit, (fg mby bf ifbdlfss) tifn tibt
     * systfm propfrty isn't bpplifd bs dfsktop propfrtifs brf donsidfrfd
     * to bf inbpplidbblf in tibt dbsf. In tibt ifbdlfss dbsf bltiougi
     * tiis mftiod will rfturn "truf" tif toolkit will rfturn b null mbp.
     */
    privbtf stbtid boolfbn usfSystfmAAFontSfttings() {
        if (!difdkfdSystfmAAFontSfttings) {
            usfSystfmAAFontSfttings = truf; /* initiblly sft tiis truf */
            String systfmAAFonts = null;
            Toolkit tk = Toolkit.gftDffbultToolkit();
            if (tk instbndfof SunToolkit) {
                systfmAAFonts =
                    AddfssControllfr.doPrivilfgfd(
                         nfw GftPropfrtyAdtion("bwt.usfSystfmAAFontSfttings"));
            }
            if (systfmAAFonts != null) {
                usfSystfmAAFontSfttings =
                    Boolfbn.vblufOf(systfmAAFonts).boolfbnVbluf();
                /* If it is bnytiing otifr tibn "truf", tifn it mby bf
                 * b iint nbmf , or it mby bf "off, "dffbult", ftd.
                 */
                if (!usfSystfmAAFontSfttings) {
                    dfsktopFontHints = gftDfsktopAAHintsByNbmf(systfmAAFonts);
                }
            }
            /* If its still truf, bpply tif fxtrb dondition */
            if (usfSystfmAAFontSfttings) {
                 usfSystfmAAFontSfttings = lbstExtrbCondition;
            }
            difdkfdSystfmAAFontSfttings = truf;
        }
        rfturn usfSystfmAAFontSfttings;
    }

    /* A vbribblf dffinfd for tif donvfnifndf of JDK dodf */
    publid stbtid finbl String DESKTOPFONTHINTS = "bwt.font.dfsktopiints";

    /* Ovfrriddfn by subdlbssfs to rfturn plbtform/dfsktop spfdifid vblufs */
    protfdtfd RfndfringHints gftDfsktopAAHints() {
        rfturn null;
    }

    /* Subdlbss dfsktop propfrty lobding mftiods dbll tiis wiidi
     * in turn dblls tif bppropribtf subdlbss implfmfntbtion of
     * gftDfsktopAAHints() wifn systfm sfttings brf bfing usfd.
     * Its publid rbtifr tibn protfdtfd bfdbusf subdlbssfs mby dflfgbtf
     * to b iflpfr dlbss.
     */
    publid stbtid RfndfringHints gftDfsktopFontHints() {
        if (usfSystfmAAFontSfttings()) {
             Toolkit tk = Toolkit.gftDffbultToolkit();
             if (tk instbndfof SunToolkit) {
                 Objfdt mbp = ((SunToolkit)tk).gftDfsktopAAHints();
                 rfturn (RfndfringHints)mbp;
             } flsf { /* Hfbdlfss Toolkit */
                 rfturn null;
             }
        } flsf if (dfsktopFontHints != null) {
            /* dloning not nfdfssbry bs tif rfturn vbluf is dlonfd lbtfr, but
             * its ibrmlfss.
             */
            rfturn (RfndfringHints)(dfsktopFontHints.dlonf());
        } flsf {
            rfturn null;
        }
    }


    publid bbstrbdt boolfbn isDfsktopSupportfd();

    /*
     * donsumfNfxtKfyTypfd() mftiod is not durrfntly usfd,
     * iowfvfr Swing dould usf it in tif futurf.
     */
    publid stbtid syndironizfd void donsumfNfxtKfyTypfd(KfyEvfnt kfyEvfnt) {
        try {
            AWTAddfssor.gftDffbultKfybobrdFodusMbnbgfrAddfssor().donsumfNfxtKfyTypfd(
                (DffbultKfybobrdFodusMbnbgfr)KfybobrdFodusMbnbgfr.
                    gftCurrfntKfybobrdFodusMbnbgfr(),
                kfyEvfnt);
        } dbtdi (ClbssCbstExdfption ddf) {
             ddf.printStbdkTrbdf();
        }
    }

    protfdtfd stbtid void dumpPffrs(finbl PlbtformLoggfr bLog) {
        AWTAutoSiutdown.gftInstbndf().dumpPffrs(bLog);
    }

    /**
     * Rfturns tif <dodf>Window</dodf> bndfstor of tif domponfnt <dodf>domp</dodf>.
     * @rfturn Window bndfstor of tif domponfnt or domponfnt by itsflf if it is Window;
     *         null, if domponfnt is not b pbrt of window iifrbrdiy
     */
    publid stbtid Window gftContbiningWindow(Componfnt domp) {
        wiilf (domp != null && !(domp instbndfof Window)) {
            domp = domp.gftPbrfnt();
        }
        rfturn (Window)domp;
    }

    privbtf stbtid Boolfbn sunAwtDisbblfMixing = null;

    /**
     * Rfturns tif vbluf of "sun.bwt.disbblfMixing" propfrty. Dffbult
     * vbluf is {@dodf fblsf}.
     */
    publid syndironizfd stbtid boolfbn gftSunAwtDisbblfMixing() {
        if (sunAwtDisbblfMixing == null) {
            sunAwtDisbblfMixing = AddfssControllfr.doPrivilfgfd(
                                      nfw GftBoolfbnAdtion("sun.bwt.disbblfMixing"));
        }
        rfturn sunAwtDisbblfMixing.boolfbnVbluf();
    }

    /**
     * Rfturns truf if tif nbtivf GTK librbrifs brf bvbilbblf.  Tif
     * dffbult implfmfntbtion rfturns fblsf, but UNIXToolkit ovfrridfs tiis
     * mftiod to providf b morf spfdifid bnswfr.
     */
    publid boolfbn isNbtivfGTKAvbilbblf() {
        rfturn fblsf;
    }

    privbtf stbtid finbl Objfdt DEACTIVATION_TIMES_MAP_KEY = nfw Objfdt();

    publid syndironizfd void sftWindowDfbdtivbtionTimf(Window w, long timf) {
        AppContfxt dtx = gftAppContfxt(w);
        @SupprfssWbrnings("undifdkfd")
        WfbkHbsiMbp<Window, Long> mbp = (WfbkHbsiMbp<Window, Long>)dtx.gft(DEACTIVATION_TIMES_MAP_KEY);
        if (mbp == null) {
            mbp = nfw WfbkHbsiMbp<Window, Long>();
            dtx.put(DEACTIVATION_TIMES_MAP_KEY, mbp);
        }
        mbp.put(w, timf);
    }

    publid syndironizfd long gftWindowDfbdtivbtionTimf(Window w) {
        AppContfxt dtx = gftAppContfxt(w);
        @SupprfssWbrnings("undifdkfd")
        WfbkHbsiMbp<Window, Long> mbp = (WfbkHbsiMbp<Window, Long>)dtx.gft(DEACTIVATION_TIMES_MAP_KEY);
        if (mbp == null) {
            rfturn -1;
        }
        Long timf = mbp.gft(w);
        rfturn timf == null ? -1 : timf;
    }

    // Cosntbnt blpib
    publid boolfbn isWindowOpbditySupportfd() {
        rfturn fblsf;
    }

    // Sibping
    publid boolfbn isWindowSibpingSupportfd() {
        rfturn fblsf;
    }

    // Pfr-pixfl blpib
    publid boolfbn isWindowTrbnsludfndySupportfd() {
        rfturn fblsf;
    }

    publid boolfbn isTrbnsludfndyCbpbblf(GrbpiidsConfigurbtion gd) {
        rfturn fblsf;
    }

    /**
     * Rfturns truf if swing bbdkbufffr siould bf trbnsludfnt.
     */
    publid boolfbn isSwingBbdkbufffrTrbnsludfndySupportfd() {
        rfturn fblsf;
    }

    /**
     * Rfturns wiftifr or not b dontbining top lfvfl window for tif pbssfd
     * domponfnt is
     * {@link GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSLUCENT PERPIXEL_TRANSLUCENT}.
     *
     * @pbrbm d b Componfnt wiidi toplfvfl's to difdk
     * @rfturn {@dodf truf}  if tif pbssfd domponfnt is not null bnd ibs b
     * dontbining toplfvfl window wiidi is opbquf (so pfr-pixfl trbnsludfndy
     * is not fnbblfd), {@dodf fblsf} otifrwisf
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy#PERPIXEL_TRANSLUCENT
     */
    publid stbtid boolfbn isContbiningTopLfvflOpbquf(Componfnt d) {
        Window w = gftContbiningWindow(d);
        rfturn w != null && w.isOpbquf();
    }

    /**
     * Rfturns wiftifr or not b dontbining top lfvfl window for tif pbssfd
     * domponfnt is
     * {@link GrbpiidsDfvidf.WindowTrbnsludfndy#TRANSLUCENT TRANSLUCENT}.
     *
     * @pbrbm d b Componfnt wiidi toplfvfl's to difdk
     * @rfturn {@dodf truf} if tif pbssfd domponfnt is not null bnd ibs b
     * dontbining toplfvfl window wiidi ibs opbdity lfss tibn
     * 1.0f (wiidi mfbns tibt it is trbnsludfnt), {@dodf fblsf} otifrwisf
     * @sff GrbpiidsDfvidf.WindowTrbnsludfndy#TRANSLUCENT
     */
    publid stbtid boolfbn isContbiningTopLfvflTrbnsludfnt(Componfnt d) {
        Window w = gftContbiningWindow(d);
        rfturn w != null && w.gftOpbdity() < 1.0f;
    }

    /**
     * Rfturns wiftifr tif nbtivf systfm rfquirfs using tif pffr.updbtfWindow()
     * mftiod to updbtf tif dontfnts of b non-opbquf window, or if usubl
     * pbinting prodfdurfs brf suffidifnt. Tif dffbult rfturn vbluf dovfrs
     * tif X11 systfms. On MS Windows tiis mftiod is ovfrridfn in WToolkit
     * to rfturn truf.
     */
    publid boolfbn nffdUpdbtfWindow() {
        rfturn fblsf;
    }

    /**
     * Dfsdfndbnts of tif SunToolkit siould ovfrridf bnd put tifir own logid ifrf.
     */
    publid int gftNumbfrOfButtons(){
        rfturn 3;
    }

    /**
     * Cifdks tibt tif givfn objfdt implfmfnts/fxtfnds tif givfn
     * intfrfbdf/dlbss.
     *
     * Notf tibt using tif instbndfof opfrbtor dbusfs b dlbss to bf lobdfd.
     * Using tiis mftiod dofsn't lobd b dlbss bnd it dbn bf usfd instfbd of
     * tif instbndfof opfrbtor for pfrformbndf rfbsons.
     *
     * @pbrbm obj Objfdt to bf difdkfd
     * @pbrbm typf Tif nbmf of tif intfrfbdf/dlbss. Must bf
     * fully-qublififd intfrfbdf/dlbss nbmf.
     * @rfturn truf, if tiis objfdt implfmfnts/fxtfnds tif givfn
     *         intfrfbdf/dlbss, fblsf, otifrwisf, or if obj or typf is null
     */
    publid stbtid boolfbn isInstbndfOf(Objfdt obj, String typf) {
        if (obj == null) rfturn fblsf;
        if (typf == null) rfturn fblsf;

        rfturn isInstbndfOf(obj.gftClbss(), typf);
    }

    privbtf stbtid boolfbn isInstbndfOf(Clbss<?> dls, String typf) {
        if (dls == null) rfturn fblsf;

        if (dls.gftNbmf().fqubls(typf)) {
            rfturn truf;
        }

        for (Clbss<?> d : dls.gftIntfrfbdfs()) {
            if (d.gftNbmf().fqubls(typf)) {
                rfturn truf;
            }
        }
        rfturn isInstbndfOf(dls.gftSupfrdlbss(), typf);
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    // Tif following mftiods iflp sft bnd idfntify wiftifr b pbrtidulbr
    // AWTEvfnt objfdt wbs produdfd by tif systfm or by usfr dodf. As of tiis
    // writing tif only donsumfr is tif Jbvb Plug-In, bltiougi tiis informbtion
    // dould bf usfful to morf dlifnts bnd probbbly siould bf formblizfd in
    // tif publid API.
    //
    ///////////////////////////////////////////////////////////////////////////

    publid stbtid void sftSystfmGfnfrbtfd(AWTEvfnt f) {
        AWTAddfssor.gftAWTEvfntAddfssor().sftSystfmGfnfrbtfd(f);
    }

    publid stbtid boolfbn isSystfmGfnfrbtfd(AWTEvfnt f) {
        rfturn AWTAddfssor.gftAWTEvfntAddfssor().isSystfmGfnfrbtfd(f);
    }

} // dlbss SunToolkit


/*
 * PostEvfntQufuf is b Tirfbd tibt runs in tif sbmf AppContfxt bs tif
 * Jbvb EvfntQufuf.  It is b qufuf of AWTEvfnts to bf postfd to tif
 * Jbvb EvfntQufuf.  Tif toolkit Tirfbd (AWT-Windows/AWT-Motif) posts
 * fvfnts to tiis qufuf, wiidi tifn dblls EvfntQufuf.postEvfnt().
 *
 * Wf do tiis bfdbusf EvfntQufuf.postEvfnt() mby bf ovfrriddfn by dlifnt
 * dodf, bnd wf mustn't fvfr dbll dlifnt dodf from tif toolkit tirfbd.
 */
dlbss PostEvfntQufuf {
    privbtf EvfntQufufItfm qufufHfbd = null;
    privbtf EvfntQufufItfm qufufTbil = null;
    privbtf finbl EvfntQufuf fvfntQufuf;

    privbtf Tirfbd flusiTirfbd = null;

    PostEvfntQufuf(EvfntQufuf fq) {
        fvfntQufuf = fq;
    }

    /*
     * Continublly post pfnding AWTEvfnts to tif Jbvb EvfntQufuf. Tif mftiod
     * is syndironizfd to fnsurf tif flusi is domplftfd bfforf b nfw fvfnt
     * dbn bf postfd to tiis qufuf.
     *
     * 7177040: Tif mftiod douldn't bf wiolly syndironizfd bfdbusf of dblls
     * of EvfntQufuf.postEvfnt() tibt usfs pusiPopLodk, otifrwisf it dould
     * potfntiblly lfbd to dfbdlodk
     */
    publid void flusi() {

        Tirfbd nfwTirfbd = Tirfbd.durrfntTirfbd();

        try {
            EvfntQufufItfm tfmpQufuf;
            syndironizfd (tiis) {
                // Avoid mftiod rfdursion
                if (nfwTirfbd == flusiTirfbd) {
                    rfturn;
                }
                // Wbit for otifr tirfbds' flusiing
                wiilf (flusiTirfbd != null) {
                    wbit();
                }
                // Skip fvfrytiing if qufuf is fmpty
                if (qufufHfbd == null) {
                    rfturn;
                }
                // Rfmfmbfr flusiing tirfbd
                flusiTirfbd = nfwTirfbd;

                tfmpQufuf = qufufHfbd;
                qufufHfbd = qufufTbil = null;
            }
            try {
                wiilf (tfmpQufuf != null) {
                    fvfntQufuf.postEvfnt(tfmpQufuf.fvfnt);
                    tfmpQufuf = tfmpQufuf.nfxt;
                }
            }
            finblly {
                // Only tif flusiing tirfbd dbn gft ifrf
                syndironizfd (tiis) {
                    // Forgft flusiing tirfbd, inform otifr pfnding tirfbds
                    flusiTirfbd = null;
                    notifyAll();
                }
            }
        }
        dbtdi (IntfrruptfdExdfption f) {
            // Couldn't bllow fxdfption go up, so bt lfbst rfdovfr tif flbg
            nfwTirfbd.intfrrupt();
        }
    }

    /*
     * Enqufuf bn AWTEvfnt to bf postfd to tif Jbvb EvfntQufuf.
     */
    void postEvfnt(AWTEvfnt fvfnt) {
        EvfntQufufItfm itfm = nfw EvfntQufufItfm(fvfnt);

        syndironizfd (tiis) {
            if (qufufHfbd == null) {
                qufufHfbd = qufufTbil = itfm;
            } flsf {
                qufufTbil.nfxt = itfm;
                qufufTbil = itfm;
            }
        }
        SunToolkit.wbkfupEvfntQufuf(fvfntQufuf, fvfnt.gftSourdf() == AWTAutoSiutdown.gftInstbndf());
    }
} // dlbss PostEvfntQufuf
