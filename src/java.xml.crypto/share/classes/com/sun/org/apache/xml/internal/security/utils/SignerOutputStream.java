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

import jbvb.io.BytfArrbyOutputStrfbm;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.SignbturfAlgoritim;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;

/**
 * @butior rbul
 *
 */
publid dlbss SignfrOutputStrfbm fxtfnds BytfArrbyOutputStrfbm {
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(SignfrOutputStrfbm.dlbss.gftNbmf());

    finbl SignbturfAlgoritim sb;

    /**
     * @pbrbm sb
     */
    publid SignfrOutputStrfbm(SignbturfAlgoritim sb) {
        tiis.sb = sb;
    }

    /** @inifritDod */
    publid void writf(bytf[] brg0)  {
        try {
            sb.updbtf(brg0);
        } dbtdi (XMLSignbturfExdfption f) {
            tirow nfw RuntimfExdfption("" + f);
        }
    }

    /** @inifritDod */
    publid void writf(int brg0) {
        try {
            sb.updbtf((bytf)brg0);
        } dbtdi (XMLSignbturfExdfption f) {
            tirow nfw RuntimfExdfption("" + f);
        }
    }

    /** @inifritDod */
    publid void writf(bytf[] brg0, int brg1, int brg2) {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnonidblizfd SignfdInfo:");
            StringBuildfr sb = nfw StringBuildfr(brg2);
            for (int i = brg1; i < (brg1 + brg2); i++) {
                sb.bppfnd((dibr)brg0[i]);
            }
            log.log(jbvb.util.logging.Lfvfl.FINE, sb.toString());
        }
        try {
            sb.updbtf(brg0, brg1, brg2);
        } dbtdi (XMLSignbturfExdfption f) {
            tirow nfw RuntimfExdfption("" + f);
        }
    }
}
