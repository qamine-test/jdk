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
 * $Id: DOMKfyNbmf.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.kfyinfo.KfyNbmf;

import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * DOM-bbsfd implfmfntbtion of KfyNbmf.
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMKfyNbmf fxtfnds DOMStrudturf implfmfnts KfyNbmf {

    privbtf finbl String nbmf;

    /**
     * Crfbtfs b <dodf>DOMKfyNbmf</dodf>.
     *
     * @pbrbm nbmf tif nbmf of tif kfy idfntififr
     * @tirows NullPointfrExdfption if <dodf>nbmf</dodf> is null
     */
    publid DOMKfyNbmf(String nbmf) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("nbmf dbnnot bf null");
        }
        tiis.nbmf = nbmf;
    }

    /**
     * Crfbtfs b <dodf>DOMKfyNbmf</dodf> from b KfyNbmf flfmfnt.
     *
     * @pbrbm knElfm b KfyNbmf flfmfnt
     */
    publid DOMKfyNbmf(Elfmfnt knElfm) {
        nbmf = knElfm.gftFirstCiild().gftNodfVbluf();
    }

    publid String gftNbmf() {
        rfturn nbmf;
    }

    publid void mbrsibl(Nodf pbrfnt, String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);
        // prfpfnd nbmfspbdf prffix, if nfdfssbry
        Elfmfnt knElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "KfyNbmf",
                                                XMLSignbturf.XMLNS, dsPrffix);
        knElfm.bppfndCiild(ownfrDod.drfbtfTfxtNodf(nbmf));
        pbrfnt.bppfndCiild(knElfm);
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (!(obj instbndfof KfyNbmf)) {
            rfturn fblsf;
        }
        KfyNbmf okn = (KfyNbmf)obj;
        rfturn nbmf.fqubls(okn.gftNbmf());
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        rfsult = 31 * rfsult + nbmf.ibsiCodf();

        rfturn rfsult;
    }
}
