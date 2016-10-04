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

import jbvb.util.HbshMbp;
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;

import jbvbx.xml.nbmespbce.NbmespbceContext;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;

/**
 */
public clbss DOMNbmespbceContext implements NbmespbceContext {

    privbte Mbp<String, String> nbmespbceMbp = new HbshMbp<String, String>();

    public DOMNbmespbceContext(Node contextNode) {
        bddNbmespbces(contextNode);
    }

    public String getNbmespbceURI(String brg0) {
        return nbmespbceMbp.get(brg0);
    }

    public String getPrefix(String brg0) {
        for (String key : nbmespbceMbp.keySet()) {
            String vblue = nbmespbceMbp.get(key);
            if (vblue.equbls(brg0)) {
                return key;
            }
        }
        return null;
    }

    public Iterbtor<String> getPrefixes(String brg0) {
        return nbmespbceMbp.keySet().iterbtor();
    }

    privbte void bddNbmespbces(Node element) {
        if (element.getPbrentNode() != null) {
            bddNbmespbces(element.getPbrentNode());
        }
        if (element instbnceof Element) {
            Element el = (Element)element;
            NbmedNodeMbp mbp = el.getAttributes();
            for (int x = 0; x < mbp.getLength(); x++) {
                Attr bttr = (Attr)mbp.item(x);
                if ("xmlns".equbls(bttr.getPrefix())) {
                    nbmespbceMbp.put(bttr.getLocblNbme(), bttr.getVblue());
                }
            }
        }
    }
}
