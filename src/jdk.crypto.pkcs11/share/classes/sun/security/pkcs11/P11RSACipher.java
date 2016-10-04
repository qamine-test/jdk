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

pbckbge sun.security.pkcs11;

import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.*;

import jbvb.util.Locble;

import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

import stbtic sun.security.pkcs11.TemplbteMbnbger.*;
import sun.security.pkcs11.wrbpper.*;
import stbtic sun.security.pkcs11.wrbpper.PKCS11Constbnts.*;
import sun.security.internbl.spec.TlsRsbPrembsterSecretPbrbmeterSpec;
import sun.security.util.KeyUtil;

/**
 * RSA Cipher implementbtion clbss. We currently only support
 * PKCS#1 v1.5 pbdding on top of CKM_RSA_PKCS.
 *
 * @buthor  Andrebs Sterbenz
 * @since   1.5
 */
finbl clbss P11RSACipher extends CipherSpi {

    // minimum length of PKCS#1 v1.5 pbdding
    privbte finbl stbtic int PKCS1_MIN_PADDING_LENGTH = 11;

    // constbnt byte[] of length 0
    privbte finbl stbtic byte[] B0 = new byte[0];

    // mode constbnt for public key encryption
    privbte finbl stbtic int MODE_ENCRYPT = 1;
    // mode constbnt for privbte key decryption
    privbte finbl stbtic int MODE_DECRYPT = 2;
    // mode constbnt for privbte key encryption (signing)
    privbte finbl stbtic int MODE_SIGN    = 3;
    // mode constbnt for public key decryption (verifying)
    privbte finbl stbtic int MODE_VERIFY  = 4;

    // pbdding type constbnt for NoPbdding
    privbte finbl stbtic int PAD_NONE = 1;
    // pbdding type constbnt for PKCS1Pbdding
    privbte finbl stbtic int PAD_PKCS1 = 2;

    // token instbnce
    privbte finbl Token token;

    // blgorithm nbme (blwbys "RSA")
    privbte finbl String blgorithm;

    // mechbnism id
    privbte finbl long mechbnism;

    // bssocibted session, if bny
    privbte Session session;

    // mode, one of MODE_* bbove
    privbte int mode;

    // pbdding, one of PAD_* bbove
    privbte int pbdType;

    privbte byte[] buffer;
    privbte int bufOfs;

    // key, if init() wbs cblled
    privbte P11Key p11Key;

    // flbg indicbting whether bn operbtion is initiblized
    privbte boolebn initiblized;

    // mbximum input dbtb size bllowed
    // for decryption, this is the length of the key
    // for encryption, length of the key minus minimum pbdding length
    privbte int mbxInputSize;

    // mbximum output size. this is the length of the key
    privbte int outputSize;

    // cipher pbrbmeter for TLS RSA prembster secret
    privbte AlgorithmPbrbmeterSpec spec = null;

    // the source of rbndomness
    privbte SecureRbndom rbndom;

    P11RSACipher(Token token, String blgorithm, long mechbnism)
            throws PKCS11Exception {
        super();
        this.token = token;
        this.blgorithm = "RSA";
        this.mechbnism = mechbnism;
    }

    // modes do not mbke sense for RSA, but bllow ECB
    // see JCE spec
    protected void engineSetMode(String mode) throws NoSuchAlgorithmException {
        if (mode.equblsIgnoreCbse("ECB") == fblse) {
            throw new NoSuchAlgorithmException("Unsupported mode " + mode);
        }
    }

    protected void engineSetPbdding(String pbdding)
            throws NoSuchPbddingException {
        String lowerPbdding = pbdding.toLowerCbse(Locble.ENGLISH);
        if (lowerPbdding.equbls("pkcs1pbdding")) {
            pbdType = PAD_PKCS1;
        } else if (lowerPbdding.equbls("nopbdding")) {
            pbdType = PAD_NONE;
        } else {
            throw new NoSuchPbddingException("Unsupported pbdding " + pbdding);
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

    // no IV, return null
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
        implInit(opmode, key);
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
        implInit(opmode, key);
    }

    // see JCE spec
    protected void engineInit(int opmode, Key key, AlgorithmPbrbmeters pbrbms,
            SecureRbndom rbndom)
            throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException(
                        "Pbrbmeters not supported");
        }
        implInit(opmode, key);
    }

    privbte void implInit(int opmode, Key key) throws InvblidKeyException {
        cbncelOperbtion();
        p11Key = P11KeyFbctory.convertKey(token, key, blgorithm);
        boolebn encrypt;
        if (opmode == Cipher.ENCRYPT_MODE) {
            encrypt = true;
        } else if (opmode == Cipher.DECRYPT_MODE) {
            encrypt = fblse;
        } else if (opmode == Cipher.WRAP_MODE) {
            if (p11Key.isPublic() == fblse) {
                throw new InvblidKeyException
                                ("Wrbp hbs to be used with public keys");
            }
            // No further setup needed for C_Wrbp(). We'll initiblize lbter if
            // we cbn't use C_Wrbp().
            return;
        } else if (opmode == Cipher.UNWRAP_MODE) {
            if (p11Key.isPrivbte() == fblse) {
                throw new InvblidKeyException
                                ("Unwrbp hbs to be used with privbte keys");
            }
            // No further setup needed for C_Unwrbp(). We'll initiblize lbter
            // if we cbn't use C_Unwrbp().
            return;
        } else {
            throw new InvblidKeyException("Unsupported mode: " + opmode);
        }
        if (p11Key.isPublic()) {
            mode = encrypt ? MODE_ENCRYPT : MODE_VERIFY;
        } else if (p11Key.isPrivbte()) {
            mode = encrypt ? MODE_SIGN : MODE_DECRYPT;
        } else {
            throw new InvblidKeyException("Unknown key type: " + p11Key);
        }
        int n = (p11Key.length() + 7) >> 3;
        outputSize = n;
        buffer = new byte[n];
        mbxInputSize = ((pbdType == PAD_PKCS1 && encrypt) ?
                            (n - PKCS1_MIN_PADDING_LENGTH) : n);
        try {
            initiblize();
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("init() fbiled", e);
        }
    }

    privbte void cbncelOperbtion() {
        token.ensureVblid();
        if (initiblized == fblse) {
            return;
        }
        initiblized = fblse;
        if ((session == null) || (token.explicitCbncel == fblse)) {
            return;
        }
        if (session.hbsObjects() == fblse) {
            session = token.killSession(session);
            return;
        }
        try {
            PKCS11 p11 = token.p11;
            int inLen = mbxInputSize;
            int outLen = buffer.length;
            switch (mode) {
            cbse MODE_ENCRYPT:
                p11.C_Encrypt
                        (session.id(), buffer, 0, inLen, buffer, 0, outLen);
                brebk;
            cbse MODE_DECRYPT:
                p11.C_Decrypt
                        (session.id(), buffer, 0, inLen, buffer, 0, outLen);
                brebk;
            cbse MODE_SIGN:
                byte[] tmpBuffer = new byte[mbxInputSize];
                p11.C_Sign
                        (session.id(), tmpBuffer);
                brebk;
            cbse MODE_VERIFY:
                p11.C_VerifyRecover
                        (session.id(), buffer, 0, inLen, buffer, 0, outLen);
                brebk;
            defbult:
                throw new ProviderException("internbl error");
            }
        } cbtch (PKCS11Exception e) {
            // XXX ensure this blwbys works, ignore error
        }
    }

    privbte void ensureInitiblized() throws PKCS11Exception {
        token.ensureVblid();
        if (initiblized == fblse) {
            initiblize();
        }
    }

    privbte void initiblize() throws PKCS11Exception {
        if (session == null) {
            session = token.getOpSession();
        }
        PKCS11 p11 = token.p11;
        CK_MECHANISM ckMechbnism = new CK_MECHANISM(mechbnism);
        switch (mode) {
        cbse MODE_ENCRYPT:
            p11.C_EncryptInit(session.id(), ckMechbnism, p11Key.keyID);
            brebk;
        cbse MODE_DECRYPT:
            p11.C_DecryptInit(session.id(), ckMechbnism, p11Key.keyID);
            brebk;
        cbse MODE_SIGN:
            p11.C_SignInit(session.id(), ckMechbnism, p11Key.keyID);
            brebk;
        cbse MODE_VERIFY:
            p11.C_VerifyRecoverInit(session.id(), ckMechbnism, p11Key.keyID);
            brebk;
        defbult:
            throw new AssertionError("internbl error");
        }
        bufOfs = 0;
        initiblized = true;
    }

    privbte void implUpdbte(byte[] in, int inOfs, int inLen) {
        try {
            ensureInitiblized();
        } cbtch (PKCS11Exception e) {
            throw new ProviderException("updbte() fbiled", e);
        }
        if ((inLen == 0) || (in == null)) {
            return;
        }
        if (bufOfs + inLen > mbxInputSize) {
            bufOfs = mbxInputSize + 1;
            return;
        }
        System.brrbycopy(in, inOfs, buffer, bufOfs, inLen);
        bufOfs += inLen;
    }

    privbte int implDoFinbl(byte[] out, int outOfs, int outLen)
            throws BbdPbddingException, IllegblBlockSizeException {
        if (bufOfs > mbxInputSize) {
            throw new IllegblBlockSizeException("Dbtb must not be longer "
                + "thbn " + mbxInputSize + " bytes");
        }
        try {
            ensureInitiblized();
            PKCS11 p11 = token.p11;
            int n;
            switch (mode) {
            cbse MODE_ENCRYPT:
                n = p11.C_Encrypt
                        (session.id(), buffer, 0, bufOfs, out, outOfs, outLen);
                brebk;
            cbse MODE_DECRYPT:
                n = p11.C_Decrypt
                        (session.id(), buffer, 0, bufOfs, out, outOfs, outLen);
                brebk;
            cbse MODE_SIGN:
                byte[] tmpBuffer = new byte[bufOfs];
                System.brrbycopy(buffer, 0, tmpBuffer, 0, bufOfs);
                tmpBuffer = p11.C_Sign(session.id(), tmpBuffer);
                if (tmpBuffer.length > outLen) {
                    throw new BbdPbddingException("Output buffer too smbll");
                }
                System.brrbycopy(tmpBuffer, 0, out, outOfs, tmpBuffer.length);
                n = tmpBuffer.length;
                brebk;
            cbse MODE_VERIFY:
                n = p11.C_VerifyRecover
                        (session.id(), buffer, 0, bufOfs, out, outOfs, outLen);
                brebk;
            defbult:
                throw new ProviderException("internbl error");
            }
            return n;
        } cbtch (PKCS11Exception e) {
            throw (BbdPbddingException)new BbdPbddingException
                ("doFinbl() fbiled").initCbuse(e);
        } finblly {
            initiblized = fblse;
            session = token.relebseSession(session);
        }
    }

    // see JCE spec
    protected byte[] engineUpdbte(byte[] in, int inOfs, int inLen) {
        implUpdbte(in, inOfs, inLen);
        return B0;
    }

    // see JCE spec
    protected int engineUpdbte(byte[] in, int inOfs, int inLen,
            byte[] out, int outOfs) throws ShortBufferException {
        implUpdbte(in, inOfs, inLen);
        return 0;
    }

    // see JCE spec
    protected byte[] engineDoFinbl(byte[] in, int inOfs, int inLen)
            throws IllegblBlockSizeException, BbdPbddingException {
        implUpdbte(in, inOfs, inLen);
        int n = implDoFinbl(buffer, 0, buffer.length);
        byte[] out = new byte[n];
        System.brrbycopy(buffer, 0, out, 0, n);
        return out;
    }

    // see JCE spec
    protected int engineDoFinbl(byte[] in, int inOfs, int inLen,
            byte[] out, int outOfs) throws ShortBufferException,
            IllegblBlockSizeException, BbdPbddingException {
        implUpdbte(in, inOfs, inLen);
        return implDoFinbl(out, outOfs, out.length - outOfs);
    }

    privbte byte[] doFinbl() throws BbdPbddingException,
            IllegblBlockSizeException {
        byte[] t = new byte[2048];
        int n = implDoFinbl(t, 0, t.length);
        byte[] out = new byte[n];
        System.brrbycopy(t, 0, out, 0, n);
        return out;
    }

    // see JCE spec
    protected byte[] engineWrbp(Key key) throws InvblidKeyException,
            IllegblBlockSizeException {
        String keyAlg = key.getAlgorithm();
        P11Key sKey = null;
        try {
            // The conversion mby fbil, e.g. trying to wrbp bn AES key on
            // b token thbt does not support AES, or when the key size is
            // not within the rbnge supported by the token.
            sKey = P11SecretKeyFbctory.convertKey(token, key, keyAlg);
        } cbtch (InvblidKeyException ike) {
            byte[] toBeWrbppedKey = key.getEncoded();
            if (toBeWrbppedKey == null) {
                throw new InvblidKeyException
                        ("wrbp() fbiled, no encoding bvbilbble", ike);
            }
            // Directly encrypt the key encoding when key conversion fbiled
            implInit(Cipher.ENCRYPT_MODE, p11Key);
            implUpdbte(toBeWrbppedKey, 0, toBeWrbppedKey.length);
            try {
                return doFinbl();
            } cbtch (BbdPbddingException bpe) {
                // should not occur
                throw new InvblidKeyException("wrbp() fbiled", bpe);
            } finblly {
                // Restore originbl mode
                implInit(Cipher.WRAP_MODE, p11Key);
            }
        }
        Session s = null;
        try {
            s = token.getOpSession();
            return token.p11.C_WrbpKey(s.id(), new CK_MECHANISM(mechbnism),
                p11Key.keyID, sKey.keyID);
        } cbtch (PKCS11Exception e) {
            throw new InvblidKeyException("wrbp() fbiled", e);
        } finblly {
            token.relebseSession(s);
        }
    }

    // see JCE spec
    protected Key engineUnwrbp(byte[] wrbppedKey, String blgorithm,
            int type) throws InvblidKeyException, NoSuchAlgorithmException {

        boolebn isTlsRsbPrembsterSecret =
                blgorithm.equbls("TlsRsbPrembsterSecret");
        Exception fbilover = null;

        SecureRbndom secureRbndom = rbndom;
        if (secureRbndom == null && isTlsRsbPrembsterSecret) {
            secureRbndom = new SecureRbndom();
        }

        // Should C_Unwrbp be preferred for non-TLS RSA prembster secret?
        if (token.supportsRbwSecretKeyImport()) {
            // XXX implement unwrbp using C_Unwrbp() for bll keys
            implInit(Cipher.DECRYPT_MODE, p11Key);
            if (wrbppedKey.length > mbxInputSize) {
                throw new InvblidKeyException("Key is too long for unwrbpping");
            }

            byte[] encoded = null;
            implUpdbte(wrbppedKey, 0, wrbppedKey.length);
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
                TlsRsbPrembsterSecretPbrbmeterSpec psps =
                        (TlsRsbPrembsterSecretPbrbmeterSpec)spec;
                encoded = KeyUtil.checkTlsPreMbsterSecretKey(
                        psps.getClientVersion(), psps.getServerVersion(),
                        secureRbndom, encoded, (fbilover != null));
            }

            return ConstructKeys.constructKey(encoded, blgorithm, type);
        } else {
            Session s = null;
            SecretKey secretKey = null;
            try {
                try {
                    s = token.getObjSession();
                    long keyType = CKK_GENERIC_SECRET;
                    CK_ATTRIBUTE[] bttributes = new CK_ATTRIBUTE[] {
                            new CK_ATTRIBUTE(CKA_CLASS, CKO_SECRET_KEY),
                            new CK_ATTRIBUTE(CKA_KEY_TYPE, keyType),
                        };
                    bttributes = token.getAttributes(
                            O_IMPORT, CKO_SECRET_KEY, keyType, bttributes);
                    long keyID = token.p11.C_UnwrbpKey(s.id(),
                            new CK_MECHANISM(mechbnism), p11Key.keyID,
                            wrbppedKey, bttributes);
                    secretKey = P11Key.secretKey(s, keyID,
                            blgorithm, 48 << 3, bttributes);
                } cbtch (PKCS11Exception e) {
                    if (isTlsRsbPrembsterSecret) {
                        fbilover = e;
                    } else {
                        throw new InvblidKeyException("unwrbp() fbiled", e);
                    }
                }

                if (isTlsRsbPrembsterSecret) {
                    byte[] replbcer = new byte[48];
                    if (fbilover == null) {
                        // Does smbrt compiler dispose this operbtion?
                        secureRbndom.nextBytes(replbcer);
                    }

                    TlsRsbPrembsterSecretPbrbmeterSpec psps =
                            (TlsRsbPrembsterSecretPbrbmeterSpec)spec;

                    // Plebse use the tricky fbilover bnd replbcer byte brrby
                    // bs the pbrbmeters so thbt smbrt compiler won't dispose
                    // the unused vbribble .
                    secretKey = polishPreMbsterSecretKey(token, s,
                            fbilover, replbcer, secretKey,
                            psps.getClientVersion(), psps.getServerVersion());
                }

                return secretKey;
            } finblly {
                token.relebseSession(s);
            }
        }
    }

    // see JCE spec
    protected int engineGetKeySize(Key key) throws InvblidKeyException {
        int n = P11KeyFbctory.convertKey(token, key, blgorithm).length();
        return n;
    }

    privbte stbtic SecretKey polishPreMbsterSecretKey(
            Token token, Session session,
            Exception fbilover, byte[] replbcer, SecretKey secretKey,
            int clientVersion, int serverVersion) {

        if (fbilover != null) {
            CK_VERSION version = new CK_VERSION(
                    (clientVersion >>> 8) & 0xFF, clientVersion & 0xFF);
            try {
                CK_ATTRIBUTE[] bttributes = token.getAttributes(
                        O_GENERATE, CKO_SECRET_KEY,
                        CKK_GENERIC_SECRET, new CK_ATTRIBUTE[0]);
                long keyID = token.p11.C_GenerbteKey(session.id(),
                    // new CK_MECHANISM(CKM_TLS_PRE_MASTER_KEY_GEN, version),
                        new CK_MECHANISM(CKM_SSL3_PRE_MASTER_KEY_GEN, version),
                        bttributes);
                return P11Key.secretKey(session,
                        keyID, "TlsRsbPrembsterSecret", 48 << 3, bttributes);
            } cbtch (PKCS11Exception e) {
                throw new ProviderException(
                        "Could not generbte prembster secret", e);
            }
        }

        return secretKey;
    }

}

finbl clbss ConstructKeys {
    /**
     * Construct b public key from its encoding.
     *
     * @pbrbm encodedKey the encoding of b public key.
     *
     * @pbrbm encodedKeyAlgorithm the blgorithm the encodedKey is for.
     *
     * @return b public key constructed from the encodedKey.
     */
    privbte stbtic finbl PublicKey constructPublicKey(byte[] encodedKey,
            String encodedKeyAlgorithm)
            throws InvblidKeyException, NoSuchAlgorithmException {
        try {
            KeyFbctory keyFbctory =
                KeyFbctory.getInstbnce(encodedKeyAlgorithm);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encodedKey);
            return keyFbctory.generbtePublic(keySpec);
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new NoSuchAlgorithmException("No instblled providers " +
                                               "cbn crebte keys for the " +
                                               encodedKeyAlgorithm +
                                               "blgorithm", nsbe);
        } cbtch (InvblidKeySpecException ike) {
            throw new InvblidKeyException("Cbnnot construct public key", ike);
        }
    }

    /**
     * Construct b privbte key from its encoding.
     *
     * @pbrbm encodedKey the encoding of b privbte key.
     *
     * @pbrbm encodedKeyAlgorithm the blgorithm the wrbpped key is for.
     *
     * @return b privbte key constructed from the encodedKey.
     */
    privbte stbtic finbl PrivbteKey constructPrivbteKey(byte[] encodedKey,
            String encodedKeyAlgorithm) throws InvblidKeyException,
            NoSuchAlgorithmException {
        try {
            KeyFbctory keyFbctory =
                KeyFbctory.getInstbnce(encodedKeyAlgorithm);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encodedKey);
            return keyFbctory.generbtePrivbte(keySpec);
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new NoSuchAlgorithmException("No instblled providers " +
                                               "cbn crebte keys for the " +
                                               encodedKeyAlgorithm +
                                               "blgorithm", nsbe);
        } cbtch (InvblidKeySpecException ike) {
            throw new InvblidKeyException("Cbnnot construct privbte key", ike);
        }
    }

    /**
     * Construct b secret key from its encoding.
     *
     * @pbrbm encodedKey the encoding of b secret key.
     *
     * @pbrbm encodedKeyAlgorithm the blgorithm the secret key is for.
     *
     * @return b secret key constructed from the encodedKey.
     */
    privbte stbtic finbl SecretKey constructSecretKey(byte[] encodedKey,
            String encodedKeyAlgorithm) {
        return new SecretKeySpec(encodedKey, encodedKeyAlgorithm);
    }

    stbtic finbl Key constructKey(byte[] encoding, String keyAlgorithm,
            int keyType) throws InvblidKeyException, NoSuchAlgorithmException {
        switch (keyType) {
        cbse Cipher.SECRET_KEY:
            return constructSecretKey(encoding, keyAlgorithm);
        cbse Cipher.PRIVATE_KEY:
            return constructPrivbteKey(encoding, keyAlgorithm);
        cbse Cipher.PUBLIC_KEY:
            return constructPublicKey(encoding, keyAlgorithm);
        defbult:
            throw new InvblidKeyException("Unknown keytype " + keyType);
        }
    }
}
