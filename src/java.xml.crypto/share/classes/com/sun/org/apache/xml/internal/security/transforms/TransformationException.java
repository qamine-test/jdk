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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;

/**
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss TrbnsformbtionExdfption fxtfnds XMLSfdurityExdfption {
    /**
     *
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1L;

    /**
     * Construdtor TrbnsformbtionExdfption
     *
     */
    publid TrbnsformbtionExdfption() {
        supfr();
    }

    /**
     * Construdtor TrbnsformbtionExdfption
     *
     * @pbrbm msgID
     */
    publid TrbnsformbtionExdfption(String msgID) {
        supfr(msgID);
    }

    /**
     * Construdtor TrbnsformbtionExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     */
    publid TrbnsformbtionExdfption(String msgID, Objfdt fxArgs[]) {
        supfr(msgID, fxArgs);
    }

    /**
     * Construdtor TrbnsformbtionExdfption
     *
     * @pbrbm msgID
     * @pbrbm originblExdfption
     */
    publid TrbnsformbtionExdfption(String msgID, Exdfption originblExdfption) {
        supfr(msgID, originblExdfption);
    }

    /**
     * Construdtor TrbnsformbtionExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     * @pbrbm originblExdfption
     */
    publid TrbnsformbtionExdfption(String msgID, Objfdt fxArgs[], Exdfption originblExdfption) {
        supfr(msgID, fxArgs, originblExdfption);
    }
}
