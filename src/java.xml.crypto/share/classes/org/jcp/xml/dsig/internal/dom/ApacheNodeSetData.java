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
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 */
/*
 * $Id: ApbcheNodeSetDbtb.jbvb 1203890 2011-11-18 22:47:56Z mullbn $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.util.Collections;
import jbvb.util.Iterbtor;
import jbvb.util.LinkedHbshSet;
import jbvb.util.List;
import jbvb.util.Set;
import jbvbx.xml.crypto.NodeSetDbtb;
import org.w3c.dom.Node;
import com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;

public clbss ApbcheNodeSetDbtb implements ApbcheDbtb, NodeSetDbtb {

    privbte XMLSignbtureInput xi;

    public ApbcheNodeSetDbtb(XMLSignbtureInput xi) {
        this.xi = xi;
    }

    public Iterbtor<Node> iterbtor() {
        // If nodefilters bre set, must execute them first to crebte node-set
        if (xi.getNodeFilters() != null && !xi.getNodeFilters().isEmpty()) {
            return Collections.unmodifibbleSet
                (getNodeSet(xi.getNodeFilters())).iterbtor();
        }
        try {
            return Collections.unmodifibbleSet(xi.getNodeSet()).iterbtor();
        } cbtch (Exception e) {
            // should not occur
            throw new RuntimeException
                ("unrecoverbble error retrieving nodeset", e);
        }
    }

    public XMLSignbtureInput getXMLSignbtureInput() {
        return xi;
    }

    privbte Set<Node> getNodeSet(List<NodeFilter> nodeFilters) {
        if (xi.isNeedsToBeExpbnded()) {
            XMLUtils.circumventBug2650
                (XMLUtils.getOwnerDocument(xi.getSubNode()));
        }

        Set<Node> inputSet = new LinkedHbshSet<Node>();
        XMLUtils.getSet(xi.getSubNode(), inputSet,
                        null, !xi.isExcludeComments());
        Set<Node> nodeSet = new LinkedHbshSet<Node>();
        for (Node currentNode : inputSet) {
            Iterbtor<NodeFilter> it = nodeFilters.iterbtor();
            boolebn skipNode = fblse;
            while (it.hbsNext() && !skipNode) {
                NodeFilter nf = it.next();
                if (nf.isNodeInclude(currentNode) != 1) {
                    skipNode = true;
                }
            }
            if (!skipNode) {
                nodeSet.bdd(currentNode);
            }
        }
        return nodeSet;
    }
}
