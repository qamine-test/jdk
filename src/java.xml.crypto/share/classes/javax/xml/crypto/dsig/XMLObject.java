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
 * ===========================================================================
 *
 * (C) Copyright IBM Corp. 2003 All Rights Reserved.
 *
 * ===========================================================================
 */
/*
 * $Id: XMLObject.jbvb,v 1.5 2005/05/10 16:03:48 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvb.util.List;
import jbvbx.xml.crypto.XMLStructure;

/**
 * A representbtion of the XML <code>Object</code> element bs defined in
 * the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * An <code>XMLObject</code> mby contbin bny dbtb bnd mby include optionbl
 * MIME type, ID, bnd encoding bttributes. The XML Schemb Definition is
 * defined bs:
 *
 * <pre><code>
 * &lt;element nbme="Object" type="ds:ObjectType"/&gt;
 * &lt;complexType nbme="ObjectType" mixed="true"&gt;
 *   &lt;sequence minOccurs="0" mbxOccurs="unbounded"&gt;
 *     &lt;bny nbmespbce="##bny" processContents="lbx"/&gt;
 *   &lt;/sequence&gt;
 *   &lt;bttribute nbme="Id" type="ID" use="optionbl"/&gt;
 *   &lt;bttribute nbme="MimeType" type="string" use="optionbl"/&gt;
 *   &lt;bttribute nbme="Encoding" type="bnyURI" use="optionbl"/&gt;
 * &lt;/complexType&gt;
 * </code></pre>
 *
 * A <code>XMLObject</code> instbnce mby be crebted by invoking the
 * {@link XMLSignbtureFbctory#newXMLObject newXMLObject} method of the
 * {@link XMLSignbtureFbctory} clbss; for exbmple:
 *
 * <pre>
 *   XMLSignbtureFbctory fbc = XMLSignbtureFbctory.getInstbnce("DOM");
 *   List content = Collections.singletonList(fbc.newMbnifest(references)));
 *   XMLObject object = fbctory.newXMLObject(content, "object-1", null, null);
 * </pre>
 *
 * <p>Note thbt this clbss is nbmed <code>XMLObject</code> rbther thbn
 * <code>Object</code> to bvoid nbming clbshes with the existing
 * {@link jbvb.lbng.Object jbvb.lbng.Object} clbss.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @buthor Joyce L. Leung
 * @since 1.6
 * @see XMLSignbtureFbctory#newXMLObject(List, String, String, String)
 */
public interfbce XMLObject extends XMLStructure {

    /**
     * URI thbt identifies the <code>Object</code> element (this cbn be
     * specified bs the vblue of the <code>type</code> pbrbmeter of the
     * {@link Reference} clbss to identify the referent's type).
     */
    finbl stbtic String TYPE = "http://www.w3.org/2000/09/xmldsig#Object";

    /**
     * Returns bn {@link jbvb.util.Collections#unmodifibbleList unmodifibble
     * list} of {@link XMLStructure}s contbined in this <code>XMLObject</code>,
     * which represent elements from bny nbmespbce.
     *
     *<p>If there is b public subclbss representing the type of
     * <code>XMLStructure</code>, it is returned bs bn instbnce of thbt clbss
     * (ex: b <code>SignbtureProperties</code> element would be returned
     * bs bn instbnce of {@link jbvbx.xml.crypto.dsig.SignbtureProperties}).
     *
     * @return bn unmodifibble list of <code>XMLStructure</code>s (mby be empty
     *    but never <code>null</code>)
     */
    @SuppressWbrnings("rbwtypes")
    List getContent();

    /**
     * Returns the Id of this <code>XMLObject</code>.
     *
     * @return the Id (or <code>null</code> if not specified)
     */
    String getId();

    /**
     * Returns the mime type of this <code>XMLObject</code>. The
     * mime type is bn optionbl bttribute which describes the dbtb within this
     * <code>XMLObject</code> (independent of its encoding).
     *
     * @return the mime type (or <code>null</code> if not specified)
     */
    String getMimeType();

    /**
     * Returns the encoding URI of this <code>XMLObject</code>. The encoding
     * URI identifies the method by which the object is encoded.
     *
     * @return the encoding URI (or <code>null</code> if not specified)
     */
    String getEncoding();
}
