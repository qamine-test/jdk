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
 * $Id: DOMXSLTTrbnsform.jbvb 1197150 2011-11-03 14:34:57Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.spfd.TrbnsformPbrbmftfrSpfd;
import jbvbx.xml.drypto.dsig.spfd.XSLTTrbnsformPbrbmftfrSpfd;

/**
 * DOM-bbsfd implfmfntbtion of XSLT Trbnsform.
 * (Usfs Apbdif XML-Sfd Trbnsform implfmfntbtion)
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMXSLTTrbnsform fxtfnds ApbdifTrbnsform {

    publid void init(TrbnsformPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms == null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("pbrbms brf rfquirfd");
        }
        if (!(pbrbms instbndfof XSLTTrbnsformPbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("unrfdognizfd pbrbms");
        }
        tiis.pbrbms = pbrbms;
    }

    publid void init(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows InvblidAlgoritimPbrbmftfrExdfption {

        supfr.init(pbrfnt, dontfxt);
        unmbrsiblPbrbms(DOMUtils.gftFirstCiildElfmfnt(trbnsformElfm));
    }

    privbtf void unmbrsiblPbrbms(Elfmfnt sifft) {
        tiis.pbrbms = nfw XSLTTrbnsformPbrbmftfrSpfd
            (nfw jbvbx.xml.drypto.dom.DOMStrudturf(sifft));
    }

    publid void mbrsiblPbrbms(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption {
        supfr.mbrsiblPbrbms(pbrfnt, dontfxt);
        XSLTTrbnsformPbrbmftfrSpfd xp =
            (XSLTTrbnsformPbrbmftfrSpfd) gftPbrbmftfrSpfd();
        Nodf xsltElfm =
            ((jbvbx.xml.drypto.dom.DOMStrudturf) xp.gftStylfsifft()).gftNodf();
        DOMUtils.bppfndCiild(trbnsformElfm, xsltElfm);
    }
}
