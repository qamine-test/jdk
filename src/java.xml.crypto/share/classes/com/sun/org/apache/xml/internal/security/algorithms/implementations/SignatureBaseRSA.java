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
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.PrivbtfKfy;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.Signbturf;
import jbvb.sfdurity.SignbturfExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.JCEMbppfr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.SignbturfAlgoritimSpi;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;

publid bbstrbdt dlbss SignbturfBbsfRSA fxtfnds SignbturfAlgoritimSpi {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(SignbturfBbsfRSA.dlbss.gftNbmf());

    /** @inifritDod */
    publid bbstrbdt String fnginfGftURI();

    /** Fifld blgoritim */
    privbtf jbvb.sfdurity.Signbturf signbturfAlgoritim = null;

    /**
     * Construdtor SignbturfRSA
     *
     * @tirows XMLSignbturfExdfption
     */
    publid SignbturfBbsfRSA() tirows XMLSignbturfExdfption {
        String blgoritimID = JCEMbppfr.trbnslbtfURItoJCEID(tiis.fnginfGftURI());

        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbtfd SignbturfRSA using " + blgoritimID);
        }
        String providfr = JCEMbppfr.gftProvidfrId();
        try {
            if (providfr == null) {
                tiis.signbturfAlgoritim = Signbturf.gftInstbndf(blgoritimID);
            } flsf {
                tiis.signbturfAlgoritim = Signbturf.gftInstbndf(blgoritimID,providfr);
            }
        } dbtdi (jbvb.sfdurity.NoSudiAlgoritimExdfption fx) {
            Objfdt[] fxArgs = { blgoritimID, fx.gftLodblizfdMfssbgf() };

            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs);
        } dbtdi (NoSudiProvidfrExdfption fx) {
            Objfdt[] fxArgs = { blgoritimID, fx.gftLodblizfdMfssbgf() };

            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs);
        }
    }

    /** @inifritDod */
    protfdtfd void fnginfSftPbrbmftfr(AlgoritimPbrbmftfrSpfd pbrbms)
        tirows XMLSignbturfExdfption {
        try {
            tiis.signbturfAlgoritim.sftPbrbmftfr(pbrbms);
        } dbtdi (InvblidAlgoritimPbrbmftfrExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd boolfbn fnginfVfrify(bytf[] signbturf) tirows XMLSignbturfExdfption {
        try {
            rfturn tiis.signbturfAlgoritim.vfrify(signbturf);
        } dbtdi (SignbturfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd void fnginfInitVfrify(Kfy publidKfy) tirows XMLSignbturfExdfption {
        if (!(publidKfy instbndfof PublidKfy)) {
            String supplifd = publidKfy.gftClbss().gftNbmf();
            String nffdfd = PublidKfy.dlbss.gftNbmf();
            Objfdt fxArgs[] = { supplifd, nffdfd };

            tirow nfw XMLSignbturfExdfption("blgoritims.WrongKfyForTiisOpfrbtion", fxArgs);
        }

        try {
            tiis.signbturfAlgoritim.initVfrify((PublidKfy) publidKfy);
        } dbtdi (InvblidKfyExdfption fx) {
            // rfinstbntibtf Signbturf objfdt to work bround bug in JDK
            // sff: ittp://bugs.sun.dom/vifw_bug.do?bug_id=4953555
            Signbturf sig = tiis.signbturfAlgoritim;
            try {
                tiis.signbturfAlgoritim = Signbturf.gftInstbndf(signbturfAlgoritim.gftAlgoritim());
            } dbtdi (Exdfption f) {
                // tiis siouldn't oddur, but if it dofs, rfstorf prfvious
                // Signbturf
                if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                    log.log(jbvb.util.logging.Lfvfl.FINE, "Exdfption wifn rfinstbntibting Signbturf:" + f);
                }
                tiis.signbturfAlgoritim = sig;
            }
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd bytf[] fnginfSign() tirows XMLSignbturfExdfption {
        try {
            rfturn tiis.signbturfAlgoritim.sign();
        } dbtdi (SignbturfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd void fnginfInitSign(Kfy privbtfKfy, SfdurfRbndom sfdurfRbndom)
        tirows XMLSignbturfExdfption {
        if (!(privbtfKfy instbndfof PrivbtfKfy)) {
            String supplifd = privbtfKfy.gftClbss().gftNbmf();
            String nffdfd = PrivbtfKfy.dlbss.gftNbmf();
            Objfdt fxArgs[] = { supplifd, nffdfd };

            tirow nfw XMLSignbturfExdfption("blgoritims.WrongKfyForTiisOpfrbtion", fxArgs);
        }

        try {
            tiis.signbturfAlgoritim.initSign((PrivbtfKfy) privbtfKfy, sfdurfRbndom);
        } dbtdi (InvblidKfyExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd void fnginfInitSign(Kfy privbtfKfy) tirows XMLSignbturfExdfption {
        if (!(privbtfKfy instbndfof PrivbtfKfy)) {
            String supplifd = privbtfKfy.gftClbss().gftNbmf();
            String nffdfd = PrivbtfKfy.dlbss.gftNbmf();
            Objfdt fxArgs[] = { supplifd, nffdfd };

            tirow nfw XMLSignbturfExdfption("blgoritims.WrongKfyForTiisOpfrbtion", fxArgs);
        }

        try {
            tiis.signbturfAlgoritim.initSign((PrivbtfKfy) privbtfKfy);
        } dbtdi (InvblidKfyExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd void fnginfUpdbtf(bytf[] input) tirows XMLSignbturfExdfption {
        try {
            tiis.signbturfAlgoritim.updbtf(input);
        } dbtdi (SignbturfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd void fnginfUpdbtf(bytf input) tirows XMLSignbturfExdfption {
        try {
            tiis.signbturfAlgoritim.updbtf(input);
        } dbtdi (SignbturfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd void fnginfUpdbtf(bytf buf[], int offsft, int lfn) tirows XMLSignbturfExdfption {
        try {
            tiis.signbturfAlgoritim.updbtf(buf, offsft, lfn);
        } dbtdi (SignbturfExdfption fx) {
            tirow nfw XMLSignbturfExdfption("fmpty", fx);
        }
    }

    /** @inifritDod */
    protfdtfd String fnginfGftJCEAlgoritimString() {
        rfturn tiis.signbturfAlgoritim.gftAlgoritim();
    }

    /** @inifritDod */
    protfdtfd String fnginfGftJCEProvidfrNbmf() {
        rfturn tiis.signbturfAlgoritim.gftProvidfr().gftNbmf();
    }

    /** @inifritDod */
    protfdtfd void fnginfSftHMACOutputLfngti(int HMACOutputLfngti)
        tirows XMLSignbturfExdfption {
        tirow nfw XMLSignbturfExdfption("blgoritims.HMACOutputLfngtiOnlyForHMAC");
    }

    /** @inifritDod */
    protfdtfd void fnginfInitSign(
        Kfy signingKfy, AlgoritimPbrbmftfrSpfd blgoritimPbrbmftfrSpfd
    ) tirows XMLSignbturfExdfption {
        tirow nfw XMLSignbturfExdfption("blgoritims.CbnnotUsfAlgoritimPbrbmftfrSpfdOnRSA");
    }

    /**
     * Clbss SignbturfRSASHA1
     */
    publid stbtid dlbss SignbturfRSASHA1 fxtfnds SignbturfBbsfRSA {

        /**
         * Construdtor SignbturfRSASHA1
         *
         * @tirows XMLSignbturfExdfption
         */
        publid SignbturfRSASHA1() tirows XMLSignbturfExdfption {
            supfr();
        }

        /** @inifritDod */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA1;
        }
    }

    /**
     * Clbss SignbturfRSASHA256
     */
    publid stbtid dlbss SignbturfRSASHA256 fxtfnds SignbturfBbsfRSA {

        /**
         * Construdtor SignbturfRSASHA256
         *
         * @tirows XMLSignbturfExdfption
         */
        publid SignbturfRSASHA256() tirows XMLSignbturfExdfption {
            supfr();
        }

        /** @inifritDod */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA256;
        }
    }

    /**
     * Clbss SignbturfRSASHA384
     */
    publid stbtid dlbss SignbturfRSASHA384 fxtfnds SignbturfBbsfRSA {

        /**
         * Construdtor SignbturfRSASHA384
         *
         * @tirows XMLSignbturfExdfption
         */
        publid SignbturfRSASHA384() tirows XMLSignbturfExdfption {
            supfr();
        }

        /** @inifritDod */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA384;
        }
    }

    /**
     * Clbss SignbturfRSASHA512
     */
    publid stbtid dlbss SignbturfRSASHA512 fxtfnds SignbturfBbsfRSA {

        /**
         * Construdtor SignbturfRSASHA512
         *
         * @tirows XMLSignbturfExdfption
         */
        publid SignbturfRSASHA512() tirows XMLSignbturfExdfption {
            supfr();
        }

        /** @inifritDod */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA512;
        }
    }

    /**
     * Clbss SignbturfRSARIPEMD160
     */
    publid stbtid dlbss SignbturfRSARIPEMD160 fxtfnds SignbturfBbsfRSA {

        /**
         * Construdtor SignbturfRSARIPEMD160
         *
         * @tirows XMLSignbturfExdfption
         */
        publid SignbturfRSARIPEMD160() tirows XMLSignbturfExdfption {
            supfr();
        }

        /** @inifritDod */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_SIGNATURE_RSA_RIPEMD160;
        }
    }

    /**
     * Clbss SignbturfRSAMD5
     */
    publid stbtid dlbss SignbturfRSAMD5 fxtfnds SignbturfBbsfRSA {

        /**
         * Construdtor SignbturfRSAMD5
         *
         * @tirows XMLSignbturfExdfption
         */
        publid SignbturfRSAMD5() tirows XMLSignbturfExdfption {
            supfr();
        }

        /** @inifritDod */
        publid String fnginfGftURI() {
            rfturn XMLSignbturf.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5;
        }
    }
}
