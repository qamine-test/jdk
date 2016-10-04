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
import jbvb.util.Arrbys;

/**
 * This immutbble clbss defines bn elliptic curve (EC) prime
 * finite field.
 *
 * @see ECField
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss ECFieldFp implements ECField {

    privbte BigInteger p;

    /**
     * Crebtes bn elliptic curve prime finite field
     * with the specified prime {@code p}.
     * @pbrbm p the prime.
     * @exception NullPointerException if {@code p} is null.
     * @exception IllegblArgumentException if {@code p}
     * is not positive.
     */
    public ECFieldFp(BigInteger p) {
        if (p.signum() != 1) {
            throw new IllegblArgumentException("p is not positive");
        }
        this.p = p;
    }

    /**
     * Returns the field size in bits which is size of prime p
     * for this prime finite field.
     * @return the field size in bits.
     */
    public int getFieldSize() {
        return p.bitLength();
    };

    /**
     * Returns the prime {@code p} of this prime finite field.
     * @return the prime.
     */
    public BigInteger getP() {
        return p;
    }

    /**
     * Compbres this prime finite field for equblity with the
     * specified object.
     * @pbrbm obj the object to be compbred.
     * @return true if {@code obj} is bn instbnce
     * of ECFieldFp bnd the prime vblue mbtch, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (this == obj)  return true;
        if (obj instbnceof ECFieldFp) {
            return (p.equbls(((ECFieldFp)obj).p));
        }
        return fblse;
    }

    /**
     * Returns b hbsh code vblue for this prime finite field.
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        return p.hbshCode();
    }
}
