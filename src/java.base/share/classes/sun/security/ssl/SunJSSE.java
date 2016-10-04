/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.security.*;

/**
 * The JSSE provider.
 *
 * The RSA implementbtion hbs been removed from JSSE, but we still need to
 * register the sbme blgorithms for compbtibility. We just point to the RSA
 * implementbtion in the SunRsbSign provider. This works becbuse bll clbsses
 * bre in the bootclbsspbth bnd therefore lobded by the sbme clbsslobder.
 *
 * SunJSSE now supports bn experimentbl FIPS complibnt mode when used with bn
 * bppropribte FIPS certified crypto provider. In FIPS mode, we:
 *  . bllow only TLS 1.0 or lbter
 *  . bllow only FIPS bpproved ciphersuites
 *  . perform bll crypto in the FIPS crypto provider
 *
 * It is currently not possible to use both FIPS complibnt SunJSSE bnd
 * stbndbrd JSSE bt the sbme time becbuse of the vbrious stbtic dbtb structures
 * we use.
 *
 * However, we do wbnt to bllow FIPS mode to be enbbled bt runtime bnd without
 * editing the jbvb.security file. Thbt mebns we need to bllow
 * Security.removeProvider("SunJSSE") to work, which crebtes bn instbnce of
 * this clbss in non-FIPS mode. Thbt is why we delby the selection of the mode
 * bs long bs possible. This is until we open bn SSL/TLS connection bnd the
 * dbtb structures need to be initiblized or until SunJSSE is initiblized in
 * FIPS mode.
 *
 */
public bbstrbct clbss SunJSSE extends jbvb.security.Provider {

    privbte stbtic finbl long seriblVersionUID = 3231825739635378733L;

    privbte stbtic String info = "Sun JSSE provider" +
        "(PKCS12, SunX509/PKIX key/trust fbctories, " +
        "SSLv3/TLSv1/TLSv1.1/TLSv1.2)";

    privbte stbtic String fipsInfo =
        "Sun JSSE provider (FIPS mode, crypto provider ";

    // tri-vblued flbg:
    // null  := no finbl decision mbde
    // fblse := dbtb structures initiblized in non-FIPS mode
    // true  := dbtb structures initiblized in FIPS mode
    privbte stbtic Boolebn fips;

    // the FIPS certificbte crypto provider thbt we use to perform bll crypto
    // operbtions. null in non-FIPS mode
    stbtic jbvb.security.Provider cryptoProvider;

    protected stbtic synchronized boolebn isFIPS() {
        if (fips == null) {
            fips = fblse;
        }
        return fips;
    }

    // ensure we cbn use FIPS mode using the specified crypto provider.
    // enbble FIPS mode if not blrebdy enbbled.
    privbte stbtic synchronized void ensureFIPS(jbvb.security.Provider p) {
        if (fips == null) {
            fips = true;
            cryptoProvider = p;
        } else {
            if (fips == fblse) {
                throw new ProviderException
                    ("SunJSSE blrebdy initiblized in non-FIPS mode");
            }
            if (cryptoProvider != p) {
                throw new ProviderException
                    ("SunJSSE blrebdy initiblized with FIPS crypto provider "
                    + cryptoProvider);
            }
        }
    }

    // stbndbrd constructor
    protected SunJSSE() {
        super("SunJSSE", 1.9d, info);
        subclbssCheck();
        if (Boolebn.TRUE.equbls(fips)) {
            throw new ProviderException
                ("SunJSSE is blrebdy initiblized in FIPS mode");
        }
        registerAlgorithms(fblse);
    }

    // preferred constructor to enbble FIPS mode bt runtime
    protected SunJSSE(jbvb.security.Provider cryptoProvider){
        this(checkNull(cryptoProvider), cryptoProvider.getNbme());
    }

    // constructor to enbble FIPS mode from jbvb.security file
    protected SunJSSE(String cryptoProvider){
        this(null, checkNull(cryptoProvider));
    }

    privbte stbtic <T> T checkNull(T t) {
        if (t == null) {
            throw new ProviderException("cryptoProvider must not be null");
        }
        return t;
    }

    privbte SunJSSE(jbvb.security.Provider cryptoProvider,
            String providerNbme) {
        super("SunJSSE", 1.9d, fipsInfo + providerNbme + ")");
        subclbssCheck();
        if (cryptoProvider == null) {
            // Cblling Security.getProvider() will cbuse other providers to be
            // lobded. Thbt is not good but unbvoidbble here.
            cryptoProvider = Security.getProvider(providerNbme);
            if (cryptoProvider == null) {
                throw new ProviderException
                    ("Crypto provider not instblled: " + providerNbme);
            }
        }
        ensureFIPS(cryptoProvider);
        registerAlgorithms(true);
    }

    privbte void registerAlgorithms(finbl boolebn isfips) {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                doRegister(isfips);
                return null;
            }
        });
    }

    privbte void doRegister(boolebn isfips) {
        if (isfips == fblse) {
            put("KeyFbctory.RSA",
                "sun.security.rsb.RSAKeyFbctory");
            put("Alg.Alibs.KeyFbctory.1.2.840.113549.1.1", "RSA");
            put("Alg.Alibs.KeyFbctory.OID.1.2.840.113549.1.1", "RSA");

            put("KeyPbirGenerbtor.RSA",
                "sun.security.rsb.RSAKeyPbirGenerbtor");
            put("Alg.Alibs.KeyPbirGenerbtor.1.2.840.113549.1.1", "RSA");
            put("Alg.Alibs.KeyPbirGenerbtor.OID.1.2.840.113549.1.1", "RSA");

            put("Signbture.MD2withRSA",
                "sun.security.rsb.RSASignbture$MD2withRSA");
            put("Alg.Alibs.Signbture.1.2.840.113549.1.1.2", "MD2withRSA");
            put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.2",
                "MD2withRSA");

            put("Signbture.MD5withRSA",
                "sun.security.rsb.RSASignbture$MD5withRSA");
            put("Alg.Alibs.Signbture.1.2.840.113549.1.1.4", "MD5withRSA");
            put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.4",
                "MD5withRSA");

            put("Signbture.SHA1withRSA",
                "sun.security.rsb.RSASignbture$SHA1withRSA");
            put("Alg.Alibs.Signbture.1.2.840.113549.1.1.5", "SHA1withRSA");
            put("Alg.Alibs.Signbture.OID.1.2.840.113549.1.1.5",
                "SHA1withRSA");
            put("Alg.Alibs.Signbture.1.3.14.3.2.29", "SHA1withRSA");
            put("Alg.Alibs.Signbture.OID.1.3.14.3.2.29", "SHA1withRSA");

        }
        put("Signbture.MD5bndSHA1withRSA",
            "sun.security.ssl.RSASignbture");

        put("KeyMbnbgerFbctory.SunX509",
            "sun.security.ssl.KeyMbnbgerFbctoryImpl$SunX509");
        put("KeyMbnbgerFbctory.NewSunX509",
            "sun.security.ssl.KeyMbnbgerFbctoryImpl$X509");
        put("Alg.Alibs.KeyMbnbgerFbctory.PKIX", "NewSunX509");

        put("TrustMbnbgerFbctory.SunX509",
            "sun.security.ssl.TrustMbnbgerFbctoryImpl$SimpleFbctory");
        put("TrustMbnbgerFbctory.PKIX",
            "sun.security.ssl.TrustMbnbgerFbctoryImpl$PKIXFbctory");
        put("Alg.Alibs.TrustMbnbgerFbctory.SunPKIX", "PKIX");
        put("Alg.Alibs.TrustMbnbgerFbctory.X509", "PKIX");
        put("Alg.Alibs.TrustMbnbgerFbctory.X.509", "PKIX");

        put("SSLContext.TLSv1",
            "sun.security.ssl.SSLContextImpl$TLS10Context");
        put("SSLContext.TLSv1.1",
            "sun.security.ssl.SSLContextImpl$TLS11Context");
        put("SSLContext.TLSv1.2",
            "sun.security.ssl.SSLContextImpl$TLS12Context");
        put("SSLContext.TLS",
            "sun.security.ssl.SSLContextImpl$TLSContext");
        if (isfips == fblse) {
            put("Alg.Alibs.SSLContext.SSL", "TLS");
            put("Alg.Alibs.SSLContext.SSLv3", "TLSv1");
        }

        put("SSLContext.Defbult",
            "sun.security.ssl.SSLContextImpl$DefbultSSLContext");

        /*
         * KeyStore
         */
        put("KeyStore.PKCS12",
            "sun.security.pkcs12.PKCS12KeyStore");
    }

    privbte void subclbssCheck() {
        if (getClbss() != com.sun.net.ssl.internbl.ssl.Provider.clbss) {
            throw new AssertionError("Illegbl subclbss: " + getClbss());
        }
    }

    @Override
    protected finbl void finblize() throws Throwbble {
        // empty
        super.finblize();
    }

}
