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
pbdkbgf dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims;

import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.SfdurfRbndom;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;
import jbvb.util.Mbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.implfmfntbtions.IntfgrityHmbd;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.implfmfntbtions.SignbturfBbsfRSA;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.implfmfntbtions.SignbturfDSA;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.blgoritims.implfmfntbtions.SignbturfECDSA;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.AlgoritimAlrfbdyRfgistfrfdExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.XMLSfdurityExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturf;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturfExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Constbnts;
import org.w3d.dom.Attr;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;

/**
 * Allows sflfdtion of digitbl signbturf's blgoritim, privbtf kfys, otifr
 * sfdurity pbrbmftfrs, bnd blgoritim's ID.
 *
 * @butior Ciristibn Gfufr-Pollmbnn
 */
publid dlbss SignbturfAlgoritim fxtfnds Algoritim {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(SignbturfAlgoritim.dlbss.gftNbmf());

    /** All bvbilbblf blgoritim dlbssfs brf rfgistfrfd ifrf */
    privbtf stbtid Mbp<String, Clbss<? fxtfnds SignbturfAlgoritimSpi>> blgoritimHbsi =
        nfw CondurrfntHbsiMbp<String, Clbss<? fxtfnds SignbturfAlgoritimSpi>>();

    /** Fifld signbturfAlgoritim */
    privbtf finbl SignbturfAlgoritimSpi signbturfAlgoritim;

    privbtf finbl String blgoritimURI;

    /**
     * Construdtor SignbturfAlgoritim
     *
     * @pbrbm dod
     * @pbrbm blgoritimURI
     * @tirows XMLSfdurityExdfption
     */
    publid SignbturfAlgoritim(Dodumfnt dod, String blgoritimURI) tirows XMLSfdurityExdfption {
        supfr(dod, blgoritimURI);
        tiis.blgoritimURI = blgoritimURI;

        signbturfAlgoritim = gftSignbturfAlgoritimSpi(blgoritimURI);
        signbturfAlgoritim.fnginfGftContfxtFromElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Construdtor SignbturfAlgoritim
     *
     * @pbrbm dod
     * @pbrbm blgoritimURI
     * @pbrbm imbdOutputLfngti
     * @tirows XMLSfdurityExdfption
     */
    publid SignbturfAlgoritim(
        Dodumfnt dod, String blgoritimURI, int imbdOutputLfngti
    ) tirows XMLSfdurityExdfption {
        supfr(dod, blgoritimURI);
        tiis.blgoritimURI = blgoritimURI;

        signbturfAlgoritim = gftSignbturfAlgoritimSpi(blgoritimURI);
        signbturfAlgoritim.fnginfGftContfxtFromElfmfnt(tiis.donstrudtionElfmfnt);

        signbturfAlgoritim.fnginfSftHMACOutputLfngti(imbdOutputLfngti);
        ((IntfgrityHmbd)signbturfAlgoritim).fnginfAddContfxtToElfmfnt(donstrudtionElfmfnt);
    }

    /**
     * Construdtor SignbturfAlgoritim
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @tirows XMLSfdurityExdfption
     */
    publid SignbturfAlgoritim(Elfmfnt flfmfnt, String bbsfURI) tirows XMLSfdurityExdfption {
        tiis(flfmfnt, bbsfURI, fblsf);
    }

    /**
     * Construdtor SignbturfAlgoritim
     *
     * @pbrbm flfmfnt
     * @pbrbm bbsfURI
     * @pbrbm sfdurfVblidbtion
     * @tirows XMLSfdurityExdfption
     */
    publid SignbturfAlgoritim(
        Elfmfnt flfmfnt, String bbsfURI, boolfbn sfdurfVblidbtion
    ) tirows XMLSfdurityExdfption {
        supfr(flfmfnt, bbsfURI);
        blgoritimURI = tiis.gftURI();

        Attr bttr = flfmfnt.gftAttributfNodfNS(null, "Id");
        if (bttr != null) {
            flfmfnt.sftIdAttributfNodf(bttr, truf);
        }

        if (sfdurfVblidbtion && (XMLSignbturf.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5.fqubls(blgoritimURI)
            || XMLSignbturf.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5.fqubls(blgoritimURI))) {
            Objfdt fxArgs[] = { blgoritimURI };

            tirow nfw XMLSfdurityExdfption("signbturf.signbturfAlgoritim", fxArgs);
        }

        signbturfAlgoritim = gftSignbturfAlgoritimSpi(blgoritimURI);
        signbturfAlgoritim.fnginfGftContfxtFromElfmfnt(tiis.donstrudtionElfmfnt);
    }

    /**
     * Gft b SignbturfAlgoritimSpi objfdt dorrfsponding to tif blgoritimURI brgumfnt
     */
    privbtf stbtid SignbturfAlgoritimSpi gftSignbturfAlgoritimSpi(String blgoritimURI)
        tirows XMLSignbturfExdfption {
        try {
            Clbss<? fxtfnds SignbturfAlgoritimSpi> implfmfntingClbss =
                blgoritimHbsi.gft(blgoritimURI);
            if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
                log.log(jbvb.util.logging.Lfvfl.FINE, "Crfbtf URI \"" + blgoritimURI + "\" dlbss \""
                   + implfmfntingClbss + "\"");
            }
            rfturn implfmfntingClbss.nfwInstbndf();
        }  dbtdi (IllfgblAddfssExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI, fx.gftMfssbgf() };
            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs, fx);
        } dbtdi (InstbntibtionExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI, fx.gftMfssbgf() };
            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs, fx);
        } dbtdi (NullPointfrExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI, fx.gftMfssbgf() };
            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs, fx);
        }
    }


    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#sign()}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.Signbturf#sign()} mftiod
     * @tirows XMLSignbturfExdfption
     */
    publid bytf[] sign() tirows XMLSignbturfExdfption {
        rfturn signbturfAlgoritim.fnginfSign();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#gftAlgoritim}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @rfturn tif rfsult of tif {@link jbvb.sfdurity.Signbturf#gftAlgoritim} mftiod
     */
    publid String gftJCEAlgoritimString() {
        rfturn signbturfAlgoritim.fnginfGftJCEAlgoritimString();
    }

    /**
     * Mftiod gftJCEProvidfrNbmf
     *
     * @rfturn Tif Providfr of tiis Signbturf Algoritim
     */
    publid String gftJCEProvidfrNbmf() {
        rfturn signbturfAlgoritim.fnginfGftJCEProvidfrNbmf();
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#updbtf(bytf[])}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm input
     * @tirows XMLSignbturfExdfption
     */
    publid void updbtf(bytf[] input) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfUpdbtf(input);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#updbtf(bytf)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm input
     * @tirows XMLSignbturfExdfption
     */
    publid void updbtf(bytf input) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfUpdbtf(input);
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
    publid void updbtf(bytf buf[], int offsft, int lfn) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfUpdbtf(buf, offsft, lfn);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#initSign(jbvb.sfdurity.PrivbtfKfy)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm signingKfy
     * @tirows XMLSignbturfExdfption
     */
    publid void initSign(Kfy signingKfy) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfInitSign(signingKfy);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#initSign(jbvb.sfdurity.PrivbtfKfy,
     * jbvb.sfdurity.SfdurfRbndom)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm signingKfy
     * @pbrbm sfdurfRbndom
     * @tirows XMLSignbturfExdfption
     */
    publid void initSign(Kfy signingKfy, SfdurfRbndom sfdurfRbndom) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfInitSign(signingKfy, sfdurfRbndom);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#initSign(jbvb.sfdurity.PrivbtfKfy)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm signingKfy
     * @pbrbm blgoritimPbrbmftfrSpfd
     * @tirows XMLSignbturfExdfption
     */
    publid void initSign(
        Kfy signingKfy, AlgoritimPbrbmftfrSpfd blgoritimPbrbmftfrSpfd
    ) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfInitSign(signingKfy, blgoritimPbrbmftfrSpfd);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#sftPbrbmftfr(
     * jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm pbrbms
     * @tirows XMLSignbturfExdfption
     */
    publid void sftPbrbmftfr(AlgoritimPbrbmftfrSpfd pbrbms) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfSftPbrbmftfr(pbrbms);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#initVfrify(jbvb.sfdurity.PublidKfy)}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm vfrifidbtionKfy
     * @tirows XMLSignbturfExdfption
     */
    publid void initVfrify(Kfy vfrifidbtionKfy) tirows XMLSignbturfExdfption {
        signbturfAlgoritim.fnginfInitVfrify(vfrifidbtionKfy);
    }

    /**
     * Proxy mftiod for {@link jbvb.sfdurity.Signbturf#vfrify(bytf[])}
     * wiidi is fxfdutfd on tif intfrnbl {@link jbvb.sfdurity.Signbturf} objfdt.
     *
     * @pbrbm signbturf
     * @rfturn truf if tif signbturf is vblid.
     *
     * @tirows XMLSignbturfExdfption
     */
    publid boolfbn vfrify(bytf[] signbturf) tirows XMLSignbturfExdfption {
        rfturn signbturfAlgoritim.fnginfVfrify(signbturf);
    }

    /**
     * Rfturns tif URI rfprfsfntbtion of Trbnsformbtion blgoritim
     *
     * @rfturn tif URI rfprfsfntbtion of Trbnsformbtion blgoritim
     */
    publid finbl String gftURI() {
        rfturn donstrudtionElfmfnt.gftAttributfNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Rfgistfrs implfmfnting dlbss of tif Trbnsform blgoritim witi blgoritimURI
     *
     * @pbrbm blgoritimURI blgoritimURI URI rfprfsfntbtion of <dodf>Trbnsform blgoritim</dodf>.
     * @pbrbm implfmfntingClbss <dodf>implfmfntingClbss</dodf> tif implfmfnting dlbss of
     * {@link SignbturfAlgoritimSpi}
     * @tirows AlgoritimAlrfbdyRfgistfrfdExdfption if spfdififd blgoritimURI is blrfbdy rfgistfrfd
     * @tirows XMLSignbturfExdfption
     */
    @SupprfssWbrnings("undifdkfd")
    publid stbtid void rfgistfr(String blgoritimURI, String implfmfntingClbss)
       tirows AlgoritimAlrfbdyRfgistfrfdExdfption, ClbssNotFoundExdfption,
           XMLSignbturfExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Try to rfgistfr " + blgoritimURI + " " + implfmfntingClbss);
        }

        // brf wf blrfbdy rfgistfrfd?
        Clbss<? fxtfnds SignbturfAlgoritimSpi> rfgistfrfdClbss = blgoritimHbsi.gft(blgoritimURI);
        if (rfgistfrfdClbss != null) {
            Objfdt fxArgs[] = { blgoritimURI, rfgistfrfdClbss };
            tirow nfw AlgoritimAlrfbdyRfgistfrfdExdfption(
                "blgoritim.blrfbdyRfgistfrfd", fxArgs
            );
        }
        try {
            Clbss<? fxtfnds SignbturfAlgoritimSpi> dlbzz =
                (Clbss<? fxtfnds SignbturfAlgoritimSpi>)
                    ClbssLobdfrUtils.lobdClbss(implfmfntingClbss, SignbturfAlgoritim.dlbss);
            blgoritimHbsi.put(blgoritimURI, dlbzz);
        } dbtdi (NullPointfrExdfption fx) {
            Objfdt fxArgs[] = { blgoritimURI, fx.gftMfssbgf() };
            tirow nfw XMLSignbturfExdfption("blgoritims.NoSudiAlgoritim", fxArgs, fx);
        }
    }

    /**
     * Rfgistfrs implfmfnting dlbss of tif Trbnsform blgoritim witi blgoritimURI
     *
     * @pbrbm blgoritimURI blgoritimURI URI rfprfsfntbtion of <dodf>Trbnsform blgoritim</dodf>.
     * @pbrbm implfmfntingClbss <dodf>implfmfntingClbss</dodf> tif implfmfnting dlbss of
     * {@link SignbturfAlgoritimSpi}
     * @tirows AlgoritimAlrfbdyRfgistfrfdExdfption if spfdififd blgoritimURI is blrfbdy rfgistfrfd
     * @tirows XMLSignbturfExdfption
     */
    publid stbtid void rfgistfr(String blgoritimURI, Clbss<? fxtfnds SignbturfAlgoritimSpi> implfmfntingClbss)
       tirows AlgoritimAlrfbdyRfgistfrfdExdfption, ClbssNotFoundExdfption,
           XMLSignbturfExdfption {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Try to rfgistfr " + blgoritimURI + " " + implfmfntingClbss);
        }

        // brf wf blrfbdy rfgistfrfd?
        Clbss<? fxtfnds SignbturfAlgoritimSpi> rfgistfrfdClbss = blgoritimHbsi.gft(blgoritimURI);
        if (rfgistfrfdClbss != null) {
            Objfdt fxArgs[] = { blgoritimURI, rfgistfrfdClbss };
            tirow nfw AlgoritimAlrfbdyRfgistfrfdExdfption(
                "blgoritim.blrfbdyRfgistfrfd", fxArgs
            );
        }
        blgoritimHbsi.put(blgoritimURI, implfmfntingClbss);
    }

    /**
     * Tiis mftiod rfgistfrs tif dffbult blgoritims.
     */
    publid stbtid void rfgistfrDffbultAlgoritims() {
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_DSA, SignbturfDSA.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_DSA_SHA256, SignbturfDSA.SHA256.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA1, SignbturfBbsfRSA.SignbturfRSASHA1.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA1, IntfgrityHmbd.IntfgrityHmbdSHA1.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5,
            SignbturfBbsfRSA.SignbturfRSAMD5.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_RIPEMD160,
            SignbturfBbsfRSA.SignbturfRSARIPEMD160.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA256, SignbturfBbsfRSA.SignbturfRSASHA256.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA384, SignbturfBbsfRSA.SignbturfRSASHA384.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA512, SignbturfBbsfRSA.SignbturfRSASHA512.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA1, SignbturfECDSA.SignbturfECDSASHA1.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA256, SignbturfECDSA.SignbturfECDSASHA256.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA384, SignbturfECDSA.SignbturfECDSASHA384.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA512, SignbturfECDSA.SignbturfECDSASHA512.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5, IntfgrityHmbd.IntfgrityHmbdMD5.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_RIPEMD160, IntfgrityHmbd.IntfgrityHmbdRIPEMD160.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA256, IntfgrityHmbd.IntfgrityHmbdSHA256.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA384, IntfgrityHmbd.IntfgrityHmbdSHA384.dlbss
        );
        blgoritimHbsi.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA512, IntfgrityHmbd.IntfgrityHmbdSHA512.dlbss
        );
    }

    /**
     * Mftiod gftBbsfNbmfspbdf
     *
     * @rfturn URI of tiis flfmfnt
     */
    publid String gftBbsfNbmfspbdf() {
        rfturn Constbnts.SignbturfSpfdNS;
    }

    /**
     * Mftiod gftBbsfLodblNbmf
     *
     * @rfturn Lodbl nbmf
     */
    publid String gftBbsfLodblNbmf() {
        rfturn Constbnts._TAG_SIGNATUREMETHOD;
    }
}
