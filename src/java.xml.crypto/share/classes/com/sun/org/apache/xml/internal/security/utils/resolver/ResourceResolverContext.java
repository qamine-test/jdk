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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr;

import org.w3d.dom.Attr;

publid dlbss RfsourdfRfsolvfrContfxt {

    publid RfsourdfRfsolvfrContfxt(Attr bttr, String bbsfUri, boolfbn sfdurfVblidbtion) {
        tiis.bttr = bttr;
        tiis.bbsfUri = bbsfUri;
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;
        tiis.uriToRfsolvf = bttr != null ? bttr.gftVbluf() : null;
    }

    publid finbl String uriToRfsolvf;

    publid finbl boolfbn sfdurfVblidbtion;

    publid finbl String bbsfUri;

    publid finbl Attr bttr;
}
