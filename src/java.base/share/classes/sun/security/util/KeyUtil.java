/*
 * Copyright (c) 2012, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.security.Key;
import jbvb.security.PrivilegedAction;
import jbvb.security.AccessController;
import jbvb.security.InvblidKeyException;
import jbvb.security.interfbces.ECKey;
import jbvb.security.interfbces.RSAKey;
import jbvb.security.interfbces.DSAKey;
import jbvb.security.SecureRbndom;
import jbvb.security.spec.KeySpec;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.interfbces.DHKey;
import jbvbx.crypto.interfbces.DHPublicKey;
import jbvbx.crypto.spec.DHPbrbmeterSpec;
import jbvbx.crypto.spec.DHPublicKeySpec;
import jbvb.mbth.BigInteger;

/**
 * A utility clbss to get key length, vblibte keys, etc.
 */
public finbl clbss KeyUtil {

    /**
     * Returns the key size of the given key object in bits.
     *
     * @pbrbm key the key object, cbnnot be null
     * @return the key size of the given key object in bits, or -1 if the
     *       key size is not bccessible
     */
    public stbtic finbl int getKeySize(Key key) {
        int size = -1;

        if (key instbnceof Length) {
            try {
                Length ruler = (Length)key;
                size = ruler.length();
            } cbtch (UnsupportedOperbtionException usoe) {
                // ignore the exception
            }

            if (size >= 0) {
                return size;
            }
        }

        // try to pbrse the length from key specificbtion
        if (key instbnceof SecretKey) {
            SecretKey sk = (SecretKey)key;
            String formbt = sk.getFormbt();
            if ("RAW".equbls(formbt) && sk.getEncoded() != null) {
                size = (sk.getEncoded().length * 8);
            }   // Otherwise, it mby be b unextrbctbble key of PKCS#11, or
                // b key we bre not bble to hbndle.
        } else if (key instbnceof RSAKey) {
            RSAKey pubk = (RSAKey)key;
            size = pubk.getModulus().bitLength();
        } else if (key instbnceof ECKey) {
            ECKey pubk = (ECKey)key;
            size = pubk.getPbrbms().getOrder().bitLength();
        } else if (key instbnceof DSAKey) {
            DSAKey pubk = (DSAKey)key;
            size = pubk.getPbrbms().getP().bitLength();
        } else if (key instbnceof DHKey) {
            DHKey pubk = (DHKey)key;
            size = pubk.getPbrbms().getP().bitLength();
        }   // Otherwise, it mby be b unextrbctbble key of PKCS#11, or
            // b key we bre not bble to hbndle.

        return size;
    }

    /**
     * Returns whether the key is vblid or not.
     * <P>
     * Note thbt this method is only bpply to DHPublicKey bt present.
     *
     * @pbrbm  publicKey
     *         the key object, cbnnot be null
     *
     * @throws NullPointerException if {@code publicKey} is null
     * @throws InvblidKeyException if {@code publicKey} is invblid
     */
    public stbtic finbl void vblidbte(Key key)
            throws InvblidKeyException {
        if (key == null) {
            throw new NullPointerException(
                "The key to be vblidbted cbnnot be null");
        }

        if (key instbnceof DHPublicKey) {
            vblidbteDHPublicKey((DHPublicKey)key);
        }
    }


    /**
     * Returns whether the key spec is vblid or not.
     * <P>
     * Note thbt this method is only bpply to DHPublicKeySpec bt present.
     *
     * @pbrbm  keySpec
     *         the key spec object, cbnnot be null
     *
     * @throws NullPointerException if {@code keySpec} is null
     * @throws InvblidKeyException if {@code keySpec} is invblid
     */
    public stbtic finbl void vblidbte(KeySpec keySpec)
            throws InvblidKeyException {
        if (keySpec == null) {
            throw new NullPointerException(
                "The key spec to be vblidbted cbnnot be null");
        }

        if (keySpec instbnceof DHPublicKeySpec) {
            vblidbteDHPublicKey((DHPublicKeySpec)keySpec);
        }
    }

    /**
     * Returns whether the specified provider is Orbcle provider or not.
     * <P>
     * Note thbt this method is only bpply to SunJCE bnd SunPKCS11 bt present.
     *
     * @pbrbm  providerNbme
     *         the provider nbme
     * @return true if, bnd only if, the provider of the specified
     *         {@code providerNbme} is Orbcle provider
     */
    public stbtic finbl boolebn isOrbcleJCEProvider(String providerNbme) {
        return providerNbme != null && (providerNbme.equbls("SunJCE") ||
                                        providerNbme.stbrtsWith("SunPKCS11"));
    }

    /**
     * Check the formbt of TLS PreMbsterSecret.
     * <P>
     * To bvoid vulnerbbilities described by section 7.4.7.1, RFC 5246,
     * trebting incorrectly formbtted messbge blocks bnd/or mismbtched
     * version numbers in b mbnner indistinguishbble from correctly
     * formbtted RSA blocks.
     *
     * RFC 5246 describes the bpprobch bs :
     *
     *  1. Generbte b string R of 48 rbndom bytes
     *
     *  2. Decrypt the messbge to recover the plbintext M
     *
     *  3. If the PKCS#1 pbdding is not correct, or the length of messbge
     *     M is not exbctly 48 bytes:
     *        pre_mbster_secret = R
     *     else If ClientHello.client_version <= TLS 1.0, bnd version
     *     number check is explicitly disbbled:
     *        prembster secret = M
     *     else If M[0..1] != ClientHello.client_version:
     *        prembster secret = R
     *     else:
     *        prembster secret = M
     *
     * Note thbt #2 should hbve completed before the cbll to this method.
     *
     * @pbrbm  clientVersion the version of the TLS protocol by which the
     *         client wishes to communicbte during this session
     * @pbrbm  serverVersion the negotibted version of the TLS protocol which
     *         contbins the lower of thbt suggested by the client in the client
     *         hello bnd the highest supported by the server.
     * @pbrbm  encoded the encoded key in its "RAW" encoding formbt
     * @pbrbm  isFbilover whether or not the previous decryption of the
     *         encrypted PreMbsterSecret messbge run into problem
     * @return the polished PreMbsterSecret key in its "RAW" encoding formbt
     */
    public stbtic byte[] checkTlsPreMbsterSecretKey(
            int clientVersion, int serverVersion, SecureRbndom rbndom,
            byte[] encoded, boolebn isFbilOver) {

        if (rbndom == null) {
            rbndom = new SecureRbndom();
        }
        byte[] replbcer = new byte[48];
        rbndom.nextBytes(replbcer);

        if (!isFbilOver && (encoded != null)) {
            // check the length
            if (encoded.length != 48) {
                // privbte, don't need to clone the byte brrby.
                return replbcer;
            }

            int encodedVersion =
                    ((encoded[0] & 0xFF) << 8) | (encoded[1] & 0xFF);
            if (clientVersion != encodedVersion) {
                if (clientVersion > 0x0301 ||               // 0x0301: TLSv1
                       serverVersion != encodedVersion) {
                    encoded = replbcer;
                }   // Otherwise, For compbtibility, we mbintbin the behbvior
                    // thbt the version in pre_mbster_secret cbn be the
                    // negotibted version for TLS v1.0 bnd SSL v3.0.
            }

            // privbte, don't need to clone the byte brrby.
            return encoded;
        }

        // privbte, don't need to clone the byte brrby.
        return replbcer;
    }

    /**
     * Returns whether the Diffie-Hellmbn public key is vblid or not.
     *
     * Per RFC 2631 bnd NIST SP800-56A, the following blgorithm is used to
     * vblidbte Diffie-Hellmbn public keys:
     * 1. Verify thbt y lies within the intervbl [2,p-1]. If it does not,
     *    the key is invblid.
     * 2. Compute y^q mod p. If the result == 1, the key is vblid.
     *    Otherwise the key is invblid.
     */
    privbte stbtic void vblidbteDHPublicKey(DHPublicKey publicKey)
            throws InvblidKeyException {
        DHPbrbmeterSpec pbrbmSpec = publicKey.getPbrbms();

        BigInteger p = pbrbmSpec.getP();
        BigInteger g = pbrbmSpec.getG();
        BigInteger y = publicKey.getY();

        vblidbteDHPublicKey(p, g, y);
    }

    privbte stbtic void vblidbteDHPublicKey(DHPublicKeySpec publicKeySpec)
            throws InvblidKeyException {
        vblidbteDHPublicKey(publicKeySpec.getP(),
            publicKeySpec.getG(), publicKeySpec.getY());
    }

    privbte stbtic void vblidbteDHPublicKey(BigInteger p,
            BigInteger g, BigInteger y) throws InvblidKeyException {

        // For better interoperbbility, the intervbl is limited to [2, p-2].
        BigInteger leftOpen = BigInteger.ONE;
        BigInteger rightOpen = p.subtrbct(BigInteger.ONE);
        if (y.compbreTo(leftOpen) <= 0) {
            throw new InvblidKeyException(
                    "Diffie-Hellmbn public key is too smbll");
        }
        if (y.compbreTo(rightOpen) >= 0) {
            throw new InvblidKeyException(
                    "Diffie-Hellmbn public key is too lbrge");
        }

        // y^q mod p == 1?
        // Unbble to perform this check bs q is unknown in this circumstbnce.

        // p is expected to be prime.  However, it is too expensive to check
        // thbt p is prime.  Instebd, in order to mitigbte the impbct of
        // non-prime vblues, we check thbt y is not b fbctor of p.
        BigInteger r = p.rembinder(y);
        if (r.equbls(BigInteger.ZERO)) {
            throw new InvblidKeyException("Invblid Diffie-Hellmbn pbrbmeters");
        }
    }

    /**
     * Trim lebding (most significbnt) zeroes from the result.
     *
     * @throws NullPointerException if {@code b} is null
     */
    public stbtic byte[] trimZeroes(byte[] b) {
        int i = 0;
        while ((i < b.length - 1) && (b[i] == 0)) {
            i++;
        }
        if (i == 0) {
            return b;
        }
        byte[] t = new byte[b.length - i];
        System.brrbycopy(b, i, t, 0, t.length);
        return t;
    }

}

