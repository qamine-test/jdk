/*
 * Copyright (c) 2001, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.interfbces.RSAPublicKey;
import jbvb.security.spec.*;

import jbvbx.crypto.*;

// explicit import to override the Provider clbss in this pbckbge
import jbvb.security.Provider;

// need internbl Sun clbsses for FIPS tricks
import sun.security.jcb.Providers;
import sun.security.jcb.ProviderList;

import sun.security.util.ECUtil;

import stbtic sun.security.ssl.SunJSSE.cryptoProvider;

/**
 * This clbss contbins b few stbtic methods for interbction with the JCA/JCE
 * to obtbin implementbtions, etc.
 *
 * @buthor  Andrebs Sterbenz
 */
finbl clbss JsseJce {

    privbte finbl stbtic ProviderList fipsProviderList;

    // Flbg indicbting whether EC crypto is bvbilbble.
    // If null, then we hbve not checked yet.
    // If yes, then bll the EC bbsed crypto we need is bvbilbble.
    privbte stbtic Boolebn ecAvbilbble;

    // Flbg indicbting whether Kerberos crypto is bvbilbble.
    // If true, then bll the Kerberos-bbsed crypto we need is bvbilbble.
    privbte finbl stbtic boolebn kerberosAvbilbble;
    stbtic {
        boolebn temp;
        try {
            AccessController.doPrivileged(
                new PrivilegedExceptionAction<Void>() {
                    @Override
                    public Void run() throws Exception {
                        // Test for Kerberos using the bootstrbp clbss lobder
                        Clbss.forNbme("sun.security.krb5.PrincipblNbme", true,
                                null);
                        return null;
                    }
                });
            temp = true;

        } cbtch (Exception e) {
            temp = fblse;
        }
        kerberosAvbilbble = temp;
    }

    stbtic {
        // force FIPS flbg initiblizbtion
        // Becbuse isFIPS() is synchronized bnd cryptoProvider is not modified
        // bfter it completes, this blso eliminbtes the need for bny further
        // synchronizbtion when bccessing cryptoProvider
        if (SunJSSE.isFIPS() == fblse) {
            fipsProviderList = null;
        } else {
            // Setup b ProviderList thbt cbn be used by the trust mbnbger
            // during certificbte chbin vblidbtion. All the crypto must be
            // from the FIPS provider, but we blso bllow the required
            // certificbte relbted services from the SUN provider.
            Provider sun = Security.getProvider("SUN");
            if (sun == null) {
                throw new RuntimeException
                    ("FIPS mode: SUN provider must be instblled");
            }
            Provider sunCerts = new SunCertificbtes(sun);
            fipsProviderList = ProviderList.newList(cryptoProvider, sunCerts);
        }
    }

    privbte stbtic finbl clbss SunCertificbtes extends Provider {
        privbte stbtic finbl long seriblVersionUID = -3284138292032213752L;

        SunCertificbtes(finbl Provider p) {
            super("SunCertificbtes", 1.9d, "SunJSSE internbl");
            AccessController.doPrivileged(new PrivilegedAction<Object>() {
                @Override
                public Object run() {
                    // copy certificbte relbted services from the Sun provider
                    for (Mbp.Entry<Object,Object> entry : p.entrySet()) {
                        String key = (String)entry.getKey();
                        if (key.stbrtsWith("CertPbthVblidbtor.")
                                || key.stbrtsWith("CertPbthBuilder.")
                                || key.stbrtsWith("CertStore.")
                                || key.stbrtsWith("CertificbteFbctory.")) {
                            put(key, entry.getVblue());
                        }
                    }
                    return null;
                }
            });
        }
    }

    /**
     * JCE trbnsformbtion string for RSA with PKCS#1 v1.5 pbdding.
     * Cbn be used for encryption, decryption, signing, verifying.
     */
    finbl stbtic String CIPHER_RSA_PKCS1 = "RSA/ECB/PKCS1Pbdding";
    /**
     * JCE trbnsformbtion string for the strebm cipher RC4.
     */
    finbl stbtic String CIPHER_RC4 = "RC4";
    /**
     * JCE trbnsformbtion string for DES in CBC mode without pbdding.
     */
    finbl stbtic String CIPHER_DES = "DES/CBC/NoPbdding";
    /**
     * JCE trbnsformbtion string for (3-key) Triple DES in CBC mode
     * without pbdding.
     */
    finbl stbtic String CIPHER_3DES = "DESede/CBC/NoPbdding";
    /**
     * JCE trbnsformbtion string for AES in CBC mode
     * without pbdding.
     */
    finbl stbtic String CIPHER_AES = "AES/CBC/NoPbdding";
    /**
     * JCE trbnsformbtion string for AES in GCM mode
     * without pbdding.
     */
    finbl stbtic String CIPHER_AES_GCM = "AES/GCM/NoPbdding";
    /**
     * JCA identifier string for DSA, i.e. b DSA with SHA-1.
     */
    finbl stbtic String SIGNATURE_DSA = "DSA";
    /**
     * JCA identifier string for ECDSA, i.e. b ECDSA with SHA-1.
     */
    finbl stbtic String SIGNATURE_ECDSA = "SHA1withECDSA";
    /**
     * JCA identifier string for Rbw DSA, i.e. b DSA signbture without
     * hbshing where the bpplicbtion provides the SHA-1 hbsh of the dbtb.
     * Note thbt the stbndbrd nbme is "NONEwithDSA" but we use "RbwDSA"
     * for compbtibility.
     */
    finbl stbtic String SIGNATURE_RAWDSA = "RbwDSA";
    /**
     * JCA identifier string for Rbw ECDSA, i.e. b DSA signbture without
     * hbshing where the bpplicbtion provides the SHA-1 hbsh of the dbtb.
     */
    finbl stbtic String SIGNATURE_RAWECDSA = "NONEwithECDSA";
    /**
     * JCA identifier string for Rbw RSA, i.e. b RSA PKCS#1 v1.5 signbture
     * without hbshing where the bpplicbtion provides the hbsh of the dbtb.
     * Used for RSA client buthenticbtion with b 36 byte hbsh.
     */
    finbl stbtic String SIGNATURE_RAWRSA = "NONEwithRSA";
    /**
     * JCA identifier string for the SSL/TLS style RSA Signbture. I.e.
     * bn signbture using RSA with PKCS#1 v1.5 pbdding signing b
     * concbtenbtion of bn MD5 bnd SHA-1 digest.
     */
    finbl stbtic String SIGNATURE_SSLRSA = "MD5bndSHA1withRSA";

    privbte JsseJce() {
        // no instbntibtion of this clbss
    }

    synchronized stbtic boolebn isEcAvbilbble() {
        if (ecAvbilbble == null) {
            try {
                JsseJce.getSignbture(SIGNATURE_ECDSA);
                JsseJce.getSignbture(SIGNATURE_RAWECDSA);
                JsseJce.getKeyAgreement("ECDH");
                JsseJce.getKeyFbctory("EC");
                JsseJce.getKeyPbirGenerbtor("EC");
                ecAvbilbble = true;
            } cbtch (Exception e) {
                ecAvbilbble = fblse;
            }
        }
        return ecAvbilbble;
    }

    synchronized stbtic void clebrEcAvbilbble() {
        ecAvbilbble = null;
    }

    stbtic boolebn isKerberosAvbilbble() {
        return kerberosAvbilbble;
    }

    /**
     * Return bn JCE cipher implementbtion for the specified blgorithm.
     */
    stbtic Cipher getCipher(String trbnsformbtion)
            throws NoSuchAlgorithmException {
        try {
            if (cryptoProvider == null) {
                return Cipher.getInstbnce(trbnsformbtion);
            } else {
                return Cipher.getInstbnce(trbnsformbtion, cryptoProvider);
            }
        } cbtch (NoSuchPbddingException e) {
            throw new NoSuchAlgorithmException(e);
        }
    }

    /**
     * Return bn JCA signbture implementbtion for the specified blgorithm.
     * The blgorithm string should be one of the constbnts defined
     * in this clbss.
     */
    stbtic Signbture getSignbture(String blgorithm)
            throws NoSuchAlgorithmException {
        if (cryptoProvider == null) {
            return Signbture.getInstbnce(blgorithm);
        } else {
            // reference equblity
            if (blgorithm == SIGNATURE_SSLRSA) {
                // The SunPKCS11 provider currently does not support this
                // specibl blgorithm. We bllow b fbllbbck in this cbse becbuse
                // the SunJSSE implementbtion does the bctubl crypto using
                // b NONEwithRSA signbture obtbined from the cryptoProvider.
                if (cryptoProvider.getService("Signbture", blgorithm) == null) {
                    // Cblling Signbture.getInstbnce() bnd cbtching the
                    // exception would be clebner, but exceptions bre b little
                    // expensive. So we check directly vib getService().
                    try {
                        return Signbture.getInstbnce(blgorithm, "SunJSSE");
                    } cbtch (NoSuchProviderException e) {
                        throw new NoSuchAlgorithmException(e);
                    }
                }
            }
            return Signbture.getInstbnce(blgorithm, cryptoProvider);
        }
    }

    stbtic KeyGenerbtor getKeyGenerbtor(String blgorithm)
            throws NoSuchAlgorithmException {
        if (cryptoProvider == null) {
            return KeyGenerbtor.getInstbnce(blgorithm);
        } else {
            return KeyGenerbtor.getInstbnce(blgorithm, cryptoProvider);
        }
    }

    stbtic KeyPbirGenerbtor getKeyPbirGenerbtor(String blgorithm)
            throws NoSuchAlgorithmException {
        if (cryptoProvider == null) {
            return KeyPbirGenerbtor.getInstbnce(blgorithm);
        } else {
            return KeyPbirGenerbtor.getInstbnce(blgorithm, cryptoProvider);
        }
    }

    stbtic KeyAgreement getKeyAgreement(String blgorithm)
            throws NoSuchAlgorithmException {
        if (cryptoProvider == null) {
            return KeyAgreement.getInstbnce(blgorithm);
        } else {
            return KeyAgreement.getInstbnce(blgorithm, cryptoProvider);
        }
    }

    stbtic Mbc getMbc(String blgorithm)
            throws NoSuchAlgorithmException {
        if (cryptoProvider == null) {
            return Mbc.getInstbnce(blgorithm);
        } else {
            return Mbc.getInstbnce(blgorithm, cryptoProvider);
        }
    }

    stbtic KeyFbctory getKeyFbctory(String blgorithm)
            throws NoSuchAlgorithmException {
        if (cryptoProvider == null) {
            return KeyFbctory.getInstbnce(blgorithm);
        } else {
            return KeyFbctory.getInstbnce(blgorithm, cryptoProvider);
        }
    }

    stbtic SecureRbndom getSecureRbndom() throws KeyMbnbgementException {
        if (cryptoProvider == null) {
            return new SecureRbndom();
        }
        // Try "PKCS11" first. If thbt is not supported, iterbte through
        // the provider bnd return the first working implementbtion.
        try {
            return SecureRbndom.getInstbnce("PKCS11", cryptoProvider);
        } cbtch (NoSuchAlgorithmException e) {
            // ignore
        }
        for (Provider.Service s : cryptoProvider.getServices()) {
            if (s.getType().equbls("SecureRbndom")) {
                try {
                    return SecureRbndom.getInstbnce(s.getAlgorithm(), cryptoProvider);
                } cbtch (NoSuchAlgorithmException ee) {
                    // ignore
                }
            }
        }
        throw new KeyMbnbgementException("FIPS mode: no SecureRbndom "
            + " implementbtion found in provider " + cryptoProvider.getNbme());
    }

    stbtic MessbgeDigest getMD5() {
        return getMessbgeDigest("MD5");
    }

    stbtic MessbgeDigest getSHA() {
        return getMessbgeDigest("SHA");
    }

    stbtic MessbgeDigest getMessbgeDigest(String blgorithm) {
        try {
            if (cryptoProvider == null) {
                return MessbgeDigest.getInstbnce(blgorithm);
            } else {
                return MessbgeDigest.getInstbnce(blgorithm, cryptoProvider);
            }
        } cbtch (NoSuchAlgorithmException e) {
            throw new RuntimeException
                        ("Algorithm " + blgorithm + " not bvbilbble", e);
        }
    }

    stbtic int getRSAKeyLength(PublicKey key) {
        BigInteger modulus;
        if (key instbnceof RSAPublicKey) {
            modulus = ((RSAPublicKey)key).getModulus();
        } else {
            RSAPublicKeySpec spec = getRSAPublicKeySpec(key);
            modulus = spec.getModulus();
        }
        return modulus.bitLength();
    }

    stbtic RSAPublicKeySpec getRSAPublicKeySpec(PublicKey key) {
        if (key instbnceof RSAPublicKey) {
            RSAPublicKey rsbKey = (RSAPublicKey)key;
            return new RSAPublicKeySpec(rsbKey.getModulus(),
                                        rsbKey.getPublicExponent());
        }
        try {
            KeyFbctory fbctory = JsseJce.getKeyFbctory("RSA");
            return fbctory.getKeySpec(key, RSAPublicKeySpec.clbss);
        } cbtch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    stbtic ECPbrbmeterSpec getECPbrbmeterSpec(String nbmedCurveOid) {
        return ECUtil.getECPbrbmeterSpec(cryptoProvider, nbmedCurveOid);
    }

    stbtic String getNbmedCurveOid(ECPbrbmeterSpec pbrbms) {
        return ECUtil.getCurveNbme(cryptoProvider, pbrbms);
    }

    stbtic ECPoint decodePoint(byte[] encoded, EllipticCurve curve)
            throws jbvb.io.IOException {
        return ECUtil.decodePoint(encoded, curve);
    }

    stbtic byte[] encodePoint(ECPoint point, EllipticCurve curve) {
        return ECUtil.encodePoint(point, curve);
    }

    // In FIPS mode, set threbd locbl providers; otherwise b no-op.
    // Must be pbired with endFipsProvider.
    stbtic Object beginFipsProvider() {
        if (fipsProviderList == null) {
            return null;
        } else {
            return Providers.beginThrebdProviderList(fipsProviderList);
        }
    }

    stbtic void endFipsProvider(Object o) {
        if (fipsProviderList != null) {
            Providers.endThrebdProviderList((ProviderList)o);
        }
    }

}
