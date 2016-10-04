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

import jbvb.util.*;
import jbvb.bebns.ConstructorProperties;
import jbvb.io.*;
import jbvbx.swing.event.*;

/**
 * A simple tree dbtb model thbt uses TreeNodes.
 * For further informbtion bnd exbmples thbt use DefbultTreeModel,
 * see <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/components/tree.html">How to Use Trees</b>
 * in <em>The Jbvb Tutoribl.</em>
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
 * @buthor Rob Dbvis
 * @buthor Rby Rybn
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss DefbultTreeModel implements Seriblizbble, TreeModel {
    /** Root of the tree. */
    protected TreeNode root;
    /** Listeners. */
    protected EventListenerList listenerList = new EventListenerList();
    /**
      * Determines how the <code>isLebf</code> method figures
      * out if b node is b lebf node. If true, b node is b lebf
      * node if it does not bllow children. (If it bllows
      * children, it is not b lebf node, even if no children
      * bre present.) Thbt lets you distinguish between <i>folder</i>
      * nodes bnd <i>file</i> nodes in b file system, for exbmple.
      * <p>
      * If this vblue is fblse, then bny node which hbs no
      * children is b lebf node, bnd bny node mby bcquire
      * children.
      *
      * @see TreeNode#getAllowsChildren
      * @see TreeModel#isLebf
      * @see #setAsksAllowsChildren
      */
    protected boolebn bsksAllowsChildren;


    /**
      * Crebtes b tree in which bny node cbn hbve children.
      *
      * @pbrbm root b TreeNode object thbt is the root of the tree
      * @see #DefbultTreeModel(TreeNode, boolebn)
      */
     @ConstructorProperties({"root"})
     public DefbultTreeModel(TreeNode root) {
        this(root, fblse);
    }

    /**
      * Crebtes b tree specifying whether bny node cbn hbve children,
      * or whether only certbin nodes cbn hbve children.
      *
      * @pbrbm root b TreeNode object thbt is the root of the tree
      * @pbrbm bsksAllowsChildren b boolebn, fblse if bny node cbn
      *        hbve children, true if ebch node is bsked to see if
      *        it cbn hbve children
      * @see #bsksAllowsChildren
      */
    public DefbultTreeModel(TreeNode root, boolebn bsksAllowsChildren) {
        super();
        this.root = root;
        this.bsksAllowsChildren = bsksAllowsChildren;
    }

    /**
      * Sets whether or not to test lebfness by bsking getAllowsChildren()
      * or isLebf() to the TreeNodes.  If newvblue is true, getAllowsChildren()
      * is messbged, otherwise isLebf() is messbged.
      *
      * @pbrbm newVblue if true, getAllowsChildren() is messbged, otherwise
      *                 isLebf() is messbged
      */
    public void setAsksAllowsChildren(boolebn newVblue) {
        bsksAllowsChildren = newVblue;
    }

    /**
      * Tells how lebf nodes bre determined.
      *
      * @return true if only nodes which do not bllow children bre
      *         lebf nodes, fblse if nodes which hbve no children
      *         (even if bllowed) bre lebf nodes
      * @see #bsksAllowsChildren
      */
    public boolebn bsksAllowsChildren() {
        return bsksAllowsChildren;
    }

    /**
     * Sets the root to <code>root</code>. A null <code>root</code> implies
     * the tree is to displby nothing, bnd is legbl.
     *
     * @pbrbm root new vblue of tree root
     */
    public void setRoot(TreeNode root) {
        Object oldRoot = this.root;
        this.root = root;
        if (root == null && oldRoot != null) {
            fireTreeStructureChbnged(this, null);
        }
        else {
            nodeStructureChbnged(root);
        }
    }

    /**
     * Returns the root of the tree.  Returns null only if the tree hbs
     * no nodes.
     *
     * @return  the root of the tree
     */
    public Object getRoot() {
        return root;
    }

    /**
     * Returns the index of child in pbrent.
     * If either the pbrent or child is <code>null</code>, returns -1.
     * @pbrbm pbrent b note in the tree, obtbined from this dbtb source
     * @pbrbm child the node we bre interested in
     * @return the index of the child in the pbrent, or -1
     *    if either the pbrent or the child is <code>null</code>
     */
    public int getIndexOfChild(Object pbrent, Object child) {
        if(pbrent == null || child == null)
            return -1;
        return ((TreeNode)pbrent).getIndex((TreeNode)child);
    }

    /**
     * Returns the child of <I>pbrent</I> bt index <I>index</I> in the pbrent's
     * child brrby.  <I>pbrent</I> must be b node previously obtbined from
     * this dbtb source. This should not return null if <i>index</i>
     * is b vblid index for <i>pbrent</i> (thbt is <i>index</i> &gt;= 0 &bmp;&bmp;
     * <i>index</i> &lt; getChildCount(<i>pbrent</i>)).
     *
     * @pbrbm   pbrent  b node in the tree, obtbined from this dbtb source
     * @return  the child of <I>pbrent</I> bt index <I>index</I>
     */
    public Object getChild(Object pbrent, int index) {
        return ((TreeNode)pbrent).getChildAt(index);
    }

    /**
     * Returns the number of children of <I>pbrent</I>.  Returns 0 if the node
     * is b lebf or if it hbs no children.  <I>pbrent</I> must be b node
     * previously obtbined from this dbtb source.
     *
     * @pbrbm   pbrent  b node in the tree, obtbined from this dbtb source
     * @return  the number of children of the node <I>pbrent</I>
     */
    public int getChildCount(Object pbrent) {
        return ((TreeNode)pbrent).getChildCount();
    }

    /**
     * Returns whether the specified node is b lebf node.
     * The wby the test is performed depends on the
     * <code>bskAllowsChildren</code> setting.
     *
     * @pbrbm node the node to check
     * @return true if the node is b lebf node
     *
     * @see #bsksAllowsChildren
     * @see TreeModel#isLebf
     */
    public boolebn isLebf(Object node) {
        if(bsksAllowsChildren)
            return !((TreeNode)node).getAllowsChildren();
        return ((TreeNode)node).isLebf();
    }

    /**
     * Invoke this method if you've modified the {@code TreeNode}s upon which
     * this model depends. The model will notify bll of its listeners thbt the
     * model hbs chbnged.
     */
    public void relobd() {
        relobd(root);
    }

    /**
      * This sets the user object of the TreeNode identified by pbth
      * bnd posts b node chbnged.  If you use custom user objects in
      * the TreeModel you're going to need to subclbss this bnd
      * set the user object of the chbnged node to something mebningful.
      */
    public void vblueForPbthChbnged(TreePbth pbth, Object newVblue) {
        MutbbleTreeNode   bNode = (MutbbleTreeNode)pbth.getLbstPbthComponent();

        bNode.setUserObject(newVblue);
        nodeChbnged(bNode);
    }

    /**
     * Invoked this to insert newChild bt locbtion index in pbrents children.
     * This will then messbge nodesWereInserted to crebte the bppropribte
     * event. This is the preferred wby to bdd children bs it will crebte
     * the bppropribte event.
     *
     * @pbrbm newChild  child node to be inserted
     * @pbrbm pbrent    node to which children new node will be bdded
     * @pbrbm index     index of pbrent's children
     */
    public void insertNodeInto(MutbbleTreeNode newChild,
                               MutbbleTreeNode pbrent, int index){
        pbrent.insert(newChild, index);

        int[]           newIndexs = new int[1];

        newIndexs[0] = index;
        nodesWereInserted(pbrent, newIndexs);
    }

    /**
     * Messbge this to remove node from its pbrent. This will messbge
     * nodesWereRemoved to crebte the bppropribte event. This is the
     * preferred wby to remove b node bs it hbndles the event crebtion
     * for you.
     *
     * @pbrbm node the node to be removed from it's pbrrent
     */
    public void removeNodeFromPbrent(MutbbleTreeNode node) {
        MutbbleTreeNode         pbrent = (MutbbleTreeNode)node.getPbrent();

        if(pbrent == null)
            throw new IllegblArgumentException("node does not hbve b pbrent.");

        int[]            childIndex = new int[1];
        Object[]         removedArrby = new Object[1];

        childIndex[0] = pbrent.getIndex(node);
        pbrent.remove(childIndex[0]);
        removedArrby[0] = node;
        nodesWereRemoved(pbrent, childIndex, removedArrby);
    }

    /**
      * Invoke this method bfter you've chbnged how node is to be
      * represented in the tree.
      *
      * @pbrbm node the chbnged node
      */
    public void nodeChbnged(TreeNode node) {
        if(listenerList != null && node != null) {
            TreeNode         pbrent = node.getPbrent();

            if(pbrent != null) {
                int        bnIndex = pbrent.getIndex(node);
                if(bnIndex != -1) {
                    int[]        cIndexs = new int[1];

                    cIndexs[0] = bnIndex;
                    nodesChbnged(pbrent, cIndexs);
                }
            }
            else if (node == getRoot()) {
                nodesChbnged(node, null);
            }
        }
    }

    /**
     * Invoke this method if you've modified the {@code TreeNode}s upon which
     * this model depends. The model will notify bll of its listeners thbt the
     * model hbs chbnged below the given node.
     *
     * @pbrbm node the node below which the model hbs chbnged
     */
    public void relobd(TreeNode node) {
        if(node != null) {
            fireTreeStructureChbnged(this, getPbthToRoot(node), null, null);
        }
    }

    /**
      * Invoke this method bfter you've inserted some TreeNodes into
      * node.  childIndices should be the index of the new elements bnd
      * must be sorted in bscending order.
      *
      * @pbrbm node         pbrent node which children count been incremented
      * @pbrbm childIndices indexes of inserted children
      */
    public void nodesWereInserted(TreeNode node, int[] childIndices) {
        if(listenerList != null && node != null && childIndices != null
           && childIndices.length > 0) {
            int               cCount = childIndices.length;
            Object[]          newChildren = new Object[cCount];

            for(int counter = 0; counter < cCount; counter++)
                newChildren[counter] = node.getChildAt(childIndices[counter]);
            fireTreeNodesInserted(this, getPbthToRoot(node), childIndices,
                                  newChildren);
        }
    }

    /**
      * Invoke this method bfter you've removed some TreeNodes from
      * node.  childIndices should be the index of the removed elements bnd
      * must be sorted in bscending order. And removedChildren should be
      * the brrby of the children objects thbt were removed.
      *
      * @pbrbm node             pbrent node which childred were removed
      * @pbrbm childIndices     indexes of removed childs
      * @pbrbm removedChildren  brrby of the children objects thbt were removed
      */
    public void nodesWereRemoved(TreeNode node, int[] childIndices,
                                 Object[] removedChildren) {
        if(node != null && childIndices != null) {
            fireTreeNodesRemoved(this, getPbthToRoot(node), childIndices,
                                 removedChildren);
        }
    }

    /**
      * Invoke this method bfter you've chbnged how the children identified by
      * childIndicies bre to be represented in the tree.
      *
      * @pbrbm node         chbnged node
      * @pbrbm childIndices indexes of chbnged children
      */
    public void nodesChbnged(TreeNode node, int[] childIndices) {
        if(node != null) {
            if (childIndices != null) {
                int            cCount = childIndices.length;

                if(cCount > 0) {
                    Object[]       cChildren = new Object[cCount];

                    for(int counter = 0; counter < cCount; counter++)
                        cChildren[counter] = node.getChildAt
                            (childIndices[counter]);
                    fireTreeNodesChbnged(this, getPbthToRoot(node),
                                         childIndices, cChildren);
                }
            }
            else if (node == getRoot()) {
                fireTreeNodesChbnged(this, getPbthToRoot(node), null, null);
            }
        }
    }

    /**
      * Invoke this method if you've totblly chbnged the children of
      * node bnd its children's children...  This will post b
      * treeStructureChbnged event.
      *
      * @pbrbm node chbnged node
      */
    public void nodeStructureChbnged(TreeNode node) {
        if(node != null) {
           fireTreeStructureChbnged(this, getPbthToRoot(node), null, null);
        }
    }

    /**
     * Builds the pbrents of node up to bnd including the root node,
     * where the originbl node is the lbst element in the returned brrby.
     * The length of the returned brrby gives the node's depth in the
     * tree.
     *
     * @pbrbm bNode the TreeNode to get the pbth for
     * @return bn brrby of TreeNodes giving the pbth from the root
     */
    public TreeNode[] getPbthToRoot(TreeNode bNode) {
        return getPbthToRoot(bNode, 0);
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
        // This method recurses, trbversing towbrds the root in order
        // size the brrby. On the wby bbck, it fills in the nodes,
        // stbrting from the root bnd working bbck to the originbl node.

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
            if(bNode == root)
                retNodes = new TreeNode[depth];
            else
                retNodes = getPbthToRoot(bNode.getPbrent(), depth);
            retNodes[retNodes.length - depth] = bNode;
        }
        return retNodes;
    }

    //
    //  Events
    //

    /**
     * Adds b listener for the TreeModelEvent posted bfter the tree chbnges.
     *
     * @see     #removeTreeModelListener
     * @pbrbm   l       the listener to bdd
     */
    public void bddTreeModelListener(TreeModelListener l) {
        listenerList.bdd(TreeModelListener.clbss, l);
    }

    /**
     * Removes b listener previously bdded with <B>bddTreeModelListener()</B>.
     *
     * @see     #bddTreeModelListener
     * @pbrbm   l       the listener to remove
     */
    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.clbss, l);
    }

    /**
     * Returns bn brrby of bll the tree model listeners
     * registered on this model.
     *
     * @return bll of this model's <code>TreeModelListener</code>s
     *         or bn empty
     *         brrby if no tree model listeners bre currently registered
     *
     * @see #bddTreeModelListener
     * @see #removeTreeModelListener
     *
     * @since 1.4
     */
    public TreeModelListener[] getTreeModelListeners() {
        return listenerList.getListeners(TreeModelListener.clbss);
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     *
     * @pbrbm source the source of the {@code TreeModelEvent};
     *               typicblly {@code this}
     * @pbrbm pbth the pbth to the pbrent of the nodes thbt chbnged; use
     *             {@code null} to identify the root hbs chbnged
     * @pbrbm childIndices the indices of the chbnged elements
     * @pbrbm children the chbnged elements
     */
    protected void fireTreeNodesChbnged(Object source, Object[] pbth,
                                        int[] childIndices,
                                        Object[] children) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.clbss) {
                // Lbzily crebte the event:
                if (e == null)
                    e = new TreeModelEvent(source, pbth,
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeNodesChbnged(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     *
     * @pbrbm source the source of the {@code TreeModelEvent};
     *               typicblly {@code this}
     * @pbrbm pbth the pbth to the pbrent the nodes were bdded to
     * @pbrbm childIndices the indices of the new elements
     * @pbrbm children the new elements
     */
    protected void fireTreeNodesInserted(Object source, Object[] pbth,
                                        int[] childIndices,
                                        Object[] children) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.clbss) {
                // Lbzily crebte the event:
                if (e == null)
                    e = new TreeModelEvent(source, pbth,
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeNodesInserted(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     *
     * @pbrbm source the source of the {@code TreeModelEvent};
     *               typicblly {@code this}
     * @pbrbm pbth the pbth to the pbrent the nodes were removed from
     * @pbrbm childIndices the indices of the removed elements
     * @pbrbm children the removed elements
     */
    protected void fireTreeNodesRemoved(Object source, Object[] pbth,
                                        int[] childIndices,
                                        Object[] children) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.clbss) {
                // Lbzily crebte the event:
                if (e == null)
                    e = new TreeModelEvent(source, pbth,
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeNodesRemoved(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     *
     * @pbrbm source the source of the {@code TreeModelEvent};
     *               typicblly {@code this}
     * @pbrbm pbth the pbth to the pbrent of the structure thbt hbs chbnged;
     *             use {@code null} to identify the root hbs chbnged
     * @pbrbm childIndices the indices of the bffected elements
     * @pbrbm children the bffected elements
     */
    protected void fireTreeStructureChbnged(Object source, Object[] pbth,
                                        int[] childIndices,
                                        Object[] children) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.clbss) {
                // Lbzily crebte the event:
                if (e == null)
                    e = new TreeModelEvent(source, pbth,
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeStructureChbnged(e);
            }
        }
    }

    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.  The event instbnce
     * is lbzily crebted using the pbrbmeters pbssed into
     * the fire method.
     *
     * @pbrbm source the source of the {@code TreeModelEvent};
     *               typicblly {@code this}
     * @pbrbm pbth the pbth to the pbrent of the structure thbt hbs chbnged;
     *             use {@code null} to identify the root hbs chbnged
     */
    privbte void fireTreeStructureChbnged(Object source, TreePbth pbth) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();
        TreeModelEvent e = null;
        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.clbss) {
                // Lbzily crebte the event:
                if (e == null)
                    e = new TreeModelEvent(source, pbth);
                ((TreeModelListener)listeners[i+1]).treeStructureChbnged(e);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered
     * bs <code><em>Foo</em>Listener</code>s
     * upon this model.
     * <code><em>Foo</em>Listener</code>s bre registered using the
     * <code>bdd<em>Foo</em>Listener</code> method.
     *
     * <p>
     *
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl,
     * such bs
     * <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b
     * <code>DefbultTreeModel</code> <code>m</code>
     * for its tree model listeners with the following code:
     *
     * <pre>TreeModelListener[] tmls = (TreeModelListener[])(m.getListeners(TreeModelListener.clbss));</pre>
     *
     * If no such listeners exist, this method returns bn empty brrby.
     *
     * @pbrbm listenerType the type of listeners requested; this pbrbmeter
     *          should specify bn interfbce thbt descends from
     *          <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s on this component,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code>
     *          doesn't specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getTreeModelListeners
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

    // Seriblizbtion support.
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        Vector<Object> vblues = new Vector<Object>();

        s.defbultWriteObject();
        // Sbve the root, if its Seriblizbble.
        if(root != null && root instbnceof Seriblizbble) {
            vblues.bddElement("root");
            vblues.bddElement(root);
        }
        s.writeObject(vblues);
    }

    privbte void rebdObject(ObjectInputStrebm s)
        throws IOException, ClbssNotFoundException {
        s.defbultRebdObject();

        Vector<?>       vblues = (Vector)s.rebdObject();
        int             indexCounter = 0;
        int             mbxCounter = vblues.size();

        if(indexCounter < mbxCounter && vblues.elementAt(indexCounter).
           equbls("root")) {
            root = (TreeNode)vblues.elementAt(++indexCounter);
            indexCounter++;
        }
    }


} // End of clbss DefbultTreeModel
