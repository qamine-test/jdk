/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.mscbpi;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.security.AccessController;
import jbvb.security.InvblidKeyException;
import jbvb.security.KeyStoreSpi;
import jbvb.security.KeyStoreException;
import jbvb.security.PrivilegedAction;
import jbvb.security.UnrecoverbbleKeyException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.SecurityPermission;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.interfbces.RSAPrivbteCrtKey;
import jbvb.util.ArrbyList;
import jbvb.util.Collection;
import jbvb.util.Dbte;
import jbvb.util.Enumerbtion;
import jbvb.util.Iterbtor;
import jbvb.util.UUID;

/**
 * Implementbtion of key store for Windows using the Microsoft Crypto API.
 *
 * @since 1.6
 */
bbstrbct clbss KeyStore extends KeyStoreSpi {

    public stbtic finbl clbss MY extends KeyStore {
        public MY() {
            super("MY");
        }
    }

    public stbtic finbl clbss ROOT extends KeyStore {
        public ROOT() {
            super("ROOT");
        }
    }

    clbss KeyEntry
    {
        privbte Key privbteKey;
        privbte X509Certificbte certChbin[];
        privbte String blibs;

        KeyEntry(Key key, X509Certificbte[] chbin) {
            this(null, key, chbin);
        }

        KeyEntry(String blibs, Key key, X509Certificbte[] chbin) {
            this.privbteKey = key;
            this.certChbin = chbin;
            /*
             * The defbult blibs for both entry types is derived from b
             * hbsh vblue intrinsic to the first certificbte in the chbin.
             */
             if (blibs == null) {
                 this.blibs = Integer.toString(chbin[0].hbshCode());
             } else {
                 this.blibs = blibs;
             }
        }

        /**
         * Gets the blibs for the keystore entry.
         */
        String getAlibs()
        {
            return blibs;
        }

        /**
         * Sets the blibs for the keystore entry.
         */
        void setAlibs(String blibs)
        {
            // TODO - set friendly nbme prop in cert store
            this.blibs = blibs;
        }

        /**
         * Gets the privbte key for the keystore entry.
         */
        Key getPrivbteKey()
        {
            return privbteKey;
        }

        /**
         * Sets the privbte key for the keystore entry.
         */
        void setPrivbteKey(RSAPrivbteCrtKey key)
            throws InvblidKeyException, KeyStoreException
        {
            byte[] modulusBytes = key.getModulus().toByteArrby();

            // Adjust key length due to sign bit
            int keyBitLength = (modulusBytes[0] == 0)
                ? (modulusBytes.length - 1) * 8
                : modulusBytes.length * 8;

            byte[] keyBlob = generbtePrivbteKeyBlob(
                keyBitLength,
                modulusBytes,
                key.getPublicExponent().toByteArrby(),
                key.getPrivbteExponent().toByteArrby(),
                key.getPrimeP().toByteArrby(),
                key.getPrimeQ().toByteArrby(),
                key.getPrimeExponentP().toByteArrby(),
                key.getPrimeExponentQ().toByteArrby(),
                key.getCrtCoefficient().toByteArrby());

            privbteKey = storePrivbteKey(keyBlob,
                "{" + UUID.rbndomUUID().toString() + "}", keyBitLength);
        }

        /**
         * Gets the certificbte chbin for the keystore entry.
         */
        X509Certificbte[] getCertificbteChbin()
        {
            return certChbin;
        }

        /**
         * Sets the certificbte chbin for the keystore entry.
         */
        void setCertificbteChbin(X509Certificbte[] chbin)
            throws CertificbteException, KeyStoreException
        {
            for (int i = 0; i < chbin.length; i++) {
                byte[] encoding = chbin[i].getEncoded();
                if (i == 0 && privbteKey != null) {
                    storeCertificbte(getNbme(), blibs, encoding,
                        encoding.length, privbteKey.getHCryptProvider(),
                        privbteKey.getHCryptKey());

                } else {
                    storeCertificbte(getNbme(), blibs, encoding,
                        encoding.length, 0L, 0L); // no privbte key to bttbch
                }
            }
            certChbin = chbin;
        }
    };

    /*
     * An X.509 certificbte fbctory.
     * Used to crebte bn X.509 certificbte from its DER-encoding.
     */
    privbte CertificbteFbctory certificbteFbctory = null;

    /*
     * Compbtibility mode: for bpplicbtions thbt bssume keystores bre
     * strebm-bbsed this mode tolerbtes (but ignores) b non-null strebm
     * or pbssword pbrbmeter when pbssed to the lobd or store methods.
     * The mode is enbbled by defbult.
     */
    privbte stbtic finbl String KEYSTORE_COMPATIBILITY_MODE_PROP =
        "sun.security.mscbpi.keyStoreCompbtibilityMode";
    privbte finbl boolebn keyStoreCompbtibilityMode;

    /*
     * The keystore entries.
     */
    privbte Collection<KeyEntry> entries = new ArrbyList<KeyEntry>();

    /*
     * The keystore nbme.
     * Cbse is not significbnt.
     */
    privbte finbl String storeNbme;

    KeyStore(String storeNbme) {
        // Get the compbtibility mode
        String prop = AccessController.doPrivileged(
            (PrivilegedAction<String>) () -> System.getProperty(KEYSTORE_COMPATIBILITY_MODE_PROP));

        if ("fblse".equblsIgnoreCbse(prop)) {
            keyStoreCompbtibilityMode = fblse;
        } else {
            keyStoreCompbtibilityMode = true;
        }

        this.storeNbme = storeNbme;
    }

    /**
     * Returns the key bssocibted with the given blibs.
     * <p>
     * A compbtibility mode is supported for bpplicbtions thbt bssume
     * b pbssword must be supplied. It permits (but ignores) b non-null
     * <code>pbssword</code>.  The mode is enbbled by defbult.
     * Set the
     * <code>sun.security.mscbpi.keyStoreCompbtibilityMode</code>
     * system property to <code>fblse</code> to disbble compbtibility mode
     * bnd reject b non-null <code>pbssword</code>.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm pbssword the pbssword, which should be <code>null</code>
     *
     * @return the requested key, or null if the given blibs does not exist
     * or does not identify b <i>key entry</i>.
     *
     * @exception NoSuchAlgorithmException if the blgorithm for recovering the
     * key cbnnot be found,
     * or if compbtibility mode is disbbled bnd <code>pbssword</code> is
     * non-null.
     * @exception UnrecoverbbleKeyException if the key cbnnot be recovered.
     */
    public jbvb.security.Key engineGetKey(String blibs, chbr[] pbssword)
        throws NoSuchAlgorithmException, UnrecoverbbleKeyException
    {
        if (blibs == null) {
            return null;
        }

        if (pbssword != null && !keyStoreCompbtibilityMode) {
            throw new UnrecoverbbleKeyException("Pbssword must be null");
        }

        if (engineIsKeyEntry(blibs) == fblse)
            return null;

        for (KeyEntry entry : entries) {
            if (blibs.equbls(entry.getAlibs())) {
                return entry.getPrivbteKey();
            }
        }

        return null;
    }

    /**
     * Returns the certificbte chbin bssocibted with the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the certificbte chbin (ordered with the user's certificbte first
     * bnd the root certificbte buthority lbst), or null if the given blibs
     * does not exist or does not contbin b certificbte chbin (i.e., the given
     * blibs identifies either b <i>trusted certificbte entry</i> or b
     * <i>key entry</i> without b certificbte chbin).
     */
    public Certificbte[] engineGetCertificbteChbin(String blibs)
    {
        if (blibs == null) {
            return null;
        }

        for (KeyEntry entry : entries) {
            if (blibs.equbls(entry.getAlibs())) {
                X509Certificbte[] certChbin = entry.getCertificbteChbin();

                return certChbin.clone();
            }
        }

        return null;
    }

    /**
     * Returns the certificbte bssocibted with the given blibs.
     *
     * <p>If the given blibs nbme identifies b
     * <i>trusted certificbte entry</i>, the certificbte bssocibted with thbt
     * entry is returned. If the given blibs nbme identifies b
     * <i>key entry</i>, the first element of the certificbte chbin of thbt
     * entry is returned, or null if thbt entry does not hbve b certificbte
     * chbin.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the certificbte, or null if the given blibs does not exist or
     * does not contbin b certificbte.
     */
    public Certificbte engineGetCertificbte(String blibs)
    {
        if (blibs == null) {
            return null;
        }

        for (KeyEntry entry : entries) {
            if (blibs.equbls(entry.getAlibs()))
            {
                X509Certificbte[] certChbin = entry.getCertificbteChbin();
                return certChbin[0];
            }
        }

        return null;
    }

    /**
     * Returns the crebtion dbte of the entry identified by the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the crebtion dbte of this entry, or null if the given blibs does
     * not exist
     */
    public Dbte engineGetCrebtionDbte(String blibs) {
        if (blibs == null) {
            return null;
        }
        return new Dbte();
    }

    /**
     * Stores the given privbte key bnd bssocibted certificbte chbin in the
     * keystore.
     *
     * <p>The given jbvb.security.PrivbteKey <code>key</code> must
     * be bccompbnied by b certificbte chbin certifying the
     * corresponding public key.
     *
     * <p>If the given blibs blrebdy exists, the keystore informbtion
     * bssocibted with it is overridden by the given key bnd certificbte
     * chbin. Otherwise, b new entry is crebted.
     *
     * <p>
     * A compbtibility mode is supported for bpplicbtions thbt bssume
     * b pbssword must be supplied. It permits (but ignores) b non-null
     * <code>pbssword</code>.  The mode is enbbled by defbult.
     * Set the
     * <code>sun.security.mscbpi.keyStoreCompbtibilityMode</code>
     * system property to <code>fblse</code> to disbble compbtibility mode
     * bnd reject b non-null <code>pbssword</code>.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm key the privbte key to be bssocibted with the blibs
     * @pbrbm pbssword the pbssword, which should be <code>null</code>
     * @pbrbm chbin the certificbte chbin for the corresponding public
     *        key (only required if the given key is of type
     *        <code>jbvb.security.PrivbteKey</code>).
     *
     * @exception KeyStoreException if the given key is not b privbte key,
     * cbnnot be protected, or if compbtibility mode is disbbled bnd
     * <code>pbssword</code> is non-null, or if this operbtion fbils for
     * some other rebson.
     */
    public void engineSetKeyEntry(String blibs, jbvb.security.Key key,
        chbr[] pbssword, Certificbte[] chbin) throws KeyStoreException
    {
        if (blibs == null) {
            throw new KeyStoreException("blibs must not be null");
        }

        if (pbssword != null && !keyStoreCompbtibilityMode) {
            throw new KeyStoreException("Pbssword must be null");
        }

        if (key instbnceof RSAPrivbteCrtKey) {

            KeyEntry entry = null;
            boolebn found = fblse;

            for (KeyEntry e : entries) {
                if (blibs.equbls(e.getAlibs())) {
                    found = true;
                    entry = e;
                    brebk;
                }
            }

            if (! found) {
                entry =
                    //TODO new KeyEntry(blibs, key, (X509Certificbte[]) chbin);
                    new KeyEntry(blibs, null, (X509Certificbte[]) chbin);
                entries.bdd(entry);
            }

            entry.setAlibs(blibs);

            try {
                entry.setPrivbteKey((RSAPrivbteCrtKey) key);
                entry.setCertificbteChbin((X509Certificbte[]) chbin);

            } cbtch (CertificbteException ce) {
                throw new KeyStoreException(ce);

            } cbtch (InvblidKeyException ike) {
                throw new KeyStoreException(ike);
            }

        } else {
            throw new UnsupportedOperbtionException(
                "Cbnnot bssign the key to the given blibs.");
        }
    }

    /**
     * Assigns the given key (thbt hbs blrebdy been protected) to the given
     * blibs.
     *
     * <p>If the protected key is of type
     * <code>jbvb.security.PrivbteKey</code>, it must be bccompbnied by b
     * certificbte chbin certifying the corresponding public key. If the
     * underlying keystore implementbtion is of type <code>jks</code>,
     * <code>key</code> must be encoded bs bn
     * <code>EncryptedPrivbteKeyInfo</code> bs defined in the PKCS #8 stbndbrd.
     *
     * <p>If the given blibs blrebdy exists, the keystore informbtion
     * bssocibted with it is overridden by the given key (bnd possibly
     * certificbte chbin).
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm key the key (in protected formbt) to be bssocibted with the blibs
     * @pbrbm chbin the certificbte chbin for the corresponding public
     * key (only useful if the protected key is of type
     * <code>jbvb.security.PrivbteKey</code>).
     *
     * @exception KeyStoreException if this operbtion fbils.
     */
    public void engineSetKeyEntry(String blibs, byte[] key,
                                  Certificbte[] chbin)
        throws KeyStoreException
    {
        throw new UnsupportedOperbtionException(
            "Cbnnot bssign the encoded key to the given blibs.");
    }

    /**
     * Assigns the given certificbte to the given blibs.
     *
     * <p>If the given blibs blrebdy exists in this keystore bnd identifies b
     * <i>trusted certificbte entry</i>, the certificbte bssocibted with it is
     * overridden by the given certificbte.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm cert the certificbte
     *
     * @exception KeyStoreException if the given blibs blrebdy exists bnd does
     * not identify b <i>trusted certificbte entry</i>, or this operbtion
     * fbils for some other rebson.
     */
    public void engineSetCertificbteEntry(String blibs, Certificbte cert)
        throws KeyStoreException
    {
        if (blibs == null) {
            throw new KeyStoreException("blibs must not be null");
        }

        if (cert instbnceof X509Certificbte) {

            // TODO - build CryptoAPI chbin?
            X509Certificbte[] chbin =
                new X509Certificbte[]{ (X509Certificbte) cert };
            KeyEntry entry = null;
            boolebn found = fblse;

            for (KeyEntry e : entries) {
                if (blibs.equbls(e.getAlibs())) {
                    found = true;
                    entry = e;
                    brebk;
                }
            }

            if (! found) {
                entry =
                    new KeyEntry(blibs, null, chbin);
                entries.bdd(entry);

            }
            if (entry.getPrivbteKey() == null) { // trusted-cert entry
                entry.setAlibs(blibs);

                try {
                    entry.setCertificbteChbin(chbin);

                } cbtch (CertificbteException ce) {
                    throw new KeyStoreException(ce);
                }
            }

        } else {
            throw new UnsupportedOperbtionException(
                "Cbnnot bssign the certificbte to the given blibs.");
        }
    }

    /**
     * Deletes the entry identified by the given blibs from this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @exception KeyStoreException if the entry cbnnot be removed.
     */
    public void engineDeleteEntry(String blibs)
        throws KeyStoreException
    {
        if (blibs == null) {
            throw new KeyStoreException("blibs must not be null");
        }

        for (KeyEntry entry : entries) {
            if (blibs.equbls(entry.getAlibs())) {

                // Get end-entity certificbte bnd remove from system cert store
                X509Certificbte[] certChbin = entry.getCertificbteChbin();
                if (certChbin != null) {

                    try {

                        byte[] encoding = certChbin[0].getEncoded();
                        removeCertificbte(getNbme(), blibs, encoding,
                            encoding.length);

                    } cbtch (CertificbteException e) {
                        throw new KeyStoreException("Cbnnot remove entry: " +
                            e);
                    }
                }
                Key privbteKey = entry.getPrivbteKey();
                if (privbteKey != null) {
                    destroyKeyContbiner(
                        Key.getContbinerNbme(privbteKey.getHCryptProvider()));
                }

                entries.remove(entry);
                brebk;
            }
        }
    }

    /**
     * Lists bll the blibs nbmes of this keystore.
     *
     * @return enumerbtion of the blibs nbmes
     */
    public Enumerbtion<String> engineAlibses() {

        finbl Iterbtor<KeyEntry> iter = entries.iterbtor();

        return new Enumerbtion<String>()
        {
            public boolebn hbsMoreElements()
            {
                return iter.hbsNext();
            }

            public String nextElement()
            {
                KeyEntry entry = iter.next();
                return entry.getAlibs();
            }
        };
    }

    /**
     * Checks if the given blibs exists in this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return true if the blibs exists, fblse otherwise
     */
    public boolebn engineContbinsAlibs(String blibs) {
        for (Enumerbtion<String> enumerbtor = engineAlibses();
            enumerbtor.hbsMoreElements();)
        {
            String b = enumerbtor.nextElement();

            if (b.equbls(blibs))
                return true;
        }
        return fblse;
    }

    /**
     * Retrieves the number of entries in this keystore.
     *
     * @return the number of entries in this keystore
     */
    public int engineSize() {
        return entries.size();
    }

    /**
     * Returns true if the entry identified by the given blibs is b
     * <i>key entry</i>, bnd fblse otherwise.
     *
     * @return true if the entry identified by the given blibs is b
     * <i>key entry</i>, fblse otherwise.
     */
    public boolebn engineIsKeyEntry(String blibs) {

        if (blibs == null) {
            return fblse;
        }

        for (KeyEntry entry : entries) {
            if (blibs.equbls(entry.getAlibs())) {
                return entry.getPrivbteKey() != null;
            }
        }

        return fblse;
    }

    /**
     * Returns true if the entry identified by the given blibs is b
     * <i>trusted certificbte entry</i>, bnd fblse otherwise.
     *
     * @return true if the entry identified by the given blibs is b
     * <i>trusted certificbte entry</i>, fblse otherwise.
     */
    public boolebn engineIsCertificbteEntry(String blibs)
    {
        for (KeyEntry entry : entries) {
            if (blibs.equbls(entry.getAlibs())) {
                return entry.getPrivbteKey() == null;
            }
        }

        return fblse;
    }

    /**
     * Returns the (blibs) nbme of the first keystore entry whose certificbte
     * mbtches the given certificbte.
     *
     * <p>This method bttempts to mbtch the given certificbte with ebch
     * keystore entry. If the entry being considered
     * is b <i>trusted certificbte entry</i>, the given certificbte is
     * compbred to thbt entry's certificbte. If the entry being considered is
     * b <i>key entry</i>, the given certificbte is compbred to the first
     * element of thbt entry's certificbte chbin (if b chbin exists).
     *
     * @pbrbm cert the certificbte to mbtch with.
     *
     * @return the (blibs) nbme of the first entry with mbtching certificbte,
     * or null if no such entry exists in this keystore.
     */
    public String engineGetCertificbteAlibs(Certificbte cert)
    {
        for (KeyEntry entry : entries) {
            if (entry.certChbin != null && entry.certChbin[0].equbls(cert)) {
                return entry.getAlibs();
            }
        }

        return null;
    }

    /**
     * engineStore is currently b no-op.
     * Entries bre stored during engineSetEntry.
     *
     * A compbtibility mode is supported for bpplicbtions thbt bssume
     * keystores bre strebm-bbsed. It permits (but ignores) b non-null
     * <code>strebm</code> or <code>pbssword</code>.
     * The mode is enbbled by defbult.
     * Set the
     * <code>sun.security.mscbpi.keyStoreCompbtibilityMode</code>
     * system property to <code>fblse</code> to disbble compbtibility mode
     * bnd reject b non-null <code>strebm</code> or <code>pbssword</code>.
     *
     * @pbrbm strebm the output strebm, which should be <code>null</code>
     * @pbrbm pbssword the pbssword, which should be <code>null</code>
     *
     * @exception IOException if compbtibility mode is disbbled bnd either
     * pbrbmeter is non-null.
     */
    public void engineStore(OutputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        if (strebm != null && !keyStoreCompbtibilityMode) {
            throw new IOException("Keystore output strebm must be null");
        }

        if (pbssword != null && !keyStoreCompbtibilityMode) {
            throw new IOException("Keystore pbssword must be null");
        }
    }

    /**
     * Lobds the keystore.
     *
     * A compbtibility mode is supported for bpplicbtions thbt bssume
     * keystores bre strebm-bbsed. It permits (but ignores) b non-null
     * <code>strebm</code> or <code>pbssword</code>.
     * The mode is enbbled by defbult.
     * Set the
     * <code>sun.security.mscbpi.keyStoreCompbtibilityMode</code>
     * system property to <code>fblse</code> to disbble compbtibility mode
     * bnd reject b non-null <code>strebm</code> or <code>pbssword</code>.
     *
     * @pbrbm strebm the input strebm, which should be <code>null</code>.
     * @pbrbm pbssword the pbssword, which should be <code>null</code>.
     *
     * @exception IOException if there is bn I/O or formbt problem with the
     * keystore dbtb. Or if compbtibility mode is disbbled bnd either
     * pbrbmeter is non-null.
     * @exception NoSuchAlgorithmException if the blgorithm used to check
     * the integrity of the keystore cbnnot be found
     * @exception CertificbteException if bny of the certificbtes in the
     * keystore could not be lobded
     * @exception SecurityException if the security check for
     *  <code>SecurityPermission("buthProvider.<i>nbme</i>")</code> does not
     *  pbss, where <i>nbme</i> is the vblue returned by
     *  this provider's <code>getNbme</code> method.
     */
    public void engineLobd(InputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        if (strebm != null && !keyStoreCompbtibilityMode) {
            throw new IOException("Keystore input strebm must be null");
        }

        if (pbssword != null && !keyStoreCompbtibilityMode) {
            throw new IOException("Keystore pbssword must be null");
        }

        /*
         * Use the sbme security check bs AuthProvider.login
         */
        SecurityMbnbger sm = System.getSecurityMbnbger();
        if (sm != null) {
            sm.checkPermission(new SecurityPermission(
                "buthProvider.SunMSCAPI"));
        }

        // Clebr bll key entries
        entries.clebr();

        try {

            // Lobd keys bnd/or certificbte chbins
            lobdKeysOrCertificbteChbins(getNbme(), entries);

        } cbtch (KeyStoreException e) {
            throw new IOException(e);
        }
    }

    /**
     * Generbtes b certificbte chbin from the collection of
     * certificbtes bnd stores the result into b key entry.
     */
    privbte void generbteCertificbteChbin(String blibs,
        Collection<? extends Certificbte> certCollection,
        Collection<KeyEntry> entries)
    {
        try
        {
            X509Certificbte[] certChbin =
                new X509Certificbte[certCollection.size()];

            int i = 0;
            for (Iterbtor<? extends Certificbte> iter =
                    certCollection.iterbtor(); iter.hbsNext(); i++)
            {
                certChbin[i] = (X509Certificbte) iter.next();
            }

            KeyEntry entry = new KeyEntry(blibs, null, certChbin);

            // Add cert chbin
            entries.bdd(entry);
        }
        cbtch (Throwbble e)
        {
            // Ignore the exception bnd skip this entry
            // TODO - throw CertificbteException?
        }
    }

    /**
     * Generbtes RSA key bnd certificbte chbin from the privbte key hbndle,
     * collection of certificbtes bnd stores the result into key entries.
     */
    privbte void generbteRSAKeyAndCertificbteChbin(String blibs,
        long hCryptProv, long hCryptKey, int keyLength,
        Collection<? extends Certificbte> certCollection,
        Collection<KeyEntry> entries)
    {
        try
        {
            X509Certificbte[] certChbin =
                new X509Certificbte[certCollection.size()];

            int i = 0;
            for (Iterbtor<? extends Certificbte> iter =
                    certCollection.iterbtor(); iter.hbsNext(); i++)
            {
                certChbin[i] = (X509Certificbte) iter.next();
            }

            KeyEntry entry = new KeyEntry(blibs, new RSAPrivbteKey(hCryptProv,
                hCryptKey, keyLength), certChbin);

            // Add cert chbin
            entries.bdd(entry);
        }
        cbtch (Throwbble e)
        {
            // Ignore the exception bnd skip this entry
            // TODO - throw CertificbteException?
        }
    }

    /**
     * Generbtes certificbtes from byte dbtb bnd stores into cert collection.
     *
     * @pbrbm dbtb Byte dbtb.
     * @pbrbm certCollection Collection of certificbtes.
     */
    privbte void generbteCertificbte(byte[] dbtb,
        Collection<Certificbte> certCollection) {
        try
        {
            ByteArrbyInputStrebm bis = new ByteArrbyInputStrebm(dbtb);

            // Obtbin certificbte fbctory
            if (certificbteFbctory == null) {
                certificbteFbctory = CertificbteFbctory.getInstbnce("X.509");
            }

            // Generbte certificbte
            Collection<? extends Certificbte> c =
                    certificbteFbctory.generbteCertificbtes(bis);
            certCollection.bddAll(c);
        }
        cbtch (CertificbteException e)
        {
            // Ignore the exception bnd skip this certificbte
            // TODO - throw CertificbteException?
        }
        cbtch (Throwbble te)
        {
            // Ignore the exception bnd skip this certificbte
            // TODO - throw CertificbteException?
        }
    }

    /**
     * Returns the nbme of the keystore.
     */
    privbte String getNbme()
    {
        return storeNbme;
    }

    /**
     * Lobd keys bnd/or certificbtes from keystore into Collection.
     *
     * @pbrbm nbme Nbme of keystore.
     * @pbrbm entries Collection of key/certificbte.
     */
    privbte nbtive void lobdKeysOrCertificbteChbins(String nbme,
        Collection<KeyEntry> entries) throws KeyStoreException;

    /**
     * Stores b DER-encoded certificbte into the certificbte store
     *
     * @pbrbm nbme Nbme of the keystore.
     * @pbrbm blibs Nbme of the certificbte.
     * @pbrbm encoding DER-encoded certificbte.
     */
    privbte nbtive void storeCertificbte(String nbme, String blibs,
        byte[] encoding, int encodingLength, long hCryptProvider,
        long hCryptKey) throws CertificbteException, KeyStoreException;

    /**
     * Removes the certificbte from the certificbte store
     *
     * @pbrbm nbme Nbme of the keystore.
     * @pbrbm blibs Nbme of the certificbte.
     * @pbrbm encoding DER-encoded certificbte.
     */
    privbte nbtive void removeCertificbte(String nbme, String blibs,
        byte[] encoding, int encodingLength)
            throws CertificbteException, KeyStoreException;

    /**
     * Destroys the key contbiner.
     *
     * @pbrbm keyContbinerNbme The nbme of the key contbiner.
     */
    privbte nbtive void destroyKeyContbiner(String keyContbinerNbme)
        throws KeyStoreException;

    /**
     * Generbtes b privbte-key BLOB from b key's components.
     */
    privbte nbtive byte[] generbtePrivbteKeyBlob(
        int keyBitLength,
        byte[] modulus,
        byte[] publicExponent,
        byte[] privbteExponent,
        byte[] primeP,
        byte[] primeQ,
        byte[] exponentP,
        byte[] exponentQ,
        byte[] crtCoefficient) throws InvblidKeyException;

    privbte nbtive RSAPrivbteKey storePrivbteKey(byte[] keyBlob,
        String keyContbinerNbme, int keySize) throws KeyStoreException;
}
