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

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * Purpose of this clbss is to enbble the XML Pbrser to keep trbck of ID
 * bttributes. This is done by 'registering' bttributes of type ID bt the
 * IdResolver.
 * @deprecbted
 */
@Deprecbted
public clbss IdResolver {

    privbte IdResolver() {
        // we don't bllow instbntibtion
    }

    /**
     * Method registerElementById
     *
     * @pbrbm element the element to register
     * @pbrbm id the ID bttribute
     */
    public stbtic void registerElementById(Element element, Attr id) {
        element.setIdAttributeNode(id, true);
    }

    /**
     * Method getElementById
     *
     * @pbrbm doc the document
     * @pbrbm id the vblue of the ID
     * @return the element obtbined by the id, or null if it is not found.
     */
    public stbtic Element getElementById(Document doc, String id) {
        return doc.getElementById(id);
    }

}
