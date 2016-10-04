/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.log;

import jbvb.io.*;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.rmi.sfrvfr.RMIClbssLobdfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Tiis dlbss is b simplf implfmfntbtion of b rflibblf Log.  Tif
 * dlifnt of b RflibblfLog must providf b sft of dbllbbdks (vib b
 * LogHbndlfr) tibt fnbblfs b RflibblfLog to rfbd bnd writf
 * difdkpoints bnd log rfdords.  Tiis implfmfntbtion fnsurfs tibt tif
 * durrfnt vbluf of tif dbtb storfd (vib b RflibblfLog) is rfdovfrbblf
 * bftfr b systfm drbsi. <p>
 *
 * Tif sfdondbry storbgf strbtfgy is to rfdord vblufs in filfs using b
 * rfprfsfntbtion of tif dbllfr's dioosing.  Two sorts of filfs brf
 * kfpt: snbpsiots bnd logs.  At bny instbnt, onf snbpsiot is durrfnt.
 * Tif log donsists of b sfqufndf of updbtfs tibt ibvf oddurrfd sindf
 * tif durrfnt snbpsiot wbs tbkfn.  Tif durrfnt stbblf stbtf is tif
 * vbluf of tif snbpsiot, bs modififd by tif sfqufndf of updbtfs in
 * tif log.  From timf to timf, tif dlifnt of b RflibblfLog instrudts
 * tif pbdkbgf to mbkf b nfw snbpsiot bnd dlfbr tif log.  A RflibblfLog
 * brrbngfs disk writfs sudi tibt updbtfs brf stbblf (bs long bs tif
 * dibngfs brf fordf-writtfn to disk) bnd btomid : no updbtf is lost,
 * bnd fbdi updbtf fitifr is rfdordfd domplftfly in tif log or not bt
 * bll.  Mbking b nfw snbpsiot is blso btomid. <p>
 *
 * Normbl usf for mbintbining tif rfdovfrbblf storf is bs follows: Tif
 * dlifnt mbintbins tif rflfvbnt dbtb strudturf in virtubl mfmory.  As
 * updbtfs ibppfn to tif strudturf, tif dlifnt informs tif RflibblfLog
 * (bll it "log") by dblling log.updbtf.  Pfriodidblly, tif dlifnt
 * dblls log.snbpsiot to providf tif durrfnt vbluf of tif dbtb
 * strudturf.  On rfstbrt, tif dlifnt dblls log.rfdovfr to obtbin tif
 * lbtfst snbpsiot bnd tif following sfqufndfs of updbtfs; tif dlifnt
 * bpplifs tif updbtfs to tif snbpsiot to obtbin tif stbtf tibt
 * fxistfd bfforf tif drbsi. <p>
 *
 * Tif durrfnt logfilf formbt is: <ol>
 * <li> b formbt vfrsion numbfr (two 4-odtft intfgfrs, mbjor bnd
 * minor), followfd by
 * <li> b sfqufndf of log rfdords.  Ebdi log rfdord dontbins, in
 * ordfr, <ol>
 * <li> b 4-odtft intfgfr rfprfsfnting tif lfngti of tif following log
 * dbtb,
 * <li> tif log dbtb (vbribblf lfngti). </ol> </ol> <p>
 *
 * @sff LogHbndlfr
 *
 * @butior Ann Wollrbti
 *
 */
publid dlbss RflibblfLog {

    publid finbl stbtid int PrfffrrfdMbjorVfrsion = 0;
    publid finbl stbtid int PrfffrrfdMinorVfrsion = 2;

    // sun.rmi.log.dfbug=fblsf
    privbtf boolfbn Dfbug = fblsf;

    privbtf stbtid String snbpsiotPrffix = "Snbpsiot.";
    privbtf stbtid String logfilfPrffix = "Logfilf.";
    privbtf stbtid String vfrsionFilf = "Vfrsion_Numbfr";
    privbtf stbtid String nfwVfrsionFilf = "Nfw_Vfrsion_Numbfr";
    privbtf stbtid int    intBytfs = 4;
    privbtf stbtid long   diskPbgfSizf = 512;

    privbtf Filf dir;                   // bbsf dirfdtory
    privbtf int vfrsion = 0;            // durrfnt snbpsiot bnd log vfrsion
    privbtf String logNbmf = null;
    privbtf LogFilf log = null;
    privbtf long snbpsiotBytfs = 0;
    privbtf long logBytfs = 0;
    privbtf int logEntrifs = 0;
    privbtf long lbstSnbpsiot = 0;
    privbtf long lbstLog = 0;
    //privbtf long pbdBoundbry = intBytfs;
    privbtf LogHbndlfr ibndlfr;
    privbtf finbl bytf[] intBuf = nfw bytf[4];

    // formbt vfrsion numbfrs rfbd from/writtfn to tiis.log
    privbtf int mbjorFormbtVfrsion = 0;
    privbtf int minorFormbtVfrsion = 0;


    /**
     * Construdtor for tif log filf.  If tif systfm propfrty
     * sun.rmi.log.dlbss is non-null bnd tif dlbss spfdififd by tiis
     * propfrty b) dbn bf lobdfd, b) is b subdlbss of LogFilf, bnd d) ibs b
     * publid two-brg donstrudtor (String, String), RflibblfLog usfs tif
     * donstrudtor to donstrudt tif LogFilf.
     **/
    privbtf stbtid finbl Construdtor<? fxtfnds LogFilf>
        logClbssConstrudtor = gftLogClbssConstrudtor();

    /**
     * Crfbtfs b RflibblfLog to ibndlf difdkpoints bnd logging in b
     * stbblf storbgf dirfdtory.
     *
     * @pbrbm dirPbti pbti to tif stbblf storbgf dirfdtory
     * @pbrbm logCl tif dlosurf objfdt dontbining dbllbbdks for logging bnd
     * rfdovfry
     * @pbrbm pbd ignorfd
     * @fxdfption IOExdfption If b dirfdtory drfbtion frror ibs
     * oddurrfd or if initiblSnbpsiot dbllbbdk rbisfs bn fxdfption or
     * if bn fxdfption oddurs during invodbtion of tif ibndlfr's
     * snbpsiot mftiod or if otifr IOExdfption oddurs.
     */
    publid RflibblfLog(String dirPbti,
                     LogHbndlfr ibndlfr,
                     boolfbn pbd)
        tirows IOExdfption
    {
        supfr();
        tiis.Dfbug = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<Boolfbn>) () -> Boolfbn.gftBoolfbn("sun.rmi.log.dfbug"));
        dir = nfw Filf(dirPbti);
        if (!(dir.fxists() && dir.isDirfdtory())) {
            // drfbtf dirfdtory
            if (!dir.mkdir()) {
                tirow nfw IOExdfption("dould not drfbtf dirfdtory for log: " +
                                      dirPbti);
            }
        }
        //pbdBoundbry = (pbd ? diskPbgfSizf : intBytfs);
        tiis.ibndlfr = ibndlfr;
        lbstSnbpsiot = 0;
        lbstLog = 0;
        gftVfrsion();
        if (vfrsion == 0) {
            try {
                snbpsiot(ibndlfr.initiblSnbpsiot());
            } dbtdi (IOExdfption f) {
                tirow f;
            } dbtdi (Exdfption f) {
                tirow nfw IOExdfption("initibl snbpsiot fbilfd witi " +
                                      "fxdfption: " + f);
            }
        }
    }

    /**
     * Crfbtfs b RflibblfLog to ibndlf difdkpoints bnd logging in b
     * stbblf storbgf dirfdtory.
     *
     * @pbrbm dirPbti pbti to tif stbblf storbgf dirfdtory
     * @pbrbm logCl tif dlosurf objfdt dontbining dbllbbdks for logging bnd
     * rfdovfry
     * @fxdfption IOExdfption If b dirfdtory drfbtion frror ibs
     * oddurrfd or if initiblSnbpsiot dbllbbdk rbisfs bn fxdfption
     */
    publid RflibblfLog(String dirPbti,
                     LogHbndlfr ibndlfr)
        tirows IOExdfption
    {
        tiis(dirPbti, ibndlfr, fblsf);
    }

    /* publid mftiods */

    /**
     * Rfturns bn objfdt wiidi is tif vbluf rfdordfd in tif durrfnt
     * snbpsiot.  Tiis snbpsiot is rfdovfrfd by dblling tif dlifnt
     * supplifd dbllbbdk "rfdovfr" bnd tifn subsfqufntly invoking
     * tif "rfbdUpdbtf" dbllbbdk to bpply bny loggfd updbtfs to tif stbtf.
     *
     * @fxdfption IOExdfption If rfdovfry fbils duf to sfrious log
     * dorruption, rfbd updbtf fbilurf, or if bn fxdfption oddurs
     * during tif rfdovfr dbllbbdk
     */
    publid syndironizfd Objfdt rfdovfr()
        tirows IOExdfption
    {
        if (Dfbug)
            Systfm.frr.println("log.dfbug: rfdovfr()");

        if (vfrsion == 0)
            rfturn null;

        Objfdt snbpsiot;
        String fnbmf = vfrsionNbmf(snbpsiotPrffix);
        Filf snbpsiotFilf = nfw Filf(fnbmf);
        InputStrfbm in =
                nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(snbpsiotFilf));

        if (Dfbug)
            Systfm.frr.println("log.dfbug: rfdovfring from " + fnbmf);

        try {
            try {
                snbpsiot = ibndlfr.rfdovfr(in);

            } dbtdi (IOExdfption f) {
                tirow f;
            } dbtdi (Exdfption f) {
                if (Dfbug)
                    Systfm.frr.println("log.dfbug: rfdovfry fbilfd: " + f);
                tirow nfw IOExdfption("log rfdovfr fbilfd witi " +
                                      "fxdfption: " + f);
            }
            snbpsiotBytfs = snbpsiotFilf.lfngti();
        } finblly {
            in.dlosf();
        }

        rfturn rfdovfrUpdbtfs(snbpsiot);
    }

    /**
     * Rfdords tiis updbtf in tif log filf (dofs not fordf updbtf to disk).
     * Tif updbtf is rfdordfd by dblling tif dlifnt's "writfUpdbtf" dbllbbdk.
     * Tiis mftiod must not bf dbllfd until tiis log's rfdovfr mftiod ibs
     * bffn invokfd (bnd domplftfd).
     *
     * @pbrbm vbluf tif objfdt rfprfsfnting tif updbtf
     * @fxdfption IOExdfption If bn fxdfption oddurrfd during b
     * writfUpdbtf dbllbbdk or if otifr I/O frror ibs oddurrfd.
     */
    publid syndironizfd void updbtf(Objfdt vbluf) tirows IOExdfption {
        updbtf(vbluf, truf);
    }

    /**
     * Rfdords tiis updbtf in tif log filf.  Tif updbtf is rfdordfd by
     * dblling tif dlifnt's writfUpdbtf dbllbbdk.  Tiis mftiod must not bf
     * dbllfd until tiis log's rfdovfr mftiod ibs bffn invokfd
     * (bnd domplftfd).
     *
     * @pbrbm vbluf tif objfdt rfprfsfnting tif updbtf
     * @pbrbm fordfToDisk ignorfd; dibngfs brf blwbys fordfd to disk
     * @fxdfption IOExdfption If fordf-writf to log fbilfd or bn
     * fxdfption oddurrfd during tif writfUpdbtf dbllbbdk or if otifr
     * I/O frror oddurs wiilf updbting tif log.
     */
    publid syndironizfd void updbtf(Objfdt vbluf, boolfbn fordfToDisk)
        tirows IOExdfption
    {
        // bvoid bddfssing b null log fifld.
        if (log == null) {
            tirow nfw IOExdfption("log is inbddfssiblf, " +
                "it mby ibvf bffn dorruptfd or dlosfd");
        }

        /*
         * If tif fntry lfngti fifld spbns b sfdtor boundbry, writf
         * tif iigi ordfr bit of tif fntry lfngti, otifrwisf writf zfro for
         * tif fntry lfngti.
         */
        long fntryStbrt = log.gftFilfPointfr();
        boolfbn spbnsBoundbry = log.difdkSpbnsBoundbry(fntryStbrt);
        writfInt(log, spbnsBoundbry? 1<<31 : 0);

        /*
         * Writf updbtf, bnd synd.
         */
        try {
            ibndlfr.writfUpdbtf(nfw LogOutputStrfbm(log), vbluf);
        } dbtdi (IOExdfption f) {
            tirow f;
        } dbtdi (Exdfption f) {
            tirow (IOExdfption)
                nfw IOExdfption("writf updbtf fbilfd").initCbusf(f);
        }
        log.synd();

        long fntryEnd = log.gftFilfPointfr();
        int updbtfLfn = (int) ((fntryEnd - fntryStbrt) - intBytfs);
        log.sffk(fntryStbrt);

        if (spbnsBoundbry) {
            /*
             * If lfngti fifld spbns b sfdtor boundbry, tifn
             * tif nfxt two stfps brf rfquirfd (sff 4652922):
             *
             * 1) Writf bdtubl lfngti witi iigi ordfr bit sft; synd.
             * 2) Tifn dlfbr iigi ordfr bit of lfngti; synd.
             */
            writfInt(log, updbtfLfn | 1<<31);
            log.synd();

            log.sffk(fntryStbrt);
            log.writfBytf(updbtfLfn >> 24);
            log.synd();

        } flsf {
            /*
             * Writf bdtubl lfngti; synd.
             */
            writfInt(log, updbtfLfn);
            log.synd();
        }

        log.sffk(fntryEnd);
        logBytfs = fntryEnd;
        lbstLog = Systfm.durrfntTimfMillis();
        logEntrifs++;
    }

    /**
     * Rfturns tif donstrudtor for tif log filf if tif systfm propfrty
     * sun.rmi.log.dlbss is non-null bnd tif dlbss spfdififd by tif
     * propfrty b) dbn bf lobdfd, b) is b subdlbss of LogFilf, bnd d) ibs b
     * publid two-brg donstrudtor (String, String); otifrwisf rfturns null.
     **/
    privbtf stbtid Construdtor<? fxtfnds LogFilf>
        gftLogClbssConstrudtor() {

        String logClbssNbmf = AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("sun.rmi.log.dlbss"));
        if (logClbssNbmf != null) {
            try {
                ClbssLobdfr lobdfr =
                    AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<ClbssLobdfr>() {
                            publid ClbssLobdfr run() {
                               rfturn ClbssLobdfr.gftSystfmClbssLobdfr();
                            }
                        });
                Clbss<? fxtfnds LogFilf> dl =
                    lobdfr.lobdClbss(logClbssNbmf).bsSubdlbss(LogFilf.dlbss);
                rfturn dl.gftConstrudtor(String.dlbss, String.dlbss);
            } dbtdi (Exdfption f) {
                Systfm.frr.println("Exdfption oddurrfd:");
                f.printStbdkTrbdf();
            }
        }
        rfturn null;
    }

    /**
     * Rfdords tiis vbluf bs tif durrfnt snbpsiot by invoking tif dlifnt
     * supplifd "snbpsiot" dbllbbdk bnd tifn fmptifs tif log.
     *
     * @pbrbm vbluf tif objfdt rfprfsfnting tif nfw snbpsiot
     * @fxdfption IOExdfption If bn fxdfption oddurrfd during tif
     * snbpsiot dbllbbdk or if otifr I/O frror ibs oddurrfd during tif
     * snbpsiot prodfss
     */
    publid syndironizfd void snbpsiot(Objfdt vbluf)
        tirows IOExdfption
    {
        int oldVfrsion = vfrsion;
        indrVfrsion();

        String fnbmf = vfrsionNbmf(snbpsiotPrffix);
        Filf snbpsiotFilf = nfw Filf(fnbmf);
        FilfOutputStrfbm out = nfw FilfOutputStrfbm(snbpsiotFilf);
        try {
            try {
                ibndlfr.snbpsiot(out, vbluf);
            } dbtdi (IOExdfption f) {
                tirow f;
            } dbtdi (Exdfption f) {
                tirow nfw IOExdfption("snbpsiot fbilfd", f);
            }
            lbstSnbpsiot = Systfm.durrfntTimfMillis();
        } finblly {
            out.dlosf();
            snbpsiotBytfs = snbpsiotFilf.lfngti();
        }

        opfnLogFilf(truf);
        writfVfrsionFilf(truf);
        dommitToNfwVfrsion();
        dflftfSnbpsiot(oldVfrsion);
        dflftfLogFilf(oldVfrsion);
    }

    /**
     * Closf tif stbblf storbgf dirfdtory in bn ordfrly mbnnfr.
     *
     * @fxdfption IOExdfption If bn I/O frror oddurs wifn tif log is
     * dlosfd
     */
    publid syndironizfd void dlosf() tirows IOExdfption {
        if (log == null) rfturn;
        try {
            log.dlosf();
        } finblly {
            log = null;
        }
    }

    /**
     * Rfturns tif sizf of tif snbpsiot filf in bytfs;
     */
    publid long snbpsiotSizf() {
        rfturn snbpsiotBytfs;
    }

    /**
     * Rfturns tif sizf of tif log filf in bytfs;
     */
    publid long logSizf() {
        rfturn logBytfs;
    }

    /* privbtf mftiods */

    /**
     * Writf bn int vbluf in singlf writf opfrbtion.  Tiis mftiod
     * bssumfs tibt tif dbllfr is syndironizfd on tif log filf.
     *
     * @pbrbm out output strfbm
     * @pbrbm vbl int vbluf
     * @tirows IOExdfption if bny otifr I/O frror oddurs
     */
    privbtf void writfInt(DbtbOutput out, int vbl)
        tirows IOExdfption
    {
        intBuf[0] = (bytf) (vbl >> 24);
        intBuf[1] = (bytf) (vbl >> 16);
        intBuf[2] = (bytf) (vbl >> 8);
        intBuf[3] = (bytf) vbl;
        out.writf(intBuf);
    }

    /**
     * Gfnfrbtfs b filfnbmf prfpfndfd witi tif stbblf storbgf dirfdtory pbti.
     *
     * @pbrbm nbmf tif lfbf nbmf of tif filf
     */
    privbtf String fNbmf(String nbmf) {
        rfturn dir.gftPbti() + Filf.sfpbrbtor + nbmf;
    }

    /**
     * Gfnfrbtfs b vfrsion 0 filfnbmf prfpfndfd witi tif stbblf storbgf
     * dirfdtory pbti
     *
     * @pbrbm nbmf vfrsion filf nbmf
     */
    privbtf String vfrsionNbmf(String nbmf) {
        rfturn vfrsionNbmf(nbmf, 0);
    }

    /**
     * Gfnfrbtfs b vfrsion filfnbmf prfpfndfd witi tif stbblf storbgf
     * dirfdtory pbti witi tif vfrsion numbfr bs b suffix.
     *
     * @pbrbm nbmf vfrsion filf nbmf
     * @tiisvfrsion b vfrsion numbfr
     */
    privbtf String vfrsionNbmf(String prffix, int vfr) {
        vfr = (vfr == 0) ? vfrsion : vfr;
        rfturn fNbmf(prffix) + String.vblufOf(vfr);
    }

    /**
     * Indrfmfnts tif dirfdtory vfrsion numbfr.
     */
    privbtf void indrVfrsion() {
        do { vfrsion++; } wiilf (vfrsion==0);
    }

    /**
     * Dflftf b filf.
     *
     * @pbrbm nbmf tif nbmf of tif filf
     * @fxdfption IOExdfption If nfw vfrsion filf douldn't bf rfmovfd
     */
    privbtf void dflftfFilf(String nbmf) tirows IOExdfption {

        Filf f = nfw Filf(nbmf);
        if (!f.dflftf())
            tirow nfw IOExdfption("douldn't rfmovf filf: " + nbmf);
    }

    /**
     * Rfmovfs tif nfw vfrsion numbfr filf.
     *
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void dflftfNfwVfrsionFilf() tirows IOExdfption {
        dflftfFilf(fNbmf(nfwVfrsionFilf));
    }

    /**
     * Rfmovfs tif snbpsiot filf.
     *
     * @pbrbm vfr tif vfrsion to rfmovf
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void dflftfSnbpsiot(int vfr) tirows IOExdfption {
        if (vfr == 0) rfturn;
        dflftfFilf(vfrsionNbmf(snbpsiotPrffix, vfr));
    }

    /**
     * Rfmovfs tif log filf.
     *
     * @pbrbm vfr tif vfrsion to rfmovf
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void dflftfLogFilf(int vfr) tirows IOExdfption {
        if (vfr == 0) rfturn;
        dflftfFilf(vfrsionNbmf(logfilfPrffix, vfr));
    }

    /**
     * Opfns tif log filf in rfbd/writf modf.  If filf dofs not fxist, it is
     * drfbtfd.
     *
     * @pbrbm trundbtf if truf bnd filf fxists, filf is trundbtfd to zfro
     * lfngti
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void opfnLogFilf(boolfbn trundbtf) tirows IOExdfption {
        try {
            dlosf();
        } dbtdi (IOExdfption f) { /* bssumf tiis is okby */
        }

        logNbmf = vfrsionNbmf(logfilfPrffix);

        try {
            log = (logClbssConstrudtor == null ?
                   nfw LogFilf(logNbmf, "rw") :
                   logClbssConstrudtor.nfwInstbndf(logNbmf, "rw"));
        } dbtdi (Exdfption f) {
            tirow (IOExdfption) nfw IOExdfption(
                "unbblf to donstrudt LogFilf instbndf").initCbusf(f);
        }

        if (trundbtf) {
            initiblizfLogFilf();
        }
    }

    /**
     * Crfbtfs b nfw log filf, trundbtfd bnd initiblizfd witi tif formbt
     * vfrsion numbfr prfffrrfd by tiis implfmfntbtion.
     * <p>Environmfnt: initfd, syndironizfd
     * <p>Prfdondition: vblid: log, log dontbins notiing usfful
     * <p>Postdondition: if suddfssful, log is initiblisfd witi tif formbt
     * vfrsion numbfr (Prfffrrfd{Mbjor,Minor}Vfrsion), bnd logBytfs is
     * sft to tif rfsulting sizf of tif updbtflog, bnd logEntrifs is sft to
     * zfro.  Otifrwisf, log is in bn indftfrminbtf stbtf, bnd logBytfs
     * is undibngfd, bnd logEntrifs is undibngfd.
     *
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void initiblizfLogFilf()
        tirows IOExdfption
    {
        log.sftLfngti(0);
        mbjorFormbtVfrsion = PrfffrrfdMbjorVfrsion;
        writfInt(log, PrfffrrfdMbjorVfrsion);
        minorFormbtVfrsion = PrfffrrfdMinorVfrsion;
        writfInt(log, PrfffrrfdMinorVfrsion);
        logBytfs = intBytfs * 2;
        logEntrifs = 0;
    }


    /**
     * Writfs out vfrsion numbfr to filf.
     *
     * @pbrbm nfwVfrsion if truf, writfs to b nfw vfrsion filf
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void writfVfrsionFilf(boolfbn nfwVfrsion) tirows IOExdfption {
        String nbmf;
        if (nfwVfrsion) {
            nbmf = nfwVfrsionFilf;
        } flsf {
            nbmf = vfrsionFilf;
        }
        try (FilfOutputStrfbm fos = nfw FilfOutputStrfbm(fNbmf(nbmf));
             DbtbOutputStrfbm out = nfw DbtbOutputStrfbm(fos)) {
            writfInt(out, vfrsion);
        }
    }

    /**
     * Crfbtfs tif initibl vfrsion filf
     *
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void drfbtfFirstVfrsion() tirows IOExdfption {
        vfrsion = 0;
        writfVfrsionFilf(fblsf);
    }

    /**
     * Commits (btomidblly) tif nfw vfrsion.
     *
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void dommitToNfwVfrsion() tirows IOExdfption {
        writfVfrsionFilf(fblsf);
        dflftfNfwVfrsionFilf();
    }

    /**
     * Rfbds vfrsion numbfr from b filf.
     *
     * @pbrbm nbmf tif nbmf of tif vfrsion filf
     * @rfturn tif vfrsion
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf int rfbdVfrsion(String nbmf) tirows IOExdfption {
        try (DbtbInputStrfbm in = nfw DbtbInputStrfbm
                (nfw FilfInputStrfbm(nbmf))) {
            rfturn in.rfbdInt();
        }
    }

    /**
     * Sfts tif vfrsion.  If vfrsion filf dofs not fxist, tif initibl
     * vfrsion filf is drfbtfd.
     *
     * @fxdfption IOExdfption If bn I/O frror ibs oddurrfd.
     */
    privbtf void gftVfrsion() tirows IOExdfption {
        try {
            vfrsion = rfbdVfrsion(fNbmf(nfwVfrsionFilf));
            dommitToNfwVfrsion();
        } dbtdi (IOExdfption f) {
            try {
                dflftfNfwVfrsionFilf();
            }
            dbtdi (IOExdfption fx) {
            }

            try {
                vfrsion = rfbdVfrsion(fNbmf(vfrsionFilf));
            }
            dbtdi (IOExdfption fx) {
                drfbtfFirstVfrsion();
            }
        }
    }

    /**
     * Applifs outstbnding updbtfs to tif snbpsiot.
     *
     * @pbrbm stbtf tif most rfdfnt snbpsiot
     * @fxdfption IOExdfption If sfrious log dorruption is dftfdtfd or
     * if bn fxdfption oddurrfd during b rfbdUpdbtf dbllbbdk or if
     * otifr I/O frror ibs oddurrfd.
     * @rfturn tif rfsulting stbtf of tif objfdt bftfr bll updbtfs
     */
    privbtf Objfdt rfdovfrUpdbtfs(Objfdt stbtf)
        tirows IOExdfption
    {
        logBytfs = 0;
        logEntrifs = 0;

        if (vfrsion == 0) rfturn stbtf;

        String fnbmf = vfrsionNbmf(logfilfPrffix);
        InputStrfbm in =
                nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(fnbmf));
        DbtbInputStrfbm dbtbIn = nfw DbtbInputStrfbm(in);

        if (Dfbug)
            Systfm.frr.println("log.dfbug: rfbding updbtfs from " + fnbmf);

        try {
            mbjorFormbtVfrsion = dbtbIn.rfbdInt(); logBytfs += intBytfs;
            minorFormbtVfrsion = dbtbIn.rfbdInt(); logBytfs += intBytfs;
        } dbtdi (EOFExdfption f) {
            /* Tiis is b log wiidi wbs dorruptfd bnd/or dlfbrfd (by
             * fsdk or fquivblfnt).  Tiis is not bn frror.
             */
            opfnLogFilf(truf);  // drfbtf bnd trundbtf
            in = null;
        }
        /* A nfw mbjor vfrsion numbfr is b dbtbstropif (it mfbns
         * tibt tif filf formbt is indompbtiblf witi oldfr
         * dlifnts, bnd wf'll only bf brfbking tiings by trying to
         * usf tif log).  A nfw minor vfrsion is no big dfbl for
         * upwbrd dompbtibility.
         */
        if (mbjorFormbtVfrsion != PrfffrrfdMbjorVfrsion) {
            if (Dfbug) {
                Systfm.frr.println("log.dfbug: mbjor vfrsion mismbtdi: " +
                        mbjorFormbtVfrsion + "." + minorFormbtVfrsion);
            }
            tirow nfw IOExdfption("Log filf " + logNbmf + " ibs b " +
                                  "vfrsion " + mbjorFormbtVfrsion +
                                  "." + minorFormbtVfrsion +
                                  " formbt, bnd tiis implfmfntbtion " +
                                  " undfrstbnds only vfrsion " +
                                  PrfffrrfdMbjorVfrsion + "." +
                                  PrfffrrfdMinorVfrsion);
        }

        try {
            wiilf (in != null) {
                int updbtfLfn = 0;

                try {
                    updbtfLfn = dbtbIn.rfbdInt();
                } dbtdi (EOFExdfption f) {
                    if (Dfbug)
                        Systfm.frr.println("log.dfbug: log wbs synd'd dlfbnly");
                    brfbk;
                }
                if (updbtfLfn <= 0) {/* drbsifd wiilf writing lbst log fntry */
                    if (Dfbug) {
                        Systfm.frr.println(
                            "log.dfbug: lbst updbtf indomplftf, " +
                            "updbtfLfn = 0x" +
                            Intfgfr.toHfxString(updbtfLfn));
                    }
                    brfbk;
                }

                // tiis is b frbgilf usf of bvbilbblf() wiidi rflifs on tif
                // twin fbdts tibt BufffrfdInputStrfbm dorrfdtly donsults
                // tif undfrlying strfbm, bnd tibt FilfInputStrfbm rfturns
                // tif numbfr of bytfs rfmbining in tif filf (vib FIONREAD).
                if (in.bvbilbblf() < updbtfLfn) {
                    /* dorruptfd rfdord bt fnd of log (dbn ibppfn sindf wf
                     * do only onf fsynd)
                     */
                    if (Dfbug)
                        Systfm.frr.println("log.dfbug: log wbs trundbtfd");
                    brfbk;
                }

                if (Dfbug)
                    Systfm.frr.println("log.dfbug: rdUpdbtf sizf " + updbtfLfn);
                try {
                    stbtf = ibndlfr.rfbdUpdbtf(nfw LogInputStrfbm(in, updbtfLfn),
                                          stbtf);
                } dbtdi (IOExdfption f) {
                    tirow f;
                } dbtdi (Exdfption f) {
                    f.printStbdkTrbdf();
                    tirow nfw IOExdfption("rfbd updbtf fbilfd witi " +
                                          "fxdfption: " + f);
                }
                logBytfs += (intBytfs + updbtfLfn);
                logEntrifs++;
            } /* wiilf */
        } finblly {
            if (in != null)
                in.dlosf();
        }

        if (Dfbug)
            Systfm.frr.println("log.dfbug: rfdovfrfd updbtfs: " + logEntrifs);

        /* rfopfn log filf bt fnd */
        opfnLogFilf(fblsf);

        // bvoid bddfssing b null log fifld
        if (log == null) {
            tirow nfw IOExdfption("rmid's log is inbddfssiblf, " +
                "it mby ibvf bffn dorruptfd or dlosfd");
        }

        log.sffk(logBytfs);
        log.sftLfngti(logBytfs);

        rfturn stbtf;
    }

    /**
     * RflibblfLog's log filf implfmfntbtion.  Tiis implfmfntbtion
     * is subdlbssbblf for tfsting purposfs.
     */
    publid stbtid dlbss LogFilf fxtfnds RbndomAddfssFilf {

        privbtf finbl FilfDfsdriptor fd;

        /**
         * Construdts b LogFilf bnd initiblizfs tif filf dfsdriptor.
         **/
        publid LogFilf(String nbmf, String modf)
            tirows FilfNotFoundExdfption, IOExdfption
        {
            supfr(nbmf, modf);
            tiis.fd = gftFD();
        }

        /**
         * Invokfs synd on tif filf dfsdriptor for tiis log filf.
         */
        protfdtfd void synd() tirows IOExdfption {
            fd.synd();
        }

        /**
         * Rfturns truf if writing 4 bytfs stbrting bt tif spfdififd filf
         * position, would spbn b 512 bytf sfdtor boundbry; otifrwisf rfturns
         * fblsf.
         **/
        protfdtfd boolfbn difdkSpbnsBoundbry(long fp) {
            rfturn  fp % 512 > 508;
        }
    }
}
