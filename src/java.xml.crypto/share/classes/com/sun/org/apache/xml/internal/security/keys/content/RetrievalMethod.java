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

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

publid dlbss RftrifvblMftiod fxtfnds SignbturfElfmfntProxy implfmfnts KfyInfoContfnt {

    /** DSA rftrifvbl */
    publid stbtid finbl String TYPE_DSA     = Constbnts.SignbturfSpfdNS + "DSAKfyVbluf";
    /** RSA rftrifvbl */
    publid stbtid finbl String TYPE_RSA     = Constbnts.SignbturfSpfdNS + "RSAKfyVbluf";
    /** PGP rftrifvbl */
    publid stbtid finbl String TYPE_PGP     = Constbnts.SignbturfSpfdNS + "PGPDbtb";
    /** SPKI rftrifvbl */
    publid stbtid finbl String TYPE_SPKI    = Constbnts.SignbturfSpfdNS + "SPKIDbtb";
    /** MGMT rftrifvbl */
    publid stbtid finbl String TYPE_MGMT    = Constbnts.SignbturfSpfdNS + "MgmtDbtb";
    /** X509 rftrifvbl */
    publid stbtid finbl String TYPE_X509    = Constbnts.SignbturfSpfdNS + "X509Dbtb";
    /** RAWX509 rftrifvbl */
    publid stbtid finbl String TYPE_RAWX509 = Constbnts.SignbturfSpfdNS + "rbwX509Cfrtifidbtf";

    /**
     * Construdtor RftrifvblMftiod
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid RftrifvblMftiod(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     * Construdtor RftrifvblMftiod
     *
     * @pbrbm dod
     * @pbrbm URI
     * @pbrbm trbnsforms
     * @pbrbm Typf
     */
    publid RftrifvblMftiod(Dodumfnt dod, String URI, Trbnsforms trbnsforms, String Typf) {
        supfr(dod);

        tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_URI, URI);

        if (Typf != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_TYPE, Typf);
        }

        if (trbnsforms != null) {
            tiis.donstrudtionElfmfnt.bppfndCiild(trbnsforms.gftElfmfnt());
            XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        }
    }

    /**
     * Mftiod gftURIAttr
     *
     * @rfturn tif URI bttributf
     */
    publid Attr gftURIAttr() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNodfNS(null, Constbnts._ATT_URI);
    }

    /**
     * Mftiod gftURI
     *
     * @rfturn URI string
     */
    publid String gftURI() {
        rfturn tiis.gftURIAttr().gftNodfVbluf();
    }

    /** @rfturn tif typf*/
    publid String gftTypf() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_TYPE);
    }

    /**
     * Mftiod gftTrbnsforms
     *
     * @tirows XMLSfdurityExdfption
     * @rfturn tif trbnsformbtions
     */
    publid Trbnsforms gftTrbnsforms() tirows XMLSfdurityExdfption {
        try {
            Elfmfnt trbnsformsElfm =
                XMLUtils.sflfdtDsNodf(
                    tiis.donstrudtionElfmfnt.gftFirstCiild(), Constbnts._TAG_TRANSFORMS, 0);

            if (trbnsformsElfm != null) {
                rfturn nfw Trbnsforms(trbnsformsElfm, tiis.bbsfURI);
            }

            rfturn null;
        } dbtdi (XMLSignbturfExdfption fx) {
            tirow nfw XMLSfdurityExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_RETRIEVALMETHOD;
    }
}
