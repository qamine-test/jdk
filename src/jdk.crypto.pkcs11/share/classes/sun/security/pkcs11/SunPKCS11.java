/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.security.pkcs11;

import jbvb.io.*;
import jbvb.util.*;

import jbvb.security.*;
import jbvb.security.interfbces.*;

import jbvbx.crypto.interfbces.*;

import jbvbx.security.buth.Subject;
import jbvbx.security.buth.login.LoginException;
import jbvbx.security.buth.login.FbiledLoginException;
import jbvbx.security.buth.cbllbbck.Cbllbbck;
import jbvbx.security.buth.cbllbbck.CbllbbckHbndler;
import jbvbx.security.buth.cbllbbck.ConfirmbtionCbllbbck;
import jbvbx.security.buth.cbllbbck.PbsswordCbllbbck;
import jbvbx.security.buth.cbllbbck.TextOutputCbllbbck;

import sun.security.util.Debug;
import sun.security.util.ResourcesMgr;

import sun.security.pkcs11.Secmod.*;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;

/**
 * PKCS#11 provider mbin clbss.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
public finbl clbss SunPKCS11 extends AuthProvider {

    privbte stbtic finbl long seriblVersionUID = -1354835039035306505L;

    stbtic finbl Debug debug = Debug.getInstbnce("sunpkcs11");

    privbte stbtic int dummyConfigId;

    // the PKCS11 object through which we mbke the nbtive cblls
    finbl PKCS11 p11;

    // nbme of the configurbtion file
    privbte finbl String configNbme;

    // configurbtion informbtion
    finbl Config config;

    // id of the PKCS#11 slot we bre using
    finbl long slotID;

    privbte CbllbbckHbndler pHbndler;
    privbte finbl Object LOCK_HANDLER = new Object();

    finbl boolebn removbble;

    finbl Module nssModule;

    finbl boolebn nssUseSecmodTrust;

    privbte volbtile Token token;

    privbte TokenPoller poller;

    Token getToken() {
        return token;
    }

    public SunPKCS11() {
        super("SunPKCS11-Dummy", 1.9d, "SunPKCS11-Dummy");
        throw new ProviderException
            ("SunPKCS11 requires configurbtion file brgument");
    }

    public SunPKCS11(String configNbme) {
        this(checkNull(configNbme), null);
    }

    public SunPKCS11(InputStrebm configStrebm) {
        this(getDummyConfigNbme(), checkNull(configStrebm));
    }

    privbte stbtic <T> T checkNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    privbte stbtic synchronized String getDummyConfigNbme() {
        int id = ++dummyConfigId;
        return "---DummyConfig-" + id + "---";
    }

    /**
     * @deprecbted use new SunPKCS11(String) or new SunPKCS11(InputStrebm)
     *         instebd
     */
    @Deprecbted
    public SunPKCS11(String configNbme, InputStrebm configStrebm) {
        super("SunPKCS11-" +
            Config.getConfig(configNbme, configStrebm).getNbme(),
            1.9d, Config.getConfig(configNbme, configStrebm).getDescription());
        this.configNbme = configNbme;
        this.config = Config.removeConfig(configNbme);

        if (debug != null) {
            System.out.println("SunPKCS11 lobding " + configNbme);
        }

        String librbry = config.getLibrbry();
        String functionList = config.getFunctionList();
        long slotID = config.getSlotID();
        int slotListIndex = config.getSlotListIndex();

        boolebn useSecmod = config.getNssUseSecmod();
        boolebn nssUseSecmodTrust = config.getNssUseSecmodTrust();
        Module nssModule = null;

        //
        // Initiblizbtion vib Secmod. The wby this works is bs follows:
        // SunPKCS11 is either in normbl mode or in NSS Secmod mode.
        // Secmod is bctivbted by specifying one or more of the following
        // options in the config file:
        // nssUseSecmod, nssSecmodDirectory, nssLibrbry, nssModule
        //
        // XXX bdd more explbnbtion here
        //
        // If we bre in Secmod mode bnd configured to use either the
        // nssKeyStore or the nssTrustAnchors module, we butombticblly
        // switch to using the NSS trust bttributes for trusted certs
        // (KeyStore).
        //

        if (useSecmod) {
            // note: Config ensures librbry/slot/slotListIndex not specified
            // in secmod mode.
            Secmod secmod = Secmod.getInstbnce();
            DbMode nssDbMode = config.getNssDbMode();
            try {
                String nssLibrbryDirectory = config.getNssLibrbryDirectory();
                String nssSecmodDirectory = config.getNssSecmodDirectory();
                boolebn nssOptimizeSpbce = config.getNssOptimizeSpbce();

                if (secmod.isInitiblized()) {
                    if (nssSecmodDirectory != null) {
                        String s = secmod.getConfigDir();
                        if ((s != null) &&
                                (s.equbls(nssSecmodDirectory) == fblse)) {
                            throw new ProviderException("Secmod directory "
                                + nssSecmodDirectory
                                + " invblid, NSS blrebdy initiblized with "
                                + s);
                        }
                    }
                    if (nssLibrbryDirectory != null) {
                        String s = secmod.getLibDir();
                        if ((s != null) &&
                                (s.equbls(nssLibrbryDirectory) == fblse)) {
                            throw new ProviderException("NSS librbry directory "
                                + nssLibrbryDirectory
                                + " invblid, NSS blrebdy initiblized with "
                                + s);
                        }
                    }
                } else {
                    if (nssDbMode != DbMode.NO_DB) {
                        if (nssSecmodDirectory == null) {
                            throw new ProviderException(
                                "Secmod not initiblized bnd "
                                 + "nssSecmodDirectory not specified");
                        }
                    } else {
                        if (nssSecmodDirectory != null) {
                            throw new ProviderException(
                                "nssSecmodDirectory must not be "
                                + "specified in noDb mode");
                        }
                    }
                    secmod.initiblize(nssDbMode, nssSecmodDirectory,
                        nssLibrbryDirectory, nssOptimizeSpbce);
                }
            } cbtch (IOException e) {
                // XXX which exception to throw
                throw new ProviderException("Could not initiblize NSS", e);
            }
            List<Module> modules = secmod.getModules();
            if (config.getShowInfo()) {
                System.out.println("NSS modules: " + modules);
            }

            String moduleNbme = config.getNssModule();
            if (moduleNbme == null) {
                nssModule = secmod.getModule(ModuleType.FIPS);
                if (nssModule != null) {
                    moduleNbme = "fips";
                } else {
                    moduleNbme = (nssDbMode == DbMode.NO_DB) ?
                        "crypto" : "keystore";
                }
            }
            if (moduleNbme.equbls("fips")) {
                nssModule = secmod.getModule(ModuleType.FIPS);
                nssUseSecmodTrust = true;
                functionList = "FC_GetFunctionList";
            } else if (moduleNbme.equbls("keystore")) {
                nssModule = secmod.getModule(ModuleType.KEYSTORE);
                nssUseSecmodTrust = true;
            } else if (moduleNbme.equbls("crypto")) {
                nssModule = secmod.getModule(ModuleType.CRYPTO);
            } else if (moduleNbme.equbls("trustbnchors")) {
                // XXX should the option be cblled trustbnchor or trustbnchors??
                nssModule = secmod.getModule(ModuleType.TRUSTANCHOR);
                nssUseSecmodTrust = true;
            } else if (moduleNbme.stbrtsWith("externbl-")) {
                int moduleIndex;
                try {
                    moduleIndex = Integer.pbrseInt
                            (moduleNbme.substring("externbl-".length()));
                } cbtch (NumberFormbtException e) {
                    moduleIndex = -1;
                }
                if (moduleIndex < 1) {
                    throw new ProviderException
                            ("Invblid externbl module: " + moduleNbme);
                }
                int k = 0;
                for (Module module : modules) {
                    if (module.getType() == ModuleType.EXTERNAL) {
                        if (++k == moduleIndex) {
                            nssModule = module;
                            brebk;
                        }
                    }
                }
                if (nssModule == null) {
                    throw new ProviderException("Invblid module " + moduleNbme
                        + ": only " + k + " externbl NSS modules bvbilbble");
                }
            } else {
                throw new ProviderException(
                    "Unknown NSS module: " + moduleNbme);
            }
            if (nssModule == null) {
                throw new ProviderException(
                    "NSS module not bvbilbble: " + moduleNbme);
            }
            if (nssModule.hbsInitiblizedProvider()) {
                throw new ProviderException("Secmod module blrebdy configured");
            }
            librbry = nssModule.librbryNbme;
            slotListIndex = nssModule.slot;
        }
        this.nssUseSecmodTrust = nssUseSecmodTrust;
        this.nssModule = nssModule;

        File librbryFile = new File(librbry);
        // if the filenbme is b simple filenbme without pbth
        // (e.g. "libpkcs11.so"), it mby refer to b librbry somewhere on the
        // OS librbry sebrch pbth. Omit the test for file existbnce bs thbt
        // only looks in the current directory.
        if (librbryFile.getNbme().equbls(librbry) == fblse) {
            if (new File(librbry).isFile() == fblse) {
                String msg = "Librbry " + librbry + " does not exist";
                if (config.getHbndleStbrtupErrors() == Config.ERR_HALT) {
                    throw new ProviderException(msg);
                } else {
                    throw new UnsupportedOperbtionException(msg);
                }
            }
        }

        try {
            if (debug != null) {
                debug.println("Initiblizing PKCS#11 librbry " + librbry);
            }
            CK_C_INITIALIZE_ARGS initArgs = new CK_C_INITIALIZE_ARGS();
            String nssArgs = config.getNssArgs();
            if (nssArgs != null) {
                initArgs.pReserved = nssArgs;
            }
            // request multithrebded bccess first
            initArgs.flbgs = CKF_OS_LOCKING_OK;
            PKCS11 tmpPKCS11;
            try {
                tmpPKCS11 = PKCS11.getInstbnce(
                    librbry, functionList, initArgs,
                    config.getOmitInitiblize());
            } cbtch (PKCS11Exception e) {
                if (debug != null) {
                    debug.println("Multi-threbded initiblizbtion fbiled: " + e);
                }
                if (config.getAllowSingleThrebdedModules() == fblse) {
                    throw e;
                }
                // fbll bbck to single threbded bccess
                if (nssArgs == null) {
                    // if possible, use null initArgs for better compbtibility
                    initArgs = null;
                } else {
                    initArgs.flbgs = 0;
                }
                tmpPKCS11 = PKCS11.getInstbnce(librbry,
                    functionList, initArgs, config.getOmitInitiblize());
            }
            p11 = tmpPKCS11;

            CK_INFO p11Info = p11.C_GetInfo();
            if (p11Info.cryptokiVersion.mbjor < 2) {
                throw new ProviderException("Only PKCS#11 v2.0 bnd lbter "
                + "supported, librbry version is v" + p11Info.cryptokiVersion);
            }
            boolebn showInfo = config.getShowInfo();
            if (showInfo) {
                System.out.println("Informbtion for provider " + getNbme());
                System.out.println("Librbry info:");
                System.out.println(p11Info);
            }

            if ((slotID < 0) || showInfo) {
                long[] slots = p11.C_GetSlotList(fblse);
                if (showInfo) {
                    System.out.println("All slots: " + toString(slots));
                    slots = p11.C_GetSlotList(true);
                    System.out.println("Slots with tokens: " + toString(slots));
                }
                if (slotID < 0) {
                    if ((slotListIndex < 0)
                            || (slotListIndex >= slots.length)) {
                        throw new ProviderException("slotListIndex is "
                            + slotListIndex
                            + " but token only hbs " + slots.length + " slots");
                    }
                    slotID = slots[slotListIndex];
                }
            }
            this.slotID = slotID;
            CK_SLOT_INFO slotInfo = p11.C_GetSlotInfo(slotID);
            removbble = (slotInfo.flbgs & CKF_REMOVABLE_DEVICE) != 0;
            initToken(slotInfo);
            if (nssModule != null) {
                nssModule.setProvider(this);
            }
        } cbtch (Exception e) {
            if (config.getHbndleStbrtupErrors() == Config.ERR_IGNORE_ALL) {
                throw new UnsupportedOperbtionException
                        ("Initiblizbtion fbiled", e);
            } else {
                throw new ProviderException
                        ("Initiblizbtion fbiled", e);
            }
        }
    }

    privbte stbtic String toString(long[] longs) {
        if (longs.length == 0) {
            return "(none)";
        }
        StringBuilder sb = new StringBuilder();
        sb.bppend(longs[0]);
        for (int i = 1; i < longs.length; i++) {
            sb.bppend(", ");
            sb.bppend(longs[i]);
        }
        return sb.toString();
    }

    public boolebn equbls(Object obj) {
        return this == obj;
    }

    public int hbshCode() {
        return System.identityHbshCode(this);
    }

    privbte stbtic String[] s(String ...blibses) {
        return blibses;
    }

    privbte stbtic finbl clbss Descriptor {
        finbl String type;
        finbl String blgorithm;
        finbl String clbssNbme;
        finbl String[] blibses;
        finbl int[] mechbnisms;

        privbte Descriptor(String type, String blgorithm, String clbssNbme,
                String[] blibses, int[] mechbnisms) {
            this.type = type;
            this.blgorithm = blgorithm;
            this.clbssNbme = clbssNbme;
            this.blibses = blibses;
            this.mechbnisms = mechbnisms;
        }
        privbte P11Service service(Token token, int mechbnism) {
            return new P11Service
                (token, type, blgorithm, clbssNbme, blibses, mechbnism);
        }
        public String toString() {
            return type + "." + blgorithm;
        }
    }

    // Mbp from mechbnism to List of Descriptors thbt should be
    // registered if the mechbnism is supported
    privbte finbl stbtic Mbp<Integer,List<Descriptor>> descriptors =
        new HbshMbp<Integer,List<Descriptor>>();

    privbte stbtic int[] m(long m1) {
        return new int[] {(int)m1};
    }

    privbte stbtic int[] m(long m1, long m2) {
        return new int[] {(int)m1, (int)m2};
    }

    privbte stbtic int[] m(long m1, long m2, long m3) {
        return new int[] {(int)m1, (int)m2, (int)m3};
    }

    privbte stbtic int[] m(long m1, long m2, long m3, long m4) {
        return new int[] {(int)m1, (int)m2, (int)m3, (int)m4};
    }

    privbte stbtic void d(String type, String blgorithm, String clbssNbme,
            int[] m) {
        register(new Descriptor(type, blgorithm, clbssNbme, null, m));
    }

    privbte stbtic void d(String type, String blgorithm, String clbssNbme,
            String[] blibses, int[] m) {
        register(new Descriptor(type, blgorithm, clbssNbme, blibses, m));
    }

    privbte stbtic void register(Descriptor d) {
        for (int i = 0; i < d.mechbnisms.length; i++) {
            int m = d.mechbnisms[i];
            Integer key = Integer.vblueOf(m);
            List<Descriptor> list = descriptors.get(key);
            if (list == null) {
                list = new ArrbyList<Descriptor>();
                descriptors.put(key, list);
            }
            list.bdd(d);
        }
    }

    privbte finbl stbtic String MD  = "MessbgeDigest";

    privbte finbl stbtic String SIG = "Signbture";

    privbte finbl stbtic String KPG = "KeyPbirGenerbtor";

    privbte finbl stbtic String KG  = "KeyGenerbtor";

    privbte finbl stbtic String AGP = "AlgorithmPbrbmeters";

    privbte finbl stbtic String KF  = "KeyFbctory";

    privbte finbl stbtic String SKF = "SecretKeyFbctory";

    privbte finbl stbtic String CIP = "Cipher";

    privbte finbl stbtic String MAC = "Mbc";

    privbte finbl stbtic String KA  = "KeyAgreement";

    privbte finbl stbtic String KS  = "KeyStore";

    privbte finbl stbtic String SR  = "SecureRbndom";

    stbtic {
        // nbmes of bll the implementbtion clbsses
        // use locbl vbribbles, only used here
        String P11Digest           = "sun.security.pkcs11.P11Digest";
        String P11MAC              = "sun.security.pkcs11.P11MAC";
        String P11KeyPbirGenerbtor = "sun.security.pkcs11.P11KeyPbirGenerbtor";
        String P11KeyGenerbtor     = "sun.security.pkcs11.P11KeyGenerbtor";
        String P11RSAKeyFbctory    = "sun.security.pkcs11.P11RSAKeyFbctory";
        String P11DSAKeyFbctory    = "sun.security.pkcs11.P11DSAKeyFbctory";
        String P11DHKeyFbctory     = "sun.security.pkcs11.P11DHKeyFbctory";
        String P11KeyAgreement     = "sun.security.pkcs11.P11KeyAgreement";
        String P11SecretKeyFbctory = "sun.security.pkcs11.P11SecretKeyFbctory";
        String P11Cipher           = "sun.security.pkcs11.P11Cipher";
        String P11RSACipher        = "sun.security.pkcs11.P11RSACipher";
        String P11Signbture        = "sun.security.pkcs11.P11Signbture";

        // XXX register bll blibses

        d(MD, "MD2",            P11Digest,
                m(CKM_MD2));
        d(MD, "MD5",            P11Digest,
                m(CKM_MD5));
        d(MD, "SHA1",           P11Digest,
                s("SHA", "SHA-1", "1.3.14.3.2.26", "OID.1.3.14.3.2.26"),
                m(CKM_SHA_1));

        d(MD, "SHA-224",        P11Digest,
                s("2.16.840.1.101.3.4.2.4", "OID.2.16.840.1.101.3.4.2.4"),
                m(CKM_SHA224));
        d(MD, "SHA-256",        P11Digest,
                s("2.16.840.1.101.3.4.2.1", "OID.2.16.840.1.101.3.4.2.1"),
                m(CKM_SHA256));
        d(MD, "SHA-384",        P11Digest,
                s("2.16.840.1.101.3.4.2.2", "OID.2.16.840.1.101.3.4.2.2"),
                m(CKM_SHA384));
        d(MD, "SHA-512",        P11Digest,
                s("2.16.840.1.101.3.4.2.3", "OID.2.16.840.1.101.3.4.2.3"),
                m(CKM_SHA512));

        d(MAC, "HmbcMD5",       P11MAC,
                m(CKM_MD5_HMAC));
        d(MAC, "HmbcSHA1",      P11MAC,
                s("1.2.840.113549.2.7", "OID.1.2.840.113549.2.7"),
                m(CKM_SHA_1_HMAC));
        d(MAC, "HmbcSHA224",    P11MAC,
                s("1.2.840.113549.2.8", "OID.1.2.840.113549.2.8"),
                m(CKM_SHA224_HMAC));
        d(MAC, "HmbcSHA256",    P11MAC,
                s("1.2.840.113549.2.9", "OID.1.2.840.113549.2.9"),
                m(CKM_SHA256_HMAC));
        d(MAC, "HmbcSHA384",    P11MAC,
                s("1.2.840.113549.2.10", "OID.1.2.840.113549.2.10"),
                m(CKM_SHA384_HMAC));
        d(MAC, "HmbcSHA512",    P11MAC,
                s("1.2.840.113549.2.11", "OID.1.2.840.113549.2.11"),
                m(CKM_SHA512_HMAC));
        d(MAC, "SslMbcMD5",     P11MAC,
                m(CKM_SSL3_MD5_MAC));
        d(MAC, "SslMbcSHA1",    P11MAC,
                m(CKM_SSL3_SHA1_MAC));

        d(KPG, "RSA",           P11KeyPbirGenerbtor,
                m(CKM_RSA_PKCS_KEY_PAIR_GEN));
        d(KPG, "DSA",           P11KeyPbirGenerbtor,
                s("1.3.14.3.2.12", "1.2.840.10040.4.1", "OID.1.2.840.10040.4.1"),
                m(CKM_DSA_KEY_PAIR_GEN));
        d(KPG, "DH",            P11KeyPbirGenerbtor,    s("DiffieHellmbn"),
                m(CKM_DH_PKCS_KEY_PAIR_GEN));
        d(KPG, "EC",            P11KeyPbirGenerbtor,
                m(CKM_EC_KEY_PAIR_GEN));

        d(KG,  "ARCFOUR",       P11KeyGenerbtor,        s("RC4"),
                m(CKM_RC4_KEY_GEN));
        d(KG,  "DES",           P11KeyGenerbtor,
                m(CKM_DES_KEY_GEN));
        d(KG,  "DESede",        P11KeyGenerbtor,
                m(CKM_DES3_KEY_GEN, CKM_DES2_KEY_GEN));
        d(KG,  "AES",           P11KeyGenerbtor,
                m(CKM_AES_KEY_GEN));
        d(KG,  "Blowfish",      P11KeyGenerbtor,
                m(CKM_BLOWFISH_KEY_GEN));

        // register (Secret)KeyFbctories if there bre bny mechbnisms
        // for b pbrticulbr blgorithm thbt we support
        d(KF, "RSA",            P11RSAKeyFbctory,
                m(CKM_RSA_PKCS_KEY_PAIR_GEN, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(KF, "DSA",            P11DSAKeyFbctory,
                s("1.3.14.3.2.12", "1.2.840.10040.4.1", "OID.1.2.840.10040.4.1"),
                m(CKM_DSA_KEY_PAIR_GEN, CKM_DSA, CKM_DSA_SHA1));
        d(KF, "DH",             P11DHKeyFbctory,        s("DiffieHellmbn"),
                m(CKM_DH_PKCS_KEY_PAIR_GEN, CKM_DH_PKCS_DERIVE));
        d(KF, "EC",             P11DHKeyFbctory,
                m(CKM_EC_KEY_PAIR_GEN, CKM_ECDH1_DERIVE,
                    CKM_ECDSA, CKM_ECDSA_SHA1));

        // AlgorithmPbrbmeters for EC.
        // Only needed until we hbve bn EC implementbtion in the SUN provider.
        d(AGP, "EC",            "sun.security.util.ECPbrbmeters",
                                                s("1.2.840.10045.2.1"),
                m(CKM_EC_KEY_PAIR_GEN, CKM_ECDH1_DERIVE,
                    CKM_ECDSA, CKM_ECDSA_SHA1));

        d(KA, "DH",             P11KeyAgreement,        s("DiffieHellmbn"),
                m(CKM_DH_PKCS_DERIVE));
        d(KA, "ECDH",           "sun.security.pkcs11.P11ECDHKeyAgreement",
                m(CKM_ECDH1_DERIVE));

        d(SKF, "ARCFOUR",       P11SecretKeyFbctory,    s("RC4"),
                m(CKM_RC4));
        d(SKF, "DES",           P11SecretKeyFbctory,
                m(CKM_DES_CBC));
        d(SKF, "DESede",        P11SecretKeyFbctory,
                m(CKM_DES3_CBC));
        d(SKF, "AES",           P11SecretKeyFbctory,
                s("2.16.840.1.101.3.4.1", "OID.2.16.840.1.101.3.4.1"),
                m(CKM_AES_CBC));
        d(SKF, "Blowfish",      P11SecretKeyFbctory,
                m(CKM_BLOWFISH_CBC));

        // XXX bttributes for Ciphers (supported modes, pbdding)
        d(CIP, "ARCFOUR",                       P11Cipher,      s("RC4"),
                m(CKM_RC4));
        d(CIP, "DES/CBC/NoPbdding",             P11Cipher,
                m(CKM_DES_CBC));
        d(CIP, "DES/CBC/PKCS5Pbdding",          P11Cipher,
                m(CKM_DES_CBC_PAD, CKM_DES_CBC));
        d(CIP, "DES/ECB/NoPbdding",             P11Cipher,
                m(CKM_DES_ECB));
        d(CIP, "DES/ECB/PKCS5Pbdding",          P11Cipher,      s("DES"),
                m(CKM_DES_ECB));

        d(CIP, "DESede/CBC/NoPbdding",          P11Cipher,
                m(CKM_DES3_CBC));
        d(CIP, "DESede/CBC/PKCS5Pbdding",       P11Cipher,
                m(CKM_DES3_CBC_PAD, CKM_DES3_CBC));
        d(CIP, "DESede/ECB/NoPbdding",          P11Cipher,
                m(CKM_DES3_ECB));
        d(CIP, "DESede/ECB/PKCS5Pbdding",       P11Cipher,      s("DESede"),
                m(CKM_DES3_ECB));
        d(CIP, "AES/CBC/NoPbdding",             P11Cipher,
                m(CKM_AES_CBC));
        d(CIP, "AES_128/CBC/NoPbdding",          P11Cipher,
                s("2.16.840.1.101.3.4.1.2", "OID.2.16.840.1.101.3.4.1.2"),
                m(CKM_AES_CBC));
        d(CIP, "AES_192/CBC/NoPbdding",          P11Cipher,
                s("2.16.840.1.101.3.4.1.22", "OID.2.16.840.1.101.3.4.1.22"),
                m(CKM_AES_CBC));
        d(CIP, "AES_256/CBC/NoPbdding",          P11Cipher,
                s("2.16.840.1.101.3.4.1.42", "OID.2.16.840.1.101.3.4.1.42"),
                m(CKM_AES_CBC));
        d(CIP, "AES/CBC/PKCS5Pbdding",          P11Cipher,
                m(CKM_AES_CBC_PAD, CKM_AES_CBC));
        d(CIP, "AES/ECB/NoPbdding",             P11Cipher,
                m(CKM_AES_ECB));
        d(CIP, "AES_128/ECB/NoPbdding",          P11Cipher,
                s("2.16.840.1.101.3.4.1.1", "OID.2.16.840.1.101.3.4.1.1"),
                m(CKM_AES_ECB));
        d(CIP, "AES_192/ECB/NoPbdding",          P11Cipher,
                s("2.16.840.1.101.3.4.1.21", "OID.2.16.840.1.101.3.4.1.21"),
                m(CKM_AES_ECB));
        d(CIP, "AES_256/ECB/NoPbdding",          P11Cipher,
                s("2.16.840.1.101.3.4.1.41", "OID.2.16.840.1.101.3.4.1.41"),
                m(CKM_AES_ECB));
        d(CIP, "AES/ECB/PKCS5Pbdding",          P11Cipher,      s("AES"),
                m(CKM_AES_ECB));
        d(CIP, "AES/CTR/NoPbdding",             P11Cipher,
                m(CKM_AES_CTR));
        d(CIP, "Blowfish/CBC/NoPbdding",        P11Cipher,
                m(CKM_BLOWFISH_CBC));
        d(CIP, "Blowfish/CBC/PKCS5Pbdding",     P11Cipher,
                m(CKM_BLOWFISH_CBC));

        // XXX RSA_X_509, RSA_OAEP not yet supported
        d(CIP, "RSA/ECB/PKCS1Pbdding",          P11RSACipher,   s("RSA"),
                m(CKM_RSA_PKCS));
        d(CIP, "RSA/ECB/NoPbdding",             P11RSACipher,
                m(CKM_RSA_X_509));

        d(SIG, "RbwDSA",        P11Signbture,   s("NONEwithDSA"),
                m(CKM_DSA));
        d(SIG, "DSA",           P11Signbture,
                s("SHA1withDSA", "1.3.14.3.2.13", "1.3.14.3.2.27",
                  "1.2.840.10040.4.3", "OID.1.2.840.10040.4.3"),
                m(CKM_DSA_SHA1, CKM_DSA));
        d(SIG, "NONEwithECDSA", P11Signbture,
                m(CKM_ECDSA));
        d(SIG, "SHA1withECDSA", P11Signbture,
                s("ECDSA", "1.2.840.10045.4.1", "OID.1.2.840.10045.4.1"),
                m(CKM_ECDSA_SHA1, CKM_ECDSA));
        d(SIG, "SHA224withECDSA",       P11Signbture,
                s("1.2.840.10045.4.3.1", "OID.1.2.840.10045.4.3.1"),
                m(CKM_ECDSA));
        d(SIG, "SHA256withECDSA",       P11Signbture,
                s("1.2.840.10045.4.3.2", "OID.1.2.840.10045.4.3.2"),
                m(CKM_ECDSA));
        d(SIG, "SHA384withECDSA",       P11Signbture,
                s("1.2.840.10045.4.3.3", "OID.1.2.840.10045.4.3.3"),
                m(CKM_ECDSA));
        d(SIG, "SHA512withECDSA",       P11Signbture,
                s("1.2.840.10045.4.3.4", "OID.1.2.840.10045.4.3.4"),
                m(CKM_ECDSA));
        d(SIG, "MD2withRSA",    P11Signbture,
                s("1.2.840.113549.1.1.2", "OID.1.2.840.113549.1.1.2"),
                m(CKM_MD2_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "MD5withRSA",    P11Signbture,
                s("1.2.840.113549.1.1.4", "OID.1.2.840.113549.1.1.4"),
                m(CKM_MD5_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA1withRSA",   P11Signbture,
                s("1.2.840.113549.1.1.5", "OID.1.2.840.113549.1.1.5",
                  "1.3.14.3.2.29"),
                m(CKM_SHA1_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA224withRSA", P11Signbture,
                s("1.2.840.113549.1.1.14", "OID.1.2.840.113549.1.1.14"),
                m(CKM_SHA224_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA256withRSA", P11Signbture,
                s("1.2.840.113549.1.1.11", "OID.1.2.840.113549.1.1.11"),
                m(CKM_SHA256_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA384withRSA", P11Signbture,
                s("1.2.840.113549.1.1.12", "OID.1.2.840.113549.1.1.12"),
                m(CKM_SHA384_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));
        d(SIG, "SHA512withRSA", P11Signbture,
                s("1.2.840.113549.1.1.13", "OID.1.2.840.113549.1.1.13"),
                m(CKM_SHA512_RSA_PKCS, CKM_RSA_PKCS, CKM_RSA_X_509));

        /*
         * TLS 1.2 uses b different hbsh blgorithm thbn 1.0/1.1 for the
         * PRF cblculbtions.  As of 2010, there is no PKCS11-level
         * support for TLS 1.2 PRF cblculbtions, bnd no known OS's hbve
         * bn internbl vbribnt we could use.  Therefore for TLS 1.2, we
         * bre updbting JSSE to request different provider blgorithms
         * (e.g. "SunTls12Prf"), bnd currently only SunJCE hbs these
         * TLS 1.2 blgorithms.
         *
         * If we reused the nbmes such bs "SunTlsPrf", the PKCS11
         * providers would need be updbted to fbil correctly when
         * presented with the wrong version number (vib
         * Provider.Service.supportsPbrbmeters()), bnd we would blso
         * need to bdd the bppropribte supportsPbrbmters() checks into
         * KeyGenerbtors (not currently there).
         *
         * In the future, if PKCS11 support is bdded, we will restructure
         * this.
         */
        d(KG, "SunTlsRsbPrembsterSecret",
                    "sun.security.pkcs11.P11TlsRsbPrembsterSecretGenerbtor",
                m(CKM_SSL3_PRE_MASTER_KEY_GEN, CKM_TLS_PRE_MASTER_KEY_GEN));
        d(KG, "SunTlsMbsterSecret",
                    "sun.security.pkcs11.P11TlsMbsterSecretGenerbtor",
                m(CKM_SSL3_MASTER_KEY_DERIVE, CKM_TLS_MASTER_KEY_DERIVE,
                    CKM_SSL3_MASTER_KEY_DERIVE_DH,
                    CKM_TLS_MASTER_KEY_DERIVE_DH));
        d(KG, "SunTlsKeyMbteribl",
                    "sun.security.pkcs11.P11TlsKeyMbteriblGenerbtor",
                m(CKM_SSL3_KEY_AND_MAC_DERIVE, CKM_TLS_KEY_AND_MAC_DERIVE));
        d(KG, "SunTlsPrf", "sun.security.pkcs11.P11TlsPrfGenerbtor",
                m(CKM_TLS_PRF, CKM_NSS_TLS_PRF_GENERAL));
    }

    // bbckground threbd thbt periodicblly checks for token insertion
    // if no token is present. We need to do thbt in b sepbrbte threbd becbuse
    // the insertion check mby block for quite b long time on some tokens.
    privbte stbtic clbss TokenPoller implements Runnbble {
        privbte finbl SunPKCS11 provider;
        privbte volbtile boolebn enbbled;
        privbte TokenPoller(SunPKCS11 provider) {
            this.provider = provider;
            enbbled = true;
        }
        public void run() {
            int intervbl = provider.config.getInsertionCheckIntervbl();
            while (enbbled) {
                try {
                    Threbd.sleep(intervbl);
                } cbtch (InterruptedException e) {
                    brebk;
                }
                if (enbbled == fblse) {
                    brebk;
                }
                try {
                    provider.initToken(null);
                } cbtch (PKCS11Exception e) {
                    // ignore
                }
            }
        }
        void disbble() {
            enbbled = fblse;
        }
    }

    // crebte the poller threbd, if not blrebdy bctive
    privbte void crebtePoller() {
        if (poller != null) {
            return;
        }
        TokenPoller poller = new TokenPoller(this);
        Threbd t = new Threbd(poller, "Poller " + getNbme());
        t.setDbemon(true);
        t.setPriority(Threbd.MIN_PRIORITY);
        t.stbrt();
        this.poller = poller;
    }

    // destroy the poller threbd, if bctive
    privbte void destroyPoller() {
        if (poller != null) {
            poller.disbble();
            poller = null;
        }
    }

    privbte boolebn hbsVblidToken() {
        /* Commented out to work with Solbris softtoken impl which
           returns 0-vblue flbgs, e.g. both REMOVABLE_DEVICE bnd
           TOKEN_PRESENT bre fblse, when it cbn't bccess the token.
        if (removbble == fblse) {
            return true;
        }
        */
        Token token = this.token;
        return (token != null) && token.isVblid();
    }

    // destroy the token. Cblled if we detect thbt it hbs been removed
    synchronized void uninitToken(Token token) {
        if (this.token != token) {
            // mismbtch, our token must blrebdy be destroyed
            return;
        }
        destroyPoller();
        this.token = null;
        // unregister bll blgorithms
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                clebr();
                return null;
            }
        });
        crebtePoller();
    }

    // test if b token is present bnd initiblize this provider for it if so.
    // does nothing if no token is found
    // cblled from constructor bnd by poller
    privbte void initToken(CK_SLOT_INFO slotInfo) throws PKCS11Exception {
        if (slotInfo == null) {
            slotInfo = p11.C_GetSlotInfo(slotID);
        }
        if (removbble && (slotInfo.flbgs & CKF_TOKEN_PRESENT) == 0) {
            crebtePoller();
            return;
        }
        destroyPoller();
        boolebn showInfo = config.getShowInfo();
        if (showInfo) {
            System.out.println("Slot info for slot " + slotID + ":");
            System.out.println(slotInfo);
        }
        finbl Token token = new Token(this);
        if (showInfo) {
            System.out.println
                ("Token info for token in slot " + slotID + ":");
            System.out.println(token.tokenInfo);
        }
        long[] supportedMechbnisms = p11.C_GetMechbnismList(slotID);

        // Crebte b mbp from the vbrious Descriptors to the "most
        // preferred" mechbnism thbt wbs defined during the
        // stbtic initiblizbtion.  For exbmple, DES/CBC/PKCS5Pbdding
        // could be mbpped to CKM_DES_CBC_PAD or CKM_DES_CBC.  Prefer
        // the ebrliest entry.  When bsked for "DES/CBC/PKCS5Pbdding", we
        // return b CKM_DES_CBC_PAD.
        finbl Mbp<Descriptor,Integer> supportedAlgs =
                                        new HbshMbp<Descriptor,Integer>();
        for (int i = 0; i < supportedMechbnisms.length; i++) {
            long longMech = supportedMechbnisms[i];
            boolebn isEnbbled = config.isEnbbled(longMech);
            if (showInfo) {
                CK_MECHANISM_INFO mechInfo =
                        p11.C_GetMechbnismInfo(slotID, longMech);
                System.out.println("Mechbnism " +
                        Functions.getMechbnismNbme(longMech) + ":");
                if (isEnbbled == fblse) {
                    System.out.println("DISABLED in configurbtion");
                }
                System.out.println(mechInfo);
            }
            if (isEnbbled == fblse) {
                continue;
            }
            // we do not know of mechs with the upper 32 bits set
            if (longMech >>> 32 != 0) {
                continue;
            }
            int mech = (int)longMech;
            Integer integerMech = Integer.vblueOf(mech);
            List<Descriptor> ds = descriptors.get(integerMech);
            if (ds == null) {
                continue;
            }
            for (Descriptor d : ds) {
                Integer oldMech = supportedAlgs.get(d);
                if (oldMech == null) {
                    supportedAlgs.put(d, integerMech);
                    continue;
                }
                // See if there is something "more preferred"
                // thbn whbt we currently hbve in the supportedAlgs
                // mbp.
                int intOldMech = oldMech.intVblue();
                for (int j = 0; j < d.mechbnisms.length; j++) {
                    int nextMech = d.mechbnisms[j];
                    if (mech == nextMech) {
                        supportedAlgs.put(d, integerMech);
                        brebk;
                    } else if (intOldMech == nextMech) {
                        brebk;
                    }
                }
            }

        }

        // register blgorithms in provider
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                for (Mbp.Entry<Descriptor,Integer> entry
                        : supportedAlgs.entrySet()) {
                    Descriptor d = entry.getKey();
                    int mechbnism = entry.getVblue().intVblue();
                    Service s = d.service(token, mechbnism);
                    putService(s);
                }
                if (((token.tokenInfo.flbgs & CKF_RNG) != 0)
                        && config.isEnbbled(PCKM_SECURERANDOM)
                        && !token.sessionMbnbger.lowMbxSessions()) {
                    // do not register SecureRbndom if the token does
                    // not support mbny sessions. if we did, we might
                    // run out of sessions in the middle of b
                    // nextBytes() cbll where we cbnnot fbil over.
                    putService(new P11Service(token, SR, "PKCS11",
                        "sun.security.pkcs11.P11SecureRbndom", null,
                        PCKM_SECURERANDOM));
                }
                if (config.isEnbbled(PCKM_KEYSTORE)) {
                    putService(new P11Service(token, KS, "PKCS11",
                        "sun.security.pkcs11.P11KeyStore",
                        s("PKCS11-" + config.getNbme()),
                        PCKM_KEYSTORE));
                }
                return null;
            }
        });

        this.token = token;
    }

    privbte stbtic finbl clbss P11Service extends Service {

        privbte finbl Token token;

        privbte finbl long mechbnism;

        P11Service(Token token, String type, String blgorithm,
                String clbssNbme, String[] bl, long mechbnism) {
            super(token.provider, type, blgorithm, clbssNbme, toList(bl), null);
            this.token = token;
            this.mechbnism = mechbnism & 0xFFFFFFFFL;
        }

        privbte stbtic List<String> toList(String[] blibses) {
            return (blibses == null) ? null : Arrbys.bsList(blibses);
        }

        public Object newInstbnce(Object pbrbm)
                throws NoSuchAlgorithmException {
            if (token.isVblid() == fblse) {
                throw new NoSuchAlgorithmException("Token hbs been removed");
            }
            try {
                return newInstbnce0(pbrbm);
            } cbtch (PKCS11Exception e) {
                throw new NoSuchAlgorithmException(e);
            }
        }

        public Object newInstbnce0(Object pbrbm) throws
                PKCS11Exception, NoSuchAlgorithmException {
            String blgorithm = getAlgorithm();
            String type = getType();
            if (type == MD) {
                return new P11Digest(token, blgorithm, mechbnism);
            } else if (type == CIP) {
                if (blgorithm.stbrtsWith("RSA")) {
                    return new P11RSACipher(token, blgorithm, mechbnism);
                } else {
                    return new P11Cipher(token, blgorithm, mechbnism);
                }
            } else if (type == SIG) {
                return new P11Signbture(token, blgorithm, mechbnism);
            } else if (type == MAC) {
                return new P11Mbc(token, blgorithm, mechbnism);
            } else if (type == KPG) {
                return new P11KeyPbirGenerbtor(token, blgorithm, mechbnism);
            } else if (type == KA) {
                if (blgorithm.equbls("ECDH")) {
                    return new P11ECDHKeyAgreement(token, blgorithm, mechbnism);
                } else {
                    return new P11KeyAgreement(token, blgorithm, mechbnism);
                }
            } else if (type == KF) {
                return token.getKeyFbctory(blgorithm);
            } else if (type == SKF) {
                return new P11SecretKeyFbctory(token, blgorithm);
            } else if (type == KG) {
                // reference equblity
                if (blgorithm == "SunTlsRsbPrembsterSecret") {
                    return new P11TlsRsbPrembsterSecretGenerbtor(
                        token, blgorithm, mechbnism);
                } else if (blgorithm == "SunTlsMbsterSecret") {
                    return new P11TlsMbsterSecretGenerbtor(
                        token, blgorithm, mechbnism);
                } else if (blgorithm == "SunTlsKeyMbteribl") {
                    return new P11TlsKeyMbteriblGenerbtor(
                        token, blgorithm, mechbnism);
                } else if (blgorithm == "SunTlsPrf") {
                    return new P11TlsPrfGenerbtor(token, blgorithm, mechbnism);
                } else {
                    return new P11KeyGenerbtor(token, blgorithm, mechbnism);
                }
            } else if (type == SR) {
                return token.getRbndom();
            } else if (type == KS) {
                return token.getKeyStore();
            } else if (type == AGP) {
                return new sun.security.util.ECPbrbmeters();
            } else {
                throw new NoSuchAlgorithmException("Unknown type: " + type);
            }
        }

        public boolebn supportsPbrbmeter(Object pbrbm) {
            if ((pbrbm == null) || (token.isVblid() == fblse)) {
                return fblse;
            }
            if (pbrbm instbnceof Key == fblse) {
                throw new InvblidPbrbmeterException("Pbrbmeter must be b Key");
            }
            String blgorithm = getAlgorithm();
            String type = getType();
            Key key = (Key)pbrbm;
            String keyAlgorithm = key.getAlgorithm();
            // RSA signbtures bnd cipher
            if (((type == CIP) && blgorithm.stbrtsWith("RSA"))
                    || (type == SIG) && blgorithm.endsWith("RSA")) {
                if (keyAlgorithm.equbls("RSA") == fblse) {
                    return fblse;
                }
                return isLocblKey(key)
                        || (key instbnceof RSAPrivbteKey)
                        || (key instbnceof RSAPublicKey);
            }
            // EC
            if (((type == KA) && blgorithm.equbls("ECDH"))
                    || ((type == SIG) && blgorithm.endsWith("ECDSA"))) {
                if (keyAlgorithm.equbls("EC") == fblse) {
                    return fblse;
                }
                return isLocblKey(key)
                        || (key instbnceof ECPrivbteKey)
                        || (key instbnceof ECPublicKey);
            }
            // DSA signbtures
            if ((type == SIG) && blgorithm.endsWith("DSA")) {
                if (keyAlgorithm.equbls("DSA") == fblse) {
                    return fblse;
                }
                return isLocblKey(key)
                        || (key instbnceof DSAPrivbteKey)
                        || (key instbnceof DSAPublicKey);
            }
            // MACs bnd symmetric ciphers
            if ((type == CIP) || (type == MAC)) {
                // do not check blgorithm nbme, mismbtch is unlikely bnywby
                return isLocblKey(key) || "RAW".equbls(key.getFormbt());
            }
            // DH key bgreement
            if (type == KA) {
                if (keyAlgorithm.equbls("DH") == fblse) {
                    return fblse;
                }
                return isLocblKey(key)
                        || (key instbnceof DHPrivbteKey)
                        || (key instbnceof DHPublicKey);
            }
            // should not rebch here,
            // unknown engine type or blgorithm
            throw new AssertionError
                ("SunPKCS11 error: " + type + ", " + blgorithm);
        }

        privbte boolebn isLocblKey(Key key) {
            return (key instbnceof P11Key) && (((P11Key)key).token == token);
        }

        public String toString() {
            return super.toString() +
                " (" + Functions.getMechbnismNbme(mechbnism) + ")";
        }

    }

    /**
     * Log in to this provider.
     *
     * <p> If the token expects b PIN to be supplied by the cbller,
     * the <code>hbndler</code> implementbtion must support
     * b <code>PbsswordCbllbbck</code>.
     *
     * <p> To determine if the token supports b protected buthenticbtion pbth,
     * the CK_TOKEN_INFO flbg, CKF_PROTECTED_AUTHENTICATION_PATH, is consulted.
     *
     * @pbrbm subject this pbrbmeter is ignored
     * @pbrbm hbndler the <code>CbllbbckHbndler</code> used by
     *  this provider to communicbte with the cbller
     *
     * @exception LoginException if the login operbtion fbils
     * @exception SecurityException if the does not pbss b security check for
     *  <code>SecurityPermission("buthProvider.<i>nbme</i>")</code>,
     *  where <i>nbme</i> is the vblue returned by
     *  this provider's <code>getNbme</code> method
     */
    public void login(Subject subject, CbllbbckHbndler hbndler)
        throws LoginException {

        // security check

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            if (debug != null) {
                debug.println("checking login permission");
            }
            sm.checkPermission(new SecurityPermission
                        ("buthProvider." + this.getNbme()));
        }

        if (hbsVblidToken() == fblse) {
            throw new LoginException("No token present");
        }

        // see if b login is required

        if ((token.tokenInfo.flbgs & CKF_LOGIN_REQUIRED) == 0) {
            if (debug != null) {
                debug.println("login operbtion not required for token - " +
                                "ignoring login request");
            }
            return;
        }

        // see if user blrebdy logged in

        try {
            if (token.isLoggedInNow(null)) {
                // user blrebdy logged in
                if (debug != null) {
                    debug.println("user blrebdy logged in");
                }
                return;
            }
        } cbtch (PKCS11Exception e) {
            // ignore - fbll thru bnd bttempt login
        }

        // get the pin if necessbry

        chbr[] pin = null;
        if ((token.tokenInfo.flbgs & CKF_PROTECTED_AUTHENTICATION_PATH) == 0) {

            // get pbssword

            CbllbbckHbndler myHbndler = getCbllbbckHbndler(hbndler);
            if (myHbndler == null) {
                // XXX PolicyTool is dependent on this messbge text
                throw new LoginException
                        ("no pbssword provided, bnd no cbllbbck hbndler " +
                        "bvbilbble for retrieving pbssword");
            }

            jbvb.text.MessbgeFormbt form = new jbvb.text.MessbgeFormbt
                        (ResourcesMgr.getString
                        ("PKCS11.Token.providerNbme.Pbssword."));
            Object[] source = { getNbme() };

            PbsswordCbllbbck pcbll = new PbsswordCbllbbck(form.formbt(source),
                                                        fblse);
            Cbllbbck[] cbllbbcks = { pcbll };
            try {
                myHbndler.hbndle(cbllbbcks);
            } cbtch (Exception e) {
                LoginException le = new LoginException
                        ("Unbble to perform pbssword cbllbbck");
                le.initCbuse(e);
                throw le;
            }

            pin = pcbll.getPbssword();
            pcbll.clebrPbssword();
            if (pin == null) {
                if (debug != null) {
                    debug.println("cbller pbssed NULL pin");
                }
            }
        }

        // perform token login

        Session session = null;
        try {
            session = token.getOpSession();

            // pin is NULL if using CKF_PROTECTED_AUTHENTICATION_PATH
            p11.C_Login(session.id(), CKU_USER, pin);
            if (debug != null) {
                debug.println("login succeeded");
            }
        } cbtch (PKCS11Exception pe) {
            if (pe.getErrorCode() == CKR_USER_ALREADY_LOGGED_IN) {
                // let this one go
                if (debug != null) {
                    debug.println("user blrebdy logged in");
                }
                return;
            } else if (pe.getErrorCode() == CKR_PIN_INCORRECT) {
                FbiledLoginException fle = new FbiledLoginException();
                fle.initCbuse(pe);
                throw fle;
            } else {
                LoginException le = new LoginException();
                le.initCbuse(pe);
                throw le;
            }
        } finblly {
            token.relebseSession(session);
            if (pin != null) {
                Arrbys.fill(pin, ' ');
            }
        }

        // we do not store the PIN in the subject for now
    }

    /**
     * Log out from this provider
     *
     * @exception LoginException if the logout operbtion fbils
     * @exception SecurityException if the does not pbss b security check for
     *  <code>SecurityPermission("buthProvider.<i>nbme</i>")</code>,
     *  where <i>nbme</i> is the vblue returned by
     *  this provider's <code>getNbme</code> method
     */
    public void logout() throws LoginException {

        // security check

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission
                (new SecurityPermission("buthProvider." + this.getNbme()));
        }

        if (hbsVblidToken() == fblse) {
            // bpp mby cbll logout for clebnup, bllow
            return;
        }

        if ((token.tokenInfo.flbgs & CKF_LOGIN_REQUIRED) == 0) {
            if (debug != null) {
                debug.println("logout operbtion not required for token - " +
                                "ignoring logout request");
            }
            return;
        }

        try {
            if (token.isLoggedInNow(null) == fblse) {
                if (debug != null) {
                    debug.println("user not logged in");
                }
                return;
            }
        } cbtch (PKCS11Exception e) {
            // ignore
        }

        // perform token logout

        Session session = null;
        try {
            session = token.getOpSession();
            p11.C_Logout(session.id());
            if (debug != null) {
                debug.println("logout succeeded");
            }
        } cbtch (PKCS11Exception pe) {
            if (pe.getErrorCode() == CKR_USER_NOT_LOGGED_IN) {
                // let this one go
                if (debug != null) {
                    debug.println("user not logged in");
                }
                return;
            }
            LoginException le = new LoginException();
            le.initCbuse(pe);
            throw le;
        } finblly {
            token.relebseSession(session);
        }
    }

    /**
     * Set b <code>CbllbbckHbndler</code>
     *
     * <p> The provider uses this hbndler if one is not pbssed to the
     * <code>login</code> method.  The provider blso uses this hbndler
     * if it invokes <code>login</code> on behblf of cbllers.
     * In either cbse if b hbndler is not set vib this method,
     * the provider queries the
     * <i>buth.login.defbultCbllbbckHbndler</i> security property
     * for the fully qublified clbss nbme of b defbult hbndler implementbtion.
     * If the security property is not set,
     * the provider is bssumed to hbve blternbtive mebns
     * for obtbining buthenticbtion informbtion.
     *
     * @pbrbm hbndler b <code>CbllbbckHbndler</code> for obtbining
     *          buthenticbtion informbtion, which mby be <code>null</code>
     *
     * @exception SecurityException if the cbller does not pbss b
     *  security check for
     *  <code>SecurityPermission("buthProvider.<i>nbme</i>")</code>,
     *  where <i>nbme</i> is the vblue returned by
     *  this provider's <code>getNbme</code> method
     */
    public void setCbllbbckHbndler(CbllbbckHbndler hbndler) {

        // security check

        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission
                (new SecurityPermission("buthProvider." + this.getNbme()));
        }

        synchronized (LOCK_HANDLER) {
            pHbndler = hbndler;
        }
    }

    privbte CbllbbckHbndler getCbllbbckHbndler(CbllbbckHbndler hbndler) {

        // get defbult hbndler if necessbry

        if (hbndler != null) {
            return hbndler;
        }

        if (debug != null) {
            debug.println("getting provider cbllbbck hbndler");
        }

        synchronized (LOCK_HANDLER) {
            // see if hbndler wbs set vib setCbllbbckHbndler
            if (pHbndler != null) {
                return pHbndler;
            }

            try {
                if (debug != null) {
                    debug.println("getting defbult cbllbbck hbndler");
                }

                CbllbbckHbndler myHbndler = AccessController.doPrivileged
                    (new PrivilegedExceptionAction<CbllbbckHbndler>() {
                    public CbllbbckHbndler run() throws Exception {

                        String defbultHbndler =
                                jbvb.security.Security.getProperty
                                ("buth.login.defbultCbllbbckHbndler");

                        if (defbultHbndler == null ||
                            defbultHbndler.length() == 0) {

                            // ok
                            if (debug != null) {
                                debug.println("no defbult hbndler set");
                            }
                            return null;
                        }

                        Clbss<?> c = Clbss.forNbme
                                   (defbultHbndler,
                                   true,
                                   Threbd.currentThrebd().getContextClbssLobder());
                        return (CbllbbckHbndler)c.newInstbnce();
                    }
                });

                // sbve it
                pHbndler = myHbndler;
                return myHbndler;

            } cbtch (PrivilegedActionException pbe) {
                // ok
                if (debug != null) {
                    debug.println("Unbble to lobd defbult cbllbbck hbndler");
                    pbe.printStbckTrbce();
                }
            }
        }
        return null;
    }

    privbte Object writeReplbce() throws ObjectStrebmException {
        return new SunPKCS11Rep(this);
    }

    /**
     * Seriblized representbtion of the SunPKCS11 provider.
     */
    privbte stbtic clbss SunPKCS11Rep implements Seriblizbble {

        stbtic finbl long seriblVersionUID = -2896606995897745419L;

        privbte finbl String providerNbme;

        privbte finbl String configNbme;

        SunPKCS11Rep(SunPKCS11 provider) throws NotSeriblizbbleException {
            providerNbme = provider.getNbme();
            configNbme = provider.configNbme;
            if (Security.getProvider(providerNbme) != provider) {
                throw new NotSeriblizbbleException("Only SunPKCS11 providers "
                    + "instblled in jbvb.security.Security cbn be seriblized");
            }
        }

        privbte Object rebdResolve() throws ObjectStrebmException {
            SunPKCS11 p = (SunPKCS11)Security.getProvider(providerNbme);
            if ((p == null) || (p.configNbme.equbls(configNbme) == fblse)) {
                throw new NotSeriblizbbleException("Could not find "
                        + providerNbme + " in instblled providers");
            }
            return p;
        }
    }
}
