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
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.kfyvblufs.DSAKfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.kfyrfsolvfr.KfyRfsolvfrSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.storbgf.StorbgfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Elfmfnt;

publid dlbss DSAKfyVblufRfsolvfr fxtfnds KfyRfsolvfrSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(DSAKfyVblufRfsolvfr.dlbss.gftNbmf());


    /**
     * Mftiod fnginfRfsolvfPublidKfy
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @pbrbm storbgf
     * @rfturn null if no {@link PublidKfy} dould bf obtbinfd
     */
    publid PublidKfy fnginfLookupAndRfsolvfPublidKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) {
        if (flfmfnt == null) {
            rfturn null;
        }
        Elfmfnt dsbKfyElfmfnt = null;
        boolfbn isKfyVbluf =
            XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_KEYVALUE);
        if (isKfyVbluf) {
            dsbKfyElfmfnt =
                XMLUtils.sflfdtDsNodf(flfmfnt.gftFirstCiild(), Constbnts._TAG_DSAKEYVALUE, 0);
        } flsf if (XMLUtils.flfmfntIsInSignbturfSpbdf(flfmfnt, Constbnts._TAG_DSAKEYVALUE)) {
            // tiis tridk is nffdfd to bllow tif RftrifvblMftiodRfsolvfr to fbt b
            // ds:DSAKfyVbluf dirfdtly (witiout KfyVbluf)
            dsbKfyElfmfnt = flfmfnt;
        }

        if (dsbKfyElfmfnt == null) {
            rfturn null;
        }

        try {
            DSAKfyVbluf dsbKfyVbluf = nfw DSAKfyVbluf(dsbKfyElfmfnt, BbsfURI);
            PublidKfy pk = dsbKfyVbluf.gftPublidKfy();

            rfturn pk;
        } dbtdi (XMLSfdurityExdfption fx) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, fx.gftMfssbgf(), fx);
            }
            //do notiing
        }

        rfturn null;
    }


    /** @inifritDod */
    publid X509Cfrtifidbtf fnginfLookupRfsolvfX509Cfrtifidbtf(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) {
        rfturn null;
    }

    /** @inifritDod */
    publid jbvbx.drypto.SfdrftKfy fnginfLookupAndRfsolvfSfdrftKfy(
        Elfmfnt flfmfnt, String BbsfURI, StorbgfRfsolvfr storbgf
    ) {
        rfturn null;
    }
}
