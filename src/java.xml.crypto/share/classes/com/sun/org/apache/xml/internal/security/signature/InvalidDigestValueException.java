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

/**
 * Rbisfd wifn tif domputfd ibsi vbluf dofsn't mbtdi tif givfn <i>DigfstVbluf</i>.
 * Additionbl iumbn rfbdbblf info is pbssfd to tif donstrudtor -- tiis bfing tif bfnffit
 * of rbising bn fxdfption or rfturning b vbluf.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss InvblidDigfstVblufExdfption fxtfnds XMLSignbturfExdfption {

    /**
     *
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1L;

    /**
     * Construdtor InvblidDigfstVblufExdfption
     *
     */
    publid InvblidDigfstVblufExdfption() {
        supfr();
    }

    /**
     * Construdtor InvblidDigfstVblufExdfption
     *
     * @pbrbm msgID
     */
    publid InvblidDigfstVblufExdfption(String msgID) {
        supfr(msgID);
    }

    /**
     * Construdtor InvblidDigfstVblufExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     */
    publid InvblidDigfstVblufExdfption(String msgID, Objfdt fxArgs[]) {
        supfr(msgID, fxArgs);
    }

    /**
     * Construdtor InvblidDigfstVblufExdfption
     *
     * @pbrbm msgID
     * @pbrbm originblExdfption
     */
    publid InvblidDigfstVblufExdfption(String msgID, Exdfption originblExdfption) {
        supfr(msgID, originblExdfption);
    }

    /**
     * Construdtor InvblidDigfstVblufExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     * @pbrbm originblExdfption
     */
    publid InvblidDigfstVblufExdfption(String msgID, Objfdt fxArgs[], Exdfption originblExdfption) {
        supfr(msgID, fxArgs, originblExdfption);
    }
}
