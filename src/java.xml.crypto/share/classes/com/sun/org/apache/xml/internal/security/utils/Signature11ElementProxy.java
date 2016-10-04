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
pbckbge com.sun.org.bpbche.xml.internbl.security.utils;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Clbss SignbtureElementProxy
 *
 * @buthor Brent Putmbn (putmbnb@georgetown.edu)
 */
public bbstrbct clbss Signbture11ElementProxy extends ElementProxy {

    protected Signbture11ElementProxy() {
    };

    /**
     * Constructor Signbture11ElementProxy
     *
     * @pbrbm doc
     */
    public Signbture11ElementProxy(Document doc) {
        if (doc == null) {
            throw new RuntimeException("Document is null");
        }

        this.doc = doc;
        this.constructionElement =
            XMLUtils.crebteElementInSignbture11Spbce(this.doc, this.getBbseLocblNbme());
    }

    /**
     * Constructor Signbture11ElementProxy
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public Signbture11ElementProxy(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);

    }

    /** @inheritDoc */
    public String getBbseNbmespbce() {
        return Constbnts.SignbtureSpec11NS;
    }
}
