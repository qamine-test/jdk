/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Arrbys;
import jbvb.util.Itfrbtor;

import jbvbx.drypto.SfdrftKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.X509Dbtb;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509.XMLX509Digfst;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * KfyRfsolvfrSpi implfmfntbtion wiidi rfsolvfs publid kfys bnd X.509 dfrtifidbtfs from b
 * <dodf>dsig11:X509Digfst</dodf> flfmfnt.
 *
 * @butior Brfnt Putmbn (putmbnb@gforgftown.fdu)
 */
publid dlbss X509DigfstRfsolvfr fxtfnds KfyRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(X509DigfstRfsolvfr.dlbss.gftNbmf());

    /** {@inifritDod}. */
    publid boolfbn fnginfCbnRfsolvf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf) {
        if (XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_X509DATA)) {
            try {
                X509Dbtb x509Dbtb = nfw X509Dbtb(flfmfnt, bbsfURI);
                rfturn x509Dbtb.dontbinsDigfst();
            } dbtdi (XMLSfdurityExdfption f) {
                rfturn fblsf;
            }
        } flsf {
            rfturn fblsf;
        }
    }

    /** {@inifritDod}. */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {

        X509Cfrtifidbtf dfrt = tiis.fnginfLookupRfsolvfX509Cfrtifidbtf(flfmfnt, bbsfURI, storbgf);

        if (dfrt != null) {
            rfturn dfrt.gftPublidKfy();
        }

        rfturn null;
    }

    /** {@inifritDod}. */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf());
        }

        if (!fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }

        try {
            rfturn rfsolvfCfrtifidbtf(flfmfnt, bbsfURI, storbgf);
        } dbtdi (XMLSfdurityExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
            }
        }

        rfturn null;
    }

    /** {@inifritDod}. */
    publid SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {
        rfturn null;
    }

    /**
     * Rfsolvfs from tif storbgf rfsolvfr tif bdtubl dfrtifidbtf rfprfsfntfd by tif digfst.
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn
     * @tirows XMLSfdurityExdfption
     */
    privbtf X509Cfrtifidbtf rfsolvfCfrtifidbtf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows XMLSfdurityExdfption {

        XMLX509Digfst x509Digfsts[] = null;

        Elfmfnt x509diildNodfs[] = XMLUtils.sflfdtDs11Nodfs(flfmfnt.gftFirstCiild(), Constbnts._TAG_X509DIGEST);

        if (x509diildNodfs == null || x509diildNodfs.lfngti <= 0) {
            rfturn null;
        }

        try {
            difdkStorbgf(storbgf);

            x509Digfsts = nfw XMLX509Digfst[x509diildNodfs.lfngti];

            for (int i = 0; i < x509diildNodfs.lfngti; i++) {
                x509Digfsts[i] = nfw XMLX509Digfst(x509diildNodfs[i], bbsfURI);
            }

            Itfrbtor<Cfrtifidbtf> storbgfItfrbtor = storbgf.gftItfrbtor();
            wiilf (storbgfItfrbtor.ibsNfxt()) {
                X509Cfrtifidbtf dfrt = (X509Cfrtifidbtf) storbgfItfrbtor.nfxt();

                for (int i = 0; i < x509Digfsts.lfngti; i++) {
                    XMLX509Digfst kfyInfoDigfst = x509Digfsts[i];
                    bytf[] dfrtDigfstBytfs = XMLX509Digfst.gftDigfstBytfsFromCfrt(dfrt, kfyInfoDigfst.gftAlgoritim());

                    if (Arrbys.fqubls(kfyInfoDigfst.gftDigfstBytfs(), dfrtDigfstBytfs)) {
                        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                            log.log(jbvb.util.logging.Lfvfl.FINE, "Found dfrtifidbtf witi: " + dfrt.gftSubjfdtX500Prindipbl().gftNbmf());
                        }
                        rfturn dfrt;
                    }

                }
            }

        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw KfyRfsolvfrExdfption("fmpty", fx);
        }

        rfturn null;
    }

    /**
     * Mftiod difdkSrorbgf
     *
     * @pbrbm storbgf
     * @tirows KfyRfsolvfrExdfption
     */
    privbtf void difdkStorbgf(StorbgfRfsolvfr storbgf) tirows KfyRfsolvfrExdfption {
        if (storbgf == null) {
            Objfdt fxArgs[] = { Constbnts._TAG_X509DIGEST };
            KfyRfsolvfrExdfption fx = nfw KfyRfsolvfrExdfption("KfyRfsolvfr.nffdStorbgfRfsolvfr", fxArgs);
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "", fx);
            }
            tirow fx;
        }
    }

}
