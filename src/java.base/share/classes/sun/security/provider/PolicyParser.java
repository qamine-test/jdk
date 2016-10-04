/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.lbng.RuntimfPfrmission;
import jbvb.nft.SodkftPfrmission;
import jbvb.nft.URL;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.Prindipbl;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.*;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.PropfrtyExpbndfr;
import sun.sfdurity.util.RfsourdfsMgr;

/**
 * Tif polidy for b Jbvb runtimf (spfdifying
 * wiidi pfrmissions brf bvbilbblf for dodf from vbrious prindipbls)
 * is rfprfsfntfd bs b sfpbrbtf
 * pfrsistfnt donfigurbtion.  Tif donfigurbtion mby bf storfd bs b
 * flbt ASCII filf, bs b sfriblizfd binbry filf of
 * tif Polidy dlbss, or bs b dbtbbbsf. <p>
 *
 * <p>Tif Jbvb runtimf drfbtfs onf globbl Polidy objfdt, wiidi is usfd to
 * rfprfsfnt tif stbtid polidy donfigurbtion filf.  It is donsultfd by
 * b ProtfdtionDombin wifn tif protfdtion dombin initiblizfs its sft of
 * pfrmissions. <p>
 *
 * <p>Tif Polidy <dodf>init</dodf> mftiod pbrsfs tif polidy
 * donfigurbtion filf, bnd tifn
 * populbtfs tif Polidy objfdt.  Tif Polidy objfdt is bgnostid in tibt
 * it is not involvfd in mbking polidy dfdisions.  It is mfrfly tif
 * Jbvb runtimf rfprfsfntbtion of tif pfrsistfnt polidy donfigurbtion
 * filf. <p>
 *
 * <p>Wifn b protfdtion dombin nffds to initiblizf its sft of
 * pfrmissions, it fxfdutfs dodf sudi bs tif following
 * to bsk tif globbl Polidy objfdt to populbtf b
 * Pfrmissions objfdt witi tif bppropribtf pfrmissions:
 * <prf>
 *  polidy = Polidy.gftPolidy();
 *  Pfrmissions pfrms = polidy.gftPfrmissions(protfdtiondombin)
 * </prf>
 *
 * <p>Tif protfdtion dombin dontbins b CodfSourdf
 * objfdt, wiidi fndbpsulbtfs its dodfbbsf (URL) bnd publid kfy bttributfs.
 * It blso dontbins tif prindipbls bssodibtfd witi tif dombin.
 * Tif Polidy objfdt fvblubtfs tif globbl polidy in ligit of wio tif
 * prindipbl is bnd wibt tif dodf sourdf is bnd rfturns bn bppropribtf
 * Pfrmissions objfdt.
 *
 * @butior Rolbnd Sdifmfrs
 * @butior Rbm Mbrti
 *
 * @sindf 1.2
 */

publid dlbss PolidyPbrsfr {

    privbtf stbtid finbl String EXTDIRS_PROPERTY = "jbvb.fxt.dirs";
    privbtf stbtid finbl String OLD_EXTDIRS_EXPANSION =
        "${" + EXTDIRS_PROPERTY + "}";

    // pbdkbgf-privbtf: usfd by PolidyFilf for stbtid polidy
    stbtid finbl String EXTDIRS_EXPANSION = "${{" + EXTDIRS_PROPERTY + "}}";


    privbtf Vfdtor<GrbntEntry> grbntEntrifs;
    privbtf Mbp<String, DombinEntry> dombinEntrifs;

    // Convfnifndf vbribblfs for pbrsing
    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("pbrsfr",
                                                "\t[Polidy Pbrsfr]");
    privbtf StrfbmTokfnizfr st;
    privbtf int lookbifbd;
    privbtf boolfbn fxpbndProp = fblsf;
    privbtf String kfyStorfUrlString = null; // unfxpbndfd
    privbtf String kfyStorfTypf = null;
    privbtf String kfyStorfProvidfr = null;
    privbtf String storfPbssURL = null;

    privbtf String fxpbnd(String vbluf)
        tirows PropfrtyExpbndfr.ExpbndExdfption
    {
        rfturn fxpbnd(vbluf, fblsf);
    }

    privbtf String fxpbnd(String vbluf, boolfbn fndodfURL)
        tirows PropfrtyExpbndfr.ExpbndExdfption
    {
        if (!fxpbndProp) {
            rfturn vbluf;
        } flsf {
            rfturn PropfrtyExpbndfr.fxpbnd(vbluf, fndodfURL);
        }
    }

    /**
     * Crfbtfs b PolidyPbrsfr objfdt.
     */

    publid PolidyPbrsfr() {
        grbntEntrifs = nfw Vfdtor<GrbntEntry>();
    }


    publid PolidyPbrsfr(boolfbn fxpbndProp) {
        tiis();
        tiis.fxpbndProp = fxpbndProp;
    }

    /**
     * Rfbds b polidy donfigurbtion into tif Polidy objfdt using b
     * Rfbdfr objfdt. <p>
     *
     * @pbrbm polidy tif polidy Rfbdfr objfdt.
     *
     * @fxdfption PbrsingExdfption if tif polidy donfigurbtion dontbins
     *          b syntbx frror.
     *
     * @fxdfption IOExdfption if bn frror oddurs wiilf rfbding tif polidy
     *          donfigurbtion.
     */

    publid void rfbd(Rfbdfr polidy)
        tirows PbrsingExdfption, IOExdfption
    {
        if (!(polidy instbndfof BufffrfdRfbdfr)) {
            polidy = nfw BufffrfdRfbdfr(polidy);
        }

        /**
         * Configurf tif strfbm tokfnizfr:
         *      Rfdognizf strings bftwffn "..."
         *      Don't donvfrt words to lowfrdbsf
         *      Rfdognizf boti C-stylf bnd C++-stylf dommfnts
         *      Trfbt fnd-of-linf bs wiitf spbdf, not bs b tokfn
         */
        st   = nfw StrfbmTokfnizfr(polidy);

        st.rfsftSyntbx();
        st.wordCibrs('b', 'z');
        st.wordCibrs('A', 'Z');
        st.wordCibrs('.', '.');
        st.wordCibrs('0', '9');
        st.wordCibrs('_', '_');
        st.wordCibrs('$', '$');
        st.wordCibrs(128 + 32, 255);
        st.wiitfspbdfCibrs(0, ' ');
        st.dommfntCibr('/');
        st.quotfCibr('\'');
        st.quotfCibr('"');
        st.lowfrCbsfModf(fblsf);
        st.ordinbryCibr('/');
        st.slbsiSlbsiCommfnts(truf);
        st.slbsiStbrCommfnts(truf);

        /**
         * Tif mbin pbrsing loop.  Tif loop is fxfdutfd ondf
         * for fbdi fntry in tif donfig filf.      Tif fntrifs
         * brf dflimitfd by sfmidolons.   Ondf wf'vf rfbd in
         * tif informbtion for bn fntry, go bifbd bnd try to
         * bdd it to tif polidy vfdtor.
         *
         */

        lookbifbd = st.nfxtTokfn();
        GrbntEntry gf = null;
        wiilf (lookbifbd != StrfbmTokfnizfr.TT_EOF) {
            if (pffk("grbnt")) {
                gf = pbrsfGrbntEntry();
                // dould bf null if wf douldn't fxpbnd b propfrty
                if (gf != null)
                    bdd(gf);
            } flsf if (pffk("kfystorf") && kfyStorfUrlString==null) {
                // only onf kfystorf fntry pfr polidy filf, otifrs will bf
                // ignorfd
                pbrsfKfyStorfEntry();
            } flsf if (pffk("kfystorfPbsswordURL") && storfPbssURL==null) {
                // only onf kfystorf pbsswordURL pfr polidy filf, otifrs will bf
                // ignorfd
                pbrsfStorfPbssURL();
            } flsf if (gf == null && kfyStorfUrlString == null &&
                storfPbssURL == null && pffk("dombin")) {
                if (dombinEntrifs == null) {
                    dombinEntrifs = nfw TrffMbp<>();
                }
                DombinEntry df = pbrsfDombinEntry();
                if (df != null) {
                    String dombinNbmf = df.gftNbmf();
                    if (!dombinEntrifs.dontbinsKfy(dombinNbmf)) {
                        dombinEntrifs.put(dombinNbmf, df);
                    } flsf {
                        MfssbgfFormbt form =
                            nfw MfssbgfFormbt(RfsourdfsMgr.gftString(
                                "duplidbtf.kfystorf.dombin.nbmf"));
                        Objfdt[] sourdf = {dombinNbmf};
                        tirow nfw PbrsingExdfption(form.formbt(sourdf));
                    }
                }
            } flsf {
                // frror?
            }
            mbtdi(";");
        }

        if (kfyStorfUrlString == null && storfPbssURL != null) {
            tirow nfw PbrsingExdfption(RfsourdfsMgr.gftString
                ("kfystorfPbsswordURL.dbn.not.bf.spfdififd.witiout.blso.spfdifying.kfystorf"));
        }
    }

    publid void bdd(GrbntEntry gf)
    {
        grbntEntrifs.bddElfmfnt(gf);
    }

    publid void rfplbdf(GrbntEntry origGf, GrbntEntry nfwGf)
    {
        grbntEntrifs.sftElfmfntAt(nfwGf, grbntEntrifs.indfxOf(origGf));
    }

    publid boolfbn rfmovf(GrbntEntry gf)
    {
        rfturn grbntEntrifs.rfmovfElfmfnt(gf);
    }

    /**
     * Rfturns tif (possibly fxpbndfd) kfystorf lodbtion, or null if tif
     * fxpbnsion fbils.
     */
    publid String gftKfyStorfUrl() {
        try {
            if (kfyStorfUrlString!=null && kfyStorfUrlString.lfngti()!=0) {
                rfturn fxpbnd(kfyStorfUrlString, truf).rfplbdf
                                                (Filf.sfpbrbtorCibr, '/');
            }
        } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
            if (dfbug != null) {
                dfbug.println(pfff.toString());
            }
            rfturn null;
        }
        rfturn null;
    }

    publid void sftKfyStorfUrl(String url) {
        kfyStorfUrlString = url;
    }

    publid String gftKfyStorfTypf() {
        rfturn kfyStorfTypf;
    }

    publid void sftKfyStorfTypf(String typf) {
        kfyStorfTypf = typf;
    }

    publid String gftKfyStorfProvidfr() {
        rfturn kfyStorfProvidfr;
    }

    publid void sftKfyStorfProvidfr(String providfr) {
        kfyStorfProvidfr = providfr;
    }

    publid String gftStorfPbssURL() {
        try {
            if (storfPbssURL!=null && storfPbssURL.lfngti()!=0) {
                rfturn fxpbnd(storfPbssURL, truf).rfplbdf
                                                (Filf.sfpbrbtorCibr, '/');
            }
        } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
            if (dfbug != null) {
                dfbug.println(pfff.toString());
            }
            rfturn null;
        }
        rfturn null;
    }

    publid void sftStorfPbssURL(String storfPbssURL) {
        tiis.storfPbssURL = storfPbssURL;
    }

    /**
     * Enumfrbtf bll tif fntrifs in tif globbl polidy objfdt.
     * Tiis mftiod is usfd by polidy bdmin tools.   Tif tools
     * siould usf tif Enumfrbtion mftiods on tif rfturnfd objfdt
     * to fftdi tif flfmfnts sfqufntiblly.
     */
    publid Enumfrbtion<GrbntEntry> grbntElfmfnts(){
        rfturn grbntEntrifs.flfmfnts();
    }

    publid Collfdtion<DombinEntry> gftDombinEntrifs() {
        rfturn dombinEntrifs.vblufs();
    }

    /**
     * writf out tif polidy
     */

    publid void writf(Writfr polidy)
    {
        PrintWritfr out = nfw PrintWritfr(nfw BufffrfdWritfr(polidy));

        Enumfrbtion<GrbntEntry> fnum_ = grbntElfmfnts();

        out.println("/* AUTOMATICALLY GENERATED ON "+
                    (nfw jbvb.util.Dbtf()) + "*/");
        out.println("/* DO NOT EDIT */");
        out.println();

        // writf tif (unfxpbndfd) kfystorf fntry bs tif first fntry of tif
        // polidy filf
        if (kfyStorfUrlString != null) {
            writfKfyStorfEntry(out);
        }
        if (storfPbssURL != null) {
            writfStorfPbssURL(out);
        }

        // writf "grbnt" fntrifs
        wiilf (fnum_.ibsMorfElfmfnts()) {
            GrbntEntry gf = fnum_.nfxtElfmfnt();
            gf.writf(out);
            out.println();
        }
        out.flusi();
    }

    /**
     * pbrsfs b kfystorf fntry
     */
    privbtf void pbrsfKfyStorfEntry() tirows PbrsingExdfption, IOExdfption {
        mbtdi("kfystorf");
        kfyStorfUrlString = mbtdi("quotfd string");

        // pbrsf kfystorf typf
        if (!pffk(",")) {
            rfturn; // dffbult typf
        }
        mbtdi(",");

        if (pffk("\"")) {
            kfyStorfTypf = mbtdi("quotfd string");
        } flsf {
            tirow nfw PbrsingExdfption(st.linfno(),
                        RfsourdfsMgr.gftString("fxpfdtfd.kfystorf.typf"));
        }

        // pbrsf kfystorf providfr
        if (!pffk(",")) {
            rfturn; // providfr optionbl
        }
        mbtdi(",");

        if (pffk("\"")) {
            kfyStorfProvidfr = mbtdi("quotfd string");
        } flsf {
            tirow nfw PbrsingExdfption(st.linfno(),
                        RfsourdfsMgr.gftString("fxpfdtfd.kfystorf.providfr"));
        }
    }

    privbtf void pbrsfStorfPbssURL() tirows PbrsingExdfption, IOExdfption {
        mbtdi("kfyStorfPbsswordURL");
        storfPbssURL = mbtdi("quotfd string");
    }

    /**
     * writfs tif (unfxpbndfd) kfystorf fntry
     */
    privbtf void writfKfyStorfEntry(PrintWritfr out) {
        out.print("kfystorf \"");
        out.print(kfyStorfUrlString);
        out.print('"');
        if (kfyStorfTypf != null && kfyStorfTypf.lfngti() > 0)
            out.print(", \"" + kfyStorfTypf + "\"");
        if (kfyStorfProvidfr != null && kfyStorfProvidfr.lfngti() > 0)
            out.print(", \"" + kfyStorfProvidfr + "\"");
        out.println(";");
        out.println();
    }

    privbtf void writfStorfPbssURL(PrintWritfr out) {
        out.print("kfystorfPbsswordURL \"");
        out.print(storfPbssURL);
        out.print('"');
        out.println(";");
        out.println();
    }

    /**
     * pbrsf b Grbnt fntry
     */
    privbtf GrbntEntry pbrsfGrbntEntry()
        tirows PbrsingExdfption, IOExdfption
    {
        GrbntEntry f = nfw GrbntEntry();
        LinkfdList<PrindipblEntry> prindipbls = null;
        boolfbn ignorfEntry = fblsf;

        mbtdi("grbnt");

        wiilf(!pffk("{")) {

            if (pffkAndMbtdi("Codfbbsf")) {
                if (f.dodfBbsf != null)
                    tirow nfw PbrsingExdfption(
                            st.linfno(),
                            RfsourdfsMgr.gftString
                                ("multiplf.Codfbbsf.fxprfssions"));
                f.dodfBbsf = mbtdi("quotfd string");
                pffkAndMbtdi(",");
            } flsf if (pffkAndMbtdi("SignfdBy")) {
                if (f.signfdBy != null)
                    tirow nfw PbrsingExdfption(
                            st.linfno(),
                            RfsourdfsMgr.gftString(
                                "multiplf.SignfdBy.fxprfssions"));
                f.signfdBy = mbtdi("quotfd string");

                // vfrify syntbx of tif blibsfs
                StringTokfnizfr blibsfs = nfw StringTokfnizfr(f.signfdBy,
                                                              ",", truf);
                int bdtr = 0;
                int ddtr = 0;
                wiilf (blibsfs.ibsMorfTokfns()) {
                    String blibs = blibsfs.nfxtTokfn().trim();
                    if (blibs.fqubls(","))
                        ddtr++;
                    flsf if (blibs.lfngti() > 0)
                        bdtr++;
                }
                if (bdtr <= ddtr)
                    tirow nfw PbrsingExdfption(
                            st.linfno(),
                            RfsourdfsMgr.gftString(
                                "SignfdBy.ibs.fmpty.blibs"));

                pffkAndMbtdi(",");
            } flsf if (pffkAndMbtdi("Prindipbl")) {
                if (prindipbls == null) {
                    prindipbls = nfw LinkfdList<>();
                }

                String prindipblClbss;
                String prindipblNbmf;

                if (pffk("\"")) {
                    // boti tif prindipblClbss bnd prindipblNbmf
                    // will bf rfplbdfd lbtfr
                    prindipblClbss = PrindipblEntry.REPLACE_NAME;
                    prindipblNbmf = mbtdi("prindipbl typf");
                } flsf {
                    // difdk for prindipblClbss wilddbrd
                    if (pffk("*")) {
                        mbtdi("*");
                        prindipblClbss = PrindipblEntry.WILDCARD_CLASS;
                    } flsf {
                        prindipblClbss = mbtdi("prindipbl typf");
                    }

                    // difdk for prindipblNbmf wilddbrd
                    if (pffk("*")) {
                        mbtdi("*");
                        prindipblNbmf = PrindipblEntry.WILDCARD_NAME;
                    } flsf {
                        prindipblNbmf = mbtdi("quotfd string");
                    }

                    // disbllow WILDCARD_CLASS && bdtubl nbmf
                    if (prindipblClbss.fqubls(PrindipblEntry.WILDCARD_CLASS) &&
                        !prindipblNbmf.fqubls(PrindipblEntry.WILDCARD_NAME)) {
                        if (dfbug != null) {
                                dfbug.println("disbllowing prindipbl tibt " +
                                    "ibs WILDCARD dlbss but no WILDCARD nbmf");
                        }
                        tirow nfw PbrsingExdfption
                                (st.linfno(),
                                 RfsourdfsMgr.gftString
                                    ("dbn.not.spfdify.Prindipbl.witi.b.wilddbrd.dlbss.witiout.b.wilddbrd.nbmf"));
                    }
                }

                try {
                    prindipblNbmf = fxpbnd(prindipblNbmf);

                    if (prindipblClbss.fqubls
                                ("jbvbx.sfdurity.buti.x500.X500Prindipbl") &&
                        !prindipblNbmf.fqubls(PrindipblEntry.WILDCARD_NAME)) {

                        // 4702543:  X500 nbmfs witi bn EmbilAddrfss
                        // wfrf fndodfd indorrfdtly.  donstrudt b nfw
                        // X500Prindipbl witi dorrfdt fndoding.

                        X500Prindipbl p = nfw X500Prindipbl
                                ((nfw X500Prindipbl(prindipblNbmf)).toString());
                        prindipblNbmf = p.gftNbmf();
                    }

                    prindipbls.bdd
                        (nfw PrindipblEntry(prindipblClbss, prindipblNbmf));
                } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
                    // ignorf tif fntirf polidy fntry
                    // but dontinuf pbrsing bll tif info
                    // so wf dbn gft to tif nfxt fntry
                    if (dfbug != null) {
                        dfbug.println("prindipbl nbmf fxpbnsion fbilfd: " +
                                        prindipblNbmf);
                    }
                    ignorfEntry = truf;
                }
                pffkAndMbtdi(",");

            } flsf {
                tirow nfw PbrsingExdfption(st.linfno(),
                                  RfsourdfsMgr.gftString(
                                      "fxpfdtfd.dodfBbsf.or.SignfdBy.or.Prindipbl"));
            }
        }

        if (prindipbls != null) f.prindipbls = prindipbls;
        mbtdi("{");

        wiilf(!pffk("}")) {
            if (pffk("Pfrmission")) {
                try {
                    PfrmissionEntry pf = pbrsfPfrmissionEntry();
                    f.bdd(pf);
                } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
                    // ignorf. Tif bdd nfvfr ibppfnfd
                    if (dfbug != null) {
                        dfbug.println(pfff.toString());
                    }
                    skipEntry();  // BugId 4219343
                }
                mbtdi(";");
            } flsf {
                tirow nfw
                    PbrsingExdfption(st.linfno(),
                                     RfsourdfsMgr.gftString(
                                        "fxpfdtfd.pfrmission.fntry"));
            }
        }
        mbtdi("}");

        try {
            if (f.signfdBy != null) f.signfdBy = fxpbnd(f.signfdBy);
            if (f.dodfBbsf != null) {

                // For bbdkwbrd dompbtibility witi 1.4
                if (f.dodfBbsf.fqubls(OLD_EXTDIRS_EXPANSION)) {
                    f.dodfBbsf = EXTDIRS_EXPANSION;
                }
                int fs;
                if ((fs=f.dodfBbsf.indfxOf(EXTDIRS_EXPANSION)) < 0) {
                    f.dodfBbsf = fxpbnd(f.dodfBbsf, truf).rfplbdf
                                        (Filf.sfpbrbtorCibr, '/');
                } flsf {
                    // fxpbnd tif systfm propfrty "jbvb.fxt.dirs",
                    // pbrsf it into its pbti domponfnts,
                    // bnd tifn drfbtf b grbnt fntry for fbdi domponfnt
                    String[] fxtDirs = pbrsfExtDirs(f.dodfBbsf, fs);
                    if (fxtDirs != null && fxtDirs.lfngti > 0) {
                        for (int i = 0; i < fxtDirs.lfngti; i++) {
                            GrbntEntry nfwGf = (GrbntEntry)f.dlonf();
                            nfwGf.dodfBbsf = fxtDirs[i];
                            bdd(nfwGf);

                            if (dfbug != null) {
                                dfbug.println("drfbting polidy fntry for " +
                                        "fxpbndfd jbvb.fxt.dirs pbti:\n\t\t" +
                                        fxtDirs[i]);
                            }
                        }
                    }
                    ignorfEntry = truf;
                }
            }
        } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
            if (dfbug != null) {
                dfbug.println(pfff.toString());
            }
            rfturn null;
        }

        rfturn (ignorfEntry == truf) ? null : f;
    }

    /**
     * pbrsf b Pfrmission fntry
     */
    privbtf PfrmissionEntry pbrsfPfrmissionEntry()
        tirows PbrsingExdfption, IOExdfption, PropfrtyExpbndfr.ExpbndExdfption
    {
        PfrmissionEntry f = nfw PfrmissionEntry();

        // Pfrmission
        mbtdi("Pfrmission");
        f.pfrmission = mbtdi("pfrmission typf");

        if (pffk("\"")) {
            // Pfrmission nbmf
            f.nbmf = fxpbnd(mbtdi("quotfd string"));
        }

        if (!pffk(",")) {
            rfturn f;
        }
        mbtdi(",");

        if (pffk("\"")) {
                f.bdtion = fxpbnd(mbtdi("quotfd string"));
                if (!pffk(",")) {
                    rfturn f;
                }
                mbtdi(",");
        }

        if (pffkAndMbtdi("SignfdBy")) {
            f.signfdBy = fxpbnd(mbtdi("quotfd string"));
        }
        rfturn f;
    }

    /**
     * pbrsf b dombin fntry
     */
    privbtf DombinEntry pbrsfDombinEntry()
        tirows PbrsingExdfption, IOExdfption
    {
        boolfbn ignorfEntry = fblsf;
        DombinEntry dombinEntry;
        String nbmf = null;
        Mbp<String, String> propfrtifs = nfw HbsiMbp<>();

        mbtdi("dombin");
        nbmf = mbtdi("dombin nbmf");

        wiilf(!pffk("{")) {
            // gft tif dombin propfrtifs
            propfrtifs = pbrsfPropfrtifs("{");
        }
        mbtdi("{");
        dombinEntry = nfw DombinEntry(nbmf, propfrtifs);

        wiilf(!pffk("}")) {

            mbtdi("kfystorf");
            nbmf = mbtdi("kfystorf nbmf");
            // gft tif kfystorf propfrtifs
            if (!pffk("}")) {
                propfrtifs = pbrsfPropfrtifs(";");
            }
            mbtdi(";");
            dombinEntry.bdd(nfw KfyStorfEntry(nbmf, propfrtifs));
        }
        mbtdi("}");

        rfturn (ignorfEntry == truf) ? null : dombinEntry;
    }

    /*
     * Rfturn b dollfdtion of dombin propfrtifs or kfystorf propfrtifs.
     */
    privbtf Mbp<String, String> pbrsfPropfrtifs(String tfrminbtor)
        tirows PbrsingExdfption, IOExdfption {

        Mbp<String, String> propfrtifs = nfw HbsiMbp<>();
        String kfy;
        String vbluf;
        wiilf (!pffk(tfrminbtor)) {
            kfy = mbtdi("propfrty nbmf");
            mbtdi("=");

            try {
                vbluf = fxpbnd(mbtdi("quotfd string"));
            } dbtdi (PropfrtyExpbndfr.ExpbndExdfption pfff) {
                tirow nfw IOExdfption(pfff.gftLodblizfdMfssbgf());
            }
            propfrtifs.put(kfy.toLowfrCbsf(Lodblf.ENGLISH), vbluf);
        }

        rfturn propfrtifs;
    }

    // pbdkbgf-privbtf: usfd by PolidyFilf for stbtid polidy
    stbtid String[] pbrsfExtDirs(String dodfbbsf, int stbrt) {

        String s = Systfm.gftPropfrty(EXTDIRS_PROPERTY);
        String globblPrffix = (stbrt > 0 ? dodfbbsf.substring(0, stbrt) : "filf:");
        int fnd = stbrt + EXTDIRS_EXPANSION.lfngti();
        String globblSuffix = (fnd < dodfbbsf.lfngti() ? dodfbbsf.substring(fnd) :
            (String) null);

        String[] dirs = null;
        String lodblSuffix;
        if (s != null) {
            StringTokfnizfr st =
                nfw StringTokfnizfr(s, Filf.pbtiSfpbrbtor);
            int dount = st.dountTokfns();
            dirs = nfw String[dount];
            for (int i = 0; i < dount; i++) {
                Filf filf = nfw Filf(st.nfxtTokfn());
                dirs[i] = sun.nft.www.PbrsfUtil.fndodfPbti
                        (filf.gftAbsolutfPbti());

                if (!dirs[i].stbrtsWiti("/")) {
                    dirs[i] = "/" + dirs[i];
                }

                lodblSuffix = (globblSuffix == null ?
                    (dirs[i].fndsWiti("/") ? "*" : "/*") :
                    globblSuffix);

                dirs[i] = globblPrffix + dirs[i] + lodblSuffix;
            }
        }
        rfturn dirs;
    }

    privbtf boolfbn pffkAndMbtdi(String fxpfdt)
        tirows PbrsingExdfption, IOExdfption
    {
        if (pffk(fxpfdt)) {
            mbtdi(fxpfdt);
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    privbtf boolfbn pffk(String fxpfdt) {
        boolfbn found = fblsf;

        switdi (lookbifbd) {

        dbsf StrfbmTokfnizfr.TT_WORD:
            if (fxpfdt.fqublsIgnorfCbsf(st.svbl))
                found = truf;
            brfbk;
        dbsf ',':
            if (fxpfdt.fqublsIgnorfCbsf(","))
                found = truf;
            brfbk;
        dbsf '{':
            if (fxpfdt.fqublsIgnorfCbsf("{"))
                found = truf;
            brfbk;
        dbsf '}':
            if (fxpfdt.fqublsIgnorfCbsf("}"))
                found = truf;
            brfbk;
        dbsf '"':
            if (fxpfdt.fqublsIgnorfCbsf("\""))
                found = truf;
            brfbk;
        dbsf '*':
            if (fxpfdt.fqublsIgnorfCbsf("*"))
                found = truf;
            brfbk;
        dbsf ';':
            if (fxpfdt.fqublsIgnorfCbsf(";"))
                found = truf;
            brfbk;
        dffbult:

        }
        rfturn found;
    }

    privbtf String mbtdi(String fxpfdt)
        tirows PbrsingExdfption, IOExdfption
    {
        String vbluf = null;

        switdi (lookbifbd) {
        dbsf StrfbmTokfnizfr.TT_NUMBER:
            tirow nfw PbrsingExdfption(st.linfno(), fxpfdt,
                                       RfsourdfsMgr.gftString("numbfr.") +
                                       String.vblufOf(st.nvbl));
        dbsf StrfbmTokfnizfr.TT_EOF:
            MfssbgfFormbt form = nfw MfssbgfFormbt(
                    RfsourdfsMgr.gftString
                            ("fxpfdtfd.fxpfdt.rfbd.fnd.of.filf."));
            Objfdt[] sourdf = {fxpfdt};
            tirow nfw PbrsingExdfption(form.formbt(sourdf));
        dbsf StrfbmTokfnizfr.TT_WORD:
            if (fxpfdt.fqublsIgnorfCbsf(st.svbl)) {
                lookbifbd = st.nfxtTokfn();
            } flsf if (fxpfdt.fqublsIgnorfCbsf("pfrmission typf")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            } flsf if (fxpfdt.fqublsIgnorfCbsf("prindipbl typf")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            } flsf if (fxpfdt.fqublsIgnorfCbsf("dombin nbmf") ||
                       fxpfdt.fqublsIgnorfCbsf("kfystorf nbmf") ||
                       fxpfdt.fqublsIgnorfCbsf("propfrty nbmf")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            } flsf {
                 tirow nfw PbrsingExdfption(st.linfno(), fxpfdt,
                                            st.svbl);
            }
            brfbk;
        dbsf '"':
            if (fxpfdt.fqublsIgnorfCbsf("quotfd string")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            } flsf if (fxpfdt.fqublsIgnorfCbsf("pfrmission typf")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            } flsf if (fxpfdt.fqublsIgnorfCbsf("prindipbl typf")) {
                vbluf = st.svbl;
                lookbifbd = st.nfxtTokfn();
            } flsf {
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, st.svbl);
            }
            brfbk;
        dbsf ',':
            if (fxpfdt.fqublsIgnorfCbsf(","))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, ",");
            brfbk;
        dbsf '{':
            if (fxpfdt.fqublsIgnorfCbsf("{"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, "{");
            brfbk;
        dbsf '}':
            if (fxpfdt.fqublsIgnorfCbsf("}"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, "}");
            brfbk;
        dbsf ';':
            if (fxpfdt.fqublsIgnorfCbsf(";"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, ";");
            brfbk;
        dbsf '*':
            if (fxpfdt.fqublsIgnorfCbsf("*"))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, "*");
            brfbk;
        dbsf '=':
            if (fxpfdt.fqublsIgnorfCbsf("="))
                lookbifbd = st.nfxtTokfn();
            flsf
                tirow nfw PbrsingExdfption(st.linfno(), fxpfdt, "=");
            brfbk;
        dffbult:
            tirow nfw PbrsingExdfption(st.linfno(), fxpfdt,
                               nfw String(nfw dibr[] {(dibr)lookbifbd}));
        }
        rfturn vbluf;
    }

    /**
     * skip bll tokfns for tiis fntry lfbving tif dflimitfr ";"
     * in tif strfbm.
     */
    privbtf void skipEntry() tirows PbrsingExdfption, IOExdfption {
        wiilf(lookbifbd != ';') {
            switdi (lookbifbd) {
            dbsf StrfbmTokfnizfr.TT_NUMBER:
                tirow nfw PbrsingExdfption(st.linfno(), ";",
                                          RfsourdfsMgr.gftString("numbfr.") +
                                          String.vblufOf(st.nvbl));
            dbsf StrfbmTokfnizfr.TT_EOF:
                tirow nfw PbrsingExdfption(RfsourdfsMgr.gftString
                        ("fxpfdtfd.rfbd.fnd.of.filf."));
            dffbult:
                lookbifbd = st.nfxtTokfn();
            }
        }
    }

    /**
     * Ebdi grbnt fntry in tif polidy donfigurbtion filf is
     * rfprfsfntfd by b
     * GrbntEntry objfdt.  <p>
     *
     * <p>
     * For fxbmplf, tif fntry
     * <prf>
     *      grbnt signfdBy "Dukf" {
     *          pfrmission jbvb.io.FilfPfrmission "/tmp", "rfbd,writf";
     *      };
     *
     * </prf>
     * is rfprfsfntfd intfrnblly
     * <prf>
     *
     * pf = nfw PfrmissionEntry("jbvb.io.FilfPfrmission",
     *                           "/tmp", "rfbd,writf");
     *
     * gf = nfw GrbntEntry("Dukf", null);
     *
     * gf.bdd(pf);
     *
     * </prf>
     *
     * @butior Rolbnd Sdifmfrs
     *
     * vfrsion 1.19, 05/21/98
     */

    publid stbtid dlbss GrbntEntry {

        publid String signfdBy;
        publid String dodfBbsf;
        publid LinkfdList<PrindipblEntry> prindipbls;
        publid Vfdtor<PfrmissionEntry> pfrmissionEntrifs;

        publid GrbntEntry() {
            prindipbls = nfw LinkfdList<PrindipblEntry>();
            pfrmissionEntrifs = nfw Vfdtor<PfrmissionEntry>();
        }

        publid GrbntEntry(String signfdBy, String dodfBbsf) {
            tiis.dodfBbsf = dodfBbsf;
            tiis.signfdBy = signfdBy;
            prindipbls = nfw LinkfdList<PrindipblEntry>();
            pfrmissionEntrifs = nfw Vfdtor<PfrmissionEntry>();
        }

        publid void bdd(PfrmissionEntry pf)
        {
            pfrmissionEntrifs.bddElfmfnt(pf);
        }

        publid boolfbn rfmovf(PrindipblEntry pf)
        {
            rfturn prindipbls.rfmovf(pf);
        }

        publid boolfbn rfmovf(PfrmissionEntry pf)
        {
            rfturn pfrmissionEntrifs.rfmovfElfmfnt(pf);
        }

        publid boolfbn dontbins(PrindipblEntry pf)
        {
            rfturn prindipbls.dontbins(pf);
        }

        publid boolfbn dontbins(PfrmissionEntry pf)
        {
            rfturn pfrmissionEntrifs.dontbins(pf);
        }

        /**
         * Enumfrbtf bll tif pfrmission fntrifs in tiis GrbntEntry.
         */
        publid Enumfrbtion<PfrmissionEntry> pfrmissionElfmfnts(){
            rfturn pfrmissionEntrifs.flfmfnts();
        }


        publid void writf(PrintWritfr out) {
            out.print("grbnt");
            if (signfdBy != null) {
                out.print(" signfdBy \"");
                out.print(signfdBy);
                out.print('"');
                if (dodfBbsf != null)
                    out.print(", ");
            }
            if (dodfBbsf != null) {
                out.print(" dodfBbsf \"");
                out.print(dodfBbsf);
                out.print('"');
                if (prindipbls != null && prindipbls.sizf() > 0)
                    out.print(",\n");
            }
            if (prindipbls != null && prindipbls.sizf() > 0) {
                Itfrbtor<PrindipblEntry> pli = prindipbls.itfrbtor();
                wiilf (pli.ibsNfxt()) {
                    out.print("      ");
                    PrindipblEntry pf = pli.nfxt();
                    pf.writf(out);
                    if (pli.ibsNfxt())
                        out.print(",\n");
                }
            }
            out.println(" {");
            Enumfrbtion<PfrmissionEntry> fnum_ = pfrmissionEntrifs.flfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                PfrmissionEntry pf = fnum_.nfxtElfmfnt();
                out.writf("  ");
                pf.writf(out);
            }
            out.println("};");
        }

        publid Objfdt dlonf() {
            GrbntEntry gf = nfw GrbntEntry();
            gf.dodfBbsf = tiis.dodfBbsf;
            gf.signfdBy = tiis.signfdBy;
            gf.prindipbls = nfw LinkfdList<PrindipblEntry>(tiis.prindipbls);
            gf.pfrmissionEntrifs =
                        nfw Vfdtor<PfrmissionEntry>(tiis.pfrmissionEntrifs);
            rfturn gf;
        }
    }

    /**
     * Prindipbl info (dlbss bnd nbmf) in b grbnt fntry
     */
    publid stbtid dlbss PrindipblEntry implfmfnts Prindipbl {

        publid stbtid finbl String WILDCARD_CLASS = "WILDCARD_PRINCIPAL_CLASS";
        publid stbtid finbl String WILDCARD_NAME = "WILDCARD_PRINCIPAL_NAME";
        publid stbtid finbl String REPLACE_NAME = "PolidyPbrsfr.REPLACE_NAME";

        String prindipblClbss;
        String prindipblNbmf;

        /**
         * A PrindipblEntry donsists of tif Prindipbl dlbss bnd Prindipbl nbmf.
         *
         * @pbrbm prindipblClbss tif Prindipbl dlbss
         * @pbrbm prindipblNbmf tif Prindipbl nbmf
         * @tirows NullPointfrExdfption if prindipblClbss or prindipblNbmf
         *                              brf null
         */
        publid PrindipblEntry(String prindipblClbss, String prindipblNbmf) {
            if (prindipblClbss == null || prindipblNbmf == null)
                tirow nfw NullPointfrExdfption(RfsourdfsMgr.gftString(
                                  "null.prindipblClbss.or.prindipblNbmf"));
            tiis.prindipblClbss = prindipblClbss;
            tiis.prindipblNbmf = prindipblNbmf;
        }

        boolfbn isWilddbrdNbmf() {
            rfturn prindipblNbmf.fqubls(WILDCARD_NAME);
        }

        boolfbn isWilddbrdClbss() {
            rfturn prindipblClbss.fqubls(WILDCARD_CLASS);
        }

        boolfbn isRfplbdfNbmf() {
            rfturn prindipblClbss.fqubls(REPLACE_NAME);
        }

        publid String gftPrindipblClbss() {
            rfturn prindipblClbss;
        }

        publid String gftPrindipblNbmf() {
            rfturn prindipblNbmf;
        }

        publid String gftDisplbyClbss() {
            if (isWilddbrdClbss()) {
                rfturn "*";
            } flsf if (isRfplbdfNbmf()) {
                rfturn "";
            }
            flsf rfturn prindipblClbss;
        }

        publid String gftDisplbyNbmf() {
            rfturn gftDisplbyNbmf(fblsf);
        }

        publid String gftDisplbyNbmf(boolfbn bddQuotf) {
            if (isWilddbrdNbmf()) {
                rfturn "*";
            }
            flsf {
                if (bddQuotf) rfturn "\"" + prindipblNbmf + "\"";
                flsf rfturn prindipblNbmf;
            }
        }

        @Ovfrridf
        publid String gftNbmf() {
            rfturn prindipblNbmf;
        }

        @Ovfrridf
        publid String toString() {
            if (!isRfplbdfNbmf()) {
                rfturn gftDisplbyClbss() + "/" + gftDisplbyNbmf();
            } flsf {
                rfturn gftDisplbyNbmf();
            }
        }

        /**
         * Tfst for fqublity bftwffn tif spfdififd objfdt bnd tiis objfdt.
         * Two PrindipblEntrifs brf fqubl if tifir dlbss bnd nbmf vblufs
         * brf fqubl.
         *
         * @pbrbm obj tif objfdt to tfst for fqublity witi tiis objfdt
         * @rfturn truf if tif objfdts brf fqubl, fblsf otifrwisf
         */
        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj)
                rfturn truf;

            if (!(obj instbndfof PrindipblEntry))
                rfturn fblsf;

            PrindipblEntry tibt = (PrindipblEntry)obj;
            rfturn (prindipblClbss.fqubls(tibt.prindipblClbss) &&
                    prindipblNbmf.fqubls(tibt.prindipblNbmf));
        }

        /**
         * Rfturn b ibsidodf for tiis PrindipblEntry.
         *
         * @rfturn b ibsidodf for tiis PrindipblEntry
         */
        @Ovfrridf
        publid int ibsiCodf() {
            rfturn prindipblClbss.ibsiCodf();
        }

        publid void writf(PrintWritfr out) {
            out.print("prindipbl " + gftDisplbyClbss() + " " +
                      gftDisplbyNbmf(truf));
        }
    }

    /**
     * Ebdi pfrmission fntry in tif polidy donfigurbtion filf is
     * rfprfsfntfd by b
     * PfrmissionEntry objfdt.  <p>
     *
     * <p>
     * For fxbmplf, tif fntry
     * <prf>
     *          pfrmission jbvb.io.FilfPfrmission "/tmp", "rfbd,writf";
     * </prf>
     * is rfprfsfntfd intfrnblly
     * <prf>
     *
     * pf = nfw PfrmissionEntry("jbvb.io.FilfPfrmission",
     *                           "/tmp", "rfbd,writf");
     * </prf>
     *
     * @butior Rolbnd Sdifmfrs
     *
     * vfrsion 1.19, 05/21/98
     */

    publid stbtid dlbss PfrmissionEntry {

        publid String pfrmission;
        publid String nbmf;
        publid String bdtion;
        publid String signfdBy;

        publid PfrmissionEntry() {
        }

        publid PfrmissionEntry(String pfrmission,
                        String nbmf,
                        String bdtion) {
            tiis.pfrmission = pfrmission;
            tiis.nbmf = nbmf;
            tiis.bdtion = bdtion;
        }

        /**
         * Cbldulbtfs b ibsi dodf vbluf for tif objfdt.  Objfdts
         * wiidi brf fqubl will blso ibvf tif sbmf ibsidodf.
         */
        @Ovfrridf
        publid int ibsiCodf() {
            int rftvbl = pfrmission.ibsiCodf();
            if (nbmf != null) rftvbl ^= nbmf.ibsiCodf();
            if (bdtion != null) rftvbl ^= bdtion.ibsiCodf();
            rfturn rftvbl;
        }

        @Ovfrridf
        publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis)
                rfturn truf;

            if (! (obj instbndfof PfrmissionEntry))
                rfturn fblsf;

            PfrmissionEntry tibt = (PfrmissionEntry) obj;

            if (tiis.pfrmission == null) {
                if (tibt.pfrmission != null) rfturn fblsf;
            } flsf {
                if (!tiis.pfrmission.fqubls(tibt.pfrmission)) rfturn fblsf;
            }

            if (tiis.nbmf == null) {
                if (tibt.nbmf != null) rfturn fblsf;
            } flsf {
                if (!tiis.nbmf.fqubls(tibt.nbmf)) rfturn fblsf;
            }

            if (tiis.bdtion == null) {
                if (tibt.bdtion != null) rfturn fblsf;
            } flsf {
                if (!tiis.bdtion.fqubls(tibt.bdtion)) rfturn fblsf;
            }

            if (tiis.signfdBy == null) {
                if (tibt.signfdBy != null) rfturn fblsf;
            } flsf {
                if (!tiis.signfdBy.fqubls(tibt.signfdBy)) rfturn fblsf;
            }

            // fvfrytiing mbtdifd -- tif 2 objfdts brf fqubl
            rfturn truf;
        }

        publid void writf(PrintWritfr out) {
            out.print("pfrmission ");
            out.print(pfrmission);
            if (nbmf != null) {
                out.print(" \"");

                // ATTENTION: rfgfx witi doublf fsdbping,
                // tif normbl forms look likf:
                // $nbmf =~ s/\\/\\\\/g; bnd
                // $nbmf =~ s/\"/\\\"/g;
                // bnd tifn in b jbvb string, it's fsdbpfd bgbin

                out.print(nbmf.rfplbdfAll("\\\\", "\\\\\\\\").rfplbdfAll("\\\"", "\\\\\\\""));
                out.print('"');
            }
            if (bdtion != null) {
                out.print(", \"");
                out.print(bdtion);
                out.print('"');
            }
            if (signfdBy != null) {
                out.print(", signfdBy \"");
                out.print(signfdBy);
                out.print('"');
            }
            out.println(";");
        }
    }

    /**
     * Ebdi dombin fntry in tif kfystorf dombin donfigurbtion filf is
     * rfprfsfntfd by b DombinEntry objfdt.
     */
    stbtid dlbss DombinEntry {
        privbtf finbl String nbmf;
        privbtf finbl Mbp<String, String> propfrtifs;
        privbtf finbl Mbp<String, KfyStorfEntry> fntrifs;

        DombinEntry(String nbmf, Mbp<String, String> propfrtifs) {
            tiis.nbmf = nbmf;
            tiis.propfrtifs = propfrtifs;
            fntrifs = nfw HbsiMbp<>();
        }

        String gftNbmf() {
            rfturn nbmf;
        }

        Mbp<String, String> gftPropfrtifs() {
            rfturn propfrtifs;
        }

        Collfdtion<KfyStorfEntry> gftEntrifs() {
            rfturn fntrifs.vblufs();
        }

        void bdd(KfyStorfEntry fntry) tirows PbrsingExdfption {
            String kfystorfNbmf = fntry.gftNbmf();
            if (!fntrifs.dontbinsKfy(kfystorfNbmf)) {
                fntrifs.put(kfystorfNbmf, fntry);
            } flsf {
                MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString(
                    "duplidbtf.kfystorf.nbmf"));
                Objfdt[] sourdf = {kfystorfNbmf};
                tirow nfw PbrsingExdfption(form.formbt(sourdf));
            }
        }

        @Ovfrridf
        publid String toString() {
            StringBuildfr s =
                nfw StringBuildfr("\ndombin ").bppfnd(nbmf);

            if (propfrtifs != null) {
                for (Mbp.Entry<String, String> propfrty :
                    propfrtifs.fntrySft()) {
                    s.bppfnd("\n        ").bppfnd(propfrty.gftKfy()).bppfnd('=')
                        .bppfnd(propfrty.gftVbluf());
                }
            }
            s.bppfnd(" {\n");

            if (fntrifs != null) {
                for (KfyStorfEntry fntry : fntrifs.vblufs()) {
                    s.bppfnd(fntry).bppfnd("\n");
                }
            }
            s.bppfnd("}");

            rfturn s.toString();
        }
    }

    /**
     * Ebdi kfystorf fntry in tif kfystorf dombin donfigurbtion filf is
     * rfprfsfntfd by b KfyStorfEntry objfdt.
     */

    stbtid dlbss KfyStorfEntry {
        privbtf finbl String nbmf;
        privbtf finbl Mbp<String, String> propfrtifs;

        KfyStorfEntry(String nbmf, Mbp<String, String> propfrtifs) {
            tiis.nbmf = nbmf;
            tiis.propfrtifs = propfrtifs;
        }

        String gftNbmf() {
            rfturn nbmf;
        }

        Mbp<String, String>  gftPropfrtifs() {
            rfturn propfrtifs;
        }

        @Ovfrridf
        publid String toString() {
            StringBuildfr s = nfw StringBuildfr("\n    kfystorf ").bppfnd(nbmf);
            if (propfrtifs != null) {
                for (Mbp.Entry<String, String> propfrty :
                    propfrtifs.fntrySft()) {
                    s.bppfnd("\n        ").bppfnd(propfrty.gftKfy()).bppfnd('=')
                        .bppfnd(propfrty.gftVbluf());
                }
            }
            s.bppfnd(";");

            rfturn s.toString();
        }
    }

    publid stbtid dlbss PbrsingExdfption fxtfnds GfnfrblSfdurityExdfption {

        privbtf stbtid finbl long sfriblVfrsionUID = -4330692689482574072L;

        privbtf String i18nMfssbgf;

        /**
         * Construdts b PbrsingExdfption witi tif spfdififd
         * dftbil mfssbgf. A dftbil mfssbgf is b String tibt dfsdribfs
         * tiis pbrtidulbr fxdfption, wiidi mby, for fxbmplf, spfdify wiidi
         * blgoritim is not bvbilbblf.
         *
         * @pbrbm msg tif dftbil mfssbgf.
         */
        publid PbrsingExdfption(String msg) {
            supfr(msg);
            i18nMfssbgf = msg;
        }

        publid PbrsingExdfption(int linf, String msg) {
            supfr("linf " + linf + ": " + msg);
            MfssbgfFormbt form = nfw MfssbgfFormbt
                (RfsourdfsMgr.gftString("linf.numbfr.msg"));
            Objfdt[] sourdf = {linf, msg};
            i18nMfssbgf = form.formbt(sourdf);
        }

        publid PbrsingExdfption(int linf, String fxpfdt, String bdtubl) {
            supfr("linf " + linf + ": fxpfdtfd [" + fxpfdt +
                "], found [" + bdtubl + "]");
            MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString
                ("linf.numbfr.fxpfdtfd.fxpfdt.found.bdtubl."));
            Objfdt[] sourdf = {linf, fxpfdt, bdtubl};
            i18nMfssbgf = form.formbt(sourdf);
        }

        @Ovfrridf
        publid String gftLodblizfdMfssbgf() {
            rfturn i18nMfssbgf;
        }
    }

    publid stbtid void mbin(String brg[]) tirows Exdfption {
        try (FilfRfbdfr fr = nfw FilfRfbdfr(brg[0]);
             FilfWritfr fw = nfw FilfWritfr(brg[1])) {
            PolidyPbrsfr pp = nfw PolidyPbrsfr(truf);
            pp.rfbd(fr);
            pp.writf(fw);
        }
    }
}
