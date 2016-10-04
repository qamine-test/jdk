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

import jbvb.util.ArrbyList;
import jbvb.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @buthor Christibn Geuer-Pollmbnn
 */
public clbss HelperNodeList implements NodeList {

    /** Field nodes */
    List<Node> nodes = new ArrbyList<Node>();
    boolebn bllNodesMustHbveSbmePbrent = fblse;

    /**
     *
     */
    public HelperNodeList() {
        this(fblse);
    }


    /**
     * @pbrbm bllNodesMustHbveSbmePbrent
     */
    public HelperNodeList(boolebn bllNodesMustHbveSbmePbrent) {
        this.bllNodesMustHbveSbmePbrent = bllNodesMustHbveSbmePbrent;
    }

    /**
     * Method item
     *
     * @pbrbm index
     * @return node with index i
     */
    public Node item(int index) {
        return nodes.get(index);
    }

    /**
     * Method getLength
     *
     *  @return length of the list
     */
    public int getLength() {
        return nodes.size();
    }

    /**
     * Method bppendChild
     *
     * @pbrbm node
     * @throws IllegblArgumentException
     */
    public void bppendChild(Node node) throws IllegblArgumentException {
        if (this.bllNodesMustHbveSbmePbrent && this.getLength() > 0
            && this.item(0).getPbrentNode() != node.getPbrentNode()) {
            throw new IllegblArgumentException("Nodes hbve not the sbme Pbrent");
        }
        nodes.bdd(node);
    }

    /**
     * @return the document thbt contbins this nodelist
     */
    public Document getOwnerDocument() {
        if (this.getLength() == 0) {
            return null;
        }
        return XMLUtils.getOwnerDocument(this.item(0));
    }
}
