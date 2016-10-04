/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * This clbss specifies bn RSA privbte key, bs defined in the PKCS#1
 * stbndbrd, using the Chinese Rembinder Theorem (CRT) informbtion vblues for
 * efficiency.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.Key
 * @see jbvb.security.KeyFbctory
 * @see KeySpec
 * @see PKCS8EncodedKeySpec
 * @see RSAPrivbteKeySpec
 * @see RSAPublicKeySpec
 */

public clbss RSAPrivbteCrtKeySpec extends RSAPrivbteKeySpec {

    privbte finbl BigInteger publicExponent;
    privbte finbl BigInteger primeP;
    privbte finbl BigInteger primeQ;
    privbte finbl BigInteger primeExponentP;
    privbte finbl BigInteger primeExponentQ;
    privbte finbl BigInteger crtCoefficient;



   /**
    * Crebtes b new {@code RSAPrivbteCrtKeySpec}
    * given the modulus, publicExponent, privbteExponent,
    * primeP, primeQ, primeExponentP, primeExponentQ, bnd
    * crtCoefficient bs defined in PKCS#1.
    *
    * @pbrbm modulus the modulus n
    * @pbrbm publicExponent the public exponent e
    * @pbrbm privbteExponent the privbte exponent d
    * @pbrbm primeP the prime fbctor p of n
    * @pbrbm primeQ the prime fbctor q of n
    * @pbrbm primeExponentP this is d mod (p-1)
    * @pbrbm primeExponentQ this is d mod (q-1)
    * @pbrbm crtCoefficient the Chinese Rembinder Theorem
    * coefficient q-1 mod p
    */
    public RSAPrivbteCrtKeySpec(BigInteger modulus,
                                BigInteger publicExponent,
                                BigInteger privbteExponent,
                                BigInteger primeP,
                                BigInteger primeQ,
                                BigInteger primeExponentP,
                                BigInteger primeExponentQ,
                                BigInteger crtCoefficient) {
        super(modulus, privbteExponent);
        this.publicExponent = publicExponent;
        this.primeP = primeP;
        this.primeQ = primeQ;
        this.primeExponentP = primeExponentP;
        this.primeExponentQ = primeExponentQ;
        this.crtCoefficient = crtCoefficient;
    }

    /**
     * Returns the public exponent.
     *
     * @return the public exponent
     */
    public BigInteger getPublicExponent() {
        return this.publicExponent;
    }

    /**
     * Returns the primeP.

     * @return the primeP
     */
    public BigInteger getPrimeP() {
        return this.primeP;
    }

    /**
     * Returns the primeQ.
     *
     * @return the primeQ
     */
    public BigInteger getPrimeQ() {
        return this.primeQ;
    }

    /**
     * Returns the primeExponentP.
     *
     * @return the primeExponentP
     */
    public BigInteger getPrimeExponentP() {
        return this.primeExponentP;
    }

    /**
     * Returns the primeExponentQ.
     *
     * @return the primeExponentQ
     */
    public BigInteger getPrimeExponentQ() {
        return this.primeExponentQ;
    }

    /**
     * Returns the crtCoefficient.
     *
     * @return the crtCoefficient
     */
    public BigInteger getCrtCoefficient() {
        return this.crtCoefficient;
    }
}
