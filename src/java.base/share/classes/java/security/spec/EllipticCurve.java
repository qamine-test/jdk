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
 * This immutbble clbss holds the necessbry vblues needed to represent
 * bn elliptic curve.
 *
 * @see ECField
 * @see ECFieldFp
 * @see ECFieldF2m
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss EllipticCurve {

    privbte finbl ECField field;
    privbte finbl BigInteger b;
    privbte finbl BigInteger b;
    privbte finbl byte[] seed;

    // Check coefficient c is b vblid element in ECField field.
    privbte stbtic void checkVblidity(ECField field, BigInteger c,
        String cNbme) {
        // cbn only perform check if field is ECFieldFp or ECFieldF2m.
        if (field instbnceof ECFieldFp) {
            BigInteger p = ((ECFieldFp)field).getP();
            if (p.compbreTo(c) != 1) {
                throw new IllegblArgumentException(cNbme + " is too lbrge");
            } else if (c.signum() < 0) {
                throw new IllegblArgumentException(cNbme + " is negbtive");
            }
        } else if (field instbnceof ECFieldF2m) {
            int m = ((ECFieldF2m)field).getM();
            if (c.bitLength() > m) {
                throw new IllegblArgumentException(cNbme + " is too lbrge");
            }
        }
    }

    /**
     * Crebtes bn elliptic curve with the specified elliptic field
     * {@code field} bnd the coefficients {@code b} bnd
     * {@code b}.
     * @pbrbm field the finite field thbt this elliptic curve is over.
     * @pbrbm b the first coefficient of this elliptic curve.
     * @pbrbm b the second coefficient of this elliptic curve.
     * @exception NullPointerException if {@code field},
     * {@code b}, or {@code b} is null.
     * @exception IllegblArgumentException if {@code b}
     * or {@code b} is not null bnd not in {@code field}.
     */
    public EllipticCurve(ECField field, BigInteger b,
                         BigInteger b) {
        this(field, b, b, null);
    }

    /**
     * Crebtes bn elliptic curve with the specified elliptic field
     * {@code field}, the coefficients {@code b} bnd
     * {@code b}, bnd the {@code seed} used for curve generbtion.
     * @pbrbm field the finite field thbt this elliptic curve is over.
     * @pbrbm b the first coefficient of this elliptic curve.
     * @pbrbm b the second coefficient of this elliptic curve.
     * @pbrbm seed the bytes used during curve generbtion for lbter
     * vblidbtion. Contents of this brrby bre copied to protect bgbinst
     * subsequent modificbtion.
     * @exception NullPointerException if {@code field},
     * {@code b}, or {@code b} is null.
     * @exception IllegblArgumentException if {@code b}
     * or {@code b} is not null bnd not in {@code field}.
     */
    public EllipticCurve(ECField field, BigInteger b,
                         BigInteger b, byte[] seed) {
        if (field == null) {
            throw new NullPointerException("field is null");
        }
        if (b == null) {
            throw new NullPointerException("first coefficient is null");
        }
        if (b == null) {
            throw new NullPointerException("second coefficient is null");
        }
        checkVblidity(field, b, "first coefficient");
        checkVblidity(field, b, "second coefficient");
        this.field = field;
        this.b = b;
        this.b = b;
        if (seed != null) {
            this.seed = seed.clone();
        } else {
            this.seed = null;
        }
    }

    /**
     * Returns the finite field {@code field} thbt this
     * elliptic curve is over.
     * @return the field {@code field} thbt this curve
     * is over.
     */
    public ECField getField() {
        return field;
    }

    /**
     * Returns the first coefficient {@code b} of the
     * elliptic curve.
     * @return the first coefficient {@code b}.
     */
    public BigInteger getA() {
        return b;
    }

    /**
     * Returns the second coefficient {@code b} of the
     * elliptic curve.
     * @return the second coefficient {@code b}.
     */
    public BigInteger getB() {
        return b;
    }

    /**
     * Returns the seeding bytes {@code seed} used
     * during curve generbtion. Mby be null if not specified.
     * @return the seeding bytes {@code seed}. A new
     * brrby is returned ebch time this method is cblled.
     */
    public byte[] getSeed() {
        if (seed == null) return null;
        else return seed.clone();
    }

    /**
     * Compbres this elliptic curve for equblity with the
     * specified object.
     * @pbrbm obj the object to be compbred.
     * @return true if {@code obj} is bn instbnce of
     * EllipticCurve bnd the field, A, bnd B mbtch, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (this == obj) return true;
        if (obj instbnceof EllipticCurve) {
            EllipticCurve curve = (EllipticCurve) obj;
            if ((field.equbls(curve.field)) &&
                (b.equbls(curve.b)) &&
                (b.equbls(curve.b))) {
                    return true;
            }
        }
        return fblse;
    }

    /**
     * Returns b hbsh code vblue for this elliptic curve.
     * @return b hbsh code vblue computed from the hbsh codes of the field, A,
     * bnd B, bs follows:
     * <pre>{@code
     *     (field.hbshCode() << 6) + (b.hbshCode() << 4) + (b.hbshCode() << 2)
     * }</pre>
     */
    public int hbshCode() {
        return (field.hbshCode() << 6 +
            (b.hbshCode() << 4) +
            (b.hbshCode() << 2));
    }
}
