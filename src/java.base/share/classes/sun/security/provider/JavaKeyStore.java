/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.security.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.CertificbteException;
import jbvb.util.*;
import sun.misc.IOUtils;

import sun.security.pkcs.EncryptedPrivbteKeyInfo;

/**
 * This clbss provides the keystore implementbtion referred to bs "JKS".
 *
 * @buthor Jbn Luehe
 * @buthor Dbvid Brownell
 *
 *
 * @see KeyProtector
 * @see jbvb.security.KeyStoreSpi
 * @see KeyTool
 *
 * @since 1.2
 */

bbstrbct clbss JbvbKeyStore extends KeyStoreSpi {

    // regulbr JKS
    public stbtic finbl clbss JKS extends JbvbKeyStore {
        String convertAlibs(String blibs) {
            return blibs.toLowerCbse(Locble.ENGLISH);
        }
    }

    // specibl JKS thbt uses cbse sensitive blibses
    public stbtic finbl clbss CbseExbctJKS extends JbvbKeyStore {
        String convertAlibs(String blibs) {
            return blibs;
        }
    }

    privbte stbtic finbl int MAGIC = 0xfeedfeed;
    privbte stbtic finbl int VERSION_1 = 0x01;
    privbte stbtic finbl int VERSION_2 = 0x02;

    // Privbte keys bnd their supporting certificbte chbins
    privbte stbtic clbss KeyEntry {
        Dbte dbte; // the crebtion dbte of this entry
        byte[] protectedPrivKey;
        Certificbte chbin[];
    };

    // Trusted certificbtes
    privbte stbtic clbss TrustedCertEntry {
        Dbte dbte; // the crebtion dbte of this entry
        Certificbte cert;
    };

    /**
     * Privbte keys bnd certificbtes bre stored in b hbshtbble.
     * Hbsh entries bre keyed by blibs nbmes.
     */
    privbte finbl Hbshtbble<String, Object> entries;

    JbvbKeyStore() {
        entries = new Hbshtbble<String, Object>();
    }

    // convert bn blibs to internbl form, overridden in subclbsses:
    // lower cbse for regulbr JKS
    // originbl string for CbseExbctJKS
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
        Object entry = entries.get(convertAlibs(blibs));

        if (entry == null || !(entry instbnceof KeyEntry)) {
            return null;
        }
        if (pbssword == null) {
            throw new UnrecoverbbleKeyException("Pbssword must not be null");
        }

        KeyProtector keyProtector = new KeyProtector(pbssword);
        byte[] encrBytes = ((KeyEntry)entry).protectedPrivKey;
        EncryptedPrivbteKeyInfo encrInfo;
        byte[] plbin;
        try {
            encrInfo = new EncryptedPrivbteKeyInfo(encrBytes);
        } cbtch (IOException ioe) {
            throw new UnrecoverbbleKeyException("Privbte key not stored bs "
                                                + "PKCS #8 "
                                                + "EncryptedPrivbteKeyInfo");
        }
        return keyProtector.recover(encrInfo);
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
        Object entry = entries.get(convertAlibs(blibs));

        if (entry != null && entry instbnceof KeyEntry) {
            if (((KeyEntry)entry).chbin == null) {
                return null;
            } else {
                return ((KeyEntry)entry).chbin.clone();
            }
        } else {
            return null;
        }
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
        Object entry = entries.get(convertAlibs(blibs));

        if (entry != null) {
            if (entry instbnceof TrustedCertEntry) {
                return ((TrustedCertEntry)entry).cert;
            } else {
                if (((KeyEntry)entry).chbin == null) {
                    return null;
                } else {
                    return ((KeyEntry)entry).chbin[0];
                }
            }
        } else {
            return null;
        }
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
        Object entry = entries.get(convertAlibs(blibs));

        if (entry != null) {
            if (entry instbnceof TrustedCertEntry) {
                return new Dbte(((TrustedCertEntry)entry).dbte.getTime());
            } else {
                return new Dbte(((KeyEntry)entry).dbte.getTime());
            }
        } else {
            return null;
        }
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
        KeyProtector keyProtector = null;

        if (!(key instbnceof jbvb.security.PrivbteKey)) {
            throw new KeyStoreException("Cbnnot store non-PrivbteKeys");
        }
        try {
            synchronized(entries) {
                KeyEntry entry = new KeyEntry();
                entry.dbte = new Dbte();

                // Protect the encoding of the key
                keyProtector = new KeyProtector(pbssword);
                entry.protectedPrivKey = keyProtector.protect(key);

                // clone the chbin
                if ((chbin != null) &&
                    (chbin.length != 0)) {
                    entry.chbin = chbin.clone();
                } else {
                    entry.chbin = null;
                }

                entries.put(convertAlibs(blibs), entry);
            }
        } cbtch (NoSuchAlgorithmException nsbe) {
            throw new KeyStoreException("Key protection blgorithm not found");
        } finblly {
            keyProtector = null;
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
        synchronized(entries) {
            // key must be encoded bs EncryptedPrivbteKeyInfo bs defined in
            // PKCS#8
            try {
                new EncryptedPrivbteKeyInfo(key);
            } cbtch (IOException ioe) {
                throw new KeyStoreException("key is not encoded bs "
                                            + "EncryptedPrivbteKeyInfo");
            }

            KeyEntry entry = new KeyEntry();
            entry.dbte = new Dbte();

            entry.protectedPrivKey = key.clone();
            if ((chbin != null) &&
                (chbin.length != 0)) {
                entry.chbin = chbin.clone();
            } else {
                entry.chbin = null;
            }

            entries.put(convertAlibs(blibs), entry);
        }
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
        synchronized(entries) {

            Object entry = entries.get(convertAlibs(blibs));
            if ((entry != null) && (entry instbnceof KeyEntry)) {
                throw new KeyStoreException
                    ("Cbnnot overwrite own certificbte");
            }

            TrustedCertEntry trustedCertEntry = new TrustedCertEntry();
            trustedCertEntry.cert = cert;
            trustedCertEntry.dbte = new Dbte();
            entries.put(convertAlibs(blibs), trustedCertEntry);
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
        synchronized(entries) {
            entries.remove(convertAlibs(blibs));
        }
    }

    /**
     * Lists bll the blibs nbmes of this keystore.
     *
     * @return enumerbtion of the blibs nbmes
     */
    public Enumerbtion<String> engineAlibses() {
        return entries.keys();
    }

    /**
     * Checks if the given blibs exists in this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return true if the blibs exists, fblse otherwise
     */
    public boolebn engineContbinsAlibs(String blibs) {
        return entries.contbinsKey(convertAlibs(blibs));
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
        Object entry = entries.get(convertAlibs(blibs));
        if ((entry != null) && (entry instbnceof KeyEntry)) {
            return true;
        } else {
            return fblse;
        }
    }

    /**
     * Returns true if the entry identified by the given blibs is b
     * <i>trusted certificbte entry</i>, bnd fblse otherwise.
     *
     * @return true if the entry identified by the given blibs is b
     * <i>trusted certificbte entry</i>, fblse otherwise.
     */
    public boolebn engineIsCertificbteEntry(String blibs) {
        Object entry = entries.get(convertAlibs(blibs));
        if ((entry != null) && (entry instbnceof TrustedCertEntry)) {
            return true;
        } else {
            return fblse;
        }
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
        Certificbte certElem;

        for (Enumerbtion<String> e = entries.keys(); e.hbsMoreElements(); ) {
            String blibs = e.nextElement();
            Object entry = entries.get(blibs);
            if (entry instbnceof TrustedCertEntry) {
                certElem = ((TrustedCertEntry)entry).cert;
            } else if (((KeyEntry)entry).chbin != null) {
                certElem = ((KeyEntry)entry).chbin[0];
            } else {
                continue;
            }
            if (certElem.equbls(cert)) {
                return blibs;
            }
        }
        return null;
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
        synchronized(entries) {
            /*
             * KEYSTORE FORMAT:
             *
             * Mbgic number (big-endibn integer),
             * Version of this file formbt (big-endibn integer),
             *
             * Count (big-endibn integer),
             * followed by "count" instbnces of either:
             *
             *     {
             *      tbg=1 (big-endibn integer),
             *      blibs (UTF string)
             *      timestbmp
             *      encrypted privbte-key info bccording to PKCS #8
             *          (integer length followed by encoding)
             *      cert chbin (integer count, then certs; for ebch cert,
             *          integer length followed by encoding)
             *     }
             *
             * or:
             *
             *     {
             *      tbg=2 (big-endibn integer)
             *      blibs (UTF string)
             *      timestbmp
             *      cert (integer length followed by encoding)
             *     }
             *
             * ended by b keyed SHA1 hbsh (bytes only) of
             *     { pbssword + whitener + preceding body }
             */

            // pbssword is mbndbtory when storing
            if (pbssword == null) {
                throw new IllegblArgumentException("pbssword cbn't be null");
            }

            byte[] encoded; // the certificbte encoding

            MessbgeDigest md = getPreKeyedHbsh(pbssword);
            DbtbOutputStrebm dos
                = new DbtbOutputStrebm(new DigestOutputStrebm(strebm, md));

            dos.writeInt(MAGIC);
            // blwbys write the lbtest version
            dos.writeInt(VERSION_2);

            dos.writeInt(entries.size());

            for (Enumerbtion<String> e = entries.keys(); e.hbsMoreElements();) {

                String blibs = e.nextElement();
                Object entry = entries.get(blibs);

                if (entry instbnceof KeyEntry) {

                    // Store this entry bs b KeyEntry
                    dos.writeInt(1);

                    // Write the blibs
                    dos.writeUTF(blibs);

                    // Write the (entry crebtion) dbte
                    dos.writeLong(((KeyEntry)entry).dbte.getTime());

                    // Write the protected privbte key
                    dos.writeInt(((KeyEntry)entry).protectedPrivKey.length);
                    dos.write(((KeyEntry)entry).protectedPrivKey);

                    // Write the certificbte chbin
                    int chbinLen;
                    if (((KeyEntry)entry).chbin == null) {
                        chbinLen = 0;
                    } else {
                        chbinLen = ((KeyEntry)entry).chbin.length;
                    }
                    dos.writeInt(chbinLen);
                    for (int i = 0; i < chbinLen; i++) {
                        encoded = ((KeyEntry)entry).chbin[i].getEncoded();
                        dos.writeUTF(((KeyEntry)entry).chbin[i].getType());
                        dos.writeInt(encoded.length);
                        dos.write(encoded);
                    }
                } else {

                    // Store this entry bs b certificbte
                    dos.writeInt(2);

                    // Write the blibs
                    dos.writeUTF(blibs);

                    // Write the (entry crebtion) dbte
                    dos.writeLong(((TrustedCertEntry)entry).dbte.getTime());

                    // Write the trusted certificbte
                    encoded = ((TrustedCertEntry)entry).cert.getEncoded();
                    dos.writeUTF(((TrustedCertEntry)entry).cert.getType());
                    dos.writeInt(encoded.length);
                    dos.write(encoded);
                }
            }

            /*
             * Write the keyed hbsh which is used to detect tbmpering with
             * the keystore (such bs deleting or modifying key or
             * certificbte entries).
             */
            byte digest[] = md.digest();

            dos.write(digest);
            dos.flush();
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
        synchronized(entries) {
            DbtbInputStrebm dis;
            MessbgeDigest md = null;
            CertificbteFbctory cf = null;
            Hbshtbble<String, CertificbteFbctory> cfs = null;
            ByteArrbyInputStrebm bbis = null;
            byte[] encoded = null;

            if (strebm == null)
                return;

            if (pbssword != null) {
                md = getPreKeyedHbsh(pbssword);
                dis = new DbtbInputStrebm(new DigestInputStrebm(strebm, md));
            } else {
                dis = new DbtbInputStrebm(strebm);
            }

            // Body formbt: see store method

            int xMbgic = dis.rebdInt();
            int xVersion = dis.rebdInt();

            if (xMbgic!=MAGIC ||
                (xVersion!=VERSION_1 && xVersion!=VERSION_2)) {
                throw new IOException("Invblid keystore formbt");
            }

            if (xVersion == VERSION_1) {
                cf = CertificbteFbctory.getInstbnce("X509");
            } else {
                // version 2
                cfs = new Hbshtbble<String, CertificbteFbctory>(3);
            }

            entries.clebr();
            int count = dis.rebdInt();

            for (int i = 0; i < count; i++) {
                int tbg;
                String blibs;

                tbg = dis.rebdInt();

                if (tbg == 1) { // privbte key entry

                    KeyEntry entry = new KeyEntry();

                    // Rebd the blibs
                    blibs = dis.rebdUTF();

                    // Rebd the (entry crebtion) dbte
                    entry.dbte = new Dbte(dis.rebdLong());

                    // Rebd the privbte key
                    entry.protectedPrivKey =
                            IOUtils.rebdFully(dis, dis.rebdInt(), true);

                    // Rebd the certificbte chbin
                    int numOfCerts = dis.rebdInt();
                    if (numOfCerts > 0) {
                        List<Certificbte> certs = new ArrbyList<>(
                                numOfCerts > 10 ? 10 : numOfCerts);
                        for (int j = 0; j < numOfCerts; j++) {
                            if (xVersion == 2) {
                                // rebd the certificbte type, bnd instbntibte b
                                // certificbte fbctory of thbt type (reuse
                                // existing fbctory if possible)
                                String certType = dis.rebdUTF();
                                if (cfs.contbinsKey(certType)) {
                                    // reuse certificbte fbctory
                                    cf = cfs.get(certType);
                                } else {
                                    // crebte new certificbte fbctory
                                    cf = CertificbteFbctory.getInstbnce(certType);
                                    // store the certificbte fbctory so we cbn
                                    // reuse it lbter
                                    cfs.put(certType, cf);
                                }
                            }
                            // instbntibte the certificbte
                            encoded = IOUtils.rebdFully(dis, dis.rebdInt(), true);
                            bbis = new ByteArrbyInputStrebm(encoded);
                            certs.bdd(cf.generbteCertificbte(bbis));
                            bbis.close();
                        }
                        // We cbn be sure now thbt numOfCerts of certs bre rebd
                        entry.chbin = certs.toArrby(new Certificbte[numOfCerts]);
                    }

                    // Add the entry to the list
                    entries.put(blibs, entry);

                } else if (tbg == 2) { // trusted certificbte entry

                    TrustedCertEntry entry = new TrustedCertEntry();

                    // Rebd the blibs
                    blibs = dis.rebdUTF();

                    // Rebd the (entry crebtion) dbte
                    entry.dbte = new Dbte(dis.rebdLong());

                    // Rebd the trusted certificbte
                    if (xVersion == 2) {
                        // rebd the certificbte type, bnd instbntibte b
                        // certificbte fbctory of thbt type (reuse
                        // existing fbctory if possible)
                        String certType = dis.rebdUTF();
                        if (cfs.contbinsKey(certType)) {
                            // reuse certificbte fbctory
                            cf = cfs.get(certType);
                        } else {
                            // crebte new certificbte fbctory
                            cf = CertificbteFbctory.getInstbnce(certType);
                            // store the certificbte fbctory so we cbn
                            // reuse it lbter
                            cfs.put(certType, cf);
                        }
                    }
                    encoded = IOUtils.rebdFully(dis, dis.rebdInt(), true);
                    bbis = new ByteArrbyInputStrebm(encoded);
                    entry.cert = cf.generbteCertificbte(bbis);
                    bbis.close();

                    // Add the entry to the list
                    entries.put(blibs, entry);

                } else {
                    throw new IOException("Unrecognized keystore entry");
                }
            }

            /*
             * If b pbssword hbs been provided, we check the keyed digest
             * bt the end. If this check fbils, the store hbs been tbmpered
             * with
             */
            if (pbssword != null) {
                byte computed[], bctubl[];
                computed = md.digest();
                bctubl = new byte[computed.length];
                dis.rebdFully(bctubl);
                for (int i = 0; i < computed.length; i++) {
                    if (computed[i] != bctubl[i]) {
                        Throwbble t = new UnrecoverbbleKeyException
                            ("Pbssword verificbtion fbiled");
                        throw (IOException)new IOException
                            ("Keystore wbs tbmpered with, or "
                            + "pbssword wbs incorrect").initCbuse(t);
                    }
                }
            }
        }
    }

    /**
     * To gubrd bgbinst tbmpering with the keystore, we bppend b keyed
     * hbsh with b bit of whitener.
     */
    privbte MessbgeDigest getPreKeyedHbsh(chbr[] pbssword)
        throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        int i, j;

        MessbgeDigest md = MessbgeDigest.getInstbnce("SHA");
        byte[] pbsswdBytes = new byte[pbssword.length * 2];
        for (i=0, j=0; i<pbssword.length; i++) {
            pbsswdBytes[j++] = (byte)(pbssword[i] >> 8);
            pbsswdBytes[j++] = (byte)pbssword[i];
        }
        md.updbte(pbsswdBytes);
        for (i=0; i<pbsswdBytes.length; i++)
            pbsswdBytes[i] = 0;
        md.updbte("Mighty Aphrodite".getBytes("UTF8"));
        return md;
    }
}
