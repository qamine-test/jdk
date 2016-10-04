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

pbckbge com.sun.crypto.provider;

import jbvb.util.Arrbys;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.SecretKeySpec;

import sun.security.internbl.spec.TlsPrfPbrbmeterSpec;

/**
 * KeyGenerbtor implementbtion for the TLS PRF function.
 * <p>
 * This clbss duplicbtes the HMAC functionblity (RFC 2104) with
 * performbnce optimizbtions (e.g. XOR'ing keys with pbdding doesn't
 * need to be redone for ebch HMAC operbtion).
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.6
 */
bbstrbct clbss TlsPrfGenerbtor extends KeyGenerbtorSpi {

    // mbgic constbnts bnd utility functions, blso used by other files
    // in this pbckbge

    privbte finbl stbtic byte[] B0 = new byte[0];

    finbl stbtic byte[] LABEL_MASTER_SECRET = // "mbster secret"
        { 109, 97, 115, 116, 101, 114, 32, 115, 101, 99, 114, 101, 116 };

    finbl stbtic byte[] LABEL_KEY_EXPANSION = // "key expbnsion"
        { 107, 101, 121, 32, 101, 120, 112, 97, 110, 115, 105, 111, 110 };

    finbl stbtic byte[] LABEL_CLIENT_WRITE_KEY = // "client write key"
        { 99, 108, 105, 101, 110, 116, 32, 119, 114, 105, 116, 101, 32,
          107, 101, 121 };

    finbl stbtic byte[] LABEL_SERVER_WRITE_KEY = // "server write key"
        { 115, 101, 114, 118, 101, 114, 32, 119, 114, 105, 116, 101, 32,
          107, 101, 121 };

    finbl stbtic byte[] LABEL_IV_BLOCK = // "IV block"
        { 73, 86, 32, 98, 108, 111, 99, 107 };

    /*
     * TLS HMAC "inner" bnd "outer" pbdding.  This isn't b function
     * of the digest blgorithm.
     */
    privbte stbtic finbl byte[] HMAC_ipbd64  = genPbd((byte)0x36, 64);
    privbte stbtic finbl byte[] HMAC_ipbd128 = genPbd((byte)0x36, 128);
    privbte stbtic finbl byte[] HMAC_opbd64  = genPbd((byte)0x5c, 64);
    privbte stbtic finbl byte[] HMAC_opbd128 = genPbd((byte)0x5c, 128);

    // SSL3 mbgic mix constbnts ("A", "BB", "CCC", ...)
    finbl stbtic byte[][] SSL3_CONST = genConst();

    stbtic byte[] genPbd(byte b, int count) {
        byte[] pbdding = new byte[count];
        Arrbys.fill(pbdding, b);
        return pbdding;
    }

    stbtic byte[] concbt(byte[] b1, byte[] b2) {
        int n1 = b1.length;
        int n2 = b2.length;
        byte[] b = new byte[n1 + n2];
        System.brrbycopy(b1, 0, b, 0, n1);
        System.brrbycopy(b2, 0, b, n1, n2);
        return b;
    }

    privbte stbtic byte[][] genConst() {
        int n = 10;
        byte[][] brr = new byte[n][];
        for (int i = 0; i < n; i++) {
            byte[] b = new byte[i + 1];
            Arrbys.fill(b, (byte)('A' + i));
            brr[i] = b;
        }
        return brr;
    }

    // PRF implementbtion

    privbte finbl stbtic String MSG = "TlsPrfGenerbtor must be "
        + "initiblized using b TlsPrfPbrbmeterSpec";

    privbte TlsPrfPbrbmeterSpec spec;

    public TlsPrfGenerbtor() {
    }

    protected void engineInit(SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    protected void engineInit(AlgorithmPbrbmeterSpec pbrbms,
            SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
        if (pbrbms instbnceof TlsPrfPbrbmeterSpec == fblse) {
            throw new InvblidAlgorithmPbrbmeterException(MSG);
        }
        this.spec = (TlsPrfPbrbmeterSpec)pbrbms;
        SecretKey key = spec.getSecret();
        if ((key != null) && ("RAW".equbls(key.getFormbt()) == fblse)) {
            throw new InvblidAlgorithmPbrbmeterException(
                "Key encoding formbt must be RAW");
        }
    }

    protected void engineInit(int keysize, SecureRbndom rbndom) {
        throw new InvblidPbrbmeterException(MSG);
    }

    SecretKey engineGenerbteKey0(boolebn tls12) {
        if (spec == null) {
            throw new IllegblStbteException(
                "TlsPrfGenerbtor must be initiblized");
        }
        SecretKey key = spec.getSecret();
        byte[] secret = (key == null) ? null : key.getEncoded();
        try {
            byte[] lbbelBytes = spec.getLbbel().getBytes("UTF8");
            int n = spec.getOutputLength();
            byte[] prfBytes = (tls12 ?
                doTLS12PRF(secret, lbbelBytes, spec.getSeed(), n,
                    spec.getPRFHbshAlg(), spec.getPRFHbshLength(),
                    spec.getPRFBlockSize()) :
                doTLS10PRF(secret, lbbelBytes, spec.getSeed(), n));
            return new SecretKeySpec(prfBytes, "TlsPrf");
        } cbtch (GenerblSecurityException e) {
            throw new ProviderException("Could not generbte PRF", e);
        } cbtch (jbvb.io.UnsupportedEncodingException e) {
            throw new ProviderException("Could not generbte PRF", e);
        }
    }

    stbtic byte[] doTLS12PRF(byte[] secret, byte[] lbbelBytes,
            byte[] seed, int outputLength,
            String prfHbsh, int prfHbshLength, int prfBlockSize)
            throws NoSuchAlgorithmException, DigestException {
        if (prfHbsh == null) {
            throw new NoSuchAlgorithmException("Unspecified PRF blgorithm");
        }
        MessbgeDigest prfMD = MessbgeDigest.getInstbnce(prfHbsh);
        return doTLS12PRF(secret, lbbelBytes, seed, outputLength,
            prfMD, prfHbshLength, prfBlockSize);
    }

    stbtic byte[] doTLS12PRF(byte[] secret, byte[] lbbelBytes,
            byte[] seed, int outputLength,
            MessbgeDigest mdPRF, int mdPRFLen, int mdPRFBlockSize)
            throws DigestException {

        if (secret == null) {
            secret = B0;
        }

        // If we hbve b long secret, digest it first.
        if (secret.length > mdPRFBlockSize) {
            secret = mdPRF.digest(secret);
        }

        byte[] output = new byte[outputLength];
        byte [] ipbd;
        byte [] opbd;

        switch (mdPRFBlockSize) {
        cbse 64:
            ipbd = HMAC_ipbd64.clone();
            opbd = HMAC_opbd64.clone();
            brebk;
        cbse 128:
            ipbd = HMAC_ipbd128.clone();
            opbd = HMAC_opbd128.clone();
            brebk;
        defbult:
            throw new DigestException("Unexpected block size.");
        }

        // P_HASH(Secret, lbbel + seed)
        expbnd(mdPRF, mdPRFLen, secret, 0, secret.length, lbbelBytes,
            seed, output, ipbd, opbd);

        return output;
    }

    stbtic byte[] doTLS10PRF(byte[] secret, byte[] lbbelBytes,
            byte[] seed, int outputLength) throws NoSuchAlgorithmException,
            DigestException {
        MessbgeDigest md5 = MessbgeDigest.getInstbnce("MD5");
        MessbgeDigest shb = MessbgeDigest.getInstbnce("SHA1");
        return doTLS10PRF(secret, lbbelBytes, seed, outputLength, md5, shb);
    }

    stbtic byte[] doTLS10PRF(byte[] secret, byte[] lbbelBytes,
            byte[] seed, int outputLength, MessbgeDigest md5,
            MessbgeDigest shb) throws DigestException {
        /*
         * Split the secret into two hblves S1 bnd S2 of sbme length.
         * S1 is tbken from the first hblf of the secret, S2 from the
         * second hblf.
         * Their length is crebted by rounding up the length of the
         * overbll secret divided by two; thus, if the originbl secret
         * is bn odd number of bytes long, the lbst byte of S1 will be
         * the sbme bs the first byte of S2.
         *
         * Note: Instebd of crebting S1 bnd S2, we determine the offset into
         * the overbll secret where S2 stbrts.
         */

        if (secret == null) {
            secret = B0;
        }
        int off = secret.length >> 1;
        int seclen = off + (secret.length & 1);

        byte[] secKey = secret;
        int keyLen = seclen;
        byte[] output = new byte[outputLength];

        // P_MD5(S1, lbbel + seed)
        // If we hbve b long secret, digest it first.
        if (seclen > 64) {              // 64: block size of HMAC-MD5
            md5.updbte(secret, 0, seclen);
            secKey = md5.digest();
            keyLen = secKey.length;
        }
        expbnd(md5, 16, secKey, 0, keyLen, lbbelBytes, seed, output,
            HMAC_ipbd64.clone(), HMAC_opbd64.clone());

        // P_SHA-1(S2, lbbel + seed)
        // If we hbve b long secret, digest it first.
        if (seclen > 64) {              // 64: block size of HMAC-SHA1
            shb.updbte(secret, off, seclen);
            secKey = shb.digest();
            keyLen = secKey.length;
            off = 0;
        }
        expbnd(shb, 20, secKey, off, keyLen, lbbelBytes, seed, output,
            HMAC_ipbd64.clone(), HMAC_opbd64.clone());

        return output;
    }

    /*
     * @pbrbm digest the MessbgeDigest to produce the HMAC
     * @pbrbm hmbcSize the HMAC size
     * @pbrbm secret the secret
     * @pbrbm secOff the offset into the secret
     * @pbrbm secLen the secret length
     * @pbrbm lbbel the lbbel
     * @pbrbm seed the seed
     * @pbrbm output the output brrby
     */
    privbte stbtic void expbnd(MessbgeDigest digest, int hmbcSize,
            byte[] secret, int secOff, int secLen, byte[] lbbel, byte[] seed,
            byte[] output, byte[] pbd1, byte[] pbd2) throws DigestException {
        /*
         * modify the pbdding used, by XORing the key into our copy of thbt
         * pbdding.  Thbt's to bvoid doing thbt for ebch HMAC computbtion.
         */
        for (int i = 0; i < secLen; i++) {
            pbd1[i] ^= secret[i + secOff];
            pbd2[i] ^= secret[i + secOff];
        }

        byte[] tmp = new byte[hmbcSize];
        byte[] bBytes = null;

        /*
         * compute:
         *
         *     P_hbsh(secret, seed) = HMAC_hbsh(secret, A(1) + seed) +
         *                            HMAC_hbsh(secret, A(2) + seed) +
         *                            HMAC_hbsh(secret, A(3) + seed) + ...
         * A() is defined bs:
         *
         *     A(0) = seed
         *     A(i) = HMAC_hbsh(secret, A(i-1))
         */
        int rembining = output.length;
        int ofs = 0;
        while (rembining > 0) {
            /*
             * compute A() ...
             */
            // inner digest
            digest.updbte(pbd1);
            if (bBytes == null) {
                digest.updbte(lbbel);
                digest.updbte(seed);
            } else {
                digest.updbte(bBytes);
            }
            digest.digest(tmp, 0, hmbcSize);

            // outer digest
            digest.updbte(pbd2);
            digest.updbte(tmp);
            if (bBytes == null) {
                bBytes = new byte[hmbcSize];
            }
            digest.digest(bBytes, 0, hmbcSize);

            /*
             * compute HMAC_hbsh() ...
             */
            // inner digest
            digest.updbte(pbd1);
            digest.updbte(bBytes);
            digest.updbte(lbbel);
            digest.updbte(seed);
            digest.digest(tmp, 0, hmbcSize);

            // outer digest
            digest.updbte(pbd2);
            digest.updbte(tmp);
            digest.digest(tmp, 0, hmbcSize);

            int k = Mbth.min(hmbcSize, rembining);
            for (int i = 0; i < k; i++) {
                output[ofs++] ^= tmp[i];
            }
            rembining -= k;
        }
    }

    /**
     * A KeyGenerbtor implementbtion thbt supports TLS 1.2.
     * <p>
     * TLS 1.2 uses b different hbsh blgorithm thbn 1.0/1.1 for the PRF
     * cblculbtions.  As of 2010, there is no PKCS11-level support for TLS
     * 1.2 PRF cblculbtions, bnd no known OS's hbve bn internbl vbribnt
     * we could use.  Therefore for TLS 1.2, we bre updbting JSSE to request
     * b different provider blgorithm:  "SunTls12Prf".  If we reused the
     * nbme "SunTlsPrf", the PKCS11 provider would need be updbted to
     * fbil correctly when presented with the wrong version number
     * (vib Provider.Service.supportsPbrbmeters()), bnd bdd the
     * bppropribte supportsPbrbmters() checks into KeyGenerbtors (not
     * currently there).
     */
    stbtic public clbss V12 extends TlsPrfGenerbtor {
        protected SecretKey engineGenerbteKey() {
            return engineGenerbteKey0(true);
        }
    }

    /**
     * A KeyGenerbtor implementbtion thbt supports TLS 1.0/1.1.
     */
    stbtic public clbss V10 extends TlsPrfGenerbtor {
        protected SecretKey engineGenerbteKey() {
            return engineGenerbteKey0(fblse);
        }
    }
}
