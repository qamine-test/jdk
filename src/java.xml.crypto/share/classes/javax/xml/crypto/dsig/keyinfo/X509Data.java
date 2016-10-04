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
 * $Id: X509Dbtb.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.keyinfo;

import jbvbx.xml.crypto.XMLStructure;
import jbvb.security.cert.X509CRL;
import jbvb.util.List;

/**
 * A representbtion of the XML <code>X509Dbtb</code> element bs defined in
 * the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>. An
 * <code>X509Dbtb</code> object contbins one or more identifers of keys
 * or X.509 certificbtes (or certificbtes' identifiers or b revocbtion list).
 * The XML Schemb Definition is defined bs:
 *
 * <pre>
 *    &lt;element nbme="X509Dbtb" type="ds:X509DbtbType"/&gt;
 *    &lt;complexType nbme="X509DbtbType"&gt;
 *        &lt;sequence mbxOccurs="unbounded"&gt;
 *          &lt;choice&gt;
 *            &lt;element nbme="X509IssuerSeribl" type="ds:X509IssuerSeriblType"/&gt;
 *            &lt;element nbme="X509SKI" type="bbse64Binbry"/&gt;
 *            &lt;element nbme="X509SubjectNbme" type="string"/&gt;
 *            &lt;element nbme="X509Certificbte" type="bbse64Binbry"/&gt;
 *            &lt;element nbme="X509CRL" type="bbse64Binbry"/&gt;
 *            &lt;bny nbmespbce="##other" processContents="lbx"/&gt;
 *          &lt;/choice&gt;
 *        &lt;/sequence&gt;
 *    &lt;/complexType&gt;
 *
 *    &lt;complexType nbme="X509IssuerSeriblType"&gt;
 *      &lt;sequence&gt;
 *        &lt;element nbme="X509IssuerNbme" type="string"/&gt;
 *        &lt;element nbme="X509SeriblNumber" type="integer"/&gt;
 *      &lt;/sequence&gt;
 *    &lt;/complexType&gt;
 * </pre>
 *
 * An <code>X509Dbtb</code> instbnce mby be crebted by invoking the
 * {@link KeyInfoFbctory#newX509Dbtb newX509Dbtb} methods of the
 * {@link KeyInfoFbctory} clbss bnd pbssing it b list of one or more
 * {@link XMLStructure}s representing X.509 content; for exbmple:
 * <pre>
 *   KeyInfoFbctory fbctory = KeyInfoFbctory.getInstbnce("DOM");
 *   X509Dbtb x509Dbtb = fbctory.newX509Dbtb
 *       (Collections.singletonList("cn=Alice"));
 * </pre>
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see KeyInfoFbctory#newX509Dbtb(List)
 */
//@@@ check for illegbl combinbtions of dbtb violbting MUSTs in W3c spec
public interfbce X509Dbtb extends XMLStructure {

    /**
     * URI identifying the X509Dbtb KeyInfo type:
     * http://www.w3.org/2000/09/xmldsig#X509Dbtb. This cbn be specified bs
     * the vblue of the <code>type</code> pbrbmeter of the
     * {@link RetrievblMethod} clbss to describe b remote
     * <code>X509Dbtb</code> structure.
     */
    finbl stbtic String TYPE = "http://www.w3.org/2000/09/xmldsig#X509Dbtb";

    /**
     * URI identifying the binbry (ASN.1 DER) X.509 Certificbte KeyInfo type:
     * http://www.w3.org/2000/09/xmldsig#rbwX509Certificbte. This cbn be
     * specified bs the vblue of the <code>type</code> pbrbmeter of the
     * {@link RetrievblMethod} clbss to describe b remote X509 Certificbte.
     */
    finbl stbtic String RAW_X509_CERTIFICATE_TYPE =
        "http://www.w3.org/2000/09/xmldsig#rbwX509Certificbte";

    /**
     * Returns bn {@link jbvb.util.Collections#unmodifibbleList unmodifibble
     * list} of the content in this <code>X509Dbtb</code>. Vblid types bre
     * {@link String} (subject nbmes), <code>byte[]</code> (subject key ids),
     * {@link jbvb.security.cert.X509Certificbte}, {@link X509CRL},
     * or {@link XMLStructure} ({@link X509IssuerSeribl}
     * objects or elements from bn externbl nbmespbce).
     *
     * @return bn unmodifibble list of the content in this <code>X509Dbtb</code>
     *    (never <code>null</code> or empty)
     */
    @SuppressWbrnings("rbwtypes")
    List getContent();
}
