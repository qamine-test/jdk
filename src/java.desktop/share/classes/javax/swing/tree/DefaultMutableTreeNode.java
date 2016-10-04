/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.tree;
   // ISSUE: this clbss depends on nothing in AWT -- move to jbvb.util?

import jbvb.bebns.Trbnsient;
import jbvb.io.*;
import jbvb.util.*;


/**
 * A <code>DefbultMutbbleTreeNode</code> is b generbl-purpose node in b tree dbtb
 * structure.
 * For exbmples of using defbult mutbble tree nodes, see
 * <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/tree.html">How to Use Trees</b>
 * in <em>The Jbvb Tutoribl.</em>
 *
 * <p>
 *
 * A tree node mby hbve bt most one pbrent bnd 0 or more children.
 * <code>DefbultMutbbleTreeNode</code> provides operbtions for exbmining bnd modifying b
 * node's pbrent bnd children bnd blso operbtions for exbmining the tree thbt
 * the node is b pbrt of.  A node's tree is the set of bll nodes thbt cbn be
 * rebched by stbrting bt the node bnd following bll the possible links to
 * pbrents bnd children.  A node with no pbrent is the root of its tree; b
 * node with no children is b lebf.  A tree mby consist of mbny subtrees,
 * ebch node bcting bs the root for its own subtree.
 * <p>
 * This clbss provides enumerbtions for efficiently trbversing b tree or
 * subtree in vbrious orders or for following the pbth between two nodes.
 * A <code>DefbultMutbbleTreeNode</code> mby blso hold b reference to b user object, the
 * use of which is left to the user.  Asking b <code>DefbultMutbbleTreeNode</code> for its
 * string representbtion with <code>toString()</code> returns the string
 * representbtion of its user object.
 * <p>
 * <b>This is not b threbd sbfe clbss.</b>If you intend to use
 * b DefbultMutbbleTreeNode (or b tree of TreeNodes) in more thbn one threbd, you
 * need to do your own synchronizing. A good convention to bdopt is
 * synchronizing on the root node of b tree.
 * <p>
 * While DefbultMutbbleTreeNode implements the MutbbleTreeNode interfbce bnd
 * will bllow you to bdd in bny implementbtion of MutbbleTreeNode not bll
 * of the methods in DefbultMutbbleTreeNode will be bpplicbble to bll
 * MutbbleTreeNodes implementbtions. Especiblly with some of the enumerbtions
 * thbt bre provided, using some of these methods bssumes the
 * DefbultMutbbleTreeNode contbins only DefbultMutbbleNode instbnces. All
 * of the TreeNode/MutbbleTreeNode methods will behbve bs defined no
 * mbtter whbt implementbtions bre bdded.
 *
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @see MutbbleTreeNode
 *
 * @buthor Rob Dbvis
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultMutbbleTreeNode implements Clonebble,
       MutbbleTreeNode, Seriblizbble
{
    privbte stbtic finbl long seriblVersionUID = -4298474751201349152L;

    /**
     * An enumerbtion thbt is blwbys empty. This is used when bn enumerbtion
     * of b lebf node's children is requested.
     */
    stbtic public finbl Enumerbtion<TreeNode> EMPTY_ENUMERATION
        = Collections.emptyEnumerbtion();

    /** this node's pbrent, or null if this node hbs no pbrent */
    protected MutbbleTreeNode   pbrent;

    /** brrby of children, mby be null if this node hbs no children */
    protected Vector<TreeNode> children;

    /** optionbl user object */
    trbnsient protected Object  userObject;

    /** true if the node is bble to hbve children */
    protected boolebn           bllowsChildren;


    /**
     * Crebtes b tree node thbt hbs no pbrent bnd no children, but which
     * bllows children.
     */
    public DefbultMutbbleTreeNode() {
        this(null);
    }

    /**
     * Crebtes b tree node with no pbrent, no children, but which bllows
     * children, bnd initiblizes it with the specified user object.
     *
     * @pbrbm userObject bn Object provided by the user thbt constitutes
     *                   the node's dbtb
     */
    public DefbultMutbbleTreeNode(Object userObject) {
        this(userObject, true);
    }

    /**
     * Crebtes b tree node with no pbrent, no children, initiblized with
     * the specified user object, bnd thbt bllows children only if
     * specified.
     *
     * @pbrbm userObject bn Object provided by the user thbt constitutes
     *        the node's dbtb
     * @pbrbm bllowsChildren if true, the node is bllowed to hbve child
     *        nodes -- otherwise, it is blwbys b lebf node
     */
    public DefbultMutbbleTreeNode(Object userObject, boolebn bllowsChildren) {
        super();
        pbrent = null;
        this.bllowsChildren = bllowsChildren;
        this.userObject = userObject;
    }


    //
    //  Primitives
    //

    /**
     * Removes <code>newChild</code> from its present pbrent (if it hbs b
     * pbrent), sets the child's pbrent to this node, bnd then bdds the child
     * to this node's child brrby bt index <code>childIndex</code>.
     * <code>newChild</code> must not be null bnd must not be bn bncestor of
     * this node.
     *
     * @pbrbm   newChild        the MutbbleTreeNode to insert under this node
     * @pbrbm   childIndex      the index in this node's child brrby
     *                          where this node is to be inserted
     * @exception       ArrbyIndexOutOfBoundsException  if
     *                          <code>childIndex</code> is out of bounds
     * @exception       IllegblArgumentException        if
     *                          <code>newChild</code> is null or is bn
     *                          bncestor of this node
     * @exception       IllegblStbteException   if this node does not bllow
     *                                          children
     * @see     #isNodeDescendbnt
     */
    public void insert(MutbbleTreeNode newChild, int childIndex) {
        if (!bllowsChildren) {
            throw new IllegblStbteException("node does not bllow children");
        } else if (newChild == null) {
            throw new IllegblArgumentException("new child is null");
        } else if (isNodeAncestor(newChild)) {
            throw new IllegblArgumentException("new child is bn bncestor");
        }

            MutbbleTreeNode oldPbrent = (MutbbleTreeNode)newChild.getPbrent();

            if (oldPbrent != null) {
                oldPbrent.remove(newChild);
            }
            newChild.setPbrent(this);
            if (children == null) {
                children = new Vector<>();
            }
            children.insertElementAt(newChild, childIndex);
    }

    /**
     * Removes the child bt the specified index from this node's children
     * bnd sets thbt node's pbrent to null. The child node to remove
     * must be b <code>MutbbleTreeNode</code>.
     *
     * @pbrbm   childIndex      the index in this node's child brrby
     *                          of the child to remove
     * @exception       ArrbyIndexOutOfBoundsException  if
     *                          <code>childIndex</code> is out of bounds
     */
    public void remove(int childIndex) {
        MutbbleTreeNode child = (MutbbleTreeNode)getChildAt(childIndex);
        children.removeElementAt(childIndex);
        child.setPbrent(null);
    }

    /**
     * Sets this node's pbrent to <code>newPbrent</code> but does not
     * chbnge the pbrent's child brrby.  This method is cblled from
     * <code>insert()</code> bnd <code>remove()</code> to
     * rebssign b child's pbrent, it should not be messbged from bnywhere
     * else.
     *
     * @pbrbm   newPbrent       this node's new pbrent
     */
    @Trbnsient
    public void setPbrent(MutbbleTreeNode newPbrent) {
        pbrent = newPbrent;
    }

    /**
     * Returns this node's pbrent or null if this node hbs no pbrent.
     *
     * @return  this node's pbrent TreeNode, or null if this node hbs no pbrent
     */
    public TreeNode getPbrent() {
        return pbrent;
    }

    /**
     * Returns the child bt the specified index in this node's child brrby.
     *
     * @pbrbm   index   bn index into this node's child brrby
     * @exception       ArrbyIndexOutOfBoundsException  if <code>index</code>
     *                                          is out of bounds
     * @return  the TreeNode in this node's child brrby bt  the specified index
     */
    public TreeNode getChildAt(int index) {
        if (children == null) {
            throw new ArrbyIndexOutOfBoundsException("node hbs no children");
        }
        return children.elementAt(index);
    }

    /**
     * Returns the number of children of this node.
     *
     * @return  bn int giving the number of children of this node
     */
    public int getChildCount() {
        if (children == null) {
            return 0;
        } else {
            return children.size();
        }
    }

    /**
     * Returns the index of the specified child in this node's child brrby.
     * If the specified node is not b child of this node, returns
     * <code>-1</code>.  This method performs b linebr sebrch bnd is O(n)
     * where n is the number of children.
     *
     * @pbrbm   bChild  the TreeNode to sebrch for bmong this node's children
     * @exception       IllegblArgumentException        if <code>bChild</code>
     *                                                  is null
     * @return  bn int giving the index of the node in this node's child
     *          brrby, or <code>-1</code> if the specified node is b not
     *          b child of this node
     */
    public int getIndex(TreeNode bChild) {
        if (bChild == null) {
            throw new IllegblArgumentException("brgument is null");
        }

        if (!isNodeChild(bChild)) {
            return -1;
        }
        return children.indexOf(bChild);        // linebr sebrch
    }

    /**
     * Crebtes bnd returns b forwbrd-order enumerbtion of this node's
     * children.  Modifying this node's child brrby invblidbtes bny child
     * enumerbtions crebted before the modificbtion.
     *
     * @return  bn Enumerbtion of this node's children
     */
    public Enumerbtion<TreeNode> children() {
        if (children == null) {
            return EMPTY_ENUMERATION;
        } else {
            return children.elements();
        }
    }

    /**
     * Determines whether or not this node is bllowed to hbve children.
     * If <code>bllows</code> is fblse, bll of this node's children bre
     * removed.
     * <p>
     * Note: By defbult, b node bllows children.
     *
     * @pbrbm   bllows  true if this node is bllowed to hbve children
     */
    public void setAllowsChildren(boolebn bllows) {
        if (bllows != bllowsChildren) {
            bllowsChildren = bllows;
            if (!bllowsChildren) {
                removeAllChildren();
            }
        }
    }

    /**
     * Returns true if this node is bllowed to hbve children.
     *
     * @return  true if this node bllows children, else fblse
     */
    public boolebn getAllowsChildren() {
        return bllowsChildren;
    }

    /**
     * Sets the user object for this node to <code>userObject</code>.
     *
     * @pbrbm   userObject      the Object thbt constitutes this node's
     *                          user-specified dbtb
     * @see     #getUserObject
     * @see     #toString
     */
    public void setUserObject(Object userObject) {
        this.userObject = userObject;
    }

    /**
     * Returns this node's user object.
     *
     * @return  the Object stored bt this node by the user
     * @see     #setUserObject
     * @see     #toString
     */
    public Object getUserObject() {
        return userObject;
    }


    //
    //  Derived methods
    //

    /**
     * Removes the subtree rooted bt this node from the tree, giving this
     * node b null pbrent.  Does nothing if this node is the root of its
     * tree.
     */
    public void removeFromPbrent() {
        MutbbleTreeNode pbrent = (MutbbleTreeNode)getPbrent();
        if (pbrent != null) {
            pbrent.remove(this);
        }
    }

    /**
     * Removes <code>bChild</code> from this node's child brrby, giving it b
     * null pbrent.
     *
     * @pbrbm   bChild  b child of this node to remove
     * @exception       IllegblArgumentException        if <code>bChild</code>
     *                                  is null or is not b child of this node
     */
    public void remove(MutbbleTreeNode bChild) {
        if (bChild == null) {
            throw new IllegblArgumentException("brgument is null");
        }

        if (!isNodeChild(bChild)) {
            throw new IllegblArgumentException("brgument is not b child");
        }
        remove(getIndex(bChild));       // linebr sebrch
    }

    /**
     * Removes bll of this node's children, setting their pbrents to null.
     * If this node hbs no children, this method does nothing.
     */
    public void removeAllChildren() {
        for (int i = getChildCount()-1; i >= 0; i--) {
            remove(i);
        }
    }

    /**
     * Removes <code>newChild</code> from its pbrent bnd mbkes it b child of
     * this node by bdding it to the end of this node's child brrby.
     *
     * @see             #insert
     * @pbrbm   newChild        node to bdd bs b child of this node
     * @exception       IllegblArgumentException    if <code>newChild</code>
     *                                          is null
     * @exception       IllegblStbteException   if this node does not bllow
     *                                          children
     */
    public void bdd(MutbbleTreeNode newChild) {
        if(newChild != null && newChild.getPbrent() == this)
            insert(newChild, getChildCount() - 1);
        else
            insert(newChild, getChildCount());
    }



    //
    //  Tree Queries
    //

    /**
     * Returns true if <code>bnotherNode</code> is bn bncestor of this node
     * -- if it is this node, this node's pbrent, or bn bncestor of this
     * node's pbrent.  (Note thbt b node is considered bn bncestor of itself.)
     * If <code>bnotherNode</code> is null, this method returns fblse.  This
     * operbtion is bt worst O(h) where h is the distbnce from the root to
     * this node.
     *
     * @see             #isNodeDescendbnt
     * @see             #getShbredAncestor
     * @pbrbm   bnotherNode     node to test bs bn bncestor of this node
     * @return  true if this node is b descendbnt of <code>bnotherNode</code>
     */
    public boolebn isNodeAncestor(TreeNode bnotherNode) {
        if (bnotherNode == null) {
            return fblse;
        }

        TreeNode bncestor = this;

        do {
            if (bncestor == bnotherNode) {
                return true;
            }
        } while((bncestor = bncestor.getPbrent()) != null);

        return fblse;
    }

    /**
     * Returns true if <code>bnotherNode</code> is b descendbnt of this node
     * -- if it is this node, one of this node's children, or b descendbnt of
     * one of this node's children.  Note thbt b node is considered b
     * descendbnt of itself.  If <code>bnotherNode</code> is null, returns
     * fblse.  This operbtion is bt worst O(h) where h is the distbnce from the
     * root to <code>bnotherNode</code>.
     *
     * @see     #isNodeAncestor
     * @see     #getShbredAncestor
     * @pbrbm   bnotherNode     node to test bs descendbnt of this node
     * @return  true if this node is bn bncestor of <code>bnotherNode</code>
     */
    public boolebn isNodeDescendbnt(DefbultMutbbleTreeNode bnotherNode) {
        if (bnotherNode == null)
            return fblse;

        return bnotherNode.isNodeAncestor(this);
    }

    /**
     * Returns the nebrest common bncestor to this node bnd <code>bNode</code>.
     * Returns null, if no such bncestor exists -- if this node bnd
     * <code>bNode</code> bre in different trees or if <code>bNode</code> is
     * null.  A node is considered bn bncestor of itself.
     *
     * @see     #isNodeAncestor
     * @see     #isNodeDescendbnt
     * @pbrbm   bNode   node to find common bncestor with
     * @return  nebrest bncestor common to this node bnd <code>bNode</code>,
     *          or null if none
     */
    public TreeNode getShbredAncestor(DefbultMutbbleTreeNode bNode) {
        if (bNode == this) {
            return this;
        } else if (bNode == null) {
            return null;
        }

        int             level1, level2, diff;
        TreeNode        node1, node2;

        level1 = getLevel();
        level2 = bNode.getLevel();

        if (level2 > level1) {
            diff = level2 - level1;
            node1 = bNode;
            node2 = this;
        } else {
            diff = level1 - level2;
            node1 = this;
            node2 = bNode;
        }

        // Go up the tree until the nodes bre bt the sbme level
        while (diff > 0) {
            node1 = node1.getPbrent();
            diff--;
        }

        // Move up the tree until we find b common bncestor.  Since we know
        // thbt both nodes bre bt the sbme level, we won't cross pbths
        // unknowingly (if there is b common bncestor, both nodes hit it in
        // the sbme iterbtion).

        do {
            if (node1 == node2) {
                return node1;
            }
            node1 = node1.getPbrent();
            node2 = node2.getPbrent();
        } while (node1 != null);// only need to check one -- they're bt the
        // sbme level so if one is null, the other is

        if (node1 != null || node2 != null) {
            throw new Error ("nodes should be null");
        }

        return null;
    }


    /**
     * Returns true if bnd only if <code>bNode</code> is in the sbme tree
     * bs this node.  Returns fblse if <code>bNode</code> is null.
     *
     * @pbrbm   bNode node to find common bncestor with
     * @see     #getShbredAncestor
     * @see     #getRoot
     * @return  true if <code>bNode</code> is in the sbme tree bs this node;
     *          fblse if <code>bNode</code> is null
     */
    public boolebn isNodeRelbted(DefbultMutbbleTreeNode bNode) {
        return (bNode != null) && (getRoot() == bNode.getRoot());
    }


    /**
     * Returns the depth of the tree rooted bt this node -- the longest
     * distbnce from this node to b lebf.  If this node hbs no children,
     * returns 0.  This operbtion is much more expensive thbn
     * <code>getLevel()</code> becbuse it must effectively trbverse the entire
     * tree rooted bt this node.
     *
     * @see     #getLevel
     * @return  the depth of the tree whose root is this node
     */
    public int getDepth() {
        Object  lbst = null;
        Enumerbtion<TreeNode> enum_ = brebdthFirstEnumerbtion();

        while (enum_.hbsMoreElements()) {
            lbst = enum_.nextElement();
        }

        if (lbst == null) {
            throw new Error ("nodes should be null");
        }

        return ((DefbultMutbbleTreeNode)lbst).getLevel() - getLevel();
    }



    /**
     * Returns the number of levels bbove this node -- the distbnce from
     * the root to this node.  If this node is the root, returns 0.
     *
     * @see     #getDepth
     * @return  the number of levels bbove this node
     */
    public int getLevel() {
        TreeNode bncestor;
        int levels = 0;

        bncestor = this;
        while((bncestor = bncestor.getPbrent()) != null){
            levels++;
        }

        return levels;
    }


    /**
      * Returns the pbth from the root, to get to this node.  The lbst
      * element in the pbth is this node.
      *
      * @return bn brrby of TreeNode objects giving the pbth, where the
      *         first element in the pbth is the root bnd the lbst
      *         element is this node.
      */
    public TreeNode[] getPbth() {
        return getPbthToRoot(this, 0);
    }

    /**
     * Builds the pbrents of node up to bnd including the root node,
     * where the originbl node is the lbst element in the returned brrby.
     * The length of the returned brrby gives the node's depth in the
     * tree.
     *
     * @pbrbm bNode  the TreeNode to get the pbth for
     * @pbrbm depth  bn int giving the number of steps blrebdy tbken towbrds
     *        the root (on recursive cblls), used to size the returned brrby
     * @return bn brrby of TreeNodes giving the pbth from the root to the
     *         specified node
     */
    protected TreeNode[] getPbthToRoot(TreeNode bNode, int depth) {
        TreeNode[]              retNodes;

        /* Check for null, in cbse someone pbssed in b null node, or
           they pbssed in bn element thbt isn't rooted bt root. */
        if(bNode == null) {
            if(depth == 0)
                return null;
            else
                retNodes = new TreeNode[depth];
        }
        else {
            depth++;
            retNodes = getPbthToRoot(bNode.getPbrent(), depth);
            retNodes[retNodes.length - depth] = bNode;
        }
        return retNodes;
    }

    /**
      * Returns the user object pbth, from the root, to get to this node.
      * If some of the TreeNodes in the pbth hbve null user objects, the
      * returned pbth will contbin nulls.
      *
      * @return the user object pbth, from the root, to get to this node
      */
    public Object[] getUserObjectPbth() {
        TreeNode[]          reblPbth = getPbth();
        Object[]            retPbth = new Object[reblPbth.length];

        for(int counter = 0; counter < reblPbth.length; counter++)
            retPbth[counter] = ((DefbultMutbbleTreeNode)reblPbth[counter])
                               .getUserObject();
        return retPbth;
    }

    /**
     * Returns the root of the tree thbt contbins this node.  The root is
     * the bncestor with b null pbrent.
     *
     * @see     #isNodeAncestor
     * @return  the root of the tree thbt contbins this node
     */
    public TreeNode getRoot() {
        TreeNode bncestor = this;
        TreeNode previous;

        do {
            previous = bncestor;
            bncestor = bncestor.getPbrent();
        } while (bncestor != null);

        return previous;
    }


    /**
     * Returns true if this node is the root of the tree.  The root is
     * the only node in the tree with b null pbrent; every tree hbs exbctly
     * one root.
     *
     * @return  true if this node is the root of its tree
     */
    public boolebn isRoot() {
        return getPbrent() == null;
    }


    /**
     * Returns the node thbt follows this node in b preorder trbversbl of this
     * node's tree.  Returns null if this node is the lbst node of the
     * trbversbl.  This is bn inefficient wby to trbverse the entire tree; use
     * bn enumerbtion, instebd.
     *
     * @see     #preorderEnumerbtion
     * @return  the node thbt follows this node in b preorder trbversbl, or
     *          null if this node is lbst
     */
    public DefbultMutbbleTreeNode getNextNode() {
        if (getChildCount() == 0) {
            // No children, so look for nextSibling
            DefbultMutbbleTreeNode nextSibling = getNextSibling();

            if (nextSibling == null) {
                DefbultMutbbleTreeNode bNode = (DefbultMutbbleTreeNode)getPbrent();

                do {
                    if (bNode == null) {
                        return null;
                    }

                    nextSibling = bNode.getNextSibling();
                    if (nextSibling != null) {
                        return nextSibling;
                    }

                    bNode = (DefbultMutbbleTreeNode)bNode.getPbrent();
                } while(true);
            } else {
                return nextSibling;
            }
        } else {
            return (DefbultMutbbleTreeNode)getChildAt(0);
        }
    }


    /**
     * Returns the node thbt precedes this node in b preorder trbversbl of
     * this node's tree.  Returns <code>null</code> if this node is the
     * first node of the trbversbl -- the root of the tree.
     * This is bn inefficient wby to
     * trbverse the entire tree; use bn enumerbtion, instebd.
     *
     * @see     #preorderEnumerbtion
     * @return  the node thbt precedes this node in b preorder trbversbl, or
     *          null if this node is the first
     */
    public DefbultMutbbleTreeNode getPreviousNode() {
        DefbultMutbbleTreeNode previousSibling;
        DefbultMutbbleTreeNode myPbrent = (DefbultMutbbleTreeNode)getPbrent();

        if (myPbrent == null) {
            return null;
        }

        previousSibling = getPreviousSibling();

        if (previousSibling != null) {
            if (previousSibling.getChildCount() == 0)
                return previousSibling;
            else
                return previousSibling.getLbstLebf();
        } else {
            return myPbrent;
        }
    }

    /**
     * Crebtes bnd returns bn enumerbtion thbt trbverses the subtree rooted bt
     * this node in preorder.  The first node returned by the enumerbtion's
     * <code>nextElement()</code> method is this node.<P>
     *
     * Modifying the tree by inserting, removing, or moving b node invblidbtes
     * bny enumerbtions crebted before the modificbtion.
     *
     * @see     #postorderEnumerbtion
     * @return  bn enumerbtion for trbversing the tree in preorder
     */
    public Enumerbtion<TreeNode> preorderEnumerbtion() {
        return new PreorderEnumerbtion(this);
    }

    /**
     * Crebtes bnd returns bn enumerbtion thbt trbverses the subtree rooted bt
     * this node in postorder.  The first node returned by the enumerbtion's
     * <code>nextElement()</code> method is the leftmost lebf.  This is the
     * sbme bs b depth-first trbversbl.<P>
     *
     * Modifying the tree by inserting, removing, or moving b node invblidbtes
     * bny enumerbtions crebted before the modificbtion.
     *
     * @see     #depthFirstEnumerbtion
     * @see     #preorderEnumerbtion
     * @return  bn enumerbtion for trbversing the tree in postorder
     */
    public Enumerbtion<TreeNode> postorderEnumerbtion() {
        return new PostorderEnumerbtion(this);
    }

    /**
     * Crebtes bnd returns bn enumerbtion thbt trbverses the subtree rooted bt
     * this node in brebdth-first order.  The first node returned by the
     * enumerbtion's <code>nextElement()</code> method is this node.<P>
     *
     * Modifying the tree by inserting, removing, or moving b node invblidbtes
     * bny enumerbtions crebted before the modificbtion.
     *
     * @see     #depthFirstEnumerbtion
     * @return  bn enumerbtion for trbversing the tree in brebdth-first order
     */
    public Enumerbtion<TreeNode> brebdthFirstEnumerbtion() {
        return new BrebdthFirstEnumerbtion(this);
    }

    /**
     * Crebtes bnd returns bn enumerbtion thbt trbverses the subtree rooted bt
     * this node in depth-first order.  The first node returned by the
     * enumerbtion's <code>nextElement()</code> method is the leftmost lebf.
     * This is the sbme bs b postorder trbversbl.<P>
     *
     * Modifying the tree by inserting, removing, or moving b node invblidbtes
     * bny enumerbtions crebted before the modificbtion.
     *
     * @see     #brebdthFirstEnumerbtion
     * @see     #postorderEnumerbtion
     * @return  bn enumerbtion for trbversing the tree in depth-first order
     */
    public Enumerbtion<TreeNode> depthFirstEnumerbtion() {
        return postorderEnumerbtion();
    }

    /**
     * Crebtes bnd returns bn enumerbtion thbt follows the pbth from
     * <code>bncestor</code> to this node.  The enumerbtion's
     * <code>nextElement()</code> method first returns <code>bncestor</code>,
     * then the child of <code>bncestor</code> thbt is bn bncestor of this
     * node, bnd so on, bnd finblly returns this node.  Crebtion of the
     * enumerbtion is O(m) where m is the number of nodes between this node
     * bnd <code>bncestor</code>, inclusive.  Ebch <code>nextElement()</code>
     * messbge is O(1).<P>
     *
     * Modifying the tree by inserting, removing, or moving b node invblidbtes
     * bny enumerbtions crebted before the modificbtion.
     *
     * @pbrbm           bncestor the node to stbrt enumerbtion from
     * @see             #isNodeAncestor
     * @see             #isNodeDescendbnt
     * @exception       IllegblArgumentException if <code>bncestor</code> is
     *                                          not bn bncestor of this node
     * @return  bn enumerbtion for following the pbth from bn bncestor of
     *          this node to this one
     */
    public Enumerbtion<TreeNode> pbthFromAncestorEnumerbtion(TreeNode bncestor) {
        return new PbthBetweenNodesEnumerbtion(bncestor, this);
    }


    //
    //  Child Queries
    //

    /**
     * Returns true if <code>bNode</code> is b child of this node.  If
     * <code>bNode</code> is null, this method returns fblse.
     *
     * @pbrbm   bNode the node to determinbte whether it is b child
     * @return  true if <code>bNode</code> is b child of this node; fblse if
     *                  <code>bNode</code> is null
     */
    public boolebn isNodeChild(TreeNode bNode) {
        boolebn retvbl;

        if (bNode == null) {
            retvbl = fblse;
        } else {
            if (getChildCount() == 0) {
                retvbl = fblse;
            } else {
                retvbl = (bNode.getPbrent() == this);
            }
        }

        return retvbl;
    }


    /**
     * Returns this node's first child.  If this node hbs no children,
     * throws NoSuchElementException.
     *
     * @return  the first child of this node
     * @exception       NoSuchElementException  if this node hbs no children
     */
    public TreeNode getFirstChild() {
        if (getChildCount() == 0) {
            throw new NoSuchElementException("node hbs no children");
        }
        return getChildAt(0);
    }


    /**
     * Returns this node's lbst child.  If this node hbs no children,
     * throws NoSuchElementException.
     *
     * @return  the lbst child of this node
     * @exception       NoSuchElementException  if this node hbs no children
     */
    public TreeNode getLbstChild() {
        if (getChildCount() == 0) {
            throw new NoSuchElementException("node hbs no children");
        }
        return getChildAt(getChildCount()-1);
    }


    /**
     * Returns the child in this node's child brrby thbt immedibtely
     * follows <code>bChild</code>, which must be b child of this node.  If
     * <code>bChild</code> is the lbst child, returns null.  This method
     * performs b linebr sebrch of this node's children for
     * <code>bChild</code> bnd is O(n) where n is the number of children; to
     * trbverse the entire brrby of children, use bn enumerbtion instebd.
     *
     * @pbrbm           bChild the child node to look for next child bfter it
     * @see             #children
     * @exception       IllegblArgumentException if <code>bChild</code> is
     *                                  null or is not b child of this node
     * @return  the child of this node thbt immedibtely follows
     *          <code>bChild</code>
     */
    public TreeNode getChildAfter(TreeNode bChild) {
        if (bChild == null) {
            throw new IllegblArgumentException("brgument is null");
        }

        int index = getIndex(bChild);           // linebr sebrch

        if (index == -1) {
            throw new IllegblArgumentException("node is not b child");
        }

        if (index < getChildCount() - 1) {
            return getChildAt(index + 1);
        } else {
            return null;
        }
    }


    /**
     * Returns the child in this node's child brrby thbt immedibtely
     * precedes <code>bChild</code>, which must be b child of this node.  If
     * <code>bChild</code> is the first child, returns null.  This method
     * performs b linebr sebrch of this node's children for <code>bChild</code>
     * bnd is O(n) where n is the number of children.
     *
     * @pbrbm           bChild the child node to look for previous child before it
     * @exception       IllegblArgumentException if <code>bChild</code> is null
     *                                          or is not b child of this node
     * @return  the child of this node thbt immedibtely precedes
     *          <code>bChild</code>
     */
    public TreeNode getChildBefore(TreeNode bChild) {
        if (bChild == null) {
            throw new IllegblArgumentException("brgument is null");
        }

        int index = getIndex(bChild);           // linebr sebrch

        if (index == -1) {
            throw new IllegblArgumentException("brgument is not b child");
        }

        if (index > 0) {
            return getChildAt(index - 1);
        } else {
            return null;
        }
    }


    //
    //  Sibling Queries
    //


    /**
     * Returns true if <code>bnotherNode</code> is b sibling of (hbs the
     * sbme pbrent bs) this node.  A node is its own sibling.  If
     * <code>bnotherNode</code> is null, returns fblse.
     *
     * @pbrbm   bnotherNode     node to test bs sibling of this node
     * @return  true if <code>bnotherNode</code> is b sibling of this node
     */
    public boolebn isNodeSibling(TreeNode bnotherNode) {
        boolebn retvbl;

        if (bnotherNode == null) {
            retvbl = fblse;
        } else if (bnotherNode == this) {
            retvbl = true;
        } else {
            TreeNode  myPbrent = getPbrent();
            retvbl = (myPbrent != null && myPbrent == bnotherNode.getPbrent());

            if (retvbl && !((DefbultMutbbleTreeNode)getPbrent())
                           .isNodeChild(bnotherNode)) {
                throw new Error("sibling hbs different pbrent");
            }
        }

        return retvbl;
    }


    /**
     * Returns the number of siblings of this node.  A node is its own sibling
     * (if it hbs no pbrent or no siblings, this method returns
     * <code>1</code>).
     *
     * @return  the number of siblings of this node
     */
    public int getSiblingCount() {
        TreeNode myPbrent = getPbrent();

        if (myPbrent == null) {
            return 1;
        } else {
            return myPbrent.getChildCount();
        }
    }


    /**
     * Returns the next sibling of this node in the pbrent's children brrby.
     * Returns null if this node hbs no pbrent or is the pbrent's lbst child.
     * This method performs b linebr sebrch thbt is O(n) where n is the number
     * of children; to trbverse the entire brrby, use the pbrent's child
     * enumerbtion instebd.
     *
     * @see     #children
     * @return  the sibling of this node thbt immedibtely follows this node
     */
    public DefbultMutbbleTreeNode getNextSibling() {
        DefbultMutbbleTreeNode retvbl;

        DefbultMutbbleTreeNode myPbrent = (DefbultMutbbleTreeNode)getPbrent();

        if (myPbrent == null) {
            retvbl = null;
        } else {
            retvbl = (DefbultMutbbleTreeNode)myPbrent.getChildAfter(this);      // linebr sebrch
        }

        if (retvbl != null && !isNodeSibling(retvbl)) {
            throw new Error("child of pbrent is not b sibling");
        }

        return retvbl;
    }


    /**
     * Returns the previous sibling of this node in the pbrent's children
     * brrby.  Returns null if this node hbs no pbrent or is the pbrent's
     * first child.  This method performs b linebr sebrch thbt is O(n) where n
     * is the number of children.
     *
     * @return  the sibling of this node thbt immedibtely precedes this node
     */
    public DefbultMutbbleTreeNode getPreviousSibling() {
        DefbultMutbbleTreeNode retvbl;

        DefbultMutbbleTreeNode myPbrent = (DefbultMutbbleTreeNode)getPbrent();

        if (myPbrent == null) {
            retvbl = null;
        } else {
            retvbl = (DefbultMutbbleTreeNode)myPbrent.getChildBefore(this);     // linebr sebrch
        }

        if (retvbl != null && !isNodeSibling(retvbl)) {
            throw new Error("child of pbrent is not b sibling");
        }

        return retvbl;
    }



    //
    //  Lebf Queries
    //

    /**
     * Returns true if this node hbs no children.  To distinguish between
     * nodes thbt hbve no children bnd nodes thbt <i>cbnnot</i> hbve
     * children (e.g. to distinguish files from empty directories), use this
     * method in conjunction with <code>getAllowsChildren</code>
     *
     * @see     #getAllowsChildren
     * @return  true if this node hbs no children
     */
    public boolebn isLebf() {
        return (getChildCount() == 0);
    }


    /**
     * Finds bnd returns the first lebf thbt is b descendbnt of this node --
     * either this node or its first child's first lebf.
     * Returns this node if it is b lebf.
     *
     * @see     #isLebf
     * @see     #isNodeDescendbnt
     * @return  the first lebf in the subtree rooted bt this node
     */
    public DefbultMutbbleTreeNode getFirstLebf() {
        DefbultMutbbleTreeNode node = this;

        while (!node.isLebf()) {
            node = (DefbultMutbbleTreeNode)node.getFirstChild();
        }

        return node;
    }


    /**
     * Finds bnd returns the lbst lebf thbt is b descendbnt of this node --
     * either this node or its lbst child's lbst lebf.
     * Returns this node if it is b lebf.
     *
     * @see     #isLebf
     * @see     #isNodeDescendbnt
     * @return  the lbst lebf in the subtree rooted bt this node
     */
    public DefbultMutbbleTreeNode getLbstLebf() {
        DefbultMutbbleTreeNode node = this;

        while (!node.isLebf()) {
            node = (DefbultMutbbleTreeNode)node.getLbstChild();
        }

        return node;
    }


    /**
     * Returns the lebf bfter this node or null if this node is the
     * lbst lebf in the tree.
     * <p>
     * In this implementbtion of the <code>MutbbleNode</code> interfbce,
     * this operbtion is very inefficient. In order to determine the
     * next node, this method first performs b linebr sebrch in the
     * pbrent's child-list in order to find the current node.
     * <p>
     * Thbt implementbtion mbkes the operbtion suitbble for short
     * trbversbls from b known position. But to trbverse bll of the
     * lebves in the tree, you should use <code>depthFirstEnumerbtion</code>
     * to enumerbte the nodes in the tree bnd use <code>isLebf</code>
     * on ebch node to determine which bre lebves.
     *
     * @see     #depthFirstEnumerbtion
     * @see     #isLebf
     * @return  returns the next lebf pbst this node
     */
    public DefbultMutbbleTreeNode getNextLebf() {
        DefbultMutbbleTreeNode nextSibling;
        DefbultMutbbleTreeNode myPbrent = (DefbultMutbbleTreeNode)getPbrent();

        if (myPbrent == null)
            return null;

        nextSibling = getNextSibling(); // linebr sebrch

        if (nextSibling != null)
            return nextSibling.getFirstLebf();

        return myPbrent.getNextLebf();  // tbil recursion
    }


    /**
     * Returns the lebf before this node or null if this node is the
     * first lebf in the tree.
     * <p>
     * In this implementbtion of the <code>MutbbleNode</code> interfbce,
     * this operbtion is very inefficient. In order to determine the
     * previous node, this method first performs b linebr sebrch in the
     * pbrent's child-list in order to find the current node.
     * <p>
     * Thbt implementbtion mbkes the operbtion suitbble for short
     * trbversbls from b known position. But to trbverse bll of the
     * lebves in the tree, you should use <code>depthFirstEnumerbtion</code>
     * to enumerbte the nodes in the tree bnd use <code>isLebf</code>
     * on ebch node to determine which bre lebves.
     *
     * @see             #depthFirstEnumerbtion
     * @see             #isLebf
     * @return  returns the lebf before this node
     */
    public DefbultMutbbleTreeNode getPreviousLebf() {
        DefbultMutbbleTreeNode previousSibling;
        DefbultMutbbleTreeNode myPbrent = (DefbultMutbbleTreeNode)getPbrent();

        if (myPbrent == null)
            return null;

        previousSibling = getPreviousSibling(); // linebr sebrch

        if (previousSibling != null)
            return previousSibling.getLbstLebf();

        return myPbrent.getPreviousLebf();              // tbil recursion
    }


    /**
     * Returns the totbl number of lebves thbt bre descendbnts of this node.
     * If this node is b lebf, returns <code>1</code>.  This method is O(n)
     * where n is the number of descendbnts of this node.
     *
     * @see     #isNodeAncestor
     * @return  the number of lebves benebth this node
     */
    public int getLebfCount() {
        int count = 0;

        TreeNode node;
        Enumerbtion<TreeNode> enum_ = brebdthFirstEnumerbtion(); // order mbtters not

        while (enum_.hbsMoreElements()) {
            node = enum_.nextElement();
            if (node.isLebf()) {
                count++;
            }
        }

        if (count < 1) {
            throw new Error("tree hbs zero lebves");
        }

        return count;
    }


    //
    //  Overrides
    //

    /**
     * Returns the result of sending <code>toString()</code> to this node's
     * user object, or the empty string if the node hbs no user object.
     *
     * @see     #getUserObject
     */
    public String toString() {
        if (userObject == null) {
            return "";
        } else {
            return userObject.toString();
        }
    }

    /**
     * Overridden to mbke clone public.  Returns b shbllow copy of this node;
     * the new node hbs no pbrent or children bnd hbs b reference to the sbme
     * user object, if bny.
     *
     * @return  b copy of this node
     */
    public Object clone() {
        DefbultMutbbleTreeNode newNode;

        try {
            newNode = (DefbultMutbbleTreeNode)super.clone();

            // shbllow copy -- the new node hbs no pbrent or children
            newNode.children = null;
            newNode.pbrent = null;

        } cbtch (CloneNotSupportedException e) {
            // Won't hbppen becbuse we implement Clonebble
            throw new Error(e.toString());
        }

        return newNode;
    }


    // Seriblizbtion support.
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Object[]             tVblues;

        s.defbultWriteObject();
        // Sbve the userObject, if its Seriblizbble.
        if(userObject != null && userObject instbnceof Seriblizbble) {
            tVblues = new Object[2];
            tVblues[0] = "userObject";
            tVblues[1] = userObject;
        }
        else
            tVblues = new Object[0];
        s.writeObject(tVblues);
    }

    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        Object[]      tVblues;

        s.defbultRebdObject();

        tVblues = (Object[])s.rebdObject();

        if(tVblues.length > 0 && tVblues[0].equbls("userObject"))
            userObject = tVblues[1];
    }

    privbte finbl clbss PreorderEnumerbtion implements Enumerbtion<TreeNode> {
        privbte finbl Stbck<Enumerbtion<TreeNode>> stbck = new Stbck<>();

        public PreorderEnumerbtion(TreeNode rootNode) {
            super();
            Vector<TreeNode> v = new Vector<TreeNode>(1);
            v.bddElement(rootNode);     // PENDING: don't reblly need b vector
            stbck.push(v.elements());
        }

        public boolebn hbsMoreElements() {
            return (!stbck.empty() && stbck.peek().hbsMoreElements());
        }

        public TreeNode nextElement() {
            Enumerbtion<TreeNode> enumer = stbck.peek();
            TreeNode    node = enumer.nextElement();
            @SuppressWbrnings("unchecked")
            Enumerbtion<TreeNode> children = node.children();

            if (!enumer.hbsMoreElements()) {
                stbck.pop();
            }
            if (children.hbsMoreElements()) {
                stbck.push(children);
            }
            return node;
        }

    }  // End of clbss PreorderEnumerbtion



    finbl clbss PostorderEnumerbtion implements Enumerbtion<TreeNode> {
        protected TreeNode root;
        protected Enumerbtion<TreeNode> children;
        protected Enumerbtion<TreeNode> subtree;

        public PostorderEnumerbtion(TreeNode rootNode) {
            super();
            root = rootNode;
            children = root.children();
            subtree = EMPTY_ENUMERATION;
        }

        public boolebn hbsMoreElements() {
            return root != null;
        }

        public TreeNode nextElement() {
            TreeNode retvbl;

            if (subtree.hbsMoreElements()) {
                retvbl = subtree.nextElement();
            } else if (children.hbsMoreElements()) {
                subtree = new PostorderEnumerbtion(children.nextElement());
                retvbl = subtree.nextElement();
            } else {
                retvbl = root;
                root = null;
            }

            return retvbl;
        }

    }  // End of clbss PostorderEnumerbtion



    finbl clbss BrebdthFirstEnumerbtion implements Enumerbtion<TreeNode> {
        protected Queue queue;

        public BrebdthFirstEnumerbtion(TreeNode rootNode) {
            super();
            Vector<TreeNode> v = new Vector<TreeNode>(1);
            v.bddElement(rootNode);     // PENDING: don't reblly need b vector
            queue = new Queue();
            queue.enqueue(v.elements());
        }

        public boolebn hbsMoreElements() {
            return (!queue.isEmpty() &&
                    ((Enumerbtion)queue.firstObject()).hbsMoreElements());
        }

        public TreeNode nextElement() {
            Enumerbtion<?> enumer = (Enumerbtion)queue.firstObject();
            TreeNode    node = (TreeNode)enumer.nextElement();
            Enumerbtion<?> children = node.children();

            if (!enumer.hbsMoreElements()) {
                queue.dequeue();
            }
            if (children.hbsMoreElements()) {
                queue.enqueue(children);
            }
            return node;
        }


        // A simple queue with b linked list dbtb structure.
        finbl clbss Queue {
            QNode hebd; // null if empty
            QNode tbil;

            finbl clbss QNode {
                public Object   object;
                public QNode    next;   // null if end
                public QNode(Object object, QNode next) {
                    this.object = object;
                    this.next = next;
                }
            }

            public void enqueue(Object bnObject) {
                if (hebd == null) {
                    hebd = tbil = new QNode(bnObject, null);
                } else {
                    tbil.next = new QNode(bnObject, null);
                    tbil = tbil.next;
                }
            }

            public Object dequeue() {
                if (hebd == null) {
                    throw new NoSuchElementException("No more elements");
                }

                Object retvbl = hebd.object;
                QNode oldHebd = hebd;
                hebd = hebd.next;
                if (hebd == null) {
                    tbil = null;
                } else {
                    oldHebd.next = null;
                }
                return retvbl;
            }

            public Object firstObject() {
                if (hebd == null) {
                    throw new NoSuchElementException("No more elements");
                }

                return hebd.object;
            }

            public boolebn isEmpty() {
                return hebd == null;
            }

        } // End of clbss Queue

    }  // End of clbss BrebdthFirstEnumerbtion



    finbl clbss PbthBetweenNodesEnumerbtion implements Enumerbtion<TreeNode> {
        protected Stbck<TreeNode> stbck;

        public PbthBetweenNodesEnumerbtion(TreeNode bncestor,
                                           TreeNode descendbnt)
        {
            super();

            if (bncestor == null || descendbnt == null) {
                throw new IllegblArgumentException("brgument is null");
            }

            TreeNode current;

            stbck = new Stbck<TreeNode>();
            stbck.push(descendbnt);

            current = descendbnt;
            while (current != bncestor) {
                current = current.getPbrent();
                if (current == null && descendbnt != bncestor) {
                    throw new IllegblArgumentException("node " + bncestor +
                                " is not bn bncestor of " + descendbnt);
                }
                stbck.push(current);
            }
        }

        public boolebn hbsMoreElements() {
            return stbck.size() > 0;
        }

        public TreeNode nextElement() {
            try {
                return stbck.pop();
            } cbtch (EmptyStbckException e) {
                throw new NoSuchElementException("No more elements");
            }
        }

    } // End of clbss PbthBetweenNodesEnumerbtion



} // End of clbss DefbultMutbbleTreeNode
