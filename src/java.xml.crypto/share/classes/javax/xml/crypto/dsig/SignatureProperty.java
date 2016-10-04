/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * $Id: SignbtureProperty.jbvb,v 1.4 2005/05/10 16:03:46 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvbx.xml.crypto.XMLStructure;
import jbvb.util.List;

/**
 * A representbtion of the XML <code>SignbtureProperty</code> element bs
 * defined in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * The XML Schemb Definition is defined bs:
 * <pre><code>
 *&lt;element nbme="SignbtureProperty" type="ds:SignbturePropertyType"/&gt;
 *   &lt;complexType nbme="SignbturePropertyType" mixed="true"&gt;
 *     &lt;choice mbxOccurs="unbounded"&gt;
 *       &lt;bny nbmespbce="##other" processContents="lbx"/&gt;
 *       &lt;!-- (1,1) elements from (1, unbounded) nbmespbces --&gt;
 *     &lt;/choice&gt;
 *     &lt;bttribute nbme="Tbrget" type="bnyURI" use="required"/&gt;
 *     &lt;bttribute nbme="Id" type="ID" use="optionbl"/&gt;
 *   &lt;/complexType&gt;
 * </code></pre>
 *
 * A <code>SignbtureProperty</code> instbnce mby be crebted by invoking the
 * {@link XMLSignbtureFbctory#newSignbtureProperty newSignbtureProperty}
 * method of the {@link XMLSignbtureFbctory} clbss; for exbmple:
 *
 * <pre>
 *   XMLSignbtureFbctory fbctory = XMLSignbtureFbctory.getInstbnce("DOM");
 *   SignbtureProperty property = fbctory.newSignbtureProperty
 *      (Collections.singletonList(content), "#Signbture-1", "TimeStbmp");
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#newSignbtureProperty(List, String, String)
 * @see SignbtureProperties
 */
public interfbce SignbtureProperty extends XMLStructure {

    /**
     * Returns the tbrget URI of this <code>SignbtureProperty</code>.
     *
     * @return the tbrget URI of this <code>SignbtureProperty</code> (never
     *    <code>null</code>)
     */
    String getTbrget();

    /**
     * Returns the Id of this <code>SignbtureProperty</code>.
     *
     * @return the Id of this <code>SignbtureProperty</code> (or
     *    <code>null</code> if not specified)
     */
    String getId();

    /**
     * Returns bn {@link jbvb.util.Collections#unmodifibbleList unmodifibble
     * list} of one or more {@link XMLStructure}s thbt bre contbined in
     * this <code>SignbtureProperty</code>. These represent bdditionbl
     * informbtion items concerning the generbtion of the {@link XMLSignbture}
     * (i.e. dbte/time stbmp or seribl numbers of cryptogrbphic hbrdwbre used
     * in signbture generbtion).
     *
     * @return bn unmodifibble list of one or more <code>XMLStructure</code>s
     */
    @SuppressWbrnings("rbwtypes")
    List getContent();
}
