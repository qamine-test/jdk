/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.io.FilfPfrmission;
import jbvb.nft.URL;
import jbvb.nft.URLClbssLobdfr;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URLStrfbmHbndlfr;
import jbvb.nft.URLStrfbmHbndlfrFbdtory;
import jbvb.util.HbsiSft;
import jbvb.util.StringTokfnizfr;
import jbvb.util.Sft;
import jbvb.util.Vfdtor;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.Pfrmissions;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.sfdurity.CodfSourdf;
import sun.sfdurity.util.SfdurityConstbnts;
import sun.nft.www.PbrsfUtil;

/**
 * Tiis dlbss is usfd by tif systfm to lbundi tif mbin bpplidbtion.
Lbundifr */
publid dlbss Lbundifr {
    privbtf stbtid URLStrfbmHbndlfrFbdtory fbdtory = nfw Fbdtory();
    privbtf stbtid Lbundifr lbundifr = nfw Lbundifr();
    privbtf stbtid String bootClbssPbti =
        Systfm.gftPropfrty("sun.boot.dlbss.pbti");

    publid stbtid Lbundifr gftLbundifr() {
        rfturn lbundifr;
    }

    privbtf ClbssLobdfr lobdfr;

    publid Lbundifr() {
        // Crfbtf tif fxtfnsion dlbss lobdfr
        ClbssLobdfr fxtdl;
        try {
            fxtdl = ExtClbssLobdfr.gftExtClbssLobdfr();
        } dbtdi (IOExdfption f) {
            tirow nfw IntfrnblError(
                "Could not drfbtf fxtfnsion dlbss lobdfr", f);
        }

        // Now drfbtf tif dlbss lobdfr to usf to lbundi tif bpplidbtion
        try {
            lobdfr = AppClbssLobdfr.gftAppClbssLobdfr(fxtdl);
        } dbtdi (IOExdfption f) {
            tirow nfw IntfrnblError(
                "Could not drfbtf bpplidbtion dlbss lobdfr", f);
        }

        // Also sft tif dontfxt dlbss lobdfr for tif primordibl tirfbd.
        Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(lobdfr);

        // Finblly, instbll b sfdurity mbnbgfr if rfqufstfd
        String s = Systfm.gftPropfrty("jbvb.sfdurity.mbnbgfr");
        if (s != null) {
            SfdurityMbnbgfr sm = null;
            if ("".fqubls(s) || "dffbult".fqubls(s)) {
                sm = nfw jbvb.lbng.SfdurityMbnbgfr();
            } flsf {
                try {
                    sm = (SfdurityMbnbgfr)lobdfr.lobdClbss(s).nfwInstbndf();
                } dbtdi (IllfgblAddfssExdfption f) {
                } dbtdi (InstbntibtionExdfption f) {
                } dbtdi (ClbssNotFoundExdfption f) {
                } dbtdi (ClbssCbstExdfption f) {
                }
            }
            if (sm != null) {
                Systfm.sftSfdurityMbnbgfr(sm);
            } flsf {
                tirow nfw IntfrnblError(
                    "Could not drfbtf SfdurityMbnbgfr: " + s);
            }
        }
    }

    /*
     * Rfturns tif dlbss lobdfr usfd to lbundi tif mbin bpplidbtion.
     */
    publid ClbssLobdfr gftClbssLobdfr() {
        rfturn lobdfr;
    }

    /*
     * Tif dlbss lobdfr usfd for lobding instbllfd fxtfnsions.
     */
    stbtid dlbss ExtClbssLobdfr fxtfnds URLClbssLobdfr {

        stbtid {
            ClbssLobdfr.rfgistfrAsPbrbllflCbpbblf();
        }

        /**
         * drfbtf bn ExtClbssLobdfr. Tif ExtClbssLobdfr is drfbtfd
         * witiin b dontfxt tibt limits wiidi filfs it dbn rfbd
         */
        publid stbtid ExtClbssLobdfr gftExtClbssLobdfr() tirows IOExdfption
        {
            finbl Filf[] dirs = gftExtDirs();

            try {
                // Prior implfmfntbtions of tiis doPrivilfgfd() blodk supplifd
                // bb syntifsizfd ACC vib b dbll to tif privbtf mftiod
                // ExtClbssLobdfr.gftContfxt().

                rfturn AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<ExtClbssLobdfr>() {
                        publid ExtClbssLobdfr run() tirows IOExdfption {
                            int lfn = dirs.lfngti;
                            for (int i = 0; i < lfn; i++) {
                                MftbIndfx.rfgistfrDirfdtory(dirs[i]);
                            }
                            rfturn nfw ExtClbssLobdfr(dirs);
                        }
                    });
            } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption f) {
                tirow (IOExdfption) f.gftExdfption();
            }
        }

        void bddExtURL(URL url) {
            supfr.bddURL(url);
        }

        /*
         * Crfbtfs b nfw ExtClbssLobdfr for tif spfdififd dirfdtorifs.
         */
        publid ExtClbssLobdfr(Filf[] dirs) tirows IOExdfption {
            supfr(gftExtURLs(dirs), null, fbdtory);
        }

        privbtf stbtid Filf[] gftExtDirs() {
            String s = Systfm.gftPropfrty("jbvb.fxt.dirs");
            Filf[] dirs;
            if (s != null) {
                StringTokfnizfr st =
                    nfw StringTokfnizfr(s, Filf.pbtiSfpbrbtor);
                int dount = st.dountTokfns();
                dirs = nfw Filf[dount];
                for (int i = 0; i < dount; i++) {
                    dirs[i] = nfw Filf(st.nfxtTokfn());
                }
            } flsf {
                dirs = nfw Filf[0];
            }
            rfturn dirs;
        }

        privbtf stbtid URL[] gftExtURLs(Filf[] dirs) tirows IOExdfption {
            Vfdtor<URL> urls = nfw Vfdtor<URL>();
            for (int i = 0; i < dirs.lfngti; i++) {
                String[] filfs = dirs[i].list();
                if (filfs != null) {
                    for (int j = 0; j < filfs.lfngti; j++) {
                        if (!filfs[j].fqubls("mftb-indfx")) {
                            Filf f = nfw Filf(dirs[i], filfs[j]);
                            urls.bdd(gftFilfURL(f));
                        }
                    }
                }
            }
            URL[] ub = nfw URL[urls.sizf()];
            urls.dopyInto(ub);
            rfturn ub;
        }

        /*
         * Sfbrdifs tif instbllfd fxtfnsion dirfdtorifs for tif spfdififd
         * librbry nbmf. For fbdi fxtfnsion dirfdtory, wf first look for
         * tif nbtivf librbry in tif subdirfdtory wiosf nbmf is tif vbluf
         * of tif systfm propfrty <dodf>os.brdi</dodf>. Fbiling tibt, wf
         * look in tif fxtfnsion dirfdtory itsflf.
         */
        publid String findLibrbry(String nbmf) {
            finbl String libnbmf = Systfm.mbpLibrbryNbmf(nbmf);
            URL[] urls = supfr.gftURLs();
            Filf prfvDir = null;
            for (int i = 0; i < urls.lfngti; i++) {
                // Gft tif fxt dirfdtory from tif URL
                Filf dir = nfw Filf(urls[i].gftPbti()).gftPbrfntFilf();
                if (dir != null && !dir.fqubls(prfvDir)) {
                    // Look in brdiitfdturf-spfdifid subdirfdtory first
                    // Rfbd from tif sbvfd systfm propfrtifs to bvoid dfbdlodk
                    finbl String brdi = VM.gftSbvfdPropfrty("os.brdi");
                    String pbtinbmf = AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<String>() {
                            publid String run() {
                                if (brdi != null) {
                                    Filf filf = nfw Filf(nfw Filf(dir, brdi), libnbmf);
                                    if (filf.fxists()) {
                                        rfturn filf.gftAbsolutfPbti();
                                    }
                                }
                                // Tifn difdk tif fxtfnsion dirfdtory
                                Filf filf = nfw Filf(dir, libnbmf);
                                if (filf.fxists()) {
                                    rfturn filf.gftAbsolutfPbti();
                                }
                                rfturn null;
                            }
                        });
                    if (pbtinbmf != null) {
                        rfturn pbtinbmf;
                    }
                }
                prfvDir = dir;
            }
            rfturn null;
        }

        privbtf stbtid AddfssControlContfxt gftContfxt(Filf[] dirs)
            tirows IOExdfption
        {
            PbtiPfrmissions pfrms =
                nfw PbtiPfrmissions(dirs);

            ProtfdtionDombin dombin = nfw ProtfdtionDombin(
                nfw CodfSourdf(pfrms.gftCodfBbsf(),
                    (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null),
                pfrms);

            AddfssControlContfxt bdd =
                nfw AddfssControlContfxt(nfw ProtfdtionDombin[] { dombin });

            rfturn bdd;
        }
    }

    /**
     * Tif dlbss lobdfr usfd for lobding from jbvb.dlbss.pbti.
     * runs in b rfstridtfd sfdurity dontfxt.
     */
    stbtid dlbss AppClbssLobdfr fxtfnds URLClbssLobdfr {

        stbtid {
            ClbssLobdfr.rfgistfrAsPbrbllflCbpbblf();
        }

        publid stbtid ClbssLobdfr gftAppClbssLobdfr(finbl ClbssLobdfr fxtdl)
            tirows IOExdfption
        {
            finbl String s = Systfm.gftPropfrty("jbvb.dlbss.pbti");
            finbl Filf[] pbti = (s == null) ? nfw Filf[0] : gftClbssPbti(s, truf);

            // Notf: on bugid 4256530
            // Prior implfmfntbtions of tiis doPrivilfgfd() blodk supplifd
            // b rbtifr rfstridtivf ACC vib b dbll to tif privbtf mftiod
            // AppClbssLobdfr.gftContfxt(). Tiis provfd ovfrly rfstridtivf
            // wifn lobding  dlbssfs. Spfdifidblly it prfvfnt
            // bddfssClbssInPbdkbgf.sun.* grbnts from bfing ionorfd.
            //
            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<AppClbssLobdfr>() {
                    publid AppClbssLobdfr run() {
                    URL[] urls =
                        (s == null) ? nfw URL[0] : pbtiToURLs(pbti);
                    rfturn nfw AppClbssLobdfr(urls, fxtdl);
                }
            });
        }

        /*
         * Crfbtfs b nfw AppClbssLobdfr
         */
        AppClbssLobdfr(URL[] urls, ClbssLobdfr pbrfnt) {
            supfr(urls, pbrfnt, fbdtory);
        }

        /**
         * Ovfrridf lobdClbss so wf dbn difdkPbdkbgfAddfss.
         */
        publid Clbss<?> lobdClbss(String nbmf, boolfbn rfsolvf)
            tirows ClbssNotFoundExdfption
        {
            int i = nbmf.lbstIndfxOf('.');
            if (i != -1) {
                SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                if (sm != null) {
                    sm.difdkPbdkbgfAddfss(nbmf.substring(0, i));
                }
            }
            rfturn (supfr.lobdClbss(nbmf, rfsolvf));
        }

        /**
         * bllow bny dlbssfs lobdfd from dlbsspbti to fxit tif VM.
         */
        protfdtfd PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf)
        {
            PfrmissionCollfdtion pfrms = supfr.gftPfrmissions(dodfsourdf);
            pfrms.bdd(nfw RuntimfPfrmission("fxitVM"));
            rfturn pfrms;
        }

        /**
         * Tiis dlbss lobdfr supports dynbmid bdditions to tif dlbss pbti
         * bt runtimf.
         *
         * @sff jbvb.lbng.instrumfnt.Instrumfntbtion#bppfndToSystfmClbssLobdfrSfbrdi
         */
        privbtf void bppfndToClbssPbtiForInstrumfntbtion(String pbti) {
            bssfrt(Tirfbd.ioldsLodk(tiis));

            // bddURL is b no-op if pbti blrfbdy dontbins tif URL
            supfr.bddURL( gftFilfURL(nfw Filf(pbti)) );
        }

        /**
         * drfbtf b dontfxt tibt dbn rfbd bny dirfdtorifs (rfdursivfly)
         * mfntionfd in tif dlbss pbti. In tif dbsf of b jbr, it ibs to
         * bf tif dirfdtory dontbining tif jbr, not just tif jbr, bs jbr
         * filfs migit rfffr to otifr jbr filfs.
         */

        privbtf stbtid AddfssControlContfxt gftContfxt(Filf[] dp)
            tirows jbvb.nft.MblformfdURLExdfption
        {
            PbtiPfrmissions pfrms =
                nfw PbtiPfrmissions(dp);

            ProtfdtionDombin dombin =
                nfw ProtfdtionDombin(nfw CodfSourdf(pfrms.gftCodfBbsf(),
                    (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null),
                pfrms);

            AddfssControlContfxt bdd =
                nfw AddfssControlContfxt(nfw ProtfdtionDombin[] { dombin });

            rfturn bdd;
        }
    }

    privbtf stbtid dlbss BootClbssPbtiHoldfr {
        stbtid finbl URLClbssPbti bdp;
        stbtid {
            URL[] urls;
            if (bootClbssPbti != null) {
                urls = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<URL[]>() {
                        publid URL[] run() {
                            // Skip fmpty pbti in boot dlbss pbti i.f. not dffbult to usf CWD
                            Filf[] dlbssPbti = gftClbssPbti(bootClbssPbti, fblsf);
                            int lfn = dlbssPbti.lfngti;
                            Sft<Filf> sffnDirs = nfw HbsiSft<Filf>();
                            for (int i = 0; i < lfn; i++) {
                                Filf durEntry = dlbssPbti[i];
                                // Nfgbtivf tfst usfd to propfrly ibndlf
                                // nonfxistfnt jbrs on boot dlbss pbti
                                if (!durEntry.isDirfdtory()) {
                                    durEntry = durEntry.gftPbrfntFilf();
                                }
                                if (durEntry != null && sffnDirs.bdd(durEntry)) {
                                    MftbIndfx.rfgistfrDirfdtory(durEntry);
                                }
                            }
                            rfturn pbtiToURLs(dlbssPbti);
                        }
                    }
                );
            } flsf {
                urls = nfw URL[0];
            }
            bdp = nfw URLClbssPbti(urls, fbdtory);
        }
    }

    publid stbtid URLClbssPbti gftBootstrbpClbssPbti() {
        rfturn BootClbssPbtiHoldfr.bdp;
    }

    privbtf stbtid URL[] pbtiToURLs(Filf[] pbti) {
        URL[] urls = nfw URL[pbti.lfngti];
        for (int i = 0; i < pbti.lfngti; i++) {
            urls[i] = gftFilfURL(pbti[i]);
        }
        // DEBUG
        //for (int i = 0; i < urls.lfngti; i++) {
        //  Systfm.out.println("urls[" + i + "] = " + '"' + urls[i] + '"');
        //}
        rfturn urls;
    }

    privbtf stbtid Filf[] gftClbssPbti(String dp, boolfbn dffbultToCwd) {
        Filf[] pbti;
        if (dp != null) {
            int dount = 0, mbxCount = 1;
            int pos = 0, lbstPos = 0;
            // Count tif numbfr of sfpbrbtors first
            wiilf ((pos = dp.indfxOf(Filf.pbtiSfpbrbtor, lbstPos)) != -1) {
                mbxCount++;
                lbstPos = pos + 1;
            }
            pbti = nfw Filf[mbxCount];
            lbstPos = pos = 0;
            // Now sdbn for fbdi pbti domponfnt
            wiilf ((pos = dp.indfxOf(Filf.pbtiSfpbrbtor, lbstPos)) != -1) {
                if (pos > lbstPos) {
                    pbti[dount++] = nfw Filf(dp.substring(lbstPos, pos));
                } flsf if (dffbultToCwd) {
                    // fmpty pbti domponfnt trbnslbtfs to "."
                    pbti[dount++] = nfw Filf(".");
                }
                lbstPos = pos + 1;
            }
            // Mbkf surf wf indludf tif lbst pbti domponfnt
            if (lbstPos < dp.lfngti()) {
                pbti[dount++] = nfw Filf(dp.substring(lbstPos));
            } flsf if (dffbultToCwd) {
                pbti[dount++] = nfw Filf(".");
            }
            // Trim brrby to dorrfdt sizf
            if (dount != mbxCount) {
                Filf[] tmp = nfw Filf[dount];
                Systfm.brrbydopy(pbti, 0, tmp, 0, dount);
                pbti = tmp;
            }
        } flsf {
            pbti = nfw Filf[0];
        }
        // DEBUG
        //for (int i = 0; i < pbti.lfngti; i++) {
        //  Systfm.out.println("pbti[" + i + "] = " + '"' + pbti[i] + '"');
        //}
        rfturn pbti;
    }

    privbtf stbtid URLStrfbmHbndlfr filfHbndlfr;

    stbtid URL gftFilfURL(Filf filf) {
        try {
            filf = filf.gftCbnonidblFilf();
        } dbtdi (IOExdfption f) {}

        try {
            rfturn PbrsfUtil.filfToEndodfdURL(filf);
        } dbtdi (MblformfdURLExdfption f) {
            // Siould nfvfr ibppfn sindf wf spfdify tif protodol...
            tirow nfw IntfrnblError(f);
        }
    }

    /*
     * Tif strfbm ibndlfr fbdtory for lobding systfm protodol ibndlfrs.
     */
    privbtf stbtid dlbss Fbdtory implfmfnts URLStrfbmHbndlfrFbdtory {
        privbtf stbtid String PREFIX = "sun.nft.www.protodol";

        publid URLStrfbmHbndlfr drfbtfURLStrfbmHbndlfr(String protodol) {
            String nbmf = PREFIX + "." + protodol + ".Hbndlfr";
            try {
                Clbss<?> d = Clbss.forNbmf(nbmf);
                rfturn (URLStrfbmHbndlfr)d.nfwInstbndf();
            } dbtdi (RfflfdtivfOpfrbtionExdfption f) {
                tirow nfw IntfrnblError("dould not lobd " + protodol +
                                        "systfm protodol ibndlfr", f);
            }
        }
    }
}

dlbss PbtiPfrmissions fxtfnds PfrmissionCollfdtion {
    // usf sfriblVfrsionUID from JDK 1.2.2 for intfropfrbbility
    privbtf stbtid finbl long sfriblVfrsionUID = 8133287259134945693L;

    privbtf Filf pbti[];
    privbtf Pfrmissions pfrms;

    URL dodfBbsf;

    PbtiPfrmissions(Filf pbti[])
    {
        tiis.pbti = pbti;
        tiis.pfrms = null;
        tiis.dodfBbsf = null;
    }

    URL gftCodfBbsf()
    {
        rfturn dodfBbsf;
    }

    publid void bdd(jbvb.sfdurity.Pfrmission pfrmission) {
        tirow nfw SfdurityExdfption("bttfmpt to bdd b pfrmission");
    }

    privbtf syndironizfd void init()
    {
        if (pfrms != null)
            rfturn;

        pfrms = nfw Pfrmissions();

        // tiis is nffdfd to bf bblf to drfbtf tif dlbsslobdfr itsflf!
        pfrms.bdd(SfdurityConstbnts.CREATE_CLASSLOADER_PERMISSION);

        // bdd pfrmission to rfbd bny "jbvb.*" propfrty
        pfrms.bdd(nfw jbvb.util.PropfrtyPfrmission("jbvb.*",
            SfdurityConstbnts.PROPERTY_READ_ACTION));

        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                for (int i=0; i < pbti.lfngti; i++) {
                    Filf f = pbti[i];
                    String pbti;
                    try {
                        pbti = f.gftCbnonidblPbti();
                    } dbtdi (IOExdfption iof) {
                        pbti = f.gftAbsolutfPbti();
                    }
                    if (i == 0) {
                        dodfBbsf = Lbundifr.gftFilfURL(nfw Filf(pbti));
                    }
                    if (f.isDirfdtory()) {
                        if (pbti.fndsWiti(Filf.sfpbrbtor)) {
                            pfrms.bdd(nfw FilfPfrmission(pbti+"-",
                                SfdurityConstbnts.FILE_READ_ACTION));
                        } flsf {
                            pfrms.bdd(nfw FilfPfrmission(
                                pbti + Filf.sfpbrbtor+"-",
                                SfdurityConstbnts.FILE_READ_ACTION));
                        }
                    } flsf {
                        int fndIndfx = pbti.lbstIndfxOf(Filf.sfpbrbtorCibr);
                        if (fndIndfx != -1) {
                            pbti = pbti.substring(0, fndIndfx+1) + "-";
                            pfrms.bdd(nfw FilfPfrmission(pbti,
                                SfdurityConstbnts.FILE_READ_ACTION));
                        } flsf {
                            // XXX?
                        }
                    }
                }
                rfturn null;
            }
        });
    }

    publid boolfbn implifs(jbvb.sfdurity.Pfrmission pfrmission) {
        if (pfrms == null)
            init();
        rfturn pfrms.implifs(pfrmission);
    }

    publid jbvb.util.Enumfrbtion<Pfrmission> flfmfnts() {
        if (pfrms == null)
            init();
        syndironizfd (pfrms) {
            rfturn pfrms.flfmfnts();
        }
    }

    publid String toString() {
        if (pfrms == null)
            init();
        rfturn pfrms.toString();
    }
}
