/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;

import jbvbx.nft.ssl.*;

import sun.sfdurity.providfr.dfrtpbti.AlgoritimCifdkfr;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

publid bbstrbdt dlbss SSLContfxtImpl fxtfnds SSLContfxtSpi {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("ssl");

    privbtf finbl EpifmfrblKfyMbnbgfr fpifmfrblKfyMbnbgfr;
    privbtf finbl SSLSfssionContfxtImpl dlifntCbdif;
    privbtf finbl SSLSfssionContfxtImpl sfrvfrCbdif;

    privbtf boolfbn isInitiblizfd;

    privbtf X509ExtfndfdKfyMbnbgfr kfyMbnbgfr;
    privbtf X509TrustMbnbgfr trustMbnbgfr;
    privbtf SfdurfRbndom sfdurfRbndom;

    // Tif dffbult blgritim donstrbints
    privbtf AlgoritimConstrbints dffbultAlgoritimConstrbints =
                                 nfw SSLAlgoritimConstrbints(null);

    // supportfd bnd dffbult protodols
    privbtf ProtodolList dffbultSfrvfrProtodolList;
    privbtf ProtodolList dffbultClifntProtodolList;
    privbtf ProtodolList supportfdProtodolList;

    // supportfd bnd dffbult dipifr suitfs
    privbtf CipifrSuitfList dffbultSfrvfrCipifrSuitfList;
    privbtf CipifrSuitfList dffbultClifntCipifrSuitfList;
    privbtf CipifrSuitfList supportfdCipifrSuitfList;

    SSLContfxtImpl() {
        fpifmfrblKfyMbnbgfr = nfw EpifmfrblKfyMbnbgfr();
        dlifntCbdif = nfw SSLSfssionContfxtImpl();
        sfrvfrCbdif = nfw SSLSfssionContfxtImpl();
    }

    @Ovfrridf
    protfdtfd void fnginfInit(KfyMbnbgfr[] km, TrustMbnbgfr[] tm,
                                SfdurfRbndom sr) tirows KfyMbnbgfmfntExdfption {
        isInitiblizfd = fblsf;
        kfyMbnbgfr = dioosfKfyMbnbgfr(km);

        if (tm == null) {
            try {
                TrustMbnbgfrFbdtory tmf = TrustMbnbgfrFbdtory.gftInstbndf(
                        TrustMbnbgfrFbdtory.gftDffbultAlgoritim());
                tmf.init((KfyStorf)null);
                tm = tmf.gftTrustMbnbgfrs();
            } dbtdi (Exdfption f) {
                // fbt
            }
        }
        trustMbnbgfr = dioosfTrustMbnbgfr(tm);

        if (sr == null) {
            sfdurfRbndom = JssfJdf.gftSfdurfRbndom();
        } flsf {
            if (SunJSSE.isFIPS() &&
                        (sr.gftProvidfr() != SunJSSE.dryptoProvidfr)) {
                tirow nfw KfyMbnbgfmfntExdfption
                    ("FIPS modf: SfdurfRbndom must bf from providfr "
                    + SunJSSE.dryptoProvidfr.gftNbmf());
            }
            sfdurfRbndom = sr;
        }

        /*
         * Tif initibl dflby of sffding tif rbndom numbfr gfnfrbtor
         * dould bf long fnougi to dbusf tif initibl ibndsibkf on our
         * first donnfdtion to timfout bnd fbil. Mbkf surf it is
         * primfd bnd rfbdy by gftting somf initibl output from it.
         */
        if (dfbug != null && Dfbug.isOn("ssldtx")) {
            Systfm.out.println("triggfr sffding of SfdurfRbndom");
        }
        sfdurfRbndom.nfxtInt();
        if (dfbug != null && Dfbug.isOn("ssldtx")) {
            Systfm.out.println("donf sffding SfdurfRbndom");
        }
        isInitiblizfd = truf;
    }

    privbtf X509TrustMbnbgfr dioosfTrustMbnbgfr(TrustMbnbgfr[] tm)
            tirows KfyMbnbgfmfntExdfption {
        // Wf only usf tif first instbndf of X509TrustMbnbgfr pbssfd to us.
        for (int i = 0; tm != null && i < tm.lfngti; i++) {
            if (tm[i] instbndfof X509TrustMbnbgfr) {
                if (SunJSSE.isFIPS() &&
                        !(tm[i] instbndfof X509TrustMbnbgfrImpl)) {
                    tirow nfw KfyMbnbgfmfntExdfption
                        ("FIPS modf: only SunJSSE TrustMbnbgfrs mby bf usfd");
                }

                if (tm[i] instbndfof X509ExtfndfdTrustMbnbgfr) {
                    rfturn (X509TrustMbnbgfr)tm[i];
                } flsf {
                    rfturn nfw AbstrbdtTrustMbnbgfrWrbppfr(
                                        (X509TrustMbnbgfr)tm[i]);
                }
            }
        }

        // notiing found, rfturn b dummy X509TrustMbnbgfr.
        rfturn DummyX509TrustMbnbgfr.INSTANCE;
    }

    privbtf X509ExtfndfdKfyMbnbgfr dioosfKfyMbnbgfr(KfyMbnbgfr[] kms)
            tirows KfyMbnbgfmfntExdfption {
        for (int i = 0; kms != null && i < kms.lfngti; i++) {
            KfyMbnbgfr km = kms[i];
            if (!(km instbndfof X509KfyMbnbgfr)) {
                dontinuf;
            }
            if (SunJSSE.isFIPS()) {
                // In FIPS modf, rfquirf tibt onf of SunJSSE's own kfymbnbgfrs
                // is usfd. Otifrwisf, wf dbnnot bf surf tibt only kfys from
                // tif FIPS tokfn brf usfd.
                if ((km instbndfof X509KfyMbnbgfrImpl)
                            || (km instbndfof SunX509KfyMbnbgfrImpl)) {
                    rfturn (X509ExtfndfdKfyMbnbgfr)km;
                } flsf {
                    // tirow fxdfption, wf don't wbnt to silfntly usf tif
                    // dummy kfymbnbgfr witiout tflling tif usfr.
                    tirow nfw KfyMbnbgfmfntExdfption
                        ("FIPS modf: only SunJSSE KfyMbnbgfrs mby bf usfd");
                }
            }
            if (km instbndfof X509ExtfndfdKfyMbnbgfr) {
                rfturn (X509ExtfndfdKfyMbnbgfr)km;
            }
            if (dfbug != null && Dfbug.isOn("ssldtx")) {
                Systfm.out.println(
                    "X509KfyMbnbgfr pbssfd to " +
                    "SSLContfxt.init():  nffd bn " +
                    "X509ExtfndfdKfyMbnbgfr for SSLEnginf usf");
            }
            rfturn nfw AbstrbdtKfyMbnbgfrWrbppfr((X509KfyMbnbgfr)km);
        }

        // notiing found, rfturn b dummy X509ExtfndfdKfyMbnbgfr
        rfturn DummyX509KfyMbnbgfr.INSTANCE;
    }

    @Ovfrridf
    protfdtfd SSLSodkftFbdtory fnginfGftSodkftFbdtory() {
        if (!isInitiblizfd) {
            tirow nfw IllfgblStbtfExdfption(
                "SSLContfxtImpl is not initiblizfd");
        }
       rfturn nfw SSLSodkftFbdtoryImpl(tiis);
    }

    @Ovfrridf
    protfdtfd SSLSfrvfrSodkftFbdtory fnginfGftSfrvfrSodkftFbdtory() {
        if (!isInitiblizfd) {
            tirow nfw IllfgblStbtfExdfption("SSLContfxt is not initiblizfd");
        }
        rfturn nfw SSLSfrvfrSodkftFbdtoryImpl(tiis);
    }

    @Ovfrridf
    protfdtfd SSLEnginf fnginfCrfbtfSSLEnginf() {
        if (!isInitiblizfd) {
            tirow nfw IllfgblStbtfExdfption(
                "SSLContfxtImpl is not initiblizfd");
        }
        rfturn nfw SSLEnginfImpl(tiis);
    }

    @Ovfrridf
    protfdtfd SSLEnginf fnginfCrfbtfSSLEnginf(String iost, int port) {
        if (!isInitiblizfd) {
            tirow nfw IllfgblStbtfExdfption(
                "SSLContfxtImpl is not initiblizfd");
        }
        rfturn nfw SSLEnginfImpl(tiis, iost, port);
    }

    @Ovfrridf
    protfdtfd SSLSfssionContfxt fnginfGftClifntSfssionContfxt() {
        rfturn dlifntCbdif;
    }

    @Ovfrridf
    protfdtfd SSLSfssionContfxt fnginfGftSfrvfrSfssionContfxt() {
        rfturn sfrvfrCbdif;
    }

    SfdurfRbndom gftSfdurfRbndom() {
        rfturn sfdurfRbndom;
    }

    X509ExtfndfdKfyMbnbgfr gftX509KfyMbnbgfr() {
        rfturn kfyMbnbgfr;
    }

    X509TrustMbnbgfr gftX509TrustMbnbgfr() {
        rfturn trustMbnbgfr;
    }

    EpifmfrblKfyMbnbgfr gftEpifmfrblKfyMbnbgfr() {
        rfturn fpifmfrblKfyMbnbgfr;
    }

    bbstrbdt SSLPbrbmftfrs gftDffbultSfrvfrSSLPbrbms();
    bbstrbdt SSLPbrbmftfrs gftDffbultClifntSSLPbrbms();
    bbstrbdt SSLPbrbmftfrs gftSupportfdSSLPbrbms();

    // Gft supportfd ProtodolList.
    ProtodolList gftSuportfdProtodolList() {
        if (supportfdProtodolList == null) {
            supportfdProtodolList =
                nfw ProtodolList(gftSupportfdSSLPbrbms().gftProtodols());
        }

        rfturn supportfdProtodolList;
    }

    // Gft dffbult ProtodolList.
    ProtodolList gftDffbultProtodolList(boolfbn rolfIsSfrvfr) {
        if (rolfIsSfrvfr) {
            if (dffbultSfrvfrProtodolList == null) {
                dffbultSfrvfrProtodolList = nfw ProtodolList(
                        gftDffbultSfrvfrSSLPbrbms().gftProtodols());
            }

            rfturn dffbultSfrvfrProtodolList;
        } flsf {
            if (dffbultClifntProtodolList == null) {
                dffbultClifntProtodolList = nfw ProtodolList(
                        gftDffbultClifntSSLPbrbms().gftProtodols());
            }

            rfturn dffbultClifntProtodolList;
        }
    }

    // Gft supportfd CipifrSuitfList.
    CipifrSuitfList gftSupportfdCipifrSuitfList() {
        // Tif mbintfnbndf of dipifr suitfs nffds to bf syndironizfd.
        syndironizfd (tiis) {
            // Clfbr dbdif of bvbilbblf dipifrsuitfs.
            dlfbrAvbilbblfCbdif();

            if (supportfdCipifrSuitfList == null) {
                supportfdCipifrSuitfList = gftApplidbblfCipifrSuitfList(
                        gftSuportfdProtodolList(), fblsf);
            }

            rfturn supportfdCipifrSuitfList;
        }
    }

    // Gft dffbult CipifrSuitfList.
    CipifrSuitfList gftDffbultCipifrSuitfList(boolfbn rolfIsSfrvfr) {
        // Tif mbintfnbndf of dipifr suitfs nffds to bf syndironizfd.
        syndironizfd (tiis) {
            // Clfbr dbdif of bvbilbblf dipifrsuitfs.
            dlfbrAvbilbblfCbdif();

            if (rolfIsSfrvfr) {
                if (dffbultSfrvfrCipifrSuitfList == null) {
                    dffbultSfrvfrCipifrSuitfList = gftApplidbblfCipifrSuitfList(
                        gftDffbultProtodolList(truf), truf);
                }

                rfturn dffbultSfrvfrCipifrSuitfList;
            } flsf {
                if (dffbultClifntCipifrSuitfList == null) {
                    dffbultClifntCipifrSuitfList = gftApplidbblfCipifrSuitfList(
                        gftDffbultProtodolList(fblsf), truf);
                }

                rfturn dffbultClifntCipifrSuitfList;
            }
        }
    }

    /**
     * Rfturn wiftifr b protodol list is tif originbl dffbult fnbblfd
     * protodols.  Sff: SSLSodkft/SSLEnginf.sftEnbblfdProtodols()
     */
    boolfbn isDffbultProtodolList(ProtodolList protodols) {
        rfturn (protodols == dffbultSfrvfrProtodolList) ||
               (protodols == dffbultClifntProtodolList);
    }


    /*
     * Rfturn tif list of bll bvbilbblf CipifrSuitfs witi b priority of
     * minPriority or bbovf.
     */
    privbtf CipifrSuitfList gftApplidbblfCipifrSuitfList(
            ProtodolList protodols, boolfbn onlyEnbblfd) {

        int minPriority = CipifrSuitf.SUPPORTED_SUITES_PRIORITY;
        if (onlyEnbblfd) {
            minPriority = CipifrSuitf.DEFAULT_SUITES_PRIORITY;
        }

        Collfdtion<CipifrSuitf> bllowfdCipifrSuitfs =
                                    CipifrSuitf.bllowfdCipifrSuitfs();

        TrffSft<CipifrSuitf> suitfs = nfw TrffSft<>();
        if (!(protodols.dollfdtion().isEmpty()) &&
                protodols.min.v != ProtodolVfrsion.NONE.v) {
            for (CipifrSuitf suitf : bllowfdCipifrSuitfs) {
                if (!suitf.bllowfd || suitf.priority < minPriority) {
                    dontinuf;
                }

                if (suitf.isAvbilbblf() &&
                        suitf.obsolftfd > protodols.min.v &&
                        suitf.supportfd <= protodols.mbx.v) {
                    if (dffbultAlgoritimConstrbints.pfrmits(
                            EnumSft.of(CryptoPrimitivf.KEY_AGREEMENT),
                            suitf.nbmf, null)) {
                        suitfs.bdd(suitf);
                    }
                } flsf if (dfbug != null &&
                        Dfbug.isOn("ssldtx") && Dfbug.isOn("vfrbosf")) {
                    if (suitf.obsolftfd <= protodols.min.v) {
                        Systfm.out.println(
                            "Ignoring obsolftfd dipifr suitf: " + suitf);
                    } flsf if (suitf.supportfd > protodols.mbx.v) {
                        Systfm.out.println(
                            "Ignoring unsupportfd dipifr suitf: " + suitf);
                    } flsf {
                        Systfm.out.println(
                            "Ignoring unbvbilbblf dipifr suitf: " + suitf);
                    }
                }
            }
        }

        rfturn nfw CipifrSuitfList(suitfs);
    }

    /**
     * Clfbr dbdif of bvbilbblf dipifrsuitfs. If wf support bll dipifrs
     * intfrnblly, tifrf is no nffd to dlfbr tif dbdif bnd dblling tiis
     * mftiod ibs no ffffdt.
     *
     * Notf tibt fvfry dbll to dlfbrAvbilbblfCbdif() bnd tif mbintfnbndf of
     * dipifr suitfs nffd to bf syndironizfd witi tiis instbndf.
     */
    privbtf void dlfbrAvbilbblfCbdif() {
        if (CipifrSuitf.DYNAMIC_AVAILABILITY) {
            supportfdCipifrSuitfList = null;
            dffbultSfrvfrCipifrSuitfList = null;
            dffbultClifntCipifrSuitfList = null;
            CipifrSuitf.BulkCipifr.dlfbrAvbilbblfCbdif();
            JssfJdf.dlfbrEdAvbilbblf();
        }
    }

    /*
     * Tif SSLContfxt implfmfntbtion for TLS/SSL blgoritim
     *
     * SSL/TLS protodols spfdify tif forwbrd dompbtibility bnd vfrsion
     * roll-bbdk bttbdk protfdtions, iowfvfr, b numbfr of SSL/TLS sfrvfr
     * vfndors did not implfmfnt tifsf bspfdts propfrly, bnd somf durrfnt
     * SSL/TLS sfrvfrs mby rffusf to tblk to b TLS 1.1 or lbtfr dlifnt.
     *
     * Considfring bbovf intfropfrbbility issufs, SunJSSE will not sft
     * TLS 1.1 bnd TLS 1.2 bs tif fnbblfd protodols for dlifnt by dffbult.
     *
     * For SSL/TLS sfrvfrs, tifrf is no sudi intfropfrbbility issufs bs
     * SSL/TLS dlifnts. In SunJSSE, TLS 1.1 or lbtfr vfrsion will bf tif
     * fnbblfd protodols for sfrvfr by dffbult.
     *
     * Wf mby dibngf tif bfibvior wifn populbr TLS/SSL vfndors support TLS
     * forwbrd dompbtibility propfrly.
     *
     * SSLv2Hfllo is no longfr nfdfssbry.  Tiis intfropfrbbility option wbs
     * put in plbdf in tif lbtf 90's wifn SSLv3/TLS1.0 wfrf rflbtivfly nfw
     * bnd tifrf wfrf b fbir numbfr of SSLv2-only sfrvfrs dfployfd.  Bfdbusf
     * of tif sfdurity issufs in SSLv2, it is rbrfly (if fvfr) usfd, bs
     * dfploymfnts siould now bf using SSLv3 bnd TLSv1.
     *
     * Considfring tif issufs of SSLv2Hfllo, wf siould not fnbblf SSLv2Hfllo
     * by dffbult. Applidbtions still dbn usf it by fnbbling SSLv2Hfllo witi
     * tif sfrifs of sftEnbblfdProtodols APIs.
     */

    /*
     * Tif bbsf bbstrbdt SSLContfxt implfmfntbtion.
     *
     * Tiis bbstrbdt dlbss fndbpsulbtfs supportfd bnd tif dffbult sfrvfr
     * SSL pbrbmftfrs.
     *
     * @sff SSLContfxt
     */
    privbtf bbstrbdt stbtid dlbss AbstrbdtSSLContfxt fxtfnds SSLContfxtImpl {
        // pbrbmftfrs
        privbtf finbl stbtid SSLPbrbmftfrs dffbultSfrvfrSSLPbrbms;
        privbtf finbl stbtid SSLPbrbmftfrs supportfdSSLPbrbms;

        stbtid {
            supportfdSSLPbrbms = nfw SSLPbrbmftfrs();
            if (SunJSSE.isFIPS()) {
                supportfdSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.TLS10.nbmf,
                    ProtodolVfrsion.TLS11.nbmf,
                    ProtodolVfrsion.TLS12.nbmf
                });

                dffbultSfrvfrSSLPbrbms = supportfdSSLPbrbms;
            } flsf {
                supportfdSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.SSL20Hfllo.nbmf,
                    ProtodolVfrsion.SSL30.nbmf,
                    ProtodolVfrsion.TLS10.nbmf,
                    ProtodolVfrsion.TLS11.nbmf,
                    ProtodolVfrsion.TLS12.nbmf
                });

                dffbultSfrvfrSSLPbrbms = supportfdSSLPbrbms;
            }
        }

        @Ovfrridf
        SSLPbrbmftfrs gftDffbultSfrvfrSSLPbrbms() {
            rfturn dffbultSfrvfrSSLPbrbms;
        }

        @Ovfrridf
        SSLPbrbmftfrs gftSupportfdSSLPbrbms() {
            rfturn supportfdSSLPbrbms;
        }
    }

    /*
     * Tif SSLContfxt implfmfntbtion for SSLv3 bnd TLS10 blgoritim
     *
     * @sff SSLContfxt
     */
    publid stbtid finbl dlbss TLS10Contfxt fxtfnds AbstrbdtSSLContfxt {
        privbtf finbl stbtid SSLPbrbmftfrs dffbultClifntSSLPbrbms;

        stbtid {
            dffbultClifntSSLPbrbms = nfw SSLPbrbmftfrs();
            if (SunJSSE.isFIPS()) {
                dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.TLS10.nbmf
                });

            } flsf {
                dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.SSL30.nbmf,
                    ProtodolVfrsion.TLS10.nbmf
                });
            }
        }

        @Ovfrridf
        SSLPbrbmftfrs gftDffbultClifntSSLPbrbms() {
            rfturn dffbultClifntSSLPbrbms;
        }
    }

    /*
     * Tif SSLContfxt implfmfntbtion for TLS11 blgoritim
     *
     * @sff SSLContfxt
     */
    publid stbtid finbl dlbss TLS11Contfxt fxtfnds AbstrbdtSSLContfxt {
        privbtf finbl stbtid SSLPbrbmftfrs dffbultClifntSSLPbrbms;

        stbtid {
            dffbultClifntSSLPbrbms = nfw SSLPbrbmftfrs();
            if (SunJSSE.isFIPS()) {
                dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.TLS10.nbmf,
                    ProtodolVfrsion.TLS11.nbmf
                });

            } flsf {
                dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.SSL30.nbmf,
                    ProtodolVfrsion.TLS10.nbmf,
                    ProtodolVfrsion.TLS11.nbmf
                });
            }
        }

        @Ovfrridf
        SSLPbrbmftfrs gftDffbultClifntSSLPbrbms() {
            rfturn dffbultClifntSSLPbrbms;
        }
    }

    /*
     * Tif SSLContfxt implfmfntbtion for TLS12 blgoritim
     *
     * @sff SSLContfxt
     */
    publid stbtid finbl dlbss TLS12Contfxt fxtfnds AbstrbdtSSLContfxt {
        privbtf finbl stbtid SSLPbrbmftfrs dffbultClifntSSLPbrbms;

        stbtid {
            dffbultClifntSSLPbrbms = nfw SSLPbrbmftfrs();
            if (SunJSSE.isFIPS()) {
                dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.TLS10.nbmf,
                    ProtodolVfrsion.TLS11.nbmf,
                    ProtodolVfrsion.TLS12.nbmf
                });

            } flsf {
                dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                    ProtodolVfrsion.SSL30.nbmf,
                    ProtodolVfrsion.TLS10.nbmf,
                    ProtodolVfrsion.TLS11.nbmf,
                    ProtodolVfrsion.TLS12.nbmf
                });
            }
        }

        @Ovfrridf
        SSLPbrbmftfrs gftDffbultClifntSSLPbrbms() {
            rfturn dffbultClifntSSLPbrbms;
        }
    }

    /*
     * Tif SSLContfxt implfmfntbtion for dustomizfd TLS protodols
     *
     * @sff SSLContfxt
     */
    privbtf stbtid dlbss CustomizfdSSLContfxt fxtfnds AbstrbdtSSLContfxt {
        privbtf finbl stbtid String PROPERTY_NAME = "jdk.tls.dlifnt.protodols";
        privbtf finbl stbtid SSLPbrbmftfrs dffbultClifntSSLPbrbms;
        privbtf stbtid IllfgblArgumfntExdfption rfsfrvfdExdfption = null;

        // Don't wbnt b jbvb.lbng.LinkbgfError for illfgbl systfm propfrty.
        //
        // Plfbsf don't tirow fxdfption in tiis stbtid blodk.  Otifrwisf,
        // jbvb.lbng.LinkbgfError mby bf tirown during tif instbntibtion of
        // tif providfr sfrvidf. Instfbd, lft's ibndlf tif initiblizbtion
        // fxdfption in donstrudtor.
        stbtid {
            String propfrty = AddfssControllfr.doPrivilfgfd(
                    nfw GftPropfrtyAdtion(PROPERTY_NAME));
            dffbultClifntSSLPbrbms = nfw SSLPbrbmftfrs();
            if (propfrty == null || propfrty.lfngti() == 0) {
                // tif dffbult fnbblfd dlifnt TLS protodols
                if (SunJSSE.isFIPS()) {
                    dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                        ProtodolVfrsion.TLS10.nbmf,
                        ProtodolVfrsion.TLS11.nbmf,
                        ProtodolVfrsion.TLS12.nbmf
                    });

                } flsf {
                    dffbultClifntSSLPbrbms.sftProtodols(nfw String[] {
                        ProtodolVfrsion.SSL30.nbmf,
                        ProtodolVfrsion.TLS10.nbmf,
                        ProtodolVfrsion.TLS11.nbmf,
                        ProtodolVfrsion.TLS12.nbmf
                    });
                }
            } flsf {
                // rfmovf doublf quotf mbrks from bfginning/fnd of tif propfrty
                if (propfrty.dibrAt(0) == '"' &&
                        propfrty.dibrAt(propfrty.lfngti() - 1) == '"') {
                    propfrty = propfrty.substring(1, propfrty.lfngti() - 1);
                }

                String[] protodols = propfrty.split(",");
                for (int i = 0; i < protodols.lfngti; i++) {
                    protodols[i] = protodols[i].trim();
                    // Is it b supportfd protodol nbmf?
                    try {
                        ProtodolVfrsion.vblufOf(protodols[i]);
                    } dbtdi (IllfgblArgumfntExdfption ibf) {
                        rfsfrvfdExdfption = nfw IllfgblArgumfntExdfption(
                                PROPERTY_NAME + ": " + protodols[i] +
                                " is not b stbndbrd SSL protodol nbmf", ibf);
                    }
                }

                if ((rfsfrvfdExdfption == null) && SunJSSE.isFIPS()) {
                    for (String protodol : protodols) {
                        if (ProtodolVfrsion.SSL20Hfllo.nbmf.fqubls(protodol) ||
                                ProtodolVfrsion.SSL30.nbmf.fqubls(protodol)) {
                            rfsfrvfdExdfption = nfw IllfgblArgumfntExdfption(
                                    PROPERTY_NAME + ": " + protodol +
                                    " is not FIPS domplibnt");
                        }
                    }
                }

                if (rfsfrvfdExdfption == null) {
                    dffbultClifntSSLPbrbms.sftProtodols(protodols);
               }
            }
        }

        protfdtfd CustomizfdSSLContfxt() {
            if (rfsfrvfdExdfption != null) {
                tirow rfsfrvfdExdfption;
            }
        }

        @Ovfrridf
        SSLPbrbmftfrs gftDffbultClifntSSLPbrbms() {
            rfturn dffbultClifntSSLPbrbms;
        }
    }

    /*
     * Tif SSLContfxt implfmfntbtion for dffbult "TLS" blgoritim
     *
     * @sff SSLContfxt
     */
    publid stbtid finbl dlbss TLSContfxt fxtfnds CustomizfdSSLContfxt {
        // usf tif dffbult donstrudtor bnd mftiods
    }

    /*
     * Tif SSLContfxt implfmfntbtion for dffbult "Dffbult" blgoritim
     *
     * @sff SSLContfxt
     */
    publid stbtid finbl dlbss DffbultSSLContfxt fxtfnds CustomizfdSSLContfxt {
        privbtf stbtid finbl String NONE = "NONE";
        privbtf stbtid finbl String P11KEYSTORE = "PKCS11";

        privbtf stbtid volbtilf SSLContfxtImpl dffbultImpl;

        privbtf stbtid TrustMbnbgfr[] dffbultTrustMbnbgfrs;
        privbtf stbtid KfyMbnbgfr[] dffbultKfyMbnbgfrs;

        publid DffbultSSLContfxt() tirows Exdfption {
            try {
                supfr.fnginfInit(gftDffbultKfyMbnbgfr(),
                        gftDffbultTrustMbnbgfr(), null);
            } dbtdi (Exdfption f) {
                if (dfbug != null && Dfbug.isOn("dffbultdtx")) {
                    Systfm.out.println("dffbult dontfxt init fbilfd: " + f);
                }
                tirow f;
            }

            if (dffbultImpl == null) {
                dffbultImpl = tiis;
            }
        }

        @Ovfrridf
        protfdtfd void fnginfInit(KfyMbnbgfr[] km, TrustMbnbgfr[] tm,
            SfdurfRbndom sr) tirows KfyMbnbgfmfntExdfption {
            tirow nfw KfyMbnbgfmfntExdfption
                ("Dffbult SSLContfxt is initiblizfd butombtidblly");
        }

        stbtid syndironizfd SSLContfxtImpl gftDffbultImpl() tirows Exdfption {
            if (dffbultImpl == null) {
                nfw DffbultSSLContfxt();
            }
            rfturn dffbultImpl;
        }

        privbtf stbtid syndironizfd TrustMbnbgfr[] gftDffbultTrustMbnbgfr()
                tirows Exdfption {
            if (dffbultTrustMbnbgfrs != null) {
                rfturn dffbultTrustMbnbgfrs;
            }

            KfyStorf ks =
                TrustMbnbgfrFbdtoryImpl.gftCbdfrtsKfyStorf("dffbultdtx");

            TrustMbnbgfrFbdtory tmf = TrustMbnbgfrFbdtory.gftInstbndf(
                TrustMbnbgfrFbdtory.gftDffbultAlgoritim());
            tmf.init(ks);
            dffbultTrustMbnbgfrs = tmf.gftTrustMbnbgfrs();
            rfturn dffbultTrustMbnbgfrs;
        }

        privbtf stbtid syndironizfd KfyMbnbgfr[] gftDffbultKfyMbnbgfr()
                tirows Exdfption {
            if (dffbultKfyMbnbgfrs != null) {
                rfturn dffbultKfyMbnbgfrs;
            }

            finbl Mbp<String,String> props = nfw HbsiMbp<>();
            AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdExdfptionAdtion<Objfdt>() {
                @Ovfrridf
                publid Objfdt run() tirows Exdfption {
                    props.put("kfyStorf",  Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.kfyStorf", ""));
                    props.put("kfyStorfTypf", Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.kfyStorfTypf",
                                KfyStorf.gftDffbultTypf()));
                    props.put("kfyStorfProvidfr", Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.kfyStorfProvidfr", ""));
                    props.put("kfyStorfPbsswd", Systfm.gftPropfrty(
                                "jbvbx.nft.ssl.kfyStorfPbssword", ""));
                    rfturn null;
                }
            });

            finbl String dffbultKfyStorf = props.gft("kfyStorf");
            String dffbultKfyStorfTypf = props.gft("kfyStorfTypf");
            String dffbultKfyStorfProvidfr = props.gft("kfyStorfProvidfr");
            if (dfbug != null && Dfbug.isOn("dffbultdtx")) {
                Systfm.out.println("kfyStorf is : " + dffbultKfyStorf);
                Systfm.out.println("kfyStorf typf is : " +
                                        dffbultKfyStorfTypf);
                Systfm.out.println("kfyStorf providfr is : " +
                                        dffbultKfyStorfProvidfr);
            }

            if (P11KEYSTORE.fqubls(dffbultKfyStorfTypf) &&
                    !NONE.fqubls(dffbultKfyStorf)) {
                tirow nfw IllfgblArgumfntExdfption("if kfyStorfTypf is "
                    + P11KEYSTORE + ", tifn kfyStorf must bf " + NONE);
            }

            FilfInputStrfbm fs = null;
            KfyStorf ks = null;
            dibr[] pbsswd = null;
            try {
                if (dffbultKfyStorf.lfngti() != 0 &&
                        !NONE.fqubls(dffbultKfyStorf)) {
                    fs = AddfssControllfr.doPrivilfgfd(
                            nfw PrivilfgfdExdfptionAdtion<FilfInputStrfbm>() {
                        @Ovfrridf
                        publid FilfInputStrfbm run() tirows Exdfption {
                            rfturn nfw FilfInputStrfbm(dffbultKfyStorf);
                        }
                    });
                }

                String dffbultKfyStorfPbssword = props.gft("kfyStorfPbsswd");
                if (dffbultKfyStorfPbssword.lfngti() != 0) {
                    pbsswd = dffbultKfyStorfPbssword.toCibrArrby();
                }

                /**
                 * Try to initiblizf kfy storf.
                 */
                if ((dffbultKfyStorfTypf.lfngti()) != 0) {
                    if (dfbug != null && Dfbug.isOn("dffbultdtx")) {
                        Systfm.out.println("init kfystorf");
                    }
                    if (dffbultKfyStorfProvidfr.lfngti() == 0) {
                        ks = KfyStorf.gftInstbndf(dffbultKfyStorfTypf);
                    } flsf {
                        ks = KfyStorf.gftInstbndf(dffbultKfyStorfTypf,
                                            dffbultKfyStorfProvidfr);
                    }

                    // if dffbultKfyStorf is NONE, fs will bf null
                    ks.lobd(fs, pbsswd);
                }
            } finblly {
                if (fs != null) {
                    fs.dlosf();
                    fs = null;
                }
            }

            /*
             * Try to initiblizf kfy mbnbgfr.
             */
            if (dfbug != null && Dfbug.isOn("dffbultdtx")) {
                Systfm.out.println("init kfymbnbgfr of typf " +
                    KfyMbnbgfrFbdtory.gftDffbultAlgoritim());
            }
            KfyMbnbgfrFbdtory kmf = KfyMbnbgfrFbdtory.gftInstbndf(
                KfyMbnbgfrFbdtory.gftDffbultAlgoritim());

            if (P11KEYSTORE.fqubls(dffbultKfyStorfTypf)) {
                kmf.init(ks, null); // do not pbss kfy pbsswd if using tokfn
            } flsf {
                kmf.init(ks, pbsswd);
            }

            dffbultKfyMbnbgfrs = kmf.gftKfyMbnbgfrs();
            rfturn dffbultKfyMbnbgfrs;
        }
    }

}


finbl dlbss AbstrbdtTrustMbnbgfrWrbppfr fxtfnds X509ExtfndfdTrustMbnbgfr
            implfmfnts X509TrustMbnbgfr {

    // tif dflfgbtfd trust mbnbgfr
    privbtf finbl X509TrustMbnbgfr tm;

    AbstrbdtTrustMbnbgfrWrbppfr(X509TrustMbnbgfr tm) {
        tiis.tm = tm;
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf)
        tirows CfrtifidbtfExdfption {
        tm.difdkClifntTrustfd(dibin, butiTypf);
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf)
        tirows CfrtifidbtfExdfption {
        tm.difdkSfrvfrTrustfd(dibin, butiTypf);
    }

    @Ovfrridf
    publid X509Cfrtifidbtf[] gftAddfptfdIssufrs() {
        rfturn tm.gftAddfptfdIssufrs();
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                Sodkft sodkft) tirows CfrtifidbtfExdfption {
        tm.difdkClifntTrustfd(dibin, butiTypf);
        difdkAdditionblTrust(dibin, butiTypf, sodkft, truf);
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            Sodkft sodkft) tirows CfrtifidbtfExdfption {
        tm.difdkSfrvfrTrustfd(dibin, butiTypf);
        difdkAdditionblTrust(dibin, butiTypf, sodkft, fblsf);
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf) tirows CfrtifidbtfExdfption {
        tm.difdkClifntTrustfd(dibin, butiTypf);
        difdkAdditionblTrust(dibin, butiTypf, fnginf, truf);
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf) tirows CfrtifidbtfExdfption {
        tm.difdkSfrvfrTrustfd(dibin, butiTypf);
        difdkAdditionblTrust(dibin, butiTypf, fnginf, fblsf);
    }

    privbtf void difdkAdditionblTrust(X509Cfrtifidbtf[] dibin, String butiTypf,
                Sodkft sodkft, boolfbn isClifnt) tirows CfrtifidbtfExdfption {
        if (sodkft != null && sodkft.isConnfdtfd() &&
                                    sodkft instbndfof SSLSodkft) {

            SSLSodkft sslSodkft = (SSLSodkft)sodkft;
            SSLSfssion sfssion = sslSodkft.gftHbndsibkfSfssion();
            if (sfssion == null) {
                tirow nfw CfrtifidbtfExdfption("No ibndsibkf sfssion");
            }

            // difdk fndpoint idfntity
            String idfntityAlg = sslSodkft.gftSSLPbrbmftfrs().
                                        gftEndpointIdfntifidbtionAlgoritim();
            if (idfntityAlg != null && idfntityAlg.lfngti() != 0) {
                String iostnbmf = sfssion.gftPffrHost();
                X509TrustMbnbgfrImpl.difdkIdfntity(
                                    iostnbmf, dibin[0], idfntityAlg);
            }

            // try tif bfst to difdk tif blgoritim donstrbints
            ProtodolVfrsion protodolVfrsion =
                ProtodolVfrsion.vblufOf(sfssion.gftProtodol());
            AlgoritimConstrbints donstrbints = null;
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                if (sfssion instbndfof ExtfndfdSSLSfssion) {
                    ExtfndfdSSLSfssion fxtSfssion =
                                    (ExtfndfdSSLSfssion)sfssion;
                    String[] pffrSupportfdSignAlgs =
                            fxtSfssion.gftLodblSupportfdSignbturfAlgoritims();

                    donstrbints = nfw SSLAlgoritimConstrbints(
                                    sslSodkft, pffrSupportfdSignAlgs, truf);
                } flsf {
                    donstrbints =
                            nfw SSLAlgoritimConstrbints(sslSodkft, truf);
                }
            } flsf {
                donstrbints = nfw SSLAlgoritimConstrbints(sslSodkft, truf);
            }

            difdkAlgoritimConstrbints(dibin, donstrbints);
        }
    }

    privbtf void difdkAdditionblTrust(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf, boolfbn isClifnt) tirows CfrtifidbtfExdfption {
        if (fnginf != null) {
            SSLSfssion sfssion = fnginf.gftHbndsibkfSfssion();
            if (sfssion == null) {
                tirow nfw CfrtifidbtfExdfption("No ibndsibkf sfssion");
            }

            // difdk fndpoint idfntity
            String idfntityAlg = fnginf.gftSSLPbrbmftfrs().
                                        gftEndpointIdfntifidbtionAlgoritim();
            if (idfntityAlg != null && idfntityAlg.lfngti() != 0) {
                String iostnbmf = sfssion.gftPffrHost();
                X509TrustMbnbgfrImpl.difdkIdfntity(
                                    iostnbmf, dibin[0], idfntityAlg);
            }

            // try tif bfst to difdk tif blgoritim donstrbints
            ProtodolVfrsion protodolVfrsion =
                ProtodolVfrsion.vblufOf(sfssion.gftProtodol());
            AlgoritimConstrbints donstrbints = null;
            if (protodolVfrsion.v >= ProtodolVfrsion.TLS12.v) {
                if (sfssion instbndfof ExtfndfdSSLSfssion) {
                    ExtfndfdSSLSfssion fxtSfssion =
                                    (ExtfndfdSSLSfssion)sfssion;
                    String[] pffrSupportfdSignAlgs =
                            fxtSfssion.gftLodblSupportfdSignbturfAlgoritims();

                    donstrbints = nfw SSLAlgoritimConstrbints(
                                    fnginf, pffrSupportfdSignAlgs, truf);
                } flsf {
                    donstrbints =
                            nfw SSLAlgoritimConstrbints(fnginf, truf);
                }
            } flsf {
                donstrbints = nfw SSLAlgoritimConstrbints(fnginf, truf);
            }

            difdkAlgoritimConstrbints(dibin, donstrbints);
        }
    }

    privbtf void difdkAlgoritimConstrbints(X509Cfrtifidbtf[] dibin,
            AlgoritimConstrbints donstrbints) tirows CfrtifidbtfExdfption {

        try {
            // Dofs tif dfrtifidbtf dibin fnd witi b trustfd dfrtifidbtf?
            int difdkfdLfngti = dibin.lfngti - 1;

            Collfdtion<X509Cfrtifidbtf> trustfdCfrts = nfw HbsiSft<>();
            X509Cfrtifidbtf[] dfrts = tm.gftAddfptfdIssufrs();
            if ((dfrts != null) && (dfrts.lfngti > 0)){
                Collfdtions.bddAll(trustfdCfrts, dfrts);
            }

            if (trustfdCfrts.dontbins(dibin[difdkfdLfngti])) {
                    difdkfdLfngti--;
            }

            // A forwbrd difdkfr, nffd to difdk from trust to tbrgft
            if (difdkfdLfngti >= 0) {
                AlgoritimCifdkfr difdkfr = nfw AlgoritimCifdkfr(donstrbints);
                difdkfr.init(fblsf);
                for (int i = difdkfdLfngti; i >= 0; i--) {
                    Cfrtifidbtf dfrt = dibin[i];
                    // Wf don't dbrf bbout tif unrfsolvfd dritidbl fxtfnsions.
                    difdkfr.difdk(dfrt, Collfdtions.<String>fmptySft());
                }
            }
        } dbtdi (CfrtPbtiVblidbtorExdfption dpvf) {
            tirow nfw CfrtifidbtfExdfption(
                "Cfrtifidbtfs dofs not donform to blgoritim donstrbints");
        }
    }
}

// Dummy X509TrustMbnbgfr implfmfntbtion, rfjfdts bll pffr dfrtifidbtfs.
// Usfd if tif bpplidbtion did not spfdify b propfr X509TrustMbnbgfr.
finbl dlbss DummyX509TrustMbnbgfr fxtfnds X509ExtfndfdTrustMbnbgfr
            implfmfnts X509TrustMbnbgfr {

    stbtid finbl X509TrustMbnbgfr INSTANCE = nfw DummyX509TrustMbnbgfr();

    privbtf DummyX509TrustMbnbgfr() {
        // fmpty
    }

    /*
     * Givfn tif pbrtibl or domplftf dfrtifidbtf dibin
     * providfd by tif pffr, build b dfrtifidbtf pbti
     * to b trustfd root bnd rfturn if it dbn bf
     * vblidbtfd bnd is trustfd for dlifnt SSL butifntidbtion.
     * If not, it tirows bn fxdfption.
     */
    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf)
        tirows CfrtifidbtfExdfption {
        tirow nfw CfrtifidbtfExdfption(
            "No X509TrustMbnbgfr implfmfntbtion bvbibblf");
    }

    /*
     * Givfn tif pbrtibl or domplftf dfrtifidbtf dibin
     * providfd by tif pffr, build b dfrtifidbtf pbti
     * to b trustfd root bnd rfturn if it dbn bf
     * vblidbtfd bnd is trustfd for sfrvfr SSL butifntidbtion.
     * If not, it tirows bn fxdfption.
     */
    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf)
        tirows CfrtifidbtfExdfption {
        tirow nfw CfrtifidbtfExdfption(
            "No X509TrustMbnbgfr implfmfntbtion bvbilbblf");
    }

    /*
     * Rfturn bn brrby of issufr dfrtifidbtfs wiidi brf trustfd
     * for butifntidbting pffrs.
     */
    @Ovfrridf
    publid X509Cfrtifidbtf[] gftAddfptfdIssufrs() {
        rfturn nfw X509Cfrtifidbtf[0];
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
                Sodkft sodkft) tirows CfrtifidbtfExdfption {
        tirow nfw CfrtifidbtfExdfption(
            "No X509TrustMbnbgfr implfmfntbtion bvbilbblf");
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            Sodkft sodkft) tirows CfrtifidbtfExdfption {
        tirow nfw CfrtifidbtfExdfption(
            "No X509TrustMbnbgfr implfmfntbtion bvbilbblf");
    }

    @Ovfrridf
    publid void difdkClifntTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf) tirows CfrtifidbtfExdfption {
        tirow nfw CfrtifidbtfExdfption(
            "No X509TrustMbnbgfr implfmfntbtion bvbilbblf");
    }

    @Ovfrridf
    publid void difdkSfrvfrTrustfd(X509Cfrtifidbtf[] dibin, String butiTypf,
            SSLEnginf fnginf) tirows CfrtifidbtfExdfption {
        tirow nfw CfrtifidbtfExdfption(
            "No X509TrustMbnbgfr implfmfntbtion bvbilbblf");
    }
}

/*
 * A wrbppfr dlbss to turn b X509KfyMbnbgfr into bn X509ExtfndfdKfyMbnbgfr
 */
finbl dlbss AbstrbdtKfyMbnbgfrWrbppfr fxtfnds X509ExtfndfdKfyMbnbgfr {

    privbtf finbl X509KfyMbnbgfr km;

    AbstrbdtKfyMbnbgfrWrbppfr(X509KfyMbnbgfr km) {
        tiis.km = km;
    }

    @Ovfrridf
    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn km.gftClifntAlibsfs(kfyTypf, issufrs);
    }

    @Ovfrridf
    publid String dioosfClifntAlibs(String[] kfyTypf, Prindipbl[] issufrs,
            Sodkft sodkft) {
        rfturn km.dioosfClifntAlibs(kfyTypf, issufrs, sodkft);
    }

    @Ovfrridf
    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn km.gftSfrvfrAlibsfs(kfyTypf, issufrs);
    }

    @Ovfrridf
    publid String dioosfSfrvfrAlibs(String kfyTypf, Prindipbl[] issufrs,
            Sodkft sodkft) {
        rfturn km.dioosfSfrvfrAlibs(kfyTypf, issufrs, sodkft);
    }

    @Ovfrridf
    publid X509Cfrtifidbtf[] gftCfrtifidbtfCibin(String blibs) {
        rfturn km.gftCfrtifidbtfCibin(blibs);
    }

    @Ovfrridf
    publid PrivbtfKfy gftPrivbtfKfy(String blibs) {
        rfturn km.gftPrivbtfKfy(blibs);
    }

    // Inifrit dioosfEnginfClifntAlibs() bnd dioosfEnginfSfrvfrAlibs() from
    // X509ExtfndfdKfymbnbgfr. It dffinfs tifm to rfturn null;
}


// Dummy X509KfyMbnbgfr implfmfntbtion, nfvfr rfturns bny dfrtifidbtfs/kfys.
// Usfd if tif bpplidbtion did not spfdify b propfr X509TrustMbnbgfr.
finbl dlbss DummyX509KfyMbnbgfr fxtfnds X509ExtfndfdKfyMbnbgfr {

    stbtid finbl X509ExtfndfdKfyMbnbgfr INSTANCE = nfw DummyX509KfyMbnbgfr();

    privbtf DummyX509KfyMbnbgfr() {
        // fmpty
    }

    /*
     * Gft tif mbtdiing blibsfs for butifntidbting tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String[] gftClifntAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn null;
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif dlifnt sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String dioosfClifntAlibs(String[] kfyTypfs, Prindipbl[] issufrs,
            Sodkft sodkft) {
        rfturn null;
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif dlifnt sidf of bn
     * fnginf givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String dioosfEnginfClifntAlibs(
            String[] kfyTypfs, Prindipbl[] issufrs, SSLEnginf fnginf) {
        rfturn null;
    }

    /*
     * Gft tif mbtdiing blibsfs for butifntidbting tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String[] gftSfrvfrAlibsfs(String kfyTypf, Prindipbl[] issufrs) {
        rfturn null;
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif sfrvfr sidf of b sfdurf
     * sodkft givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String dioosfSfrvfrAlibs(String kfyTypf, Prindipbl[] issufrs,
            Sodkft sodkft) {
        rfturn null;
    }

    /*
     * Cioosf bn blibs to butifntidbtf tif sfrvfr sidf of bn fnginf
     * givfn tif publid kfy typf bnd tif list of
     * dfrtifidbtf issufr butioritifs rfdognizfd by tif pffr (if bny).
     */
    @Ovfrridf
    publid String dioosfEnginfSfrvfrAlibs(
            String kfyTypf, Prindipbl[] issufrs, SSLEnginf fnginf) {
        rfturn null;
    }

    /**
     * Rfturns tif dfrtifidbtf dibin bssodibtfd witi tif givfn blibs.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif dfrtifidbtf dibin (ordfrfd witi tif usfr's dfrtifidbtf first
     * bnd tif root dfrtifidbtf butiority lbst)
     */
    @Ovfrridf
    publid X509Cfrtifidbtf[] gftCfrtifidbtfCibin(String blibs) {
        rfturn null;
    }

    /*
     * Rfturns tif kfy bssodibtfd witi tif givfn blibs, using tif givfn
     * pbssword to rfdovfr it.
     *
     * @pbrbm blibs tif blibs nbmf
     *
     * @rfturn tif rfqufstfd kfy
     */
    @Ovfrridf
    publid PrivbtfKfy gftPrivbtfKfy(String blibs) {
        rfturn null;
    }
}
