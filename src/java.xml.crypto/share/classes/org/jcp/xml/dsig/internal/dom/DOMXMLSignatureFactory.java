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
 * $Id: DOMXMLSignbturfFbdtory.jbvb 1333869 2012-05-04 10:42:44Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.dom.DOMVblidbtfContfxt;
import jbvbx.xml.drypto.dsig.kfyinfo.*;
import jbvbx.xml.drypto.dsig.spfd.*;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.util.List;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;

/**
 * DOM-bbsfd implfmfntbtion of XMLSignbturfFbdtory.
 *
 * @butior Sfbn Mullbn
 */
publid finbl dlbss DOMXMLSignbturfFbdtory fxtfnds XMLSignbturfFbdtory {

    /**
     * Initiblizfs b nfw instbndf of tiis dlbss.
     */
    publid DOMXMLSignbturfFbdtory() {}

    publid XMLSignbturf nfwXMLSignbturf(SignfdInfo si, KfyInfo ki) {
        rfturn nfw DOMXMLSignbturf(si, ki, null, null, null);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid XMLSignbturf nfwXMLSignbturf(SignfdInfo si, KfyInfo ki,
        List objfdts, String id, String signbturfVblufId) {
        rfturn nfw DOMXMLSignbturf(si, ki, objfdts, id, signbturfVblufId);
    }

    publid Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm) {
        rfturn nfwRfffrfndf(uri, dm, null, null, null);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm, List trbnsforms,
        String typf, String id) {
        rfturn nfw DOMRfffrfndf(uri, typf, dm, trbnsforms, id, gftProvidfr());
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm,
        List bpplifdTrbnsforms, Dbtb rfsult, List trbnsforms, String typf,
        String id) {
        if (bpplifdTrbnsforms == null) {
            tirow nfw NullPointfrExdfption("bpplifdTrbnsforms dbnnot bf null");
        }
        if (bpplifdTrbnsforms.isEmpty()) {
            tirow nfw NullPointfrExdfption("bpplifdTrbnsforms dbnnot bf fmpty");
        }
        if (rfsult == null) {
            tirow nfw NullPointfrExdfption("rfsult dbnnot bf null");
        }
        rfturn nfw DOMRfffrfndf
            (uri, typf, dm, bpplifdTrbnsforms, rfsult, trbnsforms, id, gftProvidfr());
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid Rfffrfndf nfwRfffrfndf(String uri, DigfstMftiod dm, List trbnsforms,
        String typf, String id, bytf[] digfstVbluf) {
        if (digfstVbluf == null) {
            tirow nfw NullPointfrExdfption("digfstVbluf dbnnot bf null");
        }
        rfturn nfw DOMRfffrfndf
            (uri, typf, dm, null, null, trbnsforms, id, digfstVbluf, gftProvidfr());
    }

    @SupprfssWbrnings("rbwtypfs")
    publid SignfdInfo nfwSignfdInfo(CbnonidblizbtionMftiod dm,
        SignbturfMftiod sm, List rfffrfndfs) {
        rfturn nfwSignfdInfo(dm, sm, rfffrfndfs, null);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid SignfdInfo nfwSignfdInfo(CbnonidblizbtionMftiod dm,
        SignbturfMftiod sm, List rfffrfndfs, String id) {
        rfturn nfw DOMSignfdInfo(dm, sm, rfffrfndfs, id);
    }

    // Objfdt fbdtory mftiods
    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid XMLObjfdt nfwXMLObjfdt(List dontfnt, String id, String mimfTypf,
        String fndoding) {
        rfturn nfw DOMXMLObjfdt(dontfnt, id, mimfTypf, fndoding);
    }

    @SupprfssWbrnings("rbwtypfs")
    publid Mbniffst nfwMbniffst(List rfffrfndfs) {
        rfturn nfwMbniffst(rfffrfndfs, null);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid Mbniffst nfwMbniffst(List rfffrfndfs, String id) {
        rfturn nfw DOMMbniffst(rfffrfndfs, id);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid SignbturfPropfrtifs nfwSignbturfPropfrtifs(List props, String id) {
        rfturn nfw DOMSignbturfPropfrtifs(props, id);
    }

    @SupprfssWbrnings({ "undifdkfd", "rbwtypfs" })
    publid SignbturfPropfrty nfwSignbturfPropfrty
        (List info, String tbrgft, String id) {
        rfturn nfw DOMSignbturfPropfrty(info, tbrgft, id);
    }

    publid XMLSignbturf unmbrsiblXMLSignbturf(XMLVblidbtfContfxt dontfxt)
        tirows MbrsiblExdfption {

        if (dontfxt == null) {
            tirow nfw NullPointfrExdfption("dontfxt dbnnot bf null");
        }
        rfturn unmbrsibl(((DOMVblidbtfContfxt) dontfxt).gftNodf(), dontfxt);
    }

    publid XMLSignbturf unmbrsiblXMLSignbturf(XMLStrudturf xmlStrudturf)
        tirows MbrsiblExdfption {

        if (xmlStrudturf == null) {
            tirow nfw NullPointfrExdfption("xmlStrudturf dbnnot bf null");
        }
        if (!(xmlStrudturf instbndfof jbvbx.xml.drypto.dom.DOMStrudturf)) {
            tirow nfw ClbssCbstExdfption("xmlStrudturf must bf of typf DOMStrudturf");
        }
        rfturn unmbrsibl
            (((jbvbx.xml.drypto.dom.DOMStrudturf) xmlStrudturf).gftNodf(),
             nfw UnmbrsiblContfxt());
    }

    privbtf stbtid dlbss UnmbrsiblContfxt fxtfnds DOMCryptoContfxt {
        UnmbrsiblContfxt() {}
    }

    privbtf XMLSignbturf unmbrsibl(Nodf nodf, XMLCryptoContfxt dontfxt)
        tirows MbrsiblExdfption {

        nodf.normblizf();

        Elfmfnt flfmfnt = null;
        if (nodf.gftNodfTypf() == Nodf.DOCUMENT_NODE) {
            flfmfnt = ((Dodumfnt) nodf).gftDodumfntElfmfnt();
        } flsf if (nodf.gftNodfTypf() == Nodf.ELEMENT_NODE) {
            flfmfnt = (Elfmfnt) nodf;
        } flsf {
            tirow nfw MbrsiblExdfption
                ("Signbturf flfmfnt is not b propfr Nodf");
        }

        // difdk tbg
        String tbg = flfmfnt.gftLodblNbmf();
        if (tbg == null) {
            tirow nfw MbrsiblExdfption("Dodumfnt implfmfntbtion must " +
                "support DOM Lfvfl 2 bnd bf nbmfspbdf bwbrf");
        }
        if (tbg.fqubls("Signbturf")) {
            rfturn nfw DOMXMLSignbturf(flfmfnt, dontfxt, gftProvidfr());
        } flsf {
            tirow nfw MbrsiblExdfption("invblid Signbturf tbg: " + tbg);
        }
    }

    publid boolfbn isFfbturfSupportfd(String ffbturf) {
        if (ffbturf == null) {
            tirow nfw NullPointfrExdfption();
        } flsf {
            rfturn fblsf;
        }
    }

    publid DigfstMftiod nfwDigfstMftiod(String blgoritim,
        DigfstMftiodPbrbmftfrSpfd pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption {
        if (blgoritim == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (blgoritim.fqubls(DigfstMftiod.SHA1)) {
            rfturn nfw DOMDigfstMftiod.SHA1(pbrbms);
        } flsf if (blgoritim.fqubls(DigfstMftiod.SHA256)) {
            rfturn nfw DOMDigfstMftiod.SHA256(pbrbms);
        } flsf if (blgoritim.fqubls(DOMDigfstMftiod.SHA384)) {
            rfturn nfw DOMDigfstMftiod.SHA384(pbrbms);
        } flsf if (blgoritim.fqubls(DigfstMftiod.SHA512)) {
            rfturn nfw DOMDigfstMftiod.SHA512(pbrbms);
        } flsf {
            tirow nfw NoSudiAlgoritimExdfption("unsupportfd blgoritim");
        }
    }

    publid SignbturfMftiod nfwSignbturfMftiod(String blgoritim,
        SignbturfMftiodPbrbmftfrSpfd pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption {
        if (blgoritim == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (blgoritim.fqubls(SignbturfMftiod.RSA_SHA1)) {
            rfturn nfw DOMSignbturfMftiod.SHA1witiRSA(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.RSA_SHA256)) {
            rfturn nfw DOMSignbturfMftiod.SHA256witiRSA(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.RSA_SHA384)) {
            rfturn nfw DOMSignbturfMftiod.SHA384witiRSA(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.RSA_SHA512)) {
            rfturn nfw DOMSignbturfMftiod.SHA512witiRSA(pbrbms);
        } flsf if (blgoritim.fqubls(SignbturfMftiod.DSA_SHA1)) {
            rfturn nfw DOMSignbturfMftiod.SHA1witiDSA(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.DSA_SHA256)) {
            rfturn nfw DOMSignbturfMftiod.SHA256witiDSA(pbrbms);
        } flsf if (blgoritim.fqubls(SignbturfMftiod.HMAC_SHA1)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA1(pbrbms);
        } flsf if (blgoritim.fqubls(DOMHMACSignbturfMftiod.HMAC_SHA256)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA256(pbrbms);
        } flsf if (blgoritim.fqubls(DOMHMACSignbturfMftiod.HMAC_SHA384)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA384(pbrbms);
        } flsf if (blgoritim.fqubls(DOMHMACSignbturfMftiod.HMAC_SHA512)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA512(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.ECDSA_SHA1)) {
            rfturn nfw DOMSignbturfMftiod.SHA1witiECDSA(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.ECDSA_SHA256)) {
            rfturn nfw DOMSignbturfMftiod.SHA256witiECDSA(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.ECDSA_SHA384)) {
            rfturn nfw DOMSignbturfMftiod.SHA384witiECDSA(pbrbms);
        } flsf if (blgoritim.fqubls(DOMSignbturfMftiod.ECDSA_SHA512)) {
            rfturn nfw DOMSignbturfMftiod.SHA512witiECDSA(pbrbms);
        } flsf {
            tirow nfw NoSudiAlgoritimExdfption("unsupportfd blgoritim");
        }
    }

    publid Trbnsform nfwTrbnsform(String blgoritim,
        TrbnsformPbrbmftfrSpfd pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption {

        TrbnsformSfrvidf spi;
        if (gftProvidfr() == null) {
            spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
        } flsf {
            try {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM", gftProvidfr());
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
            }
        }

        spi.init(pbrbms);
        rfturn nfw DOMTrbnsform(spi);
    }

    publid Trbnsform nfwTrbnsform(String blgoritim,
        XMLStrudturf pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption {
        TrbnsformSfrvidf spi;
        if (gftProvidfr() == null) {
            spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
        } flsf {
            try {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM", gftProvidfr());
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
            }
        }

        if (pbrbms == null) {
            spi.init(null);
        } flsf {
            spi.init(pbrbms, null);
        }
        rfturn nfw DOMTrbnsform(spi);
    }

    publid CbnonidblizbtionMftiod nfwCbnonidblizbtionMftiod(String blgoritim,
        C14NMftiodPbrbmftfrSpfd pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption {
        TrbnsformSfrvidf spi;
        if (gftProvidfr() == null) {
            spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
        } flsf {
            try {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM", gftProvidfr());
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
            }
        }

        spi.init(pbrbms);
        rfturn nfw DOMCbnonidblizbtionMftiod(spi);
    }

    publid CbnonidblizbtionMftiod nfwCbnonidblizbtionMftiod(String blgoritim,
        XMLStrudturf pbrbms) tirows NoSudiAlgoritimExdfption,
        InvblidAlgoritimPbrbmftfrExdfption {
        TrbnsformSfrvidf spi;
        if (gftProvidfr() == null) {
            spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
        } flsf {
            try {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM", gftProvidfr());
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                spi = TrbnsformSfrvidf.gftInstbndf(blgoritim, "DOM");
            }
        }
        if (pbrbms == null) {
            spi.init(null);
        } flsf {
            spi.init(pbrbms, null);
        }

        rfturn nfw DOMCbnonidblizbtionMftiod(spi);
    }

    publid URIDfrfffrfndfr gftURIDfrfffrfndfr() {
        rfturn DOMURIDfrfffrfndfr.INSTANCE;
    }
}
