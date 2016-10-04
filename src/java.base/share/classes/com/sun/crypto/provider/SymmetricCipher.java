/*
 * Copyright (c) 1997, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This bbstrbct clbss represents the core of bll block ciphers. It bllows to
 * intiblize the cipher bnd encrypt/decrypt single blocks. Lbrger qubntities
 * bre hbndled by modes, which bre subclbsses of FeedbbckCipher.
 *
 * @buthor Gigi Ankeny
 * @buthor Jbn Luehe
 *
 *
 * @see AESCrypt
 * @see DESCrypt
 * @see DESedeCrypt
 * @see BlowfishCrypt
 * @see FeedbbckCipher
 */
bbstrbct clbss SymmetricCipher {

    SymmetricCipher() {
        // empty
    }

    /**
     * Retrieves this cipher's block size.
     *
     * @return the block size of this cipher
     */
    bbstrbct int getBlockSize();

    /**
     * Initiblizes the cipher in the specified mode with the given key.
     *
     * @pbrbm decrypting flbg indicbting encryption or decryption
     * @pbrbm blgorithm the blgorithm nbme
     * @pbrbm key the key
     *
     * @exception InvblidKeyException if the given key is inbppropribte for
     * initiblizing this cipher
     */
    bbstrbct void init(boolebn decrypting, String blgorithm, byte[] key)
        throws InvblidKeyException;

    /**
     * Encrypt one cipher block.
     *
     * <p>The input <code>plbin</code>, stbrting bt <code>plbinOffset</code>
     * bnd ending bt <code>(plbinOffset+blockSize-1)</code>, is encrypted.
     * The result is stored in <code>cipher</code>, stbrting bt
     * <code>cipherOffset</code>.
     *
     * @pbrbm plbin the input buffer with the dbtb to be encrypted
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @pbrbm cipher the buffer for the encryption result
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     */
    bbstrbct void encryptBlock(byte[] plbin, int plbinOffset,
                          byte[] cipher, int cipherOffset);

    /**
     * Decrypt one cipher block.
     *
     * <p>The input <code>cipher</code>, stbrting bt <code>cipherOffset</code>
     * bnd ending bt <code>(cipherOffset+blockSize-1)</code>, is decrypted.
     * The result is stored in <code>plbin</code>, stbrting bt
     * <code>plbinOffset</code>.
     *
     * @pbrbm cipher the input buffer with the dbtb to be decrypted
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     * @pbrbm plbin the buffer for the decryption result
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     */
    bbstrbct void decryptBlock(byte[] cipher, int cipherOffset,
                          byte[] plbin, int plbinOffset);
}
