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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms.pbrbms;


import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformPbrbm;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * This Object serves both bs nbmespbce prefix resolver bnd bs contbiner for
 * the <CODE>ds:XPbth</CODE> Element. It implements the {@link org.w3c.dom.Element} interfbce
 * bnd cbn be used directly in b DOM tree.
 *
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss XPbthContbiner extends SignbtureElementProxy implements TrbnsformPbrbm {

    /**
     * Constructor XPbthContbiner
     *
     * @pbrbm doc
     */
    public XPbthContbiner(Document doc) {
        super(doc);
    }

    /**
     * Sets the TEXT vblue of the <CODE>ds:XPbth</CODE> Element.
     *
     * @pbrbm xpbth
     */
    public void setXPbth(String xpbth) {
        if (this.constructionElement.getChildNodes() != null) {
            NodeList nl = this.constructionElement.getChildNodes();

            for (int i = 0; i < nl.getLength(); i++) {
                this.constructionElement.removeChild(nl.item(i));
            }
        }

        Text xpbthText = this.doc.crebteTextNode(xpbth);
        this.constructionElement.bppendChild(xpbthText);
    }

    /**
     * Returns the TEXT vblue of the <CODE>ds:XPbth</CODE> Element.
     *
     * @return the TEXT vblue of the <CODE>ds:XPbth</CODE> Element.
     */
    public String getXPbth() {
        return this.getTextFromTextChild();
    }

    /** @inheritDoc */
    public String getBbseLocblNbme() {
        return Constbnts._TAG_XPATH;
    }
}
