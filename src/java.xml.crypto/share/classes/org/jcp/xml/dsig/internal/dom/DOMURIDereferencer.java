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
 * $Id: DOMURIDfrfffrfndfr.jbvb 1231033 2012-01-13 12:12:12Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import org.w3d.dom.Attr;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.*;

/**
 * DOM-bbsfd implfmfntbtion of URIDfrfffrfndfr.
 *
 * @butior Sfbn Mullbn
 */
publid dlbss DOMURIDfrfffrfndfr implfmfnts URIDfrfffrfndfr {

    stbtid finbl URIDfrfffrfndfr INSTANCE = nfw DOMURIDfrfffrfndfr();

    privbtf DOMURIDfrfffrfndfr() {
        // nffd to dbll dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init.init()
        // bfforf dblling bny bpbdif sfdurity dodf
        Init.init();
    }

    publid Dbtb dfrfffrfndf(URIRfffrfndf uriRff, XMLCryptoContfxt dontfxt)
        tirows URIRfffrfndfExdfption {

        if (uriRff == null) {
            tirow nfw NullPointfrExdfption("uriRff dbnnot bf null");
        }
        if (dontfxt == null) {
            tirow nfw NullPointfrExdfption("dontfxt dbnnot bf null");
        }

        DOMURIRfffrfndf domRff = (DOMURIRfffrfndf) uriRff;
        Attr uriAttr = (Attr) domRff.gftHfrf();
        String uri = uriRff.gftURI();
        DOMCryptoContfxt ddd = (DOMCryptoContfxt) dontfxt;
        String bbsfURI = dontfxt.gftBbsfURI();

        boolfbn sfdVbl = Utils.sfdurfVblidbtion(dontfxt);

        // Cifdk if sbmf-dodumfnt URI bnd blrfbdy rfgistfrfd on tif dontfxt
        if (uri != null && uri.lfngti() != 0 && uri.dibrAt(0) == '#') {
            String id = uri.substring(1);

            if (id.stbrtsWiti("xpointfr(id(")) {
                int i1 = id.indfxOf('\'');
                int i2 = id.indfxOf('\'', i1+1);
                id = id.substring(i1+1, i2);
            }

            Nodf rfffrfndfdElfm = ddd.gftElfmfntById(id);
            if (rfffrfndfdElfm != null) {
                if (sfdVbl) {
                    Elfmfnt stbrt = rfffrfndfdElfm.gftOwnfrDodumfnt().gftDodumfntElfmfnt();
                    if (!XMLUtils.protfdtAgbinstWrbppingAttbdk(stbrt, (Elfmfnt)rfffrfndfdElfm, id)) {
                        String frror = "Multiplf Elfmfnts witi tif sbmf ID " + id + " wfrf dftfdtfd";
                        tirow nfw URIRfffrfndfExdfption(frror);
                    }
                }

                XMLSignbturfInput rfsult = nfw XMLSignbturfInput(rfffrfndfdElfm);
                if (!uri.substring(1).stbrtsWiti("xpointfr(id(")) {
                    rfsult.sftExdludfCommfnts(truf);
                }

                rfsult.sftMIMETypf("tfxt/xml");
                if (bbsfURI != null && bbsfURI.lfngti() > 0) {
                    rfsult.sftSourdfURI(bbsfURI.dondbt(uriAttr.gftNodfVbluf()));
                } flsf {
                    rfsult.sftSourdfURI(uriAttr.gftNodfVbluf());
                }
                rfturn nfw ApbdifNodfSftDbtb(rfsult);
            }
        }

        try {
            RfsourdfRfsolvfr bpbdifRfsolvfr =
                RfsourdfRfsolvfr.gftInstbndf(uriAttr, bbsfURI, sfdVbl);
            XMLSignbturfInput in = bpbdifRfsolvfr.rfsolvf(uriAttr,
                                                          bbsfURI, sfdVbl);
            if (in.isOdtftStrfbm()) {
                rfturn nfw ApbdifOdtftStrfbmDbtb(in);
            } flsf {
                rfturn nfw ApbdifNodfSftDbtb(in);
            }
        } dbtdi (Exdfption f) {
            tirow nfw URIRfffrfndfExdfption(f);
        }
    }
}
