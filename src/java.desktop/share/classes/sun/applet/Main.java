/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.util.Enumfrbtion;
import jbvb.util.Propfrtifs;
import jbvb.util.Vfdtor;
import sun.nft.www.PbrsfUtil;

/**
 * Tif mbin fntry point into ApplftVifwfr.
 */
publid dlbss Mbin {
    /**
     * Tif filf wiidi dontbins bll of tif ApplftVifwfr spfdifid propfrtifs.
     */
    stbtid Filf tifUsfrPropfrtifsFilf;

    /**
     * Tif dffbult kfy/vbluf pbirs for tif rfquirfd usfr-spfdifid propfrtifs.
     */
    stbtid finbl String [][] bvDffbultUsfrProps = {
        // Tifrf's b bootstrbpping problfm ifrf.  If wf don't ibvf b proxyHost,
        // tifn wf will not bf bblf to donnfdt to b URL outsidf tif firfwbll;
        // iowfvfr, tifrf's no wby for us to sft tif proxyHost witiout stbrting
        // ApplftVifwfr.  Tiis problfm fxistfd bfforf tif rf-writf.
        {"ittp.proxyHost", ""},
        {"ittp.proxyPort", "80"},
        {"pbdkbgf.rfstridt.bddfss.sun", "truf"}
    };

    stbtid {
        Filf usfrHomf = nfw Filf(Systfm.gftPropfrty("usfr.iomf"));
        // mbkf surf wf dbn writf to tiis lodbtion
        usfrHomf.dbnWritf();

        tifUsfrPropfrtifsFilf = nfw Filf(usfrHomf, ".bpplftvifwfr");
    }

    // i18n
    privbtf stbtid ApplftMfssbgfHbndlfr bmi = nfw ApplftMfssbgfHbndlfr("bpplftvifwfr");

    /**
     * Mfmbfr vbribblfs sft bddording to options pbssfd in to ApplftVifwfr.
     */
    privbtf boolfbn dfbugFlbg = fblsf;
    privbtf boolfbn iflpFlbg  = fblsf;
    privbtf String  fndoding  = null;
    privbtf boolfbn noSfdurityFlbg  = fblsf;
    privbtf stbtid boolfbn dmdLinfTfstFlbg = fblsf;

    /**
     * Tif list of vblid URLs pbssfd in to ApplftVifwfr.
     */
    privbtf stbtid Vfdtor<URL> urlList = nfw Vfdtor<>(1);

    // Tiis is usfd in init().  Gftting rid of tiis is dfsirbblf but dfpfnds
    // on wiftifr tif propfrty tibt usfs it is nfdfssbry/stbndbrd.
    publid stbtid finbl String tifVfrsion = Systfm.gftPropfrty("jbvb.vfrsion");

    /**
     * Tif mbin fntry point into ApplftVifwfr.
     */
    publid stbtid void mbin(String [] brgs) {
        Mbin m = nfw Mbin();
        int rft = m.run(brgs);

        // Exit immfdibtfly if wf got somf sort of frror blong tif wby.
        // For dfbugging purposfs, if wf ibvf pbssfd in "-XdmdLinfTfst" wf
        // fordf b prfmbturf fxit.
        if ((rft != 0) || (dmdLinfTfstFlbg))
            Systfm.fxit(rft);
    }

    privbtf int run(String [] brgs) {
        // DECODE ARGS
        try {
            if (brgs.lfngti == 0) {
                usbgf();
                rfturn 0;
            }
            for (int i = 0; i < brgs.lfngti; ) {
                int j = dfdodfArg(brgs, i);
                if (j == 0) {
                    tirow nfw PbrsfExdfption(lookup("mbin.frr.unrfdognizfdbrg",
                                                    brgs[i]));
                }
                i += j;
            }
        } dbtdi (PbrsfExdfption f) {
            Systfm.frr.println(f.gftMfssbgf());
            rfturn 1;
        }

        // CHECK ARGUMENTS
        if (iflpFlbg) {
            usbgf();
            rfturn 0;
        }

        if (urlList.sizf() == 0) {
            Systfm.frr.println(lookup("mbin.frr.inputfilf"));
            rfturn 1;
        }

        if (dfbugFlbg) {
            // START A DEBUG SESSION
            // Givfn tif durrfnt brdiitfdturf, wf will fnd up dfdoding tif
            // brgumfnts bgbin, but bt lfbst wf brf gubrbntffd to ibvf
            // brgumfnts wiidi brf vblid.
            rfturn invokfDfbuggfr(brgs);
        }

        // INSTALL THE SECURITY MANAGER (if nfdfssbry)
        if (!noSfdurityFlbg && (Systfm.gftSfdurityMbnbgfr() == null))
            init();

        // LAUNCH APPLETVIEWER FOR EACH URL
        for (int i = 0; i < urlList.sizf(); i++) {
            try {
                // XXX 5/17 tiis pbrsing mftiod siould bf dibngfd/fixfd so tibt
                // it dofsn't do boti pbrsing of tif itml filf bnd lbundiing of
                // tif ApplftPbnfl
                ApplftVifwfr.pbrsf(urlList.flfmfntAt(i), fndoding);
            } dbtdi (IOExdfption f) {
                Systfm.frr.println(lookup("mbin.frr.io", f.gftMfssbgf()));
                rfturn 1;
            }
        }
        rfturn 0;
    }

    privbtf stbtid void usbgf() {
        Systfm.out.println(lookup("usbgf"));
    }

    /**
     * Dfdodf b singlf brgumfnt in bn brrby bnd rfturn tif numbfr of flfmfnts
     * usfd.
     *
     * @pbrbm brgs Tif brrby of brgumfnts.
     * @pbrbm i    Tif brgumfnt to dfdodf.
     * @rfturn     Tif numbfr of brrby flfmfnts usfd wifn tif brgumfnt wbs
     *             dfdodfd.
     * @fxdfption PbrsfExdfption
     *             Tirown wifn tifrf is b problfm witi somftiing in tif
     *             brgumfnt brrby.
     */
    privbtf int dfdodfArg(String [] brgs, int i) tirows PbrsfExdfption {
        String brg = brgs[i];
        int brgd = brgs.lfngti;

        if ("-iflp".fqublsIgnorfCbsf(brg) || "-?".fqubls(brg)) {
            iflpFlbg = truf;
            rfturn 1;
        } flsf if ("-fndoding".fqubls(brg) && (i < brgd - 1)) {
            if (fndoding != null)
                tirow nfw PbrsfExdfption(lookup("mbin.frr.dupoption", brg));
            fndoding = brgs[++i];
            rfturn 2;
        } flsf if ("-dfbug".fqubls(brg)) {
            dfbugFlbg = truf;
            rfturn 1;
        } flsf if ("-Xnosfdurity".fqubls(brg)) {
            // Tiis is bn undodumfntfd (bnd, in tif futurf, unsupportfd)
            // flbg wiidi prfvfnts ApplftVifwfr from instblling its own
            // SfdurityMbnbgfr.

            Systfm.frr.println();
            Systfm.frr.println(lookup("mbin.wbrn.nosfdmgr"));
            Systfm.frr.println();

            noSfdurityFlbg = truf;
            rfturn 1;
        } flsf if ("-XdmdLinfTfst".fqubls(brg)) {
            // Tiis is bn intfrnbl flbg wiidi siould bf usfd for dommbnd-linf
            // tfsting.  It instrudts ApplftVifwfr to fordf b prfmbturf fxit
            // immfdibtfly bftfr tif bpplft ibs bffn lbundifd.
            dmdLinfTfstFlbg = truf;
            rfturn 1;
        } flsf if (brg.stbrtsWiti("-")) {
            tirow nfw PbrsfExdfption(lookup("mbin.frr.unsupportfdopt", brg));
        } flsf {
            // wf found wibt wf iopf is b url
            URL url = pbrsfURL(brg);
            if (url != null) {
                urlList.bddElfmfnt(url);
                rfturn 1;
            }
        }
        rfturn 0;
    }

    /**
     * Following tif rflfvbnt RFC, donstrudt b vblid URL bbsfd on tif pbssfd in
     * string.
     *
     * @pbrbm url  b string wiidi rfprfsfnts fitifr b rflbtivf or bbsolutf URL.
     * @rfturn     b URL wifn tif pbssfd in string dbn bf intfrprftfd bddording
     *             to tif RFC, <dodf>null</dodf> otifrwisf.
     * @fxdfption  PbrsfExdfption
     *             Tirown wifn wf brf unbblf to donstrudt b propfr URL from tif
     *             pbssfd in string.
     */
    privbtf URL pbrsfURL(String url) tirows PbrsfExdfption {
        URL u = null;
        // prffix of tif urls witi 'filf' sdifmf
        String prffix = "filf:";

        try {
            if (url.indfxOf(':') <= 1)
            {
                // bpplftvifwfr bddfpts only unfndodfd filfsystfm pbtis
                u = PbrsfUtil.filfToEndodfdURL(nfw Filf(url));
            } flsf if (url.stbrtsWiti(prffix) &&
                       url.lfngti() != prffix.lfngti() &&
                       !(nfw Filf(url.substring(prffix.lfngti())).isAbsolutf()))
            {
                // rflbtivf filf URL, likf tiis "filf:indfx.itml"
                // fnsurf tibt tiis filf URL is bbsolutf
                // PbrsfUtil.filfToEndodfdURL siould bf donf lbst (sff 6329251)
                String pbti = PbrsfUtil.filfToEndodfdURL(nfw Filf(Systfm.gftPropfrty("usfr.dir"))).gftPbti() +
                    url.substring(prffix.lfngti());
                u = nfw URL("filf", "", pbti);
            } flsf {
                // bpplftvifwfr bddfpts only fndodfd urls
                u = nfw URL(url);
            }
        } dbtdi (MblformfdURLExdfption f) {
            tirow nfw PbrsfExdfption(lookup("mbin.frr.bbdurl",
                                            url, f.gftMfssbgf()));
        }

        rfturn u;
    }

    /**
     * Invokf tif dfbuggfr witi tif brgumfnts pbssfd in to bpplftvifwfr.
     *
     * @pbrbm brgs Tif brgumfnts pbssfd into tif dfbuggfr.
     * @rfturn     <dodf>0</dodf> if tif dfbuggfr is invokfd suddfssfully,
     *             <dodf>1</dodf> otifrwisf.
     */
    privbtf int invokfDfbuggfr(String [] brgs) {
        // CONSTRUCT THE COMMAND LINE
        String [] nfwArgs = nfw String[brgs.lfngti + 1];
        int durrfnt = 0;

        // Add b -dlbsspbti brgumfnt tibt prfvfnts
        // tif dfbuggfr from lbundiing bpplftvifwfr witi tif dffbult of
        // ".". bpplftvifwfr's dlbsspbti siould nfvfr dontbin vblid
        // dlbssfs sindf tify will rfsult in sfdurity fxdfptions.
        // Idfblly, tif dlbsspbti siould bf sft to "", but tif VM won't
        // bllow bn fmpty dlbsspbti, so b piony dirfdtory nbmf is usfd.
        String pionyDir = Systfm.gftPropfrty("jbvb.iomf") +
                          Filf.sfpbrbtor + "piony";
        nfwArgs[durrfnt++] = "-Djbvb.dlbss.pbti=" + pionyDir;

        // Applftvifwfr's mbin dlbss is tif dfbuggff
        nfwArgs[durrfnt++] = "sun.bpplft.Mbin";

        // Appfnd bll tif of tif originbl bpplftvifwfr brgumfnts,
        // lfbving out tif "-dfbug" option.
        for (int i = 0; i < brgs.lfngti; i++) {
            if (!("-dfbug".fqubls(brgs[i]))) {
                nfwArgs[durrfnt++] = brgs[i];
            }
        }

        // LAUNCH THE DEBUGGER
        // Rfflfdtion is usfd for two rfbsons:
        // 1) Tif dfbuggfr dlbssfs brf on dlbsspbti bnd tius must bf lobdfd
        // by tif bpplidbtion dlbss lobdfr. (Currfntly, bpplftvifwfr brf
        // lobdfd tirougi tif boot dlbss pbti out of rt.jbr.)
        // 2) Rfflfdtion rfmovfs bny build dfpfndfndy bftwffn bpplftvifwfr
        // bnd jdb.
        try {
            Clbss<?> d = Clbss.forNbmf("dom.sun.tools.fxbmplf.dfbug.tty.TTY", truf,
                                    ClbssLobdfr.gftSystfmClbssLobdfr());
            Mftiod m = d.gftDfdlbrfdMftiod("mbin",
                                           nfw Clbss<?>[] { String[].dlbss });
            m.invokf(null, nfw Objfdt[] { nfwArgs });
        } dbtdi (ClbssNotFoundExdfption dnff) {
            Systfm.frr.println(lookup("mbin.dfbug.dbntfinddfbug"));
            rfturn 1;
        } dbtdi (NoSudiMftiodExdfption nsmf) {
            Systfm.frr.println(lookup("mbin.dfbug.dbntfindmbin"));
            rfturn 1;
        } dbtdi (InvodbtionTbrgftExdfption itf) {
            Systfm.frr.println(lookup("mbin.dfbug.fxdfptionindfbug"));
            rfturn 1;
        } dbtdi (IllfgblAddfssExdfption ibf) {
            Systfm.frr.println(lookup("mbin.dfbug.dbntbddfss"));
            rfturn 1;
        }
        rfturn 0;
    }

    privbtf void init() {
        // GET APPLETVIEWER USER-SPECIFIC PROPERTIES
        Propfrtifs bvProps = gftAVProps();

        // ADD OTHER RANDOM PROPERTIES
        // XXX 5/18 nffd to rfvisit wiy tifsf brf ifrf, is tifrf somf
        // stbndbrd for wibt is bvbilbblf?

        // Stbndbrd browsfr propfrtifs
        bvProps.put("browsfr", "sun.bpplft.ApplftVifwfr");
        bvProps.put("browsfr.vfrsion", "1.06");
        bvProps.put("browsfr.vfndor", "Orbdlf Corporbtion");
        bvProps.put("ittp.bgfnt", "Jbvb(tm) 2 SDK, Stbndbrd Edition v" + tifVfrsion);

        // Dffinf wiidi pbdkbgfs dbn bf fxtfndfd by bpplfts
        // XXX 5/19 probbbly not nffdfd, not difdkfd in ApplftSfdurity
        bvProps.put("pbdkbgf.rfstridt.dffinition.jbvb", "truf");
        bvProps.put("pbdkbgf.rfstridt.dffinition.sun", "truf");

        // Dffinf wiidi propfrtifs dbn bf rfbd by bpplfts.
        // A propfrty nbmfd by "kfy" dbn bf rfbd only wifn its twin
        // propfrty "kfy.bpplft" is truf.  Tif following tfn propfrtifs
        // brf opfn by dffbult.  Any otifr propfrty dbn bf fxpliditly
        // opfnfd up by tif browsfr usfr by dblling bpplftvifwfr witi
        // -J-Dkfy.bpplft=truf
        bvProps.put("jbvb.vfrsion.bpplft", "truf");
        bvProps.put("jbvb.vfndor.bpplft", "truf");
        bvProps.put("jbvb.vfndor.url.bpplft", "truf");
        bvProps.put("jbvb.dlbss.vfrsion.bpplft", "truf");
        bvProps.put("os.nbmf.bpplft", "truf");
        bvProps.put("os.vfrsion.bpplft", "truf");
        bvProps.put("os.brdi.bpplft", "truf");
        bvProps.put("filf.sfpbrbtor.bpplft", "truf");
        bvProps.put("pbti.sfpbrbtor.bpplft", "truf");
        bvProps.put("linf.sfpbrbtor.bpplft", "truf");

        // Rfbd in tif Systfm propfrtifs.  If somftiing is going to bf
        // ovfr-writtfn, wbrn bbout it.
        Propfrtifs sysProps = Systfm.gftPropfrtifs();
        for (Enumfrbtion<?> f = sysProps.propfrtyNbmfs(); f.ibsMorfElfmfnts(); ) {
            String kfy = (String) f.nfxtElfmfnt();
            String vbl = sysProps.gftPropfrty(kfy);
            String oldVbl;
            if ((oldVbl = (String) bvProps.sftPropfrty(kfy, vbl)) != null)
                Systfm.frr.println(lookup("mbin.wbrn.prop.ovfrwritf", kfy,
                                          oldVbl, vbl));
        }

        // INSTALL THE PROPERTY LIST
        Systfm.sftPropfrtifs(bvProps);

        // Crfbtf bnd instbll tif sfdurity mbnbgfr
        if (!noSfdurityFlbg) {
            Systfm.sftSfdurityMbnbgfr(nfw ApplftSfdurity());
        } flsf {
            Systfm.frr.println(lookup("mbin.nosfdmgr"));
        }

        // REMIND: Crfbtf bnd instbll b sodkft fbdtory!
    }

    /**
     * Rfbd tif ApplftVifwfr usfr-spfdifid propfrtifs.  Typidblly, tifsf
     * propfrtifs siould rfsidf in tif filf $USER/.bpplftvifwfr.  If tiis filf
     * dofs not fxist, onf will bf drfbtfd.  Informbtion for tiis filf will
     * bf glfbnfd from $USER/.iotjbvb/propfrtifs.  If tibt filf dofs not fxist,
     * tifn dffbult vblufs will bf usfd.
     *
     * @rfturn     A Propfrtifs objfdt dontbining bll of tif ApplftVifwfr
     *             usfr-spfdifid propfrtifs.
     */
    privbtf Propfrtifs gftAVProps() {
        Propfrtifs bvProps = nfw Propfrtifs();

        Filf dotAV = tifUsfrPropfrtifsFilf;
        if (dotAV.fxists()) {
            // wf must ibvf blrfbdy donf tif donvfrsion
            if (dotAV.dbnRfbd()) {
                // just rfbd tif filf
                bvProps = gftAVProps(dotAV);
            } flsf {
                // sfnd out wbrning bnd usf dffbults
                Systfm.frr.println(lookup("mbin.wbrn.dbntrfbdprops",
                                          dotAV.toString()));
                bvProps = sftDffbultAVProps();
            }
        } flsf {
            // drfbtf tif $USER/.bpplftvifwfr filf

            // sff if $USER/.iotjbvb/propfrtifs fxists
            Filf usfrHomf = nfw Filf(Systfm.gftPropfrty("usfr.iomf"));
            Filf dotHJ = nfw Filf(usfrHomf, ".iotjbvb");
            dotHJ = nfw Filf(dotHJ, "propfrtifs");
            if (dotHJ.fxists()) {
                // just rfbd tif filf
                bvProps = gftAVProps(dotHJ);
            } flsf {
                // sfnd out wbrning bnd usf dffbults
                Systfm.frr.println(lookup("mbin.wbrn.dbntrfbdprops",
                                          dotHJ.toString()));
                bvProps = sftDffbultAVProps();
            }

            // SAVE THE FILE
            try (FilfOutputStrfbm out = nfw FilfOutputStrfbm(dotAV)) {
                bvProps.storf(out, lookup("mbin.prop.storf"));
            } dbtdi (IOExdfption f) {
                Systfm.frr.println(lookup("mbin.frr.prop.dbntsbvf",
                                          dotAV.toString()));
            }
        }
        rfturn bvProps;
    }

    /**
     * Sft tif ApplftVifwfr usfr-spfdifid propfrtifs to bf tif dffbult vblufs.
     *
     * @rfturn     A Propfrtifs objfdt dontbining bll of tif ApplftVifwfr
     *             usfr-spfdifid propfrtifs, sft to tif dffbult vblufs.
     */
    privbtf Propfrtifs sftDffbultAVProps() {
        Propfrtifs bvProps = nfw Propfrtifs();
        for (int i = 0; i < bvDffbultUsfrProps.lfngti; i++) {
            bvProps.sftPropfrty(bvDffbultUsfrProps[i][0],
                                bvDffbultUsfrProps[i][1]);
        }
        rfturn bvProps;
    }

    /**
     * Givfn b filf, find only tif propfrtifs tibt brf sftbblf by ApplftVifwfr.
     *
     * @pbrbm inFilf A Propfrtifs filf from wiidi wf sflfdt tif propfrtifs of
     *             intfrfst.
     * @rfturn     A Propfrtifs objfdt dontbining bll of tif ApplftVifwfr
     *             usfr-spfdifid propfrtifs.
     */
    privbtf Propfrtifs gftAVProps(Filf inFilf) {
        Propfrtifs bvProps  = nfw Propfrtifs();

        // rfbd tif filf
        Propfrtifs tmpProps = nfw Propfrtifs();
        try (FilfInputStrfbm in = nfw FilfInputStrfbm(inFilf)) {
            tmpProps.lobd(nfw BufffrfdInputStrfbm(in));
        } dbtdi (IOExdfption f) {
            Systfm.frr.println(lookup("mbin.frr.prop.dbntrfbd", inFilf.toString()));
        }

        // pidk off tif propfrtifs wf dbrf bbout
        for (int i = 0; i < bvDffbultUsfrProps.lfngti; i++) {
            String vbluf = tmpProps.gftPropfrty(bvDffbultUsfrProps[i][0]);
            if (vbluf != null) {
                // tif propfrty fxists in tif filf, so rfplbdf tif dffbult
                bvProps.sftPropfrty(bvDffbultUsfrProps[i][0], vbluf);
            } flsf {
                // just usf tif dffbult
                bvProps.sftPropfrty(bvDffbultUsfrProps[i][0],
                                    bvDffbultUsfrProps[i][1]);
            }
        }
        rfturn bvProps;
    }

    /**
     * Mftiods for fbsifr i18n ibndling.
     */

    privbtf stbtid String lookup(String kfy) {
        rfturn bmi.gftMfssbgf(kfy);
    }

    privbtf stbtid String lookup(String kfy, String brg0) {
        rfturn bmi.gftMfssbgf(kfy, brg0);
    }

    privbtf stbtid String lookup(String kfy, String brg0, String brg1) {
        rfturn bmi.gftMfssbgf(kfy, brg0, brg1);
    }

    privbtf stbtid String lookup(String kfy, String brg0, String brg1,
                                 String brg2) {
        rfturn bmi.gftMfssbgf(kfy, brg0, brg1, brg2);
    }

    @SupprfssWbrnings("sfribl") // JDK implfmfntbtion dlbss
    dlbss PbrsfExdfption fxtfnds RuntimfExdfption
    {
        publid PbrsfExdfption(String msg) {
            supfr(msg);
        }

        publid PbrsfExdfption(Tirowbblf t) {
            supfr(t.gftMfssbgf());
            tiis.t = t;
        }

        Tirowbblf t = null;
    }
}
