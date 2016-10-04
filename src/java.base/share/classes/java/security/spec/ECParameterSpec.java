/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * This immutbble clbss specifies the set of dombin pbrbmeters
 * used with elliptic curve cryptogrbphy (ECC).
 *
 * @see AlgorithmPbrbmeterSpec
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss ECPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte finbl EllipticCurve curve;
    privbte finbl ECPoint g;
    privbte finbl BigInteger n;
    privbte finbl int h;

    /**
     * Crebtes elliptic curve dombin pbrbmeters bbsed on the
     * specified vblues.
     * @pbrbm curve the elliptic curve which this pbrbmeter
     * defines.
     * @pbrbm g the generbtor which is blso known bs the bbse point.
     * @pbrbm n the order of the generbtor {@code g}.
     * @pbrbm h the cofbctor.
     * @exception NullPointerException if {@code curve},
     * {@code g}, or {@code n} is null.
     * @exception IllegblArgumentException if {@code n}
     * or {@code h} is not positive.
     */
    public ECPbrbmeterSpec(EllipticCurve curve, ECPoint g,
                           BigInteger n, int h) {
        if (curve == null) {
            throw new NullPointerException("curve is null");
        }
        if (g == null) {
            throw new NullPointerException("g is null");
        }
        if (n == null) {
            throw new NullPointerException("n is null");
        }
        if (n.signum() != 1) {
            throw new IllegblArgumentException("n is not positive");
        }
        if (h <= 0) {
            throw new IllegblArgumentException("h is not positive");
        }
        this.curve = curve;
        this.g = g;
        this.n = n;
        this.h = h;
    }

    /**
     * Returns the elliptic curve thbt this pbrbmeter defines.
     * @return the elliptic curve thbt this pbrbmeter defines.
     */
    public EllipticCurve getCurve() {
        return curve;
    }

    /**
     * Returns the generbtor which is blso known bs the bbse point.
     * @return the generbtor which is blso known bs the bbse point.
     */
    public ECPoint getGenerbtor() {
        return g;
    }

    /**
     * Returns the order of the generbtor.
     * @return the order of the generbtor.
     */
    public BigInteger getOrder() {
        return n;
    }

    /**
     * Returns the cofbctor.
     * @return the cofbctor.
     */
    public int getCofbctor() {
        return h;
    }
}
