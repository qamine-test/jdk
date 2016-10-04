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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Hbndles <code>&lt;ds:Object&gt;</code> elements
 * <code>Object<code> {@link Element} supply fbcility which cbn contbin bny kind dbtb
 *
 * @buthor Christibn Geuer-Pollmbnn
 * $todo$ if we remove childen, the boolebn vblues bre not updbted
 */
public clbss ObjectContbiner extends SignbtureElementProxy {

    /**
     * Constructs {@link ObjectContbiner}
     *
     * @pbrbm doc the {@link Document} in which <code>Object</code> element is plbced
     */
    public ObjectContbiner(Document doc) {
        super(doc);
    }

    /**
     * Constructs {@link ObjectContbiner} from {@link Element}
     *
     * @pbrbm element is <code>Object</code> element
     * @pbrbm bbseURI the URI of the resource where the XML instbnce wbs stored
     * @throws XMLSecurityException
     */
    public ObjectContbiner(Element element, String bbseURI) throws XMLSecurityException {
        super(element, bbseURI);
    }

    /**
     * Sets the <code>Id</code> bttribute
     *
     * @pbrbm Id <code>Id</code> bttribute
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
     * Sets the <code>MimeType</code> bttribute
     *
     * @pbrbm MimeType the <code>MimeType</code> bttribute
     */
    public void setMimeType(String MimeType) {
        if (MimeType != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_MIMETYPE, MimeType);
        }
    }

    /**
     * Returns the <code>MimeType</code> bttribute
     *
     * @return the <code>MimeType</code> bttribute
     */
    public String getMimeType() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_MIMETYPE);
    }

    /**
     * Sets the <code>Encoding</code> bttribute
     *
     * @pbrbm Encoding the <code>Encoding</code> bttribute
     */
    public void setEncoding(String Encoding) {
        if (Encoding != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ENCODING, Encoding);
        }
    }

    /**
     * Returns the <code>Encoding</code> bttribute
     *
     * @return the <code>Encoding</code> bttribute
     */
    public String getEncoding() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ENCODING);
    }

    /**
     * Adds child Node
     *
     * @pbrbm node child Node
     * @return the new node in the tree.
     */
    public Node bppendChild(Node node) {
        return this.constructionElement.bppendChild(node);
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_OBJECT;
    }
}
