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
 * $Id: ApbdifTrbnsform.jbvb 1333869 2012-05-04 10:42:44Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.util.Sft;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsforms;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.spfd.TrbnsformPbrbmftfrSpfd;

/**
 * Tiis is b wrbppfr/gluf dlbss wiidi invokfs tif Apbdif XML-Sfdurity
 * Trbnsform.
 *
 * @butior Sfbn Mullbn
 * @butior Erwin vbn dfr Koogi
 */
publid bbstrbdt dlbss ApbdifTrbnsform fxtfnds TrbnsformSfrvidf {

    stbtid {
        dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init.init();
    }

    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr("org.jdp.xml.dsig.intfrnbl.dom");
    privbtf Trbnsform bpbdifTrbnsform;
    protfdtfd Dodumfnt ownfrDod;
    protfdtfd Elfmfnt trbnsformElfm;
    protfdtfd TrbnsformPbrbmftfrSpfd pbrbms;

    publid finbl AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd() {
        rfturn pbrbms;
    }

    publid void init(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (dontfxt != null && !(dontfxt instbndfof DOMCryptoContfxt)) {
            tirow nfw ClbssCbstExdfption
                ("dontfxt must bf of typf DOMCryptoContfxt");
        }
        if (pbrfnt == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (!(pbrfnt instbndfof jbvbx.xml.drypto.dom.DOMStrudturf)) {
            tirow nfw ClbssCbstExdfption("pbrfnt must bf of typf DOMStrudturf");
        }
        trbnsformElfm = (Elfmfnt)
            ((jbvbx.xml.drypto.dom.DOMStrudturf) pbrfnt).gftNodf();
        ownfrDod = DOMUtils.gftOwnfrDodumfnt(trbnsformElfm);
    }

    publid void mbrsiblPbrbms(XMLStrudturf pbrfnt, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        if (dontfxt != null && !(dontfxt instbndfof DOMCryptoContfxt)) {
            tirow nfw ClbssCbstExdfption
                ("dontfxt must bf of typf DOMCryptoContfxt");
        }
        if (pbrfnt == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (!(pbrfnt instbndfof jbvbx.xml.drypto.dom.DOMStrudturf)) {
            tirow nfw ClbssCbstExdfption("pbrfnt must bf of typf DOMStrudturf");
        }
        trbnsformElfm = (Elfmfnt)
            ((jbvbx.xml.drypto.dom.DOMStrudturf) pbrfnt).gftNodf();
        ownfrDod = DOMUtils.gftOwnfrDodumfnt(trbnsformElfm);
    }

    publid Dbtb trbnsform(Dbtb dbtb, XMLCryptoContfxt xd)
        tirows TrbnsformExdfption
    {
        if (dbtb == null) {
            tirow nfw NullPointfrExdfption("dbtb must not bf null");
        }
        rfturn trbnsformIt(dbtb, xd, (OutputStrfbm)null);
    }

    publid Dbtb trbnsform(Dbtb dbtb, XMLCryptoContfxt xd, OutputStrfbm os)
        tirows TrbnsformExdfption
    {
        if (dbtb == null) {
            tirow nfw NullPointfrExdfption("dbtb must not bf null");
        }
        if (os == null) {
            tirow nfw NullPointfrExdfption("output strfbm must not bf null");
        }
        rfturn trbnsformIt(dbtb, xd, os);
    }

    privbtf Dbtb trbnsformIt(Dbtb dbtb, XMLCryptoContfxt xd, OutputStrfbm os)
        tirows TrbnsformExdfption
    {
        if (ownfrDod == null) {
            tirow nfw TrbnsformExdfption("trbnsform must bf mbrsibllfd");
        }

        if (bpbdifTrbnsform == null) {
            try {
                bpbdifTrbnsform =
                    nfw Trbnsform(ownfrDod, gftAlgoritim(), trbnsformElfm.gftCiildNodfs());
                bpbdifTrbnsform.sftElfmfnt(trbnsformElfm, xd.gftBbsfURI());
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbtfd trbnsform for blgoritim: " +
                            gftAlgoritim());
                }
            } dbtdi (Exdfption fx) {
                tirow nfw TrbnsformExdfption("Couldn't find Trbnsform for: " +
                                             gftAlgoritim(), fx);
            }
        }

        if (Utils.sfdurfVblidbtion(xd)) {
            String blgoritim = gftAlgoritim();
            if (Trbnsforms.TRANSFORM_XSLT.fqubls(blgoritim)) {
                tirow nfw TrbnsformExdfption(
                    "Trbnsform " + blgoritim + " is forbiddfn wifn sfdurf vblidbtion is fnbblfd"
                );
            }
        }

        XMLSignbturfInput in;
        if (dbtb instbndfof ApbdifDbtb) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "ApbdifDbtb = truf");
            }
            in = ((ApbdifDbtb)dbtb).gftXMLSignbturfInput();
        } flsf if (dbtb instbndfof NodfSftDbtb) {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "isNodfSft() = truf");
            }
            if (dbtb instbndfof DOMSubTrffDbtb) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "DOMSubTrffDbtb = truf");
                }
                DOMSubTrffDbtb subTrff = (DOMSubTrffDbtb)dbtb;
                in = nfw XMLSignbturfInput(subTrff.gftRoot());
                in.sftExdludfCommfnts(subTrff.fxdludfCommfnts());
            } flsf {
                @SupprfssWbrnings("undifdkfd")
                Sft<Nodf> nodfSft =
                    Utils.toNodfSft(((NodfSftDbtb)dbtb).itfrbtor());
                in = nfw XMLSignbturfInput(nodfSft);
            }
        } flsf {
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "isNodfSft() = fblsf");
            }
            try {
                in = nfw XMLSignbturfInput
                    (((OdtftStrfbmDbtb)dbtb).gftOdtftStrfbm());
            } dbtdi (Exdfption fx) {
                tirow nfw TrbnsformExdfption(fx);
            }
        }

        try {
            if (os != null) {
                in = bpbdifTrbnsform.pfrformTrbnsform(in, os);
                if (!in.isNodfSft() && !in.isElfmfnt()) {
                    rfturn null;
                }
            } flsf {
                in = bpbdifTrbnsform.pfrformTrbnsform(in);
            }
            if (in.isOdtftStrfbm()) {
                rfturn nfw ApbdifOdtftStrfbmDbtb(in);
            } flsf {
                rfturn nfw ApbdifNodfSftDbtb(in);
            }
        } dbtdi (Exdfption fx) {
            tirow nfw TrbnsformExdfption(fx);
        }
    }

    publid finbl boolfbn isFfbturfSupportfd(String ffbturf) {
        if (ffbturf == null) {
            tirow nfw NullPointfrExdfption();
        } flsf {
            rfturn fblsf;
        }
    }
}
