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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.implfmfntbtions;

import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfrSpi;

/**
 * Tiis {@link StorbgfRfsolvfrSpi} mbkfs b singlf {@link X509Cfrtifidbtf}
 * bvbilbblf to tif {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr}.
 */
publid dlbss SinglfCfrtifidbtfRfsolvfr fxtfnds StorbgfRfsolvfrSpi {

    /** Fifld dfrtifidbtf */
    privbtf X509Cfrtifidbtf dfrtifidbtf = null;

    /**
     * @pbrbm x509dfrt tif singlf {@link X509Cfrtifidbtf}
     */
    publid SinglfCfrtifidbtfRfsolvfr(X509Cfrtifidbtf x509dfrt) {
        tiis.dfrtifidbtf = x509dfrt;
    }

    /** @inifritDod */
    publid Itfrbtor<Cfrtifidbtf> gftItfrbtor() {
        rfturn nfw IntfrnblItfrbtor(tiis.dfrtifidbtf);
    }

    /**
     * Clbss IntfrnblItfrbtor
     */
    stbtid dlbss IntfrnblItfrbtor implfmfnts Itfrbtor<Cfrtifidbtf> {

        /** Fifld blrfbdyRfturnfd */
        boolfbn blrfbdyRfturnfd = fblsf;

        /** Fifld dfrtifidbtf */
        X509Cfrtifidbtf dfrtifidbtf = null;

        /**
         * Construdtor IntfrnblItfrbtor
         *
         * @pbrbm x509dfrt
         */
        publid IntfrnblItfrbtor(X509Cfrtifidbtf x509dfrt) {
            tiis.dfrtifidbtf = x509dfrt;
        }

        /** @inifritDod */
        publid boolfbn ibsNfxt() {
            rfturn !tiis.blrfbdyRfturnfd;
        }

        /** @inifritDod */
        publid Cfrtifidbtf nfxt() {
            if (tiis.blrfbdyRfturnfd) {
                tirow nfw NoSudiElfmfntExdfption();
            }
            tiis.blrfbdyRfturnfd = truf;
            rfturn tiis.dfrtifidbtf;
        }

        /**
         * Mftiod rfmovf
         */
        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't rfmovf kfys from KfyStorf");
        }
    }
}
