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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt;

import jbvb.sfdurity.PublidKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.kfyvblufs.DSAKfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.kfyvblufs.RSAKfyVbluf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Tif KfyVbluf flfmfnt dontbins b singlf publid kfy tibt mby bf usfful in
 * vblidbting tif signbturf. Strudturfd formbts for dffining DSA (REQUIRED)
 * bnd RSA (RECOMMENDED) publid kfys brf dffinfd in Signbturf Algoritims
 * (sfdtion 6.4). Tif KfyVbluf flfmfnt mby indludf fxtfrnblly dffinfd publid
 * kfys vblufs rfprfsfntfd bs PCDATA or flfmfnt typfs from bn fxtfrnbl
 * nbmfspbdf.
 *
 * @butior $Autior: doifigfb $
 */
publid dlbss KfyVbluf fxtfnds SignbturfElfmfntProxy implfmfnts KfyInfoContfnt {

    /**
     * Construdtor KfyVbluf
     *
     * @pbrbm dod
     * @pbrbm dsbKfyVbluf
     */
    publid KfyVbluf(Dodumfnt dod, DSAKfyVbluf dsbKfyVbluf) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        tiis.donstrudtionElfmfnt.bppfndCiild(dsbKfyVbluf.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Construdtor KfyVbluf
     *
     * @pbrbm dod
     * @pbrbm rsbKfyVbluf
     */
    publid KfyVbluf(Dodumfnt dod, RSAKfyVbluf rsbKfyVbluf) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        tiis.donstrudtionElfmfnt.bppfndCiild(rsbKfyVbluf.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Construdtor KfyVbluf
     *
     * @pbrbm dod
     * @pbrbm unknownKfyVbluf
     */
    publid KfyVbluf(Dodumfnt dod, Elfmfnt unknownKfyVbluf) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        tiis.donstrudtionElfmfnt.bppfndCiild(unknownKfyVbluf);
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Construdtor KfyVbluf
     *
     * @pbrbm dod
     * @pbrbm pk
     */
    publid KfyVbluf(Dodumfnt dod, PublidKfy pk) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);

        if (pk instbndfof jbvb.sfdurity.intfrfbdfs.DSAPublidKfy) {
            DSAKfyVbluf dsb = nfw DSAKfyVbluf(tiis.dod, pk);

            tiis.donstrudtionElfmfnt.bppfndCiild(dsb.gftElfmfnt());
            XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        } flsf if (pk instbndfof jbvb.sfdurity.intfrfbdfs.RSAPublidKfy) {
            RSAKfyVbluf rsb = nfw RSAKfyVbluf(tiis.dod, pk);

            tiis.donstrudtionElfmfnt.bppfndCiild(rsb.gftElfmfnt());
            XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        }
    }

    /**
     * Construdtor KfyVbluf
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid KfyVbluf(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     * Mftiod gftPublidKfy
     *
     * @rfturn tif publid kfy
     * @tirows XMLSfdurityExdfption
     */
    publid PublidKfy gftPublidKfy() tirows XMLSfdurityExdfption {
        Elfmfnt rsb =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_RSAKEYVALUE, 0);

        if (rsb != null) {
            RSAKfyVbluf kv = nfw RSAKfyVbluf(rsb, tiis.bbsfURI);
            rfturn kv.gftPublidKfy();
        }

        Elfmfnt dsb =
            XMLUtils.sflfdtDsNodf(
                tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_DSAKEYVALUE, 0);

        if (dsb != null) {
            DSAKfyVbluf kv = nfw DSAKfyVbluf(dsb, tiis.bbsfURI);
            rfturn kv.gftPublidKfy();
        }

        rfturn null;
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_KEYVALUE;
    }
}
