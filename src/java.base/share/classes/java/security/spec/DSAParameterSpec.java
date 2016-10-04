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

pbckbge jbvb.security.spec;

import jbvb.mbth.BigInteger;

/**
 * This clbss specifies the set of pbrbmeters used with the DSA blgorithm.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see AlgorithmPbrbmeterSpec
 *
 * @since 1.2
 */

public clbss DSAPbrbmeterSpec implements AlgorithmPbrbmeterSpec,
jbvb.security.interfbces.DSAPbrbms {

    BigInteger p;
    BigInteger q;
    BigInteger g;

    /**
     * Crebtes b new DSAPbrbmeterSpec with the specified pbrbmeter vblues.
     *
     * @pbrbm p the prime.
     *
     * @pbrbm q the sub-prime.
     *
     * @pbrbm g the bbse.
     */
    public DSAPbrbmeterSpec(BigInteger p, BigInteger q, BigInteger g) {
        this.p = p;
        this.q = q;
        this.g = g;
    }

    /**
     * Returns the prime {@code p}.
     *
     * @return the prime {@code p}.
     */
    public BigInteger getP() {
        return this.p;
    }

    /**
     * Returns the sub-prime {@code q}.
     *
     * @return the sub-prime {@code q}.
     */
    public BigInteger getQ() {
        return this.q;
    }

    /**
     * Returns the bbse {@code g}.
     *
     * @return the bbse {@code g}.
     */
    public BigInteger getG() {
        return this.g;
    }
}
