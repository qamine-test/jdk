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
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * Hbndlfs <dodf>&lt;ds:SignbturfPropfrty&gt;</dodf> flfmfnts
 * Additionbl informbtion itfm dondfrning tif gfnfrbtion of tif signbturf(s) dbn
 * bf plbdfd in tiis Elfmfnt
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss SignbturfPropfrty fxtfnds SignbturfElfmfntProxy {

    /**
     * Construdts{@link SignbturfPropfrty} using spfdififd <dodf>tbrgft</dodf> bttributf
     *
     * @pbrbm dod tif {@link Dodumfnt} in wiidi <dodf>XMLsignbturf</dodf> is plbdfd
     * @pbrbm tbrgft tif <dodf>tbrgft</dodf> bttributf rfffrfndfs tif <dodf>Signbturf</dodf>
     * flfmfnt to wiidi tif propfrty bpplifs SignbturfPropfrty
     */
    publid SignbturfPropfrty(Dodumfnt dod, String tbrgft) {
        tiis(dod, tbrgft, null);
    }

    /**
     * Construdts {@link SignbturfPropfrty} using sfpdififd <dodf>tbrgft</dodf> bttributf bnd
     * <dodf>id</dodf> bttributf
     *
     * @pbrbm dod tif {@link Dodumfnt} in wiidi <dodf>XMLsignbturf</dodf> is plbdfd
     * @pbrbm tbrgft tif <dodf>tbrgft</dodf> bttributf rfffrfndfs tif <dodf>Signbturf</dodf>
     *  flfmfnt to wiidi tif propfrty bpplifs
     * @pbrbm id tif <dodf>id</dodf> will bf spfdififd by {@link Rfffrfndf#gftURI} in vblidbtion
     */
    publid SignbturfPropfrty(Dodumfnt dod, String tbrgft, String id) {
        supfr(dod);

        tiis.sftTbrgft(tbrgft);
        tiis.sftId(id);
    }

    /**
     * Construdts b {@link SignbturfPropfrty} from bn {@link Elfmfnt}
     * @pbrbm flfmfnt <dodf>SignbturfPropfrty</dodf> flfmfnt
     * @pbrbm BbsfURI tif URI of tif rfsourdf wifrf tif XML instbndf wbs storfd
     * @tirows XMLSfdurityExdfption
     */
    publid SignbturfPropfrty(Elfmfnt flfmfnt, String BbsfURI) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, BbsfURI);
    }

    /**
     *   Sfts tif <dodf>id</dodf> bttributf
     *
     *   @pbrbm id tif <dodf>id</dodf> bttributf
     */
    publid void sftId(String id) {
        if (id != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_ID, id);
            tiis.donstrudtionElfmfnt.sftIdAttributfNS(null, Constbnts._ATT_ID, truf);
        }
    }

    /**
     * Rfturns tif <dodf>id</dodf> bttributf
     *
     * @rfturn tif <dodf>id</dodf> bttributf
     */
    publid String gftId() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ID);
    }

    /**
     * Sfts tif <dodf>tbrgft</dodf> bttributf
     *
     * @pbrbm tbrgft tif <dodf>tbrgft</dodf> bttributf
     */
    publid void sftTbrgft(String tbrgft) {
        if (tbrgft != null) {
            tiis.donstrudtionElfmfnt.sftAttributfNS(null, Constbnts._ATT_TARGET, tbrgft);
        }
    }

    /**
     * Rfturns tif <dodf>tbrgft</dodf> bttributf
     *
     * @rfturn tif <dodf>tbrgft</dodf> bttributf
     */
    publid String gftTbrgft() {
        rfturn tiis.donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_TARGET);
    }

    /**
     * Mftiod bppfndCiild
     *
     * @pbrbm nodf
     * @rfturn tif nodf in tiis flfmfnt.
     */
    publid Nodf bppfndCiild(Nodf nodf) {
        rfturn tiis.donstrudtionElfmfnt.bppfndCiild(nodf);
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_SIGNATUREPROPERTY;
    }
}
