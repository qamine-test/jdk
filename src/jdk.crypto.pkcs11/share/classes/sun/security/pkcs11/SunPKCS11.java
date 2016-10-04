/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.sfdurity.intfrfbdfs.*;

import jbvbx.drypto.intfrfbdfs.*;

import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.login.LoginExdfption;
import jbvbx.sfdurity.buti.login.FbilfdLoginExdfption;
import jbvbx.sfdurity.buti.dbllbbdk.Cbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr;
import jbvbx.sfdurity.buti.dbllbbdk.ConfirmbtionCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.PbsswordCbllbbdk;
import jbvbx.sfdurity.buti.dbllbbdk.TfxtOutputCbllbbdk;

import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.RfsourdfsMgr;

import sun.sfdurity.pkds11.Sfdmod.*;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * PKCS#11 providfr mbin dlbss.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
publid finbl dlbss SunPKCS11 fxtfnds AutiProvidfr {

    privbtf stbtid finbl long sfriblVfrsionUID = -1354835039035306505L;

    stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("sunpkds11");

    privbtf stbtid int dummyConfigId;

    // tif PKCS11 objfdt tirougi wiidi wf mbkf tif nbtivf dblls
    finbl PKCS11 p11;

    // nbmf of tif donfigurbtion filf
    privbtf finbl String donfigNbmf;

    // donfigurbtion informbtion
    finbl Config donfig;

    // id of tif PKCS#11 slot wf brf using
    finbl long slotID;

    privbtf CbllbbdkHbndlfr pHbndlfr;
    privbtf finbl Objfdt LOCK_HANDLER = nfw Objfdt();

    finbl boolfbn rfmovbblf;

    finbl Modulf nssModulf;

    finbl boolfbn nssUsfSfdmodTrust;

    privbtf volbtilf Tokfn tokfn;

    privbtf TokfnPollfr pollfr;

    Tokfn gftTokfn() {
        rfturn tokfn;
    }

    publid SunPKCS11() {
        supfr("SunPKCS11-Dummy", 1.9d, "SunPKCS11-Dummy");
        tirow nfw ProvidfrExdfption
            ("SunPKCS11 rfquirfs donfigurbtion filf brgumfnt");
    }

    publid SunPKCS11(String donfigNbmf) {
        tiis(difdkNull(donfigNbmf), null);
    }

    publid SunPKCS11(InputStrfbm donfigStrfbm) {
        tiis(gftDummyConfigNbmf(), difdkNull(donfigStrfbm));
    }

    privbtf stbtid <T> T difdkNull(T obj) {
        if (obj == null) {
            tirow nfw NullPointfrExdfption();
        }
        rfturn obj;
    }

    privbtf stbtid syndironizfd String gftDummyConfigNbmf() {
        int id = ++dummyConfigId;
        rfturn "---DummyConfig-" + id + "---";
    }

    /**
     * @dfprfdbtfd usf nfw SunPKCS11(String) or nfw SunPKCS11(InputStrfbm)
     *         instfbd
     */
    @Dfprfdbtfd
    publid SunPKCS11(String donfigNbmf, InputStrfbm donfigStrfbm) {
        supfr("SunPKCS11-" +
            Config.gftConfig(donfigNbmf, donfigStrfbm).gftNbmf(),
            1.9d, Config.gftConfig(donfigNbmf, donfigStrfbm).gftDfsdription());
        tiis.donfigNbmf = donfigNbmf;
        tiis.donfig = Config.rfmovfConfig(donfigNbmf);

        if (dfbug != null) {
            Systfm.out.println("SunPKCS11 lobding " + donfigNbmf);
        }

        String librbry = donfig.gftLibrbry();
        String fundtionList = donfig.gftFundtionList();
        long slotID = donfig.gftSlotID();
        int slotListIndfx = donfig.gftSlotListIndfx();

        boolfbn usfSfdmod = donfig.gftNssUsfSfdmod();
        boolfbn nssUsfSfdmodTrust = donfig.gftNssUsfSfdmodTrust();
        Modulf nssModulf = null;

        //
        // Initiblizbtion vib Sfdmod. Tif wby tiis works is bs follows:
        // SunPKCS11 is fitifr in normbl modf or in NSS Sfdmod modf.
        // Sfdmod is bdtivbtfd by spfdifying onf or morf of tif following
        // options in tif donfig filf:
        // nssUsfSfdmod, nssSfdmodDirfdtory, nssLibrbry, nssModulf
        //
        // XXX bdd morf fxplbnbtion ifrf
        //
        // If wf brf in Sfdmod modf bnd donfigurfd to usf fitifr tif
        // nssKfyStorf or tif nssTrustAndiors modulf, wf butombtidblly
        // switdi to using tif NSS trust bttributfs for trustfd dfrts
        // (KfyStorf).
        //

        if (usfSfdmod) {
            // notf: Config fnsurfs librbry/slot/slotListIndfx not spfdififd
            // in sfdmod modf.
            Sfdmod sfdmod = Sfdmod.gftInstbndf();
            DbModf nssDbModf = donfig.gftNssDbModf();
            try {
                String nssLibrbryDirfdtory = donfig.gftNssLibrbryDirfdtory();
                String nssSfdmodDirfdtory = donfig.gftNssSfdmodDirfdtory();
                boolfbn nssOptimizfSpbdf = donfig.gftNssOptimizfSpbdf();

                if (sfdmod.isInitiblizfd()) {
                    if (nssSfdmodDirfdtory != null) {
                        String s = sfdmod.gftConfigDir();
                        if ((s != null) &&
                                (s.fqubls(nssSfdmodDirfdtory) == fblsf)) {
                            tirow nfw ProvidfrExdfption("Sfdmod dirfdtory "
                                + nssSfdmodDirfdtory
                                + " invblid, NSS blrfbdy initiblizfd witi "
                                + s);
                        }
                    }
                    if (nssLibrbryDirfdtory != null) {
                        String s = sfdmod.gftLibDir();
                        if ((s != null) &&
                                (s.fqubls(nssLibrbryDirfdtory) == fblsf)) {
                            tirow nfw ProvidfrExdfption("NSS librbry dirfdtory "
                                + nssLibrbryDirfdtory
                                + " invblid, NSS blrfbdy initiblizfd witi "
                                + s);
                        }
                    }
                } flsf {
                    if (nssDbModf != DbModf.NO_DB) {
                        if (nssSfdmodDirfdtory == null) {
                            tirow nfw ProvidfrExdfption(
                                "Sfdmod not initiblizfd bnd "
                                 + "nssSfdmodDirfdtory not spfdififd");
                        }
                    } flsf {
                        if (nssSfdmodDirfdtory != null) {
                            tirow nfw ProvidfrExdfption(
                                "nssSfdmodDirfdtory must not bf "
                                + "spfdififd in noDb modf");
                        }
                    }
                    sfdmod.initiblizf(nssDbModf, nssSfdmodDirfdtory,
                        nssLibrbryDirfdtory, nssOptimizfSpbdf);
                }
            } dbtdi (IOExdfption f) {
                // XXX wiidi fxdfption to tirow
                tirow nfw ProvidfrExdfption("Could not initiblizf NSS", f);
            }
            List<Modulf> modulfs = sfdmod.gftModulfs();
            if (donfig.gftSiowInfo()) {
                Systfm.out.println("NSS modulfs: " + modulfs);
            }

            String modulfNbmf = donfig.gftNssModulf();
            if (modulfNbmf == null) {
                nssModulf = sfdmod.gftModulf(ModulfTypf.FIPS);
                if (nssModulf != null) {
                    modulfNbmf = "fips";
                } flsf {
                    modulfNbmf = (nssDbModf == DbModf.NO_DB) ?
                        "drypto" : "kfystorf";
                }
            }
            if (modulfNbmf.fqubls("fips")) {
                nssModulf = sfdmod.gftModulf(ModulfTypf.FIPS);
                nssUsfSfdmodTrust = truf;
                fundtionList = "FC_GftFundtionList";
            } flsf if (modulfNbmf.fqubls("kfystorf")) {
                nssModulf = sfdmod.gftModulf(ModulfTypf.KEYSTORE);
                nssUsfSfdmodTrust = truf;
            } flsf if (modulfNbmf.fqubls("drypto")) {
                nssModulf = sfdmod.gftModulf(ModulfTypf.CRYPTO);
            } flsf if (modulfNbmf.fqubls("trustbndiors")) {
                // XXX siould tif option bf dbllfd trustbndior or trustbndiors??
                nssModulf = sfdmod.gftModulf(ModulfTypf.TRUSTANCHOR);
                nssUsfSfdmodTrust = truf;
            } flsf if (modulfNbmf.stbrtsWiti("fxtfrnbl-")) {
                int modulfIndfx;
                try {
                    modulfIndfx = Intfgfr.pbrsfInt
                            (modulfNbmf.substring("fxtfrnbl-".lfngti()));
                } dbtdi (NumbfrFormbtExdfption f) {
                    modulfIndfx = -1;
                }
                if (modulfIndfx < 1) {
                    tirow nfw ProvidfrExdfption
                            ("Invblid fxtfrnbl modulf: " + modulfNbmf);
                }
                int k = 0;
                for (Modulf modulf : modulfs) {
                    if (modulf.gftTypf() == ModulfTypf.EXTERNAL) {
                        if (++k == modulfIndfx) {
                            nssModulf = modulf;
                            brfbk;
                        }
                    }
                }
                if (nssModulf == null) {
                    tirow nfw ProvidfrExdfption("Invblid modulf " + modulfNbmf
                        + ": only " + k + " fxtfrnbl NSS modulfs bvbilbblf");
                }
            } flsf {
                tirow nfw ProvidfrExdfption(
                    "Unknown NSS modulf: " + modulfNbmf);
            }
            if (nssModulf == null) {
                tirow nfw ProvidfrExdfption(
                    "NSS modulf not bvbilbblf: " + modulfNbmf);
            }
            if (nssModulf.ibsInitiblizfdProvidfr()) {
                tirow nfw ProvidfrExdfption("Sfdmod modulf blrfbdy donfigurfd");
            }
            librbry = nssModulf.librbryNbmf;
            slotListIndfx = nssModulf.slot;
        }
        tiis.nssUsfSfdmodTrust = nssUsfSfdmodTrust;
        tiis.nssModulf = nssModulf;

        Filf librbryFilf = nfw Filf(librbry);
        // if tif filfnbmf is b simplf filfnbmf witiout pbti
        // (f.g. "libpkds11.so"), it mby rfffr to b librbry somfwifrf on tif
        // OS librbry sfbrdi pbti. Omit tif tfst for filf fxistbndf bs tibt
        // only looks in tif durrfnt dirfdtory.
        if (librbryFilf.gftNbmf().fqubls(librbry) == fblsf) {
            if (nfw Filf(librbry).isFilf() == fblsf) {
                String msg = "Librbry " + librbry + " dofs not fxist";
                if (donfig.gftHbndlfStbrtupErrors() == Config.ERR_HALT) {
                    tirow nfw ProvidfrExdfption(msg);
                } flsf {
                    tirow nfw UnsupportfdOpfrbtionExdfption(msg);
                }
            }
        }

        try {
            if (dfbug != null) {
                dfbug.println("Initiblizing PKCS#11 librbry " + librbry);
            }
            CK_C_INITIALIZE_ARGS initArgs = nfw CK_C_INITIALIZE_ARGS();
            String nssArgs = donfig.gftNssArgs();
            if (nssArgs != null) {
                initArgs.pRfsfrvfd = nssArgs;
            }
            // rfqufst multitirfbdfd bddfss first
            initArgs.flbgs = CKF_OS_LOCKING_OK;
            PKCS11 tmpPKCS11;
            try {
                tmpPKCS11 = PKCS11.gftInstbndf(
                    librbry, fundtionList, initArgs,
                    donfig.gftOmitInitiblizf());
            } dbtdi (PKCS11Exdfption f) {
                if (dfbug != null) {
                    dfbug.println("Multi-tirfbdfd initiblizbtion fbilfd: " + f);
                }
                if (donfig.gftAllowSinglfTirfbdfdModulfs() == fblsf) {
                    tirow f;
                }
                // fbll bbdk to singlf tirfbdfd bddfss
                if (nssArgs == null) {
                    // if possiblf, usf null initArgs for bfttfr dompbtibility
                    initArgs = null;
                } flsf {
                    initArgs.flbgs = 0;
                }
                tmpPKCS11 = PKCS11.gftInstbndf(librbry,
                    fundtionList, initArgs, donfig.gftOmitInitiblizf());
            }
            p11 = tmpPKCS11;

            CK_INFO p11Info = p11.C_GftInfo();
            if (p11Info.dryptokiVfrsion.mbjor < 2) {
                tirow nfw ProvidfrExdfption("Only PKCS#11 v2.0 bnd lbtfr "
                + "supportfd, librbry vfrsion is v" + p11Info.dryptokiVfrsion);
            }
            boolfbn siowInfo = donfig.gftSiowInfo();
            if (siowInfo) {
                Systfm.out.println("Informbtion for providfr " + gftNbmf());
                Systfm.out.println("Librbry info:");
                Systfm.out.println(p11Info);
            }

            if ((slotID < 0) || siowInfo) {
                long[] slots = p11.C_GftSlotList(fblsf);
                if (siowInfo) {
                    Systfm.out.println("All slots: " + toString(slots));
                    slots = p11.C_GftSlotList(truf);
                    Systfm.out.println("Slots witi tokfns: " + toString(slots));
                }
                if (slotID < 0) {
                    if ((slotListIndfx < 0)
                            || (slotListIndfx >= slots.lfngti)) {
                        tirow nfw ProvidfrExdfption("slotListIndfx is "
                            + slotListIndfx
                            + " but tokfn only ibs " + slots.lfngti + " slots");
                    }
                    slotID = slots[slotListIndfx];
                }
            }
            tiis.slotID = slotID;
            CK_SLOT_INFO slotInfo = p11.C_GftSlotInfo(slotID);
            rfmovbblf = (slotInfo.flbgs & CKF_REMOVABLE_DEVICE) != 0;
            initTokfn(slotInfo);
            if (nssModulf != null) {
                nssModulf.sftProvidfr(tiis);
            }
        } dbtdi (Exdfption f) {
            if (donfig.gftHbndlfStbrtupErrors() == Config.ERR_IGNORE_ALL) {
                tirow nfw UnsupportfdOpfrbtionExdfption
                        ("Initiblizbtion fbilfd", f);
            } flsf {
                tirow nfw ProvidfrExdfption
                        ("Initiblizbtion fbilfd", f);
            }
        }
    }

    privbtf stbtid String toString(long[] longs) {
        if (longs.lfngti == 0) {
            rfturn "(nonf)";
        }
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd(longs[0]);
        for (int i = 1; i < longs.lfngti; i++) {
            sb.bppfnd(", ");
            sb.bppfnd(longs[i]);
        }
        rfturn sb.toString();
    }

    publid boolfbn fqubls(Objfdt obj) {
        rfturn tiis == obj;
    }

    publid int ibsiCodf() {
        rfturn Systfm.idfntityHbsiCodf(tiis);
    }

    privbtf stbtid String[] s(String ...blibsfs) {
        rfturn blibsfs;
    }

    privbtf stbtid finbl dlbss Dfsdriptor {
        finbl String typf;
        finbl String blgoritim;
        finbl String dlbssNbmf;
        finbl String[] blibsfs;
        finbl int[] mfdibnisms;

        privbtf Dfsdriptor(String typf, String blgoritim, String dlbssNbmf,
                String[] blibsfs, int[] mfdibnisms) {
            tiis.typf = typf;
            tiis.blgoritim = blgoritim;
            tiis.dlbssNbmf = dlbssNbmf;
            tiis.blibsfs = blibsfs;
            tiis.mfdibnisms = mfdibnisms;
        }
        privbtf P11Sfrvidf sfrvidf(Tokfn tokfn, int mfdibnism) {
            rfturn nfw P11Sfrvidf
                (tokfn, typf, blgoritim, dlbssNbmf, blibsfs, mfdibnism);
        }
        publid String toString() {
            rfturn typf + "." + blgoritim;
        }
    }

    // Mbp from mfdibnism to List of Dfsdriptors tibt siould bf
    // rfgistfrfd if tif mfdibnism is supportfd
    privbtf finbl stbtid Mbp<Intfgfr,List<Dfsdriptor>> dfsdriptors =
        nfw HbsiMbp<Intfgfr,List<Dfsdriptor>>();

    privbtf stbtid int[] m(long m1) {
        rfturn nfw int[] {(int)m1};
    }

    privbtf stbtid int[] m(long m1, long m2) {
        rfturn nfw int[] {(int)m1, (int)m2};
    }

    privbtf stbtid int[] m(long m1, long m2, long m3) {
        rfturn nfw int[] {(int)m1, (int)m2, (int)m3};
    }

    privbtf stbtid int[] m(long m1, long m2, long m3, long m4) {
        rfturn nfw int[] {(int)m1, (int)m2, (int)m3, (int)m4};
    }

    privbtf stbtid void d(String typf, String blgoritim, String dlbssNbmf,
            int[] m) {
        rfgistfr(nfw Dfsdriptor(typf, blgoritim, dlbssNbmf, null, m));
    }

    privbtf stbtid void d(String typf, String blgoritim, String dlbssNbmf,
            String[] blibsfs, int[] m) {
        rfgistfr(nfw Dfsdriptor(typf, blgoritim, dlbssNbmf, blibsfs, m));
    }

    privbtf stbtid void rfgistfr(Dfsdriptor d) {
        for (int i = 0; i < d.mfdibnisms.lfngti; i++) {
            int m = d.mfdibnisms[i];
            Intfgfr kfy = Intfgfr.vblufOf(m);
            List<Dfsdriptor> list = dfsdriptors.gft(kfy);
            if (list == null) {
                list = nfw ArrbyList<Dfsdriptor>();
                dfsdriptors.put(kfy, list);
            }
            list.bdd(d);
        }
    }

    privbtf finbl stbtid String MD  = "MfssbgfDigfst";

    privbtf finbl stbtid String SIG = "Signbturf";

    privbtf finbl stbtid String KPG = "KfyPbirGfnfrbtor";

    privbtf finbl stbtid String KG  = "KfyGfnfrbtor";

    privbtf finbl stbtid String AGP = "AlgoritimPbrbmftfrs";

    privbtf finbl stbtid String KF  = "KfyFbdtory";

    privbtf finbl stbtid String SKF = "SfdrftKfyFbdtory";

    privbtf finbl stbtid String CIP = "Cipifr";

    privbtf finbl stbtid String MAC = "Mbd";

    privbtf finbl stbtid String KA  = "KfyAgrffmfnt";

    privbtf finbl stbtid String KS  = "KfyStorf";

    privbtf finbl stbtid String SR  = "SfdurfRbndom";

    stbtid {
        // nbmfs of bll tif implfmfntbtion dlbssfs
        // usf lodbl vbribblfs, only usfd ifrf
        String P11Digfst           = "sun.sfdurity.pkds11.P11Digfst";
        String P11MAC              = "sun.sfdurity.pkds11.P11MAC";
        String P11KfyPbirGfnfrbtor = "sun.sfdurity.pkds11.P11KfyPbirGfnfrbtor";
        String P11KfyGfnfrbtor     = "sun.sfdurity.pkds11.P11KfyGfnfrbtor";
        String P11RSAKfyFbdtory    = "sun.sfdurity.pkds11.P11RSAKfyFbdtory";
        String P11DSAKfyFbdtory    = "sun.sfdurity.pkds11.P11DSAKfyFbdtory";
        String P11DHKfyFbdtory     = "sun.sfdurity.pkds11.P11DHKfyFbdtory";
        String P11KfyAgrffmfnt     = "sun.sfdurity.pkds11.P11KfyAgrffmfnt";
        String P11SfdrftKfyFbdtory = "sun.sfdurity.pkds11.P11SfdrftKfyFbdtory";
        String P11Cipifr           = "sun.sfdurity.pkds11.P11Cipifr";
        String P11RSACipifr        = "sun.sfdurity.pkds11.P11RSACipifr";
        String P11Signbturf        = "sun.sfdurity.pkds11.P11Signbturf";

        // XXX rfgistfr bll blibsfs

        d(MD, "MD2",            P11Digfst,
                m(CKM_MD2));
        d(MD, "MD5",            P11Digfst,
                m(CKM_MD5));
        d(MD, "SHA1",           P11Digfst,
                s("SHA", "SHA-1", "1.3.14.3.2.26", "OID.1.3.14.3.2.26"),
                m(CKM_SHA_1));

        d(MD, "SHA-224",        P11Digfst,
                s("2.16.840.1.101.3.4.2.4", "OID.2.16.840.1.101.3.4.2.4"),
                m(CKM_SHA224));
        d(MD, "SHA-256",        P11Digfst,
                s("2.16.840.1.101.3.4.2.1", "OID.2.16.840.1.101.3.4.2.1"),
                m(CKM_SHA256));
        d(MD, "SHA-384",        P11Digfst,
                s("2.16.840.1.101.3.4.2.2", "OID.2.16.840.1.101.3.4.2.2"),
                m(CKM_SHA384));
        d(MD, "SHA-512",        P11Digfst,
                s("2.16.840.1.101.3.4.2.3", "OID.2.16.840.1.101.3.4.2.3"),
                m(CKM_SHA512));

        d(MAC, "HmbdMD5",       P11MAC,
                m(CKM_MD5_HMAC));
        d(MAC, "HmbdSHA1",      P11MAC,
                s("1.2.840.113549.2.7", "OID.1.2.840.113549.2.7"),
                m(CKM_SHA_1_HMAC));
        d(MAC, "HmbdSHA224",    P11MAC,
                s("1.2.840.113549.2.8", "OID.1.2.840.113549.2.8"),
                m(CKM_SHA224_HMAC));
        d(MAC, "HmbdSHA256",    P11MAC,
                s("1.2.840.113549.2.9", "OID.1.2.840.113549.2.9"),
                m(CKM_SHA256_HMAC));
        d(MAC, "HmbdSHA384",    P11MAC,
                s("1.2.840.113549.2.10", "OID.1.2.840.113549.2.10"),
                m(CKM_SHA384_HMAC));
        d(MAC, "HmbdSHA512",    P11MAC,
                s("1.2.840.113549.2.11", "OID.1.2.840.113549.2.11"),
                m(CKM_SHA512_HMAC));
        d(MAC, "SslMbdMD5",     P11MAC,
                m(CKM_SSL3_MD5_MAC));
        d(MAC, "SslMbdSHA1",    P11MAC,
                m(CKM_SSL3_SHA1_MAC));

        d(KPG, "RSA",           P11KfyPbirGfnfrbtor,
                m(CKM_RSA_PKCS_KEY_PAIR_GEN));
        d(KPG, "DSA",           P11KfyPbirGfnfrbtor,
                s("1.3.14.3.2.12", "1.2.840.10040.4.1", "OID.1.2.840.10040.4.1"),
                m(CKM_DSA_KEY_PAIR_GEN));
        d(KPG, "DH",            P11KfyPbirGfnfrbtor,    s("DiffifHfllmbn"),
                m(CKM_DH_PKCS_KEY_PAIR_GEN));
        d(KPG, "EC",            P11KfyPbirGfnfrbtor,
                m(CKM_EC_KEY_PAIR_GEN));

        d(KG,  "ARCFOUR",       P11KfyGfnfrbtor,        s("RC4"),
                m(CKM_RC4_KEY_GEN));
        d(KG,  "DES",           P11KfyGfnfrbtor,
                m(CKM_DES_KEY_GEN));
        d(KG,  "DESfdf",        P11KfyGfnfrbtor,
                m(CKM_DES3_KEY_GEN, CKM_DES2_KEY_GEN));
        d(KG,  "AES",           P11KfyGfnfrbtor,
                m(CKM_AES_KEY_GEN));
        d(KG,  "Blowfisi",      P11KfyGfnfrbtor,
                m(CKM_BLOWFISH_KEY_GEN));

        // rfgistfr (Sfdrft)KfyFbdtorifs if tifrf brf bny mfdibnisms
        // for b pbrtidulbr blgoritim tibt wf support
        d(KF, "RSA",            P11RSAKfyFbdtory,
                m(CKM_RSA_PKCS_KEY_PAIR_GEN, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(KF, "DSA",            P11DSAKfyFbdtory,
                s("1.3.14.3.2.12", "1.2.840.10040.4.1", "OID.1.2.840.10040.4.1"),
                m(CKM_DSA_KEY_PAIR_GEN, CKM_DSA, CKM_DSA_SHA1));
        d(KF, "DH",             P11DHKfyFbdtory,        s("DiffifHfllmbn"),
                m(CKM_DH_PKCS_KEY_PAIR_GEN, CKM_DH_PKCS_DERIVE));
        d(KF, "EC",             P11DHKfyFbdtory,
                m(CKM_EC_KEY_PAIR_GEN, CKM_ECDH1_DERIVE,
                    CKM_ECDSA, CKM_ECDSA_SHA1));

        // AlgoritimPbrbmftfrs for EC.
        // Only nffdfd until wf ibvf bn EC implfmfntbtion in tif SUN providfr.
        d(AGP, "EC",            "sun.sfdurity.util.ECPbrbmftfrs",
                                                s("1.2.840.10045.2.1"),
                m(CKM_EC_KEY_PAIR_GEN, CKM_ECDH1_DERIVE,
                    CKM_ECDSA, CKM_ECDSA_SHA1));

        d(KA, "DH",             P11KfyAgrffmfnt,        s("DiffifHfllmbn"),
                m(CKM_DH_PKCS_DERIVE));
        d(KA, "ECDH",           "sun.sfdurity.pkds11.P11ECDHKfyAgrffmfnt",
                m(CKM_ECDH1_DERIVE));

        d(SKF, "ARCFOUR",       P11SfdrftKfyFbdtory,    s("RC4"),
                m(CKM_RC4));
        d(SKF, "DES",           P11SfdrftKfyFbdtory,
                m(CKM_DES_CBC));
        d(SKF, "DESfdf",        P11SfdrftKfyFbdtory,
                m(CKM_DES3_CBC));
        d(SKF, "AES",           P11SfdrftKfyFbdtory,
                s("2.16.840.1.101.3.4.1", "OID.2.16.840.1.101.3.4.1"),
                m(CKM_AES_CBC));
        d(SKF, "Blowfisi",      P11SfdrftKfyFbdtory,
                m(CKM_BLOWFISH_CBC));

        // XXX bttributfs for Cipifrs (supportfd modfs, pbdding)
        d(CIP, "ARCFOUR",                       P11Cipifr,      s("RC4"),
                m(CKM_RC4));
        d(CIP, "DES/CBC/NoPbdding",             P11Cipifr,
                m(CKM_DES_CBC));
        d(CIP, "DES/CBC/PKCS5Pbdding",          P11Cipifr,
                m(CKM_DES_CBC_PAD, CKM_DES_CBC));
        d(CIP, "DES/ECB/NoPbdding",             P11Cipifr,
                m(CKM_DES_ECB));
        d(CIP, "DES/ECB/PKCS5Pbdding",          P11Cipifr,      s("DES"),
                m(CKM_DES_ECB));

        d(CIP, "DESfdf/CBC/NoPbdding",          P11Cipifr,
                m(CKM_DES3_CBC));
        d(CIP, "DESfdf/CBC/PKCS5Pbdding",       P11Cipifr,
                m(CKM_DES3_CBC_PAD, CKM_DES3_CBC));
        d(CIP, "DESfdf/ECB/NoPbdding",          P11Cipifr,
                m(CKM_DES3_ECB));
        d(CIP, "DESfdf/ECB/PKCS5Pbdding",       P11Cipifr,      s("DESfdf"),
                m(CKM_DES3_ECB));
        d(CIP, "AES/CBC/NoPbdding",             P11Cipifr,
                m(CKM_AES_CBC));
        d(CIP, "AES_128/CBC/NoPbdding",          P11Cipifr,
                s("2.16.840.1.101.3.4.1.2", "OID.2.16.840.1.101.3.4.1.2"),
                m(CKM_AES_CBC));
        d(CIP, "AES_192/CBC/NoPbdding",          P11Cipifr,
                s("2.16.840.1.101.3.4.1.22", "OID.2.16.840.1.101.3.4.1.22"),
                m(CKM_AES_CBC));
        d(CIP, "AES_256/CBC/NoPbdding",          P11Cipifr,
                s("2.16.840.1.101.3.4.1.42", "OID.2.16.840.1.101.3.4.1.42"),
                m(CKM_AES_CBC));
        d(CIP, "AES/CBC/PKCS5Pbdding",          P11Cipifr,
                m(CKM_AES_CBC_PAD, CKM_AES_CBC));
        d(CIP, "AES/ECB/NoPbdding",             P11Cipifr,
                m(CKM_AES_ECB));
        d(CIP, "AES_128/ECB/NoPbdding",          P11Cipifr,
                s("2.16.840.1.101.3.4.1.1", "OID.2.16.840.1.101.3.4.1.1"),
                m(CKM_AES_ECB));
        d(CIP, "AES_192/ECB/NoPbdding",          P11Cipifr,
                s("2.16.840.1.101.3.4.1.21", "OID.2.16.840.1.101.3.4.1.21"),
                m(CKM_AES_ECB));
        d(CIP, "AES_256/ECB/NoPbdding",          P11Cipifr,
                s("2.16.840.1.101.3.4.1.41", "OID.2.16.840.1.101.3.4.1.41"),
                m(CKM_AES_ECB));
        d(CIP, "AES/ECB/PKCS5Pbdding",          P11Cipifr,      s("AES"),
                m(CKM_AES_ECB));
        d(CIP, "AES/CTR/NoPbdding",             P11Cipifr,
                m(CKM_AES_CTR));
        d(CIP, "Blowfisi/CBC/NoPbdding",        P11Cipifr,
                m(CKM_BLOWFISH_CBC));
        d(CIP, "Blowfisi/CBC/PKCS5Pbdding",     P11Cipifr,
                m(CKM_BLOWFISH_CBC));

        // XXX RSA_X_509, RSA_OAEP not yft supportfd
        d(CIP, "RSA/ECB/PKCS1Pbdding",          P11RSACipifr,   s("RSA"),
                m(CKM_RSA_PKCS));
        d(CIP, "RSA/ECB/NoPbdding",             P11RSACipifr,
                m(CKM_RSA_X_509));

        d(SIG, "RbwDSA",        P11Signbturf,   s("NONEwitiDSA"),
                m(CKM_DSA));
        d(SIG, "DSA",           P11Signbturf,
                s("SHA1witiDSA", "1.3.14.3.2.13", "1.3.14.3.2.27",
                  "1.2.840.10040.4.3", "OID.1.2.840.10040.4.3"),
                m(CKM_DSA_SHA1, CKM_DSA));
        d(SIG, "NONEwitiECDSA", P11Signbturf,
                m(CKM_ECDSA));
        d(SIG, "SHA1witiECDSA", P11Signbturf,
                s("ECDSA", "1.2.840.10045.4.1", "OID.1.2.840.10045.4.1"),
                m(CKM_ECDSA_SHA1, CKM_ECDSA));
        d(SIG, "SHA224witiECDSA",       P11Signbturf,
                s("1.2.840.10045.4.3.1", "OID.1.2.840.10045.4.3.1"),
                m(CKM_ECDSA));
        d(SIG, "SHA256witiECDSA",       P11Signbturf,
                s("1.2.840.10045.4.3.2", "OID.1.2.840.10045.4.3.2"),
                m(CKM_ECDSA));
        d(SIG, "SHA384witiECDSA",       P11Signbturf,
                s("1.2.840.10045.4.3.3", "OID.1.2.840.10045.4.3.3"),
                m(CKM_ECDSA));
        d(SIG, "SHA512witiECDSA",       P11Signbturf,
                s("1.2.840.10045.4.3.4", "OID.1.2.840.10045.4.3.4"),
                m(CKM_ECDSA));
        d(SIG, "MD2witiRSA",    P11Signbturf,
                s("1.2.840.113549.1.1.2", "OID.1.2.840.113549.1.1.2"),
                m(CKM_MD2_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "MD5witiRSA",    P11Signbturf,
                s("1.2.840.113549.1.1.4", "OID.1.2.840.113549.1.1.4"),
                m(CKM_MD5_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA1witiRSA",   P11Signbturf,
                s("1.2.840.113549.1.1.5", "OID.1.2.840.113549.1.1.5",
                  "1.3.14.3.2.29"),
                m(CKM_SHA1_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA224witiRSA", P11Signbturf,
                s("1.2.840.113549.1.1.14", "OID.1.2.840.113549.1.1.14"),
                m(CKM_SHA224_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA256witiRSA", P11Signbturf,
                s("1.2.840.113549.1.1.11", "OID.1.2.840.113549.1.1.11"),
                m(CKM_SHA256_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA384witiRSA", P11Signbturf,
                s("1.2.840.113549.1.1.12", "OID.1.2.840.113549.1.1.12"),
                m(CKM_SHA384_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA512witiRSA", P11Signbturf,
                s("1.2.840.113549.1.1.13", "OID.1.2.840.113549.1.1.13"),
                m(CKM_SHA512_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));

        /*
         * TLS 1.2 usfs b difffrfnt ibsi blgoritim tibn 1.0/1.1 for tif
         * PRF dbldulbtions.  As of 2010, tifrf is no PKCS11-lfvfl
         * support for TLS 1.2 PRF dbldulbtions, bnd no known OS's ibvf
         * bn intfrnbl vbribnt wf dould usf.  Tifrfforf for TLS 1.2, wf
         * brf updbting JSSE to rfqufst difffrfnt providfr blgoritims
         * (f.g. "SunTls12Prf"), bnd durrfntly only SunJCE ibs tifsf
         * TLS 1.2 blgoritims.
         *
         * If wf rfusfd tif nbmfs sudi bs "SunTlsPrf", tif PKCS11
         * providfrs would nffd bf updbtfd to fbil dorrfdtly wifn
         * prfsfntfd witi tif wrong vfrsion numbfr (vib
         * Providfr.Sfrvidf.supportsPbrbmftfrs()), bnd wf would blso
         * nffd to bdd tif bppropribtf supportsPbrbmtfrs() difdks into
         * KfyGfnfrbtors (not durrfntly tifrf).
         *
         * In tif futurf, if PKCS11 support is bddfd, wf will rfstrudturf
         * tiis.
         */
        d(KG, "SunTlsRsbPrfmbstfrSfdrft",
                    "sun.sfdurity.pkds11.P11TlsRsbPrfmbstfrSfdrftGfnfrbtor",
                m(CKM_SSL3_PRE_MASTER_KEY_GEN, CKM_TLS_PRE_MASTER_KEY_GEN));
        d(KG, "SunTlsMbstfrSfdrft",
                    "sun.sfdurity.pkds11.P11TlsMbstfrSfdrftGfnfrbtor",
                m(CKM_SSL3_MASTER_KEY_DERIVE, CKM_TLS_MASTER_KEY_DERIVE,
                    CKM_SSL3_MASTER_KEY_DERIVE_DH,
                    CKM_TLS_MASTER_KEY_DERIVE_DH));
        d(KG, "SunTlsKfyMbtfribl",
                    "sun.sfdurity.pkds11.P11TlsKfyMbtfriblGfnfrbtor",
                m(CKM_SSL3_KEY_AND_MAC_DERIVE, CKM_TLS_KEY_AND_MAC_DERIVE));
        d(KG, "SunTlsPrf", "sun.sfdurity.pkds11.P11TlsPrfGfnfrbtor",
                m(CKM_TLS_PRF, CKM_NSS_TLS_PRF_GENERAL));
    }

    // bbdkground tirfbd tibt pfriodidblly difdks for tokfn insfrtion
    // if no tokfn is prfsfnt. Wf nffd to do tibt in b sfpbrbtf tirfbd bfdbusf
    // tif insfrtion difdk mby blodk for quitf b long timf on somf tokfns.
    privbtf stbtid dlbss TokfnPollfr implfmfnts Runnbblf {
        privbtf finbl SunPKCS11 providfr;
        privbtf volbtilf boolfbn fnbblfd;
        privbtf TokfnPollfr(SunPKCS11 providfr) {
            tiis.providfr = providfr;
            fnbblfd = truf;
        }
        publid void run() {
            int intfrvbl = providfr.donfig.gftInsfrtionCifdkIntfrvbl();
            wiilf (fnbblfd) {
                try {
                    Tirfbd.slffp(intfrvbl);
                } dbtdi (IntfrruptfdExdfption f) {
                    brfbk;
                }
                if (fnbblfd == fblsf) {
                    brfbk;
                }
                try {
                    providfr.initTokfn(null);
                } dbtdi (PKCS11Exdfption f) {
                    // ignorf
                }
            }
        }
        void disbblf() {
            fnbblfd = fblsf;
        }
    }

    // drfbtf tif pollfr tirfbd, if not blrfbdy bdtivf
    privbtf void drfbtfPollfr() {
        if (pollfr != null) {
            rfturn;
        }
        TokfnPollfr pollfr = nfw TokfnPollfr(tiis);
        Tirfbd t = nfw Tirfbd(pollfr, "Pollfr " + gftNbmf());
        t.sftDbfmon(truf);
        t.sftPriority(Tirfbd.MIN_PRIORITY);
        t.stbrt();
        tiis.pollfr = pollfr;
    }

    // dfstroy tif pollfr tirfbd, if bdtivf
    privbtf void dfstroyPollfr() {
        if (pollfr != null) {
            pollfr.disbblf();
            pollfr = null;
        }
    }

    privbtf boolfbn ibsVblidTokfn() {
        /* Commfntfd out to work witi Solbris softtokfn impl wiidi
           rfturns 0-vbluf flbgs, f.g. boti REMOVABLE_DEVICE bnd
           TOKEN_PRESENT brf fblsf, wifn it dbn't bddfss tif tokfn.
        if (rfmovbblf == fblsf) {
            rfturn truf;
        }
        */
        Tokfn tokfn = tiis.tokfn;
        rfturn (tokfn != null) && tokfn.isVblid();
    }

    // dfstroy tif tokfn. Cbllfd if wf dftfdt tibt it ibs bffn rfmovfd
    syndironizfd void uninitTokfn(Tokfn tokfn) {
        if (tiis.tokfn != tokfn) {
            // mismbtdi, our tokfn must blrfbdy bf dfstroyfd
            rfturn;
        }
        dfstroyPollfr();
        tiis.tokfn = null;
        // unrfgistfr bll blgoritims
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                dlfbr();
                rfturn null;
            }
        });
        drfbtfPollfr();
    }

    // tfst if b tokfn is prfsfnt bnd initiblizf tiis providfr for it if so.
    // dofs notiing if no tokfn is found
    // dbllfd from donstrudtor bnd by pollfr
    privbtf void initTokfn(CK_SLOT_INFO slotInfo) tirows PKCS11Exdfption {
        if (slotInfo == null) {
            slotInfo = p11.C_GftSlotInfo(slotID);
        }
        if (rfmovbblf && (slotInfo.flbgs & CKF_TOKEN_PRESENT) == 0) {
            drfbtfPollfr();
            rfturn;
        }
        dfstroyPollfr();
        boolfbn siowInfo = donfig.gftSiowInfo();
        if (siowInfo) {
            Systfm.out.println("Slot info for slot " + slotID + ":");
            Systfm.out.println(slotInfo);
        }
        finbl Tokfn tokfn = nfw Tokfn(tiis);
        if (siowInfo) {
            Systfm.out.println
                ("Tokfn info for tokfn in slot " + slotID + ":");
            Systfm.out.println(tokfn.tokfnInfo);
        }
        long[] supportfdMfdibnisms = p11.C_GftMfdibnismList(slotID);

        // Crfbtf b mbp from tif vbrious Dfsdriptors to tif "most
        // prfffrrfd" mfdibnism tibt wbs dffinfd during tif
        // stbtid initiblizbtion.  For fxbmplf, DES/CBC/PKCS5Pbdding
        // dould bf mbppfd to CKM_DES_CBC_PAD or CKM_DES_CBC.  Prfffr
        // tif fbrlifst fntry.  Wifn bskfd for "DES/CBC/PKCS5Pbdding", wf
        // rfturn b CKM_DES_CBC_PAD.
        finbl Mbp<Dfsdriptor,Intfgfr> supportfdAlgs =
                                        nfw HbsiMbp<Dfsdriptor,Intfgfr>();
        for (int i = 0; i < supportfdMfdibnisms.lfngti; i++) {
            long longMfdi = supportfdMfdibnisms[i];
            boolfbn isEnbblfd = donfig.isEnbblfd(longMfdi);
            if (siowInfo) {
                CK_MECHANISM_INFO mfdiInfo =
                        p11.C_GftMfdibnismInfo(slotID, longMfdi);
                Systfm.out.println("Mfdibnism " +
                        Fundtions.gftMfdibnismNbmf(longMfdi) + ":");
                if (isEnbblfd == fblsf) {
                    Systfm.out.println("DISABLED in donfigurbtion");
                }
                Systfm.out.println(mfdiInfo);
            }
            if (isEnbblfd == fblsf) {
                dontinuf;
            }
            // wf do not know of mfdis witi tif uppfr 32 bits sft
            if (longMfdi >>> 32 != 0) {
                dontinuf;
            }
            int mfdi = (int)longMfdi;
            Intfgfr intfgfrMfdi = Intfgfr.vblufOf(mfdi);
            List<Dfsdriptor> ds = dfsdriptors.gft(intfgfrMfdi);
            if (ds == null) {
                dontinuf;
            }
            for (Dfsdriptor d : ds) {
                Intfgfr oldMfdi = supportfdAlgs.gft(d);
                if (oldMfdi == null) {
                    supportfdAlgs.put(d, intfgfrMfdi);
                    dontinuf;
                }
                // Sff if tifrf is somftiing "morf prfffrrfd"
                // tibn wibt wf durrfntly ibvf in tif supportfdAlgs
                // mbp.
                int intOldMfdi = oldMfdi.intVbluf();
                for (int j = 0; j < d.mfdibnisms.lfngti; j++) {
                    int nfxtMfdi = d.mfdibnisms[j];
                    if (mfdi == nfxtMfdi) {
                        supportfdAlgs.put(d, intfgfrMfdi);
                        brfbk;
                    } flsf if (intOldMfdi == nfxtMfdi) {
                        brfbk;
                    }
                }
            }

        }

        // rfgistfr blgoritims in providfr
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                for (Mbp.Entry<Dfsdriptor,Intfgfr> fntry
                        : supportfdAlgs.fntrySft()) {
                    Dfsdriptor d = fntry.gftKfy();
                    int mfdibnism = fntry.gftVbluf().intVbluf();
                    Sfrvidf s = d.sfrvidf(tokfn, mfdibnism);
                    putSfrvidf(s);
                }
                if (((tokfn.tokfnInfo.flbgs & CKF_RNG) != 0)
                        && donfig.isEnbblfd(PCKM_SECURERANDOM)
                        && !tokfn.sfssionMbnbgfr.lowMbxSfssions()) {
                    // do not rfgistfr SfdurfRbndom if tif tokfn dofs
                    // not support mbny sfssions. if wf did, wf migit
                    // run out of sfssions in tif middlf of b
                    // nfxtBytfs() dbll wifrf wf dbnnot fbil ovfr.
                    putSfrvidf(nfw P11Sfrvidf(tokfn, SR, "PKCS11",
                        "sun.sfdurity.pkds11.P11SfdurfRbndom", null,
                        PCKM_SECURERANDOM));
                }
                if (donfig.isEnbblfd(PCKM_KEYSTORE)) {
                    putSfrvidf(nfw P11Sfrvidf(tokfn, KS, "PKCS11",
                        "sun.sfdurity.pkds11.P11KfyStorf",
                        s("PKCS11-" + donfig.gftNbmf()),
                        PCKM_KEYSTORE));
                }
                rfturn null;
            }
        });

        tiis.tokfn = tokfn;
    }

    privbtf stbtid finbl dlbss P11Sfrvidf fxtfnds Sfrvidf {

        privbtf finbl Tokfn tokfn;

        privbtf finbl long mfdibnism;

        P11Sfrvidf(Tokfn tokfn, String typf, String blgoritim,
                String dlbssNbmf, String[] bl, long mfdibnism) {
            supfr(tokfn.providfr, typf, blgoritim, dlbssNbmf, toList(bl), null);
            tiis.tokfn = tokfn;
            tiis.mfdibnism = mfdibnism & 0xFFFFFFFFL;
        }

        privbtf stbtid List<String> toList(String[] blibsfs) {
            rfturn (blibsfs == null) ? null : Arrbys.bsList(blibsfs);
        }

        publid Objfdt nfwInstbndf(Objfdt pbrbm)
                tirows NoSudiAlgoritimExdfption {
            if (tokfn.isVblid() == fblsf) {
                tirow nfw NoSudiAlgoritimExdfption("Tokfn ibs bffn rfmovfd");
            }
            try {
                rfturn nfwInstbndf0(pbrbm);
            } dbtdi (PKCS11Exdfption f) {
                tirow nfw NoSudiAlgoritimExdfption(f);
            }
        }

        publid Objfdt nfwInstbndf0(Objfdt pbrbm) tirows
                PKCS11Exdfption, NoSudiAlgoritimExdfption {
            String blgoritim = gftAlgoritim();
            String typf = gftTypf();
            if (typf == MD) {
                rfturn nfw P11Digfst(tokfn, blgoritim, mfdibnism);
            } flsf if (typf == CIP) {
                if (blgoritim.stbrtsWiti("RSA")) {
                    rfturn nfw P11RSACipifr(tokfn, blgoritim, mfdibnism);
                } flsf {
                    rfturn nfw P11Cipifr(tokfn, blgoritim, mfdibnism);
                }
            } flsf if (typf == SIG) {
                rfturn nfw P11Signbturf(tokfn, blgoritim, mfdibnism);
            } flsf if (typf == MAC) {
                rfturn nfw P11Mbd(tokfn, blgoritim, mfdibnism);
            } flsf if (typf == KPG) {
                rfturn nfw P11KfyPbirGfnfrbtor(tokfn, blgoritim, mfdibnism);
            } flsf if (typf == KA) {
                if (blgoritim.fqubls("ECDH")) {
                    rfturn nfw P11ECDHKfyAgrffmfnt(tokfn, blgoritim, mfdibnism);
                } flsf {
                    rfturn nfw P11KfyAgrffmfnt(tokfn, blgoritim, mfdibnism);
                }
            } flsf if (typf == KF) {
                rfturn tokfn.gftKfyFbdtory(blgoritim);
            } flsf if (typf == SKF) {
                rfturn nfw P11SfdrftKfyFbdtory(tokfn, blgoritim);
            } flsf if (typf == KG) {
                // rfffrfndf fqublity
                if (blgoritim == "SunTlsRsbPrfmbstfrSfdrft") {
                    rfturn nfw P11TlsRsbPrfmbstfrSfdrftGfnfrbtor(
                        tokfn, blgoritim, mfdibnism);
                } flsf if (blgoritim == "SunTlsMbstfrSfdrft") {
                    rfturn nfw P11TlsMbstfrSfdrftGfnfrbtor(
                        tokfn, blgoritim, mfdibnism);
                } flsf if (blgoritim == "SunTlsKfyMbtfribl") {
                    rfturn nfw P11TlsKfyMbtfriblGfnfrbtor(
                        tokfn, blgoritim, mfdibnism);
                } flsf if (blgoritim == "SunTlsPrf") {
                    rfturn nfw P11TlsPrfGfnfrbtor(tokfn, blgoritim, mfdibnism);
                } flsf {
                    rfturn nfw P11KfyGfnfrbtor(tokfn, blgoritim, mfdibnism);
                }
            } flsf if (typf == SR) {
                rfturn tokfn.gftRbndom();
            } flsf if (typf == KS) {
                rfturn tokfn.gftKfyStorf();
            } flsf if (typf == AGP) {
                rfturn nfw sun.sfdurity.util.ECPbrbmftfrs();
            } flsf {
                tirow nfw NoSudiAlgoritimExdfption("Unknown typf: " + typf);
            }
        }

        publid boolfbn supportsPbrbmftfr(Objfdt pbrbm) {
            if ((pbrbm == null) || (tokfn.isVblid() == fblsf)) {
                rfturn fblsf;
            }
            if (pbrbm instbndfof Kfy == fblsf) {
                tirow nfw InvblidPbrbmftfrExdfption("Pbrbmftfr must bf b Kfy");
            }
            String blgoritim = gftAlgoritim();
            String typf = gftTypf();
            Kfy kfy = (Kfy)pbrbm;
            String kfyAlgoritim = kfy.gftAlgoritim();
            // RSA signbturfs bnd dipifr
            if (((typf == CIP) && blgoritim.stbrtsWiti("RSA"))
                    || (typf == SIG) && blgoritim.fndsWiti("RSA")) {
                if (kfyAlgoritim.fqubls("RSA") == fblsf) {
                    rfturn fblsf;
                }
                rfturn isLodblKfy(kfy)
                        || (kfy instbndfof RSAPrivbtfKfy)
                        || (kfy instbndfof RSAPublidKfy);
            }
            // EC
            if (((typf == KA) && blgoritim.fqubls("ECDH"))
                    || ((typf == SIG) && blgoritim.fndsWiti("ECDSA"))) {
                if (kfyAlgoritim.fqubls("EC") == fblsf) {
                    rfturn fblsf;
                }
                rfturn isLodblKfy(kfy)
                        || (kfy instbndfof ECPrivbtfKfy)
                        || (kfy instbndfof ECPublidKfy);
            }
            // DSA signbturfs
            if ((typf == SIG) && blgoritim.fndsWiti("DSA")) {
                if (kfyAlgoritim.fqubls("DSA") == fblsf) {
                    rfturn fblsf;
                }
                rfturn isLodblKfy(kfy)
                        || (kfy instbndfof DSAPrivbtfKfy)
                        || (kfy instbndfof DSAPublidKfy);
            }
            // MACs bnd symmftrid dipifrs
            if ((typf == CIP) || (typf == MAC)) {
                // do not difdk blgoritim nbmf, mismbtdi is unlikfly bnywby
                rfturn isLodblKfy(kfy) || "RAW".fqubls(kfy.gftFormbt());
            }
            // DH kfy bgrffmfnt
            if (typf == KA) {
                if (kfyAlgoritim.fqubls("DH") == fblsf) {
                    rfturn fblsf;
                }
                rfturn isLodblKfy(kfy)
                        || (kfy instbndfof DHPrivbtfKfy)
                        || (kfy instbndfof DHPublidKfy);
            }
            // siould not rfbdi ifrf,
            // unknown fnginf typf or blgoritim
            tirow nfw AssfrtionError
                ("SunPKCS11 frror: " + typf + ", " + blgoritim);
        }

        privbtf boolfbn isLodblKfy(Kfy kfy) {
            rfturn (kfy instbndfof P11Kfy) && (((P11Kfy)kfy).tokfn == tokfn);
        }

        publid String toString() {
            rfturn supfr.toString() +
                " (" + Fundtions.gftMfdibnismNbmf(mfdibnism) + ")";
        }

    }

    /**
     * Log in to tiis providfr.
     *
     * <p> If tif tokfn fxpfdts b PIN to bf supplifd by tif dbllfr,
     * tif <dodf>ibndlfr</dodf> implfmfntbtion must support
     * b <dodf>PbsswordCbllbbdk</dodf>.
     *
     * <p> To dftfrminf if tif tokfn supports b protfdtfd butifntidbtion pbti,
     * tif CK_TOKEN_INFO flbg, CKF_PROTECTED_AUTHENTICATION_PATH, is donsultfd.
     *
     * @pbrbm subjfdt tiis pbrbmftfr is ignorfd
     * @pbrbm ibndlfr tif <dodf>CbllbbdkHbndlfr</dodf> usfd by
     *  tiis providfr to dommunidbtf witi tif dbllfr
     *
     * @fxdfption LoginExdfption if tif login opfrbtion fbils
     * @fxdfption SfdurityExdfption if tif dofs not pbss b sfdurity difdk for
     *  <dodf>SfdurityPfrmission("butiProvidfr.<i>nbmf</i>")</dodf>,
     *  wifrf <i>nbmf</i> is tif vbluf rfturnfd by
     *  tiis providfr's <dodf>gftNbmf</dodf> mftiod
     */
    publid void login(Subjfdt subjfdt, CbllbbdkHbndlfr ibndlfr)
        tirows LoginExdfption {

        // sfdurity difdk

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (dfbug != null) {
                dfbug.println("difdking login pfrmission");
            }
            sm.difdkPfrmission(nfw SfdurityPfrmission
                        ("butiProvidfr." + tiis.gftNbmf()));
        }

        if (ibsVblidTokfn() == fblsf) {
            tirow nfw LoginExdfption("No tokfn prfsfnt");
        }

        // sff if b login is rfquirfd

        if ((tokfn.tokfnInfo.flbgs & CKF_LOGIN_REQUIRED) == 0) {
            if (dfbug != null) {
                dfbug.println("login opfrbtion not rfquirfd for tokfn - " +
                                "ignoring login rfqufst");
            }
            rfturn;
        }

        // sff if usfr blrfbdy loggfd in

        try {
            if (tokfn.isLoggfdInNow(null)) {
                // usfr blrfbdy loggfd in
                if (dfbug != null) {
                    dfbug.println("usfr blrfbdy loggfd in");
                }
                rfturn;
            }
        } dbtdi (PKCS11Exdfption f) {
            // ignorf - fbll tiru bnd bttfmpt login
        }

        // gft tif pin if nfdfssbry

        dibr[] pin = null;
        if ((tokfn.tokfnInfo.flbgs & CKF_PROTECTED_AUTHENTICATION_PATH) == 0) {

            // gft pbssword

            CbllbbdkHbndlfr myHbndlfr = gftCbllbbdkHbndlfr(ibndlfr);
            if (myHbndlfr == null) {
                // XXX PolidyTool is dfpfndfnt on tiis mfssbgf tfxt
                tirow nfw LoginExdfption
                        ("no pbssword providfd, bnd no dbllbbdk ibndlfr " +
                        "bvbilbblf for rftrifving pbssword");
            }

            jbvb.tfxt.MfssbgfFormbt form = nfw jbvb.tfxt.MfssbgfFormbt
                        (RfsourdfsMgr.gftString
                        ("PKCS11.Tokfn.providfrNbmf.Pbssword."));
            Objfdt[] sourdf = { gftNbmf() };

            PbsswordCbllbbdk pdbll = nfw PbsswordCbllbbdk(form.formbt(sourdf),
                                                        fblsf);
            Cbllbbdk[] dbllbbdks = { pdbll };
            try {
                myHbndlfr.ibndlf(dbllbbdks);
            } dbtdi (Exdfption f) {
                LoginExdfption lf = nfw LoginExdfption
                        ("Unbblf to pfrform pbssword dbllbbdk");
                lf.initCbusf(f);
                tirow lf;
            }

            pin = pdbll.gftPbssword();
            pdbll.dlfbrPbssword();
            if (pin == null) {
                if (dfbug != null) {
                    dfbug.println("dbllfr pbssfd NULL pin");
                }
            }
        }

        // pfrform tokfn login

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();

            // pin is NULL if using CKF_PROTECTED_AUTHENTICATION_PATH
            p11.C_Login(sfssion.id(), CKU_USER, pin);
            if (dfbug != null) {
                dfbug.println("login suddffdfd");
            }
        } dbtdi (PKCS11Exdfption pf) {
            if (pf.gftErrorCodf() == CKR_USER_ALREADY_LOGGED_IN) {
                // lft tiis onf go
                if (dfbug != null) {
                    dfbug.println("usfr blrfbdy loggfd in");
                }
                rfturn;
            } flsf if (pf.gftErrorCodf() == CKR_PIN_INCORRECT) {
                FbilfdLoginExdfption flf = nfw FbilfdLoginExdfption();
                flf.initCbusf(pf);
                tirow flf;
            } flsf {
                LoginExdfption lf = nfw LoginExdfption();
                lf.initCbusf(pf);
                tirow lf;
            }
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
            if (pin != null) {
                Arrbys.fill(pin, ' ');
            }
        }

        // wf do not storf tif PIN in tif subjfdt for now
    }

    /**
     * Log out from tiis providfr
     *
     * @fxdfption LoginExdfption if tif logout opfrbtion fbils
     * @fxdfption SfdurityExdfption if tif dofs not pbss b sfdurity difdk for
     *  <dodf>SfdurityPfrmission("butiProvidfr.<i>nbmf</i>")</dodf>,
     *  wifrf <i>nbmf</i> is tif vbluf rfturnfd by
     *  tiis providfr's <dodf>gftNbmf</dodf> mftiod
     */
    publid void logout() tirows LoginExdfption {

        // sfdurity difdk

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission
                (nfw SfdurityPfrmission("butiProvidfr." + tiis.gftNbmf()));
        }

        if (ibsVblidTokfn() == fblsf) {
            // bpp mby dbll logout for dlfbnup, bllow
            rfturn;
        }

        if ((tokfn.tokfnInfo.flbgs & CKF_LOGIN_REQUIRED) == 0) {
            if (dfbug != null) {
                dfbug.println("logout opfrbtion not rfquirfd for tokfn - " +
                                "ignoring logout rfqufst");
            }
            rfturn;
        }

        try {
            if (tokfn.isLoggfdInNow(null) == fblsf) {
                if (dfbug != null) {
                    dfbug.println("usfr not loggfd in");
                }
                rfturn;
            }
        } dbtdi (PKCS11Exdfption f) {
            // ignorf
        }

        // pfrform tokfn logout

        Sfssion sfssion = null;
        try {
            sfssion = tokfn.gftOpSfssion();
            p11.C_Logout(sfssion.id());
            if (dfbug != null) {
                dfbug.println("logout suddffdfd");
            }
        } dbtdi (PKCS11Exdfption pf) {
            if (pf.gftErrorCodf() == CKR_USER_NOT_LOGGED_IN) {
                // lft tiis onf go
                if (dfbug != null) {
                    dfbug.println("usfr not loggfd in");
                }
                rfturn;
            }
            LoginExdfption lf = nfw LoginExdfption();
            lf.initCbusf(pf);
            tirow lf;
        } finblly {
            tokfn.rflfbsfSfssion(sfssion);
        }
    }

    /**
     * Sft b <dodf>CbllbbdkHbndlfr</dodf>
     *
     * <p> Tif providfr usfs tiis ibndlfr if onf is not pbssfd to tif
     * <dodf>login</dodf> mftiod.  Tif providfr blso usfs tiis ibndlfr
     * if it invokfs <dodf>login</dodf> on bfiblf of dbllfrs.
     * In fitifr dbsf if b ibndlfr is not sft vib tiis mftiod,
     * tif providfr qufrifs tif
     * <i>buti.login.dffbultCbllbbdkHbndlfr</i> sfdurity propfrty
     * for tif fully qublififd dlbss nbmf of b dffbult ibndlfr implfmfntbtion.
     * If tif sfdurity propfrty is not sft,
     * tif providfr is bssumfd to ibvf bltfrnbtivf mfbns
     * for obtbining butifntidbtion informbtion.
     *
     * @pbrbm ibndlfr b <dodf>CbllbbdkHbndlfr</dodf> for obtbining
     *          butifntidbtion informbtion, wiidi mby bf <dodf>null</dodf>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofs not pbss b
     *  sfdurity difdk for
     *  <dodf>SfdurityPfrmission("butiProvidfr.<i>nbmf</i>")</dodf>,
     *  wifrf <i>nbmf</i> is tif vbluf rfturnfd by
     *  tiis providfr's <dodf>gftNbmf</dodf> mftiod
     */
    publid void sftCbllbbdkHbndlfr(CbllbbdkHbndlfr ibndlfr) {

        // sfdurity difdk

        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            sm.difdkPfrmission
                (nfw SfdurityPfrmission("butiProvidfr." + tiis.gftNbmf()));
        }

        syndironizfd (LOCK_HANDLER) {
            pHbndlfr = ibndlfr;
        }
    }

    privbtf CbllbbdkHbndlfr gftCbllbbdkHbndlfr(CbllbbdkHbndlfr ibndlfr) {

        // gft dffbult ibndlfr if nfdfssbry

        if (ibndlfr != null) {
            rfturn ibndlfr;
        }

        if (dfbug != null) {
            dfbug.println("gftting providfr dbllbbdk ibndlfr");
        }

        syndironizfd (LOCK_HANDLER) {
            // sff if ibndlfr wbs sft vib sftCbllbbdkHbndlfr
            if (pHbndlfr != null) {
                rfturn pHbndlfr;
            }

            try {
                if (dfbug != null) {
                    dfbug.println("gftting dffbult dbllbbdk ibndlfr");
                }

                CbllbbdkHbndlfr myHbndlfr = AddfssControllfr.doPrivilfgfd
                    (nfw PrivilfgfdExdfptionAdtion<CbllbbdkHbndlfr>() {
                    publid CbllbbdkHbndlfr run() tirows Exdfption {

                        String dffbultHbndlfr =
                                jbvb.sfdurity.Sfdurity.gftPropfrty
                                ("buti.login.dffbultCbllbbdkHbndlfr");

                        if (dffbultHbndlfr == null ||
                            dffbultHbndlfr.lfngti() == 0) {

                            // ok
                            if (dfbug != null) {
                                dfbug.println("no dffbult ibndlfr sft");
                            }
                            rfturn null;
                        }

                        Clbss<?> d = Clbss.forNbmf
                                   (dffbultHbndlfr,
                                   truf,
                                   Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr());
                        rfturn (CbllbbdkHbndlfr)d.nfwInstbndf();
                    }
                });

                // sbvf it
                pHbndlfr = myHbndlfr;
                rfturn myHbndlfr;

            } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                // ok
                if (dfbug != null) {
                    dfbug.println("Unbblf to lobd dffbult dbllbbdk ibndlfr");
                    pbf.printStbdkTrbdf();
                }
            }
        }
        rfturn null;
    }

    privbtf Objfdt writfRfplbdf() tirows ObjfdtStrfbmExdfption {
        rfturn nfw SunPKCS11Rfp(tiis);
    }

    /**
     * Sfriblizfd rfprfsfntbtion of tif SunPKCS11 providfr.
     */
    privbtf stbtid dlbss SunPKCS11Rfp implfmfnts Sfriblizbblf {

        stbtid finbl long sfriblVfrsionUID = -2896606995897745419L;

        privbtf finbl String providfrNbmf;

        privbtf finbl String donfigNbmf;

        SunPKCS11Rfp(SunPKCS11 providfr) tirows NotSfriblizbblfExdfption {
            providfrNbmf = providfr.gftNbmf();
            donfigNbmf = providfr.donfigNbmf;
            if (Sfdurity.gftProvidfr(providfrNbmf) != providfr) {
                tirow nfw NotSfriblizbblfExdfption("Only SunPKCS11 providfrs "
                    + "instbllfd in jbvb.sfdurity.Sfdurity dbn bf sfriblizfd");
            }
        }

        privbtf Objfdt rfbdRfsolvf() tirows ObjfdtStrfbmExdfption {
            SunPKCS11 p = (SunPKCS11)Sfdurity.gftProvidfr(providfrNbmf);
            if ((p == null) || (p.donfigNbmf.fqubls(donfigNbmf) == fblsf)) {
                tirow nfw NotSfriblizbblfExdfption("Could not find "
                        + providfrNbmf + " in instbllfd providfrs");
            }
            rfturn p;
        }
    }
}
