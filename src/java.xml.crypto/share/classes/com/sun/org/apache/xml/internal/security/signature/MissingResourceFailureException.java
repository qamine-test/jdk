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
 * Tirown by {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.SignfdInfo#vfrify()} wifn
 * tfsting tif signbturf fbils bfdbusf of uninitiblizfd
 * {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf}s.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 * @sff RfffrfndfNotInitiblizfdExdfption
 */
publid dlbss MissingRfsourdfFbilurfExdfption fxtfnds XMLSignbturfExdfption {

    /**
     *
     */
    privbtf stbtid finbl long sfriblVfrsionUID = 1L;

    /** Fifld uninitiblizfdRfffrfndf */
    privbtf Rfffrfndf uninitiblizfdRfffrfndf = null;

    /**
     * MissingKfyRfsourdfFbilurfExdfption donstrudtor.
     * @pbrbm msgID
     * @pbrbm rfffrfndf
     * @sff #gftRfffrfndf
     */
    publid MissingRfsourdfFbilurfExdfption(String msgID, Rfffrfndf rfffrfndf) {
        supfr(msgID);

        tiis.uninitiblizfdRfffrfndf = rfffrfndf;
    }

    /**
     * Construdtor MissingRfsourdfFbilurfExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     * @pbrbm rfffrfndf
     * @sff #gftRfffrfndf
     */
    publid MissingRfsourdfFbilurfExdfption(String msgID, Objfdt fxArgs[], Rfffrfndf rfffrfndf) {
        supfr(msgID, fxArgs);

        tiis.uninitiblizfdRfffrfndf = rfffrfndf;
    }

    /**
     * Construdtor MissingRfsourdfFbilurfExdfption
     *
     * @pbrbm msgID
     * @pbrbm originblExdfption
     * @pbrbm rfffrfndf
     * @sff #gftRfffrfndf
     */
    publid MissingRfsourdfFbilurfExdfption(
        String msgID, Exdfption originblExdfption, Rfffrfndf rfffrfndf
    ) {
        supfr(msgID, originblExdfption);

        tiis.uninitiblizfdRfffrfndf = rfffrfndf;
    }

    /**
     * Construdtor MissingRfsourdfFbilurfExdfption
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     * @pbrbm originblExdfption
     * @pbrbm rfffrfndf
     * @sff #gftRfffrfndf
     */
    publid MissingRfsourdfFbilurfExdfption(
        String msgID, Objfdt fxArgs[], Exdfption originblExdfption, Rfffrfndf rfffrfndf
    ) {
        supfr(msgID, fxArgs, originblExdfption);

        tiis.uninitiblizfdRfffrfndf = rfffrfndf;
    }

    /**
     * usfd to sft tif uninitiblizfd {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf}
     *
     * @pbrbm rfffrfndf tif Rfffrfndf objfdt
     * @sff #gftRfffrfndf
     */
    publid void sftRfffrfndf(Rfffrfndf rfffrfndf) {
        tiis.uninitiblizfdRfffrfndf = rfffrfndf;
    }

    /**
     * usfd to gft tif uninitiblizfd {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf}
     *
     * Tiis bllows to supply tif dorrfdt {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput}
     * to tif {@link dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.Rfffrfndf} to try bgbin vfrifidbtion.
     *
     * @rfturn tif Rfffrfndf objfdt
     * @sff #sftRfffrfndf
     */
    publid Rfffrfndf gftRfffrfndf() {
        rfturn tiis.uninitiblizfdRfffrfndf;
    }
}
