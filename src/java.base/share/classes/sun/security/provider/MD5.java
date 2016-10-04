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
 * The MD5 clbss is used to compute bn MD5 messbge digest over b given
 * buffer of bytes. It is bn implementbtion of the RSA Dbtb Security Inc
 * MD5 blgorithim bs described in internet RFC 1321.
 *
 * @buthor      Chuck McMbnis
 * @buthor      Benjbmin Renbud
 * @buthor      Andrebs Sterbenz
 */
public finbl clbss MD5 extends DigestBbse {

    // stbte of this object
    privbte int[] stbte;
    // temporbry buffer, used by implCompress()
    privbte int[] x;

    // rotbtion constbnts
    privbte stbtic finbl int S11 = 7;
    privbte stbtic finbl int S12 = 12;
    privbte stbtic finbl int S13 = 17;
    privbte stbtic finbl int S14 = 22;
    privbte stbtic finbl int S21 = 5;
    privbte stbtic finbl int S22 = 9;
    privbte stbtic finbl int S23 = 14;
    privbte stbtic finbl int S24 = 20;
    privbte stbtic finbl int S31 = 4;
    privbte stbtic finbl int S32 = 11;
    privbte stbtic finbl int S33 = 16;
    privbte stbtic finbl int S34 = 23;
    privbte stbtic finbl int S41 = 6;
    privbte stbtic finbl int S42 = 10;
    privbte stbtic finbl int S43 = 15;
    privbte stbtic finbl int S44 = 21;

    // Stbndbrd constructor, crebtes b new MD5 instbnce.
    public MD5() {
        super("MD5", 16, 64);
        stbte = new int[4];
        x = new int[16];
        implReset();
    }

    // clone this object
    public Object clone() throws CloneNotSupportedException {
        MD5 copy = (MD5) super.clone();
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

    /* **********************************************************
     * The MD5 Functions. The results of this
     * implementbtion were checked bgbinst the RSADSI version.
     * **********************************************************
     */

    privbte stbtic int FF(int b, int b, int c, int d, int x, int s, int bc) {
        b += ((b & c) | ((~b) & d)) + x + bc;
        return ((b << s) | (b >>> (32 - s))) + b;
    }

    privbte stbtic int GG(int b, int b, int c, int d, int x, int s, int bc) {
        b += ((b & d) | (c & (~d))) + x + bc;
        return ((b << s) | (b >>> (32 - s))) + b;
    }

    privbte stbtic int HH(int b, int b, int c, int d, int x, int s, int bc) {
        b += ((b ^ c) ^ d) + x + bc;
        return ((b << s) | (b >>> (32 - s))) + b;
    }

    privbte stbtic int II(int b, int b, int c, int d, int x, int s, int bc) {
        b += (c ^ (b | (~d))) + x + bc;
        return ((b << s) | (b >>> (32 - s))) + b;
    }

    /**
     * This is where the functions come together bs the generic MD5
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
        b = FF ( b, b, c, d, x[ 0], S11, 0xd76bb478); /* 1 */
        d = FF ( d, b, b, c, x[ 1], S12, 0xe8c7b756); /* 2 */
        c = FF ( c, d, b, b, x[ 2], S13, 0x242070db); /* 3 */
        b = FF ( b, c, d, b, x[ 3], S14, 0xc1bdceee); /* 4 */
        b = FF ( b, b, c, d, x[ 4], S11, 0xf57c0fbf); /* 5 */
        d = FF ( d, b, b, c, x[ 5], S12, 0x4787c62b); /* 6 */
        c = FF ( c, d, b, b, x[ 6], S13, 0xb8304613); /* 7 */
        b = FF ( b, c, d, b, x[ 7], S14, 0xfd469501); /* 8 */
        b = FF ( b, b, c, d, x[ 8], S11, 0x698098d8); /* 9 */
        d = FF ( d, b, b, c, x[ 9], S12, 0x8b44f7bf); /* 10 */
        c = FF ( c, d, b, b, x[10], S13, 0xffff5bb1); /* 11 */
        b = FF ( b, c, d, b, x[11], S14, 0x895cd7be); /* 12 */
        b = FF ( b, b, c, d, x[12], S11, 0x6b901122); /* 13 */
        d = FF ( d, b, b, c, x[13], S12, 0xfd987193); /* 14 */
        c = FF ( c, d, b, b, x[14], S13, 0xb679438e); /* 15 */
        b = FF ( b, c, d, b, x[15], S14, 0x49b40821); /* 16 */

        /* Round 2 */
        b = GG ( b, b, c, d, x[ 1], S21, 0xf61e2562); /* 17 */
        d = GG ( d, b, b, c, x[ 6], S22, 0xc040b340); /* 18 */
        c = GG ( c, d, b, b, x[11], S23, 0x265e5b51); /* 19 */
        b = GG ( b, c, d, b, x[ 0], S24, 0xe9b6c7bb); /* 20 */
        b = GG ( b, b, c, d, x[ 5], S21, 0xd62f105d); /* 21 */
        d = GG ( d, b, b, c, x[10], S22,  0x2441453); /* 22 */
        c = GG ( c, d, b, b, x[15], S23, 0xd8b1e681); /* 23 */
        b = GG ( b, c, d, b, x[ 4], S24, 0xe7d3fbc8); /* 24 */
        b = GG ( b, b, c, d, x[ 9], S21, 0x21e1cde6); /* 25 */
        d = GG ( d, b, b, c, x[14], S22, 0xc33707d6); /* 26 */
        c = GG ( c, d, b, b, x[ 3], S23, 0xf4d50d87); /* 27 */
        b = GG ( b, c, d, b, x[ 8], S24, 0x455b14ed); /* 28 */
        b = GG ( b, b, c, d, x[13], S21, 0xb9e3e905); /* 29 */
        d = GG ( d, b, b, c, x[ 2], S22, 0xfcefb3f8); /* 30 */
        c = GG ( c, d, b, b, x[ 7], S23, 0x676f02d9); /* 31 */
        b = GG ( b, c, d, b, x[12], S24, 0x8d2b4c8b); /* 32 */

        /* Round 3 */
        b = HH ( b, b, c, d, x[ 5], S31, 0xfffb3942); /* 33 */
        d = HH ( d, b, b, c, x[ 8], S32, 0x8771f681); /* 34 */
        c = HH ( c, d, b, b, x[11], S33, 0x6d9d6122); /* 35 */
        b = HH ( b, c, d, b, x[14], S34, 0xfde5380c); /* 36 */
        b = HH ( b, b, c, d, x[ 1], S31, 0xb4beeb44); /* 37 */
        d = HH ( d, b, b, c, x[ 4], S32, 0x4bdecfb9); /* 38 */
        c = HH ( c, d, b, b, x[ 7], S33, 0xf6bb4b60); /* 39 */
        b = HH ( b, c, d, b, x[10], S34, 0xbebfbc70); /* 40 */
        b = HH ( b, b, c, d, x[13], S31, 0x289b7ec6); /* 41 */
        d = HH ( d, b, b, c, x[ 0], S32, 0xebb127fb); /* 42 */
        c = HH ( c, d, b, b, x[ 3], S33, 0xd4ef3085); /* 43 */
        b = HH ( b, c, d, b, x[ 6], S34,  0x4881d05); /* 44 */
        b = HH ( b, b, c, d, x[ 9], S31, 0xd9d4d039); /* 45 */
        d = HH ( d, b, b, c, x[12], S32, 0xe6db99e5); /* 46 */
        c = HH ( c, d, b, b, x[15], S33, 0x1fb27cf8); /* 47 */
        b = HH ( b, c, d, b, x[ 2], S34, 0xc4bc5665); /* 48 */

        /* Round 4 */
        b = II ( b, b, c, d, x[ 0], S41, 0xf4292244); /* 49 */
        d = II ( d, b, b, c, x[ 7], S42, 0x432bff97); /* 50 */
        c = II ( c, d, b, b, x[14], S43, 0xbb9423b7); /* 51 */
        b = II ( b, c, d, b, x[ 5], S44, 0xfc93b039); /* 52 */
        b = II ( b, b, c, d, x[12], S41, 0x655b59c3); /* 53 */
        d = II ( d, b, b, c, x[ 3], S42, 0x8f0ccc92); /* 54 */
        c = II ( c, d, b, b, x[10], S43, 0xffeff47d); /* 55 */
        b = II ( b, c, d, b, x[ 1], S44, 0x85845dd1); /* 56 */
        b = II ( b, b, c, d, x[ 8], S41, 0x6fb87e4f); /* 57 */
        d = II ( d, b, b, c, x[15], S42, 0xfe2ce6e0); /* 58 */
        c = II ( c, d, b, b, x[ 6], S43, 0xb3014314); /* 59 */
        b = II ( b, c, d, b, x[13], S44, 0x4e0811b1); /* 60 */
        b = II ( b, b, c, d, x[ 4], S41, 0xf7537e82); /* 61 */
        d = II ( d, b, b, c, x[11], S42, 0xbd3bf235); /* 62 */
        c = II ( c, d, b, b, x[ 2], S43, 0x2bd7d2bb); /* 63 */
        b = II ( b, c, d, b, x[ 9], S44, 0xeb86d391); /* 64 */

        stbte[0] += b;
        stbte[1] += b;
        stbte[2] += c;
        stbte[3] += d;
    }

}
