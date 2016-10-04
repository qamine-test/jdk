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

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.Signbture11ElementProxy;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Provides content model support for the <code>dsig11:KeyInfoReference</code> element.
 *
 * @buthor Brent Putmbn (putmbnb@georgetown.edu)
 */
public clbss KeyInfoReference extends Signbture11ElementProxy implements KeyInfoContent {

    /**
     * Constructor RetrievblMethod
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public KeyInfoReference(Element element, String bbseURI) throws XMLSecurityException {
        super(element, bbseURI);
    }

    /**
     * Constructor RetrievblMethod
     *
     * @pbrbm doc
     * @pbrbm URI
     */
    public KeyInfoReference(Document doc, String URI) {
        super(doc);

        this.constructionElement.setAttributeNS(null, Constbnts._ATT_URI, URI);
    }

    /**
     * Method getURIAttr
     *
     * @return the URI bttribute
     */
    public Attr getURIAttr() {
        return this.constructionElement.getAttributeNodeNS(null, Constbnts._ATT_URI);
    }

    /**
     * Method getURI
     *
     * @return URI string
     */
    public String getURI() {
        return this.getURIAttr().getNodeVblue();
    }

    /**
     * Sets the <code>Id</code> bttribute
     *
     * @pbrbm Id ID
     */
    public void setId(String id) {
        if (id != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ID, id);
            this.constructionElement.setIdAttributeNS(null, Constbnts._ATT_ID, true);
        } else {
            this.constructionElement.removeAttributeNS(null, Constbnts._ATT_ID);
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

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_KEYINFOREFERENCE;
    }
}
