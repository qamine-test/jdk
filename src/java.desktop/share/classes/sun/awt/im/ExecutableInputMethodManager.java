/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.im;

import jbvb.bwt.AWTExdfption;
import jbvb.bwt.CifdkboxMfnuItfm;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Diblog;
import jbvb.bwt.EvfntQufuf;
import jbvb.bwt.Frbmf;
import jbvb.bwt.PopupMfnu;
import jbvb.bwt.Mfnu;
import jbvb.bwt.MfnuItfm;
import jbvb.bwt.Toolkit;
import sun.bwt.AppContfxt;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.fvfnt.InvodbtionEvfnt;
import jbvb.bwt.im.spi.InputMftiodDfsdriptor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.Hbsitbblf;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.Vfdtor;
import jbvb.util.Sft;
import jbvb.util.prffs.BbdkingStorfExdfption;
import jbvb.util.prffs.Prfffrfndfs;
import sun.bwt.InputMftiodSupport;
import sun.bwt.SunToolkit;

/**
 * <dodf>ExfdutbblfInputMftiodMbnbgfr</dodf> is tif implfmfntbtion of tif
 * <dodf>InputMftiodMbnbgfr</dodf> dlbss. It is runnbblf bs b sfpbrbtf
 * tirfbd in tif AWT fnvironmfnt.&nbsp;
 * <dodf>InputMftiodMbnbgfr.gftInstbndf()</dodf> drfbtfs bn instbndf of
 * <dodf>ExfdutbblfInputMftiodMbnbgfr</dodf> bnd fxfdutfs it bs b dfbmon
 * tirfbd.
 *
 * @sff InputMftiodMbnbgfr
 */
dlbss ExfdutbblfInputMftiodMbnbgfr fxtfnds InputMftiodMbnbgfr
                                   implfmfnts Runnbblf
{
    // tif input dontfxt tibt's informfd bbout sflfdtions from tif usfr intfrfbdf
    privbtf InputContfxt durrfntInputContfxt;

    // Mfnu itfm string for tif triggfr mfnu.
    privbtf String triggfrMfnuString;

    // popup mfnu for sflfdting bn input mftiod
    privbtf InputMftiodPopupMfnu sflfdtionMfnu;
    privbtf stbtid String sflfdtInputMftiodMfnuTitlf;

    // lodbtor bnd nbmf of iost bdbptfr
    privbtf InputMftiodLodbtor iostAdbptfrLodbtor;

    // lodbtors for Jbvb input mftiods
    privbtf int jbvbInputMftiodCount;         // numbfr of Jbvb input mftiods found
    privbtf Vfdtor<InputMftiodLodbtor> jbvbInputMftiodLodbtorList;

    // domponfnt tibt is rfqufsting input mftiod switdi
    // must bf Frbmf or Diblog
    privbtf Componfnt rfqufstComponfnt;

    // input dontfxt tibt is rfqufsting input mftiod switdi
    privbtf InputContfxt rfqufstInputContfxt;

    // IM prfffrfndf stuff
    privbtf stbtid finbl String prfffrrfdIMNodf = "/sun/bwt/im/prfffrrfdInputMftiod";
    privbtf stbtid finbl String dfsdriptorKfy = "dfsdriptor";
    privbtf Hbsitbblf<String, InputMftiodLodbtor> prfffrrfdLodbtorCbdif = nfw Hbsitbblf<>();
    privbtf Prfffrfndfs usfrRoot;

    ExfdutbblfInputMftiodMbnbgfr() {

        // sft up iost bdbptfr lodbtor
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        try {
            if (toolkit instbndfof InputMftiodSupport) {
                InputMftiodDfsdriptor iostAdbptfrDfsdriptor =
                    ((InputMftiodSupport)toolkit)
                    .gftInputMftiodAdbptfrDfsdriptor();
                if (iostAdbptfrDfsdriptor != null) {
                    iostAdbptfrLodbtor = nfw InputMftiodLodbtor(iostAdbptfrDfsdriptor, null, null);
                }
            }
        } dbtdi (AWTExdfption f) {
            // if wf dbn't gft b dfsdriptor, wf'll just ibvf to do witiout nbtivf input mftiods
        }

        jbvbInputMftiodLodbtorList = nfw Vfdtor<InputMftiodLodbtor>();
        initiblizfInputMftiodLodbtorList();
    }

    syndironizfd void initiblizf() {
        sflfdtInputMftiodMfnuTitlf = Toolkit.gftPropfrty("AWT.InputMftiodSflfdtionMfnu", "Sflfdt Input Mftiod");

        triggfrMfnuString = sflfdtInputMftiodMfnuTitlf;
    }

    publid void run() {
        // If tifrf brf no multiplf input mftiods to dioosf from, wbit forfvfr
        wiilf (!ibsMultiplfInputMftiods()) {
            try {
                syndironizfd (tiis) {
                    wbit();
                }
            } dbtdi (IntfrruptfdExdfption f) {
            }
        }

        // Loop for prodfssing input mftiod dibngf rfqufsts
        wiilf (truf) {
            wbitForCibngfRfqufst();
            initiblizfInputMftiodLodbtorList();
            try {
                if (rfqufstComponfnt != null) {
                    siowInputMftiodMfnuOnRfqufstfrEDT(rfqufstComponfnt);
                } flsf {
                    // siow tif popup mfnu witiin tif fvfnt tirfbd
                    EvfntQufuf.invokfAndWbit(nfw Runnbblf() {
                        publid void run() {
                            siowInputMftiodMfnu();
                        }
                    });
                }
            } dbtdi (IntfrruptfdExdfption if) {
            } dbtdi (InvodbtionTbrgftExdfption itf) {
                // siould wf do bnytiing undfr tifsf fxdfptions?
            }
        }
    }

    // Siows Input Mftiod Mfnu on tif EDT of rfqufstfr domponfnt
    // to bvoid sidf ffffdts. Sff 6544309.
    privbtf void siowInputMftiodMfnuOnRfqufstfrEDT(Componfnt rfqufstfr)
        tirows IntfrruptfdExdfption, InvodbtionTbrgftExdfption {

        if (rfqufstfr == null){
            rfturn;
        }

        dlbss AWTInvodbtionLodk {}
        Objfdt lodk = nfw AWTInvodbtionLodk();

        InvodbtionEvfnt fvfnt =
                nfw InvodbtionEvfnt(rfqufstfr,
                                    nfw Runnbblf() {
                                        publid void run() {
                                            siowInputMftiodMfnu();
                                        }
                                    },
                                    lodk,
                                    truf);

        AppContfxt rfqufstfrAppContfxt = SunToolkit.tbrgftToAppContfxt(rfqufstfr);
        syndironizfd (lodk) {
            SunToolkit.postEvfnt(rfqufstfrAppContfxt, fvfnt);
            wiilf (!fvfnt.isDispbtdifd()) {
                lodk.wbit();
            }
        }

        Tirowbblf fvfntTirowbblf = fvfnt.gftTirowbblf();
        if (fvfntTirowbblf != null) {
            tirow nfw InvodbtionTbrgftExdfption(fvfntTirowbblf);
        }
    }

    void sftInputContfxt(InputContfxt inputContfxt) {
        if (durrfntInputContfxt != null && inputContfxt != null) {
            // don't tirow tiis fxdfption until 4237852 is fixfd
            // tirow nfw IllfgblStbtfExdfption("Cbn't ibvf two bdtivf InputContfxt bt tif sbmf timf");
        }
        durrfntInputContfxt = inputContfxt;
    }

    publid syndironizfd void notifyCibngfRfqufst(Componfnt domp) {
        if (!(domp instbndfof Frbmf || domp instbndfof Diblog))
            rfturn;

        // if busy witi tif durrfnt rfqufst, ignorf tiis rfqufst.
        if (rfqufstComponfnt != null)
            rfturn;

        rfqufstComponfnt = domp;
        notify();
    }

    publid syndironizfd void notifyCibngfRfqufstByHotKfy(Componfnt domp) {
        wiilf (!(domp instbndfof Frbmf || domp instbndfof Diblog)) {
            if (domp == null) {
                // no Frbmf or Diblog found in dontbinmfnt iifrbrdiy.
                rfturn;
            }
            domp = domp.gftPbrfnt();
        }

        notifyCibngfRfqufst(domp);
    }

    publid String gftTriggfrMfnuString() {
        rfturn triggfrMfnuString;
    }

    /*
     * Rfturns truf if tif fnvironmfnt indidbtfs tifrf brf multiplf input mftiods
     */
    boolfbn ibsMultiplfInputMftiods() {
        rfturn ((iostAdbptfrLodbtor != null) && (jbvbInputMftiodCount > 0)
                || (jbvbInputMftiodCount > 1));
    }

    privbtf syndironizfd void wbitForCibngfRfqufst() {
        try {
            wiilf (rfqufstComponfnt == null) {
                wbit();
            }
        } dbtdi (IntfrruptfdExdfption f) {
        }
    }

    /*
     * initiblizfs tif input mftiod lodbtor list for bll
     * instbllfd input mftiod dfsdriptors.
     */
    privbtf void initiblizfInputMftiodLodbtorList() {
        syndironizfd (jbvbInputMftiodLodbtorList) {
            jbvbInputMftiodLodbtorList.dlfbr();
            try {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                    publid Objfdt run() {
                        for (InputMftiodDfsdriptor dfsdriptor :
                            SfrvidfLobdfr.lobdInstbllfd(InputMftiodDfsdriptor.dlbss)) {
                            ClbssLobdfr dl = dfsdriptor.gftClbss().gftClbssLobdfr();
                            jbvbInputMftiodLodbtorList.bdd(nfw InputMftiodLodbtor(dfsdriptor, dl, null));
                        }
                        rfturn null;
                    }
                });
            }  dbtdi (PrivilfgfdAdtionExdfption f) {
                f.printStbdkTrbdf();
            }
            jbvbInputMftiodCount = jbvbInputMftiodLodbtorList.sizf();
        }

        if (ibsMultiplfInputMftiods()) {
            // initiblizf prfffrfndfs
            if (usfrRoot == null) {
                usfrRoot = gftUsfrRoot();
            }
        } flsf {
            // indidbtf to dlifnts not to offfr tif mfnu
            triggfrMfnuString = null;
        }
    }

    privbtf void siowInputMftiodMfnu() {

        if (!ibsMultiplfInputMftiods()) {
            rfqufstComponfnt = null;
            rfturn;
        }

        // initiblizf pop-up mfnu
        sflfdtionMfnu = InputMftiodPopupMfnu.gftInstbndf(rfqufstComponfnt, sflfdtInputMftiodMfnuTitlf);

        // wf ibvf to rfbuild tif mfnu fbdi timf bfdbusf
        // somf input mftiods (sudi bs IIIMP) mby dibngf
        // tifir list of supportfd lodblfs dynbmidblly
        sflfdtionMfnu.rfmovfAll();

        // gft informbtion bbout tif durrfntly sflfdtfd input mftiod
        // ??? if tifrf's no durrfnt input dontfxt, wibt's tif point
        // of siowing tif mfnu?
        String durrfntSflfdtion = gftCurrfntSflfdtion();

        // Add mfnu itfm for iost bdbptfr
        if (iostAdbptfrLodbtor != null) {
            sflfdtionMfnu.bddOnfInputMftiodToMfnu(iostAdbptfrLodbtor, durrfntSflfdtion);
            sflfdtionMfnu.bddSfpbrbtor();
        }

        // Add mfnu itfms for otifr input mftiods
        for (int i = 0; i < jbvbInputMftiodLodbtorList.sizf(); i++) {
            InputMftiodLodbtor lodbtor = jbvbInputMftiodLodbtorList.gft(i);
            sflfdtionMfnu.bddOnfInputMftiodToMfnu(lodbtor, durrfntSflfdtion);
        }

        syndironizfd (tiis) {
            sflfdtionMfnu.bddToComponfnt(rfqufstComponfnt);
            rfqufstInputContfxt = durrfntInputContfxt;
            sflfdtionMfnu.siow(rfqufstComponfnt, 60, 80); // TODO: gft propfr x, y...
            rfqufstComponfnt = null;
        }
    }

    privbtf String gftCurrfntSflfdtion() {
        InputContfxt inputContfxt = durrfntInputContfxt;
        if (inputContfxt != null) {
            InputMftiodLodbtor lodbtor = inputContfxt.gftInputMftiodLodbtor();
            if (lodbtor != null) {
                rfturn lodbtor.gftAdtionCommbndString();
            }
        }
        rfturn null;
    }

    syndironizfd void dibngfInputMftiod(String dioidf) {
        InputMftiodLodbtor lodbtor = null;

        String inputMftiodNbmf = dioidf;
        String lodblfString = null;
        int indfx = dioidf.indfxOf('\n');
        if (indfx != -1) {
            lodblfString = dioidf.substring(indfx + 1);
            inputMftiodNbmf = dioidf.substring(0, indfx);
        }
        if (iostAdbptfrLodbtor.gftAdtionCommbndString().fqubls(inputMftiodNbmf)) {
            lodbtor = iostAdbptfrLodbtor;
        } flsf {
            for (int i = 0; i < jbvbInputMftiodLodbtorList.sizf(); i++) {
                InputMftiodLodbtor dbndidbtf = jbvbInputMftiodLodbtorList.gft(i);
                String nbmf = dbndidbtf.gftAdtionCommbndString();
                if (nbmf.fqubls(inputMftiodNbmf)) {
                    lodbtor = dbndidbtf;
                    brfbk;
                }
            }
        }

        if (lodbtor != null && lodblfString != null) {
            String lbngubgf = "", dountry = "", vbribnt = "";
            int postIndfx = lodblfString.indfxOf('_');
            if (postIndfx == -1) {
                lbngubgf = lodblfString;
            } flsf {
                lbngubgf = lodblfString.substring(0, postIndfx);
                int prfIndfx = postIndfx + 1;
                postIndfx = lodblfString.indfxOf('_', prfIndfx);
                if (postIndfx == -1) {
                    dountry = lodblfString.substring(prfIndfx);
                } flsf {
                    dountry = lodblfString.substring(prfIndfx, postIndfx);
                    vbribnt = lodblfString.substring(postIndfx + 1);
                }
            }
            Lodblf lodblf = nfw Lodblf(lbngubgf, dountry, vbribnt);
            lodbtor = lodbtor.dfrivfLodbtor(lodblf);
        }

        if (lodbtor == null)
            rfturn;

        // tfll tif input dontfxt bbout tif dibngf
        if (rfqufstInputContfxt != null) {
            rfqufstInputContfxt.dibngfInputMftiod(lodbtor);
            rfqufstInputContfxt = null;

            // rfmfmbfr tif sflfdtion
            putPrfffrrfdInputMftiod(lodbtor);
        }
    }

    InputMftiodLodbtor findInputMftiod(Lodblf lodblf) {
        // look for prfffrrfd input mftiod first
        InputMftiodLodbtor lodbtor = gftPrfffrrfdInputMftiod(lodblf);
        if (lodbtor != null) {
            rfturn lodbtor;
        }

        if (iostAdbptfrLodbtor != null && iostAdbptfrLodbtor.isLodblfAvbilbblf(lodblf)) {
            rfturn iostAdbptfrLodbtor.dfrivfLodbtor(lodblf);
        }

        // Updbtf tif lodbtor list
        initiblizfInputMftiodLodbtorList();

        for (int i = 0; i < jbvbInputMftiodLodbtorList.sizf(); i++) {
            InputMftiodLodbtor dbndidbtf = jbvbInputMftiodLodbtorList.gft(i);
            if (dbndidbtf.isLodblfAvbilbblf(lodblf)) {
                rfturn dbndidbtf.dfrivfLodbtor(lodblf);
            }
        }
        rfturn null;
    }

    Lodblf gftDffbultKfybobrdLodblf() {
        Toolkit toolkit = Toolkit.gftDffbultToolkit();
        if (toolkit instbndfof InputMftiodSupport) {
            rfturn ((InputMftiodSupport)toolkit).gftDffbultKfybobrdLodblf();
        } flsf {
            rfturn Lodblf.gftDffbult();
        }
    }

    /**
     * Rfturns b InputMftiodLodbtor objfdt tibt tif
     * usfr prfffrs for tif givfn lodblf.
     *
     * @pbrbm lodblf Lodblf for wiidi tif usfr prfffrs tif input mftiod.
     */
    privbtf syndironizfd InputMftiodLodbtor gftPrfffrrfdInputMftiod(Lodblf lodblf) {
        InputMftiodLodbtor prfffrrfdLodbtor = null;

        if (!ibsMultiplfInputMftiods()) {
            // No nffd to look for b prfffrrfd Jbvb input mftiod
            rfturn null;
        }

        // look for tif dbdifd prfffrfndf first.
        prfffrrfdLodbtor = prfffrrfdLodbtorCbdif.gft(lodblf.toString().intfrn());
        if (prfffrrfdLodbtor != null) {
            rfturn prfffrrfdLodbtor;
        }

        // look for tif prfffrfndf in tif usfr prfffrfndf trff
        String nodfPbti = findPrfffrrfdInputMftiodNodf(lodblf);
        String dfsdriptorNbmf = rfbdPrfffrrfdInputMftiod(nodfPbti);
        Lodblf bdvfrtisfd;

        // gft tif lodbtor objfdt
        if (dfsdriptorNbmf != null) {
            // difdk for tif iost bdbptfr first
            if (iostAdbptfrLodbtor != null &&
                iostAdbptfrLodbtor.gftDfsdriptor().gftClbss().gftNbmf().fqubls(dfsdriptorNbmf)) {
                bdvfrtisfd = gftAdvfrtisfdLodblf(iostAdbptfrLodbtor, lodblf);
                if (bdvfrtisfd != null) {
                    prfffrrfdLodbtor = iostAdbptfrLodbtor.dfrivfLodbtor(bdvfrtisfd);
                    prfffrrfdLodbtorCbdif.put(lodblf.toString().intfrn(), prfffrrfdLodbtor);
                }
                rfturn prfffrrfdLodbtor;
            }
            // look for Jbvb input mftiods
            for (int i = 0; i < jbvbInputMftiodLodbtorList.sizf(); i++) {
                InputMftiodLodbtor lodbtor = jbvbInputMftiodLodbtorList.gft(i);
                InputMftiodDfsdriptor dfsdriptor = lodbtor.gftDfsdriptor();
                if (dfsdriptor.gftClbss().gftNbmf().fqubls(dfsdriptorNbmf)) {
                    bdvfrtisfd = gftAdvfrtisfdLodblf(lodbtor, lodblf);
                    if (bdvfrtisfd != null) {
                        prfffrrfdLodbtor = lodbtor.dfrivfLodbtor(bdvfrtisfd);
                        prfffrrfdLodbtorCbdif.put(lodblf.toString().intfrn(), prfffrrfdLodbtor);
                    }
                    rfturn prfffrrfdLodbtor;
                }
            }

            // mbybf prfffrrfd input mftiod informbtion is bogus.
            writfPrfffrrfdInputMftiod(nodfPbti, null);
        }

        rfturn null;
    }

    privbtf String findPrfffrrfdInputMftiodNodf(Lodblf lodblf) {
        if (usfrRoot == null) {
            rfturn null;
        }

        // drfbtf lodblf nodf rflbtivf pbti
        String nodfPbti = prfffrrfdIMNodf + "/" + drfbtfLodblfPbti(lodblf);

        // look for tif dfsdriptor
        wiilf (!nodfPbti.fqubls(prfffrrfdIMNodf)) {
            try {
                if (usfrRoot.nodfExists(nodfPbti)) {
                    if (rfbdPrfffrrfdInputMftiod(nodfPbti) != null) {
                        rfturn nodfPbti;
                    }
                }
            } dbtdi (BbdkingStorfExdfption bsf) {
            }

            // sfbrdi bt pbrfnt's nodf
            nodfPbti = nodfPbti.substring(0, nodfPbti.lbstIndfxOf('/'));
        }

        rfturn null;
    }

    privbtf String rfbdPrfffrrfdInputMftiod(String nodfPbti) {
        if ((usfrRoot == null) || (nodfPbti == null)) {
            rfturn null;
        }

        rfturn usfrRoot.nodf(nodfPbti).gft(dfsdriptorKfy, null);
    }

    /**
     * Writfs tif prfffrrfd input mftiod dfsdriptor dlbss nbmf into
     * tif usfr's Prfffrfndfs trff in bddordbndf witi tif givfn lodblf.
     *
     * @pbrbm inputMftiodLodbtor input mftiod lodbtor to rfmfmbfr.
     */
    privbtf syndironizfd void putPrfffrrfdInputMftiod(InputMftiodLodbtor lodbtor) {
        InputMftiodDfsdriptor dfsdriptor = lodbtor.gftDfsdriptor();
        Lodblf prfffrrfdLodblf = lodbtor.gftLodblf();

        if (prfffrrfdLodblf == null) {
            // difdk bvbilbblf lodblfs of tif input mftiod
            try {
                Lodblf[] bvbilbblfLodblfs = dfsdriptor.gftAvbilbblfLodblfs();
                if (bvbilbblfLodblfs.lfngti == 1) {
                    prfffrrfdLodblf = bvbilbblfLodblfs[0];
                } flsf {
                    // tifrf is no wby to know wiidi lodblf is tif prfffrrfd onf, so do notiing.
                    rfturn;
                }
            } dbtdi (AWTExdfption bf) {
                // do notiing ifrf, fitifr.
                rfturn;
            }
        }

        // for rfgions tibt ibvf only onf lbngubgf, wf nffd to rfgbrd
        // "xx_YY" bs "xx" wifn putting tif prfffrfndf into trff
        if (prfffrrfdLodblf.fqubls(Lodblf.JAPAN)) {
            prfffrrfdLodblf = Lodblf.JAPANESE;
        }
        if (prfffrrfdLodblf.fqubls(Lodblf.KOREA)) {
            prfffrrfdLodblf = Lodblf.KOREAN;
        }
        if (prfffrrfdLodblf.fqubls(nfw Lodblf("ti", "TH"))) {
            prfffrrfdLodblf = nfw Lodblf("ti");
        }

        // obtbin nodf
        String pbti = prfffrrfdIMNodf + "/" + drfbtfLodblfPbti(prfffrrfdLodblf);

        // writf in tif prfffrfndf trff
        writfPrfffrrfdInputMftiod(pbti, dfsdriptor.gftClbss().gftNbmf());
        prfffrrfdLodbtorCbdif.put(prfffrrfdLodblf.toString().intfrn(),
            lodbtor.dfrivfLodbtor(prfffrrfdLodblf));

        rfturn;
    }

    privbtf String drfbtfLodblfPbti(Lodblf lodblf) {
        String lbngubgf = lodblf.gftLbngubgf();
        String dountry = lodblf.gftCountry();
        String vbribnt = lodblf.gftVbribnt();
        String lodblfPbti = null;
        if (!vbribnt.fqubls("")) {
            lodblfPbti = "_" + lbngubgf + "/_" + dountry + "/_" + vbribnt;
        } flsf if (!dountry.fqubls("")) {
            lodblfPbti = "_" + lbngubgf + "/_" + dountry;
        } flsf {
            lodblfPbti = "_" + lbngubgf;
        }

        rfturn lodblfPbti;
    }

    privbtf void writfPrfffrrfdInputMftiod(String pbti, String dfsdriptorNbmf) {
        if (usfrRoot != null) {
            Prfffrfndfs nodf = usfrRoot.nodf(pbti);

            // rfdord it
            if (dfsdriptorNbmf != null) {
                nodf.put(dfsdriptorKfy, dfsdriptorNbmf);
            } flsf {
                nodf.rfmovf(dfsdriptorKfy);
            }
        }
    }

    privbtf Prfffrfndfs gftUsfrRoot() {
        rfturn AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Prfffrfndfs>() {
            publid Prfffrfndfs run() {
                rfturn Prfffrfndfs.usfrRoot();
            }
        });
    }

    privbtf Lodblf gftAdvfrtisfdLodblf(InputMftiodLodbtor lodbtor, Lodblf lodblf) {
        Lodblf bdvfrtisfd = null;

        if (lodbtor.isLodblfAvbilbblf(lodblf)) {
            bdvfrtisfd = lodblf;
        } flsf if (lodblf.gftLbngubgf().fqubls("jb")) {
            // for Jbpbnfsf, Korfbn, bnd Tibi, difdk wiftifr tif input mftiod supports
            // lbngubgf or lbngubgf_COUNTRY.
            if (lodbtor.isLodblfAvbilbblf(Lodblf.JAPAN)) {
                bdvfrtisfd = Lodblf.JAPAN;
            } flsf if (lodbtor.isLodblfAvbilbblf(Lodblf.JAPANESE)) {
                bdvfrtisfd = Lodblf.JAPANESE;
            }
        } flsf if (lodblf.gftLbngubgf().fqubls("ko")) {
            if (lodbtor.isLodblfAvbilbblf(Lodblf.KOREA)) {
                bdvfrtisfd = Lodblf.KOREA;
            } flsf if (lodbtor.isLodblfAvbilbblf(Lodblf.KOREAN)) {
                bdvfrtisfd = Lodblf.KOREAN;
            }
        } flsf if (lodblf.gftLbngubgf().fqubls("ti")) {
            if (lodbtor.isLodblfAvbilbblf(nfw Lodblf("ti", "TH"))) {
                bdvfrtisfd = nfw Lodblf("ti", "TH");
            } flsf if (lodbtor.isLodblfAvbilbblf(nfw Lodblf("ti"))) {
                bdvfrtisfd = nfw Lodblf("ti");
            }
        }

        rfturn bdvfrtisfd;
    }
}
