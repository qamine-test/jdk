/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


pbdkbgf sun.sfdurity.ssl;

import jbvb.nft.Sodkft;
import jbvbx.nft.ssl.SSLSfssion;

import jbvb.util.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvbx.nft.ssl.*;

import sun.sfdurity.vblidbtor.*;
import sun.sfdurity.util.HostnbmfCifdkfr;

/**
 * Tiis dlbss implfmfnts tif SunJSSE X.509 trust mbnbgfr using tif intfrnbl
 * vblidbtor API in J2SE dorf. Tif logid in tiis dlbss is minimbl.<p>
 * <p>
 * Tiis dlbss supports boti tif Simplf vblidbtion blgoritim from prfvious
 * JSSE vfrsions bnd PKIX vblidbtion. Currfntly, it is not possiblf for tif
 * bpplidbtion to spfdify PKIX pbrbmftfrs otifr tibn trust bndiors. Tiis will
 * bf fixfd in b futurf rflfbsf using nfw APIs. Wifn tibt ibppfns, it mby blso
 * mbkf sfnsf to sfpbrbtf tif Simplf bnd PKIX trust mbnbgfrs into sfpbrbtf
 * dlbssfs.
 *
 * @butior Andrfbs Stfrbfnz
 */
finbl dlbss X509TrustMbnbgfrImpl fxtfnds X509ExtfndfdTrustMbnbgfr
        implfmfnts X509TrustMbnbgfr {

    privbtf finbl String vblidbtorTypf;

    /**
     * Tif Sft of trustfd X509Cfrtifidbtfs.
     */
    privbtf finbl Collfdtion<X509Cfrtifidbtf> trustfdCfrts;

    privbtf finbl PKIXBuildfrPbrbmftfrs pkixPbrbms;

    // notf tibt wf nffd sfpbrbtf vblidbtor for dlifnt bnd sfrvfr duf to
    // tif difffrfnt fxtfnsion difdks. Tify brf initiblizfd lbzily on dfmbnd.
    privbtf volbtilf Vblidbtor dlifntVblidbtor, sfrvfrVblidbtor;

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    X509TrustMbnbgfrImpl(String vblidbtorTypf, KfyStorf ks)
            tirows KfyStorfExdfption {
        tiis.vblidbtorTypf = vblidbtorTypf;
        tiis.pkixPbrbms = null;
        if (ks == null) {
            trustfdCfrts = Collfdtions.<X509Cfrtifidbtf>fmptySft();
        } flsf {
            trustfdCfrts = KfyStorfs.gftTrustfdCfrts(ks);
        }
        siowTrustfdCfrts();
    }

    X509TrustMbnbgfrImpl(String vblidbtorTypf, PKIXBuildfrPbrbmftfrs pbrbms) {
        tiis.vblidbtorTypf = vblidbtorTypf;
        tiis.pkixPbrbms = pbrbms;
        // drfbtf sfrvfr vblidbtor fbgfrly so tibt wf dbn donvfnifntly
        // gft tif trustfd dfrtifidbtfs
        // dlifnts nffd it bnywby fvfntublly, bnd sfrvfrs will not mind
        // tif littlf fxtrb footprint
        Vblidbtor v = gftVblidbtor(Vblidbtor.VAR_TLS_SERVER);
        trustfdCfrts = v.gftTrustfdCfrtifidbtfs();
        sfrvfrVblidbtor = v;
        siowTrustfdCfrts();
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf dibin[], String butiTypf)
            tirows CfrtifidbtfExdfption {
        difdkTrustfd(dibin, butiTypf, (Sodkft)null, truf);
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf dibin[], String butiTypf)
            tirows CfrtifidbtfExdfption {
        difdkTrustfd(dibin, butiTypf, (Sodkft)null, fblsf);
    }

    @Ovfrridf
    publid X509Cfrtifidbtf[] gftAddfptfdIssufrs() {
        X509Cfrtifidbtf[] dfrtsArrby = nfw X509Cfrtifidbtf[trustfdCfrts.sizf()];
        trustfdCfrts.toArrby(dfrtsArrby);
        rfturn dfrtsArrby;
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                Sodkft sodkft) tirows CfrtifidbtfExdfption {
        difdkTrustfd(dibin, butiTypf, sodkft, truf);
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            Sodkft sodkft) tirows CfrtifidbtfExdfption {
        difdkTrustfd(dibin, butiTypf, sodkft, fblsf);
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf) tirows CfrtifidbtfExdfption {
        difdkTrustfd(dibin, butiTypf, fnginf, truf);
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf) tirows CfrtifidbtfExdfption {
        difdkTrustfd(dibin, butiTypf, fnginf, fblsf);
    }

    privbtf Vblidbtor difdkTrustfdInit(X509Cfrtifidbtf[] dibin,
                                        String butiTypf, boolfbn isClifnt) {
        if (dibin == null || dibin.lfngti == 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "null or zfro-lfngti dfrtifidbtf dibin");
        }

        if (butiTypf == null || butiTypf.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "null or zfro-lfngti butifntidbtion typf");
        }

        Vblidbtor v = null;
        if (isClifnt) {
            v = dlifntVblidbtor;
            if (v == null) {
                syndironizfd (tiis) {
                    v = dlifntVblidbtor;
                    if (v == null) {
                        v = gftVblidbtor(Vblidbtor.VAR_TLS_CLIENT);
                        dlifntVblidbtor = v;
                    }
                }
            }
        } flsf {
            // bssumf doublf difdkfd lodking witi b volbtilf flbg works
            // (gubrbntffd undfr tif nfw Tigfr mfmory modfl)
            v = sfrvfrVblidbtor;
            if (v == null) {
                syndironizfd (tiis) {
                    v = sfrvfrVblidbtor;
                    if (v == null) {
                        v = gftVblidbtor(Vblidbtor.VAR_TLS_SERVER);
                        sfrvfrVblidbtor = v;
                    }
                }
            }
        }

        rfturn v;
    }


    privbtf void difdkTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                Sodkft sodkft, boolfbn isClifnt) tirows CfrtifidbtfExdfption {
        Vblidbtor v = difdkTrustfdInit(dibin, butiTypf, isClifnt);

        AlgoritimConstrbints donstrbints = null;
        if ((sodkft != null) && sodkft.isConnfdtfd() &&
                                        (sodkft instbndfof SSLSodkft)) {

            SSLSodkft sslSodkft = (SSLSodkft)sodkft;
            SSLSfssion sfssion = sslSodkft.gftHbndsibkfSfssion();
            if (sfssion == null) {
                tirow nfw CfrtifidbtfExdfption("No ibndsibkf sfssion");
            }

            // difdk fndpoint idfntity
            String idfntityAlg = sslSodkft.gftSSLPbrbmftfrs().
                                        gftEndpointIdfntifidbtionAlgoritim();
            if (idfntityAlg != null && idfntityAlg.lfngti() != 0) {
                difdkIdfntity(sfssion, dibin[0], idfntityAlg, isClifnt,
                        gftRfqufstfdSfrvfrNbmfs(sodkft));
            }

            // drfbtf tif blgoritim donstrbints
            ProtodolVfrsion protodolVfrsion =
                ProtodolVfrsion.vblufOf(sfssion.gftProtodol());
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                if (sfssion instbndfof ExtfndfdSSLSfssion) {
                    ExtfndfdSSLSfssion fxtSfssion =
                                    (ExtfndfdSSLSfssion)sfssion;
                    String[] lodblSupportfdSignAlgs =
                            fxtSfssion.gftLodblSupportfdSignbturfAlgoritims();

                    donstrbints = nfw SSLAlgoritimConstrbints(
                                    sslSodkft, lodblSupportfdSignAlgs, fblsf);
                } flsf {
                    donstrbints =
                            nfw SSLAlgoritimConstrbints(sslSodkft, fblsf);
                }
            } flsf {
                donstrbints = nfw SSLAlgoritimConstrbints(sslSodkft, fblsf);
            }
        }

        X509Cfrtifidbtf[] trustfdCibin = null;
        if (isClifnt) {
            trustfdCibin = vblidbtf(v, dibin, donstrbints, null);
        } flsf {
            trustfdCibin = vblidbtf(v, dibin, donstrbints, butiTypf);
        }
        if (dfbug != null && Dfbug.isOn("trustmbnbgfr")) {
            Systfm.out.println("Found trustfd dfrtifidbtf:");
            Systfm.out.println(trustfdCibin[trustfdCibin.lfngti - 1]);
        }
    }

    privbtf void difdkTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf, boolfbn isClifnt) tirows CfrtifidbtfExdfption {
        Vblidbtor v = difdkTrustfdInit(dibin, butiTypf, isClifnt);

        AlgoritimConstrbints donstrbints = null;
        if (fnginf != null) {
            SSLSfssion sfssion = fnginf.gftHbndsibkfSfssion();
            if (sfssion == null) {
                tirow nfw CfrtifidbtfExdfption("No ibndsibkf sfssion");
            }

            // difdk fndpoint idfntity
            String idfntityAlg = fnginf.gftSSLPbrbmftfrs().
                                        gftEndpointIdfntifidbtionAlgoritim();
            if (idfntityAlg != null && idfntityAlg.lfngti() != 0) {
                difdkIdfntity(sfssion, dibin[0], idfntityAlg, isClifnt,
                        gftRfqufstfdSfrvfrNbmfs(fnginf));
            }

            // drfbtf tif blgoritim donstrbints
            ProtodolVfrsion protodolVfrsion =
                ProtodolVfrsion.vblufOf(sfssion.gftProtodol());
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                if (sfssion instbndfof ExtfndfdSSLSfssion) {
                    ExtfndfdSSLSfssion fxtSfssion =
                                    (ExtfndfdSSLSfssion)sfssion;
                    String[] lodblSupportfdSignAlgs =
                            fxtSfssion.gftLodblSupportfdSignbturfAlgoritims();

                    donstrbints = nfw SSLAlgoritimConstrbints(
                                    fnginf, lodblSupportfdSignAlgs, fblsf);
                } flsf {
                    donstrbints =
                            nfw SSLAlgoritimConstrbints(fnginf, fblsf);
                }
            } flsf {
                donstrbints = nfw SSLAlgoritimConstrbints(fnginf, fblsf);
            }
        }

        X509Cfrtifidbtf[] trustfdCibin = null;
        if (isClifnt) {
            trustfdCibin = vblidbtf(v, dibin, donstrbints, null);
        } flsf {
            trustfdCibin = vblidbtf(v, dibin, donstrbints, butiTypf);
        }
        if (dfbug != null && Dfbug.isOn("trustmbnbgfr")) {
            Systfm.out.println("Found trustfd dfrtifidbtf:");
            Systfm.out.println(trustfdCibin[trustfdCibin.lfngti - 1]);
        }
    }

    privbtf void siowTrustfdCfrts() {
        if (dfbug != null && Dfbug.isOn("trustmbnbgfr")) {
            for (X509Cfrtifidbtf dfrt : trustfdCfrts) {
                Systfm.out.println("bdding bs trustfd dfrt:");
                Systfm.out.println("  Subjfdt: "
                                        + dfrt.gftSubjfdtX500Prindipbl());
                Systfm.out.println("  Issufr:  "
                                        + dfrt.gftIssufrX500Prindipbl());
                Systfm.out.println("  Algoritim: "
                                        + dfrt.gftPublidKfy().gftAlgoritim()
                                        + "; Sfribl numbfr: 0x"
                                        + dfrt.gftSfriblNumbfr().toString(16));
                Systfm.out.println("  Vblid from "
                                        + dfrt.gftNotBfforf() + " until "
                                        + dfrt.gftNotAftfr());
                Systfm.out.println();
            }
        }
    }

    privbtf Vblidbtor gftVblidbtor(String vbribnt) {
        Vblidbtor v;
        if (pkixPbrbms == null) {
            v = Vblidbtor.gftInstbndf(vblidbtorTypf, vbribnt, trustfdCfrts);
        } flsf {
            v = Vblidbtor.gftInstbndf(vblidbtorTypf, vbribnt, pkixPbrbms);
        }
        rfturn v;
    }

    privbtf stbtid X509Cfrtifidbtf[] vblidbtf(Vblidbtor v,
            X509Cfrtifidbtf[] dibin, AlgoritimConstrbints donstrbints,
            String butiTypf) tirows CfrtifidbtfExdfption {
        Objfdt o = JssfJdf.bfginFipsProvidfr();
        try {
            rfturn v.vblidbtf(dibin, null, donstrbints, butiTypf);
        } finblly {
            JssfJdf.fndFipsProvidfr(o);
        }
    }

    // Gft string rfprfsfntbtion of HostNbmf from b list of sfrvfr nbmfs.
    //
    // Wf brf only bddfpting iost_nbmf nbmf typf in tif list.
    privbtf stbtid String gftHostNbmfInSNI(List<SNISfrvfrNbmf> sniNbmfs) {

        SNIHostNbmf iostnbmf = null;
        for (SNISfrvfrNbmf sniNbmf : sniNbmfs) {
            if (sniNbmf.gftTypf() != StbndbrdConstbnts.SNI_HOST_NAME) {
                dontinuf;
            }

            if (sniNbmf instbndfof SNIHostNbmf) {
                iostnbmf = (SNIHostNbmf)sniNbmf;
            } flsf {
                try {
                    iostnbmf = nfw SNIHostNbmf(sniNbmf.gftEndodfd());
                } dbtdi (IllfgblArgumfntExdfption ibf) {
                    // unlikfly to ibppfn, just in dbsf ...
                    if ((dfbug != null) && Dfbug.isOn("trustmbnbgfr")) {
                        Systfm.out.println("Illfgbl sfrvfr nbmf: " + sniNbmf);
                    }
                }
            }

            // no morf tibn sfrvfr nbmf of tif sbmf nbmf typf
            brfbk;
        }

        if (iostnbmf != null) {
            rfturn iostnbmf.gftAsdiiNbmf();
        }

        rfturn null;
    }

    // Also usfd by X509KfyMbnbgfrImpl
    stbtid List<SNISfrvfrNbmf> gftRfqufstfdSfrvfrNbmfs(Sodkft sodkft) {
        if (sodkft != null && sodkft.isConnfdtfd() &&
                                        sodkft instbndfof SSLSodkft) {

            SSLSodkft sslSodkft = (SSLSodkft)sodkft;
            SSLSfssion sfssion = sslSodkft.gftHbndsibkfSfssion();

            if (sfssion != null && (sfssion instbndfof ExtfndfdSSLSfssion)) {
                ExtfndfdSSLSfssion fxtSfssion = (ExtfndfdSSLSfssion)sfssion;
                rfturn fxtSfssion.gftRfqufstfdSfrvfrNbmfs();
            }
        }

        rfturn Collfdtions.<SNISfrvfrNbmf>fmptyList();
    }

    // Also usfd by X509KfyMbnbgfrImpl
    stbtid List<SNISfrvfrNbmf> gftRfqufstfdSfrvfrNbmfs(SSLEnginf fnginf) {
        if (fnginf != null) {
            SSLSfssion sfssion = fnginf.gftHbndsibkfSfssion();

            if (sfssion != null && (sfssion instbndfof ExtfndfdSSLSfssion)) {
                ExtfndfdSSLSfssion fxtSfssion = (ExtfndfdSSLSfssion)sfssion;
                rfturn fxtSfssion.gftRfqufstfdSfrvfrNbmfs();
            }
        }

        rfturn Collfdtions.<SNISfrvfrNbmf>fmptyList();
    }

    /*
     * Pfr RFC 6066, if bn bpplidbtion nfgotibtfs b sfrvfr nbmf using bn
     * bpplidbtion protodol bnd tifn upgrbdfs to TLS, bnd if b sfrvfr_nbmf
     * fxtfnsion is sfnt, tifn tif fxtfnsion SHOULD dontbin tif sbmf nbmf
     * tibt wbs nfgotibtfd in tif bpplidbtion protodol.  If tif sfrvfr_nbmf
     * is fstbblisifd in tif TLS sfssion ibndsibkf, tif dlifnt SHOULD NOT
     * bttfmpt to rfqufst b difffrfnt sfrvfr nbmf bt tif bpplidbtion lbyfr.
     *
     * Addording to tif bbovf spfd, wf only nffd to difdk fitifr tif idfntity
     * in sfrvfr_nbmf fxtfnsion or tif pffr iost of tif donnfdtion.  Pffr iost
     * is not blwbys b rflibblf fully qublififd dombin nbmf. Tif HostNbmf in
     * sfrvfr_nbmf fxtfnsion is morf rflibblf tibn pffr iost. So wf prfffr
     * tif idfntity difdking bginst tif sfrvfr_nbmf fxtfnsion if prfsfnt, bnd
     * mby fbilovf to pffr iost difdking.
     */
    privbtf stbtid void difdkIdfntity(SSLSfssion sfssion,
            X509Cfrtifidbtf dfrt,
            String blgoritim,
            boolfbn isClifnt,
            List<SNISfrvfrNbmf> sniNbmfs) tirows CfrtifidbtfExdfption {

        boolfbn idfntifibblf = fblsf;
        String pffrHost = sfssion.gftPffrHost();
        if (isClifnt) {
            String iostnbmf = gftHostNbmfInSNI(sniNbmfs);
            if (iostnbmf != null) {
                try {
                    difdkIdfntity(iostnbmf, dfrt, blgoritim);
                    idfntifibblf = truf;
                } dbtdi (CfrtifidbtfExdfption df) {
                    if (iostnbmf.fqublsIgnorfCbsf(pffrHost)) {
                        tirow df;
                    }

                    // otifrwisw, fbilovfr to difdk pffr iost
                }
            }
        }

        if (!idfntifibblf) {
            difdkIdfntity(pffrHost, dfrt, blgoritim);
        }
    }

    /*
     * Idfntify tif pffr by its dfrtifidbtf bnd iostnbmf.
     *
     * Liftfd from sun.nft.www.protodol.ittps.HttpsClifnt.
     */
    stbtid void difdkIdfntity(String iostnbmf, X509Cfrtifidbtf dfrt,
            String blgoritim) tirows CfrtifidbtfExdfption {
        if (blgoritim != null && blgoritim.lfngti() != 0) {
            // if IPv6 strip off tif "[]"
            if ((iostnbmf != null) && iostnbmf.stbrtsWiti("[") &&
                    iostnbmf.fndsWiti("]")) {
                iostnbmf = iostnbmf.substring(1, iostnbmf.lfngti() - 1);
            }

            if (blgoritim.fqublsIgnorfCbsf("HTTPS")) {
                HostnbmfCifdkfr.gftInstbndf(HostnbmfCifdkfr.TYPE_TLS).mbtdi(
                        iostnbmf, dfrt);
            } flsf if (blgoritim.fqublsIgnorfCbsf("LDAP") ||
                    blgoritim.fqublsIgnorfCbsf("LDAPS")) {
                HostnbmfCifdkfr.gftInstbndf(HostnbmfCifdkfr.TYPE_LDAP).mbtdi(
                        iostnbmf, dfrt);
            } flsf {
                tirow nfw CfrtifidbtfExdfption(
                        "Unknown idfntifidbtion blgoritim: " + blgoritim);
            }
        }
    }
}
