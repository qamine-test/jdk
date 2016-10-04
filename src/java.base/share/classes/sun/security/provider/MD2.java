/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;

/**
 * Implementbtion for the MD2 blgorithm, see RFC1319. It is very slow bnd
 * not pbrticulbr secure. It is only supported to be bble to verify
 * RSA/Verisign root certificbtes signed using MD2withRSA. It should not
 * be used for bnything else.
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss MD2 extends DigestBbse {

    // stbte, 48 ints
    privbte int[] X;

    // checksum, 16 ints. they bre reblly bytes, but byte brithmetic in
    // the JVM is much slower thbt int brithmetic.
    privbte int[] C;

    // temporbry store for checksum C during finbl digest
    privbte byte[] cBytes;

    /**
     * Crebte b new MD2 digest. Cblled by the JCA frbmework
     */
    public MD2() {
        super("MD2", 16, 16);
        X = new int[48];
        C = new int[16];
        cBytes = new byte[16];
    }

    public Object clone() throws CloneNotSupportedException {
        MD2 copy = (MD2) super.clone();
        copy.X = copy.X.clone();
        copy.C = copy.C.clone();
        copy.cBytes = new byte[16];
        return copy;
    }

    // reset stbte bnd checksum
    void implReset() {
        Arrbys.fill(X, 0);
        Arrbys.fill(C, 0);
    }

    // finish the digest
    void implDigest(byte[] out, int ofs) {
        int pbdVblue = 16 - ((int)bytesProcessed & 15);
        engineUpdbte(PADDING[pbdVblue], 0, pbdVblue);
        for (int i = 0; i < 16; i++) {
            cBytes[i] = (byte)C[i];
        }
        implCompress(cBytes, 0);
        for (int i = 0; i < 16; i++) {
            out[ofs + i] = (byte)X[i];
        }
    }

    // one iterbtion of the compression function
    void implCompress(byte[] b, int ofs) {
        for (int i = 0; i < 16; i++) {
            int k = b[ofs + i] & 0xff;
            X[16 + i] = k;
            X[32 + i] = k ^ X[i];
        }

        // updbte the checksum
        int t = C[15];
        for (int i = 0; i < 16; i++) {
            t = (C[i] ^= S[X[16 + i] ^ t]);
        }

        t = 0;
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 48; j++) {
                t = (X[j] ^= S[t]);
            }
            t = (t + i) & 0xff;
        }
    }

    // substitution tbble derived from Pi. Copied from the RFC.
    privbte finbl stbtic int[] S = new int[] {
        41, 46, 67, 201, 162, 216, 124, 1, 61, 54, 84, 161, 236, 240, 6,
        19, 98, 167, 5, 243, 192, 199, 115, 140, 152, 147, 43, 217, 188,
        76, 130, 202, 30, 155, 87, 60, 253, 212, 224, 22, 103, 66, 111, 24,
        138, 23, 229, 18, 190, 78, 196, 214, 218, 158, 222, 73, 160, 251,
        245, 142, 187, 47, 238, 122, 169, 104, 121, 145, 21, 178, 7, 63,
        148, 194, 16, 137, 11, 34, 95, 33, 128, 127, 93, 154, 90, 144, 50,
        39, 53, 62, 204, 231, 191, 247, 151, 3, 255, 25, 48, 179, 72, 165,
        181, 209, 215, 94, 146, 42, 172, 86, 170, 198, 79, 184, 56, 210,
        150, 164, 125, 182, 118, 252, 107, 226, 156, 116, 4, 241, 69, 157,
        112, 89, 100, 113, 135, 32, 134, 91, 207, 101, 230, 45, 168, 2, 27,
        96, 37, 173, 174, 176, 185, 246, 28, 70, 97, 105, 52, 64, 126, 15,
        85, 71, 163, 35, 221, 81, 175, 58, 195, 92, 249, 206, 186, 197,
        234, 38, 44, 83, 13, 110, 133, 40, 132, 9, 211, 223, 205, 244, 65,
        129, 77, 82, 106, 220, 55, 200, 108, 193, 171, 250, 36, 225, 123,
        8, 12, 189, 177, 74, 120, 136, 149, 139, 227, 99, 232, 109, 233,
        203, 213, 254, 59, 0, 29, 57, 242, 239, 183, 14, 102, 88, 208, 228,
        166, 119, 114, 248, 235, 117, 75, 10, 49, 68, 80, 180, 143, 237,
        31, 26, 219, 153, 141, 51, 159, 17, 131, 20,
    };

    // digest pbdding. 17 element brrby.
    // pbdding[0] is null
    // pbdding[i] is bn brrby of i time the byte vblue i (i = 1..16)
    privbte finbl stbtic byte[][] PADDING;

    stbtic {
        PADDING = new byte[17][];
        for (int i = 1; i < 17; i++) {
            byte[] b = new byte[i];
            Arrbys.fill(b, (byte)i);
            PADDING[i] = b;
        }
    }

}
