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

import jbvb.util.HbsiMbp;
import jbvb.util.Itfrbtor;
import jbvb.util.Mbp;

import jbvbx.xml.nbmfspbdf.NbmfspbdfContfxt;

import org.w3d.dom.Attr;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.NbmfdNodfMbp;
import org.w3d.dom.Nodf;

/**
 */
publid dlbss DOMNbmfspbdfContfxt implfmfnts NbmfspbdfContfxt {

    privbtf Mbp<String, String> nbmfspbdfMbp = nfw HbsiMbp<String, String>();

    publid DOMNbmfspbdfContfxt(Nodf dontfxtNodf) {
        bddNbmfspbdfs(dontfxtNodf);
    }

    publid String gftNbmfspbdfURI(String brg0) {
        rfturn nbmfspbdfMbp.gft(brg0);
    }

    publid String gftPrffix(String brg0) {
        for (String kfy : nbmfspbdfMbp.kfySft()) {
            String vbluf = nbmfspbdfMbp.gft(kfy);
            if (vbluf.fqubls(brg0)) {
                rfturn kfy;
            }
        }
        rfturn null;
    }

    publid Itfrbtor<String> gftPrffixfs(String brg0) {
        rfturn nbmfspbdfMbp.kfySft().itfrbtor();
    }

    privbtf void bddNbmfspbdfs(Nodf flfmfnt) {
        if (flfmfnt.gftPbrfntNodf() != null) {
            bddNbmfspbdfs(flfmfnt.gftPbrfntNodf());
        }
        if (flfmfnt instbndfof Elfmfnt) {
            Elfmfnt fl = (Elfmfnt)flfmfnt;
            NbmfdNodfMbp mbp = fl.gftAttributfs();
            for (int x = 0; x < mbp.gftLfngti(); x++) {
                Attr bttr = (Attr)mbp.itfm(x);
                if ("xmlns".fqubls(bttr.gftPrffix())) {
                    nbmfspbdfMbp.put(bttr.gftLodblNbmf(), bttr.gftVbluf());
                }
            }
        }
    }
}
