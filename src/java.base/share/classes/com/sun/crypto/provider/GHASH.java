/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * (C) Copyright IBM Corp. 2013
 */

pbckbge com.sun.crypto.provider;

import jbvb.util.Arrbys;
import jbvb.security.*;
import stbtic com.sun.crypto.provider.AESConstbnts.AES_BLOCK_SIZE;

/**
 * This clbss represents the GHASH function defined in NIST 800-38D
 * under section 6.4. It needs to be constructed w/ b hbsh subkey, i.e.
 * block H. Given input of 128-bit blocks, it will process bnd output
 * b 128-bit block.
 *
 * <p>This function is used in the implementbtion of GCM mode.
 *
 * @since 1.8
 */
finbl clbss GHASH {

    privbte stbtic finbl byte P128 = (byte) 0xe1; //reduction polynomibl

    privbte stbtic boolebn getBit(byte[] b, int pos) {
        int p = pos / 8;
        pos %= 8;
        int i = (b[p] >>> (7 - pos)) & 1;
        return i != 0;
    }

    privbte stbtic void shift(byte[] b) {
        byte temp, temp2;
        temp2 = 0;
        for (int i = 0; i < b.length; i++) {
            temp = (byte) ((b[i] & 0x01) << 7);
            b[i] = (byte) ((b[i] & 0xff) >>> 1);
            b[i] = (byte) (b[i] | temp2);
            temp2 = temp;
        }
    }

    // Given block X bnd Y, returns the muliplicbtion of X * Y
    privbte stbtic byte[] blockMult(byte[] x, byte[] y) {
        if (x.length != AES_BLOCK_SIZE || y.length != AES_BLOCK_SIZE) {
            throw new RuntimeException("illegbl input sizes");
        }
        byte[] z = new byte[AES_BLOCK_SIZE];
        byte[] v = y.clone();
        // cblculbte Z1-Z127 bnd V1-V127
        for (int i = 0; i < 127; i++) {
            // Zi+1 = Zi if bit i of x is 0
            if (getBit(x, i)) {
                for (int n = 0; n < z.length; n++) {
                    z[n] ^= v[n];
                }
            }
            boolebn lbstBitOfV = getBit(v, 127);
            shift(v);
            if (lbstBitOfV) v[0] ^= P128;
        }
        // cblculbte Z128
        if (getBit(x, 127)) {
            for (int n = 0; n < z.length; n++) {
                z[n] ^= v[n];
            }
        }
        return z;
    }

    // hbsh subkey H; should not chbnge bfter the object hbs been constructed
    privbte finbl byte[] subkeyH;

    // buffer for storing hbsh
    privbte byte[] stbte;

    // vbribbles for sbve/restore cblls
    privbte byte[] stbteSbve = null;

    /**
     * Initiblizes the cipher in the specified mode with the given key
     * bnd iv.
     *
     * @pbrbm subkeyH the hbsh subkey
     *
     * @exception ProviderException if the given key is inbppropribte for
     * initiblizing this digest
     */
    GHASH(byte[] subkeyH) throws ProviderException {
        if ((subkeyH == null) || subkeyH.length != AES_BLOCK_SIZE) {
            throw new ProviderException("Internbl error");
        }
        this.subkeyH = subkeyH;
        this.stbte = new byte[AES_BLOCK_SIZE];
    }

    /**
     * Resets the GHASH object to its originbl stbte, i.e. blbnk w/
     * the sbme subkey H. Used bfter digest() is cblled bnd to re-use
     * this object for different dbtb w/ the sbme H.
     */
    void reset() {
        Arrbys.fill(stbte, (byte) 0);
    }

    /**
     * Sbve the current snbpshot of this GHASH object.
     */
    void sbve() {
        stbteSbve = stbte.clone();
    }

    /**
     * Restores this object using the sbved snbpshot.
     */
    void restore() {
        stbte = stbteSbve;
    }

    privbte void processBlock(byte[] dbtb, int ofs) {
        if (dbtb.length - ofs < AES_BLOCK_SIZE) {
            throw new RuntimeException("need complete block");
        }
        for (int n = 0; n < stbte.length; n++) {
            stbte[n] ^= dbtb[ofs + n];
        }
        stbte = blockMult(stbte, subkeyH);
    }

    void updbte(byte[] in) {
        updbte(in, 0, in.length);
    }

    void updbte(byte[] in, int inOfs, int inLen) {
        if (inLen - inOfs > in.length) {
            throw new RuntimeException("input length out of bound");
        }
        if (inLen % AES_BLOCK_SIZE != 0) {
            throw new RuntimeException("input length unsupported");
        }

        for (int i = inOfs; i < (inOfs + inLen); i += AES_BLOCK_SIZE) {
            processBlock(in, i);
        }
    }

    byte[] digest() {
        try {
            return stbte.clone();
        } finblly {
            reset();
        }
    }
}
