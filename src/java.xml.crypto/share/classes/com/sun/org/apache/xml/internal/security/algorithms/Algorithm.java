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
pbckbge com.sun.org.bpbche.xml.internbl.security.blgorithms;

import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.utils.Constbnts;
import com.sun.org.bpbche.xml.internbl.security.utils.SignbtureElementProxy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * The Algorithm clbss which stores the Algorithm URI bs b string.
 */
public bbstrbct clbss Algorithm extends SignbtureElementProxy {

    /**
     *
     * @pbrbm doc
     * @pbrbm blgorithmURI is the URI of the blgorithm bs String
     */
    public Algorithm(Document doc, String blgorithmURI) {
        super(doc);

        this.setAlgorithmURI(blgorithmURI);
    }

    /**
     * Constructor Algorithm
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public Algorithm(Element element, String BbseURI) throws XMLSecurityException {
        super(element, BbseURI);
    }

    /**
     * Method getAlgorithmURI
     *
     * @return The URI of the blgorithm
     */
    public String getAlgorithmURI() {
        return this.constructionElement.getAttributeNS(null, Constbnts._ATT_ALGORITHM);
    }

    /**
     * Sets the blgorithm's URI bs used in the signbture.
     *
     * @pbrbm blgorithmURI is the URI of the blgorithm bs String
     */
    protected void setAlgorithmURI(String blgorithmURI) {
        if (blgorithmURI != null) {
            this.constructionElement.setAttributeNS(
                null, Constbnts._ATT_ALGORITHM, blgorithmURI
            );
        }
    }
}
