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
 * $Id: DOMHMACSignbturfMftiod.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.spfd.HMACPbrbmftfrSpfd;
import jbvbx.xml.drypto.dsig.spfd.SignbturfMftiodPbrbmftfrSpfd;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.SignbturfExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvbx.drypto.Mbd;
import jbvbx.drypto.SfdrftKfy;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

import org.jdp.xml.dsig.intfrnbl.MbdOutputStrfbm;

/**
 * DOM-bbsfd implfmfntbtion of HMAC SignbturfMftiod.
 *
 * @butior Sfbn Mullbn
 */
publid bbstrbdt dlbss DOMHMACSignbturfMftiod fxtfnds AbstrbdtDOMSignbturfMftiod {

    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr("org.jdp.xml.dsig.intfrnbl.dom");

    // sff RFC 4051 for tifsf blgoritim dffinitions
    stbtid finbl String HMAC_SHA256 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#imbd-sib256";
    stbtid finbl String HMAC_SHA384 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#imbd-sib384";
    stbtid finbl String HMAC_SHA512 =
        "ittp://www.w3.org/2001/04/xmldsig-morf#imbd-sib512";

    privbtf Mbd imbd;
    privbtf int outputLfngti;
    privbtf boolfbn outputLfngtiSft;
    privbtf SignbturfMftiodPbrbmftfrSpfd pbrbms;

    /**
     * Crfbtfs b <dodf>DOMHMACSignbturfMftiod</dodf> witi tif spfdififd pbrbms
     *
     * @pbrbm pbrbms blgoritim-spfdifid pbrbmftfrs (mby bf <dodf>null</dodf>)
     * @tirows InvblidAlgoritimPbrbmftfrExdfption if pbrbms brf inbppropribtf
     */
    DOMHMACSignbturfMftiod(AlgoritimPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        difdkPbrbms((SignbturfMftiodPbrbmftfrSpfd)pbrbms);
        tiis.pbrbms = (SignbturfMftiodPbrbmftfrSpfd)pbrbms;
    }

    /**
     * Crfbtfs b <dodf>DOMHMACSignbturfMftiod</dodf> from bn flfmfnt.
     *
     * @pbrbm smElfm b SignbturfMftiod flfmfnt
     */
    DOMHMACSignbturfMftiod(Elfmfnt smElfm) tirows MbrsiblExdfption {
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

    void difdkPbrbms(SignbturfMftiodPbrbmftfrSpfd pbrbms)
        tirows InvblidAlgoritimPbrbmftfrExdfption
    {
        if (pbrbms != null) {
            if (!(pbrbms instbndfof HMACPbrbmftfrSpfd)) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("pbrbms must bf of typf HMACPbrbmftfrSpfd");
            }
            outputLfngti = ((HMACPbrbmftfrSpfd)pbrbms).gftOutputLfngti();
            outputLfngtiSft = truf;
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Sftting outputLfngti from HMACPbrbmftfrSpfd to: " + outputLfngti);
            }
        }
    }

    publid finbl AlgoritimPbrbmftfrSpfd gftPbrbmftfrSpfd() {
        rfturn pbrbms;
    }

    SignbturfMftiodPbrbmftfrSpfd unmbrsiblPbrbms(Elfmfnt pbrbmsElfm)
        tirows MbrsiblExdfption
    {
        outputLfngti = Intfgfr.vblufOf(pbrbmsElfm.gftFirstCiild().gftNodfVbluf()).intVbluf();
        outputLfngtiSft = truf;
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "unmbrsibllfd outputLfngti: " + outputLfngti);
        }
        rfturn nfw HMACPbrbmftfrSpfd(outputLfngti);
    }

    void mbrsiblPbrbms(Elfmfnt pbrfnt, String prffix)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);
        Elfmfnt imbdElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "HMACOutputLfngti",
                                                  XMLSignbturf.XMLNS, prffix);
        imbdElfm.bppfndCiild(ownfrDod.drfbtfTfxtNodf
           (String.vblufOf(outputLfngti)));

        pbrfnt.bppfndCiild(imbdElfm);
    }

    boolfbn vfrify(Kfy kfy, SignfdInfo si, bytf[] sig,
                   XMLVblidbtfContfxt dontfxt)
        tirows InvblidKfyExdfption, SignbturfExdfption, XMLSignbturfExdfption
    {
        if (kfy == null || si == null || sig == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (!(kfy instbndfof SfdrftKfy)) {
            tirow nfw InvblidKfyExdfption("kfy must bf SfdrftKfy");
        }
        if (imbd == null) {
            try {
                imbd = Mbd.gftInstbndf(gftJCAAlgoritim());
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                tirow nfw XMLSignbturfExdfption(nsbf);
            }
        }
        if (outputLfngtiSft && outputLfngti < gftDigfstLfngti()) {
            tirow nfw XMLSignbturfExdfption
                ("HMACOutputLfngti must not bf lfss tibn " + gftDigfstLfngti());
        }
        imbd.init((SfdrftKfy)kfy);
        ((DOMSignfdInfo)si).dbnonidblizf(dontfxt, nfw MbdOutputStrfbm(imbd));
        bytf[] rfsult = imbd.doFinbl();

        rfturn MfssbgfDigfst.isEqubl(sig, rfsult);
    }

    bytf[] sign(Kfy kfy, SignfdInfo si, XMLSignContfxt dontfxt)
        tirows InvblidKfyExdfption, XMLSignbturfExdfption
    {
        if (kfy == null || si == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (!(kfy instbndfof SfdrftKfy)) {
            tirow nfw InvblidKfyExdfption("kfy must bf SfdrftKfy");
        }
        if (imbd == null) {
            try {
                imbd = Mbd.gftInstbndf(gftJCAAlgoritim());
            } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                tirow nfw XMLSignbturfExdfption(nsbf);
            }
        }
        if (outputLfngtiSft && outputLfngti < gftDigfstLfngti()) {
            tirow nfw XMLSignbturfExdfption
                ("HMACOutputLfngti must not bf lfss tibn " + gftDigfstLfngti());
        }
        imbd.init((SfdrftKfy)kfy);
        ((DOMSignfdInfo)si).dbnonidblizf(dontfxt, nfw MbdOutputStrfbm(imbd));
        rfturn imbd.doFinbl();
    }

    boolfbn pbrbmsEqubl(AlgoritimPbrbmftfrSpfd spfd) {
        if (gftPbrbmftfrSpfd() == spfd) {
            rfturn truf;
        }
        if (!(spfd instbndfof HMACPbrbmftfrSpfd)) {
            rfturn fblsf;
        }
        HMACPbrbmftfrSpfd ospfd = (HMACPbrbmftfrSpfd)spfd;

        rfturn (outputLfngti == ospfd.gftOutputLfngti());
    }

    Typf gftAlgoritimTypf() {
        rfturn Typf.HMAC;
    }

    /**
     * Rfturns tif output lfngti of tif ibsi/digfst.
     */
    bbstrbdt int gftDigfstLfngti();

    stbtid finbl dlbss SHA1 fxtfnds DOMHMACSignbturfMftiod {
        SHA1(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA1(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn SignbturfMftiod.HMAC_SHA1;
        }
        String gftJCAAlgoritim() {
            rfturn "HmbdSHA1";
        }
        int gftDigfstLfngti() {
            rfturn 160;
        }
    }

    stbtid finbl dlbss SHA256 fxtfnds DOMHMACSignbturfMftiod {
        SHA256(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA256(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn HMAC_SHA256;
        }
        String gftJCAAlgoritim() {
            rfturn "HmbdSHA256";
        }
        int gftDigfstLfngti() {
            rfturn 256;
        }
    }

    stbtid finbl dlbss SHA384 fxtfnds DOMHMACSignbturfMftiod {
        SHA384(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA384(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn HMAC_SHA384;
        }
        String gftJCAAlgoritim() {
            rfturn "HmbdSHA384";
        }
        int gftDigfstLfngti() {
            rfturn 384;
        }
    }

    stbtid finbl dlbss SHA512 fxtfnds DOMHMACSignbturfMftiod {
        SHA512(AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidAlgoritimPbrbmftfrExdfption {
            supfr(pbrbms);
        }
        SHA512(Elfmfnt dmElfm) tirows MbrsiblExdfption {
            supfr(dmElfm);
        }
        publid String gftAlgoritim() {
            rfturn HMAC_SHA512;
        }
        String gftJCAAlgoritim() {
            rfturn "HmbdSHA512";
        }
        int gftDigfstLfngti() {
            rfturn 512;
        }
    }
}
