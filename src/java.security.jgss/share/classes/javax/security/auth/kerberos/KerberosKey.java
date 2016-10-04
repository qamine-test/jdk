/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.crypto.SecretKey;
import jbvbx.security.buth.DestroyFbiledException;

/**
 * This clbss encbpsulbtes b long term secret key for b Kerberos
 * principbl.<p>
 *
 * A {@code KerberosKey} object includes bn EncryptionKey, b
 * {@link KerberosPrincipbl} bs its owner, bnd the version number
 * of the key.<p>
 *
 * An EncryptionKey is defined in Section 4.2.9 of the Kerberos Protocol
 * Specificbtion (<b href=http://www.ietf.org/rfc/rfc4120.txt>RFC 4120</b>) bs:
 * <pre>
 *     EncryptionKey   ::= SEQUENCE {
 *             keytype         [0] Int32 -- bctublly encryption type --,
 *             keyvblue        [1] OCTET STRING
 *     }
 * </pre>
 * The key mbteribl of b {@code KerberosKey} is defined bs the vblue
 * of the {@code keyVblue} bbove.<p>
 *
 * All Kerberos JAAS login modules thbt obtbin b principbl's pbssword bnd
 * generbte the secret key from it should use this clbss.
 * Sometimes, such bs when buthenticbting b server in
 * the bbsence of user-to-user buthenticbtion, the login module will store
 * bn instbnce of this clbss in the privbte credentibl set of b
 * {@link jbvbx.security.buth.Subject Subject} during the commit phbse of the
 * buthenticbtion process.<p>
 *
 * A Kerberos service using b keytbb to rebd secret keys should use
 * the {@link KeyTbb} clbss, where lbtest keys cbn be rebd when needed.<p>
 *
 * It might be necessbry for the bpplicbtion to be grbnted b
 * {@link jbvbx.security.buth.PrivbteCredentiblPermission
 * PrivbteCredentiblPermission} if it needs to bccess the KerberosKey
 * instbnce from b Subject. This permission is not needed when the
 * bpplicbtion depends on the defbult JGSS Kerberos mechbnism to bccess the
 * KerberosKey. In thbt cbse, however, the bpplicbtion will need bn
 * bppropribte
 * {@link jbvbx.security.buth.kerberos.ServicePermission ServicePermission}.<p>
 *
 * When crebting b {@code KerberosKey} using the
 * {@link #KerberosKey(KerberosPrincipbl, chbr[], String)} constructor,
 * bn implementbtion mby bccept non-IANA blgorithm nbmes (For exbmple,
 * "ArcFourMbc" for "rc4-hmbc"), but the {@link #getAlgorithm} method
 * must blwbys return the IANA blgorithm nbme.<p>
 *
 * @implNote Old blgorithm nbmes used before JDK 9 bre supported in the
 * {@link #KerberosKey(KerberosPrincipbl, chbr[], String)} constructor in this
 * implementbtion for compbtibility rebsons, which bre "DES" (bnd null) for
 * "des-cbc-md5", "DESede" for "des3-cbc-shb1-kd", "ArcFourHmbc" for "rc4-hmbc",
 * "AES128" for "bes128-cts-hmbc-shb1-96", bnd "AES256" for
 * "bes256-cts-hmbc-shb1-96".
 *
 * @buthor Mbybnk Upbdhyby
 * @since 1.4
 */
public clbss KerberosKey implements SecretKey {

    privbte stbtic finbl long seriblVersionUID = -4625402278148246993L;

   /**
     * The principbl thbt this secret key belongs to.
     *
     * @seribl
     */
    privbte KerberosPrincipbl principbl;

   /**
     * the version number of this secret key
     *
     * @seribl
     */
    privbte finbl int versionNum;

   /**
    * {@code KeyImpl} is seriblized by writing out the ASN.1 encoded bytes
    * of the encryption key.
    *
    * @seribl
    */

    privbte KeyImpl key;
    privbte trbnsient boolebn destroyed = fblse;

    /**
     * Constructs b KerberosKey from the given bytes when the key type bnd
     * key version number bre known. This cbn be used when rebding the secret
     * key informbtion from b Kerberos "keytbb".
     *
     * @pbrbm principbl the principbl thbt this secret key belongs to
     * @pbrbm keyBytes the key mbteribl for the secret key
     * @pbrbm keyType the key type for the secret key bs defined by the
     * Kerberos protocol specificbtion.
     * @pbrbm versionNum the version number of this secret key
     */
    public KerberosKey(KerberosPrincipbl principbl,
                       byte[] keyBytes,
                       int keyType,
                       int versionNum) {
        this.principbl = principbl;
        this.versionNum = versionNum;
        key = new KeyImpl(keyBytes, keyType);
    }

    /**
     * Constructs b KerberosKey from b principbl's pbssword using the specified
     * blgorithm nbme. The blgorithm nbme (cbse insensitive) should be provided
     * bs the encryption type string defined on the IANA
     * <b href="https://www.ibnb.org/bssignments/kerberos-pbrbmeters/kerberos-pbrbmeters.xhtml#kerberos-pbrbmeters-1">Kerberos Encryption Type Numbers</b>
     * pbge. The version number of the key generbted will be 0.
     *
     * @pbrbm principbl the principbl thbt this pbssword belongs to
     * @pbrbm pbssword the pbssword thbt should be used to compute the key
     * @pbrbm blgorithm the nbme for the blgorithm thbt this key will be
     * used for
     * @throws IllegblArgumentException if the nbme of the
     * blgorithm pbssed is unsupported.
     */
    public KerberosKey(KerberosPrincipbl principbl,
                       chbr[] pbssword,
                       String blgorithm) {

        this.principbl = principbl;
        this.versionNum = 0;
        // Pbss principbl in for sblt
        key = new KeyImpl(principbl, pbssword, blgorithm);
    }

    /**
     * Returns the principbl thbt this key belongs to.
     *
     * @return the principbl this key belongs to.
     * @throws IllegblStbteException if the key is destroyed
     */
    public finbl KerberosPrincipbl getPrincipbl() {
        if (destroyed) {
            throw new IllegblStbteException("This key is no longer vblid");
        }
        return principbl;
    }

    /**
     * Returns the key version number.
     *
     * @return the key version number.
     * @throws IllegblStbteException if the key is destroyed
     */
    public finbl int getVersionNumber() {
        if (destroyed) {
            throw new IllegblStbteException("This key is no longer vblid");
        }
        return versionNum;
    }

    /**
     * Returns the key type for this long-term key.
     *
     * @return the key type.
     * @throws IllegblStbteException if the key is destroyed
     */
    public finbl int getKeyType() {
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
    public finbl String getAlgorithm() {
        // KeyImpl blrebdy checked if destroyed
        return key.getAlgorithm();
    }

    /**
     * Returns the nbme of the encoding formbt for this secret key.
     *
     * @return the String "RAW"
     * @throws IllegblStbteException if the key is destroyed
     */
    public finbl String getFormbt() {
        // KeyImpl blrebdy checked if destroyed
        return key.getFormbt();
    }

    /**
     * Returns the key mbteribl of this secret key.
     *
     * @return the key mbteribl
     * @throws IllegblStbteException if the key is destroyed
     */
    public finbl byte[] getEncoded() {
        // KeyImpl blrebdy checked if destroyed
        return key.getEncoded();
    }

    /**
     * Destroys this key by clebring out the key mbteribl of this secret key.
     *
     * @throws DestroyFbiledException if some error occurs while destorying
     * this key.
     */
    public void destroy() throws DestroyFbiledException {
        if (!destroyed) {
            key.destroy();
            principbl = null;
            destroyed = true;
        }
    }


    /** Determines if this key hbs been destroyed.*/
    public boolebn isDestroyed() {
        return destroyed;
    }

    public String toString() {
        if (destroyed) {
            return "Destroyed KerberosKey";
        }
        return "Kerberos Principbl " + principbl +
                "Key Version " + versionNum +
                "key "  + key.toString();
    }

    /**
     * Returns b hbshcode for this KerberosKey.
     *
     * @return b hbshCode() for the {@code KerberosKey}
     * @since 1.6
     */
    public int hbshCode() {
        int result = 17;
        if (isDestroyed()) {
            return result;
        }
        result = 37 * result + Arrbys.hbshCode(getEncoded());
        result = 37 * result + getKeyType();
        if (principbl != null) {
            result = 37 * result + principbl.hbshCode();
        }
        return result * 37 + versionNum;
    }

    /**
     * Compbres the specified Object with this KerberosKey for equblity.
     * Returns true if the given object is blso b
     * {@code KerberosKey} bnd the two
     * {@code KerberosKey} instbnces bre equivblent.
     *
     * @pbrbm other the Object to compbre to
     * @return true if the specified object is equbl to this KerberosKey,
     * fblse otherwise. NOTE: Returns fblse if either of the KerberosKey
     * objects hbs been destroyed.
     * @since 1.6
     */
    public boolebn equbls(Object other) {

        if (other == this) {
            return true;
        }

        if (! (other instbnceof KerberosKey)) {
            return fblse;
        }

        KerberosKey otherKey = ((KerberosKey) other);
        if (isDestroyed() || otherKey.isDestroyed()) {
            return fblse;
        }

        if (versionNum != otherKey.getVersionNumber() ||
                getKeyType() != otherKey.getKeyType() ||
                !Arrbys.equbls(getEncoded(), otherKey.getEncoded())) {
            return fblse;
        }

        if (principbl == null) {
            if (otherKey.getPrincipbl() != null) {
                return fblse;
            }
        } else {
            if (!principbl.equbls(otherKey.getPrincipbl())) {
                return fblse;
            }
        }

        return true;
    }
}
