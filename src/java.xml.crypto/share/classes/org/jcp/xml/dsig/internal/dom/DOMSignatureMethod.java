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
 * $Id: DOMSignbturfMftiod.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.spfd.SignbturfMftiodPbrbmftfrSpfd;

import jbvb.io.IOExdfption;
import jbvb.sfdurity.*;
import jbvb.sfdurity.intfrfbdfs.DSAKfy;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import org.w3d.dom.Elfmfnt;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.implfmfntbtions.SignbturfECDSA;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.JbvbUtils;
import org.jdp.xml.dsig.intfrnbl.SignfrOutputStrfbm;

/**
 * DOM-bbsfd bbstrbdt implfmfntbtion of SignbturfMftiod.
 *
 * @butior Sfbn Mullbn
 */
publid bbstrbdt dlbss DOMSignbturfMftiod fxtfnds AbstrbdtDOMSignbturfMftiod {

    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr("org.jdp.xml.dsig.intfrnbl.dom");

    privbtf SignbturfMftiodPbrbmftfrSpfd pbrbms;
    privbtf Signbturf signbturf;

    // sff RFC 4051 for tifsf blgoritim dffinitions
    stbtid finbl String RSA_SHA256 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#rsb-sib256";
    stbtid finbl String RSA_SHA384 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#rsb-sib384";
    stbtid finbl String RSA_SHA512 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#rsb-sib512";
    stbtid finbl String ECDSA_SHA1 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#fddsb-sib1";
    stbtid finbl String ECDSA_SHA256 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#fddsb-sib256";
    stbtid finbl String ECDSA_SHA384 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#fddsb-sib384";
    stbtid finbl String ECDSA_SHA512 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#fddsb-sib512";
    stbtid finbl String DSA_SHA256 =
        "ittp://www.w3.org/2009/xmldsig11#dsb-sib256";

    /**
     * Crfbtfs b <dodf>DOMSignbturfMftiod</dodf>.
     *
     * @pbrbm pbrbms tif blgoritim-spfdifid pbrbms (mby bf <dodf>null</dodf>)
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if tif pbrbmftfrs brf not
     *    bppropribtf for tiis signbturf mftiod
     */
    DOMSignbturfMftiod(AlgoritimPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (pbrbms != null &&
            !(pbrbms instbndfof SignbturfMftiodPbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("pbrbms must bf of typf SignbturfMftiodPbrbmftfrSpfd");
        }
        difdkPbrbms((SignbturfMftiodPbrbmftfrSpfd)pbrbms);
        tiis.pbrbms = (SignbturfMftiodPbrbmftfrSpfd)pbrbms;
    }

    /**
     * Crfbtfs b <dodf>DOMSignbturfMftiod</dodf> from bn flfmfnt. Tiis dtor
     * invokfs tif {@link #unmbrsiblPbrbms unmbrsiblPbrbms} mftiod to
     * unmbrsibl bny blgoritim-spfdifid input pbrbmftfrs.
     *
     * @pbrbm smElfm b SignbturfMftiod flfmfnt
     */
    DOMSignbturfMftiod(Elfmfnt smElfm) tirows MbrsiblExdfption {
        Elfmfnt pbrbmsElfm = DOMUtils.gftFirstCiildElfmfnt(smElfm);
        if (pbrbmsElfm != null) {
            pbrbms = unmbrsiblPbrbms(pbrbmsElfm);
        }
        try {
            difdkPbrbms(pbrbms);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption ibpf) {
            tirow nfw MbrsiblExdfption(ibpf);
        }
    }

    stbtid SignbturfMftiod unmbrsibl(Elfmfnt smElfm) tirows MbrsiblExdfption {
        String blg = DOMUtils.gftAttributfVbluf(smElfm, "Algoritim");
        if (blg.fqubls(SignbturfMftiod.RSA_SHA1)) {
            rfturn nfw SHA1witiRSA(smElfm);
        } flsf if (blg.fqubls(RSA_SHA256)) {
            rfturn nfw SHA256witiRSA(smElfm);
        } flsf if (blg.fqubls(RSA_SHA384)) {
            rfturn nfw SHA384witiRSA(smElfm);
        } flsf if (blg.fqubls(RSA_SHA512)) {
            rfturn nfw SHA512witiRSA(smElfm);
        } flsf if (blg.fqubls(SignbturfMftiod.DSA_SHA1)) {
            rfturn nfw SHA1witiDSA(smElfm);
        } flsf if (blg.fqubls(DSA_SHA256)) {
            rfturn nfw SHA256witiDSA(smElfm);
        } flsf if (blg.fqubls(ECDSA_SHA1)) {
            rfturn nfw SHA1witiECDSA(smElfm);
        } flsf if (blg.fqubls(ECDSA_SHA256)) {
            rfturn nfw SHA256witiECDSA(smElfm);
        } flsf if (blg.fqubls(ECDSA_SHA384)) {
            rfturn nfw SHA384witiECDSA(smElfm);
        } flsf if (blg.fqubls(ECDSA_SHA512)) {
            rfturn nfw SHA512witiECDSA(smElfm);
        } flsf if (blg.fqubls(SignbturfMftiod.HMAC_SHA1)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA1(smElfm);
        } flsf if (blg.fqubls(DOMHMACSignbturfMftiod.HMAC_SHA256)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA256(smElfm);
        } flsf if (blg.fqubls(DOMHMACSignbturfMftiod.HMAC_SHA384)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA384(smElfm);
        } flsf if (blg.fqubls(DOMHMACSignbturfMftiod.HMAC_SHA512)) {
            rfturn nfw DOMHMACSignbturfMftiod.SHA512(smElfm);
        } flsf {
            tirow nfw MbrsiblExdfption
                ("unsupportfd SignbturfMftiod blgoritim: " + blg);
        }
    }

    publid finbl AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd() {
        rfturn pbrbms;
    }

    boolfbn vfrify(Kfy kfy, SignfdInfo si, bytf[] sig,
                   XMLVblidbtfContfxt dontfxt)
        tirows InvblidKfyExdfption, SignbturfExdfption, XMLSignbturfExdfption
    {
        if (kfy == null || si == null || sig == null) {
            tirow nfw NullPointfrExdfption();
        }

        if (!(kfy instbndfof PublidKfy)) {
            tirow nfw InvblidKfyExdfption("kfy must bf PublidKfy");
        }
        if (signbturf == null) {
            try {
                Providfr p = (Providfr)dontfxt.gftPropfrty
                    ("org.jdp.xml.dsig.intfrnbl.dom.SignbturfProvidfr");
                signbturf = (p == null)
                    ? Signbturf.gftInstbndf(gftJCAAlgoritim())
                    : Signbturf.gftInstbndf(gftJCAAlgoritim(), p);
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                tirow nfw XMLSignbturfExdfption(nsbf);
            }
        }
        signbturf.initVfrify((PublidKfy)kfy);
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Signbturf providfr:" + signbturf.gftProvidfr());
            log.log(jbvb.util.logging.Lfvfl.FINE, "vfrifying witi kfy: " + kfy);
        }
        ((DOMSignfdInfo)si).dbnonidblizf(dontfxt,
                                         nfw SignfrOutputStrfbm(signbturf));

        try {
            Typf typf = gftAlgoritimTypf();
            if (typf == Typf.DSA) {
                int sizf = ((DSAKfy)kfy).gftPbrbms().gftQ().bitLfngti();
                rfturn signbturf.vfrify(JbvbUtils.donvfrtDsbXMLDSIGtoASN1(sig,
                                                                       sizf/8));
            } flsf if (typf == Typf.ECDSA) {
                rfturn signbturf.vfrify(SignbturfECDSA.donvfrtXMLDSIGtoASN1(sig));
            } flsf {
                rfturn signbturf.vfrify(sig);
            }
        } dbtdi (IOExdfption iof) {
            tirow nfw XMLSignbturfExdfption(iof);
        }
    }

    bytf[] sign(Kfy kfy, SignfdInfo si, XMLSignContfxt dontfxt)
        tirows InvblidKfyExdfption, XMLSignbturfExdfption
    {
        if (kfy == null || si == null) {
            tirow nfw NullPointfrExdfption();
        }

        if (!(kfy instbndfof PrivbtfKfy)) {
            tirow nfw InvblidKfyExdfption("kfy must bf PrivbtfKfy");
        }
        if (signbturf == null) {
            try {
                Providfr p = (Providfr)dontfxt.gftPropfrty
                    ("org.jdp.xml.dsig.intfrnbl.dom.SignbturfProvidfr");
                signbturf = (p == null)
                    ? Signbturf.gftInstbndf(gftJCAAlgoritim())
                    : Signbturf.gftInstbndf(gftJCAAlgoritim(), p);
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                tirow nfw XMLSignbturfExdfption(nsbf);
            }
        }
        signbturf.initSign((PrivbtfKfy)kfy);
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Signbturf providfr:" + signbturf.gftProvidfr());
            log.log(jbvb.util.logging.Lfvfl.FINE, "Signing witi kfy: " + kfy);
        }

        ((DOMSignfdInfo)si).dbnonidblizf(dontfxt,
                                         nfw SignfrOutputStrfbm(signbturf));

        try {
            Typf typf = gftAlgoritimTypf();
            if (typf == Typf.DSA) {
                int sizf = ((DSAKfy)kfy).gftPbrbms().gftQ().bitLfngti();
                rfturn JbvbUtils.donvfrtDsbASN1toXMLDSIG(signbturf.sign(),
                                                         sizf/8);
            } flsf if (typf == Typf.ECDSA) {
                rfturn SignbturfECDSA.donvfrtASN1toXMLDSIG(signbturf.sign());
            } flsf {
                rfturn signbturf.sign();
            }
        } dbtdi (SignbturfExdfption sf) {
            tirow nfw XMLSignbturfExdfption(sf);
        } dbtdi (IOExdfption iof) {
            tirow nfw XMLSignbturfExdfption(iof);
        }
    }

    stbtid finbl dlbss SHA1witiRSA fxtfnds DOMSignbturfMftiod {
        SHA1witiRSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA1witiRSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn SignbturfMftiod.RSA_SHA1;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA1witiRSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.RSA;
        }
    }

    stbtid finbl dlbss SHA256witiRSA fxtfnds DOMSignbturfMftiod {
        SHA256witiRSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA256witiRSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn RSA_SHA256;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA256witiRSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.RSA;
        }
    }

    stbtid finbl dlbss SHA384witiRSA fxtfnds DOMSignbturfMftiod {
        SHA384witiRSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA384witiRSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn RSA_SHA384;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA384witiRSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.RSA;
        }
    }

    stbtid finbl dlbss SHA512witiRSA fxtfnds DOMSignbturfMftiod {
        SHA512witiRSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA512witiRSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn RSA_SHA512;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA512witiRSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.RSA;
        }
    }

    stbtid finbl dlbss SHA1witiDSA fxtfnds DOMSignbturfMftiod {
        SHA1witiDSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA1witiDSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn SignbturfMftiod.DSA_SHA1;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA1witiDSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.DSA;
        }
    }

    stbtid finbl dlbss SHA256witiDSA fxtfnds DOMSignbturfMftiod {
        SHA256witiDSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA256witiDSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn DSA_SHA256;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA256witiDSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.DSA;
        }
    }

    stbtid finbl dlbss SHA1witiECDSA fxtfnds DOMSignbturfMftiod {
        SHA1witiECDSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA1witiECDSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn ECDSA_SHA1;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA1witiECDSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.ECDSA;
        }
    }

    stbtid finbl dlbss SHA256witiECDSA fxtfnds DOMSignbturfMftiod {
        SHA256witiECDSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA256witiECDSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn ECDSA_SHA256;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA256witiECDSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.ECDSA;
        }
    }

    stbtid finbl dlbss SHA384witiECDSA fxtfnds DOMSignbturfMftiod {
        SHA384witiECDSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA384witiECDSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn ECDSA_SHA384;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA384witiECDSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.ECDSA;
        }
    }

    stbtid finbl dlbss SHA512witiECDSA fxtfnds DOMSignbturfMftiod {
        SHA512witiECDSA(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA512witiECDSA(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn ECDSA_SHA512;
        }
        String gftJCAAlgoritim() {
            rfturn "SHA512witiECDSA";
        }
        Typf gftAlgoritimTypf() {
            rfturn Typf.ECDSA;
        }
    }
}
