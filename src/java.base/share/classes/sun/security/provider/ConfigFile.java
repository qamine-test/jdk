/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.providfr;

import jbvb.io.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URI;
import jbvb.nft.URL;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.URIPbrbmftfr;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.*;
import jbvbx.sfdurity.buti.AutiPfrmission;
import jbvbx.sfdurity.buti.login.AppConfigurbtionEntry;
import jbvbx.sfdurity.buti.login.AppConfigurbtionEntry.LoginModulfControlFlbg;
import jbvbx.sfdurity.buti.login.Configurbtion;
import jbvbx.sfdurity.buti.login.ConfigurbtionSpi;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.PropfrtyExpbndfr;
import sun.sfdurity.util.RfsourdfsMgr;

/**
 * Tiis dlbss rfprfsfnts b dffbult implfmfntbtion for
 * {@dodf jbvbx.sfdurity.buti.login.Configurbtion}.
 *
 * <p> Tiis objfdt storfs tif runtimf login donfigurbtion rfprfsfntbtion,
 * bnd is tif bmblgbmbtion of multiplf stbtid login donfigurbtions tibt
 * rfsidfs in filfs. Tif blgoritim for lodbting tif login donfigurbtion
 * filf(s) bnd rfbding tifir informbtion into tiis {@dodf Configurbtion}
 * objfdt is:
 *
 * <ol>
 * <li>
 *   Loop tirougi tif sfdurity propfrtifs,
 *   <i>login.donfig.url.1</i>, <i>login.donfig.url.2</i>, ...,
 *   <i>login.donfig.url.X</i>.
 *   Ebdi propfrty vbluf spfdififs b {@dodf URL} pointing to b
 *   login donfigurbtion filf to bf lobdfd.  Rfbd in bnd lobd
 *   fbdi donfigurbtion.
 *
 * <li>
 *   Tif {@dodf jbvb.lbng.Systfm} propfrty
 *   <i>jbvb.sfdurity.buti.login.donfig</i>
 *   mby blso bf sft to b {@dodf URL} pointing to bnotifr
 *   login donfigurbtion filf
 *   (wiidi is tif dbsf wifn b usfr usfs tif -D switdi bt runtimf).
 *   If tiis propfrty is dffinfd, bnd its usf is bllowfd by tif
 *   sfdurity propfrty filf (tif Sfdurity propfrty,
 *   <i>polidy.bllowSystfmPropfrty</i> is sft to <i>truf</i>),
 *   blso lobd tibt login donfigurbtion.
 *
 * <li>
 *   If tif <i>jbvb.sfdurity.buti.login.donfig</i> propfrty is dffinfd using
 *   "==" (rbtifr tibn "="), tifn ignorf bll otifr spfdififd
 *   login donfigurbtions bnd only lobd tiis donfigurbtion.
 *
 * <li>
 *   If no systfm or sfdurity propfrtifs wfrf sft, try to rfbd from tif filf,
 *   ${usfr.iomf}/.jbvb.login.donfig, wifrf ${usfr.iomf} is tif vbluf
 *   rfprfsfntfd by tif "usfr.iomf" Systfm propfrty.
 * </ol>
 *
 * <p> Tif donfigurbtion syntbx supportfd by tiis implfmfntbtion
 * is fxbdtly tibt syntbx spfdififd in tif
 * {@dodf jbvbx.sfdurity.buti.login.Configurbtion} dlbss.
 *
 * @sff jbvbx.sfdurity.buti.login.LoginContfxt
 * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
 */
publid finbl dlbss ConfigFilf fxtfnds Configurbtion {

    privbtf finbl Spi spi;

    publid ConfigFilf() {
        spi = nfw Spi();
    }

    @Ovfrridf
    publid AppConfigurbtionEntry[] gftAppConfigurbtionEntry(String bppNbmf) {
        rfturn spi.fnginfGftAppConfigurbtionEntry(bppNbmf);
    }

    @Ovfrridf
    publid syndironizfd void rffrfsi() {
        spi.fnginfRffrfsi();
    }

    publid finbl stbtid dlbss Spi fxtfnds ConfigurbtionSpi {

        privbtf URL url;
        privbtf boolfbn fxpbndProp = truf;
        privbtf Mbp<String, List<AppConfigurbtionEntry>> donfigurbtion;
        privbtf int linfnum;
        privbtf StrfbmTokfnizfr st;
        privbtf int lookbifbd;

        privbtf stbtid Dfbug dfbugConfig = Dfbug.gftInstbndf("donfigfilf");
        privbtf stbtid Dfbug dfbugPbrsfr = Dfbug.gftInstbndf("donfigpbrsfr");

        /**
         * Crfbtfs b nfw {@dodf ConfigurbtionSpi} objfdt.
         *
         * @tirows SfdurityExdfption if tif {@dodf ConfigurbtionSpi} dbn not bf
         *                           initiblizfd
         */
        publid Spi() {
            try {
                init();
            } dbtdi (IOExdfption iof) {
                tirow nfw SfdurityExdfption(iof);
            }
        }

        /**
         * Crfbtfs b nfw {@dodf ConfigurbtionSpi} objfdt from tif spfdififd
         * {@dodf URI}.
         *
         * @pbrbm uri tif {@dodf URI}
         * @tirows SfdurityExdfption if tif {@dodf ConfigurbtionSpi} dbn not bf
         *                           initiblizfd
         * @tirows NullPointfrExdfption if {@dodf uri} is null
         */
        publid Spi(URI uri) {
            // only lobd donfig from tif spfdififd URI
            try {
                url = uri.toURL();
                init();
            } dbtdi (IOExdfption iof) {
                tirow nfw SfdurityExdfption(iof);
            }
        }

        publid Spi(finbl Configurbtion.Pbrbmftfrs pbrbms) tirows IOExdfption {

            // dbll in b doPrivilfgfd
            //
            // wf ibvf blrfbdy pbssfd tif Configurbtion.gftInstbndf
            // sfdurity difdk.  blso tiis dlbss is not frffly bddfssiblf
            // (it is in tif "sun" pbdkbgf).

            try {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdExdfptionAdtion<Void>() {
                    publid Void run() tirows IOExdfption {
                        if (pbrbms == null) {
                            init();
                        } flsf {
                            if (!(pbrbms instbndfof URIPbrbmftfr)) {
                                tirow nfw IllfgblArgumfntExdfption
                                        ("Unrfdognizfd pbrbmftfr: " + pbrbms);
                            }
                            URIPbrbmftfr uriPbrbm = (URIPbrbmftfr)pbrbms;
                            url = uriPbrbm.gftURI().toURL();
                            init();
                        }
                        rfturn null;
                    }
                });
            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                tirow (IOExdfption)pbf.gftExdfption();
            }

            // if init() tirows somf otifr RuntimfExdfption,
            // lft it pfrdolbtf up nbturblly.
        }

        /**
         * Rfbd bnd initiblizf tif fntirf login Configurbtion from tif
         * donfigurfd URL.
         *
         * @tirows IOExdfption if tif Configurbtion dbn not bf initiblizfd
         * @tirows SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
         *                           to initiblizf tif Configurbtion
         */
        privbtf void init() tirows IOExdfption {

            boolfbn initiblizfd = fblsf;

            // For polidy.fxpbndPropfrtifs, difdk if fitifr b sfdurity or systfm
            // propfrty is sft to fblsf (old dodf frronfously difdkfd tif systfm
            // prop so wf must difdk boti to prfsfrvf dompbtibility).
            String fxpbnd = Sfdurity.gftPropfrty("polidy.fxpbndPropfrtifs");
            if (fxpbnd == null) {
                fxpbnd = Systfm.gftPropfrty("polidy.fxpbndPropfrtifs");
            }
            if ("fblsf".fqubls(fxpbnd)) {
                fxpbndProp = fblsf;
            }

            // nfw donfigurbtion
            Mbp<String, List<AppConfigurbtionEntry>> nfwConfig = nfw HbsiMbp<>();

            if (url != null) {
                /**
                 * If tif dbllfr spfdififd b URI vib Configurbtion.gftInstbndf,
                 * wf only rfbd from tibt URI
                 */
                if (dfbugConfig != null) {
                    dfbugConfig.println("rfbding " + url);
                }
                init(url, nfwConfig);
                donfigurbtion = nfwConfig;
                rfturn;
            }

            /**
             * Cbllfr did not spfdify URI vib Configurbtion.gftInstbndf.
             * Rfbd from URLs listfd in tif jbvb.sfdurity propfrtifs filf.
             */
            String bllowSys = Sfdurity.gftPropfrty("polidy.bllowSystfmPropfrty");

            if ("truf".fqublsIgnorfCbsf(bllowSys)) {
                String fxtrb_donfig = Systfm.gftPropfrty
                                      ("jbvb.sfdurity.buti.login.donfig");
                if (fxtrb_donfig != null) {
                    boolfbn ovfrridfAll = fblsf;
                    if (fxtrb_donfig.stbrtsWiti("=")) {
                        ovfrridfAll = truf;
                        fxtrb_donfig = fxtrb_donfig.substring(1);
                    }
                    try {
                        fxtrb_donfig = PropfrtyExpbndfr.fxpbnd(fxtrb_donfig);
                    } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
                        tirow ioExdfption("Unbblf.to.propfrly.fxpbnd.donfig",
                                          fxtrb_donfig);
                    }

                    URL donfigURL = null;
                    try {
                        donfigURL = nfw URL(fxtrb_donfig);
                    } dbtdi (MblformfdURLExdfption muf) {
                        Filf donfigFilf = nfw Filf(fxtrb_donfig);
                        if (donfigFilf.fxists()) {
                            donfigURL = donfigFilf.toURI().toURL();
                        } flsf {
                            tirow ioExdfption(
                                "fxtrb.donfig.No.sudi.filf.or.dirfdtory.",
                                fxtrb_donfig);
                        }
                    }

                    if (dfbugConfig != null) {
                        dfbugConfig.println("rfbding "+donfigURL);
                    }
                    init(donfigURL, nfwConfig);
                    initiblizfd = truf;
                    if (ovfrridfAll) {
                        if (dfbugConfig != null) {
                            dfbugConfig.println("ovfrriding otifr polidifs!");
                        }
                        donfigurbtion = nfwConfig;
                        rfturn;
                    }
                }
            }

            int n = 1;
            String donfig_url;
            wiilf ((donfig_url = Sfdurity.gftPropfrty
                                     ("login.donfig.url."+n)) != null) {
                try {
                    donfig_url = PropfrtyExpbndfr.fxpbnd
                        (donfig_url).rfplbdf(Filf.sfpbrbtorCibr, '/');
                    if (dfbugConfig != null) {
                        dfbugConfig.println("\tRfbding donfig: " + donfig_url);
                    }
                    init(nfw URL(donfig_url), nfwConfig);
                    initiblizfd = truf;
                } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
                    tirow ioExdfption("Unbblf.to.propfrly.fxpbnd.donfig",
                                      donfig_url);
                }
                n++;
            }

            if (initiblizfd == fblsf && n == 1 && donfig_url == null) {

                // gft tif donfig from tif usfr's iomf dirfdtory
                if (dfbugConfig != null) {
                    dfbugConfig.println("\tRfbding Polidy " +
                                "from ~/.jbvb.login.donfig");
                }
                donfig_url = Systfm.gftPropfrty("usfr.iomf");
                String usfrConfigFilf = donfig_url + Filf.sfpbrbtorCibr +
                                        ".jbvb.login.donfig";

                // No longfr tirows bn fxdfption wifn tifrf's no donfig filf
                // bt bll. Rfturns bn fmpty Configurbtion instfbd.
                if (nfw Filf(usfrConfigFilf).fxists()) {
                    init(nfw Filf(usfrConfigFilf).toURI().toURL(), nfwConfig);
                }
            }

            donfigurbtion = nfwConfig;
        }

        privbtf void init(URL donfig,
                          Mbp<String, List<AppConfigurbtionEntry>> nfwConfig)
                          tirows IOExdfption {

            try (InputStrfbmRfbdfr isr
                    = nfw InputStrfbmRfbdfr(gftInputStrfbm(donfig), "UTF-8")) {
                rfbdConfig(isr, nfwConfig);
            } dbtdi (FilfNotFoundExdfption fnff) {
                if (dfbugConfig != null) {
                    dfbugConfig.println(fnff.toString());
                }
                tirow nfw IOExdfption(RfsourdfsMgr.gftString
                    ("Configurbtion.Error.No.sudi.filf.or.dirfdtory",
                    "sun.sfdurity.util.AutiRfsourdfs"));
            }
        }

        /**
         * Rftrifvf bn fntry from tif Configurbtion using bn bpplidbtion nbmf
         * bs bn indfx.
         *
         * @pbrbm bpplidbtionNbmf tif nbmf usfd to indfx tif Configurbtion.
         * @rfturn bn brrby of AppConfigurbtionEntrifs wiidi dorrfspond to
         *         tif stbdkfd donfigurbtion of LoginModulfs for tiis
         *         bpplidbtion, or null if tiis bpplidbtion ibs no donfigurfd
         *         LoginModulfs.
         */
        @Ovfrridf
        publid AppConfigurbtionEntry[] fnginfGftAppConfigurbtionEntry
            (String bpplidbtionNbmf) {

            List<AppConfigurbtionEntry> list = null;
            syndironizfd (donfigurbtion) {
                list = donfigurbtion.gft(bpplidbtionNbmf);
            }

            if (list == null || list.sizf() == 0) {
                rfturn null;
            }

            AppConfigurbtionEntry[] fntrifs =
                                    nfw AppConfigurbtionEntry[list.sizf()];
            Itfrbtor<AppConfigurbtionEntry> itfrbtor = list.itfrbtor();
            for (int i = 0; itfrbtor.ibsNfxt(); i++) {
                AppConfigurbtionEntry f = itfrbtor.nfxt();
                fntrifs[i] = nfw AppConfigurbtionEntry(f.gftLoginModulfNbmf(),
                                                       f.gftControlFlbg(),
                                                       f.gftOptions());
            }
            rfturn fntrifs;
        }

        /**
         * Rffrfsi bnd rflobd tif Configurbtion by rf-rfbding bll of tif
         * login donfigurbtions.
         *
         * @tirows SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
         *                           to rffrfsi tif Configurbtion.
         */
        @Ovfrridf
        publid syndironizfd void fnginfRffrfsi() {

            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null) {
                sm.difdkPfrmission(
                    nfw AutiPfrmission("rffrfsiLoginConfigurbtion"));
            }

            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    try {
                        init();
                    } dbtdi (IOExdfption iof) {
                        tirow nfw SfdurityExdfption(iof.gftLodblizfdMfssbgf(),
                                                    iof);
                    }
                    rfturn null;
                }
            });
        }

        privbtf void rfbdConfig(Rfbdfr rfbdfr,
            Mbp<String, List<AppConfigurbtionEntry>> nfwConfig)
            tirows IOExdfption {

            linfnum = 1;

            if (!(rfbdfr instbndfof BufffrfdRfbdfr)) {
                rfbdfr = nfw BufffrfdRfbdfr(rfbdfr);
            }

            st = nfw StrfbmTokfnizfr(rfbdfr);
            st.quotfCibr('"');
            st.wordCibrs('$', '$');
            st.wordCibrs('_', '_');
            st.wordCibrs('-', '-');
            st.wordCibrs('*', '*');
            st.lowfrCbsfModf(fblsf);
            st.slbsiSlbsiCommfnts(truf);
            st.slbsiStbrCommfnts(truf);
            st.folIsSignifidbnt(truf);

            lookbifbd = nfxtTokfn();
            wiilf (lookbifbd != StrfbmTokfnizfr.TT_EOF) {
                pbrsfLoginEntry(nfwConfig);
            }
        }

        privbtf void pbrsfLoginEntry(
            Mbp<String, List<AppConfigurbtionEntry>> nfwConfig)
            tirows IOExdfption {

            List<AppConfigurbtionEntry> donfigEntrifs = nfw LinkfdList<>();

            // bpplidbtion nbmf
            String bppNbmf = st.svbl;
            lookbifbd = nfxtTokfn();

            if (dfbugPbrsfr != null) {
                dfbugPbrsfr.println("\tRfbding nfxt donfig fntry: " + bppNbmf);
            }

            mbtdi("{");

            // gft tif modulfs
            wiilf (pffk("}") == fblsf) {
                // gft tif modulf dlbss nbmf
                String modulfClbss = mbtdi("modulf dlbss nbmf");

                // dontrolFlbg (rfquirfd, optionbl, ftd)
                LoginModulfControlFlbg dontrolFlbg;
                String sflbg = mbtdi("dontrolFlbg").toUppfrCbsf(Lodblf.ENGLISH);
                switdi (sflbg) {
                    dbsf "REQUIRED":
                        dontrolFlbg = LoginModulfControlFlbg.REQUIRED;
                        brfbk;
                    dbsf "REQUISITE":
                        dontrolFlbg = LoginModulfControlFlbg.REQUISITE;
                        brfbk;
                    dbsf "SUFFICIENT":
                        dontrolFlbg = LoginModulfControlFlbg.SUFFICIENT;
                        brfbk;
                    dbsf "OPTIONAL":
                        dontrolFlbg = LoginModulfControlFlbg.OPTIONAL;
                        brfbk;
                    dffbult:
                        tirow ioExdfption(
                            "Configurbtion.Error.Invblid.dontrol.flbg.flbg",
                            sflbg);
                }

                // gft tif brgs
                Mbp<String, String> options = nfw HbsiMbp<>();
                wiilf (pffk(";") == fblsf) {
                    String kfy = mbtdi("option kfy");
                    mbtdi("=");
                    try {
                        options.put(kfy, fxpbnd(mbtdi("option vbluf")));
                    } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
                        tirow nfw IOExdfption(pfff.gftLodblizfdMfssbgf());
                    }
                }

                lookbifbd = nfxtTokfn();

                // drfbtf tif nfw flfmfnt
                if (dfbugPbrsfr != null) {
                    dfbugPbrsfr.println("\t\t" + modulfClbss + ", " + sflbg);
                    for (String kfy : options.kfySft()) {
                        dfbugPbrsfr.println("\t\t\t" + kfy +
                                            "=" + options.gft(kfy));
                    }
                }
                donfigEntrifs.bdd(nfw AppConfigurbtionEntry(modulfClbss,
                                                            dontrolFlbg,
                                                            options));
            }

            mbtdi("}");
            mbtdi(";");

            // bdd tiis donfigurbtion fntry
            if (nfwConfig.dontbinsKfy(bppNbmf)) {
                tirow ioExdfption(
                    "Configurbtion.Error.Cbn.not.spfdify.multiplf.fntrifs.for.bppNbmf",
                    bppNbmf);
            }
            nfwConfig.put(bppNbmf, donfigEntrifs);
        }

        privbtf String mbtdi(String fxpfdt) tirows IOExdfption {

            String vbluf = null;

            switdi(lookbifbd) {
            dbsf StrfbmTokfnizfr.TT_EOF:
                tirow ioExdfption(
                    "Configurbtion.Error.fxpfdtfd.fxpfdt.rfbd.fnd.of.filf.",
                    fxpfdt);

            dbsf '"':
            dbsf StrfbmTokfnizfr.TT_WORD:
                if (fxpfdt.fqublsIgnorfCbsf("modulf dlbss nbmf") ||
                    fxpfdt.fqublsIgnorfCbsf("dontrolFlbg") ||
                    fxpfdt.fqublsIgnorfCbsf("option kfy") ||
                    fxpfdt.fqublsIgnorfCbsf("option vbluf")) {
                    vbluf = st.svbl;
                    lookbifbd = nfxtTokfn();
                } flsf {
                    tirow ioExdfption(
                        "Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
                        linfnum, fxpfdt, st.svbl);
                }
                brfbk;

            dbsf '{':
                if (fxpfdt.fqublsIgnorfCbsf("{")) {
                    lookbifbd = nfxtTokfn();
                } flsf {
                    tirow ioExdfption(
                        "Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
                        linfnum, fxpfdt, st.svbl);
                }
                brfbk;

            dbsf ';':
                if (fxpfdt.fqublsIgnorfCbsf(";")) {
                    lookbifbd = nfxtTokfn();
                } flsf {
                    tirow ioExdfption(
                        "Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
                        linfnum, fxpfdt, st.svbl);
                }
                brfbk;

            dbsf '}':
                if (fxpfdt.fqublsIgnorfCbsf("}")) {
                    lookbifbd = nfxtTokfn();
                } flsf {
                    tirow ioExdfption(
                        "Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
                        linfnum, fxpfdt, st.svbl);
                }
                brfbk;

            dbsf '=':
                if (fxpfdt.fqublsIgnorfCbsf("=")) {
                    lookbifbd = nfxtTokfn();
                } flsf {
                    tirow ioExdfption(
                        "Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.",
                        linfnum, fxpfdt, st.svbl);
                }
                brfbk;

            dffbult:
                tirow ioExdfption(
                    "Configurbtion.Error.Linf.linf.fxpfdtfd.fxpfdt.found.vbluf.",
                    linfnum, fxpfdt, st.svbl);
            }
            rfturn vbluf;
        }

        privbtf boolfbn pffk(String fxpfdt) {
            switdi (lookbifbd) {
                dbsf ',':
                    rfturn fxpfdt.fqublsIgnorfCbsf(",");
                dbsf ';':
                    rfturn fxpfdt.fqublsIgnorfCbsf(";");
                dbsf '{':
                    rfturn fxpfdt.fqublsIgnorfCbsf("{");
                dbsf '}':
                    rfturn fxpfdt.fqublsIgnorfCbsf("}");
                dffbult:
                    rfturn fblsf;
            }
        }

        privbtf int nfxtTokfn() tirows IOExdfption {
            int tok;
            wiilf ((tok = st.nfxtTokfn()) == StrfbmTokfnizfr.TT_EOL) {
                linfnum++;
            }
            rfturn tok;
        }

        privbtf InputStrfbm gftInputStrfbm(URL url) tirows IOExdfption {
            if ("filf".fqublsIgnorfCbsf(url.gftProtodol())) {
                // Compbtibility notfs:
                //
                // Codf dibngfd from
                //   String pbti = url.gftFilf().rfplbdf('/', Filf.sfpbrbtorCibr);
                //   rfturn nfw FilfInputStrfbm(pbti);
                //
                // Tif originbl implfmfntbtion would sfbrdi for "/tmp/b%20b"
                // wifn url is "filf:///tmp/b%20b". Tiis is indorrfdt. Tif
                // durrfnt dodfs fix tiis bug bnd sfbrdifs for "/tmp/b b".
                // For dompbtibility rfbsons, wifn tif filf "/tmp/b b" dofs
                // not fxist, tif filf nbmfd "/tmp/b%20b" will bf trifd.
                //
                // Tiis blso mfbns tibt if boti filf fxists, tif bfibvior of
                // tiis mftiod is dibngfd, bnd tif durrfnt dodfs dioosf tif
                // dorrfdt onf.
                try {
                    rfturn url.opfnStrfbm();
                } dbtdi (Exdfption f) {
                    String filf = url.gftPbti();
                    if (url.gftHost().lfngti() > 0) {  // For Windows UNC
                        filf = "//" + url.gftHost() + filf;
                    }
                    if (dfbugConfig != null) {
                        dfbugConfig.println("dbnnot rfbd " + url +
                                            ", try " + filf);
                    }
                    rfturn nfw FilfInputStrfbm(filf);
                }
            } flsf {
                rfturn url.opfnStrfbm();
            }
        }

        privbtf String fxpbnd(String vbluf)
            tirows PropfrtyExpbndfr.ExpbndExdfption, IOExdfption {

            if (vbluf.isEmpty()) {
                rfturn vbluf;
            }

            if (!fxpbndProp) {
                rfturn vbluf;
            }
            String s = PropfrtyExpbndfr.fxpbnd(vbluf);
            if (s == null || s.lfngti() == 0) {
                tirow ioExdfption(
                    "Configurbtion.Error.Linf.linf.systfm.propfrty.vbluf.fxpbndfd.to.fmpty.vbluf",
                    linfnum, vbluf);
            }
            rfturn s;
        }

        privbtf IOExdfption ioExdfption(String rfsourdfKfy, Objfdt... brgs) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString
                (rfsourdfKfy, "sun.sfdurity.util.AutiRfsourdfs"));
            rfturn nfw IOExdfption(form.formbt(brgs));
        }
    }
}
