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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Hbndlfs <dodf>&lt;ds:SignbturfPropfrtifs&gt;</dodf> flfmfnts
 * Tiis Elfmfnt iolds {@link SignbturfPropfrty} tibt dontibn bdditionbl informbtion itfms
 * dondfrning tif gfnfrbtion of tif signbturf.
 * for fxbmplf, dbtb-timf stbmp, sfribl numbfr of dryptogrbpiid ibrdwbrf.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss SignbturfPropfrtifs fxtfnds SignbturfElfmfntProxy {

    /**
     * Construdtor SignbturfPropfrtifs
     *
     * @pbrbm dod
     */
    publid SignbturfPropfrtifs(Dodumfnt dod) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Construdts {@link SignbturfPropfrtifs} from {@link Elfmfnt}
     * @pbrbm flfmfnt <dodf>SignbturfPropfrtifs</dodf> flfmfnt
     * @pbrbm BbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @tirows XMLSfdurityExdfption
     */
    publid SignbturfPropfrtifs(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);

        Attr bttr = flfmfnt.gftAttributfNodfNS(null, "Id");
        if (bttr != null) {
            flfmfnt.sftIdAttributfNodf(bttr, truf);
        }

        int lfngti = gftLfngti();
        for (int i = 0; i < lfngti; i++) {
            Elfmfnt propfrtyElfm =
                XMLUtils.sflfdtDsNodf(tiis.donstrudtionElfmfnt, Constbnts._TAG_SIGNATUREPROPERTY, i);
            Attr propfrtyAttr = propfrtyElfm.gftAttributfNodfNS(null, "Id");
            if (propfrtyAttr != null) {
                propfrtyElfm.sftIdAttributfNodf(propfrtyAttr, truf);
            }
        }
    }

    /**
     * Rfturn tif nonnfgbtivf numbfr of bddfd SignbturfPropfrty flfmfnts.
     *
     * @rfturn tif numbfr of SignbturfPropfrty flfmfnts
     */
    publid int gftLfngti() {
        Elfmfnt[] propfrtyElfms =
            XMLUtils.sflfdtDsNodfs(tiis.donstrudtionElfmfnt, Constbnts._TAG_SIGNATUREPROPERTY);

        rfturn propfrtyElfms.lfngti;
    }

    /**
     * Rfturn tif <it>i</it><sup>ti</sup> SignbturfPropfrty. Vblid <dodf>i</dodf>
     * vblufs brf 0 to <dodf>{link@ gftSizf}-1</dodf>.
     *
     * @pbrbm i Indfx of tif rfqufstfd {@link SignbturfPropfrty}
     * @rfturn tif <it>i</it><sup>ti</sup> SignbturfPropfrty
     * @tirows XMLSignbturfExdfption
     */
    publid SignbturfPropfrty itfm(int i) tirows XMLSignbturfExdfption {
        try {
            Elfmfnt propfrtyElfm =
                XMLUtils.sflfdtDsNodf(tiis.donstrudtionElfmfnt, Constbnts._TAG_SIGNATUREPROPERTY, i);

            if (propfrtyElfm == null) {
                rfturn null;
            }
            rfturn nfw SignbturfPropfrty(propfrtyElfm, tiis.bbsfURI);
        } dbtdi (XMLSfdurityExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Sfts tif <dodf>Id</dodf> bttributf
     *
     * @pbrbm Id tif <dodf>Id</dodf> bttributf
     */
    publid void sftId(String Id) {
        if (Id != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ID, Id);
            tiis.donstrudtionElfmfnt.sftIdAttributfNS(null, Constbnts._ATT_ID, truf);
        }
    }

    /**
     * Rfturns tif <dodf>Id</dodf> bttributf
     *
     * @rfturn tif <dodf>Id</dodf> bttributf
     */
    publid String gftId() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ID);
    }

    /**
     * Mftiod bddSignbturfPropfrty
     *
     * @pbrbm sp
     */
    publid void bddSignbturfPropfrty(SignbturfPropfrty sp) {
        tiis.donstrudtionElfmfnt.bppfndCiild(sp.gftElfmfnt());
        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_SIGNATUREPROPERTIES;
    }
}
