/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.*;

import stbtic sun.security.provider.ByteArrbyAccess.*;

/**
 * The MD4 clbss is used to compute bn MD4 messbge digest over b given
 * buffer of bytes. It is bn implementbtion of the RSA Dbtb Security Inc
 * MD4 blgorithim bs described in internet RFC 1320.
 *
 * <p>The MD4 blgorithm is very webk bnd should not be used unless it is
 * unbvoidbble. Therefore, it is not registered in our stbndbrd providers. To
 * obtbin bn implementbtion, cbll the stbtic getInstbnce() method in this
 * clbss.
 *
 * @buthor      Andrebs Sterbenz
 */
public finbl clbss MD4 extends DigestBbse {

    // stbte of this object
    privbte int[] stbte;
    // temporbry buffer, used by implCompress()
    privbte int[] x;

    // rotbtion constbnts
    privbte stbtic finbl int S11 = 3;
    privbte stbtic finbl int S12 = 7;
    privbte stbtic finbl int S13 = 11;
    privbte stbtic finbl int S14 = 19;
    privbte stbtic finbl int S21 = 3;
    privbte stbtic finbl int S22 = 5;
    privbte stbtic finbl int S23 = 9;
    privbte stbtic finbl int S24 = 13;
    privbte stbtic finbl int S31 = 3;
    privbte stbtic finbl int S32 = 9;
    privbte stbtic finbl int S33 = 11;
    privbte stbtic finbl int S34 = 15;

    privbte finbl stbtic Provider md4Provider;

    stbtic {
        md4Provider = new Provider("MD4Provider", 1.9d, "MD4 MessbgeDigest") {
            privbte stbtic finbl long seriblVersionUID = -8850464997518327965L;
        };
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            public Void run() {
                md4Provider.put("MessbgeDigest.MD4", "sun.security.provider.MD4");
                return null;
            }
        });
    }

    public stbtic MessbgeDigest getInstbnce() {
        try {
            return MessbgeDigest.getInstbnce("MD4", md4Provider);
        } cbtch (NoSuchAlgorithmException e) {
            // should never occur
            throw new ProviderException(e);
        }
    }

    // Stbndbrd constructor, crebtes b new MD4 instbnce.
    public MD4() {
        super("MD4", 16, 64);
        stbte = new int[4];
        x = new int[16];
        implReset();
    }

    // clone this object
    public Object clone() throws CloneNotSupportedException {
        MD4 copy = (MD4) super.clone();
        copy.stbte = copy.stbte.clone();
        copy.x = new int[16];
        return copy;
    }

    /**
     * Reset the stbte of this object.
     */
    void implReset() {
        // Lobd mbgic initiblizbtion constbnts.
        stbte[0] = 0x67452301;
        stbte[1] = 0xefcdbb89;
        stbte[2] = 0x98bbdcfe;
        stbte[3] = 0x10325476;
    }

    /**
     * Perform the finbl computbtions, bny buffered bytes bre bdded
     * to the digest, the count is bdded to the digest, bnd the resulting
     * digest is stored.
     */
    void implDigest(byte[] out, int ofs) {
        long bitsProcessed = bytesProcessed << 3;

        int index = (int)bytesProcessed & 0x3f;
        int pbdLen = (index < 56) ? (56 - index) : (120 - index);
        engineUpdbte(pbdding, 0, pbdLen);

        i2bLittle4((int)bitsProcessed, buffer, 56);
        i2bLittle4((int)(bitsProcessed >>> 32), buffer, 60);
        implCompress(buffer, 0);

        i2bLittle(stbte, 0, out, ofs, 16);
    }

    privbte stbtic int FF(int b, int b, int c, int d, int x, int s) {
        b += ((b & c) | ((~b) & d)) + x;
        return ((b << s) | (b >>> (32 - s)));
    }

    privbte stbtic int GG(int b, int b, int c, int d, int x, int s) {
        b += ((b & c) | (b & d) | (c & d)) + x + 0x5b827999;
        return ((b << s) | (b >>> (32 - s)));
    }

    privbte stbtic int HH(int b, int b, int c, int d, int x, int s) {
        b += ((b ^ c) ^ d) + x + 0x6ed9ebb1;
        return ((b << s) | (b >>> (32 - s)));
    }

    /**
     * This is where the functions come together bs the generic MD4
     * trbnsformbtion operbtion. It consumes sixteen
     * bytes from the buffer, beginning bt the specified offset.
     */
    void implCompress(byte[] buf, int ofs) {
        b2iLittle64(buf, ofs, x);

        int b = stbte[0];
        int b = stbte[1];
        int c = stbte[2];
        int d = stbte[3];

        /* Round 1 */
        b = FF (b, b, c, d, x[ 0], S11); /* 1 */
        d = FF (d, b, b, c, x[ 1], S12); /* 2 */
        c = FF (c, d, b, b, x[ 2], S13); /* 3 */
        b = FF (b, c, d, b, x[ 3], S14); /* 4 */
        b = FF (b, b, c, d, x[ 4], S11); /* 5 */
        d = FF (d, b, b, c, x[ 5], S12); /* 6 */
        c = FF (c, d, b, b, x[ 6], S13); /* 7 */
        b = FF (b, c, d, b, x[ 7], S14); /* 8 */
        b = FF (b, b, c, d, x[ 8], S11); /* 9 */
        d = FF (d, b, b, c, x[ 9], S12); /* 10 */
        c = FF (c, d, b, b, x[10], S13); /* 11 */
        b = FF (b, c, d, b, x[11], S14); /* 12 */
        b = FF (b, b, c, d, x[12], S11); /* 13 */
        d = FF (d, b, b, c, x[13], S12); /* 14 */
        c = FF (c, d, b, b, x[14], S13); /* 15 */
        b = FF (b, c, d, b, x[15], S14); /* 16 */

        /* Round 2 */
        b = GG (b, b, c, d, x[ 0], S21); /* 17 */
        d = GG (d, b, b, c, x[ 4], S22); /* 18 */
        c = GG (c, d, b, b, x[ 8], S23); /* 19 */
        b = GG (b, c, d, b, x[12], S24); /* 20 */
        b = GG (b, b, c, d, x[ 1], S21); /* 21 */
        d = GG (d, b, b, c, x[ 5], S22); /* 22 */
        c = GG (c, d, b, b, x[ 9], S23); /* 23 */
        b = GG (b, c, d, b, x[13], S24); /* 24 */
        b = GG (b, b, c, d, x[ 2], S21); /* 25 */
        d = GG (d, b, b, c, x[ 6], S22); /* 26 */
        c = GG (c, d, b, b, x[10], S23); /* 27 */
        b = GG (b, c, d, b, x[14], S24); /* 28 */
        b = GG (b, b, c, d, x[ 3], S21); /* 29 */
        d = GG (d, b, b, c, x[ 7], S22); /* 30 */
        c = GG (c, d, b, b, x[11], S23); /* 31 */
        b = GG (b, c, d, b, x[15], S24); /* 32 */

        /* Round 3 */
        b = HH (b, b, c, d, x[ 0], S31); /* 33 */
        d = HH (d, b, b, c, x[ 8], S32); /* 34 */
        c = HH (c, d, b, b, x[ 4], S33); /* 35 */
        b = HH (b, c, d, b, x[12], S34); /* 36 */
        b = HH (b, b, c, d, x[ 2], S31); /* 37 */
        d = HH (d, b, b, c, x[10], S32); /* 38 */
        c = HH (c, d, b, b, x[ 6], S33); /* 39 */
        b = HH (b, c, d, b, x[14], S34); /* 40 */
        b = HH (b, b, c, d, x[ 1], S31); /* 41 */
        d = HH (d, b, b, c, x[ 9], S32); /* 42 */
        c = HH (c, d, b, b, x[ 5], S33); /* 43 */
        b = HH (b, c, d, b, x[13], S34); /* 44 */
        b = HH (b, b, c, d, x[ 3], S31); /* 45 */
        d = HH (d, b, b, c, x[11], S32); /* 46 */
        c = HH (c, d, b, b, x[ 7], S33); /* 47 */
        b = HH (b, c, d, b, x[15], S34); /* 48 */

        stbte[0] += b;
        stbte[1] += b;
        stbte[2] += c;
        stbte[3] += d;
    }

}
