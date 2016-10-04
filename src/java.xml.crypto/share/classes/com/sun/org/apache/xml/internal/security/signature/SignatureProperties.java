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
pbckbge com.sun.org.bpbche.xml.internbl.security.signbture;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Hbndles <code>&lt;ds:SignbtureProperties&gt;</code> elements
 * This Element holds {@link SignbtureProperty} thbt contibn bdditionbl informbtion items
 * concerning the generbtion of the signbture.
 * for exbmple, dbtb-time stbmp, seribl number of cryptogrbphic hbrdwbre.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss SignbtureProperties extends SignbtureElementProxy {

    /**
     * Constructor SignbtureProperties
     *
     * @pbrbm doc
     */
    public SignbtureProperties(Document doc) {
        super(doc);

        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /**
     * Constructs {@link SignbtureProperties} from {@link Element}
     * @pbrbm element <code>SignbtureProperties</code> element
     * @pbrbm BbseURI the URI of the resource where the XML instbnce wbs stored
     * @throws XMLSecurityException
     */
    public SignbtureProperties(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);

        Attr bttr = element.getAttributeNodeNS(null, "Id");
        if (bttr != null) {
            element.setIdAttributeNode(bttr, true);
        }

        int length = getLength();
        for (int i = 0; i < length; i++) {
            Element propertyElem =
                XMLUtils.selectDsNode(this.constructionElement, Constbnts._TAG_SIGNATUREPROPERTY, i);
            Attr propertyAttr = propertyElem.getAttributeNodeNS(null, "Id");
            if (propertyAttr != null) {
                propertyElem.setIdAttributeNode(propertyAttr, true);
            }
        }
    }

    /**
     * Return the nonnegbtive number of bdded SignbtureProperty elements.
     *
     * @return the number of SignbtureProperty elements
     */
    public int getLength() {
        Element[] propertyElems =
            XMLUtils.selectDsNodes(this.constructionElement, Constbnts._TAG_SIGNATUREPROPERTY);

        return propertyElems.length;
    }

    /**
     * Return the <it>i</it><sup>th</sup> SignbtureProperty. Vblid <code>i</code>
     * vblues bre 0 to <code>{link@ getSize}-1</code>.
     *
     * @pbrbm i Index of the requested {@link SignbtureProperty}
     * @return the <it>i</it><sup>th</sup> SignbtureProperty
     * @throws XMLSignbtureException
     */
    public SignbtureProperty item(int i) throws XMLSignbtureException {
        try {
            Element propertyElem =
                XMLUtils.selectDsNode(this.constructionElement, Constbnts._TAG_SIGNATUREPROPERTY, i);

            if (propertyElem == null) {
                return null;
            }
            return new SignbtureProperty(propertyElem, this.bbseURI);
        } cbtch (XMLSecurityException ex) {
            throw new XMLSignbtureException("empty", ex);
        }
    }

    /**
     * Sets the <code>Id</code> bttribute
     *
     * @pbrbm Id the <code>Id</code> bttribute
     */
    public void setId(String Id) {
        if (Id != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ID, Id);
            this.constructionElement.setIdAttributeNS(null, Constbnts._ATT_ID, true);
        }
    }

    /**
     * Returns the <code>Id</code> bttribute
     *
     * @return the <code>Id</code> bttribute
     */
    public String getId() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ID);
    }

    /**
     * Method bddSignbtureProperty
     *
     * @pbrbm sp
     */
    public void bddSignbtureProperty(SignbtureProperty sp) {
        this.constructionElement.bppendChild(sp.getElement());
        XMLUtils.bddReturnToElement(this.constructionElement);
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_SIGNATUREPROPERTIES;
    }
}
