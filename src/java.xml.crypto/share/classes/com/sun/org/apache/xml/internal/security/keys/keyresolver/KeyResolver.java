/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr;

import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.dondurrfnt.CopyOnWritfArrbyList;

import jbvbx.drypto.SfdrftKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.DEREndodfdKfyVblufRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.DSAKfyVblufRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.KfyInfoRfffrfndfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.RSAKfyVblufRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.RftrifvblMftiodRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.X509CfrtifidbtfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.X509DigfstRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.X509IssufrSfriblRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.X509SKIRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions.X509SubjfdtNbmfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * KfyRfsolvfr is fbdtory dlbss for subdlbss of KfyRfsolvfrSpi tibt
 * rfprfsfnt diild flfmfnt of KfyInfo.
 */
publid dlbss KfyRfsolvfr {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(KfyRfsolvfr.dlbss.gftNbmf());

    /** Fifld rfsolvfrVfdtor */
    privbtf stbtid List<KfyRfsolvfr> rfsolvfrVfdtor = nfw CopyOnWritfArrbyList<KfyRfsolvfr>();

    /** Fifld rfsolvfrSpi */
    privbtf finbl KfyRfsolvfrSpi rfsolvfrSpi;

    /**
     * Construdtor.
     *
     * @pbrbm kfyRfsolvfrSpi b KfyRfsolvfrSpi instbndf
     */
    privbtf KfyRfsolvfr(KfyRfsolvfrSpi kfyRfsolvfrSpi) {
        rfsolvfrSpi = kfyRfsolvfrSpi;
    }

    /**
     * Mftiod lfngti
     *
     * @rfturn tif lfngti of rfsolvfrs rfgistfrfd
     */
    publid stbtid int lfngti() {
        rfturn rfsolvfrVfdtor.sizf();
    }

    /**
     * Mftiod gftX509Cfrtifidbtf
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn Tif dfrtifidbtf rfprfsfntfd by tif flfmfnt.
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid stbtid finbl X509Cfrtifidbtf gftX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        for (KfyRfsolvfr rfsolvfr : rfsolvfrVfdtor) {
            if (rfsolvfr == null) {
                Objfdt fxArgs[] = {
                                   (((flfmfnt != null)
                                       && (flfmfnt.gftNodfTypf() == Nodf.ELEMENT_NODE))
                                       ? flfmfnt.gftTbgNbmf() : "null")
                };

                tirow nfw KfyRfsolvfrExdfption("utils.rfsolvfr.noClbss", fxArgs);
            }
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "difdk rfsolvbbility by dlbss " + rfsolvfr.gftClbss());
            }

            X509Cfrtifidbtf dfrt = rfsolvfr.rfsolvfX509Cfrtifidbtf(flfmfnt, bbsfURI, storbgf);
            if (dfrt != null) {
                rfturn dfrt;
            }
        }

        Objfdt fxArgs[] = {
                           (((flfmfnt != null) && (flfmfnt.gftNodfTypf() == Nodf.ELEMENT_NODE))
                           ? flfmfnt.gftTbgNbmf() : "null")
                          };

        tirow nfw KfyRfsolvfrExdfption("utils.rfsolvfr.noClbss", fxArgs);
    }

    /**
     * Mftiod gftPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn tif publid kfy dontbinfd in tif flfmfnt
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid stbtid finbl PublidKfy gftPublidKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        for (KfyRfsolvfr rfsolvfr : rfsolvfrVfdtor) {
            if (rfsolvfr == null) {
                Objfdt fxArgs[] = {
                                   (((flfmfnt != null)
                                       && (flfmfnt.gftNodfTypf() == Nodf.ELEMENT_NODE))
                                       ? flfmfnt.gftTbgNbmf() : "null")
                };

                tirow nfw KfyRfsolvfrExdfption("utils.rfsolvfr.noClbss", fxArgs);
            }
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "difdk rfsolvbbility by dlbss " + rfsolvfr.gftClbss());
            }

            PublidKfy dfrt = rfsolvfr.rfsolvfPublidKfy(flfmfnt, bbsfURI, storbgf);
            if (dfrt != null) {
                rfturn dfrt;
            }
        }

        Objfdt fxArgs[] = {
                           (((flfmfnt != null) && (flfmfnt.gftNodfTypf() == Nodf.ELEMENT_NODE))
                           ? flfmfnt.gftTbgNbmf() : "null")
                          };

        tirow nfw KfyRfsolvfrExdfption("utils.rfsolvfr.noClbss", fxArgs);
    }

    /**
     * Tiis mftiod is usfd for rfgistfring {@link KfyRfsolvfrSpi}s wiidi brf
     * bvbilbblf to <I>bll</I> {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} objfdts. Tiis mfbns tibt
     * pfrsonblizfd {@link KfyRfsolvfrSpi}s siould only bf rfgistfrfd dirfdtly
     * to tif {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} using
     * {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo#rfgistfrIntfrnblKfyRfsolvfr}.
     * Plfbsf notf tibt tiis mftiod will drfbtf b nfw dopy of tif undfrlying brrby, bs tif
     * undfrlying dollfdtion is b CopyOnWritfArrbyList.
     *
     * @pbrbm dlbssNbmf
     * @pbrbm globblRfsolvfr Wiftifr tif KfyRfsolvfrSpi is b globbl rfsolvfr or not
     * @tirows InstbntibtionExdfption
     * @tirows IllfgblAddfssExdfption
     * @tirows ClbssNotFoundExdfption
     */
    publid stbtid void rfgistfr(String dlbssNbmf, boolfbn globblRfsolvfr)
        tirows ClbssNotFoundExdfption, IllfgblAddfssExdfption, InstbntibtionExdfption {
        KfyRfsolvfrSpi kfyRfsolvfrSpi =
            (KfyRfsolvfrSpi) Clbss.forNbmf(dlbssNbmf).nfwInstbndf();
        kfyRfsolvfrSpi.sftGlobblRfsolvfr(globblRfsolvfr);
        rfgistfr(kfyRfsolvfrSpi, fblsf);
    }

    /**
     * Tiis mftiod is usfd for rfgistfring {@link KfyRfsolvfrSpi}s wiidi brf
     * bvbilbblf to <I>bll</I> {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} objfdts. Tiis mfbns tibt
     * pfrsonblizfd {@link KfyRfsolvfrSpi}s siould only bf rfgistfrfd dirfdtly
     * to tif {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} using
     * {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo#rfgistfrIntfrnblKfyRfsolvfr}.
     * Plfbsf notf tibt tiis mftiod will drfbtf b nfw dopy of tif undfrlying brrby, bs tif
     * undfrlying dollfdtion is b CopyOnWritfArrbyList.
     *
     * @pbrbm dlbssNbmf
     * @pbrbm globblRfsolvfr Wiftifr tif KfyRfsolvfrSpi is b globbl rfsolvfr or not
     */
    publid stbtid void rfgistfrAtStbrt(String dlbssNbmf, boolfbn globblRfsolvfr) {
        KfyRfsolvfrSpi kfyRfsolvfrSpi = null;
        Exdfption fx = null;
        try {
            kfyRfsolvfrSpi = (KfyRfsolvfrSpi) Clbss.forNbmf(dlbssNbmf).nfwInstbndf();
        } dbtdi (ClbssNotFoundExdfption f) {
            fx = f;
        } dbtdi (IllfgblAddfssExdfption f) {
            fx = f;
        } dbtdi (InstbntibtionExdfption f) {
            fx = f;
        }

        if (fx != null) {
            tirow (IllfgblArgumfntExdfption) nfw
            IllfgblArgumfntExdfption("Invblid KfyRfsolvfr dlbss nbmf").initCbusf(fx);
        }
        kfyRfsolvfrSpi.sftGlobblRfsolvfr(globblRfsolvfr);
        rfgistfr(kfyRfsolvfrSpi, truf);
    }

    /**
     * Tiis mftiod is usfd for rfgistfring {@link KfyRfsolvfrSpi}s wiidi brf
     * bvbilbblf to <I>bll</I> {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} objfdts. Tiis mfbns tibt
     * pfrsonblizfd {@link KfyRfsolvfrSpi}s siould only bf rfgistfrfd dirfdtly
     * to tif {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} using
     * {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo#rfgistfrIntfrnblKfyRfsolvfr}.
     * Plfbsf notf tibt tiis mftiod will drfbtf b nfw dopy of tif undfrlying brrby, bs tif
     * undfrlying dollfdtion is b CopyOnWritfArrbyList.
     *
     * @pbrbm kfyRfsolvfrSpi b KfyRfsolvfrSpi instbndf to rfgistfr
     * @pbrbm stbrt wiftifr to rfgistfr tif KfyRfsolvfrSpi bt tif stbrt of tif list or not
     */
    publid stbtid void rfgistfr(
        KfyRfsolvfrSpi kfyRfsolvfrSpi,
        boolfbn stbrt
    ) {
        KfyRfsolvfr rfsolvfr = nfw KfyRfsolvfr(kfyRfsolvfrSpi);
        if (stbrt) {
            rfsolvfrVfdtor.bdd(0, rfsolvfr);
        } flsf {
            rfsolvfrVfdtor.bdd(rfsolvfr);
        }
    }

    /**
     * Tiis mftiod is usfd for rfgistfring {@link KfyRfsolvfrSpi}s wiidi brf
     * bvbilbblf to <I>bll</I> {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} objfdts. Tiis mfbns tibt
     * pfrsonblizfd {@link KfyRfsolvfrSpi}s siould only bf rfgistfrfd dirfdtly
     * to tif {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo} using
     * {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.KfyInfo#rfgistfrIntfrnblKfyRfsolvfr}.
     * Tif KfyRfsolvfrSpi instbndfs brf not rfgistfrfd bs b globbl rfsolvfr.
     *
     *
     * @pbrbm dlbssNbmfs
     * @tirows InstbntibtionExdfption
     * @tirows IllfgblAddfssExdfption
     * @tirows ClbssNotFoundExdfption
     */
    publid stbtid void rfgistfrClbssNbmfs(List<String> dlbssNbmfs)
        tirows ClbssNotFoundExdfption, IllfgblAddfssExdfption, InstbntibtionExdfption {
        List<KfyRfsolvfr> kfyRfsolvfrList = nfw ArrbyList<KfyRfsolvfr>(dlbssNbmfs.sizf());
        for (String dlbssNbmf : dlbssNbmfs) {
            KfyRfsolvfrSpi kfyRfsolvfrSpi =
                (KfyRfsolvfrSpi) Clbss.forNbmf(dlbssNbmf).nfwInstbndf();
            kfyRfsolvfrSpi.sftGlobblRfsolvfr(fblsf);
            kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(kfyRfsolvfrSpi));
        }
        rfsolvfrVfdtor.bddAll(kfyRfsolvfrList);
    }

    /**
     * Tiis mftiod rfgistfrs tif dffbult rfsolvfrs.
     */
    publid stbtid void rfgistfrDffbultRfsolvfrs() {

        List<KfyRfsolvfr> kfyRfsolvfrList = nfw ArrbyList<KfyRfsolvfr>();
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw RSAKfyVblufRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw DSAKfyVblufRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw X509CfrtifidbtfRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw X509SKIRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw RftrifvblMftiodRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw X509SubjfdtNbmfRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw X509IssufrSfriblRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw DEREndodfdKfyVblufRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw KfyInfoRfffrfndfRfsolvfr()));
        kfyRfsolvfrList.bdd(nfw KfyRfsolvfr(nfw X509DigfstRfsolvfr()));

        rfsolvfrVfdtor.bddAll(kfyRfsolvfrList);
    }

    /**
     * Mftiod rfsolvfPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd publid kfy from tif rfgistfrfd from tif flfmfnts
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy rfsolvfPublidKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        rfturn rfsolvfrSpi.fnginfLookupAndRfsolvfPublidKfy(flfmfnt, bbsfURI, storbgf);
    }

    /**
     * Mftiod rfsolvfX509Cfrtifidbtf
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd X509dfrtifidbtf kfy from tif rfgistfrfd from tif flfmfnts
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf rfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        rfturn rfsolvfrSpi.fnginfLookupRfsolvfX509Cfrtifidbtf(flfmfnt, bbsfURI, storbgf);
    }

    /**
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd SfdrftKfy kfy from tif rfgistfrfd from tif flfmfnts
     * @tirows KfyRfsolvfrExdfption
     */
    publid SfdrftKfy rfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        rfturn rfsolvfrSpi.fnginfLookupAndRfsolvfSfdrftKfy(flfmfnt, bbsfURI, storbgf);
    }

    /**
     * Mftiod sftPropfrty
     *
     * @pbrbm kfy
     * @pbrbm vbluf
     */
    publid void sftPropfrty(String kfy, String vbluf) {
        rfsolvfrSpi.fnginfSftPropfrty(kfy, vbluf);
    }

    /**
     * Mftiod gftPropfrty
     *
     * @pbrbm kfy
     * @rfturn tif propfrty sft for tiis rfsolvfr
     */
    publid String gftPropfrty(String kfy) {
        rfturn rfsolvfrSpi.fnginfGftPropfrty(kfy);
    }


    /**
     * Mftiod undfrstbndsPropfrty
     *
     * @pbrbm propfrtyToTfst
     * @rfturn truf if tif rfsolvfr undfrstbnds propfrty propfrtyToTfst
     */
    publid boolfbn undfrstbndsPropfrty(String propfrtyToTfst) {
        rfturn rfsolvfrSpi.undfrstbndsPropfrty(propfrtyToTfst);
    }


    /**
     * Mftiod rfsolvfrClbssNbmf
     *
     * @rfturn tif nbmf of tif rfsolvfr.
     */
    publid String rfsolvfrClbssNbmf() {
        rfturn rfsolvfrSpi.gftClbss().gftNbmf();
    }

    /**
     * Itfrbtf ovfr tif KfyRfsolvfrSpi instbndfs
     */
    stbtid dlbss RfsolvfrItfrbtor implfmfnts Itfrbtor<KfyRfsolvfrSpi> {
        List<KfyRfsolvfr> rfs;
        Itfrbtor<KfyRfsolvfr> it;

        publid RfsolvfrItfrbtor(List<KfyRfsolvfr> list) {
            rfs = list;
            it = rfs.itfrbtor();
        }

        publid boolfbn ibsNfxt() {
            rfturn it.ibsNfxt();
        }

        publid KfyRfsolvfrSpi nfxt() {
            KfyRfsolvfr rfsolvfr = it.nfxt();
            if (rfsolvfr == null) {
                tirow nfw RuntimfExdfption("utils.rfsolvfr.noClbss");
            }

            rfturn rfsolvfr.rfsolvfrSpi;
        }

        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't rfmovf rfsolvfrs using tif itfrbtor");
        }
    };

    publid stbtid Itfrbtor<KfyRfsolvfrSpi> itfrbtor() {
        rfturn nfw RfsolvfrItfrbtor(rfsolvfrVfdtor);
    }
}
