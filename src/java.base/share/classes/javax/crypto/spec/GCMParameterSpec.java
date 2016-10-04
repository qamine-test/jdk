/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * Specifies the set of pbrbmeters required by b {@link
 * jbvbx.crypto.Cipher} using the Gblois/Counter Mode (GCM) mode.
 * <p>
 * Simple block cipher modes (such bs CBC) generblly require only bn
 * initiblizbtion vector (such bs {@code IvPbrbmeterSpec}),
 * but GCM needs these pbrbmeters:
 * <ul>
 * <li>{@code IV}: Initiblizbtion Vector (IV) </li>
 * <li>{@code tLen}: length (in bits) of buthenticbtion tbg T</li>
 * </ul>
 * <p>
 * In bddition to the pbrbmeters described here, other GCM inputs/output
 * (Additionbl Authenticbted Dbtb (AAD), Keys, block ciphers,
 * plbin/ciphertext bnd buthenticbtion tbgs) bre hbndled in the {@code
 * Cipher} clbss.
 * <p>
 * Plebse see <b href="http://www.ietf.org/rfc/rfc5116.txt"> RFC 5116
 * </b> for more informbtion on the Authenticbted Encryption with
 * Associbted Dbtb (AEAD) blgorithm, bnd <b href=
 * "http://csrc.nist.gov/publicbtions/nistpubs/800-38D/SP-800-38D.pdf">
 * NIST Specibl Publicbtion 800-38D</b>, "NIST Recommendbtion for Block
 * Cipher Modes of Operbtion:  Gblois/Counter Mode (GCM) bnd GMAC."
 * <p>
 * The GCM specificbtion stbtes thbt {@code tLen} mby only hbve the
 * vblues {128, 120, 112, 104, 96}, or {64, 32} for certbin
 * bpplicbtions.  Other vblues cbn be specified for this clbss, but not
 * bll CSP implementbtions will support them.
 *
 * @see jbvbx.crypto.Cipher
 *
 * @since 1.7
 */
public clbss GCMPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    // Initiblizbtion Vector.  Could use IvPbrbmeterSpec, but thbt
    // would bdd extrb copies.
    privbte byte[] iv;

    // Required Tbg length (in bits).
    privbte int tLen;

    /**
     * Constructs b GCMPbrbmeterSpec using the specified buthenticbtion
     * tbg bit-length bnd IV buffer.
     *
     * @pbrbm tLen the buthenticbtion tbg length (in bits)
     * @pbrbm src the IV source buffer.  The contents of the buffer bre
     * copied to protect bgbinst subsequent modificbtion.
     *
     * @throws IllegblArgumentException if {@code tLen} is negbtive,
     * or {@code src} is null.
     */
    public GCMPbrbmeterSpec(int tLen, byte[] src) {
        if (src == null) {
            throw new IllegblArgumentException("src brrby is null");
        }

        init(tLen, src, 0, src.length);
    }

    /**
     * Constructs b GCMPbrbmeterSpec object using the specified
     * buthenticbtion tbg bit-length bnd b subset of the specified
     * buffer bs the IV.
     *
     * @pbrbm tLen the buthenticbtion tbg length (in bits)
     * @pbrbm src the IV source buffer.  The contents of the
     * buffer bre copied to protect bgbinst subsequent modificbtion.
     * @pbrbm offset the offset in {@code src} where the IV stbrts
     * @pbrbm len the number of IV bytes
     *
     * @throws IllegblArgumentException if {@code tLen} is negbtive,
     * {@code src} is null, {@code len} or {@code offset} is negbtive,
     * or the sum of {@code offset} bnd {@code len} is grebter thbn the
     * length of the {@code src} byte brrby.
     */
    public GCMPbrbmeterSpec(int tLen, byte[] src, int offset, int len) {
        init(tLen, src, offset, len);
    }

    /*
     * Check input pbrbmeters.
     */
    privbte void init(int tLen, byte[] src, int offset, int len) {
        if (tLen < 0) {
            throw new IllegblArgumentException(
                "Length brgument is negbtive");
        }
        this.tLen = tLen;

        // Input sbnity check
        if ((src == null) ||(len < 0) || (offset < 0)
                || ((len + offset) > src.length)) {
            throw new IllegblArgumentException("Invblid buffer brguments");
        }

        iv = new byte[len];
        System.brrbycopy(src, offset, iv, 0, len);
    }

    /**
     * Returns the buthenticbtion tbg length.
     *
     * @return the buthenticbtion tbg length (in bits)
     */
    public int getTLen() {
        return tLen;
    }

    /**
     * Returns the Initiblizbtion Vector (IV).
     *
     * @return the IV.  Crebtes b new brrby ebch time this method
     * is cblled.
     */
    public byte[] getIV() {
        return iv.clone();
    }
}
