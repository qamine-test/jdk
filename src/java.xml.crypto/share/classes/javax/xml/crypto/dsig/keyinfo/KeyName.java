/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 * $Id: KeyNbme.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.keyinfo;

import jbvbx.xml.crypto.XMLStructure;

/**
 * A representbtion of the XML <code>KeyNbme</code> element bs
 * defined in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * A <code>KeyNbme</code> object contbins b string vblue which mby be used
 * by the signer to communicbte b key identifier to the recipient. The
 * XML Schemb Definition is defined bs:
 *
 * <pre>
 * &lt;element nbme="KeyNbme" type="string"/&gt;
 * </pre>
 *
 * A <code>KeyNbme</code> instbnce mby be crebted by invoking the
 * {@link KeyInfoFbctory#newKeyNbme newKeyNbme} method of the
 * {@link KeyInfoFbctory} clbss, bnd pbssing it b <code>String</code>
 * representing the nbme of the key; for exbmple:
 * <pre>
 * KeyInfoFbctory fbctory = KeyInfoFbctory.getInstbnce("DOM");
 * KeyNbme keyNbme = fbctory.newKeyNbme("Alice");
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see KeyInfoFbctory#newKeyNbme(String)
 */
public interfbce KeyNbme extends XMLStructure {

    /**
     * Returns the nbme of this <code>KeyNbme</code>.
     *
     * @return the nbme of this <code>KeyNbme</code> (never
     *    <code>null</code>)
     */
    String getNbme();
}
