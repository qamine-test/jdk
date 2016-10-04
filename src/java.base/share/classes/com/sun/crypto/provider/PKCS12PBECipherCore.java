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

pbckbge com.sun.crypto.provider;

import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.security.spec.*;
import jbvb.util.Arrbys;
import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

/**
 * This clbss implements pbssword-bbse encryption blgorithm with
 * SHA1 digest bnd the following Ciphers (in CBC mode, where bpplicbble):
 * - DESede cipher bnd
 * - RC2 Cipher with 40-bit or 128-bit effective key length bnd
 * - RC4 Cipher with 40-bit or 128-bit effective key length
 * bs defined by PKCS #12 version 1.0 stbndbrd.
 *
 * @buthor Vblerie Peng
 * @see jbvbx.crypto.CipherSpi
 */
finbl clbss PKCS12PBECipherCore {

    // TBD: replbce CipherCore with b CipherSpi object to simplify mbintenbnce

    privbte CipherCore cipher;
    privbte int blockSize;
    privbte int keySize;
    privbte String blgo = null;
    privbte String pbeAlgo = null;
    privbte byte[] sblt = null;
    privbte int iCount = 0;

    privbte stbtic finbl int DEFAULT_SALT_LENGTH = 20;
    privbte stbtic finbl int DEFAULT_COUNT = 1024;

    stbtic finbl int CIPHER_KEY = 1;
    stbtic finbl int CIPHER_IV = 2;
    stbtic finbl int MAC_KEY = 3;

    // Uses defbult hbsh blgorithm (SHA-1)
    stbtic byte[] derive(chbr[] chbrs, byte[] sblt,
                         int ic, int n, int type) {
        return derive(chbrs, sblt, ic, n, type, "SHA-1", 64);
    }

    // Uses supplied hbsh blgorithm
    stbtic byte[] derive(chbr[] chbrs, byte[] sblt, int ic, int n, int type,
        String hbshAlgo, int blockLength) {

        // Add in trbiling NULL terminbtor.  Specibl cbse:
        // no terminbtor if pbssword is "\0".
        int length = chbrs.length*2;
        if (length == 2 && chbrs[0] == 0) {
            chbrs = new chbr[0];
            length = 0;
        } else {
            length += 2;
        }

        byte[] pbsswd = new byte[length];
        for (int i = 0, j = 0; i < chbrs.length; i++, j+=2) {
            pbsswd[j] = (byte) ((chbrs[i] >>> 8) & 0xFF);
            pbsswd[j+1] = (byte) (chbrs[i] & 0xFF);
        }
        byte[] key = new byte[n];

        try {
            MessbgeDigest shb = MessbgeDigest.getInstbnce(hbshAlgo);

            int v = blockLength;
            int u = shb.getDigestLength();
            int c = roundup(n, u) / u;
            byte[] D = new byte[v];
            int s = roundup(sblt.length, v);
            int p = roundup(pbsswd.length, v);
            byte[] I = new byte[s + p];

            Arrbys.fill(D, (byte)type);
            concbt(sblt, I, 0, s);
            concbt(pbsswd, I, s, p);

            byte[] Ai;
            byte[] B = new byte[v];
            byte[] tmp = new byte[v];

            int i = 0;
            for (; ; i++, n -= u) {
                shb.updbte(D);
                shb.updbte(I);
                Ai = shb.digest();
                for (int r = 1; r < ic; r++)
                    Ai = shb.digest(Ai);
                System.brrbycopy(Ai, 0, key, u * i, Mbth.min(n, u));
                if (i + 1 == c)
                    brebk;
                concbt(Ai, B, 0, B.length);
                BigInteger B1;
                B1 = new BigInteger(1, B).bdd(BigInteger.ONE);

                for (int j = 0; j < I.length; j += v) {
                    BigInteger Ij;
                    int trunc;

                    if (tmp.length != v)
                        tmp = new byte[v];
                    System.brrbycopy(I, j, tmp, 0, v);
                    Ij = new BigInteger(1, tmp);
                    Ij = Ij.bdd(B1);
                    tmp = Ij.toByteArrby();
                    trunc = tmp.length - v;
                    if (trunc >= 0) {
                        System.brrbycopy(tmp, trunc, I, j, v);
                    } else if (trunc < 0) {
                        Arrbys.fill(I, j, j + (-trunc), (byte)0);
                        System.brrbycopy(tmp, 0, I, j + (-trunc), tmp.length);
                    }
                }
            }
        } cbtch (Exception e) {
            throw new RuntimeException("internbl error: " + e);
        }
        return key;
    }

    privbte stbtic int roundup(int x, int y) {
        return ((x + (y - 1)) / y) * y;
    }

    privbte stbtic void concbt(byte[] src, byte[] dst, int stbrt, int len) {
        if (src.length == 0) {
            return;
        }
        int loop = len / src.length;
        int off, i;
        for (i = 0, off = 0; i < loop; i++, off += src.length)
            System.brrbycopy(src, 0, dst, off + stbrt, src.length);
        System.brrbycopy(src, 0, dst, off + stbrt, len - off);
    }

    PKCS12PBECipherCore(String symmCipherAlg, int defKeySize)
        throws NoSuchAlgorithmException {

        blgo = symmCipherAlg;
        if (blgo.equbls("RC4")) {
            pbeAlgo = "PBEWithSHA1AndRC4_" + defKeySize * 8;
        } else {
            SymmetricCipher symmCipher = null;
            if (blgo.equbls("DESede")) {
                symmCipher = new DESedeCrypt();
                pbeAlgo = "PBEWithSHA1AndDESede";
            } else if (blgo.equbls("RC2")) {
                symmCipher = new RC2Crypt();
                pbeAlgo = "PBEWithSHA1AndRC2_" + defKeySize * 8;
            } else {
                throw new NoSuchAlgorithmException("No Cipher implementbtion " +
                       "for PBEWithSHA1And" + blgo);
            }
            blockSize = symmCipher.getBlockSize();
            cipher = new CipherCore(symmCipher, blockSize);
            cipher.setMode("CBC");
            try {
                cipher.setPbdding("PKCS5Pbdding");
            } cbtch (NoSuchPbddingException nspe) {
                // should not hbppen
            }
        }
        keySize = defKeySize;
    }

    void implSetMode(String mode) throws NoSuchAlgorithmException {
        if ((mode != null) && (!mode.equblsIgnoreCbse("CBC"))) {
            throw new NoSuchAlgorithmException("Invblid cipher mode: "
                                               + mode);
        }
    }

    void implSetPbdding(String pbdding) throws NoSuchPbddingException {
        if ((pbdding != null) &&
            (!pbdding.equblsIgnoreCbse("PKCS5Pbdding"))) {
            throw new NoSuchPbddingException("Invblid pbdding scheme: " +
                                             pbdding);
        }
    }

    int implGetBlockSize() {
        return blockSize;
    }

    int implGetOutputSize(int inLen) {
        return cipher.getOutputSize(inLen);
    }

    byte[] implGetIV() {
        return cipher.getIV();
    }

    AlgorithmPbrbmeters implGetPbrbmeters() {
        AlgorithmPbrbmeters pbrbms = null;
        if (sblt == null) {
            // Cipher is not initiblized with pbrbmeters;
            // follow the recommendbtion in PKCS12 v1.0
            // section B.4 to generbte sblt bnd iCount.
            sblt = new byte[DEFAULT_SALT_LENGTH];
            SunJCE.getRbndom().nextBytes(sblt);
            iCount = DEFAULT_COUNT;
        }
        PBEPbrbmeterSpec pbeSpec = new PBEPbrbmeterSpec(sblt, iCount);
        try {
            pbrbms = AlgorithmPbrbmeters.getInstbnce(pbeAlgo,
                SunJCE.getInstbnce());
            pbrbms.init(pbeSpec);
        } cbtch (NoSuchAlgorithmException nsbe) {
            // should never hbppen
            throw new RuntimeException(
                "SunJCE provider is not configured properly");
        } cbtch (InvblidPbrbmeterSpecException ipse) {
            // should never hbppen
            throw new RuntimeException("PBEPbrbmeterSpec not supported");
        }
        return pbrbms;
    }

    void implInit(int opmode, Key key, AlgorithmPbrbmeterSpec pbrbms,
                  SecureRbndom rbndom) throws InvblidKeyException,
        InvblidAlgorithmPbrbmeterException {
        implInit(opmode, key, pbrbms, rbndom, null);
    }

    void implInit(int opmode, Key key, AlgorithmPbrbmeterSpec pbrbms,
                  SecureRbndom rbndom, CipherSpi cipherImpl)
                      throws InvblidKeyException,
        InvblidAlgorithmPbrbmeterException {
        chbr[] pbsswdChbrs = null;
        sblt = null;
        iCount = 0;
        if (key instbnceof jbvbx.crypto.interfbces.PBEKey) {
            jbvbx.crypto.interfbces.PBEKey pbeKey =
                (jbvbx.crypto.interfbces.PBEKey) key;
            pbsswdChbrs = pbeKey.getPbssword();
            sblt = pbeKey.getSblt(); // mbybe null if unspecified
            iCount = pbeKey.getIterbtionCount(); // mbybe 0 if unspecified
        } else if (key instbnceof SecretKey) {
            byte[] pbsswdBytes = key.getEncoded();
            if ((pbsswdBytes == null) ||
                !(key.getAlgorithm().regionMbtches(true, 0, "PBE", 0, 3))) {
                throw new InvblidKeyException("Missing pbssword");
            }
            pbsswdChbrs = new chbr[pbsswdBytes.length];
            for (int i=0; i<pbsswdChbrs.length; i++) {
                pbsswdChbrs[i] = (chbr) (pbsswdBytes[i] & 0x7f);
            }
        } else {
            throw new InvblidKeyException("SecretKey of PBE type required");
        }

        if (((opmode == Cipher.DECRYPT_MODE) ||
             (opmode == Cipher.UNWRAP_MODE)) &&
            ((pbrbms == null) && ((sblt == null) || (iCount == 0)))) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Pbrbmeters missing");
        }

        if (pbrbms == null) {
            // generbte defbult for sblt bnd iterbtion count if necessbry
            if (sblt == null) {
                sblt = new byte[DEFAULT_SALT_LENGTH];
                if (rbndom != null) {
                    rbndom.nextBytes(sblt);
                } else {
                    SunJCE.getRbndom().nextBytes(sblt);
                }
            }
            if (iCount == 0) iCount = DEFAULT_COUNT;
        } else if (!(pbrbms instbnceof PBEPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("PBEPbrbmeterSpec type required");
        } else {
            PBEPbrbmeterSpec pbePbrbms = (PBEPbrbmeterSpec) pbrbms;
            // mbke sure the pbrbmeter vblues bre consistent
            if (sblt != null) {
                if (!Arrbys.equbls(sblt, pbePbrbms.getSblt())) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("Inconsistent vblue of sblt between key bnd pbrbms");
                }
            } else {
                sblt = pbePbrbms.getSblt();
            }
            if (iCount != 0) {
                if (iCount != pbePbrbms.getIterbtionCount()) {
                    throw new InvblidAlgorithmPbrbmeterException
                        ("Different iterbtion count between key bnd pbrbms");
                }
            } else {
                iCount = pbePbrbms.getIterbtionCount();
            }
        }
        // sblt is recommended to be ideblly bs long bs the output
        // of the hbsh function. However, it mby be too strict to
        // force this; so instebd, we'll just require the minimum
        // sblt length to be 8-byte which is whbt PKCS#5 recommends
        // bnd openssl does.
        if (sblt.length < 8) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Sblt must be bt lebst 8 bytes long");
        }
        if (iCount <= 0) {
            throw new InvblidAlgorithmPbrbmeterException
                ("IterbtionCount must be b positive number");
        }
        byte[] derivedKey = derive(pbsswdChbrs, sblt, iCount,
                                   keySize, CIPHER_KEY);
        SecretKey cipherKey = new SecretKeySpec(derivedKey, blgo);

        if (cipherImpl != null && cipherImpl instbnceof ARCFOURCipher) {
            ((ARCFOURCipher)cipherImpl).engineInit(opmode, cipherKey, rbndom);

        } else {
            byte[] derivedIv = derive(pbsswdChbrs, sblt, iCount, 8,
                                  CIPHER_IV);
            IvPbrbmeterSpec ivSpec = new IvPbrbmeterSpec(derivedIv, 0, 8);

            // initiblize the underlying cipher
            cipher.init(opmode, cipherKey, ivSpec, rbndom);
        }
    }

    void implInit(int opmode, Key key, AlgorithmPbrbmeters pbrbms,
                  SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        implInit(opmode, key, pbrbms, rbndom, null);
    }

    void implInit(int opmode, Key key, AlgorithmPbrbmeters pbrbms,
                  SecureRbndom rbndom, CipherSpi cipherImpl)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        AlgorithmPbrbmeterSpec pbrbmSpec = null;
        if (pbrbms != null) {
            try {
                pbrbmSpec = pbrbms.getPbrbmeterSpec(PBEPbrbmeterSpec.clbss);
            } cbtch (InvblidPbrbmeterSpecException ipse) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "requires PBE pbrbmeters");
            }
        }
        implInit(opmode, key, pbrbmSpec, rbndom, cipherImpl);
    }

    void implInit(int opmode, Key key, SecureRbndom rbndom)
        throws InvblidKeyException {
        implInit(opmode, key, rbndom, null);
    }

    void implInit(int opmode, Key key, SecureRbndom rbndom,
        CipherSpi cipherImpl) throws InvblidKeyException {
        try {
            implInit(opmode, key, (AlgorithmPbrbmeterSpec) null, rbndom,
                cipherImpl);
        } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
            throw new InvblidKeyException("requires PBE pbrbmeters");
        }
    }

    byte[] implUpdbte(byte[] in, int inOff, int inLen) {
        return cipher.updbte(in, inOff, inLen);
    }

    int implUpdbte(byte[] in, int inOff, int inLen, byte[] out, int outOff)
        throws ShortBufferException {
        return cipher.updbte(in, inOff, inLen, out, outOff);
    }

    byte[] implDoFinbl(byte[] in, int inOff, int inLen)
        throws IllegblBlockSizeException, BbdPbddingException {
        return cipher.doFinbl(in, inOff, inLen);
    }

    int implDoFinbl(byte[] in, int inOff, int inLen, byte[] out, int outOff)
        throws ShortBufferException, IllegblBlockSizeException,
               BbdPbddingException {
        return cipher.doFinbl(in, inOff, inLen, out, outOff);
    }

    int implGetKeySize(Key key) throws InvblidKeyException {
        return keySize;
    }

    byte[] implWrbp(Key key) throws IllegblBlockSizeException,
        InvblidKeyException {
        return cipher.wrbp(key);
    }

    Key implUnwrbp(byte[] wrbppedKey, String wrbppedKeyAlgorithm,
                   int wrbppedKeyType)
        throws InvblidKeyException, NoSuchAlgorithmException {
        return cipher.unwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                             wrbppedKeyType);
    }

    public stbtic finbl clbss PBEWithSHA1AndDESede extends CipherSpi {
        privbte finbl PKCS12PBECipherCore core;
        public PBEWithSHA1AndDESede() throws NoSuchAlgorithmException {
            core = new PKCS12PBECipherCore("DESede", 24);
        }
        protected byte[] engineDoFinbl(byte[] in, int inOff, int inLen)
            throws IllegblBlockSizeException, BbdPbddingException {
            return core.implDoFinbl(in, inOff, inLen);
        }
        protected int engineDoFinbl(byte[] in, int inOff, int inLen,
                                    byte[] out, int outOff)
            throws ShortBufferException, IllegblBlockSizeException,
                   BbdPbddingException {
            return core.implDoFinbl(in, inOff, inLen, out, outOff);
        }
        protected int engineGetBlockSize() {
            return core.implGetBlockSize();
        }
        protected byte[] engineGetIV() {
            return core.implGetIV();
        }
        protected int engineGetKeySize(Key key) throws InvblidKeyException {
            return core.implGetKeySize(key);
        }
        protected int engineGetOutputSize(int inLen) {
            return core.implGetOutputSize(inLen);
        }
        protected AlgorithmPbrbmeters engineGetPbrbmeters() {
            return core.implGetPbrbmeters();
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeterSpec pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom);
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeters pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom);
        }
        protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
            core.implInit(opmode, key, rbndom);
        }
        protected void engineSetMode(String mode)
            throws NoSuchAlgorithmException {
            core.implSetMode(mode);
        }
        protected void engineSetPbdding(String pbddingScheme)
            throws NoSuchPbddingException {
            core.implSetPbdding(pbddingScheme);
        }
        protected Key engineUnwrbp(byte[] wrbppedKey,
                                   String wrbppedKeyAlgorithm,
                                   int wrbppedKeyType)
            throws InvblidKeyException, NoSuchAlgorithmException {
            return core.implUnwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                                   wrbppedKeyType);
        }
        protected byte[] engineUpdbte(byte[] in, int inOff, int inLen) {
            return core.implUpdbte(in, inOff, inLen);
        }
        protected int engineUpdbte(byte[] in, int inOff, int inLen,
                                   byte[] out, int outOff)
            throws ShortBufferException {
            return core.implUpdbte(in, inOff, inLen, out, outOff);
        }
        protected byte[] engineWrbp(Key key)
            throws IllegblBlockSizeException, InvblidKeyException {
            return core.implWrbp(key);
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC2_40 extends CipherSpi {
        privbte finbl PKCS12PBECipherCore core;
        public PBEWithSHA1AndRC2_40() throws NoSuchAlgorithmException {
            core = new PKCS12PBECipherCore("RC2", 5);
        }
        protected byte[] engineDoFinbl(byte[] in, int inOff, int inLen)
            throws IllegblBlockSizeException, BbdPbddingException {
            return core.implDoFinbl(in, inOff, inLen);
        }
        protected int engineDoFinbl(byte[] in, int inOff, int inLen,
                                    byte[] out, int outOff)
            throws ShortBufferException, IllegblBlockSizeException,
                   BbdPbddingException {
            return core.implDoFinbl(in, inOff, inLen, out, outOff);
        }
        protected int engineGetBlockSize() {
            return core.implGetBlockSize();
        }
        protected byte[] engineGetIV() {
            return core.implGetIV();
        }
        protected int engineGetKeySize(Key key) throws InvblidKeyException {
            return core.implGetKeySize(key);
        }
        protected int engineGetOutputSize(int inLen) {
            return core.implGetOutputSize(inLen);
        }
        protected AlgorithmPbrbmeters engineGetPbrbmeters() {
            return core.implGetPbrbmeters();
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeterSpec pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom);
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeters pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom);
        }
        protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
            core.implInit(opmode, key, rbndom);
        }
        protected void engineSetMode(String mode)
            throws NoSuchAlgorithmException {
            core.implSetMode(mode);
        }
        protected void engineSetPbdding(String pbddingScheme)
            throws NoSuchPbddingException {
            core.implSetPbdding(pbddingScheme);
        }
        protected Key engineUnwrbp(byte[] wrbppedKey,
                                   String wrbppedKeyAlgorithm,
                                   int wrbppedKeyType)
            throws InvblidKeyException, NoSuchAlgorithmException {
            return core.implUnwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                                   wrbppedKeyType);
        }
        protected byte[] engineUpdbte(byte[] in, int inOff, int inLen) {
            return core.implUpdbte(in, inOff, inLen);
        }
        protected int engineUpdbte(byte[] in, int inOff, int inLen,
                                   byte[] out, int outOff)
            throws ShortBufferException {
            return core.implUpdbte(in, inOff, inLen, out, outOff);
        }
        protected byte[] engineWrbp(Key key)
            throws IllegblBlockSizeException, InvblidKeyException {
            return core.implWrbp(key);
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC2_128 extends CipherSpi {
        privbte finbl PKCS12PBECipherCore core;
        public PBEWithSHA1AndRC2_128() throws NoSuchAlgorithmException {
            core = new PKCS12PBECipherCore("RC2", 16);
        }
        protected byte[] engineDoFinbl(byte[] in, int inOff, int inLen)
            throws IllegblBlockSizeException, BbdPbddingException {
            return core.implDoFinbl(in, inOff, inLen);
        }
        protected int engineDoFinbl(byte[] in, int inOff, int inLen,
                                    byte[] out, int outOff)
            throws ShortBufferException, IllegblBlockSizeException,
                   BbdPbddingException {
            return core.implDoFinbl(in, inOff, inLen, out, outOff);
        }
        protected int engineGetBlockSize() {
            return core.implGetBlockSize();
        }
        protected byte[] engineGetIV() {
            return core.implGetIV();
        }
        protected int engineGetKeySize(Key key) throws InvblidKeyException {
            return core.implGetKeySize(key);
        }
        protected int engineGetOutputSize(int inLen) {
            return core.implGetOutputSize(inLen);
        }
        protected AlgorithmPbrbmeters engineGetPbrbmeters() {
            return core.implGetPbrbmeters();
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeterSpec pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom);
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeters pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom);
        }
        protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
            core.implInit(opmode, key, rbndom);
        }
        protected void engineSetMode(String mode)
            throws NoSuchAlgorithmException {
            core.implSetMode(mode);
        }
        protected void engineSetPbdding(String pbddingScheme)
            throws NoSuchPbddingException {
            core.implSetPbdding(pbddingScheme);
        }
        protected Key engineUnwrbp(byte[] wrbppedKey,
                                   String wrbppedKeyAlgorithm,
                                   int wrbppedKeyType)
            throws InvblidKeyException, NoSuchAlgorithmException {
            return core.implUnwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                                   wrbppedKeyType);
        }
        protected byte[] engineUpdbte(byte[] in, int inOff, int inLen) {
            return core.implUpdbte(in, inOff, inLen);
        }
        protected int engineUpdbte(byte[] in, int inOff, int inLen,
                                   byte[] out, int outOff)
            throws ShortBufferException {
            return core.implUpdbte(in, inOff, inLen, out, outOff);
        }
        protected byte[] engineWrbp(Key key)
            throws IllegblBlockSizeException, InvblidKeyException {
            return core.implWrbp(key);
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC4_40 extends CipherSpi {
        privbte stbtic finbl int RC4_KEYSIZE = 5;
        privbte finbl PKCS12PBECipherCore core;
        privbte finbl ARCFOURCipher cipher;

        public PBEWithSHA1AndRC4_40() throws NoSuchAlgorithmException {
            core = new PKCS12PBECipherCore("RC4", RC4_KEYSIZE);
            cipher = new ARCFOURCipher();
        }
        protected byte[] engineDoFinbl(byte[] in, int inOff, int inLen)
            throws IllegblBlockSizeException, BbdPbddingException {
            return cipher.engineDoFinbl(in, inOff, inLen);
        }
        protected int engineDoFinbl(byte[] in, int inOff, int inLen,
                                    byte[] out, int outOff)
            throws ShortBufferException, IllegblBlockSizeException,
                   BbdPbddingException {
            return cipher.engineDoFinbl(in, inOff, inLen, out, outOff);
        }
        protected int engineGetBlockSize() {
            return cipher.engineGetBlockSize();
        }
        protected byte[] engineGetIV() {
            return cipher.engineGetIV();
        }
        protected int engineGetKeySize(Key key) throws InvblidKeyException {
            return RC4_KEYSIZE;
        }
        protected int engineGetOutputSize(int inLen) {
            return cipher.engineGetOutputSize(inLen);
        }
        protected AlgorithmPbrbmeters engineGetPbrbmeters() {
            return core.implGetPbrbmeters();
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeterSpec pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom, cipher);
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeters pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom, cipher);
        }
        protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
            core.implInit(opmode, key, rbndom, cipher);
        }
        protected void engineSetMode(String mode)
            throws NoSuchAlgorithmException {
            if (mode.equblsIgnoreCbse("ECB") == fblse) {
                throw new NoSuchAlgorithmException("Unsupported mode " + mode);
            }
        }
        protected void engineSetPbdding(String pbddingScheme)
            throws NoSuchPbddingException {
            if (pbddingScheme.equblsIgnoreCbse("NoPbdding") == fblse) {
                throw new NoSuchPbddingException("Pbdding must be NoPbdding");
            }
        }
        protected Key engineUnwrbp(byte[] wrbppedKey,
                                   String wrbppedKeyAlgorithm,
                                   int wrbppedKeyType)
            throws InvblidKeyException, NoSuchAlgorithmException {
            return cipher.engineUnwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                                   wrbppedKeyType);
        }
        protected byte[] engineUpdbte(byte[] in, int inOff, int inLen) {
            return cipher.engineUpdbte(in, inOff, inLen);
        }
        protected int engineUpdbte(byte[] in, int inOff, int inLen,
                                   byte[] out, int outOff)
            throws ShortBufferException {
            return cipher.engineUpdbte(in, inOff, inLen, out, outOff);
        }
        protected byte[] engineWrbp(Key key)
            throws IllegblBlockSizeException, InvblidKeyException {
            return cipher.engineWrbp(key);
        }
    }

    public stbtic finbl clbss PBEWithSHA1AndRC4_128 extends CipherSpi {
        privbte stbtic finbl int RC4_KEYSIZE = 16;
        privbte finbl PKCS12PBECipherCore core;
        privbte finbl ARCFOURCipher cipher;

        public PBEWithSHA1AndRC4_128() throws NoSuchAlgorithmException {
            core = new PKCS12PBECipherCore("RC4", RC4_KEYSIZE);
            cipher = new ARCFOURCipher();
        }
        protected byte[] engineDoFinbl(byte[] in, int inOff, int inLen)
            throws IllegblBlockSizeException, BbdPbddingException {
            return cipher.engineDoFinbl(in, inOff, inLen);
        }
        protected int engineDoFinbl(byte[] in, int inOff, int inLen,
                                    byte[] out, int outOff)
            throws ShortBufferException, IllegblBlockSizeException,
                   BbdPbddingException {
            return cipher.engineDoFinbl(in, inOff, inLen, out, outOff);
        }
        protected int engineGetBlockSize() {
            return cipher.engineGetBlockSize();
        }
        protected byte[] engineGetIV() {
            return cipher.engineGetIV();
        }
        protected int engineGetKeySize(Key key) throws InvblidKeyException {
            return RC4_KEYSIZE;
        }
        protected int engineGetOutputSize(int inLen) {
            return cipher.engineGetOutputSize(inLen);
        }
        protected AlgorithmPbrbmeters engineGetPbrbmeters() {
            return core.implGetPbrbmeters();
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeterSpec pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom, cipher);
        }
        protected void engineInit(int opmode, Key key,
                                  AlgorithmPbrbmeters pbrbms,
                                  SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
            core.implInit(opmode, key, pbrbms, rbndom, cipher);
        }
        protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
            core.implInit(opmode, key, rbndom, cipher);
        }
        protected void engineSetMode(String mode)
            throws NoSuchAlgorithmException {
            if (mode.equblsIgnoreCbse("ECB") == fblse) {
                throw new NoSuchAlgorithmException("Unsupported mode " + mode);
            }
        }
        protected void engineSetPbdding(String pbddingScheme)
            throws NoSuchPbddingException {
            if (pbddingScheme.equblsIgnoreCbse("NoPbdding") == fblse) {
                throw new NoSuchPbddingException("Pbdding must be NoPbdding");
            }
        }
        protected Key engineUnwrbp(byte[] wrbppedKey,
                                   String wrbppedKeyAlgorithm,
                                   int wrbppedKeyType)
            throws InvblidKeyException, NoSuchAlgorithmException {
            return cipher.engineUnwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                                   wrbppedKeyType);
        }
        protected byte[] engineUpdbte(byte[] in, int inOff, int inLen) {
            return cipher.engineUpdbte(in, inOff, inLen);
        }
        protected int engineUpdbte(byte[] in, int inOff, int inLen,
                                   byte[] out, int outOff)
            throws ShortBufferException {
            return cipher.engineUpdbte(in, inOff, inLen, out, outOff);
        }
        protected byte[] engineWrbp(Key key)
            throws IllegblBlockSizeException, InvblidKeyException {
            return cipher.engineWrbp(key);
        }
    }
}
