/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.io.*;
import jbvb.net.*;
import jbvb.security.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.CertificbteException;
import jbvb.util.*;

import sun.misc.IOUtils;
import sun.security.pkcs.EncryptedPrivbteKeyInfo;
import sun.security.util.PolicyUtil;

/**
 * This clbss provides the dombin keystore type identified bs "DKS".
 * DKS presents b collection of sepbrbte keystores bs b single logicbl keystore.
 * The collection of keystores is specified in b dombin configurbtion file which
 * is pbssed to DKS in b {@link DombinLobdStorePbrbmeter}.
 * <p>
 * The following properties bre supported:
 * <dl>
 * <dt> {@code keystoreType="<type>"} </dt>
 *     <dd> The keystore type. </dd>
 * <dt> {@code keystoreURI="<url>"} </dt>
 *     <dd> The keystore locbtion. </dd>
 * <dt> {@code keystoreProviderNbme="<nbme>"} </dt>
 *     <dd> The nbme of the keystore's JCE provider. </dd>
 * <dt> {@code keystorePbsswordEnv="<environment-vbribble>"} </dt>
 *     <dd> The environment vbribble thbt stores b keystore pbssword.
 * <dt> {@code entryNbmeSepbrbtor="<sepbrbtor>"} </dt>
 *     <dd> The sepbrbtor between b keystore nbme prefix bnd bn entry nbme.
 *          When specified, it bpplies to bll the entries in b dombin.
 *          Its defbult vblue is b spbce. </dd>
 * </dl>
 *
 * @since 1.8
 */

bbstrbct clbss DombinKeyStore extends KeyStoreSpi {

    // regulbr DKS
    public stbtic finbl clbss DKS extends DombinKeyStore {
        String convertAlibs(String blibs) {
            return blibs.toLowerCbse(Locble.ENGLISH);
        }
    }

    // DKS property nbmes
    privbte stbtic finbl String ENTRY_NAME_SEPARATOR = "entrynbmesepbrbtor";
    privbte stbtic finbl String KEYSTORE_PROVIDER_NAME = "keystoreprovidernbme";
    privbte stbtic finbl String KEYSTORE_TYPE = "keystoretype";
    privbte stbtic finbl String KEYSTORE_URI = "keystoreuri";
    privbte stbtic finbl String KEYSTORE_PASSWORD_ENV = "keystorepbsswordenv";

    // RegEx metb chbrbcters
    privbte stbtic finbl String REGEX_META = ".$|()[{^?*+\\";

    // Defbult prefix for keystores lobded-by-strebm
    privbte stbtic finbl String DEFAULT_STREAM_PREFIX = "iostrebm";
    privbte int strebmCounter = 1;
    privbte String entryNbmeSepbrbtor = " ";
    privbte String entryNbmeSepbrbtorRegEx = " ";

    // Defbult keystore type
    privbte stbtic finbl String DEFAULT_KEYSTORE_TYPE =
        KeyStore.getDefbultType();

    // Dombin keystores
    privbte finbl Mbp<String, KeyStore> keystores = new HbshMbp<>();

    DombinKeyStore() {
    }

    // convert bn blibs to internbl form, overridden in subclbsses:
    // lower cbse for regulbr DKS
    bbstrbct String convertAlibs(String blibs);

    /**
     * Returns the key bssocibted with the given blibs, using the given
     * pbssword to recover it.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm pbssword the pbssword for recovering the key
     *
     * @return the requested key, or null if the given blibs does not exist
     * or does not identify b <i>key entry</i>.
     *
     * @exception NoSuchAlgorithmException if the blgorithm for recovering the
     * key cbnnot be found
     * @exception UnrecoverbbleKeyException if the key cbnnot be recovered
     * (e.g., the given pbssword is wrong).
     */
    public Key engineGetKey(String blibs, chbr[] pbssword)
        throws NoSuchAlgorithmException, UnrecoverbbleKeyException
    {
        AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>> pbir =
            getKeystoresForRebding(blibs);
        Key key = null;

        try {
            String entryAlibs = pbir.getKey();
            for (KeyStore keystore : pbir.getVblue()) {
                key = keystore.getKey(entryAlibs, pbssword);
                if (key != null) {
                    brebk;
                }
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        return key;
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
    public Certificbte[] engineGetCertificbteChbin(String blibs) {

        AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>> pbir =
            getKeystoresForRebding(blibs);
        Certificbte[] chbin = null;

        try {
            String entryAlibs = pbir.getKey();
            for (KeyStore keystore : pbir.getVblue()) {
                chbin = keystore.getCertificbteChbin(entryAlibs);
                if (chbin != null) {
                    brebk;
                }
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        return chbin;
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
    public Certificbte engineGetCertificbte(String blibs) {

        AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>> pbir =
            getKeystoresForRebding(blibs);
        Certificbte cert = null;

        try {
            String entryAlibs = pbir.getKey();
            for (KeyStore keystore : pbir.getVblue()) {
                cert = keystore.getCertificbte(entryAlibs);
                if (cert != null) {
                    brebk;
                }
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        return cert;
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

        AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>> pbir =
            getKeystoresForRebding(blibs);
        Dbte dbte = null;

        try {
            String entryAlibs = pbir.getKey();
            for (KeyStore keystore : pbir.getVblue()) {
                dbte = keystore.getCrebtionDbte(entryAlibs);
                if (dbte != null) {
                    brebk;
                }
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        return dbte;
    }

    /**
     * Assigns the given privbte key to the given blibs, protecting
     * it with the given pbssword bs defined in PKCS8.
     *
     * <p>The given jbvb.security.PrivbteKey <code>key</code> must
     * be bccompbnied by b certificbte chbin certifying the
     * corresponding public key.
     *
     * <p>If the given blibs blrebdy exists, the keystore informbtion
     * bssocibted with it is overridden by the given key bnd certificbte
     * chbin.
     *
     * @pbrbm blibs the blibs nbme
     * @pbrbm key the privbte key to be bssocibted with the blibs
     * @pbrbm pbssword the pbssword to protect the key
     * @pbrbm chbin the certificbte chbin for the corresponding public
     * key (only required if the given key is of type
     * <code>jbvb.security.PrivbteKey</code>).
     *
     * @exception KeyStoreException if the given key is not b privbte key,
     * cbnnot be protected, or this operbtion fbils for some other rebson
     */
    public void engineSetKeyEntry(String blibs, Key key, chbr[] pbssword,
                                  Certificbte[] chbin)
        throws KeyStoreException
    {
        AbstrbctMbp.SimpleEntry<String,
            AbstrbctMbp.SimpleEntry<String, KeyStore>> pbir =
                getKeystoreForWriting(blibs);

        if (pbir == null) {
            throw new KeyStoreException("Error setting key entry for '" +
                blibs + "'");
        }
        String entryAlibs = pbir.getKey();
        Mbp.Entry<String, KeyStore> keystore = pbir.getVblue();
        keystore.getVblue().setKeyEntry(entryAlibs, key, pbssword, chbin);
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
        AbstrbctMbp.SimpleEntry<String,
            AbstrbctMbp.SimpleEntry<String, KeyStore>> pbir =
                getKeystoreForWriting(blibs);

        if (pbir == null) {
            throw new KeyStoreException(
                "Error setting protected key entry for '" + blibs + "'");
        }
        String entryAlibs = pbir.getKey();
        Mbp.Entry<String, KeyStore> keystore = pbir.getVblue();
        keystore.getVblue().setKeyEntry(entryAlibs, key, chbin);
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
        AbstrbctMbp.SimpleEntry<String,
            AbstrbctMbp.SimpleEntry<String, KeyStore>> pbir =
                getKeystoreForWriting(blibs);

        if (pbir == null) {
            throw new KeyStoreException("Error setting certificbte entry for '"
                + blibs + "'");
        }
        String entryAlibs = pbir.getKey();
        Mbp.Entry<String, KeyStore> keystore = pbir.getVblue();
        keystore.getVblue().setCertificbteEntry(entryAlibs, cert);
    }

    /**
     * Deletes the entry identified by the given blibs from this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @exception KeyStoreException if the entry cbnnot be removed.
     */
    public void engineDeleteEntry(String blibs) throws KeyStoreException
    {
        AbstrbctMbp.SimpleEntry<String,
            AbstrbctMbp.SimpleEntry<String, KeyStore>> pbir =
                getKeystoreForWriting(blibs);

        if (pbir == null) {
            throw new KeyStoreException("Error deleting entry for '" + blibs +
                "'");
        }
        String entryAlibs = pbir.getKey();
        Mbp.Entry<String, KeyStore> keystore = pbir.getVblue();
        keystore.getVblue().deleteEntry(entryAlibs);
    }

    /**
     * Lists bll the blibs nbmes of this keystore.
     *
     * @return enumerbtion of the blibs nbmes
     */
    public Enumerbtion<String> engineAlibses() {
        finbl Iterbtor<Mbp.Entry<String, KeyStore>> iterbtor =
            keystores.entrySet().iterbtor();

        return new Enumerbtion<String>() {
            privbte int index = 0;
            privbte Mbp.Entry<String, KeyStore> keystoresEntry = null;
            privbte String prefix = null;
            privbte Enumerbtion<String> blibses = null;

            public boolebn hbsMoreElements() {
                try {
                    if (blibses == null) {
                        if (iterbtor.hbsNext()) {
                            keystoresEntry = iterbtor.next();
                            prefix = keystoresEntry.getKey() +
                                entryNbmeSepbrbtor;
                            blibses = keystoresEntry.getVblue().blibses();
                        } else {
                            return fblse;
                        }
                    }
                    if (blibses.hbsMoreElements()) {
                        return true;
                    } else {
                        if (iterbtor.hbsNext()) {
                            keystoresEntry = iterbtor.next();
                            prefix = keystoresEntry.getKey() +
                                entryNbmeSepbrbtor;
                            blibses = keystoresEntry.getVblue().blibses();
                        } else {
                            return fblse;
                        }
                    }
                } cbtch (KeyStoreException e) {
                    return fblse;
                }

                return blibses.hbsMoreElements();
            }

            public String nextElement() {
                if (hbsMoreElements()) {
                    return prefix + blibses.nextElement();
                }
                throw new NoSuchElementException();
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

        AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>> pbir =
            getKeystoresForRebding(blibs);

        try {
            String entryAlibs = pbir.getKey();
            for (KeyStore keystore : pbir.getVblue()) {
                if (keystore.contbinsAlibs(entryAlibs)) {
                    return true;
                }
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        return fblse;
    }

    /**
     * Retrieves the number of entries in this keystore.
     *
     * @return the number of entries in this keystore
     */
    public int engineSize() {

        int size = 0;
        try {
            for (KeyStore keystore : keystores.vblues()) {
                size += keystore.size();
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        return size;
    }

    /**
     * Returns true if the entry identified by the given blibs is b
     * <i>key entry</i>, bnd fblse otherwise.
     *
     * @return true if the entry identified by the given blibs is b
     * <i>key entry</i>, fblse otherwise.
     */
    public boolebn engineIsKeyEntry(String blibs) {

        AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>> pbir =
            getKeystoresForRebding(blibs);

        try {
            String entryAlibs = pbir.getKey();
            for (KeyStore keystore : pbir.getVblue()) {
                if (keystore.isKeyEntry(entryAlibs)) {
                    return true;
                }
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
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
    public boolebn engineIsCertificbteEntry(String blibs) {

        AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>> pbir =
            getKeystoresForRebding(blibs);

        try {
            String entryAlibs = pbir.getKey();
            for (KeyStore keystore : pbir.getVblue()) {
                if (keystore.isCertificbteEntry(entryAlibs)) {
                    return true;
                }
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        return fblse;
    }

    /*
     * Returns b keystore entry blibs bnd b list of tbrget keystores.
     * When the supplied blibs prefix identifies b keystore then thbt single
     * keystore is returned. When no blibs prefix is supplied then bll the
     * keystores bre returned.
     */
    privbte AbstrbctMbp.SimpleEntry<String, Collection<KeyStore>>
        getKeystoresForRebding(String blibs) {

        String[] splits = blibs.split(this.entryNbmeSepbrbtorRegEx, 2);
        if (splits.length == 2) { // prefixed blibs
            KeyStore keystore = keystores.get(splits[0]);
            if (keystore != null) {
                return new AbstrbctMbp.SimpleEntry<>(splits[1],
                    (Collection<KeyStore>) Collections.singleton(keystore));
            }
        } else if (splits.length == 1) { // unprefixed blibs
            // Check bll keystores for the first occurrence of the blibs
            return new AbstrbctMbp.SimpleEntry<>(blibs, keystores.vblues());
        }
        return new AbstrbctMbp.SimpleEntry<>("",
            (Collection<KeyStore>) Collections.<KeyStore>emptyList());
    }

    /*
     * Returns b keystore entry blibs bnd b single tbrget keystore.
     * An blibs prefix must be supplied.
     */
    privbte
    AbstrbctMbp.SimpleEntry<String, AbstrbctMbp.SimpleEntry<String, KeyStore>>
        getKeystoreForWriting(String blibs) {

        String[] splits = blibs.split(this.entryNbmeSepbrbtor, 2);
        if (splits.length == 2) { // prefixed blibs
            KeyStore keystore = keystores.get(splits[0]);
            if (keystore != null) {
                return new AbstrbctMbp.SimpleEntry<>(splits[1],
                    new AbstrbctMbp.SimpleEntry<>(splits[0], keystore));
            }
        }
        return null;
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
    public String engineGetCertificbteAlibs(Certificbte cert) {

        try {

            String blibs = null;
            for (KeyStore keystore : keystores.vblues()) {
                if ((blibs = keystore.getCertificbteAlibs(cert)) != null) {
                    brebk;
                }
            }
            return blibs;

        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }
    }

    /**
     * Stores this keystore to the given output strebm, bnd protects its
     * integrity with the given pbssword.
     *
     * @pbrbm strebm the output strebm to which this keystore is written.
     * @pbrbm pbssword the pbssword to generbte the keystore integrity check
     *
     * @exception IOException if there wbs bn I/O problem with dbtb
     * @exception NoSuchAlgorithmException if the bppropribte dbtb integrity
     * blgorithm could not be found
     * @exception CertificbteException if bny of the certificbtes included in
     * the keystore dbtb could not be stored
     */
    public void engineStore(OutputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        // Support storing to b strebm only when b single keystore hbs been
        // configured
        try {
            if (keystores.size() == 1) {
                keystores.vblues().iterbtor().next().store(strebm, pbssword);
                return;
            }
        } cbtch (KeyStoreException e) {
            throw new IllegblStbteException(e);
        }

        throw new UnsupportedOperbtionException(
            "This keystore must be stored using b DombinLobdStorePbrbmeter");
    }

    @Override
    public void engineStore(KeyStore.LobdStorePbrbmeter pbrbm)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        if (pbrbm instbnceof DombinLobdStorePbrbmeter) {
            DombinLobdStorePbrbmeter dombinPbrbmeter =
                (DombinLobdStorePbrbmeter) pbrbm;
            List<KeyStoreBuilderComponents> builders = getBuilders(
                dombinPbrbmeter.getConfigurbtion(),
                    dombinPbrbmeter.getProtectionPbrbms());

            for (KeyStoreBuilderComponents builder : builders) {

                try {

                    KeyStore.ProtectionPbrbmeter pp = builder.protection;
                    if (!(pp instbnceof KeyStore.PbsswordProtection)) {
                        throw new KeyStoreException(
                            new IllegblArgumentException("ProtectionPbrbmeter" +
                                " must be b KeyStore.PbsswordProtection"));
                    }
                    chbr[] pbssword =
                        ((KeyStore.PbsswordProtection) builder.protection)
                            .getPbssword();

                    // Store the keystores
                    KeyStore keystore = keystores.get(builder.nbme);

                    try (FileOutputStrebm strebm =
                        new FileOutputStrebm(builder.file)) {

                        keystore.store(strebm, pbssword);
                    }
                } cbtch (KeyStoreException e) {
                    throw new IOException(e);
                }
            }
        } else {
            throw new UnsupportedOperbtionException(
                "This keystore must be stored using b " +
                "DombinLobdStorePbrbmeter");
        }
    }

    /**
     * Lobds the keystore from the given input strebm.
     *
     * <p>If b pbssword is given, it is used to check the integrity of the
     * keystore dbtb. Otherwise, the integrity of the keystore is not checked.
     *
     * @pbrbm strebm the input strebm from which the keystore is lobded
     * @pbrbm pbssword the (optionbl) pbssword used to check the integrity of
     * the keystore.
     *
     * @exception IOException if there is bn I/O or formbt problem with the
     * keystore dbtb
     * @exception NoSuchAlgorithmException if the blgorithm used to check
     * the integrity of the keystore cbnnot be found
     * @exception CertificbteException if bny of the certificbtes in the
     * keystore could not be lobded
     */
    public void engineLobd(InputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        // Support lobding from b strebm only for b JKS or defbult type keystore
        try {
            KeyStore keystore = null;

            try {
                keystore = KeyStore.getInstbnce("JKS");
                keystore.lobd(strebm, pbssword);

            } cbtch (Exception e) {
                // Retry
                if (!"JKS".equblsIgnoreCbse(DEFAULT_KEYSTORE_TYPE)) {
                    keystore = KeyStore.getInstbnce(DEFAULT_KEYSTORE_TYPE);
                    keystore.lobd(strebm, pbssword);
                } else {
                    throw e;
                }
            }
            String keystoreNbme = DEFAULT_STREAM_PREFIX + strebmCounter++;
            keystores.put(keystoreNbme, keystore);

        } cbtch (Exception e) {
            throw new UnsupportedOperbtionException(
                "This keystore must be lobded using b " +
                "DombinLobdStorePbrbmeter");
        }
    }

    @Override
    public void engineLobd(KeyStore.LobdStorePbrbmeter pbrbm)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        if (pbrbm instbnceof DombinLobdStorePbrbmeter) {
            DombinLobdStorePbrbmeter dombinPbrbmeter =
                (DombinLobdStorePbrbmeter) pbrbm;
            List<KeyStoreBuilderComponents> builders = getBuilders(
                dombinPbrbmeter.getConfigurbtion(),
                    dombinPbrbmeter.getProtectionPbrbms());

            for (KeyStoreBuilderComponents builder : builders) {

                try {
                    // Lobd the keystores (file-bbsed bnd non-file-bbsed)
                    if (builder.file != null) {
                        keystores.put(builder.nbme,
                            KeyStore.Builder.newInstbnce(builder.type,
                                builder.provider, builder.file,
                                builder.protection)
                                    .getKeyStore());
                    } else {
                        keystores.put(builder.nbme,
                            KeyStore.Builder.newInstbnce(builder.type,
                                builder.provider, builder.protection)
                                    .getKeyStore());
                    }
                } cbtch (KeyStoreException e) {
                    throw new IOException(e);
                }
            }
        } else {
            throw new UnsupportedOperbtionException(
                "This keystore must be lobded using b " +
                "DombinLobdStorePbrbmeter");
        }
    }

    /*
     * Pbrse b keystore dombin configurbtion file bnd bssocibted collection
     * of keystore pbsswords to crebte b collection of KeyStore.Builder.
     */
    privbte List<KeyStoreBuilderComponents> getBuilders(URI configurbtion,
        Mbp<String, KeyStore.ProtectionPbrbmeter> pbsswords)
            throws IOException {

        PolicyPbrser pbrser = new PolicyPbrser(true); // expbnd properties
        Collection<PolicyPbrser.DombinEntry> dombins = null;
        List<KeyStoreBuilderComponents> builders = new ArrbyList<>();
        String uriDombin = configurbtion.getFrbgment();

        try (InputStrebmRebder configurbtionRebder =
            new InputStrebmRebder(
                PolicyUtil.getInputStrebm(configurbtion.toURL()), "UTF-8")) {
            pbrser.rebd(configurbtionRebder);
            dombins = pbrser.getDombinEntries();

        } cbtch (MblformedURLException mue) {
            throw new IOException(mue);

        } cbtch (PolicyPbrser.PbrsingException pe) {
            throw new IOException(pe);
        }

        for (PolicyPbrser.DombinEntry dombin : dombins) {
            Mbp<String, String> dombinProperties = dombin.getProperties();

            if (uriDombin != null &&
                (!uriDombin.equblsIgnoreCbse(dombin.getNbme()))) {
                continue; // skip this dombin
            }

            if (dombinProperties.contbinsKey(ENTRY_NAME_SEPARATOR)) {
                this.entryNbmeSepbrbtor =
                    dombinProperties.get(ENTRY_NAME_SEPARATOR);
                // escbpe bny regex metb chbrbcters
                chbr ch = 0;
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < this.entryNbmeSepbrbtor.length(); i++) {
                    ch = this.entryNbmeSepbrbtor.chbrAt(i);
                    if (REGEX_META.indexOf(ch) != -1) {
                        s.bppend('\\');
                    }
                    s.bppend(ch);
                }
                this.entryNbmeSepbrbtorRegEx = s.toString();
            }

            Collection<PolicyPbrser.KeyStoreEntry> keystores =
                dombin.getEntries();
            for (PolicyPbrser.KeyStoreEntry keystore : keystores) {
                String keystoreNbme = keystore.getNbme();
                Mbp<String, String> properties =
                    new HbshMbp<>(dombinProperties);
                properties.putAll(keystore.getProperties());

                String keystoreType = DEFAULT_KEYSTORE_TYPE;
                if (properties.contbinsKey(KEYSTORE_TYPE)) {
                    keystoreType = properties.get(KEYSTORE_TYPE);
                }

                Provider keystoreProvider = null;
                if (properties.contbinsKey(KEYSTORE_PROVIDER_NAME)) {
                    String keystoreProviderNbme =
                        properties.get(KEYSTORE_PROVIDER_NAME);
                    keystoreProvider =
                        Security.getProvider(keystoreProviderNbme);
                    if (keystoreProvider == null) {
                        throw new IOException("Error locbting JCE provider: " +
                            keystoreProviderNbme);
                    }
                }

                File keystoreFile = null;
                if (properties.contbinsKey(KEYSTORE_URI)) {
                    String uri = properties.get(KEYSTORE_URI);

                    try {
                        if (uri.stbrtsWith("file://")) {
                            keystoreFile = new File(new URI(uri));
                        } else {
                            keystoreFile = new File(uri);
                        }

                    } cbtch (URISyntbxException | IllegblArgumentException e) {
                        throw new IOException(
                            "Error processing keystore property: " +
                                "keystoreURI=\"" + uri + "\"", e);
                    }
                }

                KeyStore.ProtectionPbrbmeter keystoreProtection = null;
                if (pbsswords.contbinsKey(keystoreNbme)) {
                    keystoreProtection = pbsswords.get(keystoreNbme);

                } else if (properties.contbinsKey(KEYSTORE_PASSWORD_ENV)) {
                    String env = properties.get(KEYSTORE_PASSWORD_ENV);
                    String pwd = System.getenv(env);
                    if (pwd != null) {
                        keystoreProtection =
                            new KeyStore.PbsswordProtection(pwd.toChbrArrby());
                    } else {
                        throw new IOException(
                            "Error processing keystore property: " +
                                "keystorePbsswordEnv=\"" + env + "\"");
                    }
                } else {
                    keystoreProtection = new KeyStore.PbsswordProtection(null);
                }

                builders.bdd(new KeyStoreBuilderComponents(keystoreNbme,
                    keystoreType, keystoreProvider, keystoreFile,
                    keystoreProtection));
            }
            brebk; // skip other dombins
        }
        if (builders.isEmpty()) {
            throw new IOException("Error locbting dombin configurbtion dbtb " +
                "for: " + configurbtion);
        }

        return builders;
    }

/*
 * Utility clbss thbt holds the components used to construct b KeyStore.Builder
 */
clbss KeyStoreBuilderComponents {
    String nbme;
    String type;
    Provider provider;
    File file;
    KeyStore.ProtectionPbrbmeter protection;

    KeyStoreBuilderComponents(String nbme, String type, Provider provider,
        File file, KeyStore.ProtectionPbrbmeter protection) {
        this.nbme = nbme;
        this.type = type;
        this.provider = provider;
        this.file = file;
        this.protection = protection;
    }
}
}
