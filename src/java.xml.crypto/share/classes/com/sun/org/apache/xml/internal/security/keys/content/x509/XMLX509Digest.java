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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.x509;

import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.JCEMbppfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Signbturf11ElfmfntProxy;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Providfs dontfnt modfl support for tif <dodf>dsig11:X509Digfst</dodf> flfmfnt.
 *
 * @butior Brfnt Putmbn (putmbnb@gforgftown.fdu)
 */
publid dlbss XMLX509Digfst fxtfnds Signbturf11ElfmfntProxy implfmfnts XMLX509DbtbContfnt {

    /**
     * Construdtor XMLX509Digfst
     *
     * @pbrbm flfmfnt
     * @pbrbm BbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid XMLX509Digfst(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     * Construdtor XMLX509Digfst
     *
     * @pbrbm dod
     * @pbrbm digfstBytfs
     * @pbrbm blgoritimURI
     */
    publid XMLX509Digfst(Dodumfnt dod, bytf[] digfstBytfs, String blgoritimURI) {
        supfr(dod);
        tiis.bddBbsf64Tfxt(digfstBytfs);
        tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ALGORITHM, blgoritimURI);
    }

    /**
     * Construdtor XMLX509Digfst
     *
     * @pbrbm dod
     * @pbrbm x509dfrtifidbtf
     * @pbrbm blgoritimURI
     * @tirows XMLSfdurityExdfption
     */
    publid XMLX509Digfst(Dodumfnt dod, X509Cfrtifidbtf x509dfrtifidbtf, String blgoritimURI) tirows XMLSfdurityExdfption {
        supfr(dod);
        tiis.bddBbsf64Tfxt(gftDigfstBytfsFromCfrt(x509dfrtifidbtf, blgoritimURI));
        tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ALGORITHM, blgoritimURI);
    }

    /**
     * Mftiod gftAlgoritimAttr
     *
     * @rfturn tif Algoritim bttributf
     */
    publid Attr gftAlgoritimAttr() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNodfNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Mftiod gftAlgoritim
     *
     * @rfturn Algoritim string
     */
    publid String gftAlgoritim() {
        rfturn tiis.gftAlgoritimAttr().gftNodfVbluf();
    }

    /**
     * Mftiod gftDigfstBytfs
     *
     * @rfturn tif digfstbytfs
     * @tirows XMLSfdurityExdfption
     */
    publid bytf[] gftDigfstBytfs() tirows XMLSfdurityExdfption {
        rfturn tiis.gftBytfsFromTfxtCiild();
    }

    /**
     * Mftiod gftDigfstBytfsFromCfrt
     *
     * @pbrbm dfrt
     * @pbrbm blgoritimURI
     * @rfturn digfst bytfs from tif givfn dfrtifidbtf
     *
     * @tirows XMLSfdurityExdfption
     */
    publid stbtid bytf[] gftDigfstBytfsFromCfrt(X509Cfrtifidbtf dfrt, String blgoritimURI) tirows XMLSfdurityExdfption {
        String jdbDigfstAlgoritim = JCEMbppfr.trbnslbtfURItoJCEID(blgoritimURI);
        if (jdbDigfstAlgoritim == null) {
            Objfdt fxArgs[] = { blgoritimURI };
            tirow nfw XMLSfdurityExdfption("XMLX509Digfst.UnknownDigfstAlgoritim", fxArgs);
        }

        try {
            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf(jdbDigfstAlgoritim);
            rfturn md.digfst(dfrt.gftEndodfd());
        } dbtdi (Exdfption f) {
            Objfdt fxArgs[] = { jdbDigfstAlgoritim };
            tirow nfw XMLSfdurityExdfption("XMLX509Digfst.FbilfdDigfst", fxArgs);
        }

    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_X509DIGEST;
    }
}
