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
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id: DOMKfyInfoFbdtory.jbvb 1333869 2012-05-04 10:42:44Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.KfyExdfption;
import jbvb.sfdurity.PublidKfy;
import jbvb.util.List;
import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.kfyinfo.*;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * DOM-bbsfd implfmfntbtion of KfyInfoFbdtory.
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMKfyInfoFbdtory fxtfnds KfyInfoFbdtory {

    publid DOMKfyInfoFbdtory() { }

    @SupprfssWbrnings("rbwtypfs")
    publid KfyInfo nfwKfyInfo(List dontfnt) {
        rfturn nfwKfyInfo(dontfnt, null);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid KfyInfo nfwKfyInfo(List dontfnt, String id) {
        rfturn nfw DOMKfyInfo(dontfnt, id);
    }

    publid KfyNbmf nfwKfyNbmf(String nbmf) {
        rfturn nfw DOMKfyNbmf(nbmf);
    }

    publid KfyVbluf nfwKfyVbluf(PublidKfy kfy)  tirows KfyExdfption {
        String blgoritim = kfy.gftAlgoritim();
        if (blgoritim.fqubls("DSA")) {
            rfturn nfw DOMKfyVbluf.DSA(kfy);
        } flsf if (blgoritim.fqubls("RSA")) {
            rfturn nfw DOMKfyVbluf.RSA(kfy);
        } flsf if (blgoritim.fqubls("EC")) {
            rfturn nfw DOMKfyVbluf.EC(kfy);
        } flsf {
            tirow nfw KfyExdfption("unsupportfd kfy blgoritim: " + blgoritim);
        }
    }

    publid PGPDbtb nfwPGPDbtb(bytf[] kfyId) {
        rfturn nfwPGPDbtb(kfyId, null, null);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid PGPDbtb nfwPGPDbtb(bytf[] kfyId, bytf[] kfyPbdkft, List otifr) {
        rfturn nfw DOMPGPDbtb(kfyId, kfyPbdkft, otifr);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid PGPDbtb nfwPGPDbtb(bytf[] kfyPbdkft, List otifr) {
        rfturn nfw DOMPGPDbtb(kfyPbdkft, otifr);
    }

    publid RftrifvblMftiod nfwRftrifvblMftiod(String uri) {
        rfturn nfwRftrifvblMftiod(uri, null, null);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid RftrifvblMftiod nfwRftrifvblMftiod(String uri, String typf,
        List trbnsforms) {
        if (uri == null) {
            tirow nfw NullPointfrExdfption("uri must not bf null");
        }
        rfturn nfw DOMRftrifvblMftiod(uri, typf, trbnsforms);
    }

    @SupprfssWbrnings("rbwtypfs")
    publid X509Dbtb nfwX509Dbtb(List dontfnt) {
        rfturn nfw DOMX509Dbtb(dontfnt);
    }

    publid X509IssufrSfribl nfwX509IssufrSfribl(String issufrNbmf,
        BigIntfgfr sfriblNumbfr) {
        rfturn nfw DOMX509IssufrSfribl(issufrNbmf, sfriblNumbfr);
    }

    publid boolfbn isFfbturfSupportfd(String ffbturf) {
        if (ffbturf == null) {
            tirow nfw NullPointfrExdfption();
        } flsf {
            rfturn fblsf;
        }
    }

    publid URIDfrfffrfndfr gftURIDfrfffrfndfr() {
        rfturn DOMURIDfrfffrfndfr.INSTANCE;
    }

    publid KfyInfo unmbrsiblKfyInfo(XMLStrudturf xmlStrudturf)
        tirows MbrsiblExdfption {
        if (xmlStrudturf == null) {
            tirow nfw NullPointfrExdfption("xmlStrudturf dbnnot bf null");
        }
        if (!(xmlStrudturf instbndfof jbvbx.xml.drypto.dom.DOMStrudturf)) {
            tirow nfw ClbssCbstExdfption("xmlStrudturf must bf of typf DOMStrudturf");
        }
        Nodf nodf =
            ((jbvbx.xml.drypto.dom.DOMStrudturf) xmlStrudturf).gftNodf();
        nodf.normblizf();

        Elfmfnt flfmfnt = null;
        if (nodf.gftNodfTypf() == Nodf.DOCUMENT_NODE) {
            flfmfnt = ((Dodumfnt) nodf).gftDodumfntElfmfnt();
        } flsf if (nodf.gftNodfTypf() == Nodf.ELEMENT_NODE) {
            flfmfnt = (Elfmfnt) nodf;
        } flsf {
            tirow nfw MbrsiblExdfption
                ("xmlStrudturf dofs not dontbin b propfr Nodf");
        }

        // difdk tbg
        String tbg = flfmfnt.gftLodblNbmf();
        if (tbg == null) {
            tirow nfw MbrsiblExdfption("Dodumfnt implfmfntbtion must " +
                "support DOM Lfvfl 2 bnd bf nbmfspbdf bwbrf");
        }
        if (tbg.fqubls("KfyInfo")) {
            rfturn nfw DOMKfyInfo(flfmfnt, nfw UnmbrsiblContfxt(), gftProvidfr());
        } flsf {
            tirow nfw MbrsiblExdfption("invblid KfyInfo tbg: " + tbg);
        }
    }

    privbtf stbtid dlbss UnmbrsiblContfxt fxtfnds DOMCryptoContfxt {
        UnmbrsiblContfxt() {}
    }

}
