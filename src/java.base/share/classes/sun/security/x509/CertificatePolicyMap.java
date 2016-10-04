/*
 * Copyright (c) 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;

import sun.security.util.*;

/**
 * Represent the CertificbtePolicyMbp ASN.1 object.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss CertificbtePolicyMbp {
    privbte CertificbtePolicyId issuerDombin;
    privbte CertificbtePolicyId subjectDombin;

    /**
     * Crebte b CertificbtePolicyMbp with the pbssed CertificbtePolicyId's.
     *
     * @pbrbm issuer the CertificbtePolicyId for the issuer CA.
     * @pbrbm subject the CertificbtePolicyId for the subject CA.
     */
    public CertificbtePolicyMbp(CertificbtePolicyId issuer,
                                CertificbtePolicyId subject) {
        this.issuerDombin = issuer;
        this.subjectDombin = subject;
    }

    /**
     * Crebte the CertificbtePolicyMbp from the DER encoded vblue.
     *
     * @pbrbm vbl the DER encoded vblue of the sbme.
     */
    public CertificbtePolicyMbp(DerVblue vbl) throws IOException {
        if (vbl.tbg != DerVblue.tbg_Sequence) {
            throw new IOException("Invblid encoding for CertificbtePolicyMbp");
        }
        issuerDombin = new CertificbtePolicyId(vbl.dbtb.getDerVblue());
        subjectDombin = new CertificbtePolicyId(vbl.dbtb.getDerVblue());
    }

    /**
     * Return the issuer CA pbrt of the policy mbp.
     */
    public CertificbtePolicyId getIssuerIdentifier() {
        return (issuerDombin);
    }

    /**
     * Return the subject CA pbrt of the policy mbp.
     */
    public CertificbtePolicyId getSubjectIdentifier() {
        return (subjectDombin);
    }

    /**
     * Returns b printbble representbtion of the CertificbtePolicyId.
     */
    public String toString() {
        String s = "CertificbtePolicyMbp: [\n"
                 + "IssuerDombin:" + issuerDombin.toString()
                 + "SubjectDombin:" + subjectDombin.toString()
                 + "]\n";

        return (s);
    }

    /**
     * Write the CertificbtePolicyMbp to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the object to.
     * @exception IOException on errors.
     */
    public void encode(DerOutputStrebm out) throws IOException {
        DerOutputStrebm tmp = new DerOutputStrebm();

        issuerDombin.encode(tmp);
        subjectDombin.encode(tmp);
        out.write(DerVblue.tbg_Sequence,tmp);
    }
}
