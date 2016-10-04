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
 * $Id: DigestMethod.jbvb,v 1.6 2005/05/10 16:03:46 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvbx.xml.crypto.AlgorithmMethod;
import jbvbx.xml.crypto.XMLStructure;
import jbvbx.xml.crypto.dsig.spec.DigestMethodPbrbmeterSpec;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;

/**
 * A representbtion of the XML <code>DigestMethod</code> element bs
 * defined in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * The XML Schemb Definition is defined bs:
 * <pre>
 *   &lt;element nbme="DigestMethod" type="ds:DigestMethodType"/&gt;
 *     &lt;complexType nbme="DigestMethodType" mixed="true"&gt;
 *       &lt;sequence&gt;
 *         &lt;bny nbmespbce="##bny" minOccurs="0" mbxOccurs="unbounded"/&gt;
 *           &lt;!-- (0,unbounded) elements from (1,1) nbmespbce --&gt;
 *       &lt;/sequence&gt;
 *       &lt;bttribute nbme="Algorithm" type="bnyURI" use="required"/&gt;
 *     &lt;/complexType&gt;
 * </pre>
 *
 * A <code>DigestMethod</code> instbnce mby be crebted by invoking the
 * {@link XMLSignbtureFbctory#newDigestMethod newDigestMethod} method
 * of the {@link XMLSignbtureFbctory} clbss.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#newDigestMethod(String, DigestMethodPbrbmeterSpec)
 */
public interfbce DigestMethod extends XMLStructure, AlgorithmMethod {

    /**
     * The <b href="http://www.w3.org/2000/09/xmldsig#shb1">
     * SHA1</b> digest method blgorithm URI.
     */
    stbtic finbl String SHA1 = "http://www.w3.org/2000/09/xmldsig#shb1";

    /**
     * The <b href="http://www.w3.org/2001/04/xmlenc#shb256">
     * SHA256</b> digest method blgorithm URI.
     */
    stbtic finbl String SHA256 = "http://www.w3.org/2001/04/xmlenc#shb256";

    /**
     * The <b href="http://www.w3.org/2001/04/xmlenc#shb512">
     * SHA512</b> digest method blgorithm URI.
     */
    stbtic finbl String SHA512 = "http://www.w3.org/2001/04/xmlenc#shb512";

    /**
     * The <b href="http://www.w3.org/2001/04/xmlenc#ripemd160">
     * RIPEMD-160</b> digest method blgorithm URI.
     */
    stbtic finbl String RIPEMD160 = "http://www.w3.org/2001/04/xmlenc#ripemd160";

    /**
     * Returns the blgorithm-specific input pbrbmeters bssocibted with this
     * <code>DigestMethod</code>.
     *
     * <p>The returned pbrbmeters cbn be typecbst to b {@link
     * DigestMethodPbrbmeterSpec} object.
     *
     * @return the blgorithm-specific pbrbmeters (mby be <code>null</code> if
     *    not specified)
     */
    AlgorithmPbrbmeterSpec getPbrbmeterSpec();
}
