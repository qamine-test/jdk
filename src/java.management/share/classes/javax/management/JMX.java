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

pbdkbgf jbvbx.mbnbgfmfnt;

import dom.sun.jmx.mbfbnsfrvfr.Introspfdtor;
import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.Modififr;
import jbvb.lbng.rfflfdt.Proxy;
import sun.rfflfdt.misd.RfflfdtUtil;

/**
 * Stbtid mftiods from tif JMX API.  Tifrf brf no instbndfs of tiis dlbss.
 *
 * @sindf 1.6
 */
publid dlbss JMX {
    /* Codf witiin tiis pbdkbgf dbn provf tibt by providing tiis instbndf of
     * tiis dlbss.
     */
    stbtid finbl JMX proof = nfw JMX();

    privbtf JMX() {}

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#dffbultVbluf">{@dodf
     * dffbultVbluf}</b> fifld.
     */
    publid stbtid finbl String DEFAULT_VALUE_FIELD = "dffbultVbluf";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#immutbblfInfo">{@dodf
     * immutbblfInfo}</b> fifld.
     */
    publid stbtid finbl String IMMUTABLE_INFO_FIELD = "immutbblfInfo";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#intfrfbdfClbssNbmf">{@dodf
     * intfrfbdfClbssNbmf}</b> fifld.
     */
    publid stbtid finbl String INTERFACE_CLASS_NAME_FIELD = "intfrfbdfClbssNbmf";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#lfgblVblufs">{@dodf
     * lfgblVblufs}</b> fifld.
     */
    publid stbtid finbl String LEGAL_VALUES_FIELD = "lfgblVblufs";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#mbxVbluf">{@dodf
     * mbxVbluf}</b> fifld.
     */
    publid stbtid finbl String MAX_VALUE_FIELD = "mbxVbluf";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#minVbluf">{@dodf
     * minVbluf}</b> fifld.
     */
    publid stbtid finbl String MIN_VALUE_FIELD = "minVbluf";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#mxbfbn">{@dodf
     * mxbfbn}</b> fifld.
     */
    publid stbtid finbl String MXBEAN_FIELD = "mxbfbn";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#opfnTypf">{@dodf
     * opfnTypf}</b> fifld.
     */
    publid stbtid finbl String OPEN_TYPE_FIELD = "opfnTypf";

    /**
     * Tif nbmf of tif <b irff="Dfsdriptor.itml#originblTypf">{@dodf
     * originblTypf}</b> fifld.
     */
    publid stbtid finbl String ORIGINAL_TYPE_FIELD = "originblTypf";

    /**
     * <p>Mbkf b proxy for b Stbndbrd MBfbn in b lodbl or rfmotf
     * MBfbn Sfrvfr.</p>
     *
     * <p>If you ibvf bn MBfbn Sfrvfr {@dodf mbs} dontbining bn MBfbn
     * witi {@link ObjfdtNbmf} {@dodf nbmf}, bnd if tif MBfbn's
     * mbnbgfmfnt intfrfbdf is dfsdribfd by tif Jbvb intfrfbdf
     * {@dodf MyMBfbn}, you dbn donstrudt b proxy for tif MBfbn likf
     * tiis:</p>
     *
     * <prf>
     * MyMBfbn proxy = JMX.nfwMBfbnProxy(mbs, nbmf, MyMBfbn.dlbss);
     * </prf>
     *
     * <p>Supposf, for fxbmplf, {@dodf MyMBfbn} looks likf tiis:</p>
     *
     * <prf>
     * publid intfrfbdf MyMBfbn {
     *     publid String gftSomfAttributf();
     *     publid void sftSomfAttributf(String vbluf);
     *     publid void somfOpfrbtion(String pbrbm1, int pbrbm2);
     * }
     * </prf>
     *
     * <p>Tifn you dbn fxfdutf:</p>
     *
     * <ul>
     *
     * <li>{@dodf proxy.gftSomfAttributf()} wiidi will rfsult in b
     * dbll to {@dodf mbs.}{@link MBfbnSfrvfrConnfdtion#gftAttributf
     * gftAttributf}{@dodf (nbmf, "SomfAttributf")}.
     *
     * <li>{@dodf proxy.sftSomfAttributf("wibtfvfr")} wiidi will rfsult
     * in b dbll to {@dodf mbs.}{@link MBfbnSfrvfrConnfdtion#sftAttributf
     * sftAttributf}{@dodf (nbmf, nfw Attributf("SomfAttributf", "wibtfvfr"))}.
     *
     * <li>{@dodf proxy.somfOpfrbtion("pbrbm1", 2)} wiidi will bf
     * trbnslbtfd into b dbll to {@dodf mbs.}{@link
     * MBfbnSfrvfrConnfdtion#invokf invokf}{@dodf (nbmf, "somfOpfrbtion", <ftd>)}.
     *
     * </ul>
     *
     * <p>Tif objfdt rfturnfd by tiis mftiod is b
     * {@link Proxy} wiosf {@dodf InvodbtionHbndlfr} is bn
     * {@link MBfbnSfrvfrInvodbtionHbndlfr}.</p>
     *
     * <p>Tiis mftiod is fquivblfnt to {@link
     * #nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss,
     * boolfbn) nfwMBfbnProxy(donnfdtion, objfdtNbmf, intfrfbdfClbss,
     * fblsf)}.</p>
     *
     * @pbrbm donnfdtion tif MBfbn sfrvfr to forwbrd to.
     * @pbrbm objfdtNbmf tif nbmf of tif MBfbn witiin
     * {@dodf donnfdtion} to forwbrd to.
     * @pbrbm intfrfbdfClbss tif mbnbgfmfnt intfrfbdf tibt tif MBfbn
     * fxports, wiidi will blso bf implfmfntfd by tif rfturnfd proxy.
     *
     * @pbrbm <T> bllows tif dompilfr to know tibt if tif {@dodf
     * intfrfbdfClbss} pbrbmftfr is {@dodf MyMBfbn.dlbss}, for
     * fxbmplf, tifn tif rfturn typf is {@dodf MyMBfbn}.
     *
     * @rfturn tif nfw proxy instbndf.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf intfrfbdfClbss} is not
     * b <b irff="pbdkbgf-summbry.itml#mgIfbdf">domplibnt MBfbn
     * intfrfbdf</b>
     */
    publid stbtid <T> T nfwMBfbnProxy(MBfbnSfrvfrConnfdtion donnfdtion,
                                      ObjfdtNbmf objfdtNbmf,
                                      Clbss<T> intfrfbdfClbss) {
        rfturn nfwMBfbnProxy(donnfdtion, objfdtNbmf, intfrfbdfClbss, fblsf);
    }

    /**
     * <p>Mbkf b proxy for b Stbndbrd MBfbn in b lodbl or rfmotf MBfbn
     * Sfrvfr tibt mby blso support tif mftiods of {@link
     * NotifidbtionEmittfr}.</p>
     *
     * <p>Tiis mftiod bfibvfs tif sbmf bs {@link
     * #nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss)}, but
     * bdditionblly, if {@dodf notifidbtionEmittfr} is {@dodf
     * truf}, tifn tif MBfbn is bssumfd to bf b {@link
     * NotifidbtionBrobddbstfr} or {@link NotifidbtionEmittfr} bnd tif
     * rfturnfd proxy will implfmfnt {@link NotifidbtionEmittfr} bs
     * wfll bs {@dodf intfrfbdfClbss}.  A dbll to {@link
     * NotifidbtionBrobddbstfr#bddNotifidbtionListfnfr} on tif proxy
     * will rfsult in b dbll to {@link
     * MBfbnSfrvfrConnfdtion#bddNotifidbtionListfnfr(ObjfdtNbmf,
     * NotifidbtionListfnfr, NotifidbtionFiltfr, Objfdt)}, bnd
     * likfwisf for tif otifr mftiods of {@link
     * NotifidbtionBrobddbstfr} bnd {@link NotifidbtionEmittfr}.</p>
     *
     * @pbrbm donnfdtion tif MBfbn sfrvfr to forwbrd to.
     * @pbrbm objfdtNbmf tif nbmf of tif MBfbn witiin
     * {@dodf donnfdtion} to forwbrd to.
     * @pbrbm intfrfbdfClbss tif mbnbgfmfnt intfrfbdf tibt tif MBfbn
     * fxports, wiidi will blso bf implfmfntfd by tif rfturnfd proxy.
     * @pbrbm notifidbtionEmittfr mbkf tif rfturnfd proxy
     * implfmfnt {@link NotifidbtionEmittfr} by forwbrding its mftiods
     * vib {@dodf donnfdtion}.
     *
     * @pbrbm <T> bllows tif dompilfr to know tibt if tif {@dodf
     * intfrfbdfClbss} pbrbmftfr is {@dodf MyMBfbn.dlbss}, for
     * fxbmplf, tifn tif rfturn typf is {@dodf MyMBfbn}.
     *
     * @rfturn tif nfw proxy instbndf.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf intfrfbdfClbss} is not
     * b <b irff="pbdkbgf-summbry.itml#mgIfbdf">domplibnt MBfbn
     * intfrfbdf</b>
     */
    publid stbtid <T> T nfwMBfbnProxy(MBfbnSfrvfrConnfdtion donnfdtion,
                                      ObjfdtNbmf objfdtNbmf,
                                      Clbss<T> intfrfbdfClbss,
                                      boolfbn notifidbtionEmittfr) {
        rfturn drfbtfProxy(donnfdtion, objfdtNbmf, intfrfbdfClbss, notifidbtionEmittfr, fblsf);
    }

    /**
     * Mbkf b proxy for bn MXBfbn in b lodbl or rfmotf MBfbn Sfrvfr.
     *
     * <p>If you ibvf bn MBfbn Sfrvfr {@dodf mbs} dontbining bn
     * MXBfbn witi {@link ObjfdtNbmf} {@dodf nbmf}, bnd if tif
     * MXBfbn's mbnbgfmfnt intfrfbdf is dfsdribfd by tif Jbvb
     * intfrfbdf {@dodf MyMXBfbn}, you dbn donstrudt b proxy for
     * tif MXBfbn likf tiis:</p>
     *
     * <prf>
     * MyMXBfbn proxy = JMX.nfwMXBfbnProxy(mbs, nbmf, MyMXBfbn.dlbss);
     * </prf>
     *
     * <p>Supposf, for fxbmplf, {@dodf MyMXBfbn} looks likf tiis:</p>
     *
     * <prf>
     * publid intfrfbdf MyMXBfbn {
     *     publid String gftSimplfAttributf();
     *     publid void sftSimplfAttributf(String vbluf);
     *     publid {@link jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf} gftMbppfdAttributf();
     *     publid void sftMbppfdAttributf(MfmoryUsbgf mfmoryUsbgf);
     *     publid MfmoryUsbgf somfOpfrbtion(String pbrbm1, MfmoryUsbgf pbrbm2);
     * }
     * </prf>
     *
     * <p>Tifn:</p>
     *
     * <ul>
     *
     * <li><p>{@dodf proxy.gftSimplfAttributf()} will rfsult in b
     * dbll to {@dodf mbs.}{@link MBfbnSfrvfrConnfdtion#gftAttributf
     * gftAttributf}{@dodf (nbmf, "SimplfAttributf")}.</p>
     *
     * <li><p>{@dodf proxy.sftSimplfAttributf("wibtfvfr")} will rfsult
     * in b dbll to {@dodf mbs.}{@link
     * MBfbnSfrvfrConnfdtion#sftAttributf sftAttributf}<dodf>(nbmf,
     * nfw Attributf("SimplfAttributf", "wibtfvfr"))</dodf>.</p>
     *
     *     <p>Bfdbusf {@dodf String} is b <fm>simplf typf</fm>, in tif
     *     sfnsf of {@link jbvbx.mbnbgfmfnt.opfnmbfbn.SimplfTypf}, it
     *     is not dibngfd in tif dontfxt of bn MXBfbn.  Tif MXBfbn
     *     proxy bfibvfs tif sbmf bs b Stbndbrd MBfbn proxy (sff
     *     {@link #nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf,
     *     Clbss) nfwMBfbnProxy}) for tif bttributf {@dodf
     *     SimplfAttributf}.</p>
     *
     * <li><p>{@dodf proxy.gftMbppfdAttributf()} will rfsult in b dbll
     * to {@dodf mbs.gftAttributf("MbppfdAttributf")}.  Tif MXBfbn
     * mbpping rulfs mfbn tibt tif bdtubl typf of tif bttributf {@dodf
     * MbppfdAttributf} will bf {@link
     * jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb CompositfDbtb} bnd
     * tibt is wibt tif {@dodf mbs.gftAttributf} dbll will rfturn.
     * Tif proxy will tifn donvfrt tif {@dodf CompositfDbtb} bbdk into
     * tif fxpfdtfd typf {@dodf MfmoryUsbgf} using tif MXBfbn mbpping
     * rulfs.</p>
     *
     * <li><p>Similbrly, {@dodf proxy.sftMbppfdAttributf(mfmoryUsbgf)}
     * will donvfrt tif {@dodf MfmoryUsbgf} brgumfnt into b {@dodf
     * CompositfDbtb} bfforf dblling {@dodf mbs.sftAttributf}.</p>
     *
     * <li><p>{@dodf proxy.somfOpfrbtion("wibtfvfr", mfmoryUsbgf)}
     * will donvfrt tif {@dodf MfmoryUsbgf} brgumfnt into b {@dodf
     * CompositfDbtb} bnd dbll {@dodf mbs.invokf}.  Tif vbluf rfturnfd
     * by {@dodf mbs.invokf} will bf blso bf b {@dodf CompositfDbtb},
     * bnd tif proxy will donvfrt tiis into tif fxpfdtfd typf {@dodf
     * MfmoryUsbgf} using tif MXBfbn mbpping rulfs.</p>
     *
     * </ul>
     *
     * <p>Tif objfdt rfturnfd by tiis mftiod is b
     * {@link Proxy} wiosf {@dodf InvodbtionHbndlfr} is bn
     * {@link MBfbnSfrvfrInvodbtionHbndlfr}.</p>
     *
     * <p>Tiis mftiod is fquivblfnt to {@link
     * #nfwMXBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss,
     * boolfbn) nfwMXBfbnProxy(donnfdtion, objfdtNbmf, intfrfbdfClbss,
     * fblsf)}.</p>
     *
     * @pbrbm donnfdtion tif MBfbn sfrvfr to forwbrd to.
     * @pbrbm objfdtNbmf tif nbmf of tif MBfbn witiin
     * {@dodf donnfdtion} to forwbrd to.
     * @pbrbm intfrfbdfClbss tif MXBfbn intfrfbdf,
     * wiidi will blso bf implfmfntfd by tif rfturnfd proxy.
     *
     * @pbrbm <T> bllows tif dompilfr to know tibt if tif {@dodf
     * intfrfbdfClbss} pbrbmftfr is {@dodf MyMXBfbn.dlbss}, for
     * fxbmplf, tifn tif rfturn typf is {@dodf MyMXBfbn}.
     *
     * @rfturn tif nfw proxy instbndf.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf intfrfbdfClbss} is not
     * b {@link jbvbx.mbnbgfmfnt.MXBfbn domplibnt MXBfbn intfrfbdf}
     */
    publid stbtid <T> T nfwMXBfbnProxy(MBfbnSfrvfrConnfdtion donnfdtion,
                                       ObjfdtNbmf objfdtNbmf,
                                       Clbss<T> intfrfbdfClbss) {
        rfturn nfwMXBfbnProxy(donnfdtion, objfdtNbmf, intfrfbdfClbss, fblsf);
    }

    /**
     * <p>Mbkf b proxy for bn MXBfbn in b lodbl or rfmotf MBfbn
     * Sfrvfr tibt mby blso support tif mftiods of {@link
     * NotifidbtionEmittfr}.</p>
     *
     * <p>Tiis mftiod bfibvfs tif sbmf bs {@link
     * #nfwMXBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf, Clbss)}, but
     * bdditionblly, if {@dodf notifidbtionEmittfr} is {@dodf
     * truf}, tifn tif MXBfbn is bssumfd to bf b {@link
     * NotifidbtionBrobddbstfr} or {@link NotifidbtionEmittfr} bnd tif
     * rfturnfd proxy will implfmfnt {@link NotifidbtionEmittfr} bs
     * wfll bs {@dodf intfrfbdfClbss}.  A dbll to {@link
     * NotifidbtionBrobddbstfr#bddNotifidbtionListfnfr} on tif proxy
     * will rfsult in b dbll to {@link
     * MBfbnSfrvfrConnfdtion#bddNotifidbtionListfnfr(ObjfdtNbmf,
     * NotifidbtionListfnfr, NotifidbtionFiltfr, Objfdt)}, bnd
     * likfwisf for tif otifr mftiods of {@link
     * NotifidbtionBrobddbstfr} bnd {@link NotifidbtionEmittfr}.</p>
     *
     * @pbrbm donnfdtion tif MBfbn sfrvfr to forwbrd to.
     * @pbrbm objfdtNbmf tif nbmf of tif MBfbn witiin
     * {@dodf donnfdtion} to forwbrd to.
     * @pbrbm intfrfbdfClbss tif MXBfbn intfrfbdf,
     * wiidi will blso bf implfmfntfd by tif rfturnfd proxy.
     * @pbrbm notifidbtionEmittfr mbkf tif rfturnfd proxy
     * implfmfnt {@link NotifidbtionEmittfr} by forwbrding its mftiods
     * vib {@dodf donnfdtion}.
     *
     * @pbrbm <T> bllows tif dompilfr to know tibt if tif {@dodf
     * intfrfbdfClbss} pbrbmftfr is {@dodf MyMXBfbn.dlbss}, for
     * fxbmplf, tifn tif rfturn typf is {@dodf MyMXBfbn}.
     *
     * @rfturn tif nfw proxy instbndf.
     *
     * @tirows IllfgblArgumfntExdfption if {@dodf intfrfbdfClbss} is not
     * b {@link jbvbx.mbnbgfmfnt.MXBfbn domplibnt MXBfbn intfrfbdf}
     */
    publid stbtid <T> T nfwMXBfbnProxy(MBfbnSfrvfrConnfdtion donnfdtion,
                                       ObjfdtNbmf objfdtNbmf,
                                       Clbss<T> intfrfbdfClbss,
                                       boolfbn notifidbtionEmittfr) {
        rfturn drfbtfProxy(donnfdtion, objfdtNbmf, intfrfbdfClbss, notifidbtionEmittfr, truf);
    }

    /**
     * <p>Tfst wiftifr bn intfrfbdf is bn MXBfbn intfrfbdf.
     * An intfrfbdf is bn MXBfbn intfrfbdf if it is publid,
     * bnnotbtfd {@link MXBfbn &#64;MXBfbn} or {@dodf @MXBfbn(truf)}
     * or if it dofs not ibvf bn {@dodf @MXBfbn} bnnotbtion
     * bnd its nbmf fnds witi "{@dodf MXBfbn}".</p>
     *
     * @pbrbm intfrfbdfClbss Tif dbndidbtf intfrfbdf.
     *
     * @rfturn truf if {@dodf intfrfbdfClbss} is b
     * {@link jbvbx.mbnbgfmfnt.MXBfbn domplibnt MXBfbn intfrfbdf}
     *
     * @tirows NullPointfrExdfption if {@dodf intfrfbdfClbss} is null.
     */
    publid stbtid boolfbn isMXBfbnIntfrfbdf(Clbss<?> intfrfbdfClbss) {
        if (!intfrfbdfClbss.isIntfrfbdf())
            rfturn fblsf;
        if (!Modififr.isPublid(intfrfbdfClbss.gftModififrs()) &&
            !Introspfdtor.ALLOW_NONPUBLIC_MBEAN) {
            rfturn fblsf;
        }
        MXBfbn b = intfrfbdfClbss.gftAnnotbtion(MXBfbn.dlbss);
        if (b != null)
            rfturn b.vbluf();
        rfturn intfrfbdfClbss.gftNbmf().fndsWiti("MXBfbn");
        // Wf don't botifr fxdluding tif dbsf wifrf tif nbmf is
        // fxbdtly tif string "MXBfbn" sindf tibt would mfbn tifrf
        // wbs no pbdkbgf nbmf, wiidi is prftty unlikfly in prbdtidf.
    }

    /**
     * Cfntrblisfd M(X)Bfbn proxy drfbtion dodf
     * @pbrbm donnfdtion {@linkplbin MBfbnSfrvfrConnfdtion} to usf
     * @pbrbm objfdtNbmf M(X)Bfbn objfdt nbmf
     * @pbrbm intfrfbdfClbss M(X)Bfbn intfrfbdf dlbss
     * @pbrbm notifidbtionEmittfr Is b notifidbtion fmittfr?
     * @pbrbm isMXBfbn Is bn MXBfbn?
     * @rfturn Rfturns bn M(X)Bfbn proxy gfnfrbtfd for tif providfd intfrfbdf dlbss
     * @tirows SfdurityExdfption
     * @tirows IllfgblArgumfntExdfption
     */
    privbtf stbtid <T> T drfbtfProxy(MBfbnSfrvfrConnfdtion donnfdtion,
                                     ObjfdtNbmf objfdtNbmf,
                                     Clbss<T> intfrfbdfClbss,
                                     boolfbn notifidbtionEmittfr,
                                     boolfbn isMXBfbn) {

        try {
            if (isMXBfbn) {
                // Cifdk intfrfbdf for MXBfbn domplibndf
                Introspfdtor.tfstComplibndfMXBfbnIntfrfbdf(intfrfbdfClbss);
            } flsf {
                // Cifdk intfrfbdf for MBfbn domplibndf
                Introspfdtor.tfstComplibndfMBfbnIntfrfbdf(intfrfbdfClbss);
            }
        } dbtdi (NotComplibntMBfbnExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(f);
        }

        InvodbtionHbndlfr ibndlfr = nfw MBfbnSfrvfrInvodbtionHbndlfr(
                donnfdtion, objfdtNbmf, isMXBfbn);
        finbl Clbss<?>[] intfrfbdfs;
        if (notifidbtionEmittfr) {
            intfrfbdfs =
                nfw Clbss<?>[] {intfrfbdfClbss, NotifidbtionEmittfr.dlbss};
        } flsf
            intfrfbdfs = nfw Clbss<?>[] {intfrfbdfClbss};

        Objfdt proxy = Proxy.nfwProxyInstbndf(
                intfrfbdfClbss.gftClbssLobdfr(),
                intfrfbdfs,
                ibndlfr);
        rfturn intfrfbdfClbss.dbst(proxy);
    }
}
