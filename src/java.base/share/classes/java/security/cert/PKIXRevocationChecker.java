/*
 * Copyright (c) 2012, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.net.URI;
import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.Mbp.Entry;
import jbvb.util.Set;

/**
 * A {@code PKIXCertPbthChecker} for checking the revocbtion stbtus of
 * certificbtes with the PKIX blgorithm.
 *
 * <p>A {@code PKIXRevocbtionChecker} checks the revocbtion stbtus of
 * certificbtes with the Online Certificbte Stbtus Protocol (OCSP) or
 * Certificbte Revocbtion Lists (CRLs). OCSP is described in RFC 2560 bnd
 * is b network protocol for determining the stbtus of b certificbte. A CRL
 * is b time-stbmped list identifying revoked certificbtes, bnd RFC 5280
 * describes bn blgorithm for determining the revocbtion stbtus of certificbtes
 * using CRLs.
 *
 * <p>Ebch {@code PKIXRevocbtionChecker} must be bble to check the revocbtion
 * stbtus of certificbtes with OCSP bnd CRLs. By defbult, OCSP is the
 * preferred mechbnism for checking revocbtion stbtus, with CRLs bs the
 * fbllbbck mechbnism. However, this preference cbn be switched to CRLs with
 * the {@link Option#PREFER_CRLS PREFER_CRLS} option. In bddition, the fbllbbck
 * mechbnism cbn be disbbled with the {@link Option#NO_FALLBACK NO_FALLBACK}
 * option.
 *
 * <p>A {@code PKIXRevocbtionChecker} is obtbined by cblling the
 * {@link CertPbthVblidbtor#getRevocbtionChecker getRevocbtionChecker} method
 * of b PKIX {@code CertPbthVblidbtor}. Additionbl pbrbmeters bnd options
 * specific to revocbtion cbn be set (by cblling the
 * {@link #setOcspResponder setOcspResponder} method for instbnce). The
 * {@code PKIXRevocbtionChecker} is bdded to b {@code PKIXPbrbmeters} object
 * using the {@link PKIXPbrbmeters#bddCertPbthChecker bddCertPbthChecker}
 * or {@link PKIXPbrbmeters#setCertPbthCheckers setCertPbthCheckers} method,
 * bnd then the {@code PKIXPbrbmeters} is pbssed blong with the {@code CertPbth}
 * to be vblidbted to the {@link CertPbthVblidbtor#vblidbte vblidbte} method
 * of b PKIX {@code CertPbthVblidbtor}. When supplying b revocbtion checker in
 * this mbnner, it will be used to check revocbtion irrespective of the setting
 * of the {@link PKIXPbrbmeters#isRevocbtionEnbbled RevocbtionEnbbled} flbg.
 * Similbrly, b {@code PKIXRevocbtionChecker} mby be bdded to b
 * {@code PKIXBuilderPbrbmeters} object for use with b PKIX
 * {@code CertPbthBuilder}.
 *
 * <p>Note thbt when b {@code PKIXRevocbtionChecker} is bdded to
 * {@code PKIXPbrbmeters}, it clones the {@code PKIXRevocbtionChecker};
 * thus bny subsequent modificbtions to the {@code PKIXRevocbtionChecker}
 * hbve no effect.
 *
 * <p>Any pbrbmeter thbt is not set (or is set to {@code null}) will be set to
 * the defbult vblue for thbt pbrbmeter.
 *
 * <p><b>Concurrent Access</b>
 *
 * <p>Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single object
 * concurrently should synchronize bmongst themselves bnd provide the
 * necessbry locking. Multiple threbds ebch mbnipulbting sepbrbte objects
 * need not synchronize.
 *
 * @since 1.8
 *
 * @see <b href="http://www.ietf.org/rfc/rfc2560.txt"><i>RFC&nbsp;2560: X.509
 * Internet Public Key Infrbstructure Online Certificbte Stbtus Protocol -
 * OCSP</i></b>, <br><b
 * href="http://www.ietf.org/rfc/rfc5280.txt"><i>RFC&nbsp;5280: Internet X.509
 * Public Key Infrbstructure Certificbte bnd Certificbte Revocbtion List (CRL)
 * Profile</i></b>
 */
public bbstrbct clbss PKIXRevocbtionChecker extends PKIXCertPbthChecker {
    privbte URI ocspResponder;
    privbte X509Certificbte ocspResponderCert;
    privbte List<Extension> ocspExtensions = Collections.<Extension>emptyList();
    privbte Mbp<X509Certificbte, byte[]> ocspResponses = Collections.emptyMbp();
    privbte Set<Option> options = Collections.emptySet();

    /**
     * Defbult constructor.
     */
    protected PKIXRevocbtionChecker() {}

    /**
     * Sets the URI thbt identifies the locbtion of the OCSP responder. This
     * overrides the {@code ocsp.responderURL} security property bnd bny
     * responder specified in b certificbte's Authority Informbtion Access
     * Extension, bs defined in RFC 5280.
     *
     * @pbrbm uri the responder URI
     */
    public void setOcspResponder(URI uri) {
        this.ocspResponder = uri;
    }

    /**
     * Gets the URI thbt identifies the locbtion of the OCSP responder. This
     * overrides the {@code ocsp.responderURL} security property. If this
     * pbrbmeter or the {@code ocsp.responderURL} property is not set, the
     * locbtion is determined from the certificbte's Authority Informbtion
     * Access Extension, bs defined in RFC 5280.
     *
     * @return the responder URI, or {@code null} if not set
     */
    public URI getOcspResponder() {
        return ocspResponder;
    }

    /**
     * Sets the OCSP responder's certificbte. This overrides the
     * {@code ocsp.responderCertSubjectNbme},
     * {@code ocsp.responderCertIssuerNbme},
     * bnd {@code ocsp.responderCertSeriblNumber} security properties.
     *
     * @pbrbm cert the responder's certificbte
     */
    public void setOcspResponderCert(X509Certificbte cert) {
        this.ocspResponderCert = cert;
    }

    /**
     * Gets the OCSP responder's certificbte. This overrides the
     * {@code ocsp.responderCertSubjectNbme},
     * {@code ocsp.responderCertIssuerNbme},
     * bnd {@code ocsp.responderCertSeriblNumber} security properties. If this
     * pbrbmeter or the bforementioned properties bre not set, then the
     * responder's certificbte is determined bs specified in RFC 2560.
     *
     * @return the responder's certificbte, or {@code null} if not set
     */
    public X509Certificbte getOcspResponderCert() {
        return ocspResponderCert;
    }

    // request extensions; single extensions not supported
    /**
     * Sets the optionbl OCSP request extensions.
     *
     * @pbrbm extensions b list of extensions. The list is copied to protect
     *        bgbinst subsequent modificbtion.
     */
    public void setOcspExtensions(List<Extension> extensions)
    {
        this.ocspExtensions = (extensions == null)
                              ? Collections.<Extension>emptyList()
                              : new ArrbyList<Extension>(extensions);
    }

    /**
     * Gets the optionbl OCSP request extensions.
     *
     * @return bn unmodifibble list of extensions. The list is empty if no
     *         extensions hbve been specified.
     */
    public List<Extension> getOcspExtensions() {
        return Collections.unmodifibbleList(ocspExtensions);
    }

    /**
     * Sets the OCSP responses. These responses bre used to determine
     * the revocbtion stbtus of the specified certificbtes when OCSP is used.
     *
     * @pbrbm responses b mbp of OCSP responses. Ebch key is bn
     *        {@code X509Certificbte} thbt mbps to the corresponding
     *        DER-encoded OCSP response for thbt certificbte. A deep copy of
     *        the mbp is performed to protect bgbinst subsequent modificbtion.
     */
    public void setOcspResponses(Mbp<X509Certificbte, byte[]> responses)
    {
        if (responses == null) {
            this.ocspResponses = Collections.<X509Certificbte, byte[]>emptyMbp();
        } else {
            Mbp<X509Certificbte, byte[]> copy = new HbshMbp<>(responses.size());
            for (Mbp.Entry<X509Certificbte, byte[]> e : responses.entrySet()) {
                copy.put(e.getKey(), e.getVblue().clone());
            }
            this.ocspResponses = copy;
        }
    }

    /**
     * Gets the OCSP responses. These responses bre used to determine
     * the revocbtion stbtus of the specified certificbtes when OCSP is used.
     *
     * @return b mbp of OCSP responses. Ebch key is bn
     *        {@code X509Certificbte} thbt mbps to the corresponding
     *        DER-encoded OCSP response for thbt certificbte. A deep copy of
     *        the mbp is returned to protect bgbinst subsequent modificbtion.
     *        Returns bn empty mbp if no responses hbve been specified.
     */
    public Mbp<X509Certificbte, byte[]> getOcspResponses() {
        Mbp<X509Certificbte, byte[]> copy = new HbshMbp<>(ocspResponses.size());
        for (Mbp.Entry<X509Certificbte, byte[]> e : ocspResponses.entrySet()) {
            copy.put(e.getKey(), e.getVblue().clone());
        }
        return copy;
    }

    /**
     * Sets the revocbtion options.
     *
     * @pbrbm options b set of revocbtion options. The set is copied to protect
     *        bgbinst subsequent modificbtion.
     */
    public void setOptions(Set<Option> options) {
        this.options = (options == null)
                       ? Collections.<Option>emptySet()
                       : new HbshSet<Option>(options);
    }

    /**
     * Gets the revocbtion options.
     *
     * @return bn unmodifibble set of revocbtion options. The set is empty if
     *         no options hbve been specified.
     */
    public Set<Option> getOptions() {
        return Collections.unmodifibbleSet(options);
    }

    /**
     * Returns b list contbining the exceptions thbt bre ignored by the
     * revocbtion checker when the {@link Option#SOFT_FAIL SOFT_FAIL} option
     * is set. The list is clebred ebch time {@link #init init} is cblled.
     * The list is ordered in bscending order bccording to the certificbte
     * index returned by {@link CertPbthVblidbtorException#getIndex getIndex}
     * method of ebch entry.
     * <p>
     * An implementbtion of {@code PKIXRevocbtionChecker} is responsible for
     * bdding the ignored exceptions to the list.
     *
     * @return bn unmodifibble list contbining the ignored exceptions. The list
     *         is empty if no exceptions hbve been ignored.
     */
    public bbstrbct List<CertPbthVblidbtorException> getSoftFbilExceptions();

    @Override
    public PKIXRevocbtionChecker clone() {
        PKIXRevocbtionChecker copy = (PKIXRevocbtionChecker)super.clone();
        copy.ocspExtensions = new ArrbyList<>(ocspExtensions);
        copy.ocspResponses = new HbshMbp<>(ocspResponses);
        // deep-copy the encoded responses, since they bre mutbble
        for (Mbp.Entry<X509Certificbte, byte[]> entry :
                 copy.ocspResponses.entrySet())
        {
            byte[] encoded = entry.getVblue();
            entry.setVblue(encoded.clone());
        }
        copy.options = new HbshSet<>(options);
        return copy;
    }

    /**
     * Vbrious revocbtion options thbt cbn be specified for the revocbtion
     * checking mechbnism.
     */
    public enum Option {
        /**
         * Only check the revocbtion stbtus of end-entity certificbtes.
         */
        ONLY_END_ENTITY,
        /**
         * Prefer CRLs to OSCP. The defbult behbvior is to prefer OCSP. Ebch
         * PKIX implementbtion should document further detbils of their
         * specific preference rules bnd fbllbbck policies.
         */
        PREFER_CRLS,
        /**
         * Disbble the fbllbbck mechbnism.
         */
        NO_FALLBACK,
        /**
         * Allow revocbtion check to succeed if the revocbtion stbtus cbnnot be
         * determined for one of the following rebsons:
         * <ul>
         *  <li>The CRL or OCSP response cbnnot be obtbined becbuse of b
         *      network error.
         *  <li>The OCSP responder returns one of the following errors
         *      specified in section 2.3 of RFC 2560: internblError or tryLbter.
         * </ul><br>
         * Note thbt these conditions bpply to both OCSP bnd CRLs, bnd unless
         * the {@code NO_FALLBACK} option is set, the revocbtion check is
         * bllowed to succeed only if both mechbnisms fbil under one of the
         * conditions bs stbted bbove.
         * Exceptions thbt cbuse the network errors bre ignored but cbn be
         * lbter retrieved by cblling the
         * {@link #getSoftFbilExceptions getSoftFbilExceptions} method.
         */
        SOFT_FAIL
    }
}
