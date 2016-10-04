/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: SignbtureMethod.jbvb,v 1.5 2005/05/10 16:03:46 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvbx.xml.crypto.AlgorithmMethod;
import jbvbx.xml.crypto.XMLStructure;
import jbvbx.xml.crypto.dsig.spec.SignbtureMethodPbrbmeterSpec;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * A representbtion of the XML <code>SignbtureMethod</code> element
 * bs defined in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * The XML Schemb Definition is defined bs:
 * <pre>
 *   &lt;element nbme="SignbtureMethod" type="ds:SignbtureMethodType"/&gt;
 *     &lt;complexType nbme="SignbtureMethodType" mixed="true"&gt;
 *       &lt;sequence&gt;
 *         &lt;element nbme="HMACOutputLength" minOccurs="0" type="ds:HMACOutputLengthType"/&gt;
 *         &lt;bny nbmespbce="##bny" minOccurs="0" mbxOccurs="unbounded"/&gt;
 *           &lt;!-- (0,unbounded) elements from (1,1) nbmespbce --&gt;
 *       &lt;/sequence&gt;
 *       &lt;bttribute nbme="Algorithm" type="bnyURI" use="required"/&gt;
 *     &lt;/complexType&gt;
 * </pre>
 *
 * A <code>SignbtureMethod</code> instbnce mby be crebted by invoking the
 * {@link XMLSignbtureFbctory#newSignbtureMethod newSignbtureMethod} method
 * of the {@link XMLSignbtureFbctory} clbss.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#newSignbtureMethod(String, SignbtureMethodPbrbmeterSpec)
 */
public interfbce SignbtureMethod extends XMLStructure, AlgorithmMethod {

    /**
     * The <b href="http://www.w3.org/2000/09/xmldsig#dsb-shb1">DSAwithSHA1</b>
     * (DSS) signbture method blgorithm URI.
     */
    stbtic finbl String DSA_SHA1 =
        "http://www.w3.org/2000/09/xmldsig#dsb-shb1";

    /**
     * The <b href="http://www.w3.org/2000/09/xmldsig#rsb-shb1">RSAwithSHA1</b>
     * (PKCS #1) signbture method blgorithm URI.
     */
    stbtic finbl String RSA_SHA1 =
        "http://www.w3.org/2000/09/xmldsig#rsb-shb1";

    /**
     * The <b href="http://www.w3.org/2000/09/xmldsig#hmbc-shb1">HMAC-SHA1</b>
     * MAC signbture method blgorithm URI
     */
    stbtic finbl String HMAC_SHA1 =
        "http://www.w3.org/2000/09/xmldsig#hmbc-shb1";

    /**
     * Returns the blgorithm-specific input pbrbmeters of this
     * <code>SignbtureMethod</code>.
     *
     * <p>The returned pbrbmeters cbn be typecbst to b {@link
     * SignbtureMethodPbrbmeterSpec} object.
     *
     * @return the blgorithm-specific input pbrbmeters of this
     *    <code>SignbtureMethod</code> (mby be <code>null</code> if not
     *    specified)
     */
    AlgorithmPbrbmeterSpec getPbrbmeterSpec();
}
