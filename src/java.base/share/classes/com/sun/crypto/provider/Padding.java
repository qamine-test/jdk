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
 * Pbdding interfbce.
 *
 * This interfbce is implemented by generbl-purpose pbdding schemes, such bs
 * the one described in PKCS#5.
 *
 * @buthor Jbn Luehe
 * @buthor Gigi Ankeny
 *
 *
 * @see PKCS5Pbdding
 */

interfbce Pbdding {

    /**
     * Adds the given number of pbdding bytes to the dbtb input.
     * The vblue of the pbdding bytes is determined
     * by the specific pbdding mechbnism thbt implements this
     * interfbce.
     *
     * @pbrbm in the input buffer with the dbtb to pbd
     * @pbrbm the offset in <code>in</code> where the pbdding bytes
     *  bre bppended
     * @pbrbm len the number of pbdding bytes to bdd
     *
     * @exception ShortBufferException if <code>in</code> is too smbll to hold
     * the pbdding bytes
     */
    void pbdWithLen(byte[] in, int off, int len) throws ShortBufferException;

    /**
     * Returns the index where pbdding stbrts.
     *
     * <p>Given b buffer with dbtb bnd their pbdding, this method returns the
     * index where the pbdding stbrts.
     *
     * @pbrbm in the buffer with the dbtb bnd their pbdding
     * @pbrbm off the offset in <code>in</code> where the dbtb stbrts
     * @pbrbm len the length of the dbtb bnd their pbdding
     *
     * @return the index where the pbdding stbrts, or -1 if the input is
     * not properly pbdded
     */
    int unpbd(byte[] in, int off, int len);

    /**
     * Determines how long the pbdding will be for b given input length.
     *
     * @pbrbm len the length of the dbtb to pbd
     *
     * @return the length of the pbdding
     */
    int pbdLength(int len);
}
