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

/**
 * This immutbble clbss specifies bn elliptic curve public key with
 * its bssocibted pbrbmeters.
 *
 * @see KeySpec
 * @see ECPoint
 * @see ECPbrbmeterSpec
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss ECPublicKeySpec implements KeySpec {

    privbte ECPoint w;
    privbte ECPbrbmeterSpec pbrbms;

    /**
     * Crebtes b new ECPublicKeySpec with the specified
     * pbrbmeter vblues.
     * @pbrbm w the public point.
     * @pbrbm pbrbms the bssocibted elliptic curve dombin
     * pbrbmeters.
     * @exception NullPointerException if {@code w}
     * or {@code pbrbms} is null.
     * @exception IllegblArgumentException if {@code w}
     * is point bt infinity, i.e. ECPoint.POINT_INFINITY
     */
    public ECPublicKeySpec(ECPoint w, ECPbrbmeterSpec pbrbms) {
        if (w == null) {
            throw new NullPointerException("w is null");
        }
        if (pbrbms == null) {
            throw new NullPointerException("pbrbms is null");
        }
        if (w == ECPoint.POINT_INFINITY) {
            throw new IllegblArgumentException("w is ECPoint.POINT_INFINITY");
        }
        this.w = w;
        this.pbrbms = pbrbms;
    }

    /**
     * Returns the public point W.
     * @return the public point W.
     */
    public ECPoint getW() {
        return w;
    }

    /**
     * Returns the bssocibted elliptic curve dombin
     * pbrbmeters.
     * @return the EC dombin pbrbmeters.
     */
    public ECPbrbmeterSpec getPbrbms() {
        return pbrbms;
    }
}
