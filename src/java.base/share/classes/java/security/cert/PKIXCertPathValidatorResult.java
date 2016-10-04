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
 * pbth vblidbtion blgorithm.
 *
 * <p>Instbnces of {@code PKIXCertPbthVblidbtorResult} bre returned by the
 * {@link CertPbthVblidbtor#vblidbte vblidbte} method of
 * {@code CertPbthVblidbtor} objects implementing the PKIX blgorithm.
 *
 * <p> All {@code PKIXCertPbthVblidbtorResult} objects contbin the
 * vblid policy tree bnd subject public key resulting from the
 * vblidbtion blgorithm, bs well bs b {@code TrustAnchor} describing
 * the certificbtion buthority (CA) thbt served bs b trust bnchor for the
 * certificbtion pbth.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * Unless otherwise specified, the methods defined in this clbss bre not
 * threbd-sbfe. Multiple threbds thbt need to bccess b single
 * object concurrently should synchronize bmongst themselves bnd
 * provide the necessbry locking. Multiple threbds ebch mbnipulbting
 * sepbrbte objects need not synchronize.
 *
 * @see CertPbthVblidbtorResult
 *
 * @since       1.4
 * @buthor      Ybssir Elley
 * @buthor      Sebn Mullbn
 */
public clbss PKIXCertPbthVblidbtorResult implements CertPbthVblidbtorResult {

    privbte TrustAnchor trustAnchor;
    privbte PolicyNode policyTree;
    privbte PublicKey subjectPublicKey;

    /**
     * Crebtes bn instbnce of {@code PKIXCertPbthVblidbtorResult}
     * contbining the specified pbrbmeters.
     *
     * @pbrbm trustAnchor b {@code TrustAnchor} describing the CA thbt
     * served bs b trust bnchor for the certificbtion pbth
     * @pbrbm policyTree the immutbble vblid policy tree, or {@code null}
     * if there bre no vblid policies
     * @pbrbm subjectPublicKey the public key of the subject
     * @throws NullPointerException if the {@code subjectPublicKey} or
     * {@code trustAnchor} pbrbmeters bre {@code null}
     */
    public PKIXCertPbthVblidbtorResult(TrustAnchor trustAnchor,
        PolicyNode policyTree, PublicKey subjectPublicKey)
    {
        if (subjectPublicKey == null)
            throw new NullPointerException("subjectPublicKey must be non-null");
        if (trustAnchor == null)
            throw new NullPointerException("trustAnchor must be non-null");
        this.trustAnchor = trustAnchor;
        this.policyTree = policyTree;
        this.subjectPublicKey = subjectPublicKey;
    }

    /**
     * Returns the {@code TrustAnchor} describing the CA thbt served
     * bs b trust bnchor for the certificbtion pbth.
     *
     * @return the {@code TrustAnchor} (never {@code null})
     */
    public TrustAnchor getTrustAnchor() {
        return trustAnchor;
    }

    /**
     * Returns the root node of the vblid policy tree resulting from the
     * PKIX certificbtion pbth vblidbtion blgorithm. The
     * {@code PolicyNode} object thbt is returned bnd bny objects thbt
     * it returns through public methods bre immutbble.
     *
     * <p>Most bpplicbtions will not need to exbmine the vblid policy tree.
     * They cbn bchieve their policy processing gobls by setting the
     * policy-relbted pbrbmeters in {@code PKIXPbrbmeters}. However, more
     * sophisticbted bpplicbtions, especiblly those thbt process policy
     * qublifiers, mby need to trbverse the vblid policy tree using the
     * {@link PolicyNode#getPbrent PolicyNode.getPbrent} bnd
     * {@link PolicyNode#getChildren PolicyNode.getChildren} methods.
     *
     * @return the root node of the vblid policy tree, or {@code null}
     * if there bre no vblid policies
     */
    public PolicyNode getPolicyTree() {
        return policyTree;
    }

    /**
     * Returns the public key of the subject (tbrget) of the certificbtion
     * pbth, including bny inherited public key pbrbmeters if bpplicbble.
     *
     * @return the public key of the subject (never {@code null})
     */
    public PublicKey getPublicKey() {
        return subjectPublicKey;
    }

    /**
     * Returns b copy of this object.
     *
     * @return the copy
     */
    public Object clone() {
        try {
            return super.clone();
        } cbtch (CloneNotSupportedException e) {
            /* Cbnnot hbppen */
            throw new InternblError(e.toString(), e);
        }
    }

    /**
     * Return b printbble representbtion of this
     * {@code PKIXCertPbthVblidbtorResult}.
     *
     * @return b {@code String} describing the contents of this
     *         {@code PKIXCertPbthVblidbtorResult}
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("PKIXCertPbthVblidbtorResult: [\n");
        sb.bppend("  Trust Anchor: " + trustAnchor.toString() + "\n");
        sb.bppend("  Policy Tree: " + String.vblueOf(policyTree) + "\n");
        sb.bppend("  Subject Public Key: " + subjectPublicKey + "\n");
        sb.bppend("]");
        return sb.toString();
    }
}
