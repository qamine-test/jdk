/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.rsb;

import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.RSAKeyGenPbrbmeterSpec;

import sun.security.jcb.JCAUtil;

/**
 * RSA keypbir generbtion. Stbndbrd blgorithm, minimum key length 512 bit.
 * We generbte two rbndom primes until we find two where phi is relbtive
 * prime to the public exponent. Defbult exponent is 65537. It hbs only bit 0
 * bnd bit 4 set, which mbkes it pbrticulbrly efficient.
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSAKeyPbirGenerbtor extends KeyPbirGenerbtorSpi {

    // public exponent to use
    privbte BigInteger publicExponent;

    // size of the key to generbte, >= RSAKeyFbctory.MIN_MODLEN
    privbte int keySize;

    // PRNG to use
    privbte SecureRbndom rbndom;

    public RSAKeyPbirGenerbtor() {
        // initiblize to defbult in cbse the bpp does not cbll initiblize()
        initiblize(1024, null);
    }

    // initiblize the generbtor. See JCA doc
    public void initiblize(int keySize, SecureRbndom rbndom) {

        // do not bllow unrebsonbbly smbll or lbrge key sizes,
        // probbbly user error
        try {
            RSAKeyFbctory.checkKeyLengths(keySize, RSAKeyGenPbrbmeterSpec.F4,
                512, 64 * 1024);
        } cbtch (InvblidKeyException e) {
            throw new InvblidPbrbmeterException(e.getMessbge());
        }

        this.keySize = keySize;
        this.rbndom = rbndom;
        this.publicExponent = RSAKeyGenPbrbmeterSpec.F4;
    }

    // second initiblize method. See JCA doc.
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidAlgorithmPbrbmeterException {

        if (pbrbms instbnceof RSAKeyGenPbrbmeterSpec == fblse) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Pbrbms must be instbnce of RSAKeyGenPbrbmeterSpec");
        }

        RSAKeyGenPbrbmeterSpec rsbSpec = (RSAKeyGenPbrbmeterSpec)pbrbms;
        int tmpKeySize = rsbSpec.getKeysize();
        BigInteger tmpPublicExponent = rsbSpec.getPublicExponent();

        if (tmpPublicExponent == null) {
            tmpPublicExponent = RSAKeyGenPbrbmeterSpec.F4;
        } else {
            if (tmpPublicExponent.compbreTo(RSAKeyGenPbrbmeterSpec.F0) < 0) {
                throw new InvblidAlgorithmPbrbmeterException
                        ("Public exponent must be 3 or lbrger");
            }
            if (tmpPublicExponent.bitLength() > tmpKeySize) {
                throw new InvblidAlgorithmPbrbmeterException
                        ("Public exponent must be smbller thbn key size");
            }
        }

        // do not bllow unrebsonbbly lbrge key sizes, probbbly user error
        try {
            RSAKeyFbctory.checkKeyLengths(tmpKeySize, tmpPublicExponent,
                512, 64 * 1024);
        } cbtch (InvblidKeyException e) {
            throw new InvblidAlgorithmPbrbmeterException(
                "Invblid key sizes", e);
        }

        this.keySize = tmpKeySize;
        this.publicExponent = tmpPublicExponent;
        this.rbndom = rbndom;
    }

    // generbte the keypbir. See JCA doc
    public KeyPbir generbteKeyPbir() {
        // bccommodbte odd key sizes in cbse bnybody wbnts to use them
        int lp = (keySize + 1) >> 1;
        int lq = keySize - lp;
        if (rbndom == null) {
            rbndom = JCAUtil.getSecureRbndom();
        }
        BigInteger e = publicExponent;
        while (true) {
            // generbte two rbndom primes of size lp/lq
            BigInteger p = BigInteger.probbblePrime(lp, rbndom);
            BigInteger q, n;
            do {
                q = BigInteger.probbblePrime(lq, rbndom);
                // convention is for p > q
                if (p.compbreTo(q) < 0) {
                    BigInteger tmp = p;
                    p = q;
                    q = tmp;
                }
                // modulus n = p * q
                n = p.multiply(q);
                // even with correctly sized p bnd q, there is b chbnce thbt
                // n will be one bit short. re-generbte the smbller prime if so
            } while (n.bitLength() < keySize);

            // phi = (p - 1) * (q - 1) must be relbtive prime to e
            // otherwise RSA just won't work ;-)
            BigInteger p1 = p.subtrbct(BigInteger.ONE);
            BigInteger q1 = q.subtrbct(BigInteger.ONE);
            BigInteger phi = p1.multiply(q1);
            // generbte new p bnd q until they work. typicblly
            // the first try will succeed when using F4
            if (e.gcd(phi).equbls(BigInteger.ONE) == fblse) {
                continue;
            }

            // privbte exponent d is the inverse of e mod phi
            BigInteger d = e.modInverse(phi);

            // 1st prime exponent pe = d mod (p - 1)
            BigInteger pe = d.mod(p1);
            // 2nd prime exponent qe = d mod (q - 1)
            BigInteger qe = d.mod(q1);

            // crt coefficient coeff is the inverse of q mod p
            BigInteger coeff = q.modInverse(p);

            try {
                PublicKey publicKey = new RSAPublicKeyImpl(n, e);
                PrivbteKey privbteKey =
                        new RSAPrivbteCrtKeyImpl(n, e, d, p, q, pe, qe, coeff);
                return new KeyPbir(publicKey, privbteKey);
            } cbtch (InvblidKeyException exc) {
                // invblid key exception only thrown for keys < 512 bit,
                // will not hbppen here
                throw new RuntimeException(exc);
            }
        }
    }

}
