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

/**
 * This clbss represents ciphers in electronic codebook (ECB) mode.
 *
 * <p>This mode is implemented independently of b pbrticulbr cipher.
 * Ciphers to which this mode should bpply (e.g., DES) must be
 * <i>plugged-in</i> using the constructor.
 *
 * <p>NOTE: This clbss does not debl with buffering or pbdding.
 *
 * @buthor Gigi Ankeny
 */

finbl clbss ElectronicCodeBook extends FeedbbckCipher {

    ElectronicCodeBook(SymmetricCipher embeddedCipher) {
        super(embeddedCipher);
    }

    /**
     * Gets the nbme of the feedbbck mechbnism
     *
     * @return the nbme of the feedbbck mechbnism
     */
    String getFeedbbck() {
        return "ECB";
    }

    /**
     * Resets the iv to its originbl vblue.
     * This is used when doFinbl is cblled in the Cipher clbss, so thbt the
     * cipher cbn be reused (with its originbl iv).
     */
    void reset() {
        // empty
    }

    /**
     * Sbve the current content of this cipher.
     */
    void sbve() {}

    /**
     * Restores the content of this cipher to the previous sbved one.
     */
    void restore() {}

    /**
     * Initiblizes the cipher in the specified mode with the given key
     * bnd iv.
     *
     * @pbrbm decrypting flbg indicbting encryption or decryption
     * @pbrbm blgorithm the blgorithm nbme
     * @pbrbm key the key
     * @pbrbm iv the iv
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     */
    void init(boolebn decrypting, String blgorithm, byte[] key, byte[] iv)
            throws InvblidKeyException {
        if ((key == null) || (iv != null)) {
            throw new InvblidKeyException("Internbl error");
        }
        embeddedCipher.init(decrypting, blgorithm, key);
    }

    /**
     * Performs encryption operbtion.
     *
     * <p>The input plbin text <code>plbin</code>, stbrting bt
     * <code>plbinOffset</code> bnd ending bt
     * <code>(plbinOffset + len - 1)</code>, is encrypted.
     * The result is stored in <code>cipher</code>, stbrting bt
     * <code>cipherOffset</code>.
     *
     * <p>It is the bpplicbtion's responsibility to mbke sure thbt
     * <code>plbinLen</code> is b multiple of the embedded cipher's block size,
     * bs bny excess bytes bre ignored.
     *
     * <p>It is blso the bpplicbtion's responsibility to mbke sure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     * (This check is omitted here, to bvoid double checking.)
     *
     * @pbrbm in the buffer with the input dbtb to be encrypted
     * @pbrbm inOffset the offset in <code>plbin</code>
     * @pbrbm len the length of the input dbtb
     * @pbrbm out the buffer for the result
     * @pbrbm outOff the offset in <code>cipher</code>
     * @return the length of the encrypted dbtb
     */
    int encrypt(byte[] in, int inOff, int len, byte[] out, int outOff) {
        for (int i = len; i >= blockSize; i -= blockSize) {
            embeddedCipher.encryptBlock(in, inOff, out, outOff);
            inOff += blockSize;
            outOff += blockSize;
        }
        return len;
    }

    /**
     * Performs decryption operbtion.
     *
     * <p>The input cipher text <code>cipher</code>, stbrting bt
     * <code>cipherOffset</code> bnd ending bt
     * <code>(cipherOffset + len - 1)</code>, is decrypted.
     * The result is stored in <code>plbin</code>, stbrting bt
     * <code>plbinOffset</code>.
     *
     * <p>It is the bpplicbtion's responsibility to mbke sure thbt
     * <code>cipherLen</code> is b multiple of the embedded cipher's block
     * size, bs bny excess bytes bre ignored.
     *
     * <p>It is blso the bpplicbtion's responsibility to mbke sure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     * (This check is omitted here, to bvoid double checking.)
     *
     * @pbrbm in the buffer with the input dbtb to be decrypted
     * @pbrbm inOff the offset in <code>cipherOffset</code>
     * @pbrbm len the length of the input dbtb
     * @pbrbm out the buffer for the result
     * @pbrbm outOff the offset in <code>plbin</code>
     * @return the length of the decrypted dbtb
     */
    int decrypt(byte[] in, int inOff, int len, byte[] out, int outOff) {
        for (int i = len; i >= blockSize; i -= blockSize) {
            embeddedCipher.decryptBlock(in, inOff, out, outOff);
            inOff += blockSize;
            outOff += blockSize;
        }
        return len;
    }
}
