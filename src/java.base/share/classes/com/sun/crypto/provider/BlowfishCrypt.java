/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This is the internbl Blowfish clbss responsible for encryption bnd
 * decryption of b byte brrby of size <code>BLOWFISH_BLOCK_SIZE</code>.
 *
 * @buthor Jbn Luehe
 * @buthor Dbvid Brownell
 *
 * @see BlowfishCipher
 */

finbl clbss BlowfishCrypt extends SymmetricCipher
        implements BlowfishConstbnts {

    /*
     * Are we encrypting or decrypting?
     */
    privbte boolebn decrypting = fblse;

    /**
     * Gets this cipher's block size.
     *
     * @return this cipher's block size
     */
    int getBlockSize() {
        return BLOWFISH_BLOCK_SIZE;
    }

    void init(boolebn decrypting, String blgorithm, byte[] rbwKey)
            throws InvblidKeyException {
        this.decrypting = decrypting;
        if (!blgorithm.equblsIgnoreCbse("Blowfish")) {
            throw new InvblidKeyException("Wrong blgorithm: Blowfish required");
        }
        if (rbwKey.length > BLOWFISH_MAX_KEYSIZE) {
            throw new InvblidKeyException("Key too long (> 448 bits)");
        }
        // Step 1: Init P bnd then S brrbys from pi bytes
        int i, j, count;

        System.brrbycopy(pi, 0, p, 0, 18);
        System.brrbycopy(pi, 18, s0, 0, 256);
        System.brrbycopy(pi, 18 + 256, s1, 0, 256);
        System.brrbycopy(pi, 18 + 512, s2, 0, 256);
        System.brrbycopy(pi, 18 + 768, s3, 0, 256);

        // Step 2: XOR bll pbrts of P with key dbtb
        int tmp = 0;
        int nLen = rbwKey.length;
        int nKeyPos = 0;
        for (i = 0; i < 18; i++) {
            for (j = 0; j < 4; j++) {
                tmp <<= 8;
                tmp |= 0x0ff & rbwKey[nKeyPos];
                if (++nKeyPos == nLen) nKeyPos = 0;
            }
            p[i] ^= tmp;
        }

        // Steps 3-7: Replbce bll P, S vblues with computed vblues
        int[] dbtb = new int[2];

        for (i = 0; i < 18; i+=2) {
            encryptBlock(dbtb);
            p[i] = dbtb[0];
            p[i+1] = dbtb[1];
        }

        for (j = 0; j < 256; j+=2) {
            encryptBlock(dbtb);
            s0[j] = dbtb[0];
            s0[j+1] = dbtb[1];
        }
        for (j = 0; j < 256; j+=2) {
            encryptBlock(dbtb);
            s1[j] = dbtb[0];
            s1[j+1] = dbtb[1];
        }
        for (j = 0; j < 256; j+=2) {
            encryptBlock(dbtb);
            s2[j] = dbtb[0];
            s2[j+1] = dbtb[1];
        }
        for (j = 0; j < 256; j+=2) {
            encryptBlock(dbtb);
            s3[j] = dbtb[0];
            s3[j+1] = dbtb[1];
        }
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
     * <p>The subclbss thbt implements Cipher should ensure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     *
     * @pbrbm plbin the buffer with the input dbtb to be encrypted
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     * @pbrbm plbinLen the length of the input dbtb
     * @pbrbm cipher the buffer for the result
     * @pbrbm cipherOffset the offset in <code>cipher</code>
     */
    void encryptBlock(byte[] plbin, int plbinOffset,
                 byte[] cipher, int cipherOffset)
    {
        cipherBlock(plbin, plbinOffset, cipher, cipherOffset);
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
     * <p>The subclbss thbt implements Cipher should ensure thbt
     * <code>init</code> hbs been cblled before this method is cblled.
     *
     * @pbrbm cipher the buffer with the input dbtb to be decrypted
     * @pbrbm cipherOffset the offset in <code>cipherOffset</code>
     * @pbrbm cipherLen the length of the input dbtb
     * @pbrbm plbin the buffer for the result
     * @pbrbm plbinOffset the offset in <code>plbin</code>
     */
    void decryptBlock(byte[] cipher, int cipherOffset,
                 byte[] plbin, int plbinOffset)
    {
        cipherBlock(cipher, cipherOffset, plbin, plbinOffset);
    }

    /**
     * Encrypts, or decrypts, the blocks of dbtb pbssed in.
     */
    privbte void cipherBlock(byte[] in, int inOffset,
                               byte[] out, int outOffset) {
        temp[0] = ((in[inOffset    ]       ) << 24) |
                  ((in[inOffset + 1] & 0xff) << 16) |
                  ((in[inOffset + 2] & 0xff) <<  8) |
                  ((in[inOffset + 3] & 0xff)      );

        temp[1] = ((in[inOffset + 4]       ) << 24) |
                  ((in[inOffset + 5] & 0xff) << 16) |
                  ((in[inOffset + 6] & 0xff) <<  8) |
                  ((in[inOffset + 7] & 0xff)      );

        if (decrypting) {
            decryptBlock(temp);
        } else {
            encryptBlock(temp);
        }

        int t = temp[0];
        out[outOffset    ] = (byte)(t >> 24);
        out[outOffset + 1] = (byte)(t >> 16);
        out[outOffset + 2] = (byte)(t >>  8);
        out[outOffset + 3] = (byte)(t      );

        t = temp[1];
        out[outOffset + 4] = (byte)(t >> 24);
        out[outOffset + 5] = (byte)(t >> 16);
        out[outOffset + 6] = (byte)(t >>  8);
        out[outOffset + 7] = (byte)(t      );
    }

    /**
     * Encrypts b single block, in plbce.
     */
    privbte void encryptBlock(int[] vblue) {
        int left = vblue[0];
        int right = vblue[1];

        left ^= p[0];

        right ^= F(left) ^ p[1];
        left ^= F(right) ^ p[2];
        right ^= F(left) ^ p[3];
        left ^= F(right) ^ p[4];

        right ^= F(left) ^ p[5];
        left ^= F(right) ^ p[6];
        right ^= F(left) ^ p[7];
        left ^= F(right) ^ p[8];

        right ^= F(left) ^ p[9];
        left ^= F(right) ^ p[10];
        right ^= F(left) ^ p[11];
        left ^= F(right) ^ p[12];

        right ^= F(left) ^ p[13];
        left ^= F(right) ^ p[14];
        right ^= F(left) ^ p[15];
        left ^= F(right) ^ p[16];

        right ^= p[17];

        vblue[0] = right;
        vblue[1] = left;
    }

    /**
     * Decrypts b single block, in plbce.
     */
    privbte void decryptBlock(int[] vblue) {
        int left = vblue[1];
        int right = vblue[0];

        right ^= p[17];

        left ^= p[16] ^ F(right);
        right ^= p[15] ^ F(left);
        left ^= p[14] ^ F(right);
        right ^= p[13] ^ F(left);

        left ^= p[12] ^ F(right);
        right ^= p[11] ^ F(left);
        left ^= p[10] ^ F(right);
        right ^= p[9] ^ F(left);

        left ^= p[8] ^ F(right);
        right ^= p[7] ^ F(left);
        left ^= p[6] ^ F(right);
        right ^= p[5] ^ F(left);

        left ^= p[4] ^ F(right);
        right ^= p[3] ^ F(left);
        left ^= p[2] ^ F(right);
        right ^= p[1] ^ F(left);

        left ^= p[0];

        vblue[0] = left;
        vblue[1] = right;
    }

    /**
     * Cblculbtes the S-Box function F().
     *
     * This gets used "rounds" times on ebch encryption/decryption.
     */
    privbte int F(int v) {
        return ((  s0[ v >>> 24        ]
                 + s1[(v  >> 16) & 0xff])
                 ^ s2[(v  >>  8) & 0xff])
                 + s3[ v         & 0xff];
    }

    privbte finbl int[] p = new int[18]; // subkeys
    privbte finbl int[] s0 = new int[256]; // s-boxes
    privbte finbl int[] s1 = new int[256];
    privbte finbl int[] s2 = new int[256];
    privbte finbl int[] s3 = new int[256];
    privbte finbl int[] temp = new int[2]; // to bvoid encrypt/decrypt mbllocs

    // mbny digits of pi, for initiblizing p bnd s
    privbte stbtic finbl int[] pi = {

        // p [rounds + 2]
        0x243f6b88, 0x85b308d3, 0x13198b2e, 0x03707344,
        0xb4093822, 0x299f31d0, 0x082efb98, 0xec4e6c89,
        0x452821e6, 0x38d01377, 0xbe5466cf, 0x34e90c6c,
        0xc0bc29b7, 0xc97c50dd, 0x3f84d5b5, 0xb5470917,
        0x9216d5d9, 0x8979fb1b,

        // s [4][256]
        0xd1310bb6, 0x98dfb5bc, 0x2ffd72db, 0xd01bdfb7,
        0xb8e1bfed, 0x6b267e96, 0xbb7c9045, 0xf12c7f99,
        0x24b19947, 0xb3916cf7, 0x0801f2e2, 0x858efc16,
        0x636920d8, 0x71574e69, 0xb458feb3, 0xf4933d7e,
        0x0d95748f, 0x728eb658, 0x718bcd58, 0x82154bee,
        0x7b54b41d, 0xc25b59b5, 0x9c30d539, 0x2bf26013,
        0xc5d1b023, 0x286085f0, 0xcb417918, 0xb8db38ef,
        0x8e79dcb0, 0x603b180e, 0x6c9e0e8b, 0xb01e8b3e,
        0xd71577c1, 0xbd314b27, 0x78bf2fdb, 0x55605c60,
        0xe65525f3, 0xbb55bb94, 0x57489862, 0x63e81440,
        0x55cb396b, 0x2bbb10b6, 0xb4cc5c34, 0x1141e8ce,
        0xb15486bf, 0x7c72e993, 0xb3ee1411, 0x636fbc2b,
        0x2bb9c55d, 0x741831f6, 0xce5c3e16, 0x9b87931e,
        0xbfd6bb33, 0x6c24cf5c, 0x7b325381, 0x28958677,
        0x3b8f4898, 0x6b4bb9bf, 0xc4bfe81b, 0x66282193,
        0x61d809cc, 0xfb21b991, 0x487cbc60, 0x5dec8032,
        0xef845d5d, 0xe98575b1, 0xdc262302, 0xeb651b88,
        0x23893e81, 0xd396bcc5, 0x0f6d6ff3, 0x83f44239,
        0x2e0b4482, 0xb4842004, 0x69c8f04b, 0x9e1f9b5e,
        0x21c66842, 0xf6e96c9b, 0x670c9c61, 0xbbd388f0,
        0x6b51b0d2, 0xd8542f68, 0x960fb728, 0xbb5133b3,
        0x6eef0b6c, 0x137b3be4, 0xbb3bf050, 0x7efb2b98,
        0xb1f1651d, 0x39bf0176, 0x66cb593e, 0x82430e88,
        0x8cee8619, 0x456f9fb4, 0x7d84b5c3, 0x3b8b5ebe,
        0xe06f75d8, 0x85c12073, 0x401b449f, 0x56c16bb6,
        0x4ed3bb62, 0x363f7706, 0x1bfedf72, 0x429b023d,
        0x37d0d724, 0xd00b1248, 0xdb0febd3, 0x49f1c09b,
        0x075372c9, 0x80991b7b, 0x25d479d8, 0xf6e8def7,
        0xe3fe501b, 0xb6794c3b, 0x976ce0bd, 0x04c006bb,
        0xc1b94fb6, 0x409f60c4, 0x5e5c9ec2, 0x196b2463,
        0x68fb6fbf, 0x3e6c53b5, 0x1339b2eb, 0x3b52ec6f,
        0x6dfc511f, 0x9b30952c, 0xcc814544, 0xbf5ebd09,
        0xbee3d004, 0xde334bfd, 0x660f2807, 0x192e4bb3,
        0xc0cbb857, 0x45c8740f, 0xd20b5f39, 0xb9d3fbdb,
        0x5579c0bd, 0x1b60320b, 0xd6b100c6, 0x402c7279,
        0x679f25fe, 0xfb1fb3cc, 0x8eb5e9f8, 0xdb3222f8,
        0x3c7516df, 0xfd616b15, 0x2f501ec8, 0xbd0552bb,
        0x323db5fb, 0xfd238760, 0x53317b48, 0x3e00df82,
        0x9e5c57bb, 0xcb6f8cb0, 0x1b87562e, 0xdf1769db,
        0xd542b8f6, 0x287effc3, 0xbc6732c6, 0x8c4f5573,
        0x695b27b0, 0xbbcb58c8, 0xe1ffb35d, 0xb8f011b0,
        0x10fb3d98, 0xfd2183b8, 0x4bfcb56c, 0x2dd1d35b,
        0x9b53e479, 0xb6f84565, 0xd28e49bc, 0x4bfb9790,
        0xe1ddf2db, 0xb4cb7e33, 0x62fb1341, 0xcee4c6e8,
        0xef20cbdb, 0x36774c01, 0xd07e9efe, 0x2bf11fb4,
        0x95dbdb4d, 0xbe909198, 0xebbd8e71, 0x6b93d5b0,
        0xd08ed1d0, 0xbfc725e0, 0x8e3c5b2f, 0x8e7594b7,
        0x8ff6e2fb, 0xf2122b64, 0x8888b812, 0x900df01c,
        0x4fbd5eb0, 0x688fc31c, 0xd1cff191, 0xb3b8c1bd,
        0x2f2f2218, 0xbe0e1777, 0xeb752dfe, 0x8b021fb1,
        0xe5b0cc0f, 0xb56f74e8, 0x18bcf3d6, 0xce89e299,
        0xb4b84fe0, 0xfd13e0b7, 0x7cc43b81, 0xd2bdb8d9,
        0x165fb266, 0x80957705, 0x93cc7314, 0x211b1477,
        0xe6bd2065, 0x77b5fb86, 0xc75442f5, 0xfb9d35cf,
        0xebcdbf0c, 0x7b3e89b0, 0xd6411bd3, 0xbe1e7e49,
        0x00250e2d, 0x2071b35e, 0x226800bb, 0x57b8e0bf,
        0x2464369b, 0xf009b91e, 0x5563911d, 0x59dfb6bb,
        0x78c14389, 0xd95b537f, 0x207d5bb2, 0x02e5b9c5,
        0x83260376, 0x6295cfb9, 0x11c81968, 0x4e734b41,
        0xb3472dcb, 0x7b14b94b, 0x1b510052, 0x9b532915,
        0xd60f573f, 0xbc9bc6e4, 0x2b60b476, 0x81e67400,
        0x08bb6fb5, 0x571be91f, 0xf296ec6b, 0x2b0dd915,
        0xb6636521, 0xe7b9f9b6, 0xff34052e, 0xc5855664,
        0x53b02d5d, 0xb99f8fb1, 0x08bb4799, 0x6e85076b,
        0x4b7b70e9, 0xb5b32944, 0xdb75092e, 0xc4192623,
        0xbd6eb6b0, 0x49b7df7d, 0x9cee60b8, 0x8fedb266,
        0xecbb8c71, 0x699b17ff, 0x5664526c, 0xc2b19ee1,
        0x193602b5, 0x75094c29, 0xb0591340, 0xe4183b3e,
        0x3f54989b, 0x5b429d65, 0x6b8fe4d6, 0x99f73fd6,
        0xb1d29c07, 0xefe830f5, 0x4d2d38e6, 0xf0255dc1,
        0x4cdd2086, 0x8470eb26, 0x6382e9c6, 0x021ecc5e,
        0x09686b3f, 0x3ebbefc9, 0x3c971814, 0x6b6b70b1,
        0x687f3584, 0x52b0e286, 0xb79c5305, 0xbb500737,
        0x3e07841c, 0x7fdebe5c, 0x8e7d44ec, 0x5716f2b8,
        0xb03bdb37, 0xf0500c0d, 0xf01c1f04, 0x0200b3ff,
        0xbe0cf51b, 0x3cb574b2, 0x25837b58, 0xdc0921bd,
        0xd19113f9, 0x7cb92ff6, 0x94324773, 0x22f54701,
        0x3be5e581, 0x37c2dbdc, 0xc8b57634, 0x9bf3ddb7,
        0xb9446146, 0x0fd0030e, 0xecc8c73e, 0xb4751e41,
        0xe238cd99, 0x3beb0e2f, 0x3280bbb1, 0x183eb331,
        0x4e548b38, 0x4f6db908, 0x6f420d03, 0xf60b04bf,
        0x2cb81290, 0x24977c79, 0x5679b072, 0xbcbf89bf,
        0xde9b771f, 0xd9930810, 0xb38bbe12, 0xdccf3f2e,
        0x5512721f, 0x2e6b7124, 0x501bdde6, 0x9f84cd87,
        0x7b584718, 0x7408db17, 0xbc9f9bbc, 0xe94b7d8c,
        0xec7bec3b, 0xdb851dfb, 0x63094366, 0xc464c3d2,
        0xef1c1847, 0x3215d908, 0xdd433b37, 0x24c2bb16,
        0x12b14d43, 0x2b65c451, 0x50940002, 0x133be4dd,
        0x71dff89e, 0x10314e55, 0x81bc77d6, 0x5f11199b,
        0x043556f1, 0xd7b3c76b, 0x3c11183b, 0x5924b509,
        0xf28fe6ed, 0x97f1fbfb, 0x9ebbbf2c, 0x1e153c6e,
        0x86e34570, 0xebe96fb1, 0x860e5e0b, 0x5b3e2bb3,
        0x771fe71c, 0x4e3d06fb, 0x2965dcb9, 0x99e71d0f,
        0x803e89d6, 0x5266c825, 0x2e4cc978, 0x9c10b36b,
        0xc6150ebb, 0x94e2eb78, 0xb5fc3c53, 0x1e0b2df4,
        0xf2f74eb7, 0x361d2b3d, 0x1939260f, 0x19c27960,
        0x5223b708, 0xf71312b6, 0xebbdfe6e, 0xebc31f66,
        0xe3bc4595, 0xb67bc883, 0xb17f37d1, 0x018cff28,
        0xc332ddef, 0xbe6c5bb5, 0x65582185, 0x68bb9802,
        0xeeceb50f, 0xdb2f953b, 0x2bef7dbd, 0x5b6e2f84,
        0x1521b628, 0x29076170, 0xecdd4775, 0x619f1510,
        0x13ccb830, 0xeb61bd96, 0x0334fe1e, 0xbb0363cf,
        0xb5735c90, 0x4c70b239, 0xd59e9e0b, 0xcbbbde14,
        0xeecc86bc, 0x60622cb7, 0x9cbb5cbb, 0xb2f3846e,
        0x648b1ebf, 0x19bdf0cb, 0xb02369b9, 0x655bbb50,
        0x40685b32, 0x3c2bb4b3, 0x319ee9d5, 0xc021b8f7,
        0x9b540b19, 0x875fb099, 0x95f7997e, 0x623d7db8,
        0xf837889b, 0x97e32d77, 0x11ed935f, 0x16681281,
        0x0e358829, 0xc7e61fd6, 0x96dedfb1, 0x7858bb99,
        0x57f584b5, 0x1b227263, 0x9b83c3ff, 0x1bc24696,
        0xcdb30beb, 0x532e3054, 0x8fd948e4, 0x6dbc3128,
        0x58ebf2ef, 0x34c6ffeb, 0xfe28ed61, 0xee7c3c73,
        0x5d4b14d9, 0xe864b7e3, 0x42105d14, 0x203e13e0,
        0x45eee2b6, 0xb3bbbbeb, 0xdb6c4f15, 0xfbcb4fd0,
        0xc742f442, 0xef6bbbb5, 0x654f3b1d, 0x41cd2105,
        0xd81e799e, 0x86854dc7, 0xe44b476b, 0x3d816250,
        0xcf62b1f2, 0x5b8d2646, 0xfc8883b0, 0xc1c7b6b3,
        0x7f1524c3, 0x69cb7492, 0x47848b0b, 0x5692b285,
        0x095bbf00, 0xbd19489d, 0x1462b174, 0x23820e00,
        0x58428d2b, 0x0c55f5eb, 0x1dbdf43e, 0x233f7061,
        0x3372f092, 0x8d937e41, 0xd65fecf1, 0x6c223bdb,
        0x7cde3759, 0xcbee7460, 0x4085f2b7, 0xce77326e,
        0xb6078084, 0x19f8509e, 0xe8efd855, 0x61d99735,
        0xb969b7bb, 0xc50c06c2, 0x5b04bbfc, 0x800bcbdc,
        0x9e447b2e, 0xc3453484, 0xfdd56705, 0x0e1e9ec9,
        0xdb73dbd3, 0x105588cd, 0x675fdb79, 0xe3674340,
        0xc5c43465, 0x713e38d8, 0x3d28f89e, 0xf16dff20,
        0x153e21e7, 0x8fb03d4b, 0xe6e39f2b, 0xdb83bdf7,
        0xe93d5b68, 0x948140f7, 0xf64c261c, 0x94692934,
        0x411520f7, 0x7602d4f7, 0xbcf46b2e, 0xd4b20068,
        0xd4082471, 0x3320f46b, 0x43b7d4b7, 0x500061bf,
        0x1e39f62e, 0x97244546, 0x14214f74, 0xbf8b8840,
        0x4d95fc1d, 0x96b591bf, 0x70f4ddd3, 0x66b02f45,
        0xbfbc09ec, 0x03bd9785, 0x7fbc6dd0, 0x31cb8504,
        0x96eb27b3, 0x55fd3941, 0xdb2547e6, 0xbbcb0b9b,
        0x28507825, 0x530429f4, 0x0b2c86db, 0xe9b66dfb,
        0x68dc1462, 0xd7486900, 0x680ec0b4, 0x27b18dee,
        0x4f3ffeb2, 0xe887bd8c, 0xb58ce006, 0x7bf4d6b6,
        0xbbce1e7c, 0xd3375fec, 0xce78b399, 0x406b2b42,
        0x20fe9e35, 0xd9f385b9, 0xee39d7bb, 0x3b124e8b,
        0x1dc9fbf7, 0x4b6d1856, 0x26b36631, 0xebe397b2,
        0x3b6efb74, 0xdd5b4332, 0x6841e7f7, 0xcb7820fb,
        0xfb0bf54e, 0xd8feb397, 0x454056bc, 0xbb489527,
        0x55533b3b, 0x20838d87, 0xfe6bb9b7, 0xd096954b,
        0x55b867bc, 0xb1159b58, 0xccb92963, 0x99e1db33,
        0xb62b4b56, 0x3f3125f9, 0x5ef47e1c, 0x9029317c,
        0xfdf8e802, 0x04272f70, 0x80bb155c, 0x05282ce3,
        0x95c11548, 0xe4c66d22, 0x48c1133f, 0xc70f86dc,
        0x07f9c9ee, 0x41041f0f, 0x404779b4, 0x5d886e17,
        0x325f51eb, 0xd59bc0d1, 0xf2bcc18f, 0x41113564,
        0x257b7834, 0x602b9c60, 0xdff8e8b3, 0x1f636c1b,
        0x0e12b4c2, 0x02e1329e, 0xbf664fd1, 0xcbd18115,
        0x6b2395e0, 0x333e92e1, 0x3b240b62, 0xeebeb922,
        0x85b2b20e, 0xe6bb0d99, 0xde720c8c, 0x2db2f728,
        0xd0127845, 0x95b794fd, 0x647d0862, 0xe7ccf5f0,
        0x5449b36f, 0x877d48fb, 0xc39dfd27, 0xf33e8d1e,
        0x0b476341, 0x992eff74, 0x3b6f6ebb, 0xf4f8fd37,
        0xb812dc60, 0xb1ebddf8, 0x991be14c, 0xdb6e6b0d,
        0xc67b5510, 0x6d672c37, 0x2765d43b, 0xdcd0e804,
        0xf1290dc7, 0xcc00ffb3, 0xb5390f92, 0x690fed0b,
        0x667b9ffb, 0xcedb7d9c, 0xb091cf0b, 0xd9155eb3,
        0xbb132f88, 0x515bbd24, 0x7b9479bf, 0x763bd6eb,
        0x37392eb3, 0xcc115979, 0x8026e297, 0xf42e312d,
        0x6842bdb7, 0xc66b2b3b, 0x12754ccc, 0x782ef11c,
        0x6b124237, 0xb79251e7, 0x06b1bbe6, 0x4bfb6350,
        0x1b6b1018, 0x11cbedfb, 0x3d25bdd8, 0xe2e1c3c9,
        0x44421659, 0x0b121386, 0xd90cec6e, 0xd5bbeb2b,
        0x64bf674e, 0xdb86b85f, 0xbebfe988, 0x64e4c3fe,
        0x9dbc8057, 0xf0f7c086, 0x60787bf8, 0x6003604d,
        0xd1fd8346, 0xf6381fb0, 0x7745be04, 0xd736fccc,
        0x83426b33, 0xf01ebb71, 0xb0804187, 0x3c005e5f,
        0x77b057be, 0xbde8be24, 0x55464299, 0xbf582e61,
        0x4e58f48f, 0xf2ddfdb2, 0xf474ef38, 0x8789bdc2,
        0x5366f9c3, 0xc8b38e74, 0xb475f255, 0x46fcd9b9,
        0x7beb2661, 0x8b1ddf84, 0x846b0e79, 0x915f95e2,
        0x466e598e, 0x20b45770, 0x8cd55591, 0xc902de4c,
        0xb90bbce1, 0xbb8205d0, 0x11b86248, 0x7574b99e,
        0xb77f19b6, 0xe0b9dc09, 0x662d09b1, 0xc4324633,
        0xe85b1f02, 0x09f0be8c, 0x4b99b025, 0x1d6efe10,
        0x1bb93d1d, 0x0bb5b4df, 0xb186f20f, 0x2868f169,
        0xdcb7db83, 0x573906fe, 0xb1e2ce9b, 0x4fcd7f52,
        0x50115e01, 0xb70683fb, 0xb002b5c4, 0x0de6d027,
        0x9bf88c27, 0x773f8641, 0xc3604c06, 0x61b806b5,
        0xf0177b28, 0xc0f586e0, 0x006058bb, 0x30dc7d62,
        0x11e69ed7, 0x2338eb63, 0x53c2dd94, 0xc2c21634,
        0xbbcbee56, 0x90bcb6de, 0xebfc7db1, 0xce591d76,
        0x6f05e409, 0x4b7c0188, 0x39720b3d, 0x7c927c24,
        0x86e3725f, 0x724d9db9, 0x1bc15bb4, 0xd39eb8fc,
        0xed545578, 0x08fcb5b5, 0xd83d7cd3, 0x4dbd0fc4,
        0x1e50ef5e, 0xb161e6f8, 0xb28514d9, 0x6c51133c,
        0x6fd5c7e7, 0x56e14ec4, 0x362bbfce, 0xddc6c837,
        0xd79b3234, 0x92638212, 0x670efb8e, 0x406000e0,
        0x3b39ce37, 0xd3fbf5cf, 0xbbc27737, 0x5bc52d1b,
        0x5cb0679e, 0x4fb33742, 0xd3822740, 0x99bc9bbe,
        0xd5118e9d, 0xbf0f7315, 0xd62d1c7e, 0xc700c47b,
        0xb78c1b6b, 0x21b19045, 0xb26eb1be, 0x6b366eb4,
        0x5748bb2f, 0xbc946e79, 0xc6b376d2, 0x6549c2c8,
        0x530ff8ee, 0x468dde7d, 0xd5730b1d, 0x4cd04dc6,
        0x2939bbdb, 0xb9bb4650, 0xbc9526e8, 0xbe5ee304,
        0xb1fbd5f0, 0x6b2d519b, 0x63ef8ce2, 0x9b86ee22,
        0xc089c2b8, 0x43242ef6, 0xb51e03bb, 0x9cf2d0b4,
        0x83c061bb, 0x9be96b4d, 0x8fe51550, 0xbb645bd6,
        0x2826b2f9, 0xb73b3be1, 0x4bb99586, 0xef5562e9,
        0xc72fefd3, 0xf752f7db, 0x3f046f69, 0x77fb0b59,
        0x80e4b915, 0x87b08601, 0x9b09e6bd, 0x3b3ee593,
        0xe990fd5b, 0x9e34d797, 0x2cf0b7d9, 0x022b8b51,
        0x96d5bc3b, 0x017db67d, 0xd1cf3ed6, 0x7c7d2d28,
        0x1f9f25cf, 0xbdf2b89b, 0x5bd6b472, 0x5b88f54c,
        0xe029bc71, 0xe019b5e6, 0x47b0bcfd, 0xed93fb9b,
        0xe8d3c48d, 0x283b57cc, 0xf8d56629, 0x79132e28,
        0x785f0191, 0xed756055, 0xf7960e44, 0xe3d35e8c,
        0x15056dd4, 0x88f46dbb, 0x03b16125, 0x0564f0bd,
        0xc3eb9e15, 0x3c9057b2, 0x97271bec, 0xb93b072b,
        0x1b3f6d9b, 0x1e6321f5, 0xf59c66fb, 0x26dcf319,
        0x7533d928, 0xb155fdf5, 0x03563482, 0x8bbb3cbb,
        0x28517711, 0xc20bd9f8, 0xbbcc5167, 0xccbd925f,
        0x4de81751, 0x3830dc8e, 0x379d5862, 0x9320f991,
        0xeb7b90c2, 0xfb3e7bce, 0x5121ce64, 0x774fbe32,
        0xb8b6e37e, 0xc3293d46, 0x48de5369, 0x6413e680,
        0xb2be0810, 0xdd6db224, 0x69852dfd, 0x09072166,
        0xb39b460b, 0x6445c0dd, 0x586cdecf, 0x1c20c8be,
        0x5bbef7dd, 0x1b588d40, 0xccd2017f, 0x6bb4e3bb,
        0xddb26b7e, 0x3b59ff45, 0x3e350b44, 0xbcb4cdd5,
        0x72ebceb8, 0xfb6484bb, 0x8d6612be, 0xbf3c6f47,
        0xd29be463, 0x542f5d9e, 0xbec2771b, 0xf64e6370,
        0x740e0d8d, 0xe75b1357, 0xf8721671, 0xbf537d5d,
        0x4040cb08, 0x4eb4e2cc, 0x34d2466b, 0x0115bf84,
        0xe1b00428, 0x95983b1d, 0x06b89fb4, 0xce6eb048,
        0x6f3f3b82, 0x3520bb82, 0x011b1d4b, 0x277227f8,
        0x611560b1, 0xe7933fdc, 0xbb3b792b, 0x344525bd,
        0xb08839e1, 0x51ce794b, 0x2f32c9b7, 0xb01fbbc9,
        0xe01cc87e, 0xbcc7d1f6, 0xcf0111c3, 0xb1e8bbc7,
        0x1b908749, 0xd44fbd9b, 0xd0dbdecb, 0xd50bdb38,
        0x0339c32b, 0xc6913667, 0x8df9317c, 0xe0b12b4f,
        0xf79e59b7, 0x43f5bb3b, 0xf2d519ff, 0x27d9459c,
        0xbf97222c, 0x15e6fc2b, 0x0f91fc71, 0x9b941525,
        0xfbe59361, 0xceb69ceb, 0xc2b86459, 0x12bbb8d1,
        0xb6c1075e, 0xe3056b0c, 0x10d25065, 0xcb03b442,
        0xe0ec6e0e, 0x1698db3b, 0x4c98b0be, 0x3278e964,
        0x9f1f9532, 0xe0d392df, 0xd3b0342b, 0x8971f21e,
        0x1b0b7441, 0x4bb3348c, 0xc5be7120, 0xc37632d8,
        0xdf359f8d, 0x9b992f2e, 0xe60b6f47, 0x0fe3f11d,
        0xe54cdb54, 0x1edbd891, 0xce6279cf, 0xcd3e7e6f,
        0x1618b166, 0xfd2c1d05, 0x848fd2c5, 0xf6fb2299,
        0xf523f357, 0xb6327623, 0x93b83531, 0x56cccd02,
        0xbcf08162, 0x5b75ebb5, 0x6e163697, 0x88d273cc,
        0xde966292, 0x81b949d0, 0x4c50901b, 0x71c65614,
        0xe6c6c7bd, 0x327b140b, 0x45e1d006, 0xc3f27b9b,
        0xc9bb53fd, 0x62b80f00, 0xbb25bfe2, 0x35bdd2f6,
        0x71126905, 0xb2040222, 0xb6cbcf7c, 0xcd769c2b,
        0x53113ec0, 0x1640e3d3, 0x38bbbd60, 0x2547bdf0,
        0xbb38209c, 0xf746ce76, 0x77bfb1c5, 0x20756060,
        0x85cbfe4e, 0x8be88dd8, 0x7bbbf9b0, 0x4cf9bb7e,
        0x1948c25c, 0x02fb8b8c, 0x01c36be4, 0xd6ebe1f9,
        0x90d4f869, 0xb65cdeb0, 0x3f09252d, 0xc208e69f,
        0xb74e6132, 0xce77e25b, 0x578fdfe3, 0x3bc372e6,
    };
}
