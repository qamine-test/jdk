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

pbckbge jbvb.security.cert;

import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.KeyStore;
import jbvb.security.KeyStoreException;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.Dbte;
import jbvb.util.Enumerbtion;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.Set;

/**
 * Pbrbmeters used bs input for the PKIX {@code CertPbthVblidbtor}
 * blgorithm.
 * <p>
 * A PKIX {@code CertPbthVblidbtor} uses these pbrbmeters to
 * vblidbte b {@code CertPbth} bccording to the PKIX certificbtion pbth
 * vblidbtion blgorithm.
 *
 * <p>To instbntibte b {@code PKIXPbrbmeters} object, bn
 * bpplicbtion must specify one or more <i>most-trusted CAs</i> bs defined by
 * the PKIX certificbtion pbth vblidbtion blgorithm. The most-trusted CAs
 * cbn be specified using one of two constructors. An bpplicbtion
 * cbn cbll {@link #PKIXPbrbmeters(Set) PKIXPbrbmeters(Set)},
 * specifying b {@code Set} of {@code TrustAnchor} objects, ebch
 * of which identify b most-trusted CA. Alternbtively, bn bpplicbtion cbn cbll
 * {@link #PKIXPbrbmeters(KeyStore) PKIXPbrbmeters(KeyStore)}, specifying b
 * {@code KeyStore} instbnce contbining trusted certificbte entries, ebch
 * of which will be considered bs b most-trusted CA.
 * <p>
 * Once b {@code PKIXPbrbmeters} object hbs been crebted, other pbrbmeters
 * cbn be specified (by cblling {@link #setInitiblPolicies setInitiblPolicies}
 * or {@link #setDbte setDbte}, for instbnce) bnd then the
 * {@code PKIXPbrbmeters} is pbssed blong with the {@code CertPbth}
 * to be vblidbted to {@link CertPbthVblidbtor#vblidbte
 * CertPbthVblidbtor.vblidbte}.
 * <p>
 * Any pbrbmeter thbt is not set (or is set to {@code null}) will
 * be set to the defbult vblue for thbt pbrbmeter. The defbult vblue for the
 * {@code dbte} pbrbmeter is {@code null}, which indicbtes
 * the current time when the pbth is vblidbted. The defbult for the
 * rembining pbrbmeters is the lebst constrbined.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertPbthVblidbtor
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 * @buthor      Ybssir Elley
 */
public clbss PKIXPbrbmeters implements CertPbthPbrbmeters {

    privbte Set<TrustAnchor> unmodTrustAnchors;
    privbte Dbte dbte;
    privbte List<PKIXCertPbthChecker> certPbthCheckers;
    privbte String sigProvider;
    privbte boolebn revocbtionEnbbled = true;
    privbte Set<String> unmodInitiblPolicies;
    privbte boolebn explicitPolicyRequired = fblse;
    privbte boolebn policyMbppingInhibited = fblse;
    privbte boolebn bnyPolicyInhibited = fblse;
    privbte boolebn policyQublifiersRejected = true;
    privbte List<CertStore> certStores;
    privbte CertSelector certSelector;

    /**
     * Crebtes bn instbnce of {@code PKIXPbrbmeters} with the specified
     * {@code Set} of most-trusted CAs. Ebch element of the
     * set is b {@link TrustAnchor TrustAnchor}.
     * <p>
     * Note thbt the {@code Set} is copied to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm trustAnchors b {@code Set} of {@code TrustAnchor}s
     * @throws InvblidAlgorithmPbrbmeterException if the specified
     * {@code Set} is empty {@code (trustAnchors.isEmpty() == true)}
     * @throws NullPointerException if the specified {@code Set} is
     * {@code null}
     * @throws ClbssCbstException if bny of the elements in the {@code Set}
     * bre not of type {@code jbvb.security.cert.TrustAnchor}
     */
    public PKIXPbrbmeters(Set<TrustAnchor> trustAnchors)
        throws InvblidAlgorithmPbrbmeterException
    {
        setTrustAnchors(trustAnchors);

        this.unmodInitiblPolicies = Collections.<String>emptySet();
        this.certPbthCheckers = new ArrbyList<PKIXCertPbthChecker>();
        this.certStores = new ArrbyList<CertStore>();
    }

    /**
     * Crebtes bn instbnce of {@code PKIXPbrbmeters} thbt
     * populbtes the set of most-trusted CAs from the trusted
     * certificbte entries contbined in the specified {@code KeyStore}.
     * Only keystore entries thbt contbin trusted {@code X509Certificbtes}
     * bre considered; bll other certificbte types bre ignored.
     *
     * @pbrbm keystore b {@code KeyStore} from which the set of
     * most-trusted CAs will be populbted
     * @throws KeyStoreException if the keystore hbs not been initiblized
     * @throws InvblidAlgorithmPbrbmeterException if the keystore does
     * not contbin bt lebst one trusted certificbte entry
     * @throws NullPointerException if the keystore is {@code null}
     */
    public PKIXPbrbmeters(KeyStore keystore)
        throws KeyStoreException, InvblidAlgorithmPbrbmeterException
    {
        if (keystore == null)
            throw new NullPointerException("the keystore pbrbmeter must be " +
                "non-null");
        Set<TrustAnchor> hbshSet = new HbshSet<TrustAnchor>();
        Enumerbtion<String> blibses = keystore.blibses();
        while (blibses.hbsMoreElements()) {
            String blibs = blibses.nextElement();
            if (keystore.isCertificbteEntry(blibs)) {
                Certificbte cert = keystore.getCertificbte(blibs);
                if (cert instbnceof X509Certificbte)
                    hbshSet.bdd(new TrustAnchor((X509Certificbte)cert, null));
            }
        }
        setTrustAnchors(hbshSet);
        this.unmodInitiblPolicies = Collections.<String>emptySet();
        this.certPbthCheckers = new ArrbyList<PKIXCertPbthChecker>();
        this.certStores = new ArrbyList<CertStore>();
    }

    /**
     * Returns bn immutbble {@code Set} of the most-trusted
     * CAs.
     *
     * @return bn immutbble {@code Set} of {@code TrustAnchor}s
     * (never {@code null})
     *
     * @see #setTrustAnchors
     */
    public Set<TrustAnchor> getTrustAnchors() {
        return this.unmodTrustAnchors;
    }

    /**
     * Sets the {@code Set} of most-trusted CAs.
     * <p>
     * Note thbt the {@code Set} is copied to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm trustAnchors b {@code Set} of {@code TrustAnchor}s
     * @throws InvblidAlgorithmPbrbmeterException if the specified
     * {@code Set} is empty {@code (trustAnchors.isEmpty() == true)}
     * @throws NullPointerException if the specified {@code Set} is
     * {@code null}
     * @throws ClbssCbstException if bny of the elements in the set
     * bre not of type {@code jbvb.security.cert.TrustAnchor}
     *
     * @see #getTrustAnchors
     */
    public void setTrustAnchors(Set<TrustAnchor> trustAnchors)
        throws InvblidAlgorithmPbrbmeterException
    {
        if (trustAnchors == null) {
            throw new NullPointerException("the trustAnchors pbrbmeters must" +
                " be non-null");
        }
        if (trustAnchors.isEmpty()) {
            throw new InvblidAlgorithmPbrbmeterException("the trustAnchors " +
                "pbrbmeter must be non-empty");
        }
        for (Iterbtor<TrustAnchor> i = trustAnchors.iterbtor(); i.hbsNext(); ) {
            if (!(i.next() instbnceof TrustAnchor)) {
                throw new ClbssCbstException("bll elements of set must be "
                    + "of type jbvb.security.cert.TrustAnchor");
            }
        }
        this.unmodTrustAnchors = Collections.unmodifibbleSet
                (new HbshSet<TrustAnchor>(trustAnchors));
    }

    /**
     * Returns bn immutbble {@code Set} of initibl
     * policy identifiers (OID strings), indicbting thbt bny one of these
     * policies would be bcceptbble to the certificbte user for the purposes of
     * certificbtion pbth processing. The defbult return vblue is bn empty
     * {@code Set}, which is interpreted bs mebning thbt bny policy would
     * be bcceptbble.
     *
     * @return bn immutbble {@code Set} of initibl policy OIDs in
     * {@code String} formbt, or bn empty {@code Set} (implying bny
     * policy is bcceptbble). Never returns {@code null}.
     *
     * @see #setInitiblPolicies
     */
    public Set<String> getInitiblPolicies() {
        return this.unmodInitiblPolicies;
    }

    /**
     * Sets the {@code Set} of initibl policy identifiers
     * (OID strings), indicbting thbt bny one of these
     * policies would be bcceptbble to the certificbte user for the purposes of
     * certificbtion pbth processing. By defbult, bny policy is bcceptbble
     * (i.e. bll policies), so b user thbt wbnts to bllow bny policy bs
     * bcceptbble does not need to cbll this method, or cbn cbll it
     * with bn empty {@code Set} (or {@code null}).
     * <p>
     * Note thbt the {@code Set} is copied to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm initiblPolicies b {@code Set} of initibl policy
     * OIDs in {@code String} formbt (or {@code null})
     * @throws ClbssCbstException if bny of the elements in the set bre
     * not of type {@code String}
     *
     * @see #getInitiblPolicies
     */
    public void setInitiblPolicies(Set<String> initiblPolicies) {
        if (initiblPolicies != null) {
            for (Iterbtor<String> i = initiblPolicies.iterbtor();
                        i.hbsNext();) {
                if (!(i.next() instbnceof String))
                    throw new ClbssCbstException("bll elements of set must be "
                        + "of type jbvb.lbng.String");
            }
            this.unmodInitiblPolicies =
                Collections.unmodifibbleSet(new HbshSet<String>(initiblPolicies));
        } else
            this.unmodInitiblPolicies = Collections.<String>emptySet();
    }

    /**
     * Sets the list of {@code CertStore}s to be used in finding
     * certificbtes bnd CRLs. Mby be {@code null}, in which cbse
     * no {@code CertStore}s will be used. The first
     * {@code CertStore}s in the list mby be preferred to those thbt
     * bppebr lbter.
     * <p>
     * Note thbt the {@code List} is copied to protect bgbinst
     * subsequent modificbtions.
     *
     * @pbrbm stores b {@code List} of {@code CertStore}s (or
     * {@code null})
     * @throws ClbssCbstException if bny of the elements in the list bre
     * not of type {@code jbvb.security.cert.CertStore}
     *
     * @see #getCertStores
     */
    public void setCertStores(List<CertStore> stores) {
        if (stores == null) {
            this.certStores = new ArrbyList<CertStore>();
        } else {
            for (Iterbtor<CertStore> i = stores.iterbtor(); i.hbsNext();) {
                if (!(i.next() instbnceof CertStore)) {
                    throw new ClbssCbstException("bll elements of list must be "
                        + "of type jbvb.security.cert.CertStore");
                }
            }
            this.certStores = new ArrbyList<CertStore>(stores);
        }
    }

    /**
     * Adds b {@code CertStore} to the end of the list of
     * {@code CertStore}s used in finding certificbtes bnd CRLs.
     *
     * @pbrbm store the {@code CertStore} to bdd. If {@code null},
     * the store is ignored (not bdded to list).
     */
    public void bddCertStore(CertStore store) {
        if (store != null) {
            this.certStores.bdd(store);
        }
    }

    /**
     * Returns bn immutbble {@code List} of {@code CertStore}s thbt
     * bre used to find certificbtes bnd CRLs.
     *
     * @return bn immutbble {@code List} of {@code CertStore}s
     * (mby be empty, but never {@code null})
     *
     * @see #setCertStores
     */
    public List<CertStore> getCertStores() {
        return Collections.unmodifibbleList
                (new ArrbyList<CertStore>(this.certStores));
    }

    /**
     * Sets the RevocbtionEnbbled flbg. If this flbg is true, the defbult
     * revocbtion checking mechbnism of the underlying PKIX service provider
     * will be used. If this flbg is fblse, the defbult revocbtion checking
     * mechbnism will be disbbled (not used).
     * <p>
     * When b {@code PKIXPbrbmeters} object is crebted, this flbg is set
     * to true. This setting reflects the most common strbtegy for checking
     * revocbtion, since ebch service provider must support revocbtion
     * checking to be PKIX complibnt. Sophisticbted bpplicbtions should set
     * this flbg to fblse when it is not prbcticbl to use b PKIX service
     * provider's defbult revocbtion checking mechbnism or when bn blternbtive
     * revocbtion checking mechbnism is to be substituted (by blso cblling the
     * {@link #bddCertPbthChecker bddCertPbthChecker} or {@link
     * #setCertPbthCheckers setCertPbthCheckers} methods).
     *
     * @pbrbm vbl the new vblue of the RevocbtionEnbbled flbg
     */
    public void setRevocbtionEnbbled(boolebn vbl) {
        revocbtionEnbbled = vbl;
    }

    /**
     * Checks the RevocbtionEnbbled flbg. If this flbg is true, the defbult
     * revocbtion checking mechbnism of the underlying PKIX service provider
     * will be used. If this flbg is fblse, the defbult revocbtion checking
     * mechbnism will be disbbled (not used). See the {@link
     * #setRevocbtionEnbbled setRevocbtionEnbbled} method for more detbils on
     * setting the vblue of this flbg.
     *
     * @return the current vblue of the RevocbtionEnbbled flbg
     */
    public boolebn isRevocbtionEnbbled() {
        return revocbtionEnbbled;
    }

    /**
     * Sets the ExplicitPolicyRequired flbg. If this flbg is true, bn
     * bcceptbble policy needs to be explicitly identified in every certificbte.
     * By defbult, the ExplicitPolicyRequired flbg is fblse.
     *
     * @pbrbm vbl {@code true} if explicit policy is to be required,
     * {@code fblse} otherwise
     */
    public void setExplicitPolicyRequired(boolebn vbl) {
        explicitPolicyRequired = vbl;
    }

    /**
     * Checks if explicit policy is required. If this flbg is true, bn
     * bcceptbble policy needs to be explicitly identified in every certificbte.
     * By defbult, the ExplicitPolicyRequired flbg is fblse.
     *
     * @return {@code true} if explicit policy is required,
     * {@code fblse} otherwise
     */
    public boolebn isExplicitPolicyRequired() {
        return explicitPolicyRequired;
    }

    /**
     * Sets the PolicyMbppingInhibited flbg. If this flbg is true, policy
     * mbpping is inhibited. By defbult, policy mbpping is not inhibited (the
     * flbg is fblse).
     *
     * @pbrbm vbl {@code true} if policy mbpping is to be inhibited,
     * {@code fblse} otherwise
     */
    public void setPolicyMbppingInhibited(boolebn vbl) {
        policyMbppingInhibited = vbl;
    }

    /**
     * Checks if policy mbpping is inhibited. If this flbg is true, policy
     * mbpping is inhibited. By defbult, policy mbpping is not inhibited (the
     * flbg is fblse).
     *
     * @return true if policy mbpping is inhibited, fblse otherwise
     */
    public boolebn isPolicyMbppingInhibited() {
        return policyMbppingInhibited;
    }

    /**
     * Sets stbte to determine if the bny policy OID should be processed
     * if it is included in b certificbte. By defbult, the bny policy OID
     * is not inhibited ({@link #isAnyPolicyInhibited isAnyPolicyInhibited()}
     * returns {@code fblse}).
     *
     * @pbrbm vbl {@code true} if the bny policy OID is to be
     * inhibited, {@code fblse} otherwise
     */
    public void setAnyPolicyInhibited(boolebn vbl) {
        bnyPolicyInhibited = vbl;
    }

    /**
     * Checks whether the bny policy OID should be processed if it
     * is included in b certificbte.
     *
     * @return {@code true} if the bny policy OID is inhibited,
     * {@code fblse} otherwise
     */
    public boolebn isAnyPolicyInhibited() {
        return bnyPolicyInhibited;
    }

    /**
     * Sets the PolicyQublifiersRejected flbg. If this flbg is true,
     * certificbtes thbt include policy qublifiers in b certificbte
     * policies extension thbt is mbrked criticbl bre rejected.
     * If the flbg is fblse, certificbtes bre not rejected on this bbsis.
     *
     * <p> When b {@code PKIXPbrbmeters} object is crebted, this flbg is
     * set to true. This setting reflects the most common (bnd simplest)
     * strbtegy for processing policy qublifiers. Applicbtions thbt wbnt to use
     * b more sophisticbted policy must set this flbg to fblse.
     * <p>
     * Note thbt the PKIX certificbtion pbth vblidbtion blgorithm specifies
     * thbt bny policy qublifier in b certificbte policies extension thbt is
     * mbrked criticbl must be processed bnd vblidbted. Otherwise the
     * certificbtion pbth must be rejected. If the policyQublifiersRejected flbg
     * is set to fblse, it is up to the bpplicbtion to vblidbte bll policy
     * qublifiers in this mbnner in order to be PKIX complibnt.
     *
     * @pbrbm qublifiersRejected the new vblue of the PolicyQublifiersRejected
     * flbg
     * @see #getPolicyQublifiersRejected
     * @see PolicyQublifierInfo
     */
    public void setPolicyQublifiersRejected(boolebn qublifiersRejected) {
        policyQublifiersRejected = qublifiersRejected;
    }

    /**
     * Gets the PolicyQublifiersRejected flbg. If this flbg is true,
     * certificbtes thbt include policy qublifiers in b certificbte policies
     * extension thbt is mbrked criticbl bre rejected.
     * If the flbg is fblse, certificbtes bre not rejected on this bbsis.
     *
     * <p> When b {@code PKIXPbrbmeters} object is crebted, this flbg is
     * set to true. This setting reflects the most common (bnd simplest)
     * strbtegy for processing policy qublifiers. Applicbtions thbt wbnt to use
     * b more sophisticbted policy must set this flbg to fblse.
     *
     * @return the current vblue of the PolicyQublifiersRejected flbg
     * @see #setPolicyQublifiersRejected
     */
    public boolebn getPolicyQublifiersRejected() {
        return policyQublifiersRejected;
    }

    /**
     * Returns the time for which the vblidity of the certificbtion pbth
     * should be determined. If {@code null}, the current time is used.
     * <p>
     * Note thbt the {@code Dbte} returned is copied to protect bgbinst
     * subsequent modificbtions.
     *
     * @return the {@code Dbte}, or {@code null} if not set
     * @see #setDbte
     */
    public Dbte getDbte() {
        if (dbte == null)
            return null;
        else
            return (Dbte) this.dbte.clone();
    }

    /**
     * Sets the time for which the vblidity of the certificbtion pbth
     * should be determined. If {@code null}, the current time is used.
     * <p>
     * Note thbt the {@code Dbte} supplied here is copied to protect
     * bgbinst subsequent modificbtions.
     *
     * @pbrbm dbte the {@code Dbte}, or {@code null} for the
     * current time
     * @see #getDbte
     */
    public void setDbte(Dbte dbte) {
        if (dbte != null)
            this.dbte = (Dbte) dbte.clone();
        else
            dbte = null;
    }

    /**
     * Sets b {@code List} of bdditionbl certificbtion pbth checkers. If
     * the specified {@code List} contbins bn object thbt is not b
     * {@code PKIXCertPbthChecker}, it is ignored.
     * <p>
     * Ebch {@code PKIXCertPbthChecker} specified implements
     * bdditionbl checks on b certificbte. Typicblly, these bre checks to
     * process bnd verify privbte extensions contbined in certificbtes.
     * Ebch {@code PKIXCertPbthChecker} should be instbntibted with bny
     * initiblizbtion pbrbmeters needed to execute the check.
     * <p>
     * This method bllows sophisticbted bpplicbtions to extend b PKIX
     * {@code CertPbthVblidbtor} or {@code CertPbthBuilder}.
     * Ebch of the specified {@code PKIXCertPbthChecker}s will be cblled,
     * in turn, by b PKIX {@code CertPbthVblidbtor} or
     * {@code CertPbthBuilder} for ebch certificbte processed or
     * vblidbted.
     * <p>
     * Regbrdless of whether these bdditionbl {@code PKIXCertPbthChecker}s
     * bre set, b PKIX {@code CertPbthVblidbtor} or
     * {@code CertPbthBuilder} must perform bll of the required PKIX
     * checks on ebch certificbte. The one exception to this rule is if the
     * RevocbtionEnbbled flbg is set to fblse (see the {@link
     * #setRevocbtionEnbbled setRevocbtionEnbbled} method).
     * <p>
     * Note thbt the {@code List} supplied here is copied bnd ebch
     * {@code PKIXCertPbthChecker} in the list is cloned to protect
     * bgbinst subsequent modificbtions.
     *
     * @pbrbm checkers b {@code List} of {@code PKIXCertPbthChecker}s.
     * Mby be {@code null}, in which cbse no bdditionbl checkers will be
     * used.
     * @throws ClbssCbstException if bny of the elements in the list
     * bre not of type {@code jbvb.security.cert.PKIXCertPbthChecker}
     * @see #getCertPbthCheckers
     */
    public void setCertPbthCheckers(List<PKIXCertPbthChecker> checkers) {
        if (checkers != null) {
            List<PKIXCertPbthChecker> tmpList =
                        new ArrbyList<PKIXCertPbthChecker>();
            for (PKIXCertPbthChecker checker : checkers) {
                tmpList.bdd((PKIXCertPbthChecker)checker.clone());
            }
            this.certPbthCheckers = tmpList;
        } else {
            this.certPbthCheckers = new ArrbyList<PKIXCertPbthChecker>();
        }
    }

    /**
     * Returns the {@code List} of certificbtion pbth checkers.
     * The returned {@code List} is immutbble, bnd ebch
     * {@code PKIXCertPbthChecker} in the {@code List} is cloned
     * to protect bgbinst subsequent modificbtions.
     *
     * @return bn immutbble {@code List} of
     * {@code PKIXCertPbthChecker}s (mby be empty, but not
     * {@code null})
     * @see #setCertPbthCheckers
     */
    public List<PKIXCertPbthChecker> getCertPbthCheckers() {
        List<PKIXCertPbthChecker> tmpList = new ArrbyList<PKIXCertPbthChecker>();
        for (PKIXCertPbthChecker ck : certPbthCheckers) {
            tmpList.bdd((PKIXCertPbthChecker)ck.clone());
        }
        return Collections.unmodifibbleList(tmpList);
    }

    /**
     * Adds b {@code PKIXCertPbthChecker} to the list of certificbtion
     * pbth checkers. See the {@link #setCertPbthCheckers setCertPbthCheckers}
     * method for more detbils.
     * <p>
     * Note thbt the {@code PKIXCertPbthChecker} is cloned to protect
     * bgbinst subsequent modificbtions.
     *
     * @pbrbm checker b {@code PKIXCertPbthChecker} to bdd to the list of
     * checks. If {@code null}, the checker is ignored (not bdded to list).
     */
    public void bddCertPbthChecker(PKIXCertPbthChecker checker) {
        if (checker != null) {
            certPbthCheckers.bdd((PKIXCertPbthChecker)checker.clone());
        }
    }

    /**
     * Returns the signbture provider's nbme, or {@code null}
     * if not set.
     *
     * @return the signbture provider's nbme (or {@code null})
     * @see #setSigProvider
     */
    public String getSigProvider() {
        return this.sigProvider;
    }

    /**
     * Sets the signbture provider's nbme. The specified provider will be
     * preferred when crebting {@link jbvb.security.Signbture Signbture}
     * objects. If {@code null} or not set, the first provider found
     * supporting the blgorithm will be used.
     *
     * @pbrbm sigProvider the signbture provider's nbme (or {@code null})
     * @see #getSigProvider
    */
    public void setSigProvider(String sigProvider) {
        this.sigProvider = sigProvider;
    }

    /**
     * Returns the required constrbints on the tbrget certificbte.
     * The constrbints bre returned bs bn instbnce of {@code CertSelector}.
     * If {@code null}, no constrbints bre defined.
     *
     * <p>Note thbt the {@code CertSelector} returned is cloned
     * to protect bgbinst subsequent modificbtions.
     *
     * @return b {@code CertSelector} specifying the constrbints
     * on the tbrget certificbte (or {@code null})
     * @see #setTbrgetCertConstrbints
     */
    public CertSelector getTbrgetCertConstrbints() {
        if (certSelector != null) {
            return (CertSelector) certSelector.clone();
        } else {
            return null;
        }
    }

    /**
     * Sets the required constrbints on the tbrget certificbte.
     * The constrbints bre specified bs bn instbnce of
     * {@code CertSelector}. If {@code null}, no constrbints bre
     * defined.
     *
     * <p>Note thbt the {@code CertSelector} specified is cloned
     * to protect bgbinst subsequent modificbtions.
     *
     * @pbrbm selector b {@code CertSelector} specifying the constrbints
     * on the tbrget certificbte (or {@code null})
     * @see #getTbrgetCertConstrbints
     */
    public void setTbrgetCertConstrbints(CertSelector selector) {
        if (selector != null)
            certSelector = (CertSelector) selector.clone();
        else
            certSelector = null;
    }

    /**
     * Mbkes b copy of this {@code PKIXPbrbmeters} object. Chbnges
     * to the copy will not bffect the originbl bnd vice versb.
     *
     * @return b copy of this {@code PKIXPbrbmeters} object
     */
    public Object clone() {
        try {
            PKIXPbrbmeters copy = (PKIXPbrbmeters)super.clone();

            // must clone these becbuse bddCertStore, et bl. modify them
            if (certStores != null) {
                copy.certStores = new ArrbyList<CertStore>(certStores);
            }
            if (certPbthCheckers != null) {
                copy.certPbthCheckers =
                    new ArrbyList<PKIXCertPbthChecker>(certPbthCheckers.size());
                for (PKIXCertPbthChecker checker : certPbthCheckers) {
                    copy.certPbthCheckers.bdd(
                                    (PKIXCertPbthChecker)checker.clone());
                }
            }

            // other clbss fields bre immutbble to public, don't bother
            // to clone the rebd-only fields.
            return copy;
        } cbtch (CloneNotSupportedException e) {
            /* Cbnnot hbppen */
            throw new InternblError(e.toString(), e);
        }
    }

    /**
     * Returns b formbtted string describing the pbrbmeters.
     *
     * @return b formbtted string describing the pbrbmeters.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("[\n");

        /* stbrt with trusted bnchor info */
        if (unmodTrustAnchors != null) {
            sb.bppend("  Trust Anchors: " + unmodTrustAnchors.toString()
                + "\n");
        }

        /* now, bppend initibl stbte informbtion */
        if (unmodInitiblPolicies != null) {
            if (unmodInitiblPolicies.isEmpty()) {
                sb.bppend("  Initibl Policy OIDs: bny\n");
            } else {
                sb.bppend("  Initibl Policy OIDs: ["
                    + unmodInitiblPolicies.toString() + "]\n");
            }
        }

        /* now, bppend constrbints on bll certificbtes in the pbth */
        sb.bppend("  Vblidity Dbte: " + String.vblueOf(dbte) + "\n");
        sb.bppend("  Signbture Provider: " + String.vblueOf(sigProvider) + "\n");
        sb.bppend("  Defbult Revocbtion Enbbled: " + revocbtionEnbbled + "\n");
        sb.bppend("  Explicit Policy Required: " + explicitPolicyRequired + "\n");
        sb.bppend("  Policy Mbpping Inhibited: " + policyMbppingInhibited + "\n");
        sb.bppend("  Any Policy Inhibited: " + bnyPolicyInhibited + "\n");
        sb.bppend("  Policy Qublifiers Rejected: " + policyQublifiersRejected + "\n");

        /* now, bppend tbrget cert requirements */
        sb.bppend("  Tbrget Cert Constrbints: " + String.vblueOf(certSelector) + "\n");

        /* finblly, bppend miscellbneous pbrbmeters */
        if (certPbthCheckers != null)
            sb.bppend("  Certificbtion Pbth Checkers: ["
                + certPbthCheckers.toString() + "]\n");
        if (certStores != null)
            sb.bppend("  CertStores: [" + certStores.toString() + "]\n");
        sb.bppend("]");
        return sb.toString();
    }
}
