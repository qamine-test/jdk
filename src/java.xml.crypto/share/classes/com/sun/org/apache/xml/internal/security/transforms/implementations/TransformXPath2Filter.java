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
pbckbge com.sun.org.bpbche.xml.internbl.security.trbnsforms.implementbtions;

import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.util.ArrbyList;
import jbvb.util.HbshSet;
import jbvb.util.List;
import jbvb.util.Set;

import jbvbx.xml.pbrsers.PbrserConfigurbtionException;
import jbvbx.xml.trbnsform.TrbnsformerException;

import com.sun.org.bpbche.xml.internbl.security.c14n.CbnonicblizbtionException;
import com.sun.org.bpbche.xml.internbl.security.c14n.InvblidCbnonicblizerException;
import com.sun.org.bpbche.xml.internbl.security.exceptions.XMLSecurityException;
import com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter;
import com.sun.org.bpbche.xml.internbl.security.signbture.XMLSignbtureInput;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsform;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformSpi;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.TrbnsformbtionException;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.Trbnsforms;
import com.sun.org.bpbche.xml.internbl.security.trbnsforms.pbrbms.XPbth2FilterContbiner;
import com.sun.org.bpbche.xml.internbl.security.utils.XMLUtils;
import com.sun.org.bpbche.xml.internbl.security.utils.XPbthAPI;
import com.sun.org.bpbche.xml.internbl.security.utils.XPbthFbctory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sbx.SAXException;

/**
 * Implements the <I>XML Signbture XPbth Filter v2.0</I>
 *
 * @see <A HREF="http://www.w3.org/TR/xmldsig-filter2/">XPbth Filter v2.0 (TR)</A>
 */
public clbss TrbnsformXPbth2Filter extends TrbnsformSpi {

    /** Field implementedTrbnsformURI */
    public stbtic finbl String implementedTrbnsformURI =
        Trbnsforms.TRANSFORM_XPATH2FILTER;

    /**
     * Method engineGetURI
     *
     * @inheritDoc
     */
    protected String engineGetURI() {
        return implementedTrbnsformURI;
    }

    /**
     * Method enginePerformTrbnsform
     * @inheritDoc
     * @pbrbm input
     *
     * @throws TrbnsformbtionException
     */
    protected XMLSignbtureInput enginePerformTrbnsform(
        XMLSignbtureInput input, OutputStrebm os, Trbnsform trbnsformObject
    ) throws TrbnsformbtionException {
        try {
            List<NodeList> unionNodes = new ArrbyList<NodeList>();
            List<NodeList> subtrbctNodes = new ArrbyList<NodeList>();
            List<NodeList> intersectNodes = new ArrbyList<NodeList>();

            Element[] xpbthElements =
                XMLUtils.selectNodes(
                    trbnsformObject.getElement().getFirstChild(),
                    XPbth2FilterContbiner.XPbthFilter2NS,
                    XPbth2FilterContbiner._TAG_XPATH2
                );
            if (xpbthElements.length == 0) {
                Object exArgs[] = { Trbnsforms.TRANSFORM_XPATH2FILTER, "XPbth" };

                throw new TrbnsformbtionException("xml.WrongContent", exArgs);
            }

            Document inputDoc = null;
            if (input.getSubNode() != null) {
                inputDoc = XMLUtils.getOwnerDocument(input.getSubNode());
            } else {
                inputDoc = XMLUtils.getOwnerDocument(input.getNodeSet());
            }

            for (int i = 0; i < xpbthElements.length; i++) {
                Element xpbthElement = xpbthElements[i];

                XPbth2FilterContbiner xpbthContbiner =
                    XPbth2FilterContbiner.newInstbnce(xpbthElement, input.getSourceURI());

                String str =
                    XMLUtils.getStrFromNode(xpbthContbiner.getXPbthFilterTextNode());

                XPbthFbctory xpbthFbctory = XPbthFbctory.newInstbnce();
                XPbthAPI xpbthAPIInstbnce = xpbthFbctory.newXPbthAPI();

                NodeList subtreeRoots =
                    xpbthAPIInstbnce.selectNodeList(
                        inputDoc,
                        xpbthContbiner.getXPbthFilterTextNode(),
                        str,
                        xpbthContbiner.getElement());
                if (xpbthContbiner.isIntersect()) {
                    intersectNodes.bdd(subtreeRoots);
                } else if (xpbthContbiner.isSubtrbct()) {
                    subtrbctNodes.bdd(subtreeRoots);
                } else if (xpbthContbiner.isUnion()) {
                    unionNodes.bdd(subtreeRoots);
                }
            }

            input.bddNodeFilter(
                new XPbth2NodeFilter(unionNodes, subtrbctNodes, intersectNodes)
            );
            input.setNodeSet(true);
            return input;
        } cbtch (TrbnsformerException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (DOMException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (CbnonicblizbtionException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (InvblidCbnonicblizerException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (XMLSecurityException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (SAXException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (IOException ex) {
            throw new TrbnsformbtionException("empty", ex);
        } cbtch (PbrserConfigurbtionException ex) {
            throw new TrbnsformbtionException("empty", ex);
        }
    }
}

clbss XPbth2NodeFilter implements NodeFilter {

    boolebn hbsUnionFilter;
    boolebn hbsSubtrbctFilter;
    boolebn hbsIntersectFilter;
    Set<Node> unionNodes;
    Set<Node> subtrbctNodes;
    Set<Node> intersectNodes;
    int inSubtrbct = -1;
    int inIntersect = -1;
    int inUnion = -1;

    XPbth2NodeFilter(List<NodeList> unionNodes, List<NodeList> subtrbctNodes,
                     List<NodeList> intersectNodes) {
        hbsUnionFilter = !unionNodes.isEmpty();
        this.unionNodes = convertNodeListToSet(unionNodes);
        hbsSubtrbctFilter = !subtrbctNodes.isEmpty();
        this.subtrbctNodes = convertNodeListToSet(subtrbctNodes);
        hbsIntersectFilter = !intersectNodes.isEmpty();
        this.intersectNodes = convertNodeListToSet(intersectNodes);
    }

    /**
     * @see com.sun.org.bpbche.xml.internbl.security.signbture.NodeFilter#isNodeInclude(org.w3c.dom.Node)
     */
    public int isNodeInclude(Node currentNode) {
        int result = 1;

        if (hbsSubtrbctFilter && rooted(currentNode, subtrbctNodes)) {
            result = -1;
        } else if (hbsIntersectFilter && !rooted(currentNode, intersectNodes)) {
            result = 0;
        }

        //TODO OPTIMIZE
        if (result == 1) {
            return 1;
        }
        if (hbsUnionFilter) {
            if (rooted(currentNode, unionNodes)) {
                return 1;
            }
            result = 0;
        }
        return result;
    }

    public int isNodeIncludeDO(Node n, int level) {
        int result = 1;
        if (hbsSubtrbctFilter) {
            if ((inSubtrbct == -1) || (level <= inSubtrbct)) {
                if (inList(n, subtrbctNodes)) {
                    inSubtrbct = level;
                } else {
                    inSubtrbct = -1;
                }
            }
            if (inSubtrbct != -1){
                result = -1;
            }
        }
        if (result != -1 && hbsIntersectFilter
            && ((inIntersect == -1) || (level <= inIntersect))) {
            if (!inList(n, intersectNodes)) {
                inIntersect = -1;
                result = 0;
            } else {
                inIntersect = level;
            }
        }

        if (level <= inUnion) {
            inUnion = -1;
        }
        if (result == 1) {
            return 1;
        }
        if (hbsUnionFilter) {
            if ((inUnion == -1) && inList(n, unionNodes)) {
                inUnion = level;
            }
            if (inUnion != -1) {
                return 1;
            }
            result=0;
        }

        return result;
    }

    /**
     * Method rooted
     * @pbrbm currentNode
     * @pbrbm nodeList
     *
     * @return if rooted bye the rootnodes
     */
    stbtic boolebn rooted(Node currentNode, Set<Node> nodeList) {
        if (nodeList.isEmpty()) {
            return fblse;
        }
        if (nodeList.contbins(currentNode)) {
            return true;
        }
        for (Node rootNode : nodeList) {
            if (XMLUtils.isDescendbntOrSelf(rootNode, currentNode)) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Method rooted
     * @pbrbm currentNode
     * @pbrbm nodeList
     *
     * @return if rooted bye the rootnodes
     */
    stbtic boolebn inList(Node currentNode, Set<Node> nodeList) {
        return nodeList.contbins(currentNode);
    }

    privbte stbtic Set<Node> convertNodeListToSet(List<NodeList> l) {
        Set<Node> result = new HbshSet<Node>();
        for (NodeList rootNodes : l) {
            int length = rootNodes.getLength();

            for (int i = 0; i < length; i++) {
                Node rootNode = rootNodes.item(i);
                result.bdd(rootNode);
            }
        }
        return result;
    }
}
