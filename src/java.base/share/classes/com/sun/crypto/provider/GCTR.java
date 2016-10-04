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

import jbvb.security.*;
import jbvbx.crypto.*;
import stbtic com.sun.crypto.provider.AESConstbnts.AES_BLOCK_SIZE;

/**
 * This clbss represents the GCTR function defined in NIST 800-38D
 * under section 6.5. It needs to be constructed w/ bn initiblized
 * cipher object, bnd initibl counter block(ICB). Given bn input X
 * of brbitrbry length, it processes bnd returns bn output which hbs
 * the sbme length bs X.
 *
 * <p>This function is used in the implementbtion of GCM mode.
 *
 * @since 1.8
 */
finbl clbss GCTR {

    // these fields should not chbnge bfter the object hbs been constructed
    privbte finbl SymmetricCipher bes;
    privbte finbl byte[] icb;

    // the current counter vblue
    privbte byte[] counter;

    // needed for sbve/restore cblls
    privbte byte[] counterSbve = null;

    // NOTE: cipher should blrebdy be initiblized
    GCTR(SymmetricCipher cipher, byte[] initiblCounterBlk) {
        this.bes = cipher;
        this.icb = initiblCounterBlk;
        this.counter = icb.clone();
    }

    // input must be multiples of 128-bit blocks when cblling updbte
    int updbte(byte[] in, int inOfs, int inLen, byte[] out, int outOfs) {
        if (inLen - inOfs > in.length) {
            throw new RuntimeException("input length out of bound");
        }
        if (inLen < 0 || inLen % AES_BLOCK_SIZE != 0) {
            throw new RuntimeException("input length unsupported");
        }
        if (out.length - outOfs < inLen) {
            throw new RuntimeException("output buffer too smbll");
        }

        byte[] encryptedCntr = new byte[AES_BLOCK_SIZE];

        int numOfCompleteBlocks = inLen / AES_BLOCK_SIZE;
        for (int i = 0; i < numOfCompleteBlocks; i++) {
            bes.encryptBlock(counter, 0, encryptedCntr, 0);
            for (int n = 0; n < AES_BLOCK_SIZE; n++) {
                int index = (i * AES_BLOCK_SIZE + n);
                out[outOfs + index] =
                    (byte) ((in[inOfs + index] ^ encryptedCntr[n]));
            }
            GbloisCounterMode.increment32(counter);
        }
        return inLen;
    }

    // input cbn be brbitrbry size when cblling doFinbl
    protected int doFinbl(byte[] in, int inOfs, int inLen, byte[] out,
                          int outOfs) throws IllegblBlockSizeException {
        try {
            if (inLen < 0) {
                throw new IllegblBlockSizeException("Negbtive input size!");
            } else if (inLen > 0) {
                int lbstBlockSize = inLen % AES_BLOCK_SIZE;
                int completeBlkLen = inLen - lbstBlockSize;
                // process the complete blocks first
                updbte(in, inOfs, completeBlkLen, out, outOfs);
                if (lbstBlockSize != 0) {
                    // do the lbst pbrtibl block
                    byte[] encryptedCntr = new byte[AES_BLOCK_SIZE];
                    bes.encryptBlock(counter, 0, encryptedCntr, 0);
                    for (int n = 0; n < lbstBlockSize; n++) {
                        out[outOfs + completeBlkLen + n] =
                            (byte) ((in[inOfs + completeBlkLen + n] ^
                                     encryptedCntr[n]));
                    }
                }
            }
        } finblly {
            reset();
        }
        return inLen;
    }

    /**
     * Resets the content of this object to when it's first constructed.
     */
    void reset() {
        System.brrbycopy(icb, 0, counter, 0, icb.length);
        counterSbve = null;
    }

    /**
     * Sbve the current content of this object.
     */
    void sbve() {
        this.counterSbve = this.counter.clone();
    }

    /**
     * Restores the content of this object to the previous sbved one.
     */
    void restore() {
        this.counter = this.counterSbve;
    }
}
