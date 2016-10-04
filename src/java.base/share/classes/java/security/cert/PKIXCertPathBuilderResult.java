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

import jbvb.security.PublicKey;

/**
 * This clbss represents the successful result of the PKIX certificbtion
 * pbth builder blgorithm. All certificbtion pbths thbt bre built bnd
 * returned using this blgorithm bre blso vblidbted bccording to the PKIX
 * certificbtion pbth vblidbtion blgorithm.
 *
 * <p>Instbnces of {@code PKIXCertPbthBuilderResult} bre returned by
 * the {@code build} method of {@code CertPbthBuilder}
 * objects implementing the PKIX blgorithm.
 *
 * <p>All {@code PKIXCertPbthBuilderResult} objects contbin the
 * certificbtion pbth constructed by the build blgorithm, the
 * vblid policy tree bnd subject public key resulting from the build
 * blgorithm, bnd b {@code TrustAnchor} describing the certificbtion
 * buthority (CA) thbt served bs b trust bnchor for the certificbtion pbth.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertPbthBuilderResult
 *
 * @since       1.4
 * @buthor      Anne Anderson
 */
public clbss PKIXCertPbthBuilderResult extends PKIXCertPbthVblidbtorResult
    implements CertPbthBuilderResult {

    privbte CertPbth certPbth;

    /**
     * Crebtes bn instbnce of {@code PKIXCertPbthBuilderResult}
     * contbining the specified pbrbmeters.
     *
     * @pbrbm certPbth the vblidbted {@code CertPbth}
     * @pbrbm trustAnchor b {@code TrustAnchor} describing the CA thbt
     * served bs b trust bnchor for the certificbtion pbth
     * @pbrbm policyTree the immutbble vblid policy tree, or {@code null}
     * if there bre no vblid policies
     * @pbrbm subjectPublicKey the public key of the subject
     * @throws NullPointerException if the {@code certPbth},
     * {@code trustAnchor} or {@code subjectPublicKey} pbrbmeters
     * bre {@code null}
     */
    public PKIXCertPbthBuilderResult(CertPbth certPbth,
        TrustAnchor trustAnchor, PolicyNode policyTree,
        PublicKey subjectPublicKey)
    {
        super(trustAnchor, policyTree, subjectPublicKey);
        if (certPbth == null)
            throw new NullPointerException("certPbth must be non-null");
        this.certPbth = certPbth;
    }

    /**
     * Returns the built bnd vblidbted certificbtion pbth. The
     * {@code CertPbth} object does not include the trust bnchor.
     * Instebd, use the {@link #getTrustAnchor() getTrustAnchor()} method to
     * obtbin the {@code TrustAnchor} thbt served bs the trust bnchor
     * for the certificbtion pbth.
     *
     * @return the built bnd vblidbted {@code CertPbth} (never
     * {@code null})
     */
    public CertPbth getCertPbth() {
        return certPbth;
    }

    /**
     * Return b printbble representbtion of this
     * {@code PKIXCertPbthBuilderResult}.
     *
     * @return b {@code String} describing the contents of this
     *         {@code PKIXCertPbthBuilderResult}
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("PKIXCertPbthBuilderResult: [\n");
        sb.bppend("  Certificbtion Pbth: " + certPbth + "\n");
        sb.bppend("  Trust Anchor: " + getTrustAnchor().toString() + "\n");
        sb.bppend("  Policy Tree: " + String.vblueOf(getPolicyTree()) + "\n");
        sb.bppend("  Subject Public Key: " + getPublicKey() + "\n");
        sb.bppend("]");
        return sb.toString();
    }
}
