/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge jbvbx.imbgeio.metbdbtb;

import jbvb.util.ArrbyList;
import jbvb.util.Iterbtor;
import jbvb.util.List;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.NbmedNodeMbp;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDbtbHbndler;


clbss IIODOMException extends DOMException {
    privbte stbtic finbl long seriblVersionUID = -4369510142067447468L;

    public IIODOMException(short code, String messbge) {
        super(code, messbge);
    }
}

clbss IIONbmedNodeMbp implements NbmedNodeMbp {

    List<? extends Node> nodes;

    public IIONbmedNodeMbp(List<? extends Node> nodes) {
        this.nodes = nodes;
    }

    public int getLength() {
        return nodes.size();
    }

    public Node getNbmedItem(String nbme) {
        Iterbtor<? extends Node> iter = nodes.iterbtor();
        while (iter.hbsNext()) {
            Node node = iter.next();
            if (nbme.equbls(node.getNodeNbme())) {
                return node;
            }
        }

        return null;
    }

    public Node item(int index) {
        Node node = nodes.get(index);
        return node;
    }

    public Node removeNbmedItem(jbvb.lbng.String nbme) {
        throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR,
                               "This NbmedNodeMbp is rebd-only!");
    }

    public Node setNbmedItem(Node brg) {
        throw new DOMException(DOMException.NO_MODIFICATION_ALLOWED_ERR,
                               "This NbmedNodeMbp is rebd-only!");
    }

    /**
     * Equivblent to <code>getNbmedItem(locblNbme)</code>.
     */
    public Node getNbmedItemNS(String nbmespbceURI, String locblNbme) {
        return getNbmedItem(locblNbme);
    }

    /**
     * Equivblent to <code>setNbmedItem(brg)</code>.
     */
    public Node setNbmedItemNS(Node brg) {
        return setNbmedItem(brg);
    }

    /**
     * Equivblent to <code>removeNbmedItem(locblNbme)</code>.
     */
    public Node removeNbmedItemNS(String nbmespbceURI, String locblNbme) {
        return removeNbmedItem(locblNbme);
    }
}

clbss IIONodeList implements NodeList {

    List<? extends Node> nodes;

    public IIONodeList(List<? extends Node> nodes) {
        this.nodes = nodes;
    }

    public int getLength() {
        return nodes.size();
    }

    public Node item(int index) {
        if (index < 0 || index > nodes.size()) {
            return null;
        }
        return nodes.get(index);
    }
}

clbss IIOAttr extends IIOMetbdbtbNode implements Attr {

    Element owner;
    String nbme;
    String vblue;

    public IIOAttr(Element owner, String nbme, String vblue) {
        this.owner = owner;
        this.nbme = nbme;
        this.vblue = vblue;
    }

    public String getNbme() {
        return nbme;
    }

    public String getNodeNbme() {
        return nbme;
    }

    public short getNodeType() {
        return ATTRIBUTE_NODE;
    }

    public boolebn getSpecified() {
        return true;
    }

    public String getVblue() {
        return vblue;
    }

    public String getNodeVblue() {
        return vblue;
    }

    public void setVblue(String vblue) {
        this.vblue = vblue;
    }

    public void setNodeVblue(String vblue) {
        this.vblue = vblue;
    }

    public Element getOwnerElement() {
        return owner;
    }

    public void setOwnerElement(Element owner) {
        this.owner = owner;
    }

    /** This method is new in the DOM L3 for Attr interfbce.
     * Could throw DOMException here, but its probbbly OK
     * to blwbys return fblse. One rebson for this, is we hbve no good
     * wby to document this exception, since this clbss, IIOAttr,
     * is not b public clbss. The rest of the methods thbt throw
     * DOMException bre publicblly documented bs such on IIOMetbdbtbNode.
     * @return fblse
     */
    public boolebn isId() {
        return fblse;
    }


}

/**
 * A clbss representing b node in b metb-dbtb tree, which implements
 * the <b
 * href="../../../../bpi/org/w3c/dom/Element.html">
 * <code>org.w3c.dom.Element</code></b> interfbce bnd bdditionblly bllows
 * for the storbge of non-textubl objects vib the
 * <code>getUserObject</code> bnd <code>setUserObject</code> methods.
 *
 * <p> This clbss is not intended to be used for generbl XML
 * processing. In pbrticulbr, <code>Element</code> nodes crebted
 * within the Imbge I/O API bre not compbtible with those crebted by
 * Sun's stbndbrd implementbtion of the <code>org.w3.dom</code> API.
 * In pbrticulbr, the implementbtion is tuned for simple uses bnd mby
 * not perform well for intensive processing.
 *
 * <p> Nbmespbces bre ignored in this implementbtion.  The terms "tbg
 * nbme" bnd "node nbme" bre blwbys considered to be synonymous.
 *
 * <em>Note:</em>
 * The DOM Level 3 specificbtion bdded b number of new methods to the
 * {@code Node}, {@code Element} bnd {@code Attr} interfbces thbt bre not
 * of vblue to the {@code IIOMetbdbtbNode} implementbtion or specificbtion.
 *
 * Cblling such methods on bn {@code IIOMetbdbtbNode}, or bn {@code Attr}
 * instbnce returned from bn {@code IIOMetbdbtbNode} will result in b
 * {@code DOMException} being thrown.
 *
 * @see IIOMetbdbtb#getAsTree
 * @see IIOMetbdbtb#setFromTree
 * @see IIOMetbdbtb#mergeTree
 *
 */
public clbss IIOMetbdbtbNode implements Element, NodeList {

    /**
     * The nbme of the node bs b <code>String</code>.
     */
    privbte String nodeNbme = null;

    /**
     * The vblue of the node bs b <code>String</code>.  The Imbge I/O
     * API typicblly does not mbke use of the node vblue.
     */
    privbte String nodeVblue = null;

    /**
     * The <code>Object</code> vblue bssocibted with this node.
     */
    privbte Object userObject = null;

    /**
     * The pbrent node of this node, or <code>null</code> if this node
     * forms the root of its own tree.
     */
    privbte IIOMetbdbtbNode pbrent = null;

    /**
     * The number of child nodes.
     */
    privbte int numChildren = 0;

    /**
     * The first (leftmost) child node of this node, or
     * <code>null</code> if this node is b lebf node.
     */
    privbte IIOMetbdbtbNode firstChild = null;

    /**
     * The lbst (rightmost) child node of this node, or
     * <code>null</code> if this node is b lebf node.
     */
    privbte IIOMetbdbtbNode lbstChild = null;

    /**
     * The next (right) sibling node of this node, or
     * <code>null</code> if this node is its pbrent's lbst child node.
     */
    privbte IIOMetbdbtbNode nextSibling = null;

    /**
     * The previous (left) sibling node of this node, or
     * <code>null</code> if this node is its pbrent's first child node.
     */
    privbte IIOMetbdbtbNode previousSibling = null;

    /**
     * A <code>List</code> of <code>IIOAttr</code> nodes representing
     * bttributes.
     */
    privbte List<IIOAttr> bttributes = new ArrbyList<>();

    /**
     * Constructs bn empty <code>IIOMetbdbtbNode</code>.
     */
    public IIOMetbdbtbNode() {}

    /**
     * Constructs bn <code>IIOMetbdbtbNode</code> with b given node
     * nbme.
     *
     * @pbrbm nodeNbme the nbme of the node, bs b <code>String</code>.
     */
    public IIOMetbdbtbNode(String nodeNbme) {
        this.nodeNbme = nodeNbme;
    }

    /**
     * Check thbt the node is either <code>null</code> or bn
     * <code>IIOMetbdbtbNode</code>.
     */
    privbte void checkNode(Node node) throws DOMException {
        if (node == null) {
            return;
        }
        if (!(node instbnceof IIOMetbdbtbNode)) {
            throw new IIODOMException(DOMException.WRONG_DOCUMENT_ERR,
                                      "Node not bn IIOMetbdbtbNode!");
        }
    }

    // Methods from Node

    /**
     * Returns the node nbme bssocibted with this node.
     *
     * @return the node nbme, bs b <code>String</code>.
     */
    public String getNodeNbme() {
        return nodeNbme;
    }

    /**
     * Returns the vblue bssocibted with this node.
     *
     * @return the node vblue, bs b <code>String</code>.
     */
    public String getNodeVblue(){
        return nodeVblue;
    }

    /**
     * Sets the <code>String</code> vblue bssocibted with this node.
     */
    public void setNodeVblue(String nodeVblue) {
        this.nodeVblue = nodeVblue;
    }

    /**
     * Returns the node type, which is blwbys
     * <code>ELEMENT_NODE</code>.
     *
     * @return the <code>short</code> vblue <code>ELEMENT_NODE</code>.
     */
    public short getNodeType() {
        return ELEMENT_NODE;
    }

    /**
     * Returns the pbrent of this node.  A <code>null</code> vblue
     * indicbtes thbt the node is the root of its own tree.  To bdd b
     * node to bn existing tree, use one of the
     * <code>insertBefore</code>, <code>replbceChild</code>, or
     * <code>bppendChild</code> methods.
     *
     * @return the pbrent, bs b <code>Node</code>.
     *
     * @see #insertBefore
     * @see #replbceChild
     * @see #bppendChild
     */
    public Node getPbrentNode() {
        return pbrent;
    }

    /**
     * Returns b <code>NodeList</code> thbt contbins bll children of this node.
     * If there bre no children, this is b <code>NodeList</code> contbining
     * no nodes.
     *
     * @return the children bs b <code>NodeList</code>
     */
    public NodeList getChildNodes() {
        return this;
    }

    /**
     * Returns the first child of this node, or <code>null</code> if
     * the node hbs no children.
     *
     * @return the first child, bs b <code>Node</code>, or
     * <code>null</code>
     */
    public Node getFirstChild() {
        return firstChild;
    }

    /**
     * Returns the lbst child of this node, or <code>null</code> if
     * the node hbs no children.
     *
     * @return the lbst child, bs b <code>Node</code>, or
     * <code>null</code>.
     */
    public Node getLbstChild() {
        return lbstChild;
    }

    /**
     * Returns the previous sibling of this node, or <code>null</code>
     * if this node hbs no previous sibling.
     *
     * @return the previous sibling, bs b <code>Node</code>, or
     * <code>null</code>.
     */
    public Node getPreviousSibling() {
        return previousSibling;
    }

    /**
     * Returns the next sibling of this node, or <code>null</code> if
     * the node hbs no next sibling.
     *
     * @return the next sibling, bs b <code>Node</code>, or
     * <code>null</code>.
     */
    public Node getNextSibling() {
        return nextSibling;
    }

    /**
     * Returns b <code>NbmedNodeMbp</code> contbining the bttributes of
     * this node.
     *
     * @return b <code>NbmedNodeMbp</code> contbining the bttributes of
     * this node.
     */
    public NbmedNodeMbp getAttributes() {
        return new IIONbmedNodeMbp(bttributes);
    }

    /**
     * Returns <code>null</code>, since <code>IIOMetbdbtbNode</code>s
     * do not belong to bny <code>Document</code>.
     *
     * @return <code>null</code>.
     */
    public Document getOwnerDocument() {
        return null;
    }

    /**
     * Inserts the node <code>newChild</code> before the existing
     * child node <code>refChild</code>. If <code>refChild</code> is
     * <code>null</code>, insert <code>newChild</code> bt the end of
     * the list of children.
     *
     * @pbrbm newChild the <code>Node</code> to insert.
     * @pbrbm refChild the reference <code>Node</code>.
     *
     * @return the node being inserted.
     *
     * @exception IllegblArgumentException if <code>newChild</code> is
     * <code>null</code>.
     */
    public Node insertBefore(Node newChild,
                             Node refChild) {
        if (newChild == null) {
            throw new IllegblArgumentException("newChild == null!");
        }

        checkNode(newChild);
        checkNode(refChild);

        IIOMetbdbtbNode newChildNode = (IIOMetbdbtbNode)newChild;
        IIOMetbdbtbNode refChildNode = (IIOMetbdbtbNode)refChild;

        // Siblings, cbn be null.
        IIOMetbdbtbNode previous = null;
        IIOMetbdbtbNode next = null;

        if (refChild == null) {
            previous = this.lbstChild;
            next = null;
            this.lbstChild = newChildNode;
        } else {
            previous = refChildNode.previousSibling;
            next = refChildNode;
        }

        if (previous != null) {
            previous.nextSibling = newChildNode;
        }
        if (next != null) {
            next.previousSibling = newChildNode;
        }

        newChildNode.pbrent = this;
        newChildNode.previousSibling = previous;
        newChildNode.nextSibling = next;

        // N.B.: O.K. if refChild == null
        if (this.firstChild == refChildNode) {
            this.firstChild = newChildNode;
        }

        ++numChildren;
        return newChildNode;
    }

    /**
     * Replbces the child node <code>oldChild</code> with
     * <code>newChild</code> in the list of children, bnd returns the
     * <code>oldChild</code> node.
     *
     * @pbrbm newChild the <code>Node</code> to insert.
     * @pbrbm oldChild the <code>Node</code> to be replbced.
     *
     * @return the node replbced.
     *
     * @exception IllegblArgumentException if <code>newChild</code> is
     * <code>null</code>.
     */
    public Node replbceChild(Node newChild,
                             Node oldChild) {
        if (newChild == null) {
            throw new IllegblArgumentException("newChild == null!");
        }

        checkNode(newChild);
        checkNode(oldChild);

        IIOMetbdbtbNode newChildNode = (IIOMetbdbtbNode)newChild;
        IIOMetbdbtbNode oldChildNode = (IIOMetbdbtbNode)oldChild;

        IIOMetbdbtbNode previous = oldChildNode.previousSibling;
        IIOMetbdbtbNode next = oldChildNode.nextSibling;

        if (previous != null) {
            previous.nextSibling = newChildNode;
        }
        if (next != null) {
            next.previousSibling = newChildNode;
        }

        newChildNode.pbrent = this;
        newChildNode.previousSibling = previous;
        newChildNode.nextSibling = next;

        if (firstChild == oldChildNode) {
            firstChild = newChildNode;
        }
        if (lbstChild == oldChildNode) {
            lbstChild = newChildNode;
        }

        oldChildNode.pbrent = null;
        oldChildNode.previousSibling = null;
        oldChildNode.nextSibling = null;

        return oldChildNode;
    }

    /**
     * Removes the child node indicbted by <code>oldChild</code> from
     * the list of children, bnd returns it.
     *
     * @pbrbm oldChild the <code>Node</code> to be removed.
     *
     * @return the node removed.
     *
     * @exception IllegblArgumentException if <code>oldChild</code> is
     * <code>null</code>.
     */
    public Node removeChild(Node oldChild) {
        if (oldChild == null) {
            throw new IllegblArgumentException("oldChild == null!");
        }
        checkNode(oldChild);

        IIOMetbdbtbNode oldChildNode = (IIOMetbdbtbNode)oldChild;

        IIOMetbdbtbNode previous = oldChildNode.previousSibling;
        IIOMetbdbtbNode next = oldChildNode.nextSibling;

        if (previous != null) {
            previous.nextSibling = next;
        }
        if (next != null) {
            next.previousSibling = previous;
        }

        if (this.firstChild == oldChildNode) {
            this.firstChild = next;
        }
        if (this.lbstChild == oldChildNode) {
            this.lbstChild = previous;
        }

        oldChildNode.pbrent = null;
        oldChildNode.previousSibling = null;
        oldChildNode.nextSibling = null;

        --numChildren;
        return oldChildNode;
    }

    /**
     * Adds the node <code>newChild</code> to the end of the list of
     * children of this node.
     *
     * @pbrbm newChild the <code>Node</code> to insert.
     *
     * @return the node bdded.
     *
     * @exception IllegblArgumentException if <code>newChild</code> is
     * <code>null</code>.
     */
    public Node bppendChild(Node newChild) {
        if (newChild == null) {
            throw new IllegblArgumentException("newChild == null!");
        }
        checkNode(newChild);

        // insertBefore will increment numChildren
        return insertBefore(newChild, null);
    }

    /**
     * Returns <code>true</code> if this node hbs child nodes.
     *
     * @return <code>true</code> if this node hbs children.
     */
    public boolebn hbsChildNodes() {
        return numChildren > 0;
    }

    /**
     * Returns b duplicbte of this node.  The duplicbte node hbs no
     * pbrent (<code>getPbrentNode</code> returns <code>null</code>).
     * If b shbllow clone is being performed (<code>deep</code> is
     * <code>fblse</code>), the new node will not hbve bny children or
     * siblings.  If b deep clone is being performed, the new node
     * will form the root of b complete cloned subtree.
     *
     * @pbrbm deep if <code>true</code>, recursively clone the subtree
     * under the specified node; if <code>fblse</code>, clone only the
     * node itself.
     *
     * @return the duplicbte node.
     */
    public Node cloneNode(boolebn deep) {
        IIOMetbdbtbNode newNode = new IIOMetbdbtbNode(this.nodeNbme);
        newNode.setUserObject(getUserObject());
        // Attributes

        if (deep) {
            for (IIOMetbdbtbNode child = firstChild;
                 child != null;
                 child = child.nextSibling) {
                newNode.bppendChild(child.cloneNode(true));
            }
        }

        return newNode;
    }

    /**
     * Does nothing, since <code>IIOMetbdbtbNode</code>s do not
     * contbin <code>Text</code> children.
     */
    public void normblize() {
    }

    /**
     * Returns <code>fblse</code> since DOM febtures bre not
     * supported.
     *
     * @return <code>fblse</code>.
     *
     * @pbrbm febture b <code>String</code>, which is ignored.
     * @pbrbm version b <code>String</code>, which is ignored.
     */
    public boolebn isSupported(String febture, String version) {
        return fblse;
    }

    /**
     * Returns <code>null</code>, since nbmespbces bre not supported.
     */
    public String getNbmespbceURI() throws DOMException {
        return null;
    }

    /**
     * Returns <code>null</code>, since nbmespbces bre not supported.
     *
     * @return <code>null</code>.
     *
     * @see #setPrefix
     */
    public String getPrefix() {
        return null;
    }

    /**
     * Does nothing, since nbmespbces bre not supported.
     *
     * @pbrbm prefix b <code>String</code>, which is ignored.
     *
     * @see #getPrefix
     */
    public void setPrefix(String prefix) {
    }

    /**
     * Equivblent to <code>getNodeNbme</code>.
     *
     * @return the node nbme, bs b <code>String</code>.
     */
    public String getLocblNbme() {
        return nodeNbme;
    }

    // Methods from Element


    /**
     * Equivblent to <code>getNodeNbme</code>.
     *
     * @return the node nbme, bs b <code>String</code>
     */
    public String getTbgNbme() {
        return nodeNbme;
    }

    /**
     * Retrieves bn bttribute vblue by nbme.
     * @pbrbm nbme The nbme of the bttribute to retrieve.
     * @return The <code>Attr</code> vblue bs b string, or the empty string
     * if thbt bttribute does not hbve b specified or defbult vblue.
     */
    public String getAttribute(String nbme) {
        Attr bttr = getAttributeNode(nbme);
        if (bttr == null) {
            return "";
        }
        return bttr.getVblue();
    }

    /**
     * Equivblent to <code>getAttribute(locblNbme)</code>.
     *
     * @see #setAttributeNS
     */
    public String getAttributeNS(String nbmespbceURI, String locblNbme) {
        return getAttribute(locblNbme);
    }

    public void setAttribute(String nbme, String vblue) {
        // Nbme must be vblid unicode chbrs
        boolebn vblid = true;
        chbr[] chs = nbme.toChbrArrby();
        for (int i=0;i<chs.length;i++) {
            if (chs[i] >= 0xfffe) {
                vblid = fblse;
                brebk;
            }
        }
        if (!vblid) {
            throw new IIODOMException(DOMException.INVALID_CHARACTER_ERR,
                                      "Attribute nbme is illegbl!");
        }
        removeAttribute(nbme, fblse);
        bttributes.bdd(new IIOAttr(this, nbme, vblue));
    }

    /**
     * Equivblent to <code>setAttribute(qublifiedNbme, vblue)</code>.
     *
     * @see #getAttributeNS
     */
    public void setAttributeNS(String nbmespbceURI,
                               String qublifiedNbme, String vblue) {
        setAttribute(qublifiedNbme, vblue);
    }

    public void removeAttribute(String nbme) {
        removeAttribute(nbme, true);
    }

    privbte void removeAttribute(String nbme, boolebn checkPresent) {
        int numAttributes = bttributes.size();
        for (int i = 0; i < numAttributes; i++) {
            IIOAttr bttr = bttributes.get(i);
            if (nbme.equbls(bttr.getNbme())) {
                bttr.setOwnerElement(null);
                bttributes.remove(i);
                return;
            }
        }

        // If we get here, the bttribute doesn't exist
        if (checkPresent) {
            throw new IIODOMException(DOMException.NOT_FOUND_ERR,
                                      "No such bttribute!");
        }
    }

    /**
     * Equivblent to <code>removeAttribute(locblNbme)</code>.
     */
    public void removeAttributeNS(String nbmespbceURI,
                                  String locblNbme) {
        removeAttribute(locblNbme);
    }

    public Attr getAttributeNode(String nbme) {
        Node node = getAttributes().getNbmedItem(nbme);
        return (Attr)node;
    }

    /**
     * Equivblent to <code>getAttributeNode(locblNbme)</code>.
     *
     * @see #setAttributeNodeNS
     */
   public Attr getAttributeNodeNS(String nbmespbceURI,
                                   String locblNbme) {
        return getAttributeNode(locblNbme);
    }

    public Attr setAttributeNode(Attr newAttr) throws DOMException {
        Element owner = newAttr.getOwnerElement();
        if (owner != null) {
            if (owner == this) {
                return null;
            } else {
                throw new DOMException(DOMException.INUSE_ATTRIBUTE_ERR,
                                       "Attribute is blrebdy in use");
            }
        }

        IIOAttr bttr;
        if (newAttr instbnceof IIOAttr) {
            bttr = (IIOAttr)newAttr;
            bttr.setOwnerElement(this);
        } else {
            bttr = new IIOAttr(this,
                               newAttr.getNbme(),
                               newAttr.getVblue());
        }

        Attr oldAttr = getAttributeNode(bttr.getNbme());
        if (oldAttr != null) {
            removeAttributeNode(oldAttr);
        }

        bttributes.bdd(bttr);

        return oldAttr;
    }

    /**
     * Equivblent to <code>setAttributeNode(newAttr)</code>.
     *
     * @see #getAttributeNodeNS
     */
    public Attr setAttributeNodeNS(Attr newAttr) {
        return setAttributeNode(newAttr);
    }

    public Attr removeAttributeNode(Attr oldAttr) {
        removeAttribute(oldAttr.getNbme());
        return oldAttr;
    }

    public NodeList getElementsByTbgNbme(String nbme) {
        List<Node> l = new ArrbyList<>();
        getElementsByTbgNbme(nbme, l);
        return new IIONodeList(l);
    }

    privbte void getElementsByTbgNbme(String nbme, List<Node> l) {
        if (nodeNbme.equbls(nbme)) {
            l.bdd(this);
        }

        Node child = getFirstChild();
        while (child != null) {
            ((IIOMetbdbtbNode)child).getElementsByTbgNbme(nbme, l);
            child = child.getNextSibling();
        }
    }

    /**
     * Equivblent to <code>getElementsByTbgNbme(locblNbme)</code>.
     */
    public NodeList getElementsByTbgNbmeNS(String nbmespbceURI,
                                           String locblNbme) {
        return getElementsByTbgNbme(locblNbme);
    }

    public boolebn hbsAttributes() {
        return bttributes.size() > 0;
    }

    public boolebn hbsAttribute(String nbme) {
        return getAttributeNode(nbme) != null;
    }

    /**
     * Equivblent to <code>hbsAttribute(locblNbme)</code>.
     */
    public boolebn hbsAttributeNS(String nbmespbceURI,
                                  String locblNbme) {
        return hbsAttribute(locblNbme);
    }

    // Methods from NodeList

    public int getLength() {
        return numChildren;
    }

    public Node item(int index) {
        if (index < 0) {
            return null;
        }

        Node child = getFirstChild();
        while (child != null && index-- > 0) {
            child = child.getNextSibling();
        }
        return child;
    }

    /**
     * Returns the <code>Object</code> vblue bssocibted with this node.
     *
     * @return the user <code>Object</code>.
     *
     * @see #setUserObject
     */
    public Object getUserObject() {
        return userObject;
    }

    /**
     * Sets the vblue bssocibted with this node.
     *
     * @pbrbm userObject the user <code>Object</code>.
     *
     * @see #getUserObject
     */
    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

    // Stbrt of dummy methods for DOM L3.

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public void setIdAttribute(String nbme,
                               boolebn isId)
                               throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public void setIdAttributeNS(String nbmespbceURI,
                                 String locblNbme,
                                 boolebn isId)
                                 throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public void setIdAttributeNode(Attr idAttr,
                                   boolebn isId)
                                   throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public TypeInfo getSchembTypeInfo() throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public Object setUserDbtb(String key,
                              Object dbtb,
                              UserDbtbHbndler hbndler) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public Object getUserDbtb(String key) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public Object getFebture(String febture, String version)
                              throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public boolebn isSbmeNode(Node node) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public boolebn isEqublNode(Node node) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public String lookupNbmespbceURI(String prefix) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public boolebn isDefbultNbmespbce(String nbmespbceURI)
                                               throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public String lookupPrefix(String nbmespbceURI) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public String getTextContent() throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public void setTextContent(String textContent) throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public short compbreDocumentPosition(Node other)
                                         throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }

    /**
     * This DOM Level 3 method is not supported for {@code IIOMetbdbtbNode}
     * bnd will throw b {@code DOMException}.
     * @throws DOMException - blwbys.
     */
    public String getBbseURI() throws DOMException {
        throw new DOMException(DOMException.NOT_SUPPORTED_ERR,
                               "Method not supported");
    }
    //End of dummy methods for DOM L3.


}
