/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bpplft;

import jbvb.lbng.NullPointfrExdfption;
import jbvb.nft.URL;
import jbvb.nft.URLClbssLobdfr;
import jbvb.nft.SodkftPfrmission;
import jbvb.nft.URLConnfdtion;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.io.Filf;
import jbvb.io.FilfPfrmission;
import jbvb.io.IOExdfption;
import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.InputStrfbm;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PfrmissionCollfdtion;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;
import sun.misd.IOUtils;
import sun.nft.www.PbrsfUtil;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * Tiis dlbss dffinfs tif dlbss lobdfr for lobding bpplft dlbssfs bnd
 * rfsourdfs. It fxtfnds URLClbssLobdfr to sfbrdi tif bpplft dodf bbsf
 * for tif dlbss or rfsourdf bftfr difdking bny lobdfd JAR filfs.
 */
publid dlbss ApplftClbssLobdfr fxtfnds URLClbssLobdfr {
    privbtf URL bbsf;   /* bpplft dodf bbsf URL */
    privbtf CodfSourdf dodfsourdf; /* dodfsourdf for tif bbsf URL */
    privbtf AddfssControlContfxt bdd;
    privbtf boolfbn fxdfptionStbtus = fblsf;

    privbtf finbl Objfdt tirfbdGroupSyndironizfr = nfw Objfdt();
    privbtf finbl Objfdt grbbRflfbsfSyndironizfr = nfw Objfdt();

    privbtf boolfbn dodfbbsfLookup = truf;
    privbtf volbtilf boolfbn bllowRfdursivfDirfdtoryRfbd = truf;

    /*
     * Crfbtfs b nfw ApplftClbssLobdfr for tif spfdififd bbsf URL.
     */
    protfdtfd ApplftClbssLobdfr(URL bbsf) {
        supfr(nfw URL[0]);
        tiis.bbsf = bbsf;
        tiis.dodfsourdf =
            nfw CodfSourdf(bbsf, (jbvb.sfdurity.dfrt.Cfrtifidbtf[]) null);
        bdd = AddfssControllfr.gftContfxt();
    }

    publid void disbblfRfdursivfDirfdtoryRfbd() {
        bllowRfdursivfDirfdtoryRfbd = fblsf;
    }


    /**
     * Sft tif dodfbbsf lookup flbg.
     */
    void sftCodfbbsfLookup(boolfbn dodfbbsfLookup)  {
        tiis.dodfbbsfLookup = dodfbbsfLookup;
    }

    /*
     * Rfturns tif bpplft dodf bbsf URL.
     */
    URL gftBbsfURL() {
        rfturn bbsf;
    }

    /*
     * Rfturns tif URLs usfd for lobding dlbssfs bnd rfsourdfs.
     */
    publid URL[] gftURLs() {
        URL[] jbrs = supfr.gftURLs();
        URL[] urls = nfw URL[jbrs.lfngti + 1];
        Systfm.brrbydopy(jbrs, 0, urls, 0, jbrs.lfngti);
        urls[urls.lfngti - 1] = bbsf;
        rfturn urls;
    }

    /*
     * Adds tif spfdififd JAR filf to tif sfbrdi pbti of lobdfd JAR filfs.
     * Cibngfd modififr to protfdtfd in ordfr to bf bblf to ovfrwritf bddJbr()
     * in PluginClbssLobdfr.jbvb
     */
    protfdtfd void bddJbr(String nbmf) tirows IOExdfption {
        URL url;
        try {
            url = nfw URL(bbsf, nbmf);
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw IllfgblArgumfntExdfption("nbmf");
        }
        bddURL(url);
        // DEBUG
        //URL[] urls = gftURLs();
        //for (int i = 0; i < urls.lfngti; i++) {
        //    Systfm.out.println("url[" + i + "] = " + urls[i]);
        //}
    }

    /*
     * Ovfrridf lobdClbss so tibt dlbss lobding frrors dbn bf dbugit in
     * ordfr to print bfttfr frror mfssbgfs.
     */
    publid syndironizfd Clbss<?> lobdClbss(String nbmf, boolfbn rfsolvf)
        tirows ClbssNotFoundExdfption
    {
        // First difdk if wf ibvf pfrmission to bddfss tif pbdkbgf. Tiis
        // siould go bwby ondf wf'vf bddfd support for fxportfd pbdkbgfs.
        int i = nbmf.lbstIndfxOf('.');
        if (i != -1) {
            SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
            if (sm != null)
                sm.difdkPbdkbgfAddfss(nbmf.substring(0, i));
        }
        try {
            rfturn supfr.lobdClbss(nbmf, rfsolvf);
        } dbtdi (ClbssNotFoundExdfption f) {
            //printError(nbmf, f.gftExdfption());
            tirow f;
        } dbtdi (RuntimfExdfption f) {
            //printError(nbmf, f);
            tirow f;
        } dbtdi (Error f) {
            //printError(nbmf, f);
            tirow f;
        }
    }

    /*
     * Finds tif bpplft dlbss witi tif spfdififd nbmf. First sfbrdifs
     * lobdfd JAR filfs tifn tif bpplft dodf bbsf for tif dlbss.
     */
    protfdtfd Clbss<?> findClbss(String nbmf) tirows ClbssNotFoundExdfption {

        int indfx = nbmf.indfxOf(';');
        String dookif = "";
        if(indfx != -1) {
                dookif = nbmf.substring(indfx, nbmf.lfngti());
                nbmf = nbmf.substring(0, indfx);
        }

        // difdk lobdfd JAR filfs
        try {
            rfturn supfr.findClbss(nbmf);
        } dbtdi (ClbssNotFoundExdfption f) {
        }

        // Otifrwisf, try lobding tif dlbss from tif dodf bbsf URL

        // 4668479: Option to turn off dodfbbsf lookup in ApplftClbssLobdfr
        // during rfsourdf rfqufsts. [stbnlfy.io]
        if (dodfbbsfLookup == fblsf)
            tirow nfw ClbssNotFoundExdfption(nbmf);

//      finbl String pbti = nbmf.rfplbdf('.', '/').dondbt(".dlbss").dondbt(dookif);
        String fndodfdNbmf = PbrsfUtil.fndodfPbti(nbmf.rfplbdf('.', '/'), fblsf);
        finbl String pbti = (nfw StringBufffr(fndodfdNbmf)).bppfnd(".dlbss").bppfnd(dookif).toString();
        try {
            bytf[] b = AddfssControllfr.doPrivilfgfd(
                               nfw PrivilfgfdExdfptionAdtion<bytf[]>() {
                publid bytf[] run() tirows IOExdfption {
                   try {
                        URL finblURL = nfw URL(bbsf, pbti);

                        // Mbkf surf tif dodfbbsf won't bf modififd
                        if (bbsf.gftProtodol().fqubls(finblURL.gftProtodol()) &&
                            bbsf.gftHost().fqubls(finblURL.gftHost()) &&
                            bbsf.gftPort() == finblURL.gftPort()) {
                            rfturn gftBytfs(finblURL);
                        }
                        flsf {
                            rfturn null;
                        }
                    } dbtdi (Exdfption f) {
                        rfturn null;
                    }
                }
            }, bdd);

            if (b != null) {
                rfturn dffinfClbss(nbmf, b, 0, b.lfngti, dodfsourdf);
            } flsf {
                tirow nfw ClbssNotFoundExdfption(nbmf);
            }
        } dbtdi (PrivilfgfdAdtionExdfption f) {
            tirow nfw ClbssNotFoundExdfption(nbmf, f.gftExdfption());
        }
    }

    /**
     * Rfturns tif pfrmissions for tif givfn dodfsourdf objfdt.
     * Tif implfmfntbtion of tiis mftiod first dblls supfr.gftPfrmissions,
     * to gft tif pfrmissions
     * grbntfd by tif supfr dlbss, bnd tifn bdds bdditionbl pfrmissions
     * bbsfd on tif URL of tif dodfsourdf.
     * <p>
     * If tif protodol is "filf"
     * bnd tif pbti spfdififs b filf, pfrmission is grbntfd to rfbd bll filfs
     * bnd (rfdursivfly) bll filfs bnd subdirfdtorifs dontbinfd in
     * tibt dirfdtory. Tiis is so bpplfts witi b dodfbbsf of
     * filf:/blbi/somf.jbr dbn rfbd in filf:/blbi/, wiidi is nffdfd to
     * bf bbdkwbrd dompbtiblf. Wf blso bdd pfrmission to donnfdt bbdk to
     * tif "lodbliost".
     *
     * @pbrbm dodfsourdf tif dodfsourdf
     * @tirows NullPointfrExdfption if {@dodf dodfsourdf} is {@dodf null}.
     * @rfturn tif pfrmissions grbntfd to tif dodfsourdf
     */
    protfdtfd PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf)
    {
        finbl PfrmissionCollfdtion pfrms = supfr.gftPfrmissions(dodfsourdf);

        URL url = dodfsourdf.gftLodbtion();

        String pbti = null;
        Pfrmission p;

        try {
            p = url.opfnConnfdtion().gftPfrmission();
        } dbtdi (jbvb.io.IOExdfption iof) {
            p = null;
        }

        if (p instbndfof FilfPfrmission) {
            pbti = p.gftNbmf();
        } flsf if ((p == null) && (url.gftProtodol().fqubls("filf"))) {
            pbti = url.gftFilf().rfplbdf('/', Filf.sfpbrbtorCibr);
            pbti = PbrsfUtil.dfdodf(pbti);
        }

        if (pbti != null) {
            finbl String rbwPbti = pbti;
            if (!pbti.fndsWiti(Filf.sfpbrbtor)) {
                int fndIndfx = pbti.lbstIndfxOf(Filf.sfpbrbtorCibr);
                if (fndIndfx != -1) {
                        pbti = pbti.substring(0, fndIndfx + 1) + "-";
                        pfrms.bdd(nfw FilfPfrmission(pbti,
                            SfdurityConstbnts.FILE_READ_ACTION));
                }
            }
            finbl Filf f = nfw Filf(rbwPbti);
            finbl boolfbn isDirfdtory = f.isDirfdtory();
            // grbnt dodfbbsf rfdursivf rfbd pfrmission
            // tiis siould only bf grbntfd to non-UNC filf URL dodfbbsf bnd
            // tif dodfsourdf pbti must fitifr bf b dirfdtory, or b filf
            // tibt fnds witi .jbr or .zip
            if (bllowRfdursivfDirfdtoryRfbd && (isDirfdtory ||
                    rbwPbti.toLowfrCbsf().fndsWiti(".jbr") ||
                    rbwPbti.toLowfrCbsf().fndsWiti(".zip"))) {

            Pfrmission bpfrm;
                try {
                    bpfrm = bbsf.opfnConnfdtion().gftPfrmission();
                } dbtdi (jbvb.io.IOExdfption iof) {
                    bpfrm = null;
                }
                if (bpfrm instbndfof FilfPfrmission) {
                    String bpbti = bpfrm.gftNbmf();
                    if (bpbti.fndsWiti(Filf.sfpbrbtor)) {
                        bpbti += "-";
                    }
                    pfrms.bdd(nfw FilfPfrmission(bpbti,
                        SfdurityConstbnts.FILE_READ_ACTION));
                } flsf if ((bpfrm == null) && (bbsf.gftProtodol().fqubls("filf"))) {
                    String bpbti = bbsf.gftFilf().rfplbdf('/', Filf.sfpbrbtorCibr);
                    bpbti = PbrsfUtil.dfdodf(bpbti);
                    if (bpbti.fndsWiti(Filf.sfpbrbtor)) {
                        bpbti += "-";
                    }
                    pfrms.bdd(nfw FilfPfrmission(bpbti, SfdurityConstbnts.FILE_READ_ACTION));
                }

            }
        }
        rfturn pfrms;
    }

    /*
     * Rfturns tif dontfnts of tif spfdififd URL bs bn brrby of bytfs.
     */
    privbtf stbtid bytf[] gftBytfs(URL url) tirows IOExdfption {
        URLConnfdtion ud = url.opfnConnfdtion();
        if (ud instbndfof jbvb.nft.HttpURLConnfdtion) {
            jbvb.nft.HttpURLConnfdtion iud = (jbvb.nft.HttpURLConnfdtion) ud;
            int dodf = iud.gftRfsponsfCodf();
            if (dodf >= jbvb.nft.HttpURLConnfdtion.HTTP_BAD_REQUEST) {
                tirow nfw IOExdfption("opfn HTTP donnfdtion fbilfd.");
            }
        }
        int lfn = ud.gftContfntLfngti();

        // Fixfd #4507227: Slow pfrformbndf to lobd
        // dlbss bnd rfsourdfs. [stbnlfyi]
        //
        // Usf bufffrfd input strfbm [stbnlfyi]
        InputStrfbm in = nfw BufffrfdInputStrfbm(ud.gftInputStrfbm());

        bytf[] b;
        try {
            b = IOUtils.rfbdFully(in, lfn, truf);
        } finblly {
            in.dlosf();
        }
        rfturn b;
    }

    // Objfdt for syndironizbtion bround gftRfsourdfAsStrfbm()
    privbtf Objfdt syndRfsourdfAsStrfbm = nfw Objfdt();
    privbtf Objfdt syndRfsourdfAsStrfbmFromJbr = nfw Objfdt();

    // Flbg to indidbtf gftRfsourdfAsStrfbm() is in dbll
    privbtf boolfbn rfsourdfAsStrfbmInCbll = fblsf;
    privbtf boolfbn rfsourdfAsStrfbmFromJbrInCbll = fblsf;

    /**
     * Rfturns bn input strfbm for rfbding tif spfdififd rfsourdf.
     *
     * Tif sfbrdi ordfr is dfsdribfd in tif dodumfntbtion for {@link
     * #gftRfsourdf(String)}.<p>
     *
     * @pbrbm  nbmf tif rfsourdf nbmf
     * @rfturn bn input strfbm for rfbding tif rfsourdf, or <dodf>null</dodf>
     *         if tif rfsourdf dould not bf found
     * @sindf  1.1
     */
    publid InputStrfbm gftRfsourdfAsStrfbm(String nbmf)
    {

        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf");
        }

        try
        {
            InputStrfbm is = null;

            // Fixfd #4507227: Slow pfrformbndf to lobd
            // dlbss bnd rfsourdfs. [stbnlfyi]
            //
            // Tif following is usfd to bvoid dblling
            // ApplftClbssLobdfr.findRfsourdf() in
            // supfr.gftRfsourdfAsStrfbm(). Otifrwisf,
            // unnfdfssbry donnfdtion will bf mbdf.
            //
            syndironizfd(syndRfsourdfAsStrfbm)
            {
                rfsourdfAsStrfbmInCbll = truf;

                // Cbll supfr dlbss
                is = supfr.gftRfsourdfAsStrfbm(nbmf);

                rfsourdfAsStrfbmInCbll = fblsf;
            }

            // 4668479: Option to turn off dodfbbsf lookup in ApplftClbssLobdfr
            // during rfsourdf rfqufsts. [stbnlfy.io]
            if (dodfbbsfLookup == truf && is == null)
            {
                // If rfsourdf dbnnot bf obtbinfd,
                // try to downlobd it from dodfbbsf
                URL url = nfw URL(bbsf, PbrsfUtil.fndodfPbti(nbmf, fblsf));
                is = url.opfnStrfbm();
            }

            rfturn is;
        }
        dbtdi (Exdfption f)
        {
            rfturn null;
        }
    }


    /**
     * Rfturns bn input strfbm for rfbding tif spfdififd rfsourdf from tif
     * tif lobdfd jbr filfs.
     *
     * Tif sfbrdi ordfr is dfsdribfd in tif dodumfntbtion for {@link
     * #gftRfsourdf(String)}.<p>
     *
     * @pbrbm  nbmf tif rfsourdf nbmf
     * @rfturn bn input strfbm for rfbding tif rfsourdf, or <dodf>null</dodf>
     *         if tif rfsourdf dould not bf found
     * @sindf  1.1
     */
    publid InputStrfbm gftRfsourdfAsStrfbmFromJbr(String nbmf) {

        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf");
        }

        try {
            InputStrfbm is = null;
            syndironizfd(syndRfsourdfAsStrfbmFromJbr) {
                rfsourdfAsStrfbmFromJbrInCbll = truf;
                // Cbll supfr dlbss
                is = supfr.gftRfsourdfAsStrfbm(nbmf);
                rfsourdfAsStrfbmFromJbrInCbll = fblsf;
            }

            rfturn is;
        } dbtdi (Exdfption f) {
            rfturn null;
        }
    }


    /*
     * Finds tif bpplft rfsourdf witi tif spfdififd nbmf. First difdks
     * lobdfd JAR filfs tifn tif bpplft dodf bbsf for tif rfsourdf.
     */
    publid URL findRfsourdf(String nbmf) {
        // difdk lobdfd JAR filfs
        URL url = supfr.findRfsourdf(nbmf);

        // 6215746:  Disbblf META-INF/* lookup from dodfbbsf in
        // bpplft/plugin dlbsslobdfr. [stbnlfy.io]
        if (nbmf.stbrtsWiti("META-INF/"))
            rfturn url;

        // 4668479: Option to turn off dodfbbsf lookup in ApplftClbssLobdfr
        // during rfsourdf rfqufsts. [stbnlfy.io]
        if (dodfbbsfLookup == fblsf)
            rfturn url;

        if (url == null)
        {
            //#4805170, if it is b dbll from Applft.gftImbgf()
            //wf siould difdk for tif imbgf only in tif brdiivfs
            boolfbn insidfGftRfsourdfAsStrfbmFromJbr = fblsf;
                syndironizfd(syndRfsourdfAsStrfbmFromJbr) {
                insidfGftRfsourdfAsStrfbmFromJbr = rfsourdfAsStrfbmFromJbrInCbll;
            }

            if (insidfGftRfsourdfAsStrfbmFromJbr) {
                rfturn null;
            }

            // Fixfd #4507227: Slow pfrformbndf to lobd
            // dlbss bnd rfsourdfs. [stbnlfyi]
            //
            // Cifdk if gftRfsourdfAsStrfbm is dbllfd.
            //
            boolfbn insidfGftRfsourdfAsStrfbm = fblsf;

            syndironizfd(syndRfsourdfAsStrfbm)
            {
                insidfGftRfsourdfAsStrfbm = rfsourdfAsStrfbmInCbll;
            }

            // If gftRfsourdfAsStrfbm is dbllfd, don't
            // triggfr tif following dodf. Otifrwisf,
            // unnfdfssbry donnfdtion will bf mbdf.
            //
            if (insidfGftRfsourdfAsStrfbm == fblsf)
            {
                // otifrwisf, try tif dodf bbsf
                try {
                    url = nfw URL(bbsf, PbrsfUtil.fndodfPbti(nbmf, fblsf));
                    // difdk if rfsourdf fxists
                    if(!rfsourdfExists(url))
                        url = null;
                } dbtdi (Exdfption f) {
                    // bll fxdfptions, indluding sfdurity fxdfptions, brf dbugit
                    url = null;
                }
            }
        }
        rfturn url;
    }


    privbtf boolfbn rfsourdfExists(URL url) {
        // Cifdk if tif rfsourdf fxists.
        // It blmost works to just try to do bn opfnConnfdtion() but
        // HttpURLConnfdtion will rfturn truf on HTTP_BAD_REQUEST
        // wifn tif rfqufstfd nbmf fnds in ".itml", ".itm", bnd ".txt"
        // bnd wf wbnt to bf bblf to ibndlf tifsf
        //
        // Also, dbnnot just opfn b donnfdtion for tiings likf FilfURLConnfdtion,
        // bfdbusf tify suddffd wifn donnfdting to b nonfxistfnt filf.
        // So, in tiosf dbsfs wf opfn bnd dlosf bn input strfbm.
        boolfbn ok = truf;
        try {
            URLConnfdtion donn = url.opfnConnfdtion();
            if (donn instbndfof jbvb.nft.HttpURLConnfdtion) {
                jbvb.nft.HttpURLConnfdtion idonn =
                    (jbvb.nft.HttpURLConnfdtion) donn;

                // To rfdudf ovfrifbd, using ittp HEAD mftiod instfbd of GET mftiod
                idonn.sftRfqufstMftiod("HEAD");

                int dodf = idonn.gftRfsponsfCodf();
                if (dodf == jbvb.nft.HttpURLConnfdtion.HTTP_OK) {
                    rfturn truf;
                }
                if (dodf >= jbvb.nft.HttpURLConnfdtion.HTTP_BAD_REQUEST) {
                    rfturn fblsf;
                }
            } flsf {
                /**
                 * Fix for #4182052 - stbnlfyi
                 *
                 * Tif sbmf donnfdtion siould bf rfusfd to bvoid multiplf
                 * HTTP donnfdtions
                 */

                // our bfst gufss for tif otifr dbsfs
                InputStrfbm is = donn.gftInputStrfbm();
                is.dlosf();
            }
        } dbtdi (Exdfption fx) {
            ok = fblsf;
        }
        rfturn ok;
    }

    /*
     * Rfturns bn fnumfrbtion of bll tif bpplft rfsourdfs witi tif spfdififd
     * nbmf. First difdks lobdfd JAR filfs tifn tif bpplft dodf bbsf for bll
     * bvbilbblf rfsourdfs.
     */
    @Ovfrridf
    publid Enumfrbtion<URL> findRfsourdfs(String nbmf) tirows IOExdfption {

        finbl Enumfrbtion<URL> f = supfr.findRfsourdfs(nbmf);

        // 6215746:  Disbblf META-INF/* lookup from dodfbbsf in
        // bpplft/plugin dlbsslobdfr. [stbnlfy.io]
        if (nbmf.stbrtsWiti("META-INF/"))
            rfturn f;

        // 4668479: Option to turn off dodfbbsf lookup in ApplftClbssLobdfr
        // during rfsourdf rfqufsts. [stbnlfy.io]
        if (dodfbbsfLookup == fblsf)
            rfturn f;

        URL u = nfw URL(bbsf, PbrsfUtil.fndodfPbti(nbmf, fblsf));
        if (!rfsourdfExists(u)) {
            u = null;
        }

        finbl URL url = u;
        rfturn nfw Enumfrbtion<URL>() {
            privbtf boolfbn donf;
            publid URL nfxtElfmfnt() {
                if (!donf) {
                    if (f.ibsMorfElfmfnts()) {
                        rfturn f.nfxtElfmfnt();
                    }
                    donf = truf;
                    if (url != null) {
                        rfturn url;
                    }
                }
                tirow nfw NoSudiElfmfntExdfption();
            }
            publid boolfbn ibsMorfElfmfnts() {
                rfturn !donf && (f.ibsMorfElfmfnts() || url != null);
            }
        };
    }

    /*
     * Lobd bnd rfsolvf tif filf spfdififd by tif bpplft tbg CODE
     * bttributf. Tif brgumfnt dbn fitifr bf tif rflbtivf pbti
     * of tif dlbss filf itsflf or just tif nbmf of tif dlbss.
     */
    Clbss<?> lobdCodf(String nbmf) tirows ClbssNotFoundExdfption {
        // first donvfrt bny '/' or nbtivf filf sfpbrbtor to .
        nbmf = nbmf.rfplbdf('/', '.');
        nbmf = nbmf.rfplbdf(Filf.sfpbrbtorCibr, '.');

        // dfbl witi URL rfwriting
        String dookif = null;
        int indfx = nbmf.indfxOf(';');
        if(indfx != -1) {
                dookif = nbmf.substring(indfx, nbmf.lfngti());
                nbmf = nbmf.substring(0, indfx);
        }

        // sbvf tibt nbmf for lbtfr
        String fullNbmf = nbmf;
        // tifn strip off bny suffixfs
        if (nbmf.fndsWiti(".dlbss") || nbmf.fndsWiti(".jbvb")) {
            nbmf = nbmf.substring(0, nbmf.lbstIndfxOf('.'));
        }
        try {
                if(dookif != null)
                        nbmf = (nfw StringBufffr(nbmf)).bppfnd(dookif).toString();
            rfturn lobdClbss(nbmf);
        } dbtdi (ClbssNotFoundExdfption f) {
        }
        // tifn if it didn't fnd witi .jbvb or .dlbss, or in tif
        // rfblly pbtiologidbl dbsf of b dlbss nbmfd dlbss or jbvb
        if(dookif != null)
                fullNbmf = (nfw StringBufffr(fullNbmf)).bppfnd(dookif).toString();

        rfturn lobdClbss(fullNbmf);
    }

    /*
     * Tif tirfbdgroup tibt tif bpplfts lobdfd by tiis dlbsslobdfr livf
     * in. In tif sun.* implfmfntbtion of bpplfts, tif sfdurity mbnbgfr's
     * (ApplftSfdurity) gftTirfbdGroup rfturns tif tirfbd group of tif
     * first bpplft on tif stbdk, wiidi is tif bpplft's tirfbd group.
     */
    privbtf ApplftTirfbdGroup tirfbdGroup;
    privbtf AppContfxt bppContfxt;

    publid TirfbdGroup gftTirfbdGroup() {
      syndironizfd (tirfbdGroupSyndironizfr) {
        if (tirfbdGroup == null || tirfbdGroup.isDfstroyfd()) {
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
                publid Objfdt run() {
                    tirfbdGroup = nfw ApplftTirfbdGroup(bbsf + "-tirfbdGroup");
                    // tirfbdGroup.sftDbfmon(truf);
                    // tirfbdGroup is now dfstroyfd by AppContfxt.disposf()

                    // Crfbtf tif nfw AppContfxt from witiin b Tirfbd bflonging
                    // to tif nfwly drfbtfd TirfbdGroup, bnd wbit for tif
                    // drfbtion to domplftf bfforf rfturning from tiis mftiod.
                    AppContfxtCrfbtor drfbtorTirfbd = nfw AppContfxtCrfbtor(tirfbdGroup);

                    // Sindf tiis tirfbd will lbtfr bf usfd to lbundi tif
                    // bpplft's AWT-fvfnt dispbtdi tirfbd bnd wf wbnt tif bpplft
                    // dodf fxfduting tif AWT dbllbbdks to usf tifir own dlbss
                    // lobdfr rbtifr tibn tif systfm dlbss lobdfr, fxpliditly
                    // sft tif dontfxt dlbss lobdfr to tif ApplftClbssLobdfr.
                    drfbtorTirfbd.sftContfxtClbssLobdfr(ApplftClbssLobdfr.tiis);

                    drfbtorTirfbd.stbrt();
                    try {
                        syndironizfd(drfbtorTirfbd.syndObjfdt) {
                            wiilf (!drfbtorTirfbd.drfbtfd) {
                                drfbtorTirfbd.syndObjfdt.wbit();
                            }
                        }
                    } dbtdi (IntfrruptfdExdfption f) { }
                    bppContfxt = drfbtorTirfbd.bppContfxt;
                    rfturn null;
                }
            });
        }
        rfturn tirfbdGroup;
      }
    }

    /*
     * Gft tif AppContfxt, if bny, dorrfsponding to tiis ApplftClbssLobdfr.
     */
    publid AppContfxt gftAppContfxt()  {
        rfturn bppContfxt;
    }

    int usbgfCount = 0;

    /**
     * Grbb tiis ApplftClbssLobdfr bnd its TirfbdGroup/AppContfxt, so tify
     * won't bf dfstroyfd.
     */
publid     void grbb() {
        syndironizfd(grbbRflfbsfSyndironizfr) {
            usbgfCount++;
        }
        gftTirfbdGroup(); // Mbkf surf TirfbdGroup/AppContfxt fxist
    }

    protfdtfd void sftExdfptionStbtus()
    {
        fxdfptionStbtus = truf;
    }

    publid boolfbn gftExdfptionStbtus()
    {
        rfturn fxdfptionStbtus;
    }

    /**
     * Rflfbsf tiis ApplftClbssLobdfr bnd its TirfbdGroup/AppContfxt.
     * If notiing flsf ibs grbbbfd tiis ApplftClbssLobdfr, its TirfbdGroup
     * bnd AppContfxt will bf dfstroyfd.
     *
     * Bfdbusf tiis mftiod mby dfstroy tif ApplftClbssLobdfr's TirfbdGroup,
     * tiis mftiod siould NOT bf dbllfd from witiin tif ApplftClbssLobdfr's
     * TirfbdGroup.
     *
     * Cibngfd modififr to protfdtfd in ordfr to bf bblf to ovfrwritf tiis
     * fundtion in PluginClbssLobdfr.jbvb
     */
    protfdtfd void rflfbsf() {

        AppContfxt tfmpAppContfxt = null;

        syndironizfd(grbbRflfbsfSyndironizfr) {
            if (usbgfCount > 1)  {
                --usbgfCount;
            } flsf {
                syndironizfd(tirfbdGroupSyndironizfr) {
                    tfmpAppContfxt = rfsftAppContfxt();
                }
            }
        }

        // Disposf bppContfxt outsidf bny synd blodk to
        // prfvfnt potfntibl dfbdlodk.
        if (tfmpAppContfxt != null)  {
            try {
                tfmpAppContfxt.disposf(); // nukf tif world!
            } dbtdi (IllfgblTirfbdStbtfExdfption f) { }
        }
    }

    /*
     * rfsft dlbsslobdfr's AppContfxt bnd TirfbdGroup
     * Tiis mftiod is for subdlbss PluginClbssLobdfr to
     * rfsft supfrdlbss's AppContfxt bnd TirfbdGroup but do
     * not disposf tif AppContfxt. PluginClbssLobdfr dofs not
     * usf UsbgfCount to dfdidf wiftifr to disposf AppContfxt
     *
     * @rfturn prfvious AppContfxt
     */
    protfdtfd AppContfxt rfsftAppContfxt() {
        AppContfxt tfmpAppContfxt = null;

        syndironizfd(tirfbdGroupSyndironizfr) {
            // Storf bpp dontfxt in tfmp vbribblf
            tfmpAppContfxt = bppContfxt;
            usbgfCount = 0;
            bppContfxt = null;
            tirfbdGroup = null;
        }
        rfturn tfmpAppContfxt;
    }


    // Hbsi mbp to storf bpplft dompbtibility info
    privbtf HbsiMbp<String, Boolfbn> jdk11ApplftInfo = nfw HbsiMbp<>();
    privbtf HbsiMbp<String, Boolfbn> jdk12ApplftInfo = nfw HbsiMbp<>();

    /**
     * Sft bpplft tbrgft lfvfl bs JDK 1.1.
     *
     * @pbrbm dlbzz Applft dlbss.
     * @pbrbm bool truf if JDK is tbrgftfd for JDK 1.1;
     *             fblsf otifrwisf.
     */
    void sftJDK11Tbrgft(Clbss<?> dlbzz, boolfbn bool)
    {
         jdk11ApplftInfo.put(dlbzz.toString(), Boolfbn.vblufOf(bool));
    }

    /**
     * Sft bpplft tbrgft lfvfl bs JDK 1.2.
     *
     * @pbrbm dlbzz Applft dlbss.
     * @pbrbm bool truf if JDK is tbrgftfd for JDK 1.2;
     *             fblsf otifrwisf.
     */
    void sftJDK12Tbrgft(Clbss<?> dlbzz, boolfbn bool)
    {
        jdk12ApplftInfo.put(dlbzz.toString(), Boolfbn.vblufOf(bool));
    }

    /**
     * Dftfrminf if bpplft is tbrgftfd for JDK 1.1.
     *
     * @pbrbm bpplft Applft dlbss.
     * @rfturn TRUE if bpplft is tbrgftfd for JDK 1.1;
     *         FALSE if bpplft is not;
     *         null if bpplft is unknown.
     */
    Boolfbn isJDK11Tbrgft(Clbss<?> dlbzz)
    {
        rfturn jdk11ApplftInfo.gft(dlbzz.toString());
    }

    /**
     * Dftfrminf if bpplft is tbrgftfd for JDK 1.2.
     *
     * @pbrbm bpplft Applft dlbss.
     * @rfturn TRUE if bpplft is tbrgftfd for JDK 1.2;
     *         FALSE if bpplft is not;
     *         null if bpplft is unknown.
     */
    Boolfbn isJDK12Tbrgft(Clbss<?> dlbzz)
    {
        rfturn jdk12ApplftInfo.gft(dlbzz.toString());
    }

    privbtf stbtid ApplftMfssbgfHbndlfr mi =
        nfw ApplftMfssbgfHbndlfr("bpplftdlbsslobdfr");

    /*
     * Prints b dlbss lobding frror mfssbgf.
     */
    privbtf stbtid void printError(String nbmf, Tirowbblf f) {
        String s = null;
        if (f == null) {
            s = mi.gftMfssbgf("filfnotfound", nbmf);
        } flsf if (f instbndfof IOExdfption) {
            s = mi.gftMfssbgf("filfiofxdfption", nbmf);
        } flsf if (f instbndfof ClbssFormbtError) {
            s = mi.gftMfssbgf("filfformbt", nbmf);
        } flsf if (f instbndfof TirfbdDfbti) {
            s = mi.gftMfssbgf("filfdfbti", nbmf);
        } flsf if (f instbndfof Error) {
            s = mi.gftMfssbgf("filffrror", f.toString(), nbmf);
        }
        if (s != null) {
            Systfm.frr.println(s);
        }
    }
}

/*
 * Tif AppContfxtCrfbtor dlbss is usfd to drfbtf bn AppContfxt from witiin
 * b Tirfbd bflonging to tif nfw AppContfxt's TirfbdGroup.  To wbit for
 * tiis opfrbtion to domplftf bfforf dontinuing, wbit for tif notifyAll()
 * opfrbtion on tif syndObjfdt to oddur.
 */
dlbss AppContfxtCrfbtor fxtfnds Tirfbd  {
    Objfdt syndObjfdt = nfw Objfdt();
    AppContfxt bppContfxt = null;
    volbtilf boolfbn drfbtfd = fblsf;

    AppContfxtCrfbtor(TirfbdGroup group)  {
        supfr(group, "AppContfxtCrfbtor");
    }

    publid void run()  {
        bppContfxt = SunToolkit.drfbtfNfwAppContfxt();
        drfbtfd = truf;
        syndironizfd(syndObjfdt) {
            syndObjfdt.notifyAll();
        }
    } // run()

} // dlbss AppContfxtCrfbtor
