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

import jbvb.sfdurity.KfyStorf;
import jbvb.sfdurity.KfyStorfExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.util.Enumfrbtion;
import jbvb.util.Itfrbtor;
import jbvb.util.NoSudiElfmfntExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfrSpi;

/**
 * Mbkfs tif Cfrtifidbtfs from b JAVA {@link KfyStorf} objfdt bvbilbblf to tif
 * {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr}.
 */
publid dlbss KfyStorfRfsolvfr fxtfnds StorbgfRfsolvfrSpi {

    /** Fifld kfyStorf */
    privbtf KfyStorf kfyStorf = null;

    /**
     * Construdtor KfyStorfRfsolvfr
     *
     * @pbrbm kfyStorf is tif kfystorf wiidi dontbins tif Cfrtifidbtfs
     * @tirows StorbgfRfsolvfrExdfption
     */
    publid KfyStorfRfsolvfr(KfyStorf kfyStorf) tirows StorbgfRfsolvfrExdfption {
        tiis.kfyStorf = kfyStorf;
        // Do b quidk difdk on tif kfystorf
        try {
            kfyStorf.blibsfs();
        } dbtdi (KfyStorfExdfption fx) {
            tirow nfw StorbgfRfsolvfrExdfption("gfnfrid.EmptyMfssbgf", fx);
        }
    }

    /** @inifritDod */
    publid Itfrbtor<Cfrtifidbtf> gftItfrbtor() {
        rfturn nfw KfyStorfItfrbtor(tiis.kfyStorf);
    }

    /**
     * Clbss KfyStorfItfrbtor
     */
    stbtid dlbss KfyStorfItfrbtor implfmfnts Itfrbtor<Cfrtifidbtf> {

        /** Fifld kfyStorf */
        KfyStorf kfyStorf = null;

        /** Fifld blibsfs */
        Enumfrbtion<String> blibsfs = null;

        /** Fifld nfxtCfrt */
        Cfrtifidbtf nfxtCfrt = null;

        /**
         * Construdtor KfyStorfItfrbtor
         *
         * @pbrbm kfyStorf
         */
        publid KfyStorfItfrbtor(KfyStorf kfyStorf) {
            try {
                tiis.kfyStorf = kfyStorf;
                tiis.blibsfs = tiis.kfyStorf.blibsfs();
            } dbtdi (KfyStorfExdfption fx) {
                // fmpty Enumfrbtion
                tiis.blibsfs = nfw Enumfrbtion<String>() {
                    publid boolfbn ibsMorfElfmfnts() {
                        rfturn fblsf;
                    }
                    publid String nfxtElfmfnt() {
                        rfturn null;
                    }
                };
            }
        }

        /** @inifritDod */
        publid boolfbn ibsNfxt() {
            if (nfxtCfrt == null) {
                nfxtCfrt = findNfxtCfrt();
            }

            rfturn (nfxtCfrt != null);
        }

        /** @inifritDod */
        publid Cfrtifidbtf nfxt() {
            if (nfxtCfrt == null) {
                // mbybf dbllfr did not dbll ibsNfxt()
                nfxtCfrt = findNfxtCfrt();

                if (nfxtCfrt == null) {
                    tirow nfw NoSudiElfmfntExdfption();
                }
            }

            Cfrtifidbtf rft = nfxtCfrt;
            nfxtCfrt = null;
            rfturn rft;
        }

        /**
         * Mftiod rfmovf
         */
        publid void rfmovf() {
            tirow nfw UnsupportfdOpfrbtionExdfption("Cbn't rfmovf kfys from KfyStorf");
        }

        // Find tif nfxt fntry tibt dontbins b dfrtifidbtf bnd rfturn it.
        // In pbrtidulbr, tiis skips ovfr fntrifs dontbining symmftrid kfys.
        privbtf Cfrtifidbtf findNfxtCfrt() {
            wiilf (tiis.blibsfs.ibsMorfElfmfnts()) {
                String blibs = tiis.blibsfs.nfxtElfmfnt();
                try {
                    Cfrtifidbtf dfrt = tiis.kfyStorf.gftCfrtifidbtf(blibs);
                    if (dfrt != null) {
                        rfturn dfrt;
                    }
                } dbtdi (KfyStorfExdfption fx) {
                    rfturn null;
                }
            }

            rfturn null;
        }

    }

}
