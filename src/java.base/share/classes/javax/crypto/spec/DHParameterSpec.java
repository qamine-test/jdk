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
 * This clbss specifies the set of pbrbmeters used with the Diffie-Hellmbn
 * blgorithm, bs specified in PKCS #3: <i>Diffie-Hellmbn Key-Agreement
 * Stbndbrd</i>.
 *
 * <p>A centrbl buthority generbtes pbrbmeters bnd gives them to the two
 * entities seeking to generbte b secret key. The pbrbmeters bre b prime
 * <code>p</code>, b bbse <code>g</code>, bnd optionblly the length
 * in bits of the privbte vblue, <code>l</code>.
 *
 * <p>It is possible thbt more thbn one instbnce of pbrbmeters mby be
 * generbted by b given centrbl buthority, bnd thbt there mby be more thbn
 * one centrbl buthority. Indeed, ebch individubl mby be its own centrbl
 * buthority, with different entities hbving different pbrbmeters.
 *
 * <p>Note thbt this clbss does not perform bny vblidbtion on specified
 * pbrbmeters. Thus, the specified vblues bre returned directly even
 * if they bre null.
 *
 * @buthor Jbn Luehe
 *
 * @see jbvbx.crypto.KeyAgreement
 * @since 1.4
 */
public clbss DHPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    // The prime modulus
    privbte BigInteger p;

    // The bbse generbtor
    privbte BigInteger g;

    // The size in bits of the rbndom exponent (privbte vblue) (optionbl)
    privbte int l;

    /**
     * Constructs b pbrbmeter set for Diffie-Hellmbn, using b prime modulus
     * <code>p</code> bnd b bbse generbtor <code>g</code>.
     *
     * @pbrbm p the prime modulus
     * @pbrbm g the bbse generbtor
     */
    public DHPbrbmeterSpec(BigInteger p, BigInteger g) {
        this.p = p;
        this.g = g;
        this.l = 0;
    }

    /**
     * Constructs b pbrbmeter set for Diffie-Hellmbn, using b prime modulus
     * <code>p</code>, b bbse generbtor <code>g</code>,
     * bnd the size in bits, <code>l</code>, of the rbndom exponent
     * (privbte vblue).
     *
     * @pbrbm p the prime modulus
     * @pbrbm g the bbse generbtor
     * @pbrbm l the size in bits of the rbndom exponent (privbte vblue)
     */
    public DHPbrbmeterSpec(BigInteger p, BigInteger g, int l) {
        this.p = p;
        this.g = g;
        this.l = l;
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

    /**
     * Returns the size in bits, <code>l</code>, of the rbndom exponent
     * (privbte vblue).
     *
     * @return the size in bits, <code>l</code>, of the rbndom exponent
     * (privbte vblue), or 0 if this size hbs not been set
     */
    public int getL() {
        return this.l;
    }
}
