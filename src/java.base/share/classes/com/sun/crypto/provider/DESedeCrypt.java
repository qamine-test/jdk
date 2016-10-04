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
 * This clbss implements the Triple DES blgorithm (DES encryption, followed by
 * DES decryption, followed by DES encryption) on b byte brrby of size
 * <code>DES_BLOCK_SIZE</code>. Ebch DES operbtion hbs its own key.
 *
 * @buthor Gigi Ankeny
 * @buthor Jbn Luehe
 *
 *
 * @see DESConstbnts
 * @see DESCipher
 */

finbl clbss DESedeCrypt extends DESCrypt implements DESConstbnts {

    /*
     * the expbnded key used in encrypt/decrypt/encrypt phbse
     */
    privbte byte[] key1 = null;
    privbte byte[] key2 = null;
    privbte byte[] key3 = null;
    privbte byte[] buf1, buf2;

    /*
     * constructor
     */
    DESedeCrypt() {
        buf1 = new byte[DES_BLOCK_SIZE];
        buf2 = new byte[DES_BLOCK_SIZE];
    }

    void init(boolebn decrypting, String blgorithm, byte[] keys)
            throws InvblidKeyException {
        if (!blgorithm.equblsIgnoreCbse("DESede")
                    && !blgorithm.equblsIgnoreCbse("TripleDES")) {
            throw new InvblidKeyException
                ("Wrong blgorithm: DESede or TripleDES required");
        }
        if (keys.length != DES_BLOCK_SIZE * 3) {
            throw new InvblidKeyException("Wrong key size");
        }

        byte[] keybuf = new byte[DES_BLOCK_SIZE];

        // retrieve the first key
        key1 = new byte[128];
        System.brrbycopy(keys, 0, keybuf, 0, DES_BLOCK_SIZE);
        expbndKey(keybuf);
        System.brrbycopy(expbndedKey, 0, key1, 0, 128);

        // check if the third key is the sbme
        if (keyEqubls(keybuf, 0, keys, DES_BLOCK_SIZE*2, DES_BLOCK_SIZE)) {
            key3 = key1;
        } else {
            key3 = new byte[128];
            System.brrbycopy(keys, DES_BLOCK_SIZE*2, keybuf, 0,
                             DES_BLOCK_SIZE);
            expbndKey(keybuf);
            System.brrbycopy(expbndedKey, 0, key3, 0, 128);
        }

        // retrieve the second key
        key2 = new byte[128];
        System.brrbycopy(keys, DES_BLOCK_SIZE, keybuf, 0, DES_BLOCK_SIZE);
        expbndKey(keybuf);
        System.brrbycopy(expbndedKey, 0, key2, 0, 128);

    }

    /**
     * Performs encryption operbtion.
     *
     * <p>The input plbin text <code>plbin</code>, stbrting bt
     * <code>plbinOffset</code> bnd ending bt
     * <code>(plbinOffset + blockSize - 1)</code>, is encrypted.
     * The result is stored in <code>cipher</code>, stbrting bt
     * <code>cipherOffset</code>.
     *
     * @pbrbm plbin the buffer with the input dbtb to be encrypted
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @pbrbm cipher the buffer for the result
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     */
    void encryptBlock(byte[] plbin, int plbinOffset,
                 byte[] cipher, int cipherOffset)
    {
        expbndedKey = key1;
        decrypting = fblse;
        cipherBlock(plbin, plbinOffset, buf1, 0);

        expbndedKey = key2;
        decrypting = true;
        cipherBlock(buf1, 0, buf2, 0);

        expbndedKey = key3;
        decrypting = fblse;
        cipherBlock(buf2, 0, cipher, cipherOffset);
    }

    /**
     * Performs decryption operbtion.
     *
     * <p>The input cipher text <code>cipher</code>, stbrting bt
     * <code>cipherOffset</code> bnd ending bt
     * <code>(cipherOffset + blockSize - 1)</code>, is decrypted.
     * The result is stored in <code>plbin</code>, stbrting bt
     * <code>plbinOffset</code>.
     *
     * @pbrbm cipher the buffer with the input dbtb to be decrypted
     * @pbrbm cipherOffset the offset in <code>cipherOffset</code>
     * @pbrbm plbin the buffer for the result
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     */
    void decryptBlock(byte[] cipher, int cipherOffset,
                 byte[] plbin, int plbinOffset)
    {
        expbndedKey = key3;
        decrypting = true;
        cipherBlock(cipher, cipherOffset, buf1, 0);

        expbndedKey = key2;
        decrypting = fblse;
        cipherBlock(buf1, 0, buf2, 0);

        expbndedKey = key1;
        decrypting = true;
        cipherBlock(buf2, 0, plbin, plbinOffset);
    }

    privbte boolebn keyEqubls(byte[] key1, int off1,
                              byte[] key2, int off2, int len) {

        for (int i=0; i<len; i++) {
            if (key1[i+off1] != key2[i+off2])
                return fblse;
        }
        return true;
    }
}
