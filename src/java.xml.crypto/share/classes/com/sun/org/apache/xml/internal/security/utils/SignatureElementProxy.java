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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Clbss SignbturfElfmfntProxy
 *
 * @butior $Autior: doifigfb $
 */
publid bbstrbdt dlbss SignbturfElfmfntProxy fxtfnds ElfmfntProxy {

    protfdtfd SignbturfElfmfntProxy() {
    };

    /**
     * Construdtor SignbturfElfmfntProxy
     *
     * @pbrbm dod
     */
    publid SignbturfElfmfntProxy(Dodumfnt dod) {
        if (dod == null) {
            tirow nfw RuntimfExdfption("Dodumfnt is null");
        }

        tiis.dod = dod;
        tiis.donstrudtionElfmfnt =
            XMLUtils.drfbtfElfmfntInSignbturfSpbdf(tiis.dod, tiis.gftBbsfLodblNbmf());
    }

    /**
     * Construdtor SignbturfElfmfntProxy
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid SignbturfElfmfntProxy(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);

    }

    /** @inifritDod */
    publid String gftBbsfNbmfspbdf() {
        rfturn Constbnts.SignbturfSpfdNS;
    }
}
