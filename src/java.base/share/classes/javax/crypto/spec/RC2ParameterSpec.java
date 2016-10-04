/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss specifies the pbrbmeters used with the
 * <b href="http://www.ietf.org/rfc/rfc2268.txt"><i>RC2</i></b>
 * blgorithm.
 *
 * <p> The pbrbmeters consist of bn effective key size bnd optionblly
 * bn 8-byte initiblizbtion vector (IV) (only in feedbbck mode).
 *
 * <p> This clbss cbn be used to initiblize b <code>Cipher</code> object thbt
 * implements the <i>RC2</i> blgorithm.
 *
 * @buthor Jbn Luehe
 *
 * @since 1.4
 */
public clbss RC2PbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte byte[] iv = null;
    privbte int effectiveKeyBits;

    /**
     * Constructs b pbrbmeter set for RC2 from the given effective key size
     * (in bits).
     *
     * @pbrbm effectiveKeyBits the effective key size in bits.
     */
    public RC2PbrbmeterSpec(int effectiveKeyBits) {
        this.effectiveKeyBits = effectiveKeyBits;
    }

    /**
     * Constructs b pbrbmeter set for RC2 from the given effective key size
     * (in bits) bnd bn 8-byte IV.
     *
     * <p> The bytes thbt constitute the IV bre those between
     * <code>iv[0]</code> bnd <code>iv[7]</code> inclusive.
     *
     * @pbrbm effectiveKeyBits the effective key size in bits.
     * @pbrbm iv the buffer with the 8-byte IV. The first 8 bytes of
     * the buffer bre copied to protect bgbinst subsequent modificbtion.
     * @exception IllegblArgumentException if <code>iv</code> is null.
     */
    public RC2PbrbmeterSpec(int effectiveKeyBits, byte[] iv) {
        this(effectiveKeyBits, iv, 0);
    }

    /**
     * Constructs b pbrbmeter set for RC2 from the given effective key size
     * (in bits) bnd IV.
     *
     * <p> The IV is tbken from <code>iv</code>, stbrting bt
     * <code>offset</code> inclusive.
     * The bytes thbt constitute the IV bre those between
     * <code>iv[offset]</code> bnd <code>iv[offset+7]</code> inclusive.
     *
     * @pbrbm effectiveKeyBits the effective key size in bits.
     * @pbrbm iv the buffer with the IV. The first 8 bytes
     * of the buffer beginning bt <code>offset</code> inclusive
     * bre copied to protect bgbinst subsequent modificbtion.
     * @pbrbm offset the offset in <code>iv</code> where the 8-byte IV
     * stbrts.
     * @exception IllegblArgumentException if <code>iv</code> is null.
     */
    public RC2PbrbmeterSpec(int effectiveKeyBits, byte[] iv, int offset) {
        this.effectiveKeyBits = effectiveKeyBits;
        if (iv == null) throw new IllegblArgumentException("IV missing");
        int blockSize = 8;
        if (iv.length - offset < blockSize) {
            throw new IllegblArgumentException("IV too short");
        }
        this.iv = new byte[blockSize];
        System.brrbycopy(iv, offset, this.iv, 0, blockSize);
    }

    /**
     * Returns the effective key size in bits.
     *
     * @return the effective key size in bits.
     */
    public int getEffectiveKeyBits() {
        return this.effectiveKeyBits;
    }

    /**
     * Returns the IV or null if this pbrbmeter set does not contbin bn IV.
     *
     * @return the IV or null if this pbrbmeter set does not contbin bn IV.
     * Returns b new brrby ebch time this method is cblled.
     */
    public byte[] getIV() {
        return (iv == null? null:iv.clone());
    }

   /**
     * Tests for equblity between the specified object bnd this
     * object. Two RC2PbrbmeterSpec objects bre considered equbl if their
     * effective key sizes bnd IVs bre equbl.
     * (Two IV references bre considered equbl if both bre <tt>null</tt>.)
     *
     * @pbrbm obj the object to test for equblity with this object.
     *
     * @return true if the objects bre considered equbl, fblse if
     * <code>obj</code> is null or otherwise.
     */
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instbnceof RC2PbrbmeterSpec)) {
            return fblse;
        }
        RC2PbrbmeterSpec other = (RC2PbrbmeterSpec) obj;

        return ((effectiveKeyBits == other.effectiveKeyBits) &&
                jbvb.util.Arrbys.equbls(iv, other.iv));
    }

    /**
     * Cblculbtes b hbsh code vblue for the object.
     * Objects thbt bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        int retvbl = 0;
        if (iv != null) {
            for (int i = 1; i < iv.length; i++) {
                retvbl += iv[i] * i;
            }
        }
        return (retvbl += effectiveKeyBits);
    }
}
