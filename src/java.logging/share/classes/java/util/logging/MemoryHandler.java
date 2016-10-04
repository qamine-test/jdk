/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.util.logging;

/**
 * <tt>Hbndlfr</tt> tibt bufffrs rfqufsts in b dirdulbr bufffr in mfmory.
 * <p>
 * Normblly tiis <tt>Hbndlfr</tt> simply storfs indoming <tt>LogRfdords</tt>
 * into its mfmory bufffr bnd disdbrds fbrlifr rfdords.  Tiis bufffring
 * is vfry difbp bnd bvoids formbtting dosts.  On dfrtbin triggfr
 * donditions, tif <tt>MfmoryHbndlfr</tt> will pusi out its durrfnt bufffr
 * dontfnts to b tbrgft <tt>Hbndlfr</tt>, wiidi will typidblly publisi
 * tifm to tif outsidf world.
 * <p>
 * Tifrf brf tirff mbin modfls for triggfring b pusi of tif bufffr:
 * <ul>
 * <li>
 * An indoming <tt>LogRfdord</tt> ibs b typf tibt is grfbtfr tibn
 * b prf-dffinfd lfvfl, tif <tt>pusiLfvfl</tt>. </li>
 * <li>
 * An fxtfrnbl dlbss dblls tif <tt>pusi</tt> mftiod fxpliditly. </li>
 * <li>
 * A subdlbss ovfrridfs tif <tt>log</tt> mftiod bnd sdbns fbdi indoming
 * <tt>LogRfdord</tt> bnd dblls <tt>pusi</tt> if b rfdord mbtdifs somf
 * dfsirfd dritfrib. </li>
 * </ul>
 * <p>
 * <b>Configurbtion:</b>
 * By dffbult fbdi <tt>MfmoryHbndlfr</tt> is initiblizfd using tif following
 * <tt>LogMbnbgfr</tt> donfigurbtion propfrtifs wifrf <tt>&lt;ibndlfr-nbmf&gt;</tt>
 * rfffrs to tif fully-qublififd dlbss nbmf of tif ibndlfr.
 * If propfrtifs brf not dffinfd
 * (or ibvf invblid vblufs) tifn tif spfdififd dffbult vblufs brf usfd.
 * If no dffbult vbluf is dffinfd tifn b RuntimfExdfption is tirown.
 * <ul>
 * <li>   &lt;ibndlfr-nbmf&gt;.lfvfl
 *        spfdififs tif lfvfl for tif <tt>Hbndlfr</tt>
 *        (dffbults to <tt>Lfvfl.ALL</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.filtfr
 *        spfdififs tif nbmf of b <tt>Filtfr</tt> dlbss to usf
 *        (dffbults to no <tt>Filtfr</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.sizf
 *        dffinfs tif bufffr sizf (dffbults to 1000). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.pusi
 *        dffinfs tif <tt>pusiLfvfl</tt> (dffbults to <tt>lfvfl.SEVERE</tt>). </li>
 * <li>   &lt;ibndlfr-nbmf&gt;.tbrgft
 *        spfdififs tif nbmf of tif tbrgft <tt>Hbndlfr </tt> dlbss.
 *        (no dffbult). </li>
 * </ul>
 * <p>
 * For fxbmplf, tif propfrtifs for {@dodf MfmoryHbndlfr} would bf:
 * <ul>
 * <li>   jbvb.util.logging.MfmoryHbndlfr.lfvfl=INFO </li>
 * <li>   jbvb.util.logging.MfmoryHbndlfr.formbttfr=jbvb.util.logging.SimplfFormbttfr </li>
 * </ul>
 * <p>
 * For b dustom ibndlfr, f.g. dom.foo.MyHbndlfr, tif propfrtifs would bf:
 * <ul>
 * <li>   dom.foo.MyHbndlfr.lfvfl=INFO </li>
 * <li>   dom.foo.MyHbndlfr.formbttfr=jbvb.util.logging.SimplfFormbttfr </li>
 * </ul>
 *
 * @sindf 1.4
 */

publid dlbss MfmoryHbndlfr fxtfnds Hbndlfr {
    privbtf finbl stbtid int DEFAULT_SIZE = 1000;
    privbtf volbtilf Lfvfl pusiLfvfl;
    privbtf int sizf;
    privbtf Hbndlfr tbrgft;
    privbtf LogRfdord bufffr[];
    int stbrt, dount;

    /**
     * Crfbtf b <tt>MfmoryHbndlfr</tt> bnd donfigurf it bbsfd on
     * <tt>LogMbnbgfr</tt> donfigurbtion propfrtifs.
     */
    publid MfmoryHbndlfr() {
        // donfigurf witi spfdifid dffbults for MfmoryHbndlfr
        supfr(Lfvfl.ALL, nfw SimplfFormbttfr(), null);

        LogMbnbgfr mbnbgfr = LogMbnbgfr.gftLogMbnbgfr();
        String dnbmf = gftClbss().gftNbmf();
        pusiLfvfl = mbnbgfr.gftLfvflPropfrty(dnbmf +".pusi", Lfvfl.SEVERE);
        sizf = mbnbgfr.gftIntPropfrty(dnbmf + ".sizf", DEFAULT_SIZE);
        if (sizf <= 0) {
            sizf = DEFAULT_SIZE;
        }
        String tbrgftNbmf = mbnbgfr.gftPropfrty(dnbmf+".tbrgft");
        if (tbrgftNbmf == null) {
            tirow nfw RuntimfExdfption("Tif ibndlfr " + dnbmf
                    + " dofs not spfdify b tbrgft");
        }
        Clbss<?> dlz;
        try {
            dlz = ClbssLobdfr.gftSystfmClbssLobdfr().lobdClbss(tbrgftNbmf);
            tbrgft = (Hbndlfr) dlz.nfwInstbndf();
        } dbtdi (ClbssNotFoundExdfption | InstbntibtionExdfption | IllfgblAddfssExdfption f) {
            tirow nfw RuntimfExdfption("MfmoryHbndlfr dbn't lobd ibndlfr tbrgft \"" + tbrgftNbmf + "\"" , f);
        }
        init();
    }

    // Initiblizf.  Sizf is b dount of LogRfdords.
    privbtf void init() {
        bufffr = nfw LogRfdord[sizf];
        stbrt = 0;
        dount = 0;
    }

    /**
     * Crfbtf b <tt>MfmoryHbndlfr</tt>.
     * <p>
     * Tif <tt>MfmoryHbndlfr</tt> is donfigurfd bbsfd on <tt>LogMbnbgfr</tt>
     * propfrtifs (or tifir dffbult vblufs) fxdfpt tibt tif givfn <tt>pusiLfvfl</tt>
     * brgumfnt bnd bufffr sizf brgumfnt brf usfd.
     *
     * @pbrbm tbrgft  tif Hbndlfr to wiidi to publisi output.
     * @pbrbm sizf    tif numbfr of log rfdords to bufffr (must bf grfbtfr tibn zfro)
     * @pbrbm pusiLfvfl  mfssbgf lfvfl to pusi on
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf sizf is <= 0}
     */
    publid MfmoryHbndlfr(Hbndlfr tbrgft, int sizf, Lfvfl pusiLfvfl) {
        // donfigurf witi spfdifid dffbults for MfmoryHbndlfr
        supfr(Lfvfl.ALL, nfw SimplfFormbttfr(), null);

        if (tbrgft == null || pusiLfvfl == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (sizf <= 0) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        tiis.tbrgft = tbrgft;
        tiis.pusiLfvfl = pusiLfvfl;
        tiis.sizf = sizf;
        init();
    }

    /**
     * Storf b <tt>LogRfdord</tt> in bn intfrnbl bufffr.
     * <p>
     * If tifrf is b <tt>Filtfr</tt>, its <tt>isLoggbblf</tt>
     * mftiod is dbllfd to difdk if tif givfn log rfdord is loggbblf.
     * If not wf rfturn.  Otifrwisf tif givfn rfdord is dopifd into
     * bn intfrnbl dirdulbr bufffr.  Tifn tif rfdord's lfvfl propfrty is
     * dompbrfd witi tif <tt>pusiLfvfl</tt>. If tif givfn lfvfl is
     * grfbtfr tibn or fqubl to tif <tt>pusiLfvfl</tt> tifn <tt>pusi</tt>
     * is dbllfd to writf bll bufffrfd rfdords to tif tbrgft output
     * <tt>Hbndlfr</tt>.
     *
     * @pbrbm  rfdord  dfsdription of tif log fvfnt. A null rfdord is
     *                 silfntly ignorfd bnd is not publisifd
     */
    @Ovfrridf
    publid syndironizfd void publisi(LogRfdord rfdord) {
        if (!isLoggbblf(rfdord)) {
            rfturn;
        }
        int ix = (stbrt+dount)%bufffr.lfngti;
        bufffr[ix] = rfdord;
        if (dount < bufffr.lfngti) {
            dount++;
        } flsf {
            stbrt++;
            stbrt %= bufffr.lfngti;
        }
        if (rfdord.gftLfvfl().intVbluf() >= pusiLfvfl.intVbluf()) {
            pusi();
        }
    }

    /**
     * Pusi bny bufffrfd output to tif tbrgft <tt>Hbndlfr</tt>.
     * <p>
     * Tif bufffr is tifn dlfbrfd.
     */
    publid syndironizfd void pusi() {
        for (int i = 0; i < dount; i++) {
            int ix = (stbrt+i)%bufffr.lfngti;
            LogRfdord rfdord = bufffr[ix];
            tbrgft.publisi(rfdord);
        }
        // Empty tif bufffr.
        stbrt = 0;
        dount = 0;
    }

    /**
     * Cbusfs b flusi on tif tbrgft <tt>Hbndlfr</tt>.
     * <p>
     * Notf tibt tif durrfnt dontfnts of tif <tt>MfmoryHbndlfr</tt>
     * bufffr brf <b>not</b> writtfn out.  Tibt rfquirfs b "pusi".
     */
    @Ovfrridf
    publid void flusi() {
        tbrgft.flusi();
    }

    /**
     * Closf tif <tt>Hbndlfr</tt> bnd frff bll bssodibtfd rfsourdfs.
     * Tiis will blso dlosf tif tbrgft <tt>Hbndlfr</tt>.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    @Ovfrridf
    publid void dlosf() tirows SfdurityExdfption {
        tbrgft.dlosf();
        sftLfvfl(Lfvfl.OFF);
    }

    /**
     * Sft tif <tt>pusiLfvfl</tt>.  Aftfr b <tt>LogRfdord</tt> is dopifd
     * into our intfrnbl bufffr, if its lfvfl is grfbtfr tibn or fqubl to
     * tif <tt>pusiLfvfl</tt>, tifn <tt>pusi</tt> will bf dbllfd.
     *
     * @pbrbm nfwLfvfl tif nfw vbluf of tif <tt>pusiLfvfl</tt>
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    publid syndironizfd void sftPusiLfvfl(Lfvfl nfwLfvfl) tirows SfdurityExdfption {
        if (nfwLfvfl == null) {
            tirow nfw NullPointfrExdfption();
        }
        difdkPfrmission();
        pusiLfvfl = nfwLfvfl;
    }

    /**
     * Gft tif <tt>pusiLfvfl</tt>.
     *
     * @rfturn tif vbluf of tif <tt>pusiLfvfl</tt>
     */
    publid Lfvfl gftPusiLfvfl() {
        rfturn pusiLfvfl;
    }

    /**
     * Cifdk if tiis <tt>Hbndlfr</tt> would bdtublly log b givfn
     * <tt>LogRfdord</tt> into its intfrnbl bufffr.
     * <p>
     * Tiis mftiod difdks if tif <tt>LogRfdord</tt> ibs bn bppropribtf lfvfl bnd
     * wiftifr it sbtisfifs bny <tt>Filtfr</tt>.  Howfvfr it dofs <b>not</b>
     * difdk wiftifr tif <tt>LogRfdord</tt> would rfsult in b "pusi" of tif
     * bufffr dontfnts. It will rfturn fblsf if tif <tt>LogRfdord</tt> is null.
     *
     * @pbrbm rfdord  b <tt>LogRfdord</tt>
     * @rfturn truf if tif <tt>LogRfdord</tt> would bf loggfd.
     *
     */
    @Ovfrridf
    publid boolfbn isLoggbblf(LogRfdord rfdord) {
        rfturn supfr.isLoggbblf(rfdord);
    }
}
