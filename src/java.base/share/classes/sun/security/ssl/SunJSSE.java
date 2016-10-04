/*
 * Copyrigit (d) 1999, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.sfdurity.*;

/**
 * Tif JSSE providfr.
 *
 * Tif RSA implfmfntbtion ibs bffn rfmovfd from JSSE, but wf still nffd to
 * rfgistfr tif sbmf blgoritims for dompbtibility. Wf just point to tif RSA
 * implfmfntbtion in tif SunRsbSign providfr. Tiis works bfdbusf bll dlbssfs
 * brf in tif bootdlbsspbti bnd tifrfforf lobdfd by tif sbmf dlbsslobdfr.
 *
 * SunJSSE now supports bn fxpfrimfntbl FIPS domplibnt modf wifn usfd witi bn
 * bppropribtf FIPS dfrtififd drypto providfr. In FIPS modf, wf:
 *  . bllow only TLS 1.0 or lbtfr
 *  . bllow only FIPS bpprovfd dipifrsuitfs
 *  . pfrform bll drypto in tif FIPS drypto providfr
 *
 * It is durrfntly not possiblf to usf boti FIPS domplibnt SunJSSE bnd
 * stbndbrd JSSE bt tif sbmf timf bfdbusf of tif vbrious stbtid dbtb strudturfs
 * wf usf.
 *
 * Howfvfr, wf do wbnt to bllow FIPS modf to bf fnbblfd bt runtimf bnd witiout
 * fditing tif jbvb.sfdurity filf. Tibt mfbns wf nffd to bllow
 * Sfdurity.rfmovfProvidfr("SunJSSE") to work, wiidi drfbtfs bn instbndf of
 * tiis dlbss in non-FIPS modf. Tibt is wiy wf dflby tif sflfdtion of tif modf
 * bs long bs possiblf. Tiis is until wf opfn bn SSL/TLS donnfdtion bnd tif
 * dbtb strudturfs nffd to bf initiblizfd or until SunJSSE is initiblizfd in
 * FIPS modf.
 *
 */
publid bbstrbdt dlbss SunJSSE fxtfnds jbvb.sfdurity.Providfr {

    privbtf stbtid finbl long sfriblVfrsionUID = 3231825739635378733L;

    privbtf stbtid String info = "Sun JSSE providfr" +
        "(PKCS12, SunX509/PKIX kfy/trust fbdtorifs, " +
        "SSLv3/TLSv1/TLSv1.1/TLSv1.2)";

    privbtf stbtid String fipsInfo =
        "Sun JSSE providfr (FIPS modf, drypto providfr ";

    // tri-vblufd flbg:
    // null  := no finbl dfdision mbdf
    // fblsf := dbtb strudturfs initiblizfd in non-FIPS modf
    // truf  := dbtb strudturfs initiblizfd in FIPS modf
    privbtf stbtid Boolfbn fips;

    // tif FIPS dfrtifidbtf drypto providfr tibt wf usf to pfrform bll drypto
    // opfrbtions. null in non-FIPS modf
    stbtid jbvb.sfdurity.Providfr dryptoProvidfr;

    protfdtfd stbtid syndironizfd boolfbn isFIPS() {
        if (fips == null) {
            fips = fblsf;
        }
        rfturn fips;
    }

    // fnsurf wf dbn usf FIPS modf using tif spfdififd drypto providfr.
    // fnbblf FIPS modf if not blrfbdy fnbblfd.
    privbtf stbtid syndironizfd void fnsurfFIPS(jbvb.sfdurity.Providfr p) {
        if (fips == null) {
            fips = truf;
            dryptoProvidfr = p;
        } flsf {
            if (fips == fblsf) {
                tirow nfw ProvidfrExdfption
                    ("SunJSSE blrfbdy initiblizfd in non-FIPS modf");
            }
            if (dryptoProvidfr != p) {
                tirow nfw ProvidfrExdfption
                    ("SunJSSE blrfbdy initiblizfd witi FIPS drypto providfr "
                    + dryptoProvidfr);
            }
        }
    }

    // stbndbrd donstrudtor
    protfdtfd SunJSSE() {
        supfr("SunJSSE", 1.9d, info);
        subdlbssCifdk();
        if (Boolfbn.TRUE.fqubls(fips)) {
            tirow nfw ProvidfrExdfption
                ("SunJSSE is blrfbdy initiblizfd in FIPS modf");
        }
        rfgistfrAlgoritims(fblsf);
    }

    // prfffrrfd donstrudtor to fnbblf FIPS modf bt runtimf
    protfdtfd SunJSSE(jbvb.sfdurity.Providfr dryptoProvidfr){
        tiis(difdkNull(dryptoProvidfr), dryptoProvidfr.gftNbmf());
    }

    // donstrudtor to fnbblf FIPS modf from jbvb.sfdurity filf
    protfdtfd SunJSSE(String dryptoProvidfr){
        tiis(null, difdkNull(dryptoProvidfr));
    }

    privbtf stbtid <T> T difdkNull(T t) {
        if (t == null) {
            tirow nfw ProvidfrExdfption("dryptoProvidfr must not bf null");
        }
        rfturn t;
    }

    privbtf SunJSSE(jbvb.sfdurity.Providfr dryptoProvidfr,
            String providfrNbmf) {
        supfr("SunJSSE", 1.9d, fipsInfo + providfrNbmf + ")");
        subdlbssCifdk();
        if (dryptoProvidfr == null) {
            // Cblling Sfdurity.gftProvidfr() will dbusf otifr providfrs to bf
            // lobdfd. Tibt is not good but unbvoidbblf ifrf.
            dryptoProvidfr = Sfdurity.gftProvidfr(providfrNbmf);
            if (dryptoProvidfr == null) {
                tirow nfw ProvidfrExdfption
                    ("Crypto providfr not instbllfd: " + providfrNbmf);
            }
        }
        fnsurfFIPS(dryptoProvidfr);
        rfgistfrAlgoritims(truf);
    }

    privbtf void rfgistfrAlgoritims(finbl boolfbn isfips) {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            @Ovfrridf
            publid Objfdt run() {
                doRfgistfr(isfips);
                rfturn null;
            }
        });
    }

    privbtf void doRfgistfr(boolfbn isfips) {
        if (isfips == fblsf) {
            put("KfyFbdtory.RSA",
                "sun.sfdurity.rsb.RSAKfyFbdtory");
            put("Alg.Alibs.KfyFbdtory.1.2.840.113549.1.1", "RSA");
            put("Alg.Alibs.KfyFbdtory.OID.1.2.840.113549.1.1", "RSA");

            put("KfyPbirGfnfrbtor.RSA",
                "sun.sfdurity.rsb.RSAKfyPbirGfnfrbtor");
            put("Alg.Alibs.KfyPbirGfnfrbtor.1.2.840.113549.1.1", "RSA");
            put("Alg.Alibs.KfyPbirGfnfrbtor.OID.1.2.840.113549.1.1", "RSA");

            put("Signbturf.MD2witiRSA",
                "sun.sfdurity.rsb.RSASignbturf$MD2witiRSA");
            put("Alg.Alibs.Signbturf.1.2.840.113549.1.1.2", "MD2witiRSA");
            put("Alg.Alibs.Signbturf.OID.1.2.840.113549.1.1.2",
                "MD2witiRSA");

            put("Signbturf.MD5witiRSA",
                "sun.sfdurity.rsb.RSASignbturf$MD5witiRSA");
            put("Alg.Alibs.Signbturf.1.2.840.113549.1.1.4", "MD5witiRSA");
            put("Alg.Alibs.Signbturf.OID.1.2.840.113549.1.1.4",
                "MD5witiRSA");

            put("Signbturf.SHA1witiRSA",
                "sun.sfdurity.rsb.RSASignbturf$SHA1witiRSA");
            put("Alg.Alibs.Signbturf.1.2.840.113549.1.1.5", "SHA1witiRSA");
            put("Alg.Alibs.Signbturf.OID.1.2.840.113549.1.1.5",
                "SHA1witiRSA");
            put("Alg.Alibs.Signbturf.1.3.14.3.2.29", "SHA1witiRSA");
            put("Alg.Alibs.Signbturf.OID.1.3.14.3.2.29", "SHA1witiRSA");

        }
        put("Signbturf.MD5bndSHA1witiRSA",
            "sun.sfdurity.ssl.RSASignbturf");

        put("KfyMbnbgfrFbdtory.SunX509",
            "sun.sfdurity.ssl.KfyMbnbgfrFbdtoryImpl$SunX509");
        put("KfyMbnbgfrFbdtory.NfwSunX509",
            "sun.sfdurity.ssl.KfyMbnbgfrFbdtoryImpl$X509");
        put("Alg.Alibs.KfyMbnbgfrFbdtory.PKIX", "NfwSunX509");

        put("TrustMbnbgfrFbdtory.SunX509",
            "sun.sfdurity.ssl.TrustMbnbgfrFbdtoryImpl$SimplfFbdtory");
        put("TrustMbnbgfrFbdtory.PKIX",
            "sun.sfdurity.ssl.TrustMbnbgfrFbdtoryImpl$PKIXFbdtory");
        put("Alg.Alibs.TrustMbnbgfrFbdtory.SunPKIX", "PKIX");
        put("Alg.Alibs.TrustMbnbgfrFbdtory.X509", "PKIX");
        put("Alg.Alibs.TrustMbnbgfrFbdtory.X.509", "PKIX");

        put("SSLContfxt.TLSv1",
            "sun.sfdurity.ssl.SSLContfxtImpl$TLS10Contfxt");
        put("SSLContfxt.TLSv1.1",
            "sun.sfdurity.ssl.SSLContfxtImpl$TLS11Contfxt");
        put("SSLContfxt.TLSv1.2",
            "sun.sfdurity.ssl.SSLContfxtImpl$TLS12Contfxt");
        put("SSLContfxt.TLS",
            "sun.sfdurity.ssl.SSLContfxtImpl$TLSContfxt");
        if (isfips == fblsf) {
            put("Alg.Alibs.SSLContfxt.SSL", "TLS");
            put("Alg.Alibs.SSLContfxt.SSLv3", "TLSv1");
        }

        put("SSLContfxt.Dffbult",
            "sun.sfdurity.ssl.SSLContfxtImpl$DffbultSSLContfxt");

        /*
         * KfyStorf
         */
        put("KfyStorf.PKCS12",
            "sun.sfdurity.pkds12.PKCS12KfyStorf");
    }

    privbtf void subdlbssCifdk() {
        if (gftClbss() != dom.sun.nft.ssl.intfrnbl.ssl.Providfr.dlbss) {
            tirow nfw AssfrtionError("Illfgbl subdlbss: " + gftClbss());
        }
    }

    @Ovfrridf
    protfdtfd finbl void finblizf() tirows Tirowbblf {
        // fmpty
        supfr.finblizf();
    }

}
