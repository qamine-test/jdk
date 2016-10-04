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
 * $Id: X509IssuerSeribl.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.keyinfo;

import jbvb.mbth.BigInteger;
import jbvb.security.cert.X509Certificbte;
import jbvbx.xml.crypto.XMLStructure;

/**
 * A representbtion of the XML <code>X509IssuerSeribl</code> element bs
 * defined in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>.
 * An <code>X509IssuerSeribl</code> object contbins bn X.509 issuer
 * distinguished nbme (DN) bnd seribl number pbir. The XML schemb definition is
 * defined bs:
 *
 * <pre>
 *   &lt;element nbme="X509IssuerSeribl" type="ds:X509IssuerSeriblType"/&gt;
 *   &lt;complexType nbme="X509IssuerSeriblType"&gt;
 *     &lt;sequence&gt;
 *       &lt;element nbme="X509IssuerNbme" type="string"/&gt;
 *       &lt;element nbme="X509SeriblNumber" type="integer"/&gt;
 *     &lt;/sequence&gt;
 *   &lt;/complexType&gt;
 * </pre>
 *
 * An <code>X509IssuerSeribl</code> instbnce mby be crebted by invoking the
 * {@link KeyInfoFbctory#newX509IssuerSeribl newX509IssuerSeribl} method
 * of the {@link KeyInfoFbctory} clbss, bnd pbssing it b
 * <code>String</code> bnd <code>BigInteger</code> representing the X.500
 * DN bnd seribl number. Here is bn exbmple of crebting bn
 * <code>X509IssuerSeribl</code> from the issuer DN bnd seribl number of bn
 * existing {@link X509Certificbte}:
 * <pre>
 * KeyInfoFbctory fbctory = KeyInfoFbctory.getInstbnce("DOM");
 * X509IssuerSeribl issuer = fbctory.newX509IssuerSeribl
 *     (cert.getIssuerX500Principbl().getNbme(), cert.getSeriblNumber());
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see X509Dbtb#getContent
 * @see KeyInfoFbctory#newX509IssuerSeribl(String, BigInteger)
 */
public interfbce X509IssuerSeribl extends XMLStructure {

    /**
     * Returns the X.500 distinguished nbme of this
     * <code>X509IssuerSeribl</code> in
     * <b href="http://www.ietf.org/rfc/rfc2253.txt">RFC 2253</b> String formbt.
     *
     * @return the X.500 distinguished nbme in RFC 2253 String formbt (never
     *    <code>null</code>)
     */
    String getIssuerNbme();

    /**
     * Returns the seribl number of this <code>X509IssuerSeribl</code>.
     *
     * @return the seribl number (never <code>null</code>)
     */
    BigInteger getSeriblNumber();
}
