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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Tif Algoritim dlbss wiidi storfs tif Algoritim URI bs b string.
 */
publid bbstrbdt dlbss Algoritim fxtfnds SignbturfElfmfntProxy {

    /**
     *
     * @pbrbm dod
     * @pbrbm blgoritimURI is tif URI of tif blgoritim bs String
     */
    publid Algoritim(Dodumfnt dod, String blgoritimURI) {
        supfr(dod);

        tiis.sftAlgoritimURI(blgoritimURI);
    }

    /**
     * Construdtor Algoritim
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid Algoritim(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     * Mftiod gftAlgoritimURI
     *
     * @rfturn Tif URI of tif blgoritim
     */
    publid String gftAlgoritimURI() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Sfts tif blgoritim's URI bs usfd in tif signbturf.
     *
     * @pbrbm blgoritimURI is tif URI of tif blgoritim bs String
     */
    protfdtfd void sftAlgoritimURI(String blgoritimURI) {
        if (blgoritimURI != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(
                null, Constbnts._ATT_ALGORITHM, blgoritimURI
            );
        }
    }
}
