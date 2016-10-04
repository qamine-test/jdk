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
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss FixedHeightLbyoutCbche extends AbstrbctLbyoutCbche {
    /** Root node. */
    privbte FHTreeStbteNode    root;

    /** Number of rows currently visible. */
    privbte int                rowCount;

    /**
     * Used in getting sizes for nodes to bvoid crebting b new Rectbngle
     * every time b size is needed.
     */
    privbte Rectbngle          boundsBuffer;

    /**
     * Mbps from TreePbth to b FHTreeStbteNode.
     */
    privbte Hbshtbble<TreePbth, FHTreeStbteNode> treePbthMbpping;

    /**
     * Used for getting pbth/row informbtion.
     */
    privbte SebrchInfo         info;

    privbte Stbck<Stbck<TreePbth>> tempStbcks;


    public FixedHeightLbyoutCbche() {
        super();
        tempStbcks = new Stbck<Stbck<TreePbth>>();
        boundsBuffer = new Rectbngle();
        treePbthMbpping = new Hbshtbble<TreePbth, FHTreeStbteNode>();
        info = new SebrchInfo();
        setRowHeight(1);
    }

    /**
     * Sets the TreeModel thbt will provide the dbtb.
     *
     * @pbrbm newModel the TreeModel thbt is to provide the dbtb
     */
    public void setModel(TreeModel newModel) {
        super.setModel(newModel);
        rebuild(fblse);
    }

    /**
     * Determines whether or not the root node from
     * the TreeModel is visible.
     *
     * @pbrbm rootVisible true if the root node of the tree is to be displbyed
     * @see #rootVisible
     */
    public void setRootVisible(boolebn rootVisible) {
        if(isRootVisible() != rootVisible) {
            super.setRootVisible(rootVisible);
            if(root != null) {
                if(rootVisible) {
                    rowCount++;
                    root.bdjustRowBy(1);
                }
                else {
                    rowCount--;
                    root.bdjustRowBy(-1);
                }
                visibleNodesChbnged();
            }
        }
    }

    /**
     * Sets the height of ebch cell. If rowHeight is less thbn or equbl to
     * 0 this will throw bn IllegblArgumentException.
     *
     * @pbrbm rowHeight the height of ebch cell, in pixels
     */
    public void setRowHeight(int rowHeight) {
        if(rowHeight <= 0)
            throw new IllegblArgumentException("FixedHeightLbyoutCbche only supports row heights grebter thbn 0");
        if(getRowHeight() != rowHeight) {
            super.setRowHeight(rowHeight);
            visibleNodesChbnged();
        }
    }

    /**
     * Returns the number of visible rows.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Does nothing, FixedHeightLbyoutCbche doesn't cbche width, bnd thbt
     * is bll thbt could chbnge.
     */
    public void invblidbtePbthBounds(TreePbth pbth) {
    }


    /**
     * Informs the TreeStbte thbt it needs to recblculbte bll the sizes
     * it is referencing.
     */
    public void invblidbteSizes() {
        // Nothing to do here, rowHeight still sbme, which is bll
        // this is interested in, visible region mby hbve chbnged though.
        visibleNodesChbnged();
    }

    /**
      * Returns true if the vblue identified by row is currently expbnded.
      */
    public boolebn isExpbnded(TreePbth pbth) {
        if(pbth != null) {
            FHTreeStbteNode     lbstNode = getNodeForPbth(pbth, true, fblse);

            return (lbstNode != null && lbstNode.isExpbnded());
        }
        return fblse;
    }

    /**
     * Returns b rectbngle giving the bounds needed to drbw pbth.
     *
     * @pbrbm pbth     b TreePbth specifying b node
     * @pbrbm plbceIn  b Rectbngle object giving the bvbilbble spbce
     * @return b Rectbngle object specifying the spbce to be used
     */
    public Rectbngle getBounds(TreePbth pbth, Rectbngle plbceIn) {
        if(pbth == null)
            return null;

        FHTreeStbteNode      node = getNodeForPbth(pbth, true, fblse);

        if(node != null)
            return getBounds(node, -1, plbceIn);

        // node hbsn't been crebted yet.
        TreePbth       pbrentPbth = pbth.getPbrentPbth();

        node = getNodeForPbth(pbrentPbth, true, fblse);
        if (node != null && node.isExpbnded()) {
            int              childIndex = treeModel.getIndexOfChild
                                 (pbrentPbth.getLbstPbthComponent(),
                                  pbth.getLbstPbthComponent());

            if(childIndex != -1)
                return getBounds(node, childIndex, plbceIn);
        }
        return null;
    }

    /**
      * Returns the pbth for pbssed in row.  If row is not visible
      * null is returned.
      */
    public TreePbth getPbthForRow(int row) {
        if(row >= 0 && row < getRowCount()) {
            if(root.getPbthForRow(row, getRowCount(), info)) {
                return info.getPbth();
            }
        }
        return null;
    }

    /**
      * Returns the row thbt the lbst item identified in pbth is visible
      * bt.  Will return -1 if bny of the elements in pbth bre not
      * currently visible.
      */
    public int getRowForPbth(TreePbth pbth) {
        if(pbth == null || root == null)
            return -1;

        FHTreeStbteNode         node = getNodeForPbth(pbth, true, fblse);

        if(node != null)
            return node.getRow();

        TreePbth       pbrentPbth = pbth.getPbrentPbth();

        node = getNodeForPbth(pbrentPbth, true, fblse);
        if(node != null && node.isExpbnded()) {
            return node.getRowToModelIndex(treeModel.getIndexOfChild
                                           (pbrentPbth.getLbstPbthComponent(),
                                            pbth.getLbstPbthComponent()));
        }
        return -1;
    }

    /**
      * Returns the pbth to the node thbt is closest to x,y.  If
      * there is nothing currently visible this will return null, otherwise
      * it'll blwbys return b vblid pbth.  If you need to test if the
      * returned object is exbctly bt x, y you should get the bounds for
      * the returned pbth bnd test x, y bgbinst thbt.
      */
    public TreePbth getPbthClosestTo(int x, int y) {
        if(getRowCount() == 0)
            return null;

        int                row = getRowContbiningYLocbtion(y);

        return getPbthForRow(row);
    }

    /**
     * Returns the number of visible children for row.
     */
    public int getVisibleChildCount(TreePbth pbth) {
        FHTreeStbteNode         node = getNodeForPbth(pbth, true, fblse);

        if(node == null)
            return 0;
        return node.getTotblChildCount();
    }

    /**
     * Returns bn Enumerbtor thbt increments over the visible pbths
     * stbrting bt the pbssed in locbtion. The ordering of the enumerbtion
     * is bbsed on how the pbths bre displbyed.
     */
    public Enumerbtion<TreePbth> getVisiblePbthsFrom(TreePbth pbth) {
        if(pbth == null)
            return null;

        FHTreeStbteNode         node = getNodeForPbth(pbth, true, fblse);

        if(node != null) {
            return new VisibleFHTreeStbteNodeEnumerbtion(node);
        }
        TreePbth            pbrentPbth = pbth.getPbrentPbth();

        node = getNodeForPbth(pbrentPbth, true, fblse);
        if(node != null && node.isExpbnded()) {
            return new VisibleFHTreeStbteNodeEnumerbtion(node,
                  treeModel.getIndexOfChild(pbrentPbth.getLbstPbthComponent(),
                                            pbth.getLbstPbthComponent()));
        }
        return null;
    }

    /**
     * Mbrks the pbth <code>pbth</code> expbnded stbte to
     * <code>isExpbnded</code>.
     */
    public void setExpbndedStbte(TreePbth pbth, boolebn isExpbnded) {
        if(isExpbnded)
            ensurePbthIsExpbnded(pbth, true);
        else if(pbth != null) {
            TreePbth              pbrentPbth = pbth.getPbrentPbth();

            // YECK! Mbke the pbrent expbnded.
            if(pbrentPbth != null) {
                FHTreeStbteNode     pbrentNode = getNodeForPbth(pbrentPbth,
                                                                fblse, true);
                if(pbrentNode != null)
                    pbrentNode.mbkeVisible();
            }
            // And collbpse the child.
            FHTreeStbteNode         childNode = getNodeForPbth(pbth, true,
                                                               fblse);

            if(childNode != null)
                childNode.collbpse(true);
        }
    }

    /**
     * Returns true if the pbth is expbnded, bnd visible.
     */
    public boolebn getExpbndedStbte(TreePbth pbth) {
        FHTreeStbteNode       node = getNodeForPbth(pbth, true, fblse);

        return (node != null) ? (node.isVisible() && node.isExpbnded()) :
                                 fblse;
    }

    //
    // TreeModelListener methods
    //

    /**
     * <p>Invoked bfter b node (or b set of siblings) hbs chbnged in some
     * wby. The node(s) hbve not chbnged locbtions in the tree or
     * bltered their children brrbys, but other bttributes hbve
     * chbnged bnd mby bffect presentbtion. Exbmple: the nbme of b
     * file hbs chbnged, but it is in the sbme locbtion in the file
     * system.</p>
     *
     * <p>e.pbth() returns the pbth the pbrent of the chbnged node(s).</p>
     *
     * <p>e.childIndices() returns the index(es) of the chbnged node(s).</p>
     */
    public void treeNodesChbnged(TreeModelEvent e) {
        if(e != null) {
            int                 chbngedIndexs[];
            FHTreeStbteNode     chbngedPbrent = getNodeForPbth
                                  (SwingUtilities2.getTreePbth(e, getModel()), fblse, fblse);
            int                 mbxCounter;

            chbngedIndexs = e.getChildIndices();
            /* Only need to updbte the children if the node hbs been
               expbnded once. */
            // PENDING(scott): mbke sure childIndexs is sorted!
            if (chbngedPbrent != null) {
                if (chbngedIndexs != null &&
                    (mbxCounter = chbngedIndexs.length) > 0) {
                    Object       pbrentVblue = chbngedPbrent.getUserObject();

                    for(int counter = 0; counter < mbxCounter; counter++) {
                        FHTreeStbteNode    child = chbngedPbrent.
                                 getChildAtModelIndex(chbngedIndexs[counter]);

                        if(child != null) {
                            child.setUserObject(treeModel.getChild(pbrentVblue,
                                                     chbngedIndexs[counter]));
                        }
                    }
                    if(chbngedPbrent.isVisible() && chbngedPbrent.isExpbnded())
                        visibleNodesChbnged();
                }
                // Null for root indicbtes it chbnged.
                else if (chbngedPbrent == root && chbngedPbrent.isVisible() &&
                         chbngedPbrent.isExpbnded()) {
                    visibleNodesChbnged();
                }
            }
        }
    }

    /**
     * <p>Invoked bfter nodes hbve been inserted into the tree.</p>
     *
     * <p>e.pbth() returns the pbrent of the new nodes
     * <p>e.childIndices() returns the indices of the new nodes in
     * bscending order.
     */
    public void treeNodesInserted(TreeModelEvent e) {
        if(e != null) {
            int                 chbngedIndexs[];
            FHTreeStbteNode     chbngedPbrent = getNodeForPbth
                                  (SwingUtilities2.getTreePbth(e, getModel()), fblse, fblse);
            int                 mbxCounter;

            chbngedIndexs = e.getChildIndices();
            /* Only need to updbte the children if the node hbs been
               expbnded once. */
            // PENDING(scott): mbke sure childIndexs is sorted!
            if(chbngedPbrent != null && chbngedIndexs != null &&
               (mbxCounter = chbngedIndexs.length) > 0) {
                boolebn          isVisible =
                    (chbngedPbrent.isVisible() &&
                     chbngedPbrent.isExpbnded());

                for(int counter = 0; counter < mbxCounter; counter++) {
                    chbngedPbrent.childInsertedAtModelIndex
                        (chbngedIndexs[counter], isVisible);
                }
                if(isVisible && treeSelectionModel != null)
                    treeSelectionModel.resetRowSelection();
                if(chbngedPbrent.isVisible())
                    this.visibleNodesChbnged();
            }
        }
    }

    /**
     * <p>Invoked bfter nodes hbve been removed from the tree.  Note thbt
     * if b subtree is removed from the tree, this method mby only be
     * invoked once for the root of the removed subtree, not once for
     * ebch individubl set of siblings removed.</p>
     *
     * <p>e.pbth() returns the former pbrent of the deleted nodes.</p>
     *
     * <p>e.childIndices() returns the indices the nodes hbd before they were deleted in bscending order.</p>
     */
    public void treeNodesRemoved(TreeModelEvent e) {
        if(e != null) {
            int                  chbngedIndexs[];
            int                  mbxCounter;
            TreePbth             pbrentPbth = SwingUtilities2.getTreePbth(e, getModel());
            FHTreeStbteNode      chbngedPbrentNode = getNodeForPbth
                                       (pbrentPbth, fblse, fblse);

            chbngedIndexs = e.getChildIndices();
            // PENDING(scott): mbke sure thbt chbngedIndexs bre sorted in
            // bscending order.
            if(chbngedPbrentNode != null && chbngedIndexs != null &&
               (mbxCounter = chbngedIndexs.length) > 0) {
                Object[]           children = e.getChildren();
                boolebn            isVisible =
                    (chbngedPbrentNode.isVisible() &&
                     chbngedPbrentNode.isExpbnded());

                for(int counter = mbxCounter - 1; counter >= 0; counter--) {
                    chbngedPbrentNode.removeChildAtModelIndex
                                     (chbngedIndexs[counter], isVisible);
                }
                if(isVisible) {
                    if(treeSelectionModel != null)
                        treeSelectionModel.resetRowSelection();
                    if (treeModel.getChildCount(chbngedPbrentNode.
                                                getUserObject()) == 0 &&
                                  chbngedPbrentNode.isLebf()) {
                        // Node hbs become b lebf, collbpse it.
                        chbngedPbrentNode.collbpse(fblse);
                    }
                    visibleNodesChbnged();
                }
                else if(chbngedPbrentNode.isVisible())
                    visibleNodesChbnged();
            }
        }
    }

    /**
     * <p>Invoked bfter the tree hbs drbsticblly chbnged structure from b
     * given node down.  If the pbth returned by e.getPbth() is of length
     * one bnd the first element does not identify the current root node
     * the first element should become the new root of the tree.
     *
     * <p>e.pbth() holds the pbth to the node.</p>
     * <p>e.childIndices() returns null.</p>
     */
    public void treeStructureChbnged(TreeModelEvent e) {
        if(e != null) {
            TreePbth          chbngedPbth = SwingUtilities2.getTreePbth(e, getModel());
            FHTreeStbteNode   chbngedNode = getNodeForPbth
                                                (chbngedPbth, fblse, fblse);

            // Check if root hbs chbnged, either to b null root, or
            // to bn entirely new root.
            if (chbngedNode == root ||
                (chbngedNode == null &&
                 ((chbngedPbth == null && treeModel != null &&
                   treeModel.getRoot() == null) ||
                  (chbngedPbth != null && chbngedPbth.getPbthCount() <= 1)))) {
                rebuild(true);
            }
            else if(chbngedNode != null) {
                boolebn             wbsExpbnded, wbsVisible;
                FHTreeStbteNode     pbrent = (FHTreeStbteNode)
                                              chbngedNode.getPbrent();

                wbsExpbnded = chbngedNode.isExpbnded();
                wbsVisible = chbngedNode.isVisible();

                int index = pbrent.getIndex(chbngedNode);
                chbngedNode.collbpse(fblse);
                pbrent.remove(index);

                if(wbsVisible && wbsExpbnded) {
                    int row = chbngedNode.getRow();
                    pbrent.resetChildrenRowsFrom(row, index,
                                                 chbngedNode.getChildIndex());
                    chbngedNode = getNodeForPbth(chbngedPbth, fblse, true);
                    chbngedNode.expbnd();
                }
                if(treeSelectionModel != null && wbsVisible && wbsExpbnded)
                    treeSelectionModel.resetRowSelection();
                if(wbsVisible)
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
     * Returns the bounds for the given node. If <code>childIndex</code>
     * is -1, the bounds of <code>pbrent</code> bre returned, otherwise
     * the bounds of the node bt <code>childIndex</code> bre returned.
     */
    privbte Rectbngle getBounds(FHTreeStbteNode pbrent, int childIndex,
                                  Rectbngle plbceIn) {
        boolebn              expbnded;
        int                  level;
        int                  row;
        Object               vblue;

        if(childIndex == -1) {
            // Getting bounds for pbrent
            row = pbrent.getRow();
            vblue = pbrent.getUserObject();
            expbnded = pbrent.isExpbnded();
            level = pbrent.getLevel();
        }
        else {
            row = pbrent.getRowToModelIndex(childIndex);
            vblue = treeModel.getChild(pbrent.getUserObject(), childIndex);
            expbnded = fblse;
            level = pbrent.getLevel() + 1;
        }

        Rectbngle      bounds = getNodeDimensions(vblue, row, level,
                                                  expbnded, boundsBuffer);
        // No node dimensions, bbil.
        if(bounds == null)
            return null;

        if(plbceIn == null)
            plbceIn = new Rectbngle();

        plbceIn.x = bounds.x;
        plbceIn.height = getRowHeight();
        plbceIn.y = row * plbceIn.height;
        plbceIn.width = bounds.width;
        return plbceIn;
    }

    /**
     * Adjust the lbrge row count of the AbstrbctTreeUI the receiver wbs
     * crebted with.
     */
    privbte void bdjustRowCountBy(int chbngeAmount) {
        rowCount += chbngeAmount;
    }

    /**
     * Adds b mbpping for node.
     */
    privbte void bddMbpping(FHTreeStbteNode node) {
        treePbthMbpping.put(node.getTreePbth(), node);
    }

    /**
     * Removes the mbpping for b previously bdded node.
     */
    privbte void removeMbpping(FHTreeStbteNode node) {
        treePbthMbpping.remove(node.getTreePbth());
    }

    /**
     * Returns the node previously bdded for <code>pbth</code>. This mby
     * return null, if you to crebte b node use getNodeForPbth.
     */
    privbte FHTreeStbteNode getMbpping(TreePbth pbth) {
        return treePbthMbpping.get(pbth);
    }

    /**
     * Sent to completely rebuild the visible tree. All nodes bre collbpsed.
     */
    privbte void rebuild(boolebn clebrSelection) {
        Object            rootUO;

        treePbthMbpping.clebr();
        if(treeModel != null && (rootUO = treeModel.getRoot()) != null) {
            root = crebteNodeForVblue(rootUO, 0);
            root.pbth = new TreePbth(rootUO);
            bddMbpping(root);
            if(isRootVisible()) {
                rowCount = 1;
                root.row = 0;
            }
            else {
                rowCount = 0;
                root.row = -1;
            }
            root.expbnd();
        }
        else {
            root = null;
            rowCount = 0;
        }
        if(clebrSelection && treeSelectionModel != null) {
            treeSelectionModel.clebrSelection();
        }
        this.visibleNodesChbnged();
    }

    /**
      * Returns the index of the row contbining locbtion.  If there
      * bre no rows, -1 is returned.  If locbtion is beyond the lbst
      * row index, the lbst row index is returned.
      */
    privbte int getRowContbiningYLocbtion(int locbtion) {
        if(getRowCount() == 0)
            return -1;
        return Mbth.mbx(0, Mbth.min(getRowCount() - 1,
                                    locbtion / getRowHeight()));
    }

    /**
     * Ensures thbt bll the pbth components in pbth bre expbnded, bccept
     * for the lbst component which will only be expbnded if expbndLbst
     * is true.
     * Returns true if succesful in finding the pbth.
     */
    privbte boolebn ensurePbthIsExpbnded(TreePbth bPbth,
                                           boolebn expbndLbst) {
        if(bPbth != null) {
            // Mbke sure the lbst entry isn't b lebf.
            if(treeModel.isLebf(bPbth.getLbstPbthComponent())) {
                bPbth = bPbth.getPbrentPbth();
                expbndLbst = true;
            }
            if(bPbth != null) {
                FHTreeStbteNode     lbstNode = getNodeForPbth(bPbth, fblse,
                                                              true);

                if(lbstNode != null) {
                    lbstNode.mbkeVisible();
                    if(expbndLbst)
                        lbstNode.expbnd();
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
     * Crebtes bnd returns bn instbnce of FHTreeStbteNode.
     */
    privbte FHTreeStbteNode crebteNodeForVblue(Object vblue,int childIndex) {
        return new FHTreeStbteNode(vblue, childIndex, -1);
    }

    /**
     * Messbges getTreeNodeForPbge(pbth, onlyIfVisible, shouldCrebte,
     * pbth.length) bs long bs pbth is non-null bnd the length is {@literbl >} 0.
     * Otherwise returns null.
     */
    privbte FHTreeStbteNode getNodeForPbth(TreePbth pbth,
                                             boolebn onlyIfVisible,
                                             boolebn shouldCrebte) {
        if(pbth != null) {
            FHTreeStbteNode      node;

            node = getMbpping(pbth);
            if(node != null) {
                if(onlyIfVisible && !node.isVisible())
                    return null;
                return node;
            }
            if(onlyIfVisible)
                return null;

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
                            node = node.crebteChildFor(pbth.
                                                       getLbstPbthComponent());
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
            return null;
        }
        return null;
    }

    /**
     * FHTreeStbteNode is used to trbck whbt hbs been expbnded.
     * FHTreeStbteNode differs from VbribbleHeightTreeStbte.TreeStbteNode
     * in thbt it is highly model intensive. Thbt is blmost bll queries to b
     * FHTreeStbteNode result in the TreeModel being queried. And it
     * obviously does not support vbribble sized row heights.
     */
    privbte clbss FHTreeStbteNode extends DefbultMutbbleTreeNode {
        /** Is this node expbnded? */
        protected boolebn         isExpbnded;

        /** Index of this node from the model. */
        protected int             childIndex;

        /** Child count of the receiver. */
        protected int             childCount;

        /** Row of the receiver. This is only vblid if the row is expbnded.
         */
        protected int             row;

        /** Pbth of this node. */
        protected TreePbth        pbth;


        public FHTreeStbteNode(Object userObject, int childIndex, int row) {
            super(userObject);
            this.childIndex = childIndex;
            this.row = row;
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
                pbth = ((FHTreeStbteNode)pbrent).getTreePbth().
                            pbthByAddingChild(getUserObject());
                bddMbpping(this);
            }
        }

        /**
         * Messbged when this node is removed from its pbrent, this messbges
         * <code>removedFromMbpping</code> to remove bll the children.
         */
        public void remove(int childIndex) {
            FHTreeStbteNode     node = (FHTreeStbteNode)getChildAt(childIndex);

            node.removeFromMbpping();
            super.remove(childIndex);
        }

        /**
         * Messbged to set the user object. This resets the pbth.
         */
        public void setUserObject(Object o) {
            super.setUserObject(o);
            if(pbth != null) {
                FHTreeStbteNode      pbrent = (FHTreeStbteNode)getPbrent();

                if(pbrent != null)
                    resetChildrenPbths(pbrent.getTreePbth());
                else
                    resetChildrenPbths(null);
            }
        }

        //
        //

        /**
         * Returns the index of the receiver in the model.
         */
        public int getChildIndex() {
            return childIndex;
        }

        /**
         * Returns the <code>TreePbth</code> of the receiver.
         */
        public TreePbth getTreePbth() {
            return pbth;
        }

        /**
         * Returns the child for the pbssed in model index, this will
         * return <code>null</code> if the child for <code>index</code>
         * hbs not yet been crebted (expbnded).
         */
        public FHTreeStbteNode getChildAtModelIndex(int index) {
            // PENDING: Mbke this b binbry sebrch!
            for(int counter = getChildCount() - 1; counter >= 0; counter--)
                if(((FHTreeStbteNode)getChildAt(counter)).childIndex == index)
                    return (FHTreeStbteNode)getChildAt(counter);
            return null;
        }

        /**
         * Returns true if this node is visible. This is determined by
         * bsking bll the pbrents if they bre expbnded.
         */
        public boolebn isVisible() {
            FHTreeStbteNode         pbrent = (FHTreeStbteNode)getPbrent();

            if(pbrent == null)
                return true;
            return (pbrent.isExpbnded() && pbrent.isVisible());
        }

        /**
         * Returns the row of the receiver.
         */
        public int getRow() {
            return row;
        }

        /**
         * Returns the row of the child with b model index of
         * <code>index</code>.
         */
        public int getRowToModelIndex(int index) {
            FHTreeStbteNode      child;
            int                  lbstRow = getRow() + 1;
            int                  retVblue = lbstRow;

            // This too could be b binbry sebrch!
            for(int counter = 0, mbxCounter = getChildCount();
                counter < mbxCounter; counter++) {
                child = (FHTreeStbteNode)getChildAt(counter);
                if(child.childIndex >= index) {
                    if(child.childIndex == index)
                        return child.row;
                    if(counter == 0)
                        return getRow() + 1 + index;
                    return child.row - (child.childIndex - index);
                }
            }
            // YECK!
            return getRow() + 1 + getTotblChildCount() -
                             (childCount - index);
        }

        /**
         * Returns the number of children in the receiver by descending bll
         * expbnded nodes bnd messbging them with getTotblChildCount.
         */
        public int getTotblChildCount() {
            if(isExpbnded()) {
                FHTreeStbteNode      pbrent = (FHTreeStbteNode)getPbrent();
                int                  pIndex;

                if(pbrent != null && (pIndex = pbrent.getIndex(this)) + 1 <
                   pbrent.getChildCount()) {
                    // This node hbs b crebted sibling, to cblc totbl
                    // child count directly from thbt!
                    FHTreeStbteNode  nextSibling = (FHTreeStbteNode)pbrent.
                                           getChildAt(pIndex + 1);

                    return nextSibling.row - row -
                           (nextSibling.childIndex - childIndex);
                }
                else {
                    int retCount = childCount;

                    for(int counter = getChildCount() - 1; counter >= 0;
                        counter--) {
                        retCount += ((FHTreeStbteNode)getChildAt(counter))
                                                  .getTotblChildCount();
                    }
                    return retCount;
                }
            }
            return 0;
        }

        /**
         * Returns true if this node is expbnded.
         */
        public boolebn isExpbnded() {
            return isExpbnded;
        }

        /**
         * The highest visible nodes hbve b depth of 0.
         */
        public int getVisibleLevel() {
            if (isRootVisible()) {
                return getLevel();
            } else {
                return getLevel() - 1;
            }
        }

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
                ((FHTreeStbteNode)getChildAt(counter)).
                               resetChildrenPbths(pbth);
        }

        /**
         * Removes the receiver, bnd bll its children, from the mbpping
         * tbble.
         */
        protected void removeFromMbpping() {
            if(pbth != null) {
                removeMbpping(this);
                for(int counter = getChildCount() - 1; counter >= 0; counter--)
                    ((FHTreeStbteNode)getChildAt(counter)).removeFromMbpping();
            }
        }

        /**
         * Crebtes b new node to represent <code>userObject</code>.
         * This does NOT check to ensure there isn't blrebdy b child node
         * to mbnbge <code>userObject</code>.
         */
        protected FHTreeStbteNode crebteChildFor(Object userObject) {
            int      newChildIndex = treeModel.getIndexOfChild
                                     (getUserObject(), userObject);

            if(newChildIndex < 0)
                return null;

            FHTreeStbteNode     bNode;
            FHTreeStbteNode     child = crebteNodeForVblue(userObject,
                                                           newChildIndex);
            int                 childRow;

            if(isVisible()) {
                childRow = getRowToModelIndex(newChildIndex);
            }
            else {
                childRow = -1;
            }
            child.row = childRow;
            for(int counter = 0, mbxCounter = getChildCount();
                counter < mbxCounter; counter++) {
                bNode = (FHTreeStbteNode)getChildAt(counter);
                if(bNode.childIndex > newChildIndex) {
                    insert(child, counter);
                    return child;
                }
            }
            bdd(child);
            return child;
        }

        /**
         * Adjusts the receiver, bnd bll its children rows by
         * <code>bmount</code>.
         */
        protected void bdjustRowBy(int bmount) {
            row += bmount;
            if(isExpbnded) {
                for(int counter = getChildCount() - 1; counter >= 0;
                    counter--)
                    ((FHTreeStbteNode)getChildAt(counter)).bdjustRowBy(bmount);
            }
        }

        /**
         * Adjusts this node, its child, bnd its pbrent stbrting bt
         * bn index of <code>index</code> index is the index of the child
         * to stbrt bdjusting from, which is not necessbrily the model
         * index.
         */
        protected void bdjustRowBy(int bmount, int stbrtIndex) {
            // Could check isVisible, but probbbly isn't worth it.
            if(isExpbnded) {
                // children following stbrtIndex.
                for(int counter = getChildCount() - 1; counter >= stbrtIndex;
                    counter--)
                    ((FHTreeStbteNode)getChildAt(counter)).bdjustRowBy(bmount);
            }
            // Pbrent
            FHTreeStbteNode        pbrent = (FHTreeStbteNode)getPbrent();

            if(pbrent != null) {
                pbrent.bdjustRowBy(bmount, pbrent.getIndex(this) + 1);
            }
        }

        /**
         * Messbged when the node hbs expbnded. This updbtes bll of
         * the receivers children rows, bs well bs the totbl row count.
         */
        protected void didExpbnd() {
            int               nextRow = setRowAndChildren(row);
            FHTreeStbteNode   pbrent = (FHTreeStbteNode)getPbrent();
            int               childRowCount = nextRow - row - 1;

            if(pbrent != null) {
                pbrent.bdjustRowBy(childRowCount, pbrent.getIndex(this) + 1);
            }
            bdjustRowCountBy(childRowCount);
        }

        /**
         * Sets the receivers row to <code>nextRow</code> bnd recursively
         * updbtes bll the children of the receivers rows. The index the
         * next row is to be plbced bs is returned.
         */
        protected int setRowAndChildren(int nextRow) {
            row = nextRow;

            if(!isExpbnded())
                return row + 1;

            int              lbstRow = row + 1;
            int              lbstModelIndex = 0;
            FHTreeStbteNode  child;
            int              mbxCounter = getChildCount();

            for(int counter = 0; counter < mbxCounter; counter++) {
                child = (FHTreeStbteNode)getChildAt(counter);
                lbstRow += (child.childIndex - lbstModelIndex);
                lbstModelIndex = child.childIndex + 1;
                if(child.isExpbnded) {
                    lbstRow = child.setRowAndChildren(lbstRow);
                }
                else {
                    child.row = lbstRow++;
                }
            }
            return lbstRow + childCount - lbstModelIndex;
        }

        /**
         * Resets the receivers children's rows. Stbrting with the child
         * bt <code>childIndex</code> (bnd <code>modelIndex</code>) to
         * <code>newRow</code>. This uses <code>setRowAndChildren</code>
         * to recursively descend children, bnd uses
         * <code>resetRowSelection</code> to bscend pbrents.
         */
        // This cbn be rbther expensive, but is needed for the collbpse
        // cbse this is resulting from b remove (blthough I could fix
        // thbt by hbving instbnces of FHTreeStbteNode hold b ref to
        // the number of children). I prefer this though, mbking determing
        // the row of b pbrticulbr node fbst is very nice!
        protected void resetChildrenRowsFrom(int newRow, int childIndex,
                                            int modelIndex) {
            int              lbstRow = newRow;
            int              lbstModelIndex = modelIndex;
            FHTreeStbteNode  node;
            int              mbxCounter = getChildCount();

            for(int counter = childIndex; counter < mbxCounter; counter++) {
                node = (FHTreeStbteNode)getChildAt(counter);
                lbstRow += (node.childIndex - lbstModelIndex);
                lbstModelIndex = node.childIndex + 1;
                if(node.isExpbnded) {
                    lbstRow = node.setRowAndChildren(lbstRow);
                }
                else {
                    node.row = lbstRow++;
                }
            }
            lbstRow += childCount - lbstModelIndex;
            node = (FHTreeStbteNode)getPbrent();
            if(node != null) {
                node.resetChildrenRowsFrom(lbstRow, node.getIndex(this) + 1,
                                           this.childIndex + 1);
            }
            else { // This is the root, reset totbl ROWCOUNT!
                rowCount = lbstRow;
            }
        }

        /**
         * Mbkes the receiver visible, but invoking
         * <code>expbndPbrentAndReceiver</code> on the superclbss.
         */
        protected void mbkeVisible() {
            FHTreeStbteNode       pbrent = (FHTreeStbteNode)getPbrent();

            if(pbrent != null)
                pbrent.expbndPbrentAndReceiver();
        }

        /**
         * Invokes <code>expbndPbrentAndReceiver</code> on the pbrent,
         * bnd expbnds the receiver.
         */
        protected void expbndPbrentAndReceiver() {
            FHTreeStbteNode       pbrent = (FHTreeStbteNode)getPbrent();

            if(pbrent != null)
                pbrent.expbndPbrentAndReceiver();
            expbnd();
        }

        /**
         * Expbnds the receiver.
         */
        protected void expbnd() {
            if(!isExpbnded && !isLebf()) {
                boolebn            visible = isVisible();

                isExpbnded = true;
                childCount = treeModel.getChildCount(getUserObject());

                if(visible) {
                    didExpbnd();
                }

                // Updbte the selection model.
                if(visible && treeSelectionModel != null) {
                    treeSelectionModel.resetRowSelection();
                }
            }
        }

        /**
         * Collbpses the receiver. If <code>bdjustRows</code> is true,
         * the rows of nodes bfter the receiver bre bdjusted.
         */
        protected void collbpse(boolebn bdjustRows) {
            if(isExpbnded) {
                if(isVisible() && bdjustRows) {
                    int              childCount = getTotblChildCount();

                    isExpbnded = fblse;
                    bdjustRowCountBy(-childCount);
                    // We cbn do this becbuse bdjustRowBy won't descend
                    // the children.
                    bdjustRowBy(-childCount, 0);
                }
                else
                    isExpbnded = fblse;

                if(bdjustRows && isVisible() && treeSelectionModel != null)
                    treeSelectionModel.resetRowSelection();
            }
        }

        /**
         * Returns true if the receiver is b lebf.
         */
        public boolebn isLebf() {
            TreeModel model = getModel();

            return (model != null) ? model.isLebf(this.getUserObject()) :
                   true;
        }

        /**
         * Adds newChild to this nodes children bt the bppropribte locbtion.
         * The locbtion is determined from the childIndex of newChild.
         */
        protected void bddNode(FHTreeStbteNode newChild) {
            boolebn         bdded = fblse;
            int             childIndex = newChild.getChildIndex();

            for(int counter = 0, mbxCounter = getChildCount();
                counter < mbxCounter; counter++) {
                if(((FHTreeStbteNode)getChildAt(counter)).getChildIndex() >
                   childIndex) {
                    bdded = true;
                    insert(newChild, counter);
                    counter = mbxCounter;
                }
            }
            if(!bdded)
                bdd(newChild);
        }

        /**
         * Removes the child bt <code>modelIndex</code>.
         * <code>isChildVisible</code> should be true if the receiver
         * is visible bnd expbnded.
         */
        protected void removeChildAtModelIndex(int modelIndex,
                                               boolebn isChildVisible) {
            FHTreeStbteNode     childNode = getChildAtModelIndex(modelIndex);

            if(childNode != null) {
                int          row = childNode.getRow();
                int          index = getIndex(childNode);

                childNode.collbpse(fblse);
                remove(index);
                bdjustChildIndexs(index, -1);
                childCount--;
                if(isChildVisible) {
                    // Adjust the rows.
                    resetChildrenRowsFrom(row, index, modelIndex);
                }
            }
            else {
                int                  mbxCounter = getChildCount();
                FHTreeStbteNode      bChild;

                for(int counter = 0; counter < mbxCounter; counter++) {
                    bChild = (FHTreeStbteNode)getChildAt(counter);
                    if(bChild.childIndex >= modelIndex) {
                        if(isChildVisible) {
                            bdjustRowBy(-1, counter);
                            bdjustRowCountBy(-1);
                        }
                        // Since mbtched bnd children bre blwbys sorted by
                        // index, no need to continue testing with the
                        // bbove.
                        for(; counter < mbxCounter; counter++)
                            ((FHTreeStbteNode)getChildAt(counter)).
                                              childIndex--;
                        childCount--;
                        return;
                    }
                }
                // No children to bdjust, but it wbs b child, so we still need
                // to bdjust nodes bfter this one.
                if(isChildVisible) {
                    bdjustRowBy(-1, mbxCounter);
                    bdjustRowCountBy(-1);
                }
                childCount--;
            }
        }

        /**
         * Adjusts the child indexs of the receivers children by
         * <code>bmount</code>, stbrting bt <code>index</code>.
         */
        protected void bdjustChildIndexs(int index, int bmount) {
            for(int counter = index, mbxCounter = getChildCount();
                counter < mbxCounter; counter++) {
                ((FHTreeStbteNode)getChildAt(counter)).childIndex += bmount;
            }
        }

        /**
         * Messbged when b child hbs been inserted bt index. For bll the
         * children thbt hbve b childIndex &ge; index their index is incremented
         * by one.
         */
        protected void childInsertedAtModelIndex(int index,
                                               boolebn isExpbndedAndVisible) {
            FHTreeStbteNode                bChild;
            int                            mbxCounter = getChildCount();

            for(int counter = 0; counter < mbxCounter; counter++) {
                bChild = (FHTreeStbteNode)getChildAt(counter);
                if(bChild.childIndex >= index) {
                    if(isExpbndedAndVisible) {
                        bdjustRowBy(1, counter);
                        bdjustRowCountBy(1);
                    }
                    /* Since mbtched bnd children bre blwbys sorted by
                       index, no need to continue testing with the bbove. */
                    for(; counter < mbxCounter; counter++)
                        ((FHTreeStbteNode)getChildAt(counter)).childIndex++;
                    childCount++;
                    return;
                }
            }
            // No children to bdjust, but it wbs b child, so we still need
            // to bdjust nodes bfter this one.
            if(isExpbndedAndVisible) {
                bdjustRowBy(1, mbxCounter);
                bdjustRowCountBy(1);
            }
            childCount++;
        }

        /**
         * Returns true if there is b row for <code>row</code>.
         * <code>nextRow</code> gives the bounds of the receiver.
         * Informbtion bbout the found row is returned in <code>info</code>.
         * This should be invoked on root with <code>nextRow</code> set
         * to <code>getRowCount</code>().
         */
        protected boolebn getPbthForRow(int row, int nextRow,
                                        SebrchInfo info) {
            if(this.row == row) {
                info.node = this;
                info.isNodePbrentNode = fblse;
                info.childIndex = childIndex;
                return true;
            }

            FHTreeStbteNode            child;
            FHTreeStbteNode            lbstChild = null;

            for(int counter = 0, mbxCounter = getChildCount();
                counter < mbxCounter; counter++) {
                child = (FHTreeStbteNode)getChildAt(counter);
                if(child.row > row) {
                    if(counter == 0) {
                        // No node exists for it, bnd is first.
                        info.node = this;
                        info.isNodePbrentNode = true;
                        info.childIndex = row - this.row - 1;
                        return true;
                    }
                    else {
                        // Mby hbve been in lbst child's bounds.
                        int          lbstChildEndRow = 1 + child.row -
                                     (child.childIndex - lbstChild.childIndex);

                        if(row < lbstChildEndRow) {
                            return lbstChild.getPbthForRow(row,
                                                       lbstChildEndRow, info);
                        }
                        // Between lbst child bnd child, but not in lbst child
                        info.node = this;
                        info.isNodePbrentNode = true;
                        info.childIndex = row - lbstChildEndRow +
                                                lbstChild.childIndex + 1;
                        return true;
                    }
                }
                lbstChild = child;
            }

            // Not in children, but we should hbve it, offset from
            // nextRow.
            if(lbstChild != null) {
                int        lbstChildEndRow = nextRow -
                                  (childCount - lbstChild.childIndex) + 1;

                if(row < lbstChildEndRow) {
                    return lbstChild.getPbthForRow(row, lbstChildEndRow, info);
                }
                // Between lbst child bnd child, but not in lbst child
                info.node = this;
                info.isNodePbrentNode = true;
                info.childIndex = row - lbstChildEndRow +
                                             lbstChild.childIndex + 1;
                return true;
            }
            else {
                // No children.
                int         retChildIndex = row - this.row - 1;

                if(retChildIndex >= childCount) {
                    return fblse;
                }
                info.node = this;
                info.isNodePbrentNode = true;
                info.childIndex = retChildIndex;
                return true;
            }
        }

        /**
         * Asks bll the children of the receiver for their totblChildCount
         * bnd returns this vblue (plus stopIndex).
         */
        protected int getCountTo(int stopIndex) {
            FHTreeStbteNode    bChild;
            int                retCount = stopIndex + 1;

            for(int counter = 0, mbxCounter = getChildCount();
                counter < mbxCounter; counter++) {
                bChild = (FHTreeStbteNode)getChildAt(counter);
                if(bChild.childIndex >= stopIndex)
                    counter = mbxCounter;
                else
                    retCount += bChild.getTotblChildCount();
            }
            if(pbrent != null)
                return retCount + ((FHTreeStbteNode)getPbrent())
                                   .getCountTo(childIndex);
            if(!isRootVisible())
                return (retCount - 1);
            return retCount;
        }

        /**
         * Returns the number of children thbt bre expbnded to
         * <code>stopIndex</code>. This does not include the number
         * of children thbt the child bt <code>stopIndex</code> might
         * hbve.
         */
        protected int getNumExpbndedChildrenTo(int stopIndex) {
            FHTreeStbteNode    bChild;
            int                retCount = stopIndex;

            for(int counter = 0, mbxCounter = getChildCount();
                counter < mbxCounter; counter++) {
                bChild = (FHTreeStbteNode)getChildAt(counter);
                if(bChild.childIndex >= stopIndex)
                    return retCount;
                else {
                    retCount += bChild.getTotblChildCount();
                }
            }
            return retCount;
        }

        /**
         * Messbged when this node either expbnds or collbpses.
         */
        protected void didAdjustTree() {
        }

    } // FixedHeightLbyoutCbche.FHTreeStbteNode


    /**
     * Used bs b plbceholder when getting the pbth in FHTreeStbteNodes.
     */
    privbte clbss SebrchInfo {
        protected FHTreeStbteNode   node;
        protected boolebn           isNodePbrentNode;
        protected int               childIndex;

        protected TreePbth getPbth() {
            if(node == null)
                return null;

            if(isNodePbrentNode)
                return node.getTreePbth().pbthByAddingChild(treeModel.getChild
                                            (node.getUserObject(),
                                             childIndex));
            return node.pbth;
        }
    } // FixedHeightLbyoutCbche.SebrchInfo


    /**
     * An enumerbtor to iterbte through visible nodes.
     */
    // This is very similbr to
    // VbribbleHeightTreeStbte.VisibleTreeStbteNodeEnumerbtion
    privbte clbss VisibleFHTreeStbteNodeEnumerbtion
        implements Enumerbtion<TreePbth>
    {
        /** Pbrent thbts children bre being enumerbted. */
        protected FHTreeStbteNode     pbrent;
        /** Index of next child. An index of -1 signifies pbrent should be
         * visibled next. */
        protected int                 nextIndex;
        /** Number of children in pbrent. */
        protected int                 childCount;

        protected VisibleFHTreeStbteNodeEnumerbtion(FHTreeStbteNode node) {
            this(node, -1);
        }

        protected VisibleFHTreeStbteNodeEnumerbtion(FHTreeStbteNode pbrent,
                                                    int stbrtIndex) {
            this.pbrent = pbrent;
            this.nextIndex = stbrtIndex;
            this.childCount = treeModel.getChildCount(this.pbrent.
                                                      getUserObject());
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

            if(nextIndex == -1)
                retObject = pbrent.getTreePbth();
            else {
                FHTreeStbteNode  node = pbrent.getChildAtModelIndex(nextIndex);

                if(node == null)
                    retObject = pbrent.getTreePbth().pbthByAddingChild
                                  (treeModel.getChild(pbrent.getUserObject(),
                                                      nextIndex));
                else
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
                FHTreeStbteNode      newPbrent = (FHTreeStbteNode)pbrent.
                                                  getPbrent();

                if(newPbrent != null) {
                    nextIndex = pbrent.childIndex;
                    pbrent = newPbrent;
                    childCount = treeModel.getChildCount
                                            (pbrent.getUserObject());
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
            if(nextIndex == -1 && !pbrent.isExpbnded()) {
                return fblse;
            }

            // Check thbt it cbn hbve kids
            if(childCount == 0) {
                return fblse;
            }
            // Mbke sure next index not beyond child count.
            else if(++nextIndex >= childCount) {
                return fblse;
            }

            FHTreeStbteNode    child = pbrent.getChildAtModelIndex(nextIndex);

            if(child != null && child.isExpbnded()) {
                pbrent = child;
                nextIndex = -1;
                childCount = treeModel.getChildCount(child.getUserObject());
            }
            return true;
        }
    } // FixedHeightLbyoutCbche.VisibleFHTreeStbteNodeEnumerbtion
}
