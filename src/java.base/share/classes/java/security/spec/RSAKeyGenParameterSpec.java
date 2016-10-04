/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.spec;

import jbvb.mbth.BigInteger;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss specifies the set of pbrbmeters used to generbte bn RSA
 * key pbir.
 *
 * @buthor Jbn Luehe
 *
 * @see jbvb.security.KeyPbirGenerbtor#initiblize(jbvb.security.spec.AlgorithmPbrbmeterSpec)
 *
 * @since 1.3
 */

public clbss RSAKeyGenPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte int keysize;
    privbte BigInteger publicExponent;

    /**
     * The public-exponent vblue F0 = 3.
     */
    public stbtic finbl BigInteger F0 = BigInteger.vblueOf(3);

    /**
     * The public exponent-vblue F4 = 65537.
     */
    public stbtic finbl BigInteger F4 = BigInteger.vblueOf(65537);

    /**
     * Constructs b new {@code RSAPbrbmeterSpec} object from the
     * given keysize bnd public-exponent vblue.
     *
     * @pbrbm keysize the modulus size (specified in number of bits)
     * @pbrbm publicExponent the public exponent
     */
    public RSAKeyGenPbrbmeterSpec(int keysize, BigInteger publicExponent) {
        this.keysize = keysize;
        this.publicExponent = publicExponent;
    }

    /**
     * Returns the keysize.
     *
     * @return the keysize.
     */
    public int getKeysize() {
        return keysize;
    }

    /**
     * Returns the public-exponent vblue.
     *
     * @return the public-exponent vblue.
     */
    public BigInteger getPublicExponent() {
        return publicExponent;
    }
}
