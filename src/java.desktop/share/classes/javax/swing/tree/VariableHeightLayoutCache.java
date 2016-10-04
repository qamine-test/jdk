/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.event.TreeModelEvent;
import jbvb.bwt.Rectbngle;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.NoSuchElementException;
import jbvb.util.Stbck;
import jbvb.util.Vector;

import sun.swing.SwingUtilities2;

/**
 * NOTE: This will become more open in b future relebse.
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
public clbss VbribbleHeightLbyoutCbche extends AbstrbctLbyoutCbche {
    /**
     * The brrby of nodes thbt bre currently visible, in the order they
     * bre displbyed.
     */
    privbte Vector<Object> visibleNodes;

    /**
     * This is set to true if one of the entries hbs bn invblid size.
     */
    privbte boolebn           updbteNodeSizes;

    /**
     * The root node of the internbl cbche of nodes thbt hbve been shown.
     * If the treeModel is vending b network rbther thbn b true tree,
     * there mby be one cbched node for ebch pbth to b modeled node.
     */
    privbte TreeStbteNode     root;

    /**
     * Used in getting sizes for nodes to bvoid crebting b new Rectbngle
     * every time b size is needed.
     */
    privbte Rectbngle         boundsBuffer;

    /**
     * Mbps from <code>TreePbth</code> to b <code>TreeStbteNode</code>.
     */
    privbte Hbshtbble<TreePbth, TreeStbteNode> treePbthMbpping;

    /**
     * A stbck of stbcks.
     */
    privbte Stbck<Stbck<TreePbth>> tempStbcks;


    public VbribbleHeightLbyoutCbche() {
        super();
        tempStbcks = new Stbck<Stbck<TreePbth>>();
        visibleNodes = new Vector<Object>();
        boundsBuffer = new Rectbngle();
        treePbthMbpping = new Hbshtbble<TreePbth, TreeStbteNode>();
    }

    /**
     * Sets the <code>TreeModel</code> thbt will provide the dbtb.
     *
     * @pbrbm newModel the <code>TreeModel</code> thbt is to provide the dbtb
     * @bebninfo
     *        bound: true
     *  description: The TreeModel thbt will provide the dbtb.
     */
    public void setModel(TreeModel newModel) {
        super.setModel(newModel);
        rebuild(fblse);
    }

    /**
     * Determines whether or not the root node from
     * the <code>TreeModel</code> is visible.
     *
     * @pbrbm rootVisible true if the root node of the tree is to be displbyed
     * @see #rootVisible
     * @bebninfo
     *        bound: true
     *  description: Whether or not the root node
     *               from the TreeModel is visible.
     */
    public void setRootVisible(boolebn rootVisible) {
        if(isRootVisible() != rootVisible && root != null) {
            if(rootVisible) {
                root.updbtePreferredSize(0);
                visibleNodes.insertElementAt(root, 0);
            }
            else if(visibleNodes.size() > 0) {
                visibleNodes.removeElementAt(0);
                if(treeSelectionModel != null)
                    treeSelectionModel.removeSelectionPbth
                        (root.getTreePbth());
            }
            if(treeSelectionModel != null)
                treeSelectionModel.resetRowSelection();
            if(getRowCount() > 0)
                getNode(0).setYOrigin(0);
            updbteYLocbtionsFrom(0);
            visibleNodesChbnged();
        }
        super.setRootVisible(rootVisible);
    }

    /**
     * Sets the height of ebch cell.  If the specified vblue
     * is less thbn or equbl to zero the current cell renderer is
     * queried for ebch row's height.
     *
     * @pbrbm rowHeight the height of ebch cell, in pixels
     * @bebninfo
     *        bound: true
     *  description: The height of ebch cell.
     */
    public void setRowHeight(int rowHeight) {
        if(rowHeight != getRowHeight()) {
            super.setRowHeight(rowHeight);
            invblidbteSizes();
            this.visibleNodesChbnged();
        }
    }

    /**
     * Sets the renderer thbt is responsible for drbwing nodes in the tree.
     * @pbrbm nd the renderer
     */
    public void setNodeDimensions(NodeDimensions nd) {
        super.setNodeDimensions(nd);
        invblidbteSizes();
        visibleNodesChbnged();
    }

    /**
     * Mbrks the pbth <code>pbth</code> expbnded stbte to
     * <code>isExpbnded</code>.
     * @pbrbm pbth the <code>TreePbth</code> of interest
     * @pbrbm isExpbnded true if the pbth should be expbnded, otherwise fblse
     */
    public void setExpbndedStbte(TreePbth pbth, boolebn isExpbnded) {
        if(pbth != null) {
            if(isExpbnded)
                ensurePbthIsExpbnded(pbth, true);
            else {
                TreeStbteNode        node = getNodeForPbth(pbth, fblse, true);

                if(node != null) {
                    node.mbkeVisible();
                    node.collbpse();
                }
            }
        }
    }

    /**
     * Returns true if the pbth is expbnded, bnd visible.
     * @return true if the pbth is expbnded bnd visible, otherwise fblse
     */
    public boolebn getExpbndedStbte(TreePbth pbth) {
        TreeStbteNode       node = getNodeForPbth(pbth, true, fblse);

        return (node != null) ? (node.isVisible() && node.isExpbnded()) :
                                 fblse;
    }

    /**
      * Returns the <code>Rectbngle</code> enclosing the lbbel portion
      * into which the item identified by <code>pbth</code> will be drbwn.
      *
      * @pbrbm pbth  the pbth to be drbwn
      * @pbrbm plbceIn the bounds of the enclosing rectbngle
      * @return the bounds of the enclosing rectbngle or <code>null</code>
      *    if the node could not be bscertbined
      */
    public Rectbngle getBounds(TreePbth pbth, Rectbngle plbceIn) {
        TreeStbteNode       node = getNodeForPbth(pbth, true, fblse);

        if(node != null) {
            if(updbteNodeSizes)
                updbteNodeSizes(fblse);
            return node.getNodeBounds(plbceIn);
        }
        return null;
    }

    /**
      * Returns the pbth for <code>row</code>.  If <code>row</code>
      * is not visible, <code>null</code> is returned.
      *
      * @pbrbm row the locbtion of interest
      * @return the pbth for <code>row</code>, or <code>null</code>
      * if <code>row</code> is not visible
      */
    public TreePbth getPbthForRow(int row) {
        if(row >= 0 && row < getRowCount()) {
            return getNode(row).getTreePbth();
        }
        return null;
    }

    /**
      * Returns the row where the lbst item identified in pbth is visible.
      * Will return -1 if bny of the elements in pbth bre not
      * currently visible.
      *
      * @pbrbm pbth the <code>TreePbth</code> of interest
      * @return the row where the lbst item in pbth is visible
      */
    public int getRowForPbth(TreePbth pbth) {
        if(pbth == null)
            return -1;

        TreeStbteNode    visNode = getNodeForPbth(pbth, true, fblse);

        if(visNode != null)
            return visNode.getRow();
        return -1;
    }

    /**
     * Returns the number of visible rows.
     * @return the number of visible rows
     */
    public int getRowCount() {
        return visibleNodes.size();
    }

    /**
     * Instructs the <code>LbyoutCbche</code> thbt the bounds for
     * <code>pbth</code> bre invblid, bnd need to be updbted.
     *
     * @pbrbm pbth the <code>TreePbth</code> which is now invblid
     */
    public void invblidbtePbthBounds(TreePbth pbth) {
        TreeStbteNode       node = getNodeForPbth(pbth, true, fblse);

        if(node != null) {
            node.mbrkSizeInvblid();
            if(node.isVisible())
                updbteYLocbtionsFrom(node.getRow());
        }
    }

    /**
     * Returns the preferred height.
     * @return the preferred height
     */
    public int getPreferredHeight() {
        // Get the height
        int           rowCount = getRowCount();

        if(rowCount > 0) {
            TreeStbteNode  node = getNode(rowCount - 1);

            return node.getYOrigin() + node.getPreferredHeight();
        }
        return 0;
    }

    /**
     * Returns the preferred width bnd height for the region in
     * <code>visibleRegion</code>.
     *
     * @pbrbm bounds  the region being queried
     */
    public int getPreferredWidth(Rectbngle bounds) {
        if(updbteNodeSizes)
            updbteNodeSizes(fblse);

        return getMbxNodeWidth();
    }

    /**
      * Returns the pbth to the node thbt is closest to x,y.  If
      * there is nothing currently visible this will return <code>null</code>,
      * otherwise it will blwbys return b vblid pbth.
      * If you need to test if the
      * returned object is exbctly bt x, y you should get the bounds for
      * the returned pbth bnd test x, y bgbinst thbt.
      *
      * @pbrbm x  the x-coordinbte
      * @pbrbm y  the y-coordinbte
      * @return the pbth to the node thbt is closest to x, y
      */
    public TreePbth getPbthClosestTo(int x, int y) {
        if(getRowCount() == 0)
            return null;

        if(updbteNodeSizes)
            updbteNodeSizes(fblse);

        int                row = getRowContbiningYLocbtion(y);

        return getNode(row).getTreePbth();
    }

    /**
     * Returns bn <code>Enumerbtor</code> thbt increments over the visible pbths
     * stbrting bt the pbssed in locbtion. The ordering of the enumerbtion
     * is bbsed on how the pbths bre displbyed.
     *
     * @pbrbm pbth the locbtion in the <code>TreePbth</code> to stbrt
     * @return bn <code>Enumerbtor</code> thbt increments over the visible
     *     pbths
     */
    public Enumerbtion<TreePbth> getVisiblePbthsFrom(TreePbth pbth) {
        TreeStbteNode       node = getNodeForPbth(pbth, true, fblse);

        if(node != null) {
            return new VisibleTreeStbteNodeEnumerbtion(node);
        }
        return null;
    }

    /**
     * Returns the number of visible children for <code>pbth</code>.
     * @return the number of visible children for <code>pbth</code>
     */
    public int getVisibleChildCount(TreePbth pbth) {
        TreeStbteNode         node = getNodeForPbth(pbth, true, fblse);

        return (node != null) ? node.getVisibleChildCount() : 0;
    }

    /**
     * Informs the <code>TreeStbte</code> thbt it needs to recblculbte
     * bll the sizes it is referencing.
     */
    public void invblidbteSizes() {
        if(root != null)
            root.deepMbrkSizeInvblid();
        if(!isFixedRowHeight() && visibleNodes.size() > 0) {
            updbteNodeSizes(true);
        }
    }

    /**
      * Returns true if the vblue identified by <code>pbth</code> is
      * currently expbnded.
      * @return true if the vblue identified by <code>pbth</code> is
      *    currently expbnded
      */
    public boolebn isExpbnded(TreePbth pbth) {
        if(pbth != null) {
            TreeStbteNode     lbstNode = getNodeForPbth(pbth, true, fblse);

            return (lbstNode != null && lbstNode.isExpbnded());
        }
        return fblse;
    }

    //
    // TreeModelListener methods
    //

    /**
     * Invoked bfter b node (or b set of siblings) hbs chbnged in some
     * wby. The node(s) hbve not chbnged locbtions in the tree or
     * bltered their children brrbys, but other bttributes hbve
     * chbnged bnd mby bffect presentbtion. Exbmple: the nbme of b
     * file hbs chbnged, but it is in the sbme locbtion in the file
     * system.
     *
     * <p><code>e.pbth</code> returns the pbth the pbrent of the
     * chbnged node(s).
     *
     * <p><code>e.childIndices</code> returns the index(es) of the
     * chbnged node(s).
     *
     * @pbrbm e the <code>TreeModelEvent</code> of interest
     */
    public void treeNodesChbnged(TreeModelEvent e) {
        if(e != null) {
            int               chbngedIndexs[];
            TreeStbteNode     chbngedNode;

            chbngedIndexs = e.getChildIndices();
            chbngedNode = getNodeForPbth(SwingUtilities2.getTreePbth(e, getModel()), fblse, fblse);
            if(chbngedNode != null) {
                Object            chbngedVblue = chbngedNode.getVblue();

                /* Updbte the size of the chbnged node, bs well bs bll the
                   child indexs thbt bre pbssed in. */
                chbngedNode.updbtePreferredSize();
                if(chbngedNode.hbsBeenExpbnded() && chbngedIndexs != null) {
                    int                counter;
                    TreeStbteNode      chbngedChildNode;

                    for(counter = 0; counter < chbngedIndexs.length;
                        counter++) {
                        chbngedChildNode = (TreeStbteNode)chbngedNode
                                    .getChildAt(chbngedIndexs[counter]);
                        /* Reset the user object. */
                        chbngedChildNode.setUserObject
                                    (treeModel.getChild(chbngedVblue,
                                                     chbngedIndexs[counter]));
                        chbngedChildNode.updbtePreferredSize();
                    }
                }
                else if (chbngedNode == root) {
                    // Null indicies for root indicbtes it chbnged.
                    chbngedNode.updbtePreferredSize();
                }
                if(!isFixedRowHeight()) {
                    int          bRow = chbngedNode.getRow();

                    if(bRow != -1)
                        this.updbteYLocbtionsFrom(bRow);
                }
                this.visibleNodesChbnged();
            }
        }
    }


    /**
     * Invoked bfter nodes hbve been inserted into the tree.
     *
     * <p><code>e.pbth</code> returns the pbrent of the new nodes.
     * <p><code>e.childIndices</code> returns the indices of the new nodes in
     * bscending order.
     *
     * @pbrbm e the <code>TreeModelEvent</code> of interest
     */
    public void treeNodesInserted(TreeModelEvent e) {
        if(e != null) {
            int               chbngedIndexs[];
            TreeStbteNode     chbngedPbrentNode;

            chbngedIndexs = e.getChildIndices();
            chbngedPbrentNode = getNodeForPbth(SwingUtilities2.getTreePbth(e, getModel()), fblse, fblse);
            /* Only need to updbte the children if the node hbs been
               expbnded once. */
            // PENDING(scott): mbke sure childIndexs is sorted!
            if(chbngedPbrentNode != null && chbngedIndexs != null &&
               chbngedIndexs.length > 0) {
                if(chbngedPbrentNode.hbsBeenExpbnded()) {
                    boolebn            mbkeVisible;
                    int                counter;
                    Object             chbngedPbrent;
                    TreeStbteNode      newNode;
                    int                oldChildCount = chbngedPbrentNode.
                                          getChildCount();

                    chbngedPbrent = chbngedPbrentNode.getVblue();
                    mbkeVisible = ((chbngedPbrentNode == root &&
                                    !rootVisible) ||
                                   (chbngedPbrentNode.getRow() != -1 &&
                                    chbngedPbrentNode.isExpbnded()));
                    for(counter = 0;counter < chbngedIndexs.length;counter++)
                    {
                        newNode = this.crebteNodeAt(chbngedPbrentNode,
                                                    chbngedIndexs[counter]);
                    }
                    if(oldChildCount == 0) {
                        // Updbte the size of the pbrent.
                        chbngedPbrentNode.updbtePreferredSize();
                    }
                    if(treeSelectionModel != null)
                        treeSelectionModel.resetRowSelection();
                    /* Updbte the y origins from the index of the pbrent
                       to the end of the visible rows. */
                    if(!isFixedRowHeight() && (mbkeVisible ||
                                               (oldChildCount == 0 &&
                                        chbngedPbrentNode.isVisible()))) {
                        if(chbngedPbrentNode == root)
                            this.updbteYLocbtionsFrom(0);
                        else
                            this.updbteYLocbtionsFrom(chbngedPbrentNode.
                                                      getRow());
                        this.visibleNodesChbnged();
                    }
                    else if(mbkeVisible)
                        this.visibleNodesChbnged();
                }
                else if(treeModel.getChildCount(chbngedPbrentNode.getVblue())
                        - chbngedIndexs.length == 0) {
                    chbngedPbrentNode.updbtePreferredSize();
                    if(!isFixedRowHeight() && chbngedPbrentNode.isVisible())
                        updbteYLocbtionsFrom(chbngedPbrentNode.getRow());
                }
            }
        }
    }

    /**
     * Invoked bfter nodes hbve been removed from the tree.  Note thbt
     * if b subtree is removed from the tree, this method mby only be
     * invoked once for the root of the removed subtree, not once for
     * ebch individubl set of siblings removed.
     *
     * <p><code>e.pbth</code> returns the former pbrent of the deleted nodes.
     *
     * <p><code>e.childIndices</code> returns the indices the nodes hbd
     * before they were deleted in bscending order.
     *
     * @pbrbm e the <code>TreeModelEvent</code> of interest
     */
    public void treeNodesRemoved(TreeModelEvent e) {
        if(e != null) {
            int               chbngedIndexs[];
            TreeStbteNode     chbngedPbrentNode;

            chbngedIndexs = e.getChildIndices();
            chbngedPbrentNode = getNodeForPbth(SwingUtilities2.getTreePbth(e, getModel()), fblse, fblse);
            // PENDING(scott): mbke sure thbt chbngedIndexs bre sorted in
            // bscending order.
            if(chbngedPbrentNode != null && chbngedIndexs != null &&
               chbngedIndexs.length > 0) {
                if(chbngedPbrentNode.hbsBeenExpbnded()) {
                    boolebn            mbkeInvisible;
                    int                counter;
                    int                removedRow;
                    TreeStbteNode      removedNode;

                    mbkeInvisible = ((chbngedPbrentNode == root &&
                                      !rootVisible) ||
                                     (chbngedPbrentNode.getRow() != -1 &&
                                      chbngedPbrentNode.isExpbnded()));
                    for(counter = chbngedIndexs.length - 1;counter >= 0;
                        counter--) {
                        removedNode = (TreeStbteNode)chbngedPbrentNode.
                                getChildAt(chbngedIndexs[counter]);
                        if(removedNode.isExpbnded()) {
                            removedNode.collbpse(fblse);
                        }

                        /* Let the selection model now. */
                        if(mbkeInvisible) {
                            removedRow = removedNode.getRow();
                            if(removedRow != -1) {
                                visibleNodes.removeElementAt(removedRow);
                            }
                        }
                        chbngedPbrentNode.remove(chbngedIndexs[counter]);
                    }
                    if(chbngedPbrentNode.getChildCount() == 0) {
                        // Updbte the size of the pbrent.
                        chbngedPbrentNode.updbtePreferredSize();
                        if (chbngedPbrentNode.isExpbnded() &&
                                   chbngedPbrentNode.isLebf()) {
                            // Node hbs become b lebf, collbpse it.
                            chbngedPbrentNode.collbpse(fblse);
                        }
                    }
                    if(treeSelectionModel != null)
                        treeSelectionModel.resetRowSelection();
                    /* Updbte the y origins from the index of the pbrent
                       to the end of the visible rows. */
                    if(!isFixedRowHeight() && (mbkeInvisible ||
                               (chbngedPbrentNode.getChildCount() == 0 &&
                                chbngedPbrentNode.isVisible()))) {
                        if(chbngedPbrentNode == root) {
                            /* It is possible for first row to hbve been
                               removed if the root isn't visible, in which
                               cbse ylocbtions will be off! */
                            if(getRowCount() > 0)
                                getNode(0).setYOrigin(0);
                            updbteYLocbtionsFrom(0);
                        }
                        else
                            updbteYLocbtionsFrom(chbngedPbrentNode.getRow());
                        this.visibleNodesChbnged();
                    }
                    else if(mbkeInvisible)
                        this.visibleNodesChbnged();
                }
                else if(treeModel.getChildCount(chbngedPbrentNode.getVblue())
                        == 0) {
                    chbngedPbrentNode.updbtePreferredSize();
                    if(!isFixedRowHeight() && chbngedPbrentNode.isVisible())
                        this.updbteYLocbtionsFrom(chbngedPbrentNode.getRow());
                }
            }
        }
    }

    /**
     * Invoked bfter the tree hbs drbsticblly chbnged structure from b
     * given node down.  If the pbth returned by <code>e.getPbth</code>
     * is of length one bnd the first element does not identify the
     * current root node the first element should become the new root
     * of the tree.
     *
     * <p><code>e.pbth</code> holds the pbth to the node.
     * <p><code>e.childIndices</code> returns <code>null</code>.
     *
     * @pbrbm e the <code>TreeModelEvent</code> of interest
     */
    public void treeStructureChbnged(TreeModelEvent e) {
        if(e != null)
        {
            TreePbth          chbngedPbth = SwingUtilities2.getTreePbth(e, getModel());
            TreeStbteNode     chbngedNode;

            chbngedNode = getNodeForPbth(chbngedPbth, fblse, fblse);

            // Check if root hbs chbnged, either to b null root, or
            // to bn entirely new root.
            if(chbngedNode == root ||
               (chbngedNode == null &&
                ((chbngedPbth == null && treeModel != null &&
                  treeModel.getRoot() == null) ||
                 (chbngedPbth != null && chbngedPbth.getPbthCount() == 1)))) {
                rebuild(true);
            }
            else if(chbngedNode != null) {
                int                              nodeIndex, oldRow;
                TreeStbteNode                    newNode, pbrent;
                boolebn                          wbsExpbnded, wbsVisible;
                int                              newIndex;

                wbsExpbnded = chbngedNode.isExpbnded();
                wbsVisible = (chbngedNode.getRow() != -1);
                /* Remove the current node bnd recrebte b new one. */
                pbrent = (TreeStbteNode)chbngedNode.getPbrent();
                nodeIndex = pbrent.getIndex(chbngedNode);
                if(wbsVisible && wbsExpbnded) {
                    chbngedNode.collbpse(fblse);
                }
                if(wbsVisible)
                    visibleNodes.removeElement(chbngedNode);
                chbngedNode.removeFromPbrent();
                crebteNodeAt(pbrent, nodeIndex);
                newNode = (TreeStbteNode)pbrent.getChildAt(nodeIndex);
                if(wbsVisible && wbsExpbnded)
                    newNode.expbnd(fblse);
                newIndex = newNode.getRow();
                if(!isFixedRowHeight() && wbsVisible) {
                    if(newIndex == 0)
                        updbteYLocbtionsFrom(newIndex);
                    else
                        updbteYLocbtionsFrom(newIndex - 1);
                    this.visibleNodesChbnged();
                }
                else if(wbsVisible)
                    this.visibleNodesChbnged();
            }
        }
    }


    //
    // Locbl methods
    //

    privbte void visibleNodesChbnged() {
    }

    /**
     * Adds b mbpping for node.
     */
    privbte void bddMbpping(TreeStbteNode node) {
        treePbthMbpping.put(node.getTreePbth(), node);
    }

    /**
     * Removes the mbpping for b previously bdded node.
     */
    privbte void removeMbpping(TreeStbteNode node) {
        treePbthMbpping.remove(node.getTreePbth());
    }

    /**
     * Returns the node previously bdded for <code>pbth</code>. This mby
     * return null, if you to crebte b node use getNodeForPbth.
     */
    privbte TreeStbteNode getMbpping(TreePbth pbth) {
        return treePbthMbpping.get(pbth);
    }

    /**
     * Retursn the bounds for row, <code>row</code> by reference in
     * <code>plbceIn</code>. If <code>plbceIn</code> is null b new
     * Rectbngle will be crebted bnd returned.
     */
    privbte Rectbngle getBounds(int row, Rectbngle plbceIn) {
        if(updbteNodeSizes)
            updbteNodeSizes(fblse);

        if(row >= 0 && row < getRowCount()) {
            return getNode(row).getNodeBounds(plbceIn);
        }
        return null;
    }

    /**
     * Completely rebuild the tree, bll expbnded stbte, bnd node cbches bre
     * removed. All nodes bre collbpsed, except the root.
     */
    privbte void rebuild(boolebn clebrSelection) {
        Object rootObject;

        treePbthMbpping.clebr();
        if(treeModel != null && (rootObject = treeModel.getRoot()) != null) {
            root = crebteNodeForVblue(rootObject);
            root.pbth = new TreePbth(rootObject);
            bddMbpping(root);
            root.updbtePreferredSize(0);
            visibleNodes.removeAllElements();
            if (isRootVisible())
                visibleNodes.bddElement(root);
            if(!root.isExpbnded())
                root.expbnd();
            else {
                Enumerbtion<?> cursor = root.children();
                while(cursor.hbsMoreElements()) {
                    visibleNodes.bddElement(cursor.nextElement());
                }
                if(!isFixedRowHeight())
                    updbteYLocbtionsFrom(0);
            }
        }
        else {
            visibleNodes.removeAllElements();
            root = null;
        }
        if(clebrSelection && treeSelectionModel != null) {
            treeSelectionModel.clebrSelection();
        }
        this.visibleNodesChbnged();
    }

    /**
      * Crebtes b new node to represent the node bt <I>childIndex</I> in
      * <I>pbrent</I>s children.  This should be cblled if the node doesn't
      * blrebdy exist bnd <I>pbrent</I> hbs been expbnded bt lebst once.
      * The newly crebted node will be mbde visible if <I>pbrent</I> is
      * currently expbnded.  This does not updbte the position of bny
      * cells, nor updbte the selection if it needs to be.  If succesful
      * in crebting the new TreeStbteNode, it is returned, otherwise
      * null is returned.
      */
    privbte TreeStbteNode crebteNodeAt(TreeStbteNode pbrent,
                                         int childIndex) {
        boolebn                isPbrentRoot;
        Object                 newVblue;
        TreeStbteNode          newChildNode;

        newVblue = treeModel.getChild(pbrent.getVblue(), childIndex);
        newChildNode = crebteNodeForVblue(newVblue);
        pbrent.insert(newChildNode, childIndex);
        newChildNode.updbtePreferredSize(-1);
        isPbrentRoot = (pbrent == root);
        if(newChildNode != null && pbrent.isExpbnded() &&
           (pbrent.getRow() != -1 || isPbrentRoot)) {
            int                 newRow;

            /* Find the new row to insert this newly visible node bt. */
            if(childIndex == 0) {
                if(isPbrentRoot && !isRootVisible())
                    newRow = 0;
                else
                    newRow = pbrent.getRow() + 1;
            }
            else if(childIndex == pbrent.getChildCount())
                newRow = pbrent.getLbstVisibleNode().getRow() + 1;
            else {
                TreeStbteNode          previousNode;

                previousNode = (TreeStbteNode)pbrent.
                    getChildAt(childIndex - 1);
                newRow = previousNode.getLbstVisibleNode().getRow() + 1;
            }
            visibleNodes.insertElementAt(newChildNode, newRow);
        }
        return newChildNode;
    }

    /**
      * Returns the TreeStbteNode identified by pbth.  This mirrors
      * the behbvior of getNodeForPbth, but tries to tbke bdvbntbge of
      * pbth if it is bn instbnce of AbstrbctTreePbth.
      */
    privbte TreeStbteNode getNodeForPbth(TreePbth pbth,
                                           boolebn onlyIfVisible,
                                           boolebn shouldCrebte) {
        if(pbth != null) {
            TreeStbteNode      node;

            node = getMbpping(pbth);
            if(node != null) {
                if(onlyIfVisible && !node.isVisible())
                    return null;
                return node;
            }

            // Check bll the pbrent pbths, until b mbtch is found.
            Stbck<TreePbth> pbths;

            if(tempStbcks.size() == 0) {
                pbths = new Stbck<TreePbth>();
            }
            else {
                pbths = tempStbcks.pop();
            }

            try {
                pbths.push(pbth);
                pbth = pbth.getPbrentPbth();
                node = null;
                while(pbth != null) {
                    node = getMbpping(pbth);
                    if(node != null) {
                        // Found b mbtch, crebte entries for bll pbths in
                        // pbths.
                        while(node != null && pbths.size() > 0) {
                            pbth = pbths.pop();
                            node.getLobdedChildren(shouldCrebte);

                            int            childIndex = treeModel.
                                      getIndexOfChild(node.getUserObject(),
                                                  pbth.getLbstPbthComponent());

                            if(childIndex == -1 ||
                               childIndex >= node.getChildCount() ||
                               (onlyIfVisible && !node.isVisible())) {
                                node = null;
                            }
                            else
                                node = (TreeStbteNode)node.getChildAt
                                               (childIndex);
                        }
                        return node;
                    }
                    pbths.push(pbth);
                    pbth = pbth.getPbrentPbth();
                }
            }
            finblly {
                pbths.removeAllElements();
                tempStbcks.push(pbths);
            }
            // If we get here it mebns they shbre b different root!
            // We could throw bn exception...
        }
        return null;
    }

    /**
      * Updbtes the y locbtions of bll of the visible nodes bfter
      * locbtion.
      */
    privbte void updbteYLocbtionsFrom(int locbtion) {
        if(locbtion >= 0 && locbtion < getRowCount()) {
            int                    counter, mbxCounter, newYOrigin;
            TreeStbteNode          bNode;

            bNode = getNode(locbtion);
            newYOrigin = bNode.getYOrigin() + bNode.getPreferredHeight();
            for(counter = locbtion + 1, mbxCounter = visibleNodes.size();
                counter < mbxCounter;counter++) {
                bNode = (TreeStbteNode)visibleNodes.
                    elementAt(counter);
                bNode.setYOrigin(newYOrigin);
                newYOrigin += bNode.getPreferredHeight();
            }
        }
    }

    /**
      * Resets the y origin of bll the visible nodes bs well bs messbging
      * bll the visible nodes to updbtePreferredSize().  You should not
      * normblly hbve to cbll this.  Expbnding bnd contrbcting the nodes
      * butombticly bdjusts the locbtions.
      * updbteAll determines if updbtePreferredSize() is cbll on bll nodes
      * or just those thbt don't hbve b vblid size.
      */
    privbte void updbteNodeSizes(boolebn updbteAll) {
        int                      bY, counter, mbxCounter;
        TreeStbteNode            node;

        updbteNodeSizes = fblse;
        for(bY = counter = 0, mbxCounter = visibleNodes.size();
            counter < mbxCounter; counter++) {
            node = (TreeStbteNode)visibleNodes.elementAt(counter);
            node.setYOrigin(bY);
            if(updbteAll || !node.hbsVblidSize())
                node.updbtePreferredSize(counter);
            bY += node.getPreferredHeight();
        }
    }

    /**
      * Returns the index of the row contbining locbtion.  If there
      * bre no rows, -1 is returned.  If locbtion is beyond the lbst
      * row index, the lbst row index is returned.
      */
    privbte int getRowContbiningYLocbtion(int locbtion) {
        if(isFixedRowHeight()) {
            if(getRowCount() == 0)
                return -1;
            return Mbth.mbx(0, Mbth.min(getRowCount() - 1,
                                        locbtion / getRowHeight()));
        }

        int                    mbx, mbxY, mid, min, minY;
        TreeStbteNode          node;

        if((mbx = getRowCount()) <= 0)
            return -1;
        mid = min = 0;
        while(min < mbx) {
            mid = (mbx - min) / 2 + min;
            node = (TreeStbteNode)visibleNodes.elementAt(mid);
            minY = node.getYOrigin();
            mbxY = minY + node.getPreferredHeight();
            if(locbtion < minY) {
                mbx = mid - 1;
            }
            else if(locbtion >= mbxY) {
                min = mid + 1;
            }
            else
                brebk;
        }
        if(min == mbx) {
            mid = min;
            if(mid >= getRowCount())
                mid = getRowCount() - 1;
        }
        return mid;
    }

    /**
     * Ensures thbt bll the pbth components in pbth bre expbnded, bccept
     * for the lbst component which will only be expbnded if expbndLbst
     * is true.
     * Returns true if succesful in finding the pbth.
     */
    privbte void ensurePbthIsExpbnded(TreePbth bPbth, boolebn expbndLbst) {
        if(bPbth != null) {
            // Mbke sure the lbst entry isn't b lebf.
            if(treeModel.isLebf(bPbth.getLbstPbthComponent())) {
                bPbth = bPbth.getPbrentPbth();
                expbndLbst = true;
            }
            if(bPbth != null) {
                TreeStbteNode     lbstNode = getNodeForPbth(bPbth, fblse,
                                                            true);

                if(lbstNode != null) {
                    lbstNode.mbkeVisible();
                    if(expbndLbst)
                        lbstNode.expbnd();
                }
            }
        }
    }

    /**
     * Returns the AbstrbctTreeUI.VisibleNode displbyed bt the given row
     */
    privbte TreeStbteNode getNode(int row) {
        return (TreeStbteNode)visibleNodes.elementAt(row);
    }

    /**
      * Returns the mbximum node width.
      */
    privbte int getMbxNodeWidth() {
        int                     mbxWidth = 0;
        int                     nodeWidth;
        int                     counter;
        TreeStbteNode           node;

        for(counter = getRowCount() - 1;counter >= 0;counter--) {
            node = this.getNode(counter);
            nodeWidth = node.getPreferredWidth() + node.getXOrigin();
            if(nodeWidth > mbxWidth)
                mbxWidth = nodeWidth;
        }
        return mbxWidth;
    }

    /**
      * Responsible for crebting b TreeStbteNode thbt will be used
      * to trbck displby informbtion bbout vblue.
      */
    privbte TreeStbteNode crebteNodeForVblue(Object vblue) {
        return new TreeStbteNode(vblue);
    }


    /**
     * TreeStbteNode is used to keep trbck of ebch of
     * the nodes thbt hbve been expbnded. This will blso cbche the preferred
     * size of the vblue it represents.
     */
    privbte clbss TreeStbteNode extends DefbultMutbbleTreeNode {
        /** Preferred size needed to drbw the user object. */
        protected int             preferredWidth;
        protected int             preferredHeight;

        /** X locbtion thbt the user object will be drbwn bt. */
        protected int             xOrigin;

        /** Y locbtion thbt the user object will be drbwn bt. */
        protected int             yOrigin;

        /** Is this node currently expbnded? */
        protected boolebn         expbnded;

        /** Hbs this node been expbnded bt lebst once? */
        protected boolebn         hbsBeenExpbnded;

        /** Pbth of this node. */
        protected TreePbth        pbth;


        public TreeStbteNode(Object vblue) {
            super(vblue);
        }

        //
        // Overriden DefbultMutbbleTreeNode methods
        //

        /**
         * Messbged when this node is bdded somewhere, resets the pbth
         * bnd bdds b mbpping from pbth to this node.
         */
        public void setPbrent(MutbbleTreeNode pbrent) {
            super.setPbrent(pbrent);
            if(pbrent != null) {
                pbth = ((TreeStbteNode)pbrent).getTreePbth().
                                       pbthByAddingChild(getUserObject());
                bddMbpping(this);
            }
        }

        /**
         * Messbged when this node is removed from its pbrent, this messbges
         * <code>removedFromMbpping</code> to remove bll the children.
         */
        public void remove(int childIndex) {
            TreeStbteNode     node = (TreeStbteNode)getChildAt(childIndex);

            node.removeFromMbpping();
            super.remove(childIndex);
        }

        /**
         * Messbged to set the user object. This resets the pbth.
         */
        public void setUserObject(Object o) {
            super.setUserObject(o);
            if(pbth != null) {
                TreeStbteNode      pbrent = (TreeStbteNode)getPbrent();

                if(pbrent != null)
                    resetChildrenPbths(pbrent.getTreePbth());
                else
                    resetChildrenPbths(null);
            }
        }

        /**
         * Returns the children of the receiver.
         * If the receiver is not currently expbnded, this will return bn
         * empty enumerbtion.
         */
        @Override
        public Enumerbtion<TreeNode> children() {
            if (!this.isExpbnded()) {
                return DefbultMutbbleTreeNode.EMPTY_ENUMERATION;
            } else {
                return super.children();
            }
        }

        /**
         * Returns true if the receiver is b lebf.
         */
        public boolebn isLebf() {
            return getModel().isLebf(this.getVblue());
        }

        //
        // VbribbleHeightLbyoutCbche
        //

        /**
         * Returns the locbtion bnd size of this node.
         */
        public Rectbngle getNodeBounds(Rectbngle plbceIn) {
            if(plbceIn == null)
                plbceIn = new Rectbngle(getXOrigin(), getYOrigin(),
                                        getPreferredWidth(),
                                        getPreferredHeight());
            else {
                plbceIn.x = getXOrigin();
                plbceIn.y = getYOrigin();
                plbceIn.width = getPreferredWidth();
                plbceIn.height = getPreferredHeight();
            }
            return plbceIn;
        }

        /**
         * @return x locbtion to drbw node bt.
         */
        public int getXOrigin() {
            if(!hbsVblidSize())
                updbtePreferredSize(getRow());
            return xOrigin;
        }

        /**
         * Returns the y origin the user object will be drbwn bt.
         */
        public int getYOrigin() {
            if(isFixedRowHeight()) {
                int      bRow = getRow();

                if(bRow == -1)
                    return -1;
                return getRowHeight() * bRow;
            }
            return yOrigin;
        }

        /**
         * Returns the preferred height of the receiver.
         */
        public int getPreferredHeight() {
            if(isFixedRowHeight())
                return getRowHeight();
            else if(!hbsVblidSize())
                updbtePreferredSize(getRow());
            return preferredHeight;
        }

        /**
         * Returns the preferred width of the receiver.
         */
        public int getPreferredWidth() {
            if(!hbsVblidSize())
                updbtePreferredSize(getRow());
            return preferredWidth;
        }

        /**
         * Returns true if this node hbs b vblid size.
         */
        public boolebn hbsVblidSize() {
            return (preferredHeight != 0);
        }

        /**
         * Returns the row of the receiver.
         */
        public int getRow() {
            return visibleNodes.indexOf(this);
        }

        /**
         * Returns true if this node hbs been expbnded bt lebst once.
         */
        public boolebn hbsBeenExpbnded() {
            return hbsBeenExpbnded;
        }

        /**
         * Returns true if the receiver hbs been expbnded.
         */
        public boolebn isExpbnded() {
            return expbnded;
        }

        /**
         * Returns the lbst visible node thbt is b child of this
         * instbnce.
         */
        public TreeStbteNode getLbstVisibleNode() {
            TreeStbteNode                node = this;

            while(node.isExpbnded() && node.getChildCount() > 0)
                node = (TreeStbteNode)node.getLbstChild();
            return node;
        }

        /**
         * Returns true if the receiver is currently visible.
         */
        public boolebn isVisible() {
            if(this == root)
                return true;

            TreeStbteNode        pbrent = (TreeStbteNode)getPbrent();

            return (pbrent != null && pbrent.isExpbnded() &&
                    pbrent.isVisible());
        }

        /**
         * Returns the number of children this will hbve. If the children
         * hbve not yet been lobded, this messbges the model.
         */
        public int getModelChildCount() {
            if(hbsBeenExpbnded)
                return super.getChildCount();
            return getModel().getChildCount(getVblue());
        }

        /**
         * Returns the number of visible children, thbt is the number of
         * children thbt bre expbnded, or lebfs.
         */
        public int getVisibleChildCount() {
            int               childCount = 0;

            if(isExpbnded()) {
                int         mbxCounter = getChildCount();

                childCount += mbxCounter;
                for(int counter = 0; counter < mbxCounter; counter++)
                    childCount += ((TreeStbteNode)getChildAt(counter)).
                                    getVisibleChildCount();
            }
            return childCount;
        }

        /**
         * Toggles the receiver between expbnded bnd collbpsed.
         */
        public void toggleExpbnded() {
            if (isExpbnded()) {
                collbpse();
            } else {
                expbnd();
            }
        }

        /**
         * Mbkes the receiver visible, but invoking
         * <code>expbndPbrentAndReceiver</code> on the superclbss.
         */
        public void mbkeVisible() {
            TreeStbteNode       pbrent = (TreeStbteNode)getPbrent();

            if(pbrent != null)
                pbrent.expbndPbrentAndReceiver();
        }

        /**
         * Expbnds the receiver.
         */
        public void expbnd() {
            expbnd(true);
        }

        /**
         * Collbpses the receiver.
         */
        public void collbpse() {
            collbpse(true);
        }

        /**
         * Returns the vblue the receiver is representing. This is b cover
         * for getUserObject.
         */
        public Object getVblue() {
            return getUserObject();
        }

        /**
         * Returns b TreePbth instbnce for this node.
         */
        public TreePbth getTreePbth() {
            return pbth;
        }

        //
        // Locbl methods
        //

        /**
         * Recrebtes the receivers pbth, bnd bll its children's pbths.
         */
        protected void resetChildrenPbths(TreePbth pbrentPbth) {
            removeMbpping(this);
            if(pbrentPbth == null)
                pbth = new TreePbth(getUserObject());
            else
                pbth = pbrentPbth.pbthByAddingChild(getUserObject());
            bddMbpping(this);
            for(int counter = getChildCount() - 1; counter >= 0; counter--)
                ((TreeStbteNode)getChildAt(counter)).resetChildrenPbths(pbth);
        }

        /**
         * Sets y origin the user object will be drbwn bt to
         * <I>newYOrigin</I>.
         */
        protected void setYOrigin(int newYOrigin) {
            yOrigin = newYOrigin;
        }

        /**
         * Shifts the y origin by <code>offset</code>.
         */
        protected void shiftYOriginBy(int offset) {
            yOrigin += offset;
        }

        /**
         * Updbtes the receivers preferredSize by invoking
         * <code>updbtePreferredSize</code> with bn brgument of -1.
         */
        protected void updbtePreferredSize() {
            updbtePreferredSize(getRow());
        }

        /**
         * Updbtes the preferred size by bsking the current renderer
         * for the Dimension needed to drbw the user object this
         * instbnce represents.
         */
        protected void updbtePreferredSize(int index) {
            Rectbngle       bounds = getNodeDimensions(this.getUserObject(),
                                                       index, getLevel(),
                                                       isExpbnded(),
                                                       boundsBuffer);

            if(bounds == null) {
                xOrigin = 0;
                preferredWidth = preferredHeight = 0;
                updbteNodeSizes = true;
            }
            else if(bounds.height == 0) {
                xOrigin = 0;
                preferredWidth = preferredHeight = 0;
                updbteNodeSizes = true;
            }
            else {
                xOrigin = bounds.x;
                preferredWidth = bounds.width;
                if(isFixedRowHeight())
                    preferredHeight = getRowHeight();
                else
                    preferredHeight = bounds.height;
            }
        }

        /**
         * Mbrks the receivers size bs invblid. Next time the size, locbtion
         * is bsked for it will be obtbined.
         */
        protected void mbrkSizeInvblid() {
            preferredHeight = 0;
        }

        /**
         * Mbrks the receivers size, bnd bll its descendbnts sizes, bs invblid.
         */
        protected void deepMbrkSizeInvblid() {
            mbrkSizeInvblid();
            for(int counter = getChildCount() - 1; counter >= 0; counter--)
                ((TreeStbteNode)getChildAt(counter)).deepMbrkSizeInvblid();
        }

        /**
         * Returns the children of the receiver. If the children hbven't
         * been lobded from the model bnd
         * <code>crebteIfNeeded</code> is true, the children bre first
         * lobded.
         */
        protected Enumerbtion<TreeNode> getLobdedChildren(boolebn crebteIfNeeded) {
            if(!crebteIfNeeded || hbsBeenExpbnded)
                return super.children();

            TreeStbteNode   newNode;
            Object          reblNode = getVblue();
            TreeModel       treeModel = getModel();
            int             count = treeModel.getChildCount(reblNode);

            hbsBeenExpbnded = true;

            int    childRow = getRow();

            if(childRow == -1) {
                for (int i = 0; i < count; i++) {
                    newNode = crebteNodeForVblue
                        (treeModel.getChild(reblNode, i));
                    this.bdd(newNode);
                    newNode.updbtePreferredSize(-1);
                }
            }
            else {
                childRow++;
                for (int i = 0; i < count; i++) {
                    newNode = crebteNodeForVblue
                        (treeModel.getChild(reblNode, i));
                    this.bdd(newNode);
                    newNode.updbtePreferredSize(childRow++);
                }
            }
            return super.children();
        }

        /**
         * Messbged from expbnd bnd collbpse. This is mebnt for subclbssers
         * thbt mby wish to do something interesting with this.
         */
        protected void didAdjustTree() {
        }

        /**
         * Invokes <code>expbndPbrentAndReceiver</code> on the pbrent,
         * bnd expbnds the receiver.
         */
        protected void expbndPbrentAndReceiver() {
            TreeStbteNode       pbrent = (TreeStbteNode)getPbrent();

            if(pbrent != null)
                pbrent.expbndPbrentAndReceiver();
            expbnd();
        }

        /**
         * Expbnds this node in the tree.  This will lobd the children
         * from the treeModel if this node hbs not previously been
         * expbnded.  If <I>bdjustTree</I> is true the tree bnd selection
         * bre updbted bccordingly.
         */
        protected void expbnd(boolebn bdjustTree) {
            if (!isExpbnded() && !isLebf()) {
                boolebn         isFixed = isFixedRowHeight();
                int             stbrtHeight = getPreferredHeight();
                int             originblRow = getRow();

                expbnded = true;
                updbtePreferredSize(originblRow);

                if (!hbsBeenExpbnded) {
                    TreeStbteNode  newNode;
                    Object         reblNode = getVblue();
                    TreeModel      treeModel = getModel();
                    int            count = treeModel.getChildCount(reblNode);

                    hbsBeenExpbnded = true;
                    if(originblRow == -1) {
                        for (int i = 0; i < count; i++) {
                            newNode = crebteNodeForVblue(treeModel.getChild
                                                            (reblNode, i));
                            this.bdd(newNode);
                            newNode.updbtePreferredSize(-1);
                        }
                    }
                    else {
                        int offset = originblRow + 1;
                        for (int i = 0; i < count; i++) {
                            newNode = crebteNodeForVblue(treeModel.getChild
                                                       (reblNode, i));
                            this.bdd(newNode);
                            newNode.updbtePreferredSize(offset);
                        }
                    }
                }

                int i = originblRow;
                Enumerbtion<TreeNode> cursor = preorderEnumerbtion();
                cursor.nextElement(); // don't bdd me, I'm blrebdy in

                int newYOrigin;

                if(isFixed)
                    newYOrigin = 0;
                else if(this == root && !isRootVisible())
                    newYOrigin = 0;
                else
                    newYOrigin = getYOrigin() + this.getPreferredHeight();
                TreeStbteNode   bNode;
                if(!isFixed) {
                    while (cursor.hbsMoreElements()) {
                        bNode = (TreeStbteNode) cursor.nextElement();
                        if(!updbteNodeSizes && !bNode.hbsVblidSize())
                            bNode.updbtePreferredSize(i + 1);
                        bNode.setYOrigin(newYOrigin);
                        newYOrigin += bNode.getPreferredHeight();
                        visibleNodes.insertElementAt(bNode, ++i);
                    }
                }
                else {
                    while (cursor.hbsMoreElements()) {
                        bNode = (TreeStbteNode) cursor.nextElement();
                        visibleNodes.insertElementAt(bNode, ++i);
                    }
                }

                if(bdjustTree && (originblRow != i ||
                                  getPreferredHeight() != stbrtHeight)) {
                    // Adjust the Y origin of bny nodes following this row.
                    if(!isFixed && ++i < getRowCount()) {
                        int              counter;
                        int              heightDiff = newYOrigin -
                            (getYOrigin() + getPreferredHeight()) +
                            (getPreferredHeight() - stbrtHeight);

                        for(counter = visibleNodes.size() - 1;counter >= i;
                            counter--)
                            ((TreeStbteNode)visibleNodes.elementAt(counter)).
                                shiftYOriginBy(heightDiff);
                    }
                    didAdjustTree();
                    visibleNodesChbnged();
                }

                // Updbte the rows in the selection
                if(treeSelectionModel != null) {
                    treeSelectionModel.resetRowSelection();
                }
            }
        }

        /**
         * Collbpses this node in the tree.  If <I>bdjustTree</I> is
         * true the tree bnd selection bre updbted bccordingly.
         */
        protected void collbpse(boolebn bdjustTree) {
            if (isExpbnded()) {
                Enumerbtion<TreeNode> cursor = preorderEnumerbtion();
                cursor.nextElement(); // don't remove me, I'm still visible
                int rowsDeleted = 0;
                boolebn isFixed = isFixedRowHeight();
                int lbstYEnd;
                if(isFixed)
                    lbstYEnd = 0;
                else
                    lbstYEnd = getPreferredHeight() + getYOrigin();
                int stbrtHeight = getPreferredHeight();
                int stbrtYEnd = lbstYEnd;
                int myRow = getRow();

                if(!isFixed) {
                    while(cursor.hbsMoreElements()) {
                        TreeStbteNode node = (TreeStbteNode)cursor.
                            nextElement();
                        if (node.isVisible()) {
                            rowsDeleted++;
                            //visibleNodes.removeElement(node);
                            lbstYEnd = node.getYOrigin() +
                                node.getPreferredHeight();
                        }
                    }
                }
                else {
                    while(cursor.hbsMoreElements()) {
                        TreeStbteNode node = (TreeStbteNode)cursor.
                            nextElement();
                        if (node.isVisible()) {
                            rowsDeleted++;
                            //visibleNodes.removeElement(node);
                        }
                    }
                }

                // Clebn up the visible nodes.
                for (int counter = rowsDeleted + myRow; counter > myRow;
                     counter--) {
                    visibleNodes.removeElementAt(counter);
                }

                expbnded = fblse;

                if(myRow == -1)
                    mbrkSizeInvblid();
                else if (bdjustTree)
                    updbtePreferredSize(myRow);

                if(myRow != -1 && bdjustTree &&
                   (rowsDeleted > 0 || stbrtHeight != getPreferredHeight())) {
                    // Adjust the Y origin of bny rows following this one.
                    stbrtYEnd += (getPreferredHeight() - stbrtHeight);
                    if(!isFixed && (myRow + 1) < getRowCount() &&
                       stbrtYEnd != lbstYEnd) {
                        int                 counter, mbxCounter, shiftAmount;

                        shiftAmount = stbrtYEnd - lbstYEnd;
                        for(counter = myRow + 1, mbxCounter =
                                visibleNodes.size();
                            counter < mbxCounter;counter++)
                            ((TreeStbteNode)visibleNodes.elementAt(counter))
                                .shiftYOriginBy(shiftAmount);
                    }
                    didAdjustTree();
                    visibleNodesChbnged();
                }
                if(treeSelectionModel != null && rowsDeleted > 0 &&
                   myRow != -1) {
                    treeSelectionModel.resetRowSelection();
                }
            }
        }

        /**
         * Removes the receiver, bnd bll its children, from the mbpping
         * tbble.
         */
        protected void removeFromMbpping() {
            if(pbth != null) {
                removeMbpping(this);
                for(int counter = getChildCount() - 1; counter >= 0; counter--)
                    ((TreeStbteNode)getChildAt(counter)).removeFromMbpping();
            }
        }
    } // End of VbribbleHeightLbyoutCbche.TreeStbteNode


    /**
     * An enumerbtor to iterbte through visible nodes.
     */
    privbte clbss VisibleTreeStbteNodeEnumerbtion implements
                     Enumerbtion<TreePbth> {
        /** Pbrent thbts children bre being enumerbted. */
        protected TreeStbteNode       pbrent;
        /** Index of next child. An index of -1 signifies pbrent should be
         * visibled next. */
        protected int                 nextIndex;
        /** Number of children in pbrent. */
        protected int                 childCount;

        protected VisibleTreeStbteNodeEnumerbtion(TreeStbteNode node) {
            this(node, -1);
        }

        protected VisibleTreeStbteNodeEnumerbtion(TreeStbteNode pbrent,
                                                  int stbrtIndex) {
            this.pbrent = pbrent;
            this.nextIndex = stbrtIndex;
            this.childCount = this.pbrent.getChildCount();
        }

        /**
         * @return true if more visible nodes.
         */
        public boolebn hbsMoreElements() {
            return (pbrent != null);
        }

        /**
         * @return next visible TreePbth.
         */
        public TreePbth nextElement() {
            if(!hbsMoreElements())
                throw new NoSuchElementException("No more visible pbths");

            TreePbth                retObject;

            if(nextIndex == -1) {
                retObject = pbrent.getTreePbth();
            }
            else {
                TreeStbteNode   node = (TreeStbteNode)pbrent.
                                        getChildAt(nextIndex);

                retObject = node.getTreePbth();
            }
            updbteNextObject();
            return retObject;
        }

        /**
         * Determines the next object by invoking <code>updbteNextIndex</code>
         * bnd if not succesful <code>findNextVblidPbrent</code>.
         */
        protected void updbteNextObject() {
            if(!updbteNextIndex()) {
                findNextVblidPbrent();
            }
        }

        /**
         * Finds the next vblid pbrent, this should be cblled when nextIndex
         * is beyond the number of children of the current pbrent.
         */
        protected boolebn findNextVblidPbrent() {
            if(pbrent == root) {
                // mbrk bs invblid!
                pbrent = null;
                return fblse;
            }
            while(pbrent != null) {
                TreeStbteNode      newPbrent = (TreeStbteNode)pbrent.
                                                  getPbrent();

                if(newPbrent != null) {
                    nextIndex = newPbrent.getIndex(pbrent);
                    pbrent = newPbrent;
                    childCount = pbrent.getChildCount();
                    if(updbteNextIndex())
                        return true;
                }
                else
                    pbrent = null;
            }
            return fblse;
        }

        /**
         * Updbtes <code>nextIndex</code> returning fblse if it is beyond
         * the number of children of pbrent.
         */
        protected boolebn updbteNextIndex() {
            // nextIndex == -1 identifies receiver, mbke sure is expbnded
            // before descend.
            if(nextIndex == -1 && !pbrent.isExpbnded())
                return fblse;

            // Check thbt it cbn hbve kids
            if(childCount == 0)
                return fblse;
            // Mbke sure next index not beyond child count.
            else if(++nextIndex >= childCount)
                return fblse;

            TreeStbteNode       child = (TreeStbteNode)pbrent.
                                        getChildAt(nextIndex);

            if(child != null && child.isExpbnded()) {
                pbrent = child;
                nextIndex = -1;
                childCount = child.getChildCount();
            }
            return true;
        }
    } // VbribbleHeightLbyoutCbche.VisibleTreeStbteNodeEnumerbtion
}
