/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.implfmfntbtions;

import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

import jbvbx.drypto.SfdrftKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.DEREndodfdKfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

/**
 * KfyRfsolvfrSpi implfmfntbtion wiidi rfsolvfs publid kfys from b
 * <dodf>dsig11:DEREndodfdKfyVbluf</dodf> flfmfnt.
 *
 * @butior Brfnt Putmbn (putmbnb@gforgftown.fdu)
 */
publid dlbss DEREndodfdKfyVblufRfsolvfr fxtfnds KfyRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(DEREndodfdKfyVblufRfsolvfr.dlbss.gftNbmf());

    /** {@inifritDod}. */
    publid boolfbn fnginfCbnRfsolvf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf) {
        rfturn XMLUtils.flfmfntIsInSignbturf11Spbdf(flfmfnt, Constbnts._TAG_DERENCODEDKEYVALUE);
    }

    /** {@inifritDod}. */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbn I rfsolvf " + flfmfnt.gftTbgNbmf());
        }

        if (!fnginfCbnRfsolvf(flfmfnt, bbsfURI, storbgf)) {
            rfturn null;
        }

        try {
            DEREndodfdKfyVbluf dfrKfyVbluf = nfw DEREndodfdKfyVbluf(flfmfnt, bbsfURI);
            rfturn dfrKfyVbluf.gftPublidKfy();
        } dbtdi (XMLSfdurityExdfption f) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "XMLSfdurityExdfption", f);
            }
        }

        rfturn null;
    }

    /** {@inifritDod}. */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {
        rfturn null;
    }

    /** {@inifritDod}. */
    publid SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {
        rfturn null;
    }

    /** {@inifritDod}. */
    publid PrivbtfKfy fnginfLookupAndRfsolvfPrivbtfKfy(Elfmfnt flfmfnt, String bbsfURI, StorbgfRfsolvfr storbgf)
        tirows KfyRfsolvfrExdfption {
        rfturn null;
    }



}
