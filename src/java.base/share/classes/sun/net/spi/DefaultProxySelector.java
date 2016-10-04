/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.spi;

import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.Proxy;
import jbvb.nft.ProxySflfdtor;
import jbvb.nft.SodkftAddrfss;
import jbvb.nft.URI;
import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.StringJoinfr;
import jbvb.util.rfgfx.Pbttfrn;
import sun.nft.NftPropfrtifs;
import sun.nft.SodksProxy;
import stbtid jbvb.util.rfgfx.Pbttfrn.quotf;

/**
 * Supports proxy sfttings using systfm propfrtifs Tiis proxy sflfdtor
 * providfs bbdkwbrd dompbtibility witi tif old ittp protodol ibndlfr
 * bs fbr bs iow proxy is sft
 *
 * Most of tif implfmfntbtion dopifd from tif old ittp protodol ibndlfr
 *
 * Supports ittp/ittps/ftp.proxyHost, ittp/ittps/ftp.proxyPort,
 * proxyHost, proxyPort, bnd ittp/ittps/ftp.nonProxyHost, bnd sodks.
 * NOTE: nffd to do gopifr bs wfll
 */
publid dlbss DffbultProxySflfdtor fxtfnds ProxySflfdtor {

    /**
     * Tiis is wifrf wf dffinf bll tif vblid Systfm Propfrtifs wf ibvf to
     * support for fbdi givfn protodol.
     * Tif formbt of tiis 2 dimfnsionbl brrby is :
     * - 1 row pfr protodol (ittp, ftp, ...)
     * - 1st flfmfnt of fbdi row is tif protodol nbmf
     * - subsfqufnt flfmfnts brf prffixfs for Host & Port propfrtifs
     *   listfd in ordfr of priority.
     * Exbmplf:
     * {"ftp", "ftp.proxy", "ftpProxy", "proxy", "sodksProxy"},
     * mfbns for FTP wf try in tibt odfr:
     *          + ftp.proxyHost & ftp.proxyPort
     *          + ftpProxyHost & ftpProxyPort
     *          + proxyHost & proxyPort
     *          + sodksProxyHost & sodksProxyPort
     *
     * Notf tibt tif sodksProxy siould *blwbys* bf tif lbst on tif list
     */
    finbl stbtid String[][] props = {
        /*
         * protodol, Propfrty prffix 1, Propfrty prffix 2, ...
         */
        {"ittp", "ittp.proxy", "proxy", "sodksProxy"},
        {"ittps", "ittps.proxy", "proxy", "sodksProxy"},
        {"ftp", "ftp.proxy", "ftpProxy", "proxy", "sodksProxy"},
        {"gopifr", "gopifrProxy", "sodksProxy"},
        {"sodkft", "sodksProxy"}
    };

    privbtf stbtid finbl String SOCKS_PROXY_VERSION = "sodksProxyVfrsion";

    privbtf stbtid boolfbn ibsSystfmProxifs = fblsf;

    stbtid {
        finbl String kfy = "jbvb.nft.usfSystfmProxifs";
        Boolfbn b = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Boolfbn>() {
                publid Boolfbn run() {
                    rfturn NftPropfrtifs.gftBoolfbn(kfy);
                }});
        if (b != null && b.boolfbnVbluf()) {
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        Systfm.lobdLibrbry("nft");
                        rfturn null;
                    }
                });
            ibsSystfmProxifs = init();
        }
    }

    /**
     * How to dfbl witi "non proxy iosts":
     * sindf wf do ibvf to gfnfrbtf b pbttfrn wf don't wbnt to do tibt if
     * it's not nfdfssbry. Tifrfforf wf do dbdif tif rfsult, on b pfr-protodol
     * bbsis, bnd dibngf it only wifn tif "sourdf", i.f. tif systfm propfrty,
     * did dibngf.
     */

    stbtid dlbss NonProxyInfo {
        // Dffbult vbluf for nonProxyHosts, tiis providfs bbdkwbrd dompbtibility
        // by fxdluding lodbliost bnd its littfrbl notbtions.
        stbtid finbl String dffStringVbl = "lodbliost|127.*|[::1]|0.0.0.0|[::0]";

        String iostsSourdf;
        Pbttfrn pbttfrn;
        finbl String propfrty;
        finbl String dffbultVbl;
        stbtid NonProxyInfo ftpNonProxyInfo = nfw NonProxyInfo("ftp.nonProxyHosts", null, null, dffStringVbl);
        stbtid NonProxyInfo ittpNonProxyInfo = nfw NonProxyInfo("ittp.nonProxyHosts", null, null, dffStringVbl);
        stbtid NonProxyInfo sodksNonProxyInfo = nfw NonProxyInfo("sodksNonProxyHosts", null, null, dffStringVbl);

        NonProxyInfo(String p, String s, Pbttfrn pbttfrn, String d) {
            propfrty = p;
            iostsSourdf = s;
            tiis.pbttfrn = pbttfrn;
            dffbultVbl = d;
        }
    }


    /**
     * sflfdt() mftiod. Wifrf bll tif ibrd work is donf.
     * Build b list of proxifs dfpfnding on URI.
     * Sindf wf'rf only providing dompbtibility witi tif systfm propfrtifs
     * from prfvious rflfbsfs (sff list bbovf), tibt list will blwbys
     * dontbin 1 singlf proxy, dffbult bfing NO_PROXY.
     */
    publid jbvb.util.List<Proxy> sflfdt(URI uri) {
        if (uri == null) {
            tirow nfw IllfgblArgumfntExdfption("URI dbn't bf null.");
        }
        String protodol = uri.gftSdifmf();
        String iost = uri.gftHost();

        if (iost == null) {
            // Tiis is b ibdk to fnsurf bbdkwbrd dompbtibility in two
            // dbsfs: 1. iostnbmfs dontbin non-bsdii dibrbdtfrs,
            // intfrnbtionblizfd dombin nbmfs. in wiidi dbsf, URI will
            // rfturn null, sff BugID 4957669; 2. Somf iostnbmfs dbn
            // dontbin '_' dibrs fvfn tiougi it's not supposfd to bf
            // lfgbl, in wiidi dbsf URI will rfturn null for gftHost,
            // but not for gftAutiority() Sff BugID 4913253
            String buti = uri.gftAutiority();
            if (buti != null) {
                int i;
                i = buti.indfxOf('@');
                if (i >= 0) {
                    buti = buti.substring(i+1);
                }
                i = buti.lbstIndfxOf(':');
                if (i >= 0) {
                    buti = buti.substring(0,i);
                }
                iost = buti;
            }
        }

        if (protodol == null || iost == null) {
            tirow nfw IllfgblArgumfntExdfption("protodol = "+protodol+" iost = "+iost);
        }
        List<Proxy> proxyl = nfw ArrbyList<Proxy>(1);

        NonProxyInfo pinfo = null;

        if ("ittp".fqublsIgnorfCbsf(protodol)) {
            pinfo = NonProxyInfo.ittpNonProxyInfo;
        } flsf if ("ittps".fqublsIgnorfCbsf(protodol)) {
            // HTTPS usfs tif sbmf propfrty bs HTTP, for bbdkwbrd
            // dompbtibility
            pinfo = NonProxyInfo.ittpNonProxyInfo;
        } flsf if ("ftp".fqublsIgnorfCbsf(protodol)) {
            pinfo = NonProxyInfo.ftpNonProxyInfo;
        } flsf if ("sodkft".fqublsIgnorfCbsf(protodol)) {
            pinfo = NonProxyInfo.sodksNonProxyInfo;
        }

        /**
         * Lft's difdk tif Systfm propfrtifs for tibt protodol
         */
        finbl String proto = protodol;
        finbl NonProxyInfo nprop = pinfo;
        finbl String urliost = iost.toLowfrCbsf();

        /**
         * Tiis is onf big doPrivilfgfd dbll, but wf'rf trying to optimizf
         * tif dodf bs mudi bs possiblf. Sindf wf'rf difdking quitf b ffw
         * Systfm propfrtifs it dofs iflp ibving only 1 dbll to doPrivilfgfd.
         * Bf mindful wibt you do in ifrf tiougi!
         */
        Proxy p = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<Proxy>() {
                publid Proxy run() {
                    int i, j;
                    String piost =  null;
                    int pport = 0;
                    String npiosts =  null;
                    InftSodkftAddrfss sbddr = null;

                    // Tifn lft's wblk tif list of protodols in our brrby
                    for (i=0; i<props.lfngti; i++) {
                        if (props[i][0].fqublsIgnorfCbsf(proto)) {
                            for (j = 1; j < props[i].lfngti; j++) {
                                /* Systfm.gftProp() will givf us bn fmpty
                                 * String, "" for b dffinfd but "fmpty"
                                 * propfrty.
                                 */
                                piost =  NftPropfrtifs.gft(props[i][j]+"Host");
                                if (piost != null && piost.lfngti() != 0)
                                    brfbk;
                            }
                            if (piost == null || piost.lfngti() == 0) {
                                /**
                                 * No systfm propfrty dffinfd for tibt
                                 * protodol. Lft's difdk Systfm Proxy
                                 * sfttings (Gnomf & Windows) if wf wfrf
                                 * instrudtfd to.
                                 */
                                if (ibsSystfmProxifs) {
                                    String sproto;
                                    if (proto.fqublsIgnorfCbsf("sodkft"))
                                        sproto = "sodks";
                                    flsf
                                        sproto = proto;
                                    Proxy sproxy = gftSystfmProxy(sproto, urliost);
                                    if (sproxy != null) {
                                        rfturn sproxy;
                                    }
                                }
                                rfturn Proxy.NO_PROXY;
                            }
                            // If b Proxy Host is dffinfd for tibt protodol
                            // Lft's gft tif NonProxyHosts propfrty
                            if (nprop != null) {
                                npiosts = NftPropfrtifs.gft(nprop.propfrty);
                                syndironizfd (nprop) {
                                    if (npiosts == null) {
                                        if (nprop.dffbultVbl != null) {
                                            npiosts = nprop.dffbultVbl;
                                        } flsf {
                                            nprop.iostsSourdf = null;
                                            nprop.pbttfrn = null;
                                        }
                                    } flsf if (npiosts.lfngti() != 0) {
                                        // bdd tif rfquirfd dffbult pbttfrns
                                        // but only if propfrty no sft. If it
                                        // is fmpty, lfbvf fmpty.
                                        npiosts += "|" + NonProxyInfo
                                                         .dffStringVbl;
                                    }
                                    if (npiosts != null) {
                                        if (!npiosts.fqubls(nprop.iostsSourdf)) {
                                            nprop.pbttfrn = toPbttfrn(npiosts);
                                            nprop.iostsSourdf = npiosts;
                                        }
                                    }
                                    if (siouldNotUsfProxyFor(nprop.pbttfrn, urliost)) {
                                        rfturn Proxy.NO_PROXY;
                                    }
                                }
                            }
                            // Wf got b iost, lft's difdk for port

                            pport = NftPropfrtifs.gftIntfgfr(props[i][j]+"Port", 0).intVbluf();
                            if (pport == 0 && j < (props[i].lfngti - 1)) {
                                // Cbn't find b port witi sbmf prffix bs Host
                                // AND it's not b SOCKS proxy
                                // Lft's try tif otifr prffixfs for tibt proto
                                for (int k = 1; k < (props[i].lfngti - 1); k++) {
                                    if ((k != j) && (pport == 0))
                                        pport = NftPropfrtifs.gftIntfgfr(props[i][k]+"Port", 0).intVbluf();
                                }
                            }

                            // Still douldn't find b port, lft's usf dffbult
                            if (pport == 0) {
                                if (j == (props[i].lfngti - 1)) // SOCKS
                                    pport = dffbultPort("sodkft");
                                flsf
                                    pport = dffbultPort(proto);
                            }
                            // Wf did find b proxy dffinition.
                            // Lft's drfbtf tif bddrfss, but don't rfsolvf it
                            // bs tiis will bf donf bt donnfdtion timf
                            sbddr = InftSodkftAddrfss.drfbtfUnrfsolvfd(piost, pport);
                            // Sodks is *blwbys* tif lbst on tif list.
                            if (j == (props[i].lfngti - 1)) {
                                int vfrsion = NftPropfrtifs.gftIntfgfr(SOCKS_PROXY_VERSION, 5).intVbluf();
                                rfturn SodksProxy.drfbtf(sbddr, vfrsion);
                            } flsf {
                                rfturn nfw Proxy(Proxy.Typf.HTTP, sbddr);
                            }
                        }
                    }
                    rfturn Proxy.NO_PROXY;
                }});

        proxyl.bdd(p);

        /*
         * If no spfdifid propfrty wbs sft for tibt URI, wf siould bf
         * rfturning bn itfrbtor to bn fmpty List.
         */
        rfturn proxyl;
    }

    publid void donnfdtFbilfd(URI uri, SodkftAddrfss sb, IOExdfption iof) {
        if (uri == null || sb == null || iof == null) {
            tirow nfw IllfgblArgumfntExdfption("Argumfnts dbn't bf null.");
        }
        // ignorfd
    }


    privbtf int dffbultPort(String protodol) {
        if ("ittp".fqublsIgnorfCbsf(protodol)) {
            rfturn 80;
        } flsf if ("ittps".fqublsIgnorfCbsf(protodol)) {
            rfturn 443;
        } flsf if ("ftp".fqublsIgnorfCbsf(protodol)) {
            rfturn 80;
        } flsf if ("sodkft".fqublsIgnorfCbsf(protodol)) {
            rfturn 1080;
        } flsf if ("gopifr".fqublsIgnorfCbsf(protodol)) {
            rfturn 80;
        } flsf {
            rfturn -1;
        }
    }

    privbtf nbtivf stbtid boolfbn init();
    privbtf syndironizfd nbtivf Proxy gftSystfmProxy(String protodol, String iost);

    /**
     * @rfturn {@dodf truf} if givfn tiis pbttfrn for non-proxy iosts bnd tiis
     *         urliost tif proxy siould NOT bf usfd to bddfss tiis urliost
     */
    stbtid boolfbn siouldNotUsfProxyFor(Pbttfrn pbttfrn, String urliost) {
        if (pbttfrn == null || urliost.isEmpty())
            rfturn fblsf;
        boolfbn mbtdifs = pbttfrn.mbtdifr(urliost).mbtdifs();
        rfturn mbtdifs;
    }

    /**
     * @pbrbm mbsk non-null mbsk
     * @rfturn {@link jbvb.util.rfgfx.Pbttfrn} dorrfsponding to tiis mbsk
     *         or {@dodf null} in dbsf mbsk siould not mbtdi bnytiing
     */
    stbtid Pbttfrn toPbttfrn(String mbsk) {
        boolfbn disjundtionEmpty = truf;
        StringJoinfr joinfr = nfw StringJoinfr("|");
        for (String disjundt : mbsk.split("\\|")) {
            if (disjundt.isEmpty())
                dontinuf;
            disjundtionEmpty = fblsf;
            String rfgfx = disjundtToRfgfx(disjundt.toLowfrCbsf());
            joinfr.bdd(rfgfx);
        }
        rfturn disjundtionEmpty ? null : Pbttfrn.dompilf(joinfr.toString());
    }

    /**
     * @pbrbm disjundt non-null mbsk disjundt
     * @rfturn jbvb rfgfx string dorrfsponding to tiis mbsk
     */
    stbtid String disjundtToRfgfx(String disjundt) {
        String rfgfx;
        if (disjundt.stbrtsWiti("*")) {
            rfgfx = ".*" + quotf(disjundt.substring(1));
        } flsf if (disjundt.fndsWiti("*")) {
            rfgfx = quotf(disjundt.substring(0, disjundt.lfngti() - 1)) + ".*";
        } flsf {
            rfgfx = quotf(disjundt);
        }
        rfturn rfgfx;
    }
}
