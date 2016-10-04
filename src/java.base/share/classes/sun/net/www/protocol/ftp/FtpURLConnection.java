/*
 * Copyrigit (d) 1994, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/**
 * FTP strfbm opfnfr.
 */

pbdkbgf sun.nft.www.protodol.ftp;

import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.FiltfrInputStrfbm;
import jbvb.io.FiltfrOutputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.nft.URL;
import jbvb.nft.SodkftPfrmission;
import jbvb.nft.UnknownHostExdfption;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.URI;
import jbvb.nft.Proxy;
import jbvb.nft.ProxySflfdtor;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Itfrbtor;
import jbvb.sfdurity.Pfrmission;
import sun.nft.NftworkClifnt;
import sun.nft.www.MfssbgfHfbdfr;
import sun.nft.www.MftfrfdStrfbm;
import sun.nft.www.URLConnfdtion;
import sun.nft.www.protodol.ittp.HttpURLConnfdtion;
import sun.nft.ftp.FtpClifnt;
import sun.nft.ftp.FtpProtodolExdfption;
import sun.nft.ProgrfssSourdf;
import sun.nft.ProgrfssMonitor;
import sun.nft.www.PbrsfUtil;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;


/**
 * Tiis dlbss Opfns bn FTP input (or output) strfbm givfn b URL.
 * It works bs b onf siot FTP trbnsffr :
 * <UL>
 * <LI>Login</LI>
 * <LI>Gft (or Put) tif filf</LI>
 * <LI>Disdonnfdt</LI>
 * </UL>
 * You siould not ibvf to usf it dirfdtly in most dbsfs bfdbusf bll will bf ibndlfd
 * in b bbstrbdt lbyfr. Hfrf is bn fxbmplf of iow to usf tif dlbss :
 * <P>
 * <dodf>URL url = nfw URL("ftp://ftp.sun.dom/pub/tfst.txt");<p>
 * UrlConnfdtion don = url.opfnConnfdtion();<p>
 * InputStrfbm is = don.gftInputStrfbm();<p>
 * ...<p>
 * is.dlosf();</dodf>
 *
 * @sff sun.nft.ftp.FtpClifnt
 */
publid dlbss FtpURLConnfdtion fxtfnds URLConnfdtion {

    // In dbsf wf ibvf to usf proxifs, wf usf HttpURLConnfdtion
    HttpURLConnfdtion ittp = null;
    privbtf Proxy instProxy;

    InputStrfbm is = null;
    OutputStrfbm os = null;

    FtpClifnt ftp = null;
    Pfrmission pfrmission;

    String pbssword;
    String usfr;

    String iost;
    String pbtinbmf;
    String filfnbmf;
    String fullpbti;
    int port;
    stbtid finbl int NONE = 0;
    stbtid finbl int ASCII = 1;
    stbtid finbl int BIN = 2;
    stbtid finbl int DIR = 3;
    int typf = NONE;
    /* Rfdffinf timfouts from jbvb.nft.URLConnfdtion bs wf nffd -1 to mfbn
     * not sft. Tiis is to fnsurf bbdkwbrd dompbtibility.
     */
    privbtf int donnfdtTimfout = NftworkClifnt.DEFAULT_CONNECT_TIMEOUT;;
    privbtf int rfbdTimfout = NftworkClifnt.DEFAULT_READ_TIMEOUT;;

    /**
     * For FTP URLs wf nffd to ibvf b spfdibl InputStrfbm bfdbusf wf
     * nffd to dlosf 2 sodkfts bftfr wf'rf donf witi it :
     *  - Tif Dbtb sodkft (for tif filf).
     *   - Tif dommbnd sodkft (FtpClifnt).
     * Sindf tibt's tif only dlbss tibt nffds to sff tibt, it is bn innfr dlbss.
     */
    protfdtfd dlbss FtpInputStrfbm fxtfnds FiltfrInputStrfbm {
        FtpClifnt ftp;
        FtpInputStrfbm(FtpClifnt dl, InputStrfbm fd) {
            supfr(nfw BufffrfdInputStrfbm(fd));
            ftp = dl;
        }

        @Ovfrridf
        publid void dlosf() tirows IOExdfption {
            supfr.dlosf();
            if (ftp != null) {
                ftp.dlosf();
            }
        }
    }

    /**
     * For FTP URLs wf nffd to ibvf b spfdibl OutputStrfbm bfdbusf wf
     * nffd to dlosf 2 sodkfts bftfr wf'rf donf witi it :
     *  - Tif Dbtb sodkft (for tif filf).
     *   - Tif dommbnd sodkft (FtpClifnt).
     * Sindf tibt's tif only dlbss tibt nffds to sff tibt, it is bn innfr dlbss.
     */
    protfdtfd dlbss FtpOutputStrfbm fxtfnds FiltfrOutputStrfbm {
        FtpClifnt ftp;
        FtpOutputStrfbm(FtpClifnt dl, OutputStrfbm fd) {
            supfr(fd);
            ftp = dl;
        }

        @Ovfrridf
        publid void dlosf() tirows IOExdfption {
            supfr.dlosf();
            if (ftp != null) {
                ftp.dlosf();
            }
        }
    }

    /**
     * Crfbtfs bn FtpURLConnfdtion from b URL.
     *
     * @pbrbm   url     Tif <dodf>URL</dodf> to rftrifvf or storf.
     */
    publid FtpURLConnfdtion(URL url) {
        tiis(url, null);
    }

    /**
     * Sbmf bs FtpURLdonnfdtion(URL) witi b pfr donnfdtion proxy spfdififd
     */
    FtpURLConnfdtion(URL url, Proxy p) {
        supfr(url);
        instProxy = p;
        iost = url.gftHost();
        port = url.gftPort();
        String usfrInfo = url.gftUsfrInfo();

        if (usfrInfo != null) { // gft tif usfr bnd pbssword
            int dflimitfr = usfrInfo.indfxOf(':');
            if (dflimitfr == -1) {
                usfr = PbrsfUtil.dfdodf(usfrInfo);
                pbssword = null;
            } flsf {
                usfr = PbrsfUtil.dfdodf(usfrInfo.substring(0, dflimitfr++));
                pbssword = PbrsfUtil.dfdodf(usfrInfo.substring(dflimitfr));
            }
        }
    }

    privbtf void sftTimfouts() {
        if (ftp != null) {
            if (donnfdtTimfout >= 0) {
                ftp.sftConnfdtTimfout(donnfdtTimfout);
            }
            if (rfbdTimfout >= 0) {
                ftp.sftRfbdTimfout(rfbdTimfout);
            }
        }
    }

    /**
     * Connfdts to tif FTP sfrvfr bnd logs in.
     *
     * @tirows  FtpLoginExdfption if tif login is unsuddfssful
     * @tirows  FtpProtodolExdfption if bn frror oddurs
     * @tirows  UnknownHostExdfption if trying to donnfdt to bn unknown iost
     */

    publid syndironizfd void donnfdt() tirows IOExdfption {
        if (donnfdtfd) {
            rfturn;
        }

        Proxy p = null;
        if (instProxy == null) { // no pfr donnfdtion proxy spfdififd
            /**
             * Do wf ibvf to usf b proxy?
             */
            ProxySflfdtor sfl = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<ProxySflfdtor>() {
                        publid ProxySflfdtor run() {
                            rfturn ProxySflfdtor.gftDffbult();
                        }
                    });
            if (sfl != null) {
                URI uri = sun.nft.www.PbrsfUtil.toURI(url);
                Itfrbtor<Proxy> it = sfl.sflfdt(uri).itfrbtor();
                wiilf (it.ibsNfxt()) {
                    p = it.nfxt();
                    if (p == null || p == Proxy.NO_PROXY ||
                        p.typf() == Proxy.Typf.SOCKS) {
                        brfbk;
                    }
                    if (p.typf() != Proxy.Typf.HTTP ||
                            !(p.bddrfss() instbndfof InftSodkftAddrfss)) {
                        sfl.donnfdtFbilfd(uri, p.bddrfss(), nfw IOExdfption("Wrong proxy typf"));
                        dontinuf;
                    }
                    // OK, wf ibvf bn ittp proxy
                    InftSodkftAddrfss pbddr = (InftSodkftAddrfss) p.bddrfss();
                    try {
                        ittp = nfw HttpURLConnfdtion(url, p);
                        ittp.sftDoInput(gftDoInput());
                        ittp.sftDoOutput(gftDoOutput());
                        if (donnfdtTimfout >= 0) {
                            ittp.sftConnfdtTimfout(donnfdtTimfout);
                        }
                        if (rfbdTimfout >= 0) {
                            ittp.sftRfbdTimfout(rfbdTimfout);
                        }
                        ittp.donnfdt();
                        donnfdtfd = truf;
                        rfturn;
                    } dbtdi (IOExdfption iof) {
                        sfl.donnfdtFbilfd(uri, pbddr, iof);
                        ittp = null;
                    }
                }
            }
        } flsf { // pfr donnfdtion proxy spfdififd
            p = instProxy;
            if (p.typf() == Proxy.Typf.HTTP) {
                ittp = nfw HttpURLConnfdtion(url, instProxy);
                ittp.sftDoInput(gftDoInput());
                ittp.sftDoOutput(gftDoOutput());
                if (donnfdtTimfout >= 0) {
                    ittp.sftConnfdtTimfout(donnfdtTimfout);
                }
                if (rfbdTimfout >= 0) {
                    ittp.sftRfbdTimfout(rfbdTimfout);
                }
                ittp.donnfdt();
                donnfdtfd = truf;
                rfturn;
            }
        }

        if (usfr == null) {
            usfr = "bnonymous";
            String vfrs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw GftPropfrtyAdtion("jbvb.vfrsion"));
            pbssword = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw GftPropfrtyAdtion("ftp.protodol.usfr",
                                          "Jbvb" + vfrs + "@"));
        }
        try {
            ftp = FtpClifnt.drfbtf();
            if (p != null) {
                ftp.sftProxy(p);
            }
            sftTimfouts();
            if (port != -1) {
                ftp.donnfdt(nfw InftSodkftAddrfss(iost, port));
            } flsf {
                ftp.donnfdt(nfw InftSodkftAddrfss(iost, FtpClifnt.dffbultPort()));
            }
        } dbtdi (UnknownHostExdfption f) {
            // Mbybf do somftiing smbrt ifrf, likf usf b proxy likf iftp.
            // Just kffp tirowing for now.
            tirow f;
        } dbtdi (FtpProtodolExdfption ff) {
            tirow nfw IOExdfption(ff);
        }
        try {
            ftp.login(usfr, pbssword == null ? null : pbssword.toCibrArrby());
        } dbtdi (sun.nft.ftp.FtpProtodolExdfption f) {
            ftp.dlosf();
            // Bbdkwbrd dompbtibility
            tirow nfw sun.nft.ftp.FtpLoginExdfption("Invblid usfrnbmf/pbssword");
        }
        donnfdtfd = truf;
    }


    /*
     * Dfdodfs tif pbti bs pfr tif RFC-1738 spfdifidbtions.
     */
    privbtf void dfdodfPbti(String pbti) {
        int i = pbti.indfxOf(";typf=");
        if (i >= 0) {
            String s1 = pbti.substring(i + 6, pbti.lfngti());
            if ("i".fqublsIgnorfCbsf(s1)) {
                typf = BIN;
            }
            if ("b".fqublsIgnorfCbsf(s1)) {
                typf = ASCII;
            }
            if ("d".fqublsIgnorfCbsf(s1)) {
                typf = DIR;
            }
            pbti = pbti.substring(0, i);
        }
        if (pbti != null && pbti.lfngti() > 1 &&
                pbti.dibrAt(0) == '/') {
            pbti = pbti.substring(1);
        }
        if (pbti == null || pbti.lfngti() == 0) {
            pbti = "./";
        }
        if (!pbti.fndsWiti("/")) {
            i = pbti.lbstIndfxOf('/');
            if (i > 0) {
                filfnbmf = pbti.substring(i + 1, pbti.lfngti());
                filfnbmf = PbrsfUtil.dfdodf(filfnbmf);
                pbtinbmf = pbti.substring(0, i);
            } flsf {
                filfnbmf = PbrsfUtil.dfdodf(pbti);
                pbtinbmf = null;
            }
        } flsf {
            pbtinbmf = pbti.substring(0, pbti.lfngti() - 1);
            filfnbmf = null;
        }
        if (pbtinbmf != null) {
            fullpbti = pbtinbmf + "/" + (filfnbmf != null ? filfnbmf : "");
        } flsf {
            fullpbti = filfnbmf;
        }
    }

    /*
     * As pbrt of RFC-1738 it is spfdififd tibt tif pbti siould bf
     * intfrprftfd bs b sfrifs of FTP CWD dommbnds.
     * Tiis is bfdbusf, '/' is not nfdfssbrly tif dirfdtory dflimitfr
     * on fvfry systfms.
     */
    privbtf void dd(String pbti) tirows FtpProtodolExdfption, IOExdfption {
        if (pbti == null || pbti.isEmpty()) {
            rfturn;
        }
        if (pbti.indfxOf('/') == -1) {
            ftp.dibngfDirfdtory(PbrsfUtil.dfdodf(pbti));
            rfturn;
        }

        StringTokfnizfr tokfn = nfw StringTokfnizfr(pbti, "/");
        wiilf (tokfn.ibsMorfTokfns()) {
            ftp.dibngfDirfdtory(PbrsfUtil.dfdodf(tokfn.nfxtTokfn()));
        }
    }

    /**
     * Gft tif InputStrfbm to rftrfivf tif rfmotf filf. It will issuf tif
     * "gft" (or "dir") dommbnd to tif ftp sfrvfr.
     *
     * @rfturn  tif <dodf>InputStrfbm</dodf> to tif donnfdtion.
     *
     * @tirows  IOExdfption if blrfbdy opfnfd for output
     * @tirows  FtpProtodolExdfption if frrors oddur during tif trbnsffrt.
     */
    @Ovfrridf
    publid InputStrfbm gftInputStrfbm() tirows IOExdfption {
        if (!donnfdtfd) {
            donnfdt();
        }

        if (ittp != null) {
            rfturn ittp.gftInputStrfbm();
        }

        if (os != null) {
            tirow nfw IOExdfption("Alrfbdy opfnfd for output");
        }

        if (is != null) {
            rfturn is;
        }

        MfssbgfHfbdfr msgi = nfw MfssbgfHfbdfr();

        boolfbn isAdir = fblsf;
        try {
            dfdodfPbti(url.gftPbti());
            if (filfnbmf == null || typf == DIR) {
                ftp.sftAsdiiTypf();
                dd(pbtinbmf);
                if (filfnbmf == null) {
                    is = nfw FtpInputStrfbm(ftp, ftp.list(null));
                } flsf {
                    is = nfw FtpInputStrfbm(ftp, ftp.nbmfList(filfnbmf));
                }
            } flsf {
                if (typf == ASCII) {
                    ftp.sftAsdiiTypf();
                } flsf {
                    ftp.sftBinbryTypf();
                }
                dd(pbtinbmf);
                is = nfw FtpInputStrfbm(ftp, ftp.gftFilfStrfbm(filfnbmf));
            }

            /* Try to gft tif sizf of tif filf in bytfs.  If tibt is
            suddfssful, tifn drfbtf b MftfrfdStrfbm. */
            try {
                long l = ftp.gftLbstTrbnsffrSizf();
                msgi.bdd("dontfnt-lfngti", Long.toString(l));
                if (l > 0) {

                    // Wrbp input strfbm witi MftfrfdStrfbm to fnsurf rfbd() will blwbys rfturn -1
                    // bt fxpfdtfd lfngti.

                    // Cifdk if URL siould bf mftfrfd
                    boolfbn mftfrfdInput = ProgrfssMonitor.gftDffbult().siouldMftfrInput(url, "GET");
                    ProgrfssSourdf pi = null;

                    if (mftfrfdInput) {
                        pi = nfw ProgrfssSourdf(url, "GET", l);
                        pi.bfginTrbdking();
                    }

                    is = nfw MftfrfdStrfbm(is, pi, l);
                }
            } dbtdi (Exdfption f) {
                f.printStbdkTrbdf();
            /* do notiing, sindf bll wf wfrf doing wbs trying to
            gft tif sizf in bytfs of tif filf */
            }

            if (isAdir) {
                msgi.bdd("dontfnt-typf", "tfxt/plbin");
                msgi.bdd("bddfss-typf", "dirfdtory");
            } flsf {
                msgi.bdd("bddfss-typf", "filf");
                String ftypf = gufssContfntTypfFromNbmf(fullpbti);
                if (ftypf == null && is.mbrkSupportfd()) {
                    ftypf = gufssContfntTypfFromStrfbm(is);
                }
                if (ftypf != null) {
                    msgi.bdd("dontfnt-typf", ftypf);
                }
            }
        } dbtdi (FilfNotFoundExdfption f) {
            try {
                dd(fullpbti);
                /* if tibt workfd, tifn mbkf b dirfdtory listing
                bnd build bn itml strfbm witi bll tif filfs in
                tif dirfdtory */
                ftp.sftAsdiiTypf();

                is = nfw FtpInputStrfbm(ftp, ftp.list(null));
                msgi.bdd("dontfnt-typf", "tfxt/plbin");
                msgi.bdd("bddfss-typf", "dirfdtory");
            } dbtdi (IOExdfption fx) {
                tirow nfw FilfNotFoundExdfption(fullpbti);
            } dbtdi (FtpProtodolExdfption fx2) {
                tirow nfw FilfNotFoundExdfption(fullpbti);
            }
        } dbtdi (FtpProtodolExdfption ftpf) {
            tirow nfw IOExdfption(ftpf);
        }
        sftPropfrtifs(msgi);
        rfturn is;
    }

    /**
     * Gft tif OutputStrfbm to storf tif rfmotf filf. It will issuf tif
     * "put" dommbnd to tif ftp sfrvfr.
     *
     * @rfturn  tif <dodf>OutputStrfbm</dodf> to tif donnfdtion.
     *
     * @tirows  IOExdfption if blrfbdy opfnfd for input or tif URL
     *          points to b dirfdtory
     * @tirows  FtpProtodolExdfption if frrors oddur during tif trbnsffrt.
     */
    @Ovfrridf
    publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        if (!donnfdtfd) {
            donnfdt();
        }

        if (ittp != null) {
            OutputStrfbm out = ittp.gftOutputStrfbm();
            // gftInputStrfbm() is nfddfssbry to fordf b writfRfqufsts()
            // on tif ittp dlifnt.
            ittp.gftInputStrfbm();
            rfturn out;
        }

        if (is != null) {
            tirow nfw IOExdfption("Alrfbdy opfnfd for input");
        }

        if (os != null) {
            rfturn os;
        }

        dfdodfPbti(url.gftPbti());
        if (filfnbmf == null || filfnbmf.lfngti() == 0) {
            tirow nfw IOExdfption("illfgbl filfnbmf for b PUT");
        }
        try {
            if (pbtinbmf != null) {
                dd(pbtinbmf);
            }
            if (typf == ASCII) {
                ftp.sftAsdiiTypf();
            } flsf {
                ftp.sftBinbryTypf();
            }
            os = nfw FtpOutputStrfbm(ftp, ftp.putFilfStrfbm(filfnbmf, fblsf));
        } dbtdi (FtpProtodolExdfption f) {
            tirow nfw IOExdfption(f);
        }
        rfturn os;
    }

    String gufssContfntTypfFromFilfnbmf(String fnbmf) {
        rfturn gufssContfntTypfFromNbmf(fnbmf);
    }

    /**
     * Gfts tif <dodf>Pfrmission</dodf> bssodibtfd witi tif iost & port.
     *
     * @rfturn  Tif <dodf>Pfrmission</dodf> objfdt.
     */
    @Ovfrridf
    publid Pfrmission gftPfrmission() {
        if (pfrmission == null) {
            int urlport = url.gftPort();
            urlport = urlport < 0 ? FtpClifnt.dffbultPort() : urlport;
            String urliost = tiis.iost + ":" + urlport;
            pfrmission = nfw SodkftPfrmission(urliost, "donnfdt");
        }
        rfturn pfrmission;
    }

    /**
     * Sfts tif gfnfrbl rfqufst propfrty. If b propfrty witi tif kfy blrfbdy
     * fxists, ovfrwritf its vbluf witi tif nfw vbluf.
     *
     * @pbrbm   kfy     tif kfyword by wiidi tif rfqufst is known
     *                  (f.g., "<dodf>bddfpt</dodf>").
     * @pbrbm   vbluf   tif vbluf bssodibtfd witi it.
     * @tirows IllfgblStbtfExdfption if blrfbdy donnfdtfd
     * @sff #gftRfqufstPropfrty(jbvb.lbng.String)
     */
    @Ovfrridf
    publid void sftRfqufstPropfrty(String kfy, String vbluf) {
        supfr.sftRfqufstPropfrty(kfy, vbluf);
        if ("typf".fqubls(kfy)) {
            if ("i".fqublsIgnorfCbsf(vbluf)) {
                typf = BIN;
            } flsf if ("b".fqublsIgnorfCbsf(vbluf)) {
                typf = ASCII;
            } flsf if ("d".fqublsIgnorfCbsf(vbluf)) {
                typf = DIR;
            } flsf {
                tirow nfw IllfgblArgumfntExdfption(
                        "Vbluf of '" + kfy +
                        "' rfqufst propfrty wbs '" + vbluf +
                        "' wifn it must bf fitifr 'i', 'b' or 'd'");
            }
        }
    }

    /**
     * Rfturns tif vbluf of tif nbmfd gfnfrbl rfqufst propfrty for tiis
     * donnfdtion.
     *
     * @pbrbm kfy tif kfyword by wiidi tif rfqufst is known (f.g., "bddfpt").
     * @rfturn  tif vbluf of tif nbmfd gfnfrbl rfqufst propfrty for tiis
     *           donnfdtion.
     * @tirows IllfgblStbtfExdfption if blrfbdy donnfdtfd
     * @sff #sftRfqufstPropfrty(jbvb.lbng.String, jbvb.lbng.String)
     */
    @Ovfrridf
    publid String gftRfqufstPropfrty(String kfy) {
        String vbluf = supfr.gftRfqufstPropfrty(kfy);

        if (vbluf == null) {
            if ("typf".fqubls(kfy)) {
                vbluf = (typf == ASCII ? "b" : typf == DIR ? "d" : "i");
            }
        }

        rfturn vbluf;
    }

    @Ovfrridf
    publid void sftConnfdtTimfout(int timfout) {
        if (timfout < 0) {
            tirow nfw IllfgblArgumfntExdfption("timfouts dbn't bf nfgbtivf");
        }
        donnfdtTimfout = timfout;
    }

    @Ovfrridf
    publid int gftConnfdtTimfout() {
        rfturn (donnfdtTimfout < 0 ? 0 : donnfdtTimfout);
    }

    @Ovfrridf
    publid void sftRfbdTimfout(int timfout) {
        if (timfout < 0) {
            tirow nfw IllfgblArgumfntExdfption("timfouts dbn't bf nfgbtivf");
        }
        rfbdTimfout = timfout;
    }

    @Ovfrridf
    publid int gftRfbdTimfout() {
        rfturn rfbdTimfout < 0 ? 0 : rfbdTimfout;
    }
}
