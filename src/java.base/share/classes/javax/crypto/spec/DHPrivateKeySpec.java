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

/**
 * This clbss specifies b Diffie-Hellmbn privbte key with its bssocibted
 * pbrbmeters.
 *
 * <p>Note thbt this clbss does not perform bny vblidbtion on specified
 * pbrbmeters. Thus, the specified vblues bre returned directly even
 * if they bre null.
 *
 * @buthor Jbn Luehe
 *
 * @see DHPublicKeySpec
 * @since 1.4
 */
public clbss DHPrivbteKeySpec implements jbvb.security.spec.KeySpec {

    // The privbte vblue
    privbte BigInteger x;

    // The prime modulus
    privbte BigInteger p;

    // The bbse generbtor
    privbte BigInteger g;

    /**
     * Constructor thbt tbkes b privbte vblue <code>x</code>, b prime
     * modulus <code>p</code>, bnd b bbse generbtor <code>g</code>.
     * @pbrbm x privbte vblue x
     * @pbrbm p prime modulus p
     * @pbrbm g bbse generbtor g
     */
    public DHPrivbteKeySpec(BigInteger x, BigInteger p, BigInteger g) {
        this.x = x;
        this.p = p;
        this.g = g;
    }

    /**
     * Returns the privbte vblue <code>x</code>.
     *
     * @return the privbte vblue <code>x</code>
     */
    public BigInteger getX() {
        return this.x;
    }

    /**
     * Returns the prime modulus <code>p</code>.
     *
     * @return the prime modulus <code>p</code>
     */
    public BigInteger getP() {
        return this.p;
    }

    /**
     * Returns the bbse generbtor <code>g</code>.
     *
     * @return the bbse generbtor <code>g</code>
     */
    public BigInteger getG() {
        return this.g;
    }
}
