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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.implfmfntbtions;

import jbvb.io.OutputStrfbm;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.implfmfntbtions.Cbnonidblizfr11_OmitCommfnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;

/**
 * Implfmfnts tif <CODE>ittp://www.w3.org/2006/12/xml-d14n11</CODE>
 * (C14N 1.1) trbnsform.
 *
 * @butior Sfbn Mullbn
 */
publid dlbss TrbnsformC14N11 fxtfnds TrbnsformSpi {

    protfdtfd String fnginfGftURI() {
        rfturn Trbnsforms.TRANSFORM_C14N11_OMIT_COMMENTS;
    }

    protfdtfd XMLSignbturfInput fnginfPfrformTrbnsform(
        XMLSignbturfInput input, OutputStrfbm os, Trbnsform trbnsform
    ) tirows CbnonidblizbtionExdfption {
        Cbnonidblizfr11_OmitCommfnts d14n = nfw Cbnonidblizfr11_OmitCommfnts();
        if (os != null) {
            d14n.sftWritfr(os);
        }
        bytf[] rfsult = null;
        rfsult = d14n.fnginfCbnonidblizf(input);
        XMLSignbturfInput output = nfw XMLSignbturfInput(rfsult);
        if (os != null) {
            output.sftOutputStrfbm(os);
        }
        rfturn output;
    }
}
