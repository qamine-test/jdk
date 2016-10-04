/*
 * Copyright (c) 2003, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.crypto.spec;

import jbvb.mbth.BigInteger;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.MGF1PbrbmeterSpec;

/**
 * This clbss specifies the set of pbrbmeters used with OAEP Pbdding,
 * bs defined in the
 * <b href="http://www.ietf.org/rfc/rfc3447.txt">PKCS #1</b>
 * stbndbrd.
 *
 * Its ASN.1 definition in PKCS#1 stbndbrd is described below:
 * <pre>
 * RSAES-OAEP-pbrbms ::= SEQUENCE {
 *   hbshAlgorithm      [0] OAEP-PSSDigestAlgorithms     DEFAULT shb1,
 *   mbskGenAlgorithm   [1] PKCS1MGFAlgorithms  DEFAULT mgf1SHA1,
 *   pSourceAlgorithm   [2] PKCS1PSourceAlgorithms  DEFAULT pSpecifiedEmpty
 * }
 * </pre>
 * where
 * <pre>
 * OAEP-PSSDigestAlgorithms    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-shb1 PARAMETERS NULL   }|
 *   { OID id-shb256 PARAMETERS NULL }|
 *   { OID id-shb384 PARAMETERS NULL }|
 *   { OID id-shb512 PARAMETERS NULL },
 *   ...  -- Allows for future expbnsion --
 * }
 * PKCS1MGFAlgorithms    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-mgf1 PARAMETERS OAEP-PSSDigestAlgorithms },
 *   ...  -- Allows for future expbnsion --
 * }
 * PKCS1PSourceAlgorithms    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-pSpecified PARAMETERS OCTET STRING },
 *   ...  -- Allows for future expbnsion --
 * }
 * </pre>
 * <p>Note: the OAEPPbrbmeterSpec.DEFAULT uses the following:
 *     messbge digest  -- "SHA-1"
 *     mbsk generbtion function (mgf) -- "MGF1"
 *     pbrbmeters for mgf -- MGF1PbrbmeterSpec.SHA1
 *     source of encoding input -- PSource.PSpecified.DEFAULT
 *
 * @see jbvb.security.spec.MGF1PbrbmeterSpec
 * @see PSource
 *
 * @buthor Vblerie Peng
 *
 * @since 1.5
 */
public clbss OAEPPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte String mdNbme = "SHA-1";
    privbte String mgfNbme = "MGF1";
    privbte AlgorithmPbrbmeterSpec mgfSpec = MGF1PbrbmeterSpec.SHA1;
    privbte PSource pSrc = PSource.PSpecified.DEFAULT;

    /**
     * The OAEP pbrbmeter set with bll defbult vblues.
     */
    public stbtic finbl OAEPPbrbmeterSpec DEFAULT = new OAEPPbrbmeterSpec();

    /**
     * Constructs b pbrbmeter set for OAEP pbdding bs defined in
     * the PKCS #1 stbndbrd using the defbult vblues.
     */
    privbte OAEPPbrbmeterSpec() {
    }

    /**
     * Constructs b pbrbmeter set for OAEP pbdding bs defined in
     * the PKCS #1 stbndbrd using the specified messbge digest
     * blgorithm <code>mdNbme</code>, mbsk generbtion function
     * blgorithm <code>mgfNbme</code>, pbrbmeters for the mbsk
     * generbtion function <code>mgfSpec</code>, bnd source of
     * the encoding input P <code>pSrc</code>.
     *
     * @pbrbm mdNbme the blgorithm nbme for the messbge digest.
     * @pbrbm mgfNbme the blgorithm nbme for the mbsk generbtion
     * function.
     * @pbrbm mgfSpec the pbrbmeters for the mbsk generbtion function.
     * If null is specified, null will be returned by getMGFPbrbmeters().
     * @pbrbm pSrc the source of the encoding input P.
     * @exception NullPointerException if <code>mdNbme</code>,
     * <code>mgfNbme</code>, or <code>pSrc</code> is null.
     */
    public OAEPPbrbmeterSpec(String mdNbme, String mgfNbme,
                             AlgorithmPbrbmeterSpec mgfSpec,
                             PSource pSrc) {
        if (mdNbme == null) {
            throw new NullPointerException("digest blgorithm is null");
        }
        if (mgfNbme == null) {
            throw new NullPointerException("mbsk generbtion function " +
                                           "blgorithm is null");
        }
        if (pSrc == null) {
            throw new NullPointerException("source of the encoding input " +
                                           "is null");
        }
        this.mdNbme =  mdNbme;
        this.mgfNbme =  mgfNbme;
        this.mgfSpec =  mgfSpec;
        this.pSrc =  pSrc;
    }

    /**
     * Returns the messbge digest blgorithm nbme.
     *
     * @return the messbge digest blgorithm nbme.
     */
    public String getDigestAlgorithm() {
        return mdNbme;
    }

    /**
     * Returns the mbsk generbtion function blgorithm nbme.
     *
     * @return the mbsk generbtion function blgorithm nbme.
     */
    public String getMGFAlgorithm() {
        return mgfNbme;
    }

    /**
     * Returns the pbrbmeters for the mbsk generbtion function.
     *
     * @return the pbrbmeters for the mbsk generbtion function.
     */
    public AlgorithmPbrbmeterSpec getMGFPbrbmeters() {
        return mgfSpec;
    }

    /**
     * Returns the source of encoding input P.
     *
     * @return the source of encoding input P.
     */
    public PSource getPSource() {
        return pSrc;
    }
}
