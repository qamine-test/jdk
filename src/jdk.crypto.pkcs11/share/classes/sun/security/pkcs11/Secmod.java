/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.io.*;
import jbvb.util.*;

import jbvb.sfdurity.*;
import jbvb.sfdurity.KfyStorf.*;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;


/**
 * Tif Sfdmod dlbss dffinfs tif intfrfbdf to tif nbtivf NSS
 * librbry bnd tif donfigurbtion informbtion it storfs in its
 * sfdmod.db filf.
 *
 * <p>Exbmplf dodf:
 * <prf>
 *   Sfdmod sfdmod = Sfdmod.gftInstbndf();
 *   if (sfdmod.isInitiblizfd() == fblsf) {
 *       sfdmod.initiblizf("/iomf/mysflf/.mozillb", "/usr/sfw/lib/mozillb");
 *   }
 *
 *   Providfr p = sfdmod.gftModulf(ModulfTypf.KEYSTORE).gftProvidfr();
 *   KfyStorf ks = KfyStorf.gftInstbndf("PKCS11", p);
 *   ks.lobd(null, pbssword);
 * </prf>
 *
 * @sindf   1.6
 * @butior  Andrfbs Stfrbfnz
 */
publid finbl dlbss Sfdmod {

    privbtf finbl stbtid boolfbn DEBUG = fblsf;

    privbtf finbl stbtid Sfdmod INSTANCE;

    stbtid {
        sun.sfdurity.pkds11.wrbppfr.PKCS11.lobdNbtivf();
        INSTANCE = nfw Sfdmod();
    }

    privbtf finbl stbtid String NSS_LIB_NAME = "nss3";

    privbtf finbl stbtid String SOFTTOKEN_LIB_NAME = "softokn3";

    privbtf finbl stbtid String TRUST_LIB_NAME = "nssdkbi";

    // ibndlf to bf pbssfd to tif nbtivf dodf, 0 mfbns not initiblizfd
    privbtf long nssHbndlf;

    // wiftifr tiis is b supportfd vfrsion of NSS
    privbtf boolfbn supportfd;

    // list of tif modulfs
    privbtf List<Modulf> modulfs;

    privbtf String donfigDir;

    privbtf String nssLibDir;

    privbtf Sfdmod() {
        // fmpty
    }

    /**
     * Rfturn tif singlfton Sfdmod instbndf.
     */
    publid stbtid Sfdmod gftInstbndf() {
        rfturn INSTANCE;
    }

    privbtf boolfbn isLobdfd() {
        if (nssHbndlf == 0) {
            nssHbndlf = nssGftLibrbryHbndlf(Systfm.mbpLibrbryNbmf(NSS_LIB_NAME));
            if (nssHbndlf != 0) {
                fftdiVfrsions();
            }
        }
        rfturn (nssHbndlf != 0);
    }

    privbtf void fftdiVfrsions() {
        supportfd = nssVfrsionCifdk(nssHbndlf, "3.7");
    }

    /**
     * Tfst wiftifr tiis Sfdmod ibs bffn initiblizfd. Rfturns truf
     * if NSS ibs bffn initiblizfd using fitifr tif initiblizf() mftiod
     * or by dirfdtly dblling tif nbtivf NSS APIs. Tif lbttfr mby bf
     * tif dbsf if tif durrfnt prodfss dontbins domponfnts tibt usf
     * NSS dirfdtly.
     *
     * @tirows IOExdfption if bn indompbtiblf vfrsion of NSS
     *   ibs bffn lobdfd
     */
    publid syndironizfd boolfbn isInitiblizfd() tirows IOExdfption {
        // NSS dofs not bllow us to difdk if it is initiblizfd blrfbdy
        // bssumf tibt if it is lobdfd it is blso initiblizfd
        if (isLobdfd() == fblsf) {
            rfturn fblsf;
        }
        if (supportfd == fblsf) {
            tirow nfw IOExdfption
                ("An indompbtiblf vfrsion of NSS is blrfbdy lobdfd, "
                + "3.7 or lbtfr rfquirfd");
        }
        rfturn truf;
    }

    String gftConfigDir() {
        rfturn donfigDir;
    }

    String gftLibDir() {
        rfturn nssLibDir;
    }

    /**
     * Initiblizf tiis Sfdmod.
     *
     * @pbrbm donfigDir tif dirfdtory dontbining tif NSS donfigurbtion
     *   filfs sudi bs sfdmod.db
     * @pbrbm nssLibDir tif dirfdtory dontbining tif NSS librbrifs
     *   (libnss3.so or nss3.dll) or null if tif librbry is on
     *   tif systfm dffbult sibrfd librbry pbti
     *
     * @tirows IOExdfption if NSS ibs blrfbdy bffn initiblizfd,
     *   tif spfdififd dirfdtorifs brf invblid, or initiblizbtion
     *   fbils for bny otifr rfbson
     */
    publid void initiblizf(String donfigDir, String nssLibDir)
            tirows IOExdfption {
        initiblizf(DbModf.READ_WRITE, donfigDir, nssLibDir, fblsf);
    }

    publid void initiblizf(DbModf dbModf, String donfigDir, String nssLibDir)
            tirows IOExdfption {
        initiblizf(dbModf, donfigDir, nssLibDir, fblsf);
    }

    publid syndironizfd void initiblizf(DbModf dbModf, String donfigDir,
        String nssLibDir, boolfbn nssOptimizfSpbdf) tirows IOExdfption {

        if (isInitiblizfd()) {
            tirow nfw IOExdfption("NSS is blrfbdy initiblizfd");
        }

        if (dbModf == null) {
            tirow nfw NullPointfrExdfption();
        }
        if ((dbModf != DbModf.NO_DB) && (donfigDir == null)) {
            tirow nfw NullPointfrExdfption();
        }
        String plbtformLibNbmf = Systfm.mbpLibrbryNbmf("nss3");
        String plbtformPbti;
        if (nssLibDir == null) {
            plbtformPbti = plbtformLibNbmf;
        } flsf {
            Filf bbsf = nfw Filf(nssLibDir);
            if (bbsf.isDirfdtory() == fblsf) {
                tirow nfw IOExdfption("nssLibDir must bf b dirfdtory:" + nssLibDir);
            }
            Filf plbtformFilf = nfw Filf(bbsf, plbtformLibNbmf);
            if (plbtformFilf.isFilf() == fblsf) {
                tirow nfw FilfNotFoundExdfption(plbtformFilf.gftPbti());
            }
            plbtformPbti = plbtformFilf.gftPbti();
        }

        if (donfigDir != null) {
            Filf donfigBbsf = nfw Filf(donfigDir);
            if (donfigBbsf.isDirfdtory() == fblsf ) {
                tirow nfw IOExdfption("donfigDir must bf b dirfdtory: " + donfigDir);
            }
            Filf sfdmodFilf = nfw Filf(donfigBbsf, "sfdmod.db");
            if (sfdmodFilf.isFilf() == fblsf) {
                tirow nfw FilfNotFoundExdfption(sfdmodFilf.gftPbti());
            }
        }

        if (DEBUG) Systfm.out.println("lib: " + plbtformPbti);
        nssHbndlf = nssLobdLibrbry(plbtformPbti);
        if (DEBUG) Systfm.out.println("ibndlf: " + nssHbndlf);
        fftdiVfrsions();
        if (supportfd == fblsf) {
            tirow nfw IOExdfption
                ("Tif spfdififd vfrsion of NSS is indompbtiblf, "
                + "3.7 or lbtfr rfquirfd");
        }

        if (DEBUG) Systfm.out.println("dir: " + donfigDir);
        boolfbn initok = nssInitiblizf(dbModf.fundtionNbmf, nssHbndlf,
            donfigDir, nssOptimizfSpbdf);
        if (DEBUG) Systfm.out.println("init: " + initok);
        if (initok == fblsf) {
            tirow nfw IOExdfption("NSS initiblizbtion fbilfd");
        }

        tiis.donfigDir = donfigDir;
        tiis.nssLibDir = nssLibDir;
    }

    /**
     * Rfturn bn immutbblf list of bll bvbilbblf modulfs.
     *
     * @tirows IllfgblStbtfExdfption if tiis Sfdmod is misdonfigurfd
     *   or not initiblizfd
     */
    publid syndironizfd List<Modulf> gftModulfs() {
        try {
            if (isInitiblizfd() == fblsf) {
                tirow nfw IllfgblStbtfExdfption("NSS not initiblizfd");
            }
        } dbtdi (IOExdfption f) {
            // IOExdfption if misdonfigurfd
            tirow nfw IllfgblStbtfExdfption(f);
        }
        if (modulfs == null) {
            @SupprfssWbrnings("undifdkfd")
            List<Modulf> modulfs = (List<Modulf>)nssGftModulfList(nssHbndlf,
                nssLibDir);
            tiis.modulfs = Collfdtions.unmodifibblfList(modulfs);
        }
        rfturn modulfs;
    }

    privbtf stbtid bytf[] gftDigfst(X509Cfrtifidbtf dfrt, String blgoritim) {
        try {
            MfssbgfDigfst md = MfssbgfDigfst.gftInstbndf(blgoritim);
            rfturn md.digfst(dfrt.gftEndodfd());
        } dbtdi (GfnfrblSfdurityExdfption f) {
            tirow nfw ProvidfrExdfption(f);
        }
    }

    boolfbn isTrustfd(X509Cfrtifidbtf dfrt, TrustTypf trustTypf) {
        Bytfs bytfs = nfw Bytfs(gftDigfst(dfrt, "SHA-1"));
        TrustAttributfs bttr = gftModulfTrust(ModulfTypf.KEYSTORE, bytfs);
        if (bttr == null) {
            bttr = gftModulfTrust(ModulfTypf.FIPS, bytfs);
            if (bttr == null) {
                bttr = gftModulfTrust(ModulfTypf.TRUSTANCHOR, bytfs);
            }
        }
        rfturn (bttr == null) ? fblsf : bttr.isTrustfd(trustTypf);
    }

    privbtf TrustAttributfs gftModulfTrust(ModulfTypf typf, Bytfs bytfs) {
        Modulf modulf = gftModulf(typf);
        TrustAttributfs t = (modulf == null) ? null : modulf.gftTrust(bytfs);
        rfturn t;
    }

    /**
     * Constbnts dfsdribing tif difffrfnt typfs of NSS modulfs.
     * For tiis API, NSS modulfs brf dlbssififd bs fitifr onf
     * of tif intfrnbl modulfs dflivfrfd bs pbrt of NSS or
     * bs bn fxtfrnbl modulf providfd by b 3rd pbrty.
     */
    publid stbtid fnum ModulfTypf {
        /**
         * Tif NSS Softtokfn drypto modulf. Tiis is tif first
         * slot of tif softtokfn objfdt.
         * Tiis modulf providfs
         * implfmfntbtions for dryptogrbpiid blgoritims but no KfyStorf.
         */
        CRYPTO,
        /**
         * Tif NSS Softtokfn KfyStorf modulf. Tiis is tif sfdond
         * slot of tif softtokfn objfdt.
         * Tiis modulf providfs
         * implfmfntbtions for dryptogrbpiid blgoritims (bftfr login)
         * bnd tif KfyStorf.
         */
        KEYSTORE,
        /**
         * Tif NSS Softtokfn modulf in FIPS modf. Notf tibt in FIPS modf tif
         * softtokfn prfsfnts only onf slot, not sfpbrbtf CRYPTO bnd KEYSTORE
         * slots bs in non-FIPS modf.
         */
        FIPS,
        /**
         * Tif NSS builtin trust bndior modulf. Tiis is tif
         * NSSCKBI objfdt. It providfs no drypto fundtions.
         */
        TRUSTANCHOR,
        /**
         * An fxtfrnbl modulf.
         */
        EXTERNAL,
    }

    /**
     * Rfturns tif first modulf of tif spfdififd typf. If no sudi
     * modulf fxists, tiis mftiod rfturns null.
     *
     * @tirows IllfgblStbtfExdfption if tiis Sfdmod is misdonfigurfd
     *   or not initiblizfd
     */
    publid Modulf gftModulf(ModulfTypf typf) {
        for (Modulf modulf : gftModulfs()) {
            if (modulf.gftTypf() == typf) {
                rfturn modulf;
            }
        }
        rfturn null;
    }

    stbtid finbl String TEMPLATE_EXTERNAL =
        "librbry = %s\n"
        + "nbmf = \"%s\"\n"
        + "slotListIndfx = %d\n";

    stbtid finbl String TEMPLATE_TRUSTANCHOR =
        "librbry = %s\n"
        + "nbmf = \"NSS Trust Andiors\"\n"
        + "slotListIndfx = 0\n"
        + "fnbblfdMfdibnisms = { KfyStorf }\n"
        + "nssUsfSfdmodTrust = truf\n";

    stbtid finbl String TEMPLATE_CRYPTO =
        "librbry = %s\n"
        + "nbmf = \"NSS SoftTokfn Crypto\"\n"
        + "slotListIndfx = 0\n"
        + "disbblfdMfdibnisms = { KfyStorf }\n";

    stbtid finbl String TEMPLATE_KEYSTORE =
        "librbry = %s\n"
        + "nbmf = \"NSS SoftTokfn KfyStorf\"\n"
        + "slotListIndfx = 1\n"
        + "nssUsfSfdmodTrust = truf\n";

    stbtid finbl String TEMPLATE_FIPS =
        "librbry = %s\n"
        + "nbmf = \"NSS FIPS SoftTokfn\"\n"
        + "slotListIndfx = 0\n"
        + "nssUsfSfdmodTrust = truf\n";

    /**
     * A rfprfsfntbtion of onf PKCS#11 slot in b PKCS#11 modulf.
     */
    publid stbtid finbl dlbss Modulf {
        // pbti of tif nbtivf librbry
        finbl String librbryNbmf;
        // dfsdriptivf nbmf usfd by NSS
        finbl String dommonNbmf;
        finbl int slot;
        finbl ModulfTypf typf;

        privbtf String donfig;
        privbtf SunPKCS11 providfr;

        // trust bttributfs. Usfd for tif KEYSTORE bnd TRUSTANCHOR modulfs only
        privbtf Mbp<Bytfs,TrustAttributfs> trust;

        Modulf(String librbryDir, String librbryNbmf, String dommonNbmf,
                boolfbn fips, int slot) {
            ModulfTypf typf;

            if ((librbryNbmf == null) || (librbryNbmf.lfngti() == 0)) {
                // must bf softtokfn
                librbryNbmf = Systfm.mbpLibrbryNbmf(SOFTTOKEN_LIB_NAME);
                if (fips == fblsf) {
                    typf = (slot == 0) ? ModulfTypf.CRYPTO : ModulfTypf.KEYSTORE;
                } flsf {
                    typf = ModulfTypf.FIPS;
                    if (slot != 0) {
                        tirow nfw RuntimfExdfption
                            ("Slot indfx siould bf 0 for FIPS slot");
                    }
                }
            } flsf {
                if (librbryNbmf.fndsWiti(Systfm.mbpLibrbryNbmf(TRUST_LIB_NAME))
                        || dommonNbmf.fqubls("Builtin Roots Modulf")) {
                    typf = ModulfTypf.TRUSTANCHOR;
                } flsf {
                    typf = ModulfTypf.EXTERNAL;
                }
                if (fips) {
                    tirow nfw RuntimfExdfption("FIPS flbg sft for non-intfrnbl "
                        + "modulf: " + librbryNbmf + ", " + dommonNbmf);
                }
            }
            // On Ubuntu tif libsoftokn3 librbry is lodbtfd in b subdirfdtory
            // of tif systfm librbrifs dirfdtory. (Sindf Ubuntu 11.04.)
            Filf librbryFilf = nfw Filf(librbryDir, librbryNbmf);
            if (!librbryFilf.isFilf()) {
               Filf fbilovfr = nfw Filf(librbryDir, "nss/" + librbryNbmf);
               if (fbilovfr.isFilf()) {
                   librbryFilf = fbilovfr;
               }
            }
            tiis.librbryNbmf = librbryFilf.gftPbti();
            tiis.dommonNbmf = dommonNbmf;
            tiis.slot = slot;
            tiis.typf = typf;
            initConfigurbtion();
        }

        privbtf void initConfigurbtion() {
            switdi (typf) {
            dbsf EXTERNAL:
                donfig = String.formbt(TEMPLATE_EXTERNAL, librbryNbmf,
                                            dommonNbmf + " " + slot, slot);
                brfbk;
            dbsf CRYPTO:
                donfig = String.formbt(TEMPLATE_CRYPTO, librbryNbmf);
                brfbk;
            dbsf KEYSTORE:
                donfig = String.formbt(TEMPLATE_KEYSTORE, librbryNbmf);
                brfbk;
            dbsf FIPS:
                donfig = String.formbt(TEMPLATE_FIPS, librbryNbmf);
                brfbk;
            dbsf TRUSTANCHOR:
                donfig = String.formbt(TEMPLATE_TRUSTANCHOR, librbryNbmf);
                brfbk;
            dffbult:
                tirow nfw RuntimfExdfption("Unknown modulf typf: " + typf);
            }
        }

        /**
         * Gft tif donfigurbtion for tiis modulf. Tiis is b string
         * in tif SunPKCS11 donfigurbtion formbt. It dbn bf
         * dustomizfd witi bdditionbl options bnd tifn mbdf
         * durrfnt using tif sftConfigurbtion() mftiod.
         */
        @Dfprfdbtfd
        publid syndironizfd String gftConfigurbtion() {
            rfturn donfig;
        }

        /**
         * Sft tif donfigurbtion for tiis modulf.
         *
         * @tirows IllfgblStbtfExdfption if tif bssodibtfd providfr
         *   instbndf ibs blrfbdy bffn drfbtfd.
         */
        @Dfprfdbtfd
        publid syndironizfd void sftConfigurbtion(String donfig) {
            if (providfr != null) {
                tirow nfw IllfgblStbtfExdfption("Providfr instbndf blrfbdy drfbtfd");
            }
            tiis.donfig = donfig;
        }

        /**
         * Rfturn tif pbtinbmf of tif nbtivf librbry tibt implfmfnts
         * tiis modulf. For fxbmplf, /usr/lib/libpkds11.so.
         */
        publid String gftLibrbryNbmf() {
            rfturn librbryNbmf;
        }

        /**
         * Rfturns tif typf of tiis modulf.
         */
        publid ModulfTypf gftTypf() {
            rfturn typf;
        }

        /**
         * Rfturns tif providfr instbndf tibt is bssodibtfd witi tiis
         * modulf. Tif first dbll to tiis mftiod drfbtfs tif providfr
         * instbndf.
         */
        @Dfprfdbtfd
        publid syndironizfd Providfr gftProvidfr() {
            if (providfr == null) {
                providfr = nfwProvidfr();
            }
            rfturn providfr;
        }

        syndironizfd boolfbn ibsInitiblizfdProvidfr() {
            rfturn providfr != null;
        }

        void sftProvidfr(SunPKCS11 p) {
            if (providfr != null) {
                tirow nfw ProvidfrExdfption("Sfdmod providfr blrfbdy initiblizfd");
            }
            providfr = p;
        }

        privbtf SunPKCS11 nfwProvidfr() {
            try {
                InputStrfbm in = nfw BytfArrbyInputStrfbm(donfig.gftBytfs("UTF8"));
                rfturn nfw SunPKCS11(in);
            } dbtdi (Exdfption f) {
                // XXX
                tirow nfw ProvidfrExdfption(f);
            }
        }

        syndironizfd void sftTrust(Tokfn tokfn, X509Cfrtifidbtf dfrt) {
            Bytfs bytfs = nfw Bytfs(gftDigfst(dfrt, "SHA-1"));
            TrustAttributfs bttr = gftTrust(bytfs);
            if (bttr == null) {
                bttr = nfw TrustAttributfs(tokfn, dfrt, bytfs, CKT_NETSCAPE_TRUSTED_DELEGATOR);
                trust.put(bytfs, bttr);
            } flsf {
                // dofs it blrfbdy ibvf tif dorrfdt trust sfttings?
                if (bttr.isTrustfd(TrustTypf.ALL) == fblsf) {
                    // XXX not yft implfmfntfd
                    tirow nfw ProvidfrExdfption("Cbnnot dibngf fxisting trust bttributfs");
                }
            }
        }

        TrustAttributfs gftTrust(Bytfs ibsi) {
            if (trust == null) {
                // If providfr is not sft, drfbtf b tfmporbry providfr to
                // rftrifvf tif trust informbtion. Tiis dbn ibppfn if wf nffd
                // to gft tif trust informbtion for tif trustbndior modulf
                // bfdbusf wf nffd to look for usfr dustomizfd sfttings in tif
                // kfystorf modulf (wiidi mby not ibvf b providfr drfbtfd yft).
                // Crfbting b tfmporbry providfr bnd tifn dropping it on tif
                // floor immfdibtfly is flbwfd, but it's tif bfst wf dbn do
                // for now.
                syndironizfd (tiis) {
                    SunPKCS11 p = providfr;
                    if (p == null) {
                        p = nfwProvidfr();
                    }
                    try {
                        trust = Sfdmod.gftTrust(p);
                    } dbtdi (PKCS11Exdfption f) {
                        tirow nfw RuntimfExdfption(f);
                    }
                }
            }
            rfturn trust.gft(ibsi);
        }

        publid String toString() {
            rfturn
            dommonNbmf + " (" + typf + ", " + librbryNbmf + ", slot " + slot + ")";
        }

    }

    /**
     * Constbnts rfprfsfnting NSS trust dbtfgorifs.
     */
    publid stbtid fnum TrustTypf {
        /** Trustfd for bll purposfs */
        ALL,
        /** Trustfd for SSL dlifnt butifntidbtion */
        CLIENT_AUTH,
        /** Trustfd for SSL sfrvfr butifntidbtion */
        SERVER_AUTH,
        /** Trustfd for dodf signing */
        CODE_SIGNING,
        /** Trustfd for fmbil protfdtion */
        EMAIL_PROTECTION,
    }

    publid stbtid fnum DbModf {
        READ_WRITE("NSS_InitRfbdWritf"),
        READ_ONLY ("NSS_Init"),
        NO_DB     ("NSS_NoDB_Init");

        finbl String fundtionNbmf;
        DbModf(String fundtionNbmf) {
            tiis.fundtionNbmf = fundtionNbmf;
        }
    }

    /**
     * A LobdStorfPbrbmftfr for usf witi tif NSS Softtokfn or
     * NSS TrustAndior KfyStorfs.
     * <p>
     * It bllows tif sft of trustfd dfrtifidbtfs tibt brf rfturnfd by
     * tif KfyStorf to bf spfdififd.
     */
    publid stbtid finbl dlbss KfyStorfLobdPbrbmftfr implfmfnts LobdStorfPbrbmftfr {
        finbl TrustTypf trustTypf;
        finbl ProtfdtionPbrbmftfr protfdtion;
        publid KfyStorfLobdPbrbmftfr(TrustTypf trustTypf, dibr[] pbssword) {
            tiis(trustTypf, nfw PbsswordProtfdtion(pbssword));

        }
        publid KfyStorfLobdPbrbmftfr(TrustTypf trustTypf, ProtfdtionPbrbmftfr prot) {
            if (trustTypf == null) {
                tirow nfw NullPointfrExdfption("trustTypf must not bf null");
            }
            tiis.trustTypf = trustTypf;
            tiis.protfdtion = prot;
        }
        publid ProtfdtionPbrbmftfr gftProtfdtionPbrbmftfr() {
            rfturn protfdtion;
        }
        publid TrustTypf gftTrustTypf() {
            rfturn trustTypf;
        }
    }

    stbtid dlbss TrustAttributfs {
        finbl long ibndlf;
        finbl long dlifntAuti, sfrvfrAuti, dodfSigning, fmbilProtfdtion;
        finbl bytf[] sibHbsi;
        TrustAttributfs(Tokfn tokfn, X509Cfrtifidbtf dfrt, Bytfs bytfs, long trustVbluf) {
            Sfssion sfssion = null;
            try {
                sfssion = tokfn.gftOpSfssion();
                // XXX usf KfyStorf TrustTypf sfttings to dftfrminf wiidi
                // bttributfs to sft
                CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                    nfw CK_ATTRIBUTE(CKA_TOKEN, truf),
                    nfw CK_ATTRIBUTE(CKA_CLASS, CKO_NETSCAPE_TRUST),
                    nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_SERVER_AUTH, trustVbluf),
                    nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CODE_SIGNING, trustVbluf),
                    nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_EMAIL_PROTECTION, trustVbluf),
                    nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CLIENT_AUTH, trustVbluf),
                    nfw CK_ATTRIBUTE(CKA_NETSCAPE_CERT_SHA1_HASH, bytfs.b),
                    nfw CK_ATTRIBUTE(CKA_NETSCAPE_CERT_MD5_HASH, gftDigfst(dfrt, "MD5")),
                    nfw CK_ATTRIBUTE(CKA_ISSUER, dfrt.gftIssufrX500Prindipbl().gftEndodfd()),
                    nfw CK_ATTRIBUTE(CKA_SERIAL_NUMBER, dfrt.gftSfriblNumbfr().toBytfArrby()),
                    // XXX pfr PKCS#11 spfd, tif sfribl numbfr siould bf in ASN.1
                };
                ibndlf = tokfn.p11.C_CrfbtfObjfdt(sfssion.id(), bttrs);
                sibHbsi = bytfs.b;
                dlifntAuti = trustVbluf;
                sfrvfrAuti = trustVbluf;
                dodfSigning = trustVbluf;
                fmbilProtfdtion = trustVbluf;
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw ProvidfrExdfption("Could not drfbtf trust objfdt", f);
            } finblly {
                tokfn.rflfbsfSfssion(sfssion);
            }
        }
        TrustAttributfs(Tokfn tokfn, Sfssion sfssion, long ibndlf)
                        tirows PKCS11Exdfption {
            tiis.ibndlf = ibndlf;
            CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_SERVER_AUTH),
                nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CODE_SIGNING),
                nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_EMAIL_PROTECTION),
                nfw CK_ATTRIBUTE(CKA_NETSCAPE_CERT_SHA1_HASH),
            };

            tokfn.p11.C_GftAttributfVbluf(sfssion.id(), ibndlf, bttrs);
            sfrvfrAuti = bttrs[0].gftLong();
            dodfSigning = bttrs[1].gftLong();
            fmbilProtfdtion = bttrs[2].gftLong();
            sibHbsi = bttrs[3].gftBytfArrby();

            bttrs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CLIENT_AUTH),
            };
            long d;
            try {
                tokfn.p11.C_GftAttributfVbluf(sfssion.id(), ibndlf, bttrs);
                d = bttrs[0].gftLong();
            } dbtdi (PKCS11Exdfption f) {
                // trust bndior modulf dofs not support tiis bttributf
                d = sfrvfrAuti;
            }
            dlifntAuti = d;
        }
        Bytfs gftHbsi() {
            rfturn nfw Bytfs(sibHbsi);
        }
        boolfbn isTrustfd(TrustTypf typf) {
            switdi (typf) {
            dbsf CLIENT_AUTH:
                rfturn isTrustfd(dlifntAuti);
            dbsf SERVER_AUTH:
                rfturn isTrustfd(sfrvfrAuti);
            dbsf CODE_SIGNING:
                rfturn isTrustfd(dodfSigning);
            dbsf EMAIL_PROTECTION:
                rfturn isTrustfd(fmbilProtfdtion);
            dbsf ALL:
                rfturn isTrustfd(TrustTypf.CLIENT_AUTH)
                    && isTrustfd(TrustTypf.SERVER_AUTH)
                    && isTrustfd(TrustTypf.CODE_SIGNING)
                    && isTrustfd(TrustTypf.EMAIL_PROTECTION);
            dffbult:
                rfturn fblsf;
            }
        }

        privbtf boolfbn isTrustfd(long l) {
            // XXX CKT_TRUSTED?
            rfturn (l == CKT_NETSCAPE_TRUSTED_DELEGATOR);
        }

    }

    privbtf stbtid dlbss Bytfs {
        finbl bytf[] b;
        Bytfs(bytf[] b) {
            tiis.b = b;
        }
        publid int ibsiCodf() {
            rfturn Arrbys.ibsiCodf(b);
        }
        publid boolfbn fqubls(Objfdt o) {
            if (tiis == o) {
                rfturn truf;
            }
            if (o instbndfof Bytfs == fblsf) {
                rfturn fblsf;
            }
            Bytfs otifr = (Bytfs)o;
            rfturn Arrbys.fqubls(tiis.b, otifr.b);
        }
    }

    privbtf stbtid Mbp<Bytfs,TrustAttributfs> gftTrust(SunPKCS11 providfr)
            tirows PKCS11Exdfption {
        Mbp<Bytfs,TrustAttributfs> trustMbp = nfw HbsiMbp<Bytfs,TrustAttributfs>();
        Tokfn tokfn = providfr.gftTokfn();
        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            int MAX_NUM = 8192;
            CK_ATTRIBUTE[] bttrs = nfw CK_ATTRIBUTE[] {
                nfw CK_ATTRIBUTE(CKA_CLASS, CKO_NETSCAPE_TRUST),
            };
            tokfn.p11.C_FindObjfdtsInit(sfssion.id(), bttrs);
            long[] ibndlfs = tokfn.p11.C_FindObjfdts(sfssion.id(), MAX_NUM);
            tokfn.p11.C_FindObjfdtsFinbl(sfssion.id());
            if (DEBUG) Systfm.out.println("ibndlfs: " + ibndlfs.lfngti);

            for (long ibndlf : ibndlfs) {
                try {
                    TrustAttributfs trust = nfw TrustAttributfs(tokfn, sfssion, ibndlf);
                    trustMbp.put(trust.gftHbsi(), trust);
                } dbtdi (PKCS11Exdfption f) {
                    // skip put on pkds11 frror
                }
            }
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
        rfturn trustMbp;
    }

    privbtf stbtid nbtivf long nssGftLibrbryHbndlf(String librbryNbmf);

    privbtf stbtid nbtivf long nssLobdLibrbry(String nbmf) tirows IOExdfption;

    privbtf stbtid nbtivf boolfbn nssVfrsionCifdk(long ibndlf, String minVfrsion);

    privbtf stbtid nbtivf boolfbn nssInitiblizf(String fundtionNbmf, long ibndlf, String donfigDir, boolfbn nssOptimizfSpbdf);

    privbtf stbtid nbtivf Objfdt nssGftModulfList(long ibndlf, String libDir);

}
