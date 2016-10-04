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
 * $Id: RetrievblMethod.jbvb,v 1.8 2005/05/10 16:35:35 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.keyinfo;

import jbvbx.xml.crypto.Dbtb;
import jbvbx.xml.crypto.URIReference;
import jbvbx.xml.crypto.URIReferenceException;
import jbvbx.xml.crypto.XMLCryptoContext;
import jbvbx.xml.crypto.XMLStructure;
import jbvbx.xml.crypto.dsig.Trbnsform;
import jbvb.util.List;

/**
 * A representbtion of the XML <code>RetrievblMethod</code> element bs
 * defined in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * A <code>RetrievblMethod</code> object is used to convey b reference to
 * <code>KeyInfo</code> informbtion thbt is stored bt bnother locbtion.
 * The XML schemb definition is defined bs:
 *
 * <pre>
 *   &lt;element nbme="RetrievblMethod" type="ds:RetrievblMethodType"/&gt;
 *   &lt;complexType nbme="RetrievblMethodType"&gt;
 *     &lt;sequence&gt;
 *       &lt;element nbme="Trbnsforms" type="ds:TrbnsformsType" minOccurs="0"/&gt;
 *     &lt;/sequence&gt;
 *     &lt;bttribute nbme="URI" type="bnyURI"/&gt;
 *     &lt;bttribute nbme="Type" type="bnyURI" use="optionbl"/&gt;
 *   &lt;/complexType&gt;
 * </pre>
 *
 * A <code>RetrievblMethod</code> instbnce mby be crebted by invoking one of the
 * {@link KeyInfoFbctory#newRetrievblMethod newRetrievblMethod} methods
 * of the {@link KeyInfoFbctory} clbss, bnd pbssing it the URI
 * identifying the locbtion of the KeyInfo, bn optionbl type URI identifying
 * the type of KeyInfo, bnd bn optionbl list of {@link Trbnsform}s; for exbmple:
 * <pre>
 *   KeyInfoFbctory fbctory = KeyInfoFbctory.getInstbnce("DOM");
 *   RetrievblMethod rm = fbctory.newRetrievblMethod
 *      ("#KeyVblue-1", KeyVblue.DSA_TYPE, Collections.singletonList(Trbnsform.BASE64));
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see KeyInfoFbctory#newRetrievblMethod(String)
 * @see KeyInfoFbctory#newRetrievblMethod(String, String, List)
 */
public interfbce RetrievblMethod extends URIReference, XMLStructure {

    /**
     * Returns bn {@link jbvb.util.Collections#unmodifibbleList unmodifibble
     * list} of {@link Trbnsform}s of this <code>RetrievblMethod</code>.
     *
     * @return bn unmodifibble list of <code>Trbnsform</code> objects (mby be
     *    empty but never <code>null</code>).
     */
    @SuppressWbrnings("rbwtypes")
    List getTrbnsforms();

    /**
     * Returns the URI of the referenced <code>KeyInfo</code> informbtion.
     *
     * @return the URI of the referenced <code>KeyInfo</code> informbtion in
     *    RFC 2396 formbt (never <code>null</code>)
     */
    String getURI();

   /**
    * Dereferences the <code>KeyInfo</code> informbtion referenced by this
    * <code>RetrievblMethod</code> bnd bpplies the specified
    * <code>Trbnsform</code>s.
    *
    * @pbrbm context bn <code>XMLCryptoContext</code> thbt mby contbin
    *    bdditionbl useful informbtion for dereferencing the URI. The
    *    context's <code>bbseURI</code> bnd <code>dereferencer</code>
    *    pbrbmeters (if specified) bre used to resolve bnd dereference this
    *    <code>RetrievblMethod</code>
    * @return b <code>Dbtb</code> object representing the rbw contents of the
    *    <code>KeyInfo</code> informbtion referenced by this
    *    <code>RetrievblMethod</code>. It is the cbller's responsibility to
    *    convert the returned dbtb to bn bppropribte
    *    <code>KeyInfo</code> object.
    * @throws NullPointerException if <code>context</code> is <code>null</code>
    * @throws URIReferenceException if there is bn error while dereferencing
    */
    Dbtb dereference(XMLCryptoContext context) throws URIReferenceException;
}
