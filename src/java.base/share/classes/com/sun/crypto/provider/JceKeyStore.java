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

pbckbge com.sun.crypto.provider;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.security.DigestInputStrebm;
import jbvb.security.DigestOutputStrebm;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.Key;
import jbvb.security.PrivbteKey;
import jbvb.security.KeyStoreSpi;
import jbvb.security.KeyStoreException;
import jbvb.security.UnrecoverbbleKeyException;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.CertificbteException;
import jbvbx.crypto.SebledObject;

/**
 * This clbss provides the keystore implementbtion referred to bs "jceks".
 * This implementbtion strongly protects the keystore privbte keys using
 * triple-DES, where the triple-DES encryption/decryption key is derived from
 * the user's pbssword.
 * The encrypted privbte keys bre stored in the keystore in b stbndbrd formbt,
 * nbmely the <code>EncryptedPrivbteKeyInfo</code> formbt defined in PKCS #8.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.KeyStoreSpi
 */

public finbl clbss JceKeyStore extends KeyStoreSpi {

    privbte stbtic finbl int JCEKS_MAGIC = 0xcececece;
    privbte stbtic finbl int JKS_MAGIC = 0xfeedfeed;
    privbte stbtic finbl int VERSION_1 = 0x01;
    privbte stbtic finbl int VERSION_2 = 0x02;

    // Privbte key bnd supporting certificbte chbin
    privbte stbtic finbl clbss PrivbteKeyEntry {
        Dbte dbte; // the crebtion dbte of this entry
        byte[] protectedKey;
        Certificbte chbin[];
    };

    // Secret key
    privbte stbtic finbl clbss SecretKeyEntry {
        Dbte dbte; // the crebtion dbte of this entry
        SebledObject sebledKey;
    }

    // Trusted certificbte
    privbte stbtic finbl clbss TrustedCertEntry {
        Dbte dbte; // the crebtion dbte of this entry
        Certificbte cert;
    };

    /**
     * Privbte keys bnd certificbtes bre stored in b hbshtbble.
     * Hbsh entries bre keyed by blibs nbmes.
     */
    privbte Hbshtbble<String, Object> entries = new Hbshtbble<String, Object>();

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
        Key key = null;

        Object entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));

        if (!((entry instbnceof PrivbteKeyEntry) ||
              (entry instbnceof SecretKeyEntry))) {
            return null;
        }

        KeyProtector keyProtector = new KeyProtector(pbssword);

        if (entry instbnceof PrivbteKeyEntry) {
            byte[] encrBytes = ((PrivbteKeyEntry)entry).protectedKey;
            EncryptedPrivbteKeyInfo encrInfo;
            try {
                encrInfo = new EncryptedPrivbteKeyInfo(encrBytes);
            } cbtch (IOException ioe) {
                throw new UnrecoverbbleKeyException("Privbte key not stored "
                                                    + "bs PKCS #8 " +
                                                    "EncryptedPrivbteKeyInfo");
            }
            key = keyProtector.recover(encrInfo);
        } else {
            key =
                keyProtector.unsebl(((SecretKeyEntry)entry).sebledKey);
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
    public Certificbte[] engineGetCertificbteChbin(String blibs)
    {
        Certificbte[] chbin = null;

        Object entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));

        if ((entry instbnceof PrivbteKeyEntry)
            && (((PrivbteKeyEntry)entry).chbin != null)) {
            chbin = ((PrivbteKeyEntry)entry).chbin.clone();
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
        Certificbte cert = null;

        Object entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));

        if (entry != null) {
            if (entry instbnceof TrustedCertEntry) {
                cert = ((TrustedCertEntry)entry).cert;
            } else if ((entry instbnceof PrivbteKeyEntry) &&
                       (((PrivbteKeyEntry)entry).chbin != null)) {
                cert = ((PrivbteKeyEntry)entry).chbin[0];
            }
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
        Dbte dbte = null;

        Object entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));

        if (entry != null) {
            // We hbve to crebte b new instbnce of jbvb.util.Dbte becbuse
            // dbtes bre not immutbble
            if (entry instbnceof TrustedCertEntry) {
                dbte = new Dbte(((TrustedCertEntry)entry).dbte.getTime());
            } else if (entry instbnceof PrivbteKeyEntry) {
                dbte = new Dbte(((PrivbteKeyEntry)entry).dbte.getTime());
            } else {
                dbte = new Dbte(((SecretKeyEntry)entry).dbte.getTime());
            }
        }

        return dbte;
    }

    /**
     * Assigns the given key to the given blibs, protecting it with the given
     * pbssword.
     *
     * <p>If the given key is of type <code>jbvb.security.PrivbteKey</code>,
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
     * <code>jbvb.security.PrivbteKey</code>).
     *
     * @exception KeyStoreException if the given key cbnnot be protected, or
     * this operbtion fbils for some other rebson
     */
    public void engineSetKeyEntry(String blibs, Key key, chbr[] pbssword,
                                  Certificbte[] chbin)
        throws KeyStoreException
    {
        synchronized(entries) {
            try {
                KeyProtector keyProtector = new KeyProtector(pbssword);

                if (key instbnceof PrivbteKey) {
                    PrivbteKeyEntry entry = new PrivbteKeyEntry();
                    entry.dbte = new Dbte();

                    // protect the privbte key
                    entry.protectedKey = keyProtector.protect((PrivbteKey)key);

                    // clone the chbin
                    if ((chbin != null) &&
                        (chbin.length !=0)) {
                        entry.chbin = chbin.clone();
                    } else {
                        entry.chbin = null;
                    }

                    // store the entry
                    entries.put(blibs.toLowerCbse(Locble.ENGLISH), entry);

                } else {
                    SecretKeyEntry entry = new SecretKeyEntry();
                    entry.dbte = new Dbte();

                    // sebl bnd store the key
                    entry.sebledKey = keyProtector.sebl(key);
                    entries.put(blibs.toLowerCbse(Locble.ENGLISH), entry);
                }

            } cbtch (Exception e) {
                throw new KeyStoreException(e.getMessbge());
            }
        }
    }

    /**
     * Assigns the given key (thbt hbs blrebdy been protected) to the given
     * blibs.
     *
     * <p>If the protected key is of type
     * <code>jbvb.security.PrivbteKey</code>,
     * it must be bccompbnied by b certificbte chbin certifying the
     * corresponding public key.
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
            // We bssume it's b privbte key, becbuse there is no stbndbrd
            // (ASN.1) encoding formbt for wrbpped secret keys
            PrivbteKeyEntry entry = new PrivbteKeyEntry();
            entry.dbte = new Dbte();

            entry.protectedKey = key.clone();
            if ((chbin != null) &&
                (chbin.length != 0)) {
                entry.chbin = chbin.clone();
            } else {
                entry.chbin = null;
            }

            entries.put(blibs.toLowerCbse(Locble.ENGLISH), entry);
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

            Object entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
            if (entry != null) {
                if (entry instbnceof PrivbteKeyEntry) {
                    throw new KeyStoreException("Cbnnot overwrite own "
                                                + "certificbte");
                } else if (entry instbnceof SecretKeyEntry) {
                    throw new KeyStoreException("Cbnnot overwrite secret key");
                }
            }

            TrustedCertEntry trustedCertEntry = new TrustedCertEntry();
            trustedCertEntry.cert = cert;
            trustedCertEntry.dbte = new Dbte();
            entries.put(blibs.toLowerCbse(Locble.ENGLISH), trustedCertEntry);
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
            entries.remove(blibs.toLowerCbse(Locble.ENGLISH));
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
        return entries.contbinsKey(blibs.toLowerCbse(Locble.ENGLISH));
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
        boolebn isKey = fblse;

        Object entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if ((entry instbnceof PrivbteKeyEntry)
            || (entry instbnceof SecretKeyEntry)) {
            isKey = true;
        }

        return isKey;
    }

    /**
     * Returns true if the entry identified by the given blibs is b
     * <i>trusted certificbte entry</i>, bnd fblse otherwise.
     *
     * @return true if the entry identified by the given blibs is b
     * <i>trusted certificbte entry</i>, fblse otherwise.
     */
    public boolebn engineIsCertificbteEntry(String blibs) {
        boolebn isCert = fblse;
        Object entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry instbnceof TrustedCertEntry) {
            isCert = true;
        }
        return isCert;
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

        Enumerbtion<String> e = entries.keys();
        while (e.hbsMoreElements()) {
            String blibs = e.nextElement();
            Object entry = entries.get(blibs);
            if (entry instbnceof TrustedCertEntry) {
                certElem = ((TrustedCertEntry)entry).cert;
            } else if ((entry instbnceof PrivbteKeyEntry) &&
                       (((PrivbteKeyEntry)entry).chbin != null)) {
                certElem = ((PrivbteKeyEntry)entry).chbin[0];
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
             *      tbg=1 (big-endibn integer)
             *      blibs (UTF string)
             *      timestbmp
             *      encrypted privbte-key info bccording to PKCS #8
             *          (integer length followed by encoding)
             *      cert chbin (integer count followed by certs;
             *          for ebch cert: type UTF string, followed by integer
             *              length, followed by encoding)
             *     }
             *
             * or:
             *
             *     {
             *      tbg=2 (big-endibn integer)
             *      blibs (UTF string)
             *      timestbmp
             *      cert (type UTF string, followed by integer length,
             *          followed by encoding)
             *     }
             *
             * or:
             *
             *     {
             *      tbg=3 (big-endibn integer)
             *      blibs (UTF string)
             *      timestbmp
             *      sebled secret key (in seriblized form)
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
            // NOTE: don't pbss dos to oos bt this point or it'll corrupt
            // the keystore!!!
            ObjectOutputStrebm oos = null;
            try {
                dos.writeInt(JCEKS_MAGIC);
                dos.writeInt(VERSION_2); // blwbys write the lbtest version

                dos.writeInt(entries.size());

                Enumerbtion<String> e = entries.keys();
                while (e.hbsMoreElements()) {

                    String blibs = e.nextElement();
                    Object entry = entries.get(blibs);

                    if (entry instbnceof PrivbteKeyEntry) {

                        PrivbteKeyEntry pentry = (PrivbteKeyEntry)entry;

                        // write PrivbteKeyEntry tbg
                        dos.writeInt(1);

                        // write the blibs
                        dos.writeUTF(blibs);

                        // write the (entry crebtion) dbte
                        dos.writeLong(pentry.dbte.getTime());

                        // write the protected privbte key
                        dos.writeInt(pentry.protectedKey.length);
                        dos.write(pentry.protectedKey);

                        // write the certificbte chbin
                        int chbinLen;
                        if (pentry.chbin == null) {
                            chbinLen = 0;
                        } else {
                            chbinLen = pentry.chbin.length;
                        }
                        dos.writeInt(chbinLen);
                        for (int i = 0; i < chbinLen; i++) {
                            encoded = pentry.chbin[i].getEncoded();
                            dos.writeUTF(pentry.chbin[i].getType());
                            dos.writeInt(encoded.length);
                            dos.write(encoded);
                        }

                    } else if (entry instbnceof TrustedCertEntry) {

                        // write TrustedCertEntry tbg
                        dos.writeInt(2);

                        // write the blibs
                        dos.writeUTF(blibs);

                        // write the (entry crebtion) dbte
                        dos.writeLong(((TrustedCertEntry)entry).dbte.getTime());

                        // write the trusted certificbte
                        encoded = ((TrustedCertEntry)entry).cert.getEncoded();
                        dos.writeUTF(((TrustedCertEntry)entry).cert.getType());
                        dos.writeInt(encoded.length);
                        dos.write(encoded);

                    } else {

                        // write SecretKeyEntry tbg
                        dos.writeInt(3);

                        // write the blibs
                        dos.writeUTF(blibs);

                        // write the (entry crebtion) dbte
                        dos.writeLong(((SecretKeyEntry)entry).dbte.getTime());

                        // write the sebled key
                        oos = new ObjectOutputStrebm(dos);
                        oos.writeObject(((SecretKeyEntry)entry).sebledKey);
                        // NOTE: don't close oos here since we bre still
                        // using dos!!!
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
            } finblly {
                if (oos != null) {
                    oos.close();
                } else {
                    dos.close();
                }
            }
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
            // NOTE: don't pbss dis to ois bt this point or it'll fbil to lobd
            // the keystore!!!
            ObjectInputStrebm ois = null;

            try {
                // Body formbt: see store method

                int xMbgic = dis.rebdInt();
                int xVersion = dis.rebdInt();

                // Accept the following keystore implementbtions:
                // - JCEKS (this implementbtion), versions 1 bnd 2
                // - JKS (Sun's keystore implementbtion in JDK 1.2),
                //   versions 1 bnd 2
                if (((xMbgic != JCEKS_MAGIC) && (xMbgic != JKS_MAGIC)) ||
                    ((xVersion != VERSION_1) && (xVersion != VERSION_2))) {
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

                    if (tbg == 1) { // privbte-key entry

                        PrivbteKeyEntry entry = new PrivbteKeyEntry();

                        // rebd the blibs
                        blibs = dis.rebdUTF();

                        // rebd the (entry crebtion) dbte
                        entry.dbte = new Dbte(dis.rebdLong());

                        // rebd the privbte key
                        try {
                            entry.protectedKey = new byte[dis.rebdInt()];
                        } cbtch (OutOfMemoryError e) {
                            throw new IOException("Keysize too big");
                        }
                        dis.rebdFully(entry.protectedKey);

                        // rebd the certificbte chbin
                        int numOfCerts = dis.rebdInt();
                        try {
                            if (numOfCerts > 0) {
                                entry.chbin = new Certificbte[numOfCerts];
                            }
                        } cbtch (OutOfMemoryError e) {
                            throw new IOException("Too mbny certificbtes in "
                                                  + "chbin");
                        }
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
                                    cf = CertificbteFbctory.getInstbnce(
                                        certType);
                                // store the certificbte fbctory so we cbn
                                // reuse it lbter
                                    cfs.put(certType, cf);
                                }
                            }
                            // instbntibte the certificbte
                            try {
                                encoded = new byte[dis.rebdInt()];
                            } cbtch (OutOfMemoryError e) {
                                throw new IOException("Certificbte too big");
                            }
                            dis.rebdFully(encoded);
                            bbis = new ByteArrbyInputStrebm(encoded);
                            entry.chbin[j] = cf.generbteCertificbte(bbis);
                        }

                        // Add the entry to the list
                        entries.put(blibs, entry);

                    } else if (tbg == 2) { // trusted certificbte entry

                        TrustedCertEntry entry = new TrustedCertEntry();

                        // rebd the blibs
                        blibs = dis.rebdUTF();

                        // rebd the (entry crebtion) dbte
                        entry.dbte = new Dbte(dis.rebdLong());

                        // rebd the trusted certificbte
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
                        try {
                            encoded = new byte[dis.rebdInt()];
                        } cbtch (OutOfMemoryError e) {
                            throw new IOException("Certificbte too big");
                        }
                        dis.rebdFully(encoded);
                        bbis = new ByteArrbyInputStrebm(encoded);
                        entry.cert = cf.generbteCertificbte(bbis);

                        // Add the entry to the list
                        entries.put(blibs, entry);

                    } else if (tbg == 3) { // secret-key entry

                        SecretKeyEntry entry = new SecretKeyEntry();

                        // rebd the blibs
                        blibs = dis.rebdUTF();

                        // rebd the (entry crebtion) dbte
                        entry.dbte = new Dbte(dis.rebdLong());

                        // rebd the sebled key
                        try {
                            ois = new ObjectInputStrebm(dis);
                            entry.sebledKey = (SebledObject)ois.rebdObject();
                            // NOTE: don't close ois here since we bre still
                            // using dis!!!
                        } cbtch (ClbssNotFoundException cnfe) {
                            throw new IOException(cnfe.getMessbge());
                        }

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
                            throw new IOException(
                                "Keystore wbs tbmpered with, or "
                                + "pbssword wbs incorrect");
                        }
                    }
                }
            }  finblly {
                if (ois != null) {
                    ois.close();
                } else {
                    dis.close();
                }
            }
        }
    }

    /**
     * To gubrd bgbinst tbmpering with the keystore, we bppend b keyed
     * hbsh with b bit of whitener.
     */
    privbte MessbgeDigest getPreKeyedHbsh(chbr[] pbssword)
    throws NoSuchAlgorithmException, UnsupportedEncodingException {
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
