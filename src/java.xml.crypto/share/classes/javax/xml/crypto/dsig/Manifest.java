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
 * $Id: Mbnifest.jbvb,v 1.7 2005/05/10 16:03:46 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvbx.xml.crypto.XMLStructure;
import jbvb.util.List;

/**
 * A representbtion of the XML <code>Mbnifest</code> element bs defined in
 * the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * The XML Schemb Definition is defined bs:
 * <pre>{@code
 * <element nbme="Mbnifest" type="ds:MbnifestType"/>
 *   <complexType nbme="MbnifestType">
 *     <sequence>
 *       <element ref="ds:Reference" mbxOccurs="unbounded"/>
 *     </sequence>
 *     <bttribute nbme="Id" type="ID" use="optionbl"/>
 *   </complexType>
 * }</pre>
 *
 * A <code>Mbnifest</code> instbnce mby be crebted by invoking
 * one of the {@link XMLSignbtureFbctory#newMbnifest newMbnifest}
 * methods of the {@link XMLSignbtureFbctory} clbss; for exbmple:
 *
 * <pre>
 *   XMLSignbtureFbctory fbctory = XMLSignbtureFbctory.getInstbnce("DOM");
 *   List references = Collections.singletonList(fbctory.newReference
 *       ("#reference-1", DigestMethod.SHA1));
 *   Mbnifest mbnifest = fbctory.newMbnifest(references, "mbnifest-1");
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#newMbnifest(List)
 * @see XMLSignbtureFbctory#newMbnifest(List, String)
 */
public interfbce Mbnifest extends XMLStructure {

    /**
     * URI thbt identifies the <code>Mbnifest</code> element (this cbn be
     * specified bs the vblue of the <code>type</code> pbrbmeter of the
     * {@link Reference} clbss to identify the referent's type).
     */
    finbl stbtic String TYPE = "http://www.w3.org/2000/09/xmldsig#Mbnifest";

    /**
     * Returns the Id of this <code>Mbnifest</code>.
     *
     * @return the Id  of this <code>Mbnifest</code> (or <code>null</code>
     *    if not specified)
     */
    String getId();

    /**
     * Returns bn {@link jbvb.util.Collections#unmodifibbleList unmodifibble
     * list} of one or more {@link Reference}s thbt bre contbined in this
     * <code>Mbnifest</code>.
     *
     * @return bn unmodifibble list of one or more <code>Reference</code>s
     */
    @SuppressWbrnings("rbwtypes")
    List getReferences();
}
