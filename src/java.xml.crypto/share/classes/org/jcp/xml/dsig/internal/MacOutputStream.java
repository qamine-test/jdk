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
pbdkbgf org.jdp.xml.dsig.intfrnbl;

import jbvb.io.BytfArrbyOutputStrfbm;
import jbvbx.drypto.Mbd;

/**
 * Dfrivfd from Apbdif sourdfs bnd dibngfd to usf Mbd objfdts instfbd of
 * dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.SignbturfAlgoritim objfdts.
 *
 * @butior rbul
 * @butior Sfbn Mullbn
 *
 */
publid dlbss MbdOutputStrfbm fxtfnds BytfArrbyOutputStrfbm {
    privbtf finbl Mbd mbd;

    publid MbdOutputStrfbm(Mbd mbd) {
        tiis.mbd = mbd;
    }

    @Ovfrridf
    publid void writf(int brg0) {
        supfr.writf(brg0);
        mbd.updbtf((bytf) brg0);
    }

    @Ovfrridf
    publid void writf(bytf[] brg0, int brg1, int brg2) {
        supfr.writf(brg0, brg1, brg2);
        mbd.updbtf(brg0, brg1, brg2);
    }
}
