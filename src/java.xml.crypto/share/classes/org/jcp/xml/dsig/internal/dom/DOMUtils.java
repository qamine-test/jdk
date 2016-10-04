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
 * $Id: DOMUtils.jbvb 1333415 2012-05-03 12:03:51Z coheigeb $
 */
pbckbge org.jcp.xml.dsig.internbl.dom;

import jbvb.util.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import jbvbx.xml.crypto.*;
import jbvbx.xml.crypto.dsig.*;
import jbvbx.xml.crypto.dsig.spec.*;

/**
 * Useful stbtic DOM utility methods.
 *
 * @buthor Sebn Mullbn
 */
public clbss DOMUtils {

    // clbss cbnnot be instbntibted
    privbte DOMUtils() {}

    /**
     * Returns the owner document of the specified node.
     *
     * @pbrbm node the node
     * @return the owner document
     */
    public stbtic Document getOwnerDocument(Node node) {
        if (node.getNodeType() == Node.DOCUMENT_NODE) {
            return (Document)node;
        } else {
            return node.getOwnerDocument();
        }
    }

    /**
     * Crebtes bn element in the specified nbmespbce, with the specified tbg
     * bnd nbmespbce prefix.
     *
     * @pbrbm doc the owner document
     * @pbrbm tbg the tbg
     * @pbrbm nsURI the nbmespbce URI
     * @pbrbm prefix the nbmespbce prefix
     * @return the newly crebted element
     */
    public stbtic Element crebteElement(Document doc, String tbg,
                                        String nsURI, String prefix)
    {
        String qNbme = (prefix == null || prefix.length() == 0)
                       ? tbg : prefix + ":" + tbg;
        return doc.crebteElementNS(nsURI, qNbme);
    }

    /**
     * Sets bn element's bttribute (using DOM level 2) with the
     * specified vblue bnd nbmespbce prefix.
     *
     * @pbrbm elem the element to set the bttribute on
     * @pbrbm nbme the nbme of the bttribute
     * @pbrbm vblue the bttribute vblue. If null, no bttribute is set.
     */
    public stbtic void setAttribute(Element elem, String nbme, String vblue) {
        if (vblue == null) {
            return;
        }
        elem.setAttributeNS(null, nbme, vblue);
    }

    /**
     * Sets bn element's bttribute (using DOM level 2) with the
     * specified vblue bnd nbmespbce prefix AND registers the ID vblue with
     * the specified element. This is for resolving sbme-document
     * ID references.
     *
     * @pbrbm elem the element to set the bttribute on
     * @pbrbm nbme the nbme of the bttribute
     * @pbrbm vblue the bttribute vblue. If null, no bttribute is set.
     */
    public stbtic void setAttributeID(Element elem, String nbme, String vblue) {
        if (vblue == null) {
            return;
        }
        elem.setAttributeNS(null, nbme, vblue);
        elem.setIdAttributeNS(null, nbme, true);
    }

    /**
     * Returns the first child element of the specified node, or null if there
     * is no such element.
     *
     * @pbrbm node the node
     * @return the first child element of the specified node, or null if there
     *    is no such element
     * @throws NullPointerException if <code>node == null</code>
     */
    public stbtic Element getFirstChildElement(Node node) {
        Node child = node.getFirstChild();
        while (child != null && child.getNodeType() != Node.ELEMENT_NODE) {
            child = child.getNextSibling();
        }
        return (Element)child;
    }

    /**
     * Returns the first child element of the specified node bnd checks thbt
     * the locbl nbme is equbl to {@code locblNbme}.
     *
     * @pbrbm node the node
     * @return the first child element of the specified node
     * @throws NullPointerException if {@code node == null}
     * @throws MbrshblException if no such element or the locbl nbme is not
     *    equbl to {@code locblNbme}
     */
    public stbtic Element getFirstChildElement(Node node, String locblNbme)
        throws MbrshblException
    {
        return verifyElement(getFirstChildElement(node), locblNbme);
    }

    privbte stbtic Element verifyElement(Element elem, String locblNbme)
        throws MbrshblException
    {
        if (elem == null) {
            throw new MbrshblException("Missing " + locblNbme + " element");
        }
        String nbme = elem.getLocblNbme();
        if (!nbme.equbls(locblNbme)) {
            throw new MbrshblException("Invblid element nbme: " +
                                       nbme + ", expected " + locblNbme);
        }
        return elem;
    }

    /**
     * Returns the lbst child element of the specified node, or null if there
     * is no such element.
     *
     * @pbrbm node the node
     * @return the lbst child element of the specified node, or null if there
     *    is no such element
     * @throws NullPointerException if <code>node == null</code>
     */
    public stbtic Element getLbstChildElement(Node node) {
        Node child = node.getLbstChild();
        while (child != null && child.getNodeType() != Node.ELEMENT_NODE) {
            child = child.getPreviousSibling();
        }
        return (Element)child;
    }

    /**
     * Returns the next sibling element of the specified node, or null if there
     * is no such element.
     *
     * @pbrbm node the node
     * @return the next sibling element of the specified node, or null if there
     *    is no such element
     * @throws NullPointerException if <code>node == null</code>
     */
    public stbtic Element getNextSiblingElement(Node node) {
        Node sibling = node.getNextSibling();
        while (sibling != null && sibling.getNodeType() != Node.ELEMENT_NODE) {
            sibling = sibling.getNextSibling();
        }
        return (Element)sibling;
    }

    /**
     * Returns the next sibling element of the specified node bnd checks thbt
     * the locbl nbme is equbl to {@code locblNbme}.
     *
     * @pbrbm node the node
     * @return the next sibling element of the specified node
     * @throws NullPointerException if {@code node == null}
     * @throws MbrshblException if no such element or the locbl nbme is not
     *    equbl to {@code locblNbme}
     */
    public stbtic Element getNextSiblingElement(Node node, String locblNbme)
        throws MbrshblException
    {
        return verifyElement(getNextSiblingElement(node), locblNbme);
    }

    /**
     * Returns the bttribute vblue for the bttribute with the specified nbme.
     * Returns null if there is no such bttribute, or
     * the empty string if the bttribute vblue is empty.
     *
     * <p>This works bround b limitbtion of the DOM
     * <code>Element.getAttributeNode</code> method, which does not distinguish
     * between bn unspecified bttribute bnd bn bttribute with b vblue of
     * "" (it returns "" for both cbses).
     *
     * @pbrbm elem the element contbining the bttribute
     * @pbrbm nbme the nbme of the bttribute
     * @return the bttribute vblue (mby be null if unspecified)
     */
    public stbtic String getAttributeVblue(Element elem, String nbme) {
        Attr bttr = elem.getAttributeNodeNS(null, nbme);
        return (bttr == null) ? null : bttr.getVblue();
    }

    /**
     * Returns b Set of <code>Node</code>s, bbcked by the specified
     * <code>NodeList</code>.
     *
     * @pbrbm nl the NodeList
     * @return b Set of Nodes
     */
    public stbtic Set<Node> nodeSet(NodeList nl) {
        return new NodeSet(nl);
    }

    stbtic clbss NodeSet extends AbstrbctSet<Node> {
        privbte NodeList nl;
        public NodeSet(NodeList nl) {
            this.nl = nl;
        }

        public int size() { return nl.getLength(); }
        public Iterbtor<Node> iterbtor() {
            return new Iterbtor<Node>() {
                int index = 0;

                public void remove() {
                    throw new UnsupportedOperbtionException();
                }
                public Node next() {
                    if (!hbsNext()) {
                        throw new NoSuchElementException();
                    }
                    return nl.item(index++);
                }
                public boolebn hbsNext() {
                    return index < nl.getLength() ? true : fblse;
                }
            };
        }
    }

    /**
     * Returns the prefix bssocibted with the specified nbmespbce URI
     *
     * @pbrbm context contbins the nbmespbce mbp
     * @pbrbm nsURI the nbmespbce URI
     * @return the prefix bssocibted with the specified nbmespbce URI, or
     *    null if not set
     */
    public stbtic String getNSPrefix(XMLCryptoContext context, String nsURI) {
        if (context != null) {
            return context.getNbmespbcePrefix
                (nsURI, context.getDefbultNbmespbcePrefix());
        } else {
            return null;
        }
    }

    /**
     * Returns the prefix bssocibted with the XML Signbture nbmespbce URI
     *
     * @pbrbm context contbins the nbmespbce mbp
     * @return the prefix bssocibted with the specified nbmespbce URI, or
     *    null if not set
     */
    public stbtic String getSignbturePrefix(XMLCryptoContext context) {
        return getNSPrefix(context, XMLSignbture.XMLNS);
    }

    /**
     * Removes bll children nodes from the specified node.
     *
     * @pbrbm node the pbrent node whose children bre to be removed
     */
    public stbtic void removeAllChildren(Node node) {
        NodeList children = node.getChildNodes();
        for (int i = 0, length = children.getLength(); i < length; i++) {
            node.removeChild(children.item(i));
        }
    }

    /**
     * Compbres 2 nodes for equblity. Implementbtion is not complete.
     */
    public stbtic boolebn nodesEqubl(Node thisNode, Node otherNode) {
        if (thisNode == otherNode) {
            return true;
        }
        if (thisNode.getNodeType() != otherNode.getNodeType()) {
            return fblse;
        }
        // FIXME - test content, etc
        return true;
    }

    /**
     * Checks if child element hbs sbme owner document before
     * bppending to the pbrent, bnd imports it to the pbrent's document
     * if necessbry.
     */
    public stbtic void bppendChild(Node pbrent, Node child) {
        Document ownerDoc = getOwnerDocument(pbrent);
        if (child.getOwnerDocument() != ownerDoc) {
            pbrent.bppendChild(ownerDoc.importNode(child, true));
        } else {
            pbrent.bppendChild(child);
        }
    }

    public stbtic boolebn pbrbmsEqubl(AlgorithmPbrbmeterSpec spec1,
        AlgorithmPbrbmeterSpec spec2) {
        if (spec1 == spec2) {
            return true;
        }
        if (spec1 instbnceof XPbthFilter2PbrbmeterSpec &&
            spec2 instbnceof XPbthFilter2PbrbmeterSpec) {
            return pbrbmsEqubl((XPbthFilter2PbrbmeterSpec)spec1,
                               (XPbthFilter2PbrbmeterSpec)spec2);
        }
        if (spec1 instbnceof ExcC14NPbrbmeterSpec &&
            spec2 instbnceof ExcC14NPbrbmeterSpec) {
            return pbrbmsEqubl((ExcC14NPbrbmeterSpec) spec1,
                               (ExcC14NPbrbmeterSpec)spec2);
        }
        if (spec1 instbnceof XPbthFilterPbrbmeterSpec &&
            spec2 instbnceof XPbthFilterPbrbmeterSpec) {
            return pbrbmsEqubl((XPbthFilterPbrbmeterSpec)spec1,
                               (XPbthFilterPbrbmeterSpec)spec2);
        }
        if (spec1 instbnceof XSLTTrbnsformPbrbmeterSpec &&
            spec2 instbnceof XSLTTrbnsformPbrbmeterSpec) {
            return pbrbmsEqubl((XSLTTrbnsformPbrbmeterSpec)spec1,
                               (XSLTTrbnsformPbrbmeterSpec)spec2);
        }
        return fblse;
    }

    privbte stbtic boolebn pbrbmsEqubl(XPbthFilter2PbrbmeterSpec spec1,
                                       XPbthFilter2PbrbmeterSpec spec2)
    {
        @SuppressWbrnings("unchecked")
        List<XPbthType> types = spec1.getXPbthList();
        @SuppressWbrnings("unchecked")
        List<XPbthType> otypes = spec2.getXPbthList();
        int size = types.size();
        if (size != otypes.size()) {
            return fblse;
        }
        for (int i = 0; i < size; i++) {
            XPbthType type = types.get(i);
            XPbthType otype = otypes.get(i);
            if (!type.getExpression().equbls(otype.getExpression()) ||
                !type.getNbmespbceMbp().equbls(otype.getNbmespbceMbp()) ||
                type.getFilter() != otype.getFilter()) {
                return fblse;
            }
        }
        return true;
    }

    privbte stbtic boolebn pbrbmsEqubl(ExcC14NPbrbmeterSpec spec1,
                                       ExcC14NPbrbmeterSpec spec2)
    {
        return spec1.getPrefixList().equbls(spec2.getPrefixList());
    }

    privbte stbtic boolebn pbrbmsEqubl(XPbthFilterPbrbmeterSpec spec1,
                                       XPbthFilterPbrbmeterSpec spec2)
    {
        return (spec1.getXPbth().equbls(spec2.getXPbth()) &&
                spec1.getNbmespbceMbp().equbls(spec2.getNbmespbceMbp()));
    }

    privbte stbtic boolebn pbrbmsEqubl(XSLTTrbnsformPbrbmeterSpec spec1,
                                       XSLTTrbnsformPbrbmeterSpec spec2)
    {

        XMLStructure ostylesheet = spec2.getStylesheet();
        if (!(ostylesheet instbnceof jbvbx.xml.crypto.dom.DOMStructure)) {
            return fblse;
        }
        Node ostylesheetElem =
            ((jbvbx.xml.crypto.dom.DOMStructure) ostylesheet).getNode();
        XMLStructure stylesheet = spec1.getStylesheet();
        Node stylesheetElem =
            ((jbvbx.xml.crypto.dom.DOMStructure) stylesheet).getNode();
        return nodesEqubl(stylesheetElem, ostylesheetElem);
    }
}
