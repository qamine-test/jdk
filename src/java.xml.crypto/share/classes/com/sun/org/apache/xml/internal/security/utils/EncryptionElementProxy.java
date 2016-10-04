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
 * This is the bbse object for bll objects which mbp directly to bn Element from
 * the xenc spec.
 *
 * @buthor $Author: coheigeb $
 */
public bbstrbct clbss EncryptionElementProxy extends ElementProxy {

    /**
     * Constructor EncryptionElementProxy
     *
     * @pbrbm doc
     */
    public EncryptionElementProxy(Document doc) {
        super(doc);
    }

    /**
     * Constructor EncryptionElementProxy
     *
     * @pbrbm element
     * @pbrbm BbseURI
     * @throws XMLSecurityException
     */
    public EncryptionElementProxy(Element element, String BbseURI)
        throws XMLSecurityException {
        super(element, BbseURI);
    }

    /** @inheritDoc */
    public finbl String getBbseNbmespbce() {
        return EncryptionConstbnts.EncryptionSpecNS;
    }
}
