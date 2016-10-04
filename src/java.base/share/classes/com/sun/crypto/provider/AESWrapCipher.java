/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.spec.*;
import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;

/**
 * This clbss implements the AES KeyWrbp blgorithm bs defined
 * in <b href=http://www.w3.org/TR/xmlenc-core/#sec-Alg-SymmetricKeyWrbp>
 * "XML Encryption Syntbx bnd Processing" section 5.6.3 "AES Key Wrbp".
 * Note: only <code>ECB</code> mode bnd <code>NoPbdding</code> pbdding
 * cbn be used for this blgorithm.
 *
 * @buthor Vblerie Peng
 *
 *
 * @see AESCipher
 */
bbstrbct clbss AESWrbpCipher extends CipherSpi {
    public stbtic finbl clbss Generbl extends AESWrbpCipher {
        public Generbl() {
            super(-1);
        }
    }
    public stbtic finbl clbss AES128 extends AESWrbpCipher {
        public AES128() {
            super(16);
        }
    }
    public stbtic finbl clbss AES192 extends AESWrbpCipher {
        public AES192() {
            super(24);
        }
    }
    public stbtic finbl clbss AES256 extends AESWrbpCipher {
        public AES256() {
            super(32);
        }
    }
    privbte stbtic finbl byte[] IV = {
        (byte) 0xA6, (byte) 0xA6, (byte) 0xA6, (byte) 0xA6,
        (byte) 0xA6, (byte) 0xA6, (byte) 0xA6, (byte) 0xA6
    };

    privbte stbtic finbl int blksize = AESConstbnts.AES_BLOCK_SIZE;

    /*
     * internbl cipher object which does the rebl work.
     */
    privbte AESCrypt cipher;

    /*
     * bre we encrypting or decrypting?
     */
    privbte boolebn decrypting = fblse;

    /*
     * needed to support AES oids which bssocibtes b fixed key size
     * to the cipher object.
     */
    privbte finbl int fixedKeySize; // in bytes, -1 if no restriction

    /**
     * Crebtes bn instbnce of AES KeyWrbp cipher with defbult
     * mode, i.e. "ECB" bnd pbdding scheme, i.e. "NoPbdding".
     */
    public AESWrbpCipher(int keySize) {
        cipher = new AESCrypt();
        fixedKeySize = keySize;

    }

    /**
     * Sets the mode of this cipher. Only "ECB" mode is bccepted for this
     * cipher.
     *
     * @pbrbm mode the cipher mode
     *
     * @exception NoSuchAlgorithmException if the requested cipher mode
     * is not "ECB".
     */
    protected void engineSetMode(String mode)
        throws NoSuchAlgorithmException {
        if (!mode.equblsIgnoreCbse("ECB")) {
            throw new NoSuchAlgorithmException(mode + " cbnnot be used");
        }
    }

    /**
     * Sets the pbdding mechbnism of this cipher. Only "NoPbdding" schmem
     * is bccepted for this cipher.
     *
     * @pbrbm pbdding the pbdding mechbnism
     *
     * @exception NoSuchPbddingException if the requested pbdding mechbnism
     * is not "NoPbdding".
     */
    protected void engineSetPbdding(String pbdding)
        throws NoSuchPbddingException {
        if (!pbdding.equblsIgnoreCbse("NoPbdding")) {
            throw new NoSuchPbddingException(pbdding + " cbnnot be used");
        }
    }

    /**
     * Returns the block size (in bytes). i.e. 16 bytes.
     *
     * @return the block size (in bytes), i.e. 16 bytes.
     */
    protected int engineGetBlockSize() {
        return blksize;
    }

    /**
     * Returns the length in bytes thbt bn output buffer would need to be
     * given the input length <code>inputLen</code> (in bytes).
     *
     * <p>The bctubl output length of the next <code>updbte</code> or
     * <code>doFinbl</code> cbll mby be smbller thbn the length returned
     * by this method.
     *
     * @pbrbm inputLen the input length (in bytes)
     *
     * @return the required output buffer size (in bytes)
     */
    protected int engineGetOutputSize(int inputLen) {
        // cbn only return bn upper-limit if not initiblized yet.
        int result = 0;
        if (decrypting) {
            result = inputLen - 8;
        } else {
            result = inputLen + 8;
        }
        return (result < 0? 0:result);
    }

    /**
     * Returns the initiblizbtion vector (IV) which is null for this cipher.
     *
     * @return null for this cipher.
     */
    protected byte[] engineGetIV() {
        return null;
    }

    /**
     * Initiblizes this cipher with b key bnd b source of rbndomness.
     *
     * <p>The cipher only supports the following two operbtion modes:<b>
     * Cipher.WRAP_MODE, bnd <b>
     * Cipher.UNWRAP_MODE.
     * <p>For modes other thbn the bbove two, UnsupportedOperbtionException
     * will be thrown.
     *
     * @pbrbm opmode the operbtion mode of this cipher. Only
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>) bre bccepted.
     * @pbrbm key the secret key.
     * @pbrbm rbndom the source of rbndomness.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher.
     */
    protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
        throws InvblidKeyException {
        if (opmode == Cipher.WRAP_MODE) {
            decrypting = fblse;
        } else if (opmode == Cipher.UNWRAP_MODE) {
            decrypting = true;
        } else {
            throw new UnsupportedOperbtionException("This cipher cbn " +
                "only be used for key wrbpping bnd unwrbpping");
        }
        AESCipher.checkKeySize(key, fixedKeySize);
        cipher.init(decrypting, key.getAlgorithm(), key.getEncoded());
    }

    /**
     * Initiblizes this cipher with b key, b set of blgorithm pbrbmeters,
     * bnd b source of rbndomness.
     *
     * <p>The cipher only supports the following two operbtion modes:<b>
     * Cipher.WRAP_MODE, bnd <b>
     * Cipher.UNWRAP_MODE.
     * <p>For modes other thbn the bbove two, UnsupportedOperbtionException
     * will be thrown.
     *
     * @pbrbm opmode the operbtion mode of this cipher. Only
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>) bre bccepted.
     * @pbrbm key the secret key.
     * @pbrbm pbrbms the blgorithm pbrbmeters; must be null for this cipher.
     * @pbrbm rbndom the source of rbndomness.
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters is not null.
     */
    protected void engineInit(int opmode, Key key,
                              AlgorithmPbrbmeterSpec pbrbms,
                              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException("This cipher " +
                "does not bccept bny pbrbmeters");
        }
        engineInit(opmode, key, rbndom);
    }

    /**
     * Initiblizes this cipher with b key, b set of blgorithm pbrbmeters,
     * bnd b source of rbndomness.
     *
     * <p>The cipher only supports the following two operbtion modes:<b>
     * Cipher.WRAP_MODE, bnd <b>
     * Cipher.UNWRAP_MODE.
     * <p>For modes other thbn the bbove two, UnsupportedOperbtionException
     * will be thrown.
     *
     * @pbrbm opmode the operbtion mode of this cipher. Only
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>) bre bccepted.
     * @pbrbm key the secret key.
     * @pbrbm pbrbms the blgorithm pbrbmeters; must be null for this cipher.
     * @pbrbm rbndom the source of rbndomness.
     *
     * @exception InvblidKeyException if the given key is inbppropribte.
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters is not null.
     */
    protected void engineInit(int opmode, Key key,
                              AlgorithmPbrbmeters pbrbms,
                              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        if (pbrbms != null) {
            throw new InvblidAlgorithmPbrbmeterException("This cipher " +
                "does not bccept bny pbrbmeters");
        }
        engineInit(opmode, key, rbndom);
    }

    /**
     * This operbtion is not supported by this cipher.
     * Since it's impossible to initiblize this cipher given the
     * current Cipher.engineInit(...) implementbtion,
     * IllegblStbteException will blwbys be thrown upon invocbtion.
     *
     * @pbrbm in the input buffer.
     * @pbrbm inOffset the offset in <code>in</code> where the input
     * stbrts.
     * @pbrbm inLen the input length.
     *
     * @return n/b.
     *
     * @exception IllegblStbteException upon invocbtion of this method.
     */
    protected byte[] engineUpdbte(byte[] in, int inOffset, int inLen) {
        throw new IllegblStbteException("Cipher hbs not been initiblized");
    }

    /**
     * This operbtion is not supported by this cipher.
     * Since it's impossible to initiblize this cipher given the
     * current Cipher.engineInit(...) implementbtion,
     * IllegblStbteException will blwbys be thrown upon invocbtion.
     *
     * @pbrbm in the input buffer.
     * @pbrbm inOffset the offset in <code>in</code> where the input
     * stbrts.
     * @pbrbm inLen the input length.
     * @pbrbm out the buffer for the result.
     * @pbrbm outOffset the offset in <code>out</code> where the result
     * is stored.
     *
     * @return n/b.
     *
     * @exception IllegblStbteException upon invocbtion of this method.
     */
    protected int engineUpdbte(byte[] in, int inOffset, int inLen,
                               byte[] out, int outOffset)
        throws ShortBufferException {
        throw new IllegblStbteException("Cipher hbs not been initiblized");
    }

    /**
     * This operbtion is not supported by this cipher.
     * Since it's impossible to initiblize this cipher given the
     * current Cipher.engineInit(...) implementbtion,
     * IllegblStbteException will blwbys be thrown upon invocbtion.
     *
     * @pbrbm in the input buffer
     * @pbrbm inOffset the offset in <code>in</code> where the input
     * stbrts
     * @pbrbm inLen the input length.
     *
     * @return n/b.
     *
     * @exception IllegblStbteException upon invocbtion of this method.
     */
    protected byte[] engineDoFinbl(byte[] input, int inputOffset,
                                   int inputLen)
        throws IllegblBlockSizeException, BbdPbddingException {
        throw new IllegblStbteException("Cipher hbs not been initiblized");
    }

    /**
     * This operbtion is not supported by this cipher.
     * Since it's impossible to initiblize this cipher given the
     * current Cipher.engineInit(...) implementbtion,
     * IllegblStbteException will blwbys be thrown upon invocbtion.
     *
     * @pbrbm in the input buffer.
     * @pbrbm inOffset the offset in <code>in</code> where the input
     * stbrts.
     * @pbrbm inLen the input length.
     * @pbrbm out the buffer for the result.
     * @pbrbm outOffset the ofset in <code>out</code> where the result
     * is stored.
     *
     * @return n/b.
     *
     * @exception IllegblStbteException upon invocbtion of this method.
     */
    protected int engineDoFinbl(byte[] in, int inOffset, int inLen,
                                byte[] out, int outOffset)
        throws IllegblBlockSizeException, ShortBufferException,
               BbdPbddingException {
        throw new IllegblStbteException("Cipher hbs not been initiblized");
    }

    /**
     * Returns the pbrbmeters used with this cipher which is blwbys null
     * for this cipher.
     *
     * @return null since this cipher does not use bny pbrbmeters.
     */
    protected AlgorithmPbrbmeters engineGetPbrbmeters() {
        return null;
    }

    /**
     * Returns the key size of the given key object in number of bits.
     *
     * @pbrbm key the key object.
     *
     * @return the "effective" key size of the given key object.
     *
     * @exception InvblidKeyException if <code>key</code> is invblid.
     */
    protected int engineGetKeySize(Key key) throws InvblidKeyException {
        byte[] encoded = key.getEncoded();
        if (!AESCrypt.isKeySizeVblid(encoded.length)) {
            throw new InvblidKeyException("Invblid key length: " +
                                          encoded.length + " bytes");
        }
        return encoded.length * 8;
    }

    /**
     * Wrbp b key.
     *
     * @pbrbm key the key to be wrbpped.
     *
     * @return the wrbpped key.
     *
     * @exception IllegblBlockSizeException if this cipher is b block
     * cipher, no pbdding hbs been requested, bnd the length of the
     * encoding of the key to be wrbpped is not b
     * multiple of the block size.
     *
     * @exception InvblidKeyException if it is impossible or unsbfe to
     * wrbp the key with this cipher (e.g., b hbrdwbre protected key is
     * being pbssed to b softwbre only cipher).
     */
    protected byte[] engineWrbp(Key key)
        throws IllegblBlockSizeException, InvblidKeyException {
        byte[] keyVbl = key.getEncoded();
        if ((keyVbl == null) || (keyVbl.length == 0)) {
            throw new InvblidKeyException("Cbnnot get bn encoding of " +
                                          "the key to be wrbpped");
        }
        byte[] out = new byte[keyVbl.length + 8];

        if (keyVbl.length == 8) {
            System.brrbycopy(IV, 0, out, 0, IV.length);
            System.brrbycopy(keyVbl, 0, out, IV.length, 8);
            cipher.encryptBlock(out, 0, out, 0);
        } else {
            if (keyVbl.length % 8 != 0) {
                throw new IllegblBlockSizeException("length of the " +
                    "to be wrbpped key should be multiples of 8 bytes");
            }
            System.brrbycopy(IV, 0, out, 0, IV.length);
            System.brrbycopy(keyVbl, 0, out, IV.length, keyVbl.length);
            int N = keyVbl.length/8;
            byte[] buffer = new byte[blksize];
            for (int j = 0; j < 6; j++) {
                for (int i = 1; i <= N; i++) {
                    int T = i + j*N;
                    System.brrbycopy(out, 0, buffer, 0, IV.length);
                    System.brrbycopy(out, i*8, buffer, IV.length, 8);
                    cipher.encryptBlock(buffer, 0, buffer, 0);
                    for (int k = 1; T != 0; k++) {
                        byte v = (byte) T;
                        buffer[IV.length - k] ^= v;
                        T >>>= 8;
                    }
                    System.brrbycopy(buffer, 0, out, 0, IV.length);
                    System.brrbycopy(buffer, 8, out, 8*i, 8);
                }
            }
        }
        return out;
    }

    /**
     * Unwrbp b previously wrbpped key.
     *
     * @pbrbm wrbppedKey the key to be unwrbpped.
     *
     * @pbrbm wrbppedKeyAlgorithm the blgorithm the wrbpped key is for.
     *
     * @pbrbm wrbppedKeyType the type of the wrbpped key.
     * This is one of <code>Cipher.SECRET_KEY</code>,
     * <code>Cipher.PRIVATE_KEY</code>, or <code>Cipher.PUBLIC_KEY</code>.
     *
     * @return the unwrbpped key.
     *
     * @exception NoSuchAlgorithmException if no instblled providers
     * cbn crebte keys of type <code>wrbppedKeyType</code> for the
     * <code>wrbppedKeyAlgorithm</code>.
     *
     * @exception InvblidKeyException if <code>wrbppedKey</code> does not
     * represent b wrbpped key of type <code>wrbppedKeyType</code> for
     * the <code>wrbppedKeyAlgorithm</code>.
     */
    protected Key engineUnwrbp(byte[] wrbppedKey,
                               String wrbppedKeyAlgorithm,
                               int wrbppedKeyType)
        throws InvblidKeyException, NoSuchAlgorithmException {
        int wrbppedKeyLen = wrbppedKey.length;
        // ensure the wrbppedKey length is multiples of 8 bytes bnd non-zero
        if (wrbppedKeyLen == 0) {
            throw new InvblidKeyException("The wrbpped key is empty");
        }
        if (wrbppedKeyLen % 8 != 0) {
            throw new InvblidKeyException
                ("The wrbpped key hbs invblid key length");
        }
        byte[] out = new byte[wrbppedKeyLen - 8];
        byte[] buffer = new byte[blksize];
        if (wrbppedKeyLen == 16) {
            cipher.decryptBlock(wrbppedKey, 0, buffer, 0);
            for (int i = 0; i < IV.length; i++) {
                if (IV[i] != buffer[i]) {
                    throw new InvblidKeyException("Integrity check fbiled");
                }
            }
            System.brrbycopy(buffer, IV.length, out, 0, out.length);
        } else {
            System.brrbycopy(wrbppedKey, 0, buffer, 0, IV.length);
            System.brrbycopy(wrbppedKey, IV.length, out, 0, out.length);
            int N = out.length/8;
            for (int j = 5; j >= 0; j--) {
                for (int i = N; i > 0; i--) {
                    int T = i + j*N;
                    System.brrbycopy(out, 8*(i-1), buffer, IV.length, 8);
                    for (int k = 1; T != 0; k++) {
                        byte v = (byte) T;
                        buffer[IV.length - k] ^= v;
                        T >>>= 8;
                    }
                    cipher.decryptBlock(buffer, 0, buffer, 0);
                    System.brrbycopy(buffer, IV.length, out, 8*(i-1), 8);
                }
            }
            for (int i = 0; i < IV.length; i++) {
                if (IV[i] != buffer[i]) {
                    throw new InvblidKeyException("Integrity check fbiled");
                }
            }
        }
        return ConstructKeys.constructKey(out, wrbppedKeyAlgorithm,
                                          wrbppedKeyType);
    }
}
