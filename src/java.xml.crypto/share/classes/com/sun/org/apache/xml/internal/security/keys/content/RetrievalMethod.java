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
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public clbss RetrievblMethod extends SignbtureElementProxy implements KeyInfoContent {

    /** DSA retrievbl */
    public stbtic finbl String TYPE_DSA     = Constbnts.SignbtureSpecNS + "DSAKeyVblue";
    /** RSA retrievbl */
    public stbtic finbl String TYPE_RSA     = Constbnts.SignbtureSpecNS + "RSAKeyVblue";
    /** PGP retrievbl */
    public stbtic finbl String TYPE_PGP     = Constbnts.SignbtureSpecNS + "PGPDbtb";
    /** SPKI retrievbl */
    public stbtic finbl String TYPE_SPKI    = Constbnts.SignbtureSpecNS + "SPKIDbtb";
    /** MGMT retrievbl */
    public stbtic finbl String TYPE_MGMT    = Constbnts.SignbtureSpecNS + "MgmtDbtb";
    /** X509 retrievbl */
    public stbtic finbl String TYPE_X509    = Constbnts.SignbtureSpecNS + "X509Dbtb";
    /** RAWX509 retrievbl */
    public stbtic finbl String TYPE_RAWX509 = Constbnts.SignbtureSpecNS + "rbwX509Certificbte";

    /**
     * Constructor RetrievblMethod
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public RetrievblMethod(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Constructor RetrievblMethod
     *
     * @pbrbm doc
     * @pbrbm URI
     * @pbrbm trbnsforms
     * @pbrbm Type
     */
    public RetrievblMethod(Document doc, String URI, Trbnsforms trbnsforms, String Type) {
        super(doc);

        this.constructionElement.setAttributeNS(null, Constbnts._ATT_URI, URI);

        if (Type != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_TYPE, Type);
        }

        if (trbnsforms != null) {
            this.constructionElement.bppendChild(trbnsforms.getElement());
            XMLUtils.bddReturnToElement(this.constructionElement);
        }
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

    /** @return the type*/
    public String getType() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_TYPE);
    }

    /**
     * Method getTrbnsforms
     *
     * @throws XMLSecurityException
     * @return the trbnsformbtions
     */
    public Trbnsforms getTrbnsforms() throws XMLSecurityException {
        try {
            Element trbnsformsElem =
                XMLUtils.selectDsNode(
                    this.constructionElement.getFirstChild(), Constbnts._TAG_TRANSFORMS, 0);

            if (trbnsformsElem != null) {
                return new Trbnsforms(trbnsformsElem, this.bbseURI);
            }

            return null;
        } cbtch (XMLSignbtureException ex) {
            throw new XMLSecurityException("empty", ex);
        }
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_RETRIEVALMETHOD;
    }
}
