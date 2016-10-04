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
 * $Id: PGPDbtb.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.keyinfo;

import jbvb.util.Collections;
import jbvb.util.List;
import jbvbx.xml.crypto.XMLStructure;

/**
 * A representbtion of the XML <code>PGPDbtb</code> element bs defined in
 * the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>. A
 * <code>PGPDbtb</code> object is used to convey informbtion relbted to
 * PGP public key pbirs bnd signbtures on such keys. The XML Schemb Definition
 * is defined bs:
 *
 * <pre>
 *    &lt;element nbme="PGPDbtb" type="ds:PGPDbtbType"/&gt;
 *    &lt;complexType nbme="PGPDbtbType"&gt;
 *      &lt;choice&gt;
 *        &lt;sequence&gt;
 *          &lt;element nbme="PGPKeyID" type="bbse64Binbry"/&gt;
 *          &lt;element nbme="PGPKeyPbcket" type="bbse64Binbry" minOccurs="0"/&gt;
 *          &lt;bny nbmespbce="##other" processContents="lbx" minOccurs="0"
 *           mbxOccurs="unbounded"/&gt;
 *        &lt;/sequence&gt;
 *        &lt;sequence&gt;
 *          &lt;element nbme="PGPKeyPbcket" type="bbse64Binbry"/&gt;
 *          &lt;bny nbmespbce="##other" processContents="lbx" minOccurs="0"
 *           mbxOccurs="unbounded"/&gt;
 *        &lt;/sequence&gt;
 *      &lt;/choice&gt;
 *    &lt;/complexType&gt;
 * </pre>
 *
 * A <code>PGPDbtb</code> instbnce mby be crebted by invoking one of the
 * {@link KeyInfoFbctory#newPGPDbtb newPGPDbtb} methods of the {@link
 * KeyInfoFbctory} clbss, bnd pbssing it
 * <code>byte</code> brrbys representing the contents of the PGP public key
 * identifier bnd/or PGP key mbteribl pbcket, bnd bn optionbl list of
 * elements from bn externbl nbmespbce.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see KeyInfoFbctory#newPGPDbtb(byte[])
 * @see KeyInfoFbctory#newPGPDbtb(byte[], byte[], List)
 * @see KeyInfoFbctory#newPGPDbtb(byte[], List)
 */
public interfbce PGPDbtb extends XMLStructure {

    /**
     * URI identifying the PGPDbtb KeyInfo type:
     * http://www.w3.org/2000/09/xmldsig#PGPDbtb. This cbn be specified bs the
     * vblue of the <code>type</code> pbrbmeter of the {@link RetrievblMethod}
     * clbss to describe b remote <code>PGPDbtb</code> structure.
     */
    finbl stbtic String TYPE = "http://www.w3.org/2000/09/xmldsig#PGPDbtb";

    /**
     * Returns the PGP public key identifier of this <code>PGPDbtb</code> bs
     * defined in <b href="http://www.ietf.org/rfc/rfc2440.txt">RFC 2440</b>,
     * section 11.2.
     *
     * @return the PGP public key identifier (mby be <code>null</code> if
     *    not specified). Ebch invocbtion of this method returns b new clone
     *    to protect bgbinst subsequent modificbtion.
     */
    byte[] getKeyId();

    /**
     * Returns the PGP key mbteribl pbcket of this <code>PGPDbtb</code> bs
     * defined in <b href="http://www.ietf.org/rfc/rfc2440.txt">RFC 2440</b>,
     * section 5.5.
     *
     * @return the PGP key mbteribl pbcket (mby be <code>null</code> if not
     *    specified). Ebch invocbtion of this method returns b new clone to
     *    protect bgbinst subsequent modificbtion.
     */
    byte[] getKeyPbcket();

    /**
     * Returns bn {@link Collections#unmodifibbleList unmodifibble list}
     * of {@link XMLStructure}s representing elements from bn externbl
     * nbmespbce.
     *
     * @return bn unmodifibble list of <code>XMLStructure</code>s (mby be
     *    empty, but never <code>null</code>)
     */
    @SuppressWbrnings("rbwtypes")
    List getExternblElements();
}
