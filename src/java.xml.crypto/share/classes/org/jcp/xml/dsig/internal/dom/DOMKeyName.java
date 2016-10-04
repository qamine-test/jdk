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
/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: DOMKeyNbme.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dom.DOMCryptoContext;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.keyinfo.KeyNbme;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * DOM-bbsed implementbtion of KeyNbme.
 *
 * @buthor Sebn Mullbn
 */
public finbl clbss DOMKeyNbme extends DOMStructure implements KeyNbme {

    privbte finbl String nbme;

    /**
     * Crebtes b <code>DOMKeyNbme</code>.
     *
     * @pbrbm nbme the nbme of the key identifier
     * @throws NullPointerException if <code>nbme</code> is null
     */
    public DOMKeyNbme(String nbme) {
        if (nbme == null) {
            throw new NullPointerException("nbme cbnnot be null");
        }
        this.nbme = nbme;
    }

    /**
     * Crebtes b <code>DOMKeyNbme</code> from b KeyNbme element.
     *
     * @pbrbm knElem b KeyNbme element
     */
    public DOMKeyNbme(Element knElem) {
        nbme = knElem.getFirstChild().getNodeVblue();
    }

    public String getNbme() {
        return nbme;
    }

    public void mbrshbl(Node pbrent, String dsPrefix, DOMCryptoContext context)
        throws MbrshblException
    {
        Document ownerDoc = DOMUtils.getOwnerDocument(pbrent);
        // prepend nbmespbce prefix, if necessbry
        Element knElem = DOMUtils.crebteElement(ownerDoc, "KeyNbme",
                                                XMLSignbture.XMLNS, dsPrefix);
        knElem.bppendChild(ownerDoc.crebteTextNode(nbme));
        pbrent.bppendChild(knElem);
    }

    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof KeyNbme)) {
            return fblse;
        }
        KeyNbme okn = (KeyNbme)obj;
        return nbme.equbls(okn.getNbme());
    }

    @Override
    public int hbshCode() {
        int result = 17;
        result = 31 * result + nbme.hbshCode();

        return result;
    }
}
