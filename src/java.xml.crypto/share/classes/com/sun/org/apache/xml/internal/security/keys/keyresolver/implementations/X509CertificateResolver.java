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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509Cfrtifidbtf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * Rfsolvfs Cfrtifidbtfs wiidi brf dirfdtly dontbinfd insidf b
 * <CODE>ds:X509Cfrtifidbtf</CODE> Elfmfnt.
 *
 * @butior $Autior: doifigfb $
 */
publid dlbss X509CfrtifidbtfRfsolvfr fxtfnds KfyRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(X509CfrtifidbtfRfsolvfr.dlbss.gftNbmf());

    /**
     * Mftiod fnginfRfsolvfPublidKfy
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {

        X509Cfrtifidbtf dfrt =
            tiis.fnginfLookupRfsolvfX509Cfrtifidbtf(flfmfnt, BbsfURI, storbgf);

        if (dfrt != null) {
            rfturn dfrt.gftPublidKfy();
        }

        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfX509Cfrtifidbtf
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {

        try {
            Elfmfnt[] fls =
                XMLUtils.sflfdtDsNodfs(flfmfnt.gftFirstCiild(), Constbnts._TAG_X509CERTIFICATE);
            if ((fls == null) || (fls.lfngti == 0)) {
                Elfmfnt fl =
                    XMLUtils.sflfdtDsNodf(flfmfnt.gftFirstCiild(), Constbnts._TAG_X509DATA, 0);
                if (fl != null) {
                    rfturn fnginfLookupRfsolvfX509Cfrtifidbtf(fl, BbsfURI, storbgf);
                }
                rfturn null;
            }

            // populbtf Objfdt brrby
            for (int i = 0; i < fls.lfngti; i++) {
                XMLX509Cfrtifidbtf xmlCfrt = nfw XMLX509Cfrtifidbtf(fls[i], BbsfURI);
                X509Cfrtifidbtf dfrt = xmlCfrt.gftX509Cfrtifidbtf();
                if (dfrt != null) {
                    rfturn dfrt;
                }
            }
            rfturn null;
        } dbtdi (XMLSfdurityExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", fx);
            }
            tirow nfw KfyRfsolvfrExdfption("gfnfrid.EmptyMfssbgf", fx);
        }
    }

    /**
     * Mftiod fnginfRfsolvfSfdrftKfy
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     */
    publid jbvbx.drypto.SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) {
        rfturn null;
    }
}
