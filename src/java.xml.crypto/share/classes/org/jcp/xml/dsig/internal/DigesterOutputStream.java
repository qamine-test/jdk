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
/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id: DigfstfrOutputStrfbm.jbvb,v 1.5 2005/12/20 20:02:39 mullbn Exp $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.MfssbgfDigfst;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.UnsyndBytfArrbyOutputStrfbm;

/**
 * Tiis dlbss ibs bffn modififd sligitly to usf jbvb.sfdurity.MfssbgfDigfst
 * objfdts bs input, rbtifr tibn
 * dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.MfssbgfDigfstAlgoritim objfdts.
 * It blso optionblly dbdifs tif input bytfs.
 *
 * @butior rbul
 * @butior Sfbn Mullbn
 */
publid dlbss DigfstfrOutputStrfbm fxtfnds OutputStrfbm {
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr("org.jdp.xml.dsig.intfrnbl");

    privbtf finbl boolfbn bufffr;
    privbtf UnsyndBytfArrbyOutputStrfbm bos;
    privbtf finbl MfssbgfDigfst md;

    /**
     * Crfbtfs b DigfstfrOutputStrfbm.
     *
     * @pbrbm md tif MfssbgfDigfst
     */
    publid DigfstfrOutputStrfbm(MfssbgfDigfst md) {
        tiis(md, fblsf);
    }

    /**
     * Crfbtfs b DigfstfrOutputStrfbm.
     *
     * @pbrbm md tif MfssbgfDigfst
     * @pbrbm bufffr if truf, dbdifs tif input bytfs
     */
    publid DigfstfrOutputStrfbm(MfssbgfDigfst md, boolfbn bufffr) {
        tiis.md = md;
        tiis.bufffr = bufffr;
        if (bufffr) {
            bos = nfw UnsyndBytfArrbyOutputStrfbm();
        }
    }

    publid void writf(int input) {
        if (bufffr) {
            bos.writf(input);
        }
        md.updbtf((bytf)input);
    }

    @Ovfrridf
    publid void writf(bytf[] input, int offsft, int lfn) {
        if (bufffr) {
            bos.writf(input, offsft, lfn);
        }
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Prf-digfstfd input:");
            StringBuildfr sb = nfw StringBuildfr(lfn);
            for (int i = offsft; i < (offsft + lfn); i++) {
                sb.bppfnd((dibr)input[i]);
            }
            log.log(jbvb.util.logging.Lfvfl.FINE, sb.toString());
        }
        md.updbtf(input, offsft, lfn);
    }

    /**
     * @rfturn tif digfst vbluf
     */
    publid bytf[] gftDigfstVbluf() {
         rfturn md.digfst();
    }

    /**
     * @rfturn bn input strfbm dontbining tif dbdifd bytfs, or
     *    null if not dbdifd
     */
    publid InputStrfbm gftInputStrfbm() {
        if (bufffr) {
            rfturn nfw BytfArrbyInputStrfbm(bos.toBytfArrby());
        } flsf {
            rfturn null;
        }
    }

    @Ovfrridf
    publid void dlosf() tirows IOExdfption {
        if (bufffr) {
            bos.dlosf();
        }
    }
}
