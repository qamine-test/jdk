/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.KfyStorf;
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
 * Rfsolvfs b SfdrftKfy witiin b KfyStorf bbsfd on tif KfyNbmf.
 * Tif KfyNbmf is tif kfy fntry blibs witiin tif KfyStorf.
 */
publid dlbss SfdrftKfyRfsolvfr fxtfnds KfyRfsolvfrSpi
{
    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(SfdrftKfyRfsolvfr.dlbss.gftNbmf());

    privbtf KfyStorf kfyStorf;
    privbtf dibr[] pbssword;

    /**
     * Construdtor.
     */
    publid SfdrftKfyRfsolvfr(KfyStorf kfyStorf, dibr[] pbssword) {
        tiis.kfyStorf = kfyStorf;
        tiis.pbssword = pbssword;
    }

    /**
     * Tiis mftiod rfturns wiftifr tif KfyRfsolvfrSpi is bblf to pfrform tif rfqufstfd bdtion.
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
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

        if (XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYNAME)) {
            String kfyNbmf = flfmfnt.gftFirstCiild().gftNodfVbluf();
            try {
                Kfy kfy = kfyStorf.gftKfy(kfyNbmf, pbssword);
                if (kfy instbndfof SfdrftKfy) {
                    rfturn (SfdrftKfy) kfy;
                }
            } dbtdi (Exdfption f) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnnot rfdovfr tif kfy", f);
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
        rfturn null;
    }
}
