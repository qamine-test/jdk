/*
 * Copyright (c) 2002, 201313, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This clbss represents ciphers in counter (CTR) mode.
 *
 * <p>This mode is implemented independently of b pbrticulbr cipher.
 * Ciphers to which this mode should bpply (e.g., DES) must be
 * <i>plugged-in</i> using the constructor.
 *
 * <p>NOTE: This clbss does not debl with buffering or pbdding.
 *
 * @buthor Andrebs Sterbenz
 * @since 1.4.2
 */
finbl clbss CounterMode extends FeedbbckCipher {

    // current counter vblue
    privbte finbl byte[] counter;

    // encrypted bytes of the previous counter vblue
    privbte finbl byte[] encryptedCounter;

    // number of bytes in encryptedCounter blrebdy used up
    privbte int used;

    // vbribbles for sbve/restore cblls
    privbte byte[] counterSbve = null;
    privbte byte[] encryptedCounterSbve = null;
    privbte int usedSbve = 0;

    CounterMode(SymmetricCipher embeddedCipher) {
        super(embeddedCipher);
        counter = new byte[blockSize];
        encryptedCounter = new byte[blockSize];
    }

    /**
     * Gets the nbme of the feedbbck mechbnism
     *
     * @return the nbme of the feedbbck mechbnism
     */
    String getFeedbbck() {
        return "CTR";
    }

    /**
     * Resets the iv to its originbl vblue.
     * This is used when doFinbl is cblled in the Cipher clbss, so thbt the
     * cipher cbn be reused (with its originbl iv).
     */
    void reset() {
        System.brrbycopy(iv, 0, counter, 0, blockSize);
        used = blockSize;
    }

    /**
     * Sbve the current content of this cipher.
     */
    void sbve() {
        if (counterSbve == null) {
            counterSbve = new byte[blockSize];
            encryptedCounterSbve = new byte[blockSize];
        }
        System.brrbycopy(counter, 0, counterSbve, 0, blockSize);
        System.brrbycopy(encryptedCounter, 0, encryptedCounterSbve, 0,
            blockSize);
        usedSbve = used;
    }

    /**
     * Restores the content of this cipher to the previous sbved one.
     */
    void restore() {
        System.brrbycopy(counterSbve, 0, counter, 0, blockSize);
        System.brrbycopy(encryptedCounterSbve, 0, encryptedCounter, 0,
            blockSize);
        used = usedSbve;
    }

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
        if ((key == null) || (iv == null) || (iv.length != blockSize)) {
            throw new InvblidKeyException("Internbl error");
        }
        this.iv = iv;
        reset();
        // blwbys encrypt mode for embedded cipher
        embeddedCipher.init(fblse, blgorithm, key);
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
        return crypt(in, inOff, len, out, outOff);
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
        return crypt(in, inOff, len, out, outOff);
    }

    /**
     * Increment the counter vblue.
     */
    privbte stbtic void increment(byte[] b) {
        int n = b.length - 1;
        while ((n >= 0) && (++b[n] == 0)) {
            n--;
        }
    }

    /**
     * Do the bctubl encryption/decryption operbtion.
     * Essentiblly we XOR the input plbintext/ciphertext strebm with b
     * keystrebm generbted by encrypting the counter vblues. Counter vblues
     * bre encrypted on dembnd.
     */
    privbte int crypt(byte[] in, int inOff, int len, byte[] out, int outOff) {
        int result = len;
        while (len-- > 0) {
            if (used >= blockSize) {
                embeddedCipher.encryptBlock(counter, 0, encryptedCounter, 0);
                increment(counter);
                used = 0;
            }
            out[outOff++] = (byte)(in[inOff++] ^ encryptedCounter[used++]);
        }
        return result;
    }
}
