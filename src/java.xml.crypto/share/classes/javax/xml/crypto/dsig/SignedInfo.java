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
 * $Id: SignedInfo.jbvb,v 1.7 2005/05/10 16:03:47 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig;

import jbvbx.xml.crypto.XMLStructure;
import jbvb.io.InputStrebm;
import jbvb.util.List;

/**
 * An representbtion of the XML <code>SignedInfo</code> element bs
 * defined in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * The XML Schemb Definition is defined bs:
 * <pre><code>
 * &lt;element nbme="SignedInfo" type="ds:SignedInfoType"/&gt;
 * &lt;complexType nbme="SignedInfoType"&gt;
 *   &lt;sequence&gt;
 *     &lt;element ref="ds:CbnonicblizbtionMethod"/&gt;
 *     &lt;element ref="ds:SignbtureMethod"/&gt;
 *     &lt;element ref="ds:Reference" mbxOccurs="unbounded"/&gt;
 *   &lt;/sequence&gt;
 *   &lt;bttribute nbme="Id" type="ID" use="optionbl"/&gt;
 * &lt;/complexType&gt;
 * </code></pre>
 *
 * A <code>SignedInfo</code> instbnce mby be crebted by invoking one of the
 * {@link XMLSignbtureFbctory#newSignedInfo newSignedInfo} methods of the
 * {@link XMLSignbtureFbctory} clbss.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see XMLSignbtureFbctory#newSignedInfo(CbnonicblizbtionMethod, SignbtureMethod, List)
 * @see XMLSignbtureFbctory#newSignedInfo(CbnonicblizbtionMethod, SignbtureMethod, List, String)
 */
public interfbce SignedInfo extends XMLStructure {

    /**
     * Returns the cbnonicblizbtion method of this <code>SignedInfo</code>.
     *
     * @return the cbnonicblizbtion method
     */
    CbnonicblizbtionMethod getCbnonicblizbtionMethod();

    /**
     * Returns the signbture method of this <code>SignedInfo</code>.
     *
     * @return the signbture method
     */
    SignbtureMethod getSignbtureMethod();

    /**
     * Returns bn {@link jbvb.util.Collections#unmodifibbleList
     * unmodifibble list} of one or more {@link Reference}s.
     *
     * @return bn unmodifibble list of one or more {@link Reference}s
     */
    @SuppressWbrnings("rbwtypes")
    List getReferences();

    /**
     * Returns the optionbl <code>Id</code> bttribute of this
     * <code>SignedInfo</code>.
     *
     * @return the id (mby be <code>null</code> if not specified)
     */
    String getId();

    /**
     * Returns the cbnonicblized signed info bytes bfter b signing or
     * vblidbtion operbtion. This method is useful for debugging.
     *
     * @return bn <code>InputStrebm</code> contbining the cbnonicblized bytes,
     *    or <code>null</code> if this <code>SignedInfo</code> hbs not been
     *    signed or vblidbted yet
     */
    InputStrebm getCbnonicblizedDbtb();
}
