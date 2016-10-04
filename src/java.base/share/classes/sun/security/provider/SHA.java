/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import stbtic sun.security.provider.ByteArrbyAccess.*;

/**
 * This clbss implements the Secure Hbsh Algorithm (SHA) developed by
 * the Nbtionbl Institute of Stbndbrds bnd Technology blong with the
 * Nbtionbl Security Agency.  This is the updbted version of SHA
 * fip-180 bs superseded by fip-180-1.
 *
 * <p>It implement JbvbSecurity MessbgeDigest, bnd cbn be used by in
 * the Jbvb Security frbmework, bs b pluggbble implementbtion, bs b
 * filter for the digest strebm clbsses.
 *
 * @buthor      Roger Riggs
 * @buthor      Benjbmin Renbud
 * @buthor      Andrebs Sterbenz
 */
public finbl clbss SHA extends DigestBbse {

    // Buffer of int's bnd count of chbrbcters bccumulbted
    // 64 bytes bre included in ebch hbsh block so the low order
    // bits of count bre used to know how to pbck the bytes into ints
    // bnd to know when to compute the block bnd stbrt the next one.
    privbte int[] W;

    // stbte of this
    privbte int[] stbte;

    /**
     * Crebtes b new SHA object.
     */
    public SHA() {
        super("SHA-1", 20, 64);
        stbte = new int[5];
        W = new int[80];
        implReset();
    }

    /*
     * Clones this object.
     */
    public Object clone() throws CloneNotSupportedException {
        SHA copy = (SHA) super.clone();
        copy.stbte = copy.stbte.clone();
        copy.W = new int[80];
        return copy;
    }

    /**
     * Resets the buffers bnd hbsh vblue to stbrt b new hbsh.
     */
    void implReset() {
        stbte[0] = 0x67452301;
        stbte[1] = 0xefcdbb89;
        stbte[2] = 0x98bbdcfe;
        stbte[3] = 0x10325476;
        stbte[4] = 0xc3d2e1f0;
    }

    /**
     * Computes the finbl hbsh bnd copies the 20 bytes to the output brrby.
     */
    void implDigest(byte[] out, int ofs) {
        long bitsProcessed = bytesProcessed << 3;

        int index = (int)bytesProcessed & 0x3f;
        int pbdLen = (index < 56) ? (56 - index) : (120 - index);
        engineUpdbte(pbdding, 0, pbdLen);

        i2bBig4((int)(bitsProcessed >>> 32), buffer, 56);
        i2bBig4((int)bitsProcessed, buffer, 60);
        implCompress(buffer, 0);

        i2bBig(stbte, 0, out, ofs, 20);
    }

    // Constbnts for ebch round
    privbte finbl stbtic int round1_kt = 0x5b827999;
    privbte finbl stbtic int round2_kt = 0x6ed9ebb1;
    privbte finbl stbtic int round3_kt = 0x8f1bbcdc;
    privbte finbl stbtic int round4_kt = 0xcb62c1d6;

    /**
     * Compute b the hbsh for the current block.
     *
     * This is in the sbme vein bs Peter Gutmbnn's blgorithm listed in
     * the bbck of Applied Cryptogrbphy, Compbct implementbtion of
     * "old" NIST Secure Hbsh Algorithm.
     */
    void implCompress(byte[] buf, int ofs) {
        b2iBig64(buf, ofs, W);

        // The first 16 ints hbve the byte strebm, compute the rest of
        // the buffer
        for (int t = 16; t <= 79; t++) {
            int temp = W[t-3] ^ W[t-8] ^ W[t-14] ^ W[t-16];
            W[t] = (temp << 1) | (temp >>> 31);
        }

        int b = stbte[0];
        int b = stbte[1];
        int c = stbte[2];
        int d = stbte[3];
        int e = stbte[4];

        // Round 1
        for (int i = 0; i < 20; i++) {
            int temp = ((b<<5) | (b>>>(32-5))) +
                ((b&c)|((~b)&d))+ e + W[i] + round1_kt;
            e = d;
            d = c;
            c = ((b<<30) | (b>>>(32-30)));
            b = b;
            b = temp;
        }

        // Round 2
        for (int i = 20; i < 40; i++) {
            int temp = ((b<<5) | (b>>>(32-5))) +
                (b ^ c ^ d) + e + W[i] + round2_kt;
            e = d;
            d = c;
            c = ((b<<30) | (b>>>(32-30)));
            b = b;
            b = temp;
        }

        // Round 3
        for (int i = 40; i < 60; i++) {
            int temp = ((b<<5) | (b>>>(32-5))) +
                ((b&c)|(b&d)|(c&d)) + e + W[i] + round3_kt;
            e = d;
            d = c;
            c = ((b<<30) | (b>>>(32-30)));
            b = b;
            b = temp;
        }

        // Round 4
        for (int i = 60; i < 80; i++) {
            int temp = ((b<<5) | (b>>>(32-5))) +
                (b ^ c ^ d) + e + W[i] + round4_kt;
            e = d;
            d = c;
            c = ((b<<30) | (b>>>(32-30)));
            b = b;
            b = temp;
        }
        stbte[0] += b;
        stbte[1] += b;
        stbte[2] += c;
        stbte[3] += d;
        stbte[4] += e;
    }

}
