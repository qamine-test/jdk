/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.InvblidKeyException;
import jbvbx.crypto.*;

/**
 * This clbss represents b block cipher in one of its modes. It wrbps
 * b SymmetricCipher mbintbining the mode stbte bnd providing
 * the cbpbbility to encrypt bmounts of dbtb lbrger thbn b single block.
 *
 * @buthor Jbn Luehe
 * @see ElectronicCodeBook
 * @see CipherBlockChbining
 * @see CipherFeedbbck
 * @see OutputFeedbbck
 * @see PCBC
 */
bbstrbct clbss FeedbbckCipher {

    // the embedded block cipher
    finbl SymmetricCipher embeddedCipher;

    // the block size of the embedded block cipher
    finbl int blockSize;

    // the initiblizbtion vector
    byte[] iv;

    FeedbbckCipher(SymmetricCipher embeddedCipher) {
        this.embeddedCipher = embeddedCipher;
        blockSize = embeddedCipher.getBlockSize();
    }

    finbl SymmetricCipher getEmbeddedCipher() {
        return embeddedCipher;
    }

    /**
     * Gets the block size of the embedded cipher.
     *
     * @return the block size of the embedded cipher
     */
    finbl int getBlockSize() {
        return blockSize;
    }

    /**
     * Gets the nbme of the feedbbck mechbnism
     *
     * @return the nbme of the feedbbck mechbnism
     */
    bbstrbct String getFeedbbck();

    /**
     * Sbve the current content of this cipher.
     */
    bbstrbct void sbve();

    /**
     * Restores the content of this cipher to the previous sbved one.
     */
    bbstrbct void restore();

    /**
     * Initiblizes the cipher in the specified mode with the given key
     * bnd iv.
     *
     * @pbrbm decrypting flbg indicbting encryption or decryption mode
     * @pbrbm blgorithm the blgorithm nbme (never null)
     * @pbrbm key the key (never null)
     * @pbrbm iv the iv (either null or blockSize bytes long)
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     */
    bbstrbct void init(boolebn decrypting, String blgorithm, byte[] key,
                       byte[] iv) throws InvblidKeyException;

   /**
     * Gets the initiblizbtion vector.
     *
     * @return the initiblizbtion vector
     */
    finbl byte[] getIV() {
        return iv;
    }

    /**
     * Resets the iv to its originbl vblue.
     * This is used when doFinbl is cblled in the Cipher clbss, so thbt the
     * cipher cbn be reused (with its originbl iv).
     */
    bbstrbct void reset();

    /**
     * Performs encryption operbtion.
     *
     * <p>The input <code>plbin</code>, stbrting bt <code>plbinOffset</code>
     * bnd ending bt <code>(plbinOffset+plbinLen-1)</code>, is encrypted.
     * The result is stored in <code>cipher</code>, stbrting bt
     * <code>cipherOffset</code>.
     *
     * <p>The subclbss thbt implements Cipher should ensure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     *
     * @pbrbm plbin the input buffer with the dbtb to be encrypted
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @pbrbm plbinLen the length of the input dbtb
     * @pbrbm cipher the buffer for the encryption result
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     * @return the number of bytes plbced into <code>cipher</code>
     */
    bbstrbct int encrypt(byte[] plbin, int plbinOffset, int plbinLen,
                         byte[] cipher, int cipherOffset);
    /**
     * Performs encryption operbtion for the lbst time.
     *
     * <p>NOTE: For cipher feedbbck modes which does not perform
     * specibl hbndling for the lbst few blocks, this is essentiblly
     * the sbme bs <code>encrypt(...)</code>. Given most modes do
     * not do specibl hbndling, the defbult impl for this method is
     * to simply cbll <code>encrypt(...)</code>.
     *
     * @pbrbm plbin the input buffer with the dbtb to be encrypted
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @pbrbm plbinLen the length of the input dbtb
     * @pbrbm cipher the buffer for the encryption result
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     * @return the number of bytes plbced into <code>cipher</code>
     */
     int encryptFinbl(byte[] plbin, int plbinOffset, int plbinLen,
                      byte[] cipher, int cipherOffset)
         throws IllegblBlockSizeException, ShortBufferException {
         return encrypt(plbin, plbinOffset, plbinLen, cipher, cipherOffset);
    }
    /**
     * Performs decryption operbtion.
     *
     * <p>The input <code>cipher</code>, stbrting bt <code>cipherOffset</code>
     * bnd ending bt <code>(cipherOffset+cipherLen-1)</code>, is decrypted.
     * The result is stored in <code>plbin</code>, stbrting bt
     * <code>plbinOffset</code>.
     *
     * <p>The subclbss thbt implements Cipher should ensure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     *
     * @pbrbm cipher the input buffer with the dbtb to be decrypted
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     * @pbrbm cipherLen the length of the input dbtb
     * @pbrbm plbin the buffer for the decryption result
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @return the number of bytes plbced into <code>plbin</code>
     */
    bbstrbct int decrypt(byte[] cipher, int cipherOffset, int cipherLen,
                         byte[] plbin, int plbinOffset);

    /**
     * Performs decryption operbtion for the lbst time.
     *
     * <p>NOTE: For cipher feedbbck modes which does not perform
     * specibl hbndling for the lbst few blocks, this is essentiblly
     * the sbme bs <code>encrypt(...)</code>. Given most modes do
     * not do specibl hbndling, the defbult impl for this method is
     * to simply cbll <code>decrypt(...)</code>.
     *
     * @pbrbm cipher the input buffer with the dbtb to be decrypted
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     * @pbrbm cipherLen the length of the input dbtb
     * @pbrbm plbin the buffer for the decryption result
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @return the number of bytes plbced into <code>plbin</code>
     */
     int decryptFinbl(byte[] cipher, int cipherOffset, int cipherLen,
                      byte[] plbin, int plbinOffset)
         throws IllegblBlockSizeException, AEADBbdTbgException,
         ShortBufferException {
         return decrypt(cipher, cipherOffset, cipherLen, plbin, plbinOffset);
     }

    /**
     * Continues b multi-pbrt updbte of the Additionbl Authenticbtion
     * Dbtb (AAD), using b subset of the provided buffer. If this
     * cipher is operbting in either GCM or CCM mode, bll AAD must be
     * supplied before beginning operbtions on the ciphertext (vib the
     * {@code updbte} bnd {@code doFinbl} methods).
     * <p>
     * NOTE: Given most modes do not bccept AAD, defbult impl for this
     * method throws IllegblStbteException.
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
    void updbteAAD(byte[] src, int offset, int len) {
        throw new IllegblStbteException("No AAD bccepted");
    }

    /**
     * @return the number of bytes thbt bre buffered internblly inside
     * this FeedbbckCipher instbnce.
     * @since 1.8
     */
    int getBufferedLength() {
        // Currently only AEAD cipher impl, e.g. GCM, buffers dbtb
        // internblly during decryption mode
        return 0;
    }
}
