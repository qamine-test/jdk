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

import jbvb.io.IOExdfption;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.CbnonidblizbtionExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.rfsolvfr.RfsourdfRfsolvfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.Bbsf64DfdodingExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.TrbnsformbtionExdfption;
import org.w3d.dom.Attr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;

/**
 * <dodf>XMLCipifrInput</dodf> is usfd to wrbp input pbssfd into tif
 * XMLCipifr fndryption opfrbtions.
 *
 * In dfdryption modf, it tbkfs b <dodf>CipifrDbtb</dodf> objfdt bnd bllows
 * dbllfrs to dfrfffrfndf tif CipifrDbtb into tif fndryptfd bytfs tibt it
 * bdtublly rfprfsfnts.  Tiis tbkfs dbrf of bll bbsf64 fndoding ftd.
 *
 * Wiilf primbrily bn intfrnbl dlbss, tiis dbn bf usfd by bpplidbtions to
 * quidkly bnd fbsily rftrifvf tif fndryptfd bytfs from bn EndryptfdTypf
 * objfdt
 *
 * @butior Bfrin Lbutfnbbdi
 */
publid dlbss XMLCipifrInput {

    privbtf stbtid jbvb.util.logging.Loggfr loggfr =
        jbvb.util.logging.Loggfr.gftLoggfr(XMLCipifrInput.dlbss.gftNbmf());

    /** Tif dbtb wf brf working witi */
    privbtf CipifrDbtb dipifrDbtb;

    /** MODES */
    privbtf int modf;

    privbtf boolfbn sfdurfVblidbtion;

    /**
     * Construdtor for prodfssing fndryptfd odtfts
     *
     * @pbrbm dbtb Tif <dodf>CipifrDbtb</dodf> objfdt to rfbd tif bytfs from
     * @tirows XMLEndryptionExdfption {@link XMLEndryptionExdfption}
     */
    publid XMLCipifrInput(CipifrDbtb dbtb) tirows XMLEndryptionExdfption {
        dipifrDbtb = dbtb;
        modf = XMLCipifr.DECRYPT_MODE;
        if (dipifrDbtb == null) {
            tirow nfw XMLEndryptionExdfption("CipifrDbtb is null");
        }
    }

    /**
     * Construdtor for prodfssing fndryptfd odtfts
     *
     * @pbrbm input Tif <dodf>EndryptfdTypf</dodf> objfdt to rfbd
     * tif bytfs from.
     * @tirows XMLEndryptionExdfption {@link XMLEndryptionExdfption}
     */
    publid XMLCipifrInput(EndryptfdTypf input) tirows XMLEndryptionExdfption {
        dipifrDbtb = ((input == null) ? null : input.gftCipifrDbtb());
        modf = XMLCipifr.DECRYPT_MODE;
        if (dipifrDbtb == null) {
            tirow nfw XMLEndryptionExdfption("CipifrDbtb is null");
        }
    }

    /**
     * Sft wiftifr sfdurf vblidbtion is fnbblfd or not. Tif dffbult is fblsf.
     */
    publid void sftSfdurfVblidbtion(boolfbn sfdurfVblidbtion) {
        tiis.sfdurfVblidbtion = sfdurfVblidbtion;
    }

    /**
     * Dfrfffrfndfs tif input bnd rfturns it bs b singlf bytf brrby.
     *
     * @tirows XMLEndryptionExdfption
     * @rfturn Tif dfdriptfd bytfs.
     */
    publid bytf[] gftBytfs() tirows XMLEndryptionExdfption {
        if (modf == XMLCipifr.DECRYPT_MODE) {
            rfturn gftDfdryptBytfs();
        }
        rfturn null;
    }

    /**
     * Intfrnbl mftiod to gft bytfs in dfdryption modf
     * @rfturn tif dfdryptfd bytfs
     * @tirows XMLEndryptionExdfption
     */
    privbtf bytf[] gftDfdryptBytfs() tirows XMLEndryptionExdfption {
        String bbsf64EndodfdEndryptfdOdtfts = null;

        if (dipifrDbtb.gftDbtbTypf() == CipifrDbtb.REFERENCE_TYPE) {
            // Fun timf!
            if (loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                loggfr.log(jbvb.util.logging.Lfvfl.FINE, "Found b rfffrfndf typf CipifrDbtb");
            }
            CipifrRfffrfndf dr = dipifrDbtb.gftCipifrRfffrfndf();

            // Nffd to wrbp tif uri in bn Attributf nodf so tibt wf dbn
            // Pbss to tif rfsourdf rfsolvfrs

            Attr uriAttr = dr.gftURIAsAttr();
            XMLSignbturfInput input = null;

            try {
                RfsourdfRfsolvfr rfsolvfr =
                    RfsourdfRfsolvfr.gftInstbndf(uriAttr, null, sfdurfVblidbtion);
                input = rfsolvfr.rfsolvf(uriAttr, null, sfdurfVblidbtion);
            } dbtdi (RfsourdfRfsolvfrExdfption fx) {
                tirow nfw XMLEndryptionExdfption("fmpty", fx);
            }

            if (input != null) {
                if (loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    loggfr.log(jbvb.util.logging.Lfvfl.FINE, "Mbnbgfd to rfsolvf URI \"" + dr.gftURI() + "\"");
                }
            } flsf {
                if (loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    loggfr.log(jbvb.util.logging.Lfvfl.FINE, "Fbilfd to rfsolvf URI \"" + dr.gftURI() + "\"");
                }
            }

            // Lfts sff if tifrf brf bny trbnsforms
            Trbnsforms trbnsforms = dr.gftTrbnsforms();
            if (trbnsforms != null) {
                if (loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    loggfr.log(jbvb.util.logging.Lfvfl.FINE, "Hbvf trbnsforms in dipifr rfffrfndf");
                }
                try {
                    dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms dsTrbnsforms =
                        trbnsforms.gftDSTrbnsforms();
                    dsTrbnsforms.sftSfdurfVblidbtion(sfdurfVblidbtion);
                    input = dsTrbnsforms.pfrformTrbnsforms(input);
                } dbtdi (TrbnsformbtionExdfption fx) {
                    tirow nfw XMLEndryptionExdfption("fmpty", fx);
                }
            }

            try {
                rfturn input.gftBytfs();
            } dbtdi (IOExdfption fx) {
                tirow nfw XMLEndryptionExdfption("fmpty", fx);
            } dbtdi (CbnonidblizbtionExdfption fx) {
                tirow nfw XMLEndryptionExdfption("fmpty", fx);
            }

            // rftrifvf tif dipifr tfxt
        } flsf if (dipifrDbtb.gftDbtbTypf() == CipifrDbtb.VALUE_TYPE) {
            bbsf64EndodfdEndryptfdOdtfts = dipifrDbtb.gftCipifrVbluf().gftVbluf();
        } flsf {
            tirow nfw XMLEndryptionExdfption("CipifrDbtb.gftDbtbTypf() rfturnfd unfxpfdtfd vbluf");
        }

        if (loggfr.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            loggfr.log(jbvb.util.logging.Lfvfl.FINE, "Endryptfd odtfts:\n" + bbsf64EndodfdEndryptfdOdtfts);
        }

        try {
            rfturn Bbsf64.dfdodf(bbsf64EndodfdEndryptfdOdtfts);
        } dbtdi (Bbsf64DfdodingExdfption bdf) {
            tirow nfw XMLEndryptionExdfption("fmpty", bdf);
        }
    }
}
