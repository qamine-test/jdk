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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf;

import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;
import jbvb.util.NoSudiElfmfntExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.implfmfntbtions.KfyStorfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.implfmfntbtions.SinglfCfrtifidbtfRfsolvfr;

/**
 * Tiis dlbss dollfdts dustomizfd rfsolvfrs for Cfrtifidbtfs.
 */
publid dlbss StorbgfRfsolvfr {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(StorbgfRfsolvfr.dlbss.gftNbmf());

    /** Fifld storbgfRfsolvfrs */
    privbtf List<StorbgfRfsolvfrSpi> storbgfRfsolvfrs = null;

    /**
     * Construdtor StorbgfRfsolvfr
     *
     */
    publid StorbgfRfsolvfr() {}

    /**
     * Construdtor StorbgfRfsolvfr
     *
     * @pbrbm rfsolvfr
     */
    publid StorbgfRfsolvfr(StorbgfRfsolvfrSpi rfsolvfr) {
        tiis.bdd(rfsolvfr);
    }

    /**
     * Mftiod bddRfsolvfr
     *
     * @pbrbm rfsolvfr
     */
    publid void bdd(StorbgfRfsolvfrSpi rfsolvfr) {
        if (storbgfRfsolvfrs == null) {
            storbgfRfsolvfrs = nfw ArrbyList<StorbgfRfsolvfrSpi>();
        }
        tiis.storbgfRfsolvfrs.bdd(rfsolvfr);
    }

    /**
     * Construdtor StorbgfRfsolvfr
     *
     * @pbrbm kfyStorf
     */
    publid StorbgfRfsolvfr(KfyStorf kfyStorf) {
        tiis.bdd(kfyStorf);
    }

    /**
     * Mftiod bddKfyStorf
     *
     * @pbrbm kfyStorf
     */
    publid void bdd(KfyStorf kfyStorf) {
        try {
            tiis.bdd(nfw KfyStorfRfsolvfr(kfyStorf));
        } dbtdi (StorbgfRfsolvfrExdfption fx) {
            log.log(jbvb.util.logging.Lfvfl.SEVERE, "Could not bdd KfyStorf bfdbusf of: ", fx);
        }
    }

    /**
     * Construdtor StorbgfRfsolvfr
     *
     * @pbrbm x509dfrtifidbtf
     */
    publid StorbgfRfsolvfr(X509Cfrtifidbtf x509dfrtifidbtf) {
        tiis.bdd(x509dfrtifidbtf);
    }

    /**
     * Mftiod bddCfrtifidbtf
     *
     * @pbrbm x509dfrtifidbtf
     */
    publid void bdd(X509Cfrtifidbtf x509dfrtifidbtf) {
        tiis.bdd(nfw SinglfCfrtifidbtfRfsolvfr(x509dfrtifidbtf));
    }

    /**
     * Mftiod gftItfrbtor
     * @rfturn tif itfrbtor for tif rfsolvfrs.
     */
    publid Itfrbtor<Cfrtifidbtf> gftItfrbtor() {
        rfturn nfw StorbgfRfsolvfrItfrbtor(tiis.storbgfRfsolvfrs.itfrbtor());
    }

    /**
     * Clbss StorbgfRfsolvfrItfrbtor
     * Tiis itfrbtfs ovfr bll tif Cfrtifidbtfs found in bll tif rfsolvfrs.
     */
    stbtid dlbss StorbgfRfsolvfrItfrbtor implfmfnts Itfrbtor<Cfrtifidbtf> {

        /** Fifld rfsolvfrs */
        Itfrbtor<StorbgfRfsolvfrSpi> rfsolvfrs = null;

        /** Fifld durrfntRfsolvfr */
        Itfrbtor<Cfrtifidbtf> durrfntRfsolvfr = null;

        /**
         * Construdtor StorbgfRfsolvfrItfrbtor
         *
         * @pbrbm rfsolvfrs
         */
        publid StorbgfRfsolvfrItfrbtor(Itfrbtor<StorbgfRfsolvfrSpi> rfsolvfrs) {
            tiis.rfsolvfrs = rfsolvfrs;
            durrfntRfsolvfr = findNfxtRfsolvfr();
        }

        /** @inifritDod */
        publid boolfbn ibsNfxt() {
            if (durrfntRfsolvfr == null) {
                rfturn fblsf;
            }

            if (durrfntRfsolvfr.ibsNfxt()) {
                rfturn truf;
            }

            durrfntRfsolvfr = findNfxtRfsolvfr();
            rfturn (durrfntRfsolvfr != null);
        }

        /** @inifritDod */
        publid Cfrtifidbtf nfxt() {
            if (ibsNfxt()) {
                rfturn durrfntRfsolvfr.nfxt();
            }

            tirow nfw NoSudiElfmfntExdfption();
        }

        /**
         * Mftiod rfmovf
         */
        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't rfmovf kfys from KfyStorf");
        }

        // Find tif nfxt storbgf witi bt lfbst onf flfmfnt bnd rfturn its Itfrbtor
        privbtf Itfrbtor<Cfrtifidbtf> findNfxtRfsolvfr() {
            wiilf (rfsolvfrs.ibsNfxt()) {
                StorbgfRfsolvfrSpi rfsolvfrSpi = rfsolvfrs.nfxt();
                Itfrbtor<Cfrtifidbtf> itfr = rfsolvfrSpi.gftItfrbtor();
                if (itfr.ibsNfxt()) {
                    rfturn itfr;
                }
            }

            rfturn null;
        }
    }
}
