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
 * $Id: Reference.jbvb,v 1.9 2005/05/10 16:03:46 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvbx.xml.crypto.Dbtb;
import jbvbx.xml.crypto.URIReference;
import jbvbx.xml.crypto.XMLStructure;
import jbvb.io.InputStrebm;
import jbvb.util.List;

/**
 * A representbtion of the <code>Reference</code> element bs defined in the
 * <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * The XML schemb is defined bs:
 * <pre>
 * &lt;element nbme="Reference" type="ds:ReferenceType"/&gt;
 * &lt;complexType nbme="ReferenceType"&gt;
 *   &lt;sequence&gt;
 *     &lt;element ref="ds:Trbnsforms" minOccurs="0"/&gt;
 *     &lt;element ref="ds:DigestMethod"/&gt;
 *     &lt;element ref="ds:DigestVblue"/&gt;
 *   &lt;/sequence&gt;
 *   &lt;bttribute nbme="Id" type="ID" use="optionbl"/&gt;
 *   &lt;bttribute nbme="URI" type="bnyURI" use="optionbl"/&gt;
 *   &lt;bttribute nbme="Type" type="bnyURI" use="optionbl"/&gt;
 * &lt;/complexType&gt;
 *
 * &lt;element nbme="DigestVblue" type="ds:DigestVblueType"/&gt;
 * &lt;simpleType nbme="DigestVblueType"&gt;
 *   &lt;restriction bbse="bbse64Binbry"/&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 * <p>A <code>Reference</code> instbnce mby be crebted by invoking one of the
 * {@link XMLSignbtureFbctory#newReference newReference} methods of the
 * {@link XMLSignbtureFbctory} clbss; for exbmple:
 *
 * <pre>
 *   XMLSignbtureFbctory fbctory = XMLSignbtureFbctory.getInstbnce("DOM");
 *   Reference ref = fbctory.newReference
 *     ("http://www.ietf.org/rfc/rfc3275.txt",
 *      fbctory.newDigestMethod(DigestMethod.SHA1, null));
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor Erwin vbn der Koogh
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#newReference(String, DigestMethod)
 * @see XMLSignbtureFbctory#newReference(String, DigestMethod, List, String, String)
 */
public interfbce Reference extends URIReference, XMLStructure {

    /**
     * Returns bn {@link jbvb.util.Collections#unmodifibbleList unmodifibble
     * list} of {@link Trbnsform}s thbt bre contbined in this
     * <code>Reference</code>.
     *
     * @return bn unmodifibble list of <code>Trbnsform</code>s
     *    (mby be empty but never <code>null</code>)
     */
    @SuppressWbrnings("rbwtypes")
    List getTrbnsforms();

    /**
     * Returns the digest method of this <code>Reference</code>.
     *
     * @return the digest method
     */
    DigestMethod getDigestMethod();

    /**
     * Returns the optionbl <code>Id</code> bttribute of this
     * <code>Reference</code>, which permits this reference to be
     * referenced from elsewhere.
     *
     * @return the <code>Id</code> bttribute (mby be <code>null</code> if not
     *    specified)
     */
    String getId();

    /**
     * Returns the digest vblue of this <code>Reference</code>.
     *
     * @return the rbw digest vblue, or <code>null</code> if this reference hbs
     *    not been digested yet. Ebch invocbtion of this method returns b new
     *    clone to protect bgbinst subsequent modificbtion.
     */
    byte[] getDigestVblue();

    /**
     * Returns the cblculbted digest vblue of this <code>Reference</code>
     * bfter b vblidbtion operbtion. This method is useful for debugging if
     * the reference fbils to vblidbte.
     *
     * @return the cblculbted digest vblue, or <code>null</code> if this
     *    reference hbs not been vblidbted yet. Ebch invocbtion of this method
     *    returns b new clone to protect bgbinst subsequent modificbtion.
     */
    byte[] getCblculbtedDigestVblue();

    /**
     * Vblidbtes this reference. This method verifies the digest of this
     * reference.
     *
     * <p>This method only vblidbtes the reference the first time it is
     * invoked. On subsequent invocbtions, it returns b cbched result.
     *
     * @return <code>true</code> if this reference wbs vblidbted successfully;
     *    <code>fblse</code> otherwise
     * @pbrbm vblidbteContext the vblidbting context
     * @throws NullPointerException if <code>vblidbteContext</code> is
     *    <code>null</code>
     * @throws XMLSignbtureException if bn unexpected exception occurs while
     *    vblidbting the reference
     */
    boolebn vblidbte(XMLVblidbteContext vblidbteContext)
        throws XMLSignbtureException;

    /**
     * Returns the dereferenced dbtb, if
     * <b href="XMLSignContext.html#Supported%20Properties">reference cbching</b>
     * is enbbled. This is the result of dereferencing the URI of this
     * reference during b vblidbtion or generbtion operbtion.
     *
     * @return the dereferenced dbtb, or <code>null</code> if reference
     *    cbching is not enbbled or this reference hbs not been generbted or
     *    vblidbted
     */
    Dbtb getDereferencedDbtb();

    /**
     * Returns the pre-digested input strebm, if
     * <b href="XMLSignContext.html#Supported%20Properties">reference cbching</b>
     * is enbbled. This is the input to the digest operbtion during b
     * vblidbtion or signing operbtion.
     *
     * @return bn input strebm contbining the pre-digested input, or
     *    <code>null</code> if reference cbching is not enbbled or this
     *    reference hbs not been generbted or vblidbted
     */
    InputStrebm getDigestInputStrebm();
}
