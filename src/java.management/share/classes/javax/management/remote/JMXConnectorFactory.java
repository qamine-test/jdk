/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rfmotf;

import dom.sun.jmx.mbfbnsfrvfr.Util;
import jbvb.io.IOExdfption;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Itfrbtor;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.StringTokfnizfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

import dom.sun.jmx.rfmotf.util.ClbssLoggfr;
import dom.sun.jmx.rfmotf.util.EnvHflp;
import sun.rfflfdt.misd.RfflfdtUtil;


/**
 * <p>Fbdtory to drfbtf JMX API donnfdtor dlifnts.  Tifrf
 * brf no instbndfs of tiis dlbss.</p>
 *
 * <p>Connfdtions brf usublly mbdf using tif {@link
 * #donnfdt(JMXSfrvidfURL) donnfdt} mftiod of tiis dlbss.  Morf
 * bdvbndfd bpplidbtions dbn sfpbrbtf tif drfbtion of tif donnfdtor
 * dlifnt, using {@link #nfwJMXConnfdtor(JMXSfrvidfURL, Mbp)
 * nfwJMXConnfdtor} bnd tif fstbblisimfnt of tif donnfdtion itsflf, using
 * {@link JMXConnfdtor#donnfdt(Mbp)}.</p>
 *
 * <p>Ebdi dlifnt is drfbtfd by bn instbndf of {@link
 * JMXConnfdtorProvidfr}.  Tiis instbndf is found bs follows.  Supposf
 * tif givfn {@link JMXSfrvidfURL} looks likf
 * <dodf>"sfrvidf:jmx:<fm>protodol</fm>:<fm>rfmbindfr</fm>"</dodf>.
 * Tifn tif fbdtory will bttfmpt to find tif bppropribtf {@link
 * JMXConnfdtorProvidfr} for <dodf><fm>protodol</fm></dodf>.  Ebdi
 * oddurrfndf of tif dibrbdtfr <dodf>+</dodf> or <dodf>-</dodf> in
 * <dodf><fm>protodol</fm></dodf> is rfplbdfd by <dodf>.</dodf> or
 * <dodf>_</dodf>, rfspfdtivfly.</p>
 *
 * <p>A <fm>providfr pbdkbgf list</fm> is sfbrdifd for bs follows:</p>
 *
 * <ol>
 *
 * <li>If tif <dodf>fnvironmfnt</dodf> pbrbmftfr to {@link
 * #nfwJMXConnfdtor(JMXSfrvidfURL, Mbp) nfwJMXConnfdtor} dontbins tif
 * kfy <dodf>jmx.rfmotf.protodol.providfr.pkgs</dodf> tifn tif
 * bssodibtfd vbluf is tif providfr pbdkbgf list.
 *
 * <li>Otifrwisf, if tif systfm propfrty
 * <dodf>jmx.rfmotf.protodol.providfr.pkgs</dodf> fxists, tifn its vbluf
 * is tif providfr pbdkbgf list.
 *
 * <li>Otifrwisf, tifrf is no providfr pbdkbgf list.
 *
 * </ol>
 *
 * <p>Tif providfr pbdkbgf list is b string tibt is intfrprftfd bs b
 * list of non-fmpty Jbvb pbdkbgf nbmfs sfpbrbtfd by vfrtidbl bbrs
 * (<dodf>|</dodf>).  If tif string is fmpty, tifn so is tif providfr
 * pbdkbgf list.  If tif providfr pbdkbgf list is not b String, or if
 * it dontbins bn flfmfnt tibt is bn fmpty string, b {@link
 * JMXProvidfrExdfption} is tirown.</p>
 *
 * <p>If tif providfr pbdkbgf list fxists bnd is not fmpty, tifn for
 * fbdi flfmfnt <dodf><fm>pkg</fm></dodf> of tif list, tif fbdtory
 * will bttfmpt to lobd tif dlbss
 *
 * <blodkquotf>
 * <dodf><fm>pkg</fm>.<fm>protodol</fm>.ClifntProvidfr</dodf>
 * </blodkquotf>

 * <p>If tif <dodf>fnvironmfnt</dodf> pbrbmftfr to {@link
 * #nfwJMXConnfdtor(JMXSfrvidfURL, Mbp) nfwJMXConnfdtor} dontbins tif
 * kfy <dodf>jmx.rfmotf.protodol.providfr.dlbss.lobdfr</dodf> tifn tif
 * bssodibtfd vbluf is tif dlbss lobdfr to usf to lobd tif providfr.
 * If tif bssodibtfd vbluf is not bn instbndf of {@link
 * jbvb.lbng.ClbssLobdfr}, bn {@link
 * jbvb.lbng.IllfgblArgumfntExdfption} is tirown.</p>
 *
 * <p>If tif <dodf>jmx.rfmotf.protodol.providfr.dlbss.lobdfr</dodf>
 * kfy is not prfsfnt in tif <dodf>fnvironmfnt</dodf> pbrbmftfr, tif
 * dblling tirfbd's dontfxt dlbss lobdfr is usfd.</p>
 *
 * <p>If tif bttfmpt to lobd tiis dlbss produdfs b {@link
 * ClbssNotFoundExdfption}, tif sfbrdi for b ibndlfr dontinufs witi
 * tif nfxt flfmfnt of tif list.</p>
 *
 * <p>Otifrwisf, b problfm witi tif providfr found is signbllfd by b
 * {@link JMXProvidfrExdfption} wiosf {@link
 * JMXProvidfrExdfption#gftCbusf() <fm>dbusf</fm>} indidbtfs tif undfrlying
 * fxdfption, bs follows:</p>
 *
 * <ul>
 *
 * <li>if tif bttfmpt to lobd tif dlbss produdfs bn fxdfption otifr
 * tibn <dodf>ClbssNotFoundExdfption</dodf>, tibt is tif
 * <fm>dbusf</fm>;
 *
 * <li>if {@link Clbss#nfwInstbndf()} for tif dlbss produdfs bn
 * fxdfption, tibt is tif <fm>dbusf</fm>.
 *
 * </ul>
 *
 * <p>If no providfr is found by tif bbovf stfps, indluding tif
 * dffbult dbsf wifrf tifrf is no providfr pbdkbgf list, tifn tif
 * implfmfntbtion will usf its own providfr for
 * <dodf><fm>protodol</fm></dodf>, or it will tirow b
 * <dodf>MblformfdURLExdfption</dodf> if tifrf is nonf.  An
 * implfmfntbtion mby dioosf to find providfrs by otifr mfbns.  For
 * fxbmplf, it mby support tif <b
 * irff="{@dodRoot}/../tfdinotfs/guidfs/jbr/jbr.itml#Sfrvidf%20Providfr">
 * JAR donvfntions for sfrvidf providfrs</b>, wifrf tif sfrvidf
 * intfrfbdf is <dodf>JMXConnfdtorProvidfr</dodf>.</p>
 *
 * <p>Evfry implfmfntbtion must support tif RMI donnfdtor protodol witi
 * tif dffbult RMI trbnsport, spfdififd witi string <dodf>rmi</dodf>.
 * An implfmfntbtion mby optionblly support tif RMI donnfdtor protodol
 * witi tif RMI/IIOP trbnsport, spfdififd witi tif string
 * <dodf>iiop</dodf>.</p>
 *
 * <p>Ondf b providfr is found, tif rfsult of tif
 * <dodf>nfwJMXConnfdtor</dodf> mftiod is tif rfsult of dblling {@link
 * JMXConnfdtorProvidfr#nfwJMXConnfdtor(JMXSfrvidfURL,Mbp) nfwJMXConnfdtor}
 * on tif providfr.</p>
 *
 * <p>Tif <dodf>Mbp</dodf> pbrbmftfr pbssfd to tif
 * <dodf>JMXConnfdtorProvidfr</dodf> is b nfw rfbd-only
 * <dodf>Mbp</dodf> tibt dontbins bll tif fntrifs tibt wfrf in tif
 * <dodf>fnvironmfnt</dodf> pbrbmftfr to {@link
 * #nfwJMXConnfdtor(JMXSfrvidfURL,Mbp)
 * JMXConnfdtorFbdtory.nfwJMXConnfdtor}, if tifrf wbs onf.
 * Additionblly, if tif
 * <dodf>jmx.rfmotf.protodol.providfr.dlbss.lobdfr</dodf> kfy is not
 * prfsfnt in tif <dodf>fnvironmfnt</dodf> pbrbmftfr, it is bddfd to
 * tif nfw rfbd-only <dodf>Mbp</dodf>.  Tif bssodibtfd vbluf is tif
 * dblling tirfbd's dontfxt dlbss lobdfr.</p>
 *
 * @sindf 1.5
 */
publid dlbss JMXConnfdtorFbdtory {

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif dffbult dlbss
     * lobdfr. Tiis dlbss lobdfr is usfd to dfsfriblizf rfturn vblufs bnd
     * fxdfptions from rfmotf <dodf>MBfbnSfrvfrConnfdtion</dodf>
     * dblls.  Tif vbluf bssodibtfd witi tiis bttributf is bn instbndf
     * of {@link ClbssLobdfr}.</p>
     */
    publid stbtid finbl String DEFAULT_CLASS_LOADER =
        "jmx.rfmotf.dffbult.dlbss.lobdfr";

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif providfr pbdkbgfs
     * tibt brf donsultfd wifn looking for tif ibndlfr for b protodol.
     * Tif vbluf bssodibtfd witi tiis bttributf is b string witi
     * pbdkbgf nbmfs sfpbrbtfd by vfrtidbl bbrs (<dodf>|</dodf>).</p>
     */
    publid stbtid finbl String PROTOCOL_PROVIDER_PACKAGES =
        "jmx.rfmotf.protodol.providfr.pkgs";

    /**
     * <p>Nbmf of tif bttributf tibt spfdififs tif dlbss
     * lobdfr for lobding protodol providfrs.
     * Tif vbluf bssodibtfd witi tiis bttributf is bn instbndf
     * of {@link ClbssLobdfr}.</p>
     */
    publid stbtid finbl String PROTOCOL_PROVIDER_CLASS_LOADER =
        "jmx.rfmotf.protodol.providfr.dlbss.lobdfr";

    privbtf stbtid finbl String PROTOCOL_PROVIDER_DEFAULT_PACKAGE =
        "dom.sun.jmx.rfmotf.protodol";

    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.misd", "JMXConnfdtorFbdtory");

    /** Tifrf brf no instbndfs of tiis dlbss.  */
    privbtf JMXConnfdtorFbdtory() {
    }

    /**
     * <p>Crfbtfs b donnfdtion to tif donnfdtor sfrvfr bt tif givfn
     * bddrfss.</p>
     *
     * <p>Tiis mftiod is fquivblfnt to {@link
     * #donnfdt(JMXSfrvidfURL,Mbp) donnfdt(sfrvidfURL, null)}.</p>
     *
     * @pbrbm sfrvidfURL tif bddrfss of tif donnfdtor sfrvfr to
     * donnfdt to.
     *
     * @rfturn b <dodf>JMXConnfdtor</dodf> wiosf {@link
     * JMXConnfdtor#donnfdt donnfdt} mftiod ibs bffn dbllfd.
     *
     * @fxdfption NullPointfrExdfption if <dodf>sfrvidfURL</dodf> is null.
     *
     * @fxdfption IOExdfption if tif donnfdtor dlifnt or tif
     * donnfdtion dbnnot bf mbdf bfdbusf of b dommunidbtion problfm.
     *
     * @fxdfption SfdurityExdfption if tif donnfdtion dbnnot bf mbdf
     * for sfdurity rfbsons.
     */
    publid stbtid JMXConnfdtor donnfdt(JMXSfrvidfURL sfrvidfURL)
            tirows IOExdfption {
        rfturn donnfdt(sfrvidfURL, null);
    }

    /**
     * <p>Crfbtfs b donnfdtion to tif donnfdtor sfrvfr bt tif givfn
     * bddrfss.</p>
     *
     * <p>Tiis mftiod is fquivblfnt to:</p>
     *
     * <prf>
     * JMXConnfdtor donn = JMXConnfdtorFbdtory.nfwJMXConnfdtor(sfrvidfURL,
     *                                                         fnvironmfnt);
     * donn.donnfdt(fnvironmfnt);
     * </prf>
     *
     * @pbrbm sfrvidfURL tif bddrfss of tif donnfdtor sfrvfr to donnfdt to.
     *
     * @pbrbm fnvironmfnt b sft of bttributfs to dftfrminf iow tif
     * donnfdtion is mbdf.  Tiis pbrbmftfr dbn bf null.  Kfys in tiis
     * mbp must bf Strings.  Tif bppropribtf typf of fbdi bssodibtfd
     * vbluf dfpfnds on tif bttributf.  Tif dontfnts of
     * <dodf>fnvironmfnt</dodf> brf not dibngfd by tiis dbll.
     *
     * @rfturn b <dodf>JMXConnfdtor</dodf> rfprfsfnting tif nfwly-mbdf
     * donnfdtion.  Ebdi suddfssful dbll to tiis mftiod produdfs b
     * difffrfnt objfdt.
     *
     * @fxdfption NullPointfrExdfption if <dodf>sfrvidfURL</dodf> is null.
     *
     * @fxdfption IOExdfption if tif donnfdtor dlifnt or tif
     * donnfdtion dbnnot bf mbdf bfdbusf of b dommunidbtion problfm.
     *
     * @fxdfption SfdurityExdfption if tif donnfdtion dbnnot bf mbdf
     * for sfdurity rfbsons.
     */
    publid stbtid JMXConnfdtor donnfdt(JMXSfrvidfURL sfrvidfURL,
                                       Mbp<String,?> fnvironmfnt)
            tirows IOExdfption {
        if (sfrvidfURL == null)
            tirow nfw NullPointfrExdfption("Null JMXSfrvidfURL");
        JMXConnfdtor donn = nfwJMXConnfdtor(sfrvidfURL, fnvironmfnt);
        donn.donnfdt(fnvironmfnt);
        rfturn donn;
    }

    privbtf stbtid <K,V> Mbp<K,V> nfwHbsiMbp() {
        rfturn nfw HbsiMbp<K,V>();
    }

    privbtf stbtid <K> Mbp<K,Objfdt> nfwHbsiMbp(Mbp<K,?> mbp) {
        rfturn nfw HbsiMbp<K,Objfdt>(mbp);
    }

    /**
     * <p>Crfbtfs b donnfdtor dlifnt for tif donnfdtor sfrvfr bt tif
     * givfn bddrfss.  Tif rfsultbnt dlifnt is not donnfdtfd until its
     * {@link JMXConnfdtor#donnfdt(Mbp) donnfdt} mftiod is dbllfd.</p>
     *
     * @pbrbm sfrvidfURL tif bddrfss of tif donnfdtor sfrvfr to donnfdt to.
     *
     * @pbrbm fnvironmfnt b sft of bttributfs to dftfrminf iow tif
     * donnfdtion is mbdf.  Tiis pbrbmftfr dbn bf null.  Kfys in tiis
     * mbp must bf Strings.  Tif bppropribtf typf of fbdi bssodibtfd
     * vbluf dfpfnds on tif bttributf.  Tif dontfnts of
     * <dodf>fnvironmfnt</dodf> brf not dibngfd by tiis dbll.
     *
     * @rfturn b <dodf>JMXConnfdtor</dodf> rfprfsfnting tif nfw
     * donnfdtor dlifnt.  Ebdi suddfssful dbll to tiis mftiod produdfs
     * b difffrfnt objfdt.
     *
     * @fxdfption NullPointfrExdfption if <dodf>sfrvidfURL</dodf> is null.
     *
     * @fxdfption IOExdfption if tif donnfdtor dlifnt dbnnot bf mbdf
     * bfdbusf of b dommunidbtion problfm.
     *
     * @fxdfption MblformfdURLExdfption if tifrf is no providfr for tif
     * protodol in <dodf>sfrvidfURL</dodf>.
     *
     * @fxdfption JMXProvidfrExdfption if tifrf is b providfr for tif
     * protodol in <dodf>sfrvidfURL</dodf> but it dbnnot bf usfd for
     * somf rfbson.
     */
    publid stbtid JMXConnfdtor nfwJMXConnfdtor(JMXSfrvidfURL sfrvidfURL,
                                               Mbp<String,?> fnvironmfnt)
            tirows IOExdfption {

        finbl Mbp<String,Objfdt> fnvdopy;
        if (fnvironmfnt == null)
            fnvdopy = nfwHbsiMbp();
        flsf {
            EnvHflp.difdkAttributfs(fnvironmfnt);
            fnvdopy = nfwHbsiMbp(fnvironmfnt);
        }

        finbl ClbssLobdfr lobdfr = rfsolvfClbssLobdfr(fnvdopy);
        finbl Clbss<JMXConnfdtorProvidfr> tbrgftIntfrfbdf =
                JMXConnfdtorProvidfr.dlbss;
        finbl String protodol = sfrvidfURL.gftProtodol();
        finbl String providfrClbssNbmf = "ClifntProvidfr";
        finbl JMXSfrvidfURL providfrURL = sfrvidfURL;

        JMXConnfdtorProvidfr providfr = gftProvidfr(providfrURL, fnvdopy,
                                               providfrClbssNbmf,
                                               tbrgftIntfrfbdf,
                                               lobdfr);

        IOExdfption fxdfption = null;
        if (providfr == null) {
            // Lobdfr is null wifn dontfxt dlbss lobdfr is sft to null
            // bnd no lobdfr ibs bffn providfd in mbp.
            // dom.sun.jmx.rfmotf.util.Sfrvidf dlbss fxtrbdtfd from j2sf
            // providfr sfbrdi blgoritim dofsn't ibndlf wfll null dlbsslobdfr.
            if (lobdfr != null) {
                try {
                    JMXConnfdtor donnfdtion =
                        gftConnfdtorAsSfrvidf(lobdfr, providfrURL, fnvdopy);
                    if (donnfdtion != null)
                        rfturn donnfdtion;
                } dbtdi (JMXProvidfrExdfption f) {
                    tirow f;
                } dbtdi (IOExdfption f) {
                    fxdfption = f;
                }
            }
            providfr = gftProvidfr(protodol, PROTOCOL_PROVIDER_DEFAULT_PACKAGE,
                            JMXConnfdtorFbdtory.dlbss.gftClbssLobdfr(),
                            providfrClbssNbmf, tbrgftIntfrfbdf);
        }

        if (providfr == null) {
            MblformfdURLExdfption f =
                nfw MblformfdURLExdfption("Unsupportfd protodol: " + protodol);
            if (fxdfption == null) {
                tirow f;
            } flsf {
                tirow EnvHflp.initCbusf(f, fxdfption);
            }
        }

        finbl Mbp<String,Objfdt> fixfdfnv =
                Collfdtions.unmodifibblfMbp(fnvdopy);

        rfturn providfr.nfwJMXConnfdtor(sfrvidfURL, fixfdfnv);
    }

    privbtf stbtid String rfsolvfPkgs(Mbp<String, ?> fnv)
            tirows JMXProvidfrExdfption {

        Objfdt pkgsObjfdt = null;

        if (fnv != null)
            pkgsObjfdt = fnv.gft(PROTOCOL_PROVIDER_PACKAGES);

        if (pkgsObjfdt == null)
            pkgsObjfdt =
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
                    publid String run() {
                        rfturn Systfm.gftPropfrty(PROTOCOL_PROVIDER_PACKAGES);
                    }
                });

        if (pkgsObjfdt == null)
            rfturn null;

        if (!(pkgsObjfdt instbndfof String)) {
            finbl String msg = "Vbluf of " + PROTOCOL_PROVIDER_PACKAGES +
                " pbrbmftfr is not b String: " +
                pkgsObjfdt.gftClbss().gftNbmf();
            tirow nfw JMXProvidfrExdfption(msg);
        }

        finbl String pkgs = (String) pkgsObjfdt;
        if (pkgs.trim().fqubls(""))
            rfturn null;

        // pkgs mby not dontbin bn fmpty flfmfnt
        if (pkgs.stbrtsWiti("|") || pkgs.fndsWiti("|") ||
            pkgs.indfxOf("||") >= 0) {
            finbl String msg = "Vbluf of " + PROTOCOL_PROVIDER_PACKAGES +
                " dontbins bn fmpty flfmfnt: " + pkgs;
            tirow nfw JMXProvidfrExdfption(msg);
        }

        rfturn pkgs;
    }

    stbtid <T> T gftProvidfr(JMXSfrvidfURL sfrvidfURL,
                             finbl Mbp<String, Objfdt> fnvironmfnt,
                             String providfrClbssNbmf,
                             Clbss<T> tbrgftIntfrfbdf,
                             finbl ClbssLobdfr lobdfr)
            tirows IOExdfption {

        finbl String protodol = sfrvidfURL.gftProtodol();

        finbl String pkgs = rfsolvfPkgs(fnvironmfnt);

        T instbndf = null;

        if (pkgs != null) {
            instbndf =
                gftProvidfr(protodol, pkgs, lobdfr, providfrClbssNbmf,
                            tbrgftIntfrfbdf);

            if (instbndf != null) {
                boolfbn nffdsWrbp = (lobdfr != instbndf.gftClbss().gftClbssLobdfr());
                fnvironmfnt.put(PROTOCOL_PROVIDER_CLASS_LOADER, nffdsWrbp ? wrbp(lobdfr) : lobdfr);
            }
        }

        rfturn instbndf;
    }

    stbtid <T> Itfrbtor<T> gftProvidfrItfrbtor(finbl Clbss<T> providfrClbss,
                                               finbl ClbssLobdfr lobdfr) {
       SfrvidfLobdfr<T> sfrvidfLobdfr =
                SfrvidfLobdfr.lobd(providfrClbss, lobdfr);
       rfturn sfrvidfLobdfr.itfrbtor();
    }

    privbtf stbtid ClbssLobdfr wrbp(finbl ClbssLobdfr pbrfnt) {
        rfturn pbrfnt != null ? AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<ClbssLobdfr>() {
            @Ovfrridf
            publid ClbssLobdfr run() {
                rfturn nfw ClbssLobdfr(pbrfnt) {
                    @Ovfrridf
                    protfdtfd Clbss<?> lobdClbss(String nbmf, boolfbn rfsolvf) tirows ClbssNotFoundExdfption {
                        RfflfdtUtil.difdkPbdkbgfAddfss(nbmf);
                        rfturn supfr.lobdClbss(nbmf, rfsolvf);
                    }
                };
            }
        }) : null;
    }

    privbtf stbtid JMXConnfdtor gftConnfdtorAsSfrvidf(ClbssLobdfr lobdfr,
                                                      JMXSfrvidfURL url,
                                                      Mbp<String, ?> mbp)
        tirows IOExdfption {

        Itfrbtor<JMXConnfdtorProvidfr> providfrs =
                gftProvidfrItfrbtor(JMXConnfdtorProvidfr.dlbss, lobdfr);
        JMXConnfdtor donnfdtion;
        IOExdfption fxdfption = null;
        wiilf (providfrs.ibsNfxt()) {
            JMXConnfdtorProvidfr providfr = providfrs.nfxt();
            try {
                donnfdtion = providfr.nfwJMXConnfdtor(url, mbp);
                rfturn donnfdtion;
            } dbtdi (JMXProvidfrExdfption f) {
                tirow f;
            } dbtdi (Exdfption f) {
                if (loggfr.trbdfOn())
                    loggfr.trbdf("gftConnfdtorAsSfrvidf",
                                 "URL[" + url +
                                 "] Sfrvidf providfr fxdfption: " + f);
                if (!(f instbndfof MblformfdURLExdfption)) {
                    if (fxdfption == null) {
                        if (f instbndfof IOExdfption) {
                            fxdfption = (IOExdfption) f;
                        } flsf {
                            fxdfption = EnvHflp.initCbusf(
                                nfw IOExdfption(f.gftMfssbgf()), f);
                        }
                    }
                }
                dontinuf;
            }
        }
        if (fxdfption == null)
            rfturn null;
        flsf
            tirow fxdfption;
    }

    stbtid <T> T gftProvidfr(String protodol,
                              String pkgs,
                              ClbssLobdfr lobdfr,
                              String providfrClbssNbmf,
                              Clbss<T> tbrgftIntfrfbdf)
            tirows IOExdfption {

        StringTokfnizfr tokfnizfr = nfw StringTokfnizfr(pkgs, "|");

        wiilf (tokfnizfr.ibsMorfTokfns()) {
            String pkg = tokfnizfr.nfxtTokfn();
            String dlbssNbmf = (pkg + "." + protodol2pbdkbgf(protodol) +
                                "." + providfrClbssNbmf);
            Clbss<?> providfrClbss;
            try {
                providfrClbss = Clbss.forNbmf(dlbssNbmf, truf, lobdfr);
            } dbtdi (ClbssNotFoundExdfption f) {
                //Add trbdf.
                dontinuf;
            }

            if (!tbrgftIntfrfbdf.isAssignbblfFrom(providfrClbss)) {
                finbl String msg =
                    "Providfr dlbss dofs not implfmfnt " +
                    tbrgftIntfrfbdf.gftNbmf() + ": " +
                    providfrClbss.gftNbmf();
                tirow nfw JMXProvidfrExdfption(msg);
            }

            // Wf ibvf just provfd tibt tiis dbst is dorrfdt
            Clbss<? fxtfnds T> providfrClbssT = Util.dbst(providfrClbss);
            try {
                rfturn providfrClbssT.nfwInstbndf();
            } dbtdi (Exdfption f) {
                finbl String msg =
                    "Exdfption wifn instbntibting providfr [" + dlbssNbmf +
                    "]";
                tirow nfw JMXProvidfrExdfption(msg, f);
            }
        }

        rfturn null;
    }

    stbtid ClbssLobdfr rfsolvfClbssLobdfr(Mbp<String, ?> fnvironmfnt) {
        ClbssLobdfr lobdfr = null;

        if (fnvironmfnt != null) {
            try {
                lobdfr = (ClbssLobdfr)
                    fnvironmfnt.gft(PROTOCOL_PROVIDER_CLASS_LOADER);
            } dbtdi (ClbssCbstExdfption f) {
                finbl String msg =
                    "Tif ClbssLobdfr supplifd in tif fnvironmfnt mbp using " +
                    "tif " + PROTOCOL_PROVIDER_CLASS_LOADER +
                    " bttributf is not bn instbndf of jbvb.lbng.ClbssLobdfr";
                tirow nfw IllfgblArgumfntExdfption(msg);
            }
        }

        if (lobdfr == null) {
            lobdfr = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
        }

        rfturn lobdfr;
    }

    privbtf stbtid String protodol2pbdkbgf(String protodol) {
        rfturn protodol.rfplbdf('+', '.').rfplbdf('-', '_');
    }
}
