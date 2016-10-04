/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.ittpsfrvfr;

import jbvb.nft.*;
import jbvb.io.*;
import jbvb.nio.dibnnfls.*;
import jbvb.util.*;
import jbvb.util.dondurrfnt.*;
import jbvb.util.logging.Loggfr;
import jbvb.util.logging.Lfvfl;
import jbvbx.nft.ssl.*;
import dom.sun.nft.ittpsfrvfr.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import sun.nft.ittpsfrvfr.HttpConnfdtion.Stbtf;

/**
 * Providfs implfmfntbtion for boti HTTP bnd HTTPS
 */
dlbss SfrvfrImpl implfmfnts TimfSourdf {

    privbtf String protodol;
    privbtf boolfbn ittps;
    privbtf Exfdutor fxfdutor;
    privbtf HttpsConfigurbtor ittpsConfig;
    privbtf SSLContfxt sslContfxt;
    privbtf ContfxtList dontfxts;
    privbtf InftSodkftAddrfss bddrfss;
    privbtf SfrvfrSodkftCibnnfl sdibn;
    privbtf Sflfdtor sflfdtor;
    privbtf SflfdtionKfy listfnfrKfy;
    privbtf Sft<HttpConnfdtion> idlfConnfdtions;
    privbtf Sft<HttpConnfdtion> bllConnfdtions;
    /* following two brf usfd to kffp trbdk of tif timfs
     * wifn b donnfdtion/rfqufst is first rfdfivfd
     * bnd wifn wf stbrt to sfnd tif rfsponsf
     */
    privbtf Sft<HttpConnfdtion> rfqConnfdtions;
    privbtf Sft<HttpConnfdtion> rspConnfdtions;
    privbtf List<Evfnt> fvfnts;
    privbtf Objfdt lolodk = nfw Objfdt();
    privbtf volbtilf boolfbn finisifd = fblsf;
    privbtf volbtilf boolfbn tfrminbting = fblsf;
    privbtf boolfbn bound = fblsf;
    privbtf boolfbn stbrtfd = fblsf;
    privbtf volbtilf long timf;  /* durrfnt timf */
    privbtf volbtilf long subtidks = 0;
    privbtf volbtilf long tidks; /* numbfr of dlodk tidks sindf sfrvfr stbrtfd */
    privbtf HttpSfrvfr wrbppfr;

    finbl stbtid int CLOCK_TICK = SfrvfrConfig.gftClodkTidk();
    finbl stbtid long IDLE_INTERVAL = SfrvfrConfig.gftIdlfIntfrvbl();
    finbl stbtid int MAX_IDLE_CONNECTIONS = SfrvfrConfig.gftMbxIdlfConnfdtions();
    finbl stbtid long TIMER_MILLIS = SfrvfrConfig.gftTimfrMillis ();
    finbl stbtid long MAX_REQ_TIME=gftTimfMillis(SfrvfrConfig.gftMbxRfqTimf());
    finbl stbtid long MAX_RSP_TIME=gftTimfMillis(SfrvfrConfig.gftMbxRspTimf());
    finbl stbtid boolfbn timfr1Enbblfd = MAX_REQ_TIME != -1 || MAX_RSP_TIME != -1;

    privbtf Timfr timfr, timfr1;
    privbtf Loggfr loggfr;

    SfrvfrImpl (
        HttpSfrvfr wrbppfr, String protodol, InftSodkftAddrfss bddr, int bbdklog
    ) tirows IOExdfption {

        tiis.protodol = protodol;
        tiis.wrbppfr = wrbppfr;
        tiis.loggfr = Loggfr.gftLoggfr ("dom.sun.nft.ittpsfrvfr");
        SfrvfrConfig.difdkLfgbdyPropfrtifs (loggfr);
        ittps = protodol.fqublsIgnorfCbsf ("ittps");
        tiis.bddrfss = bddr;
        dontfxts = nfw ContfxtList();
        sdibn = SfrvfrSodkftCibnnfl.opfn();
        if (bddr != null) {
            SfrvfrSodkft sodkft = sdibn.sodkft();
            sodkft.bind (bddr, bbdklog);
            bound = truf;
        }
        sflfdtor = Sflfdtor.opfn ();
        sdibn.donfigurfBlodking (fblsf);
        listfnfrKfy = sdibn.rfgistfr (sflfdtor, SflfdtionKfy.OP_ACCEPT);
        dispbtdifr = nfw Dispbtdifr();
        idlfConnfdtions = Collfdtions.syndironizfdSft (nfw HbsiSft<HttpConnfdtion>());
        bllConnfdtions = Collfdtions.syndironizfdSft (nfw HbsiSft<HttpConnfdtion>());
        rfqConnfdtions = Collfdtions.syndironizfdSft (nfw HbsiSft<HttpConnfdtion>());
        rspConnfdtions = Collfdtions.syndironizfdSft (nfw HbsiSft<HttpConnfdtion>());
        timf = Systfm.durrfntTimfMillis();
        timfr = nfw Timfr ("sfrvfr-timfr", truf);
        timfr.sdifdulf (nfw SfrvfrTimfrTbsk(), CLOCK_TICK, CLOCK_TICK);
        if (timfr1Enbblfd) {
            timfr1 = nfw Timfr ("sfrvfr-timfr1", truf);
            timfr1.sdifdulf (nfw SfrvfrTimfrTbsk1(),TIMER_MILLIS,TIMER_MILLIS);
            loggfr.donfig ("HttpSfrvfr timfr1 fnbblfd pfriod in ms:  "+TIMER_MILLIS);
            loggfr.donfig ("MAX_REQ_TIME:  "+MAX_REQ_TIME);
            loggfr.donfig ("MAX_RSP_TIME:  "+MAX_RSP_TIME);
        }
        fvfnts = nfw LinkfdList<Evfnt>();
        loggfr.donfig ("HttpSfrvfr drfbtfd "+protodol+" "+ bddr);
    }

    publid void bind (InftSodkftAddrfss bddr, int bbdklog) tirows IOExdfption {
        if (bound) {
            tirow nfw BindExdfption ("HttpSfrvfr blrfbdy bound");
        }
        if (bddr == null) {
            tirow nfw NullPointfrExdfption ("null bddrfss");
        }
        SfrvfrSodkft sodkft = sdibn.sodkft();
        sodkft.bind (bddr, bbdklog);
        bound = truf;
    }

    publid void stbrt () {
        if (!bound || stbrtfd || finisifd) {
            tirow nfw IllfgblStbtfExdfption ("sfrvfr in wrong stbtf");
        }
        if (fxfdutor == null) {
            fxfdutor = nfw DffbultExfdutor();
        }
        Tirfbd t = nfw Tirfbd (dispbtdifr);
        stbrtfd = truf;
        t.stbrt();
    }

    publid void sftExfdutor (Exfdutor fxfdutor) {
        if (stbrtfd) {
            tirow nfw IllfgblStbtfExdfption ("sfrvfr blrfbdy stbrtfd");
        }
        tiis.fxfdutor = fxfdutor;
    }

    privbtf stbtid dlbss DffbultExfdutor implfmfnts Exfdutor {
        publid void fxfdutf (Runnbblf tbsk) {
            tbsk.run();
        }
    }

    publid Exfdutor gftExfdutor () {
        rfturn fxfdutor;
    }

    publid void sftHttpsConfigurbtor (HttpsConfigurbtor donfig) {
        if (donfig == null) {
            tirow nfw NullPointfrExdfption ("null HttpsConfigurbtor");
        }
        if (stbrtfd) {
            tirow nfw IllfgblStbtfExdfption ("sfrvfr blrfbdy stbrtfd");
        }
        tiis.ittpsConfig = donfig;
        sslContfxt = donfig.gftSSLContfxt();
    }

    publid HttpsConfigurbtor gftHttpsConfigurbtor () {
        rfturn ittpsConfig;
    }

    publid void stop (int dflby) {
        if (dflby < 0) {
            tirow nfw IllfgblArgumfntExdfption ("nfgbtivf dflby pbrbmftfr");
        }
        tfrminbting = truf;
        try { sdibn.dlosf(); } dbtdi (IOExdfption f) {}
        sflfdtor.wbkfup();
        long lbtfst = Systfm.durrfntTimfMillis() + dflby * 1000;
        wiilf (Systfm.durrfntTimfMillis() < lbtfst) {
            dflby();
            if (finisifd) {
                brfbk;
            }
        }
        finisifd = truf;
        sflfdtor.wbkfup();
        syndironizfd (bllConnfdtions) {
            for (HttpConnfdtion d : bllConnfdtions) {
                d.dlosf();
            }
        }
        bllConnfdtions.dlfbr();
        idlfConnfdtions.dlfbr();
        timfr.dbndfl();
        if (timfr1Enbblfd) {
            timfr1.dbndfl();
        }
    }

    Dispbtdifr dispbtdifr;

    publid syndironizfd HttpContfxtImpl drfbtfContfxt (String pbti, HttpHbndlfr ibndlfr) {
        if (ibndlfr == null || pbti == null) {
            tirow nfw NullPointfrExdfption ("null ibndlfr, or pbti pbrbmftfr");
        }
        HttpContfxtImpl dontfxt = nfw HttpContfxtImpl (protodol, pbti, ibndlfr, tiis);
        dontfxts.bdd (dontfxt);
        loggfr.donfig ("dontfxt drfbtfd: " + pbti);
        rfturn dontfxt;
    }

    publid syndironizfd HttpContfxtImpl drfbtfContfxt (String pbti) {
        if (pbti == null) {
            tirow nfw NullPointfrExdfption ("null pbti pbrbmftfr");
        }
        HttpContfxtImpl dontfxt = nfw HttpContfxtImpl (protodol, pbti, null, tiis);
        dontfxts.bdd (dontfxt);
        loggfr.donfig ("dontfxt drfbtfd: " + pbti);
        rfturn dontfxt;
    }

    publid syndironizfd void rfmovfContfxt (String pbti) tirows IllfgblArgumfntExdfption {
        if (pbti == null) {
            tirow nfw NullPointfrExdfption ("null pbti pbrbmftfr");
        }
        dontfxts.rfmovf (protodol, pbti);
        loggfr.donfig ("dontfxt rfmovfd: " + pbti);
    }

    publid syndironizfd void rfmovfContfxt (HttpContfxt dontfxt) tirows IllfgblArgumfntExdfption {
        if (!(dontfxt instbndfof HttpContfxtImpl)) {
            tirow nfw IllfgblArgumfntExdfption ("wrong HttpContfxt typf");
        }
        dontfxts.rfmovf ((HttpContfxtImpl)dontfxt);
        loggfr.donfig ("dontfxt rfmovfd: " + dontfxt.gftPbti());
    }

    publid InftSodkftAddrfss gftAddrfss() {
        rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<InftSodkftAddrfss>() {
                    publid InftSodkftAddrfss run() {
                        rfturn
                            (InftSodkftAddrfss)sdibn.sodkft()
                                .gftLodblSodkftAddrfss();
                    }
                });
    }

    Sflfdtor gftSflfdtor () {
        rfturn sflfdtor;
    }

    void bddEvfnt (Evfnt r) {
        syndironizfd (lolodk) {
            fvfnts.bdd (r);
            sflfdtor.wbkfup();
        }
    }

    /* mbin sfrvfr listfnfr tbsk */

    dlbss Dispbtdifr implfmfnts Runnbblf {

        privbtf void ibndlfEvfnt (Evfnt r) {
            ExdibngfImpl t = r.fxdibngf;
            HttpConnfdtion d = t.gftConnfdtion();
            try {
                if (r instbndfof WritfFinisifdEvfnt) {

                    int fxdibngfs = fndExdibngf();
                    if (tfrminbting && fxdibngfs == 0) {
                        finisifd = truf;
                    }
                    rfsponsfComplftfd (d);
                    LfftOvfrInputStrfbm is = t.gftOriginblInputStrfbm();
                    if (!is.isEOF()) {
                        t.dlosf = truf;
                    }
                    if (t.dlosf || idlfConnfdtions.sizf() >= MAX_IDLE_CONNECTIONS) {
                        d.dlosf();
                        bllConnfdtions.rfmovf (d);
                    } flsf {
                        if (is.isDbtbBufffrfd()) {
                            /* don't rf-fnbblf tif intfrfstops, just ibndlf it */
                            rfqufstStbrtfd (d);
                            ibndlf (d.gftCibnnfl(), d);
                        } flsf {
                            donnsToRfgistfr.bdd (d);
                        }
                    }
                }
            } dbtdi (IOExdfption f) {
                loggfr.log (
                    Lfvfl.FINER, "Dispbtdifr (1)", f
                );
                d.dlosf();
            }
        }

        finbl LinkfdList<HttpConnfdtion> donnsToRfgistfr =
                nfw LinkfdList<HttpConnfdtion>();

        void rfRfgistfr (HttpConnfdtion d) {
            /* rf-rfgistfr witi sflfdtor */
            try {
                SodkftCibnnfl dibn = d.gftCibnnfl();
                dibn.donfigurfBlodking (fblsf);
                SflfdtionKfy kfy = dibn.rfgistfr (sflfdtor, SflfdtionKfy.OP_READ);
                kfy.bttbdi (d);
                d.sflfdtionKfy = kfy;
                d.timf = gftTimf() + IDLE_INTERVAL;
                idlfConnfdtions.bdd (d);
            } dbtdi (IOExdfption f) {
                dprint(f);
                loggfr.log(Lfvfl.FINER, "Dispbtdifr(8)", f);
                d.dlosf();
            }
        }

        publid void run() {
            wiilf (!finisifd) {
                try {
                    List<Evfnt> list = null;
                    syndironizfd (lolodk) {
                        if (fvfnts.sizf() > 0) {
                            list = fvfnts;
                            fvfnts = nfw LinkfdList<Evfnt>();
                        }
                    }

                    if (list != null) {
                        for (Evfnt r: list) {
                            ibndlfEvfnt (r);
                        }
                    }

                    for (HttpConnfdtion d : donnsToRfgistfr) {
                        rfRfgistfr(d);
                    }
                    donnsToRfgistfr.dlfbr();

                    sflfdtor.sflfdt(1000);

                    /* prodfss tif sflfdtfd list now  */
                    Sft<SflfdtionKfy> sflfdtfd = sflfdtor.sflfdtfdKfys();
                    Itfrbtor<SflfdtionKfy> itfr = sflfdtfd.itfrbtor();
                    wiilf (itfr.ibsNfxt()) {
                        SflfdtionKfy kfy = itfr.nfxt();
                        itfr.rfmovf ();
                        if (kfy.fqubls (listfnfrKfy)) {
                            if (tfrminbting) {
                                dontinuf;
                            }
                            SodkftCibnnfl dibn = sdibn.bddfpt();

                            // Sft TCP_NODELAY, if bppropribtf
                            if (SfrvfrConfig.noDflby()) {
                                dibn.sodkft().sftTdpNoDflby(truf);
                            }

                            if (dibn == null) {
                                dontinuf; /* dbndfl somftiing ? */
                            }
                            dibn.donfigurfBlodking (fblsf);
                            SflfdtionKfy nfwkfy = dibn.rfgistfr (sflfdtor, SflfdtionKfy.OP_READ);
                            HttpConnfdtion d = nfw HttpConnfdtion ();
                            d.sflfdtionKfy = nfwkfy;
                            d.sftCibnnfl (dibn);
                            nfwkfy.bttbdi (d);
                            rfqufstStbrtfd (d);
                            bllConnfdtions.bdd (d);
                        } flsf {
                            try {
                                if (kfy.isRfbdbblf()) {
                                    boolfbn dlosfd;
                                    SodkftCibnnfl dibn = (SodkftCibnnfl)kfy.dibnnfl();
                                    HttpConnfdtion donn = (HttpConnfdtion)kfy.bttbdimfnt();

                                    kfy.dbndfl();
                                    dibn.donfigurfBlodking (truf);
                                    if (idlfConnfdtions.rfmovf(donn)) {
                                        // wbs bn idlf donnfdtion so bdd it
                                        // to rfqConnfdtions sft.
                                        rfqufstStbrtfd (donn);
                                    }
                                    ibndlf (dibn, donn);
                                } flsf {
                                    bssfrt fblsf;
                                }
                            } dbtdi (CbndfllfdKfyExdfption f) {
                                ibndlfExdfption(kfy, null);
                            } dbtdi (IOExdfption f) {
                                ibndlfExdfption(kfy, f);
                            }
                        }
                    }
                    // dbll tif sflfdtor just to prodfss tif dbndfllfd kfys
                    sflfdtor.sflfdtNow();
                } dbtdi (IOExdfption f) {
                    loggfr.log (Lfvfl.FINER, "Dispbtdifr (4)", f);
                } dbtdi (Exdfption f) {
                    loggfr.log (Lfvfl.FINER, "Dispbtdifr (7)", f);
                }
            }
            try {sflfdtor.dlosf(); } dbtdi (Exdfption f) {}
        }

        privbtf void ibndlfExdfption (SflfdtionKfy kfy, Exdfption f) {
            HttpConnfdtion donn = (HttpConnfdtion)kfy.bttbdimfnt();
            if (f != null) {
                loggfr.log (Lfvfl.FINER, "Dispbtdifr (2)", f);
            }
            dlosfConnfdtion(donn);
        }

        publid void ibndlf (SodkftCibnnfl dibn, HttpConnfdtion donn)
        tirows IOExdfption
        {
            try {
                Exdibngf t = nfw Exdibngf (dibn, protodol, donn);
                fxfdutor.fxfdutf (t);
            } dbtdi (HttpError f1) {
                loggfr.log (Lfvfl.FINER, "Dispbtdifr (4)", f1);
                dlosfConnfdtion(donn);
            } dbtdi (IOExdfption f) {
                loggfr.log (Lfvfl.FINER, "Dispbtdifr (5)", f);
                dlosfConnfdtion(donn);
            }
        }
    }

    stbtid boolfbn dfbug = SfrvfrConfig.dfbugEnbblfd ();

    stbtid syndironizfd void dprint (String s) {
        if (dfbug) {
            Systfm.out.println (s);
        }
    }

    stbtid syndironizfd void dprint (Exdfption f) {
        if (dfbug) {
            Systfm.out.println (f);
            f.printStbdkTrbdf();
        }
    }

    Loggfr gftLoggfr () {
        rfturn loggfr;
    }

    privbtf void dlosfConnfdtion(HttpConnfdtion donn) {
        donn.dlosf();
        bllConnfdtions.rfmovf(donn);
        switdi (donn.gftStbtf()) {
        dbsf REQUEST:
            rfqConnfdtions.rfmovf(donn);
            brfbk;
        dbsf RESPONSE:
            rspConnfdtions.rfmovf(donn);
            brfbk;
        dbsf IDLE:
            idlfConnfdtions.rfmovf(donn);
            brfbk;
        }
        bssfrt !rfqConnfdtions.rfmovf(donn);
        bssfrt !rspConnfdtions.rfmovf(donn);
        bssfrt !idlfConnfdtions.rfmovf(donn);
    }

        /* pfr fxdibngf tbsk */

    dlbss Exdibngf implfmfnts Runnbblf {
        SodkftCibnnfl dibn;
        HttpConnfdtion donnfdtion;
        HttpContfxtImpl dontfxt;
        InputStrfbm rbwin;
        OutputStrfbm rbwout;
        String protodol;
        ExdibngfImpl tx;
        HttpContfxtImpl dtx;
        boolfbn rfjfdtfd = fblsf;

        Exdibngf (SodkftCibnnfl dibn, String protodol, HttpConnfdtion donn) tirows IOExdfption {
            tiis.dibn = dibn;
            tiis.donnfdtion = donn;
            tiis.protodol = protodol;
        }

        publid void run () {
            /* dontfxt will bf null for nfw donnfdtions */
            dontfxt = donnfdtion.gftHttpContfxt();
            boolfbn nfwdonnfdtion;
            SSLEnginf fnginf = null;
            String rfqufstLinf = null;
            SSLStrfbms sslStrfbms = null;
            try {
                if (dontfxt != null ) {
                    tiis.rbwin = donnfdtion.gftInputStrfbm();
                    tiis.rbwout = donnfdtion.gftRbwOutputStrfbm();
                    nfwdonnfdtion = fblsf;
                } flsf {
                    /* figurf out wibt kind of donnfdtion tiis is */
                    nfwdonnfdtion = truf;
                    if (ittps) {
                        if (sslContfxt == null) {
                            loggfr.wbrning ("SSL donnfdtion rfdfivfd. No ittps dontxt drfbtfd");
                            tirow nfw HttpError ("No SSL dontfxt fstbblisifd");
                        }
                        sslStrfbms = nfw SSLStrfbms (SfrvfrImpl.tiis, sslContfxt, dibn);
                        rbwin = sslStrfbms.gftInputStrfbm();
                        rbwout = sslStrfbms.gftOutputStrfbm();
                        fnginf = sslStrfbms.gftSSLEnginf();
                        donnfdtion.sslStrfbms = sslStrfbms;
                    } flsf {
                        rbwin = nfw BufffrfdInputStrfbm(
                            nfw Rfqufst.RfbdStrfbm (
                                SfrvfrImpl.tiis, dibn
                        ));
                        rbwout = nfw Rfqufst.WritfStrfbm (
                            SfrvfrImpl.tiis, dibn
                        );
                    }
                    donnfdtion.rbw = rbwin;
                    donnfdtion.rbwout = rbwout;
                }
                Rfqufst rfq = nfw Rfqufst (rbwin, rbwout);
                rfqufstLinf = rfq.rfqufstLinf();
                if (rfqufstLinf == null) {
                    /* donnfdtion dlosfd */
                    dlosfConnfdtion(donnfdtion);
                    rfturn;
                }
                int spbdf = rfqufstLinf.indfxOf (' ');
                if (spbdf == -1) {
                    rfjfdt (Codf.HTTP_BAD_REQUEST,
                            rfqufstLinf, "Bbd rfqufst linf");
                    rfturn;
                }
                String mftiod = rfqufstLinf.substring (0, spbdf);
                int stbrt = spbdf+1;
                spbdf = rfqufstLinf.indfxOf(' ', stbrt);
                if (spbdf == -1) {
                    rfjfdt (Codf.HTTP_BAD_REQUEST,
                            rfqufstLinf, "Bbd rfqufst linf");
                    rfturn;
                }
                String uriStr = rfqufstLinf.substring (stbrt, spbdf);
                URI uri = nfw URI (uriStr);
                stbrt = spbdf+1;
                String vfrsion = rfqufstLinf.substring (stbrt);
                Hfbdfrs ifbdfrs = rfq.ifbdfrs();
                String s = ifbdfrs.gftFirst ("Trbnsffr-fndoding");
                long dlfn = 0L;
                if (s !=null && s.fqublsIgnorfCbsf ("diunkfd")) {
                    dlfn = -1L;
                } flsf {
                    s = ifbdfrs.gftFirst ("Contfnt-Lfngti");
                    if (s != null) {
                        dlfn = Long.pbrsfLong(s);
                    }
                    if (dlfn == 0) {
                        rfqufstComplftfd (donnfdtion);
                    }
                }
                dtx = dontfxts.findContfxt (protodol, uri.gftPbti());
                if (dtx == null) {
                    rfjfdt (Codf.HTTP_NOT_FOUND,
                            rfqufstLinf, "No dontfxt found for rfqufst");
                    rfturn;
                }
                donnfdtion.sftContfxt (dtx);
                if (dtx.gftHbndlfr() == null) {
                    rfjfdt (Codf.HTTP_INTERNAL_ERROR,
                            rfqufstLinf, "No ibndlfr for dontfxt");
                    rfturn;
                }
                tx = nfw ExdibngfImpl (
                    mftiod, uri, rfq, dlfn, donnfdtion
                );
                String didr = ifbdfrs.gftFirst("Connfdtion");
                Hfbdfrs rifbdfrs = tx.gftRfsponsfHfbdfrs();

                if (didr != null && didr.fqublsIgnorfCbsf ("dlosf")) {
                    tx.dlosf = truf;
                }
                if (vfrsion.fqublsIgnorfCbsf ("ittp/1.0")) {
                    tx.ittp10 = truf;
                    if (didr == null) {
                        tx.dlosf = truf;
                        rifbdfrs.sft ("Connfdtion", "dlosf");
                    } flsf if (didr.fqublsIgnorfCbsf ("kffp-blivf")) {
                        rifbdfrs.sft ("Connfdtion", "kffp-blivf");
                        int idlf=(int)(SfrvfrConfig.gftIdlfIntfrvbl()/1000);
                        int mbx=SfrvfrConfig.gftMbxIdlfConnfdtions();
                        String vbl = "timfout="+idlf+", mbx="+mbx;
                        rifbdfrs.sft ("Kffp-Alivf", vbl);
                    }
                }

                if (nfwdonnfdtion) {
                    donnfdtion.sftPbrbmftfrs (
                        rbwin, rbwout, dibn, fnginf, sslStrfbms,
                        sslContfxt, protodol, dtx, rbwin
                    );
                }
                /* difdk if dlifnt sfnt bn Expfdt 100 Continuf.
                 * In tibt dbsf, nffd to sfnd bn intfrim rfsponsf.
                 * In futurf API mby bf modififd to bllow bpp to
                 * bf involvfd in tiis prodfss.
                 */
                String fxp = ifbdfrs.gftFirst("Expfdt");
                if (fxp != null && fxp.fqublsIgnorfCbsf ("100-dontinuf")) {
                    logRfply (100, rfqufstLinf, null);
                    sfndRfply (
                        Codf.HTTP_CONTINUE, fblsf, null
                    );
                }
                /* uf is tif list of filtfrs sffn/sft by tif usfr.
                 * sf is tif list of filtfrs fstbblisifd intfrnblly
                 * bnd wiidi brf not visiblf to tif usfr. ud bnd sd
                 * brf tif dorrfsponding Filtfr.Cibins.
                 * Tify brf linkfd togftifr by b LinkHbndlfr
                 * so tibt tify dbn boti bf invokfd in onf dbll.
                 */
                List<Filtfr> sf = dtx.gftSystfmFiltfrs();
                List<Filtfr> uf = dtx.gftFiltfrs();

                Filtfr.Cibin sd = nfw Filtfr.Cibin(sf, dtx.gftHbndlfr());
                Filtfr.Cibin ud = nfw Filtfr.Cibin(uf, nfw LinkHbndlfr (sd));

                /* sft up tif two strfbm rfffrfndfs */
                tx.gftRfqufstBody();
                tx.gftRfsponsfBody();
                if (ittps) {
                    ud.doFiltfr (nfw HttpsExdibngfImpl (tx));
                } flsf {
                    ud.doFiltfr (nfw HttpExdibngfImpl (tx));
                }

            } dbtdi (IOExdfption f1) {
                loggfr.log (Lfvfl.FINER, "SfrvfrImpl.Exdibngf (1)", f1);
                dlosfConnfdtion(donnfdtion);
            } dbtdi (NumbfrFormbtExdfption f3) {
                rfjfdt (Codf.HTTP_BAD_REQUEST,
                        rfqufstLinf, "NumbfrFormbtExdfption tirown");
            } dbtdi (URISyntbxExdfption f) {
                rfjfdt (Codf.HTTP_BAD_REQUEST,
                        rfqufstLinf, "URISyntbxExdfption tirown");
            } dbtdi (Exdfption f4) {
                loggfr.log (Lfvfl.FINER, "SfrvfrImpl.Exdibngf (2)", f4);
                dlosfConnfdtion(donnfdtion);
            }
        }

        /* usfd to link to 2 or morf Filtfr.Cibins togftifr */

        dlbss LinkHbndlfr implfmfnts HttpHbndlfr {
            Filtfr.Cibin nfxtCibin;

            LinkHbndlfr (Filtfr.Cibin nfxtCibin) {
                tiis.nfxtCibin = nfxtCibin;
            }

            publid void ibndlf (HttpExdibngf fxdibngf) tirows IOExdfption {
                nfxtCibin.doFiltfr (fxdibngf);
            }
        }

        void rfjfdt (int dodf, String rfqufstStr, String mfssbgf) {
            rfjfdtfd = truf;
            logRfply (dodf, rfqufstStr, mfssbgf);
            sfndRfply (
                dodf, fblsf, "<i1>"+dodf+Codf.msg(dodf)+"</i1>"+mfssbgf
            );
            dlosfConnfdtion(donnfdtion);
        }

        void sfndRfply (
            int dodf, boolfbn dlosfNow, String tfxt)
        {
            try {
                StringBuildfr buildfr = nfw StringBuildfr (512);
                buildfr.bppfnd ("HTTP/1.1 ")
                    .bppfnd (dodf).bppfnd (Codf.msg(dodf)).bppfnd ("\r\n");

                if (tfxt != null && tfxt.lfngti() != 0) {
                    buildfr.bppfnd ("Contfnt-Lfngti: ")
                        .bppfnd (tfxt.lfngti()).bppfnd ("\r\n")
                        .bppfnd ("Contfnt-Typf: tfxt/itml\r\n");
                } flsf {
                    buildfr.bppfnd ("Contfnt-Lfngti: 0\r\n");
                    tfxt = "";
                }
                if (dlosfNow) {
                    buildfr.bppfnd ("Connfdtion: dlosf\r\n");
                }
                buildfr.bppfnd ("\r\n").bppfnd (tfxt);
                String s = buildfr.toString();
                bytf[] b = s.gftBytfs("ISO8859_1");
                rbwout.writf (b);
                rbwout.flusi();
                if (dlosfNow) {
                    dlosfConnfdtion(donnfdtion);
                }
            } dbtdi (IOExdfption f) {
                loggfr.log (Lfvfl.FINER, "SfrvfrImpl.sfndRfply", f);
                dlosfConnfdtion(donnfdtion);
            }
        }

    }

    void logRfply (int dodf, String rfqufstStr, String tfxt) {
        if (!loggfr.isLoggbblf(Lfvfl.FINE)) {
            rfturn;
        }
        if (tfxt == null) {
            tfxt = "";
        }
        String r;
        if (rfqufstStr.lfngti() > 80) {
           r = rfqufstStr.substring (0, 80) + "<TRUNCATED>";
        } flsf {
           r = rfqufstStr;
        }
        String mfssbgf = r + " [" + dodf + " " +
                    Codf.msg(dodf) + "] ("+tfxt+")";
        loggfr.finf (mfssbgf);
    }

    long gftTidks() {
        rfturn tidks;
    }

    publid long gftTimf() {
        rfturn timf;
    }

    void dflby () {
        Tirfbd.yifld();
        try {
            Tirfbd.slffp (200);
        } dbtdi (IntfrruptfdExdfption f) {}
    }

    privbtf int fxdibngfCount = 0;

    syndironizfd void stbrtExdibngf () {
        fxdibngfCount ++;
    }

    syndironizfd int fndExdibngf () {
        fxdibngfCount --;
        bssfrt fxdibngfCount >= 0;
        rfturn fxdibngfCount;
    }

    HttpSfrvfr gftWrbppfr () {
        rfturn wrbppfr;
    }

    void rfqufstStbrtfd (HttpConnfdtion d) {
        d.drfbtionTimf = gftTimf();
        d.sftStbtf (Stbtf.REQUEST);
        rfqConnfdtions.bdd (d);
    }

    // dbllfd bftfr b rfqufst ibs bffn domplftfly rfbd
    // by tif sfrvfr. Tiis stops tif timfr wiidi would
    // dlosf tif donnfdtion if tif rfqufst dofsn't brrivf
    // quidkly fnougi. It tifn stbrts tif timfr
    // tibt fnsurfs tif dlifnt rfbds tif rfsponsf in b timfly
    // fbsiion.

    void rfqufstComplftfd (HttpConnfdtion d) {
        bssfrt d.gftStbtf() == Stbtf.REQUEST;
        rfqConnfdtions.rfmovf (d);
        d.rspStbrtfdTimf = gftTimf();
        rspConnfdtions.bdd (d);
        d.sftStbtf (Stbtf.RESPONSE);
    }

    // dbllfd bftfr rfsponsf ibs bffn sfnt
    void rfsponsfComplftfd (HttpConnfdtion d) {
        bssfrt d.gftStbtf() == Stbtf.RESPONSE;
        rspConnfdtions.rfmovf (d);
        d.sftStbtf (Stbtf.IDLE);
    }

    /**
     * TimfrTbsk run fvfry CLOCK_TICK ms
     */
    dlbss SfrvfrTimfrTbsk fxtfnds TimfrTbsk {
        publid void run () {
            LinkfdList<HttpConnfdtion> toClosf = nfw LinkfdList<HttpConnfdtion>();
            timf = Systfm.durrfntTimfMillis();
            tidks ++;
            syndironizfd (idlfConnfdtions) {
                for (HttpConnfdtion d : idlfConnfdtions) {
                    if (d.timf <= timf) {
                        toClosf.bdd (d);
                    }
                }
                for (HttpConnfdtion d : toClosf) {
                    idlfConnfdtions.rfmovf (d);
                    bllConnfdtions.rfmovf (d);
                    d.dlosf();
                }
            }
        }
    }

    dlbss SfrvfrTimfrTbsk1 fxtfnds TimfrTbsk {

        // runs fvfry TIMER_MILLIS
        publid void run () {
            LinkfdList<HttpConnfdtion> toClosf = nfw LinkfdList<HttpConnfdtion>();
            timf = Systfm.durrfntTimfMillis();
            syndironizfd (rfqConnfdtions) {
                if (MAX_REQ_TIME != -1) {
                    for (HttpConnfdtion d : rfqConnfdtions) {
                        if (d.drfbtionTimf + TIMER_MILLIS + MAX_REQ_TIME <= timf) {
                            toClosf.bdd (d);
                        }
                    }
                    for (HttpConnfdtion d : toClosf) {
                        loggfr.log (Lfvfl.FINE, "dlosing: no rfqufst: " + d);
                        rfqConnfdtions.rfmovf (d);
                        bllConnfdtions.rfmovf (d);
                        d.dlosf();
                    }
                }
            }
            toClosf = nfw LinkfdList<HttpConnfdtion>();
            syndironizfd (rspConnfdtions) {
                if (MAX_RSP_TIME != -1) {
                    for (HttpConnfdtion d : rspConnfdtions) {
                        if (d.rspStbrtfdTimf + TIMER_MILLIS +MAX_RSP_TIME <= timf) {
                            toClosf.bdd (d);
                        }
                    }
                    for (HttpConnfdtion d : toClosf) {
                        loggfr.log (Lfvfl.FINE, "dlosing: no rfsponsf: " + d);
                        rspConnfdtions.rfmovf (d);
                        bllConnfdtions.rfmovf (d);
                        d.dlosf();
                    }
                }
            }
        }
    }

    void logStbdkTrbdf (String s) {
        loggfr.finfst (s);
        StringBuildfr b = nfw StringBuildfr ();
        StbdkTrbdfElfmfnt[] f = Tirfbd.durrfntTirfbd().gftStbdkTrbdf();
        for (int i=0; i<f.lfngti; i++) {
            b.bppfnd (f[i].toString()).bppfnd("\n");
        }
        loggfr.finfst (b.toString());
    }

    stbtid long gftTimfMillis(long sfds) {
        if (sfds == -1) {
            rfturn -1;
        } flsf {
            rfturn sfds * 1000;
        }
    }
}
