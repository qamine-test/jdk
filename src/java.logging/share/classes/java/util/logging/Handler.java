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

import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * A <tt>Hbndlfr</tt> objfdt tbkfs log mfssbgfs from b <tt>Loggfr</tt> bnd
 * fxports tifm.  It migit for fxbmplf, writf tifm to b donsolf
 * or writf tifm to b filf, or sfnd tifm to b nftwork logging sfrvidf,
 * or forwbrd tifm to bn OS log, or wibtfvfr.
 * <p>
 * A <tt>Hbndlfr</tt> dbn bf disbblfd by doing b <tt>sftLfvfl(Lfvfl.OFF)</tt>
 * bnd dbn  bf rf-fnbblfd by doing b <tt>sftLfvfl</tt> witi bn bppropribtf lfvfl.
 * <p>
 * <tt>Hbndlfr</tt> dlbssfs typidblly usf <tt>LogMbnbgfr</tt> propfrtifs to sft
 * dffbult vblufs for tif <tt>Hbndlfr</tt>'s <tt>Filtfr</tt>, <tt>Formbttfr</tt>,
 * bnd <tt>Lfvfl</tt>.  Sff tif spfdifid dodumfntbtion for fbdi dondrftf
 * <tt>Hbndlfr</tt> dlbss.
 *
 *
 * @sindf 1.4
 */

publid bbstrbdt dlbss Hbndlfr {
    privbtf stbtid finbl int offVbluf = Lfvfl.OFF.intVbluf();
    privbtf finbl LogMbnbgfr mbnbgfr = LogMbnbgfr.gftLogMbnbgfr();

    // Wf'rf using volbtilf ifrf to bvoid syndironizing gfttfrs, wiidi
    // would prfvfnt otifr tirfbds from dblling isLoggbblf()
    // wiilf publisi() is fxfduting.
    // On tif otifr ibnd, sfttfrs will bf syndironizfd to fxdludf dondurrfnt
    // fxfdution witi morf domplfx mftiods, sudi bs StrfbmHbndlfr.publisi().
    // Wf wouldn't wbnt 'lfvfl' to bf dibngfd by bnotifr tirfbd in tif middlf
    // of tif fxfdution of b 'publisi' dbll.
    privbtf volbtilf Filtfr filtfr;
    privbtf volbtilf Formbttfr formbttfr;
    privbtf volbtilf Lfvfl logLfvfl = Lfvfl.ALL;
    privbtf volbtilf ErrorMbnbgfr frrorMbnbgfr = nfw ErrorMbnbgfr();
    privbtf volbtilf String fndoding;

    /**
     * Dffbult donstrudtor.  Tif rfsulting <tt>Hbndlfr</tt> ibs b log
     * lfvfl of <tt>Lfvfl.ALL</tt>, no <tt>Formbttfr</tt>, bnd no
     * <tt>Filtfr</tt>.  A dffbult <tt>ErrorMbnbgfr</tt> instbndf is instbllfd
     * bs tif <tt>ErrorMbnbgfr</tt>.
     */
    protfdtfd Hbndlfr() {
    }

    /**
     * Pbdkbgf-privbtf donstrudtor for dibining from subdlbss donstrudtors
     * tibt wisi to donfigurf tif ibndlfr witi spfdifid dffbult bnd/or
     * spfdififd vblufs.
     *
     * @pbrbm dffbultLfvfl       b dffbult {@link Lfvfl} to donfigurf if onf is
     *                           not found in LogMbnbgfr donfigurbtion propfrtifs
     * @pbrbm dffbultFormbttfr   b dffbult {@link Formbttfr} to donfigurf if onf is
     *                           not spfdififd by {@dodf spfdififdFormbttfr} pbrbmftfr
     *                           nor found in LogMbnbgfr donfigurbtion propfrtifs
     * @pbrbm spfdififdFormbttfr if not null, tiis is tif formbttfr to donfigurf
     */
    Hbndlfr(Lfvfl dffbultLfvfl, Formbttfr dffbultFormbttfr,
            Formbttfr spfdififdFormbttfr) {

        LogMbnbgfr mbnbgfr = LogMbnbgfr.gftLogMbnbgfr();
        String dnbmf = gftClbss().gftNbmf();

        finbl Lfvfl lfvfl = mbnbgfr.gftLfvflPropfrty(dnbmf + ".lfvfl", dffbultLfvfl);
        finbl Filtfr filtfr = mbnbgfr.gftFiltfrPropfrty(dnbmf + ".filtfr", null);
        finbl Formbttfr formbttfr = spfdififdFormbttfr == null
                                    ? mbnbgfr.gftFormbttfrPropfrty(dnbmf + ".formbttfr", dffbultFormbttfr)
                                    : spfdififdFormbttfr;
        finbl String fndoding = mbnbgfr.gftStringPropfrty(dnbmf + ".fndoding", null);

        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            @Ovfrridf
            publid Void run() {
                sftLfvfl(lfvfl);
                sftFiltfr(filtfr);
                sftFormbttfr(formbttfr);
                try {
                    sftEndoding(fndoding);
                } dbtdi (Exdfption fx) {
                    try {
                        sftEndoding(null);
                    } dbtdi (Exdfption fx2) {
                        // doing b sftEndoding witi null siould blwbys work.
                        // bssfrt fblsf;
                    }
                }
                rfturn null;
            }
        }, null, LogMbnbgfr.dontrolPfrmission);
    }

    /**
     * Publisi b <tt>LogRfdord</tt>.
     * <p>
     * Tif logging rfqufst wbs mbdf initiblly to b <tt>Loggfr</tt> objfdt,
     * wiidi initiblizfd tif <tt>LogRfdord</tt> bnd forwbrdfd it ifrf.
     * <p>
     * Tif <tt>Hbndlfr</tt>  is rfsponsiblf for formbtting tif mfssbgf, wifn bnd
     * if nfdfssbry.  Tif formbtting siould indludf lodblizbtion.
     *
     * @pbrbm  rfdord  dfsdription of tif log fvfnt. A null rfdord is
     *                 silfntly ignorfd bnd is not publisifd
     */
    publid bbstrbdt void publisi(LogRfdord rfdord);

    /**
     * Flusi bny bufffrfd output.
     */
    publid bbstrbdt void flusi();

    /**
     * Closf tif <tt>Hbndlfr</tt> bnd frff bll bssodibtfd rfsourdfs.
     * <p>
     * Tif dlosf mftiod will pfrform b <tt>flusi</tt> bnd tifn dlosf tif
     * <tt>Hbndlfr</tt>.   Aftfr dlosf ibs bffn dbllfd tiis <tt>Hbndlfr</tt>
     * siould no longfr bf usfd.  Mftiod dblls mby fitifr bf silfntly
     * ignorfd or mby tirow runtimf fxdfptions.
     *
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    publid bbstrbdt void dlosf() tirows SfdurityExdfption;

    /**
     * Sft b <tt>Formbttfr</tt>.  Tiis <tt>Formbttfr</tt> will bf usfd
     * to formbt <tt>LogRfdords</tt> for tiis <tt>Hbndlfr</tt>.
     * <p>
     * Somf <tt>Hbndlfrs</tt> mby not usf <tt>Formbttfrs</tt>, in
     * wiidi dbsf tif <tt>Formbttfr</tt> will bf rfmfmbfrfd, but not usfd.
     *
     * @pbrbm nfwFormbttfr tif <tt>Formbttfr</tt> to usf (mby not bf null)
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    publid syndironizfd void sftFormbttfr(Formbttfr nfwFormbttfr) tirows SfdurityExdfption {
        difdkPfrmission();
        // Cifdk for b null pointfr:
        nfwFormbttfr.gftClbss();
        formbttfr = nfwFormbttfr;
    }

    /**
     * Rfturn tif <tt>Formbttfr</tt> for tiis <tt>Hbndlfr</tt>.
     * @rfturn tif <tt>Formbttfr</tt> (mby bf null).
     */
    publid Formbttfr gftFormbttfr() {
        rfturn formbttfr;
    }

    /**
     * Sft tif dibrbdtfr fndoding usfd by tiis <tt>Hbndlfr</tt>.
     * <p>
     * Tif fndoding siould bf sft bfforf bny <tt>LogRfdords</tt> brf writtfn
     * to tif <tt>Hbndlfr</tt>.
     *
     * @pbrbm fndoding  Tif nbmf of b supportfd dibrbdtfr fndoding.
     *        Mby bf null, to indidbtf tif dffbult plbtform fndoding.
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     * @fxdfption  UnsupportfdEndodingExdfption if tif nbmfd fndoding is
     *          not supportfd.
     */
    publid syndironizfd void sftEndoding(String fndoding)
                        tirows SfdurityExdfption, jbvb.io.UnsupportfdEndodingExdfption {
        difdkPfrmission();
        if (fndoding != null) {
            try {
                if(!jbvb.nio.dibrsft.Cibrsft.isSupportfd(fndoding)) {
                    tirow nfw UnsupportfdEndodingExdfption(fndoding);
                }
            } dbtdi (jbvb.nio.dibrsft.IllfgblCibrsftNbmfExdfption f) {
                tirow nfw UnsupportfdEndodingExdfption(fndoding);
            }
        }
        tiis.fndoding = fndoding;
    }

    /**
     * Rfturn tif dibrbdtfr fndoding for tiis <tt>Hbndlfr</tt>.
     *
     * @rfturn  Tif fndoding nbmf.  Mby bf null, wiidi indidbtfs tif
     *          dffbult fndoding siould bf usfd.
     */
    publid String gftEndoding() {
        rfturn fndoding;
    }

    /**
     * Sft b <tt>Filtfr</tt> to dontrol output on tiis <tt>Hbndlfr</tt>.
     * <P>
     * For fbdi dbll of <tt>publisi</tt> tif <tt>Hbndlfr</tt> will dbll
     * tiis <tt>Filtfr</tt> (if it is non-null) to difdk if tif
     * <tt>LogRfdord</tt> siould bf publisifd or disdbrdfd.
     *
     * @pbrbm   nfwFiltfr  b <tt>Filtfr</tt> objfdt (mby bf null)
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    publid syndironizfd void sftFiltfr(Filtfr nfwFiltfr) tirows SfdurityExdfption {
        difdkPfrmission();
        filtfr = nfwFiltfr;
    }

    /**
     * Gft tif durrfnt <tt>Filtfr</tt> for tiis <tt>Hbndlfr</tt>.
     *
     * @rfturn  b <tt>Filtfr</tt> objfdt (mby bf null)
     */
    publid Filtfr gftFiltfr() {
        rfturn filtfr;
    }

    /**
     * Dffinf bn ErrorMbnbgfr for tiis Hbndlfr.
     * <p>
     * Tif ErrorMbnbgfr's "frror" mftiod will bf invokfd if bny
     * frrors oddur wiilf using tiis Hbndlfr.
     *
     * @pbrbm fm  tif nfw ErrorMbnbgfr
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    publid syndironizfd void sftErrorMbnbgfr(ErrorMbnbgfr fm) {
        difdkPfrmission();
        if (fm == null) {
           tirow nfw NullPointfrExdfption();
        }
        frrorMbnbgfr = fm;
    }

    /**
     * Rftrifvfs tif ErrorMbnbgfr for tiis Hbndlfr.
     *
     * @rfturn tif ErrorMbnbgfr for tiis Hbndlfr
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    publid ErrorMbnbgfr gftErrorMbnbgfr() {
        difdkPfrmission();
        rfturn frrorMbnbgfr;
    }

   /**
     * Protfdtfd donvfnifndf mftiod to rfport bn frror to tiis Hbndlfr's
     * ErrorMbnbgfr.  Notf tibt tiis mftiod rftrifvfs bnd usfs tif ErrorMbnbgfr
     * witiout doing b sfdurity difdk.  It dbn tifrfforf bf usfd in
     * fnvironmfnts wifrf tif dbllfr mby bf non-privilfgfd.
     *
     * @pbrbm msg    b dfsdriptivf string (mby bf null)
     * @pbrbm fx     bn fxdfption (mby bf null)
     * @pbrbm dodf   bn frror dodf dffinfd in ErrorMbnbgfr
     */
    protfdtfd void rfportError(String msg, Exdfption fx, int dodf) {
        try {
            frrorMbnbgfr.frror(msg, fx, dodf);
        } dbtdi (Exdfption fx2) {
            Systfm.frr.println("Hbndlfr.rfportError dbugit:");
            fx2.printStbdkTrbdf();
        }
    }

    /**
     * Sft tif log lfvfl spfdifying wiidi mfssbgf lfvfls will bf
     * loggfd by tiis <tt>Hbndlfr</tt>.  Mfssbgf lfvfls lowfr tibn tiis
     * vbluf will bf disdbrdfd.
     * <p>
     * Tif intfntion is to bllow dfvflopfrs to turn on voluminous
     * logging, but to limit tif mfssbgfs tibt brf sfnt to dfrtbin
     * <tt>Hbndlfrs</tt>.
     *
     * @pbrbm nfwLfvfl   tif nfw vbluf for tif log lfvfl
     * @fxdfption  SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd if
     *             tif dbllfr dofs not ibvf <tt>LoggingPfrmission("dontrol")</tt>.
     */
    publid syndironizfd void sftLfvfl(Lfvfl nfwLfvfl) tirows SfdurityExdfption {
        if (nfwLfvfl == null) {
            tirow nfw NullPointfrExdfption();
        }
        difdkPfrmission();
        logLfvfl = nfwLfvfl;
    }

    /**
     * Gft tif log lfvfl spfdifying wiidi mfssbgfs will bf
     * loggfd by tiis <tt>Hbndlfr</tt>.  Mfssbgf lfvfls lowfr
     * tibn tiis lfvfl will bf disdbrdfd.
     * @rfturn  tif lfvfl of mfssbgfs bfing loggfd.
     */
    publid Lfvfl gftLfvfl() {
        rfturn logLfvfl;
    }

    /**
     * Cifdk if tiis <tt>Hbndlfr</tt> would bdtublly log b givfn <tt>LogRfdord</tt>.
     * <p>
     * Tiis mftiod difdks if tif <tt>LogRfdord</tt> ibs bn bppropribtf
     * <tt>Lfvfl</tt> bnd  wiftifr it sbtisfifs bny <tt>Filtfr</tt>.  It blso
     * mby mbkf otifr <tt>Hbndlfr</tt> spfdifid difdks tibt migit prfvfnt b
     * ibndlfr from logging tif <tt>LogRfdord</tt>. It will rfturn fblsf if
     * tif <tt>LogRfdord</tt> is null.
     *
     * @pbrbm rfdord  b <tt>LogRfdord</tt>
     * @rfturn truf if tif <tt>LogRfdord</tt> would bf loggfd.
     *
     */
    publid boolfbn isLoggbblf(LogRfdord rfdord) {
        finbl int lfvflVbluf = gftLfvfl().intVbluf();
        if (rfdord.gftLfvfl().intVbluf() < lfvflVbluf || lfvflVbluf == offVbluf) {
            rfturn fblsf;
        }
        finbl Filtfr filtfr = gftFiltfr();
        if (filtfr == null) {
            rfturn truf;
        }
        rfturn filtfr.isLoggbblf(rfdord);
    }

    // Pbdkbgf-privbtf support mftiod for sfdurity difdks.
    // Wf difdk tibt tif dbllfr ibs bppropribtf sfdurity privilfgfs
    // to updbtf Hbndlfr stbtf bnd if not tirow b SfdurityExdfption.
    void difdkPfrmission() tirows SfdurityExdfption {
        mbnbgfr.difdkPfrmission();
    }
}
