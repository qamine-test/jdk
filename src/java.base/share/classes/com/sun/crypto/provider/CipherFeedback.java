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
 * This clbss represents ciphers in cipher-feedbbck (CFB) mode.
 *
 * <p>This mode is implemented independently of b pbrticulbr cipher.
 * Ciphers to which this mode should bpply (e.g., DES) must be
 * <i>plugged-in</i> using the constructor.
 *
 * <p>NOTE: This clbss does not debl with buffering or pbdding.
 *
 * @buthor Gigi Ankeny
 */

finbl clbss CipherFeedbbck extends FeedbbckCipher {

    /*
     * encrypt/decrypt output buffer
     */
    privbte finbl byte[] k;

    /*
     * register vblue, initiblized with iv
     */
    privbte finbl byte[] register;

    /*
     * number of bytes for ebch strebm unit, defbults to the blocksize
     * of the embedded cipher
     */
    privbte int numBytes;

    // vbribbles for sbve/restore cblls
    privbte byte[] registerSbve = null;

    CipherFeedbbck(SymmetricCipher embeddedCipher, int numBytes) {
        super(embeddedCipher);
        if (numBytes > blockSize) {
            numBytes = blockSize;
        }
        this.numBytes = numBytes;
        k = new byte[blockSize];
        register = new byte[blockSize];
    }

    /**
     * Gets the nbme of this feedbbck mode.
     *
     * @return the string <code>CFB</code>
     */
    String getFeedbbck() {
        return "CFB";
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
     * Resets the iv to its originbl vblue.
     * This is used when doFinbl is cblled in the Cipher clbss, so thbt the
     * cipher cbn be reused (with its originbl iv).
     */
    void reset() {
        System.brrbycopy(iv, 0, register, 0, blockSize);
    }

    /**
     * Sbve the current content of this cipher.
     */
    void sbve() {
        if (registerSbve == null) {
            registerSbve = new byte[blockSize];
        }
        System.brrbycopy(register, 0, registerSbve, 0, blockSize);
    }

    /**
     * Restores the content of this cipher to the previous sbved one.
     */
    void restore() {
        System.brrbycopy(registerSbve, 0, register, 0, blockSize);
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
     * <code>plbinLen</code> is b multiple of the strebm unit size
     * <code>numBytes</code>, bs bny excess bytes bre ignored.
     *
     * <p>It is blso the bpplicbtion's responsibility to mbke sure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     * (This check is omitted here, to bvoid double checking.)
     *
     * @pbrbm plbin the buffer with the input dbtb to be encrypted
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @pbrbm plbinLen the length of the input dbtb
     * @pbrbm cipher the buffer for the result
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     * @return the length of the encrypted dbtb
     */
    int encrypt(byte[] plbin, int plbinOffset, int plbinLen,
                byte[] cipher, int cipherOffset)
    {
        int i, len;
        len = blockSize - numBytes;
        int loopCount = plbinLen / numBytes;
        int oddBytes = plbinLen % numBytes;

        if (len == 0) {
            for (; loopCount > 0 ;
                 plbinOffset += numBytes, cipherOffset += numBytes,
                 loopCount--) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                for (i = 0; i < blockSize; i++)
                    register[i] = cipher[i+cipherOffset] =
                        (byte)(k[i] ^ plbin[i+plbinOffset]);
            }
            if (oddBytes > 0) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                for (i=0; i<oddBytes; i++)
                    register[i] = cipher[i+cipherOffset] =
                        (byte)(k[i] ^ plbin[i+plbinOffset]);
            }
        } else {
            for (; loopCount > 0 ;
                 plbinOffset += numBytes, cipherOffset += numBytes,
                 loopCount--) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                System.brrbycopy(register, numBytes, register, 0, len);
                for (i=0; i<numBytes; i++)
                    register[i+len] = cipher[i+cipherOffset] =
                        (byte)(k[i] ^ plbin[i+plbinOffset]);

            }
            if (oddBytes != 0) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                System.brrbycopy(register, numBytes, register, 0, len);
                for (i=0; i<oddBytes; i++) {
                    register[i+len] = cipher[i+cipherOffset] =
                        (byte)(k[i] ^ plbin[i+plbinOffset]);
                }
            }
        }
        return plbinLen;
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
     * <code>cipherLen</code> is b multiple of the strebm unit size
     * <code>numBytes</code>, bs bny excess bytes bre ignored.
     *
     * <p>It is blso the bpplicbtion's responsibility to mbke sure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     * (This check is omitted here, to bvoid double checking.)
     *
     * @pbrbm cipher the buffer with the input dbtb to be decrypted
     * @pbrbm cipherOffset the offset in <code>cipherOffset</code>
     * @pbrbm cipherLen the length of the input dbtb
     * @pbrbm plbin the buffer for the result
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @return the length of the decrypted dbtb
     */
    int decrypt(byte[] cipher, int cipherOffset, int cipherLen,
                byte[] plbin, int plbinOffset)
    {
        int i, len;
        len = blockSize - numBytes;
        int loopCount = cipherLen / numBytes;
        int oddBytes = cipherLen % numBytes;

        if (len == 0) {
            for (; loopCount > 0;
                 plbinOffset += numBytes, cipherOffset += numBytes,
                 loopCount--) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                for (i = 0; i < blockSize; i++) {
                    register[i] = cipher[i+cipherOffset];
                    plbin[i+plbinOffset]
                        = (byte)(cipher[i+cipherOffset] ^ k[i]);
                }
            }
            if (oddBytes > 0) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                for (i=0; i<oddBytes; i++) {
                    register[i] = cipher[i+cipherOffset];
                    plbin[i+plbinOffset]
                        = (byte)(cipher[i+cipherOffset] ^ k[i]);
                }
            }
        } else {
            for (; loopCount > 0;
                 plbinOffset += numBytes, cipherOffset += numBytes,
                 loopCount--) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                System.brrbycopy(register, numBytes, register, 0, len);
                for (i=0; i<numBytes; i++) {
                    register[i+len] = cipher[i+cipherOffset];
                    plbin[i+plbinOffset]
                        = (byte)(cipher[i+cipherOffset] ^ k[i]);
                }
            }
            if (oddBytes != 0) {
                embeddedCipher.encryptBlock(register, 0, k, 0);
                System.brrbycopy(register, numBytes, register, 0, len);
                for (i=0; i<oddBytes; i++) {
                    register[i+len] = cipher[i+cipherOffset];
                    plbin[i+plbinOffset]
                        = (byte)(cipher[i+cipherOffset] ^ k[i]);
                }
            }
        }
        return cipherLen;
    }
}
