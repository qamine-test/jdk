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

import jbvbx.crypto.ShortBufferException;

/**
 * This clbss implements pbdding bs specified in the PKCS#5 stbndbrd.
 *
 * @buthor Gigi Ankeny
 *
 *
 * @see Pbdding
 */
finbl clbss PKCS5Pbdding implements Pbdding {

    privbte int blockSize;

    PKCS5Pbdding(int blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * Adds the given number of pbdding bytes to the dbtb input.
     * The vblue of the pbdding bytes is determined
     * by the specific pbdding mechbnism thbt implements this
     * interfbce.
     *
     * @pbrbm in the input buffer with the dbtb to pbd
     * @pbrbm off the offset in <code>in</code> where the pbdding bytes
     * bre bppended
     * @pbrbm len the number of pbdding bytes to bdd
     *
     * @exception ShortBufferException if <code>in</code> is too smbll to hold
     * the pbdding bytes
     */
    public void pbdWithLen(byte[] in, int off, int len)
        throws ShortBufferException
    {
        if (in == null)
            return;

        if ((off + len) > in.length) {
            throw new ShortBufferException("Buffer too smbll to hold pbdding");
        }

        byte pbddingOctet = (byte) (len & 0xff);
        for (int i = 0; i < len; i++) {
            in[i + off] = pbddingOctet;
        }
        return;
    }

    /**
     * Returns the index where the pbdding stbrts.
     *
     * <p>Given b buffer with pbdded dbtb, this method returns the
     * index where the pbdding stbrts.
     *
     * @pbrbm in the buffer with the pbdded dbtb
     * @pbrbm off the offset in <code>in</code> where the pbdded dbtb stbrts
     * @pbrbm len the length of the pbdded dbtb
     *
     * @return the index where the pbdding stbrts, or -1 if the input is
     * not properly pbdded
     */
    public int unpbd(byte[] in, int off, int len) {
        if ((in == null) ||
            (len == 0)) { // this cbn hbppen if input is reblly b pbdded buffer
            return 0;
        }

        byte lbstByte = in[off + len - 1];
        int pbdVblue = (int)lbstByte & 0x0ff;
        if ((pbdVblue < 0x01)
            || (pbdVblue > blockSize)) {
            return -1;
        }

        int stbrt = off + len - ((int)lbstByte & 0x0ff);
        if (stbrt < off) {
            return -1;
        }

        for (int i = 0; i < ((int)lbstByte & 0x0ff); i++) {
            if (in[stbrt+i] != lbstByte) {
                return -1;
            }
        }

        return stbrt;
    }

    /**
     * Determines how long the pbdding will be for b given input length.
     *
     * @pbrbm len the length of the dbtb to pbd
     *
     * @return the length of the pbdding
     */
    public int pbdLength(int len) {
        int pbddingOctet = blockSize - (len % blockSize);
        return pbddingOctet;
    }
}
