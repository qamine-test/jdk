/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sdript;
import jbvb.util.*;
import jbvb.sfdurity.*;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.SfrvidfConfigurbtionError;

/**
 * Tif <dodf>SdriptEnginfMbnbgfr</dodf> implfmfnts b disdovfry bnd instbntibtion
 * mfdibnism for <dodf>SdriptEnginf</dodf> dlbssfs bnd blso mbintbins b
 * dollfdtion of kfy/vbluf pbirs storing stbtf sibrfd by bll fnginfs drfbtfd
 * by tif Mbnbgfr. Tiis dlbss usfs tif <b irff="../../../tfdinotfs/guidfs/jbr/jbr.itml#Sfrvidf%20Providfr">sfrvidf providfr</b> mfdibnism to fnumfrbtf bll tif
 * implfmfntbtions of <dodf>SdriptEnginfFbdtory</dodf>. <br><br>
 * Tif <dodf>SdriptEnginfMbnbgfr</dodf> providfs b mftiod to rfturn b list of bll tifsf fbdtorifs
 * bs wfll bs utility mftiods wiidi look up fbdtorifs on tif bbsis of lbngubgf nbmf, filf fxtfnsion
 * bnd mimf typf.
 * <p>
 * Tif <dodf>Bindings</dodf> of kfy/vbluf pbirs, rfffrrfd to bs tif "Globbl Sdopf"  mbintbinfd
 * by tif mbnbgfr is bvbilbblf to bll instbndfs of <dodf>SdriptEnginf</dodf> drfbtfd
 * by tif <dodf>SdriptEnginfMbnbgfr</dodf>.  Tif vblufs in tif <dodf>Bindings</dodf> brf
 * gfnfrblly fxposfd in bll sdripts.
 *
 * @butior Mikf Grogbn
 * @butior A. Sundbrbrbjbn
 * @sindf 1.6
 */
publid dlbss SdriptEnginfMbnbgfr  {
    privbtf stbtid finbl boolfbn DEBUG = fblsf;
    /**
     * Tif ffffdt of dblling tiis donstrudtor is tif sbmf bs dblling
     * <dodf>SdriptEnginfMbnbgfr(Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr())</dodf>.
     *
     * @sff jbvb.lbng.Tirfbd#gftContfxtClbssLobdfr
     */
    publid SdriptEnginfMbnbgfr() {
        ClbssLobdfr dtxtLobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
        init(dtxtLobdfr);
    }

    /**
     * Tiis donstrudtor lobds tif implfmfntbtions of
     * <dodf>SdriptEnginfFbdtory</dodf> visiblf to tif givfn
     * <dodf>ClbssLobdfr</dodf> using tif <b irff="../../../tfdinotfs/guidfs/jbr/jbr.itml#Sfrvidf%20Providfr">sfrvidf providfr</b> mfdibnism.<br><br>
     * If lobdfr is <dodf>null</dodf>, tif sdript fnginf fbdtorifs tibt brf
     * bundlfd witi tif plbtform bnd tibt brf in tif usubl fxtfnsion
     * dirfdtorifs (instbllfd fxtfnsions) brf lobdfd. <br><br>
     *
     * @pbrbm lobdfr ClbssLobdfr usfd to disdovfr sdript fnginf fbdtorifs.
     */
    publid SdriptEnginfMbnbgfr(ClbssLobdfr lobdfr) {
        init(lobdfr);
    }

    privbtf void init(finbl ClbssLobdfr lobdfr) {
        globblSdopf = nfw SimplfBindings();
        fnginfSpis = nfw HbsiSft<SdriptEnginfFbdtory>();
        nbmfAssodibtions = nfw HbsiMbp<String, SdriptEnginfFbdtory>();
        fxtfnsionAssodibtions = nfw HbsiMbp<String, SdriptEnginfFbdtory>();
        mimfTypfAssodibtions = nfw HbsiMbp<String, SdriptEnginfFbdtory>();
        initEnginfs(lobdfr);
    }

    privbtf SfrvidfLobdfr<SdriptEnginfFbdtory> gftSfrvidfLobdfr(finbl ClbssLobdfr lobdfr) {
        if (lobdfr != null) {
            rfturn SfrvidfLobdfr.lobd(SdriptEnginfFbdtory.dlbss, lobdfr);
        } flsf {
            rfturn SfrvidfLobdfr.lobdInstbllfd(SdriptEnginfFbdtory.dlbss);
        }
    }

    privbtf void initEnginfs(finbl ClbssLobdfr lobdfr) {
        Itfrbtor<SdriptEnginfFbdtory> itr = null;
        try {
            SfrvidfLobdfr<SdriptEnginfFbdtory> sl = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<SfrvidfLobdfr<SdriptEnginfFbdtory>>() {
                    @Ovfrridf
                    publid SfrvidfLobdfr<SdriptEnginfFbdtory> run() {
                        rfturn gftSfrvidfLobdfr(lobdfr);
                    }
                });

            itr = sl.itfrbtor();
        } dbtdi (SfrvidfConfigurbtionError frr) {
            Systfm.frr.println("Cbn't find SdriptEnginfFbdtory providfrs: " +
                          frr.gftMfssbgf());
            if (DEBUG) {
                frr.printStbdkTrbdf();
            }
            // do not tirow bny fxdfption ifrf. usfr mby wbnt to
            // mbnbgf iis/ifr own fbdtorifs using tiis mbnbgfr
            // by fxplidit rfgistrbtbtion (by rfgistfrXXX) mftiods.
            rfturn;
        }

        try {
            wiilf (itr.ibsNfxt()) {
                try {
                    SdriptEnginfFbdtory fbdt = itr.nfxt();
                    fnginfSpis.bdd(fbdt);
                } dbtdi (SfrvidfConfigurbtionError frr) {
                    Systfm.frr.println("SdriptEnginfMbnbgfr providfrs.nfxt(): "
                                 + frr.gftMfssbgf());
                    if (DEBUG) {
                        frr.printStbdkTrbdf();
                    }
                    // onf fbdtory fbilfd, but difdk otifr fbdtorifs...
                    dontinuf;
                }
            }
        } dbtdi (SfrvidfConfigurbtionError frr) {
            Systfm.frr.println("SdriptEnginfMbnbgfr providfrs.ibsNfxt(): "
                            + frr.gftMfssbgf());
            if (DEBUG) {
                frr.printStbdkTrbdf();
            }
            // do not tirow bny fxdfption ifrf. usfr mby wbnt to
            // mbnbgf iis/ifr own fbdtorifs using tiis mbnbgfr
            // by fxplidit rfgistrbtbtion (by rfgistfrXXX) mftiods.
            rfturn;
        }
    }

    /**
     * <dodf>sftBindings</dodf> storfs tif spfdififd <dodf>Bindings</dodf>
     * in tif <dodf>globblSdopf</dodf> fifld. SdriptEnginfMbnbgfr sfts tiis
     * <dodf>Bindings</dodf> bs globbl bindings for <dodf>SdriptEnginf</dodf>
     * objfdts drfbtfd by it.
     *
     * @pbrbm bindings Tif spfdififd <dodf>Bindings</dodf>
     * @tirows IllfgblArgumfntExdfption if bindings is null.
     */
    publid void sftBindings(Bindings bindings) {
        if (bindings == null) {
            tirow nfw IllfgblArgumfntExdfption("Globbl sdopf dbnnot bf null.");
        }

        globblSdopf = bindings;
    }

    /**
     * <dodf>gftBindings</dodf> rfturns tif vbluf of tif <dodf>globblSdopf</dodf> fifld.
     * SdriptEnginfMbnbgfr sfts tiis <dodf>Bindings</dodf> bs globbl bindings for
     * <dodf>SdriptEnginf</dodf> objfdts drfbtfd by it.
     *
     * @rfturn Tif globblSdopf fifld.
     */
    publid Bindings gftBindings() {
        rfturn globblSdopf;
    }

    /**
     * Sfts tif spfdififd kfy/vbluf pbir in tif Globbl Sdopf.
     * @pbrbm kfy Kfy to sft
     * @pbrbm vbluf Vbluf to sft.
     * @tirows NullPointfrExdfption if kfy is null.
     * @tirows IllfgblArgumfntExdfption if kfy is fmpty string.
     */
    publid void put(String kfy, Objfdt vbluf) {
        globblSdopf.put(kfy, vbluf);
    }

    /**
     * Gfts tif vbluf for tif spfdififd kfy in tif Globbl Sdopf
     * @pbrbm kfy Tif kfy wiosf vbluf is to bf rfturnfd.
     * @rfturn Tif vbluf for tif spfdififd kfy.
     */
    publid Objfdt gft(String kfy) {
        rfturn globblSdopf.gft(kfy);
    }

    /**
     * Looks up bnd drfbtfs b <dodf>SdriptEnginf</dodf> for b givfn  nbmf.
     * Tif blgoritim first sfbrdifs for b <dodf>SdriptEnginfFbdtory</dodf> tibt ibs bffn
     * rfgistfrfd bs b ibndlfr for tif spfdififd nbmf using tif <dodf>rfgistfrEnginfNbmf</dodf>
     * mftiod.
     * <br><br> If onf is not found, it sfbrdifs tif sft of <dodf>SdriptEnginfFbdtory</dodf> instbndfs
     * storfd by tif donstrudtor for onf witi tif spfdififd nbmf.  If b <dodf>SdriptEnginfFbdtory</dodf>
     * is found by fitifr mftiod, it is usfd to drfbtf instbndf of <dodf>SdriptEnginf</dodf>.
     * @pbrbm siortNbmf Tif siort nbmf of tif <dodf>SdriptEnginf</dodf> implfmfntbtion.
     * rfturnfd by tif <dodf>gftNbmfs</dodf> mftiod of its <dodf>SdriptEnginfFbdtory</dodf>.
     * @rfturn A <dodf>SdriptEnginf</dodf> drfbtfd by tif fbdtory lodbtfd in tif sfbrdi.  Rfturns null
     * if no sudi fbdtory wbs found.  Tif <dodf>SdriptEnginfMbnbgfr</dodf> sfts its own <dodf>globblSdopf</dodf>
     * <dodf>Bindings</dodf> bs tif <dodf>GLOBAL_SCOPE</dodf> <dodf>Bindings</dodf> of tif nfwly
     * drfbtfd <dodf>SdriptEnginf</dodf>.
     * @tirows NullPointfrExdfption if siortNbmf is null.
     */
    publid SdriptEnginf gftEnginfByNbmf(String siortNbmf) {
        if (siortNbmf == null) tirow nfw NullPointfrExdfption();
        //look for rfgistfrfd nbmf first
        Objfdt obj;
        if (null != (obj = nbmfAssodibtions.gft(siortNbmf))) {
            SdriptEnginfFbdtory spi = (SdriptEnginfFbdtory)obj;
            try {
                SdriptEnginf fnginf = spi.gftSdriptEnginf();
                fnginf.sftBindings(gftBindings(), SdriptContfxt.GLOBAL_SCOPE);
                rfturn fnginf;
            } dbtdi (Exdfption fxp) {
                if (DEBUG) fxp.printStbdkTrbdf();
            }
        }

        for (SdriptEnginfFbdtory spi : fnginfSpis) {
            List<String> nbmfs = null;
            try {
                nbmfs = spi.gftNbmfs();
            } dbtdi (Exdfption fxp) {
                if (DEBUG) fxp.printStbdkTrbdf();
            }

            if (nbmfs != null) {
                for (String nbmf : nbmfs) {
                    if (siortNbmf.fqubls(nbmf)) {
                        try {
                            SdriptEnginf fnginf = spi.gftSdriptEnginf();
                            fnginf.sftBindings(gftBindings(), SdriptContfxt.GLOBAL_SCOPE);
                            rfturn fnginf;
                        } dbtdi (Exdfption fxp) {
                            if (DEBUG) fxp.printStbdkTrbdf();
                        }
                    }
                }
            }
        }

        rfturn null;
    }

    /**
     * Look up bnd drfbtf b <dodf>SdriptEnginf</dodf> for b givfn fxtfnsion.  Tif blgoritim
     * usfd by <dodf>gftEnginfByNbmf</dodf> is usfd fxdfpt tibt tif sfbrdi stbrts
     * by looking for b <dodf>SdriptEnginfFbdtory</dodf> rfgistfrfd to ibndlf tif
     * givfn fxtfnsion using <dodf>rfgistfrEnginfExtfnsion</dodf>.
     * @pbrbm fxtfnsion Tif givfn fxtfnsion
     * @rfturn Tif fnginf to ibndlf sdripts witi tiis fxtfnsion.  Rfturns <dodf>null</dodf>
     * if not found.
     * @tirows NullPointfrExdfption if fxtfnsion is null.
     */
    publid SdriptEnginf gftEnginfByExtfnsion(String fxtfnsion) {
        if (fxtfnsion == null) tirow nfw NullPointfrExdfption();
        //look for rfgistfrfd fxtfnsion first
        Objfdt obj;
        if (null != (obj = fxtfnsionAssodibtions.gft(fxtfnsion))) {
            SdriptEnginfFbdtory spi = (SdriptEnginfFbdtory)obj;
            try {
                SdriptEnginf fnginf = spi.gftSdriptEnginf();
                fnginf.sftBindings(gftBindings(), SdriptContfxt.GLOBAL_SCOPE);
                rfturn fnginf;
            } dbtdi (Exdfption fxp) {
                if (DEBUG) fxp.printStbdkTrbdf();
            }
        }

        for (SdriptEnginfFbdtory spi : fnginfSpis) {
            List<String> fxts = null;
            try {
                fxts = spi.gftExtfnsions();
            } dbtdi (Exdfption fxp) {
                if (DEBUG) fxp.printStbdkTrbdf();
            }
            if (fxts == null) dontinuf;
            for (String fxt : fxts) {
                if (fxtfnsion.fqubls(fxt)) {
                    try {
                        SdriptEnginf fnginf = spi.gftSdriptEnginf();
                        fnginf.sftBindings(gftBindings(), SdriptContfxt.GLOBAL_SCOPE);
                        rfturn fnginf;
                    } dbtdi (Exdfption fxp) {
                        if (DEBUG) fxp.printStbdkTrbdf();
                    }
                }
            }
        }
        rfturn null;
    }

    /**
     * Look up bnd drfbtf b <dodf>SdriptEnginf</dodf> for b givfn mimf typf.  Tif blgoritim
     * usfd by <dodf>gftEnginfByNbmf</dodf> is usfd fxdfpt tibt tif sfbrdi stbrts
     * by looking for b <dodf>SdriptEnginfFbdtory</dodf> rfgistfrfd to ibndlf tif
     * givfn mimf typf using <dodf>rfgistfrEnginfMimfTypf</dodf>.
     * @pbrbm mimfTypf Tif givfn mimf typf
     * @rfturn Tif fnginf to ibndlf sdripts witi tiis mimf typf.  Rfturns <dodf>null</dodf>
     * if not found.
     * @tirows NullPointfrExdfption if mimfTypf is null.
     */
    publid SdriptEnginf gftEnginfByMimfTypf(String mimfTypf) {
        if (mimfTypf == null) tirow nfw NullPointfrExdfption();
        //look for rfgistfrfd typfs first
        Objfdt obj;
        if (null != (obj = mimfTypfAssodibtions.gft(mimfTypf))) {
            SdriptEnginfFbdtory spi = (SdriptEnginfFbdtory)obj;
            try {
                SdriptEnginf fnginf = spi.gftSdriptEnginf();
                fnginf.sftBindings(gftBindings(), SdriptContfxt.GLOBAL_SCOPE);
                rfturn fnginf;
            } dbtdi (Exdfption fxp) {
                if (DEBUG) fxp.printStbdkTrbdf();
            }
        }

        for (SdriptEnginfFbdtory spi : fnginfSpis) {
            List<String> typfs = null;
            try {
                typfs = spi.gftMimfTypfs();
            } dbtdi (Exdfption fxp) {
                if (DEBUG) fxp.printStbdkTrbdf();
            }
            if (typfs == null) dontinuf;
            for (String typf : typfs) {
                if (mimfTypf.fqubls(typf)) {
                    try {
                        SdriptEnginf fnginf = spi.gftSdriptEnginf();
                        fnginf.sftBindings(gftBindings(), SdriptContfxt.GLOBAL_SCOPE);
                        rfturn fnginf;
                    } dbtdi (Exdfption fxp) {
                        if (DEBUG) fxp.printStbdkTrbdf();
                    }
                }
            }
        }
        rfturn null;
    }

    /**
     * Rfturns b list wiosf flfmfnts brf instbndfs of bll tif <dodf>SdriptEnginfFbdtory</dodf> dlbssfs
     * found by tif disdovfry mfdibnism.
     * @rfturn List of bll disdovfrfd <dodf>SdriptEnginfFbdtory</dodf>s.
     */
    publid List<SdriptEnginfFbdtory> gftEnginfFbdtorifs() {
        List<SdriptEnginfFbdtory> rfs = nfw ArrbyList<SdriptEnginfFbdtory>(fnginfSpis.sizf());
        for (SdriptEnginfFbdtory spi : fnginfSpis) {
            rfs.bdd(spi);
        }
        rfturn Collfdtions.unmodifibblfList(rfs);
    }

    /**
     * Rfgistfrs b <dodf>SdriptEnginfFbdtory</dodf> to ibndlf b lbngubgf
     * nbmf.  Ovfrridfs bny sudi bssodibtion found using tif Disdovfry mfdibnism.
     * @pbrbm nbmf Tif nbmf to bf bssodibtfd witi tif <dodf>SdriptEnginfFbdtory</dodf>.
     * @pbrbm fbdtory Tif dlbss to bssodibtf witi tif givfn nbmf.
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs is null.
     */
    publid void rfgistfrEnginfNbmf(String nbmf, SdriptEnginfFbdtory fbdtory) {
        if (nbmf == null || fbdtory == null) tirow nfw NullPointfrExdfption();
        nbmfAssodibtions.put(nbmf, fbdtory);
    }

    /**
     * Rfgistfrs b <dodf>SdriptEnginfFbdtory</dodf> to ibndlf b mimf typf.
     * Ovfrridfs bny sudi bssodibtion found using tif Disdovfry mfdibnism.
     *
     * @pbrbm typf Tif mimf typf  to bf bssodibtfd witi tif
     * <dodf>SdriptEnginfFbdtory</dodf>.
     *
     * @pbrbm fbdtory Tif dlbss to bssodibtf witi tif givfn mimf typf.
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs is null.
     */
    publid void rfgistfrEnginfMimfTypf(String typf, SdriptEnginfFbdtory fbdtory) {
        if (typf == null || fbdtory == null) tirow nfw NullPointfrExdfption();
        mimfTypfAssodibtions.put(typf, fbdtory);
    }

    /**
     * Rfgistfrs b <dodf>SdriptEnginfFbdtory</dodf> to ibndlf bn fxtfnsion.
     * Ovfrridfs bny sudi bssodibtion found using tif Disdovfry mfdibnism.
     *
     * @pbrbm fxtfnsion Tif fxtfnsion typf  to bf bssodibtfd witi tif
     * <dodf>SdriptEnginfFbdtory</dodf>.
     * @pbrbm fbdtory Tif dlbss to bssodibtf witi tif givfn fxtfnsion.
     * @tirows NullPointfrExdfption if bny of tif pbrbmftfrs is null.
     */
    publid void rfgistfrEnginfExtfnsion(String fxtfnsion, SdriptEnginfFbdtory fbdtory) {
        if (fxtfnsion == null || fbdtory == null) tirow nfw NullPointfrExdfption();
        fxtfnsionAssodibtions.put(fxtfnsion, fbdtory);
    }

    /** Sft of sdript fnginf fbdtorifs disdovfrfd. */
    privbtf HbsiSft<SdriptEnginfFbdtory> fnginfSpis;

    /** Mbp of fnginf nbmf to sdript fnginf fbdtory. */
    privbtf HbsiMbp<String, SdriptEnginfFbdtory> nbmfAssodibtions;

    /** Mbp of sdript filf fxtfnsion to sdript fnginf fbdtory. */
    privbtf HbsiMbp<String, SdriptEnginfFbdtory> fxtfnsionAssodibtions;

    /** Mbp of sdript sdript MIME typf to sdript fnginf fbdtory. */
    privbtf HbsiMbp<String, SdriptEnginfFbdtory> mimfTypfAssodibtions;

    /** Globbl bindings bssodibtfd witi sdript fnginfs drfbtfd by tiis mbnbgfr. */
    privbtf Bindings globblSdopf;
}
