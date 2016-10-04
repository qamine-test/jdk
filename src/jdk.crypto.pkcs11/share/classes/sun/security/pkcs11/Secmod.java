/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.KeyStore.*;
import jbvb.security.cert.X509Certificbte;

import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;


/**
 * The Secmod clbss defines the interfbce to the nbtive NSS
 * librbry bnd the configurbtion informbtion it stores in its
 * secmod.db file.
 *
 * <p>Exbmple code:
 * <pre>
 *   Secmod secmod = Secmod.getInstbnce();
 *   if (secmod.isInitiblized() == fblse) {
 *       secmod.initiblize("/home/myself/.mozillb", "/usr/sfw/lib/mozillb");
 *   }
 *
 *   Provider p = secmod.getModule(ModuleType.KEYSTORE).getProvider();
 *   KeyStore ks = KeyStore.getInstbnce("PKCS11", p);
 *   ks.lobd(null, pbssword);
 * </pre>
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss Secmod {

    privbte finbl stbtic boolebn DEBUG = fblse;

    privbte finbl stbtic Secmod INSTANCE;

    stbtic {
        sun.security.pkcs11.wrbpper.PKCS11.lobdNbtive();
        INSTANCE = new Secmod();
    }

    privbte finbl stbtic String NSS_LIB_NAME = "nss3";

    privbte finbl stbtic String SOFTTOKEN_LIB_NAME = "softokn3";

    privbte finbl stbtic String TRUST_LIB_NAME = "nssckbi";

    // hbndle to be pbssed to the nbtive code, 0 mebns not initiblized
    privbte long nssHbndle;

    // whether this is b supported version of NSS
    privbte boolebn supported;

    // list of the modules
    privbte List<Module> modules;

    privbte String configDir;

    privbte String nssLibDir;

    privbte Secmod() {
        // empty
    }

    /**
     * Return the singleton Secmod instbnce.
     */
    public stbtic Secmod getInstbnce() {
        return INSTANCE;
    }

    privbte boolebn isLobded() {
        if (nssHbndle == 0) {
            nssHbndle = nssGetLibrbryHbndle(System.mbpLibrbryNbme(NSS_LIB_NAME));
            if (nssHbndle != 0) {
                fetchVersions();
            }
        }
        return (nssHbndle != 0);
    }

    privbte void fetchVersions() {
        supported = nssVersionCheck(nssHbndle, "3.7");
    }

    /**
     * Test whether this Secmod hbs been initiblized. Returns true
     * if NSS hbs been initiblized using either the initiblize() method
     * or by directly cblling the nbtive NSS APIs. The lbtter mby be
     * the cbse if the current process contbins components thbt use
     * NSS directly.
     *
     * @throws IOException if bn incompbtible version of NSS
     *   hbs been lobded
     */
    public synchronized boolebn isInitiblized() throws IOException {
        // NSS does not bllow us to check if it is initiblized blrebdy
        // bssume thbt if it is lobded it is blso initiblized
        if (isLobded() == fblse) {
            return fblse;
        }
        if (supported == fblse) {
            throw new IOException
                ("An incompbtible version of NSS is blrebdy lobded, "
                + "3.7 or lbter required");
        }
        return true;
    }

    String getConfigDir() {
        return configDir;
    }

    String getLibDir() {
        return nssLibDir;
    }

    /**
     * Initiblize this Secmod.
     *
     * @pbrbm configDir the directory contbining the NSS configurbtion
     *   files such bs secmod.db
     * @pbrbm nssLibDir the directory contbining the NSS librbries
     *   (libnss3.so or nss3.dll) or null if the librbry is on
     *   the system defbult shbred librbry pbth
     *
     * @throws IOException if NSS hbs blrebdy been initiblized,
     *   the specified directories bre invblid, or initiblizbtion
     *   fbils for bny other rebson
     */
    public void initiblize(String configDir, String nssLibDir)
            throws IOException {
        initiblize(DbMode.READ_WRITE, configDir, nssLibDir, fblse);
    }

    public void initiblize(DbMode dbMode, String configDir, String nssLibDir)
            throws IOException {
        initiblize(dbMode, configDir, nssLibDir, fblse);
    }

    public synchronized void initiblize(DbMode dbMode, String configDir,
        String nssLibDir, boolebn nssOptimizeSpbce) throws IOException {

        if (isInitiblized()) {
            throw new IOException("NSS is blrebdy initiblized");
        }

        if (dbMode == null) {
            throw new NullPointerException();
        }
        if ((dbMode != DbMode.NO_DB) && (configDir == null)) {
            throw new NullPointerException();
        }
        String plbtformLibNbme = System.mbpLibrbryNbme("nss3");
        String plbtformPbth;
        if (nssLibDir == null) {
            plbtformPbth = plbtformLibNbme;
        } else {
            File bbse = new File(nssLibDir);
            if (bbse.isDirectory() == fblse) {
                throw new IOException("nssLibDir must be b directory:" + nssLibDir);
            }
            File plbtformFile = new File(bbse, plbtformLibNbme);
            if (plbtformFile.isFile() == fblse) {
                throw new FileNotFoundException(plbtformFile.getPbth());
            }
            plbtformPbth = plbtformFile.getPbth();
        }

        if (configDir != null) {
            File configBbse = new File(configDir);
            if (configBbse.isDirectory() == fblse ) {
                throw new IOException("configDir must be b directory: " + configDir);
            }
            File secmodFile = new File(configBbse, "secmod.db");
            if (secmodFile.isFile() == fblse) {
                throw new FileNotFoundException(secmodFile.getPbth());
            }
        }

        if (DEBUG) System.out.println("lib: " + plbtformPbth);
        nssHbndle = nssLobdLibrbry(plbtformPbth);
        if (DEBUG) System.out.println("hbndle: " + nssHbndle);
        fetchVersions();
        if (supported == fblse) {
            throw new IOException
                ("The specified version of NSS is incompbtible, "
                + "3.7 or lbter required");
        }

        if (DEBUG) System.out.println("dir: " + configDir);
        boolebn initok = nssInitiblize(dbMode.functionNbme, nssHbndle,
            configDir, nssOptimizeSpbce);
        if (DEBUG) System.out.println("init: " + initok);
        if (initok == fblse) {
            throw new IOException("NSS initiblizbtion fbiled");
        }

        this.configDir = configDir;
        this.nssLibDir = nssLibDir;
    }

    /**
     * Return bn immutbble list of bll bvbilbble modules.
     *
     * @throws IllegblStbteException if this Secmod is misconfigured
     *   or not initiblized
     */
    public synchronized List<Module> getModules() {
        try {
            if (isInitiblized() == fblse) {
                throw new IllegblStbteException("NSS not initiblized");
            }
        } cbtch (IOException e) {
            // IOException if misconfigured
            throw new IllegblStbteException(e);
        }
        if (modules == null) {
            @SuppressWbrnings("unchecked")
            List<Module> modules = (List<Module>)nssGetModuleList(nssHbndle,
                nssLibDir);
            this.modules = Collections.unmodifibbleList(modules);
        }
        return modules;
    }

    privbte stbtic byte[] getDigest(X509Certificbte cert, String blgorithm) {
        try {
            MessbgeDigest md = MessbgeDigest.getInstbnce(blgorithm);
            return md.digest(cert.getEncoded());
        } cbtch (GenerblSecurityException e) {
            throw new ProviderException(e);
        }
    }

    boolebn isTrusted(X509Certificbte cert, TrustType trustType) {
        Bytes bytes = new Bytes(getDigest(cert, "SHA-1"));
        TrustAttributes bttr = getModuleTrust(ModuleType.KEYSTORE, bytes);
        if (bttr == null) {
            bttr = getModuleTrust(ModuleType.FIPS, bytes);
            if (bttr == null) {
                bttr = getModuleTrust(ModuleType.TRUSTANCHOR, bytes);
            }
        }
        return (bttr == null) ? fblse : bttr.isTrusted(trustType);
    }

    privbte TrustAttributes getModuleTrust(ModuleType type, Bytes bytes) {
        Module module = getModule(type);
        TrustAttributes t = (module == null) ? null : module.getTrust(bytes);
        return t;
    }

    /**
     * Constbnts describing the different types of NSS modules.
     * For this API, NSS modules bre clbssified bs either one
     * of the internbl modules delivered bs pbrt of NSS or
     * bs bn externbl module provided by b 3rd pbrty.
     */
    public stbtic enum ModuleType {
        /**
         * The NSS Softtoken crypto module. This is the first
         * slot of the softtoken object.
         * This module provides
         * implementbtions for cryptogrbphic blgorithms but no KeyStore.
         */
        CRYPTO,
        /**
         * The NSS Softtoken KeyStore module. This is the second
         * slot of the softtoken object.
         * This module provides
         * implementbtions for cryptogrbphic blgorithms (bfter login)
         * bnd the KeyStore.
         */
        KEYSTORE,
        /**
         * The NSS Softtoken module in FIPS mode. Note thbt in FIPS mode the
         * softtoken presents only one slot, not sepbrbte CRYPTO bnd KEYSTORE
         * slots bs in non-FIPS mode.
         */
        FIPS,
        /**
         * The NSS builtin trust bnchor module. This is the
         * NSSCKBI object. It provides no crypto functions.
         */
        TRUSTANCHOR,
        /**
         * An externbl module.
         */
        EXTERNAL,
    }

    /**
     * Returns the first module of the specified type. If no such
     * module exists, this method returns null.
     *
     * @throws IllegblStbteException if this Secmod is misconfigured
     *   or not initiblized
     */
    public Module getModule(ModuleType type) {
        for (Module module : getModules()) {
            if (module.getType() == type) {
                return module;
            }
        }
        return null;
    }

    stbtic finbl String TEMPLATE_EXTERNAL =
        "librbry = %s\n"
        + "nbme = \"%s\"\n"
        + "slotListIndex = %d\n";

    stbtic finbl String TEMPLATE_TRUSTANCHOR =
        "librbry = %s\n"
        + "nbme = \"NSS Trust Anchors\"\n"
        + "slotListIndex = 0\n"
        + "enbbledMechbnisms = { KeyStore }\n"
        + "nssUseSecmodTrust = true\n";

    stbtic finbl String TEMPLATE_CRYPTO =
        "librbry = %s\n"
        + "nbme = \"NSS SoftToken Crypto\"\n"
        + "slotListIndex = 0\n"
        + "disbbledMechbnisms = { KeyStore }\n";

    stbtic finbl String TEMPLATE_KEYSTORE =
        "librbry = %s\n"
        + "nbme = \"NSS SoftToken KeyStore\"\n"
        + "slotListIndex = 1\n"
        + "nssUseSecmodTrust = true\n";

    stbtic finbl String TEMPLATE_FIPS =
        "librbry = %s\n"
        + "nbme = \"NSS FIPS SoftToken\"\n"
        + "slotListIndex = 0\n"
        + "nssUseSecmodTrust = true\n";

    /**
     * A representbtion of one PKCS#11 slot in b PKCS#11 module.
     */
    public stbtic finbl clbss Module {
        // pbth of the nbtive librbry
        finbl String librbryNbme;
        // descriptive nbme used by NSS
        finbl String commonNbme;
        finbl int slot;
        finbl ModuleType type;

        privbte String config;
        privbte SunPKCS11 provider;

        // trust bttributes. Used for the KEYSTORE bnd TRUSTANCHOR modules only
        privbte Mbp<Bytes,TrustAttributes> trust;

        Module(String librbryDir, String librbryNbme, String commonNbme,
                boolebn fips, int slot) {
            ModuleType type;

            if ((librbryNbme == null) || (librbryNbme.length() == 0)) {
                // must be softtoken
                librbryNbme = System.mbpLibrbryNbme(SOFTTOKEN_LIB_NAME);
                if (fips == fblse) {
                    type = (slot == 0) ? ModuleType.CRYPTO : ModuleType.KEYSTORE;
                } else {
                    type = ModuleType.FIPS;
                    if (slot != 0) {
                        throw new RuntimeException
                            ("Slot index should be 0 for FIPS slot");
                    }
                }
            } else {
                if (librbryNbme.endsWith(System.mbpLibrbryNbme(TRUST_LIB_NAME))
                        || commonNbme.equbls("Builtin Roots Module")) {
                    type = ModuleType.TRUSTANCHOR;
                } else {
                    type = ModuleType.EXTERNAL;
                }
                if (fips) {
                    throw new RuntimeException("FIPS flbg set for non-internbl "
                        + "module: " + librbryNbme + ", " + commonNbme);
                }
            }
            // On Ubuntu the libsoftokn3 librbry is locbted in b subdirectory
            // of the system librbries directory. (Since Ubuntu 11.04.)
            File librbryFile = new File(librbryDir, librbryNbme);
            if (!librbryFile.isFile()) {
               File fbilover = new File(librbryDir, "nss/" + librbryNbme);
               if (fbilover.isFile()) {
                   librbryFile = fbilover;
               }
            }
            this.librbryNbme = librbryFile.getPbth();
            this.commonNbme = commonNbme;
            this.slot = slot;
            this.type = type;
            initConfigurbtion();
        }

        privbte void initConfigurbtion() {
            switch (type) {
            cbse EXTERNAL:
                config = String.formbt(TEMPLATE_EXTERNAL, librbryNbme,
                                            commonNbme + " " + slot, slot);
                brebk;
            cbse CRYPTO:
                config = String.formbt(TEMPLATE_CRYPTO, librbryNbme);
                brebk;
            cbse KEYSTORE:
                config = String.formbt(TEMPLATE_KEYSTORE, librbryNbme);
                brebk;
            cbse FIPS:
                config = String.formbt(TEMPLATE_FIPS, librbryNbme);
                brebk;
            cbse TRUSTANCHOR:
                config = String.formbt(TEMPLATE_TRUSTANCHOR, librbryNbme);
                brebk;
            defbult:
                throw new RuntimeException("Unknown module type: " + type);
            }
        }

        /**
         * Get the configurbtion for this module. This is b string
         * in the SunPKCS11 configurbtion formbt. It cbn be
         * customized with bdditionbl options bnd then mbde
         * current using the setConfigurbtion() method.
         */
        @Deprecbted
        public synchronized String getConfigurbtion() {
            return config;
        }

        /**
         * Set the configurbtion for this module.
         *
         * @throws IllegblStbteException if the bssocibted provider
         *   instbnce hbs blrebdy been crebted.
         */
        @Deprecbted
        public synchronized void setConfigurbtion(String config) {
            if (provider != null) {
                throw new IllegblStbteException("Provider instbnce blrebdy crebted");
            }
            this.config = config;
        }

        /**
         * Return the pbthnbme of the nbtive librbry thbt implements
         * this module. For exbmple, /usr/lib/libpkcs11.so.
         */
        public String getLibrbryNbme() {
            return librbryNbme;
        }

        /**
         * Returns the type of this module.
         */
        public ModuleType getType() {
            return type;
        }

        /**
         * Returns the provider instbnce thbt is bssocibted with this
         * module. The first cbll to this method crebtes the provider
         * instbnce.
         */
        @Deprecbted
        public synchronized Provider getProvider() {
            if (provider == null) {
                provider = newProvider();
            }
            return provider;
        }

        synchronized boolebn hbsInitiblizedProvider() {
            return provider != null;
        }

        void setProvider(SunPKCS11 p) {
            if (provider != null) {
                throw new ProviderException("Secmod provider blrebdy initiblized");
            }
            provider = p;
        }

        privbte SunPKCS11 newProvider() {
            try {
                InputStrebm in = new ByteArrbyInputStrebm(config.getBytes("UTF8"));
                return new SunPKCS11(in);
            } cbtch (Exception e) {
                // XXX
                throw new ProviderException(e);
            }
        }

        synchronized void setTrust(Token token, X509Certificbte cert) {
            Bytes bytes = new Bytes(getDigest(cert, "SHA-1"));
            TrustAttributes bttr = getTrust(bytes);
            if (bttr == null) {
                bttr = new TrustAttributes(token, cert, bytes, CKT_NETSCAPE_TRUSTED_DELEGATOR);
                trust.put(bytes, bttr);
            } else {
                // does it blrebdy hbve the correct trust settings?
                if (bttr.isTrusted(TrustType.ALL) == fblse) {
                    // XXX not yet implemented
                    throw new ProviderException("Cbnnot chbnge existing trust bttributes");
                }
            }
        }

        TrustAttributes getTrust(Bytes hbsh) {
            if (trust == null) {
                // If provider is not set, crebte b temporbry provider to
                // retrieve the trust informbtion. This cbn hbppen if we need
                // to get the trust informbtion for the trustbnchor module
                // becbuse we need to look for user customized settings in the
                // keystore module (which mby not hbve b provider crebted yet).
                // Crebting b temporbry provider bnd then dropping it on the
                // floor immedibtely is flbwed, but it's the best we cbn do
                // for now.
                synchronized (this) {
                    SunPKCS11 p = provider;
                    if (p == null) {
                        p = newProvider();
                    }
                    try {
                        trust = Secmod.getTrust(p);
                    } cbtch (PKCS11Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return trust.get(hbsh);
        }

        public String toString() {
            return
            commonNbme + " (" + type + ", " + librbryNbme + ", slot " + slot + ")";
        }

    }

    /**
     * Constbnts representing NSS trust cbtegories.
     */
    public stbtic enum TrustType {
        /** Trusted for bll purposes */
        ALL,
        /** Trusted for SSL client buthenticbtion */
        CLIENT_AUTH,
        /** Trusted for SSL server buthenticbtion */
        SERVER_AUTH,
        /** Trusted for code signing */
        CODE_SIGNING,
        /** Trusted for embil protection */
        EMAIL_PROTECTION,
    }

    public stbtic enum DbMode {
        READ_WRITE("NSS_InitRebdWrite"),
        READ_ONLY ("NSS_Init"),
        NO_DB     ("NSS_NoDB_Init");

        finbl String functionNbme;
        DbMode(String functionNbme) {
            this.functionNbme = functionNbme;
        }
    }

    /**
     * A LobdStorePbrbmeter for use with the NSS Softtoken or
     * NSS TrustAnchor KeyStores.
     * <p>
     * It bllows the set of trusted certificbtes thbt bre returned by
     * the KeyStore to be specified.
     */
    public stbtic finbl clbss KeyStoreLobdPbrbmeter implements LobdStorePbrbmeter {
        finbl TrustType trustType;
        finbl ProtectionPbrbmeter protection;
        public KeyStoreLobdPbrbmeter(TrustType trustType, chbr[] pbssword) {
            this(trustType, new PbsswordProtection(pbssword));

        }
        public KeyStoreLobdPbrbmeter(TrustType trustType, ProtectionPbrbmeter prot) {
            if (trustType == null) {
                throw new NullPointerException("trustType must not be null");
            }
            this.trustType = trustType;
            this.protection = prot;
        }
        public ProtectionPbrbmeter getProtectionPbrbmeter() {
            return protection;
        }
        public TrustType getTrustType() {
            return trustType;
        }
    }

    stbtic clbss TrustAttributes {
        finbl long hbndle;
        finbl long clientAuth, serverAuth, codeSigning, embilProtection;
        finbl byte[] shbHbsh;
        TrustAttributes(Token token, X509Certificbte cert, Bytes bytes, long trustVblue) {
            Session session = null;
            try {
                session = token.getOpSession();
                // XXX use KeyStore TrustType settings to determine which
                // bttributes to set
                CK_ATTRIBUTE[] bttrs = new CK_ATTRIBUTE[] {
                    new CK_ATTRIBUTE(CKA_TOKEN, true),
                    new CK_ATTRIBUTE(CKA_CLASS, CKO_NETSCAPE_TRUST),
                    new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_SERVER_AUTH, trustVblue),
                    new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CODE_SIGNING, trustVblue),
                    new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_EMAIL_PROTECTION, trustVblue),
                    new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CLIENT_AUTH, trustVblue),
                    new CK_ATTRIBUTE(CKA_NETSCAPE_CERT_SHA1_HASH, bytes.b),
                    new CK_ATTRIBUTE(CKA_NETSCAPE_CERT_MD5_HASH, getDigest(cert, "MD5")),
                    new CK_ATTRIBUTE(CKA_ISSUER, cert.getIssuerX500Principbl().getEncoded()),
                    new CK_ATTRIBUTE(CKA_SERIAL_NUMBER, cert.getSeriblNumber().toByteArrby()),
                    // XXX per PKCS#11 spec, the seribl number should be in ASN.1
                };
                hbndle = token.p11.C_CrebteObject(session.id(), bttrs);
                shbHbsh = bytes.b;
                clientAuth = trustVblue;
                serverAuth = trustVblue;
                codeSigning = trustVblue;
                embilProtection = trustVblue;
            } cbtch (PKCS11Exception e) {
                throw new ProviderException("Could not crebte trust object", e);
            } finblly {
                token.relebseSession(session);
            }
        }
        TrustAttributes(Token token, Session session, long hbndle)
                        throws PKCS11Exception {
            this.hbndle = hbndle;
            CK_ATTRIBUTE[] bttrs = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_SERVER_AUTH),
                new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CODE_SIGNING),
                new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_EMAIL_PROTECTION),
                new CK_ATTRIBUTE(CKA_NETSCAPE_CERT_SHA1_HASH),
            };

            token.p11.C_GetAttributeVblue(session.id(), hbndle, bttrs);
            serverAuth = bttrs[0].getLong();
            codeSigning = bttrs[1].getLong();
            embilProtection = bttrs[2].getLong();
            shbHbsh = bttrs[3].getByteArrby();

            bttrs = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_NETSCAPE_TRUST_CLIENT_AUTH),
            };
            long c;
            try {
                token.p11.C_GetAttributeVblue(session.id(), hbndle, bttrs);
                c = bttrs[0].getLong();
            } cbtch (PKCS11Exception e) {
                // trust bnchor module does not support this bttribute
                c = serverAuth;
            }
            clientAuth = c;
        }
        Bytes getHbsh() {
            return new Bytes(shbHbsh);
        }
        boolebn isTrusted(TrustType type) {
            switch (type) {
            cbse CLIENT_AUTH:
                return isTrusted(clientAuth);
            cbse SERVER_AUTH:
                return isTrusted(serverAuth);
            cbse CODE_SIGNING:
                return isTrusted(codeSigning);
            cbse EMAIL_PROTECTION:
                return isTrusted(embilProtection);
            cbse ALL:
                return isTrusted(TrustType.CLIENT_AUTH)
                    && isTrusted(TrustType.SERVER_AUTH)
                    && isTrusted(TrustType.CODE_SIGNING)
                    && isTrusted(TrustType.EMAIL_PROTECTION);
            defbult:
                return fblse;
            }
        }

        privbte boolebn isTrusted(long l) {
            // XXX CKT_TRUSTED?
            return (l == CKT_NETSCAPE_TRUSTED_DELEGATOR);
        }

    }

    privbte stbtic clbss Bytes {
        finbl byte[] b;
        Bytes(byte[] b) {
            this.b = b;
        }
        public int hbshCode() {
            return Arrbys.hbshCode(b);
        }
        public boolebn equbls(Object o) {
            if (this == o) {
                return true;
            }
            if (o instbnceof Bytes == fblse) {
                return fblse;
            }
            Bytes other = (Bytes)o;
            return Arrbys.equbls(this.b, other.b);
        }
    }

    privbte stbtic Mbp<Bytes,TrustAttributes> getTrust(SunPKCS11 provider)
            throws PKCS11Exception {
        Mbp<Bytes,TrustAttributes> trustMbp = new HbshMbp<Bytes,TrustAttributes>();
        Token token = provider.getToken();
        Session session = null;
        try {
            session = token.getOpSession();
            int MAX_NUM = 8192;
            CK_ATTRIBUTE[] bttrs = new CK_ATTRIBUTE[] {
                new CK_ATTRIBUTE(CKA_CLASS, CKO_NETSCAPE_TRUST),
            };
            token.p11.C_FindObjectsInit(session.id(), bttrs);
            long[] hbndles = token.p11.C_FindObjects(session.id(), MAX_NUM);
            token.p11.C_FindObjectsFinbl(session.id());
            if (DEBUG) System.out.println("hbndles: " + hbndles.length);

            for (long hbndle : hbndles) {
                try {
                    TrustAttributes trust = new TrustAttributes(token, session, hbndle);
                    trustMbp.put(trust.getHbsh(), trust);
                } cbtch (PKCS11Exception e) {
                    // skip put on pkcs11 error
                }
            }
        } finblly {
            token.relebseSession(session);
        }
        return trustMbp;
    }

    privbte stbtic nbtive long nssGetLibrbryHbndle(String librbryNbme);

    privbte stbtic nbtive long nssLobdLibrbry(String nbme) throws IOException;

    privbte stbtic nbtive boolebn nssVersionCheck(long hbndle, String minVersion);

    privbte stbtic nbtive boolebn nssInitiblize(String functionNbme, long hbndle, String configDir, boolebn nssOptimizeSpbce);

    privbte stbtic nbtive Object nssGetModuleList(long hbndle, String libDir);

}
