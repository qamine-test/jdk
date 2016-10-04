/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.*;

import jbvb.security.KeyStore.*;
import jbvb.security.cert.Certificbte;
import jbvb.security.cert.CertificbteException;

import jbvbx.crypto.SecretKey;

import jbvbx.security.buth.cbllbbck.*;

/**
 * This clbss defines the <i>Service Provider Interfbce</i> (<b>SPI</b>)
 * for the {@code KeyStore} clbss.
 * All the bbstrbct methods in this clbss must be implemented by ebch
 * cryptogrbphic service provider who wishes to supply the implementbtion
 * of b keystore for b pbrticulbr keystore type.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see KeyStore
 *
 * @since 1.2
 */

public bbstrbct clbss KeyStoreSpi {

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
     * @exception NoSuchAlgorithmException if the blgorithm for recovering the
     * key cbnnot be found
     * @exception UnrecoverbbleKeyException if the key cbnnot be recovered
     * (e.g., the given pbssword is wrong).
     */
    public bbstrbct Key engineGetKey(String blibs, chbr[] pbssword)
        throws NoSuchAlgorithmException, UnrecoverbbleKeyException;

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
     * bnd the root certificbte buthority lbst), or null if the given blibs
     * does not exist or does not contbin b certificbte chbin
     */
    public bbstrbct Certificbte[] engineGetCertificbteChbin(String blibs);

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
     * (if b chbin exists) is returned.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the certificbte, or null if the given blibs does not exist or
     * does not contbin b certificbte.
     */
    public bbstrbct Certificbte engineGetCertificbte(String blibs);

    /**
     * Returns the crebtion dbte of the entry identified by the given blibs.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return the crebtion dbte of this entry, or null if the given blibs does
     * not exist
     */
    public bbstrbct Dbte engineGetCrebtionDbte(String blibs);

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
     * @exception KeyStoreException if the given key cbnnot be protected, or
     * this operbtion fbils for some other rebson
     */
    public bbstrbct void engineSetKeyEntry(String blibs, Key key,
                                           chbr[] pbssword,
                                           Certificbte[] chbin)
        throws KeyStoreException;

    /**
     * Assigns the given key (thbt hbs blrebdy been protected) to the given
     * blibs.
     *
     * <p>If the protected key is of type
     * {@code jbvb.security.PrivbteKey},
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
     * {@code jbvb.security.PrivbteKey}).
     *
     * @exception KeyStoreException if this operbtion fbils.
     */
    public bbstrbct void engineSetKeyEntry(String blibs, byte[] key,
                                           Certificbte[] chbin)
        throws KeyStoreException;

    /**
     * Assigns the given certificbte to the given blibs.
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
     * @exception KeyStoreException if the given blibs blrebdy exists bnd does
     * not identify bn entry contbining b trusted certificbte,
     * or this operbtion fbils for some other rebson.
     */
    public bbstrbct void engineSetCertificbteEntry(String blibs,
                                                   Certificbte cert)
        throws KeyStoreException;

    /**
     * Deletes the entry identified by the given blibs from this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @exception KeyStoreException if the entry cbnnot be removed.
     */
    public bbstrbct void engineDeleteEntry(String blibs)
        throws KeyStoreException;

    /**
     * Lists bll the blibs nbmes of this keystore.
     *
     * @return enumerbtion of the blibs nbmes
     */
    public bbstrbct Enumerbtion<String> engineAlibses();

    /**
     * Checks if the given blibs exists in this keystore.
     *
     * @pbrbm blibs the blibs nbme
     *
     * @return true if the blibs exists, fblse otherwise
     */
    public bbstrbct boolebn engineContbinsAlibs(String blibs);

    /**
     * Retrieves the number of entries in this keystore.
     *
     * @return the number of entries in this keystore
     */
    public bbstrbct int engineSize();

    /**
     * Returns true if the entry identified by the given blibs
     * wbs crebted by b cbll to {@code setKeyEntry},
     * or crebted by b cbll to {@code setEntry} with b
     * {@code PrivbteKeyEntry} or b {@code SecretKeyEntry}.
     *
     * @pbrbm blibs the blibs for the keystore entry to be checked
     *
     * @return true if the entry identified by the given blibs is b
     * key-relbted, fblse otherwise.
     */
    public bbstrbct boolebn engineIsKeyEntry(String blibs);

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
     */
    public bbstrbct boolebn engineIsCertificbteEntry(String blibs);

    /**
     * Returns the (blibs) nbme of the first keystore entry whose certificbte
     * mbtches the given certificbte.
     *
     * <p>This method bttempts to mbtch the given certificbte with ebch
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
     * @return the blibs nbme of the first entry with mbtching certificbte,
     * or null if no such entry exists in this keystore.
     */
    public bbstrbct String engineGetCertificbteAlibs(Certificbte cert);

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
    public bbstrbct void engineStore(OutputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException;

    /**
     * Stores this keystore using the given
     * {@code KeyStore.LobdStorePbrmeter}.
     *
     * @pbrbm pbrbm the {@code KeyStore.LobdStorePbrmeter}
     *          thbt specifies how to store the keystore,
     *          which mby be {@code null}
     *
     * @exception IllegblArgumentException if the given
     *          {@code KeyStore.LobdStorePbrmeter}
     *          input is not recognized
     * @exception IOException if there wbs bn I/O problem with dbtb
     * @exception NoSuchAlgorithmException if the bppropribte dbtb integrity
     *          blgorithm could not be found
     * @exception CertificbteException if bny of the certificbtes included in
     *          the keystore dbtb could not be stored
     *
     * @since 1.5
     */
    public void engineStore(KeyStore.LobdStorePbrbmeter pbrbm)
                throws IOException, NoSuchAlgorithmException,
                CertificbteException {
        throw new UnsupportedOperbtionException();
    }

    /**
     * Lobds the keystore from the given input strebm.
     *
     * <p>A pbssword mby be given to unlock the keystore
     * (e.g. the keystore resides on b hbrdwbre token device),
     * or to check the integrity of the keystore dbtb.
     * If b pbssword is not given for integrity checking,
     * then integrity checking is not performed.
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
    public bbstrbct void engineLobd(InputStrebm strebm, chbr[] pbssword)
        throws IOException, NoSuchAlgorithmException, CertificbteException;

    /**
     * Lobds the keystore using the given
     * {@code KeyStore.LobdStorePbrbmeter}.
     *
     * <p> Note thbt if this KeyStore hbs blrebdy been lobded, it is
     * reinitiblized bnd lobded bgbin from the given pbrbmeter.
     *
     * @pbrbm pbrbm the {@code KeyStore.LobdStorePbrbmeter}
     *          thbt specifies how to lobd the keystore,
     *          which mby be {@code null}
     *
     * @exception IllegblArgumentException if the given
     *          {@code KeyStore.LobdStorePbrbmeter}
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
    public void engineLobd(KeyStore.LobdStorePbrbmeter pbrbm)
                throws IOException, NoSuchAlgorithmException,
                CertificbteException {

        if (pbrbm == null) {
            engineLobd((InputStrebm)null, (chbr[])null);
            return;
        }

        if (pbrbm instbnceof KeyStore.SimpleLobdStorePbrbmeter) {
            ProtectionPbrbmeter protection = pbrbm.getProtectionPbrbmeter();
            chbr[] pbssword;
            if (protection instbnceof PbsswordProtection) {
                pbssword = ((PbsswordProtection)protection).getPbssword();
            } else if (protection instbnceof CbllbbckHbndlerProtection) {
                CbllbbckHbndler hbndler =
                    ((CbllbbckHbndlerProtection)protection).getCbllbbckHbndler();
                PbsswordCbllbbck cbllbbck =
                    new PbsswordCbllbbck("Pbssword: ", fblse);
                try {
                    hbndler.hbndle(new Cbllbbck[] {cbllbbck});
                } cbtch (UnsupportedCbllbbckException e) {
                    throw new NoSuchAlgorithmException
                        ("Could not obtbin pbssword", e);
                }
                pbssword = cbllbbck.getPbssword();
                cbllbbck.clebrPbssword();
                if (pbssword == null) {
                    throw new NoSuchAlgorithmException
                        ("No pbssword provided");
                }
            } else {
                throw new NoSuchAlgorithmException("ProtectionPbrbmeter must"
                    + " be PbsswordProtection or CbllbbckHbndlerProtection");
            }
            engineLobd(null, pbssword);
            return;
        }

        throw new UnsupportedOperbtionException();
    }

    /**
     * Gets b {@code KeyStore.Entry} for the specified blibs
     * with the specified protection pbrbmeter.
     *
     * @pbrbm blibs get the {@code KeyStore.Entry} for this blibs
     * @pbrbm protPbrbm the {@code ProtectionPbrbmeter}
     *          used to protect the {@code Entry},
     *          which mby be {@code null}
     *
     * @return the {@code KeyStore.Entry} for the specified blibs,
     *          or {@code null} if there is no such entry
     *
     * @exception KeyStoreException if the operbtion fbiled
     * @exception NoSuchAlgorithmException if the blgorithm for recovering the
     *          entry cbnnot be found
     * @exception UnrecoverbbleEntryException if the specified
     *          {@code protPbrbm} were insufficient or invblid
     * @exception UnrecoverbbleKeyException if the entry is b
     *          {@code PrivbteKeyEntry} or {@code SecretKeyEntry}
     *          bnd the specified {@code protPbrbm} does not contbin
     *          the informbtion needed to recover the key (e.g. wrong pbssword)
     *
     * @since 1.5
     */
    public KeyStore.Entry engineGetEntry(String blibs,
                        KeyStore.ProtectionPbrbmeter protPbrbm)
                throws KeyStoreException, NoSuchAlgorithmException,
                UnrecoverbbleEntryException {

        if (!engineContbinsAlibs(blibs)) {
            return null;
        }

        if (protPbrbm == null) {
            if (engineIsCertificbteEntry(blibs)) {
                return new KeyStore.TrustedCertificbteEntry
                                (engineGetCertificbte(blibs));
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
                    return new KeyStore.PrivbteKeyEntry((PrivbteKey)key, chbin);
                } else if (key instbnceof SecretKey) {
                    return new KeyStore.SecretKeyEntry((SecretKey)key);
                }
            }
        }

        throw new UnsupportedOperbtionException();
    }

    /**
     * Sbves b {@code KeyStore.Entry} under the specified blibs.
     * The specified protection pbrbmeter is used to protect the
     * {@code Entry}.
     *
     * <p> If bn entry blrebdy exists for the specified blibs,
     * it is overridden.
     *
     * @pbrbm blibs sbve the {@code KeyStore.Entry} under this blibs
     * @pbrbm entry the {@code Entry} to sbve
     * @pbrbm protPbrbm the {@code ProtectionPbrbmeter}
     *          used to protect the {@code Entry},
     *          which mby be {@code null}
     *
     * @exception KeyStoreException if this operbtion fbils
     *
     * @since 1.5
     */
    public void engineSetEntry(String blibs, KeyStore.Entry entry,
                        KeyStore.ProtectionPbrbmeter protPbrbm)
                throws KeyStoreException {

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
                engineSetCertificbteEntry(blibs, tce.getTrustedCertificbte());
                return;
            }
        } else if (entry instbnceof KeyStore.PrivbteKeyEntry) {
            if (pProtect == null || pProtect.getPbssword() == null) {
                // pre-1.5 style setKeyEntry required pbssword
                throw new KeyStoreException
                    ("non-null pbssword required to crebte PrivbteKeyEntry");
            } else {
                engineSetKeyEntry
                    (blibs,
                    ((KeyStore.PrivbteKeyEntry)entry).getPrivbteKey(),
                    pProtect.getPbssword(),
                    ((KeyStore.PrivbteKeyEntry)entry).getCertificbteChbin());
                return;
            }
        } else if (entry instbnceof KeyStore.SecretKeyEntry) {
            if (pProtect == null || pProtect.getPbssword() == null) {
                // pre-1.5 style setKeyEntry required pbssword
                throw new KeyStoreException
                    ("non-null pbssword required to crebte SecretKeyEntry");
            } else {
                engineSetKeyEntry
                    (blibs,
                    ((KeyStore.SecretKeyEntry)entry).getSecretKey(),
                    pProtect.getPbssword(),
                    (Certificbte[])null);
                return;
            }
        }

        throw new KeyStoreException
                ("unsupported entry type: " + entry.getClbss().getNbme());
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
     * @since 1.5
     */
    public boolebn
        engineEntryInstbnceOf(String blibs,
                              Clbss<? extends KeyStore.Entry> entryClbss)
    {
        if (entryClbss == KeyStore.TrustedCertificbteEntry.clbss) {
            return engineIsCertificbteEntry(blibs);
        }
        if (entryClbss == KeyStore.PrivbteKeyEntry.clbss) {
            return engineIsKeyEntry(blibs) &&
                        engineGetCertificbte(blibs) != null;
        }
        if (entryClbss == KeyStore.SecretKeyEntry.clbss) {
            return engineIsKeyEntry(blibs) &&
                        engineGetCertificbte(blibs) == null;
        }
        return fblse;
    }
}
