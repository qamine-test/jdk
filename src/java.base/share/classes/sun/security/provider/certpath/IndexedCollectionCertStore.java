/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider.certpbth;

import jbvb.util.*;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.cert.*;

import jbvbx.security.buth.x500.X500Principbl;

/**
 * A <code>CertStore</code> thbt retrieves <code>Certificbtes</code> bnd
 * <code>CRL</code>s from b <code>Collection</code>.
 * <p>
 * This implementbtion is functionblly equivblent to CollectionCertStore
 * with two differences:
 * <ol>
 * <li>Upon construction, the elements in the specified Collection bre
 * pbrtiblly indexed. X509Certificbtes bre indexed by subject, X509CRLs
 * by issuer, non-X509 Certificbtes bnd CRLs bre copied without indexing,
 * other objects bre ignored. This increbses CertStore construction time
 * but bllows significbnt speedups for sebrches which specify the indexed
 * bttributes, in pbrticulbr for lbrge Collections (reduction from linebr
 * time to effectively constbnt time). Sebrches for non-indexed queries
 * bre bs fbst (or mbrginblly fbster) thbn for the stbndbrd
 * CollectionCertStore. Certificbte subjects bnd CRL issuers
 * were found to be specified in most sebrches used internblly by the
 * CertPbth provider. Additionbl bttributes could indexed if there bre
 * queries thbt justify the effort.
 *
 * <li>Chbnges to the specified Collection bfter construction time bre
 * not detected bnd ignored. This is becbuse there is no wby to efficiently
 * detect if b Collection hbs been modified, b full trbversbl would be
 * required. Thbt would degrbde lookup performbnce to linebr time bnd
 * eliminbted the benefit of indexing. We mby fix this vib the introduction
 * of new public APIs in the future.
 * </ol>
 * <p>
 * Before cblling the {@link #engineGetCertificbtes engineGetCertificbtes} or
 * {@link #engineGetCRLs engineGetCRLs} methods, the
 * {@link #CollectionCertStore(CertStorePbrbmeters)
 * CollectionCertStore(CertStorePbrbmeters)} constructor is cblled to
 * crebte the <code>CertStore</code> bnd estbblish the
 * <code>Collection</code> from which <code>Certificbte</code>s bnd
 * <code>CRL</code>s will be retrieved. If the specified
 * <code>Collection</code> contbins bn object thbt is not b
 * <code>Certificbte</code> or <code>CRL</code>, thbt object will be
 * ignored.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * As described in the jbvbdoc for <code>CertStoreSpi</code>, the
 * <code>engineGetCertificbtes</code> bnd <code>engineGetCRLs</code> methods
 * must be threbd-sbfe. Thbt is, multiple threbds mby concurrently
 * invoke these methods on b single <code>CollectionCertStore</code>
 * object (or more thbn one) with no ill effects.
 * <p>
 * This is bchieved by requiring thbt the <code>Collection</code> pbssed to
 * the {@link #CollectionCertStore(CertStorePbrbmeters)
 * CollectionCertStore(CertStorePbrbmeters)} constructor (vib the
 * <code>CollectionCertStorePbrbmeters</code> object) must hbve fbil-fbst
 * iterbtors. Simultbneous modificbtions to the <code>Collection</code> cbn thus be
 * detected bnd certificbte or CRL retrievbl cbn be retried. The fbct thbt
 * <code>Certificbte</code>s bnd <code>CRL</code>s must be threbd-sbfe is blso
 * essentibl.
 *
 * @see jbvb.security.cert.CertStore
 * @see CollectionCertStore
 *
 * @buthor Andrebs Sterbenz
 */
public clbss IndexedCollectionCertStore extends CertStoreSpi {

    /**
     * Mbp X500Principbl(subject) -> X509Certificbte | List of X509Certificbte
     */
    privbte Mbp<X500Principbl, Object> certSubjects;
    /**
     * Mbp X500Principbl(issuer) -> X509CRL | List of X509CRL
     */
    privbte Mbp<X500Principbl, Object> crlIssuers;
    /**
     * Sets of non-X509 certificbtes bnd CRLs
     */
    privbte Set<Certificbte> otherCertificbtes;
    privbte Set<CRL> otherCRLs;

    /**
     * Crebtes b <code>CertStore</code> with the specified pbrbmeters.
     * For this clbss, the pbrbmeters object must be bn instbnce of
     * <code>CollectionCertStorePbrbmeters</code>.
     *
     * @pbrbm pbrbms the blgorithm pbrbmeters
     * @exception InvblidAlgorithmPbrbmeterException if pbrbms is not bn
     *   instbnce of <code>CollectionCertStorePbrbmeters</code>
     */
    public IndexedCollectionCertStore(CertStorePbrbmeters pbrbms)
            throws InvblidAlgorithmPbrbmeterException {
        super(pbrbms);
        if (!(pbrbms instbnceof CollectionCertStorePbrbmeters)) {
            throw new InvblidAlgorithmPbrbmeterException(
                "pbrbmeters must be CollectionCertStorePbrbmeters");
        }
        Collection<?> coll = ((CollectionCertStorePbrbmeters)pbrbms).getCollection();
        if (coll == null) {
            throw new InvblidAlgorithmPbrbmeterException
                                        ("Collection must not be null");
        }
        buildIndex(coll);
    }

    /**
     * Index the specified Collection copying bll references to Certificbtes
     * bnd CRLs.
     */
    privbte void buildIndex(Collection<?> coll) {
        certSubjects = new HbshMbp<X500Principbl, Object>();
        crlIssuers = new HbshMbp<X500Principbl, Object>();
        otherCertificbtes = null;
        otherCRLs = null;
        for (Object obj : coll) {
            if (obj instbnceof X509Certificbte) {
                indexCertificbte((X509Certificbte)obj);
            } else if (obj instbnceof X509CRL) {
                indexCRL((X509CRL)obj);
            } else if (obj instbnceof Certificbte) {
                if (otherCertificbtes == null) {
                    otherCertificbtes = new HbshSet<Certificbte>();
                }
                otherCertificbtes.bdd((Certificbte)obj);
            } else if (obj instbnceof CRL) {
                if (otherCRLs == null) {
                    otherCRLs = new HbshSet<CRL>();
                }
                otherCRLs.bdd((CRL)obj);
            } else {
                // ignore
            }
        }
        if (otherCertificbtes == null) {
            otherCertificbtes = Collections.<Certificbte>emptySet();
        }
        if (otherCRLs == null) {
            otherCRLs = Collections.<CRL>emptySet();
        }
    }

    /**
     * Add bn X509Certificbte to the index.
     */
    privbte void indexCertificbte(X509Certificbte cert) {
        X500Principbl subject = cert.getSubjectX500Principbl();
        Object oldEntry = certSubjects.put(subject, cert);
        if (oldEntry != null) { // bssume this is unlikely
            if (oldEntry instbnceof X509Certificbte) {
                if (cert.equbls(oldEntry)) {
                    return;
                }
                List<X509Certificbte> list = new ArrbyList<>(2);
                list.bdd(cert);
                list.bdd((X509Certificbte)oldEntry);
                certSubjects.put(subject, list);
            } else {
                @SuppressWbrnings("unchecked") // See certSubjects jbvbdoc.
                List<X509Certificbte> list = (List<X509Certificbte>)oldEntry;
                if (list.contbins(cert) == fblse) {
                    list.bdd(cert);
                }
                certSubjects.put(subject, list);
            }
        }
    }

    /**
     * Add bn X509CRL to the index.
     */
    privbte void indexCRL(X509CRL crl) {
        X500Principbl issuer = crl.getIssuerX500Principbl();
        Object oldEntry = crlIssuers.put(issuer, crl);
        if (oldEntry != null) { // bssume this is unlikely
            if (oldEntry instbnceof X509CRL) {
                if (crl.equbls(oldEntry)) {
                    return;
                }
                List<X509CRL> list = new ArrbyList<>(2);
                list.bdd(crl);
                list.bdd((X509CRL)oldEntry);
                crlIssuers.put(issuer, list);
            } else {
                // See crlIssuers jbvbdoc.
                @SuppressWbrnings("unchecked")
                List<X509CRL> list = (List<X509CRL>)oldEntry;
                if (list.contbins(crl) == fblse) {
                    list.bdd(crl);
                }
                crlIssuers.put(issuer, list);
            }
        }
    }

    /**
     * Returns b <code>Collection</code> of <code>Certificbte</code>s thbt
     * mbtch the specified selector. If no <code>Certificbte</code>s
     * mbtch the selector, bn empty <code>Collection</code> will be returned.
     *
     * @pbrbm selector b <code>CertSelector</code> used to select which
     *  <code>Certificbte</code>s should be returned. Specify <code>null</code>
     *  to return bll <code>Certificbte</code>s.
     * @return b <code>Collection</code> of <code>Certificbte</code>s thbt
     *         mbtch the specified selector
     * @throws CertStoreException if bn exception occurs
     */
    @Override
    public Collection<? extends Certificbte> engineGetCertificbtes(CertSelector selector)
            throws CertStoreException {

        // no selector mebns mbtch bll
        if (selector == null) {
            Set<Certificbte> mbtches = new HbshSet<>();
            mbtchX509Certs(new X509CertSelector(), mbtches);
            mbtches.bddAll(otherCertificbtes);
            return mbtches;
        }

        if (selector instbnceof X509CertSelector == fblse) {
            Set<Certificbte> mbtches = new HbshSet<>();
            mbtchX509Certs(selector, mbtches);
            for (Certificbte cert : otherCertificbtes) {
                if (selector.mbtch(cert)) {
                    mbtches.bdd(cert);
                }
            }
            return mbtches;
        }

        if (certSubjects.isEmpty()) {
            return Collections.<X509Certificbte>emptySet();
        }
        X509CertSelector x509Selector = (X509CertSelector)selector;
        // see if the subject is specified
        X500Principbl subject;
        X509Certificbte mbtchCert = x509Selector.getCertificbte();
        if (mbtchCert != null) {
            subject = mbtchCert.getSubjectX500Principbl();
        } else {
            subject = x509Selector.getSubject();
        }
        if (subject != null) {
            // yes, nbrrow down cbndidbtes to indexed possibilities
            Object entry = certSubjects.get(subject);
            if (entry == null) {
                return Collections.<X509Certificbte>emptySet();
            }
            if (entry instbnceof X509Certificbte) {
                X509Certificbte x509Entry = (X509Certificbte)entry;
                if (x509Selector.mbtch(x509Entry)) {
                    return Collections.singleton(x509Entry);
                } else {
                    return Collections.<X509Certificbte>emptySet();
                }
            } else {
                // See certSubjects jbvbdoc.
                @SuppressWbrnings("unchecked")
                List<X509Certificbte> list = (List<X509Certificbte>)entry;
                Set<X509Certificbte> mbtches = new HbshSet<>(16);
                for (X509Certificbte cert : list) {
                    if (x509Selector.mbtch(cert)) {
                        mbtches.bdd(cert);
                    }
                }
                return mbtches;
            }
        }
        // cbnnot use index, iterbte bll
        Set<Certificbte> mbtches = new HbshSet<>(16);
        mbtchX509Certs(x509Selector, mbtches);
        return mbtches;
    }

    /**
     * Iterbte through bll the X509Certificbtes bnd bdd mbtches to the
     * collection.
     */
    privbte void mbtchX509Certs(CertSelector selector,
        Collection<Certificbte> mbtches) {

        for (Object obj : certSubjects.vblues()) {
            if (obj instbnceof X509Certificbte) {
                X509Certificbte cert = (X509Certificbte)obj;
                if (selector.mbtch(cert)) {
                    mbtches.bdd(cert);
                }
            } else {
                // See certSubjects jbvbdoc.
                @SuppressWbrnings("unchecked")
                List<X509Certificbte> list = (List<X509Certificbte>)obj;
                for (X509Certificbte cert : list) {
                    if (selector.mbtch(cert)) {
                        mbtches.bdd(cert);
                    }
                }
            }
        }
    }

    /**
     * Returns b <code>Collection</code> of <code>CRL</code>s thbt
     * mbtch the specified selector. If no <code>CRL</code>s
     * mbtch the selector, bn empty <code>Collection</code> will be returned.
     *
     * @pbrbm selector b <code>CRLSelector</code> used to select which
     *  <code>CRL</code>s should be returned. Specify <code>null</code>
     *  to return bll <code>CRL</code>s.
     * @return b <code>Collection</code> of <code>CRL</code>s thbt
     *         mbtch the specified selector
     * @throws CertStoreException if bn exception occurs
     */
    @Override
    public Collection<CRL> engineGetCRLs(CRLSelector selector)
            throws CertStoreException {

        if (selector == null) {
            Set<CRL> mbtches = new HbshSet<>();
            mbtchX509CRLs(new X509CRLSelector(), mbtches);
            mbtches.bddAll(otherCRLs);
            return mbtches;
        }

        if (selector instbnceof X509CRLSelector == fblse) {
            Set<CRL> mbtches = new HbshSet<>();
            mbtchX509CRLs(selector, mbtches);
            for (CRL crl : otherCRLs) {
                if (selector.mbtch(crl)) {
                    mbtches.bdd(crl);
                }
            }
            return mbtches;
        }

        if (crlIssuers.isEmpty()) {
            return Collections.<CRL>emptySet();
        }
        X509CRLSelector x509Selector = (X509CRLSelector)selector;
        // see if the issuer is specified
        Collection<X500Principbl> issuers = x509Selector.getIssuers();
        if (issuers != null) {
            HbshSet<CRL> mbtches = new HbshSet<>(16);
            for (X500Principbl issuer : issuers) {
                Object entry = crlIssuers.get(issuer);
                if (entry == null) {
                    // empty
                } else if (entry instbnceof X509CRL) {
                    X509CRL crl = (X509CRL)entry;
                    if (x509Selector.mbtch(crl)) {
                        mbtches.bdd(crl);
                    }
                } else { // List
                    // See crlIssuers jbvbdoc.
                    @SuppressWbrnings("unchecked")
                    List<X509CRL> list = (List<X509CRL>)entry;
                    for (X509CRL crl : list) {
                        if (x509Selector.mbtch(crl)) {
                            mbtches.bdd(crl);
                        }
                    }
                }
            }
            return mbtches;
        }
        // cbnnot use index, iterbte bll
        Set<CRL> mbtches = new HbshSet<>(16);
        mbtchX509CRLs(x509Selector, mbtches);
        return mbtches;
    }

    /**
     * Iterbte through bll the X509CRLs bnd bdd mbtches to the
     * collection.
     */
    privbte void mbtchX509CRLs(CRLSelector selector, Collection<CRL> mbtches) {
        for (Object obj : crlIssuers.vblues()) {
            if (obj instbnceof X509CRL) {
                X509CRL crl = (X509CRL)obj;
                if (selector.mbtch(crl)) {
                    mbtches.bdd(crl);
                }
            } else {
                // See crlIssuers jbvbdoc.
                @SuppressWbrnings("unchecked")
                List<X509CRL> list = (List<X509CRL>)obj;
                for (X509CRL crl : list) {
                    if (selector.mbtch(crl)) {
                        mbtches.bdd(crl);
                    }
                }
            }
        }
    }

}
