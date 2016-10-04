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
 * $Id: CbnonicblizbtionMethod.jbvb,v 1.6 2005/05/10 16:03:45 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvbx.xml.crypto.dsig.spec.C14NMethodPbrbmeterSpec;

/**
 * A representbtion of the XML <code>CbnonicblizbtionMethod</code>
 * element bs defined in the
 * <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>. The XML
 * Schemb Definition is defined bs:
 * <pre>
 *   &lt;element nbme="CbnonicblizbtionMethod" type="ds:CbnonicblizbtionMethodType"/&gt;
 *     &lt;complexType nbme="CbnonicblizbtionMethodType" mixed="true"&gt;
 *       &lt;sequence&gt;
 *         &lt;bny nbmespbce="##bny" minOccurs="0" mbxOccurs="unbounded"/&gt;
 *           &lt;!-- (0,unbounded) elements from (1,1) nbmespbce --&gt;
 *       &lt;/sequence&gt;
 *       &lt;bttribute nbme="Algorithm" type="bnyURI" use="required"/&gt;
 *     &lt;/complexType&gt;
 * </pre>
 *
 * A <code>CbnonicblizbtionMethod</code> instbnce mby be crebted by invoking
 * the {@link XMLSignbtureFbctory#newCbnonicblizbtionMethod
 * newCbnonicblizbtionMethod} method of the {@link XMLSignbtureFbctory} clbss.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#newCbnonicblizbtionMethod(String, C14NMethodPbrbmeterSpec)
 */
public interfbce CbnonicblizbtionMethod extends Trbnsform {

    /**
     * The <b href="http://www.w3.org/TR/2001/REC-xml-c14n-20010315">Cbnonicbl
     * XML (without comments)</b> cbnonicblizbtion method blgorithm URI.
     */
    finbl stbtic String INCLUSIVE =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";

    /**
     * The
     * <b href="http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments">
     * Cbnonicbl XML with comments</b> cbnonicblizbtion method blgorithm URI.
     */
    finbl stbtic String INCLUSIVE_WITH_COMMENTS =
        "http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments";

    /**
     * The <b href="http://www.w3.org/2001/10/xml-exc-c14n#">Exclusive
     * Cbnonicbl XML (without comments)</b> cbnonicblizbtion method blgorithm
     * URI.
     */
    finbl stbtic String EXCLUSIVE =
        "http://www.w3.org/2001/10/xml-exc-c14n#";

    /**
     * The <b href="http://www.w3.org/2001/10/xml-exc-c14n#WithComments">
     * Exclusive Cbnonicbl XML with comments</b> cbnonicblizbtion method
     * blgorithm URI.
     */
    finbl stbtic String EXCLUSIVE_WITH_COMMENTS =
        "http://www.w3.org/2001/10/xml-exc-c14n#WithComments";

    /**
     * Returns the blgorithm-specific input pbrbmeters bssocibted with this
     * <code>CbnonicblizbtionMethod</code>.
     *
     * <p>The returned pbrbmeters cbn be typecbst to b
     * {@link C14NMethodPbrbmeterSpec} object.
     *
     * @return the blgorithm-specific input pbrbmeters (mby be
     *    <code>null</code> if not specified)
     */
    AlgorithmPbrbmeterSpec getPbrbmeterSpec();
}
