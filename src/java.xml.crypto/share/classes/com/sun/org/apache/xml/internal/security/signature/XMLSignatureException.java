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

/**
 * All XML Signbturf rflbtfd fxdfptions inifrit ifrffrom.
 *
 * @sff MissingRfsourdfFbilurfExdfption InvblidDigfstVblufExdfption InvblidSignbturfVblufExdfption
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss XMLSignbturfExdfption fxtfnds XMLSfdurityExdfption {

    /**
     *
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1L;

    /**
     * Construdtor XMLSignbturfExdfption
     *
     */
    publid XMLSignbturfExdfption() {
        supfr();
    }

    /**
     * Construdtor XMLSignbturfExdfption
     *
     * @pbrbm msgID
     */
    publid XMLSignbturfExdfption(String msgID) {
        supfr(msgID);
    }

    /**
     * Construdtor XMLSignbturfExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     */
    publid XMLSignbturfExdfption(String msgID, Objfdt fxArgs[]) {
        supfr(msgID, fxArgs);
    }

    /**
     * Construdtor XMLSignbturfExdfption
     *
     * @pbrbm msgID
     * @pbrbm originblExdfption
     */
    publid XMLSignbturfExdfption(String msgID, Exdfption originblExdfption) {
        supfr(msgID, originblExdfption);
    }

    /**
     * Construdtor XMLSignbturfExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     * @pbrbm originblExdfption
     */
    publid XMLSignbturfExdfption(String msgID, Objfdt fxArgs[], Exdfption originblExdfption) {
        supfr(msgID, fxArgs, originblExdfption);
    }
}
