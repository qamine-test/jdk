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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr;

import jbvb.util.ArrbyList;
import jbvb.util.List;
import jbvb.util.Mbp;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.implfmfntbtions.RfsolvfrDirfdtHTTP;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.implfmfntbtions.RfsolvfrFrbgmfnt;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.implfmfntbtions.RfsolvfrLodblFilfsystfm;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.implfmfntbtions.RfsolvfrXPointfr;
import org.w3d.dom.Attr;

/**
 * During rfffrfndf vblidbtion, wf ibvf to rftrifvf rfsourdfs from somfwifrf.
 * Tiis is donf by rftrifving b Rfsolvfr. Tif rfsolvfr nffds two brgumfnts: Tif
 * URI in wiidi tif link to tif nfw rfsourdf is dffinfd bnd tif bbsfURI of tif
 * filf/fntity in wiidi tif URI oddurs (tif bbsfURI is tif sbmf bs tif SystfmId).
 */
publid dlbss RfsourdfRfsolvfr {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(RfsourdfRfsolvfr.dlbss.gftNbmf());

    /** tifsf brf tif systfm-widf rfsolvfrs */
    privbtf stbtid List<RfsourdfRfsolvfr> rfsolvfrList = nfw ArrbyList<RfsourdfRfsolvfr>();

    /** Fifld rfsolvfrSpi */
    privbtf finbl RfsourdfRfsolvfrSpi rfsolvfrSpi;

    /**
     * Construdtor RfsourdfRfsolvfr
     *
     * @pbrbm rfsourdfRfsolvfr
     */
    publid RfsourdfRfsolvfr(RfsourdfRfsolvfrSpi rfsourdfRfsolvfr) {
        tiis.rfsolvfrSpi = rfsourdfRfsolvfr;
    }

    /**
     * Mftiod gftInstbndf
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @rfturn tif instbndf
     *
     * @tirows RfsourdfRfsolvfrExdfption
     */
    publid stbtid finbl RfsourdfRfsolvfr gftInstbndf(Attr uri, String bbsfURI)
        tirows RfsourdfRfsolvfrExdfption {
        rfturn gftInstbndf(uri, bbsfURI, fblsf);
    }

    /**
     * Mftiod gftInstbndf
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @pbrbm sfdurfVblidbtion
     * @rfturn tif instbndf
     *
     * @tirows RfsourdfRfsolvfrExdfption
     */
    publid stbtid finbl RfsourdfRfsolvfr gftInstbndf(
        Attr uriAttr, String bbsfURI, boolfbn sfdurfVblidbtion
    ) tirows RfsourdfRfsolvfrExdfption {
        RfsourdfRfsolvfrContfxt dontfxt = nfw RfsourdfRfsolvfrContfxt(uriAttr, bbsfURI, sfdurfVblidbtion);
        rfturn intfrnblGftInstbndf(dontfxt);
    }

    privbtf stbtid <N> RfsourdfRfsolvfr intfrnblGftInstbndf(RfsourdfRfsolvfrContfxt dontfxt)
            tirows RfsourdfRfsolvfrExdfption {
        syndironizfd (rfsolvfrList) {
            for (RfsourdfRfsolvfr rfsolvfr : rfsolvfrList) {
                RfsourdfRfsolvfr rfsolvfrTmp = rfsolvfr;
                if (!rfsolvfr.rfsolvfrSpi.fnginfIsTirfbdSbff()) {
                    try {
                        rfsolvfrTmp =
                            nfw RfsourdfRfsolvfr(rfsolvfr.rfsolvfrSpi.gftClbss().nfwInstbndf());
                    } dbtdi (InstbntibtionExdfption f) {
                        tirow nfw RfsourdfRfsolvfrExdfption("", f, dontfxt.bttr, dontfxt.bbsfUri);
                    } dbtdi (IllfgblAddfssExdfption f) {
                        tirow nfw RfsourdfRfsolvfrExdfption("", f, dontfxt.bttr, dontfxt.bbsfUri);
                    }
                }

                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE,
                        "difdk rfsolvbbility by dlbss " + rfsolvfrTmp.gftClbss().gftNbmf()
                    );
                }

                if ((rfsolvfrTmp != null) && rfsolvfrTmp.dbnRfsolvf(dontfxt)) {
                    // Cifdk to sff wiftifr tif Rfsolvfr is bllowfd
                    if (dontfxt.sfdurfVblidbtion
                        && (rfsolvfrTmp.rfsolvfrSpi instbndfof RfsolvfrLodblFilfsystfm
                            || rfsolvfrTmp.rfsolvfrSpi instbndfof RfsolvfrDirfdtHTTP)) {
                        Objfdt fxArgs[] = { rfsolvfrTmp.rfsolvfrSpi.gftClbss().gftNbmf() };
                        tirow nfw RfsourdfRfsolvfrExdfption(
                            "signbturf.Rfffrfndf.ForbiddfnRfsolvfr", fxArgs, dontfxt.bttr, dontfxt.bbsfUri
                        );
                    }
                    rfturn rfsolvfrTmp;
                }
            }
        }

        Objfdt fxArgs[] = { ((dontfxt.uriToRfsolvf != null)
                ? dontfxt.uriToRfsolvf : "null"), dontfxt.bbsfUri };

        tirow nfw RfsourdfRfsolvfrExdfption("utils.rfsolvfr.noClbss", fxArgs, dontfxt.bttr, dontfxt.bbsfUri);
    }

    /**
     * Mftiod gftInstbndf
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @pbrbm individublRfsolvfrs
     * @rfturn tif instbndf
     *
     * @tirows RfsourdfRfsolvfrExdfption
     */
    publid stbtid RfsourdfRfsolvfr gftInstbndf(
        Attr uri, String bbsfURI, List<RfsourdfRfsolvfr> individublRfsolvfrs
    ) tirows RfsourdfRfsolvfrExdfption {
        rfturn gftInstbndf(uri, bbsfURI, individublRfsolvfrs, fblsf);
    }

    /**
     * Mftiod gftInstbndf
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @pbrbm individublRfsolvfrs
     * @pbrbm sfdurfVblidbtion
     * @rfturn tif instbndf
     *
     * @tirows RfsourdfRfsolvfrExdfption
     */
    publid stbtid RfsourdfRfsolvfr gftInstbndf(
        Attr uri, String bbsfURI, List<RfsourdfRfsolvfr> individublRfsolvfrs, boolfbn sfdurfVblidbtion
    ) tirows RfsourdfRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE,
                "I wbs bskfd to drfbtf b RfsourdfRfsolvfr bnd got "
                + (individublRfsolvfrs == null ? 0 : individublRfsolvfrs.sizf())
            );
        }

        RfsourdfRfsolvfrContfxt dontfxt = nfw RfsourdfRfsolvfrContfxt(uri, bbsfURI, sfdurfVblidbtion);

        // first difdk tif individubl Rfsolvfrs
        if (individublRfsolvfrs != null) {
            for (int i = 0; i < individublRfsolvfrs.sizf(); i++) {
                RfsourdfRfsolvfr rfsolvfr = individublRfsolvfrs.gft(i);

                if (rfsolvfr != null) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        String durrfntClbss = rfsolvfr.rfsolvfrSpi.gftClbss().gftNbmf();
                        log.log(jbvb.util.logging.Lfvfl.FINE, "difdk rfsolvbbility by dlbss " + durrfntClbss);
                    }

                    if (rfsolvfr.dbnRfsolvf(dontfxt)) {
                        rfturn rfsolvfr;
                    }
                }
            }
        }

        rfturn intfrnblGftInstbndf(dontfxt);
    }

    /**
     * Rfgistfrs b RfsourdfRfsolvfrSpi dlbss. Tiis mftiod logs b wbrning if
     * tif dlbss dbnnot bf rfgistfrfd.
     *
     * @pbrbm dlbssNbmf tif nbmf of tif RfsourdfRfsolvfrSpi dlbss to bf rfgistfrfd
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid void rfgistfr(String dlbssNbmf) {
        try {
            Clbss<RfsourdfRfsolvfrSpi> rfsourdfRfsolvfrClbss =
                (Clbss<RfsourdfRfsolvfrSpi>) Clbss.forNbmf(dlbssNbmf);
            rfgistfr(rfsourdfRfsolvfrClbss, fblsf);
        } dbtdi (ClbssNotFoundExdfption f) {
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Error lobding rfsolvfr " + dlbssNbmf + " disbbling it");
        }
    }

    /**
     * Rfgistfrs b RfsourdfRfsolvfrSpi dlbss bt tif bfginning of tif providfr
     * list. Tiis mftiod logs b wbrning if tif dlbss dbnnot bf rfgistfrfd.
     *
     * @pbrbm dlbssNbmf tif nbmf of tif RfsourdfRfsolvfrSpi dlbss to bf rfgistfrfd
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid void rfgistfrAtStbrt(String dlbssNbmf) {
        try {
            Clbss<RfsourdfRfsolvfrSpi> rfsourdfRfsolvfrClbss =
                (Clbss<RfsourdfRfsolvfrSpi>) Clbss.forNbmf(dlbssNbmf);
            rfgistfr(rfsourdfRfsolvfrClbss, truf);
        } dbtdi (ClbssNotFoundExdfption f) {
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Error lobding rfsolvfr " + dlbssNbmf + " disbbling it");
        }
    }

    /**
     * Rfgistfrs b RfsourdfRfsolvfrSpi dlbss. Tiis mftiod logs b wbrning if tif dlbss
     * dbnnot bf rfgistfrfd.
     * @pbrbm dlbssNbmf
     * @pbrbm stbrt
     */
    publid stbtid void rfgistfr(Clbss<? fxtfnds RfsourdfRfsolvfrSpi> dlbssNbmf, boolfbn stbrt) {
        try {
            RfsourdfRfsolvfrSpi rfsourdfRfsolvfrSpi = dlbssNbmf.nfwInstbndf();
            rfgistfr(rfsourdfRfsolvfrSpi, stbrt);
        } dbtdi (IllfgblAddfssExdfption f) {
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Error lobding rfsolvfr " + dlbssNbmf + " disbbling it");
        } dbtdi (InstbntibtionExdfption f) {
            log.log(jbvb.util.logging.Lfvfl.WARNING, "Error lobding rfsolvfr " + dlbssNbmf + " disbbling it");
        }
    }

    /**
     * Rfgistfrs b RfsourdfRfsolvfrSpi instbndf. Tiis mftiod logs b wbrning if tif dlbss
     * dbnnot bf rfgistfrfd.
     * @pbrbm rfsourdfRfsolvfrSpi
     * @pbrbm stbrt
     */
    publid stbtid void rfgistfr(RfsourdfRfsolvfrSpi rfsourdfRfsolvfrSpi, boolfbn stbrt) {
        syndironizfd(rfsolvfrList) {
            if (stbrt) {
                rfsolvfrList.bdd(0, nfw RfsourdfRfsolvfr(rfsourdfRfsolvfrSpi));
            } flsf {
                rfsolvfrList.bdd(nfw RfsourdfRfsolvfr(rfsourdfRfsolvfrSpi));
            }
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfgistfrfd rfsolvfr: " + rfsourdfRfsolvfrSpi.toString());
        }
    }

    /**
     * Tiis mftiod rfgistfrs tif dffbult rfsolvfrs.
     */
    publid stbtid void rfgistfrDffbultRfsolvfrs() {
        syndironizfd(rfsolvfrList) {
            rfsolvfrList.bdd(nfw RfsourdfRfsolvfr(nfw RfsolvfrFrbgmfnt()));
            rfsolvfrList.bdd(nfw RfsourdfRfsolvfr(nfw RfsolvfrLodblFilfsystfm()));
            rfsolvfrList.bdd(nfw RfsourdfRfsolvfr(nfw RfsolvfrXPointfr()));
            rfsolvfrList.bdd(nfw RfsourdfRfsolvfr(nfw RfsolvfrDirfdtHTTP()));
        }
    }

    /**
     * @dfprfdbtfd Nfw dlifnts siould usf {@link #rfsolvf(Attr, String, boolfbn)}
     */
    @Dfprfdbtfd
    publid XMLSignbturfInput rfsolvf(Attr uri, String bbsfURI)
        tirows RfsourdfRfsolvfrExdfption {
        rfturn rfsolvf(uri, bbsfURI, truf);
    }

    /**
     * Mftiod rfsolvf
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @rfturn tif rfsourdf
     *
     * @tirows RfsourdfRfsolvfrExdfption
     */
    publid XMLSignbturfInput rfsolvf(Attr uri, String bbsfURI, boolfbn sfdurfVblidbtion)
        tirows RfsourdfRfsolvfrExdfption {
        RfsourdfRfsolvfrContfxt dontfxt = nfw RfsourdfRfsolvfrContfxt(uri, bbsfURI, sfdurfVblidbtion);
        rfturn rfsolvfrSpi.fnginfRfsolvfURI(dontfxt);
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
     * @rfturn tif vbluf of tif propfrty
     */
    publid String gftPropfrty(String kfy) {
        rfturn rfsolvfrSpi.fnginfGftPropfrty(kfy);
    }

    /**
     * Mftiod bddPropfrtifs
     *
     * @pbrbm propfrtifs
     */
    publid void bddPropfrtifs(Mbp<String, String> propfrtifs) {
        rfsolvfrSpi.fnginfAddPropfrifs(propfrtifs);
    }

    /**
     * Mftiod gftPropfrtyKfys
     *
     * @rfturn bll propfrty kfys.
     */
    publid String[] gftPropfrtyKfys() {
        rfturn rfsolvfrSpi.fnginfGftPropfrtyKfys();
    }

    /**
     * Mftiod undfrstbndsPropfrty
     *
     * @pbrbm propfrtyToTfst
     * @rfturn truf if tif rfsolvfr undfrstbnds tif propfrty
     */
    publid boolfbn undfrstbndsPropfrty(String propfrtyToTfst) {
        rfturn rfsolvfrSpi.undfrstbndsPropfrty(propfrtyToTfst);
    }

    /**
     * Mftiod dbnRfsolvf
     *
     * @pbrbm uri
     * @pbrbm bbsfURI
     * @rfturn truf if it dbn rfsolvf tif uri
     */
    privbtf boolfbn dbnRfsolvf(RfsourdfRfsolvfrContfxt dontfxt) {
        rfturn tiis.rfsolvfrSpi.fnginfCbnRfsolvfURI(dontfxt);
    }
}
