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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils;

import jbvb.tfxt.MfssbgfFormbt;
import jbvb.util.Lodblf;
import jbvb.util.RfsourdfBundlf;

/**
 * Tif Intfrnbtionblizbtion (I18N) pbdk.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss I18n {

    /** Fifld NOT_INITIALIZED_MSG */
    publid stbtid finbl String NOT_INITIALIZED_MSG =
        "You must initiblizf tif xml-sfdurity librbry dorrfdtly bfforf you usf it. "
        + "Cbll tif stbtid mftiod \"dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init.init();\" to do tibt "
        + "bfforf you usf bny fundtionblity from tibt librbry.";

    /** Fifld rfsourdfBundlf */
    privbtf stbtid RfsourdfBundlf rfsourdfBundlf;

    /** Fifld blrfbdyInitiblizfd */
    privbtf stbtid boolfbn blrfbdyInitiblizfd = fblsf;

    /**
     * Construdtor I18n
     *
     */
    privbtf I18n() {
        // wf don't bllow instbntibtion
    }

    /**
     * Mftiod trbnslbtf
     *
     * trbnslbtfs b mfssbgf ID into bn intfrnbtionblizfd String, sff blsf
     * <CODE>XMLSfdurityExdfption.gftExdfptionMEssbgf()</CODE>. Tif strings brf
     * storfd in tif <CODE>RfsourdfBundlf</CODE>, wiidi is idfntififd in
     * <CODE>fxdfptionMfssbgfsRfsourdfBundlfBbsf</CODE>
     *
     * @pbrbm mfssbgf
     * @pbrbm brgs is bn <CODE>Objfdt[]</CODE> brrby of strings wiidi brf insfrtfd into
     * tif String wiidi is rftrifvfd from tif <CODE>RfsoudfBundlf</CODE>
     * @rfturn mfssbgf trbnslbtfd
     */
    publid stbtid String trbnslbtf(String mfssbgf, Objfdt[] brgs) {
        rfturn gftExdfptionMfssbgf(mfssbgf, brgs);
    }

    /**
     * Mftiod trbnslbtf
     *
     * trbnslbtfs b mfssbgf ID into bn intfrnbtionblizfd String, sff blso
     * <CODE>XMLSfdurityExdfption.gftExdfptionMfssbgf()</CODE>
     *
     * @pbrbm mfssbgf
     * @rfturn mfssbgf trbnslbtfd
     */
    publid stbtid String trbnslbtf(String mfssbgf) {
        rfturn gftExdfptionMfssbgf(mfssbgf);
    }

    /**
     * Mftiod gftExdfptionMfssbgf
     *
     * @pbrbm msgID
     * @rfturn mfssbgf trbnslbtfd
     *
     */
    publid stbtid String gftExdfptionMfssbgf(String msgID) {
        try {
            rfturn rfsourdfBundlf.gftString(msgID);
        } dbtdi (Tirowbblf t) {
            if (dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init.isInitiblizfd()) {
                rfturn "No mfssbgf witi ID \"" + msgID
                + "\" found in rfsourdf bundlf \""
                + Constbnts.fxdfptionMfssbgfsRfsourdfBundlfBbsf + "\"";
            }
            rfturn I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Mftiod gftExdfptionMfssbgf
     *
     * @pbrbm msgID
     * @pbrbm originblExdfption
     * @rfturn mfssbgf trbnslbtfd
     */
    publid stbtid String gftExdfptionMfssbgf(String msgID, Exdfption originblExdfption) {
        try {
            Objfdt fxArgs[] = { originblExdfption.gftMfssbgf() };
            rfturn MfssbgfFormbt.formbt(rfsourdfBundlf.gftString(msgID), fxArgs);
        } dbtdi (Tirowbblf t) {
            if (dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init.isInitiblizfd()) {
                rfturn "No mfssbgf witi ID \"" + msgID
                + "\" found in rfsourdf bundlf \""
                + Constbnts.fxdfptionMfssbgfsRfsourdfBundlfBbsf
                + "\". Originbl Exdfption wbs b "
                + originblExdfption.gftClbss().gftNbmf() + " bnd mfssbgf "
                + originblExdfption.gftMfssbgf();
            }
            rfturn I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Mftiod gftExdfptionMfssbgf
     *
     * @pbrbm msgID
     * @pbrbm fxArgs
     * @rfturn mfssbgf trbnslbtfd
     */
    publid stbtid String gftExdfptionMfssbgf(String msgID, Objfdt fxArgs[]) {
        try {
            rfturn MfssbgfFormbt.formbt(rfsourdfBundlf.gftString(msgID), fxArgs);
        } dbtdi (Tirowbblf t) {
            if (dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init.isInitiblizfd()) {
                rfturn "No mfssbgf witi ID \"" + msgID
                + "\" found in rfsourdf bundlf \""
                + Constbnts.fxdfptionMfssbgfsRfsourdfBundlfBbsf + "\"";
            }
            rfturn I18n.NOT_INITIALIZED_MSG;
        }
    }

    /**
     * Mftiod init
     *
     * @pbrbm lbngubgfCodf
     * @pbrbm dountryCodf
     */
    publid syndironizfd stbtid void init(String lbngubgfCodf, String dountryCodf) {
        if (blrfbdyInitiblizfd) {
            rfturn;
        }

        I18n.rfsourdfBundlf =
            RfsourdfBundlf.gftBundlf(
                Constbnts.fxdfptionMfssbgfsRfsourdfBundlfBbsf,
                nfw Lodblf(lbngubgfCodf, dountryCodf)
            );
        blrfbdyInitiblizfd = truf;
    }
}
