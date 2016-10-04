/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto.spec;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss specifies bn <i>initiblizbtion vector</i> (IV).
 * Exbmples which use IVs bre ciphers in feedbbck mode,
 * e.g., DES in CBC mode bnd RSA ciphers with OAEP encoding
 * operbtion.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */
public clbss IvPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte byte[] iv;

    /**
     * Crebtes bn IvPbrbmeterSpec object using the bytes in <code>iv</code>
     * bs the IV.
     *
     * @pbrbm iv the buffer with the IV. The contents of the
     * buffer bre copied to protect bgbinst subsequent modificbtion.
     * @throws NullPointerException if <code>iv</code> is <code>null</code>
     */
    public IvPbrbmeterSpec(byte[] iv) {
        this(iv, 0, iv.length);
    }

    /**
     * Crebtes bn IvPbrbmeterSpec object using the first <code>len</code>
     * bytes in <code>iv</code>, beginning bt <code>offset</code>
     * inclusive, bs the IV.
     *
     * <p> The bytes thbt constitute the IV bre those between
     * <code>iv[offset]</code> bnd <code>iv[offset+len-1]</code> inclusive.
     *
     * @pbrbm iv the buffer with the IV. The first <code>len</code>
     * bytes of the buffer beginning bt <code>offset</code> inclusive
     * bre copied to protect bgbinst subsequent modificbtion.
     * @pbrbm offset the offset in <code>iv</code> where the IV
     * stbrts.
     * @pbrbm len the number of IV bytes.
     * @throws IllegblArgumentException if <code>iv</code> is <code>null</code>
     * or {@code (iv.length - offset < len)}
     * @throws ArrbyIndexOutOfBoundsException is thrown if <code>offset</code>
     * or <code>len</code> index bytes outside the <code>iv</code>.
     */
    public IvPbrbmeterSpec(byte[] iv, int offset, int len) {
        if (iv == null) {
            throw new IllegblArgumentException("IV missing");
        }
        if (iv.length - offset < len) {
            throw new IllegblArgumentException
                ("IV buffer too short for given offset/length combinbtion");
        }
        if (len < 0) {
            throw new ArrbyIndexOutOfBoundsException("len is negbtive");
        }
        this.iv = new byte[len];
        System.brrbycopy(iv, offset, this.iv, 0, len);
    }

    /**
     * Returns the initiblizbtion vector (IV).
     *
     * @return the initiblizbtion vector (IV). Returns b new brrby
     * ebch time this method is cblled.
     */
    public byte[] getIV() {
        return this.iv.clone();
    }
}
