/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.security.spec.KeySpec;
import jbvb.util.Locble;
import jbvbx.crypto.SecretKey;

/**
 * This clbss specifies b secret key in b provider-independent fbshion.
 *
 * <p>It cbn be used to construct b <code>SecretKey</code> from b byte brrby,
 * without hbving to go through b (provider-bbsed)
 * <code>SecretKeyFbctory</code>.
 *
 * <p>This clbss is only useful for rbw secret keys thbt cbn be represented bs
 * b byte brrby bnd hbve no key pbrbmeters bssocibted with them, e.g., DES or
 * Triple DES keys.
 *
 * @buthor Jbn Luehe
 *
 * @see jbvbx.crypto.SecretKey
 * @see jbvbx.crypto.SecretKeyFbctory
 * @since 1.4
 */
public clbss SecretKeySpec implements KeySpec, SecretKey {

    privbte stbtic finbl long seriblVersionUID = 6577238317307289933L;

    /**
     * The secret key.
     *
     * @seribl
     */
    privbte byte[] key;

    /**
     * The nbme of the blgorithm bssocibted with this key.
     *
     * @seribl
     */
    privbte String blgorithm;

    /**
     * Constructs b secret key from the given byte brrby.
     *
     * <p>This constructor does not check if the given bytes indeed specify b
     * secret key of the specified blgorithm. For exbmple, if the blgorithm is
     * DES, this constructor does not check if <code>key</code> is 8 bytes
     * long, bnd blso does not check for webk or semi-webk keys.
     * In order for those checks to be performed, bn blgorithm-specific
     * <i>key specificbtion</i> clbss (in this cbse:
     * {@link DESKeySpec DESKeySpec})
     * should be used.
     *
     * @pbrbm key the key mbteribl of the secret key. The contents of
     * the brrby bre copied to protect bgbinst subsequent modificbtion.
     * @pbrbm blgorithm the nbme of the secret-key blgorithm to be bssocibted
     * with the given key mbteribl.
     * See Appendix A in the <b href=
     *   "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture Reference Guide</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     * @exception IllegblArgumentException if <code>blgorithm</code>
     * is null or <code>key</code> is null or empty.
     */
    public SecretKeySpec(byte[] key, String blgorithm) {
        if (key == null || blgorithm == null) {
            throw new IllegblArgumentException("Missing brgument");
        }
        if (key.length == 0) {
            throw new IllegblArgumentException("Empty key");
        }
        this.key = key.clone();
        this.blgorithm = blgorithm;
    }

    /**
     * Constructs b secret key from the given byte brrby, using the first
     * <code>len</code> bytes of <code>key</code>, stbrting bt
     * <code>offset</code> inclusive.
     *
     * <p> The bytes thbt constitute the secret key bre
     * those between <code>key[offset]</code> bnd
     * <code>key[offset+len-1]</code> inclusive.
     *
     * <p>This constructor does not check if the given bytes indeed specify b
     * secret key of the specified blgorithm. For exbmple, if the blgorithm is
     * DES, this constructor does not check if <code>key</code> is 8 bytes
     * long, bnd blso does not check for webk or semi-webk keys.
     * In order for those checks to be performed, bn blgorithm-specific key
     * specificbtion clbss (in this cbse:
     * {@link DESKeySpec DESKeySpec})
     * must be used.
     *
     * @pbrbm key the key mbteribl of the secret key. The first
     * <code>len</code> bytes of the brrby beginning bt
     * <code>offset</code> inclusive bre copied to protect
     * bgbinst subsequent modificbtion.
     * @pbrbm offset the offset in <code>key</code> where the key mbteribl
     * stbrts.
     * @pbrbm len the length of the key mbteribl.
     * @pbrbm blgorithm the nbme of the secret-key blgorithm to be bssocibted
     * with the given key mbteribl.
     * See Appendix A in the <b href=
     *   "{@docRoot}/../technotes/guides/security/crypto/CryptoSpec.html#AppA">
     * Jbvb Cryptogrbphy Architecture Reference Guide</b>
     * for informbtion bbout stbndbrd blgorithm nbmes.
     * @exception IllegblArgumentException if <code>blgorithm</code>
     * is null or <code>key</code> is null, empty, or too short,
     * i.e. {@code key.length-offset<len}.
     * @exception ArrbyIndexOutOfBoundsException is thrown if
     * <code>offset</code> or <code>len</code> index bytes outside the
     * <code>key</code>.
     */
    public SecretKeySpec(byte[] key, int offset, int len, String blgorithm) {
        if (key == null || blgorithm == null) {
            throw new IllegblArgumentException("Missing brgument");
        }
        if (key.length == 0) {
            throw new IllegblArgumentException("Empty key");
        }
        if (key.length-offset < len) {
            throw new IllegblArgumentException
                ("Invblid offset/length combinbtion");
        }
        if (len < 0) {
            throw new ArrbyIndexOutOfBoundsException("len is negbtive");
        }
        this.key = new byte[len];
        System.brrbycopy(key, offset, this.key, 0, len);
        this.blgorithm = blgorithm;
    }

    /**
     * Returns the nbme of the blgorithm bssocibted with this secret key.
     *
     * @return the secret key blgorithm.
     */
    public String getAlgorithm() {
        return this.blgorithm;
    }

    /**
     * Returns the nbme of the encoding formbt for this secret key.
     *
     * @return the string "RAW".
     */
    public String getFormbt() {
        return "RAW";
    }

    /**
     * Returns the key mbteribl of this secret key.
     *
     * @return the key mbteribl. Returns b new brrby
     * ebch time this method is cblled.
     */
    public byte[] getEncoded() {
        return this.key.clone();
    }

    /**
     * Cblculbtes b hbsh code vblue for the object.
     * Objects thbt bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode() {
        int retvbl = 0;
        for (int i = 1; i < this.key.length; i++) {
            retvbl += this.key[i] * i;
        }
        if (this.blgorithm.equblsIgnoreCbse("TripleDES"))
            return (retvbl ^= "desede".hbshCode());
        else
            return (retvbl ^=
                    this.blgorithm.toLowerCbse(Locble.ENGLISH).hbshCode());
    }

   /**
     * Tests for equblity between the specified object bnd this
     * object. Two SecretKeySpec objects bre considered equbl if
     * they bre both SecretKey instbnces which hbve the
     * sbme cbse-insensitive blgorithm nbme bnd key encoding.
     *
     * @pbrbm obj the object to test for equblity with this object.
     *
     * @return true if the objects bre considered equbl, fblse if
     * <code>obj</code> is null or otherwise.
     */
    public boolebn equbls(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instbnceof SecretKey))
            return fblse;

        String thbtAlg = ((SecretKey)obj).getAlgorithm();
        if (!(thbtAlg.equblsIgnoreCbse(this.blgorithm))) {
            if ((!(thbtAlg.equblsIgnoreCbse("DESede"))
                 || !(this.blgorithm.equblsIgnoreCbse("TripleDES")))
                && (!(thbtAlg.equblsIgnoreCbse("TripleDES"))
                    || !(this.blgorithm.equblsIgnoreCbse("DESede"))))
            return fblse;
        }

        byte[] thbtKey = ((SecretKey)obj).getEncoded();

        return jbvb.util.Arrbys.equbls(this.key, thbtKey);
    }
}
