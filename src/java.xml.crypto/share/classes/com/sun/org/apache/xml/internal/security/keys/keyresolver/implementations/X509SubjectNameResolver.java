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
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Itfrbtor;


import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509SubjfdtNbmf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

publid dlbss X509SubjfdtNbmfRfsolvfr fxtfnds KfyRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(X509SubjfdtNbmfRfsolvfr.dlbss.gftNbmf());


    /**
     * Mftiod fnginfRfsolvfPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     * @rfturn null if no {@link PublidKfy} dould bf obtbinfd
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {

        X509Cfrtifidbtf dfrt =
            tiis.fnginfLookupRfsolvfX509Cfrtifidbtf(flfmfnt, bbsfURI, storbgf);

        if (dfrt != null) {
            rfturn dfrt.gftPublidKfy();
        }

        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfX509Cfrtifidbtf
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf() + "?");
        }
        Elfmfnt[] x509diildNodfs = null;
        XMLX509SubjfdtNbmf x509diildObjfdt[] = null;

        if (!XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_X509DATA)) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dbn't");
            }
            rfturn null;
        }
        x509diildNodfs =
            XMLUtils.sflfdtDsNodfs(flfmfnt.gftFirstCiild(), Constbnts._TAG_X509SUBJECTNAME);

        if (!((x509diildNodfs != null)
            && (x509diildNodfs.lfngti > 0))) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "I dbn't");
            }
            rfturn null;
        }

        try {
            if (storbgf == null) {
                Objfdt fxArgs[] = { Constbnts._TAG_X509SUBJECTNAME };
                KfyRfsolvfrExdfption fx =
                    nfw KfyRfsolvfrExdfption("KfyRfsolvfr.nffdStorbgfRfsolvfr", fxArgs);

                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "", fx);
                }

                tirow fx;
            }

            x509diildObjfdt = nfw XMLX509SubjfdtNbmf[x509diildNodfs.lfngti];

            for (int i = 0; i < x509diildNodfs.lfngti; i++) {
                x509diildObjfdt[i] = nfw XMLX509SubjfdtNbmf(x509diildNodfs[i], bbsfURI);
            }

            Itfrbtor<Cfrtifidbtf> storbgfItfrbtor = storbgf.gftItfrbtor();
            wiilf (storbgfItfrbtor.ibsNfxt()) {
                X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf)storbgfItfrbtor.nfxt();
                XMLX509SubjfdtNbmf dfrtSN =
                    nfw XMLX509SubjfdtNbmf(flfmfnt.gftOwnfrDodumfnt(), dfrt);

                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Found Cfrtifidbtf SN: " + dfrtSN.gftSubjfdtNbmf());
                }

                for (int i = 0; i < x509diildObjfdt.lfngti; i++) {
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "Found Elfmfnt SN:     "
                              + x509diildObjfdt[i].gftSubjfdtNbmf());
                    }

                    if (dfrtSN.fqubls(x509diildObjfdt[i])) {
                        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "mbtdi !!! ");
                        }

                        rfturn dfrt;
                    }
                    if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                        log.log(jbvb.util.logging.Lfvfl.FINE, "no mbtdi...");
                    }
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
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     *
     */
    publid jbvbx.drypto.SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) {
        rfturn null;
    }
}
