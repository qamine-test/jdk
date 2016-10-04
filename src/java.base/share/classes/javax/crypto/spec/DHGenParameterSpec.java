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

pbckbge jbvbx.crypto.spec;

import jbvb.mbth.BigInteger;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss specifies the set of pbrbmeters used for generbting
 * Diffie-Hellmbn (system) pbrbmeters for use in Diffie-Hellmbn key
 * bgreement. This is typicblly done by b centrbl
 * buthority.
 *
 * <p> The centrbl buthority, bfter computing the pbrbmeters, must send this
 * informbtion to the pbrties looking to bgree on b secret key.
 *
 * @buthor Jbn Luehe
 *
 * @see DHPbrbmeterSpec
 * @since 1.4
 */
public clbss DHGenPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    // The size in bits of the prime modulus
    privbte int primeSize;

    // The size in bits of the rbndom exponent (privbte vblue)
    privbte int exponentSize;

    /**
     * Constructs b pbrbmeter set for the generbtion of Diffie-Hellmbn
     * (system) pbrbmeters. The constructed pbrbmeter set cbn be used to
     * initiblize bn
     * {@link jbvb.security.AlgorithmPbrbmeterGenerbtor AlgorithmPbrbmeterGenerbtor}
     * object for the generbtion of Diffie-Hellmbn pbrbmeters.
     *
     * @pbrbm primeSize the size (in bits) of the prime modulus.
     * @pbrbm exponentSize the size (in bits) of the rbndom exponent.
     */
    public DHGenPbrbmeterSpec(int primeSize, int exponentSize) {
        this.primeSize = primeSize;
        this.exponentSize = exponentSize;
    }

    /**
     * Returns the size in bits of the prime modulus.
     *
     * @return the size in bits of the prime modulus
     */
    public int getPrimeSize() {
        return this.primeSize;
    }

    /**
     * Returns the size in bits of the rbndom exponent (privbte vblue).
     *
     * @return the size in bits of the rbndom exponent (privbte vblue)
     */
    public int getExponentSize() {
        return this.exponentSize;
    }
}
