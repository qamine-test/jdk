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
 * $Id: ApbdifCbnonidblizfr.jbvb 1333869 2012-05-04 10:42:44Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.io.BytfArrbyOutputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.util.Sft;
import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.TrbnsformExdfption;
import jbvbx.xml.drypto.dsig.TrbnsformSfrvidf;
import jbvbx.xml.drypto.dsig.spfd.C14NMftiodPbrbmftfrSpfd;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.Cbnonidblizfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.d14n.InvblidCbnonidblizfrExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfInput;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.trbnsforms.Trbnsform;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

publid bbstrbdt dlbss ApbdifCbnonidblizfr fxtfnds TrbnsformSfrvidf {

    stbtid {
        dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.Init.init();
    }

    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr("org.jdp.xml.dsig.intfrnbl.dom");
    protfdtfd Cbnonidblizfr bpbdifCbnonidblizfr;
    privbtf Trbnsform bpbdifTrbnsform;
    protfdtfd String indlusivfNbmfspbdfs;
    protfdtfd C14NMftiodPbrbmftfrSpfd pbrbms;
    protfdtfd Dodumfnt ownfrDod;
    protfdtfd Elfmfnt trbnsformElfm;

    publid finbl AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd()
    {
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
            ((jbvbx.xml.drypto.dom.DOMStrudturf)pbrfnt).gftNodf();
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
            ((jbvbx.xml.drypto.dom.DOMStrudturf)pbrfnt).gftNodf();
        ownfrDod = DOMUtils.gftOwnfrDodumfnt(trbnsformElfm);
    }

    publid Dbtb dbnonidblizf(Dbtb dbtb, XMLCryptoContfxt xd)
        tirows TrbnsformExdfption
    {
        rfturn dbnonidblizf(dbtb, xd, null);
    }

    publid Dbtb dbnonidblizf(Dbtb dbtb, XMLCryptoContfxt xd, OutputStrfbm os)
        tirows TrbnsformExdfption
    {
        if (bpbdifCbnonidblizfr == null) {
            try {
                bpbdifCbnonidblizfr = Cbnonidblizfr.gftInstbndf(gftAlgoritim());
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbtfd dbnonidblizfr for blgoritim: " + gftAlgoritim());
                }
            } dbtdi (InvblidCbnonidblizfrExdfption idf) {
                tirow nfw TrbnsformExdfption
                    ("Couldn't find Cbnonidblizfr for: " + gftAlgoritim() +
                     ": " + idf.gftMfssbgf(), idf);
            }
        }

        if (os != null) {
            bpbdifCbnonidblizfr.sftWritfr(os);
        } flsf {
            bpbdifCbnonidblizfr.sftWritfr(nfw BytfArrbyOutputStrfbm());
        }

        try {
            Sft<Nodf> nodfSft = null;
            if (dbtb instbndfof ApbdifDbtb) {
                XMLSignbturfInput in =
                    ((ApbdifDbtb)dbtb).gftXMLSignbturfInput();
                if (in.isElfmfnt()) {
                    if (indlusivfNbmfspbdfs != null) {
                        rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm
                            (bpbdifCbnonidblizfr.dbnonidblizfSubtrff
                                (in.gftSubNodf(), indlusivfNbmfspbdfs)));
                    } flsf {
                        rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm
                            (bpbdifCbnonidblizfr.dbnonidblizfSubtrff
                                (in.gftSubNodf())));
                    }
                } flsf if (in.isNodfSft()) {
                    nodfSft = in.gftNodfSft();
                } flsf {
                    rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm(
                        bpbdifCbnonidblizfr.dbnonidblizf(
                            Utils.rfbdBytfsFromStrfbm(in.gftOdtftStrfbm()))));
                }
            } flsf if (dbtb instbndfof DOMSubTrffDbtb) {
                DOMSubTrffDbtb subTrff = (DOMSubTrffDbtb)dbtb;
                if (indlusivfNbmfspbdfs != null) {
                    rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm
                        (bpbdifCbnonidblizfr.dbnonidblizfSubtrff
                         (subTrff.gftRoot(), indlusivfNbmfspbdfs)));
                } flsf {
                    rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm
                        (bpbdifCbnonidblizfr.dbnonidblizfSubtrff
                         (subTrff.gftRoot())));
                }
            } flsf if (dbtb instbndfof NodfSftDbtb) {
                NodfSftDbtb nsd = (NodfSftDbtb)dbtb;
                // donvfrt Itfrbtor to Sft
                @SupprfssWbrnings("undifdkfd")
                Sft<Nodf> ns = Utils.toNodfSft(nsd.itfrbtor());
                nodfSft = ns;
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Cbnonidblizing " + nodfSft.sizf() + " nodfs");
                }
            } flsf {
                rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm(
                    bpbdifCbnonidblizfr.dbnonidblizf(
                        Utils.rfbdBytfsFromStrfbm(
                        ((OdtftStrfbmDbtb)dbtb).gftOdtftStrfbm()))));
            }
            if (indlusivfNbmfspbdfs != null) {
                rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm(
                    bpbdifCbnonidblizfr.dbnonidblizfXPbtiNodfSft
                        (nodfSft, indlusivfNbmfspbdfs)));
            } flsf {
                rfturn nfw OdtftStrfbmDbtb(nfw BytfArrbyInputStrfbm(
                    bpbdifCbnonidblizfr.dbnonidblizfXPbtiNodfSft(nodfSft)));
            }
        } dbtdi (Exdfption f) {
            tirow nfw TrbnsformExdfption(f);
        }
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

        if (ownfrDod == null) {
            tirow nfw TrbnsformExdfption("trbnsform must bf mbrsibllfd");
        }

        if (bpbdifTrbnsform == null) {
            try {
                bpbdifTrbnsform =
                    nfw Trbnsform(ownfrDod, gftAlgoritim(), trbnsformElfm.gftCiildNodfs());
                bpbdifTrbnsform.sftElfmfnt(trbnsformElfm, xd.gftBbsfURI());
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbtfd trbnsform for blgoritim: " + gftAlgoritim());
                }
            } dbtdi (Exdfption fx) {
                tirow nfw TrbnsformExdfption
                    ("Couldn't find Trbnsform for: " + gftAlgoritim(), fx);
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
            in = bpbdifTrbnsform.pfrformTrbnsform(in, os);
            if (!in.isNodfSft() && !in.isElfmfnt()) {
                rfturn null;
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
