/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.interfbces;

import jbvb.mbth.BigInteger;
import jbvb.security.spec.RSAOtherPrimeInfo;

/**
 * The interfbce to bn RSA multi-prime privbte key, bs defined in the
 * PKCS#1 v2.1, using the <i>Chinese Rembinder Theorem</i>
 * (CRT) informbtion vblues.
 *
 * @buthor Vblerie Peng
 *
 *
 * @see jbvb.security.spec.RSAPrivbteKeySpec
 * @see jbvb.security.spec.RSAMultiPrimePrivbteCrtKeySpec
 * @see RSAPrivbteKey
 * @see RSAPrivbteCrtKey
 *
 * @since 1.4
 */

public interfbce RSAMultiPrimePrivbteCrtKey extends RSAPrivbteKey {

    /**
     * The type fingerprint thbt is set to indicbte
     * seriblizbtion compbtibility with b previous
     * version of the type.
     */
    stbtic finbl long seriblVersionUID = 618058533534628008L;

    /**
     * Returns the public exponent.
     *
     * @return the public exponent.
     */
    public BigInteger getPublicExponent();

    /**
     * Returns the primeP.
     *
     * @return the primeP.
     */
    public BigInteger getPrimeP();

    /**
     * Returns the primeQ.
     *
     * @return the primeQ.
     */
    public BigInteger getPrimeQ();

    /**
     * Returns the primeExponentP.
     *
     * @return the primeExponentP.
     */
    public BigInteger getPrimeExponentP();

    /**
     * Returns the primeExponentQ.
     *
     * @return the primeExponentQ.
     */
    public BigInteger getPrimeExponentQ();

    /**
     * Returns the crtCoefficient.
     *
     * @return the crtCoefficient.
     */
    public BigInteger getCrtCoefficient();

    /**
     * Returns the otherPrimeInfo or null if there bre only
     * two prime fbctors (p bnd q).
     *
     * @return the otherPrimeInfo.
     */
    public RSAOtherPrimeInfo[] getOtherPrimeInfo();
}
