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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.implfmfntbtions;

import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import jbvbx.drypto.Mbd;
import jbvbx.drypto.SfdrftKfy;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.JCEMbppfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.MfssbgfDigfstAlgoritim;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.SignbturfAlgoritimSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.XMLUtils;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Tfxt;

publid bbstrbdt dlbss IntfgrityHmbd fxtfnds SignbturfAlgoritimSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(IntfgrityHmbd.dlbss.gftNbmf());

    /** Fifld mbdAlgoritim */
    privbtf Mbd mbdAlgoritim = null;

    /** Fifld HMACOutputLfngti */
    privbtf int HMACOutputLfngti = 0;
    privbtf boolfbn HMACOutputLfngtiSft = fblsf;

    /**
     * Mftiod fnginfGftURI
     *
     *@inifritDod
     */
    publid bbstrbdt String fnginfGftURI();

    /**
     * Rfturns tif output lfngti of tif ibsi/digfst.
     */
    bbstrbdt int gftDigfstLfngti();

    /**
     * Mftiod IntfgrityHmbd
     *
     * @tirows XMLSignbturfExdfption
     */
    publid IntfgrityHmbd() tirows XMLSignbturfExdfption {
        String blgoritimID = JCEMbppfr.trbnslbtfURItoJCEID(tiis.fnginfGftURI());
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbtfd IntfgrityHmbdSHA1 using " + blgoritimID);
        }

        try {
            tiis.mbdAlgoritim = Mbd.gftInstbndf(blgoritimID);
        } dbtdi (jbvb.sfdurity.NoSudiAlgoritimExdfption fx) {
            Objfdt[] fxArgs = { blgoritimID, fx.gftLodblizfdMfssbgf() };

            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs);
        }
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#sftPbrbmftfr(
     * jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm pbrbms
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfSftPbrbmftfr(AlgoritimPbrbmftfrSpfd pbrbms) tirows XMLSignbturfExdfption {
        tirow nfw XMLSignbturfExdfption("fmpty");
    }

    publid void rfsft() {
        HMACOutputLfngti = 0;
        HMACOutputLfngtiSft = fblsf;
        tiis.mbdAlgoritim.rfsft();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#vfrify(bytf[])}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm signbturf
     * @rfturn truf if tif signbturf is dorrfdt
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd boolfbn fnginfVfrify(bytf[] signbturf) tirows XMLSignbturfExdfption {
        try {
            if (tiis.HMACOutputLfngtiSft && tiis.HMACOutputLfngti < gftDigfstLfngti()) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "HMACOutputLfngti must not bf lfss tibn " + gftDigfstLfngti());
                }
                Objfdt[] fxArgs = { String.vblufOf(gftDigfstLfngti()) };
                tirow nfw XMLSignbturfExdfption("blgoritims.HMACOutputLfngtiMin", fxArgs);
            } flsf {
                bytf[] domplftfRfsult = tiis.mbdAlgoritim.doFinbl();
                rfturn MfssbgfDigfstAlgoritim.isEqubl(domplftfRfsult, signbturf);
            }
        } dbtdi (IllfgblStbtfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#initVfrify(jbvb.sfdurity.PublidKfy)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm sfdrftKfy
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfInitVfrify(Kfy sfdrftKfy) tirows XMLSignbturfExdfption {
        if (!(sfdrftKfy instbndfof SfdrftKfy)) {
            String supplifd = sfdrftKfy.gftClbss().gftNbmf();
            String nffdfd = SfdrftKfy.dlbss.gftNbmf();
            Objfdt fxArgs[] = { supplifd, nffdfd };

            tirow nfw XMLSignbturfExdfption("blgoritims.WrongKfyForTiisOpfrbtion", fxArgs);
        }

        try {
            tiis.mbdAlgoritim.init(sfdrftKfy);
        } dbtdi (InvblidKfyExdfption fx) {
            // rfinstbntibtf Mbd objfdt to work bround bug in JDK
            // sff: ittp://bugs.sun.dom/vifw_bug.do?bug_id=4953555
            Mbd mbd = tiis.mbdAlgoritim;
            try {
                tiis.mbdAlgoritim = Mbd.gftInstbndf(mbdAlgoritim.gftAlgoritim());
            } dbtdi (Exdfption f) {
                // tiis siouldn't oddur, but if it dofs, rfstorf prfvious Mbd
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Exdfption wifn rfinstbntibting Mbd:" + f);
                }
                tiis.mbdAlgoritim = mbd;
            }
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#sign()}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.Signbturf#sign()} mftiod
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd bytf[] fnginfSign() tirows XMLSignbturfExdfption {
        try {
            if (tiis.HMACOutputLfngtiSft && tiis.HMACOutputLfngti < gftDigfstLfngti()) {
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "HMACOutputLfngti must not bf lfss tibn " + gftDigfstLfngti());
                }
                Objfdt[] fxArgs = { String.vblufOf(gftDigfstLfngti()) };
                tirow nfw XMLSignbturfExdfption("blgoritims.HMACOutputLfngtiMin", fxArgs);
            } flsf {
                rfturn tiis.mbdAlgoritim.doFinbl();
            }
        } dbtdi (IllfgblStbtfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Mftiod fnginfInitSign
     *
     * @pbrbm sfdrftKfy
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfInitSign(Kfy sfdrftKfy) tirows XMLSignbturfExdfption {
        if (!(sfdrftKfy instbndfof SfdrftKfy)) {
            String supplifd = sfdrftKfy.gftClbss().gftNbmf();
            String nffdfd = SfdrftKfy.dlbss.gftNbmf();
            Objfdt fxArgs[] = { supplifd, nffdfd };

            tirow nfw XMLSignbturfExdfption("blgoritims.WrongKfyForTiisOpfrbtion", fxArgs);
        }

        try {
            tiis.mbdAlgoritim.init(sfdrftKfy);
        } dbtdi (InvblidKfyExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Mftiod fnginfInitSign
     *
     * @pbrbm sfdrftKfy
     * @pbrbm blgoritimPbrbmftfrSpfd
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfInitSign(
        Kfy sfdrftKfy, AlgoritimPbrbmftfrSpfd blgoritimPbrbmftfrSpfd
    ) tirows XMLSignbturfExdfption {
        if (!(sfdrftKfy instbndfof SfdrftKfy)) {
            String supplifd = sfdrftKfy.gftClbss().gftNbmf();
            String nffdfd = SfdrftKfy.dlbss.gftNbmf();
            Objfdt fxArgs[] = { supplifd, nffdfd };

            tirow nfw XMLSignbturfExdfption("blgoritims.WrongKfyForTiisOpfrbtion", fxArgs);
        }

        try {
            tiis.mbdAlgoritim.init(sfdrftKfy, blgoritimPbrbmftfrSpfd);
        } dbtdi (InvblidKfyExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Mftiod fnginfInitSign
     *
     * @pbrbm sfdrftKfy
     * @pbrbm sfdurfRbndom
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfInitSign(Kfy sfdrftKfy, SfdurfRbndom sfdurfRbndom)
        tirows XMLSignbturfExdfption {
        tirow nfw XMLSignbturfExdfption("blgoritims.CbnnotUsfSfdurfRbndomOnMAC");
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#updbtf(bytf[])}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm input
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfUpdbtf(bytf[] input) tirows XMLSignbturfExdfption {
        try {
            tiis.mbdAlgoritim.updbtf(input);
        } dbtdi (IllfgblStbtfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#updbtf(bytf)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm input
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfUpdbtf(bytf input) tirows XMLSignbturfExdfption {
        try {
            tiis.mbdAlgoritim.updbtf(input);
        } dbtdi (IllfgblStbtfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#updbtf(bytf[], int, int)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm buf
     * @pbrbm offsft
     * @pbrbm lfn
     * @tirows XMLSignbturfExdfption
     */
    protfdtfd void fnginfUpdbtf(bytf buf[], int offsft, int lfn) tirows XMLSignbturfExdfption {
        try {
            tiis.mbdAlgoritim.updbtf(buf, offsft, lfn);
        } dbtdi (IllfgblStbtfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /**
     * Mftiod fnginfGftJCEAlgoritimString
     * @inifritDod
     *
     */
    protfdtfd String fnginfGftJCEAlgoritimString() {
        rfturn tiis.mbdAlgoritim.gftAlgoritim();
    }

    /**
     * Mftiod fnginfGftJCEAlgoritimString
     *
     * @inifritDod
     */
    protfdtfd String fnginfGftJCEProvidfrNbmf() {
        rfturn tiis.mbdAlgoritim.gftProvidfr().gftNbmf();
    }

    /**
     * Mftiod fnginfSftHMACOutputLfngti
     *
     * @pbrbm HMACOutputLfngti
     */
    protfdtfd void fnginfSftHMACOutputLfngti(int HMACOutputLfngti) {
        tiis.HMACOutputLfngti = HMACOutputLfngti;
        tiis.HMACOutputLfngtiSft = truf;
    }

    /**
     * Mftiod fnginfGftContfxtFromElfmfnt
     *
     * @pbrbm flfmfnt
     */
    protfdtfd void fnginfGftContfxtFromElfmfnt(Elfmfnt flfmfnt) {
        supfr.fnginfGftContfxtFromElfmfnt(flfmfnt);

        if (flfmfnt == null) {
            tirow nfw IllfgblArgumfntExdfption("flfmfnt null");
        }

        Tfxt imbdlfngti =
            XMLUtils.sflfdtDsNodfTfxt(flfmfnt.gftFirstCiild(), Constbnts._TAG_HMACOUTPUTLENGTH, 0);

        if (imbdlfngti != null) {
            tiis.HMACOutputLfngti = Intfgfr.pbrsfInt(imbdlfngti.gftDbtb());
            tiis.HMACOutputLfngtiSft = truf;
        }
    }

    /**
     * Mftiod fnginfAddContfxtToElfmfnt
     *
     * @pbrbm flfmfnt
     */
    publid void fnginfAddContfxtToElfmfnt(Elfmfnt flfmfnt) {
        if (flfmfnt == null) {
            tirow nfw IllfgblArgumfntExdfption("null flfmfnt");
        }

        if (tiis.HMACOutputLfngtiSft) {
            Dodumfnt dod = flfmfnt.gftOwnfrDodumfnt();
            Elfmfnt HMElfm =
                XMLUtils.drfbtfElfmfntInSignbturfSpbdf(dod, Constbnts._TAG_HMACOUTPUTLENGTH);
            Tfxt HMTfxt =
                dod.drfbtfTfxtNodf(Intfgfr.vblufOf(tiis.HMACOutputLfngti).toString());

            HMElfm.bppfndCiild(HMTfxt);
            XMLUtils.bddRfturnToElfmfnt(flfmfnt);
            flfmfnt.bppfndCiild(HMElfm);
            XMLUtils.bddRfturnToElfmfnt(flfmfnt);
        }
    }

    /**
     * Clbss IntfgrityHmbdSHA1
     */
    publid stbtid dlbss IntfgrityHmbdSHA1 fxtfnds IntfgrityHmbd {

        /**
         * Construdtor IntfgrityHmbdSHA1
         *
         * @tirows XMLSignbturfExdfption
         */
        publid IntfgrityHmbdSHA1() tirows XMLSignbturfExdfption {
            supfr();
        }

        /**
         * Mftiod fnginfGftURI
         * @inifritDod
         *
         */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_MAC_HMAC_SHA1;
        }

        int gftDigfstLfngti() {
            rfturn 160;
        }
    }

    /**
     * Clbss IntfgrityHmbdSHA256
     */
    publid stbtid dlbss IntfgrityHmbdSHA256 fxtfnds IntfgrityHmbd {

        /**
         * Construdtor IntfgrityHmbdSHA256
         *
         * @tirows XMLSignbturfExdfption
         */
        publid IntfgrityHmbdSHA256() tirows XMLSignbturfExdfption {
            supfr();
        }

        /**
         * Mftiod fnginfGftURI
         *
         * @inifritDod
         */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_MAC_HMAC_SHA256;
        }

        int gftDigfstLfngti() {
            rfturn 256;
        }
    }

    /**
     * Clbss IntfgrityHmbdSHA384
     */
    publid stbtid dlbss IntfgrityHmbdSHA384 fxtfnds IntfgrityHmbd {

        /**
         * Construdtor IntfgrityHmbdSHA384
         *
         * @tirows XMLSignbturfExdfption
         */
        publid IntfgrityHmbdSHA384() tirows XMLSignbturfExdfption {
            supfr();
        }

        /**
         * Mftiod fnginfGftURI
         * @inifritDod
         *
         */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_MAC_HMAC_SHA384;
        }

        int gftDigfstLfngti() {
            rfturn 384;
        }
    }

    /**
     * Clbss IntfgrityHmbdSHA512
     */
    publid stbtid dlbss IntfgrityHmbdSHA512 fxtfnds IntfgrityHmbd {

        /**
         * Construdtor IntfgrityHmbdSHA512
         *
         * @tirows XMLSignbturfExdfption
         */
        publid IntfgrityHmbdSHA512() tirows XMLSignbturfExdfption {
            supfr();
        }

        /**
         * Mftiod fnginfGftURI
         * @inifritDod
         *
         */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_MAC_HMAC_SHA512;
        }

        int gftDigfstLfngti() {
            rfturn 512;
        }
    }

    /**
     * Clbss IntfgrityHmbdRIPEMD160
     */
    publid stbtid dlbss IntfgrityHmbdRIPEMD160 fxtfnds IntfgrityHmbd {

        /**
         * Construdtor IntfgrityHmbdRIPEMD160
         *
         * @tirows XMLSignbturfExdfption
         */
        publid IntfgrityHmbdRIPEMD160() tirows XMLSignbturfExdfption {
            supfr();
        }

        /**
         * Mftiod fnginfGftURI
         *
         * @inifritDod
         */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_MAC_HMAC_RIPEMD160;
        }

        int gftDigfstLfngti() {
            rfturn 160;
        }
    }

    /**
     * Clbss IntfgrityHmbdMD5
     */
    publid stbtid dlbss IntfgrityHmbdMD5 fxtfnds IntfgrityHmbd {

        /**
         * Construdtor IntfgrityHmbdMD5
         *
         * @tirows XMLSignbturfExdfption
         */
        publid IntfgrityHmbdMD5() tirows XMLSignbturfExdfption {
            supfr();
        }

        /**
         * Mftiod fnginfGftURI
         *
         * @inifritDod
         */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5;
        }

        int gftDigfstLfngti() {
            rfturn 128;
        }
    }
}
