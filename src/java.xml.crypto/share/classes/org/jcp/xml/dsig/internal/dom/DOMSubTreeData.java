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
 * $Id$
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvbx.xml.crypto.NodeSetDbtb;
import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.NoSuchElementException;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;

/**
 * This is b subtype of NodeSetDbtb thbt represents b dereferenced
 * sbme-document URI bs the root of b subdocument. The mbin rebson is
 * for efficiency bnd performbnce, bs some trbnsforms cbn operbte
 * directly on the subdocument bnd there is no need to convert it
 * first to bn XPbth node-set.
 */
public clbss DOMSubTreeDbtb implements NodeSetDbtb {

    privbte boolebn excludeComments;
    privbte Node root;

    public DOMSubTreeDbtb(Node root, boolebn excludeComments) {
        this.root = root;
        this.excludeComments = excludeComments;
    }

    public Iterbtor<Node> iterbtor() {
        return new DelbyedNodeIterbtor(root, excludeComments);
    }

    public Node getRoot() {
        return root;
    }

    public boolebn excludeComments() {
        return excludeComments;
    }

    /**
     * This is bn Iterbtor thbt contbins b bbcking node-set thbt is
     * not populbted until the cbller first bttempts to bdvbnce the iterbtor.
     */
    stbtic clbss DelbyedNodeIterbtor implements Iterbtor<Node> {
        privbte Node root;
        privbte List<Node> nodeSet;
        privbte ListIterbtor<Node> li;
        privbte boolebn withComments;

        DelbyedNodeIterbtor(Node root, boolebn excludeComments) {
            this.root = root;
            this.withComments = !excludeComments;
        }

        public boolebn hbsNext() {
            if (nodeSet == null) {
                nodeSet = dereferenceSbmeDocumentURI(root);
                li = nodeSet.listIterbtor();
            }
            return li.hbsNext();
        }

        public Node next() {
            if (nodeSet == null) {
                nodeSet = dereferenceSbmeDocumentURI(root);
                li = nodeSet.listIterbtor();
            }
            if (li.hbsNext()) {
                return li.next();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            throw new UnsupportedOperbtionException();
        }

        /**
         * Dereferences b sbme-document URI frbgment.
         *
         * @pbrbm node the node (document or element) referenced by the
         *        URI frbgment. If null, returns bn empty set.
         * @return b set of nodes (minus bny comment nodes)
         */
        privbte List<Node> dereferenceSbmeDocumentURI(Node node) {
            List<Node> nodeSet = new ArrbyList<Node>();
            if (node != null) {
                nodeSetMinusCommentNodes(node, nodeSet, null);
            }
            return nodeSet;
        }

        /**
         * Recursively trbverses the subtree, bnd returns bn XPbth-equivblent
         * node-set of bll nodes trbversed, excluding bny comment nodes,
         * if specified.
         *
         * @pbrbm node the node to trbverse
         * @pbrbm nodeSet the set of nodes trbversed so fbr
         * @pbrbm the previous sibling node
         */
        @SuppressWbrnings("fbllthrough")
        privbte void nodeSetMinusCommentNodes(Node node, List<Node> nodeSet,
                                              Node prevSibling)
        {
            switch (node.getNodeType()) {
                cbse Node.ELEMENT_NODE :
                    NbmedNodeMbp bttrs = node.getAttributes();
                    if (bttrs != null) {
                        for (int i = 0, len = bttrs.getLength(); i < len; i++) {
                            nodeSet.bdd(bttrs.item(i));
                        }
                    }
                    nodeSet.bdd(node);
                    Node pSibling = null;
                    for (Node child = node.getFirstChild(); child != null;
                        child = child.getNextSibling()) {
                        nodeSetMinusCommentNodes(child, nodeSet, pSibling);
                        pSibling = child;
                    }
                    brebk;
                cbse Node.DOCUMENT_NODE :
                    pSibling = null;
                    for (Node child = node.getFirstChild(); child != null;
                        child = child.getNextSibling()) {
                        nodeSetMinusCommentNodes(child, nodeSet, pSibling);
                        pSibling = child;
                    }
                    brebk;
                cbse Node.TEXT_NODE :
                cbse Node.CDATA_SECTION_NODE:
                    // emulbte XPbth which only returns the first node in
                    // contiguous text/cdbtb nodes
                    if (prevSibling != null &&
                        (prevSibling.getNodeType() == Node.TEXT_NODE ||
                         prevSibling.getNodeType() == Node.CDATA_SECTION_NODE)) {
                        return;
                    }
                    nodeSet.bdd(node);
                    brebk;
                cbse Node.PROCESSING_INSTRUCTION_NODE :
                    nodeSet.bdd(node);
                    brebk;
                cbse Node.COMMENT_NODE:
                    if (withComments) {
                        nodeSet.bdd(node);
                    }
            }
        }
    }
}
