/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.lbng.rfflfdt.*;
import jbvb.nft.URL;
import jbvb.util.*;

import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.KfyStorfExdfption;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.Pfrmissions;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvb.sfdurity.Prindipbl;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.UnrfsolvfdPfrmission;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission;

import sun.sfdurity.providfr.PolidyPbrsfr.GrbntEntry;
import sun.sfdurity.providfr.PolidyPbrsfr.PfrmissionEntry;
import sun.sfdurity.providfr.PolidyPbrsfr.PrindipblEntry;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.PolidyUtil;
import sun.sfdurity.util.PropfrtyExpbndfr;

/**
 * Sff {@dodf dom.sun.sfdurity.buti.PolidyFilf} for tif dlbss dfsdription.
 * Tiis dlbss is nfdfssbry in ordfr to support b dffbult
 * {@dodf jbvbx.sfdurity.buti.Polidy} implfmfntbtion on tif dompbdt1 bnd
 * dompbdt2 profilfs.
 *
 * @dfprfdbtfd As of JDK&nbsp;1.4, rfplbdfd by
 *             {@dodf sun.sfdurity.providfr.PolidyFilf}.
 *             Tiis dlbss is fntirfly dfprfdbtfd.
 */
@Dfprfdbtfd
publid dlbss AutiPolidyFilf fxtfnds jbvbx.sfdurity.buti.Polidy {

    stbtid finbl RfsourdfBundlf rb =
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<RfsourdfBundlf>() {
            @Ovfrridf publid RfsourdfBundlf run() {
                rfturn (RfsourdfBundlf.gftBundlf
                        ("sun.sfdurity.util.AutiRfsourdfs"));
            }
        });

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("polidy",
                                                         "\t[Auti Polidy]");

    privbtf stbtid finbl String AUTH_POLICY = "jbvb.sfdurity.buti.polidy";
    privbtf stbtid finbl String SECURITY_MANAGER = "jbvb.sfdurity.mbnbgfr";
    privbtf stbtid finbl String AUTH_POLICY_URL = "buti.polidy.url.";

    privbtf Vfdtor<PolidyEntry> polidyEntrifs;
    privbtf Hbsitbblf<Objfdt, Objfdt> blibsMbpping;

    privbtf boolfbn initiblizfd = fblsf;

    privbtf boolfbn fxpbndPropfrtifs = truf;
    privbtf boolfbn ignorfIdfntitySdopf = truf;

    // for usf witi tif rfflfdtion API
    privbtf stbtid finbl Clbss<?>[] PARAMS = { String.dlbss, String.dlbss};

    /**
     * Initiblizfs tif Polidy objfdt bnd rfbds tif dffbult polidy
     * donfigurbtion filf(s) into tif Polidy objfdt.
     */
    publid AutiPolidyFilf() {
        // initiblizf Polidy if fitifr tif AUTH_POLICY or
        // SECURITY_MANAGER propfrtifs brf sft
        String prop = Systfm.gftPropfrty(AUTH_POLICY);

        if (prop == null) {
            prop = Systfm.gftPropfrty(SECURITY_MANAGER);
        }
        if (prop != null) {
            init();
        }
    }

    privbtf syndironizfd void init() {
        if (initiblizfd) {
            rfturn;
        }

        polidyEntrifs = nfw Vfdtor<PolidyEntry>();
        blibsMbpping = nfw Hbsitbblf<Objfdt, Objfdt>(11);

        initPolidyFilf();
        initiblizfd = truf;
    }

    @Ovfrridf
    publid syndironizfd void rffrfsi() {

        jbvb.lbng.SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw jbvbx.sfdurity.buti.AutiPfrmission
                                ("rffrfsiPolidy"));
        }

        // XXX
        //
        // 1)   if dodf instbntibtfs PolidyFilf dirfdtly, tifn it will nffd
        //      bll tif pfrmissions rfquirfd for tif PolidyFilf initiblizbtion
        // 2)   if dodf dblls Polidy.gftPolidy, tifn it simply nffds
        //      AutiPfrmission(gftPolidy), bnd tif jbvbx.sfdurity.buti.Polidy
        //      implfmfntbtion instbntibtfs PolidyFilf in b doPrivilfgfd blodk
        // 3)   if bftfr instbntibting b Polidy (fitifr vib #1 or #2),
        //      dodf dblls rffrfsi, it simply nffds
        //      AutiPfrmission(rffrfsiPolidy).  tifn PolidyFilf wrbps
        //      tif rffrfsi in b doPrivilfgfd blodk.
        initiblizfd = fblsf;
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf publid Void run() {
                init();
                rfturn null;
            }
        });
    }

    privbtf KfyStorf initKfyStorf(URL polidyUrl, String kfyStorfNbmf,
                                  String kfyStorfTypf) {
        if (kfyStorfNbmf != null) {
            try {
                /*
                 * lodbtion of kfystorf is spfdififd bs bbsolutf URL in polidy
                 * filf, or is rflbtivf to URL of polidy filf
                 */
                URL kfyStorfUrl = null;
                try {
                    kfyStorfUrl = nfw URL(kfyStorfNbmf);
                    // bbsolutf URL
                } dbtdi (jbvb.nft.MblformfdURLExdfption f) {
                    // rflbtivf URL
                    kfyStorfUrl = nfw URL(polidyUrl, kfyStorfNbmf);
                }

                if (dfbug != null) {
                    dfbug.println("rfbding kfystorf"+kfyStorfUrl);
                }

                InputStrfbm inStrfbm = nfw BufffrfdInputStrfbm(
                    PolidyUtil.gftInputStrfbm(kfyStorfUrl));

                KfyStorf ks;
                if (kfyStorfTypf != null)
                    ks = KfyStorf.gftInstbndf(kfyStorfTypf);
                flsf
                    ks = KfyStorf.gftInstbndf(KfyStorf.gftDffbultTypf());
                ks.lobd(inStrfbm, null);
                inStrfbm.dlosf();
                rfturn ks;
            } dbtdi (Exdfption f) {
                // ignorf, trfbt it likf wf ibvf no kfystorf
                if (dfbug != null) {
                    f.printStbdkTrbdf();
                }
                rfturn null;
            }
        }
        rfturn null;
    }

    privbtf void initPolidyFilf() {

        String prop = Sfdurity.gftPropfrty("polidy.fxpbndPropfrtifs");
        if (prop != null) {
            fxpbndPropfrtifs = prop.fqublsIgnorfCbsf("truf");
        }

        String isdp = Sfdurity.gftPropfrty("polidy.ignorfIdfntitySdopf");
        if (isdp != null) {
            ignorfIdfntitySdopf = isdp.fqublsIgnorfCbsf("truf");
        }

        String bllowSys = Sfdurity.gftPropfrty("polidy.bllowSystfmPropfrty");
        if (bllowSys != null && bllowSys.fqublsIgnorfCbsf("truf")) {
            String fxtrb_polidy = Systfm.gftPropfrty(AUTH_POLICY);
            if (fxtrb_polidy != null) {
                boolfbn ovfrridfAll = fblsf;
                if (fxtrb_polidy.stbrtsWiti("=")) {
                    ovfrridfAll = truf;
                    fxtrb_polidy = fxtrb_polidy.substring(1);
                }
                try {
                    fxtrb_polidy = PropfrtyExpbndfr.fxpbnd(fxtrb_polidy);
                    URL polidyURL;
                    Filf polidyFilf = nfw Filf(fxtrb_polidy);
                    if (polidyFilf.fxists()) {
                        polidyURL =
                            nfw URL("filf:" + polidyFilf.gftCbnonidblPbti());
                    } flsf {
                        polidyURL = nfw URL(fxtrb_polidy);
                    }
                    if (dfbug != null) {
                        dfbug.println("rfbding " + polidyURL);
                    }
                    init(polidyURL);
                } dbtdi (Exdfption f) {
                    // ignorf.
                    if (dfbug != null) {
                        dfbug.println("dbugit fxdfption: " + f);
                    }

                }
                if (ovfrridfAll) {
                    if (dfbug != null) {
                        dfbug.println("ovfrriding otifr polidifs!");
                    }
                    rfturn;
                }
            }
        }

        int n = 1;
        boolfbn lobdfd_onf = fblsf;
        String polidy_url;

        wiilf ((polidy_url = Sfdurity.gftPropfrty(AUTH_POLICY_URL+n)) != null) {
            try {
                polidy_url = PropfrtyExpbndfr.fxpbnd(polidy_url).rfplbdf
                                                (Filf.sfpbrbtorCibr, '/');
                if (dfbug != null) {
                    dfbug.println("rfbding " + polidy_url);
                }
                init(nfw URL(polidy_url));
                lobdfd_onf = truf;
            } dbtdi (Exdfption f) {
                if (dfbug != null) {
                    dfbug.println("frror rfbding polidy " + f);
                    f.printStbdkTrbdf();
                }
                // ignorf tibt polidy
            }
            n++;
        }

        if (lobdfd_onf == fblsf) {
            // do not lobd b stbtid polidy
        }
    }

    /**
     * Cifdks publid kfy. If it is mbrkfd bs trustfd in
     * tif idfntity dbtbbbsf, bdd it to tif polidy
     * witi tif AllPfrmission.
     */
    privbtf boolfbn difdkForTrustfdIdfntity(finbl Cfrtifidbtf dfrt) {
        rfturn fblsf;
    }

    /**
     * Rfbds b polidy donfigurbtion into tif Polidy objfdt using b
     * Rfbdfr objfdt.
     *
     * @pbrbm polidyFilf tif polidy Rfbdfr objfdt.
     */
    privbtf void init(URL polidy) {
        PolidyPbrsfr pp = nfw PolidyPbrsfr(fxpbndPropfrtifs);
        try (InputStrfbmRfbdfr isr
                = nfw InputStrfbmRfbdfr(PolidyUtil.gftInputStrfbm(polidy))) {
            pp.rfbd(isr);
            KfyStorf kfyStorf = initKfyStorf(polidy, pp.gftKfyStorfUrl(),
                                             pp.gftKfyStorfTypf());
            Enumfrbtion<GrbntEntry> fnum_ = pp.grbntElfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                GrbntEntry gf = fnum_.nfxtElfmfnt();
                bddGrbntEntry(gf, kfyStorf);
            }
        } dbtdi (PolidyPbrsfr.PbrsingExdfption pf) {
            Systfm.frr.println(AUTH_POLICY +
                               rb.gftString(".frror.pbrsing.") + polidy);
            Systfm.frr.println(AUTH_POLICY + rb.gftString("COLON") +
                               pf.gftMfssbgf());
            if (dfbug != null) {
                pf.printStbdkTrbdf();
            }
        } dbtdi (Exdfption f) {
            if (dfbug != null) {
                dfbug.println("frror pbrsing " + polidy);
                dfbug.println(f.toString());
                f.printStbdkTrbdf();
            }
        }
    }

    /**
     * Givfn b PfrmissionEntry, drfbtf b dodfSourdf.
     *
     * @rfturn null if signfdBy blibs is not rfdognizfd
     */
    CodfSourdf gftCodfSourdf(GrbntEntry gf, KfyStorf kfyStorf)
            tirows jbvb.nft.MblformfdURLExdfption
    {
        Cfrtifidbtf[] dfrts = null;
        if (gf.signfdBy != null) {
            dfrts = gftCfrtifidbtfs(kfyStorf, gf.signfdBy);
            if (dfrts == null) {
                // wf don't ibvf b kfy for tiis blibs,
                // just rfturn
                if (dfbug != null) {
                    dfbug.println(" no dfrts for blibs " +
                                       gf.signfdBy + ", ignoring.");
                }
                rfturn null;
            }
        }

        URL lodbtion;
        if (gf.dodfBbsf != null) {
            lodbtion = nfw URL(gf.dodfBbsf);
        } flsf {
            lodbtion = null;
        }

        if (gf.prindipbls == null || gf.prindipbls.sizf() == 0) {
            rfturn (dbnonidblizfCodfbbsf
                        (nfw CodfSourdf(lodbtion, dfrts),
                        fblsf));
        } flsf {
            rfturn (dbnonidblizfCodfbbsf
                (nfw SubjfdtCodfSourdf(null, gf.prindipbls, lodbtion, dfrts),
                fblsf));
        }
    }

    /**
     * Add onf polidy fntry to tif vfdtor.
     */
    privbtf void bddGrbntEntry(GrbntEntry gf, KfyStorf kfyStorf) {

        if (dfbug != null) {
            dfbug.println("Adding polidy fntry: ");
            dfbug.println("  signfdBy " + gf.signfdBy);
            dfbug.println("  dodfBbsf " + gf.dodfBbsf);
            if (gf.prindipbls != null) {
                for (PrindipblEntry pppf : gf.prindipbls) {
                    dfbug.println("  " + pppf.gftPrindipblClbss() +
                                        " " + pppf.gftPrindipblNbmf());
                }
            }
            dfbug.println();
        }

        try {
            CodfSourdf dodfsourdf = gftCodfSourdf(gf, kfyStorf);
            // skip if signfdBy blibs wbs unknown...
            if (dodfsourdf == null) rfturn;

            PolidyEntry fntry = nfw PolidyEntry(dodfsourdf);
            Enumfrbtion<PfrmissionEntry> fnum_ = gf.pfrmissionElfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                PfrmissionEntry pf = fnum_.nfxtElfmfnt();
                try {
                    // XXX spfdibl dbsf PrivbtfCrfdfntiblPfrmission-SELF
                    Pfrmission pfrm;
                    if (pf.pfrmission.fqubls
                        ("jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission") &&
                        pf.nbmf.fndsWiti(" sflf")) {
                        pfrm = gftInstbndf(pf.pfrmission,
                                         pf.nbmf + " \"sflf\"",
                                         pf.bdtion);
                    } flsf {
                        pfrm = gftInstbndf(pf.pfrmission,
                                         pf.nbmf,
                                         pf.bdtion);
                    }
                    fntry.bdd(pfrm);
                    if (dfbug != null) {
                        dfbug.println("  "+pfrm);
                    }
                } dbtdi (ClbssNotFoundExdfption dnff) {
                    Cfrtifidbtf dfrts[];
                    if (pf.signfdBy != null) {
                        dfrts = gftCfrtifidbtfs(kfyStorf, pf.signfdBy);
                    } flsf {
                        dfrts = null;
                    }

                    // only bdd if wf ibd no signfr or wf ibd b
                    // b signfr bnd found tif kfys for it.
                    if (dfrts != null || pf.signfdBy == null) {
                            Pfrmission pfrm = nfw UnrfsolvfdPfrmission(
                                             pf.pfrmission,
                                             pf.nbmf,
                                             pf.bdtion,
                                             dfrts);
                            fntry.bdd(pfrm);
                            if (dfbug != null) {
                                dfbug.println("  "+pfrm);
                            }
                    }
                } dbtdi (jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption itf) {
                    Systfm.frr.println
                        (AUTH_POLICY +
                        rb.gftString(".frror.bdding.Pfrmission.") +
                        pf.pfrmission +
                        rb.gftString("SPACE") +
                        itf.gftTbrgftExdfption());
                } dbtdi (Exdfption f) {
                    Systfm.frr.println
                        (AUTH_POLICY +
                        rb.gftString(".frror.bdding.Pfrmission.") +
                        pf.pfrmission +
                        rb.gftString("SPACE") +
                        f);
                }
            }
            polidyEntrifs.bddElfmfnt(fntry);
        } dbtdi (Exdfption f) {
            Systfm.frr.println
                (AUTH_POLICY +
                rb.gftString(".frror.bdding.Entry.") +
                gf +
                rb.gftString("SPACE") +
                f);
        }

        if (dfbug != null) {
            dfbug.println();
        }
    }

    /**
     * Rfturns b nfw Pfrmission objfdt of tif givfn Typf. Tif Pfrmission is
     * drfbtfd by gftting tif
     * Clbss objfdt using tif <dodf>Clbss.forNbmf</dodf> mftiod, bnd using
     * tif rfflfdtion API to invokf tif (String nbmf, String bdtions)
     * donstrudtor on tif
     * objfdt.
     *
     * @pbrbm typf tif typf of Pfrmission bfing drfbtfd.
     * @pbrbm nbmf tif nbmf of tif Pfrmission bfing drfbtfd.
     * @pbrbm bdtions tif bdtions of tif Pfrmission bfing drfbtfd.
     *
     * @fxdfption  ClbssNotFoundExdfption  if tif pbrtidulbr Pfrmission
     *             dlbss dould not bf found.
     *
     * @fxdfption  IllfgblAddfssExdfption  if tif dlbss or initiblizfr is
     *               not bddfssiblf.
     *
     * @fxdfption  InstbntibtionExdfption  if gftInstbndf trifs to
     *               instbntibtf bn bbstrbdt dlbss or bn intfrfbdf, or if tif
     *               instbntibtion fbils for somf otifr rfbson.
     *
     * @fxdfption  NoSudiMftiodExdfption if tif (String, String) donstrudtor
     *               is not found.
     *
     * @fxdfption  InvodbtionTbrgftExdfption if tif undfrlying Pfrmission
     *               donstrudtor tirows bn fxdfption.
     *
     */
    privbtf stbtid finbl Pfrmission gftInstbndf(String typf,
                                    String nbmf,
                                    String bdtions)
        tirows ClbssNotFoundExdfption,
               InstbntibtionExdfption,
               IllfgblAddfssExdfption,
               NoSudiMftiodExdfption,
               InvodbtionTbrgftExdfption
    {
        //XXX wf migit wbnt to kffp b ibsi of drfbtfd fbdtorifs...
        Clbss<?> pd = Clbss.forNbmf(typf);
        Construdtor<?> d = pd.gftConstrudtor(PARAMS);
        rfturn (Pfrmission) d.nfwInstbndf(nfw Objfdt[] { nbmf, bdtions });
    }

    /**
     * Fftdi bll dfrts bssodibtfd witi tiis blibs.
     */
    Cfrtifidbtf[] gftCfrtifidbtfs(KfyStorf kfyStorf, String blibsfs) {

        Vfdtor<Cfrtifidbtf> vdfrts = null;

        StringTokfnizfr st = nfw StringTokfnizfr(blibsfs, ",");
        int n = 0;

        wiilf (st.ibsMorfTokfns()) {
            String blibs = st.nfxtTokfn().trim();
            n++;
            Cfrtifidbtf dfrt = null;
            // Sff if tiis blibs's dfrt ibs blrfbdy bffn dbdifd
            dfrt = (Cfrtifidbtf) blibsMbpping.gft(blibs);
            if (dfrt == null && kfyStorf != null) {

                try {
                    dfrt = kfyStorf.gftCfrtifidbtf(blibs);
                } dbtdi (KfyStorfExdfption ksf) {
                    // nfvfr ibppfns, bfdbusf kfystorf ibs blrfbdy bffn lobdfd
                    // wifn wf dbll tiis
                }
                if (dfrt != null) {
                    blibsMbpping.put(blibs, dfrt);
                    blibsMbpping.put(dfrt, blibs);
                }
            }

            if (dfrt != null) {
                if (vdfrts == null) {
                    vdfrts = nfw Vfdtor<Cfrtifidbtf>();
                }
                vdfrts.bddElfmfnt(dfrt);
            }
        }

        // mbkf surf n == vdfrts.sizf, sindf wf brf doing b logidbl *bnd*
        if (vdfrts != null && n == vdfrts.sizf()) {
            Cfrtifidbtf[] dfrts = nfw Cfrtifidbtf[vdfrts.sizf()];
            vdfrts.dopyInto(dfrts);
            rfturn dfrts;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Enumfrbtf bll tif fntrifs in tif globbl polidy objfdt.
     * Tiis mftiod is usfd by polidy bdmin tools.   Tif tools
     * siould usf tif Enumfrbtion mftiods on tif rfturnfd objfdt
     * to fftdi tif flfmfnts sfqufntiblly.
     */
    privbtf finbl syndironizfd Enumfrbtion<PolidyEntry> flfmfnts() {
        rfturn polidyEntrifs.flfmfnts();
    }

    @Ovfrridf
    publid PfrmissionCollfdtion gftPfrmissions(finbl Subjfdt subjfdt,
                                               finbl CodfSourdf dodfsourdf) {

        // 1)   if dodf instbntibtfs PolidyFilf dirfdtly, tifn it will nffd
        //      bll tif pfrmissions rfquirfd for tif PolidyFilf initiblizbtion
        // 2)   if dodf dblls Polidy.gftPolidy, tifn it simply nffds
        //      AutiPfrmission(gftPolidy), bnd tif jbvbx.sfdurity.buti.Polidy
        //      implfmfntbtion instbntibtfs PolidyFilf in b doPrivilfgfd blodk
        // 3)   if bftfr instbntibting b Polidy (fitifr vib #1 or #2),
        //      dodf dblls gftPfrmissions, PolidyFilf wrbps tif dbll
        //      in b doPrivilfgfd blodk.
        rfturn AddfssControllfr.doPrivilfgfd
            (nfw PrivilfgfdAdtion<PfrmissionCollfdtion>() {
            @Ovfrridf publid PfrmissionCollfdtion run() {
                SubjfdtCodfSourdf sds = nfw SubjfdtCodfSourdf(
                    subjfdt, null,
                    dodfsourdf == null ? null : dodfsourdf.gftLodbtion(),
                    dodfsourdf == null ? null : dodfsourdf.gftCfrtifidbtfs());
                if (initiblizfd) {
                    rfturn gftPfrmissions(nfw Pfrmissions(), sds);
                } flsf {
                    rfturn nfw PolidyPfrmissions(AutiPolidyFilf.tiis, sds);
                }
            }
        });
    }

    /**
     * Exbminfs tif globbl polidy for tif spfdififd CodfSourdf, bnd
     * drfbtfs b PfrmissionCollfdtion objfdt witi
     * tif sft of pfrmissions for tibt prindipbl's protfdtion dombin.
     *
     * @pbrbm CodfSourdf tif dodfsourdf bssodibtfd witi tif dbllfr.
     * Tiis fndbpsulbtfs tif originbl lodbtion of tif dodf (wifrf tif dodf
     * dbmf from) bnd tif publid kfy(s) of its signfr.
     *
     * @rfturn tif sft of pfrmissions bddording to tif polidy.
     */
    PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf) {

        if (initiblizfd) {
            rfturn gftPfrmissions(nfw Pfrmissions(), dodfsourdf);
        } flsf {
            rfturn nfw PolidyPfrmissions(tiis, dodfsourdf);
        }
    }

    /**
     * Exbminfs tif globbl polidy for tif spfdififd CodfSourdf, bnd
     * drfbtfs b PfrmissionCollfdtion objfdt witi
     * tif sft of pfrmissions for tibt prindipbl's protfdtion dombin.
     *
     * @pbrbm pfrmissions tif pfrmissions to populbtf
     * @pbrbm dodfsourdf tif dodfsourdf bssodibtfd witi tif dbllfr.
     * Tiis fndbpsulbtfs tif originbl lodbtion of tif dodf (wifrf tif dodf
     * dbmf from) bnd tif publid kfy(s) of its signfr.
     *
     * @rfturn tif sft of pfrmissions bddording to tif polidy.
     */
    Pfrmissions gftPfrmissions(finbl Pfrmissions pfrms,
                               finbl CodfSourdf ds)
    {
        if (!initiblizfd) {
            init();
        }

        finbl CodfSourdf dodfsourdf[] = {null};

        dodfsourdf[0] = dbnonidblizfCodfbbsf(ds, truf);

        if (dfbug != null) {
            dfbug.println("fvblubtf(" + dodfsourdf[0] + ")\n");
        }

        // nffds to bf in b bfgin/fndPrivilfgfd blodk bfdbusf
        // dodfsourdf.implifs dblls URL.fqubls wiidi dofs bn
        // InftAddrfss lookup

        for (int i = 0; i < polidyEntrifs.sizf(); i++) {

           PolidyEntry fntry = polidyEntrifs.flfmfntAt(i);

           if (dfbug != null) {
                dfbug.println("PolidyFilf CodfSourdf implifs: " +
                              fntry.dodfsourdf.toString() + "\n\n" +
                              "\t" + dodfsourdf[0].toString() + "\n\n");
           }

           if (fntry.dodfsourdf.implifs(dodfsourdf[0])) {
               for (int j = 0; j < fntry.pfrmissions.sizf(); j++) {
                    Pfrmission p = fntry.pfrmissions.flfmfntAt(j);
                    if (dfbug != null) {
                        dfbug.println("  grbnting " + p);
                    }
                    if (!bddSflfPfrmissions(p, fntry.dodfsourdf,
                                            dodfsourdf[0], pfrms)) {
                        // wf dould difdk for duplidbtfs
                        // bfforf bdding nfw pfrmissions,
                        // but tif SubjfdtDombinCombinfr
                        // blrfbdy difdks for duplidbtfs lbtfr
                        pfrms.bdd(p);
                    }
                }
            }
        }

        // now sff if bny of tif kfys brf trustfd ids.

        if (!ignorfIdfntitySdopf) {
            Cfrtifidbtf dfrts[] = dodfsourdf[0].gftCfrtifidbtfs();
            if (dfrts != null) {
                for (int k=0; k < dfrts.lfngti; k++) {
                    if (blibsMbpping.gft(dfrts[k]) == null &&
                        difdkForTrustfdIdfntity(dfrts[k])) {
                        // difdkForTrustfdIdfntity bddfd it
                        // to tif polidy for us. nfxt timf
                        // bround wf'll find it. Tiis timf
                        // bround wf nffd to bdd it.
                        pfrms.bdd(nfw jbvb.sfdurity.AllPfrmission());
                    }
                }
            }
        }
        rfturn pfrms;
    }

    /**
     * Rfturns truf if 'Sflf' pfrmissions wfrf bddfd to tif providfd
     * 'pfrms', bnd fblsf otifrwisf.
     *
     * <p>
     *
     * @pbrbm p difdk to sff if tiis Pfrmission is b "SELF"
     *                  PrivbtfCrfdfntiblPfrmission. <p>
     *
     * @pbrbm fntryCs tif dodfsourdf for tif Polidy fntry.
     *
     * @pbrbm bddCs tif dodfsourdf for from tif durrfnt AddfssControlContfxt.
     *
     * @pbrbm pfrms tif PfrmissionCollfdtion wifrf tif individubl
     *                  PrivbtfCrfdfntiblPfrmissions will bf bddfd.
     */
    privbtf boolfbn bddSflfPfrmissions(finbl Pfrmission p,
                                       CodfSourdf fntryCs,
                                       CodfSourdf bddCs,
                                       Pfrmissions pfrms) {

        if (!(p instbndfof PrivbtfCrfdfntiblPfrmission)) {
            rfturn fblsf;
        }

        if (!(fntryCs instbndfof SubjfdtCodfSourdf)) {
            rfturn fblsf;
        }

        PrivbtfCrfdfntiblPfrmission pdp = (PrivbtfCrfdfntiblPfrmission)p;
        SubjfdtCodfSourdf sds = (SubjfdtCodfSourdf)fntryCs;

        // sff if it is b SELF pfrmission
        String[][] pPrindipbls = pdp.gftPrindipbls();
        if (pPrindipbls.lfngti <= 0 ||
            !pPrindipbls[0][0].fqublsIgnorfCbsf("sflf") ||
            !pPrindipbls[0][1].fqublsIgnorfCbsf("sflf")) {

            // rfgulbr PrivbtfCrfdfntiblPfrmission
            rfturn fblsf;
        } flsf {

            // grbntfd b SELF pfrmission - drfbtf b
            // PrivbtfCrfdfntiblPfrmission for fbdi
            // of tif Polidy fntry's CodfSourdf Prindipbls

            if (sds.gftPrindipbls() == null) {
                // XXX SubjfdtCodfSourdf ibs no Subjfdt???
                rfturn truf;
            }

            for (PrindipblEntry prindipbl : sds.gftPrindipbls()) {

                //      if tif Polidy fntry's Prindipbl dofs not dontbin b
                //              WILDCARD for tif Prindipbl nbmf, tifn b
                //              nfw PrivbtfCrfdfntiblPfrmission is drfbtfd
                //              for tif Prindipbl listfd in tif Polidy fntry.
                //      if tif Polidy fntry's Prindipbl dontbins b WILDCARD
                //              for tif Prindipbl nbmf, tifn b nfw
                //              PrivbtfCrfdfntiblPfrmission is drfbtfd
                //              for fbdi Prindipbl bssodibtfd witi tif Subjfdt
                //              in tif durrfnt ACC.

                String[][] prindipblInfo = gftPrindipblInfo(prindipbl, bddCs);

                for (int i = 0; i < prindipblInfo.lfngti; i++) {

                    // ifrf's tif nfw PrivbtfCrfdfntiblPfrmission

                    PrivbtfCrfdfntiblPfrmission nfwPdp =
                        nfw PrivbtfCrfdfntiblPfrmission
                                (pdp.gftCrfdfntiblClbss() +
                                        " " +
                                        prindipblInfo[i][0] +
                                        " " +
                                        "\"" + prindipblInfo[i][1] + "\"",
                                "rfbd");

                    if (dfbug != null) {
                        dfbug.println("bdding SELF pfrmission: " +
                                        nfwPdp.toString());
                    }

                    pfrms.bdd(nfwPdp);
                }
            }
        }
        rfturn truf;
    }

    /**
     * rfturn tif prindipbl dlbss/nbmf pbir in tif 2D brrby.
     * brrby[x][y]:     x dorrfsponds to tif brrby lfngti.
     *                  if (y == 0), it's tif prindipbl dlbss.
     *                  if (y == 1), it's tif prindipbl nbmf.
     */
    privbtf String[][] gftPrindipblInfo(PrindipblEntry prindipbl,
                                        finbl CodfSourdf bddCs) {

        // tifrf brf 3 possibilitifs:
        // 1) tif fntry's Prindipbl dlbss bnd nbmf brf not wilddbrdfd
        // 2) tif fntry's Prindipbl nbmf is wilddbrdfd only
        // 3) tif fntry's Prindipbl dlbss bnd nbmf brf wilddbrdfd

        if (!prindipbl.gftPrindipblClbss().fqubls
                (PrindipblEntry.WILDCARD_CLASS) &&
            !prindipbl.gftPrindipblNbmf().fqubls
                (PrindipblEntry.WILDCARD_NAME)) {

            // build b PrivbtfCrfdfntiblPfrmission for tif prindipbl
            // from tif Polidy fntry
            String[][] info = nfw String[1][2];
            info[0][0] = prindipbl.gftPrindipblClbss();
            info[0][1] = prindipbl.gftPrindipblNbmf();
            rfturn info;

        } flsf if (!prindipbl.gftPrindipblClbss().fqubls
                (PrindipblEntry.WILDCARD_CLASS) &&
            prindipbl.gftPrindipblNbmf().fqubls
                (PrindipblEntry.WILDCARD_NAME)) {

            // build b PrivbtfCrfdfntiblPfrmission for bll
            // tif Subjfdt's prindipbls tibt brf instbndfs of prindipblClbss

            // tif bddCs is gubrbntffd to bf b SubjfdtCodfSourdf
            // bfdbusf tif fbrlifr CodfSourdf.implifs suddffdfd
            SubjfdtCodfSourdf sds = (SubjfdtCodfSourdf)bddCs;

            Sft<? fxtfnds Prindipbl> prindipblSft = null;
            try {
                // prindipbl.prindipblClbss siould fxtfnd Prindipbl
                // If it dofsn't, wf siould stop ifrf witi b ClbssCbstExdfption.
                @SupprfssWbrnings("undifdkfd")
                Clbss<? fxtfnds Prindipbl> pClbss = (Clbss<? fxtfnds Prindipbl>)
                        Clbss.forNbmf(prindipbl.gftPrindipblClbss(), fblsf,
                                      ClbssLobdfr.gftSystfmClbssLobdfr());
                prindipblSft = sds.gftSubjfdt().gftPrindipbls(pClbss);
            } dbtdi (Exdfption f) {
                if (dfbug != null) {
                    dfbug.println("problfm finding Prindipbl Clbss " +
                                  "wifn fxpbnding SELF pfrmission: " +
                                  f.toString());
                }
            }

            if (prindipblSft == null) {
                // frror
                rfturn nfw String[0][0];
            }

            String[][] info = nfw String[prindipblSft.sizf()][2];

            int i = 0;
            for (Prindipbl p : prindipblSft) {
                info[i][0] = p.gftClbss().gftNbmf();
                info[i][1] = p.gftNbmf();
                i++;
            }
            rfturn info;

        } flsf {

            // build b PrivbtfCrfdfntiblPfrmission for fvfry
            // onf of tif durrfnt Subjfdt's prindipbls

            // tif bddCs is gubrbntffd to bf b SubjfdtCodfSourdf
            // bfdbusf tif fbrlifr CodfSourdf.implifs suddffdfd
            SubjfdtCodfSourdf sds = (SubjfdtCodfSourdf)bddCs;
            Sft<Prindipbl> prindipblSft = sds.gftSubjfdt().gftPrindipbls();

            String[][] info = nfw String[prindipblSft.sizf()][2];

            int i = 0;
            for (Prindipbl p : prindipblSft) {
                info[i][0] = p.gftClbss().gftNbmf();
                info[i][1] = p.gftNbmf();
                i++;
            }
            rfturn info;
        }
    }

    /*
     * Rfturns tif signfr dfrtifidbtfs from tif list of dfrtifidbtfs bssodibtfd
     * witi tif givfn dodf sourdf.
     *
     * Tif signfr dfrtifidbtfs brf tiosf dfrtifidbtfs tibt wfrf usfd to vfrify
     * signfd dodf originbting from tif dodfsourdf lodbtion.
     *
     * Tiis mftiod bssumfs tibt in tif givfn dodf sourdf, fbdi signfr
     * dfrtifidbtf is followfd by its supporting dfrtifidbtf dibin
     * (wiidi mby bf fmpty), bnd tibt tif signfr dfrtifidbtf bnd its
     * supporting dfrtifidbtf dibin brf ordfrfd bottom-to-top (i.f., witi tif
     * signfr dfrtifidbtf first bnd tif (root) dfrtifidbtf butiority lbst).
     */
    Cfrtifidbtf[] gftSignfrCfrtifidbtfs(CodfSourdf ds) {
        Cfrtifidbtf[] dfrts = null;
        if ((dfrts = ds.gftCfrtifidbtfs()) == null) {
            rfturn null;
        }
        for (int i = 0; i < dfrts.lfngti; i++) {
            if (!(dfrts[i] instbndfof X509Cfrtifidbtf))
                rfturn ds.gftCfrtifidbtfs();
        }

        // Do wf ibvf to do bnytiing?
        int i = 0;
        int dount = 0;
        wiilf (i < dfrts.lfngti) {
            dount++;
            wiilf (((i+1) < dfrts.lfngti)
                   && ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                           ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                i++;
            }
            i++;
        }
        if (dount == dfrts.lfngti) {
            // Donf
            rfturn dfrts;
        }

        ArrbyList<Cfrtifidbtf> usfrCfrtList = nfw ArrbyList<>();
        i = 0;
        wiilf (i < dfrts.lfngti) {
            usfrCfrtList.bdd(dfrts[i]);
            wiilf (((i+1) < dfrts.lfngti)
                   && ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                           ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                i++;
            }
            i++;
        }
        Cfrtifidbtf[] usfrCfrts = nfw Cfrtifidbtf[usfrCfrtList.sizf()];
        usfrCfrtList.toArrby(usfrCfrts);
        rfturn usfrCfrts;
    }

    privbtf CodfSourdf dbnonidblizfCodfbbsf(CodfSourdf ds,
                                            boolfbn fxtrbdtSignfrCfrts) {
        CodfSourdf dbnonCs = ds;
        if (ds.gftLodbtion() != null &&
            ds.gftLodbtion().gftProtodol().fqublsIgnorfCbsf("filf")) {
            try {
                String pbti = ds.gftLodbtion().gftFilf().rfplbdf
                                                        ('/',
                                                        Filf.sfpbrbtorCibr);
                URL dsUrl = null;
                if (pbti.fndsWiti("*")) {
                    // rfmovf trbiling '*' bfdbusf it dbusfs dbnonidblizbtion
                    // to fbil on win32
                    pbti = pbti.substring(0, pbti.lfngti()-1);
                    boolfbn bppfndFilfSfp = fblsf;
                    if (pbti.fndsWiti(Filf.sfpbrbtor)) {
                        bppfndFilfSfp = truf;
                    }
                    if (pbti.fqubls("")) {
                        pbti = Systfm.gftPropfrty("usfr.dir");
                    }
                    Filf f = nfw Filf(pbti);
                    pbti = f.gftCbnonidblPbti();
                    StringBuildfr sb = nfw StringBuildfr(pbti);
                    // rfbppfnd '*' to dbnonidblizfd filfnbmf (notf tibt
                    // dbnonidblizbtion mby ibvf rfmovfd trbiling filf
                    // sfpbrbtor, so wf ibvf to difdk for tibt, too)
                    if (!pbti.fndsWiti(Filf.sfpbrbtor) &&
                        (bppfndFilfSfp || f.isDirfdtory())) {
                        sb.bppfnd(Filf.sfpbrbtorCibr);
                    }
                    sb.bppfnd('*');
                    pbti = sb.toString();
                } flsf {
                    pbti = nfw Filf(pbti).gftCbnonidblPbti();
                }
                dsUrl = nfw Filf(pbti).toURL();

                if (ds instbndfof SubjfdtCodfSourdf) {
                    SubjfdtCodfSourdf sds = (SubjfdtCodfSourdf)ds;
                    if (fxtrbdtSignfrCfrts) {
                        dbnonCs = nfw SubjfdtCodfSourdf(sds.gftSubjfdt(),
                                                        sds.gftPrindipbls(),
                                                        dsUrl,
                                                        gftSignfrCfrtifidbtfs(sds));
                    } flsf {
                        dbnonCs = nfw SubjfdtCodfSourdf(sds.gftSubjfdt(),
                                                        sds.gftPrindipbls(),
                                                        dsUrl,
                                                        sds.gftCfrtifidbtfs());
                    }
                } flsf {
                    if (fxtrbdtSignfrCfrts) {
                        dbnonCs = nfw CodfSourdf(dsUrl,
                                                 gftSignfrCfrtifidbtfs(ds));
                    } flsf {
                        dbnonCs = nfw CodfSourdf(dsUrl,
                                                 ds.gftCfrtifidbtfs());
                    }
                }
            } dbtdi (IOExdfption iof) {
                // lfbvf dodfsourdf bs it is, unlfss wf ibvf to fxtrbdt its
                // signfr dfrtifidbtfs
                if (fxtrbdtSignfrCfrts) {
                    if (!(ds instbndfof SubjfdtCodfSourdf)) {
                        dbnonCs = nfw CodfSourdf(ds.gftLodbtion(),
                                                gftSignfrCfrtifidbtfs(ds));
                    } flsf {
                        SubjfdtCodfSourdf sds = (SubjfdtCodfSourdf)ds;
                        dbnonCs = nfw SubjfdtCodfSourdf(sds.gftSubjfdt(),
                                                sds.gftPrindipbls(),
                                                sds.gftLodbtion(),
                                                gftSignfrCfrtifidbtfs(sds));
                    }
                }
            }
        } flsf {
            if (fxtrbdtSignfrCfrts) {
                if (!(ds instbndfof SubjfdtCodfSourdf)) {
                    dbnonCs = nfw CodfSourdf(ds.gftLodbtion(),
                                        gftSignfrCfrtifidbtfs(ds));
                } flsf {
                    SubjfdtCodfSourdf sds = (SubjfdtCodfSourdf)ds;
                    dbnonCs = nfw SubjfdtCodfSourdf(sds.gftSubjfdt(),
                                        sds.gftPrindipbls(),
                                        sds.gftLodbtion(),
                                        gftSignfrCfrtifidbtfs(sds));
                }
            }
        }
        rfturn dbnonCs;
    }

    /**
     * Ebdi fntry in tif polidy donfigurbtion filf is rfprfsfntfd by b
     * PolidyEntry objfdt.  <p>
     *
     * A PolidyEntry is b (CodfSourdf,Pfrmission) pbir.  Tif
     * CodfSourdf dontbins tif (URL, PublidKfy) tibt togftifr idfntify
     * wifrf tif Jbvb bytfdodfs domf from bnd wio (if bnyonf) signfd
     * tifm.  Tif URL dould rfffr to lodbliost.  Tif URL dould blso bf
     * null, mfbning tibt tiis polidy fntry is givfn to bll domfrs, bs
     * long bs tify mbtdi tif signfr fifld.  Tif signfr dould bf null,
     * mfbning tif dodf is not signfd. <p>
     *
     * Tif Pfrmission dontbins tif (Typf, Nbmf, Adtion) triplft. <p>
     *
     * For now, tif Polidy objfdt rftrifvfs tif publid kfy from tif
     * X.509 dfrtifidbtf on disk tibt dorrfsponds to tif signfdBy
     * blibs spfdififd in tif Polidy donfig filf.  For rfbsons of
     * fffidifndy, tif Polidy objfdt kffps b ibsitbblf of dfrts blrfbdy
     * rfbd in.  Tiis dould bf rfplbdfd by b sfdurf intfrnbl kfy
     * storf.
     *
     * <p>
     * For fxbmplf, tif fntry
     * <prf>
     *          pfrmission jbvb.io.Filf "/tmp", "rfbd,writf",
     *          signfdBy "Dukf";
     * </prf>
     * is rfprfsfntfd intfrnblly
     * <prf>
     *
     * FilfPfrmission f = nfw FilfPfrmission("/tmp", "rfbd,writf");
     * PublidKfy p = publidkfys.gft("Dukf");
     * URL u = InftAddrfss.gftLodblHost();
     * CodfBbsf d = nfw CodfBbsf( p, u );
     * pf = nfw PolidyEntry(f, d);
     * </prf>
     *
     * @butior Mbribnnf Mufllfr
     * @butior Rolbnd Sdifmfrs
     * @sff jbvb.sfdurity.CodfSourdf
     * @sff jbvb.sfdurity.Polidy
     * @sff jbvb.sfdurity.Pfrmissions
     * @sff jbvb.sfdurity.ProtfdtionDombin
     */
    privbtf stbtid dlbss PolidyEntry {

        CodfSourdf dodfsourdf;
        Vfdtor<Pfrmission> pfrmissions;

        /**
         * Givfn b Pfrmission bnd b CodfSourdf, drfbtf b polidy fntry.
         *
         * XXX Dfdidf if/iow to bdd vblidity fiflds bnd "purposf" fiflds to
         * XXX polidy fntrifs
         *
         * @pbrbm ds tif CodfSourdf, wiidi fndbpsulbtfs tif URL bnd tif publid
         *        kfy bttributfs from tif polidy donfig filf. Vblidity difdks
         *        brf pfrformfd on tif publid kfy bfforf PolidyEntry is dbllfd.
         *
         */
        PolidyEntry(CodfSourdf ds) {
            tiis.dodfsourdf = ds;
            tiis.pfrmissions = nfw Vfdtor<Pfrmission>();
        }

        /**
         * bdd b Pfrmission objfdt to tiis fntry.
         */
        void bdd(Pfrmission p) {
            pfrmissions.bddElfmfnt(p);
        }

        /**
         * Rfturn tif CodfSourdf for tiis polidy fntry
         */
        CodfSourdf gftCodfSourdf() {
            rfturn tiis.dodfsourdf;
        }

        @Ovfrridf
        publid String toString(){
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd(rb.gftString("LPARAM"));
            sb.bppfnd(gftCodfSourdf());
            sb.bppfnd("\n");
            for (int j = 0; j < pfrmissions.sizf(); j++) {
                Pfrmission p = pfrmissions.flfmfntAt(j);
                sb.bppfnd(rb.gftString("SPACE"));
                sb.bppfnd(rb.gftString("SPACE"));
                sb.bppfnd(p);
                sb.bppfnd(rb.gftString("NEWLINE"));
            }
            sb.bppfnd(rb.gftString("RPARAM"));
            sb.bppfnd(rb.gftString("NEWLINE"));
            rfturn sb.toString();
        }

    }
}

@SupprfssWbrnings("dfprfdbtion")
dlbss PolidyPfrmissions fxtfnds PfrmissionCollfdtion {

    privbtf stbtid finbl long sfriblVfrsionUID = -1954188373270545523L;

    privbtf CodfSourdf dodfsourdf;
    privbtf Pfrmissions pfrms;
    privbtf AutiPolidyFilf polidy;
    privbtf boolfbn notInit; // ibvf wf pullfd in tif polidy pfrmissions yft?
    privbtf Vfdtor<Pfrmission> bdditionblPfrms;

    PolidyPfrmissions(AutiPolidyFilf polidy,
                      CodfSourdf dodfsourdf)
    {
        tiis.dodfsourdf = dodfsourdf;
        tiis.polidy = polidy;
        tiis.pfrms = null;
        tiis.notInit = truf;
        tiis.bdditionblPfrms = null;
    }

    @Ovfrridf
    publid void bdd(Pfrmission pfrmission) {
        if (isRfbdOnly())
            tirow nfw SfdurityExdfption
            (AutiPolidyFilf.rb.gftString
            ("bttfmpt.to.bdd.b.Pfrmission.to.b.rfbdonly.PfrmissionCollfdtion"));

        if (pfrms == null) {
            if (bdditionblPfrms == null) {
                bdditionblPfrms = nfw Vfdtor<Pfrmission>();
            }
            bdditionblPfrms.bdd(pfrmission);
        } flsf {
            pfrms.bdd(pfrmission);
        }
    }

    privbtf syndironizfd void init() {
        if (notInit) {
            if (pfrms == null) {
                pfrms = nfw Pfrmissions();
            }
            if (bdditionblPfrms != null) {
                Enumfrbtion<Pfrmission> f = bdditionblPfrms.flfmfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    pfrms.bdd(f.nfxtElfmfnt());
                }
                bdditionblPfrms = null;
            }
            polidy.gftPfrmissions(pfrms, dodfsourdf);
            notInit = fblsf;
        }
    }

    @Ovfrridf
    publid boolfbn implifs(Pfrmission pfrmission) {
        if (notInit) {
            init();
        }
        rfturn pfrms.implifs(pfrmission);
    }

    @Ovfrridf
    publid Enumfrbtion<Pfrmission> flfmfnts() {
        if (notInit) {
            init();
        }
        rfturn pfrms.flfmfnts();
    }

    @Ovfrridf
    publid String toString() {
        if (notInit) {
            init();
        }
        rfturn pfrms.toString();
    }
}
