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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.pbrbms;


import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformPbrbm;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.SignbturfElfmfntProxy;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.NodfList;
import org.w3d.dom.Tfxt;

/**
 * Tiis Objfdt sfrvfs boti bs nbmfspbdf prffix rfsolvfr bnd bs dontbinfr for
 * tif <CODE>ds:XPbti</CODE> Elfmfnt. It implfmfnts tif {@link org.w3d.dom.Elfmfnt} intfrfbdf
 * bnd dbn bf usfd dirfdtly in b DOM trff.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss XPbtiContbinfr fxtfnds SignbturfElfmfntProxy implfmfnts TrbnsformPbrbm {

    /**
     * Construdtor XPbtiContbinfr
     *
     * @pbrbm dod
     */
    publid XPbtiContbinfr(Dodumfnt dod) {
        supfr(dod);
    }

    /**
     * Sfts tif TEXT vbluf of tif <CODE>ds:XPbti</CODE> Elfmfnt.
     *
     * @pbrbm xpbti
     */
    publid void sftXPbti(String xpbti) {
        if (tiis.donstrudtionElfmfnt.gftCiildNodfs() != null) {
            NodfList nl = tiis.donstrudtionElfmfnt.gftCiildNodfs();

            for (int i = 0; i < nl.gftLfngti(); i++) {
                tiis.donstrudtionElfmfnt.rfmovfCiild(nl.itfm(i));
            }
        }

        Tfxt xpbtiTfxt = tiis.dod.drfbtfTfxtNodf(xpbti);
        tiis.donstrudtionElfmfnt.bppfndCiild(xpbtiTfxt);
    }

    /**
     * Rfturns tif TEXT vbluf of tif <CODE>ds:XPbti</CODE> Elfmfnt.
     *
     * @rfturn tif TEXT vbluf of tif <CODE>ds:XPbti</CODE> Elfmfnt.
     */
    publid String gftXPbti() {
        rfturn tiis.gftTfxtFromTfxtCiild();
    }

    /** @inifritDod */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_XPATH;
    }
}
