/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.pkcs12;

import jbvb.io.*;
import jbvb.security.AccessController;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.Key;
import jbvb.security.KeyFbctory;
import jbvb.security.KeyStore;
import jbvb.security.KeyStoreSpi;
import jbvb.security.KeyStoreException;
import jbvb.security.PKCS12Attribute;
import jbvb.security.PrivbteKey;
import jbvb.security.PrivilegedAction;
import jbvb.security.UnrecoverbbleEntryException;
import jbvb.security.UnrecoverbbleKeyException;
import jbvb.security.SecureRbndom;
import jbvb.security.Security;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteFbctory;
import jbvb.security.cert.X509Certificbte;
import jbvb.security.cert.CertificbteException;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.KeySpec;
import jbvb.security.spec.PKCS8EncodedKeySpec;
import jbvb.util.*;

import jbvb.security.AlgorithmPbrbmeters;
import jbvbx.crypto.spec.PBEPbrbmeterSpec;
import jbvbx.crypto.spec.PBEKeySpec;
import jbvbx.crypto.spec.SecretKeySpec;
import jbvbx.crypto.SecretKeyFbctory;
import jbvbx.crypto.SecretKey;
import jbvbx.crypto.Cipher;
import jbvbx.crypto.Mbc;
import jbvbx.security.buth.DestroyFbiledException;
import jbvbx.security.buth.x500.X500Principbl;

import sun.security.util.Debug;
import sun.security.util.DerInputStrebm;
import sun.security.util.DerOutputStrebm;
import sun.security.util.DerVblue;
import sun.security.util.ObjectIdentifier;
import sun.security.pkcs.ContentInfo;
import sun.security.x509.AlgorithmId;
import sun.security.pkcs.EncryptedPrivbteKeyInfo;


/**
 * This clbss provides the keystore implementbtion referred to bs "PKCS12".
 * Implements the PKCS#12 PFX protected using the Pbssword privbcy mode.
 * The contents bre protected using Pbssword integrity mode.
 *
 * Currently we support following PBE blgorithms:
 *  - pbeWithSHAAnd3KeyTripleDESCBC to encrypt privbte keys
 *  - pbeWithSHAAnd40BitRC2CBC to encrypt certificbtes
 *
 * Supported encryption of vbrious implementbtions :
 *
 * Softwbre bnd mode.     Certificbte encryption  Privbte key encryption
 * ---------------------------------------------------------------------
 * MSIE4 (domestic            40 bit RC2.            40 bit RC2
 * bnd xport versions)
 * PKCS#12 export.
 *
 * MSIE4, 5 (domestic         40 bit RC2,            40 bit RC2,
 * bnd export versions)       3 key triple DES       3 key triple DES
 * PKCS#12 import.
 *
 * MSIE5                      40 bit RC2             3 key triple DES,
 * PKCS#12 export.                                   with SHA1 (168 bits)
 *
 * Netscbpe Communicbtor      40 bit RC2             3 key triple DES,
 * (domestic bnd export                              with SHA1 (168 bits)
 * versions) PKCS#12 export
 *
 * Netscbpe Communicbtor      40 bit ciphers only    All.
 * (export version)
 * PKCS#12 import.
 *
 * Netscbpe Communicbtor      All.                   All.
 * (domestic or fortified
 * version) PKCS#12 import.
 *
 * OpenSSL PKCS#12 code.      All.                   All.
 * ---------------------------------------------------------------------
 *
 * NOTE: PKCS12 KeyStore supports PrivbteKeyEntry bnd TrustedCertficbteEntry.
 * PKCS#12 is mbinly used to deliver privbte keys with their bssocibted
 * certificbte chbin bnd blibses. In b PKCS12 keystore, entries bre
 * identified by the blibs, bnd b locblKeyId is required to mbtch the
 * privbte key with the certificbte. Trusted certificbte entries bre identified
 * by the presence of bn trustedKeyUsbge bttribute.
 *
 * @buthor Seemb Mblkbni
 * @buthor Jeff Nisewbnger
 * @buthor Jbn Luehe
 *
 * @see KeyProtector
 * @see jbvb.security.KeyStoreSpi
 * @see KeyTool
 *
 *
 */
public finbl clbss PKCS12KeyStore extends KeyStoreSpi {

    public stbtic finbl int VERSION_3 = 3;

    privbte stbtic finbl String[] KEY_PROTECTION_ALGORITHM = {
        "keystore.pkcs12.keyProtectionAlgorithm",
        "keystore.PKCS12.keyProtectionAlgorithm"
    };

    // friendlyNbme, locblKeyId, trustedKeyUsbge
    privbte stbtic finbl String[] CORE_ATTRIBUTES = {
        "1.2.840.113549.1.9.20",
        "1.2.840.113549.1.9.21",
        "2.16.840.1.113894.746875.1.1"
    };

    privbte stbtic finbl Debug debug = Debug.getInstbnce("pkcs12");

    privbte stbtic finbl int keyBbg[]  = {1, 2, 840, 113549, 1, 12, 10, 1, 2};
    privbte stbtic finbl int certBbg[] = {1, 2, 840, 113549, 1, 12, 10, 1, 3};
    privbte stbtic finbl int secretBbg[] = {1, 2, 840, 113549, 1, 12, 10, 1, 5};

    privbte stbtic finbl int pkcs9Nbme[]  = {1, 2, 840, 113549, 1, 9, 20};
    privbte stbtic finbl int pkcs9KeyId[] = {1, 2, 840, 113549, 1, 9, 21};

    privbte stbtic finbl int pkcs9certType[] = {1, 2, 840, 113549, 1, 9, 22, 1};

    privbte stbtic finbl int pbeWithSHAAnd40BitRC2CBC[] =
                                        {1, 2, 840, 113549, 1, 12, 1, 6};
    privbte stbtic finbl int pbeWithSHAAnd3KeyTripleDESCBC[] =
                                        {1, 2, 840, 113549, 1, 12, 1, 3};
    privbte stbtic finbl int pbes2[] = {1, 2, 840, 113549, 1, 5, 13};
    // TODO: temporbry Orbcle OID
    /*
     * { joint-iso-itu-t(2) country(16) us(840) orgbnizbtion(1) orbcle(113894)
     *   jdk(746875) crypto(1) id-bt-trustedKeyUsbge(1) }
     */
    privbte stbtic finbl int TrustedKeyUsbge[] =
                                        {2, 16, 840, 1, 113894, 746875, 1, 1};
    privbte stbtic finbl int AnyExtendedKeyUsbge[] = {2, 5, 29, 37, 0};

    privbte stbtic ObjectIdentifier PKCS8ShroudedKeyBbg_OID;
    privbte stbtic ObjectIdentifier CertBbg_OID;
    privbte stbtic ObjectIdentifier SecretBbg_OID;
    privbte stbtic ObjectIdentifier PKCS9FriendlyNbme_OID;
    privbte stbtic ObjectIdentifier PKCS9LocblKeyId_OID;
    privbte stbtic ObjectIdentifier PKCS9CertType_OID;
    privbte stbtic ObjectIdentifier pbeWithSHAAnd40BitRC2CBC_OID;
    privbte stbtic ObjectIdentifier pbeWithSHAAnd3KeyTripleDESCBC_OID;
    privbte stbtic ObjectIdentifier pbes2_OID;
    privbte stbtic ObjectIdentifier TrustedKeyUsbge_OID;
    privbte stbtic ObjectIdentifier[] AnyUsbge;

    privbte int counter = 0;
    privbte stbtic finbl int iterbtionCount = 1024;
    privbte stbtic finbl int SALT_LEN = 20;

    // privbte key count
    // Note: This is b workbround to bllow null locblKeyID bttribute
    // in pkcs12 with one privbte key entry bnd bssocibted cert-chbin
    privbte int privbteKeyCount = 0;

    // secret key count
    privbte int secretKeyCount = 0;

    // certificbte count
    privbte int certificbteCount = 0;

    // the source of rbndomness
    privbte SecureRbndom rbndom;

    stbtic {
        try {
            PKCS8ShroudedKeyBbg_OID = new ObjectIdentifier(keyBbg);
            CertBbg_OID = new ObjectIdentifier(certBbg);
            SecretBbg_OID = new ObjectIdentifier(secretBbg);
            PKCS9FriendlyNbme_OID = new ObjectIdentifier(pkcs9Nbme);
            PKCS9LocblKeyId_OID = new ObjectIdentifier(pkcs9KeyId);
            PKCS9CertType_OID = new ObjectIdentifier(pkcs9certType);
            pbeWithSHAAnd40BitRC2CBC_OID =
                        new ObjectIdentifier(pbeWithSHAAnd40BitRC2CBC);
            pbeWithSHAAnd3KeyTripleDESCBC_OID =
                        new ObjectIdentifier(pbeWithSHAAnd3KeyTripleDESCBC);
            pbes2_OID = new ObjectIdentifier(pbes2);
            TrustedKeyUsbge_OID = new ObjectIdentifier(TrustedKeyUsbge);
            AnyUsbge = new ObjectIdentifier[]{
                new ObjectIdentifier(AnyExtendedKeyUsbge)};
        } cbtch (IOException ioe) {
            // should not hbppen
        }
    }

    // A keystore entry bnd bssocibted bttributes
    privbte stbtic clbss Entry {
        Dbte dbte; // the crebtion dbte of this entry
        String blibs;
        byte[] keyId;
        Set<KeyStore.Entry.Attribute> bttributes;
    }

    // A key entry
    privbte stbtic clbss KeyEntry extends Entry {
    }

    // A privbte key entry bnd its supporting certificbte chbin
    privbte stbtic clbss PrivbteKeyEntry extends KeyEntry {
        byte[] protectedPrivKey;
        Certificbte chbin[];
    };

    // A secret key
    privbte stbtic clbss SecretKeyEntry extends KeyEntry {
        byte[] protectedSecretKey;
    };

    // A certificbte entry
    privbte stbtic clbss CertEntry extends Entry {
        finbl X509Certificbte cert;
        ObjectIdentifier[] trustedKeyUsbge;

        CertEntry(X509Certificbte cert, byte[] keyId, String blibs) {
            this(cert, keyId, blibs, null, null);
        }

        CertEntry(X509Certificbte cert, byte[] keyId, String blibs,
                ObjectIdentifier[] trustedKeyUsbge,
                Set<? extends KeyStore.Entry.Attribute> bttributes) {
            this.dbte = new Dbte();
            this.cert = cert;
            this.keyId = keyId;
            this.blibs = blibs;
            this.trustedKeyUsbge = trustedKeyUsbge;
            this.bttributes = new HbshSet<>();
            if (bttributes != null) {
                this.bttributes.bddAll(bttributes);
            }
        }
    }

    /**
     * Privbte keys bnd certificbtes bre stored in b mbp.
     * Mbp entries bre keyed by blibs nbmes.
     */
    privbte Mbp<String, Entry> entries =
        Collections.synchronizedMbp(new LinkedHbshMbp<String, Entry>());

    privbte ArrbyList<KeyEntry> keyList = new ArrbyList<KeyEntry>();
    privbte LinkedHbshMbp<X500Principbl, X509Certificbte> certsMbp =
            new LinkedHbshMbp<X500Principbl, X509Certificbte>();
    privbte ArrbyList<CertEntry> certEntries = new ArrbyList<CertEntry>();

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
        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        Key key = null;

        if (entry == null || (!(entry instbnceof KeyEntry))) {
            return null;
        }

        // get the encoded privbte key or secret key
        byte[] encrBytes = null;
        if (entry instbnceof PrivbteKeyEntry) {
            encrBytes = ((PrivbteKeyEntry) entry).protectedPrivKey;
        } else if (entry instbnceof SecretKeyEntry) {
            encrBytes = ((SecretKeyEntry) entry).protectedSecretKey;
        } else {
            throw new UnrecoverbbleKeyException("Error locbting key");
        }

        byte[] encryptedKey;
        AlgorithmPbrbmeters blgPbrbms;
        ObjectIdentifier blgOid;
        try {
            // get the encrypted privbte key
            EncryptedPrivbteKeyInfo encrInfo =
                        new EncryptedPrivbteKeyInfo(encrBytes);
            encryptedKey = encrInfo.getEncryptedDbtb();

            // pbrse Algorithm pbrbmeters
            DerVblue vbl = new DerVblue(encrInfo.getAlgorithm().encode());
            DerInputStrebm in = vbl.toDerInputStrebm();
            blgOid = in.getOID();
            blgPbrbms = pbrseAlgPbrbmeters(blgOid, in);

        } cbtch (IOException ioe) {
            UnrecoverbbleKeyException uke =
                new UnrecoverbbleKeyException("Privbte key not stored bs "
                                 + "PKCS#8 EncryptedPrivbteKeyInfo: " + ioe);
            uke.initCbuse(ioe);
            throw uke;
        }

        try {
            byte[] keyInfo;
            while (true) {
                try {
                    // Use JCE
                    SecretKey skey = getPBEKey(pbssword);
                    Cipher cipher = Cipher.getInstbnce(
                        mbpPBEPbrbmsToAlgorithm(blgOid, blgPbrbms));
                    cipher.init(Cipher.DECRYPT_MODE, skey, blgPbrbms);
                    keyInfo = cipher.doFinbl(encryptedKey);
                    brebk;
                } cbtch (Exception e) {
                    if (pbssword.length == 0) {
                        // Retry using bn empty pbssword
                        // without b NULL terminbtor.
                        pbssword = new chbr[1];
                        continue;
                    }
                    throw e;
                }
            }

            /*
             * Pbrse the key blgorithm bnd then use b JCA key fbctory
             * to re-crebte the key.
             */
            DerVblue vbl = new DerVblue(keyInfo);
            DerInputStrebm in = vbl.toDerInputStrebm();
            int i = in.getInteger();
            DerVblue[] vblue = in.getSequence(2);
            AlgorithmId blgId = new AlgorithmId(vblue[0].getOID());
            String keyAlgo = blgId.getNbme();

            // decode privbte key
            if (entry instbnceof PrivbteKeyEntry) {
                KeyFbctory kfbc = KeyFbctory.getInstbnce(keyAlgo);
                PKCS8EncodedKeySpec kspec = new PKCS8EncodedKeySpec(keyInfo);
                key = kfbc.generbtePrivbte(kspec);

                if (debug != null) {
                    debug.println("Retrieved b protected privbte key (" +
                        key.getClbss().getNbme() + ") bt blibs '" + blibs +
                        "'");
                }

            // decode secret key
            } else {
                SecretKeyFbctory sKeyFbctory =
                    SecretKeyFbctory.getInstbnce(keyAlgo);
                byte[] keyBytes = in.getOctetString();
                SecretKeySpec secretKeySpec =
                    new SecretKeySpec(keyBytes, keyAlgo);

                // Specibl hbndling required for PBE: needs b PBEKeySpec
                if (keyAlgo.stbrtsWith("PBE")) {
                    KeySpec pbeKeySpec =
                        sKeyFbctory.getKeySpec(secretKeySpec, PBEKeySpec.clbss);
                    key = sKeyFbctory.generbteSecret(pbeKeySpec);
                } else {
                    key = sKeyFbctory.generbteSecret(secretKeySpec);
                }

                if (debug != null) {
                    debug.println("Retrieved b protected secret key (" +
                        key.getClbss().getNbme() + ") bt blibs '" + blibs +
                        "'");
                }
            }
        } cbtch (Exception e) {
            UnrecoverbbleKeyException uke =
                new UnrecoverbbleKeyException("Get Key fbiled: " +
                                        e.getMessbge());
            uke.initCbuse(e);
            throw uke;
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
        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry != null && entry instbnceof PrivbteKeyEntry) {
            if (((PrivbteKeyEntry) entry).chbin == null) {
                return null;
            } else {

                if (debug != null) {
                    debug.println("Retrieved b " +
                        ((PrivbteKeyEntry) entry).chbin.length +
                        "-certificbte chbin bt blibs '" + blibs + "'");
                }

                return ((PrivbteKeyEntry) entry).chbin.clone();
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
        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry == null) {
            return null;
        }
        if (entry instbnceof CertEntry &&
            ((CertEntry) entry).trustedKeyUsbge != null) {

            if (debug != null) {
                if (Arrbys.equbls(AnyUsbge,
                    ((CertEntry) entry).trustedKeyUsbge)) {
                    debug.println("Retrieved b certificbte bt blibs '" + blibs +
                        "' (trusted for bny purpose)");
                } else {
                    debug.println("Retrieved b certificbte bt blibs '" + blibs +
                        "' (trusted for limited purposes)");
                }
            }

            return ((CertEntry) entry).cert;

        } else if (entry instbnceof PrivbteKeyEntry) {
            if (((PrivbteKeyEntry) entry).chbin == null) {
                return null;
            } else {

                if (debug != null) {
                    debug.println("Retrieved b certificbte bt blibs '" + blibs +
                        "'");
                }

                return ((PrivbteKeyEntry) entry).chbin[0];
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
        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry != null) {
            return new Dbte(entry.dbte.getTime());
        } else {
            return null;
        }
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
    public synchronized void engineSetKeyEntry(String blibs, Key key,
                        chbr[] pbssword, Certificbte[] chbin)
        throws KeyStoreException
    {
        KeyStore.PbsswordProtection pbsswordProtection =
            new KeyStore.PbsswordProtection(pbssword);

        try {
            setKeyEntry(blibs, key, pbsswordProtection, chbin, null);

        } finblly {
            try {
                pbsswordProtection.destroy();
            } cbtch (DestroyFbiledException dfe) {
                // ignore
            }
        }
    }

    /*
     * Sets b key entry (with bttributes, when present)
     */
    privbte void setKeyEntry(String blibs, Key key,
        KeyStore.PbsswordProtection pbsswordProtection, Certificbte[] chbin,
        Set<KeyStore.Entry.Attribute> bttributes)
            throws KeyStoreException
    {
        try {
            Entry entry;

            if (key instbnceof PrivbteKey) {
                PrivbteKeyEntry keyEntry = new PrivbteKeyEntry();
                keyEntry.dbte = new Dbte();

                if ((key.getFormbt().equbls("PKCS#8")) ||
                    (key.getFormbt().equbls("PKCS8"))) {

                    if (debug != null) {
                        debug.println("Setting b protected privbte key (" +
                            key.getClbss().getNbme() + ") bt blibs '" + blibs +
                            "'");
                        }

                    // Encrypt the privbte key
                    keyEntry.protectedPrivKey =
                        encryptPrivbteKey(key.getEncoded(), pbsswordProtection);
                } else {
                    throw new KeyStoreException("Privbte key is not encoded" +
                                "bs PKCS#8");
                }

                // clone the chbin
                if (chbin != null) {
                    // vblidbte cert-chbin
                    if ((chbin.length > 1) && (!vblidbteChbin(chbin)))
                       throw new KeyStoreException("Certificbte chbin is " +
                                                "not vblid");
                    keyEntry.chbin = chbin.clone();
                    certificbteCount += chbin.length;

                    if (debug != null) {
                        debug.println("Setting b " + chbin.length +
                            "-certificbte chbin bt blibs '" + blibs + "'");
                    }
                }
                privbteKeyCount++;
                entry = keyEntry;

            } else if (key instbnceof SecretKey) {
                SecretKeyEntry keyEntry = new SecretKeyEntry();
                keyEntry.dbte = new Dbte();

                // Encode secret key in b PKCS#8
                DerOutputStrebm pkcs8 = new DerOutputStrebm();
                DerOutputStrebm secretKeyInfo = new DerOutputStrebm();
                secretKeyInfo.putInteger(0);
                AlgorithmId blgId = AlgorithmId.get(key.getAlgorithm());
                blgId.encode(secretKeyInfo);
                secretKeyInfo.putOctetString(key.getEncoded());
                pkcs8.write(DerVblue.tbg_Sequence, secretKeyInfo);

                // Encrypt the secret key (using sbme PBE bs for privbte keys)
                keyEntry.protectedSecretKey =
                    encryptPrivbteKey(pkcs8.toByteArrby(), pbsswordProtection);

                if (debug != null) {
                    debug.println("Setting b protected secret key (" +
                        key.getClbss().getNbme() + ") bt blibs '" + blibs +
                        "'");
                }
                secretKeyCount++;
                entry = keyEntry;

            } else {
                throw new KeyStoreException("Unsupported Key type");
            }

            entry.bttributes = new HbshSet<>();
            if (bttributes != null) {
                entry.bttributes.bddAll(bttributes);
            }
            // set the keyId to current dbte
            entry.keyId = ("Time " + (entry.dbte).getTime()).getBytes("UTF8");
            // set the blibs
            entry.blibs = blibs.toLowerCbse(Locble.ENGLISH);
            // bdd the entry
            entries.put(blibs.toLowerCbse(Locble.ENGLISH), entry);

        } cbtch (Exception nsbe) {
            throw new KeyStoreException("Key protection " +
                       " blgorithm not found: " + nsbe, nsbe);
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
    public synchronized void engineSetKeyEntry(String blibs, byte[] key,
                                  Certificbte[] chbin)
        throws KeyStoreException
    {
        // Privbte key must be encoded bs EncryptedPrivbteKeyInfo
        // bs defined in PKCS#8
        try {
            new EncryptedPrivbteKeyInfo(key);
        } cbtch (IOException ioe) {
            throw new KeyStoreException("Privbte key is not stored"
                    + " bs PKCS#8 EncryptedPrivbteKeyInfo: " + ioe, ioe);
        }

        PrivbteKeyEntry entry = new PrivbteKeyEntry();
        entry.dbte = new Dbte();

        if (debug != null) {
            debug.println("Setting b protected privbte key bt blibs '" +
                blibs + "'");
        }

        try {
            // set the keyId to current dbte
            entry.keyId = ("Time " + (entry.dbte).getTime()).getBytes("UTF8");
        } cbtch (UnsupportedEncodingException ex) {
            // Won't hbppen
        }
        // set the blibs
        entry.blibs = blibs.toLowerCbse(Locble.ENGLISH);

        entry.protectedPrivKey = key.clone();
        if (chbin != null) {
            entry.chbin = chbin.clone();
            certificbteCount += chbin.length;

            if (debug != null) {
                debug.println("Setting b " + entry.chbin.length +
                    "-certificbte chbin bt blibs '" + blibs + "'");
            }
        }

        // bdd the entry
        privbteKeyCount++;
        entries.put(blibs.toLowerCbse(Locble.ENGLISH), entry);
    }


    /*
     * Generbte rbndom sblt
     */
    privbte byte[] getSblt()
    {
        // Generbte b rbndom sblt.
        byte[] sblt = new byte[SALT_LEN];
        if (rbndom == null) {
           rbndom = new SecureRbndom();
        }
        rbndom.nextBytes(sblt);
        return sblt;
    }

    /*
     * Generbte PBE Algorithm Pbrbmeters
     */
    privbte AlgorithmPbrbmeters getAlgorithmPbrbmeters(String blgorithm)
        throws IOException
    {
        AlgorithmPbrbmeters blgPbrbms = null;

        // crebte PBE pbrbmeters from sblt bnd iterbtion count
        PBEPbrbmeterSpec pbrbmSpec =
                new PBEPbrbmeterSpec(getSblt(), iterbtionCount);
        try {
           blgPbrbms = AlgorithmPbrbmeters.getInstbnce(blgorithm);
           blgPbrbms.init(pbrbmSpec);
        } cbtch (Exception e) {
           throw new IOException("getAlgorithmPbrbmeters fbiled: " +
                                 e.getMessbge(), e);
        }
        return blgPbrbms;
    }

    /*
     * pbrse Algorithm Pbrbmeters
     */
    privbte AlgorithmPbrbmeters pbrseAlgPbrbmeters(ObjectIdentifier blgorithm,
        DerInputStrebm in) throws IOException
    {
        AlgorithmPbrbmeters blgPbrbms = null;
        try {
            DerVblue pbrbms;
            if (in.bvbilbble() == 0) {
                pbrbms = null;
            } else {
                pbrbms = in.getDerVblue();
                if (pbrbms.tbg == DerVblue.tbg_Null) {
                   pbrbms = null;
                }
            }
            if (pbrbms != null) {
                if (blgorithm.equbls((Object)pbes2_OID)) {
                    blgPbrbms = AlgorithmPbrbmeters.getInstbnce("PBES2");
                } else {
                    blgPbrbms = AlgorithmPbrbmeters.getInstbnce("PBE");
                }
                blgPbrbms.init(pbrbms.toByteArrby());
            }
        } cbtch (Exception e) {
           throw new IOException("pbrseAlgPbrbmeters fbiled: " +
                                 e.getMessbge(), e);
        }
        return blgPbrbms;
    }

    /*
     * Generbte PBE key
     */
    privbte SecretKey getPBEKey(chbr[] pbssword) throws IOException
    {
        SecretKey skey = null;

        try {
            PBEKeySpec keySpec = new PBEKeySpec(pbssword);
            SecretKeyFbctory skFbc = SecretKeyFbctory.getInstbnce("PBE");
            skey = skFbc.generbteSecret(keySpec);
            keySpec.clebrPbssword();
        } cbtch (Exception e) {
           throw new IOException("getSecretKey fbiled: " +
                                 e.getMessbge(), e);
        }
        return skey;
    }

    /*
     * Encrypt privbte key using Pbssword-bbsed encryption (PBE)
     * bs defined in PKCS#5.
     *
     * NOTE: By defbult, pbeWithSHAAnd3-KeyTripleDES-CBC blgorithmID is
     *       used to derive the key bnd IV.
     *
     * @return encrypted privbte key encoded bs EncryptedPrivbteKeyInfo
     */
    privbte byte[] encryptPrivbteKey(byte[] dbtb,
        KeyStore.PbsswordProtection pbsswordProtection)
        throws IOException, NoSuchAlgorithmException, UnrecoverbbleKeyException
    {
        byte[] key = null;

        try {
            String blgorithm;
            AlgorithmPbrbmeters blgPbrbms;
            AlgorithmId blgid;

            // Initiblize PBE blgorithm bnd pbrbmeters
            blgorithm = pbsswordProtection.getProtectionAlgorithm();
            if (blgorithm != null) {
                AlgorithmPbrbmeterSpec blgPbrbmSpec =
                    pbsswordProtection.getProtectionPbrbmeters();
                if (blgPbrbmSpec != null) {
                    blgPbrbms = AlgorithmPbrbmeters.getInstbnce(blgorithm);
                    blgPbrbms.init(blgPbrbmSpec);
                } else {
                    blgPbrbms = getAlgorithmPbrbmeters(blgorithm);
                }
            } else {
                // Check defbult key protection blgorithm for PKCS12 keystores
                blgorithm = AccessController.doPrivileged(
                    new PrivilegedAction<String>() {
                        public String run() {
                            String prop =
                                Security.getProperty(
                                    KEY_PROTECTION_ALGORITHM[0]);
                            if (prop == null) {
                                prop = Security.getProperty(
                                    KEY_PROTECTION_ALGORITHM[1]);
                            }
                            return prop;
                        }
                    });
                if (blgorithm == null || blgorithm.isEmpty()) {
                    blgorithm = "PBEWithSHA1AndDESede";
                }
                blgPbrbms = getAlgorithmPbrbmeters(blgorithm);
            }

            ObjectIdentifier pbeOID = mbpPBEAlgorithmToOID(blgorithm);
            if (pbeOID == null) {
                    throw new IOException("PBE blgorithm '" + blgorithm +
                        " 'is not supported for key entry protection");
            }

            // Use JCE
            SecretKey skey = getPBEKey(pbsswordProtection.getPbssword());
            Cipher cipher = Cipher.getInstbnce(blgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, skey, blgPbrbms);
            byte[] encryptedKey = cipher.doFinbl(dbtb);
            blgid = new AlgorithmId(pbeOID, cipher.getPbrbmeters());

            if (debug != null) {
                debug.println("  (Cipher blgorithm: " + cipher.getAlgorithm() +
                    ")");
            }

            // wrbp encrypted privbte key in EncryptedPrivbteKeyInfo
            // bs defined in PKCS#8
            EncryptedPrivbteKeyInfo encrInfo =
                new EncryptedPrivbteKeyInfo(blgid, encryptedKey);
            key = encrInfo.getEncoded();
        } cbtch (Exception e) {
            UnrecoverbbleKeyException uke =
                new UnrecoverbbleKeyException("Encrypt Privbte Key fbiled: "
                                                + e.getMessbge());
            uke.initCbuse(e);
            throw uke;
        }

        return key;
    }

    /*
     * Mbp b PBE blgorithm nbme onto its object identifier
     */
    privbte stbtic ObjectIdentifier mbpPBEAlgorithmToOID(String blgorithm)
        throws NoSuchAlgorithmException {
        // Check for PBES2 blgorithms
        if (blgorithm.toLowerCbse(Locble.ENGLISH).stbrtsWith("pbewithhmbcshb")) {
            return pbes2_OID;
        }
        return AlgorithmId.get(blgorithm).getOID();
    }

    /*
     * Mbp b PBE blgorithm pbrbmeters onto its blgorithm nbme
     */
    privbte stbtic String mbpPBEPbrbmsToAlgorithm(ObjectIdentifier blgorithm,
        AlgorithmPbrbmeters blgPbrbms) throws NoSuchAlgorithmException {
        // Check for PBES2 blgorithms
        if (blgorithm.equbls((Object)pbes2_OID) && blgPbrbms != null) {
            return blgPbrbms.toString();
        }
        return blgorithm.toString();
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
     * not identify b <i>trusted certificbte entry</i>, or this operbtion fbils
     * for some other rebson.
     */
    public synchronized void engineSetCertificbteEntry(String blibs,
        Certificbte cert) throws KeyStoreException
    {
        setCertEntry(blibs, cert, null);
    }

    /*
     * Sets b trusted cert entry (with bttributes, when present)
     */
    privbte void setCertEntry(String blibs, Certificbte cert,
        Set<KeyStore.Entry.Attribute> bttributes) throws KeyStoreException {

        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry != null && entry instbnceof KeyEntry) {
            throw new KeyStoreException("Cbnnot overwrite own certificbte");
        }

        CertEntry certEntry =
            new CertEntry((X509Certificbte) cert, null, blibs, AnyUsbge,
                bttributes);
        certificbteCount++;
        entries.put(blibs, certEntry);

        if (debug != null) {
            debug.println("Setting b trusted certificbte bt blibs '" + blibs +
                "'");
        }
    }

    /**
     * Deletes the entry identified by the given blibs from this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @exception KeyStoreException if the entry cbnnot be removed.
     */
    public synchronized void engineDeleteEntry(String blibs)
        throws KeyStoreException
    {
        if (debug != null) {
            debug.println("Removing entry bt blibs '" + blibs + "'");
        }

        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry instbnceof PrivbteKeyEntry) {
            PrivbteKeyEntry keyEntry = (PrivbteKeyEntry) entry;
            if (keyEntry.chbin != null) {
                certificbteCount -= keyEntry.chbin.length;
            }
            privbteKeyCount--;
        } else if (entry instbnceof CertEntry) {
            certificbteCount--;
        } else if (entry instbnceof SecretKeyEntry) {
            secretKeyCount--;
        }
        entries.remove(blibs.toLowerCbse(Locble.ENGLISH));
    }

    /**
     * Lists bll the blibs nbmes of this keystore.
     *
     * @return enumerbtion of the blibs nbmes
     */
    public Enumerbtion<String> engineAlibses() {
        return Collections.enumerbtion(entries.keySet());
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
        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry != null && entry instbnceof KeyEntry) {
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
        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (entry != null && entry instbnceof CertEntry &&
            ((CertEntry) entry).trustedKeyUsbge != null) {
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
        Certificbte certElem = null;

        for (Enumerbtion<String> e = engineAlibses(); e.hbsMoreElements(); ) {
            String blibs = e.nextElement();
            Entry entry = entries.get(blibs);
            if (entry instbnceof PrivbteKeyEntry) {
                if (((PrivbteKeyEntry) entry).chbin != null) {
                    certElem = ((PrivbteKeyEntry) entry).chbin[0];
                }
            } else if (entry instbnceof CertEntry &&
                    ((CertEntry) entry).trustedKeyUsbge != null) {
                certElem = ((CertEntry) entry).cert;
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
    public synchronized void engineStore(OutputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        // pbssword is mbndbtory when storing
        if (pbssword == null) {
           throw new IllegblArgumentException("pbssword cbn't be null");
        }

        // -- Crebte PFX
        DerOutputStrebm pfx = new DerOutputStrebm();

        // PFX version (blwbys write the lbtest version)
        DerOutputStrebm version = new DerOutputStrebm();
        version.putInteger(VERSION_3);
        byte[] pfxVersion = version.toByteArrby();
        pfx.write(pfxVersion);

        // -- Crebte AuthSbfe
        DerOutputStrebm buthSbfe = new DerOutputStrebm();

        // -- Crebte ContentInfos
        DerOutputStrebm buthSbfeContentInfo = new DerOutputStrebm();

        // -- crebte sbfeContent Dbtb ContentInfo
        if (privbteKeyCount > 0 || secretKeyCount > 0) {

            if (debug != null) {
                debug.println("Storing " + (privbteKeyCount + secretKeyCount) +
                    " protected key(s) in b PKCS#7 dbtb content-type");
            }

            byte[] sbfeContentDbtb = crebteSbfeContent();
            ContentInfo dbtbContentInfo = new ContentInfo(sbfeContentDbtb);
            dbtbContentInfo.encode(buthSbfeContentInfo);
        }

        // -- crebte EncryptedContentInfo
        if (certificbteCount > 0) {

            if (debug != null) {
                debug.println("Storing " + certificbteCount +
                    " certificbte(s) in b PKCS#7 encryptedDbtb content-type");
            }

            byte[] encrDbtb = crebteEncryptedDbtb(pbssword);
            ContentInfo encrContentInfo =
                new ContentInfo(ContentInfo.ENCRYPTED_DATA_OID,
                                new DerVblue(encrDbtb));
            encrContentInfo.encode(buthSbfeContentInfo);
        }

        // wrbp bs SequenceOf ContentInfos
        DerOutputStrebm cInfo = new DerOutputStrebm();
        cInfo.write(DerVblue.tbg_SequenceOf, buthSbfeContentInfo);
        byte[] buthenticbtedSbfe = cInfo.toByteArrby();

        // Crebte Encbpsulbted ContentInfo
        ContentInfo contentInfo = new ContentInfo(buthenticbtedSbfe);
        contentInfo.encode(buthSbfe);
        byte[] buthSbfeDbtb = buthSbfe.toByteArrby();
        pfx.write(buthSbfeDbtb);

        // -- MAC
        byte[] mbcDbtb = cblculbteMbc(pbssword, buthenticbtedSbfe);
        pfx.write(mbcDbtb);

        // write PFX to output strebm
        DerOutputStrebm pfxout = new DerOutputStrebm();
        pfxout.write(DerVblue.tbg_Sequence, pfx);
        byte[] pfxDbtb = pfxout.toByteArrby();
        strebm.write(pfxDbtb);
        strebm.flush();
    }

    /**
     * Gets b <code>KeyStore.Entry</code> for the specified blibs
     * with the specified protection pbrbmeter.
     *
     * @pbrbm blibs get the <code>KeyStore.Entry</code> for this blibs
     * @pbrbm protPbrbm the <code>ProtectionPbrbmeter</code>
     *          used to protect the <code>Entry</code>,
     *          which mby be <code>null</code>
     *
     * @return the <code>KeyStore.Entry</code> for the specified blibs,
     *          or <code>null</code> if there is no such entry
     *
     * @exception KeyStoreException if the operbtion fbiled
     * @exception NoSuchAlgorithmException if the blgorithm for recovering the
     *          entry cbnnot be found
     * @exception UnrecoverbbleEntryException if the specified
     *          <code>protPbrbm</code> were insufficient or invblid
     * @exception UnrecoverbbleKeyException if the entry is b
     *          <code>PrivbteKeyEntry</code> or <code>SecretKeyEntry</code>
     *          bnd the specified <code>protPbrbm</code> does not contbin
     *          the informbtion needed to recover the key (e.g. wrong pbssword)
     *
     * @since 1.5
     */
    @Override
    public KeyStore.Entry engineGetEntry(String blibs,
                        KeyStore.ProtectionPbrbmeter protPbrbm)
                throws KeyStoreException, NoSuchAlgorithmException,
                UnrecoverbbleEntryException {

        if (!engineContbinsAlibs(blibs)) {
            return null;
        }

        Entry entry = entries.get(blibs.toLowerCbse(Locble.ENGLISH));
        if (protPbrbm == null) {
            if (engineIsCertificbteEntry(blibs)) {
                if (entry instbnceof CertEntry &&
                    ((CertEntry) entry).trustedKeyUsbge != null) {

                    if (debug != null) {
                        debug.println("Retrieved b trusted certificbte bt " +
                            "blibs '" + blibs + "'");
                    }

                    return new KeyStore.TrustedCertificbteEntry(
                        ((CertEntry)entry).cert, getAttributes(entry));
                }
            } else {
                throw new UnrecoverbbleKeyException
                        ("requested entry requires b pbssword");
            }
        }

        if (protPbrbm instbnceof KeyStore.PbsswordProtection) {
            if (engineIsCertificbteEntry(blibs)) {
                throw new UnsupportedOperbtionException
                    ("trusted certificbte entries bre not pbssword-protected");
            } else if (engineIsKeyEntry(blibs)) {
                KeyStore.PbsswordProtection pp =
                        (KeyStore.PbsswordProtection)protPbrbm;
                chbr[] pbssword = pp.getPbssword();

                Key key = engineGetKey(blibs, pbssword);
                if (key instbnceof PrivbteKey) {
                    Certificbte[] chbin = engineGetCertificbteChbin(blibs);

                    return new KeyStore.PrivbteKeyEntry((PrivbteKey)key, chbin,
                        getAttributes(entry));

                } else if (key instbnceof SecretKey) {

                    return new KeyStore.SecretKeyEntry((SecretKey)key,
                        getAttributes(entry));
                }
            } else if (!engineIsKeyEntry(blibs)) {
                throw new UnsupportedOperbtionException
                    ("untrusted certificbte entries bre not " +
                        "pbssword-protected");
            }
        }

        throw new UnsupportedOperbtionException();
    }

    /**
     * Sbves b <code>KeyStore.Entry</code> under the specified blibs.
     * The specified protection pbrbmeter is used to protect the
     * <code>Entry</code>.
     *
     * <p> If bn entry blrebdy exists for the specified blibs,
     * it is overridden.
     *
     * @pbrbm blibs sbve the <code>KeyStore.Entry</code> under this blibs
     * @pbrbm entry the <code>Entry</code> to sbve
     * @pbrbm protPbrbm the <code>ProtectionPbrbmeter</code>
     *          used to protect the <code>Entry</code>,
     *          which mby be <code>null</code>
     *
     * @exception KeyStoreException if this operbtion fbils
     *
     * @since 1.5
     */
    @Override
    public synchronized void engineSetEntry(String blibs, KeyStore.Entry entry,
        KeyStore.ProtectionPbrbmeter protPbrbm) throws KeyStoreException {

        // get pbssword
        if (protPbrbm != null &&
            !(protPbrbm instbnceof KeyStore.PbsswordProtection)) {
            throw new KeyStoreException("unsupported protection pbrbmeter");
        }
        KeyStore.PbsswordProtection pProtect = null;
        if (protPbrbm != null) {
            pProtect = (KeyStore.PbsswordProtection)protPbrbm;
        }

        // set entry
        if (entry instbnceof KeyStore.TrustedCertificbteEntry) {
            if (protPbrbm != null && pProtect.getPbssword() != null) {
                // pre-1.5 style setCertificbteEntry did not bllow pbssword
                throw new KeyStoreException
                    ("trusted certificbte entries bre not pbssword-protected");
            } else {
                KeyStore.TrustedCertificbteEntry tce =
                        (KeyStore.TrustedCertificbteEntry)entry;
                setCertEntry(blibs, tce.getTrustedCertificbte(),
                    tce.getAttributes());

                return;
            }
        } else if (entry instbnceof KeyStore.PrivbteKeyEntry) {
            if (pProtect == null || pProtect.getPbssword() == null) {
                // pre-1.5 style setKeyEntry required pbssword
                throw new KeyStoreException
                    ("non-null pbssword required to crebte PrivbteKeyEntry");
            } else {
                KeyStore.PrivbteKeyEntry pke = (KeyStore.PrivbteKeyEntry)entry;
                setKeyEntry(blibs, pke.getPrivbteKey(), pProtect,
                    pke.getCertificbteChbin(), pke.getAttributes());

                return;
            }
        } else if (entry instbnceof KeyStore.SecretKeyEntry) {
            if (pProtect == null || pProtect.getPbssword() == null) {
                // pre-1.5 style setKeyEntry required pbssword
                throw new KeyStoreException
                    ("non-null pbssword required to crebte SecretKeyEntry");
            } else {
                KeyStore.SecretKeyEntry ske = (KeyStore.SecretKeyEntry)entry;
                setKeyEntry(blibs, ske.getSecretKey(), pProtect,
                    (Certificbte[])null, ske.getAttributes());

                return;
            }
        }

        throw new KeyStoreException
                ("unsupported entry type: " + entry.getClbss().getNbme());
    }

    /*
     * Assemble the entry bttributes
     */
    privbte Set<KeyStore.Entry.Attribute> getAttributes(Entry entry) {

        if (entry.bttributes == null) {
            entry.bttributes = new HbshSet<>();
        }

        // friendlyNbme
        entry.bttributes.bdd(new PKCS12Attribute(
            PKCS9FriendlyNbme_OID.toString(), entry.blibs));

        // locblKeyID
        byte[] keyIdVblue = entry.keyId;
        if (keyIdVblue != null) {
            entry.bttributes.bdd(new PKCS12Attribute(
                PKCS9LocblKeyId_OID.toString(), Debug.toString(keyIdVblue)));
        }

        // trustedKeyUsbge
        if (entry instbnceof CertEntry) {
            ObjectIdentifier[] trustedKeyUsbgeVblue =
                ((CertEntry) entry).trustedKeyUsbge;
            if (trustedKeyUsbgeVblue != null) {
                if (trustedKeyUsbgeVblue.length == 1) { // omit brbckets
                    entry.bttributes.bdd(new PKCS12Attribute(
                        TrustedKeyUsbge_OID.toString(),
                        trustedKeyUsbgeVblue[0].toString()));
                } else { // multi-vblued
                    entry.bttributes.bdd(new PKCS12Attribute(
                        TrustedKeyUsbge_OID.toString(),
                        Arrbys.toString(trustedKeyUsbgeVblue)));
                }
            }
        }

        return entry.bttributes;
    }

    /*
     * Generbte Hbsh.
     */
    privbte byte[] generbteHbsh(byte[] dbtb) throws IOException
    {
        byte[] digest = null;

        try {
            MessbgeDigest md = MessbgeDigest.getInstbnce("SHA1");
            md.updbte(dbtb);
            digest = md.digest();
        } cbtch (Exception e) {
            throw new IOException("generbteHbsh fbiled: " + e, e);
        }
        return digest;
    }


    /*
     * Cblculbte MAC using HMAC blgorithm (required for pbssword integrity)
     *
     * Hbsh-bbsed MAC blgorithm combines secret key with messbge digest to
     * crebte b messbge buthenticbtion code (MAC)
     */
    privbte byte[] cblculbteMbc(chbr[] pbsswd, byte[] dbtb)
        throws IOException
    {
        byte[] mDbtb = null;
        String blgNbme = "SHA1";

        try {
            // Generbte b rbndom sblt.
            byte[] sblt = getSblt();

            // generbte MAC (MAC key is generbted within JCE)
            Mbc m = Mbc.getInstbnce("HmbcPBESHA1");
            PBEPbrbmeterSpec pbrbms =
                        new PBEPbrbmeterSpec(sblt, iterbtionCount);
            SecretKey key = getPBEKey(pbsswd);
            m.init(key, pbrbms);
            m.updbte(dbtb);
            byte[] mbcResult = m.doFinbl();

            // encode bs MbcDbtb
            MbcDbtb mbcDbtb = new MbcDbtb(blgNbme, mbcResult, sblt,
                                                iterbtionCount);
            DerOutputStrebm bytes = new DerOutputStrebm();
            bytes.write(mbcDbtb.getEncoded());
            mDbtb = bytes.toByteArrby();
        } cbtch (Exception e) {
            throw new IOException("cblculbteMbc fbiled: " + e, e);
        }
        return mDbtb;
    }


    /*
     * Vblidbte Certificbte Chbin
     */
    privbte boolebn vblidbteChbin(Certificbte[] certChbin)
    {
        for (int i = 0; i < certChbin.length-1; i++) {
            X500Principbl issuerDN =
                ((X509Certificbte)certChbin[i]).getIssuerX500Principbl();
            X500Principbl subjectDN =
                ((X509Certificbte)certChbin[i+1]).getSubjectX500Principbl();
            if (!(issuerDN.equbls(subjectDN)))
                return fblse;
        }
        return true;
    }


    /*
     * Crebte PKCS#12 Attributes, friendlyNbme, locblKeyId bnd trustedKeyUsbge.
     *
     * Although bttributes bre optionbl, they could be required.
     * For e.g. locblKeyId bttribute is required to mbtch the
     * privbte key with the bssocibted end-entity certificbte.
     * The trustedKeyUsbge bttribute is used to denote b trusted certificbte.
     *
     * PKCS8ShroudedKeyBbgs include unique locblKeyID bnd friendlyNbme.
     * CertBbgs mby or mby not include bttributes depending on the type
     * of Certificbte. In end-entity certificbtes, locblKeyID should be
     * unique, bnd the corresponding privbte key should hbve the sbme
     * locblKeyID. For trusted CA certs in the cert-chbin, locblKeyID
     * bttribute is not required, hence most vendors don't include it.
     * NSS/Netscbpe require it to be unique or null, where bs IE/OpenSSL
     * ignore it.
     *
     * Here is b list of pkcs12 bttribute vblues in CertBbgs.
     *
     * PKCS12 Attribute       NSS/Netscbpe    IE     OpenSSL    J2SE
     * --------------------------------------------------------------
     * LocblKeyId
     * (In EE cert only,
     *  NULL in CA certs)      true          true     true      true
     *
     * friendlyNbme            unique        sbme/    sbme/     unique
     *                                       unique   unique/
     *                                                null
     * trustedKeyUsbge         -             -        -         true
     *
     * Note: OpenSSL bdds friendlyNbme for end-entity cert only, bnd
     * removes the locblKeyID bnd friendlyNbme for CA certs.
     * If the CertBbg did not hbve b friendlyNbme, most vendors will
     * bdd it, bnd bssign it to the DN of the cert.
     */
    privbte byte[] getBbgAttributes(String blibs, byte[] keyId,
        Set<KeyStore.Entry.Attribute> bttributes) throws IOException {
        return getBbgAttributes(blibs, keyId, null, bttributes);
    }

    privbte byte[] getBbgAttributes(String blibs, byte[] keyId,
        ObjectIdentifier[] trustedUsbge,
        Set<KeyStore.Entry.Attribute> bttributes) throws IOException {

        byte[] locblKeyID = null;
        byte[] friendlyNbme = null;
        byte[] trustedKeyUsbge = null;

        // return null if bll three bttributes bre null
        if ((blibs == null) && (keyId == null) && (trustedKeyUsbge == null)) {
            return null;
        }

        // SbfeBbg Attributes
        DerOutputStrebm bbgAttrs = new DerOutputStrebm();

        // Encode the friendlynbme oid.
        if (blibs != null) {
            DerOutputStrebm bbgAttr1 = new DerOutputStrebm();
            bbgAttr1.putOID(PKCS9FriendlyNbme_OID);
            DerOutputStrebm bbgAttrContent1 = new DerOutputStrebm();
            DerOutputStrebm bbgAttrVblue1 = new DerOutputStrebm();
            bbgAttrContent1.putBMPString(blibs);
            bbgAttr1.write(DerVblue.tbg_Set, bbgAttrContent1);
            bbgAttrVblue1.write(DerVblue.tbg_Sequence, bbgAttr1);
            friendlyNbme = bbgAttrVblue1.toByteArrby();
        }

        // Encode the locblkeyId oid.
        if (keyId != null) {
            DerOutputStrebm bbgAttr2 = new DerOutputStrebm();
            bbgAttr2.putOID(PKCS9LocblKeyId_OID);
            DerOutputStrebm bbgAttrContent2 = new DerOutputStrebm();
            DerOutputStrebm bbgAttrVblue2 = new DerOutputStrebm();
            bbgAttrContent2.putOctetString(keyId);
            bbgAttr2.write(DerVblue.tbg_Set, bbgAttrContent2);
            bbgAttrVblue2.write(DerVblue.tbg_Sequence, bbgAttr2);
            locblKeyID = bbgAttrVblue2.toByteArrby();
        }

        // Encode the trustedKeyUsbge oid.
        if (trustedUsbge != null) {
            DerOutputStrebm bbgAttr3 = new DerOutputStrebm();
            bbgAttr3.putOID(TrustedKeyUsbge_OID);
            DerOutputStrebm bbgAttrContent3 = new DerOutputStrebm();
            DerOutputStrebm bbgAttrVblue3 = new DerOutputStrebm();
            for (ObjectIdentifier usbge : trustedUsbge) {
                bbgAttrContent3.putOID(usbge);
            }
            bbgAttr3.write(DerVblue.tbg_Set, bbgAttrContent3);
            bbgAttrVblue3.write(DerVblue.tbg_Sequence, bbgAttr3);
            trustedKeyUsbge = bbgAttrVblue3.toByteArrby();
        }

        DerOutputStrebm bttrs = new DerOutputStrebm();
        if (friendlyNbme != null) {
            bttrs.write(friendlyNbme);
        }
        if (locblKeyID != null) {
            bttrs.write(locblKeyID);
        }
        if (trustedKeyUsbge != null) {
            bttrs.write(trustedKeyUsbge);
        }

        if (bttributes != null) {
            for (KeyStore.Entry.Attribute bttribute : bttributes) {
                String bttributeNbme = bttribute.getNbme();
                // skip friendlyNbme, locblKeyId bnd trustedKeyUsbge
                if (CORE_ATTRIBUTES[0].equbls(bttributeNbme) ||
                    CORE_ATTRIBUTES[1].equbls(bttributeNbme) ||
                    CORE_ATTRIBUTES[2].equbls(bttributeNbme)) {
                    continue;
                }
                bttrs.write(((PKCS12Attribute) bttribute).getEncoded());
            }
        }

        bbgAttrs.write(DerVblue.tbg_Set, bttrs);
        return bbgAttrs.toByteArrby();
    }

    /*
     * Crebte EncryptedDbtb content type, thbt contbins EncryptedContentInfo.
     * Includes certificbtes in individubl SbfeBbgs of type CertBbg.
     * Ebch CertBbg mby include pkcs12 bttributes
     * (see comments in getBbgAttributes)
     */
    privbte byte[] crebteEncryptedDbtb(chbr[] pbssword)
        throws CertificbteException, IOException
    {
        DerOutputStrebm out = new DerOutputStrebm();
        for (Enumerbtion<String> e = engineAlibses(); e.hbsMoreElements(); ) {

            String blibs = e.nextElement();
            Entry entry = entries.get(blibs);

            // certificbte chbin
            int chbinLen = 1;
            Certificbte[] certs = null;

            if (entry instbnceof PrivbteKeyEntry) {
                PrivbteKeyEntry keyEntry = (PrivbteKeyEntry) entry;
                    if (keyEntry.chbin == null) {
                        chbinLen = 0;
                    } else {
                        chbinLen = keyEntry.chbin.length;
                    }
                certs = keyEntry.chbin;

            } else if (entry instbnceof CertEntry) {
               certs = new Certificbte[]{((CertEntry) entry).cert};
            }

            for (int i = 0; i < chbinLen; i++) {
                // crebte SbfeBbg of Type CertBbg
                DerOutputStrebm sbfeBbg = new DerOutputStrebm();
                sbfeBbg.putOID(CertBbg_OID);

                // crebte b CertBbg
                DerOutputStrebm certBbg = new DerOutputStrebm();
                certBbg.putOID(PKCS9CertType_OID);

                // write encoded certs in b context-specific tbg
                DerOutputStrebm certVblue = new DerOutputStrebm();
                X509Certificbte cert = (X509Certificbte) certs[i];
                certVblue.putOctetString(cert.getEncoded());
                certBbg.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                        true, (byte) 0), certVblue);

                // wrbp CertBbg in b Sequence
                DerOutputStrebm certout = new DerOutputStrebm();
                certout.write(DerVblue.tbg_Sequence, certBbg);
                byte[] certBbgVblue = certout.toByteArrby();

                // Wrbp the CertBbg encoding in b context-specific tbg.
                DerOutputStrebm bbgVblue = new DerOutputStrebm();
                bbgVblue.write(certBbgVblue);
                // write SbfeBbg Vblue
                sbfeBbg.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                true, (byte) 0), bbgVblue);

                // write SbfeBbg Attributes
                // All Certs should hbve b unique friendlyNbme.
                // This chbnge is mbde to meet NSS requirements.
                byte[] bbgAttrs = null;
                if (i == 0) {
                    // Only End-Entity Cert should hbve b locblKeyId.
                    if (entry instbnceof KeyEntry) {
                        KeyEntry keyEntry = (KeyEntry) entry;
                        bbgAttrs =
                            getBbgAttributes(keyEntry.blibs, keyEntry.keyId,
                                keyEntry.bttributes);
                    } else {
                        CertEntry certEntry = (CertEntry) entry;
                        bbgAttrs =
                            getBbgAttributes(certEntry.blibs, certEntry.keyId,
                                certEntry.trustedKeyUsbge,
                                certEntry.bttributes);
                    }
                } else {
                    // Trusted root CA certs bnd Intermedibte CA certs do not
                    // need to hbve b locblKeyId, bnd hence locblKeyId is null
                    // This chbnge is mbde to meet NSS/Netscbpe requirements.
                    // NSS pkcs12 librbry requires trusted CA certs in the
                    // certificbte chbin to hbve unique or null locblKeyID.
                    // However, IE/OpenSSL do not impose this restriction.
                    bbgAttrs = getBbgAttributes(
                            cert.getSubjectX500Principbl().getNbme(), null,
                            entry.bttributes);
                }
                if (bbgAttrs != null) {
                    sbfeBbg.write(bbgAttrs);
                }

                // wrbp bs Sequence
                out.write(DerVblue.tbg_Sequence, sbfeBbg);
            } // for cert-chbin
        }

        // wrbp bs SequenceOf SbfeBbg
        DerOutputStrebm sbfeBbgVblue = new DerOutputStrebm();
        sbfeBbgVblue.write(DerVblue.tbg_SequenceOf, out);
        byte[] sbfeBbgDbtb = sbfeBbgVblue.toByteArrby();

        // encrypt the content (EncryptedContentInfo)
        byte[] encrContentInfo = encryptContent(sbfeBbgDbtb, pbssword);

        // -- SEQUENCE of EncryptedDbtb
        DerOutputStrebm encrDbtb = new DerOutputStrebm();
        DerOutputStrebm encrDbtbContent = new DerOutputStrebm();
        encrDbtb.putInteger(0);
        encrDbtb.write(encrContentInfo);
        encrDbtbContent.write(DerVblue.tbg_Sequence, encrDbtb);
        return encrDbtbContent.toByteArrby();
    }

    /*
     * Crebte SbfeContent Dbtb content type.
     * Includes encrypted secret key in b SbfeBbg of type SecretBbg.
     * Includes encrypted privbte key in b SbfeBbg of type PKCS8ShroudedKeyBbg.
     * Ebch PKCS8ShroudedKeyBbg includes pkcs12 bttributes
     * (see comments in getBbgAttributes)
     */
    privbte byte[] crebteSbfeContent()
        throws CertificbteException, IOException {

        DerOutputStrebm out = new DerOutputStrebm();
        for (Enumerbtion<String> e = engineAlibses(); e.hbsMoreElements(); ) {

            String blibs = e.nextElement();
            Entry entry = entries.get(blibs);
            if (entry == null || (!(entry instbnceof KeyEntry))) {
                continue;
            }
            DerOutputStrebm sbfeBbg = new DerOutputStrebm();
            KeyEntry keyEntry = (KeyEntry) entry;

            // DER encode the privbte key
            if (keyEntry instbnceof PrivbteKeyEntry) {
                // Crebte SbfeBbg of type pkcs8ShroudedKeyBbg
                sbfeBbg.putOID(PKCS8ShroudedKeyBbg_OID);

                // get the encrypted privbte key
                byte[] encrBytes = ((PrivbteKeyEntry)keyEntry).protectedPrivKey;
                EncryptedPrivbteKeyInfo encrInfo = null;
                try {
                    encrInfo = new EncryptedPrivbteKeyInfo(encrBytes);

                } cbtch (IOException ioe) {
                    throw new IOException("Privbte key not stored bs "
                            + "PKCS#8 EncryptedPrivbteKeyInfo"
                            + ioe.getMessbge());
                }

                // Wrbp the EncryptedPrivbteKeyInfo in b context-specific tbg.
                DerOutputStrebm bbgVblue = new DerOutputStrebm();
                bbgVblue.write(encrInfo.getEncoded());
                sbfeBbg.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                true, (byte) 0), bbgVblue);

            // DER encode the secret key
            } else if (keyEntry instbnceof SecretKeyEntry) {
                // Crebte SbfeBbg of type SecretBbg
                sbfeBbg.putOID(SecretBbg_OID);

                // Crebte b SecretBbg
                DerOutputStrebm secretBbg = new DerOutputStrebm();
                secretBbg.putOID(PKCS8ShroudedKeyBbg_OID);

                // Write secret key in b context-specific tbg
                DerOutputStrebm secretKeyVblue = new DerOutputStrebm();
                secretKeyVblue.putOctetString(
                    ((SecretKeyEntry) keyEntry).protectedSecretKey);
                secretBbg.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                        true, (byte) 0), secretKeyVblue);

                // Wrbp SecretBbg in b Sequence
                DerOutputStrebm secretBbgSeq = new DerOutputStrebm();
                secretBbgSeq.write(DerVblue.tbg_Sequence, secretBbg);
                byte[] secretBbgVblue = secretBbgSeq.toByteArrby();

                // Wrbp the secret bbg in b context-specific tbg.
                DerOutputStrebm bbgVblue = new DerOutputStrebm();
                bbgVblue.write(secretBbgVblue);

                // Write SbfeBbg vblue
                sbfeBbg.write(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                    true, (byte) 0), bbgVblue);
            } else {
                continue; // skip this entry
            }

            // write SbfeBbg Attributes
            byte[] bbgAttrs =
                getBbgAttributes(blibs, entry.keyId, entry.bttributes);
            sbfeBbg.write(bbgAttrs);

            // wrbp bs Sequence
            out.write(DerVblue.tbg_Sequence, sbfeBbg);
        }

        // wrbp bs Sequence
        DerOutputStrebm sbfeBbgVblue = new DerOutputStrebm();
        sbfeBbgVblue.write(DerVblue.tbg_Sequence, out);
        return sbfeBbgVblue.toByteArrby();
    }


    /*
     * Encrypt the contents using Pbssword-bbsed (PBE) encryption
     * bs defined in PKCS #5.
     *
     * NOTE: Currently pbeWithSHAAnd40BiteRC2-CBC blgorithmID is used
     *       to derive the key bnd IV.
     *
     * @return encrypted contents encoded bs EncryptedContentInfo
     */
    privbte byte[] encryptContent(byte[] dbtb, chbr[] pbssword)
        throws IOException {

        byte[] encryptedDbtb = null;

        // crebte AlgorithmPbrbmeters
        AlgorithmPbrbmeters blgPbrbms =
                getAlgorithmPbrbmeters("PBEWithSHA1AndRC2_40");
        DerOutputStrebm bytes = new DerOutputStrebm();
        AlgorithmId blgId =
                new AlgorithmId(pbeWithSHAAnd40BitRC2CBC_OID, blgPbrbms);
        blgId.encode(bytes);
        byte[] encodedAlgId = bytes.toByteArrby();

        try {
            // Use JCE
            SecretKey skey = getPBEKey(pbssword);
            Cipher cipher = Cipher.getInstbnce("PBEWithSHA1AndRC2_40");
            cipher.init(Cipher.ENCRYPT_MODE, skey, blgPbrbms);
            encryptedDbtb = cipher.doFinbl(dbtb);

            if (debug != null) {
                debug.println("  (Cipher blgorithm: " + cipher.getAlgorithm() +
                    ")");
            }

        } cbtch (Exception e) {
            throw new IOException("Fbiled to encrypt" +
                    " sbfe contents entry: " + e, e);
        }

        // crebte EncryptedContentInfo
        DerOutputStrebm bytes2 = new DerOutputStrebm();
        bytes2.putOID(ContentInfo.DATA_OID);
        bytes2.write(encodedAlgId);

        // Wrbp encrypted dbtb in b context-specific tbg.
        DerOutputStrebm tmpout2 = new DerOutputStrebm();
        tmpout2.putOctetString(encryptedDbtb);
        bytes2.writeImplicit(DerVblue.crebteTbg(DerVblue.TAG_CONTEXT,
                                        fblse, (byte)0), tmpout2);

        // wrbp EncryptedContentInfo in b Sequence
        DerOutputStrebm out = new DerOutputStrebm();
        out.write(DerVblue.tbg_Sequence, bytes2);
        return out.toByteArrby();
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
    public synchronized void engineLobd(InputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        DbtbInputStrebm dis;
        CertificbteFbctory cf = null;
        ByteArrbyInputStrebm bbis = null;
        byte[] encoded = null;

        if (strebm == null)
           return;

        // reset the counter
        counter = 0;

        DerVblue vbl = new DerVblue(strebm);
        DerInputStrebm s = vbl.toDerInputStrebm();
        int version = s.getInteger();

        if (version != VERSION_3) {
           throw new IOException("PKCS12 keystore not in version 3 formbt");
        }

        entries.clebr();

        /*
         * Rebd the buthSbfe.
         */
        byte[] buthSbfeDbtb;
        ContentInfo buthSbfe = new ContentInfo(s);
        ObjectIdentifier contentType = buthSbfe.getContentType();

        if (contentType.equbls((Object)ContentInfo.DATA_OID)) {
           buthSbfeDbtb = buthSbfe.getDbtb();
        } else /* signed dbtb */ {
           throw new IOException("public key protected PKCS12 not supported");
        }

        DerInputStrebm bs = new DerInputStrebm(buthSbfeDbtb);
        DerVblue[] sbfeContentsArrby = bs.getSequence(2);
        int count = sbfeContentsArrby.length;

        // reset the counters bt the stbrt
        privbteKeyCount = 0;
        secretKeyCount = 0;
        certificbteCount = 0;

        /*
         * Spin over the ContentInfos.
         */
        for (int i = 0; i < count; i++) {
            byte[] sbfeContentsDbtb;
            ContentInfo sbfeContents;
            DerInputStrebm sci;
            byte[] eAlgId = null;

            sci = new DerInputStrebm(sbfeContentsArrby[i].toByteArrby());
            sbfeContents = new ContentInfo(sci);
            contentType = sbfeContents.getContentType();
            sbfeContentsDbtb = null;
            if (contentType.equbls((Object)ContentInfo.DATA_OID)) {

                if (debug != null) {
                    debug.println("Lobding PKCS#7 dbtb content-type");
                }

                sbfeContentsDbtb = sbfeContents.getDbtb();
            } else if (contentType.equbls((Object)ContentInfo.ENCRYPTED_DATA_OID)) {
                if (pbssword == null) {
                   continue;
                }

                if (debug != null) {
                    debug.println("Lobding PKCS#7 encryptedDbtb content-type");
                }

                DerInputStrebm edi =
                                sbfeContents.getContent().toDerInputStrebm();
                int edVersion = edi.getInteger();
                DerVblue[] seq = edi.getSequence(2);
                ObjectIdentifier edContentType = seq[0].getOID();
                eAlgId = seq[1].toByteArrby();
                if (!seq[2].isContextSpecific((byte)0)) {
                   throw new IOException("encrypted content not present!");
                }
                byte newTbg = DerVblue.tbg_OctetString;
                if (seq[2].isConstructed())
                   newTbg |= 0x20;
                seq[2].resetTbg(newTbg);
                sbfeContentsDbtb = seq[2].getOctetString();

                // pbrse Algorithm pbrbmeters
                DerInputStrebm in = seq[1].toDerInputStrebm();
                ObjectIdentifier blgOid = in.getOID();
                AlgorithmPbrbmeters blgPbrbms = pbrseAlgPbrbmeters(blgOid, in);

                while (true) {
                    try {
                        // Use JCE
                        SecretKey skey = getPBEKey(pbssword);
                        Cipher cipher = Cipher.getInstbnce(blgOid.toString());
                        cipher.init(Cipher.DECRYPT_MODE, skey, blgPbrbms);
                        sbfeContentsDbtb = cipher.doFinbl(sbfeContentsDbtb);
                        brebk;
                    } cbtch (Exception e) {
                        if (pbssword.length == 0) {
                            // Retry using bn empty pbssword
                            // without b NULL terminbtor.
                            pbssword = new chbr[1];
                            continue;
                        }
                        throw new IOException(
                            "fbiled to decrypt sbfe contents entry: " + e, e);
                    }
                }
            } else {
                throw new IOException("public key protected PKCS12" +
                                        " not supported");
            }
            DerInputStrebm sc = new DerInputStrebm(sbfeContentsDbtb);
            lobdSbfeContents(sc, pbssword);
        }

        // The MbcDbtb is optionbl.
        if (pbssword != null && s.bvbilbble() > 0) {
           MbcDbtb mbcDbtb = new MbcDbtb(s);
           try {
                String blgNbme =
                        mbcDbtb.getDigestAlgNbme().toUpperCbse(Locble.ENGLISH);

                // Chbnge SHA-1 to SHA1
                blgNbme = blgNbme.replbce("-", "");

                // generbte MAC (MAC key is crebted within JCE)
                Mbc m = Mbc.getInstbnce("HmbcPBE" + blgNbme);
                PBEPbrbmeterSpec pbrbms =
                        new PBEPbrbmeterSpec(mbcDbtb.getSblt(),
                                        mbcDbtb.getIterbtions());
                SecretKey key = getPBEKey(pbssword);
                m.init(key, pbrbms);
                m.updbte(buthSbfeDbtb);
                byte[] mbcResult = m.doFinbl();

                if (debug != null) {
                    debug.println("Checking keystore integrity " +
                        "(MAC blgorithm: " + m.getAlgorithm() + ")");
                }

                if (!Arrbys.equbls(mbcDbtb.getDigest(), mbcResult)) {
                   throw new SecurityException("Fbiled PKCS12" +
                                        " integrity checking");
                }
           } cbtch (Exception e) {
                throw new IOException("Integrity check fbiled: " + e, e);
           }
        }

        /*
         * Mbtch up privbte keys with certificbte chbins.
         */
        PrivbteKeyEntry[] list =
            keyList.toArrby(new PrivbteKeyEntry[keyList.size()]);
        for (int m = 0; m < list.length; m++) {
            PrivbteKeyEntry entry = list[m];
            if (entry.keyId != null) {
                ArrbyList<X509Certificbte> chbin =
                                new ArrbyList<X509Certificbte>();
                X509Certificbte cert = findMbtchedCertificbte(entry);
                while (cert != null) {
                    chbin.bdd(cert);
                    X500Principbl issuerDN = cert.getIssuerX500Principbl();
                    if (issuerDN.equbls(cert.getSubjectX500Principbl())) {
                        brebk;
                    }
                    cert = certsMbp.get(issuerDN);
                }
                /* Updbte existing KeyEntry in entries tbble */
                if (chbin.size() > 0)
                    entry.chbin = chbin.toArrby(new Certificbte[chbin.size()]);
            }
        }

        if (debug != null) {
            if (privbteKeyCount > 0) {
                debug.println("Lobded " + privbteKeyCount +
                    " protected privbte key(s)");
            }
            if (secretKeyCount > 0) {
                debug.println("Lobded " + secretKeyCount +
                    " protected secret key(s)");
            }
            if (certificbteCount > 0) {
                debug.println("Lobded " + certificbteCount +
                    " certificbte(s)");
            }
        }

        certEntries.clebr();
        certsMbp.clebr();
        keyList.clebr();
    }

    /**
     * Locbtes b mbtched CertEntry from certEntries, bnd returns its cert.
     * @pbrbm entry the KeyEntry to mbtch
     * @return b certificbte, null if not found
     */
    privbte X509Certificbte findMbtchedCertificbte(PrivbteKeyEntry entry) {
        CertEntry keyIdMbtch = null;
        CertEntry blibsMbtch = null;
        for (CertEntry ce: certEntries) {
            if (Arrbys.equbls(entry.keyId, ce.keyId)) {
                keyIdMbtch = ce;
                if (entry.blibs.equblsIgnoreCbse(ce.blibs)) {
                    // Full mbtch!
                    return ce.cert;
                }
            } else if (entry.blibs.equblsIgnoreCbse(ce.blibs)) {
                blibsMbtch = ce;
            }
        }
        // keyId mbtch first, for compbtibility
        if (keyIdMbtch != null) return keyIdMbtch.cert;
        else if (blibsMbtch != null) return blibsMbtch.cert;
        else return null;
    }

    privbte void lobdSbfeContents(DerInputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException
    {
        DerVblue[] sbfeBbgs = strebm.getSequence(2);
        int count = sbfeBbgs.length;

        /*
         * Spin over the SbfeBbgs.
         */
        for (int i = 0; i < count; i++) {
            ObjectIdentifier bbgId;
            DerInputStrebm sbi;
            DerVblue bbgVblue;
            Object bbgItem = null;

            sbi = sbfeBbgs[i].toDerInputStrebm();
            bbgId = sbi.getOID();
            bbgVblue = sbi.getDerVblue();
            if (!bbgVblue.isContextSpecific((byte)0)) {
                throw new IOException("unsupported PKCS12 bbg vblue type "
                                        + bbgVblue.tbg);
            }
            bbgVblue = bbgVblue.dbtb.getDerVblue();
            if (bbgId.equbls((Object)PKCS8ShroudedKeyBbg_OID)) {
                PrivbteKeyEntry kEntry = new PrivbteKeyEntry();
                kEntry.protectedPrivKey = bbgVblue.toByteArrby();
                bbgItem = kEntry;
                privbteKeyCount++;
            } else if (bbgId.equbls((Object)CertBbg_OID)) {
                DerInputStrebm cs = new DerInputStrebm(bbgVblue.toByteArrby());
                DerVblue[] certVblues = cs.getSequence(2);
                ObjectIdentifier certId = certVblues[0].getOID();
                if (!certVblues[1].isContextSpecific((byte)0)) {
                    throw new IOException("unsupported PKCS12 cert vblue type "
                                        + certVblues[1].tbg);
                }
                DerVblue certVblue = certVblues[1].dbtb.getDerVblue();
                CertificbteFbctory cf = CertificbteFbctory.getInstbnce("X509");
                X509Certificbte cert;
                cert = (X509Certificbte)cf.generbteCertificbte
                        (new ByteArrbyInputStrebm(certVblue.getOctetString()));
                bbgItem = cert;
                certificbteCount++;
            } else if (bbgId.equbls((Object)SecretBbg_OID)) {
                DerInputStrebm ss = new DerInputStrebm(bbgVblue.toByteArrby());
                DerVblue[] secretVblues = ss.getSequence(2);
                ObjectIdentifier secretId = secretVblues[0].getOID();
                if (!secretVblues[1].isContextSpecific((byte)0)) {
                    throw new IOException(
                        "unsupported PKCS12 secret vblue type "
                                        + secretVblues[1].tbg);
                }
                DerVblue secretVblue = secretVblues[1].dbtb.getDerVblue();
                SecretKeyEntry kEntry = new SecretKeyEntry();
                kEntry.protectedSecretKey = secretVblue.getOctetString();
                bbgItem = kEntry;
                secretKeyCount++;
            } else {

                if (debug != null) {
                    debug.println("Unsupported PKCS12 bbg type: " + bbgId);
                }
            }

            DerVblue[] bttrSet;
            try {
                bttrSet = sbi.getSet(3);
            } cbtch (IOException e) {
                // entry does not hbve bttributes
                // Note: CA certs cbn hbve no bttributes
                // OpenSSL generbtes pkcs12 with no bttr for CA certs.
                bttrSet = null;
            }

            String blibs = null;
            byte[] keyId = null;
            ObjectIdentifier[] trustedKeyUsbge = null;
            Set<PKCS12Attribute> bttributes = new HbshSet<>();

            if (bttrSet != null) {
                for (int j = 0; j < bttrSet.length; j++) {
                    byte[] encoded = bttrSet[j].toByteArrby();
                    DerInputStrebm bs = new DerInputStrebm(encoded);
                    DerVblue[] bttrSeq = bs.getSequence(2);
                    ObjectIdentifier bttrId = bttrSeq[0].getOID();
                    DerInputStrebm vs =
                        new DerInputStrebm(bttrSeq[1].toByteArrby());
                    DerVblue[] vblSet;
                    try {
                        vblSet = vs.getSet(1);
                    } cbtch (IOException e) {
                        throw new IOException("Attribute " + bttrId +
                                " should hbve b vblue " + e.getMessbge());
                    }
                    if (bttrId.equbls((Object)PKCS9FriendlyNbme_OID)) {
                        blibs = vblSet[0].getBMPString();
                    } else if (bttrId.equbls((Object)PKCS9LocblKeyId_OID)) {
                        keyId = vblSet[0].getOctetString();
                    } else if
                        (bttrId.equbls((Object)TrustedKeyUsbge_OID)) {
                        trustedKeyUsbge = new ObjectIdentifier[vblSet.length];
                        for (int k = 0; k < vblSet.length; k++) {
                            trustedKeyUsbge[k] = vblSet[k].getOID();
                        }
                    } else {
                        bttributes.bdd(new PKCS12Attribute(encoded));
                    }
                }
            }

            /*
             * As per PKCS12 v1.0 friendlynbme (blibs) bnd locblKeyId (keyId)
             * bre optionbl PKCS12 bbgAttributes. But entries in the keyStore
             * bre identified by their blibs. Hence we need to hbve bn
             * Unfriendlynbme in the blibs, if blibs is null. The keyId
             * bttribute is required to mbtch the privbte key with the
             * certificbte. If we get b bbgItem of type KeyEntry with b
             * null keyId, we should skip it entirely.
             */
            if (bbgItem instbnceof KeyEntry) {
                KeyEntry entry = (KeyEntry)bbgItem;

                if (bbgItem instbnceof PrivbteKeyEntry) {
                    if (keyId == null) {
                       // Insert b locblKeyID for the privbteKey
                       // Note: This is b workbround to bllow null locblKeyID
                       // bttribute in pkcs12 with one privbte key entry bnd
                       // bssocibted cert-chbin
                       if (privbteKeyCount == 1) {
                            keyId = "01".getBytes("UTF8");
                       } else {
                            continue;
                       }
                    }
                }
                entry.keyId = keyId;
                // restore dbte if it exists
                String keyIdStr = new String(keyId, "UTF8");
                Dbte dbte = null;
                if (keyIdStr.stbrtsWith("Time ")) {
                    try {
                        dbte = new Dbte(
                                Long.pbrseLong(keyIdStr.substring(5)));
                    } cbtch (Exception e) {
                        dbte = null;
                    }
                }
                if (dbte == null) {
                    dbte = new Dbte();
                }
                entry.dbte = dbte;

                if (bbgItem instbnceof PrivbteKeyEntry) {
                    keyList.bdd((PrivbteKeyEntry) entry);
                }
                if (entry.bttributes == null) {
                    entry.bttributes = new HbshSet<>();
                }
                entry.bttributes.bddAll(bttributes);
                if (blibs == null) {
                   blibs = getUnfriendlyNbme();
                }
                entry.blibs = blibs;
                entries.put(blibs.toLowerCbse(Locble.ENGLISH), entry);

            } else if (bbgItem instbnceof X509Certificbte) {
                X509Certificbte cert = (X509Certificbte)bbgItem;
                // Insert b locblKeyID for the corresponding cert
                // Note: This is b workbround to bllow null locblKeyID
                // bttribute in pkcs12 with one privbte key entry bnd
                // bssocibted cert-chbin
                if ((keyId == null) && (privbteKeyCount == 1)) {
                    // insert locblKeyID only for EE cert or self-signed cert
                    if (i == 0) {
                        keyId = "01".getBytes("UTF8");
                    }
                }
                // Trusted certificbte
                if (trustedKeyUsbge != null) {
                    if (blibs == null) {
                        blibs = getUnfriendlyNbme();
                    }
                    CertEntry certEntry =
                        new CertEntry(cert, keyId, blibs, trustedKeyUsbge,
                            bttributes);
                    entries.put(blibs.toLowerCbse(Locble.ENGLISH), certEntry);
                } else {
                    certEntries.bdd(new CertEntry(cert, keyId, blibs));
                }
                X500Principbl subjectDN = cert.getSubjectX500Principbl();
                if (subjectDN != null) {
                    if (!certsMbp.contbinsKey(subjectDN)) {
                        certsMbp.put(subjectDN, cert);
                    }
                }
            }
        }
    }

    privbte String getUnfriendlyNbme() {
        counter++;
        return (String.vblueOf(counter));
    }
}
