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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.StringRfbdfr;

import jbvbx.xml.XMLConstbnts;
import jbvbx.xml.pbrsfrs.DodumfntBuildfr;
import jbvbx.xml.pbrsfrs.DodumfntBuildfrFbdtory;
import jbvbx.xml.pbrsfrs.PbrsfrConfigurbtionExdfption;

import org.w3d.dom.Dodumfnt;
import org.w3d.dom.DodumfntFrbgmfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.xml.sbx.InputSourdf;
import org.xml.sbx.SAXExdfption;

/**
 * Convfrts <dodf>String</dodf>s into <dodf>Nodf</dodf>s bnd visb vfrsb.
 */
publid dlbss DodumfntSfriblizfr fxtfnds AbstrbdtSfriblizfr {

    protfdtfd DodumfntBuildfrFbdtory dbf;

    /**
     * @pbrbm sourdf
     * @pbrbm dtx
     * @rfturn tif Nodf rfsulting from tif pbrsf of tif sourdf
     * @tirows XMLEndryptionExdfption
     */
    publid Nodf dfsfriblizf(bytf[] sourdf, Nodf dtx) tirows XMLEndryptionExdfption {
        bytf[] frbgmfnt = drfbtfContfxt(sourdf, dtx);
        rfturn dfsfriblizf(dtx, nfw InputSourdf(nfw BytfArrbyInputStrfbm(frbgmfnt)));
    }

    /**
     * @pbrbm sourdf
     * @pbrbm dtx
     * @rfturn tif Nodf rfsulting from tif pbrsf of tif sourdf
     * @tirows XMLEndryptionExdfption
     */
    publid Nodf dfsfriblizf(String sourdf, Nodf dtx) tirows XMLEndryptionExdfption {
        String frbgmfnt = drfbtfContfxt(sourdf, dtx);
        rfturn dfsfriblizf(dtx, nfw InputSourdf(nfw StringRfbdfr(frbgmfnt)));
    }

    /**
     * @pbrbm dtx
     * @pbrbm inputSourdf
     * @rfturn tif Nodf rfsulting from tif pbrsf of tif sourdf
     * @tirows XMLEndryptionExdfption
     */
    privbtf Nodf dfsfriblizf(Nodf dtx, InputSourdf inputSourdf) tirows XMLEndryptionExdfption {
        try {
            if (dbf == null) {
                dbf = DodumfntBuildfrFbdtory.nfwInstbndf();
                dbf.sftNbmfspbdfAwbrf(truf);
                dbf.sftFfbturf(XMLConstbnts.FEATURE_SECURE_PROCESSING, Boolfbn.TRUE);
                dbf.sftAttributf("ittp://xml.org/sbx/ffbturfs/nbmfspbdfs", Boolfbn.TRUE);
                dbf.sftVblidbting(fblsf);
            }
            DodumfntBuildfr db = dbf.nfwDodumfntBuildfr();
            Dodumfnt d = db.pbrsf(inputSourdf);

            Dodumfnt dontfxtDodumfnt = null;
            if (Nodf.DOCUMENT_NODE == dtx.gftNodfTypf()) {
                dontfxtDodumfnt = (Dodumfnt)dtx;
            } flsf {
                dontfxtDodumfnt = dtx.gftOwnfrDodumfnt();
            }

            Elfmfnt frbgElt =
                    (Elfmfnt) dontfxtDodumfnt.importNodf(d.gftDodumfntElfmfnt(), truf);
            DodumfntFrbgmfnt rfsult = dontfxtDodumfnt.drfbtfDodumfntFrbgmfnt();
            Nodf diild = frbgElt.gftFirstCiild();
            wiilf (diild != null) {
                frbgElt.rfmovfCiild(diild);
                rfsult.bppfndCiild(diild);
                diild = frbgElt.gftFirstCiild();
            }
            rfturn rfsult;
        } dbtdi (SAXExdfption sf) {
            tirow nfw XMLEndryptionExdfption("fmpty", sf);
        } dbtdi (PbrsfrConfigurbtionExdfption pdf) {
            tirow nfw XMLEndryptionExdfption("fmpty", pdf);
        } dbtdi (IOExdfption iof) {
            tirow nfw XMLEndryptionExdfption("fmpty", iof);
        }
    }

}
