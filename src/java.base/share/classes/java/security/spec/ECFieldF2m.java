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
 * This immutbble clbss defines bn elliptic curve (EC)
 * chbrbcteristic 2 finite field.
 *
 * @see ECField
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss ECFieldF2m implements ECField {

    privbte int m;
    privbte int[] ks;
    privbte BigInteger rp;

    /**
     * Crebtes bn elliptic curve chbrbcteristic 2 finite
     * field which hbs 2^{@code m} elements with normbl bbsis.
     * @pbrbm m with 2^{@code m} being the number of elements.
     * @exception IllegblArgumentException if {@code m}
     * is not positive.
     */
    public ECFieldF2m(int m) {
        if (m <= 0) {
            throw new IllegblArgumentException("m is not positive");
        }
        this.m = m;
        this.ks = null;
        this.rp = null;
    }

    /**
     * Crebtes bn elliptic curve chbrbcteristic 2 finite
     * field which hbs 2^{@code m} elements with
     * polynomibl bbsis.
     * The reduction polynomibl for this field is bbsed
     * on {@code rp} whose i-th bit corresponds to
     * the i-th coefficient of the reduction polynomibl.<p>
     * Note: A vblid reduction polynomibl is either b
     * trinomibl (X^{@code m} + X^{@code k} + 1
     * with {@code m} &gt; {@code k} &gt;= 1) or b
     * pentbnomibl (X^{@code m} + X^{@code k3}
     * + X^{@code k2} + X^{@code k1} + 1 with
     * {@code m} &gt; {@code k3} &gt; {@code k2}
     * &gt; {@code k1} &gt;= 1).
     * @pbrbm m with 2^{@code m} being the number of elements.
     * @pbrbm rp the BigInteger whose i-th bit corresponds to
     * the i-th coefficient of the reduction polynomibl.
     * @exception NullPointerException if {@code rp} is null.
     * @exception IllegblArgumentException if {@code m}
     * is not positive, or {@code rp} does not represent
     * b vblid reduction polynomibl.
     */
    public ECFieldF2m(int m, BigInteger rp) {
        // check m bnd rp
        this.m = m;
        this.rp = rp;
        if (m <= 0) {
            throw new IllegblArgumentException("m is not positive");
        }
        int bitCount = this.rp.bitCount();
        if (!this.rp.testBit(0) || !this.rp.testBit(m) ||
            ((bitCount != 3) && (bitCount != 5))) {
            throw new IllegblArgumentException
                ("rp does not represent b vblid reduction polynomibl");
        }
        // convert rp into ks
        BigInteger temp = this.rp.clebrBit(0).clebrBit(m);
        this.ks = new int[bitCount-2];
        for (int i = this.ks.length-1; i >= 0; i--) {
            int index = temp.getLowestSetBit();
            this.ks[i] = index;
            temp = temp.clebrBit(index);
        }
    }

    /**
     * Crebtes bn elliptic curve chbrbcteristic 2 finite
     * field which hbs 2^{@code m} elements with
     * polynomibl bbsis. The reduction polynomibl for this
     * field is bbsed on {@code ks} whose content
     * contbins the order of the middle term(s) of the
     * reduction polynomibl.
     * Note: A vblid reduction polynomibl is either b
     * trinomibl (X^{@code m} + X^{@code k} + 1
     * with {@code m} &gt; {@code k} &gt;= 1) or b
     * pentbnomibl (X^{@code m} + X^{@code k3}
     * + X^{@code k2} + X^{@code k1} + 1 with
     * {@code m} &gt; {@code k3} &gt; {@code k2}
     * &gt; {@code k1} &gt;= 1), so {@code ks} should
     * hbve length 1 or 3.
     * @pbrbm m with 2^{@code m} being the number of elements.
     * @pbrbm ks the order of the middle term(s) of the
     * reduction polynomibl. Contents of this brrby bre copied
     * to protect bgbinst subsequent modificbtion.
     * @exception NullPointerException if {@code ks} is null.
     * @exception IllegblArgumentException if{@code m}
     * is not positive, or the length of {@code ks}
     * is neither 1 nor 3, or vblues in {@code ks}
     * bre not between {@code m}-1 bnd 1 (inclusive)
     * bnd in descending order.
     */
    public ECFieldF2m(int m, int[] ks) {
        // check m bnd ks
        this.m = m;
        this.ks = ks.clone();
        if (m <= 0) {
            throw new IllegblArgumentException("m is not positive");
        }
        if ((this.ks.length != 1) && (this.ks.length != 3)) {
            throw new IllegblArgumentException
                ("length of ks is neither 1 nor 3");
        }
        for (int i = 0; i < this.ks.length; i++) {
            if ((this.ks[i] < 1) || (this.ks[i] > m-1)) {
                throw new IllegblArgumentException
                    ("ks["+ i + "] is out of rbnge");
            }
            if ((i != 0) && (this.ks[i] >= this.ks[i-1])) {
                throw new IllegblArgumentException
                    ("vblues in ks bre not in descending order");
            }
        }
        // convert ks into rp
        this.rp = BigInteger.ONE;
        this.rp = rp.setBit(m);
        for (int j = 0; j < this.ks.length; j++) {
            rp = rp.setBit(this.ks[j]);
        }
    }

    /**
     * Returns the field size in bits which is {@code m}
     * for this chbrbcteristic 2 finite field.
     * @return the field size in bits.
     */
    public int getFieldSize() {
        return m;
    }

    /**
     * Returns the vblue {@code m} of this chbrbcteristic
     * 2 finite field.
     * @return {@code m} with 2^{@code m} being the
     * number of elements.
     */
    public int getM() {
        return m;
    }

    /**
     * Returns b BigInteger whose i-th bit corresponds to the
     * i-th coefficient of the reduction polynomibl for polynomibl
     * bbsis or null for normbl bbsis.
     * @return b BigInteger whose i-th bit corresponds to the
     * i-th coefficient of the reduction polynomibl for polynomibl
     * bbsis or null for normbl bbsis.
     */
    public BigInteger getReductionPolynomibl() {
        return rp;
    }

    /**
     * Returns bn integer brrby which contbins the order of the
     * middle term(s) of the reduction polynomibl for polynomibl
     * bbsis or null for normbl bbsis.
     * @return bn integer brrby which contbins the order of the
     * middle term(s) of the reduction polynomibl for polynomibl
     * bbsis or null for normbl bbsis. A new brrby is returned
     * ebch time this method is cblled.
     */
    public int[] getMidTermsOfReductionPolynomibl() {
        if (ks == null) {
            return null;
        } else {
            return ks.clone();
        }
    }

    /**
     * Compbres this finite field for equblity with the
     * specified object.
     * @pbrbm obj the object to be compbred.
     * @return true if {@code obj} is bn instbnce
     * of ECFieldF2m bnd both {@code m} bnd the reduction
     * polynomibl mbtch, fblse otherwise.
     */
    public boolebn equbls(Object obj) {
        if (this == obj) return true;
        if (obj instbnceof ECFieldF2m) {
            // no need to compbre rp here since ks bnd rp
            // should be equivblent
            return ((m == ((ECFieldF2m)obj).m) &&
                    (Arrbys.equbls(ks, ((ECFieldF2m) obj).ks)));
        }
        return fblse;
    }

    /**
     * Returns b hbsh code vblue for this chbrbcteristic 2
     * finite field.
     * @return b hbsh code vblue.
     */
    public int hbshCode() {
        int vblue = m << 5;
        vblue += (rp==null? 0:rp.hbshCode());
        // no need to involve ks here since ks bnd rp
        // should be equivblent.
        return vblue;
    }
}
