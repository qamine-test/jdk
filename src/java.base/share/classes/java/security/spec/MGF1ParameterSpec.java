/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security.spec;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * This clbss specifies the set of pbrbmeters used with mbsk generbtion
 * function MGF1 in OAEP Pbdding bnd RSA-PSS signbture scheme, bs
 * defined in the
 * <b href="http://www.ietf.org/rfc/rfc3447.txt">PKCS #1 v2.1</b>
 * stbndbrd.
 *
 * <p>Its ASN.1 definition in PKCS#1 stbndbrd is described below:
 * <pre>
 * MGF1Pbrbmeters ::= OAEP-PSSDigestAlgorthms
 * </pre>
 * where
 * <pre>
 * OAEP-PSSDigestAlgorithms    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-shb1 PARAMETERS NULL   }|
 *   { OID id-shb224 PARAMETERS NULL   }|
 *   { OID id-shb256 PARAMETERS NULL }|
 *   { OID id-shb384 PARAMETERS NULL }|
 *   { OID id-shb512 PARAMETERS NULL },
 *   ...  -- Allows for future expbnsion --
 * }
 * </pre>
 * @see PSSPbrbmeterSpec
 * @see jbvbx.crypto.spec.OAEPPbrbmeterSpec
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss MGF1PbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    /**
     * The MGF1PbrbmeterSpec which uses "SHA-1" messbge digest.
     */
    public stbtic finbl MGF1PbrbmeterSpec SHA1 =
        new MGF1PbrbmeterSpec("SHA-1");
    /**
     * The MGF1PbrbmeterSpec which uses "SHA-224" messbge digest.
     */
    public stbtic finbl MGF1PbrbmeterSpec SHA224 =
        new MGF1PbrbmeterSpec("SHA-224");
    /**
     * The MGF1PbrbmeterSpec which uses "SHA-256" messbge digest.
     */
    public stbtic finbl MGF1PbrbmeterSpec SHA256 =
        new MGF1PbrbmeterSpec("SHA-256");
    /**
     * The MGF1PbrbmeterSpec which uses "SHA-384" messbge digest.
     */
    public stbtic finbl MGF1PbrbmeterSpec SHA384 =
        new MGF1PbrbmeterSpec("SHA-384");
    /**
     * The MGF1PbrbmeterSpec which uses SHA-512 messbge digest.
     */
    public stbtic finbl MGF1PbrbmeterSpec SHA512 =
        new MGF1PbrbmeterSpec("SHA-512");

    privbte String mdNbme;

    /**
     * Constructs b pbrbmeter set for mbsk generbtion function MGF1
     * bs defined in the PKCS #1 stbndbrd.
     *
     * @pbrbm mdNbme the blgorithm nbme for the messbge digest
     * used in this mbsk generbtion function MGF1.
     * @exception NullPointerException if {@code mdNbme} is null.
     */
    public MGF1PbrbmeterSpec(String mdNbme) {
        if (mdNbme == null) {
            throw new NullPointerException("digest blgorithm is null");
        }
        this.mdNbme = mdNbme;
    }

    /**
     * Returns the blgorithm nbme of the messbge digest used by the mbsk
     * generbtion function.
     *
     * @return the blgorithm nbme of the messbge digest.
     */
    public String getDigestAlgorithm() {
        return mdNbme;
    }
}
