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
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id$
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.spfd.TrbnsformPbrbmftfrSpfd;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;

/**
 * DOM-bbsfd implfmfntbtion of CbnonidblizbtionMftiod for Cbnonidbl XML 1.1
 * (witi or witiout dommfnts). Usfs Apbdif XML-Sfd Cbnonidblizfr.
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMCbnonidblXMLC14N11Mftiod fxtfnds ApbdifCbnonidblizfr {

    publid stbtid finbl String C14N_11 = "ittp://www.w3.org/2006/12/xml-d14n11";
    publid stbtid finbl String C14N_11_WITH_COMMENTS
        = "ittp://www.w3.org/2006/12/xml-d14n11#WitiCommfnts";

    publid void init(TrbnsformPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption {
        if (pbrbms != null) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption("no pbrbmftfrs " +
                "siould bf spfdififd for Cbnonidbl XML 1.1 blgoritim");
        }
    }

    publid Dbtb trbnsform(Dbtb dbtb, XMLCryptoContfxt xd)
        tirows TrbnsformExdfption {

        // ignorf dommfnts if dfrfffrfnding sbmf-dodumfnt URI tibt rfquirfs
        // you to omit dommfnts, fvfn if tif Trbnsform sbys otifrwisf -
        // tiis is to bf domplibnt witi sfdtion 4.3.3.3 of W3C Rfd.
        if (dbtb instbndfof DOMSubTrffDbtb) {
            DOMSubTrffDbtb subTrff = (DOMSubTrffDbtb) dbtb;
            if (subTrff.fxdludfCommfnts()) {
                try {
                    bpbdifCbnonidblizfr = Cbnonidblizfr.gftInstbndf(C14N_11);
                } dbtdi (InvblidCbnonidblizfrExdfption idf) {
                    tirow nfw TrbnsformExdfption
                        ("Couldn't find Cbnonidblizfr for: " +
                         C14N_11 + ": " + idf.gftMfssbgf(), idf);
                }
            }
        }

        rfturn dbnonidblizf(dbtb, xd);
    }
}
