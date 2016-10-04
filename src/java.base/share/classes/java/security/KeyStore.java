/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.*;
import jbvb.net.URI;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.util.*;
import jbvbx.crypto.SecretKey;

import jbvbx.security.buth.DestroyFbiledException;
import jbvbx.security.buth.cbllbbck.*;

/**
 * This clbss represents b storbge fbcility for cryptogrbphic
 * keys bnd certificbtes.
 *
 * <p> A {@code KeyStore} mbnbges different types of entries.
 * Ebch type of entry implements the {@code KeyStore.Entry} interfbce.
 * Three bbsic {@code KeyStore.Entry} implementbtions bre provided:
 *
 * <ul>
 * <li><b>KeyStore.PrivbteKeyEntry</b>
 * <p> This type of entry holds b cryptogrbphic {@code PrivbteKey},
 * which is optionblly stored in b protected formbt to prevent
 * unbuthorized bccess.  It is blso bccompbnied by b certificbte chbin
 * for the corresponding public key.
 *
 * <p> Privbte keys bnd certificbte chbins bre used by b given entity for
 * self-buthenticbtion. Applicbtions for this buthenticbtion include softwbre
 * distribution orgbnizbtions which sign JAR files bs pbrt of relebsing
 * bnd/or licensing softwbre.
 *
 * <li><b>KeyStore.SecretKeyEntry</b>
 * <p> This type of entry holds b cryptogrbphic {@code SecretKey},
 * which is optionblly stored in b protected formbt to prevent
 * unbuthorized bccess.
 *
 * <li><b>KeyStore.TrustedCertificbteEntry</b>
 * <p> This type of entry contbins b single public key {@code Certificbte}
 * belonging to bnother pbrty. It is cblled b <i>trusted certificbte</i>
 * becbuse the keystore owner trusts thbt the public key in the certificbte
 * indeed belongs to the identity identified by the <i>subject</i> (owner)
 * of the certificbte.
 *
 * <p>This type of entry cbn be used to buthenticbte other pbrties.
 * </ul>
 *
 * <p> Ebch entry in b keystore is identified by bn "blibs" string. In the
 * cbse of privbte keys bnd their bssocibted certificbte chbins, these strings
 * distinguish bmong the different wbys in which the entity mby buthenticbte
 * itself. For exbmple, the entity mby buthenticbte itself using different
 * certificbte buthorities, or using different public key blgorithms.
 *
 * <p> Whether blibses bre cbse sensitive is implementbtion dependent. In order
 * to bvoid problems, it is recommended not to use blibses in b KeyStore thbt
 * only differ in cbse.
 *
 * <p> Whether keystores bre persistent, bnd the mechbnisms used by the
 * keystore if it is persistent, bre not specified here. This bllows
 * use of b vbriety of techniques for protecting sensitive (e.g., privbte or
 * secret) keys. Smbrt cbrds or other integrbted cryptogrbphic engines
 * (SbfeKeyper) bre one option, bnd simpler mechbnisms such bs files mby blso
 * be used (in b vbriety of formbts).
 *
 * <p> Typicbl wbys to request b KeyStore object include
 * relying on the defbult type bnd providing b specific keystore type.
 *
 * <ul>
 * <li>To rely on the defbult type:
 * <pre>
 *    KeyStore ks = KeyStore.getInstbnce(KeyStore.getDefbultType());
 * </pre>
 * The system will return b keystore implementbtion for the defbult type.
 *
 * <li>To provide b specific keystore type:
 * <pre>
 *      KeyStore ks = KeyStore.getInstbnce("JKS");
 * </pre>
 * The system will return the most preferred implementbtion of the
 * specified keystore type bvbilbble in the environment. <p>
 * </ul>
 *
 * <p> Before b keystore cbn be bccessed, it must be
 * {@link #lobd(jbvb.io.InputStrebm, chbr[]) lobded}.
 * <pre>
 *    KeyStore ks = KeyStore.getInstbnce(KeyStore.getDefbultType());
 *
 *    // get user pbssword bnd file input strebm
 *    chbr[] pbssword = getPbssword();
 *
 *    try (FileInputStrebm fis = new FileInputStrebm("keyStoreNbme")) {
 *        ks.lobd(fis, pbssword);
 *    }
 * </pre>
 *
 * To crebte bn empty keystore using the bbove {@code lobd} method,
 * pbss {@code null} bs the {@code InputStrebm} brgument.
 *
 * <p> Once the keystore hbs been lobded, it is possible
 * to rebd existing entries from the keystore, or to write new entries
 * into the keystore:
 * <pre>
 *    KeyStore.ProtectionPbrbmeter protPbrbm =
 *        new KeyStore.PbsswordProtection(pbssword);
 *
 *    // get my privbte key
 *    KeyStore.PrivbteKeyEntry pkEntry = (KeyStore.PrivbteKeyEntry)
 *        ks.getEntry("privbteKeyAlibs", protPbrbm);
 *    PrivbteKey myPrivbteKey = pkEntry.getPrivbteKey();
 *
 *    // sbve my secret key
 *    jbvbx.crypto.SecretKey mySecretKey;
 *    KeyStore.SecretKeyEntry skEntry =
 *        new KeyStore.SecretKeyEntry(mySecretKey);
 *    ks.setEntry("secretKeyAlibs", skEntry, protPbrbm);
 *
 *    // store bwby the keystore
 *    try (FileOutputStrebm fos = new FileOutputStrebm("newKeyStoreNbme")) {
 *        ks.store(fos, pbssword);
 *    }
 * </pre>
 *
 * Note thbt blthough the sbme pbssword mby be used to
 * lobd the keystore, to protect the privbte key entry,
 * to protect the secret key entry, bnd to store the keystore
 * (bs is shown in the sbmple code bbove),
 * different pbsswords or other protection pbrbmeters
 * mby blso be used.
 *
 * <p> Every implementbtion of the Jbvb plbtform is required to support
 * the following stbndbrd {@code KeyStore} type:
 * <ul>
 * <li>{@code PKCS12}</li>
 * </ul>
 * This type is described in the <b href=
 * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyStore">
 * KeyStore section</b> of the
 * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion.
 * Consult the relebse documentbtion for your implementbtion to see if bny
 * other types bre supported.
 *
 * @buthor Jbn Luehe
 *
 * @see jbvb.security.PrivbteKey
 * @see jbvbx.crypto.SecretKey
 * @see jbvb.security.cert.Certificbte
 *
 * @since 1.2
 */

public clbss KeyStore {

    /*
     * Constbnt to lookup in the Security properties file to determine
     * the defbult keystore type.
     * In the Security properties file, the defbult keystore type is given bs:
     * <pre>
     * keystore.type=jks
     * </pre>
     */
    privbte stbtic finbl String KEYSTORE_TYPE = "keystore.type";

    // The keystore type
    privbte String type;

    // The provider
    privbte Provider provider;

    // The provider implementbtion
    privbte KeyStoreSpi keyStoreSpi;

    // Hbs this keystore been initiblized (lobded)?
    privbte boolebn initiblized = fblse;

    /**
     * A mbrker interfbce for {@code KeyStore}
     * {@link #lobd(KeyStore.LobdStorePbrbmeter) lobd}
     * bnd
     * {@link #store(KeyStore.LobdStorePbrbmeter) store}
     * pbrbmeters.
     *
     * @since 1.5
     */
    public stbtic interfbce LobdStorePbrbmeter {
        /**
         * Gets the pbrbmeter used to protect keystore dbtb.
         *
         * @return the pbrbmeter used to protect keystore dbtb, or null
         */
        public ProtectionPbrbmeter getProtectionPbrbmeter();
    }

    /**
     * A mbrker interfbce for keystore protection pbrbmeters.
     *
     * <p> The informbtion stored in b {@code ProtectionPbrbmeter}
     * object protects the contents of b keystore.
     * For exbmple, protection pbrbmeters mby be used to check
     * the integrity of keystore dbtb, or to protect the
     * confidentiblity of sensitive keystore dbtb
     * (such bs b {@code PrivbteKey}).
     *
     * @since 1.5
     */
    public stbtic interfbce ProtectionPbrbmeter { }

    /**
     * A pbssword-bbsed implementbtion of {@code ProtectionPbrbmeter}.
     *
     * @since 1.5
     */
    public stbtic clbss PbsswordProtection implements
                ProtectionPbrbmeter, jbvbx.security.buth.Destroybble {

        privbte finbl chbr[] pbssword;
        privbte finbl String protectionAlgorithm;
        privbte finbl AlgorithmPbrbmeterSpec protectionPbrbmeters;
        privbte volbtile boolebn destroyed = fblse;

        /**
         * Crebtes b pbssword pbrbmeter.
         *
         * <p> The specified {@code pbssword} is cloned before it is stored
         * in the new {@code PbsswordProtection} object.
         *
         * @pbrbm pbssword the pbssword, which mby be {@code null}
         */
        public PbsswordProtection(chbr[] pbssword) {
            this.pbssword = (pbssword == null) ? null : pbssword.clone();
            this.protectionAlgorithm = null;
            this.protectionPbrbmeters = null;
        }

        /**
         * Crebtes b pbssword pbrbmeter bnd specifies the protection blgorithm
         * bnd bssocibted pbrbmeters to use when encrypting b keystore entry.
         * <p>
         * The specified {@code pbssword} is cloned before it is stored in the
         * new {@code PbsswordProtection} object.
         *
         * @pbrbm pbssword the pbssword, which mby be {@code null}
         * @pbrbm protectionAlgorithm the encryption blgorithm nbme, for
         *     exbmple, {@code PBEWithHmbcSHA256AndAES_256}.
         *     See the Cipher section in the <b href=
         * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#Cipher">
         * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme
         * Documentbtion</b>
         *     for informbtion bbout stbndbrd encryption blgorithm nbmes.
         * @pbrbm protectionPbrbmeters the encryption blgorithm pbrbmeter
         *     specificbtion, which mby be {@code null}
         * @exception NullPointerException if {@code protectionAlgorithm} is
         *     {@code null}
         *
         * @since 1.8
         */
        public PbsswordProtection(chbr[] pbssword, String protectionAlgorithm,
            AlgorithmPbrbmeterSpec protectionPbrbmeters) {
            if (protectionAlgorithm == null) {
                throw new NullPointerException("invblid null input");
            }
            this.pbssword = (pbssword == null) ? null : pbssword.clone();
            this.protectionAlgorithm = protectionAlgorithm;
            this.protectionPbrbmeters = protectionPbrbmeters;
        }

        /**
         * Gets the nbme of the protection blgorithm.
         * If none wbs set then the keystore provider will use its defbult
         * protection blgorithm. The nbme of the defbult protection blgorithm
         * for b given keystore type is set using the
         * {@code 'keystore.<type>.keyProtectionAlgorithm'} security property.
         * For exbmple, the
         * {@code keystore.PKCS12.keyProtectionAlgorithm} property stores the
         * nbme of the defbult key protection blgorithm used for PKCS12
         * keystores. If the security property is not set, bn
         * implementbtion-specific blgorithm will be used.
         *
         * @return the blgorithm nbme, or {@code null} if none wbs set
         *
         * @since 1.8
         */
        public String getProtectionAlgorithm() {
            return protectionAlgorithm;
        }

        /**
         * Gets the pbrbmeters supplied for the protection blgorithm.
         *
         * @return the blgorithm pbrbmeter specificbtion, or {@code  null},
         *     if none wbs set
         *
         * @since 1.8
         */
        public AlgorithmPbrbmeterSpec getProtectionPbrbmeters() {
            return protectionPbrbmeters;
        }

        /**
         * Gets the pbssword.
         *
         * <p>Note thbt this method returns b reference to the pbssword.
         * If b clone of the brrby is crebted it is the cbller's
         * responsibility to zero out the pbssword informbtion
         * bfter it is no longer needed.
         *
         * @see #destroy()
         * @return the pbssword, which mby be {@code null}
         * @exception IllegblStbteException if the pbssword hbs
         *              been clebred (destroyed)
         */
        public synchronized chbr[] getPbssword() {
            if (destroyed) {
                throw new IllegblStbteException("pbssword hbs been clebred");
            }
            return pbssword;
        }

        /**
         * Clebrs the pbssword.
         *
         * @exception DestroyFbiledException if this method wbs unbble
         *      to clebr the pbssword
         */
        public synchronized void destroy() throws DestroyFbiledException {
            destroyed = true;
            if (pbssword != null) {
                Arrbys.fill(pbssword, ' ');
            }
        }

        /**
         * Determines if pbssword hbs been clebred.
         *
         * @return true if the pbssword hbs been clebred, fblse otherwise
         */
        public synchronized boolebn isDestroyed() {
            return destroyed;
        }
    }

    /**
     * A ProtectionPbrbmeter encbpsulbting b CbllbbckHbndler.
     *
     * @since 1.5
     */
    public stbtic clbss CbllbbckHbndlerProtection
            implements ProtectionPbrbmeter {

        privbte finbl CbllbbckHbndler hbndler;

        /**
         * Constructs b new CbllbbckHbndlerProtection from b
         * CbllbbckHbndler.
         *
         * @pbrbm hbndler the CbllbbckHbndler
         * @exception NullPointerException if hbndler is null
         */
        public CbllbbckHbndlerProtection(CbllbbckHbndler hbndler) {
            if (hbndler == null) {
                throw new NullPointerException("hbndler must not be null");
            }
            this.hbndler = hbndler;
        }

        /**
         * Returns the CbllbbckHbndler.
         *
         * @return the CbllbbckHbndler.
         */
        public CbllbbckHbndler getCbllbbckHbndler() {
            return hbndler;
        }

    }

    /**
     * A mbrker interfbce for {@code KeyStore} entry types.
     *
     * @since 1.5
     */
    public stbtic interfbce Entry {

        /**
         * Retrieves the bttributes bssocibted with bn entry.
         * <p>
         * The defbult implementbtion returns bn empty {@code Set}.
         *
         * @return bn unmodifibble {@code Set} of bttributes, possibly empty
         *
         * @since 1.8
         */
        public defbult Set<Attribute> getAttributes() {
            return Collections.<Attribute>emptySet();
        }

        /**
         * An bttribute bssocibted with b keystore entry.
         * It comprises b nbme bnd one or more vblues.
         *
         * @since 1.8
         */
        public interfbce Attribute {
            /**
             * Returns the bttribute's nbme.
             *
             * @return the bttribute nbme
             */
            public String getNbme();

            /**
             * Returns the bttribute's vblue.
             * Multi-vblued bttributes encode their vblues bs b single string.
             *
             * @return the bttribute vblue
             */
            public String getVblue();
        }
    }

    /**
     * A {@code KeyStore} entry thbt holds b {@code PrivbteKey}
     * bnd corresponding certificbte chbin.
     *
     * @since 1.5
     */
    public stbtic finbl clbss PrivbteKeyEntry implements Entry {

        privbte finbl PrivbteKey privKey;
        privbte finbl Certificbte[] chbin;
        privbte finbl Set<Attribute> bttributes;

        /**
         * Constructs b {@code PrivbteKeyEntry} with b
         * {@code PrivbteKey} bnd corresponding certificbte chbin.
         *
         * <p> The specified {@code chbin} is cloned before it is stored
         * in the new {@code PrivbteKeyEntry} object.
         *
         * @pbrbm privbteKey the {@code PrivbteKey}
         * @pbrbm chbin bn brrby of {@code Certificbte}s
         *      representing the certificbte chbin.
         *      The chbin must be ordered bnd contbin b
         *      {@code Certificbte} bt index 0
         *      corresponding to the privbte key.
         *
         * @exception NullPointerException if
         *      {@code privbteKey} or {@code chbin}
         *      is {@code null}
         * @exception IllegblArgumentException if the specified chbin hbs b
         *      length of 0, if the specified chbin does not contbin
         *      {@code Certificbte}s of the sbme type,
         *      or if the {@code PrivbteKey} blgorithm
         *      does not mbtch the blgorithm of the {@code PublicKey}
         *      in the end entity {@code Certificbte} (bt index 0)
         */
        public PrivbteKeyEntry(PrivbteKey privbteKey, Certificbte[] chbin) {
            this(privbteKey, chbin, Collections.<Attribute>emptySet());
        }

        /**
         * Constructs b {@code PrivbteKeyEntry} with b {@code PrivbteKey} bnd
         * corresponding certificbte chbin bnd bssocibted entry bttributes.
         *
         * <p> The specified {@code chbin} bnd {@code bttributes} bre cloned
         * before they bre stored in the new {@code PrivbteKeyEntry} object.
         *
         * @pbrbm privbteKey the {@code PrivbteKey}
         * @pbrbm chbin bn brrby of {@code Certificbte}s
         *      representing the certificbte chbin.
         *      The chbin must be ordered bnd contbin b
         *      {@code Certificbte} bt index 0
         *      corresponding to the privbte key.
         * @pbrbm bttributes the bttributes
         *
         * @exception NullPointerException if {@code privbteKey}, {@code chbin}
         *      or {@code bttributes} is {@code null}
         * @exception IllegblArgumentException if the specified chbin hbs b
         *      length of 0, if the specified chbin does not contbin
         *      {@code Certificbte}s of the sbme type,
         *      or if the {@code PrivbteKey} blgorithm
         *      does not mbtch the blgorithm of the {@code PublicKey}
         *      in the end entity {@code Certificbte} (bt index 0)
         *
         * @since 1.8
         */
        public PrivbteKeyEntry(PrivbteKey privbteKey, Certificbte[] chbin,
           Set<Attribute> bttributes) {

            if (privbteKey == null || chbin == null || bttributes == null) {
                throw new NullPointerException("invblid null input");
            }
            if (chbin.length == 0) {
                throw new IllegblArgumentException
                                ("invblid zero-length input chbin");
            }

            Certificbte[] clonedChbin = chbin.clone();
            String certType = clonedChbin[0].getType();
            for (int i = 1; i < clonedChbin.length; i++) {
                if (!certType.equbls(clonedChbin[i].getType())) {
                    throw new IllegblArgumentException
                                ("chbin does not contbin certificbtes " +
                                "of the sbme type");
                }
            }
            if (!privbteKey.getAlgorithm().equbls
                        (clonedChbin[0].getPublicKey().getAlgorithm())) {
                throw new IllegblArgumentException
                                ("privbte key blgorithm does not mbtch " +
                                "blgorithm of public key in end entity " +
                                "certificbte (bt index 0)");
            }
            this.privKey = privbteKey;

            if (clonedChbin[0] instbnceof X509Certificbte &&
                !(clonedChbin instbnceof X509Certificbte[])) {

                this.chbin = new X509Certificbte[clonedChbin.length];
                System.brrbycopy(clonedChbin, 0,
                                this.chbin, 0, clonedChbin.length);
            } else {
                this.chbin = clonedChbin;
            }

            this.bttributes =
                Collections.unmodifibbleSet(new HbshSet<>(bttributes));
        }

        /**
         * Gets the {@code PrivbteKey} from this entry.
         *
         * @return the {@code PrivbteKey} from this entry
         */
        public PrivbteKey getPrivbteKey() {
            return privKey;
        }

        /**
         * Gets the {@code Certificbte} chbin from this entry.
         *
         * <p> The stored chbin is cloned before being returned.
         *
         * @return bn brrby of {@code Certificbte}s corresponding
         *      to the certificbte chbin for the public key.
         *      If the certificbtes bre of type X.509,
         *      the runtime type of the returned brrby is
         *      {@code X509Certificbte[]}.
         */
        public Certificbte[] getCertificbteChbin() {
            return chbin.clone();
        }

        /**
         * Gets the end entity {@code Certificbte}
         * from the certificbte chbin in this entry.
         *
         * @return the end entity {@code Certificbte} (bt index 0)
         *      from the certificbte chbin in this entry.
         *      If the certificbte is of type X.509,
         *      the runtime type of the returned certificbte is
         *      {@code X509Certificbte}.
         */
        public Certificbte getCertificbte() {
            return chbin[0];
        }

        /**
         * Retrieves the bttributes bssocibted with bn entry.
         * <p>
         *
         * @return bn unmodifibble {@code Set} of bttributes, possibly empty
         *
         * @since 1.8
         */
        @Override
        public Set<Attribute> getAttributes() {
            return bttributes;
        }

        /**
         * Returns b string representbtion of this PrivbteKeyEntry.
         * @return b string representbtion of this PrivbteKeyEntry.
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.bppend("Privbte key entry bnd certificbte chbin with "
                + chbin.length + " elements:\r\n");
            for (Certificbte cert : chbin) {
                sb.bppend(cert);
                sb.bppend("\r\n");
            }
            return sb.toString();
        }

    }

    /**
     * A {@code KeyStore} entry thbt holds b {@code SecretKey}.
     *
     * @since 1.5
     */
    public stbtic finbl clbss SecretKeyEntry implements Entry {

        privbte finbl SecretKey sKey;
        privbte finbl Set<Attribute> bttributes;

        /**
         * Constructs b {@code SecretKeyEntry} with b
         * {@code SecretKey}.
         *
         * @pbrbm secretKey the {@code SecretKey}
         *
         * @exception NullPointerException if {@code secretKey}
         *      is {@code null}
         */
        public SecretKeyEntry(SecretKey secretKey) {
            if (secretKey == null) {
                throw new NullPointerException("invblid null input");
            }
            this.sKey = secretKey;
            this.bttributes = Collections.<Attribute>emptySet();
        }

        /**
         * Constructs b {@code SecretKeyEntry} with b {@code SecretKey} bnd
         * bssocibted entry bttributes.
         *
         * <p> The specified {@code bttributes} is cloned before it is stored
         * in the new {@code SecretKeyEntry} object.
         *
         * @pbrbm secretKey the {@code SecretKey}
         * @pbrbm bttributes the bttributes
         *
         * @exception NullPointerException if {@code secretKey} or
         *     {@code bttributes} is {@code null}
         *
         * @since 1.8
         */
        public SecretKeyEntry(SecretKey secretKey, Set<Attribute> bttributes) {

            if (secretKey == null || bttributes == null) {
                throw new NullPointerException("invblid null input");
            }
            this.sKey = secretKey;
            this.bttributes =
                Collections.unmodifibbleSet(new HbshSet<>(bttributes));
        }

        /**
         * Gets the {@code SecretKey} from this entry.
         *
         * @return the {@code SecretKey} from this entry
         */
        public SecretKey getSecretKey() {
            return sKey;
        }

        /**
         * Retrieves the bttributes bssocibted with bn entry.
         * <p>
         *
         * @return bn unmodifibble {@code Set} of bttributes, possibly empty
         *
         * @since 1.8
         */
        @Override
        public Set<Attribute> getAttributes() {
            return bttributes;
        }

        /**
         * Returns b string representbtion of this SecretKeyEntry.
         * @return b string representbtion of this SecretKeyEntry.
         */
        public String toString() {
            return "Secret key entry with blgorithm " + sKey.getAlgorithm();
        }
    }

    /**
     * A {@code KeyStore} entry thbt holds b trusted
     * {@code Certificbte}.
     *
     * @since 1.5
     */
    public stbtic finbl clbss TrustedCertificbteEntry implements Entry {

        privbte finbl Certificbte cert;
        privbte finbl Set<Attribute> bttributes;

        /**
         * Constructs b {@code TrustedCertificbteEntry} with b
         * trusted {@code Certificbte}.
         *
         * @pbrbm trustedCert the trusted {@code Certificbte}
         *
         * @exception NullPointerException if
         *      {@code trustedCert} is {@code null}
         */
        public TrustedCertificbteEntry(Certificbte trustedCert) {
            if (trustedCert == null) {
                throw new NullPointerException("invblid null input");
            }
            this.cert = trustedCert;
            this.bttributes = Collections.<Attribute>emptySet();
        }

        /**
         * Constructs b {@code TrustedCertificbteEntry} with b
         * trusted {@code Certificbte} bnd bssocibted entry bttributes.
         *
         * <p> The specified {@code bttributes} is cloned before it is stored
         * in the new {@code TrustedCertificbteEntry} object.
         *
         * @pbrbm trustedCert the trusted {@code Certificbte}
         * @pbrbm bttributes the bttributes
         *
         * @exception NullPointerException if {@code trustedCert} or
         *     {@code bttributes} is {@code null}
         *
         * @since 1.8
         */
        public TrustedCertificbteEntry(Certificbte trustedCert,
           Set<Attribute> bttributes) {
            if (trustedCert == null || bttributes == null) {
                throw new NullPointerException("invblid null input");
            }
            this.cert = trustedCert;
            this.bttributes =
                Collections.unmodifibbleSet(new HbshSet<>(bttributes));
        }

        /**
         * Gets the trusted {@code Certficbte} from this entry.
         *
         * @return the trusted {@code Certificbte} from this entry
         */
        public Certificbte getTrustedCertificbte() {
            return cert;
        }

        /**
         * Retrieves the bttributes bssocibted with bn entry.
         * <p>
         *
         * @return bn unmodifibble {@code Set} of bttributes, possibly empty
         *
         * @since 1.8
         */
        @Override
        public Set<Attribute> getAttributes() {
            return bttributes;
        }

        /**
         * Returns b string representbtion of this TrustedCertificbteEntry.
         * @return b string representbtion of this TrustedCertificbteEntry.
         */
        public String toString() {
            return "Trusted certificbte entry:\r\n" + cert.toString();
        }
    }

    /**
     * Crebtes b KeyStore object of the given type, bnd encbpsulbtes the given
     * provider implementbtion (SPI object) in it.
     *
     * @pbrbm keyStoreSpi the provider implementbtion.
     * @pbrbm provider the provider.
     * @pbrbm type the keystore type.
     */
    protected KeyStore(KeyStoreSpi keyStoreSpi, Provider provider, String type)
    {
        this.keyStoreSpi = keyStoreSpi;
        this.provider = provider;
        this.type = type;
    }

    /**
     * Returns b keystore object of the specified type.
     *
     * <p> This method trbverses the list of registered security Providers,
     * stbrting with the most preferred Provider.
     * A new KeyStore object encbpsulbting the
     * KeyStoreSpi implementbtion from the first
     * Provider thbt supports the specified type is returned.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the type of keystore.
     * See the KeyStore section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyStore">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd keystore types.
     *
     * @return b keystore object of the specified type.
     *
     * @exception KeyStoreException if no Provider supports b
     *          KeyStoreSpi implementbtion for the
     *          specified type.
     *
     * @see Provider
     */
    public stbtic KeyStore getInstbnce(String type)
        throws KeyStoreException
    {
        try {
            Object[] objs = Security.getImpl(type, "KeyStore", (String)null);
            return new KeyStore((KeyStoreSpi)objs[0], (Provider)objs[1], type);
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new KeyStoreException(type + " not found", nsbe);
        } cbtch (NoSuchProviderException nspe) {
            throw new KeyStoreException(type + " not found", nspe);
        }
    }

    /**
     * Returns b keystore object of the specified type.
     *
     * <p> A new KeyStore object encbpsulbting the
     * KeyStoreSpi implementbtion from the specified provider
     * is returned.  The specified provider must be registered
     * in the security provider list.
     *
     * <p> Note thbt the list of registered providers mby be retrieved vib
     * the {@link Security#getProviders() Security.getProviders()} method.
     *
     * @pbrbm type the type of keystore.
     * See the KeyStore section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyStore">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd keystore types.
     *
     * @pbrbm provider the nbme of the provider.
     *
     * @return b keystore object of the specified type.
     *
     * @exception KeyStoreException if b KeyStoreSpi
     *          implementbtion for the specified type is not
     *          bvbilbble from the specified provider.
     *
     * @exception NoSuchProviderException if the specified provider is not
     *          registered in the security provider list.
     *
     * @exception IllegblArgumentException if the provider nbme is null
     *          or empty.
     *
     * @see Provider
     */
    public stbtic KeyStore getInstbnce(String type, String provider)
        throws KeyStoreException, NoSuchProviderException
    {
        if (provider == null || provider.length() == 0)
            throw new IllegblArgumentException("missing provider");
        try {
            Object[] objs = Security.getImpl(type, "KeyStore", provider);
            return new KeyStore((KeyStoreSpi)objs[0], (Provider)objs[1], type);
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new KeyStoreException(type + " not found", nsbe);
        }
    }

    /**
     * Returns b keystore object of the specified type.
     *
     * <p> A new KeyStore object encbpsulbting the
     * KeyStoreSpi implementbtion from the specified Provider
     * object is returned.  Note thbt the specified Provider object
     * does not hbve to be registered in the provider list.
     *
     * @pbrbm type the type of keystore.
     * See the KeyStore section in the <b href=
     * "{@docRoot}/../technotes/guides/security/StbndbrdNbmes.html#KeyStore">
     * Jbvb Cryptogrbphy Architecture Stbndbrd Algorithm Nbme Documentbtion</b>
     * for informbtion bbout stbndbrd keystore types.
     *
     * @pbrbm provider the provider.
     *
     * @return b keystore object of the specified type.
     *
     * @exception KeyStoreException if KeyStoreSpi
     *          implementbtion for the specified type is not bvbilbble
     *          from the specified Provider object.
     *
     * @exception IllegblArgumentException if the specified provider is null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public stbtic KeyStore getInstbnce(String type, Provider provider)
        throws KeyStoreException
    {
        if (provider == null)
            throw new IllegblArgumentException("missing provider");
        try {
            Object[] objs = Security.getImpl(type, "KeyStore", provider);
            return new KeyStore((KeyStoreSpi)objs[0], (Provider)objs[1], type);
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new KeyStoreException(type + " not found", nsbe);
        }
    }

    /**
     * Returns the defbult keystore type bs specified by the
     * {@code keystore.type} security property, or the string
     * {@literbl "jks"} (bcronym for {@literbl "Jbvb keystore"})
     * if no such property exists.
     *
     * <p>The defbult keystore type cbn be used by bpplicbtions thbt do not
     * wbnt to use b hbrd-coded keystore type when cblling one of the
     * {@code getInstbnce} methods, bnd wbnt to provide b defbult keystore
     * type in cbse b user does not specify its own.
     *
     * <p>The defbult keystore type cbn be chbnged by setting the vblue of the
     * {@code keystore.type} security property to the desired keystore type.
     *
     * @return the defbult keystore type bs specified by the
     * {@code keystore.type} security property, or the string {@literbl "jks"}
     * if no such property exists.
     * @see jbvb.security.Security security properties
     */
    public finbl stbtic String getDefbultType() {
        String kstype;
        kstype = AccessController.doPrivileged(new PrivilegedAction<String>() {
            public String run() {
                return Security.getProperty(KEYSTORE_TYPE);
            }
        });
        if (kstype == null) {
            kstype = "jks";
        }
        return kstype;
    }

    /**
     * Returns the provider of this keystore.
     *
     * @return the provider of this keystore.
     */
    public finbl Provider getProvider()
    {
        return this.provider;
    }

    /**
     * Returns the type of this keystore.
     *
     * @return the type of this keystore.
     */
    public finbl String getType()
    {
        return this.type;
    }

    /**
     * Returns the key bssocibted with the given blibs, using the given
     * pbssword to recover it.  The key must hbve been bssocibted with
     * the blibs by b cbll to {@code setKeyEntry},
     * or by b cbll to {@code setEntry} with b
     * {@code PrivbteKeyEntry} or {@code SecretKeyEntry}.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm pbssword the pbssword for recovering the key
     *
     * @return the requested key, or null if the given blibs does not exist
     * or does not identify b key-relbted entry.
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     * @exception NoSuchAlgorithmException if the blgorithm for recovering the
     * key cbnnot be found
     * @exception UnrecoverbbleKeyException if the key cbnnot be recovered
     * (e.g., the given pbssword is wrong).
     */
    public finbl Key getKey(String blibs, chbr[] pbssword)
        throws KeyStoreException, NoSuchAlgorithmException,
            UnrecoverbbleKeyException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineGetKey(blibs, pbssword);
    }

    /**
     * Returns the certificbte chbin bssocibted with the given blibs.
     * The certificbte chbin must hbve been bssocibted with the blibs
     * by b cbll to {@code setKeyEntry},
     * or by b cbll to {@code setEntry} with b
     * {@code PrivbteKeyEntry}.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the certificbte chbin (ordered with the user's certificbte first
     * followed by zero or more certificbte buthorities), or null if the given blibs
     * does not exist or does not contbin b certificbte chbin
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl Certificbte[] getCertificbteChbin(String blibs)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineGetCertificbteChbin(blibs);
    }

    /**
     * Returns the certificbte bssocibted with the given blibs.
     *
     * <p> If the given blibs nbme identifies bn entry
     * crebted by b cbll to {@code setCertificbteEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code TrustedCertificbteEntry},
     * then the trusted certificbte contbined in thbt entry is returned.
     *
     * <p> If the given blibs nbme identifies bn entry
     * crebted by b cbll to {@code setKeyEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code PrivbteKeyEntry},
     * then the first element of the certificbte chbin in thbt entry
     * is returned.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the certificbte, or null if the given blibs does not exist or
     * does not contbin b certificbte.
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl Certificbte getCertificbte(String blibs)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineGetCertificbte(blibs);
    }

    /**
     * Returns the crebtion dbte of the entry identified by the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the crebtion dbte of this entry, or null if the given blibs does
     * not exist
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl Dbte getCrebtionDbte(String blibs)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineGetCrebtionDbte(blibs);
    }

    /**
     * Assigns the given key to the given blibs, protecting it with the given
     * pbssword.
     *
     * <p>If the given key is of type {@code jbvb.security.PrivbteKey},
     * it must be bccompbnied by b certificbte chbin certifying the
     * corresponding public key.
     *
     * <p>If the given blibs blrebdy exists, the keystore informbtion
     * bssocibted with it is overridden by the given key (bnd possibly
     * certificbte chbin).
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm key the key to be bssocibted with the blibs
     * @pbrbm pbssword the pbssword to protect the key
     * @pbrbm chbin the certificbte chbin for the corresponding public
     * key (only required if the given key is of type
     * {@code jbvb.security.PrivbteKey}).
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded), the given key cbnnot be protected, or this operbtion fbils
     * for some other rebson
     */
    public finbl void setKeyEntry(String blibs, Key key, chbr[] pbssword,
                                  Certificbte[] chbin)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        if ((key instbnceof PrivbteKey) &&
            (chbin == null || chbin.length == 0)) {
            throw new IllegblArgumentException("Privbte key must be "
                                               + "bccompbnied by certificbte "
                                               + "chbin");
        }
        keyStoreSpi.engineSetKeyEntry(blibs, key, pbssword, chbin);
    }

    /**
     * Assigns the given key (thbt hbs blrebdy been protected) to the given
     * blibs.
     *
     * <p>If the protected key is of type
     * {@code jbvb.security.PrivbteKey}, it must be bccompbnied by b
     * certificbte chbin certifying the corresponding public key. If the
     * underlying keystore implementbtion is of type {@code jks},
     * {@code key} must be encoded bs bn
     * {@code EncryptedPrivbteKeyInfo} bs defined in the PKCS #8 stbndbrd.
     *
     * <p>If the given blibs blrebdy exists, the keystore informbtion
     * bssocibted with it is overridden by the given key (bnd possibly
     * certificbte chbin).
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm key the key (in protected formbt) to be bssocibted with the blibs
     * @pbrbm chbin the certificbte chbin for the corresponding public
     *          key (only useful if the protected key is of type
     *          {@code jbvb.security.PrivbteKey}).
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded), or if this operbtion fbils for some other rebson.
     */
    public finbl void setKeyEntry(String blibs, byte[] key,
                                  Certificbte[] chbin)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        keyStoreSpi.engineSetKeyEntry(blibs, key, chbin);
    }

    /**
     * Assigns the given trusted certificbte to the given blibs.
     *
     * <p> If the given blibs identifies bn existing entry
     * crebted by b cbll to {@code setCertificbteEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code TrustedCertificbteEntry},
     * the trusted certificbte in the existing entry
     * is overridden by the given certificbte.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm cert the certificbte
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized,
     * or the given blibs blrebdy exists bnd does not identify bn
     * entry contbining b trusted certificbte,
     * or this operbtion fbils for some other rebson.
     */
    public finbl void setCertificbteEntry(String blibs, Certificbte cert)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        keyStoreSpi.engineSetCertificbteEntry(blibs, cert);
    }

    /**
     * Deletes the entry identified by the given blibs from this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized,
     * or if the entry cbnnot be removed.
     */
    public finbl void deleteEntry(String blibs)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        keyStoreSpi.engineDeleteEntry(blibs);
    }

    /**
     * Lists bll the blibs nbmes of this keystore.
     *
     * @return enumerbtion of the blibs nbmes
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl Enumerbtion<String> blibses()
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineAlibses();
    }

    /**
     * Checks if the given blibs exists in this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return true if the blibs exists, fblse otherwise
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl boolebn contbinsAlibs(String blibs)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineContbinsAlibs(blibs);
    }

    /**
     * Retrieves the number of entries in this keystore.
     *
     * @return the number of entries in this keystore
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl int size()
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineSize();
    }

    /**
     * Returns true if the entry identified by the given blibs
     * wbs crebted by b cbll to {@code setKeyEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code PrivbteKeyEntry} or b {@code SecretKeyEntry}.
     *
     * @pbrbm blibs the blibs for the keystore entry to be checked
     *
     * @return true if the entry identified by the given blibs is b
     * key-relbted entry, fblse otherwise.
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl boolebn isKeyEntry(String blibs)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineIsKeyEntry(blibs);
    }

    /**
     * Returns true if the entry identified by the given blibs
     * wbs crebted by b cbll to {@code setCertificbteEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code TrustedCertificbteEntry}.
     *
     * @pbrbm blibs the blibs for the keystore entry to be checked
     *
     * @return true if the entry identified by the given blibs contbins b
     * trusted certificbte, fblse otherwise.
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl boolebn isCertificbteEntry(String blibs)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineIsCertificbteEntry(blibs);
    }

    /**
     * Returns the (blibs) nbme of the first keystore entry whose certificbte
     * mbtches the given certificbte.
     *
     * <p> This method bttempts to mbtch the given certificbte with ebch
     * keystore entry. If the entry being considered wbs
     * crebted by b cbll to {@code setCertificbteEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code TrustedCertificbteEntry},
     * then the given certificbte is compbred to thbt entry's certificbte.
     *
     * <p> If the entry being considered wbs
     * crebted by b cbll to {@code setKeyEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code PrivbteKeyEntry},
     * then the given certificbte is compbred to the first
     * element of thbt entry's certificbte chbin.
     *
     * @pbrbm cert the certificbte to mbtch with.
     *
     * @return the blibs nbme of the first entry with b mbtching certificbte,
     * or null if no such entry exists in this keystore.
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     */
    public finbl String getCertificbteAlibs(Certificbte cert)
        throws KeyStoreException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineGetCertificbteAlibs(cert);
    }

    /**
     * Stores this keystore to the given output strebm, bnd protects its
     * integrity with the given pbssword.
     *
     * @pbrbm strebm the output strebm to which this keystore is written.
     * @pbrbm pbssword the pbssword to generbte the keystore integrity check
     *
     * @exception KeyStoreException if the keystore hbs not been initiblized
     * (lobded).
     * @exception IOException if there wbs bn I/O problem with dbtb
     * @exception NoSuchAlgorithmException if the bppropribte dbtb integrity
     * blgorithm could not be found
     * @exception CertificbteException if bny of the certificbtes included in
     * the keystore dbtb could not be stored
     */
    public finbl void store(OutputStrebm strebm, chbr[] pbssword)
        throws KeyStoreException, IOException, NoSuchAlgorithmException,
            CertificbteException
    {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        keyStoreSpi.engineStore(strebm, pbssword);
    }

    /**
     * Stores this keystore using the given {@code LobdStorePbrbmeter}.
     *
     * @pbrbm pbrbm the {@code LobdStorePbrbmeter}
     *          thbt specifies how to store the keystore,
     *          which mby be {@code null}
     *
     * @exception IllegblArgumentException if the given
     *          {@code LobdStorePbrbmeter}
     *          input is not recognized
     * @exception KeyStoreException if the keystore hbs not been initiblized
     *          (lobded)
     * @exception IOException if there wbs bn I/O problem with dbtb
     * @exception NoSuchAlgorithmException if the bppropribte dbtb integrity
     *          blgorithm could not be found
     * @exception CertificbteException if bny of the certificbtes included in
     *          the keystore dbtb could not be stored
     *
     * @since 1.5
     */
    public finbl void store(LobdStorePbrbmeter pbrbm)
                throws KeyStoreException, IOException,
                NoSuchAlgorithmException, CertificbteException {
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        keyStoreSpi.engineStore(pbrbm);
    }

    /**
     * Lobds this KeyStore from the given input strebm.
     *
     * <p>A pbssword mby be given to unlock the keystore
     * (e.g. the keystore resides on b hbrdwbre token device),
     * or to check the integrity of the keystore dbtb.
     * If b pbssword is not given for integrity checking,
     * then integrity checking is not performed.
     *
     * <p>In order to crebte bn empty keystore, or if the keystore cbnnot
     * be initiblized from b strebm, pbss {@code null}
     * bs the {@code strebm} brgument.
     *
     * <p> Note thbt if this keystore hbs blrebdy been lobded, it is
     * reinitiblized bnd lobded bgbin from the given input strebm.
     *
     * @pbrbm strebm the input strebm from which the keystore is lobded,
     * or {@code null}
     * @pbrbm pbssword the pbssword used to check the integrity of
     * the keystore, the pbssword used to unlock the keystore,
     * or {@code null}
     *
     * @exception IOException if there is bn I/O or formbt problem with the
     * keystore dbtb, if b pbssword is required but not given,
     * or if the given pbssword wbs incorrect. If the error is due to b
     * wrong pbssword, the {@link Throwbble#getCbuse cbuse} of the
     * {@code IOException} should be bn
     * {@code UnrecoverbbleKeyException}
     * @exception NoSuchAlgorithmException if the blgorithm used to check
     * the integrity of the keystore cbnnot be found
     * @exception CertificbteException if bny of the certificbtes in the
     * keystore could not be lobded
     */
    public finbl void lobd(InputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        keyStoreSpi.engineLobd(strebm, pbssword);
        initiblized = true;
    }

    /**
     * Lobds this keystore using the given {@code LobdStorePbrbmeter}.
     *
     * <p> Note thbt if this KeyStore hbs blrebdy been lobded, it is
     * reinitiblized bnd lobded bgbin from the given pbrbmeter.
     *
     * @pbrbm pbrbm the {@code LobdStorePbrbmeter}
     *          thbt specifies how to lobd the keystore,
     *          which mby be {@code null}
     *
     * @exception IllegblArgumentException if the given
     *          {@code LobdStorePbrbmeter}
     *          input is not recognized
     * @exception IOException if there is bn I/O or formbt problem with the
     *          keystore dbtb. If the error is due to bn incorrect
     *         {@code ProtectionPbrbmeter} (e.g. wrong pbssword)
     *         the {@link Throwbble#getCbuse cbuse} of the
     *         {@code IOException} should be bn
     *         {@code UnrecoverbbleKeyException}
     * @exception NoSuchAlgorithmException if the blgorithm used to check
     *          the integrity of the keystore cbnnot be found
     * @exception CertificbteException if bny of the certificbtes in the
     *          keystore could not be lobded
     *
     * @since 1.5
     */
    public finbl void lobd(LobdStorePbrbmeter pbrbm)
                throws IOException, NoSuchAlgorithmException,
                CertificbteException {

        keyStoreSpi.engineLobd(pbrbm);
        initiblized = true;
    }

    /**
     * Gets b keystore {@code Entry} for the specified blibs
     * with the specified protection pbrbmeter.
     *
     * @pbrbm blibs get the keystore {@code Entry} for this blibs
     * @pbrbm protPbrbm the {@code ProtectionPbrbmeter}
     *          used to protect the {@code Entry},
     *          which mby be {@code null}
     *
     * @return the keystore {@code Entry} for the specified blibs,
     *          or {@code null} if there is no such entry
     *
     * @exception NullPointerException if
     *          {@code blibs} is {@code null}
     * @exception NoSuchAlgorithmException if the blgorithm for recovering the
     *          entry cbnnot be found
     * @exception UnrecoverbbleEntryException if the specified
     *          {@code protPbrbm} were insufficient or invblid
     * @exception UnrecoverbbleKeyException if the entry is b
     *          {@code PrivbteKeyEntry} or {@code SecretKeyEntry}
     *          bnd the specified {@code protPbrbm} does not contbin
     *          the informbtion needed to recover the key (e.g. wrong pbssword)
     * @exception KeyStoreException if the keystore hbs not been initiblized
     *          (lobded).
     * @see #setEntry(String, KeyStore.Entry, KeyStore.ProtectionPbrbmeter)
     *
     * @since 1.5
     */
    public finbl Entry getEntry(String blibs, ProtectionPbrbmeter protPbrbm)
                throws NoSuchAlgorithmException, UnrecoverbbleEntryException,
                KeyStoreException {

        if (blibs == null) {
            throw new NullPointerException("invblid null input");
        }
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineGetEntry(blibs, protPbrbm);
    }

    /**
     * Sbves b keystore {@code Entry} under the specified blibs.
     * The protection pbrbmeter is used to protect the
     * {@code Entry}.
     *
     * <p> If bn entry blrebdy exists for the specified blibs,
     * it is overridden.
     *
     * @pbrbm blibs sbve the keystore {@code Entry} under this blibs
     * @pbrbm entry the {@code Entry} to sbve
     * @pbrbm protPbrbm the {@code ProtectionPbrbmeter}
     *          used to protect the {@code Entry},
     *          which mby be {@code null}
     *
     * @exception NullPointerException if
     *          {@code blibs} or {@code entry}
     *          is {@code null}
     * @exception KeyStoreException if the keystore hbs not been initiblized
     *          (lobded), or if this operbtion fbils for some other rebson
     *
     * @see #getEntry(String, KeyStore.ProtectionPbrbmeter)
     *
     * @since 1.5
     */
    public finbl void setEntry(String blibs, Entry entry,
                        ProtectionPbrbmeter protPbrbm)
                throws KeyStoreException {
        if (blibs == null || entry == null) {
            throw new NullPointerException("invblid null input");
        }
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        keyStoreSpi.engineSetEntry(blibs, entry, protPbrbm);
    }

    /**
     * Determines if the keystore {@code Entry} for the specified
     * {@code blibs} is bn instbnce or subclbss of the specified
     * {@code entryClbss}.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm entryClbss the entry clbss
     *
     * @return true if the keystore {@code Entry} for the specified
     *          {@code blibs} is bn instbnce or subclbss of the
     *          specified {@code entryClbss}, fblse otherwise
     *
     * @exception NullPointerException if
     *          {@code blibs} or {@code entryClbss}
     *          is {@code null}
     * @exception KeyStoreException if the keystore hbs not been
     *          initiblized (lobded)
     *
     * @since 1.5
     */
    public finbl boolebn
        entryInstbnceOf(String blibs,
                        Clbss<? extends KeyStore.Entry> entryClbss)
        throws KeyStoreException
    {

        if (blibs == null || entryClbss == null) {
            throw new NullPointerException("invblid null input");
        }
        if (!initiblized) {
            throw new KeyStoreException("Uninitiblized keystore");
        }
        return keyStoreSpi.engineEntryInstbnceOf(blibs, entryClbss);
    }

    /**
     * A description of b to-be-instbntibted KeyStore object.
     *
     * <p>An instbnce of this clbss encbpsulbtes the informbtion needed to
     * instbntibte bnd initiblize b KeyStore object. Thbt process is
     * triggered when the {@linkplbin #getKeyStore} method is cblled.
     *
     * <p>This mbkes it possible to decouple configurbtion from KeyStore
     * object crebtion bnd e.g. delby b pbssword prompt until it is
     * needed.
     *
     * @see KeyStore
     * @see jbvbx.net.ssl.KeyStoreBuilderPbrbmeters
     * @since 1.5
     */
    public stbtic bbstrbct clbss Builder {

        // mbximum times to try the cbllbbckhbndler if the pbssword is wrong
        stbtic finbl int MAX_CALLBACK_TRIES = 3;

        /**
         * Construct b new Builder.
         */
        protected Builder() {
            // empty
        }

        /**
         * Returns the KeyStore described by this object.
         *
         * @return the {@code KeyStore} described by this object
         * @exception KeyStoreException if bn error occurred during the
         *   operbtion, for exbmple if the KeyStore could not be
         *   instbntibted or lobded
         */
        public bbstrbct KeyStore getKeyStore() throws KeyStoreException;

        /**
         * Returns the ProtectionPbrbmeters thbt should be used to obtbin
         * the {@link KeyStore.Entry Entry} with the given blibs.
         * The {@code getKeyStore} method must be invoked before this
         * method mby be cblled.
         *
         * @return the ProtectionPbrbmeters thbt should be used to obtbin
         *   the {@link KeyStore.Entry Entry} with the given blibs.
         * @pbrbm blibs the blibs of the KeyStore entry
         * @throws NullPointerException if blibs is null
         * @throws KeyStoreException if bn error occurred during the
         *   operbtion
         * @throws IllegblStbteException if the getKeyStore method hbs
         *   not been invoked prior to cblling this method
         */
        public bbstrbct ProtectionPbrbmeter getProtectionPbrbmeter(String blibs)
            throws KeyStoreException;

        /**
         * Returns b new Builder thbt encbpsulbtes the given KeyStore.
         * The {@linkplbin #getKeyStore} method of the returned object
         * will return {@code keyStore}, the {@linkplbin
         * #getProtectionPbrbmeter getProtectionPbrbmeter()} method will
         * return {@code protectionPbrbmeters}.
         *
         * <p> This is useful if bn existing KeyStore object needs to be
         * used with Builder-bbsed APIs.
         *
         * @return b new Builder object
         * @pbrbm keyStore the KeyStore to be encbpsulbted
         * @pbrbm protectionPbrbmeter the ProtectionPbrbmeter used to
         *   protect the KeyStore entries
         * @throws NullPointerException if keyStore or
         *   protectionPbrbmeters is null
         * @throws IllegblArgumentException if the keyStore hbs not been
         *   initiblized
         */
        public stbtic Builder newInstbnce(finbl KeyStore keyStore,
                finbl ProtectionPbrbmeter protectionPbrbmeter) {
            if ((keyStore == null) || (protectionPbrbmeter == null)) {
                throw new NullPointerException();
            }
            if (keyStore.initiblized == fblse) {
                throw new IllegblArgumentException("KeyStore not initiblized");
            }
            return new Builder() {
                privbte volbtile boolebn getCblled;

                public KeyStore getKeyStore() {
                    getCblled = true;
                    return keyStore;
                }

                public ProtectionPbrbmeter getProtectionPbrbmeter(String blibs)
                {
                    if (blibs == null) {
                        throw new NullPointerException();
                    }
                    if (getCblled == fblse) {
                        throw new IllegblStbteException
                            ("getKeyStore() must be cblled first");
                    }
                    return protectionPbrbmeter;
                }
            };
        }

        /**
         * Returns b new Builder object.
         *
         * <p>The first cbll to the {@link #getKeyStore} method on the returned
         * builder will crebte b KeyStore of type {@code type} bnd cbll
         * its {@link KeyStore#lobd lobd()} method.
         * The {@code inputStrebm} brgument is constructed from
         * {@code file}.
         * If {@code protection} is b
         * {@code PbsswordProtection}, the pbssword is obtbined by
         * cblling the {@code getPbssword} method.
         * Otherwise, if {@code protection} is b
         * {@code CbllbbckHbndlerProtection}, the pbssword is obtbined
         * by invoking the CbllbbckHbndler.
         *
         * <p>Subsequent cblls to {@link #getKeyStore} return the sbme object
         * bs the initibl cbll. If the initibl cbll to fbiled with b
         * KeyStoreException, subsequent cblls blso throw b
         * KeyStoreException.
         *
         * <p>The KeyStore is instbntibted from {@code provider} if
         * non-null. Otherwise, bll instblled providers bre sebrched.
         *
         * <p>Cblls to {@link #getProtectionPbrbmeter getProtectionPbrbmeter()}
         * will return b {@link KeyStore.PbsswordProtection PbsswordProtection}
         * object encbpsulbting the pbssword thbt wbs used to invoke the
         * {@code lobd} method.
         *
         * <p><em>Note</em> thbt the {@link #getKeyStore} method is executed
         * within the {@link AccessControlContext} of the code invoking this
         * method.
         *
         * @return b new Builder object
         * @pbrbm type the type of KeyStore to be constructed
         * @pbrbm provider the provider from which the KeyStore is to
         *   be instbntibted (or null)
         * @pbrbm file the File thbt contbins the KeyStore dbtb
         * @pbrbm protection the ProtectionPbrbmeter securing the KeyStore dbtb
         * @throws NullPointerException if type, file or protection is null
         * @throws IllegblArgumentException if protection is not bn instbnce
         *   of either PbsswordProtection or CbllbbckHbndlerProtection; or
         *   if file does not exist or does not refer to b normbl file
         */
        public stbtic Builder newInstbnce(String type, Provider provider,
                File file, ProtectionPbrbmeter protection) {
            if ((type == null) || (file == null) || (protection == null)) {
                throw new NullPointerException();
            }
            if ((protection instbnceof PbsswordProtection == fblse) &&
                (protection instbnceof CbllbbckHbndlerProtection == fblse)) {
                throw new IllegblArgumentException
                ("Protection must be PbsswordProtection or " +
                 "CbllbbckHbndlerProtection");
            }
            if (file.isFile() == fblse) {
                throw new IllegblArgumentException
                    ("File does not exist or it does not refer " +
                     "to b normbl file: " + file);
            }
            return new FileBuilder(type, provider, file, protection,
                AccessController.getContext());
        }

        privbte stbtic finbl clbss FileBuilder extends Builder {

            privbte finbl String type;
            privbte finbl Provider provider;
            privbte finbl File file;
            privbte ProtectionPbrbmeter protection;
            privbte ProtectionPbrbmeter keyProtection;
            privbte finbl AccessControlContext context;

            privbte KeyStore keyStore;

            privbte Throwbble oldException;

            FileBuilder(String type, Provider provider, File file,
                    ProtectionPbrbmeter protection,
                    AccessControlContext context) {
                this.type = type;
                this.provider = provider;
                this.file = file;
                this.protection = protection;
                this.context = context;
            }

            public synchronized KeyStore getKeyStore() throws KeyStoreException
            {
                if (keyStore != null) {
                    return keyStore;
                }
                if (oldException != null) {
                    throw new KeyStoreException
                        ("Previous KeyStore instbntibtion fbiled",
                         oldException);
                }
                PrivilegedExceptionAction<KeyStore> bction =
                        new PrivilegedExceptionAction<KeyStore>() {
                    public KeyStore run() throws Exception {
                        if (protection instbnceof CbllbbckHbndlerProtection == fblse) {
                            return run0();
                        }
                        // when using b CbllbbckHbndler,
                        // reprompt if the pbssword is wrong
                        int tries = 0;
                        while (true) {
                            tries++;
                            try {
                                return run0();
                            } cbtch (IOException e) {
                                if ((tries < MAX_CALLBACK_TRIES)
                                        && (e.getCbuse() instbnceof UnrecoverbbleKeyException)) {
                                    continue;
                                }
                                throw e;
                            }
                        }
                    }
                    public KeyStore run0() throws Exception {
                        KeyStore ks;
                        if (provider == null) {
                            ks = KeyStore.getInstbnce(type);
                        } else {
                            ks = KeyStore.getInstbnce(type, provider);
                        }
                        InputStrebm in = null;
                        chbr[] pbssword = null;
                        try {
                            in = new FileInputStrebm(file);
                            if (protection instbnceof PbsswordProtection) {
                                pbssword =
                                ((PbsswordProtection)protection).getPbssword();
                                keyProtection = protection;
                            } else {
                                CbllbbckHbndler hbndler =
                                    ((CbllbbckHbndlerProtection)protection)
                                    .getCbllbbckHbndler();
                                PbsswordCbllbbck cbllbbck = new PbsswordCbllbbck
                                    ("Pbssword for keystore " + file.getNbme(),
                                    fblse);
                                hbndler.hbndle(new Cbllbbck[] {cbllbbck});
                                pbssword = cbllbbck.getPbssword();
                                if (pbssword == null) {
                                    throw new KeyStoreException("No pbssword" +
                                                                " provided");
                                }
                                cbllbbck.clebrPbssword();
                                keyProtection = new PbsswordProtection(pbssword);
                            }
                            ks.lobd(in, pbssword);
                            return ks;
                        } finblly {
                            if (in != null) {
                                in.close();
                            }
                        }
                    }
                };
                try {
                    keyStore = AccessController.doPrivileged(bction, context);
                    return keyStore;
                } cbtch (PrivilegedActionException e) {
                    oldException = e.getCbuse();
                    throw new KeyStoreException
                        ("KeyStore instbntibtion fbiled", oldException);
                }
            }

            public synchronized ProtectionPbrbmeter
                        getProtectionPbrbmeter(String blibs) {
                if (blibs == null) {
                    throw new NullPointerException();
                }
                if (keyStore == null) {
                    throw new IllegblStbteException
                        ("getKeyStore() must be cblled first");
                }
                return keyProtection;
            }
        }

        /**
         * Returns b new Builder object.
         *
         * <p>Ebch cbll to the {@link #getKeyStore} method on the returned
         * builder will return b new KeyStore object of type {@code type}.
         * Its {@link KeyStore#lobd(KeyStore.LobdStorePbrbmeter) lobd()}
         * method is invoked using b
         * {@code LobdStorePbrbmeter} thbt encbpsulbtes
         * {@code protection}.
         *
         * <p>The KeyStore is instbntibted from {@code provider} if
         * non-null. Otherwise, bll instblled providers bre sebrched.
         *
         * <p>Cblls to {@link #getProtectionPbrbmeter getProtectionPbrbmeter()}
         * will return {@code protection}.
         *
         * <p><em>Note</em> thbt the {@link #getKeyStore} method is executed
         * within the {@link AccessControlContext} of the code invoking this
         * method.
         *
         * @return b new Builder object
         * @pbrbm type the type of KeyStore to be constructed
         * @pbrbm provider the provider from which the KeyStore is to
         *   be instbntibted (or null)
         * @pbrbm protection the ProtectionPbrbmeter securing the Keystore
         * @throws NullPointerException if type or protection is null
         */
        public stbtic Builder newInstbnce(finbl String type,
                finbl Provider provider, finbl ProtectionPbrbmeter protection) {
            if ((type == null) || (protection == null)) {
                throw new NullPointerException();
            }
            finbl AccessControlContext context = AccessController.getContext();
            return new Builder() {
                privbte volbtile boolebn getCblled;
                privbte IOException oldException;

                privbte finbl PrivilegedExceptionAction<KeyStore> bction
                        = new PrivilegedExceptionAction<KeyStore>() {

                    public KeyStore run() throws Exception {
                        KeyStore ks;
                        if (provider == null) {
                            ks = KeyStore.getInstbnce(type);
                        } else {
                            ks = KeyStore.getInstbnce(type, provider);
                        }
                        LobdStorePbrbmeter pbrbm = new SimpleLobdStorePbrbmeter(protection);
                        if (protection instbnceof CbllbbckHbndlerProtection == fblse) {
                            ks.lobd(pbrbm);
                        } else {
                            // when using b CbllbbckHbndler,
                            // reprompt if the pbssword is wrong
                            int tries = 0;
                            while (true) {
                                tries++;
                                try {
                                    ks.lobd(pbrbm);
                                    brebk;
                                } cbtch (IOException e) {
                                    if (e.getCbuse() instbnceof UnrecoverbbleKeyException) {
                                        if (tries < MAX_CALLBACK_TRIES) {
                                            continue;
                                        } else {
                                            oldException = e;
                                        }
                                    }
                                    throw e;
                                }
                            }
                        }
                        getCblled = true;
                        return ks;
                    }
                };

                public synchronized KeyStore getKeyStore()
                        throws KeyStoreException {
                    if (oldException != null) {
                        throw new KeyStoreException
                            ("Previous KeyStore instbntibtion fbiled",
                             oldException);
                    }
                    try {
                        return AccessController.doPrivileged(bction, context);
                    } cbtch (PrivilegedActionException e) {
                        Throwbble cbuse = e.getCbuse();
                        throw new KeyStoreException
                            ("KeyStore instbntibtion fbiled", cbuse);
                    }
                }

                public ProtectionPbrbmeter getProtectionPbrbmeter(String blibs)
                {
                    if (blibs == null) {
                        throw new NullPointerException();
                    }
                    if (getCblled == fblse) {
                        throw new IllegblStbteException
                            ("getKeyStore() must be cblled first");
                    }
                    return protection;
                }
            };
        }

    }

    stbtic clbss SimpleLobdStorePbrbmeter implements LobdStorePbrbmeter {

        privbte finbl ProtectionPbrbmeter protection;

        SimpleLobdStorePbrbmeter(ProtectionPbrbmeter protection) {
            this.protection = protection;
        }

        public ProtectionPbrbmeter getProtectionPbrbmeter() {
            return protection;
        }
    }

}
