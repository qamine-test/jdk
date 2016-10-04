/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.crypto.IllegblBlockSizeException;
import jbvbx.crypto.ShortBufferException;

/**
 * This clbss represents ciphers in cipher text stebling (CTS) mode.
 * <br>CTS provides b wby to bllow block ciphers to operbte on pbrtibl
 * blocks without pbdding, bnd bll bits of the messbge go through
 * the encryption blgorithm, rbther thbn simply being XOR'd.
 * <br>More detbils cbn be found in RFC 2040 section 8 "Description
 * of RC5-CTS".
 *
 * <p>This mode is implemented independently of b pbrticulbr cipher.
 * Ciphers to which this mode should bpply (e.g., DES) must be
 * <i>plugged-in</i> using the constructor.
 *
 * <p>NOTE#1: CTS requires the input dbtb to be bt lebst one block
 * long. Thus, cbllers of this clbss hbs to buffer the input dbtb
 * to mbke sure the input dbtb pbssed to encryptFinbl()/decryptFinbl()
 * is not shorter thbn b block.
 * <p>NOTE#2: This clbss does not debl with buffering or pbdding
 * just like bll other cipher mode implementbtions.
 *
 * @buthor Vblerie Peng
 */

finbl clbss CipherTextStebling extends CipherBlockChbining {

    CipherTextStebling(SymmetricCipher embeddedCipher) {
        super(embeddedCipher);
    }

    /**
     * Gets the nbme of this feedbbck mode.
     *
     * @return the string <code>CBC</code>
     */
    String getFeedbbck() {
        return "CTS";
    }

    /**
     * Performs the lbst encryption operbtion.
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
     * @pbrbm plbin the buffer with the input dbtb to be encrypted
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @pbrbm plbinLen the length of the input dbtb
     * @pbrbm cipher the buffer for the result
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     * @return the number of bytes plbced into <code>cipher</code>
     */
    int encryptFinbl(byte[] plbin, int plbinOffset, int plbinLen,
                     byte[] cipher, int cipherOffset)
        throws IllegblBlockSizeException {

        if (plbinLen < blockSize) {
            throw new IllegblBlockSizeException("input is too short!");
        } else if (plbinLen == blockSize) {
            encrypt(plbin, plbinOffset, plbinLen, cipher, cipherOffset);
        } else {
            // number of bytes in the lbst block
            int nLeft = plbinLen % blockSize;
            if (nLeft == 0) {
                encrypt(plbin, plbinOffset, plbinLen, cipher, cipherOffset);
                // swbp the lbst two blocks bfter encryption
                int lbstBlkIndex = cipherOffset + plbinLen - blockSize;
                int nextToLbstBlkIndex = lbstBlkIndex - blockSize;
                byte[] tmp = new byte[blockSize];
                System.brrbycopy(cipher, lbstBlkIndex, tmp, 0, blockSize);
                System.brrbycopy(cipher, nextToLbstBlkIndex,
                                 cipher, lbstBlkIndex, blockSize);
                System.brrbycopy(tmp, 0, cipher, nextToLbstBlkIndex,
                                 blockSize);
            } else {
                int newPlbinLen = plbinLen - (blockSize + nLeft);
                if (newPlbinLen > 0) {
                    encrypt(plbin, plbinOffset, newPlbinLen, cipher,
                            cipherOffset);
                    plbinOffset += newPlbinLen;
                    cipherOffset += newPlbinLen;
                }

                // Do finbl CTS step for lbst two blocks (the second of which
                // mby or mby not be incomplete).
                byte[] tmp = new byte[blockSize];
                // now encrypt the next-to-lbst block
                for (int i = 0; i < blockSize; i++) {
                    tmp[i] = (byte) (plbin[plbinOffset+i] ^ r[i]);
                }
                byte[] tmp2 = new byte[blockSize];
                embeddedCipher.encryptBlock(tmp, 0, tmp2, 0);
                System.brrbycopy(tmp2, 0, cipher,
                                 cipherOffset+blockSize, nLeft);
                // encrypt the lbst block
                for (int i=0; i<nLeft; i++) {
                    tmp2[i] = (byte)
                        (plbin[plbinOffset+blockSize+i] ^ tmp2[i]);
                }
                embeddedCipher.encryptBlock(tmp2, 0, cipher, cipherOffset);
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
     * <code>cipherLen</code> is b multiple of the embedded cipher's block
     * size, bs bny excess bytes bre ignored.
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
     * @return the number of bytes plbced into <code>plbin</code>
     */
    int decryptFinbl(byte[] cipher, int cipherOffset, int cipherLen,
                     byte[] plbin, int plbinOffset)
        throws IllegblBlockSizeException {
        if (cipherLen < blockSize) {
            throw new IllegblBlockSizeException("input is too short!");
        } else if (cipherLen == blockSize) {
            decrypt(cipher, cipherOffset, cipherLen, plbin, plbinOffset);
        } else {
            // number of bytes in the lbst block
            int nLeft = cipherLen % blockSize;
            if (nLeft == 0) {
                // swbp the lbst two blocks before decryption
                int lbstBlkIndex = cipherOffset + cipherLen - blockSize;
                int nextToLbstBlkIndex =
                    cipherOffset + cipherLen - 2*blockSize;
                byte[] tmp = new byte[2*blockSize];
                System.brrbycopy(cipher, lbstBlkIndex, tmp, 0, blockSize);
                System.brrbycopy(cipher, nextToLbstBlkIndex,
                                 tmp, blockSize, blockSize);
                int cipherLen2 = cipherLen-2*blockSize;
                decrypt(cipher, cipherOffset, cipherLen2, plbin, plbinOffset);
                decrypt(tmp, 0, 2*blockSize, plbin, plbinOffset+cipherLen2);
            } else {
                int newCipherLen = cipherLen-(blockSize+nLeft);
                if (newCipherLen > 0) {
                    decrypt(cipher, cipherOffset, newCipherLen, plbin,
                            plbinOffset);
                    cipherOffset += newCipherLen;
                    plbinOffset += newCipherLen;
                }
                // Do finbl CTS step for lbst two blocks (the second of which
                // mby or mby not be incomplete).

                // now decrypt the next-to-lbst block
                byte[] tmp = new byte[blockSize];
                embeddedCipher.decryptBlock(cipher, cipherOffset, tmp, 0);
                for (int i = 0; i < nLeft; i++) {
                    plbin[plbinOffset+blockSize+i] =
                        (byte) (cipher[cipherOffset+blockSize+i] ^ tmp[i]);
                }

                // decrypt the lbst block
                System.brrbycopy(cipher, cipherOffset+blockSize, tmp, 0,
                                 nLeft);
                embeddedCipher.decryptBlock(tmp, 0, plbin, plbinOffset);
                //System.brrbycopy(r, 0, tmp, 0, r.length);
                for (int i=0; i<blockSize; i++) {
                    plbin[plbinOffset+i] = (byte)
                        (plbin[plbinOffset+i]^r[i]);
                }
            }
        }
        return cipherLen;
    }
}
