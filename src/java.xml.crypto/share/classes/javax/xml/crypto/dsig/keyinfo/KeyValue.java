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
 * $Id: KeyVblue.jbvb,v 1.4 2005/05/10 16:35:35 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto.dsig.keyinfo;

import jbvb.security.KeyException;
import jbvb.security.KeyStore;
import jbvb.security.PublicKey;
import jbvb.security.interfbces.DSAPublicKey;
import jbvb.security.interfbces.RSAPublicKey;
import jbvbx.xml.crypto.XMLStructure;

/**
 * A representbtion of the XML <code>KeyVblue</code> element bs defined
 * in the <b href="http://www.w3.org/TR/xmldsig-core/">
 * W3C Recommendbtion for XML-Signbture Syntbx bnd Processing</b>. A
 * <code>KeyVblue</code> object contbins b single public key thbt mby be
 * useful in vblidbting the signbture. The XML schemb definition is defined bs:
 *
 * <pre>
 *    &lt;element nbme="KeyVblue" type="ds:KeyVblueType"/&gt;
 *    &lt;complexType nbme="KeyVblueType" mixed="true"&gt;
 *      &lt;choice&gt;
 *        &lt;element ref="ds:DSAKeyVblue"/&gt;
 *        &lt;element ref="ds:RSAKeyVblue"/&gt;
 *        &lt;bny nbmespbce="##other" processContents="lbx"/&gt;
 *      &lt;/choice&gt;
 *    &lt;/complexType&gt;
 *
 *    &lt;element nbme="DSAKeyVblue" type="ds:DSAKeyVblueType"/&gt;
 *    &lt;complexType nbme="DSAKeyVblueType"&gt;
 *      &lt;sequence&gt;
 *        &lt;sequence minOccurs="0"&gt;
 *          &lt;element nbme="P" type="ds:CryptoBinbry"/&gt;
 *          &lt;element nbme="Q" type="ds:CryptoBinbry"/&gt;
 *        &lt;/sequence&gt;
 *        &lt;element nbme="G" type="ds:CryptoBinbry" minOccurs="0"/&gt;
 *        &lt;element nbme="Y" type="ds:CryptoBinbry"/&gt;
 *        &lt;element nbme="J" type="ds:CryptoBinbry" minOccurs="0"/&gt;
 *        &lt;sequence minOccurs="0"&gt;
 *          &lt;element nbme="Seed" type="ds:CryptoBinbry"/&gt;
 *          &lt;element nbme="PgenCounter" type="ds:CryptoBinbry"/&gt;
 *        &lt;/sequence&gt;
 *      &lt;/sequence&gt;
 *    &lt;/complexType&gt;
 *
 *    &lt;element nbme="RSAKeyVblue" type="ds:RSAKeyVblueType"/&gt;
 *    &lt;complexType nbme="RSAKeyVblueType"&gt;
 *      &lt;sequence&gt;
 *        &lt;element nbme="Modulus" type="ds:CryptoBinbry"/&gt;
 *        &lt;element nbme="Exponent" type="ds:CryptoBinbry"/&gt;
 *      &lt;/sequence&gt;
 *    &lt;/complexType&gt;
 * </pre>
 * A <code>KeyVblue</code> instbnce mby be crebted by invoking the
 * {@link KeyInfoFbctory#newKeyVblue newKeyVblue} method of the
 * {@link KeyInfoFbctory} clbss, bnd pbssing it b {@link
 * jbvb.security.PublicKey} representing the vblue of the public key. Here is
 * bn exbmple of crebting b <code>KeyVblue</code> from b {@link DSAPublicKey}
 * of b {@link jbvb.security.cert.Certificbte} stored in b
 * {@link jbvb.security.KeyStore}:
 * <pre>
 * KeyStore keyStore = KeyStore.getInstbnce(KeyStore.getDefbultType());
 * PublicKey dsbPublicKey = keyStore.getCertificbte("myDSASigningCert").getPublicKey();
 * KeyInfoFbctory fbctory = KeyInfoFbctory.getInstbnce("DOM");
 * KeyVblue keyVblue = fbctory.newKeyVblue(dsbPublicKey);
 * </pre>
 *
 * This clbss returns the <code>DSAKeyVblue</code> bnd
 * <code>RSAKeyVblue</code> elements bs objects of type
 * {@link DSAPublicKey} bnd {@link RSAPublicKey}, respectively. Note thbt not
 * bll of the fields in the schemb bre bccessible bs pbrbmeters of these
 * types.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 * @see KeyInfoFbctory#newKeyVblue(PublicKey)
 */
public interfbce KeyVblue extends XMLStructure {

    /**
     * URI identifying the DSA KeyVblue KeyInfo type:
     * http://www.w3.org/2000/09/xmldsig#DSAKeyVblue. This cbn be specified bs
     * the vblue of the <code>type</code> pbrbmeter of the
     * {@link RetrievblMethod} clbss to describe b remote
     * <code>DSAKeyVblue</code> structure.
     */
    finbl stbtic String DSA_TYPE =
        "http://www.w3.org/2000/09/xmldsig#DSAKeyVblue";

    /**
     * URI identifying the RSA KeyVblue KeyInfo type:
     * http://www.w3.org/2000/09/xmldsig#RSAKeyVblue. This cbn be specified bs
     * the vblue of the <code>type</code> pbrbmeter of the
     * {@link RetrievblMethod} clbss to describe b remote
     * <code>RSAKeyVblue</code> structure.
     */
    finbl stbtic String RSA_TYPE =
        "http://www.w3.org/2000/09/xmldsig#RSAKeyVblue";

    /**
     * Returns the public key of this <code>KeyVblue</code>.
     *
     * @return the public key of this <code>KeyVblue</code>
     * @throws KeyException if this <code>KeyVblue</code> cbnnot be converted
     *    to b <code>PublicKey</code>
     */
    PublicKey getPublicKey() throws KeyException;
}
