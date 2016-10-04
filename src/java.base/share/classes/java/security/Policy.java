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


pbdkbgf jbvb.sfdurity;

import jbvb.util.Enumfrbtion;
import jbvb.util.WfbkHbsiMbp;
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf;
import sun.sfdurity.jdb.GftInstbndf;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.SfdurityConstbnts;


/**
 * A Polidy objfdt is rfsponsiblf for dftfrmining wiftifr dodf fxfduting
 * in tif Jbvb runtimf fnvironmfnt ibs pfrmission to pfrform b
 * sfdurity-sfnsitivf opfrbtion.
 *
 * <p> Tifrf is only onf Polidy objfdt instbllfd in tif runtimf bt bny
 * givfn timf.  A Polidy objfdt dbn bf instbllfd by dblling tif
 * {@dodf sftPolidy} mftiod.  Tif instbllfd Polidy objfdt dbn bf
 * obtbinfd by dblling tif {@dodf gftPolidy} mftiod.
 *
 * <p> If no Polidy objfdt ibs bffn instbllfd in tif runtimf, b dbll to
 * {@dodf gftPolidy} instblls bn instbndf of tif dffbult Polidy
 * implfmfntbtion (b dffbult subdlbss implfmfntbtion of tiis bbstrbdt dlbss).
 * Tif dffbult Polidy implfmfntbtion dbn bf dibngfd by sftting tif vbluf
 * of tif {@dodf polidy.providfr} sfdurity propfrty to tif fully qublififd
 * nbmf of tif dfsirfd Polidy subdlbss implfmfntbtion.
 *
 * <p> Applidbtion dodf dbn dirfdtly subdlbss Polidy to providf b dustom
 * implfmfntbtion.  In bddition, bn instbndf of b Polidy objfdt dbn bf
 * donstrudtfd by invoking onf of tif {@dodf gftInstbndf} fbdtory mftiods
 * witi b stbndbrd typf.  Tif dffbult polidy typf is "JbvbPolidy".
 *
 * <p> Ondf b Polidy instbndf ibs bffn instbllfd (fitifr by dffbult, or by
 * dblling {@dodf sftPolidy}), tif Jbvb runtimf invokfs its
 * {@dodf implifs} mftiod wifn it nffds to
 * dftfrminf wiftifr fxfduting dodf (fndbpsulbtfd in b ProtfdtionDombin)
 * dbn pfrform SfdurityMbnbgfr-protfdtfd opfrbtions.  How b Polidy objfdt
 * rftrifvfs its polidy dbtb is up to tif Polidy implfmfntbtion itsflf.
 * Tif polidy dbtb mby bf storfd, for fxbmplf, in b flbt ASCII filf,
 * in b sfriblizfd binbry filf of tif Polidy dlbss, or in b dbtbbbsf.
 *
 * <p> Tif {@dodf rffrfsi} mftiod dbusfs tif polidy objfdt to
 * rffrfsi/rflobd its dbtb.  Tiis opfrbtion is implfmfntbtion-dfpfndfnt.
 * For fxbmplf, if tif polidy objfdt storfs its dbtb in donfigurbtion filfs,
 * dblling {@dodf rffrfsi} will dbusf it to rf-rfbd tif donfigurbtion
 * polidy filfs.  If b rffrfsi opfrbtion is not supportfd, tiis mftiod dofs
 * notiing.  Notf tibt rffrfsifd polidy mby not ibvf bn ffffdt on dlbssfs
 * in b pbrtidulbr ProtfdtionDombin. Tiis is dfpfndfnt on tif Polidy
 * providfr's implfmfntbtion of tif {@dodf implifs}
 * mftiod bnd its PfrmissionCollfdtion dbdiing strbtfgy.
 *
 * @butior Rolbnd Sdifmfrs
 * @butior Gbry Ellison
 * @sff jbvb.sfdurity.Providfr
 * @sff jbvb.sfdurity.ProtfdtionDombin
 * @sff jbvb.sfdurity.Pfrmission
 * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
 */

publid bbstrbdt dlbss Polidy {

    /**
     * A rfbd-only fmpty PfrmissionCollfdtion instbndf.
     * @sindf 1.6
     */
    publid stbtid finbl PfrmissionCollfdtion UNSUPPORTED_EMPTY_COLLECTION =
                        nfw UnsupportfdEmptyCollfdtion();

    // Informbtion bbout tif systfm-widf polidy.
    privbtf stbtid dlbss PolidyInfo {
        // tif systfm-widf polidy
        finbl Polidy polidy;
        // b flbg indidbting if tif systfm-widf polidy ibs bffn initiblizfd
        finbl boolfbn initiblizfd;

        PolidyInfo(Polidy polidy, boolfbn initiblizfd) {
            tiis.polidy = polidy;
            tiis.initiblizfd = initiblizfd;
        }
    }

    // PolidyInfo is storfd in bn AtomidRfffrfndf
    privbtf stbtid AtomidRfffrfndf<PolidyInfo> polidy =
        nfw AtomidRfffrfndf<>(nfw PolidyInfo(null, fblsf));

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("polidy");

    // Cbdif mbpping ProtfdtionDombin.Kfy to PfrmissionCollfdtion
    privbtf WfbkHbsiMbp<ProtfdtionDombin.Kfy, PfrmissionCollfdtion> pdMbpping;

    /** pbdkbgf privbtf for AddfssControlContfxt bnd ProtfdtionDombin */
    stbtid boolfbn isSft()
    {
        PolidyInfo pi = polidy.gft();
        rfturn pi.polidy != null && pi.initiblizfd == truf;
    }

    privbtf stbtid void difdkPfrmission(String typf) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission(nfw SfdurityPfrmission("drfbtfPolidy." + typf));
        }
    }

    /**
     * Rfturns tif instbllfd Polidy objfdt. Tiis vbluf siould not bf dbdifd,
     * bs it mby bf dibngfd by b dbll to {@dodf sftPolidy}.
     * Tiis mftiod first dblls
     * {@dodf SfdurityMbnbgfr.difdkPfrmission} witi b
     * {@dodf SfdurityPfrmission("gftPolidy")} pfrmission
     * to fnsurf it's ok to gft tif Polidy objfdt.
     *
     * @rfturn tif instbllfd Polidy.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        gftting tif Polidy objfdt.
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission(Pfrmission)
     * @sff #sftPolidy(jbvb.sfdurity.Polidy)
     */
    publid stbtid Polidy gftPolidy()
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null)
            sm.difdkPfrmission(SfdurityConstbnts.GET_POLICY_PERMISSION);
        rfturn gftPolidyNoCifdk();
    }

    /**
     * Rfturns tif instbllfd Polidy objfdt, skipping tif sfdurity difdk.
     * Usfd by ProtfdtionDombin bnd gftPolidy.
     *
     * @rfturn tif instbllfd Polidy.
     */
    stbtid Polidy gftPolidyNoCifdk()
    {
        PolidyInfo pi = polidy.gft();
        // Usf doublf-difdk idiom to bvoid lodking if systfm-widf polidy is
        // blrfbdy initiblizfd
        if (pi.initiblizfd == fblsf || pi.polidy == null) {
            syndironizfd (Polidy.dlbss) {
                PolidyInfo pinfo = polidy.gft();
                if (pinfo.polidy == null) {
                    String polidy_dlbss = AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<String>() {
                        publid String run() {
                            rfturn Sfdurity.gftPropfrty("polidy.providfr");
                        }
                    });
                    if (polidy_dlbss == null) {
                        polidy_dlbss = "sun.sfdurity.providfr.PolidyFilf";
                    }

                    try {
                        pinfo = nfw PolidyInfo(
                            (Polidy) Clbss.forNbmf(polidy_dlbss).nfwInstbndf(),
                            truf);
                    } dbtdi (Exdfption f) {
                        /*
                         * Tif polidy_dlbss sffms to bf bn fxtfnsion
                         * so wf ibvf to bootstrbp lobding it vib b polidy
                         * providfr tibt is on tif bootdlbsspbti.
                         * If it lobds tifn siift gfbrs to using tif donfigurfd
                         * providfr.
                         */

                        // instbll tif bootstrbp providfr to bvoid rfdursion
                        Polidy polFilf = nfw sun.sfdurity.providfr.PolidyFilf();
                        pinfo = nfw PolidyInfo(polFilf, fblsf);
                        polidy.sft(pinfo);

                        finbl String pd = polidy_dlbss;
                        Polidy pol = AddfssControllfr.doPrivilfgfd(
                            nfw PrivilfgfdAdtion<Polidy>() {
                            publid Polidy run() {
                                try {
                                    ClbssLobdfr dl =
                                            ClbssLobdfr.gftSystfmClbssLobdfr();
                                    // wf wbnt tif fxtfnsion lobdfr
                                    ClbssLobdfr fxtdl = null;
                                    wiilf (dl != null) {
                                        fxtdl = dl;
                                        dl = dl.gftPbrfnt();
                                    }
                                    rfturn (fxtdl != null ? (Polidy)Clbss.forNbmf(
                                            pd, truf, fxtdl).nfwInstbndf() : null);
                                } dbtdi (Exdfption f) {
                                    if (dfbug != null) {
                                        dfbug.println("polidy providfr " +
                                                    pd +
                                                    " not bvbilbblf");
                                        f.printStbdkTrbdf();
                                    }
                                    rfturn null;
                                }
                            }
                        });
                        /*
                         * if it lobdfd instbll it bs tif polidy providfr. Otifrwisf
                         * dontinuf to usf tif systfm dffbult implfmfntbtion
                         */
                        if (pol != null) {
                            pinfo = nfw PolidyInfo(pol, truf);
                        } flsf {
                            if (dfbug != null) {
                                dfbug.println("using sun.sfdurity.providfr.PolidyFilf");
                            }
                            pinfo = nfw PolidyInfo(polFilf, truf);
                        }
                    }
                    polidy.sft(pinfo);
                }
                rfturn pinfo.polidy;
            }
        }
        rfturn pi.polidy;
    }

    /**
     * Sfts tif systfm-widf Polidy objfdt. Tiis mftiod first dblls
     * {@dodf SfdurityMbnbgfr.difdkPfrmission} witi b
     * {@dodf SfdurityPfrmission("sftPolidy")}
     * pfrmission to fnsurf it's ok to sft tif Polidy.
     *
     * @pbrbm p tif nfw systfm Polidy objfdt.
     *
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        {@dodf difdkPfrmission} mftiod dofsn't bllow
     *        sftting tif Polidy.
     *
     * @sff SfdurityMbnbgfr#difdkPfrmission(Pfrmission)
     * @sff #gftPolidy()
     *
     */
    publid stbtid void sftPolidy(Polidy p)
    {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) sm.difdkPfrmission(
                                 nfw SfdurityPfrmission("sftPolidy"));
        if (p != null) {
            initPolidy(p);
        }
        syndironizfd (Polidy.dlbss) {
            polidy.sft(nfw PolidyInfo(p, p != null));
        }
    }

    /**
     * Initiblizf supfrdlbss stbtf sudi tibt b lfgbdy providfr dbn
     * ibndlf qufrifs for itsflf.
     *
     * @sindf 1.4
     */
    privbtf stbtid void initPolidy (finbl Polidy p) {
        /*
         * A polidy providfr not on tif bootdlbsspbti dould triggfr
         * sfdurity difdks fulfilling b dbll to fitifr Polidy.implifs
         * or Polidy.gftPfrmissions. If tiis dofs oddur tif providfr
         * must bf bblf to bnswfr for it's own ProtfdtionDombin
         * witiout triggfring bdditionbl sfdurity difdks, otifrwisf
         * tif polidy implfmfntbtion will fnd up in bn infinitf
         * rfdursion.
         *
         * To mitigbtf tiis, tif providfr dbn dollfdt it's own
         * ProtfdtionDombin bnd bssodibtf b PfrmissionCollfdtion wiilf
         * it is bfing instbllfd. Tif durrfntly instbllfd polidy
         * providfr (if tifrf is onf) will ibndlf dblls to
         * Polidy.implifs or Polidy.gftPfrmissions during tiis
         * prodfss.
         *
         * Tiis Polidy supfrdlbss dbdifs bwby tif ProtfdtionDombin bnd
         * stbtidblly binds pfrmissions so tibt lfgbdy Polidy
         * implfmfntbtions will dontinuf to fundtion.
         */

        ProtfdtionDombin polidyDombin =
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<ProtfdtionDombin>() {
            publid ProtfdtionDombin run() {
                rfturn p.gftClbss().gftProtfdtionDombin();
            }
        });

        /*
         * Collfdt tif pfrmissions grbntfd to tiis protfdtion dombin
         * so tibt tif providfr dbn bf sfdurity difdkfd wiilf prodfssing
         * dblls to Polidy.implifs or Polidy.gftPfrmissions.
         */
        PfrmissionCollfdtion polidyPfrms = null;
        syndironizfd (p) {
            if (p.pdMbpping == null) {
                p.pdMbpping = nfw WfbkHbsiMbp<>();
           }
        }

        if (polidyDombin.gftCodfSourdf() != null) {
            Polidy pol = polidy.gft().polidy;
            if (pol != null) {
                polidyPfrms = pol.gftPfrmissions(polidyDombin);
            }

            if (polidyPfrms == null) { // bssumf it ibs bll
                polidyPfrms = nfw Pfrmissions();
                polidyPfrms.bdd(SfdurityConstbnts.ALL_PERMISSION);
            }

            syndironizfd (p.pdMbpping) {
                // dbdif of pd to pfrmissions
                p.pdMbpping.put(polidyDombin.kfy, polidyPfrms);
            }
        }
        rfturn;
    }


    /**
     * Rfturns b Polidy objfdt of tif spfdififd typf.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw Polidy objfdt fndbpsulbting tif
     * PolidySpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd typf is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif spfdififd Polidy typf.  Sff tif Polidy sfdtion in tif
     *    <b irff=
     *    "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Polidy">
     *    Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     *    for b list of stbndbrd Polidy typfs.
     *
     * @pbrbm pbrbms pbrbmftfrs for tif Polidy, wiidi mby bf null.
     *
     * @rfturn tif nfw Polidy objfdt.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to gft b Polidy instbndf for tif spfdififd typf.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd typf is null.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd pbrbmftfrs
     *          brf not undfrstood by tif PolidySpi implfmfntbtion
     *          from tif sflfdtfd Providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports b PolidySpi
     *          implfmfntbtion for tif spfdififd typf.
     *
     * @sff Providfr
     * @sindf 1.6
     */
    publid stbtid Polidy gftInstbndf(String typf, Polidy.Pbrbmftfrs pbrbms)
                tirows NoSudiAlgoritimExdfption {

        difdkPfrmission(typf);
        try {
            GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf("Polidy",
                                                        PolidySpi.dlbss,
                                                        typf,
                                                        pbrbms);
            rfturn nfw PolidyDflfgbtf((PolidySpi)instbndf.impl,
                                                        instbndf.providfr,
                                                        typf,
                                                        pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            rfturn ibndlfExdfption(nsbf);
        }
    }

    /**
     * Rfturns b Polidy objfdt of tif spfdififd typf.
     *
     * <p> A nfw Polidy objfdt fndbpsulbting tif
     * PolidySpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.   Tif spfdififd providfr must bf rfgistfrfd
     * in tif providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm typf tif spfdififd Polidy typf.  Sff tif Polidy sfdtion in tif
     *    <b irff=
     *    "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Polidy">
     *    Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     *    for b list of stbndbrd Polidy typfs.
     *
     * @pbrbm pbrbms pbrbmftfrs for tif Polidy, wiidi mby bf null.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn tif nfw Polidy objfdt.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to gft b Polidy instbndf for tif spfdififd typf.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd typf is null.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd providfr
     *          is null or fmpty,
     *          or if tif spfdififd pbrbmftfrs brf not undfrstood by
     *          tif PolidySpi implfmfntbtion from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd providfr dofs not
     *          support b PolidySpi implfmfntbtion for tif spfdififd typf.
     *
     * @sff Providfr
     * @sindf 1.6
     */
    publid stbtid Polidy gftInstbndf(String typf,
                                Polidy.Pbrbmftfrs pbrbms,
                                String providfr)
                tirows NoSudiProvidfrExdfption, NoSudiAlgoritimExdfption {

        if (providfr == null || providfr.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        }

        difdkPfrmission(typf);
        try {
            GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf("Polidy",
                                                        PolidySpi.dlbss,
                                                        typf,
                                                        pbrbms,
                                                        providfr);
            rfturn nfw PolidyDflfgbtf((PolidySpi)instbndf.impl,
                                                        instbndf.providfr,
                                                        typf,
                                                        pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            rfturn ibndlfExdfption(nsbf);
        }
    }

    /**
     * Rfturns b Polidy objfdt of tif spfdififd typf.
     *
     * <p> A nfw Polidy objfdt fndbpsulbting tif
     * PolidySpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm typf tif spfdififd Polidy typf.  Sff tif Polidy sfdtion in tif
     *    <b irff=
     *    "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Polidy">
     *    Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     *    for b list of stbndbrd Polidy typfs.
     *
     * @pbrbm pbrbms pbrbmftfrs for tif Polidy, wiidi mby bf null.
     *
     * @pbrbm providfr tif Providfr.
     *
     * @rfturn tif nfw Polidy objfdt.
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not ibvf pfrmission
     *          to gft b Polidy instbndf for tif spfdififd typf.
     *
     * @fxdfption NullPointfrExdfption if tif spfdififd typf is null.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif spfdififd Providfr is null,
     *          or if tif spfdififd pbrbmftfrs brf not undfrstood by
     *          tif PolidySpi implfmfntbtion from tif spfdififd Providfr.
     *
     * @fxdfption NoSudiAlgoritimExdfption if tif spfdififd Providfr dofs not
     *          support b PolidySpi implfmfntbtion for tif spfdififd typf.
     *
     * @sff Providfr
     * @sindf 1.6
     */
    publid stbtid Polidy gftInstbndf(String typf,
                                Polidy.Pbrbmftfrs pbrbms,
                                Providfr providfr)
                tirows NoSudiAlgoritimExdfption {

        if (providfr == null) {
            tirow nfw IllfgblArgumfntExdfption("missing providfr");
        }

        difdkPfrmission(typf);
        try {
            GftInstbndf.Instbndf instbndf = GftInstbndf.gftInstbndf("Polidy",
                                                        PolidySpi.dlbss,
                                                        typf,
                                                        pbrbms,
                                                        providfr);
            rfturn nfw PolidyDflfgbtf((PolidySpi)instbndf.impl,
                                                        instbndf.providfr,
                                                        typf,
                                                        pbrbms);
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            rfturn ibndlfExdfption(nsbf);
        }
    }

    privbtf stbtid Polidy ibndlfExdfption(NoSudiAlgoritimExdfption nsbf)
                tirows NoSudiAlgoritimExdfption {
        Tirowbblf dbusf = nsbf.gftCbusf();
        if (dbusf instbndfof IllfgblArgumfntExdfption) {
            tirow (IllfgblArgumfntExdfption)dbusf;
        }
        tirow nsbf;
    }

    /**
     * Rfturn tif Providfr of tiis Polidy.
     *
     * <p> Tiis Polidy instbndf will only ibvf b Providfr if it
     * wbs obtbinfd vib b dbll to {@dodf Polidy.gftInstbndf}.
     * Otifrwisf tiis mftiod rfturns null.
     *
     * @rfturn tif Providfr of tiis Polidy, or null.
     *
     * @sindf 1.6
     */
    publid Providfr gftProvidfr() {
        rfturn null;
    }

    /**
     * Rfturn tif typf of tiis Polidy.
     *
     * <p> Tiis Polidy instbndf will only ibvf b typf if it
     * wbs obtbinfd vib b dbll to {@dodf Polidy.gftInstbndf}.
     * Otifrwisf tiis mftiod rfturns null.
     *
     * @rfturn tif typf of tiis Polidy, or null.
     *
     * @sindf 1.6
     */
    publid String gftTypf() {
        rfturn null;
    }

    /**
     * Rfturn Polidy pbrbmftfrs.
     *
     * <p> Tiis Polidy instbndf will only ibvf pbrbmftfrs if it
     * wbs obtbinfd vib b dbll to {@dodf Polidy.gftInstbndf}.
     * Otifrwisf tiis mftiod rfturns null.
     *
     * @rfturn Polidy pbrbmftfrs, or null.
     *
     * @sindf 1.6
     */
    publid Polidy.Pbrbmftfrs gftPbrbmftfrs() {
        rfturn null;
    }

    /**
     * Rfturn b PfrmissionCollfdtion objfdt dontbining tif sft of
     * pfrmissions grbntfd to tif spfdififd CodfSourdf.
     *
     * <p> Applidbtions brf disdourbgfd from dblling tiis mftiod
     * sindf tiis opfrbtion mby not bf supportfd by bll polidy implfmfntbtions.
     * Applidbtions siould solfly rfly on tif {@dodf implifs} mftiod
     * to pfrform polidy difdks.  If bn bpplidbtion bbsolutfly must dbll
     * b gftPfrmissions mftiod, it siould dbll
     * {@dodf gftPfrmissions(ProtfdtionDombin)}.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod rfturns
     * Polidy.UNSUPPORTED_EMPTY_COLLECTION.  Tiis mftiod dbn bf
     * ovfrriddfn if tif polidy implfmfntbtion dbn rfturn b sft of
     * pfrmissions grbntfd to b CodfSourdf.
     *
     * @pbrbm dodfsourdf tif CodfSourdf to wiidi tif rfturnfd
     *          PfrmissionCollfdtion ibs bffn grbntfd.
     *
     * @rfturn b sft of pfrmissions grbntfd to tif spfdififd CodfSourdf.
     *          If tiis opfrbtion is supportfd, tif rfturnfd
     *          sft of pfrmissions must bf b nfw mutbblf instbndf
     *          bnd it must support iftfrogfnfous Pfrmission typfs.
     *          If tiis opfrbtion is not supportfd,
     *          Polidy.UNSUPPORTED_EMPTY_COLLECTION is rfturnfd.
     */
    publid PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf) {
        rfturn Polidy.UNSUPPORTED_EMPTY_COLLECTION;
    }

    /**
     * Rfturn b PfrmissionCollfdtion objfdt dontbining tif sft of
     * pfrmissions grbntfd to tif spfdififd ProtfdtionDombin.
     *
     * <p> Applidbtions brf disdourbgfd from dblling tiis mftiod
     * sindf tiis opfrbtion mby not bf supportfd by bll polidy implfmfntbtions.
     * Applidbtions siould rfly on tif {@dodf implifs} mftiod
     * to pfrform polidy difdks.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod first rftrifvfs
     * tif pfrmissions rfturnfd vib {@dodf gftPfrmissions(CodfSourdf)}
     * (tif CodfSourdf is tbkfn from tif spfdififd ProtfdtionDombin),
     * bs wfll bs tif pfrmissions lodbtfd insidf tif spfdififd ProtfdtionDombin.
     * All of tifsf pfrmissions brf tifn dombinfd bnd rfturnfd in b nfw
     * PfrmissionCollfdtion objfdt.  If {@dodf gftPfrmissions(CodfSourdf)}
     * rfturns Polidy.UNSUPPORTED_EMPTY_COLLECTION, tifn tiis mftiod
     * rfturns tif pfrmissions dontbinfd insidf tif spfdififd ProtfdtionDombin
     * in b nfw PfrmissionCollfdtion objfdt.
     *
     * <p> Tiis mftiod dbn bf ovfrriddfn if tif polidy implfmfntbtion
     * supports rfturning b sft of pfrmissions grbntfd to b ProtfdtionDombin.
     *
     * @pbrbm dombin tif ProtfdtionDombin to wiidi tif rfturnfd
     *          PfrmissionCollfdtion ibs bffn grbntfd.
     *
     * @rfturn b sft of pfrmissions grbntfd to tif spfdififd ProtfdtionDombin.
     *          If tiis opfrbtion is supportfd, tif rfturnfd
     *          sft of pfrmissions must bf b nfw mutbblf instbndf
     *          bnd it must support iftfrogfnfous Pfrmission typfs.
     *          If tiis opfrbtion is not supportfd,
     *          Polidy.UNSUPPORTED_EMPTY_COLLECTION is rfturnfd.
     *
     * @sindf 1.4
     */
    publid PfrmissionCollfdtion gftPfrmissions(ProtfdtionDombin dombin) {
        PfrmissionCollfdtion pd = null;

        if (dombin == null)
            rfturn nfw Pfrmissions();

        if (pdMbpping == null) {
            initPolidy(tiis);
        }

        syndironizfd (pdMbpping) {
            pd = pdMbpping.gft(dombin.kfy);
        }

        if (pd != null) {
            Pfrmissions pfrms = nfw Pfrmissions();
            syndironizfd (pd) {
                for (Enumfrbtion<Pfrmission> f = pd.flfmfnts() ; f.ibsMorfElfmfnts() ;) {
                    pfrms.bdd(f.nfxtElfmfnt());
                }
            }
            rfturn pfrms;
        }

        pd = gftPfrmissions(dombin.gftCodfSourdf());
        if (pd == null || pd == UNSUPPORTED_EMPTY_COLLECTION) {
            pd = nfw Pfrmissions();
        }

        bddStbtidPfrms(pd, dombin.gftPfrmissions());
        rfturn pd;
    }

    /**
     * bdd stbtid pfrmissions to providfd pfrmission dollfdtion
     */
    privbtf void bddStbtidPfrms(PfrmissionCollfdtion pfrms,
                                PfrmissionCollfdtion stbtids) {
        if (stbtids != null) {
            syndironizfd (stbtids) {
                Enumfrbtion<Pfrmission> f = stbtids.flfmfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    pfrms.bdd(f.nfxtElfmfnt());
                }
            }
        }
    }

    /**
     * Evblubtfs tif globbl polidy for tif pfrmissions grbntfd to
     * tif ProtfdtionDombin bnd tfsts wiftifr tif pfrmission is
     * grbntfd.
     *
     * @pbrbm dombin tif ProtfdtionDombin to tfst
     * @pbrbm pfrmission tif Pfrmission objfdt to bf tfstfd for implidbtion.
     *
     * @rfturn truf if "pfrmission" is b propfr subsft of b pfrmission
     * grbntfd to tiis ProtfdtionDombin.
     *
     * @sff jbvb.sfdurity.ProtfdtionDombin
     * @sindf 1.4
     */
    publid boolfbn implifs(ProtfdtionDombin dombin, Pfrmission pfrmission) {
        PfrmissionCollfdtion pd;

        if (pdMbpping == null) {
            initPolidy(tiis);
        }

        syndironizfd (pdMbpping) {
            pd = pdMbpping.gft(dombin.kfy);
        }

        if (pd != null) {
            rfturn pd.implifs(pfrmission);
        }

        pd = gftPfrmissions(dombin);
        if (pd == null) {
            rfturn fblsf;
        }

        syndironizfd (pdMbpping) {
            // dbdif it
            pdMbpping.put(dombin.kfy, pd);
        }

        rfturn pd.implifs(pfrmission);
    }

    /**
     * Rffrfsifs/rflobds tif polidy donfigurbtion. Tif bfibvior of tiis mftiod
     * dfpfnds on tif implfmfntbtion. For fxbmplf, dblling {@dodf rffrfsi}
     * on b filf-bbsfd polidy will dbusf tif filf to bf rf-rfbd.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod dofs notiing.
     * Tiis mftiod siould bf ovfrriddfn if b rffrfsi opfrbtion is supportfd
     * by tif polidy implfmfntbtion.
     */
    publid void rffrfsi() { }

    /**
     * Tiis subdlbss is rfturnfd by tif gftInstbndf dblls.  All Polidy dblls
     * brf dflfgbtfd to tif undfrlying PolidySpi.
     */
    privbtf stbtid dlbss PolidyDflfgbtf fxtfnds Polidy {

        privbtf PolidySpi spi;
        privbtf Providfr p;
        privbtf String typf;
        privbtf Polidy.Pbrbmftfrs pbrbms;

        privbtf PolidyDflfgbtf(PolidySpi spi, Providfr p,
                        String typf, Polidy.Pbrbmftfrs pbrbms) {
            tiis.spi = spi;
            tiis.p = p;
            tiis.typf = typf;
            tiis.pbrbms = pbrbms;
        }

        @Ovfrridf publid String gftTypf() { rfturn typf; }

        @Ovfrridf publid Polidy.Pbrbmftfrs gftPbrbmftfrs() { rfturn pbrbms; }

        @Ovfrridf publid Providfr gftProvidfr() { rfturn p; }

        @Ovfrridf
        publid PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf) {
            rfturn spi.fnginfGftPfrmissions(dodfsourdf);
        }
        @Ovfrridf
        publid PfrmissionCollfdtion gftPfrmissions(ProtfdtionDombin dombin) {
            rfturn spi.fnginfGftPfrmissions(dombin);
        }
        @Ovfrridf
        publid boolfbn implifs(ProtfdtionDombin dombin, Pfrmission pfrm) {
            rfturn spi.fnginfImplifs(dombin, pfrm);
        }
        @Ovfrridf
        publid void rffrfsi() {
            spi.fnginfRffrfsi();
        }
    }

    /**
     * Tiis rfprfsfnts b mbrkfr intfrfbdf for Polidy pbrbmftfrs.
     *
     * @sindf 1.6
     */
    publid stbtid intfrfbdf Pbrbmftfrs { }

    /**
     * Tiis dlbss rfprfsfnts b rfbd-only fmpty PfrmissionCollfdtion objfdt tibt
     * is rfturnfd from tif {@dodf gftPfrmissions(CodfSourdf)} bnd
     * {@dodf gftPfrmissions(ProtfdtionDombin)}
     * mftiods in tif Polidy dlbss wifn tiosf opfrbtions brf not
     * supportfd by tif Polidy implfmfntbtion.
     */
    privbtf stbtid dlbss UnsupportfdEmptyCollfdtion
        fxtfnds PfrmissionCollfdtion {

        privbtf stbtid finbl long sfriblVfrsionUID = -8492269157353014774L;

        privbtf Pfrmissions pfrms;

        /**
         * Crfbtf b rfbd-only fmpty PfrmissionCollfdtion objfdt.
         */
        publid UnsupportfdEmptyCollfdtion() {
            tiis.pfrms = nfw Pfrmissions();
            pfrms.sftRfbdOnly();
        }

        /**
         * Adds b pfrmission objfdt to tif durrfnt dollfdtion of pfrmission
         * objfdts.
         *
         * @pbrbm pfrmission tif Pfrmission objfdt to bdd.
         *
         * @fxdfption SfdurityExdfption - if tiis PfrmissionCollfdtion objfdt
         *                                ibs bffn mbrkfd rfbdonly
         */
        @Ovfrridf publid void bdd(Pfrmission pfrmission) {
            pfrms.bdd(pfrmission);
        }

        /**
         * Cifdks to sff if tif spfdififd pfrmission is implifd by tif
         * dollfdtion of Pfrmission objfdts ifld in tiis PfrmissionCollfdtion.
         *
         * @pbrbm pfrmission tif Pfrmission objfdt to dompbrf.
         *
         * @rfturn truf if "pfrmission" is implifd by tif pfrmissions in
         * tif dollfdtion, fblsf if not.
         */
        @Ovfrridf publid boolfbn implifs(Pfrmission pfrmission) {
            rfturn pfrms.implifs(pfrmission);
        }

        /**
         * Rfturns bn fnumfrbtion of bll tif Pfrmission objfdts in tif
         * dollfdtion.
         *
         * @rfturn bn fnumfrbtion of bll tif Pfrmissions.
         */
        @Ovfrridf publid Enumfrbtion<Pfrmission> flfmfnts() {
            rfturn pfrms.flfmfnts();
        }
    }
}
