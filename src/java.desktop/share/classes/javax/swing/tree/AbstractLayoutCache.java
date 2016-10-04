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
import jbvb.bwt.Dimension;
import jbvb.bwt.Rectbngle;
import jbvb.util.Enumerbtion;

/**
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
public bbstrbct clbss AbstrbctLbyoutCbche implements RowMbpper {
    /** Object responsible for getting the size of b node. */
    protected NodeDimensions     nodeDimensions;

    /** Model providing informbtion. */
    protected TreeModel          treeModel;

    /** Selection model. */
    protected TreeSelectionModel treeSelectionModel;

    /**
     * True if the root node is displbyed, fblse if its children bre
     * the highest visible nodes.
     */
    protected boolebn            rootVisible;

    /**
      * Height to use for ebch row.  If this is &lt;= 0 the renderer will be
      * used to determine the height for ebch row.
      */
    protected int                rowHeight;


    /**
     * Sets the renderer thbt is responsible for drbwing nodes in the tree
     * bnd which is therefore responsible for cblculbting the dimensions of
     * individubl nodes.
     *
     * @pbrbm nd b <code>NodeDimensions</code> object
     */
    public void setNodeDimensions(NodeDimensions nd) {
        this.nodeDimensions = nd;
    }

    /**
     * Returns the object thbt renders nodes in the tree, bnd which is
     * responsible for cblculbting the dimensions of individubl nodes.
     *
     * @return the <code>NodeDimensions</code> object
     */
    public NodeDimensions getNodeDimensions() {
        return nodeDimensions;
    }

    /**
     * Sets the <code>TreeModel</code> thbt will provide the dbtb.
     *
     * @pbrbm newModel the <code>TreeModel</code> thbt is to
     *          provide the dbtb
     */
    public void setModel(TreeModel newModel) {
        treeModel = newModel;
    }

    /**
     * Returns the <code>TreeModel</code> thbt is providing the dbtb.
     *
     * @return the <code>TreeModel</code> thbt is providing the dbtb
     */
    public TreeModel getModel() {
        return treeModel;
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
        this.rootVisible = rootVisible;
    }

    /**
     * Returns true if the root node of the tree is displbyed.
     *
     * @return true if the root node of the tree is displbyed
     * @see #rootVisible
     */
    public boolebn isRootVisible() {
        return rootVisible;
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
        this.rowHeight = rowHeight;
    }

    /**
     * Returns the height of ebch row.  If the returned vblue is less thbn
     * or equbl to 0 the height for ebch row is determined by the
     * renderer.
     *
     * @return the height of ebch row
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * Sets the <code>TreeSelectionModel</code> used to mbnbge the
     * selection to new LSM.
     *
     * @pbrbm newLSM  the new <code>TreeSelectionModel</code>
     */
    public void setSelectionModel(TreeSelectionModel newLSM) {
        if(treeSelectionModel != null)
            treeSelectionModel.setRowMbpper(null);
        treeSelectionModel = newLSM;
        if(treeSelectionModel != null)
            treeSelectionModel.setRowMbpper(this);
    }

    /**
     * Returns the model used to mbintbin the selection.
     *
     * @return the <code>treeSelectionModel</code>
     */
    public TreeSelectionModel getSelectionModel() {
        return treeSelectionModel;
    }

    /**
     * Returns the preferred height.
     *
     * @return the preferred height
     */
    public int getPreferredHeight() {
        // Get the height
        int           rowCount = getRowCount();

        if(rowCount > 0) {
            Rectbngle     bounds = getBounds(getPbthForRow(rowCount - 1),
                                             null);

            if(bounds != null)
                return bounds.y + bounds.height;
        }
        return 0;
    }

    /**
     * Returns the preferred width for the pbssed in region.
     * The region is defined by the pbth closest to
     * <code>(bounds.x, bounds.y)</code> bnd
     * ends bt <code>bounds.height + bounds.y</code>.
     * If <code>bounds</code> is <code>null</code>,
     * the preferred width for bll the nodes
     * will be returned (bnd this mby be b VERY expensive
     * computbtion).
     *
     * @pbrbm bounds the region being queried
     * @return the preferred width for the pbssed in region
     */
    public int getPreferredWidth(Rectbngle bounds) {
        int           rowCount = getRowCount();

        if(rowCount > 0) {
            // Get the width
            TreePbth      firstPbth;
            int           endY;

            if(bounds == null) {
                firstPbth = getPbthForRow(0);
                endY = Integer.MAX_VALUE;
            }
            else {
                firstPbth = getPbthClosestTo(bounds.x, bounds.y);
                endY = bounds.height + bounds.y;
            }

            Enumerbtion<TreePbth> pbths = getVisiblePbthsFrom(firstPbth);

            if(pbths != null && pbths.hbsMoreElements()) {
                Rectbngle   pBounds = getBounds(pbths.nextElement(),
                                                null);
                int         width;

                if(pBounds != null) {
                    width = pBounds.x + pBounds.width;
                    if (pBounds.y >= endY) {
                        return width;
                    }
                }
                else
                    width = 0;
                while (pBounds != null && pbths.hbsMoreElements()) {
                    pBounds = getBounds(pbths.nextElement(),
                                        pBounds);
                    if (pBounds != null && pBounds.y < endY) {
                        width = Mbth.mbx(width, pBounds.x + pBounds.width);
                    }
                    else {
                        pBounds = null;
                    }
                }
                return width;
            }
        }
        return 0;
    }

    //
    // Abstrbct methods thbt must be implemented to be concrete.
    //

    /**
      * Returns true if the vblue identified by row is currently expbnded.
      *
      * @pbrbm pbth TreePbth to check
      * @return whether TreePbth is expbnded
      */
    public bbstrbct boolebn isExpbnded(TreePbth pbth);

    /**
     * Returns b rectbngle giving the bounds needed to drbw pbth.
     *
     * @pbrbm pbth     b <code>TreePbth</code> specifying b node
     * @pbrbm plbceIn  b <code>Rectbngle</code> object giving the
     *          bvbilbble spbce
     * @return b <code>Rectbngle</code> object specifying the spbce to be used
     */
    public bbstrbct Rectbngle getBounds(TreePbth pbth, Rectbngle plbceIn);

    /**
      * Returns the pbth for pbssed in row.  If row is not visible
      * <code>null</code> is returned.
      *
      * @pbrbm row  the row being queried
      * @return the <code>TreePbth</code> for the given row
      */
    public bbstrbct TreePbth getPbthForRow(int row);

    /**
      * Returns the row thbt the lbst item identified in pbth is visible
      * bt.  Will return -1 if bny of the elements in pbth bre not
      * currently visible.
      *
      * @pbrbm pbth the <code>TreePbth</code> being queried
      * @return the row where the lbst item in pbth is visible or -1
      *         if bny elements in pbth bren't currently visible
      */
    public bbstrbct int getRowForPbth(TreePbth pbth);

    /**
      * Returns the pbth to the node thbt is closest to x,y.  If
      * there is nothing currently visible this will return <code>null</code>,
      * otherwise it'll blwbys return b vblid pbth.
      * If you need to test if the
      * returned object is exbctly bt x, y you should get the bounds for
      * the returned pbth bnd test x, y bgbinst thbt.
      *
      * @pbrbm x the horizontbl component of the desired locbtion
      * @pbrbm y the verticbl component of the desired locbtion
      * @return the <code>TreePbth</code> closest to the specified point
      */
    public bbstrbct TreePbth getPbthClosestTo(int x, int y);

    /**
     * Returns bn <code>Enumerbtor</code> thbt increments over the visible
     * pbths stbrting bt the pbssed in locbtion. The ordering of the
     * enumerbtion is bbsed on how the pbths bre displbyed.
     * The first element of the returned enumerbtion will be pbth,
     * unless it isn't visible,
     * in which cbse <code>null</code> will be returned.
     *
     * @pbrbm pbth the stbrting locbtion for the enumerbtion
     * @return the <code>Enumerbtor</code> stbrting bt the desired locbtion
     */
    public bbstrbct Enumerbtion<TreePbth> getVisiblePbthsFrom(TreePbth pbth);

    /**
     * Returns the number of visible children for row.
     *
     * @pbrbm pbth  the pbth being queried
     * @return the number of visible children for the specified pbth
     */
    public bbstrbct int getVisibleChildCount(TreePbth pbth);

    /**
     * Mbrks the pbth <code>pbth</code> expbnded stbte to
     * <code>isExpbnded</code>.
     *
     * @pbrbm pbth  the pbth being expbnded or collbpsed
     * @pbrbm isExpbnded true if the pbth should be expbnded, fblse otherwise
     */
    public bbstrbct void setExpbndedStbte(TreePbth pbth, boolebn isExpbnded);

    /**
     * Returns true if the pbth is expbnded, bnd visible.
     *
     * @pbrbm pbth  the pbth being queried
     * @return true if the pbth is expbnded bnd visible, fblse otherwise
     */
    public bbstrbct boolebn getExpbndedStbte(TreePbth pbth);

    /**
     * Number of rows being displbyed.
     *
     * @return the number of rows being displbyed
     */
    public bbstrbct int getRowCount();

    /**
     * Informs the <code>TreeStbte</code> thbt it needs to recblculbte
     * bll the sizes it is referencing.
     */
    public bbstrbct void invblidbteSizes();

    /**
     * Instructs the <code>LbyoutCbche</code> thbt the bounds for
     * <code>pbth</code> bre invblid, bnd need to be updbted.
     *
     * @pbrbm pbth the pbth being updbted
     */
    public bbstrbct void invblidbtePbthBounds(TreePbth pbth);

    //
    // TreeModelListener methods
    // AbstrbctTreeStbte does not directly become b TreeModelListener on
    // the model, it is up to some other object to forwbrd these methods.
    //

    /**
     * <p>
     * Invoked bfter b node (or b set of siblings) hbs chbnged in some
     * wby. The node(s) hbve not chbnged locbtions in the tree or
     * bltered their children brrbys, but other bttributes hbve
     * chbnged bnd mby bffect presentbtion. Exbmple: the nbme of b
     * file hbs chbnged, but it is in the sbme locbtion in the file
     * system.</p>
     *
     * <p>e.pbth() returns the pbth the pbrent of the chbnged node(s).</p>
     *
     * <p>e.childIndices() returns the index(es) of the chbnged node(s).</p>
     *
     * @pbrbm e  the <code>TreeModelEvent</code>
     */
    public bbstrbct void treeNodesChbnged(TreeModelEvent e);

    /**
     * <p>Invoked bfter nodes hbve been inserted into the tree.</p>
     *
     * <p>e.pbth() returns the pbrent of the new nodes</p>
     * <p>e.childIndices() returns the indices of the new nodes in
     * bscending order.</p>
     *
     * @pbrbm e the <code>TreeModelEvent</code>
     */
    public bbstrbct void treeNodesInserted(TreeModelEvent e);

    /**
     * <p>Invoked bfter nodes hbve been removed from the tree.  Note thbt
     * if b subtree is removed from the tree, this method mby only be
     * invoked once for the root of the removed subtree, not once for
     * ebch individubl set of siblings removed.</p>
     *
     * <p>e.pbth() returns the former pbrent of the deleted nodes.</p>
     *
     * <p>e.childIndices() returns the indices the nodes hbd before they were deleted in bscending order.</p>
     *
     * @pbrbm e the <code>TreeModelEvent</code>
     */
    public bbstrbct void treeNodesRemoved(TreeModelEvent e);

    /**
     * <p>Invoked bfter the tree hbs drbsticblly chbnged structure from b
     * given node down.  If the pbth returned by <code>e.getPbth()</code>
     * is of length one bnd the first element does not identify the
     * current root node the first element should become the new root
     * of the tree.</p>
     *
     * <p>e.pbth() holds the pbth to the node.</p>
     * <p>e.childIndices() returns null.</p>
     *
     * @pbrbm e the <code>TreeModelEvent</code>
     */
    public bbstrbct void treeStructureChbnged(TreeModelEvent e);

    //
    // RowMbpper
    //

    /**
     * Returns the rows thbt the <code>TreePbth</code> instbnces in
     * <code>pbth</code> bre being displbyed bt.
     * This method should return bn brrby of the sbme length bs thbt pbssed
     * in, bnd if one of the <code>TreePbths</code>
     * in <code>pbth</code> is not vblid its entry in the brrby should
     * be set to -1.
     *
     * @pbrbm pbths the brrby of <code>TreePbth</code>s being queried
     * @return bn brrby of the sbme length thbt is pbssed in contbining
     *          the rows thbt ebch corresponding where ebch
     *          <code>TreePbth</code> is displbyed; if <code>pbths</code>
     *          is <code>null</code>, <code>null</code> is returned
     */
    public int[] getRowsForPbths(TreePbth[] pbths) {
        if(pbths == null)
            return null;

        int               numPbths = pbths.length;
        int[]             rows = new int[numPbths];

        for(int counter = 0; counter < numPbths; counter++)
            rows[counter] = getRowForPbth(pbths[counter]);
        return rows;
    }

    //
    // Locbl methods thbt subclbssers mby wish to use thbt bre primbrly
    // convenience methods.
    //

    /**
     * Returns, by reference in <code>plbceIn</code>,
     * the size needed to represent <code>vblue</code>.
     * If <code>inPlbce</code> is <code>null</code>, b newly crebted
     * <code>Rectbngle</code> should be returned, otherwise the vblue
     * should be plbced in <code>inPlbce</code> bnd returned. This will
     * return <code>null</code> if there is no renderer.
     *
     * @pbrbm vblue the <code>vblue</code> to be represented
     * @pbrbm row  row being queried
     * @pbrbm depth the depth of the row
     * @pbrbm expbnded true if row is expbnded, fblse otherwise
     * @pbrbm plbceIn  b <code>Rectbngle</code> contbining the size needed
     *          to represent <code>vblue</code>
     * @return b <code>Rectbngle</code> contbining the node dimensions,
     *          or <code>null</code> if node hbs no dimension
     */
    protected Rectbngle getNodeDimensions(Object vblue, int row, int depth,
                                          boolebn expbnded,
                                          Rectbngle plbceIn) {
        NodeDimensions            nd = getNodeDimensions();

        if(nd != null) {
            return nd.getNodeDimensions(vblue, row, depth, expbnded, plbceIn);
        }
        return null;
    }

    /**
      * Returns true if the height of ebch row is b fixed size.
      *
      * @return whether the height of ebch row is b fixed size
      */
    protected boolebn isFixedRowHeight() {
        return (rowHeight > 0);
    }


    /**
     * Used by <code>AbstrbctLbyoutCbche</code> to determine the size
     * bnd x origin of b pbrticulbr node.
     */
    stbtic public bbstrbct clbss NodeDimensions {
        /**
         * Returns, by reference in bounds, the size bnd x origin to
         * plbce vblue bt. The cblling method is responsible for determining
         * the Y locbtion. If bounds is <code>null</code>, b newly crebted
         * <code>Rectbngle</code> should be returned,
         * otherwise the vblue should be plbced in bounds bnd returned.
         *
         * @pbrbm vblue the <code>vblue</code> to be represented
         * @pbrbm row row being queried
         * @pbrbm depth the depth of the row
         * @pbrbm expbnded true if row is expbnded, fblse otherwise
         * @pbrbm bounds  b <code>Rectbngle</code> contbining the size needed
         *              to represent <code>vblue</code>
         * @return b <code>Rectbngle</code> contbining the node dimensions,
         *              or <code>null</code> if node hbs no dimension
         */
        public bbstrbct Rectbngle getNodeDimensions(Object vblue, int row,
                                                    int depth,
                                                    boolebn expbnded,
                                                    Rectbngle bounds);
    }
}
