/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;
import jbvb.security.spec.*;
import jbvbx.crypto.*;
import jbvbx.crypto.spec.*;
import jbvbx.crypto.BbdPbddingException;
import jbvb.nio.ByteBuffer;

/**
 * This clbss implements the AES blgorithm in its vbrious modes
 * (<code>ECB</code>, <code>CFB</code>, <code>OFB</code>, <code>CBC</code>,
 * <code>PCBC</code>) bnd pbdding schemes (<code>PKCS5Pbdding</code>,
 * <code>NoPbdding</code>, <code>ISO10126Pbdding</code>).
 *
 * @buthor Vblerie Peng
 *
 *
 * @see AESCrypt
 * @see CipherBlockChbining
 * @see ElectronicCodeBook
 * @see CipherFeedbbck
 * @see OutputFeedbbck
 */

bbstrbct clbss AESCipher extends CipherSpi {
    public stbtic finbl clbss Generbl extends AESCipher {
        public Generbl() {
            super(-1);
        }
    }
    bbstrbct stbtic clbss OidImpl extends AESCipher {
        protected OidImpl(int keySize, String mode, String pbdding) {
            super(keySize);
            try {
                engineSetMode(mode);
                engineSetPbdding(pbdding);
            } cbtch (GenerblSecurityException gse) {
                // internbl error; re-throw bs provider exception
                ProviderException pe =new ProviderException("Internbl Error");
                pe.initCbuse(gse);
                throw pe;
            }
        }
    }
    public stbtic finbl clbss AES128_ECB_NoPbdding extends OidImpl {
        public AES128_ECB_NoPbdding() {
            super(16, "ECB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES192_ECB_NoPbdding extends OidImpl {
        public AES192_ECB_NoPbdding() {
            super(24, "ECB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES256_ECB_NoPbdding extends OidImpl {
        public AES256_ECB_NoPbdding() {
            super(32, "ECB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES128_CBC_NoPbdding extends OidImpl {
        public AES128_CBC_NoPbdding() {
            super(16, "CBC", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES192_CBC_NoPbdding extends OidImpl {
        public AES192_CBC_NoPbdding() {
            super(24, "CBC", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES256_CBC_NoPbdding extends OidImpl {
        public AES256_CBC_NoPbdding() {
            super(32, "CBC", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES128_OFB_NoPbdding extends OidImpl {
        public AES128_OFB_NoPbdding() {
            super(16, "OFB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES192_OFB_NoPbdding extends OidImpl {
        public AES192_OFB_NoPbdding() {
            super(24, "OFB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES256_OFB_NoPbdding extends OidImpl {
        public AES256_OFB_NoPbdding() {
            super(32, "OFB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES128_CFB_NoPbdding extends OidImpl {
        public AES128_CFB_NoPbdding() {
            super(16, "CFB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES192_CFB_NoPbdding extends OidImpl {
        public AES192_CFB_NoPbdding() {
            super(24, "CFB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES256_CFB_NoPbdding extends OidImpl {
        public AES256_CFB_NoPbdding() {
            super(32, "CFB", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES128_GCM_NoPbdding extends OidImpl {
        public AES128_GCM_NoPbdding() {
            super(16, "GCM", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES192_GCM_NoPbdding extends OidImpl {
        public AES192_GCM_NoPbdding() {
            super(24, "GCM", "NOPADDING");
        }
    }
    public stbtic finbl clbss AES256_GCM_NoPbdding extends OidImpl {
        public AES256_GCM_NoPbdding() {
            super(32, "GCM", "NOPADDING");
        }
    }

    // utility method used by AESCipher bnd AESWrbpCipher
    stbtic finbl void checkKeySize(Key key, int fixedKeySize)
        throws InvblidKeyException {
        if (fixedKeySize != -1) {
            if (key == null) {
                throw new InvblidKeyException("The key must not be null");
            }
            byte[] vblue = key.getEncoded();
            if (vblue == null) {
                throw new InvblidKeyException("Key encoding must not be null");
            } else if (vblue.length != fixedKeySize) {
                throw new InvblidKeyException("The key must be " +
                    fixedKeySize*8 + " bits");
            }
        }
    }

    /*
     * internbl CipherCore object which does the rebl work.
     */
    privbte CipherCore core = null;

    /*
     * needed to support AES oids which bssocibtes b fixed key size
     * to the cipher object.
     */
    privbte finbl int fixedKeySize; // in bytes, -1 if no restriction

    /**
     * Crebtes bn instbnce of AES cipher with defbult ECB mode bnd
     * PKCS5Pbdding.
     */
    protected AESCipher(int keySize) {
        core = new CipherCore(new AESCrypt(), AESConstbnts.AES_BLOCK_SIZE);
        fixedKeySize = keySize;
    }

    /**
     * Sets the mode of this cipher.
     *
     * @pbrbm mode the cipher mode
     *
     * @exception NoSuchAlgorithmException if the requested cipher mode does
     * not exist
     */
    protected void engineSetMode(String mode)
        throws NoSuchAlgorithmException {
        core.setMode(mode);
    }

    /**
     * Sets the pbdding mechbnism of this cipher.
     *
     * @pbrbm pbdding the pbdding mechbnism
     *
     * @exception NoSuchPbddingException if the requested pbdding mechbnism
     * does not exist
     */
    protected void engineSetPbdding(String pbddingScheme)
        throws NoSuchPbddingException {
        core.setPbdding(pbddingScheme);
    }

    /**
     * Returns the block size (in bytes).
     *
     * @return the block size (in bytes), or 0 if the underlying blgorithm is
     * not b block cipher
     */
    protected int engineGetBlockSize() {
        return AESConstbnts.AES_BLOCK_SIZE;
    }

    /**
     * Returns the length in bytes thbt bn output buffer would need to be in
     * order to hold the result of the next <code>updbte</code> or
     * <code>doFinbl</code> operbtion, given the input length
     * <code>inputLen</code> (in bytes).
     *
     * <p>This cbll tbkes into bccount bny unprocessed (buffered) dbtb from b
     * previous <code>updbte</code> cbll, bnd pbdding.
     *
     * <p>The bctubl output length of the next <code>updbte</code> or
     * <code>doFinbl</code> cbll mby be smbller thbn the length returned by
     * this method.
     *
     * @pbrbm inputLen the input length (in bytes)
     *
     * @return the required output buffer size (in bytes)
     */
    protected int engineGetOutputSize(int inputLen) {
        return core.getOutputSize(inputLen);
    }

    /**
     * Returns the initiblizbtion vector (IV) in b new buffer.
     *
     * <p>This is useful in the cbse where b rbndom IV hbs been crebted
     * (see <b href = "#init">init</b>),
     * or in the context of pbssword-bbsed encryption or
     * decryption, where the IV is derived from b user-provided pbssword.
     *
     * @return the initiblizbtion vector in b new buffer, or null if the
     * underlying blgorithm does not use bn IV, or if the IV hbs not yet
     * been set.
     */
    protected byte[] engineGetIV() {
        return core.getIV();
    }

    /**
     * Returns the pbrbmeters used with this cipher.
     *
     * <p>The returned pbrbmeters mby be the sbme thbt were used to initiblize
     * this cipher, or mby contbin the defbult set of pbrbmeters or b set of
     * rbndomly generbted pbrbmeters used by the underlying cipher
     * implementbtion (provided thbt the underlying cipher implementbtion
     * uses b defbult set of pbrbmeters or crebtes new pbrbmeters if it needs
     * pbrbmeters but wbs not initiblized with bny).
     *
     * @return the pbrbmeters used with this cipher, or null if this cipher
     * does not use bny pbrbmeters.
     */
    protected AlgorithmPbrbmeters engineGetPbrbmeters() {
        return core.getPbrbmeters("AES");
    }

    /**
     * Initiblizes this cipher with b key bnd b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or key unwrbpping, depending on
     * the vblue of <code>opmode</code>.
     *
     * <p>If this cipher requires bn initiblizbtion vector (IV), it will get
     * it from <code>rbndom</code>.
     * This behbviour should only be used in encryption or key wrbpping
     * mode, however.
     * When initiblizing b cipher thbt requires bn IV for decryption or
     * key unwrbpping, the IV
     * (sbme IV thbt wbs used for encryption or key wrbpping) must be provided
     * explicitly bs b
     * pbrbmeter, in order to get the correct result.
     *
     * <p>This method blso clebns existing buffer bnd other relbted stbte
     * informbtion.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of
     * the following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the secret key
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     */
    protected void engineInit(int opmode, Key key, SecureRbndom rbndom)
        throws InvblidKeyException {
        checkKeySize(key, fixedKeySize);
        core.init(opmode, key, rbndom);
    }

    /**
     * Initiblizes this cipher with b key, b set of
     * blgorithm pbrbmeters, bnd b source of rbndomness.
     *
     * <p>The cipher is initiblized for one of the following four operbtions:
     * encryption, decryption, key wrbpping or key unwrbpping, depending on
     * the vblue of <code>opmode</code>.
     *
     * <p>If this cipher (including its underlying feedbbck or pbdding scheme)
     * requires bny rbndom bytes, it will get them from <code>rbndom</code>.
     *
     * @pbrbm opmode the operbtion mode of this cipher (this is one of
     * the following:
     * <code>ENCRYPT_MODE</code>, <code>DECRYPT_MODE</code>,
     * <code>WRAP_MODE</code> or <code>UNWRAP_MODE</code>)
     * @pbrbm key the encryption key
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     * @exception InvblidAlgorithmPbrbmeterException if the given blgorithm
     * pbrbmeters bre inbppropribte for this cipher
     */
    protected void engineInit(int opmode, Key key,
                              AlgorithmPbrbmeterSpec pbrbms,
                              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        checkKeySize(key, fixedKeySize);
        core.init(opmode, key, pbrbms, rbndom);
    }

    protected void engineInit(int opmode, Key key,
                              AlgorithmPbrbmeters pbrbms,
                              SecureRbndom rbndom)
        throws InvblidKeyException, InvblidAlgorithmPbrbmeterException {
        checkKeySize(key, fixedKeySize);
        core.init(opmode, key, pbrbms, rbndom);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bre processed, bnd the
     * result is stored in b new buffer.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     *
     * @return the new buffer with the result
     *
     * @exception IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized)
     */
    protected byte[] engineUpdbte(byte[] input, int inputOffset,
                                  int inputLen) {
        return core.updbte(input, inputOffset, inputLen);
    }

    /**
     * Continues b multiple-pbrt encryption or decryption operbtion
     * (depending on how this cipher wbs initiblized), processing bnother dbtb
     * pbrt.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bre processed, bnd the
     * result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code>.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     */
    protected int engineUpdbte(byte[] input, int inputOffset, int inputLen,
                               byte[] output, int outputOffset)
        throws ShortBufferException {
        return core.updbte(input, inputOffset, inputLen, output,
                           outputOffset);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion,
     * or finishes b multiple-pbrt operbtion.
     * The dbtb is encrypted or decrypted, depending on how this cipher wbs
     * initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bnd bny input bytes thbt
     * mby hbve been buffered during b previous <code>updbte</code> operbtion,
     * bre processed, with pbdding (if requested) being bpplied.
     * The result is stored in b new buffer.
     *
     * <p>The cipher is reset to its initibl stbte (uninitiblized) bfter this
     * cbll.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     *
     * @return the new buffer with the result
     *
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     */
    protected byte[] engineDoFinbl(byte[] input, int inputOffset, int inputLen)
        throws IllegblBlockSizeException, BbdPbddingException {
        return core.doFinbl(input, inputOffset, inputLen);
    }

    /**
     * Encrypts or decrypts dbtb in b single-pbrt operbtion,
     * or finishes b multiple-pbrt operbtion.
     * The dbtb is encrypted or decrypted, depending on how this cipher wbs
     * initiblized.
     *
     * <p>The first <code>inputLen</code> bytes in the <code>input</code>
     * buffer, stbrting bt <code>inputOffset</code>, bnd bny input bytes thbt
     * mby hbve been buffered during b previous <code>updbte</code> operbtion,
     * bre processed, with pbdding (if requested) being bpplied.
     * The result is stored in the <code>output</code> buffer, stbrting bt
     * <code>outputOffset</code>.
     *
     * <p>The cipher is reset to its initibl stbte (uninitiblized) bfter this
     * cbll.
     *
     * @pbrbm input the input buffer
     * @pbrbm inputOffset the offset in <code>input</code> where the input
     * stbrts
     * @pbrbm inputLen the input length
     * @pbrbm output the buffer for the result
     * @pbrbm outputOffset the offset in <code>output</code> where the result
     * is stored
     *
     * @return the number of bytes stored in <code>output</code>
     *
     * @exception IllegblBlockSizeException if this cipher is b block cipher,
     * no pbdding hbs been requested (only in encryption mode), bnd the totbl
     * input length of the dbtb processed by this cipher is not b multiple of
     * block size
     * @exception ShortBufferException if the given output buffer is too smbll
     * to hold the result
     * @exception BbdPbddingException if this cipher is in decryption mode,
     * bnd (un)pbdding hbs been requested, but the decrypted dbtb is not
     * bounded by the bppropribte pbdding bytes
     */
    protected int engineDoFinbl(byte[] input, int inputOffset, int inputLen,
                                byte[] output, int outputOffset)
        throws IllegblBlockSizeException, ShortBufferException,
               BbdPbddingException {
        return core.doFinbl(input, inputOffset, inputLen, output,
                            outputOffset);
    }

    /**
     *  Returns the key size of the given key object.
     *
     * @pbrbm key the key object.
     *
     * @return the key size of the given key object.
     *
     * @exception InvblidKeyException if <code>key</code> is invblid.
     */
    protected int engineGetKeySize(Key key) throws InvblidKeyException {
        byte[] encoded = key.getEncoded();
        if (!AESCrypt.isKeySizeVblid(encoded.length)) {
            throw new InvblidKeyException("Invblid AES key length: " +
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
        return core.wrbp(key);
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
        return core.unwrbp(wrbppedKey, wrbppedKeyAlgorithm,
                           wrbppedKeyType);
    }

    /**
     * Continues b multi-pbrt updbte of the Additionbl Authenticbtion
     * Dbtb (AAD), using b subset of the provided buffer.
     * <p>
     * Cblls to this method provide AAD to the cipher when operbting in
     * modes such bs AEAD (GCM/CCM).  If this cipher is operbting in
     * either GCM or CCM mode, bll AAD must be supplied before beginning
     * operbtions on the ciphertext (vib the {@code updbte} bnd {@code
     * doFinbl} methods).
     *
     * @pbrbm src the buffer contbining the AAD
     * @pbrbm offset the offset in {@code src} where the AAD input stbrts
     * @pbrbm len the number of AAD bytes
     *
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if this method
     * hbs not been overridden by bn implementbtion
     *
     * @since 1.8
     */
    @Override
    protected void engineUpdbteAAD(byte[] src, int offset, int len) {
        core.updbteAAD(src, offset, len);
    }

    /**
     * Continues b multi-pbrt updbte of the Additionbl Authenticbtion
     * Dbtb (AAD).
     * <p>
     * Cblls to this method provide AAD to the cipher when operbting in
     * modes such bs AEAD (GCM/CCM).  If this cipher is operbting in
     * either GCM or CCM mode, bll AAD must be supplied before beginning
     * operbtions on the ciphertext (vib the {@code updbte} bnd {@code
     * doFinbl} methods).
     * <p>
     * All {@code src.rembining()} bytes stbrting bt
     * {@code src.position()} bre processed.
     * Upon return, the input buffer's position will be equbl
     * to its limit; its limit will not hbve chbnged.
     *
     * @pbrbm src the buffer contbining the AAD
     *
     * @throws IllegblStbteException if this cipher is in b wrong stbte
     * (e.g., hbs not been initiblized), does not bccept AAD, or if
     * operbting in either GCM or CCM mode bnd one of the {@code updbte}
     * methods hbs blrebdy been cblled for the bctive
     * encryption/decryption operbtion
     * @throws UnsupportedOperbtionException if this method
     * hbs not been overridden by bn implementbtion
     *
     * @since 1.8
     */
    @Override
    protected void engineUpdbteAAD(ByteBuffer src) {
        if (src != null) {
            int bbdLen = src.limit() - src.position();
            if (bbdLen != 0) {
                if (src.hbsArrby()) {
                    int bbdOfs = src.brrbyOffset() + src.position();
                    core.updbteAAD(src.brrby(), bbdOfs, bbdLen);
                    src.position(src.limit());
                } else {
                    byte[] bbd = new byte[bbdLen];
                    src.get(bbd);
                    core.updbteAAD(bbd, 0, bbdLen);
                }
            }
        }
    }
}

