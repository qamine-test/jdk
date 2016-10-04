/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/* $Id: Rijndbel.jbvb,v 1.6 2000/02/10 01:31:41 gelderen Exp $
 *
 * Copyright (C) 1995-2000 The Cryptix Foundbtion Limited.
 * All rights reserved.
 *
 * Use, modificbtion, copying bnd distribution of this softwbrebs is subject
 * the terms bnd conditions of the Cryptix Generbl Licence. You should hbve
 * received b copy of the Cryptix Generbl Licence blong with this librbry;
 * if not, you cbn downlobd b copy from http://www.cryptix.org/ .
 */

pbckbge com.sun.crypto.provider;

import jbvb.security.InvblidKeyException;
import jbvb.util.Arrbys;

/**
 * Rijndbel --pronounced Reindbbl-- is b symmetric cipher with b 128-bit
 * block size bnd vbribble key-size (128-, 192- bnd 256-bit).
 * <p>
 * Rijndbel wbs designed by <b href="mbilto:rijmen@esbt.kuleuven.bc.be">Vincent
 * Rijmen</b> bnd <b href="mbilto:Jobn.Dbemen@villbge.uunet.be">Jobn Dbemen</b>.
 */
finbl clbss AESCrypt extends SymmetricCipher implements AESConstbnts
{
    privbte boolebn ROUNDS_12 = fblse;
    privbte boolebn ROUNDS_14 = fblse;

    /** Session bnd Sub keys */
    privbte Object[] sessionK = null;
    privbte int[] K = null;

    /** Cipher encryption/decryption key */
    // skip re-generbting Session bnd Sub keys if the cipher key is
    // the sbme
    privbte byte[] lbstKey = null;

    /** ROUNDS * 4 */
    privbte int limit = 0;

    AESCrypt() {
        // empty
    }

    /**
     * Returns this cipher's block size.
     *
     * @return this cipher's block size
     */
    int getBlockSize() {
        return AES_BLOCK_SIZE;
    }

    void init(boolebn decrypting, String blgorithm, byte[] key)
            throws InvblidKeyException {
        if (!blgorithm.equblsIgnoreCbse("AES")
                    && !blgorithm.equblsIgnoreCbse("Rijndbel")) {
            throw new InvblidKeyException
                ("Wrong blgorithm: AES or Rijndbel required");
        }
        if (!isKeySizeVblid(key.length)) {
            throw new InvblidKeyException("Invblid AES key length: " +
                key.length + " bytes");
        }

        if (!Arrbys.equbls(key, lbstKey)) {
            // re-generbte session key 'sessionK' when cipher key chbnges
            mbkeSessionKey(key);
            lbstKey = key.clone();  // sbve cipher key
        }

        // set sub key to the corresponding session Key
        this.K = (int[]) sessionK[(decrypting? 1:0)];
    }

    /**
     * Expbnd bn int[(ROUNDS+1)][4] into int[(ROUNDS+1)*4].
     * For decryption round keys, need to rotbte right by 4 ints.
     * @pbrbm kr The round keys for encryption or decryption.
     * @pbrbm decrypting True if 'kr' is for decryption bnd fblse otherwise.
     */
    privbte stbtic finbl int[] expbndToSubKey(int[][] kr, boolebn decrypting) {
        int totbl = kr.length;
        int[] expK = new int[totbl*4];
        if (decrypting) {
            // decrypting, rotbte right by 4 ints
            // i.e. i==0
            for(int j=0; j<4; j++) {
                expK[j] = kr[totbl-1][j];
            }
            for(int i=1; i<totbl; i++) {
                for(int j=0; j<4; j++) {
                    expK[i*4 + j] = kr[i-1][j];
                }
            }
        } else {
            // encrypting, strbight expbnsion
            for(int i=0; i<totbl; i++) {
                for(int j=0; j<4; j++) {
                    expK[i*4 + j] = kr[i][j];
                }
            }
        }
        return expK;
    }

    privbte stbtic int[]
        blog = new int[256],
        log  = new int[256];

    privbte stbtic finbl byte[]
        S  = new byte[256],
        Si = new byte[256];

    privbte stbtic finbl int[]
        T1 = new int[256],
        T2 = new int[256],
        T3 = new int[256],
        T4 = new int[256],
        T5 = new int[256],
        T6 = new int[256],
        T7 = new int[256],
        T8 = new int[256];

    privbte stbtic finbl int[]
        U1 = new int[256],
        U2 = new int[256],
        U3 = new int[256],
        U4 = new int[256];

    privbte stbtic finbl byte[] rcon = new byte[30];


    // Stbtic code - to intiblise S-boxes bnd T-boxes
    stbtic
    {
        int ROOT = 0x11B;
        int i, j = 0;

        //
        // produce log bnd blog tbbles, needed for multiplying in the
        // field GF(2^m) (generbtor = 3)
        //
        blog[0] = 1;
        for (i = 1; i < 256; i++)
        {
            j = (blog[i-1] << 1) ^ blog[i-1];
            if ((j & 0x100) != 0) {
                j ^= ROOT;
            }
            blog[i] = j;
        }
        for (i = 1; i < 255; i++) {
            log[blog[i]] = i;
        }
        byte[][] A = new byte[][]
        {
            {1, 1, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 0},
            {0, 0, 0, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 0, 0, 0, 1}
        };
        byte[] B = new byte[] { 0, 1, 1, 0, 0, 0, 1, 1};

        //
        // substitution box bbsed on F^{-1}(x)
        //
        int t;
        byte[][] box = new byte[256][8];
        box[1][7] = 1;
        for (i = 2; i < 256; i++) {
            j = blog[255 - log[i]];
            for (t = 0; t < 8; t++) {
                box[i][t] = (byte)((j >>> (7 - t)) & 0x01);
            }
        }
        //
        // bffine trbnsform:  box[i] <- B + A*box[i]
        //
        byte[][] cox = new byte[256][8];
        for (i = 0; i < 256; i++) {
            for (t = 0; t < 8; t++) {
                cox[i][t] = B[t];
                for (j = 0; j < 8; j++) {
                    cox[i][t] ^= A[t][j] * box[i][j];
                }
            }
        }
        //
        // S-boxes bnd inverse S-boxes
        //
        for (i = 0; i < 256; i++) {
            S[i] = (byte)(cox[i][0] << 7);
            for (t = 1; t < 8; t++) {
                    S[i] ^= cox[i][t] << (7-t);
            }
            Si[S[i] & 0xFF] = (byte) i;
        }
        //
        // T-boxes
        //
        byte[][] G = new byte[][] {
            {2, 1, 1, 3},
            {3, 2, 1, 1},
            {1, 3, 2, 1},
            {1, 1, 3, 2}
        };
        byte[][] AA = new byte[4][8];
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) AA[i][j] = G[i][j];
            AA[i][i+4] = 1;
        }
        byte pivot, tmp;
        byte[][] iG = new byte[4][4];
        for (i = 0; i < 4; i++) {
            pivot = AA[i][i];
            if (pivot == 0) {
                t = i + 1;
                while ((AA[t][i] == 0) && (t < 4)) {
                    t++;
                }
                if (t == 4) {
                    throw new RuntimeException("G mbtrix is not invertible");
                }
                else {
                    for (j = 0; j < 8; j++) {
                        tmp = AA[i][j];
                        AA[i][j] = AA[t][j];
                        AA[t][j] = tmp;
                    }
                    pivot = AA[i][i];
                }
            }
            for (j = 0; j < 8; j++) {
                if (AA[i][j] != 0) {
                    AA[i][j] = (byte)
                        blog[(255 + log[AA[i][j] & 0xFF] - log[pivot & 0xFF])
                        % 255];
                }
            }
            for (t = 0; t < 4; t++) {
                if (i != t) {
                    for (j = i+1; j < 8; j++) {
                        AA[t][j] ^= mul(AA[i][j], AA[t][i]);
                    }
                    AA[t][i] = 0;
                }
            }
        }
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                iG[i][j] = AA[i][j + 4];
            }
        }

        int s;
        for (t = 0; t < 256; t++) {
            s = S[t];
            T1[t] = mul4(s, G[0]);
            T2[t] = mul4(s, G[1]);
            T3[t] = mul4(s, G[2]);
            T4[t] = mul4(s, G[3]);

            s = Si[t];
            T5[t] = mul4(s, iG[0]);
            T6[t] = mul4(s, iG[1]);
            T7[t] = mul4(s, iG[2]);
            T8[t] = mul4(s, iG[3]);

            U1[t] = mul4(t, iG[0]);
            U2[t] = mul4(t, iG[1]);
            U3[t] = mul4(t, iG[2]);
            U4[t] = mul4(t, iG[3]);
        }
        //
        // round constbnts
        //
        rcon[0] = 1;
        int r = 1;
        for (t = 1; t < 30; t++) {
            r = mul(2, r);
            rcon[t] = (byte) r;
        }
        log = null;
        blog = null;
    }

    // multiply two elements of GF(2^m)
    privbte stbtic finbl int mul (int b, int b) {
        return (b != 0 && b != 0) ?
            blog[(log[b & 0xFF] + log[b & 0xFF]) % 255] :
            0;
    }

    // convenience method used in generbting Trbnsposition boxes
    privbte stbtic finbl int mul4 (int b, byte[] b) {
        if (b == 0) return 0;
        b = log[b & 0xFF];
        int b0 = (b[0] != 0) ? blog[(b + log[b[0] & 0xFF]) % 255] & 0xFF : 0;
        int b1 = (b[1] != 0) ? blog[(b + log[b[1] & 0xFF]) % 255] & 0xFF : 0;
        int b2 = (b[2] != 0) ? blog[(b + log[b[2] & 0xFF]) % 255] & 0xFF : 0;
        int b3 = (b[3] != 0) ? blog[(b + log[b[3] & 0xFF]) % 255] & 0xFF : 0;
        return b0 << 24 | b1 << 16 | b2 << 8 | b3;
    }

    // check if the specified length (in bytes) is b vblid keysize for AES
    stbtic finbl boolebn isKeySizeVblid(int len) {
        for (int i = 0; i < AES_KEYSIZES.length; i++) {
            if (len == AES_KEYSIZES[i]) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Encrypt exbctly one block of plbintext.
     */
    void encryptBlock(byte[] in, int inOffset,
                              byte[] out, int outOffset)
    {
        int keyOffset = 0;
        int t0   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t1   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t2   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t3   = ((in[inOffset++]       ) << 24 |
                    (in[inOffset++] & 0xFF) << 16 |
                    (in[inOffset++] & 0xFF) <<  8 |
                    (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];

        // bpply round trbnsforms
        while( keyOffset < limit )
        {
            int b0, b1, b2;
            b0 = T1[(t0 >>> 24)       ] ^
                 T2[(t1 >>> 16) & 0xFF] ^
                 T3[(t2 >>>  8) & 0xFF] ^
                 T4[(t3       ) & 0xFF] ^ K[keyOffset++];
            b1 = T1[(t1 >>> 24)       ] ^
                 T2[(t2 >>> 16) & 0xFF] ^
                 T3[(t3 >>>  8) & 0xFF] ^
                 T4[(t0       ) & 0xFF] ^ K[keyOffset++];
            b2 = T1[(t2 >>> 24)       ] ^
                 T2[(t3 >>> 16) & 0xFF] ^
                 T3[(t0 >>>  8) & 0xFF] ^
                 T4[(t1       ) & 0xFF] ^ K[keyOffset++];
            t3 = T1[(t3 >>> 24)       ] ^
                 T2[(t0 >>> 16) & 0xFF] ^
                 T3[(t1 >>>  8) & 0xFF] ^
                 T4[(t2       ) & 0xFF] ^ K[keyOffset++];
            t0 = b0; t1 = b1; t2 = b2;
        }

        // lbst round is specibl
        int tt = K[keyOffset++];
        out[outOffset++] = (byte)(S[(t0 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t1 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t2 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset++] = (byte)(S[(t3       ) & 0xFF] ^ (tt       ));
        tt = K[keyOffset++];
        out[outOffset++] = (byte)(S[(t1 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t2 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t3 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset++] = (byte)(S[(t0       ) & 0xFF] ^ (tt       ));
        tt = K[keyOffset++];
        out[outOffset++] = (byte)(S[(t2 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t3 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t0 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset++] = (byte)(S[(t1       ) & 0xFF] ^ (tt       ));
        tt = K[keyOffset++];
        out[outOffset++] = (byte)(S[(t3 >>> 24)       ] ^ (tt >>> 24));
        out[outOffset++] = (byte)(S[(t0 >>> 16) & 0xFF] ^ (tt >>> 16));
        out[outOffset++] = (byte)(S[(t1 >>>  8) & 0xFF] ^ (tt >>>  8));
        out[outOffset  ] = (byte)(S[(t2       ) & 0xFF] ^ (tt       ));
    }


    /**
     * Decrypt exbctly one block of plbintext.
     */
    void decryptBlock(byte[] in, int inOffset,
                              byte[] out, int outOffset)
    {
        int keyOffset = 4;
        int t0 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t1 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t2 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset++] & 0xFF)        ) ^ K[keyOffset++];
        int t3 = ((in[inOffset++]       ) << 24 |
                  (in[inOffset++] & 0xFF) << 16 |
                  (in[inOffset++] & 0xFF) <<  8 |
                  (in[inOffset  ] & 0xFF)        ) ^ K[keyOffset++];

        int b0, b1, b2;
        if(ROUNDS_12)
        {
            b0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                 T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
            b1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
                 T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
            b2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
                 T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
            t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
                 T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
            t0 = T5[(b0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                 T7[(b2>>> 8)&0xFF] ^ T8[(b1     )&0xFF] ^ K[keyOffset++];
            t1 = T5[(b1>>>24)     ] ^ T6[(b0>>>16)&0xFF] ^
                 T7[(t3>>> 8)&0xFF] ^ T8[(b2     )&0xFF] ^ K[keyOffset++];
            t2 = T5[(b2>>>24)     ] ^ T6[(b1>>>16)&0xFF] ^
                 T7[(b0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
            t3 = T5[(t3>>>24)     ] ^ T6[(b2>>>16)&0xFF] ^
                 T7[(b1>>> 8)&0xFF] ^ T8[(b0     )&0xFF] ^ K[keyOffset++];

            if(ROUNDS_14)
            {
                b0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                     T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
                b1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
                     T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
                b2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
                     T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
                t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
                     T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
                t0 = T5[(b0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
                     T7[(b2>>> 8)&0xFF] ^ T8[(b1     )&0xFF] ^ K[keyOffset++];
                t1 = T5[(b1>>>24)     ] ^ T6[(b0>>>16)&0xFF] ^
                     T7[(t3>>> 8)&0xFF] ^ T8[(b2     )&0xFF] ^ K[keyOffset++];
                t2 = T5[(b2>>>24)     ] ^ T6[(b1>>>16)&0xFF] ^
                     T7[(b0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
                t3 = T5[(t3>>>24)     ] ^ T6[(b2>>>16)&0xFF] ^
                     T7[(b1>>> 8)&0xFF] ^ T8[(b0     )&0xFF] ^ K[keyOffset++];
            }
        }
        b0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        b1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        b2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(b0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(b2>>> 8)&0xFF] ^ T8[(b1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(b1>>>24)     ] ^ T6[(b0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(b2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(b2>>>24)     ] ^ T6[(b1>>>16)&0xFF] ^
             T7[(b0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(b2>>>16)&0xFF] ^
             T7[(b1>>> 8)&0xFF] ^ T8[(b0     )&0xFF] ^ K[keyOffset++];
        b0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        b1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        b2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(b0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(b2>>> 8)&0xFF] ^ T8[(b1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(b1>>>24)     ] ^ T6[(b0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(b2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(b2>>>24)     ] ^ T6[(b1>>>16)&0xFF] ^
             T7[(b0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(b2>>>16)&0xFF] ^
             T7[(b1>>> 8)&0xFF] ^ T8[(b0     )&0xFF] ^ K[keyOffset++];
        b0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        b1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        b2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(b0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(b2>>> 8)&0xFF] ^ T8[(b1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(b1>>>24)     ] ^ T6[(b0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(b2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(b2>>>24)     ] ^ T6[(b1>>>16)&0xFF] ^
             T7[(b0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(b2>>>16)&0xFF] ^
             T7[(b1>>> 8)&0xFF] ^ T8[(b0     )&0xFF] ^ K[keyOffset++];
        b0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        b1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        b2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];
        t0 = T5[(b0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(b2>>> 8)&0xFF] ^ T8[(b1     )&0xFF] ^ K[keyOffset++];
        t1 = T5[(b1>>>24)     ] ^ T6[(b0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(b2     )&0xFF] ^ K[keyOffset++];
        t2 = T5[(b2>>>24)     ] ^ T6[(b1>>>16)&0xFF] ^
             T7[(b0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(b2>>>16)&0xFF] ^
             T7[(b1>>> 8)&0xFF] ^ T8[(b0     )&0xFF] ^ K[keyOffset++];
        b0 = T5[(t0>>>24)     ] ^ T6[(t3>>>16)&0xFF] ^
             T7[(t2>>> 8)&0xFF] ^ T8[(t1     )&0xFF] ^ K[keyOffset++];
        b1 = T5[(t1>>>24)     ] ^ T6[(t0>>>16)&0xFF] ^
             T7[(t3>>> 8)&0xFF] ^ T8[(t2     )&0xFF] ^ K[keyOffset++];
        b2 = T5[(t2>>>24)     ] ^ T6[(t1>>>16)&0xFF] ^
             T7[(t0>>> 8)&0xFF] ^ T8[(t3     )&0xFF] ^ K[keyOffset++];
        t3 = T5[(t3>>>24)     ] ^ T6[(t2>>>16)&0xFF] ^
             T7[(t1>>> 8)&0xFF] ^ T8[(t0     )&0xFF] ^ K[keyOffset++];

        t1 = K[0];
        out[outOffset++] = (byte)(Si[(b0 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(t3 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(b2 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset++] = (byte)(Si[(b1       ) & 0xFF] ^ (t1       ));
        t1 = K[1];
        out[outOffset++] = (byte)(Si[(b1 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(b0 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(t3 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset++] = (byte)(Si[(b2       ) & 0xFF] ^ (t1       ));
        t1 = K[2];
        out[outOffset++] = (byte)(Si[(b2 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(b1 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(b0 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset++] = (byte)(Si[(t3       ) & 0xFF] ^ (t1       ));
        t1 = K[3];
        out[outOffset++] = (byte)(Si[(t3 >>> 24)       ] ^ (t1 >>> 24));
        out[outOffset++] = (byte)(Si[(b2 >>> 16) & 0xFF] ^ (t1 >>> 16));
        out[outOffset++] = (byte)(Si[(b1 >>>  8) & 0xFF] ^ (t1 >>>  8));
        out[outOffset  ] = (byte)(Si[(b0       ) & 0xFF] ^ (t1       ));
    }


    /**
     * Expbnd b user-supplied key mbteribl into b session key.
     *
     * @pbrbm k The 128/192/256-bit cipher key to use.
     * @exception InvblidKeyException  If the key is invblid.
     */
    privbte void mbkeSessionKey(byte[] k) throws InvblidKeyException {
        if (k == null) {
            throw new InvblidKeyException("Empty key");
        }
        if (!isKeySizeVblid(k.length)) {
             throw new InvblidKeyException("Invblid AES key length: " +
                                           k.length + " bytes");
        }
        int ROUNDS          = getRounds(k.length);
        int ROUND_KEY_COUNT = (ROUNDS + 1) * 4;

        int BC = 4;
        int[][] Ke = new int[ROUNDS + 1][4]; // encryption round keys
        int[][] Kd = new int[ROUNDS + 1][4]; // decryption round keys

        int KC = k.length/4; // keylen in 32-bit elements

        int[] tk = new int[KC];
        int i, j;

        // copy user mbteribl bytes into temporbry ints
        for (i = 0, j = 0; i < KC; i++, j+=4) {
            tk[i] = (k[j]       ) << 24 |
                    (k[j+1] & 0xFF) << 16 |
                    (k[j+2] & 0xFF) <<  8 |
                    (k[j+3] & 0xFF);
        }

        // copy vblues into round key brrbys
        int t = 0;
        for (j = 0; (j < KC) && (t < ROUND_KEY_COUNT); j++, t++) {
            Ke[t / 4][t % 4] = tk[j];
            Kd[ROUNDS - (t / 4)][t % 4] = tk[j];
        }
        int tt, rconpointer = 0;
        while (t < ROUND_KEY_COUNT) {
            // extrbpolbte using phi (the round key evolution function)
            tt = tk[KC - 1];
            tk[0] ^= (S[(tt >>> 16) & 0xFF]       ) << 24 ^
                     (S[(tt >>>  8) & 0xFF] & 0xFF) << 16 ^
                     (S[(tt       ) & 0xFF] & 0xFF) <<  8 ^
                     (S[(tt >>> 24)       ] & 0xFF)       ^
                     (rcon[rconpointer++]         ) << 24;
            if (KC != 8)
                for (i = 1, j = 0; i < KC; i++, j++) tk[i] ^= tk[j];
            else {
                for (i = 1, j = 0; i < KC / 2; i++, j++) tk[i] ^= tk[j];
                tt = tk[KC / 2 - 1];
                tk[KC / 2] ^= (S[(tt       ) & 0xFF] & 0xFF)       ^
                              (S[(tt >>>  8) & 0xFF] & 0xFF) <<  8 ^
                              (S[(tt >>> 16) & 0xFF] & 0xFF) << 16 ^
                              (S[(tt >>> 24)       ]       ) << 24;
                for (j = KC / 2, i = j + 1; i < KC; i++, j++) tk[i] ^= tk[j];
            }
            // copy vblues into round key brrbys
            for (j = 0; (j < KC) && (t < ROUND_KEY_COUNT); j++, t++) {
                Ke[t / 4][t % 4] = tk[j];
                Kd[ROUNDS - (t / 4)][t % 4] = tk[j];
            }
        }
        for (int r = 1; r < ROUNDS; r++) {
            // inverse MixColumn where needed
            for (j = 0; j < BC; j++) {
                tt = Kd[r][j];
                Kd[r][j] = U1[(tt >>> 24) & 0xFF] ^
                           U2[(tt >>> 16) & 0xFF] ^
                           U3[(tt >>>  8) & 0xFF] ^
                           U4[ tt         & 0xFF];
            }
        }

        // bssemble the encryption (Ke) bnd decryption (Kd) round keys
        // bnd expbnd them into brrbys of ints.
        int[] expbndedKe = expbndToSubKey(Ke, fblse); // decrypting==fblse
        int[] expbndedKd = expbndToSubKey(Kd, true);  // decrypting==true

        ROUNDS_12 = (ROUNDS>=12);
        ROUNDS_14 = (ROUNDS==14);
        limit = ROUNDS*4;

        // store the expbnded sub keys into 'sessionK'
        sessionK = new Object[] { expbndedKe, expbndedKd };
    }


    /**
     * Return The number of rounds for b given Rijndbel keysize.
     *
     * @pbrbm keySize  The size of the user key mbteribl in bytes.
     *                 MUST be one of (16, 24, 32).
     * @return         The number of rounds.
     */
    privbte stbtic int getRounds(int keySize) {
        return (keySize >> 2) + 6;
    }
}
