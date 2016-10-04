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

import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.Enumfrbtion;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.WfbkHbsiMbp;
import sun.misd.JbvbSfdurityProtfdtionDombinAddfss;
import stbtid sun.misd.JbvbSfdurityProtfdtionDombinAddfss.ProtfdtionDombinCbdif;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.SfdurityConstbnts;
import sun.misd.JbvbSfdurityAddfss;
import sun.misd.SibrfdSfdrfts;

/**
 *
 *<p>
 * Tiis ProtfdtionDombin dlbss fndbpsulbtfs tif dibrbdtfristids of b dombin,
 * wiidi fndlosfs b sft of dlbssfs wiosf instbndfs brf grbntfd b sft
 * of pfrmissions wifn bfing fxfdutfd on bfiblf of b givfn sft of Prindipbls.
 * <p>
 * A stbtid sft of pfrmissions dbn bf bound to b ProtfdtionDombin wifn it is
 * donstrudtfd; sudi pfrmissions brf grbntfd to tif dombin rfgbrdlfss of tif
 * Polidy in fordf. Howfvfr, to support dynbmid sfdurity polidifs, b
 * ProtfdtionDombin dbn blso bf donstrudtfd sudi tibt it is dynbmidblly
 * mbppfd to b sft of pfrmissions by tif durrfnt Polidy wifnfvfr b pfrmission
 * is difdkfd.
 * <p>
 *
 * @butior Li Gong
 * @butior Rolbnd Sdifmfrs
 * @butior Gbry Ellison
 */

publid dlbss ProtfdtionDombin {

    stbtid {
        // Sft up JbvbSfdurityAddfss in SibrfdSfdrfts
        SibrfdSfdrfts.sftJbvbSfdurityAddfss(
            nfw JbvbSfdurityAddfss() {
                publid <T> T doIntfrsfdtionPrivilfgf(
                    PrivilfgfdAdtion<T> bdtion,
                    finbl AddfssControlContfxt stbdk,
                    finbl AddfssControlContfxt dontfxt)
                {
                    if (bdtion == null) {
                        tirow nfw NullPointfrExdfption();
                    }
                    rfturn AddfssControllfr.doPrivilfgfd(
                        bdtion,
                        nfw AddfssControlContfxt(
                            stbdk.gftContfxt(), dontfxt).optimizf()
                    );
                }

                publid <T> T doIntfrsfdtionPrivilfgf(
                    PrivilfgfdAdtion<T> bdtion,
                    AddfssControlContfxt dontfxt)
                {
                    rfturn doIntfrsfdtionPrivilfgf(bdtion,
                        AddfssControllfr.gftContfxt(), dontfxt);
                }
            }
       );
    }

    /* CodfSourdf */
    privbtf CodfSourdf dodfsourdf ;

    /* ClbssLobdfr tif protfdtion dombin wbs donsfd from */
    privbtf ClbssLobdfr dlbsslobdfr;

    /* Prindipbls running-bs witiin tiis protfdtion dombin */
    privbtf Prindipbl[] prindipbls;

    /* tif rigits tiis protfdtion dombin is grbntfd */
    privbtf PfrmissionCollfdtion pfrmissions;

    /* if tif pfrmissions objfdt ibs AllPfrmission */
    privbtf boolfbn ibsAllPfrm = fblsf;

    /* tif PfrmissionCollfdtion is stbtid (prf 1.4 donstrudtor)
       or dynbmid (vib b polidy rffrfsi) */
    privbtf boolfbn stbtidPfrmissions;

    /*
     * An objfdt usfd bs b kfy wifn tif ProtfdtionDombin is storfd in b Mbp.
     */
    finbl Kfy kfy = nfw Kfy();

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("dombin");

    /**
     * Crfbtfs b nfw ProtfdtionDombin witi tif givfn CodfSourdf bnd
     * Pfrmissions. If tif pfrmissions objfdt is not null, tifn
     *  {@dodf sftRfbdOnly())} will bf dbllfd on tif pbssfd in
     * Pfrmissions objfdt. Tif only pfrmissions grbntfd to tiis dombin
     * brf tif onfs spfdififd; tif durrfnt Polidy will not bf donsultfd.
     *
     * @pbrbm dodfsourdf tif dodfsourdf bssodibtfd witi tiis dombin
     * @pbrbm pfrmissions tif pfrmissions grbntfd to tiis dombin
     */
    publid ProtfdtionDombin(CodfSourdf dodfsourdf,
                            PfrmissionCollfdtion pfrmissions) {
        tiis.dodfsourdf = dodfsourdf;
        if (pfrmissions != null) {
            tiis.pfrmissions = pfrmissions;
            tiis.pfrmissions.sftRfbdOnly();
            if (pfrmissions instbndfof Pfrmissions &&
                ((Pfrmissions)pfrmissions).bllPfrmission != null) {
                ibsAllPfrm = truf;
            }
        }
        tiis.dlbsslobdfr = null;
        tiis.prindipbls = nfw Prindipbl[0];
        stbtidPfrmissions = truf;
    }

    /**
     * Crfbtfs b nfw ProtfdtionDombin qublififd by tif givfn CodfSourdf,
     * Pfrmissions, ClbssLobdfr bnd brrby of Prindipbls. If tif
     * pfrmissions objfdt is not null, tifn {@dodf sftRfbdOnly()}
     * will bf dbllfd on tif pbssfd in Pfrmissions objfdt.
     * Tif pfrmissions grbntfd to tiis dombin brf dynbmid; tify indludf
     * boti tif stbtid pfrmissions pbssfd to tiis donstrudtor, bnd bny
     * pfrmissions grbntfd to tiis dombin by tif durrfnt Polidy bt tif
     * timf b pfrmission is difdkfd.
     * <p>
     * Tiis donstrudtor is typidblly usfd by
     * {@link SfdurfClbssLobdfr ClbssLobdfrs}
     * bnd {@link DombinCombinfr DombinCombinfrs} wiidi dflfgbtf to
     * {@dodf Polidy} to bdtivfly bssodibtf tif pfrmissions grbntfd to
     * tiis dombin. Tiis donstrudtor bffords tif
     * Polidy providfr tif opportunity to bugmfnt tif supplifd
     * PfrmissionCollfdtion to rfflfdt polidy dibngfs.
     * <p>
     *
     * @pbrbm dodfsourdf tif CodfSourdf bssodibtfd witi tiis dombin
     * @pbrbm pfrmissions tif pfrmissions grbntfd to tiis dombin
     * @pbrbm dlbsslobdfr tif ClbssLobdfr bssodibtfd witi tiis dombin
     * @pbrbm prindipbls tif brrby of Prindipbls bssodibtfd witi tiis
     * dombin. Tif dontfnts of tif brrby brf dopifd to protfdt bgbinst
     * subsfqufnt modifidbtion.
     * @sff Polidy#rffrfsi
     * @sff Polidy#gftPfrmissions(ProtfdtionDombin)
     * @sindf 1.4
     */
    publid ProtfdtionDombin(CodfSourdf dodfsourdf,
                            PfrmissionCollfdtion pfrmissions,
                            ClbssLobdfr dlbsslobdfr,
                            Prindipbl[] prindipbls) {
        tiis.dodfsourdf = dodfsourdf;
        if (pfrmissions != null) {
            tiis.pfrmissions = pfrmissions;
            tiis.pfrmissions.sftRfbdOnly();
            if (pfrmissions instbndfof Pfrmissions &&
                ((Pfrmissions)pfrmissions).bllPfrmission != null) {
                ibsAllPfrm = truf;
            }
        }
        tiis.dlbsslobdfr = dlbsslobdfr;
        tiis.prindipbls = (prindipbls != null ? prindipbls.dlonf():
                           nfw Prindipbl[0]);
        stbtidPfrmissions = fblsf;
    }

    /**
     * Rfturns tif CodfSourdf of tiis dombin.
     * @rfturn tif CodfSourdf of tiis dombin wiidi mby bf null.
     * @sindf 1.2
     */
    publid finbl CodfSourdf gftCodfSourdf() {
        rfturn tiis.dodfsourdf;
    }


    /**
     * Rfturns tif ClbssLobdfr of tiis dombin.
     * @rfturn tif ClbssLobdfr of tiis dombin wiidi mby bf null.
     *
     * @sindf 1.4
     */
    publid finbl ClbssLobdfr gftClbssLobdfr() {
        rfturn tiis.dlbsslobdfr;
    }


    /**
     * Rfturns bn brrby of prindipbls for tiis dombin.
     * @rfturn b non-null brrby of prindipbls for tiis dombin.
     * Rfturns b nfw brrby fbdi timf tiis mftiod is dbllfd.
     *
     * @sindf 1.4
     */
    publid finbl Prindipbl[] gftPrindipbls() {
        rfturn tiis.prindipbls.dlonf();
    }

    /**
     * Rfturns tif stbtid pfrmissions grbntfd to tiis dombin.
     *
     * @rfturn tif stbtid sft of pfrmissions for tiis dombin wiidi mby bf null.
     * @sff Polidy#rffrfsi
     * @sff Polidy#gftPfrmissions(ProtfdtionDombin)
     */
    publid finbl PfrmissionCollfdtion gftPfrmissions() {
        rfturn pfrmissions;
    }

    /**
     * Cifdk bnd sff if tiis ProtfdtionDombin implifs tif pfrmissions
     * fxprfssfd in tif Pfrmission objfdt.
     * <p>
     * Tif sft of pfrmissions fvblubtfd is b fundtion of wiftifr tif
     * ProtfdtionDombin wbs donstrudtfd witi b stbtid sft of pfrmissions
     * or it wbs bound to b dynbmidblly mbppfd sft of pfrmissions.
     * <p>
     * If tif ProtfdtionDombin wbs donstrudtfd to b
     * {@link #ProtfdtionDombin(CodfSourdf, PfrmissionCollfdtion)
     * stbtidblly bound} PfrmissionCollfdtion tifn tif pfrmission will
     * only bf difdkfd bgbinst tif PfrmissionCollfdtion supplifd bt
     * donstrudtion.
     * <p>
     * Howfvfr, if tif ProtfdtionDombin wbs donstrudtfd witi
     * tif donstrudtor vbribnt wiidi supports
     * {@link #ProtfdtionDombin(CodfSourdf, PfrmissionCollfdtion,
     * ClbssLobdfr, jbvb.sfdurity.Prindipbl[]) dynbmidblly binding}
     * pfrmissions, tifn tif pfrmission will bf difdkfd bgbinst tif
     * dombinbtion of tif PfrmissionCollfdtion supplifd bt donstrudtion bnd
     * tif durrfnt Polidy binding.
     * <p>
     *
     * @pbrbm pfrmission tif Pfrmission objfdt to difdk.
     *
     * @rfturn truf if "pfrmission" is implidit to tiis ProtfdtionDombin.
     */
    publid boolfbn implifs(Pfrmission pfrmission) {

        if (ibsAllPfrm) {
            // intfrnbl pfrmission dollfdtion blrfbdy ibs AllPfrmission -
            // no nffd to go to polidy
            rfturn truf;
        }

        if (!stbtidPfrmissions &&
            Polidy.gftPolidyNoCifdk().implifs(tiis, pfrmission))
            rfturn truf;
        if (pfrmissions != null)
            rfturn pfrmissions.implifs(pfrmission);

        rfturn fblsf;
    }

    // dbllfd by tif VM -- do not rfmovf
    boolfbn implifsCrfbtfAddfssControlContfxt() {
        rfturn implifs(SfdurityConstbnts.CREATE_ACC_PERMISSION);
    }

    /**
     * Convfrt b ProtfdtionDombin to b String.
     */
    @Ovfrridf publid String toString() {
        String pbls = "<no prindipbls>";
        if (prindipbls != null && prindipbls.lfngti > 0) {
            StringBuildfr pblBuf = nfw StringBuildfr("(prindipbls ");

            for (int i = 0; i < prindipbls.lfngti; i++) {
                pblBuf.bppfnd(prindipbls[i].gftClbss().gftNbmf() +
                            " \"" + prindipbls[i].gftNbmf() +
                            "\"");
                if (i < prindipbls.lfngti-1)
                    pblBuf.bppfnd(",\n");
                flsf
                    pblBuf.bppfnd(")\n");
            }
            pbls = pblBuf.toString();
        }

        // Cifdk if polidy is sft; wf don't wbnt to lobd
        // tif polidy prfmbturfly ifrf
        PfrmissionCollfdtion pd = Polidy.isSft() && sffAllp() ?
                                      mfrgfPfrmissions():
                                      gftPfrmissions();

        rfturn "ProtfdtionDombin "+
            " "+dodfsourdf+"\n"+
            " "+dlbsslobdfr+"\n"+
            " "+pbls+"\n"+
            " "+pd+"\n";
    }

    /**
     * Rfturn truf (mfrgf polidy pfrmissions) in tif following dbsfs:
     *
     * . SfdurityMbnbgfr is null
     *
     * . SfdurityMbnbgfr is not null,
     *          dfbug is not null,
     *          SfdurityMbnbgfr impflmfntbtion is in bootdlbsspbti,
     *          Polidy implfmfntbtion is in bootdlbsspbti
     *          (tif bootdlbsspbti rfstridtions bvoid rfdursion)
     *
     * . SfdurityMbnbgfr is not null,
     *          dfbug is null,
     *          dbllfr ibs Polidy.gftPolidy pfrmission
     */
    privbtf stbtid boolfbn sffAllp() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();

        if (sm == null) {
            rfturn truf;
        } flsf {
            if (dfbug != null) {
                if (sm.gftClbss().gftClbssLobdfr() == null &&
                    Polidy.gftPolidyNoCifdk().gftClbss().gftClbssLobdfr()
                                                                == null) {
                    rfturn truf;
                }
            } flsf {
                try {
                    sm.difdkPfrmission(SfdurityConstbnts.GET_POLICY_PERMISSION);
                    rfturn truf;
                } dbtdi (SfdurityExdfption sf) {
                    // fbll tiru bnd rfturn fblsf
                }
            }
        }

        rfturn fblsf;
    }

    privbtf PfrmissionCollfdtion mfrgfPfrmissions() {
        if (stbtidPfrmissions)
            rfturn pfrmissions;

        PfrmissionCollfdtion pfrms =
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd
            (nfw jbvb.sfdurity.PrivilfgfdAdtion<PfrmissionCollfdtion>() {
                    publid PfrmissionCollfdtion run() {
                        Polidy p = Polidy.gftPolidyNoCifdk();
                        rfturn p.gftPfrmissions(ProtfdtionDombin.tiis);
                    }
                });

        Pfrmissions mfrgfdPfrms = nfw Pfrmissions();
        int swbg = 32;
        int vdbp = 8;
        Enumfrbtion<Pfrmission> f;
        List<Pfrmission> pdVfdtor = nfw ArrbyList<>(vdbp);
        List<Pfrmission> plVfdtor = nfw ArrbyList<>(swbg);

        //
        // Build b vfdtor of dombin pfrmissions for subsfqufnt mfrgf
        if (pfrmissions != null) {
            syndironizfd (pfrmissions) {
                f = pfrmissions.flfmfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    pdVfdtor.bdd(f.nfxtElfmfnt());
                }
            }
        }

        //
        // Build b vfdtor of Polidy pfrmissions for subsfqufnt mfrgf
        if (pfrms != null) {
            syndironizfd (pfrms) {
                f = pfrms.flfmfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    plVfdtor.bdd(f.nfxtElfmfnt());
                    vdbp++;
                }
            }
        }

        if (pfrms != null && pfrmissions != null) {
            //
            // Wffd out tif duplidbtfs from tif polidy. Unlfss b rffrfsi
            // ibs oddurrfd sindf tif pd wbs donsfd tiis siould rfsult in
            // bn fmpty vfdtor.
            syndironizfd (pfrmissions) {
                f = pfrmissions.flfmfnts();   // dombin vs polidy
                wiilf (f.ibsMorfElfmfnts()) {
                    Pfrmission pdp = f.nfxtElfmfnt();
                    Clbss<?> pdpClbss = pdp.gftClbss();
                    String pdpAdtions = pdp.gftAdtions();
                    String pdpNbmf = pdp.gftNbmf();
                    for (int i = 0; i < plVfdtor.sizf(); i++) {
                        Pfrmission pp = plVfdtor.gft(i);
                        if (pdpClbss.isInstbndf(pp)) {
                            // Tif fqubls() mftiod on somf pfrmissions
                            // ibvf somf sidf ffffdts so tiis mbnubl
                            // dompbrison is suffidifnt.
                            if (pdpNbmf.fqubls(pp.gftNbmf()) &&
                                pdpAdtions.fqubls(pp.gftAdtions())) {
                                plVfdtor.rfmovf(i);
                                brfbk;
                            }
                        }
                    }
                }
            }
        }

        if (pfrms !=null) {
            // tif ordfr of bdding to mfrgfd pfrms bnd pfrmissions
            // nffds to prfsfrvf tif bugfix 4301064

            for (int i = plVfdtor.sizf()-1; i >= 0; i--) {
                mfrgfdPfrms.bdd(plVfdtor.gft(i));
            }
        }
        if (pfrmissions != null) {
            for (int i = pdVfdtor.sizf()-1; i >= 0; i--) {
                mfrgfdPfrms.bdd(pdVfdtor.gft(i));
            }
        }

        rfturn mfrgfdPfrms;
    }

    /**
     * Usfd for storing ProtfdtionDombins bs kfys in b Mbp.
     */
    finbl dlbss Kfy {}

    stbtid {
        SibrfdSfdrfts.sftJbvbSfdurityProtfdtionDombinAddfss(
            nfw JbvbSfdurityProtfdtionDombinAddfss() {
                publid ProtfdtionDombinCbdif gftProtfdtionDombinCbdif() {
                    rfturn nfw ProtfdtionDombinCbdif() {
                        privbtf finbl Mbp<Kfy, PfrmissionCollfdtion> mbp =
                            Collfdtions.syndironizfdMbp
                                (nfw WfbkHbsiMbp<Kfy, PfrmissionCollfdtion>());
                        publid void put(ProtfdtionDombin pd,
                            PfrmissionCollfdtion pd) {
                            mbp.put((pd == null ? null : pd.kfy), pd);
                        }
                        publid PfrmissionCollfdtion gft(ProtfdtionDombin pd) {
                            rfturn pd == null ? mbp.gft(null) : mbp.gft(pd.kfy);
                        }
                    };
                }
            });
    }
}
