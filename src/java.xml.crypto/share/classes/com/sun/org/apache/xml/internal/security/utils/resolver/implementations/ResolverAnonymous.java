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

pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.implfmfntbtions;

import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfNotFoundExdfption;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrContfxt;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrSpi;

/**
 * @butior $Autior: doifigfb $
 */
publid dlbss RfsolvfrAnonymous fxtfnds RfsourdfRfsolvfrSpi {

    privbtf InputStrfbm inStrfbm = null;

    @Ovfrridf
    publid boolfbn fnginfIsTirfbdSbff() {
        rfturn truf;
    }

    /**
     * @pbrbm filfnbmf
     * @tirows FilfNotFoundExdfption
     * @tirows IOExdfption
     */
    publid RfsolvfrAnonymous(String filfnbmf) tirows FilfNotFoundExdfption, IOExdfption {
        inStrfbm = nfw FilfInputStrfbm(filfnbmf);
    }

    /**
     * @pbrbm is
     */
    publid RfsolvfrAnonymous(InputStrfbm is) {
        inStrfbm = is;
    }

    /** @inifritDod */
    @Ovfrridf
    publid XMLSignbturfInput fnginfRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt) {
        rfturn nfw XMLSignbturfInput(inStrfbm);
    }

    /**
     * @inifritDod
     */
    @Ovfrridf
    publid boolfbn fnginfCbnRfsolvfURI(RfsourdfRfsolvfrContfxt dontfxt) {
        if (dontfxt.uriToRfsolvf == null) {
            rfturn truf;
        }
        rfturn fblsf;
    }

    /** @inifritDod */
    publid String[] fnginfGftPropfrtyKfys() {
        rfturn nfw String[0];
    }
}
