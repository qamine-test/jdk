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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.kfys.dontfnt.kfyvblufs;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.KfyFbdtory;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.intfrfbdfs.DSAPublidKfy;
import jbvb.sfdurity.spfd.DSAPublidKfySpfd;
import jbvb.sfdurity.spfd.InvblidKfySpfdExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.I18n;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

publid dlbss DSAKfyVbluf fxtfnds SignbturfElfmfntProxy implfmfnts KfyVblufContfnt {

    /**
     * Construdtor DSAKfyVbluf
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid DSAKfyVbluf(Elfmfnt flfmfnt, String bbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, bbsfURI);
    }

    /**
     * Construdtor DSAKfyVbluf
     *
     * @pbrbm dod
     * @pbrbm P
     * @pbrbm Q
     * @pbrbm G
     * @pbrbm Y
     */
    publid DSAKfyVbluf(Dodumfnt dod, BigIntfgfr P, BigIntfgfr Q, BigIntfgfr G, BigIntfgfr Y) {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);
        tiis.bddBigIntfgfrElfmfnt(P, Constbnts._TAG_P);
        tiis.bddBigIntfgfrElfmfnt(Q, Constbnts._TAG_Q);
        tiis.bddBigIntfgfrElfmfnt(G, Constbnts._TAG_G);
        tiis.bddBigIntfgfrElfmfnt(Y, Constbnts._TAG_Y);
    }

    /**
     * Construdtor DSAKfyVbluf
     *
     * @pbrbm dod
     * @pbrbm kfy
     * @tirows IllfgblArgumfntExdfption
     */
    publid DSAKfyVbluf(Dodumfnt dod, Kfy kfy) tirows IllfgblArgumfntExdfption {
        supfr(dod);

        XMLUtils.bddRfturnToElfmfnt(tiis.donstrudtionElfmfnt);

        if (kfy instbndfof jbvb.sfdurity.intfrfbdfs.DSAPublidKfy) {
            tiis.bddBigIntfgfrElfmfnt(((DSAPublidKfy) kfy).gftPbrbms().gftP(), Constbnts._TAG_P);
            tiis.bddBigIntfgfrElfmfnt(((DSAPublidKfy) kfy).gftPbrbms().gftQ(), Constbnts._TAG_Q);
            tiis.bddBigIntfgfrElfmfnt(((DSAPublidKfy) kfy).gftPbrbms().gftG(), Constbnts._TAG_G);
            tiis.bddBigIntfgfrElfmfnt(((DSAPublidKfy) kfy).gftY(), Constbnts._TAG_Y);
        } flsf {
            Objfdt fxArgs[] = { Constbnts._TAG_DSAKEYVALUE, kfy.gftClbss().gftNbmf() };

            tirow nfw IllfgblArgumfntExdfption(I18n.trbnslbtf("KfyVbluf.IllfgblArgumfnt", fxArgs));
        }
    }

    /** @inifritDod */
    publid PublidKfy gftPublidKfy() tirows XMLSfdurityExdfption {
        try {
            DSAPublidKfySpfd pkspfd =
                nfw DSAPublidKfySpfd(
                    tiis.gftBigIntfgfrFromCiildElfmfnt(
                        Constbnts._TAG_Y, Constbnts.SignbturfSpfdNS
                    ),
                    tiis.gftBigIntfgfrFromCiildElfmfnt(
                        Constbnts._TAG_P, Constbnts.SignbturfSpfdNS
                    ),
                    tiis.gftBigIntfgfrFromCiildElfmfnt(
                        Constbnts._TAG_Q, Constbnts.SignbturfSpfdNS
                    ),
                    tiis.gftBigIntfgfrFromCiildElfmfnt(
                        Constbnts._TAG_G, Constbnts.SignbturfSpfdNS
                    )
                );
            KfyFbdtory dsbFbdtory = KfyFbdtory.gftInstbndf("DSA");
            PublidKfy pk = dsbFbdtory.gfnfrbtfPublid(pkspfd);

            rfturn pk;
        } dbtdi (NoSudiAlgoritimExdfption fx) {
            tirow nfw XMLSfdurityExdfption("fmpty", fx);
        } dbtdi (InvblidKfySpfdExdfption fx) {
            tirow nfw XMLSfdurityExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_DSAKEYVALUE;
    }
}
