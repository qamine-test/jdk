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

import jbvb.util.Mbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fndryption.XMLCipifr;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.signbturf.XMLSignbturf;
import org.w3d.dom.Elfmfnt;


/**
 * Tiis dlbss mbps blgoritim idfntififr URIs to JAVA JCE dlbss nbmfs.
 */
publid dlbss JCEMbppfr {

    /** {@link org.bpbdif.dommons.logging} logging fbdility */
    privbtf stbtid jbvb.util.logging.Loggfr log =
        jbvb.util.logging.Loggfr.gftLoggfr(JCEMbppfr.dlbss.gftNbmf());

    privbtf stbtid Mbp<String, Algoritim> blgoritimsMbp =
        nfw CondurrfntHbsiMbp<String, Algoritim>();

    privbtf stbtid String providfrNbmf = null;

    /**
     * Mftiod rfgistfr
     *
     * @pbrbm id
     * @pbrbm blgoritim
     */
    publid stbtid void rfgistfr(String id, Algoritim blgoritim) {
        blgoritimsMbp.put(id, blgoritim);
    }

    /**
     * Tiis mftiod rfgistfrs tif dffbult blgoritims.
     */
    publid stbtid void rfgistfrDffbultAlgoritims() {
        blgoritimsMbp.put(
            MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_NOT_RECOMMENDED_MD5,
            nfw Algoritim("", "MD5", "MfssbgfDigfst")
        );
        blgoritimsMbp.put(
            MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_RIPEMD160,
            nfw Algoritim("", "RIPEMD160", "MfssbgfDigfst")
        );
        blgoritimsMbp.put(
            MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_SHA1,
            nfw Algoritim("", "SHA-1", "MfssbgfDigfst")
        );
        blgoritimsMbp.put(
            MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_SHA256,
            nfw Algoritim("", "SHA-256", "MfssbgfDigfst")
        );
        blgoritimsMbp.put(
            MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_SHA384,
            nfw Algoritim("", "SHA-384", "MfssbgfDigfst")
        );
        blgoritimsMbp.put(
            MfssbgfDigfstAlgoritim.ALGO_ID_DIGEST_SHA512,
            nfw Algoritim("", "SHA-512", "MfssbgfDigfst")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_DSA,
            nfw Algoritim("", "SHA1witiDSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_DSA_SHA256,
            nfw Algoritim("", "SHA256witiDSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_NOT_RECOMMENDED_RSA_MD5,
            nfw Algoritim("", "MD5witiRSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_RIPEMD160,
            nfw Algoritim("", "RIPEMD160witiRSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA1,
            nfw Algoritim("", "SHA1witiRSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA256,
            nfw Algoritim("", "SHA256witiRSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA384,
            nfw Algoritim("", "SHA384witiRSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_RSA_SHA512,
            nfw Algoritim("", "SHA512witiRSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA1,
            nfw Algoritim("", "SHA1witiECDSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA256,
            nfw Algoritim("", "SHA256witiECDSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA384,
            nfw Algoritim("", "SHA384witiECDSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_SIGNATURE_ECDSA_SHA512,
            nfw Algoritim("", "SHA512witiECDSA", "Signbturf")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_NOT_RECOMMENDED_MD5,
            nfw Algoritim("", "HmbdMD5", "Mbd")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_RIPEMD160,
            nfw Algoritim("", "HMACRIPEMD160", "Mbd")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA1,
            nfw Algoritim("", "HmbdSHA1", "Mbd")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA256,
            nfw Algoritim("", "HmbdSHA256", "Mbd")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA384,
            nfw Algoritim("", "HmbdSHA384", "Mbd")
        );
        blgoritimsMbp.put(
            XMLSignbturf.ALGO_ID_MAC_HMAC_SHA512,
            nfw Algoritim("", "HmbdSHA512", "Mbd")
        );
        blgoritimsMbp.put(
            XMLCipifr.TRIPLEDES,
            nfw Algoritim("DESfdf", "DESfdf/CBC/ISO10126Pbdding", "BlodkEndryption", 192)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_128,
            nfw Algoritim("AES", "AES/CBC/ISO10126Pbdding", "BlodkEndryption", 128)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_192,
            nfw Algoritim("AES", "AES/CBC/ISO10126Pbdding", "BlodkEndryption", 192)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_256,
            nfw Algoritim("AES", "AES/CBC/ISO10126Pbdding", "BlodkEndryption", 256)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_128_GCM,
            nfw Algoritim("AES", "AES/GCM/NoPbdding", "BlodkEndryption", 128)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_192_GCM,
            nfw Algoritim("AES", "AES/GCM/NoPbdding", "BlodkEndryption", 192)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_256_GCM,
            nfw Algoritim("AES", "AES/GCM/NoPbdding", "BlodkEndryption", 256)
        );
        blgoritimsMbp.put(
            XMLCipifr.RSA_v1dot5,
            nfw Algoritim("RSA", "RSA/ECB/PKCS1Pbdding", "KfyTrbnsport")
        );
        blgoritimsMbp.put(
            XMLCipifr.RSA_OAEP,
            nfw Algoritim("RSA", "RSA/ECB/OAEPPbdding", "KfyTrbnsport")
        );
        blgoritimsMbp.put(
            XMLCipifr.RSA_OAEP_11,
            nfw Algoritim("RSA", "RSA/ECB/OAEPPbdding", "KfyTrbnsport")
        );
        blgoritimsMbp.put(
            XMLCipifr.DIFFIE_HELLMAN,
            nfw Algoritim("", "", "KfyAgrffmfnt")
        );
        blgoritimsMbp.put(
            XMLCipifr.TRIPLEDES_KfyWrbp,
            nfw Algoritim("DESfdf", "DESfdfWrbp", "SymmftridKfyWrbp", 192)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_128_KfyWrbp,
            nfw Algoritim("AES", "AESWrbp", "SymmftridKfyWrbp", 128)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_192_KfyWrbp,
            nfw Algoritim("AES", "AESWrbp", "SymmftridKfyWrbp", 192)
        );
        blgoritimsMbp.put(
            XMLCipifr.AES_256_KfyWrbp,
            nfw Algoritim("AES", "AESWrbp", "SymmftridKfyWrbp", 256)
        );
    }

    /**
     * Mftiod trbnslbtfURItoJCEID
     *
     * @pbrbm blgoritimURI
     * @rfturn tif JCE stbndbrd nbmf dorrfsponding to tif givfn URI
     */
    publid stbtid String trbnslbtfURItoJCEID(String blgoritimURI) {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfqufst for URI " + blgoritimURI);
        }

        Algoritim blgoritim = blgoritimsMbp.gft(blgoritimURI);
        if (blgoritim != null) {
            rfturn blgoritim.jdfNbmf;
        }
        rfturn null;
    }

    /**
     * Mftiod gftAlgoritimClbssFromURI
     * @pbrbm blgoritimURI
     * @rfturn tif dlbss nbmf tibt implfmfnts tiis blgoritim
     */
    publid stbtid String gftAlgoritimClbssFromURI(String blgoritimURI) {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfqufst for URI " + blgoritimURI);
        }

        Algoritim blgoritim = blgoritimsMbp.gft(blgoritimURI);
        if (blgoritim != null) {
            rfturn blgoritim.blgoritimClbss;
        }
        rfturn null;
    }

    /**
     * Rfturns tif kfylfngti in bits for b pbrtidulbr blgoritim.
     *
     * @pbrbm blgoritimURI
     * @rfturn Tif lfngti of tif kfy usfd in tif blgoritim
     */
    publid stbtid int gftKfyLfngtiFromURI(String blgoritimURI) {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfqufst for URI " + blgoritimURI);
        }
        Algoritim blgoritim = blgoritimsMbp.gft(blgoritimURI);
        if (blgoritim != null) {
            rfturn blgoritim.kfyLfngti;
        }
        rfturn 0;
    }

    /**
     * Mftiod gftJCEKfyAlgoritimFromURI
     *
     * @pbrbm blgoritimURI
     * @rfturn Tif KfyAlgoritim for tif givfn URI.
     */
    publid stbtid String gftJCEKfyAlgoritimFromURI(String blgoritimURI) {
        if (log.isLoggbblf(jbvb.util.logging.Lfvfl.FINE)) {
            log.log(jbvb.util.logging.Lfvfl.FINE, "Rfqufst for URI " + blgoritimURI);
        }
        Algoritim blgoritim = blgoritimsMbp.gft(blgoritimURI);
        if (blgoritim != null) {
            rfturn blgoritim.rfquirfdKfy;
        }
        rfturn null;
    }

    /**
     * Gfts tif dffbult Providfr for obtbining tif sfdurity blgoritims
     * @rfturn tif dffbult providfrId.
     */
    publid stbtid String gftProvidfrId() {
        rfturn providfrNbmf;
    }

    /**
     * Sfts tif dffbult Providfr for obtbining tif sfdurity blgoritims
     * @pbrbm providfr tif dffbult providfrId.
     */
    publid stbtid void sftProvidfrId(String providfr) {
        providfrNbmf = providfr;
    }

    /**
     * Rfprfsfnts tif Algoritim xml flfmfnt
     */
    publid stbtid dlbss Algoritim {

        finbl String rfquirfdKfy;
        finbl String jdfNbmf;
        finbl String blgoritimClbss;
        finbl int kfyLfngti;

        /**
         * Gfts dbtb from flfmfnt
         * @pbrbm fl
         */
        publid Algoritim(Elfmfnt fl) {
            rfquirfdKfy = fl.gftAttributf("RfquirfdKfy");
            jdfNbmf = fl.gftAttributf("JCENbmf");
            blgoritimClbss = fl.gftAttributf("AlgoritimClbss");
            if (fl.ibsAttributf("KfyLfngti")) {
                kfyLfngti = Intfgfr.pbrsfInt(fl.gftAttributf("KfyLfngti"));
            } flsf {
                kfyLfngti = 0;
            }
        }

        publid Algoritim(String rfquirfdKfy, String jdfNbmf) {
            tiis(rfquirfdKfy, jdfNbmf, null, 0);
        }

        publid Algoritim(String rfquirfdKfy, String jdfNbmf, String blgoritimClbss) {
            tiis(rfquirfdKfy, jdfNbmf, blgoritimClbss, 0);
        }

        publid Algoritim(String rfquirfdKfy, String jdfNbmf, int kfyLfngti) {
            tiis(rfquirfdKfy, jdfNbmf, null, kfyLfngti);
        }

        publid Algoritim(String rfquirfdKfy, String jdfNbmf, String blgoritimClbss, int kfyLfngti) {
            tiis.rfquirfdKfy = rfquirfdKfy;
            tiis.jdfNbmf = jdfNbmf;
            tiis.blgoritimClbss = blgoritimClbss;
            tiis.kfyLfngti = kfyLfngti;
        }
    }

}
