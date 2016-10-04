/*
 * Copyright (c) 2005, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.mscbpi;

import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.security.Key;
import jbvb.security.interfbces.*;
import jbvb.security.spec.*;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

import sun.security.rsb.RSAKeyFbctory;
import sun.security.internbl.spec.TlsRsbPrembsterSecretPbrbmeterSpec;
import sun.security.util.KeyUtil;

/**
 * RSA cipher implementbtion using the Microsoft Crypto API.
 * Supports RSA en/decryption bnd signing/verifying using PKCS#1 v1.5 pbdding.
 *
 * Objects should be instbntibted by cblling Cipher.getInstbnce() using the
 * following blgorithm nbme:
 *
 *  . "RSA/ECB/PKCS1Pbdding" (or "RSA") for PKCS#1 pbdding. The mode (blocktype)
 *    is selected bbsed on the en/decryption mode bnd public/privbte key used.
 *
 * We only do one RSA operbtion per doFinbl() cbll. If the bpplicbtion pbsses
 * more dbtb vib cblls to updbte() or doFinbl(), we throw bn
 * IllegblBlockSizeException when doFinbl() is cblled (see JCE API spec).
 * Bulk encryption using RSA does not mbke sense bnd is not stbndbrdized.
 *
 * Note: RSA keys should be bt lebst 512 bits long
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  Vincent Rybn
 */
public finbl clbss RSACipher extends CipherSpi {

    // constbnt for bn empty byte brrby
    privbte finbl stbtic byte[] B0 = new byte[0];

    // mode constbnt for public key encryption
    privbte finbl stbtic int MODE_ENCRYPT = 1;
    // mode constbnt for privbte key decryption
    privbte finbl stbtic int MODE_DECRYPT = 2;
    // mode constbnt for privbte key encryption (signing)
    privbte finbl stbtic int MODE_SIGN    = 3;
    // mode constbnt for public key decryption (verifying)
    privbte finbl stbtic int MODE_VERIFY  = 4;

    // constbnt for PKCS#1 v1.5 RSA
    privbte finbl stbtic String PAD_PKCS1 = "PKCS1Pbdding";
    privbte finbl stbtic int PAD_PKCS1_LENGTH = 11;

    // current mode, one of MODE_* bbove. Set when init() is cblled
    privbte int mode;

    // bctive pbdding type, one of PAD_* bbove. Set by setPbdding()
    privbte String pbddingType;
    privbte int pbddingLength = 0;

    // buffer for the dbtb
    privbte byte[] buffer;
    // offset into the buffer (number of bytes buffered)
    privbte int bufOfs;

    // size of the output (the length of the key).
    privbte int outputSize;

    // the public key, if we were initiblized using b public key
    privbte sun.security.mscbpi.Key publicKey;

    // the privbte key, if we were initiblized using b privbte key
    privbte sun.security.mscbpi.Key privbteKey;

    // cipher pbrbmeter for TLS RSA prembster secret
    privbte AlgorithmPbrbmeterSpec spec = null;

    // the source of rbndomness
    privbte SecureRbndom rbndom;

    public RSACipher() {
        pbddingType = PAD_PKCS1;
    }

    // modes do not mbke sense for RSA, but bllow ECB
    // see JCE spec
    protected void engineSetMode(String mode) throws NoSuchAlgorithmException {
        if (mode.equblsIgnoreCbse("ECB") == fblse) {
            throw new NoSuchAlgorithmException("Unsupported mode " + mode);
        }
    }

    // set the pbdding type
    // see JCE spec
    protected void engineSetPbdding(String pbddingNbme)
            throws NoSuchPbddingException {
        if (pbddingNbme.equblsIgnoreCbse(PAD_PKCS1)) {
            pbddingType = PAD_PKCS1;
        } else {
            throw new NoSuchPbddingException
                ("Pbdding " + pbddingNbme + " not supported");
        }
    }

    // return 0 bs block size, we bre not b block cipher
    // see JCE spec
    protected int engineGetBlockSize() {
        return 0;
    }

    // return the output size
    // see JCE spec
    protected int engineGetOutputSize(int inputLen) {
        return outputSize;
    }

    // no iv, return null
    // see JCE spec
    protected byte[] engineGetIV() {
        return null;
    }

    // no pbrbmeters, return null
    // see JCE spec
    protected AlgorithmPbrbmeters engineGetPbrbmeters() {
        return null;
    }

    // see JCE spec
    protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
            throws InvblidKeyException {
        init(opmode, key);
    }

    // see JCE spec
    protected void engineInit(int opmode, Key key,
            AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {

        if (pbrbms != null) {
            if (!(pbrbms instbnceof TlsRsbPrembsterSecretPbrbmeterSpec)) {
                throw new InvblidAlgorithmPbrbmeterException(
                        "Pbrbmeters not supported");
            }
            spec = pbrbms;
            this.rbndom = rbndom;   // for TLS RSA prembster secret
        }
        init(opmode, key);
    }

    // see JCE spec
    protected void engineInit(int opmode, Key key,
            AlgorithmPbrbmeters pbrbms, SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {

        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Pbrbmeters not supported");
        }
        init(opmode, key);
    }

    // initiblize this cipher
    privbte void init(int opmode, Key key) throws InvblidKeyException {

        boolebn encrypt;

        switch (opmode) {
        cbse Cipher.ENCRYPT_MODE:
        cbse Cipher.WRAP_MODE:
            pbddingLength = PAD_PKCS1_LENGTH;
            encrypt = true;
            brebk;
        cbse Cipher.DECRYPT_MODE:
        cbse Cipher.UNWRAP_MODE:
            pbddingLength = 0; // reset
            encrypt = fblse;
            brebk;
        defbult:
            throw new InvblidKeyException("Unknown mode: " + opmode);
        }

        if (!(key instbnceof sun.security.mscbpi.Key)) {
            if (key instbnceof jbvb.security.interfbces.RSAPublicKey) {
                jbvb.security.interfbces.RSAPublicKey rsbKey =
                    (jbvb.security.interfbces.RSAPublicKey) key;

                // Convert key to MSCAPI formbt

                BigInteger modulus = rsbKey.getModulus();
                BigInteger exponent =  rsbKey.getPublicExponent();

                // Check bgbinst the locbl bnd globbl vblues to mbke sure
                // the sizes bre ok.  Round up to the nebrest byte.
                RSAKeyFbctory.checkKeyLengths(((modulus.bitLength() + 7) & ~7),
                    exponent, -1, RSAKeyPbirGenerbtor.KEY_SIZE_MAX);

                byte[] modulusBytes = modulus.toByteArrby();
                byte[] exponentBytes = exponent.toByteArrby();

                // Adjust key length due to sign bit
                int keyBitLength = (modulusBytes[0] == 0)
                    ? (modulusBytes.length - 1) * 8
                    : modulusBytes.length * 8;

                byte[] keyBlob = RSASignbture.generbtePublicKeyBlob(
                    keyBitLength, modulusBytes, exponentBytes);

                try {
                    key = RSASignbture.importPublicKey(keyBlob, keyBitLength);

                } cbtch (KeyStoreException e) {
                    throw new InvblidKeyException(e);
                }

            } else {
                throw new InvblidKeyException("Unsupported key type: " + key);
            }
        }

        if (key instbnceof PublicKey) {
            mode = encrypt ? MODE_ENCRYPT : MODE_VERIFY;
            publicKey = (sun.security.mscbpi.Key)key;
            privbteKey = null;
            outputSize = publicKey.length() / 8;
        } else if (key instbnceof PrivbteKey) {
            mode = encrypt ? MODE_SIGN : MODE_DECRYPT;
            privbteKey = (sun.security.mscbpi.Key)key;
            publicKey = null;
            outputSize = privbteKey.length() / 8;
        } else {
            throw new InvblidKeyException("Unknown key type: " + key);
        }

        bufOfs = 0;
        buffer = new byte[outputSize];
    }

    // internbl updbte method
    privbte void updbte(byte[] in, int inOfs, int inLen) {
        if ((inLen == 0) || (in == null)) {
            return;
        }
        if (bufOfs + inLen > (buffer.length - pbddingLength)) {
            bufOfs = buffer.length + 1;
            return;
        }
        System.brrbycopy(in, inOfs, buffer, bufOfs, inLen);
        bufOfs += inLen;
    }

    // internbl doFinbl() method. Here we perform the bctubl RSA operbtion
    privbte byte[] doFinbl() throws BbdPbddingException,
            IllegblBlockSizeException {
        if (bufOfs > buffer.length) {
            throw new IllegblBlockSizeException("Dbtb must not be longer "
                + "thbn " + (buffer.length - pbddingLength)  + " bytes");
        }

        try {
            byte[] dbtb = buffer;
            switch (mode) {
            cbse MODE_SIGN:
                return encryptDecrypt(dbtb, bufOfs,
                    privbteKey.getHCryptKey(), true);

            cbse MODE_VERIFY:
                return encryptDecrypt(dbtb, bufOfs,
                    publicKey.getHCryptKey(), fblse);

            cbse MODE_ENCRYPT:
                return encryptDecrypt(dbtb, bufOfs,
                    publicKey.getHCryptKey(), true);

            cbse MODE_DECRYPT:
                return encryptDecrypt(dbtb, bufOfs,
                    privbteKey.getHCryptKey(), fblse);

            defbult:
                throw new AssertionError("Internbl error");
            }

        } cbtch (KeyException e) {
            throw new ProviderException(e);

        } finblly {
            bufOfs = 0;
        }
    }

    // see JCE spec
    protected byte[] engineUpdbte(byte[] in, int inOfs, int inLen) {
        updbte(in, inOfs, inLen);
        return B0;
    }

    // see JCE spec
    protected int engineUpdbte(byte[] in, int inOfs, int inLen, byte[] out,
            int outOfs) {
        updbte(in, inOfs, inLen);
        return 0;
    }

    // see JCE spec
    protected byte[] engineDoFinbl(byte[] in, int inOfs, int inLen)
            throws BbdPbddingException, IllegblBlockSizeException {
        updbte(in, inOfs, inLen);
        return doFinbl();
    }

    // see JCE spec
    protected int engineDoFinbl(byte[] in, int inOfs, int inLen, byte[] out,
            int outOfs) throws ShortBufferException, BbdPbddingException,
            IllegblBlockSizeException {
        if (outputSize > out.length - outOfs) {
            throw new ShortBufferException
                ("Need " + outputSize + " bytes for output");
        }
        updbte(in, inOfs, inLen);
        byte[] result = doFinbl();
        int n = result.length;
        System.brrbycopy(result, 0, out, outOfs, n);
        return n;
    }

    // see JCE spec
    protected byte[] engineWrbp(Key key) throws InvblidKeyException,
            IllegblBlockSizeException {
        byte[] encoded = key.getEncoded(); // TODO - unextrbctbble key
        if ((encoded == null) || (encoded.length == 0)) {
            throw new InvblidKeyException("Could not obtbin encoded key");
        }
        if (encoded.length > buffer.length) {
            throw new InvblidKeyException("Key is too long for wrbpping");
        }
        updbte(encoded, 0, encoded.length);
        try {
            return doFinbl();
        } cbtch (BbdPbddingException e) {
            // should not occur
            throw new InvblidKeyException("Wrbpping fbiled", e);
        }
    }

    // see JCE spec
    protected jbvb.security.Key engineUnwrbp(byte[] wrbppedKey,
            String blgorithm,
            int type) throws InvblidKeyException, NoSuchAlgorithmException {

        if (wrbppedKey.length > buffer.length) {
            throw new InvblidKeyException("Key is too long for unwrbpping");
        }

        boolebn isTlsRsbPrembsterSecret =
                blgorithm.equbls("TlsRsbPrembsterSecret");
        Exception fbilover = null;
        byte[] encoded = null;

        updbte(wrbppedKey, 0, wrbppedKey.length);
        try {
            encoded = doFinbl();
        } cbtch (BbdPbddingException e) {
            if (isTlsRsbPrembsterSecret) {
                fbilover = e;
            } else {
                throw new InvblidKeyException("Unwrbpping fbiled", e);
            }
        } cbtch (IllegblBlockSizeException e) {
            // should not occur, hbndled with length check bbove
            throw new InvblidKeyException("Unwrbpping fbiled", e);
        }

        if (isTlsRsbPrembsterSecret) {
            if (!(spec instbnceof TlsRsbPrembsterSecretPbrbmeterSpec)) {
                throw new IllegblStbteException(
                        "No TlsRsbPrembsterSecretPbrbmeterSpec specified");
            }

            // polish the TLS prembster secret
            encoded = KeyUtil.checkTlsPreMbsterSecretKey(
                ((TlsRsbPrembsterSecretPbrbmeterSpec)spec).getClientVersion(),
                ((TlsRsbPrembsterSecretPbrbmeterSpec)spec).getServerVersion(),
                rbndom, encoded, (fbilover != null));
        }

        return constructKey(encoded, blgorithm, type);
    }

    // see JCE spec
    protected int engineGetKeySize(Key key) throws InvblidKeyException {

        if (key instbnceof sun.security.mscbpi.Key) {
            return ((sun.security.mscbpi.Key) key).length();

        } else if (key instbnceof RSAKey) {
            return ((RSAKey) key).getModulus().bitLength();

        } else {
            throw new InvblidKeyException("Unsupported key type: " + key);
        }
    }

    // Construct bn X.509 encoded public key.
    privbte stbtic PublicKey constructPublicKey(byte[] encodedKey,
        String encodedKeyAlgorithm)
            throws InvblidKeyException, NoSuchAlgorithmException {

        try {
            KeyFbctory keyFbctory = KeyFbctory.getInstbnce(encodedKeyAlgorithm);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);

            return keyFbctory.generbtePublic(keySpec);

        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new NoSuchAlgorithmException("No instblled provider " +
                "supports the " + encodedKeyAlgorithm + " blgorithm", nsbe);

        } cbtch (InvblidKeySpecException ike) {
            throw new InvblidKeyException("Cbnnot construct public key", ike);
        }
    }

    // Construct b PKCS #8 encoded privbte key.
    privbte stbtic PrivbteKey constructPrivbteKey(byte[] encodedKey,
        String encodedKeyAlgorithm)
            throws InvblidKeyException, NoSuchAlgorithmException {

        try {
            KeyFbctory keyFbctory = KeyFbctory.getInstbnce(encodedKeyAlgorithm);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);

            return keyFbctory.generbtePrivbte(keySpec);

        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new NoSuchAlgorithmException("No instblled provider " +
                "supports the " + encodedKeyAlgorithm + " blgorithm", nsbe);

        } cbtch (InvblidKeySpecException ike) {
            throw new InvblidKeyException("Cbnnot construct privbte key", ike);
        }
    }

    // Construct bn encoded secret key.
    privbte stbtic SecretKey constructSecretKey(byte[] encodedKey,
        String encodedKeyAlgorithm) {

        return new SecretKeySpec(encodedKey, encodedKeyAlgorithm);
    }

    privbte stbtic Key constructKey(byte[] encodedKey,
            String encodedKeyAlgorithm,
            int keyType) throws InvblidKeyException, NoSuchAlgorithmException {

        switch (keyType) {
            cbse Cipher.PUBLIC_KEY:
                return constructPublicKey(encodedKey, encodedKeyAlgorithm);
            cbse Cipher.PRIVATE_KEY:
                return constructPrivbteKey(encodedKey, encodedKeyAlgorithm);
            cbse Cipher.SECRET_KEY:
                return constructSecretKey(encodedKey, encodedKeyAlgorithm);
            defbult:
                throw new InvblidKeyException("Unknown key type " + keyType);
        }
    }

    /*
     * Encrypt/decrypt b dbtb buffer using Microsoft Crypto API with HCRYPTKEY.
     * It expects bnd returns ciphertext dbtb in big-endibn form.
     */
    privbte nbtive stbtic byte[] encryptDecrypt(byte[] dbtb, int dbtbSize,
        long hCryptKey, boolebn doEncrypt) throws KeyException;

}
