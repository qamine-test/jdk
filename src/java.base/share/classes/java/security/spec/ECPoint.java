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
 * This immutbble clbss represents b point on bn elliptic curve (EC)
 * in bffine coordinbtes. Other coordinbte systems cbn
 * extend this clbss to represent this point in other
 * coordinbtes.
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss ECPoint {

    privbte finbl BigInteger x;
    privbte finbl BigInteger y;

    /**
     * This defines the point bt infinity.
     */
    public stbtic finbl ECPoint POINT_INFINITY = new ECPoint();

    // privbte constructor for constructing point bt infinity
    privbte ECPoint() {
        this.x = null;
        this.y = null;
    }

    /**
     * Crebtes bn ECPoint from the specified bffine x-coordinbte
     * {@code x} bnd bffine y-coordinbte {@code y}.
     * @pbrbm x the bffine x-coordinbte.
     * @pbrbm y the bffine y-coordinbte.
     * @exception NullPointerException if {@code x} or
     * {@code y} is null.
     */
    public ECPoint(BigInteger x, BigInteger y) {
        if ((x==null) || (y==null)) {
            throw new NullPointerException("bffine coordinbte x or y is null");
        }
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the bffine x-coordinbte {@code x}.
     * Note: POINT_INFINITY hbs b null bffine x-coordinbte.
     * @return the bffine x-coordinbte.
     */
    public BigInteger getAffineX() {
        return x;
    }

    /**
     * Returns the bffine y-coordinbte {@code y}.
     * Note: POINT_INFINITY hbs b null bffine y-coordinbte.
     * @return the bffine y-coordinbte.
     */
    public BigInteger getAffineY() {
        return y;
    }

    /**
     * Compbres this elliptic curve point for equblity with
     * the specified object.
     * @pbrbm obj the object to be compbred.
     * @return true if {@code obj} is bn instbnce of
     * ECPoint bnd the bffine coordinbtes mbtch, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (this == obj) return true;
        if (this == POINT_INFINITY) return fblse;
        if (obj instbnceof ECPoint) {
            return ((x.equbls(((ECPoint)obj).x)) &&
                    (y.equbls(((ECPoint)obj).y)));
        }
        return fblse;
    }

    /**
     * Returns b hbsh code vblue for this elliptic curve point.
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        if (this == POINT_INFINITY) return 0;
        return x.hbshCode() << 5 + y.hbshCode();
    }
}
