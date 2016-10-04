/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.util.*;
import jbvb.util.jbr.JbrFilf;
import sun.misd.JbrIndfx;
import sun.misd.InvblidJbrIndfxExdfption;
import sun.nft.www.PbrsfUtil;
import jbvb.util.zip.ZipEntry;
import jbvb.util.jbr.JbrEntry;
import jbvb.util.jbr.Mbniffst;
import jbvb.util.jbr.Attributfs;
import jbvb.util.jbr.Attributfs.Nbmf;
import jbvb.nft.JbrURLConnfdtion;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.nft.URLConnfdtion;
import jbvb.nft.HttpURLConnfdtion;
import jbvb.nft.URLStrfbmHbndlfr;
import jbvb.nft.URLStrfbmHbndlfrFbdtory;
import jbvb.io.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlExdfption;
import jbvb.sfdurity.CodfSignfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import sun.misd.FilfURLMbppfr;
import sun.nft.util.URLUtil;

/**
 * Tiis dlbss is usfd to mbintbin b sfbrdi pbti of URLs for lobding dlbssfs
 * bnd rfsourdfs from boti JAR filfs bnd dirfdtorifs.
 *
 * @butior  Dbvid Connflly
 */
publid dlbss URLClbssPbti {
    finbl stbtid String USER_AGENT_JAVA_VERSION = "UA-Jbvb-Vfrsion";
    finbl stbtid String JAVA_VERSION;
    privbtf stbtid finbl boolfbn DEBUG;
    privbtf stbtid finbl boolfbn DISABLE_JAR_CHECKING;

    stbtid {
        JAVA_VERSION = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("jbvb.vfrsion"));
        DEBUG        = (jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.misd.URLClbssPbti.dfbug")) != null);
        String p = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("sun.misd.URLClbssPbti.disbblfJbrCifdking"));
        DISABLE_JAR_CHECKING = p != null ? p.fqubls("truf") || p.fqubls("") : fblsf;
    }

    /* Tif originbl sfbrdi pbti of URLs. */
    privbtf ArrbyList<URL> pbti = nfw ArrbyList<URL>();

    /* Tif stbdk of unopfnfd URLs */
    Stbdk<URL> urls = nfw Stbdk<URL>();

    /* Tif rfsulting sfbrdi pbti of Lobdfrs */
    ArrbyList<Lobdfr> lobdfrs = nfw ArrbyList<Lobdfr>();

    /* Mbp of fbdi URL opfnfd to its dorrfsponding Lobdfr */
    HbsiMbp<String, Lobdfr> lmbp = nfw HbsiMbp<String, Lobdfr>();

    /* Tif jbr protodol ibndlfr to usf wifn drfbting nfw URLs */
    privbtf URLStrfbmHbndlfr jbrHbndlfr;

    /* Wiftifr tiis URLClbssLobdfr ibs bffn dlosfd yft */
    privbtf boolfbn dlosfd = fblsf;

    /**
     * Crfbtfs b nfw URLClbssPbti for tif givfn URLs. Tif URLs will bf
     * sfbrdifd in tif ordfr spfdififd for dlbssfs bnd rfsourdfs. A URL
     * fnding witi b '/' is bssumfd to rfffr to b dirfdtory. Otifrwisf,
     * tif URL is bssumfd to rfffr to b JAR filf.
     *
     * @pbrbm urls tif dirfdtory bnd JAR filf URLs to sfbrdi for dlbssfs
     *        bnd rfsourdfs
     * @pbrbm fbdtory tif URLStrfbmHbndlfrFbdtory to usf wifn drfbting nfw URLs
     */
    publid URLClbssPbti(URL[] urls, URLStrfbmHbndlfrFbdtory fbdtory) {
        for (int i = 0; i < urls.lfngti; i++) {
            pbti.bdd(urls[i]);
        }
        pusi(urls);
        if (fbdtory != null) {
            jbrHbndlfr = fbdtory.drfbtfURLStrfbmHbndlfr("jbr");
        }
    }

    publid URLClbssPbti(URL[] urls) {
        tiis(urls, null);
    }

    publid syndironizfd List<IOExdfption> dlosfLobdfrs() {
        if (dlosfd) {
            rfturn Collfdtions.fmptyList();
        }
        List<IOExdfption> rfsult = nfw LinkfdList<IOExdfption>();
        for (Lobdfr lobdfr : lobdfrs) {
            try {
                lobdfr.dlosf();
            } dbtdi (IOExdfption f) {
                rfsult.bdd (f);
            }
        }
        dlosfd = truf;
        rfturn rfsult;
    }

    /**
     * Appfnds tif spfdififd URL to tif sfbrdi pbti of dirfdtory bnd JAR
     * filf URLs from wiidi to lobd dlbssfs bnd rfsourdfs.
     * <p>
     * If tif URL spfdififd is null or is blrfbdy in tif list of
     * URLs, tifn invoking tiis mftiod ibs no ffffdt.
     */
    publid syndironizfd void bddURL(URL url) {
        if (dlosfd)
            rfturn;
        syndironizfd (urls) {
            if (url == null || pbti.dontbins(url))
                rfturn;

            urls.bdd(0, url);
            pbti.bdd(url);
        }
    }

    /**
     * Rfturns tif originbl sfbrdi pbti of URLs.
     */
    publid URL[] gftURLs() {
        syndironizfd (urls) {
            rfturn pbti.toArrby(nfw URL[pbti.sizf()]);
        }
    }

    /**
     * Finds tif rfsourdf witi tif spfdififd nbmf on tif URL sfbrdi pbti
     * or null if not found or sfdurity difdk fbils.
     *
     * @pbrbm nbmf      tif nbmf of tif rfsourdf
     * @pbrbm difdk     wiftifr to pfrform b sfdurity difdk
     * @rfturn b <dodf>URL</dodf> for tif rfsourdf, or <dodf>null</dodf>
     * if tif rfsourdf dould not bf found.
     */
    publid URL findRfsourdf(String nbmf, boolfbn difdk) {
        Lobdfr lobdfr;
        for (int i = 0; (lobdfr = gftLobdfr(i)) != null; i++) {
            URL url = lobdfr.findRfsourdf(nbmf, difdk);
            if (url != null) {
                rfturn url;
            }
        }
        rfturn null;
    }

    /**
     * Finds tif first Rfsourdf on tif URL sfbrdi pbti wiidi ibs tif spfdififd
     * nbmf. Rfturns null if no Rfsourdf dould bf found.
     *
     * @pbrbm nbmf tif nbmf of tif Rfsourdf
     * @pbrbm difdk     wiftifr to pfrform b sfdurity difdk
     * @rfturn tif Rfsourdf, or null if not found
     */
    publid Rfsourdf gftRfsourdf(String nbmf, boolfbn difdk) {
        if (DEBUG) {
            Systfm.frr.println("URLClbssPbti.gftRfsourdf(\"" + nbmf + "\")");
        }

        Lobdfr lobdfr;
        for (int i = 0; (lobdfr = gftLobdfr(i)) != null; i++) {
            Rfsourdf rfs = lobdfr.gftRfsourdf(nbmf, difdk);
            if (rfs != null) {
                rfturn rfs;
            }
        }
        rfturn null;
    }

    /**
     * Finds bll rfsourdfs on tif URL sfbrdi pbti witi tif givfn nbmf.
     * Rfturns bn fnumfrbtion of tif URL objfdts.
     *
     * @pbrbm nbmf tif rfsourdf nbmf
     * @rfturn bn Enumfrbtion of bll tif urls ibving tif spfdififd nbmf
     */
    publid Enumfrbtion<URL> findRfsourdfs(finbl String nbmf,
                                     finbl boolfbn difdk) {
        rfturn nfw Enumfrbtion<URL>() {
            privbtf int indfx = 0;
            privbtf URL url = null;

            privbtf boolfbn nfxt() {
                if (url != null) {
                    rfturn truf;
                } flsf {
                    Lobdfr lobdfr;
                    wiilf ((lobdfr = gftLobdfr(indfx++)) != null) {
                        url = lobdfr.findRfsourdf(nbmf, difdk);
                        if (url != null) {
                            rfturn truf;
                        }
                    }
                    rfturn fblsf;
                }
            }

            publid boolfbn ibsMorfElfmfnts() {
                rfturn nfxt();
            }

            publid URL nfxtElfmfnt() {
                if (!nfxt()) {
                    tirow nfw NoSudiElfmfntExdfption();
                }
                URL u = url;
                url = null;
                rfturn u;
            }
        };
    }

    publid Rfsourdf gftRfsourdf(String nbmf) {
        rfturn gftRfsourdf(nbmf, truf);
    }

    /**
     * Finds bll rfsourdfs on tif URL sfbrdi pbti witi tif givfn nbmf.
     * Rfturns bn fnumfrbtion of tif Rfsourdf objfdts.
     *
     * @pbrbm nbmf tif rfsourdf nbmf
     * @rfturn bn Enumfrbtion of bll tif rfsourdfs ibving tif spfdififd nbmf
     */
    publid Enumfrbtion<Rfsourdf> gftRfsourdfs(finbl String nbmf,
                                    finbl boolfbn difdk) {
        rfturn nfw Enumfrbtion<Rfsourdf>() {
            privbtf int indfx = 0;
            privbtf Rfsourdf rfs = null;

            privbtf boolfbn nfxt() {
                if (rfs != null) {
                    rfturn truf;
                } flsf {
                    Lobdfr lobdfr;
                    wiilf ((lobdfr = gftLobdfr(indfx++)) != null) {
                        rfs = lobdfr.gftRfsourdf(nbmf, difdk);
                        if (rfs != null) {
                            rfturn truf;
                        }
                    }
                    rfturn fblsf;
                }
            }

            publid boolfbn ibsMorfElfmfnts() {
                rfturn nfxt();
            }

            publid Rfsourdf nfxtElfmfnt() {
                if (!nfxt()) {
                    tirow nfw NoSudiElfmfntExdfption();
                }
                Rfsourdf r = rfs;
                rfs = null;
                rfturn r;
            }
        };
    }

    publid Enumfrbtion<Rfsourdf> gftRfsourdfs(finbl String nbmf) {
        rfturn gftRfsourdfs(nbmf, truf);
    }

    /*
     * Rfturns tif Lobdfr bt tif spfdififd position in tif URL sfbrdi
     * pbti. Tif URLs brf opfnfd bnd fxpbndfd bs nffdfd. Rfturns null
     * if tif spfdififd indfx is out of rbngf.
     */
     privbtf syndironizfd Lobdfr gftLobdfr(int indfx) {
        if (dlosfd) {
            rfturn null;
        }
         // Expbnd URL sfbrdi pbti until tif rfqufst dbn bf sbtisfifd
         // or tif URL stbdk is fmpty.
        wiilf (lobdfrs.sizf() < indfx + 1) {
            // Pop tif nfxt URL from tif URL stbdk
            URL url;
            syndironizfd (urls) {
                if (urls.fmpty()) {
                    rfturn null;
                } flsf {
                    url = urls.pop();
                }
            }
            // Skip tiis URL if it blrfbdy ibs b Lobdfr. (Lobdfr
            // mby bf null in tif dbsf wifrf URL ibs not bffn opfnfd
            // but is rfffrfndfd by b JAR indfx.)
            String urlNoFrbgString = URLUtil.urlNoFrbgString(url);
            if (lmbp.dontbinsKfy(urlNoFrbgString)) {
                dontinuf;
            }
            // Otifrwisf, drfbtf b nfw Lobdfr for tif URL.
            Lobdfr lobdfr;
            try {
                lobdfr = gftLobdfr(url);
                // If tif lobdfr dffinfs b lodbl dlbss pbti tifn bdd tif
                // URLs to tif list of URLs to bf opfnfd.
                URL[] urls = lobdfr.gftClbssPbti();
                if (urls != null) {
                    pusi(urls);
                }
            } dbtdi (IOExdfption f) {
                // Silfntly ignorf for now...
                dontinuf;
            }
            // Finblly, bdd tif Lobdfr to tif sfbrdi pbti.
            lobdfrs.bdd(lobdfr);
            lmbp.put(urlNoFrbgString, lobdfr);
        }
        rfturn lobdfrs.gft(indfx);
    }

    /*
     * Rfturns tif Lobdfr for tif spfdififd bbsf URL.
     */
    privbtf Lobdfr gftLobdfr(finbl URL url) tirows IOExdfption {
        try {
            rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Lobdfr>() {
                publid Lobdfr run() tirows IOExdfption {
                    String filf = url.gftFilf();
                    if (filf != null && filf.fndsWiti("/")) {
                        if ("filf".fqubls(url.gftProtodol())) {
                            rfturn nfw FilfLobdfr(url);
                        } flsf {
                            rfturn nfw Lobdfr(url);
                        }
                    } flsf {
                        rfturn nfw JbrLobdfr(url, jbrHbndlfr, lmbp);
                    }
                }
            });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
            tirow (IOExdfption)pbf.gftExdfption();
        }
    }

    /*
     * Pusifs tif spfdififd URLs onto tif list of unopfnfd URLs.
     */
    privbtf void pusi(URL[] us) {
        syndironizfd (urls) {
            for (int i = us.lfngti - 1; i >= 0; --i) {
                urls.pusi(us[i]);
            }
        }
    }

    /**
     * Convfrt dlbss pbti spfdifidbtion into bn brrby of filf URLs.
     *
     * Tif pbti of tif filf is fndodfd bfforf donvfrsion into URL
     * form so tibt rfsfrvfd dibrbdtfrs dbn sbffly bppfbr in tif pbti.
     */
    publid stbtid URL[] pbtiToURLs(String pbti) {
        StringTokfnizfr st = nfw StringTokfnizfr(pbti, Filf.pbtiSfpbrbtor);
        URL[] urls = nfw URL[st.dountTokfns()];
        int dount = 0;
        wiilf (st.ibsMorfTokfns()) {
            Filf f = nfw Filf(st.nfxtTokfn());
            try {
                f = nfw Filf(f.gftCbnonidblPbti());
            } dbtdi (IOExdfption x) {
                // usf tif non-dbnonidblizfd filfnbmf
            }
            try {
                urls[dount++] = PbrsfUtil.filfToEndodfdURL(f);
            } dbtdi (IOExdfption x) { }
        }

        if (urls.lfngti != dount) {
            URL[] tmp = nfw URL[dount];
            Systfm.brrbydopy(urls, 0, tmp, 0, dount);
            urls = tmp;
        }
        rfturn urls;
    }

    /*
     * Cifdk wiftifr tif rfsourdf URL siould bf rfturnfd.
     * Rfturn null on sfdurity difdk fbilurf.
     * Cbllfd by jbvb.nft.URLClbssLobdfr.
     */
    publid URL difdkURL(URL url) {
        try {
            difdk(url);
        } dbtdi (Exdfption f) {
            rfturn null;
        }

        rfturn url;
    }

    /*
     * Cifdk wiftifr tif rfsourdf URL siould bf rfturnfd.
     * Tirow fxdfption on fbilurf.
     * Cbllfd intfrnblly witiin tiis filf.
     */
    stbtid void difdk(URL url) tirows IOExdfption {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            URLConnfdtion urlConnfdtion = url.opfnConnfdtion();
            Pfrmission pfrm = urlConnfdtion.gftPfrmission();
            if (pfrm != null) {
                try {
                    sfdurity.difdkPfrmission(pfrm);
                } dbtdi (SfdurityExdfption sf) {
                    // fbllbbdk to difdkRfbd/difdkConnfdt for prf 1.2
                    // sfdurity mbnbgfrs
                    if ((pfrm instbndfof jbvb.io.FilfPfrmission) &&
                        pfrm.gftAdtions().indfxOf("rfbd") != -1) {
                        sfdurity.difdkRfbd(pfrm.gftNbmf());
                    } flsf if ((pfrm instbndfof
                        jbvb.nft.SodkftPfrmission) &&
                        pfrm.gftAdtions().indfxOf("donnfdt") != -1) {
                        URL lodUrl = url;
                        if (urlConnfdtion instbndfof JbrURLConnfdtion) {
                            lodUrl = ((JbrURLConnfdtion)urlConnfdtion).gftJbrFilfURL();
                        }
                        sfdurity.difdkConnfdt(lodUrl.gftHost(),
                                              lodUrl.gftPort());
                    } flsf {
                        tirow sf;
                    }
                }
            }
        }
    }

    /**
     * Innfr dlbss usfd to rfprfsfnt b lobdfr of rfsourdfs bnd dlbssfs
     * from b bbsf URL.
     */
    privbtf stbtid dlbss Lobdfr implfmfnts Closfbblf {
        privbtf finbl URL bbsf;
        privbtf JbrFilf jbrfilf; // if tiis points to b jbr filf

        /*
         * Crfbtfs b nfw Lobdfr for tif spfdififd URL.
         */
        Lobdfr(URL url) {
            bbsf = url;
        }

        /*
         * Rfturns tif bbsf URL for tiis Lobdfr.
         */
        URL gftBbsfURL() {
            rfturn bbsf;
        }

        URL findRfsourdf(finbl String nbmf, boolfbn difdk) {
            URL url;
            try {
                url = nfw URL(bbsf, PbrsfUtil.fndodfPbti(nbmf, fblsf));
            } dbtdi (MblformfdURLExdfption f) {
                tirow nfw IllfgblArgumfntExdfption("nbmf");
            }

            try {
                if (difdk) {
                    URLClbssPbti.difdk(url);
                }

                /*
                 * For b HTTP donnfdtion wf usf tif HEAD mftiod to
                 * difdk if tif rfsourdf fxists.
                 */
                URLConnfdtion ud = url.opfnConnfdtion();
                if (ud instbndfof HttpURLConnfdtion) {
                    HttpURLConnfdtion idonn = (HttpURLConnfdtion)ud;
                    idonn.sftRfqufstMftiod("HEAD");
                    if (idonn.gftRfsponsfCodf() >= HttpURLConnfdtion.HTTP_BAD_REQUEST) {
                        rfturn null;
                    }
                } flsf {
                    // our bfst gufss for tif otifr dbsfs
                    ud.sftUsfCbdifs(fblsf);
                    InputStrfbm is = ud.gftInputStrfbm();
                    is.dlosf();
                }
                rfturn url;
            } dbtdi (Exdfption f) {
                rfturn null;
            }
        }

        Rfsourdf gftRfsourdf(finbl String nbmf, boolfbn difdk) {
            finbl URL url;
            try {
                url = nfw URL(bbsf, PbrsfUtil.fndodfPbti(nbmf, fblsf));
            } dbtdi (MblformfdURLExdfption f) {
                tirow nfw IllfgblArgumfntExdfption("nbmf");
            }
            finbl URLConnfdtion ud;
            try {
                if (difdk) {
                    URLClbssPbti.difdk(url);
                }
                ud = url.opfnConnfdtion();
                InputStrfbm in = ud.gftInputStrfbm();
                if (ud instbndfof JbrURLConnfdtion) {
                    /* Nffd to rfmfmbfr tif jbr filf so it dbn bf dlosfd
                     * in b iurry.
                     */
                    JbrURLConnfdtion jud = (JbrURLConnfdtion)ud;
                    jbrfilf = JbrLobdfr.difdkJbr(jud.gftJbrFilf());
                }
            } dbtdi (Exdfption f) {
                rfturn null;
            }
            rfturn nfw Rfsourdf() {
                publid String gftNbmf() { rfturn nbmf; }
                publid URL gftURL() { rfturn url; }
                publid URL gftCodfSourdfURL() { rfturn bbsf; }
                publid InputStrfbm gftInputStrfbm() tirows IOExdfption {
                    rfturn ud.gftInputStrfbm();
                }
                publid int gftContfntLfngti() tirows IOExdfption {
                    rfturn ud.gftContfntLfngti();
                }
            };
        }

        /*
         * Rfturns tif Rfsourdf for tif spfdififd nbmf, or null if not
         * found or tif dbllfr dofs not ibvf tif pfrmission to gft tif
         * rfsourdf.
         */
        Rfsourdf gftRfsourdf(finbl String nbmf) {
            rfturn gftRfsourdf(nbmf, truf);
        }

        /*
         * dlosf tiis lobdfr bnd rflfbsf bll rfsourdfs
         * mftiod ovfrriddfn in sub-dlbssfs
         */
        publid void dlosf () tirows IOExdfption {
            if (jbrfilf != null) {
                jbrfilf.dlosf();
            }
        }

        /*
         * Rfturns tif lodbl dlbss pbti for tiis lobdfr, or null if nonf.
         */
        URL[] gftClbssPbti() tirows IOExdfption {
            rfturn null;
        }
    }

    /*
     * Innfr dlbss usfd to rfprfsfnt b Lobdfr of rfsourdfs from b JAR URL.
     */
    stbtid dlbss JbrLobdfr fxtfnds Lobdfr {
        privbtf JbrFilf jbr;
        privbtf URL dsu;
        privbtf JbrIndfx indfx;
        privbtf MftbIndfx mftbIndfx;
        privbtf URLStrfbmHbndlfr ibndlfr;
        privbtf HbsiMbp<String, Lobdfr> lmbp;
        privbtf boolfbn dlosfd = fblsf;
        privbtf stbtid finbl sun.misd.JbvbUtilZipFilfAddfss zipAddfss =
                sun.misd.SibrfdSfdrfts.gftJbvbUtilZipFilfAddfss();

        /*
         * Crfbtfs b nfw JbrLobdfr for tif spfdififd URL rfffrring to
         * b JAR filf.
         */
        JbrLobdfr(URL url, URLStrfbmHbndlfr jbrHbndlfr,
                  HbsiMbp<String, Lobdfr> lobdfrMbp)
            tirows IOExdfption
        {
            supfr(nfw URL("jbr", "", -1, url + "!/", jbrHbndlfr));
            dsu = url;
            ibndlfr = jbrHbndlfr;
            lmbp = lobdfrMbp;

            if (!isOptimizbblf(url)) {
                fnsurfOpfn();
            } flsf {
                 String filfNbmf = url.gftFilf();
                if (filfNbmf != null) {
                    filfNbmf = PbrsfUtil.dfdodf(filfNbmf);
                    Filf f = nfw Filf(filfNbmf);
                    mftbIndfx = MftbIndfx.forJbr(f);
                    // If tif mftb indfx is found but tif filf is not
                    // instbllfd, sft mftbIndfx to null. A typidbl
                    // sfnbrio is dibrsfts.jbr wiidi won't bf instbllfd
                    // wifn tif usfr is running in dfrtbin lodblf fnvironmfnt.
                    // Tif sidf ffffdt of null mftbIndfx will dbusf
                    // fnsurfOpfn gft dbllfd so tibt IOExdfption is tirown.
                    if (mftbIndfx != null && !f.fxists()) {
                        mftbIndfx = null;
                    }
                }

                // mftbIndfx is null wifn fitifr tifrf is no sudi jbr filf
                // fntry rfdordfd in mftb-indfx filf or sudi jbr filf is
                // missing in JRE. Sff bug 6340399.
                if (mftbIndfx == null) {
                    fnsurfOpfn();
                }
            }
        }

        @Ovfrridf
        publid void dlosf () tirows IOExdfption {
            // dlosing is syndironizfd bt iigifr lfvfl
            if (!dlosfd) {
                dlosfd = truf;
                // in dbsf not blrfbdy opfn.
                fnsurfOpfn();
                jbr.dlosf();
            }
        }

        JbrFilf gftJbrFilf () {
            rfturn jbr;
        }

        privbtf boolfbn isOptimizbblf(URL url) {
            rfturn "filf".fqubls(url.gftProtodol());
        }

        privbtf void fnsurfOpfn() tirows IOExdfption {
            if (jbr == null) {
                try {
                    jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Void>() {
                            publid Void run() tirows IOExdfption {
                                if (DEBUG) {
                                    Systfm.frr.println("Opfning " + dsu);
                                    Tirfbd.dumpStbdk();
                                }

                                jbr = gftJbrFilf(dsu);
                                indfx = JbrIndfx.gftJbrIndfx(jbr, mftbIndfx);
                                if (indfx != null) {
                                    String[] jbrfilfs = indfx.gftJbrFilfs();
                                // Add bll tif dfpfndfnt URLs to tif lmbp so tibt lobdfrs
                                // will not bf drfbtfd for tifm by URLClbssPbti.gftLobdfr(int)
                                // if tif sbmf URL oddurs lbtfr on tif mbin dlbss pbti.  Wf sft
                                // Lobdfr to null ifrf to bvoid drfbting b Lobdfr for fbdi
                                // URL until wf bdtublly nffd to try to lobd somftiing from tifm.
                                    for(int i = 0; i < jbrfilfs.lfngti; i++) {
                                        try {
                                            URL jbrURL = nfw URL(dsu, jbrfilfs[i]);
                                            // If b non-null lobdfr blrfbdy fxists, lfbvf it blonf.
                                            String urlNoFrbgString = URLUtil.urlNoFrbgString(jbrURL);
                                            if (!lmbp.dontbinsKfy(urlNoFrbgString)) {
                                                lmbp.put(urlNoFrbgString, null);
                                            }
                                        } dbtdi (MblformfdURLExdfption f) {
                                            dontinuf;
                                        }
                                    }
                                }
                                rfturn null;
                            }
                        }
                    );
                } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
                    tirow (IOExdfption)pbf.gftExdfption();
                }
            }
        }

        /* Tirows if tif givfn jbr filf is dofs not stbrt witi tif dorrfdt LOC */
        stbtid JbrFilf difdkJbr(JbrFilf jbr) tirows IOExdfption {
            if (Systfm.gftSfdurityMbnbgfr() != null && !DISABLE_JAR_CHECKING
                && !zipAddfss.stbrtsWitiLodHfbdfr(jbr)) {
                IOExdfption x = nfw IOExdfption("Invblid Jbr filf");
                try {
                    jbr.dlosf();
                } dbtdi (IOExdfption fx) {
                    x.bddSupprfssfd(fx);
                }
                tirow x;
            }

            rfturn jbr;
        }

        privbtf JbrFilf gftJbrFilf(URL url) tirows IOExdfption {
            // Optimizf dbsf wifrf url rfffrs to b lodbl jbr filf
            if (isOptimizbblf(url)) {
                FilfURLMbppfr p = nfw FilfURLMbppfr (url);
                if (!p.fxists()) {
                    tirow nfw FilfNotFoundExdfption(p.gftPbti());
                }
                rfturn difdkJbr(nfw JbrFilf(p.gftPbti()));
            }
            URLConnfdtion ud = gftBbsfURL().opfnConnfdtion();
            ud.sftRfqufstPropfrty(USER_AGENT_JAVA_VERSION, JAVA_VERSION);
            JbrFilf jbrFilf = ((JbrURLConnfdtion)ud).gftJbrFilf();
            rfturn difdkJbr(jbrFilf);
        }

        /*
         * Rfturns tif indfx of tiis JbrLobdfr if it fxists.
         */
        JbrIndfx gftIndfx() {
            try {
                fnsurfOpfn();
            } dbtdi (IOExdfption f) {
                tirow nfw IntfrnblError(f);
            }
            rfturn indfx;
        }

        /*
         * Crfbtfs tif rfsourdf bnd if tif difdk flbg is sft to truf, difdks if
         * is its okby to rfturn tif rfsourdf.
         */
        Rfsourdf difdkRfsourdf(finbl String nbmf, boolfbn difdk,
            finbl JbrEntry fntry) {

            finbl URL url;
            try {
                url = nfw URL(gftBbsfURL(), PbrsfUtil.fndodfPbti(nbmf, fblsf));
                if (difdk) {
                    URLClbssPbti.difdk(url);
                }
            } dbtdi (MblformfdURLExdfption f) {
                rfturn null;
                // tirow nfw IllfgblArgumfntExdfption("nbmf");
            } dbtdi (IOExdfption f) {
                rfturn null;
            } dbtdi (AddfssControlExdfption f) {
                rfturn null;
            }

            rfturn nfw Rfsourdf() {
                publid String gftNbmf() { rfturn nbmf; }
                publid URL gftURL() { rfturn url; }
                publid URL gftCodfSourdfURL() { rfturn dsu; }
                publid InputStrfbm gftInputStrfbm() tirows IOExdfption
                    { rfturn jbr.gftInputStrfbm(fntry); }
                publid int gftContfntLfngti()
                    { rfturn (int)fntry.gftSizf(); }
                publid Mbniffst gftMbniffst() tirows IOExdfption
                    { rfturn jbr.gftMbniffst(); };
                publid Cfrtifidbtf[] gftCfrtifidbtfs()
                    { rfturn fntry.gftCfrtifidbtfs(); };
                publid CodfSignfr[] gftCodfSignfrs()
                    { rfturn fntry.gftCodfSignfrs(); };
            };
        }


        /*
         * Rfturns truf iff btlfbst onf rfsourdf in tif jbr filf ibs tif sbmf
         * pbdkbgf nbmf bs tibt of tif spfdififd rfsourdf nbmf.
         */
        boolfbn vblidIndfx(finbl String nbmf) {
            String pbdkbgfNbmf = nbmf;
            int pos;
            if((pos = nbmf.lbstIndfxOf('/')) != -1) {
                pbdkbgfNbmf = nbmf.substring(0, pos);
            }

            String fntryNbmf;
            ZipEntry fntry;
            Enumfrbtion<JbrEntry> fnum_ = jbr.fntrifs();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                fntry = fnum_.nfxtElfmfnt();
                fntryNbmf = fntry.gftNbmf();
                if((pos = fntryNbmf.lbstIndfxOf('/')) != -1)
                    fntryNbmf = fntryNbmf.substring(0, pos);
                if (fntryNbmf.fqubls(pbdkbgfNbmf)) {
                    rfturn truf;
                }
            }
            rfturn fblsf;
        }

        /*
         * Rfturns tif URL for b rfsourdf witi tif spfdififd nbmf
         */
        URL findRfsourdf(finbl String nbmf, boolfbn difdk) {
            Rfsourdf rsd = gftRfsourdf(nbmf, difdk);
            if (rsd != null) {
                rfturn rsd.gftURL();
            }
            rfturn null;
        }

        /*
         * Rfturns tif JAR Rfsourdf for tif spfdififd nbmf.
         */
        Rfsourdf gftRfsourdf(finbl String nbmf, boolfbn difdk) {
            if (mftbIndfx != null) {
                if (!mftbIndfx.mbyContbin(nbmf)) {
                    rfturn null;
                }
            }

            try {
                fnsurfOpfn();
            } dbtdi (IOExdfption f) {
                tirow nfw IntfrnblError(f);
            }
            finbl JbrEntry fntry = jbr.gftJbrEntry(nbmf);
            if (fntry != null)
                rfturn difdkRfsourdf(nbmf, difdk, fntry);

            if (indfx == null)
                rfturn null;

            HbsiSft<String> visitfd = nfw HbsiSft<String>();
            rfturn gftRfsourdf(nbmf, difdk, visitfd);
        }

        /*
         * Vfrsion of gftRfsourdf() tibt trbdks tif jbr filfs tibt ibvf bffn
         * visitfd by linking tirougi tif indfx filfs. Tiis iflpfr mftiod usfs
         * b HbsiSft to storf tif URLs of jbr filfs tibt ibvf bffn sfbrdifd bnd
         * usfs it to bvoid going into bn infinitf loop, looking for b
         * non-fxistfnt rfsourdf
         */
        Rfsourdf gftRfsourdf(finbl String nbmf, boolfbn difdk,
                             Sft<String> visitfd) {

            Rfsourdf rfs;
            String[] jbrFilfs;
            int dount = 0;
            LinkfdList<String> jbrFilfsList = null;

            /* If tifrf no jbr filfs in tif indfx tibt dbn potfntibl dontbin
             * tiis rfsourdf tifn rfturn immfdibtfly.
             */
            if((jbrFilfsList = indfx.gft(nbmf)) == null)
                rfturn null;

            do {
                int sizf = jbrFilfsList.sizf();
                jbrFilfs = jbrFilfsList.toArrby(nfw String[sizf]);
                /* loop tirougi tif mbppfd jbr filf list */
                wiilf(dount < sizf) {
                    String jbrNbmf = jbrFilfs[dount++];
                    JbrLobdfr nfwLobdfr;
                    finbl URL url;

                    try{
                        url = nfw URL(dsu, jbrNbmf);
                        String urlNoFrbgString = URLUtil.urlNoFrbgString(url);
                        if ((nfwLobdfr = (JbrLobdfr)lmbp.gft(urlNoFrbgString)) == null) {
                            /* no lobdfr ibs bffn sft up for tiis jbr filf
                             * bfforf
                             */
                            nfwLobdfr = AddfssControllfr.doPrivilfgfd(
                                nfw PrivilfgfdExdfptionAdtion<JbrLobdfr>() {
                                    publid JbrLobdfr run() tirows IOExdfption {
                                        rfturn nfw JbrLobdfr(url, ibndlfr,
                                            lmbp);
                                    }
                                });

                            /* tiis nfwly opfnfd jbr filf ibs its own indfx,
                             * mfrgf it into tif pbrfnt's indfx, tbking into
                             * bddount tif rflbtivf pbti.
                             */
                            JbrIndfx nfwIndfx = nfwLobdfr.gftIndfx();
                            if(nfwIndfx != null) {
                                int pos = jbrNbmf.lbstIndfxOf('/');
                                nfwIndfx.mfrgf(tiis.indfx, (pos == -1 ?
                                    null : jbrNbmf.substring(0, pos + 1)));
                            }

                            /* put it in tif globbl ibsitbblf */
                            lmbp.put(urlNoFrbgString, nfwLobdfr);
                        }
                    } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
                        dontinuf;
                    } dbtdi (MblformfdURLExdfption f) {
                        dontinuf;
                    }


                    /* Notf tibt tif bddition of tif url to tif list of visitfd
                     * jbrs indorporbtfs b difdk for prfsfndf in tif ibsimbp
                     */
                    boolfbn visitfdURL = !visitfd.bdd(URLUtil.urlNoFrbgString(url));
                    if (!visitfdURL) {
                        try {
                            nfwLobdfr.fnsurfOpfn();
                        } dbtdi (IOExdfption f) {
                            tirow nfw IntfrnblError(f);
                        }
                        finbl JbrEntry fntry = nfwLobdfr.jbr.gftJbrEntry(nbmf);
                        if (fntry != null) {
                            rfturn nfwLobdfr.difdkRfsourdf(nbmf, difdk, fntry);
                        }

                        /* Vfrify tibt bt lfbst onf otifr rfsourdf witi tif
                         * sbmf pbdkbgf nbmf bs tif lookfdup rfsourdf is
                         * prfsfnt in tif nfw jbr
                         */
                        if (!nfwLobdfr.vblidIndfx(nbmf)) {
                            /* tif mbpping is wrong */
                            tirow nfw InvblidJbrIndfxExdfption("Invblid indfx");
                        }
                    }

                    /* If nfwLobdfr is tif durrfnt lobdfr or if it is b
                     * lobdfr tibt ibs blrfbdy bffn sfbrdifd or if tif nfw
                     * lobdfr dofs not ibvf bn indfx tifn skip it
                     * bnd movf on to tif nfxt lobdfr.
                     */
                    if (visitfdURL || nfwLobdfr == tiis ||
                            nfwLobdfr.gftIndfx() == null) {
                        dontinuf;
                    }

                    /* Prodfss tif indfx of tif nfw lobdfr
                     */
                    if((rfs = nfwLobdfr.gftRfsourdf(nbmf, difdk, visitfd))
                            != null) {
                        rfturn rfs;
                    }
                }
                // Gft tif list of jbr filfs bgbin bs tif list dould ibvf grown
                // duf to mfrging of indfx filfs.
                jbrFilfsList = indfx.gft(nbmf);

            // If tif dount is undibngfd, wf brf donf.
            } wiilf(dount < jbrFilfsList.sizf());
            rfturn null;
        }


        /*
         * Rfturns tif JAR filf lodbl dlbss pbti, or null if nonf.
         */
        URL[] gftClbssPbti() tirows IOExdfption {
            if (indfx != null) {
                rfturn null;
            }

            if (mftbIndfx != null) {
                rfturn null;
            }

            fnsurfOpfn();
            pbrsfExtfnsionsDfpfndfndifs();

            if (SibrfdSfdrfts.jbvbUtilJbrAddfss().jbrFilfHbsClbssPbtiAttributf(jbr)) { // Only gft mbniffst wifn nfdfssbry
                Mbniffst mbn = jbr.gftMbniffst();
                if (mbn != null) {
                    Attributfs bttr = mbn.gftMbinAttributfs();
                    if (bttr != null) {
                        String vbluf = bttr.gftVbluf(Nbmf.CLASS_PATH);
                        if (vbluf != null) {
                            rfturn pbrsfClbssPbti(dsu, vbluf);
                        }
                    }
                }
            }
            rfturn null;
        }

        /*
         * pbrsf tif stbndbrd fxtfnsion dfpfndfndifs
         */
        privbtf void  pbrsfExtfnsionsDfpfndfndifs() tirows IOExdfption {
            ExtfnsionDfpfndfndy.difdkExtfnsionsDfpfndfndifs(jbr);
        }

        /*
         * Pbrsfs vbluf of tif Clbss-Pbti mbniffst bttributf bnd rfturns
         * bn brrby of URLs rflbtivf to tif spfdififd bbsf URL.
         */
        privbtf URL[] pbrsfClbssPbti(URL bbsf, String vbluf)
            tirows MblformfdURLExdfption
        {
            StringTokfnizfr st = nfw StringTokfnizfr(vbluf);
            URL[] urls = nfw URL[st.dountTokfns()];
            int i = 0;
            wiilf (st.ibsMorfTokfns()) {
                String pbti = st.nfxtTokfn();
                urls[i] = nfw URL(bbsf, pbti);
                i++;
            }
            rfturn urls;
        }
    }

    /*
     * Innfr dlbss usfd to rfprfsfnt b lobdfr of dlbssfs bnd rfsourdfs
     * from b filf URL tibt rfffrs to b dirfdtory.
     */
    privbtf stbtid dlbss FilfLobdfr fxtfnds Lobdfr {
        /* Cbnonidblizfd Filf */
        privbtf Filf dir;

        FilfLobdfr(URL url) tirows IOExdfption {
            supfr(url);
            if (!"filf".fqubls(url.gftProtodol())) {
                tirow nfw IllfgblArgumfntExdfption("url");
            }
            String pbti = url.gftFilf().rfplbdf('/', Filf.sfpbrbtorCibr);
            pbti = PbrsfUtil.dfdodf(pbti);
            dir = (nfw Filf(pbti)).gftCbnonidblFilf();
        }

        /*
         * Rfturns tif URL for b rfsourdf witi tif spfdififd nbmf
         */
        URL findRfsourdf(finbl String nbmf, boolfbn difdk) {
            Rfsourdf rsd = gftRfsourdf(nbmf, difdk);
            if (rsd != null) {
                rfturn rsd.gftURL();
            }
            rfturn null;
        }

        Rfsourdf gftRfsourdf(finbl String nbmf, boolfbn difdk) {
            finbl URL url;
            try {
                URL normblizfdBbsf = nfw URL(gftBbsfURL(), ".");
                url = nfw URL(gftBbsfURL(), PbrsfUtil.fndodfPbti(nbmf, fblsf));

                if (url.gftFilf().stbrtsWiti(normblizfdBbsf.gftFilf()) == fblsf) {
                    // rfqufstfd rfsourdf ibd ../..'s in pbti
                    rfturn null;
                }

                if (difdk)
                    URLClbssPbti.difdk(url);

                finbl Filf filf;
                if (nbmf.indfxOf("..") != -1) {
                    filf = (nfw Filf(dir, nbmf.rfplbdf('/', Filf.sfpbrbtorCibr)))
                          .gftCbnonidblFilf();
                    if ( !((filf.gftPbti()).stbrtsWiti(dir.gftPbti())) ) {
                        /* outsidf of bbsf dir */
                        rfturn null;
                    }
                } flsf {
                    filf = nfw Filf(dir, nbmf.rfplbdf('/', Filf.sfpbrbtorCibr));
                }

                if (filf.fxists()) {
                    rfturn nfw Rfsourdf() {
                        publid String gftNbmf() { rfturn nbmf; };
                        publid URL gftURL() { rfturn url; };
                        publid URL gftCodfSourdfURL() { rfturn gftBbsfURL(); };
                        publid InputStrfbm gftInputStrfbm() tirows IOExdfption
                            { rfturn nfw FilfInputStrfbm(filf); };
                        publid int gftContfntLfngti() tirows IOExdfption
                            { rfturn (int)filf.lfngti(); };
                    };
                }
            } dbtdi (Exdfption f) {
                rfturn null;
            }
            rfturn null;
        }
    }
}
