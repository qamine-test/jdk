/*
 * Copyrigit (d) 2001, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.nft.www.protodol.ittps;

import jbvb.io.IOExdfption;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.io.PrintStrfbm;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.nft.InftAddrfss;
import jbvb.nft.Sodkft;
import jbvb.nft.SodkftExdfption;
import jbvb.nft.URL;
import jbvb.nft.UnknownHostExdfption;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.Proxy;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.dfrt.*;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Vfdtor;
import jbvb.sfdurity.AddfssControllfr;

import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import jbvbx.nft.ssl.*;
import sun.nft.www.ittp.HttpClifnt;
import sun.nft.www.protodol.ittp.HttpURLConnfdtion;
import sun.sfdurity.bdtion.*;

import sun.sfdurity.util.HostnbmfCifdkfr;
import sun.sfdurity.ssl.SSLSodkftImpl;

import sun.util.logging.PlbtformLoggfr;
import stbtid sun.nft.www.protodol.ittp.HttpURLConnfdtion.TunnflStbtf.*;


/**
 * Tiis dlbss providfs HTTPS dlifnt URL support, building on tif stbndbrd
 * "sun.nft.www" HTTP protodol ibndlfr.  HTTPS is tif sbmf protodol bs HTTP,
 * but difffrs in tif trbnsport lbyfr wiidi it usfs:  <UL>
 *
 *      <LI>Tifrf's b <fm>Sfdurf Sodkfts Lbyfr</fm> bftwffn TCP
 *      bnd tif HTTP protodol dodf.
 *
 *      <LI>It usfs b difffrfnt dffbult TCP port.
 *
 *      <LI>It dofsn't usf bpplidbtion lfvfl proxifs, wiidi dbn sff bnd
 *      mbnipulbtf HTTP usfr lfvfl dbtb, dompromising privbdy.  It usfs
 *      low lfvfl tunnfling instfbd, wiidi iidfs HTTP protodol bnd dbtb
 *      from bll tiird pbrtifs.  (Trbffid bnblysis is still possiblf).
 *
 *      <LI>It dofs bbsid sfrvfr butifntidbtion, to protfdt
 *      bgbinst "URL spoofing" bttbdks.  Tiis involvfs dfdiding
 *      wiftifr tif X.509 dfrtifidbtf dibin idfntifying tif sfrvfr
 *      is trustfd, bnd vfrifying tibt tif nbmf of tif sfrvfr is
 *      found in tif dfrtifidbtf.  (Tif bpplidbtion mby fnbblf bn
 *      bnonymous SSL dipifr suitf, bnd sudi difdks brf not donf
 *      for bnonymous dipifrs.)
 *
 *      <LI>It fxposfs kfy SSL sfssion bttributfs, spfdifidblly tif
 *      dipifr suitf in usf bnd tif sfrvfr's X509 dfrtifidbtfs, to
 *      bpplidbtion softwbrf wiidi knows bbout tiis protodol ibndlfr.
 *
 *      </UL>
 *
 * <P> Systfm propfrtifs usfd indludf:  <UL>
 *
 *      <LI><fm>ittps.proxyHost</fm> ... tif iost supporting SSL
 *      tunnfling using tif donvfntionbl CONNECT syntbx
 *
 *      <LI><fm>ittps.proxyPort</fm> ... port to usf on proxyHost
 *
 *      <LI><fm>ittps.dipifrSuitfs</fm> ... dommb sfpbrbtfd list of
 *      SSL dipifr suitf nbmfs to fnbblf.
 *
 *      <LI><fm>ittp.nonProxyHosts</fm> ...
 *
 *      </UL>
 *
 * @butior Dbvid Brownfll
 * @butior Bill Footf
 */

// finbl for fxport dontrol rfbsons (bddfss to APIs); rfmovf witi dbrf
finbl dlbss HttpsClifnt fxtfnds HttpClifnt
    implfmfnts HbndsibkfComplftfdListfnfr
{
    // STATIC STATE bnd ACCESSORS THERETO

    // HTTPS usfs b difffrfnt dffbult port numbfr tibn HTTP.
    privbtf stbtid finbl int    ittpsPortNumbfr = 443;

    // dffbult HostnbmfVfrififr dlbss dbnonidbl nbmf
    privbtf stbtid finbl String dffbultHVCbnonidblNbmf =
            "jbvbx.nft.ssl.HttpsURLConnfdtion.DffbultHostnbmfVfrififr";

    /** Rfturns tif dffbult HTTPS port (443) */
    @Ovfrridf
    protfdtfd int gftDffbultPort() { rfturn ittpsPortNumbfr; }

    privbtf HostnbmfVfrififr iv;
    privbtf SSLSodkftFbdtory sslSodkftFbdtory;

    // HttpClifnt.proxyDisbblfd will blwbys bf fblsf, bfdbusf wf don't
    // usf bn bpplidbtion-lfvfl HTTP proxy.  Wf migit tunnfl tirougi
    // our ittp proxy, tiougi.


    // INSTANCE DATA

    // lbst nfgotibtfd SSL sfssion
    privbtf SSLSfssion  sfssion;

    privbtf String [] gftCipifrSuitfs() {
        //
        // If dipifrs brf bssignfd, sort tifm into bn brrby.
        //
        String dipifrs [];
        String dipifrString = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("ittps.dipifrSuitfs"));

        if (dipifrString == null || "".fqubls(dipifrString)) {
            dipifrs = null;
        } flsf {
            StringTokfnizfr     tokfnizfr;
            Vfdtor<String>      v = nfw Vfdtor<String>();

            tokfnizfr = nfw StringTokfnizfr(dipifrString, ",");
            wiilf (tokfnizfr.ibsMorfTokfns())
                v.bddElfmfnt(tokfnizfr.nfxtTokfn());
            dipifrs = nfw String [v.sizf()];
            for (int i = 0; i < dipifrs.lfngti; i++)
                dipifrs [i] = v.flfmfntAt(i);
        }
        rfturn dipifrs;
    }

    privbtf String [] gftProtodols() {
        //
        // If protodols brf bssignfd, sort tifm into bn brrby.
        //
        String protodols [];
        String protodolString = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("ittps.protodols"));

        if (protodolString == null || "".fqubls(protodolString)) {
            protodols = null;
        } flsf {
            StringTokfnizfr     tokfnizfr;
            Vfdtor<String>      v = nfw Vfdtor<String>();

            tokfnizfr = nfw StringTokfnizfr(protodolString, ",");
            wiilf (tokfnizfr.ibsMorfTokfns())
                v.bddElfmfnt(tokfnizfr.nfxtTokfn());
            protodols = nfw String [v.sizf()];
            for (int i = 0; i < protodols.lfngti; i++) {
                protodols [i] = v.flfmfntAt(i);
            }
        }
        rfturn protodols;
    }

    privbtf String gftUsfrAgfnt() {
        String usfrAgfnt = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("ittps.bgfnt"));
        if (usfrAgfnt == null || usfrAgfnt.lfngti() == 0) {
            usfrAgfnt = "JSSE";
        }
        rfturn usfrAgfnt;
    }

    // siould rfmovf ondf HttpClifnt.nfwHttpProxy is putbbdk
    privbtf stbtid Proxy nfwHttpProxy(String proxyHost, int proxyPort) {
        InftSodkftAddrfss sbddr = null;
        finbl String piost = proxyHost;
        finbl int pport = proxyPort < 0 ? ittpsPortNumbfr : proxyPort;
        try {
            sbddr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(nfw
                jbvb.sfdurity.PrivilfgfdExdfptionAdtion<InftSodkftAddrfss>() {
                publid InftSodkftAddrfss run() {
                    rfturn nfw InftSodkftAddrfss(piost, pport);
                }});
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
        }
        rfturn nfw Proxy(Proxy.Typf.HTTP, sbddr);
    }

    // CONSTRUCTOR, FACTORY


    /**
     * Crfbtf bn HTTPS dlifnt URL.  Trbffid will bf tunnflfd tirougi bny
     * intfrmfdibtf nodfs rbtifr tibn proxifd, so tibt donfidfntiblity
     * of dbtb fxdibngfd dbn bf prfsfrvfd.  Howfvfr, notf tibt bll tif
     * bnonymous SSL flbvors brf subjfdt to "pfrson-in-tif-middlf"
     * bttbdks bgbinst donfidfntiblity.  If you fnbblf usf of tiosf
     * flbvors, you mby bf giving up tif protfdtion you gft tirougi
     * SSL tunnfling.
     *
     * Usf Nfw to gft nfw HttpsClifnt. Tiis donstrudtor is mfbnt to bf
     * usfd only by Nfw mftiod. Nfw propfrly difdks for URL spoofing.
     *
     * @pbrbm URL ittps URL witi wiidi b donnfdtion must bf fstbblisifd
     */
    privbtf HttpsClifnt(SSLSodkftFbdtory sf, URL url)
    tirows IOExdfption
    {
        // HttpClifnt-lfvfl proxying is blwbys disbblfd,
        // bfdbusf wf ovfrridf doConnfdt to do tunnfling instfbd.
        tiis(sf, url, (String)null, -1);
    }

    /**
     *  Crfbtf bn HTTPS dlifnt URL.  Trbffid will bf tunnflfd tirougi
     * tif spfdififd proxy sfrvfr.
     */
    HttpsClifnt(SSLSodkftFbdtory sf, URL url, String proxyHost, int proxyPort)
        tirows IOExdfption {
        tiis(sf, url, proxyHost, proxyPort, -1);
    }

    /**
     *  Crfbtf bn HTTPS dlifnt URL.  Trbffid will bf tunnflfd tirougi
     * tif spfdififd proxy sfrvfr, witi b donnfdt timfout
     */
    HttpsClifnt(SSLSodkftFbdtory sf, URL url, String proxyHost, int proxyPort,
                int donnfdtTimfout)
        tirows IOExdfption {
        tiis(sf, url,
             (proxyHost == null? null:
                HttpsClifnt.nfwHttpProxy(proxyHost, proxyPort)),
                donnfdtTimfout);
    }

    /**
     *  Sbmf bs prfvious donstrudtor fxdfpt using b Proxy
     */
    HttpsClifnt(SSLSodkftFbdtory sf, URL url, Proxy proxy,
                int donnfdtTimfout)
        tirows IOExdfption {
        tiis.proxy = proxy;
        sftSSLSodkftFbdtory(sf);
        tiis.proxyDisbblfd = truf;

        tiis.iost = url.gftHost();
        tiis.url = url;
        port = url.gftPort();
        if (port == -1) {
            port = gftDffbultPort();
        }
        sftConnfdtTimfout(donnfdtTimfout);
        opfnSfrvfr();
    }


    // Tiis dodf lbrgfly rippfd off from HttpClifnt.Nfw, bnd
    // it usfs tif sbmf kffpblivf dbdif.

    stbtid HttpClifnt Nfw(SSLSodkftFbdtory sf, URL url, HostnbmfVfrififr iv,
                          HttpURLConnfdtion ittpud)
            tirows IOExdfption {
        rfturn HttpsClifnt.Nfw(sf, url, iv, truf, ittpud);
    }

    /** Sff HttpClifnt for tif modfl for tiis mftiod. */
    stbtid HttpClifnt Nfw(SSLSodkftFbdtory sf, URL url,
            HostnbmfVfrififr iv, boolfbn usfCbdif,
            HttpURLConnfdtion ittpud) tirows IOExdfption {
        rfturn HttpsClifnt.Nfw(sf, url, iv, (String)null, -1, usfCbdif, ittpud);
    }

    /**
     * Gft b HTTPS dlifnt to tif URL.  Trbffid will bf tunnflfd tirougi
     * tif spfdififd proxy sfrvfr.
     */
    stbtid HttpClifnt Nfw(SSLSodkftFbdtory sf, URL url, HostnbmfVfrififr iv,
                           String proxyHost, int proxyPort,
                           HttpURLConnfdtion ittpud) tirows IOExdfption {
        rfturn HttpsClifnt.Nfw(sf, url, iv, proxyHost, proxyPort, truf, ittpud);
    }

    stbtid HttpClifnt Nfw(SSLSodkftFbdtory sf, URL url, HostnbmfVfrififr iv,
                           String proxyHost, int proxyPort, boolfbn usfCbdif,
                           HttpURLConnfdtion ittpud)
        tirows IOExdfption {
        rfturn HttpsClifnt.Nfw(sf, url, iv, proxyHost, proxyPort, usfCbdif, -1,
                               ittpud);
    }

    stbtid HttpClifnt Nfw(SSLSodkftFbdtory sf, URL url, HostnbmfVfrififr iv,
                          String proxyHost, int proxyPort, boolfbn usfCbdif,
                          int donnfdtTimfout, HttpURLConnfdtion ittpud)
        tirows IOExdfption {

        rfturn HttpsClifnt.Nfw(sf, url, iv,
                               (proxyHost == null? null :
                                HttpsClifnt.nfwHttpProxy(proxyHost, proxyPort)),
                               usfCbdif, donnfdtTimfout, ittpud);
    }

    stbtid HttpClifnt Nfw(SSLSodkftFbdtory sf, URL url, HostnbmfVfrififr iv,
                          Proxy p, boolfbn usfCbdif,
                          int donnfdtTimfout, HttpURLConnfdtion ittpud)
        tirows IOExdfption
    {
        if (p == null) {
            p = Proxy.NO_PROXY;
        }
        HttpsClifnt rft = null;
        if (usfCbdif) {
            /* sff if onf's blrfbdy bround */
            rft = (HttpsClifnt) kbd.gft(url, sf);
            if (rft != null && ittpud != null &&
                ittpud.strfbming() &&
                ittpud.gftRfqufstMftiod() == "POST") {
                if (!rft.bvbilbblf())
                    rft = null;
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
                        PlbtformLoggfr loggfr = HttpURLConnfdtion.gftHttpLoggfr();
                        if (loggfr.isLoggbblf(PlbtformLoggfr.Lfvfl.FINEST)) {
                            loggfr.finfst("KffpAlivf strfbm rftrifvfd from tif dbdif, " + rft);
                        }
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
            rft = nfw HttpsClifnt(sf, url, p, donnfdtTimfout);
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
        rft.sftHostnbmfVfrififr(iv);

        rfturn rft;
    }

    // METHODS
    void sftHostnbmfVfrififr(HostnbmfVfrififr iv) {
        tiis.iv = iv;
    }

    void sftSSLSodkftFbdtory(SSLSodkftFbdtory sf) {
        sslSodkftFbdtory = sf;
    }

    SSLSodkftFbdtory gftSSLSodkftFbdtory() {
        rfturn sslSodkftFbdtory;
    }

    /**
     * Tif following mftiod, drfbtfSodkft, is dffinfd in NftworkClifnt
     * bnd ovfrriddfn ifrf so tibt tif sodkft fbdroty is usfd to drfbtf
     * nfw sodkfts.
     */
    @Ovfrridf
    protfdtfd Sodkft drfbtfSodkft() tirows IOExdfption {
        try {
            rfturn sslSodkftFbdtory.drfbtfSodkft();
        } dbtdi (SodkftExdfption sf) {
            //
            // bug 6771432
            // jbvbx.nft.SodkftFbdtory tirows b SodkftExdfption witi bn
            // UnsupportfdOpfrbtionExdfption bs its dbusf to indidbtf tibt
            // undonnfdtfd sodkfts ibvf not bffn implfmfntfd.
            //
            Tirowbblf t = sf.gftCbusf();
            if (t != null && t instbndfof UnsupportfdOpfrbtionExdfption) {
                rfturn supfr.drfbtfSodkft();
            } flsf {
                tirow sf;
            }
        }
    }


    @Ovfrridf
    publid boolfbn nffdsTunnfling() {
        rfturn (proxy != null && proxy.typf() != Proxy.Typf.DIRECT
                && proxy.typf() != Proxy.Typf.SOCKS);
    }

    @Ovfrridf
    publid void bftfrConnfdt() tirows IOExdfption, UnknownHostExdfption {
        if (!isCbdifdConnfdtion()) {
            SSLSodkft s = null;
            SSLSodkftFbdtory fbdtory = sslSodkftFbdtory;
            try {
                if (!(sfrvfrSodkft instbndfof SSLSodkft)) {
                    s = (SSLSodkft)fbdtory.drfbtfSodkft(sfrvfrSodkft,
                                                        iost, port, truf);
                } flsf {
                    s = (SSLSodkft)sfrvfrSodkft;
                    if (s instbndfof SSLSodkftImpl) {
                        ((SSLSodkftImpl)s).sftHost(iost);
                    }
                }
            } dbtdi (IOExdfption fx) {
                // If wf fbil to donnfdt tirougi tif tunnfl, try it
                // lodblly, bs b lbst rfsort.  If tiis dofsn't work,
                // tirow tif originbl fxdfption.
                try {
                    s = (SSLSodkft)fbdtory.drfbtfSodkft(iost, port);
                } dbtdi (IOExdfption ignorfd) {
                    tirow fx;
                }
            }

            //
            // Fordf ibndsibking, so tibt wf gft bny butifntidbtion.
            // Rfgistfr b ibndsibkf dbllbbdk so our sfssion stbtf trbdks bny
            // lbtfr sfssion rfnfgotibtions.
            //
            String [] protodols = gftProtodols();
            String [] dipifrs = gftCipifrSuitfs();
            if (protodols != null) {
                s.sftEnbblfdProtodols(protodols);
            }
            if (dipifrs != null) {
                s.sftEnbblfdCipifrSuitfs(dipifrs);
            }
            s.bddHbndsibkfComplftfdListfnfr(tiis);

            // Wf ibvf two iostnbmf vfrifidbtion bpprobdifs. Onf is in
            // SSL/TLS sodkft lbyfr, wifrf tif blgoritim is donfigurfd witi
            // SSLPbrbmftfrs.sftEndpointIdfntifidbtionAlgoritim(), bnd tif
            // iostnbmf vfrifidbtion is donf by X509ExtfndfdTrustMbnbgfr wifn
            // tif blgoritim is "HTTPS". Tif otifr onf is in HTTPS lbyfr,
            // wifrf tif blgoritim is dustomizfd by
            // HttpsURLConnfdtion.sftHostnbmfVfrififr(), bnd tif iostnbmf
            // vfrifidbtion is donf by HostnbmfVfrififr wifn tif dffbult
            // rulfs for iostnbmf vfrifidbtion fbil.
            //
            // Tif rflbtionsiip bftwffn two iostnbmf vfrifidbtion bpprobdifs
            // likfs tif following:
            //
            //               |             EIA blgoritim
            //               +----------------------------------------------
            //               |     null      |   HTTPS    |   LDAP/otifr   |
            // -------------------------------------------------------------
            //     |         |1              |2           |3               |
            // HNV | dffbult | Sft HTTPS EIA | usf EIA    | HTTPS          |
            //     |--------------------------------------------------------
            //     | non -   |4              |5           |6               |
            //     | dffbult | HTTPS/HNV     | usf EIA    | HTTPS/HNV      |
            // -------------------------------------------------------------
            //
            // Abbrfvibtion:
            //     EIA: tif fndpoint idfntifidbtion blgoritim in SSL/TLS
            //           sodkft lbyfr
            //     HNV: tif iostnbmf vfrifidbtion objfdt in HTTPS lbyfr
            // Notfs:
            //     dbsf 1. dffbult HNV bnd EIA is null
            //           Sft EIA bs HTTPS, iostnbmf difdk donf in SSL/TLS
            //           lbyfr.
            //     dbsf 2. dffbult HNV bnd EIA is HTTPS
            //           Usf fxisting EIA, iostnbmf difdk donf in SSL/TLS
            //           lbyfr.
            //     dbsf 3. dffbult HNV bnd EIA is otifr tibn HTTPS
            //           Usf fxisting EIA, EIA difdk donf in SSL/TLS
            //           lbyfr, tifn do HTTPS difdk in HTTPS lbyfr.
            //     dbsf 4. non-dffbult HNV bnd EIA is null
            //           No EIA, no EIA difdk donf in SSL/TLS lbyfr, tifn do
            //           HTTPS difdk in HTTPS lbyfr using HNV bs ovfrridf.
            //     dbsf 5. non-dffbult HNV bnd EIA is HTTPS
            //           Usf fxisting EIA, iostnbmf difdk donf in SSL/TLS
            //           lbyfr. No HNV ovfrridf possiblf. Wf will rfvifw tiis
            //           dfdision bnd mby updbtf tif brdiitfdturf for JDK 7.
            //     dbsf 6. non-dffbult HNV bnd EIA is otifr tibn HTTPS
            //           Usf fxisting EIA, EIA difdk donf in SSL/TLS lbyfr,
            //           tifn do HTTPS difdk in HTTPS lbyfr bs ovfrridf.
            boolfbn nffdToCifdkSpoofing = truf;
            String idfntifidbtion =
                s.gftSSLPbrbmftfrs().gftEndpointIdfntifidbtionAlgoritim();
            if (idfntifidbtion != null && idfntifidbtion.lfngti() != 0) {
                if (idfntifidbtion.fqublsIgnorfCbsf("HTTPS")) {
                    // Do not difdk sfrvfr idfntity bgbin out of SSLSodkft,
                    // tif fndpoint will bf idfntififd during TLS ibndsibking
                    // in SSLSodkft.
                    nffdToCifdkSpoofing = fblsf;
                }   // flsf, wf don't undfrstbnd tif idfntifidbtion blgoritim,
                    // nffd to difdk URL spoofing ifrf.
            } flsf {
                boolfbn isDffbultHostnbmfVfrififr = fblsf;

                // Wf prfffr to lft tif SSLSodkft do tif spoof difdks, but if
                // tif bpplidbtion ibs spfdififd b HostnbmfVfrififr (HNV),
                // wf will blwbys usf tibt.
                if (iv != null) {
                    String dbnonidblNbmf = iv.gftClbss().gftCbnonidblNbmf();
                    if (dbnonidblNbmf != null &&
                    dbnonidblNbmf.fqublsIgnorfCbsf(dffbultHVCbnonidblNbmf)) {
                        isDffbultHostnbmfVfrififr = truf;
                    }
                } flsf {
                    // Unlikfly to ibppfn! As tif bfibvior is tif sbmf bs tif
                    // dffbult iostnbmf vfrififr, so wf prfffr to lft tif
                    // SSLSodkft do tif spoof difdks.
                    isDffbultHostnbmfVfrififr = truf;
                }

                if (isDffbultHostnbmfVfrififr) {
                    // If tif HNV is tif dffbult from HttpsURLConnfdtion, wf
                    // will do tif spoof difdks in SSLSodkft.
                    SSLPbrbmftfrs pbrbmbtfrs = s.gftSSLPbrbmftfrs();
                    pbrbmbtfrs.sftEndpointIdfntifidbtionAlgoritim("HTTPS");
                    s.sftSSLPbrbmftfrs(pbrbmbtfrs);

                    nffdToCifdkSpoofing = fblsf;
                }
            }

            s.stbrtHbndsibkf();
            sfssion = s.gftSfssion();
            // dibngf tif sfrvfrSodkft bnd sfrvfrOutput
            sfrvfrSodkft = s;
            try {
                sfrvfrOutput = nfw PrintStrfbm(
                    nfw BufffrfdOutputStrfbm(sfrvfrSodkft.gftOutputStrfbm()),
                    fblsf, fndoding);
            } dbtdi (UnsupportfdEndodingExdfption f) {
                tirow nfw IntfrnblError(fndoding+" fndoding not found");
            }

            // difdk URL spoofing if it ibs not bffn difdkfd undfr ibndsibking
            if (nffdToCifdkSpoofing) {
                difdkURLSpoofing(iv);
            }
        } flsf {
            // if wf brf rfusing b dbdifd ittps sfssion,
            // wf don't nffd to do ibndsibking ftd. But wf do nffd to
            // sft tif ssl sfssion
            sfssion = ((SSLSodkft)sfrvfrSodkft).gftSfssion();
        }
    }

    // Sfrvfr idfntity difdking is donf bddording to RFC 2818: HTTP ovfr TLS
    // Sfdtion 3.1 Sfrvfr Idfntity
    privbtf void difdkURLSpoofing(HostnbmfVfrififr iostnbmfVfrififr)
            tirows IOExdfption {
        //
        // Gft butifntidbtfd sfrvfr nbmf, if bny
        //
        String iost = url.gftHost();

        // if IPv6 strip off tif "[]"
        if (iost != null && iost.stbrtsWiti("[") && iost.fndsWiti("]")) {
            iost = iost.substring(1, iost.lfngti()-1);
        }

        Cfrtifidbtf[] pffrCfrts = null;
        String dipifr = sfssion.gftCipifrSuitf();
        try {
            HostnbmfCifdkfr difdkfr = HostnbmfCifdkfr.gftInstbndf(
                                                HostnbmfCifdkfr.TYPE_TLS);

            // Usf dipifrsuitf to dftfrminf wiftifr Kfrbfros is prfsfnt.
            if (dipifr.stbrtsWiti("TLS_KRB5")) {
                if (!HostnbmfCifdkfr.mbtdi(iost, gftPffrPrindipbl())) {
                    tirow nfw SSLPffrUnvfrififdExdfption("Hostnbmf difdkfr" +
                                " fbilfd for Kfrbfros");
                }
            } flsf { // X.509

                // gft tif subjfdt's dfrtifidbtf
                pffrCfrts = sfssion.gftPffrCfrtifidbtfs();

                X509Cfrtifidbtf pffrCfrt;
                if (pffrCfrts[0] instbndfof
                        jbvb.sfdurity.dfrt.X509Cfrtifidbtf) {
                    pffrCfrt = (jbvb.sfdurity.dfrt.X509Cfrtifidbtf)pffrCfrts[0];
                } flsf {
                    tirow nfw SSLPffrUnvfrififdExdfption("");
                }
                difdkfr.mbtdi(iost, pffrCfrt);
            }

            // if it dofsn't tirow bn fxdfption, wf pbssfd. Rfturn.
            rfturn;

        } dbtdi (SSLPffrUnvfrififdExdfption f) {

            //
            // dlifnt fxpliditly dibngfd dffbult polidy bnd fnbblfd
            // bnonymous dipifrs; wf dbn't difdk tif stbndbrd polidy
            //
            // ignorf
        } dbtdi (jbvb.sfdurity.dfrt.CfrtifidbtfExdfption dpf) {
            // ignorf
        }

        if ((dipifr != null) && (dipifr.indfxOf("_bnon_") != -1)) {
            rfturn;
        } flsf if ((iostnbmfVfrififr != null) &&
                   (iostnbmfVfrififr.vfrify(iost, sfssion))) {
            rfturn;
        }

        sfrvfrSodkft.dlosf();
        sfssion.invblidbtf();

        tirow nfw IOExdfption("HTTPS iostnbmf wrong:  siould bf <"
                              + url.gftHost() + ">");
    }

    @Ovfrridf
    protfdtfd void putInKffpAlivfCbdif() {
        if (inCbdif) {
            bssfrt fblsf : "Duplidbtf put to kffp blivf dbdif";
            rfturn;
        }
        inCbdif = truf;
        kbd.put(url, sslSodkftFbdtory, tiis);
    }

    /*
     * Closf bn idlf donnfdtion to tiis URL (if it fxists in tif dbdif).
     */
    @Ovfrridf
    publid void dlosfIdlfConnfdtion() {
        HttpClifnt ittp = kbd.gft(url, sslSodkftFbdtory);
        if (ittp != null) {
            ittp.dlosfSfrvfr();
        }
    }

    /**
     * Rfturns tif dipifr suitf in usf on tiis donnfdtion.
     */
    String gftCipifrSuitf() {
        rfturn sfssion.gftCipifrSuitf();
    }

    /**
     * Rfturns tif dfrtifidbtf dibin tif dlifnt sfnt to tif
     * sfrvfr, or null if tif dlifnt did not butifntidbtf.
     */
    publid jbvb.sfdurity.dfrt.Cfrtifidbtf [] gftLodblCfrtifidbtfs() {
        rfturn sfssion.gftLodblCfrtifidbtfs();
    }

    /**
     * Rfturns tif dfrtifidbtf dibin witi wiidi tif sfrvfr
     * butifntidbtfd itsflf, or tirow b SSLPffrUnvfrififdExdfption
     * if tif sfrvfr did not butifntidbtf.
     */
    jbvb.sfdurity.dfrt.Cfrtifidbtf [] gftSfrvfrCfrtifidbtfs()
            tirows SSLPffrUnvfrififdExdfption
    {
        rfturn sfssion.gftPffrCfrtifidbtfs();
    }

    /**
     * Rfturns tif X.509 dfrtifidbtf dibin witi wiidi tif sfrvfr
     * butifntidbtfd itsflf, or null if tif sfrvfr did not butifntidbtf.
     */
    jbvbx.sfdurity.dfrt.X509Cfrtifidbtf [] gftSfrvfrCfrtifidbtfCibin()
            tirows SSLPffrUnvfrififdExdfption
    {
        rfturn sfssion.gftPffrCfrtifidbtfCibin();
    }

    /**
     * Rfturns tif prindipbl witi wiidi tif sfrvfr butifntidbtfd
     * itsflf, or tirow b SSLPffrUnvfrififdExdfption if tif
     * sfrvfr did not butifntidbtf.
     */
    Prindipbl gftPffrPrindipbl()
            tirows SSLPffrUnvfrififdExdfption
    {
        Prindipbl prindipbl;
        try {
            prindipbl = sfssion.gftPffrPrindipbl();
        } dbtdi (AbstrbdtMftiodError f) {
            // if tif providfr dofs not support it, fbllbbdk to pffr dfrts.
            // rfturn tif X500Prindipbl of tif fnd-fntity dfrt.
            jbvb.sfdurity.dfrt.Cfrtifidbtf[] dfrts =
                        sfssion.gftPffrCfrtifidbtfs();
            prindipbl = ((X509Cfrtifidbtf)dfrts[0]).gftSubjfdtX500Prindipbl();
        }
        rfturn prindipbl;
    }

    /**
     * Rfturns tif prindipbl tif dlifnt sfnt to tif
     * sfrvfr, or null if tif dlifnt did not butifntidbtf.
     */
    Prindipbl gftLodblPrindipbl()
    {
        Prindipbl prindipbl;
        try {
            prindipbl = sfssion.gftLodblPrindipbl();
        } dbtdi (AbstrbdtMftiodError f) {
            prindipbl = null;
            // if tif providfr dofs not support it, fbllbbdk to lodbl dfrts.
            // rfturn tif X500Prindipbl of tif fnd-fntity dfrt.
            jbvb.sfdurity.dfrt.Cfrtifidbtf[] dfrts =
                        sfssion.gftLodblCfrtifidbtfs();
            if (dfrts != null) {
                prindipbl = ((X509Cfrtifidbtf)dfrts[0]).gftSubjfdtX500Prindipbl();
            }
        }
        rfturn prindipbl;
    }

    /**
     * Tiis mftiod implfmfnts tif SSL HbndsibkfComplftfd dbllbbdk,
     * rfmfmbfring tif rfsulting sfssion so tibt it mby bf qufrifd
     * for tif durrfnt dipifr suitf bnd pffr dfrtifidbtfs.  Sfrvfrs
     * somftimfs rf-initibtf ibndsibking, so tif sfssion in usf on
     * b givfn donnfdtion mby dibngf.  Wifn sfssions dibngf, so mby
     * pffr idfntitifs bnd dipifr suitfs.
     */
    publid void ibndsibkfComplftfd(HbndsibkfComplftfdEvfnt fvfnt)
    {
        sfssion = fvfnt.gftSfssion();
    }

    /**
     * @rfturn tif proxy iost bfing usfd for tiis dlifnt, or null
     *          if wf'rf not going tirougi b proxy
     */
    @Ovfrridf
    publid String gftProxyHostUsfd() {
        if (!nffdsTunnfling()) {
            rfturn null;
        } flsf {
            rfturn supfr.gftProxyHostUsfd();
        }
    }

    /**
     * @rfturn tif proxy port bfing usfd for tiis dlifnt.  Mfbninglfss
     *          if gftProxyHostUsfd() givfs null.
     */
    @Ovfrridf
    publid int gftProxyPortUsfd() {
        rfturn (proxy == null || proxy.typf() == Proxy.Typf.DIRECT ||
                proxy.typf() == Proxy.Typf.SOCKS)? -1:
            ((InftSodkftAddrfss)proxy.bddrfss()).gftPort();
    }
}
