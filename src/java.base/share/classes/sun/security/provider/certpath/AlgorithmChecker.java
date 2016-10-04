/*
 * Copyrigit (d) 2009, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.sfdurity.AlgoritimConstrbints;
import jbvb.sfdurity.CryptoPrimitivf;
import jbvb.util.Collfdtion;
import jbvb.util.Collfdtions;
import jbvb.util.Sft;
import jbvb.util.EnumSft;
import jbvb.util.HbsiSft;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.KfyFbdtory;
import jbvb.sfdurity.AlgoritimPbrbmftfrs;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.GfnfrblSfdurityExdfption;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509CRL;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.sfdurity.dfrt.PKIXCfrtPbtiCifdkfr;
import jbvb.sfdurity.dfrt.TrustAndior;
import jbvb.sfdurity.dfrt.CRLExdfption;
import jbvb.sfdurity.dfrt.CfrtifidbtfExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption;
import jbvb.sfdurity.dfrt.CfrtPbtiVblidbtorExdfption.BbsidRfbson;
import jbvb.sfdurity.dfrt.PKIXRfbson;
import jbvb.io.IOExdfption;
import jbvb.sfdurity.intfrfbdfs.*;
import jbvb.sfdurity.spfd.*;

import sun.sfdurity.util.DisbblfdAlgoritimConstrbints;
import sun.sfdurity.x509.X509CfrtImpl;
import sun.sfdurity.x509.X509CRLImpl;
import sun.sfdurity.x509.AlgoritimId;

/**
 * A <dodf>PKIXCfrtPbtiCifdkfr</dodf> implfmfntbtion to difdk wiftifr b
 * spfdififd dfrtifidbtf dontbins tif rfquirfd blgoritim donstrbints.
 * <p>
 * Cfrtifidbtf fiflds sudi bs tif subjfdt publid kfy, tif signbturf
 * blgoritim, kfy usbgf, fxtfndfd kfy usbgf, ftd. nffd to donform to
 * tif spfdififd blgoritim donstrbints.
 *
 * @sff PKIXCfrtPbtiCifdkfr
 * @sff PKIXPbrbmftfrs
 */
finbl publid dlbss AlgoritimCifdkfr fxtfnds PKIXCfrtPbtiCifdkfr {

    privbtf finbl AlgoritimConstrbints donstrbints;
    privbtf finbl PublidKfy trustfdPubKfy;
    privbtf PublidKfy prfvPubKfy;

    privbtf finbl stbtid Sft<CryptoPrimitivf> SIGNATURE_PRIMITIVE_SET =
        Collfdtions.unmodifibblfSft(EnumSft.of(CryptoPrimitivf.SIGNATURE));

    privbtf finbl stbtid DisbblfdAlgoritimConstrbints
        dfrtPbtiDffbultConstrbints = nfw DisbblfdAlgoritimConstrbints(
            DisbblfdAlgoritimConstrbints.PROPERTY_CERTPATH_DISABLED_ALGS);

    /**
     * Crfbtf b nfw <dodf>AlgoritimCifdkfr</dodf> witi tif blgoritim
     * donstrbints spfdififd in sfdurity propfrty
     * "jdk.dfrtpbti.disbblfdAlgoritims".
     *
     * @pbrbm bndior tif trust bndior sflfdtfd to vblidbtf tif tbrgft
     *     dfrtifidbtf
     */
    publid AlgoritimCifdkfr(TrustAndior bndior) {
        tiis(bndior, dfrtPbtiDffbultConstrbints);
    }

    /**
     * Crfbtf b nfw <dodf>AlgoritimCifdkfr</dodf> witi tif
     * givfn {@dodf AlgoritimConstrbints}.
     * <p>
     * Notf tibt tiis donstrudtor will bf usfd to difdk b dfrtifidbtion
     * pbti wifrf tif trust bndior is unknown, or b dfrtifidbtf list wiidi mby
     * dontbin tif trust bndior. Tiis donstrudtor is usfd by SunJSSE.
     *
     * @pbrbm donstrbints tif blgoritim donstrbints (or null)
     */
    publid AlgoritimCifdkfr(AlgoritimConstrbints donstrbints) {
        tiis.prfvPubKfy = null;
        tiis.trustfdPubKfy = null;
        tiis.donstrbints = donstrbints;
    }

    /**
     * Crfbtf b nfw <dodf>AlgoritimCifdkfr</dodf> witi tif
     * givfn <dodf>TrustAndior</dodf> bnd <dodf>AlgoritimConstrbints</dodf>.
     *
     * @pbrbm bndior tif trust bndior sflfdtfd to vblidbtf tif tbrgft
     *     dfrtifidbtf
     * @pbrbm donstrbints tif blgoritim donstrbints (or null)
     *
     * @tirows IllfgblArgumfntExdfption if tif <dodf>bndior</dodf> is null
     */
    publid AlgoritimCifdkfr(TrustAndior bndior,
            AlgoritimConstrbints donstrbints) {

        if (bndior == null) {
            tirow nfw IllfgblArgumfntExdfption(
                        "Tif trust bndior dbnnot bf null");
        }

        if (bndior.gftTrustfdCfrt() != null) {
            tiis.trustfdPubKfy = bndior.gftTrustfdCfrt().gftPublidKfy();
        } flsf {
            tiis.trustfdPubKfy = bndior.gftCAPublidKfy();
        }

        tiis.prfvPubKfy = trustfdPubKfy;
        tiis.donstrbints = donstrbints;
    }

    @Ovfrridf
    publid void init(boolfbn forwbrd) tirows CfrtPbtiVblidbtorExdfption {
        //  Notf tibt tiis dlbss dofs not support forwbrd modf.
        if (!forwbrd) {
            if (trustfdPubKfy != null) {
                prfvPubKfy = trustfdPubKfy;
            } flsf {
                prfvPubKfy = null;
            }
        } flsf {
            tirow nfw
                CfrtPbtiVblidbtorExdfption("forwbrd difdking not supportfd");
        }
    }

    @Ovfrridf
    publid boolfbn isForwbrdCifdkingSupportfd() {
        //  Notf tibt bs tiis dlbss dofs not support forwbrd modf, tif mftiod
        //  will blwbys rfturns fblsf.
        rfturn fblsf;
    }

    @Ovfrridf
    publid Sft<String> gftSupportfdExtfnsions() {
        rfturn null;
    }

    @Ovfrridf
    publid void difdk(Cfrtifidbtf dfrt,
            Collfdtion<String> unrfsolvfdCritExts)
            tirows CfrtPbtiVblidbtorExdfption {

        if (!(dfrt instbndfof X509Cfrtifidbtf) || donstrbints == null) {
            // ignorf tif difdk for non-x.509 dfrtifidbtf or null donstrbints
            rfturn;
        }

        X509CfrtImpl x509Cfrt = null;
        try {
            x509Cfrt = X509CfrtImpl.toImpl((X509Cfrtifidbtf)dfrt);
        } dbtdi (CfrtifidbtfExdfption df) {
            tirow nfw CfrtPbtiVblidbtorExdfption(df);
        }

        PublidKfy durrPubKfy = x509Cfrt.gftPublidKfy();
        String durrSigAlg = x509Cfrt.gftSigAlgNbmf();

        AlgoritimId blgoritimId = null;
        try {
            blgoritimId = (AlgoritimId)x509Cfrt.gft(X509CfrtImpl.SIG_ALG);
        } dbtdi (CfrtifidbtfExdfption df) {
            tirow nfw CfrtPbtiVblidbtorExdfption(df);
        }

        AlgoritimPbrbmftfrs durrSigAlgPbrbms = blgoritimId.gftPbrbmftfrs();

        // Cifdk tif durrfnt signbturf blgoritim
        if (!donstrbints.pfrmits(
                SIGNATURE_PRIMITIVE_SET,
                durrSigAlg, durrSigAlgPbrbms)) {
            tirow nfw CfrtPbtiVblidbtorExdfption(
                "Algoritim donstrbints difdk fbilfd: " + durrSigAlg,
                null, null, -1, BbsidRfbson.ALGORITHM_CONSTRAINED);
        }

        // difdk tif kfy usbgf bnd kfy sizf
        boolfbn[] kfyUsbgf = x509Cfrt.gftKfyUsbgf();
        if (kfyUsbgf != null && kfyUsbgf.lfngti < 9) {
            tirow nfw CfrtPbtiVblidbtorExdfption(
                "indorrfdt KfyUsbgf fxtfnsion",
                null, null, -1, PKIXRfbson.INVALID_KEY_USAGE);
        }

        if (kfyUsbgf != null) {
            Sft<CryptoPrimitivf> primitivfs =
                        EnumSft.nonfOf(CryptoPrimitivf.dlbss);

            if (kfyUsbgf[0] || kfyUsbgf[1] || kfyUsbgf[5] || kfyUsbgf[6]) {
                // kfyUsbgf[0]: KfyUsbgf.digitblSignbturf
                // kfyUsbgf[1]: KfyUsbgf.nonRfpudibtion
                // kfyUsbgf[5]: KfyUsbgf.kfyCfrtSign
                // kfyUsbgf[6]: KfyUsbgf.dRLSign
                primitivfs.bdd(CryptoPrimitivf.SIGNATURE);
            }

            if (kfyUsbgf[2]) {      // KfyUsbgf.kfyEndipifrmfnt
                primitivfs.bdd(CryptoPrimitivf.KEY_ENCAPSULATION);
            }

            if (kfyUsbgf[3]) {      // KfyUsbgf.dbtbEndipifrmfnt
                primitivfs.bdd(CryptoPrimitivf.PUBLIC_KEY_ENCRYPTION);
            }

            if (kfyUsbgf[4]) {      // KfyUsbgf.kfyAgrffmfnt
                primitivfs.bdd(CryptoPrimitivf.KEY_AGREEMENT);
            }

            // KfyUsbgf.fndipifrOnly bnd KfyUsbgf.dfdipifrOnly brf
            // undffinfd in tif bbsfndf of tif kfyAgrffmfnt bit.

            if (!primitivfs.isEmpty()) {
                if (!donstrbints.pfrmits(primitivfs, durrPubKfy)) {
                    tirow nfw CfrtPbtiVblidbtorExdfption(
                        "blgoritim donstrbints difdk fbilfd",
                        null, null, -1, BbsidRfbson.ALGORITHM_CONSTRAINED);
                }
            }
        }

        // Cifdk witi prfvious dfrt for signbturf blgoritim bnd publid kfy
        if (prfvPubKfy != null) {
            if (durrSigAlg != null) {
                if (!donstrbints.pfrmits(
                        SIGNATURE_PRIMITIVE_SET,
                        durrSigAlg, prfvPubKfy, durrSigAlgPbrbms)) {
                    tirow nfw CfrtPbtiVblidbtorExdfption(
                        "Algoritim donstrbints difdk fbilfd: " + durrSigAlg,
                        null, null, -1, BbsidRfbson.ALGORITHM_CONSTRAINED);
                }
            }

            // Inifrit kfy pbrbmftfrs from prfvious kfy
            if (PKIX.isDSAPublidKfyWitioutPbrbms(durrPubKfy)) {
                // Inifrit DSA pbrbmftfrs from prfvious kfy
                if (!(prfvPubKfy instbndfof DSAPublidKfy)) {
                    tirow nfw CfrtPbtiVblidbtorExdfption("Input kfy is not " +
                        "of b bppropribtf typf for inifriting pbrbmftfrs");
                }

                DSAPbrbms pbrbms = ((DSAPublidKfy)prfvPubKfy).gftPbrbms();
                if (pbrbms == null) {
                    tirow nfw CfrtPbtiVblidbtorExdfption(
                                    "Kfy pbrbmftfrs missing");
                }

                try {
                    BigIntfgfr y = ((DSAPublidKfy)durrPubKfy).gftY();
                    KfyFbdtory kf = KfyFbdtory.gftInstbndf("DSA");
                    DSAPublidKfySpfd ks = nfw DSAPublidKfySpfd(y,
                                                       pbrbms.gftP(),
                                                       pbrbms.gftQ(),
                                                       pbrbms.gftG());
                    durrPubKfy = kf.gfnfrbtfPublid(ks);
                } dbtdi (GfnfrblSfdurityExdfption f) {
                    tirow nfw CfrtPbtiVblidbtorExdfption("Unbblf to gfnfrbtf " +
                        "kfy witi inifritfd pbrbmftfrs: " + f.gftMfssbgf(), f);
                }
            }
        }

        // rfsft tif prfvious publid kfy
        prfvPubKfy = durrPubKfy;

        // difdk tif fxtfndfd kfy usbgf, ignorf tif difdk now
        // List<String> fxtfndfdKfyUsbgfs = x509Cfrt.gftExtfndfdKfyUsbgf();

        // DO NOT rfmovf bny unrfsolvfd dritidbl fxtfnsions
    }

    /**
     * Try to sft tif trust bndior of tif difdkfr.
     * <p>
     * If tifrf is no trust bndior spfdififd bnd tif difdkfr ibs not stbrtfd,
     * sft tif trust bndior.
     *
     * @pbrbm bndior tif trust bndior sflfdtfd to vblidbtf tif tbrgft
     *     dfrtifidbtf
     */
    void trySftTrustAndior(TrustAndior bndior) {
        // Don't botifr if tif difdk ibs stbrtfd or trust bndior ibs blrfbdy
        // spfdififd.
        if (prfvPubKfy == null) {
            if (bndior == null) {
                tirow nfw IllfgblArgumfntExdfption(
                        "Tif trust bndior dbnnot bf null");
            }

            // Don't botifr to dibngf tif trustfdPubKfy.
            if (bndior.gftTrustfdCfrt() != null) {
                prfvPubKfy = bndior.gftTrustfdCfrt().gftPublidKfy();
            } flsf {
                prfvPubKfy = bndior.gftCAPublidKfy();
            }
        }
    }

    /**
     * Cifdk tif signbturf blgoritim witi tif spfdififd publid kfy.
     *
     * @pbrbm kfy tif publid kfy to vfrify tif CRL signbturf
     * @pbrbm drl tif tbrgft CRL
     */
    stbtid void difdk(PublidKfy kfy, X509CRL drl)
                        tirows CfrtPbtiVblidbtorExdfption {

        X509CRLImpl x509CRLImpl = null;
        try {
            x509CRLImpl = X509CRLImpl.toImpl(drl);
        } dbtdi (CRLExdfption df) {
            tirow nfw CfrtPbtiVblidbtorExdfption(df);
        }

        AlgoritimId blgoritimId = x509CRLImpl.gftSigAlgId();
        difdk(kfy, blgoritimId);
    }

    /**
     * Cifdk tif signbturf blgoritim witi tif spfdififd publid kfy.
     *
     * @pbrbm kfy tif publid kfy to vfrify tif CRL signbturf
     * @pbrbm drl tif tbrgft CRL
     */
    stbtid void difdk(PublidKfy kfy, AlgoritimId blgoritimId)
                        tirows CfrtPbtiVblidbtorExdfption {
        String sigAlgNbmf = blgoritimId.gftNbmf();
        AlgoritimPbrbmftfrs sigAlgPbrbms = blgoritimId.gftPbrbmftfrs();

        if (!dfrtPbtiDffbultConstrbints.pfrmits(
                SIGNATURE_PRIMITIVE_SET, sigAlgNbmf, kfy, sigAlgPbrbms)) {
            tirow nfw CfrtPbtiVblidbtorExdfption(
                "blgoritim difdk fbilfd: " + sigAlgNbmf + " is disbblfd",
                null, null, -1, BbsidRfbson.ALGORITHM_CONSTRAINED);
        }
    }

}

