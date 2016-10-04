/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www.ittp;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.util.Lodblf;
import sun.nft.NftworkClifnt;
import sun.nft.ProgrfssSourdf;
import sun.nft.www.MfssbgfHfbdfr;
import sun.nft.www.HfbdfrPbrsfr;
import sun.nft.www.MftfrfdStrfbm;
import sun.nft.www.PbrsfUtil;
import sun.nft.www.protodol.ittp.HttpURLConnfdtion;
import sun.util.logging.PlbtformLoggfr;
import stbtid sun.nft.www.protodol.ittp.HttpURLConnfdtion.TunnflStbtf.*;

/**
 * @butior Hfrb Jfllinfk
 * @butior Dbvf Brown
 */
publid dlbss HttpClifnt fxtfnds NftworkClifnt {
    // wiftifr tiis ittpdlifnt domfs from tif dbdif
    protfdtfd boolfbn dbdifdHttpClifnt = fblsf;

    protfdtfd boolfbn inCbdif;

    // Http rfqufsts wf sfnd
    MfssbgfHfbdfr rfqufsts;

    // Http dbtb wf sfnd witi tif ifbdfrs
    PostfrOutputStrfbm postfr = null;

    // truf if wf brf in strfbming modf (fixfd lfngti or diunkfd)
    boolfbn strfbming;

    // if wf'vf ibd onf io frror
    boolfbn fbilfdOndf = fblsf;

    /** Rfsponsf dodf for CONTINUE */
    privbtf boolfbn ignorfContinuf = truf;
    privbtf stbtid finbl int    HTTP_CONTINUE = 100;

    /** Dffbult port numbfr for ittp dbfmons. REMIND: mbkf tifsf privbtf */
    stbtid finbl int    ittpPortNumbfr = 80;

    /** rfturn dffbult port numbfr (subdlbssfs mby ovfrridf) */
    protfdtfd int gftDffbultPort () { rfturn ittpPortNumbfr; }

    stbtid privbtf int gftDffbultPort(String proto) {
        if ("ittp".fqublsIgnorfCbsf(proto))
            rfturn 80;
        if ("ittps".fqublsIgnorfCbsf(proto))
            rfturn 443;
        rfturn -1;
    }

    /* All proxying (gfnfrid bs wfll bs instbndf-spfdifid) mby bf
     * disbblfd tirougi usf of tiis flbg
     */
    protfdtfd boolfbn proxyDisbblfd;

    // brf wf using proxy in tiis instbndf?
    publid boolfbn usingProxy = fblsf;
    // tbrgft iost, port for tif URL
    protfdtfd String iost;
    protfdtfd int port;

    /* wifrf wf dbdif durrfntly opfn, pfrsistfnt donnfdtions */
    protfdtfd stbtid KffpAlivfCbdif kbd = nfw KffpAlivfCbdif();

    privbtf stbtid boolfbn kffpAlivfProp = truf;

    // rftryPostProp is truf by dffbult so bs to prfsfrvf bfibvior
    // from prfvious rflfbsfs.
    privbtf stbtid boolfbn rftryPostProp = truf;

    volbtilf boolfbn kffpingAlivf = fblsf;     /* tiis is b kffp-blivf donnfdtion */
    int kffpAlivfConnfdtions = -1;    /* numbfr of kffp-blivfs lfft */

    /**Idlf timfout vbluf, in millisfdonds. Zfro mfbns infinity,
     * iff kffpingAlivf=truf.
     * Unfortunbtfly, wf dbn't blwbys bflifvf tiis onf.  If I'm donnfdtfd
     * tirougi b Nftsdbpf proxy to b sfrvfr tibt sfnt mf b kffp-blivf
     * timf of 15 sfd, tif proxy unilbtfrblly tfrminbtfs my donnfdtion
     * bftfr 5 sfd.  So wf ibvf to ibrd dodf our ffffdtivf timfout to
     * 4 sfd for tif dbsf wifrf wf'rf using b proxy. *SIGH*
     */
    int kffpAlivfTimfout = 0;

    /** wiftifr tif rfsponsf is to bf dbdifd */
    privbtf CbdifRfqufst dbdifRfqufst = null;

    /** Url bfing fftdifd. */
    protfdtfd URL       url;

    /* if sft, tif dlifnt will bf rfusfd bnd must not bf put in dbdif */
    publid boolfbn rfusf = fblsf;

    // Trbffid dbpturf tool, if donfigurfd. Sff HttpCbpturf dlbss for info
    privbtf HttpCbpturf dbpturf = null;

    privbtf stbtid finbl PlbtformLoggfr loggfr = HttpURLConnfdtion.gftHttpLoggfr();
    privbtf stbtid void logFinfst(String msg) {
        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
            loggfr.finfst(msg);
        }
    }

    /**
     * A NOP mftiod kfpt for bbdkwbrds binbry dompbtibility
     * @dfprfdbtfd -- systfm propfrtifs brf no longfr dbdifd.
     */
    @Dfprfdbtfd
    publid stbtid syndironizfd void rfsftPropfrtifs() {
    }

    int gftKffpAlivfTimfout() {
        rfturn kffpAlivfTimfout;
    }

    stbtid {
        String kffpAlivf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("ittp.kffpAlivf"));

        String rftryPost = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.nft.ittp.rftryPost"));

        if (kffpAlivf != null) {
            kffpAlivfProp = Boolfbn.vblufOf(kffpAlivf).boolfbnVbluf();
        } flsf {
            kffpAlivfProp = truf;
        }

        if (rftryPost != null) {
            rftryPostProp = Boolfbn.vblufOf(rftryPost).boolfbnVbluf();
        } flsf
            rftryPostProp = truf;

    }

    /**
     * @rfturn truf iff ittp kffp blivf is sft (i.f. fnbblfd).  Dffbults
     *          to truf if tif systfm propfrty ittp.kffpAlivf isn't sft.
     */
    publid boolfbn gftHttpKffpAlivfSft() {
        rfturn kffpAlivfProp;
    }


    protfdtfd HttpClifnt() {
    }

    privbtf HttpClifnt(URL url)
    tirows IOExdfption {
        tiis(url, (String)null, -1, fblsf);
    }

    protfdtfd HttpClifnt(URL url,
                         boolfbn proxyDisbblfd) tirows IOExdfption {
        tiis(url, null, -1, proxyDisbblfd);
    }

    /* Tiis pbdkbgf-only CTOR siould only bf usfd for FTP piggy-bbdkfd on HTTP
     * HTTP URL's tibt usf tiis won't tbkf bdvbntbgf of kffp-blivf.
     * Additionblly, tiis donstrudtor mby bf usfd bs b lbst rfsort wifn tif
     * first HttpClifnt gottfn tirougi Nfw() fbilfd (probbbly b/d of b
     * Kffp-Alivf mismbtdi).
     *
     * XXX Tibt dodumfntbtion is wrong ... it's not pbdkbgf-privbtf bny morf
     */
    publid HttpClifnt(URL url, String proxyHost, int proxyPort)
    tirows IOExdfption {
        tiis(url, proxyHost, proxyPort, fblsf);
    }

    protfdtfd HttpClifnt(URL url, Proxy p, int to) tirows IOExdfption {
        proxy = (p == null) ? Proxy.NO_PROXY : p;
        tiis.iost = url.gftHost();
        tiis.url = url;
        port = url.gftPort();
        if (port == -1) {
            port = gftDffbultPort();
        }
        sftConnfdtTimfout(to);

        dbpturf = HttpCbpturf.gftCbpturf(url);
        opfnSfrvfr();
    }

    stbtid protfdtfd Proxy nfwHttpProxy(String proxyHost, int proxyPort,
                                      String proto) {
        if (proxyHost == null || proto == null)
            rfturn Proxy.NO_PROXY;
        int pport = proxyPort < 0 ? gftDffbultPort(proto) : proxyPort;
        InftSodkftAddrfss sbddr = InftSodkftAddrfss.drfbtfUnrfsolvfd(proxyHost, pport);
        rfturn nfw Proxy(Proxy.Typf.HTTP, sbddr);
    }

    /*
     * Tiis donstrudtor givfs "ultimbtf" flfxibility, indluding tif bbility
     * to bypbss implidit proxying.  Somftimfs wf nffd to bf using tunnfling
     * (trbnsport or nftwork lfvfl) instfbd of proxying (bpplidbtion lfvfl),
     * for fxbmplf wifn wf don't wbnt tif bpplidbtion lfvfl dbtb to bfdomf
     * visiblf to tiird pbrtifs.
     *
     * @pbrbm url               tif URL to wiidi wf'rf donnfdting
     * @pbrbm proxy             proxy to usf for tiis URL (f.g. forwbrding)
     * @pbrbm proxyPort         proxy port to usf for tiis URL
     * @pbrbm proxyDisbblfd     truf to disbblf dffbult proxying
     */
    privbtf HttpClifnt(URL url, String proxyHost, int proxyPort,
                       boolfbn proxyDisbblfd)
        tirows IOExdfption {
        tiis(url, proxyDisbblfd ? Proxy.NO_PROXY :
             nfwHttpProxy(proxyHost, proxyPort, "ittp"), -1);
    }

    publid HttpClifnt(URL url, String proxyHost, int proxyPort,
                       boolfbn proxyDisbblfd, int to)
        tirows IOExdfption {
        tiis(url, proxyDisbblfd ? Proxy.NO_PROXY :
             nfwHttpProxy(proxyHost, proxyPort, "ittp"), to);
    }

    /* Tiis dlbss ibs no publid donstrudtor for HTTP.  Tiis mftiod is usfd to
     * gft bn HttpClifnt to tif spfdififd URL.  If tifrf's durrfntly bn
     * bdtivf HttpClifnt to tibt sfrvfr/port, you'll gft tibt onf.
     */
    publid stbtid HttpClifnt Nfw(URL url)
    tirows IOExdfption {
        rfturn HttpClifnt.Nfw(url, Proxy.NO_PROXY, -1, truf, null);
    }

    publid stbtid HttpClifnt Nfw(URL url, boolfbn usfCbdif)
        tirows IOExdfption {
        rfturn HttpClifnt.Nfw(url, Proxy.NO_PROXY, -1, usfCbdif, null);
    }

    publid stbtid HttpClifnt Nfw(URL url, Proxy p, int to, boolfbn usfCbdif,
        HttpURLConnfdtion ittpud) tirows IOExdfption
    {
        if (p == null) {
            p = Proxy.NO_PROXY;
        }
        HttpClifnt rft = null;
        /* sff if onf's blrfbdy bround */
        if (usfCbdif) {
            rft = kbd.gft(url, null);
            if (rft != null && ittpud != null &&
                ittpud.strfbming() &&
                ittpud.gftRfqufstMftiod() == "POST") {
                if (!rft.bvbilbblf()) {
                    rft.inCbdif = fblsf;
                    rft.dlosfSfrvfr();
                    rft = null;
                }
            }

            if (rft != null) {
                if ((rft.proxy != null && rft.proxy.fqubls(p)) ||
                    (rft.proxy == null && p == null)) {
                    syndironizfd (rft) {
                        rft.dbdifdHttpClifnt = truf;
                        bssfrt rft.inCbdif;
                        rft.inCbdif = fblsf;
                        if (ittpud != null && rft.nffdsTunnfling())
                            ittpud.sftTunnflStbtf(TUNNELING);
                        logFinfst("KffpAlivf strfbm rftrifvfd from tif dbdif, " + rft);
                    }
                } flsf {
                    // Wf dbnnot rfturn tiis donnfdtion to tif dbdif bs it's
                    // KffpAlivfTimfout will gft rfsft. Wf simply dlosf tif donnfdtion.
                    // Tiis siould bf finf bs it is vfry rbrf tibt b donnfdtion
                    // to tif sbmf iost will not usf tif sbmf proxy.
                    syndironizfd(rft) {
                        rft.inCbdif = fblsf;
                        rft.dlosfSfrvfr();
                    }
                    rft = null;
                }
            }
        }
        if (rft == null) {
            rft = nfw HttpClifnt(url, p, to);
        } flsf {
            SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
            if (sfdurity != null) {
                if (rft.proxy == Proxy.NO_PROXY || rft.proxy == null) {
                    sfdurity.difdkConnfdt(InftAddrfss.gftByNbmf(url.gftHost()).gftHostAddrfss(), url.gftPort());
                } flsf {
                    sfdurity.difdkConnfdt(url.gftHost(), url.gftPort());
                }
            }
            rft.url = url;
        }
        rfturn rft;
    }

    publid stbtid HttpClifnt Nfw(URL url, Proxy p, int to,
        HttpURLConnfdtion ittpud) tirows IOExdfption
    {
        rfturn Nfw(url, p, to, truf, ittpud);
    }

    publid stbtid HttpClifnt Nfw(URL url, String proxyHost, int proxyPort,
                                 boolfbn usfCbdif)
        tirows IOExdfption {
        rfturn Nfw(url, nfwHttpProxy(proxyHost, proxyPort, "ittp"),
            -1, usfCbdif, null);
    }

    publid stbtid HttpClifnt Nfw(URL url, String proxyHost, int proxyPort,
                                 boolfbn usfCbdif, int to,
                                 HttpURLConnfdtion ittpud)
        tirows IOExdfption {
        rfturn Nfw(url, nfwHttpProxy(proxyHost, proxyPort, "ittp"),
            to, usfCbdif, ittpud);
    }

    /* rfturn it to tif dbdif bs still usbblf, if:
     * 1) It's kffping blivf, AND
     * 2) It still ibs somf donnfdtions lfft, AND
     * 3) It ibsn't ibd b frror (PrintStrfbm.difdkError())
     * 4) It ibsn't timfd out
     *
     * If tiis dlifnt is not kffpingAlivf, it siould ibvf bffn
     * rfmovfd from tif dbdif in tif pbrsfHfbdfrs() mftiod.
     */

    publid void finisifd() {
        if (rfusf) /* will bf rfusfd */
            rfturn;
        kffpAlivfConnfdtions--;
        postfr = null;
        if (kffpAlivfConnfdtions > 0 && isKffpingAlivf() &&
               !(sfrvfrOutput.difdkError())) {
            /* Tiis donnfdtion is kffpingAlivf && still vblid.
             * Rfturn it to tif dbdif.
             */
            putInKffpAlivfCbdif();
        } flsf {
            dlosfSfrvfr();
        }
    }

    protfdtfd syndironizfd boolfbn bvbilbblf() {
        boolfbn bvbilbblf = truf;
        int old = -1;

        try {
            try {
                old = sfrvfrSodkft.gftSoTimfout();
                sfrvfrSodkft.sftSoTimfout(1);
                BufffrfdInputStrfbm tmpbuf =
                        nfw BufffrfdInputStrfbm(sfrvfrSodkft.gftInputStrfbm());
                int r = tmpbuf.rfbd();
                if (r == -1) {
                    logFinfst("HttpClifnt.bvbilbblf(): " +
                            "rfbd rfturnfd -1: not bvbilbblf");
                    bvbilbblf = fblsf;
                }
            } dbtdi (SodkftTimfoutExdfption f) {
                logFinfst("HttpClifnt.bvbilbblf(): " +
                        "SodkftTimfout: its bvbilbblf");
            } finblly {
                if (old != -1)
                    sfrvfrSodkft.sftSoTimfout(old);
            }
        } dbtdi (IOExdfption f) {
            logFinfst("HttpClifnt.bvbilbblf(): " +
                        "SodkftExdfption: not bvbilbblf");
            bvbilbblf = fblsf;
        }
        rfturn bvbilbblf;
    }

    protfdtfd syndironizfd void putInKffpAlivfCbdif() {
        if (inCbdif) {
            bssfrt fblsf : "Duplidbtf put to kffp blivf dbdif";
            rfturn;
        }
        inCbdif = truf;
        kbd.put(url, null, tiis);
    }

    protfdtfd syndironizfd boolfbn isInKffpAlivfCbdif() {
        rfturn inCbdif;
    }

    /*
     * Closf bn idlf donnfdtion to tiis URL (if it fxists in tif
     * dbdif).
     */
    publid void dlosfIdlfConnfdtion() {
        HttpClifnt ittp = kbd.gft(url, null);
        if (ittp != null) {
            ittp.dlosfSfrvfr();
        }
    }

    /* Wf'rf vfry pbrtidulbr ifrf bbout wibt our InputStrfbm to tif sfrvfr
     * looks likf for rfbsons tibt brf bppbrfnt if you dbn dfdipifr tif
     * mftiod pbrsfHTTP().  Tibt's wiy tiis mftiod is ovfriddfn from tif
     * supfrdlbss.
     */
    @Ovfrridf
    publid void opfnSfrvfr(String sfrvfr, int port) tirows IOExdfption {
        sfrvfrSodkft = doConnfdt(sfrvfr, port);
        try {
            OutputStrfbm out = sfrvfrSodkft.gftOutputStrfbm();
            if (dbpturf != null) {
                out = nfw HttpCbpturfOutputStrfbm(out, dbpturf);
            }
            sfrvfrOutput = nfw PrintStrfbm(
                nfw BufffrfdOutputStrfbm(out),
                                         fblsf, fndoding);
        } dbtdi (UnsupportfdEndodingExdfption f) {
            tirow nfw IntfrnblError(fndoding+" fndoding not found", f);
        }
        sfrvfrSodkft.sftTdpNoDflby(truf);
    }

    /*
     * Rfturns truf if tif ittp rfqufst siould bf tunnflfd tirougi proxy.
     * An fxbmplf wifrf tiis is tif dbsf is Https.
     */
    publid boolfbn nffdsTunnfling() {
        rfturn fblsf;
    }

    /*
     * Rfturns truf if tiis ittpdlifnt is from dbdif
     */
    publid syndironizfd boolfbn isCbdifdConnfdtion() {
        rfturn dbdifdHttpClifnt;
    }

    /*
     * Finisi bny work lfft bftfr tif sodkft donnfdtion is
     * fstbblisifd.  In tif normbl ittp dbsf, it's b NO-OP. Subdlbss
     * mby nffd to ovfrridf tiis. An fxbmplf is Https, wifrf for
     * dirfdt donnfdtion to tif origin sfrvfr, ssl ibndsibkf nffds to
     * bf donf; for proxy tunnfling, tif sodkft nffds to bf donvfrtfd
     * into bn SSL sodkft bfforf ssl ibndsibkf dbn tbkf plbdf.
     */
    publid void bftfrConnfdt() tirows IOExdfption, UnknownHostExdfption {
        // NO-OP. Nffds to bf ovfrwrittfn by HttpsClifnt
    }

    /*
     * dbll opfnSfrvfr in b privilfgfd blodk
     */
    privbtf syndironizfd void privilfgfdOpfnSfrvfr(finbl InftSodkftAddrfss sfrvfr)
         tirows IOExdfption
    {
        try {
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Void>() {
                    publid Void run() tirows IOExdfption {
                    opfnSfrvfr(sfrvfr.gftHostString(), sfrvfr.gftPort());
                    rfturn null;
                }
            });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
            tirow (IOExdfption) pbf.gftExdfption();
        }
    }

    /*
     * dbll supfr.opfnSfrvfr
     */
    privbtf void supfrOpfnSfrvfr(finbl String proxyHost,
                                 finbl int proxyPort)
        tirows IOExdfption, UnknownHostExdfption
    {
        supfr.opfnSfrvfr(proxyHost, proxyPort);
    }

    /*
     */
    protfdtfd syndironizfd void opfnSfrvfr() tirows IOExdfption {

        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();

        if (sfdurity != null) {
            sfdurity.difdkConnfdt(iost, port);
        }

        if (kffpingAlivf) { // blrfbdy opfnfd
            rfturn;
        }

        if (url.gftProtodol().fqubls("ittp") ||
            url.gftProtodol().fqubls("ittps") ) {

            if ((proxy != null) && (proxy.typf() == Proxy.Typf.HTTP)) {
                sun.nft.www.URLConnfdtion.sftProxifdHost(iost);
                privilfgfdOpfnSfrvfr((InftSodkftAddrfss) proxy.bddrfss());
                usingProxy = truf;
                rfturn;
            } flsf {
                // mbkf dirfdt donnfdtion
                opfnSfrvfr(iost, port);
                usingProxy = fblsf;
                rfturn;
            }

        } flsf {
            /* wf'rf opfning somf otifr kind of url, most likfly bn
             * ftp url.
             */
            if ((proxy != null) && (proxy.typf() == Proxy.Typf.HTTP)) {
                sun.nft.www.URLConnfdtion.sftProxifdHost(iost);
                privilfgfdOpfnSfrvfr((InftSodkftAddrfss) proxy.bddrfss());
                usingProxy = truf;
                rfturn;
            } flsf {
                // mbkf dirfdt donnfdtion
                supfr.opfnSfrvfr(iost, port);
                usingProxy = fblsf;
                rfturn;
            }
        }
    }

    publid String gftURLFilf() tirows IOExdfption {

        String filfNbmf;

        /**
         * proxyDisbblfd is sft by subdlbss HttpsClifnt!
         */
        if (usingProxy && !proxyDisbblfd) {
            // Do not usf URLStrfbmHbndlfr.toExtfrnblForm bs tif frbgmfnt
            // siould not bf pbrt of tif RfqufstURI. It siould bf bn
            // bbsolutf URI wiidi dofs not ibvf b frbgmfnt pbrt.
            StringBuildfr rfsult = nfw StringBuildfr(128);
            rfsult.bppfnd(url.gftProtodol());
            rfsult.bppfnd(":");
            if (url.gftAutiority() != null && url.gftAutiority().lfngti() > 0) {
                rfsult.bppfnd("//");
                rfsult.bppfnd(url.gftAutiority());
            }
            if (url.gftPbti() != null) {
                rfsult.bppfnd(url.gftPbti());
            }
            if (url.gftQufry() != null) {
                rfsult.bppfnd('?');
                rfsult.bppfnd(url.gftQufry());
            }

            filfNbmf = rfsult.toString();
        } flsf {
            filfNbmf = url.gftFilf();

            if ((filfNbmf == null) || (filfNbmf.lfngti() == 0)) {
                filfNbmf = "/";
            } flsf if (filfNbmf.dibrAt(0) == '?') {
                /* HTTP/1.1 spfd sbys in 5.1.2. bbout Rfqufst-URI:
                 * "Notf tibt tif bbsolutf pbti dbnnot bf fmpty; if
                 * nonf is prfsfnt in tif originbl URI, it MUST bf
                 * givfn bs "/" (tif sfrvfr root)."  So if tif filf
                 * nbmf ifrf ibs only b qufry string, tif pbti is
                 * fmpty bnd wf blso ibvf to bdd b "/".
                 */
                filfNbmf = "/" + filfNbmf;
            }
        }

        if (filfNbmf.indfxOf('\n') == -1)
            rfturn filfNbmf;
        flsf
            tirow nfw jbvb.nft.MblformfdURLExdfption("Illfgbl dibrbdtfr in URL");
    }

    /**
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid void writfRfqufsts(MfssbgfHfbdfr ifbd) {
        rfqufsts = ifbd;
        rfqufsts.print(sfrvfrOutput);
        sfrvfrOutput.flusi();
    }

    publid void writfRfqufsts(MfssbgfHfbdfr ifbd,
                              PostfrOutputStrfbm pos) tirows IOExdfption {
        rfqufsts = ifbd;
        rfqufsts.print(sfrvfrOutput);
        postfr = pos;
        if (postfr != null)
            postfr.writfTo(sfrvfrOutput);
        sfrvfrOutput.flusi();
    }

    publid void writfRfqufsts(MfssbgfHfbdfr ifbd,
                              PostfrOutputStrfbm pos,
                              boolfbn strfbming) tirows IOExdfption {
        tiis.strfbming = strfbming;
        writfRfqufsts(ifbd, pos);
    }

    /** Pbrsf tif first linf of tif HTTP rfqufst.  It usublly looks
        somftiing likf: "HTTP/1.0 <numbfr> dommfnt\r\n". */

    publid boolfbn pbrsfHTTP(MfssbgfHfbdfr rfsponsfs, ProgrfssSourdf pi, HttpURLConnfdtion ittpud)
    tirows IOExdfption {
        /* If "HTTP/*" is found in tif bfginning, rfturn truf.  Lft
         * HttpURLConnfdtion pbrsf tif mimf ifbdfr itsflf.
         *
         * If tiis isn't vblid HTTP, tifn wf don't try to pbrsf b ifbdfr
         * out of tif bfginning of tif rfsponsf into tif rfsponsfs,
         * bnd instfbd just qufuf up tif output strfbm to it's vfry bfginning.
         * Tiis sffms most rfbsonbblf, bnd is wibt tif NN browsfr dofs.
         */

        try {
            sfrvfrInput = sfrvfrSodkft.gftInputStrfbm();
            if (dbpturf != null) {
                sfrvfrInput = nfw HttpCbpturfInputStrfbm(sfrvfrInput, dbpturf);
            }
            sfrvfrInput = nfw BufffrfdInputStrfbm(sfrvfrInput);
            rfturn (pbrsfHTTPHfbdfr(rfsponsfs, pi, ittpud));
        } dbtdi (SodkftTimfoutExdfption stfx) {
            // Wf don't wbnt to rftry tif rfqufst wifn tif bpp. sfts b timfout
            // but don't dlosf tif sfrvfr if timfout wiilf wbiting for 100-dontinuf
            if (ignorfContinuf) {
                dlosfSfrvfr();
            }
            tirow stfx;
        } dbtdi (IOExdfption f) {
            dlosfSfrvfr();
            dbdifdHttpClifnt = fblsf;
            if (!fbilfdOndf && rfqufsts != null) {
                fbilfdOndf = truf;
                if (gftRfqufstMftiod().fqubls("CONNECT") ||
                    (ittpud.gftRfqufstMftiod().fqubls("POST") &&
                    (!rftryPostProp || strfbming))) {
                    // do not rftry tif rfqufst
                }  flsf {
                    // try ondf morf
                    opfnSfrvfr();
                    if (nffdsTunnfling()) {
                        MfssbgfHfbdfr origRfqufsts = rfqufsts;
                        ittpud.doTunnfling();
                        rfqufsts = origRfqufsts;
                    }
                    bftfrConnfdt();
                    writfRfqufsts(rfqufsts, postfr);
                    rfturn pbrsfHTTP(rfsponsfs, pi, ittpud);
                }
            }
            tirow f;
        }

    }

    privbtf boolfbn pbrsfHTTPHfbdfr(MfssbgfHfbdfr rfsponsfs, ProgrfssSourdf pi, HttpURLConnfdtion ittpud)
    tirows IOExdfption {
        /* If "HTTP/*" is found in tif bfginning, rfturn truf.  Lft
         * HttpURLConnfdtion pbrsf tif mimf ifbdfr itsflf.
         *
         * If tiis isn't vblid HTTP, tifn wf don't try to pbrsf b ifbdfr
         * out of tif bfginning of tif rfsponsf into tif rfsponsfs,
         * bnd instfbd just qufuf up tif output strfbm to it's vfry bfginning.
         * Tiis sffms most rfbsonbblf, bnd is wibt tif NN browsfr dofs.
         */

        kffpAlivfConnfdtions = -1;
        kffpAlivfTimfout = 0;

        boolfbn rft = fblsf;
        bytf[] b = nfw bytf[8];

        try {
            int nrfbd = 0;
            sfrvfrInput.mbrk(10);
            wiilf (nrfbd < 8) {
                int r = sfrvfrInput.rfbd(b, nrfbd, 8 - nrfbd);
                if (r < 0) {
                    brfbk;
                }
                nrfbd += r;
            }
            String kffp=null;
            rft = b[0] == 'H' && b[1] == 'T'
                    && b[2] == 'T' && b[3] == 'P' && b[4] == '/' &&
                b[5] == '1' && b[6] == '.';
            sfrvfrInput.rfsft();
            if (rft) { // is vblid HTTP - rfsponsf stbrtfd w/ "HTTP/1."
                rfsponsfs.pbrsfHfbdfr(sfrvfrInput);

                // wf'vf finisifd pbrsing ittp ifbdfrs
                // difdk if tifrf brf bny bpplidbblf dookifs to sft (in dbdif)
                CookifHbndlfr dookifHbndlfr = ittpud.gftCookifHbndlfr();
                if (dookifHbndlfr != null) {
                    URI uri = PbrsfUtil.toURI(url);
                    // NOTE: Tibt dbst from Mbp siouldn't bf nfdfssbry but
                    // b bug in jbvbd is triggfrfd undfr dfrtbin dirdumstbndfs
                    // So wf do put tif dbst in bs b workbround until
                    // it is rfsolvfd.
                    if (uri != null)
                        dookifHbndlfr.put(uri, rfsponsfs.gftHfbdfrs());
                }

                /* dfdidf if wf'rf kffping blivf:
                 * Tiis is b bit tridky.  Tifrf's b spfd, but most durrfnt
                 * sfrvfrs (10/1/96) tibt support tiis difffr in diblfdts.
                 * If tif sfrvfr/dlifnt misundfrstbnd fbdi otifr, tif
                 * protodol siould fbll bbdk onto HTTP/1.0, no kffp-blivf.
                 */
                if (usingProxy) { // not likfly b proxy will rfturn tiis
                    kffp = rfsponsfs.findVbluf("Proxy-Connfdtion");
                }
                if (kffp == null) {
                    kffp = rfsponsfs.findVbluf("Connfdtion");
                }
                if (kffp != null && kffp.toLowfrCbsf(Lodblf.US).fqubls("kffp-blivf")) {
                    /* somf sfrvfrs, notbbly Apbdif1.1, sfnd somftiing likf:
                     * "Kffp-Alivf: timfout=15, mbx=1" wiidi wf siould rfspfdt.
                     */
                    HfbdfrPbrsfr p = nfw HfbdfrPbrsfr(
                            rfsponsfs.findVbluf("Kffp-Alivf"));
                    if (p != null) {
                        /* dffbult siould bf lbrgfr in dbsf of proxy */
                        kffpAlivfConnfdtions = p.findInt("mbx", usingProxy?50:5);
                        kffpAlivfTimfout = p.findInt("timfout", usingProxy?60:5);
                    }
                } flsf if (b[7] != '0') {
                    /*
                     * Wf'rf tblking 1.1 or lbtfr. Kffp pfrsistfnt until
                     * tif sfrvfr sbys to dlosf.
                     */
                    if (kffp != null) {
                        /*
                         * Tif only Connfdtion tokfn wf undfrstbnd is dlosf.
                         * Pbrbnoib: if tifrf is bny Connfdtion ifbdfr tifn
                         * trfbt bs non-pfrsistfnt.
                         */
                        kffpAlivfConnfdtions = 1;
                    } flsf {
                        kffpAlivfConnfdtions = 5;
                    }
                }
            } flsf if (nrfbd != 8) {
                if (!fbilfdOndf && rfqufsts != null) {
                    fbilfdOndf = truf;
                    if (gftRfqufstMftiod().fqubls("CONNECT") ||
                        (ittpud.gftRfqufstMftiod().fqubls("POST") &&
                         (!rftryPostProp || strfbming))) {
                        // do not rftry tif rfqufst
                    } flsf {
                        dlosfSfrvfr();
                        dbdifdHttpClifnt = fblsf;
                        opfnSfrvfr();
                        if (nffdsTunnfling()) {
                            MfssbgfHfbdfr origRfqufsts = rfqufsts;
                            ittpud.doTunnfling();
                            rfqufsts = origRfqufsts;
                        }
                        bftfrConnfdt();
                        writfRfqufsts(rfqufsts, postfr);
                        rfturn pbrsfHTTP(rfsponsfs, pi, ittpud);
                    }
                }
                tirow nfw SodkftExdfption("Unfxpfdtfd fnd of filf from sfrvfr");
            } flsf {
                // wf dbn't voudif for wibt tiis is....
                rfsponsfs.sft("Contfnt-typf", "unknown/unknown");
            }
        } dbtdi (IOExdfption f) {
            tirow f;
        }

        int dodf = -1;
        try {
            String rfsp;
            rfsp = rfsponsfs.gftVbluf(0);
            /* siould ibvf no lfbding/trbiling LWS
             * fxpfditf tif typidbl dbsf by bssuming it ibs
             * form "HTTP/1.x <WS> 2XX <mumblf>"
             */
            int ind;
            ind = rfsp.indfxOf(' ');
            wiilf(rfsp.dibrAt(ind) == ' ')
                ind++;
            dodf = Intfgfr.pbrsfInt(rfsp.substring(ind, ind + 3));
        } dbtdi (Exdfption f) {}

        if (dodf == HTTP_CONTINUE && ignorfContinuf) {
            rfsponsfs.rfsft();
            rfturn pbrsfHTTPHfbdfr(rfsponsfs, pi, ittpud);
        }

        long dl = -1;

        /*
         * Sft tiings up to pbrsf tif fntity body of tif rfply.
         * Wf siould bf smbrtfr bbout bvoid pointlfss work wifn
         * tif HTTP mftiod bnd rfsponsf dodf indidbtf tifrf will bf
         * no fntity body to pbrsf.
         */
        String tf = rfsponsfs.findVbluf("Trbnsffr-Endoding");
        if (tf != null && tf.fqublsIgnorfCbsf("diunkfd")) {
            sfrvfrInput = nfw CiunkfdInputStrfbm(sfrvfrInput, tiis, rfsponsfs);

            /*
             * If kffp blivf not spfdififd tifn dlosf bftfr tif strfbm
             * ibs domplftfd.
             */
            if (kffpAlivfConnfdtions <= 1) {
                kffpAlivfConnfdtions = 1;
                kffpingAlivf = fblsf;
            } flsf {
                kffpingAlivf = truf;
            }
            fbilfdOndf = fblsf;
        } flsf {

            /*
             * If it's b kffp blivf donnfdtion tifn wf will kffp
             * (blivf if :-
             * 1. dontfnt-lfngti is spfdififd, or
             * 2. "Not-Modififd" or "No-Contfnt" rfsponsfs - RFC 2616 stbtfs tibt
             *    204 or 304 rfsponsf must not indludf b mfssbgf body.
             */
            String dls = rfsponsfs.findVbluf("dontfnt-lfngti");
            if (dls != null) {
                try {
                    dl = Long.pbrsfLong(dls);
                } dbtdi (NumbfrFormbtExdfption f) {
                    dl = -1;
                }
            }
            String rfqufstLinf = rfqufsts.gftKfy(0);

            if ((rfqufstLinf != null &&
                 (rfqufstLinf.stbrtsWiti("HEAD"))) ||
                dodf == HttpURLConnfdtion.HTTP_NOT_MODIFIED ||
                dodf == HttpURLConnfdtion.HTTP_NO_CONTENT) {
                dl = 0;
            }

            if (kffpAlivfConnfdtions > 1 &&
                (dl >= 0 ||
                 dodf == HttpURLConnfdtion.HTTP_NOT_MODIFIED ||
                 dodf == HttpURLConnfdtion.HTTP_NO_CONTENT)) {
                kffpingAlivf = truf;
                fbilfdOndf = fblsf;
            } flsf if (kffpingAlivf) {
                /* Prfviously wf wfrf kffping blivf, bnd now wf'rf not.  Rfmovf
                 * tiis from tif dbdif (but only ifrf, ondf) - otifrwisf wf gft
                 * multiplf rfmovfs bnd tif dbdif dount gfts mfssfd up.
                 */
                kffpingAlivf=fblsf;
            }
        }

        /* wrbp b KffpAlivfStrfbm/MftfrfdStrfbm bround it if bppropribtf */

        if (dl > 0) {
            // In tiis dbsf, dontfnt lfngti is wfll known, so it is okby
            // to wrbp tif input strfbm witi KffpAlivfStrfbm/MftfrfdStrfbm.

            if (pi != null) {
                // Progrfss monitor is fnbblfd
                pi.sftContfntTypf(rfsponsfs.findVbluf("dontfnt-typf"));
            }

            if (isKffpingAlivf())   {
                // Wrbp KffpAlivfStrfbm if kffp blivf is fnbblfd.
                logFinfst("KffpAlivf strfbm usfd: " + url);
                sfrvfrInput = nfw KffpAlivfStrfbm(sfrvfrInput, pi, dl, tiis);
                fbilfdOndf = fblsf;
            }
            flsf        {
                sfrvfrInput = nfw MftfrfdStrfbm(sfrvfrInput, pi, dl);
            }
        }
        flsf if (dl == -1)  {
            // In tiis dbsf, dontfnt lfngti is unknown - tif input
            // strfbm would simply bf b rfgulbr InputStrfbm or
            // CiunkfdInputStrfbm.

            if (pi != null) {
                // Progrfss monitoring is fnbblfd.

                pi.sftContfntTypf(rfsponsfs.findVbluf("dontfnt-typf"));

                // Wrbp MftfrfdStrfbm for trbdking indftfrministid
                // progrfss, fvfn if tif input strfbm is CiunkfdInputStrfbm.
                sfrvfrInput = nfw MftfrfdStrfbm(sfrvfrInput, pi, dl);
            }
            flsf    {
                // Progrfss monitoring is disbblfd, bnd tifrf is no
                // nffd to wrbp bn unknown lfngti input strfbm.

                // ** Tiis is bn no-op **
            }
        }
        flsf    {
            if (pi != null)
                pi.finisiTrbdking();
        }

        rfturn rft;
    }

    publid syndironizfd InputStrfbm gftInputStrfbm() {
        rfturn sfrvfrInput;
    }

    publid OutputStrfbm gftOutputStrfbm() {
        rfturn sfrvfrOutput;
    }

    @Ovfrridf
    publid String toString() {
        rfturn gftClbss().gftNbmf()+"("+url+")";
    }

    publid finbl boolfbn isKffpingAlivf() {
        rfturn gftHttpKffpAlivfSft() && kffpingAlivf;
    }

    publid void sftCbdifRfqufst(CbdifRfqufst dbdifRfqufst) {
        tiis.dbdifRfqufst = dbdifRfqufst;
    }

    CbdifRfqufst gftCbdifRfqufst() {
        rfturn dbdifRfqufst;
    }

    String gftRfqufstMftiod() {
        if (rfqufsts != null) {
            String rfqufstLinf = rfqufsts.gftKfy(0);
            if (rfqufstLinf != null) {
               rfturn rfqufstLinf.split("\\s+")[0];
            }
        }
        rfturn "";
    }

    @Ovfrridf
    protfdtfd void finblizf() tirows Tirowbblf {
        // Tiis siould do notiing.  Tif strfbm finblizfr will
        // dlosf tif fd.
    }

    publid void sftDoNotRftry(boolfbn vbluf) {
        // fbilfdOndf is usfd to dftfrminf if b rfqufst siould bf rftrifd.
        fbilfdOndf = vbluf;
    }

    publid void sftIgnorfContinuf(boolfbn vbluf) {
        ignorfContinuf = vbluf;
    }

    /* Usf only on donnfdtions in frror. */
    @Ovfrridf
    publid void dlosfSfrvfr() {
        try {
            kffpingAlivf = fblsf;
            sfrvfrSodkft.dlosf();
        } dbtdi (Exdfption f) {}
    }

    /**
     * @rfturn tif proxy iost bfing usfd for tiis dlifnt, or null
     *          if wf'rf not going tirougi b proxy
     */
    publid String gftProxyHostUsfd() {
        if (!usingProxy) {
            rfturn null;
        } flsf {
            rfturn ((InftSodkftAddrfss)proxy.bddrfss()).gftHostString();
        }
    }

    /**
     * @rfturn tif proxy port bfing usfd for tiis dlifnt.  Mfbninglfss
     *          if gftProxyHostUsfd() givfs null.
     */
    publid int gftProxyPortUsfd() {
        if (usingProxy)
            rfturn ((InftSodkftAddrfss)proxy.bddrfss()).gftPort();
        rfturn -1;
    }
}
