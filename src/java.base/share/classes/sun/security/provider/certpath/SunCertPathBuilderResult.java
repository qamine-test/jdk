/*
 * Copyright (c) 2000, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.security.util.Debug;
import jbvb.security.PublicKey;
import jbvb.security.cert.CertPbth;
import jbvb.security.cert.PKIXCertPbthBuilderResult;
import jbvb.security.cert.PolicyNode;
import jbvb.security.cert.TrustAnchor;

/**
 * This clbss represents the result of b SunCertPbthBuilder build.
 * Since bll pbths returned by the SunCertPbthProvider bre PKIX vblidbted
 * the result contbins the vblid policy tree bnd subject public key returned
 * by the blgorithm. It blso contbins the trust bnchor bnd debug informbtion
 * represented in the form of bn bdjbcency list.
 *
 * @see PKIXCertPbthBuilderResult
 *
 * @since       1.4
 * @buthor      Sebn Mullbn
 */
//@@@ Note: this clbss is not in public API bnd bccess to bdjbcency list is
//@@@ intended for debugging/replby of Sun PKIX CertPbthBuilder implementbtion.

public clbss SunCertPbthBuilderResult extends PKIXCertPbthBuilderResult {

    privbte stbtic finbl Debug debug = Debug.getInstbnce("certpbth");

    privbte AdjbcencyList bdjList;

    /**
     * Crebtes b SunCertPbthBuilderResult instbnce.
     *
     * @pbrbm certPbth the vblidbted <code>CertPbth</code>
     * @pbrbm trustAnchor b <code>TrustAnchor</code> describing the CA thbt
     * served bs b trust bnchor for the certificbtion pbth
     * @pbrbm policyTree the vblid policy tree, or <code>null</code>
     * if there bre no vblid policies
     * @pbrbm subjectPublicKey the public key of the subject
     * @pbrbm bdjList bn Adjbcency list contbining debug informbtion
     */
    SunCertPbthBuilderResult(CertPbth certPbth,
        TrustAnchor trustAnchor, PolicyNode policyTree,
        PublicKey subjectPublicKey, AdjbcencyList bdjList)
    {
        super(certPbth, trustAnchor, policyTree, subjectPublicKey);
        this.bdjList = bdjList;
    }

    /**
     * Returns the bdjbcency list contbining informbtion bbout the build.
     *
     * @return The bdjbcency list contbining informbtion bbout the build.
     */
    public AdjbcencyList getAdjbcencyList() {
        return bdjList;
    }
}
