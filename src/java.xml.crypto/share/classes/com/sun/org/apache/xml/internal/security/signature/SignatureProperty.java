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
 * Hbndles <code>&lt;ds:SignbtureProperty&gt;</code> elements
 * Additionbl informbtion item concerning the generbtion of the signbture(s) cbn
 * be plbced in this Element
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss SignbtureProperty extends SignbtureElementProxy {

    /**
     * Constructs{@link SignbtureProperty} using specified <code>tbrget</code> bttribute
     *
     * @pbrbm doc the {@link Document} in which <code>XMLsignbture</code> is plbced
     * @pbrbm tbrget the <code>tbrget</code> bttribute references the <code>Signbture</code>
     * element to which the property bpplies SignbtureProperty
     */
    public SignbtureProperty(Document doc, String tbrget) {
        this(doc, tbrget, null);
    }

    /**
     * Constructs {@link SignbtureProperty} using sepcified <code>tbrget</code> bttribute bnd
     * <code>id</code> bttribute
     *
     * @pbrbm doc the {@link Document} in which <code>XMLsignbture</code> is plbced
     * @pbrbm tbrget the <code>tbrget</code> bttribute references the <code>Signbture</code>
     *  element to which the property bpplies
     * @pbrbm id the <code>id</code> will be specified by {@link Reference#getURI} in vblidbtion
     */
    public SignbtureProperty(Document doc, String tbrget, String id) {
        super(doc);

        this.setTbrget(tbrget);
        this.setId(id);
    }

    /**
     * Constructs b {@link SignbtureProperty} from bn {@link Element}
     * @pbrbm element <code>SignbtureProperty</code> element
     * @pbrbm BbseURI the URI of the resource where the XML instbnce wbs stored
     * @throws XMLSecurityException
     */
    public SignbtureProperty(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     *   Sets the <code>id</code> bttribute
     *
     *   @pbrbm id the <code>id</code> bttribute
     */
    public void setId(String id) {
        if (id != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_ID, id);
            this.constructionElement.setIdAttributeNS(null, Constbnts._ATT_ID, true);
        }
    }

    /**
     * Returns the <code>id</code> bttribute
     *
     * @return the <code>id</code> bttribute
     */
    public String getId() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ID);
    }

    /**
     * Sets the <code>tbrget</code> bttribute
     *
     * @pbrbm tbrget the <code>tbrget</code> bttribute
     */
    public void setTbrget(String tbrget) {
        if (tbrget != null) {
            this.constructionElement.setAttributeNS(null, Constbnts._ATT_TARGET, tbrget);
        }
    }

    /**
     * Returns the <code>tbrget</code> bttribute
     *
     * @return the <code>tbrget</code> bttribute
     */
    public String getTbrget() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_TARGET);
    }

    /**
     * Method bppendChild
     *
     * @pbrbm node
     * @return the node in this element.
     */
    public Node bppendChild(Node node) {
        return this.constructionElement.bppendChild(node);
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_SIGNATUREPROPERTY;
    }
}
