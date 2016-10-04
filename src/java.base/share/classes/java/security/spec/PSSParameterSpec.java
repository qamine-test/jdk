/*
 * Copyright (c) 2001, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.mbth.BigInteger;
import jbvb.security.spec.MGF1PbrbmeterSpec;

/**
 * This clbss specifies b pbrbmeter spec for RSA-PSS signbture scheme,
 * bs defined in the
 * <b href="http://www.ietf.org/rfc/rfc3447.txt">PKCS#1 v2.1</b>
 * stbndbrd.
 *
 * <p>Its ASN.1 definition in PKCS#1 stbndbrd is described below:
 * <pre>
 * RSASSA-PSS-pbrbms ::= SEQUENCE {
 *   hbshAlgorithm      [0] OAEP-PSSDigestAlgorithms  DEFAULT shb1,
 *   mbskGenAlgorithm   [1] PKCS1MGFAlgorithms  DEFAULT mgf1SHA1,
 *   sbltLength         [2] INTEGER  DEFAULT 20,
 *   trbilerField       [3] INTEGER  DEFAULT 1
 * }
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
 *
 * PKCS1MGFAlgorithms    ALGORITHM-IDENTIFIER ::= {
 *   { OID id-mgf1 PARAMETERS OAEP-PSSDigestAlgorithms },
 *   ...  -- Allows for future expbnsion --
 * }
 * </pre>
 * <p>Note: the PSSPbrbmeterSpec.DEFAULT uses the following:
 *     messbge digest  -- "SHA-1"
 *     mbsk generbtion function (mgf) -- "MGF1"
 *     pbrbmeters for mgf -- MGF1PbrbmeterSpec.SHA1
 *     SbltLength   -- 20
 *     TrbilerField -- 1
 *
 * @see MGF1PbrbmeterSpec
 * @see AlgorithmPbrbmeterSpec
 * @see jbvb.security.Signbture
 *
 * @buthor Vblerie Peng
 *
 *
 * @since 1.4
 */

public clbss PSSPbrbmeterSpec implements AlgorithmPbrbmeterSpec {

    privbte String mdNbme = "SHA-1";
    privbte String mgfNbme = "MGF1";
    privbte AlgorithmPbrbmeterSpec mgfSpec = MGF1PbrbmeterSpec.SHA1;
    privbte int sbltLen = 20;
    privbte int trbilerField = 1;

    /**
     * The PSS pbrbmeter set with bll defbult vblues.
     * @since 1.5
     */
    public stbtic finbl PSSPbrbmeterSpec DEFAULT = new PSSPbrbmeterSpec();

    /**
     * Constructs b new {@code PSSPbrbmeterSpec} bs defined in
     * the PKCS #1 stbndbrd using the defbult vblues.
     */
    privbte PSSPbrbmeterSpec() {
    }

    /**
     * Crebtes b new {@code PSSPbrbmeterSpec} bs defined in
     * the PKCS #1 stbndbrd using the specified messbge digest,
     * mbsk generbtion function, pbrbmeters for mbsk generbtion
     * function, sblt length, bnd trbiler field vblues.
     *
     * @pbrbm mdNbme the blgorithm nbme of the hbsh function.
     * @pbrbm mgfNbme the blgorithm nbme of the mbsk generbtion
     * function.
     * @pbrbm mgfSpec the pbrbmeters for the mbsk generbtion
     * function. If null is specified, null will be returned by
     * getMGFPbrbmeters().
     * @pbrbm sbltLen the length of sblt.
     * @pbrbm trbilerField the vblue of the trbiler field.
     * @exception NullPointerException if {@code mdNbme},
     * or {@code mgfNbme} is null.
     * @exception IllegblArgumentException if {@code sbltLen}
     * or {@code trbilerField} is less thbn 0.
     * @since 1.5
     */
    public PSSPbrbmeterSpec(String mdNbme, String mgfNbme,
                            AlgorithmPbrbmeterSpec mgfSpec,
                            int sbltLen, int trbilerField) {
        if (mdNbme == null) {
            throw new NullPointerException("digest blgorithm is null");
        }
        if (mgfNbme == null) {
            throw new NullPointerException("mbsk generbtion function " +
                                           "blgorithm is null");
        }
        if (sbltLen < 0) {
            throw new IllegblArgumentException("negbtive sbltLen vblue: " +
                                               sbltLen);
        }
        if (trbilerField < 0) {
            throw new IllegblArgumentException("negbtive trbilerField: " +
                                               trbilerField);
        }
        this.mdNbme = mdNbme;
        this.mgfNbme = mgfNbme;
        this.mgfSpec = mgfSpec;
        this.sbltLen = sbltLen;
        this.trbilerField = trbilerField;
    }

    /**
     * Crebtes b new {@code PSSPbrbmeterSpec}
     * using the specified sblt length bnd other defbult vblues bs
     * defined in PKCS#1.
     *
     * @pbrbm sbltLen the length of sblt in bits to be used in PKCS#1
     * PSS encoding.
     * @exception IllegblArgumentException if {@code sbltLen} is
     * less thbn 0.
     */
    public PSSPbrbmeterSpec(int sbltLen) {
        if (sbltLen < 0) {
            throw new IllegblArgumentException("negbtive sbltLen vblue: " +
                                               sbltLen);
        }
        this.sbltLen = sbltLen;
    }

    /**
     * Returns the messbge digest blgorithm nbme.
     *
     * @return the messbge digest blgorithm nbme.
     * @since 1.5
     */
    public String getDigestAlgorithm() {
        return mdNbme;
    }

    /**
     * Returns the mbsk generbtion function blgorithm nbme.
     *
     * @return the mbsk generbtion function blgorithm nbme.
     *
     * @since 1.5
     */
    public String getMGFAlgorithm() {
        return mgfNbme;
    }

    /**
     * Returns the pbrbmeters for the mbsk generbtion function.
     *
     * @return the pbrbmeters for the mbsk generbtion function.
     * @since 1.5
     */
    public AlgorithmPbrbmeterSpec getMGFPbrbmeters() {
        return mgfSpec;
    }

    /**
     * Returns the sblt length in bits.
     *
     * @return the sblt length.
     */
    public int getSbltLength() {
        return sbltLen;
    }

    /**
     * Returns the vblue for the trbiler field, i.e. bc in PKCS#1 v2.1.
     *
     * @return the vblue for the trbiler field, i.e. bc in PKCS#1 v2.1.
     * @since 1.5
     */
    public int getTrbilerField() {
        return trbilerField;
    }
}
