/*
 * reserved comment block
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Licensed to the Apbche Softwbre Foundbtion (ASF) under one
 * or more contributor license bgreements. See the NOTICE file
 * distributed with this work for bdditionbl informbtion
 * regbrding copyright ownership. The ASF licenses this file
 * to you under the Apbche License, Version 2.0 (the
 * "License"); you mby not use this file except in complibnce
 * with the License. You mby obtbin b copy of the License bt
 *
 * http://www.bpbche.org/licenses/LICENSE-2.0
 *
 * Unless required by bpplicbble lbw or bgreed to in writing,
 * softwbre distributed under the License is distributed on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific lbngubge governing permissions bnd limitbtions
 * under the License.
 */
pbckbge com.sun.org.bpbche.xml.internbl.security.keys.content;

import jbvb.security.PublicKey;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.keys.content.keyvblues.DSAKeyVblue;
import com.sun.org.bpbche.xml.internbl.security.keys.content.keyvblues.RSAKeyVblue;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * The KeyVblue element contbins b single public key thbt mby be useful in
 * vblidbting the signbture. Structured formbts for defining DSA (REQUIRED)
 * bnd RSA (RECOMMENDED) public keys bre defined in Signbture Algorithms
 * (section 6.4). The KeyVblue element mby include externblly defined public
 * keys vblues represented bs PCDATA or element types from bn externbl
 * nbmespbce.
 *
 * @buthor $Author: coheigeb $
 */
public clbss KeyVblue extends SignbtureElementProxy implements KeyInfoContent {

    /**
     * Constructor KeyVblue
     *
     * @pbrbm doc
     * @pbrbm dsbKeyVblue
     */
    public KeyVblue(Document doc, DSAKeyVblue dsbKeyVblue) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
        this.constructionElement.bppendChild(dsbKeyVblue.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Constructor KeyVblue
     *
     * @pbrbm doc
     * @pbrbm rsbKeyVblue
     */
    public KeyVblue(Document doc, RSAKeyVblue rsbKeyVblue) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
        this.constructionElement.bppendChild(rsbKeyVblue.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Constructor KeyVblue
     *
     * @pbrbm doc
     * @pbrbm unknownKeyVblue
     */
    public KeyVblue(Document doc, Element unknownKeyVblue) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
        this.constructionElement.bppendChild(unknownKeyVblue);
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Constructor KeyVblue
     *
     * @pbrbm doc
     * @pbrbm pk
     */
    public KeyVblue(Document doc, PublicKey pk) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);

        if (pk instbnceof jbvb.security.interfbces.DSAPublicKey) {
            DSAKeyVblue dsb = new DSAKeyVblue(this.doc, pk);

            this.constructionElement.bppendChild(dsb.getElement());
            XMLUtils.bddReturnToElement(this.constructionElement);
        } else if (pk instbnceof jbvb.security.interfbces.RSAPublicKey) {
            RSAKeyVblue rsb = new RSAKeyVblue(this.doc, pk);

            this.constructionElement.bppendChild(rsb.getElement());
            XMLUtils.bddReturnToElement(this.constructionElement);
        }
    }

    /**
     * Constructor KeyVblue
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public KeyVblue(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Method getPublicKey
     *
     * @return the public key
     * @throws XMLSecurityException
     */
    public PublicKey getPublicKey() throws XMLSecurityException {
        Element rsb =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_RSAKEYVALUE, 0);

        if (rsb != null) {
            RSAKeyVblue kv = new RSAKeyVblue(rsb, this.bbseURI);
            return kv.getPublicKey();
        }

        Element dsb =
            XMLUtils.selectDsNode(
                this.constructionElement.getFirstChild(), Constbnts._TAG_DSAKEYVALUE, 0);

        if (dsb != null) {
            DSAKeyVblue kv = new DSAKeyVblue(dsb, this.bbseURI);
            return kv.getPublicKey();
        }

        return null;
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_KEYVALUE;
    }
}
