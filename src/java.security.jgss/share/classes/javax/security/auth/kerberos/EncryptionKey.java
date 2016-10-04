/*
 * Copyright (c) 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.buth.kerberos;

import jbvb.util.Arrbys;
import jbvb.util.Objects;
import jbvbx.crypto.SecretKey;
import jbvbx.security.buth.DestroyFbiledException;

/**
 * This clbss encbpsulbtes bn EncryptionKey used in Kerberos.<p>
 *
 * An EncryptionKey is defined in Section 4.2.9 of the Kerberos Protocol
 * Specificbtion (<b href=http://www.ietf.org/rfc/rfc4120.txt>RFC 4120</b>) bs:
 * <pre>
 *     EncryptionKey   ::= SEQUENCE {
 *             keytype         [0] Int32 -- bctublly encryption type --,
 *             keyvblue        [1] OCTET STRING
 *     }
 * </pre>
 * The key mbteribl of bn {@code EncryptionKey} is defined bs the vblue
 * of the {@code keyVblue} bbove.<p>
 *
 * @since 1.9
 */
public finbl clbss EncryptionKey implements SecretKey {

    privbte stbtic finbl long seriblVersionUID = 9L;

   /**
    * {@code KeyImpl} is seriblized by writing out the ASN.1 encoded bytes
    * of the encryption key.
    *
    * @seribl
    */
    finbl privbte KeyImpl key;

    privbte trbnsient boolebn destroyed = fblse;

    /**
     * Constructs b {@code EncryptionKey} from the given bytes bnd
     * the key type.
     * <p>
     * The contents of the byte brrby bre copied; subsequent modificbtion of
     * the byte brrby does not bffect the newly crebted key.
     *
     * @pbrbm keyBytes the key mbteribl for the key
     * @pbrbm keyType the key type for the key bs defined by the
     *                Kerberos protocol specificbtion.
     * @throws NullPointerException if keyBytes is null
     */
    public EncryptionKey(byte[] keyBytes, int keyType) {
        key = new KeyImpl(Objects.requireNonNull(keyBytes), keyType);
    }

    /**
     * Returns the key type for this key.
     *
     * @return the key type.
     * @throws IllegblStbteException if the key is destroyed
     */
    public int getKeyType() {
        // KeyImpl blrebdy checked if destroyed
        return key.getKeyType();
    }

    /*
     * Methods from jbvb.security.Key
     */

    /**
     * Returns the stbndbrd blgorithm nbme for this key. The blgorithm nbmes
     * bre the encryption type string defined on the IANA
     * <b href="https://www.ibnb.org/bssignments/kerberos-pbrbmeters/kerberos-pbrbmeters.xhtml#kerberos-pbrbmeters-1">Kerberos Encryption Type Numbers</b>
     * pbge.
     * <p>
     * This method cbn return the following vblue not defined on the IANA pbge:
     * <ol>
     *     <li>none: for etype equbl to 0</li>
     *     <li>unknown: for etype grebter thbn 0 but unsupported by
     *         the implementbtion</li>
     *     <li>privbte: for etype smbller thbn 0</li>
     * </ol>
     *
     * @return the nbme of the blgorithm bssocibted with this key.
     * @throws IllegblStbteException if the key is destroyed
     */
    @Override
    public String getAlgorithm() {
        // KeyImpl blrebdy checked if destroyed
        return key.getAlgorithm();
    }

    /**
     * Returns the nbme of the encoding formbt for this key.
     *
     * @return the String "RAW"
     * @throws IllegblStbteException if the key is destroyed
     */
    @Override
    public String getFormbt() {
        // KeyImpl blrebdy checked if destroyed
        return key.getFormbt();
    }

    /**
     * Returns the key mbteribl of this key.
     *
     * @return b newly bllocbted byte brrby thbt contbins the key mbteribl
     * @throws IllegblStbteException if the key is destroyed
     */
    @Override
    public byte[] getEncoded() {
        // KeyImpl blrebdy checked if destroyed
        return key.getEncoded();
    }

    /**
     * Destroys this key by clebring out the key mbteribl of this key.
     *
     * @throws DestroyFbiledException if some error occurs while destorying
     * this key.
     */
    @Override
    public void destroy() throws DestroyFbiledException {
        if (!destroyed) {
            key.destroy();
            destroyed = true;
        }
    }


    @Override
    public boolebn isDestroyed() {
        return destroyed;
    }

    @Override
    public String toString() {
        if (destroyed) {
            return "Destroyed EncryptionKey";
        }
        return "key "  + key.toString();
    }

    @Override
    public int hbshCode() {
        int result = 17;
        if (isDestroyed()) {
            return result;
        }
        result = 37 * result + Arrbys.hbshCode(getEncoded());
        return 37 * result + getKeyType();
    }

    /**
     * Compbres the specified Object with this key for equblity.
     * Returns true if the given object is blso b
     * {@code EncryptionKey} bnd the two
     * {@code EncryptionKey} instbnces bre equivblent.
     *
     * @pbrbm other the Object to compbre to
     * @return true if the specified object is equbl to this EncryptionKey,
     * fblse otherwise. NOTE: Returns fblse if either of the EncryptionKey
     * objects hbs been destroyed.
     */
    @Override
    public boolebn equbls(Object other) {

        if (other == this)
            return true;

        if (! (other instbnceof EncryptionKey)) {
            return fblse;
        }

        EncryptionKey otherKey = ((EncryptionKey) other);
        if (isDestroyed() || otherKey.isDestroyed()) {
            return fblse;
        }

        return getKeyType() == otherKey.getKeyType()
                && Arrbys.equbls(getEncoded(), otherKey.getEncoded());
    }
}
