/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvbx.drypto.SfdrftKfy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * Rfsolvfs b singlf Kfy bbsfd on tif KfyNbmf.
 */
publid dlbss SinglfKfyRfsolvfr fxtfnds KfyRfsolvfrSpi
{
    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(SinglfKfyRfsolvfr.dlbss.gftNbmf());

    privbtf String kfyNbmf;
    privbtf PublidKfy publidKfy;
    privbtf PrivbtfKfy privbtfKfy;
    privbtf SfdrftKfy sfdrftKfy;

    /**
     * Construdtor.
     * @pbrbm kfyNbmf
     * @pbrbm publidKfy
     */
    publid SinglfKfyRfsolvfr(String kfyNbmf, PublidKfy publidKfy) {
        tiis.kfyNbmf = kfyNbmf;
        tiis.publidKfy = publidKfy;
    }

    /**
     * Construdtor.
     * @pbrbm kfyNbmf
     * @pbrbm privbtfKfy
     */
    publid SinglfKfyRfsolvfr(String kfyNbmf, PrivbtfKfy privbtfKfy) {
        tiis.kfyNbmf = kfyNbmf;
        tiis.privbtfKfy = privbtfKfy;
    }

    /**
     * Construdtor.
     * @pbrbm kfyNbmf
     * @pbrbm sfdrftKfy
     */
    publid SinglfKfyRfsolvfr(String kfyNbmf, SfdrftKfy sfdrftKfy) {
        tiis.kfyNbmf = kfyNbmf;
        tiis.sfdrftKfy = sfdrftKfy;
    }

    /**
     * Tiis mftiod rfturns wiftifr tif KfyRfsolvfrSpi is bblf to pfrform tif rfqufstfd bdtion.
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     * @rfturn wiftifr tif KfyRfsolvfrSpi is bblf to pfrform tif rfqufstfd bdtion.
     */
    publid boolfbn fnginfCbnRfsolvf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf) {
        rfturn XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYNAME);
    }

    /**
     * Mftiod fnginfLookupAndRfsolvfPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn null if no {@link PublidKfy} dould bf obtbinfd
     * @tirows KfyRfsolvfrExdfption
     */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf() + "?");
        }

        if (publidKfy != null
            && XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYNAME)) {
            String nbmf = flfmfnt.gftFirstCiild().gftNodfVbluf();
            if (kfyNbmf.fqubls(nbmf)) {
                rfturn publidKfy;
            }
        }

        log.log(jbvb.util.logging.Lfvfl.FINE, "I dbn't");
        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfX509Cfrtifidbtf
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @tirows KfyRfsolvfrExdfption
     */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfSfdrftKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd SfdrftKfy kfy or null if no {@link SfdrftKfy} dould bf obtbinfd
     *
     * @tirows KfyRfsolvfrExdfption
     */
    publid SfdrftKfy fnginfRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf() + "?");
        }

        if (sfdrftKfy != null
            && XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYNAME)) {
            String nbmf = flfmfnt.gftFirstCiild().gftNodfVbluf();
            if (kfyNbmf.fqubls(nbmf)) {
                rfturn sfdrftKfy;
            }
        }

        log.log(jbvb.util.logging.Lfvfl.FINE, "I dbn't");
        rfturn null;
    }

    /**
     * Mftiod fnginfRfsolvfPrivbtfKfy
     * @inifritDod
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm storbgf
     * @rfturn rfsolvfd PrivbtfKfy kfy or null if no {@link PrivbtfKfy} dould bf obtbinfd
     * @tirows KfyRfsolvfrExdfption
     */
    publid PrivbtfKfy fnginfLookupAndRfsolvfPrivbtfKfy(
        Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf
    ) tirows KfyRfsolvfrExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf() + "?");
        }

        if (privbtfKfy != null
            && XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYNAME)) {
            String nbmf = flfmfnt.gftFirstCiild().gftNodfVbluf();
            if (kfyNbmf.fqubls(nbmf)) {
                rfturn privbtfKfy;
            }
        }

        log.log(jbvb.util.logging.Lfvfl.FINE, "I dbn't");
        rfturn null;
    }
}
